
package net.imagej.ops.image.equation;

import java.util.function.DoubleBinaryOperator;

import net.imagej.ops.Ops;
import net.imagej.ops.special.computer.AbstractUnaryComputerOp;
import net.imagej.ops.special.computer.Computers;
import net.imagej.ops.special.computer.UnaryComputerOp;
import net.imagej.ops.special.function.AbstractUnaryFunctionOp;
import net.imagej.ops.special.function.UnaryFunctionOp;
import net.imglib2.IterableInterval;
import net.imglib2.type.numeric.RealType;

import org.scijava.plugin.Plugin;

/**
 * An equation operation which computes image values from x and y coordinates
 * using a binary lambda. The op calculates f(x,y). For example to compute
 * f(x,y)=x^2 + y^2 call: ops.image().equation(image2, (x, y) -> Math.pow(x, 2)
 * + Math.pow(y, 2));
 * 
 * @author Brian Northan
 */
@Plugin(type = Ops.Image.Equation.class)
public class DefaultXYEquation<T extends RealType<T>> extends
	AbstractUnaryComputerOp<DoubleBinaryOperator, IterableInterval<T>> implements
	DoubleBinaryEquationOp<T>
{

	private UnaryComputerOp<UnaryFunctionOp<long[], Double>, IterableInterval<T>> equation;

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void initialize() {
		super.initialize();
		equation = (UnaryComputerOp) Computers.unary(ops(),
			DefaultCoordinatesEquation.class, IterableInterval.class,
			UnaryFunctionOp.class);
	}

	@Override
	public void compute(final DoubleBinaryOperator lambda,
		final IterableInterval<T> output)
	{

		// create an op that calls the binary operator with the first two
		// coordinates
		final UnaryFunctionOp<long[], Double> op =
			new AbstractUnaryFunctionOp<long[], Double>()
		{

				@Override
				public Double calculate(final long[] coords) {
					return lambda.applyAsDouble(coords[0], coords[1]);
				}

			};

		equation.compute(op, output);

	}
}
