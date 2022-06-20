package com.codeliner.vitalchernavsky.view.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.codeliner.vitalchernavsky.R
import com.codeliner.vitalchernavsky.model.Item
import kotlinx.android.synthetic.main.fragment_detail.view.*

class DetailFragment : Fragment() {

    private var name: TextView? = null
    private var description: TextView? = null
    private var image: ImageView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        name = view.tv_name_detail
        description = view.tv_description_detail
        image = view.img_detail
        return view
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val model = requireArguments().getSerializable("model") as Item
        name?.text = model.name
        description?.text = model.description

        Glide.with(requireContext())
            .load(model.owner.avatar_url)
            .fitCenter()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(image!!)
    }
}