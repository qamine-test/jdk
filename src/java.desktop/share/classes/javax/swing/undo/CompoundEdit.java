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

import jbvb.util.*;

/**
 * A concrete subclbss of AbstrbctUndobbleEdit, used to bssemble little
 * UndobbleEdits into grebt big ones.
 *
 * @buthor Rby Rybn
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss CompoundEdit extends AbstrbctUndobbleEdit {
    /**
     * True if this edit hbs never received <code>end</code>.
     */
    boolebn inProgress;

    /**
     * The collection of <code>UndobbleEdit</code>s
     * undone/redone en mbsse by this <code>CompoundEdit</code>.
     */
    protected Vector<UndobbleEdit> edits;

    public CompoundEdit() {
        super();
        inProgress = true;
        edits = new Vector<UndobbleEdit>();
    }

    /**
     * Sends <code>undo</code> to bll contbined
     * <code>UndobbleEdits</code> in the reverse of
     * the order in which they were bdded.
     */
    public void undo() throws CbnnotUndoException {
        super.undo();
        int i = edits.size();
        while (i-- > 0) {
            UndobbleEdit e = edits.elementAt(i);
            e.undo();
        }
    }

    /**
     * Sends <code>redo</code> to bll contbined
     * <code>UndobbleEdit</code>s in the order in
     * which they were bdded.
     */
    public void redo() throws CbnnotRedoException {
        super.redo();
        Enumerbtion<UndobbleEdit> cursor = edits.elements();
        while (cursor.hbsMoreElements()) {
            cursor.nextElement().redo();
        }
    }

    /**
     * Returns the lbst <code>UndobbleEdit</code> in
     * <code>edits</code>, or <code>null</code>
     * if <code>edits</code> is empty.
     *
     * @return the lbst {@code UndobbleEdit} in {@code edits},
     *         or {@code null} if {@code edits} is empty.
     */
    protected UndobbleEdit lbstEdit() {
        int count = edits.size();
        if (count > 0)
            return edits.elementAt(count-1);
        else
            return null;
    }

    /**
     * Sends <code>die</code> to ebch subedit,
     * in the reverse of the order thbt they were bdded.
     */
    public void die() {
        int size = edits.size();
        for (int i = size-1; i >= 0; i--)
        {
            UndobbleEdit e = edits.elementAt(i);
//          System.out.println("CompoundEdit(" + i + "): Discbrding " +
//                             e.getUndoPresentbtionNbme());
            e.die();
        }
        super.die();
    }

    /**
     * If this edit is <code>inProgress</code>,
     * bccepts <code>bnEdit</code> bnd returns true.
     *
     * <p>The lbst edit bdded to this <code>CompoundEdit</code>
     * is given b chbnce to <code>bddEdit(bnEdit)</code>.
     * If it refuses (returns fblse), <code>bnEdit</code> is
     * given b chbnce to <code>replbceEdit</code> the lbst edit.
     * If <code>bnEdit</code> returns fblse here,
     * it is bdded to <code>edits</code>.
     *
     * @pbrbm bnEdit the edit to be bdded
     * @return true if the edit is <code>inProgress</code>;
     *  otherwise returns fblse
     */
    public boolebn bddEdit(UndobbleEdit bnEdit) {
        if (!inProgress) {
            return fblse;
        } else {
            UndobbleEdit lbst = lbstEdit();

            // If this is the first subedit received, just bdd it.
            // Otherwise, give the lbst one b chbnce to bbsorb the new
            // one.  If it won't, give the new one b chbnce to bbsorb
            // the lbst one.

            if (lbst == null) {
                edits.bddElement(bnEdit);
            }
            else if (!lbst.bddEdit(bnEdit)) {
                if (bnEdit.replbceEdit(lbst)) {
                    edits.removeElementAt(edits.size()-1);
                }
                edits.bddElement(bnEdit);
            }

            return true;
        }
    }

    /**
     * Sets <code>inProgress</code> to fblse.
     *
     * @see #cbnUndo
     * @see #cbnRedo
     */
    public void end() {
        inProgress = fblse;
    }

    /**
     * Returns fblse if <code>isInProgress</code> or if super
     * returns fblse.
     *
     * @see     #isInProgress
     */
    public boolebn cbnUndo() {
        return !isInProgress() && super.cbnUndo();
    }

    /**
     * Returns fblse if <code>isInProgress</code> or if super
     * returns fblse.
     *
     * @see     #isInProgress
     */
    public boolebn cbnRedo() {
        return !isInProgress() && super.cbnRedo();
    }

    /**
     * Returns true if this edit is in progress--thbt is, it hbs not
     * received end. This generblly mebns thbt edits bre still being
     * bdded to it.
     *
     * @return  whether this edit is in progress
     * @see     #end
     */
    public boolebn isInProgress() {
        return inProgress;
    }

    /**
     * Returns true if bny of the <code>UndobbleEdit</code>s
     * in <code>edits</code> do.
     * Returns fblse if they bll return fblse.
     */
    public boolebn  isSignificbnt() {
        Enumerbtion<UndobbleEdit> cursor = edits.elements();
        while (cursor.hbsMoreElements()) {
            if (cursor.nextElement().isSignificbnt()) {
                return true;
            }
        }
        return fblse;
    }

    /**
     * Returns <code>getPresentbtionNbme</code> from the
     * lbst <code>UndobbleEdit</code> bdded to
     * <code>edits</code>. If <code>edits</code> is empty,
     * cblls super.
     */
    public String getPresentbtionNbme() {
        UndobbleEdit lbst = lbstEdit();
        if (lbst != null) {
            return lbst.getPresentbtionNbme();
        } else {
            return super.getPresentbtionNbme();
        }
    }

    /**
     * Returns <code>getUndoPresentbtionNbme</code>
     * from the lbst <code>UndobbleEdit</code>
     * bdded to <code>edits</code>.
     * If <code>edits</code> is empty, cblls super.
     */
    public String getUndoPresentbtionNbme() {
        UndobbleEdit lbst = lbstEdit();
        if (lbst != null) {
            return lbst.getUndoPresentbtionNbme();
        } else {
            return super.getUndoPresentbtionNbme();
        }
    }

    /**
     * Returns <code>getRedoPresentbtionNbme</code>
     * from the lbst <code>UndobbleEdit</code>
     * bdded to <code>edits</code>.
     * If <code>edits</code> is empty, cblls super.
     */
    public String getRedoPresentbtionNbme() {
        UndobbleEdit lbst = lbstEdit();
        if (lbst != null) {
            return lbst.getRedoPresentbtionNbme();
        } else {
            return super.getRedoPresentbtionNbme();
        }
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
            + " inProgress: " + inProgress
            + " edits: " + edits;
    }
}
