package com.test;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.core.BaseTest;
import com.page.BBLoginPage;
import com.page.MainPage;
import com.page.BBRegisterPage;

public class BBLoginTest extends BaseTest{
	
	private BBLoginPage loginPage;
	private BBRegisterPage registerPage;
	private MainPage mainPage;
	
	
	@BeforeEach
	public void setUp() {
		loginPage = new BBLoginPage();
		loginPage.open("https://bugbank.netlify.app/#");
		
	}

    @Disabled("[BUG-0010] - Tentativa de Login com campos vazios não exibe modal com mensagem 'Usuário e senha precisam ser preenchidos.' ")
    @DisplayName("CN0001 - Login com os campos vazios")	
    @Test
    public void loginCamposVazios(){
        loginPage.clickAcessar();

        String message = loginPage.getWarningMessage();
        assertEquals("Usuário e senha precisam ser preenchidos.", message);
    }

    @DisplayName("CN0002 - Login com credenciais inválidas")
    @Test
    public void loginCredenciaisInvalidas() {
        loginPage.sendEmail("test@te.com");
        loginPage.sendPassword("123");
        loginPage.clickAcessar();
                
        String message = loginPage.getWarningMessage();
        assertEquals("Usuário ou senha inválido. Tente novamente ou verifique suas informações!", message.replaceAll("\\s+", " ").trim());
    }

    @DisplayName("CN0003 - Login com email válido e senha inválida")
    @Test
    public void loginSenhaInvalida(){
        registerPage = loginPage.clickRegister();

		registerPage.sendEmail("test@test.com");
		registerPage.sendName("Gustavo Freitas");
		registerPage.sendPassword("123456");
		registerPage.sendConfirmationPassword("123456");
        registerPage.clickCadastrar();

    	String successmsg = registerPage.getSuccessMessage();
		System.out.println(successmsg);
		assertTrue(successmsg.contains("foi criada com sucesso"));
        registerPage.closeModalMessage();

		loginPage.sendEmail("test@test.com");
		loginPage.sendPassword("1212");
		mainPage = loginPage.clickAcessar();

        String warningmsg = loginPage.getWarningMessage();
        assertEquals("Usuário ou senha inválido. Tente novamente ou verifique suas informações!", warningmsg.replaceAll("\\s+", " ").trim());
    }

    @DisplayName("CN0004 - Login com email inválido e senha válida")
    @Test
    public void loginEmailInvalido(){
        registerPage = loginPage.clickRegister();

		registerPage.sendEmail("test@test.com");
		registerPage.sendName("Gustavo Freitas");
		registerPage.sendPassword("123456");
		registerPage.sendConfirmationPassword("123456");
        registerPage.clickCadastrar();

    	String successmsg = registerPage.getSuccessMessage();
		System.out.println(successmsg);
		assertTrue(successmsg.contains("foi criada com sucesso"));
        registerPage.closeModalMessage();

		loginPage.sendEmail("test@tes.com");
		loginPage.sendPassword("123456");
		mainPage = loginPage.clickAcessar();

        String warningmsg = loginPage.getWarningMessage();
        assertEquals("Usuário ou senha inválido. Tente novamente ou verifique suas informações!", warningmsg.replaceAll("\\s+", " ").trim());
    }

    @DisplayName("CN0005 - Login com credenciais válidas")
	@Test
	public void loginCredenciaisValidas() throws InterruptedException {
		registerPage = loginPage.clickRegister();
		
		Thread.sleep(1000);
		registerPage.sendEmail("test@test.com");
		registerPage.sendName("Gustavo Freitas");
		registerPage.sendPassword("123456");
		registerPage.sendConfirmationPassword("123456");		
		registerPage.clickCadastrar();		
		
		String successmsg = registerPage.getSuccessMessage();
		System.out.println(successmsg);
		assertTrue(successmsg.contains("foi criada com sucesso"));
		
		registerPage.closeModalMessage();
		
		loginPage.sendEmail("test@test.com");
		loginPage.sendPassword("123456");
		mainPage = loginPage.clickAcessar();
		
		Thread.sleep(2000);
		assertEquals("Olá Gustavo Freitas,", mainPage.getUserLogged());
		
	}

    @DisplayName("CN0006 - Logout")
    @Test
    public void logout() throws InterruptedException{
		registerPage = loginPage.clickRegister();
        
		
		Thread.sleep(1000);
		registerPage.sendEmail("test@test.com");
		registerPage.sendName("Gustavo Freitas");
		registerPage.sendPassword("123456");
		registerPage.sendConfirmationPassword("123456");		
		registerPage.clickCadastrar();		
		
		String successmsg = registerPage.getSuccessMessage();
		System.out.println(successmsg);
		assertTrue(successmsg.contains("foi criada com sucesso"));
		
		registerPage.closeModalMessage();
		
		loginPage.sendEmail("test@test.com");
		loginPage.sendPassword("123456");
		mainPage = loginPage.clickAcessar();
		
		Thread.sleep(2000);
		assertEquals("Olá Gustavo Freitas,", mainPage.getUserLogged());

        mainPage.btnlogout();
        assertEquals("O banco com bugs e falhas do seu jeito", loginPage.initialText());

    }

}