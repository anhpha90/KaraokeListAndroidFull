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

import java.util.ArrayList;

import vn.com.pqs.adapter.DiaDiemAdapter;
import vn.com.pqs.model.BaiHat;


public class Diadiem extends Fragment{
    DiaDiemAdapter rvTimKiemad;
    MaterialSearchView searchViewdd;
    public Diadiem() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_item, menu);
        MenuItem itemdd = menu.findItem(R.id.action_search);
        searchViewdd.setMenuItem(itemdd);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.diadiem, container, false);
        searchViewdd = (MaterialSearchView) getActivity().findViewById(R.id.search_view);
        final MainActivity main1 = (MainActivity) getActivity();
        main1.diaDiemAdapter = new DiaDiemAdapter(main1.dsDiadiem, (MainActivity) getActivity());
        final RecyclerView rvx = (RecyclerView) view.findViewById(R.id.my_recycler_viewdd);
        main1.diaDiemAdapter.notifyDataSetChanged();
        rvx.setAdapter(main1.diaDiemAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rvx.setLayoutManager(llm);

       searchViewdd.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                main1.diaDiemAdapter.notifyDataSetChanged();
                rvx.setAdapter(main1.diaDiemAdapter);
                LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                rvx.setLayoutManager(llm);

            }

            @Override
            public void onSearchViewClosed() {
                main1.diaDiemAdapter.notifyDataSetChanged();
                rvx.setAdapter(main1.diaDiemAdapter);
                LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                rvx.setLayoutManager(llm);

            }
        });
        searchViewdd.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText != null && !newText.isEmpty()) {
                    main1.dsDiadiemTimkiem = new ArrayList<>();
                    newText = newText.toLowerCase();
                    newText = main1.unAccent(newText);
                    for (BaiHat objects : main1.dsDiadiem) {
                        String fullname = objects.getTxtcs().toString().toLowerCase();
                        fullname = main1.unAccent(fullname);

                        if(fullname.contains(newText)){
                            main1.dsDiadiemTimkiem.add(objects);
                        }

                    }

                    rvTimKiemad = new DiaDiemAdapter(main1.dsDiadiemTimkiem, (MainActivity) getActivity());
                    rvTimKiemad.notifyDataSetChanged();
                    rvx.setAdapter(rvTimKiemad);
                    LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                    rvx.setLayoutManager(llm);

                } else {

                    //lvdanhsach.setAdapter(main1.DsAdapter);
                    main1.diaDiemAdapter.notifyDataSetChanged();
                    rvx.setAdapter(main1.diaDiemAdapter);
                    LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                    rvx.setLayoutManager(llm);
                }

                return true;
            }
        });


        return view;
    }


}