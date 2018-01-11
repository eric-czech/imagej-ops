/*
 * #%L
 * ImageJ software for multidimensional image processing and analysis.
 * %%
 * Copyright (C) 2014 - 2017 ImageJ developers.
 * %%
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * #L%
 */

package net.imagej.ops.image.quality;

import net.imagej.ops.Contingent;
import net.imagej.ops.Ops;
import net.imagej.ops.map.Maps;
import net.imagej.ops.special.hybrid.AbstractBinaryHybridCF;
import net.imglib2.Cursor;
import net.imglib2.IterableInterval;
import net.imglib2.type.numeric.RealType;
import net.imglib2.type.numeric.real.DoubleType;
import net.imglib2.util.Intervals;

import org.scijava.plugin.Plugin;

/**
 * Computes the root mean square error (RMSE) between a reference image and a
 * (noisy) test image.
 * <p>
 * Computations are based on the definitions of Gonzalez (R.C. Gonzalez and R.E.
 * Woods, "Digital Image Processing," Prentice Hall 2008).
 * </p>
 * 
 * @author Stefan Helfrich (University of Konstanz)
 * @param <I> type of input elements
 */
@Plugin(type = Ops.Image.RMSE.class)
public class DefaultRMSE<I extends RealType<I>> extends
	AbstractBinaryHybridCF<IterableInterval<I>, IterableInterval<I>, DoubleType>
	implements Ops.Image.RMSE, Contingent
{

	@Override
	public void compute(final IterableInterval<I> input1,
		final IterableInterval<I> input2, final DoubleType output)
	{
		Cursor<I> cursor = input1.cursor();
		Cursor<I> cursor2 = input2.cursor();

		double denominatorSum = 0d;
		while (cursor.hasNext()) {
			double r = cursor.next().getRealDouble();
			double t = cursor2.next().getRealDouble();
			denominatorSum += Math.pow(r - t, 2);
		}

		denominatorSum *= 1d / Intervals.numElements(input1);

		double rmse = Math.sqrt(denominatorSum);

		output.setReal(rmse);
	}

	@Override
	public boolean conforms() {
		return Intervals.equalDimensions(in1(), in2()) && //
			Maps.compatible(in1(), in2());
	}

	@Override
	public DoubleType createOutput(IterableInterval<I> input1,
		IterableInterval<I> input2)
	{
		return new DoubleType();
	}

}
