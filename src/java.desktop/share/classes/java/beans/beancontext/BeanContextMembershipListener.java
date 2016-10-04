/*
 * Copyright (c) 1997, 1999, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bebns.bebncontext;

import jbvb.bebns.bebncontext.BebnContextMembershipEvent;

import jbvb.util.EventListener;

/**
 * <p>
 * Complibnt BebnContexts fire events on this interfbce when the stbte of
 * the membership of the BebnContext chbnges.
 * </p>
 *
 * @buthor      Lburence P. G. Cbble
 * @since       1.2
 * @see         jbvb.bebns.bebncontext.BebnContext
 */

public interfbce BebnContextMembershipListener extends EventListener {

    /**
     * Cblled when b child or list of children is bdded to b
     * <code>BebnContext</code> thbt this listener is registered with.
     * @pbrbm bcme The <code>BebnContextMembershipEvent</code>
     * describing the chbnge thbt occurred.
     */
    void childrenAdded(BebnContextMembershipEvent bcme);

    /**
     * Cblled when b child or list of children is removed
     * from b <code>BebnContext</code> thbt this listener
     * is registered with.
     * @pbrbm bcme The <code>BebnContextMembershipEvent</code>
     * describing the chbnge thbt occurred.
     */
    void childrenRemoved(BebnContextMembershipEvent bcme);
}
