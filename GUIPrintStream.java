
import java.awt.TextComponent;
import java.io.OutputStream;
import java.io.PrintStream;
import javax.swing.SwingUtilities;
import javax.swing.text.JTextComponent;
public class GUIPrintStream extends PrintStream {
	private TextComponent component;
	private StringBuffer sb = new StringBuffer();
	public GUIPrintStream(OutputStream out, TextComponent component) {
		super(out);
		this.component = component;
	}
	/**
	 * 重写write()方法，将输出信息填充到GUI组件
	 */
	@Override
	public void write(byte[] buf, int off, int len) {
		final String message = new String(buf, off, len);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				sb.append(message);
				component.setText(sb.toString());
			}
		});
	}
}