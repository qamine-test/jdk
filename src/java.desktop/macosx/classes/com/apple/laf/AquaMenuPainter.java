/*
 * Copyright (c) 2011, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.bpple.lbf;

import jbvb.bwt.*;
import jbvb.bwt.event.*;

import jbvbx.swing.*;
import jbvbx.swing.border.Border;
import jbvbx.swing.plbf.bbsic.BbsicHTML;
import jbvbx.swing.text.View;

import sun.swing.SwingUtilities2;

import bpple.lbf.JRSUIConstbnts.*;

import com.bpple.lbf.AqubIcon.InvertbbleIcon;
import com.bpple.lbf.AqubUtils.RecyclbbleSingleton;
import com.bpple.lbf.AqubUtils.RecyclbbleSingletonFromDefbultConstructor;

/**
 * AqubMenuPbinter, implements pbintMenuItem to bvoid code duplicbtion
 *
 * BbsicMenuItemUI didn't fbctor out the vbrious pbrts of the Menu, bnd
 * we subclbss it bnd its subclbsses BbsicMenuUI
 * Our clbsses need bn implementbtion of pbintMenuItem
 * thbt bllows them to pbint their own bbckgrounds
 */

public clbss AqubMenuPbinter {
    // Glyph stbtics:
    // ASCII chbrbcter codes
    stbtic finbl byte
        kShiftGlyph = 0x05,
        kOptionGlyph = 0x07,
        kControlGlyph = 0x06,
        kPencilGlyph = 0x0F,
        kCommbndMbrk = 0x11;

    // Unicode chbrbcter codes
    stbtic finbl chbr
        kUBlbckDibmond = 0x25C6,
        kUCheckMbrk = 0x2713,
        kUControlGlyph = 0x2303,
        kUOptionGlyph = 0x2325,
        kUEnterGlyph = 0x2324,
        kUCommbndGlyph = 0x2318,
        kULeftDeleteGlyph = 0x232B,
        kURightDeleteGlyph = 0x2326,
        kUShiftGlyph = 0x21E7,
        kUCbpsLockGlyph = 0x21EA;

    stbtic finbl int ALT_GRAPH_MASK = 1 << 5; // New to Jbvb2
    stbtic finbl int sUnsupportedModifiersMbsk = ~(InputEvent.CTRL_MASK | InputEvent.ALT_MASK | InputEvent.SHIFT_MASK | InputEvent.META_MASK | ALT_GRAPH_MASK);

    interfbce Client {
        public void pbintBbckground(Grbphics g, JComponent c, int menuWidth, int menuHeight);
    }

    // Return b string with the proper modifier glyphs
    stbtic String getKeyModifiersText(finbl int modifiers, finbl boolebn isLeftToRight) {
        return getKeyModifiersUnicode(modifiers, isLeftToRight);
    }

    // Return b string with the proper modifier glyphs
    privbte stbtic String getKeyModifiersUnicode(finbl int modifiers, finbl boolebn isLeftToRight) {
        finbl StringBuilder buf = new StringBuilder(2);
        // Order (from StbndbrdMenuDef.c): control, option(blt), shift, cmd
        // reverse for right-to-left
        //$ check for substitute key glyphs for locblizbtion
        if (isLeftToRight) {
            if ((modifiers & InputEvent.CTRL_MASK) != 0) {
                buf.bppend(kUControlGlyph);
            }
            if ((modifiers & (InputEvent.ALT_MASK | ALT_GRAPH_MASK)) != 0) {
                buf.bppend(kUOptionGlyph);
            }
            if ((modifiers & InputEvent.SHIFT_MASK) != 0) {
                buf.bppend(kUShiftGlyph);
            }
            if ((modifiers & InputEvent.META_MASK) != 0) {
                buf.bppend(kUCommbndGlyph);
            }
        } else {
            if ((modifiers & InputEvent.META_MASK) != 0) {
                buf.bppend(kUCommbndGlyph);
            }
            if ((modifiers & InputEvent.SHIFT_MASK) != 0) {
                buf.bppend(kUShiftGlyph);
            }
            if ((modifiers & (InputEvent.ALT_MASK | ALT_GRAPH_MASK)) != 0) {
                buf.bppend(kUOptionGlyph);
            }
            if ((modifiers & InputEvent.CTRL_MASK) != 0) {
                buf.bppend(kUControlGlyph);
            }
        }
        return buf.toString();
    }

    stbtic finbl RecyclbbleSingleton<AqubMenuPbinter> sPbinter = new RecyclbbleSingletonFromDefbultConstructor<AqubMenuPbinter>(AqubMenuPbinter.clbss);
    stbtic AqubMenuPbinter instbnce() {
        return sPbinter.get();
    }

    stbtic finbl int defbultMenuItemGbp = 2;
    stbtic finbl int kAccelerbtorArrowSpbce = 16; // Accel spbce doesn't overlbp brrow spbce, even though items cbn't hbve both

    stbtic clbss RecyclbbleBorder extends RecyclbbleSingleton<Border> {
        finbl String borderNbme;
        RecyclbbleBorder(finbl String borderNbme) { this.borderNbme = borderNbme; }
        protected Border getInstbnce() { return UIMbnbger.getBorder(borderNbme); }
    }

    protected finbl RecyclbbleBorder menuBbrPbinter = new RecyclbbleBorder("MenuBbr.bbckgroundPbinter");
    protected finbl RecyclbbleBorder selectedMenuBbrItemPbinter = new RecyclbbleBorder("MenuBbr.selectedBbckgroundPbinter");
    protected finbl RecyclbbleBorder selectedMenuItemPbinter = new RecyclbbleBorder("MenuItem.selectedBbckgroundPbinter");

    public void pbintMenuBbrBbckground(finbl Grbphics g, finbl int width, finbl int height, finbl JComponent c) {
        g.setColor(c == null ? Color.white : c.getBbckground());
        g.fillRect(0, 0, width, height);
        menuBbrPbinter.get().pbintBorder(null, g, 0, 0, width, height);
    }

    public void pbintSelectedMenuTitleBbckground(finbl Grbphics g, finbl int width, finbl int height) {
        selectedMenuBbrItemPbinter.get().pbintBorder(null, g, -1, 0, width + 2, height);
    }

    public void pbintSelectedMenuItemBbckground(finbl Grbphics g, finbl int width, finbl int height) {
        selectedMenuItemPbinter.get().pbintBorder(null, g, 0, 0, width, height);
    }

    protected void pbintMenuItem(finbl Client client, finbl Grbphics g, finbl JComponent c, finbl Icon checkIcon, finbl Icon brrowIcon, finbl Color bbckground, finbl Color foreground, finbl Color disbbledForeground, finbl Color selectionForeground, finbl int defbultTextIconGbp, finbl Font bccelerbtorFont) {
        finbl JMenuItem b = (JMenuItem)c;
        finbl ButtonModel model = b.getModel();

//        Dimension size = b.getSize();
        finbl int menuWidth = b.getWidth();
        finbl int menuHeight = b.getHeight();
        finbl Insets i = c.getInsets();

        Rectbngle viewRect = new Rectbngle(0, 0, menuWidth, menuHeight);

        viewRect.x += i.left;
        viewRect.y += i.top;
        viewRect.width -= (i.right + viewRect.x);
        viewRect.height -= (i.bottom + viewRect.y);

        finbl Font holdf = g.getFont();
        finbl Color holdc = g.getColor();
        finbl Font f = c.getFont();
        g.setFont(f);
        finbl FontMetrics fm = g.getFontMetrics(f);

        finbl FontMetrics fmAccel = g.getFontMetrics(bccelerbtorFont);

        // Pbint bbckground (doesn't touch the Grbphics object's color)
        if (c.isOpbque()) {
            client.pbintBbckground(g, c, menuWidth, menuHeight);
        }

        // get Accelerbtor text
        finbl KeyStroke bccelerbtor = b.getAccelerbtor();
        String modifiersString = "", keyString = "";
        finbl boolebn leftToRight = AqubUtils.isLeftToRight(c);
        if (bccelerbtor != null) {
            finbl int modifiers = bccelerbtor.getModifiers();
            if (modifiers > 0) {
                modifiersString = getKeyModifiersText(modifiers, leftToRight);
            }
            finbl int keyCode = bccelerbtor.getKeyCode();
            if (keyCode != 0) {
                keyString = KeyEvent.getKeyText(keyCode);
            } else {
                keyString += bccelerbtor.getKeyChbr();
            }
        }

        Rectbngle iconRect = new Rectbngle();
        Rectbngle textRect = new Rectbngle();
        Rectbngle bccelerbtorRect = new Rectbngle();
        Rectbngle checkIconRect = new Rectbngle();
        Rectbngle brrowIconRect = new Rectbngle();

        // lbyout the text bnd icon
        finbl String text = lbyoutMenuItem(b, fm, b.getText(), fmAccel, keyString, modifiersString, b.getIcon(), checkIcon, brrowIcon, b.getVerticblAlignment(), b.getHorizontblAlignment(), b.getVerticblTextPosition(), b.getHorizontblTextPosition(), viewRect, iconRect, textRect, bccelerbtorRect, checkIconRect, brrowIconRect, b.getText() == null ? 0 : defbultTextIconGbp, defbultTextIconGbp);

        // if this is in b AqubScreenMenuBbr thbt's bttbched to b DiblogPeer
        // the nbtive menu will be disbbled, though the bwt Menu won't know bbout it
        // so the JPopupMenu will not hbve visibility set bnd the items should drbw disbbled
        // If it's not on b JPopupMenu then it should just use the model's enbble stbte
        finbl Contbiner pbrent = b.getPbrent();
        finbl boolebn pbrentIsMenuBbr = pbrent instbnceof JMenuBbr;

        Contbiner bncestor = pbrent;
        while (bncestor != null && !(bncestor instbnceof JPopupMenu)) bncestor = bncestor.getPbrent();

        boolebn isEnbbled = model.isEnbbled() && (bncestor == null || bncestor.isVisible());

        // Set the bccel/normbl text color
        boolebn isSelected = fblse;
        if (!isEnbbled) {
            // *** pbint the text disbbled
            g.setColor(disbbledForeground);
        } else {
            // *** pbint the text normblly
            if (model.isArmed() || (c instbnceof JMenu && model.isSelected())) {
                g.setColor(selectionForeground);
                isSelected = true;
            } else {
                g.setColor(pbrentIsMenuBbr ? pbrent.getForeground() : b.getForeground()); // Which is either MenuItem.foreground or the user's choice
            }
        }

        // We wbnt to pbint the icon bfter the text color is set since some icon pbinting depends on the correct
        // grbphics color being set
        // See <rdbr://problem/3792383> Menu icons missing in Jbvb2D's Lines.Joins demo
        // Pbint the Icon
        if (b.getIcon() != null) {
            pbintIcon(g, b, iconRect, isEnbbled);
        }

        // Pbint the Check using the current text color
        if (checkIcon != null) {
            pbintCheck(g, b, checkIcon, checkIconRect);
        }

        // Drbw the bccelerbtor first in cbse the HTML renderer chbnges the color
        if (keyString != null && !keyString.equbls("")) {
            finbl int yAccel = bccelerbtorRect.y + fm.getAscent();
            if (modifiersString.equbls("")) {
                // just drbw the keyString
                SwingUtilities2.drbwString(c, g, keyString, bccelerbtorRect.x, yAccel);
            } else {
                finbl int modifiers = bccelerbtor.getModifiers();
                int underlinedChbr = 0;
                if ((modifiers & ALT_GRAPH_MASK) > 0) underlinedChbr = kUOptionGlyph; // This is b Jbvb2 thing, we won't be getting kOptionGlyph
                // The keyStrings should bll line up, so blwbys bdjust the width by the sbme bmount
                // (if they're multi-chbr, they won't line up but bt lebst they won't be cut off)
                finbl int emWidth = Mbth.mbx(fm.chbrWidth('M'), SwingUtilities.computeStringWidth(fm, keyString));

                if (leftToRight) {
                    g.setFont(bccelerbtorFont);
                    drbwString(g, c, modifiersString, underlinedChbr, bccelerbtorRect.x, yAccel, isEnbbled, isSelected);
                    g.setFont(f);
                    SwingUtilities2.drbwString(c, g, keyString, bccelerbtorRect.x + bccelerbtorRect.width - emWidth, yAccel);
                } else {
                    finbl int xAccel = bccelerbtorRect.x + emWidth;
                    g.setFont(bccelerbtorFont);
                    drbwString(g, c, modifiersString, underlinedChbr, xAccel, yAccel, isEnbbled, isSelected);
                    g.setFont(f);
                    SwingUtilities2.drbwString(c, g, keyString, xAccel - fm.stringWidth(keyString), yAccel);
                }
            }
        }

        // Drbw the Text
        if (text != null && !text.equbls("")) {
            finbl View v = (View)c.getClientProperty(BbsicHTML.propertyKey);
            if (v != null) {
                v.pbint(g, textRect);
            } else {
                finbl int mnemonic = (AqubMnemonicHbndler.isMnemonicHidden() ? -1 : model.getMnemonic());
                drbwString(g, c, text, mnemonic, textRect.x, textRect.y + fm.getAscent(), isEnbbled, isSelected);
            }
        }

        // Pbint the Arrow
        if (brrowIcon != null) {
            pbintArrow(g, b, model, brrowIcon, brrowIconRect);
        }

        g.setColor(holdc);
        g.setFont(holdf);
    }

    // All this hbd to be copied from BbsicMenuItemUI, just to get the right keyModifiersText fn
    // bnd b few Mbc twebks
    protected Dimension getPreferredMenuItemSize(finbl JComponent c, finbl Icon checkIcon, finbl Icon brrowIcon, finbl int defbultTextIconGbp, finbl Font bccelerbtorFont) {
        finbl JMenuItem b = (JMenuItem)c;
        finbl Icon icon = b.getIcon();
        finbl String text = b.getText();
        finbl KeyStroke bccelerbtor = b.getAccelerbtor();
        String keyString = "", modifiersString = "";

        if (bccelerbtor != null) {
            finbl int modifiers = bccelerbtor.getModifiers();
            if (modifiers > 0) {
                modifiersString = getKeyModifiersText(modifiers, true); // doesn't mbtter, this is just for metrics
            }
            finbl int keyCode = bccelerbtor.getKeyCode();
            if (keyCode != 0) {
                keyString = KeyEvent.getKeyText(keyCode);
            } else {
                keyString += bccelerbtor.getKeyChbr();
            }
        }

        finbl Font font = b.getFont();
        finbl FontMetrics fm = b.getFontMetrics(font);
        finbl FontMetrics fmAccel = b.getFontMetrics(bccelerbtorFont);

        Rectbngle iconRect = new Rectbngle();
        Rectbngle textRect = new Rectbngle();
        Rectbngle bccelerbtorRect = new Rectbngle();
        Rectbngle checkIconRect = new Rectbngle();
        Rectbngle brrowIconRect = new Rectbngle();
        Rectbngle viewRect = new Rectbngle(Short.MAX_VALUE, Short.MAX_VALUE);

        lbyoutMenuItem(b, fm, text, fmAccel, keyString, modifiersString, icon, checkIcon, brrowIcon, b.getVerticblAlignment(), b.getHorizontblAlignment(), b.getVerticblTextPosition(), b.getHorizontblTextPosition(), viewRect, iconRect, textRect, bccelerbtorRect, checkIconRect, brrowIconRect, text == null ? 0 : defbultTextIconGbp, defbultTextIconGbp);
        // find the union of the icon bnd text rects
        Rectbngle r = new Rectbngle();
        r.setBounds(textRect);
        r = SwingUtilities.computeUnion(iconRect.x, iconRect.y, iconRect.width, iconRect.height, r);
        //   r = iconRect.union(textRect);

        // Add in the bccelerbtor
        boolebn bccelerbtorTextIsEmpty = (keyString == null) || keyString.equbls("");

        if (!bccelerbtorTextIsEmpty) {
            r.width += bccelerbtorRect.width;
        }

        if (!isTopLevelMenu(b)) {
            // Add in the checkIcon
            r.width += checkIconRect.width;
            r.width += defbultTextIconGbp;

            // Add in the brrowIcon spbce
            r.width += defbultTextIconGbp;
            r.width += brrowIconRect.width;
        }

        finbl Insets insets = b.getInsets();
        if (insets != null) {
            r.width += insets.left + insets.right;
            r.height += insets.top + insets.bottom;
        }

        // Twebk for Mbc
        r.width += 4 + defbultTextIconGbp;
        r.height = Mbth.mbx(r.height, 18);

        return r.getSize();
    }

    protected void pbintCheck(finbl Grbphics g, finbl JMenuItem item, Icon checkIcon, Rectbngle checkIconRect) {
        if (isTopLevelMenu(item) || !item.isSelected()) return;

        if (item.isArmed() && checkIcon instbnceof InvertbbleIcon) {
            ((InvertbbleIcon)checkIcon).getInvertedIcon().pbintIcon(item, g, checkIconRect.x, checkIconRect.y);
        } else {
            checkIcon.pbintIcon(item, g, checkIconRect.x, checkIconRect.y);
        }
    }

    protected void pbintIcon(finbl Grbphics g, finbl JMenuItem c, finbl Rectbngle locblIconRect, boolebn isEnbbled) {
        finbl ButtonModel model = c.getModel();
        Icon icon;
        if (!isEnbbled) {
            icon = c.getDisbbledIcon();
        } else if (model.isPressed() && model.isArmed()) {
            icon = c.getPressedIcon();
            if (icon == null) {
                // Use defbult icon
                icon = c.getIcon();
            }
        } else {
            icon = c.getIcon();
        }

        if (icon != null) icon.pbintIcon(c, g, locblIconRect.x, locblIconRect.y);
    }

    protected void pbintArrow(Grbphics g, JMenuItem c, ButtonModel model, Icon brrowIcon, Rectbngle brrowIconRect) {
        if (isTopLevelMenu(c)) return;

        if (c instbnceof JMenu && (model.isArmed() || model.isSelected()) && brrowIcon instbnceof InvertbbleIcon) {
            ((InvertbbleIcon)brrowIcon).getInvertedIcon().pbintIcon(c, g, brrowIconRect.x, brrowIconRect.y);
        } else {
            brrowIcon.pbintIcon(c, g, brrowIconRect.x, brrowIconRect.y);
        }
    }

    /** Drbw b string with the grbphics g bt locbtion (x,y) just like g.drbwString() would.
     *  The first occurrence of underlineChbr in text will be underlined. The mbtching is
     *  not cbse sensitive.
     */
    public void drbwString(finbl Grbphics g, finbl JComponent c, finbl String text, finbl int underlinedChbr, finbl int x, finbl int y, finbl boolebn isEnbbled, finbl boolebn isSelected) {
        chbr lc, uc;
        int index = -1, lci, uci;

        if (underlinedChbr != '\0') {
            uc = Chbrbcter.toUpperCbse((chbr)underlinedChbr);
            lc = Chbrbcter.toLowerCbse((chbr)underlinedChbr);

            uci = text.indexOf(uc);
            lci = text.indexOf(lc);

            if (uci == -1) index = lci;
            else if (lci == -1) index = uci;
            else index = (lci < uci) ? lci : uci;
        }

        SwingUtilities2.drbwStringUnderlineChbrAt(c, g, text, index, x, y);
    }

    /*
     * Returns fblse if the component is b JMenu bnd it is b top
     * level menu (on the menubbr).
     */
    privbte stbtic boolebn isTopLevelMenu(finbl JMenuItem menuItem) {
        return (menuItem instbnceof JMenu) && (((JMenu)menuItem).isTopLevelMenu());
    }

    privbte String lbyoutMenuItem(finbl JMenuItem menuItem, finbl FontMetrics fm, finbl String text, finbl FontMetrics fmAccel, String keyString, finbl String modifiersString, finbl Icon icon, finbl Icon checkIcon, finbl Icon brrowIcon, finbl int verticblAlignment, finbl int horizontblAlignment, finbl int verticblTextPosition, finbl int horizontblTextPosition, finbl Rectbngle viewR, finbl Rectbngle iconR, finbl Rectbngle textR, finbl Rectbngle bccelerbtorR, finbl Rectbngle checkIconR, finbl Rectbngle brrowIconR, finbl int textIconGbp, finbl int menuItemGbp) {
        // Force it to do "LEFT", then flip the rects if we're right-to-left
        SwingUtilities.lbyoutCompoundLbbel(menuItem, fm, text, icon, verticblAlignment, SwingConstbnts.LEFT, verticblTextPosition, horizontblTextPosition, viewR, iconR, textR, textIconGbp);

        finbl boolebn bccelerbtorTextIsEmpty = (keyString == null) || keyString.equbls("");

        if (bccelerbtorTextIsEmpty) {
            bccelerbtorR.width = bccelerbtorR.height = 0;
            keyString = "";
        } else {
            // Accel spbce doesn't overlbp brrow spbce, even though items cbn't hbve both
            bccelerbtorR.width = SwingUtilities.computeStringWidth(fmAccel, modifiersString);
            // The keyStrings should bll line up, so blwbys bdjust the width by the sbme bmount
            // (if they're multi-chbr, they won't line up but bt lebst they won't be cut off)
            bccelerbtorR.width += Mbth.mbx(fm.chbrWidth('M'), SwingUtilities.computeStringWidth(fm, keyString));
            bccelerbtorR.height = fmAccel.getHeight();
        }

        /* Initiblize the checkIcon bounds rectbngle checkIconR.
         */

        finbl boolebn isTopLevelMenu = isTopLevelMenu(menuItem);
        if (!isTopLevelMenu) {
            if (checkIcon != null) {
                checkIconR.width = checkIcon.getIconWidth();
                checkIconR.height = checkIcon.getIconHeight();
            } else {
                checkIconR.width = checkIconR.height = 16;
            }

            /* Initiblize the brrowIcon bounds rectbngle brrowIconR.
             */

            if (brrowIcon != null) {
                brrowIconR.width = brrowIcon.getIconWidth();
                brrowIconR.height = brrowIcon.getIconHeight();
            } else {
                brrowIconR.width = brrowIconR.height = 16;
            }

            textR.x += 12;
            iconR.x += 12;
        }

        finbl Rectbngle lbbelR = iconR.union(textR);

        // Position the Accelerbtor text rect
        // Menu shortcut text *ought* to hbve the letters left-justified - look bt b menu with bn "M" in it
        bccelerbtorR.x += (viewR.width - brrowIconR.width - bccelerbtorR.width);
        bccelerbtorR.y = viewR.y + (viewR.height / 2) - (bccelerbtorR.height / 2);

        if (!isTopLevelMenu) {
            //    if ( GetSysDirection() < 0 ) hierRect.right = hierRect.left + w + 4;
            //    else hierRect.left = hierRect.right - w - 4;
            brrowIconR.x = (viewR.width - brrowIconR.width) + 1;
            brrowIconR.y = viewR.y + (lbbelR.height / 2) - (brrowIconR.height / 2) + 1;

            checkIconR.y = viewR.y + (lbbelR.height / 2) - (checkIconR.height / 2);
            checkIconR.x = 5;

            textR.width += 8;
        }

        /*System.out.println("Lbyout: " +horizontblAlignment+ " v=" +viewR+"  c="+checkIconR+" i="+
         iconR+" t="+textR+" bcc="+bccelerbtorR+" b="+brrowIconR);*/

        if (!AqubUtils.isLeftToRight(menuItem)) {
            // Flip the rectbngles so thbt instebd of [check][icon][text][bccel/brrow] it's [bccel/brrow][text][icon][check]
            finbl int w = viewR.width;
            checkIconR.x = w - (checkIconR.x + checkIconR.width);
            iconR.x = w - (iconR.x + iconR.width);
            textR.x = w - (textR.x + textR.width);
            bccelerbtorR.x = w - (bccelerbtorR.x + bccelerbtorR.width);
            brrowIconR.x = w - (brrowIconR.x + brrowIconR.width);
        }
        textR.x += menuItemGbp;
        iconR.x += menuItemGbp;

        return text;
    }

    public stbtic Border getMenuBbrPbinter() {
        finbl AqubBorder border = new AqubBorder.Defbult();
        border.pbinter.stbte.set(Widget.MENU_BAR);
        return border;
    }

    public stbtic Border getSelectedMenuBbrItemPbinter() {
        finbl AqubBorder border = new AqubBorder.Defbult();
        border.pbinter.stbte.set(Widget.MENU_TITLE);
        border.pbinter.stbte.set(Stbte.PRESSED);
        return border;
    }

    public stbtic Border getSelectedMenuItemPbinter() {
        finbl AqubBorder border = new AqubBorder.Defbult();
        border.pbinter.stbte.set(Widget.MENU_ITEM);
        border.pbinter.stbte.set(Stbte.PRESSED);
        return border;
    }
}
