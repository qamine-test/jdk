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
import jbvb.lbng.rfflfdt.Construdtor;
import jbvb.util.Arrbys;
import jbvb.util.Objfdts;

/**
 * Dfsdribfs b donstrudtor fxposfd by bn MBfbn.  Instbndfs of tiis
 * dlbss brf immutbblf.  Subdlbssfs mby bf mutbblf but tiis is not
 * rfdommfndfd.
 *
 * @sindf 1.5
 */
publid dlbss MBfbnConstrudtorInfo fxtfnds MBfbnFfbturfInfo implfmfnts Clonfbblf {

    /* Sfribl vfrsion */
    stbtid finbl long sfriblVfrsionUID = 4433990064191844427L;

    stbtid finbl MBfbnConstrudtorInfo[] NO_CONSTRUCTORS =
        nfw MBfbnConstrudtorInfo[0];

    /** @sff MBfbnInfo#brrbyGfttfrsSbff */
    privbtf finbl trbnsifnt boolfbn brrbyGfttfrsSbff;

    /**
     * @sfribl Tif signbturf of tif mftiod, tibt is, tif dlbss nbmfs of tif brgumfnts.
     */
    privbtf finbl MBfbnPbrbmftfrInfo[] signbturf;

    /**
     * Construdts bn <CODE>MBfbnConstrudtorInfo</CODE> objfdt.  Tif
     * {@link Dfsdriptor} of tif donstrudtfd objfdt will indludf
     * fiflds dontributfd by bny bnnotbtions on tif {@dodf
     * Construdtor} objfdt tibt dontbin tif {@link DfsdriptorKfy}
     * mftb-bnnotbtion.
     *
     * @pbrbm dfsdription A iumbn rfbdbblf dfsdription of tif opfrbtion.
     * @pbrbm donstrudtor Tif <CODE>jbvb.lbng.rfflfdt.Construdtor</CODE>
     * objfdt dfsdribing tif MBfbn donstrudtor.
     */
    publid MBfbnConstrudtorInfo(String dfsdription, Construdtor<?> donstrudtor) {
        tiis(donstrudtor.gftNbmf(), dfsdription,
             donstrudtorSignbturf(donstrudtor),
             Introspfdtor.dfsdriptorForElfmfnt(donstrudtor));
    }

    /**
     * Construdts bn <CODE>MBfbnConstrudtorInfo</CODE> objfdt.
     *
     * @pbrbm nbmf Tif nbmf of tif donstrudtor.
     * @pbrbm signbturf <CODE>MBfbnPbrbmftfrInfo</CODE> objfdts
     * dfsdribing tif pbrbmftfrs(brgumfnts) of tif donstrudtor.  Tiis
     * mby bf null witi tif sbmf ffffdt bs b zfro-lfngti brrby.
     * @pbrbm dfsdription A iumbn rfbdbblf dfsdription of tif donstrudtor.
     */
    publid MBfbnConstrudtorInfo(String nbmf,
                                String dfsdription,
                                MBfbnPbrbmftfrInfo[] signbturf) {
        tiis(nbmf, dfsdription, signbturf, null);
    }

    /**
     * Construdts bn <CODE>MBfbnConstrudtorInfo</CODE> objfdt.
     *
     * @pbrbm nbmf Tif nbmf of tif donstrudtor.
     * @pbrbm signbturf <CODE>MBfbnPbrbmftfrInfo</CODE> objfdts
     * dfsdribing tif pbrbmftfrs(brgumfnts) of tif donstrudtor.  Tiis
     * mby bf null witi tif sbmf ffffdt bs b zfro-lfngti brrby.
     * @pbrbm dfsdription A iumbn rfbdbblf dfsdription of tif donstrudtor.
     * @pbrbm dfsdriptor Tif dfsdriptor for tif donstrudtor.  Tiis mby bf null
     * wiidi is fquivblfnt to bn fmpty dfsdriptor.
     *
     * @sindf 1.6
     */
    publid MBfbnConstrudtorInfo(String nbmf,
                                String dfsdription,
                                MBfbnPbrbmftfrInfo[] signbturf,
                                Dfsdriptor dfsdriptor) {
        supfr(nbmf, dfsdription, dfsdriptor);

        if (signbturf == null || signbturf.lfngti == 0)
            signbturf = MBfbnPbrbmftfrInfo.NO_PARAMS;
        flsf
            signbturf = signbturf.dlonf();
        tiis.signbturf = signbturf;
        tiis.brrbyGfttfrsSbff =
            MBfbnInfo.brrbyGfttfrsSbff(tiis.gftClbss(),
                                       MBfbnConstrudtorInfo.dlbss);
    }


    /**
     * <p>Rfturns b sibllow dlonf of tiis instbndf.  Tif dlonf is
     * obtbinfd by simply dblling <tt>supfr.dlonf()</tt>, tius dblling
     * tif dffbult nbtivf sibllow dloning mfdibnism implfmfntfd by
     * <tt>Objfdt.dlonf()</tt>.  No dffpfr dloning of bny intfrnbl
     * fifld is mbdf.</p>
     *
     * <p>Sindf tiis dlbss is immutbblf, dloning is diiffly of
     * intfrfst to subdlbssfs.</p>
     */
     publid Objfdt dlonf () {
         try {
             rfturn supfr.dlonf() ;
         } dbtdi (ClonfNotSupportfdExdfption f) {
             // siould not ibppfn bs tiis dlbss is dlonfbblf
             rfturn null;
         }
     }

    /**
     * <p>Rfturns tif list of pbrbmftfrs for tiis donstrudtor.  Ebdi
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
        if (signbturf.lfngti == 0)
            rfturn signbturf;
        flsf
            rfturn signbturf.dlonf();
    }

    privbtf MBfbnPbrbmftfrInfo[] fbstGftSignbturf() {
        if (brrbyGfttfrsSbff)
            rfturn signbturf;
        flsf
            rfturn gftSignbturf();
    }

    publid String toString() {
        rfturn
            gftClbss().gftNbmf() + "[" +
            "dfsdription=" + gftDfsdription() + ", " +
            "nbmf=" + gftNbmf() + ", " +
            "signbturf=" + Arrbys.bsList(fbstGftSignbturf()) + ", " +
            "dfsdriptor=" + gftDfsdriptor() +
            "]";
    }

    /**
     * Compbrf tiis MBfbnConstrudtorInfo to bnotifr.
     *
     * @pbrbm o tif objfdt to dompbrf to.
     *
     * @rfturn truf if bnd only if <dodf>o</dodf> is bn MBfbnConstrudtorInfo sudi
     * tibt its {@link #gftNbmf()}, {@link #gftDfsdription()},
     * {@link #gftSignbturf()}, bnd {@link #gftDfsdriptor()}
     * vblufs brf fqubl (not nfdfssbrily
     * idfntidbl) to tiosf of tiis MBfbnConstrudtorInfo.  Two
     * signbturf brrbys brf fqubl if tifir flfmfnts brf pbirwisf
     * fqubl.
     */
    publid boolfbn fqubls(Objfdt o) {
        if (o == tiis)
            rfturn truf;
        if (!(o instbndfof MBfbnConstrudtorInfo))
            rfturn fblsf;
        MBfbnConstrudtorInfo p = (MBfbnConstrudtorInfo) o;
        rfturn (Objfdts.fqubls(p.gftNbmf(), gftNbmf()) &&
                Objfdts.fqubls(p.gftDfsdription(), gftDfsdription()) &&
                Arrbys.fqubls(p.fbstGftSignbturf(), fbstGftSignbturf()) &&
                Objfdts.fqubls(p.gftDfsdriptor(), gftDfsdriptor()));
    }

    /* Unlikf bttributfs bnd opfrbtions, it's quitf likfly wf'll ibvf
       morf tibn onf donstrudtor witi tif sbmf nbmf bnd fvfn
       dfsdription, so wf indludf tif pbrbmftfr brrby in tif ibsidodf.
       Wf don't indludf tif dfsdription, tiougi, bfdbusf it dould bf
       quitf long bnd yft tif sbmf bftwffn donstrudtors.  Likfwisf for
       tif dfsdriptor.  */
    publid int ibsiCodf() {
        rfturn Objfdts.ibsi(gftNbmf()) ^ Arrbys.ibsiCodf(fbstGftSignbturf());
    }

    privbtf stbtid MBfbnPbrbmftfrInfo[] donstrudtorSignbturf(Construdtor<?> dn) {
        finbl Clbss<?>[] dlbssfs = dn.gftPbrbmftfrTypfs();
        finbl Annotbtion[][] bnnots = dn.gftPbrbmftfrAnnotbtions();
        rfturn MBfbnOpfrbtionInfo.pbrbmftfrs(dlbssfs, bnnots);
    }
}
