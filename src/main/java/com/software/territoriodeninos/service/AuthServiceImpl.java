package com.software.territoriodeninos.service;

import com.software.territoriodeninos.dto.AuthResponseDTO;
import com.software.territoriodeninos.dto.LoginRequestDTO;
import com.software.territoriodeninos.entity.Usuario;
import com.software.territoriodeninos.exception.CredencialesInvalidasException;
import com.software.territoriodeninos.repository.UsuarioRepository;
import com.software.territoriodeninos.security.JwtUtil;
import com.software.territoriodeninos.util.EstadoUsuario;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService {
    
    private final UsuarioRepository usuarioRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    
    public AuthServiceImpl(UsuarioRepository usuarioRepository, JwtUtil jwtUtil,
                            PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    public AuthResponseDTO login(LoginRequestDTO loginRequestDTO) {
        Usuario usuario = usuarioRepository.findByCorreoAndEstado(loginRequestDTO.getCorreo(), EstadoUsuario.ACTIVO)
                .orElseThrow(() -> new CredencialesInvalidasException("Correo o contraseña incorrectos"));
        
        if (!passwordEncoder.matches(loginRequestDTO.getPassword(), usuario.getPassword())) {
            throw new CredencialesInvalidasException("Correo o contraseña incorrectos");
        }
        
        String nombreRol = usuario.getRol().getNombre().name();
        String token = jwtUtil.generarToken(usuario.getCorreo(), nombreRol);
        
        return AuthResponseDTO.builder()
                .token(token)
                .tipoToken("Bearer")
                .correo(usuario.getCorreo())
                .rol(nombreRol)
                .build();
    }
}
