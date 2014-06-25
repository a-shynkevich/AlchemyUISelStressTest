package bn.nook.alchemy.screen;

import bn.nook.alchemy.utils.TestManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Alecs on 25.06.2014.
 */
public class Home extends ScreenHelper {

    public static class Constant{

        public static class ScreenId{
            public static final int UNKNOWN_SCREEN = -1;
            public static final int HOME_SCREEN = 0;
        }

        public static class Id{
            public static final String WELCOME_IMAGE = "fkljgslskdjewr456";
            public static final String VIEW_PAGER = "pager";
        }
    }

    public Home(WebDriver driver) {
        super(driver);
    }

    ActionHomeScreen actionHomeScreen = new ActionHomeScreen();
    private static int currentScreenId = Constant.ScreenId.UNKNOWN_SCREEN;

    @Override
    public int detectedScreen(){
        return currentScreenId;
    }

    @Override
    public boolean isVisible() {
        if (isExistHomeScreen()){
            return true;
        }else return false;
    }

    public void start() {

        switch (detectedScreen()) {
            case Constant.ScreenId.HOME_SCREEN:
                randomActionHomeScreen();
                break;
            default:

        }
    }

    private class ActionHomeScreen{

        private void swipeRightLeft(){
            By idPager = By.id(Constant.Id.VIEW_PAGER);
            WebElement pager = waitForElement(idPager, driver, 60);
            if(pager == null){
                TestManager.log("\"Pager\" element was not found!");
            }else {
                swipe(pager, ((int) (Math.random() * 2) - 1));
                TestManager.log("SWIPE");
            }
        }

        private void swipeUpDown(){
            By idPager = By.id(Constant.Id.VIEW_PAGER);
            WebElement pager = waitForElement(idPager, driver, 60);
            if(pager == null){
                TestManager.log("\"Pager\" element was not found!");
            }else {
                swipe(pager, TestManager.getRandomInt(2, 1));
                TestManager.log("SWIPE");
            }
        }
    }

    public boolean isExistHomeScreen(){
        if(isWebElementPresent(By.id(Constant.Id.WELCOME_IMAGE))){
            currentScreenId = Constant.ScreenId.HOME_SCREEN;
            return true;
        }else return false;
    }

    public void randomActionHomeScreen(){
        switch (setRandomNumber(2)){
            case 0:
                actionHomeScreen.swipeRightLeft();
                break;
            case 1:
                actionHomeScreen.swipeUpDown();
        }
    }

}
