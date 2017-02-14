package net.imglib2.jmh.oob;

import net.imglib2.util.Util;

public class GrayCode
{
	private final int n;

	private int i;

	private int code = 0;

	private final int[] moves;

	private final int[] codes;

	public GrayCode( final int n, final int[] moves, final int[] codes )
	{
		this.n = n;
		this.moves = moves;
		this.codes = codes;
	}

	void print()
	{
		i = 0;
		code = 0;
		accumulate();
		graycodeFwdRecursive( n - 1 );
	}

	final private void graycodeFwdRecursive( final int dimension )
	{
		if ( dimension == 0 )
		{
			fwd( 0 );
			code += 1;
			accumulate();
		}
		else
		{
			graycodeFwdRecursive( dimension - 1 );
			fwd( dimension );
			code += 1 << dimension;
			accumulate();
			graycodeBckRecursive( dimension - 1 );
		}
	}

	final private void graycodeBckRecursive( final int dimension )
	{
		if ( dimension == 0 )
		{
			bck( 0 );
			code -= 1;
			accumulate();
		}
		else
		{
			graycodeFwdRecursive( dimension - 1 );
			bck( dimension );
			code -= 1 << dimension;
			accumulate();
			graycodeBckRecursive( dimension - 1 );
		}
	}

	void fwd( final int d )
	{
		moves[ i++ ] = d + 1;
		System.out.println( "fwd " + d );
	}
	void bck( final int d )
	{
		moves[ i++ ] = - ( d + 1 );
		System.out.println( "bck " + d );
	}

	void accumulate()
	{
		codes[ i ] = code;
		System.out.println( "accumulate " + String.format( "%" + n + "s", Integer.toBinaryString( code ) ).replace( ' ', '0' ) );
	}

	public static void main( final String[] args )
	{
		final int n = 4;
		final int[] codes = new int[ 1 << n ];
		final int[] moves = new int[ 1 << n ];
		new GrayCode( n, moves, codes ).print();
		System.out.println( Util.printCoordinates( moves ) );
		System.out.println( Util.printCoordinates( codes ) );
	}
}
