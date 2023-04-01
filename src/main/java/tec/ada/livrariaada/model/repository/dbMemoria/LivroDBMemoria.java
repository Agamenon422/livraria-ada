package tec.ada.livrariaada.model.repository.dbMemoria;

import tec.ada.livrariaada.model.dto.LivroDTO;

import java.util.ArrayList;
import java.util.List;

public class LivroDBMemoria {
    private List<LivroDTO> lista = new ArrayList<>();
    private Integer contador = 1;

    public LivroDTO pegarUm(Integer id){
        Integer indice = null;
        for (Integer i=0;i<lista.size();i++) {
            if (lista.get(i).getId().equals(id)){
                indice = i;
                break;
            }
        }
        return lista.get(indice);
    }

    public LivroDTO editar(LivroDTO livroDTO, Integer id){
        livroDTO.setId(id);

        Integer indice = null;
        for (Integer i=0; i< lista.size();i++){
            if(lista.get(i).getId().equals(id)){
                indice = i;
                break;
            }
        }
        lista.set(indice, livroDTO);
        return livroDTO;
    }

    public LivroDTO editar(LivroDTO livroDTO){

        for(Integer i = 0; i < lista.size(); i++){
            if (lista.get(i).getId().equals(livroDTO.getId())){
                lista.set(i, livroDTO);
            }else {
                throw new RuntimeException("Livro não encontrado!");
            }
        }
        return livroDTO;
    }

    public void deletar(Integer id){
        int indice = -1;
        for (Integer i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId().equals(id)) {
                indice = i;
                break;
            }
        }
        lista.remove(indice);
    }

    public List<LivroDTO> listarTodos (){
        return lista;
    }
}
