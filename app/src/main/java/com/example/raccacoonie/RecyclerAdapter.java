package com.example.raccacoonie;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private final RecyclerViewInterface recyclerViewInterface;


    //Variables storing data to display for this example
    private final String[] titles = {"Fasolakia", "Fakes", "Pastitsio", "Keftedakia", "Makaronia me kima", "Spanakorizo", "Omeleta", "Kokkinisto me kritharaki"};
    private Context context;
    private final int[] images = { R.drawable.recipe_0, R.drawable.recipe_image, R.drawable.recipe_image, R.drawable.recipe_image, R.drawable.recipe_image,
            R.drawable.recipe_image, R.drawable.recipe_image, R.drawable.recipe_image };

    private final int[] recipeDrawables = {
            R.drawable.recipe_0,
            R.drawable.recipe_1,
            R.drawable.recipe_2,
            R.drawable.recipe_3,
            R.drawable.recipe_4,
            R.drawable.recipe_5
    };
    ArrayList<Recipe> recipes = new ArrayList<>();
    ArrayList<Recipe>ogrecipes=new ArrayList<>();
    ArrayList<Integer> pics = new ArrayList<>();
    Map<String,Integer> types=new HashMap<>();



    public RecyclerAdapter(RecyclerViewInterface recyclerViewInterface,Context context) {
        this.recyclerViewInterface = recyclerViewInterface;
        this.context = context;
        fillPics();
        fillOgRecipes();
        recipes.addAll(ogrecipes);
        types.put("Pescetarian",1);
        types.put("Vegetarian",2);
        types.put("Vegan",3);
        types.put("Tag",0);

    }
    public void fillPics()
    /**
     * fills the pics arraylist with the appropriate amount of pictures
     */
    {
        for (int i = 0 ; i < recipes.size(); i ++)
        {
            pics.add(R.drawable.recipe_image);
        }
    }
    public void fillOgRecipes()
    {
        ogrecipes.add(new Recipe(1, "Classic Chocolate Chip Cookies",
                "chocolate_chip_cookies.jpg",
                "1. Preheat the oven to 350°F. \n2. In a mixing bowl, cream together butter and sugars. \n3. Beat in eggs and vanilla. \n4. In a separate bowl, combine flour, baking soda, and salt. \n5. Gradually add the dry ingredients to the wet ingredients, mixing well. \n6. Stir in chocolate chips. \n7. Drop rounded tablespoons of dough onto a baking sheet. \n8. Bake for 10-12 minutes or until golden brown. \n9. Allow cookies to cool on the baking sheet for a few minutes before transferring to a wire rack.",
                "Butter, granulated sugar, brown sugar, eggs, vanilla extract, all-purpose flour, baking soda, salt, chocolate chips",
                "Snack", 3, "United States"));
        ogrecipes.add(new Recipe(2, "Chicken Parmesan",
                "chicken_parmesan.jpg",
                "1. Preheat the oven to 375°F. \n2. Season chicken cutlets with salt, pepper, and Italian seasoning. \n3. Dip each cutlet in beaten egg, then coat with breadcrumbs. \n4. Heat oil in a skillet and cook the cutlets until golden brown. \n5. Place cooked cutlets in a baking dish and top with marinara sauce and shredded mozzarella cheese. \n6. Bake for 15-20 minutes or until the cheese is melted and bubbly. \n7. Serve with cooked spaghetti or your choice of pasta.",
                "Chicken cutlets, salt, pepper, Italian seasoning, eggs, breadcrumbs, oil, marinara sauce, shredded mozzarella cheese, spaghetti",
                "Dinner", 3, "Italy"));
        ogrecipes.add(new Recipe(3, "Caprese Salad",
                "caprese_salad.jpg",
                "1. Slice tomatoes and fresh mozzarella cheese. \n2. Arrange the tomato and mozzarella slices on a serving platter. \n3. Drizzle with olive oil and balsamic glaze. \n4. Sprinkle with salt, pepper, and fresh basil leaves. \n5. Serve immediately as a refreshing appetizer or side dish.",
                "Tomatoes, fresh mozzarella cheese, olive oil, balsamic glaze, salt, pepper, fresh basil leaves",
                "Snack", 2, "Greece"));
        ogrecipes.add(new Recipe(4, "Vegetable Stir-Fry",
                "vegetable_stirfry.jpg",
                "1. Heat oil in a wok or skillet over high heat. \n2. Add sliced vegetables (e.g., bell peppers, carrots, broccoli, snap peas). \n3. Stir-fry for a few minutes until vegetables are crisp-tender. \n4. In a small bowl, mix together soy sauce, ginger, and garlic. \n5. Pour the sauce over the vegetables and stir-fry for another minute. \n6. Serve hot over steamed rice or noodles.",
                "Assorted vegetables (bell peppers, carrots, broccoli, snap peas), oil, soy sauce, ginger, garlic, steamed rice or noodles",
                "Dinner", 1, "China"));
        ogrecipes.add(new Recipe(1, "Avocado Toast",
                "avocado_toast.jpg",
                "1. Toast a slice of bread until golden brown. \n2. Mash half an avocado and spread it on the toast. \n3. Sprinkle with salt, pepper, and red pepper flakes to taste. \n4. Optionally, top with sliced tomatoes, a drizzle of olive oil, or a squeeze of lemon juice. \n5. Enjoy as a quick and healthy breakfast or snack.",
                "Bread, avocado, salt, pepper, red pepper flakes",
                "Snack", 1, "United States"));
        ogrecipes.add(new Recipe(4, "Peanut Butter Banana Smoothie",
                "peanut_butter_smoothie.jpg",
                "1. In a blender, combine a ripe banana, a spoonful of peanut butter, and a cup of milk. \n2. Add a drizzle of honey and a pinch of cinnamon. \n3. Blend until smooth and creamy. \n4. Pour into a glass and enjoy as a delicious and energizing smoothie.",
                "Banana, peanut butter, milk, honey, cinnamon",
                "Drink", 1, "Greece"));
    }

    public void updateRecipes(String category,String tag,String ingredients,String country) {

        String [] filter_ingredients=ingredients.split(",");
        for (int i = 0; i < filter_ingredients.length; i++) {
            filter_ingredients[i] = filter_ingredients[i].trim();
        }
            recipes.clear();
            for (Recipe recipe : ogrecipes) {


               /*if ( (category.equals(recipe.category) || types.get(tag)==recipe.dietaryStatus)|| similarity>=2||country.equals(recipe.country)) {
                    recipes.add(recipe);
                }*/

                    if (category.equals("Category")) {
                        if (tag.equals("Tag")) {
                            if (country.equals(""))
                                Toast.makeText(context, "null", Toast.LENGTH_SHORT).show();
                            else {
                                if (recipe.country.equals((country))){
                                    recipes.add(recipe);
                                }
                            }
                        } else {
                            if (country.equals("")) {
                                if (types.get(tag) == recipe.dietaryStatus) {
                                    recipes.add(recipe);
                                }
                            } else {
                                if (types.get(tag) == recipe.dietaryStatus && recipe.country.equals(country)) {
                                    recipes.add(recipe);
                                }
                            }
                        }

                    } else {
                        if (tag.equals("Tag")) {
                            if (country.equals("")) {
                                if (recipe.category.equals(category)) {
                                    recipes.add(recipe);
                                }
                            } else {
                                if (recipe.country.equals((country)) && recipe.category.equals(category)) {
                                    recipes.add(recipe);
                                }
                            }
                        } else {
                            if (country.equals("")) {
                                if (types.get(tag) == recipe.dietaryStatus && recipe.category.equals(category)) {
                                    recipes.add(recipe);
                                }
                            } else {
                                if (types.get(tag) == recipe.dietaryStatus && recipe.category.equals(category) && recipe.country.equals(country)) {
                                    recipes.add(recipe);
                                }
                            }
                        }
                    }


            if (ingredients.length()>0)
            {


                int similarity = 0;
                String[] recipe_ingredients = recipe.ingredients.split(",");
                for (int i = 0; i < recipe_ingredients.length; i++) {
                    recipe_ingredients[i] = recipe_ingredients[i].trim();
                }

                for (String word : filter_ingredients) {
                    if (Arrays.asList(recipe_ingredients).contains(word)) {
                        similarity++;
                    }
                }
                if (similarity==0)
                {
                    recipes.remove(recipe);
                }
                else
                {
                    recipes.add(recipe);
                }
            }}




        notifyDataSetChanged();
    }
    public void clearFilters()
    {
        recipes.clear();
        recipes.addAll(ogrecipes);
        notifyDataSetChanged();
    }
    public void fillLikedRecipes(ArrayList<String> liked)
    {
        recipes.clear();

        for (String title:liked)
        {
            for(Recipe recipe:ogrecipes)
            {
                if (recipe.title.equals(title))
                {
                    //recipes.add(recipe);
                }
            }
        }
      notifyDataSetChanged();
    }



    //Class that holds the items to be displayed (Views in card_layout)
    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemTitle;

        public ViewHolder(View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.item_image);
            itemTitle = itemView.findViewById(R.id.item_title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    //card position

                    int position = getAdapterPosition();
                    Log.d("DEBUG","Got adapter pos");
                    Intent intent = new Intent(v.getContext(), View_Recipe_Activity.class);
                    Log.d("DEBUG","LOADED INTENT");
                    //get binding adapter where all the cards are
                    RecyclerView.Adapter adapter = (RecyclerAdapter)getBindingAdapter();
                    Log.d("DEBUG","Got binding adapter");

                    Bundle data = new Bundle();
                    Log.d("DEBUG","DATALOADED");
                    //FILL RECIPE DATA ON BUNDLE TO GIVE TO THE ACTIVITY
                    data.putString("Rec_title",((RecyclerAdapter) adapter).recipes.get(position).title);
                    data.putString("Recipe_execution",((RecyclerAdapter) adapter).recipes.get(position).getExecution());
                    data.putInt("Recipe_pic",((RecyclerAdapter) adapter).recipeDrawables[position]);
                    data.putString("Recipe_ingredients",((RecyclerAdapter) adapter).recipes.get(position).getIngredients());


                    intent.putExtras(data);
                    Log.d("DEBUG",data.getString("Rec_title"));


                    //start activity
                    v.getContext().startActivity(intent,data);


                    if( recyclerViewInterface!=null){
                        //int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                           // recyclerViewInterface.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
    //Methods that must be implemented for a RecyclerView.Adapter
    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_layout, parent, false);

        return new ViewHolder(v, recyclerViewInterface);
    }
    public Recipe getRecipefromPosition(int pos)
    {
        return this.recipes.get(pos);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.itemTitle.setText(recipes.get(position).title);

        holder.itemImage.setImageResource(recipeDrawables[position]);
        //MIN PEIRAKSEI KANEIS TO PANO, EIMAI POLI EKSIPNOS POU TO SKEFTIKA
    }
    @Override
    public int getItemCount() {
        return recipes.size();
        //return titles.length;
    }
    public void fillTitles(int limit)
    {
        DatabaseHandler myhandler = new DatabaseHandler(this.context,1);
        Cursor cursor = myhandler.getRecipes(-1);
        ArrayList<String> titleList = new ArrayList<>();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String title = cursor.getString(1);
                titleList.add(title);
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }

        for (int i = 0 ; i < titleList.size(); i++)
        {
            Log.d("TEST",titleList.get(i));
        }
    }
}