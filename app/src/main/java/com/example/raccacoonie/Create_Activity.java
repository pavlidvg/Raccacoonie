package com.example.raccacoonie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class Create_Activity extends AppCompatActivity implements RecyclerViewInterface{

    int TEMP_CREATOR_ID = 2; //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);


        //get elements
        String[] countries = getResources().getStringArray(R.array.countries);
        AutoCompleteTextView country = findViewById(R.id.autoCompleteTextView_country);
        ArrayAdapter<String> country_adapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,countries);
        country.setAdapter(country_adapter);
        Button share_recipe_button = findViewById(R.id.button_share);
        ImageButton search_button= findViewById(R.id.search_button);
        ImageButton home_button= findViewById(R.id.home_button);
        EditText recipe_title = findViewById(R.id.editText_recipe_title);
        EditText ingredients = findViewById(R.id.editText_recipe_ingredients);
        EditText execution = findViewById(R.id.editText_recipe_execution);
        Spinner category_spinner = findViewById(R.id.spinner_category);
        Spinner tag_spinner = findViewById(R.id.spinner_tag);

        DatabaseHandler myHandler = new DatabaseHandler(this,1);
        RecyclerAdapter adapter=new RecyclerAdapter(this,this);




       //debug
        myHandler.printRecipes_db();


        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loadHomeActivity= new Intent(Create_Activity.this,Home_Activity.class);
                startActivity(loadHomeActivity);
            }
        });


        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loadSearchActivity= new Intent(Create_Activity.this,Search_Activity.class);
                startActivity(loadSearchActivity);

            }
        });

        ImageButton create_button= findViewById(R.id.create_button);
        create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loadCreateActivity= new Intent(Create_Activity.this,Create_Activity.class);
                startActivity(loadCreateActivity);

            }
        });

        ImageButton book_button= findViewById(R.id.book_button);
        book_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loadSavedActivity= new Intent(Create_Activity.this,Saved_Activity.class);
                startActivity(loadSavedActivity);
            }
        });

        ImageButton profile_button= findViewById(R.id.profile_button);
        profile_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loadProfileActivity= new Intent(Create_Activity.this,Profile_Activity.class);
                startActivity(loadProfileActivity);
            }
        });

        share_recipe_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ingredients.getText().length() == 0 || recipe_title.getText().length()==0|| execution.getText().length()==0)
                {

                    //Toast.makeText(Create_Activity.this, String.valueOf(myHandler.recipe_count()), Toast.LENGTH_SHORT).show();
                   // myHandler.printRecipes_db();
                    //Toast.makeText(Create_Activity.this, "Please add all the necessary fields before sharing", Toast.LENGTH_SHORT).show();


                }else
                {

                    String recipe_title_str = recipe_title.getText().toString();
                   String execution_str = execution.getText().toString();
                   String ingredients_str = ingredients.getText().toString();
                   String country_str = country.getText().toString();

                   int dietaryStatus = 0;


                   if (tag_spinner.getSelectedItem().toString().equals("Pescetarian"))
                   {
                       dietaryStatus = 1;
                   }else if(tag_spinner.getSelectedItem().toString().equals("Vegetarian"))
                   {
                       dietaryStatus = 2;
                   }else if (tag_spinner.getSelectedItem().toString().equals("Vegan"))
                   {
                       dietaryStatus = 3;
                   }

                   String category = "Dinner";
                   if (category_spinner.getSelectedItem().toString().equals("Snack"))
                    {
                        category = "Snack";
                    }else if (category_spinner.getSelectedItem().toString().equals("Dessert"))
                   {
                       category = "Dessert";
                   }else if (category_spinner.getSelectedItem().toString().equals("Drink"))
                   {
                       category = "Drink";
                   }

                   //myHandler.printRecipes_db();

                   Recipe user_recipe = new Recipe(-1,recipe_title_str,"nullpic",execution_str,ingredients_str,category,dietaryStatus, country_str);
                   //todo: add pictures, for now "nullpic" works fine


                    //backend for recipe input

                    submitRecipe(user_recipe,myHandler);

                   adapter.ogrecipes.add(user_recipe);
                   adapter.notifyItemInserted(adapter.getItemCount()-1);



                }
            }
        });

    }
    public void submitRecipe(Recipe r, DatabaseHandler handler)
    {
        Toast.makeText(this, "Recipe was created", Toast.LENGTH_SHORT).show();

        handler.addRecipe(r);
        //Toast.makeText(this, String.valueOf(handler.recipe_count()), Toast.LENGTH_SHORT).show();



    }

    @Override
    public void onItemClick(int position) {

    }
}