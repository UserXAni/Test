
import android.content.Context;
import java.lang.reflect.Method;
import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

public class Main {

	static Application app() {
		try {
			Class<?> clazz = Class.forName("android.app.ActivityThread");
			Method currentApplication = clazz.getMethod("currentApplication");
			if (!currentApplication.isAccessible()) {
				currentApplication.setAccessible(true);
			}
			Object instance = currentApplication.invoke(null);
			if (instance instanceof Application) {
				System.out.println("currentApplication: " + (instance instanceof Application));
				return (Application) instance;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new RuntimeException();
	}

	public static void main(String[] args) {
		new Handler(Looper.getMainLooper()).post(() -> {
			Toast.makeText(app().getApplicationContext(), "Hallo", 1).show();
		});
	}
}

