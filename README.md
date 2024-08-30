# WhatsApp Group Creation Automation

This project automates the creation of WhatsApp groups using data from an Excel spreadsheet.
Criação automatizada de grupos do zap, utilizando de uma planilha excel.

## Requirements

- **Eclipse IDE**
- **Java Development Kit (JDK)** (Download and install)
- **Maven** for dependency management (https://dlcdn.apache.org/maven/maven-3/3.9.9/binaries/apache-maven-3.9.9-bin.zip)
- **ChromeDriver** for Chrome automation (https://storage.googleapis.com/chrome-for-testing-public/128.0.6613.86/win64/chromedriver-win64.zip)
- **Test Spreadsheet**

## Setting Up the Environment

1. Add the paths for Maven and ChromeDriver to your environment variables:
   - Search for "Environment Variables" in Windows (you can add them for either the user or system; user variables are only valid for the current user, while system variables are for all users on the PC).
   - Create a variable named `MAVEN_HOME` and set its value to the directory of `apache-maven-3.9.9`.
   - Go to the `Path` variable, click "Edit," then click "New" (be careful not to delete or overwrite existing entries). Add `%MAVEN_HOME%\bin` as a new entry.
   - In the `Path` variable, add another entry for the directory of the ChromeDriver folder `chromedriver-win64`.

2. Within the project:
   - In the file `WhatsAppGroupAutomationUI.java`, go to line 127 and specify the directory of the `chromedriver.exe` file (located inside the `chromedriver-win64` folder).
   - At the end of the code, the `cssSelector` code was intentionally modified to avoid spamming group creation, but the correct code is commented in the file.

## How to Run

1. **Choose the Spreadsheet:**
   - Select the desired spreadsheet. Note that the first row will be skipped.
   - Table A should contain the names, and Table B should contain the numbers. The format number is: "82988884444" (11 digits). If you need put another coutry number, you will need to modify the code.

2. **Start the Automation:**
   - Enter the group name.
   - Click on "Start Automation."

3. **Open WhatsApp Web:**
   - A new page with the WhatsApp Web QR Code will open.
   - A popup with a notice will appear. Click "OK" only after logging in with the QR Code and waiting for the page to fully load.

4. **Completion:**
   - The automation will perform its function as configured.
   - At the end, the group name will be added.


---

# Automação de Criação de Grupos no WhatsApp

Este projeto automatiza a criação de grupos no WhatsApp usando dados de uma planilha Excel.

## Requisitos

- **Eclipse IDE**
- **Java Development Kit (JDK)** (Baixe e instale)
- **Maven** para gerenciamento de dependências (https://dlcdn.apache.org/maven/maven-3/3.9.9/binaries/apache-maven-3.9.9-bin.zip)
- **ChromeDriver** para automação do Chrome (https://storage.googleapis.com/chrome-for-testing-public/128.0.6613.86/win64/chromedriver-win64.zip)
- **Planilha de Teste**

## Preparando o Ambiente de Trabalho

1. Adicione os caminhos para Maven e ChromeDriver nas variáveis de ambiente:
   - Pesquise "Variáveis de Ambiente" no Windows (você pode adicioná-las para o usuário ou para o sistema; variáveis de usuário são válidas apenas para o usuário atual, enquanto variáveis de sistema são para todos os usuários do PC).
   - Crie uma variável chamada `MAVEN_HOME` e defina seu valor como o diretório de `apache-maven-3.9.9'.
   - Vá até a variável `Path`, clique em "Editar", depois clique em "Novo" (cuidado para não excluir ou sobrepor entradas existentes). Adicione `%MAVEN_HOME%\bin` como uma nova entrada.
   - Na variável `Path`, adicione outra entrada para o diretório da pasta do ChromeDriver `chromedriver-win64`.

2. Dentro do projeto:
   - No arquivo `WhatsAppGroupAutomationUI.java`, vá para a linha 127 e especifique o diretório do arquivo `chromedriver.exe` (localizado dentro da pasta `chromedriver-win64`).
   - No final do código, o código do `cssSelector` foi alterado propositalmente para evitar o envio em massa de criação de grupos, mas o código correto está comentado no arquivo.

## Como Rodar

1. **Escolha a Planilha:**
   - Selecione a planilha desejada. Note que a primeira linha será ignorada.
   - A tabela A deve conter os nomes e a tabela B, os números. Os numeros tem que ser formatados assim:"82988884444" (11 digitos)

2. **Inicie a Automação:**
   - Escolha o nome do grupo.
   - Clique em "Iniciar Automação".

3. **Abra o WhatsApp Web:**
   - Uma nova página será aberta com o QR Code do WhatsApp Web.
   - Um popup com um aviso aparecerá. Clique em "OK" somente após fazer login com o QR Code e aguardar o carregamento completo da página.

4. **Finalização:**
   - A automação será realizada conforme configurado.
   - Ao final, o nome do grupo será adicionado.

