package com.xxh.basic

import android.content.Intent
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.xxh.basic.databinding.ContentListBinding


abstract class ListBaseActivity : BaseActivity<ContentListBinding>() {

    override fun bindView(inflater: LayoutInflater): ContentListBinding {
        return ContentListBinding.inflate(layoutInflater)
    }

    override fun setupViews() {
        val dataset = setData()
        val customAdapter = StringAdapter(dataset)
        customAdapter.setItemClickCallback {
            val intent: Intent? = setClickIntent(it)
            intent?.let {
                startActivity(intent)
            }
        }
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        mBinding.recyclerView.layoutManager = layoutManager
        mBinding.recyclerView.adapter = customAdapter
    }

    abstract fun setData(): Array<String>

    abstract fun setClickIntent(name: String): Intent?
}