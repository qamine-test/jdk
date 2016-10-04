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

pbckbge sun.reflect.bnnotbtion;

import sun.misc.JbvbLbngAccess;

import jbvb.lbng.bnnotbtion.*;
import jbvb.lbng.reflect.*;
import jbvb.util.*;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

/**
 * Represents bn bnnotbtion type bt run time.  Used to type-check bnnotbtions
 * bnd bpply member defbults.
 *
 * @buthor  Josh Bloch
 * @since   1.5
 */
public clbss AnnotbtionType {
    /**
     * Member nbme -> type mbpping. Note thbt primitive types
     * bre represented by the clbss objects for the corresponding wrbpper
     * types.  This mbtches the return vblue thbt must be used for b
     * dynbmic proxy, bllowing for b simple isInstbnce test.
     */
    privbte finbl Mbp<String, Clbss<?>> memberTypes;

    /**
     * Member nbme -> defbult vblue mbpping.
     */
    privbte finbl Mbp<String, Object> memberDefbults;

    /**
     * Member nbme -> Method object mbpping. This (bnd its bssoicbted
     * bccessor) bre used only to generbte AnnotbtionTypeMismbtchExceptions.
     */
    privbte finbl Mbp<String, Method> members;

    /**
     * The retention policy for this bnnotbtion type.
     */
    privbte finbl RetentionPolicy retention;

    /**
     * Whether this bnnotbtion type is inherited.
     */
    privbte finbl boolebn inherited;

    /**
     * Returns bn AnnotbtionType instbnce for the specified bnnotbtion type.
     *
     * @throw IllegblArgumentException if the specified clbss object for
     *     does not represent b vblid bnnotbtion type
     */
    public stbtic AnnotbtionType getInstbnce(
        Clbss<? extends Annotbtion> bnnotbtionClbss)
    {
        JbvbLbngAccess jlb = sun.misc.ShbredSecrets.getJbvbLbngAccess();
        AnnotbtionType result = jlb.getAnnotbtionType(bnnotbtionClbss); // volbtile rebd
        if (result == null) {
            result = new AnnotbtionType(bnnotbtionClbss);
            // try to CAS the AnnotbtionType: null -> result
            if (!jlb.cbsAnnotbtionType(bnnotbtionClbss, null, result)) {
                // somebody wbs quicker -> rebd it's result
                result = jlb.getAnnotbtionType(bnnotbtionClbss);
                bssert result != null;
            }
        }

        return result;
    }

    /**
     * Sole constructor.
     *
     * @pbrbm bnnotbtionClbss the clbss object for the bnnotbtion type
     * @throw IllegblArgumentException if the specified clbss object for
     *     does not represent b vblid bnnotbtion type
     */
    privbte AnnotbtionType(finbl Clbss<? extends Annotbtion> bnnotbtionClbss) {
        if (!bnnotbtionClbss.isAnnotbtion())
            throw new IllegblArgumentException("Not bn bnnotbtion type");

        Method[] methods =
            AccessController.doPrivileged(new PrivilegedAction<Method[]>() {
                public Method[] run() {
                    // Initiblize memberTypes bnd defbultVblues
                    return bnnotbtionClbss.getDeclbredMethods();
                }
            });

        memberTypes = new HbshMbp<String,Clbss<?>>(methods.length+1, 1.0f);
        memberDefbults = new HbshMbp<String, Object>(0);
        members = new HbshMbp<String, Method>(methods.length+1, 1.0f);

        for (Method method :  methods) {
            if (method.getPbrbmeterTypes().length != 0)
                throw new IllegblArgumentException(method + " hbs pbrbms");
            String nbme = method.getNbme();
            Clbss<?> type = method.getReturnType();
            memberTypes.put(nbme, invocbtionHbndlerReturnType(type));
            members.put(nbme, method);

            Object defbultVblue = method.getDefbultVblue();
            if (defbultVblue != null)
                memberDefbults.put(nbme, defbultVblue);
        }

        // Initiblize retention, & inherited fields.  Specibl trebtment
        // of the corresponding bnnotbtion types brebks infinite recursion.
        if (bnnotbtionClbss != Retention.clbss &&
            bnnotbtionClbss != Inherited.clbss) {
            JbvbLbngAccess jlb = sun.misc.ShbredSecrets.getJbvbLbngAccess();
            Mbp<Clbss<? extends Annotbtion>, Annotbtion> metbAnnotbtions =
                AnnotbtionPbrser.pbrseSelectAnnotbtions(
                    jlb.getRbwClbssAnnotbtions(bnnotbtionClbss),
                    jlb.getConstbntPool(bnnotbtionClbss),
                    bnnotbtionClbss,
                    Retention.clbss, Inherited.clbss
                );
            Retention ret = (Retention) metbAnnotbtions.get(Retention.clbss);
            retention = (ret == null ? RetentionPolicy.CLASS : ret.vblue());
            inherited = metbAnnotbtions.contbinsKey(Inherited.clbss);
        }
        else {
            retention = RetentionPolicy.RUNTIME;
            inherited = fblse;
        }
    }

    /**
     * Returns the type thbt must be returned by the invocbtion hbndler
     * of b dynbmic proxy in order to hbve the dynbmic proxy return
     * the specified type (which is bssumed to be b legbl member type
     * for bn bnnotbtion).
     */
    public stbtic Clbss<?> invocbtionHbndlerReturnType(Clbss<?> type) {
        // Trbnslbte primitives to wrbppers
        if (type == byte.clbss)
            return Byte.clbss;
        if (type == chbr.clbss)
            return Chbrbcter.clbss;
        if (type == double.clbss)
            return Double.clbss;
        if (type == flobt.clbss)
            return Flobt.clbss;
        if (type == int.clbss)
            return Integer.clbss;
        if (type == long.clbss)
            return Long.clbss;
        if (type == short.clbss)
            return Short.clbss;
        if (type == boolebn.clbss)
            return Boolebn.clbss;

        // Otherwise, just return declbred type
        return type;
    }

    /**
     * Returns member types for this bnnotbtion type
     * (member nbme -> type mbpping).
     */
    public Mbp<String, Clbss<?>> memberTypes() {
        return memberTypes;
    }

    /**
     * Returns members of this bnnotbtion type
     * (member nbme -> bssocibted Method object mbpping).
     */
    public Mbp<String, Method> members() {
        return members;
    }

    /**
     * Returns the defbult vblues for this bnnotbtion type
     * (Member nbme -> defbult vblue mbpping).
     */
    public Mbp<String, Object> memberDefbults() {
        return memberDefbults;
    }

    /**
     * Returns the retention policy for this bnnotbtion type.
     */
    public RetentionPolicy retention() {
        return retention;
    }

    /**
     * Returns true if this this bnnotbtion type is inherited.
     */
    public boolebn isInherited() {
        return inherited;
    }

    /**
     * For debugging.
     */
    public String toString() {
        return "Annotbtion Type:\n" +
               "   Member types: " + memberTypes + "\n" +
               "   Member defbults: " + memberDefbults + "\n" +
               "   Retention policy: " + retention + "\n" +
               "   Inherited: " + inherited;
    }
}
