package sayan.banerjee.newsapp.common

import android.content.Context
import android.util.Log
import android.widget.ImageView
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest


@PrepareForTest(Log::class)
open class BaseUnitTest {

    @Mock
    protected var mContext: Context = Mockito.mock(Context::class.java)

    @Mock
    protected var mImageView: ImageView = Mockito.mock(ImageView::class.java)

    @Test
    fun test() {
        PowerMockito.mockStatic(Log::class.java)
    }
}