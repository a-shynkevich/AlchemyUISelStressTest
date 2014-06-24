package bn.nook.alchemy.screen;

import bn.nook.alchemy.utils.TestManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static java.lang.Thread.sleep;

/**
 * Created by Alecs on 17.06.2014.
 */
public class Library extends ScreenHelper {
    public Library(WebDriver driver) {
        super(driver);
    }

    public static class Constant{
        public static class Id{
            public static final String ACTION_BAR = "action_bar";
            public static final String MENU_BTN = "up";
            public static final String MENU_LIST_VIEW = "listview";
            public static final String TEXTVIEW = "textview";
        }
    }

    public void signOut (WebDriver driver) throws InterruptedException {
        waitForElement(By.id(Constant.Id.ACTION_BAR), driver, 30000);

        WebElement menuBtn = driver.findElement(By.id(Constant.Id.MENU_BTN));
        if (menuBtn.isDisplayed()){
            menuBtn.click();
            sleep (1000);
        }else {
            TestManager.log("\"Menu\" button was not found");
        }
        sleep(4000);
    }
}
