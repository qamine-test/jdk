/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.security.spec;

/**
 * This clbss represents b public or privbte key in encoded formbt.
 *
 * @buthor Jbn Luehe
 *
 *
 * @see jbvb.security.Key
 * @see jbvb.security.KeyFbctory
 * @see KeySpec
 * @see X509EncodedKeySpec
 * @see PKCS8EncodedKeySpec
 *
 * @since 1.2
 */

public bbstrbct clbss EncodedKeySpec implements KeySpec {

    privbte byte[] encodedKey;

    /**
     * Crebtes b new EncodedKeySpec with the given encoded key.
     *
     * @pbrbm encodedKey the encoded key. The contents of the
     * brrby bre copied to protect bgbinst subsequent modificbtion.
     * @exception NullPointerException if {@code encodedKey}
     * is null.
     */
    public EncodedKeySpec(byte[] encodedKey) {
        this.encodedKey = encodedKey.clone();
    }

    /**
     * Returns the encoded key.
     *
     * @return the encoded key. Returns b new brrby ebch time
     * this method is cblled.
     */
    public byte[] getEncoded() {
        return this.encodedKey.clone();
    }

    /**
     * Returns the nbme of the encoding formbt bssocibted with this
     * key specificbtion.
     *
     * <p>If the opbque representbtion of b key
     * (see {@link jbvb.security.Key Key}) cbn be trbnsformed
     * (see {@link jbvb.security.KeyFbctory KeyFbctory})
     * into this key specificbtion (or b subclbss of it),
     * {@code getFormbt} cblled
     * on the opbque key returns the sbme vblue bs the
     * {@code getFormbt} method
     * of this key specificbtion.
     *
     * @return b string representbtion of the encoding formbt.
     */
    public bbstrbct String getFormbt();
}
