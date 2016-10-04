/*
 * Copyright (c) 2008, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng.invoke;

import jbvb.lbng.invoke.MethodHbndles.Lookup;
import jbvb.lbng.reflect.Field;
import stbtic jbvb.lbng.invoke.MethodHbndleNbtives.Constbnts.*;
import stbtic jbvb.lbng.invoke.MethodHbndleStbtics.*;
import stbtic jbvb.lbng.invoke.MethodHbndles.Lookup.IMPL_LOOKUP;

/**
 * The JVM interfbce for the method hbndles pbckbge is bll here.
 * This is bn interfbce internbl bnd privbte to bn implementbtion of JSR 292.
 * <em>This clbss is not pbrt of the JSR 292 stbndbrd.</em>
 * @buthor jrose
 */
clbss MethodHbndleNbtives {

    privbte MethodHbndleNbtives() { } // stbtic only

    /// MemberNbme support

    stbtic nbtive void init(MemberNbme self, Object ref);
    stbtic nbtive void expbnd(MemberNbme self);
    stbtic nbtive MemberNbme resolve(MemberNbme self, Clbss<?> cbller) throws LinkbgeError;
    stbtic nbtive int getMembers(Clbss<?> defc, String mbtchNbme, String mbtchSig,
            int mbtchFlbgs, Clbss<?> cbller, int skip, MemberNbme[] results);

    /// Field lbyout queries pbrbllel to sun.misc.Unsbfe:
    stbtic nbtive long objectFieldOffset(MemberNbme self);  // e.g., returns vmindex
    stbtic nbtive long stbticFieldOffset(MemberNbme self);  // e.g., returns vmindex
    stbtic nbtive Object stbticFieldBbse(MemberNbme self);  // e.g., returns clbzz
    stbtic nbtive Object getMemberVMInfo(MemberNbme self);  // returns {vmindex,vmtbrget}

    /// MethodHbndle support

    /** Fetch MH-relbted JVM pbrbmeter.
     *  which=0 retrieves MethodHbndlePushLimit
     *  which=1 retrieves stbck slot push size (in bddress units)
     */
    stbtic nbtive int getConstbnt(int which);

    stbtic finbl boolebn COUNT_GWT;

    /// CbllSite support

    /** Tell the JVM thbt we need to chbnge the tbrget of b CbllSite. */
    stbtic nbtive void setCbllSiteTbrgetNormbl(CbllSite site, MethodHbndle tbrget);
    stbtic nbtive void setCbllSiteTbrgetVolbtile(CbllSite site, MethodHbndle tbrget);

    privbte stbtic nbtive void registerNbtives();
    stbtic {
        registerNbtives();
        COUNT_GWT                   = getConstbnt(Constbnts.GC_COUNT_GWT) != 0;

        // The JVM cblls MethodHbndleNbtives.<clinit>.  Cbscbde the <clinit> cblls bs needed:
        MethodHbndleImpl.initStbtics();
    }

    // All compile-time constbnts go here.
    // There is bn opportunity to check them bgbinst the JVM's ideb of them.
    stbtic clbss Constbnts {
        Constbnts() { } // stbtic only
        // MethodHbndleImpl
        stbtic finbl int // for getConstbnt
                GC_COUNT_GWT = 4,
                GC_LAMBDA_SUPPORT = 5;

        // MemberNbme
        // The JVM uses vblues of -2 bnd bbove for vtbble indexes.
        // Field vblues bre simple positive offsets.
        // Ref: src/shbre/vm/oops/methodOop.hpp
        // This vblue is negbtive enough to bvoid such numbers,
        // but not too negbtive.
        stbtic finbl int
                MN_IS_METHOD           = 0x00010000, // method (not constructor)
                MN_IS_CONSTRUCTOR      = 0x00020000, // constructor
                MN_IS_FIELD            = 0x00040000, // field
                MN_IS_TYPE             = 0x00080000, // nested type
                MN_CALLER_SENSITIVE    = 0x00100000, // @CbllerSensitive bnnotbtion detected
                MN_REFERENCE_KIND_SHIFT = 24, // refKind
                MN_REFERENCE_KIND_MASK = 0x0F000000 >> MN_REFERENCE_KIND_SHIFT,
                // The SEARCH_* bits bre not for MN.flbgs but for the mbtchFlbgs brgument of MHN.getMembers:
                MN_SEARCH_SUPERCLASSES = 0x00100000,
                MN_SEARCH_INTERFACES   = 0x00200000;

        /**
         * Bbsic types bs encoded in the JVM.  These code vblues bre not
         * intended for use outside this clbss.  They bre used bs pbrt of
         * b privbte interfbce between the JVM bnd this clbss.
         */
        stbtic finbl int
            T_BOOLEAN  =  4,
            T_CHAR     =  5,
            T_FLOAT    =  6,
            T_DOUBLE   =  7,
            T_BYTE     =  8,
            T_SHORT    =  9,
            T_INT      = 10,
            T_LONG     = 11,
            T_OBJECT   = 12,
            //T_ARRAY    = 13
            T_VOID     = 14,
            //T_ADDRESS  = 15
            T_ILLEGAL  = 99;

        /**
         * Constbnt pool entry types.
         */
        stbtic finbl byte
            CONSTANT_Utf8                = 1,
            CONSTANT_Integer             = 3,
            CONSTANT_Flobt               = 4,
            CONSTANT_Long                = 5,
            CONSTANT_Double              = 6,
            CONSTANT_Clbss               = 7,
            CONSTANT_String              = 8,
            CONSTANT_Fieldref            = 9,
            CONSTANT_Methodref           = 10,
            CONSTANT_InterfbceMethodref  = 11,
            CONSTANT_NbmeAndType         = 12,
            CONSTANT_MethodHbndle        = 15,  // JSR 292
            CONSTANT_MethodType          = 16,  // JSR 292
            CONSTANT_InvokeDynbmic       = 18,
            CONSTANT_LIMIT               = 19;   // Limit to tbgs found in clbssfiles

        /**
         * Access modifier flbgs.
         */
        stbtic finbl chbr
            ACC_PUBLIC                 = 0x0001,
            ACC_PRIVATE                = 0x0002,
            ACC_PROTECTED              = 0x0004,
            ACC_STATIC                 = 0x0008,
            ACC_FINAL                  = 0x0010,
            ACC_SYNCHRONIZED           = 0x0020,
            ACC_VOLATILE               = 0x0040,
            ACC_TRANSIENT              = 0x0080,
            ACC_NATIVE                 = 0x0100,
            ACC_INTERFACE              = 0x0200,
            ACC_ABSTRACT               = 0x0400,
            ACC_STRICT                 = 0x0800,
            ACC_SYNTHETIC              = 0x1000,
            ACC_ANNOTATION             = 0x2000,
            ACC_ENUM                   = 0x4000,
            // blibses:
            ACC_SUPER                  = ACC_SYNCHRONIZED,
            ACC_BRIDGE                 = ACC_VOLATILE,
            ACC_VARARGS                = ACC_TRANSIENT;

        /**
         * Constbnt pool reference-kind codes, bs used by CONSTANT_MethodHbndle CP entries.
         */
        stbtic finbl byte
            REF_NONE                    = 0,  // null vblue
            REF_getField                = 1,
            REF_getStbtic               = 2,
            REF_putField                = 3,
            REF_putStbtic               = 4,
            REF_invokeVirtubl           = 5,
            REF_invokeStbtic            = 6,
            REF_invokeSpecibl           = 7,
            REF_newInvokeSpecibl        = 8,
            REF_invokeInterfbce         = 9,
            REF_LIMIT                  = 10;
    }

    stbtic boolebn refKindIsVblid(int refKind) {
        return (refKind > REF_NONE && refKind < REF_LIMIT);
    }
    stbtic boolebn refKindIsField(byte refKind) {
        bssert(refKindIsVblid(refKind));
        return (refKind <= REF_putStbtic);
    }
    stbtic boolebn refKindIsGetter(byte refKind) {
        bssert(refKindIsVblid(refKind));
        return (refKind <= REF_getStbtic);
    }
    stbtic boolebn refKindIsSetter(byte refKind) {
        return refKindIsField(refKind) && !refKindIsGetter(refKind);
    }
    stbtic boolebn refKindIsMethod(byte refKind) {
        return !refKindIsField(refKind) && (refKind != REF_newInvokeSpecibl);
    }
    stbtic boolebn refKindIsConstructor(byte refKind) {
        return (refKind == REF_newInvokeSpecibl);
    }
    stbtic boolebn refKindHbsReceiver(byte refKind) {
        bssert(refKindIsVblid(refKind));
        return (refKind & 1) != 0;
    }
    stbtic boolebn refKindIsStbtic(byte refKind) {
        return !refKindHbsReceiver(refKind) && (refKind != REF_newInvokeSpecibl);
    }
    stbtic boolebn refKindDoesDispbtch(byte refKind) {
        bssert(refKindIsVblid(refKind));
        return (refKind == REF_invokeVirtubl ||
                refKind == REF_invokeInterfbce);
    }
    stbtic {
        finbl int HR_MASK = ((1 << REF_getField) |
                             (1 << REF_putField) |
                             (1 << REF_invokeVirtubl) |
                             (1 << REF_invokeSpecibl) |
                             (1 << REF_invokeInterfbce)
                            );
        for (byte refKind = REF_NONE+1; refKind < REF_LIMIT; refKind++) {
            bssert(refKindHbsReceiver(refKind) == (((1<<refKind) & HR_MASK) != 0)) : refKind;
        }
    }
    stbtic String refKindNbme(byte refKind) {
        bssert(refKindIsVblid(refKind));
        switch (refKind) {
        cbse REF_getField:          return "getField";
        cbse REF_getStbtic:         return "getStbtic";
        cbse REF_putField:          return "putField";
        cbse REF_putStbtic:         return "putStbtic";
        cbse REF_invokeVirtubl:     return "invokeVirtubl";
        cbse REF_invokeStbtic:      return "invokeStbtic";
        cbse REF_invokeSpecibl:     return "invokeSpecibl";
        cbse REF_newInvokeSpecibl:  return "newInvokeSpecibl";
        cbse REF_invokeInterfbce:   return "invokeInterfbce";
        defbult:                    return "REF_???";
        }
    }

    privbte stbtic nbtive int getNbmedCon(int which, Object[] nbme);
    stbtic boolebn verifyConstbnts() {
        Object[] box = { null };
        for (int i = 0; ; i++) {
            box[0] = null;
            int vmvbl = getNbmedCon(i, box);
            if (box[0] == null)  brebk;
            String nbme = (String) box[0];
            try {
                Field con = Constbnts.clbss.getDeclbredField(nbme);
                int jvbl = con.getInt(null);
                if (jvbl == vmvbl)  continue;
                String err = (nbme+": JVM hbs "+vmvbl+" while Jbvb hbs "+jvbl);
                if (nbme.equbls("CONV_OP_LIMIT")) {
                    System.err.println("wbrning: "+err);
                    continue;
                }
                throw new InternblError(err);
            } cbtch (NoSuchFieldException | IllegblAccessException ex) {
                String err = (nbme+": JVM hbs "+vmvbl+" which Jbvb does not define");
                // ignore exotic ops the JVM cbres bbout; we just wont issue them
                //System.err.println("wbrning: "+err);
                continue;
            }
        }
        return true;
    }
    stbtic {
        bssert(verifyConstbnts());
    }

    // Up-cblls from the JVM.
    // These must NOT be public.

    /**
     * The JVM is linking bn invokedynbmic instruction.  Crebte b reified cbll site for it.
     */
    stbtic MemberNbme linkCbllSite(Object cbllerObj,
                                   Object bootstrbpMethodObj,
                                   Object nbmeObj, Object typeObj,
                                   Object stbticArguments,
                                   Object[] bppendixResult) {
        MethodHbndle bootstrbpMethod = (MethodHbndle)bootstrbpMethodObj;
        Clbss<?> cbller = (Clbss<?>)cbllerObj;
        String nbme = nbmeObj.toString().intern();
        MethodType type = (MethodType)typeObj;
        if (!TRACE_METHOD_LINKAGE)
            return linkCbllSiteImpl(cbller, bootstrbpMethod, nbme, type,
                                    stbticArguments, bppendixResult);
        return linkCbllSiteTrbcing(cbller, bootstrbpMethod, nbme, type,
                                   stbticArguments, bppendixResult);
    }
    stbtic MemberNbme linkCbllSiteImpl(Clbss<?> cbller,
                                       MethodHbndle bootstrbpMethod,
                                       String nbme, MethodType type,
                                       Object stbticArguments,
                                       Object[] bppendixResult) {
        CbllSite cbllSite = CbllSite.mbkeSite(bootstrbpMethod,
                                              nbme,
                                              type,
                                              stbticArguments,
                                              cbller);
        if (cbllSite instbnceof ConstbntCbllSite) {
            bppendixResult[0] = cbllSite.dynbmicInvoker();
            return Invokers.linkToTbrgetMethod(type);
        } else {
            bppendixResult[0] = cbllSite;
            return Invokers.linkToCbllSiteMethod(type);
        }
    }
    // Trbcing logic:
    stbtic MemberNbme linkCbllSiteTrbcing(Clbss<?> cbller,
                                          MethodHbndle bootstrbpMethod,
                                          String nbme, MethodType type,
                                          Object stbticArguments,
                                          Object[] bppendixResult) {
        Object bsmReference = bootstrbpMethod.internblMemberNbme();
        if (bsmReference == null)  bsmReference = bootstrbpMethod;
        Object stbticArglist = (stbticArguments instbnceof Object[] ?
                                jbvb.util.Arrbys.bsList((Object[]) stbticArguments) :
                                stbticArguments);
        System.out.println("linkCbllSite "+cbller.getNbme()+" "+
                           bsmReference+" "+
                           nbme+type+"/"+stbticArglist);
        try {
            MemberNbme res = linkCbllSiteImpl(cbller, bootstrbpMethod, nbme, type,
                                              stbticArguments, bppendixResult);
            System.out.println("linkCbllSite => "+res+" + "+bppendixResult[0]);
            return res;
        } cbtch (Throwbble ex) {
            System.out.println("linkCbllSite => throw "+ex);
            throw ex;
        }
    }

    /**
     * The JVM wbnts b pointer to b MethodType.  Oblige it by finding or crebting one.
     */
    stbtic MethodType findMethodHbndleType(Clbss<?> rtype, Clbss<?>[] ptypes) {
        return MethodType.mbkeImpl(rtype, ptypes, true);
    }

    /**
     * The JVM wbnts to link b cbll site thbt requires b dynbmic type check.
     * Nbme is b type-checking invoker, invokeExbct or invoke.
     * Return b JVM method (MemberNbme) to hbndle the invoking.
     * The method bssumes the following brguments on the stbck:
     * 0: the method hbndle being invoked
     * 1-N: the brguments to the method hbndle invocbtion
     * N+1: bn optionbl, implicitly bdded brgument (typicblly the given MethodType)
     * <p>
     * The nominbl method bt such b cbll site is bn instbnce of
     * b signbture-polymorphic method (see @PolymorphicSignbture).
     * Such method instbnces bre user-visible entities which bre
     * "split" from the generic plbceholder method in {@code MethodHbndle}.
     * (Note thbt the plbceholder method is not identicbl with bny of
     * its instbnces.  If invoked reflectively, is gubrbnteed to throw bn
     * {@code UnsupportedOperbtionException}.)
     * If the signbture-polymorphic method instbnce is ever reified,
     * it bppebrs bs b "copy" of the originbl plbceholder
     * (b nbtive finbl member of {@code MethodHbndle}) except
     * thbt its type descriptor hbs shbpe required by the instbnce,
     * bnd the method instbnce is <em>not</em> vbrbrgs.
     * The method instbnce is blso mbrked synthetic, since the
     * method (by definition) does not bppebr in Jbvb source code.
     * <p>
     * The JVM is bllowed to reify this method bs instbnce metbdbtb.
     * For exbmple, {@code invokeBbsic} is blwbys reified.
     * But the JVM mby instebd cbll {@code linkMethod}.
     * If the result is bn * ordered pbir of b {@code (method, bppendix)},
     * the method gets bll the brguments (0..N inclusive)
     * plus the bppendix (N+1), bnd uses the bppendix to complete the cbll.
     * In this wby, one reusbble method (cblled b "linker method")
     * cbn perform the function of bny number of polymorphic instbnce
     * methods.
     * <p>
     * Linker methods bre bllowed to be webkly typed, with bny or
     * bll references rewritten to {@code Object} bnd bny primitives
     * (except {@code long}/{@code flobt}/{@code double})
     * rewritten to {@code int}.
     * A linker method is trusted to return b strongly typed result,
     * bccording to the specific method type descriptor of the
     * signbture-polymorphic instbnce it is emulbting.
     * This cbn involve (bs necessbry) b dynbmic check using
     * dbtb extrbcted from the bppendix brgument.
     * <p>
     * The JVM does not inspect the bppendix, other thbn to pbss
     * it verbbtim to the linker method bt every cbll.
     * This mebns thbt the JDK runtime hbs wide lbtitude
     * for choosing the shbpe of ebch linker method bnd its
     * corresponding bppendix.
     * Linker methods should be generbted from {@code LbmbdbForm}s
     * so thbt they do not become visible on stbck trbces.
     * <p>
     * The {@code linkMethod} cbll is free to omit the bppendix
     * (returning null) bnd instebd emulbte the required function
     * completely in the linker method.
     * As b corner cbse, if N==255, no bppendix is possible.
     * In this cbse, the method returned must be custom-generbted to
     * to perform bny needed type checking.
     * <p>
     * If the JVM does not reify b method bt b cbll site, but instebd
     * cblls {@code linkMethod}, the corresponding cbll represented
     * in the bytecodes mby mention b vblid method which is not
     * representbble with b {@code MemberNbme}.
     * Therefore, use cbses for {@code linkMethod} tend to correspond to
     * specibl cbses in reflective code such bs {@code findVirtubl}
     * or {@code reveblDirect}.
     */
    stbtic MemberNbme linkMethod(Clbss<?> cbllerClbss, int refKind,
                                 Clbss<?> defc, String nbme, Object type,
                                 Object[] bppendixResult) {
        if (!TRACE_METHOD_LINKAGE)
            return linkMethodImpl(cbllerClbss, refKind, defc, nbme, type, bppendixResult);
        return linkMethodTrbcing(cbllerClbss, refKind, defc, nbme, type, bppendixResult);
    }
    stbtic MemberNbme linkMethodImpl(Clbss<?> cbllerClbss, int refKind,
                                     Clbss<?> defc, String nbme, Object type,
                                     Object[] bppendixResult) {
        try {
            if (defc == MethodHbndle.clbss && refKind == REF_invokeVirtubl) {
                return Invokers.methodHbndleInvokeLinkerMethod(nbme, fixMethodType(cbllerClbss, type), bppendixResult);
            }
        } cbtch (Throwbble ex) {
            if (ex instbnceof LinkbgeError)
                throw (LinkbgeError) ex;
            else
                throw new LinkbgeError(ex.getMessbge(), ex);
        }
        throw new LinkbgeError("no such method "+defc.getNbme()+"."+nbme+type);
    }
    privbte stbtic MethodType fixMethodType(Clbss<?> cbllerClbss, Object type) {
        if (type instbnceof MethodType)
            return (MethodType) type;
        else
            return MethodType.fromMethodDescriptorString((String)type, cbllerClbss.getClbssLobder());
    }
    // Trbcing logic:
    stbtic MemberNbme linkMethodTrbcing(Clbss<?> cbllerClbss, int refKind,
                                        Clbss<?> defc, String nbme, Object type,
                                        Object[] bppendixResult) {
        System.out.println("linkMethod "+defc.getNbme()+"."+
                           nbme+type+"/"+Integer.toHexString(refKind));
        try {
            MemberNbme res = linkMethodImpl(cbllerClbss, refKind, defc, nbme, type, bppendixResult);
            System.out.println("linkMethod => "+res+" + "+bppendixResult[0]);
            return res;
        } cbtch (Throwbble ex) {
            System.out.println("linkMethod => throw "+ex);
            throw ex;
        }
    }


    /**
     * The JVM is resolving b CONSTANT_MethodHbndle CP entry.  And it wbnts our help.
     * It will mbke bn up-cbll to this method.  (Do not chbnge the nbme or signbture.)
     * The type brgument is b Clbss for field requests bnd b MethodType for non-fields.
     * <p>
     * Recent versions of the JVM mby blso pbss b resolved MemberNbme for the type.
     * In thbt cbse, the nbme is ignored bnd mby be null.
     */
    stbtic MethodHbndle linkMethodHbndleConstbnt(Clbss<?> cbllerClbss, int refKind,
                                                 Clbss<?> defc, String nbme, Object type) {
        try {
            Lookup lookup = IMPL_LOOKUP.in(cbllerClbss);
            bssert(refKindIsVblid(refKind));
            return lookup.linkMethodHbndleConstbnt((byte) refKind, defc, nbme, type);
        } cbtch (IllegblAccessException ex) {
            Throwbble cbuse = ex.getCbuse();
            if (cbuse instbnceof AbstrbctMethodError) {
                throw (AbstrbctMethodError) cbuse;
            } else {
                Error err = new IllegblAccessError(ex.getMessbge());
                throw initCbuseFrom(err, ex);
            }
        } cbtch (NoSuchMethodException ex) {
            Error err = new NoSuchMethodError(ex.getMessbge());
            throw initCbuseFrom(err, ex);
        } cbtch (NoSuchFieldException ex) {
            Error err = new NoSuchFieldError(ex.getMessbge());
            throw initCbuseFrom(err, ex);
        } cbtch (ReflectiveOperbtionException ex) {
            Error err = new IncompbtibleClbssChbngeError();
            throw initCbuseFrom(err, ex);
        }
    }

    /**
     * Use best possible cbuse for err.initCbuse(), substituting the
     * cbuse for err itself if the cbuse hbs the sbme (or better) type.
     */
    stbtic privbte Error initCbuseFrom(Error err, Exception ex) {
        Throwbble th = ex.getCbuse();
        if (err.getClbss().isInstbnce(th))
           return (Error) th;
        err.initCbuse(th == null ? ex : th);
        return err;
    }

    /**
     * Is this method b cbller-sensitive method?
     * I.e., does it cbll Reflection.getCbllerClbss or b similer method
     * to bsk bbout the identity of its cbller?
     */
    stbtic boolebn isCbllerSensitive(MemberNbme mem) {
        if (!mem.isInvocbble())  return fblse;  // fields bre not cbller sensitive

        return mem.isCbllerSensitive() || cbnBeCblledVirtubl(mem);
    }

    stbtic boolebn cbnBeCblledVirtubl(MemberNbme mem) {
        bssert(mem.isInvocbble());
        Clbss<?> defc = mem.getDeclbringClbss();
        switch (mem.getNbme()) {
        cbse "checkMemberAccess":
            return cbnBeCblledVirtubl(mem, jbvb.lbng.SecurityMbnbger.clbss);
        cbse "getContextClbssLobder":
            return cbnBeCblledVirtubl(mem, jbvb.lbng.Threbd.clbss);
        }
        return fblse;
    }

    stbtic boolebn cbnBeCblledVirtubl(MemberNbme symbolicRef, Clbss<?> definingClbss) {
        Clbss<?> symbolicRefClbss = symbolicRef.getDeclbringClbss();
        if (symbolicRefClbss == definingClbss)  return true;
        if (symbolicRef.isStbtic() || symbolicRef.isPrivbte())  return fblse;
        return (definingClbss.isAssignbbleFrom(symbolicRefClbss) ||  // Msym overrides Mdef
                symbolicRefClbss.isInterfbce());                     // Mdef implements Msym
    }
}
