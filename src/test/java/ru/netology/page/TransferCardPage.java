package ru.netology.page;

import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class TransferCardPage {
    public DashboardPage transferCard(DataHelper.CardNumber from, String money) {
        $x("//*[@data-test-id=\"amount\"]//self::input").setValue(money);
        $("[placeholder=\"0000 0000 0000 0000\"]").setValue(from.getNumber());
        $("[data-test-id=\"action-transfer\"]").click();
        return new DashboardPage();
    }
}
