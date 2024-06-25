package com.github.mateusnere.localizacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.mateusnere.localizacao.domain.entity.Cidade;
import com.github.mateusnere.localizacao.service.CidadeService;

@SpringBootApplication
public class LocalizacaoApplication implements CommandLineRunner {

	@Autowired
	private CidadeService cidadeService;

	public static void main(String[] args) {
		SpringApplication.run(LocalizacaoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		cidadeService.printHeader();
		cidadeService.listarCidades();
		// cidadeService.listarCidadesPorNomeLikeIgnorandoCaseSensitiveOrdenado("s%");
		// cidadeService.listarCidadesPorNomeLikeIgnorandoCaseSensitivePaginado("s%");

		Cidade cidade = Cidade.builder()
						.id(null)
						.nome("são")
						.habitantes(null)
						.build();
		
		// cidadeService.filtroDinamico(cidade).forEach(System.out::println);

		// chamar método que utiliza specification
		// cidadeService.listarCidadeSpecsByNomeSpec();
		// cidadeService.listarCidadeSpecsByNomeLikeSpec();

		cidadeService.listarCidadesSpecsFiltroDinamico(cidade);
		cidadeService.listarCidadesPorNomeSQLNativo("São Paulo");
		cidadeService.listarCidadesPorNomeSQLNativoProjection("São Paulo");
	}

	

}
