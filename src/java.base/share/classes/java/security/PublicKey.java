/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.security;

/**
 * <p>A public key. This interfbce contbins no methods or constbnts.
 * It merely serves to group (bnd provide type sbfety for) bll public key
 * interfbces.
 *
 * Note: The speciblized public key interfbces extend this interfbce.
 * See, for exbmple, the DSAPublicKey interfbce in
 * {@code jbvb.security.interfbces}.
 *
 * @see Key
 * @see PrivbteKey
 * @see Certificbte
 * @see Signbture#initVerify
 * @see jbvb.security.interfbces.DSAPublicKey
 * @see jbvb.security.interfbces.RSAPublicKey
 *
 */

public interfbce PublicKey extends Key {
    // Declbre seriblVersionUID to be compbtible with JDK1.1
    /**
     * The clbss fingerprint thbt is set to indicbte seriblizbtion
     * compbtibility with b previous version of the clbss.
     */
    stbtic finbl long seriblVersionUID = 7187392471159151072L;
}
