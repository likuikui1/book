package com.chxy.usedbook.activity;


import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;
/**
 * Ö÷Ò³Tab
 * @author Èî·É
 *
 */
public class MainTabActivity extends TabActivity{

	public static final String TAB_HOME = "HOME_ACTIVITY";
	public static final String TAB_WISH = "WISH_ACTIVITY";
	public static final String TAB_MINE = "MINE_ACTIVITY";
	private TabHost mTabHost;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.bottom_tab);
		
		init();
	}

	private void init() {
		
		mTabHost = getTabHost();
		Intent homeIntent = new Intent(this,HomeActivity.class );
		Intent wishIntent = new Intent(this, WishListActivity.class);
		Intent mineIntent = new Intent(this, MineActivity.class);
		mTabHost.addTab(mTabHost.newTabSpec(TAB_HOME).setIndicator(TAB_HOME).setContent(homeIntent));
		mTabHost.addTab(mTabHost.newTabSpec(TAB_WISH).setIndicator(TAB_WISH).setContent(wishIntent));
		mTabHost.addTab(mTabHost.newTabSpec(TAB_MINE).setIndicator(TAB_MINE).setContent(mineIntent));
		RadioGroup rg_group=(RadioGroup) findViewById(R.id.rg_group);
		
		rg_group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				
				switch (checkedId) {
				case R.id.rb_home:
					mTabHost.setCurrentTabByTag(TAB_HOME);
					break;
				case R.id.rb_wish:
					mTabHost.setCurrentTabByTag(TAB_WISH);
					break;
				case R.id.rb_mine:
					mTabHost.setCurrentTabByTag(TAB_MINE);
					break;

				default:
					break;
				}
			}
		});
	}
	
	
}
