package com.github.mateusnere.localizacao.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.mateusnere.localizacao.domain.entity.Cidade;
import com.github.mateusnere.localizacao.domain.repository.CidadeRepository;
import com.github.mateusnere.localizacao.domain.repository.specs.CidadeSpec;

@Service
public class CidadeService {

    private CidadeRepository cidadeRepository;

    public CidadeService(CidadeRepository cidadeRepository) {
        this.cidadeRepository = cidadeRepository;
    }

    public void listarCidadesQueryMethodsString() {
		listarCidadesPorNome("Belo Horizonte");
		listarCidadesPorNomeComecandoCom("São");
		listarCidadesPorNomeTerminandoCom("is");
		listarCidadesPorNomeContendo("al");
		listarCidadesPorNomeLike("%bra%");
		listarCidadesPorNomeLikeIgnorandoCaseSensitive("%bra%");
	}

	public void listarCidadesQueryMethodsValoresNumericos() {
		listarCidadesPorHabitantes(751300L);
		listarCidadesPorHabitantesMenorQue(1000000L);
		listarCidadesPorHabitantesMaiorQue(1000000L);
		listarCidadesPorHabitantesMaiorOuIgualQue(2428678L);
	}

	public void listarCidades() {
		cidadeRepository.findAll().forEach(System.out::println);
	}

	public void listarCidadesPorNome(String nome) {
		System.out.println("##### Cidades pelo nome " + nome + " #####");
		cidadeRepository.findByNome(nome).forEach(System.out::println);
	} 

	public void listarCidadesPorNomeSQLNativo(String nome) {
		System.out.println("##### Cidades pelo nome " + nome + " #####");
		cidadeRepository.findByNomeSQLNativo(nome).forEach(System.out::println);
	}

	public void listarCidadesPorNomeSQLNativoProjection(String nome) {
		System.out.println("##### Cidades pelo nome " + nome + " #####");
		cidadeRepository.findByNomeSQLNativoProjection(nome)
						.stream().map(cidadeProjection -> new Cidade(cidadeProjection.getId(), cidadeProjection.getNome(), null))
						.forEach(System.out::println);
	}

	public void listarCidadesPorNomeComecandoCom(String nome) {
		System.out.println("##### Cidades pelo nome iniciando com: " + nome + " #####");
		cidadeRepository.findByNomeStartingWith(nome).forEach(System.out::println);
	}

	public void listarCidadesPorNomeTerminandoCom(String nome) {
		System.out.println("##### Cidades pelo nome terminando com: " + nome + " #####");
		cidadeRepository.findByNomeEndingWith(nome).forEach(System.out::println);
	}

	public void listarCidadesPorNomeContendo(String nome) {
		System.out.println("##### Cidades pelo nome contendo a palavra: " + nome + " #####");
		cidadeRepository.findByNomeContaining(nome).forEach(System.out::println);
	}

	public void listarCidadesPorNomeLike(String expressao) {
		// Nesse método nós fazemos a expressão no parametro
		System.out.println("##### Cidades pelo nome contendo a palavra: " + expressao + " #####");
		cidadeRepository.findByNomeLike(expressao).forEach(System.out::println);
	}

	public void listarCidadesPorNomeLikeIgnorandoCaseSensitive(String nome) {
		System.out.println("##### Cidades pelo nome contendo a palavra: " + nome + " (sem case sensitive) #####");
		cidadeRepository.findByNomeIgnorandoCaseSensitive(nome).forEach(System.out::println);
	}

	public void listarCidadesPorNomeLikeIgnorandoCaseSensitiveOrdenado(String nome) {
		System.out.println("##### Cidades pelo nome contendo a palavra: " + nome + " (sem case sensitive) #####");
		cidadeRepository.findByNomeIgnorandoCaseSensitiveOrdenado(nome, Sort.by(Direction.DESC, "habitantes")).forEach(System.out::println);
	}

	public void listarCidadesPorNomeLikeIgnorandoCaseSensitivePaginado(String nome) {

        Pageable pageable = PageRequest.of(0, 1);

		System.out.println("##### Cidades pelo nome contendo a palavra: " + nome + " (sem case sensitive) #####");
		cidadeRepository.findByNomeIgnorandoCaseSensitivePaginado(nome, pageable).forEach(System.out::println);
	}

	public void listarCidadesPorHabitantes(Long habitantes) {
		System.out.println("##### Cidades pelo número de habitantes #####");
		cidadeRepository.findByHabitantes(habitantes).forEach(System.out::println);
	}

	public void listarCidadesPorHabitantesMenorQue(Long habitantes) {
		System.out.println("##### Cidades com número de habitantes menor que "+ habitantes +" #####");
		cidadeRepository.findByHabitantesLessThan(habitantes).forEach(System.out::println);
	}

	public void listarCidadesPorHabitantesMaiorQue(Long habitantes) {
		System.out.println("##### Cidades com número de habitantes maior que "+ habitantes +" #####");
		cidadeRepository.findByHabitantesGreaterThan(habitantes).forEach(System.out::println);
	}

	public void listarCidadesPorHabitantesMaiorOuIgualQue(Long habitantes) {
		System.out.println("##### Cidades com número de habitantes maior ou igual que "+ habitantes +" #####");
		cidadeRepository.findByHabitantesGreaterThanEqual(habitantes).forEach(System.out::println);
	}

	public void listarCidadesPorHabitantesMenorQueENomeLike(Long habitantes, String nome) {
		System.out.println("##### Cidades com número de habitantes menor que "+ habitantes +" e nome like "+ nome +" #####");
		cidadeRepository.findByHabitantesLessThanAndNomeLike(habitantes, nome).forEach(System.out::println);
	}

	public void listarCidadesPorHabitantesMaiorOuIgualQueOuNomeComecando(Long habitantes, String nome) {
		System.out.println("##### Cidades com número de habitantes maior ou igual que "+ habitantes +" ou nome começando com "+ nome +" #####");
		cidadeRepository.findByHabitantesGreaterThanEqualOrNomeStartingWith(habitantes, nome).forEach(System.out::println);
	}

	public void printHeader() {
		System.out.println("####################");
		System.out.println("Application started!");
		System.out.println("####################");
	}


	// Query by example
	public List<Cidade> filtroDinamico(Cidade cidade) {
		// Aqui eu posso customizar um matcher para passar como parâmetro no example
		// .withIgnoreCase podemos passar como parâmetro o nome das propriedades que queremos que faça o filtro ignorando o case, 
		// se não passar parâmetro nenhum ele vai ignorar em todos os fields que forem strings
		ExampleMatcher matcher = ExampleMatcher.matching()
									.withIgnoreCase()
									.withStringMatcher(StringMatcher.STARTING);

		Example<Cidade> example = Example.of(cidade, matcher);
		return cidadeRepository.findAll(example);
	}

	public void listarCidadeSpecsByNomeSpec() {
		Specification<Cidade> nomeEqSaoPaulo = CidadeSpec.nomeEqual("São Paulo");
		cidadeRepository.findAll(nomeEqSaoPaulo).forEach(System.out::println);
	}

	public void listarCidadeSpecsByNomeLikeSpec() {
		Specification<Cidade> nomeLikeSaoAnything = CidadeSpec.nomeLike("São%").and(CidadeSpec.habitantesGreaterThan(10000000L));
		cidadeRepository.findAll(nomeLikeSaoAnything).forEach(System.out::println);
	}

	public void listarCidadesSpecsFiltroDinamico(Cidade filtro) {
		Specification<Cidade> specs = Specification.where((root, query, cb) -> cb.conjunction());
		
		if(filtro.getId() != null) {
			specs = specs.and(CidadeSpec.idEqual(filtro.getId()));
		}

		if(StringUtils.hasText(filtro.getNome())) {
			specs = specs.and(CidadeSpec.nomeLikeUpper("%" + filtro.getNome() + "%"));
		}

		if(filtro.getHabitantes() != null) {
			specs = specs.and(CidadeSpec.habitantesGreaterThan(filtro.getHabitantes()));
		}

		cidadeRepository.findAll(specs).forEach(System.out::println);
	}

}
