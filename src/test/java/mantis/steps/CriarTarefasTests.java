package mantis.steps;

import static mantis.core.DriverFactory.getDriver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import mantis.core.BaseTest;
import mantis.core.DSL;
import mantis.pages.CriarTarefasPages;

public class CriarTarefasTests extends BaseTest {

	private CriarTarefasPages page;
	private DSL dsl;

	@Before
	public void inicializa() {
		page = new CriarTarefasPages();
		dsl = new DSL();
		getDriver().get("https://mantis-prova.base2.com.br/login_page.php");
		page.realizarLogin("Carlos_Cavalcante", "C151179@");
		dsl.aguardarCarregamentoUrl("https://mantis-prova.base2.com.br/my_view_page.php", 10);

	}

	@Test
	public void cadastrarTarefaComSucesso() throws InterruptedException {
		assertEquals("https://mantis-prova.base2.com.br/my_view_page.php", getDriver().getCurrentUrl());
		page.setCriarTarefa();
		page.selectDropDown("category_id", "[Todos os Projetos] nova categoria");
		page.selectDropDown("reproducibility", "sempre");
		page.selectDropDown("severity", "obstáculo");
		page.selectDropDown("priority", "urgente");
		dsl.escrever("platform", "teste");
		dsl.escrever("os", "2020");
		dsl.escrever("os_build", "2.1");
		dsl.escrever("summary", "Testando automação 1.2");
		dsl.escrever("description", "Teste escrita no campo (descrição) 1.2");
		dsl.escrever("steps_to_reproduce", "Teste escrita no campo (Passos para reproduzir) 1.2");
		dsl.escrever("additional_info", "Teste escrita no campo (Passos para reproduzir)");
		dsl.escrever("tag_string", "tagTest");
		dsl.clicar(By.xpath("//span[contains(.,'privado')]"));
		dsl.clicar(By.xpath("//input[@value='Criar Nova Tarefa']"));
		Thread.sleep(5000);
		dsl.aguardarElemento(By.xpath("//h4[contains(.,'Ver Detalhes da Tarefa')]"), 10);
		assertTrue(dsl.elementoVisivel("//h4[contains(.,'Ver Detalhes da Tarefa')]"));
	}

	@Test
	public void validarCamposObrigatoriosCategoria() {
		assertEquals("https://mantis-prova.base2.com.br/my_view_page.php", getDriver().getCurrentUrl());

		page.setCriarTarefa();
		dsl.escrever("summary", "Testando automação 1.0");
		dsl.escrever("description", "Teste escrita no campo (descrição)");
		dsl.clicar(By.xpath("//input[@value='Criar Nova Tarefa']"));

		assertEquals("Um campo necessário 'category' estava vazio. Por favor, verifique novamente suas entradas.",
				dsl.capturarTexto("//div[@class='alert alert-danger']/p[2]"));
	}

	@Test
	public void validarCamposObrigatoriosResumo() {
		assertEquals("https://mantis-prova.base2.com.br/my_view_page.php", getDriver().getCurrentUrl());

		page.setCriarTarefa();
		page.selectDropDown("category_id", "[Todos os Projetos] categoria teste");
		dsl.escrever("description", "Teste escrita no campo (descrição)");
		dsl.clicar(By.xpath("//input[@value='Criar Nova Tarefa']"));

		assertEquals("https://mantis-prova.base2.com.br/bug_report_page.php", getDriver().getCurrentUrl());
	}

	@Test
	public void validarCamposObrigatoriosDescricao() {
		assertEquals("https://mantis-prova.base2.com.br/my_view_page.php", getDriver().getCurrentUrl());

		page.setCriarTarefa();
		page.selectDropDown("category_id", "[Todos os Projetos] categoria teste");
		dsl.escrever("summary", "Testando automação 1.0");
		dsl.clicar(By.xpath("//input[@value='Criar Nova Tarefa']"));

		assertEquals("https://mantis-prova.base2.com.br/bug_report_page.php", getDriver().getCurrentUrl());
	}

}
