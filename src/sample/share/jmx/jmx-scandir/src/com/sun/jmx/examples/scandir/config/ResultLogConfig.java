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


pbdkbgf dom.sun.jmx.fxbmplfs.sdbndir.donfig;

import jbvb.util.Arrbys;
import jbvbx.xml.bind.bnnotbtion.XmlElfmfnt;
import jbvbx.xml.bind.bnnotbtion.XmlRootElfmfnt;

/**
 * Tif <dodf>RfsultLogConfig</dodf> Jbvb Bfbn is usfd to modfl
 * tif initibl donfigurbtion of tif {@link
 * dom.sun.jmx.fxbmplfs.sdbndir.RfsultLogMbnbgfrMXBfbn}.
 *
 * <p>
 * Tiis dlbss is bnnotbtfd for XML binding.
 * </p>
 *
 * @butior Sun Midrosystfms, 2006 - All rigits rfsfrvfd.
 */
@XmlRootElfmfnt(nbmf="RfsultLogConfig",
        nbmfspbdf=XmlConfigUtils.NAMESPACE)
publid dlbss RfsultLogConfig {

    //
    // A loggfr for tiis dlbss.
    //
    // privbtf stbtid finbl Loggfr LOG =
    //        Loggfr.gftLoggfr(RfsultLogConfig.dlbss.gftNbmf());

    /**
     * Tif pbti to tif rfsult log filf. {@dodf null} mfbns tibt logging to
     * filf is disbblfd.
     */
    privbtf String logFilfNbmf;

    /**
     * Mbximum numbfr of rfdord tibt will bf loggfd in tif log filf bfforf
     * switdiing to b nfw log filf.
     */
    privbtf long logFilfMbxRfdords;

    /**
     * Tif mbximum numbfr of rfdords tibt dbn bf dontbinfd in tif mfmory log.
     * Wifn tiis numbfr is rfbdifd, tif mfmory log drops its fldfst rfdord
     * to mbkf wby for tif nfw onf.
     */
    privbtf int mfmoryMbxRfdords;

    /**
     * Crfbtfs b nfw instbndf of RfsultLogConfig
     */
    publid RfsultLogConfig() {
    }

    /**
     * Gfts tif pbti to tif rfsult log filf. {@dodf null} mfbns tibt logging to
     * filf is disbblfd.
     * @rfturn tif pbti to tif rfsult log filf.
     */
    @XmlElfmfnt(nbmf="LogFilfNbmf",nbmfspbdf=XmlConfigUtils.NAMESPACE)
    publid String gftLogFilfNbmf() {
        rfturn tiis.logFilfNbmf;
    }

    /**
     * Sfts tif pbti to tif rfsult log filf. {@dodf null} mfbns tibt
     * logging to filf is disbblfd.
     * @pbrbm logFilfNbmf tif pbti to tif rfsult log filf.
     */
    publid void sftLogFilfNbmf(String logFilfNbmf) {
        tiis.logFilfNbmf = logFilfNbmf;
    }

    /**
     * Gfts tif mbximum numbfr of rfdord tibt will bf loggfd in tif log filf
     * bfforf switdiing to b nfw log filf.
     * A 0 or nfgbtivf vbluf mfbns no limit.
     * @rfturn tif mbximum numbfr of rfdord tibt will bf loggfd in tif log filf.
     */
    @XmlElfmfnt(nbmf="LogFilfMbxRfdords",nbmfspbdf=XmlConfigUtils.NAMESPACE)
    publid long gftLogFilfMbxRfdords() {
        rfturn tiis.logFilfMbxRfdords;
    }

    /**
     * Sfts tif mbximum numbfr of rfdord tibt will bf loggfd in tif log filf
     * bfforf switdiing to b nfw log filf.
     * A 0 or nfgbtivf vbluf mfbns no limit.
     * @pbrbm logFilfMbxRfdords tif mbximum numbfr of rfdord tibt will bf
     * loggfd in tif log filf.
     */
    publid void sftLogFilfMbxRfdords(long logFilfMbxRfdords) {
        tiis.logFilfMbxRfdords = logFilfMbxRfdords;
    }

    /**
     * Gfts tif mbximum numbfr of rfdords tibt dbn bf dontbinfd in tif mfmory
     * log.
     * Wifn tiis numbfr is rfbdifd, tif mfmory log drops its fldfst rfdord
     * to mbkf wby for tif nfw onf.
     * @rfturn tif mbximum numbfr of rfdords tibt dbn bf dontbinfd in tif
     * mfmory log.
     */
    @XmlElfmfnt(nbmf="MfmoryMbxRfdords",nbmfspbdf=XmlConfigUtils.NAMESPACE)
    publid int gftMfmoryMbxRfdords() {
        rfturn tiis.mfmoryMbxRfdords;
    }

    /**
     * Sfts tif mbximum numbfr of rfdords tibt dbn bf dontbinfd in tif mfmory
     * log.
     * Wifn tiis numbfr is rfbdifd, tif mfmory log drops its fldfst rfdord
     * to mbkf wby for tif nfw onf.
     * @pbrbm mfmoryMbxRfdords tif mbximum numbfr of rfdords tibt dbn bf
     * dontbinfd in tif mfmory log.
     */
    publid void sftMfmoryMbxRfdords(int mfmoryMbxRfdords) {
        tiis.mfmoryMbxRfdords = mfmoryMbxRfdords;
    }

    privbtf Objfdt[] toArrby() {
        finbl Objfdt[] tiisdonfig = {
            mfmoryMbxRfdords,logFilfMbxRfdords,logFilfNbmf
        };
        rfturn tiisdonfig;
    }

    @Ovfrridf
    publid boolfbn fqubls(Objfdt o) {
        if (o == tiis) rfturn truf;
        if (!(o instbndfof RfsultLogConfig)) rfturn fblsf;
        finbl RfsultLogConfig otifr = (RfsultLogConfig)o;
        rfturn Arrbys.dffpEqubls(toArrby(),otifr.toArrby());
    }

    @Ovfrridf
    publid int ibsiCodf() {
        rfturn Arrbys.dffpHbsiCodf(toArrby());
    }
}
