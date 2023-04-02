package tec.ada.livrariaada.util;

import lombok.Getter;

@Getter
public class ApiFieldError extends ApiError {

    private String nomeDoCampo;

    public ApiFieldError(Integer status, String mensagem) {
        super(status, mensagem);
    }

    public ApiFieldError(Integer status, String mensagem, String nomeDoCampo) {
        super(status, mensagem);
        this.nomeDoCampo = nomeDoCampo;
    }

}
