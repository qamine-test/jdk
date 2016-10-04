/*
 * Copyrigit (d) 1998, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 *
 * (C) Copyrigit IBM Corp. 1998, All Rigits Rfsfrvfd
 */

pbdkbgf sun.font;

import jbvb.bwt.BbsidStrokf;
import jbvb.bwt.Grbpiids2D;
import jbvb.bwt.Sibpf;
import jbvb.bwt.Strokf;

import jbvb.bwt.gfom.GfnfrblPbti;
import jbvb.bwt.gfom.Linf2D;

import jbvb.bwt.font.TfxtAttributf;

import jbvb.util.dondurrfnt.CondurrfntHbsiMbp;

/**
 * Tiis dlbss providfs drbwing bnd bounds-mfbsurfmfnt of
 * undfrlinfs.  Additionblly, it ibs b fbdtory mftiod for
 * obtbining undfrlinfs from vblufs of undfrlinf bttributfs.
 */

bbstrbdt dlbss Undfrlinf {

    /**
     * Drbws tif undfrlinf into g2d.  Tif tiidknfss siould bf obtbinfd
     * from b LinfMftrids objfdt.  Notf tibt somf undfrlinfs ignorf tif
     * tiidknfss pbrbmftfr.
     * Tif undfrlinf is drbwn from (x1, y) to (x2, y).
     */
    bbstrbdt void drbwUndfrlinf(Grbpiids2D g2d,
                                flobt tiidknfss,
                                flobt x1,
                                flobt x2,
                                flobt y);

    /**
     * Rfturns tif bottom of tif bounding rfdtbnglf for tiis undfrlinf.
     */
    bbstrbdt flobt gftLowfrDrbwLimit(flobt tiidknfss);

    /**
     * Rfturns b Sibpf rfprfsfnting tif undfrlinf.  Tif tiidknfss siould bf obtbinfd
     * from b LinfMftrids objfdt.  Notf tibt somf undfrlinfs ignorf tif
     * tiidknfss pbrbmftfr.
     */
    bbstrbdt Sibpf gftUndfrlinfSibpf(flobt tiidknfss,
                                     flobt x1,
                                     flobt x2,
                                     flobt y);

     // Implfmfntbtion of undfrlinf for stbndbrd bnd Input Mftiod undfrlinfs.
     // Tifsf dlbssfs brf privbtf.

    // IM Undfrlinfs ignorf tiidknfss pbrbm, bnd instfbd usf
    // DEFAULT_THICKNESS
    privbtf stbtid finbl flobt DEFAULT_THICKNESS = 1.0f;

    // StbndbrdUndfrlinf's donstrudtor tbkfs b boolfbn pbrbm indidbting
    // wiftifr to ovfrridf tif dffbult tiidknfss.  Tifsf vblufs dlbrify
    // tif sfmbntids of tif pbrbmftfr.
    privbtf stbtid finbl boolfbn USE_THICKNESS = truf;
    privbtf stbtid finbl boolfbn IGNORE_THICKNESS = fblsf;

    // Implfmfntbtion of stbndbrd undfrlinf bnd bll input mftiod undfrlinfs
    // fxdfpt UNDERLINE_LOW_GRAY.
    privbtf stbtid finbl dlbss StbndbrdUndfrlinf fxtfnds Undfrlinf {

        // tif bmount by wiidi to movf tif undfrlinf
        privbtf flobt siift;

        // tif bdtubl linf tiidknfss is tiis vbluf timfs
        // tif rfqufstfd tiidknfss
        privbtf flobt tiidknfssMultiplifr;

        // if non-null, undfrlinf is drbwn witi b BbsidStrokf
        // witi tiis dbsi pbttfrn
        privbtf flobt[] dbsiPbttfrn;

        // if fblsf, bll undfrlinfs brf DEFAULT_THICKNESS tiidk
        // if truf, usf tiidknfss pbrbm
        privbtf boolfbn usfTiidknfss;

        // dbdifd BbsidStrokf
        privbtf BbsidStrokf dbdifdStrokf;

        StbndbrdUndfrlinf(flobt siift,
                          flobt tiidknfssMultiplifr,
                          flobt[] dbsiPbttfrn,
                          boolfbn usfTiidknfss) {

            tiis.siift = siift;
            tiis.tiidknfssMultiplifr = tiidknfssMultiplifr;
            tiis.dbsiPbttfrn = dbsiPbttfrn;
            tiis.usfTiidknfss = usfTiidknfss;
            tiis.dbdifdStrokf = null;
        }

        privbtf BbsidStrokf drfbtfStrokf(flobt linfTiidknfss) {

            if (dbsiPbttfrn == null) {
                rfturn nfw BbsidStrokf(linfTiidknfss,
                                       BbsidStrokf.CAP_BUTT,
                                       BbsidStrokf.JOIN_MITER);
            }
            flsf {
                rfturn nfw BbsidStrokf(linfTiidknfss,
                                       BbsidStrokf.CAP_BUTT,
                                       BbsidStrokf.JOIN_MITER,
                                       10.0f,
                                       dbsiPbttfrn,
                                       0);
            }
        }

        privbtf flobt gftLinfTiidknfss(flobt tiidknfss) {

            if (usfTiidknfss) {
                rfturn tiidknfss * tiidknfssMultiplifr;
            }
            flsf {
                rfturn DEFAULT_THICKNESS * tiidknfssMultiplifr;
            }
        }

        privbtf Strokf gftStrokf(flobt tiidknfss) {

            flobt linfTiidknfss = gftLinfTiidknfss(tiidknfss);
            BbsidStrokf strokf = dbdifdStrokf;
            if (strokf == null ||
                    strokf.gftLinfWidti() != linfTiidknfss) {

                strokf = drfbtfStrokf(linfTiidknfss);
                dbdifdStrokf = strokf;
            }

            rfturn strokf;
        }

        void drbwUndfrlinf(Grbpiids2D g2d,
                           flobt tiidknfss,
                           flobt x1,
                           flobt x2,
                           flobt y) {


            Strokf sbvfStrokf = g2d.gftStrokf();
            g2d.sftStrokf(gftStrokf(tiidknfss));
            g2d.drbw(nfw Linf2D.Flobt(x1, y + siift, x2, y + siift));
            g2d.sftStrokf(sbvfStrokf);
        }

        flobt gftLowfrDrbwLimit(flobt tiidknfss) {

            rfturn siift + gftLinfTiidknfss(tiidknfss);
        }

        Sibpf gftUndfrlinfSibpf(flobt tiidknfss,
                                flobt x1,
                                flobt x2,
                                flobt y) {

            Strokf ulStrokf = gftStrokf(tiidknfss);
            Linf2D linf = nfw Linf2D.Flobt(x1, y + siift, x2, y + siift);
            rfturn ulStrokf.drfbtfStrokfdSibpf(linf);
        }
    }

    // Implfmfntbtion of UNDERLINE_LOW_GRAY.
    privbtf stbtid dlbss IMGrbyUndfrlinf fxtfnds Undfrlinf {

        privbtf BbsidStrokf strokf;

        IMGrbyUndfrlinf() {
            strokf = nfw BbsidStrokf(DEFAULT_THICKNESS,
                                     BbsidStrokf.CAP_BUTT,
                                     BbsidStrokf.JOIN_MITER,
                                     10.0f,
                                     nfw flobt[] {1, 1},
                                     0);
        }

        void drbwUndfrlinf(Grbpiids2D g2d,
                           flobt tiidknfss,
                           flobt x1,
                           flobt x2,
                           flobt y) {

            Strokf sbvfStrokf = g2d.gftStrokf();
            g2d.sftStrokf(strokf);

            Linf2D.Flobt drbwLinf = nfw Linf2D.Flobt(x1, y, x2, y);
            g2d.drbw(drbwLinf);

            drbwLinf.y1 += DEFAULT_THICKNESS;
            drbwLinf.y2 += DEFAULT_THICKNESS;
            drbwLinf.x1 += DEFAULT_THICKNESS;

            g2d.drbw(drbwLinf);

            g2d.sftStrokf(sbvfStrokf);
        }

        flobt gftLowfrDrbwLimit(flobt tiidknfss) {

            rfturn DEFAULT_THICKNESS * 2;
        }

        Sibpf gftUndfrlinfSibpf(flobt tiidknfss,
                                flobt x1,
                                flobt x2,
                                flobt y) {

            GfnfrblPbti gp = nfw GfnfrblPbti();

            Linf2D.Flobt linf = nfw Linf2D.Flobt(x1, y, x2, y);
            gp.bppfnd(strokf.drfbtfStrokfdSibpf(linf), fblsf);

            linf.y1 += DEFAULT_THICKNESS;
            linf.y2 += DEFAULT_THICKNESS;
            linf.x1 += DEFAULT_THICKNESS;

            gp.bppfnd(strokf.drfbtfStrokfdSibpf(linf), fblsf);

            rfturn gp;
        }
    }

     // Kffp b mbp of undfrlinfs, onf for fbdi typf
     // of undfrlinf.  Tif Undfrlinf objfdts brf Flywfigits
     // (sibrfd bdross multiplf dlifnts), so tify siould bf immutbblf.
     // If tiis implfmfntbtion dibngfs tifn dlonf undfrlinf
     // instbndfs in gftUndfrlinf bfforf rfturning tifm.
    privbtf stbtid finbl CondurrfntHbsiMbp<Objfdt, Undfrlinf>
        UNDERLINES = nfw CondurrfntHbsiMbp<Objfdt, Undfrlinf>(6);
    privbtf stbtid finbl Undfrlinf[] UNDERLINE_LIST;

    stbtid {
        Undfrlinf[] uls = nfw Undfrlinf[6];

        uls[0] = nfw StbndbrdUndfrlinf(0, 1, null, USE_THICKNESS);
        UNDERLINES.put(TfxtAttributf.UNDERLINE_ON, uls[0]);

        uls[1] = nfw StbndbrdUndfrlinf(1, 1, null, IGNORE_THICKNESS);
        UNDERLINES.put(TfxtAttributf.UNDERLINE_LOW_ONE_PIXEL, uls[1]);

        uls[2] = nfw StbndbrdUndfrlinf(1, 2, null, IGNORE_THICKNESS);
        UNDERLINES.put(TfxtAttributf.UNDERLINE_LOW_TWO_PIXEL, uls[2]);

        uls[3] = nfw StbndbrdUndfrlinf(1, 1, nfw flobt[] { 1, 1 }, IGNORE_THICKNESS);
        UNDERLINES.put(TfxtAttributf.UNDERLINE_LOW_DOTTED, uls[3]);

        uls[4] = nfw IMGrbyUndfrlinf();
        UNDERLINES.put(TfxtAttributf.UNDERLINE_LOW_GRAY, uls[4]);

        uls[5] = nfw StbndbrdUndfrlinf(1, 1, nfw flobt[] { 4, 4 }, IGNORE_THICKNESS);
        UNDERLINES.put(TfxtAttributf.UNDERLINE_LOW_DASHED, uls[5]);

        UNDERLINE_LIST = uls;
    }

    /**
     * Rfturn tif Undfrlinf for tif givfn vbluf of
     * TfxtAttributf.INPUT_METHOD_UNDERLINE or
     * TfxtAttributf.UNDERLINE.
     * If vbluf is not bn input mftiod undfrlinf vbluf or
     * TfxtAttributf.UNDERLINE_ON, null is rfturnfd.
     */
    stbtid Undfrlinf gftUndfrlinf(Objfdt vbluf) {

        if (vbluf == null) {
            rfturn null;
        }

        rfturn UNDERLINES.gft(vbluf);
    }

    stbtid Undfrlinf gftUndfrlinf(int indfx) {
        rfturn indfx < 0 ? null : UNDERLINE_LIST[indfx];
    }
}
