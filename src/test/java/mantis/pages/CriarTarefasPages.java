package mantis.pages;

import static mantis.core.DriverFactory.getDriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import mantis.core.BasePage;

public class CriarTarefasPages extends BasePage {

	private LoginPages page;

	public void realizarLogin(String login, String password) {
		page = new LoginPages();

		page.setLogin(login);
		page.clickEntrar();
		page.setPassword(password);
		page.clickEntrar();
	}

	public void setCriarTarefa() {
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
		WebElement criarTarefaBtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(.,'Criar Tarefa')]")));
		
		criarTarefaBtn.click();
	}

	public void selectDropDown(String id, String categoria) {
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		combo.selectByVisibleText(categoria);
	}

}
