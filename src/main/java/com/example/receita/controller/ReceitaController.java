package com.example.receita.controller;
import java.util.Arrays;
import java.util.List;

import com.example.receita.model.receita.repository.Receita;
import com.example.receita.model.receita.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

@Controller
@RequestMapping({"/receita"})
public class ReceitaController {
    @Autowired
    private ReceitaRepository receitaRepository;


    public ReceitaController() {
    }

    @RequestMapping({"/cadastro"})
    public ModelAndView cadastro() {
        return new ModelAndView("cadastro");
    }

//    @ModelAttribute("todosStatusTitulo")
//    public List<StatusTitulo> todosStatusTitulo() {
//        return Arrays.asList(StatusTitulo.values());
//    }

    @RequestMapping(method = {RequestMethod.POST})
    public ModelAndView salvar(Receita receita) {
        this.receitaRepository.save(receita);
        ModelAndView mv = new ModelAndView("cadastro");
        mv.addObject("mensagem", "Receita salva com sucesso!");
        return mv;
    }



    @GetMapping("/lista")
    public String listarReceitas(Model model) {
        Iterable<Receita> receitas = receitaRepository.findAll();
        model.addAttribute("receitas", receitas);
        return "lista"; // Nome da página Thymeleaf
    }
}


