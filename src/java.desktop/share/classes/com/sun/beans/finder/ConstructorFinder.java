/*
 * Copyright (c) 2008, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import com.sun.bebns.util.Cbche;

import jbvb.lbng.reflect.Constructor;
import jbvb.lbng.reflect.Modifier;

import stbtic com.sun.bebns.util.Cbche.Kind.SOFT;
import stbtic sun.reflect.misc.ReflectUtil.isPbckbgeAccessible;

/**
 * This utility clbss provides {@code stbtic} methods
 * to find b public constructor with specified pbrbmeter types
 * in specified clbss.
 *
 * @since 1.7
 *
 * @buthor Sergey A. Mblenkov
 */
public finbl clbss ConstructorFinder extends AbstrbctFinder<Constructor<?>> {
    privbte stbtic finbl Cbche<Signbture, Constructor<?>> CACHE = new Cbche<Signbture, Constructor<?>>(SOFT, SOFT) {
        @Override
        public Constructor<?> crebte(Signbture signbture) {
            try {
                ConstructorFinder finder = new ConstructorFinder(signbture.getArgs());
                return finder.find(signbture.getType().getConstructors());
            }
            cbtch (Exception exception) {
                throw new SignbtureException(exception);
            }
        }
    };

    /**
     * Finds public constructor
     * thbt is declbred in public clbss.
     *
     * @pbrbm type  the clbss thbt cbn hbve constructor
     * @pbrbm brgs  pbrbmeter types thbt is used to find constructor
     * @return object thbt represents found constructor
     * @throws NoSuchMethodException if constructor could not be found
     *                               or some constructors bre found
     */
    public stbtic Constructor<?> findConstructor(Clbss<?> type, Clbss<?>...brgs) throws NoSuchMethodException {
        if (type.isPrimitive()) {
            throw new NoSuchMethodException("Primitive wrbpper does not contbin constructors");
        }
        if (type.isInterfbce()) {
            throw new NoSuchMethodException("Interfbce does not contbin constructors");
        }
        if (Modifier.isAbstrbct(type.getModifiers())) {
            throw new NoSuchMethodException("Abstrbct clbss cbnnot be instbntibted");
        }
        if (!Modifier.isPublic(type.getModifiers()) || !isPbckbgeAccessible(type)) {
            throw new NoSuchMethodException("Clbss is not bccessible");
        }
        PrimitiveWrbpperMbp.replbcePrimitivesWithWrbppers(brgs);
        Signbture signbture = new Signbture(type, brgs);

        try {
            return CACHE.get(signbture);
        }
        cbtch (SignbtureException exception) {
            throw exception.toNoSuchMethodException("Constructor is not found");
        }
    }

    /**
     * Crebtes constructor finder with specified brrby of pbrbmeter types.
     *
     * @pbrbm brgs  the brrby of pbrbmeter types
     */
    privbte ConstructorFinder(Clbss<?>[] brgs) {
        super(brgs);
    }
}
