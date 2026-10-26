package com.lattesscore.backend.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "item_pontuacao", uniqueConstraints = {
    @UniqueConstraint(name = "uk_item_pontuacao_producao_regra", columnNames = {"fk_pontuacao", "fk_producao", "fk_regra"})
})
public class ItemPontuacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item")
    private Long idItem;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_pontuacao", nullable = false)
    private Pontuacao pontuacao;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_producao", nullable = false)
    private Producao producao;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_regra", nullable = false)
    private RegraBarema regra;

    @Column(name = "pontos_obtidos", nullable = false, precision = 10, scale = 2)
    private BigDecimal pontosObtidos = BigDecimal.ZERO;

    @Column(name = "criterio_aplicado", nullable = false, length = 255)
    private String criterioAplicado;

    


    
    public Long getIdItem() { return idItem; }
    public void setIdItem(Long idItem) { this.idItem = idItem; }
    public Pontuacao getPontuacao() { return pontuacao; }
    public void setPontuacao(Pontuacao pontuacao) { this.pontuacao = pontuacao; }
    public Producao getProducao() { return producao; }
    public void setProducao(Producao producao) { this.producao = producao; }
    public RegraBarema getRegra() { return regra; }
    public void setRegra(RegraBarema regra) { this.regra = regra; }
    public BigDecimal getPontosObtidos() { return pontosObtidos; }
    public void setPontosObtidos(BigDecimal pontosObtidos) { this.pontosObtidos = pontosObtidos; }
    public String getCriterioAplicado() { return criterioAplicado; }
    public void setCriterioAplicado(String criterioAplicado) { this.criterioAplicado = criterioAplicado; }
}