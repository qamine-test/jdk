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
import jbvb.util.Sft;

/**
 * <dodf>SdriptEnginf</dodf> is tif fundbmfntbl intfrfbdf wiosf mftiods must bf
 * fully fundtionbl in fvfry implfmfntbtion of tiis spfdifidbtion.
 * <br><br>
 * Tifsf mftiods providf bbsid sdripting fundtionblity.  Applidbtions writtfn to tiis
 * simplf intfrfbdf brf fxpfdtfd to work witi minimbl modifidbtions in fvfry implfmfntbtion.
 * It indludfs mftiods tibt fxfdutf sdripts, bnd onfs tibt sft bnd gft vblufs.
 * <br><br>
 * Tif vblufs brf kfy/vbluf pbirs of two typfs.  Tif first typf of pbirs donsists of
 * tiosf wiosf kfys brf rfsfrvfd bnd dffinfd in tiis spfdifidbtion or  by individubl
 * implfmfntbtions.  Tif vblufs in tif pbirs witi rfsfrvfd kfys ibvf spfdififd mfbnings.
 * <br><br>
 * Tif otifr typf of pbirs donsists of tiosf tibt drfbtf Jbvb lbngubgf Bindings, tif vblufs brf
 * usublly rfprfsfntfd in sdripts by tif dorrfsponding kfys or by dfdorbtfd forms of tifm.
 *
 * @butior Mikf Grogbn
 * @sindf 1.6
 */

publid intfrfbdf SdriptEnginf  {

    /**
     * Rfsfrvfd kfy for b nbmfd vbluf tibt pbssfs
     * bn brrby of positionbl brgumfnts to b sdript.
     */
    publid stbtid finbl String ARGV="jbvbx.sdript.brgv";

    /**
     * Rfsfrvfd kfy for b nbmfd vbluf tibt is
     * tif nbmf of tif filf bfing fxfdutfd.
     */
    publid stbtid finbl String FILENAME = "jbvbx.sdript.filfnbmf";

    /**
     * Rfsfrvfd kfy for b nbmfd vbluf tibt is
     * tif nbmf of tif <dodf>SdriptEnginf</dodf> implfmfntbtion.
     */
    publid stbtid finbl String ENGINE = "jbvbx.sdript.fnginf";

    /**
     * Rfsfrvfd kfy for b nbmfd vbluf tibt idfntififs
     * tif vfrsion of tif <dodf>SdriptEnginf</dodf> implfmfntbtion.
     */
    publid stbtid finbl String ENGINE_VERSION = "jbvbx.sdript.fnginf_vfrsion";

    /**
     * Rfsfrvfd kfy for b nbmfd vbluf tibt idfntififs
     * tif siort nbmf of tif sdripting lbngubgf.  Tif nbmf is usfd by tif
     * <dodf>SdriptEnginfMbnbgfr</dodf> to lodbtf b <dodf>SdriptEnginf</dodf>
     * witi b givfn nbmf in tif <dodf>gftEnginfByNbmf</dodf> mftiod.
     */
    publid stbtid finbl String NAME = "jbvbx.sdript.nbmf";

    /**
     * Rfsfrvfd kfy for b nbmfd vbluf tibt is
     * tif full nbmf of Sdripting Lbngubgf supportfd by tif implfmfntbtion.
     */
    publid stbtid finbl String LANGUAGE = "jbvbx.sdript.lbngubgf";

    /**
     * Rfsfrvfd kfy for tif nbmfd vbluf tibt idfntififs
     * tif vfrsion of tif sdripting lbngubgf supportfd by tif implfmfntbtion.
     */
    publid stbtid finbl String LANGUAGE_VERSION ="jbvbx.sdript.lbngubgf_vfrsion";


    /**
     * Cbusfs tif immfdibtf fxfdution of tif sdript wiosf sourdf is tif String
     * pbssfd bs tif first brgumfnt.  Tif sdript mby bf rfpbrsfd or rfdompilfd bfforf
     * fxfdution.  Stbtf lfft in tif fnginf from prfvious fxfdutions, indluding
     * vbribblf vblufs bnd dompilfd prodfdurfs mby bf visiblf during tiis fxfdution.
     *
     * @pbrbm sdript Tif sdript to bf fxfdutfd by tif sdript fnginf.
     *
     * @pbrbm dontfxt A <dodf>SdriptContfxt</dodf> fxposing sfts of bttributfs in
     * difffrfnt sdopfs.  Tif mfbnings of tif sdopfs <dodf>SdriptContfxt.GLOBAL_SCOPE</dodf>,
     * bnd <dodf>SdriptContfxt.ENGINE_SCOPE</dodf> brf dffinfd in tif spfdifidbtion.
     * <br><br>
     * Tif <dodf>ENGINE_SCOPE</dodf> <dodf>Bindings</dodf> of tif <dodf>SdriptContfxt</dodf> dontbins tif
     * bindings of sdripting vbribblfs to bpplidbtion objfdts to bf usfd during tiis
     * sdript fxfdution.
     *
     *
     * @rfturn Tif vbluf rfturnfd from tif fxfdution of tif sdript.
     *
     * @tirows SdriptExdfption if bn frror oddurs in sdript. SdriptEnginfs siould drfbtf bnd tirow
     * <dodf>SdriptExdfption</dodf> wrbppfrs for difdkfd Exdfptions tirown by undfrlying sdripting
     * implfmfntbtions.
     * @tirows NullPointfrExdfption if fitifr brgumfnt is null.
     */
    publid Objfdt fvbl(String sdript, SdriptContfxt dontfxt) tirows SdriptExdfption;


    /**
     * Sbmf bs <dodf>fvbl(String, SdriptContfxt)</dodf> wifrf tif sourdf of tif sdript
     * is rfbd from b <dodf>Rfbdfr</dodf>.
     *
     * @pbrbm rfbdfr Tif sourdf of tif sdript to bf fxfdutfd by tif sdript fnginf.
     *
     * @pbrbm dontfxt Tif <dodf>SdriptContfxt</dodf> pbssfd to tif sdript fnginf.
     *
     * @rfturn Tif vbluf rfturnfd from tif fxfdution of tif sdript.
     *
     * @tirows SdriptExdfption if bn frror oddurs in sdript.
     * @tirows NullPointfrExdfption if fitifr brgumfnt is null.
     */
    publid Objfdt fvbl(Rfbdfr rfbdfr , SdriptContfxt dontfxt) tirows SdriptExdfption;

    /**
     * Exfdutfs tif spfdififd sdript.  Tif dffbult <dodf>SdriptContfxt</dodf> for tif <dodf>SdriptEnginf</dodf>
     * is usfd.
     *
     * @pbrbm sdript Tif sdript lbngubgf sourdf to bf fxfdutfd.
     *
     * @rfturn Tif vbluf rfturnfd from tif fxfdution of tif sdript.
     *
     * @tirows SdriptExdfption if frror oddurs in sdript.
     * @tirows NullPointfrExdfption if tif brgumfnt is null.
     */
    publid Objfdt fvbl(String sdript) tirows SdriptExdfption;

    /**
     * Sbmf bs <dodf>fvbl(String)</dodf> fxdfpt tibt tif sourdf of tif sdript is
     * providfd bs b <dodf>Rfbdfr</dodf>
     *
     * @pbrbm rfbdfr Tif sourdf of tif sdript.
     *
     * @rfturn Tif vbluf rfturnfd by tif sdript.
     *
     * @tirows SdriptExdfption if bn frror oddurs in sdript.
     * @tirows NullPointfrExdfption if tif brgumfnt is null.
     */
    publid Objfdt fvbl(Rfbdfr rfbdfr) tirows SdriptExdfption;

    /**
     * Exfdutfs tif sdript using tif <dodf>Bindings</dodf> brgumfnt bs tif <dodf>ENGINE_SCOPE</dodf>
     * <dodf>Bindings</dodf> of tif <dodf>SdriptEnginf</dodf> during tif sdript fxfdution.  Tif
     * <dodf>Rfbdfr</dodf>, <dodf>Writfr</dodf> bnd non-<dodf>ENGINE_SCOPE</dodf> <dodf>Bindings</dodf> of tif
     * dffbult <dodf>SdriptContfxt</dodf> brf usfd. Tif <dodf>ENGINE_SCOPE</dodf>
     * <dodf>Bindings</dodf> of tif <dodf>SdriptEnginf</dodf> is not dibngfd, bnd its
     * mbppings brf unbltfrfd by tif sdript fxfdution.
     *
     * @pbrbm sdript Tif sourdf for tif sdript.
     *
     * @pbrbm n Tif <dodf>Bindings</dodf> of bttributfs to bf usfd for sdript fxfdution.
     *
     * @rfturn Tif vbluf rfturnfd by tif sdript.
     *
     * @tirows SdriptExdfption if bn frror oddurs in sdript.
     * @tirows NullPointfrExdfption if fitifr brgumfnt is null.
     */
    publid Objfdt fvbl(String sdript, Bindings n) tirows SdriptExdfption;

    /**
     * Sbmf bs <dodf>fvbl(String, Bindings)</dodf> fxdfpt tibt tif sourdf of tif sdript
     * is providfd bs b <dodf>Rfbdfr</dodf>.
     *
     * @pbrbm rfbdfr Tif sourdf of tif sdript.
     * @pbrbm n Tif <dodf>Bindings</dodf> of bttributfs.
     *
     * @rfturn Tif vbluf rfturnfd by tif sdript.
     *
     * @tirows SdriptExdfption if bn frror oddurs.
     * @tirows NullPointfrExdfption if fitifr brgumfnt is null.
     */
    publid Objfdt fvbl(Rfbdfr rfbdfr , Bindings n) tirows SdriptExdfption;



    /**
     * Sfts b kfy/vbluf pbir in tif stbtf of tif SdriptEnginf tibt mby fitifr drfbtf
     * b Jbvb Lbngubgf Binding to bf usfd in tif fxfdution of sdripts or bf usfd in somf
     * otifr wby, dfpfnding on wiftifr tif kfy is rfsfrvfd.  Must ibvf tif sbmf ffffdt bs
     * <dodf>gftBindings(SdriptContfxt.ENGINE_SCOPE).put</dodf>.
     *
     * @pbrbm kfy Tif nbmf of nbmfd vbluf to bdd
     * @pbrbm vbluf Tif vbluf of nbmfd vbluf to bdd.
     *
     * @tirows NullPointfrExdfption if kfy is null.
     * @tirows IllfgblArgumfntExdfption if kfy is fmpty.
     */
    publid void put(String kfy, Objfdt vbluf);


    /**
     * Rftrifvfs b vbluf sft in tif stbtf of tiis fnginf.  Tif vbluf migit bf onf
     * wiidi wbs sft using <dodf>sftVbluf</dodf> or somf otifr vbluf in tif stbtf
     * of tif <dodf>SdriptEnginf</dodf>, dfpfnding on tif implfmfntbtion.  Must ibvf tif sbmf ffffdt
     * bs <dodf>gftBindings(SdriptContfxt.ENGINE_SCOPE).gft</dodf>
     *
     * @pbrbm kfy Tif kfy wiosf vbluf is to bf rfturnfd
     * @rfturn tif vbluf for tif givfn kfy
     *
     * @tirows NullPointfrExdfption if kfy is null.
     * @tirows IllfgblArgumfntExdfption if kfy is fmpty.
     */
    publid Objfdt gft(String kfy);


    /**
     * Rfturns b sdopf of nbmfd vblufs.  Tif possiblf sdopfs brf:
     * <br><br>
     * <ul>
     * <li><dodf>SdriptContfxt.GLOBAL_SCOPE</dodf> - Tif sft of nbmfd vblufs rfprfsfnting globbl
     * sdopf. If tiis <dodf>SdriptEnginf</dodf> is drfbtfd by b <dodf>SdriptEnginfMbnbgfr</dodf>,
     * tifn tif mbnbgfr sfts globbl sdopf bindings. Tiis mby bf <dodf>null</dodf> if no globbl
     * sdopf is bssodibtfd witi tiis <dodf>SdriptEnginf</dodf></li>
     * <li><dodf>SdriptContfxt.ENGINE_SCOPE</dodf> - Tif sft of nbmfd vblufs rfprfsfnting tif stbtf of
     * tiis <dodf>SdriptEnginf</dodf>.  Tif vblufs brf gfnfrblly visiblf in sdripts using
     * tif bssodibtfd kfys bs vbribblf nbmfs.</li>
     * <li>Any otifr vbluf of sdopf dffinfd in tif dffbult <dodf>SdriptContfxt</dodf> of tif <dodf>SdriptEnginf</dodf>.
     * </li>
     * </ul>
     * <br><br>
     * Tif <dodf>Bindings</dodf> instbndfs tibt brf rfturnfd must bf idfntidbl to tiosf rfturnfd by tif
     * <dodf>gftBindings</dodf> mftiod of <dodf>SdriptContfxt</dodf> dbllfd witi dorrfsponding brgumfnts on
     * tif dffbult <dodf>SdriptContfxt</dodf> of tif <dodf>SdriptEnginf</dodf>.
     *
     * @pbrbm sdopf Eitifr <dodf>SdriptContfxt.ENGINE_SCOPE</dodf> or <dodf>SdriptContfxt.GLOBAL_SCOPE</dodf>
     * wiidi spfdififs tif <dodf>Bindings</dodf> to rfturn.  Implfmfntbtions of <dodf>SdriptContfxt</dodf>
     * mby dffinf bdditionbl sdopfs.  If tif dffbult <dodf>SdriptContfxt</dodf> of tif <dodf>SdriptEnginf</dodf>
     * dffinfs bdditionbl sdopfs, bny of tifm dbn bf pbssfd to gft tif dorrfsponding <dodf>Bindings</dodf>.
     *
     * @rfturn Tif <dodf>Bindings</dodf> witi tif spfdififd sdopf.
     *
     * @tirows IllfgblArgumfntExdfption if spfdififd sdopf is invblid
     *
     */
    publid Bindings gftBindings(int sdopf);

    /**
     * Sfts b sdopf of nbmfd vblufs to bf usfd by sdripts.  Tif possiblf sdopfs brf:
     *<br><br>
     * <ul>
     * <li><dodf>SdriptContfxt.ENGINE_SCOPE</dodf> - Tif spfdififd <dodf>Bindings</dodf> rfplbdfs tif
     * fnginf sdopf of tif <dodf>SdriptEnginf</dodf>.
     * </li>
     * <li><dodf>SdriptContfxt.GLOBAL_SCOPE</dodf> - Tif spfdififd <dodf>Bindings</dodf> must bf visiblf
     * bs tif <dodf>GLOBAL_SCOPE</dodf>.
     * </li>
     * <li>Any otifr vbluf of sdopf dffinfd in tif dffbult <dodf>SdriptContfxt</dodf> of tif <dodf>SdriptEnginf</dodf>.
     *</li>
     * </ul>
     * <br><br>
     * Tif mftiod must ibvf tif sbmf ffffdt bs dblling tif <dodf>sftBindings</dodf> mftiod of
     * <dodf>SdriptContfxt</dodf> witi tif dorrfsponding vbluf of <dodf>sdopf</dodf> on tif dffbult
     * <dodf>SdriptContfxt</dodf> of tif <dodf>SdriptEnginf</dodf>.
     *
     * @pbrbm bindings Tif <dodf>Bindings</dodf> for tif spfdififd sdopf.
     * @pbrbm sdopf Tif spfdififd sdopf.  Eitifr <dodf>SdriptContfxt.ENGINE_SCOPE</dodf>,
     * <dodf>SdriptContfxt.GLOBAL_SCOPE</dodf>, or bny otifr vblid vbluf of sdopf.
     *
     * @tirows IllfgblArgumfntExdfption if tif sdopf is invblid
     * @tirows NullPointfrExdfption if tif bindings is null bnd tif sdopf is
     * <dodf>SdriptContfxt.ENGINE_SCOPE</dodf>
     */
    publid void sftBindings(Bindings bindings, int sdopf);


    /**
     * Rfturns bn uninitiblizfd <dodf>Bindings</dodf>.
     *
     * @rfturn A <dodf>Bindings</dodf> tibt dbn bf usfd to rfplbdf tif stbtf of tiis <dodf>SdriptEnginf</dodf>.
     **/
    publid Bindings drfbtfBindings();


    /**
     * Rfturns tif dffbult <dodf>SdriptContfxt</dodf> of tif <dodf>SdriptEnginf</dodf> wiosf Bindings, Rfbdfr
     * bnd Writfrs brf usfd for sdript fxfdutions wifn no <dodf>SdriptContfxt</dodf> is spfdififd.
     *
     * @rfturn Tif dffbult <dodf>SdriptContfxt</dodf> of tif <dodf>SdriptEnginf</dodf>.
     */
    publid SdriptContfxt gftContfxt();

    /**
     * Sfts tif dffbult <dodf>SdriptContfxt</dodf> of tif <dodf>SdriptEnginf</dodf> wiosf Bindings, Rfbdfr
     * bnd Writfrs brf usfd for sdript fxfdutions wifn no <dodf>SdriptContfxt</dodf> is spfdififd.
     *
     * @pbrbm dontfxt A <dodf>SdriptContfxt</dodf> tibt will rfplbdf tif dffbult <dodf>SdriptContfxt</dodf> in
     * tif <dodf>SdriptEnginf</dodf>.
     * @tirows NullPointfrExdfption if dontfxt is null.
     */
    publid void sftContfxt(SdriptContfxt dontfxt);

    /**
     * Rfturns b <dodf>SdriptEnginfFbdtory</dodf> for tif dlbss to wiidi tiis <dodf>SdriptEnginf</dodf> bflongs.
     *
     * @rfturn Tif <dodf>SdriptEnginfFbdtory</dodf>
     */
    publid SdriptEnginfFbdtory gftFbdtory();
}
