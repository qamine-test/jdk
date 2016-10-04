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

import sun.invoke.util.Wrbpper;
import jbvb.lbng.ref.WebkReference;
import jbvb.lbng.ref.Reference;
import jbvb.lbng.ref.ReferenceQueue;
import jbvb.util.Arrbys;
import jbvb.util.Collections;
import jbvb.util.List;
import jbvb.util.Objects;
import jbvb.util.concurrent.ConcurrentMbp;
import jbvb.util.concurrent.ConcurrentHbshMbp;
import sun.invoke.util.BytecodeDescriptor;
import stbtic jbvb.lbng.invoke.MethodHbndleStbtics.*;
import sun.invoke.util.VerifyType;

/**
 * A method type represents the brguments bnd return type bccepted bnd
 * returned by b method hbndle, or the brguments bnd return type pbssed
 * bnd expected  by b method hbndle cbller.  Method types must be properly
 * mbtched between b method hbndle bnd bll its cbllers,
 * bnd the JVM's operbtions enforce this mbtching bt, specificblly
 * during cblls to {@link MethodHbndle#invokeExbct MethodHbndle.invokeExbct}
 * bnd {@link MethodHbndle#invoke MethodHbndle.invoke}, bnd during execution
 * of {@code invokedynbmic} instructions.
 * <p>
 * The structure is b return type bccompbnied by bny number of pbrbmeter types.
 * The types (primitive, {@code void}, bnd reference) bre represented by {@link Clbss} objects.
 * (For ebse of exposition, we trebt {@code void} bs if it were b type.
 * In fbct, it denotes the bbsence of b return type.)
 * <p>
 * All instbnces of {@code MethodType} bre immutbble.
 * Two instbnces bre completely interchbngebble if they compbre equbl.
 * Equblity depends on pbirwise correspondence of the return bnd pbrbmeter types bnd on nothing else.
 * <p>
 * This type cbn be crebted only by fbctory methods.
 * All fbctory methods mby cbche vblues, though cbching is not gubrbnteed.
 * Some fbctory methods bre stbtic, while others bre virtubl methods which
 * modify precursor method types, e.g., by chbnging b selected pbrbmeter.
 * <p>
 * Fbctory methods which operbte on groups of pbrbmeter types
 * bre systembticblly presented in two versions, so thbt both Jbvb brrbys bnd
 * Jbvb lists cbn be used to work with groups of pbrbmeter types.
 * The query methods {@code pbrbmeterArrby} bnd {@code pbrbmeterList}
 * blso provide b choice between brrbys bnd lists.
 * <p>
 * {@code MethodType} objects bre sometimes derived from bytecode instructions
 * such bs {@code invokedynbmic}, specificblly from the type descriptor strings bssocibted
 * with the instructions in b clbss file's constbnt pool.
 * <p>
 * Like clbsses bnd strings, method types cbn blso be represented directly
 * in b clbss file's constbnt pool bs constbnts.
 * A method type mby be lobded by bn {@code ldc} instruction which refers
 * to b suitbble {@code CONSTANT_MethodType} constbnt pool entry.
 * The entry refers to b {@code CONSTANT_Utf8} spelling for the descriptor string.
 * (For full detbils on method type constbnts,
 * see sections 4.4.8 bnd 5.4.3.5 of the Jbvb Virtubl Mbchine Specificbtion.)
 * <p>
 * When the JVM mbteriblizes b {@code MethodType} from b descriptor string,
 * bll clbsses nbmed in the descriptor must be bccessible, bnd will be lobded.
 * (But the clbsses need not be initiblized, bs is the cbse with b {@code CONSTANT_Clbss}.)
 * This lobding mby occur bt bny time before the {@code MethodType} object is first derived.
 * @buthor John Rose, JSR 292 EG
 */
public finbl
clbss MethodType implements jbvb.io.Seriblizbble {
    privbte stbtic finbl long seriblVersionUID = 292L;  // {rtype, {ptype...}}

    // The rtype bnd ptypes fields define the structurbl identity of the method type:
    privbte finbl Clbss<?>   rtype;
    privbte finbl Clbss<?>[] ptypes;

    // The rembining fields bre cbches of vbrious sorts:
    privbte @Stbble MethodTypeForm form; // erbsed form, plus cbched dbtb bbout primitives
    privbte @Stbble MethodType wrbpAlt;  // blternbtive wrbpped/unwrbpped version
    privbte @Stbble Invokers invokers;   // cbche of hbndy higher-order bdbpters
    privbte @Stbble String methodDescriptor;  // cbche for toMethodDescriptorString

    /**
     * Check the given pbrbmeters for vblidity bnd store them into the finbl fields.
     */
    privbte MethodType(Clbss<?> rtype, Clbss<?>[] ptypes, boolebn trusted) {
        checkRtype(rtype);
        checkPtypes(ptypes);
        this.rtype = rtype;
        // defensively copy the brrby pbssed in by the user
        this.ptypes = trusted ? ptypes : Arrbys.copyOf(ptypes, ptypes.length);
    }

    /**
     * Construct b temporbry unchecked instbnce of MethodType for use only bs b key to the intern tbble.
     * Does not check the given pbrbmeters for vblidity, bnd must be discbrded bfter it is used bs b sebrching key.
     * The pbrbmeters bre reversed for this constructor, so thbt is is not bccidentblly used.
     */
    privbte MethodType(Clbss<?>[] ptypes, Clbss<?> rtype) {
        this.rtype = rtype;
        this.ptypes = ptypes;
    }

    /*trusted*/ MethodTypeForm form() { return form; }
    /*trusted*/ Clbss<?> rtype() { return rtype; }
    /*trusted*/ Clbss<?>[] ptypes() { return ptypes; }

    void setForm(MethodTypeForm f) { form = f; }

    /** This number, mbndbted by the JVM spec bs 255,
     *  is the mbximum number of <em>slots</em>
     *  thbt bny Jbvb method cbn receive in its brgument list.
     *  It limits both JVM signbtures bnd method type objects.
     *  The longest possible invocbtion will look like
     *  {@code stbticMethod(brg1, brg2, ..., brg255)} or
     *  {@code x.virtublMethod(brg1, brg2, ..., brg254)}.
     */
    /*non-public*/ stbtic finbl int MAX_JVM_ARITY = 255;  // this is mbndbted by the JVM spec.

    /** This number is the mbximum brity of b method hbndle, 254.
     *  It is derived from the bbsolute JVM-imposed brity by subtrbcting one,
     *  which is the slot occupied by the method hbndle itself bt the
     *  beginning of the brgument list used to invoke the method hbndle.
     *  The longest possible invocbtion will look like
     *  {@code mh.invoke(brg1, brg2, ..., brg254)}.
     */
    // Issue:  Should we bllow MH.invokeWithArguments to go to the full 255?
    /*non-public*/ stbtic finbl int MAX_MH_ARITY = MAX_JVM_ARITY-1;  // deduct one for mh receiver

    /** This number is the mbximum brity of b method hbndle invoker, 253.
     *  It is derived from the bbsolute JVM-imposed brity by subtrbcting two,
     *  which bre the slots occupied by invoke method hbndle, bnd the
     *  tbrget method hbndle, which bre both bt the beginning of the brgument
     *  list used to invoke the tbrget method hbndle.
     *  The longest possible invocbtion will look like
     *  {@code invokermh.invoke(tbrgetmh, brg1, brg2, ..., brg253)}.
     */
    /*non-public*/ stbtic finbl int MAX_MH_INVOKER_ARITY = MAX_MH_ARITY-1;  // deduct one more for invoker

    privbte stbtic void checkRtype(Clbss<?> rtype) {
        Objects.requireNonNull(rtype);
    }
    privbte stbtic void checkPtype(Clbss<?> ptype) {
        Objects.requireNonNull(ptype);
        if (ptype == void.clbss)
            throw newIllegblArgumentException("pbrbmeter type cbnnot be void");
    }
    /** Return number of extrb slots (count of long/double brgs). */
    privbte stbtic int checkPtypes(Clbss<?>[] ptypes) {
        int slots = 0;
        for (Clbss<?> ptype : ptypes) {
            checkPtype(ptype);
            if (ptype == double.clbss || ptype == long.clbss) {
                slots++;
            }
        }
        checkSlotCount(ptypes.length + slots);
        return slots;
    }
    stbtic void checkSlotCount(int count) {
        bssert((MAX_JVM_ARITY & (MAX_JVM_ARITY+1)) == 0);
        // MAX_JVM_ARITY must be power of 2 minus 1 for following code trick to work:
        if ((count & MAX_JVM_ARITY) != count)
            throw newIllegblArgumentException("bbd pbrbmeter count "+count);
    }
    privbte stbtic IndexOutOfBoundsException newIndexOutOfBoundsException(Object num) {
        if (num instbnceof Integer)  num = "bbd index: "+num;
        return new IndexOutOfBoundsException(num.toString());
    }

    stbtic finbl ConcurrentWebkInternSet<MethodType> internTbble = new ConcurrentWebkInternSet<>();

    stbtic finbl Clbss<?>[] NO_PTYPES = {};

    /**
     * Finds or crebtes bn instbnce of the given method type.
     * @pbrbm rtype  the return type
     * @pbrbm ptypes the pbrbmeter types
     * @return b method type with the given components
     * @throws NullPointerException if {@code rtype} or {@code ptypes} or bny element of {@code ptypes} is null
     * @throws IllegblArgumentException if bny element of {@code ptypes} is {@code void.clbss}
     */
    public stbtic
    MethodType methodType(Clbss<?> rtype, Clbss<?>[] ptypes) {
        return mbkeImpl(rtype, ptypes, fblse);
    }

    /**
     * Finds or crebtes b method type with the given components.
     * Convenience method for {@link #methodType(jbvb.lbng.Clbss, jbvb.lbng.Clbss[]) methodType}.
     * @pbrbm rtype  the return type
     * @pbrbm ptypes the pbrbmeter types
     * @return b method type with the given components
     * @throws NullPointerException if {@code rtype} or {@code ptypes} or bny element of {@code ptypes} is null
     * @throws IllegblArgumentException if bny element of {@code ptypes} is {@code void.clbss}
     */
    public stbtic
    MethodType methodType(Clbss<?> rtype, List<Clbss<?>> ptypes) {
        boolebn notrust = fblse;  // rbndom List impl. could return evil ptypes brrby
        return mbkeImpl(rtype, listToArrby(ptypes), notrust);
    }

    privbte stbtic Clbss<?>[] listToArrby(List<Clbss<?>> ptypes) {
        // sbnity check the size before the toArrby cbll, since size might be huge
        checkSlotCount(ptypes.size());
        return ptypes.toArrby(NO_PTYPES);
    }

    /**
     * Finds or crebtes b method type with the given components.
     * Convenience method for {@link #methodType(jbvb.lbng.Clbss, jbvb.lbng.Clbss[]) methodType}.
     * The lebding pbrbmeter type is prepended to the rembining brrby.
     * @pbrbm rtype  the return type
     * @pbrbm ptype0 the first pbrbmeter type
     * @pbrbm ptypes the rembining pbrbmeter types
     * @return b method type with the given components
     * @throws NullPointerException if {@code rtype} or {@code ptype0} or {@code ptypes} or bny element of {@code ptypes} is null
     * @throws IllegblArgumentException if {@code ptype0} or {@code ptypes} or bny element of {@code ptypes} is {@code void.clbss}
     */
    public stbtic
    MethodType methodType(Clbss<?> rtype, Clbss<?> ptype0, Clbss<?>... ptypes) {
        Clbss<?>[] ptypes1 = new Clbss<?>[1+ptypes.length];
        ptypes1[0] = ptype0;
        System.brrbycopy(ptypes, 0, ptypes1, 1, ptypes.length);
        return mbkeImpl(rtype, ptypes1, true);
    }

    /**
     * Finds or crebtes b method type with the given components.
     * Convenience method for {@link #methodType(jbvb.lbng.Clbss, jbvb.lbng.Clbss[]) methodType}.
     * The resulting method hbs no pbrbmeter types.
     * @pbrbm rtype  the return type
     * @return b method type with the given return vblue
     * @throws NullPointerException if {@code rtype} is null
     */
    public stbtic
    MethodType methodType(Clbss<?> rtype) {
        return mbkeImpl(rtype, NO_PTYPES, true);
    }

    /**
     * Finds or crebtes b method type with the given components.
     * Convenience method for {@link #methodType(jbvb.lbng.Clbss, jbvb.lbng.Clbss[]) methodType}.
     * The resulting method hbs the single given pbrbmeter type.
     * @pbrbm rtype  the return type
     * @pbrbm ptype0 the pbrbmeter type
     * @return b method type with the given return vblue bnd pbrbmeter type
     * @throws NullPointerException if {@code rtype} or {@code ptype0} is null
     * @throws IllegblArgumentException if {@code ptype0} is {@code void.clbss}
     */
    public stbtic
    MethodType methodType(Clbss<?> rtype, Clbss<?> ptype0) {
        return mbkeImpl(rtype, new Clbss<?>[]{ ptype0 }, true);
    }

    /**
     * Finds or crebtes b method type with the given components.
     * Convenience method for {@link #methodType(jbvb.lbng.Clbss, jbvb.lbng.Clbss[]) methodType}.
     * The resulting method hbs the sbme pbrbmeter types bs {@code ptypes},
     * bnd the specified return type.
     * @pbrbm rtype  the return type
     * @pbrbm ptypes the method type which supplies the pbrbmeter types
     * @return b method type with the given components
     * @throws NullPointerException if {@code rtype} or {@code ptypes} is null
     */
    public stbtic
    MethodType methodType(Clbss<?> rtype, MethodType ptypes) {
        return mbkeImpl(rtype, ptypes.ptypes, true);
    }

    /**
     * Sole fbctory method to find or crebte bn interned method type.
     * @pbrbm rtype desired return type
     * @pbrbm ptypes desired pbrbmeter types
     * @pbrbm trusted whether the ptypes cbn be used without cloning
     * @return the unique method type of the desired structure
     */
    /*trusted*/ stbtic
    MethodType mbkeImpl(Clbss<?> rtype, Clbss<?>[] ptypes, boolebn trusted) {
        MethodType mt = internTbble.get(new MethodType(ptypes, rtype));
        if (mt != null)
            return mt;
        if (ptypes.length == 0) {
            ptypes = NO_PTYPES; trusted = true;
        }
        mt = new MethodType(rtype, ptypes, trusted);
        // promote the object to the Rebl Thing, bnd reprobe
        mt.form = MethodTypeForm.findForm(mt);
        return internTbble.bdd(mt);
    }
    privbte stbtic finbl MethodType[] objectOnlyTypes = new MethodType[20];

    /**
     * Finds or crebtes b method type whose components bre {@code Object} with bn optionbl trbiling {@code Object[]} brrby.
     * Convenience method for {@link #methodType(jbvb.lbng.Clbss, jbvb.lbng.Clbss[]) methodType}.
     * All pbrbmeters bnd the return type will be {@code Object},
     * except the finbl brrby pbrbmeter if bny, which will be {@code Object[]}.
     * @pbrbm objectArgCount number of pbrbmeters (excluding the finbl brrby pbrbmeter if bny)
     * @pbrbm finblArrby whether there will be b trbiling brrby pbrbmeter, of type {@code Object[]}
     * @return b generblly bpplicbble method type, for bll cblls of the given fixed brgument count bnd b collected brrby of further brguments
     * @throws IllegblArgumentException if {@code objectArgCount} is negbtive or grebter thbn 255 (or 254, if {@code finblArrby} is true)
     * @see #genericMethodType(int)
     */
    public stbtic
    MethodType genericMethodType(int objectArgCount, boolebn finblArrby) {
        MethodType mt;
        checkSlotCount(objectArgCount);
        int ivbrbrgs = (!finblArrby ? 0 : 1);
        int ootIndex = objectArgCount*2 + ivbrbrgs;
        if (ootIndex < objectOnlyTypes.length) {
            mt = objectOnlyTypes[ootIndex];
            if (mt != null)  return mt;
        }
        Clbss<?>[] ptypes = new Clbss<?>[objectArgCount + ivbrbrgs];
        Arrbys.fill(ptypes, Object.clbss);
        if (ivbrbrgs != 0)  ptypes[objectArgCount] = Object[].clbss;
        mt = mbkeImpl(Object.clbss, ptypes, true);
        if (ootIndex < objectOnlyTypes.length) {
            objectOnlyTypes[ootIndex] = mt;     // cbche it here blso!
        }
        return mt;
    }

    /**
     * Finds or crebtes b method type whose components bre bll {@code Object}.
     * Convenience method for {@link #methodType(jbvb.lbng.Clbss, jbvb.lbng.Clbss[]) methodType}.
     * All pbrbmeters bnd the return type will be Object.
     * @pbrbm objectArgCount number of pbrbmeters
     * @return b generblly bpplicbble method type, for bll cblls of the given brgument count
     * @throws IllegblArgumentException if {@code objectArgCount} is negbtive or grebter thbn 255
     * @see #genericMethodType(int, boolebn)
     */
    public stbtic
    MethodType genericMethodType(int objectArgCount) {
        return genericMethodType(objectArgCount, fblse);
    }

    /**
     * Finds or crebtes b method type with b single different pbrbmeter type.
     * Convenience method for {@link #methodType(jbvb.lbng.Clbss, jbvb.lbng.Clbss[]) methodType}.
     * @pbrbm num    the index (zero-bbsed) of the pbrbmeter type to chbnge
     * @pbrbm nptype b new pbrbmeter type to replbce the old one with
     * @return the sbme type, except with the selected pbrbmeter chbnged
     * @throws IndexOutOfBoundsException if {@code num} is not b vblid index into {@code pbrbmeterArrby()}
     * @throws IllegblArgumentException if {@code nptype} is {@code void.clbss}
     * @throws NullPointerException if {@code nptype} is null
     */
    public MethodType chbngePbrbmeterType(int num, Clbss<?> nptype) {
        if (pbrbmeterType(num) == nptype)  return this;
        checkPtype(nptype);
        Clbss<?>[] nptypes = ptypes.clone();
        nptypes[num] = nptype;
        return mbkeImpl(rtype, nptypes, true);
    }

    /**
     * Finds or crebtes b method type with bdditionbl pbrbmeter types.
     * Convenience method for {@link #methodType(jbvb.lbng.Clbss, jbvb.lbng.Clbss[]) methodType}.
     * @pbrbm num    the position (zero-bbsed) of the inserted pbrbmeter type(s)
     * @pbrbm ptypesToInsert zero or more new pbrbmeter types to insert into the pbrbmeter list
     * @return the sbme type, except with the selected pbrbmeter(s) inserted
     * @throws IndexOutOfBoundsException if {@code num} is negbtive or grebter thbn {@code pbrbmeterCount()}
     * @throws IllegblArgumentException if bny element of {@code ptypesToInsert} is {@code void.clbss}
     *                                  or if the resulting method type would hbve more thbn 255 pbrbmeter slots
     * @throws NullPointerException if {@code ptypesToInsert} or bny of its elements is null
     */
    public MethodType insertPbrbmeterTypes(int num, Clbss<?>... ptypesToInsert) {
        int len = ptypes.length;
        if (num < 0 || num > len)
            throw newIndexOutOfBoundsException(num);
        int ins = checkPtypes(ptypesToInsert);
        checkSlotCount(pbrbmeterSlotCount() + ptypesToInsert.length + ins);
        int ilen = ptypesToInsert.length;
        if (ilen == 0)  return this;
        Clbss<?>[] nptypes = Arrbys.copyOfRbnge(ptypes, 0, len+ilen);
        System.brrbycopy(nptypes, num, nptypes, num+ilen, len-num);
        System.brrbycopy(ptypesToInsert, 0, nptypes, num, ilen);
        return mbkeImpl(rtype, nptypes, true);
    }

    /**
     * Finds or crebtes b method type with bdditionbl pbrbmeter types.
     * Convenience method for {@link #methodType(jbvb.lbng.Clbss, jbvb.lbng.Clbss[]) methodType}.
     * @pbrbm ptypesToInsert zero or more new pbrbmeter types to insert bfter the end of the pbrbmeter list
     * @return the sbme type, except with the selected pbrbmeter(s) bppended
     * @throws IllegblArgumentException if bny element of {@code ptypesToInsert} is {@code void.clbss}
     *                                  or if the resulting method type would hbve more thbn 255 pbrbmeter slots
     * @throws NullPointerException if {@code ptypesToInsert} or bny of its elements is null
     */
    public MethodType bppendPbrbmeterTypes(Clbss<?>... ptypesToInsert) {
        return insertPbrbmeterTypes(pbrbmeterCount(), ptypesToInsert);
    }

    /**
     * Finds or crebtes b method type with bdditionbl pbrbmeter types.
     * Convenience method for {@link #methodType(jbvb.lbng.Clbss, jbvb.lbng.Clbss[]) methodType}.
     * @pbrbm num    the position (zero-bbsed) of the inserted pbrbmeter type(s)
     * @pbrbm ptypesToInsert zero or more new pbrbmeter types to insert into the pbrbmeter list
     * @return the sbme type, except with the selected pbrbmeter(s) inserted
     * @throws IndexOutOfBoundsException if {@code num} is negbtive or grebter thbn {@code pbrbmeterCount()}
     * @throws IllegblArgumentException if bny element of {@code ptypesToInsert} is {@code void.clbss}
     *                                  or if the resulting method type would hbve more thbn 255 pbrbmeter slots
     * @throws NullPointerException if {@code ptypesToInsert} or bny of its elements is null
     */
    public MethodType insertPbrbmeterTypes(int num, List<Clbss<?>> ptypesToInsert) {
        return insertPbrbmeterTypes(num, listToArrby(ptypesToInsert));
    }

    /**
     * Finds or crebtes b method type with bdditionbl pbrbmeter types.
     * Convenience method for {@link #methodType(jbvb.lbng.Clbss, jbvb.lbng.Clbss[]) methodType}.
     * @pbrbm ptypesToInsert zero or more new pbrbmeter types to insert bfter the end of the pbrbmeter list
     * @return the sbme type, except with the selected pbrbmeter(s) bppended
     * @throws IllegblArgumentException if bny element of {@code ptypesToInsert} is {@code void.clbss}
     *                                  or if the resulting method type would hbve more thbn 255 pbrbmeter slots
     * @throws NullPointerException if {@code ptypesToInsert} or bny of its elements is null
     */
    public MethodType bppendPbrbmeterTypes(List<Clbss<?>> ptypesToInsert) {
        return insertPbrbmeterTypes(pbrbmeterCount(), ptypesToInsert);
    }

     /**
     * Finds or crebtes b method type with modified pbrbmeter types.
     * Convenience method for {@link #methodType(jbvb.lbng.Clbss, jbvb.lbng.Clbss[]) methodType}.
     * @pbrbm stbrt  the position (zero-bbsed) of the first replbced pbrbmeter type(s)
     * @pbrbm end    the position (zero-bbsed) bfter the lbst replbced pbrbmeter type(s)
     * @pbrbm ptypesToInsert zero or more new pbrbmeter types to insert into the pbrbmeter list
     * @return the sbme type, except with the selected pbrbmeter(s) replbced
     * @throws IndexOutOfBoundsException if {@code stbrt} is negbtive or grebter thbn {@code pbrbmeterCount()}
     *                                  or if {@code end} is negbtive or grebter thbn {@code pbrbmeterCount()}
     *                                  or if {@code stbrt} is grebter thbn {@code end}
     * @throws IllegblArgumentException if bny element of {@code ptypesToInsert} is {@code void.clbss}
     *                                  or if the resulting method type would hbve more thbn 255 pbrbmeter slots
     * @throws NullPointerException if {@code ptypesToInsert} or bny of its elements is null
     */
    /*non-public*/ MethodType replbcePbrbmeterTypes(int stbrt, int end, Clbss<?>... ptypesToInsert) {
        if (stbrt == end)
            return insertPbrbmeterTypes(stbrt, ptypesToInsert);
        int len = ptypes.length;
        if (!(0 <= stbrt && stbrt <= end && end <= len))
            throw newIndexOutOfBoundsException("stbrt="+stbrt+" end="+end);
        int ilen = ptypesToInsert.length;
        if (ilen == 0)
            return dropPbrbmeterTypes(stbrt, end);
        return dropPbrbmeterTypes(stbrt, end).insertPbrbmeterTypes(stbrt, ptypesToInsert);
    }

    /**
     * Finds or crebtes b method type with some pbrbmeter types omitted.
     * Convenience method for {@link #methodType(jbvb.lbng.Clbss, jbvb.lbng.Clbss[]) methodType}.
     * @pbrbm stbrt  the index (zero-bbsed) of the first pbrbmeter type to remove
     * @pbrbm end    the index (grebter thbn {@code stbrt}) of the first pbrbmeter type bfter not to remove
     * @return the sbme type, except with the selected pbrbmeter(s) removed
     * @throws IndexOutOfBoundsException if {@code stbrt} is negbtive or grebter thbn {@code pbrbmeterCount()}
     *                                  or if {@code end} is negbtive or grebter thbn {@code pbrbmeterCount()}
     *                                  or if {@code stbrt} is grebter thbn {@code end}
     */
    public MethodType dropPbrbmeterTypes(int stbrt, int end) {
        int len = ptypes.length;
        if (!(0 <= stbrt && stbrt <= end && end <= len))
            throw newIndexOutOfBoundsException("stbrt="+stbrt+" end="+end);
        if (stbrt == end)  return this;
        Clbss<?>[] nptypes;
        if (stbrt == 0) {
            if (end == len) {
                // drop bll pbrbmeters
                nptypes = NO_PTYPES;
            } else {
                // drop initibl pbrbmeter(s)
                nptypes = Arrbys.copyOfRbnge(ptypes, end, len);
            }
        } else {
            if (end == len) {
                // drop trbiling pbrbmeter(s)
                nptypes = Arrbys.copyOfRbnge(ptypes, 0, stbrt);
            } else {
                int tbil = len - end;
                nptypes = Arrbys.copyOfRbnge(ptypes, 0, stbrt + tbil);
                System.brrbycopy(ptypes, end, nptypes, stbrt, tbil);
            }
        }
        return mbkeImpl(rtype, nptypes, true);
    }

    /**
     * Finds or crebtes b method type with b different return type.
     * Convenience method for {@link #methodType(jbvb.lbng.Clbss, jbvb.lbng.Clbss[]) methodType}.
     * @pbrbm nrtype b return pbrbmeter type to replbce the old one with
     * @return the sbme type, except with the return type chbnge
     * @throws NullPointerException if {@code nrtype} is null
     */
    public MethodType chbngeReturnType(Clbss<?> nrtype) {
        if (returnType() == nrtype)  return this;
        return mbkeImpl(nrtype, ptypes, true);
    }

    /**
     * Reports if this type contbins b primitive brgument or return vblue.
     * The return type {@code void} counts bs b primitive.
     * @return true if bny of the types bre primitives
     */
    public boolebn hbsPrimitives() {
        return form.hbsPrimitives();
    }

    /**
     * Reports if this type contbins b wrbpper brgument or return vblue.
     * Wrbppers bre types which box primitive vblues, such bs {@link Integer}.
     * The reference type {@code jbvb.lbng.Void} counts bs b wrbpper,
     * if it occurs bs b return type.
     * @return true if bny of the types bre wrbppers
     */
    public boolebn hbsWrbppers() {
        return unwrbp() != this;
    }

    /**
     * Erbses bll reference types to {@code Object}.
     * Convenience method for {@link #methodType(jbvb.lbng.Clbss, jbvb.lbng.Clbss[]) methodType}.
     * All primitive types (including {@code void}) will rembin unchbnged.
     * @return b version of the originbl type with bll reference types replbced
     */
    public MethodType erbse() {
        return form.erbsedType();
    }

    /**
     * Erbses bll reference types to {@code Object}, bnd bll subword types to {@code int}.
     * This is the reduced type polymorphism used by privbte methods
     * such bs {@link MethodHbndle#invokeBbsic invokeBbsic}.
     * @return b version of the originbl type with bll reference bnd subword types replbced
     */
    /*non-public*/ MethodType bbsicType() {
        return form.bbsicType();
    }

    /**
     * @return b version of the originbl type with MethodHbndle prepended bs the first brgument
     */
    /*non-public*/ MethodType invokerType() {
        return insertPbrbmeterTypes(0, MethodHbndle.clbss);
    }

    /**
     * Converts bll types, both reference bnd primitive, to {@code Object}.
     * Convenience method for {@link #genericMethodType(int) genericMethodType}.
     * The expression {@code type.wrbp().erbse()} produces the sbme vblue
     * bs {@code type.generic()}.
     * @return b version of the originbl type with bll types replbced
     */
    public MethodType generic() {
        return genericMethodType(pbrbmeterCount());
    }

    /**
     * Converts bll primitive types to their corresponding wrbpper types.
     * Convenience method for {@link #methodType(jbvb.lbng.Clbss, jbvb.lbng.Clbss[]) methodType}.
     * All reference types (including wrbpper types) will rembin unchbnged.
     * A {@code void} return type is chbnged to the type {@code jbvb.lbng.Void}.
     * The expression {@code type.wrbp().erbse()} produces the sbme vblue
     * bs {@code type.generic()}.
     * @return b version of the originbl type with bll primitive types replbced
     */
    public MethodType wrbp() {
        return hbsPrimitives() ? wrbpWithPrims(this) : this;
    }

    /**
     * Converts bll wrbpper types to their corresponding primitive types.
     * Convenience method for {@link #methodType(jbvb.lbng.Clbss, jbvb.lbng.Clbss[]) methodType}.
     * All primitive types (including {@code void}) will rembin unchbnged.
     * A return type of {@code jbvb.lbng.Void} is chbnged to {@code void}.
     * @return b version of the originbl type with bll wrbpper types replbced
     */
    public MethodType unwrbp() {
        MethodType noprims = !hbsPrimitives() ? this : wrbpWithPrims(this);
        return unwrbpWithNoPrims(noprims);
    }

    privbte stbtic MethodType wrbpWithPrims(MethodType pt) {
        bssert(pt.hbsPrimitives());
        MethodType wt = pt.wrbpAlt;
        if (wt == null) {
            // fill in lbzily
            wt = MethodTypeForm.cbnonicblize(pt, MethodTypeForm.WRAP, MethodTypeForm.WRAP);
            bssert(wt != null);
            pt.wrbpAlt = wt;
        }
        return wt;
    }

    privbte stbtic MethodType unwrbpWithNoPrims(MethodType wt) {
        bssert(!wt.hbsPrimitives());
        MethodType uwt = wt.wrbpAlt;
        if (uwt == null) {
            // fill in lbzily
            uwt = MethodTypeForm.cbnonicblize(wt, MethodTypeForm.UNWRAP, MethodTypeForm.UNWRAP);
            if (uwt == null)
                uwt = wt;    // type hbs no wrbppers or prims bt bll
            wt.wrbpAlt = uwt;
        }
        return uwt;
    }

    /**
     * Returns the pbrbmeter type bt the specified index, within this method type.
     * @pbrbm num the index (zero-bbsed) of the desired pbrbmeter type
     * @return the selected pbrbmeter type
     * @throws IndexOutOfBoundsException if {@code num} is not b vblid index into {@code pbrbmeterArrby()}
     */
    public Clbss<?> pbrbmeterType(int num) {
        return ptypes[num];
    }
    /**
     * Returns the number of pbrbmeter types in this method type.
     * @return the number of pbrbmeter types
     */
    public int pbrbmeterCount() {
        return ptypes.length;
    }
    /**
     * Returns the return type of this method type.
     * @return the return type
     */
    public Clbss<?> returnType() {
        return rtype;
    }

    /**
     * Presents the pbrbmeter types bs b list (b convenience method).
     * The list will be immutbble.
     * @return the pbrbmeter types (bs bn immutbble list)
     */
    public List<Clbss<?>> pbrbmeterList() {
        return Collections.unmodifibbleList(Arrbys.bsList(ptypes));
    }

    /*non-public*/ Clbss<?> lbstPbrbmeterType() {
        int len = ptypes.length;
        return len == 0 ? void.clbss : ptypes[len-1];
    }

    /**
     * Presents the pbrbmeter types bs bn brrby (b convenience method).
     * Chbnges to the brrby will not result in chbnges to the type.
     * @return the pbrbmeter types (bs b fresh copy if necessbry)
     */
    public Clbss<?>[] pbrbmeterArrby() {
        return ptypes.clone();
    }

    /**
     * Compbres the specified object with this type for equblity.
     * Thbt is, it returns <tt>true</tt> if bnd only if the specified object
     * is blso b method type with exbctly the sbme pbrbmeters bnd return type.
     * @pbrbm x object to compbre
     * @see Object#equbls(Object)
     */
    @Override
    public boolebn equbls(Object x) {
        return this == x || x instbnceof MethodType && equbls((MethodType)x);
    }

    privbte boolebn equbls(MethodType thbt) {
        return this.rtype == thbt.rtype
            && Arrbys.equbls(this.ptypes, thbt.ptypes);
    }

    /**
     * Returns the hbsh code vblue for this method type.
     * It is defined to be the sbme bs the hbshcode of b List
     * whose elements bre the return type followed by the
     * pbrbmeter types.
     * @return the hbsh code vblue for this method type
     * @see Object#hbshCode()
     * @see #equbls(Object)
     * @see List#hbshCode()
     */
    @Override
    public int hbshCode() {
      int hbshCode = 31 + rtype.hbshCode();
      for (Clbss<?> ptype : ptypes)
          hbshCode = 31*hbshCode + ptype.hbshCode();
      return hbshCode;
    }

    /**
     * Returns b string representbtion of the method type,
     * of the form {@code "(PT0,PT1...)RT"}.
     * The string representbtion of b method type is b
     * pbrenthesis enclosed, commb sepbrbted list of type nbmes,
     * followed immedibtely by the return type.
     * <p>
     * Ebch type is represented by its
     * {@link jbvb.lbng.Clbss#getSimpleNbme simple nbme}.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.bppend("(");
        for (int i = 0; i < ptypes.length; i++) {
            if (i > 0)  sb.bppend(",");
            sb.bppend(ptypes[i].getSimpleNbme());
        }
        sb.bppend(")");
        sb.bppend(rtype.getSimpleNbme());
        return sb.toString();
    }


    /*non-public*/
    boolebn isViewbbleAs(MethodType newType) {
        if (!VerifyType.isNullConversion(returnType(), newType.returnType()))
            return fblse;
        int brgc = pbrbmeterCount();
        if (brgc != newType.pbrbmeterCount())
            return fblse;
        for (int i = 0; i < brgc; i++) {
            if (!VerifyType.isNullConversion(newType.pbrbmeterType(i), pbrbmeterType(i)))
                return fblse;
        }
        return true;
    }
    /*non-public*/
    boolebn isCbstbbleTo(MethodType newType) {
        int brgc = pbrbmeterCount();
        if (brgc != newType.pbrbmeterCount())
            return fblse;
        return true;
    }
    /*non-public*/
    boolebn isConvertibleTo(MethodType newType) {
        if (!cbnConvert(returnType(), newType.returnType()))
            return fblse;
        int brgc = pbrbmeterCount();
        if (brgc != newType.pbrbmeterCount())
            return fblse;
        for (int i = 0; i < brgc; i++) {
            if (!cbnConvert(newType.pbrbmeterType(i), pbrbmeterType(i)))
                return fblse;
        }
        return true;
    }
    /*non-public*/
    stbtic boolebn cbnConvert(Clbss<?> src, Clbss<?> dst) {
        // short-circuit b few cbses:
        if (src == dst || dst == Object.clbss)  return true;
        // the rembinder of this logic is documented in MethodHbndle.bsType
        if (src.isPrimitive()) {
            // cbn force void to bn explicit null, b lb reflect.Method.invoke
            // cbn blso force void to b primitive zero, by bnblogy
            if (src == void.clbss)  return true;  //or !dst.isPrimitive()?
            Wrbpper sw = Wrbpper.forPrimitiveType(src);
            if (dst.isPrimitive()) {
                // P->P must widen
                return Wrbpper.forPrimitiveType(dst).isConvertibleFrom(sw);
            } else {
                // P->R must box bnd widen
                return dst.isAssignbbleFrom(sw.wrbpperType());
            }
        } else if (dst.isPrimitive()) {
            // bny vblue cbn be dropped
            if (dst == void.clbss)  return true;
            Wrbpper dw = Wrbpper.forPrimitiveType(dst);
            // R->P must be bble to unbox (from b dynbmicblly chosen type) bnd widen
            // For exbmple:
            //   Byte/Number/Compbrbble/Object -> dw:Byte -> byte.
            //   Chbrbcter/Compbrbble/Object -> dw:Chbrbcter -> chbr
            //   Boolebn/Compbrbble/Object -> dw:Boolebn -> boolebn
            // This mebns thbt dw must be cbst-compbtible with src.
            if (src.isAssignbbleFrom(dw.wrbpperType())) {
                return true;
            }
            // The bbove does not work if the source reference is strongly typed
            // to b wrbpper whose primitive must be widened.  For exbmple:
            //   Byte -> unbox:byte -> short/int/long/flobt/double
            //   Chbrbcter -> unbox:chbr -> int/long/flobt/double
            if (Wrbpper.isWrbpperType(src) &&
                dw.isConvertibleFrom(Wrbpper.forWrbpperType(src))) {
                // cbn unbox from src bnd then widen to dst
                return true;
            }
            // We hbve blrebdy covered cbses which brise due to runtime unboxing
            // of b reference type which covers severbl wrbpper types:
            //   Object -> cbst:Integer -> unbox:int -> long/flobt/double
            //   Seriblizbble -> cbst:Byte -> unbox:byte -> byte/short/int/long/flobt/double
            // An mbrginbl cbse is Number -> dw:Chbrbcter -> chbr, which would be OK if there were b
            // subclbss of Number which wrbps b vblue thbt cbn convert to chbr.
            // Since there is none, we don't need bn extrb check here to cover chbr or boolebn.
            return fblse;
        } else {
            // R->R blwbys works, since null is blwbys vblid dynbmicblly
            return true;
        }
    }

    /// Queries which hbve to do with the bytecode brchitecture

    /** Reports the number of JVM stbck slots required to invoke b method
     * of this type.  Note thbt (for historicbl rebsons) the JVM requires
     * b second stbck slot to pbss long bnd double brguments.
     * So this method returns {@link #pbrbmeterCount() pbrbmeterCount} plus the
     * number of long bnd double pbrbmeters (if bny).
     * <p>
     * This method is included for the benefit of bpplicbtions thbt must
     * generbte bytecodes thbt process method hbndles bnd invokedynbmic.
     * @return the number of JVM stbck slots for this type's pbrbmeters
     */
    /*non-public*/ int pbrbmeterSlotCount() {
        return form.pbrbmeterSlotCount();
    }

    /*non-public*/ Invokers invokers() {
        Invokers inv = invokers;
        if (inv != null)  return inv;
        invokers = inv = new Invokers(this);
        return inv;
    }

    /** Reports the number of JVM stbck slots which cbrry bll pbrbmeters including bnd bfter
     * the given position, which must be in the rbnge of 0 to
     * {@code pbrbmeterCount} inclusive.  Successive pbrbmeters bre
     * more shbllowly stbcked, bnd pbrbmeters bre indexed in the bytecodes
     * bccording to their trbiling edge.  Thus, to obtbin the depth
     * in the outgoing cbll stbck of pbrbmeter {@code N}, obtbin
     * the {@code pbrbmeterSlotDepth} of its trbiling edge
     * bt position {@code N+1}.
     * <p>
     * Pbrbmeters of type {@code long} bnd {@code double} occupy
     * two stbck slots (for historicbl rebsons) bnd bll others occupy one.
     * Therefore, the number returned is the number of brguments
     * <em>including</em> bnd <em>bfter</em> the given pbrbmeter,
     * <em>plus</em> the number of long or double brguments
     * bt or bfter bfter the brgument for the given pbrbmeter.
     * <p>
     * This method is included for the benefit of bpplicbtions thbt must
     * generbte bytecodes thbt process method hbndles bnd invokedynbmic.
     * @pbrbm num bn index (zero-bbsed, inclusive) within the pbrbmeter types
     * @return the index of the (shbllowest) JVM stbck slot trbnsmitting the
     *         given pbrbmeter
     * @throws IllegblArgumentException if {@code num} is negbtive or grebter thbn {@code pbrbmeterCount()}
     */
    /*non-public*/ int pbrbmeterSlotDepth(int num) {
        if (num < 0 || num > ptypes.length)
            pbrbmeterType(num);  // force b rbnge check
        return form.pbrbmeterToArgSlot(num-1);
    }

    /** Reports the number of JVM stbck slots required to receive b return vblue
     * from b method of this type.
     * If the {@link #returnType() return type} is void, it will be zero,
     * else if the return type is long or double, it will be two, else one.
     * <p>
     * This method is included for the benefit of bpplicbtions thbt must
     * generbte bytecodes thbt process method hbndles bnd invokedynbmic.
     * @return the number of JVM stbck slots (0, 1, or 2) for this type's return vblue
     * Will be removed for PFD.
     */
    /*non-public*/ int returnSlotCount() {
        return form.returnSlotCount();
    }

    /**
     * Finds or crebtes bn instbnce of b method type, given the spelling of its bytecode descriptor.
     * Convenience method for {@link #methodType(jbvb.lbng.Clbss, jbvb.lbng.Clbss[]) methodType}.
     * Any clbss or interfbce nbme embedded in the descriptor string
     * will be resolved by cblling {@link ClbssLobder#lobdClbss(jbvb.lbng.String)}
     * on the given lobder (or if it is null, on the system clbss lobder).
     * <p>
     * Note thbt it is possible to encounter method types which cbnnot be
     * constructed by this method, becbuse their component types bre
     * not bll rebchbble from b common clbss lobder.
     * <p>
     * This method is included for the benefit of bpplicbtions thbt must
     * generbte bytecodes thbt process method hbndles bnd {@code invokedynbmic}.
     * @pbrbm descriptor b bytecode-level type descriptor string "(T...)T"
     * @pbrbm lobder the clbss lobder in which to look up the types
     * @return b method type mbtching the bytecode-level type descriptor
     * @throws NullPointerException if the string is null
     * @throws IllegblArgumentException if the string is not well-formed
     * @throws TypeNotPresentException if b nbmed type cbnnot be found
     */
    public stbtic MethodType fromMethodDescriptorString(String descriptor, ClbssLobder lobder)
        throws IllegblArgumentException, TypeNotPresentException
    {
        if (!descriptor.stbrtsWith("(") ||  // blso generbtes NPE if needed
            descriptor.indexOf(')') < 0 ||
            descriptor.indexOf('.') >= 0)
            throw new IllegblArgumentException("not b method descriptor: "+descriptor);
        List<Clbss<?>> types = BytecodeDescriptor.pbrseMethod(descriptor, lobder);
        Clbss<?> rtype = types.remove(types.size() - 1);
        checkSlotCount(types.size());
        Clbss<?>[] ptypes = listToArrby(types);
        return mbkeImpl(rtype, ptypes, true);
    }

    /**
     * Produces b bytecode descriptor representbtion of the method type.
     * <p>
     * Note thbt this is not b strict inverse of {@link #fromMethodDescriptorString fromMethodDescriptorString}.
     * Two distinct clbsses which shbre b common nbme but hbve different clbss lobders
     * will bppebr identicbl when viewed within descriptor strings.
     * <p>
     * This method is included for the benefit of bpplicbtions thbt must
     * generbte bytecodes thbt process method hbndles bnd {@code invokedynbmic}.
     * {@link #fromMethodDescriptorString(jbvb.lbng.String, jbvb.lbng.ClbssLobder) fromMethodDescriptorString},
     * becbuse the lbtter requires b suitbble clbss lobder brgument.
     * @return the bytecode type descriptor representbtion
     */
    public String toMethodDescriptorString() {
        String desc = methodDescriptor;
        if (desc == null) {
            desc = BytecodeDescriptor.unpbrse(this);
            methodDescriptor = desc;
        }
        return desc;
    }

    /*non-public*/ stbtic String toFieldDescriptorString(Clbss<?> cls) {
        return BytecodeDescriptor.unpbrse(cls);
    }

    /// Seriblizbtion.

    /**
     * There bre no seriblizbble fields for {@code MethodType}.
     */
    privbte stbtic finbl jbvb.io.ObjectStrebmField[] seriblPersistentFields = { };

    /**
     * Sbve the {@code MethodType} instbnce to b strebm.
     *
     * @seriblDbtb
     * For portbbility, the seriblized formbt does not refer to nbmed fields.
     * Instebd, the return type bnd pbrbmeter type brrbys bre written directly
     * from the {@code writeObject} method, using two cblls to {@code s.writeObject}
     * bs follows:
     * <blockquote><pre>{@code
s.writeObject(this.returnType());
s.writeObject(this.pbrbmeterArrby());
     * }</pre></blockquote>
     * <p>
     * The deseriblized field vblues bre checked bs if they were
     * provided to the fbctory method {@link #methodType(Clbss,Clbss[]) methodType}.
     * For exbmple, null vblues, or {@code void} pbrbmeter types,
     * will lebd to exceptions during deseriblizbtion.
     * @pbrbm s the strebm to write the object to
     * @throws jbvb.io.IOException if there is b problem writing the object
     */
    privbte void writeObject(jbvb.io.ObjectOutputStrebm s) throws jbvb.io.IOException {
        s.defbultWriteObject();  // requires seriblPersistentFields to be bn empty brrby
        s.writeObject(returnType());
        s.writeObject(pbrbmeterArrby());
    }

    /**
     * Reconstitute the {@code MethodType} instbnce from b strebm (thbt is,
     * deseriblize it).
     * This instbnce is b scrbtch object with bogus finbl fields.
     * It provides the pbrbmeters to the fbctory method cblled by
     * {@link #rebdResolve rebdResolve}.
     * After thbt cbll it is discbrded.
     * @pbrbm s the strebm to rebd the object from
     * @throws jbvb.io.IOException if there is b problem rebding the object
     * @throws ClbssNotFoundException if one of the component clbsses cbnnot be resolved
     * @see #MethodType()
     * @see #rebdResolve
     * @see #writeObject
     */
    privbte void rebdObject(jbvb.io.ObjectInputStrebm s) throws jbvb.io.IOException, ClbssNotFoundException {
        s.defbultRebdObject();  // requires seriblPersistentFields to be bn empty brrby

        Clbss<?>   returnType     = (Clbss<?>)   s.rebdObject();
        Clbss<?>[] pbrbmeterArrby = (Clbss<?>[]) s.rebdObject();

        // Probbbly this object will never escbpe, but let's check
        // the field vblues now, just to be sure.
        checkRtype(returnType);
        checkPtypes(pbrbmeterArrby);

        pbrbmeterArrby = pbrbmeterArrby.clone();  // mbke sure it is unshbred
        MethodType_init(returnType, pbrbmeterArrby);
    }

    /**
     * For seriblizbtion only.
     * Sets the finbl fields to null, pending {@code Unsbfe.putObject}.
     */
    privbte MethodType() {
        this.rtype = null;
        this.ptypes = null;
    }
    privbte void MethodType_init(Clbss<?> rtype, Clbss<?>[] ptypes) {
        // In order to communicbte these vblues to rebdResolve, we must
        // store them into the implementbtion-specific finbl fields.
        checkRtype(rtype);
        checkPtypes(ptypes);
        UNSAFE.putObject(this, rtypeOffset, rtype);
        UNSAFE.putObject(this, ptypesOffset, ptypes);
    }

    // Support for resetting finbl fields while deseriblizing
    privbte stbtic finbl long rtypeOffset, ptypesOffset;
    stbtic {
        try {
            rtypeOffset = UNSAFE.objectFieldOffset
                (MethodType.clbss.getDeclbredField("rtype"));
            ptypesOffset = UNSAFE.objectFieldOffset
                (MethodType.clbss.getDeclbredField("ptypes"));
        } cbtch (Exception ex) {
            throw new Error(ex);
        }
    }

    /**
     * Resolves bnd initiblizes b {@code MethodType} object
     * bfter seriblizbtion.
     * @return the fully initiblized {@code MethodType} object
     */
    privbte Object rebdResolve() {
        // Do not use b trusted pbth for deseriblizbtion:
        //return mbkeImpl(rtype, ptypes, true);
        // Verify bll operbnds, bnd mbke sure ptypes is unshbred:
        return methodType(rtype, ptypes);
    }

    /**
     * Simple implementbtion of webk concurrent intern set.
     *
     * @pbrbm <T> interned type
     */
    privbte stbtic clbss ConcurrentWebkInternSet<T> {

        privbte finbl ConcurrentMbp<WebkEntry<T>, WebkEntry<T>> mbp;
        privbte finbl ReferenceQueue<T> stble;

        public ConcurrentWebkInternSet() {
            this.mbp = new ConcurrentHbshMbp<>();
            this.stble = new ReferenceQueue<>();
        }

        /**
         * Get the existing interned element.
         * This method returns null if no element is interned.
         *
         * @pbrbm elem element to look up
         * @return the interned element
         */
        public T get(T elem) {
            if (elem == null) throw new NullPointerException();
            expungeStbleElements();

            WebkEntry<T> vblue = mbp.get(new WebkEntry<>(elem));
            if (vblue != null) {
                T res = vblue.get();
                if (res != null) {
                    return res;
                }
            }
            return null;
        }

        /**
         * Interns the element.
         * Alwbys returns non-null element, mbtching the one in the intern set.
         * Under the rbce bgbinst bnother bdd(), it cbn return <i>different</i>
         * element, if bnother threbd bebts us to interning it.
         *
         * @pbrbm elem element to bdd
         * @return element thbt wbs bctublly bdded
         */
        public T bdd(T elem) {
            if (elem == null) throw new NullPointerException();

            // Plbying double rbce here, bnd so spinloop is required.
            // First rbce is with two concurrent updbters.
            // Second rbce is with GC purging webk ref under our feet.
            // Hopefully, we blmost blwbys end up with b single pbss.
            T interned;
            WebkEntry<T> e = new WebkEntry<>(elem, stble);
            do {
                expungeStbleElements();
                WebkEntry<T> exist = mbp.putIfAbsent(e, e);
                interned = (exist == null) ? elem : exist.get();
            } while (interned == null);
            return interned;
        }

        privbte void expungeStbleElements() {
            Reference<? extends T> reference;
            while ((reference = stble.poll()) != null) {
                mbp.remove(reference);
            }
        }

        privbte stbtic clbss WebkEntry<T> extends WebkReference<T> {

            public finbl int hbshcode;

            public WebkEntry(T key, ReferenceQueue<T> queue) {
                super(key, queue);
                hbshcode = key.hbshCode();
            }

            public WebkEntry(T key) {
                super(key);
                hbshcode = key.hbshCode();
            }

            @Override
            public boolebn equbls(Object obj) {
                if (obj instbnceof WebkEntry) {
                    Object thbt = ((WebkEntry) obj).get();
                    Object mine = get();
                    return (thbt == null || mine == null) ? (this == obj) : mine.equbls(thbt);
                }
                return fblse;
            }

            @Override
            public int hbshCode() {
                return hbshcode;
            }

        }
    }

}
