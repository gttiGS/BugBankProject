package com.page;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.core.DriverFactory.getDriver;

import java.time.Duration;

public class BBRegisterPage {
	
	private WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
	
	public BBRegisterPage sendEmail(String email) {
		WebElement tfEmail = getDriver().findElement(By.xpath("//div[@class='card__register']//input[@name='email']"));		
		tfEmail.sendKeys(email);
		return this;
	}
	
	public BBRegisterPage sendName(String name) {
		WebElement tfName = getDriver().findElement(By.name("name"));
		tfName.sendKeys(name);
		return this;
	}
	
	public BBRegisterPage sendPassword(String pass) {
		WebElement tfPass = getDriver().findElement(By.xpath("//div[@class='card__register']//input[@name='password']"));
		tfPass.sendKeys(pass);
		return this;
	}
	
	public BBRegisterPage sendConfirmationPassword(String pass) {
		WebElement tfConfirmationPass = getDriver().findElement(By.name("passwordConfirmation"));
		tfConfirmationPass.sendKeys(pass);		
		return this;
	}
	
	public BBRegisterPage toogleAddBalance() {
		//wait.until(ExpectedConditions.elementToBeClickable(By.id("toggleAddBalance")));
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        WebElement toggle = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("toggleAddBalance")));

    try {
        toggle.click();
    } catch (ElementClickInterceptedException e) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", toggle);
    }
		return this;
	}

    public boolean toggleIsChecked() {
        WebElement toggleElement = getDriver().findElement(By.id("toggleAddBalance"));
        WebElement toggleContainer = toggleElement.findElement(By.xpath("./.."));
        String classes = toggleContainer.getDomAttribute("class");
        return classes.contains("hsmFIT");
    }

        public boolean toggleIsNotChecked() {
        WebElement toggleElement = getDriver().findElement(By.id("toggleAddBalance"));
        WebElement toggleContainer = toggleElement.findElement(By.xpath("./.."));
        String classes = toggleContainer.getDomAttribute("class");
        return classes.contains("kIwoPV");
    }
	
	public String getSuccessMessage() {		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("modalText")));
		
		WebElement modalMessage = getDriver().findElement(By.id("modalText"));
		return modalMessage.getText();
	}

    public String getWarningMessage() {		
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("modalText")));
        
        WebElement modalMessage = getDriver().findElement(By.id("modalText"));
        return modalMessage.getText();
    }
	
	public void closeModalMessage() {
		WebElement btnClose = getDriver().findElement(By.id("btnCloseModal"));
		btnClose.click();		
		
	}
	
	public BBLoginPage clickCadastrar() {
		WebElement btnCadastrar = getDriver().findElement(By.xpath("//button[.='Cadastrar']"));
		btnCadastrar.click();
		return new BBLoginPage();
	}

}