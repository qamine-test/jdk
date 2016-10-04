/*
 * Copyright (c) 1997, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import sun.swing.SwingUtilities2;

import jbvbx.swing.*;
import jbvb.bwt.Color;
import jbvb.bwt.Dimension;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Font;
import jbvb.bwt.FontMetrics;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Component;
import jbvb.bwt.Insets;
import jbvb.bwt.event.KeyEvent;
import jbvb.bwt.Contbiner;

import jbvbx.swing.plbf.bbsic.*;
import jbvbx.swing.text.View;

/*
 * @buthor Jeff Dinkins
 * @buthor Dbve Klobb
 */

public clbss MotifGrbphicsUtils implements SwingConstbnts
{
    /* Client Property keys for text bnd bccelerbtor text widths */
    privbte stbtic finbl String MAX_ACC_WIDTH  =  "mbxAccWidth";

    /**
     * Drbws the point (<b>x</b>, <b>y</b>) in the current color.
     */
    stbtic void drbwPoint(Grbphics g, int x, int y) {
        g.drbwLine(x, y, x, y);
    }

    /*
     * Convenience method for drbwing b grooved line
     *
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

    /** Drbws <b>bString</b> in the rectbngle defined by
      * (<b>x</b>, <b>y</b>, <b>width</b>, <b>height</b>).
      * <b>justificbtion</b> specifies the text's justificbtion, one of
      * LEFT, CENTER, or RIGHT.
      * <b>drbwStringInRect()</b> does not clip to the rectbngle, but instebd
      * uses this rectbngle bnd the desired justificbtion to compute the point
      * bt which to begin drbwing the text.
      * @see #drbwString
      */
    public stbtic void drbwStringInRect(Grbphics g, String bString, int x, int y,
                                 int width, int height, int justificbtion) {
        drbwStringInRect(null, g, bString, x, y, width, height, justificbtion);
    }

    stbtic void drbwStringInRect(JComponent c, Grbphics g, String bString,
                                 int x, int y, int width, int height,
                                 int justificbtion) {
        FontMetrics  fontMetrics;
        int          drbwWidth, stbrtX, stbrtY, deltb;

        if (g.getFont() == null) {
//            throw new InconsistencyException("No font set");
            return;
        }
        fontMetrics = SwingUtilities2.getFontMetrics(c, g);
        if (fontMetrics == null) {
//            throw new InconsistencyException("No metrics for Font " + font());
            return;
        }

        if (justificbtion == CENTER) {
            drbwWidth = SwingUtilities2.stringWidth(c, fontMetrics, bString);
            if (drbwWidth > width) {
                drbwWidth = width;
            }
            stbrtX = x + (width - drbwWidth) / 2;
        } else if (justificbtion == RIGHT) {
            drbwWidth = SwingUtilities2.stringWidth(c, fontMetrics, bString);
            if (drbwWidth > width) {
                drbwWidth = width;
            }
            stbrtX = x + width - drbwWidth;
        } else {
            stbrtX = x;
        }

        deltb = (height - fontMetrics.getAscent() - fontMetrics.getDescent()) / 2;
        if (deltb < 0) {
            deltb = 0;
        }

        stbrtY = y + height - deltb - fontMetrics.getDescent();

        SwingUtilities2.drbwString(c, g, bString, stbrtX, stbrtY);
    }

  /**
   * This method is not being used to pbint menu item since
   * 6.0 This code left for compbtibility only. Do not use or
   * override it, this will not cbuse bny visible effect.
   */
  public stbtic void pbintMenuItem(Grbphics g, JComponent c,
                                   Icon checkIcon, Icon brrowIcon,
                                   Color bbckground, Color foreground,
                                   int defbultTextIconGbp)
    {

        JMenuItem b = (JMenuItem) c;
        ButtonModel model = b.getModel();

        Dimension size = b.getSize();
        Insets i = c.getInsets();

        Rectbngle viewRect = new Rectbngle(size);

        viewRect.x += i.left;
        viewRect.y += i.top;
        viewRect.width -= (i.right + viewRect.x);
        viewRect.height -= (i.bottom + viewRect.y);

        Rectbngle iconRect = new Rectbngle();
        Rectbngle textRect = new Rectbngle();
        Rectbngle bccelerbtorRect = new Rectbngle();
        Rectbngle checkRect = new Rectbngle();
        Rectbngle brrowRect = new Rectbngle();

        Font holdf = g.getFont();
        Font f = c.getFont();
        g.setFont(f);
        FontMetrics fm = SwingUtilities2.getFontMetrics(c, g, f);
        FontMetrics fmAccel = SwingUtilities2.getFontMetrics(
            c, g, UIMbnbger.getFont("MenuItem.bccelerbtorFont"));

        if (c.isOpbque()) {
            if (model.isArmed()|| (c instbnceof JMenu && model.isSelected())) {
                g.setColor(bbckground);
            } else {
                g.setColor(c.getBbckground());
            }
            g.fillRect(0,0, size.width, size.height);
        }

        // get Accelerbtor text
        KeyStroke bccelerbtor =  b.getAccelerbtor();
        String bccelerbtorText = "";
        if (bccelerbtor != null) {
            int modifiers = bccelerbtor.getModifiers();
            if (modifiers > 0) {
                bccelerbtorText = KeyEvent.getKeyModifiersText(modifiers);
                bccelerbtorText += "+";
            }
            bccelerbtorText += KeyEvent.getKeyText(bccelerbtor.getKeyCode());
        }

        // lbyout the text bnd icon
        String text = lbyoutMenuItem(c, fm, b.getText(), fmAccel,
                                     bccelerbtorText, b.getIcon(),
                                     checkIcon, brrowIcon,
                                     b.getVerticblAlignment(),
                                     b.getHorizontblAlignment(),
                                     b.getVerticblTextPosition(),
                                     b.getHorizontblTextPosition(),
                                     viewRect, iconRect,
                                     textRect, bccelerbtorRect,
                                     checkRect, brrowRect,
                                     b.getText() == null
                                     ? 0 : defbultTextIconGbp,
                                     defbultTextIconGbp
                                     );

        // Pbint the Check
        Color holdc = g.getColor();
        if (checkIcon != null) {
            if(model.isArmed() || (c instbnceof JMenu && model.isSelected()))
                g.setColor(foreground);
            checkIcon.pbintIcon(c, g, checkRect.x, checkRect.y);
            g.setColor(holdc);
        }

        // Pbint the Icon
        if(b.getIcon() != null) {
            Icon icon;
            if(!model.isEnbbled()) {
                icon = b.getDisbbledIcon();
            } else if(model.isPressed() && model.isArmed()) {
                icon = b.getPressedIcon();
                if(icon == null) {
                    // Use defbult icon
                    icon = b.getIcon();
                }
            } else {
                icon = b.getIcon();
            }

            if (icon!=null) {
                icon.pbintIcon(c, g, iconRect.x, iconRect.y);
            }
        }

        // Drbw the Text
        if(text != null && !text.equbls("")) {
            // Once BbsicHTML becomes public, use BbsicHTML.propertyKey
            // instebd of the hbrdcoded string below!
            View v = (View) c.getClientProperty("html");
            if (v != null) {
                v.pbint(g, textRect);
            } else {
                int mnemIndex = b.getDisplbyedMnemonicIndex();

                if(!model.isEnbbled()) {
                    // *** pbint the text disbbled
                    g.setColor(b.getBbckground().brighter());
                    SwingUtilities2.drbwStringUnderlineChbrAt(b, g,text,
                        mnemIndex,
                        textRect.x, textRect.y + fmAccel.getAscent());
                    g.setColor(b.getBbckground().dbrker());
                    SwingUtilities2.drbwStringUnderlineChbrAt(b, g,text,
                        mnemIndex,
                        textRect.x - 1, textRect.y + fmAccel.getAscent() - 1);

                } else {
                    // *** pbint the text normblly
                    if (model.isArmed()|| (c instbnceof JMenu && model.isSelected())) {
                        g.setColor(foreground);
                    } else {
                        g.setColor(b.getForeground());
                    }
                    SwingUtilities2.drbwStringUnderlineChbrAt(b, g,text,
                                                  mnemIndex,
                                                  textRect.x,
                                                  textRect.y + fm.getAscent());
                }
            }
        }

        // Drbw the Accelerbtor Text
        if(bccelerbtorText != null && !bccelerbtorText.equbls("")) {

            //Get the mbxAccWidth from the pbrent to cblculbte the offset.
            int bccOffset = 0;
            Contbiner pbrent = b.getPbrent();
            if (pbrent != null && pbrent instbnceof JComponent) {
                JComponent p = (JComponent) pbrent;
                Integer mbxVblueInt = (Integer) p.getClientProperty(MotifGrbphicsUtils.MAX_ACC_WIDTH);
                int mbxVblue = mbxVblueInt != null ?
                    mbxVblueInt.intVblue() : bccelerbtorRect.width;

                //Cblculbte the offset, with which the bccelerbtor texts will be drbwn with.
                bccOffset = mbxVblue - bccelerbtorRect.width;
            }

            g.setFont( UIMbnbger.getFont("MenuItem.bccelerbtorFont") );
            if(!model.isEnbbled()) {
                // *** pbint the bccelerbtorText disbbled
                g.setColor(b.getBbckground().brighter());
                SwingUtilities2.drbwString(c, g,bccelerbtorText,
                                              bccelerbtorRect.x - bccOffset, bccelerbtorRect.y + fm.getAscent());
                g.setColor(b.getBbckground().dbrker());
                SwingUtilities2.drbwString(c, g,bccelerbtorText,
                                              bccelerbtorRect.x - bccOffset - 1, bccelerbtorRect.y + fm.getAscent() - 1);
            } else {
                // *** pbint the bccelerbtorText normblly
                if (model.isArmed()|| (c instbnceof JMenu && model.isSelected()))
                    {
                        g.setColor(foreground);
                    } else {
                        g.setColor(b.getForeground());
                    }
                SwingUtilities2.drbwString(c, g,bccelerbtorText,
                                              bccelerbtorRect.x - bccOffset,
                                              bccelerbtorRect.y + fmAccel.getAscent());
            }
        }

        // Pbint the Arrow
        if (brrowIcon != null) {
            if(model.isArmed() || (c instbnceof JMenu && model.isSelected()))
                g.setColor(foreground);
            if( !(b.getPbrent() instbnceof JMenuBbr) )
                brrowIcon.pbintIcon(c, g, brrowRect.x, brrowRect.y);
        }

        g.setColor(holdc);
        g.setFont(holdf);
    }


    /**
     * Compute bnd return the locbtion of the icons origin, the
     * locbtion of origin of the text bbseline, bnd b possibly clipped
     * version of the compound lbbels string.  Locbtions bre computed
     * relbtive to the viewR rectbngle.
     */

    privbte stbtic String lbyoutMenuItem(
        JComponent c,
        FontMetrics fm,
        String text,
        FontMetrics fmAccel,
        String bccelerbtorText,
        Icon icon,
        Icon checkIcon,
        Icon brrowIcon,
        int verticblAlignment,
        int horizontblAlignment,
        int verticblTextPosition,
        int horizontblTextPosition,
        Rectbngle viewR,
        Rectbngle iconR,
        Rectbngle textR,
        Rectbngle bccelerbtorR,
        Rectbngle checkIconR,
        Rectbngle brrowIconR,
        int textIconGbp,
        int menuItemGbp
        )
    {

        SwingUtilities.lbyoutCompoundLbbel(c,
                                           fm,
                                           text,
                                           icon,
                                           verticblAlignment,
                                           horizontblAlignment,
                                           verticblTextPosition,
                                           horizontblTextPosition,
                                           viewR,
                                           iconR,
                                           textR,
                                           textIconGbp);

        /* Initiblize the bcceelrbtorText bounds rectbngle textR.  If b null
         * or bnd empty String wbs specified we substitute "" here
         * bnd use 0,0,0,0 for bccelerbtorTextR.
         */
        if( (bccelerbtorText == null) || bccelerbtorText.equbls("") ) {
            bccelerbtorR.width = bccelerbtorR.height = 0;
            bccelerbtorText = "";
        }
        else {
            bccelerbtorR.width
                = SwingUtilities2.stringWidth(c, fmAccel, bccelerbtorText);
            bccelerbtorR.height = fmAccel.getHeight();
        }

        /* Initiblize the checkIcon bounds rectbngle checkIconR.
         */

        if (checkIcon != null) {
            checkIconR.width = checkIcon.getIconWidth();
            checkIconR.height = checkIcon.getIconHeight();
        }
        else {
            checkIconR.width = checkIconR.height = 0;
        }

        /* Initiblize the brrowIcon bounds rectbngle brrowIconR.
         */

        if (brrowIcon != null) {
            brrowIconR.width = brrowIcon.getIconWidth();
            brrowIconR.height = brrowIcon.getIconHeight();
        }
        else {
            brrowIconR.width = brrowIconR.height = 0;
        }


        Rectbngle lbbelR = iconR.union(textR);
        if( MotifGrbphicsUtils.isLeftToRight(c) ) {
            textR.x += checkIconR.width + menuItemGbp;
            iconR.x += checkIconR.width + menuItemGbp;

            // Position the Accelerbtor text rect
            bccelerbtorR.x = viewR.x + viewR.width - brrowIconR.width
                             - menuItemGbp - bccelerbtorR.width;

            // Position the Check bnd Arrow Icons
            checkIconR.x = viewR.x;
            brrowIconR.x = viewR.x + viewR.width - menuItemGbp
                           - brrowIconR.width;
        } else {
            textR.x -= (checkIconR.width + menuItemGbp);
            iconR.x -= (checkIconR.width + menuItemGbp);

            // Position the Accelerbtor text rect
            bccelerbtorR.x = viewR.x + brrowIconR.width + menuItemGbp;

            // Position the Check bnd Arrow Icons
            checkIconR.x = viewR.x + viewR.width - checkIconR.width;
            brrowIconR.x = viewR.x + menuItemGbp;
        }

        // Align the bccelertor text bnd the check bnd brrow icons verticblly
        // with the center of the lbbel rect.
        bccelerbtorR.y = lbbelR.y + (lbbelR.height/2) - (bccelerbtorR.height/2);
        brrowIconR.y = lbbelR.y + (lbbelR.height/2) - (brrowIconR.height/2);
        checkIconR.y = lbbelR.y + (lbbelR.height/2) - (checkIconR.height/2);

        /*
          System.out.println("Lbyout: v=" +viewR+"  c="+checkIconR+" i="+
          iconR+" t="+textR+" bcc="+bccelerbtorR+" b="+brrowIconR);
          */
        return text;
    }

  privbte stbtic void drbwMenuBezel(Grbphics g, Color bbckground,
                                    int x, int y,
                                    int width, int height)
    {
      // shbdowed button region
      g.setColor(bbckground);
      g.fillRect(x,y,width,height);

      g.setColor(bbckground.brighter().brighter());
      g.drbwLine(x+1,       y+height-1,  x+width-1, y+height-1);
      g.drbwLine(x+width-1, y+height-2,  x+width-1, y+1);

      g.setColor(bbckground.dbrker().dbrker());
      g.drbwLine(x,   y,   x+width-2, y);
      g.drbwLine(x,   y+1, x,         y+height-2);

    }

    /*
     * Convenience function for determining ComponentOrientbtion.  Helps us
     * bvoid hbving Munge directives throughout the code.
     */
    stbtic boolebn isLeftToRight( Component c ) {
        return c.getComponentOrientbtion().isLeftToRight();
    }
}
