/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portfolio.cps.Security.Service;

import com.portfolio.cps.Security.Entity.Usuario;
import com.portfolio.cps.Security.Repository.iUsuarioRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    iUsuarioRepository iusuarioRepository;

    public Optional<Usuario> getByNombreUsuario(String nombreUsuario) {
        return iusuarioRepository.findByNombreUsuario(nombreUsuario);
    }

    public boolean existsByNombreUsuario(String nombreUsuario) {
        return iusuarioRepository.existsByNombreUsuario(nombreUsuario);
    }

    public boolean existsByEmail(String email) {
        return iusuarioRepository.existsByEmail(email);
    }

    public boolean existsByPassword(String password) {
        return iusuarioRepository.existsByPassword(password);
    }

    public void save(Usuario usuario) {
        iusuarioRepository.save(usuario);
    }

    public List<Usuario> list(){
        return iusuarioRepository.findAll();
    }
}
