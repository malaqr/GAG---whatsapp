package com.meuprojeto.whatsapp.whatsapp_automation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

class Contato 
{
    private String nome;
    private String telefone;

    public Contato(String nome, String telefone) 
    {
        this.nome = nome;
        this.telefone = telefone;
    }

    public String getNome() 
    {
        return nome;
    }

    public String getTelefone() 
    {
        return telefone;
    }
}

public class WhatsAppGroupAutomationUI extends JFrame 
{

    private JLabel lblFilePath;
    private String filePath;
    private JTextField txtGroupName;

    public WhatsAppGroupAutomationUI() {
        setTitle("Automatização de Grupos no WhatsApp");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout da interface
        setLayout(new FlowLayout());

        // Botão para selecionar planilha
        JButton btnSelectFile = new JButton("Selecionar Planilha");
        btnSelectFile.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                selecionarPlanilha();
            }
        });

        // Rótulo para mostrar o caminho do arquivo selecionado
        lblFilePath = new JLabel("Nenhum arquivo selecionado");

        // Campo para inserir o nome do grupo
        txtGroupName = new JTextField(20);

        // Botão para iniciar a automação
        JButton btnStartAutomation = new JButton("Iniciar Automação");
        btnStartAutomation.addActionListener(new ActionListener() {
            @Override
            
            public void actionPerformed(ActionEvent e) {
                if (filePath != null && !filePath.isEmpty() && !txtGroupName.getText().isEmpty()) 
                {
                    iniciarAutomacao(filePath, txtGroupName.getText());
                } 
                
                else 
		        {
		           JOptionPane.showMessageDialog(null, "Por favor, selecione uma planilha e insira o nome do grupo.");
		        }
            }
        });

        // Adicionando componentes ao frame
        add(btnSelectFile);
        add(lblFilePath);
        add(new JLabel("Nome do Grupo:"));
        add(txtGroupName);
        add(btnStartAutomation);
    }

    private void selecionarPlanilha() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) 
        {
            File selectedFile = fileChooser.getSelectedFile();
            filePath = selectedFile.getAbsolutePath();
            lblFilePath.setText("Arquivo: " + selectedFile.getName());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                new WhatsAppGroupAutomationUI().setVisible(true);
            }
        });
    }

    private void iniciarAutomacao(String caminhoArquivo, String nomeGrupo) 
    {
        System.setProperty("webdriver.chrome.driver", "D:\\lulinha\\Dev\\Projetos\\Projeto poo\\Arquivos\\chromedriver-win64\\chromedriver.exe"); 
        WebDriver driver = new ChromeDriver(); // Configurar o caminho do ChromeDriver

        try 
        {
            List<Contato> contatos = obterContatosDaPlanilha(caminhoArquivo); // Ler os contatos da planilha +55 82 9677-2200

            driver.get("https://web.whatsapp.com/");  // Acessar o WhatsApp Web
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            JOptionPane.showMessageDialog(null, "Por favor, escaneie o código QR do WhatsApp Web e espere carregar a página, depois pressione OK para continuar...");

            criarGrupoWhatsApp(driver, nomeGrupo, contatos);

            JOptionPane.showMessageDialog(null, "Grupo criado com sucesso!");

        } catch (Exception e) 
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao criar grupo: " + e.getMessage());
        } finally 
        {
            driver.quit();
        }
    }

    private List<Contato> obterContatosDaPlanilha(String caminhoArquivo) {
        List<Contato> contatos = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(new File(caminhoArquivo));
             Workbook workbook = new XSSFWorkbook(file)) {
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) 
            {
                if (row.getRowNum() == 0) continue; // Ignora a primeira linha (cabeçalho)

                // Obtém os valores das células
                String nome = obterValorCelulaComoString(row.getCell(0));
                String telefone = obterValorCelulaComoString(row.getCell(1));

                // Adiciona o contato à lista se ambos os valores não forem nulos
                if (nome != null && telefone != null)
                {
                    contatos.add(new Contato(nome, telefone));
                }
            }

        } catch (IOException e) 
        {
            e.printStackTrace();
        }

        return contatos;
    }

    private String obterValorCelulaComoString(Cell cell) 
    {
        if (cell == null) return null;

        switch (cell.getCellType()) 
        {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                // Formata o número como string sem casas decimais
                return String.format("%.0f", cell.getNumericCellValue());
            default:
                System.out.println("Tipo de célula inesperado na linha " + cell.getRowIndex());
                return null;
        }
    }


    
    public String formatarNumero(String numero) 
    {
        // Verifica se o número possui o comprimento esperado
        if (numero.length() != 11) {
            throw new IllegalArgumentException("O número deve ter 11 dígitos.");
        }

        // Remove o dígito '9' do início, após o código da área
        String numeroSemNove;
        if (numero.charAt(2) == '9') {
            numeroSemNove = numero.substring(0, 2) + numero.substring(3);
        } else {
            numeroSemNove = numero;
        }

        // Adiciona o '+' no início do número, os espaços e hífen para a formatação correta
        String formatado = "+55 " + numeroSemNove.substring(0, 2);  // Código da área
        formatado += " " + numeroSemNove.substring(2, 6);           // Primeiro bloco do número local
        formatado += "-" + numeroSemNove.substring(6);              // Segundo bloco do número local

        return formatado;
    }



    
    
    private void criarGrupoWhatsApp(WebDriver driver, String nomeGrupo, List<Contato> contatos) 
    {
        WebElement menu = driver.findElement(By.cssSelector("span[data-icon='menu']"));
        menu.click(); // clica nos 3 pontos (menu)

        WebElement novoGrupo = driver.findElement(By.cssSelector("div[class='_aj-z _aj-t _alxo']"));
        novoGrupo.click(); // clica no elemento novo grupo


        for (Contato contato : contatos) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Espera de até 10 segundos

            WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='text']")));
            
            // Formata o número de telefone antes de fazer a pesquisa
            String telefoneFormatado = formatarNumero(contato.getTelefone());
            searchBox.sendKeys(telefoneFormatado);
            
            try {
                // Aguarda até que o elemento com classe e número  apareça na lista de contatos
                   	
            	WebElement contatoElemento = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@title='" + telefoneFormatado + "']")));
            		contatoElemento.click();
            		
                // Adiciona um pequeno atraso para garantir que o contato seja adicionado
                Thread.sleep(1000); // 1 segundo de pausa
                
            } catch (Exception e) {
                System.out.println("Erro ao adicionar contato: " + e.getMessage());
            }
            
            searchBox.clear();  // Limpa a caixa de pesquisa para o próximo contato
        }


        driver.findElement(By.cssSelector("span[data-icon='arrow-forward']")).click();  //prosseguir para tela de nomear grupo


        WebElement nomeGrupoElemento = driver.findElement(By.cssSelector("div[contenteditable='true']"));
        nomeGrupoElemento.sendKeys(nomeGrupo);//inserir o nome do grupo

        
      //clica em finalizar a criação do grupo/
        driver.findElement(By.cssSelector("span[data-icon='checkmark']")).click();//------>  TROQUE o CSselector para:  "span[data-icon='checkmark-medium']" 
    }																					//para n ficar criando vários grupos em teste, quando for querer criar pra valer, troque.
}
