package com.example.workforfirstsem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment(R.layout.fragment_main) {

    private var userAdapter : UserAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userAdapter = UserAdapter(UserRepository.users) {
            showUserInfo(it)
        }
        view.findViewById<RecyclerView>(R.id.users).run {
            adapter = userAdapter
        }
    }

    private fun showUserInfo(id : Int) {
        val bundle = Bundle()
        bundle.putInt("id", id)
        val userInfoFragment = UserInfoFragment()
        userInfoFragment.arguments = bundle
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container, userInfoFragment)
            ?.addToBackStack("fragment")
            ?.commit()
    }
}
