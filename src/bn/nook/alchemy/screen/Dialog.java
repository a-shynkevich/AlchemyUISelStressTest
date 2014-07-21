package bn.nook.alchemy.screen;

import bn.nook.alchemy.utils.TestManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Alecs on 25.06.2014.
 */
public class Dialog extends ScreenHelper {
    ActionOnPushNotificationDialog actionOnPushNotificationDialog;
    private static int currentScreenId = Constant.ScreenId.UNKNOWN_SCREEN;
    private DialogScreen dialog = null;

    public Dialog(WebDriver driver) {
        super(driver);
        dialog = new DialogScreen();
        actionOnPushNotificationDialog = new ActionOnPushNotificationDialog();
    }

    public static class Constant{
        public static class ScreenId{
            public static final int UNKNOWN_SCREEN = -1;
            public static final int POPUP_VALIDATION_ERRORS = 0;
            public static final int DIALOG_NO_INTERNET = 1;
            public static final int SELECT_COUNTRY_ERROR_DIALOG = 2;
            public static final int PUSH_NOTIFICATIONS_DIALOG = 3;
        }

        public static class Text{
            public static final String TRY_AGAIN_BUTTON = "Try again";
            public static final String NO_INTERNET_TITLE = "Let's get connected";
            public static final String OK_BUTTON = "OK";
            public static final String SELECT_COUNTRY_TEXT = "Please select your country in order to proceed.";
            public static final String PUSH_NOTIFICANIONS = "Nook would like to send you push Notifications";
            public static final String ALLOW_BTN = "Allow";
            public static final String DO_NOT_ALLOW_BTN = "Don't Allow";
        }
    }

    public void start() {
        switch (detectedScreen()) {

            case Constant.ScreenId.POPUP_VALIDATION_ERRORS:
                TestManager.log("Oobe screen is Login screen : \"Validation errors\" popup.)");
                dialog.closeTryAgainDialog();
                break;

            case Constant.ScreenId.DIALOG_NO_INTERNET:
                TestManager.log("Oobe screen is Dialog no Internet.");
                dialog.clickOkBtn();
                break;

            case Constant.ScreenId.SELECT_COUNTRY_ERROR_DIALOG:
                TestManager.log("Oobe screen is Select Country Error Dialog.");
                dialog.clickOkBtn();
                break;
            case Constant.ScreenId.PUSH_NOTIFICATIONS_DIALOG:
                TestManager.log("Push notifications dialog is displayed.");
                randomActionPushNotificationsDialog();
                break;
        }
    }

    @Override
    public int detectedScreen() {
        return currentScreenId;
    }

    public boolean isVisible() {
        if(isExistPopupValidationErrors() ||
                isExistDialogNoInternet() ||
                isExistSelectCountryDialog() ||
                isExistPushNotificationsDialog())
            return true;
        return false;
    }

    private boolean isExistDialogNoInternet(){

        if (testHelper.getViewByText(Constant.Text.NO_INTERNET_TITLE, true, false).exists()){
            currentScreenId = Constant.ScreenId.DIALOG_NO_INTERNET;
            return true;
        }else return false;
    }

    private boolean isExistSelectCountryDialog(){

        if (testHelper.getViewById(Constant.Text.SELECT_COUNTRY_TEXT, true, false).exists()){
            currentScreenId = Constant.ScreenId.SELECT_COUNTRY_ERROR_DIALOG;
            return true;
        }else return false;
    }

    private boolean isExistPopupValidationErrors(){

        if (testHelper.getViewByText(Constant.Text.TRY_AGAIN_BUTTON, true, false).exists()){
            currentScreenId = Constant.ScreenId.POPUP_VALIDATION_ERRORS;
            return true;
        }else return false;
    }

    private boolean isExistPushNotificationsDialog(){

        if(testHelper.getViewByText(Constant.Text.PUSH_NOTIFICANIONS, true, false).exists()){
            currentScreenId = Constant.ScreenId.PUSH_NOTIFICATIONS_DIALOG;
            return true;
        }else  return false;
    }

    private class ActionOnPushNotificationDialog{

        private void clickAllowButton(){
            By textAllow = By.linkText(Constant.Text.ALLOW_BTN);
            WebElement allowBtn = waitForElement(textAllow, driver, 60);
            if (allowBtn == null){
                TestManager.log("\"Allow\" button was not found!");
            }else {
                allowBtn.click();
                TestManager.log("Click \"Allow\" button.");
            }
        }

        private void clickDontAllowButton(){
            By textDontAllow = By.linkText(Constant.Text.DO_NOT_ALLOW_BTN);
            WebElement dontAllowBtn = waitForElement(textDontAllow, driver, 60);
            if (dontAllowBtn == null){
                TestManager.log("\"Don't Allow\" button was not found!");
            }else {
                dontAllowBtn.click();
                TestManager.log("Click \"Don't Allow\" button.");
            }
        }
    }

    private class DialogScreen {

        private void closeTryAgainDialog(){
            By textTryAgainBtn = By.linkText(Constant.Text.TRY_AGAIN_BUTTON);
            WebElement tryAgainBtn = waitForElement(textTryAgainBtn, driver, 60);
            if(tryAgainBtn == null){
                TestManager.log("\"Try again\" button was not found");
            }else {
                tryAgainBtn.click();
                TestManager.log("Click TRY AGAIN button");
            }
        }

        private void clickOkBtn(){
            By textOkBtn = By.linkText(Constant.Text.OK_BUTTON);
            WebElement okBtn = waitForElement(textOkBtn, driver, 60);
            if (okBtn == null){
                TestManager.log("\"OK\" button was not found");
            }else okBtn.click();
        }
    }

    public void randomActionPushNotificationsDialog(){
        switch (getRandomNumber(2)){
            case 0: actionOnPushNotificationDialog.clickAllowButton();
                break;
            case 1: actionOnPushNotificationDialog.clickDontAllowButton();
                break;
        }
    }
}




