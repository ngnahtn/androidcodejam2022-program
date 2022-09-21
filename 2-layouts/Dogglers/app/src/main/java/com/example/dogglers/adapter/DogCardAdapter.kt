/*
* Copyright (C) 2021 The Android Open Source Project.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.example.dogglers.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.dogglers.DogDetailActivity
import com.example.dogglers.R
import com.example.dogglers.const.Layout
import com.example.dogglers.data.DataSource
import com.example.dogglers.model.Dog
import com.google.android.material.card.MaterialCardView
import org.w3c.dom.Text

/**
 * Adapter to inflate the appropriate list item layout and populate the view with information
 * from the appropriate data source
 */
class DogCardAdapter(
    private val context: Context?,
    private val layout: Int
): RecyclerView.Adapter<DogCardAdapter.DogCardViewHolder>() {

    // TODO: Initialize the data using the List found in data/DataSource
    val dataSet = DataSource.dogs
    var dogListenner: DogListener? = null

    /**
     * Initialize view elements
     */
    class DogCardViewHolder(view: View?): RecyclerView.ViewHolder(view!!) {
        // TODO: Declare and initialize all of the list item UI components
        val dogImageView: ImageView? = view?.findViewById(R.id.item_imageView)
        val dogNameTextView: TextView? = view?.findViewById(R.id.dog_name)
        val dogAgeTextView: TextView? = view?.findViewById(R.id.dog_age)
        val dogHobbiesTextView: TextView? = view?.findViewById(R.id.dog_hobbies)
        val rootView: MaterialCardView? = view?.findViewById(R.id.layout_root)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogCardViewHolder {
        // TODO: Use a conditional to determine the layout type and set it accordingly.
        //  if the layout variable is Layout.GRID the grid list item should be used. Otherwise the
        //  the vertical/horizontal list item should be used.
        val adapterLayout = LayoutInflater.from(parent.context)
        if (layout == Layout.GRID) {
            val gridViewHolder = adapterLayout.inflate(R.layout.grid_list_item,parent,false)
            return DogCardViewHolder(gridViewHolder)
        } else {
            val normalViewHolder = adapterLayout.inflate(R.layout.vertical_horizontal_list_item,parent,false)
            return DogCardViewHolder(normalViewHolder)
        }
    }

    override fun getItemCount(): Int = dataSet.size // TODO: return the size of the data set instead of 0

    override fun onBindViewHolder(holder: DogCardViewHolder, position: Int) {
        // TODO: Get the data at the current position
        // TODO: Set the image resource for the current dog
        // TODO: Set the text for the current dog's name
        // TODO: Set the text for the current dog's age
        var item = dataSet[position]
        holder.dogImageView?.setImageResource(item.imageResourceId)
        val resources = context?.resources
        if (resources != null) {
            holder.dogNameTextView?.text = item.name
            holder.dogAgeTextView?.text = resources.getString(R.string.dog_age, item.age)
            holder.dogHobbiesTextView?.text = resources.getString(R.string.dog_hobbies, item.hobbies)
        }
        holder.itemView.setOnClickListener {
            dogListenner?.onClickDog(item)
            println(item.name)
        }
        // TODO: Set the text for the current dog's hobbies by passing the hobbies to the
        //  R.string.dog_hobbies string constant.
        //  Passing an argument to the string resource looks like:
        //  resources?.getString(R.string.dog_hobbies, dog.hobbies)
    }
}

interface DogListener {
    fun onClickDog(dog: Dog)
}
