# Spribe API Test Framework

## 🛠 Configuration

To add a configuration file:

1. Copy the `.env.template` file.
2. Rename it to `.env`.
3. Replace the value of `APP_HOST` with the real IP address of the backend.

---

## 🧪 TestNG Groups

There are 3 main TestNG groups of tests:

- **`smoke`** – checks 1 happy path of each API endpoint separately.
- **`e2e`** – end-to-end test that uses all API endpoints together.
- **`functional`** – includes two subgroups:
    - **`positive`** – validates correct values in requests.
    - **`negative`** – covers negative test cases.

---

## 🚀 Running Tests

You can run tests using Maven and one of the predefined TestNG XML files:

- `TestNG_smoke.xml` – run smoke tests for each API endpoint
- `TestNG_e2e.xml` – run only the end-to-end test
- `TestNG_functional.xml` – run all positive and negative functional tests
- `TestNG_all.xml` – run **all** existing tests

### 📦 Maven command examples

Run all tests (default):

```bash
mvn clean test --no-transfer-progress
```

Run specific suite (e.g. e2e):

```bash
mvn clean test --no-transfer-progress -DtestSuite=TestNG_e2e.xml
```
### 📊 Allure Report

After running the tests, you can view the Allure report in two ways:

1. Serve locally in a browser:
```bash
mvn allure:serve
```
2. Generate a local HTML folder for export:
```bash
mvn allure:report
```
Report files will be saved in: `target/site/allure-maven-plugin`

### 📄 Additional Files
- `SPRIBE checklist.txt` – all test cases after exploratory testing in Postman
- `SPRIBE checklist actual results.txt` – requests & actual response results after exploratory testing
- `SpribeTestFramework API.postman_collection.json` – Postman collection
- `openapi.json` – OpenAPI specification
- `final-allure-report.zip` - archived Allure report after local test execution of all tests