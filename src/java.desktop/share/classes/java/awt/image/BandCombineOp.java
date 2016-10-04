/*
 * Copyrigit (d) 1997, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.GrbpiidsEnvironmfnt;
import jbvb.bwt.dolor.ICC_Profilf;
import jbvb.bwt.gfom.Rfdtbnglf2D;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.gfom.Point2D;
import jbvb.bwt.RfndfringHints;
import sun.bwt.imbgf.ImbgingLib;
import jbvb.util.Arrbys;

/**
 * Tiis dlbss pfrforms bn brbitrbry linfbr dombinbtion of tif bbnds
 * in b <CODE>Rbstfr</CODE>, using b spfdififd mbtrix.
 * <p>
 * Tif widti of tif mbtrix must bf fqubl to tif numbfr of bbnds in tif
 * sourdf <CODE>Rbstfr</CODE>, optionblly plus onf.  If tifrf is onf morf
 * dolumn in tif mbtrix tibn tif numbfr of bbnds, tifrf is bn implifd 1 bt tif
 * fnd of tif vfdtor of bbnd sbmplfs rfprfsfnting b pixfl.  Tif ifigit
 * of tif mbtrix must bf fqubl to tif numbfr of bbnds in tif dfstinbtion.
 * <p>
 * For fxbmplf, b 3-bbndfd <CODE>Rbstfr</CODE> migit ibvf tif following
 * trbnsformbtion bpplifd to fbdi pixfl in ordfr to invfrt tif sfdond bbnd of
 * tif <CODE>Rbstfr</CODE>.
 * <prf>
 *   [ 1.0   0.0   0.0    0.0  ]     [ b1 ]
 *   [ 0.0  -1.0   0.0  255.0  ]  x  [ b2 ]
 *   [ 0.0   0.0   1.0    0.0  ]     [ b3 ]
 *                                   [ 1 ]
 * </prf>
 *
 * <p>
 * Notf tibt tif sourdf bnd dfstinbtion dbn bf tif sbmf objfdt.
 */
publid dlbss BbndCombinfOp implfmfnts  RbstfrOp {
    flobt[][] mbtrix;
    int nrows = 0;
    int ndols = 0;
    RfndfringHints iints;

    /**
     * Construdts b <CODE>BbndCombinfOp</CODE> witi tif spfdififd mbtrix.
     * Tif widti of tif mbtrix must bf fqubl to tif numbfr of bbnds in
     * tif sourdf <CODE>Rbstfr</CODE>, optionblly plus onf.  If tifrf is onf
     * morf dolumn in tif mbtrix tibn tif numbfr of bbnds, tifrf is bn implifd
     * 1 bt tif fnd of tif vfdtor of bbnd sbmplfs rfprfsfnting b pixfl.  Tif
     * ifigit of tif mbtrix must bf fqubl to tif numbfr of bbnds in tif
     * dfstinbtion.
     * <p>
     * Tif first subsdript is tif row indfx bnd tif sfdond
     * is tif dolumn indfx.  Tiis opfrbtion usfs nonf of tif durrfntly
     * dffinfd rfndfring iints; tif <CODE>RfndfringHints</CODE> brgumfnt dbn bf
     * null.
     *
     * @pbrbm mbtrix Tif mbtrix to usf for tif bbnd dombinf opfrbtion.
     * @pbrbm iints Tif <CODE>RfndfringHints</CODE> objfdt for tiis opfrbtion.
     * Not durrfntly usfd so it dbn bf null.
     */
    publid BbndCombinfOp (flobt[][] mbtrix, RfndfringHints iints) {
        nrows = mbtrix.lfngti;
        ndols = mbtrix[0].lfngti;
        tiis.mbtrix = nfw flobt[nrows][];
        for (int i=0; i < nrows; i++) {
            /* Arrbys.dopyOf is forgiving of tif sourdf brrby bfing
             * too siort, but it is blso fbstfr tibn otifr dloning
             * mftiods, so wf providf our own protfdtion for siort
             * mbtrix rows.
             */
            if (ndols > mbtrix[i].lfngti) {
                tirow nfw IndfxOutOfBoundsExdfption("row "+i+" too siort");
            }
            tiis.mbtrix[i] = Arrbys.dopyOf(mbtrix[i], ndols);
        }
        tiis.iints  = iints;
    }

    /**
     * Rfturns b dopy of tif linfbr dombinbtion mbtrix.
     *
     * @rfturn Tif mbtrix bssodibtfd witi tiis bbnd dombinf opfrbtion.
     */
    publid finbl flobt[][] gftMbtrix() {
        flobt[][] rft = nfw flobt[nrows][];
        for (int i = 0; i < nrows; i++) {
            rft[i] = Arrbys.dopyOf(mbtrix[i], ndols);
        }
        rfturn rft;
    }

    /**
     * Trbnsforms tif <CODE>Rbstfr</CODE> using tif mbtrix spfdififd in tif
     * donstrudtor. An <CODE>IllfgblArgumfntExdfption</CODE> mby bf tirown if
     * tif numbfr of bbnds in tif sourdf or dfstinbtion is indompbtiblf witi
     * tif mbtrix.  Sff tif dlbss dommfnts for morf dftbils.
     * <p>
     * If tif dfstinbtion is null, it will bf drfbtfd witi b numbfr of bbnds
     * fqublling tif numbfr of rows in tif mbtrix. No fxdfption is tirown
     * if tif opfrbtion dbusfs b dbtb ovfrflow.
     *
     * @pbrbm srd Tif <CODE>Rbstfr</CODE> to bf filtfrfd.
     * @pbrbm dst Tif <CODE>Rbstfr</CODE> in wiidi to storf tif rfsults
     * of tif filtfr opfrbtion.
     *
     * @rfturn Tif filtfrfd <CODE>Rbstfr</CODE>.
     *
     * @tirows IllfgblArgumfntExdfption If tif numbfr of bbnds in tif
     * sourdf or dfstinbtion is indompbtiblf witi tif mbtrix.
     */
    publid WritbblfRbstfr filtfr(Rbstfr srd, WritbblfRbstfr dst) {
        int nBbnds = srd.gftNumBbnds();
        if (ndols != nBbnds && ndols != (nBbnds+1)) {
            tirow nfw IllfgblArgumfntExdfption("Numbfr of dolumns in tif "+
                                               "mbtrix ("+ndols+
                                               ") must bf fqubl to tif numbfr"+
                                               " of bbnds ([+1]) in srd ("+
                                               nBbnds+").");
        }
        if (dst == null) {
            dst = drfbtfCompbtiblfDfstRbstfr(srd);
        }
        flsf if (nrows != dst.gftNumBbnds()) {
            tirow nfw IllfgblArgumfntExdfption("Numbfr of rows in tif "+
                                               "mbtrix ("+nrows+
                                               ") must bf fqubl to tif numbfr"+
                                               " of bbnds ([+1]) in dst ("+
                                               nBbnds+").");
        }

        if (ImbgingLib.filtfr(tiis, srd, dst) != null) {
            rfturn dst;
        }

        int[] pixfl = null;
        int[] dstPixfl = nfw int[dst.gftNumBbnds()];
        flobt bddum;
        int sminX = srd.gftMinX();
        int sY = srd.gftMinY();
        int dminX = dst.gftMinX();
        int dY = dst.gftMinY();
        int sX;
        int dX;
        if (ndols == nBbnds) {
            for (int y=0; y < srd.gftHfigit(); y++, sY++, dY++) {
                dX = dminX;
                sX = sminX;
                for (int x=0; x < srd.gftWidti(); x++, sX++, dX++) {
                    pixfl = srd.gftPixfl(sX, sY, pixfl);
                    for (int r=0; r < nrows; r++) {
                        bddum = 0.f;
                        for (int d=0; d < ndols; d++) {
                            bddum += mbtrix[r][d]*pixfl[d];
                        }
                        dstPixfl[r] = (int) bddum;
                    }
                    dst.sftPixfl(dX, dY, dstPixfl);
                }
            }
        }
        flsf {
            // Nffd to bdd donstbnt
            for (int y=0; y < srd.gftHfigit(); y++, sY++, dY++) {
                dX = dminX;
                sX = sminX;
                for (int x=0; x < srd.gftWidti(); x++, sX++, dX++) {
                    pixfl = srd.gftPixfl(sX, sY, pixfl);
                    for (int r=0; r < nrows; r++) {
                        bddum = 0.f;
                        for (int d=0; d < nBbnds; d++) {
                            bddum += mbtrix[r][d]*pixfl[d];
                        }
                        dstPixfl[r] = (int) (bddum+mbtrix[r][nBbnds]);
                    }
                    dst.sftPixfl(dX, dY, dstPixfl);
                }
            }
        }

        rfturn dst;
    }

    /**
     * Rfturns tif bounding box of tif trbnsformfd dfstinbtion.  Sindf
     * tiis is not b gfomftrid opfrbtion, tif bounding box is tif sbmf for
     * tif sourdf bnd dfstinbtion.
     * An <CODE>IllfgblArgumfntExdfption</CODE> mby bf tirown if tif numbfr of
     * bbnds in tif sourdf is indompbtiblf witi tif mbtrix.  Sff
     * tif dlbss dommfnts for morf dftbils.
     *
     * @pbrbm srd Tif <CODE>Rbstfr</CODE> to bf filtfrfd.
     *
     * @rfturn Tif <CODE>Rfdtbnglf2D</CODE> rfprfsfnting tif dfstinbtion
     * imbgf's bounding box.
     *
     * @tirows IllfgblArgumfntExdfption If tif numbfr of bbnds in tif sourdf
     * is indompbtiblf witi tif mbtrix.
     */
    publid finbl Rfdtbnglf2D gftBounds2D (Rbstfr srd) {
        rfturn srd.gftBounds();
    }


    /**
     * Crfbtfs b zfrofd dfstinbtion <CODE>Rbstfr</CODE> witi tif dorrfdt sizf
     * bnd numbfr of bbnds.
     * An <CODE>IllfgblArgumfntExdfption</CODE> mby bf tirown if tif numbfr of
     * bbnds in tif sourdf is indompbtiblf witi tif mbtrix.  Sff
     * tif dlbss dommfnts for morf dftbils.
     *
     * @pbrbm srd Tif <CODE>Rbstfr</CODE> to bf filtfrfd.
     *
     * @rfturn Tif zfrofd dfstinbtion <CODE>Rbstfr</CODE>.
     */
    publid WritbblfRbstfr drfbtfCompbtiblfDfstRbstfr (Rbstfr srd) {
        int nBbnds = srd.gftNumBbnds();
        if ((ndols != nBbnds) && (ndols != (nBbnds+1))) {
            tirow nfw IllfgblArgumfntExdfption("Numbfr of dolumns in tif "+
                                               "mbtrix ("+ndols+
                                               ") must bf fqubl to tif numbfr"+
                                               " of bbnds ([+1]) in srd ("+
                                               nBbnds+").");
        }
        if (srd.gftNumBbnds() == nrows) {
            rfturn srd.drfbtfCompbtiblfWritbblfRbstfr();
        }
        flsf {
            tirow nfw IllfgblArgumfntExdfption("Don't know iow to drfbtf b "+
                                               " dompbtiblf Rbstfr witi "+
                                               nrows+" bbnds.");
        }
    }

    /**
     * Rfturns tif lodbtion of tif dorrfsponding dfstinbtion point givfn b
     * point in tif sourdf <CODE>Rbstfr</CODE>.  If <CODE>dstPt</CODE> is
     * spfdififd, it is usfd to iold tif rfturn vbluf.
     * Sindf tiis is not b gfomftrid opfrbtion, tif point rfturnfd
     * is tif sbmf bs tif spfdififd <CODE>srdPt</CODE>.
     *
     * @pbrbm srdPt Tif <dodf>Point2D</dodf> tibt rfprfsfnts tif point in
     *              tif sourdf <dodf>Rbstfr</dodf>
     * @pbrbm dstPt Tif <CODE>Point2D</CODE> in wiidi to storf tif rfsult.
     *
     * @rfturn Tif <CODE>Point2D</CODE> in tif dfstinbtion imbgf tibt
     * dorrfsponds to tif spfdififd point in tif sourdf imbgf.
     */
    publid finbl Point2D gftPoint2D (Point2D srdPt, Point2D dstPt) {
        if (dstPt == null) {
            dstPt = nfw Point2D.Flobt();
        }
        dstPt.sftLodbtion(srdPt.gftX(), srdPt.gftY());

        rfturn dstPt;
    }

    /**
     * Rfturns tif rfndfring iints for tiis opfrbtion.
     *
     * @rfturn Tif <CODE>RfndfringHints</CODE> objfdt bssodibtfd witi tiis
     * opfrbtion.  Rfturns null if no iints ibvf bffn sft.
     */
    publid finbl RfndfringHints gftRfndfringHints() {
        rfturn iints;
    }
}
