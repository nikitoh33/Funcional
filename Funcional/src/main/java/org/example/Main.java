package org.example;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Main {

        public static void main (String[]args){
            caso1Alumnos();
            System.out.println("\n" + "=".repeat(80) + "\n");
            caso2Productos();
            System.out.println("\n" + "=".repeat(80) + "\n");
            caso3Libros();
            System.out.println("\n" + "=".repeat(80) + "\n");
            caso4Empleados();
        }

        // ============== CASO 1: ALUMNOS ==============
        public static void caso1Alumnos () {
            System.out.println("============== CASO 1: ALUMNOS ==============\n");

            List<Alumno> alumnos = Arrays.asList(
                    new Alumno("Ana García", 8.5, "3A"),
                    new Alumno("Juan Pérez", 6.0, "3B"),
                    new Alumno("María López", 9.0, "3A"),
                    new Alumno("Carlos Ruiz", 7.5, "3B"),
                    new Alumno("Laura Torres", 5.5, "3A"),
                    new Alumno("Pedro Sánchez", 8.0, "3B"),
                    new Alumno("Sofía Martínez", 9.5, "3A"),
                    new Alumno("Diego Fernández", 7.0, "3B")
            );

            // 1. Nombres de aprobados (≥7) en mayúsculas y ordenados
            System.out.println("1. Alumnos aprobados (nota ≥ 7) en mayúsculas:");
            List<String> aprobados = alumnos.stream()
                    .filter(a -> a.getNota() >= 7)
                    .map(a -> a.getNombre().toUpperCase())
                    .sorted()
                    .collect(Collectors.toList());
            aprobados.forEach(System.out::println);

            // 2. Promedio general de notas
            System.out.println("\n2. Promedio general de notas:");
            double promedioGeneral = alumnos.stream()
                    .mapToDouble(Alumno::getNota)
                    .average()
                    .orElse(0.0);
            System.out.printf("Promedio: %.2f\n", promedioGeneral);

            // 3. Agrupar alumnos por curso
            System.out.println("\n3. Alumnos agrupados por curso:");
            Map<String, List<Alumno>> alumnosPorCurso = alumnos.stream()
                    .collect(Collectors.groupingBy(Alumno::getCurso));
            alumnosPorCurso.forEach((curso, lista) -> {
                System.out.println("Curso " + curso + ":");
                lista.forEach(a -> System.out.println("  - " + a.getNombre()));
            });

            // 4. Los 3 mejores promedios
            System.out.println("\n4. Los 3 mejores promedios:");
            alumnos.stream()
                    .sorted(Comparator.comparingDouble(Alumno::getNota).reversed())
                    .limit(3)
                    .forEach(a -> System.out.printf("  %s - Nota: %.2f\n", a.getNombre(), a.getNota()));
        }

        // ============== CASO 2: PRODUCTOS ==============
        public static void caso2Productos () {
            System.out.println("============== CASO 2: PRODUCTOS ==============\n");

            List<Producto> productos = Arrays.asList(
                    new Producto("Laptop Dell", "Electrónica", 850.00, 15),
                    new Producto("Mouse Logitech", "Electrónica", 25.50, 50),
                    new Producto("Teclado Mecánico", "Electrónica", 120.00, 30),
                    new Producto("Monitor Samsung", "Electrónica", 300.00, 20),
                    new Producto("Silla Gamer", "Muebles", 250.00, 10),
                    new Producto("Escritorio", "Muebles", 180.00, 8),
                    new Producto("Lámpara LED", "Iluminación", 45.00, 40),
                    new Producto("Webcam HD", "Electrónica", 80.00, 25)
            );

            // 1. Productos con precio > 100, ordenados por precio descendente
            System.out.println("1. Productos con precio mayor a $100 (ordenados por precio):");
            productos.stream()
                    .filter(p -> p.getPrecio() > 100)
                    .sorted(Comparator.comparingDouble(Producto::getPrecio).reversed())
                    .forEach(System.out::println);

            // 2. Agrupar por categoría y calcular stock total
            System.out.println("\n2. Stock total por categoría:");
            Map<String, Integer> stockPorCategoria = productos.stream()
                    .collect(Collectors.groupingBy(
                            Producto::getCategoria,
                            Collectors.summingInt(Producto::getStock)
                    ));
            stockPorCategoria.forEach((cat, stock) ->
                    System.out.printf("  %s: %d unidades\n", cat, stock));

            // 3. String con nombre;precio separados por ";"
            System.out.println("\n3. Productos en formato nombre;precio:");
            String productosConcatenados = productos.stream()
                    .map(p -> p.getNombre() + ";" + p.getPrecio())
                    .collect(Collectors.joining(";"));
            System.out.println(productosConcatenados);

            // 4. Precio promedio general y por categoría
            System.out.println("\n4. Precio promedio:");
            double precioPromedioGeneral = productos.stream()
                    .mapToDouble(Producto::getPrecio)
                    .average()
                    .orElse(0.0);
            System.out.printf("  General: $%.2f\n", precioPromedioGeneral);

            System.out.println("  Por categoría:");
            Map<String, Double> promedioPorCategoria = productos.stream()
                    .collect(Collectors.groupingBy(
                            Producto::getCategoria,
                            Collectors.averagingDouble(Producto::getPrecio)
                    ));
            promedioPorCategoria.forEach((cat, promedio) ->
                    System.out.printf("    %s: $%.2f\n", cat, promedio));
        }

        // ============== CASO 3: LIBROS ==============
        public static void caso3Libros () {
            System.out.println("============== CASO 3: LIBROS ==============\n");

            List<Libro> libros = Arrays.asList(
                    new Libro("Cien años de soledad", "Gabriel García Márquez", 417, 25.99),
                    new Libro("El principito", "Antoine de Saint-Exupéry", 96, 15.50),
                    new Libro("1984", "George Orwell", 328, 22.00),
                    new Libro("Don Quijote", "Miguel de Cervantes", 863, 35.00),
                    new Libro("La metamorfosis", "Franz Kafka", 201, 12.99),
                    new Libro("El proceso", "Franz Kafka", 255, 18.50),
                    new Libro("Rayuela", "Julio Cortázar", 600, 28.00),
                    new Libro("Ficciones", "Jorge Luis Borges", 174, 16.99)
            );

            // 1. Títulos de libros con más de 300 páginas, ordenados alfabéticamente
            System.out.println("1. Libros con más de 300 páginas (orden alfabético):");
            libros.stream()
                    .filter(l -> l.getPaginas() > 300)
                    .map(Libro::getTitulo)
                    .sorted()
                    .forEach(titulo -> System.out.println("  - " + titulo));

            // 2. Promedio de páginas
            System.out.println("\n2. Promedio de páginas:");
            double promedioPaginas = libros.stream()
                    .mapToInt(Libro::getPaginas)
                    .average()
                    .orElse(0.0);
            System.out.printf("  %.2f páginas\n", promedioPaginas);

            // 3. Agrupar por autor y contar libros
            System.out.println("\n3. Cantidad de libros por autor:");
            Map<String, Long> librosPorAutor = libros.stream()
                    .collect(Collectors.groupingBy(
                            Libro::getAutor,
                            Collectors.counting()
                    ));
            librosPorAutor.forEach((autor, cantidad) ->
                    System.out.printf("  %s: %d libro(s)\n", autor, cantidad));

            // 4. Libro más caro
            System.out.println("\n4. Libro más caro:");
            libros.stream()
                    .max(Comparator.comparingDouble(Libro::getPrecio))
                    .ifPresent(libro -> System.out.println("  " + libro));
        }

        // ============== CASO 4: EMPLEADOS ==============
        public static void caso4Empleados () {
            System.out.println("============== CASO 4: EMPLEADOS ==============\n");

            List<Empleado> empleados = Arrays.asList(
                    new Empleado("Roberto Gómez", "IT", 3500.00, 35),
                    new Empleado("Ana Martínez", "RRHH", 2800.00, 28),
                    new Empleado("Luis Fernández", "IT", 4200.00, 42),
                    new Empleado("Carmen López", "Ventas", 2500.00, 31),
                    new Empleado("Jorge Pérez", "IT", 3800.00, 25),
                    new Empleado("Isabel Torres", "RRHH", 3000.00, 38),
                    new Empleado("Miguel Sánchez", "Ventas", 2200.00, 23),
                    new Empleado("Patricia Ruiz", "Ventas", 2600.00, 29)
            );

            // 1. Empleados con salario > 2000, ordenados por salario descendente
            System.out.println("1. Empleados con salario mayor a $2000 (orden descendente):");
            empleados.stream()
                    .filter(e -> e.getSalario() > 2000)
                    .sorted(Comparator.comparingDouble(Empleado::getSalario).reversed())
                    .forEach(System.out::println);

            // 2. Salario promedio general
            System.out.println("\n2. Salario promedio general:");
            double salarioPromedio = empleados.stream()
                    .mapToDouble(Empleado::getSalario)
                    .average()
                    .orElse(0.0);
            System.out.printf("  $%.2f\n", salarioPromedio);

            // 3. Agrupar por departamento y sumar salarios
            System.out.println("\n3. Suma de salarios por departamento:");
            Map<String, Double> salariosPorDepartamento = empleados.stream()
                    .collect(Collectors.groupingBy(
                            Empleado::getDepartamento,
                            Collectors.summingDouble(Empleado::getSalario)
                    ));
            salariosPorDepartamento.forEach((dept, total) ->
                    System.out.printf("  %s: $%.2f\n", dept, total));

            // 4. Los 2 empleados más jóvenes
            System.out.println("\n4. Los 2 empleados más jóvenes:");
            empleados.stream()
                    .sorted(Comparator.comparingInt(Empleado::getEdad))
                    .limit(2)
                    .map(Empleado::getNombre)
                    .forEach(nombre -> System.out.println("  - " + nombre));
        }
}


