package bn.nook.alchemy.screen;

import bn.nook.alchemy.utils.TestManager;
import net.bugs.testhelper.TestHelper;
import net.bugs.testhelper.view.View;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.*;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

/**
 * Created by Alecs on 17.06.2014.
 */
public class ScreenHelper implements IScreenHelper {
    private static final int SWIPE_LEFT = -1;
    private static final int SWIPE_RIGHT = 0;
    private static final int SWIPE_DOWN = 1;
    private static final int SWIPE_UP = 2;

    protected WebDriver driver = null;
    protected TestHelper testHelper;

    public ScreenHelper(WebDriver driver){
        this.driver = driver;
        testHelper = TestManager.getInstance().getTestHelper();
    }

    protected WebElement waitForElement(final By by,  WebDriver driver, long timeoutInSec) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSec);
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            if (element.isDisplayed()) {
                return element;
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
    protected boolean isWebElementPresent(By by){
        try {
            WebElement element = driver.findElement(by);
            if(element.isDisplayed()) {
                return true;
            }
        }catch (Exception e){
            return false;
        }
        return false;
    }

    public WebElement getNativeViewElement(WebDriver driver, By parentBy, By childBy, int indexChild){
        WebElement webElement = null;
        WebElement parent = driver.findElement(parentBy);
        if(parent.isDisplayed()){
            List<WebElement> list = parent.findElements(childBy);
            if(list.size() > 0){
                webElement = list.get(indexChild);
            }
        }
        return webElement;
    }

    public WebElement getNativeViewElementByIndex(WebDriver driver, By parentBy, By childBy, int indexChild){
        WebElement webElement = null;
        WebElement parent = driver.findElement(parentBy);
        List<WebElement> list = parent.findElements(childBy);
        for(int i = 0; i < list.size(); i++){
            webElement = list.get(i);
            TestManager.log(webElement.getText());
        }
        return webElement;
    }
    public WebElement getNativeViewElementByText(WebDriver driver, By parentBy, By childBy, String text) {
        WebElement webElement = null;
        WebElement parent = driver.findElement(parentBy);
        List<WebElement> list = parent.findElements(childBy);

        for (int i = 0; i < list.size(); i++) {
            webElement = list.get(i);
            TestManager.log("TEXT: " + webElement.getText());
            if (webElement.getText().equals(text)) {
                return webElement;
            }
        }
        return null;
    }

    public void start(){}

    @Override
    public int detectedScreen() {
        return 0;
    }

    @Override
    public boolean isVisible() {
        return false;
    }

    public void clickById(By elementId)  {
        try {
            sleep(1000);
            WebElement element = findElement(elementId);
            element.click();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickByText(By elementBy){
        try {
            sleep(1000);
            WebElement element = findElement(elementBy);
            element.click();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getRandomNumber(int maxNumber){
//        Random random = new Random();
//        return random.nextInt(maxNumber);
        return (int) (Math.random() * maxNumber);
    }

    public boolean swipe(WebElement webElement, int swipeSide){
        return swipe(webElement, swipeSide, 0);
    }

    public boolean swipe(WebElement webElement, int swipeSide, int distance) {
        if (webElement == null || !webElement.isDisplayed())
            return false;
        int width = webElement.getSize().getWidth();
        int height = webElement.getSize().getHeight();

        switch (swipeSide) {
            case SWIPE_LEFT:
                if (distance == 0)
                    distance = width - 10;
                distance = distance * (-1);
                new TouchActions(driver).flick(webElement, distance, 0, 2).perform();
                break;
            case SWIPE_RIGHT:
                if (distance == 0)
                    distance = width - 10;
                new TouchActions(driver).flick(webElement, distance, 0, 2).perform();
                break;
            case SWIPE_DOWN:
                if (distance == 0)
                    distance = height - 10;
                new TouchActions(driver).flick(webElement, 0, distance, 2).perform();
                break;
            case SWIPE_UP:
                if (distance == 0)
                    distance = height - 10;
                distance = distance * (-1);
                new TouchActions(driver).flick(webElement, 0, distance, 2).perform();
                break;
        }
        return true;
    }

    public WebElement findElement (By byElement){
        WebElement element = null;
        try {
            element = driver.findElement(byElement);
        }catch (Exception e) {
            return null;
        }
        if(!element.isDisplayed()){
            return null;
        }
        return element;
    }

    public View getWidestChild(String className){
        View view = null;
        int width = 0;

        ArrayList<View> views = testHelper.getCurrentViewsByClass(className, true, false);
        for (int i = 0; i<views.size();i++){
            int viewWidth = views.get(i).getWidth();
            if (viewWidth > width) {
                width = viewWidth;
                view = views.get(i);
            }
        }
        return view;
    }
}
