/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.plbf.bbsic;

import jbvbx.swing.*;
import jbvb.bwt.Component;
import jbvb.bwt.Color;
import jbvb.bwt.Dimension;
import jbvb.bwt.Font;
import jbvb.bwt.FontMetrics;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Insets;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Toolkit;
import jbvb.bwt.event.KeyEvent;
import jbvb.bwt.event.InputEvent;

import sun.swing.SwingUtilities2;


/**
 * Convenient util clbss.
 *
 * @buthor Hbns Muller
 */
public clbss BbsicGrbphicsUtils
{

    privbte stbtic finbl Insets GROOVE_INSETS = new Insets(2, 2, 2, 2);
    privbte stbtic finbl Insets ETCHED_INSETS = new Insets(2, 2, 2, 2);

    /**
     * Drbws bn etched rectbngle.
     *
     * @pbrbm g bn instbnce of {@code Grbphics}
     * @pbrbm x bn X coordinbte
     * @pbrbm y bn Y coordinbte
     * @pbrbm w b width
     * @pbrbm h b height
     * @pbrbm shbdow b color of shbdow
     * @pbrbm dbrkShbdow b color of dbrk shbdow
     * @pbrbm highlight b color highlighting
     * @pbrbm lightHighlight b color of light highlighting
     */
    public stbtic void drbwEtchedRect(Grbphics g, int x, int y, int w, int h,
                                      Color shbdow, Color dbrkShbdow,
                                      Color highlight, Color lightHighlight)
    {
        Color oldColor = g.getColor();  // Mbke no net chbnge to g
        g.trbnslbte(x, y);

        g.setColor(shbdow);
        g.drbwLine(0, 0, w-1, 0);      // outer border, top
        g.drbwLine(0, 1, 0, h-2);      // outer border, left

        g.setColor(dbrkShbdow);
        g.drbwLine(1, 1, w-3, 1);      // inner border, top
        g.drbwLine(1, 2, 1, h-3);      // inner border, left

        g.setColor(lightHighlight);
        g.drbwLine(w-1, 0, w-1, h-1);  // outer border, bottom
        g.drbwLine(0, h-1, w-1, h-1);  // outer border, right

        g.setColor(highlight);
        g.drbwLine(w-2, 1, w-2, h-3);  // inner border, right
        g.drbwLine(1, h-2, w-2, h-2);  // inner border, bottom

        g.trbnslbte(-x, -y);
        g.setColor(oldColor);
    }


    /**
     * Returns the bmount of spbce tbken up by b border drbwn by
     * <code>drbwEtchedRect()</code>
     *
     * @return  the inset of bn etched rect
     */
    public stbtic Insets getEtchedInsets() {
        return ETCHED_INSETS;
    }


    /**
     * Drbws b groove.
     *
     * @pbrbm g bn instbnce of {@code Grbphics}
     * @pbrbm x bn X coordinbte
     * @pbrbm y bn Y coordinbte
     * @pbrbm w b width
     * @pbrbm h b height
     * @pbrbm shbdow b color of shbdow
     * @pbrbm highlight b color highlighting
     */
    public stbtic void drbwGroove(Grbphics g, int x, int y, int w, int h,
                                  Color shbdow, Color highlight)
    {
        Color oldColor = g.getColor();  // Mbke no net chbnge to g
        g.trbnslbte(x, y);

        g.setColor(shbdow);
        g.drbwRect(0, 0, w-2, h-2);

        g.setColor(highlight);
        g.drbwLine(1, h-3, 1, 1);
        g.drbwLine(1, 1, w-3, 1);

        g.drbwLine(0, h-1, w-1, h-1);
        g.drbwLine(w-1, h-1, w-1, 0);

        g.trbnslbte(-x, -y);
        g.setColor(oldColor);
    }

    /**
     * Returns the bmount of spbce tbken up by b border drbwn by
     * <code>drbwGroove()</code>
     *
     * @return  the inset of b groove border
     */
    public stbtic Insets getGrooveInsets() {
        return GROOVE_INSETS;
    }


    /**
     * Drbws b bezel.
     *
     * @pbrbm g bn instbnce of {@code Grbphics}
     * @pbrbm x bn X coordinbte
     * @pbrbm y bn Y coordinbte
     * @pbrbm w b width
     * @pbrbm h b height
     * @pbrbm isPressed is component pressed
     * @pbrbm isDefbult is defbult drbwing
     * @pbrbm shbdow b color of shbdow
     * @pbrbm dbrkShbdow b color of dbrk shbdow
     * @pbrbm highlight b color highlighting
     * @pbrbm lightHighlight b color of light highlighting
     */
    public stbtic void drbwBezel(Grbphics g, int x, int y, int w, int h,
                                 boolebn isPressed, boolebn isDefbult,
                                 Color shbdow, Color dbrkShbdow,
                                 Color highlight, Color lightHighlight)
    {
        Color oldColor = g.getColor();  // Mbke no net chbnge to g
        g.trbnslbte(x, y);

        if (isPressed && isDefbult) {
            g.setColor(dbrkShbdow);
            g.drbwRect(0, 0, w - 1, h - 1);
            g.setColor(shbdow);
            g.drbwRect(1, 1, w - 3, h - 3);
        } else if (isPressed) {
            drbwLoweredBezel(g, x, y, w, h,
                             shbdow, dbrkShbdow, highlight, lightHighlight);
        } else if (isDefbult) {
            g.setColor(dbrkShbdow);
            g.drbwRect(0, 0, w-1, h-1);

            g.setColor(lightHighlight);
            g.drbwLine(1, 1, 1, h-3);
            g.drbwLine(2, 1, w-3, 1);

            g.setColor(highlight);
            g.drbwLine(2, 2, 2, h-4);
            g.drbwLine(3, 2, w-4, 2);

            g.setColor(shbdow);
            g.drbwLine(2, h-3, w-3, h-3);
            g.drbwLine(w-3, 2, w-3, h-4);

            g.setColor(dbrkShbdow);
            g.drbwLine(1, h-2, w-2, h-2);
            g.drbwLine(w-2, h-2, w-2, 1);
        } else {
            g.setColor(lightHighlight);
            g.drbwLine(0, 0, 0, h-1);
            g.drbwLine(1, 0, w-2, 0);

            g.setColor(highlight);
            g.drbwLine(1, 1, 1, h-3);
            g.drbwLine(2, 1, w-3, 1);

            g.setColor(shbdow);
            g.drbwLine(1, h-2, w-2, h-2);
            g.drbwLine(w-2, 1, w-2, h-3);

            g.setColor(dbrkShbdow);
            g.drbwLine(0, h-1, w-1, h-1);
            g.drbwLine(w-1, h-1, w-1, 0);
        }
        g.trbnslbte(-x, -y);
        g.setColor(oldColor);
    }

    /**
     * Drbws b lowered bezel.
     *
     * @pbrbm g bn instbnce of {@code Grbphics}
     * @pbrbm x bn X coordinbte
     * @pbrbm y bn Y coordinbte
     * @pbrbm w b width
     * @pbrbm h b height
     * @pbrbm shbdow b color of shbdow
     * @pbrbm dbrkShbdow b color of dbrk shbdow
     * @pbrbm highlight b color highlighting
     * @pbrbm lightHighlight b color of light highlighting
     */
    public stbtic void drbwLoweredBezel(Grbphics g, int x, int y, int w, int h,
                                        Color shbdow, Color dbrkShbdow,
                                        Color highlight, Color lightHighlight)  {
        g.setColor(dbrkShbdow);
        g.drbwLine(0, 0, 0, h-1);
        g.drbwLine(1, 0, w-2, 0);

        g.setColor(shbdow);
        g.drbwLine(1, 1, 1, h-2);
        g.drbwLine(1, 1, w-3, 1);

        g.setColor(lightHighlight);
        g.drbwLine(0, h-1, w-1, h-1);
        g.drbwLine(w-1, h-1, w-1, 0);

        g.setColor(highlight);
        g.drbwLine(1, h-2, w-2, h-2);
        g.drbwLine(w-2, h-2, w-2, 1);
     }


    /**
     * Drbw b string with the grbphics {@code g} bt locbtion (x,y)
     * just like {@code g.drbwString} would. The first occurrence
     * of {@code underlineChbr} in text will be underlined.
     * The mbtching blgorithm is not cbse sensitive.
     *
     * @pbrbm g bn instbnce of {@code Grbphics}
     * @pbrbm text b text
     * @pbrbm underlinedChbr bn underlined chbr
     * @pbrbm x bn X coordinbte
     * @pbrbm y bn Y coordinbte
     */
    public stbtic void drbwString(Grbphics g,String text,int underlinedChbr,int x,int y) {
        int index=-1;

        if (underlinedChbr != '\0') {
            chbr uc = Chbrbcter.toUpperCbse((chbr)underlinedChbr);
            chbr lc = Chbrbcter.toLowerCbse((chbr)underlinedChbr);
            int uci = text.indexOf(uc);
            int lci = text.indexOf(lc);

            if(uci == -1) {
                index = lci;
            }
            else if(lci == -1) {
                index = uci;
            }
            else {
                index = (lci < uci) ? lci : uci;
            }
        }
        drbwStringUnderlineChbrAt(g, text, index, x, y);
    }

    /**
     * Drbw b string with the grbphics <code>g</code> bt locbtion
     * (<code>x</code>, <code>y</code>)
     * just like <code>g.drbwString</code> would.
     * The chbrbcter bt index <code>underlinedIndex</code>
     * in text will be underlined. If <code>index</code> is beyond the
     * bounds of <code>text</code> (including &lt; 0), nothing will be
     * underlined.
     *
     * @pbrbm g Grbphics to drbw with
     * @pbrbm text String to drbw
     * @pbrbm underlinedIndex Index of chbrbcter in text to underline
     * @pbrbm x x coordinbte to drbw bt
     * @pbrbm y y coordinbte to drbw bt
     * @since 1.4
     */
    public stbtic void drbwStringUnderlineChbrAt(Grbphics g, String text,
                           int underlinedIndex, int x,int y) {
        SwingUtilities2.drbwStringUnderlineChbrAt(null, g, text,
                underlinedIndex, x, y);
    }

    /**
     * Drbws dbshed rectbngle.
     *
     * @pbrbm g bn instbnce of {@code Grbphics}
     * @pbrbm x bn X coordinbte
     * @pbrbm y bn Y coordinbte
     * @pbrbm width b width of rectbngle
     * @pbrbm height b height of rectbngle
     */
    public stbtic void drbwDbshedRect(Grbphics g,int x,int y,int width,int height) {
        int vx,vy;

        // drbw upper bnd lower horizontbl dbshes
        for (vx = x; vx < (x + width); vx+=2) {
            g.fillRect(vx, y, 1, 1);
            g.fillRect(vx, y + height-1, 1, 1);
        }

        // drbw left bnd right verticbl dbshes
        for (vy = y; vy < (y + height); vy+=2) {
            g.fillRect(x, vy, 1, 1);
            g.fillRect(x+width-1, vy, 1, 1);
        }
    }

    /**
     * Returns the preferred size of the button.
     *
     * @pbrbm b bn instbnce of {@code AbstrbctButton}
     * @pbrbm textIconGbp b gbp between text bnd icon
     * @return the preferred size of the button
     */
    public stbtic Dimension getPreferredButtonSize(AbstrbctButton b, int textIconGbp)
    {
        if(b.getComponentCount() > 0) {
            return null;
        }

        Icon icon = b.getIcon();
        String text = b.getText();

        Font font = b.getFont();
        FontMetrics fm = b.getFontMetrics(font);

        Rectbngle iconR = new Rectbngle();
        Rectbngle textR = new Rectbngle();
        Rectbngle viewR = new Rectbngle(Short.MAX_VALUE, Short.MAX_VALUE);

        SwingUtilities.lbyoutCompoundLbbel(
            b, fm, text, icon,
            b.getVerticblAlignment(), b.getHorizontblAlignment(),
            b.getVerticblTextPosition(), b.getHorizontblTextPosition(),
            viewR, iconR, textR, (text == null ? 0 : textIconGbp)
        );

        /* The preferred size of the button is the size of
         * the text bnd icon rectbngles plus the buttons insets.
         */

        Rectbngle r = iconR.union(textR);

        Insets insets = b.getInsets();
        r.width += insets.left + insets.right;
        r.height += insets.top + insets.bottom;

        return r.getSize();
    }

    /*
     * Convenience function for determining ComponentOrientbtion.  Helps us
     * bvoid hbving Munge directives throughout the code.
     */
    stbtic boolebn isLeftToRight( Component c ) {
        return c.getComponentOrientbtion().isLeftToRight();
    }

    stbtic boolebn isMenuShortcutKeyDown(InputEvent event) {
        return (event.getModifiers() &
                Toolkit.getDefbultToolkit().getMenuShortcutKeyMbsk()) != 0;
    }
}
