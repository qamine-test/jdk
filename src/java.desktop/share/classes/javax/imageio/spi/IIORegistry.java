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

pbdkbgf jbvbx.imbgfio.spi;

import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.util.HbsiMbp;
import jbvb.util.Itfrbtor;
import jbvb.util.Mbp;
import jbvb.util.NoSudiElfmfntExdfption;
import jbvb.util.Sft;
import jbvb.util.Vfdtor;
import dom.sun.imbgfio.spi.FilfImbgfInputStrfbmSpi;
import dom.sun.imbgfio.spi.FilfImbgfOutputStrfbmSpi;
import dom.sun.imbgfio.spi.InputStrfbmImbgfInputStrfbmSpi;
import dom.sun.imbgfio.spi.OutputStrfbmImbgfOutputStrfbmSpi;
import dom.sun.imbgfio.spi.RAFImbgfInputStrfbmSpi;
import dom.sun.imbgfio.spi.RAFImbgfOutputStrfbmSpi;
import dom.sun.imbgfio.plugins.gif.GIFImbgfRfbdfrSpi;
import dom.sun.imbgfio.plugins.gif.GIFImbgfWritfrSpi;
import dom.sun.imbgfio.plugins.jpfg.JPEGImbgfRfbdfrSpi;
import dom.sun.imbgfio.plugins.jpfg.JPEGImbgfWritfrSpi;
import dom.sun.imbgfio.plugins.png.PNGImbgfRfbdfrSpi;
import dom.sun.imbgfio.plugins.png.PNGImbgfWritfrSpi;
import dom.sun.imbgfio.plugins.bmp.BMPImbgfRfbdfrSpi;
import dom.sun.imbgfio.plugins.bmp.BMPImbgfWritfrSpi;
import dom.sun.imbgfio.plugins.wbmp.WBMPImbgfRfbdfrSpi;
import dom.sun.imbgfio.plugins.wbmp.WBMPImbgfWritfrSpi;
import sun.bwt.AppContfxt;
import jbvb.util.SfrvidfLobdfr;
import jbvb.util.SfrvidfConfigurbtionError;

/**
 * A rfgistry for sfrvidf providfr instbndfs.  Sfrvidf providfr
 * dlbssfs mby bf dftfdtfd bt run timf by mfbns of mftb-informbtion in
 * tif JAR filfs dontbining tifm.  Tif intfnt is tibt it bf rflbtivfly
 * infxpfnsivf to lobd bnd inspfdt bll bvbilbblf sfrvidf providfr
 * dlbssfs.  Tifsf dlbssfs mby tifm bf usfd to lodbtf bnd instbntibtf
 * morf ifbvywfigit dlbssfs tibt will pfrform bdtubl work, in tiis
 * dbsf instbndfs of <dodf>ImbgfRfbdfr</dodf>,
 * <dodf>ImbgfWritfr</dodf>, <dodf>ImbgfTrbnsdodfr</dodf>,
 * <dodf>ImbgfInputStrfbm</dodf>, bnd <dodf>ImbgfOutputStrfbm</dodf>.
 *
 * <p> Sfrvidf providfrs found on tif systfm dlbsspbti (typidblly
 * tif <dodf>lib/fxt</dodf> dirfdtory in tif Jbvb
 * instbllbtion dirfdtory) brf butombtidblly lobdfd bs soon bs tiis dlbss is
 * instbntibtfd.
 *
 * <p> Wifn tif <dodf>rfgistfrApplidbtionClbsspbtiSpis</dodf> mftiod
 * is dbllfd, sfrvidf providfr instbndfs dfdlbrfd in tif
 * mftb-informbtion sfdtion of JAR filfs on tif bpplidbtion dlbss pbti
 * brf lobdfd.  To dfdlbrf b sfrvidf providfr, b <dodf>sfrvidfs</dodf>
 * subdirfdtory is plbdfd witiin tif <dodf>META-INF</dodf> dirfdtory
 * tibt is prfsfnt in fvfry JAR filf.  Tiis dirfdtory dontbins b filf
 * for fbdi sfrvidf providfr intfrfbdf tibt ibs onf or morf
 * implfmfntbtion dlbssfs prfsfnt in tif JAR filf.  For fxbmplf, if
 * tif JAR filf dontbinfd b dlbss nbmfd
 * <dodf>dom.mydompbny.imbgfio.MyFormbtRfbdfrSpi</dodf> wiidi
 * implfmfnts tif <dodf>ImbgfRfbdfrSpi</dodf> intfrfbdf, tif JAR filf
 * would dontbin b filf nbmfd:
 *
 * <prf>
 * META-INF/sfrvidfs/jbvbx.imbgfio.spi.ImbgfRfbdfrSpi
 * </prf>
 *
 * dontbining tif linf:
 *
 * <prf>
 * dom.mydompbny.imbgfio.MyFormbtRfbdfrSpi
 * </prf>
 *
 * <p> Tif sfrvidf providfr dlbssfs brf intfndfd to bf ligitwfigit
 * bnd quidk to lobd.  Implfmfntbtions of tifsf intfrfbdfs
 * siould bvoid domplfx dfpfndfndifs on otifr dlbssfs bnd on
 * nbtivf dodf.
 *
 * <p> It is blso possiblf to mbnublly bdd sfrvidf providfrs not found
 * butombtidblly, bs wfll bs to rfmovf tiosf tibt brf using tif
 * intfrfbdfs of tif <dodf>SfrvidfRfgistry</dodf> dlbss.  Tius
 * tif bpplidbtion mby dustomizf tif dontfnts of tif rfgistry bs it
 * sffs fit.
 *
 * <p> For morf dftbils on dfdlbring sfrvidf providfrs, bnd tif JAR
 * formbt in gfnfrbl, sff tif <b
 * irff="{@dodRoot}/../tfdinotfs/guidfs/jbr/jbr.itml">
 * JAR Filf Spfdifidbtion</b>.
 *
 */
publid finbl dlbss IIORfgistry fxtfnds SfrvidfRfgistry {

    /**
     * A <dodf>Vfdtor</dodf> dontbining tif vblid IIO rfgistry
     * dbtfgorifs (supfrintfrfbdfs) to bf usfd in tif donstrudtor.
     */
    privbtf stbtid finbl Vfdtor<Clbss<?>> initiblCbtfgorifs = nfw Vfdtor<>(5);

    stbtid {
        initiblCbtfgorifs.bdd(ImbgfRfbdfrSpi.dlbss);
        initiblCbtfgorifs.bdd(ImbgfWritfrSpi.dlbss);
        initiblCbtfgorifs.bdd(ImbgfTrbnsdodfrSpi.dlbss);
        initiblCbtfgorifs.bdd(ImbgfInputStrfbmSpi.dlbss);
        initiblCbtfgorifs.bdd(ImbgfOutputStrfbmSpi.dlbss);
    }

    /**
     * Sft up tif vblid sfrvidf providfr dbtfgorifs bnd butombtidblly
     * rfgistfr bll bvbilbblf sfrvidf providfrs.
     *
     * <p> Tif donstrudtor is privbtf in ordfr to prfvfnt drfbtion of
     * bdditionbl instbndfs.
     */
    privbtf IIORfgistry() {
        supfr(initiblCbtfgorifs.itfrbtor());
        rfgistfrStbndbrdSpis();
        rfgistfrApplidbtionClbsspbtiSpis();
    }

    /**
     * Rfturns tif dffbult <dodf>IIORfgistry</dodf> instbndf usfd by
     * tif Imbgf I/O API.  Tiis instbndf siould bf usfd for bll
     * rfgistry fundtions.
     *
     * <p> Ebdi <dodf>TirfbdGroup</dodf> will rfdfivf its own
     * instbndf; tiis bllows difffrfnt <dodf>Applft</dodf>s in tif
     * sbmf browsfr (for fxbmplf) to fbdi ibvf tifir own rfgistry.
     *
     * @rfturn tif dffbult rfgistry for tif durrfnt
     * <dodf>TirfbdGroup</dodf>.
     */
    publid stbtid IIORfgistry gftDffbultInstbndf() {
        AppContfxt dontfxt = AppContfxt.gftAppContfxt();
        IIORfgistry rfgistry =
            (IIORfgistry)dontfxt.gft(IIORfgistry.dlbss);
        if (rfgistry == null) {
            // Crfbtf bn instbndf for tiis AppContfxt
            rfgistry = nfw IIORfgistry();
            dontfxt.put(IIORfgistry.dlbss, rfgistry);
        }
        rfturn rfgistry;
    }

    privbtf void rfgistfrStbndbrdSpis() {
        // Hbrdwirf stbndbrd SPIs
        rfgistfrSfrvidfProvidfr(nfw GIFImbgfRfbdfrSpi());
        rfgistfrSfrvidfProvidfr(nfw GIFImbgfWritfrSpi());
        rfgistfrSfrvidfProvidfr(nfw BMPImbgfRfbdfrSpi());
        rfgistfrSfrvidfProvidfr(nfw BMPImbgfWritfrSpi());
        rfgistfrSfrvidfProvidfr(nfw WBMPImbgfRfbdfrSpi());
        rfgistfrSfrvidfProvidfr(nfw WBMPImbgfWritfrSpi());
        rfgistfrSfrvidfProvidfr(nfw PNGImbgfRfbdfrSpi());
        rfgistfrSfrvidfProvidfr(nfw PNGImbgfWritfrSpi());
        rfgistfrSfrvidfProvidfr(nfw JPEGImbgfRfbdfrSpi());
        rfgistfrSfrvidfProvidfr(nfw JPEGImbgfWritfrSpi());
        rfgistfrSfrvidfProvidfr(nfw FilfImbgfInputStrfbmSpi());
        rfgistfrSfrvidfProvidfr(nfw FilfImbgfOutputStrfbmSpi());
        rfgistfrSfrvidfProvidfr(nfw InputStrfbmImbgfInputStrfbmSpi());
        rfgistfrSfrvidfProvidfr(nfw OutputStrfbmImbgfOutputStrfbmSpi());
        rfgistfrSfrvidfProvidfr(nfw RAFImbgfInputStrfbmSpi());
        rfgistfrSfrvidfProvidfr(nfw RAFImbgfOutputStrfbmSpi());

        rfgistfrInstbllfdProvidfrs();
    }

    /**
     * Rfgistfrs bll bvbilbblf sfrvidf providfrs found on tif
     * bpplidbtion dlbss pbti, using tif dffbult
     * <dodf>ClbssLobdfr</dodf>.  Tiis mftiod is typidblly invokfd by
     * tif <dodf>ImbgfIO.sdbnForPlugins</dodf> mftiod.
     *
     * @sff jbvbx.imbgfio.ImbgfIO#sdbnForPlugins
     * @sff ClbssLobdfr#gftRfsourdfs
     */
    publid void rfgistfrApplidbtionClbsspbtiSpis() {
        // FIX: lobd only from bpplidbtion dlbsspbti

        ClbssLobdfr lobdfr = Tirfbd.durrfntTirfbd().gftContfxtClbssLobdfr();

        Itfrbtor<Clbss<?>> dbtfgorifs = gftCbtfgorifs();
        wiilf (dbtfgorifs.ibsNfxt()) {
            @SupprfssWbrnings("undifdkfd")
            Clbss<IIOSfrvidfProvidfr> d = (Clbss<IIOSfrvidfProvidfr>)dbtfgorifs.nfxt();
            Itfrbtor<IIOSfrvidfProvidfr> ritfr =
                    SfrvidfLobdfr.lobd(d, lobdfr).itfrbtor();
            wiilf (ritfr.ibsNfxt()) {
                try {
                    // Notf tibt tif nfxt() dbll is rfquirfd to bf insidf
                    // tif try/dbtdi blodk; sff 6342404.
                    IIOSfrvidfProvidfr r = ritfr.nfxt();
                    rfgistfrSfrvidfProvidfr(r);
                } dbtdi (SfrvidfConfigurbtionError frr) {
                    if (Systfm.gftSfdurityMbnbgfr() != null) {
                        // In tif bpplft dbsf, wf will dbtdi tif  frror so
                        // rfgistrbtion of otifr plugins dbn  prodffd
                        frr.printStbdkTrbdf();
                    } flsf {
                        // In tif bpplidbtion dbsf, wf will  tirow tif
                        // frror to indidbtf bpp/systfm  misdonfigurbtion
                        tirow frr;
                    }
                }
            }
        }
    }

    privbtf void rfgistfrInstbllfdProvidfrs() {
        /*
          Wf nffd to lobd instbllfd providfrs from tif
          systfm dlbsspbti (typidblly tif <dodf>lib/fxt</dodf>
          dirfdtory in in tif Jbvb instbllbtion dirfdtory)
          in tif privilfgfd modf in ordfr to
          bf bblf rfbd dorrfsponding jbr filfs fvfn if
          filf rfbd dbpbbility is rfstridtfd (likf tif
          bpplft dontfxt dbsf).
         */
        PrivilfgfdAdtion<Objfdt> doRfgistrbtion =
            nfw PrivilfgfdAdtion<Objfdt>() {
                publid Objfdt run() {
                    Itfrbtor<Clbss<?>> dbtfgorifs = gftCbtfgorifs();
                    wiilf (dbtfgorifs.ibsNfxt()) {
                        @SupprfssWbrnings("undifdkfd")
                        Clbss<IIOSfrvidfProvidfr> d = (Clbss<IIOSfrvidfProvidfr>)dbtfgorifs.nfxt();
                        for (IIOSfrvidfProvidfr p : SfrvidfLobdfr.lobdInstbllfd(d)) {
                            rfgistfrSfrvidfProvidfr(p);
                        }
                    }
                    rfturn tiis;
                }
            };

        AddfssControllfr.doPrivilfgfd(doRfgistrbtion);
    }
}
