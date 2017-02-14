package net.imglib2.jmh.oob;

import net.imglib2.RealRandomAccessible;
import net.imglib2.converter.TypeIdentity;
import net.imglib2.img.Img;
import net.imglib2.img.array.ArrayImgs;
import net.imglib2.img.display.imagej.ImageJFunctions;
import net.imglib2.interpolation.randomaccess.NLinearInterpolatorFactory;
import net.imglib2.realtransform.AffineGet;
import net.imglib2.realtransform.AffineRandomAccessible;
import net.imglib2.realtransform.AffineTransform3D;
import net.imglib2.realtransform.RealViews;
import net.imglib2.type.numeric.ARGBType;
import net.imglib2.view.Views;

public class RealTransformProjection
{
	public static void main( final String[] args )
	{
		final Img< ARGBType > output = ArrayImgs.argbs( 800, 600 );
//		output.forEach( t -> t.set( 1000 ) );


		final Img< ARGBType > input = ArrayImgs.argbs( 100, 100, 100 );
		input.forEach( t -> t.set( 0xf0f0800f ) );

		final RealRandomAccessible< ARGBType > interpolated = Views.interpolate(
				Views.extendValue( input, new ARGBType() ),
//				new NearestNeighborInterpolatorFactory<>() );
				new NLinearInterpolatorFactory() );
		final AffineTransform3D transform = new AffineTransform3D();
//		transform.set(
//				2, 0, 0, 100,
//				0, 2, 0, 100,
//				0, 0, 2, -10 );
		transform.set(
				5.295586247967197, -1.1102230246251565E-15, -1.3322676295501878E-15, 113.94362828458958,
				-2.220446049250313E-16, 5.295586247967196, 1.9984014443252818E-15, 20.46203929297465,
				1.7763568394002505E-15, 0.0, 5.2955862479672, -246.09883910014548 );

//		transform.set(
//				35.50558800930632, -7.443769106001904E-15, -8.932522927202282E-15, -1540.7557931012605,
//				-1.488753821200381E-15, 35.505588009306315, 1.3398784390803424E-14, -1465.8421928166058,
//				1.1910030569603047E-14, 0.0, 35.50558800930634, -1650.031475554292 );
//
//		transform.set(
//				2.585111266841494, -0.7053402480107281, -0.8405917745680779, 310.95901360230266,
//				-6.957371724304071E-16, 2.1513304502207107, -1.8051805873206517, 242.67079149066052,
//				1.0973146298739227, 1.6616774919867852, 1.9803101208081912, -154.9578950308882 );
//
//		transform.set(
//				6.2213784960122664, -1.6974854071590588, -2.0229843323580576, 282.7689766731602,
//				-1.674374460764869E-15, 5.177433239526466, -4.344382321731651, 232.3613858336912,
//				2.640818493665198, 3.9990250124069626, 4.7658524254101104, -372.92465059361984 );
//
//		transform.set(
//				4.546817752282592, -7.233783174913243, -8.620887086750425, 707.5026246844927,
//				-3.0069359674944837E-15, 9.297926235760855, -7.801886475140493, 218.32358316717074,
//				11.253768843554496, 2.9226381146700544, 3.4830644730108107, -534.8096648012742 );
//
//		transform.set(
//				2.1459533564184397, 1.7442227195618756, 0.489357696911752, 220.4456018767193,
//				-0.9143002609060271, 1.6977130335616921, -2.0417456128196037, 288.89005569780284,
//				-1.5639177995885092, 1.400841611005719, 1.865128294753825, -309.2326191733801 );

		final AffineRandomAccessible< ARGBType, AffineGet > affine = RealViews.affine( interpolated, transform );

		new SimpleProjector<>( affine, new TypeIdentity<>(), output ).map();

		ImageJFunctions.show( output );

//		final Bdv bdv = BdvFunctions.show( input, "input" );
//
//		final Behaviours behaviours = new Behaviours( new InputTriggerConfig() );
//		behaviours.install( bdv.getBdvHandle().getTriggerbindings(), "paint" );
//
//		behaviours.behaviour( new ClickBehaviour()
//		{
//			@Override
//			public void click( final int x, final int y )
//			{
//				final AffineTransform3D t = new AffineTransform3D();
//				bdv.getBdvHandle().getViewerPanel().getState().getViewerTransform( t );
//				System.out.println( t );
//			}
//		}, "paint", "D" );

	}
}
