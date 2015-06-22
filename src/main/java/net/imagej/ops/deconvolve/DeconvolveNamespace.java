/*
 * #%L
 * ImageJ software for multidimensional image processing and analysis.
 * %%
 * Copyright (C) 2014 - 2015 Board of Regents of the University of
 * Wisconsin-Madison, University of Konstanz and Brian Northan.
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

package net.imagej.ops.deconvolve;

import net.imagej.ops.AbstractNamespace;
import net.imagej.ops.OpMethod;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.img.Img;
import net.imglib2.img.ImgFactory;
import net.imglib2.outofbounds.OutOfBoundsFactory;
import net.imglib2.type.Type;
import net.imglib2.type.numeric.ComplexType;
import net.imglib2.type.numeric.RealType;

/**
 * The deconvolve namespace contains deconvolution operations.
 *
 * @author Alison Walter
 */
public class DeconvolveNamespace extends AbstractNamespace {

	//-- Deconvolve namespace ops --

	@OpMethod(op = net.imagej.ops.DeconvolveOps.RichardsonLucy.class)
	public Object richardsonLucy(final Object... args) {
		return ops().run(net.imagej.ops.DeconvolveOps.RichardsonLucy.class, args);
	}

	@OpMethod(op = net.imagej.ops.deconvolve.RichardsonLucyImg.class)
	public
		<I extends RealType<I>, O extends RealType<O>, K extends RealType<K>, C extends ComplexType<C>>
		Img<O> richardsonLucy(final Img<I> in,
			final RandomAccessibleInterval<K> kernel, final int maxIterations)
	{
		@SuppressWarnings("unchecked")
		final Img<O> result =
			(Img<O>) ops().run(net.imagej.ops.deconvolve.RichardsonLucyImg.class, in,
				kernel, maxIterations);
		return result;
	}

	@OpMethod(op = net.imagej.ops.deconvolve.RichardsonLucyImg.class)
	public
		<I extends RealType<I>, O extends RealType<O>, K extends RealType<K>, C extends ComplexType<C>>
		Img<O> richardsonLucy(final Img<O> out, final Img<I> in,
			final RandomAccessibleInterval<K> kernel, final int maxIterations)
	{
		@SuppressWarnings("unchecked")
		final Img<O> result =
			(Img<O>) ops().run(net.imagej.ops.deconvolve.RichardsonLucyImg.class,
				out, in, kernel, maxIterations);
		return result;
	}

	@OpMethod(op = net.imagej.ops.deconvolve.RichardsonLucyImg.class)
	public
		<I extends RealType<I>, O extends RealType<O>, K extends RealType<K>, C extends ComplexType<C>>
		Img<O> richardsonLucy(final Img<O> out, final Img<I> in,
			final RandomAccessibleInterval<K> kernel, final long[] borderSize,
			final int maxIterations)
	{
		@SuppressWarnings("unchecked")
		final Img<O> result =
			(Img<O>) ops().run(net.imagej.ops.deconvolve.RichardsonLucyImg.class,
				out, in, kernel, borderSize, maxIterations);
		return result;
	}

	@OpMethod(op = net.imagej.ops.deconvolve.RichardsonLucyImg.class)
	public
		<I extends RealType<I>, O extends RealType<O>, K extends RealType<K>, C extends ComplexType<C>>
		Img<O> richardsonLucy(final Img<O> out, final Img<I> in,
			final RandomAccessibleInterval<K> kernel, final long[] borderSize,
			final OutOfBoundsFactory<I, RandomAccessibleInterval<I>> obfInput,
			final int maxIterations)
	{
		@SuppressWarnings("unchecked")
		final Img<O> result =
			(Img<O>) ops().run(net.imagej.ops.deconvolve.RichardsonLucyImg.class,
				out, in, kernel, borderSize, obfInput, maxIterations);
		return result;
	}

	@OpMethod(op = net.imagej.ops.deconvolve.RichardsonLucyImg.class)
	public
		<I extends RealType<I>, O extends RealType<O>, K extends RealType<K>, C extends ComplexType<C>>
		Img<O> richardsonLucy(final Img<O> out, final Img<I> in,
			final RandomAccessibleInterval<K> kernel, final long[] borderSize,
			final OutOfBoundsFactory<I, RandomAccessibleInterval<I>> obfInput,
			final OutOfBoundsFactory<K, RandomAccessibleInterval<K>> obfKernel,
			final int maxIterations)
	{
		@SuppressWarnings("unchecked")
		final Img<O> result =
			(Img<O>) ops().run(net.imagej.ops.deconvolve.RichardsonLucyImg.class,
				out, in, kernel, borderSize, obfInput, obfKernel, maxIterations);
		return result;
	}

	@OpMethod(op = net.imagej.ops.deconvolve.RichardsonLucyImg.class)
	public
		<I extends RealType<I>, O extends RealType<O>, K extends RealType<K>, C extends ComplexType<C>>
		Img<O> richardsonLucy(final Img<O> out, final Img<I> in,
			final RandomAccessibleInterval<K> kernel, final long[] borderSize,
			final OutOfBoundsFactory<I, RandomAccessibleInterval<I>> obfInput,
			final OutOfBoundsFactory<K, RandomAccessibleInterval<K>> obfKernel,
			final Type<O> outType, final int maxIterations)
	{
		@SuppressWarnings("unchecked")
		final Img<O> result =
			(Img<O>) ops().run(net.imagej.ops.deconvolve.RichardsonLucyImg.class,
				out, in, kernel, borderSize, obfInput, obfKernel, outType,
				maxIterations);
		return result;
	}

	@OpMethod(op = net.imagej.ops.deconvolve.RichardsonLucyImg.class)
	public
		<I extends RealType<I>, O extends RealType<O>, K extends RealType<K>, C extends ComplexType<C>>
		Img<O> richardsonLucy(final Img<O> out, final Img<I> in,
			final RandomAccessibleInterval<K> kernel, final long[] borderSize,
			final OutOfBoundsFactory<I, RandomAccessibleInterval<I>> obfInput,
			final OutOfBoundsFactory<K, RandomAccessibleInterval<K>> obfKernel,
			final Type<O> outType, final ImgFactory<O> outFactory,
			final int maxIterations)
	{
		@SuppressWarnings("unchecked")
		final Img<O> result =
			(Img<O>) ops().run(net.imagej.ops.deconvolve.RichardsonLucyImg.class,
				out, in, kernel, borderSize, obfInput, obfKernel, outType, outFactory,
				maxIterations);
		return result;
	}

	@OpMethod(op = net.imagej.ops.deconvolve.RichardsonLucyImg.class)
	public
		<I extends RealType<I>, O extends RealType<O>, K extends RealType<K>, C extends ComplexType<C>>
		Img<O> richardsonLucy(final Img<O> out, final Img<I> in,
			final RandomAccessibleInterval<K> kernel, final long[] borderSize,
			final OutOfBoundsFactory<I, RandomAccessibleInterval<I>> obfInput,
			final OutOfBoundsFactory<K, RandomAccessibleInterval<K>> obfKernel,
			final Type<O> outType, final ImgFactory<O> outFactory,
			final ComplexType<C> fftType, final int maxIterations)
	{
		@SuppressWarnings("unchecked")
		final Img<O> result =
			(Img<O>) ops().run(net.imagej.ops.deconvolve.RichardsonLucyImg.class,
				out, in, kernel, borderSize, obfInput, obfKernel, outType, outFactory,
				fftType, maxIterations);
		return result;
	}

	@OpMethod(op = net.imagej.ops.deconvolve.RichardsonLucyImg.class)
	public
		<I extends RealType<I>, O extends RealType<O>, K extends RealType<K>, C extends ComplexType<C>>
		Img<O> richardsonLucy(final Img<O> out, final Img<I> in,
			final RandomAccessibleInterval<K> kernel, final long[] borderSize,
			final OutOfBoundsFactory<I, RandomAccessibleInterval<I>> obfInput,
			final OutOfBoundsFactory<K, RandomAccessibleInterval<K>> obfKernel,
			final Type<O> outType, final ImgFactory<O> outFactory,
			final ComplexType<C> fftType, final ImgFactory<C> fftFactory,
			final int maxIterations)
	{
		@SuppressWarnings("unchecked")
		final Img<O> result =
			(Img<O>) ops().run(net.imagej.ops.deconvolve.RichardsonLucyImg.class,
				out, in, kernel, borderSize, obfInput, obfKernel, outType, outFactory,
				fftType, fftFactory, maxIterations);
		return result;
	}

	@Override
	public String getName() {
		return "deconvolve";
	}

}