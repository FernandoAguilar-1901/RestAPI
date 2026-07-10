package org.generation.api.dto;

public class PassDto {

    private String passActual;
    private String passNuevo;

    public PassDto(String passActual, String passNuevo) {
        this.passActual = passActual;
        this.passNuevo = passNuevo;
    }// constructor PassDto

    public PassDto() {}// constructor vacío

    public String getPassActual() {
        return passActual;
    }// getPassActual

    public void setPassActual(String passActual) {
        this.passActual = passActual;
    }// setPassActual

    public String getPassNuevo() {
        return passNuevo;
    }// getPassNuevo

    public void setPassNuevo(String passNuevo) {
        this.passNuevo = passNuevo;
    }// setPassNuevo

    @Override
    public String toString() {
        return "PassDto{" +
                "passActual='" + passActual + '\'' +
                ", passNuevo='" + passNuevo + '\'' +
                '}';
    }// toString
}// class PassDto
