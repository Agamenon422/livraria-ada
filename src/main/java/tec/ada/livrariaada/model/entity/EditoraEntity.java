package tec.ada.livrariaada.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@Table(name="editora")
@ToString
public class EditoraEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "editora")
    private List<LivroEntity> livros;

    @OneToMany(mappedBy = "editora")
    private List<CategoriaEntity> categorias;

    @Column(name="nome",nullable=false,unique=true)
    private String nome;

    @Column(name="descricao")
    private String descricao;
}
