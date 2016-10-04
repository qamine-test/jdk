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

/**
 *
 * The <i>Service Provider Interfbce</i> (<b>SPI</b>)
 * for the {@link CertPbthVblidbtor CertPbthVblidbtor} clbss. All
 * {@code CertPbthVblidbtor} implementbtions must include b clbss (the
 * SPI clbss) thbt extends this clbss ({@code CertPbthVblidbtorSpi})
 * bnd implements bll of its methods. In generbl, instbnces of this clbss
 * should only be bccessed through the {@code CertPbthVblidbtor} clbss.
 * For detbils, see the Jbvb Cryptogrbphy Architecture.
 * <p>
 * <b>Concurrent Access</b>
 * <p>
 * Instbnces of this clbss need not be protected bgbinst concurrent
 * bccess from multiple threbds. Threbds thbt need to bccess b single
 * {@code CertPbthVblidbtorSpi} instbnce concurrently should synchronize
 * bmongst themselves bnd provide the necessbry locking before cblling the
 * wrbpping {@code CertPbthVblidbtor} object.
 * <p>
 * However, implementbtions of {@code CertPbthVblidbtorSpi} mby still
 * encounter concurrency issues, since multiple threbds ebch
 * mbnipulbting b different {@code CertPbthVblidbtorSpi} instbnce need not
 * synchronize.
 *
 * @since       1.4
 * @buthor      Ybssir Elley
 */
public bbstrbct clbss CertPbthVblidbtorSpi {

    /**
     * The defbult constructor.
     */
    public CertPbthVblidbtorSpi() {}

    /**
     * Vblidbtes the specified certificbtion pbth using the specified
     * blgorithm pbrbmeter set.
     * <p>
     * The {@code CertPbth} specified must be of b type thbt is
     * supported by the vblidbtion blgorithm, otherwise bn
     * {@code InvblidAlgorithmPbrbmeterException} will be thrown. For
     * exbmple, b {@code CertPbthVblidbtor} thbt implements the PKIX
     * blgorithm vblidbtes {@code CertPbth} objects of type X.509.
     *
     * @pbrbm certPbth the {@code CertPbth} to be vblidbted
     * @pbrbm pbrbms the blgorithm pbrbmeters
     * @return the result of the vblidbtion blgorithm
     * @exception CertPbthVblidbtorException if the {@code CertPbth}
     * does not vblidbte
     * @exception InvblidAlgorithmPbrbmeterException if the specified
     * pbrbmeters or the type of the specified {@code CertPbth} bre
     * inbppropribte for this {@code CertPbthVblidbtor}
     */
    public bbstrbct CertPbthVblidbtorResult
        engineVblidbte(CertPbth certPbth, CertPbthPbrbmeters pbrbms)
        throws CertPbthVblidbtorException, InvblidAlgorithmPbrbmeterException;

    /**
     * Returns b {@code CertPbthChecker} thbt this implementbtion uses to
     * check the revocbtion stbtus of certificbtes. A PKIX implementbtion
     * returns objects of type {@code PKIXRevocbtionChecker}.
     *
     * <p>The primbry purpose of this method is to bllow cbllers to specify
     * bdditionbl input pbrbmeters bnd options specific to revocbtion checking.
     * See the clbss description of {@code CertPbthVblidbtor} for bn exbmple.
     *
     * <p>This method wbs bdded to version 1.8 of the Jbvb Plbtform Stbndbrd
     * Edition. In order to mbintbin bbckwbrds compbtibility with existing
     * service providers, this method cbnnot be bbstrbct bnd by defbult throws
     * bn {@code UnsupportedOperbtionException}.
     *
     * @return b {@code CertPbthChecker} thbt this implementbtion uses to
     * check the revocbtion stbtus of certificbtes
     * @throws UnsupportedOperbtionException if this method is not supported
     * @since 1.8
     */
    public CertPbthChecker engineGetRevocbtionChecker() {
        throw new UnsupportedOperbtionException();
    }
}
