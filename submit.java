package co.shrey.fireapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;


public class submit extends AppCompatActivity {
    private static final String TAG = "submit";
    private FirebaseDatabase database = FirebaseDatabase.getInstance();;
    private DatabaseReference rootref = database.getReference();
    private DatabaseReference childref = rootref.getRef();
    ArrayList<UserInformation> list;
    ArrayAdapter <UserInformation> adapter;
    UserInformation userInfo;
    TextView name_tv,address_tv,age_tv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.submit_layout);
         name_tv = (TextView) findViewById(R.id.name);
         address_tv = (TextView) findViewById(R.id.address);
         age_tv = (TextView) findViewById(R.id.age);
    }
    @Override
    protected void onStart() {
        super.onStart();
        list =new ArrayList<>();
        adapter = new ArrayAdapter<UserInformation>(this,R.layout.submit_layout,R.id.userinfo,list);
        childref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                     userInfo = dataSnapshot.getValue(UserInformation.class);
                    /*Log.e("TAG",name.getAddress()+name.getAge()+name.getName());*/
                updateui();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void updateui() {
        address_tv.setText(userInfo.getAddress());
        age_tv.setText(userInfo.getAge());
        name_tv.setText(userInfo.getName());
    }
}
