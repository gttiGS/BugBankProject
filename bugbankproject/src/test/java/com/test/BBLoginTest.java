package com.test;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
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
	
	@Test
	public void testLoginSuccess() throws InterruptedException {
		registerPage = loginPage.clickRegister();
		
		Thread.sleep(1000);
		registerPage.sendEmail("test@test.com");
		registerPage.sendName("Teste01");
		registerPage.sendPassword("123456");
		registerPage.sendConfirmationPassword("123456");		
		registerPage.toogleAddBalance();
		
		registerPage.clickCadastrar();		
		
		String message = registerPage.getSuccessMessage();
		System.out.println(message);
		assertTrue(message.contains("foi criada com sucesso"));
		
		registerPage.closeModalMessage();
		
		loginPage.sendEmail("test@test.com");
		loginPage.sendPassword("123456");
		mainPage = loginPage.clickAcessar();
		
		Thread.sleep(2000);
		assertEquals("Olá Teste01,", mainPage.getUserLogged());
		
	}

}