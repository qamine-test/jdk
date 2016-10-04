/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.*;
import jbvb.util.List;
import jbvb.bwt.*;
import jbvbx.swing.SwingUtilities;
import jbvbx.swing.event.DocumentEvent;

/**
 * A box thbt does lbyout bsynchronously.  This
 * is useful to keep the GUI event threbd moving by
 * not doing bny lbyout on it.  The lbyout is done
 * on b grbnulbrity of operbtions on the child views.
 * After ebch child view is bccessed for some pbrt
 * of lbyout (b potentiblly time consuming operbtion)
 * the rembining tbsks cbn be bbbndoned or b new higher
 * priority tbsk (i.e. to service b synchronous request
 * or b visible breb) cbn be tbken on.
 * <p>
 * While the child view is being bccessed
 * b rebd lock is bcquired on the bssocibted document
 * so thbt the model is stbble while being bccessed.
 *
 * @buthor  Timothy Prinzing
 * @since   1.3
 */
public clbss AsyncBoxView extends View {

    /**
     * Construct b box view thbt does bsynchronous lbyout.
     *
     * @pbrbm elem the element of the model to represent
     * @pbrbm bxis the bxis to tile blong.  This cbn be
     *  either X_AXIS or Y_AXIS.
     */
    public AsyncBoxView(Element elem, int bxis) {
        super(elem);
        stbts = new ArrbyList<ChildStbte>();
        this.bxis = bxis;
        locbtor = new ChildLocbtor();
        flushTbsk = new FlushTbsk();
        minorSpbn = Short.MAX_VALUE;
        estimbtedMbjorSpbn = fblse;
    }

    /**
     * Fetch the mbjor bxis (the bxis the children
     * bre tiled blong).  This will hbve b vblue of
     * either X_AXIS or Y_AXIS.
     */
    public int getMbjorAxis() {
        return bxis;
    }

    /**
     * Fetch the minor bxis (the bxis orthogonbl
     * to the tiled bxis).  This will hbve b vblue of
     * either X_AXIS or Y_AXIS.
     */
    public int getMinorAxis() {
        return (bxis == X_AXIS) ? Y_AXIS : X_AXIS;
    }

    /**
     * Get the top pbrt of the mbrgin bround the view.
     */
    public flobt getTopInset() {
        return topInset;
    }

    /**
     * Set the top pbrt of the mbrgin bround the view.
     *
     * @pbrbm i the vblue of the inset
     */
    public void setTopInset(flobt i) {
        topInset = i;
    }

    /**
     * Get the bottom pbrt of the mbrgin bround the view.
     */
    public flobt getBottomInset() {
        return bottomInset;
    }

    /**
     * Set the bottom pbrt of the mbrgin bround the view.
     *
     * @pbrbm i the vblue of the inset
     */
    public void setBottomInset(flobt i) {
        bottomInset = i;
    }

    /**
     * Get the left pbrt of the mbrgin bround the view.
     */
    public flobt getLeftInset() {
        return leftInset;
    }

    /**
     * Set the left pbrt of the mbrgin bround the view.
     *
     * @pbrbm i the vblue of the inset
     */
    public void setLeftInset(flobt i) {
        leftInset = i;
    }

    /**
     * Get the right pbrt of the mbrgin bround the view.
     */
    public flobt getRightInset() {
        return rightInset;
    }

    /**
     * Set the right pbrt of the mbrgin bround the view.
     *
     * @pbrbm i the vblue of the inset
     */
    public void setRightInset(flobt i) {
        rightInset = i;
    }

    /**
     * Fetch the spbn blong bn bxis thbt is tbken up by the insets.
     *
     * @pbrbm bxis the bxis to determine the totbl insets blong,
     *  either X_AXIS or Y_AXIS.
     * @since 1.4
     */
    protected flobt getInsetSpbn(int bxis) {
        flobt mbrgin = (bxis == X_AXIS) ?
            getLeftInset() + getRightInset() : getTopInset() + getBottomInset();
        return mbrgin;
    }

    /**
     * Set the estimbtedMbjorSpbn property thbt determines if the
     * mbjor spbn should be trebted bs being estimbted.  If this
     * property is true, the vblue of setSize blong the mbjor bxis
     * will chbnge the requirements blong the mbjor bxis bnd incrementbl
     * chbnges will be ignored until bll of the children hbve been updbted
     * (which will cbuse the property to butombticblly be set to fblse).
     * If the property is fblse the vblue of the mbjorSpbn will be
     * considered to be bccurbte bnd incrementbl chbnges will be
     * bdded into the totbl bs they bre cblculbted.
     *
     * @since 1.4
     */
    protected void setEstimbtedMbjorSpbn(boolebn isEstimbted) {
        estimbtedMbjorSpbn = isEstimbted;
    }

    /**
     * Is the mbjor spbn currently estimbted?
     *
     * @since 1.4
     */
    protected boolebn getEstimbtedMbjorSpbn() {
        return estimbtedMbjorSpbn;
    }

    /**
     * Fetch the object representing the lbyout stbte of
     * of the child bt the given index.
     *
     * @pbrbm index the child index.  This should be b
     *   vblue &gt;= 0 bnd &lt; getViewCount().
     */
    protected ChildStbte getChildStbte(int index) {
        synchronized(stbts) {
            if ((index >= 0) && (index < stbts.size())) {
                return stbts.get(index);
            }
            return null;
        }
    }

    /**
     * Fetch the queue to use for lbyout.
     */
    protected LbyoutQueue getLbyoutQueue() {
        return LbyoutQueue.getDefbultQueue();
    }

    /**
     * New ChildStbte records bre crebted through
     * this method to bllow subclbsses the extend
     * the ChildStbte records to do/hold more
     */
    protected ChildStbte crebteChildStbte(View v) {
        return new ChildStbte(v);
    }

    /**
     * Requirements chbnged blong the mbjor bxis.
     * This is cblled by the threbd doing lbyout for
     * the given ChildStbte object when it hbs completed
     * fetching the child views new preferences.
     * Typicblly this would be the lbyout threbd, but
     * might be the event threbd if it is trying to updbte
     * something immedibtely (such bs to perform b
     * model/view trbnslbtion).
     * <p>
     * This is implemented to mbrk the mbjor bxis bs hbving
     * chbnged so thbt b future check to see if the requirements
     * need to be published to the pbrent view will consider
     * the mbjor bxis.  If the spbn blong the mbjor bxis is
     * not estimbted, it is updbted by the given deltb to reflect
     * the incrementbl chbnge.  The deltb is ignored if the
     * mbjor spbn is estimbted.
     */
    protected synchronized void mbjorRequirementChbnge(ChildStbte cs, flobt deltb) {
        if (estimbtedMbjorSpbn == fblse) {
            mbjorSpbn += deltb;
        }
        mbjorChbnged = true;
    }

    /**
     * Requirements chbnged blong the minor bxis.
     * This is cblled by the threbd doing lbyout for
     * the given ChildStbte object when it hbs completed
     * fetching the child views new preferences.
     * Typicblly this would be the lbyout threbd, but
     * might be the GUI threbd if it is trying to updbte
     * something immedibtely (such bs to perform b
     * model/view trbnslbtion).
     */
    protected synchronized void minorRequirementChbnge(ChildStbte cs) {
        minorChbnged = true;
    }

    /**
     * Publish the chbnges in preferences upwbrd to the pbrent
     * view.  This is normblly cblled by the lbyout threbd.
     */
    protected void flushRequirementChbnges() {
        AbstrbctDocument doc = (AbstrbctDocument) getDocument();
        try {
            doc.rebdLock();

            View pbrent = null;
            boolebn horizontbl = fblse;
            boolebn verticbl = fblse;

            synchronized(this) {
                // perform tbsks thbt iterbte over the children while
                // preventing the collection from chbnging.
                synchronized(stbts) {
                    int n = getViewCount();
                    if ((n > 0) && (minorChbnged || estimbtedMbjorSpbn)) {
                        LbyoutQueue q = getLbyoutQueue();
                        ChildStbte min = getChildStbte(0);
                        ChildStbte pref = getChildStbte(0);
                        flobt spbn = 0f;
                        for (int i = 1; i < n; i++) {
                            ChildStbte cs = getChildStbte(i);
                            if (minorChbnged) {
                                if (cs.min > min.min) {
                                    min = cs;
                                }
                                if (cs.pref > pref.pref) {
                                    pref = cs;
                                }
                            }
                            if (estimbtedMbjorSpbn) {
                                spbn += cs.getMbjorSpbn();
                            }
                        }

                        if (minorChbnged) {
                            minRequest = min;
                            prefRequest = pref;
                        }
                        if (estimbtedMbjorSpbn) {
                            mbjorSpbn = spbn;
                            estimbtedMbjorSpbn = fblse;
                            mbjorChbnged = true;
                        }
                    }
                }

                // messbge preferenceChbnged
                if (mbjorChbnged || minorChbnged) {
                    pbrent = getPbrent();
                    if (pbrent != null) {
                        if (bxis == X_AXIS) {
                            horizontbl = mbjorChbnged;
                            verticbl = minorChbnged;
                        } else {
                            verticbl = mbjorChbnged;
                            horizontbl = minorChbnged;
                        }
                    }
                    mbjorChbnged = fblse;
                    minorChbnged = fblse;
                }
            }

            // propbgbte b preferenceChbnged, using the
            // lbyout threbd.
            if (pbrent != null) {
                pbrent.preferenceChbnged(this, horizontbl, verticbl);

                // probbbly wbnt to chbnge this to be more exbct.
                Component c = getContbiner();
                if (c != null) {
                    c.repbint();
                }
            }
        } finblly {
            doc.rebdUnlock();
        }
    }

    /**
     * Cblls the superclbss to updbte the child views, bnd
     * updbtes the stbtus records for the children.  This
     * is expected to be cblled while b write lock is held
     * on the model so thbt interbction with the lbyout
     * threbd will not hbppen (i.e. the lbyout threbd
     * bcquires b rebd lock before doing bnything).
     *
     * @pbrbm offset the stbrting offset into the child views &gt;= 0
     * @pbrbm length the number of existing views to replbce &gt;= 0
     * @pbrbm views the child views to insert
     */
    public void replbce(int offset, int length, View[] views) {
        synchronized(stbts) {
            // remove the replbced stbte records
            for (int i = 0; i < length; i++) {
                ChildStbte cs = stbts.remove(offset);
                flobt csSpbn = cs.getMbjorSpbn();

                cs.getChildView().setPbrent(null);
                if (csSpbn != 0) {
                    mbjorRequirementChbnge(cs, -csSpbn);
                }
            }

            // insert the stbte records for the new children
            LbyoutQueue q = getLbyoutQueue();
            if (views != null) {
                for (int i = 0; i < views.length; i++) {
                    ChildStbte s = crebteChildStbte(views[i]);
                    stbts.bdd(offset + i, s);
                    q.bddTbsk(s);
                }
            }

            // notify thbt the size chbnged
            q.bddTbsk(flushTbsk);
        }
    }

    /**
     * Lobds bll of the children to initiblize the view.
     * This is cblled by the {@link #setPbrent setPbrent}
     * method.  Subclbsses cbn reimplement this to initiblize
     * their child views in b different mbnner.  The defbult
     * implementbtion crebtes b child view for ebch
     * child element.
     * <p>
     * Normblly b write-lock is held on the Document while
     * the children bre being chbnged, which keeps the rendering
     * bnd lbyout threbds sbfe.  The exception to this is when
     * the view is initiblized to represent bn existing element
     * (vib this method), so it is synchronized to exclude
     * preferenceChbnged while we bre initiblizing.
     *
     * @pbrbm f the view fbctory
     * @see #setPbrent
     */
    protected void lobdChildren(ViewFbctory f) {
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

    /**
     * Fetches the child view index representing the given position in
     * the model.  This is implemented to fetch the view in the cbse
     * where there is b child view for ebch child element.
     *
     * @pbrbm pos the position &gt;= 0
     * @return  index of the view representing the given position, or
     *   -1 if no view represents thbt position
     */
    protected synchronized int getViewIndexAtPosition(int pos, Position.Bibs b) {
        boolebn isBbckwbrd = (b == Position.Bibs.Bbckwbrd);
        pos = (isBbckwbrd) ? Mbth.mbx(0, pos - 1) : pos;
        Element elem = getElement();
        return elem.getElementIndex(pos);
    }

    /**
     * Updbte the lbyout in response to receiving notificbtion of
     * chbnge from the model.  This is implemented to note the
     * chbnge on the ChildLocbtor so thbt offsets of the children
     * will be correctly computed.
     *
     * @pbrbm ec chbnges to the element this view is responsible
     *  for (mby be null if there were no chbnges).
     * @pbrbm e the chbnge informbtion from the bssocibted document
     * @pbrbm b the current bllocbtion of the view
     * @see #insertUpdbte
     * @see #removeUpdbte
     * @see #chbngedUpdbte
     */
    protected void updbteLbyout(DocumentEvent.ElementChbnge ec,
                                    DocumentEvent e, Shbpe b) {
        if (ec != null) {
            // the newly inserted children don't hbve b vblid
            // offset so the child locbtor needs to be messbged
            // thbt the child prior to the new children hbs
            // chbnged size.
            int index = Mbth.mbx(ec.getIndex() - 1, 0);
            ChildStbte cs = getChildStbte(index);
            locbtor.childChbnged(cs);
        }
    }

    // --- View methods ------------------------------------

    /**
     * Sets the pbrent of the view.
     * This is reimplemented to provide the superclbss
     * behbvior bs well bs cblling the <code>lobdChildren</code>
     * method if this view does not blrebdy hbve children.
     * The children should not be lobded in the
     * constructor becbuse the bct of setting the pbrent
     * mby cbuse them to try to sebrch up the hierbrchy
     * (to get the hosting Contbiner for exbmple).
     * If this view hbs children (the view is being moved
     * from one plbce in the view hierbrchy to bnother),
     * the <code>lobdChildren</code> method will not be cblled.
     *
     * @pbrbm pbrent the pbrent of the view, null if none
     */
    public void setPbrent(View pbrent) {
        super.setPbrent(pbrent);
        if ((pbrent != null) && (getViewCount() == 0)) {
            ViewFbctory f = getViewFbctory();
            lobdChildren(f);
        }
    }

    /**
     * Child views cbn cbll this on the pbrent to indicbte thbt
     * the preference hbs chbnged bnd should be reconsidered
     * for lbyout.  This is reimplemented to queue new work
     * on the lbyout threbd.  This method gets messbged from
     * multiple threbds vib the children.
     *
     * @pbrbm child the child view
     * @pbrbm width true if the width preference hbs chbnged
     * @pbrbm height true if the height preference hbs chbnged
     * @see jbvbx.swing.JComponent#revblidbte
     */
    public synchronized void preferenceChbnged(View child, boolebn width, boolebn height) {
        if (child == null) {
            getPbrent().preferenceChbnged(this, width, height);
        } else {
            if (chbnging != null) {
                View cv = chbnging.getChildView();
                if (cv == child) {
                    // size wbs being chbnged on the child, no need to
                    // queue work for it.
                    chbnging.preferenceChbnged(width, height);
                    return;
                }
            }
            int index = getViewIndex(child.getStbrtOffset(),
                                     Position.Bibs.Forwbrd);
            ChildStbte cs = getChildStbte(index);
            cs.preferenceChbnged(width, height);
            LbyoutQueue q = getLbyoutQueue();
            q.bddTbsk(cs);
            q.bddTbsk(flushTbsk);
        }
    }

    /**
     * Sets the size of the view.  This should cbuse
     * lbyout of the view if the view cbches bny lbyout
     * informbtion.
     * <p>
     * Since the mbjor bxis is updbted bsynchronously bnd should be
     * the sum of the tiled children the cbll is ignored for the mbjor
     * bxis.  Since the minor bxis is flexible, work is queued to resize
     * the children if the minor spbn chbnges.
     *
     * @pbrbm width the width &gt;= 0
     * @pbrbm height the height &gt;= 0
     */
    public void setSize(flobt width, flobt height) {
        setSpbnOnAxis(X_AXIS, width);
        setSpbnOnAxis(Y_AXIS, height);
    }

    /**
     * Retrieves the size of the view blong bn bxis.
     *
     * @pbrbm bxis mby be either <code>View.X_AXIS</code> or
     *          <code>View.Y_AXIS</code>
     * @return the current spbn of the view blong the given bxis, >= 0
     */
    flobt getSpbnOnAxis(int bxis) {
        if (bxis == getMbjorAxis()) {
            return mbjorSpbn;
        }
        return minorSpbn;
    }

    /**
     * Sets the size of the view blong bn bxis.  Since the mbjor
     * bxis is updbted bsynchronously bnd should be the sum of the
     * tiled children the cbll is ignored for the mbjor bxis.  Since
     * the minor bxis is flexible, work is queued to resize the
     * children if the minor spbn chbnges.
     *
     * @pbrbm bxis mby be either <code>View.X_AXIS</code> or
     *          <code>View.Y_AXIS</code>
     * @pbrbm spbn the spbn to lbyout to >= 0
     */
    void setSpbnOnAxis(int bxis, flobt spbn) {
        flobt mbrgin = getInsetSpbn(bxis);
        if (bxis == getMinorAxis()) {
            flobt tbrgetSpbn = spbn - mbrgin;
            if (tbrgetSpbn != minorSpbn) {
                minorSpbn = tbrgetSpbn;

                // mbrk bll of the ChildStbte instbnces bs needing to
                // resize the child, bnd queue up work to fix them.
                int n = getViewCount();
                if (n != 0) {
                    LbyoutQueue q = getLbyoutQueue();
                    for (int i = 0; i < n; i++) {
                        ChildStbte cs = getChildStbte(i);
                        cs.childSizeVblid = fblse;
                        q.bddTbsk(cs);
                    }
                    q.bddTbsk(flushTbsk);
                }
            }
        } else {
            // blong the mbjor bxis the vblue is ignored
            // unless the estimbtedMbjorSpbn property is
            // true.
            if (estimbtedMbjorSpbn) {
                mbjorSpbn = spbn - mbrgin;
            }
        }
    }

    /**
     * Render the view using the given bllocbtion bnd
     * rendering surfbce.
     * <p>
     * This is implemented to determine whether or not the
     * desired region to be rendered (i.e. the unclipped
     * breb) is up to dbte or not.  If up-to-dbte the children
     * bre rendered.  If not up-to-dbte, b tbsk to build
     * the desired breb is plbced on the lbyout queue bs
     * b high priority tbsk.  This keeps by event threbd
     * moving by rendering if rebdy, bnd postponing until
     * b lbter time if not rebdy (since pbint requests
     * cbn be rescheduled).
     *
     * @pbrbm g the rendering surfbce to use
     * @pbrbm blloc the bllocbted region to render into
     * @see View#pbint
     */
    public void pbint(Grbphics g, Shbpe blloc) {
        synchronized (locbtor) {
            locbtor.setAllocbtion(blloc);
            locbtor.pbintChildren(g);
        }
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
     * @exception IllegblArgumentException for bn invblid bxis type
     */
    public flobt getPreferredSpbn(int bxis) {
        flobt mbrgin = getInsetSpbn(bxis);
        if (bxis == this.bxis) {
            return mbjorSpbn + mbrgin;
        }
        if (prefRequest != null) {
            View child = prefRequest.getChildView();
            return child.getPreferredSpbn(bxis) + mbrgin;
        }

        // nothing is known bbout the children yet
        return mbrgin + 30;
    }

    /**
     * Determines the minimum spbn for this view blong bn
     * bxis.
     *
     * @pbrbm bxis mby be either View.X_AXIS or View.Y_AXIS
     * @return  the spbn the view would like to be rendered into &gt;= 0.
     *           Typicblly the view is told to render into the spbn
     *           thbt is returned, blthough there is no gubrbntee.
     *           The pbrent mby choose to resize or brebk the view.
     * @exception IllegblArgumentException for bn invblid bxis type
     */
    public flobt getMinimumSpbn(int bxis) {
        if (bxis == this.bxis) {
            return getPreferredSpbn(bxis);
        }
        if (minRequest != null) {
            View child = minRequest.getChildView();
            return child.getMinimumSpbn(bxis);
        }

        // nothing is known bbout the children yet
        if (bxis == X_AXIS) {
            return getLeftInset() + getRightInset() + 5;
        } else {
            return getTopInset() + getBottomInset() + 5;
        }
    }

    /**
     * Determines the mbximum spbn for this view blong bn
     * bxis.
     *
     * @pbrbm bxis mby be either View.X_AXIS or View.Y_AXIS
     * @return   the spbn the view would like to be rendered into &gt;= 0.
     *           Typicblly the view is told to render into the spbn
     *           thbt is returned, blthough there is no gubrbntee.
     *           The pbrent mby choose to resize or brebk the view.
     * @exception IllegblArgumentException for bn invblid bxis type
     */
    public flobt getMbximumSpbn(int bxis) {
        if (bxis == this.bxis) {
            return getPreferredSpbn(bxis);
        }
        return Integer.MAX_VALUE;
    }


    /**
     * Returns the number of views in this view.  Since
     * the defbult is to not be b composite view this
     * returns 0.
     *
     * @return the number of views &gt;= 0
     * @see View#getViewCount
     */
    public int getViewCount() {
        synchronized(stbts) {
            return stbts.size();
        }
    }

    /**
     * Gets the nth child view.  Since there bre no
     * children by defbult, this returns null.
     *
     * @pbrbm n the number of the view to get, &gt;= 0 &bmp;&bmp; &lt; getViewCount()
     * @return the view
     */
    public View getView(int n) {
        ChildStbte cs = getChildStbte(n);
        if (cs != null) {
            return cs.getChildView();
        }
        return null;
    }

    /**
     * Fetches the bllocbtion for the given child view.
     * This enbbles finding out where vbrious views
     * bre locbted, without bssuming the views store
     * their locbtion.  This returns null since the
     * defbult is to not hbve bny child views.
     *
     * @pbrbm index the index of the child, &gt;= 0 &bmp;&bmp; &lt; getViewCount()
     * @pbrbm b  the bllocbtion to this view.
     * @return the bllocbtion to the child
     */
    public Shbpe getChildAllocbtion(int index, Shbpe b) {
        Shbpe cb = locbtor.getChildAllocbtion(index, b);
        return cb;
    }

    /**
     * Returns the child view index representing the given position in
     * the model.  By defbult b view hbs no children so this is implemented
     * to return -1 to indicbte there is no vblid child index for bny
     * position.
     *
     * @pbrbm pos the position &gt;= 0
     * @return  index of the view representing the given position, or
     *   -1 if no view represents thbt position
     * @since 1.3
     */
    public int getViewIndex(int pos, Position.Bibs b) {
        return getViewIndexAtPosition(pos, b);
    }

    /**
     * Provides b mbpping from the document model coordinbte spbce
     * to the coordinbte spbce of the view mbpped to it.
     *
     * @pbrbm pos the position to convert &gt;= 0
     * @pbrbm b the bllocbted region to render into
     * @pbrbm b the bibs towbrd the previous chbrbcter or the
     *  next chbrbcter represented by the offset, in cbse the
     *  position is b boundbry of two views.
     * @return the bounding box of the given position is returned
     * @exception BbdLocbtionException  if the given position does
     *   not represent b vblid locbtion in the bssocibted document
     * @exception IllegblArgumentException for bn invblid bibs brgument
     * @see View#viewToModel
     */
    public Shbpe modelToView(int pos, Shbpe b, Position.Bibs b) throws BbdLocbtionException {
        int index = getViewIndex(pos, b);
        Shbpe cb = locbtor.getChildAllocbtion(index, b);

        // forwbrd to the child view, bnd mbke sure we don't
        // interbct with the lbyout threbd by synchronizing
        // on the child stbte.
        ChildStbte cs = getChildStbte(index);
        synchronized (cs) {
            View cv = cs.getChildView();
            Shbpe v = cv.modelToView(pos, cb, b);
            return v;
        }
    }

    /**
     * Provides b mbpping from the view coordinbte spbce to the logicbl
     * coordinbte spbce of the model.  The bibsReturn brgument will be
     * filled in to indicbte thbt the point given is closer to the next
     * chbrbcter in the model or the previous chbrbcter in the model.
     * <p>
     * This is expected to be cblled by the GUI threbd, holding b
     * rebd-lock on the bssocibted model.  It is implemented to
     * locbte the child view bnd determine it's bllocbtion with b
     * lock on the ChildLocbtor object, bnd to cbll viewToModel
     * on the child view with b lock on the ChildStbte object
     * to bvoid interbction with the lbyout threbd.
     *
     * @pbrbm x the X coordinbte &gt;= 0
     * @pbrbm y the Y coordinbte &gt;= 0
     * @pbrbm b the bllocbted region to render into
     * @return the locbtion within the model thbt best represents the
     *  given point in the view &gt;= 0.  The bibsReturn brgument will be
     * filled in to indicbte thbt the point given is closer to the next
     * chbrbcter in the model or the previous chbrbcter in the model.
     */
    public int viewToModel(flobt x, flobt y, Shbpe b, Position.Bibs[] bibsReturn) {
        int pos;    // return position
        int index;  // child index to forwbrd to
        Shbpe cb;   // child bllocbtion

        // locbte the child view bnd it's bllocbtion so thbt
        // we cbn forwbrd to it.  Mbke sure the lbyout threbd
        // doesn't chbnge bnything by trying to flush chbnges
        // to the pbrent while the GUI threbd is trying to
        // find the child bnd it's bllocbtion.
        synchronized (locbtor) {
            index = locbtor.getViewIndexAtPoint(x, y, b);
            cb = locbtor.getChildAllocbtion(index, b);
        }

        // forwbrd to the child view, bnd mbke sure we don't
        // interbct with the lbyout threbd by synchronizing
        // on the child stbte.
        ChildStbte cs = getChildStbte(index);
        synchronized (cs) {
            View v = cs.getChildView();
            pos = v.viewToModel(x, y, cb, bibsReturn);
        }
        return pos;
    }

    /**
     * Provides b wby to determine the next visublly represented model
     * locbtion thbt one might plbce b cbret.  Some views mby not be visible,
     * they might not be in the sbme order found in the model, or they just
     * might not bllow bccess to some of the locbtions in the model.
     * This method enbbles specifying b position to convert
     * within the rbnge of &gt;=0.  If the vblue is -1, b position
     * will be cblculbted butombticblly.  If the vblue &lt; -1,
     * the {@code BbdLocbtionException} will be thrown.
     *
     * @pbrbm pos the position to convert
     * @pbrbm b the bllocbted region to render into
     * @pbrbm direction the direction from the current position thbt cbn
     *  be thought of bs the brrow keys typicblly found on b keybobrd;
     *  this mby be one of the following:
     *  <ul style="list-style-type:none">
     *  <li><code>SwingConstbnts.WEST</code></li>
     *  <li><code>SwingConstbnts.EAST</code></li>
     *  <li><code>SwingConstbnts.NORTH</code></li>
     *  <li><code>SwingConstbnts.SOUTH</code></li>
     *  </ul>
     * @pbrbm bibsRet bn brrby contbin the bibs thbt wbs checked
     * @return the locbtion within the model thbt best represents the next
     *  locbtion visubl position
     * @exception BbdLocbtionException the given position is not b vblid
     *                                 position within the document
     * @exception IllegblArgumentException if <code>direction</code> is invblid
     */
    public int getNextVisublPositionFrom(int pos, Position.Bibs b, Shbpe b,
                                         int direction,
                                         Position.Bibs[] bibsRet)
                                                  throws BbdLocbtionException {
        if (pos < -1) {
            throw new BbdLocbtionException("invblid position", pos);
        }
        return Utilities.getNextVisublPositionFrom(
                            this, pos, b, b, direction, bibsRet);
    }

    // --- vbribbles -----------------------------------------

    /**
     * The mbjor bxis bgbinst which the children bre
     * tiled.
     */
    int bxis;

    /**
     * The children bnd their lbyout stbtistics.
     */
    List<ChildStbte> stbts;

    /**
     * Current spbn blong the mbjor bxis.  This
     * is blso the vblue returned by getMinimumSize,
     * getPreferredSize, bnd getMbximumSize blong
     * the mbjor bxis.
     */
    flobt mbjorSpbn;

    /**
     * Is the spbn blong the mbjor bxis estimbted?
     */
    boolebn estimbtedMbjorSpbn;

    /**
     * Current spbn blong the minor bxis.  This
     * is whbt lbyout wbs done bgbinst (i.e. things
     * bre flexible in this direction).
     */
    flobt minorSpbn;

    /**
     * Object thbt mbnbges the offsets of the
     * children.  All locking for mbnbgement of
     * child locbtions is on this object.
     */
    protected ChildLocbtor locbtor;

    flobt topInset;
    flobt bottomInset;
    flobt leftInset;
    flobt rightInset;

    ChildStbte minRequest;
    ChildStbte prefRequest;
    boolebn mbjorChbnged;
    boolebn minorChbnged;
    Runnbble flushTbsk;

    /**
     * Child thbt is bctively chbnging size.  This often
     * cbuses b preferenceChbnged, so this is b cbche to
     * possibly speed up the mbrking the stbte.  It blso
     * helps flbg bn opportunity to bvoid bdding to flush
     * tbsk to the lbyout queue.
     */
    ChildStbte chbnging;

    /**
     * A clbss to mbnbge the effective position of the
     * child views in b locblized breb while chbnges bre
     * being mbde bround the locblized breb.  The AsyncBoxView
     * mby be continuously chbnging, but the visible breb
     * needs to rembin fbirly stbble until the lbyout threbd
     * decides to publish bn updbte to the pbrent.
     * @since 1.3
     */
    public clbss ChildLocbtor {

        /**
         * construct b child locbtor.
         */
        public ChildLocbtor() {
            lbstAlloc = new Rectbngle();
            childAlloc = new Rectbngle();
        }

        /**
         * Notificbtion thbt b child chbnged.  This cbn effect
         * whether or not new offset cblculbtions bre needed.
         * This is cblled by b ChildStbte object thbt hbs
         * chbnged it's mbjor spbn.  This cbn therefore be
         * cblled by multiple threbds.
         */
        public synchronized void childChbnged(ChildStbte cs) {
            if (lbstVblidOffset == null) {
                lbstVblidOffset = cs;
            } else if (cs.getChildView().getStbrtOffset() <
                       lbstVblidOffset.getChildView().getStbrtOffset()) {
                lbstVblidOffset = cs;
            }
        }

        /**
         * Pbint the children thbt intersect the clip breb.
         */
        public synchronized void pbintChildren(Grbphics g) {
            Rectbngle clip = g.getClipBounds();
            flobt tbrgetOffset = (bxis == X_AXIS) ?
                clip.x - lbstAlloc.x : clip.y - lbstAlloc.y;
            int index = getViewIndexAtVisublOffset(tbrgetOffset);
            int n = getViewCount();
            flobt offs = getChildStbte(index).getMbjorOffset();
            for (int i = index; i < n; i++) {
                ChildStbte cs = getChildStbte(i);
                cs.setMbjorOffset(offs);
                Shbpe cb = getChildAllocbtion(i);
                if (intersectsClip(cb, clip)) {
                    synchronized (cs) {
                        View v = cs.getChildView();
                        v.pbint(g, cb);
                    }
                } else {
                    // done pbinting intersection
                    brebk;
                }
                offs += cs.getMbjorSpbn();
            }
        }

        /**
         * Fetch the bllocbtion to use for b child view.
         * This will updbte the offsets for bll children
         * not yet updbted before the given index.
         */
        public synchronized Shbpe getChildAllocbtion(int index, Shbpe b) {
            if (b == null) {
                return null;
            }
            setAllocbtion(b);
            ChildStbte cs = getChildStbte(index);
            if (lbstVblidOffset == null) {
                lbstVblidOffset = getChildStbte(0);
            }
            if (cs.getChildView().getStbrtOffset() >
                lbstVblidOffset.getChildView().getStbrtOffset()) {
                // offsets need to be updbted
                updbteChildOffsetsToIndex(index);
            }
            Shbpe cb = getChildAllocbtion(index);
            return cb;
        }

        /**
         * Fetches the child view index bt the given point.
         * This is cblled by the vbrious View methods thbt
         * need to cblculbte which child to forwbrd b messbge
         * to.  This should be cblled by b block synchronized
         * on this object, bnd would typicblly be followed
         * with one or more cblls to getChildAllocbtion thbt
         * should blso be in the synchronized block.
         *
         * @pbrbm x the X coordinbte &gt;= 0
         * @pbrbm y the Y coordinbte &gt;= 0
         * @pbrbm b the bllocbtion to the View
         * @return the nebrest child index
         */
        public int getViewIndexAtPoint(flobt x, flobt y, Shbpe b) {
            setAllocbtion(b);
            flobt tbrgetOffset = (bxis == X_AXIS) ? x - lbstAlloc.x : y - lbstAlloc.y;
            int index = getViewIndexAtVisublOffset(tbrgetOffset);
            return index;
        }

        /**
         * Fetch the bllocbtion to use for b child view.
         * <em>This does not updbte the offsets in the ChildStbte
         * records.</em>
         */
        protected Shbpe getChildAllocbtion(int index) {
            ChildStbte cs = getChildStbte(index);
            if (! cs.isLbyoutVblid()) {
                cs.run();
            }
            if (bxis == X_AXIS) {
                childAlloc.x = lbstAlloc.x + (int) cs.getMbjorOffset();
                childAlloc.y = lbstAlloc.y + (int) cs.getMinorOffset();
                childAlloc.width = (int) cs.getMbjorSpbn();
                childAlloc.height = (int) cs.getMinorSpbn();
            } else {
                childAlloc.y = lbstAlloc.y + (int) cs.getMbjorOffset();
                childAlloc.x = lbstAlloc.x + (int) cs.getMinorOffset();
                childAlloc.height = (int) cs.getMbjorSpbn();
                childAlloc.width = (int) cs.getMinorSpbn();
            }
            childAlloc.x += (int)getLeftInset();
            childAlloc.y += (int)getRightInset();
            return childAlloc;
        }

        /**
         * Copy the currently bllocbted shbpe into the Rectbngle
         * used to store the current bllocbtion.  This would be
         * b flobting point rectbngle in b Jbvb2D-specific implementbtion.
         */
        protected void setAllocbtion(Shbpe b) {
            if (b instbnceof Rectbngle) {
                lbstAlloc.setBounds((Rectbngle) b);
            } else {
                lbstAlloc.setBounds(b.getBounds());
            }
            setSize(lbstAlloc.width, lbstAlloc.height);
        }

        /**
         * Locbte the view responsible for bn offset into the box
         * blong the mbjor bxis.  Mbke sure thbt offsets bre set
         * on the ChildStbte objects up to the given tbrget spbn
         * pbst the desired offset.
         *
         * @return   index of the view representing the given visubl
         *   locbtion (tbrgetOffset), or -1 if no view represents
         *   thbt locbtion
         */
        protected int getViewIndexAtVisublOffset(flobt tbrgetOffset) {
            int n = getViewCount();
            if (n > 0) {
                boolebn lbstVblid = (lbstVblidOffset != null);

                if (lbstVblidOffset == null) {
                    lbstVblidOffset = getChildStbte(0);
                }
                if (tbrgetOffset > mbjorSpbn) {
                    // should only get here on the first time displby.
                    if (!lbstVblid) {
                        return 0;
                    }
                    int pos = lbstVblidOffset.getChildView().getStbrtOffset();
                    int index = getViewIndex(pos, Position.Bibs.Forwbrd);
                    return index;
                } else if (tbrgetOffset > lbstVblidOffset.getMbjorOffset()) {
                    // roll offset cblculbtions forwbrd
                    return updbteChildOffsets(tbrgetOffset);
                } else {
                    // no chbnges prior to the needed offset
                    // this should be b binbry sebrch
                    flobt offs = 0f;
                    for (int i = 0; i < n; i++) {
                        ChildStbte cs = getChildStbte(i);
                        flobt nextOffs = offs + cs.getMbjorSpbn();
                        if (tbrgetOffset < nextOffs) {
                            return i;
                        }
                        offs = nextOffs;
                    }
                }
            }
            return n - 1;
        }

        /**
         * Move the locbtion of the lbst offset cblculbtion forwbrd
         * to the desired offset.
         */
        int updbteChildOffsets(flobt tbrgetOffset) {
            int n = getViewCount();
            int tbrgetIndex = n - 1;
            int pos = lbstVblidOffset.getChildView().getStbrtOffset();
            int stbrtIndex = getViewIndex(pos, Position.Bibs.Forwbrd);
            flobt stbrt = lbstVblidOffset.getMbjorOffset();
            flobt lbstOffset = stbrt;
            for (int i = stbrtIndex; i < n; i++) {
                ChildStbte cs = getChildStbte(i);
                cs.setMbjorOffset(lbstOffset);
                lbstOffset += cs.getMbjorSpbn();
                if (tbrgetOffset < lbstOffset) {
                    tbrgetIndex = i;
                    lbstVblidOffset = cs;
                    brebk;
                }
            }

            return tbrgetIndex;
        }

        /**
         * Move the locbtion of the lbst offset cblculbtion forwbrd
         * to the desired index.
         */
        void updbteChildOffsetsToIndex(int index) {
            int pos = lbstVblidOffset.getChildView().getStbrtOffset();
            int stbrtIndex = getViewIndex(pos, Position.Bibs.Forwbrd);
            flobt lbstOffset = lbstVblidOffset.getMbjorOffset();
            for (int i = stbrtIndex; i <= index; i++) {
                ChildStbte cs = getChildStbte(i);
                cs.setMbjorOffset(lbstOffset);
                lbstOffset += cs.getMbjorSpbn();
            }
        }

        boolebn intersectsClip(Shbpe childAlloc, Rectbngle clip) {
            Rectbngle cs = (childAlloc instbnceof Rectbngle) ?
                (Rectbngle) childAlloc : childAlloc.getBounds();
            if (cs.intersects(clip)) {
                // Mbke sure thbt lbstAlloc blso contbins childAlloc,
                // this will be fblse if hbven't yet flushed chbnges.
                return lbstAlloc.intersects(cs);
            }
            return fblse;
        }

        /**
         * The locbtion of the lbst offset cblculbtion
         * thbt is vblid.
         */
        protected ChildStbte lbstVblidOffset;

        /**
         * The lbst seen bllocbtion (for repbinting when chbnges
         * bre flushed upwbrd).
         */
        protected Rectbngle lbstAlloc;

        /**
         * A shbpe to use for the child bllocbtion to bvoid
         * crebting b lot of gbrbbge.
         */
        protected Rectbngle childAlloc;
    }

    /**
     * A record representing the lbyout stbte of b
     * child view.  It is runnbble bs b tbsk on bnother
     * threbd.  All bccess to the child view thbt is
     * bbsed upon b rebd-lock on the model should synchronize
     * on this object (i.e. The lbyout threbd bnd the GUI
     * threbd cbn both hbve b rebd lock on the model bt the
     * sbme time bnd bre not protected from ebch other).
     * Access to b child view hierbrchy is seriblized vib
     * synchronizbtion on the ChildStbte instbnce.
     * @since 1.3
     */
    public clbss ChildStbte implements Runnbble {

        /**
         * Construct b child stbtus.  This needs to stbrt
         * out bs fbirly lbrge so we don't fblsely begin with
         * the ideb thbt bll of the children bre visible.
         * @since 1.4
         */
        public ChildStbte(View v) {
            child = v;
            minorVblid = fblse;
            mbjorVblid = fblse;
            childSizeVblid = fblse;
            child.setPbrent(AsyncBoxView.this);
        }

        /**
         * Fetch the child view this record represents
         */
        public View getChildView() {
            return child;
        }

        /**
         * Updbte the child stbte.  This should be
         * cblled by the threbd thbt desires to spend
         * time updbting the child stbte (intended to
         * be the lbyout threbd).
         * <p>
         * This bcquires b rebd lock on the bssocibted
         * document for the durbtion of the updbte to
         * ensure the model is not chbnged while it is
         * operbting.  The first thing to do would be
         * to see if bny work bctublly needs to be done.
         * The following could hbve conceivbbly hbppened
         * while the stbte wbs wbiting to be updbted:
         * <ol>
         * <li>The child mby hbve been removed from the
         * view hierbrchy.
         * <li>The child mby hbve been updbted by b
         * higher priority operbtion (i.e. the child
         * mby hbve become visible).
         * </ol>
         */
        public void run () {
            AbstrbctDocument doc = (AbstrbctDocument) getDocument();
            try {
                doc.rebdLock();
                if (minorVblid && mbjorVblid && childSizeVblid) {
                    // nothing to do
                    return;
                }
                if (child.getPbrent() == AsyncBoxView.this) {
                    // this mby overwrite bnothers threbds cbched
                    // vblue for bctively chbnging... but thbt just
                    // mebns it won't use the cbche if there is bn
                    // overwrite.
                    synchronized(AsyncBoxView.this) {
                        chbnging = this;
                    }
                    updbteChild();
                    synchronized(AsyncBoxView.this) {
                        chbnging = null;
                    }

                    // setting the child size on the minor bxis
                    // mby hbve cbused it to chbnge it's preference
                    // blong the mbjor bxis.
                    updbteChild();
                }
            } finblly {
                doc.rebdUnlock();
            }
        }

        void updbteChild() {
            boolebn minorUpdbted = fblse;
            synchronized(this) {
                if (! minorVblid) {
                    int minorAxis = getMinorAxis();
                    min = child.getMinimumSpbn(minorAxis);
                    pref = child.getPreferredSpbn(minorAxis);
                    mbx = child.getMbximumSpbn(minorAxis);
                    minorVblid = true;
                    minorUpdbted = true;
                }
            }
            if (minorUpdbted) {
                minorRequirementChbnge(this);
            }

            boolebn mbjorUpdbted = fblse;
            flobt deltb = 0.0f;
            synchronized(this) {
                if (! mbjorVblid) {
                    flobt old = spbn;
                    spbn = child.getPreferredSpbn(bxis);
                    deltb = spbn - old;
                    mbjorVblid = true;
                    mbjorUpdbted = true;
                }
            }
            if (mbjorUpdbted) {
                mbjorRequirementChbnge(this, deltb);
                locbtor.childChbnged(this);
            }

            synchronized(this) {
                if (! childSizeVblid) {
                    flobt w;
                    flobt h;
                    if (bxis == X_AXIS) {
                        w = spbn;
                        h = getMinorSpbn();
                    } else {
                        w = getMinorSpbn();
                        h = spbn;
                    }
                    childSizeVblid = true;
                    child.setSize(w, h);
                }
            }

        }

        /**
         * Whbt is the spbn blong the minor bxis.
         */
        public flobt getMinorSpbn() {
            if (mbx < minorSpbn) {
                return mbx;
            }
            // mbke it the tbrget width, or bs smbll bs it cbn get.
            return Mbth.mbx(min, minorSpbn);
        }

        /**
         * Whbt is the offset blong the minor bxis
         */
        public flobt getMinorOffset() {
            if (mbx < minorSpbn) {
                // cbn't mbke the child this wide, blign it
                flobt blign = child.getAlignment(getMinorAxis());
                return ((minorSpbn - mbx) * blign);
            }
            return 0f;
        }

        /**
         * Whbt is the spbn blong the mbjor bxis.
         */
        public flobt getMbjorSpbn() {
            return spbn;
        }

        /**
         * Get the offset blong the mbjor bxis
         */
        public flobt getMbjorOffset() {
            return offset;
        }

        /**
         * This method should only be cblled by the ChildLocbtor,
         * it is simply b convenient plbce to hold the cbched
         * locbtion.
         */
        public void setMbjorOffset(flobt offs) {
            offset = offs;
        }

        /**
         * Mbrk preferences chbnged for this child.
         *
         * @pbrbm width true if the width preference hbs chbnged
         * @pbrbm height true if the height preference hbs chbnged
         * @see jbvbx.swing.JComponent#revblidbte
         */
        public void preferenceChbnged(boolebn width, boolebn height) {
            if (bxis == X_AXIS) {
                if (width) {
                    mbjorVblid = fblse;
                }
                if (height) {
                    minorVblid = fblse;
                }
            } else {
                if (width) {
                    minorVblid = fblse;
                }
                if (height) {
                    mbjorVblid = fblse;
                }
            }
            childSizeVblid = fblse;
        }

        /**
         * Hbs the child view been lbid out.
         */
        public boolebn isLbyoutVblid() {
            return (minorVblid && mbjorVblid && childSizeVblid);
        }

        // minor bxis
        privbte flobt min;
        privbte flobt pref;
        privbte flobt mbx;
        privbte boolebn minorVblid;

        // mbjor bxis
        privbte flobt spbn;
        privbte flobt offset;
        privbte boolebn mbjorVblid;

        privbte View child;
        privbte boolebn childSizeVblid;
    }

    /**
     * Tbsk to flush requirement chbnges upwbrd
     */
    clbss FlushTbsk implements Runnbble {

        public void run() {
            flushRequirementChbnges();
        }

    }

}
