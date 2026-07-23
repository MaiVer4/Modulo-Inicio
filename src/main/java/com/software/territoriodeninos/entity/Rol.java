package com.software.territoriodeninos.entity;

import com.software.territoriodeninos.util.NombreRol;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rol {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true, length = 50)
    private NombreRol nombre;
    
    @Column(length = 255)
    private String descripcion;
    
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;
    
    @OneToMany(mappedBy = "rol", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private List<Usuario> usuarios;
    
    public Rol(NombreRol nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    
    @PrePersist
    protected void onCreate() {
        if (this.fechaCreacion == null) {
            this.fechaCreacion = LocalDateTime.now();
        }
    }
}
