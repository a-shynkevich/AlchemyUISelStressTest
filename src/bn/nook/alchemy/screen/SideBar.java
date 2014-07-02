package bn.nook.alchemy.screen;

import bn.nook.alchemy.utils.TestManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Alecs on 02.07.2014.
 */
public class SideBar extends ScreenHelper {
    private static int currentScreenId = Constant.ScreenId.UNKNOWN_SCREEN;
    ActionSideBar actionSideBar = null;

    public SideBar(WebDriver driver) {
        super(driver);
        actionSideBar = new ActionSideBar();
    }

    public static class Constant{
        public static class ScreenId{
            public static final int UNKNOWN_SCREEN = -1;
            public static final int SIDE_BAR = 0;
        }
        public static class Id{
            public static final String PACKAGE_PATH_FOR_ID = "bn.ereader:id/";
            public static final String NAVIGATION_DRAWER = "navigation_drawer";

        }
        public static class Text{
            public static final String MY_HOME = "My Home";
            public static final String DISCOVERY = "Discovery";
            public static final String QUICK_READS = "Quick Reads";
            public static final String LIBRARY = "Library";
            public static final String SETTINGS = "Settings";
        }
    }

    public void start(){
        switch(getRandomNumber(2)){
            case Constant.ScreenId.SIDE_BAR:
                rundomActionSideBar();
                break;
        }
    }

    @Override
    public boolean isVisible() {
        if (isExistSideBar())
            return true;
        return false;
    }

    private boolean isExistSideBar() {
        if (testHelper.getViewById(Constant.Id.PACKAGE_PATH_FOR_ID + Constant.Id.NAVIGATION_DRAWER, true, false).exists()) {
            currentScreenId = Constant.ScreenId.SIDE_BAR;
            return true;
        }
        return false;
    }

    private class ActionSideBar{

        private void openSettingsScreen(){
            By textSettings = By.linkText(Constant.Text.SETTINGS);
            WebElement settingsTab = waitForElement(textSettings, driver, 60);
            if(settingsTab == null){
                TestManager.log("\"Settings tab\" was not found.");
            }else {
                settingsTab.click();
                TestManager.log("clisk on the \"Settings tab\".");
            }
        }

        private void openMyHomeScreen(){
            By textMyHome = By.linkText(Constant.Text.MY_HOME);
            WebElement myHomeTab = waitForElement(textMyHome, driver, 60);
            if(myHomeTab == null){
                TestManager.log("\"My Home\" tab was not found.");
            }else {
                myHomeTab.click();
                TestManager.log("Click on the \"My home\" tab.");
            }
        }

        private void openDiscoveryScreen(){
            By textDiscovery = By.linkText(Constant.Text.DISCOVERY);
            WebElement discoveryTab = waitForElement(textDiscovery, driver, 60);
            if(discoveryTab == null){
                TestManager.log("\"Discovery\" tab was not found.");
            }else {
                discoveryTab.click();
                TestManager.log("Click on the \"Discovery\" tab.");
            }
        }

        private void openQuickReadsScreen(){
            By textQuickReads = By.linkText(Constant.Text.QUICK_READS);
            WebElement quickReadsTab = waitForElement(textQuickReads, driver, 60);
            if(quickReadsTab == null){
                TestManager.log("\"Quick Reads\" tab was not found.");
            }else {
                quickReadsTab.click();
                TestManager.log("Click on the \"Quick Reads\" tab.");
            }
        }

        private void openLibraryScreen(){
            By textlibrary = By.linkText(Constant.Text.LIBRARY);
            WebElement libraryTab = waitForElement(textlibrary, driver, 60);
            if(libraryTab == null){
                TestManager.log("\"Library\" tab was not found.");
            }else {
                libraryTab.click();
                TestManager.log("Click on the \"Library\" tab.");
            }
        }
    }

    public void rundomActionSideBar(){
        switch (getRandomNumber(5)){
            case 0:
                TestManager.log("Open settings.");
//                actionSideBar.openSettingsScreen();
                break;
            case 1:
                actionSideBar.openMyHomeScreen();
                break;
            case 2:
                actionSideBar.openDiscoveryScreen();
                break;
            case 3:
                actionSideBar.openQuickReadsScreen();
                break;
            case 4:
                    TestManager.log("Library open.");
//                Screen not ready
//                actionSideBar.openLibraryScreen();
                break;
        }
    }

}
