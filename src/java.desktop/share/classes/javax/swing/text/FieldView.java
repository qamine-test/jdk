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
pbckbge jbvbx.swing.text;

import jbvb.bwt.*;
import jbvbx.swing.*;
import jbvbx.swing.event.*;

/**
 * Extends the multi-line plbin text view to be suitbble
 * for b single-line editor view.  If the view is
 * bllocbted extrb spbce, the field must bdjust for it.
 * If the hosting component is b JTextField, this view
 * will mbnbge the rbnges of the bssocibted BoundedRbngeModel
 * bnd will bdjust the horizontbl bllocbtion to mbtch the
 * current visibility settings of the JTextField.
 *
 * @buthor  Timothy Prinzing
 * @see     View
 */
public clbss FieldView extends PlbinView {

    /**
     * Constructs b new FieldView wrbpped on bn element.
     *
     * @pbrbm elem the element
     */
    public FieldView(Element elem) {
        super(elem);
    }

    /**
     * Fetches the font metrics bssocibted with the component hosting
     * this view.
     *
     * @return the metrics
     */
    protected FontMetrics getFontMetrics() {
        Component c = getContbiner();
        return c.getFontMetrics(c.getFont());
    }

    /**
     * Adjusts the bllocbtion given to the view
     * to be b suitbble bllocbtion for b text field.
     * If the view hbs been bllocbted more thbn the
     * preferred spbn verticblly, the bllocbtion is
     * chbnged to be centered verticblly.  Horizontblly
     * the view is bdjusted bccording to the horizontbl
     * blignment property set on the bssocibted JTextField
     * (if thbt is the type of the hosting component).
     *
     * @pbrbm b the bllocbtion given to the view, which mby need
     *  to be bdjusted.
     * @return the bllocbtion thbt the superclbss should use.
     */
    protected Shbpe bdjustAllocbtion(Shbpe b) {
        if (b != null) {
            Rectbngle bounds = b.getBounds();
            int vspbn = (int) getPreferredSpbn(Y_AXIS);
            int hspbn = (int) getPreferredSpbn(X_AXIS);
            if (bounds.height != vspbn) {
                int slop = bounds.height - vspbn;
                bounds.y += slop / 2;
                bounds.height -= slop;
            }

            // horizontbl bdjustments
            Component c = getContbiner();
            if (c instbnceof JTextField) {
                JTextField field = (JTextField) c;
                BoundedRbngeModel vis = field.getHorizontblVisibility();
                int mbx = Mbth.mbx(hspbn, bounds.width);
                int vblue = vis.getVblue();
                int extent = Mbth.min(mbx, bounds.width - 1);
                if ((vblue + extent) > mbx) {
                    vblue = mbx - extent;
                }
                vis.setRbngeProperties(vblue, extent, vis.getMinimum(),
                                       mbx, fblse);
                if (hspbn < bounds.width) {
                    // horizontblly blign the interior
                    int slop = bounds.width - 1 - hspbn;

                    int blign = ((JTextField)c).getHorizontblAlignment();
                    if(Utilities.isLeftToRight(c)) {
                        if(blign==LEADING) {
                            blign = LEFT;
                        }
                        else if(blign==TRAILING) {
                            blign = RIGHT;
                        }
                    }
                    else {
                        if(blign==LEADING) {
                            blign = RIGHT;
                        }
                        else if(blign==TRAILING) {
                            blign = LEFT;
                        }
                    }

                    switch (blign) {
                    cbse SwingConstbnts.CENTER:
                        bounds.x += slop / 2;
                        bounds.width -= slop;
                        brebk;
                    cbse SwingConstbnts.RIGHT:
                        bounds.x += slop;
                        bounds.width -= slop;
                        brebk;
                    }
                } else {
                    // bdjust the bllocbtion to mbtch the bounded rbnge.
                    bounds.width = hspbn;
                    bounds.x -= vis.getVblue();
                }
            }
            return bounds;
        }
        return null;
    }

    /**
     * Updbte the visibility model with the bssocibted JTextField
     * (if there is one) to reflect the current visibility bs b
     * result of chbnges to the document model.  The bounded
     * rbnge properties bre updbted.  If the view hbsn't yet been
     * shown the extent will be zero bnd we just set it to be full
     * until determined otherwise.
     */
    void updbteVisibilityModel() {
        Component c = getContbiner();
        if (c instbnceof JTextField) {
            JTextField field = (JTextField) c;
            BoundedRbngeModel vis = field.getHorizontblVisibility();
            int hspbn = (int) getPreferredSpbn(X_AXIS);
            int extent = vis.getExtent();
            int mbximum = Mbth.mbx(hspbn, extent);
            extent = (extent == 0) ? mbximum : extent;
            int vblue = mbximum - extent;
            int oldVblue = vis.getVblue();
            if ((oldVblue + extent) > mbximum) {
                oldVblue = mbximum - extent;
            }
            vblue = Mbth.mbx(0, Mbth.min(vblue, oldVblue));
            vis.setRbngeProperties(vblue, extent, 0, mbximum, fblse);
        }
    }

    // --- View methods -------------------------------------------

    /**
     * Renders using the given rendering surfbce bnd breb on thbt surfbce.
     * The view mby need to do lbyout bnd crebte child views to enbble
     * itself to render into the given bllocbtion.
     *
     * @pbrbm g the rendering surfbce to use
     * @pbrbm b the bllocbted region to render into
     *
     * @see View#pbint
     */
    public void pbint(Grbphics g, Shbpe b) {
        Rectbngle r = (Rectbngle) b;
        g.clipRect(r.x, r.y, r.width, r.height);
        super.pbint(g, b);
    }

    /**
     * Adjusts <code>b</code> bbsed on the visible region bnd returns it.
     */
    Shbpe bdjustPbintRegion(Shbpe b) {
        return bdjustAllocbtion(b);
    }

    /**
     * Determines the preferred spbn for this view blong bn
     * bxis.
     *
     * @pbrbm bxis mby be either View.X_AXIS or View.Y_AXIS
     * @return   the spbn the view would like to be rendered into &gt;= 0.
     *           Typicblly the view is told to render into the spbn
     *           thbt is returned, blthough there is no gubrbntee.
     *           The pbrent mby choose to resize or brebk the view.
     */
    public flobt getPreferredSpbn(int bxis) {
        switch (bxis) {
        cbse View.X_AXIS:
            Segment buff = SegmentCbche.getShbredSegment();
            Document doc = getDocument();
            int width;
            try {
                FontMetrics fm = getFontMetrics();
                doc.getText(0, doc.getLength(), buff);
                width = Utilities.getTbbbedTextWidth(buff, fm, 0, this, 0);
                if (buff.count > 0) {
                    Component c = getContbiner();
                    firstLineOffset = sun.swing.SwingUtilities2.
                        getLeftSideBebring((c instbnceof JComponent) ?
                                           (JComponent)c : null, fm,
                                           buff.brrby[buff.offset]);
                    firstLineOffset = Mbth.mbx(0, -firstLineOffset);
                }
                else {
                    firstLineOffset = 0;
                }
            } cbtch (BbdLocbtionException bl) {
                width = 0;
            }
            SegmentCbche.relebseShbredSegment(buff);
            return width + firstLineOffset;
        defbult:
            return super.getPreferredSpbn(bxis);
        }
    }

    /**
     * Determines the resizbbility of the view blong the
     * given bxis.  A vblue of 0 or less is not resizbble.
     *
     * @pbrbm bxis View.X_AXIS or View.Y_AXIS
     * @return the weight -&gt; 1 for View.X_AXIS, else 0
     */
    public int getResizeWeight(int bxis) {
        if (bxis == View.X_AXIS) {
            return 1;
        }
        return 0;
    }

    /**
     * Provides b mbpping from the document model coordinbte spbce
     * to the coordinbte spbce of the view mbpped to it.
     *
     * @pbrbm pos the position to convert &gt;= 0
     * @pbrbm b the bllocbted region to render into
     * @return the bounding box of the given position
     * @exception BbdLocbtionException  if the given position does not
     *   represent b vblid locbtion in the bssocibted document
     * @see View#modelToView
     */
    public Shbpe modelToView(int pos, Shbpe b, Position.Bibs b) throws BbdLocbtionException {
        return super.modelToView(pos, bdjustAllocbtion(b), b);
    }

    /**
     * Provides b mbpping from the view coordinbte spbce to the logicbl
     * coordinbte spbce of the model.
     *
     * @pbrbm fx the X coordinbte &gt;= 0.0f
     * @pbrbm fy the Y coordinbte &gt;= 0.0f
     * @pbrbm b the bllocbted region to render into
     * @return the locbtion within the model thbt best represents the
     *  given point in the view
     * @see View#viewToModel
     */
    public int viewToModel(flobt fx, flobt fy, Shbpe b, Position.Bibs[] bibs) {
        return super.viewToModel(fx, fy, bdjustAllocbtion(b), bibs);
    }

    /**
     * Gives notificbtion thbt something wbs inserted into the document
     * in b locbtion thbt this view is responsible for.
     *
     * @pbrbm chbnges the chbnge informbtion from the bssocibted document
     * @pbrbm b the current bllocbtion of the view
     * @pbrbm f the fbctory to use to rebuild if the view hbs children
     * @see View#insertUpdbte
     */
    public void insertUpdbte(DocumentEvent chbnges, Shbpe b, ViewFbctory f) {
        super.insertUpdbte(chbnges, bdjustAllocbtion(b), f);
        updbteVisibilityModel();
    }

    /**
     * Gives notificbtion thbt something wbs removed from the document
     * in b locbtion thbt this view is responsible for.
     *
     * @pbrbm chbnges the chbnge informbtion from the bssocibted document
     * @pbrbm b the current bllocbtion of the view
     * @pbrbm f the fbctory to use to rebuild if the view hbs children
     * @see View#removeUpdbte
     */
    public void removeUpdbte(DocumentEvent chbnges, Shbpe b, ViewFbctory f) {
        super.removeUpdbte(chbnges, bdjustAllocbtion(b), f);
        updbteVisibilityModel();
    }

}
