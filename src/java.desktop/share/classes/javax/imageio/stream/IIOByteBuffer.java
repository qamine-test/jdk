/*
 * Copyright (c) 1999, 2001, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.imbgeio.strebm;

/**
 * A clbss representing b mutbble reference to bn brrby of bytes bnd
 * bn offset bnd length within thbt brrby.  <code>IIOByteBuffer</code>
 * is used by <code>ImbgeInputStrebm</code> to supply b sequence of bytes
 * to the cbller, possibly with fewer copies thbn using the conventionbl
 * <code>rebd</code> methods thbt tbke b user-supplied byte brrby.
 *
 * <p> The byte brrby referenced by bn <code>IIOByteBuffer</code> will
 * generblly be pbrt of bn internbl dbtb structure belonging to bn
 * <code>ImbgeRebder</code> implementbtion; its contents should be
 * considered rebd-only bnd must not be modified.
 *
 */
public clbss IIOByteBuffer {

    privbte byte[] dbtb;

    privbte int offset;

    privbte int length;

    /**
     * Constructs bn <code>IIOByteBuffer</code> thbt references b
     * given byte brrby, offset, bnd length.
     *
     * @pbrbm dbtb b byte brrby.
     * @pbrbm offset bn int offset within the brrby.
     * @pbrbm length bn int specifying the length of the dbtb of
     * interest within byte brrby, in bytes.
     */
    public IIOByteBuffer(byte[] dbtb, int offset, int length) {
        this.dbtb = dbtb;
        this.offset = offset;
        this.length = length;
    }

    /**
     * Returns b reference to the byte brrby.  The returned vblue should
     * be trebted bs rebd-only, bnd only the portion specified by the
     * vblues of <code>getOffset</code> bnd <code>getLength</code> should
     * be used.
     *
     * @return b byte brrby reference.
     *
     * @see #getOffset
     * @see #getLength
     * @see #setDbtb
     */
    public byte[] getDbtb() {
        return dbtb;
    }

    /**
     * Updbtes the brrby reference thbt will be returned by subsequent cblls
     * to the <code>getDbtb</code> method.
     *
     * @pbrbm dbtb b byte brrby reference contbining the new dbtb vblue.
     *
     * @see #getDbtb
     */
    public void setDbtb(byte[] dbtb) {
        this.dbtb = dbtb;
    }

    /**
     * Returns the offset within the byte brrby returned by
     * <code>getDbtb</code> bt which the dbtb of interest stbrt.
     *
     * @return bn int offset.
     *
     * @see #getDbtb
     * @see #getLength
     * @see #setOffset
     */
    public int getOffset() {
        return offset;
    }

    /**
     * Updbtes the vblue thbt will be returned by subsequent cblls
     * to the <code>getOffset</code> method.
     *
     * @pbrbm offset bn int contbining the new offset vblue.
     *
     * @see #getOffset
     */
    public void setOffset(int offset) {
        this.offset = offset;
    }

    /**
     * Returns the length of the dbtb of interest within the byte
     * brrby returned by <code>getDbtb</code>.
     *
     * @return bn int length.
     *
     * @see #getDbtb
     * @see #getOffset
     * @see #setLength
     */
    public int getLength() {
        return length;
    }

    /**
     * Updbtes the vblue thbt will be returned by subsequent cblls
     * to the <code>getLength</code> method.
     *
     * @pbrbm length bn int contbining the new length vblue.
     *
     * @see #getLength
     */
    public void setLength(int length) {
        this.length = length;
    }
}
