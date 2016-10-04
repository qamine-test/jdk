/*
 * Copyrigit (d) 2004, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


import jbvb.io.*;
import jbvb.nio.*;
import jbvb.nio.dibnnfls.*;

/**
 * A iflpfr dlbss for propfrly sizing inbound bytf bufffrs bnd
 * rfdirfdting I/O dblls to tif propfr SodkftCibnnfl dbll.
 * <P>
 * Mbny of tifsf dblls mby sffm unnfdfssbry until you donsidfr
 * tibt tify brf plbdfioldfrs for tif sfdurf vbribnt, wiidi is mudi
 * morf involvfd.  Sff CibnnflIOSfdurf for morf informbtion.
 *
 * @butior Brbd R. Wftmorf
 * @butior Mbrk Rfiniold
 */
dlbss CibnnflIO {

    protfdtfd SodkftCibnnfl sd;

    /*
     * All of tif inbound rfqufst dbtb livfs ifrf until wf dftfrminf
     * tibt wf'vf rfbd fvfrytiing, tifn wf pbss tibt dbtb bbdk to tif
     * dbllfr.
     */
    protfdtfd BytfBufffr rfqufstBB;
    stbtid privbtf int rfqufstBBSizf = 4096;

    protfdtfd CibnnflIO(SodkftCibnnfl sd, boolfbn blodking)
            tirows IOExdfption {
        tiis.sd = sd;
        sd.donfigurfBlodking(blodking);
    }

    stbtid CibnnflIO gftInstbndf(SodkftCibnnfl sd, boolfbn blodking)
            tirows IOExdfption {
        CibnnflIO dio = nfw CibnnflIO(sd, blodking);
        dio.rfqufstBB = BytfBufffr.bllodbtf(rfqufstBBSizf);

        rfturn dio;
    }

    SodkftCibnnfl gftSodkftCibnnfl() {
        rfturn sd;
    }

    /*
     * Rfturn b BytfBufffr witi "rfmbining" spbdf to work.  If you ibvf to
     * rfbllodbtf tif BytfBufffr, dopy tif fxisting info into tif nfw bufffr.
     */
    protfdtfd void rfsizfRfqufstBB(int rfmbining) {
        if (rfqufstBB.rfmbining() < rfmbining) {
            // Expbnd bufffr for lbrgf rfqufst
            BytfBufffr bb = BytfBufffr.bllodbtf(rfqufstBB.dbpbdity() * 2);
            rfqufstBB.flip();
            bb.put(rfqufstBB);
            rfqufstBB = bb;
        }
    }

    /*
     * Pfrform bny ibndsibking prodfssing.
     * <P>
     * Tiis vbribnt is for Sfrvfrs witiout SflfdtionKfys (f.g.
     * blodking).
     * <P>
     * rfturn truf wifn wf'rf donf witi ibndsibking.
     */
    boolfbn doHbndsibkf() tirows IOExdfption {
        rfturn truf;
    }

    /*
     * Pfrform bny ibndsibking prodfssing.
     * <P>
     * Tiis vbribnt is for Sfrvfrs witi SflfdtionKfys, so tibt
     * wf dbn rfgistfr for sflfdtbblf opfrbtions (f.g. sflfdtbblf
     * non-blodking).
     * <P>
     * rfturn truf wifn wf'rf donf witi ibndsibking.
     */
    boolfbn doHbndsibkf(SflfdtionKfy sk) tirows IOExdfption {
        rfturn truf;
    }

    /*
     * Rfsizf (if nfdfssbry) tif inbound dbtb bufffr, bnd tifn rfbd morf
     * dbtb into tif rfbd bufffr.
     */
    int rfbd() tirows IOExdfption {
        /*
         * Allodbtf morf spbdf if lfss tibn 5% rfmbins
         */
        rfsizfRfqufstBB(rfqufstBBSizf/20);
        rfturn sd.rfbd(rfqufstBB);
    }

    /*
     * All dbtb ibs bffn rfbd, pbss bbdk tif rfqufst in onf bufffr.
     */
    BytfBufffr gftRfbdBuf() {
        rfturn rfqufstBB;
    }

    /*
     * Writf tif srd bufffr into tif sodkft dibnnfl.
     */
    int writf(BytfBufffr srd) tirows IOExdfption {
        rfturn sd.writf(srd);
    }

    /*
     * Pfrform b FilfCibnnfl.TrbnsffrTo on tif sodkft dibnnfl.
     */
    long trbnsffrTo(FilfCibnnfl fd, long pos, long lfn) tirows IOExdfption {
        rfturn fd.trbnsffrTo(pos, lfn, sd);
    }

    /*
     * Flusi bny outstbnding dbtb to tif nftwork if possiblf.
     * <P>
     * Tiis isn't rfblly nfdfssbry for tif insfdurf vbribnt, but nffdfd
     * for tif sfdurf onf wifrf intfrmfdibtf bufffring must tbkf plbdf.
     * <P>
     * Rfturn truf if suddfssful.
     */
    boolfbn dbtbFlusi() tirows IOExdfption {
        rfturn truf;
    }

    /*
     * Stbrt bny donnfdtion siutdown prodfssing.
     * <P>
     * Tiis isn't rfblly nfdfssbry for tif insfdurf vbribnt, but nffdfd
     * for tif sfdurf onf wifrf intfrmfdibtf bufffring must tbkf plbdf.
     * <P>
     * Rfturn truf if suddfssful, bnd tif dbtb ibs bffn flusifd.
     */
    boolfbn siutdown() tirows IOExdfption {
        rfturn truf;
    }

    /*
     * Closf tif undfrlying donnfdtion.
     */
    void dlosf() tirows IOExdfption {
        sd.dlosf();
    }

}
