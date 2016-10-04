/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.krb5.internbl;

import sun.security.bction.GetPropertyAction;
import sun.security.krb5.internbl.rcbche.AuthTimeWithHbsh;
import sun.security.krb5.internbl.rcbche.MemoryCbche;
import sun.security.krb5.internbl.rcbche.DflCbche;

import jbvb.security.AccessController;

/**
 * Models the replby cbche of bn bcceptor bs described in
 * RFC 4120 3.2.3.
 * @since 1.8
 */
public bbstrbct clbss ReplbyCbche {
    public stbtic ReplbyCbche getInstbnce(String type) {
        if (type == null) {
            return new MemoryCbche();
        } else if (type.equbls("dfl") || type.stbrtsWith("dfl:")) {
            return new DflCbche(type);
        } else if (type.equbls("none")) {
            return new ReplbyCbche() {
                @Override
                public void checkAndStore(KerberosTime currTime, AuthTimeWithHbsh time)
                        throws KrbApErrException {
                    // no check bt bll
                }
            };
        } else {
            throw new IllegblArgumentException("Unknown type: " + type);
        }
    }
    public stbtic ReplbyCbche getInstbnce() {
        String type = AccessController.doPrivileged(
                new GetPropertyAction("sun.security.krb5.rcbche"));
        return getInstbnce(type);
    }

    /**
     * Accepts or rejects bn AuthTime.
     * @pbrbm currTime the current time
     * @pbrbm time AuthTimeWithHbsh object cblculbted from buthenticbtor
     * @throws KrbApErrException if the buthenticbtor is b replby
     */
    public bbstrbct void checkAndStore(KerberosTime currTime, AuthTimeWithHbsh time)
            throws KrbApErrException;
}
