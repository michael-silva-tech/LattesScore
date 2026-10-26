package com.lattesscore.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "pesquisador")
public class Pesquisador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pesquisador")
    private Long idPesquisador;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_usuario", nullable = false, unique = true)
    private Usuario usuario;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(name = "id_lattes", nullable = false, unique = true, length = 32)
    private String idLattes;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(length = 150)
    private String departamento;

    



    
    public Long getIdPesquisador() { return idPesquisador; }
    public void setIdPesquisador(Long idPesquisador) { this.idPesquisador = idPesquisador; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getIdLattes() { return idLattes; }
    public void setIdLattes(String idLattes) { this.idLattes = idLattes; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }
}