package ru.progwards.java1.lessons.files;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

public class test1 {
    public static double main(String[] args) {
        final int testCount = 2;

        File file = new File(false ? "D:/work/bench.dat" : "./work/bench.dat");
        RandomAccessFile wFile = null, rFile = null;
        try {
            System.out.println("Allocating test file ...");
            int blockSize = 1024 * 1024;
            long size = false ? 10L * 1024L * (long) blockSize : Runtime.getRuntime().maxMemory() * 2;
            byte[] block = new byte[blockSize];
            for (int i = 0; i < blockSize; i++) {
                if (i % 2 == 0) block[i] = (byte) (i & 0xFF);
            }

            System.out.println("Writing ...");
            wFile = new RandomAccessFile(file, "rw");
            wFile.setLength(size);
            for (long i = 0; i < size - blockSize; i += blockSize) {
                wFile.write(block);
            }
            wFile.close();

            System.out.println("Running read test ...");
            long t0 = System.nanoTime();

            rFile = new RandomAccessFile(file, "r");
            int blockCount = (int) (size / blockSize) - 1;
            Random rnd = new Random();
            for (int i = 0; i < testCount; i++) {
                rFile.seek((long) rnd.nextInt(blockCount) * (long) blockSize);
                rFile.readFully(block, 0, blockSize);
            }
            rFile.close();

            long t1 = System.nanoTime();

            double readB = ((double) testCount * (double) blockSize);
            double timeNs = (double) (t1 - t0);

            return (readB / (1024 * 1024)) / (timeNs / (1000 * 1000 * 1000));
        } catch (Exception e) {
            System.out.println("Failed to benchmark drive speed!" + e);
            return 0;
        } finally {
            if (wFile != null) {
                try {
                    wFile.close();
                } catch (IOException e) {
                }
            }
            if (rFile != null) {
                try {
                    rFile.close();
                } catch (IOException e) {
                }
            }
            if (file.exists()) {
                file.delete();
            }
        }
    }
}