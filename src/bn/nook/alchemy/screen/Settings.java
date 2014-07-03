package bn.nook.alchemy.screen;

import bn.nook.alchemy.utils.TestManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Settings extends ScreenHelper{

    private static int currentScreenId = Constant.ScreenId.UNKNOWN_SCREEN;
    private ActionSettingsScreen actionSettingsScreen = null;
    private ActionDialogSignOutScreen actionDialogSignOutScreen = null;

    public Settings(WebDriver driver) {
        super(driver);
        actionSettingsScreen = new ActionSettingsScreen();
        actionDialogSignOutScreen = new ActionDialogSignOutScreen();
    }

    public static class Constant{
        public static class ScreenId{
            public static final int UNKNOWN_SCREEN = -1;
            public static final int SETTINGS = 0;
            public static final int SIGN_OUT_DIALOG = 1;
        }
        public static class Id{
            public static final String PACKAGE_PATH_FOR_ID = "bn.ereader:id/";
            public static final String SIGN_OUT = "sign_in_out_btn";
        }
        public static class Text{
            public static final String PAGE_SETTINGS = "Page Settings";
            public static final String SIGN_OUT_DIALOG = "Sign Out of Your Account";
            public static final String SIGN_OUT = "Sign Out";
            public static final String CANCEL = "Cancel";

        }
    }

    @Override
    public boolean isVisible() {
        if (isExistSettingsScreen()||
                isExistDialogSignOutScreen())
            return true;
        return false;
    }

    @Override
    public void start() {
        switch (detectedScreen()) {
            case Constant.ScreenId.SETTINGS:
                randomActionSettingsScreen();
                break;
            case Constant.ScreenId.SIGN_OUT_DIALOG:
                randomActionDialogSignOutScreen();
                break;
        }
    }

    @Override
    public int detectedScreen() {
        return currentScreenId;
    }

    private class ActionSettingsScreen{
        private void clickSignOutButton(){
            By idSignOut = By.id(Constant.Id.SIGN_OUT);
            WebElement signOutBtn = waitForElement(idSignOut, driver, 60);
            if (signOutBtn == null)
                TestManager.log("\"Sign out\" button was not found!");
            else {
                signOutBtn.click();
                TestManager.log("Click on the \"Sign out\" button.");
            }
        }
    }
    private class ActionDialogSignOutScreen{
        private void clickCancel(){
            By textCancelBtn = By.linkText(Constant.Text.CANCEL);
            WebElement cancelBtn = waitForElement(textCancelBtn, driver, 60);
            if(cancelBtn == null){
                TestManager.log("\"Cancel button\" was not found!");
            }else {
                cancelBtn.click();
                TestManager.log("Click on the \"Cancel button\".");
            }
        }
        private void clickSignOut(){
            By textSignOutBtn = By.linkText(Constant.Text.SIGN_OUT);
            WebElement signOutBtn = waitForElement(textSignOutBtn, driver, 60);
            if(signOutBtn == null){
                TestManager.log("\"Sign Out button\" was not found!");
            }else {
                signOutBtn.click();
                TestManager.log("Click on the \"Sig out button\".");
            }
        }

    }

    private boolean isExistSettingsScreen(){
        if(testHelper.getViewByText(Constant.Text.PAGE_SETTINGS, true, false).exists()){
            currentScreenId = Constant.ScreenId.SETTINGS;
            return true;
        }
        return false;
    }
    private boolean isExistDialogSignOutScreen(){
        if (testHelper.getViewByText(Constant.Text.SIGN_OUT_DIALOG, true ,false).exists()){
            currentScreenId = Constant.ScreenId.SIGN_OUT_DIALOG;
            return true;
        }
        return false;
    }

    public void randomActionSettingsScreen(){
        switch (getRandomNumber(1)){
            case 0:
                actionSettingsScreen.clickSignOutButton();
                break;
        }
    }
    public void randomActionDialogSignOutScreen(){
        switch (getRandomNumber(2)){
            case 0:
                actionDialogSignOutScreen.clickCancel();
                break;
            case 1:
                actionDialogSignOutScreen.clickSignOut();
                break;
        }
    }
}
