
package practicas.ipc1_bs12026_p1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Inventario {
    private Producto[] Productos;
    private int Contador;
    
    public Inventario(int capacidadMaxima) {
        Productos = new Producto[capacidadMaxima];
        Contador = 0;
    }
    
     public boolean agregarProducto(Producto ProductoRecibido) {//p es un objeto creado para usar funciones de la clase producto

        if (Contador == Productos.length) {
            return false; // inventario lleno
        }
        //
        if (buscarPorCodigo(ProductoRecibido.getCodigo()) != null) {
            return false; // código duplicado
        }

        Productos[Contador] = ProductoRecibido;
        Contador++;

        return true;
    }
    //buscarpor codigo
    public Producto buscarPorCodigo(String codigo) {

        for (int i = 0; i < Contador; i++) {
            if (Productos[i].getCodigo().equalsIgnoreCase(codigo)) {
                return Productos[i];
            }
        }

        return null;
    }
    
     public Producto[] buscarPorNombre(String nombre) {

        Producto[] resultados = new Producto[Contador];
        int encontrados = 0;

        for (int i = 0; i < Contador; i++) {
            if (Productos[i].getNombre().equalsIgnoreCase(nombre)) {
                resultados[encontrados] = Productos[i];
                encontrados++;
            }
        }
        
        if (encontrados == 0) {
            return null;
        }

        return resultados;
    }

    //buscar por categoria
     
    public Producto[] buscarPorCategoria(String categoria) {

        Producto[] resultados = new Producto[Contador];
        int encontrados = 0;

        for (int i = 0; i < Contador; i++) {
            if (Productos[i].getCategoria().equalsIgnoreCase(categoria)) {
                resultados[encontrados] = Productos[i];
                encontrados++;
            }
        }
        if (encontrados == 0) {
            return null;
        }

        return resultados;
    }
    //reporte de todos los productos 
    public void reporteProductos() {
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd.MM.yyyy_HH.mm.ss");
        
        for (int i = 0; i < Contador; i++) {
            System.out.println(Productos[i]);//en consola 
        }
        try {
        String ProductosHTML= "reportes/"+ahora.format(formato)+"_stock.html";
        BufferedWriter writer = new BufferedWriter(new FileWriter(ProductosHTML));

        writer.write("<!DOCTYPE html>");
        writer.write("<html>");
        writer.write("<head>");
        writer.write("<meta charset='UTF-8'>");
        writer.write("<title>Reporte de Productos</title>");

        writer.write("<style>");
        writer.write("body{font-family:Times New Roman;}");
        writer.write("h1{color:blue;text-align:center;}");
        writer.write("h2{text-align:center;}");
        writer.write("table{width:100%;border-collapse:collapse;margin-top:10px;}");
        writer.write("th,td{border:1px solid black;padding:8px;text-align:center;}");
        writer.write("th{background-color:cyan;color:blue;font-size:18px;}");
        writer.write("footer{text-align:right;font-style:italic;font-size:10px;margin-top:20px;}");
        writer.write("</style>");

        writer.write("</head>");
        writer.write("<body>");

        // Título
        writer.write("<h1>REPORTE DE PRODUCTOS</h1>");

        // Subtítulo
        writer.write("<h2>PRODUCTOS EN STOCK</h2>");

        // Tabla
        writer.write("<table>");

        writer.write("<tr>");
        writer.write("<th>Codigo</th>");
        writer.write("<th>Nombre</th>");
        writer.write("<th>Categoria</th>");
        writer.write("<th>Precio</th>");
        writer.write("<th>Stock</th>");
        writer.write("</tr>");

        for (int i = 0; i < Contador; i++) {

            

                writer.write("<tr>");
                writer.write("<td>" + Productos[i].getCodigo() + "</td>");
                writer.write("<td>" + Productos[i].getNombre() + "</td>");
                writer.write("<td>" + Productos[i].getCategoria() + "</td>");
                writer.write("<td>" + Productos[i].getPrecio() + "</td>");
                writer.write("<td>" + Productos[i].getStock() + "</td>");
                writer.write("</tr>");
        }

        writer.write("</table>");

        // Pie de página
        writer.write("<footer>Reporte generado automáticamente</footer>");

        writer.write("</body>");
        writer.write("</html>");

        writer.close();

        System.out.println("Reporte HTML generado exitosamente");

       } catch (Exception e) {
        e.printStackTrace();
       }
        
    }

    public int getContador() {
        return Contador;
    }
    


    //eliminar producto
    public boolean eliminarProducto(String codigo) {

        for (int i = 0; i < Contador; i++) {

            if (Productos[i].getCodigo().equalsIgnoreCase(codigo)) {
                
                for (int j = i; j < Contador - 1; j++) {
                    Productos[j] = Productos[j + 1];
                }
                
                Productos[Contador - 1] = null;
                Contador--;

                return true;
            }
        }

        return false; // no encontrado
    }
     
    
}
