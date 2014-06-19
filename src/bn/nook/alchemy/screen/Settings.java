package bn.nook.alchemy.screen;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Alecs on 17.06.2014.
 */
public class Settings extends ScreenHelper{

    public Settings(WebDriver driver) {
        super(driver);
    }

    public static class Constant{
        public static class Id{
            public static final String SIGN_OUT = "settings_logout_btn";
        }
    }
   public void signOut(WebDriver driver){
       waitForElement(By.id(Constant.Id.SIGN_OUT), driver, 30000);
       WebElement signOutBtn = driver.findElement(By.id(Constant.Id.SIGN_OUT));
       signOutBtn.click();
   }
}
