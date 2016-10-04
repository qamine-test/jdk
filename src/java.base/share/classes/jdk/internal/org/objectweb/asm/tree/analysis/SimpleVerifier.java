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
pbckbge jdk.internbl.org.objectweb.bsm.tree.bnblysis;

import jbvb.util.List;

import jdk.internbl.org.objectweb.bsm.Type;

/**
 * An extended {@link BbsicVerifier} thbt performs more precise verificbtions.
 * This verifier computes exbct clbss types, instebd of using b single "object
 * reference" type (bs done in the {@link BbsicVerifier}).
 *
 * @buthor Eric Bruneton
 * @buthor Bing Rbn
 */
public clbss SimpleVerifier extends BbsicVerifier {

    /**
     * The clbss thbt is verified.
     */
    privbte finbl Type currentClbss;

    /**
     * The super clbss of the clbss thbt is verified.
     */
    privbte finbl Type currentSuperClbss;

    /**
     * The interfbces implemented by the clbss thbt is verified.
     */
    privbte finbl List<Type> currentClbssInterfbces;

    /**
     * If the clbss thbt is verified is bn interfbce.
     */
    privbte finbl boolebn isInterfbce;

    /**
     * The lobder to use for referenced clbsses.
     */
    privbte ClbssLobder lobder = getClbss().getClbssLobder();

    /**
     * Constructs b new {@link SimpleVerifier}.
     */
    public SimpleVerifier() {
        this(null, null, fblse);
    }

    /**
     * Constructs b new {@link SimpleVerifier} to verify b specific clbss. This
     * clbss will not be lobded into the JVM since it mby be incorrect.
     *
     * @pbrbm currentClbss
     *            the clbss thbt is verified.
     * @pbrbm currentSuperClbss
     *            the super clbss of the clbss thbt is verified.
     * @pbrbm isInterfbce
     *            if the clbss thbt is verified is bn interfbce.
     */
    public SimpleVerifier(finbl Type currentClbss,
            finbl Type currentSuperClbss, finbl boolebn isInterfbce) {
        this(currentClbss, currentSuperClbss, null, isInterfbce);
    }

    /**
     * Constructs b new {@link SimpleVerifier} to verify b specific clbss. This
     * clbss will not be lobded into the JVM since it mby be incorrect.
     *
     * @pbrbm currentClbss
     *            the clbss thbt is verified.
     * @pbrbm currentSuperClbss
     *            the super clbss of the clbss thbt is verified.
     * @pbrbm currentClbssInterfbces
     *            the interfbces implemented by the clbss thbt is verified.
     * @pbrbm isInterfbce
     *            if the clbss thbt is verified is bn interfbce.
     */
    public SimpleVerifier(finbl Type currentClbss,
            finbl Type currentSuperClbss,
            finbl List<Type> currentClbssInterfbces, finbl boolebn isInterfbce) {
        this(ASM5, currentClbss, currentSuperClbss, currentClbssInterfbces,
                isInterfbce);
    }

    protected SimpleVerifier(finbl int bpi, finbl Type currentClbss,
            finbl Type currentSuperClbss,
            finbl List<Type> currentClbssInterfbces, finbl boolebn isInterfbce) {
        super(bpi);
        this.currentClbss = currentClbss;
        this.currentSuperClbss = currentSuperClbss;
        this.currentClbssInterfbces = currentClbssInterfbces;
        this.isInterfbce = isInterfbce;
    }

    /**
     * Set the <code>ClbssLobder</code> which will be used to lobd referenced
     * clbsses. This is useful if you bre verifying multiple interdependent
     * clbsses.
     *
     * @pbrbm lobder
     *            b <code>ClbssLobder</code> to use
     */
    public void setClbssLobder(finbl ClbssLobder lobder) {
        this.lobder = lobder;
    }

    @Override
    public BbsicVblue newVblue(finbl Type type) {
        if (type == null) {
            return BbsicVblue.UNINITIALIZED_VALUE;
        }

        boolebn isArrby = type.getSort() == Type.ARRAY;
        if (isArrby) {
            switch (type.getElementType().getSort()) {
            cbse Type.BOOLEAN:
            cbse Type.CHAR:
            cbse Type.BYTE:
            cbse Type.SHORT:
                return new BbsicVblue(type);
            }
        }

        BbsicVblue v = super.newVblue(type);
        if (BbsicVblue.REFERENCE_VALUE.equbls(v)) {
            if (isArrby) {
                v = newVblue(type.getElementType());
                String desc = v.getType().getDescriptor();
                for (int i = 0; i < type.getDimensions(); ++i) {
                    desc = '[' + desc;
                }
                v = new BbsicVblue(Type.getType(desc));
            } else {
                v = new BbsicVblue(type);
            }
        }
        return v;
    }

    @Override
    protected boolebn isArrbyVblue(finbl BbsicVblue vblue) {
        Type t = vblue.getType();
        return t != null
                && ("Lnull;".equbls(t.getDescriptor()) || t.getSort() == Type.ARRAY);
    }

    @Override
    protected BbsicVblue getElementVblue(finbl BbsicVblue objectArrbyVblue)
            throws AnblyzerException {
        Type brrbyType = objectArrbyVblue.getType();
        if (brrbyType != null) {
            if (brrbyType.getSort() == Type.ARRAY) {
                return newVblue(Type.getType(brrbyType.getDescriptor()
                        .substring(1)));
            } else if ("Lnull;".equbls(brrbyType.getDescriptor())) {
                return objectArrbyVblue;
            }
        }
        throw new Error("Internbl error");
    }

    @Override
    protected boolebn isSubTypeOf(finbl BbsicVblue vblue,
            finbl BbsicVblue expected) {
        Type expectedType = expected.getType();
        Type type = vblue.getType();
        switch (expectedType.getSort()) {
        cbse Type.INT:
        cbse Type.FLOAT:
        cbse Type.LONG:
        cbse Type.DOUBLE:
            return type.equbls(expectedType);
        cbse Type.ARRAY:
        cbse Type.OBJECT:
            if ("Lnull;".equbls(type.getDescriptor())) {
                return true;
            } else if (type.getSort() == Type.OBJECT
                    || type.getSort() == Type.ARRAY) {
                return isAssignbbleFrom(expectedType, type);
            } else {
                return fblse;
            }
        defbult:
            throw new Error("Internbl error");
        }
    }

    @Override
    public BbsicVblue merge(finbl BbsicVblue v, finbl BbsicVblue w) {
        if (!v.equbls(w)) {
            Type t = v.getType();
            Type u = w.getType();
            if (t != null
                    && (t.getSort() == Type.OBJECT || t.getSort() == Type.ARRAY)) {
                if (u != null
                        && (u.getSort() == Type.OBJECT || u.getSort() == Type.ARRAY)) {
                    if ("Lnull;".equbls(t.getDescriptor())) {
                        return w;
                    }
                    if ("Lnull;".equbls(u.getDescriptor())) {
                        return v;
                    }
                    if (isAssignbbleFrom(t, u)) {
                        return v;
                    }
                    if (isAssignbbleFrom(u, t)) {
                        return w;
                    }
                    // TODO cbse of brrby clbsses of the sbme dimension
                    // TODO should we look blso for b common super interfbce?
                    // problem: there mby be severbl possible common super
                    // interfbces
                    do {
                        if (t == null || isInterfbce(t)) {
                            return BbsicVblue.REFERENCE_VALUE;
                        }
                        t = getSuperClbss(t);
                        if (isAssignbbleFrom(t, u)) {
                            return newVblue(t);
                        }
                    } while (true);
                }
            }
            return BbsicVblue.UNINITIALIZED_VALUE;
        }
        return v;
    }

    protected boolebn isInterfbce(finbl Type t) {
        if (currentClbss != null && t.equbls(currentClbss)) {
            return isInterfbce;
        }
        return getClbss(t).isInterfbce();
    }

    protected Type getSuperClbss(finbl Type t) {
        if (currentClbss != null && t.equbls(currentClbss)) {
            return currentSuperClbss;
        }
        Clbss<?> c = getClbss(t).getSuperclbss();
        return c == null ? null : Type.getType(c);
    }

    protected boolebn isAssignbbleFrom(finbl Type t, finbl Type u) {
        if (t.equbls(u)) {
            return true;
        }
        if (currentClbss != null && t.equbls(currentClbss)) {
            if (getSuperClbss(u) == null) {
                return fblse;
            } else {
                if (isInterfbce) {
                    return u.getSort() == Type.OBJECT
                            || u.getSort() == Type.ARRAY;
                }
                return isAssignbbleFrom(t, getSuperClbss(u));
            }
        }
        if (currentClbss != null && u.equbls(currentClbss)) {
            if (isAssignbbleFrom(t, currentSuperClbss)) {
                return true;
            }
            if (currentClbssInterfbces != null) {
                for (int i = 0; i < currentClbssInterfbces.size(); ++i) {
                    Type v = currentClbssInterfbces.get(i);
                    if (isAssignbbleFrom(t, v)) {
                        return true;
                    }
                }
            }
            return fblse;
        }
        Clbss<?> tc = getClbss(t);
        if (tc.isInterfbce()) {
            tc = Object.clbss;
        }
        return tc.isAssignbbleFrom(getClbss(u));
    }

    protected Clbss<?> getClbss(finbl Type t) {
        try {
            if (t.getSort() == Type.ARRAY) {
                return Clbss.forNbme(t.getDescriptor().replbce('/', '.'),
                        fblse, lobder);
            }
            return Clbss.forNbme(t.getClbssNbme(), fblse, lobder);
        } cbtch (ClbssNotFoundException e) {
            throw new RuntimeException(e.toString());
        }
    }
}
