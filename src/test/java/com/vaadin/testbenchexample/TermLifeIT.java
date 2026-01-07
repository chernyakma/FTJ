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
	public void addNewBusiness() throws Exception {
		VaadinSelectView getSelectButton = $(VaadinSelectView.class).first();
		getSelectButton.getSelectItem().selectItemByIndex(3);
		SearchComponentView getFamily = $(SearchComponentView.class).first();
		getFamily.searchBySSN().sendKeys("293-75-5647");
		getFamily.searchButton().click();
		getFamily.family().getCell("Palmer").click();
		NaviMenuView newBusiness = $(NaviMenuView.class).first();
		newBusiness.getNewBusiness().click();
		NewIllustrationView addNewBusiness = $(NewIllustrationView.class).first();
//		addNewBusiness.getProductType().selectByText( "Immediate Annuity" );
		addNewBusiness.getFaceAmount().sendKeys(Keys.chord(Keys.CONTROL, "a"), "150000");
//		addNewBusiness.effectiveDate().setDate(LocalDate.now());
		addNewBusiness.effectiveDate().setDate(LocalDate.now());
		addNewBusiness.getProduct().selectByText("NYL Decreasing Term");
		addNewBusiness.coverageLevel().selectByText("High Option");
		addNewBusiness.inputFace().selectByText("Input Face");
		addNewBusiness.getOkButton().click();

		NaviMenuView getReport = $(NaviMenuView.class).first();
		getReport.getReport().click();
		IllustrationView apply = $(IllustrationView.class).first();
		apply.getApplyButtonReport().click();
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
	public void addNewBusinessEFT() throws Exception {
		VaadinSelectView getSelectButton = $(VaadinSelectView.class).first();
		getSelectButton.getSelectItem().selectItemByIndex(3);
		SearchComponentView getFamily = $(SearchComponentView.class).first();
		getFamily.searchBySSN().sendKeys("293-75-5647");
		getFamily.searchButton().click();
		getFamily.family().getCell("Palmer").click();
		NaviMenuView newBusiness = $(NaviMenuView.class).first();
		newBusiness.getNewBusiness().click();
		NewIllustrationView addNewBusiness = $(NewIllustrationView.class).first();
//		addNewBusiness.getProductType().selectByText( "Immediate Annuity" );
		addNewBusiness.getFaceAmount().sendKeys(Keys.chord(Keys.CONTROL, "a"), "150000");
		addNewBusiness.effectiveDate().setDate(LocalDate.now());
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


/*
		ApplicationView application = $( ApplicationView.class ).first();
		application.getAgentNumber().sendKeys("MC001");
		Thread.sleep( 3_000 );
	//	application.getAgentNumber().sendKeys(Keys.ARROW_DOWN);
	//	application.getAgentNumber().sendKeys(Keys.ENTER);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].dispatchEvent(new KeyboardEvent('keydown', {'key': 'ArrowDown'}));", application.getAgentNumber());
		application.getAgentNumber().sendKeys( Keys.ENTER );

		application.applicationReceived().selectByText( "Yes" );
		Assertions.assertEquals( "Yes", application.applicationReceived().getSelectedText() );
		application.applicationReceivedDate().setDate( LocalDate.now() );
		application.applicationSignedDate().setDate( LocalDate.now() );
		Assertions.assertEquals( "MC001 - Mariia Cherniak", application.getAgentNumber().getSelectedText() );
		application.applicationFundsReceived().selectByText( "Yes" );
		Assertions.assertEquals( "Yes", application.applicationFundsReceived().getSelectedText() );
		application.paymentMethod().selectByText("Electronic Fund Transfer");
//		application.cashWithApplication().selectByText( "Yes" );
//		Assertions.assertEquals( "Yes", application.cashWithApplication().getSelectedText() );
//		application.cashWithApplicationReceivedDate().setDate( LocalDate.now() );

		NaviMenuView getDocument = $( NaviMenuView.class ).first();
		getDocument.getDocument().click();
		ApplicationView report = $( ApplicationView.class ).first();
		report.downloadButton().click();
		Thread.sleep( 3_000 );
		application.compareAndDeleteDownloadedPdfFPIUL();

		application.threeDotsButton().click();
		WebElement noteList = findElement( By.xpath( "//*[@class='vaadin-menu-item']" ) );
		noteList.click();
		Thread.sleep( 3_000 );
		EntryDialogContent addNote = $( EntryDialogContent.class ).first();
		addNote.addNoteButton().click();
		addNote.noteText().setValue( "document 1" );
		addNote.expiresDate().setDate( LocalDate.of( 2024, 12, 12 ) );
		addNote.attachButton().click();
		addNote.attachmentType().selectByText( "Final Application" );
		//	addNote.uploadFileButton().upload( new File( "C:\\Users\\MariiaCherniak\\Downloads\\Final Application.pdf") );
		String filePathApp = System.getenv("UPLOAD_FILE_PATH_App");
		File fileToUploadApp = new File(filePathApp).getAbsoluteFile();
		addNote.uploadFileButton().upload(fileToUploadApp);
		Thread.sleep( 5_000 );
		addNote.attachButton().click();
		addNote.attachmentType().selectByText( "Final Illustration" );
		//	addNote.uploadFileButton().upload( new File( "C:\\Users\\MariiaCherniak\\Downloads\\Final Illustration .pdf" ) );
		String filePathIll = System.getenv("UPLOAD_FILE_PATH_ILL");
		File fileToUploadIll = new File(filePathIll).getAbsoluteFile();
		addNote.uploadFileButton().upload(fileToUploadIll);
		addNote.okButton().click();
		addNote.closeButton().click();
*/
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




