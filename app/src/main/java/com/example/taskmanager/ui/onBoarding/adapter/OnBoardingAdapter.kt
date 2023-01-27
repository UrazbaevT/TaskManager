package com.example.taskmanager.ui.onBoarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.taskmanager.databinding.ItemOnboardingBinding
import com.example.taskmanager.model.OnBoard
import com.example.taskmanager.utils.loadImage

class OnBoardingAdapter(private val onClick: () -> Unit) :
    Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {
    private val data = arrayListOf(
        OnBoard(
            "Напоминания стали простыми",
            "Начните искать свое следующее большое путешествие, начиная с этого момента.\n" +
                    "Проведите пальцем вверх для просмотра интересных историй или влево для\n" +
                    "просмотра популярных каналов.",
            "https://i.postimg.cc/K8fv9tTv/zyro-image.png"
        ),
        OnBoard(
            "Мы более сосредоточены",
            "Начните искать свое следующее большое путешествие, начиная с этого момента.\n" +
                    "Проведите пальцем вверх для просмотра интересных историй или влево для\n" +
                    "просмотра популярных каналов.",
            "https://i.postimg.cc/fyQNWbdr/zyro-image-1.png"
        ),
        OnBoard(
            "Зарегистрируйтесь для интеграции",
            "Начните искать свое следующее большое путешествие, начиная с этого момента.\n" +
                    "Проведите пальцем вверх для просмотра интересных историй или влево для\n" +
                    "просмотра популярных каналов.",
            "https://i.postimg.cc/J4Rfpv5Z/zyro-image-2.png"
        )
    )


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(
            ItemOnboardingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class OnBoardingViewHolder(private val binding: ItemOnboardingBinding) :
        ViewHolder(binding.root) {
        fun bind(onBoard: OnBoard) {
            binding.tvTitle.text = onBoard.title
            binding.tvDesk.text = onBoard.desc
            binding.image.loadImage(onBoard.image.toString())

            binding.btnStart.isVisible = adapterPosition == data.lastIndex

            binding.btnStart.setOnClickListener {
                onClick()
            }

//            if (adapterPosition == data.lastIndex){
//                binding.btnStart.isVisible = true
//            }else{
//                binding.btnStart.isVisible = false
//            }
        }
    }
}