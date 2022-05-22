package com.saadfauzi.myrecyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.saadfauzi.myrecyclerview.databinding.ItemAndroidBinding

class RecyclerAdapterAndroid(private val listAndroid: ArrayList<AndroidModel>): RecyclerView.Adapter<RecyclerAdapterAndroid.ViewHolder>() {

    private lateinit var onItemClickCallback: IOnItemClickCallback

    fun setOnItemClickListener(onItemClickCallback: IOnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ViewHolder(val binding: ItemAndroidBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemAndroidBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val context = holder.itemView.context

        with(holder){
            with(listAndroid[position]){
                binding.apply {
                    tvName.text = name
                    tvDesc.text = desc
                    val imageAndroid = context.resources.getIdentifier(image, null, context.packageName)
                    icImage.setImageResource(imageAndroid)
                }
                itemView.setOnClickListener {
                    onItemClickCallback.onItemClicked(this)
                }
            }
        }
    }

    override fun getItemCount(): Int = listAndroid.size

    interface IOnItemClickCallback {
        fun onItemClicked(data: AndroidModel)
    }
}