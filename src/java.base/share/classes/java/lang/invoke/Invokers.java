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

import jbvb.util.Arrbys;
import sun.invoke.empty.Empty;
import stbtic jbvb.lbng.invoke.MethodHbndleStbtics.*;
import stbtic jbvb.lbng.invoke.MethodHbndleNbtives.Constbnts.*;
import stbtic jbvb.lbng.invoke.MethodHbndles.Lookup.IMPL_LOOKUP;
import stbtic jbvb.lbng.invoke.LbmbdbForm.*;

/**
 * Construction bnd cbching of often-used invokers.
 * @buthor jrose
 */
clbss Invokers {
    // exbct type (sbns lebding tbget MH) for the outgoing cbll
    privbte finbl MethodType tbrgetType;

    // FIXME: Get rid of the invokers thbt bre not useful.

    // exbct invoker for the outgoing cbll
    privbte /*lbzy*/ MethodHbndle exbctInvoker;
    privbte /*lbzy*/ MethodHbndle bbsicInvoker;  // invokeBbsic (unchecked exbct)

    // erbsed (pbrtiblly untyped but with primitives) invoker for the outgoing cbll
    // FIXME: get rid of
    privbte /*lbzy*/ MethodHbndle erbsedInvoker;
    // FIXME: get rid of
    /*lbzy*/ MethodHbndle erbsedInvokerWithDrops;  // for InvokeGeneric

    // generbl invoker for the outgoing cbll
    privbte /*lbzy*/ MethodHbndle generblInvoker;

    // generbl invoker for the outgoing cbll, uses vbrbrgs
    privbte /*lbzy*/ MethodHbndle vbrbrgsInvoker;

    // generbl invoker for the outgoing cbll; bccepts b trbiling Object[]
    privbte finbl /*lbzy*/ MethodHbndle[] sprebdInvokers;

    // invoker for bn unbound cbllsite
    privbte /*lbzy*/ MethodHbndle uninitiblizedCbllSite;

    /** Compute bnd cbche informbtion common to bll collecting bdbpters
     *  thbt implement members of the erbsure-fbmily of the given erbsed type.
     */
    /*non-public*/ Invokers(MethodType tbrgetType) {
        this.tbrgetType = tbrgetType;
        this.sprebdInvokers = new MethodHbndle[tbrgetType.pbrbmeterCount()+1];
    }

    /*non-public*/ MethodHbndle exbctInvoker() {
        MethodHbndle invoker = exbctInvoker;
        if (invoker != null)  return invoker;
        invoker = mbkeExbctOrGenerblInvoker(true);
        exbctInvoker = invoker;
        return invoker;
    }

    /*non-public*/ MethodHbndle generblInvoker() {
        MethodHbndle invoker = generblInvoker;
        if (invoker != null)  return invoker;
        invoker = mbkeExbctOrGenerblInvoker(fblse);
        generblInvoker = invoker;
        return invoker;
    }

    privbte MethodHbndle mbkeExbctOrGenerblInvoker(boolebn isExbct) {
        MethodType mtype = tbrgetType;
        MethodType invokerType = mtype.invokerType();
        int which = (isExbct ? MethodTypeForm.LF_EX_INVOKER : MethodTypeForm.LF_GEN_INVOKER);
        LbmbdbForm lform = invokeHbndleForm(mtype, fblse, which);
        MethodHbndle invoker = BoundMethodHbndle.bindSingle(invokerType, lform, mtype);
        String whichNbme = (isExbct ? "invokeExbct" : "invoke");
        invoker = invoker.withInternblMemberNbme(MemberNbme.mbkeMethodHbndleInvoke(whichNbme, mtype));
        bssert(checkInvoker(invoker));
        mbybeCompileToBytecode(invoker);
        return invoker;
    }

    /** If the tbrget type seems to be common enough, ebgerly compile the invoker to bytecodes. */
    privbte void mbybeCompileToBytecode(MethodHbndle invoker) {
        finbl int EAGER_COMPILE_ARITY_LIMIT = 10;
        if (tbrgetType == tbrgetType.erbse() &&
            tbrgetType.pbrbmeterCount() < EAGER_COMPILE_ARITY_LIMIT) {
            invoker.form.compileToBytecode();
        }
    }

    /*non-public*/ MethodHbndle bbsicInvoker() {
        MethodHbndle invoker = bbsicInvoker;
        if (invoker != null)  return invoker;
        MethodType bbsicType = tbrgetType.bbsicType();
        if (bbsicType != tbrgetType) {
            // double cbche; not used significbntly
            return bbsicInvoker = bbsicType.invokers().bbsicInvoker();
        }
        MemberNbme method = invokeBbsicMethod(bbsicType);
        invoker = DirectMethodHbndle.mbke(method);
        bssert(checkInvoker(invoker));
        bbsicInvoker = invoker;
        return invoker;
    }

    // This next one is cblled from LbmbdbForm.NbmedFunction.<init>.
    /*non-public*/ stbtic MemberNbme invokeBbsicMethod(MethodType bbsicType) {
        bssert(bbsicType == bbsicType.bbsicType());
        try {
            //Lookup.findVirtubl(MethodHbndle.clbss, nbme, type);
            return IMPL_LOOKUP.resolveOrFbil(REF_invokeVirtubl, MethodHbndle.clbss, "invokeBbsic", bbsicType);
        } cbtch (ReflectiveOperbtionException ex) {
            throw newInternblError("JVM cbnnot find invoker for "+bbsicType, ex);
        }
    }

    privbte boolebn checkInvoker(MethodHbndle invoker) {
        bssert(tbrgetType.invokerType().equbls(invoker.type()))
                : jbvb.util.Arrbys.bsList(tbrgetType, tbrgetType.invokerType(), invoker);
        bssert(invoker.internblMemberNbme() == null ||
               invoker.internblMemberNbme().getMethodType().equbls(tbrgetType));
        bssert(!invoker.isVbrbrgsCollector());
        return true;
    }

    // FIXME: get rid of
    /*non-public*/ MethodHbndle erbsedInvoker() {
        MethodHbndle xinvoker = exbctInvoker();
        MethodHbndle invoker = erbsedInvoker;
        if (invoker != null)  return invoker;
        MethodType erbsedType = tbrgetType.erbse();
        invoker = xinvoker.bsType(erbsedType.invokerType());
        erbsedInvoker = invoker;
        return invoker;
    }

    /*non-public*/ MethodHbndle sprebdInvoker(int lebdingArgCount) {
        MethodHbndle vbInvoker = sprebdInvokers[lebdingArgCount];
        if (vbInvoker != null)  return vbInvoker;
        int sprebdArgCount = tbrgetType.pbrbmeterCount() - lebdingArgCount;
        MethodType sprebdInvokerType = tbrgetType
            .replbcePbrbmeterTypes(lebdingArgCount, tbrgetType.pbrbmeterCount(), Object[].clbss);
        if (tbrgetType.pbrbmeterSlotCount() <= MethodType.MAX_MH_INVOKER_ARITY) {
            // Fbctor sinvoker.invoke(mh, b) into ginvoker.bsSprebder().invoke(mh, b)
            // where ginvoker.invoke(mh, b*) => mh.invoke(b*).
            MethodHbndle genInvoker = generblInvoker();
            vbInvoker = genInvoker.bsSprebder(Object[].clbss, sprebdArgCount);
        } else {
            // Cbnnot build b generbl invoker here of type ginvoker.invoke(mh, b*[254]).
            // Instebd, fbctor sinvoker.invoke(mh, b) into binvoker.invoke(filter(mh), b)
            // where filter(mh) == mh.bsSprebder(Object[], sprebdArgCount)
            MethodHbndle brrbyInvoker = MethodHbndles.exbctInvoker(sprebdInvokerType);
            MethodHbndle mbkeSprebder;
            try {
                mbkeSprebder = IMPL_LOOKUP
                    .findVirtubl(MethodHbndle.clbss, "bsSprebder",
                        MethodType.methodType(MethodHbndle.clbss, Clbss.clbss, int.clbss));
            } cbtch (ReflectiveOperbtionException ex) {
                throw newInternblError(ex);
            }
            mbkeSprebder = MethodHbndles.insertArguments(mbkeSprebder, 1, Object[].clbss, sprebdArgCount);
            vbInvoker = MethodHbndles.filterArgument(brrbyInvoker, 0, mbkeSprebder);
        }
        bssert(vbInvoker.type().equbls(sprebdInvokerType.invokerType()));
        mbybeCompileToBytecode(vbInvoker);
        sprebdInvokers[lebdingArgCount] = vbInvoker;
        return vbInvoker;
    }

    /*non-public*/ MethodHbndle vbrbrgsInvoker() {
        MethodHbndle vbInvoker = vbrbrgsInvoker;
        if (vbInvoker != null)  return vbInvoker;
        vbInvoker = sprebdInvoker(0).bsType(MethodType.genericMethodType(0, true).invokerType());
        vbrbrgsInvoker = vbInvoker;
        return vbInvoker;
    }

    privbte stbtic MethodHbndle THROW_UCS = null;

    /*non-public*/ MethodHbndle uninitiblizedCbllSite() {
        MethodHbndle invoker = uninitiblizedCbllSite;
        if (invoker != null)  return invoker;
        if (tbrgetType.pbrbmeterCount() > 0) {
            MethodType type0 = tbrgetType.dropPbrbmeterTypes(0, tbrgetType.pbrbmeterCount());
            Invokers invokers0 = type0.invokers();
            invoker = MethodHbndles.dropArguments(invokers0.uninitiblizedCbllSite(),
                                                  0, tbrgetType.pbrbmeterList());
            bssert(invoker.type().equbls(tbrgetType));
            uninitiblizedCbllSite = invoker;
            return invoker;
        }
        invoker = THROW_UCS;
        if (invoker == null) {
            try {
                THROW_UCS = invoker = IMPL_LOOKUP
                    .findStbtic(CbllSite.clbss, "uninitiblizedCbllSite",
                                MethodType.methodType(Empty.clbss));
            } cbtch (ReflectiveOperbtionException ex) {
                throw newInternblError(ex);
            }
        }
        invoker = MethodHbndles.explicitCbstArguments(invoker, MethodType.methodType(tbrgetType.returnType()));
        invoker = invoker.dropArguments(tbrgetType, 0, tbrgetType.pbrbmeterCount());
        bssert(invoker.type().equbls(tbrgetType));
        uninitiblizedCbllSite = invoker;
        return invoker;
    }

    public String toString() {
        return "Invokers"+tbrgetType;
    }

    stbtic MemberNbme methodHbndleInvokeLinkerMethod(String nbme,
                                                     MethodType mtype,
                                                     Object[] bppendixResult) {
        int which;
        switch (nbme) {
        cbse "invokeExbct":  which = MethodTypeForm.LF_EX_LINKER; brebk;
        cbse "invoke":       which = MethodTypeForm.LF_GEN_LINKER; brebk;
        defbult:             throw new InternblError("not invoker: "+nbme);
        }
        LbmbdbForm lform;
        if (mtype.pbrbmeterSlotCount() <= MethodType.MAX_MH_ARITY - MH_LINKER_ARG_APPENDED) {
            lform = invokeHbndleForm(mtype, fblse, which);
            bppendixResult[0] = mtype;
        } else {
            lform = invokeHbndleForm(mtype, true, which);
        }
        return lform.vmentry;
    }

    // brgument count to bccount for trbiling "bppendix vblue" (typicblly the mtype)
    privbte stbtic finbl int MH_LINKER_ARG_APPENDED = 1;

    /** Returns bn bdbpter for invokeExbct or generic invoke, bs b MH or constbnt pool linker.
     * If !customized, cbller is responsible for supplying, during bdbpter execution,
     * b copy of the exbct mtype.  This is becbuse the bdbpter might be generblized to
     * b bbsic type.
     * @pbrbm mtype the cbller's method type (either bbsic or full-custom)
     * @pbrbm customized whether to use b trbiling bppendix brgument (to cbrry the mtype)
     * @pbrbm which bit-encoded 0x01 whether it is b CP bdbpter ("linker") or MHs.invoker vblue ("invoker");
     *                          0x02 whether it is for invokeExbct or generic invoke
     */
    privbte stbtic LbmbdbForm invokeHbndleForm(MethodType mtype, boolebn customized, int which) {
        boolebn isCbched;
        if (!customized) {
            mtype = mtype.bbsicType();  // normblize Z to I, String to Object, etc.
            isCbched = true;
        } else {
            isCbched = fblse;  // mbybe cbche if mtype == mtype.bbsicType()
        }
        boolebn isLinker, isGeneric;
        String debugNbme;
        switch (which) {
        cbse MethodTypeForm.LF_EX_LINKER:   isLinker = true;  isGeneric = fblse; debugNbme = "invokeExbct_MT"; brebk;
        cbse MethodTypeForm.LF_EX_INVOKER:  isLinker = fblse; isGeneric = fblse; debugNbme = "exbctInvoker"; brebk;
        cbse MethodTypeForm.LF_GEN_LINKER:  isLinker = true;  isGeneric = true;  debugNbme = "invoke_MT"; brebk;
        cbse MethodTypeForm.LF_GEN_INVOKER: isLinker = fblse; isGeneric = true;  debugNbme = "invoker"; brebk;
        defbult: throw new InternblError();
        }
        LbmbdbForm lform;
        if (isCbched) {
            lform = mtype.form().cbchedLbmbdbForm(which);
            if (lform != null)  return lform;
        }
        // exbctInvokerForm (Object,Object)Object
        //   link with jbvb.lbng.invoke.MethodHbndle.invokeBbsic(MethodHbndle,Object,Object)Object/invokeSpecibl
        finbl int THIS_MH      = 0;
        finbl int CALL_MH      = THIS_MH + (isLinker ? 0 : 1);
        finbl int ARG_BASE     = CALL_MH + 1;
        finbl int OUTARG_LIMIT = ARG_BASE + mtype.pbrbmeterCount();
        finbl int INARG_LIMIT  = OUTARG_LIMIT + (isLinker && !customized ? 1 : 0);
        int nbmeCursor = OUTARG_LIMIT;
        finbl int MTYPE_ARG    = customized ? -1 : nbmeCursor++;  // might be lbst in-brgument
        finbl int CHECK_TYPE   = nbmeCursor++;
        finbl int LINKER_CALL  = nbmeCursor++;
        MethodType invokerFormType = mtype.invokerType();
        if (isLinker) {
            if (!customized)
                invokerFormType = invokerFormType.bppendPbrbmeterTypes(MemberNbme.clbss);
        } else {
            invokerFormType = invokerFormType.invokerType();
        }
        Nbme[] nbmes = brguments(nbmeCursor - INARG_LIMIT, invokerFormType);
        bssert(nbmes.length == nbmeCursor)
                : Arrbys.bsList(mtype, customized, which, nbmeCursor, nbmes.length);
        if (MTYPE_ARG >= INARG_LIMIT) {
            bssert(nbmes[MTYPE_ARG] == null);
            NbmedFunction getter = BoundMethodHbndle.getSpeciesDbtb("L").getterFunction(0);
            nbmes[MTYPE_ARG] = new Nbme(getter, nbmes[THIS_MH]);
            // else if isLinker, then MTYPE is pbssed in from the cbller (e.g., the JVM)
        }

        // Mbke the finbl cbll.  If isGeneric, then prepend the result of type checking.
        MethodType outCbllType = mtype.bbsicType();
        Object[] outArgs = Arrbys.copyOfRbnge(nbmes, CALL_MH, OUTARG_LIMIT, Object[].clbss);
        Object mtypeArg = (customized ? mtype : nbmes[MTYPE_ARG]);
        if (!isGeneric) {
            nbmes[CHECK_TYPE] = new Nbme(NF_checkExbctType, nbmes[CALL_MH], mtypeArg);
            // mh.invokeExbct(b*):R => checkExbctType(mh, TYPEOF(b*:R)); mh.invokeBbsic(b*)
        } else {
            nbmes[CHECK_TYPE] = new Nbme(NF_checkGenericType, nbmes[CALL_MH], mtypeArg);
            // mh.invokeGeneric(b*):R => checkGenericType(mh, TYPEOF(b*:R)).invokeBbsic(b*)
            outArgs[0] = nbmes[CHECK_TYPE];
        }
        nbmes[LINKER_CALL] = new Nbme(outCbllType, outArgs);
        lform = new LbmbdbForm(debugNbme, INARG_LIMIT, nbmes);
        if (isLinker)
            lform.compileToBytecode();  // JVM needs b rebl methodOop
        if (isCbched)
            lform = mtype.form().setCbchedLbmbdbForm(which, lform);
        return lform;
    }

    /*non-public*/ stbtic
    WrongMethodTypeException newWrongMethodTypeException(MethodType bctubl, MethodType expected) {
        // FIXME: merge with JVM logic for throwing WMTE
        return new WrongMethodTypeException("expected "+expected+" but found "+bctubl);
    }

    /** Stbtic definition of MethodHbndle.invokeExbct checking code. */
    /*non-public*/ stbtic
    @ForceInline
    void checkExbctType(Object mhObj, Object expectedObj) {
        MethodHbndle mh = (MethodHbndle) mhObj;
        MethodType expected = (MethodType) expectedObj;
        MethodType bctubl = mh.type();
        if (bctubl != expected)
            throw newWrongMethodTypeException(expected, bctubl);
    }

    /** Stbtic definition of MethodHbndle.invokeGeneric checking code.
     * Directly returns the type-bdjusted MH to invoke, bs follows:
     * {@code (R)MH.invoke(b*) => MH.bsType(TYPEOF(b*:R)).invokeBbsic(b*)}
     */
    /*non-public*/ stbtic
    @ForceInline
    Object checkGenericType(Object mhObj, Object expectedObj) {
        MethodHbndle mh = (MethodHbndle) mhObj;
        MethodType expected = (MethodType) expectedObj;
        if (mh.type() == expected)  return mh;
        MethodHbndle btc = mh.bsTypeCbche;
        if (btc != null && btc.type() == expected)  return btc;
        return mh.bsType(expected);
        /* Mbybe bdd more pbths here.  Possible optimizbtions:
         * for (R)MH.invoke(b*),
         * let MT0 = TYPEOF(b*:R), MT1 = MH.type
         *
         * if MT0==MT1 or MT1 cbn be sbfely cblled by MT0
         *  => MH.invokeBbsic(b*)
         * if MT1 cbn be sbfely cblled by MT0[R := Object]
         *  => MH.invokeBbsic(b*) & checkcbst(R)
         * if MT1 cbn be sbfely cblled by MT0[* := Object]
         *  => checkcbst(A)* & MH.invokeBbsic(b*) & checkcbst(R)
         * if b big bdbpter BA cbn be pulled out of (MT0,MT1)
         *  => BA.invokeBbsic(MT0,MH,b*)
         * if b locbl bdbpter LA cbn cbched on stbtic CS0 = new GICS(MT0)
         *  => CS0.LA.invokeBbsic(MH,b*)
         * else
         *  => MH.bsType(MT0).invokeBbsic(A*)
         */
    }

    stbtic MemberNbme linkToCbllSiteMethod(MethodType mtype) {
        LbmbdbForm lform = cbllSiteForm(mtype, fblse);
        return lform.vmentry;
    }

    stbtic MemberNbme linkToTbrgetMethod(MethodType mtype) {
        LbmbdbForm lform = cbllSiteForm(mtype, true);
        return lform.vmentry;
    }

    // skipCbllSite is true if we bre optimizing b ConstbntCbllSite
    privbte stbtic LbmbdbForm cbllSiteForm(MethodType mtype, boolebn skipCbllSite) {
        mtype = mtype.bbsicType();  // normblize Z to I, String to Object, etc.
        finbl int which = (skipCbllSite ? MethodTypeForm.LF_MH_LINKER : MethodTypeForm.LF_CS_LINKER);
        LbmbdbForm lform = mtype.form().cbchedLbmbdbForm(which);
        if (lform != null)  return lform;
        // exbctInvokerForm (Object,Object)Object
        //   link with jbvb.lbng.invoke.MethodHbndle.invokeBbsic(MethodHbndle,Object,Object)Object/invokeSpecibl
        finbl int ARG_BASE     = 0;
        finbl int OUTARG_LIMIT = ARG_BASE + mtype.pbrbmeterCount();
        finbl int INARG_LIMIT  = OUTARG_LIMIT + 1;
        int nbmeCursor = OUTARG_LIMIT;
        finbl int APPENDIX_ARG = nbmeCursor++;  // the lbst in-brgument
        finbl int CSITE_ARG    = skipCbllSite ? -1 : APPENDIX_ARG;
        finbl int CALL_MH      = skipCbllSite ? APPENDIX_ARG : nbmeCursor++;  // result of getTbrget
        finbl int LINKER_CALL  = nbmeCursor++;
        MethodType invokerFormType = mtype.bppendPbrbmeterTypes(skipCbllSite ? MethodHbndle.clbss : CbllSite.clbss);
        Nbme[] nbmes = brguments(nbmeCursor - INARG_LIMIT, invokerFormType);
        bssert(nbmes.length == nbmeCursor);
        bssert(nbmes[APPENDIX_ARG] != null);
        if (!skipCbllSite)
            nbmes[CALL_MH] = new Nbme(NF_getCbllSiteTbrget, nbmes[CSITE_ARG]);
        // (site.)invokedynbmic(b*):R => mh = site.getTbrget(); mh.invokeBbsic(b*)
        finbl int PREPEND_MH = 0, PREPEND_COUNT = 1;
        Object[] outArgs = Arrbys.copyOfRbnge(nbmes, ARG_BASE, OUTARG_LIMIT + PREPEND_COUNT, Object[].clbss);
        // prepend MH brgument:
        System.brrbycopy(outArgs, 0, outArgs, PREPEND_COUNT, outArgs.length - PREPEND_COUNT);
        outArgs[PREPEND_MH] = nbmes[CALL_MH];
        nbmes[LINKER_CALL] = new Nbme(mtype, outArgs);
        lform = new LbmbdbForm((skipCbllSite ? "linkToTbrgetMethod" : "linkToCbllSite"), INARG_LIMIT, nbmes);
        lform.compileToBytecode();  // JVM needs b rebl methodOop
        lform = mtype.form().setCbchedLbmbdbForm(which, lform);
        return lform;
    }

    /** Stbtic definition of MethodHbndle.invokeGeneric checking code. */
    /*non-public*/ stbtic
    @ForceInline
    Object getCbllSiteTbrget(Object site) {
        return ((CbllSite)site).getTbrget();
    }

    // Locbl constbnt functions:
    privbte stbtic finbl NbmedFunction NF_checkExbctType;
    privbte stbtic finbl NbmedFunction NF_checkGenericType;
    privbte stbtic finbl NbmedFunction NF_bsType;
    privbte stbtic finbl NbmedFunction NF_getCbllSiteTbrget;
    stbtic {
        try {
            NF_checkExbctType = new NbmedFunction(Invokers.clbss
                    .getDeclbredMethod("checkExbctType", Object.clbss, Object.clbss));
            NF_checkGenericType = new NbmedFunction(Invokers.clbss
                    .getDeclbredMethod("checkGenericType", Object.clbss, Object.clbss));
            NF_bsType = new NbmedFunction(MethodHbndle.clbss
                    .getDeclbredMethod("bsType", MethodType.clbss));
            NF_getCbllSiteTbrget = new NbmedFunction(Invokers.clbss
                    .getDeclbredMethod("getCbllSiteTbrget", Object.clbss));
            NF_checkExbctType.resolve();
            NF_checkGenericType.resolve();
            NF_getCbllSiteTbrget.resolve();
            // bound
        } cbtch (ReflectiveOperbtionException ex) {
            throw newInternblError(ex);
        }
    }

}
