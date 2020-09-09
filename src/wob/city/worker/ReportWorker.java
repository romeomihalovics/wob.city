package wob.city.worker;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import wob.city.abstractions.NewsPaper;
import wob.city.util.FtpConfig;

import java.io.*;
import java.util.TimerTask;

public class ReportWorker extends TimerTask {
    private final NewsPaper newsPaper;

    public ReportWorker(NewsPaper newsPaper) {
        this.newsPaper = newsPaper;
    }

    @Override
    public void run() {
        try {
            generateReport();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generateReport() throws IOException {
        File tempReport = File.createTempFile("report-", ".json");
        FileWriter fileWriter = new FileWriter(tempReport, false);
        try(BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)){
            bufferedWriter.write(newsPaper.toString());
        } finally {
            uploadReport(tempReport);
        }
    }

    private void uploadReport(File tempReport) {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(FtpConfig.ftpURL);
            ftpClient.login(FtpConfig.ftpUser, FtpConfig.ftpPassword);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            try(InputStream inputStream = new FileInputStream(tempReport)) {
                ftpClient.makeDirectory(newsPaper.getFolder());
                ftpClient.changeWorkingDirectory(newsPaper.getFolder());
                ftpClient.storeFile(tempReport.getName(), inputStream);
            }finally {
                newsPaper.flushData();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            tempReport.delete();
        }
    }
}
