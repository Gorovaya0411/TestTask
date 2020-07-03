package com.example.test_task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.test_task.data.model.ImageAnswer
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_view.view.*

class Adapter(private var callback: (ImageAnswer) -> Unit) :
    RecyclerView.Adapter<Adapter.MyViewHolder>() {
    var dataTest = listOf<ImageAnswer>()
    fun setData(data: List<ImageAnswer>) {
        this.dataTest = data
        notifyDataSetChanged()
    }

    fun addData(listItems: List<ImageAnswer>) {
        val sizePast = this.dataTest.size
        this.dataTest = this.dataTest + listItems
        val sizeNew = this.dataTest.size
        notifyItemRangeChanged(sizePast, sizeNew)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_view,
                parent,
                false
            ), callback
        )
    }

    override fun getItemCount(): Int = dataTest.count()

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(dataTest[position])
    }

    class MyViewHolder(itemView: View, private var callback: (ImageAnswer) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        var ItemImageView: ImageView = itemView.recyclerViewImage
        fun bind(model: ImageAnswer) {
            Picasso.get()
                .load("http://gallery.dev.webant.ru/api/photos/media/ ${model.image.name}")
                .into(ItemImageView)
            itemView.setOnClickListener {
                callback.invoke(model)
            }
        }
    }

}
