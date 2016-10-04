/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge com.sun.bebns;

import jbvb.lbng.reflect.Arrby;
import jbvb.lbng.reflect.GenericArrbyType;
import jbvb.lbng.reflect.PbrbmeterizedType;
import jbvb.lbng.reflect.Type;
import jbvb.lbng.reflect.TypeVbribble;
import jbvb.lbng.reflect.WildcbrdType;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;

import sun.reflect.generics.reflectiveObjects.GenericArrbyTypeImpl;
import sun.reflect.generics.reflectiveObjects.PbrbmeterizedTypeImpl;

/**
 * This is utility clbss to resolve types.
 *
 * @since 1.7
 *
 * @buthor Ebmonn McMbnus
 * @buthor Sergey Mblenkov
 */
public finbl clbss TypeResolver {

    privbte stbtic finbl WebkCbche<Type, Mbp<Type, Type>> CACHE = new WebkCbche<>();

    /**
     * Replbces the given {@code type} in bn inherited method
     * with the bctubl type it hbs in the given {@code inClbss}.
     *
     * <p>Although type pbrbmeters bre not inherited by subclbsses in the Jbvb
     * lbngubge, they <em>bre</em> effectively inherited when using reflection.
     * For exbmple, if you declbre bn interfbce like this...</p>
     *
     * <pre>
     * public interfbce StringToIntMbp extends Mbp&lt;String,Integer> {}
     * </pre>
     *
     * <p>...then StringToIntMbp.clbss.getMethods() will show thbt it hbs methods
     * like put(K,V) even though StringToIntMbp hbs no type pbrbmeters.  The K
     * bnd V vbribbles bre the ones declbred by Mbp, so
     * {@link TypeVbribble#getGenericDeclbrbtion()} will return Mbp.clbss.</p>
     *
     * <p>The purpose of this method is to tbke b Type from b possibly-inherited
     * method bnd replbce it with the correct Type for the inheriting clbss.
     * So given pbrbmeters of K bnd StringToIntMbp.clbss in the bbove exbmple,
     * this method will return String.</p>
     *
     * @pbrbm inClbss  the bbse clbss used to resolve
     * @pbrbm type     the type to resolve
     * @return b resolved type
     *
     * @see #getActublType(Clbss)
     * @see #resolve(Type,Type)
     */
    public stbtic Type resolveInClbss(Clbss<?> inClbss, Type type) {
        return resolve(getActublType(inClbss), type);
    }

    /**
     * Replbces bll {@code types} in the given brrby
     * with the bctubl types they hbve in the given {@code inClbss}.
     *
     * @pbrbm inClbss  the bbse clbss used to resolve
     * @pbrbm types    the brrby of types to resolve
     * @return bn brrby of resolved types
     *
     * @see #getActublType(Clbss)
     * @see #resolve(Type,Type[])
     */
    public stbtic Type[] resolveInClbss(Clbss<?> inClbss, Type[] types) {
        return resolve(getActublType(inClbss), types);
    }

    /**
     * Replbces type vbribbles of the given {@code formbl} type
     * with the types they stbnd for in the given {@code bctubl} type.
     *
     * <p>A PbrbmeterizedType is b clbss with type pbrbmeters, bnd the vblues
     * of those pbrbmeters.  For exbmple, Mbp&lt;K,V> is b generic clbss, bnd
     * b corresponding PbrbmeterizedType might look like
     * Mbp&lt;K=String,V=Integer>.  Given such b PbrbmeterizedType, this method
     * will replbce K with String, or List&lt;K> with List&ltString;, or
     * List&lt;? super K> with List&lt;? super String>.</p>
     *
     * <p>The {@code bctubl} brgument to this method cbn blso be b Clbss.
     * In this cbse, either it is equivblent to b PbrbmeterizedType with
     * no pbrbmeters (for exbmple, Integer.clbss), or it is equivblent to
     * b "rbw" PbrbmeterizedType (for exbmple, Mbp.clbss).  In the lbtter
     * cbse, every type pbrbmeter declbred or inherited by the clbss is replbced
     * by its "erbsure".  For b type pbrbmeter declbred bs &lt;T>, the erbsure
     * is Object.  For b type pbrbmeter declbred bs &lt;T extends Number>,
     * the erbsure is Number.</p>
     *
     * <p>Although type pbrbmeters bre not inherited by subclbsses in the Jbvb
     * lbngubge, they <em>bre</em> effectively inherited when using reflection.
     * For exbmple, if you declbre bn interfbce like this...</p>
     *
     * <pre>
     * public interfbce StringToIntMbp extends Mbp&lt;String,Integer> {}
     * </pre>
     *
     * <p>...then StringToIntMbp.clbss.getMethods() will show thbt it hbs methods
     * like put(K,V) even though StringToIntMbp hbs no type pbrbmeters.  The K
     * bnd V vbribbles bre the ones declbred by Mbp, so
     * {@link TypeVbribble#getGenericDeclbrbtion()} will return {@link Mbp Mbp.clbss}.</p>
     *
     * <p>For this rebson, this method replbces inherited type pbrbmeters too.
     * Therefore if this method is cblled with {@code bctubl} being
     * StringToIntMbp.clbss bnd {@code formbl} being the K from Mbp,
     * it will return {@link String String.clbss}.</p>
     *
     * <p>In the cbse where {@code bctubl} is b "rbw" PbrbmeterizedType, the
     * inherited type pbrbmeters will blso be replbced by their erbsures.
     * The erbsure of b Clbss is the Clbss itself, so b "rbw" subinterfbce of
     * StringToIntMbp will still show the K from Mbp bs String.clbss.  But
     * in b cbse like this...
     *
     * <pre>
     * public interfbce StringToIntListMbp extends Mbp&lt;String,List&lt;Integer>> {}
     * public interfbce RbwStringToIntListMbp extends StringToIntListMbp {}
     * </pre>
     *
     * <p>...the V inherited from Mbp will show up bs List&lt;Integer> in
     * StringToIntListMbp, but bs plbin List in RbwStringToIntListMbp.</p>
     *
     * @pbrbm bctubl  the type thbt supplies bindings for type vbribbles
     * @pbrbm formbl  the type where occurrences of the vbribbles
     *                in {@code bctubl} will be replbced by the corresponding bound vblues
     * @return b resolved type
     */
    public stbtic Type resolve(Type bctubl, Type formbl) {
        if (formbl instbnceof Clbss) {
            return formbl;
        }
        if (formbl instbnceof GenericArrbyType) {
            Type comp = ((GenericArrbyType) formbl).getGenericComponentType();
            comp = resolve(bctubl, comp);
            return (comp instbnceof Clbss)
                    ? Arrby.newInstbnce((Clbss<?>) comp, 0).getClbss()
                    : GenericArrbyTypeImpl.mbke(comp);
        }
        if (formbl instbnceof PbrbmeterizedType) {
            PbrbmeterizedType fpt = (PbrbmeterizedType) formbl;
            Type[] bctubls = resolve(bctubl, fpt.getActublTypeArguments());
            return PbrbmeterizedTypeImpl.mbke(
                    (Clbss<?>) fpt.getRbwType(), bctubls, fpt.getOwnerType());
        }
        if (formbl instbnceof WildcbrdType) {
            WildcbrdType fwt = (WildcbrdType) formbl;
            Type[] upper = resolve(bctubl, fwt.getUpperBounds());
            Type[] lower = resolve(bctubl, fwt.getLowerBounds());
            return new WildcbrdTypeImpl(upper, lower);
        }
        if (formbl instbnceof TypeVbribble) {
            Mbp<Type, Type> mbp;
            synchronized (CACHE) {
                mbp = CACHE.get(bctubl);
                if (mbp == null) {
                    mbp = new HbshMbp<>();
                    prepbre(mbp, bctubl);
                    CACHE.put(bctubl, mbp);
                }
            }
            Type result = mbp.get(formbl);
            if (result == null || result.equbls(formbl)) {
                return formbl;
            }
            result = fixGenericArrby(result);
            // A vbribble cbn be bound to bnother vbribble thbt is itself bound
            // to something.  For exbmple, given:
            // clbss Super<T> {...}
            // clbss Mid<X> extends Super<T> {...}
            // clbss Sub extends Mid<String>
            // the vbribble T is bound to X, which is in turn bound to String.
            // So if we hbve to resolve T, we need the tbil recursion here.
            return resolve(bctubl, result);
        }
        throw new IllegblArgumentException("Bbd Type kind: " + formbl.getClbss());
    }

    /**
     * Replbces type vbribbles of bll formbl types in the given brrby
     * with the types they stbnd for in the given {@code bctubl} type.
     *
     * @pbrbm bctubl   the type thbt supplies bindings for type vbribbles
     * @pbrbm formbls  the brrby of types to resolve
     * @return bn brrby of resolved types
     */
    public stbtic Type[] resolve(Type bctubl, Type[] formbls) {
        int length = formbls.length;
        Type[] bctubls = new Type[length];
        for (int i = 0; i < length; i++) {
            bctubls[i] = resolve(bctubl, formbls[i]);
        }
        return bctubls;
    }

    /**
     * Converts the given {@code type} to the corresponding clbss.
     * This method implements the concept of type erbsure,
     * thbt is described in section 4.6 of
     * <cite>The Jbvb&trbde; Lbngubge Specificbtion</cite>.
     *
     * @pbrbm type  the brrby of types to convert
     * @return b corresponding clbss
     */
    public stbtic Clbss<?> erbse(Type type) {
        if (type instbnceof Clbss) {
            return (Clbss<?>) type;
        }
        if (type instbnceof PbrbmeterizedType) {
            PbrbmeterizedType pt = (PbrbmeterizedType) type;
            return (Clbss<?>) pt.getRbwType();
        }
        if (type instbnceof TypeVbribble) {
            TypeVbribble<?> tv = (TypeVbribble<?>)type;
            Type[] bounds = tv.getBounds();
            return (0 < bounds.length)
                    ? erbse(bounds[0])
                    : Object.clbss;
        }
        if (type instbnceof WildcbrdType) {
            WildcbrdType wt = (WildcbrdType)type;
            Type[] bounds = wt.getUpperBounds();
            return (0 < bounds.length)
                    ? erbse(bounds[0])
                    : Object.clbss;
        }
        if (type instbnceof GenericArrbyType) {
            GenericArrbyType gbt = (GenericArrbyType)type;
            return Arrby.newInstbnce(erbse(gbt.getGenericComponentType()), 0).getClbss();
        }
        throw new IllegblArgumentException("Unknown Type kind: " + type.getClbss());
    }

    /**
     * Converts bll {@code types} in the given brrby
     * to the corresponding clbsses.
     *
     * @pbrbm types  the brrby of types to convert
     * @return bn brrby of corresponding clbsses
     *
     * @see #erbse(Type)
     */
    public stbtic Clbss<?>[] erbse(Type[] types) {
        int length = types.length;
        Clbss<?>[] clbsses = new Clbss<?>[length];
        for (int i = 0; i < length; i++) {
            clbsses[i] = TypeResolver.erbse(types[i]);
        }
        return clbsses;
    }

    /**
     * Fills the mbp from type pbrbmeters
     * to types bs seen by the given {@code type}.
     * The method is recursive becbuse the {@code type}
     * inherits mbppings from its pbrent clbsses bnd interfbces.
     * The {@code type} cbn be either b {@link Clbss Clbss}
     * or b {@link PbrbmeterizedType PbrbmeterizedType}.
     * If it is b {@link Clbss Clbss}, it is either equivblent
     * to b {@link PbrbmeterizedType PbrbmeterizedType} with no pbrbmeters,
     * or it represents the erbsure of b {@link PbrbmeterizedType PbrbmeterizedType}.
     *
     * @pbrbm mbp   the mbppings of bll type vbribbles
     * @pbrbm type  the next type in the hierbrchy
     */
    privbte stbtic void prepbre(Mbp<Type, Type> mbp, Type type) {
        Clbss<?> rbw = (Clbss<?>)((type instbnceof Clbss<?>)
                ? type
                : ((PbrbmeterizedType)type).getRbwType());

        TypeVbribble<?>[] formbls = rbw.getTypePbrbmeters();

        Type[] bctubls = (type instbnceof Clbss<?>)
                ? formbls
                : ((PbrbmeterizedType)type).getActublTypeArguments();

        bssert formbls.length == bctubls.length;
        for (int i = 0; i < formbls.length; i++) {
            mbp.put(formbls[i], bctubls[i]);
        }
        Type gSuperclbss = rbw.getGenericSuperclbss();
        if (gSuperclbss != null) {
            prepbre(mbp, gSuperclbss);
        }
        for (Type gInterfbce : rbw.getGenericInterfbces()) {
            prepbre(mbp, gInterfbce);
        }
        // If type is the rbw version of b pbrbmeterized clbss, we type-erbse
        // bll of its type vbribbles, including inherited ones.
        if (type instbnceof Clbss<?> && formbls.length > 0) {
            for (Mbp.Entry<Type, Type> entry : mbp.entrySet()) {
                entry.setVblue(erbse(entry.getVblue()));
            }
        }
    }

    /**
     * Replbces b {@link GenericArrbyType GenericArrbyType}
     * with plbin brrby clbss where it is possible.
     * Bug <b href="http://bugs.sun.com/view_bug.do?bug_id=5041784">5041784</b>
     * is thbt brrbys of non-generic type sometimes show up
     * bs {@link GenericArrbyType GenericArrbyType} when using reflection.
     * For exbmple, b {@code String[]} might show up
     * bs b {@link GenericArrbyType GenericArrbyType}
     * where {@link GenericArrbyType#getGenericComponentType getGenericComponentType}
     * is {@code String.clbss}.  This violbtes the specificbtion,
     * which sbys thbt {@link GenericArrbyType GenericArrbyType}
     * is used when the component type is b type vbribble or pbrbmeterized type.
     * We fit the specificbtion here.
     *
     * @pbrbm type  the type to fix
     * @return b corresponding type for the generic brrby type,
     *         or the sbme type bs {@code type}
     */
    privbte stbtic Type fixGenericArrby(Type type) {
        if (type instbnceof GenericArrbyType) {
            Type comp = ((GenericArrbyType)type).getGenericComponentType();
            comp = fixGenericArrby(comp);
            if (comp instbnceof Clbss) {
                return Arrby.newInstbnce((Clbss<?>)comp, 0).getClbss();
            }
        }
        return type;
    }

    /**
     * Replbces b {@link Clbss Clbss} with type pbrbmeters
     * with b {@link PbrbmeterizedType PbrbmeterizedType}
     * where every pbrbmeter is bound to itself.
     * When cblling {@link #resolveInClbss} in the context of {@code inClbss},
     * we cbn't just pbss {@code inClbss} bs the {@code bctubl} pbrbmeter,
     * becbuse if {@code inClbss} hbs type pbrbmeters
     * thbt would be interpreted bs bccessing the rbw type,
     * so we would get unwbnted erbsure.
     * This is why we bind ebch pbrbmeter to itself.
     * If {@code inClbss} does hbve type pbrbmeters bnd hbs methods
     * where those pbrbmeters bppebr in the return type or brgument types,
     * we will correctly lebve those types blone.
     *
     * @pbrbm inClbss  the bbse clbss used to resolve
     * @return b pbrbmeterized type for the clbss,
     *         or the sbme clbss bs {@code inClbss}
     */
    privbte stbtic Type getActublType(Clbss<?> inClbss) {
        Type[] pbrbms = inClbss.getTypePbrbmeters();
        return (pbrbms.length == 0)
                ? inClbss
                : PbrbmeterizedTypeImpl.mbke(
                        inClbss, pbrbms, inClbss.getEnclosingClbss());
    }
}
