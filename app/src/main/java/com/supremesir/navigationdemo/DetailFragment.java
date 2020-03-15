package com.supremesir.navigationdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.supremesir.navigationdemo.databinding.FragmentDetailBinding;
import com.supremesir.navigationdemo.databinding.FragmentHomeBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    TextView textView;
    String string;

    NavController controller;
    MyViewModel myViewModel;
    FragmentDetailBinding binding;

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        myViewModel = new ViewModelProvider(getActivity(), new SavedStateViewModelFactory(getActivity().getApplication(), this))
                .get(MyViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false);
        binding.setData(myViewModel);
        binding.setLifecycleOwner(getActivity());

        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_detailFragment2_to_homeFragment2);
            }
        });

        return binding.getRoot();

        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        getActivity().findViewById(R.id.button2).
//                setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_detailFragment2_to_homeFragment2));
//        textView = getView().findViewById(R.id.textView2);
//        // 直接从Fragment中获取缺省值
////        textView.setText(getArguments().getString("name"));
//        // 获取从其他Fragment中传入的自定义值
//        textView.setText(getArguments().getString("my_name"));
    }

    @Override
    public void onPause() {
        super.onPause();
        myViewModel.save();
    }
}
