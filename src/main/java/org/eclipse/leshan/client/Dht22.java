package org.eclipse.leshan.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;

public class Dht22 {
    public static float getTemperature(int pin){
        float t = 0.0f;
        String cmd = String.format("python DHT/dht.py 22 %d", pin);
        try {
            String out = "";
            try {
                String line;
                Process p = Runtime.getRuntime().exec(cmd.split(" "));
                p.waitFor();
                BufferedReader input = new BufferedReader
                        (new InputStreamReader(p.getInputStream()));
                while ((line = input.readLine()) != null) {
                    out += line;
                }
                input.close();
                System.out.println(out);
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

            if (out.length() == 0) // Library is not present
                throw new RuntimeException("LIB_NOT_PRESENT_MESSAGE");
            else{
                // Error reading the the sensor, maybe is not connected.
                if(out.contains("Failed to get reading. Try again!")){
                    String msg = String.format("ERROR_READING_MSG");
                    throw new Exception(msg);
                }
                else{
                    // Read completed. Parse and update the values
                    String[] vals = out.split(" ");
                    t = Float.parseFloat(vals[0]);
                    float h = Float.parseFloat(vals[1]);
                    /*if( (t != lastTemp) || (h != lastHum) ){
                        lastUpdate = new Date();
                        lastTemp = t;
                        lastHum = h;
                    }*/
                }
            }
        } catch (Exception e) {
            System.out.println(String.format("Error: %s", e.getMessage()));
            if( e instanceof RuntimeException)
                System.exit(-1);
        }
        finally {
            return t;
        }
    }

    public static float getHumidity(int pin){
        float h = 0.0f;
        String cmd = String.format("python DHT/dht.py 22 %d", pin);
        try {
            String out = "";
            try {
                String line;
                Process p = Runtime.getRuntime().exec(cmd.split(" "));
                p.waitFor();
                BufferedReader input = new BufferedReader
                        (new InputStreamReader(p.getInputStream()));
                while ((line = input.readLine()) != null) {
                    out += line;
                }
                input.close();
                System.out.println(out);
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

            if (out.length() == 0) // Library is not present
                throw new RuntimeException("LIB_NOT_PRESENT_MESSAGE");
            else{
                // Error reading the the sensor, maybe is not connected.
                if(out.contains("Failed to get reading. Try again!")){
                    String msg = String.format("ERROR_READING_MSG");
                    throw new Exception(msg);
                }
                else{
                    // Read completed. Parse and update the values
                    String[] vals = out.split(" ");
                    float t = Float.parseFloat(vals[0]);
                    h = Float.parseFloat(vals[1]);
                    /*if( (t != lastTemp) || (h != lastHum) ){
                        lastUpdate = new Date();
                        lastTemp = t;
                        lastHum = h;
                    }*/
                }
            }
        } catch (Exception e) {
            System.out.println(String.format("Error: %s", e.getMessage()));
            if( e instanceof RuntimeException)
                System.exit(-1);
        }
        finally {
            return h;
        }
    }

    public static void update(int pin) {
        String cmd = String.format("python dht.py 22 %d", pin);
        try {
            String out = "";
            try {
                String line;
                Process p = Runtime.getRuntime().exec(cmd.split(" "));
                p.waitFor();
                BufferedReader input = new BufferedReader
                        (new InputStreamReader(p.getInputStream()));
                while ((line = input.readLine()) != null) {
                    out += line;
                }
                input.close();
                System.out.println(out);
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

            if (out.length() == 0) // Library is not present
                throw new RuntimeException("LIB_NOT_PRESENT_MESSAGE");
            else{
                // Error reading the the sensor, maybe is not connected.
                if(out.contains("Failed to get reading. Try again!")){
                    String msg = String.format("ERROR_READING_MSG");
                    throw new Exception(msg);
                }
                else{
                    // Read completed. Parse and update the values
                    String[] vals = out.split(" ");
                    float t = Float.parseFloat(vals[0]);
                    float h = Float.parseFloat(vals[1]);
                    /*if( (t != lastTemp) || (h != lastHum) ){
                        lastUpdate = new Date();
                        lastTemp = t;
                        lastHum = h;
                    }*/
                    System.out.println(String.format("Temp: %f  Hum: %f", new BigDecimal(t), new BigDecimal(h)));
                }
            }
        } catch (Exception e) {
            System.out.println(String.format("Error: %s", e.getMessage()));
            if( e instanceof RuntimeException)
                System.exit(-1);
        }
    }
}

