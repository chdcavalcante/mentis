package mantis.pages;

import org.openqa.selenium.By;

import mantis.core.BasePage;

public class LoginPages extends BasePage {

	public void setLogin(String login) {
		dsl.escrever("username", login);
	}

	public void setPassword(String password) {
		dsl.escrever("password", password);
	}

	public void clickEntrar() {
		dsl.clicar(By.xpath("//input[@value='Entrar']"));
	}
	
	

}
