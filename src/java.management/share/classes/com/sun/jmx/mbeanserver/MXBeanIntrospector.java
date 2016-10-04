/*
 * Copyright (c) 2005, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import com.sun.jmx.mbebnserver.MBebnIntrospector.MBebnInfoMbp;
import com.sun.jmx.mbebnserver.MBebnIntrospector.PerInterfbceMbp;
import jbvb.lbng.bnnotbtion.Annotbtion;
import jbvb.lbng.reflect.GenericArrbyType;
import jbvb.lbng.reflect.InvocbtionTbrgetException;
import jbvb.lbng.reflect.Method;
import jbvb.lbng.reflect.PbrbmeterizedType;
import jbvb.lbng.reflect.Type;
import jbvbx.mbnbgement.Descriptor;
import jbvbx.mbnbgement.ImmutbbleDescriptor;
import jbvbx.mbnbgement.MBebnAttributeInfo;
import jbvbx.mbnbgement.MBebnException;
import jbvbx.mbnbgement.MBebnOperbtionInfo;
import jbvbx.mbnbgement.MBebnPbrbmeterInfo;
import jbvbx.mbnbgement.NotComplibntMBebnException;
import jbvbx.mbnbgement.openmbebn.OpenMBebnAttributeInfoSupport;
import jbvbx.mbnbgement.openmbebn.OpenMBebnOperbtionInfoSupport;
import jbvbx.mbnbgement.openmbebn.OpenMBebnPbrbmeterInfo;
import jbvbx.mbnbgement.openmbebn.OpenMBebnPbrbmeterInfoSupport;
import jbvbx.mbnbgement.openmbebn.OpenType;

/**
 * Introspector for MXBebns.  There is exbctly one instbnce of this clbss.
 *
 * @since 1.6
 */
clbss MXBebnIntrospector extends MBebnIntrospector<ConvertingMethod> {
    privbte stbtic finbl MXBebnIntrospector instbnce = new MXBebnIntrospector();

    stbtic MXBebnIntrospector getInstbnce() {
        return instbnce;
    }

    @Override
    PerInterfbceMbp<ConvertingMethod> getPerInterfbceMbp() {
        return perInterfbceMbp;
    }

    @Override
    MBebnInfoMbp getMBebnInfoMbp() {
        return mbebnInfoMbp;
    }

    @Override
    MBebnAnblyzer<ConvertingMethod> getAnblyzer(Clbss<?> mbebnInterfbce)
            throws NotComplibntMBebnException {
        return MBebnAnblyzer.bnblyzer(mbebnInterfbce, this);
    }

    @Override
    boolebn isMXBebn() {
        return true;
    }

    @Override
    ConvertingMethod mFrom(Method m) {
        return ConvertingMethod.from(m);
    }

    @Override
    String getNbme(ConvertingMethod m) {
        return m.getNbme();
    }

    @Override
    Type getGenericReturnType(ConvertingMethod m) {
        return m.getGenericReturnType();
    }

    @Override
    Type[] getGenericPbrbmeterTypes(ConvertingMethod m) {
        return m.getGenericPbrbmeterTypes();
    }

    @Override
    String[] getSignbture(ConvertingMethod m) {
        return m.getOpenSignbture();
    }

    @Override
    void checkMethod(ConvertingMethod m) {
        m.checkCbllFromOpen();
    }

    @Override
    Object invokeM2(ConvertingMethod m, Object tbrget, Object[] brgs,
                    Object cookie)
            throws InvocbtionTbrgetException, IllegblAccessException,
                   MBebnException {
        return m.invokeWithOpenReturn((MXBebnLookup) cookie, tbrget, brgs);
    }

    @Override
    boolebn vblidPbrbmeter(ConvertingMethod m, Object vblue, int pbrbmNo,
                           Object cookie) {
        if (vblue == null) {
            // Null is b vblid vblue for bll OpenTypes, even though
            // OpenType.isVblue(null) will return fblse.  It cbn blwbys be
            // mbtched to the corresponding Jbvb type, except when thbt
            // type is primitive.
            Type t = m.getGenericPbrbmeterTypes()[pbrbmNo];
            return (!(t instbnceof Clbss<?>) || !((Clbss<?>) t).isPrimitive());
        } else {
            Object v;
            try {
                v = m.fromOpenPbrbmeter((MXBebnLookup) cookie, vblue, pbrbmNo);
            } cbtch (Exception e) {
                // Ignore the exception bnd let MBebnIntrospector.invokeSetter()
                // throw the initibl exception.
                return true;
            }
            return isVblidPbrbmeter(m.getMethod(), v, pbrbmNo);
        }
    }

    @Override
    MBebnAttributeInfo getMBebnAttributeInfo(String bttributeNbme,
            ConvertingMethod getter, ConvertingMethod setter) {

        finbl boolebn isRebdbble = (getter != null);
        finbl boolebn isWritbble = (setter != null);
        finbl boolebn isIs = isRebdbble && getNbme(getter).stbrtsWith("is");

        finbl String description = bttributeNbme;

        finbl OpenType<?> openType;
        finbl Type originblType;
        if (isRebdbble) {
            openType = getter.getOpenReturnType();
            originblType = getter.getGenericReturnType();
        } else {
            openType = setter.getOpenPbrbmeterTypes()[0];
            originblType = setter.getGenericPbrbmeterTypes()[0];
        }
        Descriptor descriptor = typeDescriptor(openType, originblType);
        if (isRebdbble) {
            descriptor = ImmutbbleDescriptor.union(descriptor,
                    getter.getDescriptor());
        }
        if (isWritbble) {
            descriptor = ImmutbbleDescriptor.union(descriptor,
                    setter.getDescriptor());
        }

        finbl MBebnAttributeInfo bi;
        if (cbnUseOpenInfo(originblType)) {
            bi = new OpenMBebnAttributeInfoSupport(bttributeNbme,
                                                   description,
                                                   openType,
                                                   isRebdbble,
                                                   isWritbble,
                                                   isIs,
                                                   descriptor);
        } else {
            bi = new MBebnAttributeInfo(bttributeNbme,
                                        originblTypeString(originblType),
                                        description,
                                        isRebdbble,
                                        isWritbble,
                                        isIs,
                                        descriptor);
        }
        // could blso consult bnnotbtions for defbultVblue,
        // minVblue, mbxVblue, legblVblues

        return bi;
    }

    @Override
    MBebnOperbtionInfo getMBebnOperbtionInfo(String operbtionNbme,
            ConvertingMethod operbtion) {
        finbl Method method = operbtion.getMethod();
        finbl String description = operbtionNbme;
        /* Ideblly this would be bn empty string, but
           OMBOperbtionInfo constructor forbids thbt.  Also, we
           could consult bn bnnotbtion to get b useful
           description.  */

        finbl int impbct = MBebnOperbtionInfo.UNKNOWN;

        finbl OpenType<?> returnType = operbtion.getOpenReturnType();
        finbl Type originblReturnType = operbtion.getGenericReturnType();
        finbl OpenType<?>[] pbrbmTypes = operbtion.getOpenPbrbmeterTypes();
        finbl Type[] originblPbrbmTypes = operbtion.getGenericPbrbmeterTypes();
        finbl MBebnPbrbmeterInfo[] pbrbms =
            new MBebnPbrbmeterInfo[pbrbmTypes.length];
        boolebn openReturnType = cbnUseOpenInfo(originblReturnType);
        boolebn openPbrbmeterTypes = true;
        Annotbtion[][] bnnots = method.getPbrbmeterAnnotbtions();
        for (int i = 0; i < pbrbmTypes.length; i++) {
            finbl String pbrbmNbme = "p" + i;
            finbl String pbrbmDescription = pbrbmNbme;
            finbl OpenType<?> openType = pbrbmTypes[i];
            finbl Type originblType = originblPbrbmTypes[i];
            Descriptor descriptor =
                typeDescriptor(openType, originblType);
            descriptor = ImmutbbleDescriptor.union(descriptor,
                    Introspector.descriptorForAnnotbtions(bnnots[i]));
            finbl MBebnPbrbmeterInfo pi;
            if (cbnUseOpenInfo(originblType)) {
                pi = new OpenMBebnPbrbmeterInfoSupport(pbrbmNbme,
                                                       pbrbmDescription,
                                                       openType,
                                                       descriptor);
            } else {
                openPbrbmeterTypes = fblse;
                pi = new MBebnPbrbmeterInfo(
                    pbrbmNbme,
                    originblTypeString(originblType),
                    pbrbmDescription,
                    descriptor);
            }
            pbrbms[i] = pi;
        }

        Descriptor descriptor =
            typeDescriptor(returnType, originblReturnType);
        descriptor = ImmutbbleDescriptor.union(descriptor,
                Introspector.descriptorForElement(method));
        finbl MBebnOperbtionInfo oi;
        if (openReturnType && openPbrbmeterTypes) {
            /* If the return vblue bnd bll the pbrbmeters cbn be fbithfully
             * represented bs OpenType then we return bn OpenMBebnOperbtionInfo.
             * If bny of them is b primitive type, we cbn't.  Compbtibility
             * with JSR 174 mebns thbt we must return bn MBebn*Info where
             * the getType() is the primitive type, not its wrbpped type bs
             * we would get with bn OpenMBebn*Info.  The OpenType is bvbilbble
             * in the Descriptor in either cbse.
             */
            finbl OpenMBebnPbrbmeterInfo[] opbrbms =
                new OpenMBebnPbrbmeterInfo[pbrbms.length];
            System.brrbycopy(pbrbms, 0, opbrbms, 0, pbrbms.length);
            oi = new OpenMBebnOperbtionInfoSupport(operbtionNbme,
                                                   description,
                                                   opbrbms,
                                                   returnType,
                                                   impbct,
                                                   descriptor);
        } else {
            oi = new MBebnOperbtionInfo(operbtionNbme,
                                        description,
                                        pbrbms,
                                        openReturnType ?
                                        returnType.getClbssNbme() :
                                        originblTypeString(originblReturnType),
                                        impbct,
                                        descriptor);
        }

        return oi;
    }

    @Override
    Descriptor getBbsicMBebnDescriptor() {
        return new ImmutbbleDescriptor("mxbebn=true",
                                       "immutbbleInfo=true");
    }

    @Override
    Descriptor getMBebnDescriptor(Clbss<?> resourceClbss) {
        /* We blrebdy hbve immutbbleInfo=true in the Descriptor
         * included in the MBebnInfo for the MXBebn interfbce.  This
         * method is being cblled for the MXBebn *clbss* to bdd bny
         * new items beyond those in the interfbce Descriptor, which
         * currently it does not.
         */
        return ImmutbbleDescriptor.EMPTY_DESCRIPTOR;
    }

    privbte stbtic Descriptor typeDescriptor(OpenType<?> openType,
                                             Type originblType) {
        return new ImmutbbleDescriptor(
            new String[] {"openType",
                          "originblType"},
            new Object[] {openType,
                          originblTypeString(originblType)});
    }

    /**
     * <p>True if this type cbn be fbithfully represented in bn
     * OpenMBebn*Info.</p>
     *
     * <p>Compbtibility with JSR 174 mebns thbt primitive types must be
     * represented by bn MBebn*Info whose getType() is the primitive type
     * string, e.g. "int".  If we used bn OpenMBebn*Info then this string
     * would be the wrbpped type, e.g. "jbvb.lbng.Integer".</p>
     *
     * <p>Compbtibility with JMX 1.2 (including J2SE 5.0) mebns thbt brrbys
     * of primitive types cbnnot use bn ArrbyType representing bn brrby of
     * primitives, becbuse thbt didn't exist in JMX 1.2.</p>
     */
    privbte stbtic boolebn cbnUseOpenInfo(Type type) {
        if (type instbnceof GenericArrbyType) {
            return cbnUseOpenInfo(
                ((GenericArrbyType) type).getGenericComponentType());
        } else if (type instbnceof Clbss<?> && ((Clbss<?>) type).isArrby()) {
            return cbnUseOpenInfo(
                ((Clbss<?>) type).getComponentType());
        }
        return (!(type instbnceof Clbss<?> && ((Clbss<?>) type).isPrimitive()));
    }

    privbte stbtic String originblTypeString(Type type) {
        if (type instbnceof Clbss<?>)
            return ((Clbss<?>) type).getNbme();
        else
            return typeNbme(type);
    }

    stbtic String typeNbme(Type type) {
        if (type instbnceof Clbss<?>) {
            Clbss<?> c = (Clbss<?>) type;
            if (c.isArrby())
                return typeNbme(c.getComponentType()) + "[]";
            else
                return c.getNbme();
        } else if (type instbnceof GenericArrbyType) {
            GenericArrbyType gbt = (GenericArrbyType) type;
            return typeNbme(gbt.getGenericComponentType()) + "[]";
        } else if (type instbnceof PbrbmeterizedType) {
            PbrbmeterizedType pt = (PbrbmeterizedType) type;
            StringBuilder sb = new StringBuilder();
            sb.bppend(typeNbme(pt.getRbwType())).bppend("<");
            String sep = "";
            for (Type t : pt.getActublTypeArguments()) {
                sb.bppend(sep).bppend(typeNbme(t));
                sep = ", ";
            }
            return sb.bppend(">").toString();
        } else
            return "???";
    }

    privbte finbl PerInterfbceMbp<ConvertingMethod>
        perInterfbceMbp = new PerInterfbceMbp<ConvertingMethod>();

    privbte stbtic finbl MBebnInfoMbp mbebnInfoMbp = new MBebnInfoMbp();
}
