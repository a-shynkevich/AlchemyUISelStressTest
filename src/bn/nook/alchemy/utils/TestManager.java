package bn.nook.alchemy.utils;

import net.bugs.testhelper.TestHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Random;


/**
 * Created by nikolai on 06.06.14.
 */
public class TestManager {

    private static TestManager instanceTestManager = null;
    private static TestHelper mTestHelper = null;
    private static String mDeviceId = null;
    private static String mLogin = null;
    private static String mPassword = null;
    private static String mPathToAPK = null;
    private static net.bugs.testhelper.helpers.PropertiesManager mPropertiesManager;

    public static void log(String message){
        System.out.println(message);
    }

    private TestManager(String deviceId){
        mTestHelper = new TestHelper(deviceId);
        mPropertiesManager = mTestHelper.propertiesManager;
        readProperties();
    }

    public static TestManager getInstance(final String deviceId){
        mDeviceId = deviceId;
        if(instanceTestManager == null){
            synchronized (TestManager.class){
                if(instanceTestManager == null)
                    instanceTestManager = new TestManager(mDeviceId);
            }
        }
        return instanceTestManager;
    }

    public static TestManager getInstance(){
        return getInstance(mDeviceId);
    }

    public void runIntent(String intent){
        mTestHelper.executeShellCommand(" am start -n " + intent, mTestHelper.defaultCallBack);
    }

    public TestHelper getTestHelper() {
        return mTestHelper;
    }

    private void readProperties(){
        PropertiesManager.init();
    }

    public static int generateRandom(int n){
        if(n == 0)
            return 0;
        Random random = new Random();
        return Math.abs(random.nextInt()) % n;
    }

    public String getLogin(){
        if(mLogin == null)
            mLogin = mPropertiesManager.getProperty(Constant.Config.LOGIN_PROPERTY);
        return mLogin;
    }

    public String getPassword(){
        if(mPassword == null)
            mPassword = mPropertiesManager.getProperty(Constant.Config.PASSWORD_PROPERTY);
        return mPassword;
    }
    public String getPathToAPK(){
        if (mPathToAPK == null)
            mPathToAPK = mPropertiesManager.getProperty(Constant.Config.PATH_TO_APK);
        return mPathToAPK;
    }

}