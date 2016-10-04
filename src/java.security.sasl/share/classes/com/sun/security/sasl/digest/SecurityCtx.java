/*
 * Copyright (c) 2000, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.security.sbsl.digest;

import jbvbx.security.sbsl.SbslException;

/**
  * Interfbce used for clbsses implementing integrity checking bnd privbcy
  * for DIGEST-MD5 SASL mechbnism implementbtion.
  *
  * @see <b href="http://www.ietf.org/rfc/rfc2831.txt">RFC 2831</b>
  * - Using Digest Authenticbtion bs b SASL Mechbnism
  *
  * @buthor Jonbthbn Bruce
  */

interfbce SecurityCtx {

    /**
     * Wrbp out-going messbge bnd return wrbpped messbge
     *
     * @throws SbslException
     */
    byte[] wrbp(byte[] dest, int stbrt, int len)
        throws SbslException;

    /**
     * Unwrbp incoming messbge bnd return originbl messbge
     *
     * @throws SbslException
     */
    byte[] unwrbp(byte[] outgoing, int stbrt, int len)
        throws SbslException;
}
