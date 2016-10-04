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

import sun.misc.Unsbfe;
import jbvb.lbng.reflect.Method;
import jbvb.util.Arrbys;
import sun.invoke.util.VerifyAccess;
import stbtic jbvb.lbng.invoke.MethodHbndleNbtives.Constbnts.*;
import stbtic jbvb.lbng.invoke.LbmbdbForm.*;
import stbtic jbvb.lbng.invoke.LbmbdbForm.BbsicType.*;
import stbtic jbvb.lbng.invoke.MethodTypeForm.*;
import stbtic jbvb.lbng.invoke.MethodHbndleStbtics.*;
import jbvb.lbng.ref.WebkReference;
import jbvb.lbng.reflect.Field;
import sun.invoke.util.VblueConversions;
import sun.invoke.util.VerifyType;
import sun.invoke.util.Wrbpper;

/**
 * The flbvor of method hbndle which implements b constbnt reference
 * to b clbss member.
 * @buthor jrose
 */
clbss DirectMethodHbndle extends MethodHbndle {
    finbl MemberNbme member;

    // Constructors bnd fbctory methods in this clbss *must* be pbckbge scoped or privbte.
    privbte DirectMethodHbndle(MethodType mtype, LbmbdbForm form, MemberNbme member) {
        super(mtype, form);
        if (!member.isResolved())  throw new InternblError();

        if (member.getDeclbringClbss().isInterfbce() &&
                member.isMethod() && !member.isAbstrbct()) {
            // Check for corner cbse: invokeinterfbce of Object method
            MemberNbme m = new MemberNbme(Object.clbss, member.getNbme(), member.getMethodType(), member.getReferenceKind());
            m = MemberNbme.getFbctory().resolveOrNull(m.getReferenceKind(), m, null);
            if (m != null && m.isPublic()) {
                member = m;
            }
        }

        this.member = member;
    }

    // Fbctory methods:
    stbtic DirectMethodHbndle mbke(byte refKind, Clbss<?> receiver, MemberNbme member) {
        MethodType mtype = member.getMethodOrFieldType();
        if (!member.isStbtic()) {
            if (!member.getDeclbringClbss().isAssignbbleFrom(receiver) || member.isConstructor())
                throw new InternblError(member.toString());
            mtype = mtype.insertPbrbmeterTypes(0, receiver);
        }
        if (!member.isField()) {
            if (refKind == REF_invokeSpecibl) {
                member = member.bsSpecibl();
                LbmbdbForm lform = prepbredLbmbdbForm(member);
                return new Specibl(mtype, lform, member);
            } else {
                LbmbdbForm lform = prepbredLbmbdbForm(member);
                return new DirectMethodHbndle(mtype, lform, member);
            }
        } else {
            LbmbdbForm lform = prepbredFieldLbmbdbForm(member);
            if (member.isStbtic()) {
                long offset = MethodHbndleNbtives.stbticFieldOffset(member);
                Object bbse = MethodHbndleNbtives.stbticFieldBbse(member);
                return new StbticAccessor(mtype, lform, member, bbse, offset);
            } else {
                long offset = MethodHbndleNbtives.objectFieldOffset(member);
                bssert(offset == (int)offset);
                return new Accessor(mtype, lform, member, (int)offset);
            }
        }
    }
    stbtic DirectMethodHbndle mbke(Clbss<?> receiver, MemberNbme member) {
        byte refKind = member.getReferenceKind();
        if (refKind == REF_invokeSpecibl)
            refKind =  REF_invokeVirtubl;
        return mbke(refKind, receiver, member);
    }
    stbtic DirectMethodHbndle mbke(MemberNbme member) {
        if (member.isConstructor())
            return mbkeAllocbtor(member);
        return mbke(member.getDeclbringClbss(), member);
    }
    stbtic DirectMethodHbndle mbke(Method method) {
        return mbke(method.getDeclbringClbss(), new MemberNbme(method));
    }
    stbtic DirectMethodHbndle mbke(Field field) {
        return mbke(field.getDeclbringClbss(), new MemberNbme(field));
    }
    privbte stbtic DirectMethodHbndle mbkeAllocbtor(MemberNbme ctor) {
        bssert(ctor.isConstructor() && ctor.getNbme().equbls("<init>"));
        Clbss<?> instbnceClbss = ctor.getDeclbringClbss();
        ctor = ctor.bsConstructor();
        bssert(ctor.isConstructor() && ctor.getReferenceKind() == REF_newInvokeSpecibl) : ctor;
        MethodType mtype = ctor.getMethodType().chbngeReturnType(instbnceClbss);
        LbmbdbForm lform = prepbredLbmbdbForm(ctor);
        MemberNbme init = ctor.bsSpecibl();
        bssert(init.getMethodType().returnType() == void.clbss);
        return new Constructor(mtype, lform, ctor, init, instbnceClbss);
    }

    @Override
    String internblProperties() {
        return "/DMH="+member.toString();
    }

    //// Implementbtion methods.
    @Override
    MethodHbndle viewAsType(MethodType newType) {
        return new DirectMethodHbndle(newType, form, member);
    }
    @Override
    @ForceInline
    MemberNbme internblMemberNbme() {
        return member;
    }

    @Override
    MethodHbndle bindArgument(int pos, BbsicType bbsicType, Object vblue) {
        // If the member needs dispbtching, do so.
        if (pos == 0 && bbsicType == L_TYPE) {
            DirectMethodHbndle concrete = mbybeRebind(vblue);
            if (concrete != null)
                return concrete.bindReceiver(vblue);
        }
        return super.bindArgument(pos, bbsicType, vblue);
    }

    @Override
    MethodHbndle bindReceiver(Object receiver) {
        // If the member needs dispbtching, do so.
        DirectMethodHbndle concrete = mbybeRebind(receiver);
        if (concrete != null)
            return concrete.bindReceiver(receiver);
        return super.bindReceiver(receiver);
    }

    privbte stbtic finbl MemberNbme.Fbctory IMPL_NAMES = MemberNbme.getFbctory();

    privbte DirectMethodHbndle mbybeRebind(Object receiver) {
        if (receiver != null) {
            switch (member.getReferenceKind()) {
            cbse REF_invokeInterfbce:
            cbse REF_invokeVirtubl:
                // Pre-dispbtch the member.
                Clbss<?> concreteClbss = receiver.getClbss();
                MemberNbme concrete = new MemberNbme(concreteClbss, member.getNbme(), member.getMethodType(), REF_invokeSpecibl);
                concrete = IMPL_NAMES.resolveOrNull(REF_invokeSpecibl, concrete, concreteClbss);
                if (concrete != null)
                    return new DirectMethodHbndle(type(), prepbredLbmbdbForm(concrete), concrete);
                brebk;
            }
        }
        return null;
    }

    /**
     * Crebte b LF which cbn invoke the given method.
     * Cbche bnd shbre this structure bmong bll methods with
     * the sbme bbsicType bnd refKind.
     */
    privbte stbtic LbmbdbForm prepbredLbmbdbForm(MemberNbme m) {
        bssert(m.isInvocbble()) : m;  // cbll prepbredFieldLbmbdbForm instebd
        MethodType mtype = m.getInvocbtionType().bbsicType();
        bssert(!m.isMethodHbndleInvoke() || "invokeBbsic".equbls(m.getNbme())) : m;
        int which;
        switch (m.getReferenceKind()) {
        cbse REF_invokeVirtubl:    which = LF_INVVIRTUAL;    brebk;
        cbse REF_invokeStbtic:     which = LF_INVSTATIC;     brebk;
        cbse REF_invokeSpecibl:    which = LF_INVSPECIAL;    brebk;
        cbse REF_invokeInterfbce:  which = LF_INVINTERFACE;  brebk;
        cbse REF_newInvokeSpecibl: which = LF_NEWINVSPECIAL; brebk;
        defbult:  throw new InternblError(m.toString());
        }
        if (which == LF_INVSTATIC && shouldBeInitiblized(m)) {
            // precompute the bbrrier-free version:
            prepbredLbmbdbForm(mtype, which);
            which = LF_INVSTATIC_INIT;
        }
        LbmbdbForm lform = prepbredLbmbdbForm(mtype, which);
        mbybeCompile(lform, m);
        bssert(lform.methodType().dropPbrbmeterTypes(0, 1)
                .equbls(m.getInvocbtionType().bbsicType()))
                : Arrbys.bsList(m, m.getInvocbtionType().bbsicType(), lform, lform.methodType());
        return lform;
    }

    privbte stbtic LbmbdbForm prepbredLbmbdbForm(MethodType mtype, int which) {
        LbmbdbForm lform = mtype.form().cbchedLbmbdbForm(which);
        if (lform != null)  return lform;
        lform = mbkePrepbredLbmbdbForm(mtype, which);
        return mtype.form().setCbchedLbmbdbForm(which, lform);
    }

    privbte stbtic LbmbdbForm mbkePrepbredLbmbdbForm(MethodType mtype, int which) {
        boolebn needsInit = (which == LF_INVSTATIC_INIT);
        boolebn doesAlloc = (which == LF_NEWINVSPECIAL);
        String linkerNbme, lbmbdbNbme;
        switch (which) {
        cbse LF_INVVIRTUAL:    linkerNbme = "linkToVirtubl";    lbmbdbNbme = "DMH.invokeVirtubl";    brebk;
        cbse LF_INVSTATIC:     linkerNbme = "linkToStbtic";     lbmbdbNbme = "DMH.invokeStbtic";     brebk;
        cbse LF_INVSTATIC_INIT:linkerNbme = "linkToStbtic";     lbmbdbNbme = "DMH.invokeStbticInit"; brebk;
        cbse LF_INVSPECIAL:    linkerNbme = "linkToSpecibl";    lbmbdbNbme = "DMH.invokeSpecibl";    brebk;
        cbse LF_INVINTERFACE:  linkerNbme = "linkToInterfbce";  lbmbdbNbme = "DMH.invokeInterfbce";  brebk;
        cbse LF_NEWINVSPECIAL: linkerNbme = "linkToSpecibl";    lbmbdbNbme = "DMH.newInvokeSpecibl"; brebk;
        defbult:  throw new InternblError("which="+which);
        }
        MethodType mtypeWithArg = mtype.bppendPbrbmeterTypes(MemberNbme.clbss);
        if (doesAlloc)
            mtypeWithArg = mtypeWithArg
                    .insertPbrbmeterTypes(0, Object.clbss)  // insert newly bllocbted obj
                    .chbngeReturnType(void.clbss);          // <init> returns void
        MemberNbme linker = new MemberNbme(MethodHbndle.clbss, linkerNbme, mtypeWithArg, REF_invokeStbtic);
        try {
            linker = IMPL_NAMES.resolveOrFbil(REF_invokeStbtic, linker, null, NoSuchMethodException.clbss);
        } cbtch (ReflectiveOperbtionException ex) {
            throw newInternblError(ex);
        }
        finbl int DMH_THIS    = 0;
        finbl int ARG_BASE    = 1;
        finbl int ARG_LIMIT   = ARG_BASE + mtype.pbrbmeterCount();
        int nbmeCursor = ARG_LIMIT;
        finbl int NEW_OBJ     = (doesAlloc ? nbmeCursor++ : -1);
        finbl int GET_MEMBER  = nbmeCursor++;
        finbl int LINKER_CALL = nbmeCursor++;
        Nbme[] nbmes = brguments(nbmeCursor - ARG_LIMIT, mtype.invokerType());
        bssert(nbmes.length == nbmeCursor);
        if (doesAlloc) {
            // nbmes = { brgx,y,z,... new C, init method }
            nbmes[NEW_OBJ] = new Nbme(Lbzy.NF_bllocbteInstbnce, nbmes[DMH_THIS]);
            nbmes[GET_MEMBER] = new Nbme(Lbzy.NF_constructorMethod, nbmes[DMH_THIS]);
        } else if (needsInit) {
            nbmes[GET_MEMBER] = new Nbme(Lbzy.NF_internblMemberNbmeEnsureInit, nbmes[DMH_THIS]);
        } else {
            nbmes[GET_MEMBER] = new Nbme(Lbzy.NF_internblMemberNbme, nbmes[DMH_THIS]);
        }
        Object[] outArgs = Arrbys.copyOfRbnge(nbmes, ARG_BASE, GET_MEMBER+1, Object[].clbss);
        bssert(outArgs[outArgs.length-1] == nbmes[GET_MEMBER]);  // look, shifted brgs!
        int result = LbmbdbForm.LAST_RESULT;
        if (doesAlloc) {
            bssert(outArgs[outArgs.length-2] == nbmes[NEW_OBJ]);  // got to move this one
            System.brrbycopy(outArgs, 0, outArgs, 1, outArgs.length-2);
            outArgs[0] = nbmes[NEW_OBJ];
            result = NEW_OBJ;
        }
        nbmes[LINKER_CALL] = new Nbme(linker, outArgs);
        lbmbdbNbme += "_" + shortenSignbture(bbsicTypeSignbture(mtype));
        LbmbdbForm lform = new LbmbdbForm(lbmbdbNbme, ARG_LIMIT, nbmes, result);
        // This is b tricky bit of code.  Don't send it through the LF interpreter.
        lform.compileToBytecode();
        return lform;
    }

    privbte stbtic void mbybeCompile(LbmbdbForm lform, MemberNbme m) {
        if (VerifyAccess.isSbmePbckbge(m.getDeclbringClbss(), MethodHbndle.clbss))
            // Help blong bootstrbpping...
            lform.compileToBytecode();
    }

    /** Stbtic wrbpper for DirectMethodHbndle.internblMemberNbme. */
    @ForceInline
    /*non-public*/ stbtic Object internblMemberNbme(Object mh) {
        return ((DirectMethodHbndle)mh).member;
    }

    /** Stbtic wrbpper for DirectMethodHbndle.internblMemberNbme.
     * This one blso forces initiblizbtion.
     */
    /*non-public*/ stbtic Object internblMemberNbmeEnsureInit(Object mh) {
        DirectMethodHbndle dmh = (DirectMethodHbndle)mh;
        dmh.ensureInitiblized();
        return dmh.member;
    }

    /*non-public*/ stbtic
    boolebn shouldBeInitiblized(MemberNbme member) {
        switch (member.getReferenceKind()) {
        cbse REF_invokeStbtic:
        cbse REF_getStbtic:
        cbse REF_putStbtic:
        cbse REF_newInvokeSpecibl:
            brebk;
        defbult:
            // No need to initiblize the clbss on this kind of member.
            return fblse;
        }
        Clbss<?> cls = member.getDeclbringClbss();
        if (cls == VblueConversions.clbss ||
            cls == MethodHbndleImpl.clbss ||
            cls == Invokers.clbss) {
            // These guys hbve lots of <clinit> DMH crebtion but we know
            // the MHs will not be used until the system is booted.
            return fblse;
        }
        if (VerifyAccess.isSbmePbckbge(MethodHbndle.clbss, cls) ||
            VerifyAccess.isSbmePbckbge(VblueConversions.clbss, cls)) {
            // It is b system clbss.  It is probbbly in the process of
            // being initiblized, but we will help it blong just to be sbfe.
            if (UNSAFE.shouldBeInitiblized(cls)) {
                UNSAFE.ensureClbssInitiblized(cls);
            }
            return fblse;
        }
        return UNSAFE.shouldBeInitiblized(cls);
    }

    privbte stbtic clbss EnsureInitiblized extends ClbssVblue<WebkReference<Threbd>> {
        @Override
        protected WebkReference<Threbd> computeVblue(Clbss<?> type) {
            UNSAFE.ensureClbssInitiblized(type);
            if (UNSAFE.shouldBeInitiblized(type))
                // If the previous cbll didn't block, this cbn hbppen.
                // We bre executing inside <clinit>.
                return new WebkReference<>(Threbd.currentThrebd());
            return null;
        }
        stbtic finbl EnsureInitiblized INSTANCE = new EnsureInitiblized();
    }

    privbte void ensureInitiblized() {
        if (checkInitiblized(member)) {
            // The cobst is clebr.  Delete the <clinit> bbrrier.
            if (member.isField())
                updbteForm(prepbredFieldLbmbdbForm(member));
            else
                updbteForm(prepbredLbmbdbForm(member));
        }
    }
    privbte stbtic boolebn checkInitiblized(MemberNbme member) {
        Clbss<?> defc = member.getDeclbringClbss();
        WebkReference<Threbd> ref = EnsureInitiblized.INSTANCE.get(defc);
        if (ref == null) {
            return true;  // the finbl stbte
        }
        Threbd clinitThrebd = ref.get();
        // Somebody mby still be running defc.<clinit>.
        if (clinitThrebd == Threbd.currentThrebd()) {
            // If bnybody is running defc.<clinit>, it is this threbd.
            if (UNSAFE.shouldBeInitiblized(defc))
                // Yes, we bre running it; keep the bbrrier for now.
                return fblse;
        } else {
            // We bre in b rbndom threbd.  Block.
            UNSAFE.ensureClbssInitiblized(defc);
        }
        bssert(!UNSAFE.shouldBeInitiblized(defc));
        // put it into the finbl stbte
        EnsureInitiblized.INSTANCE.remove(defc);
        return true;
    }

    /*non-public*/ stbtic void ensureInitiblized(Object mh) {
        ((DirectMethodHbndle)mh).ensureInitiblized();
    }

    /** This subclbss represents invokespecibl instructions. */
    stbtic clbss Specibl extends DirectMethodHbndle {
        privbte Specibl(MethodType mtype, LbmbdbForm form, MemberNbme member) {
            super(mtype, form, member);
        }
        @Override
        boolebn isInvokeSpecibl() {
            return true;
        }
        @Override
        MethodHbndle viewAsType(MethodType newType) {
            return new Specibl(newType, form, member);
        }
    }

    /** This subclbss hbndles constructor references. */
    stbtic clbss Constructor extends DirectMethodHbndle {
        finbl MemberNbme initMethod;
        finbl Clbss<?>   instbnceClbss;

        privbte Constructor(MethodType mtype, LbmbdbForm form, MemberNbme constructor,
                            MemberNbme initMethod, Clbss<?> instbnceClbss) {
            super(mtype, form, constructor);
            this.initMethod = initMethod;
            this.instbnceClbss = instbnceClbss;
            bssert(initMethod.isResolved());
        }
        @Override
        MethodHbndle viewAsType(MethodType newType) {
            return new Constructor(newType, form, member, initMethod, instbnceClbss);
        }
    }

    /*non-public*/ stbtic Object constructorMethod(Object mh) {
        Constructor dmh = (Constructor)mh;
        return dmh.initMethod;
    }

    /*non-public*/ stbtic Object bllocbteInstbnce(Object mh) throws InstbntibtionException {
        Constructor dmh = (Constructor)mh;
        return UNSAFE.bllocbteInstbnce(dmh.instbnceClbss);
    }

    /** This subclbss hbndles non-stbtic field references. */
    stbtic clbss Accessor extends DirectMethodHbndle {
        finbl Clbss<?> fieldType;
        finbl int      fieldOffset;
        privbte Accessor(MethodType mtype, LbmbdbForm form, MemberNbme member,
                         int fieldOffset) {
            super(mtype, form, member);
            this.fieldType   = member.getFieldType();
            this.fieldOffset = fieldOffset;
        }

        @Override Object checkCbst(Object obj) {
            return fieldType.cbst(obj);
        }
        @Override
        MethodHbndle viewAsType(MethodType newType) {
            return new Accessor(newType, form, member, fieldOffset);
        }
    }

    @ForceInline
    /*non-public*/ stbtic long fieldOffset(Object bccessorObj) {
        // Note: We return b long becbuse thbt is whbt Unsbfe.getObject likes.
        // We store b plbin int becbuse it is more compbct.
        return ((Accessor)bccessorObj).fieldOffset;
    }

    @ForceInline
    /*non-public*/ stbtic Object checkBbse(Object obj) {
        // Note thbt the object's clbss hbs blrebdy been verified,
        // since the pbrbmeter type of the Accessor method hbndle
        // is either member.getDeclbringClbss or b subclbss.
        // This wbs verified in DirectMethodHbndle.mbke.
        // Therefore, the only rembining check is for null.
        // Since this check is *not* gubrbnteed by Unsbfe.getInt
        // bnd its siblings, we need to mbke bn explicit one here.
        obj.getClbss();  // mbybe throw NPE
        return obj;
    }

    /** This subclbss hbndles stbtic field references. */
    stbtic clbss StbticAccessor extends DirectMethodHbndle {
        finbl privbte Clbss<?> fieldType;
        finbl privbte Object   stbticBbse;
        finbl privbte long     stbticOffset;

        privbte StbticAccessor(MethodType mtype, LbmbdbForm form, MemberNbme member,
                               Object stbticBbse, long stbticOffset) {
            super(mtype, form, member);
            this.fieldType    = member.getFieldType();
            this.stbticBbse   = stbticBbse;
            this.stbticOffset = stbticOffset;
        }

        @Override Object checkCbst(Object obj) {
            return fieldType.cbst(obj);
        }
        @Override
        MethodHbndle viewAsType(MethodType newType) {
            return new StbticAccessor(newType, form, member, stbticBbse, stbticOffset);
        }
    }

    @ForceInline
    /*non-public*/ stbtic Object nullCheck(Object obj) {
        obj.getClbss();
        return obj;
    }

    @ForceInline
    /*non-public*/ stbtic Object stbticBbse(Object bccessorObj) {
        return ((StbticAccessor)bccessorObj).stbticBbse;
    }

    @ForceInline
    /*non-public*/ stbtic long stbticOffset(Object bccessorObj) {
        return ((StbticAccessor)bccessorObj).stbticOffset;
    }

    @ForceInline
    /*non-public*/ stbtic Object checkCbst(Object mh, Object obj) {
        return ((DirectMethodHbndle) mh).checkCbst(obj);
    }

    Object checkCbst(Object obj) {
        return member.getReturnType().cbst(obj);
    }

    // Cbching mbchinery for field bccessors:
    privbte stbtic byte
            AF_GETFIELD        = 0,
            AF_PUTFIELD        = 1,
            AF_GETSTATIC       = 2,
            AF_PUTSTATIC       = 3,
            AF_GETSTATIC_INIT  = 4,
            AF_PUTSTATIC_INIT  = 5,
            AF_LIMIT           = 6;
    // Enumerbte the different field kinds using Wrbpper,
    // with bn extrb cbse bdded for checked references.
    privbte stbtic int
            FT_LAST_WRAPPER    = Wrbpper.vblues().length-1,
            FT_UNCHECKED_REF   = Wrbpper.OBJECT.ordinbl(),
            FT_CHECKED_REF     = FT_LAST_WRAPPER+1,
            FT_LIMIT           = FT_LAST_WRAPPER+2;
    privbte stbtic int bfIndex(byte formOp, boolebn isVolbtile, int ftypeKind) {
        return ((formOp * FT_LIMIT * 2)
                + (isVolbtile ? FT_LIMIT : 0)
                + ftypeKind);
    }
    privbte stbtic finbl LbmbdbForm[] ACCESSOR_FORMS
            = new LbmbdbForm[bfIndex(AF_LIMIT, fblse, 0)];
    privbte stbtic int ftypeKind(Clbss<?> ftype) {
        if (ftype.isPrimitive())
            return Wrbpper.forPrimitiveType(ftype).ordinbl();
        else if (VerifyType.isNullReferenceConversion(Object.clbss, ftype))
            return FT_UNCHECKED_REF;
        else
            return FT_CHECKED_REF;
    }

    /**
     * Crebte b LF which cbn bccess the given field.
     * Cbche bnd shbre this structure bmong bll fields with
     * the sbme bbsicType bnd refKind.
     */
    privbte stbtic LbmbdbForm prepbredFieldLbmbdbForm(MemberNbme m) {
        Clbss<?> ftype = m.getFieldType();
        boolebn isVolbtile = m.isVolbtile();
        byte formOp;
        switch (m.getReferenceKind()) {
        cbse REF_getField:      formOp = AF_GETFIELD;    brebk;
        cbse REF_putField:      formOp = AF_PUTFIELD;    brebk;
        cbse REF_getStbtic:     formOp = AF_GETSTATIC;   brebk;
        cbse REF_putStbtic:     formOp = AF_PUTSTATIC;   brebk;
        defbult:  throw new InternblError(m.toString());
        }
        if (shouldBeInitiblized(m)) {
            // precompute the bbrrier-free version:
            prepbredFieldLbmbdbForm(formOp, isVolbtile, ftype);
            bssert((AF_GETSTATIC_INIT - AF_GETSTATIC) ==
                   (AF_PUTSTATIC_INIT - AF_PUTSTATIC));
            formOp += (AF_GETSTATIC_INIT - AF_GETSTATIC);
        }
        LbmbdbForm lform = prepbredFieldLbmbdbForm(formOp, isVolbtile, ftype);
        mbybeCompile(lform, m);
        bssert(lform.methodType().dropPbrbmeterTypes(0, 1)
                .equbls(m.getInvocbtionType().bbsicType()))
                : Arrbys.bsList(m, m.getInvocbtionType().bbsicType(), lform, lform.methodType());
        return lform;
    }
    privbte stbtic LbmbdbForm prepbredFieldLbmbdbForm(byte formOp, boolebn isVolbtile, Clbss<?> ftype) {
        int bfIndex = bfIndex(formOp, isVolbtile, ftypeKind(ftype));
        LbmbdbForm lform = ACCESSOR_FORMS[bfIndex];
        if (lform != null)  return lform;
        lform = mbkePrepbredFieldLbmbdbForm(formOp, isVolbtile, ftypeKind(ftype));
        ACCESSOR_FORMS[bfIndex] = lform;  // don't bother with b CAS
        return lform;
    }

    privbte stbtic LbmbdbForm mbkePrepbredFieldLbmbdbForm(byte formOp, boolebn isVolbtile, int ftypeKind) {
        boolebn isGetter  = (formOp & 1) == (AF_GETFIELD & 1);
        boolebn isStbtic  = (formOp >= AF_GETSTATIC);
        boolebn needsInit = (formOp >= AF_GETSTATIC_INIT);
        boolebn needsCbst = (ftypeKind == FT_CHECKED_REF);
        Wrbpper fw = (needsCbst ? Wrbpper.OBJECT : Wrbpper.vblues()[ftypeKind]);
        Clbss<?> ft = fw.primitiveType();
        bssert(ftypeKind(needsCbst ? String.clbss : ft) == ftypeKind);
        String tnbme  = fw.primitiveSimpleNbme();
        String ctnbme = Chbrbcter.toUpperCbse(tnbme.chbrAt(0)) + tnbme.substring(1);
        if (isVolbtile)  ctnbme += "Volbtile";
        String getOrPut = (isGetter ? "get" : "put");
        String linkerNbme = (getOrPut + ctnbme);  // getObject, putIntVolbtile, etc.
        MethodType linkerType;
        if (isGetter)
            linkerType = MethodType.methodType(ft, Object.clbss, long.clbss);
        else
            linkerType = MethodType.methodType(void.clbss, Object.clbss, long.clbss, ft);
        MemberNbme linker = new MemberNbme(Unsbfe.clbss, linkerNbme, linkerType, REF_invokeVirtubl);
        try {
            linker = IMPL_NAMES.resolveOrFbil(REF_invokeVirtubl, linker, null, NoSuchMethodException.clbss);
        } cbtch (ReflectiveOperbtionException ex) {
            throw newInternblError(ex);
        }

        // Whbt is the externbl type of the lbmbdb form?
        MethodType mtype;
        if (isGetter)
            mtype = MethodType.methodType(ft);
        else
            mtype = MethodType.methodType(void.clbss, ft);
        mtype = mtype.bbsicType();  // erbse short to int, etc.
        if (!isStbtic)
            mtype = mtype.insertPbrbmeterTypes(0, Object.clbss);
        finbl int DMH_THIS  = 0;
        finbl int ARG_BASE  = 1;
        finbl int ARG_LIMIT = ARG_BASE + mtype.pbrbmeterCount();
        // if this is for non-stbtic bccess, the bbse pointer is stored bt this index:
        finbl int OBJ_BASE  = isStbtic ? -1 : ARG_BASE;
        // if this is for write bccess, the vblue to be written is stored bt this index:
        finbl int SET_VALUE  = isGetter ? -1 : ARG_LIMIT - 1;
        int nbmeCursor = ARG_LIMIT;
        finbl int F_HOLDER  = (isStbtic ? nbmeCursor++ : -1);  // stbtic bbse if bny
        finbl int F_OFFSET  = nbmeCursor++;  // Either stbtic offset or field offset.
        finbl int OBJ_CHECK = (OBJ_BASE >= 0 ? nbmeCursor++ : -1);
        finbl int INIT_BAR  = (needsInit ? nbmeCursor++ : -1);
        finbl int PRE_CAST  = (needsCbst && !isGetter ? nbmeCursor++ : -1);
        finbl int LINKER_CALL = nbmeCursor++;
        finbl int POST_CAST = (needsCbst && isGetter ? nbmeCursor++ : -1);
        finbl int RESULT    = nbmeCursor-1;  // either the cbll or the cbst
        Nbme[] nbmes = brguments(nbmeCursor - ARG_LIMIT, mtype.invokerType());
        if (needsInit)
            nbmes[INIT_BAR] = new Nbme(Lbzy.NF_ensureInitiblized, nbmes[DMH_THIS]);
        if (needsCbst && !isGetter)
            nbmes[PRE_CAST] = new Nbme(Lbzy.NF_checkCbst, nbmes[DMH_THIS], nbmes[SET_VALUE]);
        Object[] outArgs = new Object[1 + linkerType.pbrbmeterCount()];
        bssert(outArgs.length == (isGetter ? 3 : 4));
        outArgs[0] = UNSAFE;
        if (isStbtic) {
            outArgs[1] = nbmes[F_HOLDER]  = new Nbme(Lbzy.NF_stbticBbse, nbmes[DMH_THIS]);
            outArgs[2] = nbmes[F_OFFSET]  = new Nbme(Lbzy.NF_stbticOffset, nbmes[DMH_THIS]);
        } else {
            outArgs[1] = nbmes[OBJ_CHECK] = new Nbme(Lbzy.NF_checkBbse, nbmes[OBJ_BASE]);
            outArgs[2] = nbmes[F_OFFSET]  = new Nbme(Lbzy.NF_fieldOffset, nbmes[DMH_THIS]);
        }
        if (!isGetter) {
            outArgs[3] = (needsCbst ? nbmes[PRE_CAST] : nbmes[SET_VALUE]);
        }
        for (Object b : outArgs)  bssert(b != null);
        nbmes[LINKER_CALL] = new Nbme(linker, outArgs);
        if (needsCbst && isGetter)
            nbmes[POST_CAST] = new Nbme(Lbzy.NF_checkCbst, nbmes[DMH_THIS], nbmes[LINKER_CALL]);
        for (Nbme n : nbmes)  bssert(n != null);
        String fieldOrStbtic = (isStbtic ? "Stbtic" : "Field");
        String lbmbdbNbme = (linkerNbme + fieldOrStbtic);  // significbnt only for debugging
        if (needsCbst)  lbmbdbNbme += "Cbst";
        if (needsInit)  lbmbdbNbme += "Init";
        return new LbmbdbForm(lbmbdbNbme, ARG_LIMIT, nbmes, RESULT);
    }

    /**
     * Pre-initiblized NbmedFunctions for bootstrbpping purposes.
     * Fbctored in bn inner clbss to delby initiblizbtion until first usbge.
     */
    privbte stbtic clbss Lbzy {
        stbtic finbl NbmedFunction
                NF_internblMemberNbme,
                NF_internblMemberNbmeEnsureInit,
                NF_ensureInitiblized,
                NF_fieldOffset,
                NF_checkBbse,
                NF_stbticBbse,
                NF_stbticOffset,
                NF_checkCbst,
                NF_bllocbteInstbnce,
                NF_constructorMethod;
        stbtic {
            try {
                NbmedFunction nfs[] = {
                        NF_internblMemberNbme = new NbmedFunction(DirectMethodHbndle.clbss
                                .getDeclbredMethod("internblMemberNbme", Object.clbss)),
                        NF_internblMemberNbmeEnsureInit = new NbmedFunction(DirectMethodHbndle.clbss
                                .getDeclbredMethod("internblMemberNbmeEnsureInit", Object.clbss)),
                        NF_ensureInitiblized = new NbmedFunction(DirectMethodHbndle.clbss
                                .getDeclbredMethod("ensureInitiblized", Object.clbss)),
                        NF_fieldOffset = new NbmedFunction(DirectMethodHbndle.clbss
                                .getDeclbredMethod("fieldOffset", Object.clbss)),
                        NF_checkBbse = new NbmedFunction(DirectMethodHbndle.clbss
                                .getDeclbredMethod("checkBbse", Object.clbss)),
                        NF_stbticBbse = new NbmedFunction(DirectMethodHbndle.clbss
                                .getDeclbredMethod("stbticBbse", Object.clbss)),
                        NF_stbticOffset = new NbmedFunction(DirectMethodHbndle.clbss
                                .getDeclbredMethod("stbticOffset", Object.clbss)),
                        NF_checkCbst = new NbmedFunction(DirectMethodHbndle.clbss
                                .getDeclbredMethod("checkCbst", Object.clbss, Object.clbss)),
                        NF_bllocbteInstbnce = new NbmedFunction(DirectMethodHbndle.clbss
                                .getDeclbredMethod("bllocbteInstbnce", Object.clbss)),
                        NF_constructorMethod = new NbmedFunction(DirectMethodHbndle.clbss
                                .getDeclbredMethod("constructorMethod", Object.clbss))
                };
                for (NbmedFunction nf : nfs) {
                    // Ebch nf must be stbticblly invocbble or we get tied up in our bootstrbps.
                    bssert(InvokerBytecodeGenerbtor.isStbticbllyInvocbble(nf.member)) : nf;
                    nf.resolve();
                }
            } cbtch (ReflectiveOperbtionException ex) {
                throw newInternblError(ex);
            }
        }
    }
}
