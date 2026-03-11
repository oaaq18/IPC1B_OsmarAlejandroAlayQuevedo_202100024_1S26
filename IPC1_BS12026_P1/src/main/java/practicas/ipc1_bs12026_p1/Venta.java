
package practicas.ipc1_bs12026_p1;
import java.time.LocalDateTime;

public class Venta {

    private String Descripcion;
    private LocalDateTime FechayHora;
    private double Total;

    // Constructor
    public Venta(String descripcion, double total) {
        this.Descripcion = descripcion;
        this.Total = total;
        this.FechayHora = LocalDateTime.now();
    }

    // Getters
    public String getCodigoProducto() {
        return Descripcion;
    }

    

    public LocalDateTime getFechaHora() {
        return FechayHora;
    }

    public double getTotal() {
        return Total;
    }
    
    @Override
    public String toString() {
        return  Descripcion + "\n"+
               ", Total: Q" + Total +
               ", Fecha: " + FechayHora;
    }
}

