package ice.mobarak.test.ui.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;


/**
 * Created by Mobarak on 22-Sep-17.
 */

public class BaseFragment extends Fragment {

    public BaseFragment() {

    }


    /**
     * Invoke to back in previous fragment
     *
     * @return true|false
     */
    public boolean popFragment() {
        boolean isPop = false;
        if (getActivity() != null) {
            Log.e("getBackStackEntryCount ", "getBackStackEntryCount: "
                    + getActivity().getSupportFragmentManager().getBackStackEntryCount());
            if (getActivity().getSupportFragmentManager().getBackStackEntryCount() > 0) {
                isPop = true;
                getActivity().getSupportFragmentManager().popBackStack();
            }
        }
        return isPop;
    }


    /**
     * Invoke to back parent fragment
     */
    public void popBackToFragment() {
        if (getActivity() != null) {
            FragmentManager fm = getActivity().getSupportFragmentManager();
            int count = fm.getBackStackEntryCount();
            Log.e("getBackStackEntryCount ", "getBackStackEntryCount: " + count);
            for (int i = 0; i < count; ++i) {
                fm.popBackStack();
            }
        }
    }

    /**
     * Invoke to controlling visibility of BackArrow (Toolbar)
     * If @isShow is true BackArrow is visible
     * Else BackArrow is Gone
     *
     * @param isShow true| false
     */
    protected void setBackArrowVisibility(boolean isShow) {
        if (getActivity() == null) {
            return;
        }

        ((MainActivity) getActivity()).setBackArrowVisibility(isShow);

    }

    /**
     * Invoke to controlling visibility of BackArrow (Toolbar)
     * If @isShow is true BackArrow is visible
     * Else BackArrow is Gone
     *
     * @param title String
     */
    protected void setTitleText(String title) {
        if (getActivity() == null || title == null) {
            return;
        }
        ((MainActivity) getActivity()).setTitleText(title);
    }

    protected void initializedView(View view){}
    protected void setClickListener(){}
}
