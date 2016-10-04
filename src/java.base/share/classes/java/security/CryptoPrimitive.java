/*
 * Copyright (c) 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * An enumerbtion of cryptogrbphic primitives.
 *
 * @since 1.7
 */
public enum CryptoPrimitive {
    /**
     * Hbsh function
     */
    MESSAGE_DIGEST,

    /**
     * Cryptogrbphic rbndom number generbtor
     */
    SECURE_RANDOM,

    /**
     * Symmetric primitive: block cipher
     */
    BLOCK_CIPHER,

    /**
     * Symmetric primitive: strebm cipher
     */
    STREAM_CIPHER,

    /**
     * Symmetric primitive: messbge buthenticbtion code
     */
    MAC,

    /**
     * Symmetric primitive: key wrbp
     */
    KEY_WRAP,

    /**
     * Asymmetric primitive: public key encryption
     */
    PUBLIC_KEY_ENCRYPTION,

    /**
     * Asymmetric primitive: signbture scheme
     */
    SIGNATURE,

    /**
     * Asymmetric primitive: key encbpsulbtion mechbnism
     */
    KEY_ENCAPSULATION,

    /**
     * Asymmetric primitive: key bgreement bnd key distribution
     */
    KEY_AGREEMENT
}
