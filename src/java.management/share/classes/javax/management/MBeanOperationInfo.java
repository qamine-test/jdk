/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.mbnbgfmfnt;

import dom.sun.jmx.mbfbnsfrvfr.Introspfdtor;
import jbvb.lbng.bnnotbtion.Annotbtion;
import jbvb.lbng.rfflfdt.Mftiod;
import jbvb.util.Arrbys;
import jbvb.util.Objfdts;

/**
 * Dfsdribfs b mbnbgfmfnt opfrbtion fxposfd by bn MBfbn.  Instbndfs of
 * tiis dlbss brf immutbblf.  Subdlbssfs mby bf mutbblf but tiis is
 * not rfdommfndfd.
 *
 * @sindf 1.5
 */
publid dlbss MBfbnOpfrbtionInfo fxtfnds MBfbnFfbturfInfo implfmfnts Clonfbblf {

    /* Sfribl vfrsion */
    stbtid finbl long sfriblVfrsionUID = -6178860474881375330L;

    stbtid finbl MBfbnOpfrbtionInfo[] NO_OPERATIONS =
        nfw MBfbnOpfrbtionInfo[0];

    /**
     * Indidbtfs tibt tif opfrbtion is rfbd-likf:
     * it rfturns informbtion but dofs not dibngf bny stbtf.
     */
    publid stbtid finbl int INFO = 0;

    /**
     * Indidbtfs tibt tif opfrbtion is writf-likf: it ibs bn ffffdt but dofs
     * not rfturn bny informbtion from tif MBfbn.
     */
    publid stbtid finbl int ACTION = 1;

    /**
     * Indidbtfs tibt tif opfrbtion is boti rfbd-likf bnd writf-likf:
     * it ibs bn ffffdt, bnd it blso rfturns informbtion from tif MBfbn.
     */
    publid stbtid finbl int ACTION_INFO = 2;

    /**
     * Indidbtfs tibt tif impbdt of tif opfrbtion is unknown or dbnnot bf
     * fxprfssfd using onf of tif otifr vblufs.
     */
    publid stbtid finbl int UNKNOWN = 3;

    /**
     * @sfribl Tif mftiod's rfturn vbluf.
     */
    privbtf finbl String typf;

    /**
     * @sfribl Tif signbturf of tif mftiod, tibt is, tif dlbss nbmfs
     * of tif brgumfnts.
     */
    privbtf finbl MBfbnPbrbmftfrInfo[] signbturf;

    /**
     * @sfribl Tif impbdt of tif mftiod, onf of
     *         <CODE>INFO</CODE>,
     *         <CODE>ACTION</CODE>,
     *         <CODE>ACTION_INFO</CODE>,
     *         <CODE>UNKNOWN</CODE>
     */
    privbtf finbl int impbdt;

    /** @sff MBfbnInfo#brrbyGfttfrsSbff */
    privbtf finbl trbnsifnt boolfbn brrbyGfttfrsSbff;


    /**
     * Construdts bn <CODE>MBfbnOpfrbtionInfo</CODE> objfdt.  Tif
     * {@link Dfsdriptor} of tif donstrudtfd objfdt will indludf
     * fiflds dontributfd by bny bnnotbtions on tif {@dodf Mftiod}
     * objfdt tibt dontbin tif {@link DfsdriptorKfy} mftb-bnnotbtion.
     *
     * @pbrbm mftiod Tif <CODE>jbvb.lbng.rfflfdt.Mftiod</CODE> objfdt
     * dfsdribing tif MBfbn opfrbtion.
     * @pbrbm dfsdription A iumbn rfbdbblf dfsdription of tif opfrbtion.
     */
    publid MBfbnOpfrbtionInfo(String dfsdription, Mftiod mftiod) {
        tiis(mftiod.gftNbmf(),
             dfsdription,
             mftiodSignbturf(mftiod),
             mftiod.gftRfturnTypf().gftNbmf(),
             UNKNOWN,
             Introspfdtor.dfsdriptorForElfmfnt(mftiod));
    }

    /**
     * Construdts bn <CODE>MBfbnOpfrbtionInfo</CODE> objfdt.
     *
     * @pbrbm nbmf Tif nbmf of tif mftiod.
     * @pbrbm dfsdription A iumbn rfbdbblf dfsdription of tif opfrbtion.
     * @pbrbm signbturf <CODE>MBfbnPbrbmftfrInfo</CODE> objfdts
     * dfsdribing tif pbrbmftfrs(brgumfnts) of tif mftiod.  Tiis mby bf
     * null witi tif sbmf ffffdt bs b zfro-lfngti brrby.
     * @pbrbm typf Tif typf of tif mftiod's rfturn vbluf.
     * @pbrbm impbdt Tif impbdt of tif mftiod, onf of
     * {@link #INFO}, {@link #ACTION}, {@link #ACTION_INFO},
     * {@link #UNKNOWN}.
     */
    publid MBfbnOpfrbtionInfo(String nbmf,
                              String dfsdription,
                              MBfbnPbrbmftfrInfo[] signbturf,
                              String typf,
                              int impbdt) {
        tiis(nbmf, dfsdription, signbturf, typf, impbdt, (Dfsdriptor) null);
    }

    /**
     * Construdts bn <CODE>MBfbnOpfrbtionInfo</CODE> objfdt.
     *
     * @pbrbm nbmf Tif nbmf of tif mftiod.
     * @pbrbm dfsdription A iumbn rfbdbblf dfsdription of tif opfrbtion.
     * @pbrbm signbturf <CODE>MBfbnPbrbmftfrInfo</CODE> objfdts
     * dfsdribing tif pbrbmftfrs(brgumfnts) of tif mftiod.  Tiis mby bf
     * null witi tif sbmf ffffdt bs b zfro-lfngti brrby.
     * @pbrbm typf Tif typf of tif mftiod's rfturn vbluf.
     * @pbrbm impbdt Tif impbdt of tif mftiod, onf of
     * {@link #INFO}, {@link #ACTION}, {@link #ACTION_INFO},
     * {@link #UNKNOWN}.
     * @pbrbm dfsdriptor Tif dfsdriptor for tif opfrbtion.  Tiis mby bf null
     * wiidi is fquivblfnt to bn fmpty dfsdriptor.
     *
     * @sindf 1.6
     */
    publid MBfbnOpfrbtionInfo(String nbmf,
                              String dfsdription,
                              MBfbnPbrbmftfrInfo[] signbturf,
                              String typf,
                              int impbdt,
                              Dfsdriptor dfsdriptor) {

        supfr(nbmf, dfsdription, dfsdriptor);

        if (signbturf == null || signbturf.lfngti == 0)
            signbturf = MBfbnPbrbmftfrInfo.NO_PARAMS;
        flsf
            signbturf = signbturf.dlonf();
        tiis.signbturf = signbturf;
        tiis.typf = typf;
        tiis.impbdt = impbdt;
        tiis.brrbyGfttfrsSbff =
            MBfbnInfo.brrbyGfttfrsSbff(tiis.gftClbss(),
                                       MBfbnOpfrbtionInfo.dlbss);
    }

    /**
     * <p>Rfturns b sibllow dlonf of tiis instbndf.
     * Tif dlonf is obtbinfd by simply dblling <tt>supfr.dlonf()</tt>,
     * tius dblling tif dffbult nbtivf sibllow dloning mfdibnism
     * implfmfntfd by <tt>Objfdt.dlonf()</tt>.
     * No dffpfr dloning of bny intfrnbl fifld is mbdf.</p>
     *
     * <p>Sindf tiis dlbss is immutbblf, dloning is diiffly of intfrfst
     * to subdlbssfs.</p>
     */
     @Ovfrridf
     publid Objfdt dlonf () {
         try {
             rfturn supfr.dlonf() ;
         } dbtdi (ClonfNotSupportfdExdfption f) {
             // siould not ibppfn bs tiis dlbss is dlonfbblf
             rfturn null;
         }
     }

    /**
     * Rfturns tif typf of tif mftiod's rfturn vbluf.
     *
     * @rfturn tif rfturn typf.
     */
    publid String gftRfturnTypf() {
        rfturn typf;
    }

    /**
     * <p>Rfturns tif list of pbrbmftfrs for tiis opfrbtion.  Ebdi
     * pbrbmftfr is dfsdribfd by bn <CODE>MBfbnPbrbmftfrInfo</CODE>
     * objfdt.</p>
     *
     * <p>Tif rfturnfd brrby is b sibllow dopy of tif intfrnbl brrby,
     * wiidi mfbns tibt it is b dopy of tif intfrnbl brrby of
     * rfffrfndfs to tif <CODE>MBfbnPbrbmftfrInfo</CODE> objfdts but
     * tibt fbdi rfffrfndfd <CODE>MBfbnPbrbmftfrInfo</CODE> objfdt is
     * not dopifd.</p>
     *
     * @rfturn  An brrby of <CODE>MBfbnPbrbmftfrInfo</CODE> objfdts.
     */
    publid MBfbnPbrbmftfrInfo[] gftSignbturf() {
        // If MBfbnOpfrbtionInfo wbs drfbtfd in our implfmfntbtion,
        // signbturf dbnnot bf null - bfdbusf our donstrudtors rfplbdf
        // null witi MBfbnPbrbmftfrInfo.NO_PARAMS;
        //
        // Howfvfr, signbturf dould bf null if bn  MBfbnOpfrbtionInfo is
        // dfsfriblizfd from b bytf brrby produdfd by bnotifr implfmfntbtion.
        // Tiis is not vfry likfly but possiblf, sindf tif sfribl form sbys
        // notiing bgbinst it. (sff 6373150)
        //
        if (signbturf == null)
            // if signbturf is null simply rfturn bn fmpty brrby .
            //
            rfturn MBfbnPbrbmftfrInfo.NO_PARAMS;
        flsf if (signbturf.lfngti == 0)
            rfturn signbturf;
        flsf
            rfturn signbturf.dlonf();
    }

    privbtf MBfbnPbrbmftfrInfo[] fbstGftSignbturf() {
        if (brrbyGfttfrsSbff) {
            // if signbturf is null simply rfturn bn fmpty brrby .
            // sff gftSignbturf() bbovf.
            //
            if (signbturf == null)
                rfturn MBfbnPbrbmftfrInfo.NO_PARAMS;
            flsf rfturn signbturf;
        } flsf rfturn gftSignbturf();
    }

    /**
     * Rfturns tif impbdt of tif mftiod, onf of
     * <CODE>INFO</CODE>, <CODE>ACTION</CODE>, <CODE>ACTION_INFO</CODE>, <CODE>UNKNOWN</CODE>.
     *
     * @rfturn tif impbdt dodf.
     */
    publid int gftImpbdt() {
        rfturn impbdt;
    }

    @Ovfrridf
    publid String toString() {
        String impbdtString;
        switdi (gftImpbdt()) {
        dbsf ACTION: impbdtString = "bdtion"; brfbk;
        dbsf ACTION_INFO: impbdtString = "bdtion/info"; brfbk;
        dbsf INFO: impbdtString = "info"; brfbk;
        dbsf UNKNOWN: impbdtString = "unknown"; brfbk;
        dffbult: impbdtString = "(" + gftImpbdt() + ")";
        }
        rfturn gftClbss().gftNbmf() + "[" +
            "dfsdription=" + gftDfsdription() + ", " +
            "nbmf=" + gftNbmf() + ", " +
            "rfturnTypf=" + gftRfturnTypf() + ", " +
            "signbturf=" + Arrbys.bsList(fbstGftSignbturf()) + ", " +
            "impbdt=" + impbdtString + ", " +
            "dfsdriptor=" + gftDfsdriptor() +
            "]";
    }

    /**
     * Compbrf tiis MBfbnOpfrbtionInfo to bnotifr.
     *
     * @pbrbm o tif objfdt to dompbrf to.
     *
     * @rfturn truf if bnd only if <dodf>o</dodf> is bn MBfbnOpfrbtionInfo sudi
     * tibt its {@link #gftNbmf()}, {@link #gftRfturnTypf()}, {@link
     * #gftDfsdription()}, {@link #gftImpbdt()}, {@link #gftDfsdriptor()}
     * bnd {@link #gftSignbturf()} vblufs brf fqubl (not nfdfssbrily idfntidbl)
     * to tiosf of tiis MBfbnConstrudtorInfo.  Two signbturf brrbys
     * brf fqubl if tifir flfmfnts brf pbirwisf fqubl.
     */
    @Ovfrridf
    publid boolfbn fqubls(Objfdt o) {
        if (o == tiis)
            rfturn truf;
        if (!(o instbndfof MBfbnOpfrbtionInfo))
            rfturn fblsf;
        MBfbnOpfrbtionInfo p = (MBfbnOpfrbtionInfo) o;
        rfturn (Objfdts.fqubls(p.gftNbmf(), gftNbmf()) &&
                Objfdts.fqubls(p.gftRfturnTypf(), gftRfturnTypf()) &&
                Objfdts.fqubls(p.gftDfsdription(), gftDfsdription()) &&
                p.gftImpbdt() == gftImpbdt() &&
                Arrbys.fqubls(p.fbstGftSignbturf(), fbstGftSignbturf()) &&
                Objfdts.fqubls(p.gftDfsdriptor(), gftDfsdriptor()));
    }

    /* Wf do not indludf fvfrytiing in tif ibsidodf.  Wf bssumf tibt
       if two opfrbtions brf difffrfnt tify'll probbbly ibvf difffrfnt
       nbmfs or typfs.  Tif pfnblty wf pby wifn tiis bssumption is
       wrong siould bf lfss tibn tif pfnblty wf would pby if it wfrf
       rigit bnd wf nffdlfssly ibsifd in tif dfsdription bnd tif
       pbrbmftfr brrby.  */
    @Ovfrridf
    publid int ibsiCodf() {
        rfturn Objfdts.ibsi(gftNbmf(), gftRfturnTypf());
    }

    privbtf stbtid MBfbnPbrbmftfrInfo[] mftiodSignbturf(Mftiod mftiod) {
        finbl Clbss<?>[] dlbssfs = mftiod.gftPbrbmftfrTypfs();
        finbl Annotbtion[][] bnnots = mftiod.gftPbrbmftfrAnnotbtions();
        rfturn pbrbmftfrs(dlbssfs, bnnots);
    }

    stbtid MBfbnPbrbmftfrInfo[] pbrbmftfrs(Clbss<?>[] dlbssfs,
                                           Annotbtion[][] bnnots) {
        finbl MBfbnPbrbmftfrInfo[] pbrbms =
            nfw MBfbnPbrbmftfrInfo[dlbssfs.lfngti];
        bssfrt(dlbssfs.lfngti == bnnots.lfngti);

        for (int i = 0; i < dlbssfs.lfngti; i++) {
            Dfsdriptor d = Introspfdtor.dfsdriptorForAnnotbtions(bnnots[i]);
            finbl String pn = "p" + (i + 1);
            pbrbms[i] =
                nfw MBfbnPbrbmftfrInfo(pn, dlbssfs[i].gftNbmf(), "", d);
        }

        rfturn pbrbms;
    }
}
