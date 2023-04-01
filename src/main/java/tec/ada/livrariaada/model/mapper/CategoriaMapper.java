package tec.ada.livrariaada.model.mapper;

import java.util.List;

import org.springframework.stereotype.Component;
import tec.ada.livrariaada.model.dto.CategoriaDTO;
import tec.ada.livrariaada.model.entity.CategoriaEntity;


@Component//usando 'Component' para eu poder usar @Autowired;
public class CategoriaMapper {

    //Conversão de  'CategoriaEntity' p/ 'CategoriaDTO';
    public CategoriaDTO update(CategoriaEntity categoria) {
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setId(categoria.getId());
        categoriaDTO.setNome(categoria.getNome());
        categoriaDTO.setDescricao(categoria.getDescricao());
        return categoriaDTO;
    }

    //Conversão de 'CategoriaDTO' p/ 'CategoriaEntity';
    public CategoriaEntity update(CategoriaDTO categoria) {
        CategoriaEntity categoriaEntity = new CategoriaEntity();
        categoriaEntity.setId(categoria.getId());
        categoriaEntity.setNome(categoria.getNome());
        categoriaEntity.setDescricao(categoria.getDescricao());
        return categoriaEntity;
    }

    public List<CategoriaEntity> updateListEntity(List<CategoriaDTO> listaDTO){
        return listaDTO
                .stream()
                .map(categoriaDTO -> this.update(categoriaDTO))
                .toList();
    }

    public List<CategoriaDTO> updateListDTO(List<CategoriaEntity> listaEntity){
        return listaEntity
                .stream()
                .map(categoriaEntity -> this.update(categoriaEntity))
                .toList();
    }
}

