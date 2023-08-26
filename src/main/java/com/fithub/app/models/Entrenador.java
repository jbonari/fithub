package com.fithub.app.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "entrenador")
public class Entrenador implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "identrenador")
    private Long idEntrenador;

    @NotNull
    @Size(max = 500)
    @Column(name = "descripcion")
    private String descripcion;

    @NotNull
    @Size(max = 500)
    @Column(name = "servicios")
    private String servicios;

    @Size(max = 200)
    @Column(name = "foto_perfil")
    private String fotoPerfil;

    @Column(name = "delete")
    private boolean delete;

    @OneToOne
    @JoinColumn(name = "usuario_idusuario",referencedColumnName = "idusuario")
    private Usuario usuario;










}
