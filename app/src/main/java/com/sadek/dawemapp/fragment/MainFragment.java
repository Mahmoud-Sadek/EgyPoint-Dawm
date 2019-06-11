package com.sadek.dawemapp.fragment;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sadek.dawemapp.R;
import com.sadek.dawemapp.activity.MainActivity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import me.khrystal.library.widget.CircleRecyclerView;
import me.khrystal.library.widget.CircularHorizontalMode;
import me.khrystal.library.widget.ItemViewMode;

import static com.facebook.FacebookSdk.getApplicationContext;


public class MainFragment extends Fragment {

    private CircleRecyclerView mCircleRecyclerView;
    private ItemViewMode mItemViewMode;
    private LinearLayoutManager mLayoutManager;
    private List<Integer> mImgList;
    private boolean mIsNotLoop;

    ImageView mDropZone;
    int _yDelta;
    private Integer[] mImgs = {
            R.drawable.c1, R.drawable.c2, R.drawable.c3, R.drawable.c4,
            R.drawable.c5, R.drawable.c5, R.drawable.c6
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mCircleRecyclerView = (CircleRecyclerView) view.findViewById(R.id.circle_rv);

        // notice when use horizontal layoutManager and item in recyclerView bottom
        // the item xml file is not useful set marginTop and set alginParentBottom,
        // u can use the first argument to control the item position of the recyclerView
        // and this offset need use some density util to resolve adaptation
        // and the offset is relative recyclerView top abs distance
//                mItemViewMode = new CircularHorizontalBTTMode(-200, true);
//                mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true);

        mItemViewMode = new CircularHorizontalMode();
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);


        mCircleRecyclerView.setLayoutManager(mLayoutManager);
        mCircleRecyclerView.setViewMode(mItemViewMode);
        mCircleRecyclerView.setNeedCenterForce(true);
        mCircleRecyclerView.setNeedLoop(!mIsNotLoop);


        mImgList = Arrays.asList(mImgs);
        Collections.shuffle(mImgList);

        mCircleRecyclerView.setAdapter(new A());

        mDropZone = view.findViewById(R.id.dropZone);

    }

    class A extends RecyclerView.Adapter<VH> {

        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            VH h = null;

            h = new VH(LayoutInflater.from(getContext())
                    .inflate(R.layout.item_category, parent, false));

            return h;
        }

        private static final String IMAGEVIEW_TAG = "icon bitmap";

        @Override
        public void onBindViewHolder(final VH holder, final int position) {
//            holder.tv.setText("Number :" + (position % mImgList.size()));
//            Glide.with(getContext())
//                    .load(mImgList.get(position % mImgList.size()))
//                    .bitmapTransform(new CropCircleTransformation(getContext()))
//                    .into(holder.iv);
            /*holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), " Clicked" + (position % mImgList.size()), Toast.LENGTH_SHORT).show();
                }
            });*/

            holder.iv.setImageResource(mImgList.get(position % mImgList.size()));
            // Sets the tag
            holder.iv.setTag(IMAGEVIEW_TAG);


// Sets a long click listener for the ImageView using an anonymous listener object that
// implements the OnLongClickListener interface
            holder.iv.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {


                    ClipData.Item item = new ClipData.Item(IMAGEVIEW_TAG);
                    ClipData dragData = new ClipData(
                            IMAGEVIEW_TAG,
                            new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN},
                            item);

                    // Instantiates the drag shadow builder.
                    View.DragShadowBuilder myShadow = new View.DragShadowBuilder(holder.iv);

                    // Starts the drag

                    v.startDrag(dragData,  // the data to be dragged
                            myShadow,  // the drag shadow builder
                            null,      // no need to use local data
                            0          // flags (not currently used, set to 0)
                    );
                    return true;
                }
            });

            mDropZone.setOnDragListener(new View.OnDragListener() {
                @Override
                public boolean onDrag(View v, DragEvent event) {
                    switch (event.getAction()) {
                        case DragEvent.ACTION_DRAG_STARTED:
                            mDropZone.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.dark_gray)));
//                            ViewGroup.MarginLayoutParams lParams = (ViewGroup.MarginLayoutParams) holder.iv.getLayoutParams();
//                            _yDelta = (int) event.getY() - lParams.topMargin;
//                            Toast.makeText(getContext(), "ACTION_DRAG_STARTED", Toast.LENGTH_SHORT).show();
                            break;
                        case DragEvent.ACTION_DRAG_ENDED:
                            mDropZone.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.white)));
//                            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) holder.iv.getLayoutParams();
//                            layoutParams.topMargin = (int) event.getY() - _yDelta;
//                            holder.iv.setLayoutParams(layoutParams);
//                            Toast.makeText(getContext(), "ACTION_DRAG_LOCATION", Toast.LENGTH_SHORT).show();
                            break;
                        case DragEvent.ACTION_DRAG_EXITED:
//                            mDropZone.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.white)));
                            break;
                        case DragEvent.ACTION_DROP:
                            mDropZone.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));
                            // Dropped, reassign View to ViewGroup
//                            View view = (View) event.getLocalState();
//                            Toast.makeText(getContext(), "ACTION_DROP", Toast.LENGTH_SHORT).show();
                            ((MainActivity)getContext()).switchToPage(2,null,R.string.app_name);
                            // Do your drop actions here

//                            view.setVisibility(View.VISIBLE);
                            break;
                        default:
//                            Toast.makeText(getContext(), "default", Toast.LENGTH_SHORT).show();
                            mDropZone.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.white)));
                            break;
                    }
                    return true;
                }
            });
        }

        @Override
        public int getItemCount() {
            return mIsNotLoop ? mImgList.size() : Integer.MAX_VALUE;
        }
    }


    class VH extends RecyclerView.ViewHolder {

        ImageView iv;

        public VH(View itemView) {
            super(itemView);

            iv = (ImageView) itemView.findViewById(R.id.item_img);
        }
    }
}
