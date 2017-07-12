package com.chxy.usedbook.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
/**
 * 我的
 * @author 范晨
 *
 */
public class MineActivity  extends Activity implements OnClickListener {
    private Button mRegisterButton;//我的注册按钮
    private Button mLoginButton;//我的登录按钮



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.avtivity_mine);
        initView();
    }


    private void initView() {
        mRegisterButton = (Button) findViewById(R.id.mine_register_button);
        mLoginButton = (Button) findViewById(R.id.mine_login_button);

        mRegisterButton.setOnClickListener(this);
        mLoginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mine_register_button:
                Intent registerIntent = new Intent(this, RegisterActivity.class);
                startActivity(registerIntent);
                break;

            case R.id.mine_login_button:
                Intent loginInIntent = new Intent(this, LoginActivity.class);
                startActivity(loginInIntent);
                
                /*Intent personMsgIntent = new Intent(this, PersonalMessageActivity.class);
                startActivity(personMsgIntent);*/
                break;

            case R.id.mine_message:
                Intent myMessageIntent = new Intent(this,MyMessageActivity.class);
                startActivity(myMessageIntent);
                break;

            case R.id.mine_goods:
            	Intent myGoodsIntent = new Intent(this,MyGoodsActivity.class);
                startActivity(myGoodsIntent);
                break;

            case R.id.mine_wishlist:
            	Intent myWishListIntent = new Intent(this,MyWishListActivity.class);
                startActivity(myWishListIntent);
                break;

            case R.id.mine_setting:
            	Intent settingsIntent = new Intent(this,SettingsActivity.class);
                startActivity(settingsIntent);
                break;

            case R.id.mine_feedback:

                break;
        }
    }
}