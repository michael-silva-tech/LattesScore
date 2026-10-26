package com.lattesscore.backend.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pontuacao")
public class Pontuacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pontuacao")
    private Long idPontuacao;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_pesquisador", nullable = false)
    private Pesquisador pesquisador;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_barema", nullable = false)
    private Barema barema;

    @Column(name = "protocolo_calculo", nullable = false, unique = true, length = 80)
    private String protocoloCalculo;

    @Column(name = "ano_referencia", nullable = false)
    private Integer anoReferencia;

    @Column(name = "total_pontos", nullable = false, precision = 12, scale = 2)
    private BigDecimal totalPontos = BigDecimal.ZERO;

    @Column(name = "data_calculo", nullable = false, updatable = false)
    private LocalDateTime dataCalculo;

    @PrePersist
    protected void onCreate() {
        this.dataCalculo = LocalDateTime.now();
    }

  

    
    public Long getIdPontuacao() { return idPontuacao; }
    public void setIdPontuacao(Long idPontuacao) { this.idPontuacao = idPontuacao; }
    public Pesquisador getPesquisador() { return pesquisador; }
    public void setPesquisador(Pesquisador pesquisador) { this.pesquisador = pesquisador; }
    public Barema getBarema() { return barema; }
    public void setBarema(Barema barema) { this.barema = barema; }
    public String getProtocoloCalculo() { return protocoloCalculo; }
    public void setProtocoloCalculo(String protocoloCalculo) { this.protocoloCalculo = protocoloCalculo; }
    public Integer getAnoReferencia() { return anoReferencia; }
    public void setAnoReferencia(Integer anoReferencia) { this.anoReferencia = anoReferencia; }
    public BigDecimal getTotalPontos() { return totalPontos; }
    public void setTotalPontos(BigDecimal totalPontos) { this.totalPontos = totalPontos; }
    public LocalDateTime getDataCalculo() { return dataCalculo; }
    public void setDataCalculo(LocalDateTime dataCalculo) { this.dataCalculo = dataCalculo; }
}