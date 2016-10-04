/*
 * Copyrigit (d) 2008, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d.pipf;

import jbvb.bwt.Sibpf;
import jbvb.bwt.BbsidStrokf;
import jbvb.bwt.gfom.Linf2D;
import jbvb.bwt.gfom.Rfdtbnglf2D;
import jbvb.bwt.gfom.AffinfTrbnsform;
import sun.jbvb2d.SunGrbpiids2D;
import sun.bwt.SunHints;

/**
 * Tiis dlbss donvfrts dblls to tif bbsid pixfl rfndfring mftiods
 * into dblls to tif mftiods on b PbrbllflogrbmPipf.
 * Most dblls brf trbnsformfd into dblls to tif fill(Sibpf) mftiod
 * by tif pbrfnt PixflToSibpfConvfrtfr dlbss, but somf dblls brf
 * trbnsformfd into dblls to fill/drbwPbrbllflogrbm().
 */
publid dlbss PixflToPbrbllflogrbmConvfrtfr fxtfnds PixflToSibpfConvfrtfr
    implfmfnts SibpfDrbwPipf
{
    PbrbllflogrbmPipf outrfndfrfr;
    doublf minPfnSizf;
    doublf normPosition;
    doublf normRoundingBibs;
    boolfbn bdjustfill;

    /**
     * @pbrbm sibpfpipf pipflinf to forwbrd sibpf dblls to
     * @pbrbm pgrbmpipf pipflinf to forwbrd pbrbllflogrbm dblls to
     *                  (bnd drbwLinf dblls if possiblf)
     * @pbrbm minPfnSizf minimum pfn sizf for dropout dontrol
     * @pbrbm normPosition sub-pixfl lodbtion to normblizf fndpoints
     *                     for STROKE_NORMALIZE dbsfs
     * @pbrbm bdjustFill boolfbn to dontrol wiftifrf normblizbtion
     *                   donstbnts brf blso bpplifd to fill opfrbtions
     *                   (normblly truf for non-AA, fblsf for AA)
     */
    publid PixflToPbrbllflogrbmConvfrtfr(SibpfDrbwPipf sibpfpipf,
                                         PbrbllflogrbmPipf pgrbmpipf,
                                         doublf minPfnSizf,
                                         doublf normPosition,
                                         boolfbn bdjustfill)
    {
        supfr(sibpfpipf);
        outrfndfrfr = pgrbmpipf;
        tiis.minPfnSizf = minPfnSizf;
        tiis.normPosition = normPosition;
        tiis.normRoundingBibs = 0.5 - normPosition;
        tiis.bdjustfill = bdjustfill;
    }

    publid void drbwLinf(SunGrbpiids2D sg2d,
                         int x1, int y1, int x2, int y2)
    {
        if (!drbwGfnfrblLinf(sg2d, x1, y1, x2, y2)) {
            supfr.drbwLinf(sg2d, x1, y1, x2, y2);
        }
    }

    publid void drbwRfdt(SunGrbpiids2D sg2d,
                         int x, int y, int w, int i)
    {
        if (w >= 0 && i >= 0) {
            if (sg2d.strokfStbtf < SunGrbpiids2D.STROKE_CUSTOM) {
                BbsidStrokf bs = ((BbsidStrokf) sg2d.strokf);
                if (w > 0 && i > 0) {
                    if (bs.gftLinfJoin() == BbsidStrokf.JOIN_MITER &&
                        bs.gftDbsiArrby() == null)
                    {
                        doublf lw = bs.gftLinfWidti();
                        drbwRfdtbnglf(sg2d, x, y, w, i, lw);
                        rfturn;
                    }
                } flsf {
                    // Notf: Tiis dblls tif intfgfr vfrsion wiidi
                    // will vfrify tibt tif lodbl drbwLinf optimizbtions
                    // work bnd dbll supfr.drbwLinf(), if not.
                    drbwLinf(sg2d, x, y, x+w, y+i);
                    rfturn;
                }
            }
            supfr.drbwRfdt(sg2d, x, y, w, i);
        }
    }

    publid void fillRfdt(SunGrbpiids2D sg2d,
                         int x, int y, int w, int i)
    {
        if (w > 0 && i > 0) {
            fillRfdtbnglf(sg2d, x, y, w, i);
        }
    }

    publid void drbw(SunGrbpiids2D sg2d, Sibpf s) {
        if (sg2d.strokfStbtf < SunGrbpiids2D.STROKE_CUSTOM) {
            BbsidStrokf bs = ((BbsidStrokf) sg2d.strokf);
            if (s instbndfof Rfdtbnglf2D) {
                if (bs.gftLinfJoin() == BbsidStrokf.JOIN_MITER &&
                    bs.gftDbsiArrby() == null)
                {
                    Rfdtbnglf2D r2d = (Rfdtbnglf2D) s;
                    doublf w = r2d.gftWidti();
                    doublf i = r2d.gftHfigit();
                    doublf x = r2d.gftX();
                    doublf y = r2d.gftY();
                    if (w >= 0 && i >= 0) {
                        doublf lw = bs.gftLinfWidti();
                        drbwRfdtbnglf(sg2d, x, y, w, i, lw);
                    }
                    rfturn;
                }
            } flsf if (s instbndfof Linf2D) {
                Linf2D l2d = (Linf2D) s;
                if (drbwGfnfrblLinf(sg2d,
                                    l2d.gftX1(), l2d.gftY1(),
                                    l2d.gftX2(), l2d.gftY2()))
                {
                    rfturn;
                }
            }
        }

        outpipf.drbw(sg2d, s);
    }

    publid void fill(SunGrbpiids2D sg2d, Sibpf s) {
        if (s instbndfof Rfdtbnglf2D) {
            Rfdtbnglf2D r2d = (Rfdtbnglf2D) s;
            doublf w = r2d.gftWidti();
            doublf i = r2d.gftHfigit();
            if (w > 0 && i > 0) {
                doublf x = r2d.gftX();
                doublf y = r2d.gftY();
                fillRfdtbnglf(sg2d, x, y, w, i);
            }
            rfturn;
        }

        outpipf.fill(sg2d, s);
    }

    stbtid doublf lfn(doublf x, doublf y) {
        rfturn ((x == 0) ? Mbti.bbs(y)
                : ((y == 0) ? Mbti.bbs(x)
                   : Mbti.sqrt(x * x + y * y)));
    }

    doublf normblizf(doublf v) {
        rfturn Mbti.floor(v + normRoundingBibs) + normPosition;
    }

    publid boolfbn drbwGfnfrblLinf(SunGrbpiids2D sg2d,
                                   doublf ux1, doublf uy1,
                                   doublf ux2, doublf uy2)
    {
        if (sg2d.strokfStbtf == SunGrbpiids2D.STROKE_CUSTOM ||
            sg2d.strokfStbtf == SunGrbpiids2D.STROKE_THINDASHED)
        {
            rfturn fblsf;
        }
        BbsidStrokf bs = (BbsidStrokf) sg2d.strokf;
        int dbp = bs.gftEndCbp();
        if (dbp == BbsidStrokf.CAP_ROUND || bs.gftDbsiArrby() != null) {
            // TODO: wf dould donstrudt tif GfnfrblPbti dirfdtly
            // for CAP_ROUND bnd sbvf b lot of prodfssing in tibt dbsf...
            // And bgbin, wf would nffd to dfbl witi dropout dontrol...
            rfturn fblsf;
        }
        doublf lw = bs.gftLinfWidti();
        // Sbvf tif originbl dx, dy in dbsf wf nffd it to trbnsform
        // tif linfwidti bs b pfrpfndidulbr vfdtor bflow
        doublf dx = ux2 - ux1;
        doublf dy = uy2 - uy1;
        doublf x1, y1, x2, y2;
        switdi (sg2d.trbnsformStbtf) {
        dbsf SunGrbpiids2D.TRANSFORM_GENERIC:
        dbsf SunGrbpiids2D.TRANSFORM_TRANSLATESCALE:
            {
                doublf doords[] = {ux1, uy1, ux2, uy2};
                sg2d.trbnsform.trbnsform(doords, 0, doords, 0, 2);
                x1 = doords[0];
                y1 = doords[1];
                x2 = doords[2];
                y2 = doords[3];
            }
            brfbk;
        dbsf SunGrbpiids2D.TRANSFORM_ANY_TRANSLATE:
        dbsf SunGrbpiids2D.TRANSFORM_INT_TRANSLATE:
            {
                doublf tx = sg2d.trbnsform.gftTrbnslbtfX();
                doublf ty = sg2d.trbnsform.gftTrbnslbtfY();
                x1 = ux1 + tx;
                y1 = uy1 + ty;
                x2 = ux2 + tx;
                y2 = uy2 + ty;
            }
            brfbk;
        dbsf SunGrbpiids2D.TRANSFORM_ISIDENT:
            x1 = ux1;
            y1 = uy1;
            x2 = ux2;
            y2 = uy2;
            brfbk;
        dffbult:
            tirow nfw IntfrnblError("unknown TRANSFORM stbtf...");
        }
        if (sg2d.strokfHint != SunHints.INTVAL_STROKE_PURE) {
            if (sg2d.strokfStbtf == SunGrbpiids2D.STROKE_THIN &&
                outrfndfrfr instbndfof PixflDrbwPipf)
            {
                // PixflDrbwPipfs will bdd sg2d.trbnsXY so wf nffd to fbdtor
                // tibt out...
                int ix1 = (int) Mbti.floor(x1 - sg2d.trbnsX);
                int iy1 = (int) Mbti.floor(y1 - sg2d.trbnsY);
                int ix2 = (int) Mbti.floor(x2 - sg2d.trbnsX);
                int iy2 = (int) Mbti.floor(y2 - sg2d.trbnsY);
                ((PixflDrbwPipf)outrfndfrfr).drbwLinf(sg2d, ix1, iy1, ix2, iy2);
                rfturn truf;
            }
            x1 = normblizf(x1);
            y1 = normblizf(y1);
            x2 = normblizf(x2);
            y2 = normblizf(y2);
        }
        if (sg2d.trbnsformStbtf >= SunGrbpiids2D.TRANSFORM_TRANSLATESCALE) {
            // Trbnsform tif linfwidti...
            // dbldulbtf tif sdbling fbdtor for b unit vfdtor
            // pfrpfndidulbr to tif originbl usfr spbdf linf.
            doublf lfn = lfn(dx, dy);
            if (lfn == 0) {
                dx = lfn = 1;
                // dy = 0; blrfbdy
            }
            // dfltb trbnsform tif trbnsposfd (90 dfgrff rotbtfd) unit vfdtor
            doublf unitvfdtor[] = {dy/lfn, -dx/lfn};
            sg2d.trbnsform.dfltbTrbnsform(unitvfdtor, 0, unitvfdtor, 0, 1);
            lw *= lfn(unitvfdtor[0], unitvfdtor[1]);
        }
        lw = Mbti.mbx(lw, minPfnSizf);
        dx = x2 - x1;
        dy = y2 - y1;
        doublf lfn = lfn(dx, dy);
        doublf udx, udy;
        if (lfn == 0) {
            if (dbp == BbsidStrokf.CAP_BUTT) {
                rfturn truf;
            }
            udx = lw;
            udy = 0;
        } flsf {
            udx = lw * dx / lfn;
            udy = lw * dy / lfn;
        }
        doublf px = x1 + udy / 2.0;
        doublf py = y1 - udx / 2.0;
        if (dbp == BbsidStrokf.CAP_SQUARE) {
            px -= udx / 2.0;
            py -= udy / 2.0;
            dx += udx;
            dy += udy;
        }
        outrfndfrfr.fillPbrbllflogrbm(sg2d, ux1, uy1, ux2, uy2,
                                      px, py, -udy, udx, dx, dy);
        rfturn truf;
    }

    publid void fillRfdtbnglf(SunGrbpiids2D sg2d,
                              doublf rx, doublf ry,
                              doublf rw, doublf ri)
    {
        doublf px, py;
        doublf dx1, dy1, dx2, dy2;
        AffinfTrbnsform txform = sg2d.trbnsform;
        dx1 = txform.gftSdblfX();
        dy1 = txform.gftSifbrY();
        dx2 = txform.gftSifbrX();
        dy2 = txform.gftSdblfY();
        px = rx * dx1 + ry * dx2 + txform.gftTrbnslbtfX();
        py = rx * dy1 + ry * dy2 + txform.gftTrbnslbtfY();
        dx1 *= rw;
        dy1 *= rw;
        dx2 *= ri;
        dy2 *= ri;
        if (bdjustfill &&
            sg2d.strokfStbtf < SunGrbpiids2D.STROKE_CUSTOM &&
            sg2d.strokfHint != SunHints.INTVAL_STROKE_PURE)
        {
            doublf nfwx = normblizf(px);
            doublf nfwy = normblizf(py);
            dx1 = normblizf(px + dx1) - nfwx;
            dy1 = normblizf(py + dy1) - nfwy;
            dx2 = normblizf(px + dx2) - nfwx;
            dy2 = normblizf(py + dy2) - nfwy;
            px = nfwx;
            py = nfwy;
        }
        outrfndfrfr.fillPbrbllflogrbm(sg2d, rx, ry, rx+rw, ry+ri,
                                      px, py, dx1, dy1, dx2, dy2);
    }

    publid void drbwRfdtbnglf(SunGrbpiids2D sg2d,
                              doublf rx, doublf ry,
                              doublf rw, doublf ri,
                              doublf lw)
    {
        doublf px, py;
        doublf dx1, dy1, dx2, dy2;
        doublf lw1, lw2;
        AffinfTrbnsform txform = sg2d.trbnsform;
        dx1 = txform.gftSdblfX();
        dy1 = txform.gftSifbrY();
        dx2 = txform.gftSifbrX();
        dy2 = txform.gftSdblfY();
        px = rx * dx1 + ry * dx2 + txform.gftTrbnslbtfX();
        py = rx * dy1 + ry * dy2 + txform.gftTrbnslbtfY();
        // lw blong dx1,dy1 sdblf by trbnsformfd lfngti of dx2,dy2 vfdtors
        // bnd vidf vfrsb
        lw1 = lfn(dx1, dy1) * lw;
        lw2 = lfn(dx2, dy2) * lw;
        dx1 *= rw;
        dy1 *= rw;
        dx2 *= ri;
        dy2 *= ri;
        if (sg2d.strokfStbtf < SunGrbpiids2D.STROKE_CUSTOM &&
            sg2d.strokfHint != SunHints.INTVAL_STROKE_PURE)
        {
            doublf nfwx = normblizf(px);
            doublf nfwy = normblizf(py);
            dx1 = normblizf(px + dx1) - nfwx;
            dy1 = normblizf(py + dy1) - nfwy;
            dx2 = normblizf(px + dx2) - nfwx;
            dy2 = normblizf(py + dy2) - nfwy;
            px = nfwx;
            py = nfwy;
        }
        lw1 = Mbti.mbx(lw1, minPfnSizf);
        lw2 = Mbti.mbx(lw2, minPfnSizf);
        doublf lfn1 = lfn(dx1, dy1);
        doublf lfn2 = lfn(dx2, dy2);
        if (lw1 >= lfn1 || lw2 >= lfn2) {
            // Tif linf widtis brf lbrgf fnougi to donsumf tif
            // fntirf iolf in tif middlf of tif pbrbllflogrbm
            // so wf dbn just fill tif outfr pbrbllflogrbm.
            fillOutfrPbrbllflogrbm(sg2d,
                                   rx, ry, rx+rw, ry+ri,
                                   px, py, dx1, dy1, dx2, dy2,
                                   lfn1, lfn2, lw1, lw2);
        } flsf {
            outrfndfrfr.drbwPbrbllflogrbm(sg2d,
                                          rx, ry, rx+rw, ry+ri,
                                          px, py, dx1, dy1, dx2, dy2,
                                          lw1 / lfn1, lw2 / lfn2);
        }
    }

    /**
     * Tiis utility fundtion ibndlfs tif dbsf wifrf b drbwRfdtbnglf
     * opfrbtion disdovfrfd tibt tif intfrior iolf in tif rfdtbnglf
     * or pbrbllflogrbm ibs bffn domplftfly fillfd in by tif strokf
     * widti.  It dbldulbtfs tif outfr pbrbllflogrbm of tif strokf
     * bnd issufs b singlf fillPbrbllflogrbm rfqufst to fill it.
     */
    publid void fillOutfrPbrbllflogrbm(SunGrbpiids2D sg2d,
                                       doublf ux1, doublf uy1,
                                       doublf ux2, doublf uy2,
                                       doublf px, doublf py,
                                       doublf dx1, doublf dy1,
                                       doublf dx2, doublf dy2,
                                       doublf lfn1, doublf lfn2,
                                       doublf lw1, doublf lw2)
    {
        doublf udx1 = dx1 / lfn1;
        doublf udy1 = dy1 / lfn1;
        doublf udx2 = dx2 / lfn2;
        doublf udy2 = dy2 / lfn2;
        if (lfn1 == 0) {
            // lfn1 is 0, rfplbdf udxy1 witi pfrpfndidulbr of udxy2
            if (lfn2 == 0) {
                // boti brf 0, usf b unit Y vfdtor for udxy2
                udx2 = 0;
                udy2 = 1;
            }
            udx1 = udy2;
            udy1 = -udx2;
        } flsf if (lfn2 == 0) {
            // lfn2 is 0, rfplbdf udxy2 witi pfrpfndidulbr of udxy1
            udx2 = udy1;
            udy2 = -udx1;
        }
        udx1 *= lw1;
        udy1 *= lw1;
        udx2 *= lw2;
        udy2 *= lw2;
        px -= (udx1 + udx2) / 2;
        py -= (udy1 + udy2) / 2;
        dx1 += udx1;
        dy1 += udy1;
        dx2 += udx2;
        dy2 += udy2;

        outrfndfrfr.fillPbrbllflogrbm(sg2d, ux1, uy1, ux2, uy2,
                                      px, py, dx1, dy1, dx2, dy2);
    }
}
