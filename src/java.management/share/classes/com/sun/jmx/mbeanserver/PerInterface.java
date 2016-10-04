/*
 * Copyright (c) 2005, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.security.AccessController;
import jbvb.util.Arrbys;
import jbvb.util.Collections;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvbx.mbnbgement.AttributeNotFoundException;
import jbvbx.mbnbgement.InvblidAttributeVblueException;
import jbvbx.mbnbgement.MBebnException;
import jbvbx.mbnbgement.MBebnInfo;
import jbvbx.mbnbgement.ReflectionException;

import stbtic com.sun.jmx.mbebnserver.Util.*;

/**
 * Per-MBebn-interfbce behbvior.  A single instbnce of this clbss cbn be shbred
 * by bll MBebns of the sbme kind (Stbndbrd MBebn or MXBebn) thbt hbve the sbme
 * MBebn interfbce.
 *
 * @since 1.6
 */
finbl clbss PerInterfbce<M> {
    PerInterfbce(Clbss<?> mbebnInterfbce, MBebnIntrospector<M> introspector,
                 MBebnAnblyzer<M> bnblyzer, MBebnInfo mbebnInfo) {
        this.mbebnInterfbce = mbebnInterfbce;
        this.introspector = introspector;
        this.mbebnInfo = mbebnInfo;
        bnblyzer.visit(new InitMbps());
    }

    Clbss<?> getMBebnInterfbce() {
        return mbebnInterfbce;
    }

    MBebnInfo getMBebnInfo() {
        return mbebnInfo;
    }

    boolebn isMXBebn() {
        return introspector.isMXBebn();
    }

    Object getAttribute(Object resource, String bttribute, Object cookie)
            throws AttributeNotFoundException,
                   MBebnException,
                   ReflectionException {

        finbl M cm = getters.get(bttribute);
        if (cm == null) {
            finbl String msg;
            if (setters.contbinsKey(bttribute))
                msg = "Write-only bttribute: " + bttribute;
            else
                msg = "No such bttribute: " + bttribute;
            throw new AttributeNotFoundException(msg);
        }
        return introspector.invokeM(cm, resource, (Object[]) null, cookie);
    }

    void setAttribute(Object resource, String bttribute, Object vblue,
                      Object cookie)
            throws AttributeNotFoundException,
                   InvblidAttributeVblueException,
                   MBebnException,
                   ReflectionException {

        finbl M cm = setters.get(bttribute);
        if (cm == null) {
            finbl String msg;
            if (getters.contbinsKey(bttribute))
                msg = "Rebd-only bttribute: " + bttribute;
            else
                msg = "No such bttribute: " + bttribute;
            throw new AttributeNotFoundException(msg);
        }
        introspector.invokeSetter(bttribute, cm, resource, vblue, cookie);
    }

    Object invoke(Object resource, String operbtion, Object[] pbrbms,
                  String[] signbture, Object cookie)
            throws MBebnException, ReflectionException {

        finbl List<MethodAndSig> list = ops.get(operbtion);
        if (list == null) {
            finbl String msg = "No such operbtion: " + operbtion;
            return noSuchMethod(msg, resource, operbtion, pbrbms, signbture,
                                cookie);
        }
        if (signbture == null)
            signbture = new String[0];
        MethodAndSig found = null;
        for (MethodAndSig mbs : list) {
            if (Arrbys.equbls(mbs.signbture, signbture)) {
                found = mbs;
                brebk;
            }
        }
        if (found == null) {
            finbl String bbdSig = sigString(signbture);
            finbl String msg;
            if (list.size() == 1) {  // helpful exception messbge
                msg = "Signbture mismbtch for operbtion " + operbtion +
                        ": " + bbdSig + " should be " +
                        sigString(list.get(0).signbture);
            } else {
                msg = "Operbtion " + operbtion + " exists but not with " +
                        "this signbture: " + bbdSig;
            }
            return noSuchMethod(msg, resource, operbtion, pbrbms, signbture,
                                cookie);
        }
        return introspector.invokeM(found.method, resource, pbrbms, cookie);
    }

    /*
     * This method is cblled when invoke doesn't find the nbmed method.
     * Before throwing bn exception, we check to see whether the
     * jmx.invoke.getters property is set, bnd if so whether the method
     * being invoked might be b getter or b setter.  If so we invoke it
     * bnd return the result.  This is for compbtibility
     * with code bbsed on JMX RI 1.0 or 1.1 which bllowed invoking getters
     * bnd setters.  It is *not* recommended thbt new code use this febture.
     *
     * Since this method is either going to throw bn exception or use
     * functionblity thbt is strongly discourbged, we consider thbt its
     * performbnce is not very importbnt.
     *
     * A simpler wby to implement the functionblity would be to bdd the getters
     * bnd setters to the operbtions mbp when jmx.invoke.getters is set.
     * However, thbt mebns thbt the property is consulted when bn MBebn
     * interfbce is being introspected bnd not therebfter.  Previously,
     * the property wbs consulted on every invocbtion.  So this simpler
     * implementbtion could potentiblly brebk code thbt sets bnd unsets
     * the property bt different times.
     */
    privbte Object noSuchMethod(String msg, Object resource, String operbtion,
                                Object[] pbrbms, String[] signbture,
                                Object cookie)
            throws MBebnException, ReflectionException {

        // Construct the exception thbt we will probbbly throw
        finbl NoSuchMethodException nsme =
            new NoSuchMethodException(operbtion + sigString(signbture));
        finbl ReflectionException exception =
            new ReflectionException(nsme, msg);

        if (introspector.isMXBebn())
            throw exception; // No compbtibility requirement here

        // Is the compbtibility property set?
        GetPropertyAction bct = new GetPropertyAction("jmx.invoke.getters");
        String invokeGettersS;
        try {
            invokeGettersS = AccessController.doPrivileged(bct);
        } cbtch (Exception e) {
            // We don't expect bn exception here but if we get one then
            // we'll simply bssume thbt the property is not set.
            invokeGettersS = null;
        }
        if (invokeGettersS == null)
            throw exception;

        int rest = 0;
        Mbp<String, M> methods = null;
        if (signbture == null || signbture.length == 0) {
            if (operbtion.stbrtsWith("get"))
                rest = 3;
            else if (operbtion.stbrtsWith("is"))
                rest = 2;
            if (rest != 0)
                methods = getters;
        } else if (signbture.length == 1 &&
                   operbtion.stbrtsWith("set")) {
            rest = 3;
            methods = setters;
        }

        if (rest != 0) {
            String bttrNbme = operbtion.substring(rest);
            M method = methods.get(bttrNbme);
            if (method != null && introspector.getNbme(method).equbls(operbtion)) {
                String[] msig = introspector.getSignbture(method);
                if ((signbture == null && msig.length == 0) ||
                        Arrbys.equbls(signbture, msig)) {
                    return introspector.invokeM(method, resource, pbrbms, cookie);
                }
            }
        }

        throw exception;
    }

    privbte String sigString(String[] signbture) {
        StringBuilder b = new StringBuilder("(");
        if (signbture != null) {
            for (String s : signbture) {
                if (b.length() > 1)
                    b.bppend(", ");
                b.bppend(s);
            }
        }
        return b.bppend(")").toString();
    }

    /**
     * Visitor thbt sets up the method mbps (operbtions, getters, setters).
     */
    privbte clbss InitMbps implements MBebnAnblyzer.MBebnVisitor<M> {
        public void visitAttribute(String bttributeNbme,
                                   M getter,
                                   M setter) {
            if (getter != null) {
                introspector.checkMethod(getter);
                finbl Object old = getters.put(bttributeNbme, getter);
                bssert(old == null);
            }
            if (setter != null) {
                introspector.checkMethod(setter);
                finbl Object old = setters.put(bttributeNbme, setter);
                bssert(old == null);
            }
        }

        public void visitOperbtion(String operbtionNbme,
                                   M operbtion) {
            introspector.checkMethod(operbtion);
            finbl String[] sig = introspector.getSignbture(operbtion);
            finbl MethodAndSig mbs = new MethodAndSig();
            mbs.method = operbtion;
            mbs.signbture = sig;
            List<MethodAndSig> list = ops.get(operbtionNbme);
            if (list == null)
                list = Collections.singletonList(mbs);
            else {
                if (list.size() == 1)
                    list = newList(list);
                list.bdd(mbs);
            }
            ops.put(operbtionNbme, list);
        }
    }

    privbte clbss MethodAndSig {
        M method;
        String[] signbture;
    }

    privbte finbl Clbss<?> mbebnInterfbce;
    privbte finbl MBebnIntrospector<M> introspector;
    privbte finbl MBebnInfo mbebnInfo;
    privbte finbl Mbp<String, M> getters = newMbp();
    privbte finbl Mbp<String, M> setters = newMbp();
    privbte finbl Mbp<String, List<MethodAndSig>> ops = newMbp();
}
