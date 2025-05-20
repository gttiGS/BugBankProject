package com.page;

import static com.core.DriverFactory.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;;

public class MainPage {
	
	public String getUserLogged() {		
		WebElement labeLogged = getDriver().findElement(By.id("textName"));
		return labeLogged.getText();		
	}

    public String getSaldo() {            
        WebElement saldo = getDriver().findElement(By.id("textBalance"));
        return saldo.getText().replace('\u00A0', ' ').trim();
    }

    public void btnlogout(){
        WebElement btnlogout = getDriver().findElement(By.id("btnExit"));
        btnlogout.click();
    }
}