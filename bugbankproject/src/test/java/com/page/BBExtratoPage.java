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
        List<WebElement> transacoes = getDriver().findElements(By.id("textTypeTransaction"));
        List<String> comments = new ArrayList<>();
        for (WebElement transacao : transacoes) {
        comments.add(transacao.getText());
    }   
        return comments;
    }

    public List<String> getComTransaction(){
        List<WebElement> transacoes = getDriver().findElements(By.id("textDescription"));
        List<String> desc = new ArrayList<>();
        for (WebElement transacao : transacoes) {
        desc.add(transacao.getText());
    }   
        return desc;
    }

    public String getwithdrawalColorRed(){
        WebElement valorNegativo = getDriver().findElement(By.cssSelector(".bank-statement__Value-sc-7n8vh8-17.gvXfbP"));
        String redcolor = valorNegativo.getCssValue("color");
        return redcolor;
    }

    public String getwithdrawalColorGreen(){
        WebElement valorPositivo = getDriver().findElement(By.cssSelector(".bank-statement__Value-sc-7n8vh8-17.bbfCaL"));
        String greencolor = valorPositivo.getCssValue("color");
        return greencolor;
    } 

    public List<String> getValTransacaoNegativa(){
        List<WebElement> transacoesNegativas = getDriver().findElements(By.id("textTransferValue"));
        List<String> transfValues = new ArrayList<>();
        for (WebElement transacao : transacoesNegativas) {
        transfValues.add(transacao.getText());
    }   
        return transfValues;
    }


}
