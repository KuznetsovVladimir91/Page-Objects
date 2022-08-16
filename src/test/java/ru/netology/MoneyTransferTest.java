package ru.netology;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.TransferPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {

    @Test
    void shouldTransferOnFirstCard() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        var dashboardPageBefore = new DashboardPage();
        var balanceFirstCardBeforeTransfer = dashboardPageBefore.getFirstCardBalance();
        var balanceSecondCardBeforeTransfer = dashboardPageBefore.getSecondCardBalance();
        dashboardPageBefore.transferFromFirstCard();
        int sum = 2000;
        var transferPage = new TransferPage();
        transferPage.transferToCard(sum, DataHelper.SecondCard());
        var dashboardPageAfter = new DashboardPage();
        var balanceFirstCardAfterTransfer = dashboardPageAfter.getFirstCardBalance();
        var balanceSecondCardAfterTransfer = dashboardPageAfter.getSecondCardBalance();
        assertEquals((balanceFirstCardBeforeTransfer + sum), balanceFirstCardAfterTransfer);
        assertEquals((balanceSecondCardBeforeTransfer - sum), balanceSecondCardAfterTransfer);
    }

    @Test
    void shouldTransferOnSecondCard() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        var dashboardPageBefore = new DashboardPage();
        var balanceFirstCardBeforeTransfer = dashboardPageBefore.getFirstCardBalance();
        var balanceSecondCardBeforeTransfer = dashboardPageBefore.getSecondCardBalance();
        dashboardPageBefore.transferFromSecondCard();
        int sum = 3000;
        var transferPage = new TransferPage();
        transferPage.transferToCard(sum, DataHelper.FirstCard());
        var dashboardPageAfter = new DashboardPage();
        var balanceFirstCardAfterTransfer = dashboardPageAfter.getFirstCardBalance();
        var balanceSecondCardAfterTransfer = dashboardPageAfter.getSecondCardBalance();
        assertEquals((balanceFirstCardBeforeTransfer - sum), balanceFirstCardAfterTransfer);
        assertEquals((balanceSecondCardBeforeTransfer + sum), balanceSecondCardAfterTransfer);
    }

    @Test
    void shouldNotTransferMoneyOnCard() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        var dashboardPageBefore = new DashboardPage();
        var balanceFirstCardBeforeTransfer = dashboardPageBefore.getFirstCardBalance();
        var balanceSecondCardBeforeTransfer = dashboardPageBefore.getSecondCardBalance();
        dashboardPageBefore.transferFromFirstCard();
        int sum = 80000;
        var transferPage = new TransferPage();
        transferPage.transferToCard(sum, DataHelper.SecondCard());
        var dashboardPageAfter = new DashboardPage();
        var balanceFirstCardAfterTransfer = dashboardPageAfter.getFirstCardBalance();
        var balanceSecondCardAfterTransfer = dashboardPageAfter.getSecondCardBalance();
        assertEquals((balanceFirstCardBeforeTransfer), balanceFirstCardAfterTransfer);
        assertEquals((balanceSecondCardBeforeTransfer), balanceSecondCardAfterTransfer);
    }

}
