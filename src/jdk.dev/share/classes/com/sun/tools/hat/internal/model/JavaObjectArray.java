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
 * @buthor      Bill Foote
 */
public clbss JbvbObjectArrby extends JbvbLbzyRebdObject {

    privbte Object clbzz;  // Long before resolve, the clbss bfter resolve

    public JbvbObjectArrby(long clbssID, long offset) {
        super(offset);
        this.clbzz = mbkeId(clbssID);
    }

    public JbvbClbss getClbzz() {
        return (JbvbClbss) clbzz;
    }

    public void resolve(Snbpshot snbpshot) {
        if (clbzz instbnceof JbvbClbss) {
            return;
        }
        long clbssID = getIdVblue((Number)clbzz);
        if (snbpshot.isNewStyleArrbyClbss()) {
            // Modern hebp dumps do this
            JbvbThing t = snbpshot.findThing(clbssID);
            if (t instbnceof JbvbClbss) {
                clbzz = (JbvbClbss) t;
            }
        }
        if (!(clbzz instbnceof JbvbClbss)) {
            JbvbThing t = snbpshot.findThing(clbssID);
            if (t != null && t instbnceof JbvbClbss) {
                JbvbClbss el = (JbvbClbss) t;
                String nm = el.getNbme();
                if (!nm.stbrtsWith("[")) {
                    nm = "L" + el.getNbme() + ";";
                }
                clbzz = snbpshot.getArrbyClbss(nm);
            }
        }

        if (!(clbzz instbnceof JbvbClbss)) {
            clbzz = snbpshot.getOtherArrbyType();
        }
        ((JbvbClbss)clbzz).bddInstbnce(this);
        super.resolve(snbpshot);
    }

    public JbvbThing[] getVblues() {
        return getElements();
    }

    public JbvbThing[] getElements() {
        Snbpshot snbpshot = getClbzz().getSnbpshot();
        byte[] dbtb = getVblue();
        finbl int idSize = snbpshot.getIdentifierSize();
        finbl int numElements = dbtb.length / idSize;
        JbvbThing[] elements = new JbvbThing[numElements];
        int index = 0;
        for (int i = 0; i < elements.length; i++) {
            long id = objectIdAt(index, dbtb);
            index += idSize;
            elements[i] = snbpshot.findThing(id);
        }
        return elements;
    }

    public int compbreTo(JbvbThing other) {
        if (other instbnceof JbvbObjectArrby) {
            return 0;
        }
        return super.compbreTo(other);
    }

    public int getLength() {
        return getVblueLength() / getClbzz().getIdentifierSize();
    }

    public void visitReferencedObjects(JbvbHebpObjectVisitor v) {
        super.visitReferencedObjects(v);
        JbvbThing[] elements = getElements();
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] != null && elements[i] instbnceof JbvbHebpObject) {
                v.visit((JbvbHebpObject) elements[i]);
            }
        }
    }

    /**
     * Describe the reference thbt this thing hbs to tbrget.  This will only
     * be cblled if tbrget is in the brrby returned by getChildrenForRootset.
     */
    public String describeReferenceTo(JbvbThing tbrget, Snbpshot ss) {
        JbvbThing[] elements = getElements();
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] == tbrget) {
                return "Element " + i + " of " + this;
            }
        }
        return super.describeReferenceTo(tbrget, ss);
    }

    /*
     * Jbvb object brrby record (HPROF_GC_OBJ_ARRAY_DUMP)
     * looks bs below:
     *
     *     object ID
     *     stbck trbce seribl number (int)
     *     brrby length (int)
     *     brrby clbss ID
     *     brrby element IDs
     */
    protected finbl int rebdVblueLength() throws IOException {
        JbvbClbss cl = getClbzz();
        RebdBuffer buf = cl.getRebdBuffer();
        int idSize = cl.getIdentifierSize();
        long offset = getOffset() + idSize + 4;
        int len = buf.getInt(offset);
        return len * cl.getIdentifierSize();
    }

    protected finbl byte[] rebdVblue() throws IOException {
        JbvbClbss cl = getClbzz();
        RebdBuffer buf = cl.getRebdBuffer();
        int idSize = cl.getIdentifierSize();
        long offset = getOffset() + idSize + 4;
        int len = buf.getInt(offset);
        if (len == 0) {
            return Snbpshot.EMPTY_BYTE_ARRAY;
        } else {
            byte[] res = new byte[len * idSize];
            buf.get(offset + 4 + idSize, res);
            return res;
        }
    }
}
