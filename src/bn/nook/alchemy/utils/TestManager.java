package bn.nook.alchemy.utils;

import io.selendroid.SelendroidCapabilities;
import io.selendroid.SelendroidDriver;
import net.bugs.testhelper.TestHelper;
import org.openqa.selenium.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class TestManager {

    private static TestManager instanceTestManager = null;
    private static TestHelper mTestHelper = null;
    private static String mDeviceId = null;
    private static String mLogin = null;
    private static String mPassword = null;
    private static String mPathToRootFolder = null;
    private static net.bugs.testhelper.helpers.PropertiesManager mPropertiesManager;
    private static String timeLog = new SimpleDateFormat("d.MM.YYYY HH:MM:s ").format(Calendar.getInstance().getTime());
    private static long rowNumber = 0;
    public static WebDriver driver = null;
    private static String mAppID = null;
    private static String mDeviceHW = null;
    private static String mDeviceOS = null;

    public static void startServer(){
        getAppID();
        SelendroidCapabilities cap = new SelendroidCapabilities(mAppID);
        try {
            driver = new SelendroidDriver(cap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stopServer(){
        if(driver != null){
            driver.close();
        }
    }

    public static void log(Object message){
        String fileName = mDeviceHW +" (" + mDeviceOS + ").txt";
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(getPathToReportFolder() + fileName, true)));
            out.println(++rowNumber + ". " + timeLog + message);
        }catch (IOException e) {
            System.err.println(e);
        }finally {
            if (out != null) {
                out.close();
            }
        }

        System.out.println(message);
    }

    private TestManager(String deviceId){
        mTestHelper = new TestHelper(deviceId);
        mDeviceHW = mTestHelper.getHwDevice();
        mDeviceOS = mTestHelper.getOsDevice();
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

    private static String getAppID(){
        if(mAppID == null)
            mAppID = mPropertiesManager.getProperty(Constant.Config.APP_ID_PROPERTY);
        return mAppID;
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

    public static int getRandomInt(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    public static void takeScreenshot(){
        mTestHelper.takeScreenshot("screenshot"+".png");
    }

    public static String getPathToReportFolder(){
        if(mPathToRootFolder == null)
            mPathToRootFolder = mPropertiesManager.getProperty(Constant.Config.REPORT_FOLDER_PROPERTY);
        return mPathToRootFolder;
    }

}
