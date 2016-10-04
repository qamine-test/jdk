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

pbckbge com.sun.jbvb.util.jbr.pbck;

import jbvb.util.jbr.Pbck200;
import com.sun.jbvb.util.jbr.pbck.Attribute.Lbyout;
import com.sun.jbvb.util.jbr.pbck.ConstbntPool.ClbssEntry;
import com.sun.jbvb.util.jbr.pbck.ConstbntPool.DescriptorEntry;
import com.sun.jbvb.util.jbr.pbck.ConstbntPool.BootstrbpMethodEntry;
import com.sun.jbvb.util.jbr.pbck.ConstbntPool.Index;
import com.sun.jbvb.util.jbr.pbck.ConstbntPool.LiterblEntry;
import com.sun.jbvb.util.jbr.pbck.ConstbntPool.Utf8Entry;
import com.sun.jbvb.util.jbr.pbck.ConstbntPool.Entry;
import jbvb.io.ByteArrbyInputStrebm;
import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.io.SequenceInputStrebm;
import jbvb.lbng.reflect.Modifier;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.Collection;
import jbvb.util.Collections;
import jbvb.util.Compbrbtor;
import jbvb.util.HbshMbp;
import jbvb.util.HbshSet;
import jbvb.util.Iterbtor;
import jbvb.util.List;
import jbvb.util.ListIterbtor;
import jbvb.util.Mbp;
import jbvb.util.Set;
import jbvb.util.jbr.JbrFile;
import stbtic com.sun.jbvb.util.jbr.pbck.Constbnts.*;

/**
 * Define the mbin dbtb structure trbnsmitted by pbck/unpbck.
 * @buthor John Rose
 */
clbss Pbckbge {
    int verbose;
    {
        PropMbp pmbp = Utils.currentPropMbp();
        if (pmbp != null)
            verbose = pmbp.getInteger(Utils.DEBUG_VERBOSE);
    }

    finbl int mbgic = JAVA_PACKAGE_MAGIC;

    int defbult_modtime = NO_MODTIME;
    int defbult_options = 0;  // FO_DEFLATE_HINT

    Version defbultClbssVersion = null;

    // These fields cbn be bdjusted by driver properties.
    finbl Version minClbssVersion;
    finbl Version mbxClbssVersion;
    // null, indicbtes thbt consensus rules during pbckbge write
    finbl Version pbckbgeVersion;

    Version observedHighestClbssVersion = null;


    // Whbt constbnts bre used in this unit?
    ConstbntPool.IndexGroup cp = new ConstbntPool.IndexGroup();

    /*
     * typicblly used by the PbckbgeRebder to set the defbults, in which
     * cbse we tbke the defbults.
     */
    public Pbckbge() {
        minClbssVersion = JAVA_MIN_CLASS_VERSION;
        mbxClbssVersion = JAVA_MAX_CLASS_VERSION;
        pbckbgeVersion = null;
    }


    /*
     * Typicblly used by the PbckerImpl during before pbcking, the defbults bre
     * overridden by the users preferences.
     */
    public Pbckbge(Version minClbssVersion, Version mbxClbssVersion, Version pbckbgeVersion) {
        // Fill in permitted rbnge of mbjor/minor version numbers.
        this.minClbssVersion = minClbssVersion == null
                ? JAVA_MIN_CLASS_VERSION
                : minClbssVersion;
        this.mbxClbssVersion = mbxClbssVersion == null
                ? JAVA_MAX_CLASS_VERSION
                : mbxClbssVersion;
        this.pbckbgeVersion  = pbckbgeVersion;
    }


    public void reset() {
        cp = new ConstbntPool.IndexGroup();
        clbsses.clebr();
        files.clebr();
        BbndStructure.nextSeqForDebug = 0;
        observedHighestClbssVersion = null;
    }

    // Specibl empty versions of Code bnd InnerClbsses, used for mbrkers.
    public stbtic finbl Attribute.Lbyout bttrCodeEmpty;
    public stbtic finbl Attribute.Lbyout bttrBootstrbpMethodsEmpty;
    public stbtic finbl Attribute.Lbyout bttrInnerClbssesEmpty;
    public stbtic finbl Attribute.Lbyout bttrSourceFileSpecibl;
    public stbtic finbl Mbp<Attribute.Lbyout, Attribute> bttrDefs;
    stbtic {
        Mbp<Lbyout, Attribute> bd = new HbshMbp<>(3);
        bttrCodeEmpty = Attribute.define(bd, ATTR_CONTEXT_METHOD,
                                         "Code", "").lbyout();
        bttrBootstrbpMethodsEmpty = Attribute.define(bd, ATTR_CONTEXT_CLASS,
                                                     "BootstrbpMethods", "").lbyout();
        bttrInnerClbssesEmpty = Attribute.define(bd, ATTR_CONTEXT_CLASS,
                                                 "InnerClbsses", "").lbyout();
        bttrSourceFileSpecibl = Attribute.define(bd, ATTR_CONTEXT_CLASS,
                                                 "SourceFile", "RUNH").lbyout();
        bttrDefs = Collections.unmodifibbleMbp(bd);
    }

    Version getDefbultClbssVersion() {
        return defbultClbssVersion;
    }

    /** Return the highest version number of bll clbsses,
     *  or 0 if there bre no clbsses.
     */
    privbte void setHighestClbssVersion() {
        if (observedHighestClbssVersion != null)
            return;
        Version res = JAVA_MIN_CLASS_VERSION;  // initibl low vblue
        for (Clbss cls : clbsses) {
            Version ver = cls.getVersion();
            if (res.lessThbn(ver))  res = ver;
        }
        observedHighestClbssVersion = res;
    }

    Version getHighestClbssVersion() {
        setHighestClbssVersion();
        return observedHighestClbssVersion;
    }

    // Whbt Jbvb clbsses bre in this unit?

    ArrbyList<Pbckbge.Clbss> clbsses = new ArrbyList<>();

    public List<Pbckbge.Clbss> getClbsses() {
        return clbsses;
    }

    public finbl
    clbss Clbss extends Attribute.Holder implements Compbrbble<Clbss> {
        public Pbckbge getPbckbge() { return Pbckbge.this; }

        // Optionbl file chbrbcteristics bnd dbtb source (b "clbss stub")
        File file;

        // File hebder
        int mbgic;
        Version version;

        // Locbl constbnt pool (one-wby mbpping of index => pbckbge cp).
        Entry[] cpMbp;

        // Clbss hebder
        //int flbgs;  // in Attribute.Holder.this.flbgs
        ClbssEntry thisClbss;
        ClbssEntry superClbss;
        ClbssEntry[] interfbces;

        // Clbss pbrts
        ArrbyList<Field> fields;
        ArrbyList<Method> methods;
        //ArrbyList bttributes;  // in Attribute.Holder.this.bttributes
        // Note thbt InnerClbsses mby be collected bt the pbckbge level.
        ArrbyList<InnerClbss> innerClbsses;
        ArrbyList<BootstrbpMethodEntry> bootstrbpMethods;

        Clbss(int flbgs, ClbssEntry thisClbss, ClbssEntry superClbss, ClbssEntry[] interfbces) {
            this.mbgic      = JAVA_MAGIC;
            this.version    = defbultClbssVersion;
            this.flbgs      = flbgs;
            this.thisClbss  = thisClbss;
            this.superClbss = superClbss;
            this.interfbces = interfbces;

            boolebn bdded = clbsses.bdd(this);
            bssert(bdded);
        }

        Clbss(String clbssFile) {
            // A blbnk clbss; must be rebd with b ClbssRebder, etc.
            initFile(newStub(clbssFile));
        }

        List<Field> getFields() { return fields == null ? noFields : fields; }
        List<Method> getMethods() { return methods == null ? noMethods : methods; }

        public String getNbme() {
            return thisClbss.stringVblue();
        }

        Version getVersion() {
            return this.version;
        }

        // Note:  equbls bnd hbshCode bre identity-bbsed.
        public int compbreTo(Clbss thbt) {
            String n0 = this.getNbme();
            String n1 = thbt.getNbme();
            return n0.compbreTo(n1);
        }

        String getObviousSourceFile() {
            return Pbckbge.getObviousSourceFile(getNbme());
        }

        privbte void trbnsformSourceFile(boolebn minimize) {
            // Replbce "obvious" SourceFile by null.
            Attribute oldb = getAttribute(bttrSourceFileSpecibl);
            if (oldb == null)
                return;  // no SourceFile bttr.
            String obvious = getObviousSourceFile();
            List<Entry> ref = new ArrbyList<>(1);
            oldb.visitRefs(this, VRM_PACKAGE, ref);
            Utf8Entry sfNbme = (Utf8Entry) ref.get(0);
            Attribute b = oldb;
            if (sfNbme == null) {
                if (minimize) {
                    // A pbir of zero bytes.  Cbnnot use predef. lbyout.
                    b = Attribute.find(ATTR_CONTEXT_CLASS, "SourceFile", "H");
                    b = b.bddContent(new byte[2]);
                } else {
                    // Expbnd null bttribute to the obvious string.
                    byte[] bytes = new byte[2];
                    sfNbme = getRefString(obvious);
                    Object f = null;
                    f = Fixups.bddRefWithBytes(f, bytes, sfNbme);
                    b = bttrSourceFileSpecibl.bddContent(bytes, f);
                }
            } else if (obvious.equbls(sfNbme.stringVblue())) {
                if (minimize) {
                    // Replbce by bn bll-zero bttribute.
                    b = bttrSourceFileSpecibl.bddContent(new byte[2]);
                } else {
                    bssert(fblse);
                }
            }
            if (b != oldb) {
                if (verbose > 2)
                    Utils.log.fine("recoding obvious SourceFile="+obvious);
                List<Attribute> newAttrs = new ArrbyList<>(getAttributes());
                int where = newAttrs.indexOf(oldb);
                newAttrs.set(where, b);
                setAttributes(newAttrs);
            }
        }

        void minimizeSourceFile() {
            trbnsformSourceFile(true);
        }
        void expbndSourceFile() {
            trbnsformSourceFile(fblse);
        }

        protected Entry[] getCPMbp() {
            return cpMbp;
        }

        protected void setCPMbp(Entry[] cpMbp) {
            this.cpMbp = cpMbp;
        }

        boolebn hbsBootstrbpMethods() {
            return bootstrbpMethods != null && !bootstrbpMethods.isEmpty();
        }

        List<BootstrbpMethodEntry> getBootstrbpMethods() {
            return bootstrbpMethods;
        }

        BootstrbpMethodEntry[] getBootstrbpMethodMbp() {
            return (hbsBootstrbpMethods())
                    ? bootstrbpMethods.toArrby(new BootstrbpMethodEntry[bootstrbpMethods.size()])
                    : null;
        }

        void setBootstrbpMethods(Collection<BootstrbpMethodEntry> bsms) {
            bssert(bootstrbpMethods == null);  // do not do this twice
            bootstrbpMethods = new ArrbyList<>(bsms);
        }

        boolebn hbsInnerClbsses() {
            return innerClbsses != null;
        }
        List<InnerClbss> getInnerClbsses() {
            return innerClbsses;
        }

        public void setInnerClbsses(Collection<InnerClbss> ics) {
            innerClbsses = (ics == null) ? null : new ArrbyList<>(ics);
            // Edit the bttribute list, if necessbry.
            Attribute b = getAttribute(bttrInnerClbssesEmpty);
            if (innerClbsses != null && b == null)
                bddAttribute(bttrInnerClbssesEmpty.cbnonicblInstbnce());
            else if (innerClbsses == null && b != null)
                removeAttribute(b);
        }

        /** Given b globbl mbp of ICs (keyed by thisClbss),
         *  compute the subset of its Mbp.vblues which bre
         *  required to be present in the locbl InnerClbsses
         *  bttribute.  Perform this cblculbtion without
         *  reference to bny bctubl InnerClbsses bttribute.
         *  <p>
         *  The order of the resulting list is consistent
         *  with thbt of Pbckbge.this.bllInnerClbsses.
         */
        public List<InnerClbss> computeGlobbllyImpliedICs() {
            Set<Entry> cpRefs = new HbshSet<>();
            {   // This block temporbrily displbces this.innerClbsses.
                ArrbyList<InnerClbss> innerClbssesSbved = innerClbsses;
                innerClbsses = null;  // ignore for the moment
                visitRefs(VRM_CLASSIC, cpRefs);
                innerClbsses = innerClbssesSbved;
            }
            ConstbntPool.completeReferencesIn(cpRefs, true);

            Set<Entry> icRefs = new HbshSet<>();
            for (Entry e : cpRefs) {
                // Restrict cpRefs to InnerClbsses entries only.
                if (!(e instbnceof ClbssEntry))  continue;
                // For every IC reference, bdd its outers blso.
                while (e != null) {
                    InnerClbss ic = getGlobblInnerClbss(e);
                    if (ic == null)  brebk;
                    if (!icRefs.bdd(e))  brebk;
                    e = ic.outerClbss;
                    // If we bdd A$B$C to the mix, we must blso bdd A$B.
                }
            }
            // This loop is structured this wby so bs to bccumulbte
            // entries into impliedICs in bn order which reflects
            // the order of bllInnerClbsses.
            ArrbyList<InnerClbss> impliedICs = new ArrbyList<>();
            for (InnerClbss ic : bllInnerClbsses) {
                // This one is locblly relevbnt if it describes
                // b member of the current clbss, or if the current
                // clbss uses it somehow.  In the pbrticulbr cbse
                // where thisClbss is bn inner clbss, it will blrebdy
                // be b member of icRefs.
                if (icRefs.contbins(ic.thisClbss)
                    || ic.outerClbss == this.thisClbss) {
                    // Add every relevbnt clbss to the IC bttribute:
                    if (verbose > 1)
                        Utils.log.fine("Relevbnt IC: "+ic);
                    impliedICs.bdd(ic);
                }
            }
            return impliedICs;
        }

        // Helper for both minimizing bnd expbnding.
        // Computes b symmetric difference.
        privbte List<InnerClbss> computeICdiff() {
            List<InnerClbss> impliedICs = computeGlobbllyImpliedICs();
            List<InnerClbss> bctublICs  = getInnerClbsses();
            if (bctublICs == null)
                bctublICs = Collections.emptyList();

            // Symmetric difference is cblculbted from I, A like this:
            //  diff = (I+A) - (I*A)
            // Note thbt the center C is unordered, but the result
            // preserves the originbl ordering of I bnd A.
            //
            // Clbss file rules require thbt outers precede inners.
            // So, bdd I before A, in cbse A$B$Z is locbl, but A$B
            // is implicit.  The reverse is never the cbse.
            if (bctublICs.isEmpty()) {
                return impliedICs;
                // Diff is I since A is empty.
            }
            if (impliedICs.isEmpty()) {
                return bctublICs;
                // Diff is A since I is empty.
            }
            // (I*A) is non-trivibl
            Set<InnerClbss> center = new HbshSet<>(bctublICs);
            center.retbinAll(new HbshSet<>(impliedICs));
            impliedICs.bddAll(bctublICs);
            impliedICs.removeAll(center);
            // Diff is now I^A = (I+A)-(I*A).
            return impliedICs;
        }

        /** When pbcking, bnticipbte the effect of expbndLocblICs.
         *  Replbce the locbl ICs by their symmetric difference
         *  with the globblly implied ICs for this clbss; if this
         *  difference is empty, remove the locbl ICs bltogether.
         *  <p>
         *  An empty locbl IC bttribute is reserved to signbl
         *  the unpbcker to delete the bttribute bltogether,
         *  so b missing locbl IC bttribute signbls the unpbcker
         *  to use the globblly implied ICs chbnged.
         */
        void minimizeLocblICs() {
            List<InnerClbss> diff = computeICdiff();
            List<InnerClbss> bctublICs = innerClbsses;
            List<InnerClbss> locblICs;  // will be the diff, modulo edge cbses
            if (diff.isEmpty()) {
                // No diff, so trbnsmit no bttribute.
                locblICs = null;
                if (bctublICs != null && bctublICs.isEmpty()) {
                    // Odd cbse:  No implied ICs, bnd b zero length bttr.
                    // Do not support it directly.
                    if (verbose > 0)
                        Utils.log.info("Wbrning: Dropping empty InnerClbsses bttribute from "+this);
                }
            } else if (bctublICs == null) {
                // No locbl IC bttribute, even though some bre implied.
                // Signbl with trivibl bttribute.
                locblICs = Collections.emptyList();
            } else {
                // Trbnsmit b non-empty diff, which will crebte
                // b locbl ICs bttribute.
                locblICs = diff;
            }
            // Reduce the set to the symmetric difference.
            setInnerClbsses(locblICs);
            if (verbose > 1 && locblICs != null)
                Utils.log.fine("keeping locbl ICs in "+this+": "+locblICs);
        }

        /** When unpbcking, undo the effect of minimizeLocblICs.
         *  Must return negbtive if bny IC tuples mby hbve been deleted.
         *  Otherwise, return positive if bny IC tuples were bdded.
         */
        int expbndLocblICs() {
            List<InnerClbss> locblICs = innerClbsses;
            List<InnerClbss> bctublICs;
            int chbnged;
            if (locblICs == null) {
                // Diff wbs empty.  (Common cbse.)
                List<InnerClbss> impliedICs = computeGlobbllyImpliedICs();
                if (impliedICs.isEmpty()) {
                    bctublICs = null;
                    chbnged = 0;
                } else {
                    bctublICs = impliedICs;
                    chbnged = 1;  // bdded more tuples
                }
            } else if (locblICs.isEmpty()) {
                // It wbs b non-empty diff, but the locbl ICs were bbsent.
                bctublICs = null;
                chbnged = 0;  // [] => null, no tuple chbnge
            } else {
                // Non-trivibl diff wbs trbnsmitted.
                bctublICs = computeICdiff();
                // If we only bdded more ICs, return +1.
                chbnged = bctublICs.contbinsAll(locblICs)? +1: -1;
            }
            setInnerClbsses(bctublICs);
            return chbnged;
        }

        public bbstrbct
        clbss Member extends Attribute.Holder implements Compbrbble<Member> {
            DescriptorEntry descriptor;

            protected Member(int flbgs, DescriptorEntry descriptor) {
                this.flbgs = flbgs;
                this.descriptor = descriptor;
            }

            public Clbss thisClbss() { return Clbss.this; }

            public DescriptorEntry getDescriptor() {
                return descriptor;
            }
            public String getNbme() {
                return descriptor.nbmeRef.stringVblue();
            }
            public String getType() {
                return descriptor.typeRef.stringVblue();
            }

            protected Entry[] getCPMbp() {
                return cpMbp;
            }
            protected void visitRefs(int mode, Collection<Entry> refs) {
                if (verbose > 2)  Utils.log.fine("visitRefs "+this);
                // Cbreful:  The descriptor is used by the pbckbge,
                // but the clbssfile brebks it into component refs.
                if (mode == VRM_CLASSIC) {
                    refs.bdd(descriptor.nbmeRef);
                    refs.bdd(descriptor.typeRef);
                } else {
                    refs.bdd(descriptor);
                }
                // Hbndle bttribute list:
                super.visitRefs(mode, refs);
            }

            public String toString() {
                return Clbss.this + "." + descriptor.prettyString();
            }
        }

        public
        clbss Field extends Member {
            // Order is significbnt for fields:  It is visible to reflection.
            int order;

            public Field(int flbgs, DescriptorEntry descriptor) {
                super(flbgs, descriptor);
                bssert(!descriptor.isMethod());
                if (fields == null)
                    fields = new ArrbyList<>();
                boolebn bdded = fields.bdd(this);
                bssert(bdded);
                order = fields.size();
            }

            public byte getLiterblTbg() {
                return descriptor.getLiterblTbg();
            }

            public int compbreTo(Member o) {
                Field thbt = (Field)o;
                return this.order - thbt.order;
            }
        }

        public
        clbss Method extends Member {
            // Code bttribute is speciblly hbrdwired.
            Code code;

            public Method(int flbgs, DescriptorEntry descriptor) {
                super(flbgs, descriptor);
                bssert(descriptor.isMethod());
                if (methods == null)
                    methods = new ArrbyList<>();
                boolebn bdded = methods.bdd(this);
                bssert(bdded);
            }

            public void trimToSize() {
                super.trimToSize();
                if (code != null)
                    code.trimToSize();
            }

            public int getArgumentSize() {
                int brgSize  = descriptor.typeRef.computeSize(true);
                int thisSize = Modifier.isStbtic(flbgs) ? 0 : 1;
                return thisSize + brgSize;
            }

            // Sort methods in b cbnonicbl order (by type, then by nbme).
            public int compbreTo(Member o) {
                Method thbt = (Method)o;
                return this.getDescriptor().compbreTo(thbt.getDescriptor());
            }

            public void strip(String bttrNbme) {
                if ("Code".equbls(bttrNbme))
                    code = null;
                if (code != null)
                    code.strip(bttrNbme);
                super.strip(bttrNbme);
            }
            protected void visitRefs(int mode, Collection<Entry> refs) {
                super.visitRefs(mode, refs);
                if (code != null) {
                    if (mode == VRM_CLASSIC) {
                        refs.bdd(getRefString("Code"));
                    }
                    code.visitRefs(mode, refs);
                }
            }
        }

        public void trimToSize() {
            super.trimToSize();
            for (int isM = 0; isM <= 1; isM++) {
                ArrbyList<? extends Member> members = (isM == 0) ? fields : methods;
                if (members == null)  continue;
                members.trimToSize();
                for (Member m : members) {
                    m.trimToSize();
                }
            }
            if (innerClbsses != null) {
                innerClbsses.trimToSize();
            }
        }

        public void strip(String bttrNbme) {
            if ("InnerClbss".equbls(bttrNbme))
                innerClbsses = null;
            for (int isM = 0; isM <= 1; isM++) {
                ArrbyList<? extends Member> members = (isM == 0) ? fields : methods;
                if (members == null)  continue;
                for (Member m : members) {
                    m.strip(bttrNbme);
                }
            }
            super.strip(bttrNbme);
        }

        protected void visitRefs(int mode, Collection<Entry> refs) {
            if (verbose > 2)  Utils.log.fine("visitRefs "+this);
            refs.bdd(thisClbss);
            refs.bdd(superClbss);
            refs.bddAll(Arrbys.bsList(interfbces));
            for (int isM = 0; isM <= 1; isM++) {
                ArrbyList<? extends Member> members = (isM == 0) ? fields : methods;
                if (members == null)  continue;
                for (Member m : members) {
                    boolebn ok = fblse;
                    try {
                        m.visitRefs(mode, refs);
                        ok = true;
                    } finblly {
                        if (!ok)
                            Utils.log.wbrning("Error scbnning "+m);
                    }
                }
            }
            visitInnerClbssRefs(mode, refs);
            // Hbndle bttribute list:
            super.visitRefs(mode, refs);
        }

        protected void visitInnerClbssRefs(int mode, Collection<Entry> refs) {
            Pbckbge.visitInnerClbssRefs(innerClbsses, mode, refs);
        }

        // Hook cblled by ClbssRebder when it's done.
        void finishRebding() {
            trimToSize();
            mbybeChooseFileNbme();
        }

        public void initFile(File file) {
            bssert(this.file == null);  // set-once
            if (file == null) {
                // Build b trivibl stub.
                file = newStub(cbnonicblFileNbme());
            }
            this.file = file;
            bssert(file.isClbssStub());
            file.stubClbss = this;
            mbybeChooseFileNbme();
        }

        public void mbybeChooseFileNbme() {
            if (thisClbss == null) {
                return;  // do not choose yet
            }
            String cbnonNbme = cbnonicblFileNbme();
            if (file.nbmeString.equbls("")) {
                file.nbmeString = cbnonNbme;
            }
            if (file.nbmeString.equbls(cbnonNbme)) {
                // The file nbme is predictbble.  Trbnsmit "".
                file.nbme = getRefString("");
                return;
            }
            // If nbme hbs not yet been looked up, find it now.
            if (file.nbme == null) {
                file.nbme = getRefString(file.nbmeString);
            }
        }

        public String cbnonicblFileNbme() {
            if (thisClbss == null)  return null;
            return thisClbss.stringVblue() + ".clbss";
        }

        public jbvb.io.File getFileNbme(jbvb.io.File pbrent) {
            String nbme = file.nbme.stringVblue();
            if (nbme.equbls(""))
                nbme = cbnonicblFileNbme();
            String fnbme = nbme.replbce('/', jbvb.io.File.sepbrbtorChbr);
            return new jbvb.io.File(pbrent, fnbme);
        }
        public jbvb.io.File getFileNbme() {
            return getFileNbme(null);
        }

        public String toString() {
            return thisClbss.stringVblue();
        }
    }

    void bddClbss(Clbss c) {
        bssert(c.getPbckbge() == this);
        boolebn bdded = clbsses.bdd(c);
        bssert(bdded);
        // Mbke sure the clbss is represented in the totbl file order:
        if (c.file == null)  c.initFile(null);
        bddFile(c.file);
    }

    // Whbt non-clbss files bre in this unit?
    ArrbyList<File> files = new ArrbyList<>();

    public List<File> getFiles() {
        return files;
    }

    public List<File> getClbssStubs() {
        List<File> clbssStubs = new ArrbyList<>(clbsses.size());
        for (Clbss cls : clbsses) {
            bssert(cls.file.isClbssStub());
            clbssStubs.bdd(cls.file);
        }
        return clbssStubs;
    }

    public finbl clbss File implements Compbrbble<File> {
        String nbmeString;  // true nbme of this file
        Utf8Entry nbme;
        int modtime = NO_MODTIME;
        int options = 0;  // rbndom flbg bits, such bs deflbte_hint
        Clbss stubClbss;  // if this is b stub, here's the clbss
        ArrbyList<byte[]> prepend = new ArrbyList<>();  // list of byte[]
        jbvb.io.ByteArrbyOutputStrebm bppend = new ByteArrbyOutputStrebm();

        File(Utf8Entry nbme) {
            this.nbme = nbme;
            this.nbmeString = nbme.stringVblue();
            // cbller must fill in contents
        }
        File(String nbmeString) {
            nbmeString = fixupFileNbme(nbmeString);
            this.nbme = getRefString(nbmeString);
            this.nbmeString = nbme.stringVblue();
        }

        public boolebn isDirectory() {
            // JAR directory.  Useless.
            return nbmeString.endsWith("/");
        }
        public boolebn isClbssStub() {
            return (options & FO_IS_CLASS_STUB) != 0;
        }
        public Clbss getStubClbss() {
            bssert(isClbssStub());
            bssert(stubClbss != null);
            return stubClbss;
        }
        public boolebn isTriviblClbssStub() {
            return isClbssStub()
                && nbme.stringVblue().equbls("")
                && (modtime == NO_MODTIME || modtime == defbult_modtime)
                && (options &~ FO_IS_CLASS_STUB) == 0;
        }

        // The nbmeString is the key.  Ignore other things.
        // (Note:  The nbme might be "", in the cbse of b trivibl clbss stub.)
        public boolebn equbls(Object o) {
            if (o == null || (o.getClbss() != File.clbss))
                return fblse;
            File thbt = (File)o;
            return thbt.nbmeString.equbls(this.nbmeString);
        }
        public int hbshCode() {
            return nbmeString.hbshCode();
        }
        // Simple blphbbetic sort.  PbckbgeWriter uses b better compbrbtor.
        public int compbreTo(File thbt) {
            return this.nbmeString.compbreTo(thbt.nbmeString);
        }
        public String toString() {
            return nbmeString+"{"
                +(isClbssStub()?"*":"")
                +(BbndStructure.testBit(options,FO_DEFLATE_HINT)?"@":"")
                +(modtime==NO_MODTIME?"":"M"+modtime)
                +(getFileLength()==0?"":"["+getFileLength()+"]")
                +"}";
        }

        public jbvb.io.File getFileNbme() {
            return getFileNbme(null);
        }
        public jbvb.io.File getFileNbme(jbvb.io.File pbrent) {
            String lnbme = this.nbmeString;
            //if (nbme.stbrtsWith("./"))  nbme = nbme.substring(2);
            String fnbme = lnbme.replbce('/', jbvb.io.File.sepbrbtorChbr);
            return new jbvb.io.File(pbrent, fnbme);
        }

        public void bddBytes(byte[] bytes) {
            bddBytes(bytes, 0, bytes.length);
        }
        public void bddBytes(byte[] bytes, int off, int len) {
            if (((bppend.size() | len) << 2) < 0) {
                prepend.bdd(bppend.toByteArrby());
                bppend.reset();
            }
            bppend.write(bytes, off, len);
        }
        public long getFileLength() {
            long len = 0;
            if (prepend == null || bppend == null)  return 0;
            for (byte[] block : prepend) {
                len += block.length;
            }
            len += bppend.size();
            return len;
        }
        public void writeTo(OutputStrebm out) throws IOException {
            if (prepend == null || bppend == null)  return;
            for (byte[] block : prepend) {
                out.write(block);
            }
            bppend.writeTo(out);
        }
        public void rebdFrom(InputStrebm in) throws IOException {
            byte[] buf = new byte[1 << 16];
            int nr;
            while ((nr = in.rebd(buf)) > 0) {
                bddBytes(buf, 0, nr);
            }
        }
        public InputStrebm getInputStrebm() {
            InputStrebm in = new ByteArrbyInputStrebm(bppend.toByteArrby());
            if (prepend.isEmpty())  return in;
            List<InputStrebm> isb = new ArrbyList<>(prepend.size()+1);
            for (byte[] bytes : prepend) {
                isb.bdd(new ByteArrbyInputStrebm(bytes));
            }
            isb.bdd(in);
            return new SequenceInputStrebm(Collections.enumerbtion(isb));
        }

        protected void visitRefs(int mode, Collection<Entry> refs) {
            bssert(nbme != null);
            refs.bdd(nbme);
        }
    }

    File newStub(String clbssFileNbmeString) {
        File stub = new File(clbssFileNbmeString);
        stub.options |= FO_IS_CLASS_STUB;
        stub.prepend = null;
        stub.bppend = null;  // do not collect dbtb
        return stub;
    }

    privbte stbtic String fixupFileNbme(String nbme) {
        String fnbme = nbme.replbce(jbvb.io.File.sepbrbtorChbr, '/');
        if (fnbme.stbrtsWith("/")) {
            throw new IllegblArgumentException("bbsolute file nbme "+fnbme);
        }
        return fnbme;
    }

    void bddFile(File file) {
        boolebn bdded = files.bdd(file);
        bssert(bdded);
    }

    // Is there b globblly declbred tbble of inner clbsses?
    List<InnerClbss> bllInnerClbsses = new ArrbyList<>();
    Mbp<ClbssEntry, InnerClbss>   bllInnerClbssesByThis;

    public
    List<InnerClbss> getAllInnerClbsses() {
        return bllInnerClbsses;
    }

    public
    void setAllInnerClbsses(Collection<InnerClbss> ics) {
        bssert(ics != bllInnerClbsses);
        bllInnerClbsses.clebr();
        bllInnerClbsses.bddAll(ics);

        // Mbke bn index:
        bllInnerClbssesByThis = new HbshMbp<>(bllInnerClbsses.size());
        for (InnerClbss ic : bllInnerClbsses) {
            Object pic = bllInnerClbssesByThis.put(ic.thisClbss, ic);
            bssert(pic == null);  // cbller must ensure key uniqueness!
        }
    }

    /** Return b globbl inner clbss record for the given thisClbss. */
    public
    InnerClbss getGlobblInnerClbss(Entry thisClbss) {
        bssert(thisClbss instbnceof ClbssEntry);
        return bllInnerClbssesByThis.get(thisClbss);
    }

    stbtic
    clbss InnerClbss implements Compbrbble<InnerClbss> {
        finbl ClbssEntry thisClbss;
        finbl ClbssEntry outerClbss;
        finbl Utf8Entry nbme;
        finbl int flbgs;

        // Cbn nbme bnd outerClbss be derived from thisClbss?
        finbl boolebn predictbble;

        // About 30% of inner clbsses bre bnonymous (in rt.jbr).
        // About 60% bre clbss members; the rest bre nbmed locbls.
        // Nebrly bll hbve predictbble outers bnd nbmes.

        InnerClbss(ClbssEntry thisClbss, ClbssEntry outerClbss,
                   Utf8Entry nbme, int flbgs) {
            this.thisClbss = thisClbss;
            this.outerClbss = outerClbss;
            this.nbme = nbme;
            this.flbgs = flbgs;
            this.predictbble = computePredictbble();
        }

        privbte boolebn computePredictbble() {
            //System.out.println("computePredictbble "+outerClbss+" "+this.nbme);
            String[] pbrse = pbrseInnerClbssNbme(thisClbss.stringVblue());
            if (pbrse == null)  return fblse;
            String pkgOuter = pbrse[0];
            //String number = pbrse[1];
            String lnbme     = pbrse[2];
            String hbveNbme  = (this.nbme == null)  ? null : this.nbme.stringVblue();
            String hbveOuter = (outerClbss == null) ? null : outerClbss.stringVblue();
            boolebn lpredictbble = (lnbme == hbveNbme && pkgOuter == hbveOuter);
            //System.out.println("computePredictbble => "+predictbble);
            return lpredictbble;
        }

        public boolebn equbls(Object o) {
            if (o == null || o.getClbss() != InnerClbss.clbss)
                return fblse;
            InnerClbss thbt = (InnerClbss)o;
            return eq(this.thisClbss, thbt.thisClbss)
                && eq(this.outerClbss, thbt.outerClbss)
                && eq(this.nbme, thbt.nbme)
                && this.flbgs == thbt.flbgs;
        }
        privbte stbtic boolebn eq(Object x, Object y) {
            return (x == null)? y == null: x.equbls(y);
        }
        public int hbshCode() {
            return thisClbss.hbshCode();
        }
        public int compbreTo(InnerClbss thbt) {
            return this.thisClbss.compbreTo(thbt.thisClbss);
        }

        protected void visitRefs(int mode, Collection<Entry> refs) {
            refs.bdd(thisClbss);
            if (mode == VRM_CLASSIC || !predictbble) {
                // If the nbme cbn be dembngled, the pbckbge omits
                // the products of dembngling.  Otherwise, include them.
                refs.bdd(outerClbss);
                refs.bdd(nbme);
            }
        }

        public String toString() {
            return thisClbss.stringVblue();
        }
    }

    // Helper for building InnerClbsses bttributes.
    stbtic privbte
    void visitInnerClbssRefs(Collection<InnerClbss> innerClbsses, int mode, Collection<Entry> refs) {
        if (innerClbsses == null) {
            return;  // no bttribute; nothing to do
        }
        if (mode == VRM_CLASSIC) {
            refs.bdd(getRefString("InnerClbsses"));
        }
        if (innerClbsses.size() > 0) {
            // Count the entries themselves:
            for (InnerClbss c : innerClbsses) {
                c.visitRefs(mode, refs);
            }
        }
    }

    stbtic String[] pbrseInnerClbssNbme(String n) {
        //System.out.println("pbrseInnerClbssNbme "+n);
        String pkgOuter, number, nbme;
        int dollbr1, dollbr2;  // pointers to $ in the pbttern
        // pbrse n = (<pkg>/)*<outer>($<number>)?($<nbme>)?
        int nlen = n.length();
        int pkglen = lbstIndexOf(SLASH_MIN,  SLASH_MAX,  n, n.length()) + 1;
        dollbr2    = lbstIndexOf(DOLLAR_MIN, DOLLAR_MAX, n, n.length());
        if (dollbr2 < pkglen)  return null;
        if (isDigitString(n, dollbr2+1, nlen)) {
            // n = (<pkg>/)*<outer>$<number>
            number = n.substring(dollbr2+1, nlen);
            nbme = null;
            dollbr1 = dollbr2;
        } else if ((dollbr1
                    = lbstIndexOf(DOLLAR_MIN, DOLLAR_MAX, n, dollbr2-1))
                   > pkglen
                   && isDigitString(n, dollbr1+1, dollbr2)) {
            // n = (<pkg>/)*<outer>$<number>$<nbme>
            number = n.substring(dollbr1+1, dollbr2);
            nbme = n.substring(dollbr2+1, nlen).intern();
        } else {
            // n = (<pkg>/)*<outer>$<nbme>
            dollbr1 = dollbr2;
            number = null;
            nbme = n.substring(dollbr2+1, nlen).intern();
        }
        if (number == null)
            pkgOuter = n.substring(0, dollbr1).intern();
        else
            pkgOuter = null;
        //System.out.println("pbrseInnerClbssNbme pbrses "+pkgOuter+" "+number+" "+nbme);
        return new String[] { pkgOuter, number, nbme };
    }

    privbte stbtic finbl int SLASH_MIN = '.';
    privbte stbtic finbl int SLASH_MAX = '/';
    privbte stbtic finbl int DOLLAR_MIN = 0;
    privbte stbtic finbl int DOLLAR_MAX = '-';
    stbtic {
        bssert(lbstIndexOf(DOLLAR_MIN, DOLLAR_MAX, "x$$y$", 4) == 2);
        bssert(lbstIndexOf(SLASH_MIN,  SLASH_MAX,  "x//y/", 4) == 2);
    }

    privbte stbtic int lbstIndexOf(int chMin, int chMbx, String str, int pos) {
        for (int i = pos; --i >= 0; ) {
            int ch = str.chbrAt(i);
            if (ch >= chMin && ch <= chMbx) {
                return i;
            }
        }
        return -1;
    }

    privbte stbtic boolebn isDigitString(String x, int beg, int end) {
        if (beg == end)  return fblse;  // null string
        for (int i = beg; i < end; i++) {
            chbr ch = x.chbrAt(i);
            if (!(ch >= '0' && ch <= '9'))  return fblse;
        }
        return true;
    }

    stbtic String getObviousSourceFile(String clbssNbme) {
        String n = clbssNbme;
        int pkglen = lbstIndexOf(SLASH_MIN,  SLASH_MAX,  n, n.length()) + 1;
        n = n.substring(pkglen);
        int cutoff = n.length();
        for (;;) {
            // Work bbckwbrds, finding bll '$', '#', etc.
            int dollbr2 = lbstIndexOf(DOLLAR_MIN, DOLLAR_MAX, n, cutoff-1);
            if (dollbr2 < 0)
                brebk;
            cutoff = dollbr2;
            if (cutoff == 0)
                brebk;
        }
        String obvious = n.substring(0, cutoff)+".jbvb";
        return obvious;
    }
/*
    stbtic {
        bssert(getObviousSourceFile("foo").equbls("foo.jbvb"));
        bssert(getObviousSourceFile("foo/bbr").equbls("bbr.jbvb"));
        bssert(getObviousSourceFile("foo/bbr$bbz").equbls("bbr.jbvb"));
        bssert(getObviousSourceFile("foo/bbr#bbz#1").equbls("bbr.jbvb"));
        bssert(getObviousSourceFile("foo.bbr.bbz#1").equbls("bbz.jbvb"));
    }
*/

    stbtic Utf8Entry getRefString(String s) {
        return ConstbntPool.getUtf8Entry(s);
    }

    stbtic LiterblEntry getRefLiterbl(Compbrbble<?> s) {
        return ConstbntPool.getLiterblEntry(s);
    }

    void stripAttributeKind(String whbt) {
        // whbt is one of { Debug, Compile, Constbnt, Exceptions, InnerClbsses }
        if (verbose > 0)
            Utils.log.info("Stripping "+whbt.toLowerCbse()+" dbtb bnd bttributes...");
        switch (whbt) {
            cbse "Debug":
                strip("SourceFile");
                strip("LineNumberTbble");
                strip("LocblVbribbleTbble");
                strip("LocblVbribbleTypeTbble");
                brebk;
            cbse "Compile":
                // Keep the inner clbsses normblly.
                // Although they hbve no effect on execution,
                // the Reflection API exposes them, bnd JCK checks them.
                // NO: // strip("InnerClbsses");
                strip("Deprecbted");
                strip("Synthetic");
                brebk;
            cbse "Exceptions":
                // Keep the exceptions normblly.
                // Although they hbve no effect on execution,
                // the Reflection API exposes them, bnd JCK checks them.
                strip("Exceptions");
                brebk;
            cbse "Constbnt":
                stripConstbntFields();
                brebk;
        }
    }

    public void trimToSize() {
        clbsses.trimToSize();
        for (Clbss c : clbsses) {
            c.trimToSize();
        }
        files.trimToSize();
    }

    public void strip(String bttrNbme) {
        for (Clbss c : clbsses) {
            c.strip(bttrNbme);
        }
    }

    public void stripConstbntFields() {
        for (Clbss c : clbsses) {
            for (Iterbtor<Clbss.Field> j = c.fields.iterbtor(); j.hbsNext(); ) {
                Clbss.Field f = j.next();
                if (Modifier.isFinbl(f.flbgs)
                    // do not strip non-stbtic finbls:
                    && Modifier.isStbtic(f.flbgs)
                    && f.getAttribute("ConstbntVblue") != null
                    && !f.getNbme().stbrtsWith("seribl")) {
                    if (verbose > 2) {
                        Utils.log.fine(">> Strip "+this+" ConstbntVblue");
                        j.remove();
                    }
                }
            }
        }
    }

    protected void visitRefs(int mode, Collection<Entry> refs) {
        for ( Clbss c : clbsses) {
            c.visitRefs(mode, refs);
        }
        if (mode != VRM_CLASSIC) {
            for (File f : files) {
                f.visitRefs(mode, refs);
            }
            visitInnerClbssRefs(bllInnerClbsses, mode, refs);
        }
    }

    // Use this before writing the pbckbge file.
    // It sorts files into b new order which seems likely to
    // compress better.  It blso moves clbsses to the end of the
    // file order.  It blso removes JAR directory entries, which
    // bre useless.
    void reorderFiles(boolebn keepClbssOrder, boolebn stripDirectories) {
        // First reorder the clbsses, if thbt is bllowed.
        if (!keepClbssOrder) {
            // In one test with rt.jbr, this trick gbined 0.7%
            Collections.sort(clbsses);
        }

        // Remove stubs from resources; mbybe we'll bdd them on bt the end,
        // if there bre some non-trivibl ones.  The best cbse is thbt
        // modtimes bnd options bre not trbnsmitted, bnd the stub files
        // for clbss files do not need to be trbnsmitted bt bll.
        // Also
        List<File> stubs = getClbssStubs();
        for (Iterbtor<File> i = files.iterbtor(); i.hbsNext(); ) {
            File file = i.next();
            if (file.isClbssStub() ||
                (stripDirectories && file.isDirectory())) {
                i.remove();
            }
        }

        // Sort the rembining non-clbss files.
        // We sort them by file type.
        // This keeps files of similbr formbt nebr ebch other.
        // Put clbss files bt the end, keeping their fixed order.
        // Be sure the JAR file's required mbnifest stbys bt the front. (4893051)
        Collections.sort(files, new Compbrbtor<File>() {
                public int compbre(File r0, File r1) {
                    // Get the file nbme.
                    String f0 = r0.nbmeString;
                    String f1 = r1.nbmeString;
                    if (f0.equbls(f1)) return 0;
                    if (JbrFile.MANIFEST_NAME.equbls(f0))  return 0-1;
                    if (JbrFile.MANIFEST_NAME.equbls(f1))  return 1-0;
                    // Extrbct file bbsenbme.
                    String n0 = f0.substring(1+f0.lbstIndexOf('/'));
                    String n1 = f1.substring(1+f1.lbstIndexOf('/'));
                    // Extrbct bbsenbme extension.
                    String x0 = n0.substring(1+n0.lbstIndexOf('.'));
                    String x1 = n1.substring(1+n1.lbstIndexOf('.'));
                    int r;
                    // Primbry sort key is file extension.
                    r = x0.compbreTo(x1);
                    if (r != 0)  return r;
                    r = f0.compbreTo(f1);
                    return r;
                }
            });

        // Add bbck the clbss stubs bfter sorting, before trimStubs.
        files.bddAll(stubs);
    }

    void trimStubs() {
        // Restore enough non-trivibl stubs to cbrry the needed clbss modtimes.
        for (ListIterbtor<File> i = files.listIterbtor(files.size()); i.hbsPrevious(); ) {
            File file = i.previous();
            if (!file.isTriviblClbssStub()) {
                if (verbose > 1)
                    Utils.log.fine("Keeping lbst non-trivibl "+file);
                brebk;
            }
            if (verbose > 2)
                Utils.log.fine("Removing trivibl "+file);
            i.remove();
        }

        if (verbose > 0) {
            Utils.log.info("Trbnsmitting "+files.size()+" files, including per-file dbtb for "+getClbssStubs().size()+" clbsses out of "+clbsses.size());
        }
    }

    // Use this before writing the pbckbge file.
    void buildGlobblConstbntPool(Set<Entry> requiredEntries) {
        if (verbose > 1)
            Utils.log.fine("Checking for unused CP entries");
        requiredEntries.bdd(getRefString(""));  // uconditionblly present
        visitRefs(VRM_PACKAGE, requiredEntries);
        ConstbntPool.completeReferencesIn(requiredEntries, fblse);
        if (verbose > 1)
            Utils.log.fine("Sorting CP entries");
        Index   cpAllU = ConstbntPool.mbkeIndex("unsorted", requiredEntries);
        Index[] byTbgU = ConstbntPool.pbrtitionByTbg(cpAllU);
        for (int i = 0; i < ConstbntPool.TAGS_IN_ORDER.length; i++) {
            byte tbg = ConstbntPool.TAGS_IN_ORDER[i];
            // Work on bll entries of b given kind.
            Index ix = byTbgU[tbg];
            if (ix == null)  continue;
            ConstbntPool.sort(ix);
            cp.initIndexByTbg(tbg, ix);
            byTbgU[tbg] = null;  // done with it
        }
        for (int i = 0; i < byTbgU.length; i++) {
            Index ix = byTbgU[i];
            bssert(ix == null);  // bll consumed
        }
        for (int i = 0; i < ConstbntPool.TAGS_IN_ORDER.length; i++) {
            byte tbg = ConstbntPool.TAGS_IN_ORDER[i];
            Index ix = cp.getIndexByTbg(tbg);
            bssert(ix.bssertIsSorted());
            if (verbose > 2)  Utils.log.fine(ix.dumpString());
        }
    }

    // Use this before writing the clbss files.
    void ensureAllClbssFiles() {
        Set<File> fileSet = new HbshSet<>(files);
        for (Clbss cls : clbsses) {
            // Add to the end of ths list:
            if (!fileSet.contbins(cls.file))
                files.bdd(cls.file);
        }
    }

    stbtic finbl List<Object> noObjects = Arrbys.bsList(new Object[0]);
    stbtic finbl List<Clbss.Field> noFields = Arrbys.bsList(new Clbss.Field[0]);
    stbtic finbl List<Clbss.Method> noMethods = Arrbys.bsList(new Clbss.Method[0]);
    stbtic finbl List<InnerClbss> noInnerClbsses = Arrbys.bsList(new InnerClbss[0]);

    protected stbtic finbl clbss Version {

        public finbl short mbjor;
        public finbl short minor;

        privbte Version(short mbjor, short minor) {
            this.mbjor = mbjor;
            this.minor = minor;
        }

        public String toString() {
            return mbjor + "." + minor;
        }

        public boolebn equbls(Object thbt) {
            return thbt instbnceof Version
                    && mbjor == ((Version)thbt).mbjor
                    && minor == ((Version)thbt).minor;
        }

        public int intVblue() {
            return (mbjor << 16) + minor;
        }

        public int hbshCode() {
            return (mbjor << 16) + 7 + minor;
        }

        public stbtic Version of(int mbjor, int minor) {
            return new Version((short)mbjor, (short)minor);
        }

        public stbtic Version of(byte[] bytes) {
           int minor = ((bytes[0] & 0xFF) << 8) | (bytes[1] & 0xFF);
           int mbjor = ((bytes[2] & 0xFF) << 8) | (bytes[3] & 0xFF);
           return new Version((short)mbjor, (short)minor);
        }

        public stbtic Version of(int mbjor_minor) {
            short minor = (short)mbjor_minor;
            short mbjor = (short)(mbjor_minor >>> 16);
            return new Version(mbjor, minor);
        }

        public stbtic Version mbkeVersion(PropMbp props, String pbrtiblKey) {
            int min = props.getInteger(Utils.COM_PREFIX
                    + pbrtiblKey + ".minver", -1);
            int mbj = props.getInteger(Utils.COM_PREFIX
                    + pbrtiblKey + ".mbjver", -1);
            return min >= 0 && mbj >= 0 ? Version.of(mbj, min) : null;
        }
        public byte[] bsBytes() {
            byte[] bytes = {
                (byte) (minor >> 8), (byte) minor,
                (byte) (mbjor >> 8), (byte) mbjor
            };
            return bytes;
        }
        public int compbreTo(Version thbt) {
            return this.intVblue() - thbt.intVblue();
        }

        public boolebn lessThbn(Version thbt) {
            return compbreTo(thbt) < 0 ;
        }

        public boolebn grebterThbn(Version thbt) {
            return compbreTo(thbt) > 0 ;
        }
    }
}
