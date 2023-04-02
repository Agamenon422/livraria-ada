package tec.ada.livrariaada.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tec.ada.livrariaada.model.dto.EditoraDTO;
import tec.ada.livrariaada.model.dto.MensagemDTO;
import tec.ada.livrariaada.model.entity.EditoraEntity;
import tec.ada.livrariaada.service.EditoraService;

import java.util.OptionalDouble;

@RestController//diz que é controle de rest;
@RequestMapping("/editoras")//endpoint (rota);
@Slf4j//serve para imprimir várias coisas, coloca a variável log p ser usada;
public class EditoraController {
    /*Controla as entradas e saidas dos dados, não é para ter a lógica de négocio;*/

    @Autowired
    private EditoraService editoraService;

    @GetMapping
    public ResponseEntity<Object> listar(){
        try{
            return ResponseEntity.ok(editoraService.listar());

        }
        catch (Exception ex){

            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)//retrornar erro 400, pode ser q/ foi esquecido de passar um como parâmetros faltantes ou incorretos;
                    .body(new MensagemDTO(ex.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> pegarUm(@PathVariable("id") Long id){
        try{
            return ResponseEntity.ok(editoraService.pegarPorId(id));

        }
        catch (EntityNotFoundException ex){

            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)//retrornar erro 204, diz que foi bem sucedido mas não há conteúdo para retornar na resposta, pq n achou pelo id, pq ainda n tem.
                    .body(new MensagemDTO(ex.getMessage()));
        }
        catch (Exception ex){

            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)//retrornar erro 400, poder ser q/ foi esquecido de passar um ex.: como parâmetros faltantes ou incorretos;
                    .body(new MensagemDTO(ex.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<Object> criar(@RequestBody @Valid EditoraEntity editoraEntity){
        try{
            return ResponseEntity.status(HttpStatus.CREATED)//O status 201 é utilizado para indicar que a requisição foi bem sucedida e um novo recurso foi criado no servidor, é utilizada em controller;
                    .body(editoraService.criar(editoraEntity));
        }
        catch (Exception ex){

            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(ex.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> editar(
            @RequestBody
            @Valid EditoraDTO editoraDTO,
            @PathVariable("id") Long id){
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(editoraService.editar(editoraDTO, id));
        }
        catch (Exception ex){

            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(ex.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable("id") Long id){
        try {
            editoraService.deletar(id);
            return ResponseEntity
                    .ok(new MensagemDTO("Editora com id "+id+" deletada com sucesso!"));
        }
        catch (EntityNotFoundException ex) {

            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)//indica erro de status 404, que o servidor não conseguiu encontrar o recurso solicitado na requisição, isso pode ocorrer se o cliente solicita algo que n não existe em um site ou um registro que não existe em um banco de dados.;
                    .body(new MensagemDTO(ex.getMessage()));

        }//criando um JSON de msn, passando msn de erro, na classe que crie (MensagemDTO), poderia colocar horário tbm;
        catch (Exception ex){

            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(ex.getMessage()));
        }
    }

}
