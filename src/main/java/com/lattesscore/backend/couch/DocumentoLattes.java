package com.lattesscore.backend.couch;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DocumentoLattes {

    @JsonProperty("_id")
    private String id;
    
    private String tipo = "documento_lattes";
    
    @JsonProperty("id_lattes_ref")
    private String idLattesRef;
    
    @JsonProperty("data_upload")
    private String dataUpload;
    
    @JsonProperty("arquivo_xml")
    private String arquivoXml;

    



    
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getTipo() { return tipo; }
    
    public String getIdLattesRef() { return idLattesRef; }
    public void setIdLattesRef(String idLattesRef) { this.idLattesRef = idLattesRef; }
    
    public String getDataUpload() { return dataUpload; }
    public void setDataUpload(String dataUpload) { this.dataUpload = dataUpload; }
    
    public String getArquivoXml() { return arquivoXml; }
    public void setArquivoXml(String arquivoXml) { this.arquivoXml = arquivoXml; }
}