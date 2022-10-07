/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.cps.Controller;

import com.portfolio.cps.Dto.dtoAcercade;
import com.portfolio.cps.Entity.Acercade;
import com.portfolio.cps.Security.Controller.Mensaje;
import com.portfolio.cps.Service.SAcercade;
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/acercade")
@CrossOrigin(origins = "https://frontend-2002.web.app")

public class CAcercade {
    @Autowired
    SAcercade sAcercade;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Acercade>> list(){
        List<Acercade> list = sAcercade.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Acercade> getById(@PathVariable int id){
       
        Acercade acercade = sAcercade.getOne(id).get();
        return new ResponseEntity(acercade, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        
        sAcercade.delete(id);
        return "La informacion numero "+id+" fue eliminada correctamente";

    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoAcercade dtoacercade){
        if(StringUtils.isBlank(dtoacercade.getNombreA())){
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(sAcercade.existsByNombreA(dtoacercade.getNombreA())){
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        
        Acercade acercade = new Acercade(
                dtoacercade.getNombreA(), 
                dtoacercade.getDescripcionA()
            );
        sAcercade.save(acercade);
        return new ResponseEntity(new Mensaje("Acerca de creado"), HttpStatus.OK);
                
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody dtoAcercade dtoacercade){
        if(!sAcercade.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        if(sAcercade.existsByNombreA(dtoacercade.getNombreA()) && !Objects.equals(sAcercade.getByNombreA(dtoacercade.getNombreA()).get().getId(), id)){
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoacercade.getNombreA())){
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }
        
        Acercade acercade = sAcercade.getOne(id).get();
        
        acercade.setNombreA(dtoacercade.getNombreA());
        acercade.setDescripcionA(dtoacercade.getDescripcionA());
        
        sAcercade.save(acercade);
        
        return new ResponseEntity(new Mensaje("Informacion actualizada"), HttpStatus.OK);
    }
}
