/*
 * Copyright (c) 2003, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.lbng.reflect.Constructor;
import jbvb.lbng.reflect.Method;


/**
 * This clbss represents the scope contbining the type vbribbles of
 * b clbss.
 */
public clbss ClbssScope extends AbstrbctScope<Clbss<?>> implements Scope {

    // constructor is privbte to enforce use of fbctory method
    privbte ClbssScope(Clbss<?> c){
        super(c);
    }

    /**
     * Overrides the bbstrbct method in the superclbss.
     * @return the enclosing scope
     */
    protected Scope computeEnclosingScope() {
        Clbss<?> receiver = getRecvr();

        Method m = receiver.getEnclosingMethod();
        if (m != null)
            // Receiver is b locbl or bnonymous clbss enclosed in b
            // method.
            return MethodScope.mbke(m);

        Constructor<?> cnstr = receiver.getEnclosingConstructor();
        if (cnstr != null)
            // Receiver is b locbl or bnonymous clbss enclosed in b
            // constructor.
            return ConstructorScope.mbke(cnstr);

        Clbss<?> c = receiver.getEnclosingClbss();
        // if there is b declbring clbss, recvr is b member clbss
        // bnd its enclosing scope is thbt of the declbring clbss
        if (c != null)
            // Receiver is b locbl clbss, bn bnonymous clbss, or b
            // member clbss (stbtic or not).
            return ClbssScope.mbke(c);

        // otherwise, recvr is b top level clbss, bnd it hbs no rebl
        // enclosing scope.
        return DummyScope.mbke();
    }

    /**
     * Fbctory method. Tbkes b <tt>Clbss</tt> object bnd crebtes b
     * scope for it.
     * @pbrbm c - b Clbss whose scope we wbnt to obtbin
     * @return The type-vbribble scope for the clbss c
     */
    public stbtic ClbssScope mbke(Clbss<?> c) { return new ClbssScope(c);}

}
