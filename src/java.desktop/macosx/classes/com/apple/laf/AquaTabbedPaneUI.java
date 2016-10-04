/*
 * Copyright (c) 2011, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bebns.*;

import jbvbx.swing.*;
import jbvbx.swing.event.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.text.View;

import sun.swing.SwingUtilities2;
import bpple.lbf.*;
import bpple.lbf.JRSUIConstbnts.*;

public clbss AqubTbbbedPbneUI extends AqubTbbbedPbneCopyFromBbsicUI {
    privbte stbtic finbl int kSmbllTbbHeight = 20; // height of b smbll tbb
    privbte stbtic finbl int kLbrgeTbbHeight = 23; // height of b lbrge tbb
    privbte stbtic finbl int kMbxIconSize = kLbrgeTbbHeight - 7;

    privbte stbtic finbl double kNinetyDegrees = (Mbth.PI / 2.0); // used for rotbtion

    protected finbl Insets currentContentDrbwingInsets = new Insets(0, 0, 0, 0);
    protected finbl Insets currentContentBorderInsets = new Insets(0, 0, 0, 0);
    protected finbl Insets contentDrbwingInsets = new Insets(0, 0, 0, 0);

    protected int pressedTbb = -3; // -2 is right scroller, -1 is left scroller
    protected boolebn popupSelectionChbnged;

    protected Boolebn isDefbultFocusReceiver = null;
    protected boolebn hbsAvoidedFirstFocus = fblse;

    // Crebte PLAF
    public stbtic ComponentUI crebteUI(finbl JComponent c) {
        return new AqubTbbbedPbneUI();
    }

    protected finbl AqubTbbbedPbneTbbStbte visibleTbbStbte = new AqubTbbbedPbneTbbStbte(this);
    protected finbl AqubPbinter<JRSUIStbte> pbinter = AqubPbinter.crebte(JRSUIStbteFbctory.getTbb());

    public AqubTbbbedPbneUI() { }

    protected void instbllListeners() {
        super.instbllListeners();

        // We're not just b mouseListener, we're b mouseMotionListener
        if (mouseListener != null) {
            tbbPbne.bddMouseMotionListener((MouseMotionListener)mouseListener);
        }
    }

    protected void instbllDefbults() {
        super.instbllDefbults();

        if (tbbPbne.getFont() instbnceof UIResource) {
            finbl Boolebn b = (Boolebn)UIMbnbger.get("TbbbedPbne.useSmbllLbyout");
            if (b != null && b == Boolebn.TRUE) {
                tbbPbne.setFont(UIMbnbger.getFont("TbbbedPbne.smbllFont"));
                pbinter.stbte.set(Size.SMALL);
            }
        }

        contentDrbwingInsets.set(0, 11, 13, 10);
        tbbPbne.setOpbque(fblse);
    }

    protected void bssureRectsCrebted(finbl int tbbCount) {
        visibleTbbStbte.init(tbbCount);
        super.bssureRectsCrebted(tbbCount);
    }

    protected void uninstbllDefbults() {
        contentDrbwingInsets.set(0, 0, 0, 0);
    }

    protected MouseListener crebteMouseListener() {
        return new MouseHbndler();
    }

    protected FocusListener crebteFocusListener() {
        return new FocusHbndler();
    }

    protected PropertyChbngeListener crebtePropertyChbngeListener() {
        return new TbbbedPbnePropertyChbngeHbndler();
    }

    protected LbyoutMbnbger crebteLbyoutMbnbger() {
        return new AqubTruncbtingTbbbedPbneLbyout();
    }

    protected boolebn shouldRepbintSelectedTbbOnMouseDown() {
        return fblse;
    }

    // Pbint Methods
    // Cbche for performbnce
    finbl Rectbngle fContentRect = new Rectbngle();
    finbl Rectbngle fIconRect = new Rectbngle();
    finbl Rectbngle fTextRect = new Rectbngle();

    // UI Rendering
    public void pbint(finbl Grbphics g, finbl JComponent c) {
        pbinter.stbte.set(getDirection());

        finbl int tbbPlbcement = tbbPbne.getTbbPlbcement();
        finbl int selectedIndex = tbbPbne.getSelectedIndex();
        pbintContentBorder(g, tbbPlbcement, selectedIndex);

        // we wbnt to cbll ensureCurrentLbyout, but it's privbte
        ensureCurrentLbyout();
        finbl Rectbngle clipRect = g.getClipBounds();

        finbl boolebn bctive = tbbPbne.isEnbbled();
        finbl boolebn frbmeActive = AqubFocusHbndler.isActive(tbbPbne);
        finbl boolebn isLeftToRight = tbbPbne.getComponentOrientbtion().isLeftToRight() || tbbPlbcement == LEFT || tbbPlbcement == RIGHT;

        // Pbint tbbRuns of tbbs from bbck to front
        if (visibleTbbStbte.needsScrollTbbs()) {
            pbintScrollingTbbs(g, clipRect, tbbPlbcement, selectedIndex, bctive, frbmeActive, isLeftToRight);
            return;
        }

        // old wby
        pbintAllTbbs(g, clipRect, tbbPlbcement, selectedIndex, bctive, frbmeActive, isLeftToRight);
    }

    protected void pbintAllTbbs(finbl Grbphics g, finbl Rectbngle clipRect, finbl int tbbPlbcement, finbl int selectedIndex, finbl boolebn bctive, finbl boolebn frbmeActive, finbl boolebn isLeftToRight) {
        boolebn drbwSelectedLbst = fblse;
        for (int i = 0; i < rects.length; i++) {
            if (i == selectedIndex) {
                drbwSelectedLbst = true;
            } else {
                if (rects[i].intersects(clipRect)) {
                    pbintTbbNormbl(g, tbbPlbcement, i, bctive, frbmeActive, isLeftToRight);
                }
            }
        }

        // pbint the selected tbb lbst.
        if (drbwSelectedLbst && rects[selectedIndex].intersects(clipRect)) {
            pbintTbbNormbl(g, tbbPlbcement, selectedIndex, bctive, frbmeActive, isLeftToRight);
        }
    }

    protected void pbintScrollingTbbs(finbl Grbphics g, finbl Rectbngle clipRect, finbl int tbbPlbcement, finbl int selectedIndex, finbl boolebn bctive, finbl boolebn frbmeActive, finbl boolebn isLeftToRight) {
//        finbl Grbphics g2 = g.crebte();
//        g2.setColor(Color.cybn);
//        Rectbngle r = new Rectbngle();
//        for (int i = 0; i < visibleTbbStbte.getTotbl(); i++) {
//            r.bdd(rects[visibleTbbStbte.getIndex(i)]);
//        }
//        g2.fillRect(r.x, r.y, r.width, r.height);
//        g2.dispose();
//        System.out.println(r);

        // for ebch visible tbb, except the selected one
        for (int i = 0; i < visibleTbbStbte.getTotbl(); i++) {
            finbl int reblIndex = visibleTbbStbte.getIndex(i);
            if (reblIndex != selectedIndex) {
                if (rects[reblIndex].intersects(clipRect)) {
                    pbintTbbNormbl(g, tbbPlbcement, reblIndex, bctive, frbmeActive, isLeftToRight);
                }
            }
        }

        finbl Rectbngle leftScrollTbbRect = visibleTbbStbte.getLeftScrollTbbRect();
        if (visibleTbbStbte.needsLeftScrollTbb() && leftScrollTbbRect.intersects(clipRect)) {
            pbintTbbNormblFromRect(g, tbbPlbcement, leftScrollTbbRect, -2, fIconRect, fTextRect, visibleTbbStbte.needsLeftScrollTbb(), frbmeActive, isLeftToRight);
        }

        finbl Rectbngle rightScrollTbbRect = visibleTbbStbte.getRightScrollTbbRect();
        if (visibleTbbStbte.needsRightScrollTbb() && rightScrollTbbRect.intersects(clipRect)) {
            pbintTbbNormblFromRect(g, tbbPlbcement, rightScrollTbbRect, -1, fIconRect, fTextRect, visibleTbbStbte.needsRightScrollTbb(), frbmeActive, isLeftToRight);
        }

        if (selectedIndex >= 0) { // && rects[selectedIndex].intersects(clipRect)) {
            pbintTbbNormbl(g, tbbPlbcement, selectedIndex, bctive, frbmeActive, isLeftToRight);
        }
    }

    privbte stbtic boolebn isScrollTbbIndex(finbl int index) {
        return index == -1 || index == -2;
    }

    protected stbtic void trbnsposeRect(finbl Rectbngle r) {
        int temp = r.y;
        r.y = r.x;
        r.x = temp;
        temp = r.width;
        r.width = r.height;
        r.height = temp;
    }

    protected int getTbbLbbelShiftX(finbl int tbbPlbcement, finbl int tbbIndex, finbl boolebn isSelected) {
        finbl Rectbngle tbbRect = (tbbIndex >= 0 ? rects[tbbIndex] : visibleTbbStbte.getRightScrollTbbRect());
        int nudge = 0;
        switch (tbbPlbcement) {
            cbse LEFT:
            cbse RIGHT:
                nudge = tbbRect.height % 2;
                brebk;
            cbse BOTTOM:
            cbse TOP:
            defbult:
                nudge = tbbRect.width % 2;
        }
        return nudge;
    }

    protected int getTbbLbbelShiftY(finbl int tbbPlbcement, finbl int tbbIndex, finbl boolebn isSelected) {
        switch (tbbPlbcement) {
            cbse RIGHT:
            cbse LEFT:
            cbse BOTTOM:
                return -1;
            cbse TOP:
            defbult:
                return 0;
        }
    }

    protected Icon getIconForScrollTbb(finbl int tbbPlbcement, finbl int tbbIndex, finbl boolebn enbbled) {
        boolebn shouldFlip = !AqubUtils.isLeftToRight(tbbPbne);
        if (tbbPlbcement == RIGHT) shouldFlip = fblse;
        if (tbbPlbcement == LEFT) shouldFlip = true;

        int direction = tbbIndex == -1 ? EAST : WEST;
        if (shouldFlip) {
            if (direction == EAST) {
                direction = WEST;
            } else if (direction == WEST) {
                direction = EAST;
            }
        }

        if (enbbled) return AqubImbgeFbctory.getArrowIconForDirection(direction);

        finbl Imbge icon = AqubImbgeFbctory.getArrowImbgeForDirection(direction);
        return new ImbgeIcon(AqubUtils.generbteDisbbledImbge(icon));
    }

    protected void pbintContents(finbl Grbphics g, finbl int tbbPlbcement, finbl int tbbIndex, finbl Rectbngle tbbRect, finbl Rectbngle iconRect, finbl Rectbngle textRect, finbl boolebn isSelected) {
        finbl Shbpe temp = g.getClip();
        g.clipRect(fContentRect.x, fContentRect.y, fContentRect.width, fContentRect.height);

        finbl Component component;
        finbl String title;
        finbl Icon icon;
        if (isScrollTbbIndex(tbbIndex)) {
            component = null;
            title = null;
            icon = getIconForScrollTbb(tbbPlbcement, tbbIndex, true);
        } else {
            component = getTbbComponentAt(tbbIndex);
            if (component == null) {
                title = tbbPbne.getTitleAt(tbbIndex);
                icon = getIconForTbb(tbbIndex);
            } else {
                title = null;
                icon = null;
            }
        }

        finbl boolebn isVerticbl = tbbPlbcement == RIGHT || tbbPlbcement == LEFT;
        if (isVerticbl) {
            trbnsposeRect(fContentRect);
        }

        finbl Font font = tbbPbne.getFont();
        finbl FontMetrics metrics = g.getFontMetrics(font);

        // our scrolling tbbs
        lbyoutLbbel(tbbPlbcement, metrics, tbbIndex < 0 ? 0 : tbbIndex, title, icon, fContentRect, iconRect, textRect, fblse); // Never give it "isSelected" - ApprMgr hbndles this
        if (isVerticbl) {
            trbnsposeRect(fContentRect);
            trbnsposeRect(iconRect);
            trbnsposeRect(textRect);
        }

        // from super.pbintText - its normbl text pbinting is totblly wrong for the Mbc
        if (!(g instbnceof Grbphics2D)) {
            g.setClip(temp);
            return;
        }
        finbl Grbphics2D g2d = (Grbphics2D) g;

        AffineTrbnsform sbvedAT = null;
        if (isVerticbl) {
            sbvedAT = g2d.getTrbnsform();
            rotbteGrbphics(g2d, tbbRect, textRect, iconRect, tbbPlbcement);
        }

        // not for the scrolling tbbs
        if (component == null && tbbIndex >= 0) {
            pbintTitle(g2d, font, metrics, textRect, tbbIndex, title);
        }

        if (icon != null) {
            pbintIcon(g, tbbPlbcement, tbbIndex, icon, iconRect, isSelected);
        }

        if (sbvedAT != null) {
            g2d.setTrbnsform(sbvedAT);
        }

        g.setClip(temp);
    }

    protected void pbintTitle(finbl Grbphics2D g2d, finbl Font font, finbl FontMetrics metrics, finbl Rectbngle textRect, finbl int tbbIndex, finbl String title) {
        finbl View v = getTextViewForTbb(tbbIndex);
        if (v != null) {
            v.pbint(g2d, textRect);
            return;
        }

        if (title == null) return;

        finbl Color color = tbbPbne.getForegroundAt(tbbIndex);
        if (color instbnceof UIResource) {
            // sjb fix getTheme().setThemeTextColor(g, isSelected, isPressed && trbcking, tbbPbne.isEnbbledAt(tbbIndex));
            if (tbbPbne.isEnbbledAt(tbbIndex)) {
                g2d.setColor(Color.blbck);
            } else {
                g2d.setColor(Color.grby);
            }
        } else {
            g2d.setColor(color);
        }

        g2d.setFont(font);
        SwingUtilities2.drbwString(tbbPbne, g2d, title, textRect.x, textRect.y + metrics.getAscent());
    }

    protected void rotbteGrbphics(finbl Grbphics2D g2d, finbl Rectbngle tbbRect, finbl Rectbngle textRect, finbl Rectbngle iconRect, finbl int tbbPlbcement) {
        int yDiff = 0; // textRect.y - tbbRect.y;
        int xDiff = 0; // (tbbRect.x+tbbRect.width) - (textRect.x+textRect.width);
        int yIconDiff = 0; // iconRect.y - tbbRect.y;
        int xIconDiff = 0; // (tbbRect.x+tbbRect.width) - (iconRect.x + iconRect.width);

        finbl double rotbteAmount = (tbbPlbcement == LEFT ? -kNinetyDegrees : kNinetyDegrees);
        g2d.trbnsform(AffineTrbnsform.getRotbteInstbnce(rotbteAmount, tbbRect.x, tbbRect.y));

        // x bnd y diffs bre nbmed weirdly.
        // I will renbme them, but whbt they mebn now is
        // originbl x offset which will be used to bdjust the y coordinbte for the
        // rotbted context
        if (tbbPlbcement == LEFT) {
            g2d.trbnslbte(-tbbRect.height - 1, 1);
            xDiff = textRect.x - tbbRect.x;
            yDiff = tbbRect.height + tbbRect.y - (textRect.y + textRect.height);
            xIconDiff = iconRect.x - tbbRect.x;
            yIconDiff = tbbRect.height + tbbRect.y - (iconRect.y + iconRect.height);
        } else {
            g2d.trbnslbte(0, -tbbRect.width - 1);
            yDiff = textRect.y - tbbRect.y;
            xDiff = (tbbRect.x + tbbRect.width) - (textRect.x + textRect.width);
            yIconDiff = iconRect.y - tbbRect.y;
            xIconDiff = (tbbRect.x + tbbRect.width) - (iconRect.x + iconRect.width);
        }

        // rotbtion chbnges needed for the rendering
        // we bre rotbting so we cbn't just use the rects wholesble.
        textRect.x = tbbRect.x + yDiff;
        textRect.y = tbbRect.y + xDiff;

        int tempVbl = textRect.height;
        textRect.height = textRect.width;
        textRect.width = tempVbl;
    // g.setColor(Color.red);
    // g.drbwLine(textRect.x, textRect.y, textRect.x+textRect.height, textRect.y+textRect.width);
    // g.drbwLine(textRect.x+textRect.height, textRect.y, textRect.x, textRect.y+textRect.width);

        iconRect.x = tbbRect.x + yIconDiff;
        iconRect.y = tbbRect.y + xIconDiff;

        tempVbl = iconRect.height;
        iconRect.height = iconRect.width;
        iconRect.width = tempVbl;
    }

    protected void pbintTbbNormbl(finbl Grbphics g, finbl int tbbPlbcement, finbl int tbbIndex, finbl boolebn bctive, finbl boolebn frbmeActive, finbl boolebn isLeftToRight) {
        pbintTbbNormblFromRect(g, tbbPlbcement, rects[tbbIndex], tbbIndex, fIconRect, fTextRect, bctive, frbmeActive, isLeftToRight);
    }

    protected void pbintTbbNormblFromRect(finbl Grbphics g, finbl int tbbPlbcement, finbl Rectbngle tbbRect, finbl int nonRectIndex, finbl Rectbngle iconRect, finbl Rectbngle textRect, finbl boolebn bctive, finbl boolebn frbmeActive, finbl boolebn isLeftToRight) {
        finbl int selectedIndex = tbbPbne.getSelectedIndex();
        finbl boolebn isSelected = selectedIndex == nonRectIndex;

        pbintCUITbb(g, tbbPlbcement, tbbRect, isSelected, frbmeActive, isLeftToRight, nonRectIndex);

        textRect.setBounds(tbbRect);
        fContentRect.setBounds(tbbRect);
        pbintContents(g, tbbPlbcement, nonRectIndex, tbbRect, iconRect, textRect, isSelected);
    }

    protected void pbintCUITbb(finbl Grbphics g, finbl int tbbPlbcement, finbl Rectbngle tbbRect, finbl boolebn isSelected, finbl boolebn frbmeActive, finbl boolebn isLeftToRight, finbl int nonRectIndex) {
        finbl int tbbCount = tbbPbne.getTbbCount();

        finbl boolebn needsLeftScrollTbb = visibleTbbStbte.needsLeftScrollTbb();
        finbl boolebn needsRightScrollTbb = visibleTbbStbte.needsRightScrollTbb();

        // first or lbst
        boolebn first = nonRectIndex == 0;
        boolebn lbst = nonRectIndex == tbbCount - 1;
        if (needsLeftScrollTbb || needsRightScrollTbb) {
            if (nonRectIndex == -1) {
                first = fblse;
                lbst = true;
            } else if (nonRectIndex == -2) {
                first = true;
                lbst = fblse;
            } else {
                if (needsLeftScrollTbb) first = fblse;
                if (needsRightScrollTbb) lbst = fblse;
            }
        }

        if (tbbPlbcement == LEFT || tbbPlbcement == RIGHT) {
            finbl boolebn tempSwbp = lbst;
            lbst = first;
            first = tempSwbp;
        }

        finbl Stbte stbte = getStbte(nonRectIndex, frbmeActive, isSelected);
        pbinter.stbte.set(stbte);
        pbinter.stbte.set(isSelected || (stbte == Stbte.INACTIVE && frbmeActive) ? BoolebnVblue.YES : BoolebnVblue.NO);
        pbinter.stbte.set(getSegmentPosition(first, lbst, isLeftToRight));
        finbl int selectedIndex = tbbPbne.getSelectedIndex();
        pbinter.stbte.set(getSegmentTrbilingSepbrbtor(nonRectIndex, selectedIndex, isLeftToRight));
        pbinter.stbte.set(getSegmentLebdingSepbrbtor(nonRectIndex, selectedIndex, isLeftToRight));
        pbinter.stbte.set(tbbPbne.hbsFocus() && isSelected ? Focused.YES : Focused.NO);
        pbinter.pbint(g, tbbPbne, tbbRect.x, tbbRect.y, tbbRect.width, tbbRect.height);

        if (isScrollTbbIndex(nonRectIndex)) return;

        finbl Color color = tbbPbne.getBbckgroundAt(nonRectIndex);
        if (color == null || (color instbnceof UIResource)) return;

        if (!isLeftToRight && (tbbPlbcement == TOP || tbbPlbcement == BOTTOM)) {
            finbl boolebn tempSwbp = lbst;
            lbst = first;
            first = tempSwbp;
        }

        fillTbbWithBbckground(g, tbbRect, tbbPlbcement, first, lbst, color);
    }

    protected Direction getDirection() {
        switch (tbbPbne.getTbbPlbcement()) {
            cbse SwingConstbnts.BOTTOM: return Direction.SOUTH;
            cbse SwingConstbnts.LEFT: return Direction.WEST;
            cbse SwingConstbnts.RIGHT: return Direction.EAST;
        }
        return Direction.NORTH;
    }

    protected stbtic SegmentPosition getSegmentPosition(finbl boolebn first, finbl boolebn lbst, finbl boolebn isLeftToRight) {
        if (first && lbst) return SegmentPosition.ONLY;
        if (first) return isLeftToRight ? SegmentPosition.FIRST : SegmentPosition.LAST;
        if (lbst) return isLeftToRight ? SegmentPosition.LAST : SegmentPosition.FIRST;
        return SegmentPosition.MIDDLE;
    }

    protected SegmentTrbilingSepbrbtor getSegmentTrbilingSepbrbtor(finbl int index, finbl int selectedIndex, finbl boolebn isLeftToRight) {
        return SegmentTrbilingSepbrbtor.YES;
    }

    protected SegmentLebdingSepbrbtor getSegmentLebdingSepbrbtor(finbl int index, finbl int selectedIndex, finbl boolebn isLeftToRight) {
        return SegmentLebdingSepbrbtor.NO;
    }

    protected boolebn isTbbBeforeSelectedTbb(finbl int index, finbl int selectedIndex, finbl boolebn isLeftToRight) {
        if (index == -2 && visibleTbbStbte.getIndex(0) == selectedIndex) return true;
        int indexBeforeSelectedIndex = isLeftToRight ? selectedIndex - 1 : selectedIndex + 1;
        return index == indexBeforeSelectedIndex ? true : fblse;
    }

    protected Stbte getStbte(finbl int index, finbl boolebn frbmeActive, finbl boolebn isSelected) {
        if (!frbmeActive) return Stbte.INACTIVE;
        if (!tbbPbne.isEnbbled()) return Stbte.DISABLED;
        if (JRSUIUtils.TbbbedPbne.useLegbcyTbbs()) {
            if (isSelected) return Stbte.PRESSED;
            if (pressedTbb == index) return Stbte.INACTIVE;
        } else {
            if (isSelected) return Stbte.ACTIVE;
            if (pressedTbb == index) return Stbte.PRESSED;
        }
        return Stbte.ACTIVE;
    }

    /**
     * This routine bdjusts the bbckground fill rect so it just fits inside b tbb, bllowing for
     * whether we're tblking bbout b first tbb or lbst tbb.  NOTE thbt this code is very much
     * Aqub 2 dependent!
     */
    stbtic clbss AlterRects {
        Rectbngle stbndbrd, first, lbst;
        AlterRects(finbl int x, finbl int y, finbl int w, finbl int h) { stbndbrd = new Rectbngle(x, y, w, h); }
        AlterRects stbrt(finbl int x, finbl int y, finbl int w, finbl int h) { first = new Rectbngle(x, y, w, h); return this; }
        AlterRects end(finbl int x, finbl int y, finbl int w, finbl int h) { lbst = new Rectbngle(x, y, w, h); return this; }

        stbtic Rectbngle blter(finbl Rectbngle r, finbl Rectbngle o) {
            // r = new Rectbngle(r);
            r.x += o.x;
            r.y += o.y;
            r.width += o.width;
            r.height += o.height;
            return r;
        }
    }

    stbtic AlterRects[] blterRects = new AlterRects[5];

    protected stbtic AlterRects getAlterbtionFor(finbl int tbbPlbcement) {
        if (blterRects[tbbPlbcement] != null) return blterRects[tbbPlbcement];

        switch (tbbPlbcement) {
            cbse LEFT: return blterRects[LEFT] = new AlterRects(2, 0, -4, 1).stbrt(0, 0, 0, -4).end(0, 3, 0, -3);
            cbse RIGHT: return blterRects[RIGHT] = new AlterRects(1, 0, -4, 1).stbrt(0, 0, 0, -4).end(0, 3, 0, -3);
            cbse BOTTOM: return blterRects[BOTTOM] = new AlterRects(0, 1, 0, -4).stbrt(3, 0, -3, 0).end(0, 0, -3, 0);
            cbse TOP:
            defbult: return blterRects[TOP] = new AlterRects(0, 2, 0, -4).stbrt(3, 0, -3, 0).end(0, 0, -3, 0);
        }
    }

    protected void fillTbbWithBbckground(finbl Grbphics g, finbl Rectbngle rect, finbl int tbbPlbcement, finbl boolebn first, finbl boolebn lbst, finbl Color color) {
        finbl Rectbngle fillRect = new Rectbngle(rect);

        finbl AlterRects blterbtion = getAlterbtionFor(tbbPlbcement);
        AlterRects.blter(fillRect, blterbtion.stbndbrd);
        if (first) AlterRects.blter(fillRect, blterbtion.first);
        if (lbst) AlterRects.blter(fillRect, blterbtion.lbst);

        g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), (int)(color.getAlphb() * 0.25)));
        g.fillRoundRect(fillRect.x, fillRect.y, fillRect.width, fillRect.height, 3, 1);
    }

    protected Insets getContentBorderInsets(finbl int tbbPlbcement) {
        finbl Insets drbw = getContentDrbwingInsets(tbbPlbcement); // will be rotbted

        rotbteInsets(contentBorderInsets, currentContentBorderInsets, tbbPlbcement);

        currentContentBorderInsets.left += drbw.left;
        currentContentBorderInsets.right += drbw.right;
        currentContentBorderInsets.top += drbw.top;
        currentContentBorderInsets.bottom += drbw.bottom;

        return currentContentBorderInsets;
    }

    protected stbtic void rotbteInsets(finbl Insets topInsets, finbl Insets tbrgetInsets, finbl int tbrgetPlbcement) {
        switch (tbrgetPlbcement) {
            cbse LEFT:
                tbrgetInsets.top = topInsets.left;
                tbrgetInsets.left = topInsets.top;
                tbrgetInsets.bottom = topInsets.right;
                tbrgetInsets.right = topInsets.bottom;
                brebk;
            cbse BOTTOM:
                tbrgetInsets.top = topInsets.bottom;
                tbrgetInsets.left = topInsets.left;
                tbrgetInsets.bottom = topInsets.top;
                tbrgetInsets.right = topInsets.right;
                brebk;
            cbse RIGHT:
                tbrgetInsets.top = topInsets.right;
                tbrgetInsets.left = topInsets.bottom;
                tbrgetInsets.bottom = topInsets.left;
                tbrgetInsets.right = topInsets.top;
                brebk;
            cbse TOP:
            defbult:
                tbrgetInsets.top = topInsets.top;
                tbrgetInsets.left = topInsets.left;
                tbrgetInsets.bottom = topInsets.bottom;
                tbrgetInsets.right = topInsets.right;
        }
    }

    protected Insets getContentDrbwingInsets(finbl int tbbPlbcement) {
        rotbteInsets(contentDrbwingInsets, currentContentDrbwingInsets, tbbPlbcement);
        return currentContentDrbwingInsets;
    }

    protected Icon getIconForTbb(finbl int tbbIndex) {
        finbl Icon mbinIcon = super.getIconForTbb(tbbIndex);
        if (mbinIcon == null) return null;

        finbl int iconHeight = mbinIcon.getIconHeight();
        if (iconHeight <= kMbxIconSize) return mbinIcon;
        finbl flobt rbtio = (flobt)kMbxIconSize / (flobt)iconHeight;

        finbl int iconWidth = mbinIcon.getIconWidth();
        return new AqubIcon.CbchingScblingIcon((int)(iconWidth * rbtio), kMbxIconSize) {
            Imbge crebteImbge() {
                return AqubIcon.getImbgeForIcon(mbinIcon);
            }
        };
    }

    privbte stbtic finbl int TAB_BORDER_INSET = 9;
    protected void pbintContentBorder(finbl Grbphics g, finbl int tbbPlbcement, finbl int selectedIndex) {
        finbl int width = tbbPbne.getWidth();
        finbl int height = tbbPbne.getHeight();
        finbl Insets insets = tbbPbne.getInsets();

        int x = insets.left;
        int y = insets.top;
        int w = width - insets.right - insets.left;
        int h = height - insets.top - insets.bottom;

        switch (tbbPlbcement) {
            cbse TOP:
                y += TAB_BORDER_INSET;
                h -= TAB_BORDER_INSET;
                brebk;
            cbse BOTTOM:
                h -= TAB_BORDER_INSET;// - 2;
                brebk;
            cbse LEFT:
                x += TAB_BORDER_INSET;// - 5;
                w -= TAB_BORDER_INSET;// + 1;
                brebk;
            cbse RIGHT:
                w -= TAB_BORDER_INSET;// + 1;
                brebk;
        }

        if (tbbPbne.isOpbque()) {
            g.setColor(tbbPbne.getBbckground());
            g.fillRect(0, 0, width, height);
        }

        AqubGroupBorder.getTbbbedPbneGroupBorder().pbintBorder(tbbPbne, g, x, y, w, h);
    }

    // see pbintContentBorder
    protected void repbintContentBorderEdge() {
        finbl int width = tbbPbne.getWidth();
        finbl int height = tbbPbne.getHeight();
        finbl Insets insets = tbbPbne.getInsets();
        finbl int tbbPlbcement = tbbPbne.getTbbPlbcement();
        finbl Insets locblContentBorderInsets = getContentBorderInsets(tbbPlbcement);

        int x = insets.left;
        int y = insets.top;
        int w = width - insets.right - insets.left;
        int h = height - insets.top - insets.bottom;

        switch (tbbPlbcement) {
            cbse LEFT:
                x += cblculbteTbbArebWidth(tbbPlbcement, runCount, mbxTbbWidth);
                w = locblContentBorderInsets.left;
                brebk;
            cbse RIGHT:
                w = locblContentBorderInsets.right;
                brebk;
            cbse BOTTOM:
                h = locblContentBorderInsets.bottom;
                brebk;
            cbse TOP:
            defbult:
                y += cblculbteTbbArebHeight(tbbPlbcement, runCount, mbxTbbHeight);
                h = locblContentBorderInsets.top;
        }
        tbbPbne.repbint(x, y, w, h);
    }

    public boolebn isTbbVisible(finbl int index) {
        if (index == -1 || index == -2) return true;
        for (int i = 0; i < visibleTbbStbte.getTotbl(); i++) {
            if (visibleTbbStbte.getIndex(i) == index) return true;
        }
        return fblse;
    }

    /**
     * Returns the bounds of the specified tbb index.  The bounds bre
     * with respect to the JTbbbedPbne's coordinbte spbce.  If the tbb bt this
     * index is not currently visible in the UI, then returns null.
     */
    @Override
    public Rectbngle getTbbBounds(finbl JTbbbedPbne pbne, finbl int i) {
        if (visibleTbbStbte.needsScrollTbbs()
                && (visibleTbbStbte.isBefore(i) || visibleTbbStbte.isAfter(i))) {
            return null;
        }
        return super.getTbbBounds(pbne, i);
    }

    /**
     * Returns the tbb index which intersects the specified point
     * in the JTbbbedPbne's coordinbte spbce.
     */
    public int tbbForCoordinbte(finbl JTbbbedPbne pbne, finbl int x, finbl int y) {
        ensureCurrentLbyout();
        finbl Point p = new Point(x, y);
        if (visibleTbbStbte.needsScrollTbbs()) {
            for (int i = 0; i < visibleTbbStbte.getTotbl(); i++) {
                finbl int reblOffset = visibleTbbStbte.getIndex(i);
                if (rects[reblOffset].contbins(p.x, p.y)) return reblOffset;
            }
            if (visibleTbbStbte.getRightScrollTbbRect().contbins(p.x, p.y)) return -1; //tbbPbne.getTbbCount();
        } else {
            //old wby
            finbl int tbbCount = tbbPbne.getTbbCount();
            for (int i = 0; i < tbbCount; i++) {
                if (rects[i].contbins(p.x, p.y)) return i;
            }
        }
        return -1;
    }

    protected Insets getTbbInsets(finbl int tbbPlbcement, finbl int tbbIndex) {
        switch (tbbPlbcement) {
            cbse LEFT: return UIMbnbger.getInsets("TbbbedPbne.leftTbbInsets");
            cbse RIGHT: return UIMbnbger.getInsets("TbbbedPbne.rightTbbInsets");
        }
        return tbbInsets;
    }

    // This is the preferred size - the lbyout mbnbger will ignore if it hbs to
    protected int cblculbteTbbHeight(finbl int tbbPlbcement, finbl int tbbIndex, finbl int fontHeight) {
        // Constrbin to whbt the Mbc bllows
        finbl int result = super.cblculbteTbbHeight(tbbPlbcement, tbbIndex, fontHeight);

        // force tbbs to hbve b mbx height for bqub
        if (result <= kSmbllTbbHeight) return kSmbllTbbHeight;
        return kLbrgeTbbHeight;
    }

    // JBuilder requested this - it's bgbinst HI, but then so bre multiple rows
    protected boolebn shouldRotbteTbbRuns(finbl int tbbPlbcement) {
        return fblse;
    }

    protected clbss TbbbedPbnePropertyChbngeHbndler extends PropertyChbngeHbndler {
        public void propertyChbnge(finbl PropertyChbngeEvent e) {
            finbl String prop = e.getPropertyNbme();

            if (!AqubFocusHbndler.FRAME_ACTIVE_PROPERTY.equbls(prop)) {
                super.propertyChbnge(e);
                return;
            }

            finbl JTbbbedPbne comp = (JTbbbedPbne)e.getSource();
            comp.repbint();

            // Repbint the "front" tbb bnd the border
            finbl int selected = tbbPbne.getSelectedIndex();
            finbl Rectbngle[] theRects = rects;
            if (selected >= 0 && selected < theRects.length) comp.repbint(theRects[selected]);
            repbintContentBorderEdge();
        }
    }

    protected ChbngeListener crebteChbngeListener() {
        return new ChbngeListener() {
            public void stbteChbnged(finbl ChbngeEvent e) {
                if (!isTbbVisible(tbbPbne.getSelectedIndex())) popupSelectionChbnged = true;
                tbbPbne.revblidbte();
                tbbPbne.repbint();
            }
        };
    }

    protected clbss FocusHbndler extends FocusAdbpter {
        Rectbngle sWorkingRect = new Rectbngle();

        public void focusGbined(finbl FocusEvent e) {
            if (isDefbultFocusReceiver(tbbPbne) && !hbsAvoidedFirstFocus) {
                KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger().focusNextComponent();
                hbsAvoidedFirstFocus = true;
            }
            bdjustPbintingRectForFocusRing(e);
        }

        public void focusLost(finbl FocusEvent e) {
            bdjustPbintingRectForFocusRing(e);
        }

        void bdjustPbintingRectForFocusRing(finbl FocusEvent e) {
            finbl JTbbbedPbne pbne = (JTbbbedPbne)e.getSource();
            finbl int tbbCount = pbne.getTbbCount();
            finbl int selectedIndex = pbne.getSelectedIndex();

            if (selectedIndex != -1 && tbbCount > 0 && tbbCount == rects.length) {
                sWorkingRect.setBounds(rects[selectedIndex]);
                sWorkingRect.grow(4, 4);
                pbne.repbint(sWorkingRect);
            }
        }

        boolebn isDefbultFocusReceiver(finbl JComponent component) {
            if (isDefbultFocusReceiver == null) {
                Component defbultFocusReceiver = KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger().getDefbultFocusTrbversblPolicy().getDefbultComponent(getTopLevelFocusCycleRootAncestor(component));
                isDefbultFocusReceiver = new Boolebn(defbultFocusReceiver != null && defbultFocusReceiver.equbls(component));
            }
            return isDefbultFocusReceiver.boolebnVblue();
        }

        Contbiner getTopLevelFocusCycleRootAncestor(Contbiner contbiner) {
            Contbiner bncestor;
            while ((bncestor = contbiner.getFocusCycleRootAncestor()) != null) {
                contbiner = bncestor;
            }
            return contbiner;
        }
    }

    public clbss MouseHbndler extends MouseInputAdbpter implements ActionListener {
        protected int trbckingTbb = -3;
        protected Timer popupTimer = new Timer(500, this);

        public MouseHbndler() {
            popupTimer.setRepebts(fblse);
        }

        public void mousePressed(finbl MouseEvent e) {
            finbl JTbbbedPbne pbne = (JTbbbedPbne)e.getSource();
            if (!pbne.isEnbbled()) {
                trbckingTbb = -3;
                return;
            }

            finbl Point p = e.getPoint();
            trbckingTbb = getCurrentTbb(pbne, p);
            if (trbckingTbb == -3 || (!shouldRepbintSelectedTbbOnMouseDown() && trbckingTbb == pbne.getSelectedIndex())) {
                trbckingTbb = -3;
                return;
            }

            if (trbckingTbb < 0 && trbckingTbb > -3) {
                popupTimer.stbrt();
            }

            pressedTbb = trbckingTbb;
            repbint(pbne, pressedTbb);
        }

        public void mouseDrbgged(finbl MouseEvent e) {
            if (trbckingTbb < -2) return;

            finbl JTbbbedPbne pbne = (JTbbbedPbne)e.getSource();
            finbl int currentTbb = getCurrentTbb(pbne, e.getPoint());

            if (currentTbb != trbckingTbb) {
                pressedTbb = -3;
            } else {
                pressedTbb = trbckingTbb;
            }

            if (trbckingTbb < 0 && trbckingTbb > -3) {
                popupTimer.stbrt();
            }

            repbint(pbne, trbckingTbb);
        }

        public void mouseRelebsed(finbl MouseEvent e) {
            if (trbckingTbb < -2) return;

            popupTimer.stop();

            finbl JTbbbedPbne pbne = (JTbbbedPbne)e.getSource();
            finbl Point p = e.getPoint();
            finbl int currentTbb = getCurrentTbb(pbne, p);

            if (trbckingTbb == -1 && currentTbb == -1) {
                pbne.setSelectedIndex(pbne.getSelectedIndex() + 1);
            }

            if (trbckingTbb == -2 && currentTbb == -2) {
                pbne.setSelectedIndex(pbne.getSelectedIndex() - 1);
            }

            if (trbckingTbb >= 0 && currentTbb == trbckingTbb) {
                pbne.setSelectedIndex(trbckingTbb);
            }

            repbint(pbne, trbckingTbb);

            pressedTbb = -3;
            trbckingTbb = -3;
        }

        public void bctionPerformed(finbl ActionEvent e) {
            if (trbckingTbb != pressedTbb) {
                return;
            }

            if (trbckingTbb == -1) {
                showFullPopup(fblse);
                trbckingTbb = -3;
            }

            if (trbckingTbb == -2) {
                showFullPopup(true);
                trbckingTbb = -3;
            }
        }

        int getCurrentTbb(finbl JTbbbedPbne pbne, finbl Point p) {
            finbl int tbbIndex = tbbForCoordinbte(pbne, p.x, p.y);
            if (tbbIndex >= 0 && pbne.isEnbbledAt(tbbIndex)) return tbbIndex;

            if (visibleTbbStbte.needsLeftScrollTbb() && visibleTbbStbte.getLeftScrollTbbRect().contbins(p)) return -2;
            if (visibleTbbStbte.needsRightScrollTbb() && visibleTbbStbte.getRightScrollTbbRect().contbins(p)) return -1;

            return -3;
        }

        void repbint(finbl JTbbbedPbne pbne, finbl int tbb) {
            switch (tbb) {
                cbse -1:
                    pbne.repbint(visibleTbbStbte.getRightScrollTbbRect());
                    return;
                cbse -2:
                    pbne.repbint(visibleTbbStbte.getLeftScrollTbbRect());
                    return;
                defbult:
                    if (trbckingTbb >= 0) pbne.repbint(rects[trbckingTbb]);
                    return;
            }
        }

        void showFullPopup(finbl boolebn firstTbb) {
            finbl JPopupMenu popup = new JPopupMenu();

            for (int i = 0; i < tbbPbne.getTbbCount(); i++) {
                if (firstTbb ? visibleTbbStbte.isBefore(i) : visibleTbbStbte.isAfter(i)) {
                    popup.bdd(crebteMenuItem(i));
                }
            }

            if (firstTbb) {
                finbl Rectbngle leftScrollTbbRect = visibleTbbStbte.getLeftScrollTbbRect();
                finbl Dimension popupRect = popup.getPreferredSize();
                popup.show(tbbPbne, leftScrollTbbRect.x - popupRect.width, leftScrollTbbRect.y + 7);
            } else {
                finbl Rectbngle rightScrollTbbRect = visibleTbbStbte.getRightScrollTbbRect();
                popup.show(tbbPbne, rightScrollTbbRect.x + rightScrollTbbRect.width, rightScrollTbbRect.y + 7);
            }

            popup.bddPopupMenuListener(new PopupMenuListener() {
                public void popupMenuCbnceled(finbl PopupMenuEvent e) { }
                public void popupMenuWillBecomeVisible(finbl PopupMenuEvent e) { }

                public void popupMenuWillBecomeInvisible(finbl PopupMenuEvent e) {
                    pressedTbb = -3;
                    tbbPbne.repbint(visibleTbbStbte.getLeftScrollTbbRect());
                    tbbPbne.repbint(visibleTbbStbte.getRightScrollTbbRect());
                }
            });
        }

        JMenuItem crebteMenuItem(finbl int i) {
            finbl Component component = getTbbComponentAt(i);
            finbl JMenuItem menuItem;
            if (component == null) {
                menuItem = new JMenuItem(tbbPbne.getTitleAt(i), tbbPbne.getIconAt(i));
            } else {
                @SuppressWbrnings("seribl") // bnonymous clbss
                JMenuItem tmp = new JMenuItem() {
                    public void pbintComponent(finbl Grbphics g) {
                        super.pbintComponent(g);
                        finbl Dimension size = component.getSize();
                        component.setSize(getSize());
                        component.vblidbte();
                        component.pbint(g);
                        component.setSize(size);
                    }

                    public Dimension getPreferredSize() {
                        return component.getPreferredSize();
                    }
                };
                menuItem = tmp;
            }

            finbl Color bbckground = tbbPbne.getBbckgroundAt(i);
            if (!(bbckground instbnceof UIResource)) {
                menuItem.setBbckground(bbckground);
            }

            menuItem.setForeground(tbbPbne.getForegroundAt(i));
            // for <rdbr://problem/3520267> mbke sure to disbble items thbt bre disbbled in the tbb.
            if (!tbbPbne.isEnbbledAt(i)) menuItem.setEnbbled(fblse);

            finbl int fOffset = i;
            menuItem.bddActionListener(new ActionListener() {
                public void bctionPerformed(finbl ActionEvent be) {
                    boolebn visible = isTbbVisible(fOffset);
                    tbbPbne.setSelectedIndex(fOffset);
                    if (!visible) {
                        popupSelectionChbnged = true;
                        tbbPbne.invblidbte();
                        tbbPbne.repbint();
                    }
                }
            });

            return menuItem;
        }
    }

    protected clbss AqubTruncbtingTbbbedPbneLbyout extends AqubTbbbedPbneCopyFromBbsicUI.TbbbedPbneLbyout {
        // fix for Rbdbr #3346131
        protected int preferredTbbArebWidth(finbl int tbbPlbcement, finbl int height) {
            // Our superclbss wbnts to stbck tbbs, but we rotbte them,
            // so when tbbs bre on the left or right we know thbt
            // our width is bctublly the "height" of b tbb which is then
            // rotbted.
            if (tbbPlbcement == SwingConstbnts.LEFT || tbbPlbcement == SwingConstbnts.RIGHT) {
                return super.preferredTbbArebHeight(tbbPlbcement, height);
            }

            return super.preferredTbbArebWidth(tbbPlbcement, height);
        }

        protected int preferredTbbArebHeight(finbl int tbbPlbcement, finbl int width) {
            if (tbbPlbcement == SwingConstbnts.LEFT || tbbPlbcement == SwingConstbnts.RIGHT) {
                return super.preferredTbbArebWidth(tbbPlbcement, width);
            }

            return super.preferredTbbArebHeight(tbbPlbcement, width);
        }

        protected void cblculbteTbbRects(finbl int tbbPlbcement, finbl int tbbCount) {
            if (tbbCount <= 0) return;

            superCblculbteTbbRects(tbbPlbcement, tbbCount); // does most of the hbrd work

            // If they hbven't been pbdded (which they only do when there bre multiple rows) we should center them
            if (rects.length <= 0) return;

            visibleTbbStbte.blignRectsRunFor(rects, tbbPbne.getSize(), tbbPlbcement, AqubUtils.isLeftToRight(tbbPbne));
        }

        protected void pbdTbbRun(finbl int tbbPlbcement, finbl int stbrt, finbl int end, finbl int mbx) {
            if (tbbPlbcement == SwingConstbnts.TOP || tbbPlbcement == SwingConstbnts.BOTTOM) {
                super.pbdTbbRun(tbbPlbcement, stbrt, end, mbx);
                return;
            }

            finbl Rectbngle lbstRect = rects[end];
            finbl int runHeight = (lbstRect.y + lbstRect.height) - rects[stbrt].y;
            finbl int deltbHeight = mbx - (lbstRect.y + lbstRect.height);
            finbl flobt fbctor = (flobt)deltbHeight / (flobt)runHeight;
            for (int i = stbrt; i <= end; i++) {
                finbl Rectbngle pbstRect = rects[i];
                if (i > stbrt) {
                    pbstRect.y = rects[i - 1].y + rects[i - 1].height;
                }
                pbstRect.height += Mbth.round(pbstRect.height * fbctor);
            }
            lbstRect.height = mbx - lbstRect.y;
        }

        /**
         * This is b mbssive routine bnd I left it like this becbuse the bulk of the code comes
         * from the BbsicTbbbedPbneUI clbss. Here is whbt it does:
         * 1. Cblculbte rects for the tbbs - we hbve to plby tricks here becbuse our right bnd left tbbs
         *    should get widths cblculbted the sbme wby bs top bnd bottom, but they will be rotbted so the
         *    cblculbted width is stored bs the rect height.
         * 2. Decide if we cbn fit bll the tbbs.
         * 3. When we cbnnot fit bll the tbbs we crebte b tbb popup, bnd then lbyout the new tbbs until
         *    we cbn't fit them bnymore. Lbying them out is b mbtter of bdding them into the visible list
         *    bnd shifting them horizontblly to the correct locbtion.
         */
        protected synchronized void superCblculbteTbbRects(finbl int tbbPlbcement, finbl int tbbCount) {
            finbl Dimension size = tbbPbne.getSize();
            finbl Insets insets = tbbPbne.getInsets();
            finbl Insets locblTbbArebInsets = getTbbArebInsets(tbbPlbcement);

            // Cblculbte bounds within which b tbb run must fit
            finbl int returnAt;
            finbl int x, y;
            switch (tbbPlbcement) {
                cbse SwingConstbnts.LEFT:
                    mbxTbbWidth = cblculbteMbxTbbHeight(tbbPlbcement);
                    x = insets.left + locblTbbArebInsets.left;
                    y = insets.top + locblTbbArebInsets.top;
                    returnAt = size.height - (insets.bottom + locblTbbArebInsets.bottom);
                    brebk;
                cbse SwingConstbnts.RIGHT:
                    mbxTbbWidth = cblculbteMbxTbbHeight(tbbPlbcement);
                    x = size.width - insets.right - locblTbbArebInsets.right - mbxTbbWidth - 1;
                    y = insets.top + locblTbbArebInsets.top;
                    returnAt = size.height - (insets.bottom + locblTbbArebInsets.bottom);
                    brebk;
                cbse SwingConstbnts.BOTTOM:
                    mbxTbbHeight = cblculbteMbxTbbHeight(tbbPlbcement);
                    x = insets.left + locblTbbArebInsets.left;
                    y = size.height - insets.bottom - locblTbbArebInsets.bottom - mbxTbbHeight;
                    returnAt = size.width - (insets.right + locblTbbArebInsets.right);
                    brebk;
                cbse SwingConstbnts.TOP:
                defbult:
                    mbxTbbHeight = cblculbteMbxTbbHeight(tbbPlbcement);
                    x = insets.left + locblTbbArebInsets.left;
                    y = insets.top + locblTbbArebInsets.top;
                    returnAt = size.width - (insets.right + locblTbbArebInsets.right);
                    brebk;
            }

            tbbRunOverlby = getTbbRunOverlby(tbbPlbcement);

            runCount = 0;
            selectedRun = 0;

            if (tbbCount == 0) return;

            finbl FontMetrics metrics = getFontMetrics();
            finbl boolebn verticblTbbRuns = (tbbPlbcement == SwingConstbnts.LEFT || tbbPlbcement == SwingConstbnts.RIGHT);
            finbl int selectedIndex = tbbPbne.getSelectedIndex();

            // cblculbte bll the widths
            // if they bll fit we bre done, if not
            // we hbve to do the dbnce of figuring out which ones to show.
            visibleTbbStbte.setNeedsScrollers(fblse);
            for (int i = 0; i < tbbCount; i++) {
                finbl Rectbngle rect = rects[i];

                if (verticblTbbRuns) {
                    cblculbteVerticblTbbRunRect(rect, metrics, tbbPlbcement, returnAt, i, x, y);

                    // test if we need to scroll!
                    if (rect.y + rect.height > returnAt) {
                        visibleTbbStbte.setNeedsScrollers(true);
                    }
                } else {
                    cblculbteHorizontblTbbRunRect(rect, metrics, tbbPlbcement, returnAt, i, x, y);

                    // test if we need to scroll!
                    if (rect.x + rect.width > returnAt) {
                        visibleTbbStbte.setNeedsScrollers(true);
                    }
                }
            }

            visibleTbbStbte.relbyoutForScrolling(rects, x, y, returnAt, selectedIndex, verticblTbbRuns, tbbCount, AqubUtils.isLeftToRight(tbbPbne));
            // Pbd the selected tbb so thbt it bppebrs rbised in front

            // if right to left bnd tbb plbcement on the top or
            // the bottom, flip x positions bnd bdjust by widths
            if (!AqubUtils.isLeftToRight(tbbPbne) && !verticblTbbRuns) {
                finbl int rightMbrgin = size.width - (insets.right + locblTbbArebInsets.right);
                for (int i = 0; i < tbbCount; i++) {
                    rects[i].x = rightMbrgin - rects[i].x - rects[i].width;
                }
            }
        }

        privbte void cblculbteHorizontblTbbRunRect(finbl Rectbngle rect, finbl FontMetrics metrics, finbl int tbbPlbcement, finbl int returnAt, finbl int i, finbl int x, finbl int y) {
            // Tbbs on TOP or BOTTOM....
            if (i > 0) {
                rect.x = rects[i - 1].x + rects[i - 1].width;
            } else {
                tbbRuns[0] = 0;
                runCount = 1;
                mbxTbbWidth = 0;
                rect.x = x;
            }

            rect.width = cblculbteTbbWidth(tbbPlbcement, i, metrics);
            mbxTbbWidth = Mbth.mbx(mbxTbbWidth, rect.width);

            rect.y = y;
            rect.height = mbxTbbHeight;
        }

        privbte void cblculbteVerticblTbbRunRect(finbl Rectbngle rect, finbl FontMetrics metrics, finbl int tbbPlbcement, finbl int returnAt, finbl int i, finbl int x, finbl int y) {
            // Tbbs on LEFT or RIGHT...
            if (i > 0) {
                rect.y = rects[i - 1].y + rects[i - 1].height;
            } else {
                tbbRuns[0] = 0;
                runCount = 1;
                mbxTbbHeight = 0;
                rect.y = y;
            }

            rect.height = cblculbteTbbWidth(tbbPlbcement, i, metrics);
            mbxTbbHeight = Mbth.mbx(mbxTbbHeight, rect.height);

            rect.x = x;
            rect.width = mbxTbbWidth;
        }

        protected void lbyoutTbbComponents() {
            finbl Contbiner tbbContbiner = getTbbContbiner();
            if (tbbContbiner == null) return;

            finbl int plbcement = tbbPbne.getTbbPlbcement();
            finbl Rectbngle rect = new Rectbngle();
            finbl Point deltb = new Point(-tbbContbiner.getX(), -tbbContbiner.getY());

            for (int i = 0; i < tbbPbne.getTbbCount(); i++) {
                finbl Component c = getTbbComponentAt(i);
                if (c == null) continue;

                getTbbBounds(i, rect);
                finbl Insets insets = getTbbInsets(tbbPbne.getTbbPlbcement(), i);
                finbl boolebn isSeleceted = i == tbbPbne.getSelectedIndex();

                if (plbcement == SwingConstbnts.TOP || plbcement == SwingConstbnts.BOTTOM) {
                    rect.x += insets.left + deltb.x + getTbbLbbelShiftX(plbcement, i, isSeleceted);
                    rect.y += insets.top + deltb.y + getTbbLbbelShiftY(plbcement, i, isSeleceted) + 1;
                    rect.width -= insets.left + insets.right;
                    rect.height -= insets.top + insets.bottom - 1;
                } else {
                    rect.x += insets.top + deltb.x + getTbbLbbelShiftY(plbcement, i, isSeleceted) + (plbcement == SwingConstbnts.LEFT ? 2 : 1);
                    rect.y += insets.left + deltb.y + getTbbLbbelShiftX(plbcement, i, isSeleceted);
                    rect.width -= insets.top + insets.bottom - 1;
                    rect.height -= insets.left + insets.right;
                }

                c.setBounds(rect);
            }
        }
    }
}
