package com.lattesscore.backend.service;

import com.lattesscore.backend.couch.CouchDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvaliacaoService {

    @Autowired
    private CouchDbService couchDbService;

    public void processarCurriculoLattes(String idLattes, String xmlContent) {
      
        couchDbService.salvarDocumento(idLattes, xmlContent);
        
    }
}