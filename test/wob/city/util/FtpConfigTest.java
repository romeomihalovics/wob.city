package wob.city.util;

import org.apache.commons.net.ftp.FTPClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.SocketException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FtpConfigTest {
    @Test
    @DisplayName("FTP Client should connect and log in to ftp server with the credentials in the config class")
    void ftpClientShouldConnect() {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(FtpConfig.ftpURL);
            ftpClient.login(FtpConfig.ftpUser, FtpConfig.ftpPassword);
            ftpClient.enterLocalPassiveMode();
            assertTrue(ftpClient.isConnected());
            assertEquals(230, ftpClient.getReplyCode());
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
