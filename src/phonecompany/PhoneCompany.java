package phonecompany;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PhoneCompany {

    public static String readFileAndPrint(String fileName) {
	FileReader fileReader = null;
	BufferedReader bufferedReader = null;
        String text = "";
	try {
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                text += line + "\n";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return text;
    }
        
    public static void main(String[] args) {
        String[] msg = readFileAndPrint("D:\\- Work2\\MFEC\\promotion1.log").split("\n");
        String[] callTime;
        Date startTime,endTime;
        long diff,hours,minutes,seconds;
        double price = 0;
        double sec;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        
        try {
            for (int i = 0; i < msg.length; i++) {
                callTime = msg[i].split("\\|");
                startTime = format.parse(callTime[0] + " " + callTime[1]);
                endTime = format.parse(callTime[0] + " " + callTime[2]);
                diff = (endTime.getTime() - startTime.getTime())/1000;
                hours = (diff / 3600) % 24;
                minutes = (diff /60) % 60 ;
                seconds = diff % 60;
                sec = seconds;
                
                if ("P1".equals(callTime[4])) {
                    if (hours == 0 && minutes == 0) {
                        price = 3;
                    }
                    else {
                        price = (hours * 60) + (minutes - 1) + (sec/60) + 3;
                    }
                }
                else if ("P2".equals(callTime[4])) {
                    price = ((hours * 60) + minutes + (sec/60))*2 ;
                }
                System.out.printf(msg[i] + "\nDuration: " + hours + ":" + minutes + ":" + seconds + "\nPrice: %.2f Baht.\n",price);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
