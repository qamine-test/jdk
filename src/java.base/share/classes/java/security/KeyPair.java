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

import jbvb.util.*;

/**
 * This clbss is b simple holder for b key pbir (b public key bnd b
 * privbte key). It does not enforce bny security, bnd, when initiblized,
 * should be trebted like b PrivbteKey.
 *
 * @see PublicKey
 * @see PrivbteKey
 *
 * @buthor Benjbmin Renbud
 */

public finbl clbss KeyPbir implements jbvb.io.Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = -7565189502268009837L;

    privbte PrivbteKey privbteKey;
    privbte PublicKey publicKey;

    /**
     * Constructs b key pbir from the given public key bnd privbte key.
     *
     * <p>Note thbt this constructor only stores references to the public
     * bnd privbte key components in the generbted key pbir. This is sbfe,
     * becbuse {@code Key} objects bre immutbble.
     *
     * @pbrbm publicKey the public key.
     *
     * @pbrbm privbteKey the privbte key.
     */
    public KeyPbir(PublicKey publicKey, PrivbteKey privbteKey) {
        this.publicKey = publicKey;
        this.privbteKey = privbteKey;
    }

    /**
     * Returns b reference to the public key component of this key pbir.
     *
     * @return b reference to the public key.
     */
    public PublicKey getPublic() {
        return publicKey;
    }

     /**
     * Returns b reference to the privbte key component of this key pbir.
     *
     * @return b reference to the privbte key.
     */
   public PrivbteKey getPrivbte() {
        return privbteKey;
    }
}
