package bn.nook.alchemy;

import bn.nook.alchemy.screen.Library;
import bn.nook.alchemy.screen.Oobe;
import bn.nook.alchemy.screen.ScreenHelper;
import bn.nook.alchemy.utils.Constant;
import bn.nook.alchemy.utils.ScreenDefinition;
import bn.nook.alchemy.utils.TestManager;
import io.selendroid.SelendroidCapabilities;
import io.selendroid.SelendroidConfiguration;
import io.selendroid.SelendroidDriver;
import io.selendroid.SelendroidLauncher;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;

import static java.lang.Thread.sleep;

/**
 * Created by Alecs on 17.06.2014.
 */
public class TestRunner {
    private static String pathToApp = TestManager.getInstance().getPathToAPK();
    private static SelendroidLauncher selendroidServer;
    private static String appID = "bn.ereader:4.0.0.158";
    private static WebDriver driver = null;

    public static void startServer(){
        if (selendroidServer != null){
            selendroidServer.stopSelendroid();
        }

        SelendroidConfiguration config = new SelendroidConfiguration();
        config.addSupportedApp(pathToApp);
        selendroidServer = new SelendroidLauncher(config);
        selendroidServer.launchSelendroid();

        SelendroidCapabilities cap = new SelendroidCapabilities(appID);
        try {
            driver = new SelendroidDriver(cap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stopServer(){
        if(driver != null){
            driver.quit();
        }
        if(selendroidServer != null){
            selendroidServer.stopSelendroid();
        }
    }

    public static void runTest() throws InterruptedException {
        int countUnknownScreen = 0;
        ScreenDefinition screenDefinition = new ScreenDefinition(driver);
        while(true){
            sleep(2000);
            switch (screenDefinition.detectedScreen()){
                case Constant.EnumScreen.OOBE_SCREEN:
                    TestManager.log("OOBE_SCREEN");
                    (new Oobe(driver)).start();
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
        startServer();
        runTest();
        stopServer();
    }

}
