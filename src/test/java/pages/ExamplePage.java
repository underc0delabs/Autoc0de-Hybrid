package pages;

import com.core.utility.MasterPage;
import org.testng.Assert;


import static locators.ExampleLocators.*;

public class ExamplePage extends MasterPage {
    public void navigateToMainURL() {
        auto_openURLInBrowser();
    }

    public void verifyHomeTitle() {
        Assert.assertTrue(page.get().isVisible(HOME_TITLE_XPATH));
    }

    public void clickButtonSwitch(String button) {
        switch (button.toLowerCase()) {
            case "foro":
                clickOnForoButton();
                break;
            case "ingresar":
                clickOnIngresarButtons();
                break;
            default:
                Assert.assertEquals(button,"No button matched", "Invalid button options");
        }
    }

    private void clickOnIngresarButtons() {
        auto_setClickElement(FORO_BUTTON_XPATH);
    }

    private void clickOnForoButton() {
        auto_setClickElement(FORO_BUTTON_XPATH);
    }

    public void clickIngresarButtonForum() {
        auto_setClickElement(INGRESAR_LOGIN_BUTTONS_XPATH);
    }

    public void clickIngresarButtonForumModal() {
        auto_setClickElement(INGRESAR_LOGIN_MODAL_BUTTONS_XPATH);
    }

    public void completeLoginData(String user, String pass) {
        auto_setTextToInput(USUARIO_INPUT_XPATH, user);
        auto_setTextToInput(PASS_INPUT_XPATH, pass);
    }

    public void verifyLogin() {
        auto_setClickElement(USER_LOGIN_IMG_XPATH);
        Assert.assertTrue(auto_getElementText(USER_LOGIN_LBL_XPATH).toLowerCase().contains("autoc0de"), "Error at login - Invalid username or passwor");
    }
}