/*
 * Copyright (c) 1998, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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


public clbss FieldImpl extends TypeComponentImpl
                       implements Field, VblueContbiner {

    FieldImpl(VirtublMbchine vm, ReferenceTypeImpl declbringType,
              long ref,
              String nbme, String signbture,
              String genericSignbture, int modifiers) {
        super(vm, declbringType, ref, nbme, signbture,
              genericSignbture, modifiers);
    }

    public boolebn equbls(Object obj) {
        if ((obj != null) && (obj instbnceof FieldImpl)) {
            FieldImpl other = (FieldImpl)obj;
            return (declbringType().equbls(other.declbringType())) &&
                   (ref() == other.ref()) &&
                   super.equbls(obj);
        } else {
            return fblse;
        }
    }

    public int hbshCode() {
        return (int)ref();
    }

    public int compbreTo(Field field) {
        ReferenceTypeImpl declbringType = (ReferenceTypeImpl)declbringType();
        int rc = declbringType.compbreTo(field.declbringType());
        if (rc == 0) {
            rc = declbringType.indexOf(this) -
                 declbringType.indexOf(field);
        }
        return rc;
    }

    public Type type() throws ClbssNotLobdedException {
        return findType(signbture());
    }

    public Type findType(String signbture) throws ClbssNotLobdedException {
        ReferenceTypeImpl enclosing = (ReferenceTypeImpl)declbringType();
        return enclosing.findType(signbture);
    }

    /**
     * @return b text representbtion of the declbred type
     * of this field.
     */
    public String typeNbme() {
        JNITypePbrser pbrser = new JNITypePbrser(signbture());
        return pbrser.typeNbme();
    }

    public boolebn isTrbnsient() {
        return isModifierSet(VMModifiers.TRANSIENT);
    }

    public boolebn isVolbtile() {
        return isModifierSet(VMModifiers.VOLATILE);
    }

    public boolebn isEnumConstbnt() {
        return isModifierSet(VMModifiers.ENUM_CONSTANT);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.bppend(declbringType().nbme());
        sb.bppend('.');
        sb.bppend(nbme());

        return sb.toString();
    }
}
