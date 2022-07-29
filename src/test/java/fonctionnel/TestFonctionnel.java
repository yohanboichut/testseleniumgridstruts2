package fonctionnel;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import java.net.URL;
import java.time.Duration;

public class TestFonctionnel {

    private final static String URL_DRIVER="http://172.31.0.2:4444/wd/hub"; // A mettre à jour en fonction
    // de l'IP du conteneur hub
    private final static String URL_APPLICATION = "http://172.31.0.3:8080/saisienotes-1.0-SNAPSHOT";
    // A mettre à jour en fonction de l'adresse IP du conteneur tomcat
    static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();



    public WebDriver getDriver() {
        return driver.get();
    }


    @BeforeEach
    public  void setUp() throws Exception {
        RemoteWebDriver webDriver = new RemoteWebDriver(new URL(URL_DRIVER), new ChromeOptions());
        webDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(40l));
        driver.set(webDriver);
    }

    @Test
    public void testSaisieNoteNumeroEtudiantIncorrecte() {
        String email = "yohan.boichut@univ-orleans.fr";
        String motDePasse ="password";
        String numeroEtudiant = "00000";
        String noteEtudiant = "12";
        String enseignement = "Test";

        getDriver().get(URL_APPLICATION);
        getDriver().findElement(By.name("email")).sendKeys(email);
        getDriver().findElement(By.name("motDePasse")).sendKeys(motDePasse);
        getDriver().findElement(By.name("valider")).click();
        WebElement selectionEnseignement = getDriver().findElement(By.name("enseignement"));
        Select selectEnseignement = new Select(selectionEnseignement);
        selectEnseignement.selectByValue("Test");
        getDriver().findElement(By.name("valider")).click();
        getDriver().findElement(By.name("numeroEtudiant")).sendKeys(numeroEtudiant);
        getDriver().findElement(By.name("note")).sendKeys(noteEtudiant);
        getDriver().findElement(By.name("valider")).click();

        Assertions.assertNotNull(getDriver().findElement(By.className("errorMessage")));





    }




    @AfterEach
    public void tearDown() {
        getDriver().quit();
    }




}
