package co.cmd.cook.presentation.recipeList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import co.cmd.cook.R
import co.cmd.cook.presentation.recipeList.RecipeListAdapter.RecipeViewHolder
import co.cmd.core.domain.Recipe
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

class RecipeListAdapter : PagingDataAdapter<Recipe, RecipeViewHolder>(COMPARATOR) {

    var recipeClickListener: ((Recipe) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder =
        RecipeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_recipe, parent, false))

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        println("POSITION $position")
        getItem(position)?.let {
            holder.bindTo(it)
        } ?: let {
            holder.clear()
        }
    }

    inner class RecipeViewHolder(
        val view: View,
    ) : RecyclerView.ViewHolder(view) {

        val name = view.findViewById<TextView>(R.id.text_name)
        val description = view.findViewById<TextView>(R.id.text_description)
        val image = view.findViewById<ImageView>(R.id.image_recipe)

        fun bindTo(recipe: Recipe) = with(view) {
            name.text = recipe.name.value
            description.text = recipe.description.value
            Glide.with(this).applyDefaultRequestOptions(
                RequestOptions()
                    .placeholder(R.drawable.menu_placeholder)
                    .error(R.drawable.menu_placeholder)
            )
                .load(recipe.imageUrl.value)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(image)
            view.setOnClickListener {
                recipeClickListener?.invoke(recipe)
            }
        }

        fun clear() {
        }
    }

    companion object {

        private val COMPARATOR = object : DiffUtil.ItemCallback<Recipe>() {
            override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean =
                oldItem == newItem
        }
    }
}
