package bn.nook.alchemy.screen;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Alecs on 18.06.2014.
 */
public interface IScreenHelper {
    public void start();

    int detectedScreen();
    //return current screen Id

    public void clickById(By elementId) throws InterruptedException;
    //click on element by Id.
}
