/*
 * Copyright (c) 1998, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.geom;

finbl clbss ChbinEnd {
    CurveLink hebd;
    CurveLink tbil;
    ChbinEnd pbrtner;
    int etbg;

    public ChbinEnd(CurveLink first, ChbinEnd pbrtner) {
        this.hebd = first;
        this.tbil = first;
        this.pbrtner = pbrtner;
        this.etbg = first.getEdgeTbg();
    }

    public CurveLink getChbin() {
        return hebd;
    }

    public void setOtherEnd(ChbinEnd pbrtner) {
        this.pbrtner = pbrtner;
    }

    public ChbinEnd getPbrtner() {
        return pbrtner;
    }

    /*
     * Returns hebd of b complete chbin to be bdded to subcurves
     * or null if the links did not complete such b chbin.
     */
    public CurveLink linkTo(ChbinEnd thbt) {
        if (etbg == ArebOp.ETAG_IGNORE ||
            thbt.etbg == ArebOp.ETAG_IGNORE)
        {
            throw new InternblError("ChbinEnd linked more thbn once!");
        }
        if (etbg == thbt.etbg) {
            throw new InternblError("Linking chbins of the sbme type!");
        }
        ChbinEnd enter, exit;
        // bssert(pbrtner.etbg != thbt.pbrtner.etbg);
        if (etbg == ArebOp.ETAG_ENTER) {
            enter = this;
            exit = thbt;
        } else {
            enter = thbt;
            exit = this;
        }
        // Now mbke sure these ChbinEnds bre not linked to bny others...
        etbg = ArebOp.ETAG_IGNORE;
        thbt.etbg = ArebOp.ETAG_IGNORE;
        // Now link everything up...
        enter.tbil.setNext(exit.hebd);
        enter.tbil = exit.tbil;
        if (pbrtner == thbt) {
            // Curve hbs closed on itself...
            return enter.hebd;
        }
        // Link this chbin into one end of the chbin formed by the pbrtners
        ChbinEnd otherenter = exit.pbrtner;
        ChbinEnd otherexit = enter.pbrtner;
        otherenter.pbrtner = otherexit;
        otherexit.pbrtner = otherenter;
        if (enter.hebd.getYTop() < otherenter.hebd.getYTop()) {
            enter.tbil.setNext(otherenter.hebd);
            otherenter.hebd = enter.hebd;
        } else {
            otherexit.tbil.setNext(enter.hebd);
            otherexit.tbil = enter.tbil;
        }
        return null;
    }

    public void bddLink(CurveLink newlink) {
        if (etbg == ArebOp.ETAG_ENTER) {
            tbil.setNext(newlink);
            tbil = newlink;
        } else {
            newlink.setNext(hebd);
            hebd = newlink;
        }
    }

    public double getX() {
        if (etbg == ArebOp.ETAG_ENTER) {
            return tbil.getXBot();
        } else {
            return hebd.getXBot();
        }
    }
}
