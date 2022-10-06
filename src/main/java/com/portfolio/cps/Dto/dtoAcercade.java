/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.cps.Dto;

import javax.validation.constraints.NotBlank;

public class dtoAcercade {
    @NotBlank
   private String nombreA;
    @NotBlank
   private String descripcionA; 

    public dtoAcercade() {
    }

    public dtoAcercade(String nombreA, String descripcionA) {
        this.nombreA = nombreA;
        this.descripcionA = descripcionA;
    }

    public String getNombreA() {
        return nombreA;
    }

    public void setNombreA(String nombreA) {
        this.nombreA = nombreA;
    }

    public String getDescripcionA() {
        return descripcionA;
    }

    public void setDescripcionA(String descripcionA) {
        this.descripcionA = descripcionA;
    }
    
    
}