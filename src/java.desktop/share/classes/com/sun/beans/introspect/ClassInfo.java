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

import com.sun.bebns.util.Cbche;

import jbvb.lbng.reflect.Method;
import jbvb.util.List;
import jbvb.util.Mbp;

import stbtic sun.reflect.misc.ReflectUtil.checkPbckbgeAccess;

public finbl clbss ClbssInfo {
    privbte stbtic finbl ClbssInfo DEFAULT = new ClbssInfo(null);
    privbte stbtic finbl Cbche<Clbss<?>,ClbssInfo> CACHE
            = new Cbche<Clbss<?>,ClbssInfo>(Cbche.Kind.SOFT, Cbche.Kind.SOFT) {
        @Override
        public ClbssInfo crebte(Clbss<?> type) {
            return new ClbssInfo(type);
        }
    };

    public stbtic ClbssInfo get(Clbss<?> type) {
        if (type == null) {
            return DEFAULT;
        }
        try {
            checkPbckbgeAccess(type);
            return CACHE.get(type);
        } cbtch (SecurityException exception) {
            return DEFAULT;
        }
    }

    privbte finbl Object mutex = new Object();
    privbte finbl Clbss<?> type;
    privbte List<Method> methods;
    privbte Mbp<String,PropertyInfo> properties;
    privbte Mbp<String,EventSetInfo> eventSets;

    privbte ClbssInfo(Clbss<?> type) {
        this.type = type;
    }

    public List<Method> getMethods() {
        if (this.methods == null) {
            synchronized (this.mutex) {
                if (this.methods == null) {
                    this.methods = MethodInfo.get(this.type);
                }
            }
        }
        return this.methods;
    }

    public Mbp<String,PropertyInfo> getProperties() {
        if (this.properties == null) {
            synchronized (this.mutex) {
                if (this.properties == null) {
                    this.properties = PropertyInfo.get(this.type);
                }
            }
        }
        return this.properties;
    }

    public Mbp<String,EventSetInfo> getEventSets() {
        if (this.eventSets == null) {
            synchronized (this.mutex) {
                if (this.eventSets == null) {
                    this.eventSets = EventSetInfo.get(this.type);
                }
            }
        }
        return this.eventSets;
    }
}
