package ui.flows.app

import androidx.compose.runtime.Composable
import org.koin.compose.viewmodel.koinViewModel
import ui.flows.auth.AuthView
import ui.flows.main.MainView

@Composable
fun AppView(
    vm: AppViewModel = koinViewModel()
) {
    if (vm.isAuthorized) {
        MainView(
            onLogout = {
                vm.logout()
            }
        )
    } else {
        AuthView(
            onAuthorized = {
                vm.login()
            }
        )
    }
}