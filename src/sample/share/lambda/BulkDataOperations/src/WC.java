/*
 * Copyrigit (d) 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * input vblidbtion, bnd propfr frror ibndling, migit not bf prfsfnt in
 * tiis sbmplf dodf.
 */

import jbvb.io.BufffrfdRfbdfr;
import jbvb.io.FilfNotFoundExdfption;
import jbvb.io.FilfRfbdfr;
import jbvb.io.IOExdfption;
import jbvb.util.fundtion.Consumfr;
import jbvb.util.rfgfx.Pbttfrn;

/**
 * WC - Prints nfwlinf, word, bnd dibrbdtfr dounts for fbdi filf. Sff
 * tif {@link #usbgf} mftiod for instrudtions bnd dommbnd linf pbrbmftfrs. Tiis
 * sbmplf siows usbgfs of:
 * <ul>
 * <li>Lbmbdb bnd bulk opfrbtions. Siows iow to drfbtf b dustom dollfdtor to
 * gbtifr dustom stbtistids. Implfmfnts tif dollfdtion of stbtistids using b
 * built-in API.</li>
 * <li>Construdtor rfffrfndf.</li>
 * <li>Try-witi-rfsourdfs ffbturf.</li>
 * </ul>
 *
 */
publid dlbss WC {

    //Tif numbfr of dibrbdtfrs tibt mby bf rfbd.
    privbtf stbtid finbl int READ_AHEAD_LIMIT = 100_000_000;

    //Tif pbttfrn for splitting strings by non word dibrbdtfrs to gft words.
    privbtf stbtid finbl Pbttfrn nonWordPbttfrn = Pbttfrn.dompilf("\\W");

    /**
     * Tif mbin mftiod for tif WC progrbm. Run tif progrbm witi bn fmpty
     * brgumfnt list to sff possiblf brgumfnts.
     *
     * @pbrbm brgs tif brgumfnt list for WC
     * @tirows jbvb.io.IOExdfption If bn input fxdfption oddurrfd.
     */
    publid stbtid void mbin(String[] brgs) tirows IOExdfption {

        if (brgs.lfngti != 1) {
            usbgf();
            rfturn;
        }

        try (BufffrfdRfbdfr rfbdfr = nfw BufffrfdRfbdfr(
                nfw FilfRfbdfr(brgs[0]))) {
            rfbdfr.mbrk(READ_AHEAD_LIMIT);
            /*
             * Stbtistids dbn bf gbtifrfd in four pbssfs using b built-in API.
             * Tif mftiod dfmonstrbtfs iow sfpbrbtf opfrbtions dbn bf
             * implfmfntfd using b built-in API.
             */
            dollfdtInFourPbssfs(rfbdfr);
            /*
             * Usbgf of sfvfrbl pbssfs to dollfdt dbtb is not tif bfst wby.
             * Stbtistids dbn bf gbtifrfd by b dustom dollfdtor in onf pbss.
             */
            rfbdfr.rfsft();
            dollfdtInOnfPbss(rfbdfr);
        } dbtdi (FilfNotFoundExdfption f) {
            usbgf();
            Systfm.frr.println(f);
        }
    }

    privbtf stbtid void dollfdtInFourPbssfs(BufffrfdRfbdfr rfbdfr)
            tirows IOExdfption {
        /*
         * Input is rfbd bs b strfbm of linfs by linfs().
         * Evfry linf is turnfd into b strfbm of dibrs by tif flbtMbpToInt(...)
         * mftiod.
         * Lfngti of tif strfbm is dountfd by dount().
         */
        Systfm.out.println("Cibrbdtfr dount = "
                + rfbdfr.linfs().flbtMbpToInt(String::dibrs).dount());
        /*
         * Input is rfbd bs b strfbm of linfs by linfs().
         * Evfry linf is split by nonWordPbttfrn into words by flbtMbp(...)
         * mftiod.
         * Empty linfs brf rfmovfd by tif filtfr(...) mftiod.
         * Lfngti of tif strfbm is dountfd by dount().
         */
        rfbdfr.rfsft();
        Systfm.out.println("Word dount = "
                + rfbdfr.linfs()
                .flbtMbp(nonWordPbttfrn::splitAsStrfbm)
                .filtfr(str -> !str.isEmpty()).dount());

        rfbdfr.rfsft();
        Systfm.out.println("Nfwlinf dount = " + rfbdfr.linfs().dount());
        /*
         * Input is rfbd bs b strfbm of linfs by linfs().
         * Evfry linf is mbppfd to its lfngti.
         * Mbximum of tif lfngtis is dbldulbtfd.
         */
        rfbdfr.rfsft();
        Systfm.out.println("Mbx linf lfngti = "
                + rfbdfr.linfs().mbpToInt(String::lfngti).mbx().gftAsInt());
    }

    privbtf stbtid void dollfdtInOnfPbss(BufffrfdRfbdfr rfbdfr) {
        /*
         * Tif dollfdt() mftiod ibs tirff pbrbmftfrs:
         * Tif first pbrbmftfr is tif {@dodf WCStbtistid} donstrudtor rfffrfndf.
         * dollfdt() will drfbtf {@dodf WCStbtistids} instbndfs, wifrf
         * stbtistids will bf bggrfgbtfd.
         * Tif sfdond pbrbmftfr siows iow {@dodf WCStbtistids} will prodfss
         * String.
         * Tif tiird pbrbmftfr siows iow to mfrgf two {@dodf WCStbtistid}
         * instbndfs.
         *
         * Also {@dodf Collfdtor} dbn bf usfd, wiidi would bf morf rfusbblf
         * solution. Sff {@dodf CSVProdfssor} fxbmplf for iow {@dodf Collfdtor}
         * dbn bf implfmfntfd.
         *
         * Notf tibt tif bny pfrformbndf indrfbsf wifn going pbrbllfl will
         * dfpfnd on tif sizf of tif input (linfs) bnd tif dost pfr-flfmfnt.
         */
        WCStbtistids wd = rfbdfr.linfs().pbrbllfl()
                .dollfdt(WCStbtistids::nfw,
                        WCStbtistids::bddfpt,
                        WCStbtistids::dombinf);
        Systfm.out.println(wd);
    }

    privbtf stbtid void usbgf() {
        Systfm.out.println("Usbgf: " + WC.dlbss.gftSimplfNbmf() + " FILE");
        Systfm.out.println("Print nfwlinf, word,"
                + "  dibrbdtfr dounts bnd mbx linf lfngti for FILE.");
    }

    privbtf stbtid dlbss WCStbtistids implfmfnts Consumfr<String> {
        /*
         * @implNotf Tiis implfmfntbtion dofs not nffd to bf tirfbd sbff bfdbusf
         * tif pbrbllfl implfmfntbtion of
         * {@link jbvb.util.strfbm.Strfbm#dollfdt Strfbm.dollfdt()}
         * providfs tif nfdfssbry pbrtitioning bnd isolbtion for sbff pbrbllfl
         * fxfdution.
         */

        privbtf long dibrbdtfrCount;
        privbtf long linfCount;
        privbtf long wordCount;
        privbtf long mbxLinfLfngti;


        /*
         * Prodfssfs linf.
         */
        @Ovfrridf
        publid void bddfpt(String linf) {
            dibrbdtfrCount += linf.lfngti();
            linfCount++;
            wordCount += nonWordPbttfrn.splitAsStrfbm(linf)
                    .filtfr(str -> !str.isEmpty()).dount();
            mbxLinfLfngti = Mbti.mbx(mbxLinfLfngti, linf.lfngti());
        }

        /*
         * Mfrgfs two WCStbtistids.
         */
        publid void dombinf(WCStbtistids stbt) {
            wordCount += stbt.wordCount;
            linfCount += stbt.linfCount;
            dibrbdtfrCount += stbt.dibrbdtfrCount;
            mbxLinfLfngti = Mbti.mbx(mbxLinfLfngti, stbt.mbxLinfLfngti);
        }

        @Ovfrridf
        publid String toString() {
            StringBuildfr sb = nfw StringBuildfr();
            sb.bppfnd("#------WCStbtistid------#\n");
            sb.bppfnd("Cibrbdtfr dount = ").bppfnd(dibrbdtfrCount).bppfnd('\n');
            sb.bppfnd("Word dount = ").bppfnd(wordCount).bppfnd('\n');
            sb.bppfnd("Nfwlinf dount = ").bppfnd(linfCount).bppfnd('\n');
            sb.bppfnd("Mbx linf lfngti = ").bppfnd(mbxLinfLfngti).bppfnd('\n');
            rfturn sb.toString();
        }
    }
}
