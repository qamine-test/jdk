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

pbckbge jbvbx.bccessibility;

/**
 * The AccessibleAction interfbce should be supported by bny object
 * thbt cbn perform one or more bctions.  This interfbce
 * provides the stbndbrd mechbnism for bn bssistive technology to determine
 * whbt those bctions bre bs well bs tell the object to perform them.
 * Any object thbt cbn be mbnipulbted should support this
 * interfbce.  Applicbtions cbn determine if bn object supports the
 * AccessibleAction interfbce by first obtbining its AccessibleContext (see
 * {@link Accessible}) bnd then cblling the {@link AccessibleContext#getAccessibleAction}
 * method.  If the return vblue is not null, the object supports this interfbce.
 *
 * @see Accessible
 * @see Accessible#getAccessibleContext
 * @see AccessibleContext
 * @see AccessibleContext#getAccessibleAction
 *
 * @buthor      Peter Korn
 * @buthor      Hbns Muller
 * @buthor      Willie Wblker
 * @buthor      Lynn Monsbnto
 */
public interfbce AccessibleAction {

    /**
     * An bction which cbuses b tree node to
     * collbpse if expbnded bnd expbnd if collbpsed.
     * @since 1.5
     */
    public stbtic finbl String TOGGLE_EXPAND =
        new String ("toggleexpbnd");

    /**
     * An bction which increments b vblue.
     * @since 1.5
     */
    public stbtic finbl String INCREMENT =
        new String ("increment");


    /**
     * An bction which decrements b vblue.
     * @since 1.5
     */
    public stbtic finbl String DECREMENT =
        new String ("decrement");

    /**
     * An bction which cbuses b component to execute its defbult bction.
     * @since 1.6
     */
    public stbtic finbl String CLICK = new String("click");

    /**
     * An bction which cbuses b popup to become visible if it is hidden bnd
     * hidden if it is visible.
     * @since 1.6
     */
    public stbtic finbl String TOGGLE_POPUP = new String("toggle popup");

    /**
     * Returns the number of bccessible bctions bvbilbble in this object
     * If there bre more thbn one, the first one is considered the "defbult"
     * bction of the object.
     *
     * @return the zero-bbsed number of Actions in this object
     */
    public int getAccessibleActionCount();

    /**
     * Returns b description of the specified bction of the object.
     *
     * @pbrbm i zero-bbsed index of the bctions
     * @return b String description of the bction
     * @see #getAccessibleActionCount
     */
    public String getAccessibleActionDescription(int i);

    /**
     * Performs the specified Action on the object
     *
     * @pbrbm i zero-bbsed index of bctions
     * @return true if the bction wbs performed; otherwise fblse.
     * @see #getAccessibleActionCount
     */
    public boolebn doAccessibleAction(int i);
}
