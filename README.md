#EasyVolley
-----------------------------------------
EasyVolley旨在让http网络请求的编写更加便捷和顺畅。基于公司具体业务抽象出的框架，仅供抛砖引玉用。后期可能会改善局限性。
原理很简单，主要是利用建造者模式生成NetEngine对象，以链式调用的形式灵活组织需要的回调接口。另外，RopRequest把解析json的逻辑移到#parseNetworkResponse()方法内，于子线程中进行解析。

<h2>安装</h2>

**JitPack**
在工程根目录的build.gradle添加
````
allprojects {
  repositories {
    ...
    maven { url "http://jitpack.io" }
  }
}
````
````
dependencies {
  compile 'com.github.deerclops:EasyVolley:0.0.1'
}
````

<h2>使用</h2>
在引入了依赖的module的Application内，于onCreate()方法内进行初始化。
````
@Override
public void onCreate() {
	super.onCreate();
	Rop.init(this, "your app secret", "your app code",
		new Rop.ISessionProvider() {
			@Override
			public String getSession() {
				return "";
			}
		});
}
````

**执行post请求**

````
NetEngine.builder()
	.url("your base url") // 设置base url
  	.apiName("your api name") // 设置api名称，例如"account.company.login"
  	.apiVersion("your api version") // 设置api版本号，例如"1.0"
  	.addBodyParam("param name", "param value") // 传参方式1，默认该字段加密
  	// 传参方式2，第三个参数表示是否加密
  	.addBodyParam("param name", "param value", true) 
  	.addBodyParam(loginParam) // 传参方式3，直接传入对象，下面会有说明
  	.noSession() // 是否需要传递session
  	.onRequest(new IRequest() {
  		@Override
  		public void onRequestBegin() {
  			// 请求开始前UI相关处理
  			...
  		}

  		@Override
  		public void onRequestEnd() {
  			// 请求结束后相关处理
  			...
  		}
  	})
  	.onSuccess(new AbstractSuccess<Result>(Result.class) {
  		@Override
  		public void success(Result result) {
  			// Result为定义的实体类，映射response中的json
  			...
  		}
  	})
  	.onToastError(new IToastError() {
  		@Override
  		public void onToastError(String errorMsg) {
  			// 弹出错误提示，例 toast(errorMsg)
  			...
  		}
  	})
  	.onFailure(new IFailure() {
  		@Override
  		public void onFailure(String msg) {
  			// 请求失败均回调至此
  			...
  		}
  	})
  	.build()
  	.fetch();
````

**POST请求的参数传递**

1. addBodyParam(String paramName, String paramValue)
  默认会把该字段加密
2. addBodyParam(String paramName, String paramValue, boolean needEncrypted)
  上面方法的重载，第三个参数可设置是否加密
3. addBodyParam(Object paramObj)
  直接传入一个对象。最后会通过反射将该对象内非static的字段生成加密map和非加密map。
  可在对象的filed上添加@Encrypt注解表示该字段是否参与加密。例：
````
public class LoginParam {
    public String account; // 不添加注解默认参与加密
    public boolean force;
    public int userType;
    public long deviceNum;
    public int deviceType;
    @Encrypt(value = false)
    public String password; // 该字段不参与加密

    public LoginParam() {}
}
````