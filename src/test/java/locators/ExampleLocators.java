package locators;

public class ExampleLocators {
    public static final String HOME_TITLE_XPATH = "//*[@id=\"top\"]/section[1]/div/div/div/div/h2";
    public static final String FORO_BUTTON_XPATH = "//*[@id=\"materialize-menu\"]/ul/li[2]/a";
    public static final String INGRESAR_LOGIN_BUTTONS_XPATH = "//*[contains(@onclick,'Iniciar')]";
    public static final String INGRESAR_LOGIN_MODAL_BUTTONS_XPATH = "//*[contains(@value,'Iniciar')]";
    public static final String USUARIO_INPUT_XPATH = "//input[@id='ajax_loginuser']";
    public static final String PASS_INPUT_XPATH = "//input[@id='ajax_loginpass']";
    public static final String USER_LOGIN_IMG_XPATH = "//div/img[@class='avatar']";
    public static final String USER_LOGIN_LBL_XPATH = "(//*[contains(text(),'Autoc0de')])[2]";
}