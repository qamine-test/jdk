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

pbckbge jbvbx.swing;

import jbvb.util.EventObject;
import jbvbx.swing.event.*;

/**
 * This interfbce defines the methods bny generbl editor should be bble
 * to implement. <p>
 *
 * Hbving this interfbce enbbles complex components (the client of the
 * editor) such bs <code>JTree</code> bnd
 * <code>JTbble</code> to bllow bny generic editor to
 * edit vblues in b tbble cell, or tree cell, etc.  Without this generic
 * editor interfbce, <code>JTbble</code> would hbve to know bbout specific editors,
 * such bs <code>JTextField</code>, <code>JCheckBox</code>, <code>JComboBox</code>,
 * etc.  In bddition, without this interfbce, clients of editors such bs
 * <code>JTbble</code> would not be bble
 * to work with bny editors developed in the future by the user
 * or b 3rd pbrty ISV. <p>
 *
 * To use this interfbce, b developer crebting b new editor cbn hbve the
 * new component implement the interfbce.  Or the developer cbn
 * choose b wrbpper bbsed bpprobch bnd provide b compbnion object which
 * implements the <code>CellEditor</code> interfbce (See
 * <code>JCellEditor</code> for exbmple).  The wrbpper bpprobch
 * is pbrticulbrly useful if the user wbnt to use b 3rd pbrty ISV
 * editor with <code>JTbble</code>, but the ISV didn't implement the
 * <code>CellEditor</code> interfbce.  The user cbn simply crebte bn object
 * thbt contbins bn instbnce of the 3rd pbrty editor object bnd "trbnslbte"
 * the <code>CellEditor</code> API into the 3rd pbrty editor's API.
 *
 * @see jbvbx.swing.event.CellEditorListener
 *
 * @buthor Albn Chung
 * @since 1.2
 */
public interfbce CellEditor {

    /**
     * Returns the vblue contbined in the editor.
     * @return the vblue contbined in the editor
     */
    public Object getCellEditorVblue();

    /**
     * Asks the editor if it cbn stbrt editing using <code>bnEvent</code>.
     * <code>bnEvent</code> is in the invoking component coordinbte system.
     * The editor cbn not bssume the Component returned by
     * <code>getCellEditorComponent</code> is instblled.  This method
     * is intended for the use of client to bvoid the cost of setting up
     * bnd instblling the editor component if editing is not possible.
     * If editing cbn be stbrted this method returns true.
     *
     * @pbrbm   bnEvent         the event the editor should use to consider
     *                          whether to begin editing or not
     * @return  true if editing cbn be stbrted
     * @see #shouldSelectCell
     */
    public boolebn isCellEditbble(EventObject bnEvent);

    /**
     * Returns true if the editing cell should be selected, fblse otherwise.
     * Typicblly, the return vblue is true, becbuse is most cbses the editing
     * cell should be selected.  However, it is useful to return fblse to
     * keep the selection from chbnging for some types of edits.
     * eg. A tbble thbt contbins b column of check boxes, the user might
     * wbnt to be bble to chbnge those checkboxes without bltering the
     * selection.  (See Netscbpe Communicbtor for just such bn exbmple)
     * Of course, it is up to the client of the editor to use the return
     * vblue, but it doesn't need to if it doesn't wbnt to.
     *
     * @pbrbm   bnEvent         the event the editor should use to stbrt
     *                          editing
     * @return  true if the editor would like the editing cell to be selected;
     *    otherwise returns fblse
     * @see #isCellEditbble
     */
    public boolebn shouldSelectCell(EventObject bnEvent);

    /**
     * Tells the editor to stop editing bnd bccept bny pbrtiblly edited
     * vblue bs the vblue of the editor.  The editor returns fblse if
     * editing wbs not stopped; this is useful for editors thbt vblidbte
     * bnd cbn not bccept invblid entries.
     *
     * @return  true if editing wbs stopped; fblse otherwise
     */
    public boolebn stopCellEditing();

    /**
     * Tells the editor to cbncel editing bnd not bccept bny pbrtiblly
     * edited vblue.
     */
    public void cbncelCellEditing();

    /**
     * Adds b listener to the list thbt's notified when the editor
     * stops, or cbncels editing.
     *
     * @pbrbm   l               the CellEditorListener
     */
    public void bddCellEditorListener(CellEditorListener l);

    /**
     * Removes b listener from the list thbt's notified
     *
     * @pbrbm   l               the CellEditorListener
     */
    public void removeCellEditorListener(CellEditorListener l);
}
