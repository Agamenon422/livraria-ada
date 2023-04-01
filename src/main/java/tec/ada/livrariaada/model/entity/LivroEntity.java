package tec.ada.livrariaada.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="livro")
@ToString
public class  LivroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="editora",nullable=false)//esse é o nome que vai para a DB;
    private EditoraEntity editora;//P/ esse relacionamento eu não estou pegando o id, e sim a classe inteira;ç
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="categoria",nullable=false)
    private CategoriaEntity categoria;

    @Column(name="nome",nullable=false,unique=true, length = 150)
    private String nome;

    @UpdateTimestamp//anotação do hibernate;
    @Column(name="data_ultima_atualizacao",nullable=false)//esse é o nome que vai para a DB;
    private LocalDateTime dataUltimaAtualizacao;

    @Column(name="isdn",nullable=false,unique=true)
    private String isdn;
}
