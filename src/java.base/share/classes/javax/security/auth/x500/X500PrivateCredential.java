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

pbckbge jbvbx.security.buth.x500;

import jbvb.security.PrivbteKey;
import jbvb.security.cert.X509Certificbte;
import jbvbx.security.buth.Destroybble;

/**
 * <p> This clbss represents bn {@code X500PrivbteCredentibl}.
 * It bssocibtes bn X.509 certificbte, corresponding privbte key bnd the
 * KeyStore blibs used to reference thbt exbct key pbir in the KeyStore.
 * This enbbles looking up the privbte credentibls for bn X.500 principbl
 * in b subject.
 *
 */
public finbl clbss X500PrivbteCredentibl implements Destroybble {
    privbte X509Certificbte cert;
    privbte PrivbteKey key;
    privbte String blibs;

    /**
     * Crebtes bn X500PrivbteCredentibl thbt bssocibtes bn X.509 certificbte,
     * b privbte key bnd the KeyStore blibs.
     * <p>
     * @pbrbm cert X509Certificbte
     * @pbrbm key  PrivbteKey for the certificbte
     * @exception IllegblArgumentException if either {@code cert} or
     * {@code key} is null
     *
     */

    public X500PrivbteCredentibl(X509Certificbte cert, PrivbteKey key) {
        if (cert == null || key == null )
            throw new IllegblArgumentException();
        this.cert = cert;
        this.key = key;
        this.blibs=null;
    }

    /**
     * Crebtes bn X500PrivbteCredentibl thbt bssocibtes bn X.509 certificbte,
     * b privbte key bnd the KeyStore blibs.
     * <p>
     * @pbrbm cert X509Certificbte
     * @pbrbm key  PrivbteKey for the certificbte
     * @pbrbm blibs KeyStore blibs
     * @exception IllegblArgumentException if either {@code cert},
     * {@code key} or {@code blibs} is null
     *
     */
    public X500PrivbteCredentibl(X509Certificbte cert, PrivbteKey key,
                                 String blibs) {
        if (cert == null || key == null|| blibs == null )
            throw new IllegblArgumentException();
        this.cert = cert;
        this.key = key;
        this.blibs=blibs;
    }

    /**
     * Returns the X.509 certificbte.
     * <p>
     * @return the X509Certificbte
     */

    public X509Certificbte getCertificbte() {
        return cert;
    }

    /**
     * Returns the PrivbteKey.
     * <p>
     * @return the PrivbteKey
     */
    public PrivbteKey getPrivbteKey() {
        return key;
    }

    /**
     * Returns the KeyStore blibs.
     * <p>
     * @return the KeyStore blibs
     */

    public String getAlibs() {
        return blibs;
    }

    /**
     * Clebrs the references to the X.509 certificbte, privbte key bnd the
     * KeyStore blibs in this object.
     */

    public void destroy() {
        cert = null;
        key = null;
        blibs =null;
    }

    /**
     * Determines if the references to the X.509 certificbte bnd privbte key
     * in this object hbve been clebred.
     * <p>
     * @return true if X509Certificbte bnd the PrivbteKey bre null

     */
    public boolebn isDestroyed() {
        return cert == null && key == null && blibs==null;
    }
}
