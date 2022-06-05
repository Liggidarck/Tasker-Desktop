import tornadofx.*

class MyView: View() {
    override val root = borderpane {
        top<TopView>()
        bottom<BottomView>()
    }

}

class TopView: View() {
    override val root = label("TEST")
}


class BottomView: View() {
    override val root = label("TEST")
}