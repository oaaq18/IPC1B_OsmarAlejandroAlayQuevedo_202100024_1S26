
package practicas.ipc1_bs12026_p1;

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

    //eliminar producto
    public boolean eliminarProducto(String codigo) {

        for (int i = 0; i < Contador; i++) {

            if (Productos[i].getCodigo().equalsIgnoreCase(codigo)) {
                /*
                for (int j = i; j < contador - 1; j++) {
                    productos[j] = productos[j + 1];
                }
                */
                Productos[Contador - 1] = null;
                Contador--;

                return true;
            }
        }

        return false; // no encontrado
    }
     
    
}
