package com.lattesscore.backend.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "barema", uniqueConstraints = {
    @UniqueConstraint(name = "uk_barema_nome_area_versao", columnNames = {"nome", "area_avaliacao", "versao"})
})
public class Barema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_barema")
    private Long idBarema;

    @Column(nullable = false, length = 180)
    private String nome;

    @Column(name = "periodo_inicio", nullable = false)
    private LocalDate periodoInicio;

    @Column(name = "periodo_fim", nullable = false)
    private LocalDate periodoFim;

    @Column(name = "area_avaliacao", nullable = false, length = 150)
    private String areaAvaliacao;

    @Column(nullable = false, length = 30)
    private String versao;

    @Column(nullable = false)
    private Boolean ativo = false;

   


    
    public Long getIdBarema() { return idBarema; }
    public void setIdBarema(Long idBarema) { this.idBarema = idBarema; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public LocalDate getPeriodoInicio() { return periodoInicio; }
    public void setPeriodoInicio(LocalDate periodoInicio) { this.periodoInicio = periodoInicio; }
    public LocalDate getPeriodoFim() { return periodoFim; }
    public void setPeriodoFim(LocalDate periodoFim) { this.periodoFim = periodoFim; }
    public String getAreaAvaliacao() { return areaAvaliacao; }
    public void setAreaAvaliacao(String areaAvaliacao) { this.areaAvaliacao = areaAvaliacao; }
    public String getVersao() { return versao; }
    public void setVersao(String versao) { this.versao = versao; }
    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }
}