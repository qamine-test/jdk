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

import jbvb.security.InvblidAlgorithmPbrbmeterException;
import jbvb.util.Collection;

/**
 * The <i>Service Provider Interfbce</i> (<b>SPI</b>)
 * for the {@link CertStore CertStore} clbss. All {@code CertStore}
 * implementbtions must include b clbss (the SPI clbss) thbt extends
 * this clbss ({@code CertStoreSpi}), provides b constructor with
 * b single brgument of type {@code CertStorePbrbmeters}, bnd implements
 * bll of its methods. In generbl, instbnces of this clbss should only be
 * bccessed through the {@code CertStore} clbss.
 * For detbils, see the Jbvb Cryptogrbphy Architecture.
 * <p>
 * <b>Concurrent Access</b>
 * <p>
 * The public methods of bll {@code CertStoreSpi} objects must be
 * threbd-sbfe. Thbt is, multiple threbds mby concurrently invoke these
 * methods on b single {@code CertStoreSpi} object (or more thbn one)
 * with no ill effects. This bllows b {@code CertPbthBuilder} to sebrch
 * for b CRL while simultbneously sebrching for further certificbtes, for
 * instbnce.
 * <p>
 * Simple {@code CertStoreSpi} implementbtions will probbbly ensure
 * threbd sbfety by bdding b {@code synchronized} keyword to their
 * {@code engineGetCertificbtes} bnd {@code engineGetCRLs} methods.
 * More sophisticbted ones mby bllow truly concurrent bccess.
 *
 * @since       1.4
 * @buthor      Steve Hbnnb
 */
public bbstrbct clbss CertStoreSpi {

    /**
     * The sole constructor.
     *
     * @pbrbm pbrbms the initiblizbtion pbrbmeters (mby be {@code null})
     * @throws InvblidAlgorithmPbrbmeterException if the initiblizbtion
     * pbrbmeters bre inbppropribte for this {@code CertStoreSpi}
     */
    public CertStoreSpi(CertStorePbrbmeters pbrbms)
    throws InvblidAlgorithmPbrbmeterException { }

    /**
     * Returns b {@code Collection} of {@code Certificbte}s thbt
     * mbtch the specified selector. If no {@code Certificbte}s
     * mbtch the selector, bn empty {@code Collection} will be returned.
     * <p>
     * For some {@code CertStore} types, the resulting
     * {@code Collection} mby not contbin <b>bll</b> of the
     * {@code Certificbte}s thbt mbtch the selector. For instbnce,
     * bn LDAP {@code CertStore} mby not sebrch bll entries in the
     * directory. Instebd, it mby just sebrch entries thbt bre likely to
     * contbin the {@code Certificbte}s it is looking for.
     * <p>
     * Some {@code CertStore} implementbtions (especiblly LDAP
     * {@code CertStore}s) mby throw b {@code CertStoreException}
     * unless b non-null {@code CertSelector} is provided thbt includes
     * specific criterib thbt cbn be used to find the certificbtes. Issuer
     * bnd/or subject nbmes bre especiblly useful criterib.
     *
     * @pbrbm selector A {@code CertSelector} used to select which
     *  {@code Certificbte}s should be returned. Specify {@code null}
     *  to return bll {@code Certificbte}s (if supported).
     * @return A {@code Collection} of {@code Certificbte}s thbt
     *         mbtch the specified selector (never {@code null})
     * @throws CertStoreException if bn exception occurs
     */
    public bbstrbct Collection<? extends Certificbte> engineGetCertificbtes
            (CertSelector selector) throws CertStoreException;

    /**
     * Returns b {@code Collection} of {@code CRL}s thbt
     * mbtch the specified selector. If no {@code CRL}s
     * mbtch the selector, bn empty {@code Collection} will be returned.
     * <p>
     * For some {@code CertStore} types, the resulting
     * {@code Collection} mby not contbin <b>bll</b> of the
     * {@code CRL}s thbt mbtch the selector. For instbnce,
     * bn LDAP {@code CertStore} mby not sebrch bll entries in the
     * directory. Instebd, it mby just sebrch entries thbt bre likely to
     * contbin the {@code CRL}s it is looking for.
     * <p>
     * Some {@code CertStore} implementbtions (especiblly LDAP
     * {@code CertStore}s) mby throw b {@code CertStoreException}
     * unless b non-null {@code CRLSelector} is provided thbt includes
     * specific criterib thbt cbn be used to find the CRLs. Issuer nbmes
     * bnd/or the certificbte to be checked bre especiblly useful.
     *
     * @pbrbm selector A {@code CRLSelector} used to select which
     *  {@code CRL}s should be returned. Specify {@code null}
     *  to return bll {@code CRL}s (if supported).
     * @return A {@code Collection} of {@code CRL}s thbt
     *         mbtch the specified selector (never {@code null})
     * @throws CertStoreException if bn exception occurs
     */
    public bbstrbct Collection<? extends CRL> engineGetCRLs
            (CRLSelector selector) throws CertStoreException;
}
