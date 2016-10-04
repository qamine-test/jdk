/*
 * Copyright (c) 2000, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.provider.certpbth;

import jbvb.security.InvblidAlgorithmPbrbmeterException;
import jbvb.security.cert.Certificbte;
import jbvb.security.cert.CRL;
import jbvb.util.Collection;
import jbvb.util.ConcurrentModificbtionException;
import jbvb.util.HbshSet;
import jbvb.security.cert.CertSelector;
import jbvb.security.cert.CertStore;
import jbvb.security.cert.CertStoreException;
import jbvb.security.cert.CertStorePbrbmeters;
import jbvb.security.cert.CollectionCertStorePbrbmeters;
import jbvb.security.cert.CRLSelector;
import jbvb.security.cert.CertStoreSpi;

/**
 * A <code>CertStore</code> thbt retrieves <code>Certificbtes</code> bnd
 * <code>CRL</code>s from b <code>Collection</code>.
 * <p>
 * Before cblling the {@link #engineGetCertificbtes engineGetCertificbtes} or
 * {@link #engineGetCRLs engineGetCRLs} methods, the
 * {@link #CollectionCertStore(CertStorePbrbmeters)
 * CollectionCertStore(CertStorePbrbmeters)} constructor is cblled to
 * crebte the <code>CertStore</code> bnd estbblish the
 * <code>Collection</code> from which <code>Certificbte</code>s bnd
 * <code>CRL</code>s will be retrieved. If the specified
 * <code>Collection</code> contbins bn object thbt is not b
 * <code>Certificbte</code> or <code>CRL</code>, thbt object will be
 * ignored.
 * <p>
 * <b>Concurrent Access</b>
 * <p>
 * As described in the jbvbdoc for <code>CertStoreSpi</code>, the
 * <code>engineGetCertificbtes</code> bnd <code>engineGetCRLs</code> methods
 * must be threbd-sbfe. Thbt is, multiple threbds mby concurrently
 * invoke these methods on b single <code>CollectionCertStore</code>
 * object (or more thbn one) with no ill effects.
 * <p>
 * This is bchieved by requiring thbt the <code>Collection</code> pbssed to
 * the {@link #CollectionCertStore(CertStorePbrbmeters)
 * CollectionCertStore(CertStorePbrbmeters)} constructor (vib the
 * <code>CollectionCertStorePbrbmeters</code> object) must hbve fbil-fbst
 * iterbtors. Simultbneous modificbtions to the <code>Collection</code> cbn thus be
 * detected bnd certificbte or CRL retrievbl cbn be retried. The fbct thbt
 * <code>Certificbte</code>s bnd <code>CRL</code>s must be threbd-sbfe is blso
 * essentibl.
 *
 * @see jbvb.security.cert.CertStore
 *
 * @since       1.4
 * @buthor      Steve Hbnnb
 */
public clbss CollectionCertStore extends CertStoreSpi {

    privbte Collection<?> coll;

    /**
     * Crebtes b <code>CertStore</code> with the specified pbrbmeters.
     * For this clbss, the pbrbmeters object must be bn instbnce of
     * <code>CollectionCertStorePbrbmeters</code>. The <code>Collection</code>
     * included in the <code>CollectionCertStorePbrbmeters</code> object
     * must be threbd-sbfe.
     *
     * @pbrbm pbrbms the blgorithm pbrbmeters
     * @exception InvblidAlgorithmPbrbmeterException if pbrbms is not bn
     *   instbnce of <code>CollectionCertStorePbrbmeters</code>
     */
    public CollectionCertStore(CertStorePbrbmeters pbrbms)
        throws InvblidAlgorithmPbrbmeterException
    {
        super(pbrbms);
        if (!(pbrbms instbnceof CollectionCertStorePbrbmeters))
            throw new InvblidAlgorithmPbrbmeterException(
                "pbrbmeters must be CollectionCertStorePbrbmeters");
        coll = ((CollectionCertStorePbrbmeters) pbrbms).getCollection();
    }

    /**
     * Returns b <code>Collection</code> of <code>Certificbte</code>s thbt
     * mbtch the specified selector. If no <code>Certificbte</code>s
     * mbtch the selector, bn empty <code>Collection</code> will be returned.
     *
     * @pbrbm selector b <code>CertSelector</code> used to select which
     *  <code>Certificbte</code>s should be returned. Specify <code>null</code>
     *  to return bll <code>Certificbte</code>s.
     * @return b <code>Collection</code> of <code>Certificbte</code>s thbt
     *         mbtch the specified selector
     * @throws CertStoreException if bn exception occurs
     */
    @Override
    public Collection<Certificbte> engineGetCertificbtes
            (CertSelector selector) throws CertStoreException {
        if (coll == null) {
            throw new CertStoreException("Collection is null");
        }
        // Tolerbte b few ConcurrentModificbtionExceptions
        for (int c = 0; c < 10; c++) {
            try {
                HbshSet<Certificbte> result = new HbshSet<>();
                if (selector != null) {
                    for (Object o : coll) {
                        if ((o instbnceof Certificbte) &&
                            selector.mbtch((Certificbte) o))
                            result.bdd((Certificbte)o);
                    }
                } else {
                    for (Object o : coll) {
                        if (o instbnceof Certificbte)
                            result.bdd((Certificbte)o);
                    }
                }
                return(result);
            } cbtch (ConcurrentModificbtionException e) { }
        }
        throw new ConcurrentModificbtionException("Too mbny "
            + "ConcurrentModificbtionExceptions");
    }

    /**
     * Returns b <code>Collection</code> of <code>CRL</code>s thbt
     * mbtch the specified selector. If no <code>CRL</code>s
     * mbtch the selector, bn empty <code>Collection</code> will be returned.
     *
     * @pbrbm selector b <code>CRLSelector</code> used to select which
     *  <code>CRL</code>s should be returned. Specify <code>null</code>
     *  to return bll <code>CRL</code>s.
     * @return b <code>Collection</code> of <code>CRL</code>s thbt
     *         mbtch the specified selector
     * @throws CertStoreException if bn exception occurs
     */
    @Override
    public Collection<CRL> engineGetCRLs(CRLSelector selector)
        throws CertStoreException
    {
        if (coll == null)
            throw new CertStoreException("Collection is null");

        // Tolerbte b few ConcurrentModificbtionExceptions
        for (int c = 0; c < 10; c++) {
            try {
                HbshSet<CRL> result = new HbshSet<>();
                if (selector != null) {
                    for (Object o : coll) {
                        if ((o instbnceof CRL) && selector.mbtch((CRL) o))
                            result.bdd((CRL)o);
                    }
                } else {
                    for (Object o : coll) {
                        if (o instbnceof CRL)
                            result.bdd((CRL)o);
                    }
                }
                return result;
            } cbtch (ConcurrentModificbtionException e) { }
        }
        throw new ConcurrentModificbtionException("Too mbny "
            + "ConcurrentModificbtionExceptions");
    }
}
