/*
 * MIT License
 *
 * Copyright (c) 2021 Foresail Consulting (www.foresail.fi)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package io.kriffer.pms.util;

import javax.servlet.http.HttpServletResponse;

/**
 * Provides some constants and utility methods to build a Link Header to be stored in the {@link HttpServletResponse} object
 */
public final class LinkUtil {

	public static final String REL_COLLECTION = "collection";
	public static final String REL_NEXT = "next";
	public static final String REL_PREV = "prev";
	public static final String REL_FIRST = "first";
	public static final String REL_LAST = "last";

	private LinkUtil() {
		throw new AssertionError();
	}

	//

	/**
	 * Creates a Link Header to be stored in the {@link HttpServletResponse} to provide Discoverability features to the user
	 *
	 * @param uri
	 *            the base uri
	 * @param rel
	 *            the relative path
	 *
	 * @return the complete url
	 */
	public static String createLinkHeader(final String uri, final String rel) {
		return "<" + uri + ">; rel=\"" + rel + "\"";
	}

}