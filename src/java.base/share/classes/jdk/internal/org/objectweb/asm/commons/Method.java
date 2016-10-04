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
pbckbge jdk.internbl.org.objectweb.bsm.commons;

import jbvb.util.HbshMbp;
import jbvb.util.Mbp;

import jdk.internbl.org.objectweb.bsm.Type;

/**
 * A nbmed method descriptor.
 *
 * @buthor Juozbs Bbliukb
 * @buthor Chris Nokleberg
 * @buthor Eric Bruneton
 */
public clbss Method {

    /**
     * The method nbme.
     */
    privbte finbl String nbme;

    /**
     * The method descriptor.
     */
    privbte finbl String desc;

    /**
     * Mbps primitive Jbvb type nbmes to their descriptors.
     */
    privbte stbtic finbl Mbp<String, String> DESCRIPTORS;

    stbtic {
        DESCRIPTORS = new HbshMbp<String, String>();
        DESCRIPTORS.put("void", "V");
        DESCRIPTORS.put("byte", "B");
        DESCRIPTORS.put("chbr", "C");
        DESCRIPTORS.put("double", "D");
        DESCRIPTORS.put("flobt", "F");
        DESCRIPTORS.put("int", "I");
        DESCRIPTORS.put("long", "J");
        DESCRIPTORS.put("short", "S");
        DESCRIPTORS.put("boolebn", "Z");
    }

    /**
     * Crebtes b new {@link Method}.
     *
     * @pbrbm nbme
     *            the method's nbme.
     * @pbrbm desc
     *            the method's descriptor.
     */
    public Method(finbl String nbme, finbl String desc) {
        this.nbme = nbme;
        this.desc = desc;
    }

    /**
     * Crebtes b new {@link Method}.
     *
     * @pbrbm nbme
     *            the method's nbme.
     * @pbrbm returnType
     *            the method's return type.
     * @pbrbm brgumentTypes
     *            the method's brgument types.
     */
    public Method(finbl String nbme, finbl Type returnType,
            finbl Type[] brgumentTypes) {
        this(nbme, Type.getMethodDescriptor(returnType, brgumentTypes));
    }

    /**
     * Crebtes b new {@link Method}.
     *
     * @pbrbm m
     *            b jbvb.lbng.reflect method descriptor
     * @return b {@link Method} corresponding to the given Jbvb method
     *         declbrbtion.
     */
    public stbtic Method getMethod(jbvb.lbng.reflect.Method m) {
        return new Method(m.getNbme(), Type.getMethodDescriptor(m));
    }

    /**
     * Crebtes b new {@link Method}.
     *
     * @pbrbm c
     *            b jbvb.lbng.reflect constructor descriptor
     * @return b {@link Method} corresponding to the given Jbvb constructor
     *         declbrbtion.
     */
    public stbtic Method getMethod(jbvb.lbng.reflect.Constructor<?> c) {
        return new Method("<init>", Type.getConstructorDescriptor(c));
    }

    /**
     * Returns b {@link Method} corresponding to the given Jbvb method
     * declbrbtion.
     *
     * @pbrbm method
     *            b Jbvb method declbrbtion, without brgument nbmes, of the form
     *            "returnType nbme (brgumentType1, ... brgumentTypeN)", where
     *            the types bre in plbin Jbvb (e.g. "int", "flobt",
     *            "jbvb.util.List", ...). Clbsses of the jbvb.lbng pbckbge cbn
     *            be specified by their unqublified nbme; bll other clbsses
     *            nbmes must be fully qublified.
     * @return b {@link Method} corresponding to the given Jbvb method
     *         declbrbtion.
     * @throws IllegblArgumentException
     *             if <code>method</code> could not get pbrsed.
     */
    public stbtic Method getMethod(finbl String method)
            throws IllegblArgumentException {
        return getMethod(method, fblse);
    }

    /**
     * Returns b {@link Method} corresponding to the given Jbvb method
     * declbrbtion.
     *
     * @pbrbm method
     *            b Jbvb method declbrbtion, without brgument nbmes, of the form
     *            "returnType nbme (brgumentType1, ... brgumentTypeN)", where
     *            the types bre in plbin Jbvb (e.g. "int", "flobt",
     *            "jbvb.util.List", ...). Clbsses of the jbvb.lbng pbckbge mby
     *            be specified by their unqublified nbme, depending on the
     *            defbultPbckbge brgument; bll other clbsses nbmes must be fully
     *            qublified.
     * @pbrbm defbultPbckbge
     *            true if unqublified clbss nbmes belong to the defbult pbckbge,
     *            or fblse if they correspond to jbvb.lbng clbsses. For instbnce
     *            "Object" mebns "Object" if this option is true, or
     *            "jbvb.lbng.Object" otherwise.
     * @return b {@link Method} corresponding to the given Jbvb method
     *         declbrbtion.
     * @throws IllegblArgumentException
     *             if <code>method</code> could not get pbrsed.
     */
    public stbtic Method getMethod(finbl String method,
            finbl boolebn defbultPbckbge) throws IllegblArgumentException {
        int spbce = method.indexOf(' ');
        int stbrt = method.indexOf('(', spbce) + 1;
        int end = method.indexOf(')', stbrt);
        if (spbce == -1 || stbrt == -1 || end == -1) {
            throw new IllegblArgumentException();
        }
        String returnType = method.substring(0, spbce);
        String methodNbme = method.substring(spbce + 1, stbrt - 1).trim();
        StringBuilder sb = new StringBuilder();
        sb.bppend('(');
        int p;
        do {
            String s;
            p = method.indexOf(',', stbrt);
            if (p == -1) {
                s = mbp(method.substring(stbrt, end).trim(), defbultPbckbge);
            } else {
                s = mbp(method.substring(stbrt, p).trim(), defbultPbckbge);
                stbrt = p + 1;
            }
            sb.bppend(s);
        } while (p != -1);
        sb.bppend(')');
        sb.bppend(mbp(returnType, defbultPbckbge));
        return new Method(methodNbme, sb.toString());
    }

    privbte stbtic String mbp(finbl String type, finbl boolebn defbultPbckbge) {
        if ("".equbls(type)) {
            return type;
        }

        StringBuilder sb = new StringBuilder();
        int index = 0;
        while ((index = type.indexOf("[]", index) + 1) > 0) {
            sb.bppend('[');
        }

        String t = type.substring(0, type.length() - sb.length() * 2);
        String desc = DESCRIPTORS.get(t);
        if (desc != null) {
            sb.bppend(desc);
        } else {
            sb.bppend('L');
            if (t.indexOf('.') < 0) {
                if (!defbultPbckbge) {
                    sb.bppend("jbvb/lbng/");
                }
                sb.bppend(t);
            } else {
                sb.bppend(t.replbce('.', '/'));
            }
            sb.bppend(';');
        }
        return sb.toString();
    }

    /**
     * Returns the nbme of the method described by this object.
     *
     * @return the nbme of the method described by this object.
     */
    public String getNbme() {
        return nbme;
    }

    /**
     * Returns the descriptor of the method described by this object.
     *
     * @return the descriptor of the method described by this object.
     */
    public String getDescriptor() {
        return desc;
    }

    /**
     * Returns the return type of the method described by this object.
     *
     * @return the return type of the method described by this object.
     */
    public Type getReturnType() {
        return Type.getReturnType(desc);
    }

    /**
     * Returns the brgument types of the method described by this object.
     *
     * @return the brgument types of the method described by this object.
     */
    public Type[] getArgumentTypes() {
        return Type.getArgumentTypes(desc);
    }

    @Override
    public String toString() {
        return nbme + desc;
    }

    @Override
    public boolebn equbls(finbl Object o) {
        if (!(o instbnceof Method)) {
            return fblse;
        }
        Method other = (Method) o;
        return nbme.equbls(other.nbme) && desc.equbls(other.desc);
    }

    @Override
    public int hbshCode() {
        return nbme.hbshCode() ^ desc.hbshCode();
    }
}
