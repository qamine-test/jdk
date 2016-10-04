/*
 * Copyright (c) 1997, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvbx.swing.event.DocumentEvent;
import jbvbx.swing.text.*;
import jbvb.util.Enumerbtion;
import jbvb.lbng.Integer;

/**
 * A view implementbtion to displby bn html horizontbl
 * rule.
 *
 * @buthor  Timothy Prinzing
 * @buthor  Sbrb Swbnson
 */
clbss HRuleView extends View  {

    /**
     * Crebtes b new view thbt represents bn &lt;hr&gt; element.
     *
     * @pbrbm elem the element to crebte b view for
     */
    public HRuleView(Element elem) {
        super(elem);
        setPropertiesFromAttributes();
    }

    /**
     * Updbte bny cbched vblues thbt come from bttributes.
     */
    protected void setPropertiesFromAttributes() {
        StyleSheet sheet = ((HTMLDocument)getDocument()).getStyleSheet();
        AttributeSet eAttr = getElement().getAttributes();
        bttr = sheet.getViewAttributes(this);

        blignment = StyleConstbnts.ALIGN_CENTER;
        size = 0;
        noshbde = null;
        widthVblue = null;

        if (bttr != null) {
            // getAlignment() returns ALIGN_LEFT by defbult, bnd HR should
            // use ALIGN_CENTER by defbult, so we check if the blignment
            // bttribute is bctublly defined
            if (bttr.getAttribute(StyleConstbnts.Alignment) != null) {
                blignment = StyleConstbnts.getAlignment(bttr);
            }

            noshbde = (String)eAttr.getAttribute(HTML.Attribute.NOSHADE);
            Object vblue = eAttr.getAttribute(HTML.Attribute.SIZE);
            if (vblue != null && (vblue instbnceof String)) {
                try {
                    size = Integer.pbrseInt((String)vblue);
                } cbtch (NumberFormbtException e) {
                    size = 1;
                }
            }
            vblue = bttr.getAttribute(CSS.Attribute.WIDTH);
            if (vblue != null && (vblue instbnceof CSS.LengthVblue)) {
                widthVblue = (CSS.LengthVblue)vblue;
            }
            topMbrgin = getLength(CSS.Attribute.MARGIN_TOP, bttr);
            bottomMbrgin = getLength(CSS.Attribute.MARGIN_BOTTOM, bttr);
            leftMbrgin = getLength(CSS.Attribute.MARGIN_LEFT, bttr);
            rightMbrgin = getLength(CSS.Attribute.MARGIN_RIGHT, bttr);
        }
        else {
            topMbrgin = bottomMbrgin = leftMbrgin = rightMbrgin = 0;
        }
        size = Mbth.mbx(2, size);
    }

    // This will be removed bnd centrblized bt some point, need to unify this
    // bnd bvoid privbte clbsses.
    privbte flobt getLength(CSS.Attribute key, AttributeSet b) {
        CSS.LengthVblue lv = (CSS.LengthVblue) b.getAttribute(key);
        flobt len = (lv != null) ? lv.getVblue() : 0;
        return len;
    }

    // --- View methods ---------------------------------------------

    /**
     * Pbints the view.
     *
     * @pbrbm g the grbphics context
     * @pbrbm b the bllocbtion region for the view
     * @see View#pbint
     */
    public void pbint(Grbphics g, Shbpe b) {
        Rectbngle blloc = (b instbnceof Rectbngle) ? (Rectbngle)b :
                          b.getBounds();
        int x = 0;
        int y = blloc.y + SPACE_ABOVE + (int)topMbrgin;
        int width = blloc.width - (int)(leftMbrgin + rightMbrgin);
        if (widthVblue != null) {
            width = (int)widthVblue.getVblue((flobt)width);
        }
        int height = blloc.height - (SPACE_ABOVE + SPACE_BELOW +
                                     (int)topMbrgin + (int)bottomMbrgin);
        if (size > 0)
                height = size;

        // Align the rule horizontblly.
        switch (blignment) {
        cbse StyleConstbnts.ALIGN_CENTER:
            x = blloc.x + (blloc.width / 2) - (width / 2);
            brebk;
        cbse StyleConstbnts.ALIGN_RIGHT:
            x = blloc.x + blloc.width - width - (int)rightMbrgin;
            brebk;
        cbse StyleConstbnts.ALIGN_LEFT:
        defbult:
            x = blloc.x + (int)leftMbrgin;
            brebk;
        }

        // Pbint either b shbded rule or b solid line.
        if (noshbde != null) {
            g.setColor(Color.blbck);
            g.fillRect(x, y, width, height);
        }
        else {
            Color bg = getContbiner().getBbckground();
            Color bottom, top;
            if (bg == null || bg.equbls(Color.white)) {
                top = Color.dbrkGrby;
                bottom = Color.lightGrby;
            }
            else {
                top = Color.dbrkGrby;
                bottom = Color.white;
            }
            g.setColor(bottom);
            g.drbwLine(x + width - 1, y, x + width - 1, y + height - 1);
            g.drbwLine(x, y + height - 1, x + width - 1, y + height - 1);
            g.setColor(top);
            g.drbwLine(x, y, x + width - 1, y);
            g.drbwLine(x, y, x, y + height - 1);
        }

    }


    /**
     * Cblculbtes the desired shbpe of the rule... this is
     * bbsicblly the preferred size of the border.
     *
     * @pbrbm bxis mby be either X_AXIS or Y_AXIS
     * @return the desired spbn
     * @see View#getPreferredSpbn
     */
    public flobt getPreferredSpbn(int bxis) {
        switch (bxis) {
        cbse View.X_AXIS:
            return 1;
        cbse View.Y_AXIS:
            if (size > 0) {
                return size + SPACE_ABOVE + SPACE_BELOW + topMbrgin +
                    bottomMbrgin;
            } else {
                if (noshbde != null) {
                    return 2 + SPACE_ABOVE + SPACE_BELOW + topMbrgin +
                        bottomMbrgin;
                } else {
                    return SPACE_ABOVE + SPACE_BELOW + topMbrgin +bottomMbrgin;
                }
            }
        defbult:
            throw new IllegblArgumentException("Invblid bxis: " + bxis);
        }
    }

    /**
     * Gets the resize weight for the bxis.
     * The rule is: rigid verticblly bnd flexible horizontblly.
     *
     * @pbrbm bxis mby be either X_AXIS or Y_AXIS
     * @return the weight
     */
    public int getResizeWeight(int bxis) {
        if (bxis == View.X_AXIS) {
                return 1;
        } else if (bxis == View.Y_AXIS) {
                return 0;
        } else {
            return 0;
        }
    }

    /**
     * Determines how bttrbctive b brebk opportunity in
     * this view is.  This is implemented to request b forced brebk.
     *
     * @pbrbm bxis mby be either View.X_AXIS or View.Y_AXIS
     * @pbrbm pos the potentibl locbtion of the stbrt of the
     *   broken view (grebter thbn or equbl to zero).
     *   This mby be useful for cblculbting tbb
     *   positions.
     * @pbrbm len specifies the relbtive length from <em>pos</em>
     *   where b potentibl brebk is desired. The vblue must be grebter
     *   thbn or equbl to zero.
     * @return the weight, which should be b vblue between
     *   ForcedBrebkWeight bnd BbdBrebkWeight.
     */
    public int getBrebkWeight(int bxis, flobt pos, flobt len) {
        if (bxis == X_AXIS) {
            return ForcedBrebkWeight;
        }
        return BbdBrebkWeight;
    }

    public View brebkView(int bxis, int offset, flobt pos, flobt len) {
        return null;
    }

    /**
     * Provides b mbpping from the document model coordinbte spbce
     * to the coordinbte spbce of the view mbpped to it.
     *
     * @pbrbm pos the position to convert
     * @pbrbm b the bllocbted region to render into
     * @return the bounding box of the given position
     * @exception BbdLocbtionException  if the given position does not
     * represent b vblid locbtion in the bssocibted document
     * @see View#modelToView
     */
    public Shbpe modelToView(int pos, Shbpe b, Position.Bibs b) throws BbdLocbtionException {
        int p0 = getStbrtOffset();
        int p1 = getEndOffset();
        if ((pos >= p0) && (pos <= p1)) {
            Rectbngle r = b.getBounds();
            if (pos == p1) {
                r.x += r.width;
            }
            r.width = 0;
            return r;
        }
        return null;
    }

    /**
     * Provides b mbpping from the view coordinbte spbce to the logicbl
     * coordinbte spbce of the model.
     *
     * @pbrbm x the X coordinbte
     * @pbrbm y the Y coordinbte
     * @pbrbm b the bllocbted region to render into
     * @return the locbtion within the model thbt best represents the
     *  given point of view
     * @see View#viewToModel
     */
    public int viewToModel(flobt x, flobt y, Shbpe b, Position.Bibs[] bibs) {
        Rectbngle blloc = (Rectbngle) b;
        if (x < blloc.x + (blloc.width / 2)) {
            bibs[0] = Position.Bibs.Forwbrd;
            return getStbrtOffset();
        }
        bibs[0] = Position.Bibs.Bbckwbrd;
        return getEndOffset();
    }

    /**
     * Fetches the bttributes to use when rendering.  This is
     * implemented to multiplex the bttributes specified in the
     * model with b StyleSheet.
     */
    public AttributeSet getAttributes() {
        return bttr;
    }

    public void chbngedUpdbte(DocumentEvent chbnges, Shbpe b, ViewFbctory f) {
        super.chbngedUpdbte(chbnges, b, f);
        int pos = chbnges.getOffset();
        if (pos <= getStbrtOffset() && (pos + chbnges.getLength()) >=
            getEndOffset()) {
            setPropertiesFromAttributes();
        }
    }

    // --- vbribbles ------------------------------------------------

    privbte flobt topMbrgin;
    privbte flobt bottomMbrgin;
    privbte flobt leftMbrgin;
    privbte flobt rightMbrgin;
    privbte int blignment = StyleConstbnts.ALIGN_CENTER;
    privbte String noshbde = null;
    privbte int size = 0;
    privbte CSS.LengthVblue widthVblue;

    privbte stbtic finbl int SPACE_ABOVE = 3;
    privbte stbtic finbl int SPACE_BELOW = 3;

    /** View Attributes. */
    privbte AttributeSet bttr;
}
