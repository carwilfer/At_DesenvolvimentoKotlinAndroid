package com.carwilfer.carlos_ferreira_dr3_tp1.ui.vendedor.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.carwilfer.carlos_ferreira_dr3_tp1.R
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.login_vendedor_fragment.*

class LoginVendedorFragment : Fragment() {

    private lateinit var viewModelLoginVendedor: LoginVendedorViewModel
    private lateinit var callbackManager: CallbackManager
    private lateinit var buttonFacebookLogin: LoginButton
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Initialize Firebase Auth
        auth =  Firebase.auth  // = FirebaseAuth.getInstance();

        val view = inflater.inflate(R.layout.login_vendedor_fragment, container, false)
        // Initialize Facebook Login button
        callbackManager = CallbackManager.Factory.create();

        buttonFacebookLogin = view.findViewById(R.id.buttonFacebookLogin)
        buttonFacebookLogin.fragment=this
        buttonFacebookLogin.setPermissions("email", "public_profile")
        buttonFacebookLogin.registerCallback(
            callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult) {
                    Log.i("FacebookLogin", "Sucesso")
                    handleFacebookAccessToken(result.accessToken);
                }

                override fun onCancel() {
                    Log.i("FacebookLogin", "Cancelado")
                }

                override fun onError(error: FacebookException?) {
                    Log.i("FacebookLogin", "${error?.message}")
                }

            }
        )

        viewModelLoginVendedor = ViewModelProvider(this).get(LoginVendedorViewModel::class.java)
        viewModelLoginVendedor.status.observe(viewLifecycleOwner, Observer{
            if (it){
                requireActivity().bottomNavigationView.visibility = View.VISIBLE
                findNavController().navigate(R.id.listaOculosFragment)
            }
        })
        viewModelLoginVendedor.msg.observe(viewLifecycleOwner, Observer{
            if (!it.isNullOrBlank())
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })
        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }
    private fun handleFacebookAccessToken(token: AccessToken) {
        val credential = FacebookAuthProvider.getCredential(token.token)
        auth.signInWithCredential(credential)
            .addOnSuccessListener {
                val user = auth.currentUser
                Log.i("FacebookUser", "$user")

                //No caso de sucesso ele navegara para a lista de clientes se apertar o botão acessar
                findNavController().navigate(R.id.listaClienteFragment)
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Authentication failed: ${it.message}",
                    Toast.LENGTH_SHORT).show()
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnFormLoginAcessarVendedor.setOnClickListener {
            val email = editTextFormLoginEmailVendedor.text.toString()
            val senha = editTextFormLoginSenhaVendedor.text.toString()
            viewModelLoginVendedor.verificarCredenciais(email, senha)
        }

        btnFormLoginCadastroVendedor.setOnClickListener {
            findNavController().navigate(R.id.cadastroVendedorFragment)
        }
    }
}