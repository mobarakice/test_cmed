package ice.mobarak.test.ui.test01;

import android.app.ActivityManager;
import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import ice.mobarak.test.R;
import ice.mobarak.test.data.model.DownloadFile;
import ice.mobarak.test.data.network.ApiUrls;
import ice.mobarak.test.data.network.RetrofitInterface;
import ice.mobarak.test.utils.Constants;
import okhttp3.ResponseBody;

import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * This is service. It's used to handle download all kind of file
 * Created by Mobarak on 08 April, 2018
 */
public class DownloadService extends IntentService {

    /**
     * Default constructor
     */
    public DownloadService() {
        super(Constants.DOWNLOAD_SERVICE);
    }

    /**
     * Notification builder
     */
    private NotificationCompat.Builder notificationBuilder;
    /**
     * Notification Manager
     */
    private NotificationManager notificationManager;
    /**
     * Total content length
     */
    private int totalFileSize;


    @Override
    protected void onHandleIntent(Intent intent) {

        // Set up notification
        setUpNotification();
        // Initialized download
        initializedDownload();

    }

    /**
     * Initialized notification
     */
    private void setUpNotification() {
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationBuilder = new NotificationCompat.Builder(this, "")
                .setSmallIcon(R.drawable.ic_face_white_48px)
                .setContentTitle(getString(R.string.label_text_download))
                .setContentText(getString(R.string.label_text_downloading))
                .setAutoCancel(true);
        notificationManager.notify(0, notificationBuilder.build());
    }

    /**
     * Initialized download
     */
    private void initializedDownload() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrls.BASE_DOWNLOAD_URL)
                .build();

        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<ResponseBody> request = retrofitInterface.downloadFile();
        try {

            writeDownloadFile(request.execute().body());

        } catch (IOException e) {

            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }

    /**
     * @param body
     * @throws IOException
     */
    private void writeDownloadFile(ResponseBody body) throws IOException {

        int count;
        byte data[] = new byte[1024 * 4];
        long fileSize = body.contentLength();
        InputStream bis = new BufferedInputStream(body.byteStream(), 1024 * 8);
        File outputFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "file2.mp4");
        OutputStream output = new FileOutputStream(outputFile);
        long total = 0;
        long startTime = System.currentTimeMillis();
        int timeCount = 1;
        while ((count = bis.read(data)) != -1) {

            total += count;
            totalFileSize = (int) (fileSize / (Math.pow(1024, 2)));
            double current = Math.round(total / (Math.pow(1024, 2)));

            int progress = (int) ((total * 100) / fileSize);

            long currentTime = System.currentTimeMillis() - startTime;

            DownloadFile downloadFile = new DownloadFile();
            downloadFile.setTotalFileSize(totalFileSize);

            if (currentTime > 1000 * timeCount) {

                downloadFile.setCurrentFileSize((int) current);
                downloadFile.setProgress(progress);
                updateDownloadingProgress(downloadFile);
                timeCount++;
            }

            output.write(data, 0, count);
        }
        onDownloadComplete();
        output.flush();
        output.close();
        bis.close();

    }

    /**
     * Invoke to send update to ui or send notification
     *
     * @param downloadFile
     */
    private void updateDownloadingProgress(DownloadFile downloadFile) {
        if (isAppOnForeground(this)) {
            notificationManager.cancelAll();
            sendIntent(downloadFile);
        } else {
            notificationBuilder.setProgress(100, downloadFile.getProgress(), false);
            notificationBuilder.setContentText("Downloading file " + downloadFile.getCurrentFileSize() + "/" + totalFileSize + " MB");
            notificationManager.notify(0, notificationBuilder.build());
        }

    }

    /**
     * Send intent for local broadcast receiver
     *
     * @param downloadFile
     */
    private void sendIntent(DownloadFile downloadFile) {
        Intent intent = new Intent(DownloadFileFragment.MESSAGE_PROGRESS);
        intent.putExtra(Constants.DOWNLOAD_FILE, downloadFile);
        LocalBroadcastManager.getInstance(DownloadService.this).sendBroadcast(intent);
    }

    /**
     * Reset notification after complete the download
     */
    private void onDownloadComplete() {

        DownloadFile downloadFile = new DownloadFile();
        downloadFile.setProgress(100);
        sendIntent(downloadFile);

        notificationManager.cancel(0);
        notificationBuilder.setProgress(0, 0, true);
        notificationBuilder.setContentText("File Downloaded");
        notificationManager.notify(0, notificationBuilder.build());

    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        notificationManager.cancel(0);
    }

    /**
     * Check app is background or not
     *
     * @param context
     * @return
     */
    private boolean isAppOnForeground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null) {
            return false;
        }
        final String packageName = context.getPackageName();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND && appProcess.processName.equals(packageName)) {
                return true;
            }
        }
        return false;
    }

}