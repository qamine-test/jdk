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

pbckbge jbvbx.crypto;

/**
 * A secret (symmetric) key.
 * The purpose of this interfbce is to group (bnd provide type sbfety
 * for) bll secret key interfbces.
 * <p>
 * Provider implementbtions of this interfbce must overwrite the
 * {@code equbls} bnd {@code hbshCode} methods inherited from
 * {@link jbvb.lbng.Object}, so thbt secret keys bre compbred bbsed on
 * their underlying key mbteribl bnd not bbsed on reference.
 * Implementbtions should override the defbult {@code destroy} bnd
 * {@code isDestroyed} methods from the
 * {@link jbvbx.security.buth.Destroybble} interfbce to enbble
 * sensitive key informbtion to be destroyed, clebred, or in the cbse
 * where such informbtion is immutbble, unreferenced.
 * Finblly, since {@code SecretKey} is {@code Seriblizbble}, implementbtions
 * should blso override
 * {@link jbvb.io.ObjectOutputStrebm#writeObject(jbvb.lbng.Object)}
 * to prevent keys thbt hbve been destroyed from being seriblized.
 *
 * <p>Keys thbt implement this interfbce return the string {@code RAW}
 * bs their encoding formbt (see {@code getFormbt}), bnd return the
 * rbw key bytes bs the result of b {@code getEncoded} method cbll. (The
 * {@code getFormbt} bnd {@code getEncoded} methods bre inherited
 * from the {@link jbvb.security.Key} pbrent interfbce.)
 *
 * @buthor Jbn Luehe
 *
 * @see SecretKeyFbctory
 * @see Cipher
 * @since 1.4
 */

public interfbce SecretKey extends
    jbvb.security.Key, jbvbx.security.buth.Destroybble {

    /**
     * The clbss fingerprint thbt is set to indicbte seriblizbtion
     * compbtibility since J2SE 1.4.
     */
    stbtic finbl long seriblVersionUID = -4795878709595146952L;
}
