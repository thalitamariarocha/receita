package com.example.receita.controller;

import com.example.receita.model.receita.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ExclusaoReceitaController {

    @Autowired
    private ReceitaRepository receitaRepository;

    @PostMapping("/excluir/{id}")
    public String excluirReceita(@PathVariable Long id) {
        // Verifique se a receita existe antes de excluí-la
        if (receitaRepository.existsById(id)) {
            receitaRepository.deleteById(id);
        }
        return "redirect:/receita/lista"; // Redirecione de volta para a lista de receitas
    }
}
