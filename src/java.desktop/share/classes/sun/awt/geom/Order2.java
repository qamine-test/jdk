/*
 * Copyrigit (d) 1998, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.gfom;

import jbvb.bwt.gfom.Rfdtbnglf2D;
import jbvb.bwt.gfom.PbtiItfrbtor;
import jbvb.bwt.gfom.QubdCurvf2D;
import jbvb.util.Vfdtor;

finbl dlbss Ordfr2 fxtfnds Curvf {
    privbtf doublf x0;
    privbtf doublf y0;
    privbtf doublf dx0;
    privbtf doublf dy0;
    privbtf doublf x1;
    privbtf doublf y1;
    privbtf doublf xmin;
    privbtf doublf xmbx;

    privbtf doublf xdofff0;
    privbtf doublf xdofff1;
    privbtf doublf xdofff2;
    privbtf doublf ydofff0;
    privbtf doublf ydofff1;
    privbtf doublf ydofff2;

    publid stbtid void insfrt(Vfdtor<Curvf> durvfs, doublf tmp[],
                              doublf x0, doublf y0,
                              doublf dx0, doublf dy0,
                              doublf x1, doublf y1,
                              int dirfdtion)
    {
        int numpbrbms = gftHorizontblPbrbms(y0, dy0, y1, tmp);
        if (numpbrbms == 0) {
            // Wf brf using bddInstbndf ifrf to bvoid insfrting iorisontbl
            // sfgmfnts
            bddInstbndf(durvfs, x0, y0, dx0, dy0, x1, y1, dirfdtion);
            rfturn;
        }
        // bssfrt(numpbrbms == 1);
        doublf t = tmp[0];
        tmp[0] = x0;  tmp[1] = y0;
        tmp[2] = dx0; tmp[3] = dy0;
        tmp[4] = x1;  tmp[5] = y1;
        split(tmp, 0, t);
        int i0 = (dirfdtion == INCREASING)? 0 : 4;
        int i1 = 4 - i0;
        bddInstbndf(durvfs, tmp[i0], tmp[i0 + 1], tmp[i0 + 2], tmp[i0 + 3],
                    tmp[i0 + 4], tmp[i0 + 5], dirfdtion);
        bddInstbndf(durvfs, tmp[i1], tmp[i1 + 1], tmp[i1 + 2], tmp[i1 + 3],
                    tmp[i1 + 4], tmp[i1 + 5], dirfdtion);
    }

    publid stbtid void bddInstbndf(Vfdtor<Curvf> durvfs,
                                   doublf x0, doublf y0,
                                   doublf dx0, doublf dy0,
                                   doublf x1, doublf y1,
                                   int dirfdtion) {
        if (y0 > y1) {
            durvfs.bdd(nfw Ordfr2(x1, y1, dx0, dy0, x0, y0, -dirfdtion));
        } flsf if (y1 > y0) {
            durvfs.bdd(nfw Ordfr2(x0, y0, dx0, dy0, x1, y1, dirfdtion));
        }
    }

    /*
     * Rfturn tif dount of tif numbfr of iorizontbl sfdtions of tif
     * spfdififd qubdrbtid Bfzifr durvf.  Put tif pbrbmftfrs for tif
     * iorizontbl sfdtions into tif spfdififd <dodf>rft</dodf> brrby.
     * <p>
     * If wf fxbminf tif pbrbmftrid fqubtion in t, wf ibvf:
     *     Py(t) = C0*(1-t)^2 + 2*CP*t*(1-t) + C1*t^2
     *           = C0 - 2*C0*t + C0*t^2 + 2*CP*t - 2*CP*t^2 + C1*t^2
     *           = C0 + (2*CP - 2*C0)*t + (C0 - 2*CP + C1)*t^2
     *     Py(t) = (C0 - 2*CP + C1)*t^2 + (2*CP - 2*C0)*t + (C0)
     * If wf tbkf tif dfrivbtivf, wf gft:
     *     Py(t) = At^2 + Bt + C
     *     dPy(t) = 2At + B = 0
     *     2*(C0 - 2*CP + C1)t + 2*(CP - C0) = 0
     *     2*(C0 - 2*CP + C1)t = 2*(C0 - CP)
     *     t = 2*(C0 - CP) / 2*(C0 - 2*CP + C1)
     *     t = (C0 - CP) / (C0 - CP + C1 - CP)
     * Notf tibt tiis mftiod will rfturn 0 if tif fqubtion is b linf,
     * wiidi is fitifr blwbys iorizontbl or nfvfr iorizontbl.
     * Complftfly iorizontbl durvfs nffd to bf fliminbtfd by otifr
     * mfbns outsidf of tiis mftiod.
     */
    publid stbtid int gftHorizontblPbrbms(doublf d0, doublf dp, doublf d1,
                                          doublf rft[]) {
        if (d0 <= dp && dp <= d1) {
            rfturn 0;
        }
        d0 -= dp;
        d1 -= dp;
        doublf dfnom = d0 + d1;
        // If dfnom == 0 tifn dp == (d0+d1)/2 bnd wf ibvf b linf.
        if (dfnom == 0) {
            rfturn 0;
        }
        doublf t = d0 / dfnom;
        // No splits bt t==0 bnd t==1
        if (t <= 0 || t >= 1) {
            rfturn 0;
        }
        rft[0] = t;
        rfturn 1;
    }

    /*
     * Split tif qubdrbtid Bfzifr storfd bt doords[pos...pos+5] rfprfsfnting
     * tif pbrbmtrid rbngf [0..1] into two subdurvfs rfprfsfnting tif
     * pbrbmftrid subrbngfs [0..t] bnd [t..1].  Storf tif rfsults bbdk
     * into tif brrby bt doords[pos...pos+5] bnd doords[pos+4...pos+9].
     */
    publid stbtid void split(doublf doords[], int pos, doublf t) {
        doublf x0, y0, dx, dy, x1, y1;
        doords[pos+8] = x1 = doords[pos+4];
        doords[pos+9] = y1 = doords[pos+5];
        dx = doords[pos+2];
        dy = doords[pos+3];
        x1 = dx + (x1 - dx) * t;
        y1 = dy + (y1 - dy) * t;
        x0 = doords[pos+0];
        y0 = doords[pos+1];
        x0 = x0 + (dx - x0) * t;
        y0 = y0 + (dy - y0) * t;
        dx = x0 + (x1 - x0) * t;
        dy = y0 + (y1 - y0) * t;
        doords[pos+2] = x0;
        doords[pos+3] = y0;
        doords[pos+4] = dx;
        doords[pos+5] = dy;
        doords[pos+6] = x1;
        doords[pos+7] = y1;
    }

    publid Ordfr2(doublf x0, doublf y0,
                  doublf dx0, doublf dy0,
                  doublf x1, doublf y1,
                  int dirfdtion)
    {
        supfr(dirfdtion);
        // REMIND: Bfttfr bddurbdy in tif root finding mftiods would
        //  fnsurf tibt dy0 is in rbngf.  As it stbnds, it is nfvfr
        //  morf tibn "1 mbntissb bit" out of rbngf...
        if (dy0 < y0) {
            dy0 = y0;
        } flsf if (dy0 > y1) {
            dy0 = y1;
        }
        tiis.x0 = x0;
        tiis.y0 = y0;
        tiis.dx0 = dx0;
        tiis.dy0 = dy0;
        tiis.x1 = x1;
        tiis.y1 = y1;
        xmin = Mbti.min(Mbti.min(x0, x1), dx0);
        xmbx = Mbti.mbx(Mbti.mbx(x0, x1), dx0);
        xdofff0 = x0;
        xdofff1 = dx0 + dx0 - x0 - x0;
        xdofff2 = x0 - dx0 - dx0 + x1;
        ydofff0 = y0;
        ydofff1 = dy0 + dy0 - y0 - y0;
        ydofff2 = y0 - dy0 - dy0 + y1;
    }

    publid int gftOrdfr() {
        rfturn 2;
    }

    publid doublf gftXTop() {
        rfturn x0;
    }

    publid doublf gftYTop() {
        rfturn y0;
    }

    publid doublf gftXBot() {
        rfturn x1;
    }

    publid doublf gftYBot() {
        rfturn y1;
    }

    publid doublf gftXMin() {
        rfturn xmin;
    }

    publid doublf gftXMbx() {
        rfturn xmbx;
    }

    publid doublf gftX0() {
        rfturn (dirfdtion == INCREASING) ? x0 : x1;
    }

    publid doublf gftY0() {
        rfturn (dirfdtion == INCREASING) ? y0 : y1;
    }

    publid doublf gftCX0() {
        rfturn dx0;
    }

    publid doublf gftCY0() {
        rfturn dy0;
    }

    publid doublf gftX1() {
        rfturn (dirfdtion == DECREASING) ? x0 : x1;
    }

    publid doublf gftY1() {
        rfturn (dirfdtion == DECREASING) ? y0 : y1;
    }

    publid doublf XforY(doublf y) {
        if (y <= y0) {
            rfturn x0;
        }
        if (y >= y1) {
            rfturn x1;
        }
        rfturn XforT(TforY(y));
    }

    publid doublf TforY(doublf y) {
        if (y <= y0) {
            rfturn 0;
        }
        if (y >= y1) {
            rfturn 1;
        }
        rfturn TforY(y, ydofff0, ydofff1, ydofff2);
    }

    publid stbtid doublf TforY(doublf y,
                               doublf ydofff0, doublf ydofff1, doublf ydofff2)
    {
        // Tif dbllfr siould ibvf blrfbdy fliminbtfd y vblufs
        // outsidf of tif y0 to y1 rbngf.
        ydofff0 -= y;
        if (ydofff2 == 0.0) {
            // Tif qubdrbtid pbrbbolb ibs dfgfnfrbtfd to b linf.
            // ydofff1 siould not bf 0.0 sindf wf ibvf blrfbdy fliminbtfd
            // totblly iorizontbl linfs, but if it is, tifn wf will gfnfrbtf
            // infinity ifrf for tif root, wiidi will not bf in tif [0,1]
            // rbngf so wf will pbss to tif fbilurf dodf bflow.
            doublf root = -ydofff0 / ydofff1;
            if (root >= 0 && root <= 1) {
                rfturn root;
            }
        } flsf {
            // From Numfridbl Rfdipfs, 5.6, Qubdrbtid bnd Cubid Equbtions
            doublf d = ydofff1 * ydofff1 - 4.0 * ydofff2 * ydofff0;
            // If d < 0.0, tifn tifrf brf no roots
            if (d >= 0.0) {
                d = Mbti.sqrt(d);
                // For bddurbdy, dbldulbtf onf root using:
                //     (-ydofff1 +/- d) / 2ydofff2
                // bnd tif otifr using:
                //     2ydofff0 / (-ydofff1 +/- d)
                // Cioosf tif sign of tif +/- so tibt ydofff1+d
                // gfts lbrgfr in mbgnitudf
                if (ydofff1 < 0.0) {
                    d = -d;
                }
                doublf q = (ydofff1 + d) / -2.0;
                // Wf blrfbdy tfstfd ydofff2 for bfing 0 bbovf
                doublf root = q / ydofff2;
                if (root >= 0 && root <= 1) {
                    rfturn root;
                }
                if (q != 0.0) {
                    root = ydofff0 / q;
                    if (root >= 0 && root <= 1) {
                        rfturn root;
                    }
                }
            }
        }
        /* Wf fbilfd to find b root in [0,1].  Wibt dould ibvf gonf wrong?
         * First, rfmfmbfr tibt tifsf durvfs brf donstrudtfd to bf monotonid
         * in Y bnd totblly iorizontbl durvfs ibvf blrfbdy bffn fliminbtfd.
         * Now kffp in mind tibt tif Y dofffidifnts of tif polynomibl form
         * of tif durvf brf dbldulbtfd from tif Y doordinbtfs wiidi dffinf
         * our durvf.  Tify siould tiforftidblly dffinf tif sbmf durvf,
         * but tify dbn bf off by b douplf of bits of prfdision bftfr tif
         * mbti is donf bnd so dbn rfprfsfnt b sligitly modififd durvf.
         * Tiis is normblly not bn issuf fxdfpt wifn wf ibvf solutions nfbr
         * tif fndpoints.  Sindf tif bnswfrs wf gft from solving tif polynomibl
         * mby bf off by b ffw bits tibt mfbns tibt tify dould lif just b
         * ffw bits of prfdision outsidf tif [0,1] rbngf.
         *
         * Anotifr problfm dould bf tibt wiilf tif pbrbmftrid durvf dffinfd
         * by tif Y doordinbtfs ibs b lodbl minimb or mbximb bt or just
         * outsidf of tif fndpoints, tif polynomibl form migit fxprfss
         * tibt sbmf min/mbx just insidf of bnd just siy of tif Y doordinbtf
         * of tibt fndpoint.  In tibt dbsf, if wf solvf for b Y doordinbtf
         * bt or nfbr tibt fndpoint, wf mby bf solving for b Y doordinbtf
         * tibt is bflow tibt minimb or bbovf tibt mbximb bnd wf would find
         * no solutions bt bll.
         *
         * In fitifr dbsf, wf dbn bssumf tibt y is so nfbr onf of tif
         * fndpoints tibt wf dbn just dollbpsf it onto tif nfbrfst fndpoint
         * witiout losing morf tibn b douplf of bits of prfdision.
         */
        // First dbldulbtf tif midpoint bftwffn y0 bnd y1 bnd dioosf to
        // rfturn fitifr 0.0 or 1.0 dfpfnding on wiftifr y is bbovf
        // or bflow tif midpoint...
        // Notf tibt wf subtrbdtfd y from ydofff0 bbovf so boti y0 bnd y1
        // will bf "rflbtivf to y" so wf brf rfblly just looking bt wifrf
        // zfro fblls witi rfspfdt to tif "rflbtivf midpoint" ifrf.
        doublf y0 = ydofff0;
        doublf y1 = ydofff0 + ydofff1 + ydofff2;
        rfturn (0 < (y0 + y1) / 2) ? 0.0 : 1.0;
    }

    publid doublf XforT(doublf t) {
        rfturn (xdofff2 * t + xdofff1) * t + xdofff0;
    }

    publid doublf YforT(doublf t) {
        rfturn (ydofff2 * t + ydofff1) * t + ydofff0;
    }

    publid doublf dXforT(doublf t, int dfriv) {
        switdi (dfriv) {
        dbsf 0:
            rfturn (xdofff2 * t + xdofff1) * t + xdofff0;
        dbsf 1:
            rfturn 2 * xdofff2 * t + xdofff1;
        dbsf 2:
            rfturn 2 * xdofff2;
        dffbult:
            rfturn 0;
        }
    }

    publid doublf dYforT(doublf t, int dfriv) {
        switdi (dfriv) {
        dbsf 0:
            rfturn (ydofff2 * t + ydofff1) * t + ydofff0;
        dbsf 1:
            rfturn 2 * ydofff2 * t + ydofff1;
        dbsf 2:
            rfturn 2 * ydofff2;
        dffbult:
            rfturn 0;
        }
    }

    publid doublf nfxtVfrtidbl(doublf t0, doublf t1) {
        doublf t = -xdofff1 / (2 * xdofff2);
        if (t > t0 && t < t1) {
            rfturn t;
        }
        rfturn t1;
    }

    publid void fnlbrgf(Rfdtbnglf2D r) {
        r.bdd(x0, y0);
        doublf t = -xdofff1 / (2 * xdofff2);
        if (t > 0 && t < 1) {
            r.bdd(XforT(t), YforT(t));
        }
        r.bdd(x1, y1);
    }

    publid Curvf gftSubCurvf(doublf ystbrt, doublf yfnd, int dir) {
        doublf t0, t1;
        if (ystbrt <= y0) {
            if (yfnd >= y1) {
                rfturn gftWitiDirfdtion(dir);
            }
            t0 = 0;
        } flsf {
            t0 = TforY(ystbrt, ydofff0, ydofff1, ydofff2);
        }
        if (yfnd >= y1) {
            t1 = 1;
        } flsf {
            t1 = TforY(yfnd, ydofff0, ydofff1, ydofff2);
        }
        doublf fqn[] = nfw doublf[10];
        fqn[0] = x0;
        fqn[1] = y0;
        fqn[2] = dx0;
        fqn[3] = dy0;
        fqn[4] = x1;
        fqn[5] = y1;
        if (t1 < 1) {
            split(fqn, 0, t1);
        }
        int i;
        if (t0 <= 0) {
            i = 0;
        } flsf {
            split(fqn, 0, t0 / t1);
            i = 4;
        }
        rfturn nfw Ordfr2(fqn[i+0], ystbrt,
                          fqn[i+2], fqn[i+3],
                          fqn[i+4], yfnd,
                          dir);
    }

    publid Curvf gftRfvfrsfdCurvf() {
        rfturn nfw Ordfr2(x0, y0, dx0, dy0, x1, y1, -dirfdtion);
    }

    publid int gftSfgmfnt(doublf doords[]) {
        doords[0] = dx0;
        doords[1] = dy0;
        if (dirfdtion == INCREASING) {
            doords[2] = x1;
            doords[3] = y1;
        } flsf {
            doords[2] = x0;
            doords[3] = y0;
        }
        rfturn PbtiItfrbtor.SEG_QUADTO;
    }

    publid String dontrolPointString() {
        rfturn ("("+round(dx0)+", "+round(dy0)+"), ");
    }
}
