package tec.ada.livrariaada.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import tec.ada.livrariaada.model.entity.CategoriaEntity;
import tec.ada.livrariaada.model.entity.EditoraEntity;
@Data
public class LivroDTO {
/*Em geral, uma classe DTO deve conter apenas atributos e métodos que se relacionem diretamente
com os dados que ela representa. Ela não deve conter lógica de negócio ou realizar operações complexas.
Os dados dentro da classe DTO devem ser acessados por meio de métodos getters e setters.*/
    private Integer id;

    private EditoraEntity editora;
    private CategoriaEntity categoria;

    @Size(message = "Nome acima do permitido!")
    @NotBlank(message = "Nome deve conter alum valor")
    private String nome;
    @Size(min = 13, max = 13,message="Tamanho do isdn ácima do permitido!")
    @NotBlank(message="Isbn deve conter um valor de 13 dígitos!")
    private String isdn;
}
