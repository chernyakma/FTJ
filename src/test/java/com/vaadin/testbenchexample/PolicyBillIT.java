package com.vaadin.testbenchexample;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class PolicyBillIT extends BaseLoginTest{

    protected LocalDate initialPaidToDate;
    protected final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH);

    @Test
    public void addSuspense() throws InterruptedException {
        VaadinSelectView getSelectButton = $( VaadinSelectView.class ).first();
        getSelectButton.getSelectItem().selectItemByIndex( 6 );
        SearchComponentView getPolicy = $( SearchComponentView.class ).first();
        getPolicy.searchByPolicy().sendKeys( "14000003" );
        getPolicy.searchButton().click();
        getPolicy.family().getCell( "14000003" ).click();
        NaviMenuView addSuspense = $( NaviMenuView.class ).first();
        addSuspense.suspense2().click();
        ScenarioView addSuspenseButton = $( ScenarioView.class ).first();
        addSuspenseButton.addSuspenceButton().click();
        EntryDialogContent suspenseSource = $( EntryDialogContent.class ).first();
        suspenseSource.suspenseAmount().sendKeys( "500" );
        Assertions.assertEquals( "500",suspenseSource.suspenseAmount().getValue() );
        suspenseSource.suspenseSource().selectByText( "Check" );
        Assertions.assertEquals( "Check",suspenseSource.suspenseSource().getSelectedText() );
        suspenseSource.depositAccount().selectByText( "SPECIFIC SUSPENSE" );
        suspenseSource.processButton().click();

        ScenarioView checkSuspence=$(ScenarioView.class).first();
        Assertions.assertEquals( "$500.00",checkSuspence.suspenceBalance().getText() );

        checkSuspence.transferSuspenceButton().click();
        EntryDialogContent transferSuspence = $(EntryDialogContent.class).first();
        transferSuspence.fromAccount().selectByText( "SPECIFIC SUSPENSE" );
        //	EntryDialogContent transferSuspenceTo = $(EntryDialogContent.class).first();
        //	transferSuspence.note().sendKeys( "123" );
        //	transferSuspence.toAccount().focus();
        transferSuspence.toAccount().selectByText( "Family" );
        transferSuspence.searchFamily().sendKeys( "Palmer" );
        transferSuspence.search().doubleClick();
        transferSuspence.family().getCell( "Palmer" ).click();
        transferSuspence.toAccount().selectByText( "General Premium" );
        transferSuspence.transferAmount().sendKeys( "500" );
        Assertions.assertEquals( "500",transferSuspence.transferAmount().getValue() );
        transferSuspence.transferEffectveDate().setDate( LocalDate.now() );
        transferSuspence.note().sendKeys( "transfer to David Palmer" );
        transferSuspence.okButton().click();
        ScenarioView suspenceAmount=$(ScenarioView.class).first();
        Assertions.assertEquals( "$0.00",suspenceAmount.suspenceBalance().getText() );
        NaviMenuView transactions = $(NaviMenuView.class).first();
        transactions.policyTransactions().click();
        ScenarioView deleteTransaction = $(ScenarioView.class).first();
        deleteTransaction.reverseSecondTransactionButton().click();
        VaadinConfirmDialogView ok = $(VaadinConfirmDialogView.class).first();
        ok.getSaveButton().click();
        ScenarioView deleteFirstTransaction = $(ScenarioView.class).first();
        waitUntil(driver -> !deleteFirstTransaction.progressBar().isDisplayed(), 80);
        //		ScenarioView deleteLoanTransaction = $(ScenarioView.class).first();
        deleteFirstTransaction.deleteLoanTransactionButton().click();
        VaadinConfirmDialogView confirmation = $(VaadinConfirmDialogView.class).first();
        confirmation.getSaveButton().click();
        ScenarioView deleteSecondTransaction = $(ScenarioView.class).first();
        waitUntil(driver -> !deleteSecondTransaction.progressBar().isDisplayed(), 80);
        //		ScenarioView deleteLoanTransaction = $(ScenarioView.class).first();
        deleteSecondTransaction.deleteLoanTransactionButton().click();
        VaadinConfirmDialogView confirm = $(VaadinConfirmDialogView.class).first();
        confirm.getSaveButton().click();


    }


    @Test
    public void payDirectBill() {
        VaadinSelectView getSelectButton = $(VaadinSelectView.class).first();
        getSelectButton.getSelectItem().selectByText("Search Policy");
        waitUntil(driver -> $(SearchComponentView.class).exists(), 80);
        SearchComponentView getPolicy = $(SearchComponentView.class).first();
        getPolicy.searchByPolicy().sendKeys("POL-1003");
        getPolicy.searchButton().click();
        getPolicy.family().getCell("POL-1003").click();
        NaviMenuView transaction = $(NaviMenuView.class).first();
        transaction.policyTransactions().click();
        ScenarioView premiumTransaction = $(ScenarioView.class).first();
        String originalDateText = premiumTransaction.policyPaidToDate().getText();
        initialPaidToDate = LocalDate.parse(originalDateText, formatter);
        premiumTransaction.addTransactionButton().click();
        TransactionPopUpPageView selectTransaction = $(TransactionPopUpPageView.class).first();
        selectTransaction.transactionType().selectByText("Premium");
        waitUntil(driver -> $(EntryDialogContent.class).exists(), 160);
        EntryDialogContent premium = $(EntryDialogContent.class).first();
//        waitUntil(driver -> premium.isDisplayed(), 60);
        premium.premiumAmount().sendKeys(Keys.chord(Keys.CONTROL, "a"), "87.50");
//        premium.billingMonths().sendKeys(Keys.chord(Keys.CONTROL, "a"), "3");
        premium.okButton().click();
        ScenarioView processPremiumTransaction = $(ScenarioView.class).first();
        processPremiumTransaction.processInitialPremiumTransactionButton().click();
        VaadinConfirmDialogView confirm = $(VaadinConfirmDialogView.class).first();
        confirm.getSaveButton().click();
        ScenarioView transactionsPage = $(ScenarioView.class).first();

        waitUntil(driver -> !transactionsPage.progressBar().isDisplayed(), 80);
        //       ScenarioView paidToDate = $(ScenarioView.class).first();
        String updatedText = transactionsPage.policyPaidToDate().getText();
        LocalDate updatedDate = LocalDate.parse(updatedText, formatter);

        Assertions.assertEquals(initialPaidToDate.plusMonths(1), updatedDate);
        ScenarioView deleteTransaction = $(ScenarioView.class).first();
        deleteTransaction.reverseAddRiderTransactionButton().click();
        VaadinConfirmDialogView ok = $(VaadinConfirmDialogView.class).first();
        ok.getSaveButton().click();
        ScenarioView deleteLoanTransaction = $(ScenarioView.class).first();
        waitUntil(driver -> !deleteTransaction.progressBar().isDisplayed(), 80);

//		ScenarioView deleteLoanTransaction = $(ScenarioView.class).first();
        deleteLoanTransaction.deleteLoanTransactionButton().click();
        VaadinConfirmDialogView confirmation = $(VaadinConfirmDialogView.class).first();
        confirmation.getSaveButton().click();

    }

    @Test
    public void payEFT() {

        VaadinSelectView getSelectButton = $(VaadinSelectView.class).first();
        getSelectButton.getSelectItem().selectByText("Search Policy");

        SearchComponentView getPolicy = $(SearchComponentView.class).first();
        getPolicy.searchByPolicy().sendKeys("POL-1002");
        getPolicy.searchButton().click();
        getPolicy.family().getCell("POL-1002").click();

        NaviMenuView transaction = $(NaviMenuView.class).first();
        transaction.policyTransactions().click();

        ScenarioView payPremium = $(ScenarioView.class).first();

        //flexible parser
        String originalDateText = payPremium.policyPaidToDate().getText();
        initialPaidToDate = parseFlexibleDate(originalDateText);

        LocalDate originalDate = parseFlexibleDate(originalDateText);
        LocalDate newDate = originalDate.plusDays(1);
        payPremium.date().setDate(newDate);

        payPremium.cycle().click();
        VaadinConfirmDialogView cycleUp = $(VaadinConfirmDialogView.class).first();
        cycleUp.getSaveButton().click();
        waitUntil(driver -> !payPremium.progressBar().isDisplayed(), 80);
        String updatedText = payPremium.policyPaidToDate().getText();
        LocalDate updatedDate = LocalDate.parse(updatedText, formatter);

        Assertions.assertEquals(initialPaidToDate.plusMonths(1), updatedDate);

    }

    protected LocalDate parseFlexibleDate(String dateString) {
        dateString = dateString.trim(); // 🔑 trims extra spaces

        List<DateTimeFormatter> formatters = List.of(
                DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH),   // "Sep 1, 2025"
                DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH),  // "September 1, 2025"
                DateTimeFormatter.ofPattern("M/d/yyyy")                       // "4/1/2025"
        );

        for (DateTimeFormatter f : formatters) {
            try {
                return LocalDate.parse(dateString, f);
            } catch (Exception ignored) {}
        }

        throw new IllegalArgumentException("Could not parse date: " + dateString);
    }




}
