package tec.ada.livrariaada.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name="livro")
@ToString
public class  LivroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "{msg.livro_editora_ausente}")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="editora_id",nullable=false)//esse é o nome que vai para a DB;
    private EditoraEntity editora;//P/ esse relacionamento eu não estou pegando o id, e sim a classe inteira;

    @NotNull(message = "{msg.livro_categoria_ausente}")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="categoria_id",nullable=false)
    private CategoriaEntity categoria;

    @NotNull(message = "{msg.livro_nome_ausente}")
    @Column(name="nome",nullable=false,unique=true, length = 150)
    private String nome;

    @NotNull(message = "{msg.livro_isbn_ausente}")
    @Column(name="isbn",nullable=false,unique=true)
    @Size(min = 13, max = 13, message = "o isbn deve ter 13 caracteres")
    private String isbn;

}