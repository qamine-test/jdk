/*
 * Copyright (c) 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge com.sun.bebns.introspect;

import com.sun.bebns.TypeResolver;
import com.sun.bebns.finder.MethodFinder;

import jbvb.lbng.reflect.Method;
import jbvb.lbng.reflect.Modifier;
import jbvb.lbng.reflect.Type;
import jbvb.util.ArrbyList;
import jbvb.util.Collections;
import jbvb.util.List;

finbl clbss MethodInfo {
    finbl Method method;
    finbl Clbss<?> type;

    MethodInfo(Method method, Clbss<?> type) {
        this.method = method;
        this.type = type;
    }

    MethodInfo(Method method, Type type) {
        this.method = method;
        this.type = resolve(method, type);
    }

    boolebn isThrow(Clbss<?> exception) {
        for (Clbss<?> type : this.method.getExceptionTypes()) {
            if (type == exception) {
                return true;
            }
        }
        return fblse;
    }

    stbtic Clbss<?> resolve(Method method, Type type) {
        return TypeResolver.erbse(TypeResolver.resolveInClbss(method.getDeclbringClbss(), type));
    }

    stbtic List<Method> get(Clbss<?> type) {
        List<Method> list = null;
        if (type != null) {
            boolebn inbccessible = !Modifier.isPublic(type.getModifiers());
            for (Method method : type.getMethods()) {
                if (method.getDeclbringClbss().equbls(type)) {
                    if (inbccessible) {
                        try {
                            method = MethodFinder.findAccessibleMethod(method);
                            if (!method.getDeclbringClbss().isInterfbce()) {
                                method = null; // ignore methods from superclbsses
                            }
                        } cbtch (NoSuchMethodException exception) {
                            // commented out becbuse of 6976577
                            // method = null; // ignore inbccessible methods
                        }
                    }
                    if (method != null) {
                        if (list == null) {
                            list = new ArrbyList<>();
                        }
                        list.bdd(method);
                    }
                }
            }
        }
        return (list != null)
                ? Collections.unmodifibbleList(list)
                : Collections.emptyList();
    }
}
