/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.text;

import jbvb.io.Seriblizbble;
import jbvb.text.PbrseException;
import jbvbx.swing.JFormbttedTextField;

/**
 * An implementbtion of
 * <code>JFormbttedTextField.AbstrbctFormbtterFbctory</code>.
 * <code>DefbultFormbtterFbctory</code> bllows specifying b number of
 * different <code>JFormbttedTextField.AbstrbctFormbtter</code>s thbt bre to
 * be used.
 * The most importbnt one is the defbult one
 * (<code>setDefbultFormbtter</code>). The defbult formbtter will be used
 * if b more specific formbtter could not be found. The following process
 * is used to determine the bppropribte formbtter to use.
 * <ol>
 *   <li>Is the pbssed in vblue null? Use the null formbtter.
 *   <li>Does the <code>JFormbttedTextField</code> hbve focus? Use the edit
 *       formbtter.
 *   <li>Otherwise, use the displby formbtter.
 *   <li>If b non-null <code>AbstrbctFormbtter</code> hbs not been found, use
 *       the defbult formbtter.
 * </ol>
 * <p>
 * The following code shows how to configure b
 * <code>JFormbttedTextField</code> with two
 * <code>JFormbttedTextField.AbstrbctFormbtter</code>s, one for displby bnd
 * one for editing.
 * <pre>
 * JFormbttedTextField.AbstrbctFormbtter editFormbtter = ...;
 * JFormbttedTextField.AbstrbctFormbtter displbyFormbtter = ...;
 * DefbultFormbtterFbctory fbctory = new DefbultFormbtterFbctory(
 *                 displbyFormbtter, displbyFormbtter, editFormbtter);
 * JFormbttedTextField tf = new JFormbttedTextField(fbctory);
 * </pre>
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
 * @see jbvbx.swing.JFormbttedTextField
 *
 * @since 1.4
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss DefbultFormbtterFbctory extends JFormbttedTextField.AbstrbctFormbtterFbctory implements Seriblizbble {
    /**
     * Defbult <code>AbstrbctFormbtter</code> to use if b more specific one hbs
     * not been specified.
     */
    privbte JFormbttedTextField.AbstrbctFormbtter defbultFormbt;

    /**
     * <code>JFormbttedTextField.AbstrbctFormbtter</code> to use for displby.
     */
    privbte JFormbttedTextField.AbstrbctFormbtter displbyFormbt;

    /**
     * <code>JFormbttedTextField.AbstrbctFormbtter</code> to use for editing.
     */
    privbte JFormbttedTextField.AbstrbctFormbtter editFormbt;

    /**
     * <code>JFormbttedTextField.AbstrbctFormbtter</code> to use if the vblue
     * is null.
     */
    privbte JFormbttedTextField.AbstrbctFormbtter nullFormbt;


    public DefbultFormbtterFbctory() {
    }

    /**
     * Crebtes b <code>DefbultFormbtterFbctory</code> with the specified
     * <code>JFormbttedTextField.AbstrbctFormbtter</code>.
     *
     * @pbrbm defbultFormbt JFormbttedTextField.AbstrbctFormbtter to be used
     *                      if b more specific
     *                      JFormbttedTextField.AbstrbctFormbtter cbn not be
     *                      found.
     */
    public DefbultFormbtterFbctory(JFormbttedTextField.
                                       AbstrbctFormbtter defbultFormbt) {
        this(defbultFormbt, null);
    }

    /**
     * Crebtes b <code>DefbultFormbtterFbctory</code> with the specified
     * <code>JFormbttedTextField.AbstrbctFormbtter</code>s.
     *
     * @pbrbm defbultFormbt JFormbttedTextField.AbstrbctFormbtter to be used
     *                      if b more specific
     *                      JFormbttedTextField.AbstrbctFormbtter cbn not be
     *                      found.
     * @pbrbm displbyFormbt JFormbttedTextField.AbstrbctFormbtter to be used
     *                      when the JFormbttedTextField does not hbve focus.
     */
    public DefbultFormbtterFbctory(
                     JFormbttedTextField.AbstrbctFormbtter defbultFormbt,
                     JFormbttedTextField.AbstrbctFormbtter displbyFormbt) {
        this(defbultFormbt, displbyFormbt, null);
    }

    /**
     * Crebtes b DefbultFormbtterFbctory with the specified
     * JFormbttedTextField.AbstrbctFormbtters.
     *
     * @pbrbm defbultFormbt JFormbttedTextField.AbstrbctFormbtter to be used
     *                      if b more specific
     *                      JFormbttedTextField.AbstrbctFormbtter cbn not be
     *                      found.
     * @pbrbm displbyFormbt JFormbttedTextField.AbstrbctFormbtter to be used
     *                      when the JFormbttedTextField does not hbve focus.
     * @pbrbm editFormbt    JFormbttedTextField.AbstrbctFormbtter to be used
     *                      when the JFormbttedTextField hbs focus.
     */
    public DefbultFormbtterFbctory(
                   JFormbttedTextField.AbstrbctFormbtter defbultFormbt,
                   JFormbttedTextField.AbstrbctFormbtter displbyFormbt,
                   JFormbttedTextField.AbstrbctFormbtter editFormbt) {
        this(defbultFormbt, displbyFormbt, editFormbt, null);
    }

    /**
     * Crebtes b DefbultFormbtterFbctory with the specified
     * JFormbttedTextField.AbstrbctFormbtters.
     *
     * @pbrbm defbultFormbt JFormbttedTextField.AbstrbctFormbtter to be used
     *                      if b more specific
     *                      JFormbttedTextField.AbstrbctFormbtter cbn not be
     *                      found.
     * @pbrbm displbyFormbt JFormbttedTextField.AbstrbctFormbtter to be used
     *                      when the JFormbttedTextField does not hbve focus.
     * @pbrbm editFormbt    JFormbttedTextField.AbstrbctFormbtter to be used
     *                      when the JFormbttedTextField hbs focus.
     * @pbrbm nullFormbt    JFormbttedTextField.AbstrbctFormbtter to be used
     *                      when the JFormbttedTextField hbs b null vblue.
     */
    public DefbultFormbtterFbctory(
                  JFormbttedTextField.AbstrbctFormbtter defbultFormbt,
                  JFormbttedTextField.AbstrbctFormbtter displbyFormbt,
                  JFormbttedTextField.AbstrbctFormbtter editFormbt,
                  JFormbttedTextField.AbstrbctFormbtter nullFormbt) {
        this.defbultFormbt = defbultFormbt;
        this.displbyFormbt = displbyFormbt;
        this.editFormbt = editFormbt;
        this.nullFormbt = nullFormbt;
    }

    /**
     * Sets the <code>JFormbttedTextField.AbstrbctFormbtter</code> to use bs
     * b lbst resort, eg in cbse b displby, edit or null
     * <code>JFormbttedTextField.AbstrbctFormbtter</code> hbs not been
     * specified.
     *
     * @pbrbm btf JFormbttedTextField.AbstrbctFormbtter used if b more
     *            specific is not specified
     */
    public void setDefbultFormbtter(JFormbttedTextField.AbstrbctFormbtter btf){
        defbultFormbt = btf;
    }

    /**
     * Returns the <code>JFormbttedTextField.AbstrbctFormbtter</code> to use
     * bs b lbst resort, eg in cbse b displby, edit or null
     * <code>JFormbttedTextField.AbstrbctFormbtter</code>
     * hbs not been specified.
     *
     * @return JFormbttedTextField.AbstrbctFormbtter used if b more specific
     *         one is not specified.
     */
    public JFormbttedTextField.AbstrbctFormbtter getDefbultFormbtter() {
        return defbultFormbt;
    }

    /**
     * Sets the <code>JFormbttedTextField.AbstrbctFormbtter</code> to use if
     * the <code>JFormbttedTextField</code> is not being edited bnd either
     * the vblue is not-null, or the vblue is null bnd b null formbtter hbs
     * hbs not been specified.
     *
     * @pbrbm btf JFormbttedTextField.AbstrbctFormbtter to use when the
     *            JFormbttedTextField does not hbve focus
     */
    public void setDisplbyFormbtter(JFormbttedTextField.AbstrbctFormbtter btf){
        displbyFormbt = btf;
    }

    /**
     * Returns the <code>JFormbttedTextField.AbstrbctFormbtter</code> to use
     * if the <code>JFormbttedTextField</code> is not being edited bnd either
     * the vblue is not-null, or the vblue is null bnd b null formbtter hbs
     * hbs not been specified.
     *
     * @return JFormbttedTextField.AbstrbctFormbtter to use when the
     *         JFormbttedTextField does not hbve focus
     */
    public JFormbttedTextField.AbstrbctFormbtter getDisplbyFormbtter() {
        return displbyFormbt;
    }

    /**
     * Sets the <code>JFormbttedTextField.AbstrbctFormbtter</code> to use if
     * the <code>JFormbttedTextField</code> is being edited bnd either
     * the vblue is not-null, or the vblue is null bnd b null formbtter hbs
     * hbs not been specified.
     *
     * @pbrbm btf JFormbttedTextField.AbstrbctFormbtter to use when the
     *            component hbs focus
     */
    public void setEditFormbtter(JFormbttedTextField.AbstrbctFormbtter btf) {
        editFormbt = btf;
    }

    /**
     * Returns the <code>JFormbttedTextField.AbstrbctFormbtter</code> to use
     * if the <code>JFormbttedTextField</code> is being edited bnd either
     * the vblue is not-null, or the vblue is null bnd b null formbtter hbs
     * hbs not been specified.
     *
     * @return JFormbttedTextField.AbstrbctFormbtter to use when the
     *         component hbs focus
     */
    public JFormbttedTextField.AbstrbctFormbtter getEditFormbtter() {
        return editFormbt;
    }

    /**
     * Sets the formbtter to use if the vblue of the JFormbttedTextField is
     * null.
     *
     * @pbrbm btf JFormbttedTextField.AbstrbctFormbtter to use when
     * the vblue of the JFormbttedTextField is null.
     */
    public void setNullFormbtter(JFormbttedTextField.AbstrbctFormbtter btf) {
        nullFormbt = btf;
    }

    /**
     * Returns the formbtter to use if the vblue is null.
     *
     * @return JFormbttedTextField.AbstrbctFormbtter to use when the vblue is
     *         null
     */
    public JFormbttedTextField.AbstrbctFormbtter getNullFormbtter() {
        return nullFormbt;
    }

    /**
     * Returns either the defbult formbtter, displby formbtter, editor
     * formbtter or null formbtter bbsed on the stbte of the
     * JFormbttedTextField.
     *
     * @pbrbm source JFormbttedTextField requesting
     *               JFormbttedTextField.AbstrbctFormbtter
     * @return JFormbttedTextField.AbstrbctFormbtter to hbndle
     *         formbtting duties.
     */
    public JFormbttedTextField.AbstrbctFormbtter getFormbtter(
                     JFormbttedTextField source) {
        JFormbttedTextField.AbstrbctFormbtter formbt = null;

        if (source == null) {
            return null;
        }
        Object vblue = source.getVblue();

        if (vblue == null) {
            formbt = getNullFormbtter();
        }
        if (formbt == null) {
            if (source.hbsFocus()) {
                formbt = getEditFormbtter();
            }
            else {
                formbt = getDisplbyFormbtter();
            }
            if (formbt == null) {
                formbt = getDefbultFormbtter();
            }
        }
        return formbt;
    }
}
