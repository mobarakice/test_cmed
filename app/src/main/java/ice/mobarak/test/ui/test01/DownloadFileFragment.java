package ice.mobarak.test.ui.test01;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import ice.mobarak.test.R;
import ice.mobarak.test.data.model.DownloadFile;
import ice.mobarak.test.ui.base.BaseFragment;
import ice.mobarak.test.utils.Constants;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;


/**
 * A simple {@link Fragment} subclass.
 */
public class DownloadFileFragment extends BaseFragment {

    private Button btnDownloadFile;
    private ProgressBar progressBar;
    private TextView mProgressText;
    private boolean isPermissionGrant;
    public static final String MESSAGE_PROGRESS = "message_progress";

    public DownloadFileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        setTitleText(getString(R.string.label_text_download_file));
        setBackArrowVisibility(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_download_large_file, container, false);
        initializedView(view);
        setClickListener();
        return view;
    }

    /**
     * Initialized view
     *
     * @param view
     */
    @Override
    protected void initializedView(View view) {
        btnDownloadFile = view.findViewById(R.id.btn_download);
        mProgressText = view.findViewById(R.id.tv_progress_text);
        progressBar = view.findViewById(R.id.progress_bar);
        progressBar.setProgress(0);
        progressBar.setMax(100);
    }

    /**
     * Set click listener
     */
    @Override
    protected void setClickListener() {
        btnDownloadFile.setOnClickListener(v -> {
            if (checkWriteExternalStoragePermission()) {
                startDownload();
            }
        });
    }


    /**
     * Invoke to check write external storage permission.
     * If permission is grant
     *
     * @return
     */
    public boolean checkWriteExternalStoragePermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (ContextCompat.checkSelfPermission(getActivity(), WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE}, Constants.REQUEST_WRITE_EXTERNAL_STORAGE);
        }
        return false;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Constants.REQUEST_WRITE_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                isPermissionGrant = true;
            } else {
                isPermissionGrant = false;
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        registerReceiver();
        if (isPermissionGrant) {
            isPermissionGrant = false;
            startDownload();
        }
    }

    /**
     * Invoke to start download
     */
    private void startDownload() {

        Intent intent = new Intent(getActivity(), DownloadService.class);
        getActivity().startService(intent);

    }

    /**
     * Invoke to register local broadcast receiver
     */
    private void registerReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MESSAGE_PROGRESS);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(broadcastReceiver, intentFilter);

    }

    /**
     * Broadcast Receiver
     */
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equals(MESSAGE_PROGRESS)) {

                DownloadFile downloadFile = intent.getParcelableExtra(Constants.DOWNLOAD_FILE);
                progressBar.setProgress(downloadFile.getProgress());
                if (downloadFile.getProgress() == 100) {
                    mProgressText.setText(R.string.label_text_download_completed);
                } else {
                    mProgressText.setText(String.format(
                            getString(R.string.label_text_downloaded)
                                    + " (%d/%d) "
                                    + getString(R.string.label_text_mb),
                            downloadFile.getCurrentFileSize(),
                            downloadFile.getTotalFileSize())
                    );

                }
            }
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(broadcastReceiver);
    }
}

