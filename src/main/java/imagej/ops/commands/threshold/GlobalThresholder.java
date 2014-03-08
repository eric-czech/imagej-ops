/*
 * #%L
 * ImageJ OPS: a framework for reusable algorithms.
 * %%
 * Copyright (C) 2014 Board of Regents of the University of
 * Wisconsin-Madison and University of Konstanz.
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

package imagej.ops.commands.threshold;

import imagej.command.Command;
import imagej.ops.slicer.SlicingService;
import imagej.ops.threshold.ThresholdMethod;
import net.imglib2.Axis;
import net.imglib2.meta.ImgPlus;
import net.imglib2.type.logic.BitType;
import net.imglib2.type.numeric.RealType;

import org.scijava.ItemIO;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;

/**
 * TODO: should actually live in a different package!! OR: can this be
 * auto-generated?? (e.g. based on other plugin annotations)
 */
@Plugin(type = Command.class, menuPath = "Image > Threshold > Apply Threshold")
public class GlobalThresholder<T extends RealType<T>> implements Command {

	@Parameter
	private ThresholdMethod<T> method;

	// should not be Dataset, DisplayService, ...
	@Parameter
	private ImgPlus<T> src;

	// needs to be created by the pre-processor!
	@Parameter(type = ItemIO.BOTH)
	private ImgPlus<BitType> res;

	// we need another widget for this!!
	@Parameter
	private Axis[] axes;

	@Parameter
	private SlicingService sliceService;

	@Override
	public void run() {

		final imagej.ops.threshold.GlobalThresholder<T> thresholder =
			new imagej.ops.threshold.GlobalThresholder<T>();
		thresholder.setMethod(method);

		sliceService.process(src, res, new int[] { 1, 2 }, thresholder);

	}
}