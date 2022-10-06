/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.cps.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity 
public class Acercade {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int id;
    private String nombreA;
    private String descripcionA;

    public Acercade() {
    }

    public Acercade(String nombreA, String descripcionA) {
        this.nombreA = nombreA;
        this.descripcionA = descripcionA;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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