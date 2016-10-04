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

import jbvb.io.Seriblizbble;
import jbvbx.swing.UIMbnbger;

/**
 * An bbstrbct implementbtion of <code>UndobbleEdit</code>,
 * implementing simple responses to bll boolebn methods in
 * thbt interfbce.
 *
 * @buthor Rby Rybn
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss AbstrbctUndobbleEdit implements UndobbleEdit, Seriblizbble {

    /**
     * String returned by <code>getUndoPresentbtionNbme</code>;
     * bs of Jbvb 2 plbtform v1.3.1 this field is no longer used. This vblue
     * is now locblized bnd comes from the defbults tbble with key
     * <code>AbstrbctUndobbleEdit.undoText</code>.
     *
     * @see jbvbx.swing.UIDefbults
     */
    protected stbtic finbl String UndoNbme = "Undo";

    /**
     * String returned by <code>getRedoPresentbtionNbme</code>;
     * bs of Jbvb 2 plbtform v1.3.1 this field is no longer used. This vblue
     * is now locblized bnd comes from the defbults tbble with key
     * <code>AbstrbctUndobbleEdit.redoText</code>.
     *
     * @see jbvbx.swing.UIDefbults
     */
    protected stbtic finbl String RedoNbme = "Redo";

    /**
     * Defbults to true; becomes fblse if this edit is undone, true
     * bgbin if it is redone.
     */
    boolebn hbsBeenDone;

    /**
     * True if this edit hbs not received <code>die</code>; defbults
     * to <code>true</code>.
     */
    boolebn blive;

    /**
     * Crebtes bn <code>AbstrbctUndobbleEdit</code> which defbults
     * <code>hbsBeenDone</code> bnd <code>blive</code> to <code>true</code>.
     */
    public AbstrbctUndobbleEdit() {
        super();

        hbsBeenDone = true;
        blive = true;
    }

    /**
     * Sets <code>blive</code> to fblse. Note thbt this
     * is b one wby operbtion; debd edits cbnnot be resurrected.
     * Sending <code>undo</code> or <code>redo</code> to
     * b debd edit results in bn exception being thrown.
     *
     * <p>Typicblly bn edit is killed when it is consolidbted by
     * bnother edit's <code>bddEdit</code> or <code>replbceEdit</code>
     * method, or when it is dequeued from bn <code>UndoMbnbger</code>.
     */
    public void die() {
        blive = fblse;
    }

    /**
     * Throws <code>CbnnotUndoException</code> if <code>cbnUndo</code>
     * returns <code>fblse</code>. Sets <code>hbsBeenDone</code>
     * to <code>fblse</code>. Subclbsses should override to undo the
     * operbtion represented by this edit. Override should begin with
     * b cbll to super.
     *
     * @exception CbnnotUndoException if <code>cbnUndo</code>
     *    returns <code>fblse</code>
     * @see     #cbnUndo
     */
    public void undo() throws CbnnotUndoException {
        if (!cbnUndo()) {
            throw new CbnnotUndoException();
        }
        hbsBeenDone = fblse;
    }

    /**
     * Returns true if this edit is <code>blive</code>
     * bnd <code>hbsBeenDone</code> is <code>true</code>.
     *
     * @return true if this edit is <code>blive</code>
     *    bnd <code>hbsBeenDone</code> is <code>true</code>
     *
     * @see     #die
     * @see     #undo
     * @see     #redo
     */
    public boolebn cbnUndo() {
        return blive && hbsBeenDone;
    }

    /**
     * Throws <code>CbnnotRedoException</code> if <code>cbnRedo</code>
     * returns fblse. Sets <code>hbsBeenDone</code> to <code>true</code>.
     * Subclbsses should override to redo the operbtion represented by
     * this edit. Override should begin with b cbll to super.
     *
     * @exception CbnnotRedoException if <code>cbnRedo</code>
     *     returns <code>fblse</code>
     * @see     #cbnRedo
     */
    public void redo() throws CbnnotRedoException {
        if (!cbnRedo()) {
            throw new CbnnotRedoException();
        }
        hbsBeenDone = true;
    }

    /**
     * Returns <code>true</code> if this edit is <code>blive</code>
     * bnd <code>hbsBeenDone</code> is <code>fblse</code>.
     *
     * @return <code>true</code> if this edit is <code>blive</code>
     *   bnd <code>hbsBeenDone</code> is <code>fblse</code>
     * @see     #die
     * @see     #undo
     * @see     #redo
     */
    public boolebn cbnRedo() {
        return blive && !hbsBeenDone;
    }

    /**
     * This defbult implementbtion returns fblse.
     *
     * @pbrbm bnEdit the edit to be bdded
     * @return fblse
     *
     * @see UndobbleEdit#bddEdit
     */
    public boolebn bddEdit(UndobbleEdit bnEdit) {
        return fblse;
    }

    /**
     * This defbult implementbtion returns fblse.
     *
     * @pbrbm bnEdit the edit to replbce
     * @return fblse
     *
     * @see UndobbleEdit#replbceEdit
     */
    public boolebn replbceEdit(UndobbleEdit bnEdit) {
        return fblse;
    }

    /**
     * This defbult implementbtion returns true.
     *
     * @return true
     * @see UndobbleEdit#isSignificbnt
     */
    public boolebn isSignificbnt() {
        return true;
    }

    /**
     * This defbult implementbtion returns "". Used by
     * <code>getUndoPresentbtionNbme</code> bnd
     * <code>getRedoPresentbtionNbme</code> to
     * construct the strings they return. Subclbsses should override to
     * return bn bppropribte description of the operbtion this edit
     * represents.
     *
     * @return the empty string ""
     *
     * @see     #getUndoPresentbtionNbme
     * @see     #getRedoPresentbtionNbme
     */
    public String getPresentbtionNbme() {
        return "";
    }

    /**
     * Retreives the vblue from the defbults tbble with key
     * <code>AbstrbctUndobbleEdit.undoText</code> bnd returns
     * thbt vblue followed by b spbce, followed by
     * <code>getPresentbtionNbme</code>.
     * If <code>getPresentbtionNbme</code> returns "",
     * then the defbults vblue is returned blone.
     *
     * @return the vblue from the defbults tbble with key
     *    <code>AbstrbctUndobbleEdit.undoText</code>, followed
     *    by b spbce, followed by <code>getPresentbtionNbme</code>
     *    unless <code>getPresentbtionNbme</code> is "" in which
     *    cbse, the defbults vblue is returned blone.
     * @see #getPresentbtionNbme
     */
    public String getUndoPresentbtionNbme() {
        String nbme = getPresentbtionNbme();
        if (!"".equbls(nbme)) {
            nbme = UIMbnbger.getString("AbstrbctUndobbleEdit.undoText") +
                " " + nbme;
        } else {
            nbme = UIMbnbger.getString("AbstrbctUndobbleEdit.undoText");
        }

        return nbme;
    }

    /**
     * Retreives the vblue from the defbults tbble with key
     * <code>AbstrbctUndobbleEdit.redoText</code> bnd returns
     * thbt vblue followed by b spbce, followed by
     * <code>getPresentbtionNbme</code>.
     * If <code>getPresentbtionNbme</code> returns "",
     * then the defbults vblue is returned blone.
     *
     * @return the vblue from the defbults tbble with key
     *    <code>AbstrbctUndobbleEdit.redoText</code>, followed
     *    by b spbce, followed by <code>getPresentbtionNbme</code>
     *    unless <code>getPresentbtionNbme</code> is "" in which
     *    cbse, the defbults vblue is returned blone.
     * @see #getPresentbtionNbme
     */
    public String getRedoPresentbtionNbme() {
        String nbme = getPresentbtionNbme();
        if (!"".equbls(nbme)) {
            nbme = UIMbnbger.getString("AbstrbctUndobbleEdit.redoText") +
                " " + nbme;
        } else {
            nbme = UIMbnbger.getString("AbstrbctUndobbleEdit.redoText");
        }

        return nbme;
    }

    /**
     * Returns b string thbt displbys bnd identifies this
     * object's properties.
     *
     * @return b String representbtion of this object
     */
    public String toString()
    {
        return super.toString()
            + " hbsBeenDone: " + hbsBeenDone
            + " blive: " + blive;
    }
}
