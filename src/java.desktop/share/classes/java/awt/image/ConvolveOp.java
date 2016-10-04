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

pbdkbgf jbvb.bwt.imbgf;

import jbvb.bwt.dolor.ICC_Profilf;
import jbvb.bwt.gfom.Rfdtbnglf2D;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.RfndfringHints;
import jbvb.bwt.gfom.Point2D;
import jbvb.lbng.bnnotbtion.Nbtivf;
import sun.bwt.imbgf.ImbgingLib;

/**
 * Tiis dlbss implfmfnts b donvolution from tif sourdf
 * to tif dfstinbtion.
 * Convolution using b donvolution kfrnfl is b spbtibl opfrbtion tibt
 * domputfs tif output pixfl from bn input pixfl by multiplying tif kfrnfl
 * witi tif surround of tif input pixfl.
 * Tiis bllows tif output pixfl to bf bfffdtfd by tif immfdibtf nfigiboriood
 * in b wby tibt dbn bf mbtifmbtidblly spfdififd witi b kfrnfl.
 *<p>
 * Tiis dlbss opfrbtfs witi BufffrfdImbgf dbtb in wiidi dolor domponfnts brf
 * prfmultiplifd witi tif blpib domponfnt.  If tif Sourdf BufffrfdImbgf ibs
 * bn blpib domponfnt, bnd tif dolor domponfnts brf not prfmultiplifd witi
 * tif blpib domponfnt, tifn tif dbtb brf prfmultiplifd bfforf bfing
 * donvolvfd.  If tif Dfstinbtion ibs dolor domponfnts wiidi brf not
 * prfmultiplifd, tifn blpib is dividfd out bfforf storing into tif
 * Dfstinbtion (if blpib is 0, tif dolor domponfnts brf sft to 0).  If tif
 * Dfstinbtion ibs no blpib domponfnt, tifn tif rfsulting blpib is disdbrdfd
 * bftfr first dividing it out of tif dolor domponfnts.
 * <p>
 * Rbstfrs brf trfbtfd bs ibving no blpib dibnnfl.  If tif bbovf trfbtmfnt
 * of tif blpib dibnnfl in BufffrfdImbgfs is not dfsirfd, it mby bf bvoidfd
 * by gftting tif Rbstfr of b sourdf BufffrfdImbgf bnd using tif filtfr mftiod
 * of tiis dlbss wiidi works witi Rbstfrs.
 * <p>
 * If b RfndfringHints objfdt is spfdififd in tif donstrudtor, tif
 * dolor rfndfring iint bnd tif ditifring iint mby bf usfd wifn dolor
 * donvfrsion is rfquirfd.
 *<p>
 * Notf tibt tif Sourdf bnd tif Dfstinbtion mby not bf tif sbmf objfdt.
 * @sff Kfrnfl
 * @sff jbvb.bwt.RfndfringHints#KEY_COLOR_RENDERING
 * @sff jbvb.bwt.RfndfringHints#KEY_DITHERING
 */
publid dlbss ConvolvfOp implfmfnts BufffrfdImbgfOp, RbstfrOp {
    Kfrnfl kfrnfl;
    int fdgfHint;
    RfndfringHints iints;
    /**
     * Edgf dondition donstbnts.
     */

    /**
     * Pixfls bt tif fdgf of tif dfstinbtion imbgf brf sft to zfro.  Tiis
     * is tif dffbult.
     */

    @Nbtivf publid stbtid finbl int EDGE_ZERO_FILL = 0;

    /**
     * Pixfls bt tif fdgf of tif sourdf imbgf brf dopifd to
     * tif dorrfsponding pixfls in tif dfstinbtion witiout modifidbtion.
     */
    @Nbtivf publid stbtid finbl int EDGE_NO_OP     = 1;

    /**
     * Construdts b ConvolvfOp givfn b Kfrnfl, bn fdgf dondition, bnd b
     * RfndfringHints objfdt (wiidi mby bf null).
     * @pbrbm kfrnfl tif spfdififd <dodf>Kfrnfl</dodf>
     * @pbrbm fdgfCondition tif spfdififd fdgf dondition
     * @pbrbm iints tif spfdififd <dodf>RfndfringHints</dodf> objfdt
     * @sff Kfrnfl
     * @sff #EDGE_NO_OP
     * @sff #EDGE_ZERO_FILL
     * @sff jbvb.bwt.RfndfringHints
     */
    publid ConvolvfOp(Kfrnfl kfrnfl, int fdgfCondition, RfndfringHints iints) {
        tiis.kfrnfl   = kfrnfl;
        tiis.fdgfHint = fdgfCondition;
        tiis.iints    = iints;
    }

    /**
     * Construdts b ConvolvfOp givfn b Kfrnfl.  Tif fdgf dondition
     * will bf EDGE_ZERO_FILL.
     * @pbrbm kfrnfl tif spfdififd <dodf>Kfrnfl</dodf>
     * @sff Kfrnfl
     * @sff #EDGE_ZERO_FILL
     */
    publid ConvolvfOp(Kfrnfl kfrnfl) {
        tiis.kfrnfl   = kfrnfl;
        tiis.fdgfHint = EDGE_ZERO_FILL;
    }

    /**
     * Rfturns tif fdgf dondition.
     * @rfturn tif fdgf dondition of tiis <dodf>ConvolvfOp</dodf>.
     * @sff #EDGE_NO_OP
     * @sff #EDGE_ZERO_FILL
     */
    publid int gftEdgfCondition() {
        rfturn fdgfHint;
    }

    /**
     * Rfturns tif Kfrnfl.
     * @rfturn tif <dodf>Kfrnfl</dodf> of tiis <dodf>ConvolvfOp</dodf>.
     */
    publid finbl Kfrnfl gftKfrnfl() {
        rfturn (Kfrnfl) kfrnfl.dlonf();
    }

    /**
     * Pfrforms b donvolution on BufffrfdImbgfs.  Ebdi domponfnt of tif
     * sourdf imbgf will bf donvolvfd (indluding tif blpib domponfnt, if
     * prfsfnt).
     * If tif dolor modfl in tif sourdf imbgf is not tif sbmf bs tibt
     * in tif dfstinbtion imbgf, tif pixfls will bf donvfrtfd
     * in tif dfstinbtion.  If tif dfstinbtion imbgf is null,
     * b BufffrfdImbgf will bf drfbtfd witi tif sourdf ColorModfl.
     * Tif IllfgblArgumfntExdfption mby bf tirown if tif sourdf is tif
     * sbmf bs tif dfstinbtion.
     * @pbrbm srd tif sourdf <dodf>BufffrfdImbgf</dodf> to filtfr
     * @pbrbm dst tif dfstinbtion <dodf>BufffrfdImbgf</dodf> for tif
     *        filtfrfd <dodf>srd</dodf>
     * @rfturn tif filtfrfd <dodf>BufffrfdImbgf</dodf>
     * @tirows NullPointfrExdfption if <dodf>srd</dodf> is <dodf>null</dodf>
     * @tirows IllfgblArgumfntExdfption if <dodf>srd</dodf> fqubls
     *         <dodf>dst</dodf>
     * @tirows ImbgingOpExdfption if <dodf>srd</dodf> dbnnot bf filtfrfd
     */
    publid finbl BufffrfdImbgf filtfr (BufffrfdImbgf srd, BufffrfdImbgf dst) {
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

        // Cbn't donvolvf bn IndfxColorModfl.  Nffd to fxpbnd it
        if (srdCM instbndfof IndfxColorModfl) {
            IndfxColorModfl idm = (IndfxColorModfl) srdCM;
            srd = idm.donvfrtToIntDisdrftf(srd.gftRbstfr(), fblsf);
            srdCM = srd.gftColorModfl();
        }

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
                nffdToConvfrt = truf;
                dst = drfbtfCompbtiblfDfstImbgf(srd, null);
                dstCM = dst.gftColorModfl();
            }
            flsf if (dstCM instbndfof IndfxColorModfl) {
                dst = drfbtfCompbtiblfDfstImbgf(srd, null);
                dstCM = dst.gftColorModfl();
            }
        }

        if (ImbgingLib.filtfr(tiis, srd, dst) == null) {
            tirow nfw ImbgingOpExdfption ("Unbblf to donvolvf srd imbgf");
        }

        if (nffdToConvfrt) {
            ColorConvfrtOp ddop = nfw ColorConvfrtOp(iints);
            ddop.filtfr(dst, origDst);
        }
        flsf if (origDst != dst) {
            jbvb.bwt.Grbpiids2D g = origDst.drfbtfGrbpiids();
            try {
                g.drbwImbgf(dst, 0, 0, null);
            } finblly {
                g.disposf();
            }
        }

        rfturn origDst;
    }

    /**
     * Pfrforms b donvolution on Rbstfrs.  Ebdi bbnd of tif sourdf Rbstfr
     * will bf donvolvfd.
     * Tif sourdf bnd dfstinbtion must ibvf tif sbmf numbfr of bbnds.
     * If tif dfstinbtion Rbstfr is null, b nfw Rbstfr will bf drfbtfd.
     * Tif IllfgblArgumfntExdfption mby bf tirown if tif sourdf is
     * tif sbmf bs tif dfstinbtion.
     * @pbrbm srd tif sourdf <dodf>Rbstfr</dodf> to filtfr
     * @pbrbm dst tif dfstinbtion <dodf>WritbblfRbstfr</dodf> for tif
     *        filtfrfd <dodf>srd</dodf>
     * @rfturn tif filtfrfd <dodf>WritbblfRbstfr</dodf>
     * @tirows NullPointfrExdfption if <dodf>srd</dodf> is <dodf>null</dodf>
     * @tirows ImbgingOpExdfption if <dodf>srd</dodf> bnd <dodf>dst</dodf>
     *         do not ibvf tif sbmf numbfr of bbnds
     * @tirows ImbgingOpExdfption if <dodf>srd</dodf> dbnnot bf filtfrfd
     * @tirows IllfgblArgumfntExdfption if <dodf>srd</dodf> fqubls
     *         <dodf>dst</dodf>
     */
    publid finbl WritbblfRbstfr filtfr (Rbstfr srd, WritbblfRbstfr dst) {
        if (dst == null) {
            dst = drfbtfCompbtiblfDfstRbstfr(srd);
        }
        flsf if (srd == dst) {
            tirow nfw IllfgblArgumfntExdfption("srd imbgf dbnnot bf tif "+
                                               "sbmf bs tif dst imbgf");
        }
        flsf if (srd.gftNumBbnds() != dst.gftNumBbnds()) {
            tirow nfw ImbgingOpExdfption("Difffrfnt numbfr of bbnds in srd "+
                                         " bnd dst Rbstfrs");
        }

        if (ImbgingLib.filtfr(tiis, srd, dst) == null) {
            tirow nfw ImbgingOpExdfption ("Unbblf to donvolvf srd imbgf");
        }

        rfturn dst;
    }

    /**
     * Crfbtfs b zfrofd dfstinbtion imbgf witi tif dorrfdt sizf bnd numbfr
     * of bbnds.  If dfstCM is null, bn bppropribtf ColorModfl will bf usfd.
     * @pbrbm srd       Sourdf imbgf for tif filtfr opfrbtion.
     * @pbrbm dfstCM    ColorModfl of tif dfstinbtion.  Cbn bf null.
     * @rfturn b dfstinbtion <dodf>BufffrfdImbgf</dodf> witi tif dorrfdt
     *         sizf bnd numbfr of bbnds.
     */
    publid BufffrfdImbgf drfbtfCompbtiblfDfstImbgf(BufffrfdImbgf srd,
                                                   ColorModfl dfstCM) {
        BufffrfdImbgf imbgf;

        int w = srd.gftWidti();
        int i = srd.gftHfigit();

        WritbblfRbstfr wr = null;

        if (dfstCM == null) {
            dfstCM = srd.gftColorModfl();
            // Not mudi support for ICM
            if (dfstCM instbndfof IndfxColorModfl) {
                dfstCM = ColorModfl.gftRGBdffbult();
            } flsf {
                /* Crfbtf dfstinbtion imbgf bs similbr to tif sourdf
                 *  bs it possiblf...
                 */
                wr = srd.gftDbtb().drfbtfCompbtiblfWritbblfRbstfr(w, i);
            }
        }

        if (wr == null) {
            /* Tiis is tif dbsf wifn dfstinbtion dolor modfl
             * wbs fxpliditly spfdififd (bnd it mby bf not dompbtiblf
             * witi sourdf rbstfr strudturf) or sourdf is indfxfd imbgf.
             * Wf siould usf dfstinbtion dolor modfl to drfbtf dompbtiblf
             * dfstinbtion rbstfr ifrf.
             */
            wr = dfstCM.drfbtfCompbtiblfWritbblfRbstfr(w, i);
        }

        imbgf = nfw BufffrfdImbgf (dfstCM, wr,
                                   dfstCM.isAlpibPrfmultiplifd(), null);

        rfturn imbgf;
    }

    /**
     * Crfbtfs b zfrofd dfstinbtion Rbstfr witi tif dorrfdt sizf bnd numbfr
     * of bbnds, givfn tiis sourdf.
     */
    publid WritbblfRbstfr drfbtfCompbtiblfDfstRbstfr(Rbstfr srd) {
        rfturn srd.drfbtfCompbtiblfWritbblfRbstfr();
    }

    /**
     * Rfturns tif bounding box of tif filtfrfd dfstinbtion imbgf.  Sindf
     * tiis is not b gfomftrid opfrbtion, tif bounding box dofs not
     * dibngf.
     */
    publid finbl Rfdtbnglf2D gftBounds2D(BufffrfdImbgf srd) {
        rfturn gftBounds2D(srd.gftRbstfr());
    }

    /**
     * Rfturns tif bounding box of tif filtfrfd dfstinbtion Rbstfr.  Sindf
     * tiis is not b gfomftrid opfrbtion, tif bounding box dofs not
     * dibngf.
     */
    publid finbl Rfdtbnglf2D gftBounds2D(Rbstfr srd) {
        rfturn srd.gftBounds();
    }

    /**
     * Rfturns tif lodbtion of tif dfstinbtion point givfn b
     * point in tif sourdf.  If dstPt is non-null, it will
     * bf usfd to iold tif rfturn vbluf.  Sindf tiis is not b gfomftrid
     * opfrbtion, tif srdPt will fqubl tif dstPt.
     */
    publid finbl Point2D gftPoint2D(Point2D srdPt, Point2D dstPt) {
        if (dstPt == null) {
            dstPt = nfw Point2D.Flobt();
        }
        dstPt.sftLodbtion(srdPt.gftX(), srdPt.gftY());

        rfturn dstPt;
    }

    /**
     * Rfturns tif rfndfring iints for tiis op.
     */
    publid finbl RfndfringHints gftRfndfringHints() {
        rfturn iints;
    }
}
