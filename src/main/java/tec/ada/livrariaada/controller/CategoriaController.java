package tec.ada.livrariaada.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import tec.ada.livrariaada.model.dto.CategoriaDTO;
import tec.ada.livrariaada.model.dto.MensagemDTO;
import tec.ada.livrariaada.model.entity.CategoriaEntity;
import tec.ada.livrariaada.service.CategoriaService;

@RestController//diz que é controle de rest;
@RequestMapping("/categorias")//endpoint (rota);
@Slf4j//serve para imprimir várias coisas, coloca a variável log p ser usada, biblioteca de log;
public class CategoriaController {

    /*Controla as entradas e saidas dos dados, não é para ter a lógica de négocio;*/

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<Object> listar() {/*ResponseEntity -> passa uma resposta mais completa, com mais dados em questão de requisição;*/
        try {
            return ResponseEntity.ok(categoriaService.listar());//o 'ok()' diz que a requisição deu certo, ele retorna 200, dentro dele (vem a respostra que vai conter no body) coloco a resposta q vai conter no dob;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            //ex.printStackTrace();->imprime toda a cadeia de erros, caso eu queria;
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(ex.getMessage()));
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> pegarUm(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(categoriaService.pegarPorId(id));
        }
        //fazendo um multiCatch e deixando o mais gênerico SEMPRE por último;
        catch (EntityNotFoundException ex) {//mais espefífico;
            //caso n for esse vai cair no debaixo;
            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)//204 diz que n tem conteúdo,ñ é especificamente um erro;
                    .body(new MensagemDTO(ex.getMessage()));

        } catch (Exception ex) {//em um multicatch sempre deixe a exerção mais genêrica por último;
            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)//BAD_REQUEST -> p erro geral;
                    .body(new MensagemDTO(ex.getMessage()));
        }
    }


    @PostMapping
    public ResponseEntity<Object> criar(@RequestBody
                                        @Valid/*referente a validação da 'CategoriaDTO'*/
                                                CategoriaEntity categoriaEntity) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)//passando o status(HttpStatus) como (.CREATED);
                    .body(categoriaService.criar(categoriaEntity));//retorno da criação;

        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(ex.getMessage()));
        }

    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> editar(
            @RequestBody @Valid CategoriaDTO categoriaDTO,
            @PathVariable("id") Long id) {

        try {
            return ResponseEntity.ok(
                    categoriaService.editar(categoriaDTO, id));

        } catch (EntityNotFoundException ex) {

            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)//erro 404 n foi encontrado;
                    .body(new MensagemDTO(ex.getMessage()));

        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(ex.getMessage()));
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(
            @PathVariable("id") Long id) {

        try {
            categoriaService.deletar(id);
            return ResponseEntity
                    .ok(new MensagemDTO("Categoria com id " + id + " removido com sucesso!"));

        } catch (EntityNotFoundException ex) {

            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MensagemDTO(ex.getMessage()));

        }//criando um JSON de msn, passando msn de erro, na classe que crie (MensagemDTO), poderia colocar horário tbm;
        catch (Exception ex) {

            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(ex.getMessage()));
        }
    }
}
