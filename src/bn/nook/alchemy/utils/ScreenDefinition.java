package bn.nook.alchemy.utils;

import bn.nook.alchemy.screen.Dialog;
import bn.nook.alchemy.screen.Home;
import bn.nook.alchemy.screen.Oobe;
import net.bugs.testhelper.TestHelper;
import org.openqa.selenium.WebDriver;

/**
 * Created by Alecs on 18.06.2014.
 */
public class ScreenDefinition {
    private static WebDriver driver = null;
    TestHelper testHelperh = TestManager.getInstance().getTestHelper();

    public ScreenDefinition(WebDriver driver){
        this.driver = driver;
    }

    public int detectedScreen() {
        testHelperh.updateViews();
        if(oobeDetected())
            return Constant.EnumScreen.OOBE_SCREEN;
        if(dialogDetected())
            return Constant.EnumScreen.DIALOG_SCREEN;
        if (homeDetected())
            return Constant.EnumScreen.HOME_SCREEN;
//        if(libraryDetected())
//            return Constant.EnumScreen.LIBRARY_SCREEN;
        TestManager.log("OOBE NOT DETECTED");
        return Constant.EnumScreen.UNKNOWN_SCREEN;
    }

    private boolean oobeDetected(){
//        driver.switchTo().window("NATIVE_APP");
        Oobe oobe = new Oobe(driver);
        TestManager.log("Start oobe detected\n");
        return oobe.isVisible();
    }

    private boolean dialogDetected(){
//        driver.switchTo().window("NATIVE_APP");
        Dialog dialogScreen = new Dialog(driver);
        TestManager.log("Start Dialog detected\n");
        return dialogScreen.isVisible();
    }
    public boolean homeDetected(){
        driver.switchTo().window("WEBVIEW_0");
        Home home = new Home(driver);
        TestManager.log("Start Home detected\n");
        boolean result = home.isVisible();
        driver.switchTo().window("NATIVE_APP");
        return result;
    }
}
