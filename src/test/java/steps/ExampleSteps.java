package steps;

import io.cucumber.java.en.*;
import pages.ExamplePage;

public class ExampleSteps {
    /*
     ** PAGE INSTANCE **
     */

    ExamplePage examplePage = new ExamplePage();

    @Given("the user is on the home screen of Underc0de.org")
    public void theUserIsOnTheHomeScreenOfUndercDeOrg() {
        examplePage.navigateToMainURL();
        examplePage.verifyHomeTitle();
    }

    @And("^the user click the \"(.*)\" button$")
    public void theUserClickTheButton(String button) {
        examplePage.clickButtonSwitch(button);
    }
    @And("^the user click the \"INGRESAR\" button of the forum$")
    public void theUserClickTheINGRESARButtonOfTheForum() {
        examplePage.clickIngresarButtonForum();
    }

    @And("^the user click the \"INGRESAR\" button of the forum modal$")
    public void theUserClickTheINGRESARButtonOfTheForumModal() {
        examplePage.clickIngresarButtonForumModal();
    }

    @And("^the user complete te user information. Usuario: \"(.*)\" Contraseña: \"(.*)\"$")
    public void theUserCompleteTeUserInformationUsuarioUserContraseñaPass(String user, String pass) {
        examplePage.completeLoginData(user, pass);
    }

    @Then("the user verifies that they are logged in.")
    public void theUserVerifiesThatTheyAreLoggedIn() {
        examplePage.verifyLogin();
    }

}