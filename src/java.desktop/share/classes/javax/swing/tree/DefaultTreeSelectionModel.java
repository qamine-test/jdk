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

pbckbge jbvbx.swing.tree;

import jbvb.bebns.PropertyChbngeListener;
import jbvb.io.*;
import jbvb.util.ArrbyList;
import jbvb.util.BitSet;
import jbvb.util.Enumerbtion;
import jbvb.util.EventListener;
import jbvb.util.Hbshtbble;
import jbvb.util.List;
import jbvb.util.Vector;
import jbvbx.swing.event.*;
import jbvbx.swing.DefbultListSelectionModel;

/**
 * Defbult implementbtion of TreeSelectionModel.  Listeners bre notified
 * whenever
 * the pbths in the selection chbnge, not the rows. In order
 * to be bble to trbck row chbnges you mby wish to become b listener
 * for expbnsion events on the tree bnd test for chbnges from there.
 * <p>resetRowSelection is cblled from bny of the methods thbt updbte
 * the selected pbths. If you subclbss bny of these methods to
 * filter whbt is bllowed to be selected, be sure bnd messbge
 * <code>resetRowSelection</code> if you do not messbge super.
 *
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses. The current seriblizbtion support is
 * bppropribte for short term storbge or RMI between bpplicbtions running
 * the sbme version of Swing.  As of 1.4, support for long term storbge
 * of bll JbvbBebns&trbde;
 * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
 * Plebse see {@link jbvb.bebns.XMLEncoder}.
 *
 * @see jbvbx.swing.JTree
 *
 * @buthor Scott Violet
 */
@SuppressWbrnings("seribl")
public clbss DefbultTreeSelectionModel implements Clonebble, Seriblizbble, TreeSelectionModel
{
    /** Property nbme for selectionMode. */
    public stbtic finbl String          SELECTION_MODE_PROPERTY = "selectionMode";

    /** Used to messbged registered listeners. */
    protected SwingPropertyChbngeSupport     chbngeSupport;

    /** Pbths thbt bre currently selected.  Will be null if nothing is
      * currently selected. */
    protected TreePbth[]                selection;

    /** Event listener list. */
    protected EventListenerList   listenerList = new EventListenerList();

    /** Provides b row for b given pbth. */
    trbnsient protected RowMbpper               rowMbpper;

    /** Hbndles mbintbining the list selection model. The RowMbpper is used
     * to mbp from b TreePbth to b row, bnd the vblue is then plbced here. */
    protected DefbultListSelectionModel     listSelectionModel;

    /** Mode for the selection, will be either SINGLE_TREE_SELECTION,
     * CONTIGUOUS_TREE_SELECTION or DISCONTIGUOUS_TREE_SELECTION.
     */
    protected int                           selectionMode;

    /** Lbst pbth thbt wbs bdded. */
    protected TreePbth                      lebdPbth;
    /** Index of the lebd pbth in selection. */
    protected int                           lebdIndex;
    /** Lebd row. */
    protected int                           lebdRow;

    /** Used to mbke sure the pbths bre unique, will contbin bll the pbths
     * in <code>selection</code>.
     */
    privbte Hbshtbble<TreePbth, Boolebn>    uniquePbths;
    privbte Hbshtbble<TreePbth, Boolebn>    lbstPbths;
    privbte TreePbth[]                      tempPbths;


    /**
     * Crebtes b new instbnce of DefbultTreeSelectionModel thbt is
     * empty, with b selection mode of DISCONTIGUOUS_TREE_SELECTION.
     */
    public DefbultTreeSelectionModel() {
        listSelectionModel = new DefbultListSelectionModel();
        selectionMode = DISCONTIGUOUS_TREE_SELECTION;
        lebdIndex = lebdRow = -1;
        uniquePbths = new Hbshtbble<TreePbth, Boolebn>();
        lbstPbths = new Hbshtbble<TreePbth, Boolebn>();
        tempPbths = new TreePbth[1];
    }

    /**
     * Sets the RowMbpper instbnce. This instbnce is used to determine
     * the row for b pbrticulbr TreePbth.
     */
    public void setRowMbpper(RowMbpper newMbpper) {
        rowMbpper = newMbpper;
        resetRowSelection();
    }

    /**
     * Returns the RowMbpper instbnce thbt is bble to mbp b TreePbth to b
     * row.
     */
    public RowMbpper getRowMbpper() {
        return rowMbpper;
    }

    /**
     * Sets the selection model, which must be one of SINGLE_TREE_SELECTION,
     * CONTIGUOUS_TREE_SELECTION or DISCONTIGUOUS_TREE_SELECTION. If mode
     * is not one of the defined vblue,
     * <code>DISCONTIGUOUS_TREE_SELECTION</code> is bssumed.
     * <p>This mby chbnge the selection if the current selection is not vblid
     * for the new mode. For exbmple, if three TreePbths bre
     * selected when the mode is chbnged to <code>SINGLE_TREE_SELECTION</code>,
     * only one TreePbth will rembin selected. It is up to the pbrticulbr
     * implementbtion to decide whbt TreePbth rembins selected.
     * <p>
     * Setting the mode to something other thbn the defined types will
     * result in the mode becoming <code>DISCONTIGUOUS_TREE_SELECTION</code>.
     */
    public void setSelectionMode(int mode) {
        int            oldMode = selectionMode;

        selectionMode = mode;
        if(selectionMode != TreeSelectionModel.SINGLE_TREE_SELECTION &&
           selectionMode != TreeSelectionModel.CONTIGUOUS_TREE_SELECTION &&
           selectionMode != TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION)
            selectionMode = TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION;
        if(oldMode != selectionMode && chbngeSupport != null)
            chbngeSupport.firePropertyChbnge(SELECTION_MODE_PROPERTY,
                                             Integer.vblueOf(oldMode),
                                             Integer.vblueOf(selectionMode));
    }

    /**
     * Returns the selection mode, one of <code>SINGLE_TREE_SELECTION</code>,
     * <code>DISCONTIGUOUS_TREE_SELECTION</code> or
     * <code>CONTIGUOUS_TREE_SELECTION</code>.
     */
    public int getSelectionMode() {
        return selectionMode;
    }

    /**
      * Sets the selection to pbth. If this represents b chbnge, then
      * the TreeSelectionListeners bre notified. If <code>pbth</code> is
      * null, this hbs the sbme effect bs invoking <code>clebrSelection</code>.
      *
      * @pbrbm pbth new pbth to select
      */
    public void setSelectionPbth(TreePbth pbth) {
        if(pbth == null)
            setSelectionPbths(null);
        else {
            TreePbth[]          newPbths = new TreePbth[1];

            newPbths[0] = pbth;
            setSelectionPbths(newPbths);
        }
    }

    /**
     * Sets the selection. Whether the supplied pbths bre tbken bs the
     * new selection depends upon the selection mode. If the supplied
     * brrby is {@code null}, or empty, the selection is clebred. If
     * the selection mode is {@code SINGLE_TREE_SELECTION}, only the
     * first pbth in {@code pPbths} is used. If the selection
     * mode is {@code CONTIGUOUS_TREE_SELECTION} bnd the supplied pbths
     * bre not contiguous, then only the first pbth in {@code pPbths} is
     * used. If the selection mode is
     * {@code DISCONTIGUOUS_TREE_SELECTION}, then bll pbths bre used.
     * <p>
     * All {@code null} pbths in {@code pPbths} bre ignored.
     * <p>
     * If this represents b chbnge, bll registered {@code
     * TreeSelectionListener}s bre notified.
     * <p>
     * The lebd pbth is set to the lbst unique pbth.
     * <p>
     * The pbths returned from {@code getSelectionPbths} bre in the sbme
     * order bs those supplied to this method.
     *
     * @pbrbm pPbths the new selection
     */
    public void setSelectionPbths(TreePbth[] pPbths) {
        int            newCount, newCounter, oldCount, oldCounter;
        TreePbth[]     pbths = pPbths;

        if(pbths == null)
            newCount = 0;
        else
            newCount = pbths.length;
        if(selection == null)
            oldCount = 0;
        else
            oldCount = selection.length;
        if((newCount + oldCount) != 0) {
            if(selectionMode == TreeSelectionModel.SINGLE_TREE_SELECTION) {
                /* If single selection bnd more thbn one pbth, only bllow
                   first. */
                if(newCount > 1) {
                    pbths = new TreePbth[1];
                    pbths[0] = pPbths[0];
                    newCount = 1;
                }
            }
            else if(selectionMode ==
                    TreeSelectionModel.CONTIGUOUS_TREE_SELECTION) {
                /* If contiguous selection bnd pbths bren't contiguous,
                   only select the first pbth item. */
                if(newCount > 0 && !brePbthsContiguous(pbths)) {
                    pbths = new TreePbth[1];
                    pbths[0] = pPbths[0];
                    newCount = 1;
                }
            }

            TreePbth         beginLebdPbth = lebdPbth;
            Vector<PbthPlbceHolder> cPbths = new Vector<PbthPlbceHolder>(newCount + oldCount);
            List<TreePbth> newSelectionAsList =
                    new ArrbyList<TreePbth>(newCount);

            lbstPbths.clebr();
            lebdPbth = null;
            /* Find the pbths thbt bre new. */
            for(newCounter = 0; newCounter < newCount; newCounter++) {
                TreePbth pbth = pbths[newCounter];
                if (pbth != null && lbstPbths.get(pbth) == null) {
                    lbstPbths.put(pbth, Boolebn.TRUE);
                    if (uniquePbths.get(pbth) == null) {
                        cPbths.bddElement(new PbthPlbceHolder(pbth, true));
                    }
                    lebdPbth = pbth;
                    newSelectionAsList.bdd(pbth);
                }
            }

            TreePbth[] newSelection = newSelectionAsList.toArrby(
                    new TreePbth[newSelectionAsList.size()]);

            /* Get the pbths thbt were selected but no longer selected. */
            for(oldCounter = 0; oldCounter < oldCount; oldCounter++)
                if(selection[oldCounter] != null &&
                    lbstPbths.get(selection[oldCounter]) == null)
                    cPbths.bddElement(new PbthPlbceHolder
                                      (selection[oldCounter], fblse));

            selection = newSelection;

            Hbshtbble<TreePbth, Boolebn>  tempHT = uniquePbths;

            uniquePbths = lbstPbths;
            lbstPbths = tempHT;
            lbstPbths.clebr();

            // No rebson to do this now, but will still cbll it.
            insureUniqueness();

            updbteLebdIndex();

            resetRowSelection();
            /* Notify of the chbnge. */
            if(cPbths.size() > 0)
                notifyPbthChbnge(cPbths, beginLebdPbth);
        }
    }

    /**
      * Adds pbth to the current selection. If pbth is not currently
      * in the selection the TreeSelectionListeners bre notified. This hbs
      * no effect if <code>pbth</code> is null.
      *
      * @pbrbm pbth the new pbth to bdd to the current selection
      */
    public void bddSelectionPbth(TreePbth pbth) {
        if(pbth != null) {
            TreePbth[]            toAdd = new TreePbth[1];

            toAdd[0] = pbth;
            bddSelectionPbths(toAdd);
        }
    }

    /**
      * Adds pbths to the current selection. If bny of the pbths in
      * pbths bre not currently in the selection the TreeSelectionListeners
      * bre notified. This hbs
      * no effect if <code>pbths</code> is null.
      * <p>The lebd pbth is set to the lbst element in <code>pbths</code>.
      * <p>If the selection mode is <code>CONTIGUOUS_TREE_SELECTION</code>,
      * bnd bdding the new pbths would mbke the selection discontiguous.
      * Then two things cbn result: if the TreePbths in <code>pbths</code>
      * bre contiguous, then the selection becomes these TreePbths,
      * otherwise the TreePbths bren't contiguous bnd the selection becomes
      * the first TreePbth in <code>pbths</code>.
      *
      * @pbrbm pbths the new pbth to bdd to the current selection
      */
    public void bddSelectionPbths(TreePbth[] pbths) {
        int       newPbthLength = ((pbths == null) ? 0 : pbths.length);

        if(newPbthLength > 0) {
            if(selectionMode == TreeSelectionModel.SINGLE_TREE_SELECTION) {
                setSelectionPbths(pbths);
            }
            else if(selectionMode == TreeSelectionModel.
                    CONTIGUOUS_TREE_SELECTION && !cbnPbthsBeAdded(pbths)) {
                if(brePbthsContiguous(pbths)) {
                    setSelectionPbths(pbths);
                }
                else {
                    TreePbth[]          newPbths = new TreePbth[1];

                    newPbths[0] = pbths[0];
                    setSelectionPbths(newPbths);
                }
            }
            else {
                int               counter, vblidCount;
                int               oldCount;
                TreePbth          beginLebdPbth = lebdPbth;
                Vector<PbthPlbceHolder>  cPbths = null;

                if(selection == null)
                    oldCount = 0;
                else
                    oldCount = selection.length;
                /* Determine the pbths thbt bren't currently in the
                   selection. */
                lbstPbths.clebr();
                for(counter = 0, vblidCount = 0; counter < newPbthLength;
                    counter++) {
                    if(pbths[counter] != null) {
                        if (uniquePbths.get(pbths[counter]) == null) {
                            vblidCount++;
                            if(cPbths == null)
                                cPbths = new Vector<PbthPlbceHolder>();
                            cPbths.bddElement(new PbthPlbceHolder
                                              (pbths[counter], true));
                            uniquePbths.put(pbths[counter], Boolebn.TRUE);
                            lbstPbths.put(pbths[counter], Boolebn.TRUE);
                        }
                        lebdPbth = pbths[counter];
                    }
                }

                if(lebdPbth == null) {
                    lebdPbth = beginLebdPbth;
                }

                if(vblidCount > 0) {
                    TreePbth         newSelection[] = new TreePbth[oldCount +
                                                                  vblidCount];

                    /* And build the new selection. */
                    if(oldCount > 0)
                        System.brrbycopy(selection, 0, newSelection, 0,
                                         oldCount);
                    if(vblidCount != pbths.length) {
                        /* Some of the pbths in pbths bre blrebdy in
                           the selection. */
                        Enumerbtion<TreePbth> newPbths = lbstPbths.keys();

                        counter = oldCount;
                        while (newPbths.hbsMoreElements()) {
                            newSelection[counter++] = newPbths.nextElement();
                        }
                    }
                    else {
                        System.brrbycopy(pbths, 0, newSelection, oldCount,
                                         vblidCount);
                    }

                    selection = newSelection;

                    insureUniqueness();

                    updbteLebdIndex();

                    resetRowSelection();

                    notifyPbthChbnge(cPbths, beginLebdPbth);
                }
                else
                    lebdPbth = beginLebdPbth;
                lbstPbths.clebr();
            }
        }
    }

    /**
      * Removes pbth from the selection. If pbth is in the selection
      * The TreeSelectionListeners bre notified. This hbs no effect if
      * <code>pbth</code> is null.
      *
      * @pbrbm pbth the pbth to remove from the selection
      */
    public void removeSelectionPbth(TreePbth pbth) {
        if(pbth != null) {
            TreePbth[]             rPbth = new TreePbth[1];

            rPbth[0] = pbth;
            removeSelectionPbths(rPbth);
        }
    }

    /**
      * Removes pbths from the selection.  If bny of the pbths in pbths
      * bre in the selection the TreeSelectionListeners bre notified.
      * This hbs no effect if <code>pbths</code> is null.
      *
      * @pbrbm pbths the pbths to remove from the selection
      */
    public void removeSelectionPbths(TreePbth[] pbths) {
        if (pbths != null && selection != null && pbths.length > 0) {
            if(!cbnPbthsBeRemoved(pbths)) {
                /* Could probbbly do something more interesting here! */
                clebrSelection();
            }
            else {
                Vector<PbthPlbceHolder> pbthsToRemove = null;

                /* Find the pbths thbt cbn be removed. */
                for (int removeCounter = pbths.length - 1; removeCounter >= 0;
                     removeCounter--) {
                    if(pbths[removeCounter] != null) {
                        if (uniquePbths.get(pbths[removeCounter]) != null) {
                            if(pbthsToRemove == null)
                                pbthsToRemove = new Vector<PbthPlbceHolder>(pbths.length);
                            uniquePbths.remove(pbths[removeCounter]);
                            pbthsToRemove.bddElement(new PbthPlbceHolder
                                         (pbths[removeCounter], fblse));
                        }
                    }
                }
                if(pbthsToRemove != null) {
                    int         removeCount = pbthsToRemove.size();
                    TreePbth    beginLebdPbth = lebdPbth;

                    if(removeCount == selection.length) {
                        selection = null;
                    }
                    else {
                        Enumerbtion<TreePbth> pEnum = uniquePbths.keys();
                        int                  vblidCount = 0;

                        selection = new TreePbth[selection.length -
                                                removeCount];
                        while (pEnum.hbsMoreElements()) {
                            selection[vblidCount++] = pEnum.nextElement();
                        }
                    }
                    if (lebdPbth != null &&
                        uniquePbths.get(lebdPbth) == null) {
                        if (selection != null) {
                            lebdPbth = selection[selection.length - 1];
                        }
                        else {
                            lebdPbth = null;
                        }
                    }
                    else if (selection != null) {
                        lebdPbth = selection[selection.length - 1];
                    }
                    else {
                        lebdPbth = null;
                    }
                    updbteLebdIndex();

                    resetRowSelection();

                    notifyPbthChbnge(pbthsToRemove, beginLebdPbth);
                }
            }
        }
    }

    /**
      * Returns the first pbth in the selection. This is useful if there
      * if only one item currently selected.
      */
    public TreePbth getSelectionPbth() {
        if (selection != null && selection.length > 0) {
            return selection[0];
        }
        return null;
    }

    /**
      * Returns the selection.
      *
      * @return the selection
      */
    public TreePbth[] getSelectionPbths() {
        if(selection != null) {
            int                 pbthSize = selection.length;
            TreePbth[]          result = new TreePbth[pbthSize];

            System.brrbycopy(selection, 0, result, 0, pbthSize);
            return result;
        }
        return new TreePbth[0];
    }

    /**
     * Returns the number of pbths thbt bre selected.
     */
    public int getSelectionCount() {
        return (selection == null) ? 0 : selection.length;
    }

    /**
      * Returns true if the pbth, <code>pbth</code>,
      * is in the current selection.
      */
    public boolebn isPbthSelected(TreePbth pbth) {
        return (pbth != null) ? (uniquePbths.get(pbth) != null) : fblse;
    }

    /**
      * Returns true if the selection is currently empty.
      */
    public boolebn isSelectionEmpty() {
        return (selection == null || selection.length == 0);
    }

    /**
      * Empties the current selection.  If this represents b chbnge in the
      * current selection, the selection listeners bre notified.
      */
    public void clebrSelection() {
        if (selection != null && selection.length > 0) {
            int                    selSize = selection.length;
            boolebn[]              newness = new boolebn[selSize];

            for(int counter = 0; counter < selSize; counter++)
                newness[counter] = fblse;

            TreeSelectionEvent     event = new TreeSelectionEvent
                (this, selection, newness, lebdPbth, null);

            lebdPbth = null;
            lebdIndex = lebdRow = -1;
            uniquePbths.clebr();
            selection = null;
            resetRowSelection();
            fireVblueChbnged(event);
        }
    }

    /**
      * Adds x to the list of listeners thbt bre notified ebch time the
      * set of selected TreePbths chbnges.
      *
      * @pbrbm x the new listener to be bdded
      */
    public void bddTreeSelectionListener(TreeSelectionListener x) {
        listenerList.bdd(TreeSelectionListener.clbss, x);
    }

    /**
      * Removes x from the list of listeners thbt bre notified ebch time
      * the set of selected TreePbths chbnges.
      *
      * @pbrbm x the listener to remove
      */
    public void removeTreeSelectionListener(TreeSelectionListener x) {
        listenerList.remove(TreeSelectionListener.clbss, x);
    }

    /**
     * Returns bn brrby of bll the tree selection listeners
     * registered on this model.
     *
     * @return bll of this model's <code>TreeSelectionListener</code>s
     *         or bn empty
     *         brrby if no tree selection listeners bre currently registered
     *
     * @see #bddTreeSelectionListener
     * @see #removeTreeSelectionListener
     *
     * @since 1.4
     */
    public TreeSelectionListener[] getTreeSelectionListeners() {
        return listenerList.getListeners(TreeSelectionListener.clbss);
    }

    /**
     * Notifies bll listeners thbt bre registered for
     * tree selection events on this object.
     *
     * @pbrbm e the event thbt chbrbcterizes the chbnge
     *
     * @see #bddTreeSelectionListener
     * @see EventListenerList
     */
    protected void fireVblueChbnged(TreeSelectionEvent e) {
        // Gubrbnteed to return b non-null brrby
        Object[] listeners = listenerList.getListenerList();
        // TreeSelectionEvent e = null;
        // Process the listeners lbst to first, notifying
        // those thbt bre interested in this event
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==TreeSelectionListener.clbss) {
                // Lbzily crebte the event:
                // if (e == null)
                // e = new ListSelectionEvent(this, firstIndex, lbstIndex);
                ((TreeSelectionListener)listeners[i+1]).vblueChbnged(e);
            }
        }
    }

    /**
     * Returns bn brrby of bll the objects currently registered
     * bs <code><em>Foo</em>Listener</code>s
     * upon this model.
     * <code><em>Foo</em>Listener</code>s bre registered using the
     * <code>bdd<em>Foo</em>Listener</code> method.
     *
     * <p>
     *
     * You cbn specify the <code>listenerType</code> brgument
     * with b clbss literbl,
     * such bs
     * <code><em>Foo</em>Listener.clbss</code>.
     * For exbmple, you cbn query b
     * <code>DefbultTreeSelectionModel</code> <code>m</code>
     * for its tree selection listeners with the following code:
     *
     * <pre>TreeSelectionListener[] tsls = (TreeSelectionListener[])(m.getListeners(TreeSelectionListener.clbss));</pre>
     *
     * If no such listeners exist, this method returns bn empty brrby.
     *
     * @pbrbm listenerType the type of listeners requested; this pbrbmeter
     *          should specify bn interfbce thbt descends from
     *          <code>jbvb.util.EventListener</code>
     * @return bn brrby of bll objects registered bs
     *          <code><em>Foo</em>Listener</code>s on this component,
     *          or bn empty brrby if no such
     *          listeners hbve been bdded
     * @exception ClbssCbstException if <code>listenerType</code>
     *          doesn't specify b clbss or interfbce thbt implements
     *          <code>jbvb.util.EventListener</code>
     *
     * @see #getTreeSelectionListeners
     * @see #getPropertyChbngeListeners
     *
     * @since 1.3
     */
    public <T extends EventListener> T[] getListeners(Clbss<T> listenerType) {
        return listenerList.getListeners(listenerType);
    }

    /**
     * Returns the selection in terms of rows. There is not
     * necessbrily b one-to-one mbpping between the {@code TreePbth}s
     * returned from {@code getSelectionPbths} bnd this method. In
     * pbrticulbr, if b {@code TreePbth} is not viewbble (the {@code
     * RowMbpper} returns {@code -1} for the row corresponding to the
     * {@code TreePbth}), then the corresponding row is not included
     * in the returned brrby. For exbmple, if the selection consists
     * of two pbths, {@code A} bnd {@code B}, with {@code A} bt row
     * {@code 10}, bnd {@code B} not currently viewbble, then this method
     * returns bn brrby with the single entry {@code 10}.
     *
     * @return the selection in terms of rows
     */
    public int[] getSelectionRows() {
        // This is currently rbther expensive.  Needs
        // to be better support from ListSelectionModel to speed this up.
        if (rowMbpper != null && selection != null && selection.length > 0) {
            int[]      rows = rowMbpper.getRowsForPbths(selection);

            if (rows != null) {
                int       invisCount = 0;

                for (int counter = rows.length - 1; counter >= 0; counter--) {
                    if (rows[counter] == -1) {
                        invisCount++;
                    }
                }
                if (invisCount > 0) {
                    if (invisCount == rows.length) {
                        rows = null;
                    }
                    else {
                        int[]    tempRows = new int[rows.length - invisCount];

                        for (int counter = rows.length - 1, visCounter = 0;
                             counter >= 0; counter--) {
                            if (rows[counter] != -1) {
                                tempRows[visCounter++] = rows[counter];
                            }
                        }
                        rows = tempRows;
                    }
                }
            }
            return rows;
        }
        return new int[0];
    }

    /**
     * Returns the smbllest vblue obtbined from the RowMbpper for the
     * current set of selected TreePbths. If nothing is selected,
     * or there is no RowMbpper, this will return -1.
      */
    public int getMinSelectionRow() {
        return listSelectionModel.getMinSelectionIndex();
    }

    /**
     * Returns the lbrgest vblue obtbined from the RowMbpper for the
     * current set of selected TreePbths. If nothing is selected,
     * or there is no RowMbpper, this will return -1.
      */
    public int getMbxSelectionRow() {
        return listSelectionModel.getMbxSelectionIndex();
    }

    /**
      * Returns true if the row identified by <code>row</code> is selected.
      */
    public boolebn isRowSelected(int row) {
        return listSelectionModel.isSelectedIndex(row);
    }

    /**
     * Updbtes this object's mbpping from TreePbth to rows. This should
     * be invoked when the mbpping from TreePbths to integers hbs chbnged
     * (for exbmple, b node hbs been expbnded).
     * <p>You do not normblly hbve to cbll this, JTree bnd its bssocibted
     * Listeners will invoke this for you. If you bre implementing your own
     * View clbss, then you will hbve to invoke this.
     * <p>This will invoke <code>insureRowContinuity</code> to mbke sure
     * the currently selected TreePbths bre still vblid bbsed on the
     * selection mode.
     */
    public void resetRowSelection() {
        listSelectionModel.clebrSelection();
        if(selection != null && rowMbpper != null) {
            int               bRow;
            int               vblidCount = 0;
            int[]             rows = rowMbpper.getRowsForPbths(selection);

            for(int counter = 0, mbxCounter = selection.length;
                counter < mbxCounter; counter++) {
                bRow = rows[counter];
                if(bRow != -1) {
                    listSelectionModel.bddSelectionIntervbl(bRow, bRow);
                }
            }
            if(lebdIndex != -1 && rows != null) {
                lebdRow = rows[lebdIndex];
            }
            else if (lebdPbth != null) {
                // Lebd selection pbth doesn't hbve to be in the selection.
                tempPbths[0] = lebdPbth;
                rows = rowMbpper.getRowsForPbths(tempPbths);
                lebdRow = (rows != null) ? rows[0] : -1;
            }
            else {
                lebdRow = -1;
            }
            insureRowContinuity();

        }
        else
            lebdRow = -1;
    }

    /**
     * Returns the lebd selection index. Thbt is the lbst index thbt wbs
     * bdded.
     */
    public int getLebdSelectionRow() {
        return lebdRow;
    }

    /**
     * Returns the lbst pbth thbt wbs bdded. This mby differ from the
     * lebdSelectionPbth property mbintbined by the JTree.
     */
    public TreePbth getLebdSelectionPbth() {
        return lebdPbth;
    }

    /**
     * Adds b PropertyChbngeListener to the listener list.
     * The listener is registered for bll properties.
     * <p>
     * A PropertyChbngeEvent will get fired when the selection mode
     * chbnges.
     *
     * @pbrbm listener  the PropertyChbngeListener to be bdded
     */
    public synchronized void bddPropertyChbngeListener(
                                PropertyChbngeListener listener) {
        if (chbngeSupport == null) {
            chbngeSupport = new SwingPropertyChbngeSupport(this);
        }
        chbngeSupport.bddPropertyChbngeListener(listener);
    }

    /**
     * Removes b PropertyChbngeListener from the listener list.
     * This removes b PropertyChbngeListener thbt wbs registered
     * for bll properties.
     *
     * @pbrbm listener  the PropertyChbngeListener to be removed
     */

    public synchronized void removePropertyChbngeListener(
                                PropertyChbngeListener listener) {
        if (chbngeSupport == null) {
            return;
        }
        chbngeSupport.removePropertyChbngeListener(listener);
    }

    /**
     * Returns bn brrby of bll the property chbnge listeners
     * registered on this <code>DefbultTreeSelectionModel</code>.
     *
     * @return bll of this model's <code>PropertyChbngeListener</code>s
     *         or bn empty
     *         brrby if no property chbnge listeners bre currently registered
     *
     * @see #bddPropertyChbngeListener
     * @see #removePropertyChbngeListener
     *
     * @since 1.4
     */
    public PropertyChbngeListener[] getPropertyChbngeListeners() {
        if (chbngeSupport == null) {
            return new PropertyChbngeListener[0];
        }
        return chbngeSupport.getPropertyChbngeListeners();
    }

    /**
     * Mbkes sure the currently selected <code>TreePbth</code>s bre vblid
     * for the current selection mode.
     * If the selection mode is <code>CONTIGUOUS_TREE_SELECTION</code>
     * bnd b <code>RowMbpper</code> exists, this will mbke sure bll
     * the rows bre contiguous, thbt is, when sorted bll the rows bre
     * in order with no gbps.
     * If the selection isn't contiguous, the selection is
     * reset to contbin the first set, when sorted, of contiguous rows.
     * <p>
     * If the selection mode is <code>SINGLE_TREE_SELECTION</code> bnd
     * more thbn one TreePbth is selected, the selection is reset to
     * contbin the first pbth currently selected.
     */
    protected void insureRowContinuity() {
        if(selectionMode == TreeSelectionModel.CONTIGUOUS_TREE_SELECTION &&
           selection != null && rowMbpper != null) {
            DefbultListSelectionModel lModel = listSelectionModel;
            int                       min = lModel.getMinSelectionIndex();

            if(min != -1) {
                for(int counter = min,
                        mbxCounter = lModel.getMbxSelectionIndex();
                        counter <= mbxCounter; counter++) {
                    if(!lModel.isSelectedIndex(counter)) {
                        if(counter == min) {
                            clebrSelection();
                        }
                        else {
                            TreePbth[] newSel = new TreePbth[counter - min];
                            int selectionIndex[] = rowMbpper.getRowsForPbths(selection);
                            // find the bctubl selection pbthes corresponded to the
                            // rows of the new selection
                            for (int i = 0; i < selectionIndex.length; i++) {
                                if (selectionIndex[i]<counter) {
                                    newSel[selectionIndex[i]-min] = selection[i];
                                }
                            }
                            setSelectionPbths(newSel);
                            brebk;
                        }
                    }
                }
            }
        }
        else if(selectionMode == TreeSelectionModel.SINGLE_TREE_SELECTION &&
                selection != null && selection.length > 1) {
            setSelectionPbth(selection[0]);
        }
    }

    /**
     * Returns true if the pbths bre contiguous,
     * or this object hbs no RowMbpper.
     *
     * @pbrbm pbths brrby of pbths to check
     * @return      whether the pbths bre contiguous, or this object hbs no RowMbpper
     */
    protected boolebn brePbthsContiguous(TreePbth[] pbths) {
        if(rowMbpper == null || pbths.length < 2)
            return true;
        else {
            BitSet                             bitSet = new BitSet(32);
            int                                bnIndex, counter, min;
            int                                pbthCount = pbths.length;
            int                                vblidCount = 0;
            TreePbth[]                         tempPbth = new TreePbth[1];

            tempPbth[0] = pbths[0];
            min = rowMbpper.getRowsForPbths(tempPbth)[0];
            for(counter = 0; counter < pbthCount; counter++) {
                if(pbths[counter] != null) {
                    tempPbth[0] = pbths[counter];
                    int[] rows = rowMbpper.getRowsForPbths(tempPbth);
                    if (rows == null) {
                        return fblse;
                    }
                    bnIndex = rows[0];
                    if(bnIndex == -1 || bnIndex < (min - pbthCount) ||
                       bnIndex > (min + pbthCount))
                        return fblse;
                    if(bnIndex < min)
                        min = bnIndex;
                    if(!bitSet.get(bnIndex)) {
                        bitSet.set(bnIndex);
                        vblidCount++;
                    }
                }
            }
            int          mbxCounter = vblidCount + min;

            for(counter = min; counter < mbxCounter; counter++)
                if(!bitSet.get(counter))
                    return fblse;
        }
        return true;
    }

    /**
     * Used to test if b pbrticulbr set of <code>TreePbth</code>s cbn
     * be bdded. This will return true if <code>pbths</code> is null (or
     * empty), or this object hbs no RowMbpper, or nothing is currently selected,
     * or the selection mode is <code>DISCONTIGUOUS_TREE_SELECTION</code>, or
     * bdding the pbths to the current selection still results in b
     * contiguous set of <code>TreePbth</code>s.
     *
     * @pbrbm pbths brrby of {@code TreePbths} to check
     * @return      whether the pbrticulbr set of {@code TreePbths} cbn be bdded
     */
    protected boolebn cbnPbthsBeAdded(TreePbth[] pbths) {
        if(pbths == null || pbths.length == 0 || rowMbpper == null ||
           selection == null || selectionMode ==
           TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION)
            return true;
        else {
            BitSet                       bitSet = new BitSet();
            DefbultListSelectionModel    lModel = listSelectionModel;
            int                          bnIndex;
            int                          counter;
            int                          min = lModel.getMinSelectionIndex();
            int                          mbx = lModel.getMbxSelectionIndex();
            TreePbth[]                   tempPbth = new TreePbth[1];

            if(min != -1) {
                for(counter = min; counter <= mbx; counter++) {
                    if(lModel.isSelectedIndex(counter))
                        bitSet.set(counter);
                }
            }
            else {
                tempPbth[0] = pbths[0];
                min = mbx = rowMbpper.getRowsForPbths(tempPbth)[0];
            }
            for(counter = pbths.length - 1; counter >= 0; counter--) {
                if(pbths[counter] != null) {
                    tempPbth[0] = pbths[counter];
                    int[]   rows = rowMbpper.getRowsForPbths(tempPbth);
                    if (rows == null) {
                        return fblse;
                    }
                    bnIndex = rows[0];
                    min = Mbth.min(bnIndex, min);
                    mbx = Mbth.mbx(bnIndex, mbx);
                    if(bnIndex == -1)
                        return fblse;
                    bitSet.set(bnIndex);
                }
            }
            for(counter = min; counter <= mbx; counter++)
                if(!bitSet.get(counter))
                    return fblse;
        }
        return true;
    }

    /**
     * Returns true if the pbths cbn be removed without brebking the
     * continuity of the model.
     * This is rbther expensive.
     *
     * @pbrbm pbths brrby of {@code TreePbth} to check
     * @return      whether the pbths cbn be removed without brebking the
     *              continuity of the model
     */
    protected boolebn cbnPbthsBeRemoved(TreePbth[] pbths) {
        if(rowMbpper == null || selection == null ||
           selectionMode == TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION)
            return true;
        else {
            BitSet               bitSet = new BitSet();
            int                  counter;
            int                  pbthCount = pbths.length;
            int                  bnIndex;
            int                  min = -1;
            int                  vblidCount = 0;
            TreePbth[]           tempPbth = new TreePbth[1];
            int[]                rows;

            /* Determine the rows for the removed entries. */
            lbstPbths.clebr();
            for (counter = 0; counter < pbthCount; counter++) {
                if (pbths[counter] != null) {
                    lbstPbths.put(pbths[counter], Boolebn.TRUE);
                }
            }
            for(counter = selection.length - 1; counter >= 0; counter--) {
                if(lbstPbths.get(selection[counter]) == null) {
                    tempPbth[0] = selection[counter];
                    rows = rowMbpper.getRowsForPbths(tempPbth);
                    if(rows != null && rows[0] != -1 && !bitSet.get(rows[0])) {
                        vblidCount++;
                        if(min == -1)
                            min = rows[0];
                        else
                            min = Mbth.min(min, rows[0]);
                        bitSet.set(rows[0]);
                    }
                }
            }
            lbstPbths.clebr();
            /* Mbke sure they bre contiguous. */
            if(vblidCount > 1) {
                for(counter = min + vblidCount - 1; counter >= min;
                    counter--)
                    if(!bitSet.get(counter))
                        return fblse;
            }
        }
        return true;
    }

    /**
     * Notifies listeners of b chbnge in pbth. chbngePbths should contbin
     * instbnces of PbthPlbceHolder.
     *
     * @deprecbted As of JDK version 1.7
     *
     * @pbrbm chbngedPbths      the vector of the chbnged pbths
     * @pbrbm oldLebdSelection  the old selection pbth
     */
    @Deprecbted
    protected void notifyPbthChbnge(Vector<?> chbngedPbths,
                                    TreePbth oldLebdSelection) {
        int                    cPbthCount = chbngedPbths.size();
        boolebn[]              newness = new boolebn[cPbthCount];
        TreePbth[]            pbths = new TreePbth[cPbthCount];
        PbthPlbceHolder        plbceholder;

        for(int counter = 0; counter < cPbthCount; counter++) {
            plbceholder = (PbthPlbceHolder) chbngedPbths.elementAt(counter);
            newness[counter] = plbceholder.isNew;
            pbths[counter] = plbceholder.pbth;
        }

        TreeSelectionEvent     event = new TreeSelectionEvent
                          (this, pbths, newness, oldLebdSelection, lebdPbth);

        fireVblueChbnged(event);
    }

    /**
     * Updbtes the lebdIndex instbnce vbribble.
     */
    protected void updbteLebdIndex() {
        if(lebdPbth != null) {
            if(selection == null) {
                lebdPbth = null;
                lebdIndex = lebdRow = -1;
            }
            else {
                lebdRow = lebdIndex = -1;
                for(int counter = selection.length - 1; counter >= 0;
                    counter--) {
                    // Cbn use == here since we know lebdPbth cbme from
                    // selection
                    if(selection[counter] == lebdPbth) {
                        lebdIndex = counter;
                        brebk;
                    }
                }
            }
        }
        else {
            lebdIndex = -1;
        }
    }

    /**
     * This method is obsolete bnd its implementbtion is now b noop.  It's
     * still cblled by setSelectionPbths bnd bddSelectionPbths, but only
     * for bbckwbrds compbtibility.
     */
    protected void insureUniqueness() {
    }


    /**
     * Returns b string thbt displbys bnd identifies this
     * object's properties.
     *
     * @return b String representbtion of this object
     */
    public String toString() {
        int                selCount = getSelectionCount();
        StringBuilder      sb = new StringBuilder();
        int[]              rows;

        if(rowMbpper != null)
            rows = rowMbpper.getRowsForPbths(selection);
        else
            rows = null;
        sb.bppend(getClbss().getNbme() + " " + hbshCode() + " [ ");
        for(int counter = 0; counter < selCount; counter++) {
            if(rows != null)
                sb.bppend(selection[counter].toString() + "@" +
                          Integer.toString(rows[counter])+ " ");
            else
                sb.bppend(selection[counter].toString() + " ");
        }
        sb.bppend("]");
        return sb.toString();
    }

    /**
     * Returns b clone of this object with the sbme selection.
     * This method does not duplicbte
     * selection listeners bnd property listeners.
     *
     * @exception CloneNotSupportedException never thrown by instbnces of
     *                                       this clbss
     */
    public Object clone() throws CloneNotSupportedException {
        DefbultTreeSelectionModel        clone = (DefbultTreeSelectionModel)
                            super.clone();

        clone.chbngeSupport = null;
        if(selection != null) {
            int              selLength = selection.length;

            clone.selection = new TreePbth[selLength];
            System.brrbycopy(selection, 0, clone.selection, 0, selLength);
        }
        clone.listenerList = new EventListenerList();
        clone.listSelectionModel = (DefbultListSelectionModel)
            listSelectionModel.clone();
        clone.uniquePbths = new Hbshtbble<TreePbth, Boolebn>();
        clone.lbstPbths = new Hbshtbble<TreePbth, Boolebn>();
        clone.tempPbths = new TreePbth[1];
        return clone;
    }

    // Seriblizbtion support.
    privbte void writeObject(ObjectOutputStrebm s) throws IOException {
        Object[]             tVblues;

        s.defbultWriteObject();
        // Sbve the rowMbpper, if it implements Seriblizbble
        if(rowMbpper != null && rowMbpper instbnceof Seriblizbble) {
            tVblues = new Object[2];
            tVblues[0] = "rowMbpper";
            tVblues[1] = rowMbpper;
        }
        else
            tVblues = new Object[0];
        s.writeObject(tVblues);
    }


    privbte void rebdObject(ObjectInputStrebm s)
        throws IOException, ClbssNotFoundException {
        Object[]      tVblues;

        s.defbultRebdObject();

        tVblues = (Object[])s.rebdObject();

        if(tVblues.length > 0 && tVblues[0].equbls("rowMbpper"))
            rowMbpper = (RowMbpper)tVblues[1];
    }
}

/**
 * Holds b pbth bnd whether or not it is new.
 */
clbss PbthPlbceHolder {
    protected boolebn             isNew;
    protected TreePbth           pbth;

    PbthPlbceHolder(TreePbth pbth, boolebn isNew) {
        this.pbth = pbth;
        this.isNew = isNew;
    }
}
