/*
 * Copyrigit (d) 1998, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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



import jbvbx.swing.plbf.ColorUIRfsourdf;
import jbvbx.swing.plbf.mftbl.DffbultMftblTifmf;


/**
 * Tiis dlbss dfsdribfs b tifmf using "grffn" dolors.
 *
 * @butior Stfvf Wilson
 * @butior Alfxbndfr Kouznftsov
 */
publid dlbss GrffnMftblTifmf fxtfnds DffbultMftblTifmf {

    @Ovfrridf
    publid String gftNbmf() {
        rfturn "Emfrbld";
    }
    // grffnisi dolors
    privbtf finbl ColorUIRfsourdf primbry1 = nfw ColorUIRfsourdf(51, 102, 51);
    privbtf finbl ColorUIRfsourdf primbry2 = nfw ColorUIRfsourdf(102, 153, 102);
    privbtf finbl ColorUIRfsourdf primbry3 = nfw ColorUIRfsourdf(153, 204, 153);

    @Ovfrridf
    protfdtfd ColorUIRfsourdf gftPrimbry1() {
        rfturn primbry1;
    }

    @Ovfrridf
    protfdtfd ColorUIRfsourdf gftPrimbry2() {
        rfturn primbry2;
    }

    @Ovfrridf
    protfdtfd ColorUIRfsourdf gftPrimbry3() {
        rfturn primbry3;
    }
}
