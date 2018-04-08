package ice.mobarak.test.ui.test01;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ice.mobarak.test.R;
import ice.mobarak.test.ui.base.BaseFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class DownloadFileFragment extends BaseFragment {


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

    @Override
    protected void initializedView(View view) {

    }

    @Override
    protected void setClickListener() {

    }
}

