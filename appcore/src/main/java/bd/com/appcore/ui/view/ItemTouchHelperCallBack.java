package bd.com.appcore.ui.view;

import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.Collections;
import java.util.List;

import bd.com.appcore.ui.adapter.CommonAdapter;
import bd.com.appcore.ui.adapter.MultiItemTypeAdapter;


public class ItemTouchHelperCallBack<T> extends ItemTouchHelper.Callback {

private final static String TAG=ItemTouchHelperCallBack.class.getSimpleName();
    private XRecyclerView mRecyclerVIew;
    private List<T> datas;
    private MultiItemTypeAdapter<T> adapter;

    public ItemTouchHelperCallBack(XRecyclerView mRecyclerVIew, List<T> datas, MultiItemTypeAdapter<T> adapter) {
        this.mRecyclerVIew = mRecyclerVIew;
        this.datas = datas;
        this.adapter = adapter;
    }

    /**
     *   RecyclerViewUPDOWN，RecyclerViewLEFTRIGHT
     * @param recyclerView
     * @param viewHolder
     * @return
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        Log.e(TAG,"getMovementFlags");
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN |
                    ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            final int swipeFlags = 0;
            return makeMovementFlags(dragFlags, swipeFlags);
        } else {
            final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            final int swipeFlags = 0;
//                    final int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
            return makeMovementFlags(dragFlags, swipeFlags);
        }
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        Log.e(TAG,"onMove");
        if(datas.size()<=1){
            return false;
        }
        //viewHolderPosition
        int fromPosition = viewHolder.getAdapterPosition();

        //itemviewHolder
        int toPosition = target.getAdapterPosition();

        int headerCount=mRecyclerVIew.getHeaders_includingRefreshCount();
        Log.e("BaseList","fromPosition="+fromPosition+"   toPosition="+toPosition);
        int fromIndex=fromPosition-headerCount;
        int toIndex=toPosition-headerCount;
        if(fromIndex<0||toIndex<0){
            return false;
        }
        if (fromIndex < toIndex) {
            if(toIndex>=datas.size())return false;
            for (int i = fromIndex; i < toIndex; i++) {
                Collections.swap(datas, i, i + 1);
            }
        } else {
            if(fromIndex>=datas.size())return false;
            for (int i = fromIndex; i > toIndex; i--) {
                Collections.swap(datas, i, i - 1);
            }
        }
        adapter.notifyItemMoved(fromPosition, toPosition);
        return true;
    }
   
}
