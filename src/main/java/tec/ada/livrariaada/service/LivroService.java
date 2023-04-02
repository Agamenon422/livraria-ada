package tec.ada.livrariaada.service;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tec.ada.livrariaada.model.dto.LivroDTO;
import tec.ada.livrariaada.model.entity.CategoriaEntity;
import tec.ada.livrariaada.model.entity.EditoraEntity;
import tec.ada.livrariaada.model.entity.LivroEntity;
import tec.ada.livrariaada.model.mapper.LivroMapper;
import tec.ada.livrariaada.model.repository.LivroRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {
    @Autowired
    private LivroRepository repository;
    @Autowired
    private LivroMapper mapper;


    public List<LivroDTO> listar() {
        List<LivroEntity> listaEntities = repository.findAll();
        return mapper.updateListDTO(listaEntities);
    }

    public LivroDTO pegarPorId(Long id) {
        Optional<LivroEntity> livroEntityOp = repository.findById(id);

        if (livroEntityOp.isPresent()){
            LivroEntity livroEntity = livroEntityOp.get();
            return mapper.update(livroEntity);
        }
        throw new EntityNotFoundException("Livro não encontrado");
    }

    public LivroDTO criar(LivroEntity livroEntity) {

        Optional<LivroEntity> optional = repository.findByNomeOrIsbn(livroEntity.getNome(), livroEntity.getIsbn());

        if (optional.isEmpty()) {
            // saveAndFlush serve para salvar e forçar o retorno do ojeto criado
            LivroEntity livro = repository.saveAndFlush(livroEntity);
            return mapper.update(livro);
        } else {
            throw new RuntimeException("Já existe um livro com o nome ou isbn informado!");
        }

    }

    public LivroDTO editar(LivroDTO livroDTO, Long id) {
        if (repository.existsById(id)){
            LivroEntity livroEntity = mapper.update(livroDTO);
            livroEntity.setId(id);//para que eu tenha a garantia que p id está correto;
            livroEntity = repository.save(livroEntity);
            return mapper.update(livroEntity);
        }
        throw new EntityNotFoundException("Livro não encontrado!");
    }

    public void deletar(Long id) {
        Optional<LivroEntity> livroEntityDeleteOp = repository.findById(id);

        if (livroEntityDeleteOp.isPresent()) {
            LivroEntity livroEntity = livroEntityDeleteOp.get();
            repository.delete(livroEntity);
            return;
        }
        throw new EntityNotFoundException("Livro não encontrado!");
    }

    public List<LivroDTO> listarPorCategoria(Integer idCategoria){
        CategoriaEntity categoria = new CategoriaEntity();
        categoria.setId(idCategoria.longValue());
        List <LivroEntity> listaEntities = repository.findByCategoria(categoria);
        return mapper.updateListDTO(listaEntities);
    }

    public List<LivroDTO> listarPorEditora(Integer idEditora){
        EditoraEntity editora = new EditoraEntity();
        editora.setId(idEditora.longValue());
        List <LivroEntity> listaEntities = repository.findByEditora(editora);
        return mapper.updateListDTO(listaEntities);
    }

    public List<LivroDTO> listarPorNomeOuIsbn(String nome, String isbn){
        List<LivroEntity> listaEntities = repository.buscarPorNomeOuIsbn(nome, isbn);
        return mapper.updateListDTO(listaEntities);
    }

}