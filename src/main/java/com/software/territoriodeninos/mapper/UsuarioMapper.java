package com.software.territoriodeninos.mapper;

import com.software.territoriodeninos.dto.UsuarioResponseDTO;
import com.software.territoriodeninos.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    /**
     * Maps a Usuario entity to a UsuarioResponseDTO.
     *
     * @param usuario the entity to map
     * @return the mapped DTO, or null if the input is null
     */
    public UsuarioResponseDTO toResponseDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        return UsuarioResponseDTO.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .correo(usuario.getCorreo())
                .estado(usuario.getEstado() != null ? usuario.getEstado().name() : null)
                .rol(usuario.getRol() != null && usuario.getRol().getNombre() != null 
                        ? usuario.getRol().getNombre().name() : null)
                .fechaCreacion(usuario.getFechaCreacion())
                .fechaActualizacion(usuario.getFechaActualizacion())
                .build();
    }
}
