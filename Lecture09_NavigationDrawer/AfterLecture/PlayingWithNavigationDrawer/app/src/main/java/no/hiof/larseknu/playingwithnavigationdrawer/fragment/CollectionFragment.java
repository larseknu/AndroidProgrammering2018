package no.hiof.larseknu.playingwithnavigationdrawer.fragment;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import no.hiof.larseknu.playingwithnavigationdrawer.R;
import no.hiof.larseknu.playingwithnavigationdrawer.adapter.CollectionRecyclerAdapter;
import no.hiof.larseknu.playingwithnavigationdrawer.model.Movie;


/**
 * A simple {@link Fragment} subclass.
 */
public class CollectionFragment extends Fragment {

    private RecyclerView mRecyclerView;

    public CollectionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collection, container, false);

        setUpRecyclerView(view);

        return view;
    }

    private void setUpRecyclerView(View view) {

        mRecyclerView = (RecyclerView) view.findViewById(R.id.collectionRecyclerView);
        CollectionRecyclerAdapter adapter = new CollectionRecyclerAdapter(getContext(), Movie.getData());
        mRecyclerView.setAdapter(adapter);

        GridLayoutManager mGridLayoutManager = new GridLayoutManager(getContext(), 2); // (Context context, int spanCount)
        mRecyclerView.setLayoutManager(mGridLayoutManager);


        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0)
                    ((FloatingActionButton)getActivity().findViewById(R.id.floatingActionButton)).hide();
                else if (dy < 0)
                    ((FloatingActionButton)getActivity().findViewById(R.id.floatingActionButton)).show();
            }
        });
    }


}
