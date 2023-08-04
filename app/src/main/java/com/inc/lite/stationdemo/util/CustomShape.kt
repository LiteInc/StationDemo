package com.inc.lite.stationdemo.util

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import com.pixplicity.sharp.Sharp

class Polygon : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val s = "M0,0h24v24h-24z"
        val pathString = "<path d=\"M24.755 33.2793L2.16441 20.0665C-0.721468 18.382 -0.721468 15.6201 2.16441 13.9335L24.757 0.720727C27.6429 -0.967918 30 0.414072 30 3.78727V30.2127C30 33.5859 27.6388 34.9679 24.757 33.2793H24.755Z\" fill=\"#5DAEAC\"/>"
//        val shart = Sharp.
        val path = Sharp.loadPath(pathString)

        return Outline.Generic(
            path.asComposePath()
//           PathParser().parsePathString(pathString).toPath()
        )
    }
}
