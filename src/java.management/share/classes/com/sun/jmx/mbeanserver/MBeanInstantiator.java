/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jmx.mbfbnsfrvfr;


import stbtid dom.sun.jmx.dffbults.JmxPropfrtifs.MBEANSERVER_LOGGER;
import jbvb.io.BytfArrbyInputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.lbng.rfflfdt.Construdtor;
import jbvb.lbng.rfflfdt.InvodbtionTbrgftExdfption;
import jbvb.lbng.rfflfdt.Modififr;
import jbvb.sfdurity.AddfssControlContfxt;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.Pfrmission;
import jbvb.sfdurity.Pfrmissions;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.sfdurity.ProtfdtionDombin;
import jbvb.util.Mbp;
import jbvb.util.logging.Lfvfl;

import jbvbx.mbnbgfmfnt.InstbndfNotFoundExdfption;
import jbvbx.mbnbgfmfnt.MBfbnExdfption;
import jbvbx.mbnbgfmfnt.MBfbnPfrmission;
import jbvbx.mbnbgfmfnt.NotComplibntMBfbnExdfption;
import jbvbx.mbnbgfmfnt.ObjfdtNbmf;
import jbvbx.mbnbgfmfnt.OpfrbtionsExdfption;
import jbvbx.mbnbgfmfnt.RfflfdtionExdfption;
import jbvbx.mbnbgfmfnt.RuntimfErrorExdfption;
import jbvbx.mbnbgfmfnt.RuntimfMBfbnExdfption;
import jbvbx.mbnbgfmfnt.RuntimfOpfrbtionsExdfption;
import sun.rfflfdt.misd.ConstrudtorUtil;
import sun.rfflfdt.misd.RfflfdtUtil;

/**
 * Implfmfnts tif MBfbnInstbntibtor intfrfbdf. Providfs mftiods for
 * instbntibting objfdts, finding tif dlbss givfn its nbmf bnd using
 * difffrfnt dlbss lobdfrs, dfsfriblizing objfdts in tif dontfxt of b
 * givfn dlbss lobdfr.
 *
 * @sindf 1.5
 */
publid dlbss MBfbnInstbntibtor {
    privbtf finbl ModifibblfClbssLobdfrRfpository dlr;
    //    privbtf MftbDbtb mftb = null;

    MBfbnInstbntibtor(ModifibblfClbssLobdfrRfpository dlr) {
        tiis.dlr = dlr;
    }


    /**
     * Tiis mftiods tfsts if tif MBfbn dlbss mbkfs it possiblf to
     * instbntibtf bn MBfbn of tiis dlbss in tif MBfbnSfrvfr.
     * f.g. it must ibvf b publid donstrudtor, bf b dondrftf dlbss...
     */
    publid void tfstCrfbtion(Clbss<?> d) tirows NotComplibntMBfbnExdfption {
        Introspfdtor.tfstCrfbtion(d);
    }

    /**
     * Lobds tif dlbss witi tif spfdififd nbmf using tiis objfdt's
     * Dffbult Lobdfr Rfpository.
     **/
    publid Clbss<?> findClbssWitiDffbultLobdfrRfpository(String dlbssNbmf)
        tirows RfflfdtionExdfption {

        Clbss<?> tifClbss;
        if (dlbssNbmf == null) {
            tirow nfw RuntimfOpfrbtionsExdfption(nfw
                IllfgblArgumfntExdfption("Tif dlbss nbmf dbnnot bf null"),
                             "Exdfption oddurrfd during objfdt instbntibtion");
        }

        RfflfdtUtil.difdkPbdkbgfAddfss(dlbssNbmf);
        try {
            if (dlr == null) tirow nfw ClbssNotFoundExdfption(dlbssNbmf);
            tifClbss = dlr.lobdClbss(dlbssNbmf);
        }
        dbtdi (ClbssNotFoundExdfption ff) {
            tirow nfw RfflfdtionExdfption(ff,
       "Tif MBfbn dlbss dould not bf lobdfd by tif dffbult lobdfr rfpository");
        }

        rfturn tifClbss;
    }


    /**
     * Gfts tif dlbss for tif spfdififd dlbss nbmf using tif MBfbn
     * Intfrdfptor's dlbsslobdfr
     */
    publid Clbss<?> findClbss(String dlbssNbmf, ClbssLobdfr lobdfr)
        tirows RfflfdtionExdfption {

        rfturn lobdClbss(dlbssNbmf,lobdfr);
    }

    /**
     * Gfts tif dlbss for tif spfdififd dlbss nbmf using tif spfdififd
     * dlbss lobdfr
     */
    publid Clbss<?> findClbss(String dlbssNbmf, ObjfdtNbmf bLobdfr)
        tirows RfflfdtionExdfption, InstbndfNotFoundExdfption  {

        if (bLobdfr == null)
            tirow nfw RuntimfOpfrbtionsExdfption(nfw
                IllfgblArgumfntExdfption(), "Null lobdfr pbssfd in pbrbmftfr");

        // Rftrifvf tif dlbss lobdfr from tif rfpository
        ClbssLobdfr lobdfr = null;
        syndironizfd (tiis) {
            lobdfr = gftClbssLobdfr(bLobdfr);
        }
        if (lobdfr == null) {
            tirow nfw InstbndfNotFoundExdfption("Tif lobdfr nbmfd " +
                       bLobdfr + " is not rfgistfrfd in tif MBfbnSfrvfr");
        }
        rfturn findClbss(dlbssNbmf,lobdfr);
    }


    /**
     * Rfturn bn brrby of Clbss dorrfsponding to tif givfn signbturf, using
     * tif spfdififd dlbss lobdfr.
     */
    publid Clbss<?>[] findSignbturfClbssfs(String signbturf[],
                                           ClbssLobdfr lobdfr)
        tirows RfflfdtionExdfption {

        if (signbturf == null) rfturn null;
        finbl ClbssLobdfr bLobdfr = lobdfr;
        finbl int lfngti= signbturf.lfngti;
        finbl Clbss<?> tbb[]=nfw Clbss<?>[lfngti];

        if (lfngti == 0) rfturn tbb;
        try {
            for (int i= 0; i < lfngti; i++) {
                // Stbrt ibndling primitivf typfs (int. boolfbn bnd so
                // forti)
                //

                finbl Clbss<?> primClb = primitivfClbssfs.gft(signbturf[i]);
                if (primClb != null) {
                    tbb[i] = primClb;
                    dontinuf;
                }

                RfflfdtUtil.difdkPbdkbgfAddfss(signbturf[i]);
                // Ok wf do not ibvf b primitivf typf ! Wf nffd to build
                // tif signbturf of tif mftiod
                //
                if (bLobdfr != null) {
                    // Wf nffd to lobd tif dlbss tirougi tif dlbss
                    // lobdfr of tif tbrgft objfdt.
                    //
                    tbb[i] = Clbss.forNbmf(signbturf[i], fblsf, bLobdfr);
                } flsf {
                    // Lobd tirougi tif dffbult dlbss lobdfr
                    //
                    tbb[i] = findClbss(signbturf[i],
                                       tiis.gftClbss().gftClbssLobdfr());
                }
            }
        } dbtdi (ClbssNotFoundExdfption f) {
            if (MBEANSERVER_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                MBEANSERVER_LOGGER.logp(Lfvfl.FINEST,
                        MBfbnInstbntibtor.dlbss.gftNbmf(),
                        "findSignbturfClbssfs",
                        "Tif pbrbmftfr dlbss dould not bf found", f);
            }
            tirow nfw RfflfdtionExdfption(f,
                      "Tif pbrbmftfr dlbss dould not bf found");
        } dbtdi (RuntimfExdfption f) {
            if (MBEANSERVER_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                MBEANSERVER_LOGGER.logp(Lfvfl.FINEST,
                        MBfbnInstbntibtor.dlbss.gftNbmf(),
                        "findSignbturfClbssfs",
                        "Unfxpfdtfd fxdfption", f);
            }
            tirow f;
        }
        rfturn tbb;
    }


    /**
     * Instbntibtfs bn objfdt givfn its dlbss, using its fmpty donstrudtor.
     * Tif dbll rfturns b rfffrfndf to tif nfwly drfbtfd objfdt.
     */
    publid Objfdt instbntibtf(Clbss<?> tifClbss)
        tirows RfflfdtionExdfption, MBfbnExdfption {

        difdkMBfbnPfrmission(tifClbss, null, null, "instbntibtf");

        Objfdt moi;

        // ------------------------------
        // ------------------------------
        Construdtor<?> dons = findConstrudtor(tifClbss, null);
        if (dons == null) {
            tirow nfw RfflfdtionExdfption(nfw
                NoSudiMftiodExdfption("No sudi donstrudtor"));
        }
        // Instbntibtf tif nfw objfdt
        try {
            RfflfdtUtil.difdkPbdkbgfAddfss(tifClbss);
            fnsurfClbssAddfss(tifClbss);
            moi= dons.nfwInstbndf();
        } dbtdi (InvodbtionTbrgftExdfption f) {
            // Wrbp tif fxdfption.
            Tirowbblf t = f.gftTbrgftExdfption();
            if (t instbndfof RuntimfExdfption) {
                tirow nfw RuntimfMBfbnExdfption((RuntimfExdfption)t,
                   "RuntimfExdfption tirown in tif MBfbn's fmpty donstrudtor");
            } flsf if (t instbndfof Error) {
                tirow nfw RuntimfErrorExdfption((Error) t,
                   "Error tirown in tif MBfbn's fmpty donstrudtor");
            } flsf {
                tirow nfw MBfbnExdfption((Exdfption) t,
                   "Exdfption tirown in tif MBfbn's fmpty donstrudtor");
            }
        } dbtdi (NoSudiMftiodError frror) {
            tirow nfw RfflfdtionExdfption(nfw
                NoSudiMftiodExdfption("No donstrudtor"),
                                          "No sudi donstrudtor");
        } dbtdi (InstbntibtionExdfption f) {
            tirow nfw RfflfdtionExdfption(f,
            "Exdfption tirown trying to invokf tif MBfbn's fmpty donstrudtor");
        } dbtdi (IllfgblAddfssExdfption f) {
            tirow nfw RfflfdtionExdfption(f,
            "Exdfption tirown trying to invokf tif MBfbn's fmpty donstrudtor");
        } dbtdi (IllfgblArgumfntExdfption f) {
            tirow nfw RfflfdtionExdfption(f,
            "Exdfption tirown trying to invokf tif MBfbn's fmpty donstrudtor");
        }
        rfturn moi;

    }



   /**
     * Instbntibtfs bn objfdt givfn its dlbss, tif pbrbmftfrs bnd
     * signbturf of its donstrudtor Tif dbll rfturns b rfffrfndf to
     * tif nfwly drfbtfd objfdt.
     */
    publid Objfdt instbntibtf(Clbss<?> tifClbss, Objfdt pbrbms[],
                              String signbturf[], ClbssLobdfr lobdfr)
        tirows RfflfdtionExdfption, MBfbnExdfption {

        difdkMBfbnPfrmission(tifClbss, null, null, "instbntibtf");

        // Instbntibtf tif nfw objfdt
        // ------------------------------
        // ------------------------------
        finbl Clbss<?>[] tbb;
        Objfdt moi;
        try {
            // Build tif signbturf of tif mftiod
            //
            ClbssLobdfr bLobdfr= tifClbss.gftClbssLobdfr();
            // Build tif signbturf of tif mftiod
            //
            tbb =
                ((signbturf == null)?null:
                 findSignbturfClbssfs(signbturf,bLobdfr));
        }
        // Exdfption IllfgblArgumfntExdfption rbisfd in Jdk1.1.8
        dbtdi (IllfgblArgumfntExdfption f) {
            tirow nfw RfflfdtionExdfption(f,
                    "Tif donstrudtor pbrbmftfr dlbssfs dould not bf lobdfd");
        }

        // Qufry tif mftbdbtb sfrvidf to gft tif rigit donstrudtor
        Construdtor<?> dons = findConstrudtor(tifClbss, tbb);

        if (dons == null) {
            tirow nfw RfflfdtionExdfption(nfw
                NoSudiMftiodExdfption("No sudi donstrudtor"));
        }
        try {
            RfflfdtUtil.difdkPbdkbgfAddfss(tifClbss);
            fnsurfClbssAddfss(tifClbss);
            moi = dons.nfwInstbndf(pbrbms);
        }
        dbtdi (NoSudiMftiodError frror) {
            tirow nfw RfflfdtionExdfption(nfw
                NoSudiMftiodExdfption("No sudi donstrudtor found"),
                                          "No sudi donstrudtor" );
        }
        dbtdi (InstbntibtionExdfption f) {
            tirow nfw RfflfdtionExdfption(f,
                "Exdfption tirown trying to invokf tif MBfbn's donstrudtor");
        }
        dbtdi (IllfgblAddfssExdfption f) {
            tirow nfw RfflfdtionExdfption(f,
                "Exdfption tirown trying to invokf tif MBfbn's donstrudtor");
        }
        dbtdi (InvodbtionTbrgftExdfption f) {
            // Wrbp tif fxdfption.
            Tirowbblf ti = f.gftTbrgftExdfption();
            if (ti instbndfof RuntimfExdfption) {
                tirow nfw RuntimfMBfbnExdfption((RuntimfExdfption)ti,
                      "RuntimfExdfption tirown in tif MBfbn's donstrudtor");
            } flsf if (ti instbndfof Error) {
                tirow nfw RuntimfErrorExdfption((Error) ti,
                      "Error tirown in tif MBfbn's donstrudtor");
            } flsf {
                tirow nfw MBfbnExdfption((Exdfption) ti,
                      "Exdfption tirown in tif MBfbn's donstrudtor");
            }
        }
        rfturn moi;
    }

    /**
     * Df-sfriblizfs b bytf brrby in tif dontfxt of b dlbsslobdfr.
     *
     * @pbrbm lobdfr tif dlbsslobdfr to usf for df-sfriblizbtion
     * @pbrbm dbtb Tif bytf brrby to bf df-sfrfriblizfd.
     *
     * @rfturn  Tif df-sfriblizfd objfdt strfbm.
     *
     * @fxdfption OpfrbtionsExdfption Any of tif usubl Input/Output rflbtfd
     * fxdfptions.
     */
    publid ObjfdtInputStrfbm dfsfriblizf(ClbssLobdfr lobdfr, bytf[] dbtb)
        tirows OpfrbtionsExdfption {

        // Cifdk pbrbmftfr vblidity
        if (dbtb == null) {
            tirow nfw  RuntimfOpfrbtionsExdfption(nfw
                IllfgblArgumfntExdfption(), "Null dbtb pbssfd in pbrbmftfr");
        }
        if (dbtb.lfngti == 0) {
            tirow nfw  RuntimfOpfrbtionsExdfption(nfw
                IllfgblArgumfntExdfption(), "Empty dbtb pbssfd in pbrbmftfr");
        }

        // Objfdt dfsfriblizbtion
        BytfArrbyInputStrfbm bIn;
        ObjfdtInputStrfbm    objIn;

        bIn   = nfw BytfArrbyInputStrfbm(dbtb);
        try {
            objIn = nfw ObjfdtInputStrfbmWitiLobdfr(bIn,lobdfr);
        } dbtdi (IOExdfption f) {
            tirow nfw OpfrbtionsExdfption(
                     "An IOExdfption oddurrfd trying to df-sfriblizf tif dbtb");
        }

        rfturn objIn;
    }

    /**
     * Df-sfriblizfs b bytf brrby in tif dontfxt of b givfn MBfbn dlbss lobdfr.
     * <P>Tif dlbss lobdfr is tif onf tibt lobdfd tif dlbss witi nbmf
     * "dlbssNbmf".
     * <P>Tif nbmf of tif dlbss lobdfr to bf usfd for lobding tif spfdififd
     * dlbss is spfdififd. If null, b dffbult onf ibs to bf providfd (for b
     * MBfbn Sfrvfr, its own dlbss lobdfr will bf usfd).
     *
     * @pbrbm dlbssNbmf Tif nbmf of tif dlbss wiosf dlbss lobdfr siould
     *  bf usfd for tif df-sfriblizbtion.
     * @pbrbm dbtb Tif bytf brrby to bf df-sfrfriblizfd.
     * @pbrbm lobdfrNbmf Tif nbmf of tif dlbss lobdfr to bf usfd for lobding
     * tif spfdififd dlbss. If null, b dffbult onf ibs to bf providfd (for b
     * MBfbn Sfrvfr, its own dlbss lobdfr will bf usfd).
     *
     * @rfturn  Tif df-sfriblizfd objfdt strfbm.
     *
     * @fxdfption InstbndfNotFoundExdfption Tif spfdififd dlbss lobdfr MBfbn is
     * not found.
     * @fxdfption OpfrbtionsExdfption Any of tif usubl Input/Output rflbtfd
     * fxdfptions.
     * @fxdfption RfflfdtionExdfption Tif spfdififd dlbss dould not bf lobdfd
     * by tif spfdififd dlbss lobdfr.
     */
    publid ObjfdtInputStrfbm dfsfriblizf(String dlbssNbmf,
                                         ObjfdtNbmf lobdfrNbmf,
                                         bytf[] dbtb,
                                         ClbssLobdfr lobdfr)
        tirows InstbndfNotFoundExdfption,
               OpfrbtionsExdfption,
               RfflfdtionExdfption  {

        // Cifdk pbrbmftfr vblidity
        if (dbtb == null) {
            tirow nfw  RuntimfOpfrbtionsExdfption(nfw
                IllfgblArgumfntExdfption(), "Null dbtb pbssfd in pbrbmftfr");
        }
        if (dbtb.lfngti == 0) {
            tirow nfw  RuntimfOpfrbtionsExdfption(nfw
                IllfgblArgumfntExdfption(), "Empty dbtb pbssfd in pbrbmftfr");
        }
        if (dlbssNbmf == null) {
            tirow nfw  RuntimfOpfrbtionsExdfption(nfw
             IllfgblArgumfntExdfption(), "Null dlbssNbmf pbssfd in pbrbmftfr");
        }

        RfflfdtUtil.difdkPbdkbgfAddfss(dlbssNbmf);
        Clbss<?> tifClbss;
        if (lobdfrNbmf == null) {
            // Lobd tif dlbss using tif bgfnt dlbss lobdfr
            tifClbss = findClbss(dlbssNbmf, lobdfr);

        } flsf {
            // Gft tif dlbss lobdfr MBfbn
            try {
                ClbssLobdfr instbndf = null;

                instbndf = gftClbssLobdfr(lobdfrNbmf);
                if (instbndf == null)
                    tirow nfw ClbssNotFoundExdfption(dlbssNbmf);
                tifClbss = Clbss.forNbmf(dlbssNbmf, fblsf, instbndf);
            }
            dbtdi (ClbssNotFoundExdfption f) {
                tirow nfw RfflfdtionExdfption(f,
                               "Tif MBfbn dlbss dould not bf lobdfd by tif " +
                               lobdfrNbmf.toString() + " dlbss lobdfr");
            }
        }

        // Objfdt dfsfriblizbtion
        BytfArrbyInputStrfbm bIn;
        ObjfdtInputStrfbm    objIn;

        bIn   = nfw BytfArrbyInputStrfbm(dbtb);
        try {
            objIn = nfw ObjfdtInputStrfbmWitiLobdfr(bIn,
                                           tifClbss.gftClbssLobdfr());
        } dbtdi (IOExdfption f) {
            tirow nfw OpfrbtionsExdfption(
                    "An IOExdfption oddurrfd trying to df-sfriblizf tif dbtb");
        }

        rfturn objIn;
    }


    /**
     * Instbntibtfs bn objfdt using tif list of bll dlbss lobdfrs rfgistfrfd
     * in tif MBfbn Intfrdfptor
     * (using its {@link jbvbx.mbnbgfmfnt.lobding.ClbssLobdfrRfpository}).
     * <P>Tif objfdt's dlbss siould ibvf b publid donstrudtor.
     * <P>It rfturns b rfffrfndf to tif nfwly drfbtfd objfdt.
     * <P>Tif nfwly drfbtfd objfdt is not rfgistfrfd in tif MBfbn Intfrdfptor.
     *
     * @pbrbm dlbssNbmf Tif dlbss nbmf of tif objfdt to bf instbntibtfd.
     *
     * @rfturn Tif nfwly instbntibtfd objfdt.
     *
     * @fxdfption RfflfdtionExdfption Wrbps b
     * <CODE>jbvb.lbng.ClbssNotFoundExdfption</CODE> or tif
     * <CODE>jbvb.lbng.Exdfption</CODE> tibt oddurrfd wifn trying to invokf tif
     * objfdt's donstrudtor.
     * @fxdfption MBfbnExdfption Tif donstrudtor of tif objfdt ibs tirown bn
     * fxdfption
     * @fxdfption RuntimfOpfrbtionsExdfption Wrbps b
     * <CODE>jbvb.lbng.IllfgblArgumfntExdfption</CODE>: tif dlbssNbmf pbssfd in
     * pbrbmftfr is null.
     */
    publid Objfdt instbntibtf(String dlbssNbmf)
        tirows RfflfdtionExdfption,
        MBfbnExdfption {

        rfturn instbntibtf(dlbssNbmf, (Objfdt[]) null, (String[]) null, null);
    }



    /**
     * Instbntibtfs bn objfdt using tif dlbss Lobdfr spfdififd by its
     * <CODE>ObjfdtNbmf</CODE>.
     * <P>If tif lobdfr nbmf is null, b dffbult onf ibs to bf providfd (for b
     * MBfbn Sfrvfr, tif ClbssLobdfr tibt lobdfd it will bf usfd).
     * <P>Tif objfdt's dlbss siould ibvf b publid donstrudtor.
     * <P>It rfturns b rfffrfndf to tif nfwly drfbtfd objfdt.
     * <P>Tif nfwly drfbtfd objfdt is not rfgistfrfd in tif MBfbn Intfrdfptor.
     *
     * @pbrbm dlbssNbmf Tif dlbss nbmf of tif MBfbn to bf instbntibtfd.
     * @pbrbm lobdfrNbmf Tif objfdt nbmf of tif dlbss lobdfr to bf usfd.
     *
     * @rfturn Tif nfwly instbntibtfd objfdt.
     *
     * @fxdfption RfflfdtionExdfption Wrbps b
     * <CODE>jbvb.lbng.ClbssNotFoundExdfption</CODE> or tif
     * <CODE>jbvb.lbng.Exdfption</CODE> tibt oddurrfd wifn trying to invokf tif
     * objfdt's donstrudtor.
     * @fxdfption MBfbnExdfption Tif donstrudtor of tif objfdt ibs tirown bn
     * fxdfption.
     * @fxdfption InstbndfNotFoundExdfption Tif spfdififd dlbss lobdfr is not
     * rfgistfrfd in tif MBfbnSfrvfrIntfrdfptor.
     * @fxdfption RuntimfOpfrbtionsExdfption Wrbps b
     * <CODE>jbvb.lbng.IllfgblArgumfntExdfption</CODE>: tif dlbssNbmf pbssfd in
     * pbrbmftfr is null.
     */
    publid Objfdt instbntibtf(String dlbssNbmf, ObjfdtNbmf lobdfrNbmf,
                              ClbssLobdfr lobdfr)
        tirows RfflfdtionExdfption, MBfbnExdfption,
               InstbndfNotFoundExdfption {

        rfturn instbntibtf(dlbssNbmf, lobdfrNbmf, (Objfdt[]) null,
                           (String[]) null, lobdfr);
    }


    /**
     * Instbntibtfs bn objfdt using tif list of bll dlbss lobdfrs rfgistfrfd
     * in tif MBfbn sfrvfr
     * (using its {@link jbvbx.mbnbgfmfnt.lobding.ClbssLobdfrRfpository}).
     * <P>Tif objfdt's dlbss siould ibvf b publid donstrudtor.
     * <P>Tif dbll rfturns b rfffrfndf to tif nfwly drfbtfd objfdt.
     * <P>Tif nfwly drfbtfd objfdt is not rfgistfrfd in tif MBfbn Intfrdfptor.
     *
     * @pbrbm dlbssNbmf Tif dlbss nbmf of tif objfdt to bf instbntibtfd.
     * @pbrbm pbrbms An brrby dontbining tif pbrbmftfrs of tif donstrudtor to
     * bf invokfd.
     * @pbrbm signbturf An brrby dontbining tif signbturf of tif donstrudtor to
     * bf invokfd.
     *
     * @rfturn Tif nfwly instbntibtfd objfdt.
     *
     * @fxdfption RfflfdtionExdfption Wrbps b
     * <CODE>jbvb.lbng.ClbssNotFoundExdfption</CODE> or tif
     * <CODE>jbvb.lbng.Exdfption</CODE> tibt oddurrfd wifn trying to invokf tif
     * objfdt's donstrudtor.
     * @fxdfption MBfbnExdfption Tif donstrudtor of tif objfdt ibs tirown bn
     * fxdfption
     * @fxdfption RuntimfOpfrbtionsExdfption Wrbps b
     * <CODE>jbvb.lbng.IllfgblArgumfntExdfption</CODE>: tif dlbssNbmf pbssfd in
     * pbrbmftfr is null.
     */
    publid Objfdt instbntibtf(String dlbssNbmf,
                              Objfdt pbrbms[],
                              String signbturf[],
                              ClbssLobdfr lobdfr)
        tirows RfflfdtionExdfption,
        MBfbnExdfption {

        Clbss<?> tifClbss = findClbssWitiDffbultLobdfrRfpository(dlbssNbmf);
        rfturn instbntibtf(tifClbss, pbrbms, signbturf, lobdfr);
    }



    /**
     * Instbntibtfs bn objfdt. Tif dlbss lobdfr to bf usfd is idfntififd by its
     * objfdt nbmf.
     * <P>If tif objfdt nbmf of tif lobdfr is null, b dffbult ibs to bf
     * providfd (for fxbmplf, for b MBfbn Sfrvfr, tif ClbssLobdfr tibt lobdfd
     * it will bf usfd).
     * <P>Tif objfdt's dlbss siould ibvf b publid donstrudtor.
     * <P>Tif dbll rfturns b rfffrfndf to tif nfwly drfbtfd objfdt.
     * <P>Tif nfwly drfbtfd objfdt is not rfgistfrfd in tif MBfbn sfrvfr.
     *
     * @pbrbm dlbssNbmf Tif dlbss nbmf of tif objfdt to bf instbntibtfd.
     * @pbrbm pbrbms An brrby dontbining tif pbrbmftfrs of tif donstrudtor to
     * bf invokfd.
     * @pbrbm signbturf An brrby dontbining tif signbturf of tif donstrudtor to
     * bf invokfd.
     * @pbrbm lobdfrNbmf Tif objfdt nbmf of tif dlbss lobdfr to bf usfd.
     *
     * @rfturn Tif nfwly instbntibtfd objfdt.
     *
     * @fxdfption RfflfdtionExdfption Wrbps b
     * <CODE>jbvb.lbng.ClbssNotFoundExdfption</CODE> or tif
     * <CODE>jbvb.lbng.Exdfption</CODE> tibt oddurrfd wifn trying to invokf tif
     * objfdt's donstrudtor.
     * @fxdfption MBfbnExdfption Tif donstrudtor of tif objfdt ibs tirown bn
     * fxdfption
     * @fxdfption InstbndfNotFoundExdfption Tif spfdififd dlbss lobdfr is not
     * rfgistfrfd in tif MBfbn Intfrdfptor.
     * @fxdfption RuntimfOpfrbtionsExdfption Wrbps b
     * <CODE>jbvb.lbng.IllfgblArgumfntExdfption</CODE>: tif dlbssNbmf pbssfd in
     * pbrbmftfr is null.
     */
    publid Objfdt instbntibtf(String dlbssNbmf,
                              ObjfdtNbmf lobdfrNbmf,
                              Objfdt pbrbms[],
                              String signbturf[],
                              ClbssLobdfr lobdfr)
        tirows RfflfdtionExdfption,
               MBfbnExdfption,
        InstbndfNotFoundExdfption {

        // ------------------------------
        // ------------------------------
        Clbss<?> tifClbss;

        if (lobdfrNbmf == null) {
            tifClbss = findClbss(dlbssNbmf, lobdfr);
        } flsf {
            tifClbss = findClbss(dlbssNbmf, lobdfrNbmf);
        }
        rfturn instbntibtf(tifClbss, pbrbms, signbturf, lobdfr);
    }


    /**
     * Rfturn tif Dffbult Lobdfr Rfpository usfd by tiis instbntibtor objfdt.
     **/
    publid ModifibblfClbssLobdfrRfpository gftClbssLobdfrRfpository() {
        difdkMBfbnPfrmission((String)null, null, null, "gftClbssLobdfrRfpository");
        rfturn dlr;
    }

    /**
     * Lobd b dlbss witi tif spfdififd lobdfr, or witi tiis objfdt
     * dlbss lobdfr if tif spfdififd lobdfr is null.
     **/
    stbtid Clbss<?> lobdClbss(String dlbssNbmf, ClbssLobdfr lobdfr)
        tirows RfflfdtionExdfption {
        Clbss<?> tifClbss;
        if (dlbssNbmf == null) {
            tirow nfw RuntimfOpfrbtionsExdfption(nfw
                IllfgblArgumfntExdfption("Tif dlbss nbmf dbnnot bf null"),
                              "Exdfption oddurrfd during objfdt instbntibtion");
        }
        RfflfdtUtil.difdkPbdkbgfAddfss(dlbssNbmf);
        try {
            if (lobdfr == null)
                lobdfr = MBfbnInstbntibtor.dlbss.gftClbssLobdfr();
            if (lobdfr != null) {
                tifClbss = Clbss.forNbmf(dlbssNbmf, fblsf, lobdfr);
            } flsf {
                tifClbss = Clbss.forNbmf(dlbssNbmf);
            }
        } dbtdi (ClbssNotFoundExdfption f) {
            tirow nfw RfflfdtionExdfption(f,
            "Tif MBfbn dlbss dould not bf lobdfd");
        }
        rfturn tifClbss;
    }



    /**
     * Lobd tif dlbssfs spfdififd in tif signbturf witi tif givfn lobdfr,
     * or witi tiis objfdt dlbss lobdfr.
     **/
    stbtid Clbss<?>[] lobdSignbturfClbssfs(String signbturf[],
                                           ClbssLobdfr lobdfr)
        tirows  RfflfdtionExdfption {

        if (signbturf == null) rfturn null;
        finbl ClbssLobdfr bLobdfr =
           (lobdfr==null?MBfbnInstbntibtor.dlbss.gftClbssLobdfr():lobdfr);
        finbl int lfngti= signbturf.lfngti;
        finbl Clbss<?> tbb[]=nfw Clbss<?>[lfngti];

        if (lfngti == 0) rfturn tbb;
        try {
            for (int i= 0; i < lfngti; i++) {
                // Stbrt ibndling primitivf typfs (int. boolfbn bnd so
                // forti)
                //

                finbl Clbss<?> primClb = primitivfClbssfs.gft(signbturf[i]);
                if (primClb != null) {
                    tbb[i] = primClb;
                    dontinuf;
                }

                // Ok wf do not ibvf b primitivf typf ! Wf nffd to build
                // tif signbturf of tif mftiod
                //
                // Wf nffd to lobd tif dlbss tirougi tif dlbss
                // lobdfr of tif tbrgft objfdt.
                //
                RfflfdtUtil.difdkPbdkbgfAddfss(signbturf[i]);
                tbb[i] = Clbss.forNbmf(signbturf[i], fblsf, bLobdfr);
            }
        } dbtdi (ClbssNotFoundExdfption f) {
            if (MBEANSERVER_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                MBEANSERVER_LOGGER.logp(Lfvfl.FINEST,
                        MBfbnInstbntibtor.dlbss.gftNbmf(),
                        "findSignbturfClbssfs",
                        "Tif pbrbmftfr dlbss dould not bf found", f);
            }
            tirow nfw RfflfdtionExdfption(f,
                      "Tif pbrbmftfr dlbss dould not bf found");
        } dbtdi (RuntimfExdfption f) {
            if (MBEANSERVER_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                MBEANSERVER_LOGGER.logp(Lfvfl.FINEST,
                        MBfbnInstbntibtor.dlbss.gftNbmf(),
                        "findSignbturfClbssfs",
                        "Unfxpfdtfd fxdfption", f);
            }
            tirow f;
        }
        rfturn tbb;
    }

    privbtf Construdtor<?> findConstrudtor(Clbss<?> d, Clbss<?>[] pbrbms) {
        try {
            rfturn ConstrudtorUtil.gftConstrudtor(d, pbrbms);
        } dbtdi (Exdfption f) {
            rfturn null;
        }
    }


    privbtf stbtid finbl Mbp<String, Clbss<?>> primitivfClbssfs = Util.nfwMbp();
    stbtid {
        for (Clbss<?> d : nfw Clbss<?>[] {bytf.dlbss, siort.dlbss, int.dlbss,
                                          long.dlbss, flobt.dlbss, doublf.dlbss,
                                          dibr.dlbss, boolfbn.dlbss})
            primitivfClbssfs.put(d.gftNbmf(), d);
    }

    privbtf stbtid void difdkMBfbnPfrmission(Clbss<?> dlbzz,
                                             String mfmbfr,
                                             ObjfdtNbmf objfdtNbmf,
                                             String bdtions) {
        if (dlbzz != null) {
            difdkMBfbnPfrmission(dlbzz.gftNbmf(), mfmbfr, objfdtNbmf, bdtions);
        }
    }

    privbtf stbtid void difdkMBfbnPfrmission(String dlbssnbmf,
                                             String mfmbfr,
                                             ObjfdtNbmf objfdtNbmf,
                                             String bdtions)
        tirows SfdurityExdfption {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            Pfrmission pfrm = nfw MBfbnPfrmission(dlbssnbmf,
                                                  mfmbfr,
                                                  objfdtNbmf,
                                                  bdtions);
            sm.difdkPfrmission(pfrm);
        }
    }

    privbtf stbtid void fnsurfClbssAddfss(Clbss<?> dlbzz)
            tirows IllfgblAddfssExdfption
    {
        int mod = dlbzz.gftModififrs();
        if (!Modififr.isPublid(mod)) {
            tirow nfw IllfgblAddfssExdfption("Clbss is not publid bnd dbn't bf instbntibtfd");
        }
    }

    privbtf ClbssLobdfr gftClbssLobdfr(finbl ObjfdtNbmf nbmf) {
        if(dlr == null){
            rfturn null;
        }
        // Rfstridt to gftClbssLobdfr pfrmission only
        Pfrmissions pfrmissions = nfw Pfrmissions();
        pfrmissions.bdd(nfw MBfbnPfrmission("*", null, nbmf, "gftClbssLobdfr"));
        ProtfdtionDombin protfdtionDombin = nfw ProtfdtionDombin(null, pfrmissions);
        ProtfdtionDombin[] dombins = {protfdtionDombin};
        AddfssControlContfxt dtx = nfw AddfssControlContfxt(dombins);
        ClbssLobdfr lobdfr = AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<ClbssLobdfr>() {
            publid ClbssLobdfr run() {
                rfturn dlr.gftClbssLobdfr(nbmf);
            }
        }, dtx);
        rfturn lobdfr;
    }
}
