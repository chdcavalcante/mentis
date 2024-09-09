package mantis.pages;

import static mantis.core.DriverFactory.getDriver;

import org.openqa.selenium.By;

import mantis.core.BasePage;

public class VerTarefasPages extends BasePage {

	private CriarTarefasPages page;

	public void cadastrarTarefa(String categoria, String resumo, String descrição) {
		page = new CriarTarefasPages();

		dsl.clicar(By.xpath("//span[contains(.,'Criar Tarefa')]"));
		page.selectDropDown("category_id", categoria);
		dsl.escrever("summary", resumo);
		dsl.escrever("description", descrição);
		dsl.clicar(By.xpath("//input[@value='Criar Nova Tarefa']"));

	}

	public void clickVerTarefas() {
		dsl.clicar(By.xpath("//span[contains(.,'Ver Tarefas')]"));
	}

	public void buscarTarefaPorId(String id) {
		dsl.escrever(By.xpath("//input[contains(@name,'bug_id')]"), id);
		getDriver().findElement(By.xpath("//input[contains(@name,'bug_id')]")).submit();
	}
}
