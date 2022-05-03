package testing;
import org.junit.Before;
import org.junit.Test;
import net.sourceforge.jwebunit.junit.WebTester;



public class UnregisteredUserGUIWebTestCase {
	private WebTester tester;

    /**
     * Create a new WebTester object that performs the test.
     */
    @Before
    public void prepare() {
        tester = new WebTester();
        tester.setBaseUrl("http://localhost:8080/VR/");
    }
    
    @Test 
    public void testregister() {
    	//Start testing for unregisterGUI
    	tester.beginAt("register");
    	
    	//Check all components of the registering form
    	tester.assertTextPresent("Required Information");
    	tester.assertTextPresent("Welcome to our little demonstration on the MRA Webapp, Please Register");
    	tester.assertTextPresent("Username");
    	tester.assertTextPresent("Age");
    	tester.assertTextPresent("Email");
    	
    	tester.assertFormElementPresent("username");
    	tester.assertFormElementPresent("age");
    	tester.assertFormElementPresent("email");
    	
    	tester.assertSubmitButtonPresent();
    	
    	tester.setTextField("username", "Mahmoud");
    	tester.setTextField("age", "104");
    	tester.setTextField("email","mahmoud@gmail.com");
    	
    }
}
