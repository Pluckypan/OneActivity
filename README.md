# OneActivity
开局一个Activity,结局仍然一个Activity


### 背景故事
> 最近项目中使用 `Android` 过渡动画 `Transition`,在 `Activity` 页面间切换时,出现了一些奇怪的BUG,比如从 **A页面** 切换至 **B页面**,再从 **B页面** 返回 **A页面** 时,**A页面** 竟然残留 **B页面** 的画面,太惊悚了.
而替换成 `Fragment` 时,这个问题就不会出现了.使用 `Fragment` 整体感觉上要简单很多,而且体验上流畅很多。 另外一个重要的原因是:不同 `Activity` 绘制在不同的 `Window` 上,每个 `window` 都会持有一个焦点,
这样在存在 **虚拟导航栏** 的手机上,页面切换时,会触发 **导航栏** 和 **状态栏** (沉浸式模式下) 闪动一下,体验不是很好。基于这些,有了一个想法,如果全局只有一个 `Activity` 就太酷了。
于是就有了这个项目。**`OneActivity`** 基于 项目 [**FragmentMaster**](https://github.com/fengdai/FragmentMaster),
由于原项目已经很久没人维护,决定另起炉灶。

### 做了些什么
在 **FragmentMaster** 的基础上
1. 修复了一些BUG
2. 重新调整了项目的结构
3. 优化Fragment页面间切换动画,支持设定动画执行时间
4. 踩平Fragment一些坑并优化Fragment性能
5. 支持gradle引用

### 支持哪些功能
1. 全局仅一个 `Activity`
2. 支持侧滑返回上一个页面,此功能支持配置 **开启** 和 **关闭**
3. `Fragment` 页面支持 **返回键** `onBackPress` 回调
4. 支持定义不同 `Fragment` 页面的切换动画
5. 最低兼容版本 `Android API 15`

### 功能演示及代码说明

#### 打开一个页面
``` java
// 打开界面是通过request来实现的
Request request = new Request(ColorFragment.class);
fragment.startFragment(request);
```

#### 页面A给页面B传值
``` java

//传值
Request request = new Request(ColorFragment.class);
request.putExtra(KEY_COLOR, color);
fragment.startFragment(request);

//接收值
mColor = getRequest().getIntExtra(KEY_COLOR, -1);
```

#### 页面回调传值
``` java
/**
 * 类似于Activity的StartActivityForResult
 */

//请求
public static void openPage(BaseFragment fragment, int color, int code) {
    Request request = new Request(ColorFragment.class);
    request.putExtra(KEY_COLOR, color);
    fragment.startFragmentForResult(request, code);
}

//请求回调
@Override
public void onFragmentResult(int requestCode, int resultCode, Request data) {
    super.onFragmentResult(requestCode, resultCode, data);
    if (resultCode == RESULT_OK && data != null) {
        switch (requestCode) {
            case COLOR_CODE:
                onColorSelected(data.getIntExtra(ColorFragment.KEY_COLOR, mColor));
                break;
        }
    }
}
```

#### 定义页面切换动画
``` java
@Override
public PageAnimator onCreatePageAnimator() {
    return new WeChatPageAnimator();
}

// 如果要指定动画执行时间
@Override
public PageAnimator onCreatePageAnimator() {
    return new WeChatPageAnimator(600);
}

```

#### 禁止侧滑功能
``` xml
// 目前仅支持LEFT侧滑控制
// TODO: Plucky 2018/1/7 下午1:08  实现滑动的位置Edge:LEFT、RIGHT、TOP、BOTTOM
boolean allow=mAnimatorBean.getType() != VerticalSlideAnimator.class;
allowSwipeBack(allow);
```

#### 页面返回键监听
``` java
@Override
public void onBackPressed() {
    super.onBackPressed();
    mColorTV.setText("Finish");
}
```

#### 控制软键盘弹出
``` java
setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//其他 比如
setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
```

#### 设置主题
``` java
//在定义Fragment类的地方使用注解如下
@Configuration(theme = R.style.Theme_AppCompat_Light)
public class LightThemeFragment extends MasterFragment {

}
```

### TODO
- [ ] 实现可以设置 滑动边缘:比如从上往下滑动
- [ ] xxx
- [ ] xxx

### 写在结尾
> 碧玉妆成一树高，万条垂下绿丝绦。不知细叶谁裁出，二月春风似剪刀。
