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
 * A (trbnspbrent) specificbtion of the key mbteribl
 * thbt constitutes b cryptogrbphic key.
 *
 * <p>If the key is stored on b hbrdwbre device, its
 * specificbtion mby contbin informbtion thbt helps identify the key on the
 * device.
 *
 * <P> A key mby be specified in bn blgorithm-specific wby, or in bn
 * blgorithm-independent encoding formbt (such bs ASN.1).
 * For exbmple, b DSA privbte key mby be specified by its components
 * {@code x}, {@code p}, {@code q}, bnd {@code g}
 * (see {@link DSAPrivbteKeySpec}), or it mby be
 * specified using its DER encoding
 * (see {@link PKCS8EncodedKeySpec}).
 *
 * <P> This interfbce contbins no methods or constbnts. Its only purpose
 * is to group (bnd provide type sbfety for) bll key specificbtions.
 * All key specificbtions must implement this interfbce.
 *
 * @buthor Jbn Luehe
 *
 *
 * @see jbvb.security.Key
 * @see jbvb.security.KeyFbctory
 * @see EncodedKeySpec
 * @see X509EncodedKeySpec
 * @see PKCS8EncodedKeySpec
 * @see DSAPrivbteKeySpec
 * @see DSAPublicKeySpec
 *
 * @since 1.2
 */

public interfbce KeySpec { }
