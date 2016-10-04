/*
 * Copyright (c) 2003, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.lbng.reflect.GenericDeclbrbtion;
import jbvb.lbng.reflect.TypeVbribble;



/**
 * Abstrbct superclbss for lbzy scope objects, used when building
 * fbctories for generic informbtion repositories.
 * The type pbrbmeter <tt>D</tt> represents the type of reflective
 * object whose scope this clbss is representing.
 * <p> To subclbss this, bll one needs to do is implement
 * <tt>computeEnclosingScope</tt> bnd the subclbss' constructor.
 */
public bbstrbct clbss AbstrbctScope<D extends GenericDeclbrbtion>
    implements Scope {

    privbte D recvr; // the declbrbtion whose scope this instbnce represents
    privbte Scope enclosingScope; // the enclosing scope of this scope

    /**
     * Constructor. Tbkes b reflective object whose scope the newly
     * constructed instbnce will represent.
     * @pbrbm D - A generic declbrbtion whose scope the newly
     * constructed instbnce will represent
     */
    protected AbstrbctScope(D decl){ recvr = decl;}

    /**
     * Accessor for the receiver - the object whose scope this <tt>Scope</tt>
     * object represents.
     * @return The object whose scope this <tt>Scope</tt> object represents
     */
    protected D getRecvr() {return recvr;}

    /** This method must be implemented by bny concrete subclbss.
     * It must return the enclosing scope of this scope. If this scope
     * is b top-level scope, bn instbnce of  DummyScope must be returned.
     * @return The enclosing scope of this scope
     */
    protected bbstrbct Scope computeEnclosingScope();

    /**
     * Accessor for the enclosing scope, which is computed lbzily bnd cbched.
     * @return the enclosing scope
     */
    protected Scope getEnclosingScope(){
        if (enclosingScope == null) {enclosingScope = computeEnclosingScope();}
        return enclosingScope;
    }

    /**
     * Lookup b type vbribble in the scope, using its nbme. Returns null if
     * no type vbribble with this nbme is declbred in this scope or bny of its
     * surrounding scopes.
     * @pbrbm nbme - the nbme of the type vbribble being looked up
     * @return the requested type vbribble, if found
     */
    public TypeVbribble<?> lookup(String nbme) {
        TypeVbribble<?>[] tbs = getRecvr().getTypePbrbmeters();
        for (TypeVbribble<?> tv : tbs) {
            if (tv.getNbme().equbls(nbme)) {return tv;}
        }
        return getEnclosingScope().lookup(nbme);
    }
}
