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
3. 优化Fragment页面间切换动画
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

```

#### 页面A给页面B传值
``` java

```

#### 页面回调传值
``` java
/**
 * 类似于Activity的StartActivityForResult
 */
```

#### 定义页面切换动画
``` java

```

#### 定义页面样式
``` xml

```

### TODO
- [ ] 实现可以设置 滑动边缘:比如从上往下滑动
- [ ] xxx
- [ ] xxx

### 写在结尾
> 碧玉妆成一树高，万条垂下绿丝绦。不知细叶谁裁出，二月春风似剪刀。
