package co.cmd.cook.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseViewBindingFragment<ViewBindingType : ViewBinding> : Fragment() {


    private var _binding: ViewBindingType? = null
    protected abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> ViewBindingType

    // This property is only valid between onCreateView and onDestroyView.
    val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        _binding = bindingInflater.invoke(inflater, container, false)
        return requireNotNull(_binding).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onCreated()

    }

    abstract fun onCreated()

    override fun onDestroy() {
        _binding = null
        super.onDestroy()

    }


}