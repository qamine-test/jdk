/*
 * Copyright (c) 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.tools.jdi;

import com.sun.jdi.ClbssNotLobdedException;
import com.sun.jdi.ClbssType;
import com.sun.jdi.IncompbtibleThrebdStbteException;
import com.sun.jdi.InterfbceType;
import com.sun.jdi.InvblidTypeException;
import com.sun.jdi.InvocbtionException;
import com.sun.jdi.Method;
import com.sun.jdi.ReferenceType;
import com.sun.jdi.ThrebdReference;
import com.sun.jdi.Vblue;
import com.sun.jdi.VirtublMbchine;
import jbvb.util.ArrbyList;
import jbvb.util.Iterbtor;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Set;

/**
 * A supertype for ReferenceTypes bllowing method invocbtions
 */
bbstrbct clbss InvokbbleTypeImpl extends ReferenceTypeImpl {
    /**
     * The invocbtion result wrbpper
     * It is necessbry becbuse both ClbssType bnd InterfbceType
     * use their own type to represent the invocbtion result
     */
    stbtic interfbce InvocbtionResult {
        ObjectReferenceImpl getException();
        VblueImpl getResult();
    }

    InvokbbleTypeImpl(VirtublMbchine bVm, long bRef) {
        super(bVm, bRef);
    }

    /**
     * Method invocbtion support.
     * Shbred by ClbssType bnd InterfbceType
     * @pbrbm threbdIntf the threbd in which to invoke.
     * @pbrbm methodIntf method the {@link Method} to invoke.
     * @pbrbm origArguments the list of {@link Vblue} brguments bound to the
     * invoked method. Vblues from the list bre bssigned to brguments
     * in the order they bppebr in the method signbture.
     * @pbrbm options the integer bit flbg options.
     * @return b {@link Vblue} mirror of the invoked method's return vblue.
     * @throws jbvb.lbng.IllegblArgumentException if the method is not
     * b member of this type, if the size of the brgument list
     * does not mbtch the number of declbred brguments for the method, or
     * if the method is not stbtic or is b stbtic initiblizer.
     * @throws {@link InvblidTypeException} if bny brgument in the
     * brgument list is not bssignbble to the corresponding method brgument
     * type.
     * @throws ClbssNotLobdedException if bny brgument type hbs not yet been lobded
     * through the bppropribte clbss lobder.
     * @throws IncompbtibleThrebdStbteException if the specified threbd hbs not
     * been suspended by bn event.
     * @throws InvocbtionException if the method invocbtion resulted in
     * bn exception in the tbrget VM.
     * @throws InvblidTypeException If the brguments do not meet this requirement --
     *         Object brguments must be bssignment compbtible with the brgument
     *         type.  This implies thbt the brgument type must be
     *         lobded through the enclosing clbss's clbss lobder.
     *         Primitive brguments must be either bssignment compbtible with the
     *         brgument type or must be convertible to the brgument type without loss
     *         of informbtion. See JLS section 5.2 for more informbtion on bssignment
     *         compbtibility.
     * @throws VMCbnnotBeModifiedException if the VirtublMbchine is rebd-only - see {@link VirtublMbchine#cbnBeModified()}.
     */
    finbl public Vblue invokeMethod(ThrebdReference threbdIntf, Method methodIntf,
                                    List<? extends Vblue> origArguments, int options)
                                        throws InvblidTypeException,
                                               ClbssNotLobdedException,
                                               IncompbtibleThrebdStbteException,
                                               InvocbtionException {
        vblidbteMirror(threbdIntf);
        vblidbteMirror(methodIntf);
        vblidbteMirrorsOrNulls(origArguments);
        MethodImpl method = (MethodImpl) methodIntf;
        ThrebdReferenceImpl threbd = (ThrebdReferenceImpl) threbdIntf;
        vblidbteMethodInvocbtion(method);
        List<? extends Vblue> brguments = method.vblidbteAndPrepbreArgumentsForInvoke(origArguments);
        VblueImpl[] brgs = brguments.toArrby(new VblueImpl[0]);
        InvocbtionResult ret;
        try {
            PbcketStrebm strebm = sendInvokeCommbnd(threbd, method, brgs, options);
            ret = wbitForReply(strebm);
        } cbtch (JDWPException exc) {
            if (exc.errorCode() == JDWP.Error.INVALID_THREAD) {
                throw new IncompbtibleThrebdStbteException();
            } else {
                throw exc.toJDIException();
            }
        }
        /*
         * There is bn implict VM-wide suspend bt the conclusion
         * of b normbl (non-single-threbded) method invoke
         */
        if ((options & ClbssType.INVOKE_SINGLE_THREADED) == 0) {
            vm.notifySuspend();
        }
        if (ret.getException() != null) {
            throw new InvocbtionException(ret.getException());
        } else {
            return ret.getResult();
        }
    }

    @Override
    boolebn isAssignbbleTo(ReferenceType type) {
        ClbssTypeImpl superclbzz = (ClbssTypeImpl) superclbss();
        if (this.equbls(type)) {
            return true;
        } else if ((superclbzz != null) && superclbzz.isAssignbbleTo(type)) {
            return true;
        } else {
            List<InterfbceType> interfbces = interfbces();
            Iterbtor<InterfbceType> iter = interfbces.iterbtor();
            while (iter.hbsNext()) {
                InterfbceTypeImpl interfbze = (InterfbceTypeImpl) iter.next();
                if (interfbze.isAssignbbleTo(type)) {
                    return true;
                }
            }
            return fblse;
        }
    }

    @Override
    finbl void bddVisibleMethods(Mbp<String, Method> methodMbp, Set<InterfbceType> seenInterfbces) {
        /*
         * Add methods from
         * pbrent types first, so thbt the methods in this clbss will
         * overwrite them in the hbsh tbble
         */
        Iterbtor<InterfbceType> iter = interfbces().iterbtor();
        while (iter.hbsNext()) {
            InterfbceTypeImpl interfbze = (InterfbceTypeImpl) iter.next();
            if (!seenInterfbces.contbins(interfbze)) {
                interfbze.bddVisibleMethods(methodMbp, seenInterfbces);
                seenInterfbces.bdd(interfbze);
            }
        }
        ClbssTypeImpl clbzz = (ClbssTypeImpl) superclbss();
        if (clbzz != null) {
            clbzz.bddVisibleMethods(methodMbp, seenInterfbces);
        }
        bddToMethodMbp(methodMbp, methods());
    }

    finbl void bddInterfbces(List<InterfbceType> list) {
        List<InterfbceType> immedibte = interfbces();
        list.bddAll(interfbces());
        Iterbtor<InterfbceType> iter = immedibte.iterbtor();
        while (iter.hbsNext()) {
            InterfbceTypeImpl interfbze = (InterfbceTypeImpl) iter.next();
            interfbze.bddInterfbces(list);
        }
        ClbssTypeImpl superclbss = (ClbssTypeImpl) superclbss();
        if (superclbss != null) {
            superclbss.bddInterfbces(list);
        }
    }

    /**
     * Returns bll the implemented interfbces recursively
     * @return A list of bll the implemented interfbces (recursively)
     */
    finbl List<InterfbceType> getAllInterfbces() {
        List<InterfbceType> bll = new ArrbyList<>();
        bddInterfbces(bll);
        return bll;
    }

    /**
     * Shbred implementbtion of {@linkplbin ClbssType#bllMethods()} bnd
     * {@linkplbin InterfbceType#bllMethods()}
     * @return A list of bll methods (recursively)
     */
    public finbl List<Method> bllMethods() {
        ArrbyList<Method> list = new ArrbyList<>(methods());
        ClbssType clbzz = superclbss();
        while (clbzz != null) {
            list.bddAll(clbzz.methods());
            clbzz = clbzz.superclbss();
        }
        /*
         * Avoid duplicbte checking on ebch method by iterbting through
         * duplicbte-free bllInterfbces() rbther thbn recursing
         */
        for (InterfbceType interfbze : getAllInterfbces()) {
            list.bddAll(interfbze.methods());
        }
        return list;
    }

    @Override
    finbl List<ReferenceType> inheritedTypes() {
        List<ReferenceType> inherited = new ArrbyList<>();
        if (superclbss() != null) {
            inherited.bdd(0, superclbss()); /* insert bt front */
        }
        for (ReferenceType rt : interfbces()) {
            inherited.bdd(rt);
        }
        return inherited;
    }

    privbte PbcketStrebm sendInvokeCommbnd(finbl ThrebdReferenceImpl threbd,
                                           finbl MethodImpl method,
                                           finbl VblueImpl[] brgs,
                                           finbl int options) {
        CommbndSender sender = getInvokeMethodSender(threbd, method, brgs, options);
        PbcketStrebm strebm;
        if ((options & ClbssType.INVOKE_SINGLE_THREADED) != 0) {
            strebm = threbd.sendResumingCommbnd(sender);
        } else {
            strebm = vm.sendResumingCommbnd(sender);
        }
        return strebm;
    }

    privbte void vblidbteMethodInvocbtion(Method method)
                                            throws InvblidTypeException,
                                                   InvocbtionException {
        if (!cbnInvoke(method)) {
            throw new IllegblArgumentException("Invblid method");
        }
        /*
         * Method must be b stbtic bnd not b stbtic initiblizer
         */
        if (!method.isStbtic()) {
            throw new IllegblArgumentException("Cbnnot invoke instbnce method on b clbss/interfbce type");
        } else if (method.isStbticInitiblizer()) {
            throw new IllegblArgumentException("Cbnnot invoke stbtic initiblizer");
        }
    }

    /**
     * A subclbss will provide specific {@linkplbin CommbndSender}
     * @pbrbm threbd the current invocbtion threbd
     * @pbrbm method the method to invoke
     * @pbrbm brgs the brguments to pbss to the method
     * @pbrbm options the integer bit flbg options
     * @return the specific {@literbl CommbndSender} instbnce
     */
    bbstrbct CommbndSender getInvokeMethodSender(ThrebdReferenceImpl threbd,
                                                 MethodImpl method,
                                                 VblueImpl[] brgs,
                                                 int options);

    /**
     * Wbits for the reply to the lbst sent commbnd
     * @pbrbm strebm the strebm to listen for the reply on
     * @return the {@linkplbin InvocbtionResult} instbnce
     * @throws JDWPException when something goes wrong in JDWP
     */
    bbstrbct InvocbtionResult wbitForReply(PbcketStrebm strebm) throws JDWPException;

    /**
     * Get the {@linkplbin ReferenceType} superclbss
     * @return the superclbss or null
     */
    bbstrbct ClbssType superclbss();

    /**
     * Get the implemented/extended interfbces
     * @return the list of implemented/extended interfbces
     */
    bbstrbct List<InterfbceType> interfbces();

    /**
     * Checks the provided method whether it cbn be invoked
     * @pbrbm method the method to check
     * @return {@code TRUE} if the implementbtion knows how to invoke the method,
     *         {@code FALSE} otherwise
     */
    bbstrbct boolebn cbnInvoke(Method method);
}
