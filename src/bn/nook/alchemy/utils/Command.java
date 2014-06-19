package bn.nook.alchemy.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Alecs on 18.06.2014.
 */
public class Command {


    private String path;
    private String command;

    public Command(String path, String command) {
        this.command = command;
        this.path = path;
    }

    public void doCommand() {
        Runtime runtime = Runtime.getRuntime();
        InputStreamReader isr = null;
        BufferedReader br = null;
        String line;
        Process process = null;
        try {
            process = runtime.exec(path + command);
            process.waitFor();
            isr = new InputStreamReader(process.getInputStream());
            br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            finishCommand(isr, br, process);
        }
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
