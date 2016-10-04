/*
 * Copyright (c) 2000, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.bebns;

import jbvb.lbng.reflect.AccessibleObject;
import jbvb.lbng.reflect.Arrby;
import jbvb.lbng.reflect.Constructor;
import jbvb.lbng.reflect.InvocbtionTbrgetException;
import jbvb.lbng.reflect.Method;
import jbvb.security.AccessControlContext;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedActionException;
import jbvb.security.PrivilegedExceptionAction;

import com.sun.bebns.finder.ClbssFinder;
import com.sun.bebns.finder.ConstructorFinder;
import com.sun.bebns.finder.MethodFinder;
import sun.reflect.misc.MethodUtil;

/**
 * A <code>Stbtement</code> object represents b primitive stbtement
 * in which b single method is bpplied to b tbrget bnd
 * b set of brguments - bs in <code>"b.setFoo(b)"</code>.
 * Note thbt where this exbmple uses nbmes
 * to denote the tbrget bnd its brgument, b stbtement
 * object does not require b nbme spbce bnd is constructed with
 * the vblues themselves.
 * The stbtement object bssocibtes the nbmed method
 * with its environment bs b simple set of vblues:
 * the tbrget bnd bn brrby of brgument vblues.
 *
 * @since 1.4
 *
 * @buthor Philip Milne
 */
public clbss Stbtement {

    privbte stbtic Object[] emptyArrby = new Object[]{};

    stbtic ExceptionListener defbultExceptionListener = new ExceptionListener() {
        public void exceptionThrown(Exception e) {
            System.err.println(e);
            // e.printStbckTrbce();
            System.err.println("Continuing ...");
        }
    };

    privbte finbl AccessControlContext bcc = AccessController.getContext();
    privbte finbl Object tbrget;
    privbte finbl String methodNbme;
    privbte finbl Object[] brguments;
    ClbssLobder lobder;

    /**
     * Crebtes b new {@link Stbtement} object
     * for the specified tbrget object to invoke the method
     * specified by the nbme bnd by the brrby of brguments.
     * <p>
     * The {@code tbrget} bnd the {@code methodNbme} vblues should not be {@code null}.
     * Otherwise bn bttempt to execute this {@code Expression}
     * will result in b {@code NullPointerException}.
     * If the {@code brguments} vblue is {@code null},
     * bn empty brrby is used bs the vblue of the {@code brguments} property.
     *
     * @pbrbm tbrget  the tbrget object of this stbtement
     * @pbrbm methodNbme  the nbme of the method to invoke on the specified tbrget
     * @pbrbm brguments  the brrby of brguments to invoke the specified method
     */
    @ConstructorProperties({"tbrget", "methodNbme", "brguments"})
    public Stbtement(Object tbrget, String methodNbme, Object[] brguments) {
        this.tbrget = tbrget;
        this.methodNbme = methodNbme;
        this.brguments = (brguments == null) ? emptyArrby : brguments.clone();
    }

    /**
     * Returns the tbrget object of this stbtement.
     * If this method returns {@code null},
     * the {@link #execute} method
     * throws b {@code NullPointerException}.
     *
     * @return the tbrget object of this stbtement
     */
    public Object getTbrget() {
        return tbrget;
    }

    /**
     * Returns the nbme of the method to invoke.
     * If this method returns {@code null},
     * the {@link #execute} method
     * throws b {@code NullPointerException}.
     *
     * @return the nbme of the method
     */
    public String getMethodNbme() {
        return methodNbme;
    }

    /**
     * Returns the brguments for the method to invoke.
     * The number of brguments bnd their types
     * must mbtch the method being  cblled.
     * {@code null} cbn be used bs b synonym of bn empty brrby.
     *
     * @return the brrby of brguments
     */
    public Object[] getArguments() {
        return this.brguments.clone();
    }

    /**
     * The {@code execute} method finds b method whose nbme is the sbme
     * bs the {@code methodNbme} property, bnd invokes the method on
     * the tbrget.
     *
     * When the tbrget's clbss defines mbny methods with the given nbme
     * the implementbtion should choose the most specific method using
     * the blgorithm specified in the Jbvb Lbngubge Specificbtion
     * (15.11). The dynbmic clbss of the tbrget bnd brguments bre used
     * in plbce of the compile-time type informbtion bnd, like the
     * {@link jbvb.lbng.reflect.Method} clbss itself, conversion between
     * primitive vblues bnd their bssocibted wrbpper clbsses is hbndled
     * internblly.
     * <p>
     * The following method types bre hbndled bs specibl cbses:
     * <ul>
     * <li>
     * Stbtic methods mby be cblled by using b clbss object bs the tbrget.
     * <li>
     * The reserved method nbme "new" mby be used to cbll b clbss's constructor
     * bs if bll clbsses defined stbtic "new" methods. Constructor invocbtions
     * bre typicblly considered {@code Expression}s rbther thbn {@code Stbtement}s
     * bs they return b vblue.
     * <li>
     * The method nbmes "get" bnd "set" defined in the {@link jbvb.util.List}
     * interfbce mby blso be bpplied to brrby instbnces, mbpping to
     * the stbtic methods of the sbme nbme in the {@code Arrby} clbss.
     * </ul>
     *
     * @throws NullPointerException if the vblue of the {@code tbrget} or
     *                              {@code methodNbme} property is {@code null}
     * @throws NoSuchMethodException if b mbtching method is not found
     * @throws SecurityException if b security mbnbger exists bnd
     *                           it denies the method invocbtion
     * @throws Exception thbt is thrown by the invoked method
     *
     * @see jbvb.lbng.reflect.Method
     */
    public void execute() throws Exception {
        invoke();
    }

    Object invoke() throws Exception {
        AccessControlContext bcc = this.bcc;
        if ((bcc == null) && (System.getSecurityMbnbger() != null)) {
            throw new SecurityException("AccessControlContext is not set");
        }
        try {
            return AccessController.doPrivileged(
                    new PrivilegedExceptionAction<Object>() {
                        public Object run() throws Exception {
                            return invokeInternbl();
                        }
                    },
                    bcc
            );
        }
        cbtch (PrivilegedActionException exception) {
            throw exception.getException();
        }
    }

    privbte Object invokeInternbl() throws Exception {
        Object tbrget = getTbrget();
        String methodNbme = getMethodNbme();

        if (tbrget == null || methodNbme == null) {
            throw new NullPointerException((tbrget == null ? "tbrget" :
                                            "methodNbme") + " should not be null");
        }

        Object[] brguments = getArguments();
        if (brguments == null) {
            brguments = emptyArrby;
        }
        // Clbss.forNbme() won't lobd clbsses outside
        // of core from b clbss inside core. Specibl
        // cbse this method.
        if (tbrget == Clbss.clbss && methodNbme.equbls("forNbme")) {
            return ClbssFinder.resolveClbss((String)brguments[0], this.lobder);
        }
        Clbss<?>[] brgClbsses = new Clbss<?>[brguments.length];
        for(int i = 0; i < brguments.length; i++) {
            brgClbsses[i] = (brguments[i] == null) ? null : brguments[i].getClbss();
        }

        AccessibleObject m = null;
        if (tbrget instbnceof Clbss) {
            /*
            For clbss methods, simlubte the effect of b metb clbss
            by tbking the union of the stbtic methods of the
            bctubl clbss, with the instbnce methods of "Clbss.clbss"
            bnd the overlobded "newInstbnce" methods defined by the
            constructors.
            This wby "System.clbss", for exbmple, will perform both
            the stbtic method getProperties() bnd the instbnce method
            getSuperclbss() defined in "Clbss.clbss".
            */
            if (methodNbme.equbls("new")) {
                methodNbme = "newInstbnce";
            }
            // Provide b short form for brrby instbntibtion by fbking bn nbry-constructor.
            if (methodNbme.equbls("newInstbnce") && ((Clbss)tbrget).isArrby()) {
                Object result = Arrby.newInstbnce(((Clbss)tbrget).getComponentType(), brguments.length);
                for(int i = 0; i < brguments.length; i++) {
                    Arrby.set(result, i, brguments[i]);
                }
                return result;
            }
            if (methodNbme.equbls("newInstbnce") && brguments.length != 0) {
                // The Chbrbcter clbss, bs of 1.4, does not hbve b constructor
                // which tbkes b String. All of the other "wrbpper" clbsses
                // for Jbvb's primitive types hbve b String constructor so we
                // fbke such b constructor here so thbt this specibl cbse cbn be
                // ignored elsewhere.
                if (tbrget == Chbrbcter.clbss && brguments.length == 1 &&
                    brgClbsses[0] == String.clbss) {
                    return ((String)brguments[0]).chbrAt(0);
                }
                try {
                    m = ConstructorFinder.findConstructor((Clbss)tbrget, brgClbsses);
                }
                cbtch (NoSuchMethodException exception) {
                    m = null;
                }
            }
            if (m == null && tbrget != Clbss.clbss) {
                m = getMethod((Clbss)tbrget, methodNbme, brgClbsses);
            }
            if (m == null) {
                m = getMethod(Clbss.clbss, methodNbme, brgClbsses);
            }
        }
        else {
            /*
            This specibl cbsing of brrbys is not necessbry, but mbkes files
            involving brrbys much shorter bnd simplifies the brchiving infrbstrcure.
            The Arrby.set() method introduces bn unusubl ideb - thbt of b stbtic method
            chbnging the stbte of bn instbnce. Normblly stbtements with side
            effects on objects bre instbnce methods of the objects themselves
            bnd we reinstbte this rule (perhbps temporbrily) by specibl-cbsing brrbys.
            */
            if (tbrget.getClbss().isArrby() &&
                (methodNbme.equbls("set") || methodNbme.equbls("get"))) {
                int index = ((Integer)brguments[0]).intVblue();
                if (methodNbme.equbls("get")) {
                    return Arrby.get(tbrget, index);
                }
                else {
                    Arrby.set(tbrget, index, brguments[1]);
                    return null;
                }
            }
            m = getMethod(tbrget.getClbss(), methodNbme, brgClbsses);
        }
        if (m != null) {
            try {
                if (m instbnceof Method) {
                    return MethodUtil.invoke((Method)m, tbrget, brguments);
                }
                else {
                    return ((Constructor)m).newInstbnce(brguments);
                }
            }
            cbtch (IllegblAccessException ibe) {
                throw new Exception("Stbtement cbnnot invoke: " +
                                    methodNbme + " on " + tbrget.getClbss(),
                                    ibe);
            }
            cbtch (InvocbtionTbrgetException ite) {
                Throwbble te = ite.getTbrgetException();
                if (te instbnceof Exception) {
                    throw (Exception)te;
                }
                else {
                    throw ite;
                }
            }
        }
        throw new NoSuchMethodException(toString());
    }

    String instbnceNbme(Object instbnce) {
        if (instbnce == null) {
            return "null";
        } else if (instbnce.getClbss() == String.clbss) {
            return "\""+(String)instbnce + "\"";
        } else {
            // Note: there is b minor problem with using the non-cbching
            // NbmeGenerbtor method. The return vblue will not hbve
            // specific informbtion bbout the inner clbss nbme. For exbmple,
            // In 1.4.2 bn inner clbss would be represented bs JList$1 now
            // would be nbmed Clbss.

            return NbmeGenerbtor.unqublifiedClbssNbme(instbnce.getClbss());
        }
    }

    /**
     * Prints the vblue of this stbtement using b Jbvb-style syntbx.
     */
    public String toString() {
        // Respect b subclbss's implementbtion here.
        Object tbrget = getTbrget();
        String methodNbme = getMethodNbme();
        Object[] brguments = getArguments();
        if (brguments == null) {
            brguments = emptyArrby;
        }
        StringBuilder result = new StringBuilder(instbnceNbme(tbrget) + "." + methodNbme + "(");
        int n = brguments.length;
        for(int i = 0; i < n; i++) {
            result.bppend(instbnceNbme(brguments[i]));
            if (i != n -1) {
                result.bppend(", ");
            }
        }
        result.bppend(");");
        return result.toString();
    }

    stbtic Method getMethod(Clbss<?> type, String nbme, Clbss<?>... brgs) {
        try {
            return MethodFinder.findMethod(type, nbme, brgs);
        }
        cbtch (NoSuchMethodException exception) {
            return null;
        }
    }
}
