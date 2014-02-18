package framework;

import android.graphics.drawable.GradientDrawable;

public class Gradients {
	public static GradientDrawable SetGradient(int[] colors, float[] conerRadii, int stroke, int strokeWidth)
	{
		GradientDrawable drawable = new GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT, colors);
		drawable.setGradientType(GradientDrawable.RECTANGLE);
		drawable.setCornerRadii(conerRadii);
		drawable.setStroke(strokeWidth, stroke);
		return drawable;
	}
}
