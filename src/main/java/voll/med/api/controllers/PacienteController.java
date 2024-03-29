package voll.med.api.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import voll.med.api.domain.paciente.*;

@RestController
@RequestMapping("/pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {

    @Autowired
    PacienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroPacientes dados,
                                    UriComponentsBuilder uriBuilder){

        var paciente = new Paciente(dados);
        repository.save(paciente);
        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosListagemIndividual(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPacientes>> listar(@PageableDefault(size=10, page = 0, sort="nome") Pageable paginacao){
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemPacientes::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosListagemIndividual> listar(@PathVariable Long id){
        return ResponseEntity.ok(new DadosListagemIndividual(repository.getReferenceById(id)));

    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoPacientes dados){
        Paciente paciente = repository.getReferenceById(dados.id());
        paciente.atualizarPaciente(dados);
        return ResponseEntity.ok(new DadosListagemIndividual(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        Paciente paciente = repository.getReferenceById(id);
        paciente.excluiRegistro();
        return ResponseEntity.noContent().build();
    }
}
