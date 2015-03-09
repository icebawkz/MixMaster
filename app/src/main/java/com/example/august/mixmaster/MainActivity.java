package com.example.august.mixmaster;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {
        public boolean age = false;

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    final Context context = this;
    private ShakeListener aShaker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setReminder();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final boolean firstrun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("firstrun", true);



        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Warning!");
        builder.setMessage("This app is intended only for use by people 21 years old and older. Are you old enough to view it?")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putBoolean("firstrun", false).commit();
                        dialog.cancel();
                    }
                })
                .setNegativeButton("no", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
        // Create the AlertDialog object and return it
       if (firstrun) {
           AlertDialog alertDialog = builder.create();
           alertDialog.show();

       }
        aShaker = new ShakeListener(this);
        aShaker.setOnShakeListener(new ShakeListener.OnShakeListener() {
            public void onShake() {

                Toast toast = Toast.makeText(MainActivity.this, "Shake to Discover Activated\nBe Refreshed", Toast.LENGTH_SHORT);
                TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                if( v != null) v.setGravity(Gravity.CENTER);
                toast.show();
                if( v != null) v.setGravity(Gravity.CENTER);
                Random r = new Random();
                //generates a number between 0 and 13
                int i1 = r.nextInt(13);
                final List<Recipes> drinks = new ArrayList<>();
                switch (i1) {
                    case 0:
                        String[] screwdriver = {"Vodka"};

                        drinks.add(new Recipes("Screwdriver", R.drawable.screwdriver, R.drawable.screwdriver_small, getString(R.string.screwdriver_desc), getString(R.string.screwdriver_recipe), screwdriver));
                    case 1:
                        String[] fuzzy = {"Peach Schnapps"};
                        drinks.add(new Recipes("Fuzzy Navel", R.drawable.fuzzy_navel, R.drawable.fuzzy_navel_small, getString(R.string.fuzzynavel_desc), getString(R.string.fuzzynavel_recipe), fuzzy));
                        break;
                    case 2:
                        String[] cooler = {"Vodka", "Triple Sec"};
                        drinks.add(new Recipes("Cactus Cooler", R.drawable.cactus_cooler, R.drawable.cactus_cooler_small, getString(R.string.cactuscooler_desc), getString(R.string.cactuscooler_recipe), cooler));
                        break;
                    case 4:
                        String[] Rumncoke = {"Rum"};
                        drinks.add(new Recipes("Rum and Coke", R.drawable.rum, R.drawable.rum_small, getString(R.string.rum_coke_desc), getString(R.string.rum_coke_recipe), Rumncoke));
                        break;
                    case 5:
                        String[] Rumncoke1 = {"Rum"};
                        drinks.add(new Recipes("Pina Colada", R.drawable.pina, R.drawable.pina_small, getString(R.string.pina_desc), getString(R.string.pina_recipe), Rumncoke1));
                        break;
                    case 6:
                        String[] Rumncoke2 = {"Rum"};
                        drinks.add(new Recipes("Daiquiri", R.drawable.daiquiri, R.drawable.daiquiri_small, getString(R.string.daiquiri_desc), getString(R.string.daiquiri_recipe), Rumncoke2));
                        break;
                    case 7:
                        String[] Gin = {"Gin"};
                        drinks.add(new Recipes("Gin and Tonic", R.drawable.gin_tonic, R.drawable.gin_tonic_small, getString(R.string.gin_tonic_desc), getString(R.string.gin_tonic_recipe), Gin));
                        break;
                    case 8:
                        String[] martini = {"Gin", "Vermouth"};
                        drinks.add(new Recipes("Martini", R.drawable.martini, R.drawable.martini_small, getString(R.string.martini_desc), getString(R.string.martini_recipe), martini));
                        break;
                    case 9:
                        String[] Gin2 = {"Gin"};
                        drinks.add(new Recipes("Tom Collins", R.drawable.tom_collins, R.drawable.tom_collins_small, getString(R.string.tom_collins_desc), getString(R.string.tom_collins_recipe), Gin2));
                        break;
                    case 10:
                        String[] carbomb = {"Whiskey", "Jameson"};
                        drinks.add(new Recipes("Irish Car Bomb", R.drawable.irish_car_bomb, R.drawable.irish_car_bomb_small, getString(R.string.irishcarbomb_desc), getString(R.string.irishccarbomb_recipe), carbomb));
                        break;
                    case 11:
                        String[] Whiskey = {"Whiskey"};
                        drinks.add(new Recipes("Whiskey Sour", R.drawable.whiskey_sour, R.drawable.whiskey_sour_small, getString(R.string.whiskey_sour_desc), getString(R.string.whiskey_sour_recipe), Whiskey));
                        break;
                    case 12:
                        String[] manhattan = {"Whiskey", "Vermouth"};
                        drinks.add(new Recipes("Manhattan", R.drawable.manhattan, R.drawable.manhattan_small, getString(R.string.manhattan_desc), getString(R.string.manhattan_recipe), manhattan));
                        break;
                    default:
                        String[] Jager = {"Jager"};
                        drinks.add(new Recipes("Jager Bomb", R.drawable.jager_bomb, R.drawable.jager_bomb_small, getString(R.string.jagerbomb_desc), getString(R.string.jagerbomb_recipe), Jager));
                        break;

                }
                final Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                Recipes drink = drinks.get(0);
                intent.putExtra("name", drink.getName());
                intent.putExtra("fileName", drink.getIcon());
                intent.putExtra("about", drink.getDescription());
                intent.putExtra("how", drink.getHow());
                intent.putExtra("ingredients", drink.getIngredients());

                startActivity(intent);


            }
        });
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

    }

    public void setReminder(){
        Intent myIntent = new Intent(MainActivity.this, UserPresentBroadcastReceiver.class);
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, myIntent, 0);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 01);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 00);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 24 * 60 * 60 * 1000, pendingIntent);  //set repeating every 24 hou

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        final List<Recipes> drinks = new ArrayList<>();
        TextView textView = (TextView) findViewById(R.id.recipes);
        switch (number) {
            case 1:
                mTitle = getString(R.string.app_name);
                textView.setText("Common Vodka Recipes");
                String[] screwdriver = {"Vodka"};
                String[] fuzzy = {"Peach Schnapps"};
                String[] cooler = {"Vodka", "Triple Sec"};

                drinks.add(new Recipes( "Screwdriver",  R.drawable.screwdriver, R.drawable.screwdriver_small, getString(R.string.screwdriver_desc), getString(R.string.screwdriver_recipe),screwdriver));
                drinks.add(new Recipes( "Fuzzy Navel",  R.drawable.fuzzy_navel, R.drawable.fuzzy_navel_small, getString(R.string.fuzzynavel_desc), getString(R.string.fuzzynavel_recipe), fuzzy));
                drinks.add(new Recipes( "Cactus Cooler", R.drawable.cactus_cooler, R.drawable.cactus_cooler_small, getString(R.string.cactuscooler_desc), getString(R.string.cactuscooler_recipe), cooler));
                break;
            case 2:
                mTitle = getString(R.string.app_name);
                textView.setText("Common Rum Recipes");
                String[] Rumncoke = {"Rum"};

                drinks.add(new Recipes("Rum and Coke", R.drawable.rum ,R.drawable.rum_small, getString(R.string.rum_coke_desc), getString(R.string.rum_coke_recipe), Rumncoke));
                drinks.add(new Recipes("Pina Colada", R.drawable.pina, R.drawable.pina_small, getString(R.string.pina_desc), getString(R.string.pina_recipe), Rumncoke));
                drinks.add(new Recipes("Daiquiri", R.drawable.daiquiri, R.drawable.daiquiri_small, getString(R.string.daiquiri_desc), getString(R.string.daiquiri_recipe), Rumncoke));
                break;
            case 3:
                mTitle = getString(R.string.app_name);
                textView.setText("Common Gin Recipes");
                String[] Gin = {"Gin"};
                String[] martini = {"Gin", "Vermouth"};

                drinks.add(new Recipes( "Gin and Tonic",  R.drawable.gin_tonic, R.drawable.gin_tonic_small, getString(R.string.gin_tonic_desc), getString(R.string.gin_tonic_recipe), Gin));
                drinks.add(new Recipes("Martini", R.drawable.martini, R.drawable.martini_small, getString(R.string.martini_desc), getString(R.string.martini_recipe), martini));
                drinks.add(new Recipes("Tom Collins", R.drawable.tom_collins, R.drawable.tom_collins_small, getString(R.string.tom_collins_desc), getString(R.string.tom_collins_recipe), Gin));
                break;
            case 4:
                mTitle = getString(R.string.app_name);
                textView.setText("Common Whiskey Recipes");
                String[] Whiskey = {"Whiskey"};
                String[] manhattan = {"Whiskey", "Vermouth"};
                String[] carbomb = {"Whiskey", "Jameson"};

                drinks.add(new Recipes("Irish Car Bomb", R.drawable.irish_car_bomb, R.drawable.irish_car_bomb_small, getString(R.string.irishcarbomb_desc), getString(R.string.irishccarbomb_recipe), carbomb));
                drinks.add(new Recipes("Whiskey Sour", R.drawable.whiskey_sour, R.drawable.whiskey_sour_small, getString(R.string.whiskey_sour_desc), getString(R.string.whiskey_sour_recipe), Whiskey));
                drinks.add(new Recipes("Manhattan", R.drawable.manhattan, R.drawable.manhattan_small, getString(R.string.manhattan_desc), getString(R.string.manhattan_recipe), manhattan));
                break;
            case 5:
                mTitle = getString(R.string.app_name);
                textView.setText("Common Tequila Recipes");
                String[] Margarita = {"Tequila"};

                drinks.add(new Recipes("Margarita", R.drawable.margarita, R.drawable.margarita_small, getString(R.string.margarita_desc), getString(R.string.margarita_recipe), Margarita));
                drinks.add(new Recipes("Horny Bull", R.drawable.horny_bull, R.drawable.horny_bull_small, getString(R.string.horny_bull_desc), getString(R.string.horny_bull_recipe), Margarita));
                drinks.add(new Recipes("Tequila Sunrise", R.drawable.tequila_sunrise, R.drawable.tequila_sunrise_small, getString(R.string.tequila_sunrise_desc), getString(R.string.tequila_sunrise_recipe), Margarita));
                break;
            case 6:
                mTitle = getString(R.string.app_name);
                textView.setText("Extra Recipes");
                String[] Jager = {"Jager"};

                drinks.add(new Recipes("Jager Bomb", R.drawable.jager_bomb, R.drawable.jager_bomb_small, getString(R.string.jagerbomb_desc), getString(R.string.jagerbomb_recipe), Jager));
                //extras
                break;

        }
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new BoozeAdapter(this, R.layout.list_single_entry, drinks));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){

                Recipes drink = drinks.get(position);

                final Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("name", drink.getName());
                intent.putExtra("fileName", drink.getIcon());
                intent.putExtra("about", drink.getDescription());
                intent.putExtra("how", drink.getHow());
                intent.putExtra("ingredients", drink.getIngredients());

                startActivity(intent);
            }


        });
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, 0, 0, "Uninstall");
        menu.add(Menu.NONE, 1, 1, "Pantry");

        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.

            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {

            case 0:
                getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putBoolean("firstrun", true).commit();
                Uri packageURI = Uri.parse("package:com.example.august.mixmaster");
                Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);
                startActivity(uninstallIntent);
                return true;
            case 1:
                Intent pantry = new Intent(MainActivity.this, PantryActivity.class);
                startActivity(pantry);
                return true;
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }

    }

}
