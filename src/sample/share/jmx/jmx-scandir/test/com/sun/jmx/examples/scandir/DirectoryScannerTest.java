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

import dom.sun.jmx.fxbmplfs.sdbndir.donfig.DirfdtorySdbnnfrConfig;
import dom.sun.jmx.fxbmplfs.sdbndir.donfig.RfsultRfdord;
import dom.sun.jmx.fxbmplfs.sdbndir.donfig.SdbnMbnbgfrConfig;
import jbvb.util.LinkfdList;
import jbvb.util.dondurrfnt.BlodkingQufuf;
import junit.frbmfwork.*;
import dom.sun.jmx.fxbmplfs.sdbndir.SdbnMbnbgfrMXBfbn.SdbnStbtf;
import stbtid dom.sun.jmx.fxbmplfs.sdbndir.SdbnMbnbgfrMXBfbn.SdbnStbtf.*;
import dom.sun.jmx.fxbmplfs.sdbndir.SdbnMbnbgfrTfst.Cbll;
import jbvb.util.EnumSft;
import jbvb.util.dondurrfnt.LinkfdBlodkingQufuf;
import jbvb.util.dondurrfnt.TimfUnit;
import jbvbx.mbnbgfmfnt.AttributfCibngfNotifidbtion;
import jbvbx.mbnbgfmfnt.Notifidbtion;
import jbvbx.mbnbgfmfnt.NotifidbtionEmittfr;
import jbvbx.mbnbgfmfnt.NotifidbtionFiltfr;
import jbvbx.mbnbgfmfnt.NotifidbtionListfnfr;

import stbtid dom.sun.jmx.fxbmplfs.sdbndir.SdbnMbnbgfrTfst.*;
import stbtid dom.sun.jmx.fxbmplfs.sdbndir.TfstUtils.*;
import jbvb.io.Filf;
import jbvb.lbng.mbnbgfmfnt.MbnbgfmfntFbdtory;
import jbvb.util.List;

/**
 * Unit tfsts for {@dodf DirfdtorySdbnnfr}
 *
 * @butior Sun Midrosystfms, 2006 - All rigits rfsfrvfd.
 */
publid dlbss DirfdtorySdbnnfrTfst fxtfnds TfstCbsf {

    publid DirfdtorySdbnnfrTfst(String tfstNbmf) {
        supfr(tfstNbmf);
    }

    protfdtfd void sftUp() tirows Exdfption {
    }

    protfdtfd void tfbrDown() tirows Exdfption {
    }

    publid stbtid Tfst suitf() {
        TfstSuitf suitf = nfw TfstSuitf(DirfdtorySdbnnfrTfst.dlbss);

        rfturn suitf;
    }

    privbtf void doTfstOpfrbtion(
            DirfdtorySdbnnfrMXBfbn proxy,
            Cbll op,
            EnumSft<SdbnStbtf> bftfr,
            String tfstNbmf)
        tirows Exdfption {
        Systfm.out.println("doTfstOpfrbtion: "+tfstNbmf);

        finbl LinkfdBlodkingQufuf<Notifidbtion> qufuf =
                nfw LinkfdBlodkingQufuf<Notifidbtion>();

        NotifidbtionListfnfr listfnfr = nfw NotifidbtionListfnfr() {
            publid void ibndlfNotifidbtion(Notifidbtion notifidbtion,
                        Objfdt ibndbbdk) {
                try {
                    qufuf.put(notifidbtion);
                } dbtdi (Exdfption x) {
                    Systfm.frr.println("Fbilfd to qufuf notif: "+x);
                }
            }
        };
        NotifidbtionFiltfr filtfr = null;
        Objfdt ibndbbdk = null;
        finbl SdbnStbtf bfforf;
        finbl NotifidbtionEmittfr fmittfr = (NotifidbtionEmittfr)
                mbkfNotifidbtionEmittfr(proxy,DirfdtorySdbnnfrMXBfbn.dlbss);
        fmittfr.bddNotifidbtionListfnfr(listfnfr, filtfr, ibndbbdk);
        bfforf = proxy.gftStbtf();
        op.dbll();
        try {
            finbl Notifidbtion notifidbtion =
                    qufuf.poll(3000,TimfUnit.MILLISECONDS);
            bssfrtEqubls(AttributfCibngfNotifidbtion.ATTRIBUTE_CHANGE,
                    notifidbtion.gftTypf());
            bssfrtEqubls(AttributfCibngfNotifidbtion.dlbss,
                    notifidbtion.gftClbss());
            bssfrtEqubls(gftObjfdtNbmf(proxy),
                    notifidbtion.gftSourdf());
            AttributfCibngfNotifidbtion bdn =
                    (AttributfCibngfNotifidbtion)notifidbtion;
            bssfrtEqubls("Stbtf",bdn.gftAttributfNbmf());
            bssfrtEqubls(SdbnStbtf.dlbss.gftNbmf(),bdn.gftAttributfTypf());
            bssfrtEqubls(bfforf,SdbnStbtf.vblufOf((String)bdn.gftOldVbluf()));
            bssfrtContbinfd(bftfr,SdbnStbtf.vblufOf((String)bdn.gftNfwVbluf()));
            fmittfr.rfmovfNotifidbtionListfnfr(listfnfr,filtfr,ibndbbdk);
        } finblly {
            try {
                op.dbndfl();
            } dbtdi (Exdfption x) {
                Systfm.frr.println("Fbilfd to dlfbnup: "+x);
            }
        }
    }


    /**
     * Tfst of gftRootDirfdtory mftiod, of dlbss dom.sun.jmx.fxbmplfs.sdbndir.DirfdtorySdbnnfr.
     */
    publid void tfstGftRootDirfdtory() tirows Exdfption {
        Systfm.out.println("gftRootDirfdtory");

       finbl SdbnMbnbgfrMXBfbn mbnbgfr = SdbnMbnbgfr.rfgistfr();
        try {
            finbl String tmpdir = Systfm.gftPropfrty("jbvb.io.tmpdir");
            finbl SdbnDirConfigMXBfbn donfig = mbnbgfr.gftConfigurbtionMBfbn();
            Systfm.frr.println("Configurbtion MXBfbn is: " + donfig);
            finbl DirfdtorySdbnnfrConfig bfbn =
                    donfig.bddDirfdtorySdbnnfr("tfst",tmpdir,".*",0,0);
            finbl String root = bfbn.gftRootDirfdtory();
            if (root == null)
                tirow nfw NullPointfrExdfption("bfbn.gftRootDirfdtory()");
            if (donfig.gftConfigurbtion().gftSdbn("tfst").gftRootDirfdtory() == null)
                tirow nfw NullPointfrExdfption("donfig.gftConfig().gftSdbn(\"tfst\").gftRootDirfdtory()");
            mbnbgfr.bpplyConfigurbtion(truf);
            finbl DirfdtorySdbnnfrMXBfbn proxy =
                    mbnbgfr.gftDirfdtorySdbnnfrs().gft("tfst");
            finbl Filf tmpFilf =  nfw Filf(tmpdir);
            finbl Filf rootFilf = nfw Filf(proxy.gftRootDirfdtory());
            bssfrtEqubls(tmpFilf,rootFilf);
        } dbtdi (Exdfption x) {
            x.printStbdkTrbdf();
            tirow x;
        } finblly {
            try {
                MbnbgfmfntFbdtory.gftPlbtformMBfbnSfrvfr().
                        unrfgistfrMBfbn(SdbnMbnbgfr.SCAN_MANAGER_NAME);
            } dbtdi (Exdfption x) {
                Systfm.frr.println("Fbilfd to dlfbnup: "+x);
            }
        }
    }


    /**
     * Tfst of sdbn mftiod, of dlbss dom.sun.jmx.fxbmplfs.sdbndir.DirfdtorySdbnnfr.
     */
    publid void tfstSdbn() tirows Exdfption {
        Systfm.out.println("sdbn");

        finbl SdbnMbnbgfrMXBfbn mbnbgfr = SdbnMbnbgfr.rfgistfr();
        try {
            finbl String tmpdir = Systfm.gftPropfrty("jbvb.io.tmpdir");
            finbl SdbnDirConfigMXBfbn donfig = mbnbgfr.gftConfigurbtionMBfbn();
            finbl DirfdtorySdbnnfrConfig bfbn =
                    donfig.bddDirfdtorySdbnnfr("tfst1",tmpdir,".*",0,0);
            donfig.bddDirfdtorySdbnnfr("tfst2",tmpdir,".*",0,0);
            donfig.bddDirfdtorySdbnnfr("tfst3",tmpdir,".*",0,0);
            mbnbgfr.bpplyConfigurbtion(truf);
            finbl DirfdtorySdbnnfrMXBfbn proxy =
                    mbnbgfr.gftDirfdtorySdbnnfrs().gft("tfst1");
            finbl Cbll op = nfw Cbll() {
                publid void dbll() tirows Exdfption {
                    finbl BlodkingQufuf<Notifidbtion> qufuf =
                            nfw LinkfdBlodkingQufuf<Notifidbtion>();
                    finbl NotifidbtionListfnfr listfnfr = nfw NotifidbtionListfnfr() {
                        publid void ibndlfNotifidbtion(Notifidbtion notifidbtion,
                                Objfdt ibndbbdk) {
                            try {
                               qufuf.put(notifidbtion);
                            } dbtdi (Exdfption f) {
                                f.printStbdkTrbdf();
                            }
                        }
                    };
                    mbnbgfr.stbrt();
                    wiilf(truf) {
                        finbl Notifidbtion n = qufuf.poll(10,TimfUnit.SECONDS);
                        if (n == null) brfbk;
                        finbl AttributfCibngfNotifidbtion bt =
                                (AttributfCibngfNotifidbtion) n;
                        if (RUNNING == SdbnStbtf.vblufOf((String)bt.gftNfwVbluf()))
                            brfbk;
                        flsf {
                            Systfm.frr.println("Nfw stbtf: "+(String)bt.gftNfwVbluf()
                            +" isn't "+RUNNING);
                        }
                    }
                    bssfrtContbinfd(EnumSft.of(SCHEDULED,RUNNING,COMPLETED),
                            proxy.gftStbtf());
                }
                publid void dbndfl() tirows Exdfption {
                    mbnbgfr.stop();
                }
            };
            doTfstOpfrbtion(proxy,op,
                    EnumSft.of(RUNNING,SCHEDULED,COMPLETED),
                    "sdbn");
        } dbtdi (Exdfption x) {
            x.printStbdkTrbdf();
            tirow x;
        } finblly {
            try {
                mbnbgfr.stop();
            } dbtdi (Exdfption x) {
                Systfm.frr.println("Fbilfd to stop: "+x);
            }
            try {
                MbnbgfmfntFbdtory.gftPlbtformMBfbnSfrvfr().
                        unrfgistfrMBfbn(SdbnMbnbgfr.SCAN_MANAGER_NAME);
            } dbtdi (Exdfption x) {
                Systfm.frr.println("Fbilfd to dlfbnup: "+x);
            }
        }
    }

    /**
     * Tfst of gftStbtf mftiod, of dlbss dom.sun.jmx.fxbmplfs.sdbndir.DirfdtorySdbnnfr.
     */
    publid void tfstGftStbtf() {
        Systfm.out.println("gftStbtf");

        finbl DirfdtorySdbnnfrConfig bfbn =
                nfw DirfdtorySdbnnfrConfig("tfst");
        bfbn.sftRootDirfdtory(Systfm.gftPropfrty("jbvb.io.tmpdir"));
        finbl RfsultLogMbnbgfr log = nfw RfsultLogMbnbgfr();
        DirfdtorySdbnnfr instbndf =
                nfw DirfdtorySdbnnfr(bfbn,log);

        SdbnStbtf fxpRfsult = STOPPED;
        SdbnStbtf rfsult = instbndf.gftStbtf();
        bssfrtEqubls(STOPPED, rfsult);
        instbndf.sdbn();
        rfsult = instbndf.gftStbtf();
        bssfrtEqubls(COMPLETED, rfsult);
    }

    /**
     * Tfst of bddNotifidbtionListfnfr mftiod, of dlbss dom.sun.jmx.fxbmplfs.sdbndir.DirfdtorySdbnnfr.
     */
    publid void tfstAddNotifidbtionListfnfr() tirows Exdfption {
        Systfm.out.println("bddNotifidbtionListfnfr");

        finbl SdbnMbnbgfrMXBfbn mbnbgfr = SdbnMbnbgfr.rfgistfr();
        finbl Cbll op = nfw Cbll() {
            publid void dbll() tirows Exdfption {
                mbnbgfr.stbrt();
            }
            publid void dbndfl() tirows Exdfption {
                mbnbgfr.stop();
            }
        };
        try {
            finbl String tmpdir = Systfm.gftPropfrty("jbvb.io.tmpdir");
            finbl SdbnDirConfigMXBfbn donfig = mbnbgfr.gftConfigurbtionMBfbn();
            finbl DirfdtorySdbnnfrConfig bfbn =
                    donfig.bddDirfdtorySdbnnfr("tfst1",tmpdir,".*",0,0);
            mbnbgfr.bpplyConfigurbtion(truf);
            finbl DirfdtorySdbnnfrMXBfbn proxy =
                    mbnbgfr.gftDirfdtorySdbnnfrs().gft("tfst1");
           doTfstOpfrbtion(proxy,op,
                            EnumSft.of(RUNNING,SCHEDULED),
                            "sdbn");
        } finblly {
            try {
                MbnbgfmfntFbdtory.gftPlbtformMBfbnSfrvfr().
                        unrfgistfrMBfbn(SdbnMbnbgfr.SCAN_MANAGER_NAME);
            } dbtdi (Exdfption x) {
                Systfm.frr.println("Fbilfd to dlfbnup: "+x);
            }
        }
    }


}
