package com.lattesscore.backend.controller;

import com.lattesscore.backend.entity.Usuario;
import com.lattesscore.backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registar")
    public ResponseEntity<Usuario> registar(@RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioService.registarUsuario(usuario);
        return ResponseEntity.ok(novoUsuario);
    }
}