package bn.nook.alchemy.utils;

import bn.nook.alchemy.screen.Oobe;
import org.openqa.selenium.WebDriver;

/**
 * Created by Alecs on 18.06.2014.
 */
public class ScreenDefinition {
    private static WebDriver driver = null;

    public ScreenDefinition(WebDriver driver){
        this.driver = driver;
    }
    public int detectedScreen() {
        if(oobeDetected())
            return Constant.EnumScreen.OOBE_SCREEN;
//        if(libraryDetected())
//            return Constant.EnumScreen.LIBRARY_SCREEN;
        TestManager.log("OOBE NOT DETECTED");
        return Constant.EnumScreen.UNKNOWN_SCREEN;
    }
    private boolean oobeDetected() {
        Oobe oobe = new Oobe(driver);
        TestManager.log("Start oobe detected\n");
        return oobe.isVisible();
    }
}
