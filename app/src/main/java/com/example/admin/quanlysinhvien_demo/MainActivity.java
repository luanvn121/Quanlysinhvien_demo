package com.example.admin.quanlysinhvien_demo;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    StudentModify studentModify;
    StudentAdapter adapter;
    ListView lvDS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lvDS=(ListView)findViewById(R.id.lvDS);
        studentModify =new StudentModify(this);

    }
    public void display(){
         adapter = new StudentAdapter(this, studentModify.getStudentList(), true);
        lvDS.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_new) {
            final Dialog dialog = new Dialog(this);
            dialog.setTitle("Mới");
            dialog.setContentView(R.layout.show_dilog);
            final EditText edtName, edtEmail, edtAge, edtSdt;
            Button btnCancel, btnInsert;

            edtAge = (EditText) dialog.findViewById(R.id.edtAge);
            edtEmail = (EditText) dialog.findViewById(R.id.edtEmail);
            edtName = (EditText) dialog.findViewById(R.id.edtName);
            edtSdt = (EditText) dialog.findViewById(R.id.edtSdt);
            btnCancel = (Button) dialog.findViewById(R.id.btnCancel);
            btnInsert = (Button) dialog.findViewById(R.id.btnSave);

            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            btnInsert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Student student = new Student(Integer.parseInt(edtAge.getText().toString()),edtName.getText().toString(), edtEmail.getText().toString(),  Integer.parseInt(edtSdt.getText().toString()));
                    studentModify.insert(student);
                   // display();
                    dialog.dismiss();
                }
            });

            dialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.context_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        Cursor cursor=(Cursor) lvDS.getItemAtPosition(info.position);
        final int id = cursor.getInt(0);

        switch (item.getItemId()){
            case R.id.action_delete:
                studentModify.delete(id);
                Toast.makeText(this,"Xóa thành công", Toast.LENGTH_SHORT).show();
                display();
                return true;
            case R.id.action_edit:
                final Dialog dialog=new Dialog(this);
                dialog.setTitle("Cập nhật sinh viên");
                dialog.setContentView(R.layout.show_dilog);
                final EditText edtName, edtEmail, edtAge,edtSdt;
                Button btnCancel, btnUpdate;

                edtAge=(EditText) dialog.findViewById(R.id.edtAge);
                edtEmail=(EditText) dialog.findViewById(R.id.edtEmail);
                edtName=(EditText) dialog.findViewById(R.id.edtName);
                edtSdt=(EditText) dialog.findViewById(R.id.edtSdt);
                btnCancel=(Button) dialog.findViewById(R.id.btnCancel);
                btnUpdate=(Button) dialog.findViewById(R.id.btnSave);

                Student student=studentModify.fetchStudentByID(id);
                edtAge.setText(String.valueOf(student.getAge()));
                edtEmail.setText(student.getEmail());
                edtName.setText(student.getName());

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Student student=new Student(id,edtName.getText().toString(),edtEmail.getText().toString(), Integer.parseInt(edtAge.getText().toString(),Integer.parseInt(edtSdt.getText().toString())));
                        studentModify.update(student);
                        display();
                        dialog.dismiss();
                    }
                });

                dialog.show();
                return true;
        }

        return super.onContextItemSelected(item);
    }

}
