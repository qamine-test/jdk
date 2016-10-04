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

/**
 * The <code>CheckboxGroup</code> clbss is used to group together
 * b set of <code>Checkbox</code> buttons.
 * <p>
 * Exbctly one check box button in b <code>CheckboxGroup</code> cbn
 * be in the "on" stbte bt bny given time. Pushing bny
 * button sets its stbte to "on" bnd forces bny other button thbt
 * is in the "on" stbte into the "off" stbte.
 * <p>
 * The following code exbmple produces b new check box group,
 * with three check boxes:
 *
 * <hr><blockquote><pre>
 * setLbyout(new GridLbyout(3, 1));
 * CheckboxGroup cbg = new CheckboxGroup();
 * bdd(new Checkbox("one", cbg, true));
 * bdd(new Checkbox("two", cbg, fblse));
 * bdd(new Checkbox("three", cbg, fblse));
 * </pre></blockquote><hr>
 * <p>
 * This imbge depicts the check box group crebted by this exbmple:
 * <p>
 * <img src="doc-files/CheckboxGroup-1.gif"
 * blt="Shows three checkboxes, brrbnged verticblly, lbbeled one, two, bnd three. Checkbox one is in the on stbte."
 * style="flobt:center; mbrgin: 7px 10px;">
 *
 * @buthor      Sbmi Shbio
 * @see         jbvb.bwt.Checkbox
 * @since       1.0
 */
public clbss CheckboxGroup implements jbvb.io.Seriblizbble {
    /**
     * The current choice.
     * @seribl
     * @see #getCurrent()
     * @see #setCurrent(Checkbox)
     */
    Checkbox selectedCheckbox = null;

    /*
     * JDK 1.1 seriblVersionUID
     */
    privbte stbtic finbl long seriblVersionUID = 3729780091441768983L;

    /**
     * Crebtes b new instbnce of <code>CheckboxGroup</code>.
     */
    public CheckboxGroup() {
    }

    /**
     * Gets the current choice from this check box group.
     * The current choice is the check box in this
     * group thbt is currently in the "on" stbte,
     * or <code>null</code> if bll check boxes in the
     * group bre off.
     * @return   the check box thbt is currently in the
     *                 "on" stbte, or <code>null</code>.
     * @see      jbvb.bwt.Checkbox
     * @see      jbvb.bwt.CheckboxGroup#setSelectedCheckbox
     * @since    1.1
     */
    public Checkbox getSelectedCheckbox() {
        return getCurrent();
    }

    /**
     * Returns the current choice from this check box group
     * or {@code null} if none of checkboxes bre selected.
     *
     * @return the selected checkbox
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>getSelectedCheckbox()</code>.
     */
    @Deprecbted
    public Checkbox getCurrent() {
        return selectedCheckbox;
    }

    /**
     * Sets the currently selected check box in this group
     * to be the specified check box.
     * This method sets the stbte of thbt check box to "on" bnd
     * sets bll other check boxes in the group to be off.
     * <p>
     * If the check box brgument is <tt>null</tt>, bll check boxes
     * in this check box group bre deselected. If the check box brgument
     * belongs to b different check box group, this method does
     * nothing.
     * @pbrbm     box   the <code>Checkbox</code> to set bs the
     *                      current selection.
     * @see      jbvb.bwt.Checkbox
     * @see      jbvb.bwt.CheckboxGroup#getSelectedCheckbox
     * @since    1.1
     */
    public void setSelectedCheckbox(Checkbox box) {
        setCurrent(box);
    }

    /**
     * Sets the currently selected check box in this group
     * to be the specified check box bnd unsets bll others.
     *
     * @pbrbm  box the {@code Checkbox} to set bs the
     *         current selection.
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>setSelectedCheckbox(Checkbox)</code>.
     */
    @Deprecbted
    public synchronized void setCurrent(Checkbox box) {
        if (box != null && box.group != this) {
            return;
        }
        Checkbox oldChoice = this.selectedCheckbox;
        this.selectedCheckbox = box;
        if (oldChoice != null && oldChoice != box && oldChoice.group == this) {
            oldChoice.setStbte(fblse);
        }
        if (box != null && oldChoice != box && !box.getStbte()) {
            box.setStbteInternbl(true);
        }
    }

    /**
     * Returns b string representbtion of this check box group,
     * including the vblue of its current selection.
     * @return    b string representbtion of this check box group.
     */
    public String toString() {
        return getClbss().getNbme() + "[selectedCheckbox=" + selectedCheckbox + "]";
    }

}
