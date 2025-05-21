package com.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.core.DriverFactory.getDriver;

import java.time.Duration;


public class BBTransferPage {


    private WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

    public BBTransferPage preencherNumeroConta(String numeroConta) {
        WebElement campoNumeroConta = getDriver().findElement(By.name("accountNumber"));
        campoNumeroConta.sendKeys(numeroConta);
        return this;
    }

    public BBTransferPage preencherDigitoConta(String digitoConta) {
        WebElement campoDigitoConta = getDriver().findElement(By.name("digit"));
        campoDigitoConta.sendKeys(digitoConta);
        return this;
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
		WebElement btnClose = getDriver().findElement(By.id("btnCloseModal"));
		btnClose.click();				
	}
    

}
