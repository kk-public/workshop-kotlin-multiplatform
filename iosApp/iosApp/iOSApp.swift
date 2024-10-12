import SwiftUI
import shared

@main
struct iOSApp: App {
    
    init() {
        HelperKt.doInitKoin()
    }
        
	var body: some Scene {
		WindowGroup {
            AppView().ignoresSafeArea(.keyboard)
		}
	}
}

struct AppView: UIViewControllerRepresentable {

    func makeUIViewController(context: Context) -> UIViewController {
        AppViewControllerKt.AppViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}
