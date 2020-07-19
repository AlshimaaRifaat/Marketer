package com.example.themarketer.ui.GettingStarted

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.example.themarketer.R
import com.example.themarketer.ui.ChooseLoginRegister.ChooseLoginRegisterFragment
import kotlinx.android.synthetic.main.activity_getting_started.*


class GettingStartedActivity : AppCompatActivity() ,View.OnClickListener{
    companion object {
        private const val MIN_SCALE = 0.65f
        private const val MIN_ALPHA = 0.3f
    }
    private lateinit var gettingStartedPagerAdapter: GettingStartedPagerAdapter
    //private val uiHelper = UiHelper()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
         window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_getting_started)
        gettingStartedPagerAdapter = GettingStartedPagerAdapter(supportFragmentManager)
        addPagerFragments()
        myViewPager.adapter = gettingStartedPagerAdapter
        myViewPager.setPageTransformer(true, this::zoomOutTransformation)
        // btnGettingStarted.typeface = uiHelper.getTypeFace(TypeFaceEnum.BUTTON_TEXT, this)
        myViewPager.addOnPageChangeListener(ViewPagerListener(this::onPageSelected))
        btnGettingStarted.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.btnGettingStarted -> {  replaceFragment(ChooseLoginRegisterFragment()) }
        }

    }
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            ?.beginTransaction()
            ?.replace(
                R.id.gettingStartedContainer,
                fragment
            )
            ?.commitNow()
    }
    private fun onPageSelected(position: Int) {
        when (position) {
            0 -> {
                firstDotImageView.setImageResource(R.drawable.current_position_icon)
                secondDotImageView.setImageResource(R.drawable.disable_position_icon)
                thirdDotImageView.setImageResource(R.drawable.disable_position_icon)
                fourthDotImageView.setImageResource(R.drawable.disable_position_icon)
                fifthDotImageView.setImageResource(R.drawable.disable_position_icon)
                sixthDotImageView.setImageResource(R.drawable.disable_position_icon)
            }
            1 -> {
                firstDotImageView.setImageResource(R.drawable.disable_position_icon)
                secondDotImageView.setImageResource(R.drawable.current_position_icon)
                thirdDotImageView.setImageResource(R.drawable.disable_position_icon)
                fourthDotImageView.setImageResource(R.drawable.disable_position_icon)
                fifthDotImageView.setImageResource(R.drawable.disable_position_icon)
                sixthDotImageView.setImageResource(R.drawable.disable_position_icon)
            }
            2 -> {
                firstDotImageView.setImageResource(R.drawable.disable_position_icon)
                secondDotImageView.setImageResource(R.drawable.disable_position_icon)
                thirdDotImageView.setImageResource(R.drawable.current_position_icon)
                fourthDotImageView.setImageResource(R.drawable.disable_position_icon)
                fifthDotImageView.setImageResource(R.drawable.disable_position_icon)
                sixthDotImageView.setImageResource(R.drawable.disable_position_icon)
            }
            3 -> {
                firstDotImageView.setImageResource(R.drawable.disable_position_icon)
                secondDotImageView.setImageResource(R.drawable.disable_position_icon)
                thirdDotImageView.setImageResource(R.drawable.disable_position_icon)
                fourthDotImageView.setImageResource(R.drawable.current_position_icon)
                fifthDotImageView.setImageResource(R.drawable.disable_position_icon)
                sixthDotImageView.setImageResource(R.drawable.disable_position_icon)
            }
            4 -> {
                firstDotImageView.setImageResource(R.drawable.disable_position_icon)
                secondDotImageView.setImageResource(R.drawable.disable_position_icon)
                thirdDotImageView.setImageResource(R.drawable.disable_position_icon)
                fourthDotImageView.setImageResource(R.drawable.disable_position_icon)
                fifthDotImageView.setImageResource(R.drawable.current_position_icon)
                sixthDotImageView.setImageResource(R.drawable.disable_position_icon)
            }
            5 -> {
                firstDotImageView.setImageResource(R.drawable.disable_position_icon)
                secondDotImageView.setImageResource(R.drawable.disable_position_icon)
                thirdDotImageView.setImageResource(R.drawable.disable_position_icon)
                fourthDotImageView.setImageResource(R.drawable.disable_position_icon)
                fifthDotImageView.setImageResource(R.drawable.disable_position_icon)
                sixthDotImageView.setImageResource(R.drawable.current_position_icon)
            }
        }
    }
    private fun addPagerFragments() {
        gettingStartedPagerAdapter.addFragments(IntroFirstFragment())
        gettingStartedPagerAdapter.addFragments(IntroSecondFragment())
        gettingStartedPagerAdapter.addFragments(IntroThirdFragment())
        gettingStartedPagerAdapter.addFragments(IntroFourthFragment())
        gettingStartedPagerAdapter.addFragments(IntroFifthFragment())
        gettingStartedPagerAdapter.addFragments(IntroSixthFragment())
    }
    private fun zoomOutTransformation(page: View, position: Float) {
        when {
            position < -1 ->
                page.alpha = 0f
            position <= 1 -> {
                page.scaleX = Math.max(MIN_SCALE, 1 - Math.abs(position))
                page.scaleY = Math.max(MIN_SCALE, 1 - Math.abs(position))
                page.alpha = Math.max(MIN_ALPHA, 1 - Math.abs(position))
            }
            else -> page.alpha = 0f
        }
    }
}

