package bn.nook.alchemy.screen;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Alecs on 18.06.2014.
 */
public interface IScreenHelper {
    public void start();
    //return current screen Id
    int detectedScreen();

    //return state of screen
    public boolean isVisible();

}
