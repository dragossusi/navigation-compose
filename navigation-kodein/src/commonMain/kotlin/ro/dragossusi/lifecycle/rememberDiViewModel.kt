package ro.dragossusi.lifecycle

import androidx.compose.runtime.Composable
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance
import org.kodein.di.subDI
import ro.dragossusi.navigation.NavArguments
import ro.dragossusi.navigation.emptyArguments
import kotlin.reflect.KClass

@Composable
inline fun <reified VM : ViewModel> DI.rememberDiViewModel(): VM {
    val factory = object : ViewModel.Factory {
        override fun <FVM : ViewModel> createViewModel(clazz: KClass<FVM>, arguments: NavArguments?): FVM {
            val vm by subDI(this@rememberDiViewModel) {
                bindProvider { arguments ?: emptyArguments() }
            }.instance<VM>()
            return vm as FVM
        }
    }
    return rememberViewModel(factory)
}