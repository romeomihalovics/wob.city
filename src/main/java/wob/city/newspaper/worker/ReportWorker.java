package wob.city.newspaper.worker;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import wob.city.newspaper.abstraction.NewsPaper;
import wob.city.newspaper.abstraction.PersonNews;
import wob.city.newspaper.enums.Limit;
import wob.city.newspaper.object.DisasterNews;
import wob.city.newspaper.object.PersonHistoryNews;
import wob.city.util.FtpConfig;

import java.io.*;
import java.util.TimerTask;

public class ReportWorker extends TimerTask {
    private final NewsPaper newsPaper;
    private int fromId = 0;

    public ReportWorker(NewsPaper newsPaper) {
        this.newsPaper = newsPaper;
    }

    @Override
    public void run() {
        try {
            newsPaper.fetchData(Limit.FETCH_LIMIT.getValue(), fromId);
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
        boolean hasNextPage = false;
        try {
            ftpClient.connect(FtpConfig.URL);
            ftpClient.login(FtpConfig.USER, FtpConfig.PASSWORD);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            try(InputStream inputStream = new FileInputStream(tempReport)) {
                ftpClient.makeDirectory(newsPaper.getFolder());
                ftpClient.changeWorkingDirectory(newsPaper.getFolder());
                ftpClient.storeFile(tempReport.getName(), inputStream);
                newsPaper.setToReported(Limit.FETCH_LIMIT.getValue(), fromId);

                hasNextPage = checkForPagination();
            }finally {
                newsPaper.flushData();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            tempReport.delete();
            if(hasNextPage) {
                run();
            }
        }
    }

    private boolean checkForPagination() {
        boolean hasNextPage = false;
        if (newsPaper.getFetchedSize() == Limit.FETCH_LIMIT.getValue()) {
            fromId = newsPaper.getLastId();
            hasNextPage = true;
        }
        return hasNextPage;
    }
}