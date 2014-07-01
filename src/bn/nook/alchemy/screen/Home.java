package bn.nook.alchemy.screen;

import bn.nook.alchemy.utils.TestManager;
import net.bugs.testhelper.view.View;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Alecs on 25.06.2014.
 */
public class Home extends ScreenHelper {
    private static int currentScreenId = Constant.ScreenId.UNKNOWN_SCREEN;
    ActionHomeScreen actionHomeScreen = new ActionHomeScreen();
    private String nameOfCurrentTub = null;

    public static class Constant{
        public static class ScreenId{
            public static final int UNKNOWN_SCREEN = -1;
            public static final int MY_HOME_SCREEN = 0;
            public static final int DISCOVERY_SCREEN = 1;
            public static final int TEMPORARY_SCREEN = 2;
            public static final int QUICK_READS_SCREEN = 3;
            public static final int LIBRARY_SCREEN = 4;
        }

        public static class Id{
            public static final String WELCOME_IMAGE = "fkljgslskdjewr456";
            public static final String VIEW_PAGER = "pager";
        }

        public static class Text{
            public static final String HOME = "Home";
            public static final String DISCOVERY_SCREEN = "Discovery";
            public static final String CLASS_NAME_TABS_BAR = "android.widget.TextView";
            public static final String TEMPORARY_SCREEN = "Temporary";
            public static final String QUICK_READS_SCREEN = "Quick Reads";
            public static final String LIBRARY_SCREEN = "Library";
        }
    }

    public Home(WebDriver driver) {
        super(driver);

    }

    @Override
    public int detectedScreen(){
        return currentScreenId;
    }

    @Override
    public boolean isVisible() {
        View view =  getWidestChild(Constant.Text.CLASS_NAME_TABS_BAR);
        if(view == null)
            return false;

        nameOfCurrentTub = view.getText();

        if (isExistMyHomeScreen() ||
                isExistDiscovery() ||
                isExistTemporaryScreen() ||
                isExistQuickReadsScreen() ||
                isExistLibraryScreen()){
            return true;
        }

        return false;
    }

    public void start() {
        switch (detectedScreen()) {
            case Constant.ScreenId.MY_HOME_SCREEN:
                TestManager.log("\"My Home\" screen is detected");
                randomActionMyHomeScreen();
                break;
            case Constant.ScreenId.DISCOVERY_SCREEN:
                TestManager.log("\"Discovery\" screen is detected");
                randomActionDiscoveryScreen();
                break;
            case Constant.ScreenId.TEMPORARY_SCREEN:
                TestManager.log("\"Temporary\" screen is detected");
                randomActionTemporaryScreen();
                break;
            case Constant.ScreenId.QUICK_READS_SCREEN:
                TestManager.log("\"Quck Reads\" screen is detected");
                randomActionQuickReadsScreen();
                break;
            case Constant.ScreenId.LIBRARY_SCREEN:
                TestManager.log("\"Library\" screen is detected");
                randomActionLibraryScreen();
                break;
        }
    }

    private class ActionHomeScreen {

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

    private class ActionMyHomeScreen{

    }

    private class ActionDiscoveryScreen{

    }

    private class ActionTemporaryScreen {

    }

    private class ActionQuckReadsScreen{

    }

    private class ActionLibraryScreen{

    }

    public boolean isExistMyHomeScreen(){
        if(Constant.Text.HOME.equals(nameOfCurrentTub)){
            currentScreenId = Constant.ScreenId.MY_HOME_SCREEN;
            return true;
        }else return false;
    }

    public boolean isExistDiscovery(){
        if (Constant.Text.DISCOVERY_SCREEN.equals(nameOfCurrentTub)) {
            currentScreenId = Constant.ScreenId.DISCOVERY_SCREEN;
            return true;
        }else return false;
    }

    public boolean isExistTemporaryScreen(){
        if (Constant.Text.TEMPORARY_SCREEN.equals(nameOfCurrentTub)) {
            currentScreenId = Constant.ScreenId.TEMPORARY_SCREEN;
            return true;
        }else return false;
    }

    public boolean isExistQuickReadsScreen(){
        if(Constant.Text.QUICK_READS_SCREEN.equals(nameOfCurrentTub)){
            currentScreenId = Constant.ScreenId.QUICK_READS_SCREEN;
            return true;
        }else return false;
    }

    public boolean isExistLibraryScreen(){
        if(Constant.Text.LIBRARY_SCREEN.equals(nameOfCurrentTub)){
            currentScreenId = Constant.ScreenId.LIBRARY_SCREEN;
            return true;
        }else return false;
    }


    public void randomActionMyHomeScreen(){
        switch (getRandomNumber(2)){
            case 0:
                actionHomeScreen.swipeRightLeft();
                break;
            case 1:
                actionHomeScreen.swipeUpDown();
                break;
        }
    }

    public void randomActionDiscoveryScreen(){
        switch (getRandomNumber(2)){
            case 0:
                actionHomeScreen.swipeRightLeft();
                break;
            case 1:
                actionHomeScreen.swipeUpDown();
                break;
        }
    }

    public void randomActionTemporaryScreen(){
        switch (getRandomNumber(2)){
            case 0:
                actionHomeScreen.swipeRightLeft();
                break;
            case 1:
                actionHomeScreen.swipeUpDown();
                break;
        }
    }

    public void randomActionQuickReadsScreen(){
        switch (getRandomNumber(2)){
            case 0:
                actionHomeScreen.swipeRightLeft();
                break;
            case 1:
                actionHomeScreen.swipeUpDown();
                break;
        }
    }

    public void randomActionLibraryScreen(){
        switch (getRandomNumber(2)){
            case 0:
                actionHomeScreen.swipeRightLeft();
                break;
            case 1:
                actionHomeScreen.swipeUpDown();
                break;
        }
    }

}
