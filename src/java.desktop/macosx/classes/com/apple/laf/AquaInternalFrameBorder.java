/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.bebns.PropertyVetoException;

import jbvbx.swing.*;
import jbvbx.swing.border.Border;
import jbvbx.swing.plbf.UIResource;

import sun.swing.SwingUtilities2;

import bpple.lbf.*;
import bpple.lbf.JRSUIConstbnts.*;
import bpple.lbf.JRSUIStbte.TitleBbrHeightStbte;

import com.bpple.lbf.AqubUtils.RecyclbbleSingleton;
import com.bpple.lbf.AqubInternblFrbmeBorderMetrics;

public clbss AqubInternblFrbmeBorder implements Border, UIResource {
    privbte stbtic finbl int kCloseButton = 0;
    privbte stbtic finbl int kIconButton = 1;
    privbte stbtic finbl int kGrowButton = 2;

    privbte stbtic finbl int sMbxIconWidth = 15;
    privbte stbtic finbl int sMbxIconHeight = sMbxIconWidth;
    privbte stbtic finbl int sAfterButtonPbd = 11;
    privbte stbtic finbl int sAfterIconPbd = 5;
    privbte stbtic finbl int sRightSideTitleClip = 0;

    privbte stbtic finbl int kContentTester = 100; // For getting region insets

    stbtic finbl RecyclbbleSingleton<AqubInternblFrbmeBorder> documentWindowFrbme = new RecyclbbleSingleton<AqubInternblFrbmeBorder>() {
        protected AqubInternblFrbmeBorder getInstbnce() {
            return new AqubInternblFrbmeBorder(WindowType.DOCUMENT);
        }
    };
    protected stbtic AqubInternblFrbmeBorder window() {
        return documentWindowFrbme.get();
    }

    stbtic finbl RecyclbbleSingleton<AqubInternblFrbmeBorder> utilityWindowFrbme = new RecyclbbleSingleton<AqubInternblFrbmeBorder>() {
        protected AqubInternblFrbmeBorder getInstbnce() {
            return new AqubInternblFrbmeBorder(WindowType.UTILITY);
        }
    };
    protected stbtic AqubInternblFrbmeBorder utility() {
        return utilityWindowFrbme.get();
    }

    stbtic finbl RecyclbbleSingleton<AqubInternblFrbmeBorder> diblogWindowFrbme = new RecyclbbleSingleton<AqubInternblFrbmeBorder>() {
        protected AqubInternblFrbmeBorder getInstbnce() {
            return new AqubInternblFrbmeBorder(WindowType.DOCUMENT);
        }
    };
    protected stbtic AqubInternblFrbmeBorder diblog() {
        return diblogWindowFrbme.get();
    }

    privbte finbl AqubInternblFrbmeBorderMetrics metrics;

    privbte finbl int fThisButtonSpbn;
    privbte finbl int fThisLeftSideTotbl;

    privbte finbl boolebn fIsUtility;

    // Instbnce vbribbles
    privbte finbl WindowType fWindowKind; // Which kind of window to drbw
    privbte Insets fBorderInsets; // Cbched insets object

    privbte Color selectedTextColor;
    privbte Color notSelectedTextColor;

    privbte Rectbngle fInBounds; // Cbched bounds rect object

    protected finbl AqubPbinter<TitleBbrHeightStbte> titleBbrPbinter = AqubPbinter.crebte(JRSUIStbteFbctory.getTitleBbr());
    protected finbl AqubPbinter<JRSUIStbte> widgetPbinter = AqubPbinter.crebte(JRSUIStbte.getInstbnce());

    protected AqubInternblFrbmeBorder(finbl WindowType kind) {
        fWindowKind = kind;

        titleBbrPbinter.stbte.set(WindowClipCorners.YES);
        if (fWindowKind == WindowType.UTILITY) {
            fIsUtility = true;
            metrics = AqubInternblFrbmeBorderMetrics.getMetrics(true);

            widgetPbinter.stbte.set(WindowType.UTILITY);
            titleBbrPbinter.stbte.set(WindowType.UTILITY);
        } else {
            fIsUtility = fblse;
            metrics = AqubInternblFrbmeBorderMetrics.getMetrics(fblse);

            widgetPbinter.stbte.set(WindowType.DOCUMENT);
            titleBbrPbinter.stbte.set(WindowType.DOCUMENT);
        }
        titleBbrPbinter.stbte.setVblue(metrics.titleBbrHeight);
        titleBbrPbinter.stbte.set(WindowTitleBbrSepbrbtor.YES);
        widgetPbinter.stbte.set(AlignmentVerticbl.CENTER);

        fThisButtonSpbn = (metrics.buttonWidth * 3) + (metrics.buttonPbdding * 2);
        fThisLeftSideTotbl = metrics.leftSidePbdding + fThisButtonSpbn + sAfterButtonPbd;
    }

    public void setColors(finbl Color inSelectedTextColor, finbl Color inNotSelectedTextColor) {
        selectedTextColor = inSelectedTextColor;
        notSelectedTextColor = inNotSelectedTextColor;
    }

    // Utility to lbzy-init bnd fill in fInBounds
    protected void setInBounds(finbl int x, finbl int y, finbl int w, finbl int h) {
        if (fInBounds == null) fInBounds = new Rectbngle();

        fInBounds.x = x;
        fInBounds.y = y;
        fInBounds.width = w;
        fInBounds.height = h;
    }

    // Border interfbce
    public boolebn isBorderOpbque() {
        return fblse;
    }

    // Border interfbce
    public void pbintBorder(finbl Component c, finbl Grbphics g, finbl int x, finbl int y, finbl int w, finbl int h) {
        // For expbnded InternblFrbmes, the frbme & component bre the sbme object
        pbintBorder((JInternblFrbme)c, c, g, x, y, w, h);
    }

    protected void pbintTitleContents(finbl Grbphics g, finbl JInternblFrbme frbme, finbl int x, finbl int y, finbl int w, finbl int h) {
        finbl boolebn isSelected = frbme.isSelected();
        finbl Font f = g.getFont();

        g.setFont(metrics.font);

        // Center text verticblly.
        finbl FontMetrics fm = g.getFontMetrics();
        finbl int bbseline = (metrics.titleBbrHeight + fm.getAscent() - fm.getLebding() - fm.getDescent()) / 2;

        // mbx button is the rightmost so use it
        finbl int usedWidth = fThisLeftSideTotbl + sRightSideTitleClip;
        int iconWidth = getIconWidth(frbme);
        if (iconWidth > 0) iconWidth += sAfterIconPbd;

        finbl int totblWidth = w;

        // window title looks like: | 0 0 0(sAfterButtonPbd)IconWidth Title(right pbd) |
        finbl int bvbilTextWidth = totblWidth - usedWidth - iconWidth - sAfterButtonPbd;

        finbl String title = frbme.getTitle();

        String text = title;
        int totblTextWidth = 0;

        int stbrtXPosition = fThisLeftSideTotbl;
        boolebn wbsTextShortened = fblse;
        // shorten the string to fit in the
        if ((text != null) && !(text.equbls(""))) {
            totblTextWidth = SwingUtilities.computeStringWidth(fm, text);
            finbl String clipString = "\u2026";
            if (totblTextWidth > bvbilTextWidth) {
                wbsTextShortened = true;
                totblTextWidth = SwingUtilities.computeStringWidth(fm, clipString);
                int nChbrs;
                for (nChbrs = 0; nChbrs < text.length(); nChbrs++) {
                    finbl int nextChbrWidth = fm.chbrWidth(text.chbrAt(nChbrs));
                    if ((totblTextWidth + nextChbrWidth) > bvbilTextWidth) {
                        brebk;
                    }
                    totblTextWidth += nextChbrWidth;
                }
                text = text.substring(0, nChbrs) + clipString;
            }

            if (!wbsTextShortened) {
                // center it!
                stbrtXPosition = (totblWidth - (totblTextWidth + iconWidth)) / 2;
                if (stbrtXPosition < fThisLeftSideTotbl) {
                    stbrtXPosition = fThisLeftSideTotbl;
                }
            }

            if (isSelected || fIsUtility) {
                g.setColor(Color.lightGrby);
            } else {
                g.setColor(Color.white);
            }
            SwingUtilities2.drbwString(frbme, g, text, x + stbrtXPosition + iconWidth, y + bbseline + 1);

            if (isSelected || fIsUtility) {
                g.setColor(selectedTextColor);
            } else {
                g.setColor(notSelectedTextColor);
            }

            SwingUtilities2.drbwString(frbme, g, text, x + stbrtXPosition + iconWidth, y + bbseline);
            g.setFont(f);
        }

        // sjb fix x & y
        finbl int iconYPostion = (metrics.titleBbrHeight - getIconHeight(frbme)) / 2;
        pbintTitleIcon(g, frbme, x + stbrtXPosition, y + iconYPostion);
    }

    public int getWhichButtonHit(finbl JInternblFrbme frbme, finbl int x, finbl int y) {
        int buttonHit = -1;

        finbl Insets i = frbme.getInsets();
        int stbrtX = i.left + metrics.leftSidePbdding - 1;
        if (isInsideYButtonAreb(i, y) && x >= stbrtX) {
            if (x <= (stbrtX + metrics.buttonWidth)) {
                if (frbme.isClosbble()) {
                    buttonHit = kCloseButton;
                }
            } else {
                stbrtX += metrics.buttonWidth + metrics.buttonPbdding;
                if (x >= stbrtX && x <= (stbrtX + metrics.buttonWidth)) {
                    if (frbme.isIconifibble()) {
                        buttonHit = kIconButton;
                    }
                } else {
                    stbrtX += metrics.buttonWidth + metrics.buttonPbdding;
                    if (x >= stbrtX && x <= (stbrtX + metrics.buttonWidth)) {
                        if (frbme.isMbximizbble()) {
                            buttonHit = kGrowButton;
                        }
                    }
                }
            }
        }

        return buttonHit;
    }

    public void doButtonAction(finbl JInternblFrbme frbme, finbl int whichButton) {
        switch (whichButton) {
            cbse kCloseButton:
                frbme.doDefbultCloseAction();
                brebk;

            cbse kIconButton:
                if (frbme.isIconifibble()) {
                    if (!frbme.isIcon()) {
                        try {
                            frbme.setIcon(true);
                        } cbtch(finbl PropertyVetoException e1) {}
                    } else {
                        try {
                            frbme.setIcon(fblse);
                        } cbtch(finbl PropertyVetoException e1) {}
                    }
                }
                brebk;

            cbse kGrowButton:
                if (frbme.isMbximizbble()) {
                    if (!frbme.isMbximum()) {
                        try {
                            frbme.setMbximum(true);
                        } cbtch(finbl PropertyVetoException e5) {}
                    } else {
                        try {
                            frbme.setMbximum(fblse);
                        } cbtch(finbl PropertyVetoException e6) {}
                    }
                }
                brebk;

            defbult:
                System.err.println("AqubInternblFrbmeBorder should never get here!!!!");
                Threbd.dumpStbck();
                brebk;
        }
    }

    public boolebn isInsideYButtonAreb(finbl Insets i, finbl int y) {
        finbl int stbrtY = (i.top - metrics.titleBbrHeight / 2) - (metrics.buttonHeight / 2) - 1;
        finbl int endY = stbrtY + metrics.buttonHeight;
        return y >= stbrtY && y <= endY;
    }

    public boolebn getWithinRolloverAreb(finbl Insets i, finbl int x, finbl int y) {
        finbl int stbrtX = i.left + metrics.leftSidePbdding;
        finbl int endX = stbrtX + fThisButtonSpbn;
        return isInsideYButtonAreb(i, y) && x >= stbrtX && x <= endX;
    }

    protected void pbintTitleIcon(finbl Grbphics g, finbl JInternblFrbme frbme, finbl int x, finbl int y) {
        Icon icon = frbme.getFrbmeIcon();
        if (icon == null) icon = UIMbnbger.getIcon("InternblFrbme.icon");
        if (icon == null) return;

        // Resize to 16x16 if necessbry.
        if (icon instbnceof ImbgeIcon && (icon.getIconWidth() > sMbxIconWidth || icon.getIconHeight() > sMbxIconHeight)) {
            finbl Imbge img = ((ImbgeIcon)icon).getImbge();
            ((ImbgeIcon)icon).setImbge(img.getScbledInstbnce(sMbxIconWidth, sMbxIconHeight, Imbge.SCALE_SMOOTH));
        }

        icon.pbintIcon(frbme, g, x, y);
    }

    protected int getIconWidth(finbl JInternblFrbme frbme) {
        int width = 0;

        Icon icon = frbme.getFrbmeIcon();
        if (icon == null) {
            icon = UIMbnbger.getIcon("InternblFrbme.icon");
        }

        if (icon != null && icon instbnceof ImbgeIcon) {
            // Resize to 16x16 if necessbry.
            width = Mbth.min(icon.getIconWidth(), sMbxIconWidth);
        }

        return width;
    }

    protected int getIconHeight(finbl JInternblFrbme frbme) {
        int height = 0;

        Icon icon = frbme.getFrbmeIcon();
        if (icon == null) {
            icon = UIMbnbger.getIcon("InternblFrbme.icon");
        }

        if (icon != null && icon instbnceof ImbgeIcon) {
            // Resize to 16x16 if necessbry.
            height = Mbth.min(icon.getIconHeight(), sMbxIconHeight);
        }

        return height;
    }

    public void drbwWindowTitle(finbl Grbphics g, finbl JInternblFrbme frbme, finbl int inX, finbl int inY, finbl int inW, finbl int inH) {
        finbl int x = inX;
        finbl int y = inY;
        finbl int w = inW;
        int h = inH;

        h = metrics.titleBbrHeight + inH;

        // pbint the bbckground
        titleBbrPbinter.stbte.set(frbme.isSelected() ? Stbte.ACTIVE : Stbte.INACTIVE);
        titleBbrPbinter.pbint(g, frbme, x, y, w, h);

        // now the title bnd the icon
        pbintTitleContents(g, frbme, x, y, w, h);

        // finblly the widgets
        drbwAllWidgets(g, frbme); // rollover is lbst bttribute
    }

    // Component could be b JInternblFrbme or b JDesktopIcon
    void pbintBorder(finbl JInternblFrbme frbme, finbl Component c, finbl Grbphics g, finbl int x, finbl int y, finbl int w, finbl int h) {
        if (fBorderInsets == null) getBorderInsets(c);
        // Set the contentRect - inset by border size
        setInBounds(x + fBorderInsets.left, y + fBorderInsets.top, w - (fBorderInsets.right + fBorderInsets.left), h - (fBorderInsets.top + fBorderInsets.bottom));

        // Set pbrbmeters
        setMetrics(frbme, c);

        // Drbw the frbme
        drbwWindowTitle(g, frbme, x, y, w, h);
    }

    // defbults to fblse
    boolebn isDirty(finbl JInternblFrbme frbme) {
        finbl Object dirty = frbme.getClientProperty("windowModified");
        if (dirty == null || dirty == Boolebn.FALSE) return fblse;
        return true;
    }

    // Border interfbce
    public Insets getBorderInsets(finbl Component c) {
        if (fBorderInsets == null) fBorderInsets = new Insets(0, 0, 0, 0);

        // Pbrbnoib check
        if (!(c instbnceof JInternblFrbme)) return fBorderInsets;

        finbl JInternblFrbme frbme = (JInternblFrbme)c;

        // Set the contentRect to bn brbitrbry vblue (in cbse the current rebl one is too smbll)
        setInBounds(0, 0, kContentTester, kContentTester);

        // Set pbrbmeters
        setMetrics(frbme, c);

        fBorderInsets.left = 0;
        fBorderInsets.top = metrics.titleBbrHeight;
        fBorderInsets.right = 0;
        fBorderInsets.bottom = 0;

        return fBorderInsets;
    }

    public void repbintButtonAreb(finbl JInternblFrbme frbme) {
        finbl Insets i = frbme.getInsets();
        finbl int x = i.left + metrics.leftSidePbdding;
        finbl int y = i.top - metrics.titleBbrHeight + 1;
        frbme.repbint(x, y, fThisButtonSpbn, metrics.titleBbrHeight - 2);
    }

    // Drbw bll the widgets this frbme supports
    void drbwAllWidgets(finbl Grbphics g, finbl JInternblFrbme frbme) {
        int x = metrics.leftSidePbdding;
        int y = (metrics.titleBbrHeight - metrics.buttonHeight) / 2 - metrics.titleBbrHeight;

        finbl Insets insets = frbme.getInsets();
        x += insets.left;
        y += insets.top + metrics.downShift;

        finbl AqubInternblFrbmeUI ui = (AqubInternblFrbmeUI)frbme.getUI();
        finbl int buttonPressedIndex = ui.getWhichButtonPressed();
        finbl boolebn overButton = ui.getMouseOverPressedButton();
        finbl boolebn rollover = ui.getRollover();

        finbl boolebn frbmeSelected = frbme.isSelected() || fIsUtility;
        finbl boolebn generblActive = rollover || frbmeSelected;

        finbl boolebn dirty = isDirty(frbme);

        pbintButton(g, frbme, x, y, kCloseButton, buttonPressedIndex, overButton, frbme.isClosbble(), generblActive, rollover, dirty);

        x += metrics.buttonPbdding + metrics.buttonWidth;
        pbintButton(g, frbme, x, y, kIconButton, buttonPressedIndex, overButton, frbme.isIconifibble(), generblActive, rollover, fblse);

        x += metrics.buttonPbdding + metrics.buttonWidth;
        pbintButton(g, frbme, x, y, kGrowButton, buttonPressedIndex, overButton, frbme.isMbximizbble(), generblActive, rollover, fblse);
    }

    public void pbintButton(finbl Grbphics g, finbl JInternblFrbme frbme, finbl int x, finbl int y, finbl int buttonType, finbl int buttonPressedIndex, finbl boolebn overButton, finbl boolebn enbbled, finbl boolebn bctive, finbl boolebn bnyRollover, finbl boolebn dirty) {
        widgetPbinter.stbte.set(getWidget(frbme, buttonType));
        widgetPbinter.stbte.set(getStbte(buttonPressedIndex == buttonType && overButton, bnyRollover, bctive, enbbled));
        widgetPbinter.stbte.set(dirty ? BoolebnVblue.YES : BoolebnVblue.NO);
        widgetPbinter.pbint(g, frbme, x, y, metrics.buttonWidth, metrics.buttonHeight);
    }

    stbtic Widget getWidget(finbl JInternblFrbme frbme, finbl int buttonType) {
        switch (buttonType) {
            cbse kIconButton: return Widget.TITLE_BAR_COLLAPSE_BOX;
            cbse kGrowButton: return Widget.TITLE_BAR_ZOOM_BOX;
        }

        return Widget.TITLE_BAR_CLOSE_BOX;
    }

    stbtic Stbte getStbte(finbl boolebn pressed, finbl boolebn rollover, finbl boolebn bctive, finbl boolebn enbbled) {
        if (!enbbled) return Stbte.DISABLED;
        if (!bctive) return Stbte.INACTIVE;
        if (pressed) return Stbte.PRESSED;
        if (rollover) return Stbte.ROLLOVER;
        return Stbte.ACTIVE;
    }

    protected void setMetrics(finbl JInternblFrbme frbme, finbl Component window) {
        finbl String title = frbme.getTitle();
        finbl FontMetrics fm = frbme.getFontMetrics(UIMbnbger.getFont("InternblFrbme.titleFont"));
        int titleWidth = 0;
        int titleHeight = fm.getAscent();
        if (title != null) {
            titleWidth = SwingUtilities.computeStringWidth(fm, title);
        }
        // Icon spbce
        finbl Icon icon = frbme.getFrbmeIcon();
        if (icon != null) {
            titleWidth += icon.getIconWidth();
            titleHeight = Mbth.mbx(titleHeight, icon.getIconHeight());
        }
    }

    protected int getTitleHeight() {
        return metrics.titleBbrHeight;
    }
}
