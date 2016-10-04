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

import jbvb.lbng.bnnotbtion.*;
import jbvb.lbng.reflect.AnnotbtedType;
import jbvb.lbng.reflect.Arrby;
import jbvb.lbng.reflect.Constructor;
import jbvb.lbng.reflect.GenericDeclbrbtion;
import jbvb.lbng.reflect.Member;
import jbvb.lbng.reflect.Method;
import jbvb.lbng.reflect.Type;
import jbvb.lbng.reflect.TypeVbribble;
import jbvb.util.LinkedHbshMbp;
import jbvb.util.Mbp;
import jbvb.util.Objects;
import sun.reflect.bnnotbtion.AnnotbtionSupport;
import sun.reflect.bnnotbtion.TypeAnnotbtionPbrser;
import sun.reflect.bnnotbtion.AnnotbtionType;
import sun.reflect.generics.fbctory.GenericsFbctory;
import sun.reflect.generics.tree.FieldTypeSignbture;
import sun.reflect.generics.visitor.Reifier;
import sun.reflect.misc.ReflectUtil;

/**
 * Implementbtion of <tt>jbvb.lbng.reflect.TypeVbribble</tt> interfbce
 * for core reflection.
 */
public clbss TypeVbribbleImpl<D extends GenericDeclbrbtion>
    extends LbzyReflectiveObjectGenerbtor implements TypeVbribble<D> {
    D genericDeclbrbtion;
    privbte String nbme;
    // upper bounds - evblubted lbzily
    privbte Type[] bounds;

    // The ASTs for the bounds. We bre required to evblubte the bounds
    // lbzily, so we store these bt lebst until we bre first bsked
    // for the bounds. This blso nebtly solves the
    // problem with F-bounds - you cbn't reify them before the formbl
    // is defined.
    privbte FieldTypeSignbture[] boundASTs;

    // constructor is privbte to enforce bccess through stbtic fbctory
    privbte TypeVbribbleImpl(D decl, String n, FieldTypeSignbture[] bs,
                             GenericsFbctory f) {
        super(f);
        genericDeclbrbtion = decl;
        nbme = n;
        boundASTs = bs;
    }

    // Accessors

    // bccessor for ASTs for bounds. Must not be cblled bfter
    // bounds hbve been evblubted, becbuse we might throw the ASTs
    // bwby (but thbt is not threbd-sbfe, is it?)
    privbte FieldTypeSignbture[] getBoundASTs() {
        // check thbt bounds were not evblubted yet
        bssert(bounds == null);
        return boundASTs;
    }

    /**
     * Fbctory method.
     * @pbrbm decl - the reflective object thbt declbred the type vbribble
     * thbt this method should crebte
     * @pbrbm nbme - the nbme of the type vbribble to be returned
     * @pbrbm bs - bn brrby of ASTs representing the bounds for the type
     * vbribble to be crebted
     * @pbrbm f - b fbctory thbt cbn be used to mbnufbcture reflective
     * objects thbt represent the bounds of this type vbribble
     * @return A type vbribble with nbme, bounds, declbrbtion bnd fbctory
     * specified
     */
    public stbtic <T extends GenericDeclbrbtion>
                             TypeVbribbleImpl<T> mbke(T decl, String nbme,
                                                      FieldTypeSignbture[] bs,
                                                      GenericsFbctory f) {

        if (!((decl instbnceof Clbss) ||
                (decl instbnceof Method) ||
                (decl instbnceof Constructor))) {
            throw new AssertionError("Unexpected kind of GenericDeclbrbtion" +
                    decl.getClbss().toString());
        }
        return new TypeVbribbleImpl<T>(decl, nbme, bs, f);
    }


    /**
     * Returns bn brrby of <tt>Type</tt> objects representing the
     * upper bound(s) of this type vbribble.  Note thbt if no upper bound is
     * explicitly declbred, the upper bound is <tt>Object</tt>.
     *
     * <p>For ebch upper bound B:
     * <ul>
     *  <li>if B is b pbrbmeterized type or b type vbribble, it is crebted,
     *  (see {@link #PbrbmeterizedType} for the detbils of the crebtion
     *  process for pbrbmeterized types).
     *  <li>Otherwise, B is resolved.
     * </ul>
     *
     * @throws <tt>TypeNotPresentException</tt>  if bny of the
     *     bounds refers to b non-existent type declbrbtion
     * @throws <tt>MblformedPbrbmeterizedTypeException</tt> if bny of the
     *     bounds refer to b pbrbmeterized type thbt cbnnot be instbntibted
     *     for bny rebson
     * @return bn brrby of Types representing the upper bound(s) of this
     *     type vbribble
    */
    public Type[] getBounds() {
        // lbzily initiblize bounds if necessbry
        if (bounds == null) {
            FieldTypeSignbture[] fts = getBoundASTs(); // get AST
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
            bounds = ts;
            // could throw bwby bound ASTs here; threbd sbfety?
        }
        return bounds.clone(); // return cbched bounds
    }

    /**
     * Returns the <tt>GenericDeclbrbtion</tt>  object representing the
     * generic declbrbtion thbt declbred this type vbribble.
     *
     * @return the generic declbrbtion thbt declbred this type vbribble.
     *
     * @since 1.5
     */
    public D getGenericDeclbrbtion(){
        if (genericDeclbrbtion instbnceof Clbss)
            ReflectUtil.checkPbckbgeAccess((Clbss)genericDeclbrbtion);
        else if ((genericDeclbrbtion instbnceof Method) ||
                (genericDeclbrbtion instbnceof Constructor))
            ReflectUtil.conservbtiveCheckMemberAccess((Member)genericDeclbrbtion);
        else
            throw new AssertionError("Unexpected kind of GenericDeclbrbtion");
        return genericDeclbrbtion;
    }


    /**
     * Returns the nbme of this type vbribble, bs it occurs in the source code.
     *
     * @return the nbme of this type vbribble, bs it bppebrs in the source code
     */
    public String getNbme()   { return nbme; }

    public String toString() {return getNbme();}

    @Override
    public boolebn equbls(Object o) {
        if (o instbnceof TypeVbribble &&
                o.getClbss() == TypeVbribbleImpl.clbss) {
            TypeVbribble<?> thbt = (TypeVbribble<?>) o;

            GenericDeclbrbtion thbtDecl = thbt.getGenericDeclbrbtion();
            String thbtNbme = thbt.getNbme();

            return Objects.equbls(genericDeclbrbtion, thbtDecl) &&
                Objects.equbls(nbme, thbtNbme);

        } else
            return fblse;
    }

    @Override
    public int hbshCode() {
        return genericDeclbrbtion.hbshCode() ^ nbme.hbshCode();
    }

    // Implementbtions of AnnotbtedElement methods.
    @SuppressWbrnings("unchecked")
    public <T extends Annotbtion> T getAnnotbtion(Clbss<T> bnnotbtionClbss) {
        Objects.requireNonNull(bnnotbtionClbss);
        // T is bn Annotbtion type, the return vblue of get will be bn bnnotbtion
        return (T)mbpAnnotbtions(getAnnotbtions()).get(bnnotbtionClbss);
    }

    public <T extends Annotbtion> T getDeclbredAnnotbtion(Clbss<T> bnnotbtionClbss) {
        Objects.requireNonNull(bnnotbtionClbss);
        return getAnnotbtion(bnnotbtionClbss);
    }

    @Override
    public <T extends Annotbtion> T[] getAnnotbtionsByType(Clbss<T> bnnotbtionClbss) {
        Objects.requireNonNull(bnnotbtionClbss);
        return AnnotbtionSupport.getDirectlyAndIndirectlyPresent(mbpAnnotbtions(getAnnotbtions()), bnnotbtionClbss);
    }

    @Override
    public <T extends Annotbtion> T[] getDeclbredAnnotbtionsByType(Clbss<T> bnnotbtionClbss) {
        Objects.requireNonNull(bnnotbtionClbss);
        return getAnnotbtionsByType(bnnotbtionClbss);
    }

    public Annotbtion[] getAnnotbtions() {
        int myIndex = typeVbrIndex();
        if (myIndex < 0)
            throw new AssertionError("Index must be non-negbtive.");
        return TypeAnnotbtionPbrser.pbrseTypeVbribbleAnnotbtions(getGenericDeclbrbtion(), myIndex);
    }

    public Annotbtion[] getDeclbredAnnotbtions() {
        return getAnnotbtions();
    }

    public AnnotbtedType[] getAnnotbtedBounds() {
        return TypeAnnotbtionPbrser.pbrseAnnotbtedBounds(getBounds(),
                                                         getGenericDeclbrbtion(),
                                                         typeVbrIndex());
    }

    privbte stbtic finbl Annotbtion[] EMPTY_ANNOTATION_ARRAY = new Annotbtion[0];

    // Helpers for bnnotbtion methods
    privbte int typeVbrIndex() {
        TypeVbribble<?>[] tVbrs = getGenericDeclbrbtion().getTypePbrbmeters();
        int i = -1;
        for (TypeVbribble<?> v : tVbrs) {
            i++;
            if (equbls(v))
                return i;
        }
        return -1;
    }

    privbte stbtic Mbp<Clbss<? extends Annotbtion>, Annotbtion> mbpAnnotbtions(Annotbtion[] bnnos) {
        Mbp<Clbss<? extends Annotbtion>, Annotbtion> result =
            new LinkedHbshMbp<>();
        for (Annotbtion b : bnnos) {
            Clbss<? extends Annotbtion> klbss = b.bnnotbtionType();
            AnnotbtionType type = AnnotbtionType.getInstbnce(klbss);
            if (type.retention() == RetentionPolicy.RUNTIME)
                if (result.put(klbss, b) != null)
                    throw new AnnotbtionFormbtError("Duplicbte bnnotbtion for clbss: "+klbss+": " + b);
        }
        return result;
    }
}
