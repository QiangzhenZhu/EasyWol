package com.a10835.easywol.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.Toast;

import com.a10835.easywol.R;

/**
 * Created by 10835 on 2018/5/2.
 */

public class XEditText extends AppCompatEditText implements View.OnFocusChangeListener,TextWatcher{
    private boolean hasFocus;
    private Drawable deleteDrawable;
    public XEditText(Context context) {
        super(context);
    }

    /**
     * 此构造方法会在Xml中定义控件的时候被调用
     * @param context
     * @param attrs
     */
    public XEditText(Context context, AttributeSet attrs) {
        this(context,attrs,android.R.attr.editTextStyle);
    }

    public XEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        deleteDrawable = getCompoundDrawables()[2];
        if (deleteDrawable == null){
            //R.drawable.icon_edit_delete
            deleteDrawable = getResources().getDrawable(R.drawable.icon_edit_delete);
        }
        deleteDrawable.setBounds(0,0,48,48);
        setDeleteDrawableVisible(deleteDrawable,false);
        addTextChangedListener(this);
        setOnFocusChangeListener(this);
    }

    public void setDeleteDrawableVisible(Drawable drawable,boolean visible){
        Drawable deleteDrawable = visible? drawable:null;
        setCompoundDrawables(getCompoundDrawables()[0],getCompoundDrawables()[1],deleteDrawable,getCompoundDrawables()[3]);
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP){
            if (getCompoundDrawables()[2] != null){
                if (event.getX()>getWidth()-getTotalPaddingRight()&&event.getX()<getWidth()-getPaddingRight()){
                    setText("");
                }
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (hasFocus){
            if (charSequence.length()>0){
                setDeleteDrawableVisible(deleteDrawable,true);
            }else {
                setDeleteDrawableVisible(deleteDrawable,false);
            }
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        this.hasFocus = b;
        if (hasFocus){
            setDeleteDrawableVisible(deleteDrawable,getText().length()>0);
        }else {
            setDeleteDrawableVisible(deleteDrawable,false);
        }
    }

    public void showSharkAnimotion(){
        TranslateAnimation animation = new TranslateAnimation(0,10,0,0);
        animation.setInterpolator(new CycleInterpolator(3));
        animation.setDuration(1000);
        startAnimation(animation);
    }

    public void setDeleteDrawable(){
        deleteDrawable = getResources().getDrawable(R.drawable.icon_edit_delete_1);
        deleteDrawable.setBounds(0,0,48,48);
        setDeleteDrawableVisible(deleteDrawable,false);
    }



}
