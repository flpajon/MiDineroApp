package ar.com.midinero.ui.viewpager

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ar.com.midinero.R
import ar.com.midinero.databinding.FragmentLogInViewPagerBinding
import ar.com.midinero.ui.adapter.LogInViewPagerAdapter
import ar.com.midinero.ui.view.LogInFragment
import ar.com.midinero.ui.view.SingUpFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogInViewPagerFragment : Fragment(R.layout.fragment_log_in_view_pager) {

    private lateinit var binding: FragmentLogInViewPagerBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLogInViewPagerBinding.bind(view)

        val adapter =
            LogInViewPagerAdapter(
                childFragmentManager,
                lifecycle
            )
        adapter.addFragment(LogInFragment(), getString(R.string.btn_ingresar))
        adapter.addFragment(SingUpFragment(), getString(R.string.btn_singup))

        binding.logInViewPager.adapter = adapter


        TabLayoutMediator(binding.tabLayout, binding.logInViewPager) { tab, position ->
            tab.text = adapter.getTittle(position)
        }.attach()
    }
}