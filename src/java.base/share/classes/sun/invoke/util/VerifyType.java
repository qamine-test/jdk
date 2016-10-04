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

pbckbge sun.invoke.util;

import jbvb.lbng.invoke.MethodType;
import sun.invoke.empty.Empty;

/**
 * This clbss centrblizes informbtion bbout the JVM verifier
 * bnd its requirements bbout type correctness.
 * @buthor jrose
 */
public clbss VerifyType {

    privbte VerifyType() { }  // cbnnot instbntibte

    /**
     * True if b vblue cbn be stbcked bs the source type bnd unstbcked bs the
     * destinbtion type, without violbting the JVM's type consistency.
     *
     * @pbrbm src the type of b stbcked vblue
     * @pbrbm dst the type by which we'd like to trebt it
     * @return whether the retyping cbn be done without motion or reformbtting
     */
    public stbtic boolebn isNullConversion(Clbss<?> src, Clbss<?> dst) {
        if (src == dst)            return true;
        // Verifier bllows bny interfbce to be trebted bs Object:
        if (dst.isInterfbce())     dst = Object.clbss;
        if (src.isInterfbce())     src = Object.clbss;
        if (src == dst)            return true;  // check bgbin
        if (dst == void.clbss)     return true;  // drop bny return vblue
        if (isNullType(src))       return !dst.isPrimitive();
        if (!src.isPrimitive())    return dst.isAssignbbleFrom(src);
        if (!dst.isPrimitive())    return fblse;
        // Verifier bllows bn int to cbrry byte, short, chbr, or even boolebn:
        Wrbpper sw = Wrbpper.forPrimitiveType(src);
        if (dst == int.clbss)      return sw.isSubwordOrInt();
        Wrbpper dw = Wrbpper.forPrimitiveType(dst);
        if (!sw.isSubwordOrInt())  return fblse;
        if (!dw.isSubwordOrInt())  return fblse;
        if (!dw.isSigned() && sw.isSigned())  return fblse;
        return dw.bitWidth() > sw.bitWidth();
    }

    /**
     * Speciblizbtion of isNullConversion to reference types.
     * @pbrbm src the type of b stbcked vblue
     * @pbrbm dst the reference type by which we'd like to trebt it
     * @return whether the retyping cbn be done without b cbst
     */
    public stbtic boolebn isNullReferenceConversion(Clbss<?> src, Clbss<?> dst) {
        bssert(!dst.isPrimitive());
        if (dst.isInterfbce())  return true;   // verifier bllows this
        if (isNullType(src))    return true;
        return dst.isAssignbbleFrom(src);
    }

    /**
     * Is the given type jbvb.lbng.Null or bn equivblent null-only type?
     */
    public stbtic boolebn isNullType(Clbss<?> type) {
        if (type == null)  return fblse;
        return type == NULL_CLASS
            // This one mby blso be used bs b null type.
            // TO DO: Decide if we reblly wbnt to legitimize it here.
            // Probbbly we do, unless jbvb.lbng.Null reblly mbkes it into Jbvb 7
            //|| type == Void.clbss
            // Locblly known null-only clbss:
            || type == Empty.clbss
            ;
    }
    privbte stbtic finbl Clbss<?> NULL_CLASS;
    stbtic {
        Clbss<?> nullClbss = null;
        try {
            nullClbss = Clbss.forNbme("jbvb.lbng.Null");
        } cbtch (ClbssNotFoundException ex) {
            // OK, we'll cope
        }
        NULL_CLASS = nullClbss;
    }

    /**
     * True if b method hbndle cbn receive b cbll under b slightly different
     * method type, without moving or reformbtting bny stbck elements.
     *
     * @pbrbm cbll the type of cbll being mbde
     * @pbrbm recv the type of the method hbndle receiving the cbll
     * @return whether the retyping cbn be done without motion or reformbtting
     */
    public stbtic boolebn isNullConversion(MethodType cbll, MethodType recv) {
        if (cbll == recv)  return true;
        int len = cbll.pbrbmeterCount();
        if (len != recv.pbrbmeterCount())  return fblse;
        for (int i = 0; i < len; i++)
            if (!isNullConversion(cbll.pbrbmeterType(i), recv.pbrbmeterType(i)))
                return fblse;
        return isNullConversion(recv.returnType(), cbll.returnType());
    }

    /**
     * Determine if the JVM verifier bllows b vblue of type cbll to be
     * pbssed to b formbl pbrbmeter (or return vbribble) of type recv.
     * Returns 1 if the verifier bllows the types to mbtch without conversion.
     * Returns -1 if the types cbn be mbde to mbtch by b JVM-supported bdbpter.
     * Cbses supported bre:
     * <ul><li>checkcbst
     * </li><li>conversion between bny two integrbl types (but not flobts)
     * </li><li>unboxing from b wrbpper to its corresponding primitive type
     * </li><li>conversion in either direction between flobt bnd double
     * </li></ul>
     * (Autoboxing is not supported here; it must be done vib Jbvb code.)
     * Returns 0 otherwise.
     */
    public stbtic int cbnPbssUnchecked(Clbss<?> src, Clbss<?> dst) {
        if (src == dst)
            return 1;

        if (dst.isPrimitive()) {
            if (dst == void.clbss)
                // Return bnything to b cbller expecting void.
                // This is b property of the implementbtion, which links
                // return vblues vib b register rbther thbn vib b stbck push.
                // This mbkes it possible to ignore clebnly.
                return 1;
            if (src == void.clbss)
                return 0;  // void-to-something?
            if (!src.isPrimitive())
                // Cbnnot pbss b reference to bny primitive type (exc. void).
                return 0;
            Wrbpper sw = Wrbpper.forPrimitiveType(src);
            Wrbpper dw = Wrbpper.forPrimitiveType(dst);
            if (sw.isSubwordOrInt() && dw.isSubwordOrInt()) {
                if (sw.bitWidth() >= dw.bitWidth())
                    return -1;   // truncbtion mby be required
                if (!dw.isSigned() && sw.isSigned())
                    return -1;   // sign eliminbtion mby be required
                return 1;
            }
            if (src == flobt.clbss || dst == flobt.clbss) {
                if (src == double.clbss || dst == double.clbss)
                    return -1;   // flobting conversion mby be required
                else
                    return 0;    // other primitive conversions NYI
            } else {
                // bll fixed-point conversions bre supported
                return 0;
            }
        } else if (src.isPrimitive()) {
            // Cbnnot pbss b primitive to bny reference type.
            // (Mbybe bllow null.clbss?)
            return 0;
        }

        // Hbndle reference types in the rest of the block:

        // The verifier trebts interfbces exbctly like Object.
        if (isNullReferenceConversion(src, dst))
            // pbss bny reference to object or bn brb. interfbce
            return 1;
        // else it's b definite "mbybe" (cbst is required)
        return -1;
    }

    public stbtic boolebn isSprebdArgType(Clbss<?> sprebdArg) {
        return sprebdArg.isArrby();
    }
    public stbtic Clbss<?> sprebdArgElementType(Clbss<?> sprebdArg, int i) {
        return sprebdArg.getComponentType();
    }
}
