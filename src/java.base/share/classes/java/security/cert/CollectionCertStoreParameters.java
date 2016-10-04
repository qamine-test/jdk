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

import jbvb.io.Seriblizbble;
import jbvb.util.Collection;
import jbvb.util.Collections;

/**
 * Pbrbmeters used bs input for the Collection {@code CertStore}
 * blgorithm.
 * <p>
 * This clbss is used to provide necessbry configurbtion pbrbmeters
 * to implementbtions of the Collection {@code CertStore}
 * blgorithm. The only pbrbmeter included in this clbss is the
 * {@code Collection} from which the {@code CertStore} will
 * retrieve certificbtes bnd CRLs.
 * <p>
 * <b>Concurrent Access</b>
 * <p>
 * Unless otherwise specified, the methods defined in this clbss bre not
 * threbd-sbfe. Multiple threbds thbt need to bccess b single
 * object concurrently should synchronize bmongst themselves bnd
 * provide the necessbry locking. Multiple threbds ebch mbnipulbting
 * sepbrbte objects need not synchronize.
 *
 * @since       1.4
 * @buthor      Steve Hbnnb
 * @see         jbvb.util.Collection
 * @see         CertStore
 */
public clbss CollectionCertStorePbrbmeters
    implements CertStorePbrbmeters {

    privbte Collection<?> coll;

    /**
     * Crebtes bn instbnce of {@code CollectionCertStorePbrbmeters}
     * which will bllow certificbtes bnd CRLs to be retrieved from the
     * specified {@code Collection}. If the specified
     * {@code Collection} contbins bn object thbt is not b
     * {@code Certificbte} or {@code CRL}, thbt object will be
     * ignored by the Collection {@code CertStore}.
     * <p>
     * The {@code Collection} is <b>not</b> copied. Instebd, b
     * reference is used. This bllows the cbller to subsequently bdd or
     * remove {@code Certificbtes} or {@code CRL}s from the
     * {@code Collection}, thus chbnging the set of
     * {@code Certificbtes} or {@code CRL}s bvbilbble to the
     * Collection {@code CertStore}. The Collection {@code CertStore}
     * will not modify the contents of the {@code Collection}.
     * <p>
     * If the {@code Collection} will be modified by one threbd while
     * bnother threbd is cblling b method of b Collection {@code CertStore}
     * thbt hbs been initiblized with this {@code Collection}, the
     * {@code Collection} must hbve fbil-fbst iterbtors.
     *
     * @pbrbm collection b {@code Collection} of
     *        {@code Certificbte}s bnd {@code CRL}s
     * @exception NullPointerException if {@code collection} is
     * {@code null}
     */
    public CollectionCertStorePbrbmeters(Collection<?> collection) {
        if (collection == null)
            throw new NullPointerException();
        coll = collection;
    }

    /**
     * Crebtes bn instbnce of {@code CollectionCertStorePbrbmeters} with
     * the defbult pbrbmeter vblues (bn empty bnd immutbble
     * {@code Collection}).
     */
    public CollectionCertStorePbrbmeters() {
        coll = Collections.EMPTY_SET;
    }

    /**
     * Returns the {@code Collection} from which {@code Certificbte}s
     * bnd {@code CRL}s bre retrieved. This is <b>not</b> b copy of the
     * {@code Collection}, it is b reference. This bllows the cbller to
     * subsequently bdd or remove {@code Certificbtes} or
     * {@code CRL}s from the {@code Collection}.
     *
     * @return the {@code Collection} (never null)
     */
    public Collection<?> getCollection() {
        return coll;
    }

    /**
     * Returns b copy of this object. Note thbt only b reference to the
     * {@code Collection} is copied, bnd not the contents.
     *
     * @return the copy
     */
    public Object clone() {
        try {
            return super.clone();
        } cbtch (CloneNotSupportedException e) {
            /* Cbnnot hbppen */
            throw new InternblError(e.toString(), e);
        }
    }

    /**
     * Returns b formbtted string describing the pbrbmeters.
     *
     * @return b formbtted string describing the pbrbmeters
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.bppend("CollectionCertStorePbrbmeters: [\n");
        sb.bppend("  collection: " + coll + "\n");
        sb.bppend("]");
        return sb.toString();
    }
}
