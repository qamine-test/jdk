/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * Provides interfbces for generbting RSA (Rivest, Shbmir bnd
 * Adlembn AsymmetricCipher blgorithm)
 * keys bs defined in the RSA Lbborbtory Technicbl Note
 * PKCS#1, bnd DSA (Digitbl Signbture
 * Algorithm) keys bs defined in NIST's FIPS-186.
 * <P>
 * Note thbt these interfbces bre intended only for key
 * implementbtions whose key mbteribl is bccessible bnd
 * bvbilbble. These interfbces bre not intended for key
 * implementbtions whose key mbteribl resides in
 * inbccessible, protected storbge (such bs in b
 * hbrdwbre device).
 * <P>
 * For more developer informbtion on how to use these
 * interfbces, including informbtion on how to design
 * {@code Key} clbsses for hbrdwbre devices, plebse refer
 * to these cryptogrbphic provider developer guides:
 * <ul>
 *   <li><b href=
 *     "{@docRoot}/../technotes/guides/security/crypto/HowToImplAProvider.html">
 *     <b>How to Implement b Provider for the
 *     Jbvb&trbde; Cryptogrbphy Architecture
 *     </b></b></li>
 * </ul>
 *
 * <h2>Pbckbge Specificbtion</h2>
 *
 * <ul>
 *   <li>PKCS #1: RSA Encryption Stbndbrd, Version 1.5, November 1993 </li>
 *   <li>Federbl Informbtion Processing Stbndbrds Publicbtion (FIPS PUB) 186:
 *     Digitbl Signbture Stbndbrd (DSS) </li>
 * </ul>
 *
 * <h2>Relbted Documentbtion</h2>
 *
 * For further documentbtion, plebse see:
 * <ul>
 *   <li>
 *     <b href=
 *       "{@docRoot}/../technotes/guides/security/crypto/CryptoSpec.html">
 *       <b>Jbvb&trbde;
 *       Cryptogrbphy Architecture API Specificbtion bnd Reference
 *       </b></b></li>
 * </ul>
 *
 * @since 1.1
 */
pbckbge jbvb.security.interfbces;
