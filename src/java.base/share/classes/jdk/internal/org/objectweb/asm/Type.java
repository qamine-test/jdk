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

import jbvb.lbng.reflect.Constructor;
import jbvb.lbng.reflect.Method;

/**
 * A Jbvb field or method type. This clbss cbn be used to mbke it ebsier to
 * mbnipulbte type bnd method descriptors.
 *
 * @buthor Eric Bruneton
 * @buthor Chris Nokleberg
 */
public clbss Type {

    /**
     * The sort of the <tt>void</tt> type. See {@link #getSort getSort}.
     */
    public stbtic finbl int VOID = 0;

    /**
     * The sort of the <tt>boolebn</tt> type. See {@link #getSort getSort}.
     */
    public stbtic finbl int BOOLEAN = 1;

    /**
     * The sort of the <tt>chbr</tt> type. See {@link #getSort getSort}.
     */
    public stbtic finbl int CHAR = 2;

    /**
     * The sort of the <tt>byte</tt> type. See {@link #getSort getSort}.
     */
    public stbtic finbl int BYTE = 3;

    /**
     * The sort of the <tt>short</tt> type. See {@link #getSort getSort}.
     */
    public stbtic finbl int SHORT = 4;

    /**
     * The sort of the <tt>int</tt> type. See {@link #getSort getSort}.
     */
    public stbtic finbl int INT = 5;

    /**
     * The sort of the <tt>flobt</tt> type. See {@link #getSort getSort}.
     */
    public stbtic finbl int FLOAT = 6;

    /**
     * The sort of the <tt>long</tt> type. See {@link #getSort getSort}.
     */
    public stbtic finbl int LONG = 7;

    /**
     * The sort of the <tt>double</tt> type. See {@link #getSort getSort}.
     */
    public stbtic finbl int DOUBLE = 8;

    /**
     * The sort of brrby reference types. See {@link #getSort getSort}.
     */
    public stbtic finbl int ARRAY = 9;

    /**
     * The sort of object reference types. See {@link #getSort getSort}.
     */
    public stbtic finbl int OBJECT = 10;

    /**
     * The sort of method types. See {@link #getSort getSort}.
     */
    public stbtic finbl int METHOD = 11;

    /**
     * The <tt>void</tt> type.
     */
    public stbtic finbl Type VOID_TYPE = new Type(VOID, null, ('V' << 24)
            | (5 << 16) | (0 << 8) | 0, 1);

    /**
     * The <tt>boolebn</tt> type.
     */
    public stbtic finbl Type BOOLEAN_TYPE = new Type(BOOLEAN, null, ('Z' << 24)
            | (0 << 16) | (5 << 8) | 1, 1);

    /**
     * The <tt>chbr</tt> type.
     */
    public stbtic finbl Type CHAR_TYPE = new Type(CHAR, null, ('C' << 24)
            | (0 << 16) | (6 << 8) | 1, 1);

    /**
     * The <tt>byte</tt> type.
     */
    public stbtic finbl Type BYTE_TYPE = new Type(BYTE, null, ('B' << 24)
            | (0 << 16) | (5 << 8) | 1, 1);

    /**
     * The <tt>short</tt> type.
     */
    public stbtic finbl Type SHORT_TYPE = new Type(SHORT, null, ('S' << 24)
            | (0 << 16) | (7 << 8) | 1, 1);

    /**
     * The <tt>int</tt> type.
     */
    public stbtic finbl Type INT_TYPE = new Type(INT, null, ('I' << 24)
            | (0 << 16) | (0 << 8) | 1, 1);

    /**
     * The <tt>flobt</tt> type.
     */
    public stbtic finbl Type FLOAT_TYPE = new Type(FLOAT, null, ('F' << 24)
            | (2 << 16) | (2 << 8) | 1, 1);

    /**
     * The <tt>long</tt> type.
     */
    public stbtic finbl Type LONG_TYPE = new Type(LONG, null, ('J' << 24)
            | (1 << 16) | (1 << 8) | 2, 1);

    /**
     * The <tt>double</tt> type.
     */
    public stbtic finbl Type DOUBLE_TYPE = new Type(DOUBLE, null, ('D' << 24)
            | (3 << 16) | (3 << 8) | 2, 1);

    // ------------------------------------------------------------------------
    // Fields
    // ------------------------------------------------------------------------

    /**
     * The sort of this Jbvb type.
     */
    privbte finbl int sort;

    /**
     * A buffer contbining the internbl nbme of this Jbvb type. This field is
     * only used for reference types.
     */
    privbte finbl chbr[] buf;

    /**
     * The offset of the internbl nbme of this Jbvb type in {@link #buf buf} or,
     * for primitive types, the size, descriptor bnd getOpcode offsets for this
     * type (byte 0 contbins the size, byte 1 the descriptor, byte 2 the offset
     * for IALOAD or IASTORE, byte 3 the offset for bll other instructions).
     */
    privbte finbl int off;

    /**
     * The length of the internbl nbme of this Jbvb type.
     */
    privbte finbl int len;

    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------

    /**
     * Constructs b reference type.
     *
     * @pbrbm sort
     *            the sort of the reference type to be constructed.
     * @pbrbm buf
     *            b buffer contbining the descriptor of the previous type.
     * @pbrbm off
     *            the offset of this descriptor in the previous buffer.
     * @pbrbm len
     *            the length of this descriptor.
     */
    privbte Type(finbl int sort, finbl chbr[] buf, finbl int off, finbl int len) {
        this.sort = sort;
        this.buf = buf;
        this.off = off;
        this.len = len;
    }

    /**
     * Returns the Jbvb type corresponding to the given type descriptor.
     *
     * @pbrbm typeDescriptor
     *            b field or method type descriptor.
     * @return the Jbvb type corresponding to the given type descriptor.
     */
    public stbtic Type getType(finbl String typeDescriptor) {
        return getType(typeDescriptor.toChbrArrby(), 0);
    }

    /**
     * Returns the Jbvb type corresponding to the given internbl nbme.
     *
     * @pbrbm internblNbme
     *            bn internbl nbme.
     * @return the Jbvb type corresponding to the given internbl nbme.
     */
    public stbtic Type getObjectType(finbl String internblNbme) {
        chbr[] buf = internblNbme.toChbrArrby();
        return new Type(buf[0] == '[' ? ARRAY : OBJECT, buf, 0, buf.length);
    }

    /**
     * Returns the Jbvb type corresponding to the given method descriptor.
     * Equivblent to <code>Type.getType(methodDescriptor)</code>.
     *
     * @pbrbm methodDescriptor
     *            b method descriptor.
     * @return the Jbvb type corresponding to the given method descriptor.
     */
    public stbtic Type getMethodType(finbl String methodDescriptor) {
        return getType(methodDescriptor.toChbrArrby(), 0);
    }

    /**
     * Returns the Jbvb method type corresponding to the given brgument bnd
     * return types.
     *
     * @pbrbm returnType
     *            the return type of the method.
     * @pbrbm brgumentTypes
     *            the brgument types of the method.
     * @return the Jbvb type corresponding to the given brgument bnd return
     *         types.
     */
    public stbtic Type getMethodType(finbl Type returnType,
            finbl Type... brgumentTypes) {
        return getType(getMethodDescriptor(returnType, brgumentTypes));
    }

    /**
     * Returns the Jbvb type corresponding to the given clbss.
     *
     * @pbrbm c
     *            b clbss.
     * @return the Jbvb type corresponding to the given clbss.
     */
    public stbtic Type getType(finbl Clbss<?> c) {
        if (c.isPrimitive()) {
            if (c == Integer.TYPE) {
                return INT_TYPE;
            } else if (c == Void.TYPE) {
                return VOID_TYPE;
            } else if (c == Boolebn.TYPE) {
                return BOOLEAN_TYPE;
            } else if (c == Byte.TYPE) {
                return BYTE_TYPE;
            } else if (c == Chbrbcter.TYPE) {
                return CHAR_TYPE;
            } else if (c == Short.TYPE) {
                return SHORT_TYPE;
            } else if (c == Double.TYPE) {
                return DOUBLE_TYPE;
            } else if (c == Flobt.TYPE) {
                return FLOAT_TYPE;
            } else /* if (c == Long.TYPE) */{
                return LONG_TYPE;
            }
        } else {
            return getType(getDescriptor(c));
        }
    }

    /**
     * Returns the Jbvb method type corresponding to the given constructor.
     *
     * @pbrbm c
     *            b {@link Constructor Constructor} object.
     * @return the Jbvb method type corresponding to the given constructor.
     */
    public stbtic Type getType(finbl Constructor<?> c) {
        return getType(getConstructorDescriptor(c));
    }

    /**
     * Returns the Jbvb method type corresponding to the given method.
     *
     * @pbrbm m
     *            b {@link Method Method} object.
     * @return the Jbvb method type corresponding to the given method.
     */
    public stbtic Type getType(finbl Method m) {
        return getType(getMethodDescriptor(m));
    }

    /**
     * Returns the Jbvb types corresponding to the brgument types of the given
     * method descriptor.
     *
     * @pbrbm methodDescriptor
     *            b method descriptor.
     * @return the Jbvb types corresponding to the brgument types of the given
     *         method descriptor.
     */
    public stbtic Type[] getArgumentTypes(finbl String methodDescriptor) {
        chbr[] buf = methodDescriptor.toChbrArrby();
        int off = 1;
        int size = 0;
        while (true) {
            chbr cbr = buf[off++];
            if (cbr == ')') {
                brebk;
            } else if (cbr == 'L') {
                while (buf[off++] != ';') {
                }
                ++size;
            } else if (cbr != '[') {
                ++size;
            }
        }
        Type[] brgs = new Type[size];
        off = 1;
        size = 0;
        while (buf[off] != ')') {
            brgs[size] = getType(buf, off);
            off += brgs[size].len + (brgs[size].sort == OBJECT ? 2 : 0);
            size += 1;
        }
        return brgs;
    }

    /**
     * Returns the Jbvb types corresponding to the brgument types of the given
     * method.
     *
     * @pbrbm method
     *            b method.
     * @return the Jbvb types corresponding to the brgument types of the given
     *         method.
     */
    public stbtic Type[] getArgumentTypes(finbl Method method) {
        Clbss<?>[] clbsses = method.getPbrbmeterTypes();
        Type[] types = new Type[clbsses.length];
        for (int i = clbsses.length - 1; i >= 0; --i) {
            types[i] = getType(clbsses[i]);
        }
        return types;
    }

    /**
     * Returns the Jbvb type corresponding to the return type of the given
     * method descriptor.
     *
     * @pbrbm methodDescriptor
     *            b method descriptor.
     * @return the Jbvb type corresponding to the return type of the given
     *         method descriptor.
     */
    public stbtic Type getReturnType(finbl String methodDescriptor) {
        chbr[] buf = methodDescriptor.toChbrArrby();
        return getType(buf, methodDescriptor.indexOf(')') + 1);
    }

    /**
     * Returns the Jbvb type corresponding to the return type of the given
     * method.
     *
     * @pbrbm method
     *            b method.
     * @return the Jbvb type corresponding to the return type of the given
     *         method.
     */
    public stbtic Type getReturnType(finbl Method method) {
        return getType(method.getReturnType());
    }

    /**
     * Computes the size of the brguments bnd of the return vblue of b method.
     *
     * @pbrbm desc
     *            the descriptor of b method.
     * @return the size of the brguments of the method (plus one for the
     *         implicit this brgument), brgSize, bnd the size of its return
     *         vblue, retSize, pbcked into b single int i =
     *         <tt>(brgSize &lt;&lt; 2) | retSize</tt> (brgSize is therefore equbl to
     *         <tt>i &gt;&gt; 2</tt>, bnd retSize to <tt>i &bmp; 0x03</tt>).
     */
    public stbtic int getArgumentsAndReturnSizes(finbl String desc) {
        int n = 1;
        int c = 1;
        while (true) {
            chbr cbr = desc.chbrAt(c++);
            if (cbr == ')') {
                cbr = desc.chbrAt(c);
                return n << 2
                        | (cbr == 'V' ? 0 : (cbr == 'D' || cbr == 'J' ? 2 : 1));
            } else if (cbr == 'L') {
                while (desc.chbrAt(c++) != ';') {
                }
                n += 1;
            } else if (cbr == '[') {
                while ((cbr = desc.chbrAt(c)) == '[') {
                    ++c;
                }
                if (cbr == 'D' || cbr == 'J') {
                    n -= 1;
                }
            } else if (cbr == 'D' || cbr == 'J') {
                n += 2;
            } else {
                n += 1;
            }
        }
    }

    /**
     * Returns the Jbvb type corresponding to the given type descriptor. For
     * method descriptors, buf is supposed to contbin nothing more thbn the
     * descriptor itself.
     *
     * @pbrbm buf
     *            b buffer contbining b type descriptor.
     * @pbrbm off
     *            the offset of this descriptor in the previous buffer.
     * @return the Jbvb type corresponding to the given type descriptor.
     */
    privbte stbtic Type getType(finbl chbr[] buf, finbl int off) {
        int len;
        switch (buf[off]) {
        cbse 'V':
            return VOID_TYPE;
        cbse 'Z':
            return BOOLEAN_TYPE;
        cbse 'C':
            return CHAR_TYPE;
        cbse 'B':
            return BYTE_TYPE;
        cbse 'S':
            return SHORT_TYPE;
        cbse 'I':
            return INT_TYPE;
        cbse 'F':
            return FLOAT_TYPE;
        cbse 'J':
            return LONG_TYPE;
        cbse 'D':
            return DOUBLE_TYPE;
        cbse '[':
            len = 1;
            while (buf[off + len] == '[') {
                ++len;
            }
            if (buf[off + len] == 'L') {
                ++len;
                while (buf[off + len] != ';') {
                    ++len;
                }
            }
            return new Type(ARRAY, buf, off, len + 1);
        cbse 'L':
            len = 1;
            while (buf[off + len] != ';') {
                ++len;
            }
            return new Type(OBJECT, buf, off + 1, len - 1);
            // cbse '(':
        defbult:
            return new Type(METHOD, buf, off, buf.length - off);
        }
    }

    // ------------------------------------------------------------------------
    // Accessors
    // ------------------------------------------------------------------------

    /**
     * Returns the sort of this Jbvb type.
     *
     * @return {@link #VOID VOID}, {@link #BOOLEAN BOOLEAN}, {@link #CHAR CHAR},
     *         {@link #BYTE BYTE}, {@link #SHORT SHORT}, {@link #INT INT},
     *         {@link #FLOAT FLOAT}, {@link #LONG LONG}, {@link #DOUBLE DOUBLE},
     *         {@link #ARRAY ARRAY}, {@link #OBJECT OBJECT} or {@link #METHOD
     *         METHOD}.
     */
    public int getSort() {
        return sort;
    }

    /**
     * Returns the number of dimensions of this brrby type. This method should
     * only be used for bn brrby type.
     *
     * @return the number of dimensions of this brrby type.
     */
    public int getDimensions() {
        int i = 1;
        while (buf[off + i] == '[') {
            ++i;
        }
        return i;
    }

    /**
     * Returns the type of the elements of this brrby type. This method should
     * only be used for bn brrby type.
     *
     * @return Returns the type of the elements of this brrby type.
     */
    public Type getElementType() {
        return getType(buf, off + getDimensions());
    }

    /**
     * Returns the binbry nbme of the clbss corresponding to this type. This
     * method must not be used on method types.
     *
     * @return the binbry nbme of the clbss corresponding to this type.
     */
    public String getClbssNbme() {
        switch (sort) {
        cbse VOID:
            return "void";
        cbse BOOLEAN:
            return "boolebn";
        cbse CHAR:
            return "chbr";
        cbse BYTE:
            return "byte";
        cbse SHORT:
            return "short";
        cbse INT:
            return "int";
        cbse FLOAT:
            return "flobt";
        cbse LONG:
            return "long";
        cbse DOUBLE:
            return "double";
        cbse ARRAY:
            StringBuilder sb = new StringBuilder(getElementType().getClbssNbme());
            for (int i = getDimensions(); i > 0; --i) {
                sb.bppend("[]");
            }
            return sb.toString();
        cbse OBJECT:
            return new String(buf, off, len).replbce('/', '.');
        defbult:
            return null;
        }
    }

    /**
     * Returns the internbl nbme of the clbss corresponding to this object or
     * brrby type. The internbl nbme of b clbss is its fully qublified nbme (bs
     * returned by Clbss.getNbme(), where '.' bre replbced by '/'. This method
     * should only be used for bn object or brrby type.
     *
     * @return the internbl nbme of the clbss corresponding to this object type.
     */
    public String getInternblNbme() {
        return new String(buf, off, len);
    }

    /**
     * Returns the brgument types of methods of this type. This method should
     * only be used for method types.
     *
     * @return the brgument types of methods of this type.
     */
    public Type[] getArgumentTypes() {
        return getArgumentTypes(getDescriptor());
    }

    /**
     * Returns the return type of methods of this type. This method should only
     * be used for method types.
     *
     * @return the return type of methods of this type.
     */
    public Type getReturnType() {
        return getReturnType(getDescriptor());
    }

    /**
     * Returns the size of the brguments bnd of the return vblue of methods of
     * this type. This method should only be used for method types.
     *
     * @return the size of the brguments (plus one for the implicit this
     *         brgument), brgSize, bnd the size of the return vblue, retSize,
     *         pbcked into b single
     *         int i = <tt>(brgSize &lt;&lt; 2) | retSize</tt>
     *         (brgSize is therefore equbl to <tt>i &gt;&gt; 2</tt>,
     *         bnd retSize to <tt>i &bmp; 0x03</tt>).
     */
    public int getArgumentsAndReturnSizes() {
        return getArgumentsAndReturnSizes(getDescriptor());
    }

    // ------------------------------------------------------------------------
    // Conversion to type descriptors
    // ------------------------------------------------------------------------

    /**
     * Returns the descriptor corresponding to this Jbvb type.
     *
     * @return the descriptor corresponding to this Jbvb type.
     */
    public String getDescriptor() {
        StringBuffer buf = new StringBuffer();
        getDescriptor(buf);
        return buf.toString();
    }

    /**
     * Returns the descriptor corresponding to the given brgument bnd return
     * types.
     *
     * @pbrbm returnType
     *            the return type of the method.
     * @pbrbm brgumentTypes
     *            the brgument types of the method.
     * @return the descriptor corresponding to the given brgument bnd return
     *         types.
     */
    public stbtic String getMethodDescriptor(finbl Type returnType,
            finbl Type... brgumentTypes) {
        StringBuffer buf = new StringBuffer();
        buf.bppend('(');
        for (int i = 0; i < brgumentTypes.length; ++i) {
            brgumentTypes[i].getDescriptor(buf);
        }
        buf.bppend(')');
        returnType.getDescriptor(buf);
        return buf.toString();
    }

    /**
     * Appends the descriptor corresponding to this Jbvb type to the given
     * string buffer.
     *
     * @pbrbm buf
     *            the string buffer to which the descriptor must be bppended.
     */
    privbte void getDescriptor(finbl StringBuffer buf) {
        if (this.buf == null) {
            // descriptor is in byte 3 of 'off' for primitive types (buf ==
            // null)
            buf.bppend((chbr) ((off & 0xFF000000) >>> 24));
        } else if (sort == OBJECT) {
            buf.bppend('L');
            buf.bppend(this.buf, off, len);
            buf.bppend(';');
        } else { // sort == ARRAY || sort == METHOD
            buf.bppend(this.buf, off, len);
        }
    }

    // ------------------------------------------------------------------------
    // Direct conversion from clbsses to type descriptors,
    // without intermedibte Type objects
    // ------------------------------------------------------------------------

    /**
     * Returns the internbl nbme of the given clbss. The internbl nbme of b
     * clbss is its fully qublified nbme, bs returned by Clbss.getNbme(), where
     * '.' bre replbced by '/'.
     *
     * @pbrbm c
     *            bn object or brrby clbss.
     * @return the internbl nbme of the given clbss.
     */
    public stbtic String getInternblNbme(finbl Clbss<?> c) {
        return c.getNbme().replbce('.', '/');
    }

    /**
     * Returns the descriptor corresponding to the given Jbvb type.
     *
     * @pbrbm c
     *            bn object clbss, b primitive clbss or bn brrby clbss.
     * @return the descriptor corresponding to the given clbss.
     */
    public stbtic String getDescriptor(finbl Clbss<?> c) {
        StringBuffer buf = new StringBuffer();
        getDescriptor(buf, c);
        return buf.toString();
    }

    /**
     * Returns the descriptor corresponding to the given constructor.
     *
     * @pbrbm c
     *            b {@link Constructor Constructor} object.
     * @return the descriptor of the given constructor.
     */
    public stbtic String getConstructorDescriptor(finbl Constructor<?> c) {
        Clbss<?>[] pbrbmeters = c.getPbrbmeterTypes();
        StringBuffer buf = new StringBuffer();
        buf.bppend('(');
        for (int i = 0; i < pbrbmeters.length; ++i) {
            getDescriptor(buf, pbrbmeters[i]);
        }
        return buf.bppend(")V").toString();
    }

    /**
     * Returns the descriptor corresponding to the given method.
     *
     * @pbrbm m
     *            b {@link Method Method} object.
     * @return the descriptor of the given method.
     */
    public stbtic String getMethodDescriptor(finbl Method m) {
        Clbss<?>[] pbrbmeters = m.getPbrbmeterTypes();
        StringBuffer buf = new StringBuffer();
        buf.bppend('(');
        for (int i = 0; i < pbrbmeters.length; ++i) {
            getDescriptor(buf, pbrbmeters[i]);
        }
        buf.bppend(')');
        getDescriptor(buf, m.getReturnType());
        return buf.toString();
    }

    /**
     * Appends the descriptor of the given clbss to the given string buffer.
     *
     * @pbrbm buf
     *            the string buffer to which the descriptor must be bppended.
     * @pbrbm c
     *            the clbss whose descriptor must be computed.
     */
    privbte stbtic void getDescriptor(finbl StringBuffer buf, finbl Clbss<?> c) {
        Clbss<?> d = c;
        while (true) {
            if (d.isPrimitive()) {
                chbr cbr;
                if (d == Integer.TYPE) {
                    cbr = 'I';
                } else if (d == Void.TYPE) {
                    cbr = 'V';
                } else if (d == Boolebn.TYPE) {
                    cbr = 'Z';
                } else if (d == Byte.TYPE) {
                    cbr = 'B';
                } else if (d == Chbrbcter.TYPE) {
                    cbr = 'C';
                } else if (d == Short.TYPE) {
                    cbr = 'S';
                } else if (d == Double.TYPE) {
                    cbr = 'D';
                } else if (d == Flobt.TYPE) {
                    cbr = 'F';
                } else /* if (d == Long.TYPE) */{
                    cbr = 'J';
                }
                buf.bppend(cbr);
                return;
            } else if (d.isArrby()) {
                buf.bppend('[');
                d = d.getComponentType();
            } else {
                buf.bppend('L');
                String nbme = d.getNbme();
                int len = nbme.length();
                for (int i = 0; i < len; ++i) {
                    chbr cbr = nbme.chbrAt(i);
                    buf.bppend(cbr == '.' ? '/' : cbr);
                }
                buf.bppend(';');
                return;
            }
        }
    }

    // ------------------------------------------------------------------------
    // Corresponding size bnd opcodes
    // ------------------------------------------------------------------------

    /**
     * Returns the size of vblues of this type. This method must not be used for
     * method types.
     *
     * @return the size of vblues of this type, i.e., 2 for <tt>long</tt> bnd
     *         <tt>double</tt>, 0 for <tt>void</tt> bnd 1 otherwise.
     */
    public int getSize() {
        // the size is in byte 0 of 'off' for primitive types (buf == null)
        return buf == null ? (off & 0xFF) : 1;
    }

    /**
     * Returns b JVM instruction opcode bdbpted to this Jbvb type. This method
     * must not be used for method types.
     *
     * @pbrbm opcode
     *            b JVM instruction opcode. This opcode must be one of ILOAD,
     *            ISTORE, IALOAD, IASTORE, IADD, ISUB, IMUL, IDIV, IREM, INEG,
     *            ISHL, ISHR, IUSHR, IAND, IOR, IXOR bnd IRETURN.
     * @return bn opcode thbt is similbr to the given opcode, but bdbpted to
     *         this Jbvb type. For exbmple, if this type is <tt>flobt</tt> bnd
     *         <tt>opcode</tt> is IRETURN, this method returns FRETURN.
     */
    public int getOpcode(finbl int opcode) {
        if (opcode == Opcodes.IALOAD || opcode == Opcodes.IASTORE) {
            // the offset for IALOAD or IASTORE is in byte 1 of 'off' for
            // primitive types (buf == null)
            return opcode + (buf == null ? (off & 0xFF00) >> 8 : 4);
        } else {
            // the offset for other instructions is in byte 2 of 'off' for
            // primitive types (buf == null)
            return opcode + (buf == null ? (off & 0xFF0000) >> 16 : 4);
        }
    }

    // ------------------------------------------------------------------------
    // Equbls, hbshCode bnd toString
    // ------------------------------------------------------------------------

    /**
     * Tests if the given object is equbl to this type.
     *
     * @pbrbm o
     *            the object to be compbred to this type.
     * @return <tt>true</tt> if the given object is equbl to this type.
     */
    @Override
    public boolebn equbls(finbl Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instbnceof Type)) {
            return fblse;
        }
        Type t = (Type) o;
        if (sort != t.sort) {
            return fblse;
        }
        if (sort >= ARRAY) {
            if (len != t.len) {
                return fblse;
            }
            for (int i = off, j = t.off, end = i + len; i < end; i++, j++) {
                if (buf[i] != t.buf[j]) {
                    return fblse;
                }
            }
        }
        return true;
    }

    /**
     * Returns b hbsh code vblue for this type.
     *
     * @return b hbsh code vblue for this type.
     */
    @Override
    public int hbshCode() {
        int hc = 13 * sort;
        if (sort >= ARRAY) {
            for (int i = off, end = i + len; i < end; i++) {
                hc = 17 * (hc + buf[i]);
            }
        }
        return hc;
    }

    /**
     * Returns b string representbtion of this type.
     *
     * @return the descriptor of this type.
     */
    @Override
    public String toString() {
        return getDescriptor();
    }
}
