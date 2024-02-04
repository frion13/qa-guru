package allure.lesson;


import io.qameta.allure.*;
import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class LabelsTest {

    @Test
    @DisplayName("созданиу issue для авторизованного пользователя")
    @Feature("Issue в репозитории")
    @Story("Создание Isssue")
    @Owner("")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "GitHub", url = "https://github.com")
    public void testStaticLabels(){

    }

    @Test
    public void testDinamicLabels(){
        Allure.getLifecycle().updateTestCase(
                t -> t.setName("созданиу issue для авторизованного пользователя"));
        Allure.feature(("Issue в репозитории"));
        Allure.story("Создание Isssue");
        Allure.label("owner", "t");
        Allure.label("severity", SeverityLevel.BLOCKER.value());
        Allure.link("GitHub", "https://github.com");


    }
}
