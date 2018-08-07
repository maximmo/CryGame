package ru.servtechno.cry;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

public class MicMonitoring {

    private AudioFormat audioFormat;
    private TargetDataLine targetDataLine;

    private MyThread thread;
    private Boolean stopCapture;

    private int RMSLevel;

    public MicMonitoring(){
        audioFormat = getAudioFormat();
        targetDataLine = null;
        try {
            targetDataLine = AudioSystem.getTargetDataLine(audioFormat);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        try {
            targetDataLine.open();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

    }

    public void startCapturing(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                targetDataLine.start();
                thread = new MyThread();
                thread.run();
            }
        }).start();
    }

    public int getRMSLevel() {
        return RMSLevel;
    }

    public void stopCapturing(){
        stopCapture = true;
    }

    public class MyThread extends Thread {
        public void run() {

            byte tempBuffer[] = new byte[6000];
            stopCapture = false;
            try {
                while (!stopCapture) {
                    if (targetDataLine.read(tempBuffer, 0, tempBuffer.length) > 0) {
                        RMSLevel = calculateRMSLevel(tempBuffer);
                        //System.out.println(RMSLevel);
                    }
                }
                targetDataLine.stop();
                targetDataLine.close();
                System.out.println("Capturing stop");
            } catch (Exception e) {
                System.out.println(e);
                System.exit(0);
            }
        }
    }

//    public void dispose(){
//        if (targetDataLine.isActive()){
//            targetDataLine.stop();
//            targetDataLine.close();
//        }
//
//        thread.interrupt();
//    }

    private AudioFormat getAudioFormat(){
        float sampleRate = 44100.0F;
        int sampleSizeInBits = 16;
        int channels = 1;
        boolean signed = true;
        boolean bigEndian = false;
        return new AudioFormat(sampleRate,sampleSizeInBits,channels,signed,bigEndian);
    }

    private int calculateRMSLevel(byte[] audioData)
    {
        long lSum = 0;
        for(int i=0; i < audioData.length; i++)
            lSum = lSum + audioData[i];

        double dAvg = lSum / audioData.length;
        double sumMeanSquare = 0d;

        for(int j=0; j < audioData.length; j++)
            sumMeanSquare += Math.pow(audioData[j] - dAvg, 2d);

        double averageMeanSquare = sumMeanSquare / audioData.length;

        //return (int)(Math.pow(averageMeanSquare,0.5d) + 0.5);
        return (int)(Math.pow(averageMeanSquare,0.5d) + 0.5);
    }
}
