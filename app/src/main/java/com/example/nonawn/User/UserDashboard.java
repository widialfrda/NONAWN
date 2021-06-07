package com.example.nonawn.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.nonawn.HelperClasses.HomeAdapter.FeaturedAdapter;
import com.example.nonawn.HelperClasses.HomeAdapter.FeaturedHelperClass;
import com.example.nonawn.HelperClasses.HomeAdapter.KategoriAdapter;
import com.example.nonawn.HelperClasses.HomeAdapter.KategoriHelperClass;
import com.example.nonawn.HelperClasses.HomeAdapter.PromoAdapter;
import com.example.nonawn.HelperClasses.HomeAdapter.PromoHelperClass;
import com.example.nonawn.HelperClasses.HomeAdapter.TestiAdapter;
import com.example.nonawn.HelperClasses.HomeAdapter.TestiHelperClass;
import com.example.nonawn.R;
import com.google.android.material.navigation.NavigationView;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.ArrayList;

public class UserDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    static final float END_SCALE = 0.7f;

    RecyclerView  featuredRecycler, promoRecycler, kategoriRecycler, testiRecycler;
    RecyclerView.Adapter adapter;
    private GradientDrawable gradient1, gradient2, gradient3, gradient4;
    ImageView menuIcon;
    LinearLayout contentView;

    //Drawer Menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_dashboard);

        //Hooks untuk setting agar elemen memasukkan fitur recycler ke dalam user dashboard
        featuredRecycler = findViewById(R.id.featured_recycler);
        kategoriRecycler = findViewById(R.id.kategori_recycler);
        promoRecycler = findViewById(R.id.promo_recycler);
        testiRecycler = findViewById(R.id.testi_recycler);
        menuIcon = findViewById(R.id.menu_icon);
        contentView = findViewById(R.id.content);


        //Menu Hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_menu_view);


        //memanggil metode
        featuredRecycler();
        kategoriRecycler();
        promoRecycler();
        testiRecycler();
        navigationDrawer();
    }

    //Navigation Drawer Functions
    private void navigationDrawer() {
        //Navigation Drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.bottom_nav_home);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        animationNavigationDrawer();
    }

    private void animationNavigationDrawer() {

        drawerLayout.setScrimColor(getResources().getColor(R.color.whitegrey));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {


                //Scale the view based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                //Translate the view, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        }
        );
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.bottom_nav_tentang:
                startActivity(new Intent(getApplicationContext(),Tentang.class));
                break;
        }
        return true;
    }

    private void kategoriRecycler(){

        gradient2 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xfff8f8f8, 0xfff8f8f8});
        gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffffffff, 0xffffffff});

        ArrayList<KategoriHelperClass> kategoriLocations = new ArrayList<>();

        kategoriLocations.add(new KategoriHelperClass(gradient1,R.drawable.gurih, "Gurih"));
        kategoriLocations.add(new KategoriHelperClass(gradient2,R.drawable.gurih_jeruk, "Gurih Jeruk"));
        kategoriLocations.add(new KategoriHelperClass(gradient1,R.drawable.keju, "Keju"));
        kategoriLocations.add(new KategoriHelperClass(gradient2,R.drawable.bbq, "BBQ"));
        kategoriLocations.add(new KategoriHelperClass(gradient1,R.drawable.lada_hitam, "Lada Hitam"));
        kategoriLocations.add(new KategoriHelperClass(gradient2,R.drawable.seaweed, "Rumput Laut"));
        kategoriLocations.add(new KategoriHelperClass(gradient1,R.drawable.cabe_ijo, "Cabe Ijo"));
        kategoriLocations.add(new KategoriHelperClass(gradient2,R.drawable.cabe_merah, "Cabe Merah"));
        kategoriLocations.add(new KategoriHelperClass(gradient1,R.drawable.merah_jeruk, "Merah Jeruk"));
        kategoriLocations.add(new KategoriHelperClass(gradient2,R.drawable.ijo_jeruk, "Ijo Jeruk"));

        kategoriRecycler.setHasFixedSize(true);
        kategoriRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter = new KategoriAdapter(kategoriLocations);
        kategoriRecycler.setAdapter(adapter);
    }

    private void featuredRecycler(){

        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeaturedHelperClass> featuredLocations = new ArrayList<>();

        featuredLocations.add(new FeaturedHelperClass(R.drawable.merah_jeruk, "Merah Jeruk", "Kombinasi cabe merah dan daun jeruk membuat varian ini selalu menduduki posisi pertama karena kesegarannya!"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.gurih, "gurih", "Rasa kerupuk kulit yang disertai rasa gurih yang tidak mencolok membuat lidah tidak berhenti makan!"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.cabe_merah, "Cabe Merah", "Ada yang merah, tapi bukan delima! Yap, cabe merah yang disertai rasa gurih. Rasa gurih pedasnya bikin ketagihan!"));

        adapter = new FeaturedAdapter(featuredLocations);
        featuredRecycler.setAdapter(adapter);
    }

    private void promoRecycler(){

        ArrayList<PromoHelperClass> promoLocations = new ArrayList<>();

        promoLocations.add(new PromoHelperClass(R.drawable.diskon, "BUY 3 GET 1", "Hanya di minggu ini, beli 3 dapat 1 bungkus kerupuk kulit ukuran 80gr"));
        promoLocations.add(new PromoHelperClass(R.drawable.promo99, "PROMO 9.9", "Special discount! Beli 9 ukuran 80gr cuma 99k!"));
        promoLocations.add(new PromoHelperClass(R.drawable.sharetesti, "NONAWN HAUL", "Hanya share video, bisa dapat 1 bungkus ukuran 80gr gratis!"));
        promoLocations.add(new PromoHelperClass(R.drawable.boci, "BOCI", "CUMA 35K! Kamu udah bisa dapetin bundle spesial Bakso Aci + Kerupuk Kulit"));
        promoLocations.add(new PromoHelperClass(R.drawable.taichan, "TAICHAN", "CUMA 35K! Kamu udah bisa dapetin bundle spesial Bakso Aci + Kerupuk Kulit"));

        promoRecycler.setHasFixedSize(true);
        promoRecycler.setLayoutManager((new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)));
        adapter = new PromoAdapter(promoLocations);
        promoRecycler.setAdapter(adapter);
    }

    private void testiRecycler() {

        ArrayList<TestiHelperClass> testiLocations = new ArrayList<>();

        testiLocations.add(new TestiHelperClass(R.drawable.user,5,"Merah Jeruk","enakkk bgt mo nangiss!","walfreeday"));
        testiLocations.add(new TestiHelperClass(R.drawable.user2,5,"Gurih","enakkk bgt mo nangiss!","farrasch"));
        testiLocations.add(new TestiHelperClass(R.drawable.user3,5,"Gurih Jeruk","enakkk bgt mo nangiss!","widialfrda"));

        //set layout ke userdashboard
        testiRecycler.setHasFixedSize(true);
        testiRecycler.setLayoutManager((new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)));
        adapter = new TestiAdapter(testiLocations);
        testiRecycler.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerVisible(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else
            super.onBackPressed();
    }
}


























