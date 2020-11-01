package br.com.pricardoti.casacodigo.autor;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AutorRepository extends CrudRepository<Autor, Long> {

    Optional<Autor> findByCodigo(String codigo);
}
