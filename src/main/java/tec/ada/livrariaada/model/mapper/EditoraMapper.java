package tec.ada.livrariaada.model.mapper;

import org.springframework.stereotype.Component;
import tec.ada.livrariaada.model.dto.EditoraDTO;
import tec.ada.livrariaada.model.entity.EditoraEntity;

import java.util.List;

@Component
public class EditoraMapper {

    public EditoraDTO update(EditoraEntity editora){
        EditoraDTO editoraDTO = new EditoraDTO();
        editoraDTO.setId(editora.getId());
        editoraDTO.setNome(editora.getNome());
        editoraDTO.setDescricao(editora.getDescricao());
        return editoraDTO;
    }

    public EditoraEntity update(EditoraDTO editora){
        EditoraEntity editoraEntity = new EditoraEntity();
        editoraEntity.setId(editora.getId());
        editoraEntity.setNome(editora.getNome());
        editoraEntity.setDescricao(editora.getDescricao());
        return editoraEntity;
    }

    public List<EditoraDTO> updateListDTO(List<EditoraEntity> listEditoraEntity){
        // Método que recebe uma lista de entidades EditoraEntity e retorna uma lista de DTOs EditoraDTO atualizados.
        return listEditoraEntity.stream()
                .map(editoraEntity-> this.update(editoraEntity)).toList();
        /* Para cada entidade EditoraEntity, mapeia para um novo objeto EditoraDTO atualizado
         * utilizando o método "update" deste objeto.
         * O método "toList()" converte o stream resultante para uma lista de EditoraDTO.*/
    }

    public List<EditoraEntity> updateListeEntity(List<EditoraDTO> listEditoraDTO){
        // Método que recebe uma lista de entidades EditoraDTO e retorna uma lista de Entitys EditoraEntity atualizados.
        return listEditoraDTO.stream()
                .map(editoraDTO -> this.update(editoraDTO)).toList();
        /* Para cada entidade EditoraDTO, mapeia para um novo objeto EditoraEntity atualizado
         * utilizando o método "update" deste objeto.
         * O método "toList()" converte o stream resultante para uma lista de EditoraEntity.*/
    }

}
