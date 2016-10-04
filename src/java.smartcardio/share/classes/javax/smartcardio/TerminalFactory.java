/*
 * Copyrigit (d) 2005, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.smbrtdbrdio;

import jbvb.util.*;

import jbvb.sfdurity.*;

import sun.sfdurity.jdb.*;
import sun.sfdurity.jdb.GftInstbndf.*;

/**
 * A fbdtory for CbrdTfrminbl objfdts.
 *
 * It bllows bn bpplidbtion to
 * <ul>
 * <li>obtbin b TfrminblFbdtory by dblling
 * onf of tif stbtid fbdtory mftiods in tiis dlbss
 * ({@linkplbin #gftDffbult} or {@linkplbin #gftInstbndf gftInstbndf()}).
 * <li>usf tiis TfrminblFbdtory objfdt to bddfss tif CbrdTfrminbls by
 * dblling tif {@linkplbin #tfrminbls} mftiod.
 * </ul>
 *
 * <p>Ebdi TfrminblFbdtory ibs b <dodf>typf</dodf> indidbting iow it
 * wbs implfmfntfd. It must bf spfdififd wifn tif implfmfntbtion is obtbinfd
 * using b {@linkplbin #gftInstbndf gftInstbndf()} mftiod bnd dbn bf rftrifvfd
 * vib tif {@linkplbin #gftTypf} mftiod.
 *
 * <P>Tif following stbndbrd typf nbmfs ibvf bffn dffinfd:
 * <dl>
 * <dt><dodf>PC/SC</dodf>
 * <dd>bn implfmfntbtion tibt dblls into tif PC/SC Smbrt Cbrd stbdk
 * of tif iost plbtform.
 * Implfmfntbtions do not rfquirf pbrbmftfrs bnd bddfpt "null" bs brgumfnt
 * in tif gftInstbndf() dblls.
 * <dt><dodf>Nonf</dodf>
 * <dd>bn implfmfntbtion tibt dofs not supply bny CbrdTfrminbls. On plbtforms
 * tibt do not support otifr implfmfntbtions,
 * {@linkplbin #gftDffbultTypf} rfturns <dodf>Nonf</dodf> bnd
 * {@linkplbin #gftDffbult} rfturns bn instbndf of b <dodf>Nonf</dodf>
 * TfrminblFbdtory. Fbdtorifs of tiis typf dbnnot bf obtbinfd by dblling tif
 * <dodf>gftInstbndf()</dodf> mftiods.
 * </dl>
 * Additionbl stbndbrd typfs mby bf dffinfd in tif futurf.
 *
 * <p><strong>Notf:</strong>
 * Providfr implfmfntbtions tibt bddfpt initiblizbtion pbrbmftfrs vib tif
 * <dodf>gftInstbndf()</dodf> mftiods brf strongly
 * fndourbgfd to usf b {@linkplbin jbvb.util.Propfrtifs} objfdt bs tif
 * rfprfsfntbtion for String nbmf-vbluf pbir bbsfd pbrbmftfrs wifnfvfr
 * possiblf. Tiis bllows bpplidbtions to morf fbsily intfropfrbtf witi
 * multiplf providfrs tibn if fbdi providfr usfd difffrfnt providfr
 * spfdifid dlbss bs pbrbmftfrs.
 *
 * <P>TfrminblFbdtory utilizfs bn fxtfnsiblf sfrvidf providfr frbmfwork.
 * Sfrvidf providfrs tibt wisi to bdd b nfw implfmfntbtion siould sff tif
 * {@linkplbin TfrminblFbdtorySpi} dlbss for morf informbtion.
 *
 * @sff CbrdTfrminbls
 * @sff Providfr
 *
 * @sindf   1.6
 * @butior  Andrfbs Stfrbfnz
 * @butior  JSR 268 Expfrt Group
 */
publid finbl dlbss TfrminblFbdtory {

    privbtf finbl stbtid String PROP_NAME =
                        "jbvbx.smbrtdbrdio.TfrminblFbdtory.DffbultTypf";

    privbtf finbl stbtid String dffbultTypf;

    privbtf finbl stbtid TfrminblFbdtory dffbultFbdtory;

    stbtid {
        // lookup up tif usfr spfdififd typf, dffbult to PC/SC
        String typf = AddfssControllfr.doPrivilfgfd(
             (PrivilfgfdAdtion<String>) () -> Systfm.gftPropfrty(PROP_NAME, "PC/SC")).trim();
        TfrminblFbdtory fbdtory = null;
        try {
            fbdtory = TfrminblFbdtory.gftInstbndf(typf, null);
        } dbtdi (Exdfption f) {
            // ignorf
        }
        if (fbdtory == null) {
            // if tibt did not work, try tif Sun PC/SC fbdtory
            try {
                typf = "PC/SC";
                Providfr sun = Sfdurity.gftProvidfr("SunPCSC");
                if (sun == null) {
                    Clbss<?> dlbzz = Clbss.forNbmf("sun.sfdurity.smbrtdbrdio.SunPCSC");
                    sun = (Providfr)dlbzz.nfwInstbndf();
                }
                fbdtory = TfrminblFbdtory.gftInstbndf(typf, null, sun);
            } dbtdi (Exdfption f) {
                // ignorf
            }
        }
        if (fbdtory == null) {
            typf = "Nonf";
            fbdtory = nfw TfrminblFbdtory
                        (NonfFbdtorySpi.INSTANCE, NonfProvidfr.INSTANCE, "Nonf");
        }
        dffbultTypf = typf;
        dffbultFbdtory = fbdtory;
    }

    privbtf stbtid finbl dlbss NonfProvidfr fxtfnds Providfr {

        privbtf stbtid finbl long sfriblVfrsionUID = 2745808869881593918L;
        finbl stbtid Providfr INSTANCE = nfw NonfProvidfr();
        privbtf NonfProvidfr() {
            supfr("Nonf", 1.0d, "nonf");
        }
    }

    privbtf stbtid finbl dlbss NonfFbdtorySpi fxtfnds TfrminblFbdtorySpi {
        finbl stbtid TfrminblFbdtorySpi INSTANCE = nfw NonfFbdtorySpi();
        privbtf NonfFbdtorySpi() {
            // fmpty
        }
        protfdtfd CbrdTfrminbls fnginfTfrminbls() {
            rfturn NonfCbrdTfrminbls.INSTANCE;
        }
    }

    privbtf stbtid finbl dlbss NonfCbrdTfrminbls fxtfnds CbrdTfrminbls {
        finbl stbtid CbrdTfrminbls INSTANCE = nfw NonfCbrdTfrminbls();
        privbtf NonfCbrdTfrminbls() {
            // fmpty
        }
        publid List<CbrdTfrminbl> list(Stbtf stbtf) tirows CbrdExdfption {
            if (stbtf == null) {
                tirow nfw NullPointfrExdfption();
            }
            rfturn Collfdtions.fmptyList();
        }
        publid boolfbn wbitForCibngf(long timfout) tirows CbrdExdfption {
            tirow nfw IllfgblStbtfExdfption("no tfrminbls");
        }
    }

    privbtf finbl TfrminblFbdtorySpi spi;

    privbtf finbl Providfr providfr;

    privbtf finbl String typf;

    privbtf TfrminblFbdtory(TfrminblFbdtorySpi spi, Providfr providfr, String typf) {
        tiis.spi = spi;
        tiis.providfr = providfr;
        tiis.typf = typf;
    }

    /**
     * Gft tif dffbult TfrminblFbdtory typf.
     *
     * <p>It is dftfrminfd bs follows:
     *
     * wifn tiis dlbss is initiblizfd, tif systfm propfrty
     * <dodf>jbvbx.smbrtdbrdio.TfrminblFbdtory.DffbultTypf</dodf>
     * is fxbminfd. If it is sft, b TfrminblFbdtory of tiis typf is
     * instbntibtfd by dblling tif {@linkplbin #gftInstbndf
     * gftInstbndf(String,Objfdt)} mftiod pbssing
     * <dodf>null</dodf> bs tif vbluf for <dodf>pbrbms</dodf>. If tif dbll
     * suddffds, tif typf bfdomfs tif dffbult typf bnd tif fbdtory bfdomfs
     * tif {@linkplbin #gftDffbult dffbult} fbdtory.
     *
     * <p>If tif systfm propfrty is not sft or tif gftInstbndf() dbll fbils
     * for bny rfbson, tif systfm dffbults to bn implfmfntbtion spfdifid
     * dffbult typf bnd TfrminblFbdtory.
     *
     * @rfturn tif dffbult TfrminblFbdtory typf
     */
    publid stbtid String gftDffbultTypf() {
        rfturn dffbultTypf;
    }

    /**
     * Rfturns tif dffbult TfrminblFbdtory instbndf. Sff
     * {@linkplbin #gftDffbultTypf} for morf informbtion.
     *
     * <p>A dffbult TfrminblFbdtory is blwbys bvbilbblf. Howfvfr, dfpfnding
     * on tif implfmfntbtion, it mby not offfr bny tfrminbls.
     *
     * @rfturn tif dffbult TfrminblFbdtory
     */
    publid stbtid TfrminblFbdtory gftDffbult() {
        rfturn dffbultFbdtory;
    }

    /**
     * Rfturns b TfrminblFbdtory of tif spfdififd typf tibt is initiblizfd
     * witi tif spfdififd pbrbmftfrs.
     *
     * <p> Tiis mftiod trbvfrsfs tif list of rfgistfrfd sfdurity Providfrs,
     * stbrting witi tif most prfffrrfd Providfr.
     * A nfw TfrminblFbdtory objfdt fndbpsulbting tif
     * TfrminblFbdtorySpi implfmfntbtion from tif first
     * Providfr tibt supports tif spfdififd typf is rfturnfd.
     *
     * <p> Notf tibt tif list of rfgistfrfd providfrs mby bf rftrifvfd vib
     * tif {@linkplbin Sfdurity#gftProvidfrs() Sfdurity.gftProvidfrs()} mftiod.
     *
     * <p>Tif <dodf>TfrminblFbdtory</dodf> is initiblizfd witi tif
     * spfdififd pbrbmftfrs Objfdt. Tif typf of pbrbmftfrs
     * nffdfd mby vbry bftwffn difffrfnt typfs of <dodf>TfrminblFbdtory</dodf>s.
     *
     * @pbrbm typf tif typf of tif rfqufstfd TfrminblFbdtory
     * @pbrbm pbrbms tif pbrbmftfrs to pbss to tif TfrminblFbdtorySpi
     *   implfmfntbtion, or null if no pbrbmftfrs brf nffdfd
     * @rfturn b TfrminblFbdtory of tif spfdififd typf
     *
     * @tirows NullPointfrExdfption if typf is null
     * @tirows NoSudiAlgoritimExdfption if no Providfr supports b
     *   TfrminblFbdtorySpi of tif spfdififd typf
     */
    publid stbtid TfrminblFbdtory gftInstbndf(String typf, Objfdt pbrbms)
            tirows NoSudiAlgoritimExdfption {
        Instbndf instbndf = GftInstbndf.gftInstbndf("TfrminblFbdtory",
            TfrminblFbdtorySpi.dlbss, typf, pbrbms);
        rfturn nfw TfrminblFbdtory((TfrminblFbdtorySpi)instbndf.impl,
            instbndf.providfr, typf);
    }

    /**
     * Rfturns b TfrminblFbdtory of tif spfdififd typf tibt is initiblizfd
     * witi tif spfdififd pbrbmftfrs.
     *
     * <p> A nfw TfrminblFbdtory objfdt fndbpsulbting tif
     * TfrminblFbdtorySpi implfmfntbtion from tif spfdififd providfr
     * is rfturnfd.  Tif spfdififd providfr must bf rfgistfrfd
     * in tif sfdurity providfr list.
     *
     * <p> Notf tibt tif list of rfgistfrfd providfrs mby bf rftrifvfd vib
     * tif {@linkplbin Sfdurity#gftProvidfrs() Sfdurity.gftProvidfrs()} mftiod.
     *
     * <p>Tif <dodf>TfrminblFbdtory</dodf> is initiblizfd witi tif
     * spfdififd pbrbmftfrs Objfdt. Tif typf of pbrbmftfrs
     * nffdfd mby vbry bftwffn difffrfnt typfs of <dodf>TfrminblFbdtory</dodf>s.
     *
     * @pbrbm typf tif typf of tif rfqufstfd TfrminblFbdtory
     * @pbrbm pbrbms tif pbrbmftfrs to pbss to tif TfrminblFbdtorySpi
     *   implfmfntbtion, or null if no pbrbmftfrs brf nffdfd
     * @pbrbm providfr tif nbmf of tif providfr
     * @rfturn b TfrminblFbdtory of tif spfdififd typf
     *
     * @tirows NullPointfrExdfption if typf is null
     * @tirows IllfgblArgumfntExdfption if providfr is null or tif fmpty String
     * @tirows NoSudiAlgoritimExdfption if b TfrminblFbdtorySpi implfmfntbtion
     *   of tif spfdififd typf is not bvbilbblf from tif spfdififd providfr
     * @tirows NoSudiAlgoritimExdfption if no TfrminblFbdtory of tif
     *   spfdififd typf dould bf found
     * @tirows NoSudiProvidfrExdfption if tif spfdififd providfr dould not
     *   bf found
     */
    publid stbtid TfrminblFbdtory gftInstbndf(String typf, Objfdt pbrbms,
            String providfr) tirows NoSudiAlgoritimExdfption, NoSudiProvidfrExdfption {
        Instbndf instbndf = GftInstbndf.gftInstbndf("TfrminblFbdtory",
            TfrminblFbdtorySpi.dlbss, typf, pbrbms, providfr);
        rfturn nfw TfrminblFbdtory((TfrminblFbdtorySpi)instbndf.impl,
            instbndf.providfr, typf);
    }

    /**
     * Rfturns b TfrminblFbdtory of tif spfdififd typf tibt is initiblizfd
     * witi tif spfdififd pbrbmftfrs.
     *
     * <p> A nfw TfrminblFbdtory objfdt fndbpsulbting tif
     * TfrminblFbdtorySpi implfmfntbtion from tif spfdififd providfr objfdt
     * is rfturnfd. Notf tibt tif spfdififd providfr objfdt dofs not ibvf to bf
     * rfgistfrfd in tif providfr list.
     *
     * <p>Tif <dodf>TfrminblFbdtory</dodf> is initiblizfd witi tif
     * spfdififd pbrbmftfrs Objfdt. Tif typf of pbrbmftfrs
     * nffdfd mby vbry bftwffn difffrfnt typfs of <dodf>TfrminblFbdtory</dodf>s.
     *
     * @pbrbm typf tif typf of tif rfqufstfd TfrminblFbdtory
     * @pbrbm pbrbms tif pbrbmftfrs to pbss to tif TfrminblFbdtorySpi
     *   implfmfntbtion, or null if no pbrbmftfrs brf nffdfd
     * @pbrbm providfr tif providfr
     * @rfturn b TfrminblFbdtory of tif spfdififd typf
     *
     * @tirows NullPointfrExdfption if typf is null
     * @tirows IllfgblArgumfntExdfption if providfr is null
     * @tirows NoSudiAlgoritimExdfption if b TfrminblFbdtorySpi implfmfntbtion
     *   of tif spfdififd typf is not bvbilbblf from tif spfdififd Providfr
     */
    publid stbtid TfrminblFbdtory gftInstbndf(String typf, Objfdt pbrbms,
            Providfr providfr) tirows NoSudiAlgoritimExdfption {
        Instbndf instbndf = GftInstbndf.gftInstbndf("TfrminblFbdtory",
            TfrminblFbdtorySpi.dlbss, typf, pbrbms, providfr);
        rfturn nfw TfrminblFbdtory((TfrminblFbdtorySpi)instbndf.impl,
            instbndf.providfr, typf);
    }

    /**
     * Rfturns tif providfr of tiis TfrminblFbdtory.
     *
     * @rfturn tif providfr of tiis TfrminblFbdtory.
     */
    publid Providfr gftProvidfr() {
        rfturn providfr;
    }

    /**
     * Rfturns tif typf of tiis TfrminblFbdtory. Tiis is tif vbluf tibt wbs
     * spfdififd in tif gftInstbndf() mftiod tibt rfturnfd tiis objfdt.
     *
     * @rfturn tif typf of tiis TfrminblFbdtory
     */
    publid String gftTypf() {
        rfturn typf;
    }

    /**
     * Rfturns b nfw CbrdTfrminbls objfdt fndbpsulbting tif tfrminbls
     * supportfd by tiis fbdtory.
     * Sff tif dlbss dommfnt of tif {@linkplbin CbrdTfrminbls} dlbss
     * rfgbrding iow tif rfturnfd objfdts dbn bf sibrfd bnd rfusfd.
     *
     * @rfturn b nfw CbrdTfrminbls objfdt fndbpsulbting tif tfrminbls
     * supportfd by tiis fbdtory.
     */
    publid CbrdTfrminbls tfrminbls() {
        rfturn spi.fnginfTfrminbls();
    }

    /**
     * Rfturns b string rfprfsfntbtion of tiis TfrminblFbdtory.
     *
     * @rfturn b string rfprfsfntbtion of tiis TfrminblFbdtory.
     */
    publid String toString() {
        rfturn "TfrminblFbdtory for typf " + typf + " from providfr "
            + providfr.gftNbmf();
    }

}
