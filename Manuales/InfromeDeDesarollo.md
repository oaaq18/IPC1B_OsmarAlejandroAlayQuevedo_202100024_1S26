# Informe de Desarrollo — Proyecto 1
## Sistema de Inventario Tienda de Ropa

**Universidad San Carlos de Guatemala**  
**Facultad de Ingeniería**  
**Introducción a la Programación y Computación 1**

---

**Estudiante:** Osmar Alejandro Alay Quevedo  
**Carné:** 202100024  
**Sección:** B  
**Fecha de entrega:** 13/03/2025  

---

## 1. Descripción General del Proyecto

El proyecto consiste en una aplicación desarrollada en Java utilizando interfaz grafica (NetBeans GUI Builder), que funciona como sistema de gestión de inventario para una tienda de ropa. El sistema permite registrar productos, gestionarlos, registrar ventas y generar reportes en formato HTML, manteniendo el control del stock en todo momento.

La aplicación se estructura mediante ventanas (JFrames) que representan cada módulo funcional del sistema, todas compartiendo una instancia única del inventario para garantizar la consistencia de los datos durante la ejecución.

---

## 2. Implementación del Sistema

### 2.1 Estructura General

El proyecto se compone de las siguientes clases:

| Clase | Descripción |
|---|---|
| `IPC1_BS12026_P1` | Clase principal, punto de entrada de la aplicación |
| `Producto` | Clase modelo que representa un producto del inventario |
| `Inventario` | Clase que gestiona la colección de productos mediante un arreglo |
| `Venta` | Clase que registra y gestiona las transacciones de venta |
| `VentanaMenuPrincipal` | Ventana principal con el menú de navegación |
| `VentanaAgregarProducto` | Ventana para registrar nuevos productos |
| `VentanaBuscarProducto` | Ventana para buscar productos por criterios |
| `VentanaEliminarProducto` | Ventana para eliminar productos del inventario |
| `VentanaVenta` | Ventana para registrar ventas |
| `VentanaGenerarReporte` | Ventana para generar reportes HTML |

### 2.2 Estructuras de Datos Utilizadas

se utilizaron exclusivamente **arreglos (vectores)** para el almacenamiento de datos.

- `Producto[] Productos` en la clase `Inventario`: almacena todos los productos registrados.
- `Venta[] ventas` en la clase `Venta`: almacena el historial de ventas (capacidad de 100 registros).
- `Producto[] resultados` en los métodos de búsqueda: arreglos temporales para devolver resultados de búsqueda.

---

## 3. Descripción de Módulos Funcionales
### 3.1 Menú Principal

El menú principal funciona como punto central de navegación. Cada botón abre la ventana correspondiente pasando la instancia compartida de `Inventario`. Al cerrar cualquier sub-ventana con "Regresar", el menú principal permanece activo.

**Captura de pantalla — Menú Principal:**

![Menú Principal](Capturas/MenuPrincipal.png)

---

### 3.2 Agregar Producto

El módulo permite registrar un nuevo producto ingresando: nombre, categoría (mediante ComboBox), precio, cantidad en stock y código único.

**Validaciones implementadas:**
- Verificación de campos vacíos (nombre, código).
- Conversión y validación numérica de precio y cantidad mediante `try-catch` con `NumberFormatException`.
- Precio debe ser mayor a 0 y cantidad no puede ser negativa.
- Verificación de código único antes de almacenar, mediante `buscarPorCodigo()`.
- Control de capacidad máxima del inventario.

**Captura de pantalla — Ventana Agregar Producto:**

![Ventana Agregar Producto](Capturas/AgregarProducto.png)


---

### 3.3 Buscar Producto

El módulo permite buscar productos por tres criterios distintos: nombre, código o categoría.

**Lógica de búsqueda implementada:**
- **Por nombre:** si el campo código está vacío y la categoría es "Todas", se invoca `buscarPorNombre()` usando `contains()` para coincidencia parcial.
- **Por código:** si el campo nombre está vacío y la categoría es "Todas", se invoca `buscarPorCodigo()` con comparación exacta (`equalsIgnoreCase`).
- **Por categoría:** si código y nombre están vacíos, se invoca `buscarPorCategoria()` con el valor seleccionado del ComboBox.

Los resultados se muestran en un `JOptionPane` con los datos principales del producto.

**Captura de pantalla — Ventana Buscar Producto:**

![Ventana Buscar Producto](Capturas/Buscarproducto.png)

---

### 3.4 Eliminar Producto

El módulo permite eliminar un producto del inventario mediante su código único.

**Proceso:**
1. Se verifica que el código ingresado corresponda a un producto existente.
2. Se solicita confirmación implícita mostrando el resultado antes de eliminar.
3. Se invoca `eliminarProducto()` que reorganiza el arreglo desplazando los elementos posteriores hacia la izquierda para no dejar espacios vacíos en el vector.

**Captura de pantalla — Ventana Eliminar Producto:**

![Ventana Eliminar Producto](Capturas/Eliminar.png)

---

### 3.5 Registrar Venta

El módulo permite agregar uno o varios productos a una venta antes de confirmarla.
**Proceso:**
1. Se ingresa el código del producto y la cantidad a vender.
2. Se valida que el producto exista y que el stock sea suficiente.
3. Al presionar "Agregar", se calcula el subtotal (`cantidad × precio`) y se acumula en el total de la venta.
4. La descripción de la venta se construye línea a línea y se muestra en un `JTextArea`.
5. Al confirmar, se crea un objeto `Venta` que registra la descripción, total y fecha/hora, y genera automáticamente un archivo `.txt` con los datos de la transacción.
6. El stock del producto se reduce automáticamente mediante `reducirStock()`.

**Captura de pantalla — Ventana Registrar Venta:**

![Ventana Registrar Venta - agregar producto](Caputas/RegistraVenta.png)


---

### 3.6 Generación de Reportes

El módulo ofrece dos tipos de reporte en formato HTML:

**Reporte de Stock:**  
Genera un archivo HTML con todos los productos actualmente en el inventario, mostrando: código, nombre, categoría, precio y cantidad disponible.  
El archivo se nombra con el formato: `DD.MM.YYYY_HH.mm.ss_stock.html` y se guarda en la carpeta `reportes/`.

**Reporte de Ventas:**  
Genera un archivo HTML con el historial completo de ventas registradas durante la sesión, incluyendo descripción, total y fecha/hora de cada transacción.  
El archivo se nombra con el formato: `DD.MM.YYYY_HH.mm.ss_venta.html` y se guarda en la carpeta `reportes/`.

Ambos reportes incluyen estilos CSS embebidos para una presentación tabular clara.

**Captura de pantalla — Ventana Generar Reporte:**

![Ventana Generar Reporte](Capturas/GenerarReporte.png)




---

---

## 4. Problemas Encontrados y Soluciones 

### Problema 1: Búsqueda retornaba resultados incorrectos por lógica de condiciones

**Descripción:** En el método `EjecutarBusquedaActionPerformed` de `VentanaBuscarProducto`, las condiciones de los tres tipos de búsqueda (nombre, código, categoría) se solapaban entre sí, provocando que en algunos casos se ejecutaran múltiples búsquedas simultáneamente o ninguna.

**Solución:** Se revisó y ajustó la lógica de cada bloque condicional para que fueran mutuamente excluyentes según los campos que el usuario haya llenado, garantizando que solo se active el tipo de búsqueda correspondiente.

---

### Problema 2: Búsqueda por nombre no encontraba coincidencias parciales

**Descripción:** El método `buscarPorNombre()` usaba `equalsIgnoreCase()`, lo que requería que el usuario ingresara el nombre exacto del producto. Esto hacía la búsqueda poco práctica.

**Solución:** Se cambió la comparación a `contains()` (con conversión previa a minúsculas) para permitir búsquedas por coincidencia parcial, mejorando la experiencia del usuario.

---

### Problema 3: `NullPointerException` al llamar `reporteVentas()` sobre instancia nula

**Descripción:** En `VentanaGenerarReporte`, el atributo `venta` era declarado pero nunca inicializado (`private Venta venta;`), por lo que al llamar `venta.reporteVentas()` se producía un `NullPointerException`.

**Solución:** Como `reporteVentas()` es un método estático de la clase `Venta`, se cambió la llamada para invocarla directamente sobre la clase: `Venta.reporteVentas()`, eliminando la dependencia de la instancia nula.

---

### Problema 4: Capacidad fija del inventario

**Descripción:** El arreglo `Producto[]` se inicializa con una capacidad fija definida al crear el `Inventario`. Si se llena, no es posible agregar más productos.

**Solución:** Se implementó validación que informa al usuario cuando el inventario está lleno mediante un `JOptionPane`, de modo que el sistema no falle silenciosamente.

---

### Problema 5: Archivos de reportes y ventas requieren carpetas previas

**Descripción:** Los métodos de generación de archivos (`reporteProductos`, `GenerarTXT`, `reporteVentas`) asumen que las carpetas `reportes/` y `ventas/` existen. Si no existen, se produce una excepción de I/O.

**Solución:** Se recomienda crear estas carpetas manualmente antes de ejecutar la aplicación. Como mejora futura, se puede agregar código que cree las carpetas automáticamente.

---

### Problema 6: Error al ejecutar el archivo JAR

**Descripción:** El jar lanzaba el erro: `no main manifest atribute`

**Solución:** Se modifico el main desde netbeans.

--- 

## 5. Ejemplos de Entradas y Salidas

### Ejemplo 1: Agregar Producto

**Entrada:**
```
Código:    01
Nombre:    Camisa Polo Azul
Categoría: Camisa
Precio:    150.00
Cantidad:  20
```

**Salida esperada:**
```
JOptionPane → "Producto agregado correctamente"
```

---

### Ejemplo 2: Buscar por Nombre

**Entrada:**
```
Nombre: Polo
(Código y Categoría vacíos / "Todas")
```

**Salida esperada:**
```
Código: 01, Nombre: Camisa Polo Azul, Categoría: Camisa, Precio: Q150.0, Stock: 20
```

---

### Ejemplo 3: Registrar Venta

**Entrada:**
```
Código producto: 01
Cantidad:        3
→ Agregar
→ Confirmar Venta
```

**Salida esperada en área de descripción:**
```
NOMBRE: Camisa Polo Azul | CATEGORIA: Camisa | CANTIDAD: 3 | PRECIO: 150.0 | SUBTOTAL: 450.0
TOTAL: 450.0
```

**Archivos generados:**
- `ventas/VENTA_<fecha>.txt`

---


## 6. Conclusiones

- Se logró implementar un sistema funcional de gestión de inventario en Java utilizando interfaz gráfica.
- El uso de arreglos (vectores) como estructura de datos principal permitió cumplir con los requerimientos del proyecto.
- La arquitectura de clases separadas (`Producto`, `Inventario`, `Venta`) facilitó la organización del código y el uso de POO.
- Se identificaron y corrigieron múltiples errores durante el desarrollo, fortaleciendo las habilidades de depuración y lógica de programación.

---

