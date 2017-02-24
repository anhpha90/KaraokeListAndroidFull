package vn.com.pqs.simplekaraokelist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.viethoa.RecyclerViewFastScroller;

import java.util.ArrayList;

import vn.com.pqs.adapter.RecyclerViewAdapter;
import vn.com.pqs.model.BaiHat;

public class DanhSach extends Fragment{

    MaterialSearchView searchViewds;
        public DanhSach() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
           }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_item, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchViewds.setMenuItem(item);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

         final View view =  inflater.inflate(R.layout.danhsach, container, false);
        searchViewds = (MaterialSearchView) getActivity().findViewById(R.id.search_view);


        //ListView lvdanhsach = (ListView) view.findViewById(R.id.lvdanhsach);
        final MainActivity main1 = (MainActivity) getActivity();
        main1.recyclerViewAdapter = new RecyclerViewAdapter(main1.dsBaihat, (MainActivity) getActivity(),getContext());
        main1.recyclerViewAdapterCa = new RecyclerViewAdapter(main1.dsBaihatCa, (MainActivity) getActivity(),getContext());
        main1.recyclerViewAdapterMc = new RecyclerViewAdapter(main1.dsBaihatMc, (MainActivity) getActivity(),getContext());
        main1.rvTimKiem = new RecyclerViewAdapter(main1.dsBaiHatTimKiem, (MainActivity) getActivity(),getContext());
        final RecyclerView rv = (RecyclerView) view.findViewById(R.id.my_recycler_viewds);
        final RecyclerViewFastScroller fastScroller = (RecyclerViewFastScroller) view.findViewById(R.id.fast_scroller);
       // main1.DsAdapter = new BaiHatAdapter(getActivity(),R.layout.items,main1.dsBaihat);
       // main1.DsAdapter.notifyDataSetChanged();
        fastScroller.setRecyclerView(rv);
        fastScroller.setUpAlphabet(main1.mAlphabetItems);
        //lvdanhsach.setAdapter(main1.DsAdapter);
        main1.recyclerViewAdapter.notifyDataSetChanged();
        main1.recyclerViewAdapterCa.notifyDataSetChanged();
        main1.recyclerViewAdapterMc.notifyDataSetChanged();
        if(main1.listKa=="A"){
        rv.setAdapter(main1.recyclerViewAdapter);}
        if(main1.listKa=="C"){
            rv.setAdapter(main1.recyclerViewAdapterCa);
        }
        if(main1.listKa=="M"){
            rv.setAdapter(main1.recyclerViewAdapterMc);
        }
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);



        searchViewds.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {

                // main1.DsAdapter = new BaiHatAdapter(getActivity(),R.layout.items,main1.dsBaihat);
                // main1.DsAdapter.notifyDataSetChanged();
                fastScroller.setRecyclerView(rv);
                fastScroller.setUpAlphabet(main1.mAlphabetItems);
                //lvdanhsach.setAdapter(main1.DsAdapter);
                if(main1.listKa=="A"){
                    main1.recyclerViewAdapter.notifyDataSetChanged();
                    rv.setAdapter(main1.recyclerViewAdapter);}
                if(main1.listKa=="C"){

                    main1.recyclerViewAdapterCa.notifyDataSetChanged();
                    rv.setAdapter(main1.recyclerViewAdapterCa);
                }
                if(main1.listKa=="M"){
                    main1.recyclerViewAdapterMc.notifyDataSetChanged();
                    rv.setAdapter(main1.recyclerViewAdapterMc);
                }


                LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                rv.setLayoutManager(llm);

            }
        });
        searchViewds.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText != null && !newText.isEmpty()) {
                    main1.dsBaiHatTimKiem = new ArrayList<>();
                    newText = newText.toLowerCase();
                    newText = main1.unAccent(newText);
                    if(main1.listKa=="A"){
                    for (BaiHat objects : main1.dsBaihat) {
                        if(objects.getTenviettat().toLowerCase().contains(newText)){
                            main1.dsBaiHatTimKiem.add(0,objects);
                        }
                        if (objects.getTenBhNA().toLowerCase().contains(newText)) {
                            main1.dsBaiHatTimKiem.add(0,objects);
                        }
                        if(objects.getTxtLrNA().toLowerCase().contains(newText)){
                            main1.dsBaiHatTimKiem.add(objects);
                        }
                    }}
                    if(main1.listKa=="C"){
                        for (BaiHat objects : main1.dsBaihatCa) {
                            if(objects.getTenviettat().toLowerCase().contains(newText)){
                                main1.dsBaiHatTimKiem.add(0,objects);
                            }
                            if (objects.getTenBhNA().toLowerCase().contains(newText)) {
                                main1.dsBaiHatTimKiem.add(0,objects);
                            }
                            if(objects.getTxtLrNA().toLowerCase().contains(newText)){
                                main1.dsBaiHatTimKiem.add(objects);
                            }
                        }}
                    if(main1.listKa=="M"){
                        for (BaiHat objects : main1.dsBaihatMc) {
                            if(objects.getTenviettat().toLowerCase().contains(newText)){
                                main1.dsBaiHatTimKiem.add(0,objects);
                            }
                            if (objects.getTenBhNA().toLowerCase().contains(newText)) {
                                main1.dsBaiHatTimKiem.add(0,objects);
                            }
                            if(objects.getTxtLrNA().toLowerCase().contains(newText)){
                                main1.dsBaiHatTimKiem.add(objects);
                            }
                        }}

                    main1.rvTimKiem = new RecyclerViewAdapter(main1.dsBaiHatTimKiem, (MainActivity) getActivity(),null);
                    rv.setAdapter(main1.rvTimKiem);
                    LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                    rv.setLayoutManager(llm);

                } else {
                    fastScroller.setRecyclerView(rv);
                    fastScroller.setUpAlphabet(main1.mAlphabetItems);
                    //lvdanhsach.setAdapter(main1.DsAdapter);
                    if(main1.listKa=="A"){
                        main1.recyclerViewAdapter.notifyDataSetChanged();
                        rv.setAdapter(main1.recyclerViewAdapter);}
                    if(main1.listKa=="C"){

                        main1.recyclerViewAdapterCa.notifyDataSetChanged();
                        rv.setAdapter(main1.recyclerViewAdapterCa);
                    }
                    if(main1.listKa=="M"){
                        main1.recyclerViewAdapterMc.notifyDataSetChanged();
                        rv.setAdapter(main1.recyclerViewAdapterMc);
                    }
                    LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                    rv.setLayoutManager(llm);
                }

                return true;
            }
        });


        return view;
    }

}

