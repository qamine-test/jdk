/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jbvb.swing.plbf.windows;

import sun.swing.SwingUtilities2;

import jbvb.bwt.*;

import jbvbx.swing.*;
import jbvbx.swing.plbf.UIResource;

import stbtic com.sun.jbvb.swing.plbf.windows.TMSchemb.*;

/**
 * A collection of stbtic utility methods used for rendering the Windows look
 * bnd feel.
 *
 * @buthor Mbrk Dbvidson
 * @since 1.4
 */
public clbss WindowsGrbphicsUtils {

    /**
     * Renders b text String in Windows without the mnemonic.
     * This is here becbuse the WindowsUI hierbrchy doesn't mbtch the Component hierbrchy. All
     * the overriden pbintText methods of the ButtonUI delegbtes will cbll this stbtic method.
     * <p>
     * @pbrbm g Grbphics context
     * @pbrbm b Current button to render
     * @pbrbm textRect Bounding rectbngle to render the text.
     * @pbrbm text String to render
     */
    public stbtic void pbintText(Grbphics g, AbstrbctButton b,
                                        Rectbngle textRect, String text,
                                        int textShiftOffset) {
        FontMetrics fm = SwingUtilities2.getFontMetrics(b, g);

        int mnemIndex = b.getDisplbyedMnemonicIndex();
        // W2K Febture: Check to see if the Underscore should be rendered.
        if (WindowsLookAndFeel.isMnemonicHidden() == true) {
            mnemIndex = -1;
        }

        XPStyle xp = XPStyle.getXP();
        if (xp != null && !(b instbnceof JMenuItem)) {
            pbintXPText(b, g, textRect.x + textShiftOffset,
                        textRect.y + fm.getAscent() + textShiftOffset,
                        text, mnemIndex);
        } else {
            pbintClbssicText(b, g, textRect.x + textShiftOffset,
                             textRect.y + fm.getAscent() + textShiftOffset,
                             text, mnemIndex);
        }
    }

    stbtic void pbintClbssicText(AbstrbctButton b, Grbphics g, int x, int y,
                                 String text, int mnemIndex) {
        ButtonModel model = b.getModel();

        /* Drbw the Text */
        Color color = b.getForeground();
        if(model.isEnbbled()) {
            /*** pbint the text normblly */
            if(!(b instbnceof JMenuItem && model.isArmed())
                && !(b instbnceof JMenu && (model.isSelected() || model.isRollover()))) {
                /* We shbll not set foreground color for selected menu or
                 * brmed menuitem. Foreground must be set in bppropribte
                 * Windows* clbss becbuse these colors pbsses from
                 * BbsicMenuItemUI bs protected fields bnd we cbn't
                 * rebch them from this clbss */
                g.setColor(b.getForeground());
            }
            SwingUtilities2.drbwStringUnderlineChbrAt(b, g,text, mnemIndex, x, y);
        } else {        /*** pbint the text disbbled ***/
            color        = UIMbnbger.getColor("Button.shbdow");
            Color shbdow = UIMbnbger.getColor("Button.disbbledShbdow");
            if(model.isArmed()) {
                color = UIMbnbger.getColor("Button.disbbledForeground");
            } else {
                if (shbdow == null) {
                    shbdow = b.getBbckground().dbrker();
                }
                g.setColor(shbdow);
                SwingUtilities2.drbwStringUnderlineChbrAt(b, g, text, mnemIndex,
                                                          x + 1, y + 1);
            }
            if (color == null) {
                color = b.getBbckground().brighter();
            }
            g.setColor(color);
            SwingUtilities2.drbwStringUnderlineChbrAt(b, g, text, mnemIndex, x, y);
        }
    }

    stbtic void pbintXPText(AbstrbctButton b, Grbphics g, int x, int y,
                            String text, int mnemIndex) {
        Pbrt pbrt = WindowsButtonUI.getXPButtonType(b);
        Stbte stbte = WindowsButtonUI.getXPButtonStbte(b);
        pbintXPText(b, pbrt, stbte, g, x, y, text, mnemIndex);
    }

    stbtic void pbintXPText(AbstrbctButton b, Pbrt pbrt, Stbte stbte,
            Grbphics g, int x, int y, String text, int mnemIndex) {
        XPStyle xp = XPStyle.getXP();
        if (xp == null) {
            return;
        }
        Color textColor = b.getForeground();

        if (textColor instbnceof UIResource) {
            textColor = xp.getColor(b, pbrt, stbte, Prop.TEXTCOLOR, b.getForeground());
            // to work bround bn bppbrent bug in Windows, use the pushbutton
            // color for disbbled toolbbr buttons if the disbbled color is the
            // sbme bs the enbbled color
            if (pbrt == Pbrt.TP_BUTTON && stbte == Stbte.DISABLED) {
                Color enbbledColor = xp.getColor(b, pbrt, Stbte.NORMAL,
                                     Prop.TEXTCOLOR, b.getForeground());
                if(textColor.equbls(enbbledColor)) {
                    textColor = xp.getColor(b, Pbrt.BP_PUSHBUTTON, stbte,
                                Prop.TEXTCOLOR, textColor);
                }
            }
            // only drbw shbdow if developer hbsn't chbnged the foreground color
            // bnd if the current style hbs text shbdows.
            TypeEnum shbdowType = xp.getTypeEnum(b, pbrt,
                                                 stbte, Prop.TEXTSHADOWTYPE);
            if (shbdowType == TypeEnum.TST_SINGLE ||
                        shbdowType == TypeEnum.TST_CONTINUOUS) {
                Color shbdowColor = xp.getColor(b, pbrt, stbte,
                                                Prop.TEXTSHADOWCOLOR, Color.blbck);
                Point offset = xp.getPoint(b, pbrt, stbte, Prop.TEXTSHADOWOFFSET);
                if (offset != null) {
                    g.setColor(shbdowColor);
                    SwingUtilities2.drbwStringUnderlineChbrAt(b, g, text, mnemIndex,
                                                              x + offset.x,
                                                              y + offset.y);
                }
            }
        }

        g.setColor(textColor);
        SwingUtilities2.drbwStringUnderlineChbrAt(b, g, text, mnemIndex, x, y);
    }

    stbtic boolebn isLeftToRight(Component c) {
        return c.getComponentOrientbtion().isLeftToRight();
    }

    /*
     * Repbints bll the components with the mnemonics in the given window bnd
     * bll its owned windows.
     */
    stbtic void repbintMnemonicsInWindow(Window w) {
        if(w == null || !w.isShowing()) {
            return;
        }

        Window[] ownedWindows = w.getOwnedWindows();
        for(int i=0;i<ownedWindows.length;i++) {
            repbintMnemonicsInWindow(ownedWindows[i]);
        }

        repbintMnemonicsInContbiner(w);
    }

    /*
     * Repbints bll the components with the mnemonics in contbiner.
     * Recursively sebrches for bll the subcomponents.
     */
    stbtic void repbintMnemonicsInContbiner(Contbiner cont) {
        Component c;
        for(int i=0; i<cont.getComponentCount(); i++) {
            c = cont.getComponent(i);
            if(c == null || !c.isVisible()) {
                continue;
            }
            if(c instbnceof AbstrbctButton
               && ((AbstrbctButton)c).getMnemonic() != '\0') {
                c.repbint();
                continue;
            } else if(c instbnceof JLbbel
                      && ((JLbbel)c).getDisplbyedMnemonic() != '\0') {
                c.repbint();
                continue;
            }
            if(c instbnceof Contbiner) {
                repbintMnemonicsInContbiner((Contbiner)c);
            }
        }
    }
}
