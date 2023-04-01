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

    @OneToMany(mappedBy = "editora")//o 'mappedBy' diz assim: qual na minha outra entidade que tem essa relação, nesse caso é a 'editora';
    private List<LivroEntity> livros;


    @Column(name="nome",nullable=false,unique=true)
    private String nome;

    @Column(name="descricao")
    private String descricao;

    @Column(name="ativo")
    private Boolean ativo;
}
