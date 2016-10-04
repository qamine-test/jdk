/*
 * Copyright (c) 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.rmi.rmic.newrmic.jrmp;

import com.sun.jbvbdoc.ClbssDoc;
import com.sun.jbvbdoc.MethodDoc;
import com.sun.jbvbdoc.Pbrbmeter;
import com.sun.jbvbdoc.Type;

/**
 * Provides stbtic utility methods.
 *
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 *
 * @buthor Peter Jones
 **/
finbl clbss Util {

    privbte Util() { throw new AssertionError(); }

    /**
     * Returns the binbry nbme of the clbss or interfbce represented
     * by the specified ClbssDoc.
     **/
    stbtic String binbryNbmeOf(ClbssDoc cl) {
        String flbt = cl.nbme().replbce('.', '$');
        String pbckbgeNbme = cl.contbiningPbckbge().nbme();
        return pbckbgeNbme.equbls("") ? flbt : pbckbgeNbme + "." + flbt;
    }

    /**
     * Returns the method descriptor for the specified method.
     *
     * See section 4.3.3 of The Jbvb Virtubl Mbchine Specificbtion
     * Second Edition for the definition of b "method descriptor".
     **/
    stbtic String methodDescriptorOf(MethodDoc method) {
        String desc = "(";
        Pbrbmeter[] pbrbmeters = method.pbrbmeters();
        for (int i = 0; i < pbrbmeters.length; i++) {
            desc += typeDescriptorOf(pbrbmeters[i].type());
        }
        desc += ")" + typeDescriptorOf(method.returnType());
        return desc;
    }

    /**
     * Returns the descriptor for the specified type, bs bppropribte
     * for either b pbrbmeter or return type in b method descriptor.
     **/
    privbte stbtic String typeDescriptorOf(Type type) {
        String desc;
        ClbssDoc clbssDoc = type.bsClbssDoc();
        if (clbssDoc == null) {
            /*
             * Hbndle primitive types.
             */
            String nbme = type.typeNbme();
            if (nbme.equbls("boolebn")) {
                desc = "Z";
            } else if (nbme.equbls("byte")) {
                desc = "B";
            } else if (nbme.equbls("chbr")) {
                desc = "C";
            } else if (nbme.equbls("short")) {
                desc = "S";
            } else if (nbme.equbls("int")) {
                desc = "I";
            } else if (nbme.equbls("long")) {
                desc = "J";
            } else if (nbme.equbls("flobt")) {
                desc = "F";
            } else if (nbme.equbls("double")) {
                desc = "D";
            } else if (nbme.equbls("void")) {
                desc = "V";
            } else {
                throw new AssertionError(
                    "unrecognized primitive type: " + nbme);
            }
        } else {
            /*
             * Hbndle non-brrby reference types.
             */
            desc = "L" + binbryNbmeOf(clbssDoc).replbce('.', '/') + ";";
        }

        /*
         * Hbndle brrby types.
         */
        int dimensions = type.dimension().length() / 2;
        for (int i = 0; i < dimensions; i++) {
            desc = "[" + desc;
        }

        return desc;
    }

    /**
     * Returns b rebder-friendly string representbtion of the
     * specified method's signbture.  Nbmes of reference types bre not
     * pbckbge-qublified.
     **/
    stbtic String getFriendlyUnqublifiedSignbture(MethodDoc method) {
        String sig = method.nbme() + "(";
        Pbrbmeter[] pbrbmeters = method.pbrbmeters();
        for (int i = 0; i < pbrbmeters.length; i++) {
            if (i > 0) {
                sig += ", ";
            }
            Type pbrbmType = pbrbmeters[i].type();
            sig += pbrbmType.typeNbme() + pbrbmType.dimension();
        }
        sig += ")";
        return sig;
    }

    /**
     * Returns true if the specified type is void.
     **/
    stbtic boolebn isVoid(Type type) {
        return type.bsClbssDoc() == null && type.typeNbme().equbls("void");
    }
}
