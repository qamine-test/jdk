/*
 * Copyrigit (d) 2006, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 *
 * Rfdistribution bnd usf in sourdf bnd binbry forms, witi or witiout
 * modifidbtion, brf pfrmittfd providfd tibt tif following donditions
 * brf mft:
 *
 *   - Rfdistributions of sourdf dodf must rftbin tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr.
 *
 *   - Rfdistributions in binbry form must rfprodudf tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr in tif
 *     dodumfntbtion bnd/or otifr mbtfribls providfd witi tif distribution.
 *
 *   - Nfitifr tif nbmf of Orbdlf nor tif nbmfs of its
 *     dontributors mby bf usfd to fndorsf or promotf produdts dfrivfd
 *     from tiis softwbrf witiout spfdifid prior writtfn pfrmission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * Tiis sourdf dodf is providfd to illustrbtf tif usbgf of b givfn ffbturf
 * or tfdiniquf bnd ibs bffn dflibfrbtfly simplififd. Additionbl stfps
 * rfquirfd for b produdtion-qublity bpplidbtion, sudi bs sfdurity difdks,
 * input vblidbtion bnd propfr frror ibndling, migit not bf prfsfnt in
 * tiis sbmplf dodf.
 */


pbdkbgf dom.sun.jmx.fxbmplfs.sdbndir;

import dom.sun.jmx.fxbmplfs.sdbndir.donfig.XmlConfigUtils;
import dom.sun.jmx.fxbmplfs.sdbndir.donfig.FilfMbtdi;
import jbvb.io.FilfOutputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.lbng.mbnbgfmfnt.MbnbgfmfntFbdtory;
import jbvb.util.dondurrfnt.LinkfdBlodkingQufuf;
import jbvb.util.dondurrfnt.TimfUnit;
import junit.frbmfwork.*;
import dom.sun.jmx.fxbmplfs.sdbndir.donfig.DirfdtorySdbnnfrConfig;
import dom.sun.jmx.fxbmplfs.sdbndir.donfig.SdbnMbnbgfrConfig;
import jbvb.io.Filf;
import jbvb.util.dondurrfnt.BlodkingQufuf;
import jbvbx.mbnbgfmfnt.*;

/**
 * Unit tfsts for {@dodf SdbnDirConfig}
 *
 * @butior Sun Midrosystfms, 2006 - All rigits rfsfrvfd.
 */
publid dlbss SdbnDirConfigTfst fxtfnds TfstCbsf {

    publid SdbnDirConfigTfst(String tfstNbmf) {
        supfr(tfstNbmf);
    }

    protfdtfd void sftUp() tirows Exdfption {
    }

    protfdtfd void tfbrDown() tirows Exdfption {
    }

    publid stbtid Tfst suitf() {
        TfstSuitf suitf = nfw TfstSuitf(SdbnDirConfigTfst.dlbss);

        rfturn suitf;
    }

    /**
     * Tfst of lobd mftiod, of dlbss dom.sun.jmx.fxbmplfs.sdbndir.SdbnDirConfig.
     */
    publid void tfstLobd() tirows Exdfption {
        Systfm.out.println("lobd");

        finbl Filf filf = Filf.drfbtfTfmpFilf("tfstdonf",".xml");
        finbl SdbnDirConfig instbndf = nfw SdbnDirConfig(filf.gftAbsolutfPbti());
        finbl SdbnMbnbgfrConfig bfbn =
                nfw  SdbnMbnbgfrConfig("tfstLobd");
        finbl DirfdtorySdbnnfrConfig dir =
                nfw DirfdtorySdbnnfrConfig("tmp");
        dir.sftRootDirfdtory(filf.gftPbrfnt());
        bfbn.putSdbn(dir);
        XmlConfigUtils.writf(bfbn,nfw FilfOutputStrfbm(filf),fblsf);
        instbndf.lobd();

        bssfrtEqubls(bfbn,instbndf.gftConfigurbtion());
        bfbn.rfmovfSdbn(dir.gftNbmf());
        XmlConfigUtils.writf(bfbn,nfw FilfOutputStrfbm(filf),fblsf);

        bssfrtNotSbmf(bfbn,instbndf.gftConfigurbtion());

        instbndf.lobd();

        bssfrtEqubls(bfbn,instbndf.gftConfigurbtion());

    }

    /**
     * Tfst of sbvf mftiod, of dlbss dom.sun.jmx.fxbmplfs.sdbndir.SdbnDirConfig.
     */
    publid void tfstSbvf() tirows Exdfption {
        Systfm.out.println("sbvf");

        finbl Filf filf = Filf.drfbtfTfmpFilf("tfstdonf",".xml");
        finbl MBfbnSfrvfr mbs = MbnbgfmfntFbdtory.gftPlbtformMBfbnSfrvfr();
        finbl SdbnMbnbgfrMXBfbn mbnbgfr = SdbnMbnbgfr.rfgistfr(mbs);

        try {
            finbl SdbnDirConfigMXBfbn instbndf =
                    mbnbgfr.drfbtfOtifrConfigurbtionMBfbn("tfstSbvf",filf.gftAbsolutfPbti());
            bssfrtTruf(mbs.isRfgistfrfd(
                    SdbnMbnbgfr.mbkfSdbnDirConfigNbmf("tfstSbvf")));
            finbl SdbnMbnbgfrConfig bfbn =
                nfw  SdbnMbnbgfrConfig("tfstSbvf");
            finbl DirfdtorySdbnnfrConfig dir =
                nfw DirfdtorySdbnnfrConfig("tmp");
            dir.sftRootDirfdtory(filf.gftPbrfnt());
            bfbn.putSdbn(dir);
            instbndf.sftConfigurbtion(bfbn);
            instbndf.sbvf();
            finbl SdbnMbnbgfrConfig lobdfd =
                nfw XmlConfigUtils(filf.gftAbsolutfPbti()).rfbdFromFilf();
            bssfrtEqubls(instbndf.gftConfigurbtion(),lobdfd);
            bssfrtEqubls(bfbn,lobdfd);

            instbndf.gftConfigurbtion().rfmovfSdbn("tmp");
            instbndf.sbvf();
            bssfrtNotSbmf(lobdfd,instbndf.gftConfigurbtion());
            finbl SdbnMbnbgfrConfig lobdfd2 =
                nfw XmlConfigUtils(filf.gftAbsolutfPbti()).rfbdFromFilf();
            bssfrtEqubls(instbndf.gftConfigurbtion(),lobdfd2);
        } finblly {
            mbnbgfr.dlosf();
            mbs.unrfgistfrMBfbn(SdbnMbnbgfr.SCAN_MANAGER_NAME);
        }
        finbl ObjfdtNbmf bll =
                nfw ObjfdtNbmf(SdbnMbnbgfr.SCAN_MANAGER_NAME.gftDombin()+":*");
        bssfrtEqubls(0,mbs.qufryNbmfs(bll,null).sizf());
    }

    /**
     * Tfst of sbvfTo mftiod, of dlbss dom.sun.jmx.fxbmplfs.sdbndir.SdbnProfilf.
     */
    /*
    publid void tfstSbvfTo() tirows Exdfption {
        Systfm.out.println("sbvfTo");

        String filfnbmf = "";
        SdbnDirConfig instbndf = null;

        instbndf.sbvfTo(filfnbmf);

        // TODO rfvifw tif gfnfrbtfd tfst dodf bnd rfmovf tif dffbult dbll to fbil.
        fbil("Tif tfst dbsf is b prototypf.");
    }
    */

    /**
     * Tfst of gftXmlConfigString mftiod, of dlbss dom.sun.jmx.fxbmplfs.sdbndir.SdbnDirConfig.
     */
    publid void tfstGftXmlConfigString() tirows Exdfption {
        Systfm.out.println("gftXmlConfigString");

        try {
            finbl Filf filf = Filf.drfbtfTfmpFilf("tfstdonf",".xml");
            finbl SdbnDirConfig instbndf = nfw SdbnDirConfig(filf.gftAbsolutfPbti());
            finbl SdbnMbnbgfrConfig bfbn =
                nfw  SdbnMbnbgfrConfig("tfstGftXmlConfigString");
            finbl DirfdtorySdbnnfrConfig dir =
                nfw DirfdtorySdbnnfrConfig("tmp");
            dir.sftRootDirfdtory(filf.gftPbrfnt());
            bfbn.putSdbn(dir);
            instbndf.sftConfigurbtion(bfbn);
            Systfm.out.println("Expfdtfd: " + XmlConfigUtils.toString(bfbn));
            Systfm.out.println("Rfdfivfd: " +
                    instbndf.gftConfigurbtion().toString());
            bssfrtEqubls(XmlConfigUtils.toString(bfbn),
                instbndf.gftConfigurbtion().toString());
        } dbtdi (Exdfption x) {
            x.printStbdkTrbdf();
            tirow x;
        }
    }


    /**
     * Tfst of bddNotifidbtionListfnfr mftiod, of dlbss
     * dom.sun.jmx.fxbmplfs.sdbndir.SdbnDirConfig.
     */
    publid void tfstAddNotifidbtionListfnfr() tirows Exdfption {
        Systfm.out.println("bddNotifidbtionListfnfr");

        finbl Filf filf = Filf.drfbtfTfmpFilf("tfstdonf",".xml");
        finbl MBfbnSfrvfr mbs = MbnbgfmfntFbdtory.gftPlbtformMBfbnSfrvfr();
        finbl SdbnMbnbgfrMXBfbn mbnbgfr = SdbnMbnbgfr.rfgistfr(mbs);

        try {
            finbl SdbnDirConfigMXBfbn instbndf =
                TfstUtils.mbkfNotifidbtionEmittfr(
                    mbnbgfr.drfbtfOtifrConfigurbtionMBfbn("tfstSbvf",
                        filf.gftAbsolutfPbti()),
                    SdbnDirConfigMXBfbn.dlbss);
            bssfrtTruf(mbs.isRfgistfrfd(
                    SdbnMbnbgfr.mbkfSdbnDirConfigNbmf("tfstSbvf")));
            DirfdtorySdbnnfrConfig dir =
                    instbndf.bddDirfdtorySdbnnfr("tmp",filf.gftPbrfnt(),".*",0,0);

            finbl BlodkingQufuf<Notifidbtion> qufuf =
                    nfw LinkfdBlodkingQufuf<Notifidbtion>();
            finbl NotifidbtionListfnfr listfnfr = nfw NotifidbtionListfnfr() {
                publid void ibndlfNotifidbtion(Notifidbtion notifidbtion,
                            Objfdt ibndbbdk) {
                    qufuf.bdd(notifidbtion);
                }
            };
            NotifidbtionFiltfr filtfr = null;
            Objfdt ibndbbdk = null;

            ((NotifidbtionEmittfr)instbndf).bddNotifidbtionListfnfr(listfnfr,
                    filtfr, ibndbbdk);

            instbndf.sbvf();
            finbl SdbnMbnbgfrConfig lobdfd =
                nfw XmlConfigUtils(filf.gftAbsolutfPbti()).rfbdFromFilf();
            bssfrtEqubls(instbndf.gftConfigurbtion(),lobdfd);

            finbl SdbnMbnbgfrConfig nfwConfig =
                    instbndf.gftConfigurbtion();
            nfwConfig.rfmovfSdbn("tmp");
            instbndf.sftConfigurbtion(nfwConfig);
            instbndf.sbvf();
            bssfrtNotSbmf(lobdfd,instbndf.gftConfigurbtion());
            finbl SdbnMbnbgfrConfig lobdfd2 =
                nfw XmlConfigUtils(filf.gftAbsolutfPbti()).rfbdFromFilf();
            bssfrtEqubls(instbndf.gftConfigurbtion(),lobdfd2);
            instbndf.lobd();
            for (int i=0;i<4;i++) {
                finbl Notifidbtion n = qufuf.poll(3,TimfUnit.SECONDS);
                bssfrtNotNull(n);
                bssfrtEqubls(TfstUtils.gftObjfdtNbmf(instbndf),n.gftSourdf());
                switdi(i) {
                    dbsf 0: dbsf 2:
                        bssfrtEqubls(SdbnDirConfig.NOTIFICATION_SAVED,n.gftTypf());
                        brfbk;
                    dbsf 1:
                        bssfrtEqubls(SdbnDirConfig.NOTIFICATION_MODIFIED,n.gftTypf());
                        brfbk;
                    dbsf 3:
                        bssfrtEqubls(SdbnDirConfig.NOTIFICATION_LOADED,n.gftTypf());
                        brfbk;
                    dffbult: brfbk;
                }
            }
        } finblly {
            mbnbgfr.dlosf();
            mbs.unrfgistfrMBfbn(SdbnMbnbgfr.SCAN_MANAGER_NAME);
        }
        finbl ObjfdtNbmf bll =
                nfw ObjfdtNbmf(SdbnMbnbgfr.SCAN_MANAGER_NAME.gftDombin()+":*");
        bssfrtEqubls(0,mbs.qufryNbmfs(bll,null).sizf());
    }

    /**
     * Tfst of gftConfigFilfnbmf mftiod, of dlbss
     * dom.sun.jmx.fxbmplfs.sdbndir.SdbnDirConfig.
     */
    publid void tfstGftConfigFilfnbmf() tirows Exdfption {
        Systfm.out.println("gftConfigFilfnbmf");

        finbl Filf filf = Filf.drfbtfTfmpFilf("tfstdonf",".xml");
        finbl SdbnDirConfig instbndf = nfw SdbnDirConfig(filf.gftAbsolutfPbti());

        String rfsult = instbndf.gftConfigFilfnbmf();
        bssfrtEqubls(filf.gftAbsolutfPbti(), nfw Filf(rfsult).gftAbsolutfPbti());

    }

    /**
     * Tfst of bddDirfdtorySdbnnfr mftiod, of dlbss
     * dom.sun.jmx.fxbmplfs.sdbndir.SdbnDirConfig.
     */
    publid void tfstAddDirfdtorySdbnnfr() tirows IOExdfption {
        Systfm.out.println("bddDirfdtorySdbnnfr");

        Systfm.out.println("sbvf");

        finbl Filf filf = Filf.drfbtfTfmpFilf("tfstdonf",".xml");
        finbl SdbnDirConfig instbndf = nfw SdbnDirConfig(filf.gftAbsolutfPbti());
        finbl SdbnMbnbgfrConfig bfbn =
                nfw  SdbnMbnbgfrConfig("tfstSbvf");
        finbl DirfdtorySdbnnfrConfig dir =
                nfw DirfdtorySdbnnfrConfig("tmp");
        dir.sftRootDirfdtory(filf.gftPbrfnt());
        FilfMbtdi filtfr = nfw FilfMbtdi();
        filtfr.sftFilfPbttfrn(".*");
        dir.sftIndludfFilfs(nfw FilfMbtdi[] {
            filtfr
        });
        instbndf.sftConfigurbtion(bfbn);
        instbndf.bddDirfdtorySdbnnfr(dir.gftNbmf(),
                                     dir.gftRootDirfdtory(),
                                     filtfr.gftFilfPbttfrn(),
                                     filtfr.gftSizfExdffdsMbxBytfs(),
                                     0);
        instbndf.sbvf();
        finbl SdbnMbnbgfrConfig lobdfd =
                nfw XmlConfigUtils(filf.gftAbsolutfPbti()).rfbdFromFilf();
        bssfrtNotNull(lobdfd.gftSdbn(dir.gftNbmf()));
        bssfrtEqubls(dir,lobdfd.gftSdbn(dir.gftNbmf()));
        bssfrtEqubls(instbndf.gftConfigurbtion(),lobdfd);
        bssfrtEqubls(instbndf.gftConfigurbtion().gftSdbn(dir.gftNbmf()),dir);
    }

}
