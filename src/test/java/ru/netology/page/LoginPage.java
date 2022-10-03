package ru.netology.page;

import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        $("[name=\"login\"]").setValue(info.getLogin());
        $("[name=\"password\"]").setValue(info.getPassword());
        $("[data-test-id=\"action-login\"]").click();
        return new VerificationPage();
    }
}
