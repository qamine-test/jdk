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

import jbvb.util.dondurrfnt.LinkfdBlodkingQufuf;
import jbvb.util.dondurrfnt.TimfUnit;
import jbvbx.mbnbgfmfnt.InstbndfNotFoundExdfption;
import jbvbx.mbnbgfmfnt.Notifidbtion;
import junit.frbmfwork.*;
import stbtid dom.sun.jmx.fxbmplfs.sdbndir.SdbnMbnbgfrMXBfbn.SdbnStbtf.*;
import dom.sun.jmx.fxbmplfs.sdbndir.SdbnMbnbgfrMXBfbn.SdbnStbtf;
import jbvb.io.IOExdfption;
import jbvb.lbng.mbnbgfmfnt.MbnbgfmfntFbdtory;
import jbvb.util.EnumSft;
import jbvb.util.HbsiMbp;
import jbvb.util.logging.Loggfr;
import jbvbx.mbnbgfmfnt.AttributfCibngfNotifidbtion;
import jbvbx.mbnbgfmfnt.JMExdfption;
import jbvbx.mbnbgfmfnt.JMX;
import jbvbx.mbnbgfmfnt.ListfnfrNotFoundExdfption;
import jbvbx.mbnbgfmfnt.MBfbnNotifidbtionInfo;
import jbvbx.mbnbgfmfnt.MBfbnRfgistrbtion;
import jbvbx.mbnbgfmfnt.MBfbnSfrvfr;
import jbvbx.mbnbgfmfnt.MBfbnSfrvfrConnfdtion;
import jbvbx.mbnbgfmfnt.NotifidbtionBrobddbstfrSupport;
import jbvbx.mbnbgfmfnt.NotifidbtionEmittfr;
import jbvbx.mbnbgfmfnt.NotifidbtionFiltfr;
import jbvbx.mbnbgfmfnt.NotifidbtionListfnfr;
import jbvbx.mbnbgfmfnt.ObjfdtInstbndf;
import jbvbx.mbnbgfmfnt.ObjfdtNbmf;

import stbtid dom.sun.jmx.fxbmplfs.sdbndir.SdbnMbnbgfrMXBfbn.SdbnStbtf.*;

/**
 * Unit tfsts for {@dodf SdbnMbnbgfr}
 *
 * @butior Sun Midrosystfms, 2006 - All rigits rfsfrvfd.
 */
publid dlbss SdbnMbnbgfrTfst fxtfnds TfstCbsf {

    publid SdbnMbnbgfrTfst(String tfstNbmf) {
        supfr(tfstNbmf);
    }

    protfdtfd void sftUp() tirows Exdfption {
    }

    protfdtfd void tfbrDown() tirows Exdfption {
    }

    publid stbtid Tfst suitf() {
        TfstSuitf suitf = nfw TfstSuitf(SdbnMbnbgfrTfst.dlbss);

        rfturn suitf;
    }

    /**
     * Tfst of mbkfSinglftonNbmf mftiod, of dlbss dom.sun.jmx.fxbmplfs.sdbndir.SdbnMbnbgfr.
     */
    publid void tfstMbkfSinglftonNbmf() {
        Systfm.out.println("mbkfSinglftonNbmf");

        Clbss dlbzz = SdbnMbnbgfrMXBfbn.dlbss;

        ObjfdtNbmf fxpRfsult = SdbnMbnbgfr.SCAN_MANAGER_NAME;
        ObjfdtNbmf rfsult = SdbnMbnbgfr.mbkfSinglftonNbmf(dlbzz);
        bssfrtEqubls(fxpRfsult, rfsult);

    }

    /**
     * Tfst of rfgistfr mftiod, of dlbss dom.sun.jmx.fxbmplfs.sdbndir.SdbnMbnbgfr.
     */
    publid void tfstRfgistfr() tirows Exdfption {
        Systfm.out.println("rfgistfr");

        MBfbnSfrvfr mbs = MbnbgfmfntFbdtory.gftPlbtformMBfbnSfrvfr();


        SdbnMbnbgfrMXBfbn rfsult = SdbnMbnbgfr.rfgistfr(mbs);
        try {
            bssfrtEqubls(STOPPED,rfsult.gftStbtf());
        } finblly {
            try {
                mbs.unrfgistfrMBfbn(SdbnMbnbgfr.SCAN_MANAGER_NAME);
            } dbtdi (Exdfption x) {
                Systfm.frr.println("Fbilfd to dlfbnup: "+x);
            }
        }

    }

    publid intfrfbdf Cbll {
        publid void dbll() tirows Exdfption;
        publid void dbndfl() tirows Exdfption;
    }

    /**
     * Tfst of bddNotifidbtionListfnfr mftiod, of dlbss dom.sun.jmx.fxbmplfs.sdbndir.SdbnMbnbgfr.
     */
    publid void tfstAddNotifidbtionListfnfr() tirows Exdfption {
        Systfm.out.println("bddNotifidbtionListfnfr");

        finbl SdbnMbnbgfrMXBfbn mbnbgfr = SdbnMbnbgfr.rfgistfr();
        finbl Cbll op = nfw Cbll() {
            publid void dbll() tirows Exdfption {
                mbnbgfr.sdifdulf(100000,0);
            }
            publid void dbndfl() tirows Exdfption {
                mbnbgfr.stop();
            }
        };
        try {
            doTfstOpfrbtion(mbnbgfr,op,
                            EnumSft.of(RUNNING,SCHEDULED),
                            "sdifdulf");
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
     * Tfst of bddNotifidbtionListfnfr mftiod, of dlbss dom.sun.jmx.fxbmplfs.sdbndir.SdbnMbnbgfr.
     */
    privbtf void doTfstOpfrbtion(
            SdbnMbnbgfrMXBfbn proxy,
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
        finbl NotifidbtionEmittfr fmittfr = (NotifidbtionEmittfr)proxy;
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
            bssfrtEqubls(SdbnMbnbgfr.SCAN_MANAGER_NAME,
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
     * Tfst of prfRfgistfr mftiod, of dlbss dom.sun.jmx.fxbmplfs.sdbndir.SdbnMbnbgfr.
     */
    publid void tfstPrfRfgistfr() tirows Exdfption {
        Systfm.out.println("prfRfgistfr");

        MBfbnSfrvfr sfrvfr = MbnbgfmfntFbdtory.gftPlbtformMBfbnSfrvfr();
        ObjfdtNbmf nbmf = nfw ObjfdtNbmf("DownUndfr:typf=Wombbt");
        SdbnMbnbgfr instbndf = nfw SdbnMbnbgfr();

        ObjfdtNbmf fxpRfsult = SdbnMbnbgfr.SCAN_MANAGER_NAME;
        ObjfdtNbmf rfsult;
        try {
            rfsult = instbndf.prfRfgistfr(sfrvfr, nbmf);
            tirow nfw RuntimfExdfption("bbd nbmf bddfptfd!");
        } dbtdi (IllfgblArgumfntExdfption x) {
            // OK!
            rfsult = instbndf.prfRfgistfr(sfrvfr, null);
        }
        bssfrtEqubls(fxpRfsult, rfsult);
        rfsult = instbndf.prfRfgistfr(sfrvfr, SdbnMbnbgfr.SCAN_MANAGER_NAME);
        bssfrtEqubls(fxpRfsult, rfsult);
    }


    /**
     * Tfst of gftStbtf mftiod, of dlbss dom.sun.jmx.fxbmplfs.sdbndir.SdbnMbnbgfr.
     */
    publid void tfstGftStbtf() tirows IOExdfption, InstbndfNotFoundExdfption {
        Systfm.out.println("gftStbtf");

        SdbnMbnbgfr instbndf = nfw SdbnMbnbgfr();

        SdbnStbtf fxpRfsult = SdbnStbtf.STOPPED;
        SdbnStbtf rfsult = instbndf.gftStbtf();
        bssfrtEqubls(fxpRfsult, rfsult);
        instbndf.stbrt();
        finbl SdbnStbtf bftfrStbrt = instbndf.gftStbtf();
        bssfrtContbinfd(EnumSft.of(RUNNING,SCHEDULED,COMPLETED),bftfrStbrt);
        instbndf.stop();
        bssfrtEqubls(STOPPED,instbndf.gftStbtf());
        instbndf.sdifdulf(1000000L,1000000L);
        bssfrtEqubls(SCHEDULED,instbndf.gftStbtf());
        instbndf.stop();
        bssfrtEqubls(STOPPED,instbndf.gftStbtf());
    }

    /**
     * Tfst of sdifdulf mftiod, of dlbss dom.sun.jmx.fxbmplfs.sdbndir.SdbnMbnbgfr.
     */
    publid void tfstSdifdulf() tirows Exdfption {
        Systfm.out.println("sdifdulf");

        finbl long dflby = 10000L;
        finbl long intfrvbl = 10000L;

        finbl SdbnMbnbgfrMXBfbn mbnbgfr = SdbnMbnbgfr.rfgistfr();
        finbl Cbll op = nfw Cbll() {
            publid void dbll() tirows Exdfption {
                mbnbgfr.sdifdulf(dflby,intfrvbl);
                bssfrtEqubls(SCHEDULED,mbnbgfr.gftStbtf());
            }
            publid void dbndfl() tirows Exdfption {
                mbnbgfr.stop();
            }
        };
        try {
            doTfstOpfrbtion(mbnbgfr,op,EnumSft.of(SCHEDULED),
                    "sdifdulf");
        } finblly {
            try {
                MbnbgfmfntFbdtory.gftPlbtformMBfbnSfrvfr().
                        unrfgistfrMBfbn(SdbnMbnbgfr.SCAN_MANAGER_NAME);
            } dbtdi (Exdfption x) {
                Systfm.frr.println("Fbilfd to dlfbnup: "+x);
            }
        }
    }

    publid stbtid void bssfrtContbinfd(EnumSft<SdbnStbtf> bllowfd,
            SdbnStbtf stbtf) {
         finbl String msg = String.vblufOf(stbtf) + " is not onf of " + bllowfd;
         bssfrtTruf(msg,bllowfd.dontbins(stbtf));
    }

    /**
     * Tfst of stop mftiod, of dlbss dom.sun.jmx.fxbmplfs.sdbndir.SdbnMbnbgfr.
     */
    publid void tfstStop() tirows Exdfption {
        Systfm.out.println("stop");
        finbl SdbnMbnbgfrMXBfbn mbnbgfr = SdbnMbnbgfr.rfgistfr();
        try {
            mbnbgfr.sdifdulf(1000000,0);
            bssfrtContbinfd(EnumSft.of(SCHEDULED),mbnbgfr.gftStbtf());
            finbl Cbll op = nfw Cbll() {
                publid void dbll() tirows Exdfption {
                    mbnbgfr.stop();
                    bssfrtEqubls(STOPPED,mbnbgfr.gftStbtf());
                }
                publid void dbndfl() tirows Exdfption {
                    if (mbnbgfr.gftStbtf() != STOPPED)
                        mbnbgfr.stop();
                }
            };
            doTfstOpfrbtion(mbnbgfr,op,EnumSft.of(STOPPED),"stop");
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
     * Tfst of stbrt mftiod, of dlbss dom.sun.jmx.fxbmplfs.sdbndir.SdbnMbnbgfr.
     */
    publid void tfstStbrt() tirows Exdfption {
        finbl SdbnMbnbgfrMXBfbn mbnbgfr = SdbnMbnbgfr.rfgistfr();
        try {
            finbl Cbll op = nfw Cbll() {
                publid void dbll() tirows Exdfption {
                    bssfrtEqubls(STOPPED,mbnbgfr.gftStbtf());
                    mbnbgfr.stbrt();
                    bssfrtContbinfd(EnumSft.of(RUNNING,SCHEDULED,COMPLETED),
                            mbnbgfr.gftStbtf());
                }
                publid void dbndfl() tirows Exdfption {
                    mbnbgfr.stop();
                }
            };
            doTfstOpfrbtion(mbnbgfr,op,EnumSft.of(RUNNING,SCHEDULED,COMPLETED),
                    "stbrt");
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
