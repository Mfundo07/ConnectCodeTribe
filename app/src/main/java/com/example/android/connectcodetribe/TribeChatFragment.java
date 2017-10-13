package com.example.android.connectcodetribe;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Admin on 10/4/2017.
 */

public class TribeChatFragment extends Fragment {

    ListView userList;
    TextView noUsersText;
    ArrayList<String> al = new ArrayList<>();
    int totalUsers = 0;
    ProgressDialog pd;






    public TribeChatFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.users,container,false);
        userList = rootView.findViewById(R.id.usersList);
        noUsersText = rootView.findViewById(R.id.nousersText);

        pd = new ProgressDialog(getActivity());
        pd.setMessage("Loading...");
        pd.show();
        String url = "https://codetribeconnect.firebaseio.com/users.json";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                doOnSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("" + error);
            }

        });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request);
        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                UserDetails.chatWith = al.get(i);
                startActivity(new Intent(getActivity(), ChatModel.class));
            }
        });
        return rootView;
    }
    public void doOnSuccess(String s){
        try{
            JSONObject obj = new JSONObject(s);
            Iterator i = obj.keys();
            String key = "";

            while (i.hasNext()){
                key = i.next().toString();
                if (!key.equals(UserDetails.username)){
                    al.add(key);
                }
                totalUsers++;
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        if (totalUsers <= 1){
            noUsersText.setVisibility(View.VISIBLE);
            userList.setVisibility(View.GONE);
        }
        else{
            noUsersText.setVisibility(View.GONE);
            userList.setVisibility(View.VISIBLE);
            userList.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,al));
        }
        pd.dismiss();

    }
}