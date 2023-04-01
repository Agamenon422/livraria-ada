package tec.ada.livrariaada.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tec.ada.livrariaada.model.entity.CategoriaEntity;
import tec.ada.livrariaada.model.entity.EditoraEntity;
import tec.ada.livrariaada.model.entity.LivroEntity;

import java.util.List;

@Repository
public interface LivroRepository
        extends JpaRepository<LivroEntity, Integer> {

    //SELECT * FROM produto WHERE nome = 'Arroz' OR (preco BETWEEN 2 AND 10);
    //Query Method
   // @Query("SELECT l FROM LivroEntity  l")//forma 'jpql', como vou deixar só o l então ele vai trazer toda a entidade;
    List<LivroEntity> findByCategoria(CategoriaEntity categoria);

    //@Query("SELECT l FROM LivroEntity  l")//forma 'jpql', como vou deixar só o l então ele vai trazer toda a entidade;
    List<LivroEntity> findByEditora(EditoraEntity editora);
    @Query("SELECT l FROM LivroEntity l" + " WHERE UPPER(l.nome) LIKE CONCAT('%', UPPER(:nome),'%' ) OR " + "(l.isbn) = :isbn")
    List<LivroEntity> findByNomeOrIsbn(@Param("nome") String nome, long isbn);
}
