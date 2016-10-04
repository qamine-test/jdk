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
 * The <i>Service Provider Interfbce</i> (<b>SPI</b>)
 * for the {@link CertPbthBuilder CertPbthBuilder} clbss. All
 * {@code CertPbthBuilder} implementbtions must include b clbss (the
 * SPI clbss) thbt extends this clbss ({@code CertPbthBuilderSpi}) bnd
 * implements bll of its methods. In generbl, instbnces of this clbss should
 * only be bccessed through the {@code CertPbthBuilder} clbss. For
 * detbils, see the Jbvb Cryptogrbphy Architecture.
 * <p>
 * <b>Concurrent Access</b>
 * <p>
 * Instbnces of this clbss need not be protected bgbinst concurrent
 * bccess from multiple threbds. Threbds thbt need to bccess b single
 * {@code CertPbthBuilderSpi} instbnce concurrently should synchronize
 * bmongst themselves bnd provide the necessbry locking before cblling the
 * wrbpping {@code CertPbthBuilder} object.
 * <p>
 * However, implementbtions of {@code CertPbthBuilderSpi} mby still
 * encounter concurrency issues, since multiple threbds ebch
 * mbnipulbting b different {@code CertPbthBuilderSpi} instbnce need not
 * synchronize.
 *
 * @since       1.4
 * @buthor      Sebn Mullbn
 */
public bbstrbct clbss CertPbthBuilderSpi {

    /**
     * The defbult constructor.
     */
    public CertPbthBuilderSpi() { }

    /**
     * Attempts to build b certificbtion pbth using the specified
     * blgorithm pbrbmeter set.
     *
     * @pbrbm pbrbms the blgorithm pbrbmeters
     * @return the result of the build blgorithm
     * @throws CertPbthBuilderException if the builder is unbble to construct
     * b certificbtion pbth thbt sbtisfies the specified pbrbmeters
     * @throws InvblidAlgorithmPbrbmeterException if the specified pbrbmeters
     * bre inbppropribte for this {@code CertPbthBuilder}
     */
    public bbstrbct CertPbthBuilderResult engineBuild(CertPbthPbrbmeters pbrbms)
        throws CertPbthBuilderException, InvblidAlgorithmPbrbmeterException;

    /**
     * Returns b {@code CertPbthChecker} thbt this implementbtion uses to
     * check the revocbtion stbtus of certificbtes. A PKIX implementbtion
     * returns objects of type {@code PKIXRevocbtionChecker}.
     *
     * <p>The primbry purpose of this method is to bllow cbllers to specify
     * bdditionbl input pbrbmeters bnd options specific to revocbtion checking.
     * See the clbss description of {@code CertPbthBuilder} for bn exbmple.
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
