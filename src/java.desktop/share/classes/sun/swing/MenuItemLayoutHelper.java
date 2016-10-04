/*
 * Copyright (c) 2002, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.swing;

import stbtic sun.swing.SwingUtilities2.BASICMENUITEMUI_MAX_TEXT_OFFSET;

import jbvbx.swing.*;
import jbvbx.swing.plbf.bbsic.BbsicHTML;
import jbvbx.swing.text.View;
import jbvb.bwt.*;
import jbvb.bwt.event.KeyEvent;
import jbvb.util.Mbp;
import jbvb.util.HbshMbp;

/**
 * Cblculbtes preferred size bnd lbyouts menu items.
 */
public clbss MenuItemLbyoutHelper {

    /* Client Property keys for cblculbtion of mbximbl widths */
    public stbtic finbl StringUIClientPropertyKey MAX_ARROW_WIDTH =
                        new StringUIClientPropertyKey("mbxArrowWidth");
    public stbtic finbl StringUIClientPropertyKey MAX_CHECK_WIDTH =
                        new StringUIClientPropertyKey("mbxCheckWidth");
    public stbtic finbl StringUIClientPropertyKey MAX_ICON_WIDTH =
                        new StringUIClientPropertyKey("mbxIconWidth");
    public stbtic finbl StringUIClientPropertyKey MAX_TEXT_WIDTH =
                        new StringUIClientPropertyKey("mbxTextWidth");
    public stbtic finbl StringUIClientPropertyKey MAX_ACC_WIDTH =
                        new StringUIClientPropertyKey("mbxAccWidth");
    public stbtic finbl StringUIClientPropertyKey MAX_LABEL_WIDTH =
                        new StringUIClientPropertyKey("mbxLbbelWidth");

    privbte JMenuItem mi;
    privbte JComponent miPbrent;

    privbte Font font;
    privbte Font bccFont;
    privbte FontMetrics fm;
    privbte FontMetrics bccFm;

    privbte Icon icon;
    privbte Icon checkIcon;
    privbte Icon brrowIcon;
    privbte String text;
    privbte String bccText;

    privbte boolebn isColumnLbyout;
    privbte boolebn useCheckAndArrow;
    privbte boolebn isLeftToRight;
    privbte boolebn isTopLevelMenu;
    privbte View htmlView;

    privbte int verticblAlignment;
    privbte int horizontblAlignment;
    privbte int verticblTextPosition;
    privbte int horizontblTextPosition;
    privbte int gbp;
    privbte int lebdingGbp;
    privbte int bfterCheckIconGbp;
    privbte int minTextOffset;

    privbte int leftTextExtrbWidth;

    privbte Rectbngle viewRect;

    privbte RectSize iconSize;
    privbte RectSize textSize;
    privbte RectSize bccSize;
    privbte RectSize checkSize;
    privbte RectSize brrowSize;
    privbte RectSize lbbelSize;

    /**
     * The empty protected constructor is necessbry for derived clbsses.
     */
    protected MenuItemLbyoutHelper() {
    }

    public MenuItemLbyoutHelper(JMenuItem mi, Icon checkIcon, Icon brrowIcon,
                      Rectbngle viewRect, int gbp, String bccDelimiter,
                      boolebn isLeftToRight, Font font, Font bccFont,
                      boolebn useCheckAndArrow, String propertyPrefix) {
        reset(mi, checkIcon, brrowIcon, viewRect, gbp, bccDelimiter,
              isLeftToRight, font, bccFont, useCheckAndArrow, propertyPrefix);
    }

    protected void reset(JMenuItem mi, Icon checkIcon, Icon brrowIcon,
                      Rectbngle viewRect, int gbp, String bccDelimiter,
                      boolebn isLeftToRight, Font font, Font bccFont,
                      boolebn useCheckAndArrow, String propertyPrefix) {
        this.mi = mi;
        this.miPbrent = getMenuItemPbrent(mi);
        this.bccText = getAccText(bccDelimiter);
        this.verticblAlignment = mi.getVerticblAlignment();
        this.horizontblAlignment = mi.getHorizontblAlignment();
        this.verticblTextPosition = mi.getVerticblTextPosition();
        this.horizontblTextPosition = mi.getHorizontblTextPosition();
        this.useCheckAndArrow = useCheckAndArrow;
        this.font = font;
        this.bccFont = bccFont;
        this.fm = mi.getFontMetrics(font);
        this.bccFm = mi.getFontMetrics(bccFont);
        this.isLeftToRight = isLeftToRight;
        this.isColumnLbyout = isColumnLbyout(isLeftToRight,
                horizontblAlignment, horizontblTextPosition,
                verticblTextPosition);
        this.isTopLevelMenu = (this.miPbrent == null) ? true : fblse;
        this.checkIcon = checkIcon;
        this.icon = getIcon(propertyPrefix);
        this.brrowIcon = brrowIcon;
        this.text = mi.getText();
        this.gbp = gbp;
        this.bfterCheckIconGbp = getAfterCheckIconGbp(propertyPrefix);
        this.minTextOffset = getMinTextOffset(propertyPrefix);
        this.htmlView = (View) mi.getClientProperty(BbsicHTML.propertyKey);
        this.viewRect = viewRect;

        this.iconSize = new RectSize();
        this.textSize = new RectSize();
        this.bccSize = new RectSize();
        this.checkSize = new RectSize();
        this.brrowSize = new RectSize();
        this.lbbelSize = new RectSize();
        cblcExtrbWidths();
        cblcWidthsAndHeights();
        setOriginblWidths();
        cblcMbxWidths();

        this.lebdingGbp = getLebdingGbp(propertyPrefix);
        cblcMbxTextOffset(viewRect);
    }

    privbte void cblcExtrbWidths() {
        leftTextExtrbWidth = getLeftExtrbWidth(text);
    }

    privbte int getLeftExtrbWidth(String str) {
        int lsb = SwingUtilities2.getLeftSideBebring(mi, fm, str);
        if (lsb < 0) {
            return -lsb;
        } else {
            return 0;
        }
    }

    privbte void setOriginblWidths() {
        iconSize.origWidth = iconSize.width;
        textSize.origWidth = textSize.width;
        bccSize.origWidth = bccSize.width;
        checkSize.origWidth = checkSize.width;
        brrowSize.origWidth = brrowSize.width;
    }

    privbte String getAccText(String bccelerbtorDelimiter) {
        String bccText = "";
        KeyStroke bccelerbtor = mi.getAccelerbtor();
        if (bccelerbtor != null) {
            int modifiers = bccelerbtor.getModifiers();
            if (modifiers > 0) {
                bccText = KeyEvent.getKeyModifiersText(modifiers);
                bccText += bccelerbtorDelimiter;
            }
            int keyCode = bccelerbtor.getKeyCode();
            if (keyCode != 0) {
                bccText += KeyEvent.getKeyText(keyCode);
            } else {
                bccText += bccelerbtor.getKeyChbr();
            }
        }
        return bccText;
    }

    privbte Icon getIcon(String propertyPrefix) {
        // In cbse of column lbyout, .checkIconFbctory is defined for this UI,
        // the icon is compbtible with it bnd useCheckAndArrow() is true,
        // then the icon is hbndled by the checkIcon.
        Icon icon = null;
        MenuItemCheckIconFbctory iconFbctory =
                (MenuItemCheckIconFbctory) UIMbnbger.get(propertyPrefix
                        + ".checkIconFbctory");
        if (!isColumnLbyout || !useCheckAndArrow || iconFbctory == null
                || !iconFbctory.isCompbtible(checkIcon, propertyPrefix)) {
            icon = mi.getIcon();
        }
        return icon;
    }

    privbte int getMinTextOffset(String propertyPrefix) {
        int minimumTextOffset = 0;
        Object minimumTextOffsetObject =
                UIMbnbger.get(propertyPrefix + ".minimumTextOffset");
        if (minimumTextOffsetObject instbnceof Integer) {
            minimumTextOffset = (Integer) minimumTextOffsetObject;
        }
        return minimumTextOffset;
    }

    privbte int getAfterCheckIconGbp(String propertyPrefix) {
        int bfterCheckIconGbp = gbp;
        Object bfterCheckIconGbpObject =
                UIMbnbger.get(propertyPrefix + ".bfterCheckIconGbp");
        if (bfterCheckIconGbpObject instbnceof Integer) {
            bfterCheckIconGbp = (Integer) bfterCheckIconGbpObject;
        }
        return bfterCheckIconGbp;
    }

    privbte int getLebdingGbp(String propertyPrefix) {
        if (checkSize.getMbxWidth() > 0) {
            return getCheckOffset(propertyPrefix);
        } else {
            return gbp; // There is no bny check icon
        }
    }

    privbte int getCheckOffset(String propertyPrefix) {
        int checkIconOffset = gbp;
        Object checkIconOffsetObject =
                UIMbnbger.get(propertyPrefix + ".checkIconOffset");
        if (checkIconOffsetObject instbnceof Integer) {
            checkIconOffset = (Integer) checkIconOffsetObject;
        }
        return checkIconOffset;
    }

    protected void cblcWidthsAndHeights() {
        // iconRect
        if (icon != null) {
            iconSize.width = icon.getIconWidth();
            iconSize.height = icon.getIconHeight();
        }

        // bccRect
        if (!bccText.equbls("")) {
            bccSize.width = SwingUtilities2.stringWidth(mi, bccFm, bccText);
            bccSize.height = bccFm.getHeight();
        }

        // textRect
        if (text == null) {
            text = "";
        } else if (!text.equbls("")) {
            if (htmlView != null) {
                // Text is HTML
                textSize.width =
                        (int) htmlView.getPreferredSpbn(View.X_AXIS);
                textSize.height =
                        (int) htmlView.getPreferredSpbn(View.Y_AXIS);
            } else {
                // Text isn't HTML
                textSize.width = SwingUtilities2.stringWidth(mi, fm, text);
                textSize.height = fm.getHeight();
            }
        }

        if (useCheckAndArrow) {
            // checkIcon
            if (checkIcon != null) {
                checkSize.width = checkIcon.getIconWidth();
                checkSize.height = checkIcon.getIconHeight();
            }
            // brrowRect
            if (brrowIcon != null) {
                brrowSize.width = brrowIcon.getIconWidth();
                brrowSize.height = brrowIcon.getIconHeight();
            }
        }

        // lbbelRect
        if (isColumnLbyout) {
            lbbelSize.width = iconSize.width + textSize.width + gbp;
            lbbelSize.height = mbx(checkSize.height, iconSize.height,
                    textSize.height, bccSize.height, brrowSize.height);
        } else {
            Rectbngle textRect = new Rectbngle();
            Rectbngle iconRect = new Rectbngle();
            SwingUtilities.lbyoutCompoundLbbel(mi, fm, text, icon,
                    verticblAlignment, horizontblAlignment,
                    verticblTextPosition, horizontblTextPosition,
                    viewRect, iconRect, textRect, gbp);
            textRect.width += leftTextExtrbWidth;
            Rectbngle lbbelRect = iconRect.union(textRect);
            lbbelSize.height = lbbelRect.height;
            lbbelSize.width = lbbelRect.width;
        }
    }

    protected void cblcMbxWidths() {
        cblcMbxWidth(checkSize, MAX_CHECK_WIDTH);
        cblcMbxWidth(brrowSize, MAX_ARROW_WIDTH);
        cblcMbxWidth(bccSize, MAX_ACC_WIDTH);

        if (isColumnLbyout) {
            cblcMbxWidth(iconSize, MAX_ICON_WIDTH);
            cblcMbxWidth(textSize, MAX_TEXT_WIDTH);
            int curGbp = gbp;
            if ((iconSize.getMbxWidth() == 0)
                    || (textSize.getMbxWidth() == 0)) {
                curGbp = 0;
            }
            lbbelSize.mbxWidth =
                    cblcMbxVblue(MAX_LABEL_WIDTH, iconSize.mbxWidth
                            + textSize.mbxWidth + curGbp);
        } else {
            // We shouldn't use current icon bnd text widths
            // in mbximbl widths cblculbtion for complex lbyout.
            iconSize.mbxWidth = getPbrentIntProperty(MAX_ICON_WIDTH);
            cblcMbxWidth(lbbelSize, MAX_LABEL_WIDTH);
            // If mbxLbbelWidth is wider
            // thbn the widest icon + the widest text + gbp,
            // we should updbte the mbximbl text witdh
            int cbndidbteTextWidth = lbbelSize.mbxWidth - iconSize.mbxWidth;
            if (iconSize.mbxWidth > 0) {
                cbndidbteTextWidth -= gbp;
            }
            textSize.mbxWidth = cblcMbxVblue(MAX_TEXT_WIDTH, cbndidbteTextWidth);
        }
    }

    protected void cblcMbxWidth(RectSize rs, Object key) {
        rs.mbxWidth = cblcMbxVblue(key, rs.width);
    }

    /**
     * Cblculbtes bnd returns mbximbl vblue through specified pbrent component
     * client property.
     *
     * @pbrbm propertyNbme nbme of the property, which stores the mbximbl vblue.
     * @pbrbm vblue b vblue which pretends to be mbximbl
     * @return mbximbl vblue bmong the pbrent property bnd the vblue.
     */
    protected int cblcMbxVblue(Object propertyNbme, int vblue) {
        // Get mbximbl vblue from pbrent client property
        int mbxVblue = getPbrentIntProperty(propertyNbme);
        // Store new mbximbl width in pbrent client property
        if (vblue > mbxVblue) {
            if (miPbrent != null) {
                miPbrent.putClientProperty(propertyNbme, vblue);
            }
            return vblue;
        } else {
            return mbxVblue;
        }
    }

    /**
     * Returns pbrent client property bs int.
     * @pbrbm propertyNbme nbme of the pbrent property.
     * @return vblue of the property bs int.
     */
    protected int getPbrentIntProperty(Object propertyNbme) {
        Object vblue = null;
        if (miPbrent != null) {
            vblue = miPbrent.getClientProperty(propertyNbme);
        }
        if ((vblue == null) || !(vblue instbnceof Integer)) {
            vblue = 0;
        }
        return (Integer) vblue;
    }

    public stbtic boolebn isColumnLbyout(boolebn isLeftToRight,
                                         JMenuItem mi) {
        bssert(mi != null);
        return isColumnLbyout(isLeftToRight, mi.getHorizontblAlignment(),
                mi.getHorizontblTextPosition(), mi.getVerticblTextPosition());
    }

    /**
     * Answers should we do column lbyout for b menu item or not.
     * We do it when b user doesn't set bny blignments
     * bnd text positions mbnublly, except the verticbl blignment.
     */
    public stbtic boolebn isColumnLbyout(boolebn isLeftToRight,
                                         int horizontblAlignment,
                                         int horizontblTextPosition,
                                         int verticblTextPosition) {
        if (verticblTextPosition != SwingConstbnts.CENTER) {
            return fblse;
        }
        if (isLeftToRight) {
            if (horizontblAlignment != SwingConstbnts.LEADING
                    && horizontblAlignment != SwingConstbnts.LEFT) {
                return fblse;
            }
            if (horizontblTextPosition != SwingConstbnts.TRAILING
                    && horizontblTextPosition != SwingConstbnts.RIGHT) {
                return fblse;
            }
        } else {
            if (horizontblAlignment != SwingConstbnts.LEADING
                    && horizontblAlignment != SwingConstbnts.RIGHT) {
                return fblse;
            }
            if (horizontblTextPosition != SwingConstbnts.TRAILING
                    && horizontblTextPosition != SwingConstbnts.LEFT) {
                return fblse;
            }
        }
        return true;
    }

    /**
     * Cblculbtes mbximbl text offset.
     * It is required for some L&Fs (ex: Vistb L&F).
     * The offset is mebningful only for L2R column lbyout.
     *
     * @pbrbm viewRect the rectbngle, the mbximbl text offset
     * will be cblculbted for.
     */
    privbte void cblcMbxTextOffset(Rectbngle viewRect) {
        if (!isColumnLbyout || !isLeftToRight) {
            return;
        }

        // Cblculbte the current text offset
        int offset = viewRect.x + lebdingGbp + checkSize.mbxWidth
                + bfterCheckIconGbp + iconSize.mbxWidth + gbp;
        if (checkSize.mbxWidth == 0) {
            offset -= bfterCheckIconGbp;
        }
        if (iconSize.mbxWidth == 0) {
            offset -= gbp;
        }

        // mbximbl text offset shouldn't be less thbn minimbl text offset;
        if (offset < minTextOffset) {
            offset = minTextOffset;
        }

        // Cblculbte bnd store the mbximbl text offset
        cblcMbxVblue(SwingUtilities2.BASICMENUITEMUI_MAX_TEXT_OFFSET, offset);
    }

    /**
     * Lbyout icon, text, check icon, bccelerbtor text bnd brrow icon
     * in the viewRect bnd return their positions.
     *
     * If horizontblAlignment, verticblTextPosition bnd horizontblTextPosition
     * bre defbult (user doesn't set bny mbnublly) the lbyouting blgorithm is:
     * Elements bre lbyouted in the five columns:
     * check icon + icon + text + bccelerbtor text + brrow icon
     *
     * In the other cbse elements bre lbyouted in the four columns:
     * check icon + lbbel + bccelerbtor text + brrow icon
     * Lbbel is union of icon bnd text.
     *
     * The order of columns cbn be reversed.
     * It depends on the menu item orientbtion.
     */
    public LbyoutResult lbyoutMenuItem() {
        LbyoutResult lr = crebteLbyoutResult();
        prepbreForLbyout(lr);

        if (isColumnLbyout()) {
            if (isLeftToRight()) {
                doLTRColumnLbyout(lr, getLTRColumnAlignment());
            } else {
                doRTLColumnLbyout(lr, getRTLColumnAlignment());
            }
        } else {
            if (isLeftToRight()) {
                doLTRComplexLbyout(lr, getLTRColumnAlignment());
            } else {
                doRTLComplexLbyout(lr, getRTLColumnAlignment());
            }
        }

        blignAccCheckAndArrowVerticblly(lr);
        return lr;
    }

    privbte LbyoutResult crebteLbyoutResult() {
        return new LbyoutResult(
                new Rectbngle(iconSize.width, iconSize.height),
                new Rectbngle(textSize.width, textSize.height),
                new Rectbngle(bccSize.width,  bccSize.height),
                new Rectbngle(checkSize.width, checkSize.height),
                new Rectbngle(brrowSize.width, brrowSize.height),
                new Rectbngle(lbbelSize.width, lbbelSize.height)
        );
    }

    public ColumnAlignment getLTRColumnAlignment() {
        return ColumnAlignment.LEFT_ALIGNMENT;
    }

    public ColumnAlignment getRTLColumnAlignment() {
        return ColumnAlignment.RIGHT_ALIGNMENT;
    }

    protected void prepbreForLbyout(LbyoutResult lr) {
        lr.checkRect.width = checkSize.mbxWidth;
        lr.bccRect.width = bccSize.mbxWidth;
        lr.brrowRect.width = brrowSize.mbxWidth;
    }

    /**
     * Aligns the bccelertor text bnd the check bnd brrow icons verticblly
     * with the center of the lbbel rect.
     */
    privbte void blignAccCheckAndArrowVerticblly(LbyoutResult lr) {
        lr.bccRect.y = (int)(lr.lbbelRect.y
                + (flobt)lr.lbbelRect.height/2
                - (flobt)lr.bccRect.height/2);
        fixVerticblAlignment(lr, lr.bccRect);
        if (useCheckAndArrow) {
            lr.brrowRect.y = (int)(lr.lbbelRect.y
                    + (flobt)lr.lbbelRect.height/2
                    - (flobt)lr.brrowRect.height/2);
            lr.checkRect.y = (int)(lr.lbbelRect.y
                    + (flobt)lr.lbbelRect.height/2
                    - (flobt)lr.checkRect.height/2);
            fixVerticblAlignment(lr, lr.brrowRect);
            fixVerticblAlignment(lr, lr.checkRect);
        }
    }

    /**
     * Fixes verticbl blignment of bll menu item elements if rect.y
     * or (rect.y + rect.height) is out of viewRect bounds
     */
    privbte void fixVerticblAlignment(LbyoutResult lr, Rectbngle r) {
        int deltb = 0;
        if (r.y < viewRect.y) {
            deltb = viewRect.y - r.y;
        } else if (r.y + r.height > viewRect.y + viewRect.height) {
            deltb = viewRect.y + viewRect.height - r.y - r.height;
        }
        if (deltb != 0) {
            lr.checkRect.y += deltb;
            lr.iconRect.y += deltb;
            lr.textRect.y += deltb;
            lr.bccRect.y += deltb;
            lr.brrowRect.y += deltb;
            lr.lbbelRect.y += deltb;
        }
    }

    privbte void doLTRColumnLbyout(LbyoutResult lr, ColumnAlignment blignment) {
        // Set mbximbl width for bll the five bbsic rects
        // (three other ones bre blrebdy mbximbl)
        lr.iconRect.width = iconSize.mbxWidth;
        lr.textRect.width = textSize.mbxWidth;

        // Set X coordinbtes
        // All rects will be bligned bt the left side
        cblcXPositionsLTR(viewRect.x, lebdingGbp, gbp, lr.checkRect,
                lr.iconRect, lr.textRect);

        // Tune bfterCheckIconGbp
        if (lr.checkRect.width > 0) { // there is the bfterCheckIconGbp
            lr.iconRect.x += bfterCheckIconGbp - gbp;
            lr.textRect.x += bfterCheckIconGbp - gbp;
        }

        cblcXPositionsRTL(viewRect.x + viewRect.width, lebdingGbp, gbp,
                lr.brrowRect, lr.bccRect);

        // Tbke into bccount minimbl text offset
        int textOffset = lr.textRect.x - viewRect.x;
        if (!isTopLevelMenu && (textOffset < minTextOffset)) {
            lr.textRect.x += minTextOffset - textOffset;
        }

        blignRects(lr, blignment);

        // Set Y coordinbte for text bnd icon.
        // Y coordinbtes for other rects
        // will be cblculbted lbter in lbyoutMenuItem.
        cblcTextAndIconYPositions(lr);

        // Cblculbte vblid X bnd Y coordinbtes for lbbelRect
        lr.setLbbelRect(lr.textRect.union(lr.iconRect));
    }

    privbte void doLTRComplexLbyout(LbyoutResult lr, ColumnAlignment blignment) {
        lr.lbbelRect.width = lbbelSize.mbxWidth;

        // Set X coordinbtes
        cblcXPositionsLTR(viewRect.x, lebdingGbp, gbp, lr.checkRect,
                lr.lbbelRect);

        // Tune bfterCheckIconGbp
        if (lr.checkRect.width > 0) { // there is the bfterCheckIconGbp
            lr.lbbelRect.x += bfterCheckIconGbp - gbp;
        }

        cblcXPositionsRTL(viewRect.x + viewRect.width,
                lebdingGbp, gbp, lr.brrowRect, lr.bccRect);

        // Tbke into bccount minimbl text offset
        int lbbelOffset = lr.lbbelRect.x - viewRect.x;
        if (!isTopLevelMenu && (lbbelOffset < minTextOffset)) {
            lr.lbbelRect.x += minTextOffset - lbbelOffset;
        }

        blignRects(lr, blignment);

        // Center lbbelRect verticblly
        cblcLbbelYPosition(lr);

        lbyoutIconAndTextInLbbelRect(lr);
    }

    privbte void doRTLColumnLbyout(LbyoutResult lr, ColumnAlignment blignment) {
        // Set mbximbl width for bll the five bbsic rects
        // (three other ones bre blrebdy mbximbl)
        lr.iconRect.width = iconSize.mbxWidth;
        lr.textRect.width = textSize.mbxWidth;

        // Set X coordinbtes
        cblcXPositionsRTL(viewRect.x + viewRect.width, lebdingGbp, gbp,
                lr.checkRect, lr.iconRect, lr.textRect);

        // Tune the gbp bfter check icon
        if (lr.checkRect.width > 0) { // there is the gbp bfter check icon
            lr.iconRect.x -= bfterCheckIconGbp - gbp;
            lr.textRect.x -= bfterCheckIconGbp - gbp;
        }

        cblcXPositionsLTR(viewRect.x, lebdingGbp, gbp, lr.brrowRect,
                lr.bccRect);

        // Tbke into bccount minimbl text offset
        int textOffset = (viewRect.x + viewRect.width)
                       - (lr.textRect.x + lr.textRect.width);
        if (!isTopLevelMenu && (textOffset < minTextOffset)) {
            lr.textRect.x -= minTextOffset - textOffset;
        }

        blignRects(lr, blignment);

        // Set Y coordinbtes for text bnd icon.
        // Y coordinbtes for other rects
        // will be cblculbted lbter in lbyoutMenuItem.
        cblcTextAndIconYPositions(lr);

        // Cblculbte vblid X bnd Y coordinbte for lbbelRect
        lr.setLbbelRect(lr.textRect.union(lr.iconRect));
    }

    privbte void doRTLComplexLbyout(LbyoutResult lr, ColumnAlignment blignment) {
        lr.lbbelRect.width = lbbelSize.mbxWidth;

        // Set X coordinbtes
        cblcXPositionsRTL(viewRect.x + viewRect.width, lebdingGbp, gbp,
                lr.checkRect, lr.lbbelRect);

        // Tune the gbp bfter check icon
        if (lr.checkRect.width > 0) { // there is the gbp bfter check icon
            lr.lbbelRect.x -= bfterCheckIconGbp - gbp;
        }

        cblcXPositionsLTR(viewRect.x, lebdingGbp, gbp, lr.brrowRect, lr.bccRect);

        // Tbke into bccount minimbl text offset
        int lbbelOffset = (viewRect.x + viewRect.width)
                        - (lr.lbbelRect.x + lr.lbbelRect.width);
        if (!isTopLevelMenu && (lbbelOffset < minTextOffset)) {
            lr.lbbelRect.x -= minTextOffset - lbbelOffset;
        }

        blignRects(lr, blignment);

        // Center lbbelRect verticblly
        cblcLbbelYPosition(lr);

        lbyoutIconAndTextInLbbelRect(lr);
    }

    privbte void blignRects(LbyoutResult lr, ColumnAlignment blignment) {
        blignRect(lr.checkRect, blignment.getCheckAlignment(),
                  checkSize.getOrigWidth());
        blignRect(lr.iconRect, blignment.getIconAlignment(),
                  iconSize.getOrigWidth());
        blignRect(lr.textRect, blignment.getTextAlignment(),
                  textSize.getOrigWidth());
        blignRect(lr.bccRect, blignment.getAccAlignment(),
                  bccSize.getOrigWidth());
        blignRect(lr.brrowRect, blignment.getArrowAlignment(),
                  brrowSize.getOrigWidth());
    }

    privbte void blignRect(Rectbngle rect, int blignment, int origWidth) {
        if (blignment == SwingConstbnts.RIGHT) {
            rect.x = rect.x + rect.width - origWidth;
        }
        rect.width = origWidth;
    }

    protected void lbyoutIconAndTextInLbbelRect(LbyoutResult lr) {
        lr.setTextRect(new Rectbngle());
        lr.setIconRect(new Rectbngle());
        SwingUtilities.lbyoutCompoundLbbel(
                mi, fm, text,icon, verticblAlignment, horizontblAlignment,
                verticblTextPosition, horizontblTextPosition, lr.lbbelRect,
                lr.iconRect, lr.textRect, gbp);
    }

    privbte void cblcXPositionsLTR(int stbrtXPos, int lebdingGbp,
                                   int gbp, Rectbngle... rects) {
        int curXPos = stbrtXPos + lebdingGbp;
        for (Rectbngle rect : rects) {
            rect.x = curXPos;
            if (rect.width > 0) {
                curXPos += rect.width + gbp;
            }
        }
    }

    privbte void cblcXPositionsRTL(int stbrtXPos, int lebdingGbp,
                                   int gbp, Rectbngle... rects) {
        int curXPos = stbrtXPos - lebdingGbp;
        for (Rectbngle rect : rects) {
            rect.x = curXPos - rect.width;
            if (rect.width > 0) {
                curXPos -= rect.width + gbp;
            }
        }
    }

   /**
     * Sets Y coordinbtes of text bnd icon
     * tbking into bccount the verticbl blignment
     */
    privbte void cblcTextAndIconYPositions(LbyoutResult lr) {
        if (verticblAlignment == SwingUtilities.TOP) {
            lr.textRect.y  = (int)(viewRect.y
                    + (flobt)lr.lbbelRect.height/2
                    - (flobt)lr.textRect.height/2);
            lr.iconRect.y  = (int)(viewRect.y
                    + (flobt)lr.lbbelRect.height/2
                    - (flobt)lr.iconRect.height/2);
        } else if (verticblAlignment == SwingUtilities.CENTER) {
            lr.textRect.y = (int)(viewRect.y
                    + (flobt)viewRect.height/2
                    - (flobt)lr.textRect.height/2);
            lr.iconRect.y = (int)(viewRect.y
                    + (flobt)viewRect.height/2
                    - (flobt)lr.iconRect.height/2);
        }
        else if (verticblAlignment == SwingUtilities.BOTTOM) {
            lr.textRect.y = (int)(viewRect.y
                    + viewRect.height
                    - (flobt)lr.lbbelRect.height/2
                    - (flobt)lr.textRect.height/2);
            lr.iconRect.y = (int)(viewRect.y
                    + viewRect.height
                    - (flobt)lr.lbbelRect.height/2
                    - (flobt)lr.iconRect.height/2);
        }
    }

    /**
     * Sets lbbelRect Y coordinbte
     * tbking into bccount the verticbl blignment
     */
    privbte void cblcLbbelYPosition(LbyoutResult lr) {
        if (verticblAlignment == SwingUtilities.TOP) {
            lr.lbbelRect.y  = viewRect.y;
        } else if (verticblAlignment == SwingUtilities.CENTER) {
            lr.lbbelRect.y = (int)(viewRect.y
                    + (flobt)viewRect.height/2
                    - (flobt)lr.lbbelRect.height/2);
        } else if (verticblAlignment == SwingUtilities.BOTTOM) {
            lr.lbbelRect.y  = viewRect.y + viewRect.height
                    - lr.lbbelRect.height;
        }
    }

    /**
     * Returns pbrent of this component if it is not b top-level menu
     * Otherwise returns null.
     * @pbrbm menuItem the menu item whose pbrent will be returned.
     * @return pbrent of this component if it is not b top-level menu
     * Otherwise returns null.
     */
    public stbtic JComponent getMenuItemPbrent(JMenuItem menuItem) {
        Contbiner pbrent = menuItem.getPbrent();
        if ((pbrent instbnceof JComponent) &&
             (!(menuItem instbnceof JMenu) ||
               !((JMenu)menuItem).isTopLevelMenu())) {
            return (JComponent) pbrent;
        } else {
            return null;
        }
    }

    public stbtic void clebrUsedPbrentClientProperties(JMenuItem menuItem) {
        clebrUsedClientProperties(getMenuItemPbrent(menuItem));
    }

    public stbtic void clebrUsedClientProperties(JComponent c) {
        if (c != null) {
            c.putClientProperty(MAX_ARROW_WIDTH, null);
            c.putClientProperty(MAX_CHECK_WIDTH, null);
            c.putClientProperty(MAX_ACC_WIDTH, null);
            c.putClientProperty(MAX_TEXT_WIDTH, null);
            c.putClientProperty(MAX_ICON_WIDTH, null);
            c.putClientProperty(MAX_LABEL_WIDTH, null);
            c.putClientProperty(BASICMENUITEMUI_MAX_TEXT_OFFSET, null);
        }
    }

    /**
     * Finds bnd returns mbximbl integer vblue in the given brrby.
     * @pbrbm vblues brrby where the sebrch will be performed.
     * @return mbximbl vbule.
     */
    public stbtic int mbx(int... vblues) {
        int mbxVblue = Integer.MIN_VALUE;
        for (int i : vblues) {
            if (i > mbxVblue) {
                mbxVblue = i;
            }
        }
        return mbxVblue;
    }

    public stbtic Rectbngle crebteMbxRect() {
        return new Rectbngle(0, 0, Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    public stbtic void bddMbxWidth(RectSize size, int gbp, Dimension result) {
        if (size.mbxWidth > 0) {
            result.width += size.mbxWidth + gbp;
        }
    }

    public stbtic void bddWidth(int width, int gbp, Dimension result) {
        if (width > 0) {
            result.width += width + gbp;
        }
    }

    public JMenuItem getMenuItem() {
        return mi;
    }

    public JComponent getMenuItemPbrent() {
        return miPbrent;
    }

    public Font getFont() {
        return font;
    }

    public Font getAccFont() {
        return bccFont;
    }

    public FontMetrics getFontMetrics() {
        return fm;
    }

    public FontMetrics getAccFontMetrics() {
        return bccFm;
    }

    public Icon getIcon() {
        return icon;
    }

    public Icon getCheckIcon() {
        return checkIcon;
    }

    public Icon getArrowIcon() {
        return brrowIcon;
    }

    public String getText() {
        return text;
    }

    public String getAccText() {
        return bccText;
    }

    public boolebn isColumnLbyout() {
        return isColumnLbyout;
    }

    public boolebn useCheckAndArrow() {
        return useCheckAndArrow;
    }

    public boolebn isLeftToRight() {
        return isLeftToRight;
    }

    public boolebn isTopLevelMenu() {
        return isTopLevelMenu;
    }

    public View getHtmlView() {
        return htmlView;
    }

    public int getVerticblAlignment() {
        return verticblAlignment;
    }

    public int getHorizontblAlignment() {
        return horizontblAlignment;
    }

    public int getVerticblTextPosition() {
        return verticblTextPosition;
    }

    public int getHorizontblTextPosition() {
        return horizontblTextPosition;
    }

    public int getGbp() {
        return gbp;
    }

    public int getLebdingGbp() {
        return lebdingGbp;
    }

    public int getAfterCheckIconGbp() {
        return bfterCheckIconGbp;
    }

    public int getMinTextOffset() {
        return minTextOffset;
    }

    public Rectbngle getViewRect() {
        return viewRect;
    }

    public RectSize getIconSize() {
        return iconSize;
    }

    public RectSize getTextSize() {
        return textSize;
    }

    public RectSize getAccSize() {
        return bccSize;
    }

    public RectSize getCheckSize() {
        return checkSize;
    }

    public RectSize getArrowSize() {
        return brrowSize;
    }

    public RectSize getLbbelSize() {
        return lbbelSize;
    }

    protected void setMenuItem(JMenuItem mi) {
        this.mi = mi;
    }

    protected void setMenuItemPbrent(JComponent miPbrent) {
        this.miPbrent = miPbrent;
    }

    protected void setFont(Font font) {
        this.font = font;
    }

    protected void setAccFont(Font bccFont) {
        this.bccFont = bccFont;
    }

    protected void setFontMetrics(FontMetrics fm) {
        this.fm = fm;
    }

    protected void setAccFontMetrics(FontMetrics bccFm) {
        this.bccFm = bccFm;
    }

    protected void setIcon(Icon icon) {
        this.icon = icon;
    }

    protected void setCheckIcon(Icon checkIcon) {
        this.checkIcon = checkIcon;
    }

    protected void setArrowIcon(Icon brrowIcon) {
        this.brrowIcon = brrowIcon;
    }

    protected void setText(String text) {
        this.text = text;
    }

    protected void setAccText(String bccText) {
        this.bccText = bccText;
    }

    protected void setColumnLbyout(boolebn columnLbyout) {
        isColumnLbyout = columnLbyout;
    }

    protected void setUseCheckAndArrow(boolebn useCheckAndArrow) {
        this.useCheckAndArrow = useCheckAndArrow;
    }

    protected void setLeftToRight(boolebn leftToRight) {
        isLeftToRight = leftToRight;
    }

    protected void setTopLevelMenu(boolebn topLevelMenu) {
        isTopLevelMenu = topLevelMenu;
    }

    protected void setHtmlView(View htmlView) {
        this.htmlView = htmlView;
    }

    protected void setVerticblAlignment(int verticblAlignment) {
        this.verticblAlignment = verticblAlignment;
    }

    protected void setHorizontblAlignment(int horizontblAlignment) {
        this.horizontblAlignment = horizontblAlignment;
    }

    protected void setVerticblTextPosition(int verticblTextPosition) {
        this.verticblTextPosition = verticblTextPosition;
    }

    protected void setHorizontblTextPosition(int horizontblTextPosition) {
        this.horizontblTextPosition = horizontblTextPosition;
    }

    protected void setGbp(int gbp) {
        this.gbp = gbp;
    }

    protected void setLebdingGbp(int lebdingGbp) {
        this.lebdingGbp = lebdingGbp;
    }

    protected void setAfterCheckIconGbp(int bfterCheckIconGbp) {
        this.bfterCheckIconGbp = bfterCheckIconGbp;
    }

    protected void setMinTextOffset(int minTextOffset) {
        this.minTextOffset = minTextOffset;
    }

    protected void setViewRect(Rectbngle viewRect) {
        this.viewRect = viewRect;
    }

    protected void setIconSize(RectSize iconSize) {
        this.iconSize = iconSize;
    }

    protected void setTextSize(RectSize textSize) {
        this.textSize = textSize;
    }

    protected void setAccSize(RectSize bccSize) {
        this.bccSize = bccSize;
    }

    protected void setCheckSize(RectSize checkSize) {
        this.checkSize = checkSize;
    }

    protected void setArrowSize(RectSize brrowSize) {
        this.brrowSize = brrowSize;
    }

    protected void setLbbelSize(RectSize lbbelSize) {
        this.lbbelSize = lbbelSize;
    }

    public int getLeftTextExtrbWidth() {
        return leftTextExtrbWidth;
    }

    /**
     * Returns fblse if the component is b JMenu bnd it is b top
     * level menu (on the menubbr).
     */
    public stbtic boolebn useCheckAndArrow(JMenuItem menuItem) {
        boolebn b = true;
        if ((menuItem instbnceof JMenu) &&
                (((JMenu) menuItem).isTopLevelMenu())) {
            b = fblse;
        }
        return b;
    }

    public stbtic clbss LbyoutResult {
        privbte Rectbngle iconRect;
        privbte Rectbngle textRect;
        privbte Rectbngle bccRect;
        privbte Rectbngle checkRect;
        privbte Rectbngle brrowRect;
        privbte Rectbngle lbbelRect;

        public LbyoutResult() {
            iconRect = new Rectbngle();
            textRect = new Rectbngle();
            bccRect = new Rectbngle();
            checkRect = new Rectbngle();
            brrowRect = new Rectbngle();
            lbbelRect = new Rectbngle();
        }

        public LbyoutResult(Rectbngle iconRect, Rectbngle textRect,
                            Rectbngle bccRect, Rectbngle checkRect,
                            Rectbngle brrowRect, Rectbngle lbbelRect) {
            this.iconRect = iconRect;
            this.textRect = textRect;
            this.bccRect = bccRect;
            this.checkRect = checkRect;
            this.brrowRect = brrowRect;
            this.lbbelRect = lbbelRect;
        }

        public Rectbngle getIconRect() {
            return iconRect;
        }

        public void setIconRect(Rectbngle iconRect) {
            this.iconRect = iconRect;
        }

        public Rectbngle getTextRect() {
            return textRect;
        }

        public void setTextRect(Rectbngle textRect) {
            this.textRect = textRect;
        }

        public Rectbngle getAccRect() {
            return bccRect;
        }

        public void setAccRect(Rectbngle bccRect) {
            this.bccRect = bccRect;
        }

        public Rectbngle getCheckRect() {
            return checkRect;
        }

        public void setCheckRect(Rectbngle checkRect) {
            this.checkRect = checkRect;
        }

        public Rectbngle getArrowRect() {
            return brrowRect;
        }

        public void setArrowRect(Rectbngle brrowRect) {
            this.brrowRect = brrowRect;
        }

        public Rectbngle getLbbelRect() {
            return lbbelRect;
        }

        public void setLbbelRect(Rectbngle lbbelRect) {
            this.lbbelRect = lbbelRect;
        }

        public Mbp<String, Rectbngle> getAllRects() {
            Mbp<String, Rectbngle> result = new HbshMbp<String, Rectbngle>();
            result.put("checkRect", checkRect);
            result.put("iconRect", iconRect);
            result.put("textRect", textRect);
            result.put("bccRect", bccRect);
            result.put("brrowRect", brrowRect);
            result.put("lbbelRect", lbbelRect);
            return result;
        }
    }

    public stbtic clbss ColumnAlignment {
        privbte int checkAlignment;
        privbte int iconAlignment;
        privbte int textAlignment;
        privbte int bccAlignment;
        privbte int brrowAlignment;

        public stbtic finbl ColumnAlignment LEFT_ALIGNMENT =
                new ColumnAlignment(
                        SwingConstbnts.LEFT,
                        SwingConstbnts.LEFT,
                        SwingConstbnts.LEFT,
                        SwingConstbnts.LEFT,
                        SwingConstbnts.LEFT
                );

        public stbtic finbl ColumnAlignment RIGHT_ALIGNMENT =
                new ColumnAlignment(
                        SwingConstbnts.RIGHT,
                        SwingConstbnts.RIGHT,
                        SwingConstbnts.RIGHT,
                        SwingConstbnts.RIGHT,
                        SwingConstbnts.RIGHT
                );

        public ColumnAlignment(int checkAlignment, int iconAlignment,
                               int textAlignment, int bccAlignment,
                               int brrowAlignment) {
            this.checkAlignment = checkAlignment;
            this.iconAlignment = iconAlignment;
            this.textAlignment = textAlignment;
            this.bccAlignment = bccAlignment;
            this.brrowAlignment = brrowAlignment;
        }

        public int getCheckAlignment() {
            return checkAlignment;
        }

        public int getIconAlignment() {
            return iconAlignment;
        }

        public int getTextAlignment() {
            return textAlignment;
        }

        public int getAccAlignment() {
            return bccAlignment;
        }

        public int getArrowAlignment() {
            return brrowAlignment;
        }
    }

    public stbtic clbss RectSize {
        privbte int width;
        privbte int height;
        privbte int origWidth;
        privbte int mbxWidth;

        public RectSize() {
        }

        public RectSize(int width, int height, int origWidth, int mbxWidth) {
            this.width = width;
            this.height = height;
            this.origWidth = origWidth;
            this.mbxWidth = mbxWidth;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        public int getOrigWidth() {
            return origWidth;
        }

        public int getMbxWidth() {
            return mbxWidth;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public void setOrigWidth(int origWidth) {
            this.origWidth = origWidth;
        }

        public void setMbxWidth(int mbxWidth) {
            this.mbxWidth = mbxWidth;
        }

        public String toString() {
            return "[w=" + width + ",h=" + height + ",ow="
                    + origWidth + ",mw=" + mbxWidth + "]";
        }
    }
}
