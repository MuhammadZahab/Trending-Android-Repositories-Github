package remote.codingsample.trendingandroidrepos.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import remote.codingsample.trendingandroidrepos.Models.Item;
import remote.codingsample.trendingandroidrepos.R;

public class RepoDetailActivity extends AppCompatActivity {

    private TextView tv_name;
    private TextView tv_full_name;
    private TextView tv_description;
    private TextView tv_fork;
    private TextView tv_uploaded;
    private TextView tv_watchers;
    private TextView tv_language;

    private ImageView owner_avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_detail);

        tv_name          = (TextView)findViewById(R.id.tv_repo_name_details);
        tv_full_name     = (TextView)findViewById(R.id.tv_repo_full_name_details);
        tv_description   = (TextView)findViewById(R.id.tv_repo_description_details);
        tv_fork          = (TextView)findViewById(R.id.tv_repo_forks_details);
        tv_language      = (TextView)findViewById(R.id.tv_repo_language_details);
        tv_watchers      = (TextView)findViewById(R.id.tv_repo_watchers_details);
        tv_uploaded      = (TextView)findViewById(R.id.tv_repo_uploaded_details);
        owner_avatar     = (ImageView)findViewById(R.id.owner_avatar_details_screen);


        Item item = (Item) getIntent().getParcelableExtra("parcel_data");

        setRepoDetails(item);
    }

    private void setRepoDetails(Item item){


     Picasso.get().load(item.getOwner().getAvatarUrl()).into(owner_avatar);
     tv_name.setText("Name              : "+item.getName());
     tv_full_name.setText("Full Name    : "+item.getFullName());
     tv_language.setText("Language      : "+item.getLanguage());
     tv_fork.setText("Forks             : "+item.getForksCount());
     tv_watchers.setText("Watchers      : "+item.getWatchersCount());
     tv_uploaded.setText("Updated at    : "+item.getUpdatedAt());
     tv_description.setText("Description: "+item.getDescription());

    }
}
