package tec.ada.livrariaada.model.entity;



import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@Table(name="categoria")
@ToString
public class CategoriaEntity {

    @Id//identifica (como chave primária) o id da entidade;
    @GeneratedValue(strategy = GenerationType.IDENTITY)//incrementa de forma automática;
    private Integer id;


    @OneToMany(mappedBy = "categoria")//o 'mappedBy' diz assim: qual na minha outra entidade que tem essa relação, nesse caso é a 'editora';
    private List<LivroEntity> livros;

    @Column(name="nome",nullable=false,unique=true)
    private String nome;

    @Column(name="descricao")
    private String descricao;

    @Column(name="ativo")
    private Boolean ativo;//deleção lógica;
}