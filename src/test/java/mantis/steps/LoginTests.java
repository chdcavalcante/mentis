package mantis.steps;

import static mantis.core.DriverFactory.getDriver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import mantis.core.BaseTest;
import mantis.core.DSL;
import mantis.pages.LoginPages;

public class LoginTests extends BaseTest {

	private LoginPages page;
	private DSL dsl;

	@Before
	public void inicializa() {
		page = new LoginPages();
		dsl = new DSL();
		getDriver().get("https://mantis-prova.base2.com.br/login_page.php?");

	}

	@Test
	public void loginSucesso() {
		assertTrue(dsl.elementoVisivel("//a[contains(.,'criar uma nova conta')]"));

		page.setLogin("Carlos_Cavalcante");
		page.clickEntrar();

		assertEquals("https://mantis-prova.base2.com.br/login_password_page.php", getDriver().getCurrentUrl());

		page.setPassword("C151179@");
		page.clickEntrar();

		dsl.aguardarCarregamentoUrl("https://mantis-prova.base2.com.br/my_view_page.php", 10);
		assertEquals("https://mantis-prova.base2.com.br/my_view_page.php", getDriver().getCurrentUrl());

	}

	@Test
	public void loginIncorreto() {
		assertTrue(dsl.elementoVisivel("//a[contains(.,'criar uma nova conta')]"));

		page.setLogin("Alfredo_Santos");
		page.clickEntrar();

		assertEquals("https://mantis-prova.base2.com.br/login_password_page.php", getDriver().getCurrentUrl());

		page.setPassword("C151179@");
		page.clickEntrar();

		assertEquals(
				"Sua conta pode estar desativada ou bloqueada ou o nome de usuário e a senha que você digitou não estão corretos.",
				dsl.capturarTexto("//div[@class='alert alert-danger']"));
	}

	@Test
	public void senhaIncorreta() {
		assertTrue(dsl.elementoVisivel("//a[contains(.,'criar uma nova conta')]"));

		page.setLogin("Carlos_Cavalcante");
		page.clickEntrar();

		assertEquals("https://mantis-prova.base2.com.br/login_password_page.php", getDriver().getCurrentUrl());

		page.setPassword("senha123");
		page.clickEntrar();

		assertEquals(
				"Sua conta pode estar desativada ou bloqueada ou o nome de usuário e a senha que você digitou não estão corretos.",
				dsl.capturarTexto("//div[@class='alert alert-danger']"));

	}

}
