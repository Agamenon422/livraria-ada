package tec.ada.livrariaada.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tec.ada.livrariaada.model.entity.CategoriaEntity;
import tec.ada.livrariaada.model.entity.LivroEntity;

@Repository
public interface LivroRepository
        extends JpaRepository<LivroEntity, Integer> {
}
