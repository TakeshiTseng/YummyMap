package com.jertt.yummymap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AchievementFragment extends Fragment{
	public AchievementFragment() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.favorite_fragment,
				container, false);
		
		// TODO 初始化
		// TODO 用 getArguments().getxxx 來取得傳過來的參數
		
		return rootView;
	}
}
