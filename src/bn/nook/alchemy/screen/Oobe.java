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
            public static final String SIGN_IN_BUTTN = "sign_in";
            public static final String EMAIL_FIELD = "account";
            public static final String PSSWD_FIELD = "password";
            public static final String NEXT_BTN = "next";
            public static final String SIGN_UP_BUTTN = "sign_in";
            public static final String EXPLORE_APP_BUTN = "start_reading";
            public static final String VIEW_PAGER = "pager";
            public static final String COUNTRY_SPINNER = "cor_spinner";
        }
        public  static class Account{
            public static final String EMAIL_ADDRESS = TestManager.getInstance().getLogin();
            public static final String PASSWORD = TestManager.getInstance().getPassword();
        }
        public static class ScreenId {
            public static final int UNKNOWN_SCREEN = -1;
            public static final int MAIN_SCREEN = 0;
            public static final int MAIN_WITH_COUNTRY_LIST = 1;
        }
        public static class Text{
            public static final String UNITED_STATES = "United States";
            public static final String UNITED_KINGDOM = "United Kingdom";
        }
    }

    private static int currentScreenId = Constant.ScreenId.UNKNOWN_SCREEN;

    private ActionOnMainScreen actionOnMainScreen = new ActionOnMainScreen();
    private ActionOnMainWithCountryList actionOnMainWithCountryList = new ActionOnMainWithCountryList();

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
        }
    }

    public int detectedScreen() {
        return currentScreenId;
    }

    public boolean isVisible() {
        if(isExistMainScreen() || isExistMainScreenWithCountryList())
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
        TestManager.log("Main Screen was not detected");
        return false;
    }

    private boolean isExistMainScreenWithCountryList(){
        By unitedStates = By.linkText(Constant.Text.UNITED_STATES);
        By unitedKingdom = By.linkText(Constant.Text.UNITED_KINGDOM);

        if(isElementPresent(unitedStates) && isElementPresent(unitedKingdom)){
            currentScreenId = Constant.ScreenId.MAIN_WITH_COUNTRY_LIST;
            return true;
        }
        TestManager.log("Main Screen (Country list) was not detected");
        return false;
    }

    private class ActionOnMainScreen{

        private void signIn(){
            By idSignBtn = By.id(Constant.Id.SIGN_IN_BUTTN);
            if (!waitForElement(idSignBtn, driver, 60)){
                TestManager.log("\"LOG IN\" button was not found!");
            }else {
                clickById(idSignBtn);
            }
        }
        private void swipeImages(){
            By pager = By.id(Constant.Id.VIEW_PAGER);
            if(!waitForElement(pager, driver, 60)){
                TestManager.log("\"Pager\" element was not found!");
            }else {
                WebElement pagerElement = findElement(By.id(Constant.Id.VIEW_PAGER));
                swipe(pagerElement, ((int) (Math.random() * 2) - 1));
            }
        }
        private void tapOnExplroeAppBtn(){
            By idExploreAppBtn = By.id(Constant.Id.EXPLORE_APP_BUTN);
            if(!waitForElement(idExploreAppBtn, driver , 60)){
                TestManager.log("\"Explore the app\" button was not found!");
            }else {
                clickById(idExploreAppBtn);
            }
        }
        public void clickOnCountrySpinner(){
            By idCountrySpinner = By.id(Constant.Id.COUNTRY_SPINNER);
            if(!waitForElement(idCountrySpinner, driver, 60)){
                TestManager.log("\"Country Spinner\" was not found!");
            }else {
                clickById(idCountrySpinner);
            }
        }
    }



    private class ActionOnMainWithCountryList{

        private void chooseUSA() {
            By textUSA = By.linkText(Constant.Text.UNITED_STATES);
            if (!waitForElement(textUSA, driver, 60)) {
                TestManager.log("\"United States\" was not found");
            } else {
                clickByText(textUSA);
            }
        }

        private void chooseUK(){
            By textUK = By.linkText(Constant.Text.UNITED_KINGDOM);
            if(!waitForElement(textUK, driver , 60)){
                TestManager.log("\"United Kingdom\" was not found");
            }else {
                clickByText(textUK);
            }
        }
    }


    public void randomActionMainScreen(){

        switch (setRandomNumber(4)){
            case 0: {
//                actionOnMainScreen.signIn();
                TestManager.log("SIGN IN click");
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
        switch (setRandomNumber(2)){
            case 0:
                actionOnMainWithCountryList.chooseUK();
                break;
            case 1:
                actionOnMainWithCountryList.chooseUSA();
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
