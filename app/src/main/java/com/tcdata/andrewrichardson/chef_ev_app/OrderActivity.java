package com.tcdata.andrewrichardson.chef_ev_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.tcdata.andrewrichardson.chef_ev_app.Database.Database;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;

public class OrderActivity extends AppCompatActivity {


    Spinner proteinSpinner, flavorSpinner, baseFlavorSpinner, seasoningSpinner, grainsSpinner, veggiesSpinner, sideSpinner;
    String protein, baseFlavor, flavor, seasoning, grain, veggie, side;
    Button orderViewFlavorButton, orderViewSeasoningButton, orderViewNeitherButton,
            orderViewButtonGrains, orderViewButtonVeggies, orderViewButtonNeither, addToCart;
    TextView textViewFlavor, textViewBaseFlavor, textViewSeasoning, textViewGrains, textViewVeggies, textViewSide;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);


        buttonsSelected();



    }

    public void buttonsSelected(){

        //Text Views

        textViewSeasoning = (TextView) findViewById(R.id.textViewSeasoning);
        textViewFlavor = (TextView) findViewById(R.id.textViewFlavor);
        textViewGrains = (TextView) findViewById(R.id.textViewGrains);
        textViewVeggies = (TextView) findViewById(R.id.textViewVeggies);
        textViewBaseFlavor = (TextView) findViewById(R.id.textViewBaseFlavor) ;
        textViewSide = (TextView) findViewById(R.id.textViewSide);

        //Spinners

        flavorSpinner = (Spinner) findViewById(R.id.flavorSpinner);
        baseFlavorSpinner = (Spinner) findViewById(R.id.baseFlavorSpinner) ;
        seasoningSpinner = (Spinner) findViewById(R.id.seasoningSpinner);
        grainsSpinner = (Spinner) findViewById(R.id.grainsSpinner);
        veggiesSpinner = (Spinner) findViewById(R.id.veggiesSpinner);
        sideSpinner = (Spinner) findViewById(R.id.sideSpinner);

        //Buttons

        orderViewSeasoningButton = (Button) findViewById(R.id.orderViewSeasoningButton);
        orderViewFlavorButton = (Button) findViewById(R.id.orderViewFlavorButton);
        orderViewNeitherButton = (Button) findViewById(R.id.orderViewNeitherButton);
        orderViewButtonGrains = (Button) findViewById(R.id.orderViewButtonGrains);
        orderViewButtonVeggies = (Button) findViewById(R.id.orderViewButtonVeggies);
        orderViewButtonNeither = (Button) findViewById(R.id.orderViewButtonBaseNeither);
        addToCart = (Button) findViewById(R.id.addToCart);

        //Set Up Spinners

        addItemsOnProteinSpinner();
        addItemsOnFlavorSpinner();
        addItemsOnSeasoningSpinner();
        addItemsOnGrainsSpinner();
        addItemsOnVeggiesSpinner();
        addItemsOnBaseFlavorSpinner();
        addItemsOnSideSpinner();


        //Flavor Button Selected

        orderViewFlavorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(v == orderViewFlavorButton){
                    addItemsOnFlavorSpinner();
                    textViewFlavor.setVisibility(View.VISIBLE);
                    flavorSpinner.setVisibility(View.VISIBLE);
                    textViewSeasoning.setVisibility(View.GONE);
                    seasoningSpinner.setVisibility(View.GONE);
                }

            }

        });

        //Seasoning Button Selected

        orderViewSeasoningButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(v == orderViewSeasoningButton){
                    addItemsOnSeasoningSpinner();
                    textViewSeasoning.setVisibility(View.VISIBLE);
                    seasoningSpinner.setVisibility(View.VISIBLE);
                    textViewFlavor.setVisibility(View.GONE);
                    flavorSpinner.setVisibility(View.GONE);
                }

            }

        });

        //Neither Button Selected

        orderViewNeitherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(v == orderViewNeitherButton){
                    textViewSeasoning.setVisibility(View.GONE);
                    seasoningSpinner.setVisibility(View.GONE);
                    textViewFlavor.setVisibility(View.GONE);
                    flavorSpinner.setVisibility(View.GONE);
                }

            }

        });


        //Grains Button Selected

        orderViewButtonGrains.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(v == orderViewButtonGrains){
                    addItemsOnGrainsSpinner();
                    addItemsOnBaseFlavorSpinner();
                    textViewGrains.setVisibility(View.VISIBLE);
                    grainsSpinner.setVisibility(View.VISIBLE);
                    textViewBaseFlavor.setVisibility(View.VISIBLE);
                    baseFlavorSpinner.setVisibility(View.VISIBLE);
                    textViewVeggies.setVisibility(View.GONE);
                    veggiesSpinner.setVisibility(View.GONE);
                }

            }

        });

        //Veggies Button Selected

        orderViewButtonVeggies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(v == orderViewButtonVeggies){
                    addItemsOnVeggiesSpinner();
                    textViewGrains.setVisibility(View.GONE);
                    grainsSpinner.setVisibility(View.GONE);
                    textViewBaseFlavor.setVisibility(View.GONE);
                    baseFlavorSpinner.setVisibility(View.GONE);
                    textViewVeggies.setVisibility(View.VISIBLE);
                    veggiesSpinner.setVisibility(View.VISIBLE);
                }

            }

        });

        //Neither 2 Button Selected

        orderViewButtonNeither.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(v == orderViewButtonNeither){
                    addItemsOnFlavorSpinner();
                    textViewGrains.setVisibility(View.GONE);
                    grainsSpinner.setVisibility(View.GONE);
                    textViewBaseFlavor.setVisibility(View.GONE);
                    baseFlavorSpinner.setVisibility(View.GONE);
                    textViewVeggies.setVisibility(View.GONE);
                    veggiesSpinner.setVisibility(View.GONE);
                    veggie = "No Veggies";
                    grain = "No Grains";

                }

            }

        });

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (v == addToCart){
                    new Database(getBaseContext()).addToCart(new OrderInformation(

                    ));
                }

            }
        });







    }

    public void addItemsOnProteinSpinner() {

        proteinSpinner = (Spinner) findViewById(R.id.proteinSpinner);
        List<String> proteins = new ArrayList<String>();
        proteins.add("No Protein");
        proteins.add("Chicken");
        proteins.add("Salmon");
        proteins.add("Cod");
        proteins.add("Sausage");
        proteins.add("Meatballs");
        proteins.add("Smoked Pulled Pork");
        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, proteins);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        proteinSpinner.setAdapter(dataAdapter);

        proteinSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                protein = dataAdapter.getItem(position).toString();

                Toast.makeText(getApplicationContext(),
                        "Selected Protein : " + protein, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void addItemsOnFlavorSpinner() {

        flavorSpinner = (Spinner) findViewById(R.id.flavorSpinner);
        List<String> flavors = new ArrayList<String>();
        flavors.add("No Flavor");
        flavors.add("Spicy Peanut Butter");
        flavors.add("Dark Chocolate Mocha");
        flavors.add("Fair Food BBQ");
        flavors.add("Carolina BBQ");
        flavors.add("Sweet Southern BBQ");
        flavors.add("Jalapeno Peach BBQ");
        flavors.add("Bourbon");
        flavors.add("Caribbean Jerk");
        flavors.add("Roasted Pineapple");
        flavors.add("Habanero");
        flavors.add("Italian");
        flavors.add("Horsey Garlic");
        flavors.add("Roasted Sweet Pepper");
        flavors.add("Sriracha Lime");
        flavors.add("Chipotle");
        flavors.add("Spicy Chipotle");
        flavors.add("Sweet Smokey Fire");
        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, flavors);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        flavorSpinner.setAdapter(dataAdapter);

        flavorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                flavor = dataAdapter.getItem(position).toString();

                Toast.makeText(getApplicationContext(),
                        "Selected Flavor : " + flavor, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void addItemsOnSeasoningSpinner() {

        seasoningSpinner = (Spinner) findViewById(R.id.seasoningSpinner);
        List<String> seasonings = new ArrayList<String>();
        seasonings.add("No Seasoning");
        seasonings.add("Cajun");
        seasonings.add("Mesquite");
        seasonings.add("Buffalo");
        seasonings.add("Seafood Bay");
        seasonings.add("Western BBQ");
        seasonings.add("Sriracha");
        seasonings.add("Lemon Pepper");
        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, seasonings);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        seasoningSpinner.setAdapter(dataAdapter);

        seasoningSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                seasoning = dataAdapter.getItem(position).toString();

                Toast.makeText(getApplicationContext(),
                        "Selected Seasoning : " + seasoning, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void addItemsOnGrainsSpinner() {

        grainsSpinner = (Spinner) findViewById(R.id.grainsSpinner);
        List<String> grains = new ArrayList<String>();
        grains.add("No Grain");
        grains.add("Quinoa");
        grains.add("Pearl Barley");
        grains.add("Couscous");
        grains.add("White Rice");
        grains.add("Brown Rice");
        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, grains);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        grainsSpinner.setAdapter(dataAdapter);

        grainsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                grain = dataAdapter.getItem(position).toString();

                Toast.makeText(getApplicationContext(),
                        "Selected Grain : " + grain, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void addItemsOnVeggiesSpinner() {

        veggiesSpinner = (Spinner) findViewById(R.id.veggiesSpinner);
        List<String> veggies = new ArrayList<String>();
        veggies.add("No Veggies");
        veggies.add("Zucchini Noodles");
        veggies.add("Spaghetti Squash");
        veggies.add("Sweet Potato Fries");
        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, veggies);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        veggiesSpinner.setAdapter(dataAdapter);

        veggiesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                veggie = dataAdapter.getItem(position).toString();

                Toast.makeText(getApplicationContext(),
                        "Selected Veggies : " + veggie, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void addItemsOnBaseFlavorSpinner() {

        baseFlavorSpinner = (Spinner) findViewById(R.id.baseFlavorSpinner);
        List<String> baseFlavors = new ArrayList<String>();
        baseFlavors.add("No Base Flavor");
        baseFlavors.add("Spicy");
        baseFlavors.add("Lemon Pepper");
        baseFlavors.add("Sweet & Spice");
        baseFlavors.add("Carolina BBQ");
        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, baseFlavors);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        baseFlavorSpinner.setAdapter(dataAdapter);

        baseFlavorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                baseFlavor = dataAdapter.getItem(position).toString();

                Toast.makeText(getApplicationContext(),
                        "Selected Base Flavor : " + baseFlavor, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void addItemsOnSideSpinner() {

        sideSpinner = (Spinner) findViewById(R.id.sideSpinner);
        final List<String> sideSelect = new ArrayList<String>();
        sideSelect.add("No Side");
        sideSelect.add("Veg Medley");
        sideSelect.add("Broccoli");
        sideSelect.add("Brussels Sprouts");
        sideSelect.add("Balsamic Brussels Sprouts");
        sideSelect.add("Italian Bean Soup");
        sideSelect.add("Spicy Bean Soup");
        sideSelect.add("Cauliflower");
        sideSelect.add("Green Beans");
        sideSelect.add("Asparagus");
        sideSelect.add("Peas");
        sideSelect.add("Sweet Corn");
        sideSelect.add("Veggie Blend");
        sideSelect.add("Cut Carrots");
        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, sideSelect);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sideSpinner.setAdapter(dataAdapter);

        sideSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                side = dataAdapter.getItem(position).toString();

                Toast.makeText(getApplicationContext(),
                        "Selected Side : " + side, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}
