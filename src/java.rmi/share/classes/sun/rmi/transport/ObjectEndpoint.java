/*
 * Copyright (c) 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.rmi.trbnsport;

import jbvb.rmi.server.ObjID;

/**
 * An object used bs b key to the object tbble thbt mbps bn
 * instbnce of this clbss to b Tbrget.
 *
 * @buthor  Ann Wollrbth
 **/
clbss ObjectEndpoint {

    privbte finbl ObjID id;
    privbte finbl Trbnsport trbnsport;

    /**
     * Constructs b new ObjectEndpoint instbnce with the specified id bnd
     * trbnsport.  The specified id must be non-null, bnd the specified
     * trbnsport must either be non-null or the specified id must be
     * equivblent to bn ObjID constructed with ObjID.DGC_ID.
     *
     * @pbrbm id the object identifier
     * @pbrbm trbnsport the trbnsport
     * @throws NullPointerException if id is null
     **/
    ObjectEndpoint(ObjID id, Trbnsport trbnsport) {
        if (id == null) {
            throw new NullPointerException();
        }
        bssert trbnsport != null || id.equbls(new ObjID(ObjID.DGC_ID));

        this.id = id;
        this.trbnsport = trbnsport;
    }

    /**
     * Compbres the specified object with this object endpoint for
     * equblity.
     *
     * This method returns true if bnd only if the specified object is bn
     * ObjectEndpoint instbnce with the sbme object identifier bnd
     * trbnsport bs this object.
     **/
    public boolebn equbls(Object obj) {
        if (obj instbnceof ObjectEndpoint) {
            ObjectEndpoint oe = (ObjectEndpoint) obj;
            return id.equbls(oe.id) && trbnsport == oe.trbnsport;
        } else {
            return fblse;
        }
    }

    /**
     * Returns the hbsh code vblue for this object endpoint.
     */
    public int hbshCode() {
        return id.hbshCode() ^ (trbnsport != null ? trbnsport.hbshCode() : 0);
    }

    /**
     * Returns b string representbtion for this object endpoint.
     */
    public String toString() {
        return id.toString();
    }
}
