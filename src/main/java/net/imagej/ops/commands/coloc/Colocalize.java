package net.imagej.ops.commands.coloc;

import net.imagej.ops.OpService;
import net.imagej.ops.Ops;
import net.imagej.ops.coloc.pValue.PValueResult;
import net.imagej.ops.special.function.BinaryFunctionOp;
import net.imagej.ops.special.function.Functions;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.img.Img;
import net.imglib2.type.numeric.RealType;
import net.imglib2.type.numeric.real.DoubleType;

import org.scijava.ItemIO;
import org.scijava.command.Command;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;

@Plugin(type=Command.class)
public class Colocalize<T extends RealType<T>, U extends RealType<U>> implements Command {

	@Parameter
	private OpService ops;
	
	@Parameter
	private Img<T> image1;
	
	@Parameter
	private Img<U> image2;
	
	@Parameter(choices= {"ICQ", "K-Tau"})
	private String algorithm;

	@Parameter(type=ItemIO.OUTPUT)
	private Double pValue; // GLOBAL OUTPUT: calculated p-value
	
	@Parameter(type=ItemIO.OUTPUT)
	private Double colocValue; // GLOBAL OUTPUT: original calculated coloc measure
	
	@Parameter(type=ItemIO.OUTPUT)
	private double[] colocValuesArray; // GLOBAL OUTPUT: array of coloc values from shuffling
	
	// GLOBAL OUTPUT (OPTION): plot colocValuesArray as histogram/distribution of colocValues... show original colocValue on plot, and simply display p-value somewhere 
	
	/* PIXEL-WISE OUTPUTS
	 * 1) array of z-scores
	 * 2) array of SigPixel (0 or 1)
	 * 3) overlay of SigPixels
	 * 4) heat map overlay of z-scores
	 */
	@Parameter(type=ItemIO.OUTPUT)
	private Img<DoubleType> heatMap; // PIXEL-WISE OUTPUT
	
	// OPTION: add Parameter for neighborhood size/shape for pixel-wise measure
	
	@Override
	public void run() {
		BinaryFunctionOp<Iterable<T>, Iterable<U>, Double> colocOp;
		PValueResult result = new PValueResult();
		if (algorithm.equals("ICQ")) {
			colocOp = Functions.binary(ops, Ops.Coloc.ICQ.class, Double.class, image1, image2);
		} else if(algorithm.equals("K-Tau")) {
			colocOp = Functions.binary(ops, Ops.Coloc.KendallTau.class, Double.class, image1, image2);
		} else {
			throw new IllegalStateException("Unknown algorithm: " + algorithm);
		}
		result = (PValueResult) ops.run(Ops.Coloc.PValue.class, image1, image2, colocOp);
		this.pValue = result.getPValue();
		this.colocValue = result.getColocValue();
		this.colocValuesArray = result.getColocValuesArray();
	}
}
