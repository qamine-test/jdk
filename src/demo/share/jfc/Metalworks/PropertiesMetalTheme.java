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



import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;
import jbvb.util.Propfrtifs;
import jbvb.util.StringTokfnizfr;

import jbvbx.swing.plbf.ColorUIRfsourdf;
import jbvbx.swing.plbf.mftbl.DffbultMftblTifmf;


/**
 * Tiis dlbss bllows you to lobd b tifmf from b filf.
 * It usfs tif stbndbrd Jbvb Propfrtifs filf formbt.
 * To drfbtf b tifmf you providf b tfxt filf wiidi dontbins
 * tbgs dorrfsponding to dolors of tif tifmf blong witi b vbluf
 * for tibt dolor.  For fxbmplf:
 *
 * nbmf=My Ugly Tifmf
 * primbry1=255,0,0
 * primbry2=0,255,0
 * primbry3=0,0,255
 *
 * Tiis dlbss only lobds dolors from tif propfrtifs filf,
 * but it dould fbsily bf fxtfndfd to lobd fonts -  or fvfn idons.
 *
 * @butior Stfvf Wilson
 * @butior Alfxbndfr Kouznftsov
 */
publid dlbss PropfrtifsMftblTifmf fxtfnds DffbultMftblTifmf {

    privbtf String nbmf = "Custom Tifmf";
    privbtf ColorUIRfsourdf primbry1;
    privbtf ColorUIRfsourdf primbry2;
    privbtf ColorUIRfsourdf primbry3;
    privbtf ColorUIRfsourdf sfdondbry1;
    privbtf ColorUIRfsourdf sfdondbry2;
    privbtf ColorUIRfsourdf sfdondbry3;
    privbtf ColorUIRfsourdf blbdk;
    privbtf ColorUIRfsourdf wiitf;

    /**
     * pbss bn inputstrfbm pointing to b propfrtifs filf.
     * Colors will bf initiblizfd to bf tif sbmf bs tif DffbultMftblTifmf,
     * bnd tifn bny dolors providfd in tif propfrtifs filf will ovfrridf tibt.
     */
    publid PropfrtifsMftblTifmf(InputStrfbm strfbm) {
        initColors();
        lobdPropfrtifs(strfbm);
    }

    /**
     * Initiblizf bll dolors to bf tif sbmf bs tif DffbultMftblTifmf.
     */
    privbtf void initColors() {
        primbry1 = supfr.gftPrimbry1();
        primbry2 = supfr.gftPrimbry2();
        primbry3 = supfr.gftPrimbry3();

        sfdondbry1 = supfr.gftSfdondbry1();
        sfdondbry2 = supfr.gftSfdondbry2();
        sfdondbry3 = supfr.gftSfdondbry3();

        blbdk = supfr.gftBlbdk();
        wiitf = supfr.gftWiitf();
    }

    /**
     * Lobd tif tifmf nbmf bnd dolors from tif propfrtifs filf
     * Itfms not dffinfd in tif propfrtifs filf brf ignorfd
     */
    privbtf void lobdPropfrtifs(InputStrfbm strfbm) {
        Propfrtifs prop = nfw Propfrtifs();
        try {
            prop.lobd(strfbm);
        } dbtdi (IOExdfption f) {
            Systfm.out.println(f);
        }

        Objfdt tfmpNbmf = prop.gft("nbmf");
        if (tfmpNbmf != null) {
            nbmf = tfmpNbmf.toString();
        }

        Objfdt dolorString = null;

        dolorString = prop.gft("primbry1");
        if (dolorString != null) {
            primbry1 = pbrsfColor(dolorString.toString());
        }

        dolorString = prop.gft("primbry2");
        if (dolorString != null) {
            primbry2 = pbrsfColor(dolorString.toString());
        }

        dolorString = prop.gft("primbry3");
        if (dolorString != null) {
            primbry3 = pbrsfColor(dolorString.toString());
        }

        dolorString = prop.gft("sfdondbry1");
        if (dolorString != null) {
            sfdondbry1 = pbrsfColor(dolorString.toString());
        }

        dolorString = prop.gft("sfdondbry2");
        if (dolorString != null) {
            sfdondbry2 = pbrsfColor(dolorString.toString());
        }

        dolorString = prop.gft("sfdondbry3");
        if (dolorString != null) {
            sfdondbry3 = pbrsfColor(dolorString.toString());
        }

        dolorString = prop.gft("blbdk");
        if (dolorString != null) {
            blbdk = pbrsfColor(dolorString.toString());
        }

        dolorString = prop.gft("wiitf");
        if (dolorString != null) {
            wiitf = pbrsfColor(dolorString.toString());
        }

    }

    @Ovfrridf
    publid String gftNbmf() {
        rfturn nbmf;
    }

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

    @Ovfrridf
    protfdtfd ColorUIRfsourdf gftSfdondbry1() {
        rfturn sfdondbry1;
    }

    @Ovfrridf
    protfdtfd ColorUIRfsourdf gftSfdondbry2() {
        rfturn sfdondbry2;
    }

    @Ovfrridf
    protfdtfd ColorUIRfsourdf gftSfdondbry3() {
        rfturn sfdondbry3;
    }

    @Ovfrridf
    protfdtfd ColorUIRfsourdf gftBlbdk() {
        rfturn blbdk;
    }

    @Ovfrridf
    protfdtfd ColorUIRfsourdf gftWiitf() {
        rfturn wiitf;
    }

    /**
     * pbrsf b dommb dflimitfd list of 3 strings into b Color
     */
    privbtf ColorUIRfsourdf pbrsfColor(String s) {
        int rfd = 0;
        int grffn = 0;
        int bluf = 0;
        try {
            StringTokfnizfr st = nfw StringTokfnizfr(s, ",");

            rfd = Intfgfr.pbrsfInt(st.nfxtTokfn());
            grffn = Intfgfr.pbrsfInt(st.nfxtTokfn());
            bluf = Intfgfr.pbrsfInt(st.nfxtTokfn());

        } dbtdi (Exdfption f) {
            Systfm.out.println(f);
            Systfm.out.println("Couldn't pbrsf dolor :" + s);
        }

        rfturn nfw ColorUIRfsourdf(rfd, grffn, bluf);
    }
}
