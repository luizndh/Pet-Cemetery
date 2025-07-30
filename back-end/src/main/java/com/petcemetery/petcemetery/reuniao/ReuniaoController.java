package com.petcemetery.petcemetery.reuniao;

import java.util.List;

import com.petcemetery.petcemetery.reuniao.dto.ReuniaoAdminDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.petcemetery.petcemetery.reuniao.dto.ReuniaoDTO;

@RestController
@RequestMapping("/api/reuniao")
public class ReuniaoController {

    @Autowired
    private ReuniaoService reuniaoService;

    // Retorna a lista de todas as reunioes do banco de dados, para serem visualizadas pelo admin, de forma crescente pela data
    @GetMapping("/admin/visualizar")
    public List<ReuniaoAdminDTO> visualizarReunioes() {
        return this.reuniaoService.visualizarReunioes();
    }

    // Salva uma reuniao no banco de dados, com base no cpf do cliente e nos parâmetros que ele escolheu na página (data, horário e assunto)
    // A data deve ser no formato yyyy-MM-dd, e o horário no formato hh:mm, e o assunto deve ser uma String. Deve ser enviado no formato JSON.
    @PostMapping("")
    public boolean agendarReuniao(@RequestHeader("Authorization") String authHeader, @RequestBody ReuniaoDTO reuniao) {
        return this.reuniaoService.agendarReuniao(authHeader.substring(7), reuniao);
    }
}
