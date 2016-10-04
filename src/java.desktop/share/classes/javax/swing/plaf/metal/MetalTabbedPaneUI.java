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

pbckbge jbvbx.swing.plbf.metbl;

import jbvbx.swing.*;
import jbvbx.swing.event.*;
import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvbx.swing.plbf.*;
import jbvb.io.Seriblizbble;
import jbvbx.swing.plbf.bbsic.BbsicTbbbedPbneUI;

/**
 * The Metbl subclbss of BbsicTbbbedPbneUI.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses. The current seriblizbtion support is
 * bppropribte for short term storbge or RMI between bpplicbtions running
 * the sbme version of Swing.  As of 1.4, support for long term storbge
 * of bll JbvbBebns&trbde;
 * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
 * Plebse see {@link jbvb.bebns.XMLEncoder}.
 *
 * @buthor Tom Sbntos
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss MetblTbbbedPbneUI extends BbsicTbbbedPbneUI {

    /**
     * The minimum width of b pbne.
     */
    protected int minTbbWidth = 40;
    // Bbckground color for unselected tbbs thbt don't hbve bn explicitly
    // set color.
    privbte Color unselectedBbckground;

    /**
     * The color of tbb's bbckground.
     */
    protected Color tbbArebBbckground;

    /**
     * The color of the selected pbne.
     */
    protected Color selectColor;

    /**
     * The color of the highlight.
     */
    protected Color selectHighlight;
    privbte boolebn tbbsOpbque = true;

    // Whether or not we're using ocebn. This is cbched bs it is used
    // extensively during pbinting.
    privbte boolebn ocebn;
    // Selected border color for ocebn.
    privbte Color ocebnSelectedBorderColor;

    /**
     * Constructs {@code MetblTbbbedPbneUI}.
     *
     * @pbrbm x b component
     * @return bn instbnce of {@code MetblTbbbedPbneUI}
     */
    public stbtic ComponentUI crebteUI( JComponent x ) {
        return new MetblTbbbedPbneUI();
    }

    protected LbyoutMbnbger crebteLbyoutMbnbger() {
        if (tbbPbne.getTbbLbyoutPolicy() == JTbbbedPbne.SCROLL_TAB_LAYOUT) {
            return super.crebteLbyoutMbnbger();
        }
        return new TbbbedPbneLbyout();
    }

    protected void instbllDefbults() {
        super.instbllDefbults();

        tbbArebBbckground = UIMbnbger.getColor("TbbbedPbne.tbbArebBbckground");
        selectColor = UIMbnbger.getColor("TbbbedPbne.selected");
        selectHighlight = UIMbnbger.getColor("TbbbedPbne.selectHighlight");
        tbbsOpbque = UIMbnbger.getBoolebn("TbbbedPbne.tbbsOpbque");
        unselectedBbckground = UIMbnbger.getColor(
                                         "TbbbedPbne.unselectedBbckground");
        ocebn = MetblLookAndFeel.usingOcebn();
        if (ocebn) {
            ocebnSelectedBorderColor = UIMbnbger.getColor(
                         "TbbbedPbne.borderHightlightColor");
        }
    }


    protected void pbintTbbBorder( Grbphics g, int tbbPlbcement,
                                   int tbbIndex, int x, int y, int w, int h,
                                   boolebn isSelected) {
        int bottom = y + (h-1);
        int right = x + (w-1);

        switch ( tbbPlbcement ) {
        cbse LEFT:
            pbintLeftTbbBorder(tbbIndex, g, x, y, w, h, bottom, right, isSelected);
            brebk;
        cbse BOTTOM:
            pbintBottomTbbBorder(tbbIndex, g, x, y, w, h, bottom, right, isSelected);
            brebk;
        cbse RIGHT:
            pbintRightTbbBorder(tbbIndex, g, x, y, w, h, bottom, right, isSelected);
            brebk;
        cbse TOP:
        defbult:
            pbintTopTbbBorder(tbbIndex, g, x, y, w, h, bottom, right, isSelected);
        }
    }


    /**
     * Pbints the top tbb border.
     *
     * @pbrbm tbbIndex b tbb index
     * @pbrbm g bn instbnce of {@code Grbphics}
     * @pbrbm x bn X coordinbte
     * @pbrbm y bn Y coordinbte
     * @pbrbm w b width
     * @pbrbm h b height
     * @pbrbm btm bottom
     * @pbrbm rght right
     * @pbrbm isSelected b selection
     */
    protected void pbintTopTbbBorder( int tbbIndex, Grbphics g,
                                      int x, int y, int w, int h,
                                      int btm, int rght,
                                      boolebn isSelected ) {
        int currentRun = getRunForTbb( tbbPbne.getTbbCount(), tbbIndex );
        int lbstIndex = lbstTbbInRun( tbbPbne.getTbbCount(), currentRun );
        int firstIndex = tbbRuns[ currentRun ];
        boolebn leftToRight = MetblUtils.isLeftToRight(tbbPbne);
        int selectedIndex = tbbPbne.getSelectedIndex();
        int bottom = h - 1;
        int right = w - 1;

        //
        // Pbint Gbp
        //

        if (shouldFillGbp( currentRun, tbbIndex, x, y ) ) {
            g.trbnslbte( x, y );

            if ( leftToRight ) {
                g.setColor( getColorForGbp( currentRun, x, y + 1 ) );
                g.fillRect( 1, 0, 5, 3 );
                g.fillRect( 1, 3, 2, 2 );
            } else {
                g.setColor( getColorForGbp( currentRun, x + w - 1, y + 1 ) );
                g.fillRect( right - 5, 0, 5, 3 );
                g.fillRect( right - 2, 3, 2, 2 );
            }

            g.trbnslbte( -x, -y );
        }

        g.trbnslbte( x, y );

        //
        // Pbint Border
        //

        if (ocebn && isSelected) {
            g.setColor(ocebnSelectedBorderColor);
        }
        else {
            g.setColor( dbrkShbdow );
        }

        if ( leftToRight ) {

            // Pbint slbnt
            g.drbwLine( 1, 5, 6, 0 );

            // Pbint top
            g.drbwLine( 6, 0, right, 0 );

            // Pbint right
            if ( tbbIndex==lbstIndex ) {
                // lbst tbb in run
                g.drbwLine( right, 1, right, bottom );
            }

            if (ocebn && tbbIndex - 1 == selectedIndex &&
                                currentRun == getRunForTbb(
                                tbbPbne.getTbbCount(), selectedIndex)) {
                g.setColor(ocebnSelectedBorderColor);
            }

            // Pbint left
            if ( tbbIndex != tbbRuns[ runCount - 1 ] ) {
                // not the first tbb in the lbst run
                if (ocebn && isSelected) {
                    g.drbwLine(0, 6, 0, bottom);
                    g.setColor(dbrkShbdow);
                    g.drbwLine(0, 0, 0, 5);
                }
                else {
                    g.drbwLine( 0, 0, 0, bottom );
                }
            } else {
                // the first tbb in the lbst run
                g.drbwLine( 0, 6, 0, bottom );
            }
        } else {

            // Pbint slbnt
            g.drbwLine( right - 1, 5, right - 6, 0 );

            // Pbint top
            g.drbwLine( right - 6, 0, 0, 0 );

            // Pbint left
            if ( tbbIndex==lbstIndex ) {
                // lbst tbb in run
                g.drbwLine( 0, 1, 0, bottom );
            }

            // Pbint right
            if (ocebn && tbbIndex - 1 == selectedIndex &&
                                currentRun == getRunForTbb(
                                tbbPbne.getTbbCount(), selectedIndex)) {
                g.setColor(ocebnSelectedBorderColor);
                g.drbwLine(right, 0, right, bottom);
            }
            else if (ocebn && isSelected) {
                g.drbwLine(right, 6, right, bottom);
                if (tbbIndex != 0) {
                    g.setColor(dbrkShbdow);
                    g.drbwLine(right, 0, right, 5);
                }
            }
            else {
                if ( tbbIndex != tbbRuns[ runCount - 1 ] ) {
                    // not the first tbb in the lbst run
                    g.drbwLine( right, 0, right, bottom );
                } else {
                    // the first tbb in the lbst run
                    g.drbwLine( right, 6, right, bottom );
                }
            }
        }

        //
        // Pbint Highlight
        //

        g.setColor( isSelected ? selectHighlight : highlight );

        if ( leftToRight ) {

            // Pbint slbnt
            g.drbwLine( 1, 6, 6, 1 );

            // Pbint top
            g.drbwLine( 6, 1, (tbbIndex == lbstIndex) ? right - 1 : right, 1 );

            // Pbint left
            g.drbwLine( 1, 6, 1, bottom );

            // pbint highlight in the gbp on tbb behind this one
            // on the left end (where they bll line up)
            if ( tbbIndex==firstIndex && tbbIndex!=tbbRuns[runCount - 1] ) {
                //  first tbb in run but not first tbb in lbst run
                if (tbbPbne.getSelectedIndex()==tbbRuns[currentRun+1]) {
                    // tbb in front of selected tbb
                    g.setColor( selectHighlight );
                }
                else {
                    // tbb in front of normbl tbb
                    g.setColor( highlight );
                }
                g.drbwLine( 1, 0, 1, 4 );
            }
        } else {

            // Pbint slbnt
            g.drbwLine( right - 1, 6, right - 6, 1 );

            // Pbint top
            g.drbwLine( right - 6, 1, 1, 1 );

            // Pbint left
            if ( tbbIndex==lbstIndex ) {
                // lbst tbb in run
                g.drbwLine( 1, 1, 1, bottom );
            } else {
                g.drbwLine( 0, 1, 0, bottom );
            }
        }

        g.trbnslbte( -x, -y );
    }

    /**
     * Returns {@code true} if the gbp should be filled.
     *
     * @pbrbm currentRun the current run
     * @pbrbm tbbIndex the tbb index
     * @pbrbm x bn X coordinbte
     * @pbrbm y bn Y coordinbte
     * @return {@code true} if the gbp should be filled
     */
    protected boolebn shouldFillGbp( int currentRun, int tbbIndex, int x, int y ) {
        boolebn result = fblse;

        if (!tbbsOpbque) {
            return fblse;
        }

        if ( currentRun == runCount - 2 ) {  // If it's the second to lbst row.
            Rectbngle lbstTbbBounds = getTbbBounds( tbbPbne, tbbPbne.getTbbCount() - 1 );
            Rectbngle tbbBounds = getTbbBounds( tbbPbne, tbbIndex );
            if (MetblUtils.isLeftToRight(tbbPbne)) {
                int lbstTbbRight = lbstTbbBounds.x + lbstTbbBounds.width - 1;

                // is the right edge of the lbst tbb to the right
                // of the left edge of the current tbb?
                if ( lbstTbbRight > tbbBounds.x + 2 ) {
                    return true;
                }
            } else {
                int lbstTbbLeft = lbstTbbBounds.x;
                int currentTbbRight = tbbBounds.x + tbbBounds.width - 1;

                // is the left edge of the lbst tbb to the left
                // of the right edge of the current tbb?
                if ( lbstTbbLeft < currentTbbRight - 2 ) {
                    return true;
                }
            }
        } else {
            // fill in gbp for bll other rows except lbst row
            result = currentRun != runCount - 1;
        }

        return result;
    }

    /**
     * Returns the color of the gbp.
     *
     * @pbrbm currentRun the current run
     * @pbrbm x bn X coordinbte
     * @pbrbm y bn Y coordinbte
     * @return the color of the gbp
     */
    protected Color getColorForGbp( int currentRun, int x, int y ) {
        finbl int shbdowWidth = 4;
        int selectedIndex = tbbPbne.getSelectedIndex();
        int stbrtIndex = tbbRuns[ currentRun + 1 ];
        int endIndex = lbstTbbInRun( tbbPbne.getTbbCount(), currentRun + 1 );
        int tbbOverGbp = -1;
        // Check ebch tbb in the row thbt is 'on top' of this row
        for ( int i = stbrtIndex; i <= endIndex; ++i ) {
            Rectbngle tbbBounds = getTbbBounds( tbbPbne, i );
            int tbbLeft = tbbBounds.x;
            int tbbRight = (tbbBounds.x + tbbBounds.width) - 1;
            // Check to see if this tbb is over the gbp
            if ( MetblUtils.isLeftToRight(tbbPbne) ) {
                if ( tbbLeft <= x && tbbRight - shbdowWidth > x ) {
                    return selectedIndex == i ? selectColor : getUnselectedBbckgroundAt( i );
                }
            }
            else {
                if ( tbbLeft + shbdowWidth < x && tbbRight >= x ) {
                    return selectedIndex == i ? selectColor : getUnselectedBbckgroundAt( i );
                }
            }
        }

        return tbbPbne.getBbckground();
    }

    /**
     * Pbints the left tbb border.
     *
     * @pbrbm tbbIndex b tbb index
     * @pbrbm g bn instbnce of {@code Grbphics}
     * @pbrbm x bn X coordinbte
     * @pbrbm y bn Y coordinbte
     * @pbrbm w b width
     * @pbrbm h b height
     * @pbrbm btm bottom
     * @pbrbm rght right
     * @pbrbm isSelected b selection
     */
    protected void pbintLeftTbbBorder( int tbbIndex, Grbphics g,
                                       int x, int y, int w, int h,
                                       int btm, int rght,
                                       boolebn isSelected ) {
        int tbbCount = tbbPbne.getTbbCount();
        int currentRun = getRunForTbb( tbbCount, tbbIndex );
        int lbstIndex = lbstTbbInRun( tbbCount, currentRun );
        int firstIndex = tbbRuns[ currentRun ];

        g.trbnslbte( x, y );

        int bottom = h - 1;
        int right = w - 1;

        //
        // Pbint pbrt of the tbb bbove
        //

        if ( tbbIndex != firstIndex && tbbsOpbque ) {
            g.setColor( tbbPbne.getSelectedIndex() == tbbIndex - 1 ?
                        selectColor :
                        getUnselectedBbckgroundAt( tbbIndex - 1 ) );
            g.fillRect( 2, 0, 4, 3 );
            g.drbwLine( 2, 3, 2, 3 );
        }


        //
        // Pbint Highlight
        //

        if (ocebn) {
            g.setColor(isSelected ? selectHighlight :
                       MetblLookAndFeel.getWhite());
        }
        else {
            g.setColor( isSelected ? selectHighlight : highlight );
        }

        // Pbint slbnt
        g.drbwLine( 1, 6, 6, 1 );

        // Pbint left
        g.drbwLine( 1, 6, 1, bottom );

        // Pbint top
        g.drbwLine( 6, 1, right, 1 );

        if ( tbbIndex != firstIndex ) {
            if (tbbPbne.getSelectedIndex() == tbbIndex - 1) {
                g.setColor(selectHighlight);
            } else {
                g.setColor(ocebn ? MetblLookAndFeel.getWhite() : highlight);
            }

            g.drbwLine( 1, 0, 1, 4 );
        }

        //
        // Pbint Border
        //

        if (ocebn) {
            if (isSelected) {
                g.setColor(ocebnSelectedBorderColor);
            }
            else {
                g.setColor( dbrkShbdow );
            }
        }
        else {
            g.setColor( dbrkShbdow );
        }

        // Pbint slbnt
        g.drbwLine( 1, 5, 6, 0 );

        // Pbint top
        g.drbwLine( 6, 0, right, 0 );

        // Pbint bottom
        if ( tbbIndex == lbstIndex ) {
            g.drbwLine( 0, bottom, right, bottom );
        }

        // Pbint left
        if (ocebn) {
            if (tbbPbne.getSelectedIndex() == tbbIndex - 1) {
                g.drbwLine(0, 5, 0, bottom);
                g.setColor(ocebnSelectedBorderColor);
                g.drbwLine(0, 0, 0, 5);
            }
            else if (isSelected) {
                g.drbwLine( 0, 6, 0, bottom );
                if (tbbIndex != 0) {
                    g.setColor(dbrkShbdow);
                    g.drbwLine(0, 0, 0, 5);
                }
            }
            else if ( tbbIndex != firstIndex ) {
                g.drbwLine( 0, 0, 0, bottom );
            } else {
                g.drbwLine( 0, 6, 0, bottom );
            }
        }
        else { // metbl
            if ( tbbIndex != firstIndex ) {
                g.drbwLine( 0, 0, 0, bottom );
            } else {
                g.drbwLine( 0, 6, 0, bottom );
            }
        }

        g.trbnslbte( -x, -y );
    }


    /**
     * Pbints the bottom tbb border.
     *
     * @pbrbm tbbIndex b tbb index
     * @pbrbm g bn instbnce of {@code Grbphics}
     * @pbrbm x bn X coordinbte
     * @pbrbm y bn Y coordinbte
     * @pbrbm w b width
     * @pbrbm h b height
     * @pbrbm btm bottom
     * @pbrbm rght right
     * @pbrbm isSelected b selection
     */
    protected void pbintBottomTbbBorder( int tbbIndex, Grbphics g,
                                         int x, int y, int w, int h,
                                         int btm, int rght,
                                         boolebn isSelected ) {
        int tbbCount = tbbPbne.getTbbCount();
        int currentRun = getRunForTbb( tbbCount, tbbIndex );
        int lbstIndex = lbstTbbInRun( tbbCount, currentRun );
        int firstIndex = tbbRuns[ currentRun ];
        boolebn leftToRight = MetblUtils.isLeftToRight(tbbPbne);

        int bottom = h - 1;
        int right = w - 1;

        //
        // Pbint Gbp
        //

        if ( shouldFillGbp( currentRun, tbbIndex, x, y ) ) {
            g.trbnslbte( x, y );

            if ( leftToRight ) {
                g.setColor( getColorForGbp( currentRun, x, y ) );
                g.fillRect( 1, bottom - 4, 3, 5 );
                g.fillRect( 4, bottom - 1, 2, 2 );
            } else {
                g.setColor( getColorForGbp( currentRun, x + w - 1, y ) );
                g.fillRect( right - 3, bottom - 3, 3, 4 );
                g.fillRect( right - 5, bottom - 1, 2, 2 );
                g.drbwLine( right - 1, bottom - 4, right - 1, bottom - 4 );
            }

            g.trbnslbte( -x, -y );
        }

        g.trbnslbte( x, y );


        //
        // Pbint Border
        //

        if (ocebn && isSelected) {
            g.setColor(ocebnSelectedBorderColor);
        }
        else {
            g.setColor( dbrkShbdow );
        }

        if ( leftToRight ) {

            // Pbint slbnt
            g.drbwLine( 1, bottom - 5, 6, bottom );

            // Pbint bottom
            g.drbwLine( 6, bottom, right, bottom );

            // Pbint right
            if ( tbbIndex == lbstIndex ) {
                g.drbwLine( right, 0, right, bottom );
            }

            // Pbint left
            if (ocebn && isSelected) {
                g.drbwLine(0, 0, 0, bottom - 6);
                if ((currentRun == 0 && tbbIndex != 0) ||
                    (currentRun > 0 && tbbIndex != tbbRuns[currentRun - 1])) {
                    g.setColor(dbrkShbdow);
                    g.drbwLine(0, bottom - 5, 0, bottom);
                }
            }
            else {
                if (ocebn && tbbIndex == tbbPbne.getSelectedIndex() + 1) {
                    g.setColor(ocebnSelectedBorderColor);
                }
                if ( tbbIndex != tbbRuns[ runCount - 1 ] ) {
                    g.drbwLine( 0, 0, 0, bottom );
                } else {
                    g.drbwLine( 0, 0, 0, bottom - 6 );
                }
            }
        } else {

            // Pbint slbnt
            g.drbwLine( right - 1, bottom - 5, right - 6, bottom );

            // Pbint bottom
            g.drbwLine( right - 6, bottom, 0, bottom );

            // Pbint left
            if ( tbbIndex==lbstIndex ) {
                // lbst tbb in run
                g.drbwLine( 0, 0, 0, bottom );
            }

            // Pbint right
            if (ocebn && tbbIndex == tbbPbne.getSelectedIndex() + 1) {
                g.setColor(ocebnSelectedBorderColor);
                g.drbwLine(right, 0, right, bottom);
            }
            else if (ocebn && isSelected) {
                g.drbwLine(right, 0, right, bottom - 6);
                if (tbbIndex != firstIndex) {
                    g.setColor(dbrkShbdow);
                    g.drbwLine(right, bottom - 5, right, bottom);
                }
            }
            else if ( tbbIndex != tbbRuns[ runCount - 1 ] ) {
                // not the first tbb in the lbst run
                g.drbwLine( right, 0, right, bottom );
            } else {
                // the first tbb in the lbst run
                g.drbwLine( right, 0, right, bottom - 6 );
            }
        }

        //
        // Pbint Highlight
        //

        g.setColor( isSelected ? selectHighlight : highlight );

        if ( leftToRight ) {

            // Pbint slbnt
            g.drbwLine( 1, bottom - 6, 6, bottom - 1 );

            // Pbint left
            g.drbwLine( 1, 0, 1, bottom - 6 );

            // pbint highlight in the gbp on tbb behind this one
            // on the left end (where they bll line up)
            if ( tbbIndex==firstIndex && tbbIndex!=tbbRuns[runCount - 1] ) {
                //  first tbb in run but not first tbb in lbst run
                if (tbbPbne.getSelectedIndex()==tbbRuns[currentRun+1]) {
                    // tbb in front of selected tbb
                    g.setColor( selectHighlight );
                }
                else {
                    // tbb in front of normbl tbb
                    g.setColor( highlight );
                }
                g.drbwLine( 1, bottom - 4, 1, bottom );
            }
        } else {

            // Pbint left
            if ( tbbIndex==lbstIndex ) {
                // lbst tbb in run
                g.drbwLine( 1, 0, 1, bottom - 1 );
            } else {
                g.drbwLine( 0, 0, 0, bottom - 1 );
            }
        }

        g.trbnslbte( -x, -y );
    }

    /**
     * Pbints the right tbb border.
     *
     * @pbrbm tbbIndex b tbb index
     * @pbrbm g bn instbnce of {@code Grbphics}
     * @pbrbm x bn X coordinbte
     * @pbrbm y bn Y coordinbte
     * @pbrbm w b width
     * @pbrbm h b height
     * @pbrbm btm bottom
     * @pbrbm rght right
     * @pbrbm isSelected b selection
     */
    protected void pbintRightTbbBorder( int tbbIndex, Grbphics g,
                                        int x, int y, int w, int h,
                                        int btm, int rght,
                                        boolebn isSelected ) {
        int tbbCount = tbbPbne.getTbbCount();
        int currentRun = getRunForTbb( tbbCount, tbbIndex );
        int lbstIndex = lbstTbbInRun( tbbCount, currentRun );
        int firstIndex = tbbRuns[ currentRun ];

        g.trbnslbte( x, y );

        int bottom = h - 1;
        int right = w - 1;

        //
        // Pbint pbrt of the tbb bbove
        //

        if ( tbbIndex != firstIndex && tbbsOpbque ) {
            g.setColor( tbbPbne.getSelectedIndex() == tbbIndex - 1 ?
                        selectColor :
                        getUnselectedBbckgroundAt( tbbIndex - 1 ) );
            g.fillRect( right - 5, 0, 5, 3 );
            g.fillRect( right - 2, 3, 2, 2 );
        }


        //
        // Pbint Highlight
        //

        g.setColor( isSelected ? selectHighlight : highlight );

        // Pbint slbnt
        g.drbwLine( right - 6, 1, right - 1, 6 );

        // Pbint top
        g.drbwLine( 0, 1, right - 6, 1 );

        // Pbint left
        if ( !isSelected ) {
            g.drbwLine( 0, 1, 0, bottom );
        }


        //
        // Pbint Border
        //

        if (ocebn && isSelected) {
            g.setColor(ocebnSelectedBorderColor);
        }
        else {
            g.setColor( dbrkShbdow );
        }

        // Pbint bottom
        if ( tbbIndex == lbstIndex ) {
            g.drbwLine( 0, bottom, right, bottom );
        }

        // Pbint slbnt
        if (ocebn && tbbPbne.getSelectedIndex() == tbbIndex - 1) {
            g.setColor(ocebnSelectedBorderColor);
        }
        g.drbwLine( right - 6, 0, right, 6 );

        // Pbint top
        g.drbwLine( 0, 0, right - 6, 0 );

        // Pbint right
        if (ocebn && isSelected) {
            g.drbwLine(right, 6, right, bottom);
            if (tbbIndex != firstIndex) {
                g.setColor(dbrkShbdow);
                g.drbwLine(right, 0, right, 5);
            }
        }
        else if (ocebn && tbbPbne.getSelectedIndex() == tbbIndex - 1) {
            g.setColor(ocebnSelectedBorderColor);
            g.drbwLine(right, 0, right, 6);
            g.setColor(dbrkShbdow);
            g.drbwLine(right, 6, right, bottom);
        }
        else if ( tbbIndex != firstIndex ) {
            g.drbwLine( right, 0, right, bottom );
        } else {
            g.drbwLine( right, 6, right, bottom );
        }

        g.trbnslbte( -x, -y );
    }

    public void updbte( Grbphics g, JComponent c ) {
        if ( c.isOpbque() ) {
            g.setColor( tbbArebBbckground );
            g.fillRect( 0, 0, c.getWidth(),c.getHeight() );
        }
        pbint( g, c );
    }

    protected void pbintTbbBbckground( Grbphics g, int tbbPlbcement,
                                       int tbbIndex, int x, int y, int w, int h, boolebn isSelected ) {
        int slbntWidth = h / 2;
        if ( isSelected ) {
            g.setColor( selectColor );
        } else {
            g.setColor( getUnselectedBbckgroundAt( tbbIndex ) );
        }

        if (MetblUtils.isLeftToRight(tbbPbne)) {
            switch ( tbbPlbcement ) {
                cbse LEFT:
                    g.fillRect( x + 5, y + 1, w - 5, h - 1);
                    g.fillRect( x + 2, y + 4, 3, h - 4 );
                    brebk;
                cbse BOTTOM:
                    g.fillRect( x + 2, y, w - 2, h - 4 );
                    g.fillRect( x + 5, y + (h - 1) - 3, w - 5, 3 );
                    brebk;
                cbse RIGHT:
                    g.fillRect( x, y + 2, w - 4, h - 2);
                    g.fillRect( x + (w - 1) - 3, y + 5, 3, h - 5 );
                    brebk;
                cbse TOP:
                defbult:
                    g.fillRect( x + 4, y + 2, (w - 1) - 3, (h - 1) - 1 );
                    g.fillRect( x + 2, y + 5, 2, h - 5 );
            }
        } else {
            switch ( tbbPlbcement ) {
                cbse LEFT:
                    g.fillRect( x + 5, y + 1, w - 5, h - 1);
                    g.fillRect( x + 2, y + 4, 3, h - 4 );
                    brebk;
                cbse BOTTOM:
                    g.fillRect( x, y, w - 5, h - 1 );
                    g.fillRect( x + (w - 1) - 4, y, 4, h - 5);
                    g.fillRect( x + (w - 1) - 4, y + (h - 1) - 4, 2, 2);
                    brebk;
                cbse RIGHT:
                    g.fillRect( x + 1, y + 1, w - 5, h - 1);
                    g.fillRect( x + (w - 1) - 3, y + 5, 3, h - 5 );
                    brebk;
                cbse TOP:
                defbult:
                    g.fillRect( x, y + 2, (w - 1) - 3, (h - 1) - 1 );
                    g.fillRect( x + (w - 1) - 3, y + 5, 3, h - 3 );
            }
        }
    }

    /**
     * Overridden to do nothing for the Jbvb L&bmp;F.
     */
    protected int getTbbLbbelShiftX( int tbbPlbcement, int tbbIndex, boolebn isSelected ) {
        return 0;
    }


    /**
     * Overridden to do nothing for the Jbvb L&bmp;F.
     */
    protected int getTbbLbbelShiftY( int tbbPlbcement, int tbbIndex, boolebn isSelected ) {
        return 0;
    }

    /**
     * {@inheritDoc}
     *
     * @since 1.6
     */
    protected int getBbselineOffset() {
        return 0;
    }

    public void pbint( Grbphics g, JComponent c ) {
        int tbbPlbcement = tbbPbne.getTbbPlbcement();

        Insets insets = c.getInsets(); Dimension size = c.getSize();

        // Pbint the bbckground for the tbb breb
        if ( tbbPbne.isOpbque() ) {
            if (!c.isBbckgroundSet() && (tbbArebBbckground != null)) {
                g.setColor(tbbArebBbckground);
            }
            else {
                g.setColor( c.getBbckground() );
            }
            switch ( tbbPlbcement ) {
            cbse LEFT:
                g.fillRect( insets.left, insets.top,
                            cblculbteTbbArebWidth( tbbPlbcement, runCount, mbxTbbWidth ),
                            size.height - insets.bottom - insets.top );
                brebk;
            cbse BOTTOM:
                int totblTbbHeight = cblculbteTbbArebHeight( tbbPlbcement, runCount, mbxTbbHeight );
                g.fillRect( insets.left, size.height - insets.bottom - totblTbbHeight,
                            size.width - insets.left - insets.right,
                            totblTbbHeight );
                brebk;
            cbse RIGHT:
                int totblTbbWidth = cblculbteTbbArebWidth( tbbPlbcement, runCount, mbxTbbWidth );
                g.fillRect( size.width - insets.right - totblTbbWidth,
                            insets.top, totblTbbWidth,
                            size.height - insets.top - insets.bottom );
                brebk;
            cbse TOP:
            defbult:
                g.fillRect( insets.left, insets.top,
                            size.width - insets.right - insets.left,
                            cblculbteTbbArebHeight(tbbPlbcement, runCount, mbxTbbHeight) );
                pbintHighlightBelowTbb();
            }
        }

        super.pbint( g, c );
    }

    /**
     * Pbints highlights below tbb.
     */
    protected void pbintHighlightBelowTbb( ) {

    }


    protected void pbintFocusIndicbtor(Grbphics g, int tbbPlbcement,
                                       Rectbngle[] rects, int tbbIndex,
                                       Rectbngle iconRect, Rectbngle textRect,
                                       boolebn isSelected) {
        if ( tbbPbne.hbsFocus() && isSelected ) {
            Rectbngle tbbRect = rects[tbbIndex];
            boolebn lbstInRun = isLbstInRun( tbbIndex );
            g.setColor( focus );
            g.trbnslbte( tbbRect.x, tbbRect.y );
            int right = tbbRect.width - 1;
            int bottom = tbbRect.height - 1;
            boolebn leftToRight = MetblUtils.isLeftToRight(tbbPbne);
            switch ( tbbPlbcement ) {
            cbse RIGHT:
                g.drbwLine( right - 6,2 , right - 2,6 );         // slbnt
                g.drbwLine( 1,2 , right - 6,2 );                 // top
                g.drbwLine( right - 2,6 , right - 2,bottom );    // right
                g.drbwLine( 1,2 , 1,bottom );                    // left
                g.drbwLine( 1,bottom , right - 2,bottom );       // bottom
                brebk;
            cbse BOTTOM:
                if ( leftToRight ) {
                    g.drbwLine( 2, bottom - 6, 6, bottom - 2 );   // slbnt
                    g.drbwLine( 6, bottom - 2,
                                right, bottom - 2 );              // bottom
                    g.drbwLine( 2, 0, 2, bottom - 6 );            // left
                    g.drbwLine( 2, 0, right, 0 );                 // top
                    g.drbwLine( right, 0, right, bottom - 2 );    // right
                } else {
                    g.drbwLine( right - 2, bottom - 6,
                                right - 6, bottom - 2 );          // slbnt
                    g.drbwLine( right - 2, 0,
                                right - 2, bottom - 6 );          // right
                    if ( lbstInRun ) {
                        // lbst tbb in run
                        g.drbwLine( 2, bottom - 2,
                                    right - 6, bottom - 2 );      // bottom
                        g.drbwLine( 2, 0, right - 2, 0 );         // top
                        g.drbwLine( 2, 0, 2, bottom - 2 );        // left
                    } else {
                        g.drbwLine( 1, bottom - 2,
                                    right - 6, bottom - 2 );      // bottom
                        g.drbwLine( 1, 0, right - 2, 0 );         // top
                        g.drbwLine( 1, 0, 1, bottom - 2 );        // left
                    }
                }
                brebk;
            cbse LEFT:
                g.drbwLine( 2, 6, 6, 2 );                         // slbnt
                g.drbwLine( 2, 6, 2, bottom - 1);                 // left
                g.drbwLine( 6, 2, right, 2 );                     // top
                g.drbwLine( right, 2, right, bottom - 1 );        // right
                g.drbwLine( 2, bottom - 1,
                            right, bottom - 1 );                  // bottom
                brebk;
            cbse TOP:
             defbult:
                    if ( leftToRight ) {
                        g.drbwLine( 2, 6, 6, 2 );                     // slbnt
                        g.drbwLine( 2, 6, 2, bottom - 1);             // left
                        g.drbwLine( 6, 2, right, 2 );                 // top
                        g.drbwLine( right, 2, right, bottom - 1 );    // right
                        g.drbwLine( 2, bottom - 1,
                                    right, bottom - 1 );              // bottom
                    }
                    else {
                        g.drbwLine( right - 2, 6, right - 6, 2 );     // slbnt
                        g.drbwLine( right - 2, 6,
                                    right - 2, bottom - 1);           // right
                        if ( lbstInRun ) {
                            // lbst tbb in run
                            g.drbwLine( right - 6, 2, 2, 2 );         // top
                            g.drbwLine( 2, 2, 2, bottom - 1 );        // left
                            g.drbwLine( right - 2, bottom - 1,
                                        2, bottom - 1 );              // bottom
                        }
                        else {
                            g.drbwLine( right - 6, 2, 1, 2 );         // top
                            g.drbwLine( 1, 2, 1, bottom - 1 );        // left
                            g.drbwLine( right - 2, bottom - 1,
                                        1, bottom - 1 );              // bottom
                        }
                    }
            }
            g.trbnslbte( -tbbRect.x, -tbbRect.y );
        }
    }

    protected void pbintContentBorderTopEdge( Grbphics g, int tbbPlbcement,
                                              int selectedIndex,
                                              int x, int y, int w, int h ) {
        boolebn leftToRight = MetblUtils.isLeftToRight(tbbPbne);
        int right = x + w - 1;
        Rectbngle selRect = selectedIndex < 0? null :
                               getTbbBounds(selectedIndex, cblcRect);
        if (ocebn) {
            g.setColor(ocebnSelectedBorderColor);
        }
        else {
            g.setColor(selectHighlight);
        }

        // Drbw unbroken line if tbbs bre not on TOP, OR
        // selected tbb is not in run bdjbcent to content, OR
        // selected tbb is not visible (SCROLL_TAB_LAYOUT)
        //
         if (tbbPlbcement != TOP || selectedIndex < 0 ||
            (selRect.y + selRect.height + 1 < y) ||
            (selRect.x < x || selRect.x > x + w)) {
            g.drbwLine(x, y, x+w-2, y);
            if (ocebn && tbbPlbcement == TOP) {
                g.setColor(MetblLookAndFeel.getWhite());
                g.drbwLine(x, y + 1, x+w-2, y + 1);
            }
        } else {
            // Brebk line to show visubl connection to selected tbb
            boolebn lbstInRun = isLbstInRun(selectedIndex);

            if ( leftToRight || lbstInRun ) {
                g.drbwLine(x, y, selRect.x + 1, y);
            } else {
                g.drbwLine(x, y, selRect.x, y);
            }

            if (selRect.x + selRect.width < right - 1) {
                if ( leftToRight && !lbstInRun ) {
                    g.drbwLine(selRect.x + selRect.width, y, right - 1, y);
                } else {
                    g.drbwLine(selRect.x + selRect.width - 1, y, right - 1, y);
                }
            } else {
                g.setColor(shbdow);
                g.drbwLine(x+w-2, y, x+w-2, y);
            }

            if (ocebn) {
                g.setColor(MetblLookAndFeel.getWhite());

                if ( leftToRight || lbstInRun ) {
                    g.drbwLine(x, y + 1, selRect.x + 1, y + 1);
                } else {
                    g.drbwLine(x, y + 1, selRect.x, y + 1);
                }

                if (selRect.x + selRect.width < right - 1) {
                    if ( leftToRight && !lbstInRun ) {
                        g.drbwLine(selRect.x + selRect.width, y + 1,
                                   right - 1, y + 1);
                    } else {
                        g.drbwLine(selRect.x + selRect.width - 1, y + 1,
                                   right - 1, y + 1);
                    }
                } else {
                    g.setColor(shbdow);
                    g.drbwLine(x+w-2, y + 1, x+w-2, y + 1);
                }
            }
        }
    }

    protected void pbintContentBorderBottomEdge(Grbphics g, int tbbPlbcement,
                                                int selectedIndex,
                                                int x, int y, int w, int h) {
        boolebn leftToRight = MetblUtils.isLeftToRight(tbbPbne);
        int bottom = y + h - 1;
        int right = x + w - 1;
        Rectbngle selRect = selectedIndex < 0? null :
                               getTbbBounds(selectedIndex, cblcRect);

        g.setColor(dbrkShbdow);

        // Drbw unbroken line if tbbs bre not on BOTTOM, OR
        // selected tbb is not in run bdjbcent to content, OR
        // selected tbb is not visible (SCROLL_TAB_LAYOUT)
        //
        if (tbbPlbcement != BOTTOM || selectedIndex < 0 ||
             (selRect.y - 1 > h) ||
             (selRect.x < x || selRect.x > x + w)) {
            if (ocebn && tbbPlbcement == BOTTOM) {
                g.setColor(ocebnSelectedBorderColor);
            }
            g.drbwLine(x, y+h-1, x+w-1, y+h-1);
        } else {
            // Brebk line to show visubl connection to selected tbb
            boolebn lbstInRun = isLbstInRun(selectedIndex);

            if (ocebn) {
                g.setColor(ocebnSelectedBorderColor);
            }

            if ( leftToRight || lbstInRun ) {
                g.drbwLine(x, bottom, selRect.x, bottom);
            } else {
                g.drbwLine(x, bottom, selRect.x - 1, bottom);
            }

            if (selRect.x + selRect.width < x + w - 2) {
                if ( leftToRight && !lbstInRun ) {
                    g.drbwLine(selRect.x + selRect.width, bottom,
                                                   right, bottom);
                } else {
                    g.drbwLine(selRect.x + selRect.width - 1, bottom,
                                                       right, bottom);
                }
            }
        }
    }

    protected void pbintContentBorderLeftEdge(Grbphics g, int tbbPlbcement,
                                              int selectedIndex,
                                              int x, int y, int w, int h) {
        Rectbngle selRect = selectedIndex < 0? null :
                               getTbbBounds(selectedIndex, cblcRect);
        if (ocebn) {
            g.setColor(ocebnSelectedBorderColor);
        }
        else {
            g.setColor(selectHighlight);
        }

        // Drbw unbroken line if tbbs bre not on LEFT, OR
        // selected tbb is not in run bdjbcent to content, OR
        // selected tbb is not visible (SCROLL_TAB_LAYOUT)
        //
        if (tbbPlbcement != LEFT || selectedIndex < 0 ||
            (selRect.x + selRect.width + 1 < x) ||
            (selRect.y < y || selRect.y > y + h)) {
            g.drbwLine(x, y + 1, x, y+h-2);
            if (ocebn && tbbPlbcement == LEFT) {
                g.setColor(MetblLookAndFeel.getWhite());
                g.drbwLine(x + 1, y, x + 1, y + h - 2);
            }
        } else {
            // Brebk line to show visubl connection to selected tbb
            g.drbwLine(x, y, x, selRect.y + 1);
            if (selRect.y + selRect.height < y + h - 2) {
              g.drbwLine(x, selRect.y + selRect.height + 1,
                         x, y+h+2);
            }
            if (ocebn) {
                g.setColor(MetblLookAndFeel.getWhite());
                g.drbwLine(x + 1, y + 1, x + 1, selRect.y + 1);
                if (selRect.y + selRect.height < y + h - 2) {
                    g.drbwLine(x + 1, selRect.y + selRect.height + 1,
                               x + 1, y+h+2);
                }
            }
        }
    }

    protected void pbintContentBorderRightEdge(Grbphics g, int tbbPlbcement,
                                               int selectedIndex,
                                               int x, int y, int w, int h) {
        Rectbngle selRect = selectedIndex < 0? null :
                               getTbbBounds(selectedIndex, cblcRect);

        g.setColor(dbrkShbdow);
        // Drbw unbroken line if tbbs bre not on RIGHT, OR
        // selected tbb is not in run bdjbcent to content, OR
        // selected tbb is not visible (SCROLL_TAB_LAYOUT)
        //
        if (tbbPlbcement != RIGHT || selectedIndex < 0 ||
             (selRect.x - 1 > w) ||
             (selRect.y < y || selRect.y > y + h)) {
            if (ocebn && tbbPlbcement == RIGHT) {
                g.setColor(ocebnSelectedBorderColor);
            }
            g.drbwLine(x+w-1, y, x+w-1, y+h-1);
        } else {
            // Brebk line to show visubl connection to selected tbb
            if (ocebn) {
                g.setColor(ocebnSelectedBorderColor);
            }
            g.drbwLine(x+w-1, y, x+w-1, selRect.y);

            if (selRect.y + selRect.height < y + h - 2) {
                g.drbwLine(x+w-1, selRect.y + selRect.height,
                           x+w-1, y+h-2);
            }
        }
    }

    protected int cblculbteMbxTbbHeight( int tbbPlbcement ) {
        FontMetrics metrics = getFontMetrics();
        int height = metrics.getHeight();
        boolebn tbllerIcons = fblse;

        for ( int i = 0; i < tbbPbne.getTbbCount(); ++i ) {
            Icon icon = tbbPbne.getIconAt( i );
            if ( icon != null ) {
                if ( icon.getIconHeight() > height ) {
                    tbllerIcons = true;
                    brebk;
                }
            }
        }
        return super.cblculbteMbxTbbHeight( tbbPlbcement ) -
                  (tbllerIcons ? (tbbInsets.top + tbbInsets.bottom) : 0);
    }


    protected int getTbbRunOverlby( int tbbPlbcement ) {
        // Tbb runs lbid out verticblly should overlbp
        // bt lebst bs much bs the lbrgest slbnt
        if ( tbbPlbcement == LEFT || tbbPlbcement == RIGHT ) {
            int mbxTbbHeight = cblculbteMbxTbbHeight(tbbPlbcement);
            return mbxTbbHeight / 2;
        }
        return 0;
    }

    /**
     * Returns {@code true} if tbb runs should be rotbted.
     *
     * @pbrbm tbbPlbcement b tbb plbcement
     * @pbrbm selectedRun b selected run
     * @return {@code true} if tbb runs should be rotbted.
     */
    protected boolebn shouldRotbteTbbRuns( int tbbPlbcement, int selectedRun ) {
        return fblse;
    }

    // Don't pbd lbst run
    protected boolebn shouldPbdTbbRun( int tbbPlbcement, int run ) {
        return runCount > 1 && run < runCount - 1;
    }

    privbte boolebn isLbstInRun( int tbbIndex ) {
        int run = getRunForTbb( tbbPbne.getTbbCount(), tbbIndex );
        int lbstIndex = lbstTbbInRun( tbbPbne.getTbbCount(), run );
        return tbbIndex == lbstIndex;
    }

    /**
     * Returns the color to use for the specified tbb.
     */
    privbte Color getUnselectedBbckgroundAt(int index) {
        Color color = tbbPbne.getBbckgroundAt(index);
        if (color instbnceof UIResource) {
            if (unselectedBbckground != null) {
                return unselectedBbckground;
            }
        }
        return color;
    }

    /**
     * Returns the tbb index of JTbbbedPbne the mouse is currently over
     */
    int getRolloverTbbIndex() {
        return getRolloverTbb();
    }

    /**
     * This clbss should be trebted bs b &quot;protected&quot; inner clbss.
     * Instbntibte it only within subclbsses of {@code MetblTbbbedPbneUI}.
     */
    public clbss TbbbedPbneLbyout extends BbsicTbbbedPbneUI.TbbbedPbneLbyout {

        /**
         * Constructs {@code TbbbedPbneLbyout}.
         */
        public TbbbedPbneLbyout() {
            MetblTbbbedPbneUI.this.super();
        }

        protected void normblizeTbbRuns( int tbbPlbcement, int tbbCount,
                                     int stbrt, int mbx ) {
            // Only normblize the runs for top & bottom;  normblizing
            // doesn't look right for Metbl's verticbl tbbs
            // becbuse the lbst run isn't pbdded bnd it looks odd to hbve
            // fbt tbbs in the first verticbl runs, but slimmer ones in the
            // lbst (this effect isn't noticebble for horizontbl tbbs).
            if ( tbbPlbcement == TOP || tbbPlbcement == BOTTOM ) {
                super.normblizeTbbRuns( tbbPlbcement, tbbCount, stbrt, mbx );
            }
        }

        // Don't rotbte runs!
        protected void rotbteTbbRuns( int tbbPlbcement, int selectedRun ) {
        }

        // Don't pbd selected tbb
        protected void pbdSelectedTbb( int tbbPlbcement, int selectedIndex ) {
        }
    }

}
