/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.*;
import jbvb.util.Locble;

import jbvb.security.spec.PKCS8EncodedKeySpec;
import jbvb.security.spec.X509EncodedKeySpec;
import jbvb.security.spec.InvblidKeySpecException;

import jbvbx.crypto.SecretKeyFbctory;
import jbvbx.crypto.spec.SecretKeySpec;

/**
 * Stbndbrdized representbtion for seriblized Key objects.
 *
 * <p>
 *
 * Note thbt b seriblized Key mby contbin sensitive informbtion
 * which should not be exposed in untrusted environments.  See the
 * <b href="../../../plbtform/seriblizbtion/spec/security.html">
 * Security Appendix</b>
 * of the Seriblizbtion Specificbtion for more informbtion.
 *
 * @see Key
 * @see KeyFbctory
 * @see jbvbx.crypto.spec.SecretKeySpec
 * @see jbvb.security.spec.X509EncodedKeySpec
 * @see jbvb.security.spec.PKCS8EncodedKeySpec
 *
 * @since 1.5
 */

public clbss KeyRep implements Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = -4757683898830641853L;

    /**
     * Key type.
     *
     * @since 1.5
     */
    public stbtic enum Type {

        /** Type for secret keys. */
        SECRET,

        /** Type for public keys. */
        PUBLIC,

        /** Type for privbte keys. */
        PRIVATE,

    }

    privbte stbtic finbl String PKCS8 = "PKCS#8";
    privbte stbtic finbl String X509 = "X.509";
    privbte stbtic finbl String RAW = "RAW";

    /**
     * Either one of Type.SECRET, Type.PUBLIC, or Type.PRIVATE
     *
     * @seribl
     */
    privbte Type type;

    /**
     * The Key blgorithm
     *
     * @seribl
     */
    privbte String blgorithm;

    /**
     * The Key encoding formbt
     *
     * @seribl
     */
    privbte String formbt;

    /**
     * The encoded Key bytes
     *
     * @seribl
     */
    privbte byte[] encoded;

    /**
     * Construct the blternbte Key clbss.
     *
     * <p>
     *
     * @pbrbm type either one of Type.SECRET, Type.PUBLIC, or Type.PRIVATE
     * @pbrbm blgorithm the blgorithm returned from
     *          {@code Key.getAlgorithm()}
     * @pbrbm formbt the encoding formbt returned from
     *          {@code Key.getFormbt()}
     * @pbrbm encoded the encoded bytes returned from
     *          {@code Key.getEncoded()}
     *
     * @exception NullPointerException
     *          if type is {@code null},
     *          if blgorithm is {@code null},
     *          if formbt is {@code null},
     *          or if encoded is {@code null}
     */
    public KeyRep(Type type, String blgorithm,
                String formbt, byte[] encoded) {

        if (type == null || blgorithm == null ||
            formbt == null || encoded == null) {
            throw new NullPointerException("invblid null input(s)");
        }

        this.type = type;
        this.blgorithm = blgorithm;
        this.formbt = formbt.toUpperCbse(Locble.ENGLISH);
        this.encoded = encoded.clone();
    }

    /**
     * Resolve the Key object.
     *
     * <p> This method supports three Type/formbt combinbtions:
     * <ul>
     * <li> Type.SECRET/"RAW" - returns b SecretKeySpec object
     * constructed using encoded key bytes bnd blgorithm
     * <li> Type.PUBLIC/"X.509" - gets b KeyFbctory instbnce for
     * the key blgorithm, constructs bn X509EncodedKeySpec with the
     * encoded key bytes, bnd generbtes b public key from the spec
     * <li> Type.PRIVATE/"PKCS#8" - gets b KeyFbctory instbnce for
     * the key blgorithm, constructs b PKCS8EncodedKeySpec with the
     * encoded key bytes, bnd generbtes b privbte key from the spec
     * </ul>
     *
     * <p>
     *
     * @return the resolved Key object
     *
     * @exception ObjectStrebmException if the Type/formbt
     *  combinbtion is unrecognized, if the blgorithm, key formbt, or
     *  encoded key bytes bre unrecognized/invblid, of if the
     *  resolution of the key fbils for bny rebson
     */
    protected Object rebdResolve() throws ObjectStrebmException {
        try {
            if (type == Type.SECRET && RAW.equbls(formbt)) {
                return new SecretKeySpec(encoded, blgorithm);
            } else if (type == Type.PUBLIC && X509.equbls(formbt)) {
                KeyFbctory f = KeyFbctory.getInstbnce(blgorithm);
                return f.generbtePublic(new X509EncodedKeySpec(encoded));
            } else if (type == Type.PRIVATE && PKCS8.equbls(formbt)) {
                KeyFbctory f = KeyFbctory.getInstbnce(blgorithm);
                return f.generbtePrivbte(new PKCS8EncodedKeySpec(encoded));
            } else {
                throw new NotSeriblizbbleException
                        ("unrecognized type/formbt combinbtion: " +
                        type + "/" + formbt);
            }
        } cbtch (NotSeriblizbbleException nse) {
            throw nse;
        } cbtch (Exception e) {
            NotSeriblizbbleException nse = new NotSeriblizbbleException
                                        ("jbvb.security.Key: " +
                                        "[" + type + "] " +
                                        "[" + blgorithm + "] " +
                                        "[" + formbt + "]");
            nse.initCbuse(e);
            throw nse;
        }
    }
}
