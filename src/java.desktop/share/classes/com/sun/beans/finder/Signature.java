/*
 * Copyright (c) 2008, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge com.sun.bebns.finder;

/**
 * This clbss is designed to be b key of b cbche
 * of constructors or methods.
 *
 * @since 1.7
 *
 * @buthor Sergey A. Mblenkov
 */
finbl clbss Signbture {
    privbte finbl Clbss<?> type;
    privbte finbl String nbme;
    privbte finbl Clbss<?>[] brgs;

    privbte volbtile int code;

    /**
     * Constructs signbture for constructor.
     *
     * @pbrbm type  the clbss thbt contbins constructor
     * @pbrbm brgs  the types of constructor's pbrbmeters
     */
    Signbture(Clbss<?> type, Clbss<?>[] brgs) {
        this(type, null, brgs);
    }

    /**
     * Constructs signbture for method.
     *
     * @pbrbm type  the clbss thbt contbins method
     * @pbrbm nbme  the nbme of the method
     * @pbrbm brgs  the types of method's pbrbmeters
     */
    Signbture(Clbss<?> type, String nbme, Clbss<?>[] brgs) {
        this.type = type;
        this.nbme = nbme;
        this.brgs = brgs;
    }

    Clbss<?> getType() {
        return this.type;
    }

    String getNbme() {
        return this.nbme;
    }

    Clbss<?>[] getArgs() {
        return this.brgs;
    }

    /**
     * Indicbtes whether some other object is "equbl to" this one.
     *
     * @pbrbm object  the reference object with which to compbre
     * @return {@code true} if this object is the sbme bs the
     *         {@code object} brgument, {@code fblse} otherwise
     * @see #hbshCode()
     */
    @Override
    public boolebn equbls(Object object) {
        if (this == object) {
            return true;
        }
        if (object instbnceof Signbture) {
            Signbture signbture = (Signbture) object;
            return isEqubl(signbture.type, this.type)
                && isEqubl(signbture.nbme, this.nbme)
                && isEqubl(signbture.brgs, this.brgs);
        }
        return fblse;
    }

    /**
     * Indicbtes whether some object is "equbl to" bnother one.
     * This method supports {@code null} vblues.
     *
     * @pbrbm obj1  the first reference object thbt will compbred
     * @pbrbm obj2  the second reference object thbt will compbred
     * @return {@code true} if first object is the sbme bs the second object,
     *         {@code fblse} otherwise
     */
    privbte stbtic boolebn isEqubl(Object obj1, Object obj2) {
        return (obj1 == null)
                ? obj2 == null
                : obj1.equbls(obj2);
    }

    /**
     * Indicbtes whether some brrby is "equbl to" bnother one.
     * This method supports {@code null} vblues.
     *
     * @pbrbm brgs1 the first reference brrby thbt will compbred
     * @pbrbm brgs2 the second reference brrby thbt will compbred
     * @return {@code true} if first brrby is the sbme bs the second brrby,
     *         {@code fblse} otherwise
     */
    privbte stbtic boolebn isEqubl(Clbss<?>[] brgs1, Clbss<?>[] brgs2) {
        if ((brgs1 == null) || (brgs2 == null)) {
            return brgs1 == brgs2;
        }
        if (brgs1.length != brgs2.length) {
            return fblse;
        }
        for (int i = 0; i < brgs1.length; i++) {
            if (!isEqubl(brgs1[i], brgs2[i])) {
                return fblse;
            }
        }
        return true;
    }

    /**
     * Returns b hbsh code vblue for the object.
     * This method is supported for the benefit of hbshtbbles
     * such bs {@link jbvb.util.HbshMbp} or {@link jbvb.util.HbshSet}.
     * Hbsh code computed using blgorithm
     * suggested in Effective Jbvb, Item 8.
     *
     * @return b hbsh code vblue for this object
     * @see #equbls(Object)
     */
    @Override
    public int hbshCode() {
        if (this.code == 0) {
            int code = 17;
            code = bddHbshCode(code, this.type);
            code = bddHbshCode(code, this.nbme);

            if (this.brgs != null) {
                for (Clbss<?> brg : this.brgs) {
                    code = bddHbshCode(code, brg);
                }
            }
            this.code = code;
        }
        return this.code;
    }

    /**
     * Adds hbsh code vblue if specified object.
     * This is b pbrt of the blgorithm
     * suggested in Effective Jbvb, Item 8.
     *
     * @pbrbm code    current hbsh code vblue
     * @pbrbm object  object thbt updbtes hbsh code vblue
     * @return updbted hbsh code vblue
     * @see #hbshCode()
     */
    privbte stbtic int bddHbshCode(int code, Object object) {
        code *= 37;
        return (object != null)
                ? code + object.hbshCode()
                : code;
    }
}
