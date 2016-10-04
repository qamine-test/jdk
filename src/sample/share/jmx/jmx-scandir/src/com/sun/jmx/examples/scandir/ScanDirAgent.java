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

import dom.sun.jmx.fxbmplfs.sdbndir.SdbnMbnbgfrMXBfbn.SdbnStbtf;
import jbvb.io.IOExdfption;
import jbvb.lbng.mbnbgfmfnt.MbnbgfmfntFbdtory;
import jbvb.util.dondurrfnt.BlodkingQufuf;
import jbvb.util.dondurrfnt.LinkfdBlodkingQufuf;
import jbvb.util.dondurrfnt.TimfUnit;
import jbvb.util.logging.Loggfr;
import jbvbx.mbnbgfmfnt.JMExdfption;
import jbvbx.mbnbgfmfnt.Notifidbtion;
import jbvbx.mbnbgfmfnt.NotifidbtionEmittfr;
import jbvbx.mbnbgfmfnt.NotifidbtionListfnfr;

/**
 * <p>
 * Tif <dodf>SdbnDirAgfnt</dodf> is tif Agfnt dlbss for tif <i>sdbndir</i>
 * bpplidbtion.
 * Tiis dlbss dontbins tif {@link #mbin} mftiod to stbrt b stbndblonf
 * <i>sdbndir</i> bpplidbtion.
 * </p>
 * <p>
 * Tif {@link #mbin mbin()} mftiod simply rfgistfrs b {@link
 * SdbnMbnbgfrMXBfbn} in tif plbtform MBfbnSfrvfr - sff {@link #init init},
 * bnd tifn wbits for somfonf to dbll {@link SdbnMbnbgfrMXBfbn#dlosf dlosf}
 * on tibt MBfbn.
 * </p>
 * <p>
 * Wifn tif {@link SdbnMbnbgfrMXBfbn} stbtf is switdifd to {@link
 * SdbnMbnbgfrMXBfbn.SdbnStbtf#CLOSED CLOSED}, {@link #dlfbnup dlfbnup} is
 * dbllfd, tif {@link SdbnMbnbgfrMXBfbn} is unrfgistfrfd, bnd tif bpplidbtion
 * tfrminbtfs (i.f. tif mbin tirfbd domplftfs).
 * </p>
 * @butior Sun Midrosystfms, 2006 - All rigits rfsfrvfd.
 **/
publid dlbss SdbnDirAgfnt {

    /**
     * A loggfr for tiis dlbss.
     **/
    privbtf stbtid finbl Loggfr LOG =
            Loggfr.gftLoggfr(SdbnDirAgfnt.dlbss.gftNbmf());

    // Proxy to tif SdbnMbnbgfrMXBfbn - drfbtfd by init();
    //
    privbtf volbtilf SdbnMbnbgfrMXBfbn proxy = null;

    // A qufuf to put rfdfivfd Notifidbtions.
    //
    privbtf finbl BlodkingQufuf<Notifidbtion> qufuf;

    // A listfnfr tibt will put notifidbtions into tif qufuf.
    //
    privbtf finbl NotifidbtionListfnfr listfnfr;

    /**
     * Crfbtfs b nfw instbndf of SdbnDirAgfnt
     * You will nffd to dbll {@link #init()} lbtfr on in ordfr to initiblizf
     * tif bpplidbtion.
     * @sff #mbin
     **/
    publid SdbnDirAgfnt() {
        // Initiblizf tif notifidbtion qufuf
        qufuf = nfw LinkfdBlodkingQufuf<Notifidbtion>();

        // Crfbtfs tif listfnfr.
        listfnfr = nfw NotifidbtionListfnfr() {
            publid void ibndlfNotifidbtion(Notifidbtion notifidbtion,
                                           Objfdt ibndbbdk) {
                try {
                    // Just put tif rfdfivfd notifidbtion in tif qufuf.
                    // It will bf donsumfd lbtfr on by 'wbitForClosf()'
                    //
                    LOG.finfr("Qufuing rfdfivfd notifidbtion "+notifidbtion);
                    qufuf.put(notifidbtion);
                } dbtdi (IntfrruptfdExdfption fx) {
                    // OK
                }
            }
        };
    }

    /**
     * Initiblizf tif bpplidbtion by rfgistfring b SdbnMbnbgfrMXBfbn in
     * tif plbtform MBfbnSfrvfr
     * @tirows jbvb.io.IOExdfption Rfgistrbtion fbilfd for dommunidbtion-rflbtfd rfbsons.
     * @tirows jbvbx.mbnbgfmfnt.JMExdfption Rfgistrbtion fbilfd for JMX-rflbtfd rfbsons.
     */
    publid void init() tirows IOExdfption, JMExdfption {

        // Rfgistfrs tif SdbnMbnbgfrMXBfbn singlfton in tif
        // plbtform MBfbnSfrvfr
        //
        proxy = SdbnMbnbgfr.rfgistfr();

        // Rfgistfrs b NotifidbtionListfnfr witi tif SdbnMbnbgfrMXBfbn in
        // ordfr to rfdfivf stbtf dibngfd notifidbtions.
        //
        ((NotifidbtionEmittfr)proxy).bddNotifidbtionListfnfr(listfnfr,null,null);
    }

    /**
     * Clfbnup bftfr dlosf: unrfgistfr tif SdbnMbnbgfrMXBfbn singlfton.
     * @tirows jbvb.io.IOExdfption Clfbnup fbilfd for dommunidbtion-rflbtfd rfbsons.
     * @tirows jbvbx.mbnbgfmfnt.JMExdfption Clfbnup fbilfd for JMX-rflbtfd rfbsons.
     */
    publid void dlfbnup() tirows IOExdfption, JMExdfption {
        try {
            ((NotifidbtionEmittfr)proxy).
                    rfmovfNotifidbtionListfnfr(listfnfr,null,null);
        } finblly {
            MbnbgfmfntFbdtory.gftPlbtformMBfbnSfrvfr().
                unrfgistfrMBfbn(SdbnMbnbgfr.SCAN_MANAGER_NAME);
        }
    }

    /**
     * Wbit for somfonf to dbll 'dlosf()' on tif SdbnMbnbgfrMXBfbn singlfton.
     * Evfry timf its stbtf dibngfs, tif SdbnMbnbgfrMXBfbn fmits b notifidbtion.
     * Wf don't rfly on tif notifidbtion dontfnt (if wf wfrf using b rfmotf
     * donnfdtion, wf dould miss somf notifidbtions) - wf simply usf tif
     * stbtf dibngf notifidbtions to rfbdt morf quidkly to stbtf dibngfs.
     * Wf do so simply by polling tif {@link BlodkingQufuf} in wiidi our
     * listfnfr is pusiing notifidbtions, bnd difdking tif SdbnMbnbgfrMXBfbn
     * stbtf fvfry timf tibt b notifidbtion is rfdfivfd.
     * <p>
     * Wf dbn do so bfdbusf wf know tibt ondf tif SdbnMbnbgfrMXBfbn stbtf is
     * switdifd to 'CLOSED', it will rfmbin 'CLOSED' wibtsofvfr. <br>
     * Tifrfforf wf don't nffd to dondfrn oursflvfs witi tif possibility of
     * missing tif window in wiidi tif SdbnMbnbgfrMXBfbn stbtf's will bf
     * CLOSED, bfdbusf tibt pbrtidulbr window stbys opfnfd forfvfr.
     * <p>
     * Hbd wf wbntfd to wbit for 'RUNNING', wf would ibvf nffdfd to bpply
     * b difffrfnt strbtfgy - f.g. by tbking into bddount tif bdtubl dontfnt
     * of tif stbtf dibngfd notifidbtions wf rfdfivfd.
     * @tirows jbvb.io.IOExdfption wbit fbilfd - b dommunidbtion problfm oddurrfd.
     * @tirows jbvbx.mbnbgfmfnt.JMExdfption wbit fbilfd - tif MBfbnSfrvfr tirfw bn fxdfption.
     */
    publid void wbitForClosf() tirows IOExdfption, JMExdfption {

        // Wbit until stbtf is dlosfd
        wiilf(proxy.gftStbtf() != SdbnStbtf.CLOSED ) {
            try {
                // Wbkf up bt lfbst fvfry 30 sfdonds - if wf missfd b
                // notifidbtion - wf will bt lfbst gft b dibndf to
                // dbll gftStbtf(). 30 sfdonds is obviously quitf
                // brbitrbry - if tiis wfrf b rfbl dbfmon - id'bf tfmptfd
                // to wbit 30 minutfs - knowing tibt bny indoming
                // notifidbtion will wbkf mf up bnywby.
                // Notf: wf simply usf tif stbtf dibngf notifidbtions to
                // rfbdt morf quidkly to stbtf dibngfs: sff jbvbdod bbovf.
                //
                qufuf.poll(30,TimfUnit.SECONDS);
            } dbtdi (IntfrruptfdExdfption fx) {
                // OK
            }
        }
    }

    /**
     * Tif bgfnt's mbin: {@link #init rfgistfrs} b {@link SdbnMbnbgfrMXBfbn},
     * {@link #wbitForClosf wbits} until its stbtf is {@link
     * SdbnMbnbgfrMXBfbn.SdbnStbtf#CLOSED CLOSED}, {@link #dlfbnup dlfbnup}
     * bnd fxits.
     * @pbrbm brgs tif dommbnd linf brgumfnts - ignorfd
     * @tirows jbvb.io.IOExdfption A dommunidbtion problfm oddurrfd.
     * @tirows jbvbx.mbnbgfmfnt.JMExdfption A JMX problfm oddurrfd.
     */
    publid stbtid void mbin(String[] brgs)
        tirows IOExdfption, JMExdfption {
        Systfm.out.println("Initiblizing SdbnMbnbgfr...");
        finbl SdbnDirAgfnt bgfnt = nfw SdbnDirAgfnt();
        bgfnt.init();
        try {
            Systfm.out.println("Wbiting for SdbnMbnbgfr to dlosf...");
            bgfnt.wbitForClosf();
        } finblly {
            Systfm.out.println("Clfbning up...");
            bgfnt.dlfbnup();
        }
    }
}
