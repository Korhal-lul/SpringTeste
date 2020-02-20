package br.com.centroweg.Projeto_Rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.centroweg.Projeto_Rest.Model.Pessoa;
import br.com.centroweg.Projeto_Rest.Repository.PessoaRepository;

@SpringBootApplication
@RestController
@CrossOrigin(origins = "*")
public class ProjetoRestApplication {
	
	@Autowired
	private PessoaRepository repository;
	
	@PostMapping("/insert")
	public String insert(@RequestBody Pessoa pessoa) {
		
		repository.save(pessoa);
		
		return "SYSTEM: [ INSERT ] > SUCCESSFUL";
	}
	
	@GetMapping("/getAllPessoa")
	public List<Pessoa> findAllPessoa() {
		
		return repository.findAll();
	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping("/search/{id}")
	public ResponseEntity findById(@PathVariable int id) {
		
		return repository.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/delete/{id}")
	public List<Pessoa> delete(@PathVariable int id) {
		
		repository.deleteById(id);
		
		return repository.findAll();
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity update(@PathVariable int id, @RequestBody Pessoa pessoa) {
		
		
		return repository.findById(id).map(record -> {
			
			record.setNome(pessoa.getNome());
			record.setCpf(pessoa.getCpf());
			record.setEmail(pessoa.getEmail());
			record.setAnoNasc(pessoa.getAnoNasc());
			
			Pessoa updated = repository.save(record);
			return ResponseEntity.ok().body(updated);
			
		}).orElse(ResponseEntity.notFound().build());
	}

	public static void main(String[] args) {
		SpringApplication.run(ProjetoRestApplication.class, args);
	}

}
