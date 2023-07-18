package com.inc.lite.stationdemo.modules.components


import android.content.Context
import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix

@Composable
fun QrElement(
    modifier: Modifier = Modifier,
    url: String,
    width: Dp = 140.dp,
    height: Dp = 140.dp,
    foregroundColor: Color = Color.Black,
    backgroundColor: Color = Color.White
) {
    val context = LocalContext.current
    val density = context.resources.displayMetrics.density

    val bitmap = generateQRCodeBitmap(
        url,
        foregroundColor,
        backgroundColor,
        width.value.toInt(),
        height.value.toInt(),
        context
    )

    Image(bitmap = bitmap.asImageBitmap(), contentDescription = "QR Code")
}

private fun generateQRCodeBitmap(
    url: String,
    foregroundColor: Color,
    backgroundColor: Color,
    width: Int = 140,
    height: Int = 140,
    context: Context
): Bitmap {
    val writer = MultiFormatWriter()
    val bitMatrix: BitMatrix
    val density = context.resources.displayMetrics.density

    val widthPx = (width * density).toInt()
    val heightPx = (height * density).toInt()


    val hints = mutableMapOf<com.google.zxing.EncodeHintType, Any>()
    hints[com.google.zxing.EncodeHintType.MARGIN] = 0 // Set the margin to 0


    try {
        bitMatrix = writer.encode(url, BarcodeFormat.QR_CODE, widthPx, heightPx, hints)
    } catch (e: WriterException) {
        // Handle exception
        return Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
    }

    val width = bitMatrix.width
    val height = bitMatrix.height
    val pixels = IntArray(width * height)

    for (y in 0 until height) {
        val offset = y * width
        for (x in 0 until width) {
            pixels[offset + x] = if (bitMatrix[x, y]) foregroundColor.toArgb() else backgroundColor.toArgb()
        }
    }

    return Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888).apply {
        setPixels(pixels, 0, width, 0, 0, width, height)
    }
}