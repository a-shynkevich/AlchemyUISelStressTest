package bn.nook.alchemy.screen;

import bn.nook.alchemy.utils.TestManager;
import io.selendroid.SelendroidKeys;
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

            public static final String PACKAGE_PATH_FOR_TH_ID = "bn.ereader:id/";
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
            public static final String SHOW_PSSW_CHECK_BOX = "show_password";
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
            public static final int PASSWORD_SCREEN = 3;
        }

        public static class Text{
            public static final String UNITED_STATES = "United States";
            public static final String UNITED_KINGDOM = "United Kingdom";
            public static final String USE_SOCIAL_ACC = "Use a social account";
        }
    }

    private static int currentScreenId = Constant.ScreenId.UNKNOWN_SCREEN;

    private ActionOnMainScreen actionOnMainScreen = new ActionOnMainScreen();
    private ActionOnMainWithCountryList actionOnMainWithCountryList = new ActionOnMainWithCountryList();
    private ActionOnLoginScreen actionOnLoginScreen = new ActionOnLoginScreen();

    private ActionOnPasswordScreen actionPasswScreen = new ActionOnPasswordScreen();

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

            case Constant.ScreenId.PASSWORD_SCREEN:
                TestManager.log("Oobe screen is Password screen.");
                randomActionPasswordField();
                break;
        }
    }


    public int detectedScreen() {
        return currentScreenId;
    }

    public boolean isVisible() {
        if(isExistMainScreen() || isExistLoginScreen() || isExistMainScreenWithCountryList() ||
                isExistPasswordScreen())
            return true;
        return false;
    }

    private boolean isExistMainScreen(){
//        By exploreAppBtn = By.id(Constant.Id.EXPLORE_APP_BUTN);
//
//        if(isElementPresent(exploreAppBtn)){
//            currentScreenId = Constant.ScreenId.MAIN_SCREEN;
//            return true;
//        }
//        return false;
        if (testHelper.getViewById(Constant.Id.PACKAGE_PATH_FOR_TH_ID + Constant.Id.EXPLORE_APP_BUTN, true, false).exists()){
            currentScreenId = Constant.ScreenId.MAIN_SCREEN;
            return true;
        }else return false;
    }

    private boolean isExistMainScreenWithCountryList(){
        if (testHelper.getViewByText(Constant.Text.UNITED_STATES, true, false).exists()){
            currentScreenId = Constant.ScreenId.MAIN_WITH_COUNTRY_LIST;
            return true;
        }else return false;

    }

    private boolean isExistLoginScreen(){

        if ((testHelper.getViewById( Constant.Id.PACKAGE_PATH_FOR_TH_ID +Constant.Id.EMAIL_FIELD, true, false).exists()
                && !testHelper.getViewById(Constant.Id.PACKAGE_PATH_FOR_TH_ID +Constant.Id.PSSWD_FIELD, true, false).exists())
                ||testHelper.getViewByText(Constant.Text.USE_SOCIAL_ACC, true, false).exists()){
            currentScreenId = Constant.ScreenId.LOGIN_SCREEN;
            return true;
        }else return false;
    }



    private boolean isExistPasswordScreen(){

        if (testHelper.getViewById(Constant.Id.PACKAGE_PATH_FOR_TH_ID +Constant.Id.PSSWD_FIELD, true, false).exists()){
            currentScreenId = Constant.ScreenId.PASSWORD_SCREEN;
            return true;
        }else return false;

    }



    private class ActionOnMainScreen{

        private void signIn(){
            By idSignBtn = By.id(Constant.Id.SIGN_IN_BUTTN);
            WebElement signBtn =waitForElement(idSignBtn, driver, 60);
            if (signBtn == null){
                TestManager.log("\"LOG IN\" button was not found!");
            }else {
                signBtn.click();
                TestManager.log("SIGN IN click");
            }
        }
        private void swipeImages(){
            By idPager = By.id(Constant.Id.VIEW_PAGER);
            WebElement pager = waitForElement(idPager, driver, 60);
            if(pager == null){
                TestManager.log("\"Pager\" element was not found!");
            }else {
                swipe(pager, ((int) (Math.random() * 2) - 1));
                TestManager.log("SWIPE");
            }
        }

        private void tapOnExplroeAppBtn(){
            By idExploreAppBtn = By.id(Constant.Id.EXPLORE_APP_BUTN);
            WebElement exploreAppBtn = waitForElement(idExploreAppBtn, driver , 60);
            if(exploreAppBtn == null){
                TestManager.log("\"Explore the app\" button was not found!");
            }else {
                exploreAppBtn.click();
                TestManager.log("Click on EXPLORE BUTTON");
            }
        }

        public void clickOnCountrySpinner(){
            By idCountrySpinner = By.id(Constant.Id.COUNTRY_SPINNER);
            WebElement countrySpinner = waitForElement(idCountrySpinner, driver, 60);
            if(countrySpinner == null){
                TestManager.log("\"Country Spinner\" was not found!");
            }else {
                countrySpinner.click();
                TestManager.log("CLICK ON COUNTRY SPINNER");
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
                TestManager.log("CLICK UNITED STATES");
            }
        }

        private void chooseUK(){
            By textUK = By.linkText(Constant.Text.UNITED_KINGDOM);
            WebElement UK = waitForElement(textUK, driver , 60);
            if(UK == null){
                TestManager.log("\"United Kingdom\" was not found");
            }else {
                UK.click();
                TestManager.log("CLICK UNITED KINGDOM");
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
                TestManager.log("ENTER EMAIL");
            }
        }

        private void clickBackButton(){
            By idBackBtn = By.id(Constant.Id.BACK_BTN);
            WebElement back = waitForElement(idBackBtn, driver, 60);
            if (back == null){
                TestManager.log("Back button was not found");
            }else {
                back.click();
                TestManager.log("CLICK BACK BUTTON");
            }
        }

        private void cliclCloseButton(){
            By idCloseBtn = By.id(Constant.Id.CLOSE_BTN);
            WebElement close = waitForElement(idCloseBtn, driver, 60);
            if (close == null){
                TestManager.log("Close button was not found");
            }else{
                close.click();
                TestManager.log("CLICK CLOSE BUTTON");
            }
        }

        private void clickNextButton(){
            By idNextBtn = By.id(Constant.Id.NEXT_BTN);
            WebElement nextBtn = waitForElement(idNextBtn, driver, 60);
            if (nextBtn == null){
                TestManager.log("Next button was not found");
            }else{
                nextBtn.click();
                TestManager.log("CLICK NEXT BUTTON");
            }
        }
    }



    private class ActionOnPasswordScreen{

        private void enterPassword(){
            By idPassword = By.id(Constant.Id.PSSWD_FIELD);
            WebElement passwordField = waitForElement(idPassword, driver, 60);
            if(passwordField == null){
                TestManager.log("\"Password\" field was not found.");
            }else {
                passwordField.clear();
                passwordField.sendKeys(Constant.Account.PASSWORD);
                TestManager.log("ENTER PASSWORD");
            }
        }

        private void showPassword(){
            By idCheckBox = By.id(Constant.Id.SHOW_PSSW_CHECK_BOX);
            WebElement checkBox = waitForElement(idCheckBox, driver, 60);
            if (checkBox == null){
                TestManager.log("\"Show password\" checkbox was not found");
            }else{
                checkBox.click();
                TestManager.log("CLICK ON \"SHOW PASSWORD\" CHECK BOX.");
            }
        }

        private void clickSignUpBtn(){
            By idSignUpBtn = By.id(Constant.Id.SIGN_UP_BUTTN);
            WebElement signUpBtn = waitForElement(idSignUpBtn, driver, 60);
            if (signUpBtn == null){
                TestManager.log("\"Sign up\" button was not found");
            }else{
                signUpBtn.click();
                TestManager.log("CLICK ON \"SIGN UP\" BUTTON.");
            }
        }
    }

    public void randomActionMainScreen(){

        switch (getRandomNumber(4)){
            case 0: {
                actionOnMainScreen.signIn();
                break;
            }
            case 1:
                actionOnMainScreen.swipeImages();
                break;
            case 2:
//                actionOnMainScreen.tapOnExplroeAppBtn();
                TestManager.log("Click on EXPLORE BUTTON");
                break;
            case 3:
                actionOnMainScreen.clickOnCountrySpinner();
                break;
        }
    }

    public void randomActionMainCountryList(){
        switch (getRandomNumber(2)){
            case 0:
                actionOnMainWithCountryList.chooseUK();
                break;
            case 1:
                actionOnMainWithCountryList.chooseUSA();
                break;
        }
    }

    public void randomActionLoginScreen(){
        switch (getRandomNumber(5)){
            case 0:
                actionOnLoginScreen.clickBackButton();

                break;
            case 1:
                actionOnLoginScreen.cliclCloseButton();
                break;
            case 2:
                actionOnLoginScreen.enterEmail();
                break;
            case 3:
                actionOnLoginScreen.clickNextButton();
                break;
            case 4:
                actionOnLoginScreen.enterEmail();
                actionOnLoginScreen.clickNextButton();
                break;
        }
    }

    public void randomActionPasswordField(){
        switch (getRandomNumber(6)){
            case 0:
                actionPasswScreen.enterPassword();
                break;
            case 1:
                actionOnLoginScreen.clickBackButton(); //the same element as on the Login screen
                break;
            case 2:
                actionOnLoginScreen.cliclCloseButton(); //the same element as on the Login screen
                break;
            case 3:
                actionPasswScreen.clickSignUpBtn(); //the same element as on the Login screen
                break;
            case 4:
                actionPasswScreen.showPassword();
                break;
            case 5:
                actionPasswScreen.enterPassword();
                actionPasswScreen.clickSignUpBtn();
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
