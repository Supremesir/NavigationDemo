package com.supremesir.navigationdemo;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavAction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.supremesir.navigationdemo.databinding.FragmentHomeBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


//    Button button;
//    EditText editText;
//    String editString;
//    Bundle bundle;

    NavController controller;
    MyViewModel myViewModel;
    FragmentHomeBinding binding;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        myViewModel = new ViewModelProvider(getActivity(), new SavedStateViewModelFactory(getActivity().getApplication() ,this))
                .get(MyViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        binding.setData(myViewModel);
        binding.setLifecycleOwner(getActivity());
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int n = Integer.parseInt(binding.editText.getText().toString());
                myViewModel.getNumber().setValue(n);
                controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_homeFragment2_to_detailFragment2);
            }
        });

        return binding.getRoot();

        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_home, container, false);
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
//        button = getView().findViewById(R.id.buttonsub);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                editText = getView().findViewById(R.id.editText);
//                editString = editText.getText().toString();
//                if (TextUtils.isEmpty(editString)) {
//                    Toast.makeText(getActivity(), "请输入", Toast.LENGTH_SHORT).show();
//
//                    return;
//                }
//                bundle = new Bundle();
//                bundle.putString("my_name", editString);
//
//                controller = Navigation.findNavController(v);
//                // 也可以直接传入Fragment的Id
//                controller.navigate(R.id.action_homeFragment2_to_detailFragment2, bundle);
//            }
//        });
//    }

    @Override
    public void onPause() {
        super.onPause();
        myViewModel.save();
    }
}
