package com.aulaandroid.ifoodclone

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.aulaandroid.ifoodclone.databinding.FragmentLoginBinding
import com.aulaandroid.ifoodclone.viewmodels.UserViewModel

class LoginFragment : Fragment() {

    companion object {
        const val LOGIN_SUCCESSFUL: String = "LOGIN_SUCCESSFUL"
    }

    private val userViewModel: UserViewModel by viewModels()
    private lateinit var savedStateHandle: SavedStateHandle
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        userViewModel =
//            ViewModelProvider(this).get(UserViewModel::class.java)
        val navController = findNavController()
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        savedStateHandle = findNavController().previousBackStackEntry!!.savedStateHandle
        savedStateHandle.set(LOGIN_SUCCESSFUL, false)

        val usernameEditText = view.findViewById<EditText>(R.id.tv_user)
        val passwordEditText = view.findViewById<EditText>(R.id.tv_password)
        val loginButton = view.findViewById<Button>(R.id.bt_login)

        loginButton.setOnClickListener {
            hideKeyboard(view)
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            if (userViewModel.login(username, password)) {
                savedStateHandle.set(LOGIN_SUCCESSFUL, true)
                findNavController().popBackStack()
            } else {
                Toast.makeText(view.context, getText(R.string.invalid_password), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun hideKeyboard(view: View) {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(view.windowToken, 0)
    }
}