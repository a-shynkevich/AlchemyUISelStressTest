package bn.nook.alchemy.screen;

import bn.nook.alchemy.utils.TestManager;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import io.selendroid.SelendroidKeys;
import junit.framework.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static java.lang.Thread.sleep;

/**
 * Created by Alecs on 17.06.2014.
 */
public class Oobe extends ScreenHelper {


    public Oobe(WebDriver driver) {
        super(driver);
    }

    public static class Constant{

        public static class Id{

            public static final String SIGN_IN_BUTTN = "sign_in";
            public static final String EMAIL_FIELD = "account";
            public static final String PSSWD_FIELD = "password";
            public static final String NEXT_BTN = "next";
            public static final String SIGN_UP_BUTTN = "sign_in";
            public static final String EXPLORE_APP_BUTN = "start_reading";
            public static final String VIEW_PAGER = "pager";
            public static final String COUNTRY_SPINNER = "cor_spinner";
            public static final String BACK_BTN = "back";
            public static final String CLOSE_BTN = "close";
        }

        public  static class Account{
            public static final String EMAIL_ADDRESS = "avatar_unified_small2@books.com";
            public static final String PASSWORD = "access";
        }

        public static class ScreenId {
            public static final int UNKNOWN_SCREEN = -1;
            public static final int MAIN_SCREEN = 0;
            public static final int MAIN_WITH_COUNTRY_LIST = 1;
            public static final int LOGIN_SCREEN = 2;
            public static final int POPUP_VALIDATION_ERRORS =3;

        }

        public static class Text{
            public static final String UNITED_STATES = "United States";
            public static final String UNITED_KINGDOM = "United Kingdom";
            public static final String WRONG_EMAIL = "-Your Email Address field must contain a valid email address.";
            public static final String EMPTY_EMAIL_FIELD = "Please enter your email address in order to sign in to your account.";
            public static final String TRY_AGAIN_BUTTON = "Try again";
        }
    }

    private static int currentScreenId = Constant.ScreenId.UNKNOWN_SCREEN;

    private ActionOnMainScreen actionOnMainScreen = new ActionOnMainScreen();
    private ActionOnMainWithCountryList actionOnMainWithCountryList = new ActionOnMainWithCountryList();
    private ActionOnLoginScreen actionOnLoginScreen = new ActionOnLoginScreen();
    private ActionOnLoginPopupErrorScreen actionPopupErrorScreen = new ActionOnLoginPopupErrorScreen();

    public void start(){
        switch (detectedScreen()){
            case Constant.ScreenId.MAIN_SCREEN:
                TestManager.log("Oobe screen is main screen.");
                randomActionMainScreen();
                break;
            case Constant.ScreenId.MAIN_WITH_COUNTRY_LIST:
                TestManager.log("Oobe screen is main screen with country list.");
                randomActionMainCountryList();
                break;
            case Constant.ScreenId.LOGIN_SCREEN:
                TestManager.log("Oobe screen is Log in screen");
                randomActionLoginScreen();
                break;
            case Constant.ScreenId.POPUP_VALIDATION_ERRORS:
                TestManager.log("Oobe screen is Login screen : \"Validation errors\" popup.)");
                actionPopupErrorScreen.closePopup();
                break;
        }
    }

    public int detectedScreen() {
        return currentScreenId;
    }

    public boolean isVisible() {
        if(isExistMainScreen() || isExistLoginScreen() || isExistMainScreenWithCountryList() ||
                isExistPopupValidationErrors())
            return true;
        return false;
    }

    private boolean isExistMainScreen(){
        By logInBtn = By.id(Constant.Id.SIGN_IN_BUTTN);
        By exploreAppBtn = By.id(Constant.Id.EXPLORE_APP_BUTN);
        By mainImage = By.id(Constant.Id.VIEW_PAGER);

        if(isElementPresent(logInBtn) && isElementPresent(exploreAppBtn) && isElementPresent(mainImage)){
            currentScreenId = Constant.ScreenId.MAIN_SCREEN;
            return true;
        }
        return false;
    }

    private boolean isExistMainScreenWithCountryList(){
        By unitedStates = By.linkText(Constant.Text.UNITED_STATES);
        By unitedKingdom = By.linkText(Constant.Text.UNITED_KINGDOM);

        if(isElementPresent(unitedStates) && isElementPresent(unitedKingdom)){
            currentScreenId = Constant.ScreenId.MAIN_WITH_COUNTRY_LIST;
            return true;
        }
        return false;
    }

    private boolean isExistLoginScreen(){
        By idEmailField = By.id(Constant.Id.EMAIL_FIELD);

        if(isElementPresent(idEmailField)){
            currentScreenId = Constant.ScreenId.LOGIN_SCREEN;
            return true;
        }
        return false;
    }

    private boolean isExistPopupValidationErrors(){
        By textAlertMessage = By.linkText(Constant.Text.WRONG_EMAIL);
        By textEmptyEmailField = By.linkText(Constant.Text.EMPTY_EMAIL_FIELD);


        if (isElementPresent(textAlertMessage) || isElementPresent(textEmptyEmailField)){
            currentScreenId = Constant.ScreenId.POPUP_VALIDATION_ERRORS;
            return true;
        }
        return false;
    }

    private class ActionOnMainScreen{

        private void signIn(){
            By idSignBtn = By.id(Constant.Id.SIGN_IN_BUTTN);
            WebElement signBtn =waitForElement(idSignBtn, driver, 60);
            if (signBtn == null){
                TestManager.log("\"LOG IN\" button was not found!");
            }else {
                signBtn.click();
            }
        }
        private void swipeImages(){
            By idPager = By.id(Constant.Id.VIEW_PAGER);
            WebElement pager = waitForElement(idPager, driver, 60);
            if(pager == null){
                TestManager.log("\"Pager\" element was not found!");
            }else {
                swipe(pager, ((int) (Math.random() * 2) - 1));
            }
        }
        private void tapOnExplroeAppBtn(){
            By idExploreAppBtn = By.id(Constant.Id.EXPLORE_APP_BUTN);
            WebElement exploreAppBtn = waitForElement(idExploreAppBtn, driver , 60);
            if(exploreAppBtn == null){
                TestManager.log("\"Explore the app\" button was not found!");
            }else {
                exploreAppBtn.click();
            }
        }
        public void clickOnCountrySpinner(){
            By idCountrySpinner = By.id(Constant.Id.COUNTRY_SPINNER);
            WebElement countrySpinner = waitForElement(idCountrySpinner, driver, 60);
            if(countrySpinner == null){
                TestManager.log("\"Country Spinner\" was not found!");
            }else {
                countrySpinner.click();
            }
        }
    }



    private class ActionOnMainWithCountryList{

        private void chooseUSA() {
            By textUSA = By.linkText(Constant.Text.UNITED_STATES);
            WebElement USA = waitForElement(textUSA, driver, 60);
            if (USA == null) {
                TestManager.log("\"United States\" was not found");
            } else {
                USA.click();
            }
        }

        private void chooseUK(){
            By textUK = By.linkText(Constant.Text.UNITED_KINGDOM);
            WebElement UK = waitForElement(textUK, driver , 60);
            if(UK == null){
                TestManager.log("\"United Kingdom\" was not found");
            }else {
                UK.click();
            }
        }
    }

    private class ActionOnLoginScreen{

        private void enterEmail(){
            By idEmail = By.id(Constant.Id.EMAIL_FIELD);
            WebElement email = waitForElement(idEmail, driver, 60);
            if (email == null){
                TestManager.log("Email field was not found");
            }else {
                email.clear();
                email.sendKeys(Constant.Account.EMAIL_ADDRESS);
            }
        }

        private void clickBackButton(){
            By idBackBtn = By.id(Constant.Id.BACK_BTN);
            WebElement back = waitForElement(idBackBtn, driver, 60);
            if (back == null){
                TestManager.log("Back button was not found");
            }else back.click();
        }

        private void cliclCloseButton(){
            By idCloseBtn = By.id(Constant.Id.CLOSE_BTN);
            WebElement close = waitForElement(idCloseBtn, driver, 60);
            if (close == null){
                TestManager.log("Close button was not found");
            }else close.click();
        }

        private void clickNextButton(){
            By idNextBtn = By.id(Constant.Id.NEXT_BTN);
            WebElement nextBtn = waitForElement(idNextBtn, driver, 60);
            if (nextBtn == null){
                TestManager.log("Next button was not found");
            }else nextBtn.click();
        }
    }

    private class ActionOnLoginPopupErrorScreen{

        private void closePopup(){
            By textTryAgainBtn = By.linkText(Constant.Text.TRY_AGAIN_BUTTON);
            WebElement tryAgainBtn = waitForElement(textTryAgainBtn, driver, 60);
            if(tryAgainBtn == null){
                TestManager.log("\"Try again\" button was not found");
            }else tryAgainBtn.click();
        }
    }


    public void randomActionMainScreen(){

        switch (setRandomNumber(4)){
            case 0: {
                actionOnMainScreen.signIn();
                TestManager.log("SIGN IN click");
                break;
            }
            case 1:
                actionOnMainScreen.swipeImages();
                TestManager.log("SWIPE");
                break;
            case 2:
//                actionOnMainScreen.tapOnExplroeAppBtn();
                TestManager.log("Click on EXPLORE BUTTON");
                break;
            case 3:
                actionOnMainScreen.clickOnCountrySpinner();
                TestManager.log("CLICK ON COUNTRY SPINNER");
                break;
        }
    }

    public void randomActionMainCountryList(){
        switch (setRandomNumber(2)){
            case 0:
                actionOnMainWithCountryList.chooseUK();
                break;
            case 1:
                actionOnMainWithCountryList.chooseUSA();
                break;
        }
    }

    public void randomActionLoginScreen(){
        switch (setRandomNumber(4)){
            case 0:
                actionOnLoginScreen.clickBackButton();
                TestManager.log("CLICK BACK BUTTON");
                break;
            case 1:
                actionOnLoginScreen.cliclCloseButton();
                TestManager.log("CLICK CLOSE BUTTON");
                break;
            case 2:
                actionOnLoginScreen.enterEmail();
                TestManager.log("ENTER EMAIL");
                break;
            case 3:
                actionOnLoginScreen.clickNextButton();
                TestManager.log("CLICK NEXT BUTTON");
                break;
        }
    }

    public void signIn(){
        try {
            sleep(1000);

            WebElement signInBtn = driver.findElement(By.id(Constant.Id.SIGN_IN_BUTTN));
            if (signInBtn.isDisplayed()) {
                signInBtn.click();
            } else {
                TestManager.log("LOG IN button was not found!");
            }

            waitForElement(By.id(Constant.Id.EMAIL_FIELD), driver, 30000);
            WebElement emailField = driver.findElement(By.id(Constant.Id.EMAIL_FIELD));
            emailField.clear();
            emailField.sendKeys(Constant.Account.EMAIL_ADDRESS);
            sleep(500);
            new Actions(driver).sendKeys(SelendroidKeys.ENTER).perform();
            sleep(500);

            WebElement nextBtn = driver.findElement(By.id(Constant.Id.NEXT_BTN));
            if (nextBtn.isDisplayed()){
                nextBtn.click();
            }else{
                TestManager.log("Next button was not found");
            }
            sleep(1000);

            WebElement psswrdField = driver.findElement(By.id(Constant.Id.PSSWD_FIELD));
            psswrdField.clear();
            psswrdField.sendKeys(Constant.Account.PASSWORD);
            sleep(500);
            new Actions(driver).sendKeys(SelendroidKeys.ENTER).perform();

            WebElement signUpBtn = driver.findElement(By.id(Constant.Id.SIGN_UP_BUTTN));
            if (signUpBtn.isDisplayed()){
                signUpBtn.click();
            }else {
                TestManager.log("\"Sing up\' button was not found!");
            }
            sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
