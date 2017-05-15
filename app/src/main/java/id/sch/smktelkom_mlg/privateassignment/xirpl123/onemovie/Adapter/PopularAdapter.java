package id.sch.smktelkom_mlg.privateassignment.xirpl123.onemovie.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl123.onemovie.Model.Popular;
import id.sch.smktelkom_mlg.privateassignment.xirpl123.onemovie.R;

/**
 * Created by NA on 5/13/2017.
 */

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder>{
    ArrayList<Popular> list;
    PopularAdapter.IPopularAdapter mIPopularAdapter;
    Context context;

    public static  final String IMAGE_URL_BASE_PATH="https://image.tmdb.org/t/p/w500";
    public PopularAdapter(Context context, ArrayList<Popular> list)
    {
        this.list = list;
        this.context = context;
        mIPopularAdapter = (PopularAdapter.IPopularAdapter) context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Popular popular = list.get(position);
        holder.tvTitle.setText(popular.title);
        holder.tvDesc.setText(popular.overview);
        holder.tvRelease.setText(popular.release_date);
        holder.tvRating.setText(popular.vote_average);
        Glide.with(context)
                .load(IMAGE_URL_BASE_PATH + popular.poster_path).
                into(holder.ivPoster);
    }

    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
        return 0;
    }

    public interface IPopularAdapter {
        void showDetail(String poster_path, String overview, String release_date, String title, String backdrop_path, String vote_average, String original_language, String popularity, String vote_count );
    }

    public class  ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView ivPoster;
        TextView tvTitle;
        TextView tvDesc;
        TextView tvRelease;
        TextView tvRating;
        TextView tvPopularity;
        TextView tvVote;
        TextView tvLanguage;

        public ViewHolder(View itemView) {
            super(itemView);
            ivPoster = (ImageView) itemView.findViewById(R.id.imageViewPoster);
            tvTitle = (TextView) itemView.findViewById(R.id.textViewTitle);
            tvDesc = (TextView) itemView.findViewById(R.id.textViewOverview);
            tvRelease = (TextView) itemView.findViewById(R.id.textViewDate);
            tvRating = (TextView) itemView.findViewById(R.id.textViewRating);
            tvPopularity = (TextView) itemView.findViewById(R.id.VoteAverage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Popular popular = list.get(getAdapterPosition());
                    mIPopularAdapter.showDetail(popular.poster_path, popular.overview, popular.release_date, popular.title, popular.backdrop_path, popular.vote_average, popular.original_language, popular.popularity,popular.vote_count);
                }
            });
        }
    }

}
