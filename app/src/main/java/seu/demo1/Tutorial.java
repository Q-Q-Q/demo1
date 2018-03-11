package seu.demo1;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import android.graphics.Color;
import android.widget.TextView;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
import de.robv.android.xposed.XposedBridge;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;

/**
 * Created by lenovo on 2018/3/8.
 */

public class Tutorial implements IXposedHookLoadPackage{
    public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {
        // 将包名不是 com.example.login 的应用剔除掉
        if (!lpparam.packageName.equals("com.tencent.mm"))
            return;
        XposedBridge.log("Loaded app: " + lpparam.packageName);
        //(String)
        findAndHookMethod("java.security.MessageDigest", lpparam.classLoader, "getInstance", String.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("应用程序包名"+lpparam.packageName);
                    XposedBridge.log("函数名：getInstance(String)");
                    XposedBridge.log("参数："+param.args[0]);
                }
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("结果："+param.getResult().toString());
                }
            });
    }
}

