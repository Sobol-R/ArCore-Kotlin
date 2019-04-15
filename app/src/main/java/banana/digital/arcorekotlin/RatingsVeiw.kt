package banana.digital.arcorekotlin

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import banana.digital.arcorekotlin.model.Ratings
import kotlinx.android.synthetic.main.ratings_view.view.*

class RatingsVeiw @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    var ratings: Ratings? = null
        set(value) {
            field = value
            titleTextView.text = value?.title
        }

    init {
        View.inflate(getContext(), R.layout.ratings_view, this)
    }

}