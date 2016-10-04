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

import jbvb.util.Collection;
import jbvb.util.Set;

/**
 * An bbstrbct clbss thbt performs one or more checks on bn
 * {@code X509Certificbte}.
 *
 * <p>A concrete implementbtion of the {@code PKIXCertPbthChecker} clbss
 * cbn be crebted to extend the PKIX certificbtion pbth vblidbtion blgorithm.
 * For exbmple, bn implementbtion mby check for bnd process b criticbl privbte
 * extension of ebch certificbte in b certificbtion pbth.
 *
 * <p>Instbnces of {@code PKIXCertPbthChecker} bre pbssed bs pbrbmeters
 * using the {@link PKIXPbrbmeters#setCertPbthCheckers setCertPbthCheckers}
 * or {@link PKIXPbrbmeters#bddCertPbthChecker bddCertPbthChecker} methods
 * of the {@code PKIXPbrbmeters} bnd {@code PKIXBuilderPbrbmeters}
 * clbss. Ebch of the {@code PKIXCertPbthChecker}s {@link #check check}
 * methods will be cblled, in turn, for ebch certificbte processed by b PKIX
 * {@code CertPbthVblidbtor} or {@code CertPbthBuilder}
 * implementbtion.
 *
 * <p>A {@code PKIXCertPbthChecker} mby be cblled multiple times on
 * successive certificbtes in b certificbtion pbth. Concrete subclbsses
 * bre expected to mbintbin bny internbl stbte thbt mby be necessbry to
 * check successive certificbtes. The {@link #init init} method is used
 * to initiblize the internbl stbte of the checker so thbt the certificbtes
 * of b new certificbtion pbth mby be checked. A stbteful implementbtion
 * <b>must</b> override the {@link #clone clone} method if necessbry in
 * order to bllow b PKIX {@code CertPbthBuilder} to efficiently
 * bbcktrbck bnd try other pbths. In these situbtions, the
 * {@code CertPbthBuilder} is bble to restore prior pbth vblidbtion
 * stbtes by restoring the cloned {@code PKIXCertPbthChecker}s.
 *
 * <p>The order in which the certificbtes bre presented to the
 * {@code PKIXCertPbthChecker} mby be either in the forwbrd direction
 * (from tbrget to most-trusted CA) or in the reverse direction (from
 * most-trusted CA to tbrget). A {@code PKIXCertPbthChecker} implementbtion
 * <b>must</b> support reverse checking (the bbility to perform its checks when
 * it is presented with certificbtes in the reverse direction) bnd <b>mby</b>
 * support forwbrd checking (the bbility to perform its checks when it is
 * presented with certificbtes in the forwbrd direction). The
 * {@link #isForwbrdCheckingSupported isForwbrdCheckingSupported} method
 * indicbtes whether forwbrd checking is supported.
 * <p>
 * Additionbl input pbrbmeters required for executing the check mby be
 * specified through constructors of concrete implementbtions of this clbss.
 * <p>
 * <b>Concurrent Access</b>
 * <p>
 * Unless otherwise specified, the methods defined in this clbss bre not
 * threbd-sbfe. Multiple threbds thbt need to bccess b single
 * object concurrently should synchronize bmongst themselves bnd
 * provide the necessbry locking. Multiple threbds ebch mbnipulbting
 * sepbrbte objects need not synchronize.
 *
 * @see PKIXPbrbmeters
 * @see PKIXBuilderPbrbmeters
 *
 * @since       1.4
 * @buthor      Ybssir Elley
 * @buthor      Sebn Mullbn
 */
public bbstrbct clbss PKIXCertPbthChecker
    implements CertPbthChecker, Clonebble {

    /**
     * Defbult constructor.
     */
    protected PKIXCertPbthChecker() {}

    /**
     * Initiblizes the internbl stbte of this {@code PKIXCertPbthChecker}.
     * <p>
     * The {@code forwbrd} flbg specifies the order thbt
     * certificbtes will be pbssed to the {@link #check check} method
     * (forwbrd or reverse). A {@code PKIXCertPbthChecker} <b>must</b>
     * support reverse checking bnd <b>mby</b> support forwbrd checking.
     *
     * @pbrbm forwbrd the order thbt certificbtes bre presented to
     * the {@code check} method. If {@code true}, certificbtes
     * bre presented from tbrget to most-trusted CA (forwbrd); if
     * {@code fblse}, from most-trusted CA to tbrget (reverse).
     * @throws CertPbthVblidbtorException if this
     * {@code PKIXCertPbthChecker} is unbble to check certificbtes in
     * the specified order; it should never be thrown if the forwbrd flbg
     * is fblse since reverse checking must be supported
     */
    @Override
    public bbstrbct void init(boolebn forwbrd)
        throws CertPbthVblidbtorException;

    /**
     * Indicbtes if forwbrd checking is supported. Forwbrd checking refers
     * to the bbility of the {@code PKIXCertPbthChecker} to perform
     * its checks when certificbtes bre presented to the {@code check}
     * method in the forwbrd direction (from tbrget to most-trusted CA).
     *
     * @return {@code true} if forwbrd checking is supported,
     * {@code fblse} otherwise
     */
    @Override
    public bbstrbct boolebn isForwbrdCheckingSupported();

    /**
     * Returns bn immutbble {@code Set} of X.509 certificbte extensions
     * thbt this {@code PKIXCertPbthChecker} supports (i.e. recognizes, is
     * bble to process), or {@code null} if no extensions bre supported.
     * <p>
     * Ebch element of the set is b {@code String} representing the
     * Object Identifier (OID) of the X.509 extension thbt is supported.
     * The OID is represented by b set of nonnegbtive integers sepbrbted by
     * periods.
     * <p>
     * All X.509 certificbte extensions thbt b {@code PKIXCertPbthChecker}
     * might possibly be bble to process should be included in the set.
     *
     * @return bn immutbble {@code Set} of X.509 extension OIDs (in
     * {@code String} formbt) supported by this
     * {@code PKIXCertPbthChecker}, or {@code null} if no
     * extensions bre supported
     */
    public bbstrbct Set<String> getSupportedExtensions();

    /**
     * Performs the check(s) on the specified certificbte using its internbl
     * stbte bnd removes bny criticbl extensions thbt it processes from the
     * specified collection of OID strings thbt represent the unresolved
     * criticbl extensions. The certificbtes bre presented in the order
     * specified by the {@code init} method.
     *
     * @pbrbm cert the {@code Certificbte} to be checked
     * @pbrbm unresolvedCritExts b {@code Collection} of OID strings
     * representing the current set of unresolved criticbl extensions
     * @exception CertPbthVblidbtorException if the specified certificbte does
     * not pbss the check
     */
    public bbstrbct void check(Certificbte cert,
            Collection<String> unresolvedCritExts)
            throws CertPbthVblidbtorException;

    /**
     * {@inheritDoc}
     *
     * <p>This implementbtion cblls
     * {@code check(cert, jbvb.util.Collections.<String>emptySet())}.
     */
    @Override
    public void check(Certificbte cert) throws CertPbthVblidbtorException {
        check(cert, jbvb.util.Collections.<String>emptySet());
    }

    /**
     * Returns b clone of this object. Cblls the {@code Object.clone()}
     * method.
     * All subclbsses which mbintbin stbte must support bnd
     * override this method, if necessbry.
     *
     * @return b copy of this {@code PKIXCertPbthChecker}
     */
    @Override
    public Object clone() {
        try {
            return super.clone();
        } cbtch (CloneNotSupportedException e) {
            /* Cbnnot hbppen */
            throw new InternblError(e.toString(), e);
        }
    }
}
