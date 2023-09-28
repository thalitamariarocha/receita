package com.example.receita.controller;

import com.example.receita.model.receita.repository.Receita;
import com.example.receita.model.receita.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/editar"})
public class EdicaoReceitaController {

    @Autowired
    private ReceitaRepository receitaRepository;

    @GetMapping("/editar/{id}")
    public String editarReceita(@PathVariable Long id, Model model) {
        Receita receita = receitaRepository.findById(id).orElse(null);
        if (receita == null) {
            // Lide com o caso em que a receita não existe
            return "redirect:/receitas";
        }
        model.addAttribute("receita", receita);
        return "editar-receita"; // Página Thymeleaf de edição
    }

    @RequestMapping(method = {RequestMethod.POST})
    //@PostMapping("/salvar")
    public ModelAndView salvarEdicao(Receita receita) {
        // Valide e salve as alterações no banco de dados
        this.receitaRepository.save(receita);
        ModelAndView mv = new ModelAndView("editar-receita");
        mv.addObject("mensagem", "Receita salva com sucesso!");
        return mv;
    }
}
