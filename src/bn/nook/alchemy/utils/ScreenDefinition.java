package bn.nook.alchemy.utils;

import bn.nook.alchemy.screen.*;
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
        if (sideBarDetected())
            return Constant.EnumScreen.SIDE_BAR_SCREEN;
        if (settingsDetected())
            return Constant.EnumScreen.SETTINGS_SCREEN;
        return Constant.EnumScreen.UNKNOWN_SCREEN;
    }

    private boolean oobeDetected(){
        Oobe oobe = new Oobe(driver);
        TestManager.log("Start oobe detected\n");
        return oobe.isVisible();
    }

    private boolean dialogDetected(){
        Dialog dialogScreen = new Dialog(driver);
        TestManager.log("Start Dialog detected\n");
        return dialogScreen.isVisible();
    }

    public boolean homeDetected(){
        Home home = new Home(driver);
        TestManager.log("Start Home detected\n");
        return home.isVisible();
    }

    private boolean sideBarDetected(){
        SideBar sideBar = new SideBar(driver);
        TestManager.log("Start SideBar detected");
        return sideBar.isVisible();
    }

    private boolean settingsDetected(){
        Settings settings = new Settings(driver);
        TestManager.log("Start Settings detected");
        return settings.isVisible();
    }
}
