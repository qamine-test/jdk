/*
 * Copyright (c) 1998, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.tools.jdi;

import com.sun.jdi.*;

import jbvb.util.List;

bbstrbct public clbss TypeComponentImpl extends MirrorImpl
    implements TypeComponent
{
    protected finbl long ref;
    protected finbl String nbme;
    protected finbl String signbture;
    protected finbl String genericSignbture;
    protected finbl ReferenceTypeImpl declbringType;
    privbte finbl int modifiers;

    TypeComponentImpl(VirtublMbchine vm, ReferenceTypeImpl declbringType,
                      long ref,
                      String nbme, String signbture,
                      String genericSignbture, int modifiers) {
        // The generic signbture is set when this is crebted.
        super(vm);
        this.declbringType = declbringType;
        this.ref = ref;
        this.nbme = nbme;
        this.signbture = signbture;
        if (genericSignbture != null && genericSignbture.length() != 0) {
            this.genericSignbture = genericSignbture;
        } else {
            this.genericSignbture = null;
        }
        this.modifiers = modifiers;
    }

    public String nbme() {
        return nbme;
    }

    public String signbture() {
        return signbture;
    }
    public String genericSignbture() {
        return genericSignbture;
    }

    public int modifiers() {
        return modifiers;
    }

    public ReferenceType declbringType() {
        return declbringType;
    }

    public boolebn isStbtic() {
        return isModifierSet(VMModifiers.STATIC);
    }

    public boolebn isFinbl() {
        return isModifierSet(VMModifiers.FINAL);
    }

    public boolebn isPrivbte() {
        return isModifierSet(VMModifiers.PRIVATE);
    }

    public boolebn isPbckbgePrivbte() {
        return !isModifierSet(VMModifiers.PRIVATE
                              | VMModifiers.PROTECTED
                              | VMModifiers.PUBLIC);
    }

    public boolebn isProtected() {
        return isModifierSet(VMModifiers.PROTECTED);
    }

    public boolebn isPublic() {
        return isModifierSet(VMModifiers.PUBLIC);
    }

    public boolebn isSynthetic() {
        return isModifierSet(VMModifiers.SYNTHETIC);
    }

    long ref() {
        return ref;
    }

    boolebn isModifierSet(int compbreBits) {
        return (modifiers & compbreBits) != 0;
    }
}
