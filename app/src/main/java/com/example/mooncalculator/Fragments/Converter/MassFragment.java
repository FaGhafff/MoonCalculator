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
 * Use the {@link MassFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MassFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MassFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MassFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MassFragment newInstance(String param1, String param2) {
        MassFragment fragment = new MassFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private Spinner spinnerFrom;
    private Spinner spinnerTo;
    private EditText editTextFrom;
    private EditText editTextTo;
    private Core.Mass from = null;
    private Core.Mass to = null;
    private Core core;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mass, container, false);

        //init
        spinnerFrom = view.findViewById(R.id.spinnerFrom);
        spinnerTo = view.findViewById(R.id.spinnerTo);
        editTextFrom = view.findViewById(R.id.editTextFrom);
        editTextTo = view.findViewById(R.id.editTextTo);
        core = new Core();

        //editTextFrom
        editTextFrom.addTextChangedListener(textWatcherFrom);


        //editTextTo
        editTextTo.addTextChangedListener(textWatcherTo);

        //from
        String[] fromArray = getResources().getStringArray(R.array.Mass);
        ArrayAdapter<String> adapterFrom = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, fromArray);
        adapterFrom.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerFrom.setOnItemSelectedListener(listenerFrom);
        spinnerFrom.setAdapter(adapterFrom);
        String selectedFrom = spinnerFrom.getSelectedItem().toString();
        from = Core.Mass.getEnum(delUnit(selectedFrom));


        //to
        String[] toArray = getResources().getStringArray(R.array.Mass);
        ArrayAdapter<String> adapterTor = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, toArray);
        adapterTor.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerTo.setOnItemSelectedListener(listenerTo);
        spinnerTo.setAdapter(adapterTor);
        String selectedTo = spinnerTo.getSelectedItem().toString();
        to = Core.Mass.getEnum(delUnit(selectedTo));


        // Inflate the layout for this fragment
        return view;
    }

    private final AdapterView.OnItemSelectedListener listenerFrom = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String string = adapterView.getItemAtPosition(i).toString();
            from = Core.Mass.getEnum(delUnit(string));
            System.out.println(string);
            Toast.makeText(getContext(), string + " OIS from", Toast.LENGTH_SHORT).show();
            updateValues(editTextTo, to, editTextFrom, from, false);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    };

    private final AdapterView.OnItemSelectedListener listenerTo = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String string = adapterView.getItemAtPosition(i).toString();
            to = Core.Mass.getEnum(delUnit(string));
            Toast.makeText(getContext(), string + " OIS to", Toast.LENGTH_SHORT).show();
            updateValues(editTextFrom, from, editTextTo, to, false);
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

        }

        @Override
        public void afterTextChanged(Editable editable) {
            updateValues(editTextFrom, from, editTextTo, to, true);

        }
    };

    private final TextWatcher textWatcherTo = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            updateValues(editTextTo, to, editTextFrom, from, true);
        }
    };

    private void updateValues(@NotNull EditText source, Core.Mass from, EditText destination, Core.Mass to, boolean canBeInfinity) {
        if (source.isFocused() || !canBeInfinity) {
            double doubleForm = 0;
            try {
                doubleForm = Double.parseDouble(source.getText().toString());
            } catch (NumberFormatException e) {
                doubleForm = 0;
            }
            String strTo = String.valueOf(core.setInput(doubleForm).from(from).to(to).getOutput());
            destination.setText(strTo);
        }
    }

    @NotNull
    private static String delUnit(String string) {
        string = string.replaceAll(" ", "");
//        string = string.substring(0, string.indexOf('('));
        return string;
    }



}