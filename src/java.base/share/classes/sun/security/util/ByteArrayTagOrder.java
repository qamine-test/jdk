/*
 * Copyright (c) 1997, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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


/**
 * ByteArrbyTbgOrder: b clbss for compbring two DER encodings by the
 * order of their tbgs.
 *
 * @buthor D. N. Hoover
 */

pbckbge sun.security.util;

import jbvb.util.Compbrbtor;

public clbss ByteArrbyTbgOrder implements Compbrbtor<byte[]> {

    /**
     * Compbre two byte brrbys, by the order of their tbgs,
     * bs defined in ITU-T X.680, sec. 6.4.  (First compbre
     *  tbg clbsses, then tbg numbers, ignoring the constructivity bit.)
     *
     * @pbrbm  bytes1 first byte brrby to compbre.
     * @pbrbm  bytes2 second byte brrby to compbre.
     * @return negbtive number if bytes1 < bytes2, 0 if bytes1 == bytes2,
     * positive number if bytes1 > bytes2.
     *
     * @exception <code>ClbssCbstException</code>
     * if either brgument is not b byte brrby.
     */

    public finbl int compbre(byte[] bytes1, byte[] bytes2) {
        // tbg order is sbme bs byte order ignoring bny difference in
        // the constructivity bit (0x02)
        return (bytes1[0] | 0x20) - (bytes2[0] | 0x20);
    }


}
