import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.browser.ProgressListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class RecorderSpike {

    Browser browser;
        
    public RecorderSpike(Composite parent) {
        browser = new Browser(parent, SWT.MAX);
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        final Display display = new Display();
        final Shell shell = new Shell(display);
        shell.setLayout(new FillLayout());
        final RecorderSpike browserExample = new RecorderSpike(shell);
        shell.open();
        final Browser browser = browserExample.browser;
        String text = 
            "<html><head></head>" + 
            "<body>" + 
            "<h1>YEAH</h1>" +
            "<div style='background: yellow; width: 100px; height: 100px;' id='outer' class='hello_world'>" +
            "<div style='background: red; width: 50px; height: 50px;' id='inner' class='inner_class different'></div>" +
            "<span id='span_id' class='span_class'>This should not bubble</span>" +
            "</div>" +
            "<input type='text' value='hello' size='30'/>" + 
            "<input type='checkbox' value='0' checked='checked'/>" + 
            "<button id='pavan' class='submit primary funky' name='HO' value='HO'>" + 
            "</body>" + 
            "</html>";
        browser.setText(text, true);
        new BrowserFunction(browser, "clickedOnSomething") {
            @Override
            public Object function(Object[] arguments) {
                System.out.println("TAG:" + arguments[0]);
                System.out.println("ID:" + arguments[1]);
                System.out.println("CLASS NAMES:" + arguments[2]);
                System.out.println("EVENT TYPE:" + arguments[3]);
                if (arguments.length == 6) {
                    System.out.println("ELEMENT TYPE:" + arguments[4]);
                    System.out.println("ELEMENT VALUE:" + arguments[5]);
                }
                System.out.println();
                return null;
            }
        };
        browser.addProgressListener(new ProgressListener() {
            public void changed(ProgressEvent event) {
            }

            public void completed(ProgressEvent event) {
                try {
                    ClassLoader loader = Thread.currentThread().getContextClassLoader();
                    browser.execute(FileUtils.readFileToString(new File(loader.getResource("jquery.js").getFile())));
                    browser.execute(FileUtils.readFileToString(new File(loader.getResource("sample_js.js").getFile())));
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        display.dispose();
    }
}
