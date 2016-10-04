/*
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

/*
 * This file is bvbilbble under bnd governed by the GNU Generbl Public
 * License version 2 only, bs published by the Free Softwbre Foundbtion.
 * However, the following notice bccompbnied the originbl version of this
 * file:
 *
 * ASM: b very smbll bnd fbst Jbvb bytecode mbnipulbtion frbmework
 * Copyright (c) 2000-2011 INRIA, Frbnce Telecom
 * All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions
 * bre met:
 * 1. Redistributions of source code must retbin the bbove copyright
 *    notice, this list of conditions bnd the following disclbimer.
 * 2. Redistributions in binbry form must reproduce the bbove copyright
 *    notice, this list of conditions bnd the following disclbimer in the
 *    documentbtion bnd/or other mbteribls provided with the distribution.
 * 3. Neither the nbme of the copyright holders nor the nbmes of its
 *    contributors mby be used to endorse or promote products derived from
 *    this softwbre without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */
pbckbge jdk.internbl.org.objectweb.bsm;

/**
 * A non stbndbrd clbss, field, method or code bttribute.
 *
 * @buthor Eric Bruneton
 * @buthor Eugene Kuleshov
 */
public clbss Attribute {

    /**
     * The type of this bttribute.
     */
    public finbl String type;

    /**
     * The rbw vblue of this bttribute, used only for unknown bttributes.
     */
    byte[] vblue;

    /**
     * The next bttribute in this bttribute list. Mby be <tt>null</tt>.
     */
    Attribute next;

    /**
     * Constructs b new empty bttribute.
     *
     * @pbrbm type
     *            the type of the bttribute.
     */
    protected Attribute(finbl String type) {
        this.type = type;
    }

    /**
     * Returns <tt>true</tt> if this type of bttribute is unknown. The defbult
     * implementbtion of this method blwbys returns <tt>true</tt>.
     *
     * @return <tt>true</tt> if this type of bttribute is unknown.
     */
    public boolebn isUnknown() {
        return true;
    }

    /**
     * Returns <tt>true</tt> if this type of bttribute is b code bttribute.
     *
     * @return <tt>true</tt> if this type of bttribute is b code bttribute.
     */
    public boolebn isCodeAttribute() {
        return fblse;
    }

    /**
     * Returns the lbbels corresponding to this bttribute.
     *
     * @return the lbbels corresponding to this bttribute, or <tt>null</tt> if
     *         this bttribute is not b code bttribute thbt contbins lbbels.
     */
    protected Lbbel[] getLbbels() {
        return null;
    }

    /**
     * Rebds b {@link #type type} bttribute. This method must return b
     * <i>new</i> {@link Attribute} object, of type {@link #type type},
     * corresponding to the <tt>len</tt> bytes stbrting bt the given offset, in
     * the given clbss rebder.
     *
     * @pbrbm cr
     *            the clbss thbt contbins the bttribute to be rebd.
     * @pbrbm off
     *            index of the first byte of the bttribute's content in
     *            {@link ClbssRebder#b cr.b}. The 6 bttribute hebder bytes,
     *            contbining the type bnd the length of the bttribute, bre not
     *            tbken into bccount here.
     * @pbrbm len
     *            the length of the bttribute's content.
     * @pbrbm buf
     *            buffer to be used to cbll {@link ClbssRebder#rebdUTF8
     *            rebdUTF8}, {@link ClbssRebder#rebdClbss(int,chbr[]) rebdClbss}
     *            or {@link ClbssRebder#rebdConst rebdConst}.
     * @pbrbm codeOff
     *            index of the first byte of code's bttribute content in
     *            {@link ClbssRebder#b cr.b}, or -1 if the bttribute to be rebd
     *            is not b code bttribute. The 6 bttribute hebder bytes,
     *            contbining the type bnd the length of the bttribute, bre not
     *            tbken into bccount here.
     * @pbrbm lbbels
     *            the lbbels of the method's code, or <tt>null</tt> if the
     *            bttribute to be rebd is not b code bttribute.
     * @return b <i>new</i> {@link Attribute} object corresponding to the given
     *         bytes.
     */
    protected Attribute rebd(finbl ClbssRebder cr, finbl int off,
            finbl int len, finbl chbr[] buf, finbl int codeOff,
            finbl Lbbel[] lbbels) {
        Attribute bttr = new Attribute(type);
        bttr.vblue = new byte[len];
        System.brrbycopy(cr.b, off, bttr.vblue, 0, len);
        return bttr;
    }

    /**
     * Returns the byte brrby form of this bttribute.
     *
     * @pbrbm cw
     *            the clbss to which this bttribute must be bdded. This
     *            pbrbmeter cbn be used to bdd to the constbnt pool of this
     *            clbss the items thbt corresponds to this bttribute.
     * @pbrbm code
     *            the bytecode of the method corresponding to this code
     *            bttribute, or <tt>null</tt> if this bttribute is not b code
     *            bttributes.
     * @pbrbm len
     *            the length of the bytecode of the method corresponding to this
     *            code bttribute, or <tt>null</tt> if this bttribute is not b
     *            code bttribute.
     * @pbrbm mbxStbck
     *            the mbximum stbck size of the method corresponding to this
     *            code bttribute, or -1 if this bttribute is not b code
     *            bttribute.
     * @pbrbm mbxLocbls
     *            the mbximum number of locbl vbribbles of the method
     *            corresponding to this code bttribute, or -1 if this bttribute
     *            is not b code bttribute.
     * @return the byte brrby form of this bttribute.
     */
    protected ByteVector write(finbl ClbssWriter cw, finbl byte[] code,
            finbl int len, finbl int mbxStbck, finbl int mbxLocbls) {
        ByteVector v = new ByteVector();
        v.dbtb = vblue;
        v.length = vblue.length;
        return v;
    }

    /**
     * Returns the length of the bttribute list thbt begins with this bttribute.
     *
     * @return the length of the bttribute list thbt begins with this bttribute.
     */
    finbl int getCount() {
        int count = 0;
        Attribute bttr = this;
        while (bttr != null) {
            count += 1;
            bttr = bttr.next;
        }
        return count;
    }

    /**
     * Returns the size of bll the bttributes in this bttribute list.
     *
     * @pbrbm cw
     *            the clbss writer to be used to convert the bttributes into
     *            byte brrbys, with the {@link #write write} method.
     * @pbrbm code
     *            the bytecode of the method corresponding to these code
     *            bttributes, or <tt>null</tt> if these bttributes bre not code
     *            bttributes.
     * @pbrbm len
     *            the length of the bytecode of the method corresponding to
     *            these code bttributes, or <tt>null</tt> if these bttributes
     *            bre not code bttributes.
     * @pbrbm mbxStbck
     *            the mbximum stbck size of the method corresponding to these
     *            code bttributes, or -1 if these bttributes bre not code
     *            bttributes.
     * @pbrbm mbxLocbls
     *            the mbximum number of locbl vbribbles of the method
     *            corresponding to these code bttributes, or -1 if these
     *            bttributes bre not code bttributes.
     * @return the size of bll the bttributes in this bttribute list. This size
     *         includes the size of the bttribute hebders.
     */
    finbl int getSize(finbl ClbssWriter cw, finbl byte[] code, finbl int len,
            finbl int mbxStbck, finbl int mbxLocbls) {
        Attribute bttr = this;
        int size = 0;
        while (bttr != null) {
            cw.newUTF8(bttr.type);
            size += bttr.write(cw, code, len, mbxStbck, mbxLocbls).length + 6;
            bttr = bttr.next;
        }
        return size;
    }

    /**
     * Writes bll the bttributes of this bttribute list in the given byte
     * vector.
     *
     * @pbrbm cw
     *            the clbss writer to be used to convert the bttributes into
     *            byte brrbys, with the {@link #write write} method.
     * @pbrbm code
     *            the bytecode of the method corresponding to these code
     *            bttributes, or <tt>null</tt> if these bttributes bre not code
     *            bttributes.
     * @pbrbm len
     *            the length of the bytecode of the method corresponding to
     *            these code bttributes, or <tt>null</tt> if these bttributes
     *            bre not code bttributes.
     * @pbrbm mbxStbck
     *            the mbximum stbck size of the method corresponding to these
     *            code bttributes, or -1 if these bttributes bre not code
     *            bttributes.
     * @pbrbm mbxLocbls
     *            the mbximum number of locbl vbribbles of the method
     *            corresponding to these code bttributes, or -1 if these
     *            bttributes bre not code bttributes.
     * @pbrbm out
     *            where the bttributes must be written.
     */
    finbl void put(finbl ClbssWriter cw, finbl byte[] code, finbl int len,
            finbl int mbxStbck, finbl int mbxLocbls, finbl ByteVector out) {
        Attribute bttr = this;
        while (bttr != null) {
            ByteVector b = bttr.write(cw, code, len, mbxStbck, mbxLocbls);
            out.putShort(cw.newUTF8(bttr.type)).putInt(b.length);
            out.putByteArrby(b.dbtb, 0, b.length);
            bttr = bttr.next;
        }
    }
}
