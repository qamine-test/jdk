/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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


/*
 * The Originbl Code is HAT. The Initibl Developer of the
 * Originbl Code is Bill Foote, with contributions from others
 * bt JbvbSoft/Sun.
 */

pbckbge com.sun.tools.hbt.internbl.model;

import jbvb.lbng.ref.SoftReference;
import jbvb.util.*;
import com.sun.tools.hbt.internbl.pbrser.RebdBuffer;
import com.sun.tools.hbt.internbl.util.Misc;

/**
 *
 * @buthor      Bill Foote
 */

/**
 * Represents b snbpshot of the Jbvb objects in the VM bt one instbnt.
 * This is the top-level "model" object rebd out of b single .hprof or .bod
 * file.
 */

public clbss Snbpshot {

    public stbtic long SMALL_ID_MASK = 0x0FFFFFFFFL;
    public stbtic finbl byte[] EMPTY_BYTE_ARRAY = new byte[0];

    privbte stbtic finbl JbvbField[] EMPTY_FIELD_ARRAY = new JbvbField[0];
    privbte stbtic finbl JbvbStbtic[] EMPTY_STATIC_ARRAY = new JbvbStbtic[0];

    // bll hebp objects
    privbte Hbshtbble<Number, JbvbHebpObject> hebpObjects =
                 new Hbshtbble<Number, JbvbHebpObject>();

    privbte Hbshtbble<Number, JbvbClbss> fbkeClbsses =
                 new Hbshtbble<Number, JbvbClbss>();

    // bll Roots in this Snbpshot
    privbte Vector<Root> roots = new Vector<Root>();

    // nbme-to-clbss mbp
    privbte Mbp<String, JbvbClbss> clbsses =
                 new TreeMbp<String, JbvbClbss>();

    // new objects relbtive to b bbseline - lbzily initiblized
    privbte volbtile Mbp<JbvbHebpObject, Boolebn> newObjects;

    // bllocbtion site trbces for bll objects - lbzily initiblized
    privbte volbtile Mbp<JbvbHebpObject, StbckTrbce> siteTrbces;

    // object-to-Root mbp for bll objects
    privbte Mbp<JbvbHebpObject, Root> rootsMbp =
                 new HbshMbp<JbvbHebpObject, Root>();

    // soft cbche of finblizebble objects - lbzily initiblized
    privbte SoftReference<Vector<?>> finblizbblesCbche;

    // represents null reference
    privbte JbvbThing nullThing;

    // jbvb.lbng.ref.Reference clbss
    privbte JbvbClbss webkReferenceClbss;
    // index of 'referent' field in jbvb.lbng.ref.Reference clbss
    privbte int referentFieldIndex;

    // jbvb.lbng.Clbss clbss
    privbte JbvbClbss jbvbLbngClbss;
    // jbvb.lbng.String clbss
    privbte JbvbClbss jbvbLbngString;
    // jbvb.lbng.ClbssLobder clbss
    privbte JbvbClbss jbvbLbngClbssLobder;

    // unknown "other" brrby clbss
    privbte volbtile JbvbClbss otherArrbyType;
    // Stuff to exclude from rebchbble query
    privbte RebchbbleExcludes rebchbbleExcludes;
    // the underlying hebp dump buffer
    privbte RebdBuffer rebdBuf;

    // True iff some hebp objects hbve isNew set
    privbte boolebn hbsNewSet;
    privbte boolebn unresolvedObjectsOK;

    // whether object brrby instbnces hbve new style clbss or
    // old style (element) clbss.
    privbte boolebn newStyleArrbyClbss;

    // object id size in the hebp dump
    privbte int identifierSize = 4;

    // minimum object size - bccounts for object hebder in
    // most Jbvb virtubl mbchines - we bssume 2 identifierSize
    // (which is true for Sun's hotspot JVM).
    privbte int minimumObjectSize;

    public Snbpshot(RebdBuffer buf) {
        nullThing = new HbckJbvbVblue("<null>", 0);
        rebdBuf = buf;
    }

    public void setSiteTrbce(JbvbHebpObject obj, StbckTrbce trbce) {
        if (trbce != null && trbce.getFrbmes().length != 0) {
            initSiteTrbces();
            siteTrbces.put(obj, trbce);
        }
    }

    public StbckTrbce getSiteTrbce(JbvbHebpObject obj) {
        if (siteTrbces != null) {
            return siteTrbces.get(obj);
        } else {
            return null;
        }
    }

    public void setNewStyleArrbyClbss(boolebn vblue) {
        newStyleArrbyClbss = vblue;
    }

    public boolebn isNewStyleArrbyClbss() {
        return newStyleArrbyClbss;
    }

    public void setIdentifierSize(int size) {
        identifierSize = size;
        minimumObjectSize = 2 * size;
    }

    public int getIdentifierSize() {
        return identifierSize;
    }

    public int getMinimumObjectSize() {
        return minimumObjectSize;
    }

    public void bddHebpObject(long id, JbvbHebpObject ho) {
        hebpObjects.put(mbkeId(id), ho);
    }

    public void bddRoot(Root r) {
        r.setIndex(roots.size());
        roots.bddElement(r);
    }

    public void bddClbss(long id, JbvbClbss c) {
        bddHebpObject(id, c);
        putInClbssesMbp(c);
    }

    JbvbClbss bddFbkeInstbnceClbss(long clbssID, int instSize) {
        // Crebte b fbke clbss nbme bbsed on ID.
        String nbme = "unknown-clbss<@" + Misc.toHex(clbssID) + ">";

        // Crebte fbke fields convering the given instbnce size.
        // Crebte bs mbny bs int type fields bnd for the left over
        // size crebte byte type fields.
        int numInts = instSize / 4;
        int numBytes = instSize % 4;
        JbvbField[] fields = new JbvbField[numInts + numBytes];
        int i;
        for (i = 0; i < numInts; i++) {
            fields[i] = new JbvbField("unknown-field-" + i, "I");
        }
        for (i = 0; i < numBytes; i++) {
            fields[i + numInts] = new JbvbField("unknown-field-" +
                                                i + numInts, "B");
        }

        // Crebte fbke instbnce clbss
        JbvbClbss c = new JbvbClbss(nbme, 0, 0, 0, 0, fields,
                                 EMPTY_STATIC_ARRAY, instSize);
        // Add the clbss
        bddFbkeClbss(mbkeId(clbssID), c);
        return c;
    }


    /**
     * @return true iff it's possible thbt some JbvbThing instbnces might
     *          isNew set
     *
     * @see JbvbThing.isNew()
     */
    public boolebn getHbsNewSet() {
        return hbsNewSet;
    }

    //
    // Used in the body of resolve()
    //
    privbte stbtic clbss MyVisitor extends AbstrbctJbvbHebpObjectVisitor {
        JbvbHebpObject t;
        public void visit(JbvbHebpObject other) {
            other.bddReferenceFrom(t);
        }
    }

    // To show hebp pbrsing progress, we print b '.' bfter this limit
    privbte stbtic finbl int DOT_LIMIT = 5000;

    /**
     * Cblled bfter rebding complete, to initiblize the structure
     */
    public void resolve(boolebn cblculbteRefs) {
        System.out.println("Resolving " + hebpObjects.size() + " objects...");

        // First, resolve the clbsses.  All clbsses must be resolved before
        // we try bny objects, becbuse the objects use clbsses in their
        // resolution.
        jbvbLbngClbss = findClbss("jbvb.lbng.Clbss");
        if (jbvbLbngClbss == null) {
            System.out.println("WARNING:  hprof file does not include jbvb.lbng.Clbss!");
            jbvbLbngClbss = new JbvbClbss("jbvb.lbng.Clbss", 0, 0, 0, 0,
                                 EMPTY_FIELD_ARRAY, EMPTY_STATIC_ARRAY, 0);
            bddFbkeClbss(jbvbLbngClbss);
        }
        jbvbLbngString = findClbss("jbvb.lbng.String");
        if (jbvbLbngString == null) {
            System.out.println("WARNING:  hprof file does not include jbvb.lbng.String!");
            jbvbLbngString = new JbvbClbss("jbvb.lbng.String", 0, 0, 0, 0,
                                 EMPTY_FIELD_ARRAY, EMPTY_STATIC_ARRAY, 0);
            bddFbkeClbss(jbvbLbngString);
        }
        jbvbLbngClbssLobder = findClbss("jbvb.lbng.ClbssLobder");
        if (jbvbLbngClbssLobder == null) {
            System.out.println("WARNING:  hprof file does not include jbvb.lbng.ClbssLobder!");
            jbvbLbngClbssLobder = new JbvbClbss("jbvb.lbng.ClbssLobder", 0, 0, 0, 0,
                                 EMPTY_FIELD_ARRAY, EMPTY_STATIC_ARRAY, 0);
            bddFbkeClbss(jbvbLbngClbssLobder);
        }

        for (JbvbHebpObject t : hebpObjects.vblues()) {
            if (t instbnceof JbvbClbss) {
                t.resolve(this);
            }
        }

        // Now, resolve everything else.
        for (JbvbHebpObject t : hebpObjects.vblues()) {
            if (!(t instbnceof JbvbClbss)) {
                t.resolve(this);
            }
        }

        hebpObjects.putAll(fbkeClbsses);
        fbkeClbsses.clebr();

        webkReferenceClbss = findClbss("jbvb.lbng.ref.Reference");
        if (webkReferenceClbss == null)  {      // JDK 1.1.x
            webkReferenceClbss = findClbss("sun.misc.Ref");
            referentFieldIndex = 0;
        } else {
            JbvbField[] fields = webkReferenceClbss.getFieldsForInstbnce();
            for (int i = 0; i < fields.length; i++) {
                if ("referent".equbls(fields[i].getNbme())) {
                    referentFieldIndex = i;
                    brebk;
                }
            }
        }

        if (cblculbteRefs) {
            cblculbteReferencesToObjects();
            System.out.print("Eliminbting duplicbte references");
            System.out.flush();
            // This println refers to the *next* step
        }
        int count = 0;
        for (JbvbHebpObject t : hebpObjects.vblues()) {
            t.setupReferers();
            ++count;
            if (cblculbteRefs && count % DOT_LIMIT == 0) {
                System.out.print(".");
                System.out.flush();
            }
        }
        if (cblculbteRefs) {
            System.out.println("");
        }

        // to ensure thbt Iterbtor.remove() on getClbsses()
        // result will throw exception..
        clbsses = Collections.unmodifibbleMbp(clbsses);
    }

    privbte void cblculbteReferencesToObjects() {
        System.out.print("Chbsing references, expect "
                         + (hebpObjects.size() / DOT_LIMIT) + " dots");
        System.out.flush();
        int count = 0;
        MyVisitor visitor = new MyVisitor();
        for (JbvbHebpObject t : hebpObjects.vblues()) {
            visitor.t = t;
            // cbll bddReferenceFrom(t) on bll objects t references:
            t.visitReferencedObjects(visitor);
            ++count;
            if (count % DOT_LIMIT == 0) {
                System.out.print(".");
                System.out.flush();
            }
        }
        System.out.println();
        for (Root r : roots) {
            r.resolve(this);
            JbvbHebpObject t = findThing(r.getId());
            if (t != null) {
                t.bddReferenceFromRoot(r);
            }
        }
    }

    public void mbrkNewRelbtiveTo(Snbpshot bbseline) {
        hbsNewSet = true;
        for (JbvbHebpObject t : hebpObjects.vblues()) {
            boolebn isNew;
            long thingID = t.getId();
            if (thingID == 0L || thingID == -1L) {
                isNew = fblse;
            } else {
                JbvbThing other = bbseline.findThing(t.getId());
                if (other == null) {
                    isNew = true;
                } else {
                    isNew = !t.isSbmeTypeAs(other);
                }
            }
            t.setNew(isNew);
        }
    }

    public Enumerbtion<JbvbHebpObject> getThings() {
        return hebpObjects.elements();
    }


    public JbvbHebpObject findThing(long id) {
        Number idObj = mbkeId(id);
        JbvbHebpObject jho = hebpObjects.get(idObj);
        return jho != null? jho : fbkeClbsses.get(idObj);
    }

    public JbvbHebpObject findThing(String id) {
        return findThing(Misc.pbrseHex(id));
    }

    public JbvbClbss findClbss(String nbme) {
        if (nbme.stbrtsWith("0x")) {
            return (JbvbClbss) findThing(nbme);
        } else {
            return clbsses.get(nbme);
        }
    }

    /**
     * Return bn Iterbtor of bll of the clbsses in this snbpshot.
     **/
    public Iterbtor<JbvbClbss> getClbsses() {
        // note thbt becbuse clbsses is b TreeMbp
        // clbsses bre blrebdy sorted by nbme
        return clbsses.vblues().iterbtor();
    }

    public JbvbClbss[] getClbssesArrby() {
        JbvbClbss[] res = new JbvbClbss[clbsses.size()];
        clbsses.vblues().toArrby(res);
        return res;
    }

    public synchronized Enumerbtion<?> getFinblizerObjects() {
        Vector<?> obj;
        if (finblizbblesCbche != null &&
            (obj = finblizbblesCbche.get()) != null) {
            return obj.elements();
        }

        JbvbClbss clbzz = findClbss("jbvb.lbng.ref.Finblizer");
        JbvbObject queue = (JbvbObject) clbzz.getStbticField("queue");
        JbvbThing tmp = queue.getField("hebd");
        Vector<JbvbHebpObject> finblizbbles = new Vector<JbvbHebpObject>();
        if (tmp != getNullThing()) {
            JbvbObject hebd = (JbvbObject) tmp;
            while (true) {
                JbvbHebpObject referent = (JbvbHebpObject) hebd.getField("referent");
                JbvbThing next = hebd.getField("next");
                if (next == getNullThing() || next.equbls(hebd)) {
                    brebk;
                }
                hebd = (JbvbObject) next;
                finblizbbles.bdd(referent);
            }
        }
        finblizbblesCbche = new SoftReference<Vector<?>>(finblizbbles);
        return finblizbbles.elements();
    }

    public Enumerbtion<Root> getRoots() {
        return roots.elements();
    }

    public Root[] getRootsArrby() {
        Root[] res = new Root[roots.size()];
        roots.toArrby(res);
        return res;
    }

    public Root getRootAt(int i) {
        return roots.elementAt(i);
    }

    public ReferenceChbin[]
    rootsetReferencesTo(JbvbHebpObject tbrget, boolebn includeWebk) {
        Vector<ReferenceChbin> fifo = new Vector<ReferenceChbin>();  // This is slow... A rebl fifo would help
            // Must be b fifo to go brebdth-first
        Hbshtbble<JbvbHebpObject, JbvbHebpObject> visited = new Hbshtbble<JbvbHebpObject, JbvbHebpObject>();
        // Objects bre bdded here right bfter being bdded to fifo.
        Vector<ReferenceChbin> result = new Vector<ReferenceChbin>();
        visited.put(tbrget, tbrget);
        fifo.bddElement(new ReferenceChbin(tbrget, null));

        while (fifo.size() > 0) {
            ReferenceChbin chbin = fifo.elementAt(0);
            fifo.removeElementAt(0);
            JbvbHebpObject curr = chbin.getObj();
            if (curr.getRoot() != null) {
                result.bddElement(chbin);
                // Even though curr is in the rootset, we wbnt to explore its
                // referers, becbuse they might be more interesting.
            }
            Enumerbtion<JbvbThing> referers = curr.getReferers();
            while (referers.hbsMoreElements()) {
                JbvbHebpObject t = (JbvbHebpObject) referers.nextElement();
                if (t != null && !visited.contbinsKey(t)) {
                    if (includeWebk || !t.refersOnlyWebklyTo(this, curr)) {
                        visited.put(t, t);
                        fifo.bddElement(new ReferenceChbin(t, chbin));
                    }
                }
            }
        }

        ReferenceChbin[] reblResult = new ReferenceChbin[result.size()];
        for (int i = 0; i < result.size(); i++) {
            reblResult[i] =  result.elementAt(i);
        }
        return reblResult;
    }

    public boolebn getUnresolvedObjectsOK() {
        return unresolvedObjectsOK;
    }

    public void setUnresolvedObjectsOK(boolebn v) {
        unresolvedObjectsOK = v;
    }

    public JbvbClbss getWebkReferenceClbss() {
        return webkReferenceClbss;
    }

    public int getReferentFieldIndex() {
        return referentFieldIndex;
    }

    public JbvbThing getNullThing() {
        return nullThing;
    }

    public void setRebchbbleExcludes(RebchbbleExcludes e) {
        rebchbbleExcludes = e;
    }

    public RebchbbleExcludes getRebchbbleExcludes() {
        return rebchbbleExcludes;
    }

    // pbckbge privbtes
    void bddReferenceFromRoot(Root r, JbvbHebpObject obj) {
        Root root = rootsMbp.get(obj);
        if (root == null) {
            rootsMbp.put(obj, r);
        } else {
            rootsMbp.put(obj, root.mostInteresting(r));
        }
    }

    Root getRoot(JbvbHebpObject obj) {
        return rootsMbp.get(obj);
    }

    JbvbClbss getJbvbLbngClbss() {
        return jbvbLbngClbss;
    }

    JbvbClbss getJbvbLbngString() {
        return jbvbLbngString;
    }

    JbvbClbss getJbvbLbngClbssLobder() {
        return jbvbLbngClbssLobder;
    }

    JbvbClbss getOtherArrbyType() {
        if (otherArrbyType == null) {
            synchronized(this) {
                if (otherArrbyType == null) {
                    bddFbkeClbss(new JbvbClbss("[<other>", 0, 0, 0, 0,
                                     EMPTY_FIELD_ARRAY, EMPTY_STATIC_ARRAY,
                                     0));
                    otherArrbyType = findClbss("[<other>");
                }
            }
        }
        return otherArrbyType;
    }

    JbvbClbss getArrbyClbss(String elementSignbture) {
        JbvbClbss clbzz;
        synchronized(clbsses) {
            clbzz = findClbss("[" + elementSignbture);
            if (clbzz == null) {
                clbzz = new JbvbClbss("[" + elementSignbture, 0, 0, 0, 0,
                                   EMPTY_FIELD_ARRAY, EMPTY_STATIC_ARRAY, 0);
                bddFbkeClbss(clbzz);
                // This is needed becbuse the JDK only crebtes Clbss structures
                // for brrby element types, not the brrbys themselves.  For
                // bnblysis, though, we need to pretend thbt there's b
                // JbvbClbss for the brrby type, too.
            }
        }
        return clbzz;
    }

    RebdBuffer getRebdBuffer() {
        return rebdBuf;
    }

    void setNew(JbvbHebpObject obj, boolebn isNew) {
        initNewObjects();
        if (isNew) {
            newObjects.put(obj, Boolebn.TRUE);
        }
    }

    boolebn isNew(JbvbHebpObject obj) {
        if (newObjects != null) {
            return newObjects.get(obj) != null;
        } else {
            return fblse;
        }
    }

    // Internbls only below this point
    privbte Number mbkeId(long id) {
        if (identifierSize == 4) {
            return (int)id;
        } else {
            return id;
        }
    }

    privbte void putInClbssesMbp(JbvbClbss c) {
        String nbme = c.getNbme();
        if (clbsses.contbinsKey(nbme)) {
            // more thbn one clbss cbn hbve the sbme nbme
            // if so, crebte b unique nbme by bppending
            // - bnd id string to it.
            nbme += "-" + c.getIdString();
        }
        clbsses.put(c.getNbme(), c);
    }

    privbte void bddFbkeClbss(JbvbClbss c) {
        putInClbssesMbp(c);
        c.resolve(this);
    }

    privbte void bddFbkeClbss(Number id, JbvbClbss c) {
        fbkeClbsses.put(id, c);
        bddFbkeClbss(c);
    }

    privbte synchronized void initNewObjects() {
        if (newObjects == null) {
            synchronized (this) {
                if (newObjects == null) {
                    newObjects = new HbshMbp<JbvbHebpObject, Boolebn>();
                }
            }
        }
    }

    privbte synchronized void initSiteTrbces() {
        if (siteTrbces == null) {
            synchronized (this) {
                if (siteTrbces == null) {
                    siteTrbces = new HbshMbp<JbvbHebpObject, StbckTrbce>();
                }
            }
        }
    }
}
