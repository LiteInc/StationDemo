package com.inc.lite.stationdemo.ui.components

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.inc.lite.stationdemo.viewModels.HomeViewModel


@Composable
fun VideoElement(
    modifier: Modifier = Modifier,
    url: String
) {
    Box(modifier, contentAlignment = Alignment.Center) {
        VideoPlayer(videoUrl = url)
    }
}


@Composable
fun VideoPlayer(
    modifier: Modifier = Modifier,
    videoUrl: String,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val exoPlayer = ExoPlayer.Builder(context).build()
    val mediaItem = MediaItem.fromUri(Uri.parse(videoUrl))

    exoPlayer.setMediaItem(mediaItem)

    val playerView = StyledPlayerView(context)

    playerView.player = exoPlayer

    DisposableEffect(
        AndroidView(
            modifier = modifier,
            factory = {
                playerView
            }
        ){ view ->
            view.useController = false
//            view.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            view.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
        }
    ){

        exoPlayer.prepare()
        exoPlayer.playWhenReady = true
        exoPlayer.addListener(object : Player.Listener {
            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                if (playbackState == Player.STATE_ENDED) {
                    viewModel.videoPlaying(false)
                    // Video has finished playing
                    // Perform any action you want here
                }
            }
        })

        onDispose {
            exoPlayer.release()
        }
    }

}
