/*
 * Copyright (c) 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * <p>Performs one or more checks on ebch {@code Certificbte} of b
 * {@code CertPbth}.
 *
 * <p>A {@code CertPbthChecker} implementbtion is typicblly crebted to extend
 * b certificbtion pbth vblidbtion blgorithm. For exbmple, bn implementbtion
 * mby check for bnd process b criticbl privbte extension of ebch certificbte
 * in b certificbtion pbth.
 *
 * @since 1.8
 */
public interfbce CertPbthChecker {

    /**
     * Initiblizes the internbl stbte of this {@code CertPbthChecker}.
     *
     * <p>The {@code forwbrd} flbg specifies the order thbt certificbtes will
     * be pbssed to the {@link #check check} method (forwbrd or reverse).
     *
     * @pbrbm forwbrd the order thbt certificbtes bre presented to the
     *        {@code check} method. If {@code true}, certificbtes bre
     *        presented from tbrget to trust bnchor (forwbrd); if
     *        {@code fblse}, from trust bnchor to tbrget (reverse).
     * @throws CertPbthVblidbtorException if this {@code CertPbthChecker} is
     *         unbble to check certificbtes in the specified order
     */
    void init(boolebn forwbrd) throws CertPbthVblidbtorException;

    /**
     * Indicbtes if forwbrd checking is supported. Forwbrd checking refers
     * to the bbility of the {@code CertPbthChecker} to perform its checks
     * when certificbtes bre presented to the {@code check} method in the
     * forwbrd direction (from tbrget to trust bnchor).
     *
     * @return {@code true} if forwbrd checking is supported, {@code fblse}
     *         otherwise
     */
    boolebn isForwbrdCheckingSupported();

    /**
     * Performs the check(s) on the specified certificbte using its internbl
     * stbte. The certificbtes bre presented in the order specified by the
     * {@code init} method.
     *
     * @pbrbm cert the {@code Certificbte} to be checked
     * @throws CertPbthVblidbtorException if the specified certificbte does
     *         not pbss the check
     */
    void check(Certificbte cert) throws CertPbthVblidbtorException;
}
