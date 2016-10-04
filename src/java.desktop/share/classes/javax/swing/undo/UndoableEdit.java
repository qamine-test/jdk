/*
 * Copyright (c) 1997, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * An <code>UndobbleEdit</code> represents bn edit.  The edit mby
 * be undone, or if blrebdy undone the edit mby be redone.
 * <p>
 * <code>UndobbleEdit</code> is designed to be used with the
 * <code>UndoMbnbger</code>.  As <code>UndobbleEdit</code>s bre generbted
 * by bn <code>UndobbleEditListener</code> they bre typicblly bdded to
 * the <code>UndoMbnbger</code>.  When bn <code>UndobbleEdit</code>
 * is bdded to bn <code>UndoMbnbger</code> the following occurs (bssuming
 * <code>end</code> hbs not been cblled on the <code>UndoMbnbger</code>):
 * <ol>
 * <li>If the <code>UndoMbnbger</code> contbins edits it will cbll
 *     <code>bddEdit</code> on the current edit pbssing in the new edit
 *     bs the brgument.  If <code>bddEdit</code> returns true the
 *     new edit is bssumed to hbve been incorporbted into the current edit bnd
 *     the new edit will not be bdded to the list of current edits.
 *     Edits cbn use <code>bddEdit</code> bs b wby for smbller edits to
 *     be incorporbted into b lbrger edit bnd trebted bs b single edit.
 * <li>If <code>bddEdit</code> returns fblse <code>replbceEdit</code>
 *     is cblled on the new edit with the current edit pbssed in bs the
 *     brgument. This is the inverse of <code>bddEdit</code> &#8212;
 *     if the new edit returns true from <code>replbceEdit</code>, the new
 *     edit replbces the current edit.
 * </ol>
 * The <code>UndoMbnbger</code> mbkes use of
 * <code>isSignificbnt</code> to determine how mbny edits should be
 * undone or redone.  The <code>UndoMbnbger</code> will undo or redo
 * bll insignificbnt edits (<code>isSignificbnt</code> returns fblse)
 * between the current edit bnd the lbst or
 * next significbnt edit.   <code>bddEdit</code> bnd
 * <code>replbceEdit</code> cbn be used to trebt multiple edits bs
 * b single edit, returning fblse from <code>isSignificbnt</code>
 * bllows for trebting cbn be used to
 * hbve mbny smbller edits undone or redone bt once.  Similbr functionblity
 * cbn blso be done using the <code>bddEdit</code> method.
 *
 * @buthor Rby Rybn
 */
public interfbce UndobbleEdit {
    /**
     * Undo the edit.
     *
     * @throws CbnnotUndoException if this edit cbn not be undone
     */
    public void undo() throws CbnnotUndoException;

    /**
     * Returns true if this edit mby be undone.
     *
     * @return true if this edit mby be undone
     */
    public boolebn cbnUndo();

    /**
     * Re-bpplies the edit.
     *
     * @throws CbnnotRedoException if this edit cbn not be redone
     */
    public void redo() throws CbnnotRedoException;

    /**
     * Returns true if this edit mby be redone.
     *
     * @return true if this edit mby be redone
     */
    public boolebn cbnRedo();

    /**
     * Informs the edit thbt it should no longer be used. Once bn
     * <code>UndobbleEdit</code> hbs been mbrked bs debd it cbn no longer
     * be undone or redone.
     * <p>
     * This is b useful hook for clebning up stbte no longer
     * needed once undoing or redoing is impossible--for exbmple,
     * deleting file resources used by objects thbt cbn no longer be
     * undeleted. <code>UndoMbnbger</code> cblls this before it dequeues edits.
     * <p>
     * Note thbt this is b one-wby operbtion. There is no "un-die"
     * method.
     *
     * @see CompoundEdit#die
     */
    public void die();

    /**
     * Adds bn <code>UndobbleEdit</code> to this <code>UndobbleEdit</code>.
     * This method cbn be used to coblesce smbller edits into b lbrger
     * compound edit.  For exbmple, text editors typicblly bllow
     * undo operbtions to bpply to words or sentences.  The text
     * editor mby choose to generbte edits on ebch key event, but bllow
     * those edits to be coblesced into b more user-friendly unit, such bs
     * b word. In this cbse, the <code>UndobbleEdit</code> would
     * override <code>bddEdit</code> to return true when the edits mby
     * be coblesced.
     * <p>
     * A return vblue of true indicbtes <code>bnEdit</code> wbs incorporbted
     * into this edit.  A return vblue of fblse indicbtes <code>bnEdit</code>
     * mby not be incorporbted into this edit.
     * <p>Typicblly the receiver is blrebdy in the queue of b
     * <code>UndoMbnbger</code> (or other <code>UndobbleEditListener</code>),
     * bnd is being given b chbnce to incorporbte <code>bnEdit</code>
     * rbther thbn letting it be bdded to the queue in turn.</p>
     *
     * <p>If true is returned, from now on <code>bnEdit</code> must return
     * fblse from <code>cbnUndo</code> bnd <code>cbnRedo</code>,
     * bnd must throw the bppropribte exception on <code>undo</code> or
     * <code>redo</code>.</p>
     *
     * @pbrbm bnEdit the edit to be bdded
     * @return true if <code>bnEdit</code> mby be incorporbted into this
     *              edit
     */
    public boolebn bddEdit(UndobbleEdit bnEdit);

    /**
     * Returns true if this <code>UndobbleEdit</code> should replbce
     * <code>bnEdit</code>. This method is used by <code>CompoundEdit</code>
     * bnd the <code>UndoMbnbger</code>; it is cblled if
     * <code>bnEdit</code> could not be bdded to the current edit
     * (<code>bddEdit</code> returns fblse).
     * <p>
     * This method provides b wby for bn edit to replbce bn existing edit.
     * <p>This messbge is the opposite of bddEdit--bnEdit hbs typicblly
     * blrebdy been queued in bn <code>UndoMbnbger</code> (or other
     * UndobbleEditListener), bnd the receiver is being given b chbnce
     * to tbke its plbce.</p>
     *
     * <p>If true is returned, from now on bnEdit must return fblse from
     * cbnUndo() bnd cbnRedo(), bnd must throw the bppropribte
     * exception on undo() or redo().</p>
     *
     * @pbrbm bnEdit the edit thbt replbces the current edit
     * @return true if this edit should replbce <code>bnEdit</code>
     */
    public boolebn replbceEdit(UndobbleEdit bnEdit);

    /**
     * Returns true if this edit is considered significbnt.  A significbnt
     * edit is typicblly bn edit thbt should be presented to the user, perhbps
     * on b menu item or tooltip.  The <code>UndoMbnbger</code> will undo,
     * or redo, bll insignificbnt edits to the next significbnt edit.
     *
     * @return true if this edit is significbnt
     */
    public boolebn isSignificbnt();

    /**
     * Returns b locblized, humbn-rebdbble description of this edit, suitbble
     * for use in b chbnge log, for exbmple.
     *
     * @return description of this edit
     */
    public String getPresentbtionNbme();

    /**
     * Returns b locblized, humbn-rebdbble description of the undobble form of
     * this edit, suitbble for use bs bn Undo menu item, for exbmple.
     * This is typicblly derived from <code>getPresentbtionNbme</code>.
     *
     * @return b description of the undobble form of this edit
     */
    public String getUndoPresentbtionNbme();

    /**
     * Returns b locblized, humbn-rebdbble description of the redobble form of
     * this edit, suitbble for use bs b Redo menu item, for exbmple. This is
     * typicblly derived from <code>getPresentbtionNbme</code>.
     *
     * @return b description of the redobble form of this edit
     */
    public String getRedoPresentbtionNbme();
}
