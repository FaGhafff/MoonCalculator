package com.example.mooncalculator.Fragments.Converter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mooncalculator.R;

import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AreaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AreaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AreaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Area.
     */
    // TODO: Rename and change types and number of parameters
    public static AreaFragment newInstance(String param1, String param2) {
        AreaFragment fragment = new AreaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    /////////////////////////////////START////////////////////////////////

    private Spinner spinnerFrom;
    private Spinner spinnerTo;
    private EditText editTextFrom;
    private EditText editTextTo;
    private Core.Area from = null;
    private Core.Area to = Core.Area.Hectares;
    private Core core;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_area, container, false);

        //init
        spinnerFrom = view.findViewById(R.id.spinnerFrom);
        editTextFrom = view.findViewById(R.id.editTextFrom);
        editTextTo = view.findViewById(R.id.editTextTo);
        core = new Core();

        //from

        String[] fromArray = getResources().getStringArray(R.array.Area);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, fromArray);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerFrom.setOnItemSelectedListener(listenerFrom);
        spinnerFrom.setAdapter(adapter);
        String selected = spinnerFrom.getSelectedItem().toString();
        from = Core.Area.getEnum(delUnit(selected));


        //to

        //editTextFrom

//        editTextFrom.addTextChangedListener(textWatcherFrom);
//
//        //editTextTo
//
//        editTextTo.addTextChangedListener(textWatcherTo);


        // Inflate the layout for this fragment
        return view;
    }

    private final AdapterView.OnItemSelectedListener listenerFrom = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            Toast.makeText(getContext(), adapterView.getSelectedItemId() + "", Toast.LENGTH_SHORT).show();
//            if (adapterView.getSelectedItemId() == R.id.spinnerFrom) {
            String string = adapterView.getItemAtPosition(i).toString();
            from = Core.Area.getEnum(delUnit(string));
            System.out.println(string);
            Toast.makeText(getContext(), string, Toast.LENGTH_SHORT).show();
            onTextChangedFrom();
            //todo set units and

//            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    };


    private final TextWatcher textWatcherFrom = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            onTextChangedFrom();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private final TextWatcher textWatcherTo = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            onTextChangedTo();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    void onTextChangedFrom() {
//        editTextTo.setText(core.setInput(Double.parseDouble(editTextFrom.getText().toString())).from(from).to(to).getOutput() + "");
        
    }

    void onTextChangedTo() {
        editTextFrom.setText(core.setInput(Double.parseDouble(editTextTo.getText().toString())).from(to).to(from).getOutput() + "");
    }

    @NotNull
    private static String delUnit(String string) {
        string = string.replaceAll(" ", "");
        string = string.substring(0, string.indexOf('('));
        return string;
    }

}