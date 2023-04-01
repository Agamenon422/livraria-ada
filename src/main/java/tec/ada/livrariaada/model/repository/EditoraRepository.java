package tec.ada.livrariaada.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tec.ada.livrariaada.model.entity.EditoraEntity;

@Repository
public interface EditoraRepository
        extends JpaRepository<EditoraEntity, Integer> {
}
