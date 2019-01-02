package pandji.com.chordgitar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import pandji.com.chordgitar.model.users;

public class Signin extends AppCompatActivity {

    EditText edtphone, edtpassword;
    Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        edtphone = (EditText) findViewById(R.id.edtphone);
        edtpassword = (EditText) findViewById(R.id.edtpassword);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);

        //init firebase
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_User = database.getReference("User");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog mDialog = new ProgressDialog(Signin.this);
                mDialog.setMessage("Please Watiting");
                mDialog.show();

                table_User.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(edtphone.getText().toString()).exists()) {
                            mDialog.dismiss();
                            users user = dataSnapshot.child(edtphone.getText().toString()).getValue(users.class);

                            if (user.getPassword().equals(edtpassword.getText().toString())) {
                                Intent home = new Intent(Signin.this, MainActivity2.class);
                                startActivity(home);
                                Toast.makeText(Signin.this, "Sign Sukses !", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Signin.this, "Sign Gagal !", Toast.LENGTH_SHORT).show();
                            }

                        } else
                            {
                                mDialog.dismiss();
                                Toast.makeText(Signin.this,"User Tidak Ada", Toast.LENGTH_SHORT).show();
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
