package bn.nook.alchemy;

import bn.nook.alchemy.screen.Oobe;
import bn.nook.alchemy.utils.Constant;
import bn.nook.alchemy.utils.ScreenDefinition;
import bn.nook.alchemy.utils.TestManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static java.lang.Thread.sleep;

/**
 * Created by Alecs on 17.06.2014.
 */
public class TestRunner {


    public static void runTest() throws InterruptedException {
        int countUnknownScreen = 0;
        ScreenDefinition screenDefinition = new ScreenDefinition(TestManager.driver);
//        sleep(600000);

        while(true){
            switch (screenDefinition.detectedScreen()){
                case Constant.EnumScreen.OOBE_SCREEN:
                    TestManager.log("OOBE_SCREEN");
                    (new Oobe(TestManager.driver)).start();
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
//        TestManager.stopServer();
    }

}
