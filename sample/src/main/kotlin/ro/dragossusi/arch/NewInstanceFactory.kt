package ro.dragossusi.arch

import ro.dragossusi.lifecycle.ViewModel
import ro.dragossusi.navigation.NavArguments
import kotlin.reflect.KClass

object NewInstanceFactory:ViewModel.Factory {
    override fun <VM : ViewModel> createViewModel(clazz: KClass<VM>, arguments: NavArguments?): VM {
        return clazz.java.newInstance()
    }
}