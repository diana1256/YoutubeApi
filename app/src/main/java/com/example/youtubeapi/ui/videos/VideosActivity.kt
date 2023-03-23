package com.example.youtubeapi.ui.videos

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.view.LayoutInflater
import androidx.core.view.isVisible
import com.example.youtubeapi.R
import com.example.youtubeapi.core.network.ext.showToast
import com.example.youtubeapi.core.network.result.Status
import com.example.youtubeapi.core.network.ui.BaseActivity
import com.example.youtubeapi.databinding.ActivityVideosBinding
import com.example.youtubeapi.ui.item.ItemPlaylistsActivity
import com.example.youtubeapi.ui.playlist.PlaylistsActivity
import com.google.android.exoplayer2.*
import com.google.android.material.button.MaterialButton
import org.koin.androidx.viewmodel.ext.android.viewModel


class VideosActivity : BaseActivity<VideosViewModel,ActivityVideosBinding>() {

    override val viewModel: VideosViewModel by viewModel()
    override fun inflateViewBinding(inflater: LayoutInflater): ActivityVideosBinding {
        return ActivityVideosBinding.inflate(layoutInflater)
    }

    @SuppressLint("SourceLockedOrientationActivity")
    override fun initViewModel() {
        super.initView()
        viewModel.loading.observe(this){
            binding.progressCircular.isVisible = it
        }

        binding.containerToolbar.tvBack.setOnClickListener {
            finish()
        }

        val  id = intent.getStringExtra(PlaylistsActivity.DATA)
        viewModel.getVideo(id.toString()).observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.tvTitle.text = intent.getStringExtra(ItemPlaylistsActivity.KEY)
                    binding.tvDescription.text = intent.getStringExtra(ItemPlaylistsActivity.DESC)
                    viewModel.loading.postValue(false)
                }Status.ERROR -> {
                    viewModel.loading.postValue(true)
                    showToast(it.message.toString())
                }
                Status.LOADING ->  viewModel.loading.postValue(true)
            }
        }

        ExoPlayer()
        DialogCustom()
    }

    private fun DialogCustom() {
        binding.btnDownload.setOnClickListener {
            val view = LayoutInflater.from(this).inflate(R.layout.diolog_dowload, null)
            val builder = AlertDialog.Builder(this)
            builder.setView(view)
            val dialog = builder.create()
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.setCancelable(true)
            val btn = view.findViewById<MaterialButton>(R.id.btn_down)
            btn.setOnClickListener {
                dialog.dismiss()
            }
        }
    }

     private fun ExoPlayer() {
        val player = ExoPlayer.Builder(this).build()
        val mediaItem = MediaItem.fromUri(URI)
        player.setMediaItem(mediaItem)
        binding.exoPlayer.player = player
    }


    companion object{
        const val URI ="https://ia800208.us.archive.org/4/items/Popeye_forPresident/Popeye_forPresident_512kb.mp4"
    }
}

