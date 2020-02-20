package br.com.centroweg.Projeto_Rest.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.centroweg.Projeto_Rest.Model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{
	
	List<Pessoa> findByEmail(String email);
	
	
}
