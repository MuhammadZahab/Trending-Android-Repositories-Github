package remote.codingsample.trendingandroidrepos.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

import remote.codingsample.trendingandroidrepos.Fragments.ReposListFragment;
import remote.codingsample.trendingandroidrepos.R;

public class ReposListActivity extends BaseActivity {

    /* RepoListActivity to show list of Android Repositories. I just attached a fragment here by a generic initFragment method
       created in BaseActivity which will be easily accessible for all child classes to make code REUSEABLE.
   */

    private ReposListFragment reposListFragment;
    private ViewGroup fragmentContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos_list);


        fragmentContainer        = findViewById(R.id.fragment_container);
        reposListFragment        = initFragment(R.id.fragment_container, new ReposListFragment());


    }
}
