package com.jsfcourse.calc;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

@Named
@RequestScoped
public class CalcBB {
    private String x;  // kwota kredytu
    private String y;  // liczba miesięcy
    private String z;  // oprocentowanie
    private Double result;

    @Inject
    FacesContext ctx;

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getZ() {
        return z;
    }

    public void setZ(String z) {
        this.z = z;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public boolean doTheMath() {
        try {
            double P = Double.parseDouble(this.x);  
            int n = Integer.parseInt(this.y);      
            double annualRate = Double.parseDouble(this.z);

            double r = annualRate / 100 / 12;

            if (r > 0) {
                result = (P * r * Math.pow(1 + r, n)) / (Math.pow(1 + r, n) - 1);
            } else {
                result = P / n; 
            }

            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana poprawnie", null));
            return true;
        } catch (Exception e) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd podczas przetwarzania parametrów", null));
            return false;
        }
    }

    public String calc() {
        if (doTheMath()) {
            return "showresult";
        }
        return null;
    }

    public String info() {
        return "info";
    }
}
