package sayan.banerjee.newsapp.news.model.persistence

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry

class ApplicationProvider {

    companion object ApplicationProvider {

        /**
         * Returns the application [Context] for the application under test.
         *
         * @see {@link Context.getApplicationContext
         */
        fun <T : Context> getApplicationContext(): Context? {
            return InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
        }
    }
}