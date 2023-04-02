package tec.ada.livrariaada.model.repository.dbMemoria;

import tec.ada.livrariaada.model.dto.EditoraDTO;

import java.util.ArrayList;
import java.util.List;

public class EditoraDBMemoria {
    private List<EditoraDTO> lista = new ArrayList<>();
    private Long contador = 1L;

    public EditoraDTO pegarUma(Integer id){
        Integer indece = null;
        for(Integer i = 0; i<lista.size(); i++){
            if (lista.get(i).getId().equals(id)) {
                indece = i;
                break;
            }
        }
        return lista.get(indece);
    }


    public EditoraDTO criar(EditoraDTO editoraDTO){
        editoraDTO.setId(contador);
        lista.add(editoraDTO);
        contador++;

        return editoraDTO;
    }

    public EditoraDTO editar(EditoraDTO editoraDTO, Long id){
        editoraDTO.setId(id);

        Integer indice = null;
        for(Integer i = 0; i< lista.size();i++){
            if (lista.get(i).getId().equals(id)) {
                indice = i;
                break;
            }
        }
        lista.set(indice, editoraDTO);
        return editoraDTO;
    }


    public void deletar(Integer id){
        int indice = -1;
        for(Integer i=0; i< lista.size();i++){
            if (lista.get(i).getId().equals(id)) {
                indice = i;
                break;
            }
        }
        lista.remove(indice);
    }

    public List< EditoraDTO> listarTodas(){
        return lista;
    }
}
