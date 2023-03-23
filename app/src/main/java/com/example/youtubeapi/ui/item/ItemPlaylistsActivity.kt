package com.example.youtubeapi.ui.item

import android.app.ActivityManager
import android.content.Intent
import android.view.LayoutInflater
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import com.example.youtubeapi.core.network.ext.loadImage
import com.example.youtubeapi.core.network.result.Status
import com.example.youtubeapi.core.network.ui.BaseActivity
import com.example.youtubeapi.databinding.ActivityItemPlaylistsBinding
import com.example.youtubeapi.ui.playlist.PlaylistsActivity
import com.example.youtubeapi.core.network.ext.showToast
import com.example.youtubeapi.data.local.entity.remote.model.ItemsItem
import com.example.youtubeapi.ui.videos.VideosActivity
import com.livermor.delegateadapter.delegate.CompositeDelegateAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class ItemPlaylistsActivity : BaseActivity<ItemViewModel, ActivityItemPlaylistsBinding>() {

    private val adapterItem = CompositeDelegateAdapter(AdapterItem(this::onClick))
    private val registerForActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {}

    override val viewModel: ItemViewModel by viewModel()
    override fun inflateViewBinding(inflater: LayoutInflater): ActivityItemPlaylistsBinding {
        return ActivityItemPlaylistsBinding.inflate(layoutInflater)
    }

    override fun initViewModel() {
        viewModel.loading.observe(this) {
            binding.progressCircular.isVisible = it
        }
        val id = intent.getStringExtra(PlaylistsActivity.DATA)
        showToast(id.toString())

        binding.containerToolbar.tvBack.setOnClickListener {
            finish()
        }

        viewModel.playlists(id.toString()).observe(this) {
            it.data?.items?.let { it1 ->
                adapterItem.swapData(it1)
                binding.tvTitle.text = intent.getStringExtra(PlaylistsActivity.TITLE)
                binding.ivImage.loadImage(intent.getStringExtra("image").toString())
                when (it.status) {
                    Status.SUCCESS -> {
                        binding.rvPlaylistItem.adapter = adapterItem
                        viewModel.loading.postValue(false)
                    }
                    Status.LOADING -> {
                        viewModel.loading.postValue(true)
                    }
                    Status.ERROR -> {
                        viewModel.loading.postValue(true)
                        showToast(it.message.toString())
                    }
                }
            }
        }
    }

    private fun onClick(item: ItemsItem) {
        val i = Intent(this, VideosActivity::class.java)
        i.putExtra(DESC, item.snippet.description)
        i.putExtra(KEY, item.snippet.title)
        registerForActivity.launch(i)
    }



    companion object {
        const val DESC = "desc"
        const val KEY = "tit"
    }
}

