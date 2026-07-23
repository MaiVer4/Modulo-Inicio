package com.software.territoriodeninos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Schema(description = "DTO de respuesta con información del usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioResponseDTO {
    
    @Schema(description = "ID del usuario")
    private Long id;
    
    @Schema(description = "Nombre completo del usuario", example = "Juan Pérez")
    private String nombre;
    
    @Schema(description = "Correo electrónico del usuario", example = "juan@example.com")
    private String correo;
    
    @Schema(description = "Estado actual del usuario", example = "ACTIVO")
    private String estado;
    
    @Schema(description = "Nombre del rol del usuario", example = "TAQUILLERO")
    private String rol;
    
    @Schema(description = "Fecha de creación del usuario")
    private LocalDateTime fechaCreacion;
    
    @Schema(description = "Fecha de última actualización del usuario")
    private LocalDateTime fechaActualizacion;
}
