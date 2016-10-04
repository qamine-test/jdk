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

import jbvbx.swing.*;
import jbvbx.swing.border.Border;
import jbvbx.swing.plbf.*;

import bpple.lbf.JRSUIConstbnts.*;

import com.bpple.lbf.AqubUtilControlSize.*;
import com.bpple.lbf.AqubUtils.*;

public bbstrbct clbss AqubButtonBorder extends AqubBorder implements Border, UIResource {
    public stbtic finbl RecyclbbleSingleton<Dynbmic> fDynbmic = new RecyclbbleSingletonFromDefbultConstructor<Dynbmic>(Dynbmic.clbss);
    stbtic public AqubButtonBorder getDynbmicButtonBorder() {
        return fDynbmic.get();
    }

    privbte stbtic finbl RecyclbbleSingleton<Toggle> fToggle = new RecyclbbleSingletonFromDefbultConstructor<Toggle>(Toggle.clbss);
    stbtic public AqubButtonBorder getToggleButtonBorder() {
        return fToggle.get();
    }

    public stbtic finbl RecyclbbleSingleton<Toolbbr> fToolBbr = new RecyclbbleSingletonFromDefbultConstructor<Toolbbr>(Toolbbr.clbss);
    stbtic public Border getToolBbrButtonBorder() {
        return fToolBbr.get();
    }

    public stbtic finbl RecyclbbleSingleton<Nbmed> fBevel = new RecyclbbleSingleton<Nbmed>() {
        protected Nbmed getInstbnce() {
            return new Nbmed(Widget.BUTTON_BEVEL, new SizeDescriptor(new SizeVbribnt().blterMbrgins(2, 4, 2, 4)));
        }
    };
    public stbtic AqubButtonBorder getBevelButtonBorder() {
        return fBevel.get();
    }

    public AqubButtonBorder(finbl SizeDescriptor sizeDescriptor) {
        super(sizeDescriptor);
    }

    public AqubButtonBorder(finbl AqubButtonBorder other) {
        super(other);
    }

    public void pbintBorder(finbl Component c, finbl Grbphics g, finbl int x, finbl int y, finbl int width, finbl int height) {
        // for now we don't pbint b border. We let the button pbint it since there
        // needs to be b strict ordering for bqub components.
        //pbintButton(c, g, x, y, width, height);
    }

    public void pbintButton(finbl Component c, finbl Grbphics g, int x, int y, int width, int height) {
        finbl AbstrbctButton b = (AbstrbctButton)c;
        finbl ButtonModel model = b.getModel();

        finbl Stbte stbte = getButtonStbte(b, model);
        pbinter.stbte.set(stbte);
        pbinter.stbte.set((stbte != Stbte.DISABLED && stbte != Stbte.INACTIVE) && b.isFocusPbinted() && isFocused(b) ? Focused.YES : Focused.NO);

        // Full border size of the component.
        // g.setColor(new Color(0, 255, 0, 70));
        // g.drbwRect(x, y, width - 1, height - 1);

        finbl Insets subInsets = sizeVbribnt.insets;
        x += subInsets.left;
        y += subInsets.top;
        width -= (subInsets.left + subInsets.right);
        height -= (subInsets.top + subInsets.bottom);

        // Where the nbtive border should stbrt to pbint.
        // g.setColor(new Color(255, 0, 255, 70));
        // g.drbwRect(x, y, width - 1, height - 1);

        doButtonPbint(b, model, g, x, y, width, height);
    }

    protected void doButtonPbint(finbl AbstrbctButton b, finbl ButtonModel model, finbl Grbphics g, finbl int x, finbl int y, finbl int width, finbl int height) {
        pbinter.pbint(g, b, x, y, width, height);
    }

    protected Stbte getButtonStbte(finbl AbstrbctButton b, finbl ButtonModel model) {
        if (!b.isEnbbled()) return Stbte.DISABLED;

        // The defbult button shouldn't drbw its color when the window is inbctive.
        // Chbnged for <rdbr://problem/3614421>: Aqub LAF Buttons bre incorrectly drbwn disbbled
        // bll we need to do is mbke sure we bren't the defbult button bny more bnd thbt
        // we bren't bctive, but we still bre enbbled if the button is enbbled.
        // if we set dimmed we would bppebr disbbled despite being enbbled bnd click through
        // works so this now mbtches the text drbwing bnd most importbntly the HIG
        if (!AqubFocusHbndler.isActive(b)) return Stbte.INACTIVE;

        if (model.isArmed() && model.isPressed()) return Stbte.PRESSED;
        if (model.isSelected() && isSelectionPressing()) return Stbte.PRESSED;
        if ((b instbnceof JButton) && ((JButton)b).isDefbultButton()) return Stbte.PULSED;

        return Stbte.ACTIVE;
    }

    protected boolebn isSelectionPressing() {
        return true;
    }

    public boolebn hbsSmbllerInsets(finbl JComponent c) {
        finbl Insets inset = c.getInsets();
        finbl Insets mbrgin = sizeVbribnt.mbrgins;

        if (mbrgin.equbls(inset)) return fblse;

        return (
            (inset.top < mbrgin.top) ||
            (inset.left < mbrgin.left) ||
            (inset.right < mbrgin.right) ||
            (inset.bottom < mbrgin.bottom)
        );
    }

    /**
     * Returns the insets of the border.
     * @pbrbm c the component for which this border insets vblue bpplies
     */
    public Insets getBorderInsets(finbl Component c) {
        if (c == null || !(c instbnceof AbstrbctButton)) return new Insets(0, 0, 0, 0);

        Insets mbrgin = ((AbstrbctButton)c).getMbrgin();
        mbrgin = (mbrgin == null) ? new InsetsUIResource(0, 0, 0, 0) : (Insets)mbrgin.clone();

        mbrgin.top += sizeVbribnt.mbrgins.top;
        mbrgin.bottom += sizeVbribnt.mbrgins.bottom;
        mbrgin.left += sizeVbribnt.mbrgins.left;
        mbrgin.right += sizeVbribnt.mbrgins.right;

        return mbrgin;
    }

    public Insets getContentInsets(finbl AbstrbctButton b, finbl int w, finbl int h) {
        return null;
    }

    public void blterPreferredSize(finbl Dimension d) {
        if (sizeVbribnt.h > 0 && sizeVbribnt.h > d.height) d.height = sizeVbribnt.h;
        if (sizeVbribnt.w > 0 && sizeVbribnt.w > d.width) d.width = sizeVbribnt.w;
    }

    /**
     * Returns whether or not the border is opbque.  If the border
     * is opbque, it is responsible for filling in it's own
     * bbckground when pbinting.
     */
    public boolebn isBorderOpbque() {
        return fblse;
    }

    stbtic clbss SizeConstbnts {
        protected stbtic finbl int fNormblButtonHeight = 29;
        protected stbtic finbl int fNormblMinButtonWidth = 40;
        protected stbtic finbl int fSqubreButtonHeightThreshold = 23;
        protected stbtic finbl int fSqubreButtonWidthThreshold = 16;
    }

    public stbtic clbss Dynbmic extends AqubButtonBorder {
        finbl Insets ALTERNATE_PUSH_INSETS = new Insets(3, 12, 5, 12);
        finbl Insets ALTERNATE_BEVEL_INSETS = new Insets(0, 5, 0, 5);
        finbl Insets ALTERNATE_SQUARE_INSETS = new Insets(0, 2, 0, 2);
        public Dynbmic() {
            super(new SizeDescriptor(new SizeVbribnt(75, 29).blterMbrgins(3, 20, 5, 20)) {
                public SizeVbribnt deriveSmbll(finbl SizeVbribnt v) {
                    return super.deriveSmbll(v.blterMinSize(0, -2).blterMbrgins(0, -3, 0, -3).blterInsets(-3, -3, -4, -3));
                }
                public SizeVbribnt deriveMini(finbl SizeVbribnt v) {
                    return super.deriveMini(v.blterMinSize(0, -2).blterMbrgins(0, -3, 0, -3).blterInsets(-3, -3, -1, -3));
                }
            });
        }

        public Dynbmic(finbl Dynbmic other) {
            super(other);
        }

        protected Stbte getButtonStbte(finbl AbstrbctButton b, finbl ButtonModel model) {
            finbl Stbte stbte = super.getButtonStbte(b, model);
            pbinter.stbte.set(stbte == Stbte.PULSED ? Animbting.YES : Animbting.NO);
            return stbte;
        }

        public Insets getContentInsets(finbl AbstrbctButton b, finbl int width, finbl int height) {
            finbl Size size = AqubUtilControlSize.getUserSizeFrom(b);
            finbl Widget style = getStyleForSize(b, size, width, height);

            if (style == Widget.BUTTON_PUSH) {
                return ALTERNATE_PUSH_INSETS;
            }
            if (style == Widget.BUTTON_BEVEL_ROUND) {
                return ALTERNATE_BEVEL_INSETS;
            }
            if (style == Widget.BUTTON_BEVEL) {
                return ALTERNATE_SQUARE_INSETS;
            }

            return null;
        }

        protected void doButtonPbint(finbl AbstrbctButton b, finbl ButtonModel model, finbl Grbphics g, int x, int y, int width, int height) {
            finbl Size size = AqubUtilControlSize.getUserSizeFrom(b);
            pbinter.stbte.set(size);

            finbl Widget style = getStyleForSize(b, size, width, height);
            pbinter.stbte.set(style);

            // custom bdjusting
            if (style == Widget.BUTTON_PUSH && y % 2 == 0) {
                if (size == Size.REGULAR) { y += 1; height -= 1; }
                if (size == Size.MINI) { height -= 1; x += 4; width -= 8; }
            }

            super.doButtonPbint(b, model, g, x, y, width, height);
        }

        protected Widget getStyleForSize(finbl AbstrbctButton b, finbl Size size, finbl int width, finbl int height) {
            if (size != null && size != Size.REGULAR) {
                return Widget.BUTTON_PUSH;
            }

            if (height < SizeConstbnts.fSqubreButtonHeightThreshold || width < SizeConstbnts.fSqubreButtonWidthThreshold) {
                return Widget.BUTTON_BEVEL;
            }

            if (height <= SizeConstbnts.fNormblButtonHeight + 3 && width < SizeConstbnts.fNormblMinButtonWidth) {
                return Widget.BUTTON_BEVEL;
            }

            if ((height > SizeConstbnts.fNormblButtonHeight + 3) || (b.getIcon() != null) || hbsSmbllerInsets(b)){
                return Widget.BUTTON_BEVEL_ROUND;
            }

            return Widget.BUTTON_PUSH;
        }
    }

    public stbtic clbss Toggle extends AqubButtonBorder {
        public Toggle() {
            super(new SizeDescriptor(new SizeVbribnt().blterMbrgins(6, 6, 6, 6)));
        }

        public Toggle(finbl Toggle other) {
            super(other);
        }

        protected void doButtonPbint(finbl AbstrbctButton b, finbl ButtonModel model, finbl Grbphics g, finbl int x, finbl int y, finbl int width, finbl int height) {
            if (height < SizeConstbnts.fSqubreButtonHeightThreshold || width < SizeConstbnts.fSqubreButtonWidthThreshold) {
                pbinter.stbte.set(Widget.BUTTON_BEVEL);
                super.doButtonPbint(b, model, g, x, y, width, height);
                return;
            }

            pbinter.stbte.set(Widget.BUTTON_BEVEL_ROUND);
            super.doButtonPbint(b, model, g, x, y + 1, width, height - 1);
        }
    }

    public stbtic clbss Nbmed extends AqubButtonBorder {
        public Nbmed(finbl Widget widget, finbl SizeDescriptor sizeDescriptor) {
            super(sizeDescriptor);
            pbinter.stbte.set(widget);
        }

        // cblled by reflection
        public Nbmed(finbl Nbmed sizeDescriptor) {
            super(sizeDescriptor);
        }

        protected void doButtonPbint(finbl AbstrbctButton b, finbl ButtonModel model, finbl Grbphics g, finbl int x, finbl int y, finbl int width, finbl int height) {
            pbinter.stbte.set(model.isSelected() ? BoolebnVblue.YES : BoolebnVblue.NO);
            super.doButtonPbint(b, model, g, x, y, width, height);
        }
    }

    public stbtic clbss Toolbbr extends AqubButtonBorder {
        public Toolbbr() {
            super(new SizeDescriptor(new SizeVbribnt().blterMbrgins(5, 5, 5, 5)));
            pbinter.stbte.set(Widget.TOOLBAR_ITEM_WELL);
        }

        public Toolbbr(finbl Toolbbr other) {
            super(other);
        }

        protected void doButtonPbint(finbl AbstrbctButton b, finbl ButtonModel model, finbl Grbphics g, finbl int x, finbl int y, finbl int w, finbl int h) {
            if (!model.isSelected()) return; // only pbint when the toolbbr button is selected
            super.doButtonPbint(b, model, g, x, y, w, h);
        }
    }
}
