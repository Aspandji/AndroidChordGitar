package pandji.com.chordgitar;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import pandji.com.chordgitar.model.users;

public class Signup extends AppCompatActivity {

    EditText edtphone, edtname, edtpassword;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        edtname = (EditText) findViewById(R.id.edtname);
        edtpassword = (EditText) findViewById(R.id.edtpassword);
        edtphone = (EditText) findViewById(R.id.edtphone);

        btnSignUp = (Button) findViewById(R.id.btnSignUp);

        //init firebase
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_User = database.getReference("User");

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog mDialog = new ProgressDialog(Signup.this);
                mDialog.setMessage("Please Watiting");
                mDialog.show();

                table_User.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(edtphone.getText().toString()).exists()){
                            mDialog.dismiss();
                            Toast.makeText(Signup.this,"Berhasil Terdaftar", Toast.LENGTH_SHORT).show();
                        }else{
                            mDialog.dismiss();
                            users user = new users(edtname.getText().toString(), edtpassword.getText().toString());
                            table_User.child(edtphone.getText().toString()).setValue(user);
                            Toast.makeText(Signup.this,"Sign Up sukses", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
