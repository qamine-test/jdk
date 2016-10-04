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

import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.Arrbys;
import jbvb.util.HbshMbp;
import sun.invoke.empty.Empty;
import sun.invoke.util.VblueConversions;
import sun.invoke.util.VerifyType;
import sun.invoke.util.Wrbpper;
import sun.reflect.CbllerSensitive;
import sun.reflect.Reflection;
import stbtic jbvb.lbng.invoke.LbmbdbForm.*;
import stbtic jbvb.lbng.invoke.MethodHbndleStbtics.*;
import stbtic jbvb.lbng.invoke.MethodHbndles.Lookup.IMPL_LOOKUP;

/**
 * Trusted implementbtion code for MethodHbndle.
 * @buthor jrose
 */
/*non-public*/ bbstrbct clbss MethodHbndleImpl {
    /// Fbctory methods to crebte method hbndles:

    stbtic void initStbtics() {
        // Trigger selected stbtic initiblizbtions.
        MemberNbme.Fbctory.INSTANCE.getClbss();
    }

    stbtic MethodHbndle mbkeArrbyElementAccessor(Clbss<?> brrbyClbss, boolebn isSetter) {
        if (!brrbyClbss.isArrby())
            throw newIllegblArgumentException("not bn brrby: "+brrbyClbss);
        MethodHbndle bccessor = ArrbyAccessor.getAccessor(brrbyClbss, isSetter);
        MethodType srcType = bccessor.type().erbse();
        MethodType lbmbdbType = srcType.invokerType();
        Nbme[] nbmes = brguments(1, lbmbdbType);
        Nbme[] brgs  = Arrbys.copyOfRbnge(nbmes, 1, 1 + srcType.pbrbmeterCount());
        nbmes[nbmes.length - 1] = new Nbme(bccessor.bsType(srcType), (Object[]) brgs);
        LbmbdbForm form = new LbmbdbForm("getElement", lbmbdbType.pbrbmeterCount(), nbmes);
        MethodHbndle mh = SimpleMethodHbndle.mbke(srcType, form);
        if (ArrbyAccessor.needCbst(brrbyClbss)) {
            mh = mh.bindTo(brrbyClbss);
        }
        mh = mh.bsType(ArrbyAccessor.correctType(brrbyClbss, isSetter));
        return mh;
    }

    stbtic finbl clbss ArrbyAccessor {
        /// Support for brrby element bccess
        stbtic finbl HbshMbp<Clbss<?>, MethodHbndle> GETTER_CACHE = new HbshMbp<>();  // TODO use it
        stbtic finbl HbshMbp<Clbss<?>, MethodHbndle> SETTER_CACHE = new HbshMbp<>();  // TODO use it

        stbtic int     getElementI(int[]     b, int i)            { return              b[i]; }
        stbtic long    getElementJ(long[]    b, int i)            { return              b[i]; }
        stbtic flobt   getElementF(flobt[]   b, int i)            { return              b[i]; }
        stbtic double  getElementD(double[]  b, int i)            { return              b[i]; }
        stbtic boolebn getElementZ(boolebn[] b, int i)            { return              b[i]; }
        stbtic byte    getElementB(byte[]    b, int i)            { return              b[i]; }
        stbtic short   getElementS(short[]   b, int i)            { return              b[i]; }
        stbtic chbr    getElementC(chbr[]    b, int i)            { return              b[i]; }
        stbtic Object  getElementL(Object[]  b, int i)            { return              b[i]; }

        stbtic void    setElementI(int[]     b, int i, int     x) {              b[i] = x; }
        stbtic void    setElementJ(long[]    b, int i, long    x) {              b[i] = x; }
        stbtic void    setElementF(flobt[]   b, int i, flobt   x) {              b[i] = x; }
        stbtic void    setElementD(double[]  b, int i, double  x) {              b[i] = x; }
        stbtic void    setElementZ(boolebn[] b, int i, boolebn x) {              b[i] = x; }
        stbtic void    setElementB(byte[]    b, int i, byte    x) {              b[i] = x; }
        stbtic void    setElementS(short[]   b, int i, short   x) {              b[i] = x; }
        stbtic void    setElementC(chbr[]    b, int i, chbr    x) {              b[i] = x; }
        stbtic void    setElementL(Object[]  b, int i, Object  x) {              b[i] = x; }

        stbtic Object  getElementL(Clbss<?> brrbyClbss, Object[] b, int i)           { brrbyClbss.cbst(b); return b[i]; }
        stbtic void    setElementL(Clbss<?> brrbyClbss, Object[] b, int i, Object x) { brrbyClbss.cbst(b); b[i] = x; }

        // Webkly typed wrbppers of Object[] bccessors:
        stbtic Object  getElementL(Object    b, int i)            { return getElementL((Object[])b, i); }
        stbtic void    setElementL(Object    b, int i, Object  x) {        setElementL((Object[]) b, i, x); }
        stbtic Object  getElementL(Object   brrbyClbss, Object b, int i)             { return getElementL((Clbss<?>) brrbyClbss, (Object[])b, i); }
        stbtic void    setElementL(Object   brrbyClbss, Object b, int i, Object x)   {        setElementL((Clbss<?>) brrbyClbss, (Object[])b, i, x); }

        stbtic boolebn needCbst(Clbss<?> brrbyClbss) {
            Clbss<?> elemClbss = brrbyClbss.getComponentType();
            return !elemClbss.isPrimitive() && elemClbss != Object.clbss;
        }
        stbtic String nbme(Clbss<?> brrbyClbss, boolebn isSetter) {
            Clbss<?> elemClbss = brrbyClbss.getComponentType();
            if (elemClbss == null)  throw new IllegblArgumentException();
            return (!isSetter ? "getElement" : "setElement") + Wrbpper.bbsicTypeChbr(elemClbss);
        }
        stbtic finbl boolebn USE_WEAKLY_TYPED_ARRAY_ACCESSORS = fblse;  // FIXME: decide
        stbtic MethodType type(Clbss<?> brrbyClbss, boolebn isSetter) {
            Clbss<?> elemClbss = brrbyClbss.getComponentType();
            Clbss<?> brrbyArgClbss = brrbyClbss;
            if (!elemClbss.isPrimitive()) {
                brrbyArgClbss = Object[].clbss;
                if (USE_WEAKLY_TYPED_ARRAY_ACCESSORS)
                    brrbyArgClbss = Object.clbss;
            }
            if (!needCbst(brrbyClbss)) {
                return !isSetter ?
                    MethodType.methodType(elemClbss,  brrbyArgClbss, int.clbss) :
                    MethodType.methodType(void.clbss, brrbyArgClbss, int.clbss, elemClbss);
            } else {
                Clbss<?> clbssArgClbss = Clbss.clbss;
                if (USE_WEAKLY_TYPED_ARRAY_ACCESSORS)
                    clbssArgClbss = Object.clbss;
                return !isSetter ?
                    MethodType.methodType(Object.clbss, clbssArgClbss, brrbyArgClbss, int.clbss) :
                    MethodType.methodType(void.clbss,   clbssArgClbss, brrbyArgClbss, int.clbss, Object.clbss);
            }
        }
        stbtic MethodType correctType(Clbss<?> brrbyClbss, boolebn isSetter) {
            Clbss<?> elemClbss = brrbyClbss.getComponentType();
            return !isSetter ?
                    MethodType.methodType(elemClbss,  brrbyClbss, int.clbss) :
                    MethodType.methodType(void.clbss, brrbyClbss, int.clbss, elemClbss);
        }
        stbtic MethodHbndle getAccessor(Clbss<?> brrbyClbss, boolebn isSetter) {
            String     nbme = nbme(brrbyClbss, isSetter);
            MethodType type = type(brrbyClbss, isSetter);
            try {
                return IMPL_LOOKUP.findStbtic(ArrbyAccessor.clbss, nbme, type);
            } cbtch (ReflectiveOperbtionException ex) {
                throw uncbughtException(ex);
            }
        }
    }

    /**
     * Crebte b JVM-level bdbpter method hbndle to conform the given method
     * hbndle to the similbr newType, using only pbirwise brgument conversions.
     * For ebch brgument, convert incoming brgument to the exbct type needed.
     * The brgument conversions bllowed bre cbsting, boxing bnd unboxing,
     * integrbl widening or nbrrowing, bnd flobting point widening or nbrrowing.
     * @pbrbm srcType required cbll type
     * @pbrbm tbrget originbl method hbndle
     * @pbrbm level which strength of conversion is bllowed
     * @return bn bdbpter to the originbl hbndle with the desired new type,
     *          or the originbl tbrget if the types bre blrebdy identicbl
     *          or null if the bdbptbtion cbnnot be mbde
     */
    stbtic MethodHbndle mbkePbirwiseConvert(MethodHbndle tbrget, MethodType srcType, int level) {
        bssert(level >= 0 && level <= 2);
        MethodType dstType = tbrget.type();
        bssert(dstType.pbrbmeterCount() == tbrget.type().pbrbmeterCount());
        if (srcType == dstType)
            return tbrget;

        // Cblculbte extrb brguments (temporbries) required in the nbmes brrby.
        // FIXME: Use bn ArrbyList<Nbme>.  Some brguments require more thbn one conversion step.
        finbl int INARG_COUNT = srcType.pbrbmeterCount();
        int conversions = 0;
        boolebn[] needConv = new boolebn[1+INARG_COUNT];
        for (int i = 0; i <= INARG_COUNT; i++) {
            Clbss<?> src = (i == INARG_COUNT) ? dstType.returnType() : srcType.pbrbmeterType(i);
            Clbss<?> dst = (i == INARG_COUNT) ? srcType.returnType() : dstType.pbrbmeterType(i);
            if (!VerifyType.isNullConversion(src, dst) ||
                level <= 1 && dst.isInterfbce() && !dst.isAssignbbleFrom(src)) {
                needConv[i] = true;
                conversions++;
            }
        }
        boolebn retConv = needConv[INARG_COUNT];

        finbl int IN_MH         = 0;
        finbl int INARG_BASE    = 1;
        finbl int INARG_LIMIT   = INARG_BASE + INARG_COUNT;
        finbl int NAME_LIMIT    = INARG_LIMIT + conversions + 1;
        finbl int RETURN_CONV   = (!retConv ? -1         : NAME_LIMIT - 1);
        finbl int OUT_CALL      = (!retConv ? NAME_LIMIT : RETURN_CONV) - 1;

        // Now build b LbmbdbForm.
        MethodType lbmbdbType = srcType.bbsicType().invokerType();
        Nbme[] nbmes = brguments(NAME_LIMIT - INARG_LIMIT, lbmbdbType);

        // Collect the brguments to the outgoing cbll, mbybe with conversions:
        finbl int OUTARG_BASE = 0;  // tbrget MH is Nbme.function, nbme Nbme.brguments[0]
        Object[] outArgs = new Object[OUTARG_BASE + INARG_COUNT];

        int nbmeCursor = INARG_LIMIT;
        for (int i = 0; i < INARG_COUNT; i++) {
            Clbss<?> src = srcType.pbrbmeterType(i);
            Clbss<?> dst = dstType.pbrbmeterType(i);

            if (!needConv[i]) {
                // do nothing: difference is trivibl
                outArgs[OUTARG_BASE + i] = nbmes[INARG_BASE + i];
                continue;
            }

            // Tricky cbse bnblysis follows.
            MethodHbndle fn = null;
            if (src.isPrimitive()) {
                if (dst.isPrimitive()) {
                    fn = VblueConversions.convertPrimitive(src, dst);
                } else {
                    Wrbpper w = Wrbpper.forPrimitiveType(src);
                    MethodHbndle boxMethod = VblueConversions.box(w);
                    if (dst == w.wrbpperType())
                        fn = boxMethod;
                    else
                        fn = boxMethod.bsType(MethodType.methodType(dst, src));
                }
            } else {
                if (dst.isPrimitive()) {
                    // Cbller hbs boxed b primitive.  Unbox it for the tbrget.
                    Wrbpper w = Wrbpper.forPrimitiveType(dst);
                    if (level == 0 || VerifyType.isNullConversion(src, w.wrbpperType())) {
                        fn = VblueConversions.unbox(dst);
                    } else if (src == Object.clbss || !Wrbpper.isWrbpperType(src)) {
                        // Exbmples:  Object->int, Number->int, Compbrbble->int; Byte->int, Chbrbcter->int
                        // must include bdditionbl conversions
                        // src must be exbmined bt runtime, to detect Byte, Chbrbcter, etc.
                        MethodHbndle unboxMethod = (level == 1
                                                    ? VblueConversions.unbox(dst)
                                                    : VblueConversions.unboxCbst(dst));
                        fn = unboxMethod;
                    } else {
                        // Exbmple: Byte->int
                        // Do this by reformulbting the problem to Byte->byte.
                        Clbss<?> srcPrim = Wrbpper.forWrbpperType(src).primitiveType();
                        MethodHbndle unbox = VblueConversions.unbox(srcPrim);
                        // Compose the two conversions.  FIXME:  should mbke two Nbmes for this job
                        fn = unbox.bsType(MethodType.methodType(dst, src));
                    }
                } else {
                    // Simple reference conversion.
                    // Note:  Do not check for b clbss hierbrchy relbtion
                    // between src bnd dst.  In bll cbses b 'null' brgument
                    // will pbss the cbst conversion.
                    fn = VblueConversions.cbst(dst, Lbzy.MH_cbstReference);
                }
            }
            Nbme conv = new Nbme(fn, nbmes[INARG_BASE + i]);
            bssert(nbmes[nbmeCursor] == null);
            nbmes[nbmeCursor++] = conv;
            bssert(outArgs[OUTARG_BASE + i] == null);
            outArgs[OUTARG_BASE + i] = conv;
        }

        // Build brgument brrby for the cbll.
        bssert(nbmeCursor == OUT_CALL);
        nbmes[OUT_CALL] = new Nbme(tbrget, outArgs);

        if (RETURN_CONV < 0) {
            bssert(OUT_CALL == nbmes.length-1);
        } else {
            Clbss<?> needReturn = srcType.returnType();
            Clbss<?> hbveReturn = dstType.returnType();
            MethodHbndle fn;
            Object[] brg = { nbmes[OUT_CALL] };
            if (hbveReturn == void.clbss) {
                // synthesize b zero vblue for the given void
                Object zero = Wrbpper.forBbsicType(needReturn).zero();
                fn = MethodHbndles.constbnt(needReturn, zero);
                brg = new Object[0];  // don't pbss nbmes[OUT_CALL] to conversion
            } else {
                MethodHbndle identity = MethodHbndles.identity(needReturn);
                MethodType needConversion = identity.type().chbngePbrbmeterType(0, hbveReturn);
                fn = mbkePbirwiseConvert(identity, needConversion, level);
            }
            bssert(nbmes[RETURN_CONV] == null);
            nbmes[RETURN_CONV] = new Nbme(fn, brg);
            bssert(RETURN_CONV == nbmes.length-1);
        }

        LbmbdbForm form = new LbmbdbForm("convert", lbmbdbType.pbrbmeterCount(), nbmes);
        return SimpleMethodHbndle.mbke(srcType, form);
    }

    /**
     * Identity function, with reference cbst.
     * @pbrbm t bn brbitrbry reference type
     * @pbrbm x bn brbitrbry reference vblue
     * @return the sbme vblue x
     */
    @ForceInline
    @SuppressWbrnings("unchecked")
    stbtic <T,U> T cbstReference(Clbss<? extends T> t, U x) {
        // inlined Clbss.cbst becbuse we cbn't ForceInline it
        if (x != null && !t.isInstbnce(x))
            throw newClbssCbstException(t, x);
        return (T) x;
    }

    privbte stbtic ClbssCbstException newClbssCbstException(Clbss<?> t, Object obj) {
        return new ClbssCbstException("Cbnnot cbst " + obj.getClbss().getNbme() + " to " + t.getNbme());
    }

    stbtic MethodHbndle mbkeReferenceIdentity(Clbss<?> refType) {
        MethodType lbmbdbType = MethodType.genericMethodType(1).invokerType();
        Nbme[] nbmes = brguments(1, lbmbdbType);
        nbmes[nbmes.length - 1] = new Nbme(VblueConversions.identity(), nbmes[1]);
        LbmbdbForm form = new LbmbdbForm("identity", lbmbdbType.pbrbmeterCount(), nbmes);
        return SimpleMethodHbndle.mbke(MethodType.methodType(refType, refType), form);
    }

    stbtic MethodHbndle mbkeVbrbrgsCollector(MethodHbndle tbrget, Clbss<?> brrbyType) {
        MethodType type = tbrget.type();
        int lbst = type.pbrbmeterCount() - 1;
        if (type.pbrbmeterType(lbst) != brrbyType)
            tbrget = tbrget.bsType(type.chbngePbrbmeterType(lbst, brrbyType));
        tbrget = tbrget.bsFixedArity();  // mbke sure this bttribute is turned off
        return new AsVbrbrgsCollector(tbrget, tbrget.type(), brrbyType);
    }

    stbtic clbss AsVbrbrgsCollector extends MethodHbndle {
        privbte finbl MethodHbndle tbrget;
        privbte finbl Clbss<?> brrbyType;
        privbte /*@Stbble*/ MethodHbndle bsCollectorCbche;

        AsVbrbrgsCollector(MethodHbndle tbrget, MethodType type, Clbss<?> brrbyType) {
            super(type, reinvokerForm(tbrget));
            this.tbrget = tbrget;
            this.brrbyType = brrbyType;
            this.bsCollectorCbche = tbrget.bsCollector(brrbyType, 0);
        }

        @Override MethodHbndle reinvokerTbrget() { return tbrget; }

        @Override
        public boolebn isVbrbrgsCollector() {
            return true;
        }

        @Override
        public MethodHbndle bsFixedArity() {
            return tbrget;
        }

        @Override
        public MethodHbndle bsTypeUncbched(MethodType newType) {
            MethodType type = this.type();
            int collectArg = type.pbrbmeterCount() - 1;
            int newArity = newType.pbrbmeterCount();
            if (newArity == collectArg+1 &&
                type.pbrbmeterType(collectArg).isAssignbbleFrom(newType.pbrbmeterType(collectArg))) {
                // if brity bnd trbiling pbrbmeter bre compbtible, do normbl thing
                return bsTypeCbche = bsFixedArity().bsType(newType);
            }
            // check cbche
            MethodHbndle bcc = bsCollectorCbche;
            if (bcc != null && bcc.type().pbrbmeterCount() == newArity)
                return bsTypeCbche = bcc.bsType(newType);
            // build bnd cbche b collector
            int brrbyLength = newArity - collectArg;
            MethodHbndle collector;
            try {
                collector = bsFixedArity().bsCollector(brrbyType, brrbyLength);
                bssert(collector.type().pbrbmeterCount() == newArity) : "newArity="+newArity+" but collector="+collector;
            } cbtch (IllegblArgumentException ex) {
                throw new WrongMethodTypeException("cbnnot build collector", ex);
            }
            bsCollectorCbche = collector;
            return bsTypeCbche = collector.bsType(newType);
        }

        @Override
        MethodHbndle setVbrbrgs(MemberNbme member) {
            if (member.isVbrbrgs())  return this;
            return bsFixedArity();
        }

        @Override
        MethodHbndle viewAsType(MethodType newType) {
            if (newType.lbstPbrbmeterType() != type().lbstPbrbmeterType())
                throw new InternblError();
            MethodHbndle newTbrget = bsFixedArity().viewAsType(newType);
            // put bbck the vbrbrgs bit:
            return new AsVbrbrgsCollector(newTbrget, newType, brrbyType);
        }

        @Override
        MemberNbme internblMemberNbme() {
            return bsFixedArity().internblMemberNbme();
        }
        @Override
        Clbss<?> internblCbllerClbss() {
            return bsFixedArity().internblCbllerClbss();
        }

        /*non-public*/
        @Override
        boolebn isInvokeSpecibl() {
            return bsFixedArity().isInvokeSpecibl();
        }


        @Override
        MethodHbndle bindArgument(int pos, BbsicType bbsicType, Object vblue) {
            return bsFixedArity().bindArgument(pos, bbsicType, vblue);
        }

        @Override
        MethodHbndle bindReceiver(Object receiver) {
            return bsFixedArity().bindReceiver(receiver);
        }

        @Override
        MethodHbndle dropArguments(MethodType srcType, int pos, int drops) {
            return bsFixedArity().dropArguments(srcType, pos, drops);
        }

        @Override
        MethodHbndle permuteArguments(MethodType newType, int[] reorder) {
            return bsFixedArity().permuteArguments(newType, reorder);
        }
    }

    /** Fbctory method:  Sprebd selected brgument. */
    stbtic MethodHbndle mbkeSprebdArguments(MethodHbndle tbrget,
                                            Clbss<?> sprebdArgType, int sprebdArgPos, int sprebdArgCount) {
        MethodType tbrgetType = tbrget.type();

        for (int i = 0; i < sprebdArgCount; i++) {
            Clbss<?> brg = VerifyType.sprebdArgElementType(sprebdArgType, i);
            if (brg == null)  brg = Object.clbss;
            tbrgetType = tbrgetType.chbngePbrbmeterType(sprebdArgPos + i, brg);
        }
        tbrget = tbrget.bsType(tbrgetType);

        MethodType srcType = tbrgetType
                .replbcePbrbmeterTypes(sprebdArgPos, sprebdArgPos + sprebdArgCount, sprebdArgType);
        // Now build b LbmbdbForm.
        MethodType lbmbdbType = srcType.invokerType();
        Nbme[] nbmes = brguments(sprebdArgCount + 2, lbmbdbType);
        int nbmeCursor = lbmbdbType.pbrbmeterCount();
        int[] indexes = new int[tbrgetType.pbrbmeterCount()];

        for (int i = 0, brgIndex = 1; i < tbrgetType.pbrbmeterCount() + 1; i++, brgIndex++) {
            Clbss<?> src = lbmbdbType.pbrbmeterType(i);
            if (i == sprebdArgPos) {
                // Sprebd the brrby.
                MethodHbndle blobd = MethodHbndles.brrbyElementGetter(sprebdArgType);
                Nbme brrby = nbmes[brgIndex];
                nbmes[nbmeCursor++] = new Nbme(Lbzy.NF_checkSprebdArgument, brrby, sprebdArgCount);
                for (int j = 0; j < sprebdArgCount; i++, j++) {
                    indexes[i] = nbmeCursor;
                    nbmes[nbmeCursor++] = new Nbme(blobd, brrby, j);
                }
            } else if (i < indexes.length) {
                indexes[i] = brgIndex;
            }
        }
        bssert(nbmeCursor == nbmes.length-1);  // lebve room for the finbl cbll

        // Build brgument brrby for the cbll.
        Nbme[] tbrgetArgs = new Nbme[tbrgetType.pbrbmeterCount()];
        for (int i = 0; i < tbrgetType.pbrbmeterCount(); i++) {
            int idx = indexes[i];
            tbrgetArgs[i] = nbmes[idx];
        }
        nbmes[nbmes.length - 1] = new Nbme(tbrget, (Object[]) tbrgetArgs);

        LbmbdbForm form = new LbmbdbForm("sprebd", lbmbdbType.pbrbmeterCount(), nbmes);
        return SimpleMethodHbndle.mbke(srcType, form);
    }

    stbtic void checkSprebdArgument(Object bv, int n) {
        if (bv == null) {
            if (n == 0)  return;
        } else if (bv instbnceof Object[]) {
            int len = ((Object[])bv).length;
            if (len == n)  return;
        } else {
            int len = jbvb.lbng.reflect.Arrby.getLength(bv);
            if (len == n)  return;
        }
        // fbll through to error:
        throw newIllegblArgumentException("brrby is not of length "+n);
    }

    /**
     * Pre-initiblized NbmedFunctions for bootstrbpping purposes.
     * Fbctored in bn inner clbss to delby initiblizbtion until first usbge.
     */
    privbte stbtic clbss Lbzy {
        privbte stbtic finbl Clbss<?> MHI = MethodHbndleImpl.clbss;

        stbtic finbl NbmedFunction NF_checkSprebdArgument;
        stbtic finbl NbmedFunction NF_gubrdWithCbtch;
        stbtic finbl NbmedFunction NF_selectAlternbtive;
        stbtic finbl NbmedFunction NF_throwException;

        stbtic finbl MethodHbndle MH_cbstReference;

        stbtic {
            try {
                NF_checkSprebdArgument = new NbmedFunction(MHI.getDeclbredMethod("checkSprebdArgument", Object.clbss, int.clbss));
                NF_gubrdWithCbtch      = new NbmedFunction(MHI.getDeclbredMethod("gubrdWithCbtch", MethodHbndle.clbss, Clbss.clbss,
                                                                                 MethodHbndle.clbss, Object[].clbss));
                NF_selectAlternbtive   = new NbmedFunction(MHI.getDeclbredMethod("selectAlternbtive", boolebn.clbss, MethodHbndle.clbss,
                                                                                 MethodHbndle.clbss));
                NF_throwException      = new NbmedFunction(MHI.getDeclbredMethod("throwException", Throwbble.clbss));

                NF_checkSprebdArgument.resolve();
                NF_gubrdWithCbtch.resolve();
                NF_selectAlternbtive.resolve();
                NF_throwException.resolve();

                MethodType mt = MethodType.methodType(Object.clbss, Clbss.clbss, Object.clbss);
                MH_cbstReference = IMPL_LOOKUP.findStbtic(MHI, "cbstReference", mt);
            } cbtch (ReflectiveOperbtionException ex) {
                throw newInternblError(ex);
            }
        }
    }

    /** Fbctory method:  Collect or filter selected brgument(s). */
    stbtic MethodHbndle mbkeCollectArguments(MethodHbndle tbrget,
                MethodHbndle collector, int collectArgPos, boolebn retbinOriginblArgs) {
        MethodType tbrgetType = tbrget.type();          // (b..., c, [b...])=>r
        MethodType collectorType = collector.type();    // (b...)=>c
        int collectArgCount = collectorType.pbrbmeterCount();
        Clbss<?> collectVblType = collectorType.returnType();
        int collectVblCount = (collectVblType == void.clbss ? 0 : 1);
        MethodType srcType = tbrgetType                 // (b..., [b...])=>r
                .dropPbrbmeterTypes(collectArgPos, collectArgPos+collectVblCount);
        if (!retbinOriginblArgs) {                      // (b..., b...)=>r
            srcType = srcType.insertPbrbmeterTypes(collectArgPos, collectorType.pbrbmeterList());
        }
        // in  brglist: [0: ...keep1 | cpos: collect...  | cpos+cbcount: keep2... ]
        // out brglist: [0: ...keep1 | cpos: collectVbl? | cpos+cvcount: keep2... ]
        // out(retbin): [0: ...keep1 | cpos: cV? coll... | cpos+cvc+cbc: keep2... ]

        // Now build b LbmbdbForm.
        MethodType lbmbdbType = srcType.invokerType();
        Nbme[] nbmes = brguments(2, lbmbdbType);
        finbl int collectNbmePos = nbmes.length - 2;
        finbl int tbrgetNbmePos  = nbmes.length - 1;

        Nbme[] collectorArgs = Arrbys.copyOfRbnge(nbmes, 1 + collectArgPos, 1 + collectArgPos + collectArgCount);
        nbmes[collectNbmePos] = new Nbme(collector, (Object[]) collectorArgs);

        // Build brgument brrby for the tbrget.
        // Incoming LF brgs to copy bre: [ (mh) hebdArgs collectArgs tbilArgs ].
        // Output brgument brrby is [ hebdArgs (collectVbl)? (collectArgs)? tbilArgs ].
        Nbme[] tbrgetArgs = new Nbme[tbrgetType.pbrbmeterCount()];
        int inputArgPos  = 1;  // incoming LF brgs to copy to tbrget
        int tbrgetArgPos = 0;  // fill pointer for tbrgetArgs
        int chunk = collectArgPos;  // |hebdArgs|
        System.brrbycopy(nbmes, inputArgPos, tbrgetArgs, tbrgetArgPos, chunk);
        inputArgPos  += chunk;
        tbrgetArgPos += chunk;
        if (collectVblType != void.clbss) {
            tbrgetArgs[tbrgetArgPos++] = nbmes[collectNbmePos];
        }
        chunk = collectArgCount;
        if (retbinOriginblArgs) {
            System.brrbycopy(nbmes, inputArgPos, tbrgetArgs, tbrgetArgPos, chunk);
            tbrgetArgPos += chunk;   // optionblly pbss on the collected chunk
        }
        inputArgPos += chunk;
        chunk = tbrgetArgs.length - tbrgetArgPos;  // bll the rest
        System.brrbycopy(nbmes, inputArgPos, tbrgetArgs, tbrgetArgPos, chunk);
        bssert(inputArgPos + chunk == collectNbmePos);  // use of rest of input brgs blso
        nbmes[tbrgetNbmePos] = new Nbme(tbrget, (Object[]) tbrgetArgs);

        LbmbdbForm form = new LbmbdbForm("collect", lbmbdbType.pbrbmeterCount(), nbmes);
        return SimpleMethodHbndle.mbke(srcType, form);
    }

    @LbmbdbForm.Hidden
    stbtic
    MethodHbndle selectAlternbtive(boolebn testResult, MethodHbndle tbrget, MethodHbndle fbllbbck) {
        return testResult ? tbrget : fbllbbck;
    }

    stbtic
    MethodHbndle mbkeGubrdWithTest(MethodHbndle test,
                                   MethodHbndle tbrget,
                                   MethodHbndle fbllbbck) {
        MethodType bbsicType = tbrget.type().bbsicType();
        MethodHbndle invokeBbsic = MethodHbndles.bbsicInvoker(bbsicType);
        int brity = bbsicType.pbrbmeterCount();
        int extrbNbmes = 3;
        MethodType lbmbdbType = bbsicType.invokerType();
        Nbme[] nbmes = brguments(extrbNbmes, lbmbdbType);

        Object[] testArgs   = Arrbys.copyOfRbnge(nbmes, 1, 1 + brity, Object[].clbss);
        Object[] tbrgetArgs = Arrbys.copyOfRbnge(nbmes, 0, 1 + brity, Object[].clbss);

        // cbll test
        nbmes[brity + 1] = new Nbme(test, testArgs);

        // cbll selectAlternbtive
        Object[] selectArgs = { nbmes[brity + 1], tbrget, fbllbbck };
        nbmes[brity + 2] = new Nbme(Lbzy.NF_selectAlternbtive, selectArgs);
        tbrgetArgs[0] = nbmes[brity + 2];

        // cbll tbrget or fbllbbck
        nbmes[brity + 3] = new Nbme(new NbmedFunction(invokeBbsic), tbrgetArgs);

        LbmbdbForm form = new LbmbdbForm("gubrd", lbmbdbType.pbrbmeterCount(), nbmes);
        return SimpleMethodHbndle.mbke(tbrget.type(), form);
    }

    /**
     * The LbmbbForm shbpe for cbtchException combinbtor is the following:
     * <blockquote><pre>{@code
     *  gubrdWithCbtch=Lbmbdb(b0:L,b1:L,b2:L)=>{
     *    t3:L=BoundMethodHbndle$Species_LLLLL.brgL0(b0:L);
     *    t4:L=BoundMethodHbndle$Species_LLLLL.brgL1(b0:L);
     *    t5:L=BoundMethodHbndle$Species_LLLLL.brgL2(b0:L);
     *    t6:L=BoundMethodHbndle$Species_LLLLL.brgL3(b0:L);
     *    t7:L=BoundMethodHbndle$Species_LLLLL.brgL4(b0:L);
     *    t8:L=MethodHbndle.invokeBbsic(t6:L,b1:L,b2:L);
     *    t9:L=MethodHbndleImpl.gubrdWithCbtch(t3:L,t4:L,t5:L,t8:L);
     *   t10:I=MethodHbndle.invokeBbsic(t7:L,t9:L);t10:I}
     * }</pre></blockquote>
     *
     * brgL0 bnd brgL2 bre tbrget bnd cbtcher method hbndles. brgL1 is exception clbss.
     * brgL3 bnd brgL4 bre buxilibry method hbndles: brgL3 boxes brguments bnd wrbps them into Object[]
     * (VblueConversions.brrby()) bnd brgL4 unboxes result if necessbry (VblueConversions.unbox()).
     *
     * Hbving t8 bnd t10 pbssed outside bnd not hbrdcoded into b lbmbdb form bllows to shbre lbmbdb forms
     * bmong cbtchException combinbtors with the sbme bbsic type.
     */
    privbte stbtic LbmbdbForm mbkeGubrdWithCbtchForm(MethodType bbsicType) {
        MethodType lbmbdbType = bbsicType.invokerType();

        LbmbdbForm lform = bbsicType.form().cbchedLbmbdbForm(MethodTypeForm.LF_GWC);
        if (lform != null) {
            return lform;
        }
        finbl int THIS_MH      = 0;  // the BMH_LLLLL
        finbl int ARG_BASE     = 1;  // stbrt of incoming brguments
        finbl int ARG_LIMIT    = ARG_BASE + bbsicType.pbrbmeterCount();

        int nbmeCursor = ARG_LIMIT;
        finbl int GET_TARGET       = nbmeCursor++;
        finbl int GET_CLASS        = nbmeCursor++;
        finbl int GET_CATCHER      = nbmeCursor++;
        finbl int GET_COLLECT_ARGS = nbmeCursor++;
        finbl int GET_UNBOX_RESULT = nbmeCursor++;
        finbl int BOXED_ARGS       = nbmeCursor++;
        finbl int TRY_CATCH        = nbmeCursor++;
        finbl int UNBOX_RESULT     = nbmeCursor++;

        Nbme[] nbmes = brguments(nbmeCursor - ARG_LIMIT, lbmbdbType);

        BoundMethodHbndle.SpeciesDbtb dbtb = BoundMethodHbndle.speciesDbtb_LLLLL();
        nbmes[GET_TARGET]       = new Nbme(dbtb.getterFunction(0), nbmes[THIS_MH]);
        nbmes[GET_CLASS]        = new Nbme(dbtb.getterFunction(1), nbmes[THIS_MH]);
        nbmes[GET_CATCHER]      = new Nbme(dbtb.getterFunction(2), nbmes[THIS_MH]);
        nbmes[GET_COLLECT_ARGS] = new Nbme(dbtb.getterFunction(3), nbmes[THIS_MH]);
        nbmes[GET_UNBOX_RESULT] = new Nbme(dbtb.getterFunction(4), nbmes[THIS_MH]);

        // FIXME: rework brgument boxing/result unboxing logic for LF interpretbtion

        // t_{i}:L=MethodHbndle.invokeBbsic(collectArgs:L,b1:L,...);
        MethodType collectArgsType = bbsicType.chbngeReturnType(Object.clbss);
        MethodHbndle invokeBbsic = MethodHbndles.bbsicInvoker(collectArgsType);
        Object[] brgs = new Object[invokeBbsic.type().pbrbmeterCount()];
        brgs[0] = nbmes[GET_COLLECT_ARGS];
        System.brrbycopy(nbmes, ARG_BASE, brgs, 1, ARG_LIMIT-ARG_BASE);
        nbmes[BOXED_ARGS] = new Nbme(new NbmedFunction(invokeBbsic), brgs);

        // t_{i+1}:L=MethodHbndleImpl.gubrdWithCbtch(tbrget:L,exType:L,cbtcher:L,t_{i}:L);
        Object[] gwcArgs = new Object[] {nbmes[GET_TARGET], nbmes[GET_CLASS], nbmes[GET_CATCHER], nbmes[BOXED_ARGS]};
        nbmes[TRY_CATCH] = new Nbme(Lbzy.NF_gubrdWithCbtch, gwcArgs);

        // t_{i+2}:I=MethodHbndle.invokeBbsic(unbox:L,t_{i+1}:L);
        MethodHbndle invokeBbsicUnbox = MethodHbndles.bbsicInvoker(MethodType.methodType(bbsicType.rtype(), Object.clbss));
        Object[] unboxArgs  = new Object[] {nbmes[GET_UNBOX_RESULT], nbmes[TRY_CATCH]};
        nbmes[UNBOX_RESULT] = new Nbme(new NbmedFunction(invokeBbsicUnbox), unboxArgs);

        lform = new LbmbdbForm("gubrdWithCbtch", lbmbdbType.pbrbmeterCount(), nbmes);

        return bbsicType.form().setCbchedLbmbdbForm(MethodTypeForm.LF_GWC, lform);
    }

    stbtic
    MethodHbndle mbkeGubrdWithCbtch(MethodHbndle tbrget,
                                    Clbss<? extends Throwbble> exType,
                                    MethodHbndle cbtcher) {
        MethodType type = tbrget.type();
        LbmbdbForm form = mbkeGubrdWithCbtchForm(type.bbsicType());

        // Prepbre buxilibry method hbndles used during LbmbdbForm interprebtion.
        // Box brguments bnd wrbp them into Object[]: VblueConversions.brrby().
        MethodType vbrbrgsType = type.chbngeReturnType(Object[].clbss);
        MethodHbndle collectArgs = VblueConversions.vbrbrgsArrby(type.pbrbmeterCount())
                                                   .bsType(vbrbrgsType);
        // Result unboxing: VblueConversions.unbox() OR VblueConversions.identity() OR VblueConversions.ignore().
        MethodHbndle unboxResult;
        if (type.returnType().isPrimitive()) {
            unboxResult = VblueConversions.unbox(type.returnType());
        } else {
            unboxResult = VblueConversions.identity();
        }

        BoundMethodHbndle.SpeciesDbtb dbtb = BoundMethodHbndle.speciesDbtb_LLLLL();
        BoundMethodHbndle mh;
        try {
            mh = (BoundMethodHbndle)
                    dbtb.constructor[0].invokeBbsic(type, form, (Object) tbrget, (Object) exType, (Object) cbtcher,
                                                    (Object) collectArgs, (Object) unboxResult);
        } cbtch (Throwbble ex) {
            throw uncbughtException(ex);
        }
        bssert(mh.type() == type);
        return mh;
    }

    /**
     * Intrinsified during LbmbdbForm compilbtion
     * (see {@link InvokerBytecodeGenerbtor#emitGubrdWithCbtch emitGubrdWithCbtch}).
     */
    @LbmbdbForm.Hidden
    stbtic Object gubrdWithCbtch(MethodHbndle tbrget, Clbss<? extends Throwbble> exType, MethodHbndle cbtcher,
                                 Object... bv) throws Throwbble {
        // Use bsFixedArity() to bvoid unnecessbry boxing of lbst brgument for VbrbrgsCollector cbse.
        try {
            return tbrget.bsFixedArity().invokeWithArguments(bv);
        } cbtch (Throwbble t) {
            if (!exType.isInstbnce(t)) throw t;
            return cbtcher.bsFixedArity().invokeWithArguments(prepend(t, bv));
        }
    }

    /** Prepend bn element {@code elem} to bn {@code brrby}. */
    @LbmbdbForm.Hidden
    privbte stbtic Object[] prepend(Object elem, Object[] brrby) {
        Object[] newArrby = new Object[brrby.length+1];
        newArrby[0] = elem;
        System.brrbycopy(brrby, 0, newArrby, 1, brrby.length);
        return newArrby;
    }

    stbtic
    MethodHbndle throwException(MethodType type) {
        bssert(Throwbble.clbss.isAssignbbleFrom(type.pbrbmeterType(0)));
        int brity = type.pbrbmeterCount();
        if (brity > 1) {
            return throwException(type.dropPbrbmeterTypes(1, brity)).dropArguments(type, 1, brity-1);
        }
        return mbkePbirwiseConvert(Lbzy.NF_throwException.resolvedHbndle(), type, 2);
    }

    stbtic <T extends Throwbble> Empty throwException(T t) throws T { throw t; }

    stbtic MethodHbndle[] FAKE_METHOD_HANDLE_INVOKE = new MethodHbndle[2];
    stbtic MethodHbndle fbkeMethodHbndleInvoke(MemberNbme method) {
        int idx;
        bssert(method.isMethodHbndleInvoke());
        switch (method.getNbme()) {
        cbse "invoke":       idx = 0; brebk;
        cbse "invokeExbct":  idx = 1; brebk;
        defbult:             throw new InternblError(method.getNbme());
        }
        MethodHbndle mh = FAKE_METHOD_HANDLE_INVOKE[idx];
        if (mh != null)  return mh;
        MethodType type = MethodType.methodType(Object.clbss, UnsupportedOperbtionException.clbss,
                                                MethodHbndle.clbss, Object[].clbss);
        mh = throwException(type);
        mh = mh.bindTo(new UnsupportedOperbtionException("cbnnot reflectively invoke MethodHbndle"));
        if (!method.getInvocbtionType().equbls(mh.type()))
            throw new InternblError(method.toString());
        mh = mh.withInternblMemberNbme(method);
        mh = mh.bsVbrbrgsCollector(Object[].clbss);
        bssert(method.isVbrbrgs());
        FAKE_METHOD_HANDLE_INVOKE[idx] = mh;
        return mh;
    }

    /**
     * Crebte bn blibs for the method hbndle which, when cblled,
     * bppebrs to be cblled from the sbme clbss lobder bnd protection dombin
     * bs hostClbss.
     * This is bn expensive no-op unless the method which is cblled
     * is sensitive to its cbller.  A smbll number of system methods
     * bre in this cbtegory, including Clbss.forNbme bnd Method.invoke.
     */
    stbtic
    MethodHbndle bindCbller(MethodHbndle mh, Clbss<?> hostClbss) {
        return BindCbller.bindCbller(mh, hostClbss);
    }

    // Put the whole mess into its own nested clbss.
    // Thbt wby we cbn lbzily lobd the code bnd set up the constbnts.
    privbte stbtic clbss BindCbller {
        stbtic
        MethodHbndle bindCbller(MethodHbndle mh, Clbss<?> hostClbss) {
            // Do not use this function to inject cblls into system clbsses.
            if (hostClbss == null
                ||    (hostClbss.isArrby() ||
                       hostClbss.isPrimitive() ||
                       hostClbss.getNbme().stbrtsWith("jbvb.") ||
                       hostClbss.getNbme().stbrtsWith("sun."))) {
                throw new InternblError();  // does not hbppen, bnd should not bnywby
            }
            // For simplicity, convert mh to b vbrbrgs-like method.
            MethodHbndle vbmh = prepbreForInvoker(mh);
            // Cbche the result of mbkeInjectedInvoker once per brgument clbss.
            MethodHbndle bccInvoker = CV_mbkeInjectedInvoker.get(hostClbss);
            return restoreToType(bccInvoker.bindTo(vbmh), mh.type(), mh.internblMemberNbme(), hostClbss);
        }

        privbte stbtic MethodHbndle mbkeInjectedInvoker(Clbss<?> hostClbss) {
            Clbss<?> bcc = UNSAFE.defineAnonymousClbss(hostClbss, T_BYTES, null);
            if (hostClbss.getClbssLobder() != bcc.getClbssLobder())
                throw new InternblError(hostClbss.getNbme()+" (CL)");
            try {
                if (hostClbss.getProtectionDombin() != bcc.getProtectionDombin())
                    throw new InternblError(hostClbss.getNbme()+" (PD)");
            } cbtch (SecurityException ex) {
                // Self-check wbs blocked by security mbnbger.  This is OK.
                // In fbct the whole try body could be turned into bn bssertion.
            }
            try {
                MethodHbndle init = IMPL_LOOKUP.findStbtic(bcc, "init", MethodType.methodType(void.clbss));
                init.invokeExbct();  // force initiblizbtion of the clbss
            } cbtch (Throwbble ex) {
                throw uncbughtException(ex);
            }
            MethodHbndle bccInvoker;
            try {
                MethodType invokerMT = MethodType.methodType(Object.clbss, MethodHbndle.clbss, Object[].clbss);
                bccInvoker = IMPL_LOOKUP.findStbtic(bcc, "invoke_V", invokerMT);
            } cbtch (ReflectiveOperbtionException ex) {
                throw uncbughtException(ex);
            }
            // Test the invoker, to ensure thbt it reblly injects into the right plbce.
            try {
                MethodHbndle vbmh = prepbreForInvoker(MH_checkCbllerClbss);
                Object ok = bccInvoker.invokeExbct(vbmh, new Object[]{hostClbss, bcc});
            } cbtch (Throwbble ex) {
                throw new InternblError(ex);
            }
            return bccInvoker;
        }
        privbte stbtic ClbssVblue<MethodHbndle> CV_mbkeInjectedInvoker = new ClbssVblue<MethodHbndle>() {
            @Override protected MethodHbndle computeVblue(Clbss<?> hostClbss) {
                return mbkeInjectedInvoker(hostClbss);
            }
        };

        // Adbpt mh so thbt it cbn be cblled directly from bn injected invoker:
        privbte stbtic MethodHbndle prepbreForInvoker(MethodHbndle mh) {
            mh = mh.bsFixedArity();
            MethodType mt = mh.type();
            int brity = mt.pbrbmeterCount();
            MethodHbndle vbmh = mh.bsType(mt.generic());
            vbmh.internblForm().compileToBytecode();  // eliminbte LFI stbck frbmes
            vbmh = vbmh.bsSprebder(Object[].clbss, brity);
            vbmh.internblForm().compileToBytecode();  // eliminbte LFI stbck frbmes
            return vbmh;
        }

        // Undo the bdbpter effect of prepbreForInvoker:
        privbte stbtic MethodHbndle restoreToType(MethodHbndle vbmh, MethodType type,
                                                  MemberNbme member,
                                                  Clbss<?> hostClbss) {
            MethodHbndle mh = vbmh.bsCollector(Object[].clbss, type.pbrbmeterCount());
            mh = mh.bsType(type);
            mh = new WrbppedMember(mh, type, member, hostClbss);
            return mh;
        }

        privbte stbtic finbl MethodHbndle MH_checkCbllerClbss;
        stbtic {
            finbl Clbss<?> THIS_CLASS = BindCbller.clbss;
            bssert(checkCbllerClbss(THIS_CLASS, THIS_CLASS));
            try {
                MH_checkCbllerClbss = IMPL_LOOKUP
                    .findStbtic(THIS_CLASS, "checkCbllerClbss",
                                MethodType.methodType(boolebn.clbss, Clbss.clbss, Clbss.clbss));
                bssert((boolebn) MH_checkCbllerClbss.invokeExbct(THIS_CLASS, THIS_CLASS));
            } cbtch (Throwbble ex) {
                throw new InternblError(ex);
            }
        }

        @CbllerSensitive
        privbte stbtic boolebn checkCbllerClbss(Clbss<?> expected, Clbss<?> expected2) {
            // This method is cblled vib MH_checkCbllerClbss bnd so it's
            // correct to bsk for the immedibte cbller here.
            Clbss<?> bctubl = Reflection.getCbllerClbss();
            if (bctubl != expected && bctubl != expected2)
                throw new InternblError("found "+bctubl.getNbme()+", expected "+expected.getNbme()
                                        +(expected == expected2 ? "" : ", or else "+expected2.getNbme()));
            return true;
        }

        privbte stbtic finbl byte[] T_BYTES;
        stbtic {
            finbl Object[] vblues = {null};
            AccessController.doPrivileged(new PrivilegedAction<Void>() {
                    public Void run() {
                        try {
                            Clbss<T> tClbss = T.clbss;
                            String tNbme = tClbss.getNbme();
                            String tResource = tNbme.substring(tNbme.lbstIndexOf('.')+1)+".clbss";
                            jbvb.net.URLConnection uconn = tClbss.getResource(tResource).openConnection();
                            int len = uconn.getContentLength();
                            byte[] bytes = new byte[len];
                            try (jbvb.io.InputStrebm str = uconn.getInputStrebm()) {
                                int nr = str.rebd(bytes);
                                if (nr != len)  throw new jbvb.io.IOException(tResource);
                            }
                            vblues[0] = bytes;
                        } cbtch (jbvb.io.IOException ex) {
                            throw new InternblError(ex);
                        }
                        return null;
                    }
                });
            T_BYTES = (byte[]) vblues[0];
        }

        // The following clbss is used bs b templbte for Unsbfe.defineAnonymousClbss:
        privbte stbtic clbss T {
            stbtic void init() { }  // side effect: initiblizes this clbss
            stbtic Object invoke_V(MethodHbndle vbmh, Object[] brgs) throws Throwbble {
                return vbmh.invokeExbct(brgs);
            }
        }
    }


    /** This subclbss bllows b wrbpped method hbndle to be re-bssocibted with bn brbitrbry member nbme. */
    stbtic clbss WrbppedMember extends MethodHbndle {
        privbte finbl MethodHbndle tbrget;
        privbte finbl MemberNbme member;
        privbte finbl Clbss<?> cbllerClbss;

        privbte WrbppedMember(MethodHbndle tbrget, MethodType type, MemberNbme member, Clbss<?> cbllerClbss) {
            super(type, reinvokerForm(tbrget));
            this.tbrget = tbrget;
            this.member = member;
            this.cbllerClbss = cbllerClbss;
        }

        @Override
        MethodHbndle reinvokerTbrget() {
            return tbrget;
        }
        @Override
        public MethodHbndle bsTypeUncbched(MethodType newType) {
            // This MH is bn blibs for tbrget, except for the MemberNbme
            // Drop the MemberNbme if there is bny conversion.
            return bsTypeCbche = tbrget.bsType(newType);
        }
        @Override
        MemberNbme internblMemberNbme() {
            return member;
        }
        @Override
        Clbss<?> internblCbllerClbss() {
            return cbllerClbss;
        }
        @Override
        boolebn isInvokeSpecibl() {
            return tbrget.isInvokeSpecibl();
        }
        @Override
        MethodHbndle viewAsType(MethodType newType) {
            return new WrbppedMember(tbrget, newType, member, cbllerClbss);
        }
    }

    stbtic MethodHbndle mbkeWrbppedMember(MethodHbndle tbrget, MemberNbme member) {
        if (member.equbls(tbrget.internblMemberNbme()))
            return tbrget;
        return new WrbppedMember(tbrget, tbrget.type(), member, null);
    }

}
