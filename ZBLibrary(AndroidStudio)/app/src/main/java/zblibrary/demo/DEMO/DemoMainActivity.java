/*Copyright ©2015 TommyLemon(https://github.com/TommyLemon)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/

package zblibrary.demo.DEMO;

import java.util.ArrayList;
import java.util.Date;

import zblibrary.demo.R;
import zblibrary.demo.model.PickerViewData;
import zblibrary.demo.model.ProvinceBean;
import zuo.biao.library.base.BaseActivity;
import zuo.biao.library.interfaces.OnBottomDragListener;
import zuo.biao.library.ui.AlertDialog;
import zuo.biao.library.ui.AlertDialog.OnDialogButtonClickListener;
import zuo.biao.library.ui.BottomMenuWindow;
import zuo.biao.library.ui.CutPictureActivity;
import zuo.biao.library.ui.DatePickerWindow;
import zuo.biao.library.ui.EditTextInfoActivity;
import zuo.biao.library.ui.EditTextInfoWindow;
import zuo.biao.library.ui.ItemDialog;
import zuo.biao.library.ui.ItemDialog.OnDialogItemClickListener;
import zuo.biao.library.ui.PlacePickerWindow;
import zuo.biao.library.ui.SelectPictureActivity;
import zuo.biao.library.ui.TimePickerWindow;
import zuo.biao.library.ui.TopMenuWindow;
import zuo.biao.library.ui.WebViewActivity;
import zuo.biao.library.util.DataKeeper;
import zuo.biao.library.util.StringUtil;
import zuo.biao.library.util.TimeUtil;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.model.IPickerViewData;

import static zuo.biao.library.util.TimeUtil.getTime;

/**demo主页
 * @author Lemon
 */
public class DemoMainActivity extends BaseActivity implements OnClickListener, OnBottomDragListener
, OnDialogButtonClickListener, OnDialogItemClickListener, OnTouchListener {
	private static final String TAG = "DemoMainActivity";

	//启动方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	/**启动这个Activity的Intent
	 * @param context
	 * @return
	 */
	public static Intent createIntent(Context context) {
		return new Intent(context, DemoMainActivity.class);
	}

	//启动方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	@Override
	public Activity getActivity() {
		return this;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_main_activity, this);

		//功能归类分区方法，必须调用<<<<<<<<<<
		initView();
		initTimePickerView();
		initOptionData();
		initOptionPicker();
		initData();
		initEvent();
		//功能归类分区方法，必须调用>>>>>>>>>>

	}

	TimePickerView pvTime;

	private void initTimePickerView() {
		pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
			@Override
			public void onTimeSelect(Date date, View v) {//选中事件回调
				showShortToast(date.toString());
			}
		}).build();
	}

	private ArrayList<ProvinceBean> options1Items = new ArrayList<>();
	private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
	private ArrayList<ArrayList<ArrayList<IPickerViewData>>> options3Items = new ArrayList<>();

	private void initOptionData() {

		//选项1
		options1Items.add(new ProvinceBean(0,"广东","描述部分","其他数据"));
		options1Items.add(new ProvinceBean(1,"湖南","描述部分","其他数据"));
		options1Items.add(new ProvinceBean(2,"广西壮族字字去区","描述部分","其他数据"));

		//选项2
		ArrayList<String> options2Items_01=new ArrayList<>();
		options2Items_01.add("广州");
		options2Items_01.add("佛山");
		options2Items_01.add("东莞");
		options2Items_01.add("阳江");
		options2Items_01.add("珠海");
		ArrayList<String> options2Items_02=new ArrayList<>();
		options2Items_02.add("长沙");
		options2Items_02.add("岳阳");
		options2Items_02.add("株洲");
		options2Items_02.add("衡阳");
		ArrayList<String> options2Items_03=new ArrayList<>();
		options2Items_03.add("桂林");
		options2Items_03.add("玉林");
		options2Items.add(options2Items_01);
		options2Items.add(options2Items_02);
		options2Items.add(options2Items_03);

		//选项3
		ArrayList<ArrayList<IPickerViewData>> options3Items_01 = new ArrayList<>();
		ArrayList<ArrayList<IPickerViewData>> options3Items_02 = new ArrayList<>();
		ArrayList<ArrayList<IPickerViewData>> options3Items_03 = new ArrayList<>();

		//广东的地区
		ArrayList<IPickerViewData> options3Items_01_01=new ArrayList<>();
		options3Items_01_01.add(new PickerViewData("天河"));
		options3Items_01_01.add(new PickerViewData("海珠"));
		options3Items_01_01.add(new PickerViewData("越秀"));
		options3Items_01_01.add(new PickerViewData("荔湾"));
		options3Items_01_01.add(new PickerViewData("花都"));
		options3Items_01_01.add(new PickerViewData("番禺"));
		options3Items_01_01.add(new PickerViewData("萝岗"));
		options3Items_01.add(options3Items_01_01);

		ArrayList<IPickerViewData> options3Items_01_02=new ArrayList<>();
		options3Items_01_02.add(new PickerViewData("南海"));
		options3Items_01_02.add(new PickerViewData("高明"));
		options3Items_01_02.add(new PickerViewData("禅城"));
		options3Items_01_02.add(new PickerViewData("桂城"));
		options3Items_01.add(options3Items_01_02);

		ArrayList<IPickerViewData> options3Items_01_03=new ArrayList<>();
		options3Items_01_03.add(new PickerViewData("其他"));
		options3Items_01_03.add(new PickerViewData("常平"));
		options3Items_01_03.add(new PickerViewData("虎门"));
		options3Items_01.add(options3Items_01_03);

		ArrayList<IPickerViewData> options3Items_01_04=new ArrayList<>();
		options3Items_01_04.add(new PickerViewData("其他"));
		options3Items_01_04.add(new PickerViewData("其他"));
		options3Items_01_04.add(new PickerViewData("其他"));
		options3Items_01.add(options3Items_01_04);
		ArrayList<IPickerViewData> options3Items_01_05=new ArrayList<>();

		options3Items_01_05.add(new PickerViewData("其他1"));
		options3Items_01_05.add(new PickerViewData("其他2"));
		options3Items_01.add(options3Items_01_05);


		//湖南的地区
		ArrayList<IPickerViewData> options3Items_02_01=new ArrayList<>();
		options3Items_02_01.add(new PickerViewData("长沙1"));
		options3Items_02_01.add(new PickerViewData("长沙2"));
		options3Items_02_01.add(new PickerViewData("长沙3"));
		options3Items_02.add(options3Items_02_01);

		ArrayList<IPickerViewData> options3Items_02_02=new ArrayList<>();
		options3Items_02_02.add(new PickerViewData("岳阳1"));
		options3Items_02_02.add(new PickerViewData("岳阳2"));
		options3Items_02_02.add(new PickerViewData("岳阳3"));
		options3Items_02.add(options3Items_02_02);

		ArrayList<IPickerViewData> options3Items_02_03=new ArrayList<>();
		options3Items_02_03.add(new PickerViewData("株洲1"));
		options3Items_02_03.add(new PickerViewData("株洲2"));
		options3Items_02_03.add(new PickerViewData("株洲3"));
		options3Items_02.add(options3Items_02_03);

		ArrayList<IPickerViewData> options3Items_02_04=new ArrayList<>();
		options3Items_02_04.add(new PickerViewData("衡阳1"));
		options3Items_02_04.add(new PickerViewData("衡阳2"));
		options3Items_02_04.add(new PickerViewData("衡阳3"));
		options3Items_02.add(options3Items_02_04);


		//广西的地区
		ArrayList<IPickerViewData> options3Items_03_01=new ArrayList<>();
		options3Items_03_01.add(new PickerViewData("阳朔"));
		options3Items_03.add(options3Items_03_01);

		ArrayList<IPickerViewData> options3Items_03_02=new ArrayList<>();
		options3Items_03_02.add(new PickerViewData("北流"));
		options3Items_03.add(options3Items_03_02);

		//将数据分别添加到一二三项的数组去
		options3Items.add(options3Items_01);
		options3Items.add(options3Items_02);
		options3Items.add(options3Items_03);
        /*--------数据源添加完毕---------*/
	}

	private OptionsPickerView pvOptions;

	private void initOptionPicker() {//条件选择器初始化
		pvOptions = new  OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
			@Override
			public void onOptionsSelect(int options1, int option2, int options3 ,View v) {
				//返回的分别是三个级别的选中位置
				String tx = options1Items.get(options1).getPickerViewText()
						+ options2Items.get(options1).get(option2)
						+ options3Items.get(options1).get(option2).get(options3).getPickerViewText();

			}
		})
                /*.setSubmitText("确定")
                .setCancelText("取消")
                .setTitleText("城市选择")
                .setTitleSize(20)
                .setTitleColor(Color.BLACK)
                .setSubmitColor(Color.BLUE)
                .setCancelColor(Color.BLUE)
                .setBackgroundColor(Color.WHITE)
                .setLinkage(false)//default true
                .setCyclic(false, false, false)//循环与否
                .setOutSideCancelable(false)//点击外部dismiss, default true
                .setTitleBgColor(0xFF333333)//标题背景颜色 Night mode
                .setBgColor(0xFF000000)//滚轮背景颜色 Night mode
                .setLabels("省", "市", "区")//设置选择的三级单位*/
				.setSubCalSize(18)//确定取消按钮大小
				.setLineSpacingMultiplier(1.5f) //设置两横线之间的间隔倍数（范围：1.2 - 2.0倍 文字高度）
				//.setDividerColor(Color.RED)//设置分割线的颜色
				.setTextColorCenter(Color.BLACK) //设置选中项文字颜色
				.setContentTextSize(20)//设置滚轮文字大小
				.setSelectOptions(0,1,2)  //设置默认选中项
				//.isDialog(true)//设置为对话框模式
				.build();

		pvOptions.setPicker(options1Items, options2Items, options3Items);

	}

	//UI显示区(操作UI，但不存在数据获取或处理代码，也不存在事件监听代码)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	private static final String[] TOPBAR_COLOR_NAMES = {"灰色", "蓝色", "黄色"};
	private static final int[] TOPBAR_COLOR_RESIDS = {R.color.gray, R.color.blue,R.color.yellow};
	private View rlDemoMainTopbar;
	private ImageView ivDemoMainHead;
	private TextView tvDemoMainHeadName;

	private ScrollView svDemoMain;
	@Override
	public void initView() {//必须调用
		exitAnim = R.anim.bottom_push_out;

		rlDemoMainTopbar = findViewById(R.id.rlDemoMainTopbar);
		ivDemoMainHead = findViewById(R.id.ivDemoMainHead, this);
		tvDemoMainHeadName = findViewById(R.id.tvDemoMainHeadName, this);
		svDemoMain = (ScrollView) findViewById(R.id.svDemoMain);
	}

	/**显示列表选择弹窗
	 */
	private void showItemDialog() {
		new ItemDialog(context, TOPBAR_COLOR_NAMES, "选择颜色", DIALOG_SET_TOPBAR, this).show();
	}

	/**显示顶部菜单
	 */
	private void showTopMenu() {
		toActivity(TopMenuWindow.createIntent(context, new String[]{"更改导航栏颜色", "更改图片"}), REQUEST_TO_TOP_MENU, false);
	}


	private String picturePath;
	/**选择图片
	 */
	private void selectPicture() {
		toActivity(SelectPictureActivity.createIntent(context), REQUEST_TO_SELECT_PICTURE, false);
	}

	/**裁剪图片
	 * @param path
	 */
	private void cutPicture(String path) {
		if (StringUtil.isFilePath(path) == false) {
			Log.e(TAG, "cutPicture  StringUtil.isFilePath(path) == false >> showShortToast(找不到图片);return;");
			showShortToast("找不到图片");
			return;
		}
		this.picturePath = path;

		toActivity(CutPictureActivity.createIntent(context, path
				, DataKeeper.fileRootPath + DataKeeper.imagePath, "photo" + System.currentTimeMillis(), 200)
				, REQUEST_TO_CUT_PICTURE);
	}

	/**显示图片
	 * @param path
	 */
	private void setPicture(String path) {
		if (StringUtil.isFilePath(path) == false) {
			Log.e(TAG, "setPicture  StringUtil.isFilePath(path) == false >> showShortToast(找不到图片);return;");
			showShortToast("找不到图片");
			return;
		}
		this.picturePath = path;

		svDemoMain.smoothScrollTo(0, 0);
		//ImageLoaderUtil.loadImage(ivDemoMainHead, path);
	}

	/**编辑图片名称
	 */
	private void editName(boolean toWindow) {
		if (toWindow) {
			intent = EditTextInfoWindow.createIntent(context, EditTextInfoWindow.TYPE_NICK
					, "照片名称", StringUtil.getTrimedString(tvDemoMainHeadName), getPackageName());
		} else {
			intent = EditTextInfoActivity.createIntent(context, EditTextInfoActivity.TYPE_NICK
					, "照片名称", StringUtil.getTrimedString(tvDemoMainHeadName));
		}

		toActivity(intent, REQUEST_TO_EDIT_TEXT_INFO, ! toWindow);
	}

	//UI显示区(操作UI，但不存在数据获取或处理代码，也不存在事件监听代码)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>










	//Data数据区(存在数据获取或处理代码，但不存在事件监听代码)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public void initData() {//必须调用
		
	}


	//Data数据区(存在数据获取或处理代码，但不存在事件监听代码)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>








	//Event事件区(只要存在事件监听代码就是)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public void initEvent() {//必须调用
		
		findViewById(R.id.llDemoMainItemDialog).setOnClickListener(this);
		findViewById(R.id.llDemoMainAlertDialog).setOnClickListener(this);

		
		findViewById(R.id.llDemoMainScanActivity).setOnClickListener(this);
		findViewById(R.id.llDemoMainSelectPictureActivity).setOnClickListener(this);
		findViewById(R.id.llDemoMainCutPictureActivity).setOnClickListener(this);
		findViewById(R.id.llDemoMainWebViewActivity).setOnClickListener(this);
		findViewById(R.id.llDemoMainEditTextInfoActivity).setOnClickListener(this);
		findViewById(R.id.llDemoMainServerSettingActivity).setOnTouchListener(this);
		
		findViewById(R.id.llDemoMainDemoActivity).setOnClickListener(this);
		findViewById(R.id.llDemoMainDemoListActivity).setOnClickListener(this);
		findViewById(R.id.llDemoMainDemoFragmentActivity).setOnClickListener(this);
		findViewById(R.id.llDemoMainDemoTabActivity).setOnClickListener(this);
		findViewById(R.id.llDemoMainDemoSQLActivity).setOnClickListener(this);
		findViewById(R.id.llDemoMainDemoTimeRefresherActivity).setOnClickListener(this);
		findViewById(R.id.llDemoMainDemoBroadcastReceiverActivity).setOnClickListener(this);
		findViewById(R.id.llDemoMainDemoBottomWindow).setOnClickListener(this);

		
		findViewById(R.id.llDemoMainTopMenuWindow).setOnClickListener(this);
		findViewById(R.id.llDemoMainBottomMenuWindow).setOnClickListener(this);
		findViewById(R.id.llDemoMainEditTextInfoWindow).setOnClickListener(this);
		findViewById(R.id.llDemoMainPlacePickerWindow).setOnClickListener(this);
		findViewById(R.id.llDemoMainDatePickerWindow).setOnClickListener(this);
		findViewById(R.id.llDemoMainTimePickerWindow).setOnClickListener(this);
		findViewById(R.id.llDemoMainTimePickerView).setOnClickListener(this);
		findViewById(R.id.llDemoMainOptionsPickerView).setOnClickListener(this);


	}


	@Override
	public void onDialogButtonClick(int requestCode, boolean isPositive) {
		if (isPositive == false) {
			return;
		}

		rlDemoMainTopbar.setBackgroundResource(R.color.red);
	}


	private static final int DIALOG_SET_TOPBAR = 1;

	@Override
	public void onDialogItemClick(int requestCode, int position, String item) {
		if (position < 0) {
			position = 0;
		}
		switch (requestCode) {
		case DIALOG_SET_TOPBAR:
			if (position >= TOPBAR_COLOR_RESIDS.length) {
				position = TOPBAR_COLOR_RESIDS.length - 1;
			}
			rlDemoMainTopbar.setBackgroundResource(TOPBAR_COLOR_RESIDS[position]);
			break;
		default:
			break;
		}
	}

	@Override
	public void onDragBottom(boolean rightToLeft) {
		if (rightToLeft) {
			showTopMenu();
			return;
		}	

		finish();
	}

	//系统自带监听方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


	private long touchDownTime = 0;
	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (v.getId() == R.id.llDemoMainServerSettingActivity) {
				touchDownTime = System.currentTimeMillis();
				Log.i(TAG, "onTouch MotionEvent.ACTION: touchDownTime=" + touchDownTime);
				return true;
			}
		case MotionEvent.ACTION_UP:
//			if (v.getId() == R.id.llDemoMainServerSettingActivity) {
//				long time = System.currentTimeMillis() - touchDownTime;
//				if (time < 5000 || time > 8000) {
//					showShortToast("请长按5-8秒");
//				} else {
//					toActivity(ServerSettingActivity.createIntent(context
//							, SettingUtil.getServerAddress(context, false), SettingUtil.getServerAddress(context, true)
//							, SettingUtil.APP_SETTING, Context.MODE_PRIVATE
//							, SettingUtil.KEY_SERVER_ADDRESS_NORMAL, SettingUtil.KEY_SERVER_ADDRESS_TEST));
//					return true;
//				}
//			}
			break;
		default:
			break;
		}

		return false;
	}

	private int[] selectedDate = new int[]{1971, 0, 1};
	private int[] selectedTime = new int[]{12, 0, 0};
	@Override
	public void onClick(View v) {//直接调用不会显示v被点击效果
		switch (v.getId()) {
		case R.id.ivDemoMainHead:
			selectPicture();
			break;     
		case R.id.tvDemoMainHeadName:
			editName(true);
			break;    

			
		case R.id.llDemoMainItemDialog:
			showItemDialog();
			break;
		case R.id.llDemoMainTimePickerView:
			pvTime.show();
			break;
		case R.id.llDemoMainOptionsPickerView:
			pvOptions.show();
			break;
		case R.id.llDemoMainAlertDialog:
			new AlertDialog(context, "更改颜色", "确定将导航栏颜色改为红色？", true, 0, this).show();
		break;
		case R.id.llDemoMainScanActivity:
			break;
		case R.id.llDemoMainSelectPictureActivity:
			selectPicture();
			break;   
		case R.id.llDemoMainCutPictureActivity:
			cutPicture(picturePath);
			break;  
		case R.id.llDemoMainWebViewActivity:

			break;   
		case R.id.llDemoMainEditTextInfoActivity:
			editName(false);
			break;
		case R.id.llDemoMainDemoListActivity:
			break;
		case R.id.llDemoMainDemoFragmentActivity:
			break;
		case R.id.llDemoMainDemoTabActivity:
			//toActivity(DemoTabActivity.createIntent(context));
			break; 
		case R.id.llDemoMainDemoSQLActivity:
			break;
		case R.id.llDemoMainDemoTimeRefresherActivity:
			break;
		case R.id.llDemoMainDemoBottomWindow:
			toActivity(DemoBottomWindow.createIntent(context, ""), REQUEST_TO_DEMO_BOTTOM_WINDOW, false);
			break;
		case R.id.llDemoMainTopMenuWindow:
			showTopMenu();
			break;  
		case R.id.llDemoMainBottomMenuWindow:
			toActivity(BottomMenuWindow.createIntent(context, TOPBAR_COLOR_NAMES)
					.putExtra(BottomMenuWindow.INTENT_TITLE, "选择颜色"), REQUEST_TO_BOTTOM_MENU, false);
			break; 
		case R.id.llDemoMainEditTextInfoWindow:
			editName(true);
			break;  
		case R.id.llDemoMainPlacePickerWindow:
			toActivity(PlacePickerWindow.createIntent(context, getPackageName(), 2), REQUEST_TO_PLACE_PICKER, false);
			break;
		case R.id.llDemoMainDatePickerWindow:
			toActivity(DatePickerWindow.createIntent(context, new int[]{1971, 0, 1}
			, TimeUtil.getDateDetail(System.currentTimeMillis())), REQUEST_TO_DATE_PICKER, false);
			break;  
		case R.id.llDemoMainTimePickerWindow:
			toActivity(TimePickerWindow.createIntent(context, selectedTime), REQUEST_TO_TIME_PICKER, false);
			break;  

		default:
			break;
		}
	}



	//类相关监听<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	private static final int REQUEST_TO_SELECT_PICTURE = 20;
	private static final int REQUEST_TO_CUT_PICTURE = 21;
	public static final int REQUEST_TO_CAMERA_SCAN = 22;
	private static final int REQUEST_TO_EDIT_TEXT_INFO = 23;
	private static final int REQUEST_TO_SERVER_SETTING = 24;
	private static final int REQUEST_TO_DEMO_BOTTOM_WINDOW = 25;
	
	private static final int REQUEST_TO_TOP_MENU = 30;
	private static final int REQUEST_TO_BOTTOM_MENU = 31;
	private static final int REQUEST_TO_PLACE_PICKER = 32;
	private static final int REQUEST_TO_DATE_PICKER = 33;
	private static final int REQUEST_TO_TIME_PICKER = 34;
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode != RESULT_OK) {
			return;
		}
		switch (requestCode) {
		case REQUEST_TO_SELECT_PICTURE:
			if (data != null) {
				cutPicture(data.getStringExtra(SelectPictureActivity.RESULT_PICTURE_PATH));
			}
			break;
		case REQUEST_TO_CUT_PICTURE:
			if (data != null) {
				setPicture(data.getStringExtra(CutPictureActivity.RESULT_PICTURE_PATH));
			}
			break;
		case REQUEST_TO_CAMERA_SCAN:
			if (data != null) {

			}
			break;
		case REQUEST_TO_EDIT_TEXT_INFO:
			if (data != null) {
				svDemoMain.smoothScrollTo(0, 0);
				tvDemoMainHeadName.setText(StringUtil.getTrimedString(
						data.getStringExtra(EditTextInfoWindow.RESULT_VALUE)));
			}
			break;
		case REQUEST_TO_SERVER_SETTING:
			if (data != null) {
				//TODO
			}
			break;
		case REQUEST_TO_DEMO_BOTTOM_WINDOW:
			if (data != null) {
				showShortToast(data.getStringExtra(DemoBottomWindow.RESULT_DATA));
			}
			break;
			
		case REQUEST_TO_TOP_MENU:
			if (data != null) {
				switch (data.getIntExtra(TopMenuWindow.RESULT_POSITION, -1)) {
				case 0:
					showItemDialog();
					break;
				case 1:
					selectPicture();
					break;
				default:
					break;
				}
			}
			break;
		case REQUEST_TO_BOTTOM_MENU:
			if (data != null) {
				int selectedPosition = data.getIntExtra(BottomMenuWindow.RESULT_ITEM_ID, -1);
				if (selectedPosition >= 0 && selectedPosition < TOPBAR_COLOR_RESIDS.length) {
					rlDemoMainTopbar.setBackgroundResource(TOPBAR_COLOR_RESIDS[selectedPosition]);
				}
			}
			break;
	
		case REQUEST_TO_PLACE_PICKER:
			if (data != null) {
				ArrayList<String> placeList = data.getStringArrayListExtra(PlacePickerWindow.RESULT_PLACE_LIST);
				if (placeList != null) {
					String place = "";
					for (String s : placeList) {
						place += StringUtil.getTrimedString(s);
					}
					showShortToast("选择的地区为: " + place);
				}
			}
			break;
		case REQUEST_TO_DATE_PICKER:
			if (data != null) {
				ArrayList<Integer> list = data.getIntegerArrayListExtra(DatePickerWindow.RESULT_DATE_DETAIL_LIST);
				if (list != null && list.size() >= 3) {
					
					selectedDate = new int[list.size()];
					for (int i = 0; i < list.size(); i++) {
						selectedDate[i] = list.get(i);
					}
					
					showShortToast("选择的日期为" + selectedDate[0] + "-" + (selectedDate[1] + 1) + "-" + selectedDate[2]);
				}
			}
			break;
		case REQUEST_TO_TIME_PICKER:
			if (data != null) {
				ArrayList<Integer> list = data.getIntegerArrayListExtra(TimePickerWindow.RESULT_TIME_DETAIL_LIST);
				if (list != null && list.size() >= 2) {
					
					selectedTime = new int[list.size()];
					for (int i = 0; i < list.size(); i++) {
						selectedTime[i] = list.get(i);
					}
					
					String minute = "" + selectedTime[1];
					if (minute.length() < 2) {
						minute = "0" + minute;
					}
					showShortToast("选择的时间为" + selectedTime[0] + ":" + minute);
				}
			}
			break;
		default:
			break;
		}

	}


	//类相关监听<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<





	//类相关监听>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	//系统自带监听方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	//类相关监听>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	//系统自带监听>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	//Event事件区(只要存在事件监听代码就是)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>








	//内部类,尽量少用<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<



	//内部类,尽量少用>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

}