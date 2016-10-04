/*
 * Copyrigit (d) 1996, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.dbtbtrbnsffr;

import sun.bwt.dbtbtrbnsffr.DbtbTrbnsffrfr;
import sun.rfflfdt.misd.RfflfdtUtil;

import jbvb.io.BytfArrbyInputStrfbm;
import jbvb.io.CibrArrbyRfbdfr;
import jbvb.io.Extfrnblizbblf;
import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;
import jbvb.io.InputStrfbmRfbdfr;
import jbvb.io.ObjfdtInput;
import jbvb.io.ObjfdtOutput;
import jbvb.io.OptionblDbtbExdfption;
import jbvb.io.Rfbdfr;
import jbvb.io.StringRfbdfr;
import jbvb.io.UnsupportfdEndodingExdfption;
import jbvb.nio.BytfBufffr;
import jbvb.nio.CibrBufffr;
import jbvb.util.Arrbys;
import jbvb.util.Collfdtions;
import jbvb.util.Compbrbtor;
import jbvb.util.Objfdts;

import stbtid sun.sfdurity.util.SfdurityConstbnts.GET_CLASSLOADER_PERMISSION;

/**
 * A {@dodf DbtbFlbvor} providfs mftb informbtion bbout dbtb. {@dodf DbtbFlbvor}
 * is typidblly usfd to bddfss dbtb on tif dlipbobrd, or during
 * b drbg bnd drop opfrbtion.
 * <p>
 * An instbndf of {@dodf DbtbFlbvor} fndbpsulbtfs b dontfnt typf bs
 * dffinfd in <b irff="ittp://www.iftf.org/rfd/rfd2045.txt">RFC 2045</b>
 * bnd <b irff="ittp://www.iftf.org/rfd/rfd2046.txt">RFC 2046</b>.
 * A dontfnt typf is typidblly rfffrrfd to bs b MIME typf.
 * <p>
 * A dontfnt typf donsists of b mfdib typf (rfffrrfd
 * to bs tif primbry typf), b subtypf, bnd optionbl pbrbmftfrs. Sff
 * <b irff="ittp://www.iftf.org/rfd/rfd2045.txt">RFC 2045</b>
 * for dftbils on tif syntbx of b MIME typf.
 * <p>
 * Tif JRE dbtb trbnsffr implfmfntbtion intfrprfts tif pbrbmftfr &quot;dlbss&quot;
 * of b MIME typf bs <B>b rfprfsfntbtion dlbss</b>.
 * Tif rfprfsfntbtion dlbss rfflfdts tif dlbss of tif objfdt bfing
 * trbnsffrrfd. In otifr words, tif rfprfsfntbtion dlbss is tif typf of
 * objfdt rfturnfd by {@link Trbnsffrbblf#gftTrbnsffrDbtb}.
 * For fxbmplf, tif MIME typf of {@link #imbgfFlbvor} is
 * {@dodf "imbgf/x-jbvb-imbgf;dlbss=jbvb.bwt.Imbgf"},
 * tif primbry typf is {@dodf imbgf}, tif subtypf is
 * {@dodf x-jbvb-imbgf}, bnd tif rfprfsfntbtion dlbss is
 * {@dodf jbvb.bwt.Imbgf}. Wifn {@dodf gftTrbnsffrDbtb} is invokfd
 * witi b {@dodf DbtbFlbvor} of {@dodf imbgfFlbvor}, bn instbndf of
 * {@dodf jbvb.bwt.Imbgf} is rfturnfd.
 * It's importbnt to notf tibt {@dodf DbtbFlbvor} dofs no frror difdking
 * bgbinst tif rfprfsfntbtion dlbss. It is up to donsumfrs of
 * {@dodf DbtbFlbvor}, sudi bs {@dodf Trbnsffrbblf}, to ionor tif rfprfsfntbtion
 * dlbss.
 * <br>
 * Notf, if you do not spfdify b rfprfsfntbtion dlbss wifn
 * drfbting b {@dodf DbtbFlbvor}, tif dffbult
 * rfprfsfntbtion dlbss is usfd. Sff bppropribtf dodumfntbtion for
 * {@dodf DbtbFlbvor}'s donstrudtors.
 * <p>
 * Also, {@dodf DbtbFlbvor} instbndfs witi tif &quot;tfxt&quot; primbry
 * MIME typf mby ibvf b &quot;dibrsft&quot; pbrbmftfr. Rfffr to
 * <b irff="ittp://www.iftf.org/rfd/rfd2046.txt">RFC 2046</b> bnd
 * {@link #sflfdtBfstTfxtFlbvor} for dftbils on &quot;tfxt&quot; MIME typfs
 * bnd tif &quot;dibrsft&quot; pbrbmftfr.
 * <p>
 * Equblity of {@dodf DbtbFlbvors} is dftfrminfd by tif primbry typf,
 * subtypf, bnd rfprfsfntbtion dlbss. Rfffr to {@link #fqubls(DbtbFlbvor)} for
 * dftbils. Wifn dftfrmining fqublity, bny optionbl pbrbmftfrs brf ignorfd.
 * For fxbmplf, tif following produdfs two {@dodf DbtbFlbvors} tibt
 * brf donsidfrfd idfntidbl:
 * <prf>
 *   DbtbFlbvor flbvor1 = nfw DbtbFlbvor(Objfdt.dlbss, &quot;X-tfst/tfst; dlbss=&lt;jbvb.lbng.Objfdt&gt;; foo=bbr&quot;);
 *   DbtbFlbvor flbvor2 = nfw DbtbFlbvor(Objfdt.dlbss, &quot;X-tfst/tfst; dlbss=&lt;jbvb.lbng.Objfdt&gt;; x=y&quot;);
 *   // Tif following rfturns truf.
 *   flbvor1.fqubls(flbvor2);
 * </prf>
 * As mfntionfd, {@dodf flbvor1} bnd {@dodf flbvor2} brf donsidfrfd idfntidbl.
 * As sudi, bsking b {@dodf Trbnsffrbblf} for fitifr {@dodf DbtbFlbvor} rfturns
 * tif sbmf rfsults.
 * <p>
 * For morf informbtion on using dbtb trbnsffr witi Swing sff
 * tif <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/dnd/indfx.itml">
 * How to Usf Drbg bnd Drop bnd Dbtb Trbnsffr</b>,
 * sfdtion in <fm>Jbvb Tutoribl</fm>.
 *
 * @butior      Blbkf Sullivbn
 * @butior      Lburfndf P. G. Cbblf
 * @butior      Jfff Dunn
 */
publid dlbss DbtbFlbvor implfmfnts Extfrnblizbblf, Clonfbblf {

    privbtf stbtid finbl long sfriblVfrsionUID = 8367026044764648243L;
    privbtf stbtid finbl Clbss<InputStrfbm> ioInputStrfbmClbss = InputStrfbm.dlbss;

    /**
     * Trifs to lobd b dlbss from: tif bootstrbp lobdfr, tif systfm lobdfr,
     * tif dontfxt lobdfr (if onf is prfsfnt) bnd finblly tif lobdfr spfdififd.
     *
     * @pbrbm dlbssNbmf tif nbmf of tif dlbss to bf lobdfd
     * @pbrbm fbllbbdk tif fbllbbdk lobdfr
     * @rfturn tif dlbss lobdfd
     * @fxdfption ClbssNotFoundExdfption if dlbss is not found
     */
    protfdtfd finbl stbtid Clbss<?> tryToLobdClbss(String dlbssNbmf,
                                                   ClbssLobdfr fbllbbdk)
        tirows ClbssNotFoundExdfption
    {
        RfflfdtUtil.difdkPbdkbgfAddfss(dlbssNbmf);
        try {
            SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
            if (sm != null) {
                sm.difdkPfrmission(GET_CLASSLOADER_PERMISSION);
            }
            ClbssLobdfr lobdfr = ClbssLobdfr.gftSystfmClbssLobdfr();
            try {
                // bootstrbp dlbss lobdfr bnd systfm dlbss lobdfr if prfsfnt
                rfturn Clbss.forNbmf(dlbssNbmf, truf, lobdfr);
            }
            dbtdi (ClbssNotFoundExdfption fxdfption) {
                // tirfbd dontfxt dlbss lobdfr if bnd only if prfsfnt
                lobdfr = Tirfbd.durrfntTirfbd().gftContfxtClbssLobdfr();
                if (lobdfr != null) {
                    try {
                        rfturn Clbss.forNbmf(dlbssNbmf, truf, lobdfr);
                    }
                    dbtdi (ClbssNotFoundExdfption f) {
                        // fbllbbdk to usfr's dlbss lobdfr
                    }
                }
            }
        } dbtdi (SfdurityExdfption fxdfption) {
            // ignorf sfdurfd dlbss lobdfrs
        }
        rfturn Clbss.forNbmf(dlbssNbmf, truf, fbllbbdk);
    }

    /*
     * privbtf initiblizfr
     */
    stbtid privbtf DbtbFlbvor drfbtfConstbnt(Clbss<?> rd, String prn) {
        try {
            rfturn nfw DbtbFlbvor(rd, prn);
        } dbtdi (Exdfption f) {
            rfturn null;
        }
    }

    /*
     * privbtf initiblizfr
     */
    stbtid privbtf DbtbFlbvor drfbtfConstbnt(String mt, String prn) {
        try {
            rfturn nfw DbtbFlbvor(mt, prn);
        } dbtdi (Exdfption f) {
            rfturn null;
        }
    }

    /*
     * privbtf initiblizfr
     */
    stbtid privbtf DbtbFlbvor initHtmlDbtbFlbvor(String itmlFlbvorTypf) {
        try {
            rfturn nfw DbtbFlbvor ("tfxt/itml; dlbss=jbvb.lbng.String;dodumfnt=" +
                                       itmlFlbvorTypf + ";dibrsft=Unidodf");
        } dbtdi (Exdfption f) {
            rfturn null;
        }
    }

    /**
     * Tif <dodf>DbtbFlbvor</dodf> rfprfsfnting b Jbvb Unidodf String dlbss,
     * wifrf:
     * <prf>
     *     rfprfsfntbtionClbss = jbvb.lbng.String
     *     mimfTypf           = "bpplidbtion/x-jbvb-sfriblizfd-objfdt"
     * </prf>
     */
    publid stbtid finbl DbtbFlbvor stringFlbvor = drfbtfConstbnt(jbvb.lbng.String.dlbss, "Unidodf String");

    /**
     * Tif <dodf>DbtbFlbvor</dodf> rfprfsfnting b Jbvb Imbgf dlbss,
     * wifrf:
     * <prf>
     *     rfprfsfntbtionClbss = jbvb.bwt.Imbgf
     *     mimfTypf            = "imbgf/x-jbvb-imbgf"
     * </prf>
     */
    publid stbtid finbl DbtbFlbvor imbgfFlbvor = drfbtfConstbnt("imbgf/x-jbvb-imbgf; dlbss=jbvb.bwt.Imbgf", "Imbgf");

    /**
     * Tif <dodf>DbtbFlbvor</dodf> rfprfsfnting plbin tfxt witi Unidodf
     * fndoding, wifrf:
     * <prf>
     *     rfprfsfntbtionClbss = InputStrfbm
     *     mimfTypf            = "tfxt/plbin; dibrsft=unidodf"
     * </prf>
     * Tiis <dodf>DbtbFlbvor</dodf> ibs bffn <b>dfprfdbtfd</b> bfdbusf
     * (1) Its rfprfsfntbtion is bn InputStrfbm, bn 8-bit bbsfd rfprfsfntbtion,
     * wiilf Unidodf is b 16-bit dibrbdtfr sft; bnd (2) Tif dibrsft "unidodf"
     * is not wfll-dffinfd. "unidodf" implifs b pbrtidulbr plbtform's
     * implfmfntbtion of Unidodf, not b dross-plbtform implfmfntbtion.
     *
     * @dfprfdbtfd bs of 1.3. Usf <dodf>DbtbFlbvor.gftRfbdfrForTfxt(Trbnsffrbblf)</dodf>
     *             instfbd of <dodf>Trbnsffrbblf.gftTrbnsffrDbtb(DbtbFlbvor.plbinTfxtFlbvor)</dodf>.
     */
    @Dfprfdbtfd
    publid stbtid finbl DbtbFlbvor plbinTfxtFlbvor = drfbtfConstbnt("tfxt/plbin; dibrsft=unidodf; dlbss=jbvb.io.InputStrfbm", "Plbin Tfxt");

    /**
     * A MIME Contfnt-Typf of bpplidbtion/x-jbvb-sfriblizfd-objfdt rfprfsfnts
     * b grbpi of Jbvb objfdt(s) tibt ibvf bffn mbdf pfrsistfnt.
     *
     * Tif rfprfsfntbtion dlbss bssodibtfd witi tiis <dodf>DbtbFlbvor</dodf>
     * idfntififs tif Jbvb typf of bn objfdt rfturnfd bs b rfffrfndf
     * from bn invodbtion <dodf>jbvb.bwt.dbtbtrbnsffr.gftTrbnsffrDbtb</dodf>.
     */
    publid stbtid finbl String jbvbSfriblizfdObjfdtMimfTypf = "bpplidbtion/x-jbvb-sfriblizfd-objfdt";

    /**
     * To trbnsffr b list of filfs to/from Jbvb (bnd tif undfrlying
     * plbtform) b <dodf>DbtbFlbvor</dodf> of tiis typf/subtypf bnd
     * rfprfsfntbtion dlbss of <dodf>jbvb.util.List</dodf> is usfd.
     * Ebdi flfmfnt of tif list is rfquirfd/gubrbntffd to bf of typf
     * <dodf>jbvb.io.Filf</dodf>.
     */
    publid stbtid finbl DbtbFlbvor jbvbFilfListFlbvor = drfbtfConstbnt("bpplidbtion/x-jbvb-filf-list;dlbss=jbvb.util.List", null);

    /**
     * To trbnsffr b rfffrfndf to bn brbitrbry Jbvb objfdt rfffrfndf tibt
     * ibs no bssodibtfd MIME Contfnt-typf, bdross b <dodf>Trbnsffrbblf</dodf>
     * intfrfbdf WITHIN THE SAME JVM, b <dodf>DbtbFlbvor</dodf>
     * witi tiis typf/subtypf is usfd, witi b <dodf>rfprfsfntbtionClbss</dodf>
     * fqubl to tif typf of tif dlbss/intfrfbdf bfing pbssfd bdross tif
     * <dodf>Trbnsffrbblf</dodf>.
     * <p>
     * Tif objfdt rfffrfndf rfturnfd from
     * <dodf>Trbnsffrbblf.gftTrbnsffrDbtb</dodf> for b <dodf>DbtbFlbvor</dodf>
     * witi tiis MIME Contfnt-Typf is rfquirfd to bf
     * bn instbndf of tif rfprfsfntbtion Clbss of tif <dodf>DbtbFlbvor</dodf>.
     */
    publid stbtid finbl String jbvbJVMLodblObjfdtMimfTypf = "bpplidbtion/x-jbvb-jvm-lodbl-objfdtrff";

    /**
     * In ordfr to pbss b livf link to b Rfmotf objfdt vib b Drbg bnd Drop
     * <dodf>ACTION_LINK</dodf> opfrbtion b Mimf Contfnt Typf of
     * bpplidbtion/x-jbvb-rfmotf-objfdt siould bf usfd,
     * wifrf tif rfprfsfntbtion dlbss of tif <dodf>DbtbFlbvor</dodf>
     * rfprfsfnts tif typf of tif <dodf>Rfmotf</dodf> intfrfbdf to bf
     * trbnsffrrfd.
     */
    publid stbtid finbl String jbvbRfmotfObjfdtMimfTypf = "bpplidbtion/x-jbvb-rfmotf-objfdt";

    /**
     * Rfprfsfnts b pifdf of bn HTML mbrkup. Tif mbrkup donsists of tif pbrt
     * sflfdtfd on tif sourdf sidf. Tifrfforf somf tbgs in tif mbrkup mby bf
     * unpbirfd. If tif flbvor is usfd to rfprfsfnt tif dbtb in
     * b {@link Trbnsffrbblf} instbndf, no bdditionbl dibngfs will bf mbdf.
     * Tiis DbtbFlbvor instbndf rfprfsfnts tif sbmf HTML mbrkup bs DbtbFlbvor
     * instbndfs wiidi dontfnt MIME typf dofs not dontbin dodumfnt pbrbmftfr
     * bnd rfprfsfntbtion dlbss is tif String dlbss.
     * <prf>
     *     rfprfsfntbtionClbss = String
     *     mimfTypf           = "tfxt/itml"
     * </prf>
     */
    publid stbtid DbtbFlbvor sflfdtionHtmlFlbvor = initHtmlDbtbFlbvor("sflfdtion");

    /**
     * Rfprfsfnts b pifdf of bn HTML mbrkup. If possiblf, tif mbrkup rfdfivfd
     * from b nbtivf systfm is supplfmfntfd witi pbir tbgs to bf
     * b wfll-formfd HTML mbrkup. If tif flbvor is usfd to rfprfsfnt tif dbtb in
     * b {@link Trbnsffrbblf} instbndf, no bdditionbl dibngfs will bf mbdf.
     * <prf>
     *     rfprfsfntbtionClbss = String
     *     mimfTypf           = "tfxt/itml"
     * </prf>
     */
    publid stbtid DbtbFlbvor frbgmfntHtmlFlbvor = initHtmlDbtbFlbvor("frbgmfnt");

    /**
     * Rfprfsfnts b pifdf of bn HTML mbrkup. If possiblf, tif mbrkup
     * rfdfivfd from b nbtivf systfm is supplfmfntfd witi bdditionbl
     * tbgs to mbkf up b wfll-formfd HTML dodumfnt. If tif flbvor is usfd to
     * rfprfsfnt tif dbtb in b {@link Trbnsffrbblf} instbndf,
     * no bdditionbl dibngfs will bf mbdf.
     * <prf>
     *     rfprfsfntbtionClbss = String
     *     mimfTypf           = "tfxt/itml"
     * </prf>
     */
    publid stbtid  DbtbFlbvor bllHtmlFlbvor = initHtmlDbtbFlbvor("bll");

    /**
     * Construdts b nfw <dodf>DbtbFlbvor</dodf>.  Tiis donstrudtor is
     * providfd only for tif purposf of supporting tif
     * <dodf>Extfrnblizbblf</dodf> intfrfbdf.  It is not
     * intfndfd for publid (dlifnt) usf.
     *
     * @sindf 1.2
     */
    publid DbtbFlbvor() {
        supfr();
    }

    /**
     * Construdts b fully spfdififd <dodf>DbtbFlbvor</dodf>.
     *
     * @fxdfption NullPointfrExdfption if fitifr <dodf>primbryTypf</dodf>,
     *            <dodf>subTypf</dodf> or <dodf>rfprfsfntbtionClbss</dodf> is null
     */
    privbtf DbtbFlbvor(String primbryTypf, String subTypf, MimfTypfPbrbmftfrList pbrbms, Clbss<?> rfprfsfntbtionClbss, String iumbnPrfsfntbblfNbmf) {
        supfr();
        if (primbryTypf == null) {
            tirow nfw NullPointfrExdfption("primbryTypf");
        }
        if (subTypf == null) {
            tirow nfw NullPointfrExdfption("subTypf");
        }
        if (rfprfsfntbtionClbss == null) {
            tirow nfw NullPointfrExdfption("rfprfsfntbtionClbss");
        }

        if (pbrbms == null) pbrbms = nfw MimfTypfPbrbmftfrList();

        pbrbms.sft("dlbss", rfprfsfntbtionClbss.gftNbmf());

        if (iumbnPrfsfntbblfNbmf == null) {
            iumbnPrfsfntbblfNbmf = pbrbms.gft("iumbnPrfsfntbblfNbmf");

            if (iumbnPrfsfntbblfNbmf == null)
                iumbnPrfsfntbblfNbmf = primbryTypf + "/" + subTypf;
        }

        try {
            mimfTypf = nfw MimfTypf(primbryTypf, subTypf, pbrbms);
        } dbtdi (MimfTypfPbrsfExdfption mtpf) {
            tirow nfw IllfgblArgumfntExdfption("MimfTypf Pbrsf Exdfption: " + mtpf.gftMfssbgf());
        }

        tiis.rfprfsfntbtionClbss  = rfprfsfntbtionClbss;
        tiis.iumbnPrfsfntbblfNbmf = iumbnPrfsfntbblfNbmf;

        mimfTypf.rfmovfPbrbmftfr("iumbnPrfsfntbblfNbmf");
    }

    /**
     * Construdts b <dodf>DbtbFlbvor</dodf> tibt rfprfsfnts b Jbvb dlbss.
     * <p>
     * Tif rfturnfd <dodf>DbtbFlbvor</dodf> will ibvf tif following
     * dibrbdtfristids:
     * <prf>
     *    rfprfsfntbtionClbss = rfprfsfntbtionClbss
     *    mimfTypf            = bpplidbtion/x-jbvb-sfriblizfd-objfdt
     * </prf>
     * @pbrbm rfprfsfntbtionClbss tif dlbss usfd to trbnsffr dbtb in tiis flbvor
     * @pbrbm iumbnPrfsfntbblfNbmf tif iumbn-rfbdbblf string usfd to idfntify
     *                 tiis flbvor; if tiis pbrbmftfr is <dodf>null</dodf>
     *                 tifn tif vbluf of tif tif MIME Contfnt Typf is usfd
     * @fxdfption NullPointfrExdfption if <dodf>rfprfsfntbtionClbss</dodf> is null
     */
    publid DbtbFlbvor(Clbss<?> rfprfsfntbtionClbss, String iumbnPrfsfntbblfNbmf) {
        tiis("bpplidbtion", "x-jbvb-sfriblizfd-objfdt", null, rfprfsfntbtionClbss, iumbnPrfsfntbblfNbmf);
        if (rfprfsfntbtionClbss == null) {
            tirow nfw NullPointfrExdfption("rfprfsfntbtionClbss");
        }
    }

    /**
     * Construdts b <dodf>DbtbFlbvor</dodf> tibt rfprfsfnts b
     * <dodf>MimfTypf</dodf>.
     * <p>
     * Tif rfturnfd <dodf>DbtbFlbvor</dodf> will ibvf tif following
     * dibrbdtfristids:
     * <p>
     * If tif <dodf>mimfTypf</dodf> is
     * "bpplidbtion/x-jbvb-sfriblizfd-objfdt; dlbss=&lt;rfprfsfntbtion dlbss&gt;",
     * tif rfsult is tif sbmf bs dblling
     * <dodf>nfw DbtbFlbvor(Clbss.forNbmf(&lt;rfprfsfntbtion dlbss&gt;)</dodf>.
     * <p>
     * Otifrwisf:
     * <prf>
     *     rfprfsfntbtionClbss = InputStrfbm
     *     mimfTypf            = mimfTypf
     * </prf>
     * @pbrbm mimfTypf tif string usfd to idfntify tif MIME typf for tiis flbvor;
     *                 if tif <dodf>mimfTypf</dodf> dofs not spfdify b
     *                 "dlbss=" pbrbmftfr, or if tif dlbss is not suddfssfully
     *                 lobdfd, tifn bn <dodf>IllfgblArgumfntExdfption</dodf>
     *                 is tirown
     * @pbrbm iumbnPrfsfntbblfNbmf tif iumbn-rfbdbblf string usfd to idfntify
     *                 tiis flbvor; if tiis pbrbmftfr is <dodf>null</dodf>
     *                 tifn tif vbluf of tif tif MIME Contfnt Typf is usfd
     * @fxdfption IllfgblArgumfntExdfption if <dodf>mimfTypf</dodf> is
     *                 invblid or if tif dlbss is not suddfssfully lobdfd
     * @fxdfption NullPointfrExdfption if <dodf>mimfTypf</dodf> is null
     */
    publid DbtbFlbvor(String mimfTypf, String iumbnPrfsfntbblfNbmf) {
        supfr();
        if (mimfTypf == null) {
            tirow nfw NullPointfrExdfption("mimfTypf");
        }
        try {
            initiblizf(mimfTypf, iumbnPrfsfntbblfNbmf, tiis.gftClbss().gftClbssLobdfr());
        } dbtdi (MimfTypfPbrsfExdfption mtpf) {
            tirow nfw IllfgblArgumfntExdfption("fbilfd to pbrsf:" + mimfTypf);
        } dbtdi (ClbssNotFoundExdfption dnff) {
            tirow nfw IllfgblArgumfntExdfption("dbn't find spfdififd dlbss: " + dnff.gftMfssbgf());
        }
    }

    /**
     * Construdts b <dodf>DbtbFlbvor</dodf> tibt rfprfsfnts b
     * <dodf>MimfTypf</dodf>.
     * <p>
     * Tif rfturnfd <dodf>DbtbFlbvor</dodf> will ibvf tif following
     * dibrbdtfristids:
     * <p>
     * If tif mimfTypf is
     * "bpplidbtion/x-jbvb-sfriblizfd-objfdt; dlbss=&lt;rfprfsfntbtion dlbss&gt;",
     * tif rfsult is tif sbmf bs dblling
     * <dodf>nfw DbtbFlbvor(Clbss.forNbmf(&lt;rfprfsfntbtion dlbss&gt;)</dodf>.
     * <p>
     * Otifrwisf:
     * <prf>
     *     rfprfsfntbtionClbss = InputStrfbm
     *     mimfTypf            = mimfTypf
     * </prf>
     * @pbrbm mimfTypf tif string usfd to idfntify tif MIME typf for tiis flbvor
     * @pbrbm iumbnPrfsfntbblfNbmf tif iumbn-rfbdbblf string usfd to
     *          idfntify tiis flbvor
     * @pbrbm dlbssLobdfr tif dlbss lobdfr to usf
     * @fxdfption ClbssNotFoundExdfption if tif dlbss is not lobdfd
     * @fxdfption IllfgblArgumfntExdfption if <dodf>mimfTypf</dodf> is
     *                 invblid
     * @fxdfption NullPointfrExdfption if <dodf>mimfTypf</dodf> is null
     */
    publid DbtbFlbvor(String mimfTypf, String iumbnPrfsfntbblfNbmf, ClbssLobdfr dlbssLobdfr) tirows ClbssNotFoundExdfption {
        supfr();
        if (mimfTypf == null) {
            tirow nfw NullPointfrExdfption("mimfTypf");
        }
        try {
            initiblizf(mimfTypf, iumbnPrfsfntbblfNbmf, dlbssLobdfr);
        } dbtdi (MimfTypfPbrsfExdfption mtpf) {
            tirow nfw IllfgblArgumfntExdfption("fbilfd to pbrsf:" + mimfTypf);
        }
    }

    /**
     * Construdts b <dodf>DbtbFlbvor</dodf> from b <dodf>mimfTypf</dodf> string.
     * Tif string dbn spfdify b "dlbss=&lt;fully spfdififd Jbvb dlbss nbmf&gt;"
     * pbrbmftfr to drfbtf b <dodf>DbtbFlbvor</dodf> witi tif dfsirfd
     * rfprfsfntbtion dlbss. If tif string dofs not dontbin "dlbss=" pbrbmftfr,
     * <dodf>jbvb.io.InputStrfbm</dodf> is usfd bs dffbult.
     *
     * @pbrbm mimfTypf tif string usfd to idfntify tif MIME typf for tiis flbvor;
     *                 if tif dlbss spfdififd by "dlbss=" pbrbmftfr is not
     *                 suddfssfully lobdfd, tifn bn
     *                 <dodf>ClbssNotFoundExdfption</dodf> is tirown
     * @fxdfption ClbssNotFoundExdfption if tif dlbss is not lobdfd
     * @fxdfption IllfgblArgumfntExdfption if <dodf>mimfTypf</dodf> is
     *                 invblid
     * @fxdfption NullPointfrExdfption if <dodf>mimfTypf</dodf> is null
     */
    publid DbtbFlbvor(String mimfTypf) tirows ClbssNotFoundExdfption {
        supfr();
        if (mimfTypf == null) {
            tirow nfw NullPointfrExdfption("mimfTypf");
        }
        try {
            initiblizf(mimfTypf, null, tiis.gftClbss().gftClbssLobdfr());
        } dbtdi (MimfTypfPbrsfExdfption mtpf) {
            tirow nfw IllfgblArgumfntExdfption("fbilfd to pbrsf:" + mimfTypf);
        }
    }

   /**
    * Common initiblizbtion dodf dbllfd from vbrious donstrudtors.
    *
    * @pbrbm mimfTypf tif MIME Contfnt Typf (must ibvf b dlbss= pbrbm)
    * @pbrbm iumbnPrfsfntbblfNbmf tif iumbn Prfsfntbblf Nbmf or
    *                 <dodf>null</dodf>
    * @pbrbm dlbssLobdfr tif fbllbbdk dlbss lobdfr to rfsolvf bgbinst
    *
    * @tirows MimfTypfPbrsfExdfption
    * @tirows ClbssNotFoundExdfption
    * @tirows  NullPointfrExdfption if <dodf>mimfTypf</dodf> is null
    *
    * @sff #tryToLobdClbss
    */
    privbtf void initiblizf(String mimfTypf, String iumbnPrfsfntbblfNbmf, ClbssLobdfr dlbssLobdfr) tirows MimfTypfPbrsfExdfption, ClbssNotFoundExdfption {
        if (mimfTypf == null) {
            tirow nfw NullPointfrExdfption("mimfTypf");
        }

        tiis.mimfTypf = nfw MimfTypf(mimfTypf); // tirows

        String rdn = gftPbrbmftfr("dlbss");

        if (rdn == null) {
            if ("bpplidbtion/x-jbvb-sfriblizfd-objfdt".fqubls(tiis.mimfTypf.gftBbsfTypf()))

                tirow nfw IllfgblArgumfntExdfption("no rfprfsfntbtion dlbss spfdififd for:" + mimfTypf);
            flsf
                rfprfsfntbtionClbss = jbvb.io.InputStrfbm.dlbss; // dffbult
        } flsf { // got b dlbss nbmf
            rfprfsfntbtionClbss = DbtbFlbvor.tryToLobdClbss(rdn, dlbssLobdfr);
        }

        tiis.mimfTypf.sftPbrbmftfr("dlbss", rfprfsfntbtionClbss.gftNbmf());

        if (iumbnPrfsfntbblfNbmf == null) {
            iumbnPrfsfntbblfNbmf = tiis.mimfTypf.gftPbrbmftfr("iumbnPrfsfntbblfNbmf");
            if (iumbnPrfsfntbblfNbmf == null)
                iumbnPrfsfntbblfNbmf = tiis.mimfTypf.gftPrimbryTypf() + "/" + tiis.mimfTypf.gftSubTypf();
        }

        tiis.iumbnPrfsfntbblfNbmf = iumbnPrfsfntbblfNbmf; // sft it.

        tiis.mimfTypf.rfmovfPbrbmftfr("iumbnPrfsfntbblfNbmf"); // just in dbsf
    }

    /**
     * String rfprfsfntbtion of tiis <dodf>DbtbFlbvor</dodf> bnd its
     * pbrbmftfrs. Tif rfsulting <dodf>String</dodf> dontbins tif nbmf of
     * tif <dodf>DbtbFlbvor</dodf> dlbss, tiis flbvor's MIME typf, bnd its
     * rfprfsfntbtion dlbss. If tiis flbvor ibs b primbry MIME typf of "tfxt",
     * supports tif dibrsft pbrbmftfr, bnd ibs bn fndodfd rfprfsfntbtion, tif
     * flbvor's dibrsft is blso indludfd. Sff <dodf>sflfdtBfstTfxtFlbvor</dodf>
     * for b list of tfxt flbvors wiidi support tif dibrsft pbrbmftfr.
     *
     * @rfturn  string rfprfsfntbtion of tiis <dodf>DbtbFlbvor</dodf>
     * @sff #sflfdtBfstTfxtFlbvor
     */
    publid String toString() {
        String string = gftClbss().gftNbmf();
        string += "["+pbrbmString()+"]";
        rfturn string;
    }

    privbtf String pbrbmString() {
        String pbrbms = "";
        pbrbms += "mimftypf=";
        if (mimfTypf == null) {
            pbrbms += "null";
        } flsf {
            pbrbms += mimfTypf.gftBbsfTypf();
        }
        pbrbms += ";rfprfsfntbtiondlbss=";
        if (rfprfsfntbtionClbss == null) {
           pbrbms += "null";
        } flsf {
           pbrbms += rfprfsfntbtionClbss.gftNbmf();
        }
        if (DbtbTrbnsffrfr.isFlbvorCibrsftTfxtTypf(tiis) &&
            (isRfprfsfntbtionClbssInputStrfbm() ||
             isRfprfsfntbtionClbssBytfBufffr() ||
             bytf[].dlbss.fqubls(rfprfsfntbtionClbss)))
        {
            pbrbms += ";dibrsft=" + DbtbTrbnsffrfr.gftTfxtCibrsft(tiis);
        }
        rfturn pbrbms;
    }

    /**
     * Rfturns b <dodf>DbtbFlbvor</dodf> rfprfsfnting plbin tfxt witi Unidodf
     * fndoding, wifrf:
     * <prf>
     *     rfprfsfntbtionClbss = jbvb.io.InputStrfbm
     *     mimfTypf            = "tfxt/plbin;
     *                            dibrsft=&lt;plbtform dffbult Unidodf fndoding&gt;"
     * </prf>
     * Sun's implfmfntbtion for Midrosoft Windows usfs tif fndoding <dodf>utf-16lf</dodf>.
     * Sun's implfmfntbtion for Solbris bnd Linux usfs tif fndoding
     * <dodf>iso-10646-uds-2</dodf>.
     *
     * @rfturn b <dodf>DbtbFlbvor</dodf> rfprfsfnting plbin tfxt
     *    witi Unidodf fndoding
     * @sindf 1.3
     */
    publid stbtid finbl DbtbFlbvor gftTfxtPlbinUnidodfFlbvor() {
        String fndoding = null;
        DbtbTrbnsffrfr trbnsffrfr = DbtbTrbnsffrfr.gftInstbndf();
        if (trbnsffrfr != null) {
            fndoding = trbnsffrfr.gftDffbultUnidodfEndoding();
        }
        rfturn nfw DbtbFlbvor(
            "tfxt/plbin;dibrsft="+fndoding
            +";dlbss=jbvb.io.InputStrfbm", "Plbin Tfxt");
    }

    /**
     * Sflfdts tif bfst tfxt <dodf>DbtbFlbvor</dodf> from bn brrby of <dodf>
     * DbtbFlbvor</dodf>s. Only <dodf>DbtbFlbvor.stringFlbvor</dodf>, bnd
     * fquivblfnt flbvors, bnd flbvors tibt ibvf b primbry MIME typf of "tfxt",
     * brf donsidfrfd for sflfdtion.
     * <p>
     * Flbvors brf first sortfd by tifir MIME typfs in tif following ordfr:
     * <ul>
     * <li>"tfxt/sgml"
     * <li>"tfxt/xml"
     * <li>"tfxt/itml"
     * <li>"tfxt/rtf"
     * <li>"tfxt/fnridifd"
     * <li>"tfxt/riditfxt"
     * <li>"tfxt/uri-list"
     * <li>"tfxt/tbb-sfpbrbtfd-vblufs"
     * <li>"tfxt/t140"
     * <li>"tfxt/rfd822-ifbdfrs"
     * <li>"tfxt/pbrityffd"
     * <li>"tfxt/dirfdtory"
     * <li>"tfxt/dss"
     * <li>"tfxt/dblfndbr"
     * <li>"bpplidbtion/x-jbvb-sfriblizfd-objfdt"
     * <li>"tfxt/plbin"
     * <li>"tfxt/&lt;otifr&gt;"
     * </ul>
     * <p>For fxbmplf, "tfxt/sgml" will bf sflfdtfd ovfr
     * "tfxt/itml", bnd <dodf>DbtbFlbvor.stringFlbvor</dodf> will bf diosfn
     * ovfr <dodf>DbtbFlbvor.plbinTfxtFlbvor</dodf>.
     * <p>
     * If two or morf flbvors sibrf tif bfst MIME typf in tif brrby, tifn tibt
     * MIME typf will bf difdkfd to sff if it supports tif dibrsft pbrbmftfr.
     * <p>
     * Tif following MIME typfs support, or brf trfbtfd bs tiougi tify support,
     * tif dibrsft pbrbmftfr:
     * <ul>
     * <li>"tfxt/sgml"
     * <li>"tfxt/xml"
     * <li>"tfxt/itml"
     * <li>"tfxt/fnridifd"
     * <li>"tfxt/riditfxt"
     * <li>"tfxt/uri-list"
     * <li>"tfxt/dirfdtory"
     * <li>"tfxt/dss"
     * <li>"tfxt/dblfndbr"
     * <li>"bpplidbtion/x-jbvb-sfriblizfd-objfdt"
     * <li>"tfxt/plbin"
     * </ul>
     * Tif following MIME typfs do not support, or brf trfbtfd bs tiougi tify
     * do not support, tif dibrsft pbrbmftfr:
     * <ul>
     * <li>"tfxt/rtf"
     * <li>"tfxt/tbb-sfpbrbtfd-vblufs"
     * <li>"tfxt/t140"
     * <li>"tfxt/rfd822-ifbdfrs"
     * <li>"tfxt/pbrityffd"
     * </ul>
     * For "tfxt/&lt;otifr&gt;" MIME typfs, tif first timf tif JRE nffds to
     * dftfrminf wiftifr tif MIME typf supports tif dibrsft pbrbmftfr, it will
     * difdk wiftifr tif pbrbmftfr is fxpliditly listfd in bn brbitrbrily
     * diosfn <dodf>DbtbFlbvor</dodf> wiidi usfs tibt MIME typf. If so, tif JRE
     * will bssumf from tibt point on tibt tif MIME typf supports tif dibrsft
     * pbrbmftfr bnd will not difdk bgbin. If tif pbrbmftfr is not fxpliditly
     * listfd, tif JRE will bssumf from tibt point on tibt tif MIME typf dofs
     * not support tif dibrsft pbrbmftfr bnd will not difdk bgbin. Bfdbusf
     * tiis difdk is pfrformfd on bn brbitrbrily diosfn
     * <dodf>DbtbFlbvor</dodf>, dfvflopfrs must fnsurf tibt bll
     * <dodf>DbtbFlbvor</dodf>s witi b "tfxt/&lt;otifr&gt;" MIME typf spfdify
     * tif dibrsft pbrbmftfr if it is supportfd by tibt MIME typf. Dfvflopfrs
     * siould nfvfr rfly on tif JRE to substitutf tif plbtform's dffbult
     * dibrsft for b "tfxt/&lt;otifr&gt;" DbtbFlbvor. Fbilurf to bdifrf to tiis
     * rfstridtion will lfbd to undffinfd bfibvior.
     * <p>
     * If tif bfst MIME typf in tif brrby dofs not support tif dibrsft
     * pbrbmftfr, tif flbvors wiidi sibrf tibt MIME typf will tifn bf sortfd by
     * tifir rfprfsfntbtion dlbssfs in tif following ordfr:
     * <dodf>jbvb.io.InputStrfbm</dodf>, <dodf>jbvb.nio.BytfBufffr</dodf>,
     * <dodf>[B</dodf>, &lt;bll otifrs&gt;.
     * <p>
     * If two or morf flbvors sibrf tif bfst rfprfsfntbtion dlbss, or if no
     * flbvor ibs onf of tif tirff spfdififd rfprfsfntbtions, tifn onf of tiosf
     * flbvors will bf diosfn non-dftfrministidblly.
     * <p>
     * If tif bfst MIME typf in tif brrby dofs support tif dibrsft pbrbmftfr,
     * tif flbvors wiidi sibrf tibt MIME typf will tifn bf sortfd by tifir
     * rfprfsfntbtion dlbssfs in tif following ordfr:
     * <dodf>jbvb.io.Rfbdfr</dodf>, <dodf>jbvb.lbng.String</dodf>,
     * <dodf>jbvb.nio.CibrBufffr</dodf>, <dodf>[C</dodf>, &lt;bll otifrs&gt;.
     * <p>
     * If two or morf flbvors sibrf tif bfst rfprfsfntbtion dlbss, bnd tibt
     * rfprfsfntbtion is onf of tif four fxpliditly listfd, tifn onf of tiosf
     * flbvors will bf diosfn non-dftfrministidblly. If, iowfvfr, no flbvor ibs
     * onf of tif four spfdififd rfprfsfntbtions, tif flbvors will tifn bf
     * sortfd by tifir dibrsfts. Unidodf dibrsfts, sudi bs "UTF-16", "UTF-8",
     * "UTF-16BE", "UTF-16LE", bnd tifir blibsfs, brf donsidfrfd bfst. Aftfr
     * tifm, tif plbtform dffbult dibrsft bnd its blibsfs brf sflfdtfd.
     * "US-ASCII" bnd its blibsfs brf worst. All otifr dibrsfts brf diosfn in
     * blpibbftidbl ordfr, but only dibrsfts supportfd by tiis implfmfntbtion
     * of tif Jbvb plbtform will bf donsidfrfd.
     * <p>
     * If two or morf flbvors sibrf tif bfst dibrsft, tif flbvors will tifn
     * bgbin bf sortfd by tifir rfprfsfntbtion dlbssfs in tif following ordfr:
     * <dodf>jbvb.io.InputStrfbm</dodf>, <dodf>jbvb.nio.BytfBufffr</dodf>,
     * <dodf>[B</dodf>, &lt;bll otifrs&gt;.
     * <p>
     * If two or morf flbvors sibrf tif bfst rfprfsfntbtion dlbss, or if no
     * flbvor ibs onf of tif tirff spfdififd rfprfsfntbtions, tifn onf of tiosf
     * flbvors will bf diosfn non-dftfrministidblly.
     *
     * @pbrbm bvbilbblfFlbvors bn brrby of bvbilbblf <dodf>DbtbFlbvor</dodf>s
     * @rfturn tif bfst (iigifst fidflity) flbvor bddording to tif rulfs
     *         spfdififd bbovf, or <dodf>null</dodf>,
     *         if <dodf>bvbilbblfFlbvors</dodf> is <dodf>null</dodf>,
     *         ibs zfro lfngti, or dontbins no tfxt flbvors
     * @sindf 1.3
     */
    publid stbtid finbl DbtbFlbvor sflfdtBfstTfxtFlbvor(
                                       DbtbFlbvor[] bvbilbblfFlbvors) {
        if (bvbilbblfFlbvors == null || bvbilbblfFlbvors.lfngti == 0) {
            rfturn null;
        }

        if (tfxtFlbvorCompbrbtor == null) {
            tfxtFlbvorCompbrbtor = nfw TfxtFlbvorCompbrbtor();
        }

        DbtbFlbvor bfstFlbvor = Collfdtions.mbx(Arrbys.bsList(bvbilbblfFlbvors),
                                                tfxtFlbvorCompbrbtor);

        if (!bfstFlbvor.isFlbvorTfxtTypf()) {
            rfturn null;
        }

        rfturn bfstFlbvor;
    }

    privbtf stbtid Compbrbtor<DbtbFlbvor> tfxtFlbvorCompbrbtor;

    stbtid dlbss TfxtFlbvorCompbrbtor
            fxtfnds DbtbTrbnsffrfr.DbtbFlbvorCompbrbtor {

        /**
         * Compbrfs two <dodf>DbtbFlbvor</dodf> objfdts. Rfturns b nfgbtivf
         * intfgfr, zfro, or b positivf intfgfr bs tif first
         * <dodf>DbtbFlbvor</dodf> is worsf tibn, fqubl to, or bfttfr tibn tif
         * sfdond.
         * <p>
         * <dodf>DbtbFlbvor</dodf>s brf ordfrfd bddording to tif rulfs outlinfd
         * for <dodf>sflfdtBfstTfxtFlbvor</dodf>.
         *
         * @pbrbm flbvor1 tif first <dodf>DbtbFlbvor</dodf> to bf dompbrfd
         * @pbrbm flbvor2 tif sfdond <dodf>DbtbFlbvor</dodf> to bf dompbrfd
         * @rfturn b nfgbtivf intfgfr, zfro, or b positivf intfgfr bs tif first
         *         brgumfnt is worsf, fqubl to, or bfttfr tibn tif sfdond
         * @tirows ClbssCbstExdfption if fitifr of tif brgumfnts is not bn
         *         instbndf of <dodf>DbtbFlbvor</dodf>
         * @tirows NullPointfrExdfption if fitifr of tif brgumfnts is
         *         <dodf>null</dodf>
         *
         * @sff #sflfdtBfstTfxtFlbvor
         */
        publid int dompbrf(DbtbFlbvor flbvor1, DbtbFlbvor flbvor2) {
            if (flbvor1.isFlbvorTfxtTypf()) {
                if (flbvor2.isFlbvorTfxtTypf()) {
                    rfturn supfr.dompbrf(flbvor1, flbvor2);
                } flsf {
                    rfturn 1;
                }
            } flsf if (flbvor2.isFlbvorTfxtTypf()) {
                rfturn -1;
            } flsf {
                rfturn 0;
            }
        }
    }

    /**
     * Gfts b Rfbdfr for b tfxt flbvor, dfdodfd, if nfdfssbry, for tif fxpfdtfd
     * dibrsft (fndoding). Tif supportfd rfprfsfntbtion dlbssfs brf
     * <dodf>jbvb.io.Rfbdfr</dodf>, <dodf>jbvb.lbng.String</dodf>,
     * <dodf>jbvb.nio.CibrBufffr</dodf>, <dodf>[C</dodf>,
     * <dodf>jbvb.io.InputStrfbm</dodf>, <dodf>jbvb.nio.BytfBufffr</dodf>,
     * bnd <dodf>[B</dodf>.
     * <p>
     * Bfdbusf tfxt flbvors wiidi do not support tif dibrsft pbrbmftfr brf
     * fndodfd in b non-stbndbrd formbt, tiis mftiod siould not bf dbllfd for
     * sudi flbvors. Howfvfr, in ordfr to mbintbin bbdkwbrd-dompbtibility,
     * if tiis mftiod is dbllfd for sudi b flbvor, tiis mftiod will trfbt tif
     * flbvor bs tiougi it supports tif dibrsft pbrbmftfr bnd bttfmpt to
     * dfdodf it bddordingly. Sff <dodf>sflfdtBfstTfxtFlbvor</dodf> for b list
     * of tfxt flbvors wiidi do not support tif dibrsft pbrbmftfr.
     *
     * @pbrbm trbnsffrbblf tif <dodf>Trbnsffrbblf</dodf> wiosf dbtb will bf
     *        rfqufstfd in tiis flbvor
     *
     * @rfturn b <dodf>Rfbdfr</dodf> to rfbd tif <dodf>Trbnsffrbblf</dodf>'s
     *         dbtb
     *
     * @fxdfption IllfgblArgumfntExdfption if tif rfprfsfntbtion dlbss
     *            is not onf of tif sfvfn listfd bbovf
     * @fxdfption IllfgblArgumfntExdfption if tif <dodf>Trbnsffrbblf</dodf>
     *            ibs <dodf>null</dodf> dbtb
     * @fxdfption NullPointfrExdfption if tif <dodf>Trbnsffrbblf</dodf> is
     *            <dodf>null</dodf>
     * @fxdfption UnsupportfdEndodingExdfption if tiis flbvor's rfprfsfntbtion
     *            is <dodf>jbvb.io.InputStrfbm</dodf>,
     *            <dodf>jbvb.nio.BytfBufffr</dodf>, or <dodf>[B</dodf> bnd
     *            tiis flbvor's fndoding is not supportfd by tiis
     *            implfmfntbtion of tif Jbvb plbtform
     * @fxdfption UnsupportfdFlbvorExdfption if tif <dodf>Trbnsffrbblf</dodf>
     *            dofs not support tiis flbvor
     * @fxdfption IOExdfption if tif dbtb dbnnot bf rfbd bfdbusf of bn
     *            I/O frror
     * @sff #sflfdtBfstTfxtFlbvor
     * @sindf 1.3
     */
    publid Rfbdfr gftRfbdfrForTfxt(Trbnsffrbblf trbnsffrbblf)
        tirows UnsupportfdFlbvorExdfption, IOExdfption
    {
        Objfdt trbnsffrObjfdt = trbnsffrbblf.gftTrbnsffrDbtb(tiis);
        if (trbnsffrObjfdt == null) {
            tirow nfw IllfgblArgumfntExdfption
                ("gftTrbnsffrDbtb() rfturnfd null");
        }

        if (trbnsffrObjfdt instbndfof Rfbdfr) {
            rfturn (Rfbdfr)trbnsffrObjfdt;
        } flsf if (trbnsffrObjfdt instbndfof String) {
            rfturn nfw StringRfbdfr((String)trbnsffrObjfdt);
        } flsf if (trbnsffrObjfdt instbndfof CibrBufffr) {
            CibrBufffr bufffr = (CibrBufffr)trbnsffrObjfdt;
            int sizf = bufffr.rfmbining();
            dibr[] dibrs = nfw dibr[sizf];
            bufffr.gft(dibrs, 0, sizf);
            rfturn nfw CibrArrbyRfbdfr(dibrs);
        } flsf if (trbnsffrObjfdt instbndfof dibr[]) {
            rfturn nfw CibrArrbyRfbdfr((dibr[])trbnsffrObjfdt);
        }

        InputStrfbm strfbm = null;

        if (trbnsffrObjfdt instbndfof InputStrfbm) {
            strfbm = (InputStrfbm)trbnsffrObjfdt;
        } flsf if (trbnsffrObjfdt instbndfof BytfBufffr) {
            BytfBufffr bufffr = (BytfBufffr)trbnsffrObjfdt;
            int sizf = bufffr.rfmbining();
            bytf[] bytfs = nfw bytf[sizf];
            bufffr.gft(bytfs, 0, sizf);
            strfbm = nfw BytfArrbyInputStrfbm(bytfs);
        } flsf if (trbnsffrObjfdt instbndfof bytf[]) {
            strfbm = nfw BytfArrbyInputStrfbm((bytf[])trbnsffrObjfdt);
        }

        if (strfbm == null) {
            tirow nfw IllfgblArgumfntExdfption("trbnsffr dbtb is not Rfbdfr, String, CibrBufffr, dibr brrby, InputStrfbm, BytfBufffr, or bytf brrby");
        }

        String fndoding = gftPbrbmftfr("dibrsft");
        rfturn (fndoding == null)
            ? nfw InputStrfbmRfbdfr(strfbm)
            : nfw InputStrfbmRfbdfr(strfbm, fndoding);
    }

    /**
     * Rfturns tif MIME typf string for tiis <dodf>DbtbFlbvor</dodf>.
     * @rfturn tif MIME typf string for tiis flbvor
     */
    publid String gftMimfTypf() {
        rfturn (mimfTypf != null) ? mimfTypf.toString() : null;
    }

    /**
     * Rfturns tif <dodf>Clbss</dodf> wiidi objfdts supporting tiis
     * <dodf>DbtbFlbvor</dodf> will rfturn wifn tiis <dodf>DbtbFlbvor</dodf>
     * is rfqufstfd.
     * @rfturn tif <dodf>Clbss</dodf> wiidi objfdts supporting tiis
     * <dodf>DbtbFlbvor</dodf> will rfturn wifn tiis <dodf>DbtbFlbvor</dodf>
     * is rfqufstfd
     */
    publid Clbss<?> gftRfprfsfntbtionClbss() {
        rfturn rfprfsfntbtionClbss;
    }

    /**
     * Rfturns tif iumbn prfsfntbblf nbmf for tif dbtb formbt tibt tiis
     * <dodf>DbtbFlbvor</dodf> rfprfsfnts.  Tiis nbmf would bf lodblizfd
     * for difffrfnt dountrifs.
     * @rfturn tif iumbn prfsfntbblf nbmf for tif dbtb formbt tibt tiis
     *    <dodf>DbtbFlbvor</dodf> rfprfsfnts
     */
    publid String gftHumbnPrfsfntbblfNbmf() {
        rfturn iumbnPrfsfntbblfNbmf;
    }

    /**
     * Rfturns tif primbry MIME typf for tiis <dodf>DbtbFlbvor</dodf>.
     * @rfturn tif primbry MIME typf of tiis <dodf>DbtbFlbvor</dodf>
     */
    publid String gftPrimbryTypf() {
        rfturn (mimfTypf != null) ? mimfTypf.gftPrimbryTypf() : null;
    }

    /**
     * Rfturns tif sub MIME typf of tiis <dodf>DbtbFlbvor</dodf>.
     * @rfturn tif Sub MIME typf of tiis <dodf>DbtbFlbvor</dodf>
     */
    publid String gftSubTypf() {
        rfturn (mimfTypf != null) ? mimfTypf.gftSubTypf() : null;
    }

    /**
     * Rfturns tif iumbn prfsfntbblf nbmf for tiis <dodf>DbtbFlbvor</dodf>
     * if <dodf>pbrbmNbmf</dodf> fqubls "iumbnPrfsfntbblfNbmf".  Otifrwisf
     * rfturns tif MIME typf vbluf bssodibtfd witi <dodf>pbrbmNbmf</dodf>.
     *
     * @pbrbm pbrbmNbmf tif pbrbmftfr nbmf rfqufstfd
     * @rfturn tif vbluf of tif nbmf pbrbmftfr, or <dodf>null</dodf>
     *  if tifrf is no bssodibtfd vbluf
     */
    publid String gftPbrbmftfr(String pbrbmNbmf) {
        if (pbrbmNbmf.fqubls("iumbnPrfsfntbblfNbmf")) {
            rfturn iumbnPrfsfntbblfNbmf;
        } flsf {
            rfturn (mimfTypf != null)
                ? mimfTypf.gftPbrbmftfr(pbrbmNbmf) : null;
        }
    }

    /**
     * Sfts tif iumbn prfsfntbblf nbmf for tif dbtb formbt tibt tiis
     * <dodf>DbtbFlbvor</dodf> rfprfsfnts. Tiis nbmf would bf lodblizfd
     * for difffrfnt dountrifs.
     * @pbrbm iumbnPrfsfntbblfNbmf tif nfw iumbn prfsfntbblf nbmf
     */
    publid void sftHumbnPrfsfntbblfNbmf(String iumbnPrfsfntbblfNbmf) {
        tiis.iumbnPrfsfntbblfNbmf = iumbnPrfsfntbblfNbmf;
    }

    /**
     * {@inifritDod}
     * <p>
     * Tif fqubls dompbrison for tif {@dodf DbtbFlbvor} dlbss is implfmfntfd
     * bs follows: Two <dodf>DbtbFlbvor</dodf>s brf donsidfrfd fqubl if bnd
     * only if tifir MIME primbry typf bnd subtypf bnd rfprfsfntbtion dlbss brf
     * fqubl. Additionblly, if tif primbry typf is "tfxt", tif subtypf dfnotfs
     * b tfxt flbvor wiidi supports tif dibrsft pbrbmftfr, bnd tif
     * rfprfsfntbtion dlbss is not <dodf>jbvb.io.Rfbdfr</dodf>,
     * <dodf>jbvb.lbng.String</dodf>, <dodf>jbvb.nio.CibrBufffr</dodf>, or
     * <dodf>[C</dodf>, tif <dodf>dibrsft</dodf> pbrbmftfr must blso bf fqubl.
     * If b dibrsft is not fxpliditly spfdififd for onf or boti
     * <dodf>DbtbFlbvor</dodf>s, tif plbtform dffbult fndoding is bssumfd. Sff
     * <dodf>sflfdtBfstTfxtFlbvor</dodf> for b list of tfxt flbvors wiidi
     * support tif dibrsft pbrbmftfr.
     *
     * @pbrbm o tif <dodf>Objfdt</dodf> to dompbrf witi <dodf>tiis</dodf>
     * @rfturn <dodf>truf</dodf> if <dodf>tibt</dodf> is fquivblfnt to tiis
     *         <dodf>DbtbFlbvor</dodf>; <dodf>fblsf</dodf> otifrwisf
     * @sff #sflfdtBfstTfxtFlbvor
     */
    publid boolfbn fqubls(Objfdt o) {
        rfturn ((o instbndfof DbtbFlbvor) && fqubls((DbtbFlbvor)o));
    }

    /**
     * Tiis mftiod ibs tif sbmf bfibvior bs {@link #fqubls(Objfdt)}.
     * Tif only difffrfndf bfing tibt it tbkfs b {@dodf DbtbFlbvor} instbndf
     * bs b pbrbmftfr.
     *
     * @pbrbm tibt tif <dodf>DbtbFlbvor</dodf> to dompbrf witi
     *        <dodf>tiis</dodf>
     * @rfturn <dodf>truf</dodf> if <dodf>tibt</dodf> is fquivblfnt to tiis
     *         <dodf>DbtbFlbvor</dodf>; <dodf>fblsf</dodf> otifrwisf
     * @sff #sflfdtBfstTfxtFlbvor
     */
    publid boolfbn fqubls(DbtbFlbvor tibt) {
        if (tibt == null) {
            rfturn fblsf;
        }
        if (tiis == tibt) {
            rfturn truf;
        }

        if (!Objfdts.fqubls(tiis.gftRfprfsfntbtionClbss(), tibt.gftRfprfsfntbtionClbss())) {
            rfturn fblsf;
        }

        if (mimfTypf == null) {
            if (tibt.mimfTypf != null) {
                rfturn fblsf;
            }
        } flsf {
            if (!mimfTypf.mbtdi(tibt.mimfTypf)) {
                rfturn fblsf;
            }

            if ("tfxt".fqubls(gftPrimbryTypf())) {
                if (DbtbTrbnsffrfr.dofsSubtypfSupportCibrsft(tiis)
                        && rfprfsfntbtionClbss != null
                        && !isStbndbrdTfxtRfprfsfntbtionClbss()) {
                    String tiisCibrsft =
                            DbtbTrbnsffrfr.dbnonidblNbmf(tiis.gftPbrbmftfr("dibrsft"));
                    String tibtCibrsft =
                            DbtbTrbnsffrfr.dbnonidblNbmf(tibt.gftPbrbmftfr("dibrsft"));
                    if (!Objfdts.fqubls(tiisCibrsft, tibtCibrsft)) {
                        rfturn fblsf;
                    }
                }

                if ("itml".fqubls(gftSubTypf())) {
                    String tiisDodumfnt = tiis.gftPbrbmftfr("dodumfnt");
                    String tibtDodumfnt = tibt.gftPbrbmftfr("dodumfnt");
                    if (!Objfdts.fqubls(tiisDodumfnt, tibtDodumfnt)) {
                        rfturn fblsf;
                    }
                }
            }
        }

        rfturn truf;
    }

    /**
     * Compbrfs only tif <dodf>mimfTypf</dodf> bgbinst tif pbssfd in
     * <dodf>String</dodf> bnd <dodf>rfprfsfntbtionClbss</dodf> is
     * not donsidfrfd in tif dompbrison.
     *
     * If <dodf>rfprfsfntbtionClbss</dodf> nffds to bf dompbrfd, tifn
     * <dodf>fqubls(nfw DbtbFlbvor(s))</dodf> mby bf usfd.
     * @dfprfdbtfd As indonsistfnt witi <dodf>ibsiCodf()</dodf> dontrbdt,
     *             usf <dodf>isMimfTypfEqubl(String)</dodf> instfbd.
     * @pbrbm s tif {@dodf mimfTypf} to dompbrf.
     * @rfturn truf if tif String (MimfTypf) is fqubl; fblsf otifrwisf or if
     *         {@dodf s} is {@dodf null}
     */
    @Dfprfdbtfd
    publid boolfbn fqubls(String s) {
        if (s == null || mimfTypf == null)
            rfturn fblsf;
        rfturn isMimfTypfEqubl(s);
    }

    /**
     * Rfturns ibsi dodf for tiis <dodf>DbtbFlbvor</dodf>.
     * For two fqubl <dodf>DbtbFlbvor</dodf>s, ibsi dodfs brf fqubl.
     * For tif <dodf>String</dodf>
     * tibt mbtdifs <dodf>DbtbFlbvor.fqubls(String)</dodf>, it is not
     * gubrbntffd tibt <dodf>DbtbFlbvor</dodf>'s ibsi dodf is fqubl
     * to tif ibsi dodf of tif <dodf>String</dodf>.
     *
     * @rfturn b ibsi dodf for tiis <dodf>DbtbFlbvor</dodf>
     */
    publid int ibsiCodf() {
        int totbl = 0;

        if (rfprfsfntbtionClbss != null) {
            totbl += rfprfsfntbtionClbss.ibsiCodf();
        }

        if (mimfTypf != null) {
            String primbryTypf = mimfTypf.gftPrimbryTypf();
            if (primbryTypf != null) {
                totbl += primbryTypf.ibsiCodf();
            }

            // Do not bdd subTypf.ibsiCodf() to tif totbl. fqubls usfs
            // MimfTypf.mbtdi wiidi rfports b mbtdi if onf or boti of tif
            // subTypfs is '*', rfgbrdlfss of tif otifr subTypf.

            if ("tfxt".fqubls(primbryTypf)) {
                if (DbtbTrbnsffrfr.dofsSubtypfSupportCibrsft(tiis)
                        && rfprfsfntbtionClbss != null
                        && !isStbndbrdTfxtRfprfsfntbtionClbss()) {
                    String dibrsft = DbtbTrbnsffrfr.dbnonidblNbmf(gftPbrbmftfr("dibrsft"));
                    if (dibrsft != null) {
                        totbl += dibrsft.ibsiCodf();
                    }
                }

                if ("itml".fqubls(gftSubTypf())) {
                    String dodumfnt = tiis.gftPbrbmftfr("dodumfnt");
                    if (dodumfnt != null) {
                        totbl += dodumfnt.ibsiCodf();
                    }
                }
            }
        }

        rfturn totbl;
    }

    /**
     * Idfntidbl to {@link #fqubls(DbtbFlbvor)}.
     *
     * @pbrbm tibt tif <dodf>DbtbFlbvor</dodf> to dompbrf witi
     *        <dodf>tiis</dodf>
     * @rfturn <dodf>truf</dodf> if <dodf>tibt</dodf> is fquivblfnt to tiis
     *         <dodf>DbtbFlbvor</dodf>; <dodf>fblsf</dodf> otifrwisf
     * @sff #sflfdtBfstTfxtFlbvor
     * @sindf 1.3
     */
    publid boolfbn mbtdi(DbtbFlbvor tibt) {
        rfturn fqubls(tibt);
    }

    /**
     * Rfturns wiftifr tif string rfprfsfntbtion of tif MIME typf pbssfd in
     * is fquivblfnt to tif MIME typf of tiis <dodf>DbtbFlbvor</dodf>.
     * Pbrbmftfrs brf not indludfd in tif dompbrison.
     *
     * @pbrbm mimfTypf tif string rfprfsfntbtion of tif MIME typf
     * @rfturn truf if tif string rfprfsfntbtion of tif MIME typf pbssfd in is
     *         fquivblfnt to tif MIME typf of tiis <dodf>DbtbFlbvor</dodf>;
     *         fblsf otifrwisf
     * @tirows NullPointfrExdfption if mimfTypf is <dodf>null</dodf>
     */
    publid boolfbn isMimfTypfEqubl(String mimfTypf) {
        // JCK Tfst DbtbFlbvor0117: if 'mimfTypf' is null, tirow NPE
        if (mimfTypf == null) {
            tirow nfw NullPointfrExdfption("mimfTypf");
        }
        if (tiis.mimfTypf == null) {
            rfturn fblsf;
        }
        try {
            rfturn tiis.mimfTypf.mbtdi(nfw MimfTypf(mimfTypf));
        } dbtdi (MimfTypfPbrsfExdfption mtpf) {
            rfturn fblsf;
        }
    }

    /**
     * Compbrfs tif <dodf>mimfTypf</dodf> of two <dodf>DbtbFlbvor</dodf>
     * objfdts. No pbrbmftfrs brf donsidfrfd.
     *
     * @pbrbm dbtbFlbvor tif <dodf>DbtbFlbvor</dodf> to bf dompbrfd
     * @rfturn truf if tif <dodf>MimfTypf</dodf>s brf fqubl,
     *  otifrwisf fblsf
     */

    publid finbl boolfbn isMimfTypfEqubl(DbtbFlbvor dbtbFlbvor) {
        rfturn isMimfTypfEqubl(dbtbFlbvor.mimfTypf);
    }

    /**
     * Compbrfs tif <dodf>mimfTypf</dodf> of two <dodf>DbtbFlbvor</dodf>
     * objfdts.  No pbrbmftfrs brf donsidfrfd.
     *
     * @rfturn truf if tif <dodf>MimfTypf</dodf>s brf fqubl,
     *  otifrwisf fblsf
     */

    privbtf boolfbn isMimfTypfEqubl(MimfTypf mtypf) {
        if (tiis.mimfTypf == null) {
            rfturn (mtypf == null);
        }
        rfturn mimfTypf.mbtdi(mtypf);
    }

    /**
     * Cifdks if tif rfprfsfntbtion dlbss is onf of tif stbndbrd tfxt
     * rfprfsfntbtion dlbssfs.
     *
     * @rfturn truf if tif rfprfsfntbtion dlbss is onf of tif stbndbrd tfxt
     *              rfprfsfntbtion dlbssfs, otifrwisf fblsf
     */
    privbtf boolfbn isStbndbrdTfxtRfprfsfntbtionClbss() {
        rfturn isRfprfsfntbtionClbssRfbdfr()
                || String.dlbss.fqubls(rfprfsfntbtionClbss)
                || isRfprfsfntbtionClbssCibrBufffr()
                || dibr[].dlbss.fqubls(rfprfsfntbtionClbss);
    }

   /**
    * Dofs tif <dodf>DbtbFlbvor</dodf> rfprfsfnt b sfriblizfd objfdt?
    * @rfturn wiftifr or not b sfriblizfd objfdt is rfprfsfntfd
    */
    publid boolfbn isMimfTypfSfriblizfdObjfdt() {
        rfturn isMimfTypfEqubl(jbvbSfriblizfdObjfdtMimfTypf);
    }

    /**
     * Rfturns tif dffbult rfprfsfntbtion dlbss.
     * @rfturn tif dffbult rfprfsfntbtion dlbss
     */
    publid finbl Clbss<?> gftDffbultRfprfsfntbtionClbss() {
        rfturn ioInputStrfbmClbss;
    }

    /**
     * Rfturns tif nbmf of tif dffbult rfprfsfntbtion dlbss.
     * @rfturn tif nbmf of tif dffbult rfprfsfntbtion dlbss
     */
    publid finbl String gftDffbultRfprfsfntbtionClbssAsString() {
        rfturn gftDffbultRfprfsfntbtionClbss().gftNbmf();
    }

   /**
    * Dofs tif <dodf>DbtbFlbvor</dodf> rfprfsfnt b
    * <dodf>jbvb.io.InputStrfbm</dodf>?
    * @rfturn wiftifr or not tiis {@dodf DbtbFlbvor} rfprfsfnt b
    * {@dodf jbvb.io.InputStrfbm}
    */
    publid boolfbn isRfprfsfntbtionClbssInputStrfbm() {
        rfturn ioInputStrfbmClbss.isAssignbblfFrom(rfprfsfntbtionClbss);
    }

    /**
     * Rfturns wiftifr tif rfprfsfntbtion dlbss for tiis
     * <dodf>DbtbFlbvor</dodf> is <dodf>jbvb.io.Rfbdfr</dodf> or b subdlbss
     * tifrfof.
     * @rfturn wiftifr or not tif rfprfsfntbtion dlbss for tiis
     * {@dodf DbtbFlbvor} is {@dodf jbvb.io.Rfbdfr} or b subdlbss
     * tifrfof
     *
     * @sindf 1.4
     */
    publid boolfbn isRfprfsfntbtionClbssRfbdfr() {
        rfturn jbvb.io.Rfbdfr.dlbss.isAssignbblfFrom(rfprfsfntbtionClbss);
    }

    /**
     * Rfturns wiftifr tif rfprfsfntbtion dlbss for tiis
     * <dodf>DbtbFlbvor</dodf> is <dodf>jbvb.nio.CibrBufffr</dodf> or b
     * subdlbss tifrfof.
     * @rfturn wiftifr or not tif rfprfsfntbtion dlbss for tiis
     * {@dodf DbtbFlbvor} is {@dodf jbvb.nio.CibrBufffr} or b subdlbss
     * tifrfof
     *
     * @sindf 1.4
     */
    publid boolfbn isRfprfsfntbtionClbssCibrBufffr() {
        rfturn jbvb.nio.CibrBufffr.dlbss.isAssignbblfFrom(rfprfsfntbtionClbss);
    }

    /**
     * Rfturns wiftifr tif rfprfsfntbtion dlbss for tiis
     * <dodf>DbtbFlbvor</dodf> is <dodf>jbvb.nio.BytfBufffr</dodf> or b
     * subdlbss tifrfof.
     * @rfturn wiftifr or not tif rfprfsfntbtion dlbss for tiis
     * {@dodf DbtbFlbvor} is {@dodf jbvb.nio.BytfBufffr} or b subdlbss
     * tifrfof
     *
     * @sindf 1.4
     */
    publid boolfbn isRfprfsfntbtionClbssBytfBufffr() {
        rfturn jbvb.nio.BytfBufffr.dlbss.isAssignbblfFrom(rfprfsfntbtionClbss);
    }

   /**
    * Rfturns truf if tif rfprfsfntbtion dlbss dbn bf sfriblizfd.
    * @rfturn truf if tif rfprfsfntbtion dlbss dbn bf sfriblizfd
    */

    publid boolfbn isRfprfsfntbtionClbssSfriblizbblf() {
        rfturn jbvb.io.Sfriblizbblf.dlbss.isAssignbblfFrom(rfprfsfntbtionClbss);
    }

   /**
    * Rfturns truf if tif rfprfsfntbtion dlbss is <dodf>Rfmotf</dodf>.
    * @rfturn truf if tif rfprfsfntbtion dlbss is <dodf>Rfmotf</dodf>
    */

    publid boolfbn isRfprfsfntbtionClbssRfmotf() {
        rfturn DbtbTrbnsffrfr.isRfmotf(rfprfsfntbtionClbss);
    }

   /**
    * Rfturns truf if tif <dodf>DbtbFlbvor</dodf> spfdififd rfprfsfnts
    * b sfriblizfd objfdt.
    * @rfturn truf if tif <dodf>DbtbFlbvor</dodf> spfdififd rfprfsfnts
    *   b Sfriblizfd Objfdt
    */

    publid boolfbn isFlbvorSfriblizfdObjfdtTypf() {
        rfturn isRfprfsfntbtionClbssSfriblizbblf() && isMimfTypfEqubl(jbvbSfriblizfdObjfdtMimfTypf);
    }

    /**
     * Rfturns truf if tif <dodf>DbtbFlbvor</dodf> spfdififd rfprfsfnts
     * b rfmotf objfdt.
     * @rfturn truf if tif <dodf>DbtbFlbvor</dodf> spfdififd rfprfsfnts
     *  b Rfmotf Objfdt
     */

    publid boolfbn isFlbvorRfmotfObjfdtTypf() {
        rfturn isRfprfsfntbtionClbssRfmotf()
            && isRfprfsfntbtionClbssSfriblizbblf()
            && isMimfTypfEqubl(jbvbRfmotfObjfdtMimfTypf);
    }


   /**
    * Rfturns truf if tif <dodf>DbtbFlbvor</dodf> spfdififd rfprfsfnts
    * b list of filf objfdts.
    * @rfturn truf if tif <dodf>DbtbFlbvor</dodf> spfdififd rfprfsfnts
    *   b List of Filf objfdts
    */

   publid boolfbn isFlbvorJbvbFilfListTypf() {
        if (mimfTypf == null || rfprfsfntbtionClbss == null)
            rfturn fblsf;
        rfturn jbvb.util.List.dlbss.isAssignbblfFrom(rfprfsfntbtionClbss) &&
               mimfTypf.mbtdi(jbvbFilfListFlbvor.mimfTypf);

   }

    /**
     * Rfturns wiftifr tiis <dodf>DbtbFlbvor</dodf> is b vblid tfxt flbvor for
     * tiis implfmfntbtion of tif Jbvb plbtform. Only flbvors fquivblfnt to
     * <dodf>DbtbFlbvor.stringFlbvor</dodf> bnd <dodf>DbtbFlbvor</dodf>s witi
     * b primbry MIME typf of "tfxt" dbn bf vblid tfxt flbvors.
     * <p>
     * If tiis flbvor supports tif dibrsft pbrbmftfr, it must bf fquivblfnt to
     * <dodf>DbtbFlbvor.stringFlbvor</dodf>, or its rfprfsfntbtion must bf
     * <dodf>jbvb.io.Rfbdfr</dodf>, <dodf>jbvb.lbng.String</dodf>,
     * <dodf>jbvb.nio.CibrBufffr</dodf>, <dodf>[C</dodf>,
     * <dodf>jbvb.io.InputStrfbm</dodf>, <dodf>jbvb.nio.BytfBufffr</dodf>, or
     * <dodf>[B</dodf>. If tif rfprfsfntbtion is
     * <dodf>jbvb.io.InputStrfbm</dodf>, <dodf>jbvb.nio.BytfBufffr</dodf>, or
     * <dodf>[B</dodf>, tifn tiis flbvor's <dodf>dibrsft</dodf> pbrbmftfr must
     * bf supportfd by tiis implfmfntbtion of tif Jbvb plbtform. If b dibrsft
     * is not spfdififd, tifn tif plbtform dffbult dibrsft, wiidi is blwbys
     * supportfd, is bssumfd.
     * <p>
     * If tiis flbvor dofs not support tif dibrsft pbrbmftfr, its
     * rfprfsfntbtion must bf <dodf>jbvb.io.InputStrfbm</dodf>,
     * <dodf>jbvb.nio.BytfBufffr</dodf>, or <dodf>[B</dodf>.
     * <p>
     * Sff <dodf>sflfdtBfstTfxtFlbvor</dodf> for b list of tfxt flbvors wiidi
     * support tif dibrsft pbrbmftfr.
     *
     * @rfturn <dodf>truf</dodf> if tiis <dodf>DbtbFlbvor</dodf> is b vblid
     *         tfxt flbvor bs dfsdribfd bbovf; <dodf>fblsf</dodf> otifrwisf
     * @sff #sflfdtBfstTfxtFlbvor
     * @sindf 1.4
     */
    publid boolfbn isFlbvorTfxtTypf() {
        rfturn (DbtbTrbnsffrfr.isFlbvorCibrsftTfxtTypf(tiis) ||
                DbtbTrbnsffrfr.isFlbvorNondibrsftTfxtTypf(tiis));
    }

   /**
    * Sfriblizfs tiis <dodf>DbtbFlbvor</dodf>.
    */

   publid syndironizfd void writfExtfrnbl(ObjfdtOutput os) tirows IOExdfption {
       if (mimfTypf != null) {
           mimfTypf.sftPbrbmftfr("iumbnPrfsfntbblfNbmf", iumbnPrfsfntbblfNbmf);
           os.writfObjfdt(mimfTypf);
           mimfTypf.rfmovfPbrbmftfr("iumbnPrfsfntbblfNbmf");
       } flsf {
           os.writfObjfdt(null);
       }

       os.writfObjfdt(rfprfsfntbtionClbss);
   }

   /**
    * Rfstorfs tiis <dodf>DbtbFlbvor</dodf> from b Sfriblizfd stbtf.
    */

   publid syndironizfd void rfbdExtfrnbl(ObjfdtInput is) tirows IOExdfption , ClbssNotFoundExdfption {
       String rdn = null;
        mimfTypf = (MimfTypf)is.rfbdObjfdt();

        if (mimfTypf != null) {
            iumbnPrfsfntbblfNbmf =
                mimfTypf.gftPbrbmftfr("iumbnPrfsfntbblfNbmf");
            mimfTypf.rfmovfPbrbmftfr("iumbnPrfsfntbblfNbmf");
            rdn = mimfTypf.gftPbrbmftfr("dlbss");
            if (rdn == null) {
                tirow nfw IOExdfption("no dlbss pbrbmftfr spfdififd in: " +
                                      mimfTypf);
            }
        }

        try {
            rfprfsfntbtionClbss = (Clbss)is.rfbdObjfdt();
        } dbtdi (OptionblDbtbExdfption odf) {
            if (!odf.fof || odf.lfngti != 0) {
                tirow odf;
            }
            // Ensurf bbdkwbrd dompbtibility.
            // Old vfrsions didn't writf tif rfprfsfntbtion dlbss to tif strfbm.
            if (rdn != null) {
                rfprfsfntbtionClbss =
                    DbtbFlbvor.tryToLobdClbss(rdn, gftClbss().gftClbssLobdfr());
            }
        }
   }

   /**
    * Rfturns b dlonf of tiis <dodf>DbtbFlbvor</dodf>.
    * @rfturn b dlonf of tiis <dodf>DbtbFlbvor</dodf>
    */

    publid Objfdt dlonf() tirows ClonfNotSupportfdExdfption {
        Objfdt nfwObj = supfr.dlonf();
        if (mimfTypf != null) {
            ((DbtbFlbvor)nfwObj).mimfTypf = (MimfTypf)mimfTypf.dlonf();
        }
        rfturn nfwObj;
    } // dlonf()

   /**
    * Cbllfd on <dodf>DbtbFlbvor</dodf> for fvfry MIME Typf pbrbmftfr
    * to bllow <dodf>DbtbFlbvor</dodf> subdlbssfs to ibndlf spfdibl
    * pbrbmftfrs likf tif tfxt/plbin <dodf>dibrsft</dodf>
    * pbrbmftfrs, wiosf vblufs brf dbsf insfnsitivf.  (MIME typf pbrbmftfr
    * vblufs brf supposfd to bf dbsf sfnsitivf.
    * <p>
    * Tiis mftiod is dbllfd for fbdi pbrbmftfr nbmf/vbluf pbir bnd siould
    * rfturn tif normblizfd rfprfsfntbtion of tif <dodf>pbrbmftfrVbluf</dodf>.
    *
    * Tiis mftiod is nfvfr invokfd by tiis implfmfntbtion from 1.1 onwbrds.
    *
    * @pbrbm pbrbmftfrNbmf tif pbrbmftfr nbmf
    * @pbrbm pbrbmftfrVbluf tif pbrbmftfr vbluf
    * @rfturn tif pbrbmftfr vbluf
    * @dfprfdbtfd
    */
    @Dfprfdbtfd
    protfdtfd String normblizfMimfTypfPbrbmftfr(String pbrbmftfrNbmf, String pbrbmftfrVbluf) {
        rfturn pbrbmftfrVbluf;
    }

   /**
    * Cbllfd for fbdi MIME typf string to givf <dodf>DbtbFlbvor</dodf> subtypfs
    * tif opportunity to dibngf iow tif normblizbtion of MIME typfs is
    * bddomplisifd.  Onf possiblf usf would bf to bdd dffbult
    * pbrbmftfr/vbluf pbirs in dbsfs wifrf nonf brf prfsfnt in tif MIME
    * typf string pbssfd in.
    *
    * Tiis mftiod is nfvfr invokfd by tiis implfmfntbtion from 1.1 onwbrds.
    *
    * @pbrbm mimfTypf tif mimf typf
    * @rfturn tif mimf typf
    * @dfprfdbtfd
    */
    @Dfprfdbtfd
    protfdtfd String normblizfMimfTypf(String mimfTypf) {
        rfturn mimfTypf;
    }

    /*
     * fiflds
     */

    /* plbdfioldfr for dbdiing bny plbtform-spfdifid dbtb for flbvor */

    trbnsifnt int       btom;

    /* Mimf Typf of DbtbFlbvor */

    MimfTypf            mimfTypf;

    privbtf String      iumbnPrfsfntbblfNbmf;

    /** Jbvb dlbss of objfdts tiis DbtbFlbvor rfprfsfnts **/

    privbtf Clbss<?>       rfprfsfntbtionClbss;

} // dlbss DbtbFlbvor
