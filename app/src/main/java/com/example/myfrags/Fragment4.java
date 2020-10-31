package com.example.myfrags;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import static android.text.InputType.TYPE_NUMBER_FLAG_SIGNED;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment4#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment4 extends Fragment {

    private FragsData fragsData;
    private Observer<Integer> numberObserver;
    private EditText editText;
    private TextWatcher textWatcher;
    private boolean turnOffWatcher;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment4() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment4.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment4 newInstance(String param1, String param2) {
        Fragment4 fragment = new Fragment4();
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

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_4, container, false);

        editText = view.findViewById(R.id.editTextNumber);
        fragsData = new ViewModelProvider(requireActivity()).get(FragsData.class);

        numberObserver = new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                turnOffWatcher = true;
                editText.setText(integer.toString());
                editText.setSelection(editText.length());
            }
        };
        //to w komenatrzu bo chce uzywac contentObserver do obserwacji do obserwowania


        fragsData.counter.observe(getViewLifecycleOwner(), numberObserver); //obserwowanie countera we fragsData.  w komenatrzu bo chce uzywac tylko content do obserwacji

        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(s.toString().trim().length()>0){
                    if (!turnOffWatcher) {
                        //Integer i; to w komentarzu bo uzywam stringa
                        String j;
                        try {
                            //i = Integer.parseInt(s.toString()); //to w komenatrzu bo uzywam stringa
                            j = s.toString();
                        } catch (NumberFormatException e) {
                            //i = fragsData.counter.getValue(); //to w komentarzu bo uzywam stringa, cale to exception jest bez sensu gdy uzywam stringa
                            j = fragsData.counter.getValue().toString();
                        }
                        //fragsData.counter.setValue(i);    //to w komentarzu bo uzywam content
                        if (isNumerical(j)){
                            fragsData.counter.setValue(Integer.parseInt(j));
                        }
                    } else {
                        turnOffWatcher = !turnOffWatcher;
                    }
                }
                else {

                }
            }
        };
        editText.addTextChangedListener(textWatcher);

        return view;
    }

    public boolean isNumerical(String value){
        try {
            Integer.parseInt(value);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
}
