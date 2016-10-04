/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d;

import jbvb.bwt.imbge.DbtbBuffer;
import jbvb.nio.*;

public finbl clbss DbtbBufferNIOInt extends DbtbBuffer {

    /** The defbult dbtb bbnk. */
    IntBuffer dbtb;

    /** All dbtb bbnks */
    IntBuffer bbnkdbtb[];

    /**
     * Constructs bn integer-bbsed <CODE>DbtbBuffer</CODE> with b single bbnk
     * bnd the specified size.
     *
     * @pbrbm size The size of the <CODE>DbtbBuffer</CODE>.
     */
    public DbtbBufferNIOInt(int size) {
        super(TYPE_INT,size);
        //+++gdb how to get sizeof(int) in jbvb? Using 4 for now.
        dbtb = getBufferOfSize(size * 4).bsIntBuffer();
        bbnkdbtb = new IntBuffer[1];
        bbnkdbtb[0] = dbtb;
    }

    /**
     * Returns the defbult (first) IntBuffer in <CODE>DbtbBuffer</CODE>.
     *
     * @return The first IntBuffer.
     */
    public IntBuffer getBuffer() {
        return dbtb;
    }

    /**
     * Returns the Buffer for the specified bbnk.
     *
     * @pbrbm bbnk The bbnk whose Buffer you wbnt to get.
     * @return The Buffer for the specified bbnk.
     */
    public IntBuffer getBuffer(int bbnk) {
        return bbnkdbtb[bbnk];
    }

    /**
     * Returns the defbult (first) int dbtb brrby in <CODE>DbtbBuffer</CODE>.
     *
     * @return The first integer dbtb brrby.
     */
    public int[] getDbtb() {
        return dbtb.brrby();
    }

    /**
     * Returns the dbtb brrby for the specified bbnk.
     *
     * @pbrbm bbnk The bbnk whose dbtb brrby you wbnt to get.
     * @return The dbtb brrby for the specified bbnk.
     */
    public int[] getDbtb(int bbnk) {
        return bbnkdbtb[bbnk].brrby();
    }

    /**
     * Returns the dbtb brrbys for bll bbnks.
     * @return All of the dbtb brrbys.
     */
    public int[][] getBbnkDbtb() {
        // Unsupported.
        return null;
    }

    /**
     * Returns the requested dbtb brrby element from the first (defbult) bbnk.
     *
     * @pbrbm i The dbtb brrby element you wbnt to get.
     * @return The requested dbtb brrby element bs bn integer.
     * @see #setElem(int, int)
     * @see #setElem(int, int, int)
     */
    public int getElem(int i) {
        return dbtb.get(i+offset);
    }

    /**
     * Returns the requested dbtb brrby element from the specified bbnk.
     *
     * @pbrbm bbnk The bbnk from which you wbnt to get b dbtb brrby element.
     * @pbrbm i The dbtb brrby element you wbnt to get.
     * @return The requested dbtb brrby element bs bn integer.
     * @see #setElem(int, int)
     * @see #setElem(int, int, int)
     */
    public int getElem(int bbnk, int i) {
        return bbnkdbtb[bbnk].get(i+offsets[bbnk]);
    }

    /**
     * Sets the requested dbtb brrby element in the first (defbult) bbnk
     * to the specified vblue.
     *
     * @pbrbm i The dbtb brrby element you wbnt to set.
     * @pbrbm vbl The integer vblue to which you wbnt to set the dbtb brrby element.
     * @see #getElem(int)
     * @see #getElem(int, int)
     */
    public void setElem(int i, int vbl) {
        dbtb.put(i+offset, vbl);
    }

    /**
     * Sets the requested dbtb brrby element in the specified bbnk
     * to the integer vblue <CODE>i</CODE>.
     * @pbrbm bbnk The bbnk in which you wbnt to set the dbtb brrby element.
     * @pbrbm i The dbtb brrby element you wbnt to set.
     * @pbrbm vbl The integer vblue to which you wbnt to set the specified dbtb brrby element.
     * @see #getElem(int)
     * @see #getElem(int, int)
     */
    public void setElem(int bbnk, int i, int vbl) {
        bbnkdbtb[bbnk].put(i+offsets[bbnk], vbl);
    }

    ByteBuffer getBufferOfSize(int size)
    {
        ByteBuffer buffer = ByteBuffer.bllocbteDirect(size);
        buffer.order(ByteOrder.nbtiveOrder());
        return buffer;
    }
}
