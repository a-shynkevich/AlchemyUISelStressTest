package bn.nook.alchemy.utils;

import io.selendroid.device.DeviceTargetPlatform;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Alecs on 17.06.2014.
 */
public class CheckerAPI {


    private String serialNumbOfDevice = null;
    //    private String deviceVer;
    private String [] devicesVersion = new String[]{"2.3.3","3.0","3.1","3.2","4,0","4.0.3","4.1.2","4.2.2","4.3","4.4.2","4.4.3"};

    public DeviceTargetPlatform getApiLvl (String serialNimber){
        int devVer = checkVersion(findDeviceVer(serialNimber));
        DeviceTargetPlatform devTargPlatf = null;
        switch (devVer){
            case 0: {devTargPlatf = DeviceTargetPlatform.ANDROID10; break;}
            case 1: {devTargPlatf = DeviceTargetPlatform.ANDROID11; break;}
            case 2: {devTargPlatf = DeviceTargetPlatform.ANDROID12; break;}
            case 3: {devTargPlatf = DeviceTargetPlatform.ANDROID13; break;}
            case 4: {devTargPlatf = DeviceTargetPlatform.ANDROID14; break;}
            case 5: {devTargPlatf = DeviceTargetPlatform.ANDROID15; break;}
            case 6: {devTargPlatf = DeviceTargetPlatform.ANDROID16; break;}
            case 7: {devTargPlatf = DeviceTargetPlatform.ANDROID17; break;}
            case 8: {devTargPlatf = DeviceTargetPlatform.ANDROID18; break;}
            case 9: {devTargPlatf = DeviceTargetPlatform.ANDROID19; break;}
            case 10: {devTargPlatf = DeviceTargetPlatform.ANDROID19;break;}
        }
        return devTargPlatf;
    }

    private int checkVersion(String deviceVer) {

        int value = 0;
        for (int i = 0; i<devicesVersion.length; i++) {
            String version = devicesVersion[i];
            if (deviceVer.equals(version)) {
                value = i;
            }
        }
        if (value == 0){
            value = 10;
        }
        return value;
    }

    private String findDeviceVer(String serialNumbOfDevice){
        Runtime runtime = Runtime.getRuntime();
        InputStreamReader isr = null;
        BufferedReader br = null;
        String deviceVer = null;
        Process process = null;

//        String pathToAdb = "c:\\adt-bundle-windows-x86_64-20130911\\sdk\\platform-tools\\";
        String command = "adb -s "+serialNumbOfDevice+ " shell getprop ro.build.version.release";
        try {
            process = runtime.exec(command);
            isr = new InputStreamReader(process.getInputStream());
            br = new BufferedReader(isr);
            deviceVer = br.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            finishCommand(isr, br, process);
        }
        return deviceVer;
    }

    private void finishCommand(InputStreamReader isr, BufferedReader br, Process process) {
        if (isr != null) {
            try {
                isr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (br != null) {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (process != null) {
            process.destroy();
        }
    }
}
