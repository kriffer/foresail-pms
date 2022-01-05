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


import io.kriffer.pms.exception.MyResourceNotFoundException;
import org.springframework.http.HttpStatus;



/**
 * Simple static methods to be called at the start of your own methods to verify correct arguments and state.
 * If the Precondition fails, an {@link HttpStatus} code is thrown
 */
public final class RestPreconditions {

	private RestPreconditions() {
		throw new AssertionError();
	}

	// API

	/**
	 * Check if some value was found, otherwise throw exception.
	 *
	 * @param expression
	 *            has value true if found, otherwise false
	 * @throws MyResourceNotFoundException
	 *             if expression is false, means value not found.
	 */
	public static void checkFound(final boolean expression) {
		if (!expression) {
			throw new MyResourceNotFoundException();
		}
	}

	/**
	 * Check if some value was found, otherwise throw exception.
	 *            has value true if found, otherwise false
	 * @throws MyResourceNotFoundException
	 *             if expression is false, means value not found.
	 */
	public static <T> T checkFound(final T resource) {
		if (resource == null) {
			throw new MyResourceNotFoundException();
		}

		return resource;
	}

}