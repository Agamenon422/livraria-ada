package tec.ada.livrariaada.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MensagemDTO {
    //criando um JSON de msn, passando msn de erro, na classe que crie (MensagemDTO), poderia colocar horário tbm;
    private String mensagem;
}
