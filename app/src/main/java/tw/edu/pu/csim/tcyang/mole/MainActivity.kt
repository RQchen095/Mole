package tw.edu.pu.csim.tcyang.mole

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel // 確保這個 import 存在

// ----------------------------------------------------
// 假設 MoleViewModel.kt 如下 (這是最簡潔且可被觀察的 ViewModel 結構)
//
// class MoleViewModel : ViewModel(){
//     private var _counter by mutableLongStateOf(0L)
//     val counter: Long
//         get() = _counter
//
//     fun incrementCounter() {
//         _counter++
//     }
// }
// ----------------------------------------------------

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // 假設您有一個 MoleTheme
            // MoleTheme {
            MoleScreen()
            // }
        }
    }
}


@Composable
fun MoleScreen(
    moleViewModel: MoleViewModel = viewModel()
) {
    // 修正 1: 從 ViewModel 讀取數值 (Long)，而不是整個 State 物件
    val currentCounter = moleViewModel.counter

    Box(
        modifier = Modifier.fillMaxSize(),
        // 修正 2: 必須使用 contentAlignment 參數
        contentAlignment = Alignment.Center
    ) {
        // 分數顯示 (Text)
        Text(
            // 顯示分數
            text = "分數: $currentCounter",
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 50.dp)
        )

        // 地鼠圖片 (Image)
        // 修正 3: 地鼠 Image 必須在 Box 內部才能與 Text 協調佈局
        Image(
            // 假設 R.drawable.mole 存在
            painter = painterResource(id = R.drawable.mole),
            contentDescription = "地鼠",
            modifier = Modifier
                .offset { IntOffset(50, 200) }
                .size(150.dp)
                // 點擊事件：呼叫 ViewModel 的邏輯
                .clickable { moleViewModel.incrementCounter() }
        )
    }
}