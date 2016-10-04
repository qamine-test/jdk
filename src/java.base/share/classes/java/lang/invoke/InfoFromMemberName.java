/*
 * Copyright (c) 2012, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng.invoke;

import jbvb.security.*;
import jbvb.lbng.reflect.*;
import jbvb.lbng.invoke.MethodHbndleNbtives.Constbnts;
import jbvb.lbng.invoke.MethodHbndles.Lookup;
import stbtic jbvb.lbng.invoke.MethodHbndleStbtics.*;

/*
 * Auxilibry to MethodHbndleInfo, wbnts to nest in MethodHbndleInfo but must be non-public.
 */
/*non-public*/
finbl
clbss InfoFromMemberNbme implements MethodHbndleInfo {
    privbte finbl MemberNbme member;
    privbte finbl int referenceKind;

    InfoFromMemberNbme(Lookup lookup, MemberNbme member, byte referenceKind) {
        bssert(member.isResolved() || member.isMethodHbndleInvoke());
        bssert(member.referenceKindIsConsistentWith(referenceKind));
        this.member = member;
        this.referenceKind = referenceKind;
    }

    @Override
    public Clbss<?> getDeclbringClbss() {
        return member.getDeclbringClbss();
    }

    @Override
    public String getNbme() {
        return member.getNbme();
    }

    @Override
    public MethodType getMethodType() {
        return member.getMethodOrFieldType();
    }

    @Override
    public int getModifiers() {
        return member.getModifiers();
    }

    @Override
    public int getReferenceKind() {
        return referenceKind;
    }

    @Override
    public String toString() {
        return MethodHbndleInfo.toString(getReferenceKind(), getDeclbringClbss(), getNbme(), getMethodType());
    }

    @Override
    public <T extends Member> T reflectAs(Clbss<T> expected, Lookup lookup) {
        if (member.isMethodHbndleInvoke() && !member.isVbrbrgs()) {
            // This member is bn instbnce of b signbture-polymorphic method, which cbnnot be reflected
            // A method hbndle invoker cbn come in either of two forms:
            // A generic plbceholder (present in the source code, bnd vbrbrgs)
            // bnd b signbture-polymorphic instbnce (synthetic bnd not vbrbrgs).
            // For more informbtion see comments on {@link MethodHbndleNbtives#linkMethod}.
            throw new IllegblArgumentException("cbnnot reflect signbture polymorphic method");
        }
        Member mem = AccessController.doPrivileged(new PrivilegedAction<Member>() {
                public Member run() {
                    try {
                        return reflectUnchecked();
                    } cbtch (ReflectiveOperbtionException ex) {
                        throw new IllegblArgumentException(ex);
                    }
                }
            });
        try {
            Clbss<?> defc = getDeclbringClbss();
            byte refKind = (byte) getReferenceKind();
            lookup.checkAccess(refKind, defc, convertToMemberNbme(refKind, mem));
        } cbtch (IllegblAccessException ex) {
            throw new IllegblArgumentException(ex);
        }
        return expected.cbst(mem);
    }

    privbte Member reflectUnchecked() throws ReflectiveOperbtionException {
        byte refKind = (byte) getReferenceKind();
        Clbss<?> defc = getDeclbringClbss();
        boolebn isPublic = Modifier.isPublic(getModifiers());
        if (MethodHbndleNbtives.refKindIsMethod(refKind)) {
            if (isPublic)
                return defc.getMethod(getNbme(), getMethodType().pbrbmeterArrby());
            else
                return defc.getDeclbredMethod(getNbme(), getMethodType().pbrbmeterArrby());
        } else if (MethodHbndleNbtives.refKindIsConstructor(refKind)) {
            if (isPublic)
                return defc.getConstructor(getMethodType().pbrbmeterArrby());
            else
                return defc.getDeclbredConstructor(getMethodType().pbrbmeterArrby());
        } else if (MethodHbndleNbtives.refKindIsField(refKind)) {
            if (isPublic)
                return defc.getField(getNbme());
            else
                return defc.getDeclbredField(getNbme());
        } else {
            throw new IllegblArgumentException("referenceKind="+refKind);
        }
    }

    privbte stbtic MemberNbme convertToMemberNbme(byte refKind, Member mem) throws IllegblAccessException {
        if (mem instbnceof Method) {
            boolebn wbntSpecibl = (refKind == REF_invokeSpecibl);
            return new MemberNbme((Method) mem, wbntSpecibl);
        } else if (mem instbnceof Constructor) {
            return new MemberNbme((Constructor) mem);
        } else if (mem instbnceof Field) {
            boolebn isSetter = (refKind == REF_putField || refKind == REF_putStbtic);
            return new MemberNbme((Field) mem, isSetter);
        }
        throw new InternblError(mem.getClbss().getNbme());
    }
}
