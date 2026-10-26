package com.lattesscore.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "producao")
public class Producao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producao")
    private Long idProducao;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_pesquisador", nullable = false)
    private Pesquisador pesquisador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_qualis")
    private EstratoQualis qualis;

    @Column(length = 9)
    private String issn;

    @Column(nullable = false, length = 500)
    private String titulo;

    @Column(name = "ano_publicacao", nullable = false)
    private Integer anoPublicacao;

    @Column(name = "tipo_producao", nullable = false, length = 100)
    private String tipoProducao;





    
    public Long getIdProducao() { return idProducao; }
    public void setIdProducao(Long idProducao) { this.idProducao = idProducao; }

    public Pesquisador getPesquisador() { return pesquisador; }
    public void setPesquisador(Pesquisador pesquisador) { this.pesquisador = pesquisador; }

    public EstratoQualis getQualis() { return qualis; }
    public void setQualis(EstratoQualis qualis) { this.qualis = qualis; }

    public String getIssn() { return issn; }
    public void setIssn(String issn) { this.issn = issn; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public Integer getAnoPublicacao() { return anoPublicacao; }
    public void setAnoPublicacao(Integer anoPublicacao) { this.anoPublicacao = anoPublicacao; }

    public String getTipoProducao() { return tipoProducao; }
    public void setTipoProducao(String tipoProducao) { this.tipoProducao = tipoProducao; }
}