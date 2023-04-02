package tec.ada.livrariaada.model.repository;

import lombok.extern.java.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tec.ada.livrariaada.model.entity.CategoriaEntity;
import tec.ada.livrariaada.model.entity.EditoraEntity;
import tec.ada.livrariaada.model.entity.LivroEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface LivroRepository
        extends JpaRepository<LivroEntity, Long> {

    public Optional<LivroEntity> findByNomeOrIsbn(String nome, String isbn); // utilizado na hora da criação do livro

    @Query("SELECT l FROM LivroEntity l WHERE upper(l.nome) LIKE upper(concat('%', :nome, '%')) OR l.isbn = :isbn")
    public List<LivroEntity> buscarPorNomeOuIsbn(String nome, String isbn); // utilizado na busca

    public List<LivroEntity> findByCategoria(CategoriaEntity categoria);

    public List<LivroEntity> findByEditora(EditoraEntity editora);

}