package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {
    private SelenideElement monthInput = $(".react-datepicker__month-select"),
            yearInput = $(".react-datepicker__year-select"),
            dayInput = $(".react-datepicker__day--030:not(.react-datepicker__day--outside-month)");

    public CalendarComponent setDate(String day, String month, String year) {
        monthInput.selectOption(month);
        yearInput.selectOption(year);
        dayInput.click();


        return this;
    }
}

