package testng;

import org.testng.annotations.DataProvider;

public class TestDataProviders {
    @DataProvider(name = "testDataCreation")
    public Object[][] testDataCreation() {
        return new Object[][]{
                // String TCNumber, String testPlayerAlias, String age, String gender,
                //                                String login, String password, String role, String screenName
                {"PTC1.3", "adminCreator", "18", "female", "AdminCreatorLogin", "password123", "admin", "AdminCreatorName"},
                {"PTC1.4", "adminCreator", "18", "male", "AdminCreatorLogin", "password123", "admin", "AdminCreatorName"},
                {"PTC2.1", "userToDelete", "18", "male", "UserLogin", "password123", "user", "UserName"},
                {"PTC2.2", "adminToDelete", "18", "female", "AdminLogin", "password123", "admin", "AdminName"},
                {"PTC2.3", "userToDeleteByAdmin", "18", "male", "UserLogin", "password123", "user", "UserName"},
                {"PTC2.3", "adminEditor", "18", "female", "AdminLogin", "password123", "admin", "AdminName"},
                {"PTC2.4", "adminToDeleteHimself", "18", "male", "AdminLogin", "password123", "admin", "AdminName"},
                {"PTC3.1", "userById", "17", "male", "userToGetById", "019azAZ", "user", "userScreenNameToGetById"},
                {"PTC5.1", "adminToUpdate", "17", "male", "adminToUpdateLogin", "abcdef1", "admin",
                        "adminScreenNameToUpdate"},
                {"PTC5.2", "userToUpdate", "59", "female", "userToUpdateLogin", "0123456789abcDE", "user",
                        "userScreenNameToUpdate"},
                {"PTC5.3", "adminToUpdate", "20", "female", "adminToUpdateLogin", "0123456789abcDE", "admin",
                        "adminScreenNameToUpdate"},
                {"PTC5.4", "userToUpdate", "33", "female", "userToUpdateLogin", "0123456789abcDE", "user",
                        "userScreenNameToUpdate"},
                {"PTC5.4", "adminEditor", "50", "female", "adminEditorLogin", "0123456789abcDE", "admin",
                        "adminScreenNameEditor"},
                {"PTC5.5", "userToUpdate", "17", "male", "userToUpdateLogin", "0123456789abcDE", "user",
                        "userScreenNameToUpdate"}
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
                {"PTC2.2", "adminToDelete", "supervisor", "Delete player with 'admin' role by 'supervisor'"},
                {"PTC2.3", "userToDeleteByAdmin", "adminEditor", "Delete player with 'user' role by 'admin'"},
                {"PTC2.4", "adminToDeleteHimself", "adminToDeleteHimself", "Delete player himself as 'admin'"}
        };
    }

    @DataProvider(name = "getPlayerPositive")
    public Object[][] getPlayerPositive() {
        return new Object[][]{
                // String TCNumber, String playerAlias, String age, String gender, String login,
                // String password, String role, String screenName, String description
                {"PTC3.1", "userById", "17", "male", "userToGetById", "019azAZ", "user", "userScreenNameToGetById",
                        "Get details by id of player 17 years old with gender 'male' and role 'user' and " +
                                "password '019azAZ'"}
        };
    }

    @DataProvider(name = "updatePlayersPositive")
    public Object[][] updatePlayersPositive() {
        return new Object[][]{
                // String TCNumber, String playerAlias, String editor, String age, String gender, String login,
                // String password, String role, String screenName, String description
                {"PTC5.1", "adminToUpdate", "supervisor", "59", "male", "adminUpdatedLogin",
                        "0123456789abcDE", "admin", "adminScreenNameUpdated",
                        "Update player with role 'admin' all fields max length or value by supervisor"},
                {"PTC5.2", "userToUpdate", "supervisor", "17", "female", "userUpdatedLogin",
                        "012345cD", "user", "userScreenNameUpdated",
                        "Update player with role 'user' all fields min length or value by supervisor"},
                {"PTC5.3", "adminToUpdate", "adminToUpdate", "59", "female", "adminUpdatedLogin",
                        "0123456789abcDE", "admin", "adminScreenNameUpdated", "Update 'admin' himself"},
                {"PTC5.4", "userToUpdate", "adminEditor", "59", "female", "userUpdatedLogin",
                        "0123456789abcDE", "user", "userScreenNameUpdated", "Update 'user' by 'admin'"},
                {"PTC5.5", "userToUpdate", "userToUpdate", "59", "male", "userUpdatedLogin",
                        "0123456789abcDE", "user", "userScreenNameUpdated", "Update 'user' himself"}
        };
    }
}