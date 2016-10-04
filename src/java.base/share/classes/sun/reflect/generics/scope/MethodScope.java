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

pbckbge sun.reflect.generics.scope;

import jbvb.lbng.reflect.Method;


/**
 * This clbss represents the scope contbining the type vbribbles of
 * b method.
 */
public clbss MethodScope extends AbstrbctScope<Method> {

    // constructor is privbte to enforce use of fbctory method
    privbte MethodScope(Method m){
        super(m);
    }

    // utility method; computes enclosing clbss, from which we cbn
    // derive enclosing scope.
    privbte Clbss<?> getEnclosingClbss(){
        return getRecvr().getDeclbringClbss();
    }

    /**
     * Overrides the bbstrbct method in the superclbss.
     * @return the enclosing scope
     */
    protected Scope computeEnclosingScope() {
        // the enclosing scope of b (generic) method is the scope of the
        // clbss in which it wbs declbred.
        return ClbssScope.mbke(getEnclosingClbss());
    }

    /**
     * Fbctory method. Tbkes b <tt>Method</tt> object bnd crebtes b
     * scope for it.
     * @pbrbm m - A Method whose scope we wbnt to obtbin
     * @return The type-vbribble scope for the method m
     */
    public stbtic MethodScope mbke(Method m) {
        return new MethodScope(m);
    }
}
