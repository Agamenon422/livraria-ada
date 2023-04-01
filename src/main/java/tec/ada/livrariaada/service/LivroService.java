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

    public List<LivroDTO> filtrar(Integer categoria) {
        List<LivroEntity> listaEntities = repository.findByCategoria(categoria);
        return mapper.updateListDTO(listaEntities);
    }

    public List<LivroDTO> listarPorCategoria(Integer idCategoria){
        CategoriaEntity categoria= new CategoriaEntity();
        categoria.setId(idCategoria);

        List <LivroEntity> listaEntities = repository.findByCategoria(categoria);
        return mapper.updateListDTO(listaEntities);
    }

    public List<LivroDTO> listarPorEditora(Integer idEditora){
        EditoraEntity editora = new EditoraEntity();
        editora.setId(idEditora);

        List <LivroEntity> listaEntities = repository.findByEditora(editora);
        return mapper.updateListDTO(listaEntities);

    }

    public LivroDTO pegarPorId(Integer id) {
        Optional<LivroEntity> livroEntityOp = repository.findById(id);

        if (livroEntityOp.isPresent()){
            LivroEntity livroEntity = livroEntityOp.get();
            return mapper.update(livroEntity);
        }
        throw new EntityNotFoundException("Livro não encontrado");
    }

    public LivroDTO criar(LivroDTO livroDTO) {
        LivroEntity livro= mapper.update(livroDTO);
        livro = repository.save(livro);
        return mapper.update(livro);
    }

    public LivroDTO editar(LivroDTO livroDTO, Integer id) {
        if (repository.existsById(id)){

            LivroEntity livroEntity = mapper.update(livroDTO);
            livroEntity.setId(id);//para que eu tenha a garantia que p id está correto;
            livroEntity = repository.save(livroEntity);
            return mapper.update(livroEntity);
        }
        throw new EntityNotFoundException("Livro não encontrado!");
    }

    public void deletar(Integer id) {
        Optional<LivroEntity> livroEntityDeleteOp = repository.findById(id);

        if (livroEntityDeleteOp.isPresent()) {
            LivroEntity livroEntity = livroEntityDeleteOp.get();
            repository.delete(livroEntity);
            return;
        }
        throw new EntityNotFoundException("Livro não encontrado!");
    }
}
