package vn.com.pqs.simplekaraokelist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

import vn.com.pqs.adapter.AddressAdapter;
import vn.com.pqs.model.Address;


public class Diadiem extends Fragment{
    AddressAdapter rvTimKiemad;
    RecyclerView rvx;
    MainActivity main2;

    public Diadiem() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        main2 = (MainActivity) getActivity();
               if (isVisibleToUser) {
              main2.searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
                @Override
                public void onSearchViewShown() {
                    main2.diaDiemAdapter.notifyDataSetChanged();
                    rvx.setAdapter(main2.diaDiemAdapter);
                    LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                    rvx.setLayoutManager(llm);

                }

                @Override
                public void onSearchViewClosed() {
                    main2.diaDiemAdapter.notifyDataSetChanged();
                    rvx.setAdapter(main2.diaDiemAdapter);
                    LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                    rvx.setLayoutManager(llm);

                }
            });
            main2.searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    if (newText != null && !newText.isEmpty()) {
                        main2.dsDiadiemTimkiem = new ArrayList<>();
                        newText = newText.toLowerCase();
                        newText = main2.unAccent(newText);
                        for (Address objects : main2.dsDiadiem) {
                            String fullname = objects.getAdna();
                            String name = objects.getNamena();

                            if(fullname.contains(newText)){
                                main2.dsDiadiemTimkiem.add(objects);
                            }
                            if(name.contains(newText)){
                                main2.dsDiadiemTimkiem.add(objects);
                            }

                        }

                        rvTimKiemad = new AddressAdapter(main2.dsDiadiemTimkiem, (MainActivity) getActivity());
                        rvTimKiemad.notifyDataSetChanged();
                        rvx.setAdapter(rvTimKiemad);
                        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                        rvx.setLayoutManager(llm);

                    } else {

                        //lvdanhsach.setAdapter(main1.DsAdapter);
                        main2.diaDiemAdapter.notifyDataSetChanged();
                        rvx.setAdapter(main2.diaDiemAdapter);
                        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                        rvx.setLayoutManager(llm);
                    }

                    return true;
                }
            });

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment0
        main2 = (MainActivity) getActivity();
        View view =  inflater.inflate(R.layout.diadiem, container, false);
        main2.diaDiemAdapter = new AddressAdapter(main2.dsDiadiem, (MainActivity) getActivity());
        rvx = (RecyclerView) view.findViewById(R.id.my_recycler_viewdd);
        main2.diaDiemAdapter.notifyDataSetChanged();
        rvx.setAdapter(main2.diaDiemAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rvx.setLayoutManager(llm);
        return view;
    }


}