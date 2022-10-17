package ru.netology.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.*;

public class TransferBetweenAccountsTest {
    @BeforeEach
    void setup() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Transfer from the second card to the first")
    void successfullyLoginCardTransferTest() {
        var authInfo = DataHelper.getAuthInfo();
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var numberCardTransfer = DataHelper.getCardNumber("0002"); // перевод с этой карты
        String cashText = "100"; // сумма перевода
        int amount = Integer.parseInt(cashText); // вытаскиваем число из String в int

        var dashboardPage = new LoginPage()
                .validLogin(authInfo)
                .validCode(verificationCode);

        //баланс карт до перевода
        int cardA1 = dashboardPage.getCardBalance("0001");
        int cardA2 = dashboardPage.getCardBalance("0002");

        dashboardPage.buttonFirstCard().transferCard(numberCardTransfer, cashText);

        //баланс карт после перевода
        int cardB1 = dashboardPage.getCardBalance("0001");
        int cardB2 = dashboardPage.getCardBalance("0002");

        Assertions.assertEquals(cardA1 + amount, cardB1);
        Assertions.assertEquals(cardA2 - amount, cardB2);
    }

    @Test
    @DisplayName("Transfer from the first card to the second")
    void successfullyLoginCardTransferTest2() {
        var authInfo = DataHelper.getAuthInfo();
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var numberCardTransfer = DataHelper.getCardNumber("0001"); // перевод с этой карты
        String cashText = "1000"; // сумма перевода
        int amount = Integer.parseInt(cashText); // вытаскиваем число из String в int

        var dashboardPage = new LoginPage()
                .validLogin(authInfo)
                .validCode(verificationCode);

        //баланс карт до перевода
        int cardA1 = dashboardPage.getCardBalance("0002");
        int cardA2 = dashboardPage.getCardBalance("0001");

        dashboardPage.buttonLastCard().transferCard(numberCardTransfer, cashText);

        //баланс карт после перевода
        int cardB1 = dashboardPage.getCardBalance("0002");
        int cardB2 = dashboardPage.getCardBalance("0001");

        Assertions.assertEquals(cardA1 + amount, cardB1);
        Assertions.assertEquals(cardA2 - amount, cardB2);
    }
}
