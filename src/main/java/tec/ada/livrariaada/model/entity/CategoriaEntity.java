package tec.ada.livrariaada.model.entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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

    @OneToMany(mappedBy = "categoria")
    private List<LivroEntity> livros;

    @Column(name="nome",nullable=false,unique=true)
    private String nome;

    @Column(name="descricao")
    private String descricao;
}