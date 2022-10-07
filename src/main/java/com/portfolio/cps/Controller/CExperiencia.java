/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portfolio.cps.Controller;

import com.portfolio.cps.Dto.dtoExperiencia;
import com.portfolio.cps.Entity.Experiencia;
import com.portfolio.cps.Security.Controller.Mensaje;
import com.portfolio.cps.Service.SExperiencia;
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
@RequestMapping("/explab")
@CrossOrigin(origins = "https://candesantos-956ad.web.app")

public class CExperiencia {
    @Autowired
    SExperiencia sExperiencia;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Experiencia>> list(){
        List<Experiencia> list = sExperiencia.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Experiencia> getById(@PathVariable Long id){
        Experiencia experiencia = sExperiencia.getOne(id).get();
        return new ResponseEntity(experiencia, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        
        sExperiencia.delete(id);
        return "La experiencia numero "+id+" fue eliminada correctamente";
    }

    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoExperiencia dtoexp){      
        if(StringUtils.isBlank(dtoexp.getNombreE()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(sExperiencia.existsByNombreE(dtoexp.getNombreE()))
            return new ResponseEntity(new Mensaje("Esa experiencia existe"), HttpStatus.BAD_REQUEST);
        
        Experiencia experiencia = new Experiencia(
                dtoexp.getNombreE(), 
                dtoexp.getDescripcionE()
        );
        sExperiencia.save(experiencia);        
        return new ResponseEntity(new Mensaje("Experiencia agregada"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody dtoExperiencia dtoexp){
        //Validamos si existe el ID
        if(!sExperiencia.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        //Compara nombre de experiencias
        if(sExperiencia.existsByNombreE(dtoexp.getNombreE()) && !Objects.equals(sExperiencia.getByNombreE(dtoexp.getNombreE()).get().getId(), id))
            return new ResponseEntity(new Mensaje("Esa experiencia ya existe"), HttpStatus.BAD_REQUEST);
        //No puede estar vacio
        if(StringUtils.isBlank(dtoexp.getNombreE()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        
        Experiencia experiencia = sExperiencia.getOne(id).get();
        experiencia.setNombreE(dtoexp.getNombreE());
        experiencia.setDescripcionE((dtoexp.getDescripcionE()));
        
        sExperiencia.save(experiencia);
        return new ResponseEntity(new Mensaje("Experiencia actualizada"), HttpStatus.OK);
             
    }
}
