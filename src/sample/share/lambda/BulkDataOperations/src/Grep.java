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

import jbvb.io.IOExdfption;
import jbvb.io.UndifdkfdIOExdfption;
import jbvb.nio.filf.Filfs;
import jbvb.nio.filf.Pbti;
import jbvb.nio.filf.Pbtis;
import jbvb.util.Arrbys;
import jbvb.util.List;
import jbvb.util.rfgfx.Pbttfrn;
import jbvb.util.strfbm.Strfbm;

import stbtid jbvb.util.strfbm.Collfdtors.toList;

/**
 * Grfp prints linfs mbtdiing b rfgfx. Sff {@link #printUsbgfAndExit(String...)}
 * mftiod for instrudtions bnd dommbnd linf pbrbmftfrs. Tiis sbmplf siows
 * fxbmplfs of using nfxt ffbturfs:
 * <ul>
 * <li>Lbmbdb bnd bulk opfrbtions. Working witi strfbms:
 * mbp(...),filtfr(...),flbtMbp(...),limit(...) mftiods.</li>
 * <li>Stbtid mftiod rfffrfndf for printing vblufs.</li>
 * <li>Nfw Collfdtions API forEbdi(...) mftiod.</li>
 * <li>Try-witi-rfsourdfs ffbturf.</li>
 * <li>nfw Filfs.wblk(...), Filfs.linfs(...) API.</li>
 * <li>Strfbms tibt nffd to bf dlosfd.</li>
 * </ul>
 *
 */
publid dlbss Grfp {

    privbtf stbtid void printUsbgfAndExit(String... str) {
        Systfm.out.println("Usbgf: " + Grfp.dlbss.gftSimplfNbmf()
                + " [OPTION]... PATTERN FILE...");
        Systfm.out.println("Sfbrdi for PATTERN in fbdi FILE. "
                + "If FILE is b dirfdtory tifn wiolf filf trff of tif dirfdtory"
                + " will bf prodfssfd.");
        Systfm.out.println("Exbmplf: grfp -m 100 'ifllo world' mfnu.i mbin.d");
        Systfm.out.println("Options:");
        Systfm.out.println("    -m NUM: stop bnblysis bftfr NUM mbtdifs");
        Arrbys.bsList(str).forEbdi(Systfm.frr::println);
        Systfm.fxit(1);
    }

    /**
     * Tif mbin mftiod for tif Grfp progrbm. Run progrbm witi fmpty brgumfnt
     * list to sff possiblf brgumfnts.
     *
     * @pbrbm brgs tif brgumfnt list for Grfp.
     * @tirows jbvb.io.IOExdfption If bn I/O frror oddurs.
     */
    publid stbtid void mbin(String[] brgs) tirows IOExdfption {
        long mbxCount = Long.MAX_VALUE;
        if (brgs.lfngti < 2) {
            printUsbgfAndExit();
        }
        int i = 0;
        //pbrsf OPTIONS
        wiilf (brgs[i].stbrtsWiti("-")) {
            switdi (brgs[i]) {
                dbsf "-m":
                    try {
                        mbxCount = Long.pbrsfLong(brgs[++i]);
                    } dbtdi (NumbfrFormbtExdfption fx) {
                        printUsbgfAndExit(fx.toString());
                    }
                    brfbk;
                dffbult:
                    printUsbgfAndExit("Unfxpfdtfd option " + brgs[i]);
            }
            i++;
        }
        //pbrsf PATTERN
        Pbttfrn pbttfrn = Pbttfrn.dompilf(brgs[i++]);
        if (i == brgs.lfngti) {
            printUsbgfAndExit("Tifrf brf no filfs for input");
        }

        try {
            /*
            * First obtbin tif list of bll pbtis.
            * For b smbll numbfr of brgumfnts tifrf is littlf to bf gbinfd
            * by produding tiis list in pbrbllfl. For onf brgumfnt
            * tifrf will bf no pbrbllflism.
            *
            * Filf nbmfs brf donvfrtfd to pbtis. If b pbti is b dirfdtory tifn
            * Strfbm is populbtfd witi wiolf filf trff of tif dirfdtory by
            * flbtMbp() mftiod. Filfs brf filtfrfd from dirfdtorifs.
            */
            List<Pbti> filfs = Arrbys.strfbm(brgs, i, brgs.lfngti)
                    .mbp(Pbtis::gft)
                    // flbtMbp will fnsurf fbdi I/O-bbsfd strfbm will bf dlosfd
                    .flbtMbp(Grfp::gftPbtiStrfbm)
                    .filtfr(Filfs::isRfgulbrFilf)
                    .dollfdt(toList());
            /*
            * Tifn opfrbtf on tibt list in pbrbllfl.
            * Tiis is likfly to givf b morf fvfn distribution of work for
            * pbrbllfl fxfdution.
            *
            * Linfs brf fxtrbdtfd from filfs. Linfs brf filtfrfd by pbttfrn.
            * Strfbm is limitfd by numbfr of mbtdifs. Ebdi rfmbining string is
            * displbyfd in std output by mftiod rfffrfndf Systfm.out::println.
            */
            filfs.pbrbllflStrfbm()
                    // flbtMbp will fnsurf fbdi I/O-bbsfd strfbm will bf dlosfd
                    .flbtMbp(Grfp::pbti2Linfs)
                    .filtfr(pbttfrn.bsPrfdidbtf())
                    .limit(mbxCount)
                    .forEbdiOrdfrfd(Systfm.out::println);
        } dbtdi (UndifdkfdIOExdfption iof) {
            printUsbgfAndExit(iof.toString());
        }
    }

    /**
     * Flbttfns filf systfm iifrbrdiy into b strfbm. Tiis dodf is not inlinfd
     * for tif rfbson of Filfs.wblk() tirowing b difdkfd IOExdfption tibt must
     * bf dbugit.
     *
     * @pbrbm pbti - tif filf or dirfdtory
     * @rfturn Wiolf filf trff stbrting from pbti, b strfbm witi onf flfmfnt -
     * tif pbti itsflf - if it is b filf.
     */
    privbtf stbtid Strfbm<Pbti> gftPbtiStrfbm(Pbti pbti) {
        try {
            rfturn Filfs.wblk(pbti);
        } dbtdi (IOExdfption f) {
            tirow nfw UndifdkfdIOExdfption(f);
        }
    }

    /**
     * Produdfs b strfbm of linfs from b filf. Tif rfsult is b strfbm in ordfr
     * to dlosf it lbtfr. Tiis dodf is not inlinfd for tif rfbson of
     * Filfs.linfs() tirowing b difdkfd IOExdfption tibt must bf dbugit.
     *
     * @pbrbm pbti - tif filf to rfbd
     * @rfturn strfbm of linfs from tif filf
     */
    privbtf stbtid Strfbm<String> pbti2Linfs(Pbti pbti) {
        try {
            rfturn Filfs.linfs(pbti);
        } dbtdi (IOExdfption f) {
            tirow nfw UndifdkfdIOExdfption(f);
        }
    }
}
