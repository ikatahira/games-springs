package application.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;




@Entity
@Table(name = "jogos")
public class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = true)
    private String titulo;

    @ManyToOne
    @JoinColumn (name = "id_genero")
    private Categoria categoria;


    @ManyToMany
    @JoinTable(
        name ="jogos_possuem_plataformas",
        joinColumns = @JoinColumn (name="id_jogos"),
        inverseJoinColumns = @JoinColumn(name = "id_plataforma"))
    private Set<Plataforma> plataformas = new HashSet<>();



public void setId(long id) {
    this.id = id;
}
public long getId() {
    return id;
}
public void setPlataformas(Set<Plataforma> plataformas) {
    this.plataformas = plataformas;
}
public Set<Plataforma> getPlataformas() {
    return plataformas;
}
public void setTitulo(String titulo) {
    this.titulo = titulo;
}
public String getTitulo() {
    return titulo;
}
    
}
