package com.test;


import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.core.BaseTest;
import com.page.BBExtratoPage;
import com.page.BBLoginPage;
import com.page.MainPage;
import com.page.BBRegisterPage;
import com.page.BBTransferPage;

public class BBExtratoTest extends BaseTest {

    private String numeroContaDestino;
    private String digitoContaDestino;

    private BBLoginPage loginPage;
	private BBRegisterPage registerPage;
	private MainPage mainPage;
    private BBTransferPage transferPage;
    private BBExtratoPage extratoPage;

    @BeforeEach
	public void setUp() {
        transferPage = new BBTransferPage();
		loginPage = new BBLoginPage();
        registerPage = new BBRegisterPage();
        mainPage = new MainPage();
        extratoPage = new BBExtratoPage();
		loginPage.open("https://bugbank.netlify.app/#");
		
	}

    public void criaContaOps(){        
        loginPage.clickRegister();
        registerPage.sendEmail("test01@test.com");
        registerPage.sendName("Gustavo 01");
        registerPage.sendPassword("X7v#9pLr!Q2z@Wm5");
        registerPage.sendConfirmationPassword("X7v#9pLr!Q2z@Wm5");
        registerPage.clickCadastrar();
        
        String numeroConta = registerPage.getNumeroContaCriada();
        System.out.println("Texto capturado do modal: [" + numeroConta + "]");
        Pattern pattern = Pattern.compile("\\b\\d{3}-\\d\\b");
        Matcher matcher = pattern.matcher(numeroConta);

        registerPage.closeModalMessage();

        if (matcher.find()) {
            String numeroCompleto = matcher.group();
            String[] partes = numeroCompleto.split("-");
            this.numeroContaDestino = partes[0].trim();
            this.digitoContaDestino = partes[1].trim();
        } else {
            throw new RuntimeException("Número da conta não encontrado no modal");
        }
    }

    public void criaContaCSaldo(){
        loginPage.clickRegister();
        registerPage.sendEmail("test02@test.com");
        registerPage.sendName("Gustavo 02");
        registerPage.sendPassword("X7v#9pLr!Q2z@Wm5");
        registerPage.sendConfirmationPassword("X7v#9pLr!Q2z@Wm5");
        registerPage.toogleAddBalance();
        registerPage.clickCadastrar();
        registerPage.closeModalMessage();
        
        loginPage.sendEmail("test02@test.com");
		loginPage.sendPassword("X7v#9pLr!Q2z@Wm5");
	    loginPage.clickAcessar();

    }

    public void criaContaSSaldo(){
        loginPage.clickRegister();
        registerPage.sendEmail("test03@test.com");
        registerPage.sendName("Gustavo 03");
        registerPage.sendPassword("X7v#9pLr!Q2z@Wm5");
        registerPage.sendConfirmationPassword("X7v#9pLr!Q2z@Wm5");
        registerPage.clickCadastrar();
        registerPage.closeModalMessage();
        
        loginPage.sendEmail("test03@test.com");
		loginPage.sendPassword("X7v#9pLr!Q2z@Wm5");
	    loginPage.clickAcessar();

    }

    public static String getDataAtualFormatada() {
        LocalDate dataAtual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dataAtual.format(formatter);
    }

    @DisplayName("CN0001 - Visualização de saldo - Conta criada com saldo")
    @Test
    public void contaComSaldo(){
        criaContaCSaldo();
        mainPage.clkExtrato();
        String extSaldo = extratoPage.getSaldoDisponivel();
        assertEquals("R$ 1.000,00", extSaldo);

    }
    
    @DisplayName("CN0002 - Visualização de saldo - Conta criada sem saldo")
    @Test
    public void contaSemSaldo(){
        criaContaSSaldo();
        mainPage.clkExtrato();
        String extSaldo = extratoPage.getSaldoDisponivel();
        assertEquals("R$ 0,00", extSaldo);

    }

    @DisplayName("CN0003 - CT0001 - Visualização de transações - Saldo após Abertura de conta com saldo")
    @Test
    public void visuSaldoAberturaCom(){
        criaContaCSaldo();
        mainPage.clkExtrato();

        getDataAtualFormatada();
        String dateTransaction = extratoPage.getDateTransaction();
        assertEquals(getDataAtualFormatada(), dateTransaction);

        List<String> typetransacoes = extratoPage.getTypeTransaction();
        String primeiraTransacao = typetransacoes.get(0);
        assertEquals("Abertura de conta", primeiraTransacao);

        List<String> comTransaction = extratoPage.getComTransaction();
        assertEquals("Saldo adicionado ao abrir conta", comTransaction);

    }

    @DisplayName("CN0003 - CT0002 - Visualização de transações - Saldo após Abertura de conta sem saldo")
    @Test
    public void visuSaldoAberturaSem(){
        criaContaSSaldo();
        mainPage.clkExtrato();

        getDataAtualFormatada();
        String dateTransaction = extratoPage.getDateTransaction();
        assertEquals(getDataAtualFormatada(), dateTransaction);

        List<String> typetransacoes = extratoPage.getTypeTransaction();
        String primeiraTransacao = typetransacoes.get(0);
        assertEquals("Abertura de conta", primeiraTransacao);

        List<String> comTransaction = extratoPage.getComTransaction();
        assertEquals("Cliente optou por não ter saldo ao abrir conta", comTransaction);

    }

    @DisplayName("CN0003 - CT0003 - Visualização de transações - Saldo após Transferência")
    @Test
    public void visuSaldoTransf(){
        criaContaOps();
        criaContaCSaldo();
        mainPage.clkTransfer();
        transferPage.preencherNumeroConta(numeroContaDestino.trim());
        transferPage.preencherDigitoConta(digitoContaDestino.trim());
        transferPage.sendValorTransf("100.00");
        transferPage.sendDescricao("Teste");
        transferPage.btnTransf();
        transferPage.closeModalMessage();
        transferPage.btnBack();
        mainPage.clkExtrato();

        getDataAtualFormatada();
        String dateTransaction = extratoPage.getDateTransaction();
        assertEquals(getDataAtualFormatada(), dateTransaction);

        List<String> typetransacoes = extratoPage.getTypeTransaction();
        String segundaTransacao = typetransacoes.get(1);
        assertEquals("Transferência enviada", segundaTransacao);

        List<String> comTransaction = extratoPage.getComTransaction();
        assertFalse(comTransaction.get(1).isBlank());


    }

    @DisplayName("CN0003 - CT0004 - Visualização de transações - Saldo após receber Transferência")
    @Test
    public void visuRecebimento(){
        criaContaOps();
        criaContaCSaldo();
        mainPage.clkTransfer();
        transferPage.preencherNumeroConta(numeroContaDestino.trim());
        transferPage.preencherDigitoConta(digitoContaDestino.trim());
        transferPage.sendValorTransf("100.00");
        transferPage.sendDescricao("Teste");
        transferPage.btnTransf();
        transferPage.closeModalMessage();
        transferPage.btnBack();
        mainPage.btnlogout();
        loginPage.sendEmail("test01@test.com");
		loginPage.sendPassword("X7v#9pLr!Q2z@Wm5");
	    loginPage.clickAcessar();
        mainPage.clkExtrato();

        getDataAtualFormatada();
        String dateTransaction = extratoPage.getDateTransaction();
        assertEquals(getDataAtualFormatada(), dateTransaction);

        List<String> typetransacoes = extratoPage.getTypeTransaction();
        String segundaTransacao = typetransacoes.get(1);
        assertEquals("Transferência recebida", segundaTransacao);

        List<String> comTransaction = extratoPage.getComTransaction();
        assertFalse(comTransaction.get(1).isBlank());


    }

    @DisplayName("CN0004 - Valor de saída")
    @Test
    public void valorSaida(){
        criaContaOps();
        criaContaCSaldo();
        mainPage.clkTransfer();
        transferPage.preencherNumeroConta(numeroContaDestino.trim());
        transferPage.preencherDigitoConta(digitoContaDestino.trim());
        transferPage.sendValorTransf("100.00");
        transferPage.sendDescricao("Teste");
        transferPage.btnTransf();
        transferPage.closeModalMessage();
        transferPage.btnBack();
        mainPage.clkExtrato();

        List<String> typetransacoes = extratoPage.getTypeTransaction();
        typetransacoes.get(1);
        assertEquals("rgba(255, 0, 0, 1)", extratoPage.getwithdrawalColorRed());
        
        List<String> valor = extratoPage.getValTransacaoNegativa();
        assertTrue(valor.get(1).startsWith("-"));       

    }

    @DisplayName("CN0005 - Valor de entrada")
    @Test
    public void valorEntrada(){
        criaContaOps();
        criaContaCSaldo();
        mainPage.clkTransfer();
        transferPage.preencherNumeroConta(numeroContaDestino.trim());
        transferPage.preencherDigitoConta(digitoContaDestino.trim());
        transferPage.sendValorTransf("100.00");
        transferPage.sendDescricao("Teste");
        transferPage.btnTransf();
        transferPage.closeModalMessage();
        transferPage.btnBack();
        mainPage.btnlogout();
        loginPage.sendEmail("test01@test.com");
		loginPage.sendPassword("X7v#9pLr!Q2z@Wm5");
        loginPage.clickAcessar();
        mainPage.clkExtrato();

        List<String> typetransacoes = extratoPage.getTypeTransaction();
        typetransacoes.get(1);
        assertEquals("rgba(0, 128, 0, 1)", extratoPage.getwithdrawalColorGreen());
           

    }

    @DisplayName("CN0006 - Transação sem descrição")
    @Test
    public void transacaoNoDesc(){
        criaContaOps();
        criaContaCSaldo();
        mainPage.clkTransfer();
        transferPage.preencherNumeroConta(numeroContaDestino.trim());
        transferPage.preencherDigitoConta(digitoContaDestino.trim());
        transferPage.sendValorTransf("100.00");
        transferPage.btnTransf();
        transferPage.closeModalMessage();
        transferPage.btnBack();
        mainPage.btnlogout();
        loginPage.sendEmail("test01@test.com");
		loginPage.sendPassword("X7v#9pLr!Q2z@Wm5");
        loginPage.clickAcessar();
        mainPage.clkExtrato();

        List<String> desctransacoes = extratoPage.getComTransaction();
        desctransacoes.get(1);
        assertTrue(desctransacoes.contains("-"));


    }



}
