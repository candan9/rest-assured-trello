package apitests;

import org.testng.annotations.Test;

public class End2EndTests {
	DeleteRequests deleteRequests = new DeleteRequests();
	PostRequests postRequests = new PostRequests();
	PutRequests putRequests = new PutRequests();
	/**
	 * Trello üzerinde bir board oluşturunuz.
	 * Oluşturduğunuz board’ a iki tane kart oluşturunuz.
	 * Oluştrduğunuz bu iki karttan random olacak sekilde bir tanesini güncelleyiniz.
	 * Oluşturduğunuz kartları siliniz.
	 * Oluşturduğunuz board’ u siliniz.

	 */
	@Test
	public void end2endTest() {
		String card1Name="testcard1";
		String card2Name="testcard2";
		String changedCardName = "changedtestcard2";
		String boardName = "testboard1";
		postRequests.createBoard(boardName);
		postRequests.createCard(card1Name,boardName);
		postRequests.createCard(card2Name,boardName);
		putRequests.changeCardName(card2Name,changedCardName,boardName);
		deleteRequests.deleteCard(card1Name,boardName);
		deleteRequests.deleteCard(changedCardName,boardName);
		deleteRequests.deleteBoard(boardName);
	}
}
