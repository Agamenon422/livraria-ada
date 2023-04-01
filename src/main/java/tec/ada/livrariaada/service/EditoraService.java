package tec.ada.livrariaada.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tec.ada.livrariaada.model.dto.CategoriaDTO;
import tec.ada.livrariaada.model.dto.EditoraDTO;
import tec.ada.livrariaada.model.dto.LivroDTO;
import tec.ada.livrariaada.model.entity.CategoriaEntity;
import tec.ada.livrariaada.model.entity.EditoraEntity;
import tec.ada.livrariaada.model.entity.LivroEntity;
import tec.ada.livrariaada.model.mapper.EditoraMapper;
import tec.ada.livrariaada.model.repository.EditoraRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EditoraService {
    @Autowired
    private EditoraRepository repository;
    @Autowired
    private EditoraMapper mapper;

    public List<EditoraDTO> listar() {
        List<EditoraEntity> editoraEntities = repository.findAll();
        return mapper.updateListDTO(editoraEntities);
    }


    public EditoraDTO pegarPorId(Integer id) {
        Optional<EditoraEntity> editoraEntityOp = repository.findById(id);

        if (editoraEntityOp.isPresent()){
            EditoraEntity editoraEntity = editoraEntityOp.get();
            return mapper.update(editoraEntity);
        }
        throw new EntityNotFoundException("Editora não encontrada!");
    }


    public EditoraDTO criar(EditoraDTO editoraDTO) {
        EditoraEntity editora = mapper.update(editoraDTO); 
        editora = repository.save(editora);
        return mapper.update(editora);
    }


    public EditoraDTO editar(EditoraDTO editoraDTO, Integer id) {
        if (repository.existsById(id)){

            EditoraEntity editoraEntity = mapper.update(editoraDTO);
            editoraEntity.setId(id);
            editoraEntity = repository.save(editoraEntity);
            return mapper.update(editoraEntity);
        }
        throw new EntityNotFoundException("Editora não encontrada!");
    }


    public void deletar(Integer id) {
        Optional<EditoraEntity> editoraEntityDeletar = repository.findById(id);

        if (editoraEntityDeletar.isPresent()){
            EditoraEntity categoriaEntity = editoraEntityDeletar.get();
            repository.delete(categoriaEntity);
            return;
        }
        throw new EntityNotFoundException("Editora não encontrada!");
    }
}
