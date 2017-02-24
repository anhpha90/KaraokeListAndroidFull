package vn.com.pqs.simplekaraokelist;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.com.pqs.adapter.RecyclerViewAdapter;

public class YeuThich extends Fragment{
        public YeuThich() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.yeuthich, container, false);
        final MainActivity mainyt = (MainActivity) getActivity();
        mainyt.ytRecle = new RecyclerViewAdapter(mainyt.dsYeuthich, (MainActivity) getActivity(),getContext());
        mainyt.ytRecleCa = new RecyclerViewAdapter(mainyt.dsYeuthichCa, (MainActivity) getActivity(),getContext());
        mainyt.ytRecleMc = new RecyclerViewAdapter(mainyt.dsYeuthichMc, (MainActivity) getActivity(),getContext());

        final RecyclerView rv1 = (RecyclerView) view.findViewById(R.id.my_recycler_viewyt);
      mainyt.ytRecle.notifyDataSetChanged();
        mainyt.ytRecleCa.notifyDataSetChanged();
        mainyt.ytRecleMc.notifyDataSetChanged();
        if(mainyt.listKa=="A"){
        rv1.setAdapter(mainyt.ytRecle);}
        if(mainyt.listKa=="C"){
            rv1.setAdapter(mainyt.ytRecleCa);}
        if(mainyt.listKa=="M"){
            rv1.setAdapter(mainyt.ytRecleMc);}

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv1.setLayoutManager(llm);
        return view;
    }

}