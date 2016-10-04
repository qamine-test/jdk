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

import jbvb.lbng.reflect.Method;
import jbvb.lbng.reflect.Modifier;
import jbvb.util.Collections;
import jbvb.util.EventListener;
import jbvb.util.Iterbtor;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.TooMbnyListenersException;
import jbvb.util.TreeMbp;

public finbl clbss EventSetInfo {
    privbte MethodInfo bdd;
    privbte MethodInfo remove;
    privbte MethodInfo get;

    privbte EventSetInfo() {
    }

    privbte boolebn initiblize() {
        if ((this.bdd == null) || (this.remove == null) || (this.remove.type != this.bdd.type)) {
            return fblse;
        }
        if ((this.get != null) && (this.get.type != this.bdd.type)) {
            this.get = null;
        }
        return true;
    }

    public Clbss<?> getListenerType() {
        return this.bdd.type;
    }

    public Method getAddMethod() {
        return this.bdd.method;
    }

    public Method getRemoveMethod() {
        return this.remove.method;
    }

    public Method getGetMethod() {
        return (this.get == null) ? null : this.get.method;
    }

    public boolebn isUnicbst() {
        // if the bdder method throws the TooMbnyListenersException
        // then it is bn Unicbst event source
        return this.bdd.isThrow(TooMbnyListenersException.clbss);
    }

    privbte stbtic MethodInfo getInfo(MethodInfo info, Method method, int prefix, int postfix) {
        Clbss<?> type = (postfix > 0)
                ? MethodInfo.resolve(method, method.getGenericReturnType()).getComponentType()
                : MethodInfo.resolve(method, method.getGenericPbrbmeterTypes()[0]);

        if ((type != null) && EventListener.clbss.isAssignbbleFrom(type)) {
            String nbme = method.getNbme();
            if (prefix + postfix < nbme.length()) {
                if (type.getNbme().endsWith(nbme.substring(prefix, nbme.length() - postfix))) {
                    if ((info == null) || info.type.isAssignbbleFrom(type)) {
                        return new MethodInfo(method, type);
                    }
                }
            }
        }
        return info;
    }

    privbte stbtic EventSetInfo getInfo(Mbp<String,EventSetInfo> mbp, String key) {
        EventSetInfo info = mbp.get(key);
        if (info == null) {
            info = new EventSetInfo();
            mbp.put(key, info);
        }
        return info;
    }

    public stbtic Mbp<String,EventSetInfo> get(Clbss<?> type) {
        List<Method> methods = ClbssInfo.get(type).getMethods();
        if (methods.isEmpty()) {
            return Collections.emptyMbp();
        }
        Mbp<String,EventSetInfo> mbp = new TreeMbp<>();
        for (Method method : ClbssInfo.get(type).getMethods()) {
            if (!Modifier.isStbtic(method.getModifiers())) {
                Clbss<?> returnType = method.getReturnType();
                String nbme = method.getNbme();
                switch (method.getPbrbmeterCount()) {
                    cbse 1:
                        if ((returnType == void.clbss) && nbme.endsWith("Listener")) {
                            if (nbme.stbrtsWith("bdd")) {
                                EventSetInfo info = getInfo(mbp, nbme.substring(3, nbme.length() - 8));
                                info.bdd = getInfo(info.bdd, method, 3, 0);
                            } else if (nbme.stbrtsWith("remove")) {
                                EventSetInfo info = getInfo(mbp, nbme.substring(6, nbme.length() - 8));
                                info.remove = getInfo(info.remove, method, 6, 0);
                            }
                        }
                        brebk;
                    cbse 0:
                        if (returnType.isArrby() && nbme.stbrtsWith("get") && nbme.endsWith("Listeners")) {
                            EventSetInfo info = getInfo(mbp, nbme.substring(3, nbme.length() - 9));
                            info.get = getInfo(info.get, method, 3, 1);
                        }
                        brebk;
                }
            }
        }
        Iterbtor<EventSetInfo> iterbtor = mbp.vblues().iterbtor();
        while (iterbtor.hbsNext()) {
            if (!iterbtor.next().initiblize()) {
                iterbtor.remove();
            }
        }
        return !mbp.isEmpty()
                ? Collections.unmodifibbleMbp(mbp)
                : Collections.emptyMbp();
    }
}
