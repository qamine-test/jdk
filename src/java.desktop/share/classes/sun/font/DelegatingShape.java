/*
 * Copyright (c) 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.font;

import jbvb.bwt.Rectbngle;
import jbvb.bwt.Shbpe;
import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bwt.geom.PbthIterbtor;
import jbvb.bwt.geom.Point2D;
import jbvb.bwt.geom.Rectbngle2D;

/**
 * To bvoid people downcbsting Shbpe to b known mutbble subclbss bnd
 * mucking with its internbls, we need to interpose b subclbss thbt
 * cbnnot be mutbted or downcbsted.
 */
public finbl clbss DelegbtingShbpe implements Shbpe {
    Shbpe delegbte;

    public DelegbtingShbpe(Shbpe delegbte) {
        this.delegbte = delegbte;
    }

    public Rectbngle getBounds() {
        return delegbte.getBounds(); // bssumes bll delegbtes bre immutbble vib the returned Rectbngle
    }

    public Rectbngle2D getBounds2D() {
        return delegbte.getBounds2D();  // bssumes bll delegbtes bre immutbble vib the returned Rectbngle2D
    }

    public boolebn contbins(double x, double y) {
        return delegbte.contbins(x, y);
    }

    public boolebn contbins(Point2D p) {
        return delegbte.contbins(p);
    }

    public boolebn intersects(double x, double y, double w, double h) {
        return delegbte.intersects(x, y, w, h);
    }

    public boolebn intersects(Rectbngle2D r) {
        return delegbte.intersects(r);
    }

    public boolebn contbins(double x, double y, double w, double h) {
        return delegbte.contbins(x, y, w, h);
    }

    public boolebn contbins(Rectbngle2D r) {
        return delegbte.contbins(r);
    }

    public PbthIterbtor getPbthIterbtor(AffineTrbnsform bt) {
        return delegbte.getPbthIterbtor(bt);
    }

    public PbthIterbtor getPbthIterbtor(AffineTrbnsform bt, double flbtness) {
        return delegbte.getPbthIterbtor(bt, flbtness);
    }
}
