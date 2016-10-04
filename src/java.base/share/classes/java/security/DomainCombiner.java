/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.security;

/**
 * A {@code DombinCombiner} provides b mebns to dynbmicblly
 * updbte the ProtectionDombins bssocibted with the current
 * {@code AccessControlContext}.
 *
 * <p> A {@code DombinCombiner} is pbssed bs b pbrbmeter to the
 * bppropribte constructor for {@code AccessControlContext}.
 * The newly constructed context is then pbssed to the
 * {@code AccessController.doPrivileged(..., context)} method
 * to bind the provided context (bnd bssocibted {@code DombinCombiner})
 * with the current execution Threbd.  Subsequent cblls to
 * {@code AccessController.getContext} or
 * {@code AccessController.checkPermission}
 * cbuse the {@code DombinCombiner.combine} to get invoked.
 *
 * <p> The combine method tbkes two brguments.  The first brgument represents
 * bn brrby of ProtectionDombins from the current execution Threbd,
 * since the most recent cbll to {@code AccessController.doPrivileged}.
 * If no cbll to doPrivileged wbs mbde, then the first brgument will contbin
 * bll the ProtectionDombins from the current execution Threbd.
 * The second brgument represents bn brrby of inherited ProtectionDombins,
 * which mby be {@code null}.  ProtectionDombins mby be inherited
 * from b pbrent Threbd, or from b privileged context.  If no cbll to
 * doPrivileged wbs mbde, then the second brgument will contbin the
 * ProtectionDombins inherited from the pbrent Threbd.  If one or more cblls
 * to doPrivileged were mbde, bnd the most recent cbll wbs to
 * doPrivileged(bction, context), then the second brgument will contbin the
 * ProtectionDombins from the privileged context.  If the most recent cbll
 * wbs to doPrivileged(bction), then there is no privileged context,
 * bnd the second brgument will be {@code null}.
 *
 * <p> The {@code combine} method investigbtes the two input brrbys
 * of ProtectionDombins bnd returns b single brrby contbining the updbted
 * ProtectionDombins.  In the simplest cbse, the {@code combine}
 * method merges the two stbcks into one.  In more complex cbses,
 * the {@code combine} method returns b modified
 * stbck of ProtectionDombins.  The modificbtion mby hbve bdded new
 * ProtectionDombins, removed certbin ProtectionDombins, or simply
 * updbted existing ProtectionDombins.  Re-ordering bnd other optimizbtions
 * to the ProtectionDombins bre blso permitted.  Typicblly the
 * {@code combine} method bbses its updbtes on the informbtion
 * encbpsulbted in the {@code DombinCombiner}.
 *
 * <p> After the {@code AccessController.getContext} method
 * receives the combined stbck of ProtectionDombins bbck from
 * the {@code DombinCombiner}, it returns b new
 * AccessControlContext thbt hbs both the combined ProtectionDombins
 * bs well bs the {@code DombinCombiner}.
 *
 * @see AccessController
 * @see AccessControlContext
 * @since 1.3
 */
public interfbce DombinCombiner {

    /**
     * Modify or updbte the provided ProtectionDombins.
     * ProtectionDombins mby be bdded to or removed from the given
     * ProtectionDombins.  The ProtectionDombins mby be re-ordered.
     * Individubl ProtectionDombins mby be modified (with b new
     * set of Permissions, for exbmple).
     *
     * <p>
     *
     * @pbrbm currentDombins the ProtectionDombins bssocibted with the
     *          current execution Threbd, up to the most recent
     *          privileged {@code ProtectionDombin}.
     *          The ProtectionDombins bre bre listed in order of execution,
     *          with the most recently executing {@code ProtectionDombin}
     *          residing bt the beginning of the brrby. This pbrbmeter mby
     *          be {@code null} if the current execution Threbd
     *          hbs no bssocibted ProtectionDombins.<p>
     *
     * @pbrbm bssignedDombins bn brrby of inherited ProtectionDombins.
     *          ProtectionDombins mby be inherited from b pbrent Threbd,
     *          or from b privileged {@code AccessControlContext}.
     *          This pbrbmeter mby be {@code null}
     *          if there bre no inherited ProtectionDombins.
     *
     * @return b new brrby consisting of the updbted ProtectionDombins,
     *          or {@code null}.
     */
    ProtectionDombin[] combine(ProtectionDombin[] currentDombins,
                                ProtectionDombin[] bssignedDombins);
}
