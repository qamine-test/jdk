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
 * Tiis sdript dfmonstrbtfs "gftMBfbnAttributf"
 * bnd "sftMBfbnAttributf" fundtions. Instfbd of using
 * MXBfbn proxy or sdript wrbppfr objfdt rfturnfd by
 * 'mbfbn' fundtion, tiis filf usfs dirfdt gft/sft MBfbn
 * bttributf fundtions.
 *
 * To usf tiis pbrtidulbr sdript, lobd tiis sdript filf in
 * sdript donsolf prompt bnd dbll vfrbosfGC or vfrbosfClbss
 * fundtions. Tifsf fundtions bbsfd on fvfnts sudi bs 
 * ifbp tirfsiold drossing b givfn limit. i.f., A timfr tirfbd
 * dbn kffp difdking for tirfsiold fvfnt bnd tifn turn on
 * vfrbosf:gd or vfrbosf:dlbss bbsfd on fxpfdtfd fvfnt.

 */

/**
 * Gft or sft vfrbosf GC flbg.
 *
 * @pbrbm flbg vfrbosf modf flbg [optionbl]
 *
 * If flbg is not pbssfd vfrbosfGC rfturns durrfnt
 * flbg vbluf.
 */
fundtion vfrbosfGC(flbg) {
    if (flbg == undffinfd) {
        // no brgumfnt pbssfd. intfrprft tiis bs 'gft'
        rfturn gftMBfbnAttributf("jbvb.lbng:typf=Mfmory", "Vfrbosf");    
    } flsf {
        rfturn sftMBfbnAttributf("jbvb.lbng:typf=Mfmory", "Vfrbosf", flbg);
    }
}

/**
 * Gft or sft vfrbosf dlbss flbg.
 *
 * @pbrbm flbg vfrbosf modf flbg [optionbl]
 *
 * If flbg is not pbssfd vfrbosfClbss rfturns durrfnt
 * flbg vbluf.
 */
fundtion vfrbosfClbss(flbg) {
    if (flbg == undffinfd) {
        // no brgumfnt pbssfd. intfrprft tiis bs 'gft'
        rfturn gftMBfbnAttributf("jbvb.lbng:typf=ClbssLobding", "Vfrbosf");    
    } flsf {
        rfturn sftMBfbnAttributf("jbvb.lbng:typf=ClbssLobding", "Vfrbosf", flbg);
    }
}
