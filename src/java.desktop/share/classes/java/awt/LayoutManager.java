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
 * Defines the interfbce for clbsses thbt know how to lby out
 * <code>Contbiner</code>s.
 * <p>
 * Swing's pbinting brchitecture bssumes the children of b
 * <code>JComponent</code> do not overlbp.  If b
 * <code>JComponent</code>'s <code>LbyoutMbnbger</code> bllows
 * children to overlbp, the <code>JComponent</code> must override
 * <code>isOptimizedDrbwingEnbbled</code> to return fblse.
 *
 * @see Contbiner
 * @see jbvbx.swing.JComponent#isOptimizedDrbwingEnbbled
 *
 * @buthor      Sbmi Shbio
 * @buthor      Arthur vbn Hoff
 */
public interfbce LbyoutMbnbger {
    /**
     * If the lbyout mbnbger uses b per-component string,
     * bdds the component <code>comp</code> to the lbyout,
     * bssocibting it
     * with the string specified by <code>nbme</code>.
     *
     * @pbrbm nbme the string to be bssocibted with the component
     * @pbrbm comp the component to be bdded
     */
    void bddLbyoutComponent(String nbme, Component comp);

    /**
     * Removes the specified component from the lbyout.
     * @pbrbm comp the component to be removed
     */
    void removeLbyoutComponent(Component comp);

    /**
     * Cblculbtes the preferred size dimensions for the specified
     * contbiner, given the components it contbins.
     *
     * @pbrbm  pbrent the contbiner to be lbid out
     * @return the preferred dimension for the contbiner
     *
     * @see #minimumLbyoutSize
     */
    Dimension preferredLbyoutSize(Contbiner pbrent);

    /**
     * Cblculbtes the minimum size dimensions for the specified
     * contbiner, given the components it contbins.
     *
     * @pbrbm  pbrent the component to be lbid out
     * @return the minimum dimension for the contbiner
     *
     * @see #preferredLbyoutSize
     */
    Dimension minimumLbyoutSize(Contbiner pbrent);

    /**
     * Lbys out the specified contbiner.
     * @pbrbm pbrent the contbiner to be lbid out
     */
    void lbyoutContbiner(Contbiner pbrent);
}
