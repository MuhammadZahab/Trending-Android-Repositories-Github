package remote.codingsample.trendingandroidrepos.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import remote.codingsample.trendingandroidrepos.Models.Item;
import remote.codingsample.trendingandroidrepos.R;

public class ReposListAdapter extends RecyclerView.Adapter<ReposListAdapter.ViewHolder> {

    /*Custom Adapter Class to show populate cusotm data in ListItem.*/

    private List<Item> mItems;
    private Context mContext;
    private ReposItemListener mItemListener;

    public ReposListAdapter()
    {

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView owner_avatar;
        public TextView tv_repo_name;
        public TextView tv_repo_full_name;
        public TextView tv_repo_watcher;
        public TextView tv_language;

        ReposItemListener mItemListener;

        public ViewHolder(View itemView, ReposItemListener postItemListener) {
            super(itemView);

            initializeViews();
            this.mItemListener = postItemListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Item item = getItem(getAdapterPosition());
            this.mItemListener.onRepoClick(item);

            notifyDataSetChanged();
        }

        private void initializeViews(){
            tv_repo_name       = (TextView) itemView.findViewById(R.id.tv_repo_name);
            tv_repo_full_name  = (TextView) itemView.findViewById(R.id.tv_repo_full_name);
            tv_repo_watcher    = (TextView) itemView.findViewById(R.id.tv_watchers);
            tv_language        = (TextView) itemView.findViewById(R.id.tv_language);
            owner_avatar       = (ImageView) itemView.findViewById(R.id.owner_avatar);


        }


    }

    public ReposListAdapter(Context context, List<Item> posts, ReposItemListener itemListener) {
        mItems = posts;
        mContext = context;
        mItemListener = itemListener;
    }

    @Override
    public ReposListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View postView = inflater.inflate(R.layout.repos_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(postView, this.mItemListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ReposListAdapter.ViewHolder holder, int position) {

        Item item = mItems.get(position);

        Picasso.get().load(item.getOwner().getAvatarUrl()).into(holder.owner_avatar);
        holder.tv_repo_name.setText(item.getName());
        holder.tv_repo_full_name.setText(item.getFullName());
        holder.tv_repo_watcher.setText(item.getWatchers()+"");
        holder.tv_language.setText(item.getLanguage());

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void updateReposList(List<Item> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    private Item getItem(int adapterPosition) {
        return mItems.get(adapterPosition);
    }

    /*Interface to perform Click on Repo List Item*/
    public interface ReposItemListener {
        void onRepoClick(Item item);
    }
}