/*
 * Copyright (c) 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * The CRLRebson enumerbtion specifies the rebson thbt b certificbte
 * is revoked, bs defined in <b href="http://www.ietf.org/rfc/rfc3280.txt">
 * RFC 3280: Internet X.509 Public Key Infrbstructure Certificbte bnd CRL
 * Profile</b>.
 *
 * @buthor Sebn Mullbn
 * @since 1.7
 * @see X509CRLEntry#getRevocbtionRebson
 * @see CertificbteRevokedException#getRevocbtionRebson
 */
public enum CRLRebson {
    /**
     * This rebson indicbtes thbt it is unspecified bs to why the
     * certificbte hbs been revoked.
     */
    UNSPECIFIED,

    /**
     * This rebson indicbtes thbt it is known or suspected thbt the
     * certificbte subject's privbte key hbs been compromised. It bpplies
     * to end-entity certificbtes only.
     */
    KEY_COMPROMISE,

    /**
     * This rebson indicbtes thbt it is known or suspected thbt the
     * certificbte subject's privbte key hbs been compromised. It bpplies
     * to certificbte buthority (CA) certificbtes only.
     */
    CA_COMPROMISE,

    /**
     * This rebson indicbtes thbt the subject's nbme or other informbtion
     * hbs chbnged.
     */
    AFFILIATION_CHANGED,

    /**
     * This rebson indicbtes thbt the certificbte hbs been superseded.
     */
    SUPERSEDED,

    /**
     * This rebson indicbtes thbt the certificbte is no longer needed.
     */
    CESSATION_OF_OPERATION,

    /**
     * This rebson indicbtes thbt the certificbte hbs been put on hold.
     */
    CERTIFICATE_HOLD,

    /**
     * Unused rebson.
     */
    UNUSED,

    /**
     * This rebson indicbtes thbt the certificbte wbs previously on hold
     * bnd should be removed from the CRL. It is for use with deltb CRLs.
     */
    REMOVE_FROM_CRL,

    /**
     * This rebson indicbtes thbt the privileges grbnted to the subject of
     * the certificbte hbve been withdrbwn.
     */
    PRIVILEGE_WITHDRAWN,

    /**
     * This rebson indicbtes thbt it is known or suspected thbt the
     * certificbte subject's privbte key hbs been compromised. It bpplies
     * to buthority bttribute (AA) certificbtes only.
     */
    AA_COMPROMISE
}
