# Manual de Usuario — Proyecto 1
## Sistema de Inventario Tienda de Ropa

**Universidad San Carlos de Guatemala**  
**Facultad de Ingeniería**  
**Introducción a la Programación y Computación 1**

---

**Estudiante:** Osmar Alejandro Alay Quevedo  
**Carné:** 202100024  
**Sección:** B  
**Fecha:** 13/03/2025

---

## 1. Introducción

Este manual describe el funcionamiento del Sistema de Inventario para Tienda de Ropa, desarrollado en Java con interfaz gráfica. El sistema permite gestionar productos, registrar ventas y generar reportes de manera sencilla a través de ventanas interactivas.

---

## 2. Requisitos Previos

Antes de ejecutar la aplicación, asegúrese de tener instalado:

- **Java JDK 8 o superior** en su computadora.
- Las carpetas **`reportes/`** y **`ventas/`** creadas en el directorio donde se ejecuta el programa. Sin estas carpetas, la generación de reportes y el registro de ventas en archivo no funcionarán correctamente.

---

## 3. Cómo Ejecutar la Aplicación

1. Abra **La terminal de su ordenador en la ubicacion del jar**.
2. Ejecute `java -jar IPCP_1.jar`.
3. Se abrirá automáticamente la ventana del **Menú Principal**.

---

## 4. Menú Principal

Al iniciar la aplicación, se muestra el menú principal con todas las opciones disponibles del sistema.

![Menú Principal](Capturas/MenuPrincipal.png)

### Opciones disponibles

| Botón | Función |
|---|---|
| **Agregar Producto** | Abre el formulario para registrar un nuevo producto |
| **Buscar Producto** | Abre el formulario para buscar productos |
| **Eliminar Producto** | Abre el formulario para eliminar un producto |
| **Registrar Venta** | Abre el formulario para registrar una venta |
| **Ver Datos de Estudiante** | Muestra el nombre y carné del estudiante |
| **Generar Reporte** | Abre el menú de generación de reportes HTML |
| **Salir** | Cierra la aplicación |

> Cada opción abre su propia ventana. Al terminar la operación, puede regresar al menú principal usando el botón **Regresar** o cerrando la ventana correspondiente.

---

## 5. Agregar Producto

Esta opción permite registrar un nuevo producto en el inventario del sistema.

![Agregar Producto](Capturas/AgregarProducto.png)

### Campos a completar

| Campo | Descripción | Restricciones |
|---|---|---|
| **Nombre** | Nombre del producto | No puede estar vacío |
| **Precio** | Precio unitario en quetzales | Debe ser un número mayor a 0 |
| **Cantidad** | Unidades disponibles en stock | Debe ser un número entero mayor o igual a 0 |
| **Codigo** | Identificador único del producto | No puede repetirse en el inventario |
| **Categoria** | Tipo de prenda | Seleccionar del menú desplegable |

### Categorías disponibles

- Camisa
- Pantalon
- Accesorio
- Otro

### Pasos para agregar un producto

1. Ingrese el **nombre** del producto en el campo correspondiente.
2. Ingrese el **precio** unitario (ejemplo: `150.00`).
3. Ingrese la **cantidad** disponible en stock (ejemplo: `20`).
4. Ingrese un **código único** para identificar el producto (ejemplo: `CAM001`).
5. Seleccione la **categoría** del producto en el menú desplegable.
6. Presione el botón **Agregar**.

### Mensajes posibles

| Mensaje | Significado |
|---|---|
| *"Producto agregado correctamente"* | El producto fue registrado exitosamente |
| *"Precio o cantidad invalidos"* | Se ingresó texto en lugar de números |
| *"Precio o cantidad no validos"* | El precio es 0 o negativo, o la cantidad es negativa |
| *"Codigo repetido o inventario lleno"* | El código ya existe o se alcanzó la capacidad máxima |

> Al agregar exitosamente, la ventana se cierra automáticamente y regresa al menú principal.

---

## 6. Buscar Producto

Esta opción permite localizar productos en el inventario usando diferentes criterios de búsqueda.

![Buscar Producto](Capturas/Buscarproducto.png)

### Criterios de búsqueda

El sistema detecta automáticamente qué tipo de búsqueda realizar según los campos que usted complete:

| ¿Qué llenar? | Tipo de búsqueda |
|---|---|
| Solo el campo **Nombre** (Código vacío, Categoría en "Todas") | Búsqueda por nombre — admite palabras parciales |
| Solo el campo **Código** (Nombre vacío, Categoría en "Todas") | Búsqueda por código exacto |
| Solo seleccionar una **Categoría** (Código y Nombre vacíos) | Búsqueda por categoría |

### Pasos para buscar un producto

**Por nombre:**
1. Deje el campo Código vacío.
2. Escriba el nombre o parte del nombre en el campo **Nombre** (ejemplo: `Polo`).
3. Asegúrese que Categoría esté en **"Todas"**.
4. Presione **BUSCAR**.

**Por código:**
1. Deje el campo Nombre vacío.
2. Escriba el código exacto en el campo **Codigo** (ejemplo: `CAM001`).
3. Asegúrese que Categoría esté en **"Todas"**.
4. Presione **BUSCAR**.

**Por categoría:**
1. Deje los campos Nombre y Código vacíos.
2. Seleccione la categoría deseada en el menú desplegable.
3. Presione **BUSCAR**.

### Mensajes posibles

| Mensaje | Significado |
|---|---|
| Ventana con datos del producto | Se encontraron productos coincidentes |
| *"Producto No encontrado"* | No existe ningún producto con ese criterio |

> Para regresar al menú principal sin buscar, presione el botón **Regresar**.

---

## 7. Eliminar Producto

Esta opción permite retirar permanentemente un producto del inventario.

![Eliminar Producto](Capturas/Eliminar.png)

### Pasos para eliminar un producto

1. Ingrese el **código** exacto del producto que desea eliminar.
2. Presione el botón **Eliminar**.
3. Si el código existe, el producto será eliminado y se mostrará un mensaje de confirmación.
4. Si el código no existe, se mostrará un mensaje de error.

### Mensajes posibles

| Mensaje | Significado |
|---|---|
| *"Producto eliminado"* | El producto fue removido del inventario exitosamente |
| *"ERROR: Producto No encontrado"* | El código ingresado no corresponde a ningún producto |

> **Atención:** La eliminación es permanente. Una vez eliminado, el producto no puede recuperarse durante la sesión actual.

> Para regresar sin eliminar, presione el botón **Regresar**.

---

## 8. Registrar Venta

Esta opción permite registrar la venta de uno o varios productos en una misma transacción.

![Registrar Venta](Capturas/RegistraVenta.png)

### Pasos para registrar una venta

1. Ingrese el **código del producto** que desea vender.
2. Ingrese la **cantidad** a vender.
3. Presione el botón **Agregar**.
   - Si el producto existe y hay stock suficiente, aparecerá en el área de descripción con su subtotal.
   - El campo de código y cantidad se limpiarán automáticamente para agregar otro producto.
4. Repita los pasos 1 a 3 por cada producto adicional que forme parte de la venta.
5. Una vez agregados todos los productos, presione **Confirmar Venta**.

### Área de descripción

A medida que agrega productos, el área de texto central muestra el detalle acumulado de la venta:

```
NOMBRE: Camisa Polo Azul | CATEGORIA: Camisa | CANTIDAD: 2 | PRECIO: 150.0 | SUBTOTAL: 300.0
NOMBRE: Cinturon Negro   | CATEGORIA: Accesorio | CANTIDAD: 1 | PRECIO: 75.0 | SUBTOTAL: 75.0

TOTAL: 375.0
```

### Mensajes posibles

| Mensaje | Significado |
|---|---|
| *"Producto no encontrado"* | El código ingresado no existe en el inventario |
| *"Cantidad invalida"* | Se ingresó texto en lugar de un número en cantidad |
| *"La cantidad solicitada supera el inventario"* | No hay suficiente stock para esa cantidad |
| *"Venta generada exitosamente"* | La venta fue registrada y el archivo TXT generado |

### Archivos generados

Al confirmar la venta, el sistema genera automáticamente un archivo `.txt` en la carpeta `ventas/` con el detalle completo de la transacción, incluyendo productos, total y fecha/hora.

> Para cancelar la venta sin registrarla, presione **Regresar/Cancelar**. Tenga en cuenta que si ya presionó "Agregar" en algún producto, el stock de ese producto ya fue reducido.

---

## 9. Generar Reportes

Esta opción permite generar reportes en formato HTML con la información del inventario y las ventas.

![Generar Reportes](Capturas/GenerarReportes.png)

### Tipos de reporte

#### Reporte de Productos (Stock)

Genera un archivo HTML con la lista completa de productos actualmente en el inventario, mostrando: código, nombre, categoría, precio y cantidad en stock.

**Pasos:**
1. Presione el botón **REPORTE DE PRODUCTOS**.
2. Si hay productos registrados, se generará el archivo y aparecerá un mensaje de confirmación.
3. El archivo se guardará en la carpeta `reportes/` con el nombre: `DD.MM.YYYY_HH.mm.ss_stock.html`.

#### Reporte de Ventas

Genera un archivo HTML con el historial de todas las ventas registradas durante la sesión actual, incluyendo descripción de productos, total y fecha/hora de cada transacción.

**Pasos:**
1. Presione el botón **REPORTE DE VENTAS**.
2. Si hay ventas registradas, se generará el archivo y aparecerá un mensaje de confirmación.
3. El archivo se guardará en la carpeta `reportes/` con el nombre: `DD.MM.YYYY_HH.mm.ss_venta.html`.

### Cómo ver el reporte generado

1. Navegue a la carpeta `reportes/` dentro del directorio del proyecto.
2. Abra el archivo `.html` generado con cualquier navegador web (Chrome, Firefox, Edge, etc.).
3. Verá la información organizada en una tabla con formato visual.

### Mensajes posibles

| Mensaje | Significado |
|---|---|
| *"Reporte HTML Generado exitosamente"* | El archivo fue creado correctamente |
| *"ERROR: No hay productos ingresados"* | No se puede generar el reporte de stock sin productos |
| *"ERROR: No hay ventas registradas"* | No se puede generar el reporte de ventas sin transacciones |

> Para regresar al menú principal, presione el botón **Regresar**.

---

## 10. Ver Datos del Estudiante

Al presionar esta opción en el menú principal, se muestra una ventana emergente con el nombre y carné del estudiante responsable del sistema.

No requiere ninguna acción adicional. Presione **Aceptar** para cerrar el mensaje y volver al menú.

---

## 11. Salir del Sistema

Para cerrar la aplicación, presione el botón **Salir** en el menú principal. Esto cerrará todas las ventanas activas del sistema.

> **Importante:** Los datos del inventario y las ventas registradas durante la sesión **no se guardan permanentemente** al cerrar la aplicación, a excepción de los archivos `.txt` de ventas y los reportes `.html` ya generados. Al volver a abrir el programa, el inventario comenzará vacío.

---

## 12. Resumen de Navegación

```
Menú Principal
    ├── Agregar Producto   → llenar campos → Agregar → regresa automáticamente
    ├── Buscar Producto    → ingresar criterio → BUSCAR → ver resultado → Regresar
    ├── Eliminar Producto  → ingresar código → Eliminar → Regresar
    ├── Registrar Venta    → agregar productos → Confirmar Venta → cierra automáticamente
    ├── Generar Reporte    → elegir tipo → genera HTML → Regresar
    ├── Ver Datos          → muestra información → Aceptar
    └── Salir              → cierra la aplicación
```

---

