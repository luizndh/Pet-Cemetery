package com.petcemetery.petcemetery.services;

import com.petcemetery.petcemetery.model.Usuario;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final ClienteService clienteService;
    private final AdminService adminService;

    public UsuarioDetailsService(ClienteService clienteService, AdminService adminService) {
        this.clienteService = clienteService;
        this.adminService = adminService;
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Usuario usuario = clienteService.findById(Long.valueOf(id));
        usuario = (usuario != null) ? usuario : adminService.findById(Long.valueOf(id));

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado com o id: " + id);
        }

        return usuario;
    }
}
