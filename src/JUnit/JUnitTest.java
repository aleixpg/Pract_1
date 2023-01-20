package JUnit;
import org.junit.*;
public class Test1 {
    //JUnit call this method one time before all tests
    @BeforeClass
    public static void setUp(){
        //Place code here for any set up required prior to tests
    }
    @AfterClass
    public static void finished(){
        //Place code here for any clean up that should be done after tests are finished
        
    }
}
