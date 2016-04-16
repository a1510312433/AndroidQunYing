package com.example.administrator.androidqunying;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private MyListView lv;
    private TextView txt;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    //测试push是不是真的能用了
    //    private ListView lv ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

//        lv = (ListView)findViewById(R.id.listView);
        lv = ((MyListView) findViewById(R.id.listView));
        lv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        System.out.println("ACTION_UP");
                        break;
                    case MotionEvent.ACTION_DOWN:
                        System.out.println("ACTION_DOWN");
                        break;
                    case MotionEvent.ACTION_MOVE:
//                        System.out.println("ACTION_MOVE");
                        break;
                }
                return false;
            }
        });

        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case SCROLL_STATE_FLING:
                        System.out.println("SCROLL_STATE_FLING");
                        break;
                    case SCROLL_STATE_IDLE:
                        System.out.println("SCROLL_STATE_IDLE");
                        break;
                    case SCROLL_STATE_TOUCH_SCROLL:
                        System.out.println("SCROLL_STATE_TOUCH_SCROLL");
                        break;
                }
            }

            @Override

            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount > 0) {
                    System.out.println("到达了最后一行");
                }

                if (firstVisibleItem > lastvisibleitemposition) {
                    System.out.println("在上滑");
                } else if (firstVisibleItem < lastvisibleitemposition) {
                    System.out.println("在下滑");
                }
                lastvisibleitemposition = firstVisibleItem;

                System.out.println("lastposition" + lv.getLastVisiblePosition());
                System.out.println("firstposition" + lv.getFirstVisiblePosition());
            }
        });

        lists = new ArrayList<>();
        for(int i=0;i<100;i++){
            lists.add("listposition"+i);
        }

        final myAdapter adapter = new myAdapter(lists);

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 1){
                    lists.clear();
                    adapter.notifyDataSetChanged();
                }

            }
        });

        txt = ((TextView) findViewById(R.id.text));
        lv.setEmptyView(txt);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<100;i++){
                    lists.add("listPosition"+i);
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    int lastvisibleitemposition;
    List<String> lists;
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    private class myAdapter extends BaseAdapter {
        LayoutInflater inflater;
        List<String> list;
        public myAdapter(List<String> list) {
            inflater = LayoutInflater.from(MainActivity.this);
            this.list = list;
        }

        @Override
        public int getCount() {
            if(list != null && list.size()>0){
                return list.size();
            }else{
                return 0;
            }

        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.item, null);
                holder.txt = ((TextView) convertView.findViewById(R.id.textView2));
                convertView.setTag(holder);
            } else {
                holder = ((ViewHolder) convertView.getTag());
            }
            if(position%2 == 0){
                holder.txt.setBackgroundColor(0xffe63232);
                holder.txt.setTextColor(0xffffffff);
            }else{
                holder.txt.setBackgroundColor(0xffffffff);
                holder.txt.setTextColor(0xff000000);
            }
            holder.txt.setText("---" + position + "---"+list.get(position));
            return convertView;
        }
    }

    private class ViewHolder {
        private TextView txt;
    }


    private void testgit(){
        //本方法只用于测试git
    }
}