# Android-Studio
    这是一个Android-studio学习过程的记录文档，我会将每天学了什么内容，做了什么功能放在这个文档中。  
## 5.23  
### 1、Activity  
 每一个Activity都表示一个页面，大部分的Activity都被设计为占据整个屏幕。在使用Android Studio创建一个新的Activity的时候，会自动生成如下的一段框架代码：
![alt text](image/activity(base).png)
其中setContentView方法用来把一个UI分配给一个Activity，这个函数中的R.layout.activity_main指的是layout文件夹下的activity_main.xml文件。一般情况下都使用这种方式来把xml中的布局关联到Activity上。  
