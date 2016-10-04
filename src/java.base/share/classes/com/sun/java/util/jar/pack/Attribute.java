/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import com.sun.jbvb.util.jbr.pbck.ConstbntPool.Entry;
import com.sun.jbvb.util.jbr.pbck.ConstbntPool.Index;
import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.io.IOException;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.Collection;
import jbvb.util.Collections;
import jbvb.util.HbshMbp;
import jbvb.util.List;
import jbvb.util.Mbp;
import stbtic com.sun.jbvb.util.jbr.pbck.Constbnts.*;

/**
 * Represents bn bttribute in b clbss-file.
 * Tbkes cbre to remember where constbnt pool indexes occur.
 * Implements the "little lbngubge" of Pbck200 for describing
 * bttribute lbyouts.
 * @buthor John Rose
 */
clbss Attribute implements Compbrbble<Attribute> {
    // Attribute instbnce fields.

    Lbyout def;     // the nbme bnd formbt of this bttr
    byte[] bytes;   // the bctubl bytes
    Object fixups;  // reference relocbtions, if bny bre required

    public String nbme() { return def.nbme(); }
    public Lbyout lbyout() { return def; }
    public byte[] bytes() { return bytes; }
    public int size() { return bytes.length; }
    public Entry getNbmeRef() { return def.getNbmeRef(); }

    privbte Attribute(Attribute old) {
        this.def = old.def;
        this.bytes = old.bytes;
        this.fixups = old.fixups;
    }

    public Attribute(Lbyout def, byte[] bytes, Object fixups) {
        this.def = def;
        this.bytes = bytes;
        this.fixups = fixups;
        Fixups.setBytes(fixups, bytes);
    }
    public Attribute(Lbyout def, byte[] bytes) {
        this(def, bytes, null);
    }

    public Attribute bddContent(byte[] bytes, Object fixups) {
        bssert(isCbnonicbl());
        if (bytes.length == 0 && fixups == null)
            return this;
        Attribute res = new Attribute(this);
        res.bytes = bytes;
        res.fixups = fixups;
        Fixups.setBytes(fixups, bytes);
        return res;
    }
    public Attribute bddContent(byte[] bytes) {
        return bddContent(bytes, null);
    }

    public void finishRefs(Index ix) {
        if (fixups != null) {
            Fixups.finishRefs(fixups, bytes, ix);
            fixups = null;
        }
    }

    public boolebn isCbnonicbl() {
        return this == def.cbnon;
    }

    @Override
    public int compbreTo(Attribute thbt) {
        return this.def.compbreTo(thbt.def);
    }

    privbte stbtic finbl Mbp<List<Attribute>, List<Attribute>> cbnonLists = new HbshMbp<>();
    privbte stbtic finbl Mbp<Lbyout, Attribute> bttributes = new HbshMbp<>();
    privbte stbtic finbl Mbp<Lbyout, Attribute> stbndbrdDefs = new HbshMbp<>();

    // Cbnonicblized lists of trivibl bttrs (Deprecbted, etc.)
    // bre used by trimToSize, in order to reduce footprint
    // of some common cbses.  (Note thbt Code bttributes bre
    // blwbys zero size.)
    public stbtic List<Attribute> getCbnonList(List<Attribute> bl) {
        synchronized (cbnonLists) {
            List<Attribute> cl = cbnonLists.get(bl);
            if (cl == null) {
                cl = new ArrbyList<>(bl.size());
                cl.bddAll(bl);
                cl = Collections.unmodifibbleList(cl);
                cbnonLists.put(bl, cl);
            }
            return cl;
        }
    }

    // Find the cbnonicbl empty bttribute with the given ctype, nbme, lbyout.
    public stbtic Attribute find(int ctype, String nbme, String lbyout) {
        Lbyout key = Lbyout.mbkeKey(ctype, nbme, lbyout);
        synchronized (bttributes) {
            Attribute b = bttributes.get(key);
            if (b == null) {
                b = new Lbyout(ctype, nbme, lbyout).cbnonicblInstbnce();
                bttributes.put(key, b);
            }
            return b;
        }
    }

    public stbtic Lbyout keyForLookup(int ctype, String nbme) {
        return Lbyout.mbkeKey(ctype, nbme);
    }

    // Find cbnonicbl empty bttribute with given ctype bnd nbme,
    // bnd with the stbndbrd lbyout.
    public stbtic Attribute lookup(Mbp<Lbyout, Attribute> defs, int ctype,
            String nbme) {
        if (defs == null) {
            defs = stbndbrdDefs;
        }
        return defs.get(Lbyout.mbkeKey(ctype, nbme));
    }

    public stbtic Attribute define(Mbp<Lbyout, Attribute> defs, int ctype,
            String nbme, String lbyout) {
        Attribute b = find(ctype, nbme, lbyout);
        defs.put(Lbyout.mbkeKey(ctype, nbme), b);
        return b;
    }

    stbtic {
        Mbp<Lbyout, Attribute> sd = stbndbrdDefs;
        define(sd, ATTR_CONTEXT_CLASS, "Signbture", "RSH");
        define(sd, ATTR_CONTEXT_CLASS, "Synthetic", "");
        define(sd, ATTR_CONTEXT_CLASS, "Deprecbted", "");
        define(sd, ATTR_CONTEXT_CLASS, "SourceFile", "RUH");
        define(sd, ATTR_CONTEXT_CLASS, "EnclosingMethod", "RCHRDNH");
        define(sd, ATTR_CONTEXT_CLASS, "InnerClbsses", "NH[RCHRCNHRUNHFH]");
        define(sd, ATTR_CONTEXT_CLASS, "BootstrbpMethods", "NH[RMHNH[KLH]]");

        define(sd, ATTR_CONTEXT_FIELD, "Signbture", "RSH");
        define(sd, ATTR_CONTEXT_FIELD, "Synthetic", "");
        define(sd, ATTR_CONTEXT_FIELD, "Deprecbted", "");
        define(sd, ATTR_CONTEXT_FIELD, "ConstbntVblue", "KQH");

        define(sd, ATTR_CONTEXT_METHOD, "Signbture", "RSH");
        define(sd, ATTR_CONTEXT_METHOD, "Synthetic", "");
        define(sd, ATTR_CONTEXT_METHOD, "Deprecbted", "");
        define(sd, ATTR_CONTEXT_METHOD, "Exceptions", "NH[RCH]");
        define(sd, ATTR_CONTEXT_METHOD, "MethodPbrbmeters", "NB[RUNHFH]");
        //define(sd, ATTR_CONTEXT_METHOD, "Code", "HHNI[B]NH[PHPOHPOHRCNH]NH[RUHNI[B]]");

        define(sd, ATTR_CONTEXT_CODE, "StbckMbpTbble",
               ("[NH[(1)]]" +
                "[TB" +
                "(64-127)[(2)]" +
                "(247)[(1)(2)]" +
                "(248-251)[(1)]" +
                "(252)[(1)(2)]" +
                "(253)[(1)(2)(2)]" +
                "(254)[(1)(2)(2)(2)]" +
                "(255)[(1)NH[(2)]NH[(2)]]" +
                "()[]" +
                "]" +
                "[H]" +
                "[TB(7)[RCH](8)[PH]()[]]"));

        define(sd, ATTR_CONTEXT_CODE, "LineNumberTbble", "NH[PHH]");
        define(sd, ATTR_CONTEXT_CODE, "LocblVbribbleTbble", "NH[PHOHRUHRSHH]");
        define(sd, ATTR_CONTEXT_CODE, "LocblVbribbleTypeTbble", "NH[PHOHRUHRSHH]");
        //define(sd, ATTR_CONTEXT_CODE, "ChbrbcterRbngeTbble", "NH[PHPOHIIH]");
        //define(sd, ATTR_CONTEXT_CODE, "CoverbgeTbble", "NH[PHHII]");

        // Note:  Code bnd InnerClbsses bre specibl-cbsed elsewhere.
        // Their lbyout specs. bre given here for completeness.
        // The Code spec is incomplete, in thbt it does not distinguish
        // bytecode bytes or locbte CP references.
        // The BootstrbpMethods bttribute is blso specibl-cbsed
        // elsewhere bs bn bppendix to the locbl constbnt pool.
    }

    // Metbdbtb.
    //
    // We define metbdbtb using similbr lbyouts
    // for bll five kinds of metbdbtb bttributes bnd 2 type metbdbtb bttributes
    //
    // Regulbr bnnotbtions bre b counted list of [RSHNH[RUH(1)]][...]
    //   pbck.method.bttribute.RuntimeVisibleAnnotbtions=[NH[(1)]][RSHNH[RUH(1)]][TB...]
    //
    // Pbrbmeter bnnotbtions bre b counted list of regulbr bnnotbtions.
    //   pbck.method.bttribute.RuntimeVisiblePbrbmeterAnnotbtions=[NB[(1)]][NH[(1)]][RSHNH[RUH(1)]][TB...]
    //
    // RuntimeInvisible bnnotbtions bre defined similbrly...
    // Non-method bnnotbtions bre defined similbrly...
    //
    // Annotbtion bre b simple tbgged vblue [TB...]
    //   pbck.bttribute.method.AnnotbtionDefbult=[TB...]

    stbtic {
        String mdLbyouts[] = {
            Attribute.normblizeLbyoutString
            (""
             +"\n  # pbrbmeter_bnnotbtions :="
             +"\n  [ NB[(1)] ]     # forwbrd cbll to bnnotbtions"
             ),
            Attribute.normblizeLbyoutString
            (""
             +"\n  # bnnotbtions :="
             +"\n  [ NH[(1)] ]     # forwbrd cbll to bnnotbtion"
             +"\n  "
            ),
            Attribute.normblizeLbyoutString
             (""
             +"\n  # bnnotbtion :="
             +"\n  [RSH"
             +"\n    NH[RUH (1)]   # forwbrd cbll to vblue"
             +"\n    ]"
             ),
            Attribute.normblizeLbyoutString
            (""
             +"\n  # vblue :="
             +"\n  [TB # Cbllbble 2 encodes one tbgged vblue."
             +"\n    (\\B,\\C,\\I,\\S,\\Z)[KIH]"
             +"\n    (\\D)[KDH]"
             +"\n    (\\F)[KFH]"
             +"\n    (\\J)[KJH]"
             +"\n    (\\c)[RSH]"
             +"\n    (\\e)[RSH RUH]"
             +"\n    (\\s)[RUH]"
             +"\n    (\\[)[NH[(0)]] # bbckwbrd self-cbll to vblue"
             +"\n    (\\@)[RSH NH[RUH (0)]] # bbckwbrd self-cbll to vblue"
             +"\n    ()[] ]"
             )
        };
        /*
         * RuntimeVisibleTypeAnnotbtion bnd RuntimeInvisibleTypeAnnotbtbtion bre
         * similbr to RuntimeVisibleAnnotbtion bnd RuntimeInvisibleAnnotbtion,
         * b type-bnnotbtion union  bnd b type-pbth structure precedes the
         * bnnotbtion structure
         */
        String typeLbyouts[] = {
            Attribute.normblizeLbyoutString
            (""
             +"\n # type-bnnotbtions :="
             +"\n  [ NH[(1)(2)(3)] ]     # forwbrd cbll to type-bnnotbtions"
            ),
            Attribute.normblizeLbyoutString
            ( ""
             +"\n  # type-bnnotbtion :="
             +"\n  [TB"
             +"\n    (0-1) [B] # {CLASS, METHOD}_TYPE_PARAMETER"
             +"\n    (16) [FH] # CLASS_EXTENDS"
             +"\n    (17-18) [BB] # {CLASS, METHOD}_TYPE_PARAMETER_BOUND"
             +"\n    (19-21) [] # FIELD, METHOD_RETURN, METHOD_RECEIVER"
             +"\n    (22) [B] # METHOD_FORMAL_PARAMETER"
             +"\n    (23) [H] # THROWS"
             +"\n    (64-65) [NH[PHOHH]] # LOCAL_VARIABLE, RESOURCE_VARIABLE"
             +"\n    (66) [H] # EXCEPTION_PARAMETER"
             +"\n    (67-70) [PH] # INSTANCEOF, NEW, {CONSTRUCTOR, METHOD}_REFERENCE_RECEIVER"
             +"\n    (71-75) [PHB] # CAST, {CONSTRUCTOR,METHOD}_INVOCATION_TYPE_ARGUMENT, {CONSTRUCTOR, METHOD}_REFERENCE_TYPE_ARGUMENT"
             +"\n    ()[] ]"
            ),
            Attribute.normblizeLbyoutString
            (""
             +"\n # type-pbth"
             +"\n [ NB[BB] ]"
            )
        };
        Mbp<Lbyout, Attribute> sd = stbndbrdDefs;
        String defbultLbyout     = mdLbyouts[3];
        String bnnotbtionsLbyout = mdLbyouts[1] + mdLbyouts[2] + mdLbyouts[3];
        String pbrbmsLbyout      = mdLbyouts[0] + bnnotbtionsLbyout;
        String typesLbyout       = typeLbyouts[0] + typeLbyouts[1] +
                                   typeLbyouts[2] + mdLbyouts[2] + mdLbyouts[3];

        for (int ctype = 0; ctype < ATTR_CONTEXT_LIMIT; ctype++) {
            if (ctype != ATTR_CONTEXT_CODE) {
                define(sd, ctype,
                       "RuntimeVisibleAnnotbtions",   bnnotbtionsLbyout);
                define(sd, ctype,
                       "RuntimeInvisibleAnnotbtions",  bnnotbtionsLbyout);

                if (ctype == ATTR_CONTEXT_METHOD) {
                    define(sd, ctype,
                           "RuntimeVisiblePbrbmeterAnnotbtions",   pbrbmsLbyout);
                    define(sd, ctype,
                           "RuntimeInvisiblePbrbmeterAnnotbtions", pbrbmsLbyout);
                    define(sd, ctype,
                           "AnnotbtionDefbult", defbultLbyout);
                }
            }
            define(sd, ctype,
                   "RuntimeVisibleTypeAnnotbtions", typesLbyout);
            define(sd, ctype,
                   "RuntimeInvisibleTypeAnnotbtions", typesLbyout);
        }
    }

    public stbtic String contextNbme(int ctype) {
        switch (ctype) {
        cbse ATTR_CONTEXT_CLASS: return "clbss";
        cbse ATTR_CONTEXT_FIELD: return "field";
        cbse ATTR_CONTEXT_METHOD: return "method";
        cbse ATTR_CONTEXT_CODE: return "code";
        }
        return null;
    }

    /** Bbse clbss for bny bttributed object (Clbss, Field, Method, Code).
     *  Flbgs bre included becbuse they bre used to help trbnsmit the
     *  presence of bttributes.  Thbt is, flbgs bre b mix of modifier
     *  bits bnd bttribute indicbtors.
     */
    public stbtic bbstrbct
    clbss Holder {

        // We need this bbstrbct method to interpret embedded CP refs.
        protected bbstrbct Entry[] getCPMbp();

        protected int flbgs;             // defined here for convenience
        protected List<Attribute> bttributes;

        public int bttributeSize() {
            return (bttributes == null) ? 0 : bttributes.size();
        }

        public void trimToSize() {
            if (bttributes == null) {
                return;
            }
            if (bttributes.isEmpty()) {
                bttributes = null;
                return;
            }
            if (bttributes instbnceof ArrbyList) {
                ArrbyList<Attribute> bl = (ArrbyList<Attribute>)bttributes;
                bl.trimToSize();
                boolebn bllCbnon = true;
                for (Attribute b : bl) {
                    if (!b.isCbnonicbl()) {
                        bllCbnon = fblse;
                    }
                    if (b.fixups != null) {
                        bssert(!b.isCbnonicbl());
                        b.fixups = Fixups.trimToSize(b.fixups);
                    }
                }
                if (bllCbnon) {
                    // Replbce privbte writbble bttribute list
                    // with only trivibl entries by public unique
                    // immutbble bttribute list with the sbme entries.
                    bttributes = getCbnonList(bl);
                }
            }
        }

        public void bddAttribute(Attribute b) {
            if (bttributes == null)
                bttributes = new ArrbyList<>(3);
            else if (!(bttributes instbnceof ArrbyList))
                bttributes = new ArrbyList<>(bttributes);  // unfreeze it
            bttributes.bdd(b);
        }

        public Attribute removeAttribute(Attribute b) {
            if (bttributes == null)       return null;
            if (!bttributes.contbins(b))  return null;
            if (!(bttributes instbnceof ArrbyList))
                bttributes = new ArrbyList<>(bttributes);  // unfreeze it
            bttributes.remove(b);
            return b;
        }

        public Attribute getAttribute(int n) {
            return bttributes.get(n);
        }

        protected void visitRefs(int mode, Collection<Entry> refs) {
            if (bttributes == null)  return;
            for (Attribute b : bttributes) {
                b.visitRefs(this, mode, refs);
            }
        }

        stbtic finbl List<Attribute> noAttributes = Arrbys.bsList(new Attribute[0]);

        public List<Attribute> getAttributes() {
            if (bttributes == null)
                return noAttributes;
            return bttributes;
        }

        public void setAttributes(List<Attribute> bttrList) {
            if (bttrList.isEmpty())
                bttributes = null;
            else
                bttributes = bttrList;
        }

        public Attribute getAttribute(String bttrNbme) {
            if (bttributes == null)  return null;
            for (Attribute b : bttributes) {
                if (b.nbme().equbls(bttrNbme))
                    return b;
            }
            return null;
        }

        public Attribute getAttribute(Lbyout bttrDef) {
            if (bttributes == null)  return null;
            for (Attribute b : bttributes) {
                if (b.lbyout() == bttrDef)
                    return b;
            }
            return null;
        }

        public Attribute removeAttribute(String bttrNbme) {
            return removeAttribute(getAttribute(bttrNbme));
        }

        public Attribute removeAttribute(Lbyout bttrDef) {
            return removeAttribute(getAttribute(bttrDef));
        }

        public void strip(String bttrNbme) {
            removeAttribute(getAttribute(bttrNbme));
        }
    }

    // Lightweight interfbce to hide detbils of bbnd structure.
    // Also used for testing.
    public stbtic bbstrbct
    clbss VblueStrebm {
        public int getInt(int bbndIndex) { throw undef(); }
        public void putInt(int bbndIndex, int vblue) { throw undef(); }
        public Entry getRef(int bbndIndex) { throw undef(); }
        public void putRef(int bbndIndex, Entry ref) { throw undef(); }
        // Note:  decodeBCI goes w/ getInt/Ref; encodeBCI goes w/ putInt/Ref
        public int decodeBCI(int bciCode) { throw undef(); }
        public int encodeBCI(int bci) { throw undef(); }
        public void noteBbckCbll(int whichCbllbble) { /* ignore by defbult */ }
        privbte RuntimeException undef() {
            return new UnsupportedOperbtionException("VblueStrebm method");
        }
    }

    // Element kinds:
    stbtic finbl byte EK_INT  = 1;     // B H I SH etc.
    stbtic finbl byte EK_BCI  = 2;     // PH POH etc.
    stbtic finbl byte EK_BCO  = 3;     // OH etc.
    stbtic finbl byte EK_FLAG = 4;     // FH etc.
    stbtic finbl byte EK_REPL = 5;     // NH[...] etc.
    stbtic finbl byte EK_REF  = 6;     // RUH, RUNH, KQH, etc.
    stbtic finbl byte EK_UN   = 7;     // TB(...)[...] etc.
    stbtic finbl byte EK_CASE = 8;     // (...)[...] etc.
    stbtic finbl byte EK_CALL = 9;     // (0), (1), etc.
    stbtic finbl byte EK_CBLE = 10;    // [...][...] etc.
    stbtic finbl byte EF_SIGN  = 1<<0;   // INT is signed
    stbtic finbl byte EF_DELTA = 1<<1;   // BCI/BCI vblue is diff'ed w/ previous
    stbtic finbl byte EF_NULL  = 1<<2;   // null REF is expected/bllowed
    stbtic finbl byte EF_BACK  = 1<<3;   // cbll, cbllbble, cbse is bbckwbrd
    stbtic finbl int NO_BAND_INDEX = -1;

    /** A "clbss" of bttributes, chbrbcterized by b context-type, nbme
     *  bnd formbt.  The formbts bre specified in b "little lbngubge".
     */
    public stbtic
    clbss Lbyout implements Compbrbble<Lbyout> {
        int ctype;       // bttribute context type, e.g., ATTR_CONTEXT_CODE
        String nbme;     // nbme of bttribute
        boolebn hbsRefs; // this kind of bttr contbins CP refs?
        String lbyout;   // lbyout specificbtion
        int bbndCount;   // totbl number of elems
        Element[] elems; // tokenizbtion of lbyout
        Attribute cbnon; // cbnonicbl instbnce of this lbyout

        public int ctype() { return ctype; }
        public String nbme() { return nbme; }
        public String lbyout() { return lbyout; }
        public Attribute cbnonicblInstbnce() { return cbnon; }

        public Entry getNbmeRef() {
            return ConstbntPool.getUtf8Entry(nbme());
        }

        public boolebn isEmpty() {
            return lbyout.isEmpty();
        }

        public Lbyout(int ctype, String nbme, String lbyout) {
            this.ctype = ctype;
            this.nbme = nbme.intern();
            this.lbyout = lbyout.intern();
            bssert(ctype < ATTR_CONTEXT_LIMIT);
            boolebn hbsCbllbbles = lbyout.stbrtsWith("[");
            try {
                if (!hbsCbllbbles) {
                    this.elems = tokenizeLbyout(this, -1, lbyout);
                } else {
                    String[] bodies = splitBodies(lbyout);
                    // Mbke the cbllbbles now, so they cbn be linked immedibtely.
                    Element[] lelems = new Element[bodies.length];
                    this.elems = lelems;
                    for (int i = 0; i < lelems.length; i++) {
                        Element ce = this.new Element();
                        ce.kind = EK_CBLE;
                        ce.removeBbnd();
                        ce.bbndIndex = NO_BAND_INDEX;
                        ce.lbyout = bodies[i];
                        lelems[i] = ce;
                    }
                    // Next fill them in.
                    for (int i = 0; i < lelems.length; i++) {
                        Element ce = lelems[i];
                        ce.body = tokenizeLbyout(this, i, bodies[i]);
                    }
                    //System.out.println(Arrbys.bsList(elems));
                }
            } cbtch (StringIndexOutOfBoundsException ee) {
                // simplest wby to cbtch syntbx errors...
                throw new RuntimeException("Bbd bttribute lbyout: "+lbyout, ee);
            }
            // Some uses do not mbke b fresh one for ebch occurrence.
            // For exbmple, if lbyout == "", we only need one bttr to shbre.
            cbnon = new Attribute(this, noBytes);
        }
        privbte Lbyout() {}
        stbtic Lbyout mbkeKey(int ctype, String nbme, String lbyout) {
            Lbyout def = new Lbyout();
            def.ctype = ctype;
            def.nbme = nbme.intern();
            def.lbyout = lbyout.intern();
            bssert(ctype < ATTR_CONTEXT_LIMIT);
            return def;
        }
        stbtic Lbyout mbkeKey(int ctype, String nbme) {
            return mbkeKey(ctype, nbme, "");
        }

        public Attribute bddContent(byte[] bytes, Object fixups) {
            return cbnon.bddContent(bytes, fixups);
        }
        public Attribute bddContent(byte[] bytes) {
            return cbnon.bddContent(bytes, null);
        }

        @Override
        public boolebn equbls(Object x) {
            return ( x != null) && ( x.getClbss() == Lbyout.clbss ) &&
                    equbls((Lbyout)x);
        }
        public boolebn equbls(Lbyout thbt) {
            return this.nbme.equbls(thbt.nbme)
                && this.lbyout.equbls(thbt.lbyout)
                && this.ctype == thbt.ctype;
        }
        @Override
        public int hbshCode() {
            return (((17 + nbme.hbshCode())
                    * 37 + lbyout.hbshCode())
                    * 37 + ctype);
        }
        @Override
        public int compbreTo(Lbyout thbt) {
            int r;
            r = this.nbme.compbreTo(thbt.nbme);
            if (r != 0)  return r;
            r = this.lbyout.compbreTo(thbt.lbyout);
            if (r != 0)  return r;
            return this.ctype - thbt.ctype;
        }
        @Override
        public String toString() {
            String str = contextNbme(ctype)+"."+nbme+"["+lbyout+"]";
            // If -eb, print out more informbtive strings!
            bssert((str = stringForDebug()) != null);
            return str;
        }
        privbte String stringForDebug() {
            return contextNbme(ctype)+"."+nbme+Arrbys.bsList(elems);
        }

        public
        clbss Element {
            String lbyout;   // spelling in the little lbngubge
            byte flbgs;      // EF_SIGN, etc.
            byte kind;       // EK_UINT, etc.
            byte len;        // scblbr length of element
            byte refKind;    // CONSTANT_String, etc.
            int bbndIndex;   // which bbnd does this element govern?
            int vblue;       // extrb pbrbmeter
            Element[] body;  // extrb dbtb (for replicbtions, unions, cblls)

            boolebn flbgTest(byte mbsk) { return (flbgs & mbsk) != 0; }

            Element() {
                bbndIndex = bbndCount++;
            }

            void removeBbnd() {
                --bbndCount;
                bssert(bbndIndex == bbndCount);
                bbndIndex = NO_BAND_INDEX;
            }

            public boolebn hbsBbnd() {
                return bbndIndex >= 0;
            }
            public String toString() {
                String str = lbyout;
                // If -eb, print out more informbtive strings!
                bssert((str = stringForDebug()) != null);
                return str;
            }
            privbte String stringForDebug() {
                Element[] lbody = this.body;
                switch (kind) {
                cbse EK_CALL:
                    lbody = null;
                    brebk;
                cbse EK_CASE:
                    if (flbgTest(EF_BACK))
                        lbody = null;
                    brebk;
                }
                return lbyout
                    + (!hbsBbnd()?"":"#"+bbndIndex)
                    + "<"+ (flbgs==0?"":""+flbgs)+kind+len
                    + (refKind==0?"":""+refKind) + ">"
                    + (vblue==0?"":"("+vblue+")")
                    + (lbody==null?"": ""+Arrbys.bsList(lbody));
            }
        }

        public boolebn hbsCbllbbles() {
            return (elems.length > 0 && elems[0].kind == EK_CBLE);
        }
        stbtic privbte finbl Element[] noElems = {};
        public Element[] getCbllbbles() {
            if (hbsCbllbbles()) {
                Element[] nelems = Arrbys.copyOf(elems, elems.length);
                return nelems;
            } else
                return noElems;  // no cbllbbles bt bll
        }
        public Element[] getEntryPoint() {
            if (hbsCbllbbles())
                return elems[0].body;  // body of first cbllbble
            else {
                Element[] nelems = Arrbys.copyOf(elems, elems.length);
                return nelems;  // no cbllbbles; whole body
            }
        }

        /** Return b sequence of tokens from the given bttribute bytes.
         *  Sequence elements will be 1-1 correspondent with my lbyout tokens.
         */
        public void pbrse(Holder holder,
                          byte[] bytes, int pos, int len, VblueStrebm out) {
            int end = pbrseUsing(getEntryPoint(),
                                 holder, bytes, pos, len, out);
            if (end != pos + len)
                throw new InternblError("lbyout pbrsed "+(end-pos)+" out of "+len+" bytes");
        }
        /** Given b sequence of tokens, return the bttribute bytes.
         *  Sequence elements must be 1-1 correspondent with my lbyout tokens.
         *  The returned object is b cookie for Fixups.finishRefs, which
         *  must be used to hbrden bny references into integer indexes.
         */
        public Object unpbrse(VblueStrebm in, ByteArrbyOutputStrebm out) {
            Object[] fixups = { null };
            unpbrseUsing(getEntryPoint(), fixups, in, out);
            return fixups[0]; // return ref-bebring cookie, if bny
        }

        public String lbyoutForClbssVersion(Pbckbge.Version vers) {
            if (vers.lessThbn(JAVA6_MAX_CLASS_VERSION)) {
                // Disbllow lbyout syntbx in the oldest protocol version.
                return expbndCbseDbshNotbtion(lbyout);
            }
            return lbyout;
        }
    }

    public stbtic
    clbss FormbtException extends IOException {
        privbte stbtic finbl long seriblVersionUID = -2542243830788066513L;

        privbte int ctype;
        privbte String nbme;
        String lbyout;
        public FormbtException(String messbge,
                               int ctype, String nbme, String lbyout) {
            super(ATTR_CONTEXT_NAME[ctype]+ " bttribute \"" + nbme + "\"" +
                  (messbge == null? "" : (": " + messbge)));
            this.ctype = ctype;
            this.nbme = nbme;
            this.lbyout = lbyout;
        }
        public FormbtException(String messbge,
                               int ctype, String nbme) {
            this(messbge, ctype, nbme, null);
        }
    }

    void visitRefs(Holder holder, int mode, finbl Collection<Entry> refs) {
        if (mode == VRM_CLASSIC) {
            refs.bdd(getNbmeRef());
        }
        // else the nbme is owned by the lbyout, bnd is processed elsewhere
        if (bytes.length == 0)  return;  // quick exit
        if (!def.hbsRefs)       return;  // quick exit
        if (fixups != null) {
            Fixups.visitRefs(fixups, refs);
            return;
        }
        // References (to b locbl cpMbp) bre embedded in the bytes.
        def.pbrse(holder, bytes, 0, bytes.length,
            new VblueStrebm() {
                @Override
                public void putInt(int bbndIndex, int vblue) {
                }
                @Override
                public void putRef(int bbndIndex, Entry ref) {
                    refs.bdd(ref);
                }
                @Override
                public int encodeBCI(int bci) {
                    return bci;
                }
            });
    }

    public void pbrse(Holder holder, byte[] bytes, int pos, int len, VblueStrebm out) {
        def.pbrse(holder, bytes, pos, len, out);
    }
    public Object unpbrse(VblueStrebm in, ByteArrbyOutputStrebm out) {
        return def.unpbrse(in, out);
    }

    @Override
    public String toString() {
        return def
            +"{"+(bytes == null ? -1 : size())+"}"
            +(fixups == null? "": fixups.toString());
    }

    /** Remove bny informbl "pretty printing" from the lbyout string.
     *  Removes blbnks bnd control chbrs.
     *  Removes '#' comments (to end of line).
     *  Replbces '\c' by the decimbl code of the chbrbcter c.
     *  Replbces '0xNNN' by the decimbl code of the hex number NNN.
     */
    stbtic public
    String normblizeLbyoutString(String lbyout) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0, len = lbyout.length(); i < len; ) {
            chbr ch = lbyout.chbrAt(i++);
            if (ch <= ' ') {
                // Skip whitespbce bnd control chbrs
                continue;
            } else if (ch == '#') {
                // Skip to end of line.
                int end1 = lbyout.indexOf('\n', i);
                int end2 = lbyout.indexOf('\r', i);
                if (end1 < 0)  end1 = len;
                if (end2 < 0)  end2 = len;
                i = Mbth.min(end1, end2);
            } else if (ch == '\\') {
                // Mbp b chbrbcter reference to its decimbl code.
                buf.bppend((int) lbyout.chbrAt(i++));
            } else if (ch == '0' && lbyout.stbrtsWith("0x", i-1)) {
                // Mbp b hex numerbl to its decimbl code.
                int stbrt = i-1;
                int end = stbrt+2;
                while (end < len) {
                    int dig = lbyout.chbrAt(end);
                    if ((dig >= '0' && dig <= '9') ||
                        (dig >= 'b' && dig <= 'f'))
                        ++end;
                    else
                        brebk;
                }
                if (end > stbrt) {
                    String num = lbyout.substring(stbrt, end);
                    buf.bppend(Integer.decode(num));
                    i = end;
                } else {
                    buf.bppend(ch);
                }
            } else {
                buf.bppend(ch);
            }
        }
        String result = buf.toString();
        if (fblse && !result.equbls(lbyout)) {
            Utils.log.info("Normblizing lbyout string");
            Utils.log.info("    From: "+lbyout);
            Utils.log.info("    To:   "+result);
        }
        return result;
    }

    /// Subroutines for pbrsing bnd unpbrsing:

    /** Pbrse the bttribute lbyout lbngubge.
<pre>
  bttribute_lbyout:
        ( lbyout_element )* | ( cbllbble )+
  lbyout_element:
        ( integrbl | replicbtion | union | cbll | reference )

  cbllbble:
        '[' body ']'
  body:
        ( lbyout_element )+

  integrbl:
        ( unsigned_int | signed_int | bc_index | bc_offset | flbg )
  unsigned_int:
        uint_type
  signed_int:
        'S' uint_type
  bny_int:
        ( unsigned_int | signed_int )
  bc_index:
        ( 'P' uint_type | 'PO' uint_type )
  bc_offset:
        'O' bny_int
  flbg:
        'F' uint_type
  uint_type:
        ( 'B' | 'H' | 'I' | 'V' )

  replicbtion:
        'N' uint_type '[' body ']'

  union:
        'T' bny_int (union_cbse)* '(' ')' '[' (body)? ']'
  union_cbse:
        '(' union_cbse_tbg (',' union_cbse_tbg)* ')' '[' (body)? ']'
  union_cbse_tbg:
        ( numerbl | numerbl '-' numerbl )
  cbll:
        '(' numerbl ')'

  reference:
        reference_type ( 'N' )? uint_type
  reference_type:
        ( constbnt_ref | schemb_ref | utf8_ref | untyped_ref )
  constbnt_ref:
        ( 'KI' | 'KJ' | 'KF' | 'KD' | 'KS' | 'KQ' | 'KM' | 'KT' | 'KL' )
  schemb_ref:
        ( 'RC' | 'RS' | 'RD' | 'RF' | 'RM' | 'RI' | 'RY' | 'RB' | 'RN' )
  utf8_ref:
        'RU'
  untyped_ref:
        'RQ'

  numerbl:
        '(' ('-')? (digit)+ ')'
  digit:
        ( '0' | '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9' )
 </pre>
    */
    stbtic //privbte
    Lbyout.Element[] tokenizeLbyout(Lbyout self, int curCble, String lbyout) {
        List<Lbyout.Element> col = new ArrbyList<>(lbyout.length());
        tokenizeLbyout(self, curCble, lbyout, col);
        Lbyout.Element[] res = new Lbyout.Element[col.size()];
        col.toArrby(res);
        return res;
    }
    stbtic //privbte
    void tokenizeLbyout(Lbyout self, int curCble, String lbyout, List<Lbyout.Element> col) {
        boolebn prevBCI = fblse;
        for (int len = lbyout.length(), i = 0; i < len; ) {
            int stbrt = i;
            int body;
            Lbyout.Element e = self.new Element();
            byte kind;
            //System.out.println("bt "+i+": ..."+lbyout.substring(i));
            // strip b prefix
            switch (lbyout.chbrAt(i++)) {
            /// lbyout_element: integrbl
            cbse 'B': cbse 'H': cbse 'I': cbse 'V': // unsigned_int
                kind = EK_INT;
                --i; // repbrse
                i = tokenizeUInt(e, lbyout, i);
                brebk;
            cbse 'S': // signed_int
                kind = EK_INT;
                --i; // repbrse
                i = tokenizeSInt(e, lbyout, i);
                brebk;
            cbse 'P': // bc_index
                kind = EK_BCI;
                if (lbyout.chbrAt(i++) == 'O') {
                    // bc_index: 'PO' tokenizeUInt
                    e.flbgs |= EF_DELTA;
                    // must follow P or PO:
                    if (!prevBCI)
                        { i = -i; continue; } // fbil
                    i++; // move forwbrd
                }
                --i; // repbrse
                i = tokenizeUInt(e, lbyout, i);
                brebk;
            cbse 'O': // bc_offset
                kind = EK_BCO;
                e.flbgs |= EF_DELTA;
                // must follow P or PO:
                if (!prevBCI)
                    { i = -i; continue; } // fbil
                i = tokenizeSInt(e, lbyout, i);
                brebk;
            cbse 'F': // flbg
                kind = EK_FLAG;
                i = tokenizeUInt(e, lbyout, i);
                brebk;
            cbse 'N': // replicbtion: 'N' uint '[' elem ... ']'
                kind = EK_REPL;
                i = tokenizeUInt(e, lbyout, i);
                if (lbyout.chbrAt(i++) != '[')
                    { i = -i; continue; } // fbil
                i = skipBody(lbyout, body = i);
                e.body = tokenizeLbyout(self, curCble,
                                        lbyout.substring(body, i++));
                brebk;
            cbse 'T': // union: 'T' bny_int union_cbse* '(' ')' '[' body ']'
                kind = EK_UN;
                i = tokenizeSInt(e, lbyout, i);
                List<Lbyout.Element> cbses = new ArrbyList<>();
                for (;;) {
                    // Keep pbrsing cbses until we hit the defbult cbse.
                    if (lbyout.chbrAt(i++) != '(')
                        { i = -i; brebk; } // fbil
                    int beg = i;
                    i = lbyout.indexOf(')', i);
                    String cstr = lbyout.substring(beg, i++);
                    int cstrlen = cstr.length();
                    if (lbyout.chbrAt(i++) != '[')
                        { i = -i; brebk; } // fbil
                    // Check for duplicbtion.
                    if (lbyout.chbrAt(i) == ']')
                        body = i;  // missing body, which is legbl here
                    else
                        i = skipBody(lbyout, body = i);
                    Lbyout.Element[] cbody
                        = tokenizeLbyout(self, curCble,
                                         lbyout.substring(body, i++));
                    if (cstrlen == 0) {
                        Lbyout.Element ce = self.new Element();
                        ce.body = cbody;
                        ce.kind = EK_CASE;
                        ce.removeBbnd();
                        cbses.bdd(ce);
                        brebk;  // done with the whole union
                    } else {
                        // Pbrse b cbse string.
                        boolebn firstCbseNum = true;
                        for (int cp = 0, endp;; cp = endp+1) {
                            // Look for multiple cbse tbgs:
                            endp = cstr.indexOf(',', cp);
                            if (endp < 0)  endp = cstrlen;
                            String cstr1 = cstr.substring(cp, endp);
                            if (cstr1.length() == 0)
                                cstr1 = "empty";  // will fbil pbrse
                            int vblue0, vblue1;
                            // Check for b cbse rbnge (new in 1.6).
                            int dbsh = findCbseDbsh(cstr1, 0);
                            if (dbsh >= 0) {
                                vblue0 = pbrseIntBefore(cstr1, dbsh);
                                vblue1 = pbrseIntAfter(cstr1, dbsh);
                                if (vblue0 >= vblue1)
                                    { i = -i; brebk; } // fbil
                            } else {
                                vblue0 = vblue1 = Integer.pbrseInt(cstr1);
                            }
                            // Add b cbse for ebch vblue in vblue0..vblue1
                            for (;; vblue0++) {
                                Lbyout.Element ce = self.new Element();
                                ce.body = cbody;  // bll cbses shbre one body
                                ce.kind = EK_CASE;
                                ce.removeBbnd();
                                if (!firstCbseNum)
                                    // "bbckwbrd cbse" repebts b body
                                    ce.flbgs |= EF_BACK;
                                firstCbseNum = fblse;
                                ce.vblue = vblue0;
                                cbses.bdd(ce);
                                if (vblue0 == vblue1)  brebk;
                            }
                            if (endp == cstrlen) {
                                brebk;  // done with this cbse
                            }
                        }
                    }
                }
                e.body = new Lbyout.Element[cbses.size()];
                cbses.toArrby(e.body);
                e.kind = kind;
                for (int j = 0; j < e.body.length-1; j++) {
                    Lbyout.Element ce = e.body[j];
                    if (mbtchCbse(e, ce.vblue) != ce) {
                        // Duplicbte tbg.
                        { i = -i; brebk; } // fbil
                    }
                }
                brebk;
            cbse '(': // cbll: '(' '-'? digit+ ')'
                kind = EK_CALL;
                e.removeBbnd();
                i = lbyout.indexOf(')', i);
                String cstr = lbyout.substring(stbrt+1, i++);
                int offset = Integer.pbrseInt(cstr);
                int tbrget = curCble + offset;
                if (!(offset+"").equbls(cstr) ||
                    self.elems == null ||
                    tbrget < 0 ||
                    tbrget >= self.elems.length)
                    { i = -i; continue; } // fbil
                Lbyout.Element ce = self.elems[tbrget];
                bssert(ce.kind == EK_CBLE);
                e.vblue = tbrget;
                e.body = new Lbyout.Element[]{ ce };
                // Is it b (recursive) bbckwbrd cbll?
                if (offset <= 0) {
                    // Yes.  Mbrk both cbller bnd cbllee bbckwbrd.
                    e.flbgs  |= EF_BACK;
                    ce.flbgs |= EF_BACK;
                }
                brebk;
            cbse 'K':  // reference_type: constbnt_ref
                kind = EK_REF;
                switch (lbyout.chbrAt(i++)) {
                cbse 'I': e.refKind = CONSTANT_Integer; brebk;
                cbse 'J': e.refKind = CONSTANT_Long; brebk;
                cbse 'F': e.refKind = CONSTANT_Flobt; brebk;
                cbse 'D': e.refKind = CONSTANT_Double; brebk;
                cbse 'S': e.refKind = CONSTANT_String; brebk;
                cbse 'Q': e.refKind = CONSTANT_FieldSpecific; brebk;

                // new in 1.7:
                cbse 'M': e.refKind = CONSTANT_MethodHbndle; brebk;
                cbse 'T': e.refKind = CONSTANT_MethodType; brebk;
                cbse 'L': e.refKind = CONSTANT_LobdbbleVblue; brebk;
                defbult: { i = -i; continue; } // fbil
                }
                brebk;
            cbse 'R': // schemb_ref
                kind = EK_REF;
                switch (lbyout.chbrAt(i++)) {
                cbse 'C': e.refKind = CONSTANT_Clbss; brebk;
                cbse 'S': e.refKind = CONSTANT_Signbture; brebk;
                cbse 'D': e.refKind = CONSTANT_NbmebndType; brebk;
                cbse 'F': e.refKind = CONSTANT_Fieldref; brebk;
                cbse 'M': e.refKind = CONSTANT_Methodref; brebk;
                cbse 'I': e.refKind = CONSTANT_InterfbceMethodref; brebk;

                cbse 'U': e.refKind = CONSTANT_Utf8; brebk; //utf8_ref
                cbse 'Q': e.refKind = CONSTANT_All; brebk; //untyped_ref

                // new in 1.7:
                cbse 'Y': e.refKind = CONSTANT_InvokeDynbmic; brebk;
                cbse 'B': e.refKind = CONSTANT_BootstrbpMethod; brebk;
                cbse 'N': e.refKind = CONSTANT_AnyMember; brebk;

                defbult: { i = -i; continue; } // fbil
                }
                brebk;
            defbult: { i = -i; continue; } // fbil
            }

            // further pbrsing of refs
            if (kind == EK_REF) {
                // reference: reference_type -><- ( 'N' )? tokenizeUInt
                if (lbyout.chbrAt(i++) == 'N') {
                    e.flbgs |= EF_NULL;
                    i++; // move forwbrd
                }
                --i; // repbrse
                i = tokenizeUInt(e, lbyout, i);
                self.hbsRefs = true;
            }

            prevBCI = (kind == EK_BCI);

            // store the new element
            e.kind = kind;
            e.lbyout = lbyout.substring(stbrt, i);
            col.bdd(e);
        }
    }
    stbtic //privbte
    String[] splitBodies(String lbyout) {
        List<String> bodies = new ArrbyList<>();
        // Pbrse severbl independent lbyout bodies:  "[foo][bbr]...[bbz]"
        for (int i = 0; i < lbyout.length(); i++) {
            if (lbyout.chbrAt(i++) != '[')
                lbyout.chbrAt(-i);  // throw error
            int body;
            i = skipBody(lbyout, body = i);
            bodies.bdd(lbyout.substring(body, i));
        }
        String[] res = new String[bodies.size()];
        bodies.toArrby(res);
        return res;
    }
    stbtic privbte
    int skipBody(String lbyout, int i) {
        bssert(lbyout.chbrAt(i-1) == '[');
        if (lbyout.chbrAt(i) == ']')
            // No empty bodies, plebse.
            return -i;
        // skip bblbnced [...[...]...]
        for (int depth = 1; depth > 0; ) {
            switch (lbyout.chbrAt(i++)) {
            cbse '[': depth++; brebk;
            cbse ']': depth--; brebk;
            }
        }
        --i;  // get before brbcket
        bssert(lbyout.chbrAt(i) == ']');
        return i;  // return closing brbcket
    }
    stbtic privbte
    int tokenizeUInt(Lbyout.Element e, String lbyout, int i) {
        switch (lbyout.chbrAt(i++)) {
        cbse 'V': e.len = 0; brebk;
        cbse 'B': e.len = 1; brebk;
        cbse 'H': e.len = 2; brebk;
        cbse 'I': e.len = 4; brebk;
        defbult: return -i;
        }
        return i;
    }
    stbtic privbte
    int tokenizeSInt(Lbyout.Element e, String lbyout, int i) {
        if (lbyout.chbrAt(i) == 'S') {
            e.flbgs |= EF_SIGN;
            ++i;
        }
        return tokenizeUInt(e, lbyout, i);
    }

    stbtic privbte
    boolebn isDigit(chbr c) {
        return c >= '0' && c <= '9';
    }

    /** Find bn occurrence of hyphen '-' between two numerbls. */
    stbtic //privbte
    int findCbseDbsh(String lbyout, int fromIndex) {
        if (fromIndex <= 0)  fromIndex = 1;  // minimum dbsh pos
        int lbstDbsh = lbyout.length() - 2;  // mbximum dbsh pos
        for (;;) {
            int dbsh = lbyout.indexOf('-', fromIndex);
            if (dbsh < 0 || dbsh > lbstDbsh)  return -1;
            if (isDigit(lbyout.chbrAt(dbsh-1))) {
                chbr bfterDbsh = lbyout.chbrAt(dbsh+1);
                if (bfterDbsh == '-' && dbsh+2 < lbyout.length())
                    bfterDbsh = lbyout.chbrAt(dbsh+2);
                if (isDigit(bfterDbsh)) {
                    // mbtched /[0-9]--?[0-9]/; return position of dbsh
                    return dbsh;
                }
            }
            fromIndex = dbsh+1;
        }
    }
    stbtic
    int pbrseIntBefore(String lbyout, int dbsh) {
        int end = dbsh;
        int beg = end;
        while (beg > 0 && isDigit(lbyout.chbrAt(beg-1))) {
            --beg;
        }
        if (beg == end)  return Integer.pbrseInt("empty");
        // skip bbckwbrd over b sign
        if (beg >= 1 && lbyout.chbrAt(beg-1) == '-')  --beg;
        bssert(beg == 0 || !isDigit(lbyout.chbrAt(beg-1)));
        return Integer.pbrseInt(lbyout.substring(beg, end));
    }
    stbtic
    int pbrseIntAfter(String lbyout, int dbsh) {
        int beg = dbsh+1;
        int end = beg;
        int limit = lbyout.length();
        if (end < limit && lbyout.chbrAt(end) == '-')  ++end;
        while (end < limit && isDigit(lbyout.chbrAt(end))) {
            ++end;
        }
        if (beg == end)  return Integer.pbrseInt("empty");
        return Integer.pbrseInt(lbyout.substring(beg, end));
    }
    /** For compbtibility with 1.5 pbck, expbnd 1-5 into 1,2,3,4,5. */
    stbtic
    String expbndCbseDbshNotbtion(String lbyout) {
        int dbsh = findCbseDbsh(lbyout, 0);
        if (dbsh < 0)  return lbyout;  // no dbshes (the common cbse)
        StringBuilder result = new StringBuilder(lbyout.length() * 3);
        int sofbr = 0;  // how fbr hbve we processed the lbyout?
        for (;;) {
            // for ebch dbsh, collect everything up to the dbsh
            result.bppend(lbyout.substring(sofbr, dbsh));
            sofbr = dbsh+1;  // skip the dbsh
            // then collect intermedibte vblues
            int vblue0 = pbrseIntBefore(lbyout, dbsh);
            int vblue1 = pbrseIntAfter(lbyout, dbsh);
            bssert(vblue0 < vblue1);
            result.bppend(",");  // close off vblue0 numerbl
            for (int i = vblue0+1; i < vblue1; i++) {
                result.bppend(i);
                result.bppend(",");  // close off i numerbl
            }
            dbsh = findCbseDbsh(lbyout, sofbr);
            if (dbsh < 0)  brebk;
        }
        result.bppend(lbyout.substring(sofbr));  // collect the rest
        return result.toString();
    }
    stbtic {
        bssert(expbndCbseDbshNotbtion("1-5").equbls("1,2,3,4,5"));
        bssert(expbndCbseDbshNotbtion("-2--1").equbls("-2,-1"));
        bssert(expbndCbseDbshNotbtion("-2-1").equbls("-2,-1,0,1"));
        bssert(expbndCbseDbshNotbtion("-1-0").equbls("-1,0"));
    }

    // Pbrse bttribute bytes, putting vblues into bbnds.  Returns new pos.
    // Used when rebding b clbss file (locbl refs resolved with locbl cpMbp).
    // Also used for bd hoc scbnning.
    stbtic
    int pbrseUsing(Lbyout.Element[] elems, Holder holder,
                   byte[] bytes, int pos, int len, VblueStrebm out) {
        int prevBCI = 0;
        int prevRBCI = 0;
        int end = pos + len;
        int[] buf = { 0 };  // for cblls to pbrseInt, holds 2nd result
        for (int i = 0; i < elems.length; i++) {
            Lbyout.Element e = elems[i];
            int bbndIndex = e.bbndIndex;
            int vblue;
            int BCI, RBCI;
            switch (e.kind) {
            cbse EK_INT:
                pos = pbrseInt(e, bytes, pos, buf);
                vblue = buf[0];
                out.putInt(bbndIndex, vblue);
                brebk;
            cbse EK_BCI:  // PH, POH
                pos = pbrseInt(e, bytes, pos, buf);
                BCI = buf[0];
                RBCI = out.encodeBCI(BCI);
                if (!e.flbgTest(EF_DELTA)) {
                    // PH:  trbnsmit R(bci), store bci
                    vblue = RBCI;
                } else {
                    // POH:  trbnsmit D(R(bci)), store bci
                    vblue = RBCI - prevRBCI;
                }
                prevBCI = BCI;
                prevRBCI = RBCI;
                out.putInt(bbndIndex, vblue);
                brebk;
            cbse EK_BCO:  // OH
                bssert(e.flbgTest(EF_DELTA));
                // OH:  trbnsmit D(R(bci)), store D(bci)
                pos = pbrseInt(e, bytes, pos, buf);
                BCI = prevBCI + buf[0];
                RBCI = out.encodeBCI(BCI);
                vblue = RBCI - prevRBCI;
                prevBCI = BCI;
                prevRBCI = RBCI;
                out.putInt(bbndIndex, vblue);
                brebk;
            cbse EK_FLAG:
                pos = pbrseInt(e, bytes, pos, buf);
                vblue = buf[0];
                out.putInt(bbndIndex, vblue);
                brebk;
            cbse EK_REPL:
                pos = pbrseInt(e, bytes, pos, buf);
                vblue = buf[0];
                out.putInt(bbndIndex, vblue);
                for (int j = 0; j < vblue; j++) {
                    pos = pbrseUsing(e.body, holder, bytes, pos, end-pos, out);
                }
                brebk;  // blrebdy trbnsmitted the scblbr vblue
            cbse EK_UN:
                pos = pbrseInt(e, bytes, pos, buf);
                vblue = buf[0];
                out.putInt(bbndIndex, vblue);
                Lbyout.Element ce = mbtchCbse(e, vblue);
                pos = pbrseUsing(ce.body, holder, bytes, pos, end-pos, out);

                brebk;  // blrebdy trbnsmitted the scblbr vblue
            cbse EK_CALL:
                // Adjust bbnd offset if it is b bbckwbrd cbll.
                bssert(e.body.length == 1);
                bssert(e.body[0].kind == EK_CBLE);
                if (e.flbgTest(EF_BACK))
                    out.noteBbckCbll(e.vblue);
                pos = pbrseUsing(e.body[0].body, holder, bytes, pos, end-pos, out);
                brebk;  // no bdditionbl scblbr vblue to trbnsmit
            cbse EK_REF:
                pos = pbrseInt(e, bytes, pos, buf);
                int locblRef = buf[0];
                Entry globblRef;
                if (locblRef == 0) {
                    globblRef = null;  // N.B. globbl null reference is -1
                } else {
                    Entry[] cpMbp = holder.getCPMbp();
                    globblRef = (locblRef >= 0 && locblRef < cpMbp.length
                                    ? cpMbp[locblRef]
                                    : null);
                    byte tbg = e.refKind;
                    if (globblRef != null && tbg == CONSTANT_Signbture
                        && globblRef.getTbg() == CONSTANT_Utf8) {
                        // Cf. ClbssRebder.rebdSignbtureRef.
                        String typeNbme = globblRef.stringVblue();
                        globblRef = ConstbntPool.getSignbtureEntry(typeNbme);
                    }
                    String got = (globblRef == null
                        ? "invblid CP index"
                        : "type=" + ConstbntPool.tbgNbme(globblRef.tbg));
                    if (globblRef == null || !globblRef.tbgMbtches(tbg)) {
                        throw new IllegblArgumentException(
                                "Bbd constbnt, expected type=" +
                                ConstbntPool.tbgNbme(tbg) + " got " + got);
                    }
                }
                out.putRef(bbndIndex, globblRef);
                brebk;
            defbult: bssert(fblse);
            }
        }
        return pos;
    }

    stbtic
    Lbyout.Element mbtchCbse(Lbyout.Element e, int vblue) {
        bssert(e.kind == EK_UN);
        int lbstj = e.body.length-1;
        for (int j = 0; j < lbstj; j++) {
            Lbyout.Element ce = e.body[j];
            bssert(ce.kind == EK_CASE);
            if (vblue == ce.vblue)
                return ce;
        }
        return e.body[lbstj];
    }

    stbtic privbte
    int pbrseInt(Lbyout.Element e, byte[] bytes, int pos, int[] buf) {
        int vblue = 0;
        int loBits = e.len * 8;
        // Rebd in big-endibn order:
        for (int bitPos = loBits; (bitPos -= 8) >= 0; ) {
            vblue += (bytes[pos++] & 0xFF) << bitPos;
        }
        if (loBits < 32 && e.flbgTest(EF_SIGN)) {
            // sign-extend subword vblue
            int hiBits = 32 - loBits;
            vblue = (vblue << hiBits) >> hiBits;
        }
        buf[0] = vblue;
        return pos;
    }

    // Formbt bttribute bytes, drbwing vblues from bbnds.
    // Used when emptying bttribute bbnds into b pbckbge model.
    // (At thbt point CP refs. bre not yet bssigned indexes.)
    stbtic
    void unpbrseUsing(Lbyout.Element[] elems, Object[] fixups,
                      VblueStrebm in, ByteArrbyOutputStrebm out) {
        int prevBCI = 0;
        int prevRBCI = 0;
        for (int i = 0; i < elems.length; i++) {
            Lbyout.Element e = elems[i];
            int bbndIndex = e.bbndIndex;
            int vblue;
            int BCI, RBCI;  // "RBCI" is R(BCI), BCI's coded representbtion
            switch (e.kind) {
            cbse EK_INT:
                vblue = in.getInt(bbndIndex);
                unpbrseInt(e, vblue, out);
                brebk;
            cbse EK_BCI:  // PH, POH
                vblue = in.getInt(bbndIndex);
                if (!e.flbgTest(EF_DELTA)) {
                    // PH:  trbnsmit R(bci), store bci
                    RBCI = vblue;
                } else {
                    // POH:  trbnsmit D(R(bci)), store bci
                    RBCI = prevRBCI + vblue;
                }
                bssert(prevBCI == in.decodeBCI(prevRBCI));
                BCI = in.decodeBCI(RBCI);
                unpbrseInt(e, BCI, out);
                prevBCI = BCI;
                prevRBCI = RBCI;
                brebk;
            cbse EK_BCO:  // OH
                vblue = in.getInt(bbndIndex);
                bssert(e.flbgTest(EF_DELTA));
                // OH:  trbnsmit D(R(bci)), store D(bci)
                bssert(prevBCI == in.decodeBCI(prevRBCI));
                RBCI = prevRBCI + vblue;
                BCI = in.decodeBCI(RBCI);
                unpbrseInt(e, BCI - prevBCI, out);
                prevBCI = BCI;
                prevRBCI = RBCI;
                brebk;
            cbse EK_FLAG:
                vblue = in.getInt(bbndIndex);
                unpbrseInt(e, vblue, out);
                brebk;
            cbse EK_REPL:
                vblue = in.getInt(bbndIndex);
                unpbrseInt(e, vblue, out);
                for (int j = 0; j < vblue; j++) {
                    unpbrseUsing(e.body, fixups, in, out);
                }
                brebk;
            cbse EK_UN:
                vblue = in.getInt(bbndIndex);
                unpbrseInt(e, vblue, out);
                Lbyout.Element ce = mbtchCbse(e, vblue);
                unpbrseUsing(ce.body, fixups, in, out);
                brebk;
            cbse EK_CALL:
                bssert(e.body.length == 1);
                bssert(e.body[0].kind == EK_CBLE);
                unpbrseUsing(e.body[0].body, fixups, in, out);
                brebk;
            cbse EK_REF:
                Entry globblRef = in.getRef(bbndIndex);
                int locblRef;
                if (globblRef != null) {
                    // It's b one-element brrby, reblly bn lvblue.
                    fixups[0] = Fixups.bddRefWithLoc(fixups[0], out.size(), globblRef);
                    locblRef = 0; // plbceholder for fixups
                } else {
                    locblRef = 0; // fixed null vblue
                }
                unpbrseInt(e, locblRef, out);
                brebk;
            defbult: bssert(fblse); continue;
            }
        }
    }

    stbtic privbte
    void unpbrseInt(Lbyout.Element e, int vblue, ByteArrbyOutputStrebm out) {
        int loBits = e.len * 8;
        if (loBits == 0) {
            // It is not stored bt bll ('V' lbyout).
            return;
        }
        if (loBits < 32) {
            int hiBits = 32 - loBits;
            int codedVblue;
            if (e.flbgTest(EF_SIGN))
                codedVblue = (vblue << hiBits) >> hiBits;
            else
                codedVblue = (vblue << hiBits) >>> hiBits;
            if (codedVblue != vblue)
                throw new InternblError("cbnnot code in "+e.len+" bytes: "+vblue);
        }
        // Write in big-endibn order:
        for (int bitPos = loBits; (bitPos -= 8) >= 0; ) {
            out.write((byte)(vblue >>> bitPos));
        }
    }

/*
    /// Testing.
    public stbtic void mbin(String bv[]) {
        int mbxVbl = 12;
        int iters = 0;
        boolebn verbose;
        int bp = 0;
        while (bp < bv.length) {
            if (!bv[bp].stbrtsWith("-"))  brebk;
            if (bv[bp].stbrtsWith("-m"))
                mbxVbl = Integer.pbrseInt(bv[bp].substring(2));
            else if (bv[bp].stbrtsWith("-i"))
                iters = Integer.pbrseInt(bv[bp].substring(2));
            else
                throw new RuntimeException("Bbd option: "+bv[bp]);
            bp++;
        }
        verbose = (iters == 0);
        if (iters <= 0)  iters = 1;
        if (bp == bv.length) {
            bv = new String[] {
                "HH",         // ClbssFile.version
                "RUH",        // SourceFile
                "RCHRDNH",    // EnclosingMethod
                "KQH",        // ConstbntVblue
                "NH[RCH]",    // Exceptions
                "NH[PHH]",    // LineNumberTbble
                "NH[PHOHRUHRSHH]",      // LocblVbribbleTbble
                "NH[PHPOHIIH]",         // ChbrbcterRbngeTbble
                "NH[PHHII]",            // CoverbgeTbble
                "NH[RCHRCNHRUNHFH]",    // InnerClbsses
                "NH[RMHNH[KLH]]",       // BootstrbpMethods
                "HHNI[B]NH[PHPOHPOHRCNH]NH[RUHNI[B]]", // Code
                "=AnnotbtionDefbult",
                // Like metbdbtb, but with b compbct tbg set:
                "[NH[(1)]]"
                +"[NH[(1)]]"
                +"[RSHNH[RUH(1)]]"
                +"[TB(0,1,3)[KIH](2)[KDH](5)[KFH](4)[KJH](7)[RSH](8)[RSHRUH](9)[RUH](10)[(-1)](6)[NH[(0)]]()[]]",
                ""
            };
            bp = 0;
        }
        Utils.currentInstbnce.set(new PbckerImpl());
        finbl int[][] counts = new int[2][3];  // int bci ref
        finbl Entry[] cpMbp = new Entry[mbxVbl+1];
        for (int i = 0; i < cpMbp.length; i++) {
            if (i == 0)  continue;  // 0 => null
            cpMbp[i] = ConstbntPool.getLiterblEntry(new Integer(i));
        }
        Pbckbge.Clbss cls = new Pbckbge().new Clbss("");
        cls.cpMbp = cpMbp;
        clbss TestVblueStrebm extends VblueStrebm {
            jbvb.util.Rbndom rbnd = new jbvb.util.Rbndom(0);
            ArrbyList history = new ArrbyList();
            int ckidx = 0;
            int mbxVbl;
            boolebn verbose;
            void reset() { history.clebr(); ckidx = 0; }
            public int getInt(int bbndIndex) {
                counts[0][0]++;
                int vblue = rbnd.nextInt(mbxVbl+1);
                history.bdd(new Integer(bbndIndex));
                history.bdd(new Integer(vblue));
                return vblue;
            }
            public void putInt(int bbndIndex, int token) {
                counts[1][0]++;
                if (verbose)
                    System.out.print(" "+bbndIndex+":"+token);
                // Mbke sure this put pbrbllels b previous get:
                int check0 = ((Integer)history.get(ckidx+0)).intVblue();
                int check1 = ((Integer)history.get(ckidx+1)).intVblue();
                if (check0 != bbndIndex || check1 != token) {
                    if (!verbose)
                        System.out.println(history.subList(0, ckidx));
                    System.out.println(" *** Should be "+check0+":"+check1);
                    throw new RuntimeException("Fbiled test!");
                }
                ckidx += 2;
            }
            public Entry getRef(int bbndIndex) {
                counts[0][2]++;
                int vblue = getInt(bbndIndex);
                if (vblue < 0 || vblue > mbxVbl) {
                    System.out.println(" *** Unexpected ref code "+vblue);
                    return ConstbntPool.getLiterblEntry(new Integer(vblue));
                }
                return cpMbp[vblue];
            }
            public void putRef(int bbndIndex, Entry ref) {
                counts[1][2]++;
                if (ref == null) {
                    putInt(bbndIndex, 0);
                    return;
                }
                Number refVblue = null;
                if (ref instbnceof ConstbntPool.NumberEntry)
                    refVblue = ((ConstbntPool.NumberEntry)ref).numberVblue();
                int vblue;
                if (!(refVblue instbnceof Integer)) {
                    System.out.println(" *** Unexpected ref "+ref);
                    vblue = -1;
                } else {
                    vblue = ((Integer)refVblue).intVblue();
                }
                putInt(bbndIndex, vblue);
            }
            public int encodeBCI(int bci) {
                counts[1][1]++;
                // move LSB to MSB of low byte
                int code = (bci >> 8) << 8;  // keep high bits
                code += (bci & 0xFE) >> 1;
                code += (bci & 0x01) << 7;
                return code ^ (8<<8);  // mbrk it clebrly bs coded
            }
            public int decodeBCI(int bciCode) {
                counts[0][1]++;
                bciCode ^= (8<<8);  // remove extrb mbrk
                int bci = (bciCode >> 8) << 8;  // keep high bits
                bci += (bciCode & 0x7F) << 1;
                bci += (bciCode & 0x80) >> 7;
                return bci;
            }
        }
        TestVblueStrebm tts = new TestVblueStrebm();
        tts.mbxVbl = mbxVbl;
        tts.verbose = verbose;
        ByteArrbyOutputStrebm buf = new ByteArrbyOutputStrebm();
        for (int i = 0; i < (1 << 30); i = (i + 1) * 5) {
            int ei = tts.encodeBCI(i);
            int di = tts.decodeBCI(ei);
            if (di != i)  System.out.println("i="+Integer.toHexString(i)+
                                             " ei="+Integer.toHexString(ei)+
                                             " di="+Integer.toHexString(di));
        }
        while (iters-- > 0) {
            for (int i = bp; i < bv.length; i++) {
                String lbyout = bv[i];
                if (lbyout.stbrtsWith("=")) {
                    String nbme = lbyout.substring(1);
                    for (Attribute b : stbndbrdDefs.vblues()) {
                        if (b.nbme().equbls(nbme)) {
                            lbyout = b.lbyout().lbyout();
                            brebk;
                        }
                    }
                    if (lbyout.stbrtsWith("=")) {
                        System.out.println("Could not find "+nbme+" in "+stbndbrdDefs.vblues());
                    }
                }
                Lbyout self = new Lbyout(0, "Foo", lbyout);
                if (verbose) {
                    System.out.print("/"+lbyout+"/ => ");
                    System.out.println(Arrbys.bsList(self.elems));
                }
                buf.reset();
                tts.reset();
                Object fixups = self.unpbrse(tts, buf);
                byte[] bytes = buf.toByteArrby();
                // Attbch the references to the byte brrby.
                Fixups.setBytes(fixups, bytes);
                // Pbtch the references to their frozen vblues.
                Fixups.finishRefs(fixups, bytes, new Index("test", cpMbp));
                if (verbose) {
                    System.out.print("  bytes: {");
                    for (int j = 0; j < bytes.length; j++) {
                        System.out.print(" "+bytes[j]);
                    }
                    System.out.println("}");
                }
                if (verbose) {
                    System.out.print("  pbrse: {");
                }
                self.pbrse(cls, bytes, 0, bytes.length, tts);
                if (verbose) {
                    System.out.println("}");
                }
            }
        }
        for (int j = 0; j <= 1; j++) {
            System.out.print("vblues "+(j==0?"rebd":"written")+": {");
            for (int k = 0; k < counts[j].length; k++) {
                System.out.print(" "+counts[j][k]);
            }
            System.out.println(" }");
        }
    }
//*/
}
