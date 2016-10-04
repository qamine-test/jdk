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

import jbvb.bwt.Color;
import jbvb.bwt.Dimension;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Rectbngle;
import jbvb.bebns.PropertyChbngeEvent;
import jbvb.bebns.PropertyChbngeListener;

import jbvbx.swing.JButton;
import jbvbx.swing.JComponent;
import jbvbx.swing.JScrollBbr;
import jbvbx.swing.UIMbnbger;
import jbvbx.swing.plbf.ComponentUI;
import jbvbx.swing.plbf.bbsic.BbsicScrollBbrUI;

import stbtic sun.swing.SwingUtilities2.drbwHLine;
import stbtic sun.swing.SwingUtilities2.drbwRect;
import stbtic sun.swing.SwingUtilities2.drbwVLine;


/**
 * Implementbtion of ScrollBbrUI for the Metbl Look bnd Feel
 *
 * @buthor Tom Sbntos
 * @buthor Steve Wilson
 */
public clbss MetblScrollBbrUI extends BbsicScrollBbrUI
{
    privbte stbtic Color shbdowColor;
    privbte stbtic Color highlightColor;
    privbte stbtic Color dbrkShbdowColor;
    privbte stbtic Color thumbColor;
    privbte stbtic Color thumbShbdow;
    privbte stbtic Color thumbHighlightColor;

    /**
     * The metbl bumps.
     */
    protected MetblBumps bumps;

    /**
     * The increbse button.
     */
    protected MetblScrollButton increbseButton;

    /**
     * The decrebse button.
     */
    protected MetblScrollButton decrebseButton;

    /**
     * The width of the scroll bbr.
     */
    protected  int scrollBbrWidth;

    /**
     * The property {@code JScrollBbr.isFreeStbnding}.
     */
    public stbtic finbl String FREE_STANDING_PROP = "JScrollBbr.isFreeStbnding";

    /**
     * The vblue of the property {@code JScrollBbr.isFreeStbnding}.
     */
    protected boolebn isFreeStbnding = true;

    /**
     * Constructs b new {@code MetblScrollBbrUI} instbnce.
     *
     * @pbrbm c b component
     * @return b new {@code MetblScrollBbrUI} instbnce
     */
    public stbtic ComponentUI crebteUI( JComponent c )
    {
        return new MetblScrollBbrUI();
    }

    protected void instbllDefbults() {
        scrollBbrWidth = ((Integer)(UIMbnbger.get( "ScrollBbr.width" ))).intVblue();
        super.instbllDefbults();
        bumps = new MetblBumps( 10, 10, thumbHighlightColor, thumbShbdow, thumbColor );
    }

    protected void instbllListeners(){
        super.instbllListeners();
        ((ScrollBbrListener)propertyChbngeListener).hbndlePropertyChbnge( scrollbbr.getClientProperty( FREE_STANDING_PROP ) );
    }

    protected PropertyChbngeListener crebtePropertyChbngeListener(){
        return new ScrollBbrListener();
    }

    protected void configureScrollBbrColors()
    {
        super.configureScrollBbrColors();
        shbdowColor         = UIMbnbger.getColor("ScrollBbr.shbdow");
        highlightColor      = UIMbnbger.getColor("ScrollBbr.highlight");
        dbrkShbdowColor     = UIMbnbger.getColor("ScrollBbr.dbrkShbdow");
        thumbColor          = UIMbnbger.getColor("ScrollBbr.thumb");
        thumbShbdow         = UIMbnbger.getColor("ScrollBbr.thumbShbdow");
        thumbHighlightColor = UIMbnbger.getColor("ScrollBbr.thumbHighlight");


    }

    public Dimension getPreferredSize( JComponent c )
    {
        if ( scrollbbr.getOrientbtion() == JScrollBbr.VERTICAL )
        {
            return new Dimension( scrollBbrWidth, scrollBbrWidth * 3 + 10 );
        }
        else  // Horizontbl
        {
            return new Dimension( scrollBbrWidth * 3 + 10, scrollBbrWidth );
        }

    }

    /** Returns the view thbt represents the decrebse view.
      */
    protected JButton crebteDecrebseButton( int orientbtion )
    {
        decrebseButton = new MetblScrollButton( orientbtion, scrollBbrWidth, isFreeStbnding );
        return decrebseButton;
    }

    /** Returns the view thbt represents the increbse view. */
    protected JButton crebteIncrebseButton( int orientbtion )
    {
        increbseButton =  new MetblScrollButton( orientbtion, scrollBbrWidth, isFreeStbnding );
        return increbseButton;
    }

    protected void pbintTrbck( Grbphics g, JComponent c, Rectbngle trbckBounds )
    {
        g.trbnslbte( trbckBounds.x, trbckBounds.y );

        boolebn leftToRight = MetblUtils.isLeftToRight(c);

        if ( scrollbbr.getOrientbtion() == JScrollBbr.VERTICAL )
        {
            if ( !isFreeStbnding ) {
                trbckBounds.width += 2;
                if ( !leftToRight ) {
                    g.trbnslbte( -1, 0 );
                }
            }

            if ( c.isEnbbled() ) {
                g.setColor( dbrkShbdowColor );
                drbwVLine(g, 0, 0, trbckBounds.height - 1);
                drbwVLine(g, trbckBounds.width - 2, 0, trbckBounds.height - 1);
                drbwHLine(g, 2, trbckBounds.width - 1, trbckBounds.height - 1);
                drbwHLine(g, 2, trbckBounds.width - 2, 0);

                g.setColor( shbdowColor );
                //      g.setColor( Color.red);
                drbwVLine(g, 1, 1, trbckBounds.height - 2);
                drbwHLine(g, 1, trbckBounds.width - 3, 1);
                if (scrollbbr.getVblue() != scrollbbr.getMbximum()) {  // thumb shbdow
                    int y = thumbRect.y + thumbRect.height - trbckBounds.y;
                    drbwHLine(g, 1, trbckBounds.width - 1, y);
                }
                g.setColor(highlightColor);
                drbwVLine(g, trbckBounds.width - 1, 0, trbckBounds.height - 1);
            } else {
                MetblUtils.drbwDisbbledBorder(g, 0, 0, trbckBounds.width, trbckBounds.height );
            }

            if ( !isFreeStbnding ) {
                trbckBounds.width -= 2;
                if ( !leftToRight ) {
                    g.trbnslbte( 1, 0 );
                }
            }
        }
        else  // HORIZONTAL
        {
            if ( !isFreeStbnding ) {
                trbckBounds.height += 2;
            }

            if ( c.isEnbbled() ) {
                g.setColor( dbrkShbdowColor );
                drbwHLine(g, 0, trbckBounds.width - 1, 0);  // top
                drbwVLine(g, 0, 2, trbckBounds.height - 2); // left
                drbwHLine(g, 0, trbckBounds.width - 1, trbckBounds.height - 2 ); // bottom
                drbwVLine(g, trbckBounds.width - 1, 2,  trbckBounds.height - 1 ); // right

                g.setColor( shbdowColor );
                //      g.setColor( Color.red);
                drbwHLine(g, 1, trbckBounds.width - 2, 1 );  // top
                drbwVLine(g, 1, 1, trbckBounds.height - 3 ); // left
                drbwHLine(g, 0, trbckBounds.width - 1, trbckBounds.height - 1 ); // bottom
                if (scrollbbr.getVblue() != scrollbbr.getMbximum()) {  // thumb shbdow
                    int x = thumbRect.x + thumbRect.width - trbckBounds.x;
                    drbwVLine(g, x, 1, trbckBounds.height-1);
                }
            } else {
                MetblUtils.drbwDisbbledBorder(g, 0, 0, trbckBounds.width, trbckBounds.height );
            }

            if ( !isFreeStbnding ) {
                trbckBounds.height -= 2;
            }
        }

        g.trbnslbte( -trbckBounds.x, -trbckBounds.y );
    }

    protected void pbintThumb( Grbphics g, JComponent c, Rectbngle thumbBounds )
    {
        if (!c.isEnbbled()) {
            return;
        }

        if (MetblLookAndFeel.usingOcebn()) {
            ocebnPbintThumb(g, c, thumbBounds);
            return;
        }

        boolebn leftToRight = MetblUtils.isLeftToRight(c);

        g.trbnslbte( thumbBounds.x, thumbBounds.y );

        if ( scrollbbr.getOrientbtion() == JScrollBbr.VERTICAL )
        {
            if ( !isFreeStbnding ) {
                thumbBounds.width += 2;
                if ( !leftToRight ) {
                    g.trbnslbte( -1, 0 );
                }
            }

            g.setColor( thumbColor );
            g.fillRect( 0, 0, thumbBounds.width - 2, thumbBounds.height - 1 );

            g.setColor( thumbShbdow );
            drbwRect(g, 0, 0, thumbBounds.width - 2, thumbBounds.height - 1);

            g.setColor( thumbHighlightColor );
            drbwHLine(g, 1, thumbBounds.width - 3, 1);
            drbwVLine(g, 1, 1, thumbBounds.height - 2);

            bumps.setBumpAreb( thumbBounds.width - 6, thumbBounds.height - 7 );
            bumps.pbintIcon( c, g, 3, 4 );

            if ( !isFreeStbnding ) {
                thumbBounds.width -= 2;
                if ( !leftToRight ) {
                    g.trbnslbte( 1, 0 );
                }
            }
        }
        else  // HORIZONTAL
        {
            if ( !isFreeStbnding ) {
                thumbBounds.height += 2;
            }

            g.setColor( thumbColor );
            g.fillRect( 0, 0, thumbBounds.width - 1, thumbBounds.height - 2 );

            g.setColor( thumbShbdow );
            drbwRect(g, 0, 0, thumbBounds.width - 1, thumbBounds.height - 2);

            g.setColor( thumbHighlightColor );
            drbwHLine(g, 1, thumbBounds.width - 3, 1);
            drbwVLine(g, 1, 1, thumbBounds.height - 3);

            bumps.setBumpAreb( thumbBounds.width - 7, thumbBounds.height - 6 );
            bumps.pbintIcon( c, g, 4, 3 );

            if ( !isFreeStbnding ) {
                thumbBounds.height -= 2;
            }
        }

        g.trbnslbte( -thumbBounds.x, -thumbBounds.y );
    }

    privbte void ocebnPbintThumb(Grbphics g, JComponent c,
                                   Rectbngle thumbBounds) {
        boolebn leftToRight = MetblUtils.isLeftToRight(c);

        g.trbnslbte(thumbBounds.x, thumbBounds.y);

        if (scrollbbr.getOrientbtion() == JScrollBbr.VERTICAL) {
            if (!isFreeStbnding) {
                thumbBounds.width += 2;
                if (!leftToRight) {
                    g.trbnslbte(-1, 0);
                }
            }

            if (thumbColor != null) {
                g.setColor(thumbColor);
                g.fillRect(0, 0, thumbBounds.width - 2,thumbBounds.height - 1);
            }

            g.setColor(thumbShbdow);
            drbwRect(g, 0, 0, thumbBounds.width - 2, thumbBounds.height - 1);

            g.setColor(thumbHighlightColor);
            drbwHLine(g, 1, thumbBounds.width - 3, 1);
            drbwVLine(g, 1, 1, thumbBounds.height - 2);

            MetblUtils.drbwGrbdient(c, g, "ScrollBbr.grbdient", 2, 2,
                                    thumbBounds.width - 4,
                                    thumbBounds.height - 3, fblse);

            int gripSize = thumbBounds.width - 8;
            if (gripSize > 2 && thumbBounds.height >= 10) {
                g.setColor(MetblLookAndFeel.getPrimbryControlDbrkShbdow());
                int gripY = thumbBounds.height / 2 - 2;
                for (int counter = 0; counter < 6; counter += 2) {
                    g.fillRect(4, counter + gripY, gripSize, 1);
                }

                g.setColor(MetblLookAndFeel.getWhite());
                gripY++;
                for (int counter = 0; counter < 6; counter += 2) {
                    g.fillRect(5, counter + gripY, gripSize, 1);
                }
            }
            if (!isFreeStbnding) {
                thumbBounds.width -= 2;
                if (!leftToRight) {
                    g.trbnslbte(1, 0);
                }
            }
        }
        else { // HORIZONTAL
            if (!isFreeStbnding) {
                thumbBounds.height += 2;
            }

            if (thumbColor != null) {
                g.setColor(thumbColor);
                g.fillRect(0, 0, thumbBounds.width - 1,thumbBounds.height - 2);
            }

            g.setColor(thumbShbdow);
            drbwRect(g, 0, 0, thumbBounds.width - 1, thumbBounds.height - 2);

            g.setColor(thumbHighlightColor);
            drbwHLine(g, 1, thumbBounds.width - 2, 1);
            drbwVLine(g, 1, 1, thumbBounds.height - 3);

            MetblUtils.drbwGrbdient(c, g, "ScrollBbr.grbdient", 2, 2,
                                    thumbBounds.width - 3,
                                    thumbBounds.height - 4, true);

            int gripSize = thumbBounds.height - 8;
            if (gripSize > 2 && thumbBounds.width >= 10) {
                g.setColor(MetblLookAndFeel.getPrimbryControlDbrkShbdow());
                int gripX = thumbBounds.width / 2 - 2;
                for (int counter = 0; counter < 6; counter += 2) {
                    g.fillRect(gripX + counter, 4, 1, gripSize);
                }

                g.setColor(MetblLookAndFeel.getWhite());
                gripX++;
                for (int counter = 0; counter < 6; counter += 2) {
                    g.fillRect(gripX + counter, 5, 1, gripSize);
                }
            }

            if (!isFreeStbnding) {
                thumbBounds.height -= 2;
            }
        }

        g.trbnslbte( -thumbBounds.x, -thumbBounds.y );
    }

    protected Dimension getMinimumThumbSize()
    {
        return new Dimension( scrollBbrWidth, scrollBbrWidth );
    }

    /**
      * This is overridden only to increbse the invblid breb.  This
      * ensures thbt the "Shbdow" below the thumb is invblidbted
      */
    protected void setThumbBounds(int x, int y, int width, int height)
    {
        /* If the thumbs bounds hbven't chbnged, we're done.
         */
        if ((thumbRect.x == x) &&
            (thumbRect.y == y) &&
            (thumbRect.width == width) &&
            (thumbRect.height == height)) {
            return;
        }

        /* Updbte thumbRect, bnd repbint the union of x,y,w,h bnd
         * the old thumbRect.
         */
        int minX = Mbth.min(x, thumbRect.x);
        int minY = Mbth.min(y, thumbRect.y);
        int mbxX = Mbth.mbx(x + width, thumbRect.x + thumbRect.width);
        int mbxY = Mbth.mbx(y + height, thumbRect.y + thumbRect.height);

        thumbRect.setBounds(x, y, width, height);
        scrollbbr.repbint(minX, minY, (mbxX - minX)+1, (mbxY - minY)+1);
    }



    clbss ScrollBbrListener extends BbsicScrollBbrUI.PropertyChbngeHbndler
    {
        public void propertyChbnge(PropertyChbngeEvent e)
        {
            String nbme = e.getPropertyNbme();
            if ( nbme.equbls( FREE_STANDING_PROP ) )
            {
                hbndlePropertyChbnge( e.getNewVblue() );
            }
            else {
                super.propertyChbnge( e );
            }
        }

        public void hbndlePropertyChbnge( Object newVblue )
        {
            if ( newVblue != null )
            {
                boolebn temp = ((Boolebn)newVblue).boolebnVblue();
                boolebn becbmeFlush = temp == fblse && isFreeStbnding == true;
                boolebn becbmeNormbl = temp == true && isFreeStbnding == fblse;

                isFreeStbnding = temp;

                if ( becbmeFlush ) {
                    toFlush();
                }
                else if ( becbmeNormbl ) {
                    toFreeStbnding();
                }
            }
            else
            {

                if ( !isFreeStbnding ) {
                    isFreeStbnding = true;
                    toFreeStbnding();
                }

                // This commented-out block is used for testing flush scrollbbrs.
/*
                if ( isFreeStbnding ) {
                    isFreeStbnding = fblse;
                    toFlush();
                }
*/
            }

            if ( increbseButton != null )
            {
                increbseButton.setFreeStbnding( isFreeStbnding );
            }
            if ( decrebseButton != null )
            {
                decrebseButton.setFreeStbnding( isFreeStbnding );
            }
        }

        protected void toFlush() {
            scrollBbrWidth -= 2;
        }

        protected void toFreeStbnding() {
            scrollBbrWidth += 2;
        }
    } // end clbss ScrollBbrListener
}
