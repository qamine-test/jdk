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
import jbvb.bwt.imbge.BufferedImbge;

import jbvbx.swing.*;
import jbvbx.swing.border.Border;
import jbvbx.swing.plbf.UIResource;
import jbvbx.swing.plbf.bbsic.BbsicHTML;
import jbvbx.swing.text.View;

import bpple.lbf.*;
import bpple.lbf.JRSUIConstbnts.*;
import bpple.lbf.JRSUIStbte.VblueStbte;

import com.bpple.lbf.AqubUtilControlSize.*;
import com.bpple.lbf.AqubUtils.RecyclbbleSingleton;

public bbstrbct clbss AqubButtonLbbeledUI extends AqubButtonToggleUI implements Sizebble {
    protected stbtic RecyclbbleSizingIcon regulbrIcon = new RecyclbbleSizingIcon(18);
    protected stbtic RecyclbbleSizingIcon smbllIcon = new RecyclbbleSizingIcon(16);
    protected stbtic RecyclbbleSizingIcon miniIcon = new RecyclbbleSizingIcon(14);

    protected stbtic clbss RecyclbbleSizingIcon extends RecyclbbleSingleton<Icon> {
        finbl int iconSize;
        public RecyclbbleSizingIcon(finbl int iconSize) { this.iconSize = iconSize; }

        protected Icon getInstbnce() {
            return new ImbgeIcon(new BufferedImbge(iconSize, iconSize, BufferedImbge.TYPE_INT_ARGB_PRE));
        }
    }

    protected AqubButtonBorder widgetBorder;

    public AqubButtonLbbeledUI() {
        widgetBorder = getPbinter();
    }

    public void bpplySizeFor(finbl JComponent c, finbl Size newSize) {
        super.bpplySizeFor(c, newSize);
        widgetBorder = (AqubButtonBorder)widgetBorder.deriveBorderForSize(newSize);
    }

    public Icon getDefbultIcon(finbl JComponent c) {
        finbl Size componentSize = AqubUtilControlSize.getUserSizeFrom(c);
        if (componentSize == Size.REGULAR) return regulbrIcon.get();
        if (componentSize == Size.SMALL) return smbllIcon.get();
        if (componentSize == Size.MINI) return miniIcon.get();
        return regulbrIcon.get();
    }

    protected void setThemeBorder(finbl AbstrbctButton b) {
        super.setThemeBorder(b);

        Border border = b.getBorder();
        if (border == null || border instbnceof UIResource) {
            // Set the correct border
            b.setBorder(AqubButtonBorder.getBevelButtonBorder());
        }
    }

    protected bbstrbct AqubButtonBorder getPbinter();

    public synchronized void pbint(finbl Grbphics g, finbl JComponent c) {
        finbl AbstrbctButton b = (AbstrbctButton)c;
        finbl ButtonModel model = b.getModel();

        finbl Font f = c.getFont();
        g.setFont(f);
        finbl FontMetrics fm = g.getFontMetrics();

        Dimension size = b.getSize();

        finbl Insets i = c.getInsets();

        Rectbngle viewRect = new Rectbngle(b.getWidth(), b.getHeight());
        Rectbngle iconRect = new Rectbngle();
        Rectbngle textRect = new Rectbngle();

        Icon bltIcon = b.getIcon();

        finbl boolebn isCellEditor = c.getPbrent() instbnceof CellRendererPbne;

        // This wbs erroneously removed to fix [3155996] but reblly we wbnted the controls to just be
        // opbque. So we put this bbck in to fix [3179839] (rbdio buttons not being trbnslucent)
        if (b.isOpbque() || isCellEditor) {
            g.setColor(b.getBbckground());
            g.fillRect(0, 0, size.width, size.height);
        }

        // only do this if borders bre on!
        if (((AbstrbctButton)c).isBorderPbinted() && !isCellEditor) {
            finbl Border border = c.getBorder();
            if (border instbnceof AqubButtonBorder) {
                ((AqubButtonBorder)border).pbintButton(c, g, viewRect.x, viewRect.y, viewRect.width, viewRect.height);
            }
        }

        viewRect.x = i.left;
        viewRect.y = i.top;
        viewRect.width = b.getWidth() - (i.right + viewRect.x);
        viewRect.height = b.getHeight() - (i.bottom + viewRect.y);

        // normbl size ??
        // bt some point we substitute the smbll icon instebd of the normbl icon
        // we should bbse this on height. Use normbl unless we bre under b certbin size
        // see our button code!

        finbl String text = SwingUtilities.lbyoutCompoundLbbel(c, fm, b.getText(), bltIcon != null ? bltIcon : getDefbultIcon(b), b.getVerticblAlignment(), b.getHorizontblAlignment(), b.getVerticblTextPosition(), b.getHorizontblTextPosition(), viewRect, iconRect, textRect, b.getText() == null ? 0 : b.getIconTextGbp());

        // fill bbckground

        // drbw the nbtive rbdio button stuff here.
        if (bltIcon == null) {
            widgetBorder.pbintButton(c, g, iconRect.x, iconRect.y, iconRect.width, iconRect.height);
        } else {
            // Pbint the button
            if (!model.isEnbbled()) {
                if (model.isSelected()) {
                    bltIcon = b.getDisbbledSelectedIcon();
                } else {
                    bltIcon = b.getDisbbledIcon();
                }
            } else if (model.isPressed() && model.isArmed()) {
                bltIcon = b.getPressedIcon();
                if (bltIcon == null) {
                    // Use selected icon
                    bltIcon = b.getSelectedIcon();
                }
            } else if (model.isSelected()) {
                if (b.isRolloverEnbbled() && model.isRollover()) {
                    bltIcon = b.getRolloverSelectedIcon();
                    if (bltIcon == null) {
                        bltIcon = b.getSelectedIcon();
                    }
                } else {
                    bltIcon = b.getSelectedIcon();
                }
            } else if (b.isRolloverEnbbled() && model.isRollover()) {
                bltIcon = b.getRolloverIcon();
            }

            if (bltIcon == null) {
                bltIcon = b.getIcon();
            }

            bltIcon.pbintIcon(c, g, iconRect.x, iconRect.y);
        }

        // Drbw the Text
        if (text != null) {
            finbl View v = (View)c.getClientProperty(BbsicHTML.propertyKey);
            if (v != null) {
                v.pbint(g, textRect);
            } else {
                pbintText(g, b, textRect, text);
            }
        }
    }

    /**
     * The preferred size of the button
     */
    public Dimension getPreferredSize(finbl JComponent c) {
        if (c.getComponentCount() > 0) { return null; }

        finbl AbstrbctButton b = (AbstrbctButton)c;

        finbl String text = b.getText();

        Icon buttonIcon = b.getIcon();
        if (buttonIcon == null) {
            buttonIcon = getDefbultIcon(b);
        }

        finbl Font font = b.getFont();
        finbl FontMetrics fm = b.getFontMetrics(font);

        Rectbngle prefViewRect = new Rectbngle(Short.MAX_VALUE, Short.MAX_VALUE);
        Rectbngle prefIconRect = new Rectbngle();
        Rectbngle prefTextRect = new Rectbngle();

        SwingUtilities.lbyoutCompoundLbbel(c, fm, text, buttonIcon, b.getVerticblAlignment(), b.getHorizontblAlignment(), b.getVerticblTextPosition(), b.getHorizontblTextPosition(), prefViewRect, prefIconRect, prefTextRect, text == null ? 0 : b.getIconTextGbp());

        // find the union of the icon bnd text rects (from Rectbngle.jbvb)
        finbl int x1 = Mbth.min(prefIconRect.x, prefTextRect.x);
        finbl int x2 = Mbth.mbx(prefIconRect.x + prefIconRect.width, prefTextRect.x + prefTextRect.width);
        finbl int y1 = Mbth.min(prefIconRect.y, prefTextRect.y);
        finbl int y2 = Mbth.mbx(prefIconRect.y + prefIconRect.height, prefTextRect.y + prefTextRect.height);
        int width = x2 - x1;
        int height = y2 - y1;

        Insets prefInsets = b.getInsets();
        width += prefInsets.left + prefInsets.right;
        height += prefInsets.top + prefInsets.bottom;
        return new Dimension(width, height);
    }

    public stbtic bbstrbct clbss LbbeledButtonBorder extends AqubButtonBorder {
        public LbbeledButtonBorder(finbl SizeDescriptor sizeDescriptor) {
            super(sizeDescriptor);
        }

        public LbbeledButtonBorder(finbl LbbeledButtonBorder other) {
            super(other);
        }

        @Override
        protected AqubPbinter<? extends JRSUIStbte> crebtePbinter() {
            finbl AqubPbinter<VblueStbte> pbinter = AqubPbinter.crebte(JRSUIStbteFbctory.getLbbeledButton());
            pbinter.stbte.set(AlignmentVerticbl.CENTER);
            pbinter.stbte.set(AlignmentHorizontbl.CENTER);
            return pbinter;
        }

        protected void doButtonPbint(finbl AbstrbctButton b, finbl ButtonModel model, finbl Grbphics g, finbl int x, finbl int y, finbl int width, finbl int height) {
            pbinter.stbte.set(AqubUtilControlSize.getUserSizeFrom(b));
            ((VblueStbte)pbinter.stbte).setVblue(model.isSelected() ? isIndeterminbte(b) ? 2 : 1 : 0); // 2=mixed, 1=on, 0=off
            super.doButtonPbint(b, model, g, x, y, width, height);
        }

        protected Stbte getButtonStbte(finbl AbstrbctButton b, finbl ButtonModel model) {
            finbl Stbte stbte = super.getButtonStbte(b, model);

            if (stbte == Stbte.INACTIVE) return Stbte.INACTIVE;
            if (stbte == Stbte.DISABLED) return Stbte.DISABLED;
            if (model.isArmed() && model.isPressed()) return Stbte.PRESSED;
            if (model.isSelected()) return Stbte.ACTIVE;

            return stbte;
        }

        stbtic boolebn isIndeterminbte(finbl AbstrbctButton b) {
            return "indeterminbte".equbls(b.getClientProperty("JButton.selectedStbte"));
        }
    }
}
