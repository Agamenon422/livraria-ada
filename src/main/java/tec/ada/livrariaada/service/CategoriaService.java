package tec.ada.livrariaada.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

import tec.ada.livrariaada.model.dto.CategoriaDTO;
import tec.ada.livrariaada.model.entity.CategoriaEntity;
import tec.ada.livrariaada.model.mapper.CategoriaMapper;
import tec.ada.livrariaada.model.repository.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;
    @Autowired
    private CategoriaMapper mapper;

    public List<CategoriaDTO> listar() {
        // Busca todas as entidades CategoriaEntity no banco de dados.
        List<CategoriaEntity> listaEntities =  repository.findAll();
        // Converte a lista de CategoriaEntity em uma lista de CategoriaDTO e retorna a lista resultante.
        return mapper.updateListDTO(listaEntities);
    }


    public CategoriaDTO pegarPorId(Integer id) {// Este método recupera um objeto CategoriaDTO pelo seu ID de um repositório de dados
        // Recupera o objeto CategoriaEntity do repositório, caso exista;
        Optional<CategoriaEntity> categoriaEntityOp = repository.findById(id);//o findById(nativo) q já vem dentro 'JpaRepository' ele vem um optional<>, pq ele pode ou n encontrar a categoria;
        //Verifica se o objeto CategoriaEntity está presente
        if(categoriaEntityOp.isPresent()) {
            // Se o objeto CategoriaEntity estiver presente, o recupera e converte-o para um objeto CategoriaDTO usando um mapper
            CategoriaEntity categoriaEntity = categoriaEntityOp.get();
            return mapper.update(categoriaEntity);
        }
        // Se o objeto CategoriaEntity não estiver presente, lança uma EntityNotFoundException com uma mensagem
        throw new EntityNotFoundException("Categoria não encontrada!");//essa msn é chamanda ma MensagemDTO na controller;
    }


    public CategoriaDTO criar(CategoriaDTO categoriaDTO) {
        // Converte o DTO em uma entidade para persistir no banco de dados
        CategoriaEntity categoria = mapper.update(categoriaDTO);
        // Salva a entidade no banco de dados
        categoria = repository.save(categoria);
        // Converte a entidade persistida em um DTO para retornar na resposta
        return mapper.update(categoria);
    }


    public CategoriaDTO editar(CategoriaDTO categoriaDTO, Integer id) {
        if(repository.existsById(id)) {//verifica se existe uma entidade para o id;

            CategoriaEntity categoriaEntity = mapper.update(categoriaDTO);
            categoriaEntity.setId(id);//para que eu tenha a garantia que p id está correto;
            categoriaEntity = repository.save(categoriaEntity);//salvo a alteração;

            return mapper.update(categoriaEntity);//retorno a categoriaEntity atualizada;
        }
        throw new EntityNotFoundException("Categoria não encontrada!");
    }


    public void deletar(Integer id){
        Optional<CategoriaEntity> categoriaEntityOp =
                repository.findById(id);

        if(categoriaEntityOp.isPresent()) {
            CategoriaEntity categoriaEntity = categoriaEntityOp.get();
            repository.delete(categoriaEntity);
            return;//só para parar a execução pq ele é void, então n retorna nada;
        }
        throw new EntityNotFoundException("Categoria não encontrada!");
    }
}

