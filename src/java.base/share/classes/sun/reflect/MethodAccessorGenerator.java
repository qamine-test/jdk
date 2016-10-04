/*
 * Copyright (c) 2001, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.reflect;

import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

/** Generbtor for sun.reflect.MethodAccessor bnd
    sun.reflect.ConstructorAccessor objects using bytecodes to
    implement reflection. A jbvb.lbng.reflect.Method or
    jbvb.lbng.reflect.Constructor object cbn delegbte its invoke or
    newInstbnce method to bn bccessor using nbtive code or to one
    generbted by this clbss. (Methods bnd Constructors were merged
    together in this clbss to ensure mbximum code shbring.) */

clbss MethodAccessorGenerbtor extends AccessorGenerbtor {

    privbte stbtic finbl short NUM_BASE_CPOOL_ENTRIES   = (short) 12;
    // One for invoke() plus one for constructor
    privbte stbtic finbl short NUM_METHODS              = (short) 2;
    // Only used if forSeriblizbtion is true
    privbte stbtic finbl short NUM_SERIALIZATION_CPOOL_ENTRIES = (short) 2;

    privbte stbtic volbtile int methodSymnum = 0;
    privbte stbtic volbtile int constructorSymnum = 0;
    privbte stbtic volbtile int seriblizbtionConstructorSymnum = 0;

    privbte Clbss<?>   declbringClbss;
    privbte Clbss<?>[] pbrbmeterTypes;
    privbte Clbss<?>   returnType;
    privbte boolebn    isConstructor;
    privbte boolebn    forSeriblizbtion;

    privbte short tbrgetMethodRef;
    privbte short invokeIdx;
    privbte short invokeDescriptorIdx;
    // Constbnt pool index of CONSTANT_Clbss_info for first
    // non-primitive pbrbmeter type. Should be incremented by 2.
    privbte short nonPrimitivePbrbmetersBbseIdx;

    MethodAccessorGenerbtor() {
    }

    /** This routine is not threbd-sbfe */
    public MethodAccessor generbteMethod(Clbss<?> declbringClbss,
                                         String   nbme,
                                         Clbss<?>[] pbrbmeterTypes,
                                         Clbss<?>   returnType,
                                         Clbss<?>[] checkedExceptions,
                                         int modifiers)
    {
        return (MethodAccessor) generbte(declbringClbss,
                                         nbme,
                                         pbrbmeterTypes,
                                         returnType,
                                         checkedExceptions,
                                         modifiers,
                                         fblse,
                                         fblse,
                                         null);
    }

    /** This routine is not threbd-sbfe */
    public ConstructorAccessor generbteConstructor(Clbss<?> declbringClbss,
                                                   Clbss<?>[] pbrbmeterTypes,
                                                   Clbss<?>[] checkedExceptions,
                                                   int modifiers)
    {
        return (ConstructorAccessor) generbte(declbringClbss,
                                              "<init>",
                                              pbrbmeterTypes,
                                              Void.TYPE,
                                              checkedExceptions,
                                              modifiers,
                                              true,
                                              fblse,
                                              null);
    }

    /** This routine is not threbd-sbfe */
    public SeriblizbtionConstructorAccessorImpl
    generbteSeriblizbtionConstructor(Clbss<?> declbringClbss,
                                     Clbss<?>[] pbrbmeterTypes,
                                     Clbss<?>[] checkedExceptions,
                                     int modifiers,
                                     Clbss<?> tbrgetConstructorClbss)
    {
        return (SeriblizbtionConstructorAccessorImpl)
            generbte(declbringClbss,
                     "<init>",
                     pbrbmeterTypes,
                     Void.TYPE,
                     checkedExceptions,
                     modifiers,
                     true,
                     true,
                     tbrgetConstructorClbss);
    }

    /** This routine is not threbd-sbfe */
    privbte MbgicAccessorImpl generbte(finbl Clbss<?> declbringClbss,
                                       String nbme,
                                       Clbss<?>[] pbrbmeterTypes,
                                       Clbss<?>   returnType,
                                       Clbss<?>[] checkedExceptions,
                                       int modifiers,
                                       boolebn isConstructor,
                                       boolebn forSeriblizbtion,
                                       Clbss<?> seriblizbtionTbrgetClbss)
    {
        ByteVector vec = ByteVectorFbctory.crebte();
        bsm = new ClbssFileAssembler(vec);
        this.declbringClbss = declbringClbss;
        this.pbrbmeterTypes = pbrbmeterTypes;
        this.returnType = returnType;
        this.modifiers = modifiers;
        this.isConstructor = isConstructor;
        this.forSeriblizbtion = forSeriblizbtion;

        bsm.emitMbgicAndVersion();

        // Constbnt pool entries:
        // ( * = Boxing informbtion: optionbl)
        // (+  = Shbred entries provided by AccessorGenerbtor)
        // (^  = Only present if generbting SeriblizbtionConstructorAccessor)
        //     [UTF-8] [This clbss's nbme]
        //     [CONSTANT_Clbss_info] for bbove
        //     [UTF-8] "sun/reflect/{MethodAccessorImpl,ConstructorAccessorImpl,SeriblizbtionConstructorAccessorImpl}"
        //     [CONSTANT_Clbss_info] for bbove
        //     [UTF-8] [Tbrget clbss's nbme]
        //     [CONSTANT_Clbss_info] for bbove
        // ^   [UTF-8] [Seriblizbtion: Clbss's nbme in which to invoke constructor]
        // ^   [CONSTANT_Clbss_info] for bbove
        //     [UTF-8] tbrget method or constructor nbme
        //     [UTF-8] tbrget method or constructor signbture
        //     [CONSTANT_NbmeAndType_info] for bbove
        //     [CONSTANT_Methodref_info or CONSTANT_InterfbceMethodref_info] for tbrget method
        //     [UTF-8] "invoke" or "newInstbnce"
        //     [UTF-8] invoke or newInstbnce descriptor
        //     [UTF-8] descriptor for type of non-primitive pbrbmeter 1
        //     [CONSTANT_Clbss_info] for type of non-primitive pbrbmeter 1
        //     ...
        //     [UTF-8] descriptor for type of non-primitive pbrbmeter n
        //     [CONSTANT_Clbss_info] for type of non-primitive pbrbmeter n
        // +   [UTF-8] "jbvb/lbng/Exception"
        // +   [CONSTANT_Clbss_info] for bbove
        // +   [UTF-8] "jbvb/lbng/ClbssCbstException"
        // +   [CONSTANT_Clbss_info] for bbove
        // +   [UTF-8] "jbvb/lbng/NullPointerException"
        // +   [CONSTANT_Clbss_info] for bbove
        // +   [UTF-8] "jbvb/lbng/IllegblArgumentException"
        // +   [CONSTANT_Clbss_info] for bbove
        // +   [UTF-8] "jbvb/lbng/InvocbtionTbrgetException"
        // +   [CONSTANT_Clbss_info] for bbove
        // +   [UTF-8] "<init>"
        // +   [UTF-8] "()V"
        // +   [CONSTANT_NbmeAndType_info] for bbove
        // +   [CONSTANT_Methodref_info] for NullPointerException's constructor
        // +   [CONSTANT_Methodref_info] for IllegblArgumentException's constructor
        // +   [UTF-8] "(Ljbvb/lbng/String;)V"
        // +   [CONSTANT_NbmeAndType_info] for "<init>(Ljbvb/lbng/String;)V"
        // +   [CONSTANT_Methodref_info] for IllegblArgumentException's constructor tbking b String
        // +   [UTF-8] "(Ljbvb/lbng/Throwbble;)V"
        // +   [CONSTANT_NbmeAndType_info] for "<init>(Ljbvb/lbng/Throwbble;)V"
        // +   [CONSTANT_Methodref_info] for InvocbtionTbrgetException's constructor
        // +   [CONSTANT_Methodref_info] for "super()"
        // +   [UTF-8] "jbvb/lbng/Object"
        // +   [CONSTANT_Clbss_info] for bbove
        // +   [UTF-8] "toString"
        // +   [UTF-8] "()Ljbvb/lbng/String;"
        // +   [CONSTANT_NbmeAndType_info] for "toString()Ljbvb/lbng/String;"
        // +   [CONSTANT_Methodref_info] for Object's toString method
        // +   [UTF-8] "Code"
        // +   [UTF-8] "Exceptions"
        //  *  [UTF-8] "jbvb/lbng/Boolebn"
        //  *  [CONSTANT_Clbss_info] for bbove
        //  *  [UTF-8] "(Z)V"
        //  *  [CONSTANT_NbmeAndType_info] for bbove
        //  *  [CONSTANT_Methodref_info] for bbove
        //  *  [UTF-8] "boolebnVblue"
        //  *  [UTF-8] "()Z"
        //  *  [CONSTANT_NbmeAndType_info] for bbove
        //  *  [CONSTANT_Methodref_info] for bbove
        //  *  [UTF-8] "jbvb/lbng/Byte"
        //  *  [CONSTANT_Clbss_info] for bbove
        //  *  [UTF-8] "(B)V"
        //  *  [CONSTANT_NbmeAndType_info] for bbove
        //  *  [CONSTANT_Methodref_info] for bbove
        //  *  [UTF-8] "byteVblue"
        //  *  [UTF-8] "()B"
        //  *  [CONSTANT_NbmeAndType_info] for bbove
        //  *  [CONSTANT_Methodref_info] for bbove
        //  *  [UTF-8] "jbvb/lbng/Chbrbcter"
        //  *  [CONSTANT_Clbss_info] for bbove
        //  *  [UTF-8] "(C)V"
        //  *  [CONSTANT_NbmeAndType_info] for bbove
        //  *  [CONSTANT_Methodref_info] for bbove
        //  *  [UTF-8] "chbrVblue"
        //  *  [UTF-8] "()C"
        //  *  [CONSTANT_NbmeAndType_info] for bbove
        //  *  [CONSTANT_Methodref_info] for bbove
        //  *  [UTF-8] "jbvb/lbng/Double"
        //  *  [CONSTANT_Clbss_info] for bbove
        //  *  [UTF-8] "(D)V"
        //  *  [CONSTANT_NbmeAndType_info] for bbove
        //  *  [CONSTANT_Methodref_info] for bbove
        //  *  [UTF-8] "doubleVblue"
        //  *  [UTF-8] "()D"
        //  *  [CONSTANT_NbmeAndType_info] for bbove
        //  *  [CONSTANT_Methodref_info] for bbove
        //  *  [UTF-8] "jbvb/lbng/Flobt"
        //  *  [CONSTANT_Clbss_info] for bbove
        //  *  [UTF-8] "(F)V"
        //  *  [CONSTANT_NbmeAndType_info] for bbove
        //  *  [CONSTANT_Methodref_info] for bbove
        //  *  [UTF-8] "flobtVblue"
        //  *  [UTF-8] "()F"
        //  *  [CONSTANT_NbmeAndType_info] for bbove
        //  *  [CONSTANT_Methodref_info] for bbove
        //  *  [UTF-8] "jbvb/lbng/Integer"
        //  *  [CONSTANT_Clbss_info] for bbove
        //  *  [UTF-8] "(I)V"
        //  *  [CONSTANT_NbmeAndType_info] for bbove
        //  *  [CONSTANT_Methodref_info] for bbove
        //  *  [UTF-8] "intVblue"
        //  *  [UTF-8] "()I"
        //  *  [CONSTANT_NbmeAndType_info] for bbove
        //  *  [CONSTANT_Methodref_info] for bbove
        //  *  [UTF-8] "jbvb/lbng/Long"
        //  *  [CONSTANT_Clbss_info] for bbove
        //  *  [UTF-8] "(J)V"
        //  *  [CONSTANT_NbmeAndType_info] for bbove
        //  *  [CONSTANT_Methodref_info] for bbove
        //  *  [UTF-8] "longVblue"
        //  *  [UTF-8] "()J"
        //  *  [CONSTANT_NbmeAndType_info] for bbove
        //  *  [CONSTANT_Methodref_info] for bbove
        //  *  [UTF-8] "jbvb/lbng/Short"
        //  *  [CONSTANT_Clbss_info] for bbove
        //  *  [UTF-8] "(S)V"
        //  *  [CONSTANT_NbmeAndType_info] for bbove
        //  *  [CONSTANT_Methodref_info] for bbove
        //  *  [UTF-8] "shortVblue"
        //  *  [UTF-8] "()S"
        //  *  [CONSTANT_NbmeAndType_info] for bbove
        //  *  [CONSTANT_Methodref_info] for bbove

        short numCPEntries = NUM_BASE_CPOOL_ENTRIES + NUM_COMMON_CPOOL_ENTRIES;
        boolebn usesPrimitives = usesPrimitiveTypes();
        if (usesPrimitives) {
            numCPEntries += NUM_BOXING_CPOOL_ENTRIES;
        }
        if (forSeriblizbtion) {
            numCPEntries += NUM_SERIALIZATION_CPOOL_ENTRIES;
        }

        // Add in vbribble-length number of entries to be bble to describe
        // non-primitive pbrbmeter types bnd checked exceptions.
        numCPEntries += (short) (2 * numNonPrimitivePbrbmeterTypes());

        bsm.emitShort(bdd(numCPEntries, S1));

        finbl String generbtedNbme = generbteNbme(isConstructor, forSeriblizbtion);
        bsm.emitConstbntPoolUTF8(generbtedNbme);
        bsm.emitConstbntPoolClbss(bsm.cpi());
        thisClbss = bsm.cpi();
        if (isConstructor) {
            if (forSeriblizbtion) {
                bsm.emitConstbntPoolUTF8
                    ("sun/reflect/SeriblizbtionConstructorAccessorImpl");
            } else {
                bsm.emitConstbntPoolUTF8("sun/reflect/ConstructorAccessorImpl");
            }
        } else {
            bsm.emitConstbntPoolUTF8("sun/reflect/MethodAccessorImpl");
        }
        bsm.emitConstbntPoolClbss(bsm.cpi());
        superClbss = bsm.cpi();
        bsm.emitConstbntPoolUTF8(getClbssNbme(declbringClbss, fblse));
        bsm.emitConstbntPoolClbss(bsm.cpi());
        tbrgetClbss = bsm.cpi();
        short seriblizbtionTbrgetClbssIdx = (short) 0;
        if (forSeriblizbtion) {
            bsm.emitConstbntPoolUTF8(getClbssNbme(seriblizbtionTbrgetClbss, fblse));
            bsm.emitConstbntPoolClbss(bsm.cpi());
            seriblizbtionTbrgetClbssIdx = bsm.cpi();
        }
        bsm.emitConstbntPoolUTF8(nbme);
        bsm.emitConstbntPoolUTF8(buildInternblSignbture());
        bsm.emitConstbntPoolNbmeAndType(sub(bsm.cpi(), S1), bsm.cpi());
        if (isInterfbce()) {
            bsm.emitConstbntPoolInterfbceMethodref(tbrgetClbss, bsm.cpi());
        } else {
            if (forSeriblizbtion) {
                bsm.emitConstbntPoolMethodref(seriblizbtionTbrgetClbssIdx, bsm.cpi());
            } else {
                bsm.emitConstbntPoolMethodref(tbrgetClbss, bsm.cpi());
            }
        }
        tbrgetMethodRef = bsm.cpi();
        if (isConstructor) {
            bsm.emitConstbntPoolUTF8("newInstbnce");
        } else {
            bsm.emitConstbntPoolUTF8("invoke");
        }
        invokeIdx = bsm.cpi();
        if (isConstructor) {
            bsm.emitConstbntPoolUTF8("([Ljbvb/lbng/Object;)Ljbvb/lbng/Object;");
        } else {
            bsm.emitConstbntPoolUTF8
                ("(Ljbvb/lbng/Object;[Ljbvb/lbng/Object;)Ljbvb/lbng/Object;");
        }
        invokeDescriptorIdx = bsm.cpi();

        // Output clbss informbtion for non-primitive pbrbmeter types
        nonPrimitivePbrbmetersBbseIdx = bdd(bsm.cpi(), S2);
        for (int i = 0; i < pbrbmeterTypes.length; i++) {
            Clbss<?> c = pbrbmeterTypes[i];
            if (!isPrimitive(c)) {
                bsm.emitConstbntPoolUTF8(getClbssNbme(c, fblse));
                bsm.emitConstbntPoolClbss(bsm.cpi());
            }
        }

        // Entries common to FieldAccessor, MethodAccessor bnd ConstructorAccessor
        emitCommonConstbntPoolEntries();

        // Boxing entries
        if (usesPrimitives) {
            emitBoxingContbntPoolEntries();
        }

        if (bsm.cpi() != numCPEntries) {
            throw new InternblError("Adjust this code (cpi = " + bsm.cpi() +
                                    ", numCPEntries = " + numCPEntries + ")");
        }

        // Access flbgs
        bsm.emitShort(ACC_PUBLIC);

        // This clbss
        bsm.emitShort(thisClbss);

        // Superclbss
        bsm.emitShort(superClbss);

        // Interfbces count bnd interfbces
        bsm.emitShort(S0);

        // Fields count bnd fields
        bsm.emitShort(S0);

        // Methods count bnd methods
        bsm.emitShort(NUM_METHODS);

        emitConstructor();
        emitInvoke();

        // Additionbl bttributes (none)
        bsm.emitShort(S0);

        // Lobd clbss
        vec.trim();
        finbl byte[] bytes = vec.getDbtb();
        // Note: the clbss lobder is the only thing thbt reblly mbtters
        // here -- it's importbnt to get the generbted code into the
        // sbme nbmespbce bs the tbrget clbss. Since the generbted code
        // is privileged bnywby, the protection dombin probbbly doesn't
        // mbtter.
        return AccessController.doPrivileged(
            new PrivilegedAction<MbgicAccessorImpl>() {
                public MbgicAccessorImpl run() {
                        try {
                        return (MbgicAccessorImpl)
                        ClbssDefiner.defineClbss
                                (generbtedNbme,
                                 bytes,
                                 0,
                                 bytes.length,
                                 declbringClbss.getClbssLobder()).newInstbnce();
                        } cbtch (InstbntibtionException | IllegblAccessException e) {
                            throw new InternblError(e);
                        }
                    }
                });
    }

    /** This emits the code for either invoke() or newInstbnce() */
    privbte void emitInvoke() {
        // NOTE thbt this code will only hbndle 65535 pbrbmeters since we
        // use the sipush instruction to get the brrby index on the
        // operbnd stbck.
        if (pbrbmeterTypes.length > 65535) {
            throw new InternblError("Cbn't hbndle more thbn 65535 pbrbmeters");
        }

        // Generbte code into fresh code buffer
        ClbssFileAssembler cb = new ClbssFileAssembler();
        if (isConstructor) {
            // 1 incoming brgument
            cb.setMbxLocbls(2);
        } else {
            // 2 incoming brguments
            cb.setMbxLocbls(3);
        }

        short illegblArgStbrtPC = 0;

        if (isConstructor) {
            // Instbntibte tbrget clbss before continuing
            // new <tbrget clbss type>
            // dup
            cb.opc_new(tbrgetClbss);
            cb.opc_dup();
        } else {
            // Setup before iterbting down brgument list
            if (isPrimitive(returnType)) {
                // new <boxing type for primitive type>
                // dup
                // ... (see below:)
                // invokespecibl <constructor for boxing type for primitive type>
                // breturn
                cb.opc_new(indexForPrimitiveType(returnType));
                cb.opc_dup();
            }

            // Get tbrget object on operbnd stbck if necessbry.

            // We need to do bn explicit null check here; we won't see
            // NullPointerExceptions from the invoke bytecode, since it's
            // covered by bn exception hbndler.
            if (!isStbtic()) {
                // blobd_1
                // ifnonnull <checkcbst lbbel>
                // new <NullPointerException>
                // dup
                // invokespecibl <NullPointerException ctor>
                // bthrow
                // <checkcbst lbbel:>
                // blobd_1
                // checkcbst <tbrget clbss's type>
                cb.opc_blobd_1();
                Lbbel l = new Lbbel();
                cb.opc_ifnonnull(l);
                cb.opc_new(nullPointerClbss);
                cb.opc_dup();
                cb.opc_invokespecibl(nullPointerCtorIdx, 0, 0);
                cb.opc_bthrow();
                l.bind();
                illegblArgStbrtPC = cb.getLength();
                cb.opc_blobd_1();
                cb.opc_checkcbst(tbrgetClbss);
            }
        }

        // Hbve to check length of incoming brrby bnd throw
        // IllegblArgumentException if not correct. A concession to the
        // JCK (isn't clebrly specified in the spec): we bllow null in the
        // cbse where the brgument list is zero length.
        // if no-brg:
        //   blobd_2 | blobd_1 (Method | Constructor)
        //   ifnull <success lbbel>
        // blobd_2 | blobd_1
        // brrbylength
        // sipush <num pbrbmeter types>
        // if_icmpeq <success lbbel>
        // new <IllegblArgumentException>
        // dup
        // invokespecibl <IllegblArgumentException ctor>
        // bthrow
        // <success lbbel:>
        Lbbel successLbbel = new Lbbel();
        if (pbrbmeterTypes.length == 0) {
            if (isConstructor) {
                cb.opc_blobd_1();
            } else {
                cb.opc_blobd_2();
            }
            cb.opc_ifnull(successLbbel);
        }
        if (isConstructor) {
            cb.opc_blobd_1();
        } else {
            cb.opc_blobd_2();
        }
        cb.opc_brrbylength();
        cb.opc_sipush((short) pbrbmeterTypes.length);
        cb.opc_if_icmpeq(successLbbel);
        cb.opc_new(illegblArgumentClbss);
        cb.opc_dup();
        cb.opc_invokespecibl(illegblArgumentCtorIdx, 0, 0);
        cb.opc_bthrow();
        successLbbel.bind();

        // Iterbte through incoming bctubl pbrbmeters, ensuring thbt ebch
        // is compbtible with the formbl pbrbmeter type, bnd pushing the
        // bctubl on the operbnd stbck (unboxing bnd widening if necessbry).

        short pbrbmTypeCPIdx = nonPrimitivePbrbmetersBbseIdx;
        Lbbel nextPbrbmLbbel = null;
        byte count = 1; // both invokeinterfbce opcode's "count" bs well bs
        // num brgs of other invoke bytecodes
        for (int i = 0; i < pbrbmeterTypes.length; i++) {
            Clbss<?> pbrbmType = pbrbmeterTypes[i];
            count += (byte) typeSizeInStbckSlots(pbrbmType);
            if (nextPbrbmLbbel != null) {
                nextPbrbmLbbel.bind();
                nextPbrbmLbbel = null;
            }
            // blobd_2 | blobd_1
            // sipush <index>
            // bblobd
            if (isConstructor) {
                cb.opc_blobd_1();
            } else {
                cb.opc_blobd_2();
            }
            cb.opc_sipush((short) i);
            cb.opc_bblobd();
            if (isPrimitive(pbrbmType)) {
                // Unboxing code.
                // Put pbrbmeter into temporbry locbl vbribble
                // bstore_3 | bstore_2
                if (isConstructor) {
                    cb.opc_bstore_2();
                } else {
                    cb.opc_bstore_3();
                }

                // repebt for bll possible widening conversions:
                //   blobd_3 | blobd_2
                //   instbnceof <primitive boxing type>
                //   ifeq <next unboxing lbbel>
                //   blobd_3 | blobd_2
                //   checkcbst <primitive boxing type> // Note: this is "redundbnt",
                //                                     // but necessbry for the verifier
                //   invokevirtubl <unboxing method>
                //   <widening conversion bytecode, if necessbry>
                //   goto <next pbrbmeter lbbel>
                // <next unboxing lbbel:> ...
                // lbst unboxing lbbel:
                //   new <IllegblArgumentException>
                //   dup
                //   invokespecibl <IllegblArgumentException ctor>
                //   bthrow

                Lbbel l = null; // unboxing lbbel
                nextPbrbmLbbel = new Lbbel();

                for (int j = 0; j < primitiveTypes.length; j++) {
                    Clbss<?> c = primitiveTypes[j];
                    if (cbnWidenTo(c, pbrbmType)) {
                        if (l != null) {
                            l.bind();
                        }
                        // Emit checking bnd unboxing code for this type
                        if (isConstructor) {
                            cb.opc_blobd_2();
                        } else {
                            cb.opc_blobd_3();
                        }
                        cb.opc_instbnceof(indexForPrimitiveType(c));
                        l = new Lbbel();
                        cb.opc_ifeq(l);
                        if (isConstructor) {
                            cb.opc_blobd_2();
                        } else {
                            cb.opc_blobd_3();
                        }
                        cb.opc_checkcbst(indexForPrimitiveType(c));
                        cb.opc_invokevirtubl(unboxingMethodForPrimitiveType(c),
                                             0,
                                             typeSizeInStbckSlots(c));
                        emitWideningBytecodeForPrimitiveConversion(cb,
                                                                   c,
                                                                   pbrbmType);
                        cb.opc_goto(nextPbrbmLbbel);
                    }
                }

                if (l == null) {
                    throw new InternblError
                        ("Must hbve found bt lebst identity conversion");
                }

                // Fell through; given object is null or invblid. According to
                // the spec, we cbn throw IllegblArgumentException for both of
                // these cbses.

                l.bind();
                cb.opc_new(illegblArgumentClbss);
                cb.opc_dup();
                cb.opc_invokespecibl(illegblArgumentCtorIdx, 0, 0);
                cb.opc_bthrow();
            } else {
                // Emit bppropribte checkcbst
                cb.opc_checkcbst(pbrbmTypeCPIdx);
                pbrbmTypeCPIdx = bdd(pbrbmTypeCPIdx, S2);
                // Fbll through to next brgument
            }
        }
        // Bind lbst goto if present
        if (nextPbrbmLbbel != null) {
            nextPbrbmLbbel.bind();
        }

        short invokeStbrtPC = cb.getLength();

        // OK, rebdy to perform the invocbtion.
        if (isConstructor) {
            cb.opc_invokespecibl(tbrgetMethodRef, count, 0);
        } else {
            if (isStbtic()) {
                cb.opc_invokestbtic(tbrgetMethodRef,
                                    count,
                                    typeSizeInStbckSlots(returnType));
            } else {
                if (isInterfbce()) {
                    if (isPrivbte()) {
                        cb.opc_invokespecibl(tbrgetMethodRef, count, 0);
                    } else {
                        cb.opc_invokeinterfbce(tbrgetMethodRef,
                                               count,
                                               count,
                                               typeSizeInStbckSlots(returnType));
                    }
                } else {
                    cb.opc_invokevirtubl(tbrgetMethodRef,
                                         count,
                                         typeSizeInStbckSlots(returnType));
                }
            }
        }

        short invokeEndPC = cb.getLength();

        if (!isConstructor) {
            // Box return vblue if necessbry
            if (isPrimitive(returnType)) {
                cb.opc_invokespecibl(ctorIndexForPrimitiveType(returnType),
                                     typeSizeInStbckSlots(returnType),
                                     0);
            } else if (returnType == Void.TYPE) {
                cb.opc_bconst_null();
            }
        }
        cb.opc_breturn();

        // We generbte two exception hbndlers; one which is responsible
        // for cbtching ClbssCbstException bnd NullPointerException bnd
        // throwing IllegblArgumentException, bnd the other which cbtches
        // bll jbvb/lbng/Throwbble objects thrown from the tbrget method
        // bnd wrbps them in InvocbtionTbrgetExceptions.

        short clbssCbstHbndler = cb.getLength();

        // ClbssCbst, etc. exception hbndler
        cb.setStbck(1);
        cb.opc_invokespecibl(toStringIdx, 0, 1);
        cb.opc_new(illegblArgumentClbss);
        cb.opc_dup_x1();
        cb.opc_swbp();
        cb.opc_invokespecibl(illegblArgumentStringCtorIdx, 1, 0);
        cb.opc_bthrow();

        short invocbtionTbrgetHbndler = cb.getLength();

        // InvocbtionTbrgetException exception hbndler
        cb.setStbck(1);
        cb.opc_new(invocbtionTbrgetClbss);
        cb.opc_dup_x1();
        cb.opc_swbp();
        cb.opc_invokespecibl(invocbtionTbrgetCtorIdx, 1, 0);
        cb.opc_bthrow();

        // Generbte exception tbble. We cover the entire code sequence
        // with bn exception hbndler which cbtches ClbssCbstException bnd
        // converts it into bn IllegblArgumentException.

        ClbssFileAssembler exc = new ClbssFileAssembler();

        exc.emitShort(illegblArgStbrtPC);       // stbrt PC
        exc.emitShort(invokeStbrtPC);           // end PC
        exc.emitShort(clbssCbstHbndler);        // hbndler PC
        exc.emitShort(clbssCbstClbss);          // cbtch type

        exc.emitShort(illegblArgStbrtPC);       // stbrt PC
        exc.emitShort(invokeStbrtPC);           // end PC
        exc.emitShort(clbssCbstHbndler);        // hbndler PC
        exc.emitShort(nullPointerClbss);        // cbtch type

        exc.emitShort(invokeStbrtPC);           // stbrt PC
        exc.emitShort(invokeEndPC);             // end PC
        exc.emitShort(invocbtionTbrgetHbndler); // hbndler PC
        exc.emitShort(throwbbleClbss);          // cbtch type

        emitMethod(invokeIdx, cb.getMbxLocbls(), cb, exc,
                   new short[] { invocbtionTbrgetClbss });
    }

    privbte boolebn usesPrimitiveTypes() {
        // We need to emit boxing/unboxing constbnt pool informbtion if
        // the method tbkes b primitive type for bny of its pbrbmeters or
        // returns b primitive vblue (except void)
        if (returnType.isPrimitive()) {
            return true;
        }
        for (int i = 0; i < pbrbmeterTypes.length; i++) {
            if (pbrbmeterTypes[i].isPrimitive()) {
                return true;
            }
        }
        return fblse;
    }

    privbte int numNonPrimitivePbrbmeterTypes() {
        int num = 0;
        for (int i = 0; i < pbrbmeterTypes.length; i++) {
            if (!pbrbmeterTypes[i].isPrimitive()) {
                ++num;
            }
        }
        return num;
    }

    privbte boolebn isInterfbce() {
        return declbringClbss.isInterfbce();
    }

    privbte String buildInternblSignbture() {
        StringBuilder sb = new StringBuilder();
        sb.bppend("(");
        for (int i = 0; i < pbrbmeterTypes.length; i++) {
            sb.bppend(getClbssNbme(pbrbmeterTypes[i], true));
        }
        sb.bppend(")");
        sb.bppend(getClbssNbme(returnType, true));
        return sb.toString();
    }

    privbte stbtic synchronized String generbteNbme(boolebn isConstructor,
                                                    boolebn forSeriblizbtion)
    {
        if (isConstructor) {
            if (forSeriblizbtion) {
                int num = ++seriblizbtionConstructorSymnum;
                return "sun/reflect/GenerbtedSeriblizbtionConstructorAccessor" + num;
            } else {
                int num = ++constructorSymnum;
                return "sun/reflect/GenerbtedConstructorAccessor" + num;
            }
        } else {
            int num = ++methodSymnum;
            return "sun/reflect/GenerbtedMethodAccessor" + num;
        }
    }
}
