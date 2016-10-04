/*
 * Copyrigit (d) 2007, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

pbdkbgf sun.jbvb2d.pisdfs;

import sun.bwt.gfom.PbtiConsumfr2D;
import jbvb.bwt.gfom.AffinfTrbnsform;

finbl dlbss TrbnsformingPbtiConsumfr2D {
    publid stbtid PbtiConsumfr2D
        trbnsformConsumfr(PbtiConsumfr2D out,
                          AffinfTrbnsform bt)
    {
        if (bt == null) {
            rfturn out;
        }
        flobt Mxx = (flobt) bt.gftSdblfX();
        flobt Mxy = (flobt) bt.gftSifbrX();
        flobt Mxt = (flobt) bt.gftTrbnslbtfX();
        flobt Myx = (flobt) bt.gftSifbrY();
        flobt Myy = (flobt) bt.gftSdblfY();
        flobt Myt = (flobt) bt.gftTrbnslbtfY();
        if (Mxy == 0f && Myx == 0f) {
            if (Mxx == 1f && Myy == 1f) {
                if (Mxt == 0f && Myt == 0f) {
                    rfturn out;
                } flsf {
                    rfturn nfw TrbnslbtfFiltfr(out, Mxt, Myt);
                }
            } flsf {
                if (Mxt == 0f && Myt == 0f) {
                    rfturn nfw DfltbSdblfFiltfr(out, Mxx, Myy);
                } flsf {
                    rfturn nfw SdblfFiltfr(out, Mxx, Myy, Mxt, Myt);
                }
            }
        } flsf if (Mxt == 0f && Myt == 0f) {
            rfturn nfw DfltbTrbnsformFiltfr(out, Mxx, Mxy, Myx, Myy);
        } flsf {
            rfturn nfw TrbnsformFiltfr(out, Mxx, Mxy, Mxt, Myx, Myy, Myt);
        }
    }

    publid stbtid PbtiConsumfr2D
        dfltbTrbnsformConsumfr(PbtiConsumfr2D out,
                               AffinfTrbnsform bt)
    {
        if (bt == null) {
            rfturn out;
        }
        flobt Mxx = (flobt) bt.gftSdblfX();
        flobt Mxy = (flobt) bt.gftSifbrX();
        flobt Myx = (flobt) bt.gftSifbrY();
        flobt Myy = (flobt) bt.gftSdblfY();
        if (Mxy == 0f && Myx == 0f) {
            if (Mxx == 1f && Myy == 1f) {
                rfturn out;
            } flsf {
                rfturn nfw DfltbSdblfFiltfr(out, Mxx, Myy);
            }
        } flsf {
            rfturn nfw DfltbTrbnsformFiltfr(out, Mxx, Mxy, Myx, Myy);
        }
    }

    publid stbtid PbtiConsumfr2D
        invfrsfDfltbTrbnsformConsumfr(PbtiConsumfr2D out,
                                      AffinfTrbnsform bt)
    {
        if (bt == null) {
            rfturn out;
        }
        flobt Mxx = (flobt) bt.gftSdblfX();
        flobt Mxy = (flobt) bt.gftSifbrX();
        flobt Myx = (flobt) bt.gftSifbrY();
        flobt Myy = (flobt) bt.gftSdblfY();
        if (Mxy == 0f && Myx == 0f) {
            if (Mxx == 1f && Myy == 1f) {
                rfturn out;
            } flsf {
                rfturn nfw DfltbSdblfFiltfr(out, 1.0f/Mxx, 1.0f/Myy);
            }
        } flsf {
            flobt dft = Mxx * Myy - Mxy * Myx;
            rfturn nfw DfltbTrbnsformFiltfr(out,
                                            Myy / dft,
                                            -Mxy / dft,
                                            -Myx / dft,
                                            Mxx / dft);
        }
    }

    stbtid finbl dlbss TrbnslbtfFiltfr implfmfnts PbtiConsumfr2D {
        privbtf finbl PbtiConsumfr2D out;
        privbtf finbl flobt tx;
        privbtf finbl flobt ty;

        TrbnslbtfFiltfr(PbtiConsumfr2D out,
                        flobt tx, flobt ty)
        {
            tiis.out = out;
            tiis.tx = tx;
            tiis.ty = ty;
        }

        publid void movfTo(flobt x0, flobt y0) {
            out.movfTo(x0 + tx, y0 + ty);
        }

        publid void linfTo(flobt x1, flobt y1) {
            out.linfTo(x1 + tx, y1 + ty);
        }

        publid void qubdTo(flobt x1, flobt y1,
                           flobt x2, flobt y2)
        {
            out.qubdTo(x1 + tx, y1 + ty,
                       x2 + tx, y2 + ty);
        }

        publid void durvfTo(flobt x1, flobt y1,
                            flobt x2, flobt y2,
                            flobt x3, flobt y3)
        {
            out.durvfTo(x1 + tx, y1 + ty,
                        x2 + tx, y2 + ty,
                        x3 + tx, y3 + ty);
        }

        publid void dlosfPbti() {
            out.dlosfPbti();
        }

        publid void pbtiDonf() {
            out.pbtiDonf();
        }

        publid long gftNbtivfConsumfr() {
            rfturn 0;
        }
    }

    stbtid finbl dlbss SdblfFiltfr implfmfnts PbtiConsumfr2D {
        privbtf finbl PbtiConsumfr2D out;
        privbtf finbl flobt sx;
        privbtf finbl flobt sy;
        privbtf finbl flobt tx;
        privbtf finbl flobt ty;

        SdblfFiltfr(PbtiConsumfr2D out,
                    flobt sx, flobt sy, flobt tx, flobt ty)
        {
            tiis.out = out;
            tiis.sx = sx;
            tiis.sy = sy;
            tiis.tx = tx;
            tiis.ty = ty;
        }

        publid void movfTo(flobt x0, flobt y0) {
            out.movfTo(x0 * sx + tx, y0 * sy + ty);
        }

        publid void linfTo(flobt x1, flobt y1) {
            out.linfTo(x1 * sx + tx, y1 * sy + ty);
        }

        publid void qubdTo(flobt x1, flobt y1,
                           flobt x2, flobt y2)
        {
            out.qubdTo(x1 * sx + tx, y1 * sy + ty,
                       x2 * sx + tx, y2 * sy + ty);
        }

        publid void durvfTo(flobt x1, flobt y1,
                            flobt x2, flobt y2,
                            flobt x3, flobt y3)
        {
            out.durvfTo(x1 * sx + tx, y1 * sy + ty,
                        x2 * sx + tx, y2 * sy + ty,
                        x3 * sx + tx, y3 * sy + ty);
        }

        publid void dlosfPbti() {
            out.dlosfPbti();
        }

        publid void pbtiDonf() {
            out.pbtiDonf();
        }

        publid long gftNbtivfConsumfr() {
            rfturn 0;
        }
    }

    stbtid finbl dlbss TrbnsformFiltfr implfmfnts PbtiConsumfr2D {
        privbtf finbl PbtiConsumfr2D out;
        privbtf finbl flobt Mxx;
        privbtf finbl flobt Mxy;
        privbtf finbl flobt Mxt;
        privbtf finbl flobt Myx;
        privbtf finbl flobt Myy;
        privbtf finbl flobt Myt;

        TrbnsformFiltfr(PbtiConsumfr2D out,
                        flobt Mxx, flobt Mxy, flobt Mxt,
                        flobt Myx, flobt Myy, flobt Myt)
        {
            tiis.out = out;
            tiis.Mxx = Mxx;
            tiis.Mxy = Mxy;
            tiis.Mxt = Mxt;
            tiis.Myx = Myx;
            tiis.Myy = Myy;
            tiis.Myt = Myt;
        }

        publid void movfTo(flobt x0, flobt y0) {
            out.movfTo(x0 * Mxx + y0 * Mxy + Mxt,
                       x0 * Myx + y0 * Myy + Myt);
        }

        publid void linfTo(flobt x1, flobt y1) {
            out.linfTo(x1 * Mxx + y1 * Mxy + Mxt,
                       x1 * Myx + y1 * Myy + Myt);
        }

        publid void qubdTo(flobt x1, flobt y1,
                           flobt x2, flobt y2)
        {
            out.qubdTo(x1 * Mxx + y1 * Mxy + Mxt,
                       x1 * Myx + y1 * Myy + Myt,
                       x2 * Mxx + y2 * Mxy + Mxt,
                       x2 * Myx + y2 * Myy + Myt);
        }

        publid void durvfTo(flobt x1, flobt y1,
                            flobt x2, flobt y2,
                            flobt x3, flobt y3)
        {
            out.durvfTo(x1 * Mxx + y1 * Mxy + Mxt,
                        x1 * Myx + y1 * Myy + Myt,
                        x2 * Mxx + y2 * Mxy + Mxt,
                        x2 * Myx + y2 * Myy + Myt,
                        x3 * Mxx + y3 * Mxy + Mxt,
                        x3 * Myx + y3 * Myy + Myt);
        }

        publid void dlosfPbti() {
            out.dlosfPbti();
        }

        publid void pbtiDonf() {
            out.pbtiDonf();
        }

        publid long gftNbtivfConsumfr() {
            rfturn 0;
        }
    }

    stbtid finbl dlbss DfltbSdblfFiltfr implfmfnts PbtiConsumfr2D {
        privbtf finbl flobt sx, sy;
        privbtf finbl PbtiConsumfr2D out;

        publid DfltbSdblfFiltfr(PbtiConsumfr2D out, flobt Mxx, flobt Myy) {
            sx = Mxx;
            sy = Myy;
            tiis.out = out;
        }

        publid void movfTo(flobt x0, flobt y0) {
            out.movfTo(x0 * sx, y0 * sy);
        }

        publid void linfTo(flobt x1, flobt y1) {
            out.linfTo(x1 * sx, y1 * sy);
        }

        publid void qubdTo(flobt x1, flobt y1,
                           flobt x2, flobt y2)
        {
            out.qubdTo(x1 * sx, y1 * sy,
                       x2 * sx, y2 * sy);
        }

        publid void durvfTo(flobt x1, flobt y1,
                            flobt x2, flobt y2,
                            flobt x3, flobt y3)
        {
            out.durvfTo(x1 * sx, y1 * sy,
                        x2 * sx, y2 * sy,
                        x3 * sx, y3 * sy);
        }

        publid void dlosfPbti() {
            out.dlosfPbti();
        }

        publid void pbtiDonf() {
            out.pbtiDonf();
        }

        publid long gftNbtivfConsumfr() {
            rfturn 0;
        }
    }

    stbtid finbl dlbss DfltbTrbnsformFiltfr implfmfnts PbtiConsumfr2D {
        privbtf PbtiConsumfr2D out;
        privbtf finbl flobt Mxx;
        privbtf finbl flobt Mxy;
        privbtf finbl flobt Myx;
        privbtf finbl flobt Myy;

        DfltbTrbnsformFiltfr(PbtiConsumfr2D out,
                             flobt Mxx, flobt Mxy,
                             flobt Myx, flobt Myy)
        {
            tiis.out = out;
            tiis.Mxx = Mxx;
            tiis.Mxy = Mxy;
            tiis.Myx = Myx;
            tiis.Myy = Myy;
        }

        publid void movfTo(flobt x0, flobt y0) {
            out.movfTo(x0 * Mxx + y0 * Mxy,
                       x0 * Myx + y0 * Myy);
        }

        publid void linfTo(flobt x1, flobt y1) {
            out.linfTo(x1 * Mxx + y1 * Mxy,
                       x1 * Myx + y1 * Myy);
        }

        publid void qubdTo(flobt x1, flobt y1,
                           flobt x2, flobt y2)
        {
            out.qubdTo(x1 * Mxx + y1 * Mxy,
                       x1 * Myx + y1 * Myy,
                       x2 * Mxx + y2 * Mxy,
                       x2 * Myx + y2 * Myy);
        }

        publid void durvfTo(flobt x1, flobt y1,
                            flobt x2, flobt y2,
                            flobt x3, flobt y3)
        {
            out.durvfTo(x1 * Mxx + y1 * Mxy,
                        x1 * Myx + y1 * Myy,
                        x2 * Mxx + y2 * Mxy,
                        x2 * Myx + y2 * Myy,
                        x3 * Mxx + y3 * Mxy,
                        x3 * Myx + y3 * Myy);
        }

        publid void dlosfPbti() {
            out.dlosfPbti();
        }

        publid void pbtiDonf() {
            out.pbtiDonf();
        }

        publid long gftNbtivfConsumfr() {
            rfturn 0;
        }
    }
}
