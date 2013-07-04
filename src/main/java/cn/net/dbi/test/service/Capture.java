package cn.net.dbi.test.service;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.LocationEvent;
import org.eclipse.swt.browser.LocationListener;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.browser.ProgressListener;
import org.eclipse.swt.browser.StatusTextEvent;
import org.eclipse.swt.browser.StatusTextListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Capture {
	Display display;
	Browser browser;
	Shell shell;

	public Capture(final String url, final String imgUrl) {
		display = new Display();
		shell = new Shell(display);
		FillLayout layout = new FillLayout();
		shell.setLayout(layout);
		browser = new Browser(shell, SWT.NONE);
		 
	 browser.addStatusTextListener(new StatusTextListener() {
		
		@Override
		public void changed(StatusTextEvent arg0) {
		 System.out.println("--"+arg0.text);
			
		}
	});

		browser.addProgressListener(new ProgressListener() {
			public void changed(ProgressEvent progressevent) {

			}

			public void completed(ProgressEvent progressevent) {
				capture(imgUrl, 0, 0);
			}

		});

		browser.setUrl(url);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	boolean capture = false;

	private synchronized void capture(String imgUrl, int xDifference,
			int yDifference) {
		System.out.println("begin captur .. " + capture);

		if (capture)
			return;

		GC gc = new GC(browser);
		Point size = browser.getSize();
		Image image = new Image(display, size.x - xDifference, size.y
				- yDifference);
		gc.copyArea(image, 0, 0);
		ImageLoader imageLoader = new ImageLoader();
		imageLoader.data = new ImageData[] { image.getImageData() };
		imageLoader.save(imgUrl, SWT.IMAGE_JPEG);
		gc.dispose();
		System.out.println("captured");
		capture = true;
		// image.dispose();
		// browser.dispose();
		// shell.close();

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// http://www.jointjs.com/demos/run.html?unit=fsa
		// new Capture("http://www.baidu.com", "google.jpg");
		new Capture("http://127.0.0.1:8080/test/img.jspa?id=1", "googlex.jpg");
	}

}
