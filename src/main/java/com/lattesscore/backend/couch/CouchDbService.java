package com.lattesscore.backend.couch;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.UUID;

@Service
public class CouchDbService {

    @Value("${couchdb.url}")
    private String couchUrl;

    @Value("${couchdb.database}")
    private String database;

    @Value("${couchdb.username}")
    private String username;

    @Value("${couchdb.password}")
    private String password;

    public CouchDbResponse salvarDocumento(String idLattesRef, String xmlContent) {
        RestTemplate restTemplate = new RestTemplate();
        
        DocumentoLattes doc = new DocumentoLattes();
        doc.setId("doc_" + UUID.randomUUID().toString());
        doc.setIdLattesRef(idLattesRef);
        doc.setArquivoXml(xmlContent);

        doc.setDataUpload(ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_INSTANT));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String auth = username + ":" + password;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
        headers.set("Authorization", "Basic " + encodedAuth);

        HttpEntity<DocumentoLattes> request = new HttpEntity<>(doc, headers);
        String url = couchUrl + "/" + database;

        ResponseEntity<CouchDbResponse> response = restTemplate.postForEntity(url, request, CouchDbResponse.class);
        return response.getBody();
    }
}