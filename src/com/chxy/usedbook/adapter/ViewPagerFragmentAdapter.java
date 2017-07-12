package com.chxy.usedbook.adapter;

import java.util.List;

import org.xutils.x;
import org.xutils.image.ImageOptions;

import com.chxy.usedbook.activity.R;
import com.chxy.usedbook.vo.Book;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewPagerFragmentAdapter extends BaseAdapter{//

	private Context mContext;
	private List<Book> bookList;//
	private Book books;
		
	public ViewPagerFragmentAdapter(Context context,List<Book> bookList) {
		 super();		
		this.mContext=context;
		this.bookList=bookList;
	}
	
	@Override
	public int getCount() {
		return bookList.size();
	}

	@Override
	public Object getItem(int position) {
		
		
		
		return bookList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		books = bookList.get(position);
		View view;
		viewHolder holder=new viewHolder();
		if (convertView==null) {
			view=LayoutInflater.from(mContext).inflate(R.layout.books_market_content_item, null);
			holder.bookImage=(ImageView) view.findViewById(R.id.books_market_content_item_img);
			holder.bookName=((TextView)view.findViewById(R.id.books_market_content_item_name));
			holder.bookAnnounce=(TextView)view.findViewById(R.id.books_market_content_item_announce_txt);
			holder.bookPerson=(TextView)view.findViewById(R.id.books_market_content_item_announce_person_name);
			holder.bookSexImage=(ImageView) view.findViewById(R.id.books_market_content_item_announce_sex_img);
			holder.bookKind=(TextView)view.findViewById(R.id.books_market_content_item_books_kind);
			holder.bookPrice=(TextView)view.findViewById(R.id.books_market_content_item_books_price_txt);
			holder.bookMoney=(TextView)view.findViewById(R.id.books_market_content_item_books_price);
			view.setTag(holder);
		}else{
			view = convertView;
			holder=(viewHolder) view.getTag();
		}
//		holder.bookImage.setImageResource();
		/*ImageOptions imageOptions = new ImageOptions.Builder()
        .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
        .setCircular(isCircluar)
        .setCrop(true)
        .setLoadingDrawableId(R.mipmap.ic_launcher)
        .setFailureDrawableId(R.mipmap.ic_launcher)
        .build();*/
		if(books.getBookImage()!=null&&!books.getBookImage().equals("")){
			x.image().bind(holder.bookImage,books.getBookImage());
		}
		if(books.getBookName()!=null&&!books.getBookName().equals("")){
			holder.bookName.setText(books.getBookName());
		}
		if(books.getUser()!=null){
			if(books.getUser().getUserName()!=null&&!books.getUser().getUserName().equals("")){
				holder.bookPerson.setText(books.getUser().getUserName());
			}
		}
		if(books.getUser()!=null){
			if(books.getUser().getUserSex()!=null&& !books.getUser().getUserSex().equals("")){
				if (books.getUser().getUserSex().equals("ÄÐ")){//
		        	holder.bookSexImage.setImageResource(R.drawable.home_icon_boy);
				} else {
					holder.bookSexImage.setImageResource(R.drawable.home_icon_girl);
				}
			}
			
		}
		if(books.getBookKind()!=null&&!books.getBookKind().equals("")){
			holder.bookKind.setText(books.getBookKind());
		}
		if(books.getBookMoney()!=null&&!books.getBookMoney().equals("")){
			holder.bookMoney.setText(books.getBookMoney());
		}
		
		return view;			
	}
	class viewHolder{

		public TextView bookName;
		public TextView bookMoney;
		public ImageView bookSexImage;
		public TextView bookPrice;
		public TextView bookKind;
		public TextView bookPerson;
		public TextView bookAnnounce;
		public ImageView bookImage;		
					
	}
}
