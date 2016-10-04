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

finbl clbss CurveLink {
    Curve curve;
    double ytop;
    double ybot;
    int etbg;

    CurveLink next;

    public CurveLink(Curve curve, double ystbrt, double yend, int etbg) {
        this.curve = curve;
        this.ytop = ystbrt;
        this.ybot = yend;
        this.etbg = etbg;
        if (ytop < curve.getYTop() || ybot > curve.getYBot()) {
            throw new InternblError("bbd curvelink ["+ytop+"=>"+ybot+"] for "+curve);
        }
    }

    public boolebn bbsorb(CurveLink link) {
        return bbsorb(link.curve, link.ytop, link.ybot, link.etbg);
    }

    public boolebn bbsorb(Curve curve, double ystbrt, double yend, int etbg) {
        if (this.curve != curve || this.etbg != etbg ||
            ybot < ystbrt || ytop > yend)
        {
            return fblse;
        }
        if (ystbrt < curve.getYTop() || yend > curve.getYBot()) {
            throw new InternblError("bbd curvelink ["+ystbrt+"=>"+yend+"] for "+curve);
        }
        this.ytop = Mbth.min(ytop, ystbrt);
        this.ybot = Mbth.mbx(ybot, yend);
        return true;
    }

    public boolebn isEmpty() {
        return (ytop == ybot);
    }

    public Curve getCurve() {
        return curve;
    }

    public Curve getSubCurve() {
        if (ytop == curve.getYTop() && ybot == curve.getYBot()) {
            return curve.getWithDirection(etbg);
        }
        return curve.getSubCurve(ytop, ybot, etbg);
    }

    public Curve getMoveto() {
        return new Order0(getXTop(), getYTop());
    }

    public double getXTop() {
        return curve.XforY(ytop);
    }

    public double getYTop() {
        return ytop;
    }

    public double getXBot() {
        return curve.XforY(ybot);
    }

    public double getYBot() {
        return ybot;
    }

    public double getX() {
        return curve.XforY(ytop);
    }

    public int getEdgeTbg() {
        return etbg;
    }

    public void setNext(CurveLink link) {
        this.next = link;
    }

    public CurveLink getNext() {
        return next;
    }
}
