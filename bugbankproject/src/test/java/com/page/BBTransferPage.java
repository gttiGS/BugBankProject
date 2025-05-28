package com.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.NoSuchElementException;

import static com.core.DriverFactory.getDriver;

import java.time.Duration;


public class BBTransferPage {

    private WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
    private WebElement campoNumeroConta;
    private WebElement campoDigitoConta;

    public BBTransferPage preencherNumeroConta(String numeroConta) {
        this.campoNumeroConta = getDriver().findElement(By.name("accountNumber"));
        this.campoNumeroConta.sendKeys(numeroConta);
        return this;
    }

    public BBTransferPage preencherDigitoConta(String digitoConta) {
        this.campoDigitoConta = getDriver().findElement(By.name("digit"));
        this.campoDigitoConta.sendKeys(digitoConta);
        return this;
    }

    public WebElement getCampoNumeroConta() {
        return campoNumeroConta;
    }

    public WebElement getCampoDigitoConta() {
        return campoDigitoConta;
    }

    public BBTransferPage sendValorTransf(String valortransf){
        WebElement valor = getDriver().findElement(By.name("transferValue"));
        valor.sendKeys(valortransf);
        return this;        
    }

    public BBTransferPage sendDescricao(String desc){
        WebElement tfdesc = getDriver().findElement(By.name("description"));
        tfdesc.sendKeys(desc);
        return this;
    }

    public BBTransferPage btnTransf(){
        WebElement clckbtntransf = getDriver().findElement(By.xpath("//*[@id='__next']/div/div[3]/form/button"));
        clckbtntransf.click();
        return this;
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
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("modalText")));
        
		WebElement btnClose = getDriver().findElement(By.id("btnCloseModal"));
		btnClose.click();				
	}

    public boolean isInputWarningDisplayed() {
    try {
        WebElement warningElement = getDriver().findElement(By.className("input__warning"));
        String warningText = warningElement.getText();
        return !warningText.trim().isEmpty();
    } catch (NoSuchElementException e) {
        return false;
    }
    }
    
    public void btnBack(){
        WebElement btnBack = getDriver().findElement(By.id("btnBack"));
        btnBack.click();
    }

}
