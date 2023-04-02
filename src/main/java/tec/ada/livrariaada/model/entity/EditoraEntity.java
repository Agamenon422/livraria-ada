package tec.ada.livrariaada.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;


@Data
@Entity
@Table(name="editora")
@ToString
public class EditoraEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nome",nullable=false,unique=true)
    private String nome;

    @Column(name="descricao")
    private String descricao;

    @OneToMany(mappedBy = "editora", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<LivroEntity> livros;
}
