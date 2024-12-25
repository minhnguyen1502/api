package com.example.foodorder.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

@Suppress("DEPRECATION")
abstract class BaseActivity<VB : ViewBinding>(val bindingFactory: (LayoutInflater) -> VB) :
    AppCompatActivity() {
    // LayoutInflater anh xa den xml

    protected val binding: VB by lazy { bindingFactory(layoutInflater) }

    // lazy: Giá trị chỉ cần khởi tạo khi cần (ví dụ: cấu hình).
    // lateinit: Biến cần khởi tạo sau (ví dụ: Context, View).

    //public	Mặc định. Truy cập từ mọi nơi, kể cả ngoài module.
    //protected	Truy cập trong class khai báo và các class con. Không truy cập từ bên ngoài.
    //internal	Truy cập trong cùng module. Không lộ ra ngoài module.
    //private	Truy cập trong class khai báo (hoặc trong file nếu khai báo ở cấp file).

//    val Giá trị không đổi trong suốt chương trình.
//    var Giá trị cần thay đổi trong runtime.
//    Ví dụ	val name = "Kotlin"	var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        hideFullNavigation()

        initView()
        bindView()
    }

    open fun initView() {

    }

    open fun bindView() {

    }

    private fun hideFullNavigation() {
        try {
            val flags =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            window.decorView.systemUiVisibility = flags
            val decorView = window.decorView
            decorView.setOnSystemUiVisibilityChangeListener { visibility: Int ->
                if (visibility and View.SYSTEM_UI_FLAG_FULLSCREEN == 0) {
                    decorView.systemUiVisibility = flags
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        setResult(RESULT_OK)
    }
}