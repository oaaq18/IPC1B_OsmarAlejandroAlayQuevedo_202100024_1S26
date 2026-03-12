# Manual Técnico — Proyecto 1
## Sistema de Inventario Tienda de Ropa

**Universidad San Carlos de Guatemala**  
**Facultad de Ingeniería**  
**Introducción a la Programación y Computación 1**

---

**Estudiante:** Osmar Alejandro Alay Quevedo  
**Carné:** 202100024  
**Sección:** B  
**Curso:** Introducción a la Programación y Computación 1  
**Fecha:** 13/03/2025  

---

## 1. Requerimientos del Sistema

### 1.1 Requerimientos de Hardware

| Componente | Mínimo recomendado |
|---|---|
| Procesador | Intel Core i3 o equivalente |
| Memoria RAM | 2 GB |
| Espacio en disco | 200 MB libres |
| Pantalla | Resolución 1024×768 o superior |

### 1.2 Requerimientos de Software

| Software | Versión |
|---|---|
| Sistema Operativo | Windows 10 / Linux / macOS |
| Java Development Kit (JDK) | JDK 8 o superior |
| Java Runtime Environment (JRE) | JRE 8 o superior |
| IDE recomendado | NetBeans 12 o superior |

### 1.3 Estructura de carpetas requerida

Antes de ejecutar la aplicación, se deben crear manualmente las siguientes carpetas en el directorio raíz del proyecto:

```
IPC1_BS12026_P1/
├── src/
│   └── practicas/ipc1_bs12026_p1/
│       ├── IPC1_BS12026_P1.java
│       ├── Producto.java
│       ├── Inventario.java
│       ├── Venta.java
│       ├── VentanaMenuPrincipal.java
│       ├── VentanaAgregarProducto.java
│       ├── VentanaBuscarProducto.java
│       ├── VentanaEliminarProducto.java
│       ├── VentanaVenta.java
│       └── VentanaGenerarReporte.java
├── reportes/        ← carpeta para los reportes HTML generados
└── ventas/          ← carpeta para los archivos TXT de ventas
```

> **Importante:** Si las carpetas `reportes/` y `ventas/` no existen, los métodos de generación de archivos lanzarán una excepción de I/O al intentar escribir.

---

## 2. Descripción de Clases

### 2.1 Clase `Producto`

**Archivo:** `Producto.java`  
**Descripción:** Clase modelo que representa un artículo del inventario. Encapsula todos los atributos de un producto y provee métodos de acceso y modificación de stock.

#### Atributos

| Atributo | Tipo | Descripción |
|---|---|---|
| `Codigo` | `String` | Identificador único del producto |
| `Nombre` | `String` | Nombre descriptivo del producto |
| `Categoria` | `String` | Categoría a la que pertenece |
| `Precio` | `double` | Precio unitario del producto |
| `Stock` | `int` | Cantidad disponible en inventario |

#### Constructor

```java
public Producto(String Codigo, String Nombre, String Categoria, double Precio, int Stock)
```

Inicializa un producto con los datos proporcionados. Realiza validaciones básicas en consola: verifica que el código y nombre no estén vacíos, que el precio sea mayor a 0 y que el stock no sea negativo.

#### Métodos

---

**`getCodigo()`**
```java
public String getCodigo()
```
Retorna el código único del producto.

---

**`getNombre()`**
```java
public String getNombre()
```
Retorna el nombre del producto.

---

**`getCategoria()`**
```java
public String getCategoria()
```
Retorna la categoría del producto.

---

**`getPrecio()`**
```java
public double getPrecio()
```
Retorna el precio unitario del producto.

---

**`getStock()`**
```java
public int getStock()
```
Retorna la cantidad disponible en stock.

---

**`reducirStock(int cantidad)`**
```java
public void reducirStock(int cantidad)
```
Reduce el stock del producto según la cantidad vendida. Valida que la cantidad sea positiva y no supere el stock disponible.

```java
public void reducirStock(int cantidad) {
    if (cantidad <= 0) {
        System.out.println("ERROR: Cantidad Invalida");
    }
    if (cantidad > Stock) {
        System.out.println("ERROR: La cantidad supera el stock existente");
    }
    Stock = Stock - cantidad;
}
```

---

**`toString()`**
```java
@Override
public String toString()
```
Retorna una representación en texto del producto con todos sus atributos formateados.

```java
@Override
public String toString() {
    return "Código: " + Codigo +
           ", Nombre: " + Nombre +
           ", Categoría: " + Categoria +
           ", Precio: Q" + Precio +
           ", Stock: " + Stock;
}
```

---

### 2.2 Clase `Inventario`

**Archivo:** `Inventario.java`  
**Descripción:** Clase que gestiona la colección de productos del sistema mediante un arreglo estático de objetos `Producto`. Implementa todas las operaciones de gestión: agregar, buscar y eliminar.

#### Atributos

| Atributo | Tipo | Descripción |
|---|---|---|
| `Productos` | `Producto[]` | Arreglo que almacena los productos registrados |
| `Contador` | `int` | Número actual de productos en el inventario |

#### Constructor

```java
public Inventario(int capacidadMaxima)
```
Inicializa el arreglo de productos con la capacidad máxima indicada y el contador en 0.

```java
public Inventario(int capacidadMaxima) {
    Productos = new Producto[capacidadMaxima];
    Contador = 0;
}
```

#### Métodos

---

**`agregarProducto(Producto ProductoRecibido)`**
```java
public boolean agregarProducto(Producto ProductoRecibido)
```
Agrega un producto al inventario. Verifica que haya espacio disponible y que el código no esté duplicado. Retorna `true` si el producto fue agregado exitosamente, `false` en caso contrario.

```java
public boolean agregarProducto(Producto ProductoRecibido) {
    if (Contador == Productos.length) {
        return false; // inventario lleno
    }
    if (buscarPorCodigo(ProductoRecibido.getCodigo()) != null) {
        return false; // código duplicado
    }
    Productos[Contador] = ProductoRecibido;
    Contador++;
    return true;
}
```

---

**`buscarPorCodigo(String codigo)`**
```java
public Producto buscarPorCodigo(String codigo)
```
Recorre el arreglo buscando un producto cuyo código coincida (sin distinción de mayúsculas/minúsculas). Retorna el objeto `Producto` encontrado, o `null` si no existe.

```java
public Producto buscarPorCodigo(String codigo) {
    for (int i = 0; i < Contador; i++) {
        if (Productos[i].getCodigo().equalsIgnoreCase(codigo)) {
            return Productos[i];
        }
    }
    return null;
}
```

---

**`buscarPorNombre(String nombre)`**
```java
public Producto[] buscarPorNombre(String nombre)
```
Busca productos cuyo nombre contenga el texto ingresado (búsqueda parcial con `contains`). Retorna un arreglo con los productos coincidentes, o `null` si no se encontró ninguno.

```java
public Producto[] buscarPorNombre(String nombre) {
    Producto[] resultados = new Producto[Contador];
    int encontrados = 0;
    for (int i = 0; i < Contador; i++) {
        if (Productos[i].getNombre().toLowerCase().contains(nombre.toLowerCase())) {
            resultados[encontrados] = Productos[i];
            encontrados++;
        }
    }
    if (encontrados == 0) {
        return null;
    }
    return resultados;
}
```

---

**`buscarPorCategoria(String categoria)`**
```java
public Producto[] buscarPorCategoria(String categoria)
```
Busca todos los productos que pertenezcan a la categoría especificada. Retorna un arreglo con los productos coincidentes, o `null` si no hay ninguno.

```java
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
```

---

**`eliminarProducto(String codigo)`**
```java
public boolean eliminarProducto(String codigo)
```
Elimina el producto con el código indicado. Reorganiza el arreglo desplazando los elementos posteriores hacia la izquierda para mantener la continuidad del vector. Retorna `true` si fue eliminado, `false` si no se encontró.

```java
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
    return false;
}
```

---

**`reporteProductos()`**
```java
public void reporteProductos()
```
Genera un archivo HTML con la tabla completa de productos en stock. El archivo se guarda en la carpeta `reportes/` con el nombre en formato `DD.MM.YYYY_HH.mm.ss_stock.html`. También imprime los productos en consola.

---

**`getContador()`**
```java
public int getContador()
```
Retorna el número actual de productos registrados en el inventario.

---

### 2.3 Clase `Venta`

**Archivo:** `Venta.java`  
**Descripción:** Clase que representa una transacción de venta. Mantiene un registro estático de todas las ventas realizadas durante la sesión y genera automáticamente archivos `.txt` por cada venta confirmada.

#### Atributos

| Atributo | Tipo | Descripción |
|---|---|---|
| `Descripcion` | `String` | Detalle de los productos vendidos |
| `FechayHora` | `LocalDateTime` | Fecha y hora de la transacción |
| `Total` | `double` | Monto total de la venta |
| `ventas` | `Venta[]` *(static)* | Arreglo estático con todas las ventas de la sesión |
| `contadorVentas` | `int` *(static)* | Número total de ventas registradas |

#### Constructor

```java
public Venta(String descripcion, double total)
```
Crea una nueva venta, captura la fecha/hora actual, se auto-registra en el arreglo estático e invoca automáticamente la generación del archivo `.txt`.

```java
public Venta(String descripcion, double total) {
    this.Descripcion = descripcion;
    this.Total = total;
    this.FechayHora = LocalDateTime.now();
    ventas[contadorVentas] = this;
    contadorVentas++;
    GenerarTXT(descripcion, FechayHora, total);
}
```

#### Métodos

---

**`GenerarTXT(String descripcion, LocalDateTime Fecha, double Total)`**
```java
private void GenerarTXT(String descripcion, LocalDateTime Fecha, double Total)
```
Método privado que genera un archivo `.txt` en la carpeta `ventas/` con los datos de la transacción: descripción, total y fecha.

```java
private void GenerarTXT(String descripcion, LocalDateTime Fecha, double Total) {
    try {
        FileWriter archivo = new FileWriter("ventas/VENTA_" + Fecha + ".txt");
        String texto = descripcion + "\n" +
                       "Total: " + Total + "\n" +
                       "Fecha: " + Fecha;
        archivo.write(texto);
        archivo.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
```

---

**`reporteVentas()`**
```java
public static void reporteVentas()
```
Método estático que genera un archivo HTML con el historial completo de ventas de la sesión. El archivo se guarda en `reportes/` con el nombre en formato `DD.MM.YYYY_HH.mm.ss_venta.html`.

---

**`getContadorVentas()`**
```java
public static int getContadorVentas()
```
Retorna el número total de ventas registradas en la sesión actual.

---

**`getFechaHora()` / `getDescripcion()` / `getTotal()`**
```java
public LocalDateTime getFechaHora()
public String getDescripcion()
public double getTotal()
```
Métodos de acceso a los atributos de la venta.

---

### 2.4 Clase `VentanaMenuPrincipal`

**Archivo:** `VentanaMenuPrincipal.java`  
**Descripción:** Ventana principal de la aplicación. Crea la instancia compartida de `Inventario` y la pasa como parámetro a cada sub-ventana al abrirla, garantizando que todas operen sobre el mismo conjunto de datos.

#### Atributo clave

```java
private Inventario inventario;
```

#### Inicialización del inventario

```java
public VentanaMenuPrincipal() {
    inventario = new Inventario(5); // capacidad para 5 productos
    initComponents();
}
```

> **Nota:** La capacidad del inventario se define aquí. Para modificarla, cambiar el valor `5` por la capacidad deseada.

#### Métodos de acción (botones)

Cada botón del menú instancia su ventana correspondiente pasando el inventario:

```java
private void Boton_AgregarPActionPerformed(java.awt.event.ActionEvent evt) {
    VentanaAgregarProducto ventanaAgregar = new VentanaAgregarProducto(inventario);
    ventanaAgregar.setVisible(true);
}

private void Boton_BuscarPActionPerformed(java.awt.event.ActionEvent evt) {
    VentanaBuscarProducto ventanaBuscar = new VentanaBuscarProducto(inventario);
    ventanaBuscar.setVisible(true);
}

private void Boton_eliminarProductoActionPerformed(java.awt.event.ActionEvent evt) {
    VentanaEliminarProducto ventanaEliminar = new VentanaEliminarProducto(inventario);
    ventanaEliminar.setVisible(true);
}

private void Boton_RegistrarVActionPerformed(java.awt.event.ActionEvent evt) {
    VentanaVenta ventanaVender = new VentanaVenta(inventario);
    ventanaVender.setVisible(true);
}

private void Boton_GenerarRepoActionPerformed(java.awt.event.ActionEvent evt) {
    VentanaGenerarReporte ventanaReporte = new VentanaGenerarReporte(inventario);
    ventanaReporte.setVisible(true);
}

private void Boton_VerDAtosActionPerformed(java.awt.event.ActionEvent evt) {
    JOptionPane.showMessageDialog(this, "Osmar Alejandro Alay Quevedo\n 202100024");
}
```

---

### 2.5 Clase `VentanaAgregarProducto`

**Archivo:** `VentanaAgregarProducto.java`  
**Descripción:** Formulario que permite al usuario ingresar los datos de un nuevo producto. Realiza todas las validaciones antes de crear el objeto `Producto` y agregarlo al inventario.

#### Método principal — `AgregarActionPerformed`

```java
private void AgregarActionPerformed(java.awt.event.ActionEvent evt) {
    String codigo = Codigo.getText().trim();
    String nombre = Nombre.getText().trim();
    String precioTexto = Precio.getText().trim();
    String cantidadTexto = Cantidad.getText().trim();
    String categoria = (String) Categoria.getSelectedItem();
    int CantidadStock;
    double PrecioValor;

    try {
        PrecioValor = Double.parseDouble(precioTexto);
        CantidadStock = Integer.parseInt(cantidadTexto);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Precio o cantidad invalidos");
        return;
    }

    if (nombre.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Debe ingresar un nombre al producto");
        return;
    }

    if (PrecioValor <= 0 || CantidadStock < 0) {
        JOptionPane.showMessageDialog(this, "Precio o cantidad no validos");
        return;
    }

    Producto producto = new Producto(codigo, nombre, categoria, PrecioValor, CantidadStock);
    boolean agregado = inventario.agregarProducto(producto);

    if (agregado) {
        JOptionPane.showMessageDialog(this, "Producto agregado correctamente");
        dispose();
    } else {
        JOptionPane.showMessageDialog(this, "Codigo repetido o inventario lleno");
    }
}
```

**Flujo de validación:**

```
Entrada del usuario
        ↓
¿Precio y cantidad son numéricos? ──NO──→ Mostrar error y salir
        ↓ SÍ
¿Nombre vacío? ──SÍ──→ Mostrar error y salir
        ↓ NO
¿Precio > 0 y Cantidad >= 0? ──NO──→ Mostrar error y salir
        ↓ SÍ
Crear objeto Producto
        ↓
inventario.agregarProducto()
        ↓
¿Agregado? ──SÍ──→ Confirmar y cerrar ventana
          ──NO──→ Mostrar "Código repetido o inventario lleno"
```

---

### 2.6 Clase `VentanaBuscarProducto`

**Archivo:** `VentanaBuscarProducto.java`  
**Descripción:** Formulario que permite buscar productos por tres criterios: nombre, código o categoría. La lógica determina automáticamente qué tipo de búsqueda ejecutar según los campos completados.

#### Método principal — `EjecutarBusquedaActionPerformed`

```java
private void EjecutarBusquedaActionPerformed(java.awt.event.ActionEvent evt) {
    String Codigobusqueda = BuscarCodigo.getText().trim();
    String NombreBusqueda = BuscarNombre.getText().trim();
    String CategoriaBuscar = BuscarCategoria.getSelectedItem().toString();

    boolean Encontrado = false;
    String ResultadoBusqueda = "";

    // BUSCAR POR NOMBRE
    if (Codigobusqueda.isEmpty() && CategoriaBuscar.equals("Todas")) {
        Producto[] ResultadoBusquedaNombre = inventario.buscarPorNombre(NombreBusqueda);
        if (ResultadoBusquedaNombre != null) {
            Encontrado = true;
            for (int i = 0; i < ResultadoBusquedaNombre.length; i++) {
                if (ResultadoBusquedaNombre[i] != null) {
                    ResultadoBusqueda += ResultadoBusquedaNombre[i].toString() + "\n";
                }
            }
        }
    }

    // BUSCAR POR CODIGO
    if (NombreBusqueda.isEmpty() && CategoriaBuscar.equals("Todas")
            && inventario.buscarPorCodigo(Codigobusqueda) != null) {
        Encontrado = true;
        ResultadoBusqueda = inventario.buscarPorCodigo(Codigobusqueda).toString();
    }

    // BUSCAR POR CATEGORIA
    if (Codigobusqueda.isEmpty() && NombreBusqueda.isEmpty()) {
        Producto[] ResultadosBusquedaCategoria = inventario.buscarPorCategoria(CategoriaBuscar);
        if (ResultadosBusquedaCategoria != null) {
            Encontrado = true;
            for (int i = 0; i < ResultadosBusquedaCategoria.length; i++) {
                if (ResultadosBusquedaCategoria[i] != null) {
                    ResultadoBusqueda += ResultadosBusquedaCategoria[i].toString() + "\n";
                }
            }
        }
    }

    if (Encontrado) {
        JOptionPane.showMessageDialog(this, ResultadoBusqueda);
    } else {
        JOptionPane.showMessageDialog(this, "Producto No encontrado");
    }
}
```

**Tabla de criterios de búsqueda:**

| Código | Nombre | Categoría | Búsqueda ejecutada |
|---|---|---|---|
| Vacío | Con texto | "Todas" | Por nombre (parcial) |
| Con texto | Vacío | "Todas" | Por código (exacto) |
| Vacío | Vacío | Seleccionada | Por categoría |

---

### 2.7 Clase `VentanaEliminarProducto`

**Archivo:** `VentanaEliminarProducto.java`  
**Descripción:** Formulario que permite eliminar un producto ingresando su código. Verifica la existencia del producto antes de proceder con la eliminación.

#### Método principal — `BotonEliminarActionPerformed`

```java
private void BotonEliminarActionPerformed(java.awt.event.ActionEvent evt) {
    String CodigoProducto = CodigoEliminar.getText().trim();

    if (inventario.buscarPorCodigo(CodigoProducto) == null) {
        JOptionPane.showMessageDialog(this, "ERROR: Producto No encontrado");
    } else {
        inventario.eliminarProducto(CodigoProducto);
        JOptionPane.showMessageDialog(this, "Producto eliminado");
    }
}
```

---

### 2.8 Clase `VentanaVenta`

**Archivo:** `VentanaVenta.java`  
**Descripción:** Formulario para registrar ventas. Permite agregar múltiples productos a una misma transacción antes de confirmarla. Acumula la descripción y el total de la venta en variables de instancia.

#### Atributos de estado

```java
private double TotalVenta = 0;
private String Descripcion = "";
```

#### Método `BotonAgregarActionPerformed` — agregar producto a la venta

```java
private void BotonAgregarActionPerformed(java.awt.event.ActionEvent evt) {
    String Codigo = CodigoProducto.getText().trim();
    String CantidadTexto = CantidadAVender.getText().trim();
    int Cantidad;
    double Subtotal = 0;

    try {
        Cantidad = Integer.parseInt(CantidadTexto);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Cantidad invalida");
        return;
    }

    if (inventario.buscarPorCodigo(Codigo) == null) {
        JOptionPane.showMessageDialog(this, "Producto no encontrado");
        return;
    }

    Producto producto = inventario.buscarPorCodigo(Codigo);

    if (Cantidad > producto.getStock()) {
        JOptionPane.showMessageDialog(this, "La cantidad solicitada supera el inventario");
        return;
    }

    Subtotal += Cantidad * producto.getPrecio();
    Descripcion += "NOMBRE: " + producto.getNombre() +
                   " | CATEGORIA: " + producto.getCategoria() +
                   " | CANTIDAD: " + Cantidad +
                   " | PRECIO: " + producto.getPrecio() +
                   " | SUBTOTAL: " + Subtotal + "\n";

    producto.reducirStock(Cantidad);
    TotalVenta += Subtotal;

    CodigoProducto.setText("");
    CantidadAVender.setText("");
    AreaDescripcion.setText(Descripcion + "\nTOTAL: " + TotalVenta);
}
```

#### Método `BotonConfirmarVentaActionPerformed` — confirmar venta

```java
private void BotonConfirmarVentaActionPerformed(java.awt.event.ActionEvent evt) {
    Venta venta = new Venta(Descripcion, TotalVenta);
    JOptionPane.showMessageDialog(this, "Venta generada exitosamente");
    dispose();
}
```

---

### 2.9 Clase `VentanaGenerarReporte`

**Archivo:** `VentanaGenerarReporte.java`  
**Descripción:** Ventana que ofrece la generación de reportes HTML de stock y ventas. Verifica que haya datos disponibles antes de generar cada reporte.

#### Método — Reporte de Productos

```java
private void BotonReporteProductosActionPerformed(java.awt.event.ActionEvent evt) {
    if (inventario.getContador() == 0) {
        JOptionPane.showMessageDialog(this, "ERROR: No hay productos ingresados");
    } else {
        inventario.reporteProductos();
        JOptionPane.showMessageDialog(this, "Reporte HTML Generado exitosamente");
    }
}
```

#### Método — Reporte de Ventas

```java
private void BotonReporteVentasActionPerformed(java.awt.event.ActionEvent evt) {
    if (Venta.getContadorVentas() == 0) {
        JOptionPane.showMessageDialog(this, "ERROR: No hay ventas registradas");
    } else {
        Venta.reporteVentas();
        JOptionPane.showMessageDialog(this, "Reporte HTML Generado exitosamente");
    }
}
```

> **Nota técnica:** `reporteVentas()` se invoca como método estático directamente sobre la clase `Venta`, no sobre una instancia, ya que el arreglo de ventas y el contador son atributos estáticos de la clase.

---

## 3. Clase Principal

**Archivo:** `IPC1_BS12026_P1.java`  
**Descripción:** Punto de entrada de la aplicación. Lanza la ventana del menú principal en el hilo de despacho de eventos de Swing (`EventQueue`).

```java
public class IPC1_BS12026_P1 {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new VentanaMenuPrincipal().setVisible(true);
        });
    }
}
```

> El uso de `invokeLater` garantiza que la interfaz gráfica se inicialice correctamente en el hilo de eventos de AWT/Swing, evitando condiciones de carrera en la creación de componentes visuales.

---

## 4. Diagrama de Dependencias entre Clases

```
IPC1_BS12026_P1
        │
        └──→ VentanaMenuPrincipal
                    │  crea instancia única de
                    ├──→ Inventario ──→ Producto[]
                    │
                    ├──→ VentanaAgregarProducto(inventario)
                    │           └── crea Producto → inventario.agregarProducto()
                    │
                    ├──→ VentanaBuscarProducto(inventario)
                    │           └── inventario.buscarPorCodigo/Nombre/Categoria()
                    │
                    ├──→ VentanaEliminarProducto(inventario)
                    │           └── inventario.eliminarProducto()
                    │
                    ├──→ VentanaVenta(inventario)
                    │           └── crea Venta → genera TXT
                    │
                    └──→ VentanaGenerarReporte(inventario)
                                ├── inventario.reporteProductos() → HTML
                                └── Venta.reporteVentas()         → HTML
```

---

## 5. Flujo General de la Aplicación

```
Inicio
  └── main() → EventQueue.invokeLater()
        └── VentanaMenuPrincipal
              ├── [Agregar Producto]   → VentanaAgregarProducto → Inventario.agregarProducto()
              ├── [Buscar Producto]    → VentanaBuscarProducto  → Inventario.buscarPor*()
              ├── [Eliminar Producto]  → VentanaEliminarProducto→ Inventario.eliminarProducto()
              ├── [Registrar Venta]    → VentanaVenta           → new Venta() → TXT
              ├── [Generar Reporte]    → VentanaGenerarReporte  → HTML (stock / ventas)
              ├── [Ver Datos]          → JOptionPane (nombre y carné)
              └── [Salir]             → dispose()
```

---

## 6. Formato de Archivos Generados

### 6.1 Archivo de venta (`.txt`)

**Ruta:** `ventas/VENTA_<LocalDateTime>.txt`

```
NOMBRE: Camisa Polo Azul | CATEGORIA: Camisa | CANTIDAD: 2 | PRECIO: 150.0 | SUBTOTAL: 300.0

Total: 300.0
Fecha: 2025-03-13T14:35:22.123456
```

### 6.2 Reporte de Stock (`.html`)

**Ruta:** `reportes/DD.MM.YYYY_HH.mm.ss_stock.html`

Estructura HTML generada:

```html
<!DOCTYPE html>
<html>
  <head>
    <meta charset='UTF-8'>
    <title>Reporte de Productos</title>
    <style>
      body { font-family: Times New Roman; }
      h1   { color: blue; text-align: center; }
      table { width: 100%; border-collapse: collapse; }
      th   { background-color: cyan; color: blue; }
      th, td { border: 1px solid black; padding: 8px; text-align: center; }
    </style>
  </head>
  <body>
    <h1>REPORTE DE PRODUCTOS</h1>
    <h2>PRODUCTOS EN STOCK</h2>
    <table>
      <tr>
        <th>Codigo</th><th>Nombre</th><th>Categoria</th>
        <th>Precio</th><th>Stock</th>
      </tr>
      <!-- fila por cada producto -->
    </table>
    <footer>Reporte generado automáticamente</footer>
  </body>
</html>
```

### 6.3 Reporte de Ventas (`.html`)

**Ruta:** `reportes/DD.MM.YYYY_HH.mm.ss_venta.html`

```html
<table>
  <tr>
    <th>Descripcion</th><th>Total</th><th>Fecha</th>
  </tr>
  <!-- fila por cada venta registrada -->
</table>
```

---

## 7. Consideraciones Técnicas Importantes

- **La instancia de `Inventario` es única** y se crea en `VentanaMenuPrincipal`, pasándose por referencia a todas las sub-ventanas. Esto garantiza que los cambios en cualquier ventana se reflejen globalmente.
- **El arreglo de ventas es estático** (`static Venta[] ventas`), por lo que persiste durante toda la sesión independientemente de qué ventana esté activa.
- **La capacidad del inventario es fija** (definida en la construcción de `Inventario`). Para cambiarla se debe modificar el parámetro en `VentanaMenuPrincipal`.
- **Las carpetas `reportes/` y `ventas/` deben existir** antes de la primera generación de archivos. El sistema no las crea automáticamente.

---

