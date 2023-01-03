package com.example.bincheck.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bincheck.R
import com.example.bincheck.data.database.CardListEntity
import com.example.bincheck.databinding.ItemCardListBinding

class CardListAdapter : RecyclerView.Adapter<CardListAdapter.CardListViewHolder>() {
    private val cardList = mutableListOf<CardListEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardListViewHolder {
        return CardListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_card_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CardListViewHolder, position: Int) {
        holder.bind(cardList[position])
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    fun updateList(list: List<CardListEntity>) {
        this.cardList.clear()
        this.cardList.addAll(list)
        notifyDataSetChanged()
    }

    class CardListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding = ItemCardListBinding.bind(itemView)

        fun bind(model: CardListEntity) {

            binding.schemeTextView.text = model.scheme
            binding.brandTextView.text = model.brand
            binding.cardLengthTitle.text = model.length.toString()
            if (model.luhn == true) {
                binding.cardLuhnYes.setTextColor(Color.BLACK)
                binding.cardLuhnNo.setTextColor(Color.GRAY)
            } else {
                binding.cardLuhnYes.setTextColor(Color.GRAY)
                binding.cardLuhnNo.setTextColor(Color.BLACK)
            }
            binding.bankTextView.text = model.bankName
            binding.bankWebsiteTextView.text = model.url
            binding.bankPhoneTextView.text = model.phone
            if(model.type == "debit") {
                binding.typeDebit.setTextColor(Color.BLACK)
                binding.typeCredit.setTextColor(Color.GRAY)
            } else {
                binding.typeDebit.setTextColor(Color.GRAY)
                binding.typeCredit.setTextColor(Color.BLACK)
            }
            if (model.prepaid == true) {
                binding.prepaidYes.setTextColor(Color.BLACK)
                binding.prepaidNo.setTextColor(Color.GRAY)
            } else {
                binding.prepaidYes.setTextColor(Color.GRAY)
                binding.prepaidNo.setTextColor(Color.BLACK)
            }
            binding.country.text = "${model.emoji} ${model.countryName}"
            binding.countryLatitude.text = model.latitude?.toInt().toString()
            binding.countryLongitude.text = model.longitude?.toInt().toString()
            binding.bankPhoneTextView.text = model.phone
        }
    }
}