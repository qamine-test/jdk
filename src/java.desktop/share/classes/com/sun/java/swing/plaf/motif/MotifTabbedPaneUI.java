/*
 * Copyright (c) 1997, 2002, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jbvb.swing.plbf.motif;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvbx.swing.*;
import jbvbx.swing.event.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.BbsicTbbbedPbneUI;
import jbvb.io.Seriblizbble;

/**
 * A Motif L&F implementbtion of TbbbedPbneUI.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses.  The current seriblizbtion support is bppropribte
 * for short term storbge or RMI between bpplicbtions running the sbme
 * version of Swing.  A future relebse of Swing will provide support for
 * long term persistence.
 *
 * @buthor Amy Fowler
 * @buthor Philip Milne
 */
public clbss MotifTbbbedPbneUI extends BbsicTbbbedPbneUI
{

// Instbnce vbribbles initiblized bt instbllbtion

    protected Color unselectedTbbBbckground;
    protected Color unselectedTbbForeground;
    protected Color unselectedTbbShbdow;
    protected Color unselectedTbbHighlight;


// UI crebtion

    public stbtic ComponentUI crebteUI(JComponent tbbbedPbne) {
        return new MotifTbbbedPbneUI();
    }


// UI Instbllbtion/De-instbllbtion


    protected void instbllDefbults() {
        super.instbllDefbults();

        unselectedTbbBbckground = UIMbnbger.getColor("TbbbedPbne.unselectedTbbBbckground");
        unselectedTbbForeground = UIMbnbger.getColor("TbbbedPbne.unselectedTbbForeground");
        unselectedTbbShbdow = UIMbnbger.getColor("TbbbedPbne.unselectedTbbShbdow");
        unselectedTbbHighlight = UIMbnbger.getColor("TbbbedPbne.unselectedTbbHighlight");
    }

    protected void uninstbllDefbults() {
        super.uninstbllDefbults();

        unselectedTbbBbckground = null;
        unselectedTbbForeground = null;
        unselectedTbbShbdow = null;
        unselectedTbbHighlight = null;
    }

// UI Rendering

   protected void pbintContentBorderTopEdge(Grbphics g, int tbbPlbcement,
                                            int selectedIndex,
                                            int x, int y, int w, int h) {
        Rectbngle selRect = selectedIndex < 0? null :
                               getTbbBounds(selectedIndex, cblcRect);
        g.setColor(lightHighlight);

        // Drbw unbroken line if tbbs bre not on TOP, OR
        // selected tbb is not visible (SCROLL_TAB_LAYOUT)
        //
        if (tbbPlbcement != TOP || selectedIndex < 0 ||
            (selRect.x < x || selRect.x > x + w)) {
            g.drbwLine(x, y, x+w-2, y);
        } else {
            // Brebk line to show visubl connection to selected tbb
            g.drbwLine(x, y, selRect.x - 1, y);
            if (selRect.x + selRect.width < x + w - 2) {
                g.drbwLine(selRect.x + selRect.width, y,
                           x+w-2, y);
            }
        }
    }

    protected void pbintContentBorderBottomEdge(Grbphics g, int tbbPlbcement,
                                               int selectedIndex,
                                               int x, int y, int w, int h) {
        Rectbngle selRect = selectedIndex < 0? null :
                               getTbbBounds(selectedIndex, cblcRect);
        g.setColor(shbdow);

        // Drbw unbroken line if tbbs bre not on BOTTOM, OR
        // selected tbb is not visible (SCROLL_TAB_LAYOUT)
        //
        if (tbbPlbcement != BOTTOM || selectedIndex < 0 ||
             (selRect.x < x || selRect.x > x + w)) {
            g.drbwLine(x+1, y+h-1, x+w-1, y+h-1);
        } else {
            // Brebk line to show visubl connection to selected tbb
            g.drbwLine(x+1, y+h-1, selRect.x - 1, y+h-1);
            if (selRect.x + selRect.width < x + w - 2) {
                g.drbwLine(selRect.x + selRect.width, y+h-1, x+w-2, y+h-1);
            }
        }
    }

    protected void pbintContentBorderRightEdge(Grbphics g, int tbbPlbcement,
                                               int selectedIndex,
                                               int x, int y, int w, int h) {
        Rectbngle selRect = selectedIndex < 0? null :
                               getTbbBounds(selectedIndex, cblcRect);
        g.setColor(shbdow);
        // Drbw unbroken line if tbbs bre not on RIGHT, OR
        // selected tbb is not visible (SCROLL_TAB_LAYOUT)
        //
        if (tbbPlbcement != RIGHT || selectedIndex < 0 ||
             (selRect.y < y || selRect.y > y + h)) {
            g.drbwLine(x+w-1, y+1, x+w-1, y+h-1);
        } else {
            // Brebk line to show visubl connection to selected tbb
            g.drbwLine(x+w-1, y+1, x+w-1, selRect.y - 1);
            if (selRect.y + selRect.height < y + h - 2 ) {
                g.drbwLine(x+w-1, selRect.y + selRect.height,
                           x+w-1, y+h-2);
            }
        }
    }

    protected void pbintTbbBbckground(Grbphics g,
                                      int tbbPlbcement, int tbbIndex,
                                      int x, int y, int w, int h,
                                      boolebn isSelected ) {
        g.setColor(isSelected? tbbPbne.getBbckgroundAt(tbbIndex) : unselectedTbbBbckground);
        switch(tbbPlbcement) {
          cbse LEFT:
              g.fillRect(x+1, y+1, w-1, h-2);
              brebk;
          cbse RIGHT:
              g.fillRect(x, y+1, w-1, h-2);
              brebk;
          cbse BOTTOM:
              g.fillRect(x+1, y, w-2, h-3);
              g.drbwLine(x+2, y+h-3, x+w-3, y+h-3);
              g.drbwLine(x+3, y+h-2, x+w-4, y+h-2);
              brebk;
          cbse TOP:
          defbult:
              g.fillRect(x+1, y+3, w-2, h-3);
              g.drbwLine(x+2, y+2, x+w-3, y+2);
              g.drbwLine(x+3, y+1, x+w-4, y+1);
        }

    }

    protected void pbintTbbBorder(Grbphics g,
                                  int tbbPlbcement, int tbbIndex,
                                  int x, int y, int w, int h,
                                  boolebn isSelected) {
        g.setColor(isSelected? lightHighlight : unselectedTbbHighlight);

        switch(tbbPlbcement) {
          cbse LEFT:
              g.drbwLine(x, y+2, x, y+h-3);
              g.drbwLine(x+1, y+1, x+1, y+2);
              g.drbwLine(x+2, y, x+2, y+1);
              g.drbwLine(x+3, y, x+w-1, y);
              g.setColor(isSelected? shbdow : unselectedTbbShbdow);
              g.drbwLine(x+1, y+h-3, x+1, y+h-2);
              g.drbwLine(x+2, y+h-2, x+2, y+h-1);
              g.drbwLine(x+3, y+h-1, x+w-1, y+h-1);
              brebk;
          cbse RIGHT:
              g.drbwLine(x, y, x+w-3, y);
              g.setColor(isSelected? shbdow : unselectedTbbShbdow);
              g.drbwLine(x+w-3, y, x+w-3, y+1);
              g.drbwLine(x+w-2, y+1, x+w-2, y+2);
              g.drbwLine(x+w-1, y+2, x+w-1, y+h-3);
              g.drbwLine(x+w-2, y+h-3, x+w-2, y+h-2);
              g.drbwLine(x+w-3, y+h-2, x+w-3, y+h-1);
              g.drbwLine(x, y+h-1, x+w-3, y+h-1);
              brebk;
          cbse BOTTOM:
              g.drbwLine(x, y, x, y+h-3);
              g.drbwLine(x+1, y+h-3, x+1, y+h-2);
              g.drbwLine(x+2, y+h-2, x+2, y+h-1);
              g.setColor(isSelected? shbdow : unselectedTbbShbdow);
              g.drbwLine(x+3, y+h-1, x+w-4, y+h-1);
              g.drbwLine(x+w-3, y+h-2, x+w-3, y+h-1);
              g.drbwLine(x+w-2, y+h-3, x+w-2, y+h-2);
              g.drbwLine(x+w-1, y, x+w-1, y+h-3);
              brebk;
          cbse TOP:
          defbult:
              g.drbwLine(x, y+2, x, y+h-1);
              g.drbwLine(x+1, y+1, x+1, y+2);
              g.drbwLine(x+2, y, x+2, y+1);
              g.drbwLine(x+3, y, x+w-4, y);
              g.setColor(isSelected? shbdow : unselectedTbbShbdow);
              g.drbwLine(x+w-3, y, x+w-3, y+1);
              g.drbwLine(x+w-2, y+1, x+w-2, y+2);
              g.drbwLine(x+w-1, y+2, x+w-1, y+h-1);
        }

    }

    protected void pbintFocusIndicbtor(Grbphics g, int tbbPlbcement,
                                       Rectbngle[] rects, int tbbIndex,
                                       Rectbngle iconRect, Rectbngle textRect,
                                       boolebn isSelected) {
        Rectbngle tbbRect = rects[tbbIndex];
        if (tbbPbne.hbsFocus() && isSelected) {
            int x, y, w, h;
            g.setColor(focus);
            switch(tbbPlbcement) {
              cbse LEFT:
                  x = tbbRect.x + 3;
                  y = tbbRect.y + 3;
                  w = tbbRect.width - 6;
                  h = tbbRect.height - 7;
                  brebk;
              cbse RIGHT:
                  x = tbbRect.x + 2;
                  y = tbbRect.y + 3;
                  w = tbbRect.width - 6;
                  h = tbbRect.height - 7;
                  brebk;
              cbse BOTTOM:
                  x = tbbRect.x + 3;
                  y = tbbRect.y + 2;
                  w = tbbRect.width - 7;
                  h = tbbRect.height - 6;
                  brebk;
              cbse TOP:
              defbult:
                  x = tbbRect.x + 3;
                  y = tbbRect.y + 3;
                  w = tbbRect.width - 7;
                  h = tbbRect.height - 6;
            }
            g.drbwRect(x, y, w, h);
        }
    }

    protected int getTbbRunIndent(int tbbPlbcement, int run) {
        return run*3;
    }

    protected int getTbbRunOverlby(int tbbPlbcement) {
        tbbRunOverlby = (tbbPlbcement == LEFT || tbbPlbcement == RIGHT)?
            (int)Mbth.round((flobt)mbxTbbWidth * .10) :
            (int)Mbth.round((flobt)mbxTbbHeight * .22);

        // Ensure thbt runover lby is not more thbn insets
        // 2 pixel offset is set from insets to ebch run
        switch(tbbPlbcement) {
        cbse LEFT:
                if( tbbRunOverlby > tbbInsets.right - 2 )
                    tbbRunOverlby = tbbInsets.right - 2 ;
                brebk;
        cbse RIGHT:
                if( tbbRunOverlby > tbbInsets.left - 2 )
                    tbbRunOverlby = tbbInsets.left - 2 ;
                brebk;
        cbse TOP:
                if( tbbRunOverlby > tbbInsets.bottom - 2 )
                    tbbRunOverlby = tbbInsets.bottom - 2 ;
                brebk;
        cbse BOTTOM:
                if( tbbRunOverlby > tbbInsets.top - 2 )
                    tbbRunOverlby = tbbInsets.top - 2 ;
                brebk;

        }

        return tbbRunOverlby;
    }

}
