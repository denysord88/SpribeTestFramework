package testng;

import org.testng.annotations.DataProvider;

public class TestDataProviders {
    @DataProvider(name = "coveragesRoles")
    public Object[][] coveragesRoles() {
        return new Object[][]{
                // String SAGCode, Source source, String appVersion, String description
                {"10029417", "750410547", "PROTECTION 'WSALUTE' AA11 SIPO", "'01','02'", "'OLIVA MICHELA','MARTINI MIRCO'"},
                {"525213", "750410547", "PROTECTION 'WSALUTE' AA11 SIPO", "'02'", "'MARTINI MIRCO'"},
                {"1494040", "750410562", "PROTECTION 'SEMPLICE CON ALLEANZA' AA12 SIPO", "'01','02'", "'GAMBELLI LUCA','RADAELLI PIETRO','CAFARELLA ENZA'"},
                {"8429568", "750410562", "PROTECTION 'SEMPLICE CON ALLEANZA' AA12 SIPO", "'02'", "'CAFARELLA ENZA'"},
                {"10639307", "750664947", "PROTECTION 'SEMPLICE CON ALLEANZA' AA09 SIPO", "'01'", "'MANONI LEONARDO','Sofia Manoni'"},
                {"1665753", "750664947", "PROTECTION 'SEMPLICE CON ALLEANZA' AA09 SIPO", "'02'", "'MANONI LEONARDO'"}
        };
    }
}