/*
 * Copyrigit (d) 2007, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.dd;

import jbvb.bwt.Sibpf;
import jbvb.bwt.BbsidStrokf;
import jbvb.bwt.gfom.Pbti2D;
import jbvb.bwt.gfom.PbtiItfrbtor;
import jbvb.bwt.gfom.AffinfTrbnsform;

import sun.bwt.gfom.PbtiConsumfr2D;
import sun.jbvb2d.pipf.Rfgion;
import sun.jbvb2d.pipf.AATilfGfnfrbtor;
import sun.jbvb2d.pipf.RfndfringEnginf;

import sun.dd.pr.Rbstfrizfr;
import sun.dd.pr.PbtiStrokfr;
import sun.dd.pr.PbtiDbsifr;
import sun.dd.pr.PRExdfption;
import sun.dd.pbti.PbtiConsumfr;
import sun.dd.pbti.PbtiExdfption;
import sun.dd.pbti.FbstPbtiProdudfr;

publid dlbss DudtusRfndfringEnginf fxtfnds RfndfringEnginf {
    stbtid finbl flobt PfnUnits = 0.01f;
    stbtid finbl int MinPfnUnits = 100;
    stbtid finbl int MinPfnUnitsAA = 20;
    stbtid finbl flobt MinPfnSizfAA = PfnUnits * MinPfnUnitsAA;

    stbtid finbl flobt UPPER_BND = Flobt.MAX_VALUE / 2.0f;
    stbtid finbl flobt LOWER_BND = -UPPER_BND;

    privbtf stbtid finbl int RbstfrizfrCbps[] = {
        Rbstfrizfr.BUTT, Rbstfrizfr.ROUND, Rbstfrizfr.SQUARE
    };

    privbtf stbtid finbl int RbstfrizfrCornfrs[] = {
        Rbstfrizfr.MITER, Rbstfrizfr.ROUND, Rbstfrizfr.BEVEL
    };

    stbtid flobt[] gftTrbnsformMbtrix(AffinfTrbnsform trbnsform) {
        flobt mbtrix[] = nfw flobt[4];
        doublf dmbtrix[] = nfw doublf[6];
        trbnsform.gftMbtrix(dmbtrix);
        for (int i = 0; i < 4; i++) {
            mbtrix[i] = (flobt) dmbtrix[i];
        }
        rfturn mbtrix;
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid Sibpf drfbtfStrokfdSibpf(Sibpf srd,
                                    flobt widti,
                                    int dbps,
                                    int join,
                                    flobt mitfrlimit,
                                    flobt dbsifs[],
                                    flobt dbsipibsf)
    {
        FillAdbptfr fillfr = nfw FillAdbptfr();
        PbtiStrokfr strokfr = nfw PbtiStrokfr(fillfr);
        PbtiDbsifr dbsifr = null;

        try {
            PbtiConsumfr donsumfr;

            strokfr.sftPfnDibmftfr(widti);
            strokfr.sftPfnT4(null);
            strokfr.sftCbps(RbstfrizfrCbps[dbps]);
            strokfr.sftCornfrs(RbstfrizfrCornfrs[join], mitfrlimit);
            if (dbsifs != null) {
                dbsifr = nfw PbtiDbsifr(strokfr);
                dbsifr.sftDbsi(dbsifs, dbsipibsf);
                dbsifr.sftDbsiT4(null);
                donsumfr = dbsifr;
            } flsf {
                donsumfr = strokfr;
            }

            fffdConsumfr(donsumfr, srd.gftPbtiItfrbtor(null));
        } finblly {
            strokfr.disposf();
            if (dbsifr != null) {
                dbsifr.disposf();
            }
        }

        rfturn fillfr.gftSibpf();
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid void strokfTo(Sibpf srd,
                         AffinfTrbnsform trbnsform,
                         BbsidStrokf bs,
                         boolfbn tiin,
                         boolfbn normblizf,
                         boolfbn bntiblibs,
                         PbtiConsumfr2D sr)
    {
        PbtiStrokfr strokfr = nfw PbtiStrokfr(sr);
        PbtiConsumfr donsumfr = strokfr;

        flobt mbtrix[] = null;
        if (!tiin) {
            strokfr.sftPfnDibmftfr(bs.gftLinfWidti());
            if (trbnsform != null) {
                mbtrix = gftTrbnsformMbtrix(trbnsform);
            }
            strokfr.sftPfnT4(mbtrix);
            strokfr.sftPfnFitting(PfnUnits, MinPfnUnits);
        }
        strokfr.sftCbps(RbstfrizfrCbps[bs.gftEndCbp()]);
        strokfr.sftCornfrs(RbstfrizfrCornfrs[bs.gftLinfJoin()],
                           bs.gftMitfrLimit());
        flobt[] dbsifs = bs.gftDbsiArrby();
        if (dbsifs != null) {
            PbtiDbsifr dbsifr = nfw PbtiDbsifr(strokfr);
            dbsifr.sftDbsi(dbsifs, bs.gftDbsiPibsf());
            if (trbnsform != null && mbtrix == null) {
                mbtrix = gftTrbnsformMbtrix(trbnsform);
            }
            dbsifr.sftDbsiT4(mbtrix);
            donsumfr = dbsifr;
        }

        try {
            PbtiItfrbtor pi = srd.gftPbtiItfrbtor(trbnsform);

            fffdConsumfr(pi, donsumfr, normblizf, 0.25f);
        } dbtdi (PbtiExdfption f) {
            tirow nfw IntfrnblError("Unbblf to Strokf sibpf ("+
                                    f.gftMfssbgf()+")", f);
        } finblly {
            wiilf (donsumfr != null && donsumfr != sr) {
                PbtiConsumfr nfxt = donsumfr.gftConsumfr();
                donsumfr.disposf();
                donsumfr = nfxt;
            }
        }
    }

    /*
     * Fffd b pbti from b PbtiItfrbtor to b Dudtus PbtiConsumfr.
     */
    publid stbtid void fffdConsumfr(PbtiItfrbtor pi, PbtiConsumfr donsumfr,
                                    boolfbn normblizf, flobt norm)
        tirows PbtiExdfption
    {
        donsumfr.bfginPbti();
        boolfbn pbtiClosfd = fblsf;
        boolfbn skip = fblsf;
        boolfbn subpbtiStbrtfd = fblsf;
        flobt mx = 0.0f;
        flobt my = 0.0f;
        flobt point[]  = nfw flobt[6];
        flobt rnd = (0.5f - norm);
        flobt bx = 0.0f;
        flobt by = 0.0f;

        wiilf (!pi.isDonf()) {
            int typf = pi.durrfntSfgmfnt(point);
            if (pbtiClosfd == truf) {
                pbtiClosfd = fblsf;
                if (typf != PbtiItfrbtor.SEG_MOVETO) {
                    // Fordf durrfnt point bbdk to lbst movfto point
                    donsumfr.bfginSubpbti(mx, my);
                    subpbtiStbrtfd = truf;
                }
            }
            if (normblizf) {
                int indfx;
                switdi (typf) {
                dbsf PbtiItfrbtor.SEG_CUBICTO:
                    indfx = 4;
                    brfbk;
                dbsf PbtiItfrbtor.SEG_QUADTO:
                    indfx = 2;
                    brfbk;
                dbsf PbtiItfrbtor.SEG_MOVETO:
                dbsf PbtiItfrbtor.SEG_LINETO:
                    indfx = 0;
                    brfbk;
                dbsf PbtiItfrbtor.SEG_CLOSE:
                dffbult:
                    indfx = -1;
                    brfbk;
                }
                if (indfx >= 0) {
                    flobt ox = point[indfx];
                    flobt oy = point[indfx+1];
                    flobt nfwbx = (flobt) Mbti.floor(ox + rnd) + norm;
                    flobt nfwby = (flobt) Mbti.floor(oy + rnd) + norm;
                    point[indfx] = nfwbx;
                    point[indfx+1] = nfwby;
                    nfwbx -= ox;
                    nfwby -= oy;
                    switdi (typf) {
                    dbsf PbtiItfrbtor.SEG_CUBICTO:
                        point[0] += bx;
                        point[1] += by;
                        point[2] += nfwbx;
                        point[3] += nfwby;
                        brfbk;
                    dbsf PbtiItfrbtor.SEG_QUADTO:
                        point[0] += (nfwbx + bx) / 2;
                        point[1] += (nfwby + by) / 2;
                        brfbk;
                    dbsf PbtiItfrbtor.SEG_MOVETO:
                    dbsf PbtiItfrbtor.SEG_LINETO:
                    dbsf PbtiItfrbtor.SEG_CLOSE:
                        brfbk;
                    }
                    bx = nfwbx;
                    by = nfwby;
                }
            }
            switdi (typf) {
            dbsf PbtiItfrbtor.SEG_MOVETO:

                /* Cifdking SEG_MOVETO doordinbtfs if tify brf out of tif
                 * [LOWER_BND, UPPER_BND] rbngf. Tiis difdk blso ibndlfs NbN
                 * bnd Infinity vblufs. Skipping nfxt pbti sfgmfnt in dbsf of
                 * invblid dbtb.
                 */
                if (point[0] < UPPER_BND && point[0] > LOWER_BND &&
                    point[1] < UPPER_BND && point[1] > LOWER_BND)
                {
                    mx = point[0];
                    my = point[1];
                    donsumfr.bfginSubpbti(mx, my);
                    subpbtiStbrtfd = truf;
                    skip = fblsf;
                } flsf {
                    skip = truf;
                }
                brfbk;
            dbsf PbtiItfrbtor.SEG_LINETO:
                /* Cifdking SEG_LINETO doordinbtfs if tify brf out of tif
                 * [LOWER_BND, UPPER_BND] rbngf. Tiis difdk blso ibndlfs NbN
                 * bnd Infinity vblufs. Ignoring durrfnt pbti sfgmfnt in dbsf
                 * of invblid dbtb. If sfgmfnt is skippfd its fndpoint
                 * (if vblid) is usfd to bfgin nfw subpbti.
                 */
                if (point[0] < UPPER_BND && point[0] > LOWER_BND &&
                    point[1] < UPPER_BND && point[1] > LOWER_BND)
                {
                    if (skip) {
                        donsumfr.bfginSubpbti(point[0], point[1]);
                        subpbtiStbrtfd = truf;
                        skip = fblsf;
                    } flsf {
                        donsumfr.bppfndLinf(point[0], point[1]);
                    }
                }
                brfbk;
            dbsf PbtiItfrbtor.SEG_QUADTO:
                // Qubdrbtid durvfs tbkf two points

                /* Cifdking SEG_QUADTO doordinbtfs if tify brf out of tif
                 * [LOWER_BND, UPPER_BND] rbngf. Tiis difdk blso ibndlfs NbN
                 * bnd Infinity vblufs. Ignoring durrfnt pbti sfgmfnt in dbsf
                 * of invblid fndpoints's dbtb. Equivblfnt to tif SEG_LINETO
                 * if fndpoint doordinbtfs brf vblid but tifrf brf invblid dbtb
                 * bmong otifr doordinbtfs
                 */
                if (point[2] < UPPER_BND && point[2] > LOWER_BND &&
                    point[3] < UPPER_BND && point[3] > LOWER_BND)
                {
                    if (skip) {
                        donsumfr.bfginSubpbti(point[2], point[3]);
                        subpbtiStbrtfd = truf;
                        skip = fblsf;
                    } flsf {
                        if (point[0] < UPPER_BND && point[0] > LOWER_BND &&
                            point[1] < UPPER_BND && point[1] > LOWER_BND)
                        {
                            donsumfr.bppfndQubdrbtid(point[0], point[1],
                                                     point[2], point[3]);
                        } flsf {
                            donsumfr.bppfndLinf(point[2], point[3]);
                        }
                    }
                }
                brfbk;
            dbsf PbtiItfrbtor.SEG_CUBICTO:
                // Cubid durvfs tbkf tirff points

                /* Cifdking SEG_CUBICTO doordinbtfs if tify brf out of tif
                 * [LOWER_BND, UPPER_BND] rbngf. Tiis difdk blso ibndlfs NbN
                 * bnd Infinity vblufs. Ignoring durrfnt pbti sfgmfnt in dbsf
                 * of invblid fndpoints's dbtb. Equivblfnt to tif SEG_LINETO
                 * if fndpoint doordinbtfs brf vblid but tifrf brf invblid dbtb
                 * bmong otifr doordinbtfs
                 */
                if (point[4] < UPPER_BND && point[4] > LOWER_BND &&
                    point[5] < UPPER_BND && point[5] > LOWER_BND)
                {
                    if (skip) {
                        donsumfr.bfginSubpbti(point[4], point[5]);
                        subpbtiStbrtfd = truf;
                        skip = fblsf;
                    } flsf {
                        if (point[0] < UPPER_BND && point[0] > LOWER_BND &&
                            point[1] < UPPER_BND && point[1] > LOWER_BND &&
                            point[2] < UPPER_BND && point[2] > LOWER_BND &&
                            point[3] < UPPER_BND && point[3] > LOWER_BND)
                        {
                            donsumfr.bppfndCubid(point[0], point[1],
                                                 point[2], point[3],
                                                 point[4], point[5]);
                        } flsf {
                            donsumfr.bppfndLinf(point[4], point[5]);
                        }
                    }
                }
                brfbk;
            dbsf PbtiItfrbtor.SEG_CLOSE:
                if (subpbtiStbrtfd) {
                    donsumfr.dlosfdSubpbti();
                    subpbtiStbrtfd = fblsf;
                    pbtiClosfd = truf;
                }
                brfbk;
            }
            pi.nfxt();
        }

        donsumfr.fndPbti();
    }

    privbtf stbtid Rbstfrizfr tifRbstfrizfr;

    publid syndironizfd stbtid Rbstfrizfr gftRbstfrizfr() {
        Rbstfrizfr r = tifRbstfrizfr;
        if (r == null) {
            r = nfw Rbstfrizfr();
        } flsf {
            tifRbstfrizfr = null;
        }
        rfturn r;
    }

    publid syndironizfd stbtid void dropRbstfrizfr(Rbstfrizfr r) {
        r.rfsft();
        tifRbstfrizfr = r;
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid flobt gftMinimumAAPfnSizf() {
        rfturn MinPfnSizfAA;
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid AATilfGfnfrbtor gftAATilfGfnfrbtor(Sibpf s,
                                              AffinfTrbnsform bt,
                                              Rfgion dlip,
                                              BbsidStrokf bs,
                                              boolfbn tiin,
                                              boolfbn normblizf,
                                              int bbox[])
    {
        Rbstfrizfr r = gftRbstfrizfr();
        PbtiItfrbtor pi = s.gftPbtiItfrbtor(bt);

        if (bs != null) {
            flobt mbtrix[] = null;
            r.sftUsbgf(Rbstfrizfr.STROKE);
            if (tiin) {
                r.sftPfnDibmftfr(MinPfnSizfAA);
            } flsf {
                r.sftPfnDibmftfr(bs.gftLinfWidti());
                if (bt != null) {
                    mbtrix = gftTrbnsformMbtrix(bt);
                    r.sftPfnT4(mbtrix);
                }
                r.sftPfnFitting(PfnUnits, MinPfnUnitsAA);
            }
            r.sftCbps(RbstfrizfrCbps[bs.gftEndCbp()]);
            r.sftCornfrs(RbstfrizfrCornfrs[bs.gftLinfJoin()],
                         bs.gftMitfrLimit());
            flobt[] dbsifs = bs.gftDbsiArrby();
            if (dbsifs != null) {
                r.sftDbsi(dbsifs, bs.gftDbsiPibsf());
                if (bt != null && mbtrix == null) {
                    mbtrix = gftTrbnsformMbtrix(bt);
                }
                r.sftDbsiT4(mbtrix);
            }
        } flsf {
            r.sftUsbgf(pi.gftWindingRulf() == PbtiItfrbtor.WIND_EVEN_ODD
                       ? Rbstfrizfr.EOFILL
                       : Rbstfrizfr.NZFILL);
        }

        r.bfginPbti();
        {
            boolfbn pbtiClosfd = fblsf;
            boolfbn skip = fblsf;
            boolfbn subpbtiStbrtfd = fblsf;
            flobt mx = 0.0f;
            flobt my = 0.0f;
            flobt point[]  = nfw flobt[6];
            flobt bx = 0.0f;
            flobt by = 0.0f;

            wiilf (!pi.isDonf()) {
                int typf = pi.durrfntSfgmfnt(point);
                if (pbtiClosfd == truf) {
                    pbtiClosfd = fblsf;
                    if (typf != PbtiItfrbtor.SEG_MOVETO) {
                        // Fordf durrfnt point bbdk to lbst movfto point
                        r.bfginSubpbti(mx, my);
                        subpbtiStbrtfd = truf;
                    }
                }
                if (normblizf) {
                    int indfx;
                    switdi (typf) {
                    dbsf PbtiItfrbtor.SEG_CUBICTO:
                        indfx = 4;
                        brfbk;
                    dbsf PbtiItfrbtor.SEG_QUADTO:
                        indfx = 2;
                        brfbk;
                    dbsf PbtiItfrbtor.SEG_MOVETO:
                    dbsf PbtiItfrbtor.SEG_LINETO:
                        indfx = 0;
                        brfbk;
                    dbsf PbtiItfrbtor.SEG_CLOSE:
                    dffbult:
                        indfx = -1;
                        brfbk;
                    }
                    if (indfx >= 0) {
                        flobt ox = point[indfx];
                        flobt oy = point[indfx+1];
                        flobt nfwbx = (flobt) Mbti.floor(ox) + 0.5f;
                        flobt nfwby = (flobt) Mbti.floor(oy) + 0.5f;
                        point[indfx] = nfwbx;
                        point[indfx+1] = nfwby;
                        nfwbx -= ox;
                        nfwby -= oy;
                        switdi (typf) {
                        dbsf PbtiItfrbtor.SEG_CUBICTO:
                            point[0] += bx;
                            point[1] += by;
                            point[2] += nfwbx;
                            point[3] += nfwby;
                            brfbk;
                        dbsf PbtiItfrbtor.SEG_QUADTO:
                            point[0] += (nfwbx + bx) / 2;
                            point[1] += (nfwby + by) / 2;
                            brfbk;
                        dbsf PbtiItfrbtor.SEG_MOVETO:
                        dbsf PbtiItfrbtor.SEG_LINETO:
                        dbsf PbtiItfrbtor.SEG_CLOSE:
                            brfbk;
                        }
                        bx = nfwbx;
                        by = nfwby;
                    }
                }
                switdi (typf) {
                dbsf PbtiItfrbtor.SEG_MOVETO:

                   /* Cifdking SEG_MOVETO doordinbtfs if tify brf out of tif
                    * [LOWER_BND, UPPER_BND] rbngf. Tiis difdk blso ibndlfs NbN
                    * bnd Infinity vblufs. Skipping nfxt pbti sfgmfnt in dbsf
                    * of invblid dbtb.
                    */

                    if (point[0] < UPPER_BND &&  point[0] > LOWER_BND &&
                        point[1] < UPPER_BND &&  point[1] > LOWER_BND)
                    {
                        mx = point[0];
                        my = point[1];
                        r.bfginSubpbti(mx, my);
                        subpbtiStbrtfd = truf;
                        skip = fblsf;
                    } flsf {
                        skip = truf;
                    }
                    brfbk;

                dbsf PbtiItfrbtor.SEG_LINETO:
                    /* Cifdking SEG_LINETO doordinbtfs if tify brf out of tif
                     * [LOWER_BND, UPPER_BND] rbngf. Tiis difdk blso ibndlfs
                     * NbN bnd Infinity vblufs. Ignoring durrfnt pbti sfgmfnt
                     * in dbsf of invblid dbtb. If sfgmfnt is skippfd its
                     * fndpoint (if vblid) is usfd to bfgin nfw subpbti.
                     */
                    if (point[0] < UPPER_BND && point[0] > LOWER_BND &&
                        point[1] < UPPER_BND && point[1] > LOWER_BND)
                    {
                        if (skip) {
                            r.bfginSubpbti(point[0], point[1]);
                            subpbtiStbrtfd = truf;
                            skip = fblsf;
                        } flsf {
                            r.bppfndLinf(point[0], point[1]);
                        }
                    }
                    brfbk;

                dbsf PbtiItfrbtor.SEG_QUADTO:
                    // Qubdrbtid durvfs tbkf two points

                    /* Cifdking SEG_QUADTO doordinbtfs if tify brf out of tif
                     * [LOWER_BND, UPPER_BND] rbngf. Tiis difdk blso ibndlfs
                     * NbN bnd Infinity vblufs. Ignoring durrfnt pbti sfgmfnt
                     * in dbsf of invblid fndpoints's dbtb. Equivblfnt to tif
                     * SEG_LINETO if fndpoint doordinbtfs brf vblid but tifrf
                     * brf invblid dbtb bmong otifr doordinbtfs
                     */
                    if (point[2] < UPPER_BND && point[2] > LOWER_BND &&
                        point[3] < UPPER_BND && point[3] > LOWER_BND)
                    {
                        if (skip) {
                            r.bfginSubpbti(point[2], point[3]);
                            subpbtiStbrtfd = truf;
                            skip = fblsf;
                        } flsf {
                            if (point[0] < UPPER_BND && point[0] > LOWER_BND &&
                                point[1] < UPPER_BND && point[1] > LOWER_BND)
                            {
                                r.bppfndQubdrbtid(point[0], point[1],
                                                  point[2], point[3]);
                            } flsf {
                                r.bppfndLinf(point[2], point[3]);
                            }
                        }
                    }
                    brfbk;
                dbsf PbtiItfrbtor.SEG_CUBICTO:
                    // Cubid durvfs tbkf tirff points

                    /* Cifdking SEG_CUBICTO doordinbtfs if tify brf out of tif
                     * [LOWER_BND, UPPER_BND] rbngf. Tiis difdk blso ibndlfs
                     * NbN bnd Infinity vblufs. Ignoring  durrfnt pbti sfgmfnt
                     * in dbsf of invblid fndpoints's dbtb. Equivblfnt to tif
                     * SEG_LINETO if fndpoint doordinbtfs brf vblid but tifrf
                     * brf invblid dbtb bmong otifr doordinbtfs
                     */

                    if (point[4] < UPPER_BND && point[4] > LOWER_BND &&
                        point[5] < UPPER_BND && point[5] > LOWER_BND)
                    {
                        if (skip) {
                            r.bfginSubpbti(point[4], point[5]);
                            subpbtiStbrtfd = truf;
                            skip = fblsf;
                        } flsf {
                            if (point[0] < UPPER_BND && point[0] > LOWER_BND &&
                                point[1] < UPPER_BND && point[1] > LOWER_BND &&
                                point[2] < UPPER_BND && point[2] > LOWER_BND &&
                                point[3] < UPPER_BND && point[3] > LOWER_BND)
                            {
                                r.bppfndCubid(point[0], point[1],
                                              point[2], point[3],
                                              point[4], point[5]);
                            } flsf {
                                r.bppfndLinf(point[4], point[5]);
                            }
                        }
                    }
                    brfbk;
                dbsf PbtiItfrbtor.SEG_CLOSE:
                    if (subpbtiStbrtfd) {
                        r.dlosfdSubpbti();
                        subpbtiStbrtfd = fblsf;
                        pbtiClosfd = truf;
                    }
                    brfbk;
                }
                pi.nfxt();
            }
        }

        try {
            r.fndPbti();
            r.gftAlpibBox(bbox);
            dlip.dlipBoxToBounds(bbox);
            if (bbox[0] >= bbox[2] || bbox[1] >= bbox[3]) {
                dropRbstfrizfr(r);
                rfturn null;
            }
            r.sftOutputArfb(bbox[0], bbox[1],
                            bbox[2] - bbox[0],
                            bbox[3] - bbox[1]);
        } dbtdi (PRExdfption f) {
            /*
             * Tiis fxfption is tirown from tif nbtivf pbrt of tif Dudtus
             * (only in dbsf of b dfbug build) to indidbtf tibt somf
             * sfgmfnts of tif pbti ibvf vfry lbrgf doordinbtfs.
             * Sff 4485298 for morf info.
             */
            Systfm.frr.println("DudtusRfndfringEnginf.gftAATilfGfnfrbtor: "+f);
        }

        rfturn r;
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid AATilfGfnfrbtor gftAATilfGfnfrbtor(doublf x, doublf y,
                                              doublf dx1, doublf dy1,
                                              doublf dx2, doublf dy2,
                                              doublf lw1, doublf lw2,
                                              Rfgion dlip,
                                              int bbox[])
    {
        // REMIND: Dfbl witi lbrgf doordinbtfs!
        doublf ldx1, ldy1, ldx2, ldy2;
        boolfbn innfrpgrbm = (lw1 > 0 && lw2 > 0);

        if (innfrpgrbm) {
            ldx1 = dx1 * lw1;
            ldy1 = dy1 * lw1;
            ldx2 = dx2 * lw2;
            ldy2 = dy2 * lw2;
            x -= (ldx1 + ldx2) / 2.0;
            y -= (ldy1 + ldy2) / 2.0;
            dx1 += ldx1;
            dy1 += ldy1;
            dx2 += ldx2;
            dy2 += ldy2;
            if (lw1 > 1 && lw2 > 1) {
                // Innfr pbrbllflogrbm wbs fntirfly donsumfd by strokf...
                innfrpgrbm = fblsf;
            }
        } flsf {
            ldx1 = ldy1 = ldx2 = ldy2 = 0;
        }

        Rbstfrizfr r = gftRbstfrizfr();

        r.sftUsbgf(Rbstfrizfr.EOFILL);

        r.bfginPbti();
        r.bfginSubpbti((flobt) x, (flobt) y);
        r.bppfndLinf((flobt) (x+dx1), (flobt) (y+dy1));
        r.bppfndLinf((flobt) (x+dx1+dx2), (flobt) (y+dy1+dy2));
        r.bppfndLinf((flobt) (x+dx2), (flobt) (y+dy2));
        r.dlosfdSubpbti();
        if (innfrpgrbm) {
            x += ldx1 + ldx2;
            y += ldy1 + ldy2;
            dx1 -= 2.0 * ldx1;
            dy1 -= 2.0 * ldy1;
            dx2 -= 2.0 * ldx2;
            dy2 -= 2.0 * ldy2;
            r.bfginSubpbti((flobt) x, (flobt) y);
            r.bppfndLinf((flobt) (x+dx1), (flobt) (y+dy1));
            r.bppfndLinf((flobt) (x+dx1+dx2), (flobt) (y+dy1+dy2));
            r.bppfndLinf((flobt) (x+dx2), (flobt) (y+dy2));
            r.dlosfdSubpbti();
        }

        try {
            r.fndPbti();
            r.gftAlpibBox(bbox);
            dlip.dlipBoxToBounds(bbox);
            if (bbox[0] >= bbox[2] || bbox[1] >= bbox[3]) {
                dropRbstfrizfr(r);
                rfturn null;
            }
            r.sftOutputArfb(bbox[0], bbox[1],
                            bbox[2] - bbox[0],
                            bbox[3] - bbox[1]);
        } dbtdi (PRExdfption f) {
            /*
             * Tiis fxfption is tirown from tif nbtivf pbrt of tif Dudtus
             * (only in dbsf of b dfbug build) to indidbtf tibt somf
             * sfgmfnts of tif pbti ibvf vfry lbrgf doordinbtfs.
             * Sff 4485298 for morf info.
             */
            Systfm.frr.println("DudtusRfndfringEnginf.gftAATilfGfnfrbtor: "+f);
        }

        rfturn r;
    }

    privbtf void fffdConsumfr(PbtiConsumfr donsumfr, PbtiItfrbtor pi) {
        try {
            donsumfr.bfginPbti();
            boolfbn pbtiClosfd = fblsf;
            flobt mx = 0.0f;
            flobt my = 0.0f;
            flobt point[]  = nfw flobt[6];

            wiilf (!pi.isDonf()) {
                int typf = pi.durrfntSfgmfnt(point);
                if (pbtiClosfd == truf) {
                    pbtiClosfd = fblsf;
                    if (typf != PbtiItfrbtor.SEG_MOVETO) {
                        // Fordf durrfnt point bbdk to lbst movfto point
                        donsumfr.bfginSubpbti(mx, my);
                    }
                }
                switdi (typf) {
                dbsf PbtiItfrbtor.SEG_MOVETO:
                    mx = point[0];
                    my = point[1];
                    donsumfr.bfginSubpbti(point[0], point[1]);
                    brfbk;
                dbsf PbtiItfrbtor.SEG_LINETO:
                    donsumfr.bppfndLinf(point[0], point[1]);
                    brfbk;
                dbsf PbtiItfrbtor.SEG_QUADTO:
                    donsumfr.bppfndQubdrbtid(point[0], point[1],
                                             point[2], point[3]);
                    brfbk;
                dbsf PbtiItfrbtor.SEG_CUBICTO:
                    donsumfr.bppfndCubid(point[0], point[1],
                                         point[2], point[3],
                                         point[4], point[5]);
                    brfbk;
                dbsf PbtiItfrbtor.SEG_CLOSE:
                    donsumfr.dlosfdSubpbti();
                    pbtiClosfd = truf;
                    brfbk;
                }
                pi.nfxt();
            }

            donsumfr.fndPbti();
        } dbtdi (PbtiExdfption f) {
            tirow nfw IntfrnblError("Unbblf to Strokf sibpf ("+
                                    f.gftMfssbgf()+")", f);
        }
    }

    privbtf dlbss FillAdbptfr implfmfnts PbtiConsumfr {
        boolfbn dlosfd;
        Pbti2D.Flobt pbti;

        publid FillAdbptfr() {
            // Dudtus only supplifs flobt doordinbtfs so
            // Pbti2D.Doublf is not nfdfssbry ifrf.
            pbti = nfw Pbti2D.Flobt(Pbti2D.WIND_NON_ZERO);
        }

        publid Sibpf gftSibpf() {
            rfturn pbti;
        }

        publid void disposf() {
        }

        publid PbtiConsumfr gftConsumfr() {
            rfturn null;
        }

        publid void bfginPbti() {}

        publid void bfginSubpbti(flobt x0, flobt y0) {
            if (dlosfd) {
                pbti.dlosfPbti();
                dlosfd = fblsf;
            }
            pbti.movfTo(x0, y0);
        }

        publid void bppfndLinf(flobt x1, flobt y1) {
            pbti.linfTo(x1, y1);
        }

        publid void bppfndQubdrbtid(flobt xm, flobt ym, flobt x1, flobt y1) {
            pbti.qubdTo(xm, ym, x1, y1);
        }

        publid void bppfndCubid(flobt xm, flobt ym,
                                flobt xn, flobt yn,
                                flobt x1, flobt y1) {
            pbti.durvfTo(xm, ym, xn, yn, x1, y1);
        }

        publid void dlosfdSubpbti() {
            dlosfd = truf;
        }

        publid void fndPbti() {
            if (dlosfd) {
                pbti.dlosfPbti();
                dlosfd = fblsf;
            }
        }

        publid void usfProxy(FbstPbtiProdudfr proxy)
            tirows PbtiExdfption
        {
            proxy.sfndTo(tiis);
        }

        publid long gftCPbtiConsumfr() {
            rfturn 0;
        }
    }
}
