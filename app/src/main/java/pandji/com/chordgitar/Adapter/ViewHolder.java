package pandji.com.chordgitar.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import pandji.com.chordgitar.R;

public class ViewHolder extends RecyclerView.ViewHolder{

    View mView;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        mView = itemView;

        //item click
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListerner.onItemClick(view, getAdapterPosition());
            }
        });

        //item longclick

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mClickListerner.onItemLongClick(view, getAdapterPosition());
                return true;
            }
        });
    }

    public void setDetails (Context ctx, String Name, String Deskripsi ,String Image){
        TextView mNameTv = mView.findViewById(R.id.rNameTv);
        TextView mDetailTv = mView.findViewById(R.id.rDeskripsiTv);
        ImageView mImageIv = mView.findViewById(R.id.rImageView);

        mNameTv.setText(Name);
        mDetailTv.setText(Deskripsi);
        Picasso.get().load(Image).into(mImageIv);
    }

    private ViewHolder.ClickListener mClickListerner;

    public interface ClickListener{
        void onItemClick (View view, int position);
        void onItemLongClick (View view , int position);
    }

    public void setOnClickListener(ViewHolder.ClickListener clickListener){
        mClickListerner = clickListener;
    }
}
