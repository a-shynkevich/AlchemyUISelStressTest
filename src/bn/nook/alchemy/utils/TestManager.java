package bn.nook.alchemy.utils;

import io.selendroid.SelendroidCapabilities;
import io.selendroid.SelendroidConfiguration;
import io.selendroid.SelendroidDriver;
import io.selendroid.SelendroidLauncher;
import io.selendroid.server.handler.CaptureScreenshot;
import net.bugs.testhelper.TestHelper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    private static String mPathToRootFolder = null;
    private static net.bugs.testhelper.helpers.PropertiesManager mPropertiesManager;

    private static String timeLog = new SimpleDateFormat("d.MM.YYYY_HH:MM:s_").format(Calendar.getInstance().getTime());

    private static long rowNumber = 0;

    private static String appID = "bn.ereader:4.0.0.158";
    public static WebDriver driver = null;

    public static void startServer(){
        SelendroidCapabilities cap = new SelendroidCapabilities(appID);
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
        String fileName = mTestHelper.getHwDevice() +" (" + mTestHelper.getOsDevice() + ").txt";
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(TestManager.getInstance().getPathToReportFolder() + fileName, true)));
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

    public static int getRandomInt(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    public static void takeScreenshot(){
        mTestHelper.takeScreenshot("screenshot-"+".png");
    }
        public String getPathToReportFolder(){
        if(mPathToRootFolder == null)
            mPathToRootFolder = mPropertiesManager.getProperty(Constant.Config.REPORT_FOLDER_PROPERTY);
        return mPathToRootFolder;
    }

}
