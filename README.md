# JNICallBack
java和native层接口相互调用



关于java与c/c++的互相调用，网上有一大堆的文章介绍。但仔细观察可以发现，基本都是讲在java中调用一个本地方法，然后由该本地方法直接返回一个参数给java（例如，在java中定义的本地方法为private int callJNI(int i)）。但在大多数时候要求的并不是由开发者在java层主动去调JNI中的函数来返回想要的数据，而是由JNI主动去调java中的函数。举个最简单的例子，Android中的Camera，图像数据由内核一直往上传到java层，然而这些数据的传递并不需要开发者每一次主动去调用来JNI中的函数来获取，而是由JNI主动传给用java中方法，这类似于Linux驱动机制中的异步通知。

二、要求
      用NDK实现Java与C/C++互调，实现int，string，byte[]这三种类型的互相传递。

三、实现
      下面的实现中，每次java调用JNI中的某个函数时，最后会在该函数里回调java中相应的方法而不是直接返回一个参数。可能你会觉得这不还是每次都是由开发者来主动调用吗，其实这只是为了讲解而已，在实际应用中，回调java中的方法应该由某个事件（非java层）来触发。
      新建工程MyCallback，修改main.xml文件，在里面添加3个Button，分别对应3种类型的调用和3个TextView分别显示由JNI回调java时传给java的数据。
