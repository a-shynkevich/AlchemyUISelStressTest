package bn.nook.alchemy;

import bn.nook.alchemy.screen.*;
import bn.nook.alchemy.utils.Constant;
import bn.nook.alchemy.utils.ScreenDefinition;
import bn.nook.alchemy.utils.TestManager;

import static java.lang.Thread.sleep;


/**
 * Created by Alecs on 17.06.2014.
 */
public class TestRunner {
    private static TestManager testManager;

    public static void runTest() {
//        try {
//            sleep(60000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        int countUnknownScreen = 0;
        ScreenDefinition screenDefinition = new ScreenDefinition(TestManager.driver);

        while(true){
            TestManager.driver.switchTo().window(Constant.DriverSwitcher.NATIVE);
            switch (screenDefinition.detectedScreen()){
                case Constant.EnumScreen.OOBE_SCREEN:
                    TestManager.log("OOBE_SCREEN");
                    (new Oobe(TestManager.driver)).start();
                    break;
                case Constant.EnumScreen.DIALOG_SCREEN:
                    TestManager.log("DIALOG_SCREEN");
                    (new Dialog(TestManager.driver)).start();
                    break;
                case Constant.EnumScreen.HOME_SCREEN:
                    TestManager.log("HOME_SCREEN");
                    (new  Home(TestManager.driver)).start();
                    break;
                case Constant.EnumScreen.SIDE_BAR_SCREEN:
                    TestManager.log("SIDE_BAR_SCREEN");
                    (new SideBar(TestManager.driver)).start();
                    break;
                case Constant.EnumScreen.SETTINGS_SCREEN:
                    TestManager.log("SETTINGS_SCREEN");
                    (new Settings(TestManager.driver)).start();
                    break;
                case Constant.EnumScreen.UNKNOWN_SCREEN:
                    TestManager.log("UNKNOWN_SCREEN");
                    countUnknownScreen++;
                    break;

            }
            if(countUnknownScreen > 5){
                testManager.takeScreenshot();
                System.out.println("BREAK");
                break;
            }
        }
    }

    public static void main(String[] args) {
        testManager = TestManager.getInstance();
        TestManager.startServer();
        runTest();
//        TestManager.stopServer();
    }

}
