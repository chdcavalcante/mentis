package mentis.suites;

import static mantis.core.DriverFactory.killDriver;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import mantis.steps.CriarTarefasTests;
import mantis.steps.LoginTests;
import mantis.steps.VerTarefasTests;

@RunWith(Suite.class)
@SuiteClasses({
	CriarTarefasTests.class,
	LoginTests.class,
	VerTarefasTests.class
})

public class SuiteTeste {
	@AfterClass
	public static void finaliza() {
		killDriver();
	}
	

}
