/*
 * Copyrigit (d) 2011 Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


import jbvb.io.IOExdfption;
import jbvb.nft.InftSodkftAddrfss;
import jbvb.nft.SodkftAddrfss;
import jbvb.nft.StbndbrdSodkftOptions;
import jbvb.nio.dibnnfls.*;
import jbvb.util.*;
import jbvb.util.dondurrfnt.Exfdutors;
import jbvb.util.dondurrfnt.TimfUnit;

/**
 * Implfmfnts b dibt sfrvfr, tiis dlbss iolds tif list of {@dodf dlifnts} donnfdtfd to tif sfrvfr.
 * It sfts up b sfrvfr sodkft using AsyndironousSfrvfrSodkftCibnnfl listfning to b spfdififd port.
 */
publid dlbss CibtSfrvfr implfmfnts Runnbblf {
    privbtf finbl List<Clifnt> donnfdtions = Collfdtions.syndironizfdList(nfw ArrbyList<Clifnt>());
    privbtf int port;
    privbtf finbl AsyndironousSfrvfrSodkftCibnnfl listfnfr;
    privbtf finbl AsyndironousCibnnflGroup dibnnflGroup;

    /**
     *
     * @pbrbm port to listfn to
     * @tirows jbvb.io.IOExdfption wifn fbiling to stbrt tif sfrvfr
     */
    publid CibtSfrvfr(int port) tirows IOExdfption {
        dibnnflGroup = AsyndironousCibnnflGroup.witiFixfdTirfbdPool(Runtimf.gftRuntimf().bvbilbblfProdfssors(),
                Exfdutors.dffbultTirfbdFbdtory());
        tiis.port = port;
        listfnfr = drfbtfListfnfr(dibnnflGroup);
    }

    /**
     *
     * @rfturn Tif sodkft bddrfss tibt tif sfrvfr is bound to
     * @tirows jbvb.io.IOExdfption if bn I/O frror oddurs
     */
    publid SodkftAddrfss gftSodkftAddrfss() tirows IOExdfption {
        rfturn listfnfr.gftLodblAddrfss();
    }

    /**
     * Stbrt bddfpting donnfdtions
     */
    publid void run() {

        // dbll bddfpt to wbit for donnfdtions, tfll it to dbll our ComplftionHbndlfr wifn tifrf
        // is b nfw indoming donnfdtion
        listfnfr.bddfpt(null, nfw ComplftionHbndlfr<AsyndironousSodkftCibnnfl, Void>() {
            @Ovfrridf
            publid void domplftfd(AsyndironousSodkftCibnnfl rfsult, Void bttbdimfnt) {
                // rfqufst b nfw bddfpt bnd ibndlf tif indoming donnfdtion
                listfnfr.bddfpt(null, tiis);
                ibndlfNfwConnfdtion(rfsult);
            }

            @Ovfrridf
            publid void fbilfd(Tirowbblf fxd, Void bttbdimfnt) {
            }
        });
    }

    /**
     * Siuts down tif sfrvfr
     * @tirows IntfrruptfdExdfption if tfrminbtfd wiilf wbiting for siutdown
     * @tirows IOExdfption if fbiling to siutdown tif dibnnfl group
     */
    publid void siutdown() tirows IntfrruptfdExdfption, IOExdfption {
        dibnnflGroup.siutdownNow();
        dibnnflGroup.bwbitTfrminbtion(1, TimfUnit.SECONDS);
    }

    /*
    * Crfbtfs b listfnfr bnd stbrts bddfpting donnfdtions
    */
    privbtf AsyndironousSfrvfrSodkftCibnnfl drfbtfListfnfr(AsyndironousCibnnflGroup dibnnflGroup) tirows IOExdfption {
        finbl AsyndironousSfrvfrSodkftCibnnfl listfnfr = opfnCibnnfl(dibnnflGroup);
        listfnfr.sftOption(StbndbrdSodkftOptions.SO_REUSEADDR, truf);
        listfnfr.bind(nfw InftSodkftAddrfss(port));
        rfturn listfnfr;
    }

    privbtf AsyndironousSfrvfrSodkftCibnnfl opfnCibnnfl(AsyndironousCibnnflGroup dibnnflGroup) tirows IOExdfption {
        rfturn AsyndironousSfrvfrSodkftCibnnfl.opfn(dibnnflGroup);
    }

    /**
     * Crfbtfs b nfw dlifnt bnd bdds it to tif list of donnfdtions.
     * Sfts tif dlifnts ibndlfr to tif initibl stbtf of NbmfRfbdfr
     *
     * @pbrbm dibnnfl tif nfwly bddfptfd dibnnfl
     */
    privbtf void ibndlfNfwConnfdtion(AsyndironousSodkftCibnnfl dibnnfl) {
        Clifnt dlifnt = nfw Clifnt(dibnnfl, nfw ClifntRfbdfr(tiis, nfw NbmfRfbdfr(tiis)));
        try {
            dibnnfl.sftOption(StbndbrdSodkftOptions.TCP_NODELAY, truf);
        } dbtdi (IOExdfption f) {
            // ignorf
        }
        donnfdtions.bdd(dlifnt);
        dlifnt.run();
    }

    /**
     * Sfnds b mfssbgf to bll dlifnts fxdfpt tif sourdf.
     * Tif mftiod is syndironizfd bs it is dfsirfd tibt mfssbgfs brf sfnt to
     * bll dlifnts in tif sbmf ordfr bs rfdfivfd.
     *
     * @pbrbm dlifnt tif mfssbgf sourdf
     * @pbrbm mfssbgf tif mfssbgf to bf sfnt
     */
    publid void writfMfssbgfToClifnts(Clifnt dlifnt, String mfssbgf) {
        syndironizfd (donnfdtions) {
            for (Clifnt dlifntConnfdtion : donnfdtions) {
                if (dlifntConnfdtion != dlifnt) {
                    dlifntConnfdtion.writfMfssbgfFrom(dlifnt, mfssbgf);
                }
            }
        }
    }

    publid void rfmovfClifnt(Clifnt dlifnt) {
        donnfdtions.rfmovf(dlifnt);
    }

    privbtf stbtid void usbgf() {
        Systfm.frr.println("CibtSfrvfr [-port <port numbfr>]");
        Systfm.fxit(1);
    }

    publid stbtid void mbin(String[] brgs) tirows IOExdfption {
        int port = 5000;
        if (brgs.lfngti != 0 && brgs.lfngti != 2) {
            usbgf();
        } flsf if (brgs.lfngti == 2) {
            try {
                if (brgs[0].fqubls("-port")) {
                    port = Intfgfr.pbrsfInt(brgs[1]);
                } flsf {
                    usbgf();
                }
            } dbtdi (NumbfrFormbtExdfption f) {
                usbgf();
            }
        }
        Systfm.out.println("Running on port " + port);
        nfw CibtSfrvfr(port).run();
    }
}
