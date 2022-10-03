package ru.netology.page;

import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    public DashboardPage validCode(DataHelper.VerificationCode info) {
        $("[name=\"code\"]").setValue(info.getCode());
        $("[data-test-id=\"action-verify\"]").click();
        return new DashboardPage();
    }
}
