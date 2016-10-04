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

pbdkbgf jbvb.sfdurity;

import jbvb.util.*;

import jbvb.sfdurity.Providfr.Sfrvidf;
import jbvb.sfdurity.spfd.KfySpfd;
import jbvb.sfdurity.spfd.InvblidKfySpfdExdfption;

import sun.sfdurity.util.Dfbug;
import sun.sfdurity.jdb.*;
import sun.sfdurity.jdb.GftInstbndf.Instbndf;

/**
 * Kfy fbdtorifs brf usfd to donvfrt <I>kfys</I> (opbquf
 * dryptogrbpiid kfys of typf {@dodf Kfy}) into <I>kfy spfdifidbtions</I>
 * (trbnspbrfnt rfprfsfntbtions of tif undfrlying kfy mbtfribl), bnd vidf
 * vfrsb.
 *
 * <P> Kfy fbdtorifs brf bi-dirfdtionbl. Tibt is, tify bllow you to build bn
 * opbquf kfy objfdt from b givfn kfy spfdifidbtion (kfy mbtfribl), or to
 * rftrifvf tif undfrlying kfy mbtfribl of b kfy objfdt in b suitbblf formbt.
 *
 * <P> Multiplf dompbtiblf kfy spfdifidbtions mby fxist for tif sbmf kfy.
 * For fxbmplf, b DSA publid kfy mby bf spfdififd using
 * {@dodf DSAPublidKfySpfd} or
 * {@dodf X509EndodfdKfySpfd}. A kfy fbdtory dbn bf usfd to trbnslbtf
 * bftwffn dompbtiblf kfy spfdifidbtions.
 *
 * <P> Tif following is bn fxbmplf of iow to usf b kfy fbdtory in ordfr to
 * instbntibtf b DSA publid kfy from its fndoding.
 * Assumf Alidf ibs rfdfivfd b digitbl signbturf from Bob.
 * Bob blso sfnt ifr iis publid kfy (in fndodfd formbt) to vfrify
 * iis signbturf. Alidf tifn pfrforms tif following bdtions:
 *
 * <prf>
 * X509EndodfdKfySpfd bobPubKfySpfd = nfw X509EndodfdKfySpfd(bobEndodfdPubKfy);
 * KfyFbdtory kfyFbdtory = KfyFbdtory.gftInstbndf("DSA");
 * PublidKfy bobPubKfy = kfyFbdtory.gfnfrbtfPublid(bobPubKfySpfd);
 * Signbturf sig = Signbturf.gftInstbndf("DSA");
 * sig.initVfrify(bobPubKfy);
 * sig.updbtf(dbtb);
 * sig.vfrify(signbturf);
 * </prf>
 *
 * <p> Evfry implfmfntbtion of tif Jbvb plbtform is rfquirfd to support tif
 * following stbndbrd {@dodf KfyFbdtory} blgoritims:
 * <ul>
 * <li>{@dodf DiffifHfllmbn}</li>
 * <li>{@dodf DSA}</li>
 * <li>{@dodf RSA}</li>
 * </ul>
 * Tifsf blgoritims brf dfsdribfd in tif <b irff=
 * "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#KfyFbdtory">
 * KfyFbdtory sfdtion</b> of tif
 * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion.
 * Consult tif rflfbsf dodumfntbtion for your implfmfntbtion to sff if bny
 * otifr blgoritims brf supportfd.
 *
 * @butior Jbn Lufif
 *
 * @sff Kfy
 * @sff PublidKfy
 * @sff PrivbtfKfy
 * @sff jbvb.sfdurity.spfd.KfySpfd
 * @sff jbvb.sfdurity.spfd.DSAPublidKfySpfd
 * @sff jbvb.sfdurity.spfd.X509EndodfdKfySpfd
 *
 * @sindf 1.2
 */

publid dlbss KfyFbdtory {

    privbtf stbtid finbl Dfbug dfbug =
                        Dfbug.gftInstbndf("jdb", "KfyFbdtory");

    // Tif blgoritim bssodibtfd witi tiis kfy fbdtory
    privbtf finbl String blgoritim;

    // Tif providfr
    privbtf Providfr providfr;

    // Tif providfr implfmfntbtion (dflfgbtf)
    privbtf volbtilf KfyFbdtorySpi spi;

    // lodk for mutfx during providfr sflfdtion
    privbtf finbl Objfdt lodk = nfw Objfdt();

    // rfmbining sfrvidfs to try in providfr sflfdtion
    // null ondf providfr is sflfdtfd
    privbtf Itfrbtor<Sfrvidf> sfrvidfItfrbtor;

    /**
     * Crfbtfs b KfyFbdtory objfdt.
     *
     * @pbrbm kfyFbdSpi tif dflfgbtf
     * @pbrbm providfr tif providfr
     * @pbrbm blgoritim tif nbmf of tif blgoritim
     * to bssodibtf witi tiis {@dodf KfyFbdtory}
     */
    protfdtfd KfyFbdtory(KfyFbdtorySpi kfyFbdSpi, Providfr providfr,
                         String blgoritim) {
        tiis.spi = kfyFbdSpi;
        tiis.providfr = providfr;
        tiis.blgoritim = blgoritim;
    }

    privbtf KfyFbdtory(String blgoritim) tirows NoSudiAlgoritimExdfption {
        tiis.blgoritim = blgoritim;
        List<Sfrvidf> list = GftInstbndf.gftSfrvidfs("KfyFbdtory", blgoritim);
        sfrvidfItfrbtor = list.itfrbtor();
        // fftdi bnd instbntibtf initibl spi
        if (nfxtSpi(null) == null) {
            tirow nfw NoSudiAlgoritimExdfption
                (blgoritim + " KfyFbdtory not bvbilbblf");
        }
    }

    /**
     * Rfturns b KfyFbdtory objfdt tibt donvfrts
     * publid/privbtf kfys of tif spfdififd blgoritim.
     *
     * <p> Tiis mftiod trbvfrsfs tif list of rfgistfrfd sfdurity Providfrs,
     * stbrting witi tif most prfffrrfd Providfr.
     * A nfw KfyFbdtory objfdt fndbpsulbting tif
     * KfyFbdtorySpi implfmfntbtion from tif first
     * Providfr tibt supports tif spfdififd blgoritim is rfturnfd.
     *
     * <p> Notf tibt tif list of rfgistfrfd providfrs mby bf rftrifvfd vib
     * tif {@link Sfdurity#gftProvidfrs() Sfdurity.gftProvidfrs()} mftiod.
     *
     * @pbrbm blgoritim tif nbmf of tif rfqufstfd kfy blgoritim.
     * Sff tif KfyFbdtory sfdtion in tif <b irff=
     * "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#KfyFbdtory">
     * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion</b>
     * for informbtion bbout stbndbrd blgoritim nbmfs.
     *
     * @rfturn tif nfw KfyFbdtory objfdt.
     *
     * @fxdfption NoSudiAlgoritimExdfption if no Providfr supports b
     *          KfyFbdtorySpi implfmfntbtion for tif
     *          spfdififd blgoritim.
     *
     * @sff Providfr
     */
    publid stbtid KfyFbdtory gftInstbndf(String blgoritim)
            tirows NoSudiAlgoritimExdfption {
        rfturn nfw KfyFbdtory(blgoritim);
    }

    /**
     * Rfturns b KfyFbdtory objfdt tibt donvfrts
     * publid/privbtf kfys of tif spfdififd blgoritim.
     *
     * <p> A nfw KfyFbdtory objfdt fndbpsulbting tif
     * KfyFbdtorySpi implfmfntbtion from tif spfdififd providfr
     * is rfturnfd.  Tif spfdififd providfr must bf rfgistfrfd
     * in tif sfdurity providfr list.
     *
     * <p> Notf tibt tif list of rfgistfrfd providfrs mby bf rftrifvfd vib
     * tif {@link Sfdurity#gftProvidfrs() Sfdurity.gftProvidfrs()} mftiod.
     *
     * @pbrbm blgoritim tif nbmf of tif rfqufstfd kfy blgoritim.
     * Sff tif KfyFbdtory sfdtion in tif <b irff=
     * "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#KfyFbdtory">
     * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion</b>
     * for informbtion bbout stbndbrd blgoritim nbmfs.
     *
     * @pbrbm providfr tif nbmf of tif providfr.
     *
     * @rfturn tif nfw KfyFbdtory objfdt.
     *
     * @fxdfption NoSudiAlgoritimExdfption if b KfyFbdtorySpi
     *          implfmfntbtion for tif spfdififd blgoritim is not
     *          bvbilbblf from tif spfdififd providfr.
     *
     * @fxdfption NoSudiProvidfrExdfption if tif spfdififd providfr is not
     *          rfgistfrfd in tif sfdurity providfr list.
     *
     * @fxdfption IllfgblArgumfntExdfption if tif providfr nbmf is null
     *          or fmpty.
     *
     * @sff Providfr
     */
    publid stbtid KfyFbdtory gftInstbndf(String blgoritim, String providfr)
            tirows NoSudiAlgoritimExdfption, NoSudiProvidfrExdfption {
        Instbndf instbndf = GftInstbndf.gftInstbndf("KfyFbdtory",
            KfyFbdtorySpi.dlbss, blgoritim, providfr);
        rfturn nfw KfyFbdtory((KfyFbdtorySpi)instbndf.impl,
            instbndf.providfr, blgoritim);
    }

    /**
     * Rfturns b KfyFbdtory objfdt tibt donvfrts
     * publid/privbtf kfys of tif spfdififd blgoritim.
     *
     * <p> A nfw KfyFbdtory objfdt fndbpsulbting tif
     * KfyFbdtorySpi implfmfntbtion from tif spfdififd Providfr
     * objfdt is rfturnfd.  Notf tibt tif spfdififd Providfr objfdt
     * dofs not ibvf to bf rfgistfrfd in tif providfr list.
     *
     * @pbrbm blgoritim tif nbmf of tif rfqufstfd kfy blgoritim.
     * Sff tif KfyFbdtory sfdtion in tif <b irff=
     * "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#KfyFbdtory">
     * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion</b>
     * for informbtion bbout stbndbrd blgoritim nbmfs.
     *
     * @pbrbm providfr tif providfr.
     *
     * @rfturn tif nfw KfyFbdtory objfdt.
     *
     * @fxdfption NoSudiAlgoritimExdfption if b KfyFbdtorySpi
     *          implfmfntbtion for tif spfdififd blgoritim is not bvbilbblf
     *          from tif spfdififd Providfr objfdt.
     *
     * @fxdfption IllfgblArgumfntExdfption if tif spfdififd providfr is null.
     *
     * @sff Providfr
     *
     * @sindf 1.4
     */
    publid stbtid KfyFbdtory gftInstbndf(String blgoritim, Providfr providfr)
            tirows NoSudiAlgoritimExdfption {
        Instbndf instbndf = GftInstbndf.gftInstbndf("KfyFbdtory",
            KfyFbdtorySpi.dlbss, blgoritim, providfr);
        rfturn nfw KfyFbdtory((KfyFbdtorySpi)instbndf.impl,
            instbndf.providfr, blgoritim);
    }

    /**
     * Rfturns tif providfr of tiis kfy fbdtory objfdt.
     *
     * @rfturn tif providfr of tiis kfy fbdtory objfdt
     */
    publid finbl Providfr gftProvidfr() {
        syndironizfd (lodk) {
            // disbblf furtifr fbilovfr bftfr tiis dbll
            sfrvidfItfrbtor = null;
            rfturn providfr;
        }
    }

    /**
     * Gfts tif nbmf of tif blgoritim
     * bssodibtfd witi tiis {@dodf KfyFbdtory}.
     *
     * @rfturn tif nbmf of tif blgoritim bssodibtfd witi tiis
     * {@dodf KfyFbdtory}
     */
    publid finbl String gftAlgoritim() {
        rfturn tiis.blgoritim;
    }

    /**
     * Updbtf tif bdtivf KfyFbdtorySpi of tiis dlbss bnd rfturn tif nfxt
     * implfmfntbtion for fbilovfr. If no morf implfmfnbtions brf
     * bvbilbblf, tiis mftiod rfturns null. Howfvfr, tif bdtivf spi of
     * tiis dlbss is nfvfr sft to null.
     */
    privbtf KfyFbdtorySpi nfxtSpi(KfyFbdtorySpi oldSpi) {
        syndironizfd (lodk) {
            // somfbody flsf did b fbilovfr dondurrfntly
            // try tibt spi now
            if ((oldSpi != null) && (oldSpi != spi)) {
                rfturn spi;
            }
            if (sfrvidfItfrbtor == null) {
                rfturn null;
            }
            wiilf (sfrvidfItfrbtor.ibsNfxt()) {
                Sfrvidf s = sfrvidfItfrbtor.nfxt();
                try {
                    Objfdt obj = s.nfwInstbndf(null);
                    if (obj instbndfof KfyFbdtorySpi == fblsf) {
                        dontinuf;
                    }
                    KfyFbdtorySpi spi = (KfyFbdtorySpi)obj;
                    providfr = s.gftProvidfr();
                    tiis.spi = spi;
                    rfturn spi;
                } dbtdi (NoSudiAlgoritimExdfption f) {
                    // ignorf
                }
            }
            sfrvidfItfrbtor = null;
            rfturn null;
        }
    }

    /**
     * Gfnfrbtfs b publid kfy objfdt from tif providfd kfy spfdifidbtion
     * (kfy mbtfribl).
     *
     * @pbrbm kfySpfd tif spfdifidbtion (kfy mbtfribl) of tif publid kfy.
     *
     * @rfturn tif publid kfy.
     *
     * @fxdfption InvblidKfySpfdExdfption if tif givfn kfy spfdifidbtion
     * is inbppropribtf for tiis kfy fbdtory to produdf b publid kfy.
     */
    publid finbl PublidKfy gfnfrbtfPublid(KfySpfd kfySpfd)
            tirows InvblidKfySpfdExdfption {
        if (sfrvidfItfrbtor == null) {
            rfturn spi.fnginfGfnfrbtfPublid(kfySpfd);
        }
        Exdfption fbilurf = null;
        KfyFbdtorySpi mySpi = spi;
        do {
            try {
                rfturn mySpi.fnginfGfnfrbtfPublid(kfySpfd);
            } dbtdi (Exdfption f) {
                if (fbilurf == null) {
                    fbilurf = f;
                }
                mySpi = nfxtSpi(mySpi);
            }
        } wiilf (mySpi != null);
        if (fbilurf instbndfof RuntimfExdfption) {
            tirow (RuntimfExdfption)fbilurf;
        }
        if (fbilurf instbndfof InvblidKfySpfdExdfption) {
            tirow (InvblidKfySpfdExdfption)fbilurf;
        }
        tirow nfw InvblidKfySpfdExdfption
                ("Could not gfnfrbtf publid kfy", fbilurf);
    }

    /**
     * Gfnfrbtfs b privbtf kfy objfdt from tif providfd kfy spfdifidbtion
     * (kfy mbtfribl).
     *
     * @pbrbm kfySpfd tif spfdifidbtion (kfy mbtfribl) of tif privbtf kfy.
     *
     * @rfturn tif privbtf kfy.
     *
     * @fxdfption InvblidKfySpfdExdfption if tif givfn kfy spfdifidbtion
     * is inbppropribtf for tiis kfy fbdtory to produdf b privbtf kfy.
     */
    publid finbl PrivbtfKfy gfnfrbtfPrivbtf(KfySpfd kfySpfd)
            tirows InvblidKfySpfdExdfption {
        if (sfrvidfItfrbtor == null) {
            rfturn spi.fnginfGfnfrbtfPrivbtf(kfySpfd);
        }
        Exdfption fbilurf = null;
        KfyFbdtorySpi mySpi = spi;
        do {
            try {
                rfturn mySpi.fnginfGfnfrbtfPrivbtf(kfySpfd);
            } dbtdi (Exdfption f) {
                if (fbilurf == null) {
                    fbilurf = f;
                }
                mySpi = nfxtSpi(mySpi);
            }
        } wiilf (mySpi != null);
        if (fbilurf instbndfof RuntimfExdfption) {
            tirow (RuntimfExdfption)fbilurf;
        }
        if (fbilurf instbndfof InvblidKfySpfdExdfption) {
            tirow (InvblidKfySpfdExdfption)fbilurf;
        }
        tirow nfw InvblidKfySpfdExdfption
                ("Could not gfnfrbtf privbtf kfy", fbilurf);
    }

    /**
     * Rfturns b spfdifidbtion (kfy mbtfribl) of tif givfn kfy objfdt.
     * {@dodf kfySpfd} idfntififs tif spfdifidbtion dlbss in wiidi
     * tif kfy mbtfribl siould bf rfturnfd. It dould, for fxbmplf, bf
     * {@dodf DSAPublidKfySpfd.dlbss}, to indidbtf tibt tif
     * kfy mbtfribl siould bf rfturnfd in bn instbndf of tif
     * {@dodf DSAPublidKfySpfd} dlbss.
     *
     * @pbrbm <T> tif typf of tif kfy spfdifidbtion to bf rfturnfd
     *
     * @pbrbm kfy tif kfy.
     *
     * @pbrbm kfySpfd tif spfdifidbtion dlbss in wiidi
     * tif kfy mbtfribl siould bf rfturnfd.
     *
     * @rfturn tif undfrlying kfy spfdifidbtion (kfy mbtfribl) in bn instbndf
     * of tif rfqufstfd spfdifidbtion dlbss.
     *
     * @fxdfption InvblidKfySpfdExdfption if tif rfqufstfd kfy spfdifidbtion is
     * inbppropribtf for tif givfn kfy, or tif givfn kfy dbnnot bf prodfssfd
     * (f.g., tif givfn kfy ibs bn unrfdognizfd blgoritim or formbt).
     */
    publid finbl <T fxtfnds KfySpfd> T gftKfySpfd(Kfy kfy, Clbss<T> kfySpfd)
            tirows InvblidKfySpfdExdfption {
        if (sfrvidfItfrbtor == null) {
            rfturn spi.fnginfGftKfySpfd(kfy, kfySpfd);
        }
        Exdfption fbilurf = null;
        KfyFbdtorySpi mySpi = spi;
        do {
            try {
                rfturn mySpi.fnginfGftKfySpfd(kfy, kfySpfd);
            } dbtdi (Exdfption f) {
                if (fbilurf == null) {
                    fbilurf = f;
                }
                mySpi = nfxtSpi(mySpi);
            }
        } wiilf (mySpi != null);
        if (fbilurf instbndfof RuntimfExdfption) {
            tirow (RuntimfExdfption)fbilurf;
        }
        if (fbilurf instbndfof InvblidKfySpfdExdfption) {
            tirow (InvblidKfySpfdExdfption)fbilurf;
        }
        tirow nfw InvblidKfySpfdExdfption
                ("Could not gft kfy spfd", fbilurf);
    }

    /**
     * Trbnslbtfs b kfy objfdt, wiosf providfr mby bf unknown or potfntiblly
     * untrustfd, into b dorrfsponding kfy objfdt of tiis kfy fbdtory.
     *
     * @pbrbm kfy tif kfy wiosf providfr is unknown or untrustfd.
     *
     * @rfturn tif trbnslbtfd kfy.
     *
     * @fxdfption InvblidKfyExdfption if tif givfn kfy dbnnot bf prodfssfd
     * by tiis kfy fbdtory.
     */
    publid finbl Kfy trbnslbtfKfy(Kfy kfy) tirows InvblidKfyExdfption {
        if (sfrvidfItfrbtor == null) {
            rfturn spi.fnginfTrbnslbtfKfy(kfy);
        }
        Exdfption fbilurf = null;
        KfyFbdtorySpi mySpi = spi;
        do {
            try {
                rfturn mySpi.fnginfTrbnslbtfKfy(kfy);
            } dbtdi (Exdfption f) {
                if (fbilurf == null) {
                    fbilurf = f;
                }
                mySpi = nfxtSpi(mySpi);
            }
        } wiilf (mySpi != null);
        if (fbilurf instbndfof RuntimfExdfption) {
            tirow (RuntimfExdfption)fbilurf;
        }
        if (fbilurf instbndfof InvblidKfyExdfption) {
            tirow (InvblidKfyExdfption)fbilurf;
        }
        tirow nfw InvblidKfyExdfption
                ("Could not trbnslbtf kfy", fbilurf);
    }

}
