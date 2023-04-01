package tec.ada.livrariaada.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tec.ada.livrariaada.model.dto.LivroDTO;
import tec.ada.livrariaada.model.dto.MensagemDTO;
import tec.ada.livrariaada.service.LivroService;

import javax.lang.model.type.IntersectionType;

@RestController//diz que é controle de rest;
@RequestMapping("/livros")//endpoint (rota);
@Slf4j//serve para imprimir várias coisas, coloca a variável log p ser usada;
public class LivroController {
    /*Controla as entradas e saidas dos dados, não é para ter a lógica de négocio;*/
    @Autowired
    private LivroService livroService;

    @GetMapping("/categoria/{idCategoria}")
    public ResponseEntity<Object>PegarPorCategoria(@PathVariable Integer idCategoria){
        try {
            return ResponseEntity.ok(livroService.listarPorCategoria(idCategoria));
        }
        catch (Exception ex){
            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(ex.getMessage()));
        }
    }

    @GetMapping("/categoria/{idEditora}")
    public ResponseEntity<Object>PegarPorEditora(@PathVariable Integer idEditora){
        try {
            return ResponseEntity.ok(livroService.listarPorEditora(idEditora));
        }
        catch (Exception ex){
            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(ex.getMessage()));
        }

    }

    @GetMapping
    public ResponseEntity<Object> listar(){
        try{
            return  ResponseEntity.ok(livroService.listar());
        }
        catch (Exception ex){
            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)//retrornar erro 400, pode ser q/ foi esquecido de passar um como parâmetros faltantes ou incorretos;
                    .body(new MensagemDTO(ex.getMessage()));
        }
    }


    @PostMapping
    public ResponseEntity<Object> criar(
                                    @RequestBody
                                    @Valid LivroDTO livroDTO){

        try{
            return ResponseEntity.status(HttpStatus.CREATED)//O status 201 é utilizado para indicar que a requisição foi bem sucedida e um novo recurso foi criado no servidor, é utilizada em controller;
                    .body(livroService.criar(livroDTO));
        }
        catch (Exception ex){
            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(ex.getMessage()));
        }
    }


    @PutMapping("{id}")
    public ResponseEntity<Object> editar(
            @RequestBody
            @Valid LivroDTO livroDTO,
            @PathVariable("id") Integer id){
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(livroService.editar(livroDTO, id));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(ex.getMessage()));
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> pegarUm(@PathVariable("id")Integer id){
        try{
            return  ResponseEntity.ok(livroService.pegarPorId(id));
        }
        //fazendo um multicatch;
        catch (EntityNotFoundException ex){
            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)//retrornar erro 204, diz que foi bem sucedido mas não há conteúdo para retornar na resposta, pq n achou pelo id, pq ainda n tem.
                    .body(new MensagemDTO(ex.getMessage()));
        }
        catch (Exception ex){
            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(ex.getMessage()));
        }
    }



    @DeleteMapping("{id}")
    public ResponseEntity<Object> deletar(@PathVariable("id") Integer id){
        try {
            livroService.deletar(id);
            return ResponseEntity
                    .ok(new MensagemDTO("Livro com id"+id+" deletado com sucesso!"));
        }
        catch (EntityNotFoundException ex) {

            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MensagemDTO(ex.getMessage()));
        }
        catch (Exception ex){
            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(ex.getMessage()));
        }
    }
}
