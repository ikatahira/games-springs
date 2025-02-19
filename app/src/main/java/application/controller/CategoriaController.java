package application.controller;

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

    


}