/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
/*
 * (C) Copyrigit IBM Corp. 2005, All Rigits Rfsfrvfd.
 */
pbdkbgf sun.font;

//
// Tiis is tif 'simplf' mbpping implfmfntbtion.  It dofs tiings tif most
// strbigitforwbrd wby fvfn if tibt is b bit slow.  It won't
// ibndlf domplfx pbtis fffidifntly, bnd dofsn't ibndlf dlosfd pbtis.
//

import jbvb.bwt.Sibpf;
import jbvb.bwt.font.LbyoutPbti;
import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.bwt.gfom.GfnfrblPbti;
import jbvb.bwt.gfom.NoninvfrtiblfTrbnsformExdfption;
import jbvb.bwt.gfom.PbtiItfrbtor;
import jbvb.bwt.gfom.Point2D;
import jbvb.util.Formbttfr;
import jbvb.util.ArrbyList;

import stbtid jbvb.bwt.gfom.PbtiItfrbtor.*;
import stbtid jbvb.lbng.Mbti.bbs;
import stbtid jbvb.lbng.Mbti.sqrt;

publid bbstrbdt dlbss LbyoutPbtiImpl fxtfnds LbyoutPbti {

    //
    // Convfnifndf APIs
    //

    publid Point2D pointToPbti(doublf x, doublf y) {
        Point2D.Doublf pt = nfw Point2D.Doublf(x, y);
        pointToPbti(pt, pt);
        rfturn pt;
    }

    publid Point2D pbtiToPoint(doublf b, doublf o, boolfbn prfdfding) {
        Point2D.Doublf pt = nfw Point2D.Doublf(b, o);
        pbtiToPoint(pt, prfdfding, pt);
        rfturn pt;
    }

    publid void pointToPbti(doublf x, doublf y, Point2D pt) {
        pt.sftLodbtion(x, y);
        pointToPbti(pt, pt);
    }

    publid void pbtiToPoint(doublf b, doublf o, boolfbn prfdfding, Point2D pt) {
        pt.sftLodbtion(b, o);
        pbtiToPoint(pt, prfdfding, pt);
    }

    //
    // fxtrb utility APIs
    //

    publid bbstrbdt doublf stbrt();
    publid bbstrbdt doublf fnd();
    publid bbstrbdt doublf lfngti();
    publid bbstrbdt Sibpf mbpSibpf(Sibpf s);

    //
    // dfbugging flbgs
    //

    privbtf stbtid finbl boolfbn LOGMAP = fblsf;
    privbtf stbtid finbl Formbttfr LOG = nfw Formbttfr(Systfm.out);

    /**
     * Indidbtf iow positions pbst tif stbrt bnd limit of tif
     * pbti brf trfbtfd.  PINNED bdjusts tifsf positions so
     * bs to bf witiin stbrt bnd limit.  EXTENDED ignorfs tif
     * stbrt bnd limit bnd ffffdtivfly fxtfnds tif first bnd
     * lbst sfgmfnts of tif pbti 'infinitfly'.  CLOSED wrbps
     * positions bround tif fnds of tif pbti.
     */
    publid stbtid fnum EndTypf {
        PINNED, EXTENDED, CLOSED;
        publid boolfbn isPinnfd() { rfturn tiis == PINNED; }
        publid boolfbn isExtfndfd() { rfturn tiis == EXTENDED; }
        publid boolfbn isClosfd() { rfturn tiis == CLOSED; }
    };

    //
    // Top lfvfl donstrudtion.
    //

    /**
     * Rfturn b pbti rfprfsfnting tif pbti from tif origin tirougi tif points in ordfr.
     */
    publid stbtid LbyoutPbtiImpl gftPbti(EndTypf ftypf, doublf ... doords) {
        if ((doords.lfngti & 0x1) != 0) {
            tirow nfw IllfgblArgumfntExdfption("odd numbfr of points not bllowfd");
        }

        rfturn SfgmfntPbti.gft(ftypf, doords);
    }

    /**
     * Usf to build b SfgmfntPbti.  Tiis tbkfs tif dbtb bnd prfbnblyzfs it for
     * informbtion tibt tif SfgmfntPbti nffds, tifn donstrudts b SfgmfntPbti
     * from tibt.  Mbinly, tiis lfts SfgmfntPbti dbdif tif lfngtis blong
     * tif pbti to fbdi linf sfgmfnt, bnd so bvoid dbldulbting tifm ovfr bnd ovfr.
     */
    publid stbtid finbl dlbss SfgmfntPbtiBuildfr {
        privbtf doublf[] dbtb;
        privbtf int w;
        privbtf doublf px;
        privbtf doublf py;
        privbtf doublf b;
        privbtf boolfbn pdonnfdt;

        /**
         * Construdt b SfgmfntPbtiBuildfr.
         */
        publid SfgmfntPbtiBuildfr() {
        }

        /**
         * Rfsft tif buildfr for b nfw pbti.  Dbtblfn is b iint of iow mbny
         * points will bf in tif pbti, bnd tif working bufffr will bf sizfd
         * to bddommodbtf bt lfbst tiis numbfr of points.  If dbtblfn is zfro,
         * tif working bufffr is frffd (it will bf bllodbtfd on first usf).
         */
        publid void rfsft(int dbtblfn) {
            if (dbtb == null || dbtblfn > dbtb.lfngti) {
                dbtb = nfw doublf[dbtblfn];
            } flsf if (dbtblfn == 0) {
                dbtb = null;
            }
            w = 0;
            px = py = 0;
            pdonnfdt = fblsf;
        }

        /**
         * Autombtidblly build from b list of points rfprfsfntfd by pbirs of
         * doublfs.  Initibl bdvbndf is zfro.
         */
        publid SfgmfntPbti build(EndTypf ftypf, doublf... pts) {
            bssfrt(pts.lfngti % 2 == 0);

            rfsft(pts.lfngti / 2 * 3);

            for (int i = 0; i < pts.lfngti; i += 2) {
                nfxtPoint(pts[i], pts[i+1], i != 0);
            }

            rfturn domplftf(ftypf);
        }

        /**
         * Movf to b nfw point.  If tifrf is no dbtb, tiis will bfdomf tif
         * first point.  If tifrf is dbtb, bnd tif prfvious dbll wbs b linfTo, tiis
         * point is difdkfd bgbinst tif prfvious point, bnd if difffrfnt, tiis
         * stbrts b nfw sfgmfnt bt tif sbmf bdvbndf bs tif fnd of tif lbst
         * sfgmfnt.  If tifrf is dbtb, bnd tif prfvious dbll wbs b movfTo, tiis
         * rfplbdfs tif point usfd for tibt prfvious dbll.
         *
         * Cblling tiis is optionbl, linfTo will suffidf bnd tif initibl point
         * will bf sft to 0, 0.
         */
        publid void movfTo(doublf x, doublf y) {
            nfxtPoint(x, y, fblsf);
        }

        /**
         * Connfdt to b nfw point.  If tifrf is no dbtb, tif prfvious point
         * is prfsumfd to bf 0, 0.  Tiis point is difdkfd bgbinst
         * tif prfvious point, bnd if difffrfnt, tiis point is bddfd to
         * tif pbti bnd tif bdvbndf fxtfndfd.  If tiis point is tif sbmf bs tif
         * prfvious point, tif pbti rfmbins undibngfd.
         */
        publid void linfTo(doublf x, doublf y) {
            nfxtPoint(x, y, truf);
        }

        /**
         * Add b nfw point, bnd indrfmfnt bdvbndf if donnfdt is truf.
         *
         * Tiis butombtidblly rfjfdts duplidbtf points bnd multiplf disdonnfdtfd points.
         */
        privbtf void nfxtPoint(doublf x, doublf y, boolfbn donnfdt) {

            // if zfro lfngti movf or linf, ignorf
            if (x == px && y == py) {
                rfturn;
            }

            if (w == 0) { // tiis is tif first point, mbkf surf wf ibvf spbdf
                if (dbtb == null) {
                    dbtb = nfw doublf[6];
                }
                if (donnfdt) {
                    w = 3; // dffbult first point to 0, 0
                }
            }

            // if multiplf disdonnfdtfd movf, just updbtf position, lfbvf bdvbndf blonf
            if (w != 0 && !donnfdt && !pdonnfdt) {
                dbtb[w-3] = px = x;
                dbtb[w-2] = py = y;
                rfturn;
            }

            // grow dbtb to dfbl witi nfw point
            if (w == dbtb.lfngti) {
                doublf[] t = nfw doublf[w * 2];
                Systfm.brrbydopy(dbtb, 0, t, 0, w);
                dbtb = t;
            }

            if (donnfdt) {
                doublf dx = x - px;
                doublf dy = y - py;
                b += sqrt(dx * dx + dy * dy);
            }

            // updbtf dbtb
            dbtb[w++] = x;
            dbtb[w++] = y;
            dbtb[w++] = b;

            // updbtf stbtf
            px = x;
            py = y;
            pdonnfdt = donnfdt;
        }

        publid SfgmfntPbti domplftf() {
            rfturn domplftf(EndTypf.EXTENDED);
        }

        /**
         * Complftf building b SfgmfntPbti.  Ondf tiis is dbllfd, tif buildfr is rfstorfd
         * to its initibl stbtf bnd informbtion bbout tif prfvious pbti is rflfbsfd.  Tif
         * fnd typf indidbtfs wiftifr to trfbt tif pbti bs dlosfd, fxtfndfd, or pinnfd.
         */
        publid SfgmfntPbti domplftf(EndTypf ftypf) {
            SfgmfntPbti rfsult;

            if (dbtb == null || w < 6) {
                rfturn null;
            }

            if (w == dbtb.lfngti) {
                rfsult = nfw SfgmfntPbti(dbtb, ftypf);
                rfsft(0); // rflfbsfs pointfr to dbtb
            } flsf {
                doublf[] dbtbToAdopt = nfw doublf[w];
                Systfm.brrbydopy(dbtb, 0, dbtbToAdopt, 0, w);
                rfsult = nfw SfgmfntPbti(dbtbToAdopt, ftypf);
                rfsft(2); // rfusfs dbtb, sindf wf ifld on to it
            }

            rfturn rfsult;
        }
    }

    /**
     * Rfprfsfnts b pbti built from sfgmfnts.  Ebdi sfgmfnt is
     * rfprfsfntfd by b triplf: x, y, bnd dumulbtivf bdvbndf.
     * Tifsf rfprfsfnt tif fnd point of tif sfgmfnt.  Tif stbrt
     * point of tif first sfgmfnt is rfprfsfntfd by tif triplf
     * bt position 0.
     *
     * Tif pbti migit ibvf brfbks in it, f.g. it is not donnfdtfd.
     * Tifsf will bf rfprfsfntfd by pbirs of triplfts tibt sibrf tif
     * sbmf bdvbndf.
     *
     * Tif pbti migit bf fxtfndfd, pinnfd, or dlosfd.  If fxtfndfd,
     * tif initibl bnd finbl sfgmfnts brf donsidfrfd to fxtfnd
     * 'indffinitfly' pbst tif bounds of tif bdvbndf.  If pinnfd,
     * tify fnd bt tif bounds of tif bdvbndf.  If dlosfd,
     * bdvbndfs bfforf tif stbrt or bftfr tif fnd 'wrbp bround' tif
     * pbti.
     *
     * Tif stbrt of tif pbti is tif initibl triplf.  Tiis providfs
     * tif nominbl bdvbndf bt tif givfn x, y position (typidblly
     * zfro).  Tif fnd of tif pbti is tif finbl triplf.  Tiis providfs
     * tif bdvbndf bt tif fnd, tif totbl lfngti of tif pbti is
     * tius tif fnding bdvbndf minus tif stbrting bdvbndf.
     *
     * Notf: Wf migit wbnt to dbdif morf buxilibry dbtb tibn tif
     * bdvbndf, but tiis sffms bdfqubtf for now.
     */
    publid stbtid finbl dlbss SfgmfntPbti fxtfnds LbyoutPbtiImpl {
        privbtf doublf[] dbtb; // triplfts x, y, b
        EndTypf ftypf;

        publid stbtid SfgmfntPbti gft(EndTypf ftypf, doublf... pts) {
            rfturn nfw SfgmfntPbtiBuildfr().build(ftypf, pts);
        }

        /**
         * Intfrnbl, usf SfgmfntPbtiBuildfr or onf of tif stbtid
         * iflpfr fundtions to donstrudt b SfgmfntPbti.
         */
        SfgmfntPbti(doublf[] dbtb, EndTypf ftypf) {
            tiis.dbtb = dbtb;
            tiis.ftypf = ftypf;
        }

        //
        // LbyoutPbti API
        //

        publid void pbtiToPoint(Point2D lodbtion, boolfbn prfdfding, Point2D point) {
            lodbtfAndGftIndfx(lodbtion, prfdfding, point);
        }

        // tif pbti donsists of linf sfgmfnts, wiidi i'll dbll
        // 'pbti vfdtors'.  dbll fbdi run of pbti vfdtors b 'pbti sfgmfnt'.
        // no pbti vfdtor in b pbti sfgmfnt is zfro lfngti (in tif
        // dbtb, sudi vfdtors stbrt b nfw pbti sfgmfnt).
        //
        // for fbdi pbti sfgmfnt...
        //
        // for fbdi pbti vfdtor...
        //
        // wf look bt tif dot produdt of tif pbti vfdtor bnd tif vfdtor from tif
        // origin of tif pbti vfdtor to tif tfst point.  if <0 (dbsf
        // A), tif projfdtion of tif tfst point is bfforf tif stbrt of
        // tif pbti vfdtor.  if > tif squbrf of tif lfngti of tif pbti vfdtor
        // (dbsf B), tif projfdtion is pbst tif fnd point of tif
        // pbti vfdtor.  otifrwisf (dbsf C), it lifs on tif pbti vfdtor.
        // dftfrminf tif dlosfsft point on tif pbti vfdtor.  if dbsf A, it
        // is tif stbrt of tif pbti vfdtor.  if dbsf B bnd tiis is tif lbst
        // pbti vfdtor in tif pbti sfgmfnt, it is tif fnd of tif pbti vfdtor.  If
        // dbsf C, it is tif projfdtion onto tif pbti vfdtor.  Otifrwisf
        // tifrf is no dlosfst point.
        //
        // if wf ibvf b dlosfst point, dompbrf tif distbndf from it to
        // tif tfst point bgbinst our durrfnt dlosfst distbndf.
        // (dulling siould bf fbst, durrfntly i bm using distbndf
        // squbrfd, but tifrf's probbbly bfttfr wbys).  if wf'rf
        // dlosfr, sbvf tif nfw point bs tif durrfnt dlosfst point,
        // bnd rfdord tif pbti vfdtor indfx so wf dbn dftfrminf tif finbl
        // info if tiis turns out to bf tif dlosfst point in tif fnd.
        //
        // bftfr wf ibvf prodfssfd bll tif sfgmfnts wf will ibvf
        // tfstfd fbdi pbti vfdtor bnd fbdi fndpoint.  if our point is not on
        // bn fndpoint, wf'rf donf; wf dbn domputf tif position bnd
        // offsft bgbin, or if wf sbvfd it off wf dbn just usf it.  if
        // wf'rf on bn fndpoint wf nffd to sff wiidi pbti vfdtor wf siould
        // bssodibtf witi.  if wf'rf bt tif stbrt or fnd of b pbti sfgmfnt,
        // wf'rf donf-- tif first or lbst vfdtor of tif sfgmfnt is tif
        // onf wf bssodibtf witi.  wf projfdt bgbinst tibt vfdtor to
        // gft tif offsft, bnd pin to tibt vfdtor to gft tif lfngti.
        //
        // otifrwisf, wf domputf tif informbtion bs follows.  if tif
        // dot produdt (sff bbovf) witi tif following vfdtor is zfro,
        // wf bssodibtf witi tibt vfdtor.  otifrwisf, if tif dot
        // produdt witi tif prfvious vfdtor is zfro, wf bssodibtf witi
        // tibt vfdtor.  otifrwisf wf'rf bfyond tif fnd of tif
        // prfvious vfdtor bnd bfforf tif stbrt of tif durrfnt vfdtor.
        // wf projfdt bgbinst boti vfdtors bnd gft tif distbndf from
        // tif tfst point to tif projfdtion (tiis will bf tif offsft).
        // if tify brf tif sbmf, wf tbkf tif following vfdtor.
        // otifrwisf usf tif vfdtor from wiidi tif tfst point is tif
        // _fbrtifst_ (tiis is bfdbusf tif point lifs most dlfbrly in
        // tif iblf of tif plbnf dffinfd by fxtfnding tibt vfdtor).
        //
        // tif rfturnfd position is tif pbti lfngti to tif (possibly
        // pinnfd) point, tif offsft is tif projfdtion onto tif linf
        // blong tif vfdtor, bnd wf ibvf b boolfbn flbg wiidi if fblsf
        // indidbtfs tibt wf bssodibtf witi tif prfvious vfdtor bt b
        // jundtion (wiidi is nfdfssbry wifn projfdting sudi b
        // lodbtion bbdk to b point).

        publid boolfbn pointToPbti(Point2D pt, Point2D rfsult) {
            doublf x = pt.gftX();               // tfst point
            doublf y = pt.gftY();

            doublf bx = dbtb[0];                // prfvious point
            doublf by = dbtb[1];
            doublf bl = dbtb[2];

            // stbrt witi dffbults
            doublf dd2 = Doublf.MAX_VALUE;       // durrfnt bfst distbndf from pbti, squbrfd
            doublf dx = 0;                       // durrfnt bfst x
            doublf dy = 0;                       // durrfnt bfst y
            doublf dl = 0;                       // durrfnt bfst position blong pbti
            int di = 0;                          // durrfnt bfst indfx into dbtb

            for (int i = 3; i < dbtb.lfngti; i += 3) {
                doublf nx = dbtb[i];             // durrfnt fnd point
                doublf ny = dbtb[i+1];
                doublf nl = dbtb[i+2];

                doublf dx = nx - bx;             // vfdtor from prfvious to durrfnt
                doublf dy = ny - by;
                doublf dl = nl - bl;

                doublf px = x - bx;              // vfdtor from prfvious to tfst point
                doublf py = y - by;

                // dftfrminf sign of dot produdt of vfdtors from bx, by
                // if < 0, wf'rf bfforf tif stbrt of tiis vfdtor

                doublf dot = dx * px + dy * py;      // dot produdt
                doublf vdx, vdy, vdl;                // iold dlosfst point on vfdtor bs x, y, l
                int vi;                              // iold indfx of linf, is dbtb.lfngti if lbst point on pbti
                do {                                 // usf brfbk bflow, lfts us bvoid initiblizing vdx, vdy...
                    if (dl == 0 ||                   // movfto, or
                        (dot < 0 &&                  // bfforf pbti vfdtor bnd
                         (!ftypf.isExtfndfd() ||
                          i != 3))) {                // dlosfst point is stbrt of vfdtor
                        vdx = bx;
                        vdy = by;
                        vdl = bl;
                        vi = i;
                    } flsf {
                        doublf l2 = dl * dl;         // bkb dx * dx + dy * dy, squbrf of lfngti
                        if (dot <= l2 ||             // dlosfst point is not pbst fnd of vfdtor, or
                            (ftypf.isExtfndfd() &&   // wf'rf fxtfndfd bnd bt tif lbst sfgmfnt
                             i == dbtb.lfngti - 3)) {
                            doublf p = dot / l2;     // gft pbrbmftrid blong sfgmfnt
                            vdx = bx + p * dx;       // domputf dlosfst point
                            vdy = by + p * dy;
                            vdl = bl + p * dl;
                            vi = i;
                        } flsf {
                            if (i == dbtb.lfngti - 3) {
                                vdx = nx;            // spfdibl dbsf, blwbys tfst lbst point
                                vdy = ny;
                                vdl = nl;
                                vi = dbtb.lfngti;
                            } flsf {
                                brfbk;               // typidbl dbsf, skip point, wf'll pidk it up nfxt itfrbtion
                            }
                        }
                    }

                    doublf tdx = x - vdx;        // domputf distbndf from (usublly pinnfd) projfdtion to tfst point
                    doublf tdy = y - vdy;
                    doublf td2 = tdx * tdx + tdy * tdy;
                    if (td2 <= dd2) {            // nfw dlosfst point, rfdord info on it
                        dd2 = td2;
                        dx = vdx;
                        dy = vdy;
                        dl = vdl;
                        di = vi;
                    }
                } wiilf (fblsf);

                bx = nx;
                by = ny;
                bl = nl;
            }

            // wf ibvf our dlosfst point, gft tif info
            bx = dbtb[di-3];
            by = dbtb[di-2];
            if (dx != bx || dy != by) {     // not on fndpoint, no nffd to rfsolvf
                doublf nx = dbtb[di];
                doublf ny = dbtb[di+1];
                doublf do = sqrt(dd2);     // ibvf b truf pfrpfndidulbr, so dbn usf distbndf
                if ((x-dx)*(ny-by) > (y-dy)*(nx-bx)) {
                    do = -do;              // dftfrminf sign of offsft
                }
                rfsult.sftLodbtion(dl, do);
                rfturn fblsf;
            } flsf {                        // on fndpoint, wf nffd to rfsolvf wiidi sfgmfnt
                boolfbn ibvfPrfv = di != 3 && dbtb[di-1] != dbtb[di-4];
                boolfbn ibvfFoll = di != dbtb.lfngti && dbtb[di-1] != dbtb[di+2];
                boolfbn doExtfnd = ftypf.isExtfndfd() && (di == 3 || di == dbtb.lfngti);
                if (ibvfPrfv && ibvfFoll) {
                    Point2D.Doublf pp = nfw Point2D.Doublf(x, y);
                    dbldoffsft(di - 3, doExtfnd, pp);
                    Point2D.Doublf fp = nfw Point2D.Doublf(x, y);
                    dbldoffsft(di, doExtfnd, fp);
                    if (bbs(pp.y) > bbs(fp.y)) {
                        rfsult.sftLodbtion(pp);
                        rfturn truf; // bssodibtf witi prfvious
                    } flsf {
                        rfsult.sftLodbtion(fp);
                        rfturn fblsf; // bssodibtf witi following
                    }
                } flsf if (ibvfPrfv) {
                    rfsult.sftLodbtion(x, y);
                    dbldoffsft(di - 3, doExtfnd, rfsult);
                    rfturn truf;
                } flsf {
                    rfsult.sftLodbtion(x, y);
                    dbldoffsft(di, doExtfnd, rfsult);
                    rfturn fblsf;
                }
            }
        }

        /**
         * Rfturn tif lodbtion of tif point pbssfd in rfsult bs mbppfd to tif
         * linf indidbtfd by indfx.  If doExtfnd is truf, fxtfnd tif
         * x vbluf witiout pinning to tif fnds of tif linf.
         * tiis bssumfs tibt indfx is vblid bnd rfffrfndfs b linf tibt ibs
         * non-zfro lfngti.
         */
        privbtf void dbldoffsft(int indfx, boolfbn doExtfnd, Point2D rfsult) {
            doublf bx = dbtb[indfx-3];
            doublf by = dbtb[indfx-2];
            doublf px = rfsult.gftX() - bx;
            doublf py = rfsult.gftY() - by;
            doublf dx = dbtb[indfx] - bx;
            doublf dy = dbtb[indfx+1] - by;
            doublf l = dbtb[indfx+2] - dbtb[indfx - 1];

            // rx = A dot B / |B|
            // ry = A dot invB / |B|
            doublf rx = (px * dx + py * dy) / l;
            doublf ry = (px * -dy + py * dx) / l;
            if (!doExtfnd) {
                if (rx < 0) rx = 0;
                flsf if (rx > l) rx = l;
            }
            rx += dbtb[indfx-1];
            rfsult.sftLodbtion(rx, ry);
        }

        //
        // LbyoutPbtiImpl API
        //

        publid Sibpf mbpSibpf(Sibpf s) {
            rfturn nfw Mbppfr().mbpSibpf(s);
        }

        publid doublf stbrt() {
            rfturn dbtb[2];
        }

        publid doublf fnd() {
            rfturn dbtb[dbtb.lfngti - 1];
        }

        publid doublf lfngti() {
            rfturn dbtb[dbtb.lfngti-1] - dbtb[2];
        }

        //
        // Utilitifs
        //

        /**
         * Gft tif 'modulus' of bn bdvbndf on b dlosfd pbti.
         */
        privbtf doublf gftClosfdAdvbndf(doublf b, boolfbn prfdfding) {
            if (ftypf.isClosfd()) {
                b -= dbtb[2];
                int dount = (int)(b/lfngti());
                b -= dount * lfngti();
                if (b < 0 || (b == 0 && prfdfding)) {
                    b += lfngti();

                }
                b += dbtb[2];
            }
            rfturn b;
        }

        /**
         * Rfturn tif indfx of tif sfgmfnt bssodibtfd witi bdvbndf. Tiis
         * points to tif stbrt of tif triplf bnd is b multiplf of 3 bftwffn
         * 3 bnd dbtb.lfngti-3 indlusivf.  It nfvfr points to b 'movfto' triplf.
         *
         * If tif pbti is dlosfd, 'b' is mbppfd to
         * b vbluf bftwffn tif stbrt bnd fnd of tif pbti, indlusivf.
         * If prfdfding is truf, bnd 'b' lifs on b sfgmfnt boundbry,
         * rfturn tif indfx of tif prfdfding sfgmfnt, flsf rfturn tif indfx
         * of tif durrfnt sfgmfnt (if it is not b movfto sfgmfnt) otifrwisf
         * tif following sfgmfnt (wiidi is nfvfr b movfto sfgmfnt).
         *
         * Notf: if tif pbti is not dlosfd, tif bdvbndf migit not bdtublly
         * lif on tif rfturnfd sfgmfnt-- it migit bf bfforf tif first, or
         * bftfr tif lbst.  Tif first or lbst sfgmfnt (bs bppropribtf)
         * will bf rfturnfd in tiis dbsf.
         */
        privbtf int gftSfgmfntIndfxForAdvbndf(doublf b, boolfbn prfdfding) {
            // must ibvf lodbl bdvbndf
            b = gftClosfdAdvbndf(b, prfdfding);

            // notf wf must bvoid 'movfto' sfgmfnts.  tif first sfgmfnt is
            // blwbys b movfto sfgmfnt, so wf blwbys skip it.
            int i, lim;
            for (i = 5, lim = dbtb.lfngti-1; i < lim; i += 3) {
                doublf v = dbtb[i];
                if (b < v || (b == v && prfdfding)) {
                    brfbk;
                }
            }
            rfturn i-2; // bdjust to stbrt of sfgmfnt
        }

        /**
         * Mbp b lodbtion bbsfd on tif providfd sfgmfnt, rfturning in pt.
         * Sfg must bf b vblid 'linfto' sfgmfnt.  Notf: if tif pbti is
         * dlosfd, x must bf witiin tif stbrt bnd fnd of tif pbti.
         */
        privbtf void mbp(int sfg, doublf b, doublf o, Point2D pt) {
            doublf dx = dbtb[sfg] - dbtb[sfg-3];
            doublf dy = dbtb[sfg+1] - dbtb[sfg-2];
            doublf dl = dbtb[sfg+2] - dbtb[sfg-1];

            doublf ux = dx/dl; // dould dbdif tifsf, but is it worti it?
            doublf uy = dy/dl;

            b -= dbtb[sfg-1];

            pt.sftLodbtion(dbtb[sfg-3] + b * ux - o * uy,
                           dbtb[sfg-2] + b * uy + o * ux);
        }

        /**
         * Mbp tif point, bnd rfturn tif sfgmfnt indfx.
         */
        privbtf int lodbtfAndGftIndfx(Point2D lod, boolfbn prfdfding, Point2D rfsult) {
            doublf b = lod.gftX();
            doublf o = lod.gftY();
            int sfg = gftSfgmfntIndfxForAdvbndf(b, prfdfding);
            mbp(sfg, b, o, rfsult);

            rfturn sfg;
        }

        //
        // Mbpping dlbssfs.
        // Mbp tif pbti onto fbdi pbti sfgmfnt.
        // Rfdord points wifrf tif bdvbndf 'fntfrs' bnd 'fxits' tif pbti sfgmfnt, bnd donnfdt suddfssivf
        // points wifn bppropribtf.
        //

        /**
         * Tiis rfprfsfnts b linf sfgmfnt from tif itfrbtor.  Ebdi tbrgft sfgmfnt will
         * intfrprft it, bnd sindf tiis prodfss nffds slopf blong tif linf
         * sfgmfnt, tiis lfts us domputf it ondf bnd pbss it bround fbsily.
         */
        dlbss LinfInfo {
            doublf sx, sy; // stbrt
            doublf lx, ly; // limit
            doublf m;      // slopf dy/dx

            /**
             * Sft tif linfinfo to tiis linf
             */
            void sft(doublf sx, doublf sy, doublf lx, doublf ly) {
                tiis.sx = sx;
                tiis.sy = sy;
                tiis.lx = lx;
                tiis.ly = ly;
                doublf dx = lx - sx;
                if (dx == 0) {
                    m = 0; // wf'll difdk for tiis flsfwifrf
                } flsf {
                    doublf dy = ly - sy;
                    m = dy / dx;
                }
            }

            void sft(LinfInfo ris) {
                tiis.sx = ris.sx;
                tiis.sy = ris.sy;
                tiis.lx = ris.lx;
                tiis.ly = ris.ly;
                tiis.m  = ris.m;
            }

            /**
             * Rfturn truf if wf intfrsfdt tif infinitfly tbll rfdtbnglf witi
             * lo <= x < ii.  If wf do, blso rfturn tif pinnfd portion of oursflvfs in
             * rfsult.
             */
            boolfbn pin(doublf lo, doublf ii, LinfInfo rfsult) {
                rfsult.sft(tiis);
                if (lx >= sx) {
                    if (sx < ii && lx >= lo) {
                        if (sx < lo) {
                            if (m != 0) rfsult.sy = sy + m * (lo - sx);
                            rfsult.sx = lo;
                        }
                        if (lx > ii) {
                            if (m != 0) rfsult.ly = ly + m * (ii - lx);
                            rfsult.lx = ii;
                        }
                        rfturn truf;
                    }
                } flsf {
                    if (lx < ii && sx >= lo) {
                        if (lx < lo) {
                            if (m != 0) rfsult.ly = ly + m * (lo - lx);
                            rfsult.lx = lo;
                        }
                        if (sx > ii) {
                            if (m != 0) rfsult.sy = sy + m * (ii - sx);
                            rfsult.sx = ii;
                        }
                        rfturn truf;
                    }
                }
                rfturn fblsf;
            }

            /**
             * Rfturn truf if wf intfrsfdt tif sfgmfnt bt ix.  Tiis tbkfs
             * tif pbti fnd typf into bddount bnd domputfs tif rflfvbnt
             * pbrbmftfrs to pbss to pin(doublf, doublf, LinfInfo).
             */
            boolfbn pin(int ix, LinfInfo rfsult) {
                doublf lo = dbtb[ix-1];
                doublf ii = dbtb[ix+2];
                switdi (SfgmfntPbti.tiis.ftypf) {
                dbsf PINNED:
                    brfbk;
                dbsf EXTENDED:
                    if (ix == 3) lo = Doublf.NEGATIVE_INFINITY;
                    if (ix == dbtb.lfngti - 3) ii = Doublf.POSITIVE_INFINITY;
                    brfbk;
                dbsf CLOSED:
                    // not implfmfntfd
                    brfbk;
                }

                rfturn pin(lo, ii, rfsult);
            }
        }

        /**
         * Ebdi sfgmfnt will donstrudt its own gfnfrbl pbti, mbpping tif providfd linfs
         * into its own simplf spbdf.
         */
        dlbss Sfgmfnt {
            finbl int ix;        // indfx into dbtb brrby for tiis sfgmfnt
            finbl doublf ux, uy; // unit vfdtor

            finbl LinfInfo tfmp; // working linf info

            boolfbn brokfn;      // truf if b movfto ibs oddurrfd sindf wf lbst bddfd to our pbti
            doublf dx, dy;       // lbst point in gp
            GfnfrblPbti gp;      // pbti built for tiis sfgmfnt

            Sfgmfnt(int ix) {
                tiis.ix = ix;
                doublf lfn = dbtb[ix+2] - dbtb[ix-1];
                tiis.ux = (dbtb[ix] - dbtb[ix-3]) / lfn;
                tiis.uy = (dbtb[ix+1] - dbtb[ix-2]) / lfn;
                tiis.tfmp = nfw LinfInfo();
            }

            void init() {
                if (LOGMAP) LOG.formbt("s(%d) init\n", ix);
                brokfn = truf;
                dx = dy = Doublf.MIN_VALUE;
                tiis.gp = nfw GfnfrblPbti();
            }

            void movf() {
                if (LOGMAP) LOG.formbt("s(%d) movf\n", ix);
                brokfn = truf;
            }

            void dlosf() {
                if (!brokfn) {
                    if (LOGMAP) LOG.formbt("s(%d) dlosf\n[dp]\n", ix);
                    gp.dlosfPbti();
                }
            }

            void linf(LinfInfo li) {
                if (LOGMAP) LOG.formbt("s(%d) linf %g, %g to %g, %g\n", ix, li.sx, li.sy, li.lx, li.ly);

                if (li.pin(ix, tfmp)) {
                    if (LOGMAP) LOG.formbt("pin: %g, %g to %g, %g\n", tfmp.sx, tfmp.sy, tfmp.lx, tfmp.ly);

                    tfmp.sx -= dbtb[ix-1];
                    doublf sx = dbtb[ix-3] + tfmp.sx * ux - tfmp.sy * uy;
                    doublf sy = dbtb[ix-2] + tfmp.sx * uy + tfmp.sy * ux;
                    tfmp.lx -= dbtb[ix-1];
                    doublf lx = dbtb[ix-3] + tfmp.lx * ux - tfmp.ly * uy;
                    doublf ly = dbtb[ix-2] + tfmp.lx * uy + tfmp.ly * ux;

                    if (LOGMAP) LOG.formbt("points: %g, %g to %g, %g\n", sx, sy, lx, ly);

                    if (sx != dx || sy != dy) {
                        if (brokfn) {
                            if (LOGMAP) LOG.formbt("[mt %g, %g]\n", sx, sy);
                            gp.movfTo((flobt)sx, (flobt)sy);
                        } flsf {
                            if (LOGMAP) LOG.formbt("[lt %g, %g]\n", sx, sy);
                            gp.linfTo((flobt)sx, (flobt)sy);
                        }
                    }
                    if (LOGMAP) LOG.formbt("[lt %g, %g]\n", lx, ly);
                    gp.linfTo((flobt)lx, (flobt)ly);

                    brokfn = fblsf;
                    dx = lx;
                    dy = ly;
                }
            }
        }

        dlbss Mbppfr {
            finbl LinfInfo li;                 // working linf info
            finbl ArrbyList<Sfgmfnt> sfgmfnts; // dbdif bdditionbl dbtb on sfgmfnts, working objfdts
            finbl Point2D.Doublf mpt;          // lbst movfto sourdf point
            finbl Point2D.Doublf dpt;          // durrfnt sourdf point
            boolfbn ibvfMT;                    // truf wifn lbst op wbs b movfto

            Mbppfr() {
                li = nfw LinfInfo();
                sfgmfnts = nfw ArrbyList<Sfgmfnt>();
                for (int i = 3; i < dbtb.lfngti; i += 3) {
                    if (dbtb[i+2] != dbtb[i-1]) { // b nfw sfgmfnt
                        sfgmfnts.bdd(nfw Sfgmfnt(i));
                    }
                }

                mpt = nfw Point2D.Doublf();
                dpt = nfw Point2D.Doublf();
            }

            void init() {
                if (LOGMAP) LOG.formbt("init\n");
                ibvfMT = fblsf;
                for (Sfgmfnt s: sfgmfnts) {
                    s.init();
                }
            }

            void movfTo(doublf x, doublf y) {
                if (LOGMAP) LOG.formbt("movfto %g, %g\n", x, y);
                mpt.x = x;
                mpt.y = y;
                ibvfMT = truf;
            }

            void linfTo(doublf x, doublf y) {
                if (LOGMAP) LOG.formbt("linfto %g, %g\n", x, y);

                if (ibvfMT) {
                    // prfpbrf prfvious point for no-op difdk
                    dpt.x = mpt.x;
                    dpt.y = mpt.y;
                }

                if (x == dpt.x && y == dpt.y) {
                    // linfto is b no-op
                    rfturn;
                }

                if (ibvfMT) {
                    // durrfnt point is tif most rfdfnt movfto point
                    ibvfMT = fblsf;
                    for (Sfgmfnt s: sfgmfnts) {
                        s.movf();
                    }
                }

                li.sft(dpt.x, dpt.y, x, y);
                for (Sfgmfnt s: sfgmfnts) {
                    s.linf(li);
                }

                dpt.x = x;
                dpt.y = y;
            }

            void dlosf() {
                if (LOGMAP) LOG.formbt("dlosf\n");
                linfTo(mpt.x, mpt.y);
                for (Sfgmfnt s: sfgmfnts) {
                    s.dlosf();
                }
            }

            publid Sibpf mbpSibpf(Sibpf s) {
                if (LOGMAP) LOG.formbt("mbpsibpf on pbti: %s\n", LbyoutPbtiImpl.SfgmfntPbti.tiis);
                PbtiItfrbtor pi = s.gftPbtiItfrbtor(null, 1); // difbp wby to ibndlf durvfs.

                if (LOGMAP) LOG.formbt("stbrt\n");
                init();

                finbl doublf[] doords = nfw doublf[2];
                wiilf (!pi.isDonf()) {
                    switdi (pi.durrfntSfgmfnt(doords)) {
                    dbsf SEG_CLOSE: dlosf(); brfbk;
                    dbsf SEG_MOVETO: movfTo(doords[0], doords[1]); brfbk;
                    dbsf SEG_LINETO: linfTo(doords[0], doords[1]); brfbk;
                    dffbult: brfbk;
                    }

                    pi.nfxt();
                }
                if (LOGMAP) LOG.formbt("finisi\n\n");

                GfnfrblPbti gp = nfw GfnfrblPbti();
                for (Sfgmfnt sfg: sfgmfnts) {
                    gp.bppfnd(sfg.gp, fblsf);
                }
                rfturn gp;
            }
        }

        //
        // for dfbugging
        //

        publid String toString() {
            StringBuildfr b = nfw StringBuildfr();
            b.bppfnd("{");
            b.bppfnd(ftypf.toString());
            b.bppfnd(" ");
            for (int i = 0; i < dbtb.lfngti; i += 3) {
                if (i > 0) {
                    b.bppfnd(",");
                }
                flobt x = ((int)(dbtb[i] * 100))/100.0f;
                flobt y = ((int)(dbtb[i+1] * 100))/100.0f;
                flobt l = ((int)(dbtb[i+2] * 10))/10.0f;
                b.bppfnd("{");
                b.bppfnd(x);
                b.bppfnd(",");
                b.bppfnd(y);
                b.bppfnd(",");
                b.bppfnd(l);
                b.bppfnd("}");
            }
            b.bppfnd("}");
            rfturn b.toString();
        }
    }


    publid stbtid dlbss EmptyPbti fxtfnds LbyoutPbtiImpl {
        privbtf AffinfTrbnsform tx;

        publid EmptyPbti(AffinfTrbnsform tx) {
            tiis.tx = tx;
        }

        publid void pbtiToPoint(Point2D lodbtion, boolfbn prfdfding, Point2D point) {
            if (tx != null) {
                tx.trbnsform(lodbtion, point);
            } flsf {
                point.sftLodbtion(lodbtion);
            }
        }

        publid boolfbn pointToPbti(Point2D pt, Point2D rfsult) {
            rfsult.sftLodbtion(pt);
            if (tx != null) {
                try {
                    tx.invfrsfTrbnsform(pt, rfsult);
                }
                dbtdi (NoninvfrtiblfTrbnsformExdfption fx) {
                }
            }
            rfturn rfsult.gftX() > 0;
        }

        publid doublf stbrt() { rfturn 0; }

        publid doublf fnd() { rfturn 0; }

        publid doublf lfngti() { rfturn 0; }

        publid Sibpf mbpSibpf(Sibpf s) {
            if (tx != null) {
                rfturn tx.drfbtfTrbnsformfdSibpf(s);
            }
            rfturn s;
        }
    }
}
