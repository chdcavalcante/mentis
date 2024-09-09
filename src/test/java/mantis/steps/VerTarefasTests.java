package mantis.steps;

import static mantis.core.DriverFactory.getDriver;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import mantis.core.BaseTest;
import mantis.core.DSL;
import mantis.core.DriverFactory;
import mantis.pages.CriarTarefasPages;
import mantis.pages.VerTarefasPages;

public class VerTarefasTests extends BaseTest {

	private VerTarefasPages page;
	private CriarTarefasPages pageCT;
	private DSL dsl;

	@Before
	public void inicializa() {
		dsl = new DSL();
		pageCT = new CriarTarefasPages();
		page = new VerTarefasPages();
		getDriver().get("https://mantis-prova.base2.com.br/login_page.php");

		pageCT.realizarLogin("Carlos_Cavalcante", "C151179@");
		dsl.aguardarCarregamentoUrl("https://mantis-prova.base2.com.br/my_view_page.php", 10);

	}


	@Test
	public void vizualizarTarefaCadastrada() {

		pageCT.setCriarTarefa();
		page.cadastrarTarefa("[Todos os Projetos] categoria teste", "Testando automação 4",
				"teste campo descrição 4");
		page.clickVerTarefas();

		WebElement element = DriverFactory.getDriver().findElement(By.xpath("//table[@id='buglist']//tr[1]//td[4]"));
		String id = element.getText();

		assertEquals(id,
				getDriver().findElement(By.xpath("//td[@class='column-id'][contains(.,'" + id + "')]")).getText());

	}

	@Test
	public void buscarTarefaPorId() {
		assertEquals("https://mantis-prova.base2.com.br/my_view_page.php", getDriver().getCurrentUrl());

		page.buscarTarefaPorId("0001606");

		assertEquals("0001606", dsl.buscarTexto(By.xpath("//td[@class='bug-id']")));
		assertEquals("Carlos Cavalcante´s Project", dsl.buscarTexto(By.xpath("//td[@class='bug-project']")));
		assertEquals("Carlos_Cavalcante ", dsl.buscarTexto(By.xpath("//td[@class='bug-reporter']")));
		assertEquals("normal", dsl.buscarTexto(By.xpath("//td[@class='bug-priority']")));
		assertEquals("novo", dsl.buscarTexto(By.xpath("//td[@class='bug-status']")));
		assertEquals("[Todos os Projetos] categoria teste", dsl.buscarTexto(By.xpath("//td[@class='bug-category']")));
		assertEquals("público", dsl.buscarTexto(By.xpath("//td[@class='bug-view-status']")));
		assertEquals("pequeno", dsl.buscarTexto(By.xpath("//td[@class='bug-severity']")));
		assertEquals("aberto", dsl.buscarTexto(By.xpath("//td[@class='bug-resolution']")));
		assertEquals("2024-09-09 01:59", dsl.buscarTexto(By.xpath("//td[@class='bug-date-submitted']")));
		assertEquals("2024-09-09 01:59", dsl.buscarTexto(By.xpath("//td[@class='bug-last-modified']")));
		assertEquals("não se tentou", dsl.buscarTexto(By.xpath("//td[@class='bug-reproducibility']")));

	}

}
