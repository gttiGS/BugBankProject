package com.page;

import static com.core.DriverFactory.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BBLoginPage {
	
	public BBLoginPage open(String url) {
		getDriver().get(url);		
		return this;
	}

    public String initialText(){
        WebElement h1inittxt = getDriver().findElement(By.xpath("//*[@id=\"__next\"]/div/div[1]/h1"));
        return h1inittxt.getText();       
    }
	
	public BBRegisterPage clickRegister() {
		WebElement btnRegistrar = getDriver().findElement(By.xpath("//button[.='Registrar']"));
		btnRegistrar.click();
		return new BBRegisterPage();
	}
	
	public BBLoginPage sendEmail(String email){
		WebElement tfEmail = getDriver().findElement(By.xpath("//div[@class='card__login']//input[@name='email']"));
		tfEmail.sendKeys(email);
		return this;
	}
	
	public BBLoginPage sendPassword(String pass) {
		WebElement tfPass = getDriver().findElement(By.xpath("//div[@class='card__login']//input[@name='password']"));
		tfPass.sendKeys(pass);
		return this;
	}
	
	public MainPage clickAcessar() {
		WebElement btnRegistrar = getDriver().findElement(By.xpath("//button[.='Acessar']"));
		btnRegistrar.click();
		
		return new MainPage();
	}

    public String getWarningMessage() {		
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("modalText")));
        
        WebElement modalMessage = getDriver().findElement(By.id("modalText"));
        return modalMessage.getText();
    }



}
