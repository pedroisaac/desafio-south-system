package br.com.southsystem.appbank.controller;

import br.com.southsystem.appbank.model.pessoa.Pessoa;
import br.com.southsystem.appbank.service.pessoa.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class PessoaController {

	@Autowired
	PessoaService pessoaService;

	@GetMapping("/pessoas")
	public ResponseEntity<List<Pessoa>> getAll(@RequestParam(required = false) String nome) {
		try {
			var pessoas = new ArrayList<Pessoa>();

			if (StringUtils.isEmpty(nome)) {
				pessoas.addAll(pessoaService.listaTodos());
			} else {
				pessoas.addAll(pessoaService.listaByNome(nome));
			}

			if (pessoas.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(pessoas, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/pessoas/{id}")
	public ResponseEntity<Pessoa> getById(@PathVariable("id") long id) {
		try {
			var pessoaData = Optional.ofNullable(pessoaService.getById(id));

			return pessoaData.map(pessoa -> new ResponseEntity<>(pessoa, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/pessoas")
	public ResponseEntity<Pessoa> create(@RequestBody Map<String, String> payload) {
		try {
			var _pessoa = Pessoa.builder()
					.nome(payload.get("nome"))
					.documento(payload.get("documento"))
					.build();

			pessoaService.save(_pessoa);

			return new ResponseEntity<>(_pessoa, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/pessoas/{id}")
	public ResponseEntity<Pessoa> update(@PathVariable("id") long id, @RequestBody Map<String, String> payload) {
		try {
			var pessoaData = Optional.ofNullable(pessoaService.getById(id));

			if (pessoaData.isPresent()) {
				var _pessoa = pessoaData.get();

				_pessoa.setNome(payload.get("nome"));
				//_pessoa.set(pessoa.getDescription());
				return new ResponseEntity<>(pessoaService.save(_pessoa), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/pessoas/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
		try {
			pessoaService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/pessoas")
	public ResponseEntity<HttpStatus> deleteAll() {
		try {
			pessoaService.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
