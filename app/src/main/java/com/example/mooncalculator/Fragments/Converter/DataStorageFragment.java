package com.example.mooncalculator.Fragments.Converter;

import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.example.mooncalculator.R;

import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DataStorageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DataStorageFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DataStorageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DataStorageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DataStorageFragment newInstance(String param1, String param2) {
        DataStorageFragment fragment = new DataStorageFragment();
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
    private Core.Data from = null;
    private Core.Data to = null;
    private Core core;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_storage, container, false);

        //keyboard
        Button OneBTN = view.findViewById(R.id.OneBtnD);
        OneBTN.setOnClickListener(keyboard);
        Button TwoBTN = view.findViewById(R.id.TwoBtnD);
        TwoBTN.setOnClickListener(keyboard);
        Button ThreeBTN = view.findViewById(R.id.ThreeBtnD);
        ThreeBTN.setOnClickListener(keyboard);
        Button FourBTN = view.findViewById(R.id.FourBtnD);
        FourBTN.setOnClickListener(keyboard);
        Button FiveBTN = view.findViewById(R.id.FiveBtnD);
        FiveBTN.setOnClickListener(keyboard);
        Button SixBTN = view.findViewById(R.id.SixBtnD);
        SixBTN.setOnClickListener(keyboard);
        Button SevenBTN = view.findViewById(R.id.SevenBtnD);
        SevenBTN.setOnClickListener(keyboard);
        Button EightBTN = view.findViewById(R.id.EightBtnD);
        EightBTN.setOnClickListener(keyboard);
        Button NineBTN = view.findViewById(R.id.NineBtnD);
        NineBTN.setOnClickListener(keyboard);
        Button zero = view.findViewById(R.id.ZeroBtnD);
        zero.setOnClickListener(keyboard);
        Button dot = view.findViewById(R.id.DotBtnD);
        dot.setOnClickListener(keyboard);

        Button ClearBtn = view.findViewById(R.id.ClearBtnD);
        ClearBtn.setOnClickListener(cleanListener);
        ImageButton backSpaceBTN = view.findViewById(R.id.BackSpaceBtnD);
        backSpaceBTN.setOnClickListener(backSpaceListener);
        ImageButton up = view.findViewById(R.id.UpBtnD);
        up.setOnClickListener(upListener);
        ImageButton down = view.findViewById(R.id.DownBtnD);
        down.setOnClickListener(downListener);

        //init
        spinnerFrom = view.findViewById(R.id.spinnerFrom);
        spinnerTo = view.findViewById(R.id.spinnerTo);
        editTextFrom = view.findViewById(R.id.editTextFrom);
        editTextTo = view.findViewById(R.id.editTextTo);
        core = new Core();

        //editTextFrom
        editTextFrom.addTextChangedListener(textWatcherFrom);
        editTextFrom.setShowSoftInputOnFocus(false);
        editTextFrom.requestFocus();

        //editTextTo
        editTextTo.addTextChangedListener(textWatcherTo);
        editTextTo.setShowSoftInputOnFocus(false);

        //from
        String[] fromArray = getResources().getStringArray(R.array.Data);
        ArrayAdapter<String> adapterFrom = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, fromArray);
        adapterFrom.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerFrom.setOnItemSelectedListener(listenerFrom);
        spinnerFrom.setAdapter(adapterFrom);
        String selectedFrom = spinnerFrom.getSelectedItem().toString();
        from = Core.Data.getEnum(delUnit(selectedFrom));


        //to
        String[] toArray = getResources().getStringArray(R.array.Data);
        ArrayAdapter<String> adapterTor = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, toArray);
        adapterTor.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerTo.setOnItemSelectedListener(listenerTo);
        spinnerTo.setAdapter(adapterTor);
        String selectedTo = spinnerTo.getSelectedItem().toString();
        to = Core.Data.getEnum(delUnit(selectedTo));

        // Inflate the layout for this fragment
        return view;
    }

    private final AdapterView.OnItemSelectedListener listenerFrom = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String string = adapterView.getItemAtPosition(i).toString();
            from = Core.Data.getEnum(delUnit(string));
            System.out.println(string);
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
            to = Core.Data.getEnum(delUnit(string));
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

    private void updateValues(@NotNull EditText source, Core.Data from, EditText destination, Core.Data to, boolean canBeInfinity) {
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

    private void BackSpaceA(EditText ed) {
        int cursorPos = ed.getSelectionStart();
        int textlen = ed.getText().length();
        if (cursorPos != 0 && textlen != 0) {
            SpannableStringBuilder selection = (SpannableStringBuilder) ed.getText();
            selection.replace(cursorPos - 1, cursorPos, "");
            ed.setText(selection);
            ed.setSelection(cursorPos - 1);
        }
    }

    private void updateText(String strToAdd,EditText display) {
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        if (getString(R.string.display).equals(display.getText().toString()) || display.getText().toString().equals("0.0")) {
            display.setText(strToAdd);
        } else {
            display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
        }
        display.setSelection(cursorPos + strToAdd.length());
    }

    View.OnClickListener backSpaceListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (editTextFrom.isFocused())
                BackSpaceA(editTextFrom);
            if (editTextTo.isFocused())
                BackSpaceA(editTextTo);
        }
    };

    private void CleanA(EditText ed) {
        ed.setText("0.0");
    }

    View.OnClickListener cleanListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (editTextFrom.isFocused())
                CleanA(editTextFrom);
            if (editTextTo.isFocused())
                CleanA(editTextTo);
        }
    };
    View.OnClickListener upListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            editTextFrom.requestFocus();
            editTextFrom.setSelection(editTextFrom.getText().toString().length());
        }
    };
    View.OnClickListener downListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            editTextTo.requestFocus();
            editTextTo.setSelection(editTextTo.getText().toString().length());
        }
    };
    View.OnClickListener keyboard = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v instanceof Button){
                Button button = (Button) v;
                if (editTextFrom.isFocused())
                    updateText(button.getText().toString(),editTextFrom);
                if (editTextTo.isFocused())
                    updateText(button.getText().toString(),editTextTo);
            }
        }
    };

}