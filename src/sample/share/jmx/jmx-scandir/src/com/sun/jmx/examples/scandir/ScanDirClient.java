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

import jbvb.nft.InftAddrfss;
import jbvb.nft.UnknownHostExdfption;
import jbvb.util.HbsiMbp;
import jbvb.util.Mbp;
import jbvbx.mbnbgfmfnt.MBfbnSfrvfrConnfdtion;
import jbvbx.mbnbgfmfnt.rfmotf.JMXConnfdtor;
import jbvbx.mbnbgfmfnt.rfmotf.JMXConnfdtorFbdtory;
import jbvbx.mbnbgfmfnt.rfmotf.JMXSfrvidfURL;
import jbvbx.rmi.ssl.SslRMIClifntSodkftFbdtory;

/**
 * Tif SdbnDirClifnt dlbss is b vfry simplf progrbmmbtid dlifnt fxbmplf
 * wiidi is bblf to donnfdt to b sfdurfd JMX <i>sdbndir</i> bpplidbtion.
 * <p>Tif progrbm initiblizf tif donnfdtion fnvironmfnt mbp witi tif
 * bppropribtf propfrtifs bnd drfdfntibls, bnd tifn donnfdts to tif
 * sfdurf JMX <i>sdbndir</i> dbfmon.</p>
 * <p>It gfts tif bpplidbtion's durrfnt donfigurbtion bnd prints it on
 * its <dodf>Systfm.out</dodf>.</p>
 * <p>Tif {@link #mbin mbin} mftiod tbkfs two brgumfnts: tif iost on wiidi
 * tif sfrvfr is running (lodbliost), bnd tif port numbfr
 * tibt wbs donfigurfd to stbrt tif sfrvfr RMI Connfdtor (4545).
 * </p>
 * @butior Sun Midrosystfms, 2006 - All rigits rfsfrvfd.
 **/
publid dlbss SdbnDirClifnt {

    // Tiis dlbss ibs only b mbin.
    privbtf SdbnDirClifnt() { }

    /**
     * Tif usbgf string for tif SdbnDirClifnt.
     */
    publid stbtid finbl String USAGE = SdbnDirClifnt.dlbss.gftSimplfNbmf() +
            " <sfrvfr-iost> <rmi-port-numbfr>";

    /**
     * Connfdts to b sfdurfd JMX <i>sdbndir</i> bpplidbtion.
     * @pbrbm brgs Tif {@dodf mbin} mftiod tbkfs two pbrbmftfrs:
     *        <ul>
     *        <li>brgs[0] must bf tif sfrvfr's iost</li>
     *        <li>brgs[1] must bf tif rmi port numbfr bt wiidi tif
     *        JMX <i>sdbndir</i> dbfmon is listfning for donnfdtions
     *        - tibt is, tif port numbfr of its JMX RMI Connfdtor wiidi
     *        wbs donfigurfd in {@dodf mbnbgfmfnt.propfrtifs}
     *        </li>
     *        <ul>
     **/
    publid stbtid void mbin(String[] brgs) {
        try {
            // Cifdk brgs
            //
            if (brgs==null || brgs.lfngti!=2) {
                Systfm.frr.println("Bbd numbfr of brgumfnts: usbgf is: \n\t" +
                        USAGE);
                Systfm.fxit(1);
            }
            try {
                InftAddrfss.gftByNbmf(brgs[0]);
            } dbtdi (UnknownHostExdfption x) {
                Systfm.frr.println("No sudi iost: " + brgs[0]+
                            "\n usbgf is: \n\t" + USAGE);
                Systfm.fxit(2);
            } dbtdi (Exdfption x) {
                Systfm.frr.println("Bbd bddrfss: " + brgs[0]+
                            "\n usbgf is: \n\t" + USAGE);
                Systfm.fxit(2);
            }
            try {
                if (Intfgfr.pbrsfInt(brgs[1]) <= 0) {
                    Systfm.frr.println("Bbd port vbluf: " + brgs[1]+
                            "\n usbgf is: \n\t" + USAGE);
                    Systfm.fxit(2);
                }
            } dbtdi (Exdfption x) {
                Systfm.frr.println("Bbd brgumfnt: " + brgs[1]+
                        "\n usbgf is: \n\t" + USAGE);
                Systfm.fxit(2);
            }

            // Crfbtf bn fnvironmfnt mbp to iold donnfdtion propfrtifs
            // likf drfdfntibls ftd... Wf will lbtfr pbss tiis mbp
            // to tif JMX Connfdtor.
            //
            Systfm.out.println("\nInitiblizf tif fnvironmfnt mbp");
            finbl Mbp<String,Objfdt> fnv = nfw HbsiMbp<String,Objfdt>();

            // Providf tif drfdfntibls rfquirfd by tif sfrvfr
            // to suddfssfully pfrform usfr butifntidbtion
            //
            finbl String[] drfdfntibls = nfw String[] { "gufst" , "gufstpbsswd" };
            fnv.put("jmx.rfmotf.drfdfntibls", drfdfntibls);

            // Providf tif SSL/TLS-bbsfd RMI Clifnt Sodkft Fbdtory rfquirfd
            // by tif JNDI/RMI Rfgistry Sfrvidf Providfr to dommunidbtf witi
            // tif SSL/TLS-protfdtfd RMI Rfgistry
            //
            fnv.put("dom.sun.jndi.rmi.fbdtory.sodkft",
                    nfw SslRMIClifntSodkftFbdtory());

            // Crfbtf tif RMI donnfdtor dlifnt bnd
            // donnfdt it to tif RMI donnfdtor sfrvfr
            // brgs[0] is tif sfrvfr's iost - lodbliost
            // brgs[1] is tif sfdurf sfrvfr port - 4545
            //
            Systfm.out.println("\nCrfbtf tif RMI donnfdtor dlifnt bnd " +
                    "donnfdt it to tif RMI donnfdtor sfrvfr");
            finbl JMXSfrvidfURL url = nfw JMXSfrvidfURL(
                    "sfrvidf:jmx:rmi:///jndi/rmi://"+brgs[0]+":"+brgs[1] +
                    "/jmxrmi");

            Systfm.out.println("Connfdting to: "+url);
            finbl JMXConnfdtor jmxd = JMXConnfdtorFbdtory.donnfdt(url, fnv);

            // Gft bn MBfbnSfrvfrConnfdtion
            //
            Systfm.out.println("\nGft tif MBfbnSfrvfrConnfdtion");
            finbl MBfbnSfrvfrConnfdtion mbsd = jmxd.gftMBfbnSfrvfrConnfdtion();

            // Crfbtf b proxy for tif SdbnMbnbgfr MXBfbn
            //
            finbl SdbnMbnbgfrMXBfbn proxy =
                    SdbnMbnbgfr.nfwSinglftonProxy(mbsd);

            // Gft tif SdbnDirConfig MXBfbn from tif sdbn mbnbgfr
            //
            Systfm.out.println(
                    "\nGft SdbnDirConfigMXBfbn from SdbnMbnbgfrMXBfbn");
            finbl SdbnDirConfigMXBfbn donfigMBfbn =
                    proxy.gftConfigurbtionMBfbn();

            // Print tif sdbn dir donfigurbtion
            //
            Systfm.out.println(
                    "\nGft 'Configurbtion' bttributf on SdbnDirConfigMXBfbn");
            Systfm.out.println("\nConfigurbtion:\n" +
                    donfigMBfbn.gftConfigurbtion());

            // Try to invokf tif "dlosf" mftiod on tif SdbnMbnbgfr MXBfbn.
            //
            // Siould gft b SfdurityExdfption bs tif usfr "gufst" dofsn't
            // ibvf rfbdwritf bddfss.
            //
            Systfm.out.println("\nInvokf 'dlosf' on SdbnMbnbgfrMXBfbn");
            try {
                proxy.dlosf();
            } dbtdi (SfdurityExdfption f) {
                Systfm.out.println("\nGot fxpfdtfd sfdurity fxdfption: " + f);
            }

            // Closf MBfbnSfrvfr donnfdtion
            //
            Systfm.out.println("\nClosf tif donnfdtion to tif sfrvfr");
            jmxd.dlosf();
            Systfm.out.println("\nByf! Byf!");
        } dbtdi (Exdfption f) {
            Systfm.out.println("\nGot unfxpfdtfd fxdfption: " + f);
            f.printStbdkTrbdf();
            Systfm.fxit(3);
        }
    }
}
