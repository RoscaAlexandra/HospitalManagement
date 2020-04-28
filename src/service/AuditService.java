package service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class AuditService {
    private File log;

    private AuditService() {
        this.log = new File("AUDIT");
    }
    public void addAction(String action) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        try{
            if(!log.exists()){
                System.out.println("We had to make a new file.");
                log.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(log, true);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(action + "," + timestamp + "\n");
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static AuditService getInstance() {
        return AuditService.SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static AuditService INSTANCE = new AuditService();
    }
}
