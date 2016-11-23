/*
 * Copyright (c) 2016 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package kaist.homingpigeon_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import com.squareup.picasso.Picasso;
import android.graphics.Typeface;

public class RecipeAdapter extends BaseAdapter { //adapts our list view

  public static final String TAG = RecipeAdapter.class.getSimpleName();
  public static final HashMap<String, Integer> LABEL_COLORS = new HashMap<String, Integer>()
  {{
  }};

  private Context mContext;
  private LayoutInflater mInflater;
  private ArrayList<Recipe> mDataSource;


  public RecipeAdapter(Context context, ArrayList<Recipe> items) {
    mContext = context;
    mDataSource = items;
    mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }
  @Override
  public int getCount() {
    return mDataSource.size();
  }
  @Override
  public Object getItem(int position) {
    return mDataSource.get(position);
  }
  @Override
  public long getItemId(int position) {
    return position;
  }
  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    ViewHolder holder;
    // check if the view already exists if so, no need to inflate and findViewById again!
    if (convertView == null) {
      // Inflate the custom row layout from your XML.
      convertView = mInflater.inflate(R.layout.list_item_recipe, parent, false);
      // create a new "Holder" with subviews
      holder = new ViewHolder();
      holder.thumbnailImageView = (ImageView) convertView.findViewById(R.id.recipe_list_thumbnail);
      holder.titleTextView = (TextView) convertView.findViewById(R.id.recipe_list_title);
      holder.subtitleTextView = (TextView) convertView.findViewById(R.id.recipe_list_subtitle);
      holder.detailTextView = (TextView) convertView.findViewById(R.id.recipe_list_detail);

      // hang onto this holder for future recyclage
      convertView.setTag(holder);
    }
    else {
      // skip all the expensive inflation/findViewById and just get the holder you already made
      holder = (ViewHolder) convertView.getTag();
    }
    // Get relevant subviews of row view
    TextView titleTextView = holder.titleTextView;
    TextView subtitleTextView = holder.subtitleTextView;
    TextView detailTextView = holder.detailTextView;
    ImageView thumbnailImageView = holder.thumbnailImageView;

    //Get corresponding recipe for row
    Recipe recipe = (Recipe) getItem(position);

    // Update row view's textviews to display recipe information
    titleTextView.setText(recipe.title);
    subtitleTextView.setText(recipe.description);
    detailTextView.setText(recipe.label);

    // Use Picasso to load the image. Temporarily have a placeholder in case it's slow to load
    Picasso.with(mContext).load(recipe.imageUrl).placeholder(R.mipmap.ic_launcher).into(thumbnailImageView);
    return convertView;
  }
  private static class ViewHolder {
    public TextView titleTextView;
    public TextView subtitleTextView;
    public TextView detailTextView;
    public ImageView thumbnailImageView;
  }
}
