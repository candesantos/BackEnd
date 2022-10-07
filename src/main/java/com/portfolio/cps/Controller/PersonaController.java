package com.portfolio.cps.Controller;

import com.portfolio.cps.Entity.Persona;
import com.portfolio.cps.Interface.IPersonaService;
import com.portfolio.cps.Security.Controller.Mensaje;
import java.util.List;
import javax.validation.Valid;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personas")

@CrossOrigin(origins = "http://localhost:4200")

public class PersonaController {
    @Autowired
    IPersonaService ipersonaService;

    @GetMapping("/traer")
    public List<Persona> getPersona() {
        return ipersonaService.getPersona();
    }

    @PostMapping("/crear")
    public ResponseEntity createPersona(@Valid @RequestBody Persona persona) {

        Persona perso = new Persona(
                persona.getNombre(),
                persona.getApellido(),
                persona.getImg()
        );
       
        ipersonaService.savePersona(perso);
        return new ResponseEntity(new Mensaje("La persona fue creada correctamente"), HttpStatus.CREATED);
    }

    @DeleteMapping("/borrar/{id}")
    public String deletePersona(@PathVariable Long id){        
        ipersonaService.deletePersona(id);
        return "La persona de "+id+" fue eliminada correctamente";
    }

    @PutMapping("/editar/{id}")
    public Persona editPersona(@PathVariable Long id,
            @RequestParam("nombre") String nuevoNombre,
            @RequestParam("apellido") String nuevoApellido,
            @RequestParam("img") String nuevoImg) {
        Persona persona = ipersonaService.findPersona(id);

        persona.setNombre(nuevoNombre);
        persona.setApellido(nuevoApellido);
        persona.setImg(nuevoImg);
        persona.setImg(nuevoImg);

        ipersonaService.savePersona(persona);
        
        return persona;
    }

    @GetMapping("/traer/perfil")
    public Persona findPersona() {
        return ipersonaService.findPersona((long) 1);
    }

    private Persona persona(Mensaje mensaje, HttpStatus httpStatus) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}

