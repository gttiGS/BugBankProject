package com.page;

import static com.core.DriverFactory.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class BBExtratoPage {

    public String getSaldoDisponivel(){
        WebElement saldoDisponivel = getDriver().findElement(By.id("textBalanceAvailable"));
        return saldoDisponivel.getText();
    }

    public String getDateTransaction(){
        WebElement dateTransaction = getDriver().findElement(By.id("textDateTransaction"));
        return dateTransaction.getText();
    }

    public List<String> getTypeTransaction(){
        List<WebElement> transacoes = getDriver().findElements(
            By.cssSelector(".bank-statement__TypeTransaction-sc-7n8vh8-16.bhJVVL")
        );

        List<String> comments = new ArrayList<>();
        for (WebElement transacao : transacoes) {
        comments.add(transacao.getText());
    }   

        return comments;
    }

    public String getComTransaction(){
        WebElement txtTransaction = getDriver().findElement(By.id("textDescription"));
        return txtTransaction.getText();
    }

}
