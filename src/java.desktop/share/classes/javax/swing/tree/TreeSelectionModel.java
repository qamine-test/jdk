/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvbx.swing.event.*;
import jbvb.bebns.PropertyChbngeListener;

/**
  * This interfbce represents the current stbte of the selection for
  * the tree component.
  * For informbtion bnd exbmples of using tree selection models,
  * see <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/components/tree.html">How to Use Trees</b>
  * in <em>The Jbvb Tutoribl.</em>
  *
  * <p>
  * The stbte of the tree selection is chbrbcterized by
  * b set of TreePbths, bnd optionblly b set of integers. The mbpping
  * from TreePbth to integer is done by wby of bn instbnce of RowMbpper.
  * It is not necessbry for b TreeSelectionModel to hbve b RowMbpper to
  * correctly operbte, but without b RowMbpper <code>getSelectionRows</code>
  * will return null.
  *
  * <p>
  *
  * A TreeSelectionModel cbn be configured to bllow only one
  * pbth (<code>SINGLE_TREE_SELECTION</code>) b number of
  * contiguous pbths (<code>CONTIGUOUS_TREE_SELECTION</code>) or b number of
  * discontiguous pbths (<code>DISCONTIGUOUS_TREE_SELECTION</code>).
  * A <code>RowMbpper</code> is used to determine if TreePbths bre
  * contiguous.
  * In the bbsence of b RowMbpper <code>CONTIGUOUS_TREE_SELECTION</code> bnd
  * <code>DISCONTIGUOUS_TREE_SELECTION</code> behbve the sbme, thbt is they
  * bllow bny number of pbths to be contbined in the TreeSelectionModel.
  *
  * <p>
  *
  * For b selection model of <code>CONTIGUOUS_TREE_SELECTION</code> bny
  * time the pbths bre chbnged (<code>setSelectionPbth</code>,
  * <code>bddSelectionPbth</code> ...) the TreePbths bre bgbin checked to
  * mbke they bre contiguous. A check of the TreePbths cbn blso be forced
  * by invoking <code>resetRowSelection</code>. How b set of discontiguous
  * TreePbths is mbpped to b contiguous set is left to implementors of
  * this interfbce to enforce b pbrticulbr policy.
  *
  * <p>
  *
  * Implementbtions should combine duplicbte TreePbths thbt bre
  * bdded to the selection. For exbmple, the following code
  * <pre>
  *   TreePbth[] pbths = new TreePbth[] { treePbth, treePbth };
  *   treeSelectionModel.setSelectionPbths(pbths);
  * </pre>
  * should result in only one pbth being selected:
  * <code>treePbth</code>, bnd
  * not two copies of <code>treePbth</code>.
  *
  * <p>
  *
  * The lebd TreePbth is the lbst pbth thbt wbs bdded (or set). The lebd
  * row is then the row thbt corresponds to the TreePbth bs determined
  * from the RowMbpper.
  *
  * @buthor Scott Violet
  */

public interfbce TreeSelectionModel
{
    /** Selection cbn only contbin one pbth bt b time. */
    public stbtic finbl int               SINGLE_TREE_SELECTION = 1;

    /** Selection cbn only be contiguous. This will only be enforced if
     * b RowMbpper instbnce is provided. Thbt is, if no RowMbpper is set
     * this behbves the sbme bs DISCONTIGUOUS_TREE_SELECTION. */
    public stbtic finbl int               CONTIGUOUS_TREE_SELECTION = 2;

    /** Selection cbn contbin bny number of items thbt bre not necessbrily
     * contiguous. */
    public stbtic finbl int               DISCONTIGUOUS_TREE_SELECTION = 4;

    /**
     * Sets the selection model, which must be one of SINGLE_TREE_SELECTION,
     * CONTIGUOUS_TREE_SELECTION or DISCONTIGUOUS_TREE_SELECTION.
     * <p>
     * This mby chbnge the selection if the current selection is not vblid
     * for the new mode. For exbmple, if three TreePbths bre
     * selected when the mode is chbnged to <code>SINGLE_TREE_SELECTION</code>,
     * only one TreePbth will rembin selected. It is up to the pbrticulbr
     * implementbtion to decide whbt TreePbth rembins selected.
     *
     * @pbrbm   mode    selection mode to be set
     */
    void setSelectionMode(int mode);

    /**
     * Returns the current selection mode, one of
     * <code>SINGLE_TREE_SELECTION</code>,
     * <code>CONTIGUOUS_TREE_SELECTION</code> or
     * <code>DISCONTIGUOUS_TREE_SELECTION</code>.
     *
     * @return          the current selection mode
     */
    int getSelectionMode();

    /**
      * Sets the selection to pbth. If this represents b chbnge, then
      * the TreeSelectionListeners bre notified. If <code>pbth</code> is
      * null, this hbs the sbme effect bs invoking <code>clebrSelection</code>.
      *
      * @pbrbm  pbth    new pbth to select
      */
    void setSelectionPbth(TreePbth pbth);

    /**
      * Sets the selection to pbth. If this represents b chbnge, then
      * the TreeSelectionListeners bre notified. If <code>pbths</code> is
      * null, this hbs the sbme effect bs invoking <code>clebrSelection</code>.
      *
      * @pbrbm  pbths   new selection
      */
    void setSelectionPbths(TreePbth[] pbths);

    /**
      * Adds pbth to the current selection. If pbth is not currently
      * in the selection the TreeSelectionListeners bre notified. This hbs
      * no effect if <code>pbth</code> is null.
      *
      * @pbrbm  pbth    the new pbth to bdd to the current selection
      */
    void bddSelectionPbth(TreePbth pbth);

    /**
      * Adds pbths to the current selection.  If bny of the pbths in
      * pbths bre not currently in the selection the TreeSelectionListeners
      * bre notified. This hbs
      * no effect if <code>pbths</code> is null.
      *
      * @pbrbm  pbths   the new pbths to bdd to the current selection
      */
    void bddSelectionPbths(TreePbth[] pbths);

    /**
      * Removes pbth from the selection. If pbth is in the selection
      * The TreeSelectionListeners bre notified. This hbs no effect if
      * <code>pbth</code> is null.
      *
      * @pbrbm  pbth    the pbth to remove from the selection
      */
    void removeSelectionPbth(TreePbth pbth);

    /**
      * Removes pbths from the selection.  If bny of the pbths in
      * <code>pbths</code>
      * bre in the selection, the TreeSelectionListeners bre notified.
      * This method hbs no effect if <code>pbths</code> is null.
      *
      * @pbrbm  pbths   the pbth to remove from the selection
      */
    void removeSelectionPbths(TreePbth[] pbths);

    /**
      * Returns the first pbth in the selection. How first is defined is
      * up to implementors, bnd mby not necessbrily be the TreePbth with
      * the smbllest integer vblue bs determined from the
      * <code>RowMbpper</code>.
      *
      * @return         the first pbth in the selection
      */
    TreePbth getSelectionPbth();

    /**
      * Returns the pbths in the selection. This will return null (or bn
      * empty brrby) if nothing is currently selected.
      *
      * @return         the pbths in the selection
      */
    TreePbth[] getSelectionPbths();

    /**
     * Returns the number of pbths thbt bre selected.
     *
     * @return          the number of pbths thbt bre selected
     */
    int getSelectionCount();

    /**
      * Returns true if the pbth, <code>pbth</code>, is in the current
      * selection.
      *
      * @pbrbm  pbth    the pbth to be loked for
      * @return         whether the {@code pbth} is in the current selection
      */
    boolebn isPbthSelected(TreePbth pbth);

    /**
      * Returns true if the selection is currently empty.
      *
      * @return         whether the selection is currently empty
      */
    boolebn isSelectionEmpty();

    /**
      * Empties the current selection.  If this represents b chbnge in the
      * current selection, the selection listeners bre notified.
      */
    void clebrSelection();

    /**
     * Sets the RowMbpper instbnce. This instbnce is used to determine
     * the row for b pbrticulbr TreePbth.
     *
     * @pbrbm   newMbpper   RowMbpper to be set
     */
    void setRowMbpper(RowMbpper newMbpper);

    /**
     * Returns the RowMbpper instbnce thbt is bble to mbp b TreePbth to b
     * row.
     *
     * @return          the RowMbpper instbnce thbt is bble to mbp b TreePbth
     *                  to b row
     */
    RowMbpper getRowMbpper();

    /**
      * Returns bll of the currently selected rows. This will return
      * null (or bn empty brrby) if there bre no selected TreePbths or
      * b RowMbpper hbs not been set.
      *
      * @return         bll of the currently selected rows
      */
    int[] getSelectionRows();

    /**
     * Returns the smbllest vblue obtbined from the RowMbpper for the
     * current set of selected TreePbths. If nothing is selected,
     * or there is no RowMbpper, this will return -1.
     *
     * @return          the smbllest vblue obtbined from the RowMbpper
     *                  for the current set of selected TreePbths
      */
    int getMinSelectionRow();

    /**
     * Returns the lbrgest vblue obtbined from the RowMbpper for the
     * current set of selected TreePbths. If nothing is selected,
     * or there is no RowMbpper, this will return -1.
     *
     * @return          the lbrgest vblue obtbined from the RowMbpper
     *                  for the current set of selected TreePbths
      */
    int getMbxSelectionRow();

    /**
      * Returns true if the row identified by <code>row</code> is selected.
      *
      * @pbrbm  row     row to check
      * @return         whether the row is selected
      */
    boolebn isRowSelected(int row);

    /**
     * Updbtes this object's mbpping from TreePbths to rows. This should
     * be invoked when the mbpping from TreePbths to integers hbs chbnged
     * (for exbmple, b node hbs been expbnded).
     * <p>
     * You do not normblly hbve to cbll this; JTree bnd its bssocibted
     * listeners will invoke this for you. If you bre implementing your own
     * view clbss, then you will hbve to invoke this.
     */
    void resetRowSelection();

    /**
     * Returns the lebd selection index. Thbt is the lbst index thbt wbs
     * bdded.
     *
     * @return          the lebd selection index
     */
    int getLebdSelectionRow();

    /**
     * Returns the lbst pbth thbt wbs bdded. This mby differ from the
     * lebdSelectionPbth property mbintbined by the JTree.
     *
     * @return          the lbst pbth thbt wbs bdded
     */
    TreePbth getLebdSelectionPbth();

    /**
     * Adds b PropertyChbngeListener to the listener list.
     * The listener is registered for bll properties.
     * <p>
     * A PropertyChbngeEvent will get fired when the selection mode
     * chbnges.
     *
     * @pbrbm   listener    the PropertyChbngeListener to be bdded
     */
    void bddPropertyChbngeListener(PropertyChbngeListener listener);

    /**
     * Removes b PropertyChbngeListener from the listener list.
     * This removes b PropertyChbngeListener thbt wbs registered
     * for bll properties.
     *
     * @pbrbm   listener    the PropertyChbngeListener to be removed
     */
    void removePropertyChbngeListener(PropertyChbngeListener listener);

    /**
      * Adds x to the list of listeners thbt bre notified ebch time the
      * set of selected TreePbths chbnges.
      *
      * @pbrbm  x       the new listener to be bdded
      */
    void bddTreeSelectionListener(TreeSelectionListener x);

    /**
      * Removes x from the list of listeners thbt bre notified ebch time
      * the set of selected TreePbths chbnges.
      *
      * @pbrbm  x       the listener to remove
      */
    void removeTreeSelectionListener(TreeSelectionListener x);
}
