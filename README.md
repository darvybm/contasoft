<p align="center">
  <img src="https://github.com/user-attachments/assets/cf5a7217-a4a3-460c-8e5e-125ecaccbcac" alt="Header Image" width="400"/>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Language-Java-blue" alt="Java Badge"/>
  <img src="https://img.shields.io/badge/Framework-Javalin-brightgreen" alt="Javalin Badge"/>
  <img src="https://img.shields.io/badge/Frontend-Bootstrap-lightgrey" alt="Bootstrap Badge"/>
  <img src="https://img.shields.io/badge/Build%20Tool-Gradle-orange" alt="Gradle Badge"/>
</p>

# ContaSoft

**ContaSoft** es un sistema de contabilidad basado en la web diseñado para simular el proceso completo de contabilidad en un entorno educativo. La aplicación permite a los usuarios gestionar empresas ficticias, ciclos contables y realizar operaciones contables esenciales como la creación de asientos, visualización de libros mayores, y generación de estados financieros.

La aplicación ofrece una interfaz intuitiva que facilita la administración de datos contables y permite la práctica de procesos contables en un entorno controlado. Desarrollada en Java con el framework Javalin y Freemarker para el motor de plantillas, utiliza HTML, CSS, JavaScript y Bootstrap para el frontend, proporcionando una experiencia de usuario fluida y moderna.

## Índice

1. [Features](#features)
2. [Application](#application)
3. [Tools Used](#tools-used)
4. [How to Install](#how-to-install)
5. [Contributors](#contributors)
6. [License](#license)
7. [Contact Me](#contact-me)

Claro, aquí tienes los títulos de las funcionalidades con emojis al inicio:

## Features

### 🏢 Registro de Empresas
Permite a los usuarios registrar empresas ficticias dentro del sistema. Cada empresa puede tener su propia configuración y ciclos contables, facilitando la gestión y organización de datos contables para diferentes entidades.

### 📅 Creación de Ciclos Contables
Los usuarios pueden crear ciclos contables para cada empresa registrada. Estos ciclos son fundamentales para la gestión de asientos contables y la generación de informes financieros.

### 💳 Gestión de Asientos y Transacciones
Los usuarios pueden agregar asientos contables con información detallada sobre transacciones financieras. Esto incluye la entrada de débitos y créditos con fechas y descripciones adecuadas, lo cual es crucial para el registro preciso de todas las operaciones contables.

### 📓 Visualización del Libro Diario
El libro diario muestra una lista detallada de todos los asientos contables registrados en un ciclo contable específico. La vista permite a los usuarios revisar y verificar todas las transacciones en un formato cronológico.

### 📈 Visualización del Libro Mayor
El libro mayor presenta un desglose detallado de todas las cuentas en formato T para cada ciclo contable. Esta vista permite a los usuarios revisar el saldo y los movimientos de cada cuenta individualmente.

### 📊 Balanza de Comprobación
La balanza de comprobación ofrece una visión general del equilibrio entre débitos y créditos en un ciclo contable. Esta vista incluye un desglose detallado de las cuentas, ayudando a verificar la exactitud de los registros contables.

### 📑 Generación de Estados Financieros
La aplicación permite generar varios estados financieros esenciales, incluyendo:
- **Estado de Resultados:** Muestra los ingresos y gastos para calcular el beneficio neto o pérdida.
- **Estado de Cambios en el Capital:** Detalla los cambios en el capital de la empresa durante un período específico.
- **Estado de Situación (Balance General):** Proporciona una visión general de los activos, pasivos y patrimonio neto de la empresa.
- **Estado de Flujo de Efectivo:** Muestra los flujos de efectivo provenientes de las actividades operativas, de inversión y de financiación.

### 📚 Gestión de Catálogo de Cuentas
El catálogo de cuentas permite a los usuarios agregar nuevas cuentas en categorías como Activo, Pasivo, Gasto e Ingreso. Aquí se presentan las cuentas por defecto que se agregan automáticamente al sistema:

#### Activos
<table>
  <tr>
    <th>Código</th>
    <th>Nombre</th>
  </tr>
  <tr>
    <td>111</td>
    <td>Efectivo</td>
  </tr>
  <tr>
    <td>112</td>
    <td>Cuentas por Cobrar</td>
  </tr>
  <tr>
    <td>114</td>
    <td>Insumos</td>
  </tr>
  <tr>
    <td>115</td>
    <td>Materiales de Oficina</td>
  </tr>
  <tr>
    <td>121</td>
    <td>Terreno</td>
  </tr>
  <tr>
    <td>123</td>
    <td>Mobiliarios y Equipos</td>
  </tr>
</table>

#### Pasivos
<table>
  <tr>
    <th>Código</th>
    <th>Nombre</th>
  </tr>
  <tr>
    <td>211</td>
    <td>Documentos por pagar</td>
  </tr>
  <tr>
    <td>212</td>
    <td>Cuentas por pagar</td>
  </tr>
</table>

#### Ingresos
<table>
  <tr>
    <th>Código</th>
    <th>Nombre</th>
  </tr>
  <tr>
    <td>411</td>
    <td>Ingresos por Servicios</td>
  </tr>
</table>

#### Gastos
<table>
  <tr>
    <th>Código</th>
    <th>Nombre</th>
  </tr>
  <tr>
    <td>612.01</td>
    <td>Gastos por Sueldos</td>
  </tr>
  <tr>
    <td>612.05</td>
    <td>Gastos por Renta</td>
  </tr>
  <tr>
    <td>612.07</td>
    <td>Gastos por Telefonos</td>
  </tr>
  <tr>
    <td>612.1</td>
    <td>Gastos por Energía Eléctrica</td>
  </tr>
</table>

#### Capital
<table>
  <tr>
    <th>Código</th>
    <th>Nombre</th>
  </tr>
  <tr>
    <td>311</td>
    <td>Capital</td>
  </tr>
</table>

#### Retiros
<table>
  <tr>
    <th>Código</th>
    <th>Nombre</th>
  </tr>
  <tr>
    <td>312</td>
    <td>Retiros</td>
  </tr>
</table>

## Application

Aquí tienes algunas capturas de pantalla y vistas de la aplicación en acción (Las vistas más importantes):

**Inicio de Sesión y Registro**
<p align="left">
  <img src="https://github.com/user-attachments/assets/65d65201-286b-41c9-babe-25cdeb970b22" alt="Login"/>
</p>

**Lista de Ciclos Contables**
<p align="left">
  <img src="https://github.com/user-attachments/assets/28277a76-6b74-4014-823b-3a91bf2af46a" alt="Accounting Cycles"/>
</p>

**Vista del Libro Diario**
<p align="left">
  <img src="https://github.com/user-attachments/assets/d4bde41d-647f-4cb2-a581-d1d0729e870d" alt="Journal View"/>
</p>

**Vista del Libro Mayor**
<p align="left">
  <img src="https://github.com/user-attachments/assets/9252cf3e-0437-4c5b-8afa-f04a96d23bb4" alt="General Ledger View"/>
</p>

**Vista de la Balanza de Comprobación**
<p align="left">
  <img src="https://github.com/user-attachments/assets/51119d03-2dcb-4fc3-b7f1-648ebc9c1065" alt="Trial Balance View"/>
</p>

**Vista de Estados Financieros**
<p align="left">
  <img src="https://github.com/user-attachments/assets/4a9e7629-58f1-4752-9e64-f1e34290e834" alt="Financial Statements View"/>
</p>

**Vista de Catálogos de Cuentas**
<p align="left">
  <img src="https://github.com/user-attachments/assets/f25767d2-54a5-4c52-9748-8c817c3086a1" alt="Accounts Catalog View"/>
</p>


## Tools Used
- **Language:** Java ![Java](https://img.shields.io/badge/Language-Java-blue)
- **Frameworks:** Javalin ![Javalin](https://img.shields.io/badge/Framework-Javalin-brightgreen)
- **Frontend:** HTML, CSS, JavaScript, Bootstrap, Freemarker ![HTML](https://img.shields.io/badge/HTML5-E34F26?style=flat&logo=html5&logoColor=white) ![CSS](https://img.shields.io/badge/CSS3-1572B6?style=flat&logo=css3&logoColor=white) ![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=flat&logo=javascript&logoColor=black) ![Bootstrap](https://img.shields.io/badge/Bootstrap-563D7C?style=flat&logo=bootstrap&logoColor=white) ![Freemarker](https://img.shields.io/badge/Template%20Engine-Freemarker-003000?style=flat&logo=freemarker&logoColor=white)
- **Build Tool:** Gradle ![Gradle](https://img.shields.io/badge/Build%20Tool-Gradle-02303A?style=flat&logo=gradle&logoColor=white)

## How to Install
To run the application, follow these steps:

1. Clone this repository:
    ```bash
    git clone https://github.com/your-username/contasoft.git
    ```
2. **Navigate into the project directory:**
    ```bash
    cd contasoft
    ```
3. Configure the variables in `application-local.properties` if necessary.
4. Run the project using your preferred IDE or Gradle:
    ```bash
    ./gradlew run
    ```

## Contributors
Here are the contributors to this project:

<table>
  <tr>
    <td align="center">
      <a href="https://github.com/your-username">
        <img src="https://github.com/darvybm.png" width="100px;" alt="Your Name"/>
        <br />
        <sub><b>Your Name</b></sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/your-username">
        <img src="https://github.com/MarcosBlancoPUCMM.png" width="100px;" alt="Your Name"/>
        <br />
        <sub><b>Your Name</b></sub>
      </a>
    </td>
  </tr>
</table>

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact Me

<p align="center">
  <a href="https://www.linkedin.com/in/darvybm" target="_blank">
    <img src="https://img.shields.io/badge/LinkedIn-@darvybm-blue?style=flat&logo=linkedin&logoColor=white" alt="LinkedIn Badge"/>
  </a>
  <a href="mailto:darvybm@gmail.com" target="_blank">
    <img src="https://img.shields.io/badge/Email-Contact%20Me-orange" alt="Email Badge"/>
  </a>
</p>
