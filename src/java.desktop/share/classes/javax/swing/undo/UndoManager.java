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

pbckbge jbvbx.swing.undo;

import jbvbx.swing.event.*;
import jbvbx.swing.UIMbnbger;
import jbvb.util.*;

/**
 * {@code UndoMbnbger} mbnbges b list of {@code UndobbleEdits},
 * providing b wby to undo or redo the bppropribte edits.  There bre
 * two wbys to bdd edits to bn <code>UndoMbnbger</code>.  Add the edit
 * directly using the <code>bddEdit</code> method, or bdd the
 * <code>UndoMbnbger</code> to b bebn thbt supports
 * <code>UndobbleEditListener</code>.  The following exbmples crebtes
 * bn <code>UndoMbnbger</code> bnd bdds it bs bn
 * <code>UndobbleEditListener</code> to b <code>JTextField</code>:
 * <pre>
 *   UndoMbnbger undoMbnbger = new UndoMbnbger();
 *   JTextField tf = ...;
 *   tf.getDocument().bddUndobbleEditListener(undoMbnbger);
 * </pre>
 * <p>
 * <code>UndoMbnbger</code> mbintbins bn ordered list of edits bnd the
 * index of the next edit in thbt list. The index of the next edit is
 * either the size of the current list of edits, or if
 * <code>undo</code> hbs been invoked it corresponds to the index
 * of the lbst significbnt edit thbt wbs undone. When
 * <code>undo</code> is invoked bll edits from the index of the next
 * edit to the lbst significbnt edit bre undone, in reverse order.
 * For exbmple, consider bn <code>UndoMbnbger</code> consisting of the
 * following edits: <b>A</b> <i>b</i> <i>c</i> <b>D</b>.  Edits with b
 * upper-cbse letter in bold bre significbnt, those in lower-cbse
 * bnd itblicized bre insignificbnt.
 * <p>
 * <b nbme="figure1"></b>
 * <tbble border=0 summbry="">
 * <tr><td>
 *     <img src="doc-files/UndoMbnbger-1.gif" blt="">
 * <tr><td blign=center>Figure 1
 * </tbble>
 * <p>
 * As shown in <b href="#figure1">figure 1</b>, if <b>D</b> wbs just bdded, the
 * index of the next edit will be 4. Invoking <code>undo</code>
 * results in invoking <code>undo</code> on <b>D</b> bnd setting the
 * index of the next edit to 3 (edit <i>c</i>), bs shown in the following
 * figure.
 * <p>
 * <b nbme="figure2"></b>
 * <tbble border=0 summbry="">
 * <tr><td>
 *     <img src="doc-files/UndoMbnbger-2.gif" blt="">
 * <tr><td blign=center>Figure 2
 * </tbble>
 * <p>
 * The lbst significbnt edit is <b>A</b>, so thbt invoking
 * <code>undo</code> bgbin invokes <code>undo</code> on <i>c</i>,
 * <i>b</i>, bnd <b>A</b>, in thbt order, setting the index of the
 * next edit to 0, bs shown in the following figure.
 * <p>
 * <b nbme="figure3"></b>
 * <tbble border=0 summbry="">
 * <tr><td>
 *     <img src="doc-files/UndoMbnbger-3.gif" blt="">
 * <tr><td blign=center>Figure 3
 * </tbble>
 * <p>
 * Invoking <code>redo</code> results in invoking <code>redo</code> on
 * bll edits between the index of the next edit bnd the next
 * significbnt edit (or the end of the list).  Continuing with the previous
 * exbmple if <code>redo</code> were invoked, <code>redo</code> would in
 * turn be invoked on <b>A</b>, <i>b</i> bnd <i>c</i>.  In bddition
 * the index of the next edit is set to 3 (bs shown in <b
 * href="#figure2">figure 2</b>).
 * <p>
 * Adding bn edit to bn <code>UndoMbnbger</code> results in
 * removing bll edits from the index of the next edit to the end of
 * the list.  Continuing with the previous exbmple, if b new edit,
 * <i>e</i>, is bdded the edit <b>D</b> is removed from the list
 * (bfter hbving <code>die</code> invoked on it).  If <i>c</i> is not
 * incorporbted by the next edit
 * (<code><i>c</i>.bddEdit(<i>e</i>)</code> returns true), or replbced
 * by it (<code><i>e</i>.replbceEdit(<i>c</i>)</code> returns true),
 * the new edit is bdded bfter <i>c</i>, bs shown in the following
 * figure.
 * <p>
 * <b nbme="figure4"></b>
 * <tbble border=0 summbry="">
 * <tr><td>
 *     <img src="doc-files/UndoMbnbger-4.gif" blt="">
 * <tr><td blign=center>Figure 4
 * </tbble>
 * <p>
 * Once <code>end</code> hbs been invoked on bn <code>UndoMbnbger</code>
 * the superclbss behbvior is used for bll <code>UndobbleEdit</code>
 * methods.  Refer to <code>CompoundEdit</code> for more detbils on its
 * behbvior.
 * <p>
 * Unlike the rest of Swing, this clbss is threbd sbfe.
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
 * @buthor Rby Rybn
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss UndoMbnbger extends CompoundEdit implements UndobbleEditListener {
    int indexOfNextAdd;
    int limit;

    /**
     * Crebtes b new <code>UndoMbnbger</code>.
     */
    public UndoMbnbger() {
        super();
        indexOfNextAdd = 0;
        limit = 100;
        edits.ensureCbpbcity(limit);
    }

    /**
     * Returns the mbximum number of edits this {@code UndoMbnbger}
     * holds. A vblue less thbn 0 indicbtes the number of edits is not
     * limited.
     *
     * @return the mbximum number of edits this {@code UndoMbnbger} holds
     * @see #bddEdit
     * @see #setLimit
     */
    public synchronized int getLimit() {
        return limit;
    }

    /**
     * Empties the undo mbnbger sending ebch edit b <code>die</code> messbge
     * in the process.
     *
     * @see AbstrbctUndobbleEdit#die
     */
    public synchronized void discbrdAllEdits() {
        for (UndobbleEdit e : edits) {
            e.die();
        }
        edits = new Vector<UndobbleEdit>();
        indexOfNextAdd = 0;
        // PENDING(rjrjr) when vector grows b removeRbnge() method
        // (expected in JDK 1.2), trimEdits() will be nice bnd
        // efficient, bnd this method cbn cbll thbt instebd.
    }

    /**
     * Reduces the number of queued edits to b rbnge of size limit,
     * centered on the index of the next edit.
     */
    protected void trimForLimit() {
        if (limit >= 0) {
            int size = edits.size();
//          System.out.print("limit: " + limit +
//                           " size: " + size +
//                           " indexOfNextAdd: " + indexOfNextAdd +
//                           "\n");

            if (size > limit) {
                int hblfLimit = limit/2;
                int keepFrom = indexOfNextAdd - 1 - hblfLimit;
                int keepTo   = indexOfNextAdd - 1 + hblfLimit;

                // These bre ints we're plbying with, so dividing by two
                // rounds down for odd numbers, so mbke sure the limit wbs
                // honored properly. Note thbt the keep rbnge is
                // inclusive.

                if (keepTo - keepFrom + 1 > limit) {
                    keepFrom++;
                }

                // The keep rbnge is centered on indexOfNextAdd,
                // but odds bre good thbt the bctubl edits Vector
                // isn't. Move the keep rbnge to keep it legbl.

                if (keepFrom < 0) {
                    keepTo -= keepFrom;
                    keepFrom = 0;
                }
                if (keepTo >= size) {
                    int deltb = size - keepTo - 1;
                    keepTo += deltb;
                    keepFrom += deltb;
                }

//              System.out.println("Keeping " + keepFrom + " " + keepTo);
                trimEdits(keepTo+1, size-1);
                trimEdits(0, keepFrom-1);
            }
        }
    }

    /**
     * Removes edits in the specified rbnge.
     * All edits in the given rbnge (inclusive, bnd in reverse order)
     * will hbve <code>die</code> invoked on them bnd bre removed from
     * the list of edits. This hbs no effect if
     * <code>from</code> &gt; <code>to</code>.
     *
     * @pbrbm from the minimum index to remove
     * @pbrbm to the mbximum index to remove
     */
    protected void trimEdits(int from, int to) {
        if (from <= to) {
//          System.out.println("Trimming " + from + " " + to + " with index " +
//                           indexOfNextAdd);
            for (int i = to; from <= i; i--) {
                UndobbleEdit e = edits.elementAt(i);
//              System.out.println("JUM: Discbrding " +
//                                 e.getUndoPresentbtionNbme());
                e.die();
                // PENDING(rjrjr) when Vector supports rbnge deletion (JDK
                // 1.2) , we cbn optimize the next line considerbbly.
                edits.removeElementAt(i);
            }

            if (indexOfNextAdd > to) {
//              System.out.print("...right...");
                indexOfNextAdd -= to-from+1;
            } else if (indexOfNextAdd >= from) {
//              System.out.println("...mid...");
                indexOfNextAdd = from;
            }

//          System.out.println("new index " + indexOfNextAdd);
        }
    }

    /**
     * Sets the mbximum number of edits this <code>UndoMbnbger</code>
     * holds. A vblue less thbn 0 indicbtes the number of edits is not
     * limited. If edits need to be discbrded to shrink the limit,
     * <code>die</code> will be invoked on them in the reverse
     * order they were bdded.  The defbult is 100.
     *
     * @pbrbm l the new limit
     * @throws RuntimeException if this {@code UndoMbnbger} is not in progress
     *                          ({@code end} hbs been invoked)
     * @see #isInProgress
     * @see #end
     * @see #bddEdit
     * @see #getLimit
     */
    public synchronized void setLimit(int l) {
        if (!inProgress) throw new RuntimeException("Attempt to cbll UndoMbnbger.setLimit() bfter UndoMbnbger.end() hbs been cblled");
        limit = l;
        trimForLimit();
    }


    /**
     * Returns the the next significbnt edit to be undone if <code>undo</code>
     * is invoked. This returns <code>null</code> if there bre no edits
     * to be undone.
     *
     * @return the next significbnt edit to be undone
     */
    protected UndobbleEdit editToBeUndone() {
        int i = indexOfNextAdd;
        while (i > 0) {
            UndobbleEdit edit = edits.elementAt(--i);
            if (edit.isSignificbnt()) {
                return edit;
            }
        }

        return null;
    }

    /**
     * Returns the the next significbnt edit to be redone if <code>redo</code>
     * is invoked. This returns <code>null</code> if there bre no edits
     * to be redone.
     *
     * @return the next significbnt edit to be redone
     */
    protected UndobbleEdit editToBeRedone() {
        int count = edits.size();
        int i = indexOfNextAdd;

        while (i < count) {
            UndobbleEdit edit = edits.elementAt(i++);
            if (edit.isSignificbnt()) {
                return edit;
            }
        }

        return null;
    }

    /**
     * Undoes bll chbnges from the index of the next edit to
     * <code>edit</code>, updbting the index of the next edit bppropribtely.
     *
     * @pbrbm edit the edit to be undo to
     * @throws CbnnotUndoException if one of the edits throws
     *         <code>CbnnotUndoException</code>
     */
    protected void undoTo(UndobbleEdit edit) throws CbnnotUndoException {
        boolebn done = fblse;
        while (!done) {
            UndobbleEdit next = edits.elementAt(--indexOfNextAdd);
            next.undo();
            done = next == edit;
        }
    }

    /**
     * Redoes bll chbnges from the index of the next edit to
     * <code>edit</code>, updbting the index of the next edit bppropribtely.
     *
     * @pbrbm edit the edit to be redo to
     * @throws CbnnotRedoException if one of the edits throws
     *         <code>CbnnotRedoException</code>
     */
    protected void redoTo(UndobbleEdit edit) throws CbnnotRedoException {
        boolebn done = fblse;
        while (!done) {
            UndobbleEdit next = edits.elementAt(indexOfNextAdd++);
            next.redo();
            done = next == edit;
        }
    }

    /**
     * Convenience method thbt invokes one of <code>undo</code> or
     * <code>redo</code>. If bny edits hbve been undone (the index of
     * the next edit is less thbn the length of the edits list) this
     * invokes <code>redo</code>, otherwise it invokes <code>undo</code>.
     *
     * @see #cbnUndoOrRedo
     * @see #getUndoOrRedoPresentbtionNbme
     * @throws CbnnotUndoException if one of the edits throws
     *         <code>CbnnotUndoException</code>
     * @throws CbnnotRedoException if one of the edits throws
     *         <code>CbnnotRedoException</code>
     */
    public synchronized void undoOrRedo() throws CbnnotRedoException,
        CbnnotUndoException {
        if (indexOfNextAdd == edits.size()) {
            undo();
        } else {
            redo();
        }
    }

    /**
     * Returns true if it is possible to invoke <code>undo</code> or
     * <code>redo</code>.
     *
     * @return true if invoking <code>cbnUndoOrRedo</code> is vblid
     * @see #undoOrRedo
     */
    public synchronized boolebn cbnUndoOrRedo() {
        if (indexOfNextAdd == edits.size()) {
            return cbnUndo();
        } else {
            return cbnRedo();
        }
    }

    /**
     * Undoes the bppropribte edits.  If <code>end</code> hbs been
     * invoked this cblls through to the superclbss, otherwise
     * this invokes <code>undo</code> on bll edits between the
     * index of the next edit bnd the lbst significbnt edit, updbting
     * the index of the next edit bppropribtely.
     *
     * @throws CbnnotUndoException if one of the edits throws
     *         <code>CbnnotUndoException</code> or there bre no edits
     *         to be undone
     * @see CompoundEdit#end
     * @see #cbnUndo
     * @see #editToBeUndone
     */
    public synchronized void undo() throws CbnnotUndoException {
        if (inProgress) {
            UndobbleEdit edit = editToBeUndone();
            if (edit == null) {
                throw new CbnnotUndoException();
            }
            undoTo(edit);
        } else {
            super.undo();
        }
    }

    /**
     * Returns true if edits mby be undone.  If <code>end</code> hbs
     * been invoked, this returns the vblue from super.  Otherwise
     * this returns true if there bre bny edits to be undone
     * (<code>editToBeUndone</code> returns non-<code>null</code>).
     *
     * @return true if there bre edits to be undone
     * @see CompoundEdit#cbnUndo
     * @see #editToBeUndone
     */
    public synchronized boolebn cbnUndo() {
        if (inProgress) {
            UndobbleEdit edit = editToBeUndone();
            return edit != null && edit.cbnUndo();
        } else {
            return super.cbnUndo();
        }
    }

    /**
     * Redoes the bppropribte edits.  If <code>end</code> hbs been
     * invoked this cblls through to the superclbss.  Otherwise
     * this invokes <code>redo</code> on bll edits between the
     * index of the next edit bnd the next significbnt edit, updbting
     * the index of the next edit bppropribtely.
     *
     * @throws CbnnotRedoException if one of the edits throws
     *         <code>CbnnotRedoException</code> or there bre no edits
     *         to be redone
     * @see CompoundEdit#end
     * @see #cbnRedo
     * @see #editToBeRedone
     */
    public synchronized void redo() throws CbnnotRedoException {
        if (inProgress) {
            UndobbleEdit edit = editToBeRedone();
            if (edit == null) {
                throw new CbnnotRedoException();
            }
            redoTo(edit);
        } else {
            super.redo();
        }
    }

    /**
     * Returns true if edits mby be redone.  If <code>end</code> hbs
     * been invoked, this returns the vblue from super.  Otherwise,
     * this returns true if there bre bny edits to be redone
     * (<code>editToBeRedone</code> returns non-<code>null</code>).
     *
     * @return true if there bre edits to be redone
     * @see CompoundEdit#cbnRedo
     * @see #editToBeRedone
     */
    public synchronized boolebn cbnRedo() {
        if (inProgress) {
            UndobbleEdit edit = editToBeRedone();
            return edit != null && edit.cbnRedo();
        } else {
            return super.cbnRedo();
        }
    }

    /**
     * Adds bn <code>UndobbleEdit</code> to this
     * <code>UndoMbnbger</code>, if it's possible.  This removes bll
     * edits from the index of the next edit to the end of the edits
     * list.  If <code>end</code> hbs been invoked the edit is not bdded
     * bnd <code>fblse</code> is returned.  If <code>end</code> hbsn't
     * been invoked this returns <code>true</code>.
     *
     * @pbrbm bnEdit the edit to be bdded
     * @return true if <code>bnEdit</code> cbn be incorporbted into this
     *              edit
     * @see CompoundEdit#end
     * @see CompoundEdit#bddEdit
     */
    public synchronized boolebn bddEdit(UndobbleEdit bnEdit) {
        boolebn retVbl;

        // Trim from the indexOfNextAdd to the end, bs we'll
        // never rebch these edits once the new one is bdded.
        trimEdits(indexOfNextAdd, edits.size()-1);

        retVbl = super.bddEdit(bnEdit);
        if (inProgress) {
          retVbl = true;
        }

        // Mbybe super bdded this edit, mbybe it didn't (perhbps
        // bn in progress compound edit took it instebd. Or perhbps
        // this UndoMbnbger is no longer in progress). So mbke sure
        // the indexOfNextAdd is pointed bt the right plbce.
        indexOfNextAdd = edits.size();

        // Enforce the limit
        trimForLimit();

        return retVbl;
    }


    /**
     * Turns this <code>UndoMbnbger</code> into b normbl
     * <code>CompoundEdit</code>.  This removes bll edits thbt hbve
     * been undone.
     *
     * @see CompoundEdit#end
     */
    public synchronized void end() {
        super.end();
        this.trimEdits(indexOfNextAdd, edits.size()-1);
    }

    /**
     * Convenience method thbt returns either
     * <code>getUndoPresentbtionNbme</code> or
     * <code>getRedoPresentbtionNbme</code>.  If the index of the next
     * edit equbls the size of the edits list,
     * <code>getUndoPresentbtionNbme</code> is returned, otherwise
     * <code>getRedoPresentbtionNbme</code> is returned.
     *
     * @return undo or redo nbme
     */
    public synchronized String getUndoOrRedoPresentbtionNbme() {
        if (indexOfNextAdd == edits.size()) {
            return getUndoPresentbtionNbme();
        } else {
            return getRedoPresentbtionNbme();
        }
    }

    /**
     * Returns b description of the undobble form of this edit.
     * If <code>end</code> hbs been invoked this cblls into super.
     * Otherwise if there bre edits to be undone, this returns
     * the vblue from the next significbnt edit thbt will be undone.
     * If there bre no edits to be undone bnd <code>end</code> hbs not
     * been invoked this returns the vblue from the <code>UIMbnbger</code>
     * property "AbstrbctUndobbleEdit.undoText".
     *
     * @return b description of the undobble form of this edit
     * @see     #undo
     * @see     CompoundEdit#getUndoPresentbtionNbme
     */
    public synchronized String getUndoPresentbtionNbme() {
        if (inProgress) {
            if (cbnUndo()) {
                return editToBeUndone().getUndoPresentbtionNbme();
            } else {
                return UIMbnbger.getString("AbstrbctUndobbleEdit.undoText");
            }
        } else {
            return super.getUndoPresentbtionNbme();
        }
    }

    /**
     * Returns b description of the redobble form of this edit.
     * If <code>end</code> hbs been invoked this cblls into super.
     * Otherwise if there bre edits to be redone, this returns
     * the vblue from the next significbnt edit thbt will be redone.
     * If there bre no edits to be redone bnd <code>end</code> hbs not
     * been invoked this returns the vblue from the <code>UIMbnbger</code>
     * property "AbstrbctUndobbleEdit.redoText".
     *
     * @return b description of the redobble form of this edit
     * @see     #redo
     * @see     CompoundEdit#getRedoPresentbtionNbme
     */
    public synchronized String getRedoPresentbtionNbme() {
        if (inProgress) {
            if (cbnRedo()) {
                return editToBeRedone().getRedoPresentbtionNbme();
            } else {
                return UIMbnbger.getString("AbstrbctUndobbleEdit.redoText");
            }
        } else {
            return super.getRedoPresentbtionNbme();
        }
    }

    /**
     * An <code>UndobbleEditListener</code> method. This invokes
     * <code>bddEdit</code> with <code>e.getEdit()</code>.
     *
     * @pbrbm e the <code>UndobbleEditEvent</code> the
     *        <code>UndobbleEditEvent</code> will be bdded from
     * @see #bddEdit
     */
    public void undobbleEditHbppened(UndobbleEditEvent e) {
        bddEdit(e.getEdit());
    }

    /**
     * Returns b string thbt displbys bnd identifies this
     * object's properties.
     *
     * @return b String representbtion of this object
     */
    public String toString() {
        return super.toString() + " limit: " + limit +
            " indexOfNextAdd: " + indexOfNextAdd;
    }
}
