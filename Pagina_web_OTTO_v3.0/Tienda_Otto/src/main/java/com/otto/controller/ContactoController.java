package com.otto.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactoController {

    @PostMapping("/ContactoController")
    public void procesarFormularioContacto(
            @RequestParam("nombre") String nombre,
            @RequestParam("email") String email,
            @RequestParam("mensaje") String mensaje,
            HttpServletResponse response
    ) throws IOException {

        System.out.println("Mensaje de contacto recibido:");
        System.out.println("Nombre: " + nombre);
        System.out.println("Correo Electrónico: " + email);
        System.out.println("Mensaje: " + mensaje);

        response.sendRedirect("contacto.html?status=success");
    }
}
