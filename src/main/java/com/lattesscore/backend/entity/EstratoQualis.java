package com.lattesscore.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "estrato_qualis", uniqueConstraints = {
    @UniqueConstraint(name = "uk_qualis_issn_area_periodo", columnNames = {"issn", "area_avaliacao", "periodo_qualis"})
})
public class EstratoQualis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_qualis")
    private Long idQualis;

    @Column(nullable = false, length = 9)
    private String issn;

    @Column(name = "titulo_periodico", nullable = false, length = 255)
    private String tituloPeriodico;

    @Column(nullable = false, length = 10)
    private String estrato;

    @Column(name = "area_avaliacao", nullable = false, length = 150)
    private String areaAvaliacao;

    @Column(name = "periodo_qualis", nullable = false, length = 30)
    private String periodoQualis;




    
    public Long getIdQualis() { return idQualis; }
    public void setIdQualis(Long idQualis) { this.idQualis = idQualis; }

    public String getIssn() { return issn; }
    public void setIssn(String issn) { this.issn = issn; }

    public String getTituloPeriodico() { return tituloPeriodico; }
    public void setTituloPeriodico(String tituloPeriodico) { this.tituloPeriodico = tituloPeriodico; }

    public String getEstrato() { return estrato; }
    public void setEstrato(String estrato) { this.estrato = estrato; }

    public String getAreaAvaliacao() { return areaAvaliacao; }
    public void setAreaAvaliacao(String areaAvaliacao) { this.areaAvaliacao = areaAvaliacao; }

    public String getPeriodoQualis() { return periodoQualis; }
    public void setPeriodoQualis(String periodoQualis) { this.periodoQualis = periodoQualis; }
}