package com.petcemetery.petcemetery.controller;

import com.petcemetery.petcemetery.dto.ItemCarrinhoDTO;
import com.petcemetery.petcemetery.dto.VisualizarDespesasDTO;
import com.petcemetery.petcemetery.model.Usuario;
import com.petcemetery.petcemetery.services.CarrinhoService;
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
