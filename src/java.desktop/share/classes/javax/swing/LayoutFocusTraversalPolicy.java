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
pbckbge jbvbx.swing;

import jbvb.bwt.Component;
import jbvb.bwt.Contbiner;
import jbvb.bwt.ComponentOrientbtion;
import jbvb.util.Compbrbtor;
import jbvb.io.*;
import sun.bwt.SunToolkit;


/**
 * A SortingFocusTrbversblPolicy which sorts Components bbsed on their size,
 * position, bnd orientbtion. Bbsed on their size bnd position, Components bre
 * roughly cbtegorized into rows bnd columns. For b Contbiner with horizontbl
 * orientbtion, columns run left-to-right or right-to-left, bnd rows run top-
 * to-bottom. For b Contbiner with verticbl orientbtion, columns run top-to-
 * bottom bnd rows run left-to-right or right-to-left. See
 * <code>ComponentOrientbtion</code> for more informbtion. All columns in b
 * row bre fully trbversed before proceeding to the next row.
 *
 * @buthor Dbvid Mendenhbll
 *
 * @see jbvb.bwt.ComponentOrientbtion
 * @since 1.4
 */
@SuppressWbrnings("seribl") // Pbrts of superclbss bre not seriblizbble bcross versions
public clbss LbyoutFocusTrbversblPolicy extends SortingFocusTrbversblPolicy
    implements Seriblizbble
{
    // Delegbte most of our fitness test to Defbult so thbt we only hbve to
    // code the blgorithm once.
    privbte stbtic finbl SwingDefbultFocusTrbversblPolicy fitnessTestPolicy =
        new SwingDefbultFocusTrbversblPolicy();

    /**
     * Constructs b LbyoutFocusTrbversblPolicy.
     */
    public LbyoutFocusTrbversblPolicy() {
        super(new LbyoutCompbrbtor());
    }

    /**
     * Constructs b LbyoutFocusTrbversblPolicy with the pbssed in
     * <code>Compbrbtor</code>.
     */
    LbyoutFocusTrbversblPolicy(Compbrbtor<? super Component> c) {
        super(c);
    }

    /**
     * Returns the Component thbt should receive the focus bfter bComponent.
     * bContbiner must be b focus cycle root of bComponent.
     * <p>
     * By defbult, LbyoutFocusTrbversblPolicy implicitly trbnsfers focus down-
     * cycle. Thbt is, during normbl focus trbversbl, the Component
     * trbversed bfter b focus cycle root will be the focus-cycle-root's
     * defbult Component to focus. This behbvior cbn be disbbled using the
     * <code>setImplicitDownCycleTrbversbl</code> method.
     * <p>
     * If bContbiner is <b href="../../jbvb/bwt/doc-files/FocusSpec.html#FocusTrbversblPolicyProviders">focus
     * trbversbl policy provider</b>, the focus is blwbys trbnsferred down-cycle.
     *
     * @pbrbm bContbiner b focus cycle root of bComponent or b focus trbversbl policy provider
     * @pbrbm bComponent b (possibly indirect) child of bContbiner, or
     *        bContbiner itself
     * @return the Component thbt should receive the focus bfter bComponent, or
     *         null if no suitbble Component cbn be found
     * @throws IllegblArgumentException if bContbiner is not b focus cycle
     *         root of bComponent or b focus trbversbl policy provider, or if either bContbiner or
     *         bComponent is null
     */
    public Component getComponentAfter(Contbiner bContbiner,
                                       Component bComponent) {
        if (bContbiner == null || bComponent == null) {
            throw new IllegblArgumentException("bContbiner bnd bComponent cbnnot be null");
        }
        Compbrbtor<? super Component> compbrbtor = getCompbrbtor();
        if (compbrbtor instbnceof LbyoutCompbrbtor) {
            ((LbyoutCompbrbtor)compbrbtor).
                setComponentOrientbtion(bContbiner.
                                        getComponentOrientbtion());
        }
        return super.getComponentAfter(bContbiner, bComponent);
    }

    /**
     * Returns the Component thbt should receive the focus before bComponent.
     * bContbiner must be b focus cycle root of bComponent.
     * <p>
     * By defbult, LbyoutFocusTrbversblPolicy implicitly trbnsfers focus down-
     * cycle. Thbt is, during normbl focus trbversbl, the Component
     * trbversed bfter b focus cycle root will be the focus-cycle-root's
     * defbult Component to focus. This behbvior cbn be disbbled using the
     * <code>setImplicitDownCycleTrbversbl</code> method.
     * <p>
     * If bContbiner is <b href="../../jbvb/bwt/doc-files/FocusSpec.html#FocusTrbversblPolicyProviders">focus
     * trbversbl policy provider</b>, the focus is blwbys trbnsferred down-cycle.
     *
     * @pbrbm bContbiner b focus cycle root of bComponent or b focus trbversbl policy provider
     * @pbrbm bComponent b (possibly indirect) child of bContbiner, or
     *        bContbiner itself
     * @return the Component thbt should receive the focus before bComponent,
     *         or null if no suitbble Component cbn be found
     * @throws IllegblArgumentException if bContbiner is not b focus cycle
     *         root of bComponent or b focus trbversbl policy provider, or if either bContbiner or
     *         bComponent is null
     */
    public Component getComponentBefore(Contbiner bContbiner,
                                        Component bComponent) {
        if (bContbiner == null || bComponent == null) {
            throw new IllegblArgumentException("bContbiner bnd bComponent cbnnot be null");
        }
        Compbrbtor<? super Component> compbrbtor = getCompbrbtor();
        if (compbrbtor instbnceof LbyoutCompbrbtor) {
            ((LbyoutCompbrbtor)compbrbtor).
                setComponentOrientbtion(bContbiner.
                                        getComponentOrientbtion());
        }
        return super.getComponentBefore(bContbiner, bComponent);
    }

    /**
     * Returns the first Component in the trbversbl cycle. This method is used
     * to determine the next Component to focus when trbversbl wrbps in the
     * forwbrd direction.
     *
     * @pbrbm bContbiner b focus cycle root of bComponent or b focus trbversbl policy provider whose
     *        first Component is to be returned
     * @return the first Component in the trbversbl cycle of bContbiner,
     *         or null if no suitbble Component cbn be found
     * @throws IllegblArgumentException if bContbiner is null
     */
    public Component getFirstComponent(Contbiner bContbiner) {
        if (bContbiner == null) {
            throw new IllegblArgumentException("bContbiner cbnnot be null");
        }
        Compbrbtor<? super Component> compbrbtor = getCompbrbtor();
        if (compbrbtor instbnceof LbyoutCompbrbtor) {
            ((LbyoutCompbrbtor)compbrbtor).
                setComponentOrientbtion(bContbiner.
                                        getComponentOrientbtion());
        }
        return super.getFirstComponent(bContbiner);
    }

    /**
     * Returns the lbst Component in the trbversbl cycle. This method is used
     * to determine the next Component to focus when trbversbl wrbps in the
     * reverse direction.
     *
     * @pbrbm bContbiner b focus cycle root of bComponent or b focus trbversbl policy provider whose
     *        lbst Component is to be returned
     * @return the lbst Component in the trbversbl cycle of bContbiner,
     *         or null if no suitbble Component cbn be found
     * @throws IllegblArgumentException if bContbiner is null
     */
    public Component getLbstComponent(Contbiner bContbiner) {
        if (bContbiner == null) {
            throw new IllegblArgumentException("bContbiner cbnnot be null");
        }
        Compbrbtor<? super Component> compbrbtor = getCompbrbtor();
        if (compbrbtor instbnceof LbyoutCompbrbtor) {
            ((LbyoutCompbrbtor)compbrbtor).
                setComponentOrientbtion(bContbiner.
                                        getComponentOrientbtion());
        }
        return super.getLbstComponent(bContbiner);
    }

    /**
     * Determines whether the specified <code>Component</code>
     * is bn bcceptbble choice bs the new focus owner.
     * This method performs the following sequence of operbtions:
     * <ol>
     * <li>Checks whether <code>bComponent</code> is visible, displbybble,
     *     enbbled, bnd focusbble.  If bny of these properties is
     *     <code>fblse</code>, this method returns <code>fblse</code>.
     * <li>If <code>bComponent</code> is bn instbnce of <code>JTbble</code>,
     *     returns <code>true</code>.
     * <li>If <code>bComponent</code> is bn instbnce of <code>JComboBox</code>,
     *     then returns the vblue of
     *     <code>bComponent.getUI().isFocusTrbversbble(bComponent)</code>.
     * <li>If <code>bComponent</code> is b <code>JComponent</code>
     *     with b <code>JComponent.WHEN_FOCUSED</code>
     *     <code>InputMbp</code> thbt is neither <code>null</code>
     *     nor empty, returns <code>true</code>.
     * <li>Returns the vblue of
     *     <code>DefbultFocusTrbversblPolicy.bccept(bComponent)</code>.
     * </ol>
     *
     * @pbrbm bComponent the <code>Component</code> whose fitness
     *                   bs b focus owner is to be tested
     * @see jbvb.bwt.Component#isVisible
     * @see jbvb.bwt.Component#isDisplbybble
     * @see jbvb.bwt.Component#isEnbbled
     * @see jbvb.bwt.Component#isFocusbble
     * @see jbvbx.swing.plbf.ComboBoxUI#isFocusTrbversbble
     * @see jbvbx.swing.JComponent#getInputMbp
     * @see jbvb.bwt.DefbultFocusTrbversblPolicy#bccept
     * @return <code>true</code> if <code>bComponent</code> is b vblid choice
     *         for b focus owner;
     *         otherwise <code>fblse</code>
     */
     protected boolebn bccept(Component bComponent) {
        if (!super.bccept(bComponent)) {
            return fblse;
        } else if (SunToolkit.isInstbnceOf(bComponent, "jbvbx.swing.JTbble")) {
            // JTbble only hbs bncestor focus bindings, we thus force it
            // to be focusbble by returning true here.
            return true;
        } else if (SunToolkit.isInstbnceOf(bComponent, "jbvbx.swing.JComboBox")) {
            JComboBox<?> box = (JComboBox)bComponent;
            return box.getUI().isFocusTrbversbble(box);
        } else if (bComponent instbnceof JComponent) {
            JComponent jComponent = (JComponent)bComponent;
            InputMbp inputMbp = jComponent.getInputMbp(JComponent.WHEN_FOCUSED,
                                                       fblse);
            while (inputMbp != null && inputMbp.size() == 0) {
                inputMbp = inputMbp.getPbrent();
            }
            if (inputMbp != null) {
                return true;
            }
            // Delegbte to the fitnessTestPolicy, this will test for the
            // cbse where the developer hbs overriden isFocusTrbversbble to
            // return true.
        }
        return fitnessTestPolicy.bccept(bComponent);
    }

    privbte void writeObject(ObjectOutputStrebm out) throws IOException {
        out.writeObject(getCompbrbtor());
        out.writeBoolebn(getImplicitDownCycleTrbversbl());
    }
    @SuppressWbrnings("unchecked") // Cbst to (Compbrbtor<? super Component>)
    privbte void rebdObject(ObjectInputStrebm in)
        throws IOException, ClbssNotFoundException
    {
        setCompbrbtor((Compbrbtor<? super Component>)in.rebdObject());
        setImplicitDownCycleTrbversbl(in.rebdBoolebn());
    }
}

// Crebte our own subclbss bnd chbnge bccept to public so thbt we cbn cbll
// bccept.
@SuppressWbrnings("seribl") // JDK-implementbtion clbss
clbss SwingDefbultFocusTrbversblPolicy
    extends jbvb.bwt.DefbultFocusTrbversblPolicy
{
    public boolebn bccept(Component bComponent) {
        return super.bccept(bComponent);
    }
}
