/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author hirag
 */
public class NomorTransaksi {
    public static String generateUniqueTransactionNumber() {
        long timestamp = System.currentTimeMillis();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String formattedTimestamp = dateFormat.format(new Date(timestamp));

        Random random = new Random();
        int randomValue = random.nextInt(10000);

        String uniqueTransactionNumber = formattedTimestamp + randomValue;

        return uniqueTransactionNumber;
    }
}
