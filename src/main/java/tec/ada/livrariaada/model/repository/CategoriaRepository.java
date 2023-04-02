package tec.ada.livrariaada.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tec.ada.livrariaada.model.entity.CategoriaEntity;

import java.util.Optional;

@Repository
public interface CategoriaRepository
        extends JpaRepository <CategoriaEntity, Long> {

    public Optional<CategoriaEntity> findByNome(String nome);

}