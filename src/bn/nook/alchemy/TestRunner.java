package bn.nook.alchemy;

import android.webkit.WebViewFragment;
import bn.nook.alchemy.screen.Dialog;
import bn.nook.alchemy.screen.Oobe;
import bn.nook.alchemy.screen.ScreenHelper;
import bn.nook.alchemy.utils.Constant;
import bn.nook.alchemy.utils.ScreenDefinition;
import bn.nook.alchemy.utils.TestManager;
import net.bugs.testhelper.TestHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static java.lang.Thread.dumpStack;
import static java.lang.Thread.sleep;

/**
 * Created by Alecs on 17.06.2014.
 */
public class TestRunner {


    public static void runTest() throws InterruptedException {
        int countUnknownScreen = 0;

        TestHelper th = TestManager.getInstance().getTestHelper();
        ScreenDefinition screenDefinition = new ScreenDefinition(TestManager.driver);

        while(true){

            switch (screenDefinition.detectedScreen()){
                case Constant.EnumScreen.OOBE_SCREEN:
                    TestManager.log("OOBE_SCREEN");
                    (new Oobe(TestManager.driver)).start();
                    break;

                case Constant.EnumScreen.DIALOG_SCREEN:
                    TestManager.log("DIALOG_SCREEN");
                    (new Dialog(TestManager.driver)).start();
                    break;

                case Constant.EnumScreen.UNKNOWN_SCREEN:
                    TestManager.log("UNKNOWN_SCREEN");
                    countUnknownScreen++;
                    break;
            }

            if(countUnknownScreen > 5){
                System.out.println("BREAK");
                break;
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        TestManager.startServer();
        runTest();
        TestManager.stopServer();
    }

}
