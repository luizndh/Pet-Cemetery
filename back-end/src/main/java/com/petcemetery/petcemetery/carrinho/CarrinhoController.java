package com.petcemetery.petcemetery.carrinho;

import com.petcemetery.petcemetery.carrinho.dto.ItemCarrinhoDTO;
import com.petcemetery.petcemetery.usuario.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/carrinho")
@RequiredArgsConstructor
public class CarrinhoController {

    private final CarrinhoService carrinhoService;

    @GetMapping("")
    public List<ItemCarrinhoDTO> buscarCarrinho(@AuthenticationPrincipal Usuario usuario){
        return carrinhoService.buscarCarrinho(usuario.getId());
    }


}
