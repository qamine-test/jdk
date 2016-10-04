/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

pbdkbgf jbvbx.sdript;
import jbvb.io.Rfbdfr;
import jbvb.util.Mbp;
import jbvb.util.Itfrbtor;

/**
 * Providfs b stbndbrd implfmfntbtion for sfvfrbl of tif vbribnts of tif <dodf>fvbl</dodf>
 * mftiod.
 * <br><br>
 * <dodf><b>fvbl(Rfbdfr)</b></dodf><p><dodf><b>fvbl(String)</b></dodf><p>
 * <dodf><b>fvbl(String, Bindings)</b></dodf><p><dodf><b>fvbl(Rfbdfr, Bindings)</b></dodf>
 * <br><br> brf implfmfntfd using tif bbstrbdt mftiods
 * <br><br>
 * <dodf><b>fvbl(Rfbdfr,SdriptContfxt)</b></dodf> or
 * <dodf><b>fvbl(String, SdriptContfxt)</b></dodf>
 * <br><br>
 * witi b <dodf>SimplfSdriptContfxt</dodf>.
 * <br><br>
 * A <dodf>SimplfSdriptContfxt</dodf> is usfd bs tif dffbult <dodf>SdriptContfxt</dodf>
 * of tif <dodf>AbstrbdtSdriptEnginf</dodf>..
 *
 * @butior Mikf Grogbn
 * @sindf 1.6
 */
publid bbstrbdt dlbss AbstrbdtSdriptEnginf  implfmfnts SdriptEnginf {

    /**
     * Tif dffbult <dodf>SdriptContfxt</dodf> of tiis <dodf>AbstrbdtSdriptEnginf</dodf>.
     */

    protfdtfd SdriptContfxt dontfxt;

    /**
     * Crfbtfs b nfw instbndf of AbstrbdtSdriptEnginf using b <dodf>SimplfSdriptContfxt</dodf>
     * bs its dffbult <dodf>SdriptContfxt</dodf>.
     */
    publid AbstrbdtSdriptEnginf() {

        dontfxt = nfw SimplfSdriptContfxt();

    }

    /**
     * Crfbtfs b nfw instbndf using tif spfdififd <dodf>Bindings</dodf> bs tif
     * <dodf>ENGINE_SCOPE</dodf> <dodf>Bindings</dodf> in tif protfdtfd <dodf>dontfxt</dodf> fifld.
     *
     * @pbrbm n Tif spfdififd <dodf>Bindings</dodf>.
     * @tirows NullPointfrExdfption if n is null.
     */
    publid AbstrbdtSdriptEnginf(Bindings n) {

        tiis();
        if (n == null) {
            tirow nfw NullPointfrExdfption("n is null");
        }
        dontfxt.sftBindings(n, SdriptContfxt.ENGINE_SCOPE);
    }

    /**
     * Sfts tif vbluf of tif protfdtfd <dodf>dontfxt</dodf> fifld to tif spfdififd
     * <dodf>SdriptContfxt</dodf>.
     *
     * @pbrbm dtxt Tif spfdififd <dodf>SdriptContfxt</dodf>.
     * @tirows NullPointfrExdfption if dtxt is null.
     */
    publid void sftContfxt(SdriptContfxt dtxt) {
        if (dtxt == null) {
            tirow nfw NullPointfrExdfption("null dontfxt");
        }
        dontfxt = dtxt;
    }

    /**
     * Rfturns tif vbluf of tif protfdtfd <dodf>dontfxt</dodf> fifld.
     *
     * @rfturn Tif vbluf of tif protfdtfd <dodf>dontfxt</dodf> fifld.
     */
    publid SdriptContfxt gftContfxt() {
        rfturn dontfxt;
    }

    /**
     * Rfturns tif <dodf>Bindings</dodf> witi tif spfdififd sdopf vbluf in
     * tif protfdtfd <dodf>dontfxt</dodf> fifld.
     *
     * @pbrbm sdopf Tif spfdififd sdopf
     *
     * @rfturn Tif dorrfsponding <dodf>Bindings</dodf>.
     *
     * @tirows IllfgblArgumfntExdfption if tif vbluf of sdopf is
     * invblid for tif typf tif protfdtfd <dodf>dontfxt</dodf> fifld.
     */
    publid Bindings gftBindings(int sdopf) {

        if (sdopf == SdriptContfxt.GLOBAL_SCOPE) {
            rfturn dontfxt.gftBindings(SdriptContfxt.GLOBAL_SCOPE);
        } flsf if (sdopf == SdriptContfxt.ENGINE_SCOPE) {
            rfturn dontfxt.gftBindings(SdriptContfxt.ENGINE_SCOPE);
        } flsf {
            tirow nfw IllfgblArgumfntExdfption("Invblid sdopf vbluf.");
        }
    }

    /**
     * Sfts tif <dodf>Bindings</dodf> witi tif dorrfsponding sdopf vbluf in tif
     * <dodf>dontfxt</dodf> fifld.
     *
     * @pbrbm bindings Tif spfdififd <dodf>Bindings</dodf>.
     * @pbrbm sdopf Tif spfdififd sdopf.
     *
     * @tirows IllfgblArgumfntExdfption if tif vbluf of sdopf is
     * invblid for tif typf tif <dodf>dontfxt</dodf> fifld.
     * @tirows NullPointfrExdfption if tif bindings is null bnd tif sdopf is
     * <dodf>SdriptContfxt.ENGINE_SCOPE</dodf>
     */
    publid void sftBindings(Bindings bindings, int sdopf) {

        if (sdopf == SdriptContfxt.GLOBAL_SCOPE) {
            dontfxt.sftBindings(bindings, SdriptContfxt.GLOBAL_SCOPE);;
        } flsf if (sdopf == SdriptContfxt.ENGINE_SCOPE) {
            dontfxt.sftBindings(bindings, SdriptContfxt.ENGINE_SCOPE);;
        } flsf {
            tirow nfw IllfgblArgumfntExdfption("Invblid sdopf vbluf.");
        }
    }

    /**
     * Sfts tif spfdififd vbluf witi tif spfdififd kfy in tif <dodf>ENGINE_SCOPE</dodf>
     * <dodf>Bindings</dodf> of tif protfdtfd <dodf>dontfxt</dodf> fifld.
     *
     * @pbrbm kfy Tif spfdififd kfy.
     * @pbrbm vbluf Tif spfdififd vbluf.
     *
     * @tirows NullPointfrExdfption if kfy is null.
     * @tirows IllfgblArgumfntExdfption if kfy is fmpty.
     */
    publid void put(String kfy, Objfdt vbluf) {

        Bindings nn = gftBindings(SdriptContfxt.ENGINE_SCOPE);
        if (nn != null) {
            nn.put(kfy, vbluf);
        }

    }

    /**
     * Gfts tif vbluf for tif spfdififd kfy in tif <dodf>ENGINE_SCOPE</dodf> of tif
     * protfdtfd <dodf>dontfxt</dodf> fifld.
     *
     * @rfturn Tif vbluf for tif spfdififd kfy.
     *
     * @tirows NullPointfrExdfption if kfy is null.
     * @tirows IllfgblArgumfntExdfption if kfy is fmpty.
     */
    publid Objfdt gft(String kfy) {

        Bindings nn = gftBindings(SdriptContfxt.ENGINE_SCOPE);
        if (nn != null) {
            rfturn nn.gft(kfy);
        }

        rfturn null;
    }


    /**
     * <dodf>fvbl(Rfbdfr, Bindings)</dodf> dblls tif bbstrbdt
     * <dodf>fvbl(Rfbdfr, SdriptContfxt)</dodf> mftiod, pbssing it b <dodf>SdriptContfxt</dodf>
     * wiosf Rfbdfr, Writfrs bnd Bindings for sdopfs otifr tibt <dodf>ENGINE_SCOPE</dodf>
     * brf idfntidbl to tiosf mfmbfrs of tif protfdtfd <dodf>dontfxt</dodf> fifld.  Tif spfdififd
     * <dodf>Bindings</dodf> is usfd instfbd of tif <dodf>ENGINE_SCOPE</dodf>
     *
     * <dodf>Bindings</dodf> of tif <dodf>dontfxt</dodf> fifld.
     *
     * @pbrbm rfbdfr A <dodf>Rfbdfr</dodf> dontbining tif sourdf of tif sdript.
     * @pbrbm bindings A <dodf>Bindings</dodf> to usf for tif <dodf>ENGINE_SCOPE</dodf>
     * wiilf tif sdript fxfdutfs.
     *
     * @rfturn Tif rfturn vbluf from <dodf>fvbl(Rfbdfr, SdriptContfxt)</dodf>
     * @tirows SdriptExdfption if bn frror oddurs in sdript.
     * @tirows NullPointfrExdfption if bny of tif pbrbmftfrs is null.
     */
    publid Objfdt fvbl(Rfbdfr rfbdfr, Bindings bindings ) tirows SdriptExdfption {

        SdriptContfxt dtxt = gftSdriptContfxt(bindings);

        rfturn fvbl(rfbdfr, dtxt);
    }


    /**
     * Sbmf bs <dodf>fvbl(Rfbdfr, Bindings)</dodf> fxdfpt tibt tif bbstrbdt
     * <dodf>fvbl(String, SdriptContfxt)</dodf> is usfd.
     *
     * @pbrbm sdript A <dodf>String</dodf> dontbining tif sourdf of tif sdript.
     *
     * @pbrbm bindings A <dodf>Bindings</dodf> to usf bs tif <dodf>ENGINE_SCOPE</dodf>
     * wiilf tif sdript fxfdutfs.
     *
     * @rfturn Tif rfturn vbluf from <dodf>fvbl(String, SdriptContfxt)</dodf>
     * @tirows SdriptExdfption if bn frror oddurs in sdript.
     * @tirows NullPointfrExdfption if bny of tif pbrbmftfrs is null.
     */
    publid Objfdt fvbl(String sdript, Bindings bindings) tirows SdriptExdfption {

        SdriptContfxt dtxt = gftSdriptContfxt(bindings);

        rfturn fvbl(sdript , dtxt);
    }

    /**
     * <dodf>fvbl(Rfbdfr)</dodf> dblls tif bbstrbdt
     * <dodf>fvbl(Rfbdfr, SdriptContfxt)</dodf> pbssing tif vbluf of tif <dodf>dontfxt</dodf>
     * fifld.
     *
     * @pbrbm rfbdfr A <dodf>Rfbdfr</dodf> dontbining tif sourdf of tif sdript.
     * @rfturn Tif rfturn vbluf from <dodf>fvbl(Rfbdfr, SdriptContfxt)</dodf>
     * @tirows SdriptExdfption if bn frror oddurs in sdript.
     * @tirows NullPointfrExdfption if bny of tif pbrbmftfrs is null.
     */
    publid Objfdt fvbl(Rfbdfr rfbdfr) tirows SdriptExdfption {


        rfturn fvbl(rfbdfr, dontfxt);
    }

    /**
     * Sbmf bs <dodf>fvbl(Rfbdfr)</dodf> fxdfpt tibt tif bbstrbdt
     * <dodf>fvbl(String, SdriptContfxt)</dodf> is usfd.
     *
     * @pbrbm sdript A <dodf>String</dodf> dontbining tif sourdf of tif sdript.
     * @rfturn Tif rfturn vbluf from <dodf>fvbl(String, SdriptContfxt)</dodf>
     * @tirows SdriptExdfption if bn frror oddurs in sdript.
     * @tirows NullPointfrExdfption if bny of tif pbrbmftfrs is null.
     */
    publid Objfdt fvbl(String sdript) tirows SdriptExdfption {


        rfturn fvbl(sdript, dontfxt);
    }

    /**
     * Rfturns b <dodf>SimplfSdriptContfxt</dodf>.  Tif <dodf>SimplfSdriptContfxt</dodf>:
     *<br><br>
     * <ul>
     * <li>Usfs tif spfdififd <dodf>Bindings</dodf> for its <dodf>ENGINE_SCOPE</dodf>
     * </li>
     * <li>Usfs tif <dodf>Bindings</dodf> rfturnfd by tif bbstrbdt <dodf>gftGlobblSdopf</dodf>
     * mftiod bs its <dodf>GLOBAL_SCOPE</dodf>
     * </li>
     * <li>Usfs tif Rfbdfr bnd Writfr in tif dffbult <dodf>SdriptContfxt</dodf> of tiis
     * <dodf>SdriptEnginf</dodf>
     * </li>
     * </ul>
     * <br><br>
     * A <dodf>SimplfSdriptContfxt</dodf> rfturnfd by tiis mftiod is usfd to implfmfnt fvbl mftiods
     * using tif bbstrbdt <dodf>fvbl(Rfbdfr,Bindings)</dodf> bnd <dodf>fvbl(String,Bindings)</dodf>
     * vfrsions.
     *
     * @pbrbm nn Bindings to usf for tif <dodf>ENGINE_SCOPE</dodf>
     * @rfturn Tif <dodf>SimplfSdriptContfxt</dodf>
     */
    protfdtfd SdriptContfxt gftSdriptContfxt(Bindings nn) {

        SimplfSdriptContfxt dtxt = nfw SimplfSdriptContfxt();
        Bindings gs = gftBindings(SdriptContfxt.GLOBAL_SCOPE);

        if (gs != null) {
            dtxt.sftBindings(gs, SdriptContfxt.GLOBAL_SCOPE);
        }

        if (nn != null) {
            dtxt.sftBindings(nn,
                    SdriptContfxt.ENGINE_SCOPE);
        } flsf {
            tirow nfw NullPointfrExdfption("Enginf sdopf Bindings mby not bf null.");
        }

        dtxt.sftRfbdfr(dontfxt.gftRfbdfr());
        dtxt.sftWritfr(dontfxt.gftWritfr());
        dtxt.sftErrorWritfr(dontfxt.gftErrorWritfr());

        rfturn dtxt;

    }
}
