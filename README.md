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

**ContaSoft** is a web-based accounting system designed to simulate the complete accounting process in an educational environment. The application allows users to manage fictional companies, accounting cycles, and perform essential accounting operations such as creating entries, viewing ledgers, and generating financial statements. When entries are added to the journal, other components such as the general ledger, trial balance, and financial statements are automatically updated to reflect the new data.

The application features an intuitive interface for managing accounting data and practicing accounting processes in a controlled environment. It is developed in Java using the Javalin framework and Freemarker for the templating engine, with HTML, CSS, JavaScript, and Bootstrap for the frontend, ensuring a smooth and modern user experience.

## Table of Contents

1. [Features](#features)
2. [Application](#application)
3. [Tools Used](#tools-used)
4. [How to Install](#how-to-install)
5. [Contributors](#contributors)
6. [License](#license)
7. [Contact Me](#contact-me)

## Features

### 🏢 Company Registration
Allows users to register fictional companies within the system. Each company can have its own configuration and accounting cycles, facilitating the management and organization of accounting data for different entities.

### 📅 Accounting Cycle Creation
Users can create accounting cycles for each registered company. These cycles are essential for managing accounting entries and generating financial reports.

### 💳 Management of Entries and Transactions
Users can add accounting entries with detailed information about financial transactions. This includes entering debits and credits with appropriate dates and descriptions, which is crucial for accurately recording all accounting operations.

### 📓 Journal View
The journal shows a detailed list of all accounting entries recorded in a specific accounting cycle. The view allows users to review and verify all transactions in a chronological format.

### 📈 General Ledger View
The general ledger provides a detailed breakdown of all accounts in T-format for each accounting cycle. This view allows users to review the balance and movements of each individual account.

### 📊 Trial Balance
The trial balance provides an overview of the balance between debits and credits in an accounting cycle. This view includes a detailed breakdown of the accounts, helping to verify the accuracy of accounting records.

### 📑 Financial Statements Generation
The application allows generating several essential financial statements, including:
- **Income Statement:** Shows revenues and expenses to calculate net profit or loss.
- **Statement of Changes in Equity:** Details changes in the company's equity over a specific period.
- **Balance Sheet:** Provides an overview of the company's assets, liabilities, and equity.
- **Cash Flow Statement:** Shows cash flows from operating, investing, and financing activities.

### 📚 Chart of Accounts Management
The chart of accounts allows users to add new accounts in categories such as Assets, Liabilities, Expenses, and Revenues. Here are the default accounts that are automatically added to the system:

#### Assets
<table>
  <tr>
    <th>Code</th>
    <th>Name</th>
  </tr>
  <tr>
    <td>111</td>
    <td>Cash</td>
  </tr>
  <tr>
    <td>112</td>
    <td>Accounts Receivable</td>
  </tr>
  <tr>
    <td>114</td>
    <td>Supplies</td>
  </tr>
  <tr>
    <td>115</td>
    <td>Office Materials</td>
  </tr>
  <tr>
    <td>121</td>
    <td>Land</td>
  </tr>
  <tr>
    <td>123</td>
    <td>Furniture and Equipment</td>
  </tr>
</table>

#### Liabilities
<table>
  <tr>
    <th>Code</th>
    <th>Name</th>
  </tr>
  <tr>
    <td>211</td>
    <td>Notes Payable</td>
  </tr>
  <tr>
    <td>212</td>
    <td>Accounts Payable</td>
  </tr>
</table>

#### Revenues
<table>
  <tr>
    <th>Code</th>
    <th>Name</th>
  </tr>
  <tr>
    <td>411</td>
    <td>Service Revenues</td>
  </tr>
</table>

#### Expenses
<table>
  <tr>
    <th>Code</th>
    <th>Name</th>
  </tr>
  <tr>
    <td>612.01</td>
    <td>Salaries Expenses</td>
  </tr>
  <tr>
    <td>612.05</td>
    <td>Rent Expenses</td>
  </tr>
  <tr>
    <td>612.07</td>
    <td>Telephone Expenses</td>
  </tr>
  <tr>
    <td>612.1</td>
    <td>Electricity Expenses</td>
  </tr>
</table>

#### Equity
<table>
  <tr>
    <th>Code</th>
    <th>Name</th>
  </tr>
  <tr>
    <td>311</td>
    <td>Capital</td>
  </tr>
</table>

#### Withdrawals
<table>
  <tr>
    <th>Code</th>
    <th>Name</th>
  </tr>
  <tr>
    <td>312</td>
    <td>Withdrawals</td>
  </tr>
</table>

## Application

Here are some screenshots and views of the application in action (Most important views):

**Login and Registration**
<p align="left">
  <img src="https://github.com/user-attachments/assets/65d65201-286b-41c9-babe-25cdeb970b22" alt="Login"/>
</p>

**Accounting Cycles List**
<p align="left">
  <img src="https://github.com/user-attachments/assets/28277a76-6b74-4014-823b-3a91bf2af46a" alt="Accounting Cycles"/>
</p>

**Journal View**
<p align="left">
  <img src="https://github.com/user-attachments/assets/d4bde41d-647f-4cb2-a581-d1d0729e870d" alt="Journal View"/>
</p>

**General Ledger View**
<p align="left">
  <img src="https://github.com/user-attachments/assets/9252cf3e-0437-4c5b-8afa-f04a96d23bb4" alt="General Ledger View"/>
</p>

**Trial Balance View**
<p align="left">
  <img src="https://github.com/user-attachments/assets/51119d03-2dcb-4fc3-b7f1-648ebc9c1065" alt="Trial Balance View"/>
</p>

**Financial Statements View**
<p align="left">
  <img src="https://github.com/user-attachments/assets/4a9e7629-58f1-4752-9e64-f1e34290e834" alt="Financial Statements View"/>
</p>

**Accounts Catalog View**
<p align="left">
  <img src="https://github.com/user-attachments/assets/f25767d2-54a5-4c52-9748-8c817c3086a1" alt="Accounts Catalog View"/>
</p>

## Tools Used
- **Language:** Java ![Java](https://img.shields.io/badge/Language-Java-blue)
- **Frameworks:** Javalin ![Javalin](https://img.shields.io/badge/Framework-Javalin-brightgreen)
- **Frontend:** HTML, CSS, JavaScript, Bootstrap, Freemarker ![HTML](https://img.shields.io/badge/HTML5-E34F26?style=flat&logo=html5&logoColor=white) ![CSS](https://img.shields.io/badge/CSS3-1572B6?style=flat&logo=css3&logoColor=white) ![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=flat&logo=javascript&logoColor=black) ![Bootstrap](https://img.shields.io/badge/Bootstrap-563D7C?style=flat&logo=bootstrap&logoColor=white) ![Freemarker](https://img.shields.io/badge/Template%20Engine-Freemarker-003000?style=flat&logo=freemarker&logoColor=white)
- **Build Tool:** Gradle ![Gradle](https://img.shields.io/badge/Build%20Tool-Gradle-02303A?style=flat&logo=gradle&logoColor=white)

## How to Install
To run the application,
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
      <a href="https://github.com/darvybm">
        <img src="https://github.com/darvybm.png" width="100px;" alt="Your Name"/>
        <br />
        <sub><b>Darvy Betances</b></sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/MarcosBlancoPUCMM">
        <img src="https://github.com/MarcosBlancoPUCMM.png" width="100px;" alt="Your Name"/>
        <br />
        <sub><b>Marcos Blanco</b></sub>
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
