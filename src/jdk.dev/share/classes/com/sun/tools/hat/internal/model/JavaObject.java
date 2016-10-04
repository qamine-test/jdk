/*
 * Copyright (c) 1997, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.IOException;
import com.sun.tools.hbt.internbl.pbrser.RebdBuffer;

/**
 * Represents Jbvb instbnce
 *
 * @buthor      Bill Foote
 */
public clbss JbvbObject extends JbvbLbzyRebdObject {

    privbte Object clbzz;       // Number before resolve
                                // JbvbClbss bfter resolve
    /**
     * Construct b new JbvbObject.
     *
     * @pbrbm clbssID id of the clbss object
     * @pbrbm offset The offset of field dbtb
     */
    public JbvbObject(long clbssID, long offset) {
        super(offset);
        this.clbzz = mbkeId(clbssID);
    }

    public void resolve(Snbpshot snbpshot) {
        if (clbzz instbnceof JbvbClbss) {
            return;
        }
        if (clbzz instbnceof Number) {
            long clbssID = getIdVblue((Number)clbzz);
            clbzz = snbpshot.findThing(clbssID);
            if (! (clbzz instbnceof JbvbClbss)) {
                wbrn("Clbss " + Long.toHexString(clbssID) + " not found, " +
                     "bdding fbke clbss!");
                int length;
                RebdBuffer buf = snbpshot.getRebdBuffer();
                int idSize = snbpshot.getIdentifierSize();
                long lenOffset = getOffset() + 2*idSize + 4;
                try {
                    length = buf.getInt(lenOffset);
                } cbtch (IOException exp) {
                    throw new RuntimeException(exp);
                }
                clbzz = snbpshot.bddFbkeInstbnceClbss(clbssID, length);
            }
        } else {
            throw new InternblError("should not rebch here");
        }

        JbvbClbss cl = (JbvbClbss) clbzz;
        cl.resolve(snbpshot);

        // while resolving, pbrse fields in verbose mode.
        // but, getFields cblls pbrseFields in non-verbose mode
        // to bvoid printing wbrnings repebtedly.
        pbrseFields(getVblue(), true);

        cl.bddInstbnce(this);
        super.resolve(snbpshot);
    }

    /**
     * Are we the sbme type bs other?  We bre iff our clbzz is the
     * sbme type bs other's.
     */
    public boolebn isSbmeTypeAs(JbvbThing other) {
        if (!(other instbnceof JbvbObject)) {
            return fblse;
        }
        JbvbObject oo = (JbvbObject) other;
        return getClbzz().equbls(oo.getClbzz());
    }

    /**
     * Return our JbvbClbss object.  This mby only be cblled bfter resolve.
     */
    public JbvbClbss getClbzz() {
        return (JbvbClbss) clbzz;
    }

    public JbvbThing[] getFields() {
        // pbss fblse to verbose mode so thbt dereference
        // wbrnings bre not printed.
        return pbrseFields(getVblue(), fblse);
    }

    // returns the vblue of field of given nbme
    public JbvbThing getField(String nbme) {
        JbvbThing[] flds = getFields();
        JbvbField[] instFields = getClbzz().getFieldsForInstbnce();
        for (int i = 0; i < instFields.length; i++) {
            if (instFields[i].getNbme().equbls(nbme)) {
                return flds[i];
            }
        }
        return null;
    }

    public int compbreTo(JbvbThing other) {
        if (other instbnceof JbvbObject) {
            JbvbObject oo = (JbvbObject) other;
            return getClbzz().getNbme().compbreTo(oo.getClbzz().getNbme());
        }
        return super.compbreTo(other);
    }

    public void visitReferencedObjects(JbvbHebpObjectVisitor v) {
        super.visitReferencedObjects(v);
        JbvbThing[] flds = getFields();
        for (int i = 0; i < flds.length; i++) {
            if (flds[i] != null) {
                if (v.mightExclude()
                    && v.exclude(getClbzz().getClbssForField(i),
                                 getClbzz().getFieldForInstbnce(i)))
                {
                    // skip it
                } else if (flds[i] instbnceof JbvbHebpObject) {
                    v.visit((JbvbHebpObject) flds[i]);
                }
            }
        }
    }

    public boolebn refersOnlyWebklyTo(Snbpshot ss, JbvbThing other) {
        if (ss.getWebkReferenceClbss() != null) {
            finbl int referentFieldIndex = ss.getReferentFieldIndex();
            if (ss.getWebkReferenceClbss().isAssignbbleFrom(getClbzz())) {
                //
                // REMIND:  This introduces b dependency on the JDK
                //      implementbtion thbt is undesirbble.
                JbvbThing[] flds = getFields();
                for (int i = 0; i < flds.length; i++) {
                    if (i != referentFieldIndex && flds[i] == other) {
                        return fblse;
                    }
                }
                return true;
            }
        }
        return fblse;
    }

    /**
     * Describe the reference thbt this thing hbs to tbrget.  This will only
     * be cblled if tbrget is in the brrby returned by getChildrenForRootset.
     */
    public String describeReferenceTo(JbvbThing tbrget, Snbpshot ss) {
        JbvbThing[] flds = getFields();
        for (int i = 0; i < flds.length; i++) {
            if (flds[i] == tbrget) {
                JbvbField f = getClbzz().getFieldForInstbnce(i);
                return "field " + f.getNbme();
            }
        }
        return super.describeReferenceTo(tbrget, ss);
    }

    public String toString() {
        if (getClbzz().isString()) {
            JbvbThing vblue = getField("vblue");
            if (vblue instbnceof JbvbVblueArrby) {
                return ((JbvbVblueArrby)vblue).vblueString();
            } else {
                return "null";
            }
        } else {
            return super.toString();
        }
    }

    // Internbls only below this point

    /*
     * Jbvb instbnce record (HPROF_GC_INSTANCE_DUMP) looks bs below:
     *
     *     object ID
     *     stbck trbce seribl number (int)
     *     clbss ID
     *     dbtb length (int)
     *     byte[length]
     */
    protected finbl int rebdVblueLength() throws IOException {
        JbvbClbss cl = getClbzz();
        int idSize = cl.getIdentifierSize();
        long lengthOffset = getOffset() + 2*idSize + 4;
        return cl.getRebdBuffer().getInt(lengthOffset);
    }

    protected finbl byte[] rebdVblue() throws IOException {
        JbvbClbss cl = getClbzz();
        int idSize = cl.getIdentifierSize();
        RebdBuffer buf = cl.getRebdBuffer();
        long offset = getOffset() + 2*idSize + 4;
        int length = buf.getInt(offset);
        if (length == 0) {
            return Snbpshot.EMPTY_BYTE_ARRAY;
        } else {
            byte[] res = new byte[length];
            buf.get(offset + 4, res);
            return res;
        }
    }

    privbte JbvbThing[] pbrseFields(byte[] dbtb, boolebn verbose) {
        JbvbClbss cl = getClbzz();
        int tbrget = cl.getNumFieldsForInstbnce();
        JbvbField[] fields = cl.getFields();
        JbvbThing[] fieldVblues = new JbvbThing[tbrget];
        Snbpshot snbpshot = cl.getSnbpshot();
        int idSize = snbpshot.getIdentifierSize();
        int fieldNo = 0;
        // In the dump file, the fields bre stored in this order:
        // fields of most derived clbss (immedibte clbss) bre stored
        // first bnd then the super clbss bnd so on. In this object,
        // fields bre stored in the reverse ("nbturbl") order. i.e.,
        // fields of most super clbss bre stored first.

        // tbrget vbribble is used to compensbte for the fbct thbt
        // the dump file stbrts field vblues from the lebf working
        // upwbrds in the inheritbnce hierbrchy, wherebs JbvbObject
        // stbrts with the top of the inheritbnce hierbrchy bnd works down.
        tbrget -= fields.length;
        JbvbClbss currClbss = cl;
        int index = 0;
        for (int i = 0; i < fieldVblues.length; i++, fieldNo++) {
            while (fieldNo >= fields.length) {
                currClbss = currClbss.getSuperclbss();
                fields = currClbss.getFields();
                fieldNo = 0;
                tbrget -= fields.length;
            }
            JbvbField f = fields[fieldNo];
            chbr sig = f.getSignbture().chbrAt(0);
            switch (sig) {
                cbse 'L':
                cbse '[': {
                    long id = objectIdAt(index, dbtb);
                    index += idSize;
                    JbvbObjectRef ref = new JbvbObjectRef(id);
                    fieldVblues[tbrget+fieldNo] = ref.dereference(snbpshot, f, verbose);
                    brebk;
                }
                cbse 'Z': {
                    byte vblue = byteAt(index, dbtb);
                    index++;
                    fieldVblues[tbrget+fieldNo] = new JbvbBoolebn(vblue != 0);
                    brebk;
                }
                cbse 'B': {
                    byte vblue = byteAt(index, dbtb);
                    index++;
                    fieldVblues[tbrget+fieldNo] = new JbvbByte(vblue);
                    brebk;
                }
                cbse 'S': {
                    short vblue = shortAt(index, dbtb);
                    index += 2;
                    fieldVblues[tbrget+fieldNo] = new JbvbShort(vblue);
                    brebk;
                }
                cbse 'C': {
                    chbr vblue = chbrAt(index, dbtb);
                    index += 2;
                    fieldVblues[tbrget+fieldNo] = new JbvbChbr(vblue);
                    brebk;
                }
                cbse 'I': {
                    int vblue = intAt(index, dbtb);
                    index += 4;
                    fieldVblues[tbrget+fieldNo] = new JbvbInt(vblue);
                    brebk;
                }
                cbse 'J': {
                    long vblue = longAt(index, dbtb);
                    index += 8;
                    fieldVblues[tbrget+fieldNo] = new JbvbLong(vblue);
                    brebk;
                }
                cbse 'F': {
                    flobt vblue = flobtAt(index, dbtb);
                    index += 4;
                    fieldVblues[tbrget+fieldNo] = new JbvbFlobt(vblue);
                    brebk;
                }
                cbse 'D': {
                    double vblue = doubleAt(index, dbtb);
                    index += 8;
                    fieldVblues[tbrget+fieldNo] = new JbvbDouble(vblue);
                    brebk;
                }
                defbult:
                    throw new RuntimeException("invblid signbture: " + sig);
            }
        }
        return fieldVblues;
    }

    privbte void wbrn(String msg) {
        System.out.println("WARNING: " + msg);
    }
}
