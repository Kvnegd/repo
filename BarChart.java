/* 
* BarChart Code
*/

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.*;
import javax.swing.JFrame;
import java.awt.Color;

 public class BarChart implements ImageObserver
 {
  public static final int XMAX = 400;
  public static final int XSTART = 60;
  public static final int [] YMAX = { 220, 320, 420, 520 };

  // hard code an array of 4 colors
  private Color [] colors = { Color.BLUE, new Color( 150, 150, 0 ), 
                              Color.DARK_GRAY, Color.MAGENTA };
  private int [][] data;

  private int barSize;
  private int xStart;
  private int activity = 5;
  private int count = 0;
  private int studentResult;
  private int exactFrequencyCount;
  private int exactMinimum;
  private int key;
  private boolean checkNewValues;

  public BarChart( )
  { }

  public BarChart( int [][] dArray )
  {
   data = new int [dArray.length][dArray[0].length];
   for ( int i = 0; i < dArray.length; i++ )
   {
    for ( int j = 0; j < dArray[i].length; j++ )
    {
      data[i][j] = dArray[i][j];
    }
   }
   barSize = ( XMAX - 20 ) / data[0].length;
   studentResult = -1;
   exactFrequencyCount = -1;
   exactMinimum = -1;
   key = -1;
   checkNewValues = true;
  }

  public void setArray( int [][] dArray )
  {
   for ( int i = 0; i < dArray.length; i++ )
   {
    for ( int j = 0; j < dArray[i].length; j++ )
    {
      data[i][j] = dArray[i][j];
    }
   }
  }

 public void setStudentResult( int newStudentResult )
 {
   studentResult = newStudentResult;
 }

 public void setKey( int newKey )
 {
   key = newKey;
 }

 public int getExactFrequencyCount( )
 {
   return exactFrequencyCount;
 }

 public int getExactMinimum( )
 {
   return exactMinimum;
 }

 public boolean getCheckNewValues( )
 {
   return checkNewValues;
 }

 public void setActivity( int a )
 {
   activity = a;
 }

 public int getActivity( )
 {
   return activity;
 }

 public void updateBarChart( int key, int index1, int index2, Graphics g )
 {
   draw( g );
   switch( activity )
    {
     case( 0 ): // create new array values
                // drawNewArray( index1, index2, g );
                break;
     case( 1 ): // print out the array
                drawArray( index1, index2, g );
                break;
     case( 2 ): // set all array values of a row to a certain value
                drawNewValue( index1, index2, g );
                break;
     case( 3 ): // find the minimum  in a row
                drawMinimum( index1, index2, g );
                break;
     case( 4 ): // find the frequency of a value
          drawFrequency( index1, index2, key, g );
          break;
    }
 }

 public void draw( Graphics g )
 {
   // Draws the original array in 4 colors
   for ( int i = 0; i < data.length; i++ )
   {
     xStart = XSTART;
     g.setColor( colors[i] );

     g.drawString( "Row " + i, xStart - 50, YMAX[i] );

     for ( int j = 0; j < data[i].length; j++ )
     {
       g.fillRect( xStart, YMAX[i]-data[i][j], barSize-5, data[i][j] );
       g.drawString( "" + data[i][j], xStart, YMAX[i] + 15 );
       xStart += barSize;
     }
   }
 }

 public void drawArray( int index1, int index2, Graphics g )
 {
   // called to "print out the array"
   // index1 must be less than 4
   g.setColor( Color.red );

   for ( int i = 0; i < index1; i++ )
   {
     xStart = XSTART;
     for ( int j = 0; j < data[i].length; j++ )
     {
       g.fillRect( xStart, YMAX[i]-data[i][j], barSize-5, data[i][j] );
       g.drawString( "" + data[i][j], xStart, YMAX[i] + 15 );
       xStart += barSize;
     }
   }

   xStart = XSTART;
   for ( int j = 0; j <= index2; j++ )
   {
     g.fillRect( xStart, YMAX[index1]-data[index1][j], barSize-5, data[index1][j] );
     g.drawString( "" + data[index1][j], xStart, YMAX[index1] + 15 );
     xStart += barSize;
   }
 }

 public void drawNewValue( int index1, int index2, Graphics g )
 {
   g.setColor( Color.blue );
   String s = "";
   checkCurrentNewValues( index1, index2 );
   if ( checkNewValues )
      s = "correctly";
   else
      s = "incorrectly";
   g.drawString( "Setting new array elements of row " + index1 + " to " + key, 25, 110 );
   g.drawString( "Up to index " + index2 + " , the new values are set " + s, 25, 130 );

   // set all values of a row to a certain value
   g.setColor( Color.red );
   xStart = XSTART;
   // g.drawString( "" + data[index1][index2], xStart, YMAX[index1] + 15 );
   for ( int j = 0; j <= index2; j++ )
   {
     g.drawString( "" + data[index1][index2], xStart, YMAX[index1] + 15 );
     g.fillRect( xStart, YMAX[index1]-data[index1][j], barSize-5, data[index1][j] );
     xStart += barSize;
   }
 }

 public void drawMinimum( int index1, int index2, Graphics g )
 {
   int b = findSubMinimum( index1, index2 );
   if ( index2 != -1 )
   {
     g.setColor( Color.BLUE );
     g.drawString( "Your current minimum in column " + index2 + ": " + studentResult, 25, 110 );
     exactMinimum = b;
     g.drawString( "Correct current minimum in column " + index2 + ": " + exactMinimum, 25, 130 );

     g.setColor( Color.RED );
     xStart = XSTART + index2 * barSize;
     g.drawRect( xStart, YMAX[index1] - data[index1][index2], barSize-5, data[index1][index2] );
     g.drawString( "" + data[index1][index2], xStart, YMAX[index1] + 15 );
     for ( int i = 0; i <= index1; i++ )
     {
       if ( data[i][index2] == b )
       {
         g.setColor( Color.RED );
         g.fillRect( xStart, YMAX[i] - data[i][index2], barSize-5, data[i][index2] );
         break;
       }
     }
   }
 }

 public int findSubMinimum( int index1, int index2 )
 {
   int minimum = data[0][index2];
   for ( int i = 1; i <= index1; i++ )
   {
     if ( minimum > data[i][index2] )
        minimum = data[i][index2];
   }
   return minimum;
 }

 public void drawFrequency( int index1, int index2, int value, Graphics g )
 {
   // called to count frequency of a value
   // index1 must be less than 4
   // index2 must be less than 20

   g.setColor( Color.BLUE );
   g.drawString( "Your current frequency count: " + studentResult, 25, 110 );
   exactFrequencyCount = findExactFrequencyCount( index1, index2, value );
   g.drawString( "Correct current frequency count: " + exactFrequencyCount, 25, 130 );

   g.setColor( Color.RED );
   for ( int i = 0; i < index1; i++ )
   {
     xStart = XSTART;
     for ( int j = 0; j < data[i].length; j++ )
     {
       if ( data[i][j] == value )
          g.fillRect( xStart, YMAX[i]-data[i][j], barSize-5, data[i][j] );
       xStart += barSize;
     }
   }

   // last row (incomplete)
   xStart = XSTART + barSize * index2;
   g.drawRect( xStart, YMAX[index1]-value, barSize-5, value );
   g.drawString( "" + data[index1][index2], xStart, YMAX[index1] + 15 );
   xStart = XSTART;
   for ( int j = 0; j <= index2; j++ )
   {
     if ( data[index1][j] == value )
        g.fillRect( xStart, YMAX[index1]-data[index1][j], barSize-5, data[index1][j] );
     xStart += barSize;
   }
 }

 public int findExactFrequencyCount( int rowIndex, int colIndex, int searchValue )
 {
  int currentCount = 0;
  for ( int i = 0; i < rowIndex; i++ )
  {
   for( int j = 0; j < data[i].length; j++ )
   {
     if ( data[i][j] == searchValue )
       currentCount++;
   }
  }
  // check current row now
  for (int j = 0; j <= colIndex; j++ )
  {
   if ( data[rowIndex][j] == searchValue )
      currentCount++;
  }

  return currentCount;
 }

 public void checkCurrentNewValues( int index1, int index2 )
 {
   checkNewValues = true;
   for ( int i = 0; i <= index2; i++ )
   {
     if ( key != data[index1][i] )
        checkNewValues = false;
    }
 }

 public boolean imageUpdate( Image i, int infoflags, int x, int y, int width, int height )
 {
   return true;
 }
}