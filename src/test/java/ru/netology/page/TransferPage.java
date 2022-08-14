package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    private SelenideElement amount = $("[data-test-id='amount'] input");
    private SelenideElement from = $("[data-test-id='from'] input");
    private SelenideElement transfer = $("[data-test-id='action-transfer']");
    private SelenideElement error = $(".notification__content");

    public TransferPage() {
    }

    public DashboardPage transferToCard(int sum, DataHelper.CardNumber cardInfo) {
        amount.setValue(String.valueOf(sum));
        from.setValue(cardInfo.getNumber());
        transfer.click();
        return new DashboardPage();
    }

    public DashboardPage transferError(int sum, DataHelper.CardNumber cardInfo) {
        amount.setValue(String.valueOf(sum));
        from.setValue(cardInfo.getNumber());
        transfer.click();
        error.shouldBe(visible).shouldHave(exactText("Превышен остаток по карте"));
        return new DashboardPage();
    }

}
