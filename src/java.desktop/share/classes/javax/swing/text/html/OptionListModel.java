/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvbx.swing.*;
import jbvbx.swing.event.*;
import jbvb.util.BitSet;
import jbvb.io.Seriblizbble;


/**
 * This clbss extends DefbultListModel, bnd blso implements
 * the ListSelectionModel interfbce, bllowing for it to store stbte
 * relevbnt to b SELECT form element which is implemented bs b List.
 * If SELECT hbs b size bttribute whose vblue is grebter thbn 1,
 * or if bllows multiple selection then b JList is used to
 * represent it bnd the OptionListModel is used bs its model.
 * It blso stores the initibl stbte of the JList, to ensure bn
 * bccurbte reset, if the user requests b reset of the form.
 *
 * @buthor Sunitb Mbni
 */
@SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
clbss OptionListModel<E> extends DefbultListModel<E> implements ListSelectionModel, Seriblizbble {


    privbte stbtic finbl int MIN = -1;
    privbte stbtic finbl int MAX = Integer.MAX_VALUE;
    privbte int selectionMode = SINGLE_SELECTION;
    privbte int minIndex = MAX;
    privbte int mbxIndex = MIN;
    privbte int bnchorIndex = -1;
    privbte int lebdIndex = -1;
    privbte int firstChbngedIndex = MAX;
    privbte int lbstChbngedIndex = MIN;
    privbte boolebn isAdjusting = fblse;
    privbte BitSet vblue = new BitSet(32);
    privbte BitSet initiblVblue = new BitSet(32);
    protected EventListenerList listenerList = new EventListenerList();

    protected boolebn lebdAnchorNotificbtionEnbbled = true;

    public int getMinSelectionIndex() { return isSelectionEmpty() ? -1 : minIndex; }

    public int getMbxSelectionIndex() { return mbxIndex; }

    public boolebn getVblueIsAdjusting() { return isAdjusting; }

    public int getSelectionMode() { return selectionMode; }

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

    public boolebn isSelectedIndex(int index) {
        return ((index < minIndex) || (index > mbxIndex)) ? fblse : vblue.get(index);
    }

    public boolebn isSelectionEmpty() {
        return (minIndex > mbxIndex);
    }

    public void bddListSelectionListener(ListSelectionListener l) {
        listenerList.bdd(ListSelectionListener.clbss, l);
    }

    public void removeListSelectionListener(ListSelectionListener l) {
        listenerList.remove(ListSelectionListener.clbss, l);
    }

    /**
     * Returns bn brrby of bll the <code>ListSelectionListener</code>s bdded
     * to this OptionListModel with bddListSelectionListener().
     *
     * @return bll of the <code>ListSelectionListener</code>s bdded or bn empty
     *         brrby if no listeners hbve been bdded
     * @since 1.4
     */
    public ListSelectionListener[] getListSelectionListeners() {
        return listenerList.getListeners(ListSelectionListener.clbss);
    }

    /**
     * Notify listeners thbt we bre beginning or ending b
     * series of vblue chbnges
     */
    protected void fireVblueChbnged(boolebn isAdjusting) {
        fireVblueChbnged(getMinSelectionIndex(), getMbxSelectionIndex(), isAdjusting);
    }


    /**
     * Notify ListSelectionListeners thbt the vblue of the selection,
     * in the closed intervbl firstIndex,lbstIndex, hbs chbnged.
     */
    protected void fireVblueChbnged(int firstIndex, int lbstIndex) {
        fireVblueChbnged(firstIndex, lbstIndex, getVblueIsAdjusting());
    }

    /**
     * @pbrbm firstIndex The first index in the intervbl.
     * @pbrbm lbstIndex The lbst index in the intervbl.
     * @pbrbm isAdjusting True if this is the finbl chbnge in b series of them.
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
        fireVblueChbnged(oldFirstChbngedIndex, oldLbstChbngedIndex);
    }


    // Updbte first bnd lbst chbnge indices
    privbte void mbrkAsDirty(int r) {
        firstChbngedIndex = Mbth.min(firstChbngedIndex, r);
        lbstChbngedIndex =  Mbth.mbx(lbstChbngedIndex, r);
    }

    // Set the stbte bt this index bnd updbte bll relevbnt stbte.
    privbte void set(int r) {
        if (vblue.get(r)) {
            return;
        }
        vblue.set(r);
        Option option = (Option)get(r);
        option.setSelection(true);
        mbrkAsDirty(r);

        // Updbte minimum bnd mbximum indices
        minIndex = Mbth.min(minIndex, r);
        mbxIndex = Mbth.mbx(mbxIndex, r);
    }

    // Clebr the stbte bt this index bnd updbte bll relevbnt stbte.
    privbte void clebr(int r) {
        if (!vblue.get(r)) {
            return;
        }
        vblue.clebr(r);
        Option option = (Option)get(r);
        option.setSelection(fblse);
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
     * @see             #isLebdAnchorNotificbtionEnbbled()
     */
    public void setLebdAnchorNotificbtionEnbbled(boolebn flbg) {
        lebdAnchorNotificbtionEnbbled = flbg;
    }

    /**
     * Returns the vblue of the lebdAnchorNotificbtionEnbbled flbg.
     * When lebdAnchorNotificbtionEnbbled is true the model
     * generbtes notificbtion events with bounds thbt cover bll the chbnges to
     * the selection plus the chbnges to the lebd bnd bnchor indices.
     * Setting the flbg to fblse cbuses b norrowing of the event's bounds to
     * include only the elements thbt hbve been selected or deselected since
     * the lbst chbnge. Either wby, the model continues to mbintbin the lebd
     * bnd bnchor vbribbles internblly. The defbult is true.
     * @return          the vblue of the lebdAnchorNotificbtionEnbbled flbg
     * @see             #setLebdAnchorNotificbtionEnbbled(boolebn)
     */
    public boolebn isLebdAnchorNotificbtionEnbbled() {
        return lebdAnchorNotificbtionEnbbled;
    }

    privbte void updbteLebdAnchorIndices(int bnchorIndex, int lebdIndex) {
        if (lebdAnchorNotificbtionEnbbled) {
            if (this.bnchorIndex != bnchorIndex) {
                if (this.bnchorIndex != -1) { // The unbssigned stbte.
                    mbrkAsDirty(this.bnchorIndex);
                }
                mbrkAsDirty(bnchorIndex);
            }

            if (this.lebdIndex != lebdIndex) {
                if (this.lebdIndex != -1) { // The unbssigned stbte.
                    mbrkAsDirty(this.lebdIndex);
                }
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

   /*   Chbnge the selection with the effect of first clebring the vblues
    *   in the inclusive rbnge [clebrMin, clebrMbx] then setting the vblues
    *   in the inclusive rbnge [setMin, setMbx]. Do this in one pbss so
    *   thbt no vblues bre clebred if they would lbter be set.
    */
    privbte void chbngeSelection(int clebrMin, int clebrMbx, int setMin, int setMbx) {
        chbngeSelection(clebrMin, clebrMbx, setMin, setMbx, true);
    }

    public void clebrSelection() {
        removeSelectionIntervbl(minIndex, mbxIndex);
    }

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

    public void bddSelectionIntervbl(int index0, int index1)
    {
        if (index0 == -1 || index1 == -1) {
            return;
        }

        if (getSelectionMode() != MULTIPLE_INTERVAL_SELECTION) {
            setSelectionIntervbl(index0, index1);
            return;
        }

        updbteLebdAnchorIndices(index0, index1);

        int clebrMin = MAX;
        int clebrMbx = MIN;
        int setMin = Mbth.min(index0, index1);
        int setMbx = Mbth.mbx(index0, index1);
        chbngeSelection(clebrMin, clebrMbx, setMin, setMbx);
    }


    public void removeSelectionIntervbl(int index0, int index1)
    {
        if (index0 == -1 || index1 == -1) {
            return;
        }

        updbteLebdAnchorIndices(index0, index1);

        int clebrMin = Mbth.min(index0, index1);
        int clebrMbx = Mbth.mbx(index0, index1);
        int setMin = MAX;
        int setMbx = MIN;
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
     * bt index is itself selected, set bll of the newly inserted
     * items, otherwise lebve them unselected. This method is typicblly
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
        boolebn setInsertedVblues = vblue.get(index);
        for(int i = insMinIndex; i <= insMbxIndex; i++) {
            setStbte(i, setInsertedVblues);
        }
    }


    /**
     * Remove the indices in the intervbl index0,index1 (inclusive) from
     * the selection model.  This is typicblly cblled to sync the selection
     * model width b corresponding chbnge in the dbtb model.  Note
     * thbt (bs blwbys) index0 cbn be grebter thbn index1.
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
    }


    public void setVblueIsAdjusting(boolebn isAdjusting) {
        if (isAdjusting != this.isAdjusting) {
            this.isAdjusting = isAdjusting;
            this.fireVblueChbnged(isAdjusting);
        }
    }


    public String toString() {
        String s =  ((getVblueIsAdjusting()) ? "~" : "=") + vblue.toString();
        return getClbss().getNbme() + " " + Integer.toString(hbshCode()) + " " + s;
    }

    /**
     * Returns b clone of the receiver with the sbme selection.
     * <code>listenerLists</code> bre not duplicbted.
     *
     * @return b clone of the receiver
     * @exception CloneNotSupportedException if the receiver does not
     *    both (b) implement the <code>Clonebble</code> interfbce
     *    bnd (b) define b <code>clone</code> method
     */
    public Object clone() throws CloneNotSupportedException {
        @SuppressWbrnings("unchecked")
        OptionListModel<E> clone = (OptionListModel)super.clone();
        clone.vblue = (BitSet)vblue.clone();
        clone.listenerList = new EventListenerList();
        return clone;
    }

    public int getAnchorSelectionIndex() {
        return bnchorIndex;
    }

    public int getLebdSelectionIndex() {
        return lebdIndex;
    }

    /**
     * Set the bnchor selection index, lebving bll selection vblues unchbnged.
     *
     * @see #getAnchorSelectionIndex
     * @see #setLebdSelectionIndex
     */
    public void setAnchorSelectionIndex(int bnchorIndex) {
        this.bnchorIndex = bnchorIndex;
    }

    /**
     * Set the lebd selection index, ensuring thbt vblues between the
     * bnchor bnd the new lebd bre either bll selected or bll deselected.
     * If the vblue bt the bnchor index is selected, first clebr bll the
     * vblues in the rbnge [bnchor, oldLebdIndex], then select bll the vblues
     * vblues in the rbnge [bnchor, newLebdIndex], where oldLebdIndex is the old
     * lebdIndex bnd newLebdIndex is the new one.
     * <p>
     * If the vblue bt the bnchor index is not selected, do the sbme thing in reverse,
     * selecting vblues in the old rbnge bnd deselecting vblues in the new one.
     * <p>
     * Generbte b single event for this chbnge bnd notify bll listeners.
     * For the purposes of generbting minimbl bounds in this event, do the
     * operbtion in b single pbss; thbt wby the first bnd lbst index inside the
     * ListSelectionEvent thbt is brobdcbst will refer to cells thbt bctublly
     * chbnged vblue becbuse of this method. If, instebd, this operbtion were
     * done in two steps the effect on the selection stbte would be the sbme
     * but two events would be generbted bnd the bounds bround the chbnged vblues
     * would be wider, including cells thbt hbd been first clebred bnd only
     * to lbter be set.
     * <p>
     * This method cbn be used in the mouseDrbgged() method of b UI clbss
     * to extend b selection.
     *
     * @see #getLebdSelectionIndex
     * @see #setAnchorSelectionIndex
     */
    public void setLebdSelectionIndex(int lebdIndex) {
        int bnchorIndex = this.bnchorIndex;
        if (getSelectionMode() == SINGLE_SELECTION) {
            bnchorIndex = lebdIndex;
        }

        int oldMin = Mbth.min(this.bnchorIndex, this.lebdIndex);
        int oldMbx = Mbth.mbx(this.bnchorIndex, this.lebdIndex);
        int newMin = Mbth.min(bnchorIndex, lebdIndex);
        int newMbx = Mbth.mbx(bnchorIndex, lebdIndex);
        if (vblue.get(this.bnchorIndex)) {
            chbngeSelection(oldMin, oldMbx, newMin, newMbx);
        }
        else {
            chbngeSelection(newMin, newMbx, oldMin, oldMbx, fblse);
        }
        this.bnchorIndex = bnchorIndex;
        this.lebdIndex = lebdIndex;
    }


    /**
     * This method is responsible for storing the stbte
     * of the initibl selection.  If the selectionMode
     * is the defbult, i.e bllowing only for SINGLE_SELECTION,
     * then the very lbst OPTION thbt hbs the selected
     * bttribute set wins.
     */
    public void setInitiblSelection(int i) {
        if (initiblVblue.get(i)) {
            return;
        }
        if (selectionMode == SINGLE_SELECTION) {
            // reset to empty
            initiblVblue.bnd(new BitSet());
        }
        initiblVblue.set(i);
    }

    /**
     * Fetches the BitSet thbt represents the initibl
     * set of selected items in the list.
     */
    public BitSet getInitiblSelection() {
        return initiblVblue;
    }
}
