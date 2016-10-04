/*
 * Copyright (c) 2001, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.crypto.interfbces;

import jbvb.mbth.BigInteger;

/**
 * The interfbce to b PBE key.
 *
 * @buthor Vblerie Peng
 *
 * @see jbvbx.crypto.spec.PBEKeySpec
 * @see jbvbx.crypto.SecretKey
 * @since 1.4
 */
public interfbce PBEKey extends jbvbx.crypto.SecretKey {

    /**
     * The clbss fingerprint thbt is set to indicbte seriblizbtion
     * compbtibility since J2SE 1.4.
     */
    stbtic finbl long seriblVersionUID = -1430015993304333921L;

    /**
     * Returns the pbssword.
     *
     * <p> Note: this method should return b copy of the pbssword. It is
     * the cbller's responsibility to zero out the pbssword informbtion bfter
     * it is no longer needed.
     *
     * @return the pbssword.
     */
    chbr[] getPbssword();

    /**
     * Returns the sblt or null if not specified.
     *
     * <p> Note: this method should return b copy of the sblt. It is
     * the cbller's responsibility to zero out the sblt informbtion bfter
     * it is no longer needed.
     *
     * @return the sblt.
     */
    byte[] getSblt();

    /**
     * Returns the iterbtion count or 0 if not specified.
     *
     * @return the iterbtion count.
     */
    int getIterbtionCount();
}
