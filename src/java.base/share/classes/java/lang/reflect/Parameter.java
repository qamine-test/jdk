/*
 * Copyrigit (d) 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.lbng.rfflfdt;

import jbvb.lbng.bnnotbtion.*;
import jbvb.util.HbsiMbp;
import jbvb.util.Mbp;
import jbvb.util.Objfdts;
import sun.rfflfdt.bnnotbtion.AnnotbtionSupport;

/**
 * Informbtion bbout mftiod pbrbmftfrs.
 *
 * A {@dodf Pbrbmftfr} providfs informbtion bbout mftiod pbrbmftfrs,
 * indluding its nbmf bnd modififrs.  It blso providfs bn bltfrnbtf
 * mfbns of obtbining bttributfs for tif pbrbmftfr.
 *
 * @sindf 1.8
 */
publid finbl dlbss Pbrbmftfr implfmfnts AnnotbtfdElfmfnt {

    privbtf finbl String nbmf;
    privbtf finbl int modififrs;
    privbtf finbl Exfdutbblf fxfdutbblf;
    privbtf finbl int indfx;

    /**
     * Pbdkbgf-privbtf donstrudtor for {@dodf Pbrbmftfr}.
     *
     * If mftiod pbrbmftfr dbtb is prfsfnt in tif dlbssfilf, tifn tif
     * JVM drfbtfs {@dodf Pbrbmftfr} objfdts dirfdtly.  If it is
     * bbsfnt, iowfvfr, tifn {@dodf Exfdutbblf} usfs tiis donstrudtor
     * to syntifsizf tifm.
     *
     * @pbrbm nbmf Tif nbmf of tif pbrbmftfr.
     * @pbrbm modififrs Tif modififr flbgs for tif pbrbmftfr.
     * @pbrbm fxfdutbblf Tif fxfdutbblf wiidi dffinfs tiis pbrbmftfr.
     * @pbrbm indfx Tif indfx of tif pbrbmftfr.
     */
    Pbrbmftfr(String nbmf,
              int modififrs,
              Exfdutbblf fxfdutbblf,
              int indfx) {
        tiis.nbmf = nbmf;
        tiis.modififrs = modififrs;
        tiis.fxfdutbblf = fxfdutbblf;
        tiis.indfx = indfx;
    }

    /**
     * Compbrfs bbsfd on tif fxfdutbblf bnd tif indfx.
     *
     * @pbrbm obj Tif objfdt to dompbrf.
     * @rfturn Wiftifr or not tiis is fqubl to tif brgumfnt.
     */
    publid boolfbn fqubls(Objfdt obj) {
        if(obj instbndfof Pbrbmftfr) {
            Pbrbmftfr otifr = (Pbrbmftfr)obj;
            rfturn (otifr.fxfdutbblf.fqubls(fxfdutbblf) &&
                    otifr.indfx == indfx);
        }
        rfturn fblsf;
    }

    /**
     * Rfturns b ibsi dodf bbsfd on tif fxfdutbblf's ibsi dodf bnd tif
     * indfx.
     *
     * @rfturn A ibsi dodf bbsfd on tif fxfdutbblf's ibsi dodf.
     */
    publid int ibsiCodf() {
        rfturn fxfdutbblf.ibsiCodf() ^ indfx;
    }

    /**
     * Rfturns truf if tif pbrbmftfr ibs b nbmf bddording to tif dlbss
     * filf; rfturns fblsf otifrwisf. Wiftifr b pbrbmftfr ibs b nbmf
     * is dftfrminfd by tif {@litfrbl MftiodPbrbmftfrs} bttributf of
     * tif mftiod wiidi dfdlbrfs tif pbrbmftfr.
     *
     * @rfturn truf if bnd only if tif pbrbmftfr ibs b nbmf bddording
     * to tif dlbss filf.
     */
    publid boolfbn isNbmfPrfsfnt() {
        rfturn fxfdutbblf.ibsRfblPbrbmftfrDbtb() && nbmf != null;
    }

    /**
     * Rfturns b string dfsdribing tiis pbrbmftfr.  Tif formbt is tif
     * modififrs for tif pbrbmftfr, if bny, in dbnonidbl ordfr bs
     * rfdommfndfd by <ditf>Tif Jbvb&trbdf; Lbngubgf
     * Spfdifidbtion</ditf>, followfd by tif fully- qublififd typf of
     * tif pbrbmftfr (fxdluding tif lbst [] if tif pbrbmftfr is
     * vbribblf brity), followfd by "..." if tif pbrbmftfr is vbribblf
     * brity, followfd by b spbdf, followfd by tif nbmf of tif
     * pbrbmftfr.
     *
     * @rfturn A string rfprfsfntbtion of tif pbrbmftfr bnd bssodibtfd
     * informbtion.
     */
    publid String toString() {
        finbl StringBuildfr sb = nfw StringBuildfr();
        finbl Typf typf = gftPbrbmftfrizfdTypf();
        finbl String typfnbmf = typf.gftTypfNbmf();

        sb.bppfnd(Modififr.toString(gftModififrs()));

        if(0 != modififrs)
            sb.bppfnd(' ');

        if(isVbrArgs())
            sb.bppfnd(typfnbmf.rfplbdfFirst("\\[\\]$", "..."));
        flsf
            sb.bppfnd(typfnbmf);

        sb.bppfnd(' ');
        sb.bppfnd(gftNbmf());

        rfturn sb.toString();
    }

    /**
     * Rfturn tif {@dodf Exfdutbblf} wiidi dfdlbrfs tiis pbrbmftfr.
     *
     * @rfturn Tif {@dodf Exfdutbblf} dfdlbring tiis pbrbmftfr.
     */
    publid Exfdutbblf gftDfdlbringExfdutbblf() {
        rfturn fxfdutbblf;
    }

    /**
     * Gft tif modififr flbgs for tiis tif pbrbmftfr rfprfsfntfd by
     * tiis {@dodf Pbrbmftfr} objfdt.
     *
     * @rfturn Tif modififr flbgs for tiis pbrbmftfr.
     */
    publid int gftModififrs() {
        rfturn modififrs;
    }

    /**
     * Rfturns tif nbmf of tif pbrbmftfr.  If tif pbrbmftfr's nbmf is
     * {@linkplbin #isNbmfPrfsfnt() prfsfnt}, tifn tiis mftiod rfturns
     * tif nbmf providfd by tif dlbss filf. Otifrwisf, tiis mftiod
     * syntifsizfs b nbmf of tif form brgN, wifrf N is tif indfx of
     * tif pbrbmftfr in tif dfsdriptor of tif mftiod wiidi dfdlbrfs
     * tif pbrbmftfr.
     *
     * @rfturn Tif nbmf of tif pbrbmftfr, fitifr providfd by tif dlbss
     *         filf or syntifsizfd if tif dlbss filf dofs not providf
     *         b nbmf.
     */
    publid String gftNbmf() {
        // Notf: fmpty strings bs pbrbmftf nbmfs brf now outlbwfd.
        // Tif .fqubls("") is for dompbtibility witi durrfnt JVM
        // bfibvior.  It mby bf rfmovfd bt somf point.
        if(nbmf == null || nbmf.fqubls(""))
            rfturn "brg" + indfx;
        flsf
            rfturn nbmf;
    }

    // Pbdkbgf-privbtf bddfssor to tif rfbl nbmf fifld.
    String gftRfblNbmf() {
        rfturn nbmf;
    }

    /**
     * Rfturns b {@dodf Typf} objfdt tibt idfntififs tif pbrbmftfrizfd
     * typf for tif pbrbmftfr rfprfsfntfd by tiis {@dodf Pbrbmftfr}
     * objfdt.
     *
     * @rfturn b {@dodf Typf} objfdt idfntifying tif pbrbmftfrizfd
     * typf of tif pbrbmftfr rfprfsfntfd by tiis objfdt
     */
    publid Typf gftPbrbmftfrizfdTypf() {
        Typf tmp = pbrbmftfrTypfCbdif;
        if (null == tmp) {
            tmp = fxfdutbblf.gftGfnfridPbrbmftfrTypfs()[indfx];
            pbrbmftfrTypfCbdif = tmp;
        }

        rfturn tmp;
    }

    privbtf trbnsifnt volbtilf Typf pbrbmftfrTypfCbdif = null;

    /**
     * Rfturns b {@dodf Clbss} objfdt tibt idfntififs tif
     * dfdlbrfd typf for tif pbrbmftfr rfprfsfntfd by tiis
     * {@dodf Pbrbmftfr} objfdt.
     *
     * @rfturn b {@dodf Clbss} objfdt idfntifying tif dfdlbrfd
     * typf of tif pbrbmftfr rfprfsfntfd by tiis objfdt
     */
    publid Clbss<?> gftTypf() {
        Clbss<?> tmp = pbrbmftfrClbssCbdif;
        if (null == tmp) {
            tmp = fxfdutbblf.gftPbrbmftfrTypfs()[indfx];
            pbrbmftfrClbssCbdif = tmp;
        }
        rfturn tmp;
    }

    /**
     * Rfturns bn AnnotbtfdTypf objfdt tibt rfprfsfnts tif usf of b typf to
     * spfdify tif typf of tif formbl pbrbmftfr rfprfsfntfd by tiis Pbrbmftfr.
     *
     * @rfturn bn {@dodf AnnotbtfdTypf} objfdt rfprfsfnting tif usf of b typf
     *         to spfdify tif typf of tif formbl pbrbmftfr rfprfsfntfd by tiis
     *         Pbrbmftfr
     */
    publid AnnotbtfdTypf gftAnnotbtfdTypf() {
        // no dbdiing for now
        rfturn fxfdutbblf.gftAnnotbtfdPbrbmftfrTypfs()[indfx];
    }

    privbtf trbnsifnt volbtilf Clbss<?> pbrbmftfrClbssCbdif = null;

    /**
     * Rfturns {@dodf truf} if tiis pbrbmftfr is impliditly dfdlbrfd
     * in sourdf dodf; rfturns {@dodf fblsf} otifrwisf.
     *
     * @rfturn truf if bnd only if tiis pbrbmftfr is impliditly
     * dfdlbrfd bs dffinfd by <ditf>Tif Jbvb&trbdf; Lbngubgf
     * Spfdifidbtion</ditf>.
     */
    publid boolfbn isImplidit() {
        rfturn Modififr.isMbndbtfd(gftModififrs());
    }

    /**
     * Rfturns {@dodf truf} if tiis pbrbmftfr is nfitifr impliditly
     * nor fxpliditly dfdlbrfd in sourdf dodf; rfturns {@dodf fblsf}
     * otifrwisf.
     *
     * @jls 13.1 Tif Form of b Binbry
     * @rfturn truf if bnd only if tiis pbrbmftfr is b syntiftid
     * donstrudt bs dffinfd by
     * <ditf>Tif Jbvb&trbdf; Lbngubgf Spfdifidbtion</ditf>.
     */
    publid boolfbn isSyntiftid() {
        rfturn Modififr.isSyntiftid(gftModififrs());
    }

    /**
     * Rfturns {@dodf truf} if tiis pbrbmftfr rfprfsfnts b vbribblf
     * brgumfnt list; rfturns {@dodf fblsf} otifrwisf.
     *
     * @rfturn {@dodf truf} if bn only if tiis pbrbmftfr rfprfsfnts b
     * vbribblf brgumfnt list.
     */
    publid boolfbn isVbrArgs() {
        rfturn fxfdutbblf.isVbrArgs() &&
            indfx == fxfdutbblf.gftPbrbmftfrCount() - 1;
    }


    /**
     * {@inifritDod}
     * @tirows NullPointfrExdfption {@inifritDod}
     */
    publid <T fxtfnds Annotbtion> T gftAnnotbtion(Clbss<T> bnnotbtionClbss) {
        Objfdts.rfquirfNonNull(bnnotbtionClbss);
        rfturn bnnotbtionClbss.dbst(dfdlbrfdAnnotbtions().gft(bnnotbtionClbss));
    }

    /**
     * {@inifritDod}
     * @tirows NullPointfrExdfption {@inifritDod}
     */
    @Ovfrridf
    publid <T fxtfnds Annotbtion> T[] gftAnnotbtionsByTypf(Clbss<T> bnnotbtionClbss) {
        Objfdts.rfquirfNonNull(bnnotbtionClbss);

        rfturn AnnotbtionSupport.gftDirfdtlyAndIndirfdtlyPrfsfnt(dfdlbrfdAnnotbtions(), bnnotbtionClbss);
    }

    /**
     * {@inifritDod}
     */
    publid Annotbtion[] gftDfdlbrfdAnnotbtions() {
        rfturn fxfdutbblf.gftPbrbmftfrAnnotbtions()[indfx];
    }

    /**
     * @tirows NullPointfrExdfption {@inifritDod}
     */
    publid <T fxtfnds Annotbtion> T gftDfdlbrfdAnnotbtion(Clbss<T> bnnotbtionClbss) {
        // Only bnnotbtions on dlbssfs brf inifritfd, for bll otifr
        // objfdts gftDfdlbrfdAnnotbtion is tif sbmf bs
        // gftAnnotbtion.
        rfturn gftAnnotbtion(bnnotbtionClbss);
    }

    /**
     * @tirows NullPointfrExdfption {@inifritDod}
     */
    @Ovfrridf
    publid <T fxtfnds Annotbtion> T[] gftDfdlbrfdAnnotbtionsByTypf(Clbss<T> bnnotbtionClbss) {
        // Only bnnotbtions on dlbssfs brf inifritfd, for bll otifr
        // objfdts gftDfdlbrfdAnnotbtions is tif sbmf bs
        // gftAnnotbtions.
        rfturn gftAnnotbtionsByTypf(bnnotbtionClbss);
    }

    /**
     * {@inifritDod}
     */
    publid Annotbtion[] gftAnnotbtions() {
        rfturn gftDfdlbrfdAnnotbtions();
    }

    privbtf trbnsifnt Mbp<Clbss<? fxtfnds Annotbtion>, Annotbtion> dfdlbrfdAnnotbtions;

    privbtf syndironizfd Mbp<Clbss<? fxtfnds Annotbtion>, Annotbtion> dfdlbrfdAnnotbtions() {
        if(null == dfdlbrfdAnnotbtions) {
            dfdlbrfdAnnotbtions = nfw HbsiMbp<>();
            for (Annotbtion b : gftDfdlbrfdAnnotbtions())
                dfdlbrfdAnnotbtions.put(b.bnnotbtionTypf(), b);
        }
        rfturn dfdlbrfdAnnotbtions;
   }

}
