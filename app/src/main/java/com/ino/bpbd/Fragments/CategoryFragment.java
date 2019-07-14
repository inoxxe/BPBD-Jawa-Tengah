package com.ino.bpbd.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ino.bpbd.Adapter.CategoryAdapter;
import com.ino.bpbd.Model.Category;
import com.ino.bpbd.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {

    private List<Category> lstCategory;
    private RecyclerView recyclerView;
    View view;


    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_category, container, false);

        lstCategory = new ArrayList<>();

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_category);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        int[] arr={R.drawable.tanah_longsor_icon,
                R.drawable.kekeringan_icon,
                R.drawable.gempa_bumi_icon,
                R.drawable.tsunami_icon,
                R.drawable.kebakaran_icon,
                R.drawable.puting_beliung_icon
        };

        lstCategory.add(new Category(
                "Longsor",arr[0]

        ));
        lstCategory.add(new Category(
                "Kekeringan",arr[1]

        ));
        lstCategory.add(new Category(
                "Gempa",arr[2]

        ));
        lstCategory.add(new Category(
                "Banjir",arr[3]

        ));
        lstCategory.add(new Category(
                "Kebakaran",arr[4]

        ));
        lstCategory.add(new Category(
                "Puting Beliung",arr[5]
        ));

        CategoryAdapter adapter = new CategoryAdapter(getActivity(),lstCategory);
        recyclerView.setAdapter(adapter);
        return view;
    }

}
