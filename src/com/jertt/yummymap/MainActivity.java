package com.jertt.yummymap;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.os.Bundle;
import android.support.v4.app.*;
import android.support.v4.view.ViewPager;
import android.view.Menu;

public class MainActivity extends FragmentActivity implements
		ActionBar.TabListener {

	SectionsPagerAdapter mSectionsPagerAdapter;

	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		String titles[] = { this.getString(R.string.title_around),
				this.getString(R.string.title_map),
				this.getString(R.string.title_favorite),
				this.getString(R.string.title_achievement), };

		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		for (int i = 0; i < titles.length; i++) {
			actionBar.addTab(actionBar.newTab().setText(titles[i])
					.setTabListener(this));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction fragmentTransaction) {
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// TODO 在這邊決定哪個位置要給什麼 View（Fragment）
			Fragment fragment = null;
			// TODO 建議先在之前就初始化好 4 個 Fragment
			switch (position) {
			case 0:
				fragment = new AroundFragment();
				break;
			case 1:
				fragment = new MapFragment();
				break;
			case 2:
				fragment = new FavoriteFragment();
				break;
			case 3:
				fragment = new AchievementFragment();
				break;
			default:
				break;
			}

			return fragment;
		}

		@Override
		public int getCount() {
			// 共有 4 個 page
			return 4;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			// TODO 在這邊決定每一個 View 的 title
			switch (position) {
			case 0:
				return getString(R.string.title_around);
			case 1:
				return getString(R.string.title_map);
			case 2:
				return getString(R.string.title_favorite);
			case 3:
				return getString(R.string.title_achievement);
			}
			return null;
		}
	}

}
