package testng;

import org.testng.annotations.DataProvider;

public class TestDataProviders {
    @DataProvider(name = "testDataCreation")
    public Object[][] testDataCreation() {
        return new Object[][]{
                // String TCNumber, String testPlayerAlias, String age, String gender,
                //                                String login, String password, String role, String screenName
                {"PTC2.1", "userToDelete", "18", "male", "UserLogin", "password123", "user", "UserName"},
                {"PTC2.2", "adminToDelete", "18", "female", "AdminLogin", "password123", "admin", "AdminName"}
        };
    }

    @DataProvider(name = "deletePlayersPositive")
    public Object[][] deletePlayersPositive() {
        return new Object[][]{
                // String TCNumber, String playerAlias, String editor, String description
                {"PTC2.1", "userToDelete", "supervisor", "Delete player with 'user' role by 'supervisor'"},
                {"PTC2.2", "adminToDelete", "supervisor", "Delete player with 'admin' role by 'supervisor'"}
        };
    }
}