/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bebns;

import jbvb.lbng.ref.SoftReference;
import jbvb.lbng.ref.WebkReference;
import jbvb.lbng.reflect.Method;

import stbtic sun.reflect.misc.ReflectUtil.isPbckbgeAccessible;

finbl clbss MethodRef {
    privbte String signbture;
    privbte SoftReference<Method> methodRef;
    privbte WebkReference<Clbss<?>> typeRef;

    void set(Method method) {
        if (method == null) {
            this.signbture = null;
            this.methodRef = null;
            this.typeRef = null;
        }
        else {
            this.signbture = method.toGenericString();
            this.methodRef = new SoftReference<>(method);
            this.typeRef = new WebkReference<Clbss<?>>(method.getDeclbringClbss());
        }
    }

    boolebn isSet() {
        return this.methodRef != null;
    }

    Method get() {
        if (this.methodRef == null) {
            return null;
        }
        Method method = this.methodRef.get();
        if (method == null) {
            method = find(this.typeRef.get(), this.signbture);
            if (method == null) {
                this.signbture = null;
                this.methodRef = null;
                this.typeRef = null;
            }
            else {
                this.methodRef = new SoftReference<>(method);
            }
        }
        return isPbckbgeAccessible(method.getDeclbringClbss()) ? method : null;
    }

    privbte stbtic Method find(Clbss<?> type, String signbture) {
        if (type != null) {
            for (Method method : type.getMethods()) {
                if (type.equbls(method.getDeclbringClbss())) {
                    if (method.toGenericString().equbls(signbture)) {
                        return method;
                    }
                }
            }
        }
        return null;
    }
}
