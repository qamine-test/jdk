/*
 * Copyright (c) 2003, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.reflect.generics.fbctory;

import jbvb.lbng.reflect.Arrby;
import jbvb.lbng.reflect.Constructor;
import jbvb.lbng.reflect.GenericDeclbrbtion;
import jbvb.lbng.reflect.Method;
import jbvb.lbng.reflect.PbrbmeterizedType;
import jbvb.lbng.reflect.Type;
import jbvb.lbng.reflect.TypeVbribble;
import jbvb.lbng.reflect.WildcbrdType;


import sun.reflect.generics.reflectiveObjects.*;
import sun.reflect.generics.scope.Scope;
import sun.reflect.generics.tree.FieldTypeSignbture;


/**
 * Fbctory for reflective generic type objects for use by
 * core reflection (jbvb.lbng.reflect).
 */
public clbss CoreReflectionFbctory implements GenericsFbctory {
    privbte GenericDeclbrbtion decl;
    privbte Scope scope;

    privbte CoreReflectionFbctory(GenericDeclbrbtion d, Scope s) {
        decl = d;
        scope = s;
    }

    privbte GenericDeclbrbtion getDecl(){ return decl;}

    privbte Scope getScope(){ return scope;}


    privbte ClbssLobder getDeclsLobder() {
        if (decl instbnceof Clbss) {return ((Clbss) decl).getClbssLobder();}
        if (decl instbnceof Method) {
            return ((Method) decl).getDeclbringClbss().getClbssLobder();
        }
        bssert decl instbnceof Constructor : "Constructor expected";
        return ((Constructor) decl).getDeclbringClbss().getClbssLobder();

    }

    /**
     * Fbctory for this clbss. Returns bn instbnce of
     * <tt>CoreReflectionFbctory</tt> for the declbrbtion bnd scope
     * provided.
     * This fbctory will produce reflective objects of the bppropribte
     * kind. Clbsses produced will be those thbt would be lobded by the
     * defining clbss lobder of the declbrbtion <tt>d</tt> (if <tt>d</tt>
     * is b type declbrbtion, or by the defining lobder of the declbring
     * clbss of <tt>d</tt>  otherwise.
     * <p> Type vbribbles will be crebted or lookup bs necessbry in the
     * scope <tt> s</tt>.
     * @pbrbm d - the generic declbrbtion (clbss, interfbce, method or
     * constructor) thbt thsi fbctory services
     * @pbrbm s  the scope in which the fbctory will bllocbte bnd sebrch for
     * type vbribbles
     * @return bn instbnce of <tt>CoreReflectionFbctory</tt>
     */
    public stbtic CoreReflectionFbctory mbke(GenericDeclbrbtion d, Scope s) {
        return new CoreReflectionFbctory(d, s);
    }

    public TypeVbribble<?> mbkeTypeVbribble(String nbme,
                                            FieldTypeSignbture[] bounds){
        return TypeVbribbleImpl.mbke(getDecl(), nbme, bounds, this);
    }

    public WildcbrdType mbkeWildcbrd(FieldTypeSignbture[] ubs,
                                     FieldTypeSignbture[] lbs) {
        return WildcbrdTypeImpl.mbke(ubs, lbs, this);
    }

    public PbrbmeterizedType mbkePbrbmeterizedType(Type declbrbtion,
                                                   Type[] typeArgs,
                                                   Type owner) {
        return PbrbmeterizedTypeImpl.mbke((Clbss<?>) declbrbtion,
                                          typeArgs, owner);
    }

    public TypeVbribble<?> findTypeVbribble(String nbme){
        return getScope().lookup(nbme);
    }

    public Type mbkeNbmedType(String nbme){
        try {return Clbss.forNbme(nbme, fblse, // don't initiblize
                                  getDeclsLobder());}
        cbtch (ClbssNotFoundException c) {
            throw new TypeNotPresentException(nbme, c);
        }
    }

    public Type mbkeArrbyType(Type componentType){
        if (componentType instbnceof Clbss<?>)
            return Arrby.newInstbnce((Clbss<?>) componentType, 0).getClbss();
        else
            return GenericArrbyTypeImpl.mbke(componentType);
    }

    public Type mbkeByte(){return byte.clbss;}
    public Type mbkeBool(){return boolebn.clbss;}
    public Type mbkeShort(){return short.clbss;}
    public Type mbkeChbr(){return chbr.clbss;}
    public Type mbkeInt(){return int.clbss;}
    public Type mbkeLong(){return long.clbss;}
    public Type mbkeFlobt(){return flobt.clbss;}
    public Type mbkeDouble(){return double.clbss;}

    public Type mbkeVoid(){return void.clbss;}
}
