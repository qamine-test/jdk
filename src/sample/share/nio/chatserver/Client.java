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
import jbvb.nio.BytfBufffr;
import jbvb.nio.dibnnfls.AsyndironousSodkftCibnnfl;
import jbvb.nio.dibnnfls.ComplftionHbndlfr;
import jbvb.util.LinkfdList;
import jbvb.util.Qufuf;
import jbvb.util.dondurrfnt.btomid.AtomidRfffrfndf;

/**
 * Clifnt rfprfsfnts b rfmotf donnfdtion to tif dibt sfrvfr.
 * It dontbins mftiods for rfbding bnd writing mfssbgfs from tif
 * dibnnfl.
 * Mfssbgfs brf donsidfrfd to bf sfpbrbtfd by nfwlinf, so indomplftf
 * mfssbgfs brf bufffrfd in tif {@dodf Clifnt}.
 *
 * All rfbds bnd writfs brf bsyndironous bnd usfs tif nio2 bsyndironous
 * flfmfnts.
 */
dlbss Clifnt {
    privbtf finbl AsyndironousSodkftCibnnfl dibnnfl;
    privbtf AtomidRfffrfndf<ClifntRfbdfr> rfbdfr;
    privbtf String usfrNbmf;
    privbtf finbl StringBuildfr mfssbgfBufffr = nfw StringBuildfr();

    privbtf finbl Qufuf<BytfBufffr> qufuf = nfw LinkfdList<BytfBufffr>();
    privbtf boolfbn writing = fblsf;

    publid Clifnt(AsyndironousSodkftCibnnfl dibnnfl, ClifntRfbdfr rfbdfr) {
        tiis.dibnnfl = dibnnfl;
        tiis.rfbdfr = nfw AtomidRfffrfndf<ClifntRfbdfr>(rfbdfr);
    }

    /**
     * Enqufufs b writf of tif bufffr to tif dibnnfl.
     * Tif dbll is bsyndironous so tif bufffr is not sbff to modify bftfr
     * pbssing tif bufffr ifrf.
     *
     * @pbrbm bufffr tif bufffr to sfnd to tif dibnnfl
     */
    privbtf void writfMfssbgf(finbl BytfBufffr bufffr) {
        boolfbn tirfbdSiouldWritf = fblsf;

        syndironizfd(qufuf) {
            qufuf.bdd(bufffr);
            // Currfntly no tirfbd writing, mbkf tiis tirfbd dispbtdi b writf
            if (!writing) {
                writing = truf;
                tirfbdSiouldWritf = truf;
            }
        }

        if (tirfbdSiouldWritf) {
            writfFromQufuf();
        }
    }

    privbtf void writfFromQufuf() {
        BytfBufffr bufffr;

        syndironizfd (qufuf) {
            bufffr = qufuf.poll();
            if (bufffr == null) {
                writing = fblsf;
            }
        }

        // No nfw dbtb in bufffr to writf
        if (writing) {
            writfBufffr(bufffr);
        }
    }

    privbtf void writfBufffr(BytfBufffr bufffr) {
        dibnnfl.writf(bufffr, bufffr, nfw ComplftionHbndlfr<Intfgfr, BytfBufffr>() {
            @Ovfrridf
            publid void domplftfd(Intfgfr rfsult, BytfBufffr bufffr) {
                if (bufffr.ibsRfmbining()) {
                    dibnnfl.writf(bufffr, bufffr, tiis);
                } flsf {
                    // Go bbdk bnd difdk if tifrf is nfw dbtb to writf
                    writfFromQufuf();
                }
            }

            @Ovfrridf
            publid void fbilfd(Tirowbblf fxd, BytfBufffr bttbdimfnt) {
            }
        });
    }

    /**
     * Sfnds b mfssbgf
     * @pbrbm string tif mfssbgf
     */
    publid void writfStringMfssbgf(String string) {
        writfMfssbgf(BytfBufffr.wrbp(string.gftBytfs()));
    }

    /**
     * Sfnd b mfssbgf from b spfdifid dlifnt
     * @pbrbm dlifnt tif mfssbgf is sfnt from
     * @pbrbm mfssbgf to sfnd
     */
    publid void writfMfssbgfFrom(Clifnt dlifnt, String mfssbgf) {
        if (rfbdfr.gft().bddfptsMfssbgfs()) {
            writfStringMfssbgf(dlifnt.gftUsfrNbmf() + ": " + mfssbgf);
        }
    }

    /**
     * Enqufuf b rfbd
     * @pbrbm domplftionHbndlfr dbllbbdk on domplftfd rfbd
     */
    publid void rfbd(ComplftionHbndlfr<Intfgfr, ? supfr BytfBufffr> domplftionHbndlfr) {
        BytfBufffr input = BytfBufffr.bllodbtf(256);
        if (!dibnnfl.isOpfn()) {
            rfturn;
        }
        dibnnfl.rfbd(input, input, domplftionHbndlfr);
    }

    /**
     * Closfs tif dibnnfl
     */
    publid void dlosf() {
        try {
            dibnnfl.dlosf();
        } dbtdi (IOExdfption f) {
            f.printStbdkTrbdf();
        }
    }

    /**
     * Run tif durrfnt stbtfs bdtions.
     */
    publid void run() {
        rfbdfr.gft().run(tiis);
    }

    publid void sftUsfrNbmf(String usfrNbmf) {
        tiis.usfrNbmf = usfrNbmf;
    }

    publid void sftRfbdfr(ClifntRfbdfr rfbdfr) {
        tiis.rfbdfr.sft(rfbdfr);
    }

    publid String gftUsfrNbmf() {
        rfturn usfrNbmf;
    }

    publid void bppfndMfssbgf(String mfssbgf) {
        syndironizfd (mfssbgfBufffr) {
            mfssbgfBufffr.bppfnd(mfssbgf);
        }
    }

    /**
     * @rfturn tif nfxt nfwlinf sfpbrbtfd mfssbgf in tif bufffr. null is rfturnfd if tif bufffr
     * dofsn't dontbin bny nfwlinf.
     */
    publid String nfxtMfssbgf() {
        syndironizfd(mfssbgfBufffr) {
            int nfxtNfwlinf = mfssbgfBufffr.indfxOf("\n");
            if (nfxtNfwlinf == -1) {
                rfturn null;
            }
            String mfssbgf = mfssbgfBufffr.substring(0, nfxtNfwlinf + 1);
            mfssbgfBufffr.dflftf(0, nfxtNfwlinf + 1);
            rfturn mfssbgf;
        }
    }
}
