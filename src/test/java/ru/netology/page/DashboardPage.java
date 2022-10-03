package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import lombok.val;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    // к сожалению, разработчики не дали нам удобного селектора, поэтому так
    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardPage() {
    }

    public int getCardBalance(String id) {
        String cardBalance = cards.findBy(Condition.text(DataHelper.getCardNumber(id).getNumber().substring(16, 19))).getText();
        return extractBalance(cardBalance);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public TransferCardPage buttonFirstCard() {
        ElementsCollection elmnts = $$("[data-test-id=\"action-deposit\"]");
        elmnts.get(0).click();

        return new TransferCardPage();
    }

    public TransferCardPage buttonLastCard() {
        ElementsCollection elmnts = $$("[data-test-id=\"action-deposit\"]");
        elmnts.get(1).click();

        return new TransferCardPage();
    }
}