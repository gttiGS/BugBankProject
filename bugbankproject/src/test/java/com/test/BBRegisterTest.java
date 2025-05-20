package com.test;

import static org.junit.jupiter.api.Assertions.*;
import static com.core.DriverFactory.getDriver;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;


import com.core.BaseTest;
import com.page.BBLoginPage;
import com.page.MainPage;
import com.page.BBRegisterPage;

public class BBRegisterTest extends BaseTest {

    private BBLoginPage loginPage;
    private BBRegisterPage registerPage;
    private MainPage mainPage;

    @BeforeEach
    public void setUp() {
        loginPage = new BBLoginPage();
        loginPage.open("https://bugbank.netlify.app/#");
        registerPage = loginPage.clickRegister();
	}

    @Disabled("[BUG-0001] - Campo 'Nome' não exibe mensagem de campo obrigatório")
    @DisplayName("CN0001 - Cadastro com todos os campos vazios")
    @Test
    public void cadastroCamposVazios() {
        registerPage.clickCadastrar();

        List<WebElement> campoObrigatorio = getDriver().findElements(By.xpath("//*[contains(text(), 'É campo obrigatório')]"));
        List<WebElement> visiveis = campoObrigatorio.stream()
        .filter(WebElement::isDisplayed)
        .collect(Collectors.toList());

        assertEquals(4, visiveis.size());
    }

    @DisplayName("CN0002 - Campo Nome vazio")
    @Test
    public void campoNomeVazio(){
        registerPage.sendEmail("test@test.com");
        registerPage.sendPassword("12345");
        registerPage.sendConfirmationPassword("12345");
        registerPage.clickCadastrar();

		String message = registerPage.getWarningMessage();
		assertEquals("Nome não pode ser vazio.", message);

    }

    @Disabled("[BUG-0002] - Campo 'Email' não exibe mensagem 'Email não pode ser vazio.' após ser deixado em branco no cadastro")
    @DisplayName("CN0003 - Campo Email vazio")
    @Test
    public void campoEmailVazio(){
        registerPage.sendName("Gustavo Freitas");
        registerPage.sendPassword("12345");
        registerPage.sendConfirmationPassword("12345");
        registerPage.clickCadastrar();

		String message = registerPage.getWarningMessage();
		assertEquals("Email não pode ser vazio.", message);

    }

    @Disabled("[BUG-0003] - Campo 'Senha' não exibe mensagem 'Senha não pode ser vazio.' após ser deixado em branco no cadastro")
    @DisplayName("CN0004 - Campo Senha vazio")
    @Test
    public void campoSenhaVazio(){
        registerPage.sendEmail("test@test.com");
        registerPage.sendName("Gustavo Freitas");
        registerPage.sendConfirmationPassword("12345");
        registerPage.clickCadastrar();

		String message = registerPage.getWarningMessage();
		assertEquals("Senha não pode ser vazio.", message);

    }

    @Disabled("[BUG-0004] - Campo 'Confirmação de senha' não exibe mensagem 'Confirmar senha não pode ser vazio.' após ser deixado em branco no cadastro")
    @DisplayName("CN0005- Campo Confirmação de Senha vazio")
    @Test
    public void campoConfirmaSenhaVazio(){
        registerPage.sendEmail("test@test.com");
        registerPage.sendName("Gustavo Freitas");
        registerPage.sendPassword("12345");
        registerPage.clickCadastrar();

		String message = registerPage.getWarningMessage();
		assertEquals("Confirmar senha não pode ser vazio.", message);

    }

    @DisplayName("CN0006- Senha e confirmação diferentes")
    @Test
    public void senhasNaoCombinam(){
        registerPage.sendEmail("test@test.com");
        registerPage.sendName("Gustavo Freitas");
        registerPage.sendPassword("12345");
        registerPage.sendConfirmationPassword("12345678");
        registerPage.clickCadastrar();

		String message = registerPage.getWarningMessage();
		assertEquals("As senhas não são iguais.", message);

    }

    @DisplayName("CN0007 - Criar conta com saldo")
    @Test
    public void cadastroContaComSaldo() {
        registerPage.sendEmail("test@test.com");
        registerPage.sendName("Gustavo Freitas");
        registerPage.sendPassword("12345");
        registerPage.sendConfirmationPassword("12345");
        registerPage.toogleAddBalance();  

        assertTrue(registerPage.toggleIsChecked());

        registerPage.clickCadastrar();

    	String message = registerPage.getSuccessMessage();
		assertTrue(message.contains("foi criada com sucesso"));
        registerPage.closeModalMessage();

        loginPage.sendEmail("test@test.com");
		loginPage.sendPassword("12345");
		mainPage = loginPage.clickAcessar();

		assertEquals("Olá Gustavo Freitas,", mainPage.getUserLogged()); 
        assertEquals("Saldo em conta R$ 1.000,00", mainPage.getSaldo());

    }

    @DisplayName("CN0008 - Criar conta sem saldo")
    @Test
    public void cadastroContaSemSaldo() {
        registerPage.sendEmail("test@test.com");
        registerPage.sendName("Gustavo Freitas");
        registerPage.sendPassword("12345");
        registerPage.sendConfirmationPassword("12345"); 

        assertTrue(registerPage.toggleIsNotChecked());

        registerPage.clickCadastrar();

    	String message = registerPage.getSuccessMessage();
		assertTrue(message.contains("foi criada com sucesso"));
        registerPage.closeModalMessage();

        loginPage.sendEmail("test@test.com");
		loginPage.sendPassword("12345");
		mainPage = loginPage.clickAcessar();

		assertEquals("Olá Gustavo Freitas,", mainPage.getUserLogged()); 
        assertEquals("Saldo em conta R$ 0,00", mainPage.getSaldo());

    }

    @DisplayName("CN0009 - Cadastro bem-sucedido")
    @Test
    public void cadastroBemSucedido() {
        registerPage.sendEmail("test@test.com");
        registerPage.sendName("Gustavo Freitas");
        registerPage.sendPassword("12345");
        registerPage.sendConfirmationPassword("12345");

        assertTrue(registerPage.toggleIsNotChecked());

        registerPage.clickCadastrar();

    	String message = registerPage.getSuccessMessage();
		assertTrue(message.contains("foi criada com sucesso"));

    }

}
