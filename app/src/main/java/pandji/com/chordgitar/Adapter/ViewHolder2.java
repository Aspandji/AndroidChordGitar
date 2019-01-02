package pandji.com.chordgitar.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import pandji.com.chordgitar.R;

public class ViewHolder2 extends RecyclerView.ViewHolder {

    View mView;

    public ViewHolder2(@NonNull View itemView) {
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

    public void setDetails (Context ctx, String Judul, String Chord , String Imageabm){
        TextView mJudulTv = mView.findViewById(R.id.rJudulTv);
        TextView mChordTv = mView.findViewById(R.id.rChordTv);
        ImageView mImageabmIv = mView.findViewById(R.id.rImageViewAbm);

        mJudulTv.setText(Judul);
        mChordTv.setText(Chord);
        Picasso.get().load(Imageabm).into(mImageabmIv);
    }

    private ViewHolder2.ClickListener mClickListerner;

    public interface ClickListener{
        void onItemClick (View view, int position);
        void onItemLongClick (View view , int position);
    }

    public void setOnClickListener(ViewHolder2.ClickListener clickListener){
        mClickListerner = clickListener;
    }
}
