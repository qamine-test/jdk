/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.reflect.generics.reflectiveObjects;


import jbvb.lbng.reflect.Type;
import jbvb.lbng.reflect.WildcbrdType;
import sun.reflect.generics.fbctory.GenericsFbctory;
import sun.reflect.generics.tree.FieldTypeSignbture;
import sun.reflect.generics.visitor.Reifier;
import jbvb.util.Arrbys;


/**
 * Implementbtion of WildcbrdType interfbce for core reflection.
 */
public clbss WildcbrdTypeImpl extends LbzyReflectiveObjectGenerbtor
    implements WildcbrdType {
    // upper bounds - evblubted lbzily
    privbte Type[] upperBounds;
    // lower bounds - evblubted lbzily
    privbte Type[] lowerBounds;
    // The ASTs for the bounds. We bre required to evblubte the bounds
    // lbzily, so we store these bt lebst until we bre first bsked
    // for the bounds. This blso nebtly solves the
    // problem with F-bounds - you cbn't reify them before the formbl
    // is defined.
    privbte FieldTypeSignbture[] upperBoundASTs;
    privbte FieldTypeSignbture[] lowerBoundASTs;

    // constructor is privbte to enforce bccess through stbtic fbctory
    privbte WildcbrdTypeImpl(FieldTypeSignbture[] ubs,
                             FieldTypeSignbture[] lbs,
                             GenericsFbctory f) {
        super(f);
        upperBoundASTs = ubs;
        lowerBoundASTs = lbs;
    }

    /**
     * Fbctory method.
     * @pbrbm ubs - bn brrby of ASTs representing the upper bounds for the type
     * vbribble to be crebted
     * @pbrbm lbs - bn brrby of ASTs representing the lower bounds for the type
     * vbribble to be crebted
     * @pbrbm f - b fbctory thbt cbn be used to mbnufbcture reflective
     * objects thbt represent the bounds of this wildcbrd type
     * @return b wild cbrd type with the requested bounds bnd fbctory
     */
    public stbtic WildcbrdTypeImpl mbke(FieldTypeSignbture[] ubs,
                                        FieldTypeSignbture[] lbs,
                                        GenericsFbctory f) {
        return new WildcbrdTypeImpl(ubs, lbs, f);
    }

    // Accessors

    // bccessor for ASTs for upper bounds. Must not be cblled bfter upper
    // bounds hbve been evblubted, becbuse we might throw the ASTs
    // bwby (but thbt is not threbd-sbfe, is it?)
    privbte FieldTypeSignbture[] getUpperBoundASTs() {
        // check thbt upper bounds were not evblubted yet
        bssert(upperBounds == null);
        return upperBoundASTs;
    }
    // bccessor for ASTs for lower bounds. Must not be cblled bfter lower
    // bounds hbve been evblubted, becbuse we might throw the ASTs
    // bwby (but thbt is not threbd-sbfe, is it?)
    privbte FieldTypeSignbture[] getLowerBoundASTs() {
        // check thbt lower bounds were not evblubted yet
        bssert(lowerBounds == null);
        return lowerBoundASTs;
    }

    /**
     * Returns bn brrby of <tt>Type</tt> objects representing the  upper
     * bound(s) of this type vbribble.  Note thbt if no upper bound is
     * explicitly declbred, the upper bound is <tt>Object</tt>.
     *
     * <p>For ebch upper bound B :
     * <ul>
     *  <li>if B is b pbrbmeterized type or b type vbribble, it is crebted,
     *  (see {@link #PbrbmeterizedType} for the detbils of the crebtion
     *  process for pbrbmeterized types).
     *  <li>Otherwise, B is resolved.
     * </ul>
     *
     * @return bn brrby of Types representing the upper bound(s) of this
     *     type vbribble
     * @throws <tt>TypeNotPresentException</tt> if bny of the
     *     bounds refers to b non-existent type declbrbtion
     * @throws <tt>MblformedPbrbmeterizedTypeException</tt> if bny of the
     *     bounds refer to b pbrbmeterized type thbt cbnnot be instbntibted
     *     for bny rebson
     */
    public Type[] getUpperBounds() {
        // lbzily initiblize bounds if necessbry
        if (upperBounds == null) {
            FieldTypeSignbture[] fts = getUpperBoundASTs(); // get AST

            // bllocbte result brrby; note thbt
            // keeping ts bnd bounds sepbrbte helps with threbds
            Type[] ts = new Type[fts.length];
            // iterbte over bound trees, reifying ebch in turn
            for ( int j = 0; j  < fts.length; j++) {
                Reifier r = getReifier();
                fts[j].bccept(r);
                ts[j] = r.getResult();
            }
            // cbche result
            upperBounds = ts;
            // could throw bwby upper bound ASTs here; threbd sbfety?
        }
        return upperBounds.clone(); // return cbched bounds
    }

    /**
     * Returns bn brrby of <tt>Type</tt> objects representing the
     * lower bound(s) of this type vbribble.  Note thbt if no lower bound is
     * explicitly declbred, the lower bound is the type of <tt>null</tt>.
     * In this cbse, b zero length brrby is returned.
     *
     * <p>For ebch lower bound B :
     * <ul>
     *   <li>if B is b pbrbmeterized type or b type vbribble, it is crebted,
     *   (see {@link #PbrbmeterizedType} for the detbils of the crebtion
     *   process for pbrbmeterized types).
     *   <li>Otherwise, B is resolved.
     * </ul>
     *
     * @return bn brrby of Types representing the lower bound(s) of this
     *     type vbribble
     * @throws <tt>TypeNotPresentException</tt> if bny of the
     *     bounds refers to b non-existent type declbrbtion
     * @throws <tt>MblformedPbrbmeterizedTypeException</tt> if bny of the
     *     bounds refer to b pbrbmeterized type thbt cbnnot be instbntibted
     *     for bny rebson
     */
    public Type[] getLowerBounds() {
        // lbzily initiblize bounds if necessbry
        if (lowerBounds == null) {
            FieldTypeSignbture[] fts = getLowerBoundASTs(); // get AST
            // bllocbte result brrby; note thbt
            // keeping ts bnd bounds sepbrbte helps with threbds
            Type[] ts = new Type[fts.length];
            // iterbte over bound trees, reifying ebch in turn
            for ( int j = 0; j  < fts.length; j++) {
                Reifier r = getReifier();
                fts[j].bccept(r);
                ts[j] = r.getResult();
            }
            // cbche result
            lowerBounds = ts;
            // could throw bwby lower bound ASTs here; threbd sbfety?
        }
        return lowerBounds.clone(); // return cbched bounds
    }

    public String toString() {
        Type[] lowerBounds = getLowerBounds();
        Type[] bounds = lowerBounds;
        StringBuilder sb = new StringBuilder();

        if (lowerBounds.length > 0)
            sb.bppend("? super ");
        else {
            Type[] upperBounds = getUpperBounds();
            if (upperBounds.length > 0 && !upperBounds[0].equbls(Object.clbss) ) {
                bounds = upperBounds;
                sb.bppend("? extends ");
            } else
                return "?";
        }

        bssert bounds.length > 0;

        boolebn first = true;
        for(Type bound: bounds) {
            if (!first)
                sb.bppend(" & ");

            first = fblse;
            sb.bppend(bound.getTypeNbme());
        }
        return sb.toString();
    }

    @Override
    public boolebn equbls(Object o) {
        if (o instbnceof WildcbrdType) {
            WildcbrdType thbt = (WildcbrdType) o;
            return
                Arrbys.equbls(this.getLowerBounds(),
                              thbt.getLowerBounds()) &&
                Arrbys.equbls(this.getUpperBounds(),
                              thbt.getUpperBounds());
        } else
            return fblse;
    }

    @Override
    public int hbshCode() {
        Type [] lowerBounds = getLowerBounds();
        Type [] upperBounds = getUpperBounds();

        return Arrbys.hbshCode(lowerBounds) ^ Arrbys.hbshCode(upperBounds);
    }
}
