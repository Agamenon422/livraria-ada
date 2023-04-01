package tec.ada.livrariaada.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data//anotação do lombok;
public class CategoriaDTO {

    private Integer id;
    @Size(max=80,message="Tamanho do nome ácima do permitido")
    @NotBlank(message="Nome deve conter algum valor")
    /*P/ q/ essas validações de cima funcionem eu tenho que suar o '@Valid' no método criar na 'CategoriaController';*/
    private String nome;
    private String descricao;


}
