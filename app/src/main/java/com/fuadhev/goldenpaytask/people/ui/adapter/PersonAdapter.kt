package com.fuadhev.goldenpaytask.people.ui.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fuadhev.goldenpaytask.databinding.ItemPersonBinding
import com.fuadhev.goldenpaytask.databinding.ItemPersonHeaderBinding
import com.fuadhev.goldenpaytask.people.ui.model.EyeColor
import com.fuadhev.goldenpaytask.people.ui.model.GenderTypes
import com.fuadhev.goldenpaytask.people.ui.model.PeopleListItem
import com.fuadhev.goldenpaytask.people.ui.model.PeopleUiModel

class PersonAdapter(private var peopleList: List<PeopleListItem>,val onClick: (PeopleUiModel) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private const val VIEW_TYPE_HEADER = 0
        private const val VIEW_TYPE_ITEM = 1
    }



    override fun getItemViewType(position: Int): Int {
        return when (peopleList[position]) {
            is PeopleListItem.Header -> VIEW_TYPE_HEADER
            is PeopleListItem.Person -> VIEW_TYPE_ITEM
        }
    }

    fun updatePeopleList(peopleList:List<PeopleListItem>){
        this.peopleList = peopleList;
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_HEADER) {
            val binding =
                ItemPersonHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            HeaderViewHolder(binding)
        } else {
            val binding =
                ItemPersonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            DataViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> holder.bind((peopleList[position] as PeopleListItem.Header).gender)
            is DataViewHolder -> holder.bind((peopleList[position] as PeopleListItem.Person))
        }
    }

    override fun getItemCount(): Int = peopleList.size

    inner class HeaderViewHolder(
        private val binding: ItemPersonHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(title: String) {
            val context = binding.txtHeaderTitle.context
            binding.txtHeaderTitle.text = context.getString(GenderTypes.getStringResourceByType(title))
        }
    }

    inner class DataViewHolder(private val binding: ItemPersonBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PeopleListItem.Person,) {
            val person = item.person
            val eyeColorEnum = EyeColor.fromStringColor(person.eyeColor)
            binding.eyeColor.backgroundTintList = ColorStateList.valueOf(eyeColorEnum.color)
            binding.root.setOnClickListener {
                onClick(person)
            }
            binding.txtPersonName.text = person.name
        }
    }
}
