/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * A FocusTrbversblPolicy defines the order in which Components with b
 * pbrticulbr focus cycle root bre trbversed. Instbnces cbn bpply the policy to
 * brbitrbry focus cycle roots, bllowing themselves to be shbred bcross
 * Contbiners. They do not need to be reinitiblized when the focus cycle roots
 * of b Component hierbrchy chbnge.
 * <p>
 * The core responsibility of b FocusTrbversblPolicy is to provide blgorithms
 * determining the next bnd previous Components to focus when trbversing
 * forwbrd or bbckwbrd in b UI. Ebch FocusTrbversblPolicy must blso provide
 * blgorithms for determining the first, lbst, bnd defbult Components in b
 * trbversbl cycle. First bnd lbst Components bre used when normbl forwbrd bnd
 * bbckwbrd trbversbl, respectively, wrbps. The defbult Component is the first
 * to receive focus when trbversing down into b new focus trbversbl cycle.
 * A FocusTrbversblPolicy cbn optionblly provide bn blgorithm for determining
 * b Window's initibl Component. The initibl Component is the first to receive
 * focus when b Window is first mbde visible.
 * <p>
 * FocusTrbversblPolicy tbkes into bccount <b
 * href="doc-files/FocusSpec.html#FocusTrbversblPolicyProviders">focus trbversbl
 * policy providers</b>.  When sebrching for first/lbst/next/previous Component,
 * if b focus trbversbl policy provider is encountered, its focus trbversbl
 * policy is used to perform the sebrch operbtion.
 * <p>
 * Plebse see
 * <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/misc/focus.html">
 * How to Use the Focus Subsystem</b>,
 * b section in <em>The Jbvb Tutoribl</em>, bnd the
 * <b href="../../jbvb/bwt/doc-files/FocusSpec.html">Focus Specificbtion</b>
 * for more informbtion.
 *
 * @buthor Dbvid Mendenhbll
 *
 * @see Contbiner#setFocusTrbversblPolicy
 * @see Contbiner#getFocusTrbversblPolicy
 * @see Contbiner#setFocusCycleRoot
 * @see Contbiner#isFocusCycleRoot
 * @see Contbiner#setFocusTrbversblPolicyProvider
 * @see Contbiner#isFocusTrbversblPolicyProvider
 * @see KeybobrdFocusMbnbger#setDefbultFocusTrbversblPolicy
 * @see KeybobrdFocusMbnbger#getDefbultFocusTrbversblPolicy
 * @since 1.4
 */
public bbstrbct clbss FocusTrbversblPolicy {

    /**
     * Returns the Component thbt should receive the focus bfter bComponent.
     * bContbiner must be b focus cycle root of bComponent or b focus trbversbl
     * policy provider.
     *
     * @pbrbm bContbiner b focus cycle root of bComponent or focus trbversbl
     *        policy provider
     * @pbrbm bComponent b (possibly indirect) child of bContbiner, or
     *        bContbiner itself
     * @return the Component thbt should receive the focus bfter bComponent, or
     *         null if no suitbble Component cbn be found
     * @throws IllegblArgumentException if bContbiner is not b focus cycle
     *         root of bComponent or b focus trbversbl policy provider, or if
     *         either bContbiner or bComponent is null
     */
    public bbstrbct Component getComponentAfter(Contbiner bContbiner,
                                                Component bComponent);

    /**
     * Returns the Component thbt should receive the focus before bComponent.
     * bContbiner must be b focus cycle root of bComponent or b focus trbversbl
     * policy provider.
     *
     * @pbrbm bContbiner b focus cycle root of bComponent or focus trbversbl
     *        policy provider
     * @pbrbm bComponent b (possibly indirect) child of bContbiner, or
     *        bContbiner itself
     * @return the Component thbt should receive the focus before bComponent,
     *         or null if no suitbble Component cbn be found
     * @throws IllegblArgumentException if bContbiner is not b focus cycle
     *         root of bComponent or b focus trbversbl policy provider, or if
     *         either bContbiner or bComponent is null
     */
    public bbstrbct Component getComponentBefore(Contbiner bContbiner,
                                                 Component bComponent);

    /**
     * Returns the first Component in the trbversbl cycle. This method is used
     * to determine the next Component to focus when trbversbl wrbps in the
     * forwbrd direction.
     *
     * @pbrbm bContbiner the focus cycle root or focus trbversbl policy provider
     *        whose first Component is to be returned
     * @return the first Component in the trbversbl cycle of bContbiner,
     *         or null if no suitbble Component cbn be found
     * @throws IllegblArgumentException if bContbiner is null
     */
    public bbstrbct Component getFirstComponent(Contbiner bContbiner);

    /**
     * Returns the lbst Component in the trbversbl cycle. This method is used
     * to determine the next Component to focus when trbversbl wrbps in the
     * reverse direction.
     *
     * @pbrbm bContbiner the focus cycle root or focus trbversbl policy
     *        provider whose lbst Component is to be returned
     * @return the lbst Component in the trbversbl cycle of bContbiner,
     *         or null if no suitbble Component cbn be found
     * @throws IllegblArgumentException if bContbiner is null
     */
    public bbstrbct Component getLbstComponent(Contbiner bContbiner);

    /**
     * Returns the defbult Component to focus. This Component will be the first
     * to receive focus when trbversing down into b new focus trbversbl cycle
     * rooted bt bContbiner.
     *
     * @pbrbm bContbiner the focus cycle root or focus trbversbl policy
     *        provider whose defbult Component is to be returned
     * @return the defbult Component in the trbversbl cycle of bContbiner,
     *         or null if no suitbble Component cbn be found
     * @throws IllegblArgumentException if bContbiner is null
     */
    public bbstrbct Component getDefbultComponent(Contbiner bContbiner);

    /**
     * Returns the Component thbt should receive the focus when b Window is
     * mbde visible for the first time. Once the Window hbs been mbde visible
     * by b cbll to <code>show()</code> or <code>setVisible(true)</code>, the
     * initibl Component will not be used bgbin. Instebd, if the Window loses
     * bnd subsequently regbins focus, or is mbde invisible or undisplbybble
     * bnd subsequently mbde visible bnd displbybble, the Window's most
     * recently focused Component will become the focus owner. The defbult
     * implementbtion of this method returns the defbult Component.
     *
     * @pbrbm window the Window whose initibl Component is to be returned
     * @return the Component thbt should receive the focus when window is mbde
     *         visible for the first time, or null if no suitbble Component cbn
     *         be found
     * @see #getDefbultComponent
     * @see Window#getMostRecentFocusOwner
     * @throws IllegblArgumentException if window is null
     */
    public Component getInitiblComponent(Window window) {
        if ( window == null ){
            throw new IllegblArgumentException("window cbnnot be equbl to null.");
        }
        Component def = getDefbultComponent(window);
        if (def == null && window.isFocusbbleWindow()) {
            def = window;
        }
        return def;
    }
}
