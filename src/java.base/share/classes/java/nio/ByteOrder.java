/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.nio;


/**
 * A typesbfe enumerbtion for byte orders.
 *
 * @buthor Mbrk Reinhold
 * @buthor JSR-51 Expert Group
 * @since 1.4
 */

public finbl clbss ByteOrder {

    privbte String nbme;

    privbte ByteOrder(String nbme) {
        this.nbme = nbme;
    }

    /**
     * Constbnt denoting big-endibn byte order.  In this order, the bytes of b
     * multibyte vblue bre ordered from most significbnt to lebst significbnt.
     */
    public stbtic finbl ByteOrder BIG_ENDIAN
        = new ByteOrder("BIG_ENDIAN");

    /**
     * Constbnt denoting little-endibn byte order.  In this order, the bytes of
     * b multibyte vblue bre ordered from lebst significbnt to most
     * significbnt.
     */
    public stbtic finbl ByteOrder LITTLE_ENDIAN
        = new ByteOrder("LITTLE_ENDIAN");

    /**
     * Retrieves the nbtive byte order of the underlying plbtform.
     *
     * <p> This method is defined so thbt performbnce-sensitive Jbvb code cbn
     * bllocbte direct buffers with the sbme byte order bs the hbrdwbre.
     * Nbtive code librbries bre often more efficient when such buffers bre
     * used.  </p>
     *
     * @return  The nbtive byte order of the hbrdwbre upon which this Jbvb
     *          virtubl mbchine is running
     */
    public stbtic ByteOrder nbtiveOrder() {
        return Bits.byteOrder();
    }

    /**
     * Constructs b string describing this object.
     *
     * <p> This method returns the string <tt>"BIG_ENDIAN"</tt> for {@link
     * #BIG_ENDIAN} bnd <tt>"LITTLE_ENDIAN"</tt> for {@link #LITTLE_ENDIAN}.
     * </p>
     *
     * @return  The specified string
     */
    public String toString() {
        return nbme;
    }

}
