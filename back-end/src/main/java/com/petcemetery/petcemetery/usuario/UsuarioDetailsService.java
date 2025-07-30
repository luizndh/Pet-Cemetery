package com.petcemetery.petcemetery.usuario;

import com.petcemetery.petcemetery.usuario.admin.AdminService;
import com.petcemetery.petcemetery.usuario.cliente.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioDetailsService implements UserDetailsService {

    private final ClienteService clienteService;
    private final AdminService adminService;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = clienteService.findByEmail(email);
        usuario = (usuario != null) ? usuario : adminService.findByEmail(email);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado com o email: " + email);
        }

        return usuario;
    }
}
