/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portfolio.cps.Repository;

import com.portfolio.cps.Entity.Acercade;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RAcercade extends JpaRepository <Acercade, Integer> {
    public Optional <Acercade> findByNombreA (String nombreA);
    public boolean existsByNombreA (String nombreA);
}
