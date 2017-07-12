package com.chxy.usedbook.view;

import com.chxy.usedbook.activity.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * ע��
 * @author ����
 *
 */
public class CustomDialog extends Dialog {
    Button yes;//ȷ����ť

    private onYesOnclickListener yesOnclickListener;//ȡ����ť������˵ļ�����


    /**
     * ����ȷ����ť�ļ���
     *
     * @param onYesOnclickListener
     */
    public void setYesOnclickListener(onYesOnclickListener onYesOnclickListener) {

        this.yesOnclickListener = onYesOnclickListener;
    }

    public CustomDialog(Context context) {
        super(context, R.style.CustomDialogTheme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_success_dialog);
        //���հ״�����ȡ������
        setCanceledOnTouchOutside(false);
        //��ʼ������ؼ�
        initView();
        //��ʼ������ؼ����¼�
        initEvent();

    }

    /**
     * ��ʼ�������ȷ��������
     */
    private void initEvent() {
        //����ȷ����ť�������������ṩ����
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yesOnclickListener != null) {
                    yesOnclickListener.onYesClick();
                }
            }
        });
    }


    /**
     * ��ʼ������ؼ�
     */
    private void initView() {
        yes = (Button) findViewById(R.id.dialog_yes_button);
    }


    /**
     * ����ȷ����ť������Ľӿ�
     */
    public interface onYesOnclickListener {
        public void onYesClick();
    }

    public interface onNoOnclickListener {
        public void onNoClick();
    }
}
