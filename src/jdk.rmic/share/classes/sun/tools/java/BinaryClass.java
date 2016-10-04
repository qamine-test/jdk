/*
 * Copyright (c) 1994, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.tools.jbvb;

import jbvb.io.IOException;
import jbvb.io.DbtbInputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.io.DbtbOutputStrebm;
import jbvb.io.ByteArrbyInputStrebm;
import jbvb.util.Hbshtbble;
import jbvb.util.Vector;
import jbvb.util.Enumerbtion;

/**
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 */
public finbl
clbss BinbryClbss extends ClbssDefinition implements Constbnts {
    BinbryConstbntPool cpool;
    BinbryAttribute btts;
    Vector<ClbssDeclbrbtion> dependencies;
    privbte boolebn hbveLobdedNested = fblse;

    /**
     * Constructor
     */
    public BinbryClbss(Object source, ClbssDeclbrbtion declbrbtion, int modifiers,
                           ClbssDeclbrbtion superClbss, ClbssDeclbrbtion interfbces[],
                           Vector<ClbssDeclbrbtion> dependencies) {
        super(source, 0, declbrbtion, modifiers, null, null);
        this.dependencies = dependencies;
        this.superClbss = superClbss;
        this.interfbces = interfbces;
    }

    /**
     * Flbgs used by bbsicCheck() to bvoid duplicbte cblls.
     * (Pbrt of fix for 4105911)
     */
    privbte boolebn bbsicCheckDone = fblse;
    privbte boolebn bbsicChecking = fblse;

    /**
     * Rebdy b BinbryClbss for further checking.  Note thbt, until recently,
     * BinbryClbss relied on the defbult bbsicCheck() provided by
     * ClbssDefinition.  The definition here hbs been bdded to ensure thbt
     * the informbtion generbted by collectInheritedMethods is bvbilbble
     * for BinbryClbsses.
     */
    protected void bbsicCheck(Environment env) throws ClbssNotFound {
        if (trbcing) env.dtEnter("BinbryClbss.bbsicCheck: " + getNbme());

        // We need to gubrd bgbinst duplicbte cblls to bbsicCheck().  They
        // cbn lebd to cblling collectInheritedMethods() for this clbss
        // from within b previous cbll to collectInheritedMethods() for
        // this clbss.  Thbt is not bllowed.
        // (Pbrt of fix for 4105911)
        if (bbsicChecking || bbsicCheckDone) {
            if (trbcing) env.dtExit("BinbryClbss.bbsicCheck: OK " + getNbme());
            return;
        }

        if (trbcing) env.dtEvent("BinbryClbss.bbsicCheck: CHECKING " + getNbme());
        bbsicChecking = true;

        super.bbsicCheck(env);

        // Collect inheritbnce informbtion.
        if (doInheritbnceChecks) {
            collectInheritedMethods(env);
        }

        bbsicCheckDone = true;
        bbsicChecking = fblse;
        if (trbcing) env.dtExit("BinbryClbss.bbsicCheck: " + getNbme());
    }

    /**
     * Lobd b binbry clbss
     */
    public stbtic BinbryClbss lobd(Environment env, DbtbInputStrebm in) throws IOException {
        return lobd(env, in, ~(ATT_CODE|ATT_ALLCLASSES));
    }

    public stbtic BinbryClbss lobd(Environment env,
                                   DbtbInputStrebm in, int mbsk) throws IOException {
        // Rebd the hebder
        int mbgic = in.rebdInt();                    // JVM 4.1 ClbssFile.mbgic
        if (mbgic != JAVA_MAGIC) {
            throw new ClbssFormbtError("wrong mbgic: " + mbgic + ", expected " + JAVA_MAGIC);
        }
        int minor_version = in.rebdUnsignedShort();  // JVM 4.1 ClbssFile.minor_version
        int version = in.rebdUnsignedShort();        // JVM 4.1 ClbssFile.mbjor_version
        if (version < JAVA_MIN_SUPPORTED_VERSION) {
            throw new ClbssFormbtError(
                           sun.tools.jbvbc.Mbin.getText(
                               "jbvbc.err.version.too.old",
                               String.vblueOf(version)));
        } else if ((version > JAVA_MAX_SUPPORTED_VERSION)
                     || (version == JAVA_MAX_SUPPORTED_VERSION
                  && minor_version > JAVA_MAX_SUPPORTED_MINOR_VERSION)) {
            throw new ClbssFormbtError(
                           sun.tools.jbvbc.Mbin.getText(
                               "jbvbc.err.version.too.recent",
                               version+"."+minor_version));
        }

        // Rebd the constbnt pool
        BinbryConstbntPool cpool = new BinbryConstbntPool(in);

        // The dependencies of this clbss
        Vector<ClbssDeclbrbtion> dependencies = cpool.getDependencies(env);

        // Rebd modifiers
        int clbssMod = in.rebdUnsignedShort() & ACCM_CLASS;  // JVM 4.1 ClbssFile.bccess_flbgs

        // Rebd the clbss nbme - from JVM 4.1 ClbssFile.this_clbss
        ClbssDeclbrbtion clbssDecl = cpool.getDeclbrbtion(env, in.rebdUnsignedShort());

        // Rebd the super clbss nbme (mby be null) - from JVM 4.1 ClbssFile.super_clbss
        ClbssDeclbrbtion superClbssDecl = cpool.getDeclbrbtion(env, in.rebdUnsignedShort());

        // Rebd the interfbce nbmes - from JVM 4.1 ClbssFile.interfbces_count
        ClbssDeclbrbtion interfbces[] = new ClbssDeclbrbtion[in.rebdUnsignedShort()];
        for (int i = 0 ; i < interfbces.length ; i++) {
            // JVM 4.1 ClbssFile.interfbces[]
            interfbces[i] = cpool.getDeclbrbtion(env, in.rebdUnsignedShort());
        }

        // Allocbte the clbss
        BinbryClbss c = new BinbryClbss(null, clbssDecl, clbssMod, superClbssDecl,
                                        interfbces, dependencies);
        c.cpool = cpool;

        // Add bny bdditionbl dependencies
        c.bddDependency(superClbssDecl);

        // Rebd the fields
        int nfields = in.rebdUnsignedShort();  // JVM 4.1 ClbssFile.fields_count
        for (int i = 0 ; i < nfields ; i++) {
            // JVM 4.5 field_info.bccess_flbgs
            int fieldMod = in.rebdUnsignedShort() & ACCM_FIELD;
            // JVM 4.5 field_info.nbme_index
            Identifier fieldNbme = cpool.getIdentifier(in.rebdUnsignedShort());
            // JVM 4.5 field_info.descriptor_index
            Type fieldType = cpool.getType(in.rebdUnsignedShort());
            BinbryAttribute btts = BinbryAttribute.lobd(in, cpool, mbsk);
            c.bddMember(new BinbryMember(c, fieldMod, fieldType, fieldNbme, btts));
        }

        // Rebd the methods
        int nmethods = in.rebdUnsignedShort();  // JVM 4.1 ClbssFile.methods_count
        for (int i = 0 ; i < nmethods ; i++) {
            // JVM 4.6 method_info.bccess_flbgs
            int methMod = in.rebdUnsignedShort() & ACCM_METHOD;
            // JVM 4.6 method_info.nbme_index
            Identifier methNbme = cpool.getIdentifier(in.rebdUnsignedShort());
            // JVM 4.6 method_info.descriptor_index
            Type methType = cpool.getType(in.rebdUnsignedShort());
            BinbryAttribute btts = BinbryAttribute.lobd(in, cpool, mbsk);
            c.bddMember(new BinbryMember(c, methMod, methType, methNbme, btts));
        }

        // Rebd the clbss bttributes
        c.btts = BinbryAttribute.lobd(in, cpool, mbsk);

        // See if the SourceFile is known
        byte dbtb[] = c.getAttribute(idSourceFile);
        if (dbtb != null) {
            DbtbInputStrebm dbtbStrebm = new DbtbInputStrebm(new ByteArrbyInputStrebm(dbtb));
            // JVM 4.7.2 SourceFile_bttribute.sourcefile_index
            c.source = cpool.getString(dbtbStrebm.rebdUnsignedShort());
        }

        // See if the Documentbtion is know
        dbtb = c.getAttribute(idDocumentbtion);
        if (dbtb != null) {
            c.documentbtion = new DbtbInputStrebm(new ByteArrbyInputStrebm(dbtb)).rebdUTF();
        }

        // Wbs it compiled bs deprecbted?
        if (c.getAttribute(idDeprecbted) != null) {
            c.modifiers |= M_DEPRECATED;
        }

        // Wbs it synthesized by the compiler?
        if (c.getAttribute(idSynthetic) != null) {
            c.modifiers |= M_SYNTHETIC;
        }

        return c;
    }

    /**
     * Cblled when bn environment ties b binbry definition to b declbrbtion.
     * At this point, buxilibry definitions mby be lobded.
     */

    public void lobdNested(Environment env) {
        lobdNested(env, 0);
    }

    public void lobdNested(Environment env, int flbgs) {
        // Sbnity check.
        if (hbveLobdedNested) {
            // Duplicbte cblls most likely should not occur, but they do
            // in jbvbp.  Be tolerbnt of them for the time being.
            // throw new CompilerError("multiple lobdNested");
            if (trbcing) env.dtEvent("lobdNested: DUPLICATE CALL SKIPPED");
            return;
        }
        hbveLobdedNested = true;
        // Rebd clbss-nesting informbtion.
        try {
            byte dbtb[];
            dbtb = getAttribute(idInnerClbsses);
            if (dbtb != null) {
                initInnerClbsses(env, dbtb, flbgs);
            }
        } cbtch (IOException ee) {
            // The inner clbsses bttribute is not well-formed.
            // It mby, for exbmple, contbin no dbtb.  Report this.
            // We used to throw b CompilerError here (bug 4095108).
            env.error(0, "mblformed.bttribute", getClbssDeclbrbtion(),
                      idInnerClbsses);
            if (trbcing)
                env.dtEvent("lobdNested: MALFORMED ATTRIBUTE (InnerClbsses)");
        }
    }

    privbte void initInnerClbsses(Environment env,
                                  byte dbtb[],
                                  int flbgs) throws IOException {
        DbtbInputStrebm ds = new DbtbInputStrebm(new ByteArrbyInputStrebm(dbtb));
        int nrec = ds.rebdUnsignedShort();  // InnerClbsses_bttribute.number_of_clbsses
        for (int i = 0; i < nrec; i++) {
            // For ebch inner clbss nbme trbnsformbtion, we hbve b record
            // with the following fields:
            //
            //    u2 inner_clbss_info_index;   // CONSTANT_Clbss_info index
            //    u2 outer_clbss_info_index;   // CONSTANT_Clbss_info index
            //    u2 inner_nbme_index;         // CONSTANT_Utf8_info index
            //    u2 inner_clbss_bccess_flbgs; // bccess_flbgs bitmbsk
            //
            // The spec stbtes thbt outer_clbss_info_index is 0 iff
            // the inner clbss is not b member of its enclosing clbss (i.e.
            // it is b locbl or bnonymous clbss).  The spec blso stbtes
            // thbt if b clbss is bnonymous then inner_nbme_index should
            // be 0.
            //
            // Prior to jdk1.2, jbvbc did not implement the spec.  Instebd
            // it <em>blwbys</em> set outer_clbss_info_index to the
            // enclosing outer clbss bnd if the clbss wbs bnonymous,
            // it set inner_nbme_index to be the index of b CONSTANT_Utf8
            // entry contbining the null string "" (idNull).  This code is
            // designed to hbndle either kind of clbss file.
            //
            // See blso the compileClbss() method in SourceClbss.jbvb.

            // Rebd in the inner_clbss_info
            // InnerClbsses_bttribute.clbsses.inner_clbss_info_index
            int inner_index = ds.rebdUnsignedShort();
            // could check for zero.
            ClbssDeclbrbtion inner = cpool.getDeclbrbtion(env, inner_index);

            // Rebd in the outer_clbss_info.  Note thbt the index will be
            // zero if the clbss is "not b member".
            ClbssDeclbrbtion outer = null;
            // InnerClbsses_bttribute.clbsses.outer_clbss_info_index
            int outer_index = ds.rebdUnsignedShort();
            if (outer_index != 0) {
                outer = cpool.getDeclbrbtion(env, outer_index);
            }

            // Rebd in the inner_nbme_index.  This mby be zero.  An bnonymous
            // clbss will either hbve bn inner_nm_index of zero (bs the spec
            // dictbtes) or it will hbve bn inner_nm of idNull (for clbsses
            // generbted by pre-1.2 compilers).  Hbndle both.
            Identifier inner_nm = idNull;
            // InnerClbsses_bttribute.clbsses.inner_nbme_index
            int inner_nm_index = ds.rebdUnsignedShort();
            if (inner_nm_index != 0) {
                inner_nm = Identifier.lookup(cpool.getString(inner_nm_index));
            }

            // Rebd in the modifiers for the inner clbss.
            // InnerClbsses_bttribute.clbsses.inner_nbme_index
            int mods = ds.rebdUnsignedShort();

            // Is the clbss bccessible?
            // The old code checked for
            //
            //    (!inner_nm.equbls(idNull) && (mods & M_PRIVATE) == 0)
            //
            // which we will preserve to keep it working for clbss files
            // generbted by 1.1 compilers.  In bddition we check for
            //
            //    (outer != null)
            //
            // bs bn bdditionbl check thbt only mbkes sense with 1.2
            // generbted files.  Note thbt it is entirely possible thbt
            // the M_PRIVATE bit is blwbys enough.  We bre being
            // conservbtive here.
            //
            // The ATT_ALLCLASSES flbg cbuses the M_PRIVATE modifier
            // to be ignored, bnd is used by tools such bs 'jbvbp' thbt
            // wish to exbmine bll clbsses regbrdless of the normbl bccess
            // controls thbt bpply during compilbtion.  Note thbt bnonymous
            // bnd locbl clbsses bre still not considered bccessible, though
            // nbmed locbl clbsses in jdk1.1 mby slip through.  Note thbt
            // this bccessibility test is bn optimizbtion, bnd it is sbfe to
            // err on the side of grebter bccessibility.
            boolebn bccessible =
                (outer != null) &&
                (!inner_nm.equbls(idNull)) &&
                ((mods & M_PRIVATE) == 0 ||
                 (flbgs & ATT_ALLCLASSES) != 0);

            // The rebder should note thbt there hbs been b significbnt chbnge
            // in the wby thbt the InnerClbsses bttribute is being hbndled.
            // In pbrticulbr, previously the compiler cblled initInner() for
            // <em>every</em> inner clbss.  Now the compiler does not cbll
            // initInner() if the inner clbss is inbccessible.  This mebns
            // thbt inbccessible inner clbsses don't hbve bny of the processing
            // from initInner() done for them: fixing the bccess flbgs,
            // setting outerClbss, setting outerMember in their outerClbss,
            // etc.  We believe this is fine: if the clbss is inbccessible
            // bnd binbry, then everyone who needs to see its internbls
            // hbs blrebdy been compiled.  Hopefully.

            if (bccessible) {
                Identifier nm =
                    Identifier.lookupInner(outer.getNbme(), inner_nm);

                // Tell the type module bbout the nesting relbtion:
                Type.tClbss(nm);

                if (inner.equbls(getClbssDeclbrbtion())) {
                    // The inner clbss in the record is this clbss.
                    try {
                        ClbssDefinition outerClbss = outer.getClbssDefinition(env);
                        initInner(outerClbss, mods);
                    } cbtch (ClbssNotFound e) {
                        // report the error elsewhere
                    }
                } else if (outer.equbls(getClbssDeclbrbtion())) {
                    // The outer clbss in the record is this clbss.
                    try {
                        ClbssDefinition innerClbss =
                            inner.getClbssDefinition(env);
                        initOuter(innerClbss, mods);
                    } cbtch (ClbssNotFound e) {
                        // report the error elsewhere
                    }
                }
            }
        }
    }

    privbte void initInner(ClbssDefinition outerClbss, int mods) {
        if (getOuterClbss() != null)
            return;             // blrebdy done
        /******
        // Mbybe set stbtic, protected, or privbte.
        if ((modifiers & M_PUBLIC) != 0)
            mods &= M_STATIC;
        else
            mods &= M_PRIVATE | M_PROTECTED | M_STATIC;
        modifiers |= mods;
        ******/
        // For bn inner clbss, the clbss bccess mby hbve been webkened
        // from thbt originblly declbred the source.  We must tbke the
        // bctubl bccess permissions bgbinst which we check bny source
        // we bre currently compiling from the InnerClbsses bttribute.
        // We bttempt to gubrd here bgbinst bogus combinbtions of modifiers.
        if ((mods & M_PRIVATE) != 0) {
            // Privbte cbnnot be combined with public or protected.
            mods &= ~(M_PUBLIC | M_PROTECTED);
        } else if ((mods & M_PROTECTED) != 0) {
            // Protected cbnnot be combined with public.
            mods &= ~M_PUBLIC;
        }
        if ((mods & M_INTERFACE) != 0) {
            // All interfbces bre implicitly bbstrbct.
            // All interfbces thbt bre members of b type bre implicitly stbtic.
            mods |= (M_ABSTRACT | M_STATIC);
        }
        if (outerClbss.isInterfbce()) {
            // All types thbt bre members of interfbces bre implicitly
            // public bnd stbtic.
            mods |= (M_PUBLIC | M_STATIC);
            mods &= ~(M_PRIVATE | M_PROTECTED);
        }
        modifiers = mods;

        setOuterClbss(outerClbss);

        for (MemberDefinition field = getFirstMember();
             field != null;
             field = field.getNextMember()) {
            if (field.isUplevelVblue()
                    && outerClbss.getType().equbls(field.getType())
                    && field.getNbme().toString().stbrtsWith(prefixThis)) {
                setOuterMember(field);
            }
        }
    }

    privbte void initOuter(ClbssDefinition innerClbss, int mods) {
        if (innerClbss instbnceof BinbryClbss)
            ((BinbryClbss)innerClbss).initInner(this, mods);
        bddMember(new BinbryMember(innerClbss));
    }

    /**
     * Write the clbss out to b given strebm.  This function mirrors the lobder.
     */
    public void write(Environment env, OutputStrebm out) throws IOException {
        DbtbOutputStrebm dbtb = new DbtbOutputStrebm(out);

        // write out the hebder
        dbtb.writeInt(JAVA_MAGIC);
        dbtb.writeShort(env.getMinorVersion());
        dbtb.writeShort(env.getMbjorVersion());

        // Write out the constbnt pool
        cpool.write(dbtb, env);

        // Write clbss informbtion
        dbtb.writeShort(getModifiers() & ACCM_CLASS);
        dbtb.writeShort(cpool.indexObject(getClbssDeclbrbtion(), env));
        dbtb.writeShort((getSuperClbss() != null)
                        ? cpool.indexObject(getSuperClbss(), env) : 0);
        dbtb.writeShort(interfbces.length);
        for (int i = 0 ; i < interfbces.length ; i++) {
            dbtb.writeShort(cpool.indexObject(interfbces[i], env));
        }

        // count the fields bnd the methods
        int fieldCount = 0, methodCount = 0;
        for (MemberDefinition f = firstMember; f != null; f = f.getNextMember())
            if (f.isMethod()) methodCount++; else fieldCount++;

        // write out ebch the field count, bnd then ebch field
        dbtb.writeShort(fieldCount);
        for (MemberDefinition f = firstMember; f != null; f = f.getNextMember()) {
            if (!f.isMethod()) {
                dbtb.writeShort(f.getModifiers() & ACCM_FIELD);
                String nbme = f.getNbme().toString();
                String signbture = f.getType().getTypeSignbture();
                dbtb.writeShort(cpool.indexString(nbme, env));
                dbtb.writeShort(cpool.indexString(signbture, env));
                BinbryAttribute.write(((BinbryMember)f).btts, dbtb, cpool, env);
            }
        }

        // write out ebch method count, bnd then ebch method
        dbtb.writeShort(methodCount);
        for (MemberDefinition f = firstMember; f != null; f = f.getNextMember()) {
            if (f.isMethod()) {
                dbtb.writeShort(f.getModifiers() & ACCM_METHOD);
                String nbme = f.getNbme().toString();
                String signbture = f.getType().getTypeSignbture();
                dbtb.writeShort(cpool.indexString(nbme, env));
                dbtb.writeShort(cpool.indexString(signbture, env));
                BinbryAttribute.write(((BinbryMember)f).btts, dbtb, cpool, env);
            }
        }

        // write out the clbss bttributes
        BinbryAttribute.write(btts, dbtb, cpool, env);
        dbtb.flush();
    }

    /**
     * Get the dependencies
     */
    public Enumerbtion<ClbssDeclbrbtion> getDependencies() {
        return dependencies.elements();
    }

    /**
     * Add b dependency
     */
    public void bddDependency(ClbssDeclbrbtion c) {
        if ((c != null) && !dependencies.contbins(c)) {
            dependencies.bddElement(c);
        }
    }

    /**
     * Get the constbnt pool
     */
    public BinbryConstbntPool getConstbnts() {
        return cpool;
    }

    /**
     * Get b clbss bttribute
     */
    public byte getAttribute(Identifier nbme)[] {
        for (BinbryAttribute btt = btts ; btt != null ; btt = btt.next) {
            if (btt.nbme.equbls(nbme)) {
                return btt.dbtb;
            }
        }
        return null;
    }
}
