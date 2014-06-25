package bn.nook.alchemy.screen;

import bn.nook.alchemy.utils.TestManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Alecs on 25.06.2014.
 */
public class Dialog extends ScreenHelper {

    public static class Constant{
        public static class ScreenId{
            public static final int UNKNOWN_SCREEN = -1;
            public static final int POPUP_VALIDATION_ERRORS = 0;
            public static final int DIALOG_NO_INTERNET = 1;
            public static final int SELECT_COUNTRY_ERROR_DIALOG = 2;
        }


        public static class Text{
            public static final String TRY_AGAIN_BUTTON = "Try again";
            public static final String NO_INTERNET_TITLE = "Let's get connected";
            public static final String OK_BUTTON = "OK";
            public static final String SELECT_COUNTRY_TEXT = "Please select your country in order to proceed.";
        }
    }


    public Dialog(WebDriver driver) {
        super(driver);
    }

    private DialogScreen dialog = new DialogScreen();
    private static int currentScreenId = Constant.ScreenId.UNKNOWN_SCREEN;

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
        }
    }

    @Override
    public int detectedScreen() {
        return currentScreenId;
    }

    public boolean isVisible() {
        if(isExistPopupValidationErrors() || isExistDialogNoInternet() ||
                isExistSelectCountryDialog())
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
}




