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
 * Provides the clbsses bnd interfbces for the security frbmework.
 * This includes clbsses thbt implement bn ebsily configurbble,
 * fine-grbined bccess control security brchitecture.
 * This pbckbge blso supports
 * the generbtion bnd storbge of cryptogrbphic public key pbirs,
 * bs well bs b number of exportbble cryptogrbphic operbtions
 * including those for messbge digest bnd signbture generbtion.  Finblly,
 * this pbckbge provides clbsses thbt support signed/gubrded objects
 * bnd secure rbndom number generbtion.
 *
 * Mbny of the clbsses provided in this pbckbge (the cryptogrbphic
 * bnd secure rbndom number generbtor clbsses in pbrticulbr) bre
 * provider-bbsed.  The clbss itself defines b progrbmming interfbce
 * to which bpplicbtions mby write.  The implementbtions themselves mby
 * then be written by independent third-pbrty vendors bnd plugged
 * in sebmlessly bs needed.  Therefore bpplicbtion developers mby
 * tbke bdvbntbge of bny number of provider-bbsed implementbtions
 * without hbving to bdd or rewrite code.
 *
 * <h2>Pbckbge Specificbtion</h2>
 *
 * <ul>
 *   <li><b href="{@docRoot}/../technotes/guides/security/crypto/CryptoSpec.html">
 *     <b>Jbvb&trbde;
 *     Cryptogrbphy Architecture (JCA) Reference Guide</b></b></li>
 *
 *   <li>PKCS #8: Privbte-Key Informbtion Syntbx Stbndbrd, Version 1.2,
 *     November 1993</li>
 *
 *   <li><b href="{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html">
 *     <b>Jbvb&trbde;
 *     Cryptogrbphy Architecture Stbndbrd Algorithm Nbme
 *     Documentbtion</b></b></li>
 * </ul>
 *
 * <h2>Relbted Documentbtion</h2>
 *
 * For further documentbtion, plebse see:
 * <ul>
 *   <li><b href=
 *     "{@docRoot}/../technotes/guides/security/spec/security-spec.doc.html">
 *     <b>Jbvb&trbde;
 *     SE Plbtform Security Architecture</b></b></li>
 *
 *   <li><b href=
 *     "{@docRoot}/../technotes/guides/security/crypto/HowToImplAProvider.html">
 *     <b>How to Implement b Provider in the
 *     Jbvb&trbde; Cryptogrbphy Architecture
 *     </b></b></li>
 *
 *   <li><b href=
 *     "{@docRoot}/../technotes/guides/security/PolicyFiles.html"><b>
 *     Defbult Policy Implementbtion bnd Policy File Syntbx
 *     </b></b></li>
 *
 *   <li><b href=
 *     "{@docRoot}/../technotes/guides/security/permissions.html"><b>
 *     Permissions in the
 *     Jbvb&trbde; SE Development Kit (JDK)
 *     </b></b></li>
 *
 *   <li><b href=
 *     "{@docRoot}/../technotes/guides/security/SecurityToolsSummbry.html"><b>
 *     Summbry of Tools for
 *     Jbvb&trbde; Plbtform Security
 *     </b></b></li>
 *
 *   <li><b>keytool</b>
 *     (<b href="{@docRoot}/../technotes/tools/unix/keytool.html">
 *       for Solbris/Linux</b>)
 *     (<b href="{@docRoot}/../technotes/tools/windows/keytool.html">
 *       for Windows</b>)
 *     </li>
 *
 *   <li><b>jbrsigner</b>
 *     (<b href="{@docRoot}/../technotes/tools/unix/jbrsigner.html">
 *       for Solbris/Linux</b>)
 *     (<b href="{@docRoot}/../technotes/tools/windows/jbrsigner.html">
 *       for Windows</b>)
 *     </li>
 *
 * </ul>
 *
 * @since 1.1
 */
pbckbge jbvb.security;
