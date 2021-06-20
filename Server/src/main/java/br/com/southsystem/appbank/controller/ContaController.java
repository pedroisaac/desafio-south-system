package br.com.southsystem.appbank.controller;

import br.com.southsystem.appbank.AppConfig;
import br.com.southsystem.appbank.model.TipoRegraLimite;
import br.com.southsystem.appbank.model.conta.Conta;
import br.com.southsystem.appbank.service.conta.ContaService;
import br.com.southsystem.appbank.service.pessoa.PessoaService;
import br.com.southsystem.appbank.service.regraLimite.RegraLimiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ContaController {

	@Autowired
	ContaService contaService;

	@Autowired
	PessoaService pessoaService;

	@Autowired
	RegraLimiteService regraLimiteService;

	@Autowired
	AppConfig appConfig;

	@GetMapping("/contas")
	public ResponseEntity<List<Conta>> getAll(@RequestParam(required = false) Long idPessoa) {
		try {
			var contas = new ArrayList<Conta>();

			if (ObjectUtils.isEmpty(idPessoa)) {
				//busca todas as contas cadastradas
				contas.addAll(contaService.listaTodas());
			} else {
				//busca todas as contas de uma determinada pessoa
				var pessoaData = Optional.ofNullable(pessoaService.getById(idPessoa));
				if (pessoaData.isEmpty()) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
				contas.addAll(contaService.listaByPessoa(pessoaData.get()));
			}

			if (contas.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(contas, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/contas/{id}")
	public ResponseEntity<Conta> getById(@PathVariable("id") long id) {
		try {
			var conta = contaService.getById(id);

			if (!Objects.isNull(conta)) {
				return new ResponseEntity<>(conta, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/contas")
	public ResponseEntity<Conta> create(@RequestBody Map<String, String> payload) {
		try {
			//busca pessoa
			var pessoa =
					pessoaService.getById(
							Long.parseLong(Objects.requireNonNull(payload.get("idPessoa"), "Informe o Id da pessoa")
							)
					);

			if (ObjectUtils.isEmpty(pessoa)) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

			var limiteCheque = regraLimiteService.getValorLimite(pessoa.getScore(), TipoRegraLimite.CHEQUE_ESPECIAL);

			var _conta = Conta.builder()
					.agencia(appConfig.getParametrizacao().getAgenciaPadrao())
					.pessoa(pessoa)
					.limiteChequeEspecial(limiteCheque)
					.build();

			contaService.save(_conta);

			return new ResponseEntity<>(_conta, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/contas/{id}")
	public ResponseEntity<Conta> update(@PathVariable("id") long id, @RequestBody Map<String, String> payload) {
		try {
			var conta = contaService.getById(id);

			if (!ObjectUtils.isEmpty(conta)) {
				//busca pessoa
				var pessoa =
						pessoaService.getById(
								Long.parseLong(Objects.requireNonNull(payload.get("idPessoa"), "Informe o Id da pessoa"))
						);

				if (ObjectUtils.isEmpty(pessoa)) {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}

				conta.setAgencia(payload.get("agencia"));
				conta.setPessoa(pessoa);

				return new ResponseEntity<>(contaService.save(conta), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/contas/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
		try {
			contaService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
