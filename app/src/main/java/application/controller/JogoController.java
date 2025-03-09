package application.controller;

import application.model.Jogo;
import application.repository.JogoRepository;
import application.model.Categoria;
import application.repository.CategoriaRepository;
import application.model.Plataforma;
import application.repository.PlataformaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/jogos") // Mapeamento base para todas as requisições deste controller
public class JogoController {

    @Autowired
    private JogoRepository jogoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private PlataformaRepository plataformaRepository;

    @GetMapping("/list")
    public String listJogos(Model model) {
        List<Jogo> jogos = jogoRepository.findAll();
        model.addAttribute("jogos", jogos);
        return "jogos/list"; // Retorna o nome da view (jogos/list.html)
    }

    @GetMapping("/add")
    public String addJogoForm(Model model) {
        model.addAttribute("jogo", new Jogo()); // Adiciona um objeto Jogo vazio para o formulário
        List<Categoria> categorias = categoriaRepository.findAll();
         List<Plataforma> plataformas = plataformaRepository.findAll();
        model.addAttribute("categorias", categorias);
         model.addAttribute("plataformas", plataformas);
        return "jogos/add"; // Retorna o nome da view (jogos/add.html)
    }

    @PostMapping("/add")
    public String addJogoSubmit(@ModelAttribute Jogo jogo, @RequestParam("categoriaId") long categoriaId, @RequestParam(value = "plataformaIds", required = false) List<Long> plataformaIds) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(categoriaId);
                
        if (categoriaOptional.isPresent()) {
            Categoria categoria = categoriaOptional.get();
            jogo.setCategoria(categoria);
        } else {
           return "redirect:/jogos/add?error=CategoriaNaoEncontrada";
        }
         if (plataformaIds != null) {
            Set<Plataforma> plataformas = new HashSet<>();
            for (Long plataformaId : plataformaIds) {
                Optional<Plataforma> plataformaOptional = plataformaRepository.findById(plataformaId);
                if (plataformaOptional.isPresent()) {
                    plataformas.add(plataformaOptional.get());
                }
            }
            jogo.setPlataformas(plataformas);
        }

        jogoRepository.save(jogo);
        return "redirect:/jogos/list"; // Redireciona para a lista de jogos
    }


    @GetMapping("/edit/{id}")
    public String editJogoForm(@PathVariable("id") long id, Model model) {
        Jogo jogo = jogoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de jogo inválido:" + id));
        model.addAttribute("jogo", jogo);

         List<Categoria> categorias = categoriaRepository.findAll();
         List<Plataforma> plataformas = plataformaRepository.findAll();
        model.addAttribute("categorias", categorias);
         model.addAttribute("plataformas", plataformas);

        return "jogos/edit"; // Retorna o nome da view (jogos/edit.html)
    }

    @PostMapping("/update/{id}")
    public String updateJogo(@PathVariable("id") long id, @ModelAttribute Jogo jogo, @RequestParam("categoriaId") long categoriaId, @RequestParam(value = "plataformaIds", required = false) List<Long> plataformaIds) {
       Optional<Categoria> categoriaOptional = categoriaRepository.findById(categoriaId);
                
        if (categoriaOptional.isPresent()) {
            Categoria categoria = categoriaOptional.get();
            jogo.setCategoria(categoria);
        } else {
           return "redirect:/jogos/add?error=CategoriaNaoEncontrada";
        }
         if (plataformaIds != null) {
            Set<Plataforma> plataformas = new HashSet<>();
            for (Long plataformaId : plataformaIds) {
                Optional<Plataforma> plataformaOptional = plataformaRepository.findById(plataformaId);
                if (plataformaOptional.isPresent()) {
                    plataformas.add(plataformaOptional.get());
                }
            }
            jogo.setPlataformas(plataformas);
        }
        jogoRepository.save(jogo);
        return "redirect:/jogos/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteJogo(@PathVariable("id") long id) {
        Jogo jogo = jogoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de jogo inválido:" + id));
        jogoRepository.delete(jogo);
        return "redirect:/jogos/list";
    }
}