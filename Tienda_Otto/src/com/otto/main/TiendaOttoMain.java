# Query: 
# ContextLines: 1

package com.otto.main;

import com.otto.config.DatabaseConnection;
import com.otto.controller.ProductoController;
import com.otto.controller.UsuarioController;
import java.util.Scanner;

/**
 * Clase principal para ejecutar la aplicación Tienda Otto
 */
public class TiendaOttoMain {
    private static Scanner scanner = new Scanner(System.in);
    private static ProductoController productoController = new ProductoController();
    private static UsuarioController usuarioController = new UsuarioController();

    public static void main(String[] args) {
        System.out.println("==============================================");
        System.out.println("    🛍️  BIENVENIDO A TIENDA OTTO®  🛍️");
        System.out.println("==============================================");
        
        // Verificar conexión a la base de datos
        if (DatabaseConnection.getConnection() == null) {
            System.err.println("❌ Error: No se pudo conectar a la base de datos");
            System.err.println("Verifique que MySQL esté ejecutándose y que la base de datos 'tienda_otto' exista");
            return;
        }
        
        mostrarMenuPrincipal();
        
        // Cerrar conexión al finalizar
        DatabaseConnection.closeConnection();
        scanner.close();
        System.out.println("\n¡Gracias por usar Tienda Otto®! 👋");
    }

    /**
     * Muestra el menú principal de la aplicación
     */
    private static void mostrarMenuPrincipal() {
        int opcion;
        do {
            System.out.println("\n=============== MENÚ PRINCIPAL ===============");
            System.out.println("1. 📦 Gestión de Productos");
            System.out.println("2. 👥 Gestión de Usuarios");
            System.out.println("3. 📊 Ver Estadísticas");
            System.out.println("4. ℹ️  Información del Sistema");
            System.out.println("0. 🚪 Salir");
            System.out.println("==============================================");
            System.out.print("Seleccione una opción: ");
            
            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer
                
                switch (opcion) {
                    case 1:
                        productoController.mostrarMenuProductos();
                        break;
                    case 2:
                        usuarioController.mostrarMenuUsuarios();
                        break;
                    case 3:
                        mostrarEstadisticas();
                        break;
                    case 4:
                        mostrarInformacionSistema();
                        break;
                    case 0:
                        System.out.println("Cerrando aplicación...");
                        break;
                    default:
                        System.out.println("❌ Opción inválida. Por favor, seleccione una opción válida.");
                }
            } catch (Exception e) {
                System.out.println("❌ Error: Ingrese un número válido");
                scanner.nextLine(); // Limpiar buffer en caso de error
                opcion = -1; // Continuar el bucle
            }
        } while (opcion != 0);
    }

    /**
     * Muestra estadísticas básicas del sistema
     */
    private static void mostrarEstadisticas() {
        System.out.println("\n=============== ESTADÍSTICAS ===============");
        try {
            // Aquí podrías agregar lógica para mostrar estadísticas
            // Por ejemplo, contar productos, usuarios, etc.
            System.out.println("📊 Resumen del Sistema:");
            System.out.println("   • Base de datos: Conectada ✅");
            System.out.println("   • Sistema: Operativo ✅");
            System.out.println("   • Versión: 1.0.0");
            System.out.println("============================================");
        } catch (Exception e) {
            System.err.println("❌ Error al obtener estadísticas: " + e.getMessage());
        }
    }

    /**
     * Muestra información del sistema
     */
    private static void mostrarInformacionSistema() {
        System.out.println("\n============ INFORMACIÓN DEL SISTEMA ============");
        System.out.println("🏪 Tienda Otto® - Sistema de Gestión");
        System.out.println("📅 Versión: 1.0.0");
        System.out.println("👨‍💻 Desarrollado en Java");
        System.out.println("🗃️  Base de datos: MySQL");
        System.out.println("🏗️  Arquitectura: Capas (DAO, Service, Controller)");
        System.out.println("⚡ Funcionalidades:");
        System.out.println("   • Gestión completa de productos");
        System.out.println("   • Gestión completa de usuarios");
        System.out.println("   • Autenticación de usuarios");
        System.out.println("   • Validaciones de datos");
        System.out.println("   • Operaciones CRUD completas");
        System.out.println("=================================================");
    }
}
