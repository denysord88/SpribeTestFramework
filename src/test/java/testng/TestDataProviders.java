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
                {"NTC1.7", "userEditor", "17", "male", "userEditorLogin", "019azAZ", "user", "userEditorScreenName"},
                {"PTC2.1", "userToDelete", "18", "male", "UserLogin", "password123", "user", "UserName"},
                {"PTC2.2", "adminToDelete", "18", "female", "AdminLogin", "password123", "admin", "AdminName"},
                {"PTC2.3", "userToDeleteByAdmin", "18", "male", "UserLogin", "password123", "user", "UserName"},
                {"PTC2.3", "adminEditor", "18", "female", "AdminLogin", "password123", "admin", "AdminName"},
                {"PTC2.4", "adminToDeleteHimself", "18", "male", "AdminLogin", "password123", "admin", "AdminName"},
                {"NTC2.6", "adminToDelete", "18", "male", "AdminLogin", "password123", "admin", "AdminName"},
                {"NTC2.6", "adminEditor", "18", "female", "AdminEditorLogin", "password123", "admin", "AdminEditorName"},
                {"NTC2.7", "userToDelete", "18", "female", "UserLogin", "password123", "user", "UserName"},
                {"NTC2.7", "userEditor", "18", "male", "UserEditorLogin", "password123", "user", "UserEditorName"},
                {"NTC2.8", "userToDelete", "18", "male", "UserLogin", "password123", "user", "UserName"},
                {"NTC2.10", "adminToDelete", "18", "male", "AdminLogin", "password123", "admin", "AdminName"},
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
                        "userScreenNameToUpdate"},
                {"NTC5.8", "tooYoung", "17", "male", "adminToUpdateLogin", "abcdef1", "admin",
                        "adminToUpdateScreenName"},
                {"NTC5.9", "tooOld", "17", "male", "adminToUpdateLogin", "abcdef1", "admin",
                        "adminToUpdateScreenName"},
                {"NTC5.11", "wrongGender", "54", "male", "adminToUpdateLogin", "abcdef1", "admin",
                        "adminToUpdateScreenName"},
                {"NTC5.17", "userToUpdate", "43", "male", "userToUpdateLogin", "abcdef1", "user",
                        "userToUpdateScreenName"},
                {"NTC5.17", "userEditor", "33", "male", "userLogin", "abcdef1", "user",
                        "userScreenName"},
                {"NTC5.18", "adminToUpdate", "22", "male", "adminToUpdateLogin", "abcdef1", "admin",
                        "adminToUpdateScreenName"},
                {"NTC5.18", "adminEditor", "44", "male", "adminLogin", "abcdef1", "admin",
                        "adminScreenName"},
                {"NTC5.19", "adminEditor", "55", "male", "adminLogin", "abcdef1", "admin", "adminScreenName"},
                {"NTC5.20", "userEditor", "44", "male", "userLogin", "abcdef1", "user", "userScreenName"},
                {"NTC5.21", "smallPassword", "22", "male", "adminToUpdateLogin", "abcdef1", "admin",
                        "adminToUpdateScreenName"},
                {"NTC5.22", "longPassword", "33", "male", "adminToUpdateLogin", "abcdef1", "admin",
                        "adminToUpdateScreenName"}
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

    @DataProvider(name = "createPlayersNegative")
    public Object[][] createPlayersNegative() {
        return new Object[][]{
                // String TCNumber, String editor, String age, String gender, String login,
                // String password, String role, String screenName, int expectedStatusCode, String description
                {"NTC1.6", "random", "17", "male", "createdByNotExistedEditorLogin", "019azAZ", "user",
                        "createdByNotExistedEditorScreenName", 403, "Not existed editor"},
                {"NTC1.7", "userEditor", "17", "male", "createdByUserLogin", "019azAZ", "user", "createdByUserScreenName", 403,
                        "editor with role 'user'"},
                {"NTC1.8", "supervisor", "16", "male", "userToCreate", "019azAZ", "user", "userScreenNameToCreate", 400,
                        "Create player with age 16 years old"},
                {"NTC1.9", "supervisor", "60", "male", "userToCreate", "019azAZ", "user", "userScreenNameToCreate", 400,
                        "Create player with age 60 years old"},
                {"NTC1.26", "supervisor", "17", "wrong", "userToCreate", "019azAZ", "user", "userScreenNameToCreate", 400,
                        "Create player with gender 'wrong'"},
                {"NTC1.30", "supervisor", "17", "male", "userToCreate", "12345678", "user", "userScreenNameToCreate", 400,
                        "Create player with password with only digits"},
                {"NTC1.31", "supervisor", "17", "male", "userToCreate", "abcdEFGhi", "user", "userScreenNameToCreate", 400,
                        "Create player with password with only letters"},
                {"NTC1.32", "supervisor", "17", "male", "userToCreate", "()!@#$%.&*+;%:?", "user", "userScreenNameToCreate", 400,
                        "Create player with password with not allowed characters ()!@#$%.&*+;%:?"},
                {"NTC1.35", "supervisor", "17", "male", "userToCreate", "019azA", "user", "userScreenNameToCreate", 400,
                        "Create player with password 6 characters long"},
                {"NTC1.36", "supervisor", "17", "male", "userToCreate", "0123456789abcdef", "user", "userScreenNameToCreate", 400,
                        "Create player with password 16 characters long"},
                {"NTC1.37", "supervisor", "17", "male", "userToCreate", "019azAZ", "supervisor", "userScreenNameToCreate", 400,
                        "Create player with role supervisor"},
                {"NTC1.38", "supervisor", "17", "male", "userToCreate", "019azAZ", "wrong", "userScreenNameToCreate", 400,
                        "Create player with role wrong"}
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

    @DataProvider(name = "deletePlayersNegative")
    public Object[][] deletePlayersNegative() {
        return new Object[][]{
                // String TCNumber, String playerAlias, String editor, int expectedStatusCode, String description
                {"NTC2.6", "adminToDelete", "adminEditor", 403, "Delete player with role 'admin' by different 'admin'"},
                {"NTC2.7", "userToDelete", "userEditor", 403, "Delete player with role 'user' by different 'user'"},
                {"NTC2.8", "userToDelete", "userToDelete", 403, "Delete player himself as 'user'"},
                {"NTC2.10", "adminToDelete", "random", 403, "Delete player with not existed editor"}
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

    @DataProvider(name = "updatePlayersNegative")
    public Object[][] updatePlayersNegative() {
        return new Object[][]{
                // String TCNumber, String playerAlias, String editor, String age, String gender, String login,
                // String password, String role, String screenName, int expectedStatusCode, String description
                {"NTC5.8", "tooYoung", "supervisor", "16", "male", "adminUpdatedLogin",
                        "0123456789abcDE", "admin", "adminScreenNameUpdated", 400,
                        "Update player with age 16"},
                {"NTC5.9", "tooOld", "supervisor", "60", "male", "adminUpdatedLogin",
                        "0123456789abcDE", "admin", "adminScreenNameUpdated", 400,
                        "Update player with age 60"},
                {"NTC5.11", "wrongGender", "supervisor", "59", "wrong", "adminUpdatedLogin",
                        "0123456789abcDE", "admin", "adminScreenNameUpdated", 400,
                        "Update player with wrong gender"},
                {"NTC5.17", "userToUpdate", "userEditor", "59", "male", "userUpdatedLogin",
                        "0123456789abcDE", "user", "userScreenNameUpdated", 403,
                        "Update player with role 'user' by another 'user'"},
                {"NTC5.18", "adminToUpdate", "adminEditor", "59", "male", "adminUpdatedLogin",
                        "0123456789abcDE", "admin", "adminScreenNameUpdated", 403,
                        "Update player with role 'admin' by another 'admin'"},
                {"NTC5.19", "supervisor", "adminEditor", "59", "male", "supervisorUpdatedLogin",
                        "0123456789abcDE", "supervisor", "supervisorUpdatedScreenName", 403,
                        "Update player with role 'supervisor' by 'admin'"},
                {"NTC5.20", "supervisor", "userEditor", "59", "male", "supervisorUpdatedLogin",
                        "0123456789abcDE", "supervisor", "supervisorUpdatedScreenName", 403,
                        "Update player with role 'supervisor' by 'user'"},
                {"NTC5.21", "smallPassword", "supervisor", "59", "male", "adminUpdatedLogin",
                        "12345s", "admin", "adminScreenNameUpdated", 400,
                        "Update player with minimum password length (6 characters)"},
                {"NTC5.22", "longPassword", "supervisor", "59", "male", "adminUpdatedLogin",
                        "0123456789abcDEF", "admin", "adminScreenNameUpdated", 400,
                        "Update player with password length (16 characters)"}
        };
    }
}