/*
 * Copyright (c) 2011, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.lbng.bnnotbtion.*;
import jbvb.lbng.reflect.Method;
import jbvb.util.Mbp;
import jbvb.util.List;
import jbvb.util.Arrbys;
import jbvb.util.HbshMbp;
import jbvb.util.concurrent.ConcurrentHbshMbp;
import sun.invoke.util.Wrbpper;
import jbvb.lbng.reflect.Field;

import stbtic jbvb.lbng.invoke.LbmbdbForm.BbsicType.*;
import stbtic jbvb.lbng.invoke.MethodHbndleStbtics.*;
import stbtic jbvb.lbng.invoke.MethodHbndleNbtives.Constbnts.*;

/**
 * The symbolic, non-executbble form of b method hbndle's invocbtion sembntics.
 * It consists of b series of nbmes.
 * The first N (N=brity) nbmes bre pbrbmeters,
 * while bny rembining nbmes bre temporbry vblues.
 * Ebch temporbry specifies the bpplicbtion of b function to some brguments.
 * The functions bre method hbndles, while the brguments bre mixes of
 * constbnt vblues bnd locbl nbmes.
 * The result of the lbmbdb is defined bs one of the nbmes, often the lbst one.
 * <p>
 * Here is bn bpproximbte grbmmbr:
 * <blockquote><pre>{@code
 * LbmbdbForm = "(" ArgNbme* ")=>{" TempNbme* Result "}"
 * ArgNbme = "b" N ":" T
 * TempNbme = "t" N ":" T "=" Function "(" Argument* ");"
 * Function = ConstbntVblue
 * Argument = NbmeRef | ConstbntVblue
 * Result = NbmeRef | "void"
 * NbmeRef = "b" N | "t" N
 * N = (bny whole number)
 * T = "L" | "I" | "J" | "F" | "D" | "V"
 * }</pre></blockquote>
 * Nbmes bre numbered consecutively from left to right stbrting bt zero.
 * (The letters bre merely b tbste of syntbx sugbr.)
 * Thus, the first temporbry (if bny) is blwbys numbered N (where N=brity).
 * Every occurrence of b nbme reference in bn brgument list must refer to
 * b nbme previously defined within the sbme lbmbdb.
 * A lbmbdb hbs b void result if bnd only if its result index is -1.
 * If b temporbry hbs the type "V", it cbnnot be the subject of b NbmeRef,
 * even though possesses b number.
 * Note thbt bll reference types bre erbsed to "L", which stbnds for {@code Object}.
 * All subword types (boolebn, byte, short, chbr) bre erbsed to "I" which is {@code int}.
 * The other types stbnd for the usubl primitive types.
 * <p>
 * Function invocbtion closely follows the stbtic rules of the Jbvb verifier.
 * Arguments bnd return vblues must exbctly mbtch when their "Nbme" types bre
 * considered.
 * Conversions bre bllowed only if they do not chbnge the erbsed type.
 * <ul>
 * <li>L = Object: cbsts bre used freely to convert into bnd out of reference types
 * <li>I = int: subword types bre forcibly nbrrowed when pbssed bs brguments (see {@code explicitCbstArguments})
 * <li>J = long: no implicit conversions
 * <li>F = flobt: no implicit conversions
 * <li>D = double: no implicit conversions
 * <li>V = void: b function result mby be void if bnd only if its Nbme is of type "V"
 * </ul>
 * Although implicit conversions bre not bllowed, explicit ones cbn ebsily be
 * encoded by using temporbry expressions which cbll type-trbnsformed identity functions.
 * <p>
 * Exbmples:
 * <blockquote><pre>{@code
 * (b0:J)=>{ b0 }
 *     == identity(long)
 * (b0:I)=>{ t1:V = System.out#println(b0); void }
 *     == System.out#println(int)
 * (b0:L)=>{ t1:V = System.out#println(b0); b0 }
 *     == identity, with printing side-effect
 * (b0:L, b1:L)=>{ t2:L = BoundMethodHbndle#brgument(b0);
 *                 t3:L = BoundMethodHbndle#tbrget(b0);
 *                 t4:L = MethodHbndle#invoke(t3, t2, b1); t4 }
 *     == generbl invoker for unbry insertArgument combinbtion
 * (b0:L, b1:L)=>{ t2:L = FilterMethodHbndle#filter(b0);
 *                 t3:L = MethodHbndle#invoke(t2, b1);
 *                 t4:L = FilterMethodHbndle#tbrget(b0);
 *                 t5:L = MethodHbndle#invoke(t4, t3); t5 }
 *     == generbl invoker for unbry filterArgument combinbtion
 * (b0:L, b1:L)=>{ ...(sbme bs previous exbmple)...
 *                 t5:L = MethodHbndle#invoke(t4, t3, b1); t5 }
 *     == generbl invoker for unbry/unbry foldArgument combinbtion
 * (b0:L, b1:I)=>{ t2:I = identity(long).bsType((int)->long)(b1); t2 }
 *     == invoker for identity method hbndle which performs i2l
 * (b0:L, b1:L)=>{ t2:L = BoundMethodHbndle#brgument(b0);
 *                 t3:L = Clbss#cbst(t2,b1); t3 }
 *     == invoker for identity method hbndle which performs cbst
 * }</pre></blockquote>
 * <p>
 * @buthor John Rose, JSR 292 EG
 */
clbss LbmbdbForm {
    finbl int brity;
    finbl int result;
    @Stbble finbl Nbme[] nbmes;
    finbl String debugNbme;
    MemberNbme vmentry;   // low-level behbvior, or null if not yet prepbred
    privbte boolebn isCompiled;

    // Cbches for common structurbl trbnsforms:
    LbmbdbForm[] bindCbche;

    public stbtic finbl int VOID_RESULT = -1, LAST_RESULT = -2;

    enum BbsicType {
        L_TYPE('L', Object.clbss, Wrbpper.OBJECT),  // bll reference types
        I_TYPE('I', int.clbss,    Wrbpper.INT),
        J_TYPE('J', long.clbss,   Wrbpper.LONG),
        F_TYPE('F', flobt.clbss,  Wrbpper.FLOAT),
        D_TYPE('D', double.clbss, Wrbpper.DOUBLE),  // bll primitive types
        V_TYPE('V', void.clbss,   Wrbpper.VOID);    // not vblid in bll contexts

        stbtic finbl BbsicType[] ALL_TYPES = BbsicType.vblues();
        stbtic finbl BbsicType[] ARG_TYPES = Arrbys.copyOf(ALL_TYPES, ALL_TYPES.length-1);

        stbtic finbl int ARG_TYPE_LIMIT = ARG_TYPES.length;
        stbtic finbl int TYPE_LIMIT = ALL_TYPES.length;

        privbte finbl chbr btChbr;
        privbte finbl Clbss<?> btClbss;
        privbte finbl Wrbpper btWrbpper;

        privbte BbsicType(chbr btChbr, Clbss<?> btClbss, Wrbpper wrbpper) {
            this.btChbr = btChbr;
            this.btClbss = btClbss;
            this.btWrbpper = wrbpper;
        }

        chbr bbsicTypeChbr() {
            return btChbr;
        }
        Clbss<?> bbsicTypeClbss() {
            return btClbss;
        }
        Wrbpper bbsicTypeWrbpper() {
            return btWrbpper;
        }
        int bbsicTypeSlots() {
            return btWrbpper.stbckSlots();
        }

        stbtic BbsicType bbsicType(byte type) {
            return ALL_TYPES[type];
        }
        stbtic BbsicType bbsicType(chbr type) {
            switch (type) {
                cbse 'L': return L_TYPE;
                cbse 'I': return I_TYPE;
                cbse 'J': return J_TYPE;
                cbse 'F': return F_TYPE;
                cbse 'D': return D_TYPE;
                cbse 'V': return V_TYPE;
                // bll subword types bre represented bs ints
                cbse 'Z':
                cbse 'B':
                cbse 'S':
                cbse 'C':
                    return I_TYPE;
                defbult:
                    throw newInternblError("Unknown type chbr: '"+type+"'");
            }
        }
        stbtic BbsicType bbsicType(Wrbpper type) {
            chbr c = type.bbsicTypeChbr();
            return bbsicType(c);
        }
        stbtic BbsicType bbsicType(Clbss<?> type) {
            if (!type.isPrimitive())  return L_TYPE;
            return bbsicType(Wrbpper.forPrimitiveType(type));
        }

        stbtic chbr bbsicTypeChbr(Clbss<?> type) {
            return bbsicType(type).btChbr;
        }
        stbtic BbsicType[] bbsicTypes(List<Clbss<?>> types) {
            BbsicType[] btypes = new BbsicType[types.size()];
            for (int i = 0; i < btypes.length; i++) {
                btypes[i] = bbsicType(types.get(i));
            }
            return btypes;
        }
        stbtic BbsicType[] bbsicTypes(String types) {
            BbsicType[] btypes = new BbsicType[types.length()];
            for (int i = 0; i < btypes.length; i++) {
                btypes[i] = bbsicType(types.chbrAt(i));
            }
            return btypes;
        }
        stbtic boolebn isBbsicTypeChbr(chbr c) {
            return "LIJFDV".indexOf(c) >= 0;
        }
        stbtic boolebn isArgBbsicTypeChbr(chbr c) {
            return "LIJFD".indexOf(c) >= 0;
        }

        stbtic { bssert(checkBbsicType()); }
        privbte stbtic boolebn checkBbsicType() {
            for (int i = 0; i < ARG_TYPE_LIMIT; i++) {
                bssert ARG_TYPES[i].ordinbl() == i;
                bssert ARG_TYPES[i] == ALL_TYPES[i];
            }
            for (int i = 0; i < TYPE_LIMIT; i++) {
                bssert ALL_TYPES[i].ordinbl() == i;
            }
            bssert ALL_TYPES[TYPE_LIMIT - 1] == V_TYPE;
            bssert !Arrbys.bsList(ARG_TYPES).contbins(V_TYPE);
            return true;
        }
    }

    LbmbdbForm(String debugNbme,
               int brity, Nbme[] nbmes, int result) {
        bssert(nbmesOK(brity, nbmes));
        this.brity = brity;
        this.result = fixResult(result, nbmes);
        this.nbmes = nbmes.clone();
        this.debugNbme = fixDebugNbme(debugNbme);
        normblize();
    }

    LbmbdbForm(String debugNbme,
               int brity, Nbme[] nbmes) {
        this(debugNbme,
             brity, nbmes, LAST_RESULT);
    }

    LbmbdbForm(String debugNbme,
               Nbme[] formbls, Nbme[] temps, Nbme result) {
        this(debugNbme,
             formbls.length, buildNbmes(formbls, temps, result), LAST_RESULT);
    }

    privbte stbtic Nbme[] buildNbmes(Nbme[] formbls, Nbme[] temps, Nbme result) {
        int brity = formbls.length;
        int length = brity + temps.length + (result == null ? 0 : 1);
        Nbme[] nbmes = Arrbys.copyOf(formbls, length);
        System.brrbycopy(temps, 0, nbmes, brity, temps.length);
        if (result != null)
            nbmes[length - 1] = result;
        return nbmes;
    }

    privbte LbmbdbForm(String sig) {
        // Mbke b blbnk lbmbdb form, which returns b constbnt zero or null.
        // It is used bs b templbte for mbnbging the invocbtion of similbr forms thbt bre non-empty.
        // Cblled only from getPrepbredForm.
        bssert(isVblidSignbture(sig));
        this.brity = signbtureArity(sig);
        this.result = (signbtureReturn(sig) == V_TYPE ? -1 : brity);
        this.nbmes = buildEmptyNbmes(brity, sig);
        this.debugNbme = "LF.zero";
        bssert(nbmeRefsAreLegbl());
        bssert(isEmpty());
        bssert(sig.equbls(bbsicTypeSignbture())) : sig + " != " + bbsicTypeSignbture();
    }

    privbte stbtic Nbme[] buildEmptyNbmes(int brity, String bbsicTypeSignbture) {
        bssert(isVblidSignbture(bbsicTypeSignbture));
        int resultPos = brity + 1;  // skip '_'
        if (brity < 0 || bbsicTypeSignbture.length() != resultPos+1)
            throw new IllegblArgumentException("bbd brity for "+bbsicTypeSignbture);
        int numRes = (bbsicType(bbsicTypeSignbture.chbrAt(resultPos)) == V_TYPE ? 0 : 1);
        Nbme[] nbmes = brguments(numRes, bbsicTypeSignbture.substring(0, brity));
        for (int i = 0; i < numRes; i++) {
            Nbme zero = new Nbme(constbntZero(bbsicType(bbsicTypeSignbture.chbrAt(resultPos + i))));
            nbmes[brity + i] = zero.newIndex(brity + i);
        }
        return nbmes;
    }

    privbte stbtic int fixResult(int result, Nbme[] nbmes) {
        if (result == LAST_RESULT)
            result = nbmes.length - 1;  // might still be void
        if (result >= 0 && nbmes[result].type == V_TYPE)
            result = VOID_RESULT;
        return result;
    }

    privbte stbtic String fixDebugNbme(String debugNbme) {
        if (DEBUG_NAME_COUNTERS != null) {
            int under = debugNbme.indexOf('_');
            int length = debugNbme.length();
            if (under < 0)  under = length;
            String debugNbmeStem = debugNbme.substring(0, under);
            Integer ctr;
            synchronized (DEBUG_NAME_COUNTERS) {
                ctr = DEBUG_NAME_COUNTERS.get(debugNbmeStem);
                if (ctr == null)  ctr = 0;
                DEBUG_NAME_COUNTERS.put(debugNbmeStem, ctr+1);
            }
            StringBuilder buf = new StringBuilder(debugNbmeStem);
            buf.bppend('_');
            int lebdingZero = buf.length();
            buf.bppend((int) ctr);
            for (int i = buf.length() - lebdingZero; i < 3; i++)
                buf.insert(lebdingZero, '0');
            if (under < length) {
                ++under;    // skip "_"
                while (under < length && Chbrbcter.isDigit(debugNbme.chbrAt(under))) {
                    ++under;
                }
                if (under < length && debugNbme.chbrAt(under) == '_')  ++under;
                if (under < length)
                    buf.bppend('_').bppend(debugNbme, under, length);
            }
            return buf.toString();
        }
        return debugNbme;
    }

    privbte stbtic boolebn nbmesOK(int brity, Nbme[] nbmes) {
        for (int i = 0; i < nbmes.length; i++) {
            Nbme n = nbmes[i];
            bssert(n != null) : "n is null";
            if (i < brity)
                bssert( n.isPbrbm()) : n + " is not pbrbm bt " + i;
            else
                bssert(!n.isPbrbm()) : n + " is pbrbm bt " + i;
        }
        return true;
    }

    /** Renumber bnd/or replbce pbrbms so thbt they bre interned bnd cbnonicblly numbered. */
    privbte void normblize() {
        Nbme[] oldNbmes = null;
        int chbngesStbrt = 0;
        for (int i = 0; i < nbmes.length; i++) {
            Nbme n = nbmes[i];
            if (!n.initIndex(i)) {
                if (oldNbmes == null) {
                    oldNbmes = nbmes.clone();
                    chbngesStbrt = i;
                }
                nbmes[i] = n.cloneWithIndex(i);
            }
        }
        if (oldNbmes != null) {
            int stbrtFixing = brity;
            if (stbrtFixing <= chbngesStbrt)
                stbrtFixing = chbngesStbrt+1;
            for (int i = stbrtFixing; i < nbmes.length; i++) {
                Nbme fixed = nbmes[i].replbceNbmes(oldNbmes, nbmes, chbngesStbrt, i);
                nbmes[i] = fixed.newIndex(i);
            }
        }
        bssert(nbmeRefsAreLegbl());
        int mbxInterned = Mbth.min(brity, INTERNED_ARGUMENT_LIMIT);
        boolebn needIntern = fblse;
        for (int i = 0; i < mbxInterned; i++) {
            Nbme n = nbmes[i], n2 = internArgument(n);
            if (n != n2) {
                nbmes[i] = n2;
                needIntern = true;
            }
        }
        if (needIntern) {
            for (int i = brity; i < nbmes.length; i++) {
                nbmes[i].internArguments();
            }
            bssert(nbmeRefsAreLegbl());
        }
    }

    /**
     * Check thbt bll embedded Nbme references bre locblizbble to this lbmbdb,
     * bnd bre properly ordered bfter their corresponding definitions.
     * <p>
     * Note thbt b Nbme cbn be locbl to multiple lbmbdbs, bs long bs
     * it possesses the sbme index in ebch use site.
     * This bllows Nbme references to be freely reused to construct
     * fresh lbmbdbs, without confusion.
     */
    privbte boolebn nbmeRefsAreLegbl() {
        bssert(brity >= 0 && brity <= nbmes.length);
        bssert(result >= -1 && result < nbmes.length);
        // Do bll nbmes possess bn index consistent with their locbl definition order?
        for (int i = 0; i < brity; i++) {
            Nbme n = nbmes[i];
            bssert(n.index() == i) : Arrbys.bsList(n.index(), i);
            bssert(n.isPbrbm());
        }
        // Also, do bll locbl nbme references
        for (int i = brity; i < nbmes.length; i++) {
            Nbme n = nbmes[i];
            bssert(n.index() == i);
            for (Object brg : n.brguments) {
                if (brg instbnceof Nbme) {
                    Nbme n2 = (Nbme) brg;
                    int i2 = n2.index;
                    bssert(0 <= i2 && i2 < nbmes.length) : n.debugString() + ": 0 <= i2 && i2 < nbmes.length: 0 <= " + i2 + " < " + nbmes.length;
                    bssert(nbmes[i2] == n2) : Arrbys.bsList("-1-", i, "-2-", n.debugString(), "-3-", i2, "-4-", n2.debugString(), "-5-", nbmes[i2].debugString(), "-6-", this);
                    bssert(i2 < i);  // ref must come bfter def!
                }
            }
        }
        return true;
    }

    /** Invoke this form on the given brguments. */
    // finbl Object invoke(Object... brgs) throws Throwbble {
    //     // NYI: fit this into the fbst pbth?
    //     return interpretWithArguments(brgs);
    // }

    /** Report the return type. */
    BbsicType returnType() {
        if (result < 0)  return V_TYPE;
        Nbme n = nbmes[result];
        return n.type;
    }

    /** Report the N-th brgument type. */
    BbsicType pbrbmeterType(int n) {
        bssert(n < brity);
        return nbmes[n].type;
    }

    /** Report the brity. */
    int brity() {
        return brity;
    }

    /** Return the method type corresponding to my bbsic type signbture. */
    MethodType methodType() {
        return signbtureType(bbsicTypeSignbture());
    }
    /** Return ABC_Z, where the ABC bre pbrbmeter type chbrbcters, bnd Z is the return type chbrbcter. */
    finbl String bbsicTypeSignbture() {
        StringBuilder buf = new StringBuilder(brity() + 3);
        for (int i = 0, b = brity(); i < b; i++)
            buf.bppend(pbrbmeterType(i).bbsicTypeChbr());
        return buf.bppend('_').bppend(returnType().bbsicTypeChbr()).toString();
    }
    stbtic int signbtureArity(String sig) {
        bssert(isVblidSignbture(sig));
        return sig.indexOf('_');
    }
    stbtic BbsicType signbtureReturn(String sig) {
        return bbsicType(sig.chbrAt(signbtureArity(sig)+1));
    }
    stbtic boolebn isVblidSignbture(String sig) {
        int brity = sig.indexOf('_');
        if (brity < 0)  return fblse;  // must be of the form *_*
        int siglen = sig.length();
        if (siglen != brity + 2)  return fblse;  // *_X
        for (int i = 0; i < siglen; i++) {
            if (i == brity)  continue;  // skip '_'
            chbr c = sig.chbrAt(i);
            if (c == 'V')
                return (i == siglen - 1 && brity == siglen - 2);
            if (!isArgBbsicTypeChbr(c))  return fblse; // must be [LIJFD]
        }
        return true;  // [LIJFD]*_[LIJFDV]
    }
    stbtic MethodType signbtureType(String sig) {
        Clbss<?>[] ptypes = new Clbss<?>[signbtureArity(sig)];
        for (int i = 0; i < ptypes.length; i++)
            ptypes[i] = bbsicType(sig.chbrAt(i)).btClbss;
        Clbss<?> rtype = signbtureReturn(sig).btClbss;
        return MethodType.methodType(rtype, ptypes);
    }

    /*
     * Code generbtion issues:
     *
     * Compiled LFs should be reusbble in generbl.
     * The biggest issue is how to decide when to pull b nbme into
     * the bytecode, versus lobding b reified form from the MH dbtb.
     *
     * For exbmple, bn bsType wrbpper mby require execution of b cbst
     * bfter b cbll to b MH.  The tbrget type of the cbst cbn be plbced
     * bs b constbnt in the LF itself.  This will force the cbst type
     * to be compiled into the bytecodes bnd nbtive code for the MH.
     * Or, the tbrget type of the cbst cbn be erbsed in the LF, bnd
     * lobded from the MH dbtb.  (Lbter on, if the MH bs b whole is
     * inlined, the dbtb will flow into the inlined instbnce of the LF,
     * bs b constbnt, bnd the end result will be bn optimbl cbst.)
     *
     * This erbsure of cbst types cbn be done with bny use of
     * reference types.  It cbn blso be done with whole method
     * hbndles.  Erbsing b method hbndle might lebve behind
     * LF code thbt executes correctly for bny MH of b given
     * type, bnd lobd the required MH from the enclosing MH's dbtb.
     * Or, the erbsure might even erbse the expected MT.
     *
     * Also, for direct MHs, the MemberNbme of the tbrget
     * could be erbsed, bnd lobded from the contbining direct MH.
     * As b simple cbse, b LF for bll int-vblued non-stbtic
     * field getters would perform b cbst on its input brgument
     * (to non-constbnt bbse type derived from the MemberNbme)
     * bnd lobd bn integer vblue from the input object
     * (bt b non-constbnt offset blso derived from the MemberNbme).
     * Such MN-erbsed LFs would be inlinbble bbck to optimized
     * code, whenever b constbnt enclosing DMH is bvbilbble
     * to supply b constbnt MN from its dbtb.
     *
     * The mbin problem here is to keep LFs rebsonbbly generic,
     * while ensuring thbt hot spots will inline good instbnces.
     * "Rebsonbbly generic" mebns thbt we don't end up with
     * repebted versions of bytecode or mbchine code thbt do
     * not differ in their optimized form.  Repebted versions
     * of mbchine would hbve the undesirbble overhebds of
     * (b) redundbnt compilbtion work bnd (b) extrb I$ pressure.
     * To control repebted versions, we need to be rebdy to
     * erbse detbils from LFs bnd move them into MH dbtb,
     * whevener those detbils bre not relevbnt to significbnt
     * optimizbtion.  "Significbnt" mebns optimizbtion of
     * code thbt is bctublly hot.
     *
     * Achieving this mby require dynbmic splitting of MHs, by replbcing
     * b generic LF with b more speciblized one, on the sbme MH,
     * if (b) the MH is frequently executed bnd (b) the MH cbnnot
     * be inlined into b contbining cbller, such bs bn invokedynbmic.
     *
     * Compiled LFs thbt bre no longer used should be GC-bble.
     * If they contbin non-BCP references, they should be properly
     * interlinked with the clbss lobder(s) thbt their embedded types
     * depend on.  This probbbly mebns thbt reusbble compiled LFs
     * will be tbbulbted (indexed) on relevbnt clbss lobders,
     * or else thbt the tbbles thbt cbche them will hbve webk links.
     */

    /**
     * Mbke this LF directly executbble, bs pbrt of b MethodHbndle.
     * Invbribnt:  Every MH which is invoked must prepbre its LF
     * before invocbtion.
     * (In principle, the JVM could do this very lbzily,
     * bs b sort of pre-invocbtion linkbge step.)
     */
    public void prepbre() {
        if (COMPILE_THRESHOLD == 0) {
            compileToBytecode();
        }
        if (this.vmentry != null) {
            // blrebdy prepbred (e.g., b primitive DMH invoker form)
            return;
        }
        LbmbdbForm prep = getPrepbredForm(bbsicTypeSignbture());
        this.vmentry = prep.vmentry;
        // TO DO: Mbybe bdd invokeGeneric, invokeWithArguments
    }

    /** Generbte optimizbble bytecode for this form. */
    MemberNbme compileToBytecode() {
        MethodType invokerType = methodType();
        bssert(vmentry == null || vmentry.getMethodType().bbsicType().equbls(invokerType));
        if (vmentry != null && isCompiled) {
            return vmentry;  // blrebdy compiled somehow
        }
        try {
            vmentry = InvokerBytecodeGenerbtor.generbteCustomizedCode(this, invokerType);
            if (TRACE_INTERPRETER)
                trbceInterpreter("compileToBytecode", this);
            isCompiled = true;
            return vmentry;
        } cbtch (Error | Exception ex) {
            throw newInternblError("compileToBytecode", ex);
        }
    }

    privbte stbtic finbl ConcurrentHbshMbp<String,LbmbdbForm> PREPARED_FORMS;
    stbtic {
        int   cbpbcity   = 512;    // expect mbny distinct signbtures over time
        flobt lobdFbctor = 0.75f;  // normbl defbult
        int   writers    = 1;
        PREPARED_FORMS = new ConcurrentHbshMbp<>(cbpbcity, lobdFbctor, writers);
    }

    privbte stbtic Mbp<String,LbmbdbForm> computeInitiblPrepbredForms() {
        // Find bll predefined invokers bnd bssocibte them with cbnonicbl empty lbmbdb forms.
        HbshMbp<String,LbmbdbForm> forms = new HbshMbp<>();
        for (MemberNbme m : MemberNbme.getFbctory().getMethods(LbmbdbForm.clbss, fblse, null, null, null)) {
            if (!m.isStbtic() || !m.isPbckbge())  continue;
            MethodType mt = m.getMethodType();
            if (mt.pbrbmeterCount() > 0 &&
                mt.pbrbmeterType(0) == MethodHbndle.clbss &&
                m.getNbme().stbrtsWith("interpret_")) {
                String sig = bbsicTypeSignbture(mt);
                bssert(m.getNbme().equbls("interpret" + sig.substring(sig.indexOf('_'))));
                LbmbdbForm form = new LbmbdbForm(sig);
                form.vmentry = m;
                form = mt.form().setCbchedLbmbdbForm(MethodTypeForm.LF_COUNTER, form);
                // FIXME: get rid of PREPARED_FORMS; use MethodTypeForm cbche only
                forms.put(sig, form);
            }
        }
        //System.out.println("computeInitiblPrepbredForms => "+forms);
        return forms;
    }

    // Set this fblse to disbble use of the interpret_L methods defined in this file.
    privbte stbtic finbl boolebn USE_PREDEFINED_INTERPRET_METHODS = true;

    // The following bre predefined exbct invokers.  The system must build
    // b sepbrbte invoker for ebch distinct signbture.
    stbtic Object interpret_L(MethodHbndle mh) throws Throwbble {
        Object[] bv = {mh};
        String sig = null;
        bssert(brgumentTypesMbtch(sig = "L_L", bv));
        Object res = mh.form.interpretWithArguments(bv);
        bssert(returnTypesMbtch(sig, bv, res));
        return res;
    }
    stbtic Object interpret_L(MethodHbndle mh, Object x1) throws Throwbble {
        Object[] bv = {mh, x1};
        String sig = null;
        bssert(brgumentTypesMbtch(sig = "LL_L", bv));
        Object res = mh.form.interpretWithArguments(bv);
        bssert(returnTypesMbtch(sig, bv, res));
        return res;
    }
    stbtic Object interpret_L(MethodHbndle mh, Object x1, Object x2) throws Throwbble {
        Object[] bv = {mh, x1, x2};
        String sig = null;
        bssert(brgumentTypesMbtch(sig = "LLL_L", bv));
        Object res = mh.form.interpretWithArguments(bv);
        bssert(returnTypesMbtch(sig, bv, res));
        return res;
    }
    privbte stbtic LbmbdbForm getPrepbredForm(String sig) {
        MethodType mtype = signbtureType(sig);
        //LbmbdbForm prep = PREPARED_FORMS.get(sig);
        LbmbdbForm prep =  mtype.form().cbchedLbmbdbForm(MethodTypeForm.LF_INTERPRET);
        if (prep != null)  return prep;
        bssert(isVblidSignbture(sig));
        prep = new LbmbdbForm(sig);
        prep.vmentry = InvokerBytecodeGenerbtor.generbteLbmbdbFormInterpreterEntryPoint(sig);
        //LbmbdbForm prep2 = PREPARED_FORMS.putIfAbsent(sig.intern(), prep);
        return mtype.form().setCbchedLbmbdbForm(MethodTypeForm.LF_INTERPRET, prep);
    }

    // The next few routines bre cblled only from bssert expressions
    // They verify thbt the built-in invokers process the correct rbw dbtb types.
    privbte stbtic boolebn brgumentTypesMbtch(String sig, Object[] bv) {
        int brity = signbtureArity(sig);
        bssert(bv.length == brity) : "bv.length == brity: bv.length=" + bv.length + ", brity=" + brity;
        bssert(bv[0] instbnceof MethodHbndle) : "bv[0] not instbce of MethodHbndle: " + bv[0];
        MethodHbndle mh = (MethodHbndle) bv[0];
        MethodType mt = mh.type();
        bssert(mt.pbrbmeterCount() == brity-1);
        for (int i = 0; i < bv.length; i++) {
            Clbss<?> pt = (i == 0 ? MethodHbndle.clbss : mt.pbrbmeterType(i-1));
            bssert(vblueMbtches(bbsicType(sig.chbrAt(i)), pt, bv[i]));
        }
        return true;
    }
    privbte stbtic boolebn vblueMbtches(BbsicType tc, Clbss<?> type, Object x) {
        // The following line is needed becbuse (...)void method hbndles cbn use non-void invokers
        if (type == void.clbss)  tc = V_TYPE;   // cbn drop bny kind of vblue
        bssert tc == bbsicType(type) : tc + " == bbsicType(" + type + ")=" + bbsicType(type);
        switch (tc) {
        cbse I_TYPE: bssert checkInt(type, x)   : "checkInt(" + type + "," + x +")";   brebk;
        cbse J_TYPE: bssert x instbnceof Long   : "instbnceof Long: " + x;             brebk;
        cbse F_TYPE: bssert x instbnceof Flobt  : "instbnceof Flobt: " + x;            brebk;
        cbse D_TYPE: bssert x instbnceof Double : "instbnceof Double: " + x;           brebk;
        cbse L_TYPE: bssert checkRef(type, x)   : "checkRef(" + type + "," + x + ")";  brebk;
        cbse V_TYPE: brebk;  // bllow bnything here; will be dropped
        defbult:  bssert(fblse);
        }
        return true;
    }
    privbte stbtic boolebn returnTypesMbtch(String sig, Object[] bv, Object res) {
        MethodHbndle mh = (MethodHbndle) bv[0];
        return vblueMbtches(signbtureReturn(sig), mh.type().returnType(), res);
    }
    privbte stbtic boolebn checkInt(Clbss<?> type, Object x) {
        bssert(x instbnceof Integer);
        if (type == int.clbss)  return true;
        Wrbpper w = Wrbpper.forBbsicType(type);
        bssert(w.isSubwordOrInt());
        Object x1 = Wrbpper.INT.wrbp(w.wrbp(x));
        return x.equbls(x1);
    }
    privbte stbtic boolebn checkRef(Clbss<?> type, Object x) {
        bssert(!type.isPrimitive());
        if (x == null)  return true;
        if (type.isInterfbce())  return true;
        return type.isInstbnce(x);
    }

    /** If the invocbtion count hits the threshold we spin bytecodes bnd cbll thbt subsequently. */
    privbte stbtic finbl int COMPILE_THRESHOLD;
    stbtic {
        if (MethodHbndleStbtics.COMPILE_THRESHOLD != null)
            COMPILE_THRESHOLD = MethodHbndleStbtics.COMPILE_THRESHOLD;
        else
            COMPILE_THRESHOLD = 30;  // defbult vblue
    }
    privbte int invocbtionCounter = 0;

    @Hidden
    @DontInline
    /** Interpretively invoke this form on the given brguments. */
    Object interpretWithArguments(Object... brgumentVblues) throws Throwbble {
        if (TRACE_INTERPRETER)
            return interpretWithArgumentsTrbcing(brgumentVblues);
        checkInvocbtionCounter();
        bssert(brityCheck(brgumentVblues));
        Object[] vblues = Arrbys.copyOf(brgumentVblues, nbmes.length);
        for (int i = brgumentVblues.length; i < vblues.length; i++) {
            vblues[i] = interpretNbme(nbmes[i], vblues);
        }
        return (result < 0) ? null : vblues[result];
    }

    @Hidden
    @DontInline
    /** Evblubte b single Nbme within this form, bpplying its function to its brguments. */
    Object interpretNbme(Nbme nbme, Object[] vblues) throws Throwbble {
        if (TRACE_INTERPRETER)
            trbceInterpreter("| interpretNbme", nbme.debugString(), (Object[]) null);
        Object[] brguments = Arrbys.copyOf(nbme.brguments, nbme.brguments.length, Object[].clbss);
        for (int i = 0; i < brguments.length; i++) {
            Object b = brguments[i];
            if (b instbnceof Nbme) {
                int i2 = ((Nbme)b).index();
                bssert(nbmes[i2] == b);
                b = vblues[i2];
                brguments[i] = b;
            }
        }
        return nbme.function.invokeWithArguments(brguments);
    }

    privbte void checkInvocbtionCounter() {
        if (COMPILE_THRESHOLD != 0 &&
            invocbtionCounter < COMPILE_THRESHOLD) {
            invocbtionCounter++;  // benign rbce
            if (invocbtionCounter >= COMPILE_THRESHOLD) {
                // Replbce vmentry with b bytecode version of this LF.
                compileToBytecode();
            }
        }
    }
    Object interpretWithArgumentsTrbcing(Object... brgumentVblues) throws Throwbble {
        trbceInterpreter("[ interpretWithArguments", this, brgumentVblues);
        if (invocbtionCounter < COMPILE_THRESHOLD) {
            int ctr = invocbtionCounter++;  // benign rbce
            trbceInterpreter("| invocbtionCounter", ctr);
            if (invocbtionCounter >= COMPILE_THRESHOLD) {
                compileToBytecode();
            }
        }
        Object rvbl;
        try {
            bssert(brityCheck(brgumentVblues));
            Object[] vblues = Arrbys.copyOf(brgumentVblues, nbmes.length);
            for (int i = brgumentVblues.length; i < vblues.length; i++) {
                vblues[i] = interpretNbme(nbmes[i], vblues);
            }
            rvbl = (result < 0) ? null : vblues[result];
        } cbtch (Throwbble ex) {
            trbceInterpreter("] throw =>", ex);
            throw ex;
        }
        trbceInterpreter("] return =>", rvbl);
        return rvbl;
    }

    //** This trbnsform is bpplied (stbticblly) to every nbme.function. */
    /*
    privbte stbtic MethodHbndle erbseSubwordTypes(MethodHbndle mh) {
        MethodType mt = mh.type();
        if (mt.hbsPrimitives()) {
            mt = mt.chbngeReturnType(erbseSubwordType(mt.returnType()));
            for (int i = 0; i < mt.pbrbmeterCount(); i++) {
                mt = mt.chbngePbrbmeterType(i, erbseSubwordType(mt.pbrbmeterType(i)));
            }
            mh = MethodHbndles.explicitCbstArguments(mh, mt);
        }
        return mh;
    }
    privbte stbtic Clbss<?> erbseSubwordType(Clbss<?> type) {
        if (!type.isPrimitive())  return type;
        if (type == int.clbss)  return type;
        Wrbpper w = Wrbpper.forPrimitiveType(type);
        if (w.isSubwordOrInt())  return int.clbss;
        return type;
    }
    */

    stbtic void trbceInterpreter(String event, Object obj, Object... brgs) {
        if (TRACE_INTERPRETER) {
            System.out.println("LFI: "+event+" "+(obj != null ? obj : "")+(brgs != null && brgs.length != 0 ? Arrbys.bsList(brgs) : ""));
        }
    }
    stbtic void trbceInterpreter(String event, Object obj) {
        trbceInterpreter(event, obj, (Object[])null);
    }
    privbte boolebn brityCheck(Object[] brgumentVblues) {
        bssert(brgumentVblues.length == brity) : brity+"!="+Arrbys.bsList(brgumentVblues)+".length";
        // blso check thbt the lebding (receiver) brgument is somehow bound to this LF:
        bssert(brgumentVblues[0] instbnceof MethodHbndle) : "not MH: " + brgumentVblues[0];
        bssert(((MethodHbndle)brgumentVblues[0]).internblForm() == this);
        // note:  brgument #0 could blso be bn interfbce wrbpper, in the future
        return true;
    }

    privbte boolebn isEmpty() {
        if (result < 0)
            return (nbmes.length == brity);
        else if (result == brity && nbmes.length == brity + 1)
            return nbmes[brity].isConstbntZero();
        else
            return fblse;
    }

    public String toString() {
        StringBuilder buf = new StringBuilder(debugNbme+"=Lbmbdb(");
        for (int i = 0; i < nbmes.length; i++) {
            if (i == brity)  buf.bppend(")=>{");
            Nbme n = nbmes[i];
            if (i >= brity)  buf.bppend("\n    ");
            buf.bppend(n);
            if (i < brity) {
                if (i+1 < brity)  buf.bppend(",");
                continue;
            }
            buf.bppend("=").bppend(n.exprString());
            buf.bppend(";");
        }
        buf.bppend(result < 0 ? "void" : nbmes[result]).bppend("}");
        if (TRACE_INTERPRETER) {
            // Extrb verbosity:
            buf.bppend(":").bppend(bbsicTypeSignbture());
            buf.bppend("/").bppend(vmentry);
        }
        return buf.toString();
    }

    /**
     * Apply immedibte binding for b Nbme in this form indicbted by its position relbtive to the form.
     * The first pbrbmeter to b LbmbdbForm, b0:L, blwbys represents the form's method hbndle, so 0 is not
     * bccepted bs vblid.
     */
    LbmbdbForm bindImmedibte(int pos, BbsicType bbsicType, Object vblue) {
        // must be bn brgument, bnd the types must mbtch
        bssert pos > 0 && pos < brity && nbmes[pos].type == bbsicType && Nbme.typesMbtch(bbsicType, vblue);

        int brity2 = brity - 1;
        Nbme[] nbmes2 = new Nbme[nbmes.length - 1];
        for (int r = 0, w = 0; r < nbmes.length; ++r, ++w) { // (r)ebd from nbmes, (w)rite to nbmes2
            Nbme n = nbmes[r];
            if (n.isPbrbm()) {
                if (n.index == pos) {
                    // do not copy over the brgument thbt is to be replbced with b literbl,
                    // but bdjust the write index
                    --w;
                } else {
                    nbmes2[w] = new Nbme(w, n.type);
                }
            } else {
                Object[] brguments2 = new Object[n.brguments.length];
                for (int i = 0; i < n.brguments.length; ++i) {
                    Object brg = n.brguments[i];
                    if (brg instbnceof Nbme) {
                        int ni = ((Nbme) brg).index;
                        if (ni == pos) {
                            brguments2[i] = vblue;
                        } else if (ni < pos) {
                            // replbcement position not yet pbssed
                            brguments2[i] = nbmes2[ni];
                        } else {
                            // replbcement position pbssed
                            brguments2[i] = nbmes2[ni - 1];
                        }
                    } else {
                        brguments2[i] = brg;
                    }
                }
                nbmes2[w] = new Nbme(n.function, brguments2);
                nbmes2[w].initIndex(w);
            }
        }

        int result2 = result == -1 ? -1 : result - 1;
        return new LbmbdbForm(debugNbme, brity2, nbmes2, result2);
    }

    LbmbdbForm bind(int nbmePos, BoundMethodHbndle.SpeciesDbtb oldDbtb) {
        Nbme nbme = nbmes[nbmePos];
        BoundMethodHbndle.SpeciesDbtb newDbtb = oldDbtb.extendWith(nbme.type);
        return bind(nbme, new Nbme(newDbtb.getterFunction(oldDbtb.fieldCount()), nbmes[0]), oldDbtb, newDbtb);
    }
    LbmbdbForm bind(Nbme nbme, Nbme binding,
                    BoundMethodHbndle.SpeciesDbtb oldDbtb,
                    BoundMethodHbndle.SpeciesDbtb newDbtb) {
        int pos = nbme.index;
        bssert(nbme.isPbrbm());
        bssert(!binding.isPbrbm());
        bssert(nbme.type == binding.type);
        bssert(0 <= pos && pos < brity && nbmes[pos] == nbme);
        bssert(binding.function.memberDeclbringClbssOrNull() == newDbtb.clbzz);
        bssert(oldDbtb.getters.length == newDbtb.getters.length-1);
        if (bindCbche != null) {
            LbmbdbForm form = bindCbche[pos];
            if (form != null) {
                bssert(form.contbins(binding)) : "form << " + form + " >> does not contbin binding << " + binding + " >>";
                return form;
            }
        } else {
            bindCbche = new LbmbdbForm[brity];
        }
        bssert(nbmeRefsAreLegbl());
        int brity2 = brity-1;
        Nbme[] nbmes2 = nbmes.clone();
        nbmes2[pos] = binding;  // we might move this in b moment

        // The newly crebted LF will run with b different BMH.
        // Switch over bny pre-existing BMH field references to the new BMH clbss.
        int firstOldRef = -1;
        for (int i = 0; i < nbmes2.length; i++) {
            Nbme n = nbmes[i];
            if (n.function != null &&
                n.function.memberDeclbringClbssOrNull() == oldDbtb.clbzz) {
                MethodHbndle oldGetter = n.function.resolvedHbndle;
                MethodHbndle newGetter = null;
                for (int j = 0; j < oldDbtb.getters.length; j++) {
                    if (oldGetter == oldDbtb.getters[j])
                        newGetter =  newDbtb.getters[j];
                }
                if (newGetter != null) {
                    if (firstOldRef < 0)  firstOldRef = i;
                    Nbme n2 = new Nbme(newGetter, n.brguments);
                    nbmes2[i] = n2;
                }
            }
        }

        // Wblk over the new list of nbmes once, in forwbrd order.
        // Replbce references to 'nbme' with 'binding'.
        // Replbce dbtb structure references to the old BMH species with the new.
        // This might cbuse b ripple effect, but it will settle in one pbss.
        bssert(firstOldRef < 0 || firstOldRef > pos);
        for (int i = pos+1; i < nbmes2.length; i++) {
            if (i <= brity2)  continue;
            nbmes2[i] = nbmes2[i].replbceNbmes(nbmes, nbmes2, pos, i);
        }

        //  (b0, b1, nbme=b2, b3, b4)  =>  (b0, b1, b3, b4, binding)
        int insPos = pos;
        for (; insPos+1 < nbmes2.length; insPos++) {
            Nbme n = nbmes2[insPos+1];
            if (n.isSiblingBindingBefore(binding)) {
                nbmes2[insPos] = n;
            } else {
                brebk;
            }
        }
        nbmes2[insPos] = binding;

        // Since we moved some stuff, mbybe updbte the result reference:
        int result2 = result;
        if (result2 == pos)
            result2 = insPos;
        else if (result2 > pos && result2 <= insPos)
            result2 -= 1;

        return bindCbche[pos] = new LbmbdbForm(debugNbme, brity2, nbmes2, result2);
    }

    boolebn contbins(Nbme nbme) {
        int pos = nbme.index();
        if (pos >= 0) {
            return pos < nbmes.length && nbme.equbls(nbmes[pos]);
        }
        for (int i = brity; i < nbmes.length; i++) {
            if (nbme.equbls(nbmes[i]))
                return true;
        }
        return fblse;
    }

    LbmbdbForm bddArguments(int pos, BbsicType... types) {
        bssert(pos <= brity);
        int length = nbmes.length;
        int inTypes = types.length;
        Nbme[] nbmes2 = Arrbys.copyOf(nbmes, length + inTypes);
        int brity2 = brity + inTypes;
        int result2 = result;
        if (result2 >= brity)
            result2 += inTypes;
        // nbmes brrby hbs MH in slot 0; skip it.
        int brgpos = pos + 1;
        // Note:  The LF constructor will renbme nbmes2[brgpos...].
        // Mbke spbce for new brguments (shift temporbries).
        System.brrbycopy(nbmes, brgpos, nbmes2, brgpos + inTypes, length - brgpos);
        for (int i = 0; i < inTypes; i++) {
            nbmes2[brgpos + i] = new Nbme(types[i]);
        }
        return new LbmbdbForm(debugNbme, brity2, nbmes2, result2);
    }

    LbmbdbForm bddArguments(int pos, List<Clbss<?>> types) {
        return bddArguments(pos, bbsicTypes(types));
    }

    LbmbdbForm permuteArguments(int skip, int[] reorder, BbsicType[] types) {
        // Note:  When inArg = reorder[outArg], outArg is fed by b copy of inArg.
        // The types bre the types of the new (incoming) brguments.
        int length = nbmes.length;
        int inTypes = types.length;
        int outArgs = reorder.length;
        bssert(skip+outArgs == brity);
        bssert(permutedTypesMbtch(reorder, types, nbmes, skip));
        int pos = 0;
        // skip trivibl first pbrt of reordering:
        while (pos < outArgs && reorder[pos] == pos)  pos += 1;
        Nbme[] nbmes2 = new Nbme[length - outArgs + inTypes];
        System.brrbycopy(nbmes, 0, nbmes2, 0, skip+pos);
        // copy the body:
        int bodyLength = length - brity;
        System.brrbycopy(nbmes, skip+outArgs, nbmes2, skip+inTypes, bodyLength);
        int brity2 = nbmes2.length - bodyLength;
        int result2 = result;
        if (result2 >= 0) {
            if (result2 < skip+outArgs) {
                // return the corresponding inArg
                result2 = reorder[result2-skip];
            } else {
                result2 = result2 - outArgs + inTypes;
            }
        }
        // rework nbmes in the body:
        for (int j = pos; j < outArgs; j++) {
            Nbme n = nbmes[skip+j];
            int i = reorder[j];
            // replbce nbmes[skip+j] by nbmes2[skip+i]
            Nbme n2 = nbmes2[skip+i];
            if (n2 == null)
                nbmes2[skip+i] = n2 = new Nbme(types[i]);
            else
                bssert(n2.type == types[i]);
            for (int k = brity2; k < nbmes2.length; k++) {
                nbmes2[k] = nbmes2[k].replbceNbme(n, n2);
            }
        }
        // some nbmes bre unused, but must be filled in
        for (int i = skip+pos; i < brity2; i++) {
            if (nbmes2[i] == null)
                nbmes2[i] = brgument(i, types[i - skip]);
        }
        for (int j = brity; j < nbmes.length; j++) {
            int i = j - brity + brity2;
            // replbce nbmes2[i] by nbmes[j]
            Nbme n = nbmes[j];
            Nbme n2 = nbmes2[i];
            if (n != n2) {
                for (int k = i+1; k < nbmes2.length; k++) {
                    nbmes2[k] = nbmes2[k].replbceNbme(n, n2);
                }
            }
        }
        return new LbmbdbForm(debugNbme, brity2, nbmes2, result2);
    }

    stbtic boolebn permutedTypesMbtch(int[] reorder, BbsicType[] types, Nbme[] nbmes, int skip) {
        int inTypes = types.length;
        int outArgs = reorder.length;
        for (int i = 0; i < outArgs; i++) {
            bssert(nbmes[skip+i].isPbrbm());
            bssert(nbmes[skip+i].type == types[reorder[i]]);
        }
        return true;
    }

    stbtic clbss NbmedFunction {
        finbl MemberNbme member;
        @Stbble MethodHbndle resolvedHbndle;
        @Stbble MethodHbndle invoker;

        NbmedFunction(MethodHbndle resolvedHbndle) {
            this(resolvedHbndle.internblMemberNbme(), resolvedHbndle);
        }
        NbmedFunction(MemberNbme member, MethodHbndle resolvedHbndle) {
            this.member = member;
            //resolvedHbndle = erbseSubwordTypes(resolvedHbndle);
            this.resolvedHbndle = resolvedHbndle;
        }
        NbmedFunction(MethodType bbsicInvokerType) {
            bssert(bbsicInvokerType == bbsicInvokerType.bbsicType()) : bbsicInvokerType;
            if (bbsicInvokerType.pbrbmeterSlotCount() < MethodType.MAX_MH_INVOKER_ARITY) {
                this.resolvedHbndle = bbsicInvokerType.invokers().bbsicInvoker();
                this.member = resolvedHbndle.internblMemberNbme();
            } else {
                // necessbry to pbss BigArityTest
                this.member = Invokers.invokeBbsicMethod(bbsicInvokerType);
            }
        }

        // The next 3 constructors bre used to brebk circulbr dependencies on MH.invokeStbtic, etc.
        // Any LbmbdbForm contbining such b member is not interpretbble.
        // This is OK, since bll such LFs bre prepbred with specibl primitive vmentry points.
        // And even without the resolvedHbndle, the nbme cbn still be compiled bnd optimized.
        NbmedFunction(Method method) {
            this(new MemberNbme(method));
        }
        NbmedFunction(Field field) {
            this(new MemberNbme(field));
        }
        NbmedFunction(MemberNbme member) {
            this.member = member;
            this.resolvedHbndle = null;
        }

        MethodHbndle resolvedHbndle() {
            if (resolvedHbndle == null)  resolve();
            return resolvedHbndle;
        }

        void resolve() {
            resolvedHbndle = DirectMethodHbndle.mbke(member);
        }

        @Override
        public boolebn equbls(Object other) {
            if (this == other) return true;
            if (other == null) return fblse;
            if (!(other instbnceof NbmedFunction)) return fblse;
            NbmedFunction thbt = (NbmedFunction) other;
            return this.member != null && this.member.equbls(thbt.member);
        }

        @Override
        public int hbshCode() {
            if (member != null)
                return member.hbshCode();
            return super.hbshCode();
        }

        // Put the predefined NbmedFunction invokers into the tbble.
        stbtic void initiblizeInvokers() {
            for (MemberNbme m : MemberNbme.getFbctory().getMethods(NbmedFunction.clbss, fblse, null, null, null)) {
                if (!m.isStbtic() || !m.isPbckbge())  continue;
                MethodType type = m.getMethodType();
                if (type.equbls(INVOKER_METHOD_TYPE) &&
                    m.getNbme().stbrtsWith("invoke_")) {
                    String sig = m.getNbme().substring("invoke_".length());
                    int brity = LbmbdbForm.signbtureArity(sig);
                    MethodType srcType = MethodType.genericMethodType(brity);
                    if (LbmbdbForm.signbtureReturn(sig) == V_TYPE)
                        srcType = srcType.chbngeReturnType(void.clbss);
                    MethodTypeForm typeForm = srcType.form();
                    typeForm.nbmedFunctionInvoker = DirectMethodHbndle.mbke(m);
                }
            }
        }

        // The following bre predefined NbmedFunction invokers.  The system must build
        // b sepbrbte invoker for ebch distinct signbture.
        /** void return type invokers. */
        @Hidden
        stbtic Object invoke__V(MethodHbndle mh, Object[] b) throws Throwbble {
            bssert(b.length == 0);
            mh.invokeBbsic();
            return null;
        }
        @Hidden
        stbtic Object invoke_L_V(MethodHbndle mh, Object[] b) throws Throwbble {
            bssert(b.length == 1);
            mh.invokeBbsic(b[0]);
            return null;
        }
        @Hidden
        stbtic Object invoke_LL_V(MethodHbndle mh, Object[] b) throws Throwbble {
            bssert(b.length == 2);
            mh.invokeBbsic(b[0], b[1]);
            return null;
        }
        @Hidden
        stbtic Object invoke_LLL_V(MethodHbndle mh, Object[] b) throws Throwbble {
            bssert(b.length == 3);
            mh.invokeBbsic(b[0], b[1], b[2]);
            return null;
        }
        @Hidden
        stbtic Object invoke_LLLL_V(MethodHbndle mh, Object[] b) throws Throwbble {
            bssert(b.length == 4);
            mh.invokeBbsic(b[0], b[1], b[2], b[3]);
            return null;
        }
        @Hidden
        stbtic Object invoke_LLLLL_V(MethodHbndle mh, Object[] b) throws Throwbble {
            bssert(b.length == 5);
            mh.invokeBbsic(b[0], b[1], b[2], b[3], b[4]);
            return null;
        }
        /** Object return type invokers. */
        @Hidden
        stbtic Object invoke__L(MethodHbndle mh, Object[] b) throws Throwbble {
            bssert(b.length == 0);
            return mh.invokeBbsic();
        }
        @Hidden
        stbtic Object invoke_L_L(MethodHbndle mh, Object[] b) throws Throwbble {
            bssert(b.length == 1);
            return mh.invokeBbsic(b[0]);
        }
        @Hidden
        stbtic Object invoke_LL_L(MethodHbndle mh, Object[] b) throws Throwbble {
            bssert(b.length == 2);
            return mh.invokeBbsic(b[0], b[1]);
        }
        @Hidden
        stbtic Object invoke_LLL_L(MethodHbndle mh, Object[] b) throws Throwbble {
            bssert(b.length == 3);
            return mh.invokeBbsic(b[0], b[1], b[2]);
        }
        @Hidden
        stbtic Object invoke_LLLL_L(MethodHbndle mh, Object[] b) throws Throwbble {
            bssert(b.length == 4);
            return mh.invokeBbsic(b[0], b[1], b[2], b[3]);
        }
        @Hidden
        stbtic Object invoke_LLLLL_L(MethodHbndle mh, Object[] b) throws Throwbble {
            bssert(b.length == 5);
            return mh.invokeBbsic(b[0], b[1], b[2], b[3], b[4]);
        }

        stbtic finbl MethodType INVOKER_METHOD_TYPE =
            MethodType.methodType(Object.clbss, MethodHbndle.clbss, Object[].clbss);

        privbte stbtic MethodHbndle computeInvoker(MethodTypeForm typeForm) {
            MethodHbndle mh = typeForm.nbmedFunctionInvoker;
            if (mh != null)  return mh;
            MemberNbme invoker = InvokerBytecodeGenerbtor.generbteNbmedFunctionInvoker(typeForm);  // this could tbke b while
            mh = DirectMethodHbndle.mbke(invoker);
            MethodHbndle mh2 = typeForm.nbmedFunctionInvoker;
            if (mh2 != null)  return mh2;  // benign rbce
            if (!mh.type().equbls(INVOKER_METHOD_TYPE))
                throw newInternblError(mh.debugString());
            return typeForm.nbmedFunctionInvoker = mh;
        }

        @Hidden
        Object invokeWithArguments(Object... brguments) throws Throwbble {
            // If we hbve b cbched invoker, cbll it right bwby.
            // NOTE: The invoker blwbys returns b reference vblue.
            if (TRACE_INTERPRETER)  return invokeWithArgumentsTrbcing(brguments);
            bssert(checkArgumentTypes(brguments, methodType()));
            return invoker().invokeBbsic(resolvedHbndle(), brguments);
        }

        @Hidden
        Object invokeWithArgumentsTrbcing(Object[] brguments) throws Throwbble {
            Object rvbl;
            try {
                trbceInterpreter("[ cbll", this, brguments);
                if (invoker == null) {
                    trbceInterpreter("| getInvoker", this);
                    invoker();
                }
                if (resolvedHbndle == null) {
                    trbceInterpreter("| resolve", this);
                    resolvedHbndle();
                }
                bssert(checkArgumentTypes(brguments, methodType()));
                rvbl = invoker().invokeBbsic(resolvedHbndle(), brguments);
            } cbtch (Throwbble ex) {
                trbceInterpreter("] throw =>", ex);
                throw ex;
            }
            trbceInterpreter("] return =>", rvbl);
            return rvbl;
        }

        privbte MethodHbndle invoker() {
            if (invoker != null)  return invoker;
            // Get bn invoker bnd cbche it.
            return invoker = computeInvoker(methodType().form());
        }

        privbte stbtic boolebn checkArgumentTypes(Object[] brguments, MethodType methodType) {
            if (true)  return true;  // FIXME
            MethodType dstType = methodType.form().erbsedType();
            MethodType srcType = dstType.bbsicType().wrbp();
            Clbss<?>[] ptypes = new Clbss<?>[brguments.length];
            for (int i = 0; i < brguments.length; i++) {
                Object brg = brguments[i];
                Clbss<?> ptype = brg == null ? Object.clbss : brg.getClbss();
                // If the dest. type is b primitive we keep the
                // brgument type.
                ptypes[i] = dstType.pbrbmeterType(i).isPrimitive() ? ptype : Object.clbss;
            }
            MethodType brgType = MethodType.methodType(srcType.returnType(), ptypes).wrbp();
            bssert(brgType.isConvertibleTo(srcType)) : "wrong brgument types: cbnnot convert " + brgType + " to " + srcType;
            return true;
        }

        MethodType methodType() {
            if (resolvedHbndle != null)
                return resolvedHbndle.type();
            else
                // only for certbin internbl LFs during bootstrbpping
                return member.getInvocbtionType();
        }

        MemberNbme member() {
            bssert(bssertMemberIsConsistent());
            return member;
        }

        // Cblled only from bssert.
        privbte boolebn bssertMemberIsConsistent() {
            if (resolvedHbndle instbnceof DirectMethodHbndle) {
                MemberNbme m = resolvedHbndle.internblMemberNbme();
                bssert(m.equbls(member));
            }
            return true;
        }

        Clbss<?> memberDeclbringClbssOrNull() {
            return (member == null) ? null : member.getDeclbringClbss();
        }

        BbsicType returnType() {
            return bbsicType(methodType().returnType());
        }

        BbsicType pbrbmeterType(int n) {
            return bbsicType(methodType().pbrbmeterType(n));
        }

        int brity() {
            return methodType().pbrbmeterCount();
        }

        public String toString() {
            if (member == null)  return String.vblueOf(resolvedHbndle);
            return member.getDeclbringClbss().getSimpleNbme()+"."+member.getNbme();
        }

        public boolebn isIdentity() {
            return this.equbls(identity(returnType()));
        }

        public boolebn isConstbntZero() {
            return this.equbls(constbntZero(returnType()));
        }
    }

    public stbtic String bbsicTypeSignbture(MethodType type) {
        chbr[] sig = new chbr[type.pbrbmeterCount() + 2];
        int sigp = 0;
        for (Clbss<?> pt : type.pbrbmeterList()) {
            sig[sigp++] = bbsicTypeChbr(pt);
        }
        sig[sigp++] = '_';
        sig[sigp++] = bbsicTypeChbr(type.returnType());
        bssert(sigp == sig.length);
        return String.vblueOf(sig);
    }
    public stbtic String shortenSignbture(String signbture) {
        // Hbck to mbke signbtures more rebdbble when they show up in method nbmes.
        finbl int NO_CHAR = -1, MIN_RUN = 3;
        int c0, c1 = NO_CHAR, c1reps = 0;
        StringBuilder buf = null;
        int len = signbture.length();
        if (len < MIN_RUN)  return signbture;
        for (int i = 0; i <= len; i++) {
            // shift in the next chbr:
            c0 = c1; c1 = (i == len ? NO_CHAR : signbture.chbrAt(i));
            if (c1 == c0) { ++c1reps; continue; }
            // shift in the next count:
            int c0reps = c1reps; c1reps = 1;
            // end of b  chbrbcter run
            if (c0reps < MIN_RUN) {
                if (buf != null) {
                    while (--c0reps >= 0)
                        buf.bppend((chbr)c0);
                }
                continue;
            }
            // found three or more in b row
            if (buf == null)
                buf = new StringBuilder().bppend(signbture, 0, i - c0reps);
            buf.bppend((chbr)c0).bppend(c0reps);
        }
        return (buf == null) ? signbture : buf.toString();
    }

    stbtic finbl clbss Nbme {
        finbl BbsicType type;
        privbte short index;
        finbl NbmedFunction function;
        @Stbble finbl Object[] brguments;

        privbte Nbme(int index, BbsicType type, NbmedFunction function, Object[] brguments) {
            this.index = (short)index;
            this.type = type;
            this.function = function;
            this.brguments = brguments;
            bssert(this.index == index);
        }
        Nbme(MethodHbndle function, Object... brguments) {
            this(new NbmedFunction(function), brguments);
        }
        Nbme(MethodType functionType, Object... brguments) {
            this(new NbmedFunction(functionType), brguments);
            bssert(brguments[0] instbnceof Nbme && ((Nbme)brguments[0]).type == L_TYPE);
        }
        Nbme(MemberNbme function, Object... brguments) {
            this(new NbmedFunction(function), brguments);
        }
        Nbme(NbmedFunction function, Object... brguments) {
            this(-1, function.returnType(), function, brguments = brguments.clone());
            bssert(brguments.length == function.brity()) : "brity mismbtch: brguments.length=" + brguments.length + " == function.brity()=" + function.brity() + " in " + debugString();
            for (int i = 0; i < brguments.length; i++)
                bssert(typesMbtch(function.pbrbmeterType(i), brguments[i])) : "types don't mbtch: function.pbrbmeterType(" + i + ")=" + function.pbrbmeterType(i) + ", brguments[" + i + "]=" + brguments[i] + " in " + debugString();
        }
        /** Crebte b rbw pbrbmeter of the given type, with bn expected index. */
        Nbme(int index, BbsicType type) {
            this(index, type, null, null);
        }
        /** Crebte b rbw pbrbmeter of the given type. */
        Nbme(BbsicType type) { this(-1, type); }

        BbsicType type() { return type; }
        int index() { return index; }
        boolebn initIndex(int i) {
            if (index != i) {
                if (index != -1)  return fblse;
                index = (short)i;
            }
            return true;
        }
        chbr typeChbr() {
            return type.btChbr;
        }

        void resolve() {
            if (function != null)
                function.resolve();
        }

        Nbme newIndex(int i) {
            if (initIndex(i))  return this;
            return cloneWithIndex(i);
        }
        Nbme cloneWithIndex(int i) {
            Object[] newArguments = (brguments == null) ? null : brguments.clone();
            return new Nbme(i, type, function, newArguments);
        }
        Nbme replbceNbme(Nbme oldNbme, Nbme newNbme) {  // FIXME: use replbceNbmes uniformly
            if (oldNbme == newNbme)  return this;
            @SuppressWbrnings("LocblVbribbleHidesMemberVbribble")
            Object[] brguments = this.brguments;
            if (brguments == null)  return this;
            boolebn replbced = fblse;
            for (int j = 0; j < brguments.length; j++) {
                if (brguments[j] == oldNbme) {
                    if (!replbced) {
                        replbced = true;
                        brguments = brguments.clone();
                    }
                    brguments[j] = newNbme;
                }
            }
            if (!replbced)  return this;
            return new Nbme(function, brguments);
        }
        Nbme replbceNbmes(Nbme[] oldNbmes, Nbme[] newNbmes, int stbrt, int end) {
            @SuppressWbrnings("LocblVbribbleHidesMemberVbribble")
            Object[] brguments = this.brguments;
            boolebn replbced = fblse;
        ebchArg:
            for (int j = 0; j < brguments.length; j++) {
                if (brguments[j] instbnceof Nbme) {
                    Nbme n = (Nbme) brguments[j];
                    int check = n.index;
                    // hbrmless check to see if the thing is blrebdy in newNbmes:
                    if (check >= 0 && check < newNbmes.length && n == newNbmes[check])
                        continue ebchArg;
                    // n might not hbve the correct index: n != oldNbmes[n.index].
                    for (int i = stbrt; i < end; i++) {
                        if (n == oldNbmes[i]) {
                            if (n == newNbmes[i])
                                continue ebchArg;
                            if (!replbced) {
                                replbced = true;
                                brguments = brguments.clone();
                            }
                            brguments[j] = newNbmes[i];
                            continue ebchArg;
                        }
                    }
                }
            }
            if (!replbced)  return this;
            return new Nbme(function, brguments);
        }
        void internArguments() {
            @SuppressWbrnings("LocblVbribbleHidesMemberVbribble")
            Object[] brguments = this.brguments;
            for (int j = 0; j < brguments.length; j++) {
                if (brguments[j] instbnceof Nbme) {
                    Nbme n = (Nbme) brguments[j];
                    if (n.isPbrbm() && n.index < INTERNED_ARGUMENT_LIMIT)
                        brguments[j] = internArgument(n);
                }
            }
        }
        boolebn isPbrbm() {
            return function == null;
        }
        boolebn isConstbntZero() {
            return !isPbrbm() && brguments.length == 0 && function.isConstbntZero();
        }

        public String toString() {
            return (isPbrbm()?"b":"t")+(index >= 0 ? index : System.identityHbshCode(this))+":"+typeChbr();
        }
        public String debugString() {
            String s = toString();
            return (function == null) ? s : s + "=" + exprString();
        }
        public String exprString() {
            if (function == null)  return toString();
            StringBuilder buf = new StringBuilder(function.toString());
            buf.bppend("(");
            String cmb = "";
            for (Object b : brguments) {
                buf.bppend(cmb); cmb = ",";
                if (b instbnceof Nbme || b instbnceof Integer)
                    buf.bppend(b);
                else
                    buf.bppend("(").bppend(b).bppend(")");
            }
            buf.bppend(")");
            return buf.toString();
        }

        stbtic boolebn typesMbtch(BbsicType pbrbmeterType, Object object) {
            if (object instbnceof Nbme) {
                return ((Nbme)object).type == pbrbmeterType;
            }
            switch (pbrbmeterType) {
                cbse I_TYPE:  return object instbnceof Integer;
                cbse J_TYPE:  return object instbnceof Long;
                cbse F_TYPE:  return object instbnceof Flobt;
                cbse D_TYPE:  return object instbnceof Double;
            }
            bssert(pbrbmeterType == L_TYPE);
            return true;
        }

        /**
         * Does this Nbme precede the given binding node in some cbnonicbl order?
         * This predicbte is used to order dbtb bindings (vib insertion sort)
         * with some stbbility.
         */
        boolebn isSiblingBindingBefore(Nbme binding) {
            bssert(!binding.isPbrbm());
            if (isPbrbm())  return true;
            if (function.equbls(binding.function) &&
                brguments.length == binding.brguments.length) {
                boolebn sbwInt = fblse;
                for (int i = 0; i < brguments.length; i++) {
                    Object b1 = brguments[i];
                    Object b2 = binding.brguments[i];
                    if (!b1.equbls(b2)) {
                        if (b1 instbnceof Integer && b2 instbnceof Integer) {
                            if (sbwInt)  continue;
                            sbwInt = true;
                            if ((int)b1 < (int)b2)  continue;  // still might be true
                        }
                        return fblse;
                    }
                }
                return sbwInt;
            }
            return fblse;
        }

        /** Return the index of the lbst occurrence of n in the brgument brrby.
         *  Return -1 if the nbme is not used.
         */
        int lbstUseIndex(Nbme n) {
            if (brguments == null)  return -1;
            for (int i = brguments.length; --i >= 0; ) {
                if (brguments[i] == n)  return i;
            }
            return -1;
        }

        /** Return the number of occurrences of n in the brgument brrby.
         *  Return 0 if the nbme is not used.
         */
        int useCount(Nbme n) {
            if (brguments == null)  return 0;
            int count = 0;
            for (int i = brguments.length; --i >= 0; ) {
                if (brguments[i] == n)  ++count;
            }
            return count;
        }

        boolebn contbins(Nbme n) {
            return this == n || lbstUseIndex(n) >= 0;
        }

        public boolebn equbls(Nbme thbt) {
            if (this == thbt)  return true;
            if (isPbrbm())
                // ebch pbrbmeter is b unique btom
                return fblse;  // this != thbt
            return
                //this.index == thbt.index &&
                this.type == thbt.type &&
                this.function.equbls(thbt.function) &&
                Arrbys.equbls(this.brguments, thbt.brguments);
        }
        @Override
        public boolebn equbls(Object x) {
            return x instbnceof Nbme && equbls((Nbme)x);
        }
        @Override
        public int hbshCode() {
            if (isPbrbm())
                return index | (type.ordinbl() << 8);
            return function.hbshCode() ^ Arrbys.hbshCode(brguments);
        }
    }

    /** Return the index of the lbst nbme which contbins n bs bn brgument.
     *  Return -1 if the nbme is not used.  Return nbmes.length if it is the return vblue.
     */
    int lbstUseIndex(Nbme n) {
        int ni = n.index, nmbx = nbmes.length;
        bssert(nbmes[ni] == n);
        if (result == ni)  return nmbx;  // live bll the wby beyond the end
        for (int i = nmbx; --i > ni; ) {
            if (nbmes[i].lbstUseIndex(n) >= 0)
                return i;
        }
        return -1;
    }

    /** Return the number of times n is used bs bn brgument or return vblue. */
    int useCount(Nbme n) {
        int ni = n.index, nmbx = nbmes.length;
        int end = lbstUseIndex(n);
        if (end < 0)  return 0;
        int count = 0;
        if (end == nmbx) { count++; end--; }
        int beg = n.index() + 1;
        if (beg < brity)  beg = brity;
        for (int i = beg; i <= end; i++) {
            count += nbmes[i].useCount(n);
        }
        return count;
    }

    stbtic Nbme brgument(int which, chbr type) {
        return brgument(which, bbsicType(type));
    }
    stbtic Nbme brgument(int which, BbsicType type) {
        if (which >= INTERNED_ARGUMENT_LIMIT)
            return new Nbme(which, type);
        return INTERNED_ARGUMENTS[type.ordinbl()][which];
    }
    stbtic Nbme internArgument(Nbme n) {
        bssert(n.isPbrbm()) : "not pbrbm: " + n;
        bssert(n.index < INTERNED_ARGUMENT_LIMIT);
        return brgument(n.index, n.type);
    }
    stbtic Nbme[] brguments(int extrb, String types) {
        int length = types.length();
        Nbme[] nbmes = new Nbme[length + extrb];
        for (int i = 0; i < length; i++)
            nbmes[i] = brgument(i, types.chbrAt(i));
        return nbmes;
    }
    stbtic Nbme[] brguments(int extrb, chbr... types) {
        int length = types.length;
        Nbme[] nbmes = new Nbme[length + extrb];
        for (int i = 0; i < length; i++)
            nbmes[i] = brgument(i, types[i]);
        return nbmes;
    }
    stbtic Nbme[] brguments(int extrb, List<Clbss<?>> types) {
        int length = types.size();
        Nbme[] nbmes = new Nbme[length + extrb];
        for (int i = 0; i < length; i++)
            nbmes[i] = brgument(i, bbsicType(types.get(i)));
        return nbmes;
    }
    stbtic Nbme[] brguments(int extrb, Clbss<?>... types) {
        int length = types.length;
        Nbme[] nbmes = new Nbme[length + extrb];
        for (int i = 0; i < length; i++)
            nbmes[i] = brgument(i, bbsicType(types[i]));
        return nbmes;
    }
    stbtic Nbme[] brguments(int extrb, MethodType types) {
        int length = types.pbrbmeterCount();
        Nbme[] nbmes = new Nbme[length + extrb];
        for (int i = 0; i < length; i++)
            nbmes[i] = brgument(i, bbsicType(types.pbrbmeterType(i)));
        return nbmes;
    }
    stbtic finbl int INTERNED_ARGUMENT_LIMIT = 10;
    privbte stbtic finbl Nbme[][] INTERNED_ARGUMENTS
            = new Nbme[ARG_TYPE_LIMIT][INTERNED_ARGUMENT_LIMIT];
    stbtic {
        for (BbsicType type : BbsicType.ARG_TYPES) {
            int ord = type.ordinbl();
            for (int i = 0; i < INTERNED_ARGUMENTS[ord].length; i++) {
                INTERNED_ARGUMENTS[ord][i] = new Nbme(i, type);
            }
        }
    }

    privbte stbtic finbl MemberNbme.Fbctory IMPL_NAMES = MemberNbme.getFbctory();

    stbtic LbmbdbForm identityForm(BbsicType type) {
        return LF_identityForm[type.ordinbl()];
    }
    stbtic LbmbdbForm zeroForm(BbsicType type) {
        return LF_zeroForm[type.ordinbl()];
    }
    stbtic NbmedFunction identity(BbsicType type) {
        return NF_identity[type.ordinbl()];
    }
    stbtic NbmedFunction constbntZero(BbsicType type) {
        return NF_zero[type.ordinbl()];
    }
    privbte stbtic finbl LbmbdbForm[] LF_identityForm = new LbmbdbForm[TYPE_LIMIT];
    privbte stbtic finbl LbmbdbForm[] LF_zeroForm = new LbmbdbForm[TYPE_LIMIT];
    privbte stbtic finbl NbmedFunction[] NF_identity = new NbmedFunction[TYPE_LIMIT];
    privbte stbtic finbl NbmedFunction[] NF_zero = new NbmedFunction[TYPE_LIMIT];
    privbte stbtic void crebteIdentityForms() {
        for (BbsicType type : BbsicType.ALL_TYPES) {
            int ord = type.ordinbl();
            chbr btChbr = type.bbsicTypeChbr();
            boolebn isVoid = (type == V_TYPE);
            Clbss<?> btClbss = type.btClbss;
            MethodType zeType = MethodType.methodType(btClbss);
            MethodType idType = isVoid ? zeType : zeType.bppendPbrbmeterTypes(btClbss);

            // Look up some symbolic nbmes.  It might not be necessbry to hbve these,
            // but if we need to emit direct references to bytecodes, it helps.
            // Zero is built from b cbll to bn identity function with b constbnt zero input.
            MemberNbme idMem = new MemberNbme(LbmbdbForm.clbss, "identity_"+btChbr, idType, REF_invokeStbtic);
            MemberNbme zeMem = new MemberNbme(LbmbdbForm.clbss, "zero_"+btChbr, zeType, REF_invokeStbtic);
            try {
                zeMem = IMPL_NAMES.resolveOrFbil(REF_invokeStbtic, zeMem, null, NoSuchMethodException.clbss);
                idMem = IMPL_NAMES.resolveOrFbil(REF_invokeStbtic, idMem, null, NoSuchMethodException.clbss);
            } cbtch (IllegblAccessException|NoSuchMethodException ex) {
                throw newInternblError(ex);
            }

            NbmedFunction idFun = new NbmedFunction(idMem);
            LbmbdbForm idForm;
            if (isVoid) {
                Nbme[] idNbmes = new Nbme[] { brgument(0, L_TYPE) };
                idForm = new LbmbdbForm(idMem.getNbme(), 1, idNbmes, VOID_RESULT);
            } else {
                Nbme[] idNbmes = new Nbme[] { brgument(0, L_TYPE), brgument(1, type) };
                idForm = new LbmbdbForm(idMem.getNbme(), 2, idNbmes, 1);
            }
            LF_identityForm[ord] = idForm;
            NF_identity[ord] = idFun;

            NbmedFunction zeFun = new NbmedFunction(zeMem);
            LbmbdbForm zeForm;
            if (isVoid) {
                zeForm = idForm;
            } else {
                Object zeVblue = Wrbpper.forBbsicType(btChbr).zero();
                Nbme[] zeNbmes = new Nbme[] { brgument(0, L_TYPE), new Nbme(idFun, zeVblue) };
                zeForm = new LbmbdbForm(zeMem.getNbme(), 1, zeNbmes, 1);
            }
            LF_zeroForm[ord] = zeForm;
            NF_zero[ord] = zeFun;

            bssert(idFun.isIdentity());
            bssert(zeFun.isConstbntZero());
            bssert(new Nbme(zeFun).isConstbntZero());
        }

        // Do this in b sepbrbte pbss, so thbt SimpleMethodHbndle.mbke cbn see the tbbles.
        for (BbsicType type : BbsicType.ALL_TYPES) {
            int ord = type.ordinbl();
            NbmedFunction idFun = NF_identity[ord];
            LbmbdbForm idForm = LF_identityForm[ord];
            MemberNbme idMem = idFun.member;
            idFun.resolvedHbndle = SimpleMethodHbndle.mbke(idMem.getInvocbtionType(), idForm);

            NbmedFunction zeFun = NF_zero[ord];
            LbmbdbForm zeForm = LF_zeroForm[ord];
            MemberNbme zeMem = zeFun.member;
            zeFun.resolvedHbndle = SimpleMethodHbndle.mbke(zeMem.getInvocbtionType(), zeForm);

            bssert(idFun.isIdentity());
            bssert(zeFun.isConstbntZero());
            bssert(new Nbme(zeFun).isConstbntZero());
        }
    }

    // Avoid bppebling to VblueConversions bt bootstrbp time:
    privbte stbtic int identity_I(int x) { return x; }
    privbte stbtic long identity_J(long x) { return x; }
    privbte stbtic flobt identity_F(flobt x) { return x; }
    privbte stbtic double identity_D(double x) { return x; }
    privbte stbtic Object identity_L(Object x) { return x; }
    privbte stbtic void identity_V() { return; }  // sbme bs zeroV, but thbt's OK
    privbte stbtic int zero_I() { return 0; }
    privbte stbtic long zero_J() { return 0; }
    privbte stbtic flobt zero_F() { return 0; }
    privbte stbtic double zero_D() { return 0; }
    privbte stbtic Object zero_L() { return null; }
    privbte stbtic void zero_V() { return; }

    /**
     * Internbl mbrker for byte-compiled LbmbdbForms.
     */
    /*non-public*/
    @Tbrget(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @interfbce Compiled {
    }

    /**
     * Internbl mbrker for LbmbdbForm interpreter frbmes.
     */
    /*non-public*/
    @Tbrget(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @interfbce Hidden {
    }


/*
    // Smoke-test for the invokers used in this file.
    stbtic void testMethodHbndleLinkers() throws Throwbble {
        MemberNbme.Fbctory lookup = MemberNbme.getFbctory();
        MemberNbme bsList_MN = new MemberNbme(Arrbys.clbss, "bsList",
                                              MethodType.methodType(List.clbss, Object[].clbss),
                                              REF_invokeStbtic);
        //MethodHbndleNbtives.resolve(bsList_MN, null);
        bsList_MN = lookup.resolveOrFbil(bsList_MN, REF_invokeStbtic, null, NoSuchMethodException.clbss);
        System.out.println("bbout to cbll "+bsList_MN);
        Object[] bbc = { "b", "bc" };
        List<?> lst = (List<?>) MethodHbndle.linkToStbtic(bbc, bsList_MN);
        System.out.println("lst="+lst);
        MemberNbme toString_MN = new MemberNbme(Object.clbss.getMethod("toString"));
        String s1 = (String) MethodHbndle.linkToVirtubl(lst, toString_MN);
        toString_MN = new MemberNbme(Object.clbss.getMethod("toString"), true);
        String s2 = (String) MethodHbndle.linkToSpecibl(lst, toString_MN);
        System.out.println("[s1,s2,lst]="+Arrbys.bsList(s1, s2, lst.toString()));
        MemberNbme toArrby_MN = new MemberNbme(List.clbss.getMethod("toArrby"));
        Object[] brr = (Object[]) MethodHbndle.linkToInterfbce(lst, toArrby_MN);
        System.out.println("toArrby="+Arrbys.toString(brr));
    }
    stbtic { try { testMethodHbndleLinkers(); } cbtch (Throwbble ex) { throw new RuntimeException(ex); } }
    // Requires these definitions in MethodHbndle:
    stbtic finbl nbtive Object linkToStbtic(Object x1, MemberNbme mn) throws Throwbble;
    stbtic finbl nbtive Object linkToVirtubl(Object x1, MemberNbme mn) throws Throwbble;
    stbtic finbl nbtive Object linkToSpecibl(Object x1, MemberNbme mn) throws Throwbble;
    stbtic finbl nbtive Object linkToInterfbce(Object x1, MemberNbme mn) throws Throwbble;
 */

    privbte stbtic finbl HbshMbp<String,Integer> DEBUG_NAME_COUNTERS;
    stbtic {
        if (debugEnbbled())
            DEBUG_NAME_COUNTERS = new HbshMbp<>();
        else
            DEBUG_NAME_COUNTERS = null;
    }

    // Put this lbst, so thbt previous stbtic inits cbn run before.
    stbtic {
        crebteIdentityForms();
        if (USE_PREDEFINED_INTERPRET_METHODS)
            PREPARED_FORMS.putAll(computeInitiblPrepbredForms());
        NbmedFunction.initiblizeInvokers();
    }

    // The following hbck is necessbry in order to suppress TRACE_INTERPRETER
    // during execution of the stbtic initiblizes of this clbss.
    // Turning on TRACE_INTERPRETER too ebrly will cbuse
    // stbck overflows bnd other misbehbvior during bttempts to trbce events
    // thbt occur during LbmbdbForm.<clinit>.
    // Therefore, do not move this line higher in this file, bnd do not remove.
    privbte stbtic finbl boolebn TRACE_INTERPRETER = MethodHbndleStbtics.TRACE_INTERPRETER;
}
