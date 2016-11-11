package edu.example.pricecalculator;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class CalcActivity extends Activity 
{
	  private static final String BILL_TOTAL="bill_total";
	  private static final String CUSTOM_PERCENT="customPersent";
	  
	  private double currentBillTotal;
	  private int currentCustomPercent;
	  private EditText tip10EditText;
	  private EditText total10EditText;
	  private EditText tip15EditText;
	  private EditText total15EditText;
	  private EditText tip20EditText;
	  private EditText total20EditText;
	  private EditText billEditText;
	  private TextView customTipTextView;
	  private EditText tipCustomEditText;
	  private EditText totalCustomEditText;
	  private TextView textPercent;
	  

    /* (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
        
        //проверка запищено ли приложение впервые
        if(savedInstanceState==null)
        {
        	currentBillTotal=0.0;
        	currentCustomPercent=18;
        } else//если приложение востановленно с памяти, тогда забираем существующие значения тотала и процентов
        { 
        	currentBillTotal=savedInstanceState.getDouble(BILL_TOTAL);
        	currentCustomPercent=savedInstanceState.getInt(CUSTOM_PERCENT);
        }
        tip10EditText=(EditText) findViewById(R.id.tip10EditText);
        total10EditText=(EditText) findViewById(R.id.total10editText);
        tip15EditText=(EditText) findViewById(R.id.tip15EditText);
        total15EditText=(EditText) findViewById(R.id.total15editText);
        tip20EditText=(EditText) findViewById(R.id.tip20EditText);
        total20EditText=(EditText) findViewById(R.id.total20editText);
        
        textPercent=(TextView) findViewById(R.id.customTipTextView);
        
        customTipTextView=(TextView)findViewById(R.id.tipCustomtextView);
        tipCustomEditText=(EditText)findViewById(R.id.tipCustomEditText);
        totalCustomEditText=(EditText)findViewById(R.id.totalCustomEditText);
        billEditText=(EditText)findViewById(R.id.billEditText);
        billEditText.addTextChangedListener(billEditTextWatcher);//обрабатывает событие onTextChenged
        SeekBar customSeekBar=(SeekBar)findViewById(R.id.customSeekBar);
        customSeekBar.setOnSeekBarChangeListener(customSeekBarListener);//обрабатывает действия с SeekBar
   
    }
    
    @Override
	protected void onSaveInstanceState(Bundle outState)
    {		
		super.onSaveInstanceState(outState);
		//востановление значений при изминении конфигурации устройства
		outState.putDouble(BILL_TOTAL, currentBillTotal);
		outState.putInt(CUSTOM_PERCENT, currentCustomPercent);
	}
    //метод реагирующий на изменения положения ползунка
    private OnSeekBarChangeListener customSeekBarListener=new OnSeekBarChangeListener() {
		
		@Override
		public void onStopTrackingTouch(SeekBar seekBar)
		{
			// TODO Auto-generated method stub			
		}		
		@Override
		public void onStartTrackingTouch(SeekBar seekBar) 
		{
			// TODO Auto-generated method stub			
		}
		
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser)
		{
			//присваиваем currentCustomPercent значение ползунка
			currentCustomPercent=seekBar.getProgress();
			updateCustom();
			
		}
	};
	//анонимный внутрений класс реализующий интерфейс TextWatcher
	private TextWatcher billEditTextWatcher=new TextWatcher() {
		
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count)
		{
			try
			{
				currentBillTotal=Double.parseDouble(s.toString());
			} catch (NumberFormatException e)
			{
				currentBillTotal=0.0;//Значение по умолчанию
			}
			updateStandart();
			updateCustom();
		}
		
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
			
		}
	};

	//метод вычисляет чаевые хранящиеся в Edittext под ставками 10,15,20
    private void updateStandart()
    {
    	double tenPercentTip=currentBillTotal * .1;
    	double tenPercentTotal=currentBillTotal + tenPercentTip;
    	tip10EditText.setText(String.format("%.02f", tenPercentTip));//настраиваем текст в соответствии с tenPercentTip
    	total10EditText.setText(String.format("%.02f", tenPercentTotal));
    	
    	double fifteenPercentTip=currentBillTotal * .15;
    	double fifteenPercentTotal=currentBillTotal + fifteenPercentTip;
    	tip15EditText.setText(String.format("%.02f", fifteenPercentTip));//настраиваем текст в соответствии с tenPercentTip
    	total15EditText.setText(String.format("%.02f", fifteenPercentTotal));
    	
    	double twentyPercentTip=currentBillTotal * .2;
    	double twentyPercentTotal=currentBillTotal + twentyPercentTip;
    	tip20EditText.setText(String.format("%.02f", twentyPercentTip));//настраиваем текст в соответствии с tenPercentTip
    	total20EditText.setText(String.format("%.02f", twentyPercentTotal));
    }
    private void updateCustom()
    {
    	customTipTextView.setText(currentCustomPercent + " %");
    	double customTipAmount = currentBillTotal * currentCustomPercent * 0.01;
    	double customTotalAmount=currentBillTotal + customTipAmount;
    	tipCustomEditText.setText(String.format("%.02f", customTipAmount));
    	totalCustomEditText.setText(String.format("%.02f", customTotalAmount));
    	textPercent.setText(currentCustomPercent + "%");
    }
	  


  
}
