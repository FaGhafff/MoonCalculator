package com.example.mooncalculator.Fragments.Converter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.mooncalculator.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccelerationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccelerationFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AccelerationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccelerationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccelerationFragment newInstance(String param1, String param2) {
        AccelerationFragment fragment = new AccelerationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private Spinner spinner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        System.out.println("onCreate baby");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_acceleration, container, false);
        spinner = view.findViewById(R.id.spinner);
        String[] from = getResources().getStringArray(R.array.Time);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, from);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setOnItemSelectedListener(listener);
        spinner.setAdapter(adapter);

        // Inflate the layout for this fragment
        return view;
    }


    AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            System.err.println("fuck of");
            System.out.println(adapterView.getSelectedItemPosition());
            System.out.println(adapterView.getSelectedItemId());
            System.out.println(adapterView.getSelectedItem().toString());
            if (adapterView.getSelectedItemId() == R.id.spinner) {
                System.err.println("item selected");
                System.out.println(adapterView.getItemAtPosition(i).toString());
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            System.out.println("babauabauuba");
        }
    };
}