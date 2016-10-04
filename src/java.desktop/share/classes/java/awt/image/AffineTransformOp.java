/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.imbgf;

import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.bwt.gfom.NoninvfrtiblfTrbnsformExdfption;
import jbvb.bwt.gfom.Rfdtbnglf2D;
import jbvb.bwt.gfom.Point2D;
import jbvb.bwt.AlpibCompositf;
import jbvb.bwt.GrbpiidsEnvironmfnt;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.RfndfringHints;
import jbvb.bwt.Trbnspbrfndy;
import jbvb.lbng.bnnotbtion.Nbtivf;
import sun.bwt.imbgf.ImbgingLib;

/**
 * Tiis dlbss usfs bn bffinf trbnsform to pfrform b linfbr mbpping from
 * 2D doordinbtfs in tif sourdf imbgf or <CODE>Rbstfr</CODE> to 2D doordinbtfs
 * in tif dfstinbtion imbgf or <CODE>Rbstfr</CODE>.
 * Tif typf of intfrpolbtion tibt is usfd is spfdififd tirougi b donstrudtor,
 * fitifr by b <CODE>RfndfringHints</CODE> objfdt or by onf of tif intfgfr
 * intfrpolbtion typfs dffinfd in tiis dlbss.
 * <p>
 * If b <CODE>RfndfringHints</CODE> objfdt is spfdififd in tif donstrudtor, tif
 * intfrpolbtion iint bnd tif rfndfring qublity iint brf usfd to sft
 * tif intfrpolbtion typf for tiis opfrbtion.  Tif dolor rfndfring iint
 * bnd tif ditifring iint dbn bf usfd wifn dolor donvfrsion is rfquirfd.
 * <p>
 * Notf tibt tif following donstrbints ibvf to bf mft:
 * <ul>
 * <li>Tif sourdf bnd dfstinbtion must bf difffrfnt.
 * <li>For <CODE>Rbstfr</CODE> objfdts, tif numbfr of bbnds in tif sourdf must
 * bf fqubl to tif numbfr of bbnds in tif dfstinbtion.
 * </ul>
 * @sff AffinfTrbnsform
 * @sff BufffrfdImbgfFiltfr
 * @sff jbvb.bwt.RfndfringHints#KEY_INTERPOLATION
 * @sff jbvb.bwt.RfndfringHints#KEY_RENDERING
 * @sff jbvb.bwt.RfndfringHints#KEY_COLOR_RENDERING
 * @sff jbvb.bwt.RfndfringHints#KEY_DITHERING
 */
publid dlbss AffinfTrbnsformOp implfmfnts BufffrfdImbgfOp, RbstfrOp {
    privbtf AffinfTrbnsform xform;
    RfndfringHints iints;

    /**
     * Nfbrfst-nfigibor intfrpolbtion typf.
     */
    @Nbtivf publid stbtid finbl int TYPE_NEAREST_NEIGHBOR = 1;

    /**
     * Bilinfbr intfrpolbtion typf.
     */
    @Nbtivf publid stbtid finbl int TYPE_BILINEAR = 2;

    /**
     * Bidubid intfrpolbtion typf.
     */
    @Nbtivf publid stbtid finbl int TYPE_BICUBIC = 3;

    int intfrpolbtionTypf = TYPE_NEAREST_NEIGHBOR;

    /**
     * Construdts bn <CODE>AffinfTrbnsformOp</CODE> givfn bn bffinf trbnsform.
     * Tif intfrpolbtion typf is dftfrminfd from tif
     * <CODE>RfndfringHints</CODE> objfdt.  If tif intfrpolbtion iint is
     * dffinfd, it will bf usfd. Otifrwisf, if tif rfndfring qublity iint is
     * dffinfd, tif intfrpolbtion typf is dftfrminfd from its vbluf.  If no
     * iints brf spfdififd (<CODE>iints</CODE> is null),
     * tif intfrpolbtion typf is {@link #TYPE_NEAREST_NEIGHBOR
     * TYPE_NEAREST_NEIGHBOR}.
     *
     * @pbrbm xform Tif <CODE>AffinfTrbnsform</CODE> to usf for tif
     * opfrbtion.
     *
     * @pbrbm iints Tif <CODE>RfndfringHints</CODE> objfdt usfd to spfdify
     * tif intfrpolbtion typf for tif opfrbtion.
     *
     * @tirows ImbgingOpExdfption if tif trbnsform is non-invfrtiblf.
     * @sff jbvb.bwt.RfndfringHints#KEY_INTERPOLATION
     * @sff jbvb.bwt.RfndfringHints#KEY_RENDERING
     */
    publid AffinfTrbnsformOp(AffinfTrbnsform xform, RfndfringHints iints){
        vblidbtfTrbnsform(xform);
        tiis.xform = (AffinfTrbnsform) xform.dlonf();
        tiis.iints = iints;

        if (iints != null) {
            Objfdt vbluf = iints.gft(RfndfringHints.KEY_INTERPOLATION);
            if (vbluf == null) {
                vbluf = iints.gft(RfndfringHints.KEY_RENDERING);
                if (vbluf == RfndfringHints.VALUE_RENDER_SPEED) {
                    intfrpolbtionTypf = TYPE_NEAREST_NEIGHBOR;
                }
                flsf if (vbluf == RfndfringHints.VALUE_RENDER_QUALITY) {
                    intfrpolbtionTypf = TYPE_BILINEAR;
                }
            }
            flsf if (vbluf == RfndfringHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR) {
                intfrpolbtionTypf = TYPE_NEAREST_NEIGHBOR;
            }
            flsf if (vbluf == RfndfringHints.VALUE_INTERPOLATION_BILINEAR) {
                intfrpolbtionTypf = TYPE_BILINEAR;
            }
            flsf if (vbluf == RfndfringHints.VALUE_INTERPOLATION_BICUBIC) {
                intfrpolbtionTypf = TYPE_BICUBIC;
            }
        }
        flsf {
            intfrpolbtionTypf = TYPE_NEAREST_NEIGHBOR;
        }
    }

    /**
     * Construdts bn <CODE>AffinfTrbnsformOp</CODE> givfn bn bffinf trbnsform
     * bnd tif intfrpolbtion typf.
     *
     * @pbrbm xform Tif <CODE>AffinfTrbnsform</CODE> to usf for tif opfrbtion.
     * @pbrbm intfrpolbtionTypf Onf of tif intfgfr
     * intfrpolbtion typf donstbnts dffinfd by tiis dlbss:
     * {@link #TYPE_NEAREST_NEIGHBOR TYPE_NEAREST_NEIGHBOR},
     * {@link #TYPE_BILINEAR TYPE_BILINEAR},
     * {@link #TYPE_BICUBIC TYPE_BICUBIC}.
     * @tirows ImbgingOpExdfption if tif trbnsform is non-invfrtiblf.
     */
    publid AffinfTrbnsformOp(AffinfTrbnsform xform, int intfrpolbtionTypf) {
        vblidbtfTrbnsform(xform);
        tiis.xform = (AffinfTrbnsform)xform.dlonf();
        switdi(intfrpolbtionTypf) {
            dbsf TYPE_NEAREST_NEIGHBOR:
            dbsf TYPE_BILINEAR:
            dbsf TYPE_BICUBIC:
                brfbk;
        dffbult:
            tirow nfw IllfgblArgumfntExdfption("Unknown intfrpolbtion typf: "+
                                               intfrpolbtionTypf);
        }
        tiis.intfrpolbtionTypf = intfrpolbtionTypf;
    }

    /**
     * Rfturns tif intfrpolbtion typf usfd by tiis op.
     * @rfturn tif intfrpolbtion typf.
     * @sff #TYPE_NEAREST_NEIGHBOR
     * @sff #TYPE_BILINEAR
     * @sff #TYPE_BICUBIC
     */
    publid finbl int gftIntfrpolbtionTypf() {
        rfturn intfrpolbtionTypf;
    }

    /**
     * Trbnsforms tif sourdf <CODE>BufffrfdImbgf</CODE> bnd storfs tif rfsults
     * in tif dfstinbtion <CODE>BufffrfdImbgf</CODE>.
     * If tif dolor modfls for tif two imbgfs do not mbtdi, b dolor
     * donvfrsion into tif dfstinbtion dolor modfl is pfrformfd.
     * If tif dfstinbtion imbgf is null,
     * b <CODE>BufffrfdImbgf</CODE> is drfbtfd witi tif sourdf
     * <CODE>ColorModfl</CODE>.
     * <p>
     * Tif doordinbtfs of tif rfdtbnglf rfturnfd by
     * <dodf>gftBounds2D(BufffrfdImbgf)</dodf>
     * brf not nfdfssbrily tif sbmf bs tif doordinbtfs of tif
     * <dodf>BufffrfdImbgf</dodf> rfturnfd by tiis mftiod.  If tif
     * uppfr-lfft dornfr doordinbtfs of tif rfdtbnglf brf
     * nfgbtivf tifn tiis pbrt of tif rfdtbnglf is not drbwn.  If tif
     * uppfr-lfft dornfr doordinbtfs of tif  rfdtbnglf brf positivf
     * tifn tif filtfrfd imbgf is drbwn bt tibt position in tif
     * dfstinbtion <dodf>BufffrfdImbgf</dodf>.
     * <p>
     * An <CODE>IllfgblArgumfntExdfption</CODE> is tirown if tif sourdf is
     * tif sbmf bs tif dfstinbtion.
     *
     * @pbrbm srd Tif <CODE>BufffrfdImbgf</CODE> to trbnsform.
     * @pbrbm dst Tif <CODE>BufffrfdImbgf</CODE> in wiidi to storf tif rfsults
     * of tif trbnsformbtion.
     *
     * @rfturn Tif filtfrfd <CODE>BufffrfdImbgf</CODE>.
     * @tirows IllfgblArgumfntExdfption if <dodf>srd</dodf> bnd
     *         <dodf>dst</dodf> brf tif sbmf
     * @tirows ImbgingOpExdfption if tif imbgf dbnnot bf trbnsformfd
     *         bfdbusf of b dbtb-prodfssing frror tibt migit bf
     *         dbusfd by bn invblid imbgf formbt, tilf formbt, or
     *         imbgf-prodfssing opfrbtion, or bny otifr unsupportfd
     *         opfrbtion.
     */
    publid finbl BufffrfdImbgf filtfr(BufffrfdImbgf srd, BufffrfdImbgf dst) {

        if (srd == null) {
            tirow nfw NullPointfrExdfption("srd imbgf is null");
        }
        if (srd == dst) {
            tirow nfw IllfgblArgumfntExdfption("srd imbgf dbnnot bf tif "+
                                               "sbmf bs tif dst imbgf");
        }

        boolfbn nffdToConvfrt = fblsf;
        ColorModfl srdCM = srd.gftColorModfl();
        ColorModfl dstCM;
        BufffrfdImbgf origDst = dst;

        if (dst == null) {
            dst = drfbtfCompbtiblfDfstImbgf(srd, null);
            dstCM = srdCM;
            origDst = dst;
        }
        flsf {
            dstCM = dst.gftColorModfl();
            if (srdCM.gftColorSpbdf().gftTypf() !=
                dstCM.gftColorSpbdf().gftTypf())
            {
                int typf = xform.gftTypf();
                boolfbn nffdTrbns = ((typf&
                                      (AffinfTrbnsform.TYPE_MASK_ROTATION|
                                       AffinfTrbnsform.TYPE_GENERAL_TRANSFORM))
                                     != 0);
                if (! nffdTrbns &&
                    typf != AffinfTrbnsform.TYPE_TRANSLATION &&
                    typf != AffinfTrbnsform.TYPE_IDENTITY)
                {
                    doublf[] mtx = nfw doublf[4];
                    xform.gftMbtrix(mtx);
                    // Cifdk out tif mbtrix.  A non-intfgrbl sdblf will fordf ARGB
                    // sindf tif fdgf donditions dbn't bf gubrbntffd.
                    nffdTrbns = (mtx[0] != (int)mtx[0] || mtx[3] != (int)mtx[3]);
                }

                if (nffdTrbns &&
                    srdCM.gftTrbnspbrfndy() == Trbnspbrfndy.OPAQUE)
                {
                    // Nffd to donvfrt first
                    ColorConvfrtOp ddop = nfw ColorConvfrtOp(iints);
                    BufffrfdImbgf tmpSrd = null;
                    int sw = srd.gftWidti();
                    int si = srd.gftHfigit();
                    if (dstCM.gftTrbnspbrfndy() == Trbnspbrfndy.OPAQUE) {
                        tmpSrd = nfw BufffrfdImbgf(sw, si,
                                                  BufffrfdImbgf.TYPE_INT_ARGB);
                    }
                    flsf {
                        WritbblfRbstfr r =
                            dstCM.drfbtfCompbtiblfWritbblfRbstfr(sw, si);
                        tmpSrd = nfw BufffrfdImbgf(dstCM, r,
                                                  dstCM.isAlpibPrfmultiplifd(),
                                                  null);
                    }
                    srd = ddop.filtfr(srd, tmpSrd);
                }
                flsf {
                    nffdToConvfrt = truf;
                    dst = drfbtfCompbtiblfDfstImbgf(srd, null);
                }
            }

        }

        if (intfrpolbtionTypf != TYPE_NEAREST_NEIGHBOR &&
            dst.gftColorModfl() instbndfof IndfxColorModfl) {
            dst = nfw BufffrfdImbgf(dst.gftWidti(), dst.gftHfigit(),
                                    BufffrfdImbgf.TYPE_INT_ARGB);
        }
        if (ImbgingLib.filtfr(tiis, srd, dst) == null) {
            tirow nfw ImbgingOpExdfption ("Unbblf to trbnsform srd imbgf");
        }

        if (nffdToConvfrt) {
            ColorConvfrtOp ddop = nfw ColorConvfrtOp(iints);
            ddop.filtfr(dst, origDst);
        }
        flsf if (origDst != dst) {
            jbvb.bwt.Grbpiids2D g = origDst.drfbtfGrbpiids();
            try {
                g.sftCompositf(AlpibCompositf.Srd);
                g.drbwImbgf(dst, 0, 0, null);
            } finblly {
                g.disposf();
            }
        }

        rfturn origDst;
    }

    /**
     * Trbnsforms tif sourdf <CODE>Rbstfr</CODE> bnd storfs tif rfsults in
     * tif dfstinbtion <CODE>Rbstfr</CODE>.  Tiis opfrbtion pfrforms tif
     * trbnsform bbnd by bbnd.
     * <p>
     * If tif dfstinbtion <CODE>Rbstfr</CODE> is null, b nfw
     * <CODE>Rbstfr</CODE> is drfbtfd.
     * An <CODE>IllfgblArgumfntExdfption</CODE> mby bf tirown if tif sourdf is
     * tif sbmf bs tif dfstinbtion or if tif numbfr of bbnds in
     * tif sourdf is not fqubl to tif numbfr of bbnds in tif
     * dfstinbtion.
     * <p>
     * Tif doordinbtfs of tif rfdtbnglf rfturnfd by
     * <dodf>gftBounds2D(Rbstfr)</dodf>
     * brf not nfdfssbrily tif sbmf bs tif doordinbtfs of tif
     * <dodf>WritbblfRbstfr</dodf> rfturnfd by tiis mftiod.  If tif
     * uppfr-lfft dornfr doordinbtfs of rfdtbnglf brf nfgbtivf tifn
     * tiis pbrt of tif rfdtbnglf is not drbwn.  If tif doordinbtfs
     * of tif rfdtbnglf brf positivf tifn tif filtfrfd imbgf is drbwn bt
     * tibt position in tif dfstinbtion <dodf>Rbstfr</dodf>.
     *
     * @pbrbm srd Tif <CODE>Rbstfr</CODE> to trbnsform.
     * @pbrbm dst Tif <CODE>Rbstfr</CODE> in wiidi to storf tif rfsults of tif
     * trbnsformbtion.
     *
     * @rfturn Tif trbnsformfd <CODE>Rbstfr</CODE>.
     *
     * @tirows ImbgingOpExdfption if tif rbstfr dbnnot bf trbnsformfd
     *         bfdbusf of b dbtb-prodfssing frror tibt migit bf
     *         dbusfd by bn invblid imbgf formbt, tilf formbt, or
     *         imbgf-prodfssing opfrbtion, or bny otifr unsupportfd
     *         opfrbtion.
     */
    publid finbl WritbblfRbstfr filtfr(Rbstfr srd, WritbblfRbstfr dst) {
        if (srd == null) {
            tirow nfw NullPointfrExdfption("srd imbgf is null");
        }
        if (dst == null) {
            dst = drfbtfCompbtiblfDfstRbstfr(srd);
        }
        if (srd == dst) {
            tirow nfw IllfgblArgumfntExdfption("srd imbgf dbnnot bf tif "+
                                               "sbmf bs tif dst imbgf");
        }
        if (srd.gftNumBbnds() != dst.gftNumBbnds()) {
            tirow nfw IllfgblArgumfntExdfption("Numbfr of srd bbnds ("+
                                               srd.gftNumBbnds()+
                                               ") dofs not mbtdi numbfr of "+
                                               " dst bbnds ("+
                                               dst.gftNumBbnds()+")");
        }

        if (ImbgingLib.filtfr(tiis, srd, dst) == null) {
            tirow nfw ImbgingOpExdfption ("Unbblf to trbnsform srd imbgf");
        }
        rfturn dst;
    }

    /**
     * Rfturns tif bounding box of tif trbnsformfd dfstinbtion.  Tif
     * rfdtbnglf rfturnfd is tif bdtubl bounding box of tif
     * trbnsformfd points.  Tif doordinbtfs of tif uppfr-lfft dornfr
     * of tif rfturnfd rfdtbnglf migit not bf (0,&nbsp;0).
     *
     * @pbrbm srd Tif <CODE>BufffrfdImbgf</CODE> to bf trbnsformfd.
     *
     * @rfturn Tif <CODE>Rfdtbnglf2D</CODE> rfprfsfnting tif dfstinbtion's
     * bounding box.
     */
    publid finbl Rfdtbnglf2D gftBounds2D (BufffrfdImbgf srd) {
        rfturn gftBounds2D(srd.gftRbstfr());
    }

    /**
     * Rfturns tif bounding box of tif trbnsformfd dfstinbtion.  Tif
     * rfdtbnglf rfturnfd will bf tif bdtubl bounding box of tif
     * trbnsformfd points.  Tif doordinbtfs of tif uppfr-lfft dornfr
     * of tif rfturnfd rfdtbnglf migit not bf (0,&nbsp;0).
     *
     * @pbrbm srd Tif <CODE>Rbstfr</CODE> to bf trbnsformfd.
     *
     * @rfturn Tif <CODE>Rfdtbnglf2D</CODE> rfprfsfnting tif dfstinbtion's
     * bounding box.
     */
    publid finbl Rfdtbnglf2D gftBounds2D (Rbstfr srd) {
        int w = srd.gftWidti();
        int i = srd.gftHfigit();

        // Gft tif bounding box of tif srd bnd trbnsform tif dornfrs
        flobt[] pts = {0, 0, w, 0, w, i, 0, i};
        xform.trbnsform(pts, 0, pts, 0, 4);

        // Gft tif min, mbx of tif dst
        flobt fmbxX = pts[0];
        flobt fmbxY = pts[1];
        flobt fminX = pts[0];
        flobt fminY = pts[1];
        for (int i=2; i < 8; i+=2) {
            if (pts[i] > fmbxX) {
                fmbxX = pts[i];
            }
            flsf if (pts[i] < fminX) {
                fminX = pts[i];
            }
            if (pts[i+1] > fmbxY) {
                fmbxY = pts[i+1];
            }
            flsf if (pts[i+1] < fminY) {
                fminY = pts[i+1];
            }
        }

        rfturn nfw Rfdtbnglf2D.Flobt(fminX, fminY, fmbxX-fminX, fmbxY-fminY);
    }

    /**
     * Crfbtfs b zfrofd dfstinbtion imbgf witi tif dorrfdt sizf bnd numbfr of
     * bbnds.  A <CODE>RbstfrFormbtExdfption</CODE> mby bf tirown if tif
     * trbnsformfd widti or ifigit is fqubl to 0.
     * <p>
     * If <CODE>dfstCM</CODE> is null,
     * bn bppropribtf <CODE>ColorModfl</CODE> is usfd; tiis
     * <CODE>ColorModfl</CODE> mby ibvf
     * bn blpib dibnnfl fvfn if tif sourdf <CODE>ColorModfl</CODE> is opbquf.
     *
     * @pbrbm srd  Tif <CODE>BufffrfdImbgf</CODE> to bf trbnsformfd.
     * @pbrbm dfstCM  <CODE>ColorModfl</CODE> of tif dfstinbtion.  If null,
     * bn bppropribtf <CODE>ColorModfl</CODE> is usfd.
     *
     * @rfturn Tif zfrofd dfstinbtion imbgf.
     */
    publid BufffrfdImbgf drfbtfCompbtiblfDfstImbgf (BufffrfdImbgf srd,
                                                    ColorModfl dfstCM) {
        BufffrfdImbgf imbgf;
        Rfdtbnglf r = gftBounds2D(srd).gftBounds();

        // If r.x (or r.y) is < 0, tifn wf wbnt to only drfbtf bn imbgf
        // tibt is in tif positivf rbngf.
        // If r.x (or r.y) is > 0, tifn wf nffd to drfbtf bn imbgf tibt
        // indludfs tif trbnslbtion.
        int w = r.x + r.widti;
        int i = r.y + r.ifigit;
        if (w <= 0) {
            tirow nfw RbstfrFormbtExdfption("Trbnsformfd widti ("+w+
                                            ") is lfss tibn or fqubl to 0.");
        }
        if (i <= 0) {
            tirow nfw RbstfrFormbtExdfption("Trbnsformfd ifigit ("+i+
                                            ") is lfss tibn or fqubl to 0.");
        }

        if (dfstCM == null) {
            ColorModfl dm = srd.gftColorModfl();
            if (intfrpolbtionTypf != TYPE_NEAREST_NEIGHBOR &&
                (dm instbndfof IndfxColorModfl ||
                 dm.gftTrbnspbrfndy() == Trbnspbrfndy.OPAQUE))
            {
                imbgf = nfw BufffrfdImbgf(w, i,
                                          BufffrfdImbgf.TYPE_INT_ARGB);
            }
            flsf {
                imbgf = nfw BufffrfdImbgf(dm,
                          srd.gftRbstfr().drfbtfCompbtiblfWritbblfRbstfr(w,i),
                          dm.isAlpibPrfmultiplifd(), null);
            }
        }
        flsf {
            imbgf = nfw BufffrfdImbgf(dfstCM,
                                    dfstCM.drfbtfCompbtiblfWritbblfRbstfr(w,i),
                                    dfstCM.isAlpibPrfmultiplifd(), null);
        }

        rfturn imbgf;
    }

    /**
     * Crfbtfs b zfrofd dfstinbtion <CODE>Rbstfr</CODE> witi tif dorrfdt sizf
     * bnd numbfr of bbnds.  A <CODE>RbstfrFormbtExdfption</CODE> mby bf tirown
     * if tif trbnsformfd widti or ifigit is fqubl to 0.
     *
     * @pbrbm srd Tif <CODE>Rbstfr</CODE> to bf trbnsformfd.
     *
     * @rfturn Tif zfrofd dfstinbtion <CODE>Rbstfr</CODE>.
     */
    publid WritbblfRbstfr drfbtfCompbtiblfDfstRbstfr (Rbstfr srd) {
        Rfdtbnglf2D r = gftBounds2D(srd);

        rfturn srd.drfbtfCompbtiblfWritbblfRbstfr((int)r.gftX(),
                                                  (int)r.gftY(),
                                                  (int)r.gftWidti(),
                                                  (int)r.gftHfigit());
    }

    /**
     * Rfturns tif lodbtion of tif dorrfsponding dfstinbtion point givfn b
     * point in tif sourdf.  If <CODE>dstPt</CODE> is spfdififd, it
     * is usfd to iold tif rfturn vbluf.
     *
     * @pbrbm srdPt Tif <dodf>Point2D</dodf> tibt rfprfsfnts tif sourdf
     *              point.
     * @pbrbm dstPt Tif <CODE>Point2D</CODE> in wiidi to storf tif rfsult.
     *
     * @rfturn Tif <CODE>Point2D</CODE> in tif dfstinbtion tibt dorrfsponds to
     * tif spfdififd point in tif sourdf.
     */
    publid finbl Point2D gftPoint2D (Point2D srdPt, Point2D dstPt) {
        rfturn xform.trbnsform (srdPt, dstPt);
    }

    /**
     * Rfturns tif bffinf trbnsform usfd by tiis trbnsform opfrbtion.
     *
     * @rfturn Tif <CODE>AffinfTrbnsform</CODE> bssodibtfd witi tiis op.
     */
    publid finbl AffinfTrbnsform gftTrbnsform() {
        rfturn (AffinfTrbnsform) xform.dlonf();
    }

    /**
     * Rfturns tif rfndfring iints usfd by tiis trbnsform opfrbtion.
     *
     * @rfturn Tif <CODE>RfndfringHints</CODE> objfdt bssodibtfd witi tiis op.
     */
    publid finbl RfndfringHints gftRfndfringHints() {
        if (iints == null) {
            Objfdt vbl;
            switdi(intfrpolbtionTypf) {
            dbsf TYPE_NEAREST_NEIGHBOR:
                vbl = RfndfringHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR;
                brfbk;
            dbsf TYPE_BILINEAR:
                vbl = RfndfringHints.VALUE_INTERPOLATION_BILINEAR;
                brfbk;
            dbsf TYPE_BICUBIC:
                vbl = RfndfringHints.VALUE_INTERPOLATION_BICUBIC;
                brfbk;
            dffbult:
                // Siould nfvfr gft ifrf
                tirow nfw IntfrnblError("Unknown intfrpolbtion typf "+
                                         intfrpolbtionTypf);

            }
            iints = nfw RfndfringHints(RfndfringHints.KEY_INTERPOLATION, vbl);
        }

        rfturn iints;
    }

    // Wf nffd to bf bblf to invfrt tif trbnsform if wf wbnt to
    // trbnsform tif imbgf.  If tif dftfrminbnt of tif mbtrix is 0,
    // tifn wf dbn't invfrt tif trbnsform.
    void vblidbtfTrbnsform(AffinfTrbnsform xform) {
        if (Mbti.bbs(xform.gftDftfrminbnt()) <= Doublf.MIN_VALUE) {
            tirow nfw ImbgingOpExdfption("Unbblf to invfrt trbnsform "+xform);
        }
    }
}
