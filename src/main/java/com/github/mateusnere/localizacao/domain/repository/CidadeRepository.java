package com.github.mateusnere.localizacao.domain.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.github.mateusnere.localizacao.domain.entity.Cidade;
import com.github.mateusnere.localizacao.domain.repository.projections.CidadeIdNomeProjection;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long>, JpaSpecificationExecutor<Cidade> {

    static final String QUERY = " select * from tb_cidade as c where c.nome =:nome ";
    static final String QUERY_ID_NOME = " select c.id_cidade as id, c.nome as nome from tb_cidade as c where c.nome =:nome ";

    @Query(nativeQuery = true, value = QUERY)
    public List<Cidade> findByNomeSQLNativo(@Param("nome") String nome);

    @Query(nativeQuery = true, value = QUERY_ID_NOME)
    public List<CidadeIdNomeProjection> findByNomeSQLNativoProjection(@Param("nome") String nome);

    // Abaixo estão exemplos de query methods do spring data
    public List<Cidade> findByNome(String nome);

    public List<Cidade> findByNomeStartingWith(String nome);

    public List<Cidade> findByNomeEndingWith(String nome);

    public List<Cidade> findByNomeContaining(String nome);
    
    // Esse aqui pode substituir os 3 de cima, colocando a expressão no parâmetro
    public List<Cidade> findByNomeLike(String expressao);

    // exemplo de uma query para fazer a comparação em caixa baixa. Se quiser em caixa alta trocar lower por upper.
    // a expressão ?1 quer dizer o primeiro parâmetro, se tivesse outros, teria que colocar ?2, ?3 e assim por diante.
    @Query("select c from Cidade c where lower(c.nome) like lower(?1)")
    public List<Cidade> findByNomeIgnorandoCaseSensitive(String nome);

    // Com ordenação
    @Query("select c from Cidade c where lower(c.nome) like lower(?1)")
    public List<Cidade> findByNomeIgnorandoCaseSensitiveOrdenado(String nome, Sort sort);

    // Com paginação
    @Query("select c from Cidade c where lower(c.nome) like lower(?1)")
    public Page<Cidade> findByNomeIgnorandoCaseSensitivePaginado(String nome, Pageable pageable);

    // Query Methods com valores numéricos
    public List<Cidade> findByHabitantes(Long habitantes);

    public List<Cidade> findByHabitantesLessThan(Long habitantes);
    
    public List<Cidade> findByHabitantesGreaterThan(Long habitantes);
    
    public List<Cidade> findByHabitantesGreaterThanEqual(Long habitantes);


    // Query Methods combinando dois parâmetros
    List<Cidade> findByHabitantesLessThanAndNomeLike(Long habitantes, String nome);

    List<Cidade> findByHabitantesGreaterThanEqualOrNomeStartingWith(Long habitantes, String nome);
    
}
