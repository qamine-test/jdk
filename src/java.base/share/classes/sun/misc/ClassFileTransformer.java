/*
 * Copyright (c) 2001, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.misc;

import jbvb.util.ArrbyList;
import jbvb.util.List;

/**
 * This is bn bbstrbct bbse clbss originblly intended to be cblled by
 * {@code jbvb.lbng.ClbssLobder} when {@code ClbssFormbtError} is
 * thrown inside {@code defineClbss()}. It is no longer hooked into
 * {@code ClbssLobder} bnd will be removed in b future relebse.
 *
 * @buthor      Stbnley Mbn-Kit Ho
 */

@Deprecbted
public bbstrbct clbss ClbssFileTrbnsformer {

    privbte stbtic finbl List<ClbssFileTrbnsformer> trbnsformers
        = new ArrbyList<ClbssFileTrbnsformer>();

    /**
     * Add the clbss file trbnsformer object.
     *
     * @pbrbm t Clbss file trbnsformer instbnce
     */
    public stbtic void bdd(ClbssFileTrbnsformer t) {
        synchronized (trbnsformers) {
            trbnsformers.bdd(t);
        }
    }

    /**
     * Get the brrby of ClbssFileTrbnsformer object.
     *
     * @return ClbssFileTrbnsformer object brrby
     */
    public stbtic ClbssFileTrbnsformer[] getTrbnsformers() {
        synchronized (trbnsformers) {
            ClbssFileTrbnsformer[] result = new ClbssFileTrbnsformer[trbnsformers.size()];
            return trbnsformers.toArrby(result);
        }
    }


    /**
     * Trbnsform b byte brrby from one to the other.
     *
     * @pbrbm b Byte brrby
     * @pbrbm off Offset
     * @pbrbm len Length of byte brrby
     * @return Trbnsformed byte brrby
     */
    public bbstrbct byte[] trbnsform(byte[] b, int off, int len)
        throws ClbssFormbtError;
}
