package ice.mobarak.test.ui.test02;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import ice.mobarak.test.R;
import ice.mobarak.test.data.model.User;
import ice.mobarak.test.data.network.ApiUrls;
import ice.mobarak.test.utils.Constants;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserListViewHolder> {

    private Context context;
    private List<User> users;

    public UserListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public UserListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_item, parent, false);
        return new UserListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserListViewHolder holder, int position) {
        User user = users.get(position);
        holder.tvName.setText(user.getFullName() + "");
        holder.tvMobile.setText(user.getMobile() + "");

        //Set url
        String url;
        if (user.isMale()) {
            url = ApiUrls.MEN_PHOTO_URL + user.getPhotoId() + Constants.DOT_JPG;
        } else {
            url = ApiUrls.WOMEN_PHOTO_URL + user.getPhotoId() + Constants.DOT_JPG;
        }

        //Load profile image
        Glide.with(context).load(url).thumbnail(0.1f).into(holder.ivProfilePic);
    }

    @Override
    public int getItemCount() {
        return users == null ? 0 : users.size();
    }

    /**
     * Invoke to add collection of users
     *
     * @param list
     */
    public void addUsers(List<User> list) {
        if (list == null || list.size() <= 0) {
            return;
        }

        if (users == null) {
            users = list;
        } else {
            users.addAll(list);
        }

        notifyDataSetChanged();
    }

    /**
     * Invoke to add a user
     *
     * @param user
     */
    public void addUser(User user) {
        if (user == null) {
            return;
        }

        if (users == null) {
            users = new ArrayList<>();
        }

        users.add(user);
        notifyDataSetChanged();
    }

    /**
     * View holder class
     */
    protected class UserListViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProfilePic;
        TextView tvName;
        TextView tvMobile;

        public UserListViewHolder(View itemView) {
            super(itemView);
            ivProfilePic = itemView.findViewById(R.id.iv_profile_pic);
            tvName = itemView.findViewById(R.id.tv_name);
            tvMobile = itemView.findViewById(R.id.tv_mobile_no);
        }
    }
}
