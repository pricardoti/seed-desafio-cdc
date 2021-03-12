package br.com.pricardoti.casacodigo.categoria;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @PostMapping
    public ResponseEntity<SalvarCategoriaResponse> salvar(@Valid @RequestBody SalvarCategoriaRequest request) {
        final Categoria categoria = categoriaRepository.save(request.toCategoria());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new SalvarCategoriaResponse(categoria));
    }
}
