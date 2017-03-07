package com.example.administrator.reparihelper_spacpe.fragement;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.reparihelper_spacpe.R;

/**
 * Created by Administrator on 2017/1/8.
 */

public class VideoFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.video_frament, null);
        RecyclerView recycleView = (RecyclerView) view.findViewById(R.id.recycle);

        GridLayoutManager linearLayoutManager = new GridLayoutManager(getActivity(),3);
        linearLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recycleView.setLayoutManager(linearLayoutManager);

        recycleView.setAdapter(new MyAdapter());
        return view;
    }


    class MyAdapter extends RecyclerView.Adapter<MyAdapter.Viewhoulder>{

        @Override
        public Viewhoulder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(getActivity(), R.layout.video_item, null);
            return new Viewhoulder(view);
        }

        @Override
        public void onBindViewHolder(Viewhoulder holder, int position) {
            holder.setData();
        }

        @Override
        public int getItemCount() {
            return 20;
        }

        //加载数据的
        class Viewhoulder extends  RecyclerView.ViewHolder{

            private final ImageView img;

            public Viewhoulder(View itemView) {
                super(itemView);

                 img =  (ImageView) itemView.findViewById(R.id.img);
            }
            public void setData(){
                img.setBackgroundResource(R.drawable.two);
            }
        }
    }
}
