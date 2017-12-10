package ius.smartlab.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import ius.smartlab.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ManualSettingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ManualSettingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ManualSettingFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button buttonManSetting;
    private TextView temperatureManSetting;
    private TextView lightManSetting;
    private TextView doorsManSetting;
    private Long t;
    private Long l;
    private Long d;
    private OnFragmentInteractionListener mListener;
    RadioButton light_on;
    RadioButton light_off;
    RadioButton doors_locked;
    RadioButton doors_unlocked;

    public ManualSettingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ManualSettingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ManualSettingFragment newInstance(String param1, String param2) {
        ManualSettingFragment fragment = new ManualSettingFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_manual_setting, container, false);
        lightManSetting=(TextView)rootView.findViewById(R.id.man_light);
        buttonManSetting=(Button)rootView.findViewById(R.id.man_setting_button);
        temperatureManSetting=(TextView)rootView.findViewById(R.id.man_temperature);
        lightManSetting=(TextView)rootView.findViewById(R.id.man_light);
        doorsManSetting=(TextView)rootView.findViewById(R.id.man_doors);
        buttonManSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t=Long.parseLong(temperatureManSetting.getText().toString());
                l=Long.parseLong(lightManSetting.getText().toString());
                d=Long.parseLong(doorsManSetting.getText().toString());
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = database.getReference();
                databaseReference.child("Current_status").child("temperature").setValue(t);
                databaseReference.child("Current_status").child("light").setValue(l);
                databaseReference.child("Current_status").child("doors").setValue(d);

                Toast.makeText(getActivity(),"You successfully updated lab manual setting",Toast.LENGTH_LONG).show();
                //clear edittexts
                temperatureManSetting.setText("");
                lightManSetting.setText("");
                doorsManSetting.setText("");
            }
        });

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
