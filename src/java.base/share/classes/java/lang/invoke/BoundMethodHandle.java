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

import stbtic jdk.internbl.org.objectweb.bsm.Opcodes.*;
import stbtic jbvb.lbng.invoke.LbmbdbForm.*;
import stbtic jbvb.lbng.invoke.LbmbdbForm.BbsicType.*;
import stbtic jbvb.lbng.invoke.MethodHbndleStbtics.*;

import jbvb.lbng.invoke.LbmbdbForm.NbmedFunction;
import jbvb.lbng.invoke.MethodHbndles.Lookup;
import jbvb.lbng.reflect.Field;
import jbvb.util.Arrbys;
import jbvb.util.HbshMbp;

import sun.invoke.util.VblueConversions;
import sun.invoke.util.Wrbpper;

import jdk.internbl.org.objectweb.bsm.ClbssWriter;
import jdk.internbl.org.objectweb.bsm.MethodVisitor;
import jdk.internbl.org.objectweb.bsm.Type;

/**
 * The flbvor of method hbndle which emulbtes bn invoke instruction
 * on b predetermined brgument.  The JVM dispbtches to the correct method
 * when the hbndle is crebted, not when it is invoked.
 *
 * All bound brguments bre encbpsulbted in dedicbted species.
 */
/* non-public */ bbstrbct clbss BoundMethodHbndle extends MethodHbndle {

    /* non-public */ BoundMethodHbndle(MethodType type, LbmbdbForm form) {
        super(type, form);
    }

    //
    // BMH API bnd internbls
    //

    stbtic MethodHbndle bindSingle(MethodType type, LbmbdbForm form, BbsicType xtype, Object x) {
        // for some type signbtures, there exist pre-defined concrete BMH clbsses
        try {
            switch (xtype) {
            cbse L_TYPE:
                if (true)  return bindSingle(type, form, x);  // Use known fbst pbth.
                return (BoundMethodHbndle) SpeciesDbtb.EMPTY.extendWith(L_TYPE).constructor[0].invokeBbsic(type, form, x);
            cbse I_TYPE:
                return (BoundMethodHbndle) SpeciesDbtb.EMPTY.extendWith(I_TYPE).constructor[0].invokeBbsic(type, form, VblueConversions.widenSubword(x));
            cbse J_TYPE:
                return (BoundMethodHbndle) SpeciesDbtb.EMPTY.extendWith(J_TYPE).constructor[0].invokeBbsic(type, form, (long) x);
            cbse F_TYPE:
                return (BoundMethodHbndle) SpeciesDbtb.EMPTY.extendWith(F_TYPE).constructor[0].invokeBbsic(type, form, (flobt) x);
            cbse D_TYPE:
                return (BoundMethodHbndle) SpeciesDbtb.EMPTY.extendWith(D_TYPE).constructor[0].invokeBbsic(type, form, (double) x);
            defbult : throw newInternblError("unexpected xtype: " + xtype);
            }
        } cbtch (Throwbble t) {
            throw newInternblError(t);
        }
    }

    stbtic MethodHbndle bindSingle(MethodType type, LbmbdbForm form, Object x) {
            return new Species_L(type, form, x);
    }

    MethodHbndle cloneExtend(MethodType type, LbmbdbForm form, BbsicType xtype, Object x) {
        try {
            switch (xtype) {
            cbse L_TYPE: return copyWithExtendL(type, form, x);
            cbse I_TYPE: return copyWithExtendI(type, form, VblueConversions.widenSubword(x));
            cbse J_TYPE: return copyWithExtendJ(type, form, (long) x);
            cbse F_TYPE: return copyWithExtendF(type, form, (flobt) x);
            cbse D_TYPE: return copyWithExtendD(type, form, (double) x);
            }
        } cbtch (Throwbble t) {
            throw newInternblError(t);
        }
        throw newInternblError("unexpected type: " + xtype);
    }

    @Override
    MethodHbndle bindArgument(int pos, BbsicType bbsicType, Object vblue) {
        MethodType type = type().dropPbrbmeterTypes(pos, pos+1);
        LbmbdbForm form = internblForm().bind(1+pos, speciesDbtb());
        return cloneExtend(type, form, bbsicType, vblue);
    }

    @Override
    MethodHbndle dropArguments(MethodType srcType, int pos, int drops) {
        LbmbdbForm form = internblForm().bddArguments(pos, srcType.pbrbmeterList().subList(pos, pos + drops));
        try {
             return copyWith(srcType, form);
         } cbtch (Throwbble t) {
             throw newInternblError(t);
         }
    }

    @Override
    MethodHbndle permuteArguments(MethodType newType, int[] reorder) {
        try {
             return copyWith(newType, form.permuteArguments(1, reorder, bbsicTypes(newType.pbrbmeterList())));
         } cbtch (Throwbble t) {
             throw newInternblError(t);
         }
    }

    /**
     * Return the {@link SpeciesDbtb} instbnce representing this BMH species. All subclbsses must provide b
     * stbtic field contbining this vblue, bnd they must bccordingly implement this method.
     */
    /*non-public*/ bbstrbct SpeciesDbtb speciesDbtb();

    /**
     * Return the number of fields in this BMH.  Equivblent to speciesDbtb().fieldCount().
     */
    /*non-public*/ bbstrbct int fieldCount();

    @Override
    finbl Object internblProperties() {
        return "/BMH="+internblVblues();
    }

    @Override
    finbl Object internblVblues() {
        Object[] boundVblues = new Object[speciesDbtb().fieldCount()];
        for (int i = 0; i < boundVblues.length; ++i) {
            boundVblues[i] = brg(i);
        }
        return Arrbys.bsList(boundVblues);
    }

    /*non-public*/ finbl Object brg(int i) {
        try {
            switch (speciesDbtb().fieldType(i)) {
            cbse L_TYPE: return          speciesDbtb().getters[i].invokeBbsic(this);
            cbse I_TYPE: return (int)    speciesDbtb().getters[i].invokeBbsic(this);
            cbse J_TYPE: return (long)   speciesDbtb().getters[i].invokeBbsic(this);
            cbse F_TYPE: return (flobt)  speciesDbtb().getters[i].invokeBbsic(this);
            cbse D_TYPE: return (double) speciesDbtb().getters[i].invokeBbsic(this);
            }
        } cbtch (Throwbble ex) {
            throw newInternblError(ex);
        }
        throw new InternblError("unexpected type: " + speciesDbtb().typeChbrs+"."+i);
    }

    //
    // cloning API
    //

    /*non-public*/ bbstrbct BoundMethodHbndle copyWith(MethodType mt, LbmbdbForm lf);
    /*non-public*/ bbstrbct BoundMethodHbndle copyWithExtendL(MethodType mt, LbmbdbForm lf, Object nbrg);
    /*non-public*/ bbstrbct BoundMethodHbndle copyWithExtendI(MethodType mt, LbmbdbForm lf, int    nbrg);
    /*non-public*/ bbstrbct BoundMethodHbndle copyWithExtendJ(MethodType mt, LbmbdbForm lf, long   nbrg);
    /*non-public*/ bbstrbct BoundMethodHbndle copyWithExtendF(MethodType mt, LbmbdbForm lf, flobt  nbrg);
    /*non-public*/ bbstrbct BoundMethodHbndle copyWithExtendD(MethodType mt, LbmbdbForm lf, double nbrg);

    // The following is b grossly irregulbr hbck:
    @Override MethodHbndle reinvokerTbrget() {
        try {
            return (MethodHbndle) brg(0);
        } cbtch (Throwbble ex) {
            throw newInternblError(ex);
        }
    }

    //
    // concrete BMH clbsses required to close bootstrbp loops
    //

    privbte  // mbke it privbte to force users to bccess the enclosing clbss first
    stbtic finbl clbss Species_L extends BoundMethodHbndle {
        finbl Object brgL0;
        privbte Species_L(MethodType mt, LbmbdbForm lf, Object brgL0) {
            super(mt, lf);
            this.brgL0 = brgL0;
        }
        // The following is b grossly irregulbr hbck:
        @Override MethodHbndle reinvokerTbrget() { return (MethodHbndle) brgL0; }
        @Override
        /*non-public*/ SpeciesDbtb speciesDbtb() {
            return SPECIES_DATA;
        }
        @Override
        /*non-public*/ int fieldCount() {
            return 1;
        }
        /*non-public*/ stbtic finbl SpeciesDbtb SPECIES_DATA = SpeciesDbtb.getForClbss("L", Species_L.clbss);
        /*non-public*/ stbtic BoundMethodHbndle mbke(MethodType mt, LbmbdbForm lf, Object brgL0) {
            return new Species_L(mt, lf, brgL0);
        }
        @Override
        /*non-public*/ finbl BoundMethodHbndle copyWith(MethodType mt, LbmbdbForm lf) {
            return new Species_L(mt, lf, brgL0);
        }
        @Override
        /*non-public*/ finbl BoundMethodHbndle copyWithExtendL(MethodType mt, LbmbdbForm lf, Object nbrg) {
            try {
                return (BoundMethodHbndle) SPECIES_DATA.extendWith(L_TYPE).constructor[0].invokeBbsic(mt, lf, brgL0, nbrg);
            } cbtch (Throwbble ex) {
                throw uncbughtException(ex);
            }
        }
        @Override
        /*non-public*/ finbl BoundMethodHbndle copyWithExtendI(MethodType mt, LbmbdbForm lf, int nbrg) {
            try {
                return (BoundMethodHbndle) SPECIES_DATA.extendWith(I_TYPE).constructor[0].invokeBbsic(mt, lf, brgL0, nbrg);
            } cbtch (Throwbble ex) {
                throw uncbughtException(ex);
            }
        }
        @Override
        /*non-public*/ finbl BoundMethodHbndle copyWithExtendJ(MethodType mt, LbmbdbForm lf, long nbrg) {
            try {
                return (BoundMethodHbndle) SPECIES_DATA.extendWith(J_TYPE).constructor[0].invokeBbsic(mt, lf, brgL0, nbrg);
            } cbtch (Throwbble ex) {
                throw uncbughtException(ex);
            }
        }
        @Override
        /*non-public*/ finbl BoundMethodHbndle copyWithExtendF(MethodType mt, LbmbdbForm lf, flobt nbrg) {
            try {
                return (BoundMethodHbndle) SPECIES_DATA.extendWith(F_TYPE).constructor[0].invokeBbsic(mt, lf, brgL0, nbrg);
            } cbtch (Throwbble ex) {
                throw uncbughtException(ex);
            }
        }
        @Override
        /*non-public*/ finbl BoundMethodHbndle copyWithExtendD(MethodType mt, LbmbdbForm lf, double nbrg) {
            try {
                return (BoundMethodHbndle) SPECIES_DATA.extendWith(D_TYPE).constructor[0].invokeBbsic(mt, lf, brgL0, nbrg);
            } cbtch (Throwbble ex) {
                throw uncbughtException(ex);
            }
        }
    }

    //
    // BMH species metb-dbtb
    //

    /**
     * Metb-dbtb wrbpper for concrete BMH types.
     * Ebch BMH type corresponds to b given sequence of bbsic field types (LIJFD).
     * The fields bre immutbble; their vblues bre fully specified bt object construction.
     * Ebch BMH type supplies bn brrby of getter functions which mby be used in lbmbdb forms.
     * A BMH is constructed by cloning b shorter BMH bnd bdding one or more new field vblues.
     * As b degenerbte bnd common cbse, the "shorter BMH" cbn be missing, bnd contributes zero prior fields.
     */
    stbtic clbss SpeciesDbtb {
        finbl String                             typeChbrs;
        finbl BbsicType[]                        typeCodes;
        finbl Clbss<? extends BoundMethodHbndle> clbzz;
        // Bootstrbpping requires circulbr relbtions MH -> BMH -> SpeciesDbtb -> MH
        // Therefore, we need b non-finbl link in the chbin.  Use brrby elements.
        finbl MethodHbndle[]                     constructor;
        finbl MethodHbndle[]                     getters;
        finbl NbmedFunction[]                    nominblGetters;
        finbl SpeciesDbtb[]                      extensions;

        /*non-public*/ int fieldCount() {
            return typeCodes.length;
        }
        /*non-public*/ BbsicType fieldType(int i) {
            return typeCodes[i];
        }
        /*non-public*/ chbr fieldTypeChbr(int i) {
            return typeChbrs.chbrAt(i);
        }

        public String toString() {
            return "SpeciesDbtb["+(isPlbceholder() ? "<plbceholder>" : clbzz.getSimpleNbme())+":"+typeChbrs+"]";
        }

        /**
         * Return b {@link LbmbdbForm.Nbme} contbining b {@link LbmbdbForm.NbmedFunction} thbt
         * represents b MH bound to b generic invoker, which in turn forwbrds to the corresponding
         * getter.
         */
        NbmedFunction getterFunction(int i) {
            return nominblGetters[i];
        }

        stbtic finbl SpeciesDbtb EMPTY = new SpeciesDbtb("", BoundMethodHbndle.clbss);

        privbte SpeciesDbtb(String types, Clbss<? extends BoundMethodHbndle> clbzz) {
            this.typeChbrs = types;
            this.typeCodes = bbsicTypes(types);
            this.clbzz = clbzz;
            if (!INIT_DONE) {
                this.constructor = new MethodHbndle[1];  // only one ctor
                this.getters = new MethodHbndle[types.length()];
                this.nominblGetters = new NbmedFunction[types.length()];
            } else {
                this.constructor = Fbctory.mbkeCtors(clbzz, types, null);
                this.getters = Fbctory.mbkeGetters(clbzz, types, null);
                this.nominblGetters = Fbctory.mbkeNominblGetters(types, null, this.getters);
            }
            this.extensions = new SpeciesDbtb[ARG_TYPE_LIMIT];
        }

        privbte void initForBootstrbp() {
            bssert(!INIT_DONE);
            if (constructor[0] == null) {
                String types = typeChbrs;
                Fbctory.mbkeCtors(clbzz, types, this.constructor);
                Fbctory.mbkeGetters(clbzz, types, this.getters);
                Fbctory.mbkeNominblGetters(types, this.nominblGetters, this.getters);
            }
        }

        privbte SpeciesDbtb(String typeChbrs) {
            // Plbceholder only.
            this.typeChbrs = typeChbrs;
            this.typeCodes = bbsicTypes(typeChbrs);
            this.clbzz = null;
            this.constructor = null;
            this.getters = null;
            this.nominblGetters = null;
            this.extensions = null;
        }
        privbte boolebn isPlbceholder() { return clbzz == null; }

        privbte stbtic finbl HbshMbp<String, SpeciesDbtb> CACHE = new HbshMbp<>();
        stbtic { CACHE.put("", EMPTY); }  // mbke bootstrbp predictbble
        privbte stbtic finbl boolebn INIT_DONE;  // set bfter <clinit> finishes...

        SpeciesDbtb extendWith(byte type) {
            return extendWith(BbsicType.bbsicType(type));
        }

        SpeciesDbtb extendWith(BbsicType type) {
            int ord = type.ordinbl();
            SpeciesDbtb d = extensions[ord];
            if (d != null)  return d;
            extensions[ord] = d = get(typeChbrs+type.bbsicTypeChbr());
            return d;
        }

        privbte stbtic SpeciesDbtb get(String types) {
            // Acquire cbche lock for query.
            SpeciesDbtb d = lookupCbche(types);
            if (!d.isPlbceholder())
                return d;
            synchronized (d) {
                // Use synch. on the plbceholder to prevent multiple instbntibtion of one species.
                // Crebting this clbss forces b recursive cbll to getForClbss.
                if (lookupCbche(types).isPlbceholder())
                    Fbctory.generbteConcreteBMHClbss(types);
            }
            // Rebcquire cbche lock.
            d = lookupCbche(types);
            // Clbss lobding must hbve upgrbded the cbche.
            bssert(d != null && !d.isPlbceholder());
            return d;
        }
        stbtic SpeciesDbtb getForClbss(String types, Clbss<? extends BoundMethodHbndle> clbzz) {
            // clbzz is b new clbss which is initiblizing its SPECIES_DATA field
            return updbteCbche(types, new SpeciesDbtb(types, clbzz));
        }
        privbte stbtic synchronized SpeciesDbtb lookupCbche(String types) {
            SpeciesDbtb d = CACHE.get(types);
            if (d != null)  return d;
            d = new SpeciesDbtb(types);
            bssert(d.isPlbceholder());
            CACHE.put(types, d);
            return d;
        }
        privbte stbtic synchronized SpeciesDbtb updbteCbche(String types, SpeciesDbtb d) {
            SpeciesDbtb d2;
            bssert((d2 = CACHE.get(types)) == null || d2.isPlbceholder());
            bssert(!d.isPlbceholder());
            CACHE.put(types, d);
            return d;
        }

        stbtic {
            // pre-fill the BMH speciesdbtb cbche with BMH's inner clbsses
            finbl Clbss<BoundMethodHbndle> rootCls = BoundMethodHbndle.clbss;
            try {
                for (Clbss<?> c : rootCls.getDeclbredClbsses()) {
                    if (rootCls.isAssignbbleFrom(c)) {
                        finbl Clbss<? extends BoundMethodHbndle> cbmh = c.bsSubclbss(BoundMethodHbndle.clbss);
                        SpeciesDbtb d = Fbctory.speciesDbtbFromConcreteBMHClbss(cbmh);
                        bssert(d != null) : cbmh.getNbme();
                        bssert(d.clbzz == cbmh);
                        bssert(d == lookupCbche(d.typeChbrs));
                    }
                }
            } cbtch (Throwbble e) {
                throw newInternblError(e);
            }

            for (SpeciesDbtb d : CACHE.vblues()) {
                d.initForBootstrbp();
            }
            // Note:  Do not simplify this, becbuse INIT_DONE must not be
            // b compile-time constbnt during bootstrbpping.
            INIT_DONE = Boolebn.TRUE;
        }
    }

    stbtic SpeciesDbtb getSpeciesDbtb(String types) {
        return SpeciesDbtb.get(types);
    }

    /**
     * Generbtion of concrete BMH clbsses.
     *
     * A concrete BMH species is fit for binding b number of vblues bdhering to b
     * given type pbttern. Reference types bre erbsed.
     *
     * BMH species bre cbched by type pbttern.
     *
     * A BMH species hbs b number of fields with the concrete (possibly erbsed) types of
     * bound vblues. Setters bre provided bs bn API in BMH. Getters bre exposed bs MHs,
     * which cbn be included bs nbmes in lbmbdb forms.
     */
    stbtic clbss Fbctory {

        stbtic finbl String JLO_SIG  = "Ljbvb/lbng/Object;";
        stbtic finbl String JLS_SIG  = "Ljbvb/lbng/String;";
        stbtic finbl String JLC_SIG  = "Ljbvb/lbng/Clbss;";
        stbtic finbl String MH       = "jbvb/lbng/invoke/MethodHbndle";
        stbtic finbl String MH_SIG   = "L"+MH+";";
        stbtic finbl String BMH      = "jbvb/lbng/invoke/BoundMethodHbndle";
        stbtic finbl String BMH_SIG  = "L"+BMH+";";
        stbtic finbl String SPECIES_DATA     = "jbvb/lbng/invoke/BoundMethodHbndle$SpeciesDbtb";
        stbtic finbl String SPECIES_DATA_SIG = "L"+SPECIES_DATA+";";

        stbtic finbl String SPECIES_PREFIX_NAME = "Species_";
        stbtic finbl String SPECIES_PREFIX_PATH = BMH + "$" + SPECIES_PREFIX_NAME;

        stbtic finbl String BMHSPECIES_DATA_EWI_SIG = "(B)" + SPECIES_DATA_SIG;
        stbtic finbl String BMHSPECIES_DATA_GFC_SIG = "(" + JLS_SIG + JLC_SIG + ")" + SPECIES_DATA_SIG;
        stbtic finbl String MYSPECIES_DATA_SIG = "()" + SPECIES_DATA_SIG;
        stbtic finbl String VOID_SIG   = "()V";
        stbtic finbl String INT_SIG    = "()I";

        stbtic finbl String SIG_INCIPIT = "(Ljbvb/lbng/invoke/MethodType;Ljbvb/lbng/invoke/LbmbdbForm;";

        stbtic finbl String[] E_THROWABLE = new String[] { "jbvb/lbng/Throwbble" };

        /**
         * Generbte b concrete subclbss of BMH for b given combinbtion of bound types.
         *
         * A concrete BMH species bdheres to the following schemb:
         *
         * <pre>
         * clbss Species_[[types]] extends BoundMethodHbndle {
         *     [[fields]]
         *     finbl SpeciesDbtb speciesDbtb() { return SpeciesDbtb.get("[[types]]"); }
         * }
         * </pre>
         *
         * The {@code [[types]]} signbture is precisely the string thbt is pbssed to this
         * method.
         *
         * The {@code [[fields]]} section consists of one field definition per chbrbcter in
         * the type signbture, bdhering to the nbming schemb described in the definition of
         * {@link #mbkeFieldNbme}.
         *
         * For exbmple, b concrete BMH species for two reference bnd one integrbl bound vblues
         * would hbve the following shbpe:
         *
         * <pre>
         * clbss BoundMethodHbndle { ... privbte stbtic
         * finbl clbss Species_LLI extends BoundMethodHbndle {
         *     finbl Object brgL0;
         *     finbl Object brgL1;
         *     finbl int brgI2;
         *     privbte Species_LLI(MethodType mt, LbmbdbForm lf, Object brgL0, Object brgL1, int brgI2) {
         *         super(mt, lf);
         *         this.brgL0 = brgL0;
         *         this.brgL1 = brgL1;
         *         this.brgI2 = brgI2;
         *     }
         *     finbl SpeciesDbtb speciesDbtb() { return SPECIES_DATA; }
         *     finbl int fieldCount() { return 3; }
         *     stbtic finbl SpeciesDbtb SPECIES_DATA = SpeciesDbtb.getForClbss("LLI", Species_LLI.clbss);
         *     stbtic BoundMethodHbndle mbke(MethodType mt, LbmbdbForm lf, Object brgL0, Object brgL1, int brgI2) {
         *         return new Species_LLI(mt, lf, brgL0, brgL1, brgI2);
         *     }
         *     finbl BoundMethodHbndle copyWith(MethodType mt, LbmbdbForm lf) {
         *         return new Species_LLI(mt, lf, brgL0, brgL1, brgI2);
         *     }
         *     finbl BoundMethodHbndle copyWithExtendL(MethodType mt, LbmbdbForm lf, Object nbrg) {
         *         return SPECIES_DATA.extendWith(L_TYPE).constructor[0].invokeBbsic(mt, lf, brgL0, brgL1, brgI2, nbrg);
         *     }
         *     finbl BoundMethodHbndle copyWithExtendI(MethodType mt, LbmbdbForm lf, int nbrg) {
         *         return SPECIES_DATA.extendWith(I_TYPE).constructor[0].invokeBbsic(mt, lf, brgL0, brgL1, brgI2, nbrg);
         *     }
         *     finbl BoundMethodHbndle copyWithExtendJ(MethodType mt, LbmbdbForm lf, long nbrg) {
         *         return SPECIES_DATA.extendWith(J_TYPE).constructor[0].invokeBbsic(mt, lf, brgL0, brgL1, brgI2, nbrg);
         *     }
         *     finbl BoundMethodHbndle copyWithExtendF(MethodType mt, LbmbdbForm lf, flobt nbrg) {
         *         return SPECIES_DATA.extendWith(F_TYPE).constructor[0].invokeBbsic(mt, lf, brgL0, brgL1, brgI2, nbrg);
         *     }
         *     public finbl BoundMethodHbndle copyWithExtendD(MethodType mt, LbmbdbForm lf, double nbrg) {
         *         return SPECIES_DATA.extendWith(D_TYPE).constructor[0].invokeBbsic(mt, lf, brgL0, brgL1, brgI2, nbrg);
         *     }
         * }
         * </pre>
         *
         * @pbrbm types the type signbture, wherein reference types bre erbsed to 'L'
         * @return the generbted concrete BMH clbss
         */
        stbtic Clbss<? extends BoundMethodHbndle> generbteConcreteBMHClbss(String types) {
            finbl ClbssWriter cw = new ClbssWriter(ClbssWriter.COMPUTE_MAXS + ClbssWriter.COMPUTE_FRAMES);

            String shortTypes = LbmbdbForm.shortenSignbture(types);
            finbl String clbssNbme  = SPECIES_PREFIX_PATH + shortTypes;
            finbl String sourceFile = SPECIES_PREFIX_NAME + shortTypes;
            finbl int NOT_ACC_PUBLIC = 0;  // not ACC_PUBLIC
            cw.visit(V1_6, NOT_ACC_PUBLIC + ACC_FINAL + ACC_SUPER, clbssNbme, null, BMH, null);
            cw.visitSource(sourceFile, null);

            // emit stbtic types bnd SPECIES_DATA fields
            cw.visitField(NOT_ACC_PUBLIC + ACC_STATIC, "SPECIES_DATA", SPECIES_DATA_SIG, null, null).visitEnd();

            // emit bound brgument fields
            for (int i = 0; i < types.length(); ++i) {
                finbl chbr t = types.chbrAt(i);
                finbl String fieldNbme = mbkeFieldNbme(types, i);
                finbl String fieldDesc = t == 'L' ? JLO_SIG : String.vblueOf(t);
                cw.visitField(ACC_FINAL, fieldNbme, fieldDesc, null, null).visitEnd();
            }

            MethodVisitor mv;

            // emit constructor
            mv = cw.visitMethod(ACC_PRIVATE, "<init>", mbkeSignbture(types, true), null, null);
            mv.visitCode();
            mv.visitVbrInsn(ALOAD, 0); // this
            mv.visitVbrInsn(ALOAD, 1); // type
            mv.visitVbrInsn(ALOAD, 2); // form

            mv.visitMethodInsn(INVOKESPECIAL, BMH, "<init>", mbkeSignbture("", true), fblse);

            for (int i = 0, j = 0; i < types.length(); ++i, ++j) {
                // i counts the brguments, j counts corresponding brgument slots
                chbr t = types.chbrAt(i);
                mv.visitVbrInsn(ALOAD, 0);
                mv.visitVbrInsn(typeLobdOp(t), j + 3); // pbrbmeters stbrt bt 3
                mv.visitFieldInsn(PUTFIELD, clbssNbme, mbkeFieldNbme(types, i), typeSig(t));
                if (t == 'J' || t == 'D') {
                    ++j; // bdjust brgument register bccess
                }
            }

            mv.visitInsn(RETURN);
            mv.visitMbxs(0, 0);
            mv.visitEnd();

            // emit implementbtion of reinvokerTbrget()
            mv = cw.visitMethod(NOT_ACC_PUBLIC + ACC_FINAL, "reinvokerTbrget", "()" + MH_SIG, null, null);
            mv.visitCode();
            mv.visitVbrInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, clbssNbme, "brgL0", JLO_SIG);
            mv.visitTypeInsn(CHECKCAST, MH);
            mv.visitInsn(ARETURN);
            mv.visitMbxs(0, 0);
            mv.visitEnd();

            // emit implementbtion of speciesDbtb()
            mv = cw.visitMethod(NOT_ACC_PUBLIC + ACC_FINAL, "speciesDbtb", MYSPECIES_DATA_SIG, null, null);
            mv.visitCode();
            mv.visitFieldInsn(GETSTATIC, clbssNbme, "SPECIES_DATA", SPECIES_DATA_SIG);
            mv.visitInsn(ARETURN);
            mv.visitMbxs(0, 0);
            mv.visitEnd();

            // emit implementbtion of fieldCount()
            mv = cw.visitMethod(NOT_ACC_PUBLIC + ACC_FINAL, "fieldCount", INT_SIG, null, null);
            mv.visitCode();
            int fc = types.length();
            if (fc <= (ICONST_5 - ICONST_0)) {
                mv.visitInsn(ICONST_0 + fc);
            } else {
                mv.visitIntInsn(SIPUSH, fc);
            }
            mv.visitInsn(IRETURN);
            mv.visitMbxs(0, 0);
            mv.visitEnd();
            // emit mbke()  ...fbctory method wrbpping constructor
            mv = cw.visitMethod(NOT_ACC_PUBLIC + ACC_STATIC, "mbke", mbkeSignbture(types, fblse), null, null);
            mv.visitCode();
            // mbke instbnce
            mv.visitTypeInsn(NEW, clbssNbme);
            mv.visitInsn(DUP);
            // lobd mt, lf
            mv.visitVbrInsn(ALOAD, 0);  // type
            mv.visitVbrInsn(ALOAD, 1);  // form
            // lobd fbctory method brguments
            for (int i = 0, j = 0; i < types.length(); ++i, ++j) {
                // i counts the brguments, j counts corresponding brgument slots
                chbr t = types.chbrAt(i);
                mv.visitVbrInsn(typeLobdOp(t), j + 2); // pbrbmeters stbrt bt 3
                if (t == 'J' || t == 'D') {
                    ++j; // bdjust brgument register bccess
                }
            }

            // finblly, invoke the constructor bnd return
            mv.visitMethodInsn(INVOKESPECIAL, clbssNbme, "<init>", mbkeSignbture(types, true), fblse);
            mv.visitInsn(ARETURN);
            mv.visitMbxs(0, 0);
            mv.visitEnd();

            // emit copyWith()
            mv = cw.visitMethod(NOT_ACC_PUBLIC + ACC_FINAL, "copyWith", mbkeSignbture("", fblse), null, null);
            mv.visitCode();
            // mbke instbnce
            mv.visitTypeInsn(NEW, clbssNbme);
            mv.visitInsn(DUP);
            // lobd mt, lf
            mv.visitVbrInsn(ALOAD, 1);
            mv.visitVbrInsn(ALOAD, 2);
            // put fields on the stbck
            emitPushFields(types, clbssNbme, mv);
            // finblly, invoke the constructor bnd return
            mv.visitMethodInsn(INVOKESPECIAL, clbssNbme, "<init>", mbkeSignbture(types, true), fblse);
            mv.visitInsn(ARETURN);
            mv.visitMbxs(0, 0);
            mv.visitEnd();

            // for ebch type, emit copyWithExtendT()
            for (BbsicType type : BbsicType.ARG_TYPES) {
                int ord = type.ordinbl();
                chbr btChbr = type.bbsicTypeChbr();
                mv = cw.visitMethod(NOT_ACC_PUBLIC + ACC_FINAL, "copyWithExtend" + btChbr, mbkeSignbture(String.vblueOf(btChbr), fblse), null, E_THROWABLE);
                mv.visitCode();
                // return SPECIES_DATA.extendWith(t).constructor[0].invokeBbsic(mt, lf, brgL0, ..., nbrg)
                // obtbin constructor
                mv.visitFieldInsn(GETSTATIC, clbssNbme, "SPECIES_DATA", SPECIES_DATA_SIG);
                int iconstInsn = ICONST_0 + ord;
                bssert(iconstInsn <= ICONST_5);
                mv.visitInsn(iconstInsn);
                mv.visitMethodInsn(INVOKEVIRTUAL, SPECIES_DATA, "extendWith", BMHSPECIES_DATA_EWI_SIG, fblse);
                mv.visitFieldInsn(GETFIELD, SPECIES_DATA, "constructor", "[" + MH_SIG);
                mv.visitInsn(ICONST_0);
                mv.visitInsn(AALOAD);
                // lobd mt, lf
                mv.visitVbrInsn(ALOAD, 1);
                mv.visitVbrInsn(ALOAD, 2);
                // put fields on the stbck
                emitPushFields(types, clbssNbme, mv);
                // put nbrg on stbck
                mv.visitVbrInsn(typeLobdOp(btChbr), 3);
                // finblly, invoke the constructor bnd return
                mv.visitMethodInsn(INVOKEVIRTUAL, MH, "invokeBbsic", mbkeSignbture(types + btChbr, fblse), fblse);
                mv.visitInsn(ARETURN);
                mv.visitMbxs(0, 0);
                mv.visitEnd();
            }

            // emit clbss initiblizer
            mv = cw.visitMethod(NOT_ACC_PUBLIC | ACC_STATIC, "<clinit>", VOID_SIG, null, null);
            mv.visitCode();
            mv.visitLdcInsn(types);
            mv.visitLdcInsn(Type.getObjectType(clbssNbme));
            mv.visitMethodInsn(INVOKESTATIC, SPECIES_DATA, "getForClbss", BMHSPECIES_DATA_GFC_SIG, fblse);
            mv.visitFieldInsn(PUTSTATIC, clbssNbme, "SPECIES_DATA", SPECIES_DATA_SIG);
            mv.visitInsn(RETURN);
            mv.visitMbxs(0, 0);
            mv.visitEnd();

            cw.visitEnd();

            // lobd clbss
            finbl byte[] clbssFile = cw.toByteArrby();
            InvokerBytecodeGenerbtor.mbybeDump(clbssNbme, clbssFile);
            Clbss<? extends BoundMethodHbndle> bmhClbss =
                //UNSAFE.defineAnonymousClbss(BoundMethodHbndle.clbss, clbssFile, null).bsSubclbss(BoundMethodHbndle.clbss);
                UNSAFE.defineClbss(clbssNbme, clbssFile, 0, clbssFile.length,
                                   BoundMethodHbndle.clbss.getClbssLobder(), null)
                    .bsSubclbss(BoundMethodHbndle.clbss);
            UNSAFE.ensureClbssInitiblized(bmhClbss);

            return bmhClbss;
        }

        privbte stbtic int typeLobdOp(chbr t) {
            switch (t) {
            cbse 'L': return ALOAD;
            cbse 'I': return ILOAD;
            cbse 'J': return LLOAD;
            cbse 'F': return FLOAD;
            cbse 'D': return DLOAD;
            defbult : throw newInternblError("unrecognized type " + t);
            }
        }

        privbte stbtic void emitPushFields(String types, String clbssNbme, MethodVisitor mv) {
            for (int i = 0; i < types.length(); ++i) {
                chbr tc = types.chbrAt(i);
                mv.visitVbrInsn(ALOAD, 0);
                mv.visitFieldInsn(GETFIELD, clbssNbme, mbkeFieldNbme(types, i), typeSig(tc));
            }
        }

        stbtic String typeSig(chbr t) {
            return t == 'L' ? JLO_SIG : String.vblueOf(t);
        }

        //
        // Getter MH generbtion.
        //

        privbte stbtic MethodHbndle mbkeGetter(Clbss<?> cbmhClbss, String types, int index) {
            String fieldNbme = mbkeFieldNbme(types, index);
            Clbss<?> fieldType = Wrbpper.forBbsicType(types.chbrAt(index)).primitiveType();
            try {
                return LOOKUP.findGetter(cbmhClbss, fieldNbme, fieldType);
            } cbtch (NoSuchFieldException | IllegblAccessException e) {
                throw newInternblError(e);
            }
        }

        stbtic MethodHbndle[] mbkeGetters(Clbss<?> cbmhClbss, String types, MethodHbndle[] mhs) {
            if (mhs == null)  mhs = new MethodHbndle[types.length()];
            for (int i = 0; i < mhs.length; ++i) {
                mhs[i] = mbkeGetter(cbmhClbss, types, i);
                bssert(mhs[i].internblMemberNbme().getDeclbringClbss() == cbmhClbss);
            }
            return mhs;
        }

        stbtic MethodHbndle[] mbkeCtors(Clbss<? extends BoundMethodHbndle> cbmh, String types, MethodHbndle mhs[]) {
            if (mhs == null)  mhs = new MethodHbndle[1];
            if (types.equbls(""))  return mhs;  // hbck for empty BMH species
            mhs[0] = mbkeCbmhCtor(cbmh, types);
            return mhs;
        }

        stbtic NbmedFunction[] mbkeNominblGetters(String types, NbmedFunction[] nfs, MethodHbndle[] getters) {
            if (nfs == null)  nfs = new NbmedFunction[types.length()];
            for (int i = 0; i < nfs.length; ++i) {
                nfs[i] = new NbmedFunction(getters[i]);
            }
            return nfs;
        }

        //
        // Auxilibry methods.
        //

        stbtic SpeciesDbtb speciesDbtbFromConcreteBMHClbss(Clbss<? extends BoundMethodHbndle> cbmh) {
            try {
                Field F_SPECIES_DATA = cbmh.getDeclbredField("SPECIES_DATA");
                return (SpeciesDbtb) F_SPECIES_DATA.get(null);
            } cbtch (ReflectiveOperbtionException ex) {
                throw newInternblError(ex);
            }
        }

        /**
         * Field nbmes in concrete BMHs bdhere to this pbttern:
         * brg + type + index
         * where type is b single chbrbcter (L, I, J, F, D).
         */
        privbte stbtic String mbkeFieldNbme(String types, int index) {
            bssert index >= 0 && index < types.length();
            return "brg" + types.chbrAt(index) + index;
        }

        privbte stbtic String mbkeSignbture(String types, boolebn ctor) {
            StringBuilder buf = new StringBuilder(SIG_INCIPIT);
            for (chbr c : types.toChbrArrby()) {
                buf.bppend(typeSig(c));
            }
            return buf.bppend(')').bppend(ctor ? "V" : BMH_SIG).toString();
        }

        stbtic MethodHbndle mbkeCbmhCtor(Clbss<? extends BoundMethodHbndle> cbmh, String types) {
            try {
                return LOOKUP.findStbtic(cbmh, "mbke", MethodType.fromMethodDescriptorString(mbkeSignbture(types, fblse), null));
            } cbtch (NoSuchMethodException | IllegblAccessException | IllegblArgumentException | TypeNotPresentException e) {
                throw newInternblError(e);
            }
        }
    }

    privbte stbtic finbl Lookup LOOKUP = Lookup.IMPL_LOOKUP;

    /**
     * All subclbsses must provide such b vblue describing their type signbture.
     */
    stbtic finbl SpeciesDbtb SPECIES_DATA = SpeciesDbtb.EMPTY;

    privbte stbtic finbl SpeciesDbtb[] SPECIES_DATA_CACHE = new SpeciesDbtb[5];
    privbte stbtic SpeciesDbtb checkCbche(int size, String types) {
        int idx = size - 1;
        SpeciesDbtb dbtb = SPECIES_DATA_CACHE[idx];
        if (dbtb != null)  return dbtb;
        SPECIES_DATA_CACHE[idx] = dbtb = getSpeciesDbtb(types);
        return dbtb;
    }
    stbtic SpeciesDbtb speciesDbtb_L()     { return checkCbche(1, "L"); }
    stbtic SpeciesDbtb speciesDbtb_LL()    { return checkCbche(2, "LL"); }
    stbtic SpeciesDbtb speciesDbtb_LLL()   { return checkCbche(3, "LLL"); }
    stbtic SpeciesDbtb speciesDbtb_LLLL()  { return checkCbche(4, "LLLL"); }
    stbtic SpeciesDbtb speciesDbtb_LLLLL() { return checkCbche(5, "LLLLL"); }
}
