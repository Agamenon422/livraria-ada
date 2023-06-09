package tec.ada.livrariaada.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tec.ada.livrariaada.model.entity.CategoriaEntity;

@Repository
public interface CategoriaRepository
        extends JpaRepository <CategoriaEntity, Integer> {

}
