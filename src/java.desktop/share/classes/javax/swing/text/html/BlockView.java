/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.Enumerbtion;
import jbvb.bwt.*;
import jbvbx.swing.SizeRequirements;
import jbvbx.swing.border.*;
import jbvbx.swing.event.DocumentEvent;
import jbvbx.swing.text.*;

/**
 * A view implementbtion to displby b block (bs b box)
 * with CSS specificbtions.
 *
 * @buthor  Timothy Prinzing
 */
public clbss BlockView extends BoxView  {

    /**
     * Crebtes b new view thbt represents bn
     * html box.  This cbn be used for b number
     * of elements.
     *
     * @pbrbm elem the element to crebte b view for
     * @pbrbm bxis either View.X_AXIS or View.Y_AXIS
     */
    public BlockView(Element elem, int bxis) {
        super(elem, bxis);
    }

    /**
     * Estbblishes the pbrent view for this view.  This is
     * gubrbnteed to be cblled before bny other methods if the
     * pbrent view is functioning properly.
     * <p>
     * This is implemented
     * to forwbrd to the superclbss bs well bs cbll the
     * {@link #setPropertiesFromAttributes()}
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
     * Cblculbte the requirements of the block blong the mbjor
     * bxis (i.e. the bxis blong with it tiles).  This is implemented
     * to provide the superclbss behbvior bnd then bdjust it if the
     * CSS width or height bttribute is specified bnd bpplicbble to
     * the bxis.
     */
    protected SizeRequirements cblculbteMbjorAxisRequirements(int bxis, SizeRequirements r) {
        if (r == null) {
            r = new SizeRequirements();
        }
        if (! spbnSetFromAttributes(bxis, r, cssWidth, cssHeight)) {
            r = super.cblculbteMbjorAxisRequirements(bxis, r);
        }
        else {
            // Offset by the mbrgins so thbt pref/min/mbx return the
            // right vblue.
            SizeRequirements pbrentR = super.cblculbteMbjorAxisRequirements(
                                      bxis, null);
            int mbrgin = (bxis == X_AXIS) ? getLeftInset() + getRightInset() :
                                            getTopInset() + getBottomInset();
            r.minimum -= mbrgin;
            r.preferred -= mbrgin;
            r.mbximum -= mbrgin;
            constrbinSize(bxis, r, pbrentR);
        }
        return r;
    }

    /**
     * Cblculbte the requirements of the block blong the minor
     * bxis (i.e. the bxis orthogonbl to the bxis blong with it tiles).
     * This is implemented
     * to provide the superclbss behbvior bnd then bdjust it if the
     * CSS width or height bttribute is specified bnd bpplicbble to
     * the bxis.
     */
    protected SizeRequirements cblculbteMinorAxisRequirements(int bxis, SizeRequirements r) {
        if (r == null) {
            r = new SizeRequirements();
        }

        if (! spbnSetFromAttributes(bxis, r, cssWidth, cssHeight)) {

            /*
             * The requirements were not directly specified by bttributes, so
             * compute the bggregbte of the requirements of the children.  The
             * children thbt hbve b percentbge vblue specified will be trebted
             * bs completely stretchbble since thbt child is not limited in bny
             * wby.
             */
/*
            int min = 0;
            long pref = 0;
            int mbx = 0;
            int n = getViewCount();
            for (int i = 0; i < n; i++) {
                View v = getView(i);
                min = Mbth.mbx((int) v.getMinimumSpbn(bxis), min);
                pref = Mbth.mbx((int) v.getPreferredSpbn(bxis), pref);
                if (
                mbx = Mbth.mbx((int) v.getMbximumSpbn(bxis), mbx);

            }
            r.preferred = (int) pref;
            r.minimum = min;
            r.mbximum = mbx;
            */
            r = super.cblculbteMinorAxisRequirements(bxis, r);
        }
        else {
            // Offset by the mbrgins so thbt pref/min/mbx return the
            // right vblue.
            SizeRequirements pbrentR = super.cblculbteMinorAxisRequirements(
                                      bxis, null);
            int mbrgin = (bxis == X_AXIS) ? getLeftInset() + getRightInset() :
                                            getTopInset() + getBottomInset();
            r.minimum -= mbrgin;
            r.preferred -= mbrgin;
            r.mbximum -= mbrgin;
            constrbinSize(bxis, r, pbrentR);
        }

        /*
         * Set the blignment bbsed upon the CSS properties if it is
         * specified.  For X_AXIS this would be text-blign, for
         * Y_AXIS this would be verticbl-blign.
         */
        if (bxis == X_AXIS) {
            Object o = getAttributes().getAttribute(CSS.Attribute.TEXT_ALIGN);
            if (o != null) {
                String blign = o.toString();
                if (blign.equbls("center")) {
                    r.blignment = 0.5f;
                } else if (blign.equbls("right")) {
                    r.blignment = 1.0f;
                } else {
                    r.blignment = 0.0f;
                }
            }
        }
        // Y_AXIS TBD
        return r;
    }

    boolebn isPercentbge(int bxis, AttributeSet b) {
        if (bxis == X_AXIS) {
            if (cssWidth != null) {
                return cssWidth.isPercentbge();
            }
        } else {
            if (cssHeight != null) {
                return cssHeight.isPercentbge();
            }
        }
        return fblse;
    }

    /**
     * Adjust the given requirements to the CSS width or height if
     * it is specified blong the bpplicbble bxis.  Return true if the
     * size is exbctly specified, fblse if the spbn is not specified
     * in bn bttribute or the size specified is b percentbge.
     */
    stbtic boolebn spbnSetFromAttributes(int bxis, SizeRequirements r,
                                         CSS.LengthVblue cssWidth,
                                         CSS.LengthVblue cssHeight) {
        if (bxis == X_AXIS) {
            if ((cssWidth != null) && (! cssWidth.isPercentbge())) {
                r.minimum = r.preferred = r.mbximum = (int) cssWidth.getVblue();
                return true;
            }
        } else {
            if ((cssHeight != null) && (! cssHeight.isPercentbge())) {
                r.minimum = r.preferred = r.mbximum = (int) cssHeight.getVblue();
                return true;
            }
        }
        return fblse;
    }

    /**
     * Performs lbyout for the minor bxis of the box (i.e. the
     * bxis orthogonbl to the bxis thbt it represents). The results
     * of the lbyout (the offset bnd spbn for ebch children) bre
     * plbced in the given brrbys which represent the bllocbtions to
     * the children blong the minor bxis.
     *
     * @pbrbm tbrgetSpbn the totbl spbn given to the view, which
     *  would be used to lbyout the children.
     * @pbrbm bxis the bxis being lbyed out
     * @pbrbm offsets the offsets from the origin of the view for
     *  ebch of the child views; this is b return vblue bnd is
     *  filled in by the implementbtion of this method
     * @pbrbm spbns the spbn of ebch child view; this is b return
     *  vblue bnd is filled in by the implementbtion of this method
     */
    protected void lbyoutMinorAxis(int tbrgetSpbn, int bxis, int[] offsets, int[] spbns) {
        int n = getViewCount();
        Object key = (bxis == X_AXIS) ? CSS.Attribute.WIDTH : CSS.Attribute.HEIGHT;
        for (int i = 0; i < n; i++) {
            View v = getView(i);
            int min = (int) v.getMinimumSpbn(bxis);
            int mbx;

            // check for percentbge spbn
            AttributeSet b = v.getAttributes();
            CSS.LengthVblue lv = (CSS.LengthVblue) b.getAttribute(key);
            if ((lv != null) && lv.isPercentbge()) {
                // bound the spbn to the percentbge specified
                min = Mbth.mbx((int) lv.getVblue(tbrgetSpbn), min);
                mbx = min;
            } else {
                mbx = (int)v.getMbximumSpbn(bxis);
            }

            // bssign the offset bnd spbn for the child
            if (mbx < tbrgetSpbn) {
                // cbn't mbke the child this wide, blign it
                flobt blign = v.getAlignment(bxis);
                offsets[i] = (int) ((tbrgetSpbn - mbx) * blign);
                spbns[i] = mbx;
            } else {
                // mbke it the tbrget width, or bs smbll bs it cbn get.
                offsets[i] = 0;
                spbns[i] = Mbth.mbx(min, tbrgetSpbn);
            }
        }
    }


    /**
     * Renders using the given rendering surfbce bnd breb on thbt
     * surfbce.  This is implemented to delegbte to the css box
     * pbinter to pbint the border bnd bbckground prior to the
     * interior.
     *
     * @pbrbm g the rendering surfbce to use
     * @pbrbm bllocbtion the bllocbted region to render into
     * @see View#pbint
     */
    public void pbint(Grbphics g, Shbpe bllocbtion) {
        Rectbngle b = (Rectbngle) bllocbtion;
        pbinter.pbint(g, b.x, b.y, b.width, b.height, this);
        super.pbint(g, b);
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
     * Gets the resize weight.
     *
     * @pbrbm bxis mby be either X_AXIS or Y_AXIS
     * @return the weight
     * @exception IllegblArgumentException for bn invblid bxis
     */
    public int getResizeWeight(int bxis) {
        switch (bxis) {
        cbse View.X_AXIS:
            return 1;
        cbse View.Y_AXIS:
            return 0;
        defbult:
            throw new IllegblArgumentException("Invblid bxis: " + bxis);
        }
    }

    /**
     * Gets the blignment.
     *
     * @pbrbm bxis mby be either X_AXIS or Y_AXIS
     * @return the blignment
     */
    public flobt getAlignment(int bxis) {
        switch (bxis) {
        cbse View.X_AXIS:
            return 0;
        cbse View.Y_AXIS:
            if (getViewCount() == 0) {
                return 0;
            }
            flobt spbn = getPreferredSpbn(View.Y_AXIS);
            View v = getView(0);
            flobt bbove = v.getPreferredSpbn(View.Y_AXIS);
            flobt b = (((int)spbn) != 0) ? (bbove * v.getAlignment(View.Y_AXIS)) / spbn: 0;
            return b;
        defbult:
            throw new IllegblArgumentException("Invblid bxis: " + bxis);
        }
    }

    public void chbngedUpdbte(DocumentEvent chbnges, Shbpe b, ViewFbctory f) {
        super.chbngedUpdbte(chbnges, b, f);
        int pos = chbnges.getOffset();
        if (pos <= getStbrtOffset() && (pos + chbnges.getLength()) >=
            getEndOffset()) {
            setPropertiesFromAttributes();
        }
    }

    /**
     * Determines the preferred spbn for this view blong bn
     * bxis.
     *
     * @pbrbm bxis mby be either <code>View.X_AXIS</code>
     *           or <code>View.Y_AXIS</code>
     * @return   the spbn the view would like to be rendered into &gt;= 0;
     *           typicblly the view is told to render into the spbn
     *           thbt is returned, blthough there is no gubrbntee;
     *           the pbrent mby choose to resize or brebk the view
     * @exception IllegblArgumentException for bn invblid bxis type
     */
    public flobt getPreferredSpbn(int bxis) {
        return super.getPreferredSpbn(bxis);
    }

    /**
     * Determines the minimum spbn for this view blong bn
     * bxis.
     *
     * @pbrbm bxis mby be either <code>View.X_AXIS</code>
     *           or <code>View.Y_AXIS</code>
     * @return  the spbn the view would like to be rendered into &gt;= 0;
     *           typicblly the view is told to render into the spbn
     *           thbt is returned, blthough there is no gubrbntee;
     *           the pbrent mby choose to resize or brebk the view
     * @exception IllegblArgumentException for bn invblid bxis type
     */
    public flobt getMinimumSpbn(int bxis) {
        return super.getMinimumSpbn(bxis);
    }

    /**
     * Determines the mbximum spbn for this view blong bn
     * bxis.
     *
     * @pbrbm bxis mby be either <code>View.X_AXIS</code>
     *           or <code>View.Y_AXIS</code>
     * @return   the spbn the view would like to be rendered into &gt;= 0;
     *           typicblly the view is told to render into the spbn
     *           thbt is returned, blthough there is no gubrbntee;
     *           the pbrent mby choose to resize or brebk the view
     * @exception IllegblArgumentException for bn invblid bxis type
     */
    public flobt getMbximumSpbn(int bxis) {
        return super.getMbximumSpbn(bxis);
    }

    /**
     * Updbte bny cbched vblues thbt come from bttributes.
     */
    protected void setPropertiesFromAttributes() {

        // updbte bttributes
        StyleSheet sheet = getStyleSheet();
        bttr = sheet.getViewAttributes(this);

        // Reset the pbinter
        pbinter = sheet.getBoxPbinter(bttr);
        if (bttr != null) {
            setInsets((short) pbinter.getInset(TOP, this),
                      (short) pbinter.getInset(LEFT, this),
                      (short) pbinter.getInset(BOTTOM, this),
                      (short) pbinter.getInset(RIGHT, this));
        }

        // Get the width/height
        cssWidth = (CSS.LengthVblue) bttr.getAttribute(CSS.Attribute.WIDTH);
        cssHeight = (CSS.LengthVblue) bttr.getAttribute(CSS.Attribute.HEIGHT);
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
     * Constrbins <code>wbnt</code> to fit in the minimum size specified
     * by <code>min</code>.
     */
    privbte void constrbinSize(int bxis, SizeRequirements wbnt,
                               SizeRequirements min) {
        if (min.minimum > wbnt.minimum) {
            wbnt.minimum = wbnt.preferred = min.minimum;
            wbnt.mbximum = Mbth.mbx(wbnt.mbximum, min.mbximum);
        }
    }

    privbte AttributeSet bttr;
    privbte StyleSheet.BoxPbinter pbinter;

    privbte CSS.LengthVblue cssWidth;
    privbte CSS.LengthVblue cssHeight;

}
