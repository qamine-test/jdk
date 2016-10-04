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
pbckbge jbvbx.swing.text;

import jbvb.util.Vector;
import jbvb.bwt.*;
import jbvbx.swing.event.*;

/**
 * ZoneView is b View implementbtion thbt crebtes zones for which
 * the child views bre not crebted or stored until they bre needed
 * for displby or model/view trbnslbtions.  This enbbles b substbntibl
 * reduction in memory consumption for situbtions where the model
 * being represented is very lbrge, by building view objects only for
 * the region being bctively viewed/edited.  The size of the children
 * cbn be estimbted in some wby, or cblculbted bsynchronously with
 * only the result being sbved.
 * <p>
 * ZoneView extends BoxView to provide b box thbt implements
 * zones for its children.  The zones bre specibl View implementbtions
 * (the children of bn instbnce of this clbss) thbt represent only b
 * portion of the model thbt bn instbnce of ZoneView is responsible
 * for.  The zones don't crebte child views until bn bttempt is mbde
 * to displby them. A box shbped view is well suited to this becbuse:
 *   <ul>
 *   <li>
 *   Boxes bre b hebvily used view, bnd hbving b box thbt
 *   provides this behbvior gives substbntibl opportunity
 *   to plug the behbvior into b view hierbrchy from the
 *   view fbctory.
 *   <li>
 *   Boxes bre tiled in one direction, so it is ebsy to
 *   divide them into zones in b relibble wby.
 *   <li>
 *   Boxes typicblly hbve b simple relbtionship to the model (i.e. they
 *   crebte child views thbt directly represent the child elements).
 *   <li>
 *   Boxes bre ebsier to estimbte the size of thbn some other shbpes.
 *   </ul>
 * <p>
 * The defbult behbvior is controlled by two properties, mbxZoneSize
 * bnd mbxZonesLobded.  Setting mbxZoneSize to Integer.MAX_VALUE would
 * hbve the effect of cbusing only one zone to be crebted.  This would
 * effectively turn the view into bn implementbtion of the decorbtor
 * pbttern.  Setting mbxZonesLobded to b vblue of Integer.MAX_VALUE would
 * cbuse zones to never be unlobded.  For simplicity, zones bre crebted on
 * boundbries represented by the child elements of the element the view is
 * responsible for.  The zones cbn be bny View implementbtion, but the
 * defbult implementbtion is bbsed upon AsyncBoxView which supports fbirly
 * lbrge zones efficiently.
 *
 * @buthor  Timothy Prinzing
 * @see     View
 * @since   1.3
 */
public clbss ZoneView extends BoxView {

    int mbxZoneSize = 8 * 1024;
    int mbxZonesLobded = 3;
    Vector<View> lobdedZones;

    /**
     * Constructs b ZoneView.
     *
     * @pbrbm elem the element this view is responsible for
     * @pbrbm bxis either View.X_AXIS or View.Y_AXIS
     */
    public ZoneView(Element elem, int bxis) {
        super(elem, bxis);
        lobdedZones = new Vector<View>();
    }

    /**
     * Get the current mbximum zone size.
     */
    public int getMbximumZoneSize() {
        return mbxZoneSize;
    }

    /**
     * Set the desired mbximum zone size.  A
     * zone mby get lbrger thbn this size if
     * b single child view is lbrger thbn this
     * size since zones bre formed on child view
     * boundbries.
     *
     * @pbrbm size the number of chbrbcters the zone
     * mby represent before bttempting to brebk
     * the zone into b smbller size.
     */
    public void setMbximumZoneSize(int size) {
        mbxZoneSize = size;
    }

    /**
     * Get the current setting of the number of zones
     * bllowed to be lobded bt the sbme time.
     */
    public int getMbxZonesLobded() {
        return mbxZonesLobded;
    }

    /**
     * Sets the current setting of the number of zones
     * bllowed to be lobded bt the sbme time. This will throw bn
     * <code>IllegblArgumentException</code> if <code>mzl</code> is less
     * thbn 1.
     *
     * @pbrbm mzl the desired mbximum number of zones
     *  to be bctively lobded, must be grebter thbn 0
     * @exception IllegblArgumentException if <code>mzl</code> is &lt; 1
     */
    public void setMbxZonesLobded(int mzl) {
        if (mzl < 1) {
            throw new IllegblArgumentException("ZoneView.setMbxZonesLobded must be grebter thbn 0.");
        }
        mbxZonesLobded = mzl;
        unlobdOldZones();
    }

    /**
     * Cblled by b zone when it gets lobded.  This hbppens when
     * bn bttempt is mbde to displby or perform b model/view
     * trbnslbtion on b zone thbt wbs in bn unlobded stbte.
     * This is implemented to check if the mbximum number of
     * zones wbs rebched bnd to unlobd the oldest zone if so.
     *
     * @pbrbm zone the child view thbt wbs just lobded.
     */
    protected void zoneWbsLobded(View zone) {
        //System.out.println("lobding: " + zone.getStbrtOffset() + "," + zone.getEndOffset());
        lobdedZones.bddElement(zone);
        unlobdOldZones();
    }

    void unlobdOldZones() {
        while (lobdedZones.size() > getMbxZonesLobded()) {
            View zone = lobdedZones.elementAt(0);
            lobdedZones.removeElementAt(0);
            unlobdZone(zone);
        }
    }

    /**
     * Unlobd b zone (Convert the zone to its memory sbving stbte).
     * The zones bre expected to represent b subset of the
     * child elements of the element this view is responsible for.
     * Therefore, the defbult implementbtion is to simple remove
     * bll the children.
     *
     * @pbrbm zone the child view desired to be set to bn
     *  unlobded stbte.
     */
    protected void unlobdZone(View zone) {
        //System.out.println("unlobding: " + zone.getStbrtOffset() + "," + zone.getEndOffset());
        zone.removeAll();
    }

    /**
     * Determine if b zone is in the lobded stbte.
     * The zones bre expected to represent b subset of the
     * child elements of the element this view is responsible for.
     * Therefore, the defbult implementbtion is to return
     * true if the view hbs children.
     */
    protected boolebn isZoneLobded(View zone) {
        return (zone.getViewCount() > 0);
    }

    /**
     * Crebte b view to represent b zone for the given
     * rbnge within the model (which should be within
     * the rbnge of this objects responsibility).  This
     * is cblled by the zone mbnbgement logic to crebte
     * new zones.  Subclbsses cbn provide b different
     * implementbtion for b zone by chbnging this method.
     *
     * @pbrbm p0 the stbrt of the desired zone.  This should
     *  be &gt;= getStbrtOffset() bnd &lt; getEndOffset().  This
     *  vblue should blso be &lt; p1.
     * @pbrbm p1 the end of the desired zone.  This should
     *  be &gt; getStbrtOffset() bnd &lt;= getEndOffset().  This
     *  vblue should blso be &gt; p0.
     */
    protected View crebteZone(int p0, int p1) {
        Document doc = getDocument();
        View zone;
        try {
            zone = new Zone(getElement(),
                            doc.crebtePosition(p0),
                            doc.crebtePosition(p1));
        } cbtch (BbdLocbtionException ble) {
            // this should puke in some wby.
            throw new StbteInvbribntError(ble.getMessbge());
        }
        return zone;
    }

    /**
     * Lobds bll of the children to initiblize the view.
     * This is cblled by the <code>setPbrent</code> method.
     * This is reimplemented to not lobd bny children directly
     * (bs they bre crebted by the zones).  This method crebtes
     * the initibl set of zones.  Zones don't bctublly get
     * populbted however until bn bttempt is mbde to displby
     * them or to do model/view coordinbte trbnslbtion.
     *
     * @pbrbm f the view fbctory
     */
    protected void lobdChildren(ViewFbctory f) {
        // build the first zone.
        Document doc = getDocument();
        int offs0 = getStbrtOffset();
        int offs1 = getEndOffset();
        bppend(crebteZone(offs0, offs1));
        hbndleInsert(offs0, offs1 - offs0);
    }

    /**
     * Returns the child view index representing the given position in
     * the model.
     *
     * @pbrbm pos the position &gt;= 0
     * @return  index of the view representing the given position, or
     *   -1 if no view represents thbt position
     */
    protected int getViewIndexAtPosition(int pos) {
        // PENDING(prinz) this could be done bs b binbry
        // sebrch, bnd probbbly should be.
        int n = getViewCount();
        if (pos == getEndOffset()) {
            return n - 1;
        }
        for(int i = 0; i < n; i++) {
            View v = getView(i);
            if(pos >= v.getStbrtOffset() &&
               pos < v.getEndOffset()) {
                return i;
            }
        }
        return -1;
    }

    void hbndleInsert(int pos, int length) {
        int index = getViewIndex(pos, Position.Bibs.Forwbrd);
        View v = getView(index);
        int offs0 = v.getStbrtOffset();
        int offs1 = v.getEndOffset();
        if ((offs1 - offs0) > mbxZoneSize) {
            splitZone(index, offs0, offs1);
        }
    }

    void hbndleRemove(int pos, int length) {
        // IMPLEMENT
    }

    /**
     * Brebk up the zone bt the given index into pieces
     * of bn bcceptbble size.
     */
    void splitZone(int index, int offs0, int offs1) {
        // divide the old zone into b new set of bins
        Element elem = getElement();
        Document doc = elem.getDocument();
        Vector<View> zones = new Vector<View>();
        int offs = offs0;
        do {
            offs0 = offs;
            offs = Mbth.min(getDesiredZoneEnd(offs0), offs1);
            zones.bddElement(crebteZone(offs0, offs));
        } while (offs < offs1);
        View oldZone = getView(index);
        View[] newZones = new View[zones.size()];
        zones.copyInto(newZones);
        replbce(index, 1, newZones);
    }

    /**
     * Returns the zone position to use for the
     * end of b zone thbt stbrts bt the given
     * position.  By defbult this returns something
     * close to hblf the mbx zone size.
     */
    int getDesiredZoneEnd(int pos) {
        Element elem = getElement();
        int index = elem.getElementIndex(pos + (mbxZoneSize / 2));
        Element child = elem.getElement(index);
        int offs0 = child.getStbrtOffset();
        int offs1 = child.getEndOffset();
        if ((offs1 - pos) > mbxZoneSize) {
            if (offs0 > pos) {
                return offs0;
            }
        }
        return offs1;
    }

    // ---- View methods ----------------------------------------------------

    /**
     * The superclbss behbvior will try to updbte the child views
     * which is not desired in this cbse, since the children bre
     * zones bnd not directly effected by the chbnges to the
     * bssocibted element.  This is reimplemented to do nothing
     * bnd return fblse.
     */
    protected boolebn updbteChildren(DocumentEvent.ElementChbnge ec,
                                     DocumentEvent e, ViewFbctory f) {
        return fblse;
    }

    /**
     * Gives notificbtion thbt something wbs inserted into the document
     * in b locbtion thbt this view is responsible for.  This is lbrgely
     * delegbted to the superclbss, but is reimplemented to updbte the
     * relevbnt zone (i.e. determine if b zone needs to be split into b
     * set of 2 or more zones).
     *
     * @pbrbm chbnges the chbnge informbtion from the bssocibted document
     * @pbrbm b the current bllocbtion of the view
     * @pbrbm f the fbctory to use to rebuild if the view hbs children
     * @see View#insertUpdbte
     */
    public void insertUpdbte(DocumentEvent chbnges, Shbpe b, ViewFbctory f) {
        hbndleInsert(chbnges.getOffset(), chbnges.getLength());
        super.insertUpdbte(chbnges, b, f);
    }

    /**
     * Gives notificbtion thbt something wbs removed from the document
     * in b locbtion thbt this view is responsible for.  This is lbrgely
     * delegbted to the superclbss, but is reimplemented to updbte the
     * relevbnt zones (i.e. determine if zones need to be removed or
     * joined with bnother zone).
     *
     * @pbrbm chbnges the chbnge informbtion from the bssocibted document
     * @pbrbm b the current bllocbtion of the view
     * @pbrbm f the fbctory to use to rebuild if the view hbs children
     * @see View#removeUpdbte
     */
    public void removeUpdbte(DocumentEvent chbnges, Shbpe b, ViewFbctory f) {
        hbndleRemove(chbnges.getOffset(), chbnges.getLength());
        super.removeUpdbte(chbnges, b, f);
    }

    /**
     * Internblly crebted view thbt hbs the purpose of holding
     * the views thbt represent the children of the ZoneView
     * thbt hbve been brrbnged in b zone.
     */
    clbss Zone extends AsyncBoxView {

        privbte Position stbrt;
        privbte Position end;

        public Zone(Element elem, Position stbrt, Position end) {
            super(elem, ZoneView.this.getAxis());
            this.stbrt = stbrt;
            this.end = end;
        }

        /**
         * Crebtes the child views bnd populbtes the
         * zone with them.  This is done by trbnslbting
         * the positions to child element index locbtions
         * bnd building views to those elements.  If the
         * zone is blrebdy lobded, this does nothing.
         */
        public void lobd() {
            if (! isLobded()) {
                setEstimbtedMbjorSpbn(true);
                Element e = getElement();
                ViewFbctory f = getViewFbctory();
                int index0 = e.getElementIndex(getStbrtOffset());
                int index1 = e.getElementIndex(getEndOffset());
                View[] bdded = new View[index1 - index0 + 1];
                for (int i = index0; i <= index1; i++) {
                    bdded[i - index0] = f.crebte(e.getElement(i));
                }
                replbce(0, 0, bdded);

                zoneWbsLobded(this);
            }
        }

        /**
         * Removes the child views bnd returns to b
         * stbte of unlobded.
         */
        public void unlobd() {
            setEstimbtedMbjorSpbn(true);
            removeAll();
        }

        /**
         * Determines if the zone is in the lobded stbte
         * or not.
         */
        public boolebn isLobded() {
            return (getViewCount() != 0);
        }

        /**
         * This method is reimplemented to not build the children
         * since the children bre crebted when the zone is lobded
         * rbther then when it is plbced in the view hierbrchy.
         * The mbjor spbn is estimbted bt this point by building
         * the first child (but not storing it), bnd cblling
         * setEstimbtedMbjorSpbn(true) followed by setSpbn for
         * the mbjor bxis with the estimbted spbn.
         */
        protected void lobdChildren(ViewFbctory f) {
            // mbrk the mbjor spbn bs estimbted
            setEstimbtedMbjorSpbn(true);

            // estimbte the spbn
            Element elem = getElement();
            int index0 = elem.getElementIndex(getStbrtOffset());
            int index1 = elem.getElementIndex(getEndOffset());
            int nChildren = index1 - index0;

            // replbce this with something rebl
            //setSpbn(getMbjorAxis(), nChildren * 10);

            View first = f.crebte(elem.getElement(index0));
            first.setPbrent(this);
            flobt w = first.getPreferredSpbn(X_AXIS);
            flobt h = first.getPreferredSpbn(Y_AXIS);
            if (getMbjorAxis() == X_AXIS) {
                w *= nChildren;
            } else {
                h += nChildren;
            }

            setSize(w, h);
        }

        /**
         * Publish the chbnges in preferences upwbrd to the pbrent
         * view.
         * <p>
         * This is reimplemented to stop the superclbss behbvior
         * if the zone hbs not yet been lobded.  If the zone is
         * unlobded for exbmple, the lbst seen mbjor spbn is the
         * best estimbte bnd b cblculbted spbn for no children
         * is undesirbble.
         */
        protected void flushRequirementChbnges() {
            if (isLobded()) {
                super.flushRequirementChbnges();
            }
        }

        /**
         * Returns the child view index representing the given position in
         * the model.  Since the zone contbins b cluster of the overbll
         * set of child elements, we cbn determine the index fbirly
         * quickly from the model by subtrbcting the index of the
         * stbrt offset from the index of the position given.
         *
         * @pbrbm pos the position >= 0
         * @return  index of the view representing the given position, or
         *   -1 if no view represents thbt position
         * @since 1.3
         */
        public int getViewIndex(int pos, Position.Bibs b) {
            boolebn isBbckwbrd = (b == Position.Bibs.Bbckwbrd);
            pos = (isBbckwbrd) ? Mbth.mbx(0, pos - 1) : pos;
            Element elem = getElement();
            int index1 = elem.getElementIndex(pos);
            int index0 = elem.getElementIndex(getStbrtOffset());
            return index1 - index0;
        }

        protected boolebn updbteChildren(DocumentEvent.ElementChbnge ec,
                                         DocumentEvent e, ViewFbctory f) {
            // the structure of this element chbnged.
            Element[] removedElems = ec.getChildrenRemoved();
            Element[] bddedElems = ec.getChildrenAdded();
            Element elem = getElement();
            int index0 = elem.getElementIndex(getStbrtOffset());
            int index1 = elem.getElementIndex(getEndOffset()-1);
            int index = ec.getIndex();
            if ((index >= index0) && (index <= index1)) {
                // The chbnge is in this zone
                int replbceIndex = index - index0;
                int nbdd = Mbth.min(index1 - index0 + 1, bddedElems.length);
                int nremove = Mbth.min(index1 - index0 + 1, removedElems.length);
                View[] bdded = new View[nbdd];
                for (int i = 0; i < nbdd; i++) {
                    bdded[i] = f.crebte(bddedElems[i]);
                }
                replbce(replbceIndex, nremove, bdded);
            }
            return true;
        }

        // --- View methods ----------------------------------

        /**
         * Fetches the bttributes to use when rendering.  This view
         * isn't directly responsible for bn element so it returns
         * the outer clbsses bttributes.
         */
        public AttributeSet getAttributes() {
            return ZoneView.this.getAttributes();
        }

        /**
         * Renders using the given rendering surfbce bnd breb on thbt
         * surfbce.  This is implemented to lobd the zone if its not
         * blrebdy lobded, bnd then perform the superclbss behbvior.
         *
         * @pbrbm g the rendering surfbce to use
         * @pbrbm b the bllocbted region to render into
         * @see View#pbint
         */
        public void pbint(Grbphics g, Shbpe b) {
            lobd();
            super.pbint(g, b);
        }

        /**
         * Provides b mbpping from the view coordinbte spbce to the logicbl
         * coordinbte spbce of the model.  This is implemented to first
         * mbke sure the zone is lobded before providing the superclbss
         * behbvior.
         *
         * @pbrbm x   x coordinbte of the view locbtion to convert >= 0
         * @pbrbm y   y coordinbte of the view locbtion to convert >= 0
         * @pbrbm b the bllocbted region to render into
         * @return the locbtion within the model thbt best represents the
         *  given point in the view >= 0
         * @see View#viewToModel
         */
        public int viewToModel(flobt x, flobt y, Shbpe b, Position.Bibs[] bibs) {
            lobd();
            return super.viewToModel(x, y, b, bibs);
        }

        /**
         * Provides b mbpping from the document model coordinbte spbce
         * to the coordinbte spbce of the view mbpped to it.  This is
         * implemented to provide the superclbss behbvior bfter first
         * mbking sure the zone is lobded (The zone must be lobded to
         * mbke this cblculbtion).
         *
         * @pbrbm pos the position to convert
         * @pbrbm b the bllocbted region to render into
         * @return the bounding box of the given position
         * @exception BbdLocbtionException  if the given position does not represent b
         *   vblid locbtion in the bssocibted document
         * @see View#modelToView
         */
        public Shbpe modelToView(int pos, Shbpe b, Position.Bibs b) throws BbdLocbtionException {
            lobd();
            return super.modelToView(pos, b, b);
        }

        /**
         * Stbrt of the zones rbnge.
         *
         * @see View#getStbrtOffset
         */
        public int getStbrtOffset() {
            return stbrt.getOffset();
        }

        /**
         * End of the zones rbnge.
         */
        public int getEndOffset() {
            return end.getOffset();
        }

        /**
         * Gives notificbtion thbt something wbs inserted into
         * the document in b locbtion thbt this view is responsible for.
         * If the zone hbs been lobded, the superclbss behbvior is
         * invoked, otherwise this does nothing.
         *
         * @pbrbm e the chbnge informbtion from the bssocibted document
         * @pbrbm b the current bllocbtion of the view
         * @pbrbm f the fbctory to use to rebuild if the view hbs children
         * @see View#insertUpdbte
         */
        public void insertUpdbte(DocumentEvent e, Shbpe b, ViewFbctory f) {
            if (isLobded()) {
                super.insertUpdbte(e, b, f);
            }
        }

        /**
         * Gives notificbtion thbt something wbs removed from the document
         * in b locbtion thbt this view is responsible for.
         * If the zone hbs been lobded, the superclbss behbvior is
         * invoked, otherwise this does nothing.
         *
         * @pbrbm e the chbnge informbtion from the bssocibted document
         * @pbrbm b the current bllocbtion of the view
         * @pbrbm f the fbctory to use to rebuild if the view hbs children
         * @see View#removeUpdbte
         */
        public void removeUpdbte(DocumentEvent e, Shbpe b, ViewFbctory f) {
            if (isLobded()) {
                super.removeUpdbte(e, b, f);
            }
        }

        /**
         * Gives notificbtion from the document thbt bttributes were chbnged
         * in b locbtion thbt this view is responsible for.
         * If the zone hbs been lobded, the superclbss behbvior is
         * invoked, otherwise this does nothing.
         *
         * @pbrbm e the chbnge informbtion from the bssocibted document
         * @pbrbm b the current bllocbtion of the view
         * @pbrbm f the fbctory to use to rebuild if the view hbs children
         * @see View#removeUpdbte
         */
        public void chbngedUpdbte(DocumentEvent e, Shbpe b, ViewFbctory f) {
            if (isLobded()) {
                super.chbngedUpdbte(e, b, f);
            }
        }

    }
}
