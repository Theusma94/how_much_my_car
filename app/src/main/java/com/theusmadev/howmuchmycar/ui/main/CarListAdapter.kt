package com.theusmadev.howmuchmycar.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.theusmadev.howmuchmycar.R
import com.theusmadev.howmuchmycar.data.model.CarInfoResponse
import com.theusmadev.howmuchmycar.databinding.ListItemBinding
import com.theusmadev.howmuchmycar.ui.base.DataBoundListAdapter

class CarListAdapter(): DataBoundListAdapter<CarInfoResponse, ListItemBinding>(
    diffCallback = object : DiffUtil.ItemCallback<CarInfoResponse>(){
        override fun areItemsTheSame(oldItem: CarInfoResponse, newItem: CarInfoResponse): Boolean {
            return oldItem.versionId == newItem.versionId

        }

        override fun areContentsTheSame(oldItem: CarInfoResponse, newItem: CarInfoResponse): Boolean {
            return oldItem.versionId == newItem.versionId
        }

    }
) {
    override fun createBinding(parent: ViewGroup): ListItemBinding {
        val binding =  DataBindingUtil.inflate<ListItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.list_item,
            parent,
            false
        )
        return binding
    }

    override fun bind(binding: ListItemBinding, item: CarInfoResponse) {
        binding.carInfoResponse = item
    }
}