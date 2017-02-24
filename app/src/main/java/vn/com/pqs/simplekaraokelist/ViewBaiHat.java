package vn.com.pqs.simplekaraokelist;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.viethoa.models.AlphabetItem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import vn.com.pqs.adapter.BaiHatAdapter;
import vn.com.pqs.adapter.DiaDiemAdapter;
import vn.com.pqs.adapter.RecyclerViewAdapter;
import vn.com.pqs.model.BaiHat;

public class ViewBaiHat extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    public ArrayList<BaiHat> dsBaiHatTimKiem;
    public ArrayList<BaiHat> dsDiadiemTimkiem;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    public ViewPager viewPager;
    public MaterialSearchView searchView;
    private Menu menu;
    public ArrayList<BaiHat> dsBaihat;
    public ArrayList<BaiHat> dsBaihatCa;
    public ArrayList<BaiHat> dsBaihatMc;
    public ArrayList<BaiHat> dsDiadiem;
    public ArrayList<BaiHat> dsYeuthich;
    public ArrayList<BaiHat> dsYeuthichCa;
    public ArrayList<BaiHat> dsYeuthichMc;
    public BaiHatAdapter baiHatAdapter;
    public RecyclerViewAdapter recyclerViewAdapter;
    public RecyclerViewAdapter recyclerViewAdapterCa;
    public RecyclerViewAdapter recyclerViewAdapterMc;
    public RecyclerViewAdapter ytRecle;
    public RecyclerViewAdapter ytRecleCa;
    public RecyclerViewAdapter ytRecleMc;
    public DiaDiemAdapter diaDiemAdapter;
    public RecyclerView rv;
    public String listKa;

      private int[] tabIcons = {R.drawable.ds, R.drawable.yt, R.drawable.location,R.drawable.tt
    };
    public static String DATABASE_NAME = "libKara.sqlite";
    String DB_PATH_SUFFIX = "/databases/";
    public static SQLiteDatabase database = null;
    ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
    public List<AlphabetItem> mAlphabetItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        dsYeuthich = new ArrayList<>();
        dsYeuthichCa = new ArrayList<>();
        dsYeuthichMc = new ArrayList<>();
        dsBaihat = new ArrayList<>();
        dsBaihatCa = new ArrayList<>();
        dsBaihatMc = new ArrayList<>();
        dsDiadiem = new ArrayList<>();
        listKa = "A";
        addDatabase();
        addDsBaiHat();
        addDsBaiHatCaLi();
        addDsBaiHatMc();
        addDsDiadiem();
        initialiseData();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        searchView = (MaterialSearchView) findViewById(R.id.search_view);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
 if(position==0){searchView.setVisibility(View.VISIBLE);
     MenuItem item2 = menu.findItem(R.id.action_search);
     item2.setIcon(R.drawable.ic_action_action_search);
     item2.setVisible(true);

     toolbar.setTitle("Danh Sách Bài Hát");
      }

 if(position==1){
     searchView.setVisibility(View.INVISIBLE);
     MenuItem item2 = menu.findItem(R.id.action_search);
     item2.setVisible(false);
     toolbar.setTitle("Bài Hát Yêu Thích");
     }

if(position==2){//searchView.setVisibility(View.INVISIBLE);
    searchView.setVisibility(View.VISIBLE);
    MenuItem item2 = menu.findItem(R.id.action_search);
    item2.setIcon(R.drawable.ic_action_action_search);
    item2.setVisible(true);
    toolbar.setTitle("Địa điểm âm nhạc");
}
 if(position==3){searchView.setVisibility(View.INVISIBLE);
                    MenuItem item2 = menu.findItem(R.id.action_search);
                    item2.setVisible(false);
                    toolbar.setTitle("Hướng dẫn sử dụng");
                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        setupTabIcons();
    }

    private void addDsDiadiem() {
        dsDiadiem.clear();
        database = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        Cursor cursor = database.query("diadiem", null, null, null, null, null, null);
        while (cursor.moveToNext()) {

            String kId = cursor.getString(0);
            String kName = cursor.getString(1);
            String kFullAd = cursor.getString(2);
            String kWard = cursor.getString(3);
            String kDistrict = cursor.getString(4);
            String kProvince =cursor.getString(5);
            String kPrice =cursor.getString(6);
            int kPhone = cursor.getInt(7);
            boolean blthich = false;
            BaiHat diaDiem = new BaiHat();
            diaDiem.setTxtms(kId);
            diaDiem.setTenBh(kName);
            diaDiem.setTxtcs(kFullAd);
            diaDiem.setTxtLr(kWard);
            diaDiem.setTxtLrNA(kDistrict);
            diaDiem.setTenBhNA(kProvince);
            diaDiem.setTenviettat(kPrice);
            diaDiem.setImg(kPhone);
            diaDiem.setThich(blthich);
            dsDiadiem.add(diaDiem);

        }
        cursor.close();
    }

//    private void initialiseUI() {
//       mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//       mRecyclerView.setAdapter(new RecyclerViewAdapter(mDataArray));
//
//        fastScroller.setRecyclerView(mRecyclerView);
//        fastScroller.setUpAlphabet(mAlphabetItems);
//
//    }

    private void initialiseData() {
        //mDataArray.addAll(dsBaihat);
        mAlphabetItems = new ArrayList<>();
        List<String> strAlphabets = new ArrayList<>();
        for (int i = 0; i < dsBaihat.size(); i++) {
            String name = dsBaihat.get(i).getTenviettat().toString();
            if (name == null || name.trim().isEmpty())
                continue;

            String word = name.substring(0, 1);

                if (!strAlphabets.contains(word)) {
                strAlphabets.add(word);
                mAlphabetItems.add(new AlphabetItem(i, word, false));

            }
        }

    }


    private void addDsBaiHat() {
        dsBaihat.clear();
        dsYeuthich.clear();

        database = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        Cursor cursor = database.query("ArirangSongList", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String mabh = cursor.getString(0);
            String tenbh = cursor.getString(1);
            String casi = cursor.getString(3);
            String lr = cursor.getString(2);
            String bhNA = cursor.getString(7);
            String lrNA =cursor.getString(6);// cursor.getString(7);
            String tenviettat =cursor.getString(8);// cursor.getString(8);
            int yeuthich = cursor.getInt(5);
            boolean blthich;
            if (yeuthich == 0) {
                blthich = false;
            } else {
                blthich = true;
            }
            BaiHat baiHat = new BaiHat();
            baiHat.setTxtms(mabh);
            baiHat.setTenBh(tenbh);
            baiHat.setTxtcs(casi);
            baiHat.setTxtLr(lr);
            baiHat.setTenBhNA(bhNA);
            baiHat.setTxtLrNA(lrNA);
            baiHat.setTenviettat(tenviettat);
            baiHat.setThich(blthich);
            if (blthich == true) {
                baiHat.setImg(R.drawable.added);
                dsYeuthich.add(baiHat);
            }
            if (blthich == false) {
                baiHat.setImg(R.drawable.addfav);
            }
            dsBaihat.add(baiHat);

        }

        cursor.close();

    }
    private void addDsBaiHatCaLi() {
        dsYeuthichCa.clear();
        dsBaihatCa.clear();
        database = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        Cursor cursor = database.query("California", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String mabh = cursor.getString(0);
            String tenbh = cursor.getString(1);
            String casi = cursor.getString(3);
            String lr = cursor.getString(2);
            String bhNA = cursor.getString(7);
            String lrNA =cursor.getString(6);// cursor.getString(7);
            String tenviettat =cursor.getString(8);// cursor.getString(8);
            int yeuthich = cursor.getInt(5);
            boolean blthich;
            if (yeuthich == 0) {
                blthich = false;
            } else {
                blthich = true;
            }
            BaiHat baiHat = new BaiHat();
            baiHat.setTxtms(mabh);
            baiHat.setTenBh(tenbh);
            baiHat.setTxtcs(casi);
            baiHat.setTxtLr(lr);
            baiHat.setTenBhNA(bhNA);
            baiHat.setTxtLrNA(lrNA);
            baiHat.setTenviettat(tenviettat);
            baiHat.setThich(blthich);
            if (blthich == true) {
                baiHat.setImg(R.drawable.added);
                dsYeuthichCa.add(baiHat);
            }
            if (blthich == false) {
                baiHat.setImg(R.drawable.addfav);
            }
            dsBaihatCa.add(baiHat);

        }

        cursor.close();

    }
    private void addDsBaiHatMc() {
        dsBaihatMc.clear();
        dsYeuthichMc.clear();

        database = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        Cursor cursor = database.query("MusicCore", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String mabh = cursor.getString(0);
            String tenbh = cursor.getString(1);
            String casi = cursor.getString(3);
            String lr = cursor.getString(2);
            String bhNA = cursor.getString(7);
            String lrNA =cursor.getString(6);// cursor.getString(7);
            String tenviettat =cursor.getString(8);// cursor.getString(8);
            int yeuthich = cursor.getInt(5);
            boolean blthich;
            if (yeuthich == 0) {
                blthich = false;
            } else {
                blthich = true;
            }
            BaiHat baiHat = new BaiHat();
            baiHat.setTxtms(mabh);
            baiHat.setTenBh(tenbh);
            baiHat.setTxtcs(casi);
            baiHat.setTxtLr(lr);
            baiHat.setTenBhNA(bhNA);
            baiHat.setTxtLrNA(lrNA);
            baiHat.setTenviettat(tenviettat);
            baiHat.setThich(blthich);
            if (blthich == true) {
                baiHat.setImg(R.drawable.added);
                dsYeuthichMc.add(baiHat);
            }
            if (blthich == false) {
                baiHat.setImg(R.drawable.addfav);
            }
            dsBaihatMc.add(baiHat);

        }

        cursor.close();

    }


    private void addDatabase() {
        File dbFile = getDatabasePath(DATABASE_NAME);
        if (!dbFile.exists()) {
            try {
                CopyDataBaseFromAsset();
                Toast.makeText(this, "Sao chép CSDL vào hệ thống thành công", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private void CopyDataBaseFromAsset() {
        try {
            InputStream myInput = getAssets().open(DATABASE_NAME);
            String outFileName = layDuongDanLuuTru();
            File f = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if (!f.exists()) {
                f.mkdir();
            }
            OutputStream myOutput = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (Exception ex) {
            Log.e("Loi_SaoChep", ex.toString());
        }
    }

    private String layDuongDanLuuTru() {
        return getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        this.menu = menu;
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
    }

    private void setupViewPager(ViewPager viewPager) {

        adapter.addFrag(new DanhSach(), "ZANHSÁCH");
        adapter.addFrag(new YeuThich(), "  YÊUTHÍCH");
        adapter.addFrag(new Diadiem(), "  DiaDiem");
        adapter.addFrag(new LienHe(), "  LIÊNHỆ");

        viewPager.setAdapter(adapter);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public int getItemPosition(Object object) {
            notifyDataSetChanged();
            return POSITION_NONE;
        }

        @Override
        public Fragment getItem(int position) {




            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
            //return mFragmentTitleList.get(position);
        }

    }
      public static String unAccent(String s) {
            String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
            Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
          return pattern.matcher(temp).replaceAll("").replaceAll("Đ", "D").replaceAll("đ", "d");
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
        public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            listKa = "A";

            adapter.addFrag(new DanhSach(), "ZANHSÁCH");
            adapter.addFrag(new YeuThich(), "  YÊUTHÍCH");
            viewPager.setAdapter(adapter);
            viewPager.setCurrentItem(0);


        } else if (id == R.id.nav_gallery) {
            listKa = "C";
            adapter.addFrag(new DanhSach(), "ZANHSÁCH");
            adapter.addFrag(new YeuThich(), "  YÊUTHÍCH");
            viewPager.setAdapter(adapter);
            viewPager.setCurrentItem(0);


        } else if (id == R.id.nav_slideshow) {
            listKa = "M";
            adapter.addFrag(new DanhSach(), "ZANHSÁCH");
            adapter.addFrag(new YeuThich(), "  YÊUTHÍCH");
            viewPager.setAdapter(adapter);
            viewPager.setCurrentItem(0);

        } else if (id == R.id.nav_manage) {
            Toast.makeText(ViewBaiHat.this,"Camera",Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_share) {
            Toast.makeText(ViewBaiHat.this,"Camera",Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_send) {
            Toast.makeText(ViewBaiHat.this,"Camera",Toast.LENGTH_SHORT).show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
   }
