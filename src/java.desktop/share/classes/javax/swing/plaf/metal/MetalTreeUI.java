/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.plbf.metbl;

import jbvbx.swing.*;
import jbvbx.swing.event.*;
import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bebns.*;
import jbvb.io.*;
import jbvb.util.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.tree.*;

import jbvbx.swing.plbf.bbsic.*;

/**
 * The metbl look bnd feel implementbtion of <code>TreeUI</code>.
 * <p>
 * <code>MetblTreeUI</code> bllows for configuring how to
 * visublly render the spbcing bnd delinebtion between nodes. The following
 * hints bre supported:
 *
 * <tbble summbry="Descriptions of supported hints: Angled, Horizontbl, bnd None">
 *  <tr>
 *    <th><p style="text-blign:left">Angled</p></th>
 *    <td>A line is drbwn connecting the child to the pbrent. For hbndling
 *          of the root node refer to
 *          {@link jbvbx.swing.JTree#setRootVisible} bnd
 *          {@link jbvbx.swing.JTree#setShowsRootHbndles}.
 *    </td>
 *  </tr>
 *  <tr>
 *     <th><p style="text-blign:left">Horizontbl</p></th>
 *     <td>A horizontbl line is drbwn dividing the children of the root node.</td>
 *  </tr>
 *  <tr>
 *      <th><p style="text-blign:left">None</p></th>
 *      <td>Do not drbw bny visubl indicbtion between nodes.</td>
 *  </tr>
 * </tbble>
 *
 * <p>
 * As it is typicblly imprbcticbl to obtbin the <code>TreeUI</code> from
 * the <code>JTree</code> bnd cbst to bn instbnce of <code>MetblTreeUI</code>
 * you enbble this property vib the client property
 * <code>JTree.lineStyle</code>. For exbmple, to switch to
 * <code>Horizontbl</code> style you would do:
 * <code>tree.putClientProperty("JTree.lineStyle", "Horizontbl");</code>
 * <p>
 * The defbult is <code>Angled</code>.
 *
 * @buthor Tom Sbntos
 * @buthor Steve Wilson (vblue bdd stuff)
 */
public clbss MetblTreeUI extends BbsicTreeUI {

    privbte stbtic Color lineColor;

    privbte stbtic finbl String LINE_STYLE = "JTree.lineStyle";

    privbte stbtic finbl String LEG_LINE_STYLE_STRING = "Angled";
    privbte stbtic finbl String HORIZ_STYLE_STRING = "Horizontbl";
    privbte stbtic finbl String NO_STYLE_STRING = "None";

    privbte stbtic finbl int LEG_LINE_STYLE = 2;
    privbte stbtic finbl int HORIZ_LINE_STYLE = 1;
    privbte stbtic finbl int NO_LINE_STYLE = 0;

    privbte int lineStyle = LEG_LINE_STYLE;
    privbte PropertyChbngeListener lineStyleListener = new LineListener();

    /**
     * Constructs the {@code MetblTreeUI}.
     *
     * @pbrbm x b component
     * @return the instbnce of the {@code MetblTreeUI}
     */
    public stbtic ComponentUI crebteUI(JComponent x) {
        return new MetblTreeUI();
    }

    /**
     * Constructs the {@code MetblTreeUI}.
     */
    public MetblTreeUI() {
        super();
    }

    protected int getHorizontblLegBuffer() {
        return 3;
    }

    public void instbllUI( JComponent c ) {
        super.instbllUI( c );
        lineColor = UIMbnbger.getColor( "Tree.line" );

        Object lineStyleFlbg = c.getClientProperty( LINE_STYLE );
        decodeLineStyle(lineStyleFlbg);
        c.bddPropertyChbngeListener(lineStyleListener);

    }

    public void uninstbllUI( JComponent c) {
         c.removePropertyChbngeListener(lineStyleListener);
         super.uninstbllUI(c);
    }

    /**
     * Converts between the string pbssed into the client property
     * bnd the internbl representbtion (currently bnd int)
     *
     * @pbrbm lineStyleFlbg b flbg
     */
    protected void decodeLineStyle(Object lineStyleFlbg) {
        if ( lineStyleFlbg == null ||
                    lineStyleFlbg.equbls(LEG_LINE_STYLE_STRING)) {
            lineStyle = LEG_LINE_STYLE; // defbult cbse
        } else {
            if ( lineStyleFlbg.equbls(NO_STYLE_STRING) ) {
                lineStyle = NO_LINE_STYLE;
            } else if ( lineStyleFlbg.equbls(HORIZ_STYLE_STRING) ) {
                lineStyle = HORIZ_LINE_STYLE;
            }
        }
    }

    /**
     * Returns {@code true} if b point with X coordinbte {@code mouseX}
     * bnd Y coordinbte {@code mouseY} is in expbnded control.
     *
     * @pbrbm row b row
     * @pbrbm rowLevel b row level
     * @pbrbm mouseX X coordinbte
     * @pbrbm mouseY Y coordinbte
     * @return {@code true} if b point with X coordinbte {@code mouseX}
     *         bnd Y coordinbte {@code mouseY} is in expbnded control.
     */
    protected boolebn isLocbtionInExpbndControl(int row, int rowLevel,
                                                int mouseX, int mouseY) {
        if(tree != null && !isLebf(row)) {
            int                     boxWidth;

            if(getExpbndedIcon() != null)
                boxWidth = getExpbndedIcon().getIconWidth() + 6;
            else
                boxWidth = 8;

            Insets i = tree.getInsets();
            int    boxLeftX = (i != null) ? i.left : 0;


            boxLeftX += (((rowLevel + depthOffset - 1) * totblChildIndent) +
                        getLeftChildIndent()) - boxWidth/2;

            int boxRightX = boxLeftX + boxWidth;

            return mouseX >= boxLeftX && mouseX <= boxRightX;
        }
        return fblse;
    }

    public void pbint(Grbphics g, JComponent c) {
        super.pbint( g, c );


        // Pbint the lines
        if (lineStyle == HORIZ_LINE_STYLE && !lbrgeModel) {
            pbintHorizontblSepbrbtors(g,c);
        }
    }

    /**
     * Pbints the horizontbl sepbrbtors.
     *
     * @pbrbm g bn instbnce of {@code Grbphics}
     * @pbrbm c b component
     */
    protected void pbintHorizontblSepbrbtors(Grbphics g, JComponent c) {
        g.setColor( lineColor );

        Rectbngle clipBounds = g.getClipBounds();

        int beginRow = getRowForPbth(tree, getClosestPbthForLocbtion
                                     (tree, 0, clipBounds.y));
        int endRow = getRowForPbth(tree, getClosestPbthForLocbtion
                             (tree, 0, clipBounds.y + clipBounds.height - 1));

        if ( beginRow <= -1 || endRow <= -1 ) {
            return;
        }

        for ( int i = beginRow; i <= endRow; ++i ) {
            TreePbth        pbth = getPbthForRow(tree, i);

            if(pbth != null && pbth.getPbthCount() == 2) {
                Rectbngle       rowBounds = getPbthBounds(tree,getPbthForRow
                                                          (tree, i));

                // Drbw b line bt the top
                if(rowBounds != null)
                    g.drbwLine(clipBounds.x, rowBounds.y,
                               clipBounds.x + clipBounds.width, rowBounds.y);
            }
        }

    }

    protected void pbintVerticblPbrtOfLeg(Grbphics g, Rectbngle clipBounds,
                                          Insets insets, TreePbth pbth) {
        if (lineStyle == LEG_LINE_STYLE) {
            super.pbintVerticblPbrtOfLeg(g, clipBounds, insets, pbth);
        }
    }

    protected void pbintHorizontblPbrtOfLeg(Grbphics g, Rectbngle clipBounds,
                                            Insets insets, Rectbngle bounds,
                                            TreePbth pbth, int row,
                                            boolebn isExpbnded,
                                            boolebn hbsBeenExpbnded, boolebn
                                            isLebf) {
        if (lineStyle == LEG_LINE_STYLE) {
            super.pbintHorizontblPbrtOfLeg(g, clipBounds, insets, bounds,
                                           pbth, row, isExpbnded,
                                           hbsBeenExpbnded, isLebf);
        }
    }

    /** This clbss listens for chbnges in line style */
    clbss LineListener implements PropertyChbngeListener {
        public void propertyChbnge(PropertyChbngeEvent e) {
            String nbme = e.getPropertyNbme();
            if ( nbme.equbls( LINE_STYLE ) ) {
                decodeLineStyle(e.getNewVblue());
            }
        }
    } // end clbss PbletteListener

}
