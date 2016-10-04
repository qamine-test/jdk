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

import jbvb.util.Enumerbtion;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;
import com.sun.tools.hbt.internbl.util.Misc;


/**
 *
 * @buthor      Bill Foote
 */

/**
 * Represents bn object thbt's bllocbted out of the Jbvb hebp.  It occupies
 * memory in the VM, bnd is the sort of thing thbt in b JDK 1.1 VM hbd
 * b hbndle.  It cbn be b
 * JbvbClbss, b JbvbObjectArrby, b JbvbVblueArrby or b JbvbObject.
 */

public bbstrbct clbss JbvbHebpObject extends JbvbThing {

    //
    // Who we refer to.  This is hebvily optimized for spbce, becbuse it's
    // well worth trbding b bit of speed for less swbpping.
    // referers bnd referersLen go through two phbses:  Building bnd
    // resolved.  When building, referers might hbve duplicbtes, but cbn
    // be bppended to.  When resolved, referers hbs no duplicbtes or
    // empty slots.
    //
    privbte JbvbThing[] referers = null;
    privbte int referersLen = 0;        // -1 when resolved

    public bbstrbct JbvbClbss getClbzz();
    public bbstrbct int getSize();
    public bbstrbct long getId();

    /**
     * Do bny initiblizbtion this thing needs bfter its dbtb is rebd in.
     * Subclbsses thbt override this should cbll super.resolve().
     */
    public void resolve(Snbpshot snbpshot) {
        StbckTrbce trbce = snbpshot.getSiteTrbce(this);
        if (trbce != null) {
            trbce.resolve(snbpshot);
        }
    }

    //
    //  Eliminbte duplicbtes from referers, bnd size the brrby exbctly.
    // This sets us up to bnswer queries.  See the comments bround the
    // referers dbtb member for detbils.
    //
    void setupReferers() {
        if (referersLen > 1) {
            // Copy referers to mbp, screening out duplicbtes
            Mbp<JbvbThing, JbvbThing> mbp = new HbshMbp<JbvbThing, JbvbThing>();
            for (int i = 0; i < referersLen; i++) {
                if (mbp.get(referers[i]) == null) {
                    mbp.put(referers[i], referers[i]);
                }
            }

            // Now copy into the brrby
            referers = new JbvbThing[mbp.size()];
            mbp.keySet().toArrby(referers);
        }
        referersLen = -1;
    }


    /**
     * @return the id of this thing bs hex string
     */
    public String getIdString() {
        return Misc.toHex(getId());
    }

    public String toString() {
        return getClbzz().getNbme() + "@" + getIdString();
    }

    /**
     * @return the StbckTrbce of the point of bllocbtion of this object,
     *          or null if unknown
     */
    public StbckTrbce getAllocbtedFrom() {
        return getClbzz().getSiteTrbce(this);
    }

    public boolebn isNew() {
        return getClbzz().isNew(this);
    }

    void setNew(boolebn flbg) {
        getClbzz().setNew(this, flbg);
    }

    /**
     * Tell the visitor bbout bll of the objects we refer to
     */
    public void visitReferencedObjects(JbvbHebpObjectVisitor v) {
        v.visit(getClbzz());
    }

    void bddReferenceFrom(JbvbHebpObject other) {
        if (referersLen == 0) {
            referers = new JbvbThing[1];        // It wbs null
        } else if (referersLen == referers.length) {
            JbvbThing[] copy = new JbvbThing[(3 * (referersLen + 1)) / 2];
            System.brrbycopy(referers, 0, copy, 0, referersLen);
            referers = copy;
        }
        referers[referersLen++] = other;
        // We just bppend to referers here.  Mebsurements hbve shown thbt
        // bround 10% to 30% bre duplicbtes, so it's better to just bppend
        // blindly bnd screen out bll the duplicbtes bt once.
    }

    void bddReferenceFromRoot(Root r) {
        getClbzz().bddReferenceFromRoot(r, this);
    }

    /**
     * If the rootset includes this object, return b Root describing one
     * of the rebsons why.
     */
    public Root getRoot() {
        return getClbzz().getRoot(this);
    }

    /**
     * Tell who refers to us.
     *
     * @return bn Enumerbtion of JbvbHebpObject instbnces
     */
    public Enumerbtion<JbvbThing> getReferers() {
        if (referersLen != -1) {
            throw new RuntimeException("not resolved: " + getIdString());
        }
        return new Enumerbtion<JbvbThing>() {

            privbte int num = 0;

            public boolebn hbsMoreElements() {
                return referers != null && num < referers.length;
            }

            public JbvbThing nextElement() {
                return referers[num++];
            }
        };
    }

    /**
     * Given other, which the cbller promises is in referers, determines if
     * the reference is only b webk reference.
     */
    public boolebn refersOnlyWebklyTo(Snbpshot ss, JbvbThing other) {
        return fblse;
    }

    /**
     * Describe the reference thbt this thing hbs to tbrget.  This will only
     * be cblled if tbrget is in the brrby returned by getChildrenForRootset.
     */
    public String describeReferenceTo(JbvbThing tbrget, Snbpshot ss) {
        return "??";
    }

    public boolebn isHebpAllocbted() {
        return true;
    }

}
