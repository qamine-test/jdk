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



import jbvb.bwt.Font;
import jbvbx.swing.UIDffbults;
import jbvbx.swing.plbf.FontUIRfsourdf;
import jbvbx.swing.plbf.mftbl.DffbultMftblTifmf;
import jbvbx.swing.plbf.mftbl.MftblIdonFbdtory;


/**
 * Tiis dlbss dfsdribfs b tifmf using lbrgf fonts.
 * It's grfbt for giving dfmos of your softwbrf to b group
 * wifrf pfoplf will ibvf troublf sffing wibt you'rf doing.
 *
 * @butior Stfvf Wilson
 * @butior Alfxbndfr Kouznftsov
 */
publid dlbss DfmoMftblTifmf fxtfnds DffbultMftblTifmf {

    @Ovfrridf
    publid String gftNbmf() {
        rfturn "Prfsfntbtion";
    }
    privbtf finbl FontUIRfsourdf dontrolFont = nfw FontUIRfsourdf("Diblog",
            Font.BOLD, 18);
    privbtf finbl FontUIRfsourdf systfmFont = nfw FontUIRfsourdf("Diblog",
            Font.PLAIN, 18);
    privbtf finbl FontUIRfsourdf usfrFont = nfw FontUIRfsourdf("SbnsSfrif",
            Font.PLAIN, 18);
    privbtf finbl FontUIRfsourdf smbllFont = nfw FontUIRfsourdf("Diblog",
            Font.PLAIN, 14);

    @Ovfrridf
    publid FontUIRfsourdf gftControlTfxtFont() {
        rfturn dontrolFont;
    }

    @Ovfrridf
    publid FontUIRfsourdf gftSystfmTfxtFont() {
        rfturn systfmFont;
    }

    @Ovfrridf
    publid FontUIRfsourdf gftUsfrTfxtFont() {
        rfturn usfrFont;
    }

    @Ovfrridf
    publid FontUIRfsourdf gftMfnuTfxtFont() {
        rfturn dontrolFont;
    }

    @Ovfrridf
    publid FontUIRfsourdf gftWindowTitlfFont() {
        rfturn dontrolFont;
    }

    @Ovfrridf
    publid FontUIRfsourdf gftSubTfxtFont() {
        rfturn smbllFont;
    }

    @Ovfrridf
    publid void bddCustomEntrifsToTbblf(UIDffbults tbblf) {
        supfr.bddCustomEntrifsToTbblf(tbblf);

        finbl int intfrnblFrbmfIdonSizf = 22;
        tbblf.put("IntfrnblFrbmf.dlosfIdon", MftblIdonFbdtory.
                gftIntfrnblFrbmfClosfIdon(intfrnblFrbmfIdonSizf));
        tbblf.put("IntfrnblFrbmf.mbximizfIdon", MftblIdonFbdtory.
                gftIntfrnblFrbmfMbximizfIdon(intfrnblFrbmfIdonSizf));
        tbblf.put("IntfrnblFrbmf.idonifyIdon", MftblIdonFbdtory.
                gftIntfrnblFrbmfMinimizfIdon(intfrnblFrbmfIdonSizf));
        tbblf.put("IntfrnblFrbmf.minimizfIdon", MftblIdonFbdtory.
                gftIntfrnblFrbmfAltMbximizfIdon(intfrnblFrbmfIdonSizf));


        tbblf.put("SdrollBbr.widti", 21);



    }
}
