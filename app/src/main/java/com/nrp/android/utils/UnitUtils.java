package com.nrp.android.utils;

import android.content.Context;

public class UnitUtils {

	public static int dip2pix(Context context, int dips) {
		int densityDpi = context.getResources().getDisplayMetrics().densityDpi;
		return (dips * densityDpi) / 160;
	}

	public static int pix2dip(Context context, int pixs) {
		int densityDpi = context.getResources().getDisplayMetrics().densityDpi;
		return (pixs * 160) / densityDpi;
	}

	/**
	 * 将px值转换为sp值，保证文字大小不变
	 */
	public static int px2sp(Context context, float pxValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}


	
}
