package story.book.test;

import org.junit.Test;

import story.book.model.DecisionBranch;
import story.book.view.Dashboard;
import android.test.ActivityInstrumentationTestCase2;

public class DecisionBranchTest extends ActivityInstrumentationTestCase2
<story.book.view.Dashboard> {

	DecisionBranch db;
	
	public DecisionBranchTest() {
		super(Dashboard.class);
		db = new DecisionBranch("Test string", 2);
	}
	
	@Test
	public void testCreation() {
		assertNotNull(db);
	}

	@Test
	public void testGetStartingFragment() {
		assertEquals(db.getDestinationID(), 2);
	}
	
	@Test
	public void testGetText() {
		assertEquals(db.getDecisionText(), "Test string");
	}
}
