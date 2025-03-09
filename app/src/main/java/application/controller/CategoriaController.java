package application.controller;
import application.repository.CategoriaRepository;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResquestMethod;
import org.springframework.web.bind.annotation.RequestMapping;

import application.model.Categoria;
import application.repository.CategoriaRepository


@Controller
@RequestMapping("/categoria")
public class CategoriaRepository{

    @Autowired
    private CategoriaRepository categoriaRepo;


    @RequestMapping("/list")
    public String list(Model ui){
        ui.addAttribute("categorias", categoriaRepo.findAll());
        return "categoria/list";
    }

    @RequestMapping("/insert")
    public String insert(){
        return "categoria/insert";
    }

    @RequestMapping(value= "/insert", method = ResquestMethod.POST)
    public String insert (@RequestParam("nome") String nome){
        Categoria categoria = new Categoria();
        categoria.setNome(nome);
        
        categoriaRepo.save(categoria);
        return "redirect:/categoria/list";
    }

    @RequestMapping("/update")
    public String update(
        @RequestParam("id") long id
        Model ui){
        
            Optional<Categoria> categoria = categoriaRepo.findById(id);

            if(categoria.isPresent()){
                ui.addAttribute("categoria", categoria.get());
                return "categoria/update";
            }
            return "redirect:/categoria/list";
        }
    
    @RequestMapping(value = "/update", method = ResquestMethod.POST)
    public Strint updata(
        @RequestParam("id") long id,
        @RequestParam("nome") String nome){
            Optional<Categoria> categoria = new categoriaRepo.findById(id);

            if(categoria.isPresent()){
                categoria.get().setNome(nome);

                
            }
        }
    }
     



}