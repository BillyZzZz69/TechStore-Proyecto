package com.sistematecnologico.controller;

import com.sistematecnologico.model.Usuario;
import com.sistematecnologico.service.UsuarioService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsuarioController {

    private final UsuarioService service;
    private final PasswordEncoder passwordEncoder;

    public UsuarioController(UsuarioService service, PasswordEncoder passwordEncoder) {
        this.service = service;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String login() {
        return "index";
    }

    @GetMapping("/registro")
    public String registro() {
        return "registro";
    }

    @PostMapping("/usuarios")
    public String guardarUsuario(@ModelAttribute Usuario usuario) {
        usuario.setActivo(true);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        if (usuario.getRol() == null || usuario.getRol().isEmpty()) {
            usuario.setRol("USER");
        }

        service.guardar(usuario);
        return "redirect:/";
    }

}