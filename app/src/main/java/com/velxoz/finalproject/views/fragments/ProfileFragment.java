package com.velxoz.finalproject.views.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.velxoz.finalproject.R;
import com.velxoz.finalproject.util.session.MainSession;
import com.velxoz.finalproject.views.UbahDataActivity;
import com.velxoz.finalproject.views.UbahKataSandiActivity;
import com.velxoz.finalproject.views.UnderConstructionActivity;
import com.velxoz.finalproject.views.auth.LoginActivity;

import java.util.HashMap;

public class ProfileFragment extends Fragment {

    Button btnLogout;
    MainSession mainSession;
    TextView tvFullName, tvUsername, tvPhoneNumber, tvUbahDataDiri, tvUbahKataSandi, tvVoucher, tvPusatBantuan, tvPengaturan;

    View view;
    HashMap<String, String> user;
    Intent i;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);

        loadComponent();
        listener();

        return view;
    }

    private void loadComponent(){
        mainSession = new MainSession(getActivity());
        user = mainSession.getUserDetails();
        btnLogout = view.findViewById(R.id.btnLogout);
        tvFullName = view.findViewById(R.id.tvFullName);
        tvUsername = view.findViewById(R.id.tvUsername);
        tvPhoneNumber = view.findViewById(R.id.tvPhoneNumber);
        tvUbahDataDiri = view.findViewById(R.id.tvUbahDataDiri);
        tvUbahKataSandi = view.findViewById(R.id.tvUbahKataSandi);
        tvVoucher = view.findViewById(R.id.tvVoucher);
        tvPusatBantuan = view.findViewById(R.id.tvPusatBantuan);
        tvPengaturan = view.findViewById(R.id.tvPengaturan);
        tvFullName.setText(user.get("first_name") + " " + user.get("last_name"));
        tvUsername.setText(user.get("username"));
        tvPhoneNumber.setText(user.get("mobile_number"));
    }

    private void listener(){

        tvUbahDataDiri.setOnClickListener(v -> {
            i = new Intent(getActivity(), UbahDataActivity.class);
            startActivity(i);
        });

        tvUbahKataSandi.setOnClickListener(v -> {
            i = new Intent(getActivity(), UbahKataSandiActivity.class);
            startActivity(i);
        });

        tvVoucher.setOnClickListener(v -> {
            i = new Intent(getActivity(), UnderConstructionActivity.class);
            startActivity(i);
        });

        tvPusatBantuan.setOnClickListener(v -> {
            i = new Intent(getActivity(), UnderConstructionActivity.class);
            startActivity(i);
        });

        tvPengaturan.setOnClickListener(v -> {
            i = new Intent(getActivity(), UnderConstructionActivity.class);
            startActivity(i);
        });

        btnLogout.setOnClickListener(v -> {
            mainSession.logoutUser();
            i = new Intent(getActivity(), LoginActivity.class);
            startActivity(i);
            getActivity().finish();

        });
    }
}