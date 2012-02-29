import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;


public class BrowserFoo {

	public static void main(String[] args) throws InterruptedException {
		Display display = new Display();
		final Shell shell = new Shell(display);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				shell.open();
				Browser browser = new Browser(shell, SWT.NONE);
				browser.setUrl("http://google.com");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}).run();
		while (!shell.isDisposed()) Thread.sleep(10000);
	}
}
