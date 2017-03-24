package com.example.dell.firebasedata;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements ValueEventListener{
    private TextView text;
    private EditText text1;
    private RadioButton rred,rblu;
    private Button btn;

    private FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    private DatabaseReference headingReference=firebaseDatabase.getReference();
    private DatabaseReference mChildreference=headingReference.child("heading");
    private DatabaseReference mFontcolor=headingReference.child("fontcolor");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text=(TextView)findViewById(R.id.textView);
        text1=(EditText)findViewById(R.id.editText3);
       rred=(RadioButton)findViewById(R.id.radioButton);
        rblu=(RadioButton)findViewById(R.id.radioButton2);
        btn=(Button)findViewById(R.id.button2);

       text.setText("massage appear here");
       btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String heading=text1.getText().toString();
                mChildreference.setValue(heading);
            }
        });

    } /*
protected void submitheading(View view){
    String heading=text1.getText().toString();
    mChildreference.setValue(heading);

} */
public void radiobotton(View view){
    switch (view.getId())
    {
        case R.id.radioButton:
            mFontcolor.setValue("red");
            break;
        case R.id.radioButton2:
            mFontcolor.setValue("blue");
    }
}

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        if (dataSnapshot.getValue(String.class) != null) {
            String key = dataSnapshot.getKey();
            if (key.equals("heading")) {
                String heading = dataSnapshot.getValue(String.class);
                text.setText(heading);
            } else if (key.equals("fontcolor")) {
                String color = dataSnapshot.getValue(String.class);

                if (color.equals("red")) {

                    text.setTextColor(ContextCompat.getColor(this,R.color.colorRed));
                    rred.setChecked(true);
                } else if (color.equals("blue")) {
                    text.setTextColor(ContextCompat.getColor(this,R.color.colorBlue));
                    rblu.setChecked(true);
                }
            }

        }
    }
        @Override
        public void onCancelled (DatabaseError databaseError){

        }

    @Override
    protected void onStart() {
            super.onStart();
            mChildreference.addValueEventListener(this);
            mFontcolor.addValueEventListener(this);


           }
  /*      @Override
    protected void onStart() {
        super.onStart();
        mChildreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String massage=dataSnapshot.getValue(String.class);
                text.setText(massage);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
    }