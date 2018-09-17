package tv.danmaku.ijk.media.example.activities;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;

public class MediaScanner {
    private MediaScannerConnection nY = null;
    private MediaScanner.MusicSannerClient nZ = null;
    private String oa = null;
    private String ob = null;
    private String[] oc = null;

    public MediaScanner(Context context) {
        this.nZ = new MediaScanner.MusicSannerClient();
        this.nY = new MediaScannerConnection(context, this.nZ);
    }

    public void scanFile(String filepath, String fileType) {
        this.oa = filepath;
        this.ob = fileType;
        this.nY.connect();
    }

    public void scanFile(String[] filePaths, String fileType) {
        this.oc = filePaths;
        this.ob = fileType;
        this.nY.connect();
    }

    public String getFilePath() {
        return this.oa;
    }

    public void setFilePath(String filePath) {
        this.oa = filePath;
    }

    public String getFileType() {
        return this.ob;
    }

    public void setFileType(String fileType) {
        this.ob = fileType;
    }

    class MusicSannerClient implements MediaScannerConnection.MediaScannerConnectionClient {
        MusicSannerClient() {
        }

        public void onMediaScannerConnected() {
            if (MediaScanner.this.oa != null) {
                MediaScanner.this.nY.scanFile(MediaScanner.this.oa, MediaScanner.this.ob);
            }

            if (MediaScanner.this.oc != null) {
                String[] var1 = MediaScanner.this.oc;
                int var2 = var1.length;

                for(int var3 = 0; var3 < var2; ++var3) {
                    String file = var1[var3];
                    MediaScanner.this.nY.scanFile(file, MediaScanner.this.ob);
                }
            }

            MediaScanner.this.oa = null;
            MediaScanner.this.ob = null;
            MediaScanner.this.oc = null;
        }

        public void onScanCompleted(String path, Uri uri) {
            MediaScanner.this.nY.disconnect();
        }
    }
}