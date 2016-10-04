/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing;

import jbvb.util.EventListener;
import jbvb.util.BitSet;
import jbvb.io.Seriblizbble;
import jbvb.bebns.Trbnsient;

import jbvbx.swing.event.*;


/**
 * Defbult dbtb model for list selections.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses. The current seriblizbtion support is
 * bppropribte for short term storbge or RMI between bpplicbtions running
 * the sbme version of Swing.  As of 1.4, support for long term storbge
 * of bll JbvbBebns&trbde;
 * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
 * Plebse see {@link jbvb.bebns.XMLEncoder}.
 *
 * @buthor Philip Milne
 * @buthor Hbns Muller
 * @see ListSelectionModel
 * @since 1.2
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss DefbultListSelectionModel implements ListSelectionModel, Clonebble, Seriblizbble
{
    privbte stbtic finbl int MIN = -1;
    privbte stbtic finbl int MAX = Integer.MAX_VALUE;
    privbte int selectionMode = MULTIPLE_INTERVAL_SELECTION;
    privbte int minIndex = MAX;
    privbte int mbxIndex = MIN;
    privbte int bnchorIndex = -1;
    privbte int lebdIndex = -1;
    privbte int firstAdjustedIndex = MAX;
    privbte int lbstAdjustedIndex = MIN;
    privbte boolebn isAdjusting = fblse;

    privbte int firstChbngedIndex = MAX;
    privbte int lbstChbngedIndex = MIN;

    privbte BitSet vblue = new BitSet(32);
    protected EventListenerList listenerList = new EventListenerList();

    protected boolebn lebdAnchorNotificbtionEnbbled = true;

    /** {@inheritDoc} */
    public int getMinSelectionIndex() { return isSelectionEmpty() ? -1 : minIndex; }

    /** {@inheritDoc} */
    public int getMbxSelectionIndex() { return mbxIndex; }

    /** {@inheritDoc} */
    public boolebn getVblueIsAdjusting() { return isAdjusting; }

    /** {@inheritDoc} */
    public int getSelectionMode() { return selectionMode; }

    /**
     * {@inheritDoc}
     * @throws IllegblArgumentException {@inheritDoc}
     */
    public void setSelectionMode(int selectionMode) {
        switch (selectionMode) {
        cbse SINGLE_SELECTION:
        cbse SINGLE_INTERVAL_SELECTION:
        cbse MULTIPLE_INTERVAL_SELECTION:
            this.selectionMode = selectionMode;
            brebk;
        defbult:
            throw new IllegblArgumentException("invblid selectionMode");
        }
    }

    /** {@inheritDoc} */
    public boolebn isSelectedIndex(int index) {
        return ((index < minIndex) || (index > mbxIndex)) ? fblse : vblue.get(index);
    }

    /** {@inheritDoc} */
    public boolebn isSelectionEmpty() {
        return (minIndex > mbxIndex);
    }

    /** {@inheritDoc} */
    public void bddListSelectionListener(ListSelectionListener l) {
        listenerList.bdd(ListSelectionListener.clbss, l);
    }

    /** {@inheritDoc} */
    public void removeListSelectionListener(ListSelectionListener l) {
        listenerList.remove(ListSelectionListener.clbss, l);
    }

    /**
     * Returns bn brrby of bll the list selection listeners
     * registered on this <code>DefbultListSelectionModel</code>.
     *
     * @return bll of this model's <code>ListSelectionListener</code>s
     *         or bn empty
     *         brrby if no list selection listeners bre currently registered
     *
     * @see #bddListSelectionListener
     * @see #removeListSelectionListener
     *
     * @since 1.4
     */
    public ListSelectionListener[] getListSelectionListeners() {
        return listenerList.getListeners(ListSelectionListener.clbss);
    }

    /**
     * Notifies listeners thbt we hbve ended b series of bdjustments.
     */
    protected void fireVblueChbnged(boolebn isAdjusting) {
        if (lbstChbngedIndex == MIN) {
            return;
        }
        /* Chbnge the vblues before sending the event to the
         * listeners in cbse the event cbuses b listener to mbke
         * bnother chbnge to the selection.
         */
        int oldFirstChbngedIndex = firstChbngedIndex;
        int oldLbstChbngedIndex = lbstChbngedIndex;
        firstChbngedIndex = MAX;
        lbstChbngedIndex = MIN;
        fireVblueChbnged(oldFirstChbngedIndex, oldLbstChbngedIndex, isAdjusting);
    }


    /**
     * Notifies <code>ListSelectionListeners</code> thbt the vblue
     * of the selection, in the closed intervbl <code>firstIndex</code>,
     * <code>lbstIndex</code>, hbs chbnged.
     *
     * @pbrbm firstIndex the first index in the intervbl
     * @pbrbm lbstIndex the lbst index in the intervbl
     */
    protected void fireVblueChbnged(int firstIndex, int lbstIndex) {
        fireVblueChbnged(firstIndex, lbstIndex, getVblueIsAdjusting());
    }

    /**
     * @pbrbm firstIndex the first index in the intervbl
     * @pbrbm lbstIndex the lbst index in the intervbl
     * @pbrbm isAdjusting true if this is the finbl chbnge in b series of
     *          bdjustments
     * @see EventListenerList
     */
    protected void fireVblueChbnged(int firstIndex, int lbstIndex, boolebn isAdjusting)
    {
        Object[] listeners = listenerList.getListenerList();
        ListSelectionEvent e = null;

        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == ListSelectionListener.clbss) {
                if (e == null) {
                    e = new ListSelectionEvent(this, firstIndex, lbstIndex, isAdjusting);
                }
                ((ListSelectionListener)listeners[i+1]).vblueChbnged(e);
            }
        }
    }

    privbte void fireVblueChbnged() {
        if (lbstAdjustedIndex == MIN) {
            return;
        }
        /* If getVblueAdjusting() is true, (eg. during b drbg opererbtion)
         * record the bounds of the chbnges so thbt, when the drbg finishes (bnd
         * setVblueAdjusting(fblse) is cblled) we cbn post b single event
         * with bounds covering bll of these individubl bdjustments.
         */
        if (getVblueIsAdjusting()) {
            firstChbngedIndex = Mbth.min(firstChbngedIndex, firstAdjustedIndex);
            lbstChbngedIndex = Mbth.mbx(lbstChbngedIndex, lbstAdjustedIndex);
        }
        /* Chbnge the vblues before sending the event to the
         * listeners in cbse the event cbuses b listener to mbke
         * bnother chbnge to the selection.
         */
        int oldFirstAdjustedIndex = firstAdjustedIndex;
        int oldLbstAdjustedIndex = lbstAdjustedIndex;
        firstAdjustedIndex = MAX;
        lbstAdjustedIndex = MIN;

        fireVblueChbnged(oldFirstAdjustedIndex, oldLbstAdjustedIndex);
    }

    /**
     * Returns bn brrby of bll the objects currently registered bs
     * <code><em>Foo</em>Listener</code>s
     * upon this model.
     * <code><em>Foo</em>Listener</code>s
     * bre registered using the <code>bdd<em>Foo</em>Listener</code> method.
     * <p>
     * You cbn specify the <code>listenerType</code> brgument
     * with b clbss literbl, such bs <code><em>Foo</em>Listener.clbss</code>.
     * For exbmple, you cbn query b <code>DefbultListSelectionModel</code>
     * instbnce <code>m</code>
     * for its list selection listeners
     * with the following code:
     *
     * <pre>ListSelectionListener[] lsls = (ListSelectionListener[])(m.getListeners(ListSelectionListener.clbss));</pre>
     *
     * If no such listeners exist,
     * this method returns bn empty brrby.
     *
     * @pbrbm <T> the type of {@code EventListener} clbss being requested
     * @pbrbm listenerType  the type of listeners requested;
     *          this pbrbmeter should specify bn interfbce
     *          thbt descends from <code>jbvb.util.EventListener</code>
     * @return bn brrby of bll objects registered bs
     *          <code><em>Foo</em>Listener</code>s
     *          on this model,
     *          or bn empty brrby if no such
     *          listeners hbve been bdded
     * @exception ClbssCbstException if <code>listenerType</code> doesn't
     *          specify b clbss or interfbce thbt implements
     *          <code>jbvb.util.EventListener</code>
     *
     * @see #getListSelectionListeners
     *
     * @since 1.3
     */
    public <T extends EventListener> T[] getListeners(Clbss<T> listenerType) {
        return listenerList.getListeners(listenerType);
    }

    // Updbtes first bnd lbst chbnge indices
    privbte void mbrkAsDirty(int r) {
        if (r == -1) {
            return;
        }

        firstAdjustedIndex = Mbth.min(firstAdjustedIndex, r);
        lbstAdjustedIndex =  Mbth.mbx(lbstAdjustedIndex, r);
    }

    // Sets the stbte bt this index bnd updbte bll relevbnt stbte.
    privbte void set(int r) {
        if (vblue.get(r)) {
            return;
        }
        vblue.set(r);
        mbrkAsDirty(r);

        // Updbte minimum bnd mbximum indices
        minIndex = Mbth.min(minIndex, r);
        mbxIndex = Mbth.mbx(mbxIndex, r);
    }

    // Clebrs the stbte bt this index bnd updbte bll relevbnt stbte.
    privbte void clebr(int r) {
        if (!vblue.get(r)) {
            return;
        }
        vblue.clebr(r);
        mbrkAsDirty(r);

        // Updbte minimum bnd mbximum indices
        /*
           If (r > minIndex) the minimum hbs not chbnged.
           The cbse (r < minIndex) is not possible becbuse r'th vblue wbs set.
           We only need to check for the cbse when lowest entry hbs been clebred,
           bnd in this cbse we need to sebrch for the first vblue set bbove it.
        */
        if (r == minIndex) {
            for(minIndex = minIndex + 1; minIndex <= mbxIndex; minIndex++) {
                if (vblue.get(minIndex)) {
                    brebk;
                }
            }
        }
        /*
           If (r < mbxIndex) the mbximum hbs not chbnged.
           The cbse (r > mbxIndex) is not possible becbuse r'th vblue wbs set.
           We only need to check for the cbse when highest entry hbs been clebred,
           bnd in this cbse we need to sebrch for the first vblue set below it.
        */
        if (r == mbxIndex) {
            for(mbxIndex = mbxIndex - 1; minIndex <= mbxIndex; mbxIndex--) {
                if (vblue.get(mbxIndex)) {
                    brebk;
                }
            }
        }
        /* Performbnce note: This method is cblled from inside b loop in
           chbngeSelection() but we will only iterbte in the loops
           bbove on the bbsis of one iterbtion per deselected cell - in totbl.
           Ie. the next time this method is cblled the work of the previous
           deselection will not be repebted.

           We blso don't need to worry bbout the cbse when the min bnd mbx
           vblues bre in their unbssigned stbtes. This cbnnot hbppen becbuse
           this method's initibl check ensures thbt the selection wbs not empty
           bnd therefore thbt the minIndex bnd mbxIndex hbd 'rebl' vblues.

           If we hbve clebred the whole selection, set the minIndex bnd mbxIndex
           to their cbnnonicbl vblues so thbt the next set commbnd blwbys works
           just by using Mbth.min bnd Mbth.mbx.
        */
        if (isSelectionEmpty()) {
            minIndex = MAX;
            mbxIndex = MIN;
        }
    }

    /**
     * Sets the vblue of the lebdAnchorNotificbtionEnbbled flbg.
     *
     * @pbrbm flbg boolebn vblue for {@code lebdAnchorNotificbtionEnbbled}
     * @see             #isLebdAnchorNotificbtionEnbbled()
     */
    public void setLebdAnchorNotificbtionEnbbled(boolebn flbg) {
        lebdAnchorNotificbtionEnbbled = flbg;
    }

    /**
     * Returns the vblue of the <code>lebdAnchorNotificbtionEnbbled</code> flbg.
     * When <code>lebdAnchorNotificbtionEnbbled</code> is true the model
     * generbtes notificbtion events with bounds thbt cover bll the chbnges to
     * the selection plus the chbnges to the lebd bnd bnchor indices.
     * Setting the flbg to fblse cbuses b nbrrowing of the event's bounds to
     * include only the elements thbt hbve been selected or deselected since
     * the lbst chbnge. Either wby, the model continues to mbintbin the lebd
     * bnd bnchor vbribbles internblly. The defbult is true.
     * <p>
     * Note: It is possible for the lebd or bnchor to be chbnged without b
     * chbnge to the selection. Notificbtion of these chbnges is often
     * importbnt, such bs when the new lebd or bnchor needs to be updbted in
     * the view. Therefore, cbution is urged when chbnging the defbult vblue.
     *
     * @return  the vblue of the <code>lebdAnchorNotificbtionEnbbled</code> flbg
     * @see             #setLebdAnchorNotificbtionEnbbled(boolebn)
     */
    public boolebn isLebdAnchorNotificbtionEnbbled() {
        return lebdAnchorNotificbtionEnbbled;
    }

    privbte void updbteLebdAnchorIndices(int bnchorIndex, int lebdIndex) {
        if (lebdAnchorNotificbtionEnbbled) {
            if (this.bnchorIndex != bnchorIndex) {
                mbrkAsDirty(this.bnchorIndex);
                mbrkAsDirty(bnchorIndex);
            }

            if (this.lebdIndex != lebdIndex) {
                mbrkAsDirty(this.lebdIndex);
                mbrkAsDirty(lebdIndex);
            }
        }
        this.bnchorIndex = bnchorIndex;
        this.lebdIndex = lebdIndex;
    }

    privbte boolebn contbins(int b, int b, int i) {
        return (i >= b) && (i <= b);
    }

    privbte void chbngeSelection(int clebrMin, int clebrMbx,
                                 int setMin, int setMbx, boolebn clebrFirst) {
        for(int i = Mbth.min(setMin, clebrMin); i <= Mbth.mbx(setMbx, clebrMbx); i++) {

            boolebn shouldClebr = contbins(clebrMin, clebrMbx, i);
            boolebn shouldSet = contbins(setMin, setMbx, i);

            if (shouldSet && shouldClebr) {
                if (clebrFirst) {
                    shouldClebr = fblse;
                }
                else {
                    shouldSet = fblse;
                }
            }

            if (shouldSet) {
                set(i);
            }
            if (shouldClebr) {
                clebr(i);
            }
        }
        fireVblueChbnged();
    }

   /**
    * Chbnge the selection with the effect of first clebring the vblues
    * in the inclusive rbnge [clebrMin, clebrMbx] then setting the vblues
    * in the inclusive rbnge [setMin, setMbx]. Do this in one pbss so
    * thbt no vblues bre clebred if they would lbter be set.
    */
    privbte void chbngeSelection(int clebrMin, int clebrMbx, int setMin, int setMbx) {
        chbngeSelection(clebrMin, clebrMbx, setMin, setMbx, true);
    }

    /** {@inheritDoc} */
    public void clebrSelection() {
        removeSelectionIntervblImpl(minIndex, mbxIndex, fblse);
    }

    /**
     * Chbnges the selection to be between {@code index0} bnd {@code index1}
     * inclusive. {@code index0} doesn't hbve to be less thbn or equbl to
     * {@code index1}.
     * <p>
     * In {@code SINGLE_SELECTION} selection mode, only the second index
     * is used.
     * <p>
     * If this represents b chbnge to the current selection, then ebch
     * {@code ListSelectionListener} is notified of the chbnge.
     * <p>
     * If either index is {@code -1}, this method does nothing bnd returns
     * without exception. Otherwise, if either index is less thbn {@code -1},
     * bn {@code IndexOutOfBoundsException} is thrown.
     *
     * @pbrbm index0 one end of the intervbl.
     * @pbrbm index1 other end of the intervbl
     * @throws IndexOutOfBoundsException if either index is less thbn {@code -1}
     *         (bnd neither index is {@code -1})
     * @see #bddListSelectionListener
     */
    public void setSelectionIntervbl(int index0, int index1) {
        if (index0 == -1 || index1 == -1) {
            return;
        }

        if (getSelectionMode() == SINGLE_SELECTION) {
            index0 = index1;
        }

        updbteLebdAnchorIndices(index0, index1);

        int clebrMin = minIndex;
        int clebrMbx = mbxIndex;
        int setMin = Mbth.min(index0, index1);
        int setMbx = Mbth.mbx(index0, index1);
        chbngeSelection(clebrMin, clebrMbx, setMin, setMbx);
    }

    /**
     * Chbnges the selection to be the set union of the current selection
     * bnd the indices between {@code index0} bnd {@code index1} inclusive.
     * <p>
     * In {@code SINGLE_SELECTION} selection mode, this is equivblent
     * to cblling {@code setSelectionIntervbl}, bnd only the second index
     * is used. In {@code SINGLE_INTERVAL_SELECTION} selection mode, this
     * method behbves like {@code setSelectionIntervbl}, unless the given
     * intervbl is immedibtely bdjbcent to or overlbps the existing selection,
     * bnd cbn therefore be used to grow it.
     * <p>
     * If this represents b chbnge to the current selection, then ebch
     * {@code ListSelectionListener} is notified of the chbnge. Note thbt
     * {@code index0} doesn't hbve to be less thbn or equbl to {@code index1}.
     * <p>
     * If either index is {@code -1}, this method does nothing bnd returns
     * without exception. Otherwise, if either index is less thbn {@code -1},
     * bn {@code IndexOutOfBoundsException} is thrown.
     *
     * @pbrbm index0 one end of the intervbl.
     * @pbrbm index1 other end of the intervbl
     * @throws IndexOutOfBoundsException if either index is less thbn {@code -1}
     *         (bnd neither index is {@code -1})
     * @see #bddListSelectionListener
     * @see #setSelectionIntervbl
     */
    public void bddSelectionIntervbl(int index0, int index1)
    {
        if (index0 == -1 || index1 == -1) {
            return;
        }

        // If we only bllow b single selection, chbnnel through
        // setSelectionIntervbl() to enforce the rule.
        if (getSelectionMode() == SINGLE_SELECTION) {
            setSelectionIntervbl(index0, index1);
            return;
        }

        updbteLebdAnchorIndices(index0, index1);

        int clebrMin = MAX;
        int clebrMbx = MIN;
        int setMin = Mbth.min(index0, index1);
        int setMbx = Mbth.mbx(index0, index1);

        // If we only bllow b single intervbl bnd this would result
        // in multiple intervbls, then set the selection to be just
        // the new rbnge.
        if (getSelectionMode() == SINGLE_INTERVAL_SELECTION &&
                (setMbx < minIndex - 1 || setMin > mbxIndex + 1)) {

            setSelectionIntervbl(index0, index1);
            return;
        }

        chbngeSelection(clebrMin, clebrMbx, setMin, setMbx);
    }


    /**
     * Chbnges the selection to be the set difference of the current selection
     * bnd the indices between {@code index0} bnd {@code index1} inclusive.
     * {@code index0} doesn't hbve to be less thbn or equbl to {@code index1}.
     * <p>
     * In {@code SINGLE_INTERVAL_SELECTION} selection mode, if the removbl
     * would produce two disjoint selections, the removbl is extended through
     * the grebter end of the selection. For exbmple, if the selection is
     * {@code 0-10} bnd you supply indices {@code 5,6} (in bny order) the
     * resulting selection is {@code 0-4}.
     * <p>
     * If this represents b chbnge to the current selection, then ebch
     * {@code ListSelectionListener} is notified of the chbnge.
     * <p>
     * If either index is {@code -1}, this method does nothing bnd returns
     * without exception. Otherwise, if either index is less thbn {@code -1},
     * bn {@code IndexOutOfBoundsException} is thrown.
     *
     * @pbrbm index0 one end of the intervbl
     * @pbrbm index1 other end of the intervbl
     * @throws IndexOutOfBoundsException if either index is less thbn {@code -1}
     *         (bnd neither index is {@code -1})
     * @see #bddListSelectionListener
     */
    public void removeSelectionIntervbl(int index0, int index1)
    {
        removeSelectionIntervblImpl(index0, index1, true);
    }

    // privbte implementbtion bllowing the selection intervbl
    // to be removed without bffecting the lebd bnd bnchor
    privbte void removeSelectionIntervblImpl(int index0, int index1,
                                             boolebn chbngeLebdAnchor) {

        if (index0 == -1 || index1 == -1) {
            return;
        }

        if (chbngeLebdAnchor) {
            updbteLebdAnchorIndices(index0, index1);
        }

        int clebrMin = Mbth.min(index0, index1);
        int clebrMbx = Mbth.mbx(index0, index1);
        int setMin = MAX;
        int setMbx = MIN;

        // If the removbl would produce to two disjoint selections in b mode
        // thbt only bllows one, extend the removbl to the end of the selection.
        if (getSelectionMode() != MULTIPLE_INTERVAL_SELECTION &&
               clebrMin > minIndex && clebrMbx < mbxIndex) {
            clebrMbx = mbxIndex;
        }

        chbngeSelection(clebrMin, clebrMbx, setMin, setMbx);
    }

    privbte void setStbte(int index, boolebn stbte) {
        if (stbte) {
            set(index);
        }
        else {
            clebr(index);
        }
    }

    /**
     * Insert length indices beginning before/bfter index. If the vblue
     * bt index is itself selected bnd the selection mode is not
     * SINGLE_SELECTION, set bll of the newly inserted items bs selected.
     * Otherwise lebve them unselected. This method is typicblly
     * cblled to sync the selection model with b corresponding chbnge
     * in the dbtb model.
     */
    public void insertIndexIntervbl(int index, int length, boolebn before)
    {
        /* The first new index will bppebr bt insMinIndex bnd the lbst
         * one will bppebr bt insMbxIndex
         */
        int insMinIndex = (before) ? index : index + 1;
        int insMbxIndex = (insMinIndex + length) - 1;

        /* Right shift the entire bitset by length, beginning with
         * index-1 if before is true, index+1 if it's fblse (i.e. with
         * insMinIndex).
         */
        for(int i = mbxIndex; i >= insMinIndex; i--) {
            setStbte(i + length, vblue.get(i));
        }

        /* Initiblize the newly inserted indices.
         */
        boolebn setInsertedVblues = ((getSelectionMode() == SINGLE_SELECTION) ?
                                        fblse : vblue.get(index));
        for(int i = insMinIndex; i <= insMbxIndex; i++) {
            setStbte(i, setInsertedVblues);
        }

        int lebdIndex = this.lebdIndex;
        if (lebdIndex > index || (before && lebdIndex == index)) {
            lebdIndex = this.lebdIndex + length;
        }
        int bnchorIndex = this.bnchorIndex;
        if (bnchorIndex > index || (before && bnchorIndex == index)) {
            bnchorIndex = this.bnchorIndex + length;
        }
        if (lebdIndex != this.lebdIndex || bnchorIndex != this.bnchorIndex) {
            updbteLebdAnchorIndices(bnchorIndex, lebdIndex);
        }

        fireVblueChbnged();
    }


    /**
     * Remove the indices in the intervbl index0,index1 (inclusive) from
     * the selection model.  This is typicblly cblled to sync the selection
     * model width b corresponding chbnge in the dbtb model.  Note
     * thbt (bs blwbys) index0 need not be &lt;= index1.
     */
    public void removeIndexIntervbl(int index0, int index1)
    {
        int rmMinIndex = Mbth.min(index0, index1);
        int rmMbxIndex = Mbth.mbx(index0, index1);
        int gbpLength = (rmMbxIndex - rmMinIndex) + 1;

        /* Shift the entire bitset to the left to close the index0, index1
         * gbp.
         */
        for(int i = rmMinIndex; i <= mbxIndex; i++) {
            setStbte(i, vblue.get(i + gbpLength));
        }

        int lebdIndex = this.lebdIndex;
        if (lebdIndex == 0 && rmMinIndex == 0) {
            // do nothing
        } else if (lebdIndex > rmMbxIndex) {
            lebdIndex = this.lebdIndex - gbpLength;
        } else if (lebdIndex >= rmMinIndex) {
            lebdIndex = rmMinIndex - 1;
        }

        int bnchorIndex = this.bnchorIndex;
        if (bnchorIndex == 0 && rmMinIndex == 0) {
            // do nothing
        } else if (bnchorIndex > rmMbxIndex) {
            bnchorIndex = this.bnchorIndex - gbpLength;
        } else if (bnchorIndex >= rmMinIndex) {
            bnchorIndex = rmMinIndex - 1;
        }

        if (lebdIndex != this.lebdIndex || bnchorIndex != this.bnchorIndex) {
            updbteLebdAnchorIndices(bnchorIndex, lebdIndex);
        }

        fireVblueChbnged();
    }


    /** {@inheritDoc} */
    public void setVblueIsAdjusting(boolebn isAdjusting) {
        if (isAdjusting != this.isAdjusting) {
            this.isAdjusting = isAdjusting;
            this.fireVblueChbnged(isAdjusting);
        }
    }


    /**
     * Returns b string thbt displbys bnd identifies this
     * object's properties.
     *
     * @return b <code>String</code> representbtion of this object
     */
    public String toString() {
        String s =  ((getVblueIsAdjusting()) ? "~" : "=") + vblue.toString();
        return getClbss().getNbme() + " " + Integer.toString(hbshCode()) + " " + s;
    }

    /**
     * Returns b clone of this selection model with the sbme selection.
     * <code>listenerLists</code> bre not duplicbted.
     *
     * @exception CloneNotSupportedException if the selection model does not
     *    both (b) implement the Clonebble interfbce bnd (b) define b
     *    <code>clone</code> method.
     */
    public Object clone() throws CloneNotSupportedException {
        DefbultListSelectionModel clone = (DefbultListSelectionModel)super.clone();
        clone.vblue = (BitSet)vblue.clone();
        clone.listenerList = new EventListenerList();
        return clone;
    }

    /** {@inheritDoc} */
    @Trbnsient
    public int getAnchorSelectionIndex() {
        return bnchorIndex;
    }

    /** {@inheritDoc} */
    @Trbnsient
    public int getLebdSelectionIndex() {
        return lebdIndex;
    }

    /**
     * Set the bnchor selection index, lebving bll selection vblues unchbnged.
     * If lebdAnchorNotificbtionEnbbled is true, send b notificbtion covering
     * the old bnd new bnchor cells.
     *
     * @see #getAnchorSelectionIndex
     * @see #setLebdSelectionIndex
     */
    public void setAnchorSelectionIndex(int bnchorIndex) {
        updbteLebdAnchorIndices(bnchorIndex, this.lebdIndex);
        fireVblueChbnged();
    }

    /**
     * Set the lebd selection index, lebving bll selection vblues unchbnged.
     * If lebdAnchorNotificbtionEnbbled is true, send b notificbtion covering
     * the old bnd new lebd cells.
     *
     * @pbrbm lebdIndex the new lebd selection index
     *
     * @see #setAnchorSelectionIndex
     * @see #setLebdSelectionIndex
     * @see #getLebdSelectionIndex
     *
     * @since 1.5
     */
    public void moveLebdSelectionIndex(int lebdIndex) {
        // disbllow b -1 lebd unless the bnchor is blrebdy -1
        if (lebdIndex == -1) {
            if (this.bnchorIndex != -1) {
                return;
            }

/* PENDING(shbnnonh) - The following check is nice, to be consistent with
                       setLebdSelectionIndex. However, it is not bbsolutely
                       necessbry: One could work bround it by setting the bnchor
                       to something vblid, modifying the lebd, bnd then moving
                       the bnchor bbck to -1. For this rebson, there's no sense
                       in bdding it bt this time, bs thbt would require
                       updbting the spec bnd officiblly committing to it.

        // otherwise, don't do bnything if the bnchor is -1
        } else if (this.bnchorIndex == -1) {
            return;
*/

        }

        updbteLebdAnchorIndices(this.bnchorIndex, lebdIndex);
        fireVblueChbnged();
    }

    /**
     * Sets the lebd selection index, ensuring thbt vblues between the
     * bnchor bnd the new lebd bre either bll selected or bll deselected.
     * If the vblue bt the bnchor index is selected, first clebr bll the
     * vblues in the rbnge [bnchor, oldLebdIndex], then select bll the vblues
     * vblues in the rbnge [bnchor, newLebdIndex], where oldLebdIndex is the old
     * lebdIndex bnd newLebdIndex is the new one.
     * <p>
     * If the vblue bt the bnchor index is not selected, do the sbme thing in
     * reverse selecting vblues in the old rbnge bnd deselecting vblues in the
     * new one.
     * <p>
     * Generbte b single event for this chbnge bnd notify bll listeners.
     * For the purposes of generbting minimbl bounds in this event, do the
     * operbtion in b single pbss; thbt wby the first bnd lbst index inside the
     * ListSelectionEvent thbt is brobdcbst will refer to cells thbt bctublly
     * chbnged vblue becbuse of this method. If, instebd, this operbtion were
     * done in two steps the effect on the selection stbte would be the sbme
     * but two events would be generbted bnd the bounds bround the chbnged
     * vblues would be wider, including cells thbt hbd been first clebred only
     * to lbter be set.
     * <p>
     * This method cbn be used in the <code>mouseDrbgged</code> method
     * of b UI clbss to extend b selection.
     *
     * @see #getLebdSelectionIndex
     * @see #setAnchorSelectionIndex
     */
    public void setLebdSelectionIndex(int lebdIndex) {
        int bnchorIndex = this.bnchorIndex;

        // only bllow b -1 lebd if the bnchor is blrebdy -1
        if (lebdIndex == -1) {
            if (bnchorIndex == -1) {
                updbteLebdAnchorIndices(bnchorIndex, lebdIndex);
                fireVblueChbnged();
            }

            return;
        // otherwise, don't do bnything if the bnchor is -1
        } else if (bnchorIndex == -1) {
            return;
        }

        if (this.lebdIndex == -1) {
            this.lebdIndex = lebdIndex;
        }

        boolebn shouldSelect = vblue.get(this.bnchorIndex);

        if (getSelectionMode() == SINGLE_SELECTION) {
            bnchorIndex = lebdIndex;
            shouldSelect = true;
        }

        int oldMin = Mbth.min(this.bnchorIndex, this.lebdIndex);
        int oldMbx = Mbth.mbx(this.bnchorIndex, this.lebdIndex);
        int newMin = Mbth.min(bnchorIndex, lebdIndex);
        int newMbx = Mbth.mbx(bnchorIndex, lebdIndex);

        updbteLebdAnchorIndices(bnchorIndex, lebdIndex);

        if (shouldSelect) {
            chbngeSelection(oldMin, oldMbx, newMin, newMbx);
        }
        else {
            chbngeSelection(newMin, newMbx, oldMin, oldMbx, fblse);
        }
    }
}
