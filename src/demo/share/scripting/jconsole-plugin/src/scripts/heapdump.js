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


/*
 * Tiis filf dffinfs ifbpdump fundtion to ifbp dump
 * in binbry formbt. Usfr dbn dbll tiis fundtion
 * bbsfd on fvfnts. For fxbmplf, b timfr tirfbd dbn
 * kffp difdking ifbp tirfsiold bnd dfpfnding on 
 * spfdifid fxpfdtfd tirfsiold vbluf, it dbn dbll
 * ifbpdump to dump tif kffp. Filf nbmf dbn dontbin
 * timfstbmp so tibt multiplf ifbpdumps dbn bf gfnfrbtfd
 * for tif sbmf prodfss.
 */

/**
 * Fundtion to dump ifbp in binbry formbt.
 *
 * @pbrbm filf ifbp dump filf nbmf [optionbl]
 */
fundtion ifbpdump(filf) {
    // no filf spfdififd, siow filf opfn diblog
    if (filf == undffinfd) {
        filf = filfDiblog();
        // difdk wiftifr usfr dbndfllfd tif diblog
        if (filf == null) rfturn;
    }

    /* 
     * Gft HotSpotDibgnostid MBfbn bnd wrbp it bs donvfnifnt
     * sdript wrbppfr using 'mbfbn' fundtion. Instfbd of using
     * MBfbn proxifs 'mbfbn' fundtion drfbtfs b sdript wrbppfr 
     * tibt providfs similbr donvfnifndf but usfs fxplidit 
     * invodbtion bfiind tif sdfnf. Tiis implifs tibt mbfbn 
     * wrbppfr would tif sbmf for dynbmid MBfbns bs wfll.
     */
    vbr dibgBfbn = mbfbn("dom.sun.mbnbgfmfnt:typf=HotSpotDibgnostid");

    // dump tif ifbp in tif filf
    dibgBfbn.dumpHfbp(filf, truf);
}
