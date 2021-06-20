package br.com.southsystem.appbank;

import br.com.southsystem.appbank.model.Parametrizacao;
import br.com.southsystem.appbank.model.RegraLimite;
import br.com.southsystem.appbank.model.TipoRegraLimite;
import br.com.southsystem.appbank.model.pessoa.Pessoa;
import br.com.southsystem.appbank.service.pessoa.PessoaService;
import br.com.southsystem.appbank.service.regraLimite.RegraLimiteService;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.math.BigDecimal;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Component
public class AppConfig {

	@Autowired
	PessoaService pessoaService;

	@Autowired
	RegraLimiteService regraLimiteService;

	private Parametrizacao parametrizacao;

	@PostConstruct
	public void init() throws Exception {
		carregarArquivoParametrizacao();
		mockPessoas();
		mockRegrasLimite();
	}

	//numeros de documentos gerados no https://www.4devs.com.br/
	private void mockPessoas() throws Exception {
		var pessoa1 = Pessoa.builder().nome("Pedro Isaac de Sales Godoi")
				.documento("595.018.789-02")
				.build();

		var pessoa2 = Pessoa.builder().nome("Elaine e César Doces & Salgados ME")
				.documento("02.760.409/0001-31")
				.build();

		pessoaService.save(pessoa1);
		pessoaService.save(pessoa2);
	}

	private void mockRegrasLimite() throws Exception {
		var regraChequePeso1 = RegraLimite.builder()
				.tipoLimite(TipoRegraLimite.CHEQUE_ESPECIAL)
				.peso(1)
				.scoreInicial(2)
				.scoreFinal(5)
				.valorLimite(BigDecimal.valueOf(1000L))
				.build();

		var regraChequePeso2 = RegraLimite.builder()
				.tipoLimite(TipoRegraLimite.CHEQUE_ESPECIAL)
				.peso(2)
				.scoreInicial(6)
				.scoreFinal(8)
				.valorLimite(BigDecimal.valueOf(2000L))
				.build();

		var regraChequePeso3 = RegraLimite.builder()
				.tipoLimite(TipoRegraLimite.CHEQUE_ESPECIAL)
				.peso(3)
				.scoreInicial(9)
				.scoreFinal(9)
				.valorLimite(BigDecimal.valueOf(5000L))
				.build();

		regraLimiteService.save(regraChequePeso1);
		regraLimiteService.save(regraChequePeso2);
		regraLimiteService.save(regraChequePeso3);

		var regraCartaoPeso1 = RegraLimite.builder()
				.tipoLimite(TipoRegraLimite.CARTAO_CREDITO)
				.peso(1)
				.scoreInicial(2)
				.scoreFinal(5)
				.valorLimite(BigDecimal.valueOf(200L))
				.build();

		var regraCartaoPeso2 = RegraLimite.builder()
				.tipoLimite(TipoRegraLimite.CARTAO_CREDITO)
				.peso(2)
				.scoreInicial(6)
				.scoreFinal(8)
				.valorLimite(BigDecimal.valueOf(2000L))
				.build();

		var regraCartaoPeso3 = RegraLimite.builder()
				.tipoLimite(TipoRegraLimite.CARTAO_CREDITO)
				.peso(3)
				.scoreInicial(9)
				.scoreFinal(9)
				.valorLimite(BigDecimal.valueOf(15000L))
				.build();

		regraLimiteService.save(regraCartaoPeso1);
		regraLimiteService.save(regraCartaoPeso2);
		regraLimiteService.save(regraCartaoPeso3);
	}

	private void carregarArquivoParametrizacao() throws IOException {
		var resource = new ClassPathResource(Parametrizacao.FILE_PARAMETRIZACAO);

		//se nao existir o arquivo de parametrizacao cria
		if (!resource.exists()) {
			this.parametrizacao = new Parametrizacao("1234-0");
			XmlMapper xmlMapper = new XmlMapper();
			xmlMapper.writeValue(new File(resource.getPath()), this.parametrizacao);
			return;
		}

		XmlMapper xmlMapper = new XmlMapper();
		String xml = inputStreamToString(new FileInputStream(resource.getFile()));
		this.parametrizacao = xmlMapper.readValue(xml, Parametrizacao.class);
	}

	private String inputStreamToString(InputStream is) throws IOException {
		StringBuilder sb = new StringBuilder();
		String line;
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		br.close();
		return sb.toString();
	}

	public Parametrizacao getParametrizacao() {
		return parametrizacao;
	}
}
