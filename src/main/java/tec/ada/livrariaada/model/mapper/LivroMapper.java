package tec.ada.livrariaada.model.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tec.ada.livrariaada.model.dto.LivroDTO;
import tec.ada.livrariaada.model.entity.CategoriaEntity;
import tec.ada.livrariaada.model.entity.LivroEntity;
import tec.ada.livrariaada.model.repository.CategoriaRepository;
import tec.ada.livrariaada.model.repository.EditoraRepository;

import java.util.List;

@Component
public class LivroMapper {

    @Autowired
    private EditoraRepository editoraRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public LivroDTO update(LivroEntity livro){//Conversão de 'LivroEntity' p/ 'LivroDTO';
        LivroDTO livroDTO = new LivroDTO();//criando uma variável;
        String editora = editoraRepository.findById(livro.getEditora().getId()).get().getNome();
        String categoria  = categoriaRepository.findById(livro.getCategoria().getId()).get().getNome();

        livroDTO.setId(livro.getId());//pego pelo id, alteto os dados e permaneço com o mesmo id;
        livroDTO.setNome(livro.getNome());//pedo o dado (nome) de entrada e altero;
        livroDTO.setIsdn(livro.getIsbn());//pedo o dado (isbn) de entrada e altero;

        livroDTO.setEditora(editora);
        livroDTO.setCategoria(categoria);
        return livroDTO;//convertido para 'LivroDTO';
    }

    public LivroEntity update (LivroDTO livro){//Conversão de 'LivroDTO' p/ 'LivroEntity';
        LivroEntity livroEntity = new LivroEntity();//criando uma variável;
        livroEntity.setId(livroEntity.getId());//pego pelo id, alteto os dados e permaneço com o mesmo id;
        livroEntity.setNome(livroEntity.getNome());//pedo o dado (nome) de entrada e altero;
        livroEntity.setIsbn(livroEntity.getIsbn());//pedo o dado (isbn) de entrada e altero;
        return livroEntity;//convertido para 'LivroEntity';
    }

    public List<LivroEntity> updateListEntity(List<LivroDTO> listLivroDTO){
        return listLivroDTO
                .stream()
                .map(livrotDTO->this.update(livrotDTO))
                .toList();
    }

    public List<LivroDTO> updateListDTO(List<LivroEntity> listLivroEntity){
        return listLivroEntity
                .stream()
                .map(livroEntity -> this.update(livroEntity))
                .toList();
    }



}
