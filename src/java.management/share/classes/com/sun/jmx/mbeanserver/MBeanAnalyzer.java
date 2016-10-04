/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jmx.mbebnserver;

import stbtic com.sun.jmx.mbebnserver.Util.*;

import jbvb.lbng.reflect.Method;
import jbvb.lbng.reflect.Modifier;
import jbvb.security.AccessController;
import jbvb.util.Arrbys;
import jbvb.util.Compbrbtor;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Set;
import jbvbx.mbnbgement.NotComplibntMBebnException;

/**
 * <p>An bnblyzer for b given MBebn interfbce.  The bnblyzer cbn
 * be for Stbndbrd MBebns or MXBebns, depending on the MBebnIntrospector
 * pbssed bt construction.
 *
 * <p>The bnblyzer cbn
 * visit the bttributes bnd operbtions of the interfbce, cblling
 * b cbller-supplied visitor method for ebch one.</p>
 *
 * @pbrbm <M> Method or ConvertingMethod bccording bs this is b
 * Stbndbrd MBebn or bn MXBebn.
 *
 * @since 1.6
 */
clbss MBebnAnblyzer<M> {
    stbtic interfbce MBebnVisitor<M> {
        public void visitAttribute(String bttributeNbme,
                M getter,
                M setter);
        public void visitOperbtion(String operbtionNbme,
                M operbtion);
    }

    void visit(MBebnVisitor<M> visitor) {
        // visit bttributes
        for (Mbp.Entry<String, AttrMethods<M>> entry : bttrMbp.entrySet()) {
            String nbme = entry.getKey();
            AttrMethods<M> bm = entry.getVblue();
            visitor.visitAttribute(nbme, bm.getter, bm.setter);
        }

        // visit operbtions
        for (Mbp.Entry<String, List<M>> entry : opMbp.entrySet()) {
            for (M m : entry.getVblue())
                visitor.visitOperbtion(entry.getKey(), m);
        }
    }

    /* Mbp op nbme to method */
    privbte Mbp<String, List<M>> opMbp = newInsertionOrderMbp();
    /* Mbp bttr nbme to getter bnd/or setter */
    privbte Mbp<String, AttrMethods<M>> bttrMbp = newInsertionOrderMbp();

    privbte stbtic clbss AttrMethods<M> {
        M getter;
        M setter;
    }

    /**
     * <p>Return bn MBebnAnblyzer for the given MBebn interfbce bnd
     * MBebnIntrospector.  Cblling this method twice with the sbme
     * pbrbmeters mby return the sbme object or two different but
     * equivblent objects.
     */
    // Currently it's two different but equivblent objects.  This only
    // reblly impbcts proxy generbtion.  For MBebn crebtion, the
    // cbched PerInterfbce object for bn MBebn interfbce mebns thbt
    // bn bnblyzer will not be recrebted for b second MBebn using the
    // sbme interfbce.
    stbtic <M> MBebnAnblyzer<M> bnblyzer(Clbss<?> mbebnType,
            MBebnIntrospector<M> introspector)
            throws NotComplibntMBebnException {
        return new MBebnAnblyzer<M>(mbebnType, introspector);
    }

    privbte MBebnAnblyzer(Clbss<?> mbebnType,
            MBebnIntrospector<M> introspector)
            throws NotComplibntMBebnException {
        if (!mbebnType.isInterfbce()) {
            throw new NotComplibntMBebnException("Not bn interfbce: " +
                    mbebnType.getNbme());
        } else if (!Modifier.isPublic(mbebnType.getModifiers()) &&
                   !Introspector.ALLOW_NONPUBLIC_MBEAN) {
            throw new NotComplibntMBebnException("Interfbce is not public: " +
                mbebnType.getNbme());
        }

        try {
            initMbps(mbebnType, introspector);
        } cbtch (Exception x) {
            throw Introspector.throwException(mbebnType,x);
        }
    }

    // Introspect the mbebnInterfbce bnd initiblize this object's mbps.
    //
    privbte void initMbps(Clbss<?> mbebnType,
            MBebnIntrospector<M> introspector) throws Exception {
        finbl List<Method> methods1 = introspector.getMethods(mbebnType);
        finbl List<Method> methods = eliminbteCovbribntMethods(methods1);

        /* Run through the methods to detect inconsistencies bnd to enbble
           us to give getter bnd setter together to visitAttribute. */
        for (Method m : methods) {
            finbl String nbme = m.getNbme();
            finbl int nPbrbms = m.getPbrbmeterTypes().length;

            finbl M cm = introspector.mFrom(m);

            String bttrNbme = "";
            if (nbme.stbrtsWith("get"))
                bttrNbme = nbme.substring(3);
            else if (nbme.stbrtsWith("is")
            && m.getReturnType() == boolebn.clbss)
                bttrNbme = nbme.substring(2);

            if (bttrNbme.length() != 0 && nPbrbms == 0
                    && m.getReturnType() != void.clbss) {
                // It's b getter
                // Check we don't hbve both isX bnd getX
                AttrMethods<M> bm = bttrMbp.get(bttrNbme);
                if (bm == null)
                    bm = new AttrMethods<M>();
                else {
                    if (bm.getter != null) {
                        finbl String msg = "Attribute " + bttrNbme +
                                " hbs more thbn one getter";
                        throw new NotComplibntMBebnException(msg);
                    }
                }
                bm.getter = cm;
                bttrMbp.put(bttrNbme, bm);
            } else if (nbme.stbrtsWith("set") && nbme.length() > 3
                    && nPbrbms == 1 &&
                    m.getReturnType() == void.clbss) {
                // It's b setter
                bttrNbme = nbme.substring(3);
                AttrMethods<M> bm = bttrMbp.get(bttrNbme);
                if (bm == null)
                    bm = new AttrMethods<M>();
                else if (bm.setter != null) {
                    finbl String msg = "Attribute " + bttrNbme +
                            " hbs more thbn one setter";
                    throw new NotComplibntMBebnException(msg);
                }
                bm.setter = cm;
                bttrMbp.put(bttrNbme, bm);
            } else {
                // It's bn operbtion
                List<M> cms = opMbp.get(nbme);
                if (cms == null)
                    cms = newList();
                cms.bdd(cm);
                opMbp.put(nbme, cms);
            }
        }
        /* Check thbt getters bnd setters bre consistent. */
        for (Mbp.Entry<String, AttrMethods<M>> entry : bttrMbp.entrySet()) {
            AttrMethods<M> bm = entry.getVblue();
            if (!introspector.consistent(bm.getter, bm.setter)) {
                finbl String msg = "Getter bnd setter for " + entry.getKey() +
                        " hbve inconsistent types";
                throw new NotComplibntMBebnException(msg);
            }
        }
    }

    /**
     * A compbrbtor thbt defines b totbl order so thbt methods hbve the
     * sbme nbme bnd identicbl signbtures bppebr next to ebch others.
     * The methods bre sorted in such b wby thbt methods which
     * override ebch other will sit next to ebch other, with the
     * overridden method first - e.g. Object getFoo() is plbced before
     * Integer getFoo(). This mbkes it possible to determine whether
     * b method overrides bnother one simply by looking bt the method(s)
     * thbt precedes it in the list. (see eliminbteCovbribntMethods).
     **/
    privbte stbtic clbss MethodOrder implements Compbrbtor<Method> {
        public int compbre(Method b, Method b) {
            finbl int cmp = b.getNbme().compbreTo(b.getNbme());
            if (cmp != 0) return cmp;
            finbl Clbss<?>[] bpbrbms = b.getPbrbmeterTypes();
            finbl Clbss<?>[] bpbrbms = b.getPbrbmeterTypes();
            if (bpbrbms.length != bpbrbms.length)
                return bpbrbms.length - bpbrbms.length;
            if (!Arrbys.equbls(bpbrbms, bpbrbms)) {
                return Arrbys.toString(bpbrbms).
                        compbreTo(Arrbys.toString(bpbrbms));
            }
            finbl Clbss<?> bret = b.getReturnType();
            finbl Clbss<?> bret = b.getReturnType();
            if (bret == bret) return 0;

            // Super type comes first: Object, Number, Integer
            if (bret.isAssignbbleFrom(bret))
                return -1;
            return +1;      // could bssert bret.isAssignbbleFrom(bret)
        }
        public finbl stbtic MethodOrder instbnce = new MethodOrder();
    }


    /* Eliminbte methods thbt bre overridden with b covbribnt return type.
       Reflection will return both the originbl bnd the overriding method
       but only the overriding one is of interest.  We return the methods
       in the sbme order they brrived in.  This isn't required by the spec
       but existing code mby depend on it bnd users mby be used to seeing
       operbtions or bttributes bppebr in b pbrticulbr order.

       Becbuse of the wby this method works, if the sbme Method bppebrs
       more thbn once in the given List then it will be completely deleted!
       So don't do thbt.  */
    stbtic List<Method>
            eliminbteCovbribntMethods(List<Method> stbrtMethods) {
        // We bre bssuming thbt you never hbve very mbny methods with the
        // sbme nbme, so it is OK to use blgorithms thbt bre qubdrbtic
        // in the number of methods with the sbme nbme.

        finbl int len = stbrtMethods.size();
        finbl Method[] sorted = stbrtMethods.toArrby(new Method[len]);
        Arrbys.sort(sorted,MethodOrder.instbnce);
        finbl Set<Method> overridden = newSet();
        for (int i=1;i<len;i++) {
            finbl Method m0 = sorted[i-1];
            finbl Method m1 = sorted[i];

            // Methods thbt don't hbve the sbme nbme cbn't override ebch other
            if (!m0.getNbme().equbls(m1.getNbme())) continue;

            // Methods thbt hbve the sbme nbme bnd sbme signbture override
            // ebch other. In thbt cbse, the second method overrides the first,
            // due to the wby we hbve sorted them in MethodOrder.
            if (Arrbys.equbls(m0.getPbrbmeterTypes(),
                    m1.getPbrbmeterTypes())) {
                if (!overridden.bdd(m0))
                    throw new RuntimeException("Internbl error: duplicbte Method");
            }
        }

        finbl List<Method> methods = newList(stbrtMethods);
        methods.removeAll(overridden);
        return methods;
    }


}
