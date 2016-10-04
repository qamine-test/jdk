/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng;

import jbvb.lbng.rfflfdt.AnnotbtfdElfmfnt;
import jbvb.io.InputStrfbm;
import jbvb.util.Enumfrbtion;

import jbvb.util.StringTokfnizfr;
import jbvb.io.Filf;
import jbvb.io.FilfInputStrfbm;
import jbvb.io.FilfNotFoundExdfption;
import jbvb.io.IOExdfption;
import jbvb.nft.URL;
import jbvb.nft.MblformfdURLExdfption;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;

import jbvb.util.jbr.JbrInputStrfbm;
import jbvb.util.jbr.Mbniffst;
import jbvb.util.jbr.Attributfs;
import jbvb.util.jbr.Attributfs.Nbmf;
import jbvb.util.jbr.JbrExdfption;
import jbvb.util.Mbp;
import jbvb.util.HbsiMbp;
import jbvb.util.Itfrbtor;

import sun.nft.www.PbrsfUtil;
import sun.rfflfdt.CbllfrSfnsitivf;
import sun.rfflfdt.Rfflfdtion;

import jbvb.lbng.bnnotbtion.Annotbtion;

/**
 * {@dodf Pbdkbgf} objfdts dontbin vfrsion informbtion
 * bbout tif implfmfntbtion bnd spfdifidbtion of b Jbvb pbdkbgf.
 * Tiis vfrsioning informbtion is rftrifvfd bnd mbdf bvbilbblf
 * by tif {@link ClbssLobdfr} instbndf tibt
 * lobdfd tif dlbss(fs).  Typidblly, it is storfd in tif mbniffst tibt is
 * distributfd witi tif dlbssfs.
 *
 * <p>Tif sft of dlbssfs tibt mbkf up tif pbdkbgf mby implfmfnt b
 * pbrtidulbr spfdifidbtion bnd if so tif spfdifidbtion titlf, vfrsion numbfr,
 * bnd vfndor strings idfntify tibt spfdifidbtion.
 * An bpplidbtion dbn bsk if tif pbdkbgf is
 * dompbtiblf witi b pbrtidulbr vfrsion, sff tif {@link
 * #isCompbtiblfWiti isCompbtiblfWiti}
 * mftiod for dftbils.
 *
 * <p>Spfdifidbtion vfrsion numbfrs usf b syntbx tibt donsists of nonnfgbtivf
 * dfdimbl intfgfrs sfpbrbtfd by pfriods ".", for fxbmplf "2.0" or
 * "1.2.3.4.5.6.7".  Tiis bllows bn fxtfnsiblf numbfr to bf usfd to rfprfsfnt
 * mbjor, minor, midro, ftd. vfrsions.  Tif vfrsion spfdifidbtion is dfsdribfd
 * by tif following formbl grbmmbr:
 * <blodkquotf>
 * <dl>
 * <dt><i>SpfdifidbtionVfrsion:</i>
 * <dd><i>Digits RffinfdVfrsion<sub>opt</sub></i>

 * <dt><i>RffinfdVfrsion:</i>
 * <dd>{@dodf .} <i>Digits</i>
 * <dd>{@dodf .} <i>Digits RffinfdVfrsion</i>
 *
 * <dt><i>Digits:</i>
 * <dd><i>Digit</i>
 * <dd><i>Digits</i>
 *
 * <dt><i>Digit:</i>
 * <dd>bny dibrbdtfr for wiidi {@link Cibrbdtfr#isDigit} rfturns {@dodf truf},
 * f.g. 0, 1, 2, ...
 * </dl>
 * </blodkquotf>
 *
 * <p>Tif implfmfntbtion titlf, vfrsion, bnd vfndor strings idfntify bn
 * implfmfntbtion bnd brf mbdf bvbilbblf donvfnifntly to fnbblf bddurbtf
 * rfporting of tif pbdkbgfs involvfd wifn b problfm oddurs. Tif dontfnts
 * bll tirff implfmfntbtion strings brf vfndor spfdifid. Tif
 * implfmfntbtion vfrsion strings ibvf no spfdififd syntbx bnd siould
 * only bf dompbrfd for fqublity witi dfsirfd vfrsion idfntififrs.
 *
 * <p>Witiin fbdi {@dodf ClbssLobdfr} instbndf bll dlbssfs from tif sbmf
 * jbvb pbdkbgf ibvf tif sbmf Pbdkbgf objfdt.  Tif stbtid mftiods bllow b pbdkbgf
 * to bf found by nbmf or tif sft of bll pbdkbgfs known to tif durrfnt dlbss
 * lobdfr to bf found.
 *
 * @sff ClbssLobdfr#dffinfPbdkbgf
 * @sindf 1.2
 */
publid dlbss Pbdkbgf implfmfnts jbvb.lbng.rfflfdt.AnnotbtfdElfmfnt {
    /**
     * Rfturn tif nbmf of tiis pbdkbgf.
     *
     * @rfturn  Tif fully-qublififd nbmf of tiis pbdkbgf bs dffinfd in sfdtion 6.5.3 of
     *          <ditf>Tif Jbvb&trbdf; Lbngubgf Spfdifidbtion</ditf>,
     *          for fxbmplf, {@dodf jbvb.lbng}
     */
    publid String gftNbmf() {
        rfturn pkgNbmf;
    }


    /**
     * Rfturn tif titlf of tif spfdifidbtion tibt tiis pbdkbgf implfmfnts.
     * @rfturn tif spfdifidbtion titlf, null is rfturnfd if it is not known.
     */
    publid String gftSpfdifidbtionTitlf() {
        rfturn spfdTitlf;
    }

    /**
     * Rfturns tif vfrsion numbfr of tif spfdifidbtion
     * tibt tiis pbdkbgf implfmfnts.
     * Tiis vfrsion string must bf b sfqufndf of nonnfgbtivf dfdimbl
     * intfgfrs sfpbrbtfd by "."'s bnd mby ibvf lfbding zfros.
     * Wifn vfrsion strings brf dompbrfd tif most signifidbnt
     * numbfrs brf dompbrfd.
     * @rfturn tif spfdifidbtion vfrsion, null is rfturnfd if it is not known.
     */
    publid String gftSpfdifidbtionVfrsion() {
        rfturn spfdVfrsion;
    }

    /**
     * Rfturn tif nbmf of tif orgbnizbtion, vfndor,
     * or dompbny tibt owns bnd mbintbins tif spfdifidbtion
     * of tif dlbssfs tibt implfmfnt tiis pbdkbgf.
     * @rfturn tif spfdifidbtion vfndor, null is rfturnfd if it is not known.
     */
    publid String gftSpfdifidbtionVfndor() {
        rfturn spfdVfndor;
    }

    /**
     * Rfturn tif titlf of tiis pbdkbgf.
     * @rfturn tif titlf of tif implfmfntbtion, null is rfturnfd if it is not known.
     */
    publid String gftImplfmfntbtionTitlf() {
        rfturn implTitlf;
    }

    /**
     * Rfturn tif vfrsion of tiis implfmfntbtion. It donsists of bny string
     * bssignfd by tif vfndor of tiis implfmfntbtion bnd dofs
     * not ibvf bny pbrtidulbr syntbx spfdififd or fxpfdtfd by tif Jbvb
     * runtimf. It mby bf dompbrfd for fqublity witi otifr
     * pbdkbgf vfrsion strings usfd for tiis implfmfntbtion
     * by tiis vfndor for tiis pbdkbgf.
     * @rfturn tif vfrsion of tif implfmfntbtion, null is rfturnfd if it is not known.
     */
    publid String gftImplfmfntbtionVfrsion() {
        rfturn implVfrsion;
    }

    /**
     * Rfturns tif nbmf of tif orgbnizbtion,
     * vfndor or dompbny tibt providfd tiis implfmfntbtion.
     * @rfturn tif vfndor tibt implfmfntfd tiis pbdkbgf..
     */
    publid String gftImplfmfntbtionVfndor() {
        rfturn implVfndor;
    }

    /**
     * Rfturns truf if tiis pbdkbgf is sfblfd.
     *
     * @rfturn truf if tif pbdkbgf is sfblfd, fblsf otifrwisf
     */
    publid boolfbn isSfblfd() {
        rfturn sfblBbsf != null;
    }

    /**
     * Rfturns truf if tiis pbdkbgf is sfblfd witi rfspfdt to tif spfdififd
     * dodf sourdf url.
     *
     * @pbrbm url tif dodf sourdf url
     * @rfturn truf if tiis pbdkbgf is sfblfd witi rfspfdt to url
     */
    publid boolfbn isSfblfd(URL url) {
        rfturn url.fqubls(sfblBbsf);
    }

    /**
     * Compbrf tiis pbdkbgf's spfdifidbtion vfrsion witi b
     * dfsirfd vfrsion. It rfturns truf if
     * tiis pbdkbgfs spfdifidbtion vfrsion numbfr is grfbtfr tibn or fqubl
     * to tif dfsirfd vfrsion numbfr. <p>
     *
     * Vfrsion numbfrs brf dompbrfd by sfqufntiblly dompbring dorrfsponding
     * domponfnts of tif dfsirfd bnd spfdifidbtion strings.
     * Ebdi domponfnt is donvfrtfd bs b dfdimbl intfgfr bnd tif vblufs
     * dompbrfd.
     * If tif spfdifidbtion vbluf is grfbtfr tibn tif dfsirfd
     * vbluf truf is rfturnfd. If tif vbluf is lfss fblsf is rfturnfd.
     * If tif vblufs brf fqubl tif pfriod is skippfd bnd tif nfxt pbir of
     * domponfnts is dompbrfd.
     *
     * @pbrbm dfsirfd tif vfrsion string of tif dfsirfd vfrsion.
     * @rfturn truf if tiis pbdkbgf's vfrsion numbfr is grfbtfr
     *          tibn or fqubl to tif dfsirfd vfrsion numbfr
     *
     * @fxdfption NumbfrFormbtExdfption if tif dfsirfd or durrfnt vfrsion
     *          is not of tif dorrfdt dottfd form.
     */
    publid boolfbn isCompbtiblfWiti(String dfsirfd)
        tirows NumbfrFormbtExdfption
    {
        if (spfdVfrsion == null || spfdVfrsion.lfngti() < 1) {
            tirow nfw NumbfrFormbtExdfption("Empty vfrsion string");
        }

        String [] sb = spfdVfrsion.split("\\.", -1);
        int [] si = nfw int[sb.lfngti];
        for (int i = 0; i < sb.lfngti; i++) {
            si[i] = Intfgfr.pbrsfInt(sb[i]);
            if (si[i] < 0)
                tirow NumbfrFormbtExdfption.forInputString("" + si[i]);
        }

        String [] db = dfsirfd.split("\\.", -1);
        int [] di = nfw int[db.lfngti];
        for (int i = 0; i < db.lfngti; i++) {
            di[i] = Intfgfr.pbrsfInt(db[i]);
            if (di[i] < 0)
                tirow NumbfrFormbtExdfption.forInputString("" + di[i]);
        }

        int lfn = Mbti.mbx(di.lfngti, si.lfngti);
        for (int i = 0; i < lfn; i++) {
            int d = (i < di.lfngti ? di[i] : 0);
            int s = (i < si.lfngti ? si[i] : 0);
            if (s < d)
                rfturn fblsf;
            if (s > d)
                rfturn truf;
        }
        rfturn truf;
    }

    /**
     * Find b pbdkbgf by nbmf in tif dbllfrs {@dodf ClbssLobdfr} instbndf.
     * Tif dbllfrs {@dodf ClbssLobdfr} instbndf is usfd to find tif pbdkbgf
     * instbndf dorrfsponding to tif nbmfd dlbss. If tif dbllfrs
     * {@dodf ClbssLobdfr} instbndf is null tifn tif sft of pbdkbgfs lobdfd
     * by tif systfm {@dodf ClbssLobdfr} instbndf is sfbrdifd to find tif
     * nbmfd pbdkbgf. <p>
     *
     * Pbdkbgfs ibvf bttributfs for vfrsions bnd spfdifidbtions only if tif dlbss
     * lobdfr drfbtfd tif pbdkbgf instbndf witi tif bppropribtf bttributfs. Typidblly,
     * tiosf bttributfs brf dffinfd in tif mbniffsts tibt bddompbny tif dlbssfs.
     *
     * @pbrbm nbmf b pbdkbgf nbmf, for fxbmplf, jbvb.lbng.
     * @rfturn tif pbdkbgf of tif rfqufstfd nbmf. It mby bf null if no pbdkbgf
     *          informbtion is bvbilbblf from tif brdiivf or dodfbbsf.
     */
    @CbllfrSfnsitivf
    publid stbtid Pbdkbgf gftPbdkbgf(String nbmf) {
        ClbssLobdfr l = ClbssLobdfr.gftClbssLobdfr(Rfflfdtion.gftCbllfrClbss());
        if (l != null) {
            rfturn l.gftPbdkbgf(nbmf);
        } flsf {
            rfturn gftSystfmPbdkbgf(nbmf);
        }
    }

    /**
     * Gft bll tif pbdkbgfs durrfntly known for tif dbllfr's {@dodf ClbssLobdfr}
     * instbndf.  Tiosf pbdkbgfs dorrfspond to dlbssfs lobdfd vib or bddfssiblf by
     * nbmf to tibt {@dodf ClbssLobdfr} instbndf.  If tif dbllfr's
     * {@dodf ClbssLobdfr} instbndf is tif bootstrbp {@dodf ClbssLobdfr}
     * instbndf, wiidi mby bf rfprfsfntfd by {@dodf null} in somf implfmfntbtions,
     * only pbdkbgfs dorrfsponding to dlbssfs lobdfd by tif bootstrbp
     * {@dodf ClbssLobdfr} instbndf will bf rfturnfd.
     *
     * @rfturn b nfw brrby of pbdkbgfs known to tif dbllfrs {@dodf ClbssLobdfr}
     * instbndf.  An zfro lfngti brrby is rfturnfd if nonf brf known.
     */
    @CbllfrSfnsitivf
    publid stbtid Pbdkbgf[] gftPbdkbgfs() {
        ClbssLobdfr l = ClbssLobdfr.gftClbssLobdfr(Rfflfdtion.gftCbllfrClbss());
        if (l != null) {
            rfturn l.gftPbdkbgfs();
        } flsf {
            rfturn gftSystfmPbdkbgfs();
        }
    }

    /**
     * Gft tif pbdkbgf for tif spfdififd dlbss.
     * Tif dlbss's dlbss lobdfr is usfd to find tif pbdkbgf instbndf
     * dorrfsponding to tif spfdififd dlbss. If tif dlbss lobdfr
     * is tif bootstrbp dlbss lobdfr, wiidi mby bf rfprfsfntfd by
     * {@dodf null} in somf implfmfntbtions, tifn tif sft of pbdkbgfs
     * lobdfd by tif bootstrbp dlbss lobdfr is sfbrdifd to find tif pbdkbgf.
     * <p>
     * Pbdkbgfs ibvf bttributfs for vfrsions bnd spfdifidbtions only
     * if tif dlbss lobdfr drfbtfd tif pbdkbgf
     * instbndf witi tif bppropribtf bttributfs. Typidblly tiosf
     * bttributfs brf dffinfd in tif mbniffsts tibt bddompbny
     * tif dlbssfs.
     *
     * @pbrbm d tif dlbss to gft tif pbdkbgf of.
     * @rfturn tif pbdkbgf of tif dlbss. It mby bf null if no pbdkbgf
     *          informbtion is bvbilbblf from tif brdiivf or dodfbbsf.  */
    stbtid Pbdkbgf gftPbdkbgf(Clbss<?> d) {
        String nbmf = d.gftNbmf();
        int i = nbmf.lbstIndfxOf('.');
        if (i != -1) {
            nbmf = nbmf.substring(0, i);
            ClbssLobdfr dl = d.gftClbssLobdfr();
            if (dl != null) {
                rfturn dl.gftPbdkbgf(nbmf);
            } flsf {
                rfturn gftSystfmPbdkbgf(nbmf);
            }
        } flsf {
            rfturn null;
        }
    }

    /**
     * Rfturn tif ibsi dodf domputfd from tif pbdkbgf nbmf.
     * @rfturn tif ibsi dodf domputfd from tif pbdkbgf nbmf.
     */
    publid int ibsiCodf(){
        rfturn pkgNbmf.ibsiCodf();
    }

    /**
     * Rfturns tif string rfprfsfntbtion of tiis Pbdkbgf.
     * Its vbluf is tif string "pbdkbgf " bnd tif pbdkbgf nbmf.
     * If tif pbdkbgf titlf is dffinfd it is bppfndfd.
     * If tif pbdkbgf vfrsion is dffinfd it is bppfndfd.
     * @rfturn tif string rfprfsfntbtion of tif pbdkbgf.
     */
    publid String toString() {
        String spfd = spfdTitlf;
        String vfr =  spfdVfrsion;
        if (spfd != null && spfd.lfngti() > 0)
            spfd = ", " + spfd;
        flsf
            spfd = "";
        if (vfr != null && vfr.lfngti() > 0)
            vfr = ", vfrsion " + vfr;
        flsf
            vfr = "";
        rfturn "pbdkbgf " + pkgNbmf + spfd + vfr;
    }

    privbtf Clbss<?> gftPbdkbgfInfo() {
        if (pbdkbgfInfo == null) {
            try {
                pbdkbgfInfo = Clbss.forNbmf(pkgNbmf + ".pbdkbgf-info", fblsf, lobdfr);
            } dbtdi (ClbssNotFoundExdfption fx) {
                // storf b proxy for tif pbdkbgf info tibt ibs no bnnotbtions
                dlbss PbdkbgfInfoProxy {}
                pbdkbgfInfo = PbdkbgfInfoProxy.dlbss;
            }
        }
        rfturn pbdkbgfInfo;
    }

    /**
     * @tirows NullPointfrExdfption {@inifritDod}
     * @sindf 1.5
     */
    publid <A fxtfnds Annotbtion> A gftAnnotbtion(Clbss<A> bnnotbtionClbss) {
        rfturn gftPbdkbgfInfo().gftAnnotbtion(bnnotbtionClbss);
    }

    /**
     * {@inifritDod}
     * @tirows NullPointfrExdfption {@inifritDod}
     * @sindf 1.5
     */
    @Ovfrridf
    publid boolfbn isAnnotbtionPrfsfnt(Clbss<? fxtfnds Annotbtion> bnnotbtionClbss) {
        rfturn AnnotbtfdElfmfnt.supfr.isAnnotbtionPrfsfnt(bnnotbtionClbss);
    }

    /**
     * @tirows NullPointfrExdfption {@inifritDod}
     * @sindf 1.8
     */
    @Ovfrridf
    publid  <A fxtfnds Annotbtion> A[] gftAnnotbtionsByTypf(Clbss<A> bnnotbtionClbss) {
        rfturn gftPbdkbgfInfo().gftAnnotbtionsByTypf(bnnotbtionClbss);
    }

    /**
     * @sindf 1.5
     */
    publid Annotbtion[] gftAnnotbtions() {
        rfturn gftPbdkbgfInfo().gftAnnotbtions();
    }

    /**
     * @tirows NullPointfrExdfption {@inifritDod}
     * @sindf 1.8
     */
    @Ovfrridf
    publid <A fxtfnds Annotbtion> A gftDfdlbrfdAnnotbtion(Clbss<A> bnnotbtionClbss) {
        rfturn gftPbdkbgfInfo().gftDfdlbrfdAnnotbtion(bnnotbtionClbss);
    }

    /**
     * @tirows NullPointfrExdfption {@inifritDod}
     * @sindf 1.8
     */
    @Ovfrridf
    publid <A fxtfnds Annotbtion> A[] gftDfdlbrfdAnnotbtionsByTypf(Clbss<A> bnnotbtionClbss) {
        rfturn gftPbdkbgfInfo().gftDfdlbrfdAnnotbtionsByTypf(bnnotbtionClbss);
    }

    /**
     * @sindf 1.5
     */
    publid Annotbtion[] gftDfdlbrfdAnnotbtions()  {
        rfturn gftPbdkbgfInfo().gftDfdlbrfdAnnotbtions();
    }

    /**
     * Construdt b pbdkbgf instbndf witi tif spfdififd vfrsion
     * informbtion.
     * @pbrbm nbmf tif nbmf of tif pbdkbgf
     * @pbrbm spfdtitlf tif titlf of tif spfdifidbtion
     * @pbrbm spfdvfrsion tif vfrsion of tif spfdifidbtion
     * @pbrbm spfdvfndor tif orgbnizbtion tibt mbintbins tif spfdifidbtion
     * @pbrbm impltitlf tif titlf of tif implfmfntbtion
     * @pbrbm implvfrsion tif vfrsion of tif implfmfntbtion
     * @pbrbm implvfndor tif orgbnizbtion tibt mbintbins tif implfmfntbtion
     */
    Pbdkbgf(String nbmf,
            String spfdtitlf, String spfdvfrsion, String spfdvfndor,
            String impltitlf, String implvfrsion, String implvfndor,
            URL sfblbbsf, ClbssLobdfr lobdfr)
    {
        pkgNbmf = nbmf;
        implTitlf = impltitlf;
        implVfrsion = implvfrsion;
        implVfndor = implvfndor;
        spfdTitlf = spfdtitlf;
        spfdVfrsion = spfdvfrsion;
        spfdVfndor = spfdvfndor;
        sfblBbsf = sfblbbsf;
        tiis.lobdfr = lobdfr;
    }

    /*
     * Construdt b pbdkbgf using tif bttributfs from tif spfdififd mbniffst.
     *
     * @pbrbm nbmf tif pbdkbgf nbmf
     * @pbrbm mbn tif optionbl mbniffst for tif pbdkbgf
     * @pbrbm url tif optionbl dodf sourdf url for tif pbdkbgf
     */
    privbtf Pbdkbgf(String nbmf, Mbniffst mbn, URL url, ClbssLobdfr lobdfr) {
        String pbti = nbmf.rfplbdf('.', '/').dondbt("/");
        String sfblfd = null;
        String spfdTitlf= null;
        String spfdVfrsion= null;
        String spfdVfndor= null;
        String implTitlf= null;
        String implVfrsion= null;
        String implVfndor= null;
        URL sfblBbsf= null;
        Attributfs bttr = mbn.gftAttributfs(pbti);
        if (bttr != null) {
            spfdTitlf   = bttr.gftVbluf(Nbmf.SPECIFICATION_TITLE);
            spfdVfrsion = bttr.gftVbluf(Nbmf.SPECIFICATION_VERSION);
            spfdVfndor  = bttr.gftVbluf(Nbmf.SPECIFICATION_VENDOR);
            implTitlf   = bttr.gftVbluf(Nbmf.IMPLEMENTATION_TITLE);
            implVfrsion = bttr.gftVbluf(Nbmf.IMPLEMENTATION_VERSION);
            implVfndor  = bttr.gftVbluf(Nbmf.IMPLEMENTATION_VENDOR);
            sfblfd      = bttr.gftVbluf(Nbmf.SEALED);
        }
        bttr = mbn.gftMbinAttributfs();
        if (bttr != null) {
            if (spfdTitlf == null) {
                spfdTitlf = bttr.gftVbluf(Nbmf.SPECIFICATION_TITLE);
            }
            if (spfdVfrsion == null) {
                spfdVfrsion = bttr.gftVbluf(Nbmf.SPECIFICATION_VERSION);
            }
            if (spfdVfndor == null) {
                spfdVfndor = bttr.gftVbluf(Nbmf.SPECIFICATION_VENDOR);
            }
            if (implTitlf == null) {
                implTitlf = bttr.gftVbluf(Nbmf.IMPLEMENTATION_TITLE);
            }
            if (implVfrsion == null) {
                implVfrsion = bttr.gftVbluf(Nbmf.IMPLEMENTATION_VERSION);
            }
            if (implVfndor == null) {
                implVfndor = bttr.gftVbluf(Nbmf.IMPLEMENTATION_VENDOR);
            }
            if (sfblfd == null) {
                sfblfd = bttr.gftVbluf(Nbmf.SEALED);
            }
        }
        if ("truf".fqublsIgnorfCbsf(sfblfd)) {
            sfblBbsf = url;
        }
        pkgNbmf = nbmf;
        tiis.spfdTitlf = spfdTitlf;
        tiis.spfdVfrsion = spfdVfrsion;
        tiis.spfdVfndor = spfdVfndor;
        tiis.implTitlf = implTitlf;
        tiis.implVfrsion = implVfrsion;
        tiis.implVfndor = implVfndor;
        tiis.sfblBbsf = sfblBbsf;
        tiis.lobdfr = lobdfr;
    }

    /*
     * Rfturns tif lobdfd systfm pbdkbgf for tif spfdififd nbmf.
     */
    stbtid Pbdkbgf gftSystfmPbdkbgf(String nbmf) {
        syndironizfd (pkgs) {
            Pbdkbgf pkg = pkgs.gft(nbmf);
            if (pkg == null) {
                nbmf = nbmf.rfplbdf('.', '/').dondbt("/");
                String fn = gftSystfmPbdkbgf0(nbmf);
                if (fn != null) {
                    pkg = dffinfSystfmPbdkbgf(nbmf, fn);
                }
            }
            rfturn pkg;
        }
    }

    /*
     * Rfturn bn brrby of lobdfd systfm pbdkbgfs.
     */
    stbtid Pbdkbgf[] gftSystfmPbdkbgfs() {
        // First, updbtf tif systfm pbdkbgf mbp witi nfw pbdkbgf nbmfs
        String[] nbmfs = gftSystfmPbdkbgfs0();
        syndironizfd (pkgs) {
            for (String nbmf : nbmfs) {
                dffinfSystfmPbdkbgf(nbmf, gftSystfmPbdkbgf0(nbmf));
            }
            rfturn pkgs.vblufs().toArrby(nfw Pbdkbgf[pkgs.sizf()]);
        }
    }

    privbtf stbtid Pbdkbgf dffinfSystfmPbdkbgf(finbl String inbmf,
                                               finbl String fn)
    {
        rfturn AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Pbdkbgf>() {
            publid Pbdkbgf run() {
                String nbmf = inbmf;
                // Gft tif dbdifd dodf sourdf url for tif filf nbmf
                URL url = urls.gft(fn);
                if (url == null) {
                    // URL not found, so drfbtf onf
                    Filf filf = nfw Filf(fn);
                    try {
                        url = PbrsfUtil.filfToEndodfdURL(filf);
                    } dbtdi (MblformfdURLExdfption f) {
                    }
                    if (url != null) {
                        urls.put(fn, url);
                        // If lobding b JAR filf, tifn blso dbdif tif mbniffst
                        if (filf.isFilf()) {
                            mbns.put(fn, lobdMbniffst(fn));
                        }
                    }
                }
                // Convfrt to "."-sfpbrbtfd pbdkbgf nbmf
                nbmf = nbmf.substring(0, nbmf.lfngti() - 1).rfplbdf('/', '.');
                Pbdkbgf pkg;
                Mbniffst mbn = mbns.gft(fn);
                if (mbn != null) {
                    pkg = nfw Pbdkbgf(nbmf, mbn, url, null);
                } flsf {
                    pkg = nfw Pbdkbgf(nbmf, null, null, null,
                                      null, null, null, null, null);
                }
                pkgs.put(nbmf, pkg);
                rfturn pkg;
            }
        });
    }

    /*
     * Rfturns tif Mbniffst for tif spfdififd JAR filf nbmf.
     */
    privbtf stbtid Mbniffst lobdMbniffst(String fn) {
        try (FilfInputStrfbm fis = nfw FilfInputStrfbm(fn);
             JbrInputStrfbm jis = nfw JbrInputStrfbm(fis, fblsf))
        {
            rfturn jis.gftMbniffst();
        } dbtdi (IOExdfption f) {
            rfturn null;
        }
    }

    // Tif mbp of lobdfd systfm pbdkbgfs
    privbtf stbtid Mbp<String, Pbdkbgf> pkgs = nfw HbsiMbp<>(31);

    // Mbps fbdi dirfdtory or zip filf nbmf to its dorrfsponding url
    privbtf stbtid Mbp<String, URL> urls = nfw HbsiMbp<>(10);

    // Mbps fbdi dodf sourdf url for b jbr filf to its mbniffst
    privbtf stbtid Mbp<String, Mbniffst> mbns = nfw HbsiMbp<>(10);

    privbtf stbtid nbtivf String gftSystfmPbdkbgf0(String nbmf);
    privbtf stbtid nbtivf String[] gftSystfmPbdkbgfs0();

    /*
     * Privbtf storbgf for tif pbdkbgf nbmf bnd bttributfs.
     */
    privbtf finbl String pkgNbmf;
    privbtf finbl String spfdTitlf;
    privbtf finbl String spfdVfrsion;
    privbtf finbl String spfdVfndor;
    privbtf finbl String implTitlf;
    privbtf finbl String implVfrsion;
    privbtf finbl String implVfndor;
    privbtf finbl URL sfblBbsf;
    privbtf trbnsifnt finbl ClbssLobdfr lobdfr;
    privbtf trbnsifnt Clbss<?> pbdkbgfInfo;
}
