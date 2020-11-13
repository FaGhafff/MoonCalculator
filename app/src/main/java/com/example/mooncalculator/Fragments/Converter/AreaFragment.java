package com.example.mooncalculator.Fragments.Converter;

import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
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
    private Core.Area to = null;
    private Core core;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_area, container, false);


        //keyboard
        Button OneBTN = view.findViewById(R.id.OneBtnA);
        OneBTN.setOnClickListener(keyboard);
        Button TwoBTN = view.findViewById(R.id.TwoBtnA);
        TwoBTN.setOnClickListener(keyboard);
        Button ThreeBTN = view.findViewById(R.id.ThreeBtnA);
        ThreeBTN.setOnClickListener(keyboard);
        Button FourBTN = view.findViewById(R.id.FourBtnA);
        FourBTN.setOnClickListener(keyboard);
        Button FiveBTN = view.findViewById(R.id.FiveBtnA);
        FiveBTN.setOnClickListener(keyboard);
        Button SixBTN = view.findViewById(R.id.SixBtnA);
        SixBTN.setOnClickListener(keyboard);
        Button SevenBTN = view.findViewById(R.id.SevenBtnA);
        SevenBTN.setOnClickListener(keyboard);
        Button EightBTN = view.findViewById(R.id.EightBtnA);
        EightBTN.setOnClickListener(keyboard);
        Button NineBTN = view.findViewById(R.id.NineBtnA);
        NineBTN.setOnClickListener(keyboard);
        Button zero = view.findViewById(R.id.ZeroBtnA);
        zero.setOnClickListener(keyboard);
        Button ClearBtn = view.findViewById(R.id.ClearBtnA);
        ClearBtn.setOnClickListener(keyboard);
        ImageButton backSpaceBTN = view.findViewById(R.id.BackSpaceBtnA);
        backSpaceBTN.setOnClickListener(keyboard);
        ImageButton up = view.findViewById(R.id.UpBtnA);
        up.setOnClickListener(keyboard);
        ImageButton down = view.findViewById(R.id.DownBtnA);
        down.setOnClickListener(keyboard);
        Button dot = view.findViewById(R.id.DotBtnA);
        dot.setOnClickListener(keyboard);


        //init
        spinnerFrom = view.findViewById(R.id.spinnerFrom);
        spinnerTo = view.findViewById(R.id.spinnerTo);
        editTextFrom = view.findViewById(R.id.editTextFrom);
        editTextTo = view.findViewById(R.id.editTextTo);
        core = new Core();

        //editTextFrom
        editTextFrom.addTextChangedListener(textWatcherFrom);
        editTextFrom.setShowSoftInputOnFocus(false);
        editTextFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getString(R.string._0).equals(editTextFrom.getText().toString())) {
                    editTextFrom.setText("");
                }
            }
        });


        //editTextTo
        editTextTo.addTextChangedListener(textWatcherTo);
        editTextTo.setShowSoftInputOnFocus(false);
        editTextTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getString(R.string._0).equals(editTextTo.getText().toString())){
                    editTextTo.setText("");
                }
            }
        });

        //from
        String[] fromArray = getResources().getStringArray(R.array.Area);
        ArrayAdapter<String> adapterFrom = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, fromArray);
        adapterFrom.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerFrom.setOnItemSelectedListener(listenerFrom);
        spinnerFrom.setAdapter(adapterFrom);
        String selectedFrom = spinnerFrom.getSelectedItem().toString();
        from = Core.Area.getEnum(delUnit(selectedFrom));


        //to
        String[] toArray = getResources().getStringArray(R.array.Area);
        ArrayAdapter<String> adapterTor = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, toArray);
        adapterTor.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerTo.setOnItemSelectedListener(listenerTo);
        spinnerTo.setAdapter(adapterTor);
        String selectedTo = spinnerTo.getSelectedItem().toString();
        to = Core.Area.getEnum(delUnit(selectedTo));


        //setFocus
         editTextFrom.requestFocus();



        // Inflate the layout for this fragment
        return view;
    }

    private final AdapterView.OnItemSelectedListener listenerFrom = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String string = adapterView.getItemAtPosition(i).toString();
            from = Core.Area.getEnum(delUnit(string));
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
            to = Core.Area.getEnum(delUnit(string));
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

    private void updateValues(@NotNull EditText source, Core.Area from,EditText destination, Core.Area to, boolean canBeInfinity) {
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
        string = string.substring(0, string.indexOf('('));
        return string;
        // todo : clean this shits
    }


    View.OnClickListener keyboard = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button button = (Button) v;


        }
    };

    private void BackSpaceA (EditText ed){
        int cursorPos = ed.getSelectionStart();
        int textlen = ed.getText().length();
        if (cursorPos != 0 && textlen != 0) {
            SpannableStringBuilder selection = (SpannableStringBuilder) ed.getText();
            selection.replace(cursorPos - 1, cursorPos, "");
            ed.setText(selection);
            ed.setSelection(cursorPos - 1);
    }
}
    View.OnClickListener backspaceListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (editTextFrom.isFocused())
                BackSpaceA(editTextFrom);
            if (editTextTo.isFocused())
                BackSpaceA(editTextTo);
        }
    };

    private void CleanA(EditText ed){
        ed.setText("");
    }
    View.OnClickListener CleanListiner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (editTextFrom.isFocused())
                CleanA(editTextFrom);
            if (editTextTo.isFocused())
                CleanA(editTextTo);
        }
    };
    View.OnClickListener UpListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            editTextFrom.requestFocus();
        }
    };
    View.OnClickListener downListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            editTextTo.requestFocus();

        }
    };
}