package testng;

import org.testng.annotations.DataProvider;

public class TestDataProviders {
    @DataProvider(name = "testDataCreation")
    public Object[][] testDataCreation() {
        return new Object[][]{
                // String TCNumber, String testPlayerAlias, String age, String gender,
                //                                String login, String password, String role, String screenName
                {"PTC2.1", "userToDelete", "18", "male", "UserLogin", "password123", "user", "UserName"},
                {"PTC2.2", "adminToDelete", "18", "female", "AdminLogin", "password123", "admin", "AdminName"},
                {"PTC1.3", "adminCreator", "18", "female", "AdminCreatorLogin", "password123", "admin", "AdminCreatorName"},
                {"PTC1.4", "adminCreator", "18", "male", "AdminCreatorLogin", "password123", "admin", "AdminCreatorName"}
        };
    }

    @DataProvider(name = "createPlayersPositive")
    public Object[][] createPlayersPositive() {
        return new Object[][]{
                // String TCNumber, String editor, String age, String gender, String login,
                // String password, String role, String screenName, String description
                {"PTC1.1", "supervisor", "17", "male", "userToCreate", "019azAZ", "user", "userScreenNameToCreate",
                        "Create player 17 years old by role 'supervisor' with gender 'male' and role 'user' and " +
                                "password '019azAZ'"},
                {"PTC1.2", "supervisor", "59", "Female", "adminToCreate", "0123456789abcde", "admin",
                        "adminScreenNameToCreate", "Create player 59 years old by role 'supervisor' with gender " +
                        "'Female' and role 'admin' and password '0123456789abcde'"},
                {"PTC1.3", "adminCreator", "20", "female", "adminToCreateByAdmin", "0123456789ABCDE", "admin",
                        "adminScreenNameToCreateByAdmin", "Create player 20 years old by role 'admin' with gender 'female' " +
                        "and role 'admin' and password '0123456789ABCDE'"},
                {"PTC1.4", "adminCreator", "55", "MALE", "userToCreateByAdmin", "abcde0123456789", "user",
                        "userScreenNameToCreateByAdmin", "Create player 55 years old by role 'admin' with gender 'MALE' and" +
                        " role user and password 'abcde0123456789'"}
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