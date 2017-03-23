package vn.com.pqs.simplekaraokelist;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
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

import vn.com.pqs.adapter.AddressAdapter;
import vn.com.pqs.adapter.RecyclerViewAdapter;
import vn.com.pqs.adapter.SongAdapter;
import vn.com.pqs.model.Address;
import vn.com.pqs.model.Song;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    public ArrayList<Song> dsBaiHatTimKiem;
    public ArrayList<Address> dsDiadiemTimkiem;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    public ViewPager viewPager;
    public MaterialSearchView searchView;
    private Menu menu;
    public ArrayList<Song> dsBaihat;
    public ArrayList<Song> dsBaihatCa;
    public ArrayList<Address> dsDiadiem;
    public ArrayList<Song> dsYeuthich;
    public ArrayList<Song> dsYeuthichCa;
    public SongAdapter baiHatAdapter;
    public RecyclerViewAdapter recyclerViewAdapter;
    public RecyclerViewAdapter recyclerViewAdapterCa;
    public RecyclerViewAdapter ytRecle;
    public RecyclerViewAdapter ytRecleCa;
    public RecyclerViewAdapter rvTimKiem;
    public AddressAdapter diaDiemAdapter;
    public String listKa;
    private AdView mAdView;
public AdRequest adRequest;
    private int[] tabIcons = {R.drawable.ds, R.drawable.yt, R.drawable.location,R.drawable.tt
    };
    public static String DATABASE_NAME = "FullDb.sqlite";
    String DB_PATH_SUFFIX = "/databases/";
    public static SQLiteDatabase database = null;
    ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
    public List<AlphabetItem> mAlphabetItems;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        dsYeuthich = new ArrayList<>();
        dsYeuthichCa = new ArrayList<>();
        dsBaihat = new ArrayList<>();
        dsBaihatCa = new ArrayList<>();
        dsDiadiem = new ArrayList<>();
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        listKa = "A";
        addDatabase();
        addDsBaiHat();
       addDsDiadiem();
        addDsBaiHatCaLi();
        initialiseData();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

                MenuItem item = menu.findItem(R.id.action_search);
                //viewPager.getAdapter().notifyDataSetChanged();
                if(position==0){searchView.setVisibility(View.VISIBLE);
                    item.setIcon(R.drawable.ic_action_action_search);
                    item.setVisible(true);
                    toolbar.setTitle("Danh Sách Bài Hát");
                }

                if(position==1){
                    searchView.setVisibility(View.INVISIBLE);
                    searchView.clearFocus();
                    searchView.closeSearch();
                    toolbar.setTitle("Bài Hát Yêu Thích");

                }

                if(position==2){//searchView.setVisibility(View.INVISIBLE);
                    searchView.setVisibility(View.VISIBLE);

                    item.setIcon(R.drawable.ic_action_action_search);
                    item.setVisible(true);
                    toolbar.setTitle("Địa điểm âm nhạc");
                }
                if(position==3){searchView.setVisibility(View.INVISIBLE);
                    item.setIcon(null);
                    item.setVisible(false);
                    searchView.clearFocus();
                    searchView.closeSearch();

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




    public void addDsDiadiem() {
        dsDiadiem.clear();
        database = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        Cursor cursor = database.query("Address", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String namena = cursor.getString(2);
            String ad = cursor.getString(3);
            String adna = cursor.getString(4);
            String price = cursor.getString(5);
            String phone = cursor.getString(6);

            Address address = new Address();
            address.setId(id);
            address.setName(name);
            address.setNamena(namena);
            address.setAd(ad);
            address.setAdna(adna);
            address.setPrice(price);
            address.setPhone(phone);
            dsDiadiem.add(address);

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
            String name = dsBaihat.get(i).getSmn().toString();
            if (name == null || name.trim().isEmpty())
                continue;

            String word = name.substring(0, 1);

            if (!strAlphabets.contains(word)) {
                strAlphabets.add(word);
                mAlphabetItems.add(new AlphabetItem(i, word, false));

            }
        }

    }
    public void addDsBaiHat() {
        dsBaihat.clear();
        dsYeuthich.clear();
        database = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        Cursor cursor = database.query("Arirang", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            String vol = cursor.getString(1);
            String mn = cursor.getString(2);
            String mnna = cursor.getString(3);
            String smn = cursor.getString(4);
            String singer = cursor.getString(5);
            String singerna = cursor.getString(6);
            String slyric = cursor.getString(7);
            String lyric = cursor.getString(8);
            String lyricna = cursor.getString(9);
            String composer = cursor.getString(10);
            String composerna = cursor.getString(11);
            int like = cursor.getInt(12);
            boolean blthich;
            if (like == 0) {
                blthich = false;
            } else {
                blthich = true;
            }
            Song song = new Song();
            song.setId(id);
            song.setVol(vol);
            song.setMn(mn);
            song.setMnna(mnna);
            song.setSmn(smn);
            song.setSinger(singer);
            song.setSingerna(singerna);
            song.setSlyric(slyric);
            song.setLyric(lyric);
            song.setLyricna(lyricna);
            song.setComposer(composer);
            song.setComposerna(composerna);
            song.setLike(blthich);
            if (blthich == true) {
                song.setImg(R.drawable.added);
                dsYeuthich.add(song);
            }
            if (blthich == false) {
                song.setImg(R.drawable.addfav);
            }
            dsBaihat.add(song);

        }

        cursor.close();

    }
    public void addDsBaiHatCaLi() {
        dsYeuthichCa.clear();
        dsBaihatCa.clear();
        database = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        Cursor cursor = database.query("Cali", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            String vol = cursor.getString(1);
            String mn = cursor.getString(2);
            String mnna = cursor.getString(3);
            String smn = cursor.getString(4);
            String singer = cursor.getString(5);
            String singerna = cursor.getString(6);
            String slyric = cursor.getString(7);
            String lyric = cursor.getString(8);
            String lyricna = cursor.getString(9);
            String composer = cursor.getString(10);
            String composerna = cursor.getString(11);
            int like = cursor.getInt(12);
            boolean blthich;
            if (like == 0) {
                blthich = false;
            } else {
                blthich = true;
            }
            Song song = new Song();
            song.setId(id);
            song.setVol(vol);
            song.setMn(mn);
            song.setMnna(mnna);
            song.setSmn(smn);
            song.setSinger(singer);
            song.setSingerna(singerna);
            song.setSlyric(slyric);
            song.setLyric(lyric);
            song.setLyricna(lyricna);
            song.setComposer(composer);
            song.setComposerna(composerna);
            song.setLike(blthich);
            if (blthich == true) {
                song.setImg(R.drawable.added);
                dsYeuthichCa.add(song);
            }
            if (blthich == false) {
                song.setImg(R.drawable.addfav);
            }
            dsBaihatCa.add(song);

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
    class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public int getItemPosition(Object object) {
            //return super.getItemPosition(object);
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


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_arirang) {
            listKa = "A";

            adapter.addFrag(new DanhSach(), "ZANHSÁCH");
            adapter.addFrag(new YeuThich(), "  YÊUTHÍCH");
            viewPager.setAdapter(adapter);
            viewPager.setCurrentItem(0);


        } else if (id == R.id.nav_cali) {
            listKa = "C";

            adapter.addFrag(new DanhSach(), "ZANHSÁCH");
            adapter.addFrag(new YeuThich(), "  YÊUTHÍCH");
            viewPager.setAdapter(adapter);
            viewPager.setCurrentItem(0);


        }
        else if (id == R.id.nav_share) {
            final String appPackageName = getPackageName();
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT,"Ứng Dụng Tìm Bài Hát, Địa Điểm Karaoke hay nhất ");
            sendIntent.putExtra(Intent.EXTRA_TEXT,"https://play.google.com/store/apps/details?id="+ appPackageName);
            sendIntent.setType("text/plain");
            Intent.createChooser(sendIntent,"Share via");
            startActivity(sendIntent);

        } else if (id == R.id.nav_send) {
            final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://developer?id=P%26Q+Solutions")));
            } catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=P%26Q+Solutions")));
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
