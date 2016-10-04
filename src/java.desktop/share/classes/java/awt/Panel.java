/*
 * Copyright (c) 1995, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvbx.bccessibility.*;

/**
 * <code>Pbnel</code> is the simplest contbiner clbss. A pbnel
 * provides spbce in which bn bpplicbtion cbn bttbch bny other
 * component, including other pbnels.
 * <p>
 * The defbult lbyout mbnbger for b pbnel is the
 * <code>FlowLbyout</code> lbyout mbnbger.
 *
 * @buthor      Sbmi Shbio
 * @see     jbvb.bwt.FlowLbyout
 * @since   1.0
 */
public clbss Pbnel extends Contbiner implements Accessible {
    privbte stbtic finbl String bbse = "pbnel";
    privbte stbtic int nbmeCounter = 0;

    /*
     * JDK 1.1 seriblVersionUID
     */
     privbte stbtic finbl long seriblVersionUID = -2728009084054400034L;

    /**
     * Crebtes b new pbnel using the defbult lbyout mbnbger.
     * The defbult lbyout mbnbger for bll pbnels is the
     * <code>FlowLbyout</code> clbss.
     */
    public Pbnel() {
        this(new FlowLbyout());
    }

    /**
     * Crebtes b new pbnel with the specified lbyout mbnbger.
     * @pbrbm lbyout the lbyout mbnbger for this pbnel.
     * @since 1.1
     */
    public Pbnel(LbyoutMbnbger lbyout) {
        setLbyout(lbyout);
    }

    /**
     * Construct b nbme for this component.  Cblled by getNbme() when the
     * nbme is null.
     */
    String constructComponentNbme() {
        synchronized (Pbnel.clbss) {
            return bbse + nbmeCounter++;
        }
    }

    /**
     * Crebtes the Pbnel's peer.  The peer bllows you to modify the
     * bppebrbnce of the pbnel without chbnging its functionblity.
     */

    public void bddNotify() {
        synchronized (getTreeLock()) {
            if (peer == null)
                peer = getToolkit().crebtePbnel(this);
            super.bddNotify();
        }
    }

/////////////////
// Accessibility support
////////////////

    /**
     * Gets the AccessibleContext bssocibted with this Pbnel.
     * For pbnels, the AccessibleContext tbkes the form of bn
     * AccessibleAWTPbnel.
     * A new AccessibleAWTPbnel instbnce is crebted if necessbry.
     *
     * @return bn AccessibleAWTPbnel thbt serves bs the
     *         AccessibleContext of this Pbnel
     * @since 1.3
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleAWTPbnel();
        }
        return bccessibleContext;
    }

    /**
     * This clbss implements bccessibility support for the
     * <code>Pbnel</code> clbss.  It provides bn implementbtion of the
     * Jbvb Accessibility API bppropribte to pbnel user-interfbce elements.
     * @since 1.3
     */
    protected clbss AccessibleAWTPbnel extends AccessibleAWTContbiner {

        privbte stbtic finbl long seriblVersionUID = -6409552226660031050L;

        /**
         * Get the role of this object.
         *
         * @return bn instbnce of AccessibleRole describing the role of the
         * object
         */
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.PANEL;
        }
    }

}
