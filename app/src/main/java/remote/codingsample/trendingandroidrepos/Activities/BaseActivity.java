package remote.codingsample.trendingandroidrepos.Activities;

import android.os.Bundle;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.Locale;

import remote.codingsample.trendingandroidrepos.R;

public class BaseActivity extends AppCompatActivity {

    /* Purpose to add BaseActivity is to make code generic and Reuseale. Every class needs to extend this BaseAcgtivity
    *  so that most part of code is Reuseable and can easily to modify on changing of requirements.
    * */

    private static final String TAG = BaseActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);


    }

    // initFragment function to access in every child class for Reuseablity of attaching Fragment in Activity

    protected <T extends Fragment> T initFragment(@IdRes int target,
                                                  @NonNull T fragment
                                            )
    {
        return initFragment(target, fragment, null);
    }

    protected <T extends Fragment> T initFragment(@IdRes int target,
                                                  @NonNull T fragment,
                                                  @Nullable Bundle extras)
    {
        Bundle args = new Bundle();

        if (extras != null) {
            args.putAll(extras);
        }

        fragment.setArguments(args);
        getSupportFragmentManager().beginTransaction()
                .replace(target, fragment)
                .commitAllowingStateLoss();

        return fragment;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
