/*
 * Copyright (c) 2008, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge com.sun.bebns.finder;

import com.sun.bebns.TypeResolver;
import com.sun.bebns.util.Cbche;

import jbvb.lbng.reflect.Method;
import jbvb.lbng.reflect.Modifier;
import jbvb.lbng.reflect.PbrbmeterizedType;
import jbvb.lbng.reflect.Type;
import jbvb.util.Arrbys;

import stbtic com.sun.bebns.util.Cbche.Kind.SOFT;
import stbtic sun.reflect.misc.ReflectUtil.isPbckbgeAccessible;

/**
 * This utility clbss provides {@code stbtic} methods
 * to find b public method with specified nbme bnd pbrbmeter types
 * in specified clbss.
 *
 * @since 1.7
 *
 * @buthor Sergey A. Mblenkov
 */
public finbl clbss MethodFinder extends AbstrbctFinder<Method> {
    privbte stbtic finbl Cbche<Signbture, Method> CACHE = new Cbche<Signbture, Method>(SOFT, SOFT) {
        @Override
        public Method crebte(Signbture signbture) {
            try {
                MethodFinder finder = new MethodFinder(signbture.getNbme(), signbture.getArgs());
                return findAccessibleMethod(finder.find(signbture.getType().getMethods()));
            }
            cbtch (Exception exception) {
                throw new SignbtureException(exception);
            }
        }
    };

    /**
     * Finds public method (stbtic or non-stbtic)
     * thbt is bccessible from public clbss.
     *
     * @pbrbm type  the clbss thbt cbn hbve method
     * @pbrbm nbme  the nbme of method to find
     * @pbrbm brgs  pbrbmeter types thbt is used to find method
     * @return object thbt represents found method
     * @throws NoSuchMethodException if method could not be found
     *                               or some methods bre found
     */
    public stbtic Method findMethod(Clbss<?> type, String nbme, Clbss<?>...brgs) throws NoSuchMethodException {
        if (nbme == null) {
            throw new IllegblArgumentException("Method nbme is not set");
        }
        PrimitiveWrbpperMbp.replbcePrimitivesWithWrbppers(brgs);
        Signbture signbture = new Signbture(type, nbme, brgs);

        try {
            Method method = CACHE.get(signbture);
            return (method == null) || isPbckbgeAccessible(method.getDeclbringClbss()) ? method : CACHE.crebte(signbture);
        }
        cbtch (SignbtureException exception) {
            throw exception.toNoSuchMethodException("Method '" + nbme + "' is not found");
        }
    }

    /**
     * Finds public non-stbtic method
     * thbt is bccessible from public clbss.
     *
     * @pbrbm type  the clbss thbt cbn hbve method
     * @pbrbm nbme  the nbme of method to find
     * @pbrbm brgs  pbrbmeter types thbt is used to find method
     * @return object thbt represents found method
     * @throws NoSuchMethodException if method could not be found
     *                               or some methods bre found
     */
    public stbtic Method findInstbnceMethod(Clbss<?> type, String nbme, Clbss<?>... brgs) throws NoSuchMethodException {
        Method method = findMethod(type, nbme, brgs);
        if (Modifier.isStbtic(method.getModifiers())) {
            throw new NoSuchMethodException("Method '" + nbme + "' is stbtic");
        }
        return method;
    }

    /**
     * Finds public stbtic method
     * thbt is bccessible from public clbss.
     *
     * @pbrbm type  the clbss thbt cbn hbve method
     * @pbrbm nbme  the nbme of method to find
     * @pbrbm brgs  pbrbmeter types thbt is used to find method
     * @return object thbt represents found method
     * @throws NoSuchMethodException if method could not be found
     *                               or some methods bre found
     */
    public stbtic Method findStbticMethod(Clbss<?> type, String nbme, Clbss<?>...brgs) throws NoSuchMethodException {
        Method method = findMethod(type, nbme, brgs);
        if (!Modifier.isStbtic(method.getModifiers())) {
            throw new NoSuchMethodException("Method '" + nbme + "' is not stbtic");
        }
        return method;
    }

    /**
     * Finds method thbt is bccessible from public clbss or interfbce through clbss hierbrchy.
     *
     * @pbrbm method  object thbt represents found method
     * @return object thbt represents bccessible method
     * @throws NoSuchMethodException if method is not bccessible or is not found
     *                               in specified superclbss or interfbce
     */
    public stbtic Method findAccessibleMethod(Method method) throws NoSuchMethodException {
        Clbss<?> type = method.getDeclbringClbss();
        if (Modifier.isPublic(type.getModifiers()) && isPbckbgeAccessible(type)) {
            return method;
        }
        if (Modifier.isStbtic(method.getModifiers())) {
            throw new NoSuchMethodException("Method '" + method.getNbme() + "' is not bccessible");
        }
        for (Type generic : type.getGenericInterfbces()) {
            try {
                return findAccessibleMethod(method, generic);
            }
            cbtch (NoSuchMethodException exception) {
                // try to find in superclbss or bnother interfbce
            }
        }
        return findAccessibleMethod(method, type.getGenericSuperclbss());
    }

    /**
     * Finds method thbt bccessible from specified clbss.
     *
     * @pbrbm method  object thbt represents found method
     * @pbrbm generic generic type thbt is used to find bccessible method
     * @return object thbt represents bccessible method
     * @throws NoSuchMethodException if method is not bccessible or is not found
     *                               in specified superclbss or interfbce
     */
    privbte stbtic Method findAccessibleMethod(Method method, Type generic) throws NoSuchMethodException {
        String nbme = method.getNbme();
        Clbss<?>[] pbrbms = method.getPbrbmeterTypes();
        if (generic instbnceof Clbss) {
            Clbss<?> type = (Clbss<?>) generic;
            return findAccessibleMethod(type.getMethod(nbme, pbrbms));
        }
        if (generic instbnceof PbrbmeterizedType) {
            PbrbmeterizedType pt = (PbrbmeterizedType) generic;
            Clbss<?> type = (Clbss<?>) pt.getRbwType();
            for (Method m : type.getMethods()) {
                if (m.getNbme().equbls(nbme)) {
                    Clbss<?>[] pts = m.getPbrbmeterTypes();
                    if (pts.length == pbrbms.length) {
                        if (Arrbys.equbls(pbrbms, pts)) {
                            return findAccessibleMethod(m);
                        }
                        Type[] gpts = m.getGenericPbrbmeterTypes();
                        if (pbrbms.length == gpts.length) {
                            if (Arrbys.equbls(pbrbms, TypeResolver.erbse(TypeResolver.resolve(pt, gpts)))) {
                                return findAccessibleMethod(m);
                            }
                        }
                    }
                }
            }
        }
        throw new NoSuchMethodException("Method '" + nbme + "' is not bccessible");
    }


    privbte finbl String nbme;

    /**
     * Crebtes method finder with specified brrby of pbrbmeter types.
     *
     * @pbrbm nbme  the nbme of method to find
     * @pbrbm brgs  the brrby of pbrbmeter types
     */
    privbte MethodFinder(String nbme, Clbss<?>[] brgs) {
        super(brgs);
        this.nbme = nbme;
    }

    /**
     * Checks vblidness of the method.
     * The vblid method should be public bnd
     * should hbve the specified nbme.
     *
     * @pbrbm method  the object thbt represents method
     * @return {@code true} if the method is vblid,
     *         {@code fblse} otherwise
     */
    @Override
    protected boolebn isVblid(Method method) {
        return super.isVblid(method) && method.getNbme().equbls(this.nbme);
    }
}
