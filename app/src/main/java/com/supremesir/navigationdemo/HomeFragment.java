package com.supremesir.navigationdemo;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    Button button;
    NavController controller;
    EditText editText;
    String editString;
    Bundle bundle;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        button = getView().findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editText = getView().findViewById(R.id.editText);
                editString = editText.getText().toString();
                if (TextUtils.isEmpty(editString)) {
                    Toast.makeText(getActivity(), "请输入", Toast.LENGTH_SHORT).show();

                    return;
                }
                bundle = new Bundle();
                bundle.putString("my_name", editString);

                controller = Navigation.findNavController(v);
                // 也可以直接传入Fragment的Id
                controller.navigate(R.id.action_homeFragment2_to_detailFragment2, bundle);
            }
        });
    }
}
