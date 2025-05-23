# Android-Studio
    这是一个Android-studio学习过程的记录文档，我会将每天学了什么内容，做了什么功能放在这个文档中。  
## 5.23  
### 1、Activity与UI   
 Activity是很重要的一个组件，每一个Activity都表示一个页面，大部分的Activity都被设计为占据整个屏幕。在使用Android Studio每当创建一个新的Activity的时候，会自动生成如下的一段框架代码以及一个xml文件：
![alt text](image/activity(base).png)
在上述代码中setContentView方法用来把一个UI分配给一个Activity，这个函数中的R.layout.activity_main指的就是layout文件夹下自动生成的activity_main.xml文件。一般情况下都使用这种方式来把xml中的布局关联到Activity上。这个xml文件中基础代码及布局如下：
![alt text](image/xml(base).png)
上图中的constraintlayout是一个布局模式，使用约束关系来创建复杂的页面，例如图中的TextView通过约束到父布局的顶部、底部、左右来使得这个TextView显示在布局的正中间。当然还有其他的布局模式，比如说：FrameLayout,LinearLayout,RelativerLayout,GridLayout。下面详细介绍这几种布局分别是什么样子的：
#### （1）FrameLayout
FrameLayout是一种最简单的布局管理器，它只是简单的把每一个子视图放置在边框内，默认放置在布局的左上角，不过可以用gravity来改变其位置。在添加子视图的时候，它会把新的子视图堆积在前一个子视图的上面，每一个新的子视图可能会遮挡住上一个。具体代码及布局如下图所示：
![alt text](image/FrameLayout.png)
可以看到在这个布局中放了一个内容为“TextView”的TextView子视图，这个时候如果我们再添加一个新的子视图会被堆积在原本的这个TextView上。
#### （2）LinearLayout
LinearLayout有两种，一种是按照垂直方向来对其每一个子视图，另一种按照水平方向来对其每一个子视图，可以使用weight来控制每一个子视图在空间内的相对大小，比如水平方向的如图所示：
![alt text](image/Linearlayout.png)
可以看到在LinearLayout布局中水平放置了一个TextView和一个Button，这两个子视图按照1:2的水平比例占据整个布局，可以在每个子视图的weight中修改。垂直布局与此类似，只需要将android:orientation="horizontal"中的horizontal改成vertical就可以了。
#### （3）RelativeLayout
relativeLayout使用相对位置来进行布局，可以定义每一个子视图之间或子视图与屏幕边界之间的相对位置，与constraintlayout布局的使用方法类似。如下图我们采用了相对布局定义了TextView和Button之间以及它们和边界间的距离：
![alt text](image/RelativeLayout.png)
#### （4）GridLayout
这是一种网格化布局形式，会在一系列的行与列中进行布局，比如下图所示：
![alt text](image/GridLayout.png)
可以看到每个子视图中都有"layout_row"和"layout_column"属性，代表着它处在第几行第几列。  
上述的这些布局都可以扩展，以适应各种设备的尺寸，由于它们不使用绝对位置，所以在设计上可以很好的运行在各种Android设备上。在以上所有的图片中，我们可以看到对于"layout_width"和"layout_height"既有直接赋值，比如说：android：layout_width="3dp"就代表着宽度为3dp。这是一种绝对赋值的方式，还有的使用了"match_parent"和"wrap_content"两种相对的方式，match_parent表示和父布局的对应值保持一致，wrap_content表示正好包裹住内部元素。
### 2、Activity的生存期
Activity具有活动状态、暂停状态、停止状态和非活动状态。  
<b>活动状态</b>：当一个Activity位于栈顶，可见且具有焦点即为活动状态，可以接受用户的输入。Android会不惜一切代价来保持这个Activity处于活动状态。  
<b>暂停状态</b>：当一个Activity是可见的，但是不具有焦点的时候就是暂停状态，比如说有一个非全屏或者透明的Activity位于另一个Activity之前时，下面的这个Activity就会处于暂停状态。这个状态仍然被当作近似于活动状态但是它不会接受输入。  
<b>停止状态</b>：当一个Activity完全不可见的时候就处于停止状态，此时这个Activity仍存在于内存中，但是当其他地方要用到内存的时候，他就会成为首选的被终止对象。尽管Activity处于停止状态是不可见的，但是它的数据及UI还是会被保存着，我的理解是当一个Activity不可见时，证明有另一个全屏的Activity处于它前面，此时如果我想返回上一个页面，那么保存着之前不可见Activity的数据及UI就可以直接使用，可以节省时间。  
<b>非活动状态</b>：Activity被终止后，在下次启动它之前就处于非活动状态，此时这个Activity已经从Activity栈中移除了，在它可以被再次显示和使用之前要重新启动。  
了解了Activity的状态之后就可以更好地理解Activity的生命周期，首先Activity有完整生存期、可见生存期和活动生存期。<b>完整生存期</b>即为Activity被创建后一直到非活动状态的这段时期，对应的开始与结束函数为onCreate和onDestroy；<b>可见生存期</b>为Activity从可见的到不可见的这段时期，对应两个函数为onStart和onStop；<b>活动生存期</b>为Activity具有焦点的这段时间，对应的两个函数为onResume和onPause。如下图为各种函数的使用：  
![alt text](image/生存周期.png)
### 3、RecyclerView
RecyclerView是Android Studio中的一个列表控件，要进行使用需要一个Adapter，Adapter用于绑定数据到RecyclerView的每一个ViewHolder上，当然Adapter不仅仅用于RecyclerView，它适用于所有扩展了AdapterView类的视图组。对于recyclerView的Adapter比如下面这个todoAdapter：
![alt text](image/todoAdapter.png)
Adapter也需要一个xml文件用于定义每个Adapter的布局，比如图中的todo_view.xml就是，RecyclerView会使用这个xml来创建每一个ViewHolder中的对象。对于这个Adapter，我实现了获取todoList中的数据并显示，还实现了一个删除按钮用于删除todoList中对应的数据。
### 4、Todo-List小应用
使用了RecylclerView实现了一个待办事务列表，具体的功能如下：
#### （1）点击提交按钮时获取editView中的文本然后加到recylerView中，如下两图所示：
![alt text](image/Todo-List1-1.png)
![alt text](image/Todo-List1-2.png)
#### （2）点击删除按钮时删除该ViewHolder对象对应的内容，如下图所示：
![alt text](image/Todo-List2.png)