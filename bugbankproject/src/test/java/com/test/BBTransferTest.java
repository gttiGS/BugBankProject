package com.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
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

        private void criaContaDestino(){
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

    private void criaContaOrigem(){
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

        mainPage.clkTransfer();
    }

    @DisplayName("CN0009 - Transferência para conta válida")
    @Test
    public void transfContaInvalida() throws Exception{
        criaContaDestino();
        criaContaOrigem();
        transferPage.preencherNumeroConta(numeroContaDestino.trim());
        transferPage.preencherDigitoConta(digitoContaDestino.trim());
        transferPage.sendValorTransf("100.00");
        transferPage.sendDescricao("Teste");
        transferPage.btnTransf();

        String successmsg = transferPage.getSuccessMessage();
        assertEquals("Transferencia realizada com sucesso", successmsg);

    }


}