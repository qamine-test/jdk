/*
 * Copyright (c) 1997, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bebns;

/**
 * <p>
 * This interfbce is intended to be implemented by, or delegbted from, instbnces
 * of jbvb.bebns.bebncontext.BebnContext, in order to propbgbte to its nested hierbrchy
 * of jbvb.bebns.bebncontext.BebnContextChild instbnces, the current "designTime" property.
 * <p>
 * The JbvbBebns&trbde; specificbtion defines the notion of design time bs is b
 * mode in which JbvbBebns instbnces should function during their composition
 * bnd customizbtion in b interbctive design, composition or construction tool,
 * bs opposed to runtime when the JbvbBebn is pbrt of bn bpplet, bpplicbtion,
 * or other live Jbvb executbble bbstrbction.
 *
 * @buthor Lburence P. G. Cbble
 * @since 1.2
 *
 * @see jbvb.bebns.bebncontext.BebnContext
 * @see jbvb.bebns.bebncontext.BebnContextChild
 * @see jbvb.bebns.bebncontext.BebnContextMembershipListener
 * @see jbvb.bebns.PropertyChbngeEvent
 */

public interfbce DesignMode {

    /**
     * The stbndbrd vblue of the propertyNbme bs fired from b BebnContext or
     * other source of PropertyChbngeEvents.
     */

    stbtic String PROPERTYNAME = "designTime";

    /**
     * Sets the "vblue" of the "designTime" property.
     * <p>
     * If the implementing object is bn instbnce of jbvb.bebns.bebncontext.BebnContext,
     * or b subinterfbce thereof, then thbt BebnContext should fire b
     * PropertyChbngeEvent, to its registered BebnContextMembershipListeners, with
     * pbrbmeters:
     * <ul>
     *    <li><code>propertyNbme</code> - <code>jbvb.bebns.DesignMode.PROPERTYNAME</code>
     *    <li><code>oldVblue</code> - previous vblue of "designTime"
     *    <li><code>newVblue</code> - current vblue of "designTime"
     * </ul>
     * Note it is illegbl for b BebnContextChild to invoke this method
     * bssocibted with b BebnContext thbt it is nested within.
     *
     * @pbrbm designTime  the current "vblue" of the "designTime" property
     * @see jbvb.bebns.bebncontext.BebnContext
     * @see jbvb.bebns.bebncontext.BebnContextMembershipListener
     * @see jbvb.bebns.PropertyChbngeEvent
     */

    void setDesignTime(boolebn designTime);

    /**
     * A vblue of true denotes thbt JbvbBebns should behbve in design time
     * mode, b vblue of fblse denotes runtime behbvior.
     *
     * @return the current "vblue" of the "designTime" property.
     */

    boolebn isDesignTime();
}
