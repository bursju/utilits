package com.paykey.brsju.kbutils.horoscope

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.paykey.brsju.kbutils.R


class PhotoAdapter(private val onItemClick: ZodiacModel.() -> Unit) : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    var dataList = emptyList<ZodiacModel>()

    internal fun setDataList(zodiacList: List<ZodiacModel>) {
        this.dataList = zodiacList
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.zodiac_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        holder.image.setImageResource(data.image)
        holder.image.setOnClickListener {
            onItemClick(data)
        }
    }

    override fun getItemCount() = dataList.size
}