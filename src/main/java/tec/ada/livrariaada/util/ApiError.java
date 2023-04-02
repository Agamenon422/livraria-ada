package tec.ada.livrariaada.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApiError {

    private String mensagem;

    private Integer status;

    public ApiError(Integer status, String mensagem) {
        this.status = status;
        this.mensagem = mensagem;
    }

}
