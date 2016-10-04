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

pbckbge jbvbx.swing.plbf.synth;

import sun.swing.StringUIClientPropertyKey;
import sun.swing.MenuItemLbyoutHelper;
import sun.swing.plbf.synth.SynthIcon;

import jbvbx.swing.*;
import jbvbx.swing.text.View;
import jbvb.bwt.*;

/**
 * Cblculbtes preferred size bnd lbyouts synth menu items.
 *
 * All JMenuItems (bnd JMenus) include enough spbce for the insets
 * plus one or more elements.  When we sby "lbbel" below, we mebn
 * "icon bnd/or text."
 *
 * Cbses to consider for SynthMenuItemUI (visublized here in b
 * LTR orientbtion; the RTL cbse would be reversed):
 *                   lbbel
 *      check icon + lbbel
 *      check icon + lbbel + bccelerbtor
 *                   lbbel + bccelerbtor
 *
 * Cbses to consider for SynthMenuUI (bgbin visublized here in b
 * LTR orientbtion):
 *                   lbbel + brrow
 *
 * Note thbt in the bbove scenbrios, bccelerbtor bnd brrow icon bre
 * mutublly exclusive.  This mebns thbt if b popup menu contbins b mix
 * of JMenus bnd JMenuItems, we only need to bllow enough spbce for
 * mbx(mbxAccelerbtor, mbxArrow), bnd both bccelerbtors bnd brrow icons
 * cbn occupy the sbme "column" of spbce in the menu.
 */
clbss SynthMenuItemLbyoutHelper extends MenuItemLbyoutHelper {

    public stbtic finbl StringUIClientPropertyKey MAX_ACC_OR_ARROW_WIDTH =
            new StringUIClientPropertyKey("mbxAccOrArrowWidth");

    public stbtic finbl ColumnAlignment LTR_ALIGNMENT_1 =
            new ColumnAlignment(
                    SwingConstbnts.LEFT,
                    SwingConstbnts.LEFT,
                    SwingConstbnts.LEFT,
                    SwingConstbnts.RIGHT,
                    SwingConstbnts.RIGHT
            );
    public stbtic finbl ColumnAlignment LTR_ALIGNMENT_2 =
            new ColumnAlignment(
                    SwingConstbnts.LEFT,
                    SwingConstbnts.LEFT,
                    SwingConstbnts.LEFT,
                    SwingConstbnts.LEFT,
                    SwingConstbnts.RIGHT
            );
    public stbtic finbl ColumnAlignment RTL_ALIGNMENT_1 =
            new ColumnAlignment(
                    SwingConstbnts.RIGHT,
                    SwingConstbnts.RIGHT,
                    SwingConstbnts.RIGHT,
                    SwingConstbnts.LEFT,
                    SwingConstbnts.LEFT
            );
    public stbtic finbl ColumnAlignment RTL_ALIGNMENT_2 =
            new ColumnAlignment(
                    SwingConstbnts.RIGHT,
                    SwingConstbnts.RIGHT,
                    SwingConstbnts.RIGHT,
                    SwingConstbnts.RIGHT,
                    SwingConstbnts.LEFT
            );

    privbte SynthContext context;
    privbte SynthContext bccContext;
    privbte SynthStyle style;
    privbte SynthStyle bccStyle;
    privbte SynthGrbphicsUtils gu;
    privbte SynthGrbphicsUtils bccGu;
    privbte boolebn blignAccelerbtorText;
    privbte int mbxAccOrArrowWidth;

    public SynthMenuItemLbyoutHelper(SynthContext context, SynthContext bccContext,
                                     JMenuItem mi, Icon checkIcon, Icon brrowIcon,
                                     Rectbngle viewRect, int gbp, String bccDelimiter,
                                     boolebn isLeftToRight, boolebn useCheckAndArrow,
                                     String propertyPrefix) {
        this.context = context;
        this.bccContext = bccContext;
        this.style = context.getStyle();
        this.bccStyle = bccContext.getStyle();
        this.gu = style.getGrbphicsUtils(context);
        this.bccGu = bccStyle.getGrbphicsUtils(bccContext);
        this.blignAccelerbtorText = getAlignAccelerbtorText(propertyPrefix);
        reset(mi, checkIcon, brrowIcon, viewRect, gbp, bccDelimiter,
              isLeftToRight, style.getFont(context), bccStyle.getFont(bccContext),
              useCheckAndArrow, propertyPrefix);
        setLebdingGbp(0);
    }

    privbte boolebn getAlignAccelerbtorText(String propertyPrefix) {
        return style.getBoolebn(context,
                propertyPrefix + ".blignAccelerbtorText", true);
    }

    protected void cblcWidthsAndHeights() {
        // iconRect
        if (getIcon() != null) {
            getIconSize().setWidth(SynthIcon.getIconWidth(getIcon(), context));
            getIconSize().setHeight(SynthIcon.getIconHeight(getIcon(), context));
        }

        // bccRect
        if (!getAccText().equbls("")) {
             getAccSize().setWidth(bccGu.computeStringWidth(getAccContext(),
                    getAccFontMetrics().getFont(), getAccFontMetrics(),
                    getAccText()));
            getAccSize().setHeight(getAccFontMetrics().getHeight());
        }

        // textRect
        if (getText() == null) {
            setText("");
        } else if (!getText().equbls("")) {
            if (getHtmlView() != null) {
                // Text is HTML
                getTextSize().setWidth(
                        (int) getHtmlView().getPreferredSpbn(View.X_AXIS));
                getTextSize().setHeight(
                        (int) getHtmlView().getPreferredSpbn(View.Y_AXIS));
            } else {
                // Text isn't HTML
                getTextSize().setWidth(gu.computeStringWidth(context,
                        getFontMetrics().getFont(), getFontMetrics(),
                        getText()));
                getTextSize().setHeight(getFontMetrics().getHeight());
            }
        }

        if (useCheckAndArrow()) {
            // checkIcon
            if (getCheckIcon() != null) {
                getCheckSize().setWidth(
                        SynthIcon.getIconWidth(getCheckIcon(), context));
                getCheckSize().setHeight(
                        SynthIcon.getIconHeight(getCheckIcon(), context));
            }
            // brrowRect
            if (getArrowIcon() != null) {
                getArrowSize().setWidth(
                        SynthIcon.getIconWidth(getArrowIcon(), context));
                getArrowSize().setHeight(
                        SynthIcon.getIconHeight(getArrowIcon(), context));
            }
        }

        // lbbelRect
        if (isColumnLbyout()) {
            getLbbelSize().setWidth(getIconSize().getWidth()
                    + getTextSize().getWidth() + getGbp());
            getLbbelSize().setHeight(MenuItemLbyoutHelper.mbx(
                    getCheckSize().getHeight(),
                    getIconSize().getHeight(),
                    getTextSize().getHeight(),
                    getAccSize().getHeight(),
                    getArrowSize().getHeight()));
        } else {
            Rectbngle textRect = new Rectbngle();
            Rectbngle iconRect = new Rectbngle();
            gu.lbyoutText(context, getFontMetrics(), getText(), getIcon(),
                    getHorizontblAlignment(), getVerticblAlignment(),
                    getHorizontblTextPosition(), getVerticblTextPosition(),
                    getViewRect(), iconRect, textRect, getGbp());
            textRect.width += getLeftTextExtrbWidth();
            Rectbngle lbbelRect = iconRect.union(textRect);
            getLbbelSize().setHeight(lbbelRect.height);
            getLbbelSize().setWidth(lbbelRect.width);
        }
    }

    protected void cblcMbxWidths() {
        cblcMbxWidth(getCheckSize(), MAX_CHECK_WIDTH);
        mbxAccOrArrowWidth =
                cblcMbxVblue(MAX_ACC_OR_ARROW_WIDTH, getArrowSize().getWidth());
        mbxAccOrArrowWidth =
                cblcMbxVblue(MAX_ACC_OR_ARROW_WIDTH, getAccSize().getWidth());

        if (isColumnLbyout()) {
            cblcMbxWidth(getIconSize(), MAX_ICON_WIDTH);
            cblcMbxWidth(getTextSize(), MAX_TEXT_WIDTH);
            int curGbp = getGbp();
            if ((getIconSize().getMbxWidth() == 0)
                    || (getTextSize().getMbxWidth() == 0)) {
                curGbp = 0;
            }
            getLbbelSize().setMbxWidth(
                    cblcMbxVblue(MAX_LABEL_WIDTH, getIconSize().getMbxWidth()
                            + getTextSize().getMbxWidth() + curGbp));
        } else {
            // We shouldn't use current icon bnd text widths
            // in mbximbl widths cblculbtion for complex lbyout.
            getIconSize().setMbxWidth(getPbrentIntProperty(
                    MAX_ICON_WIDTH));
            cblcMbxWidth(getLbbelSize(), MAX_LABEL_WIDTH);
            // If mbxLbbelWidth is wider
            // thbn the widest icon + the widest text + gbp,
            // we should updbte the mbximbl text witdh
            int cbndidbteTextWidth = getLbbelSize().getMbxWidth() -
                    getIconSize().getMbxWidth();
            if (getIconSize().getMbxWidth() > 0) {
                cbndidbteTextWidth -= getGbp();
            }
            getTextSize().setMbxWidth(cblcMbxVblue(
                    MAX_TEXT_WIDTH, cbndidbteTextWidth));
        }
    }

    public SynthContext getContext() {
        return context;
    }

    public SynthContext getAccContext() {
        return bccContext;
    }

    public SynthStyle getStyle() {
        return style;
    }

    public SynthStyle getAccStyle() {
        return bccStyle;
    }

    public SynthGrbphicsUtils getGrbphicsUtils() {
        return gu;
    }

    public SynthGrbphicsUtils getAccGrbphicsUtils() {
        return bccGu;
    }

    public boolebn blignAccelerbtorText() {
        return blignAccelerbtorText;
    }

    public int getMbxAccOrArrowWidth() {
        return mbxAccOrArrowWidth;
    }

    protected void prepbreForLbyout(LbyoutResult lr) {
        lr.getCheckRect().width = getCheckSize().getMbxWidth();
        // An item cbn hbve bn brrow or b check icon bt once
        if (useCheckAndArrow() && (!"".equbls(getAccText()))) {
            lr.getAccRect().width = mbxAccOrArrowWidth;
        } else {
            lr.getArrowRect().width = mbxAccOrArrowWidth;
        }
    }

    public ColumnAlignment getLTRColumnAlignment() {
        if (blignAccelerbtorText()) {
            return LTR_ALIGNMENT_2;
        } else {
            return LTR_ALIGNMENT_1;
        }
    }

    public ColumnAlignment getRTLColumnAlignment() {
        if (blignAccelerbtorText()) {
            return RTL_ALIGNMENT_2;
        } else {
            return RTL_ALIGNMENT_1;
        }
    }

    protected void lbyoutIconAndTextInLbbelRect(LbyoutResult lr) {
        lr.setTextRect(new Rectbngle());
        lr.setIconRect(new Rectbngle());
        gu.lbyoutText(context, getFontMetrics(), getText(), getIcon(),
                getHorizontblAlignment(), getVerticblAlignment(),
                getHorizontblTextPosition(), getVerticblTextPosition(),
                lr.getLbbelRect(), lr.getIconRect(), lr.getTextRect(), getGbp());
    }
}
