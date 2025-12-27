
import android.content.Context;
import java.lang.reflect.Method;
import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import android.annotation.SuppressLint;

public class Main {

	static Application app() {
		try {
			@SuppressLint("")
			Class<?> clazz = Class.forName("android.app.ActivityThread");
			Method currentApplication = clazz.getMethod("currentApplication");
			if (!currentApplication.isAccessible()) {
				currentApplication.setAccessible(true);
			}
			Object instance = currentApplication.invoke(null);
			boolean ins = (instance instanceof Application);
			System.out.println("currentApplication: " + ins);
			return (Application) instance;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		new Handler(Looper.getMainLooper()).post(() -> {
			try {
				Context c = app().getApplicationContext();
				Toast.makeText(c, "Hallo", 1).show();
			} catch (Throwable e) {
			}
		});
	}
}

