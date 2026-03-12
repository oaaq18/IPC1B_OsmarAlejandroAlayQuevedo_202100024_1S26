
package practicas.ipc1_bs12026_p1;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Venta {

    private String Descripcion;
    private LocalDateTime FechayHora;
    private double Total;
    
    private static Venta[] ventas = new Venta[100];
    private static int contadorVentas = 0;
    
    // Constructor
    public Venta(String descripcion, double total) {
        this.Descripcion = descripcion;
        this.Total = total;
        this.FechayHora = LocalDateTime.now();
        ventas[contadorVentas] = this;
        contadorVentas++;
        GenerarTXT(descripcion, FechayHora, total);
        
    }
    
    private void GenerarTXT(String descripcion, LocalDateTime Fecha, double Total){
        try {
            FileWriter archivo = new FileWriter("ventas/VENTA_" + Fecha + ".txt");
            String texto = descripcion+"\n"+
                           "Total: "+Total+"\n"+
                           "Fecha: "+Fecha;
            archivo.write(texto);
            archivo.close();
            

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LocalDateTime getFechaHora() {
        return FechayHora;
    }

    public String getDescripcion() {
        return Descripcion;
    }
    

    public static int getContadorVentas() {
        return contadorVentas;
    }
    
    
    public double getTotal() {
        return Total;
    }
    public static void reporteVentas() {
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd.MM.yyyy_HH.mm.ss");
        
        for (int i = 0; i < contadorVentas; i++) {
            System.out.println(ventas[i]);//en consola 
        }
        try {
        String ProductosHTML= "reportes/"+ahora.format(formato)+"_venta.html";
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
        writer.write("<h1>REPORTE DE VENTAS</h1>");

        // Tabla
        writer.write("<table>");
        writer.write("<tr>");
        writer.write("<th>Descripcion</th>");
        writer.write("<th>Total</th>");
        writer.write("<th>Fecha</th>");
        writer.write("</tr>");

        for (int i = 0; i < contadorVentas; i++) {
                writer.write("<tr>");
                writer.write("<td>" + ventas[i].getDescripcion() + "</td>");
                writer.write("<td>Q" + ventas[i].getTotal() + "</td>");
                writer.write("<td>" + ventas[i].getFechaHora() + "</td>");
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
    
    @Override
    public String toString() {
        return  Descripcion + "\n"+
               ", Total: Q" + Total +
               ", Fecha: " + FechayHora;
    }
}

