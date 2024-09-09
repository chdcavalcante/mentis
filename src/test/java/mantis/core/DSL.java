package mantis.core;

import static mantis.core.DriverFactory.getDriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DSL {

	public void escrever(By by, String texto) {
	getDriver().findElement(by).sendKeys(texto);
	}

	public void escrever(String id, String texto) {
		escrever(By.id(id), texto);
	}

	public void clicar(By by) {
		getDriver().findElement(by).click();
	}

	public String capturarTexto(String elemento) {
		String texto = getDriver().findElement(By.xpath(elemento)).getText();

		return texto;
	}

	public boolean elementoVisivel(String elemento) {
		getDriver().findElement(By.xpath(elemento)).isDisplayed();

		return true;
	}

	public void aguardarCarregamentoUrl(String url, int time) {
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(time));
		wait.until(ExpectedConditions.urlToBe(url));
	}

	public void aguardarElemento(By elemento, int time) {
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(time));
		wait.until(ExpectedConditions.presenceOfElementLocated(elemento));
	}
	
	public String buscarTexto(By by) {
	String texto = getDriver().findElement(by).getText();
	
	return texto;
	}

}
