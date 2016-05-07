package br.com.haircutter.admin.enums;

/**
 * Created by hfaria on 5/5/16.
 */
public enum WeekdayEnum {
    SUNDAY("Domingo"),
    MONDAY("Segunda-Feira"),
    TUESDAY("Terça-Feira"),
    WEDNESDAY("Quarta-Feira"),
    THURSDAY("Quinta-Feira"),
    FRIDAY("Sexta-Feira"),
    SATURDAY("Sábado");

    private String value;

    WeekdayEnum(String value) {
        this.value = value;
    }

    public String getBundle() {
        return value;
    }
}
