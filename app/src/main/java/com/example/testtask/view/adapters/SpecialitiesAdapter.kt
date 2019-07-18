package com.example.testtask.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.R
import com.example.testtask.domain.model.Speciality
import kotlinx.android.synthetic.main.cell_specialities.view.*

class SpecialitiesAdapter(private val callback: (id: Int) -> Unit) : RecyclerView.Adapter<SpecialitiesAdapter.SpecialtyHolder>() {

    private var specialtyList = ArrayList<Speciality>()

    fun setSpecialities(specialities: ArrayList<Speciality>) {
        specialtyList = specialities
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return specialtyList.size
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): SpecialtyHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        return SpecialtyHolder(inflater.inflate(R.layout.cell_specialities, viewGroup, false))
    }

    override fun onBindViewHolder(holder: SpecialtyHolder, position: Int) {
        holder.bind(specialtyList[position])
    }

    inner class SpecialtyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var id = itemView.cell_speciality_id
        private var name = itemView.cell_speciality_name
        private var root = itemView.cell_root

        fun bind(specialty: Speciality) {
            id.text = specialty.specialityID.toString()
            name.text = specialty.specialityName
            root.setOnClickListener {
                callback.invoke(adapterPosition)
            }
        }
    }
}
