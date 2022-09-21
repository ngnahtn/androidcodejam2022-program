package vn.onesk.common

import android.graphics.drawable.Drawable
import android.text.Spanned
import android.view.View
import androidx.core.text.HtmlCompat
import androidx.core.text.toSpanned
import com.google.gson.Gson
//import vn.onesk.helper.video.ImageUtils
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.*
import kotlin.math.roundToInt

fun Any?.toJson(): String = if (this == null) "null" else Gson().toJson(this)

fun <T> String?.jsonToObject(dataClass: Class<T>): T? {
    return try {
        Gson().fromJson(this, dataClass)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun <T> String?.jsonToListObject(dataClass: Class<Array<T>>): List<T>? {
    return try {
        return Gson().fromJson(this, dataClass).toList()
    } catch (e: NullPointerException) {
        e.printStackTrace()
        null
    }
}

private val df = DecimalFormat("0.0")

fun View?.show() {
    this?.visibility = View.VISIBLE
}

fun View?.visible(isVisible: Boolean) {
    this?.visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun View?.hide() {
    this?.visibility = View.GONE
}

fun View?.invisible() {
    this?.visibility = View.INVISIBLE
}

fun Float?.formatNumber() = df.format(this)

fun Int?.formatNumber() = DecimalFormat("#.###").format(this)

fun String?.toHtml(): Spanned {
    if (this.isNullOrEmpty()) return "".toSpanned()
    return HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_COMPACT)
}

fun String.trimSpace(): String {
    return this.trim().replace(Regex("(\\s)+"), " ")
}

fun String?.getDefaultValue(): String {
    if (this.isNullOrEmpty())
        return "0"
    return this
}

fun <E> List<E>.random(): E? = if (size > 0) get(Random().nextInt(size)) else null

//fun Drawable.equal(drawable: Drawable): Boolean {
//    return ImageUtils.areDrawablesIdentical(this, drawable)
//}

fun Double.round(places: Int): Double {
    var value = this
    require(places >= 0)
    val factor = Math.pow(10.0, places.toDouble()).toLong()
    value *= factor
    val tmp = value.roundToInt()
    return tmp.toDouble() / factor
}

fun Double.round1DecimalPlace(): String {
    val df = DecimalFormat("#.#")
    df.roundingMode = RoundingMode.CEILING
    return df.format(this)
}

fun Float.round1DecimalPlace(): String {
    val df = DecimalFormat("#.#")
    df.roundingMode = RoundingMode.CEILING
    return df.format(this).replace(',', '.')
}

inline fun <T : Any, R> T?.ifNotNullOrElse(ifNotNullPath: (T) -> R, elsePath: () -> R) =
    let { if (it == null) elsePath() else ifNotNullPath(it) }

inline fun <E : Any, T : Collection<E>> T?.withNotNullNorEmpty(func: T.() -> Unit) {
    if (this != null && this.isNotEmpty()) {
        with(this) { func() }
    }
}

inline fun <E : Any, T : Collection<E>> T?.whenNotNullNorEmpty(func: (T) -> Unit) {
    if (this != null && this.isNotEmpty()) {
        func(this)
    }
}

inline fun <T> T?.notNull(f: (T) -> Unit) {
    if (this != null) {
        f(this)
    }
}

//fun String?.fromVNToWeightAPI(): String {
//    if (this == null)
//        return ""
//    return Constants.WeightConstants.WeightConvertConstants.mapVNToConst[this.trim()] ?: this
//}
//
//fun String?.fromWeightAPIToVN(): String {
//    if (this == null)
//        return ""
//    return Constants.WeightConstants.WeightConvertConstants.mapConstToVN[this.trim()] ?: this
//}

fun Long.getTimeStampSecs(): Long {
    return if (this > 9999999999) this / 1000 else this
}

fun Long.getTimeStampMillis(): Long {
    return if (this < 9999999999) this * 1000 else this
}

fun String.formatFloatString(): String {
    return String.format("%.1f", this.toFloatOrNull())
}