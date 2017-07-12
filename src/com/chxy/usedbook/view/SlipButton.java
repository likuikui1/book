package com.chxy.usedbook.view;

/**
 * ����
 * 
 * @author ������
 * 
 */
import com.chxy.usedbook.activity.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;


public class SlipButton extends View implements OnTouchListener {

	// ��¼��ǰ��ť�Ƿ��,trueΪ��,falseΪ�ر�
	private boolean NowChoose = false;
	// ��¼�û��Ƿ��ڻ����ı���
	private boolean OnSlip = false;
	// ����ʱ��x,��ǰ��x,
	private float DownX, NowX;
	// �򿪺͹ر�״̬��,�α��Rect
	private Rect Btn_On, Btn_Off;

	private boolean isChgLsnOn = false;
	private OnChangedListener ChgLsn;

	private Bitmap bg_on, bg_off, slip_btn;

	public SlipButton(Context context) {
		super(context);
		init();
	}

	public SlipButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		// ��ʼ��
		// ����ͼƬ��Դ
		bg_on = BitmapFactory.decodeResource(getResources(), R.drawable.bg_on);
		bg_off = BitmapFactory
				.decodeResource(getResources(), R.drawable.bg_off);
		slip_btn = BitmapFactory.decodeResource(getResources(),
				R.drawable.slip_btn);

		// �����Ҫ��Rect����
		// Btn_On = new Rect(0, 0, slip_btn.getWidth(), slip_btn.getHeight());
		Btn_On = new Rect(3, (bg_off.getHeight() - slip_btn.getHeight())/2, slip_btn.getWidth()+3, slip_btn.getHeight()+(bg_off.getHeight() - slip_btn.getHeight())/2);
		Btn_Off = new Rect(bg_off.getWidth()-3- slip_btn.getWidth(),(bg_off.getHeight() - slip_btn.getHeight())/2,bg_off.getWidth()-3, slip_btn.getHeight()+(bg_off.getHeight() - slip_btn.getHeight())/2);
		// ���ü�����,Ҳ����ֱ�Ӹ�дOnTouchEvent
		setOnTouchListener(this);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		// ��ͼ����
		super.onDraw(canvas);
		Matrix matrix = new Matrix();
		Paint paint = new Paint();
		float x;

		{
			// ������ǰ�������εı�����ͬ,�ڴ����ж�
			if (NowX < (bg_on.getWidth() / 2))
				canvas.drawBitmap(bg_off, matrix, paint);// �����ر�ʱ�ı���
			else
				canvas.drawBitmap(bg_on, matrix, paint);// ������ʱ�ı���

			if (OnSlip)// �Ƿ����ڻ���״̬,
			{
				if (NowX >= bg_on.getWidth() - 3)// �Ƿ񻮳�ָ����Χ,�������α��ܵ���ͷ,����������ж�
					x = bg_on.getWidth() - slip_btn.getWidth() / 2 - 3;// ��ȥ�α�1/2�ĳ���...
				else
					x = NowX - slip_btn.getWidth() / 2;
			} else {// �ǻ���״̬
				if (NowChoose)// �������ڵĿ���״̬���û��α��λ��
					x = Btn_Off.left;
				else
					x = Btn_On.left;
			}
			if (x < 0)// ���α�λ�ý����쳣�ж�...
				x = 3;
			else if (x > bg_on.getWidth() - slip_btn.getWidth())
				x = bg_on.getWidth() - slip_btn.getWidth()-3;
			canvas.drawBitmap(slip_btn, x, (bg_off.getHeight() - slip_btn.getHeight())/2, paint);// �����α�.
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// ���ݶ�����ִ�д���
		switch (event.getAction()) {
		case MotionEvent.ACTION_MOVE:// ����
			NowX = event.getX();
			break;
		case MotionEvent.ACTION_DOWN:// ����
			if (event.getX() > bg_on.getWidth()
					|| event.getY() > bg_on.getHeight())
				return false;
			OnSlip = true;
			DownX = event.getX();
			NowX = DownX;
			break;
		case MotionEvent.ACTION_UP:// �ɿ�
			OnSlip = false;
			boolean LastChoose = NowChoose;
			if (event.getX() >= (bg_on.getWidth() / 2))
				NowChoose = true;
			else
				NowChoose = false;
			// ��������˼�����,�͵����䷽��..
			if (isChgLsnOn && (LastChoose != NowChoose))
				ChgLsn.OnChanged(NowChoose);
			break;
		default:

		}
		invalidate();// �ػ��ؼ�
		return true;
	}

	public void SetOnChangedListener(OnChangedListener l) {
		// ���ü�����,��״̬�޸ĵ�ʱ��
		isChgLsnOn = true;
		ChgLsn = l;
	}

}