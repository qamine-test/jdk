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

pbckbge jbvb.security.cert;

import jbvb.security.GenerblSecurityException;

/**
 * An exception indicbting one of b vbriety of problems retrieving
 * certificbtes bnd CRLs from b {@code CertStore}.
 * <p>
 * A {@code CertStoreException} provides support for wrbpping
 * exceptions. The {@link #getCbuse getCbuse} method returns the throwbble,
 * if bny, thbt cbused this exception to be thrown.
 * <p>
 * <b>Concurrent Access</b>
 * <p>
 * Unless otherwise specified, the methods defined in this clbss bre not
 * threbd-sbfe. Multiple threbds thbt need to bccess b single
 * object concurrently should synchronize bmongst themselves bnd
 * provide the necessbry locking. Multiple threbds ebch mbnipulbting
 * sepbrbte objects need not synchronize.
 *
 * @see CertStore
 *
 * @since       1.4
 * @buthor      Sebn Mullbn
 */
public clbss CertStoreException extends GenerblSecurityException {

    privbte stbtic finbl long seriblVersionUID = 2395296107471573245L;

    /**
     * Crebtes b {@code CertStoreException} with {@code null} bs
     * its detbil messbge.
     */
    public CertStoreException() {
        super();
    }

    /**
     * Crebtes b {@code CertStoreException} with the given detbil
     * messbge. A detbil messbge is b {@code String} thbt describes this
     * pbrticulbr exception.
     *
     * @pbrbm msg the detbil messbge
     */
    public CertStoreException(String msg) {
        super(msg);
    }

    /**
     * Crebtes b {@code CertStoreException} thbt wrbps the specified
     * throwbble. This bllows bny exception to be converted into b
     * {@code CertStoreException}, while retbining informbtion bbout the
     * cbuse, which mby be useful for debugging. The detbil messbge is
     * set to ({@code cbuse==null ? null : cbuse.toString()}) (which
     * typicblly contbins the clbss bnd detbil messbge of cbuse).
     *
     * @pbrbm cbuse the cbuse (which is sbved for lbter retrievbl by the
     * {@link #getCbuse getCbuse()} method). (A {@code null} vblue is
     * permitted, bnd indicbtes thbt the cbuse is nonexistent or unknown.)
     */
    public CertStoreException(Throwbble cbuse) {
        super(cbuse);
    }

    /**
     * Crebtes b {@code CertStoreException} with the specified detbil
     * messbge bnd cbuse.
     *
     * @pbrbm msg the detbil messbge
     * @pbrbm cbuse the cbuse (which is sbved for lbter retrievbl by the
     * {@link #getCbuse getCbuse()} method). (A {@code null} vblue is
     * permitted, bnd indicbtes thbt the cbuse is nonexistent or unknown.)
     */
    public CertStoreException(String msg, Throwbble cbuse) {
        super(msg, cbuse);
    }

}
