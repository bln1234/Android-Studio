package com.example.todo_list;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;

public class ToDoListItemView extends AppCompatTextView {
    private Paint marginPaint;
    private Paint linePaint;
    private int paperColor;
    private float margin;

    public ToDoListItemView(Context context, AttributeSet ats, int ds) {
        super(context,ats,ds);
        init();
    }
    public ToDoListItemView(Context context){
        super(context);
        init();
    }
    public ToDoListItemView(Context context,AttributeSet ats){
        super(context,ats);
        init();
    }
    public void init(){
        Resources myResources = getResources();
        marginPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        marginPaint.setColor(myResources.getColor(R.color.notepad_margin));
        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(myResources.getColor(R.color.notepad_lines));
        paperColor = myResources.getColor(R.color.notepad_paper);
        margin = myResources.getDimension(R.dimen.notepad_margin);
    }
    @Override
    public void onDraw(Canvas canvas){
        canvas.drawLine(1,0,1,getMeasuredHeight(),linePaint);
        canvas.drawLine(0,getMeasuredHeight(),getMeasuredWidth(),getMeasuredHeight(),linePaint);
        canvas.drawLine(margin,0,margin,getMeasuredHeight(),marginPaint);
        canvas.save();
        canvas.translate(margin,0);

        super.onDraw(canvas);
        canvas.restore();
    }
}
