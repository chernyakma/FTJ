package com.vaadin.testbenchexample;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;

public class TermLifeIT extends BaseLoginTest {



/*
		@Test
		public void addLoan() throws InterruptedException, IOException {
			VaadinSelectView getSelectButton = $( VaadinSelectView.class ).first();
			getSelectButton.getSelectItem().selectItemByIndex( 4 );
			SearchComponentView getPolicy = $( SearchComponentView.class ).first();
			getPolicy.searchByPolicy().sendKeys( "AM00004207" );
			getPolicy.searchButton().click();
			getPolicy.family().getCell( "AM00004207" ).click();
			NaviMenuView transaction = $( NaviMenuView.class ).first();
			transaction.transactionsWL().click();
			ScenarioView loanTransaction = $(ScenarioView.class).first();
			loanTransaction.addTransactionButton().click();
	//		EntryDialogContent selectTransaction = $(EntryDialogContent.class).first();
			TransactionPopUpPageView selectTransaction = $(TransactionPopUpPageView.class).first();
			selectTransaction.transactionType().selectByText( "Loan" );
			EntryDialogContent loan = $(EntryDialogContent.class).first();
			loan.loanAmount().sendKeys( Keys.chord( Keys.CONTROL, "a" ), "1000" );
			loan.disbursementMethod().selectByText( "Check Disbursement" );
			Assertions.assertEquals( "1,000.00",loan.loanAmount().getValue() );
			TransactionPopUpPageView getApproved = $(TransactionPopUpPageView.class).first();
			getApproved.approved().click();
			loan.okButton().click();
			ScenarioView processLoanTransaction = $(ScenarioView.class).first();
			processLoanTransaction.processInitialPremiumTransactionButton().click();

			VaadinConfirmDialogView confirm = $(VaadinConfirmDialogView.class).first();
			confirm.getSaveButton().click();
			ScenarioView transactionsPage = $(ScenarioView.class).first();
			waitUntil(driver -> !transactionsPage.progressBar().isDisplayed(), 80);
			ScenarioView transactionsPage = $(ScenarioView.class).first();
			transactionsPage.viewLoanTransactionButton().click();
			Thread.sleep( 5_000 );
			Assert.assertTrue( testBench().compareScreen( ImageFileUtil.getReferenceScreenshotFile(
					"Screenshot 2024-05-31 165800.png" ) ) );
			TransactionViewPage transactionPage = $(TransactionViewPage.class).first();
			transactionPage.cancel().click();

			NaviMenuView policy = $(NaviMenuView.class).first();
			policy.getPolicy().click();
			ScenarioView policyPage = $(ScenarioView.class).first();
			Assertions.assertEquals( "1,000.00",policyPage.loanBalance().getValue() );
			NaviMenuView transactions = $(NaviMenuView.class).first();
			transactions.transactionsWL().click();
			ScenarioView deleteTransaction = $(ScenarioView.class).first();
			deleteTransaction.reverseLoanTransactionButton().click();
			VaadinConfirmDialogView ok = $(VaadinConfirmDialogView.class).first();
			ok.getSaveButton().click();
			ScenarioView deleteLoanTransaction = $(ScenarioView.class).first();
			waitUntil(driver -> !deleteLoanTransaction.progressBar().isDisplayed(), 80);
	//		ScenarioView deleteLoanTransaction = $(ScenarioView.class).first();
			deleteLoanTransaction.deleteLoanTransactionButton().click();
			VaadinConfirmDialogView confirmation = $(VaadinConfirmDialogView.class).first();
			confirmation.getSaveButton().click();

		}
	*/
	@Test
	public void addIdentityTheftBusinessDB() throws Exception {
		VaadinSelectView getSelectButton = $(VaadinSelectView.class).first();
		getSelectButton.getSelectItem().selectItemByIndex(3);
		SearchComponentView getFamily = $(SearchComponentView.class).first();
		getFamily.searchBySSN().sendKeys("511-20-7950");
		getFamily.searchButton().click();
		getFamily.family().getCell("Palmer").click();
		NaviMenuView newBusiness = $(NaviMenuView.class).first();
		newBusiness.getNewBusiness().click();
		NewIllustrationView addNewBusiness = $(NewIllustrationView.class).first();
		addNewBusiness.getProductType().selectByText( "Identity Theft" );

		LocalDate today = LocalDate.now();
		LocalDate effectiveDate;

		if (today.getDayOfMonth() >= 29) {
			effectiveDate = today.plusMonths(1).withDayOfMonth(1);
		} else {
			effectiveDate = today;
		}

		addNewBusiness.effectiveDate().setDate(effectiveDate);
		addNewBusiness.coverageLevel2().selectItemByIndex(1);
		addNewBusiness.getOkButton().click();
		IllustrationView payment =$(IllustrationView.class).first();
		payment.paymentMode2().selectItemByIndex(0);

		NaviMenuView getReport = $(NaviMenuView.class).first();
		getReport.getReport().click();
		IllustrationView apply = $(IllustrationView.class).first();
		apply.getApplyButton().click();
		VaadinConfirmDialogView confirm = $(VaadinConfirmDialogView.class).first();
		confirm.getSaveButton().click();

		ApplicationView application = $( ApplicationView.class ).first();
		application.applicationReceived().selectByText( "Yes" );
		application.cashWithApplication().selectByText( "Yes" );
		Assertions.assertEquals( "Yes", application.cashWithApplication().getSelectedText() );
	//	application.cashAmount().sendKeys(Keys.chord(Keys.CONTROL, "a"), "3163.20");
		Assertions.assertEquals( "Yes", application.applicationReceived().getSelectedText() );
		application.applicationReceivedDate().setDate( LocalDate.now() );
		application.applicationSignedDate().setDate( LocalDate.now() );

		application.applicationStatus().selectByText("Active");
		application.policyIssuedDate().setDate(LocalDate.now());
//		Assertions.assertEquals( "MC001", application.getAgentNumber().getSelectedText() );
		application.applicationFundsReceived().selectByText( "Yes" );
		Assertions.assertEquals( "Yes", application.applicationFundsReceived().getSelectedText() );

		application.cashWithApplicationReceivedDate().setDate( LocalDate.now() );


		NaviMenuView iGO = $( NaviMenuView.class ).first();
		iGO.checkIGO().click();
		Thread.sleep( 3_000 );
		ApplicationView issue = $( ApplicationView.class ).first();

		application.issueButton().click();
		VaadinConfirmDialogView confirmButton = $( VaadinConfirmDialogView.class ).first();
		confirmButton.getSaveButton().click();
	//	VaadinConfirmDialogView ok = $(VaadinConfirmDialogView.class).first();
	//	ok.getSaveButton().click();
		ScenarioView getPolicyStatus = $(ScenarioView.class).first();
		Assertions.assertEquals( "Active",getPolicyStatus.policyStatus().getText() );


	}


	@Test
	public void addNYL10YearBusinessEFT() throws Exception {
		VaadinSelectView getSelectButton = $(VaadinSelectView.class).first();
		getSelectButton.getSelectItem().selectItemByIndex(3);
		SearchComponentView getFamily = $(SearchComponentView.class).first();
		getFamily.searchBySSN().sendKeys("511-20-7950");
		getFamily.searchButton().click();
		getFamily.family().getCell("Palmer").click();
		NaviMenuView newBusiness = $(NaviMenuView.class).first();
		newBusiness.getNewBusiness().click();
		NewIllustrationView addNewBusiness = $(NewIllustrationView.class).first();
		addNewBusiness.getProductType().selectByText( "Term Life" );
		addNewBusiness.getFaceAmount().sendKeys(Keys.chord(Keys.CONTROL, "a"), "150000");
		LocalDate today = LocalDate.now();
		LocalDate effectiveDate;

		if (today.getDayOfMonth() >= 29) {
			effectiveDate = today.plusMonths(1).withDayOfMonth(1);
		} else {
			effectiveDate = today;
		}

		addNewBusiness.effectiveDate().setDate(effectiveDate);
//		addNewBusiness.effectiveDate().setDate( LocalDate.of( 2026, 01, 01 ) );
//		addNewBusiness.effectiveDate().setDate(LocalDate.of( 2025, 8, 01 ) );
		addNewBusiness.inputFace().selectByText("Input Face");
		addNewBusiness.getProduct().selectByText("NYL 10 Year Term");
		addNewBusiness.getOkButton().click();
        IllustrationView paymentMode = $(IllustrationView.class).first();
		paymentMode.paymentMode().selectByText("Monthly");
		NaviMenuView getReport = $(NaviMenuView.class).first();
		getReport.getReport().click();
		IllustrationView apply = $(IllustrationView.class).first();
		apply.getApplyButtonReport().click();
		VaadinConfirmDialogView confirm = $(VaadinConfirmDialogView.class).first();
		confirm.getSaveButton().click();
		ApplicationView application = $( ApplicationView.class ).first();
		application.applicationReceived().selectByText( "Yes" );
		application.cashWithApplication().selectByText( "Yes" );
		application.paymentMethod().selectByText("Electronic Fund Transfer");
		application.draftDay().sendKeys("1");
		Assertions.assertEquals( "Yes", application.cashWithApplication().getSelectedText() );
	//	application.cashAmount().sendKeys(Keys.chord(Keys.CONTROL, "a"), "94.62");
		Assertions.assertEquals( "Yes", application.applicationReceived().getSelectedText() );
		application.applicationReceivedDate().setDate( LocalDate.now() );
		application.applicationSignedDate().setDate( LocalDate.now() );

		application.applicationStatus().selectByText("Active");
		application.policyIssuedDate().setDate(LocalDate.now());
//		Assertions.assertEquals( "MC001", application.getAgentNumber().getSelectedText() );
		application.applicationFundsReceived().selectByText( "Yes" );
		Assertions.assertEquals( "Yes", application.applicationFundsReceived().getSelectedText() );

		application.cashWithApplicationReceivedDate().setDate( LocalDate.now() );

		NaviMenuView iGO = $( NaviMenuView.class ).first();
		iGO.checkIGO().click();
		Thread.sleep( 3_000 );
		ApplicationView issue = $( ApplicationView.class ).first();

		application.issueButton().click();
		VaadinConfirmDialogView confirmButton = $( VaadinConfirmDialogView.class ).first();
		confirmButton.getSaveButton().click();
	//	VaadinConfirmDialogView ok = $(VaadinConfirmDialogView.class).first();
	//	ok.getSaveButton().click();
		ScenarioView getPolicyStatus = $(ScenarioView.class).first();
		Assertions.assertEquals( "Active",getPolicyStatus.policyStatus().getText() );

	}
	@Test
	public void addNYLRenewableEFT() throws Exception {
		VaadinSelectView getSelectButton = $(VaadinSelectView.class).first();
		getSelectButton.getSelectItem().selectItemByIndex(3);
		SearchComponentView getFamily = $(SearchComponentView.class).first();
		getFamily.searchBySSN().sendKeys("283-43-6642");
		getFamily.searchButton().click();
		getFamily.family().getCell("Palmer").click();
		NaviMenuView newBusiness = $(NaviMenuView.class).first();
		newBusiness.getNewBusiness().click();
		NewIllustrationView addNewBusiness = $(NewIllustrationView.class).first();
		addNewBusiness.getProductType().selectByText( "Term Life" );
		addNewBusiness.getFaceAmount().sendKeys(Keys.chord(Keys.CONTROL, "a"), "100000");
		LocalDate today = LocalDate.now();
		LocalDate effectiveDate;

		if (today.getDayOfMonth() >= 29) {
			effectiveDate = today.plusMonths(1).withDayOfMonth(1);
		} else {
			effectiveDate = today;
		}

		addNewBusiness.effectiveDate().setDate(effectiveDate);
//		addNewBusiness.effectiveDate().setDate( LocalDate.of( 2026, 01, 01 ) );
//		addNewBusiness.effectiveDate().setDate(LocalDate.of( 2025, 8, 01 ) );
		addNewBusiness.inputFace().selectByText("Input Face");
		addNewBusiness.getProduct().selectByText("NYL Renewable Term");
		addNewBusiness.getOkButton().click();
		IllustrationView paymentMode = $(IllustrationView.class).first();
//		paymentMode.paymentMode().selectByText("Monthly");
		NaviMenuView getReport = $(NaviMenuView.class).first();
		getReport.getReport().click();
		IllustrationView apply = $(IllustrationView.class).first();
		apply.getApplyButtonReport().click();
		VaadinConfirmDialogView confirm = $(VaadinConfirmDialogView.class).first();
		confirm.getSaveButton().click();
		ApplicationView application = $( ApplicationView.class ).first();
		application.applicationReceived().selectByText( "Yes" );
		application.cashWithApplication().selectByText( "Yes" );
		application.paymentMethod().selectByText("Electronic Fund Transfer");
		application.draftDay().sendKeys("1");
		Assertions.assertEquals( "Yes", application.cashWithApplication().getSelectedText() );
		//	application.cashAmount().sendKeys(Keys.chord(Keys.CONTROL, "a"), "94.62");
		Assertions.assertEquals( "Yes", application.applicationReceived().getSelectedText() );
		application.applicationReceivedDate().setDate( LocalDate.now() );
		application.applicationSignedDate().setDate( LocalDate.now() );

		application.applicationStatus().selectByText("Active");
		application.policyIssuedDate().setDate(LocalDate.now());
//		Assertions.assertEquals( "MC001", application.getAgentNumber().getSelectedText() );
		application.applicationFundsReceived().selectByText( "Yes" );
		Assertions.assertEquals( "Yes", application.applicationFundsReceived().getSelectedText() );

		application.cashWithApplicationReceivedDate().setDate( LocalDate.now() );

		NaviMenuView iGO = $( NaviMenuView.class ).first();
		iGO.checkIGO().click();
		Thread.sleep( 3_000 );
		ApplicationView issue = $( ApplicationView.class ).first();

		application.issueButton().click();
		VaadinConfirmDialogView confirmButton = $( VaadinConfirmDialogView.class ).first();
		confirmButton.getSaveButton().click();
		//	VaadinConfirmDialogView ok = $(VaadinConfirmDialogView.class).first();
		//	ok.getSaveButton().click();
		ScenarioView getPolicyStatus = $(ScenarioView.class).first();
		Assertions.assertEquals( "Active",getPolicyStatus.policyStatus().getText() );

	}



}




