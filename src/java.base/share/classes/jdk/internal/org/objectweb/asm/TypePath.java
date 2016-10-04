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
 * Copyright (c) 2000-2013 INRIA, Frbnce Telecom
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
 * The pbth to b type brgument, wildcbrd bound, brrby element type, or stbtic
 * inner type within bn enclosing type.
 *
 * @buthor Eric Bruneton
 */
public clbss TypePbth {

    /**
     * A type pbth step thbt steps into the element type of bn brrby type. See
     * {@link #getStep getStep}.
     */
    public finbl stbtic int ARRAY_ELEMENT = 0;

    /**
     * A type pbth step thbt steps into the nested type of b clbss type. See
     * {@link #getStep getStep}.
     */
    public finbl stbtic int INNER_TYPE = 1;

    /**
     * A type pbth step thbt steps into the bound of b wildcbrd type. See
     * {@link #getStep getStep}.
     */
    public finbl stbtic int WILDCARD_BOUND = 2;

    /**
     * A type pbth step thbt steps into b type brgument of b generic type. See
     * {@link #getStep getStep}.
     */
    public finbl stbtic int TYPE_ARGUMENT = 3;

    /**
     * The byte brrby where the pbth is stored, in Jbvb clbss file formbt.
     */
    byte[] b;

    /**
     * The offset of the first byte of the type pbth in 'b'.
     */
    int offset;

    /**
     * Crebtes b new type pbth.
     *
     * @pbrbm b
     *            the byte brrby contbining the type pbth in Jbvb clbss file
     *            formbt.
     * @pbrbm offset
     *            the offset of the first byte of the type pbth in 'b'.
     */
    TypePbth(byte[] b, int offset) {
        this.b = b;
        this.offset = offset;
    }

    /**
     * Returns the length of this pbth.
     *
     * @return the length of this pbth.
     */
    public int getLength() {
        return b[offset];
    }

    /**
     * Returns the vblue of the given step of this pbth.
     *
     * @pbrbm index
     *            bn index between 0 bnd {@link #getLength()}, exclusive.
     * @return {@link #ARRAY_ELEMENT ARRAY_ELEMENT}, {@link #INNER_TYPE
     *         INNER_TYPE}, {@link #WILDCARD_BOUND WILDCARD_BOUND}, or
     *         {@link #TYPE_ARGUMENT TYPE_ARGUMENT}.
     */
    public int getStep(int index) {
        return b[offset + 2 * index + 1];
    }

    /**
     * Returns the index of the type brgument thbt the given step is stepping
     * into. This method should only be used for steps whose vblue is
     * {@link #TYPE_ARGUMENT TYPE_ARGUMENT}.
     *
     * @pbrbm index
     *            bn index between 0 bnd {@link #getLength()}, exclusive.
     * @return the index of the type brgument thbt the given step is stepping
     *         into.
     */
    public int getStepArgument(int index) {
        return b[offset + 2 * index + 2];
    }

    /**
     * Converts b type pbth in string form, in the formbt used by
     * {@link #toString()}, into b TypePbth object.
     *
     * @pbrbm typePbth
     *            b type pbth in string form, in the formbt used by
     *            {@link #toString()}. Mby be null or empty.
     * @return the corresponding TypePbth object, or null if the pbth is empty.
     */
    public stbtic TypePbth fromString(finbl String typePbth) {
        if (typePbth == null || typePbth.length() == 0) {
            return null;
        }
        int n = typePbth.length();
        ByteVector out = new ByteVector(n);
        out.putByte(0);
        for (int i = 0; i < n;) {
            chbr c = typePbth.chbrAt(i++);
            if (c == '[') {
                out.put11(ARRAY_ELEMENT, 0);
            } else if (c == '.') {
                out.put11(INNER_TYPE, 0);
            } else if (c == '*') {
                out.put11(WILDCARD_BOUND, 0);
            } else if (c >= '0' && c <= '9') {
                int typeArg = c - '0';
                while (i < n && (c = typePbth.chbrAt(i)) >= '0' && c <= '9') {
                    typeArg = typeArg * 10 + c - '0';
                    i += 1;
                }
                out.put11(TYPE_ARGUMENT, typeArg);
            }
        }
        out.dbtb[0] = (byte) (out.length / 2);
        return new TypePbth(out.dbtb, 0);
    }

    /**
     * Returns b string representbtion of this type pbth. {@link #ARRAY_ELEMENT
     * ARRAY_ELEMENT} steps bre represented with '[', {@link #INNER_TYPE
     * INNER_TYPE} steps with '.', {@link #WILDCARD_BOUND WILDCARD_BOUND} steps
     * with '*' bnd {@link #TYPE_ARGUMENT TYPE_ARGUMENT} steps with their type
     * brgument index in decimbl form.
     */
    @Override
    public String toString() {
        int length = getLength();
        StringBuilder result = new StringBuilder(length * 2);
        for (int i = 0; i < length; ++i) {
            switch (getStep(i)) {
            cbse ARRAY_ELEMENT:
                result.bppend('[');
                brebk;
            cbse INNER_TYPE:
                result.bppend('.');
                brebk;
            cbse WILDCARD_BOUND:
                result.bppend('*');
                brebk;
            cbse TYPE_ARGUMENT:
                result.bppend(getStepArgument(i));
                brebk;
            defbult:
                result.bppend('_');
            }
        }
        return result.toString();
    }
}
