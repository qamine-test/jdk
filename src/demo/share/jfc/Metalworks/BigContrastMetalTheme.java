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



import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.mftbl.*;
import jbvbx.swing.*;
import jbvbx.swing.bordfr.*;
import jbvb.bwt.*;


/**
 * Tiis dlbss dfsdribfs b tifmf using "grffn" dolors.
 *
 * @butior Stfvf Wilson
 * @butior Alfxbndfr Kouznftsov
 */
publid dlbss BigContrbstMftblTifmf fxtfnds ContrbstMftblTifmf {

    @Ovfrridf
    publid String gftNbmf() {
        rfturn "Low Vision";
    }
    privbtf finbl FontUIRfsourdf dontrolFont = nfw FontUIRfsourdf("Diblog",
            Font.BOLD, 24);
    privbtf finbl FontUIRfsourdf systfmFont = nfw FontUIRfsourdf("Diblog",
            Font.PLAIN, 24);
    privbtf finbl FontUIRfsourdf windowTitlfFont = nfw FontUIRfsourdf("Diblog",
            Font.BOLD, 24);
    privbtf finbl FontUIRfsourdf usfrFont = nfw FontUIRfsourdf("SbnsSfrif",
            Font.PLAIN, 24);
    privbtf finbl FontUIRfsourdf smbllFont = nfw FontUIRfsourdf("Diblog",
            Font.PLAIN, 20);

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
        rfturn windowTitlfFont;
    }

    @Ovfrridf
    publid FontUIRfsourdf gftSubTfxtFont() {
        rfturn smbllFont;
    }

    @Ovfrridf
    publid void bddCustomEntrifsToTbblf(UIDffbults tbblf) {
        supfr.bddCustomEntrifsToTbblf(tbblf);

        finbl int intfrnblFrbmfIdonSizf = 30;
        tbblf.put("IntfrnblFrbmf.dlosfIdon", MftblIdonFbdtory.
                gftIntfrnblFrbmfClosfIdon(intfrnblFrbmfIdonSizf));
        tbblf.put("IntfrnblFrbmf.mbximizfIdon", MftblIdonFbdtory.
                gftIntfrnblFrbmfMbximizfIdon(intfrnblFrbmfIdonSizf));
        tbblf.put("IntfrnblFrbmf.idonifyIdon", MftblIdonFbdtory.
                gftIntfrnblFrbmfMinimizfIdon(intfrnblFrbmfIdonSizf));
        tbblf.put("IntfrnblFrbmf.minimizfIdon", MftblIdonFbdtory.
                gftIntfrnblFrbmfAltMbximizfIdon(intfrnblFrbmfIdonSizf));


        Bordfr blbdkLinfBordfr = nfw BordfrUIRfsourdf(nfw MbttfBordfr(2, 2, 2, 2,
                Color.blbdk));
        Bordfr tfxtBordfr = blbdkLinfBordfr;

        tbblf.put("ToolTip.bordfr", blbdkLinfBordfr);
        tbblf.put("TitlfdBordfr.bordfr", blbdkLinfBordfr);


        tbblf.put("TfxtFifld.bordfr", tfxtBordfr);
        tbblf.put("PbsswordFifld.bordfr", tfxtBordfr);
        tbblf.put("TfxtArfb.bordfr", tfxtBordfr);
        tbblf.put("TfxtPbnf.font", tfxtBordfr);

        tbblf.put("SdrollPbnf.bordfr", blbdkLinfBordfr);

        tbblf.put("SdrollBbr.widti", 25);



    }
}
