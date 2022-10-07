package com.portfolio.cps.Security.Controller;

import com.portfolio.cps.Security.Dto.LoginUsuario;
import com.portfolio.cps.Security.Dto.NuevoUsuario;
import com.portfolio.cps.Security.Entity.Usuario;
import com.portfolio.cps.Security.Service.UsuarioService;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "https://candesantos-956ad.web.app")

public class AuthController {

    @Autowired
    PasswordEncoder PasswordEncoder;
    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/nuevo")
    public ResponseEntity<?> NuevoUsuario(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {
        if (usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario())) {
            return new ResponseEntity(new Mensaje("Ese nombre de usuario ya existe"), HttpStatus.BAD_REQUEST);
        }
        if (usuarioService.existsByEmail(nuevoUsuario.getEmail())) {
            return new ResponseEntity(new Mensaje("Ese email ya existe"), HttpStatus.BAD_REQUEST);
        }
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(new Mensaje("Campos mal puestos o email invalido"), HttpStatus.BAD_REQUEST);
        }

        Usuario user = new Usuario(
                nuevoUsuario.getNombre(),
                nuevoUsuario.getNombreUsuario(),
                nuevoUsuario.getEmail(),
                //PasswordEncoder.encode(nuevoUsuario.getPassword())
                nuevoUsuario.getPassword()
        );
        usuarioService.save(user);

        return new ResponseEntity(new Mensaje("Usuario guardado"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> LoginUsuario(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {

        String user = usuarioService.getByNombreUsuario(loginUsuario.getNombreUsuario()).get().getNombreUsuario();
        String password = usuarioService.getByNombreUsuario(loginUsuario.getNombreUsuario()).get().getPassword();

        if ((user == null ? loginUsuario.getNombreUsuario() == null : user.equals(loginUsuario.getNombreUsuario()))
                && (password == null ? loginUsuario.getPassword() == null : password.equals(loginUsuario.getPassword()))) {
            return new ResponseEntity(new Mensaje("true"), HttpStatus.OK);

        }
        return new ResponseEntity(new Mensaje("Usuario ó Password no coinciden"), HttpStatus.OK);
    }
}
