package bn.nook.alchemy.screen;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Settings extends ScreenHelper{

    public Settings(WebDriver driver) {
        super(driver);

    }

    public static class Constant{
        public static class ScreenId{
            public static final int UNKNOWN_SCREEN = -1;
            public static final int SETTINGS = 0;
        }
        public static class Id{
            public static final String PACKAGE_PATH_FOR_ID = "bn.ereader:id/";
            public static final String SIGN_OUT = "settings_logout_btn";
        }
        public static class Text{
        }
    }


   public void signOut(WebDriver driver){
       waitForElement(By.id(Constant.Id.SIGN_OUT), driver, 30000);
       WebElement signOutBtn = driver.findElement(By.id(Constant.Id.SIGN_OUT));
       signOutBtn.click();
   }
}
