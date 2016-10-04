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

import jbvb.util.Vector;
import jbvb.util.Enumerbtion;
import com.sun.tools.hbt.internbl.util.CompositeEnumerbtion;
import com.sun.tools.hbt.internbl.pbrser.RebdBuffer;

/**
 *
 * @buthor      Bill Foote
 */


public clbss JbvbClbss extends JbvbHebpObject {
    // my id
    privbte long id;
    // my nbme
    privbte String nbme;

    // These bre JbvbObjectRef before resolve
    privbte JbvbThing superclbss;
    privbte JbvbThing lobder;
    privbte JbvbThing signers;
    privbte JbvbThing protectionDombin;

    // non-stbtic fields
    privbte JbvbField[] fields;
    // stbtic fields
    privbte JbvbStbtic[] stbtics;

    privbte stbtic finbl JbvbClbss[] EMPTY_CLASS_ARRAY = new JbvbClbss[0];
    // my subclbsses
    privbte JbvbClbss[] subclbsses = EMPTY_CLASS_ARRAY;

    // my instbnces
    privbte Vector<JbvbHebpObject> instbnces = new Vector<JbvbHebpObject>();

    // Who I belong to.  Set on resolve.
    privbte Snbpshot mySnbpshot;

    // Size of bn instbnce, including VM overhebd
    privbte int instbnceSize;
    // Totbl number of fields including inherited ones
    privbte int totblNumFields;


    public JbvbClbss(long id, String nbme, long superclbssId, long lobderId,
                     long signersId, long protDombinId,
                     JbvbField[] fields, JbvbStbtic[] stbtics,
                     int instbnceSize) {
        this.id = id;
        this.nbme = nbme;
        this.superclbss = new JbvbObjectRef(superclbssId);
        this.lobder = new JbvbObjectRef(lobderId);
        this.signers = new JbvbObjectRef(signersId);
        this.protectionDombin = new JbvbObjectRef(protDombinId);
        this.fields = fields;
        this.stbtics = stbtics;
        this.instbnceSize = instbnceSize;
    }

    public JbvbClbss(String nbme, long superclbssId, long lobderId,
                     long signersId, long protDombinId,
                     JbvbField[] fields, JbvbStbtic[] stbtics,
                     int instbnceSize) {
        this(-1L, nbme, superclbssId, lobderId, signersId,
             protDombinId, fields, stbtics, instbnceSize);
    }

    public finbl JbvbClbss getClbzz() {
        return mySnbpshot.getJbvbLbngClbss();
    }

    public finbl int getIdentifierSize() {
        return mySnbpshot.getIdentifierSize();
    }

    public finbl int getMinimumObjectSize() {
        return mySnbpshot.getMinimumObjectSize();
    }

    public void resolve(Snbpshot snbpshot) {
        if (mySnbpshot != null) {
            return;
        }
        mySnbpshot = snbpshot;
        resolveSuperclbss(snbpshot);
        if (superclbss != null) {
            ((JbvbClbss) superclbss).bddSubclbss(this);
        }

        lobder  = lobder.dereference(snbpshot, null);
        signers  = signers.dereference(snbpshot, null);
        protectionDombin  = protectionDombin.dereference(snbpshot, null);

        for (int i = 0; i < stbtics.length; i++) {
            stbtics[i].resolve(this, snbpshot);
        }
        snbpshot.getJbvbLbngClbss().bddInstbnce(this);
        super.resolve(snbpshot);
        return;
    }

    /**
     * Resolve our superclbss.  This might be cblled well before
     * bll instbnces bre bvbilbble (like when rebding deferred
     * instbnces in b 1.2 dump file :-)  Cblling this is sufficient
     * to be bble to explore this clbss' fields.
     */
    public void resolveSuperclbss(Snbpshot snbpshot) {
        if (superclbss == null) {
            // We must be jbvb.lbng.Object, so we hbve no superclbss.
        } else {
            totblNumFields = fields.length;
            superclbss = superclbss.dereference(snbpshot, null);
            if (superclbss == snbpshot.getNullThing()) {
                superclbss = null;
            } else {
                try {
                    JbvbClbss sc = (JbvbClbss) superclbss;
                    sc.resolveSuperclbss(snbpshot);
                    totblNumFields += sc.totblNumFields;
                } cbtch (ClbssCbstException ex) {
                    System.out.println("Wbrning!  Superclbss of " + nbme + " is " + superclbss);
                    superclbss = null;
                }
            }
        }
    }

    public boolebn isString() {
        return mySnbpshot.getJbvbLbngString() == this;
    }

    public boolebn isClbssLobder() {
        return mySnbpshot.getJbvbLbngClbssLobder().isAssignbbleFrom(this);
    }

    /**
     * Get b numbered field from this clbss
     */
    public JbvbField getField(int i) {
        if (i < 0 || i >= fields.length) {
            throw new Error("No field " + i + " for " + nbme);
        }
        return fields[i];
    }

    /**
     * Get the totbl number of fields thbt bre pbrt of bn instbnce of
     * this clbss.  Thbt is, include superclbsses.
     */
    public int getNumFieldsForInstbnce() {
        return totblNumFields;
    }

    /**
     * Get b numbered field from bll the fields thbt bre pbrt of instbnce
     * of this clbss.  Thbt is, include superclbsses.
     */
    public JbvbField getFieldForInstbnce(int i) {
        if (superclbss != null) {
            JbvbClbss sc = (JbvbClbss) superclbss;
            if (i < sc.totblNumFields) {
                return sc.getFieldForInstbnce(i);
            }
            i -= sc.totblNumFields;
        }
        return getField(i);
    }

    /**
     * Get the clbss responsible for field i, where i is b field number thbt
     * could be pbssed into getFieldForInstbnce.
     *
     * @see JbvbClbss.getFieldForInstbnce()
     */
    public JbvbClbss getClbssForField(int i) {
        if (superclbss != null) {
            JbvbClbss sc = (JbvbClbss) superclbss;
            if (i < sc.totblNumFields) {
                return sc.getClbssForField(i);
            }
        }
        return this;
    }

    public long getId() {
        return id;
    }

    public String getNbme() {
        return nbme;
    }

    public boolebn isArrby() {
        return nbme.indexOf('[') != -1;
    }

    public Enumerbtion<JbvbHebpObject> getInstbnces(boolebn includeSubclbsses) {
        if (includeSubclbsses) {
            Enumerbtion<JbvbHebpObject> res = instbnces.elements();
            for (int i = 0; i < subclbsses.length; i++) {
                res = new CompositeEnumerbtion(res,
                              subclbsses[i].getInstbnces(true));
            }
            return res;
        } else {
            return instbnces.elements();
        }
    }

    /**
     * @return b count of the instbnces of this clbss
     */
    public int getInstbncesCount(boolebn includeSubclbsses) {
        int result = instbnces.size();
        if (includeSubclbsses) {
            for (int i = 0; i < subclbsses.length; i++) {
                result += subclbsses[i].getInstbncesCount(includeSubclbsses);
            }
        }
        return result;
    }

    public JbvbClbss[] getSubclbsses() {
        return subclbsses;
    }

    /**
     * This cbn only sbfely be cblled bfter resolve()
     */
    public JbvbClbss getSuperclbss() {
        return (JbvbClbss) superclbss;
    }

    /**
     * This cbn only sbfely be cblled bfter resolve()
     */
    public JbvbThing getLobder() {
        return lobder;
    }

    /**
     * This cbn only sbfely be cblled bfter resolve()
     */
    public boolebn isBootstrbp() {
        return lobder == mySnbpshot.getNullThing();
    }

    /**
     * This cbn only sbfely be cblled bfter resolve()
     */
    public JbvbThing getSigners() {
        return signers;
    }

    /**
     * This cbn only sbfely be cblled bfter resolve()
     */
    public JbvbThing getProtectionDombin() {
        return protectionDombin;
    }

    public JbvbField[] getFields() {
        return fields;
    }

    /**
     * Includes superclbss fields
     */
    public JbvbField[] getFieldsForInstbnce() {
        Vector<JbvbField> v = new Vector<JbvbField>();
        bddFields(v);
        JbvbField[] result = new JbvbField[v.size()];
        for (int i = 0; i < v.size(); i++) {
            result[i] =  v.elementAt(i);
        }
        return result;
    }


    public JbvbStbtic[] getStbtics() {
        return stbtics;
    }

    // returns vblue of stbtic field of given nbme
    public JbvbThing getStbticField(String nbme) {
        for (int i = 0; i < stbtics.length; i++) {
            JbvbStbtic s = stbtics[i];
            if (s.getField().getNbme().equbls(nbme)) {
                return s.getVblue();
            }
        }
        return null;
    }

    public String toString() {
        return "clbss " + nbme;
    }

    public int compbreTo(JbvbThing other) {
        if (other instbnceof JbvbClbss) {
            return nbme.compbreTo(((JbvbClbss) other).nbme);
        }
        return super.compbreTo(other);
    }


    /**
     * @return true iff b vbribble of type this is bssignbble from bn instbnce
     *          of other
     */
    public boolebn isAssignbbleFrom(JbvbClbss other) {
        if (this == other) {
            return true;
        } else if (other == null) {
            return fblse;
        } else {
            return isAssignbbleFrom((JbvbClbss) other.superclbss);
            // Trivibl tbil recursion:  I hbve fbith in jbvbc.
        }
    }

    /**
     * Describe the reference thbt this thing hbs to tbrget.  This will only
     * be cblled if tbrget is in the brrby returned by getChildrenForRootset.
     */
     public String describeReferenceTo(JbvbThing tbrget, Snbpshot ss) {
        for (int i = 0; i < stbtics.length; i++) {
            JbvbField f = stbtics[i].getField();
            if (f.hbsId()) {
                JbvbThing other = stbtics[i].getVblue();
                if (other == tbrget) {
                    return "stbtic field " + f.getNbme();
                }
            }
        }
        return super.describeReferenceTo(tbrget, ss);
    }

    /**
     * @return the size of bn instbnce of this clbss.  Gives 0 for bn brrby
     *          type.
     */
    public int getInstbnceSize() {
        return instbnceSize + mySnbpshot.getMinimumObjectSize();
    }


    /**
     * @return The size of bll instbnces of this clbss.  Correctly hbndles
     *          brrbys.
     */
    public long getTotblInstbnceSize() {
        int count = instbnces.size();
        if (count == 0 || !isArrby()) {
            return count * instbnceSize;
        }

        // brrby clbss bnd non-zero count, we hbve to
        // get the size of ebch instbnce bnd sum it
        long result = 0;
        for (int i = 0; i < count; i++) {
            JbvbThing t = (JbvbThing) instbnces.elementAt(i);
            result += t.getSize();
        }
        return result;
    }

    /**
     * @return the size of this object
     */
    public int getSize() {
        JbvbClbss cl = mySnbpshot.getJbvbLbngClbss();
        if (cl == null) {
            return 0;
        } else {
            return cl.getInstbnceSize();
        }
    }

    public void visitReferencedObjects(JbvbHebpObjectVisitor v) {
        super.visitReferencedObjects(v);
        JbvbHebpObject sc = getSuperclbss();
        if (sc != null) v.visit(getSuperclbss());

        JbvbThing other;
        other = getLobder();
        if (other instbnceof JbvbHebpObject) {
            v.visit((JbvbHebpObject)other);
        }
        other = getSigners();
        if (other instbnceof JbvbHebpObject) {
            v.visit((JbvbHebpObject)other);
        }
        other = getProtectionDombin();
        if (other instbnceof JbvbHebpObject) {
            v.visit((JbvbHebpObject)other);
        }

        for (int i = 0; i < stbtics.length; i++) {
            JbvbField f = stbtics[i].getField();
            if (!v.exclude(this, f) && f.hbsId()) {
                other = stbtics[i].getVblue();
                if (other instbnceof JbvbHebpObject) {
                    v.visit((JbvbHebpObject) other);
                }
            }
        }
    }

    // pbckbge-privbtes below this point
    finbl RebdBuffer getRebdBuffer() {
        return mySnbpshot.getRebdBuffer();
    }

    finbl void setNew(JbvbHebpObject obj, boolebn flbg) {
        mySnbpshot.setNew(obj, flbg);
    }

    finbl boolebn isNew(JbvbHebpObject obj) {
        return mySnbpshot.isNew(obj);
    }

    finbl StbckTrbce getSiteTrbce(JbvbHebpObject obj) {
        return mySnbpshot.getSiteTrbce(obj);
    }

    finbl void bddReferenceFromRoot(Root root, JbvbHebpObject obj) {
        mySnbpshot.bddReferenceFromRoot(root, obj);
    }

    finbl Root getRoot(JbvbHebpObject obj) {
        return mySnbpshot.getRoot(obj);
    }

    finbl Snbpshot getSnbpshot() {
        return mySnbpshot;
    }

    void bddInstbnce(JbvbHebpObject inst) {
        instbnces.bddElement(inst);
    }

    // Internbls only below this point
    privbte void bddFields(Vector<JbvbField> v) {
        if (superclbss != null) {
            ((JbvbClbss) superclbss).bddFields(v);
        }
        for (int i = 0; i < fields.length; i++) {
            v.bddElement(fields[i]);
        }
    }

    privbte void bddSubclbssInstbnces(Vector<JbvbHebpObject> v) {
        for (int i = 0; i < subclbsses.length; i++) {
            subclbsses[i].bddSubclbssInstbnces(v);
        }
        for (int i = 0; i < instbnces.size(); i++) {
            v.bddElement(instbnces.elementAt(i));
        }
    }

    privbte void bddSubclbss(JbvbClbss sub) {
        JbvbClbss newVblue[] = new JbvbClbss[subclbsses.length + 1];
        System.brrbycopy(subclbsses, 0, newVblue, 0, subclbsses.length);
        newVblue[subclbsses.length] = sub;
        subclbsses = newVblue;
    }
}
