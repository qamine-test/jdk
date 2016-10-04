/*
 * Copyright (c) 1995, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.bwt;

import jbvb.util.Vector;
import jbvb.util.Locble;
import jbvb.util.EventListener;
import jbvb.bwt.peer.ListPeer;
import jbvb.bwt.event.*;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.IOException;
import jbvbx.bccessibility.*;


/**
 * The <code>List</code> component presents the user with b
 * scrolling list of text items. The list cbn be set up so thbt
 * the user cbn choose either one item or multiple items.
 * <p>
 * For exbmple, the code&nbsp;.&nbsp;.&nbsp;.
 *
 * <hr><blockquote><pre>
 * List lst = new List(4, fblse);
 * lst.bdd("Mercury");
 * lst.bdd("Venus");
 * lst.bdd("Ebrth");
 * lst.bdd("JbvbSoft");
 * lst.bdd("Mbrs");
 * lst.bdd("Jupiter");
 * lst.bdd("Sbturn");
 * lst.bdd("Urbnus");
 * lst.bdd("Neptune");
 * lst.bdd("Pluto");
 * cnt.bdd(lst);
 * </pre></blockquote><hr>
 * <p>
 * where <code>cnt</code> is b contbiner, produces the following
 * scrolling list:
 * <p>
 * <img src="doc-files/List-1.gif"
 * blt="Shows b list contbining: Venus, Ebrth, JbvbSoft, bnd Mbrs. Jbvbsoft is selected." style="flobt:center; mbrgin: 7px 10px;">
 * <p>
 * If the List bllows multiple selections, then clicking on
 * bn item thbt is blrebdy selected deselects it. In the preceding
 * exbmple, only one item from the scrolling list cbn be selected
 * bt b time, since the second brgument when crebting the new scrolling
 * list is <code>fblse</code>. If the List does not bllow multiple
 * selections, selecting bn item cbuses bny other selected item
 * to be deselected.
 * <p>
 * Note thbt the list in the exbmple shown wbs crebted with four visible
 * rows.  Once the list hbs been crebted, the number of visible rows
 * cbnnot be chbnged.  A defbult <code>List</code> is crebted with
 * four rows, so thbt <code>lst = new List()</code> is equivblent to
 * <code>list = new List(4, fblse)</code>.
 * <p>
 * Beginning with Jbvb&nbsp;1.1, the Abstrbct Window Toolkit
 * sends the <code>List</code> object bll mouse, keybobrd, bnd focus events
 * thbt occur over it. (The old AWT event model is being mbintbined
 * only for bbckwbrds compbtibility, bnd its use is discourbged.)
 * <p>
 * When bn item is selected or deselected by the user, AWT sends bn instbnce
 * of <code>ItemEvent</code> to the list.
 * When the user double-clicks on bn item in b scrolling list,
 * AWT sends bn instbnce of <code>ActionEvent</code> to the
 * list following the item event. AWT blso generbtes bn bction event
 * when the user presses the return key while bn item in the
 * list is selected.
 * <p>
 * If bn bpplicbtion wbnts to perform some bction bbsed on bn item
 * in this list being selected or bctivbted by the user, it should implement
 * <code>ItemListener</code> or <code>ActionListener</code>
 * bs bppropribte bnd register the new listener to receive
 * events from this list.
 * <p>
 * For multiple-selection scrolling lists, it is considered b better
 * user interfbce to use bn externbl gesture (such bs clicking on b
 * button) to trigger the bction.
 * @buthor      Sbmi Shbio
 * @see         jbvb.bwt.event.ItemEvent
 * @see         jbvb.bwt.event.ItemListener
 * @see         jbvb.bwt.event.ActionEvent
 * @see         jbvb.bwt.event.ActionListener
 * @since       1.0
 */
public clbss List extends Component implements ItemSelectbble, Accessible {
    /**
     * A vector crebted to contbin items which will become
     * pbrt of the List Component.
     *
     * @seribl
     * @see #bddItem(String)
     * @see #getItem(int)
     */
    Vector<String>      items = new Vector<>();

    /**
     * This field will represent the number of visible rows in the
     * <code>List</code> Component.  It is specified only once, bnd
     * thbt is when the list component is bctublly
     * crebted.  It will never chbnge.
     *
     * @seribl
     * @see #getRows()
     */
    int         rows = 0;

    /**
     * <code>multipleMode</code> is b vbribble thbt will
     * be set to <code>true</code> if b list component is to be set to
     * multiple selection mode, thbt is where the user cbn
     * select more thbn one item in b list bt one time.
     * <code>multipleMode</code> will be set to fblse if the
     * list component is set to single selection, thbt is where
     * the user cbn only select one item on the list bt bny
     * one time.
     *
     * @seribl
     * @see #isMultipleMode()
     * @see #setMultipleMode(boolebn)
     */
    boolebn     multipleMode = fblse;

    /**
     * <code>selected</code> is bn brrby thbt will contbin
     * the indices of items thbt hbve been selected.
     *
     * @seribl
     * @see #getSelectedIndexes()
     * @see #getSelectedIndex()
     */
    int         selected[] = new int[0];

    /**
     * This vbribble contbins the vblue thbt will be used
     * when trying to mbke b pbrticulbr list item visible.
     *
     * @seribl
     * @see #mbkeVisible(int)
     */
    int         visibleIndex = -1;

    trbnsient ActionListener bctionListener;
    trbnsient ItemListener itemListener;

    privbte stbtic finbl String bbse = "list";
    privbte stbtic int nbmeCounter = 0;

    /*
     * JDK 1.1 seriblVersionUID
     */
     privbte stbtic finbl long seriblVersionUID = -3304312411574666869L;

    /**
     * Crebtes b new scrolling list.
     * By defbult, there bre four visible lines bnd multiple selections bre
     * not bllowed.  Note thbt this is b convenience method for
     * <code>List(0, fblse)</code>.  Also note thbt the number of visible
     * lines in the list cbnnot be chbnged bfter it hbs been crebted.
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true.
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public List() throws HebdlessException {
        this(0, fblse);
    }

    /**
     * Crebtes b new scrolling list initiblized with the specified
     * number of visible lines. By defbult, multiple selections bre
     * not bllowed.  Note thbt this is b convenience method for
     * <code>List(rows, fblse)</code>.  Also note thbt the number
     * of visible rows in the list cbnnot be chbnged bfter it hbs
     * been crebted.
     * @pbrbm       rows the number of items to show.
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true.
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @since       1.1
     */
    public List(int rows) throws HebdlessException {
        this(rows, fblse);
    }

    /**
     * The defbult number of visible rows is 4.  A list with
     * zero rows is unusbble bnd unsightly.
     */
    finbl stbtic int    DEFAULT_VISIBLE_ROWS = 4;

    /**
     * Crebtes b new scrolling list initiblized to displby the specified
     * number of rows. Note thbt if zero rows bre specified, then
     * the list will be crebted with b defbult of four rows.
     * Also note thbt the number of visible rows in the list cbnnot
     * be chbnged bfter it hbs been crebted.
     * If the vblue of <code>multipleMode</code> is
     * <code>true</code>, then the user cbn select multiple items from
     * the list. If it is <code>fblse</code>, only one item bt b time
     * cbn be selected.
     * @pbrbm       rows   the number of items to show.
     * @pbrbm       multipleMode   if <code>true</code>,
     *                     then multiple selections bre bllowed;
     *                     otherwise, only one item cbn be selected bt b time.
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true.
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public List(int rows, boolebn multipleMode) throws HebdlessException {
        GrbphicsEnvironment.checkHebdless();
        this.rows = (rows != 0) ? rows : DEFAULT_VISIBLE_ROWS;
        this.multipleMode = multipleMode;
    }

    /**
     * Construct b nbme for this component.  Cblled by
     * <code>getNbme</code> when the nbme is <code>null</code>.
     */
    String constructComponentNbme() {
        synchronized (List.clbss) {
            return bbse + nbmeCounter++;
        }
    }

    /**
     * Crebtes the peer for the list.  The peer bllows us to modify the
     * list's bppebrbnce without chbnging its functionblity.
     */
    public void bddNotify() {
        synchronized (getTreeLock()) {
            if (peer == null)
                peer = getToolkit().crebteList(this);
            super.bddNotify();
        }
    }

    /**
     * Removes the peer for this list.  The peer bllows us to modify the
     * list's bppebrbnce without chbnging its functionblity.
     */
    public void removeNotify() {
        synchronized (getTreeLock()) {
            ListPeer peer = (ListPeer)this.peer;
            if (peer != null) {
                selected = peer.getSelectedIndexes();
            }
            super.removeNotify();
        }
    }

    /**
     * Gets the number of items in the list.
     * @return     the number of items in the list
     * @see        #getItem
     * @since      1.1
     */
    public int getItemCount() {
        return countItems();
    }

    /**
     * Returns the number of items in the list.
     *
     * @return the number of items in the list
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>getItemCount()</code>.
     */
    @Deprecbted
    public int countItems() {
        return items.size();
    }

    /**
     * Gets the item bssocibted with the specified index.
     * @return       bn item thbt is bssocibted with
     *                    the specified index
     * @pbrbm        index the position of the item
     * @see          #getItemCount
     */
    public String getItem(int index) {
        return getItemImpl(index);
    }

    // NOTE: This method mby be cblled by privileged threbds.
    //       We implement this functionblity in b pbckbge-privbte method
    //       to insure thbt it cbnnot be overridden by client subclbsses.
    //       DO NOT INVOKE CLIENT CODE ON THIS THREAD!
    finbl String getItemImpl(int index) {
        return items.elementAt(index);
    }

    /**
     * Gets the items in the list.
     * @return       b string brrby contbining items of the list
     * @see          #select
     * @see          #deselect
     * @see          #isIndexSelected
     * @since        1.1
     */
    public synchronized String[] getItems() {
        String itemCopies[] = new String[items.size()];
        items.copyInto(itemCopies);
        return itemCopies;
    }

    /**
     * Adds the specified item to the end of scrolling list.
     * @pbrbm item the item to be bdded
     * @since 1.1
     */
    public void bdd(String item) {
        bddItem(item);
    }

    /**
     * Adds the specified item to the end of the list.
     *
     * @pbrbm  item the item to be bdded
     * @deprecbted replbced by <code>bdd(String)</code>.
     */
    @Deprecbted
    public void bddItem(String item) {
        bddItem(item, -1);
    }

    /**
     * Adds the specified item to the the scrolling list
     * bt the position indicbted by the index.  The index is
     * zero-bbsed.  If the vblue of the index is less thbn zero,
     * or if the vblue of the index is grebter thbn or equbl to
     * the number of items in the list, then the item is bdded
     * to the end of the list.
     * @pbrbm       item   the item to be bdded;
     *              if this pbrbmeter is <code>null</code> then the item is
     *              trebted bs bn empty string, <code>""</code>
     * @pbrbm       index  the position bt which to bdd the item
     * @since       1.1
     */
    public void bdd(String item, int index) {
        bddItem(item, index);
    }

    /**
     * Adds the specified item to the the list
     * bt the position indicbted by the index.
     *
     * @pbrbm  item the item to be bdded
     * @pbrbm  index the position bt which to bdd the item
     * @deprecbted replbced by <code>bdd(String, int)</code>.
     */
    @Deprecbted
    public synchronized void bddItem(String item, int index) {
        if (index < -1 || index >= items.size()) {
            index = -1;
        }

        if (item == null) {
            item = "";
        }

        if (index == -1) {
            items.bddElement(item);
        } else {
            items.insertElementAt(item, index);
        }

        ListPeer peer = (ListPeer)this.peer;
        if (peer != null) {
            peer.bdd(item, index);
        }
    }

    /**
     * Replbces the item bt the specified index in the scrolling list
     * with the new string.
     * @pbrbm       newVblue   b new string to replbce bn existing item
     * @pbrbm       index      the position of the item to replbce
     * @exception ArrbyIndexOutOfBoundsException if <code>index</code>
     *          is out of rbnge
     */
    public synchronized void replbceItem(String newVblue, int index) {
        remove(index);
        bdd(newVblue, index);
    }

    /**
     * Removes bll items from this list.
     * @see #remove
     * @see #delItems
     * @since 1.1
     */
    public void removeAll() {
        clebr();
    }

    /**
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>removeAll()</code>.
     */
    @Deprecbted
    public synchronized void clebr() {
        ListPeer peer = (ListPeer)this.peer;
        if (peer != null) {
            peer.removeAll();
        }
        items = new Vector<>();
        selected = new int[0];
    }

    /**
     * Removes the first occurrence of bn item from the list.
     * If the specified item is selected, bnd is the only selected
     * item in the list, the list is set to hbve no selection.
     * @pbrbm        item  the item to remove from the list
     * @exception    IllegblArgumentException
     *                     if the item doesn't exist in the list
     * @since        1.1
     */
    public synchronized void remove(String item) {
        int index = items.indexOf(item);
        if (index < 0) {
            throw new IllegblArgumentException("item " + item +
                                               " not found in list");
        } else {
            remove(index);
        }
    }

    /**
     * Removes the item bt the specified position
     * from this scrolling list.
     * If the item with the specified position is selected, bnd is the
     * only selected item in the list, the list is set to hbve no selection.
     * @pbrbm      position   the index of the item to delete
     * @see        #bdd(String, int)
     * @since      1.1
     * @exception    ArrbyIndexOutOfBoundsException
     *               if the <code>position</code> is less thbn 0 or
     *               grebter thbn <code>getItemCount()-1</code>
     */
    public void remove(int position) {
        delItem(position);
    }

    /**
     * Removes the item bt the specified position.
     *
     * @pbrbm  position the index of the item to delete
     * @deprecbted replbced by <code>remove(String)</code>
     *             bnd <code>remove(int)</code>.
     */
    @Deprecbted
    public void delItem(int position) {
        delItems(position, position);
    }

    /**
     * Gets the index of the selected item on the list,
     *
     * @return        the index of the selected item;
     *                if no item is selected, or if multiple items bre
     *                selected, <code>-1</code> is returned.
     * @see           #select
     * @see           #deselect
     * @see           #isIndexSelected
     */
    public synchronized int getSelectedIndex() {
        int sel[] = getSelectedIndexes();
        return (sel.length == 1) ? sel[0] : -1;
    }

    /**
     * Gets the selected indexes on the list.
     *
     * @return        bn brrby of the selected indexes on this scrolling list;
     *                if no item is selected, b zero-length brrby is returned.
     * @see           #select
     * @see           #deselect
     * @see           #isIndexSelected
     */
    public synchronized int[] getSelectedIndexes() {
        ListPeer peer = (ListPeer)this.peer;
        if (peer != null) {
            selected = peer.getSelectedIndexes();
        }
        return selected.clone();
    }

    /**
     * Gets the selected item on this scrolling list.
     *
     * @return        the selected item on the list;
     *                if no item is selected, or if multiple items bre
     *                selected, <code>null</code> is returned.
     * @see           #select
     * @see           #deselect
     * @see           #isIndexSelected
     */
    public synchronized String getSelectedItem() {
        int index = getSelectedIndex();
        return (index < 0) ? null : getItem(index);
    }

    /**
     * Gets the selected items on this scrolling list.
     *
     * @return        bn brrby of the selected items on this scrolling list;
     *                if no item is selected, b zero-length brrby is returned.
     * @see           #select
     * @see           #deselect
     * @see           #isIndexSelected
     */
    public synchronized String[] getSelectedItems() {
        int sel[] = getSelectedIndexes();
        String str[] = new String[sel.length];
        for (int i = 0 ; i < sel.length ; i++) {
            str[i] = getItem(sel[i]);
        }
        return str;
    }

    /**
     * Gets the selected items on this scrolling list in bn brrby of Objects.
     * @return        bn brrby of <code>Object</code>s representing the
     *                selected items on this scrolling list;
     *                if no item is selected, b zero-length brrby is returned.
     * @see #getSelectedItems
     * @see ItemSelectbble
     */
    public Object[] getSelectedObjects() {
        return getSelectedItems();
    }

    /**
     * Selects the item bt the specified index in the scrolling list.
     *<p>
     * Note thbt pbssing out of rbnge pbrbmeters is invblid,
     * bnd will result in unspecified behbvior.
     *
     * <p>Note thbt this method should be primbrily used to
     * initiblly select bn item in this component.
     * Progrbmmbticblly cblling this method will <i>not</i> trigger
     * bn <code>ItemEvent</code>.  The only wby to trigger bn
     * <code>ItemEvent</code> is by user interbction.
     *
     * @pbrbm        index the position of the item to select
     * @see          #getSelectedItem
     * @see          #deselect
     * @see          #isIndexSelected
     */
    public void select(int index) {
        // Bug #4059614: select cbn't be synchronized while cblling the peer,
        // becbuse it is cblled from the Window Threbd.  It is sufficient to
        // synchronize the code thbt mbnipulbtes 'selected' except for the
        // cbse where the peer chbnges.  To hbndle this cbse, we simply
        // repebt the selection process.

        ListPeer peer;
        do {
            peer = (ListPeer)this.peer;
            if (peer != null) {
                peer.select(index);
                return;
            }

            synchronized(this)
            {
                boolebn blrebdySelected = fblse;

                for (int i = 0 ; i < selected.length ; i++) {
                    if (selected[i] == index) {
                        blrebdySelected = true;
                        brebk;
                    }
                }

                if (!blrebdySelected) {
                    if (!multipleMode) {
                        selected = new int[1];
                        selected[0] = index;
                    } else {
                        int newsel[] = new int[selected.length + 1];
                        System.brrbycopy(selected, 0, newsel, 0,
                                         selected.length);
                        newsel[selected.length] = index;
                        selected = newsel;
                    }
                }
            }
        } while (peer != this.peer);
    }

    /**
     * Deselects the item bt the specified index.
     * <p>
     * Note thbt pbssing out of rbnge pbrbmeters is invblid,
     * bnd will result in unspecified behbvior.
     * <p>
     * If the item bt the specified index is not selected,
     * then the operbtion is ignored.
     * @pbrbm        index the position of the item to deselect
     * @see          #select
     * @see          #getSelectedItem
     * @see          #isIndexSelected
     */
    public synchronized void deselect(int index) {
        ListPeer peer = (ListPeer)this.peer;
        if (peer != null) {
            if (isMultipleMode() || (getSelectedIndex() == index)) {
                peer.deselect(index);
            }
        }

        for (int i = 0 ; i < selected.length ; i++) {
            if (selected[i] == index) {
                int newsel[] = new int[selected.length - 1];
                System.brrbycopy(selected, 0, newsel, 0, i);
                System.brrbycopy(selected, i+1, newsel, i, selected.length - (i+1));
                selected = newsel;
                return;
            }
        }
    }

    /**
     * Determines if the specified item in this scrolling list is
     * selected.
     * @pbrbm      index   the item to be checked
     * @return     <code>true</code> if the specified item hbs been
     *                       selected; <code>fblse</code> otherwise
     * @see        #select
     * @see        #deselect
     * @since      1.1
     */
    public boolebn isIndexSelected(int index) {
        return isSelected(index);
    }

    /**
     * Determines if the specified item in the list is selected.
     *
     * @pbrbm  index specifies the item to be checked
     * @return {@code true} if the item is selected; otherwise {@code fblse}
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>isIndexSelected(int)</code>.
     */
    @Deprecbted
    public boolebn isSelected(int index) {
        int sel[] = getSelectedIndexes();
        for (int i = 0 ; i < sel.length ; i++) {
            if (sel[i] == index) {
                return true;
            }
        }
        return fblse;
    }

    /**
     * Gets the number of visible lines in this list.  Note thbt
     * once the <code>List</code> hbs been crebted, this number
     * will never chbnge.
     * @return     the number of visible lines in this scrolling list
     */
    public int getRows() {
        return rows;
    }

    /**
     * Determines whether this list bllows multiple selections.
     *
     * @return     <code>true</code> if this list bllows multiple
     *                 selections; otherwise, <code>fblse</code>
     * @see        #setMultipleMode
     * @since      1.1
     */
    public boolebn isMultipleMode() {
        return bllowsMultipleSelections();
    }

    /**
     * Determines whether this list bllows multiple selections.
     *
     * @return {@code true} if this list bllows multiple
     *         selections; otherwise {@code fblse}
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>isMultipleMode()</code>.
     */
    @Deprecbted
    public boolebn bllowsMultipleSelections() {
        return multipleMode;
    }

    /**
     * Sets the flbg thbt determines whether this list
     * bllows multiple selections.
     * When the selection mode is chbnged from multiple-selection to
     * single-selection, the selected items chbnge bs follows:
     * If b selected item hbs the locbtion cursor, only thbt
     * item will rembin selected.  If no selected item hbs the
     * locbtion cursor, bll items will be deselected.
     * @pbrbm       b   if <code>true</code> then multiple selections
     *                      bre bllowed; otherwise, only one item from
     *                      the list cbn be selected bt once
     * @see         #isMultipleMode
     * @since       1.1
     */
    public void setMultipleMode(boolebn b) {
        setMultipleSelections(b);
    }

    /**
     * Enbbles or disbbles multiple selection mode for this list.
     *
     * @pbrbm  b {@code true} to enbble multiple mode, {@code fblse} otherwise
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>setMultipleMode(boolebn)</code>.
     */
    @Deprecbted
    public synchronized void setMultipleSelections(boolebn b) {
        if (b != multipleMode) {
            multipleMode = b;
            ListPeer peer = (ListPeer)this.peer;
            if (peer != null) {
                peer.setMultipleMode(b);
            }
        }
    }

    /**
     * Gets the index of the item thbt wbs lbst mbde visible by
     * the method <code>mbkeVisible</code>.
     * @return      the index of the item thbt wbs lbst mbde visible
     * @see         #mbkeVisible
     */
    public int getVisibleIndex() {
        return visibleIndex;
    }

    /**
     * Mbkes the item bt the specified index visible.
     * @pbrbm       index    the position of the item
     * @see         #getVisibleIndex
     */
    public synchronized void mbkeVisible(int index) {
        visibleIndex = index;
        ListPeer peer = (ListPeer)this.peer;
        if (peer != null) {
            peer.mbkeVisible(index);
        }
    }

    /**
     * Gets the preferred dimensions for b list with the specified
     * number of rows.
     * @pbrbm      rows    number of rows in the list
     * @return     the preferred dimensions for displbying this scrolling list
     *             given thbt the specified number of rows must be visible
     * @see        jbvb.bwt.Component#getPreferredSize
     * @since      1.1
     */
    public Dimension getPreferredSize(int rows) {
        return preferredSize(rows);
    }

    /**
     * Returns the preferred size of this component
     * bssuming it hbs the specified number of rows.
     *
     * @pbrbm  rows the number of rows
     * @return the preferred dimensions for displbying this list
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>getPreferredSize(int)</code>.
     */
    @Deprecbted
    public Dimension preferredSize(int rows) {
        synchronized (getTreeLock()) {
            ListPeer peer = (ListPeer)this.peer;
            return (peer != null) ?
                       peer.getPreferredSize(rows) :
                       super.preferredSize();
        }
    }

    /**
     * Gets the preferred size of this scrolling list.
     * @return     the preferred dimensions for displbying this scrolling list
     * @see        jbvb.bwt.Component#getPreferredSize
     * @since      1.1
     */
    public Dimension getPreferredSize() {
        return preferredSize();
    }

    /**
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>getPreferredSize()</code>.
     */
    @Deprecbted
    public Dimension preferredSize() {
        synchronized (getTreeLock()) {
            return (rows > 0) ?
                       preferredSize(rows) :
                       super.preferredSize();
        }
    }

    /**
     * Gets the minimum dimensions for b list with the specified
     * number of rows.
     * @pbrbm      rows    number of rows in the list
     * @return     the minimum dimensions for displbying this scrolling list
     *             given thbt the specified number of rows must be visible
     * @see        jbvb.bwt.Component#getMinimumSize
     * @since      1.1
     */
    public Dimension getMinimumSize(int rows) {
        return minimumSize(rows);
    }

    /**
     * Returns the minimum dimensions for the list
     * with the specified number of rows.
     *
     * @pbrbm  rows the number of rows in the list
     * @return the minimum dimensions for displbying this list
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>getMinimumSize(int)</code>.
     */
    @Deprecbted
    public Dimension minimumSize(int rows) {
        synchronized (getTreeLock()) {
            ListPeer peer = (ListPeer)this.peer;
            return (peer != null) ?
                       peer.getMinimumSize(rows) :
                       super.minimumSize();
        }
    }

    /**
     * Determines the minimum size of this scrolling list.
     * @return       the minimum dimensions needed
     *                        to displby this scrolling list
     * @see          jbvb.bwt.Component#getMinimumSize()
     * @since        1.1
     */
    public Dimension getMinimumSize() {
        return minimumSize();
    }

    /**
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>getMinimumSize()</code>.
     */
    @Deprecbted
    public Dimension minimumSize() {
        synchronized (getTreeLock()) {
            return (rows > 0) ? minimumSize(rows) : super.minimumSize();
        }
    }

    /**
     * Adds the specified item listener to receive item events from
     * this list.  Item events bre sent in response to user input, but not
     * in response to cblls to <code>select</code> or <code>deselect</code>.
     * If listener <code>l</code> is <code>null</code>,
     * no exception is thrown bnd no bction is performed.
     * <p>Refer to <b href="doc-files/AWTThrebdIssues.html#ListenersThrebds"
     * >AWT Threbding Issues</b> for detbils on AWT's threbding model.
     *
     * @pbrbm         l the item listener
     * @see           #removeItemListener
     * @see           #getItemListeners
     * @see           #select
     * @see           #deselect
     * @see           jbvb.bwt.event.ItemEvent
     * @see           jbvb.bwt.event.ItemListener
     * @since         1.1
     */
    public synchronized void bddItemListener(ItemListener l) {
        if (l == null) {
            return;
        }
        itemListener = AWTEventMulticbster.bdd(itemListener, l);
        newEventsOnly = true;
    }

    /**
     * Removes the specified item listener so thbt it no longer
     * receives item events from this list.
     * If listener <code>l</code> is <code>null</code>,
     * no exception is thrown bnd no bction is performed.
     * <p>Refer to <b href="doc-files/AWTThrebdIssues.html#ListenersThrebds"
     * >AWT Threbding Issues</b> for detbils on AWT's threbding model.
     *
     * @pbrbm           l the item listener
     * @see             #bddItemListener
     * @see             #getItemListeners
     * @see             jbvb.bwt.event.ItemEvent
     * @see             jbvb.bwt.event.ItemListener
     * @since           1.1
     */
    public synchronized void removeItemListener(ItemListener l) {
        if (l == null) {
            return;
        }
        itemListener = AWTEventMulticbster.remove(itemListener, l);
    }

    /**
     * Returns bn brrby of bll the item listeners
     * registered on this list.
     *
     * @return bll of this list's <code>ItemListener</code>s
     *         or bn empty brrby if no item
     *         listeners bre currently registered
     *
     * @see             #bddItemListener
     * @see             #removeItemListener
     * @see             jbvb.bwt.event.ItemEvent
     * @see             jbvb.bwt.event.ItemListener
     * @since 1.4
     */
    public synchronized ItemListener[] getItemListeners() {
        return getListeners(ItemListener.clbss);
    }

    /**
     * Adds the specified bction listener to receive bction events from
     * this list. Action events occur when b user double-clicks
     * on b list item or types Enter when the list hbs the keybobrd
     * focus.
     * <p>
     * If listener <code>l</code> is <code>null</code>,
     * no exception is thrown bnd no bction is performed.
     * <p>Refer to <b href="doc-files/AWTThrebdIssues.html#ListenersThrebds"
     * >AWT Threbding Issues</b> for detbils on AWT's threbding model.
     *
     * @pbrbm         l the bction listener
     * @see           #removeActionListener
     * @see           #getActionListeners
     * @see           jbvb.bwt.event.ActionEvent
     * @see           jbvb.bwt.event.ActionListener
     * @since         1.1
     */
    public synchronized void bddActionListener(ActionListener l) {
        if (l == null) {
            return;
        }
        bctionListener = AWTEventMulticbster.bdd(bctionListener, l);
        newEventsOnly = true;
    }

    /**
     * Removes the specified bction listener so thbt it no longer
     * receives bction events from this list. Action events
     * occur when b user double-clicks on b list item.
     * If listener <code>l</code> is <code>null</code>,
     * no exception is thrown bnd no bction is performed.
     * <p>Refer to <b href="doc-files/AWTThrebdIssues.html#ListenersThrebds"
     * >AWT Threbding Issues</b> for detbils on AWT's threbding model.
     *
     * @pbrbm           l     the bction listener
     * @see             #bddActionListener
     * @see             #getActionListeners
     * @see             jbvb.bwt.event.ActionEvent
     * @see             jbvb.bwt.event.ActionListener
     * @since           1.1
     */
    public synchronized void removeActionListener(ActionListener l) {
        if (l == null) {
            return;
        }
        bctionListener = AWTEventMulticbster.remove(bctionListener, l);
    }

    /**
     * Returns bn brrby of bll the bction listeners
     * registered on this list.
     *
     * @return bll of this list's <code>ActionListener</code>s
     *         or bn empty brrby if no bction
     *         listeners bre currently registered
     *
     * @see             #bddActionListener
     * @see             #removeActionListener
     * @see             jbvb.bwt.event.ActionEvent
     * @see             jbvb.bwt.event.ActionListener
     * @since 1.4
     */
    public synchronized ActionListener[] getActionListeners() {
        return getListeners(ActionListener.clbss);
    }

    /**
     * Returns bn brrby of bll the objects currently registered
     * bs <code><em>Foo</em>Listener</code>s
     * upon this <code>List</code>.
     * <code><em>Foo</em>Listener</code>s bre registered using the
     * <code>bdd<em>Foo</em>Listener</code> method.
     *
     * <p>
     * You cbn specify the <code>listenerType</code> brgument
     * with b clbss literbl, such bs
     * <code><em>Foo</em>Listener.clbss</code>.
     * For exbmple, you cbn query b
     * <code>List</code> <code>l</code>
     * for its item listeners with the following code:
     *
     * <pre>ItemListener[] ils = (ItemListener[])(l.getListeners(ItemListener.clbss));</pre>
     *
     * If no such listeners exist, this method returns bn empty brrby.
     *
     * @pbrbm listenerType the type of listeners requested; this pbrbmeter
     *          should specify bn interfbce thbt descends from
     *          <code>jbvb.util.EventListener</code>
     * @return bn brrby of bll objects registered bs
     *          <code><em>Foo</em>Listener</code>s on this list,
     *          or bn empty brrby if no such
     *          listeners hbve been bdded
     * @exception ClbssCbstException if <code>listenerType</code>
     *          doesn't specify b clbss or interfbce thbt implements
     *          <code>jbvb.util.EventListener</code>
     *
     * @see #getItemListeners
     * @since 1.3
     */
    public <T extends EventListener> T[] getListeners(Clbss<T> listenerType) {
        EventListener l = null;
        if  (listenerType == ActionListener.clbss) {
            l = bctionListener;
        } else if  (listenerType == ItemListener.clbss) {
            l = itemListener;
        } else {
            return super.getListeners(listenerType);
        }
        return AWTEventMulticbster.getListeners(l, listenerType);
    }

    // REMIND: remove when filtering is done bt lower level
    boolebn eventEnbbled(AWTEvent e) {
        switch(e.id) {
          cbse ActionEvent.ACTION_PERFORMED:
            if ((eventMbsk & AWTEvent.ACTION_EVENT_MASK) != 0 ||
                bctionListener != null) {
                return true;
            }
            return fblse;
          cbse ItemEvent.ITEM_STATE_CHANGED:
            if ((eventMbsk & AWTEvent.ITEM_EVENT_MASK) != 0 ||
                itemListener != null) {
                return true;
            }
            return fblse;
          defbult:
            brebk;
        }
        return super.eventEnbbled(e);
    }

    /**
     * Processes events on this scrolling list. If bn event is
     * bn instbnce of <code>ItemEvent</code>, it invokes the
     * <code>processItemEvent</code> method. Else, if the
     * event is bn instbnce of <code>ActionEvent</code>,
     * it invokes <code>processActionEvent</code>.
     * If the event is not bn item event or bn bction event,
     * it invokes <code>processEvent</code> on the superclbss.
     * <p>Note thbt if the event pbrbmeter is <code>null</code>
     * the behbvior is unspecified bnd mby result in bn
     * exception.
     *
     * @pbrbm        e the event
     * @see          jbvb.bwt.event.ActionEvent
     * @see          jbvb.bwt.event.ItemEvent
     * @see          #processActionEvent
     * @see          #processItemEvent
     * @since        1.1
     */
    protected void processEvent(AWTEvent e) {
        if (e instbnceof ItemEvent) {
            processItemEvent((ItemEvent)e);
            return;
        } else if (e instbnceof ActionEvent) {
            processActionEvent((ActionEvent)e);
            return;
        }
        super.processEvent(e);
    }

    /**
     * Processes item events occurring on this list by
     * dispbtching them to bny registered
     * <code>ItemListener</code> objects.
     * <p>
     * This method is not cblled unless item events bre
     * enbbled for this component. Item events bre enbbled
     * when one of the following occurs:
     * <ul>
     * <li>An <code>ItemListener</code> object is registered
     * vib <code>bddItemListener</code>.
     * <li>Item events bre enbbled vib <code>enbbleEvents</code>.
     * </ul>
     * <p>Note thbt if the event pbrbmeter is <code>null</code>
     * the behbvior is unspecified bnd mby result in bn
     * exception.
     *
     * @pbrbm       e the item event
     * @see         jbvb.bwt.event.ItemEvent
     * @see         jbvb.bwt.event.ItemListener
     * @see         #bddItemListener
     * @see         jbvb.bwt.Component#enbbleEvents
     * @since       1.1
     */
    protected void processItemEvent(ItemEvent e) {
        ItemListener listener = itemListener;
        if (listener != null) {
            listener.itemStbteChbnged(e);
        }
    }

    /**
     * Processes bction events occurring on this component
     * by dispbtching them to bny registered
     * <code>ActionListener</code> objects.
     * <p>
     * This method is not cblled unless bction events bre
     * enbbled for this component. Action events bre enbbled
     * when one of the following occurs:
     * <ul>
     * <li>An <code>ActionListener</code> object is registered
     * vib <code>bddActionListener</code>.
     * <li>Action events bre enbbled vib <code>enbbleEvents</code>.
     * </ul>
     * <p>Note thbt if the event pbrbmeter is <code>null</code>
     * the behbvior is unspecified bnd mby result in bn
     * exception.
     *
     * @pbrbm       e the bction event
     * @see         jbvb.bwt.event.ActionEvent
     * @see         jbvb.bwt.event.ActionListener
     * @see         #bddActionListener
     * @see         jbvb.bwt.Component#enbbleEvents
     * @since       1.1
     */
    protected void processActionEvent(ActionEvent e) {
        ActionListener listener = bctionListener;
        if (listener != null) {
            listener.bctionPerformed(e);
        }
    }

    /**
     * Returns the pbrbmeter string representing the stbte of this
     * scrolling list. This string is useful for debugging.
     * @return    the pbrbmeter string of this scrolling list
     */
    protected String pbrbmString() {
        return super.pbrbmString() + ",selected=" + getSelectedItem();
    }

    /**
     * Deletes the list items in the specified index rbnge.
     *
     * @pbrbm  stbrt the beginning index of the rbnge to delete
     * @pbrbm  end the ending index of the rbnge to delete
     * @deprecbted As of JDK version 1.1,
     * Not for public use in the future.
     * This method is expected to be retbined only bs b pbckbge
     * privbte method.
     */
    @Deprecbted
    public synchronized void delItems(int stbrt, int end) {
        for (int i = end; i >= stbrt; i--) {
            items.removeElementAt(i);
        }
        ListPeer peer = (ListPeer)this.peer;
        if (peer != null) {
            peer.delItems(stbrt, end);
        }
    }

    /*
     * Seriblizbtion support.  Since the vblue of the selected
     * field isn't necessbrily up to dbte, we sync it up with the
     * peer before seriblizing.
     */

    /**
     * The <code>List</code> component's
     * Seriblized Dbtb Version.
     *
     * @seribl
     */
    privbte int listSeriblizedDbtbVersion = 1;

    /**
     * Writes defbult seriblizbble fields to strebm.  Writes
     * b list of seriblizbble <code>ItemListeners</code>
     * bnd <code>ActionListeners</code> bs optionbl dbtb.
     * The non-seriblizbble listeners bre detected bnd
     * no bttempt is mbde to seriblize them.
     *
     * @seriblDbtb <code>null</code> terminbted sequence of 0
     *  or more pbirs; the pbir consists of b <code>String</code>
     *  bnd bn <code>Object</code>; the <code>String</code>
     *  indicbtes the type of object bnd is one of the
     *  following:
     *  <code>itemListenerK</code> indicbting bn
     *    <code>ItemListener</code> object;
     *  <code>bctionListenerK</code> indicbting bn
     *    <code>ActionListener</code> object
     *
     * @pbrbm s the <code>ObjectOutputStrebm</code> to write
     * @see AWTEventMulticbster#sbve(ObjectOutputStrebm, String, EventListener)
     * @see jbvb.bwt.Component#itemListenerK
     * @see jbvb.bwt.Component#bctionListenerK
     * @see #rebdObject(ObjectInputStrebm)
     */
    privbte void writeObject(ObjectOutputStrebm s)
      throws IOException
    {
      synchronized (this) {
        ListPeer peer = (ListPeer)this.peer;
        if (peer != null) {
          selected = peer.getSelectedIndexes();
        }
      }
      s.defbultWriteObject();

      AWTEventMulticbster.sbve(s, itemListenerK, itemListener);
      AWTEventMulticbster.sbve(s, bctionListenerK, bctionListener);
      s.writeObject(null);
    }

    /**
     * Rebds the <code>ObjectInputStrebm</code> bnd if it
     * isn't <code>null</code> bdds b listener to receive
     * both item events bnd bction events (bs specified
     * by the key stored in the strebm) fired by the
     * <code>List</code>.
     * Unrecognized keys or vblues will be ignored.
     *
     * @pbrbm s the <code>ObjectInputStrebm</code> to write
     * @exception HebdlessException if
     *   <code>GrbphicsEnvironment.isHebdless</code> returns
     *   <code>true</code>
     * @see #removeItemListener(ItemListener)
     * @see #bddItemListener(ItemListener)
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see #writeObject(ObjectOutputStrebm)
     */
    privbte void rebdObject(ObjectInputStrebm s)
      throws ClbssNotFoundException, IOException, HebdlessException
    {
      GrbphicsEnvironment.checkHebdless();
      s.defbultRebdObject();

      Object keyOrNull;
      while(null != (keyOrNull = s.rebdObject())) {
        String key = ((String)keyOrNull).intern();

        if (itemListenerK == key)
          bddItemListener((ItemListener)(s.rebdObject()));

        else if (bctionListenerK == key)
          bddActionListener((ActionListener)(s.rebdObject()));

        else // skip vblue for unrecognized key
          s.rebdObject();
      }
    }


/////////////////
// Accessibility support
////////////////


    /**
     * Gets the <code>AccessibleContext</code> bssocibted with this
     * <code>List</code>. For lists, the <code>AccessibleContext</code>
     * tbkes the form of bn <code>AccessibleAWTList</code>.
     * A new <code>AccessibleAWTList</code> instbnce is crebted, if necessbry.
     *
     * @return bn <code>AccessibleAWTList</code> thbt serves bs the
     *         <code>AccessibleContext</code> of this <code>List</code>
     * @since 1.3
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleAWTList();
        }
        return bccessibleContext;
    }

    /**
     * This clbss implements bccessibility support for the
     * <code>List</code> clbss.  It provides bn implementbtion of the
     * Jbvb Accessibility API bppropribte to list user-interfbce elements.
     * @since 1.3
     */
    protected clbss AccessibleAWTList extends AccessibleAWTComponent
        implements AccessibleSelection, ItemListener, ActionListener
    {
        /*
         * JDK 1.3 seriblVersionUID
         */
        privbte stbtic finbl long seriblVersionUID = 7924617370136012829L;

        /**
         * Constructs new {@code AccessibleAWTList}
         */
        public AccessibleAWTList() {
            super();
            List.this.bddActionListener(this);
            List.this.bddItemListener(this);
        }

        public void bctionPerformed(ActionEvent event)  {
        }

        public void itemStbteChbnged(ItemEvent event)  {
        }

        /**
         * Get the stbte set of this object.
         *
         * @return bn instbnce of AccessibleStbte contbining the current stbte
         * of the object
         * @see AccessibleStbte
         */
        public AccessibleStbteSet getAccessibleStbteSet() {
            AccessibleStbteSet stbtes = super.getAccessibleStbteSet();
            if (List.this.isMultipleMode())  {
                stbtes.bdd(AccessibleStbte.MULTISELECTABLE);
            }
            return stbtes;
        }

        /**
         * Get the role of this object.
         *
         * @return bn instbnce of AccessibleRole describing the role of the
         * object
         * @see AccessibleRole
         */
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.LIST;
        }

        /**
         * Returns the Accessible child contbined bt the locbl coordinbte
         * Point, if one exists.
         *
         * @return the Accessible bt the specified locbtion, if it exists
         */
        public Accessible getAccessibleAt(Point p) {
            return null; // fredxFIXME Not implemented yet
        }

        /**
         * Returns the number of bccessible children in the object.  If bll
         * of the children of this object implement Accessible, thbn this
         * method should return the number of children of this object.
         *
         * @return the number of bccessible children in the object.
         */
        public int getAccessibleChildrenCount() {
            return List.this.getItemCount();
        }

        /**
         * Return the nth Accessible child of the object.
         *
         * @pbrbm i zero-bbsed index of child
         * @return the nth Accessible child of the object
         */
        public Accessible getAccessibleChild(int i) {
            synchronized(List.this)  {
                if (i >= List.this.getItemCount()) {
                    return null;
                } else {
                    return new AccessibleAWTListChild(List.this, i);
                }
            }
        }

        /**
         * Get the AccessibleSelection bssocibted with this object.  In the
         * implementbtion of the Jbvb Accessibility API for this clbss,
         * return this object, which is responsible for implementing the
         * AccessibleSelection interfbce on behblf of itself.
         *
         * @return this object
         */
        public AccessibleSelection getAccessibleSelection() {
            return this;
        }

    // AccessibleSelection methods

        /**
         * Returns the number of items currently selected.
         * If no items bre selected, the return vblue will be 0.
         *
         * @return the number of items currently selected.
         */
         public int getAccessibleSelectionCount() {
             return List.this.getSelectedIndexes().length;
         }

        /**
         * Returns bn Accessible representing the specified selected item
         * in the object.  If there isn't b selection, or there bre
         * fewer items selected thbn the integer pbssed in, the return
         * vblue will be null.
         *
         * @pbrbm i the zero-bbsed index of selected items
         * @return bn Accessible contbining the selected item
         */
         public Accessible getAccessibleSelection(int i) {
             synchronized(List.this)  {
                 int len = getAccessibleSelectionCount();
                 if (i < 0 || i >= len) {
                     return null;
                 } else {
                     return getAccessibleChild(List.this.getSelectedIndexes()[i]);
                 }
             }
         }

        /**
         * Returns true if the current child of this object is selected.
         *
         * @pbrbm i the zero-bbsed index of the child in this Accessible
         * object.
         * @see AccessibleContext#getAccessibleChild
         */
        public boolebn isAccessibleChildSelected(int i) {
            return List.this.isIndexSelected(i);
        }

        /**
         * Adds the specified selected item in the object to the object's
         * selection.  If the object supports multiple selections,
         * the specified item is bdded to bny existing selection, otherwise
         * it replbces bny existing selection in the object.  If the
         * specified item is blrebdy selected, this method hbs no effect.
         *
         * @pbrbm i the zero-bbsed index of selectbble items
         */
         public void bddAccessibleSelection(int i) {
             List.this.select(i);
         }

        /**
         * Removes the specified selected item in the object from the object's
         * selection.  If the specified item isn't currently selected, this
         * method hbs no effect.
         *
         * @pbrbm i the zero-bbsed index of selectbble items
         */
         public void removeAccessibleSelection(int i) {
             List.this.deselect(i);
         }

        /**
         * Clebrs the selection in the object, so thbt nothing in the
         * object is selected.
         */
         public void clebrAccessibleSelection() {
             synchronized(List.this)  {
                 int selectedIndexes[] = List.this.getSelectedIndexes();
                 if (selectedIndexes == null)
                     return;
                 for (int i = selectedIndexes.length - 1; i >= 0; i--) {
                     List.this.deselect(selectedIndexes[i]);
                 }
             }
         }

        /**
         * Cbuses every selected item in the object to be selected
         * if the object supports multiple selections.
         */
         public void selectAllAccessibleSelection() {
             synchronized(List.this)  {
                 for (int i = List.this.getItemCount() - 1; i >= 0; i--) {
                     List.this.select(i);
                 }
             }
         }

       /**
        * This clbss implements bccessibility support for
        * List children.  It provides bn implementbtion of the
        * Jbvb Accessibility API bppropribte to list children
        * user-interfbce elements.
        * @since 1.3
        */
        protected clbss AccessibleAWTListChild extends AccessibleAWTComponent
            implements Accessible
        {
            /*
             * JDK 1.3 seriblVersionUID
             */
            privbte stbtic finbl long seriblVersionUID = 4412022926028300317L;

        // [[[FIXME]]] need to finish implementing this!!!

            privbte List pbrent;
            privbte int  indexInPbrent;

            /**
             * Constructs new {@code AccessibleAWTListChild} with the given
             * pbrent {@code List} bnd 0-bbsed index of this object in the pbrent.
             *
             * @pbrbm  pbrent the pbrent {@code List}
             * @pbrbm  indexInPbrent the index in the pbrent
             */
            public AccessibleAWTListChild(List pbrent, int indexInPbrent)  {
                this.pbrent = pbrent;
                this.setAccessiblePbrent(pbrent);
                this.indexInPbrent = indexInPbrent;
            }

            //
            // required Accessible methods
            //
          /**
           * Gets the AccessibleContext for this object.  In the
           * implementbtion of the Jbvb Accessibility API for this clbss,
           * return this object, which bcts bs its own AccessibleContext.
           *
           * @return this object
           */
            public AccessibleContext getAccessibleContext() {
                return this;
            }

            //
            // required AccessibleContext methods
            //

            /**
             * Get the role of this object.
             *
             * @return bn instbnce of AccessibleRole describing the role of
             * the object
             * @see AccessibleRole
             */
            public AccessibleRole getAccessibleRole() {
                return AccessibleRole.LIST_ITEM;
            }

            /**
             * Get the stbte set of this object.  The AccessibleStbteSet of bn
             * object is composed of b set of unique AccessibleStbte's.  A
             * chbnge in the AccessibleStbteSet of bn object will cbuse b
             * PropertyChbngeEvent to be fired for the
             * ACCESSIBLE_STATE_PROPERTY property.
             *
             * @return bn instbnce of AccessibleStbteSet contbining the
             * current stbte set of the object
             * @see AccessibleStbteSet
             * @see AccessibleStbte
             * @see #bddPropertyChbngeListener
             */
            public AccessibleStbteSet getAccessibleStbteSet() {
                AccessibleStbteSet stbtes = super.getAccessibleStbteSet();
                if (pbrent.isIndexSelected(indexInPbrent)) {
                    stbtes.bdd(AccessibleStbte.SELECTED);
                }
                return stbtes;
            }

            /**
             * Gets the locble of the component. If the component does not
             * hbve b locble, then the locble of its pbrent is returned.
             *
             * @return This component's locble.  If this component does not hbve
             * b locble, the locble of its pbrent is returned.
             *
             * @exception IllegblComponentStbteException
             * If the Component does not hbve its own locble bnd hbs not yet
             * been bdded to b contbinment hierbrchy such thbt the locble cbn
             * be determined from the contbining pbrent.
             */
            public Locble getLocble() {
                return pbrent.getLocble();
            }

            /**
             * Get the 0-bbsed index of this object in its bccessible pbrent.
             *
             * @return the 0-bbsed index of this object in its pbrent; -1 if
             * this object does not hbve bn bccessible pbrent.
             *
             * @see #getAccessiblePbrent
             * @see #getAccessibleChildrenCount
             * @see #getAccessibleChild
             */
            public int getAccessibleIndexInPbrent() {
                return indexInPbrent;
            }

            /**
             * Returns the number of bccessible children of the object.
             *
             * @return the number of bccessible children of the object.
             */
            public int getAccessibleChildrenCount() {
                return 0;       // list elements cbn't hbve children
            }

            /**
             * Return the specified Accessible child of the object.  The
             * Accessible children of bn Accessible object bre zero-bbsed,
             * so the first child of bn Accessible child is bt index 0, the
             * second child is bt index 1, bnd so on.
             *
             * @pbrbm i zero-bbsed index of child
             * @return the Accessible child of the object
             * @see #getAccessibleChildrenCount
             */
            public Accessible getAccessibleChild(int i) {
                return null;    // list elements cbn't hbve children
            }


            //
            // AccessibleComponent delegbtbtion to pbrent List
            //

            /**
             * Get the bbckground color of this object.
             *
             * @return the bbckground color, if supported, of the object;
             * otherwise, null
             * @see #setBbckground
             */
            public Color getBbckground() {
                return pbrent.getBbckground();
            }

            /**
             * Set the bbckground color of this object.
             *
             * @pbrbm c the new Color for the bbckground
             * @see #setBbckground
             */
            public void setBbckground(Color c) {
                pbrent.setBbckground(c);
            }

            /**
             * Get the foreground color of this object.
             *
             * @return the foreground color, if supported, of the object;
             * otherwise, null
             * @see #setForeground
             */
            public Color getForeground() {
                return pbrent.getForeground();
            }

            /**
             * Set the foreground color of this object.
             *
             * @pbrbm c the new Color for the foreground
             * @see #getForeground
             */
            public void setForeground(Color c) {
                pbrent.setForeground(c);
            }

            /**
             * Get the Cursor of this object.
             *
             * @return the Cursor, if supported, of the object; otherwise, null
             * @see #setCursor
             */
            public Cursor getCursor() {
                return pbrent.getCursor();
            }

            /**
             * Set the Cursor of this object.
             * <p>
             * The method mby hbve no visubl effect if the Jbvb plbtform
             * implementbtion bnd/or the nbtive system do not support
             * chbnging the mouse cursor shbpe.
             * @pbrbm cursor the new Cursor for the object
             * @see #getCursor
             */
            public void setCursor(Cursor cursor) {
                pbrent.setCursor(cursor);
            }

            /**
             * Get the Font of this object.
             *
             * @return the Font,if supported, for the object; otherwise, null
             * @see #setFont
             */
            public Font getFont() {
                return pbrent.getFont();
            }

            /**
             * Set the Font of this object.
             *
             * @pbrbm f the new Font for the object
             * @see #getFont
             */
            public void setFont(Font f) {
                pbrent.setFont(f);
            }

            /**
             * Get the FontMetrics of this object.
             *
             * @pbrbm f the Font
             * @return the FontMetrics, if supported, the object; otherwise, null
             * @see #getFont
             */
            public FontMetrics getFontMetrics(Font f) {
                return pbrent.getFontMetrics(f);
            }

            /**
             * Determine if the object is enbbled.  Objects thbt bre enbbled
             * will blso hbve the AccessibleStbte.ENABLED stbte set in their
             * AccessibleStbteSet.
             *
             * @return true if object is enbbled; otherwise, fblse
             * @see #setEnbbled
             * @see AccessibleContext#getAccessibleStbteSet
             * @see AccessibleStbte#ENABLED
             * @see AccessibleStbteSet
             */
            public boolebn isEnbbled() {
                return pbrent.isEnbbled();
            }

            /**
             * Set the enbbled stbte of the object.
             *
             * @pbrbm b if true, enbbles this object; otherwise, disbbles it
             * @see #isEnbbled
             */
            public void setEnbbled(boolebn b) {
                pbrent.setEnbbled(b);
            }

            /**
             * Determine if the object is visible.  Note: this mebns thbt the
             * object intends to be visible; however, it mby not be
             * showing on the screen becbuse one of the objects thbt this object
             * is contbined by is currently not visible.  To determine if bn
             * object is showing on the screen, use isShowing().
             * <p>Objects thbt bre visible will blso hbve the
             * AccessibleStbte.VISIBLE stbte set in their AccessibleStbteSet.
             *
             * @return true if object is visible; otherwise, fblse
             * @see #setVisible
             * @see AccessibleContext#getAccessibleStbteSet
             * @see AccessibleStbte#VISIBLE
             * @see AccessibleStbteSet
             */
            public boolebn isVisible() {
                // [[[FIXME]]] needs to work like isShowing() below
                return fblse;
                // return pbrent.isVisible();
            }

            /**
             * Set the visible stbte of the object.
             *
             * @pbrbm b if true, shows this object; otherwise, hides it
             * @see #isVisible
             */
            public void setVisible(boolebn b) {
                // [[[FIXME]]] should scroll to item to mbke it show!
                pbrent.setVisible(b);
            }

            /**
             * Determine if the object is showing.  This is determined by
             * checking the visibility of the object bnd visibility of the
             * object bncestors.
             * Note: this will return true even if the object is obscured
             * by bnother (for exbmple, it to object is undernebth b menu
             * thbt wbs pulled down).
             *
             * @return true if object is showing; otherwise, fblse
             */
            public boolebn isShowing() {
                // [[[FIXME]]] only if it's showing!!!
                return fblse;
                // return pbrent.isShowing();
            }

            /**
             * Checks whether the specified point is within this object's
             * bounds, where the point's x bnd y coordinbtes bre defined to
             * be relbtive to the coordinbte system of the object.
             *
             * @pbrbm p the Point relbtive to the coordinbte system of the
             * object
             * @return true if object contbins Point; otherwise fblse
             * @see #getBounds
             */
            public boolebn contbins(Point p) {
                // [[[FIXME]]] - only if p is within the list element!!!
                return fblse;
                // return pbrent.contbins(p);
            }

            /**
             * Returns the locbtion of the object on the screen.
             *
             * @return locbtion of object on screen; null if this object
             * is not on the screen
             * @see #getBounds
             * @see #getLocbtion
             */
            public Point getLocbtionOnScreen() {
                // [[[FIXME]]] sigh
                return null;
            }

            /**
             * Gets the locbtion of the object relbtive to the pbrent in the
             * form of b point specifying the object's top-left corner in the
             * screen's coordinbte spbce.
             *
             * @return An instbnce of Point representing the top-left corner of
             * the objects's bounds in the coordinbte spbce of the screen; null
             * if this object or its pbrent bre not on the screen
             * @see #getBounds
             * @see #getLocbtionOnScreen
             */
            public Point getLocbtion() {
                // [[[FIXME]]]
                return null;
            }

            /**
             * Sets the locbtion of the object relbtive to the pbrent.
             * @pbrbm p the new position for the top-left corner
             * @see #getLocbtion
             */
            public void setLocbtion(Point p) {
                // [[[FIXME]]] mbybe - cbn simply return bs no-op
            }

            /**
             * Gets the bounds of this object in the form of b Rectbngle object.
             * The bounds specify this object's width, height, bnd locbtion
             * relbtive to its pbrent.
             *
             * @return A rectbngle indicbting this component's bounds; null if
             * this object is not on the screen.
             * @see #contbins
             */
            public Rectbngle getBounds() {
                // [[[FIXME]]]
                return null;
            }

            /**
             * Sets the bounds of this object in the form of b Rectbngle
             * object.  The bounds specify this object's width, height, bnd
             * locbtion relbtive to its pbrent.
             *
             * @pbrbm r rectbngle indicbting this component's bounds
             * @see #getBounds
             */
            public void setBounds(Rectbngle r) {
                // no-op; not supported
            }

            /**
             * Returns the size of this object in the form of b Dimension
             * object.  The height field of the Dimension object contbins this
             * objects's height, bnd the width field of the Dimension object
             * contbins this object's width.
             *
             * @return A Dimension object thbt indicbtes the size of this
             * component; null if this object is not on the screen
             * @see #setSize
             */
            public Dimension getSize() {
                // [[[FIXME]]]
                return null;
            }

            /**
             * Resizes this object so thbt it hbs width bnd height.
             *
             * @pbrbm d - The dimension specifying the new size of the object.
             * @see #getSize
             */
            public void setSize(Dimension d) {
                // not supported; no-op
            }

            /**
             * Returns the <code>Accessible</code> child, if one exists,
             * contbined bt the locbl coordinbte <code>Point</code>.
             *
             * @pbrbm p the point relbtive to the coordinbte system of this
             *     object
             * @return the <code>Accessible</code>, if it exists,
             *     bt the specified locbtion; otherwise <code>null</code>
             */
            public Accessible getAccessibleAt(Point p) {
                return null;    // object cbnnot hbve children!
            }

            /**
             * Returns whether this object cbn bccept focus or not.   Objects
             * thbt cbn bccept focus will blso hbve the
             * <code>AccessibleStbte.FOCUSABLE</code> stbte set in their
             * <code>AccessibleStbteSet</code>.
             *
             * @return true if object cbn bccept focus; otherwise fblse
             * @see AccessibleContext#getAccessibleStbteSet
             * @see AccessibleStbte#FOCUSABLE
             * @see AccessibleStbte#FOCUSED
             * @see AccessibleStbteSet
             */
            public boolebn isFocusTrbversbble() {
                return fblse;   // list element cbnnot receive focus!
            }

            /**
             * Requests focus for this object.  If this object cbnnot bccept
             * focus, nothing will hbppen.  Otherwise, the object will bttempt
             * to tbke focus.
             * @see #isFocusTrbversbble
             */
            public void requestFocus() {
                // nothing to do; b no-op
            }

            /**
             * Adds the specified focus listener to receive focus events from
             * this component.
             *
             * @pbrbm l the focus listener
             * @see #removeFocusListener
             */
            public void bddFocusListener(FocusListener l) {
                // nothing to do; b no-op
            }

            /**
             * Removes the specified focus listener so it no longer receives
             * focus events from this component.
             *
             * @pbrbm l the focus listener
             * @see #bddFocusListener
             */
            public void removeFocusListener(FocusListener l) {
                // nothing to do; b no-op
            }



        } // inner clbss AccessibleAWTListChild

    } // inner clbss AccessibleAWTList

}
