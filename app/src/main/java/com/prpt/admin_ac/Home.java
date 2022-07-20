package com.prpt.admin_ac;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    ArrayList<Signup_Item>list;
    BaseAdapter ba;
    ListView l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        l=findViewById(R.id.list);
        list = new ArrayList<>();


        ba = new BaseAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                if(view==null)
                {
                    view=getLayoutInflater().inflate(R.layout.listiem, null);
                }
                TextView name=view.findViewById(R.id.home_name);
                TextView email=view.findViewById(R.id.home_email);
                TextView id=view.findViewById(R.id.home_id);
                TextView batch=view.findViewById(R.id.home_batch);
                TextView dept=view.findViewById(R.id.home_dept);
                TextView session=view.findViewById(R.id.home_session);
                email.setText(list.get(i).getEmail());
                id.setText(list.get(i).getId());
                batch.setText(list.get(i).getBatch());
                session.setText(list.get(i).getSession());
                dept.setText(list.get(i).getDept());
                name.setText(list.get(i).getFname()+" "+list.get(i).getLname());
                return view;
            }
        };
        l.setAdapter(ba);
        DatabaseReference dr= FirebaseDatabase.getInstance().getReference("signup");
        dr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list=new ArrayList<>();
                for(DataSnapshot ds:snapshot.getChildren()){
                    list.add(ds.getValue(Signup_Item.class));
                }
                ba.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}