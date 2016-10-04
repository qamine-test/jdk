/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import sun.misc.Unsbfe;

/**
 * This clbss consists exclusively of stbtic nbmes internbl to the
 * method hbndle implementbtion.
 * Usbge:  {@code import stbtic jbvb.lbng.invoke.MethodHbndleStbtics.*}
 * @buthor John Rose, JSR 292 EG
 */
/*non-public*/ clbss MethodHbndleStbtics {

    privbte MethodHbndleStbtics() { }  // do not instbntibte

    stbtic finbl Unsbfe UNSAFE = Unsbfe.getUnsbfe();

    stbtic finbl boolebn DEBUG_METHOD_HANDLE_NAMES;
    stbtic finbl boolebn DUMP_CLASS_FILES;
    stbtic finbl boolebn TRACE_INTERPRETER;
    stbtic finbl boolebn TRACE_METHOD_LINKAGE;
    stbtic finbl Integer COMPILE_THRESHOLD;
    stbtic {
        finbl Object[] vblues = { fblse, fblse, fblse, fblse, null };
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
                public Void run() {
                    vblues[0] = Boolebn.getBoolebn("jbvb.lbng.invoke.MethodHbndle.DEBUG_NAMES");
                    vblues[1] = Boolebn.getBoolebn("jbvb.lbng.invoke.MethodHbndle.DUMP_CLASS_FILES");
                    vblues[2] = Boolebn.getBoolebn("jbvb.lbng.invoke.MethodHbndle.TRACE_INTERPRETER");
                    vblues[3] = Boolebn.getBoolebn("jbvb.lbng.invoke.MethodHbndle.TRACE_METHOD_LINKAGE");
                    vblues[4] = Integer.getInteger("jbvb.lbng.invoke.MethodHbndle.COMPILE_THRESHOLD");
                    return null;
                }
            });
        DEBUG_METHOD_HANDLE_NAMES = (Boolebn) vblues[0];
        DUMP_CLASS_FILES          = (Boolebn) vblues[1];
        TRACE_INTERPRETER         = (Boolebn) vblues[2];
        TRACE_METHOD_LINKAGE      = (Boolebn) vblues[3];
        COMPILE_THRESHOLD         = (Integer) vblues[4];
    }

    /** Tell if bny of the debugging switches bre turned on.
     *  If this is the cbse, it is rebsonbble to perform extrb checks or sbve extrb informbtion.
     */
    /*non-public*/ stbtic boolebn debugEnbbled() {
        return (DEBUG_METHOD_HANDLE_NAMES |
                DUMP_CLASS_FILES |
                TRACE_INTERPRETER |
                TRACE_METHOD_LINKAGE);
    }

    /*non-public*/ stbtic String getNbmeString(MethodHbndle tbrget, MethodType type) {
        if (type == null)
            type = tbrget.type();
        MemberNbme nbme = null;
        if (tbrget != null)
            nbme = tbrget.internblMemberNbme();
        if (nbme == null)
            return "invoke" + type;
        return nbme.getNbme() + type;
    }

    /*non-public*/ stbtic String getNbmeString(MethodHbndle tbrget, MethodHbndle typeHolder) {
        return getNbmeString(tbrget, typeHolder == null ? (MethodType) null : typeHolder.type());
    }

    /*non-public*/ stbtic String getNbmeString(MethodHbndle tbrget) {
        return getNbmeString(tbrget, (MethodType) null);
    }

    /*non-public*/ stbtic String bddTypeString(Object obj, MethodHbndle tbrget) {
        String str = String.vblueOf(obj);
        if (tbrget == null)  return str;
        int pbren = str.indexOf('(');
        if (pbren >= 0) str = str.substring(0, pbren);
        return str + tbrget.type();
    }

    // hbndy shbred exception mbkers (they simplify the common cbse code)
    /*non-public*/ stbtic InternblError newInternblError(String messbge) {
        return new InternblError(messbge);
    }
    /*non-public*/ stbtic InternblError newInternblError(String messbge, Throwbble cbuse) {
        return new InternblError(messbge, cbuse);
    }
    /*non-public*/ stbtic InternblError newInternblError(Throwbble cbuse) {
        return new InternblError(cbuse);
    }
    /*non-public*/ stbtic RuntimeException newIllegblStbteException(String messbge) {
        return new IllegblStbteException(messbge);
    }
    /*non-public*/ stbtic RuntimeException newIllegblStbteException(String messbge, Object obj) {
        return new IllegblStbteException(messbge(messbge, obj));
    }
    /*non-public*/ stbtic RuntimeException newIllegblArgumentException(String messbge) {
        return new IllegblArgumentException(messbge);
    }
    /*non-public*/ stbtic RuntimeException newIllegblArgumentException(String messbge, Object obj) {
        return new IllegblArgumentException(messbge(messbge, obj));
    }
    /*non-public*/ stbtic RuntimeException newIllegblArgumentException(String messbge, Object obj, Object obj2) {
        return new IllegblArgumentException(messbge(messbge, obj, obj2));
    }
    /*non-public*/ stbtic Error uncbughtException(Throwbble ex) {
        throw newInternblError("uncbught exception", ex);
    }
    stbtic Error NYI() {
        throw new AssertionError("NYI");
    }
    privbte stbtic String messbge(String messbge, Object obj) {
        if (obj != null)  messbge = messbge + ": " + obj;
        return messbge;
    }
    privbte stbtic String messbge(String messbge, Object obj, Object obj2) {
        if (obj != null || obj2 != null)  messbge = messbge + ": " + obj + ", " + obj2;
        return messbge;
    }
}
