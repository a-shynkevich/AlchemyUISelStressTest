package bn.nook.alchemy.screen;

import bn.nook.alchemy.utils.TestManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Alecs on 02.07.2014.
 */
public class SideBar extends ScreenHelper implements IScreenHelper {
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
            public static final String TEXTVEW_OF_TABS = "textview";

        }
        public static class Text{
            public static final String MY_HOME = "My Home";
            public static final String SHOP = "Shop";
            public static final String QUICK_READS = "Quick Reads";
            public static final String LIBRARY = "Library";
            public static final String SETTINGS = "Settings";
        }
    }

    public void start(){
        switch(detectedScreen()){
            case Constant.ScreenId.SIDE_BAR:
                rundomActionSideBar();
                break;
        }
    }

    public int detectedScreen() {
        return currentScreenId;
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
        By textview = By.id(Constant.Id.TEXTVEW_OF_TABS);

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
            WebElement myHomeTab = waitForElement(textview, driver, 60, Constant.Text.MY_HOME);
            if(myHomeTab == null){
                TestManager.log("\"My Home\" tab was not found.");
            }else {
                myHomeTab.click();
                TestManager.log("Click on the \"My home\" tab.");
            }
        }

        private void openDiscoveryScreen(){

            WebElement discoveryTab = waitForElement(textview, driver, 60,Constant.Text.SHOP);
            if(discoveryTab == null){
                TestManager.log("\"Discovery\" tab was not found.");
            }else {
                discoveryTab.click();
                TestManager.log("Click on the \"Discovery\" tab.");
            }
        }

        private void openQuickReadsScreen(){
            WebElement quickReadsTab = waitForElement(textview, driver, 60,Constant.Text.QUICK_READS );
            if(quickReadsTab == null){
                TestManager.log("\"Quick Reads\" tab was not found.");
            }else {
                quickReadsTab.click();
                TestManager.log("Click on the \"Quick Reads\" tab.");
            }
        }

        private void openLibraryScreen(){
            WebElement libraryTab = waitForElement(textview, driver, 60, Constant.Text.LIBRARY);
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
                actionSideBar.openSettingsScreen();
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
