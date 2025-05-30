package com.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.core.BaseTest;
import com.page.BBLoginPage;
import com.page.MainPage;
import com.page.BBRegisterPage;
import com.page.BBTransferPage;

public class BBTransferTest extends BaseTest {

    private String numeroContaDestino;
    private String digitoContaDestino;

    private BBLoginPage loginPage;
    private BBRegisterPage registerPage;
    private MainPage mainPage;
    private BBTransferPage transferPage;   

    @BeforeEach
    public void setUp() {
        loginPage = new BBLoginPage();
        registerPage = new BBRegisterPage();
        mainPage = new MainPage();
        transferPage = new BBTransferPage();
        loginPage.open("https://bugbank.netlify.app/#");

	}

    public void criaContaDestino(){
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

    public void criaContaOrigem(){
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

    @DisplayName("CN0001 - Transferência para conta Inválida")
    @Test
    public void transfContaInvalida() {
        criaContaOrigem();
        mainPage.clkTransfer();
        transferPage.preencherNumeroConta("000");
        transferPage.preencherDigitoConta("1");
        transferPage.sendValorTransf("100.00");
        transferPage.sendDescricao("Teste");
        transferPage.btnTransf();

        String successmsg = transferPage.getSuccessMessage();
        assertEquals("Conta inválida ou inexistente", successmsg);

    }

    @Disabled("[BUG-0011] - Campos da conta aceitando letras ao invés de aceitar apenas números")
    @DisplayName("CN0002 - Campos da conta com letras")
    @Test
    public void camposCcLetras() {
        criaContaOrigem();
        mainPage.clkTransfer();
        transferPage.preencherNumeroConta("aaa");
        transferPage.preencherDigitoConta("a");
        String valorNumeroConta = transferPage.getCampoNumeroConta().getAttribute("value");
        String valorDigitoConta = transferPage.getCampoDigitoConta().getAttribute("value");
        transferPage.sendValorTransf("100.00");
        transferPage.sendDescricao("Teste");
        transferPage.btnTransf();

       assertTrue(valorNumeroConta.matches("^\\d*$"));
       assertTrue(valorDigitoConta.matches("^\\d*$"));

    }

    @Disabled("[BUG-0012] - Campo descrição não é um campo de preenchimento obrigatório")
    @DisplayName("CN0003 - Campo descrição vazio")
    @Test
    public void campoDescVazio() {
        criaContaDestino();
        criaContaOrigem();
        mainPage.clkTransfer();
        transferPage.preencherNumeroConta(numeroContaDestino.trim());
        transferPage.preencherDigitoConta(digitoContaDestino.trim());
        transferPage.sendValorTransf("100.00");
        transferPage.btnTransf();

        String successmsg = transferPage.getSuccessMessage();
        assertFalse(successmsg.matches(successmsg));

    }

    @DisplayName("CN0004 - Valor igual ou menor que zero")
    @Test
    public void transfValorIgualOuMenorQueZero() {
        criaContaDestino();
        criaContaOrigem();
        mainPage.clkTransfer();
        transferPage.preencherNumeroConta(numeroContaDestino.trim());
        transferPage.preencherDigitoConta(digitoContaDestino.trim());
        transferPage.sendValorTransf("-1.00");
        transferPage.sendDescricao("Teste");
        transferPage.btnTransf();

        String successmsg = transferPage.getSuccessMessage();
        assertEquals("Valor da transferência não pode ser 0 ou negativo", successmsg);

    }

    @DisplayName("CN0005 - Saldo insuficiente")
    @Test
    public void transfSaldoInsuficiente() {
        criaContaDestino();
        criaContaOrigem();
        mainPage.clkTransfer();
        transferPage.preencherNumeroConta(numeroContaDestino.trim());
        transferPage.preencherDigitoConta(digitoContaDestino.trim());
        transferPage.sendValorTransf("2000.00");
        transferPage.sendDescricao("Teste");
        transferPage.btnTransf();

        String successmsg = transferPage.getSuccessMessage();
        assertEquals("Você não tem saldo suficiente para essa transação", successmsg);

    }

    @DisplayName("CN0006 - Transferência para conta Válida")
    @Test
    public void transfContaValida() {
        criaContaDestino();
        criaContaOrigem();
        mainPage.clkTransfer();
        transferPage.preencherNumeroConta(numeroContaDestino.trim());
        transferPage.preencherDigitoConta(digitoContaDestino.trim());
        transferPage.sendValorTransf("100.00");
        transferPage.sendDescricao("Teste");
        transferPage.btnTransf();

        String successmsg = transferPage.getSuccessMessage();
        assertEquals("Transferencia realizada com sucesso", successmsg);

    }


}