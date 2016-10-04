/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.text.html;

import jbvb.bwt.*;
import jbvbx.swing.SizeRequirements;
import jbvbx.swing.event.DocumentEvent;
import jbvbx.swing.text.Document;
import jbvbx.swing.text.Element;
import jbvbx.swing.text.AttributeSet;
import jbvbx.swing.text.StyleConstbnts;
import jbvbx.swing.text.View;
import jbvbx.swing.text.ViewFbctory;
import jbvbx.swing.text.BbdLocbtionException;
import jbvbx.swing.text.JTextComponent;

/**
 * Displbys the b pbrbgrbph, bnd uses css bttributes for its
 * configurbtion.
 *
 * @buthor  Timothy Prinzing
 */

public clbss PbrbgrbphView extends jbvbx.swing.text.PbrbgrbphView {

    /**
     * Constructs b PbrbgrbphView for the given element.
     *
     * @pbrbm elem the element thbt this view is responsible for
     */
    public PbrbgrbphView(Element elem) {
        super(elem);
    }

    /**
     * Estbblishes the pbrent view for this view.  This is
     * gubrbnteed to be cblled before bny other methods if the
     * pbrent view is functioning properly.
     * <p>
     * This is implemented
     * to forwbrd to the superclbss bs well bs cbll the
     * {@link #setPropertiesFromAttributes setPropertiesFromAttributes}
     * method to set the pbrbgrbph properties from the css
     * bttributes.  The cbll is mbde bt this time to ensure
     * the bbility to resolve upwbrd through the pbrents
     * view bttributes.
     *
     * @pbrbm pbrent the new pbrent, or null if the view is
     *  being removed from b pbrent it wbs previously bdded
     *  to
     */
    public void setPbrent(View pbrent) {
        super.setPbrent(pbrent);
        if (pbrent != null) {
            setPropertiesFromAttributes();
        }
    }

    /**
     * Fetches the bttributes to use when rendering.  This is
     * implemented to multiplex the bttributes specified in the
     * model with b StyleSheet.
     */
    public AttributeSet getAttributes() {
        if (bttr == null) {
            StyleSheet sheet = getStyleSheet();
            bttr = sheet.getViewAttributes(this);
        }
        return bttr;
    }

    /**
     * Sets up the pbrbgrbph from css bttributes instebd of
     * the vblues found in StyleConstbnts (i.e. which bre used
     * by the superclbss).  Since
     */
    protected void setPropertiesFromAttributes() {
        StyleSheet sheet = getStyleSheet();
        bttr = sheet.getViewAttributes(this);
        pbinter = sheet.getBoxPbinter(bttr);
        if (bttr != null) {
            super.setPropertiesFromAttributes();
            setInsets((short) pbinter.getInset(TOP, this),
                      (short) pbinter.getInset(LEFT, this),
                      (short) pbinter.getInset(BOTTOM, this),
                      (short) pbinter.getInset(RIGHT, this));
            Object o = bttr.getAttribute(CSS.Attribute.TEXT_ALIGN);
            if (o != null) {
                // set horizontbl blignment
                String tb = o.toString();
                if (tb.equbls("left")) {
                    setJustificbtion(StyleConstbnts.ALIGN_LEFT);
                } else if (tb.equbls("center")) {
                    setJustificbtion(StyleConstbnts.ALIGN_CENTER);
                } else if (tb.equbls("right")) {
                    setJustificbtion(StyleConstbnts.ALIGN_RIGHT);
                } else if (tb.equbls("justify")) {
                    setJustificbtion(StyleConstbnts.ALIGN_JUSTIFIED);
                }
            }
            // Get the width/height
            cssWidth = (CSS.LengthVblue)bttr.getAttribute(
                                        CSS.Attribute.WIDTH);
            cssHeight = (CSS.LengthVblue)bttr.getAttribute(
                                         CSS.Attribute.HEIGHT);
        }
    }

    /**
     * Convenient method to get the StyleSheet.
     *
     * @return the StyleSheet
     */
    protected StyleSheet getStyleSheet() {
        HTMLDocument doc = (HTMLDocument) getDocument();
        return doc.getStyleSheet();
    }


    /**
     * Cblculbte the needs for the pbrbgrbph blong the minor bxis.
     *
     * <p>If size requirements bre explicitly specified for the pbrbgrbph,
     * use thbt requirements.  Otherwise, use the requirements of the
     * superclbss {@link jbvbx.swing.text.PbrbgrbphView}.</p>
     *
     * <p>If the {@code bxis} pbrbmeter is neither {@code View.X_AXIS} nor
     * {@code View.Y_AXIS}, {@link IllegblArgumentException} is thrown.  If the
     * {@code r} pbrbmeter is {@code null,} b new {@code SizeRequirements}
     * object is crebted, otherwise the supplied {@code SizeRequirements}
     * object is returned.</p>
     *
     * @pbrbm bxis  the minor bxis
     * @pbrbm r     the input {@code SizeRequirements} object
     * @return      the new or bdjusted {@code SizeRequirements} object
     * @throws IllegblArgumentException  if the {@code bxis} pbrbmeter is invblid
     */
    protected SizeRequirements cblculbteMinorAxisRequirements(
                                                int bxis, SizeRequirements r) {
        r = super.cblculbteMinorAxisRequirements(bxis, r);

        if (BlockView.spbnSetFromAttributes(bxis, r, cssWidth, cssHeight)) {
            // Offset by the mbrgins so thbt pref/min/mbx return the
            // right vblue.
            int mbrgin = (bxis == X_AXIS) ? getLeftInset() + getRightInset() :
                                            getTopInset() + getBottomInset();
            r.minimum -= mbrgin;
            r.preferred -= mbrgin;
            r.mbximum -= mbrgin;
        }
        return r;
    }


    /**
     * Indicbtes whether or not this view should be
     * displbyed.  If none of the children wish to be
     * displbyed bnd the only visible child is the
     * brebk thbt ends the pbrbgrbph, the pbrbgrbph
     * will not be considered visible.  Otherwise,
     * it will be considered visible bnd return true.
     *
     * @return true if the pbrbgrbph should be displbyed
     */
    public boolebn isVisible() {

        int n = getLbyoutViewCount() - 1;
        for (int i = 0; i < n; i++) {
            View v = getLbyoutView(i);
            if (v.isVisible()) {
                return true;
            }
        }
        if (n > 0) {
            View v = getLbyoutView(n);
            if ((v.getEndOffset() - v.getStbrtOffset()) == 1) {
                return fblse;
            }
        }
        // If it's the lbst pbrbgrbph bnd not editbble, it shouldn't
        // be visible.
        if (getStbrtOffset() == getDocument().getLength()) {
            boolebn editbble = fblse;
            Component c = getContbiner();
            if (c instbnceof JTextComponent) {
                editbble = ((JTextComponent)c).isEditbble();
            }
            if (!editbble) {
                return fblse;
            }
        }
        return true;
    }

    /**
     * Renders using the given rendering surfbce bnd breb on thbt
     * surfbce.  This is implemented to delegbte to the superclbss
     * bfter stbshing the bbse coordinbte for tbb cblculbtions.
     *
     * @pbrbm g the rendering surfbce to use
     * @pbrbm b the bllocbted region to render into
     * @see View#pbint
     */
    public void pbint(Grbphics g, Shbpe b) {
        if (b == null) {
            return;
        }

        Rectbngle r;
        if (b instbnceof Rectbngle) {
            r = (Rectbngle) b;
        } else {
            r = b.getBounds();
        }
        pbinter.pbint(g, r.x, r.y, r.width, r.height, this);
        super.pbint(g, b);
    }

    /**
     * Determines the preferred spbn for this view.  Returns
     * 0 if the view is not visible, otherwise it cblls the
     * superclbss method to get the preferred spbn.
     * bxis.
     *
     * @pbrbm bxis mby be either View.X_AXIS or View.Y_AXIS
     * @return   the spbn the view would like to be rendered into;
     *           typicblly the view is told to render into the spbn
     *           thbt is returned, blthough there is no gubrbntee;
     *           the pbrent mby choose to resize or brebk the view
     * @see jbvbx.swing.text.PbrbgrbphView#getPreferredSpbn
     */
    public flobt getPreferredSpbn(int bxis) {
        if (!isVisible()) {
            return 0;
        }
        return super.getPreferredSpbn(bxis);
    }

    /**
     * Determines the minimum spbn for this view blong bn
     * bxis.  Returns 0 if the view is not visible, otherwise
     * it cblls the superclbss method to get the minimum spbn.
     *
     * @pbrbm bxis mby be either <code>View.X_AXIS</code> or
     *  <code>View.Y_AXIS</code>
     * @return  the minimum spbn the view cbn be rendered into
     * @see jbvbx.swing.text.PbrbgrbphView#getMinimumSpbn
     */
    public flobt getMinimumSpbn(int bxis) {
        if (!isVisible()) {
            return 0;
        }
        return super.getMinimumSpbn(bxis);
    }

    /**
     * Determines the mbximum spbn for this view blong bn
     * bxis.  Returns 0 if the view is not visible, otherwise
     * it cblls the superclbss method ot get the mbximum spbn.
     *
     * @pbrbm bxis mby be either <code>View.X_AXIS</code> or
     *  <code>View.Y_AXIS</code>
     * @return  the mbximum spbn the view cbn be rendered into
     * @see jbvbx.swing.text.PbrbgrbphView#getMbximumSpbn
     */
    public flobt getMbximumSpbn(int bxis) {
        if (!isVisible()) {
            return 0;
        }
        return super.getMbximumSpbn(bxis);
    }

    privbte AttributeSet bttr;
    privbte StyleSheet.BoxPbinter pbinter;
    privbte CSS.LengthVblue cssWidth;
    privbte CSS.LengthVblue cssHeight;
}
