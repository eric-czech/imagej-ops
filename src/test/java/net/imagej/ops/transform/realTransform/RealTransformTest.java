
package net.imagej.ops.transform.realTransform;

import static org.junit.Assert.assertEquals;

import net.imagej.ops.AbstractOpTest;
import net.imglib2.Cursor;
import net.imglib2.RandomAccess;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.img.Img;
import net.imglib2.interpolation.randomaccess.LanczosInterpolatorFactory;
import net.imglib2.realtransform.AffineTransform2D;
import net.imglib2.type.numeric.integer.UnsignedByteType;
import net.imglib2.view.Views;

import org.junit.Test;

public class RealTransformTest extends AbstractOpTest {

	@Test
	public void regressionTest() throws Exception {

		Img<UnsignedByteType> image = (Img<UnsignedByteType>) this
			.openUnsignedByteType(getClass(), "lowresbridge.tif");
		Img<UnsignedByteType> expectedOutput = (Img<UnsignedByteType>) this
			.openUnsignedByteType(getClass(), "rotatedscaledcenter.tif");

		AffineTransform2D transform = new AffineTransform2D();

		transform.translate(-image.dimension(0)/2,-image.dimension(0)/2);
		transform.rotate(1);
		transform.scale(0.5);
		transform.translate(image.dimension(0)/2,image.dimension(0)/2);

		RandomAccessibleInterval<UnsignedByteType> actualOutput=ops.transform().realTransform(image, transform);
		
		// compare the output image data to that stored in the file.
		Cursor<UnsignedByteType> cursor = Views.iterable(actualOutput)
			.localizingCursor();
		RandomAccess<UnsignedByteType> actualRA = actualOutput.randomAccess();
		RandomAccess<UnsignedByteType> expectedRA = expectedOutput.randomAccess();

		while (cursor.hasNext()) {
			cursor.fwd();
			actualRA.setPosition(cursor);
			expectedRA.setPosition(cursor);
			assertEquals(expectedRA.get().get(), actualRA.get().get(), 0);
		}

	}

}
