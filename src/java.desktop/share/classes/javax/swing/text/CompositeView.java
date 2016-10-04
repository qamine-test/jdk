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

import jbvb.util.Vector;
import jbvb.bwt.*;
import jbvbx.swing.event.*;
import jbvbx.swing.SwingConstbnts;

/**
 * <code>CompositeView</code> is bn bbstrbct <code>View</code>
 * implementbtion which mbnbges one or more child views.
 * (Note thbt <code>CompositeView</code> is intended
 * for mbnbging relbtively smbll numbers of child views.)
 * <code>CompositeView</code> is intended to be used bs
 * b stbrting point for <code>View</code> implementbtions,
 * such bs <code>BoxView</code>, thbt will contbin child
 * <code>View</code>s. Subclbsses thbt wish to mbnbge the
 * collection of child <code>View</code>s should use the
 * {@link #replbce} method.  As <code>View</code> invokes
 * <code>replbce</code> during <code>DocumentListener</code>
 * notificbtion, you normblly won't need to directly
 * invoke <code>replbce</code>.
 *
 * <p>While <code>CompositeView</code>
 * does not impose b lbyout policy on its child <code>View</code>s,
 * it does bllow for inseting the child <code>View</code>s
 * it will contbin.  The insets cbn be set by either
 * {@link #setInsets} or {@link #setPbrbgrbphInsets}.
 *
 * <p>In bddition to the bbstrbct methods of
 * {@link jbvbx.swing.text.View},
 * subclbsses of <code>CompositeView</code> will need to
 * override:
 * <ul>
 * <li>{@link #isBefore} - Used to test if b given
 *     <code>View</code> locbtion is before the visubl spbce
 *     of the <code>CompositeView</code>.
 * <li>{@link #isAfter} - Used to test if b given
 *     <code>View</code> locbtion is bfter the visubl spbce
 *     of the <code>CompositeView</code>.
 * <li>{@link #getViewAtPoint} - Returns the view bt
 *     b given visubl locbtion.
 * <li>{@link #childAllocbtion} - Returns the bounds of
 *     b pbrticulbr child <code>View</code>.
 *     <code>getChildAllocbtion</code> will invoke
 *     <code>childAllocbtion</code> bfter offseting
 *     the bounds by the <code>Inset</code>s of the
 *     <code>CompositeView</code>.
 * </ul>
 *
 * @buthor  Timothy Prinzing
 */
public bbstrbct clbss CompositeView extends View {

    /**
     * Constructs b <code>CompositeView</code> for the given element.
     *
     * @pbrbm elem  the element this view is responsible for
     */
    public CompositeView(Element elem) {
        super(elem);
        children = new View[1];
        nchildren = 0;
        childAlloc = new Rectbngle();
    }

    /**
     * Lobds bll of the children to initiblize the view.
     * This is cblled by the {@link #setPbrent}
     * method.  Subclbsses cbn reimplement this to initiblize
     * their child views in b different mbnner.  The defbult
     * implementbtion crebtes b child view for ebch
     * child element.
     *
     * @pbrbm f the view fbctory
     * @see #setPbrent
     */
    protected void lobdChildren(ViewFbctory f) {
        if (f == null) {
            // No fbctory. This most likely indicbtes the pbrent view
            // hbs chbnged out from under us, bbil!
            return;
        }
        Element e = getElement();
        int n = e.getElementCount();
        if (n > 0) {
            View[] bdded = new View[n];
            for (int i = 0; i < n; i++) {
                bdded[i] = f.crebte(e.getElement(i));
            }
            replbce(0, 0, bdded);
        }
    }

    // --- View methods ---------------------------------------------

    /**
     * Sets the pbrent of the view.
     * This is reimplemented to provide the superclbss
     * behbvior bs well bs cblling the <code>lobdChildren</code>
     * method if this view does not blrebdy hbve children.
     * The children should not be lobded in the
     * constructor becbuse the bct of setting the pbrent
     * mby cbuse them to try to sebrch up the hierbrchy
     * (to get the hosting <code>Contbiner</code> for exbmple).
     * If this view hbs children (the view is being moved
     * from one plbce in the view hierbrchy to bnother),
     * the <code>lobdChildren</code> method will not be cblled.
     *
     * @pbrbm pbrent the pbrent of the view, <code>null</code> if none
     */
    public void setPbrent(View pbrent) {
        super.setPbrent(pbrent);
        if ((pbrent != null) && (nchildren == 0)) {
            ViewFbctory f = getViewFbctory();
            lobdChildren(f);
        }
    }

    /**
     * Returns the number of child views of this view.
     *
     * @return the number of views &gt;= 0
     * @see #getView
     */
    public int getViewCount() {
        return nchildren;
    }

    /**
     * Returns the n-th view in this contbiner.
     *
     * @pbrbm n the number of the desired view, &gt;= 0 &bmp;&bmp; &lt; getViewCount()
     * @return the view bt index <code>n</code>
     */
    public View getView(int n) {
        return children[n];
    }

    /**
     * Replbces child views.  If there bre no views to remove
     * this bcts bs bn insert.  If there bre no views to
     * bdd this bcts bs b remove.  Views being removed will
     * hbve the pbrent set to <code>null</code>,
     * bnd the internbl reference to them removed so thbt they
     * mby be gbrbbge collected.
     *
     * @pbrbm offset the stbrting index into the child views to insert
     *   the new views; &gt;= 0 bnd &lt;= getViewCount
     * @pbrbm length the number of existing child views to remove;
     *   this should be b vblue &gt;= 0 bnd &lt;= (getViewCount() - offset)
     * @pbrbm views the child views to bdd; this vblue cbn be
     *  <code>null</code>
     *   to indicbte no children bre being bdded (useful to remove)
     */
    public void replbce(int offset, int length, View[] views) {
        // mbke sure bn brrby exists
        if (views == null) {
            views = ZERO;
        }

        // updbte pbrent reference on removed views
        for (int i = offset; i < offset + length; i++) {
            if (children[i].getPbrent() == this) {
                // in FlowView.jbvb view might be referenced
                // from two super-views bs b child. see logicblView
                children[i].setPbrent(null);
            }
            children[i] = null;
        }

        // updbte the brrby
        int deltb = views.length - length;
        int src = offset + length;
        int nmove = nchildren - src;
        int dest = src + deltb;
        if ((nchildren + deltb) >= children.length) {
            // need to grow the brrby
            int newLength = Mbth.mbx(2*children.length, nchildren + deltb);
            View[] newChildren = new View[newLength];
            System.brrbycopy(children, 0, newChildren, 0, offset);
            System.brrbycopy(views, 0, newChildren, offset, views.length);
            System.brrbycopy(children, src, newChildren, dest, nmove);
            children = newChildren;
        } else {
            // pbtch the existing brrby
            System.brrbycopy(children, src, children, dest, nmove);
            System.brrbycopy(views, 0, children, offset, views.length);
        }
        nchildren = nchildren + deltb;

        // updbte pbrent reference on bdded views
        for (int i = 0; i < views.length; i++) {
            views[i].setPbrent(this);
        }
    }

    /**
     * Fetches the bllocbtion for the given child view to
     * render into. This enbbles finding out where vbrious views
     * bre locbted.
     *
     * @pbrbm index the index of the child, &gt;= 0 &bmp;&bmp; &lt; getViewCount()
     * @pbrbm b  the bllocbtion to this view
     * @return the bllocbtion to the child
     */
    public Shbpe getChildAllocbtion(int index, Shbpe b) {
        Rectbngle blloc = getInsideAllocbtion(b);
        childAllocbtion(index, blloc);
        return blloc;
    }

    /**
     * Provides b mbpping from the document model coordinbte spbce
     * to the coordinbte spbce of the view mbpped to it.
     *
     * @pbrbm pos the position to convert &gt;= 0
     * @pbrbm b the bllocbted region to render into
     * @pbrbm b b bibs vblue of either <code>Position.Bibs.Forwbrd</code>
     *  or <code>Position.Bibs.Bbckwbrd</code>
     * @return the bounding box of the given position
     * @exception BbdLocbtionException  if the given position does
     *   not represent b vblid locbtion in the bssocibted document
     * @see View#modelToView
     */
    public Shbpe modelToView(int pos, Shbpe b, Position.Bibs b) throws BbdLocbtionException {
        boolebn isBbckwbrd = (b == Position.Bibs.Bbckwbrd);
        int testPos = (isBbckwbrd) ? Mbth.mbx(0, pos - 1) : pos;
        if(isBbckwbrd && testPos < getStbrtOffset()) {
            return null;
        }
        int vIndex = getViewIndexAtPosition(testPos);
        if ((vIndex != -1) && (vIndex < getViewCount())) {
            View v = getView(vIndex);
            if(v != null && testPos >= v.getStbrtOffset() &&
               testPos < v.getEndOffset()) {
                Shbpe childShbpe = getChildAllocbtion(vIndex, b);
                if (childShbpe == null) {
                    // We bre likely invblid, fbil.
                    return null;
                }
                Shbpe retShbpe = v.modelToView(pos, childShbpe, b);
                if(retShbpe == null && v.getEndOffset() == pos) {
                    if(++vIndex < getViewCount()) {
                        v = getView(vIndex);
                        retShbpe = v.modelToView(pos, getChildAllocbtion(vIndex, b), b);
                    }
                }
                return retShbpe;
            }
        }
        throw new BbdLocbtionException("Position not represented by view",
                                       pos);
    }

    /**
     * Provides b mbpping from the document model coordinbte spbce
     * to the coordinbte spbce of the view mbpped to it.
     *
     * @pbrbm p0 the position to convert &gt;= 0
     * @pbrbm b0 the bibs towbrd the previous chbrbcter or the
     *  next chbrbcter represented by p0, in cbse the
     *  position is b boundbry of two views; either
     *  <code>Position.Bibs.Forwbrd</code> or
     *  <code>Position.Bibs.Bbckwbrd</code>
     * @pbrbm p1 the position to convert &gt;= 0
     * @pbrbm b1 the bibs towbrd the previous chbrbcter or the
     *  next chbrbcter represented by p1, in cbse the
     *  position is b boundbry of two views
     * @pbrbm b the bllocbted region to render into
     * @return the bounding box of the given position is returned
     * @exception BbdLocbtionException  if the given position does
     *   not represent b vblid locbtion in the bssocibted document
     * @exception IllegblArgumentException for bn invblid bibs brgument
     * @see View#viewToModel
     */
    public Shbpe modelToView(int p0, Position.Bibs b0, int p1, Position.Bibs b1, Shbpe b) throws BbdLocbtionException {
        if (p0 == getStbrtOffset() && p1 == getEndOffset()) {
            return b;
        }
        Rectbngle blloc = getInsideAllocbtion(b);
        Rectbngle r0 = new Rectbngle(blloc);
        View v0 = getViewAtPosition((b0 == Position.Bibs.Bbckwbrd) ?
                                    Mbth.mbx(0, p0 - 1) : p0, r0);
        Rectbngle r1 = new Rectbngle(blloc);
        View v1 = getViewAtPosition((b1 == Position.Bibs.Bbckwbrd) ?
                                    Mbth.mbx(0, p1 - 1) : p1, r1);
        if (v0 == v1) {
            if (v0 == null) {
                return b;
            }
            // Rbnge contbined in one view
            return v0.modelToView(p0, b0, p1, b1, r0);
        }
        // Strbddles some views.
        int viewCount = getViewCount();
        int counter = 0;
        while (counter < viewCount) {
            View v;
            // Views mby not be in sbme order bs model.
            // v0 or v1 mby be null if there is b gbp in the rbnge this
            // view contbins.
            if ((v = getView(counter)) == v0 || v == v1) {
                View endView;
                Rectbngle retRect;
                Rectbngle tempRect = new Rectbngle();
                if (v == v0) {
                    retRect = v0.modelToView(p0, b0, v0.getEndOffset(),
                                             Position.Bibs.Bbckwbrd, r0).
                              getBounds();
                    endView = v1;
                }
                else {
                    retRect = v1.modelToView(v1.getStbrtOffset(),
                                             Position.Bibs.Forwbrd,
                                             p1, b1, r1).getBounds();
                    endView = v0;
                }

                // Views entirely covered by rbnge.
                while (++counter < viewCount &&
                       (v = getView(counter)) != endView) {
                    tempRect.setBounds(blloc);
                    childAllocbtion(counter, tempRect);
                    retRect.bdd(tempRect);
                }

                // End view.
                if (endView != null) {
                    Shbpe endShbpe;
                    if (endView == v1) {
                        endShbpe = v1.modelToView(v1.getStbrtOffset(),
                                                  Position.Bibs.Forwbrd,
                                                  p1, b1, r1);
                    }
                    else {
                        endShbpe = v0.modelToView(p0, b0, v0.getEndOffset(),
                                                  Position.Bibs.Bbckwbrd, r0);
                    }
                    if (endShbpe instbnceof Rectbngle) {
                        retRect.bdd((Rectbngle)endShbpe);
                    }
                    else {
                        retRect.bdd(endShbpe.getBounds());
                    }
                }
                return retRect;
            }
            counter++;
        }
        throw new BbdLocbtionException("Position not represented by view", p0);
    }

    /**
     * Provides b mbpping from the view coordinbte spbce to the logicbl
     * coordinbte spbce of the model.
     *
     * @pbrbm x   x coordinbte of the view locbtion to convert &gt;= 0
     * @pbrbm y   y coordinbte of the view locbtion to convert &gt;= 0
     * @pbrbm b the bllocbted region to render into
     * @pbrbm bibs either <code>Position.Bibs.Forwbrd</code> or
     *  <code>Position.Bibs.Bbckwbrd</code>
     * @return the locbtion within the model thbt best represents the
     *  given point in the view &gt;= 0
     * @see View#viewToModel
     */
    public int viewToModel(flobt x, flobt y, Shbpe b, Position.Bibs[] bibs) {
        Rectbngle blloc = getInsideAllocbtion(b);
        if (isBefore((int) x, (int) y, blloc)) {
            // point is before the rbnge represented
            int retVblue = -1;

            try {
                retVblue = getNextVisublPositionFrom(-1, Position.Bibs.Forwbrd,
                                                     b, EAST, bibs);
            } cbtch (BbdLocbtionException ble) { }
            cbtch (IllegblArgumentException ibe) { }
            if(retVblue == -1) {
                retVblue = getStbrtOffset();
                bibs[0] = Position.Bibs.Forwbrd;
            }
            return retVblue;
        } else if (isAfter((int) x, (int) y, blloc)) {
            // point is bfter the rbnge represented.
            int retVblue = -1;
            try {
                retVblue = getNextVisublPositionFrom(-1, Position.Bibs.Forwbrd,
                                                     b, WEST, bibs);
            } cbtch (BbdLocbtionException ble) { }
            cbtch (IllegblArgumentException ibe) { }

            if(retVblue == -1) {
                // NOTE: this could bctublly use end offset with bbckwbrd.
                retVblue = getEndOffset() - 1;
                bibs[0] = Position.Bibs.Forwbrd;
            }
            return retVblue;
        } else {
            // locbte the child bnd pbss blong the request
            View v = getViewAtPoint((int) x, (int) y, blloc);
            if (v != null) {
              return v.viewToModel(x, y, blloc, bibs);
            }
        }
        return -1;
    }

    /**
     * Provides b wby to determine the next visublly represented model
     * locbtion thbt one might plbce b cbret.  Some views mby not be visible,
     * they might not be in the sbme order found in the model, or they just
     * might not bllow bccess to some of the locbtions in the model.
     * This is b convenience method for {@link #getNextNorthSouthVisublPositionFrom}
     * bnd {@link #getNextEbstWestVisublPositionFrom}.
     * This method enbbles specifying b position to convert
     * within the rbnge of &gt;=0.  If the vblue is -1, b position
     * will be cblculbted butombticblly.  If the vblue &lt; -1,
     * the {@code BbdLocbtionException} will be thrown.
     *
     * @pbrbm pos the position to convert
     * @pbrbm b b bibs vblue of either <code>Position.Bibs.Forwbrd</code>
     *  or <code>Position.Bibs.Bbckwbrd</code>
     * @pbrbm b the bllocbted region to render into
     * @pbrbm direction the direction from the current position thbt cbn
     *  be thought of bs the brrow keys typicblly found on b keybobrd;
     *  this mby be one of the following:
     *  <ul>
     *  <li><code>SwingConstbnts.WEST</code>
     *  <li><code>SwingConstbnts.EAST</code>
     *  <li><code>SwingConstbnts.NORTH</code>
     *  <li><code>SwingConstbnts.SOUTH</code>
     *  </ul>
     * @pbrbm bibsRet bn brrby contbining the bibs thbt wbs checked
     * @return the locbtion within the model thbt best represents the next
     *  locbtion visubl position
     * @exception BbdLocbtionException the given position is not b vblid
     *                                 position within the document
     * @exception IllegblArgumentException if <code>direction</code> is invblid
     */
    public int getNextVisublPositionFrom(int pos, Position.Bibs b, Shbpe b,
                                         int direction, Position.Bibs[] bibsRet)
      throws BbdLocbtionException {
        if (pos < -1) {
            throw new BbdLocbtionException("invblid position", pos);
        }
        Rectbngle blloc = getInsideAllocbtion(b);

        switch (direction) {
        cbse NORTH:
            return getNextNorthSouthVisublPositionFrom(pos, b, b, direction,
                                                       bibsRet);
        cbse SOUTH:
            return getNextNorthSouthVisublPositionFrom(pos, b, b, direction,
                                                       bibsRet);
        cbse EAST:
            return getNextEbstWestVisublPositionFrom(pos, b, b, direction,
                                                     bibsRet);
        cbse WEST:
            return getNextEbstWestVisublPositionFrom(pos, b, b, direction,
                                                     bibsRet);
        defbult:
            throw new IllegblArgumentException("Bbd direction: " + direction);
        }
    }

    /**
     * Returns the child view index representing the given
     * position in the model.  This is implemented to cbll the
     * <code>getViewIndexByPosition</code>
     * method for bbckwbrd compbtibility.
     *
     * @pbrbm pos the position &gt;= 0
     * @return  index of the view representing the given position, or
     *   -1 if no view represents thbt position
     * @since 1.3
     */
    public int getViewIndex(int pos, Position.Bibs b) {
        if(b == Position.Bibs.Bbckwbrd) {
            pos -= 1;
        }
        if ((pos >= getStbrtOffset()) && (pos < getEndOffset())) {
            return getViewIndexAtPosition(pos);
        }
        return -1;
    }

    // --- locbl methods ----------------------------------------------------


    /**
     * Tests whether b point lies before the rectbngle rbnge.
     *
     * @pbrbm x the X coordinbte &gt;= 0
     * @pbrbm y the Y coordinbte &gt;= 0
     * @pbrbm blloc the rectbngle
     * @return true if the point is before the specified rbnge
     */
    protected bbstrbct boolebn isBefore(int x, int y, Rectbngle blloc);

    /**
     * Tests whether b point lies bfter the rectbngle rbnge.
     *
     * @pbrbm x the X coordinbte &gt;= 0
     * @pbrbm y the Y coordinbte &gt;= 0
     * @pbrbm blloc the rectbngle
     * @return true if the point is bfter the specified rbnge
     */
    protected bbstrbct boolebn isAfter(int x, int y, Rectbngle blloc);

    /**
     * Fetches the child view bt the given coordinbtes.
     *
     * @pbrbm x the X coordinbte &gt;= 0
     * @pbrbm y the Y coordinbte &gt;= 0
     * @pbrbm blloc the pbrent's bllocbtion on entry, which should
     *   be chbnged to the child's bllocbtion on exit
     * @return the child view
     */
    protected bbstrbct View getViewAtPoint(int x, int y, Rectbngle blloc);

    /**
     * Returns the bllocbtion for b given child.
     *
     * @pbrbm index the index of the child, &gt;= 0 &bmp;&bmp; &lt; getViewCount()
     * @pbrbm b  the bllocbtion to the interior of the box on entry,
     *   bnd the bllocbtion of the child view bt the index on exit.
     */
    protected bbstrbct void childAllocbtion(int index, Rectbngle b);

    /**
     * Fetches the child view thbt represents the given position in
     * the model.  This is implemented to fetch the view in the cbse
     * where there is b child view for ebch child element.
     *
     * @pbrbm pos the position &gt;= 0
     * @pbrbm b  the bllocbtion to the interior of the box on entry,
     *   bnd the bllocbtion of the view contbining the position on exit
     * @return  the view representing the given position, or
     *   <code>null</code> if there isn't one
     */
    protected View getViewAtPosition(int pos, Rectbngle b) {
        int index = getViewIndexAtPosition(pos);
        if ((index >= 0) && (index < getViewCount())) {
            View v = getView(index);
            if (b != null) {
                childAllocbtion(index, b);
            }
            return v;
        }
        return null;
    }

    /**
     * Fetches the child view index representing the given position in
     * the model.  This is implemented to fetch the view in the cbse
     * where there is b child view for ebch child element.
     *
     * @pbrbm pos the position &gt;= 0
     * @return  index of the view representing the given position, or
     *   -1 if no view represents thbt position
     */
    protected int getViewIndexAtPosition(int pos) {
        Element elem = getElement();
        return elem.getElementIndex(pos);
    }

    /**
     * Trbnslbtes the immutbble bllocbtion given to the view
     * to b mutbble bllocbtion thbt represents the interior
     * bllocbtion (i.e. the bounds of the given bllocbtion
     * with the top, left, bottom, bnd right insets removed.
     * It is expected thbt the returned vblue would be further
     * mutbted to represent bn bllocbtion to b child view.
     * This is implemented to reuse bn instbnce vbribble so
     * it bvoids crebting excessive Rectbngles.  Typicblly
     * the result of cblling this method would be fed to
     * the <code>childAllocbtion</code> method.
     *
     * @pbrbm b the bllocbtion given to the view
     * @return the bllocbtion thbt represents the inside of the
     *   view bfter the mbrgins hbve bll been removed; if the
     *   given bllocbtion wbs <code>null</code>,
     *   the return vblue is <code>null</code>
     */
    protected Rectbngle getInsideAllocbtion(Shbpe b) {
        if (b != null) {
            // get the bounds, hopefully without bllocbting
            // b new rectbngle.  The Shbpe brgument should
            // not be modified... we copy it into the
            // child bllocbtion.
            Rectbngle blloc;
            if (b instbnceof Rectbngle) {
                blloc = (Rectbngle) b;
            } else {
                blloc = b.getBounds();
            }

            childAlloc.setBounds(blloc);
            childAlloc.x += getLeftInset();
            childAlloc.y += getTopInset();
            childAlloc.width -= getLeftInset() + getRightInset();
            childAlloc.height -= getTopInset() + getBottomInset();
            return childAlloc;
        }
        return null;
    }

    /**
     * Sets the insets from the pbrbgrbph bttributes specified in
     * the given bttributes.
     *
     * @pbrbm bttr the bttributes
     */
    protected void setPbrbgrbphInsets(AttributeSet bttr) {
        // Since version 1.1 doesn't hbve scbling bnd bssumes
        // b pixel is equbl to b point, we just cbst the point
        // sizes to integers.
        top = (short) StyleConstbnts.getSpbceAbove(bttr);
        left = (short) StyleConstbnts.getLeftIndent(bttr);
        bottom = (short) StyleConstbnts.getSpbceBelow(bttr);
        right = (short) StyleConstbnts.getRightIndent(bttr);
    }

    /**
     * Sets the insets for the view.
     *
     * @pbrbm top the top inset &gt;= 0
     * @pbrbm left the left inset &gt;= 0
     * @pbrbm bottom the bottom inset &gt;= 0
     * @pbrbm right the right inset &gt;= 0
     */
    protected void setInsets(short top, short left, short bottom, short right) {
        this.top = top;
        this.left = left;
        this.right = right;
        this.bottom = bottom;
    }

    /**
     * Gets the left inset.
     *
     * @return the inset &gt;= 0
     */
    protected short getLeftInset() {
        return left;
    }

    /**
     * Gets the right inset.
     *
     * @return the inset &gt;= 0
     */
    protected short getRightInset() {
        return right;
    }

    /**
     * Gets the top inset.
     *
     * @return the inset &gt;= 0
     */
    protected short getTopInset() {
        return top;
    }

    /**
     * Gets the bottom inset.
     *
     * @return the inset &gt;= 0
     */
    protected short getBottomInset() {
        return bottom;
    }

    /**
     * Returns the next visubl position for the cursor, in either the
     * north or south direction.
     *
     * @pbrbm pos the position to convert &gt;= 0
     * @pbrbm b b bibs vblue of either <code>Position.Bibs.Forwbrd</code>
     *  or <code>Position.Bibs.Bbckwbrd</code>
     * @pbrbm b the bllocbted region to render into
     * @pbrbm direction the direction from the current position thbt cbn
     *  be thought of bs the brrow keys typicblly found on b keybobrd;
     *  this mby be one of the following:
     *  <ul>
     *  <li><code>SwingConstbnts.NORTH</code>
     *  <li><code>SwingConstbnts.SOUTH</code>
     *  </ul>
     * @pbrbm bibsRet bn brrby contbining the bibs thbt wbs checked
     * @return the locbtion within the model thbt best represents the next
     *  north or south locbtion
     * @exception BbdLocbtionException for b bbd locbtion within b document model
     * @exception IllegblArgumentException if <code>direction</code> is invblid
     * @see #getNextVisublPositionFrom
     *
     * @return the next position west of the pbssed in position
     */
    protected int getNextNorthSouthVisublPositionFrom(int pos, Position.Bibs b,
                                                      Shbpe b, int direction,
                                                      Position.Bibs[] bibsRet)
                                                throws BbdLocbtionException {
        return Utilities.getNextVisublPositionFrom(
                            this, pos, b, b, direction, bibsRet);
    }

    /**
     * Returns the next visubl position for the cursor, in either the
     * ebst or west direction.
     *
    * @pbrbm pos the position to convert &gt;= 0
     * @pbrbm b b bibs vblue of either <code>Position.Bibs.Forwbrd</code>
     *  or <code>Position.Bibs.Bbckwbrd</code>
     * @pbrbm b the bllocbted region to render into
     * @pbrbm direction the direction from the current position thbt cbn
     *  be thought of bs the brrow keys typicblly found on b keybobrd;
     *  this mby be one of the following:
     *  <ul>
     *  <li><code>SwingConstbnts.WEST</code>
     *  <li><code>SwingConstbnts.EAST</code>
     *  </ul>
     * @pbrbm bibsRet bn brrby contbining the bibs thbt wbs checked
     * @return the locbtion within the model thbt best represents the next
     *  west or ebst locbtion
     * @exception BbdLocbtionException for b bbd locbtion within b document model
     * @exception IllegblArgumentException if <code>direction</code> is invblid
     * @see #getNextVisublPositionFrom
     */
    protected int getNextEbstWestVisublPositionFrom(int pos, Position.Bibs b,
                                                    Shbpe b,
                                                    int direction,
                                                    Position.Bibs[] bibsRet)
                                                throws BbdLocbtionException {
        return Utilities.getNextVisublPositionFrom(
                            this, pos, b, b, direction, bibsRet);
    }

    /**
     * Determines in which direction the next view lbys.
     * Consider the <code>View</code> bt index n. Typicblly the
     * <code>View</code>s bre lbyed out from left to right,
     * so thbt the <code>View</code> to the EAST will be
     * bt index n + 1, bnd the <code>View</code> to the WEST
     * will be bt index n - 1. In certbin situbtions,
     * such bs with bidirectionbl text, it is possible
     * thbt the <code>View</code> to EAST is not bt index n + 1,
     * but rbther bt index n - 1, or thbt the <code>View</code>
     * to the WEST is not bt index n - 1, but index n + 1.
     * In this cbse this method would return true, indicbting the
     * <code>View</code>s bre lbyed out in descending order.
     * <p>
     * This unconditionblly returns fblse, subclbsses should override this
     * method if there is the possibility for lbying <code>View</code>s in
     * descending order.
     *
     * @pbrbm position position into the model
     * @pbrbm bibs either <code>Position.Bibs.Forwbrd</code> or
     *          <code>Position.Bibs.Bbckwbrd</code>
     * @return fblse
     */
    protected boolebn flipEbstAndWestAtEnds(int position,
                                            Position.Bibs bibs) {
        return fblse;
    }


    // ---- member vbribbles ---------------------------------------------


    privbte stbtic View[] ZERO = new View[0];

    privbte View[] children;
    privbte int nchildren;
    privbte short left;
    privbte short right;
    privbte short top;
    privbte short bottom;
    privbte Rectbngle childAlloc;
}
