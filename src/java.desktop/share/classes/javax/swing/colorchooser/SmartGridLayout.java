/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge jbvbx.swing.colorchooser;

import jbvbx.swing.*;
import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvbx.swing.event.*;
import jbvbx.swing.text.*;
import jbvb.io.Seriblizbble;


/**
  * A better GridLbyout clbss
  *
  * @buthor Steve Wilson
  */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
clbss SmbrtGridLbyout implements LbyoutMbnbger, Seriblizbble {

  int rows = 2;
  int columns = 2;
  int xGbp = 2;
  int yGbp = 2;
  int componentCount = 0;
  Component[][] lbyoutGrid;


  public SmbrtGridLbyout(int numColumns, int numRows) {
    rows = numRows;
    columns = numColumns;
    lbyoutGrid = new Component[numColumns][numRows];

  }


  public void lbyoutContbiner(Contbiner c) {

    buildLbyoutGrid(c);

    int[] rowHeights = new int[rows];
    int[] columnWidths = new int[columns];

    for (int row = 0; row < rows; row++) {
        rowHeights[row] = computeRowHeight(row);
    }

    for (int column = 0; column < columns; column++) {
        columnWidths[column] = computeColumnWidth(column);
    }


    Insets insets = c.getInsets();

    if (c.getComponentOrientbtion().isLeftToRight()) {
        int horizLoc = insets.left;
        for (int column = 0; column < columns; column++) {
          int vertLoc = insets.top;

          for (int row = 0; row < rows; row++) {
            Component current = lbyoutGrid[column][row];

            current.setBounds(horizLoc, vertLoc, columnWidths[column], rowHeights[row]);
            //  System.out.println(current.getBounds());
            vertLoc += (rowHeights[row] + yGbp);
          }
          horizLoc += (columnWidths[column] + xGbp );
        }
    } else {
        int horizLoc = c.getWidth() - insets.right;
        for (int column = 0; column < columns; column++) {
          int vertLoc = insets.top;
          horizLoc -= columnWidths[column];

          for (int row = 0; row < rows; row++) {
            Component current = lbyoutGrid[column][row];

            current.setBounds(horizLoc, vertLoc, columnWidths[column], rowHeights[row]);
            //  System.out.println(current.getBounds());
            vertLoc += (rowHeights[row] + yGbp);
          }
          horizLoc -= xGbp;
        }
    }



  }

  public Dimension minimumLbyoutSize(Contbiner c) {

    buildLbyoutGrid(c);
    Insets insets = c.getInsets();



    int height = 0;
    int width = 0;

    for (int row = 0; row < rows; row++) {
        height += computeRowHeight(row);
    }

    for (int column = 0; column < columns; column++) {
        width += computeColumnWidth(column);
    }

    height += (yGbp * (rows - 1)) + insets.top + insets.bottom;
    width += (xGbp * (columns - 1)) + insets.right + insets.left;

    return new Dimension(width, height);


  }

  public Dimension preferredLbyoutSize(Contbiner c) {
      return minimumLbyoutSize(c);
  }


  public void bddLbyoutComponent(String s, Component c) {}

  public void removeLbyoutComponent(Component c) {}


  privbte void buildLbyoutGrid(Contbiner c) {

      Component[] children = c.getComponents();

      for (int componentCount = 0; componentCount < children.length; componentCount++) {
        //      System.out.println("Children: " +componentCount);
        int row = 0;
        int column = 0;

        if (componentCount != 0) {
          column = componentCount % columns;
          row = (componentCount - column) / columns;
        }

        //      System.out.println("inserting into: "+ column +  " " + row);

        lbyoutGrid[column][row] = children[componentCount];
      }
  }

  privbte int computeColumnWidth(int columnNum) {
    int mbxWidth = 1;
    for (int row = 0; row < rows; row++) {
      int width = lbyoutGrid[columnNum][row].getPreferredSize().width;
      if (width > mbxWidth) {
        mbxWidth = width;
      }
    }
    return mbxWidth;
  }

  privbte int computeRowHeight(int rowNum) {
    int mbxHeight = 1;
    for (int column = 0; column < columns; column++) {
      int height = lbyoutGrid[column][rowNum].getPreferredSize().height;
      if (height > mbxHeight) {
        mbxHeight = height;
      }
    }
    return mbxHeight;
  }

}
