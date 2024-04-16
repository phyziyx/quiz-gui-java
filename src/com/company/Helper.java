package com.company;

import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.util.Locale;



/**
 * Helper library
 * @author Ali
 */
public class Helper {
	/**
	 * Calculates the percentage of a value obtained out of a total value.
	 *
	 * @param obtained The value obtained.
	 * @param total The total value.
	 * @return The percentage of the value obtained out of the total value.
	 */
	public static double calculatePercentage(double obtained, double total) {
		return obtained * 100 / total;
	}

	/**
	 * Creates a table model with uneditable cells
	 * @return DefaultTableModel with isCellEditable false
	 */
	public static DefaultTableModel UneditableTableModel() {
		DefaultTableModel tableModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		return tableModel;
	}

	/**
	 * Finds a fixed font fulfilling the criteria provided
	 * @param fontName The font name
	 * @param style The style
	 * @param size The font size in pixels
	 * @param currentFont The font to fall back to
	 * @return <code>Font</code> with the matching criteria
	 */
	public Font getFixedFont(String fontName, int style, int size, Font currentFont) {
		if (currentFont == null) return null;
		String resultName;
		if (fontName == null) {
			resultName = currentFont.getName();
		} else {
			Font testFont = new Font(fontName, Font.PLAIN, 10);
			if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
				resultName = fontName;
			} else {
				resultName = currentFont.getName();
			}
		}
		Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
		boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
		Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
		return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
	}
}
