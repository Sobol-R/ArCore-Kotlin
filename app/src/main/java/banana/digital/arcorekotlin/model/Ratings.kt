package banana.digital.arcorekotlin.model

import banana.digital.arcorekotlin.Rating

class Ratings {

    var title: String? = null
    var ratings: MutableList<Rating>? = null

    companion object {

        const val TEST_RATINGS_COUNT = 5

        fun createtestRatings(): Ratings {
            val ratings = Ratings()
            ratings.title = "Стол"
            ratings.ratings = ArrayList()
            for (i in 0 .. TEST_RATINGS_COUNT) {
                ratings.ratings!!.add(Rating("user $i", (0..5).random()))
            }
            return ratings
        }

    }
}