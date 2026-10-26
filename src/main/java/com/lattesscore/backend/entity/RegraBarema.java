package com.lattesscore.backend.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "regra_barema", uniqueConstraints = {
    @UniqueConstraint(name = "uk_regra_codigo", columnNames = {"fk_barema", "codigo_regra"})
})
public class RegraBarema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_regra")
    private Long idRegra;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_barema", nullable = false)
    private Barema barema;

    @Column(name = "codigo_regra", nullable = false, length = 50)
    private String codigoRegra;

    @Column(name = "tipo_producao", nullable = false, length = 100)
    private String tipoProducao;

    @Column(name = "estrato_alvo", length = 20)
    private String estratoAlvo;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal pontos;

    


    
    public Long getIdRegra() { return idRegra; }
    public void setIdRegra(Long idRegra) { this.idRegra = idRegra; }
    public Barema getBarema() { return barema; }
    public void setBarema(Barema barema) { this.barema = barema; }
    public String getCodigoRegra() { return codigoRegra; }
    public void setCodigoRegra(String codigoRegra) { this.codigoRegra = codigoRegra; }
    public String getTipoProducao() { return tipoProducao; }
    public void setTipoProducao(String tipoProducao) { this.tipoProducao = tipoProducao; }
    public String getEstratoAlvo() { return estratoAlvo; }
    public void setEstratoAlvo(String estratoAlvo) { this.estratoAlvo = estratoAlvo; }
    public BigDecimal getPontos() { return pontos; }
    public void setPontos(BigDecimal pontos) { this.pontos = pontos; }
}