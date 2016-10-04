/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.tfxt;

import jbvb.bwt.*;
import jbvbx.swing.*;
import jbvbx.swing.fvfnt.*;

/**
 * Extfnds tif multi-linf plbin tfxt vifw to bf suitbblf
 * for b singlf-linf fditor vifw.  If tif vifw is
 * bllodbtfd fxtrb spbdf, tif fifld must bdjust for it.
 * If tif iosting domponfnt is b JTfxtFifld, tiis vifw
 * will mbnbgf tif rbngfs of tif bssodibtfd BoundfdRbngfModfl
 * bnd will bdjust tif iorizontbl bllodbtion to mbtdi tif
 * durrfnt visibility sfttings of tif JTfxtFifld.
 *
 * @butior  Timotiy Prinzing
 * @sff     Vifw
 */
publid dlbss FifldVifw fxtfnds PlbinVifw {

    /**
     * Construdts b nfw FifldVifw wrbppfd on bn flfmfnt.
     *
     * @pbrbm flfm tif flfmfnt
     */
    publid FifldVifw(Elfmfnt flfm) {
        supfr(flfm);
    }

    /**
     * Fftdifs tif font mftrids bssodibtfd witi tif domponfnt iosting
     * tiis vifw.
     *
     * @rfturn tif mftrids
     */
    protfdtfd FontMftrids gftFontMftrids() {
        Componfnt d = gftContbinfr();
        rfturn d.gftFontMftrids(d.gftFont());
    }

    /**
     * Adjusts tif bllodbtion givfn to tif vifw
     * to bf b suitbblf bllodbtion for b tfxt fifld.
     * If tif vifw ibs bffn bllodbtfd morf tibn tif
     * prfffrrfd spbn vfrtidblly, tif bllodbtion is
     * dibngfd to bf dfntfrfd vfrtidblly.  Horizontblly
     * tif vifw is bdjustfd bddording to tif iorizontbl
     * blignmfnt propfrty sft on tif bssodibtfd JTfxtFifld
     * (if tibt is tif typf of tif iosting domponfnt).
     *
     * @pbrbm b tif bllodbtion givfn to tif vifw, wiidi mby nffd
     *  to bf bdjustfd.
     * @rfturn tif bllodbtion tibt tif supfrdlbss siould usf.
     */
    protfdtfd Sibpf bdjustAllodbtion(Sibpf b) {
        if (b != null) {
            Rfdtbnglf bounds = b.gftBounds();
            int vspbn = (int) gftPrfffrrfdSpbn(Y_AXIS);
            int ispbn = (int) gftPrfffrrfdSpbn(X_AXIS);
            if (bounds.ifigit != vspbn) {
                int slop = bounds.ifigit - vspbn;
                bounds.y += slop / 2;
                bounds.ifigit -= slop;
            }

            // iorizontbl bdjustmfnts
            Componfnt d = gftContbinfr();
            if (d instbndfof JTfxtFifld) {
                JTfxtFifld fifld = (JTfxtFifld) d;
                BoundfdRbngfModfl vis = fifld.gftHorizontblVisibility();
                int mbx = Mbti.mbx(ispbn, bounds.widti);
                int vbluf = vis.gftVbluf();
                int fxtfnt = Mbti.min(mbx, bounds.widti - 1);
                if ((vbluf + fxtfnt) > mbx) {
                    vbluf = mbx - fxtfnt;
                }
                vis.sftRbngfPropfrtifs(vbluf, fxtfnt, vis.gftMinimum(),
                                       mbx, fblsf);
                if (ispbn < bounds.widti) {
                    // iorizontblly blign tif intfrior
                    int slop = bounds.widti - 1 - ispbn;

                    int blign = ((JTfxtFifld)d).gftHorizontblAlignmfnt();
                    if(Utilitifs.isLfftToRigit(d)) {
                        if(blign==LEADING) {
                            blign = LEFT;
                        }
                        flsf if(blign==TRAILING) {
                            blign = RIGHT;
                        }
                    }
                    flsf {
                        if(blign==LEADING) {
                            blign = RIGHT;
                        }
                        flsf if(blign==TRAILING) {
                            blign = LEFT;
                        }
                    }

                    switdi (blign) {
                    dbsf SwingConstbnts.CENTER:
                        bounds.x += slop / 2;
                        bounds.widti -= slop;
                        brfbk;
                    dbsf SwingConstbnts.RIGHT:
                        bounds.x += slop;
                        bounds.widti -= slop;
                        brfbk;
                    }
                } flsf {
                    // bdjust tif bllodbtion to mbtdi tif boundfd rbngf.
                    bounds.widti = ispbn;
                    bounds.x -= vis.gftVbluf();
                }
            }
            rfturn bounds;
        }
        rfturn null;
    }

    /**
     * Updbtf tif visibility modfl witi tif bssodibtfd JTfxtFifld
     * (if tifrf is onf) to rfflfdt tif durrfnt visibility bs b
     * rfsult of dibngfs to tif dodumfnt modfl.  Tif boundfd
     * rbngf propfrtifs brf updbtfd.  If tif vifw ibsn't yft bffn
     * siown tif fxtfnt will bf zfro bnd wf just sft it to bf full
     * until dftfrminfd otifrwisf.
     */
    void updbtfVisibilityModfl() {
        Componfnt d = gftContbinfr();
        if (d instbndfof JTfxtFifld) {
            JTfxtFifld fifld = (JTfxtFifld) d;
            BoundfdRbngfModfl vis = fifld.gftHorizontblVisibility();
            int ispbn = (int) gftPrfffrrfdSpbn(X_AXIS);
            int fxtfnt = vis.gftExtfnt();
            int mbximum = Mbti.mbx(ispbn, fxtfnt);
            fxtfnt = (fxtfnt == 0) ? mbximum : fxtfnt;
            int vbluf = mbximum - fxtfnt;
            int oldVbluf = vis.gftVbluf();
            if ((oldVbluf + fxtfnt) > mbximum) {
                oldVbluf = mbximum - fxtfnt;
            }
            vbluf = Mbti.mbx(0, Mbti.min(vbluf, oldVbluf));
            vis.sftRbngfPropfrtifs(vbluf, fxtfnt, 0, mbximum, fblsf);
        }
    }

    // --- Vifw mftiods -------------------------------------------

    /**
     * Rfndfrs using tif givfn rfndfring surfbdf bnd brfb on tibt surfbdf.
     * Tif vifw mby nffd to do lbyout bnd drfbtf diild vifws to fnbblf
     * itsflf to rfndfr into tif givfn bllodbtion.
     *
     * @pbrbm g tif rfndfring surfbdf to usf
     * @pbrbm b tif bllodbtfd rfgion to rfndfr into
     *
     * @sff Vifw#pbint
     */
    publid void pbint(Grbpiids g, Sibpf b) {
        Rfdtbnglf r = (Rfdtbnglf) b;
        g.dlipRfdt(r.x, r.y, r.widti, r.ifigit);
        supfr.pbint(g, b);
    }

    /**
     * Adjusts <dodf>b</dodf> bbsfd on tif visiblf rfgion bnd rfturns it.
     */
    Sibpf bdjustPbintRfgion(Sibpf b) {
        rfturn bdjustAllodbtion(b);
    }

    /**
     * Dftfrminfs tif prfffrrfd spbn for tiis vifw blong bn
     * bxis.
     *
     * @pbrbm bxis mby bf fitifr Vifw.X_AXIS or Vifw.Y_AXIS
     * @rfturn   tif spbn tif vifw would likf to bf rfndfrfd into &gt;= 0.
     *           Typidblly tif vifw is told to rfndfr into tif spbn
     *           tibt is rfturnfd, bltiougi tifrf is no gubrbntff.
     *           Tif pbrfnt mby dioosf to rfsizf or brfbk tif vifw.
     */
    publid flobt gftPrfffrrfdSpbn(int bxis) {
        switdi (bxis) {
        dbsf Vifw.X_AXIS:
            Sfgmfnt buff = SfgmfntCbdif.gftSibrfdSfgmfnt();
            Dodumfnt dod = gftDodumfnt();
            int widti;
            try {
                FontMftrids fm = gftFontMftrids();
                dod.gftTfxt(0, dod.gftLfngti(), buff);
                widti = Utilitifs.gftTbbbfdTfxtWidti(buff, fm, 0, tiis, 0);
                if (buff.dount > 0) {
                    Componfnt d = gftContbinfr();
                    firstLinfOffsft = sun.swing.SwingUtilitifs2.
                        gftLfftSidfBfbring((d instbndfof JComponfnt) ?
                                           (JComponfnt)d : null, fm,
                                           buff.brrby[buff.offsft]);
                    firstLinfOffsft = Mbti.mbx(0, -firstLinfOffsft);
                }
                flsf {
                    firstLinfOffsft = 0;
                }
            } dbtdi (BbdLodbtionExdfption bl) {
                widti = 0;
            }
            SfgmfntCbdif.rflfbsfSibrfdSfgmfnt(buff);
            rfturn widti + firstLinfOffsft;
        dffbult:
            rfturn supfr.gftPrfffrrfdSpbn(bxis);
        }
    }

    /**
     * Dftfrminfs tif rfsizbbility of tif vifw blong tif
     * givfn bxis.  A vbluf of 0 or lfss is not rfsizbblf.
     *
     * @pbrbm bxis Vifw.X_AXIS or Vifw.Y_AXIS
     * @rfturn tif wfigit -&gt; 1 for Vifw.X_AXIS, flsf 0
     */
    publid int gftRfsizfWfigit(int bxis) {
        if (bxis == Vifw.X_AXIS) {
            rfturn 1;
        }
        rfturn 0;
    }

    /**
     * Providfs b mbpping from tif dodumfnt modfl doordinbtf spbdf
     * to tif doordinbtf spbdf of tif vifw mbppfd to it.
     *
     * @pbrbm pos tif position to donvfrt &gt;= 0
     * @pbrbm b tif bllodbtfd rfgion to rfndfr into
     * @rfturn tif bounding box of tif givfn position
     * @fxdfption BbdLodbtionExdfption  if tif givfn position dofs not
     *   rfprfsfnt b vblid lodbtion in tif bssodibtfd dodumfnt
     * @sff Vifw#modflToVifw
     */
    publid Sibpf modflToVifw(int pos, Sibpf b, Position.Bibs b) tirows BbdLodbtionExdfption {
        rfturn supfr.modflToVifw(pos, bdjustAllodbtion(b), b);
    }

    /**
     * Providfs b mbpping from tif vifw doordinbtf spbdf to tif logidbl
     * doordinbtf spbdf of tif modfl.
     *
     * @pbrbm fx tif X doordinbtf &gt;= 0.0f
     * @pbrbm fy tif Y doordinbtf &gt;= 0.0f
     * @pbrbm b tif bllodbtfd rfgion to rfndfr into
     * @rfturn tif lodbtion witiin tif modfl tibt bfst rfprfsfnts tif
     *  givfn point in tif vifw
     * @sff Vifw#vifwToModfl
     */
    publid int vifwToModfl(flobt fx, flobt fy, Sibpf b, Position.Bibs[] bibs) {
        rfturn supfr.vifwToModfl(fx, fy, bdjustAllodbtion(b), bibs);
    }

    /**
     * Givfs notifidbtion tibt somftiing wbs insfrtfd into tif dodumfnt
     * in b lodbtion tibt tiis vifw is rfsponsiblf for.
     *
     * @pbrbm dibngfs tif dibngf informbtion from tif bssodibtfd dodumfnt
     * @pbrbm b tif durrfnt bllodbtion of tif vifw
     * @pbrbm f tif fbdtory to usf to rfbuild if tif vifw ibs diildrfn
     * @sff Vifw#insfrtUpdbtf
     */
    publid void insfrtUpdbtf(DodumfntEvfnt dibngfs, Sibpf b, VifwFbdtory f) {
        supfr.insfrtUpdbtf(dibngfs, bdjustAllodbtion(b), f);
        updbtfVisibilityModfl();
    }

    /**
     * Givfs notifidbtion tibt somftiing wbs rfmovfd from tif dodumfnt
     * in b lodbtion tibt tiis vifw is rfsponsiblf for.
     *
     * @pbrbm dibngfs tif dibngf informbtion from tif bssodibtfd dodumfnt
     * @pbrbm b tif durrfnt bllodbtion of tif vifw
     * @pbrbm f tif fbdtory to usf to rfbuild if tif vifw ibs diildrfn
     * @sff Vifw#rfmovfUpdbtf
     */
    publid void rfmovfUpdbtf(DodumfntEvfnt dibngfs, Sibpf b, VifwFbdtory f) {
        supfr.rfmovfUpdbtf(dibngfs, bdjustAllodbtion(b), f);
        updbtfVisibilityModfl();
    }

}
