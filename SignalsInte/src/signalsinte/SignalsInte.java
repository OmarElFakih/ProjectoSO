/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signalsinte;

import sun.misc.Signal;
import sun.misc.SignalHandler;

/**
 *
 * @author franciscogomezlopez
 */
public class SignalsInte {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DebugSignalHandler.listenTo("HUP");
        DebugSignalHandler.listenTo("INT");
        //DebugSignalHandler.listenTo("KILL");
        DebugSignalHandler.listenTo("TERM");

        //Runtime.getRuntime().exec("kill -SIGINT 12345");
        
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class DebugSignalHandler implements SignalHandler {

    
    public static void listenTo(String name) {
        Signal signal = new Signal(name);
        DebugSignalHandler t = new DebugSignalHandler();
        t.setName(name);
        Signal.handle(signal, t);
    }

    public void handle(Signal signal) {
        System.out.println("Signal: " + signal + " - name:" + this.name);
        if (signal.toString().trim().equals("SIGTERM")) {
            System.out.println("Cerrando taller 2 sistemas distribuidos");
            System.exit(1);
        }
    }
    
    public void setName(String val) {
        this.name = val;
    }
    
    private String name;
}
