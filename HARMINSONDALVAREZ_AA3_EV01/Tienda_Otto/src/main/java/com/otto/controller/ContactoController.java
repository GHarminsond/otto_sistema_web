# Query: 
# ContextLines: 1

package com.otto.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ContactoController")
public class ContactoController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String mensaje = request.getParameter("mensaje");

        // Aquí puedes agregar la lógica para enviar el mensaje, por ejemplo, a través de correo electrónico
        // Por ahora, solo imprimimos los datos en la consola
        System.out.println("Mensaje de contacto recibido:");
        System.out.println("Nombre: " + nombre);
        System.out.println("Correo Electrónico: " + email);
        System.out.println("Mensaje: " + mensaje);

        // Redirigir al usuario a una página de confirmación
        response.sendRedirect("contacto.html?status=success");
    }
}