package steps;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;


public class ExampleAPISteps {
    RequestSpecification request;
    Response response;

    @Given("^pokemon API is provided$")
    public void login_API_is_provided() {
        RestAssured.baseURI  = System.getProperty("apiURL");
        request = RestAssured.given();
    }

    @When("^user perform a \"(.*)\" method on the \"(.*)\" endpoint$")
    public void userCallTheEndpointEndpoint(String method, String endpoint) {
        switch (method.toLowerCase()) {
            case "get" -> response = request.when().get(endpoint);
            case "post" -> response = request.when().post(endpoint);
            case "delete" -> response = request.when().delete(endpoint);
            case "put" -> response = request.when().put(endpoint);
            case "patch" -> response = request.when().patch(endpoint);
            default -> Assert.fail("No valid method");
        }
    }

    @Then("^user verify a \"(.*)\" status code$")
    public void userVerifyAStatusCodeStatusCode(int statusCode) {
        response.then().statusCode(statusCode);
    }
}