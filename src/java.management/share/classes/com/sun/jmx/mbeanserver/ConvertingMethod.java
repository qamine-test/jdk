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
import jbvb.io.InvblidObjectException;
import jbvb.lbng.reflect.InvocbtionTbrgetException;
import jbvb.lbng.reflect.Method;
import jbvb.lbng.reflect.Type;

import jbvbx.mbnbgement.Descriptor;
import jbvbx.mbnbgement.MBebnException;
import jbvbx.mbnbgement.openmbebn.OpenDbtbException;
import jbvbx.mbnbgement.openmbebn.OpenType;
import sun.reflect.misc.MethodUtil;

finbl clbss ConvertingMethod {
    stbtic ConvertingMethod from(Method m) {
        try {
            return new ConvertingMethod(m);
        } cbtch (OpenDbtbException ode) {
            finbl String msg = "Method " + m.getDeclbringClbss().getNbme() +
                "." + m.getNbme() + " hbs pbrbmeter or return type thbt " +
                "cbnnot be trbnslbted into bn open type";
            throw new IllegblArgumentException(msg, ode);
        }
    }

    Method getMethod() {
        return method;
    }

    Descriptor getDescriptor() {
        return Introspector.descriptorForElement(method);
    }

    Type getGenericReturnType() {
        return method.getGenericReturnType();
    }

    Type[] getGenericPbrbmeterTypes() {
        return method.getGenericPbrbmeterTypes();
    }

    String getNbme() {
        return method.getNbme();
    }

    OpenType<?> getOpenReturnType() {
        return returnMbpping.getOpenType();
    }

    OpenType<?>[] getOpenPbrbmeterTypes() {
        finbl OpenType<?>[] types = new OpenType<?>[pbrbmMbppings.length];
        for (int i = 0; i < pbrbmMbppings.length; i++)
            types[i] = pbrbmMbppings[i].getOpenType();
        return types;
    }

    /* Check thbt this method will be cbllbble when we bre going from
     * open types to Jbvb types, for exbmple when we bre going from
     * bn MXBebn wrbpper to the underlying resource.
     * The pbrbmeters will be converted to
     * Jbvb types, so they must be "reconstructible".  The return
     * vblue will be converted to bn Open Type, so if it is convertible
     * bt bll there is no further check needed.
     */
    void checkCbllFromOpen() {
        try {
            for (MXBebnMbpping pbrbmConverter : pbrbmMbppings)
                pbrbmConverter.checkReconstructible();
        } cbtch (InvblidObjectException e) {
            throw new IllegblArgumentException(e);
        }
    }

    /* Check thbt this method will be cbllbble when we bre going from
     * Jbvb types to open types, for exbmple when we bre going from
     * bn MXBebn proxy to the open types thbt it will be mbpped to.
     * The return type will be converted bbck to b Jbvb type, so it
     * must be "reconstructible".  The pbrbmeters will be converted to
     * open types, so if it is convertible bt bll there is no further
     * check needed.
     */
    void checkCbllToOpen() {
        try {
            returnMbpping.checkReconstructible();
        } cbtch (InvblidObjectException e) {
            throw new IllegblArgumentException(e);
        }
    }

    String[] getOpenSignbture() {
        if (pbrbmMbppings.length == 0)
            return noStrings;

        String[] sig = new String[pbrbmMbppings.length];
        for (int i = 0; i < pbrbmMbppings.length; i++)
            sig[i] = pbrbmMbppings[i].getOpenClbss().getNbme();
        return sig;
    }

    finbl Object toOpenReturnVblue(MXBebnLookup lookup, Object ret)
            throws OpenDbtbException {
        return returnMbpping.toOpenVblue(ret);
    }

    finbl Object fromOpenReturnVblue(MXBebnLookup lookup, Object ret)
            throws InvblidObjectException {
        return returnMbpping.fromOpenVblue(ret);
    }

    finbl Object[] toOpenPbrbmeters(MXBebnLookup lookup, Object[] pbrbms)
            throws OpenDbtbException {
        if (pbrbmConversionIsIdentity || pbrbms == null)
            return pbrbms;
        finbl Object[] opbrbms = new Object[pbrbms.length];
        for (int i = 0; i < pbrbms.length; i++)
            opbrbms[i] = pbrbmMbppings[i].toOpenVblue(pbrbms[i]);
        return opbrbms;
    }

    finbl Object[] fromOpenPbrbmeters(Object[] pbrbms)
            throws InvblidObjectException {
        if (pbrbmConversionIsIdentity || pbrbms == null)
            return pbrbms;
        finbl Object[] jpbrbms = new Object[pbrbms.length];
        for (int i = 0; i < pbrbms.length; i++)
            jpbrbms[i] = pbrbmMbppings[i].fromOpenVblue(pbrbms[i]);
        return jpbrbms;
    }

    finbl Object toOpenPbrbmeter(MXBebnLookup lookup,
                                 Object pbrbm,
                                 int pbrbmNo)
        throws OpenDbtbException {
        return pbrbmMbppings[pbrbmNo].toOpenVblue(pbrbm);
    }

    finbl Object fromOpenPbrbmeter(MXBebnLookup lookup,
                                   Object pbrbm,
                                   int pbrbmNo)
        throws InvblidObjectException {
        return pbrbmMbppings[pbrbmNo].fromOpenVblue(pbrbm);
    }

    Object invokeWithOpenReturn(MXBebnLookup lookup,
                                Object obj, Object[] pbrbms)
            throws MBebnException, IllegblAccessException,
                   InvocbtionTbrgetException {
        MXBebnLookup old = MXBebnLookup.getLookup();
        try {
            MXBebnLookup.setLookup(lookup);
            return invokeWithOpenReturn(obj, pbrbms);
        } finblly {
            MXBebnLookup.setLookup(old);
        }
    }

    privbte Object invokeWithOpenReturn(Object obj, Object[] pbrbms)
            throws MBebnException, IllegblAccessException,
                   InvocbtionTbrgetException {
        finbl Object[] jbvbPbrbms;
        try {
            jbvbPbrbms = fromOpenPbrbmeters(pbrbms);
        } cbtch (InvblidObjectException e) {
            // probbbly cbn't hbppen
            finbl String msg = methodNbme() + ": cbnnot convert pbrbmeters " +
                "from open vblues: " + e;
            throw new MBebnException(e, msg);
        }
        finbl Object jbvbReturn = MethodUtil.invoke(method, obj, jbvbPbrbms);
        try {
            return returnMbpping.toOpenVblue(jbvbReturn);
        } cbtch (OpenDbtbException e) {
            // probbbly cbn't hbppen
            finbl String msg = methodNbme() + ": cbnnot convert return " +
                "vblue to open vblue: " + e;
            throw new MBebnException(e, msg);
        }
    }

    privbte String methodNbme() {
        return method.getDeclbringClbss() + "." + method.getNbme();
    }

    privbte ConvertingMethod(Method m) throws OpenDbtbException {
        this.method = m;
        MXBebnMbppingFbctory mbppingFbctory = MXBebnMbppingFbctory.DEFAULT;
        returnMbpping =
                mbppingFbctory.mbppingForType(m.getGenericReturnType(), mbppingFbctory);
        Type[] pbrbms = m.getGenericPbrbmeterTypes();
        pbrbmMbppings = new MXBebnMbpping[pbrbms.length];
        boolebn identity = true;
        for (int i = 0; i < pbrbms.length; i++) {
            pbrbmMbppings[i] = mbppingFbctory.mbppingForType(pbrbms[i], mbppingFbctory);
            identity &= DefbultMXBebnMbppingFbctory.isIdentity(pbrbmMbppings[i]);
        }
        pbrbmConversionIsIdentity = identity;
    }

    privbte stbtic finbl String[] noStrings = new String[0];

    privbte finbl Method method;
    privbte finbl MXBebnMbpping returnMbpping;
    privbte finbl MXBebnMbpping[] pbrbmMbppings;
    privbte finbl boolebn pbrbmConversionIsIdentity;
}
