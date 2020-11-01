package br.com.pricardoti.casacodigo.autor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/autores")
public class AutorController {

    private final AutorRepository autorRepository;

    public AutorController(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @PostMapping
    public ResponseEntity<SalvarAutorResponse> salvar(@RequestBody @Valid final AutorRequest autorRequest) {
        Autor autor = autorRepository.save(autorRequest.toAutor());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new SalvarAutorResponse(autor));
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<ConsultarAutorResponse> consultarPorCodigo(@PathVariable String codigo) {
        Optional<Autor> autor = autorRepository.findByCodigo(codigo);
        return ResponseEntity
                .of(autor.map(item -> new ConsultarAutorResponse(item)));
    }
}
