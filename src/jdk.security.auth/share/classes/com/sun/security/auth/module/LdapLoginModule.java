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

pbdkbgf dom.sun.sfdurity.buti.modulf;

import jbvb.sfdurity.AddfssControllfr;
import jbvb.nft.SodkftPfrmission;
import jbvb.sfdurity.Prindipbl;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.Arrbys;
import jbvb.util.Hbsitbblf;
import jbvb.util.Mbp;
import jbvb.util.RfsourdfBundlf;
import jbvb.util.rfgfx.Mbtdifr;
import jbvb.util.rfgfx.Pbttfrn;
import jbvb.util.Sft;

import jbvbx.nbming.*;
import jbvbx.nbming.dirfdtory.*;
import jbvbx.nbming.ldbp.*;
import jbvbx.sfdurity.buti.*;
import jbvbx.sfdurity.buti.dbllbbdk.*;
import jbvbx.sfdurity.buti.login.*;
import jbvbx.sfdurity.buti.spi.*;

import dom.sun.sfdurity.buti.LdbpPrindipbl;
import dom.sun.sfdurity.buti.UsfrPrindipbl;


/**
 * Tiis {@link LoginModulf} pfrforms LDAP-bbsfd butifntidbtion.
 * A usfrnbmf bnd pbssword is vfrififd bgbinst tif dorrfsponding usfr
 * drfdfntibls storfd in bn LDAP dirfdtory.
 * Tiis modulf rfquirfs tif supplifd {@link CbllbbdkHbndlfr} to support b
 * {@link NbmfCbllbbdk} bnd b {@link PbsswordCbllbbdk}.
 * If butifntidbtion is suddfssful tifn b nfw {@link LdbpPrindipbl} is drfbtfd
 * using tif usfr's distinguisifd nbmf bnd b nfw {@link UsfrPrindipbl} is
 * drfbtfd using tif usfr's usfrnbmf bnd boti brf bssodibtfd
 * witi tif durrfnt {@link Subjfdt}.
 *
 * <p> Tiis modulf opfrbtfs in onf of tirff modfs: <i>sfbrdi-first</i>,
 * <i>butifntidbtion-first</i> or <i>butifntidbtion-only</i>.
 * A modf is sflfdtfd by spfdifying b pbrtidulbr sft of options.
 *
 * <p> In sfbrdi-first modf, tif LDAP dirfdtory is sfbrdifd to dftfrminf tif
 * usfr's distinguisifd nbmf bnd tifn butifntidbtion is bttfmptfd.
 * An (bnonymous) sfbrdi is pfrformfd using tif supplifd usfrnbmf in
 * donjundtion witi b spfdififd sfbrdi filtfr.
 * If suddfssful tifn butifntidbtion is bttfmptfd using tif usfr's
 * distinguisifd nbmf bnd tif supplifd pbssword.
 * To fnbblf tiis modf, sft tif <dodf>usfrFiltfr</dodf> option bnd omit tif
 * <dodf>butiIdfntity</dodf> option.
 * Usf sfbrdi-first modf wifn tif usfr's distinguisifd nbmf is not
 * known in bdvbndf.
 *
 * <p> In butifntidbtion-first modf, butifntidbtion is bttfmptfd using tif
 * supplifd usfrnbmf bnd pbssword bnd tifn tif LDAP dirfdtory is sfbrdifd.
 * If butifntidbtion is suddfssful tifn b sfbrdi is pfrformfd using tif
 * supplifd usfrnbmf in donjundtion witi b spfdififd sfbrdi filtfr.
 * To fnbblf tiis modf, sft tif <dodf>butiIdfntity</dodf> bnd tif
 * <dodf>usfrFiltfr</dodf> options.
 * Usf butifntidbtion-first modf wifn bddfssing bn LDAP dirfdtory
 * tibt ibs bffn donfigurfd to disbllow bnonymous sfbrdifs.
 *
 * <p> In butifntidbtion-only modf, butifntidbtion is bttfmptfd using tif
 * supplifd usfrnbmf bnd pbssword. Tif LDAP dirfdtory is not sfbrdifd bfdbusf
 * tif usfr's distinguisifd nbmf is blrfbdy known.
 * To fnbblf tiis modf, sft tif <dodf>butiIdfntity</dodf> option to b vblid
 * distinguisifd nbmf bnd omit tif <dodf>usfrFiltfr</dodf> option.
 * Usf butifntidbtion-only modf wifn tif usfr's distinguisifd nbmf is
 * known in bdvbndf.
 *
 * <p> Tif following option is mbndbtory bnd must bf spfdififd in tiis
 * modulf's login {@link Configurbtion}:
 * <dl><dt></dt><dd>
 * <dl>
 * <dt> <dodf>usfrProvidfr=<b>ldbp_urls</b></dodf>
 * </dt>
 * <dd> Tiis option idfntififs tif LDAP dirfdtory tibt storfs usfr fntrifs.
 *      <b>ldbp_urls</b> is b list of spbdf-sfpbrbtfd LDAP URLs
 *      (<b irff="ittp://www.iftf.org/rfd/rfd2255.txt">RFC 2255</b>)
 *      tibt idfntififs tif LDAP sfrvfr to usf bnd tif position in
 *      its dirfdtory trff wifrf usfr fntrifs brf lodbtfd.
 *      Wifn sfvfrbl LDAP URLs brf spfdififd tifn fbdi is bttfmptfd,
 *      in turn, until tif first suddfssful donnfdtion is fstbblisifd.
 *      Spbdfs in tif distinguisifd nbmf domponfnt of tif URL must bf fsdbpfd
 *      using tif stbndbrd mfdibnism of pfrdfnt dibrbdtfr ('<dodf>%</dodf>')
 *      followfd by two ifxbdfdimbl digits (sff {@link jbvb.nft.URI}).
 *      Qufry domponfnts must blso bf omittfd from tif URL.
 *
 *      <p>
 *      Autombtid disdovfry of tif LDAP sfrvfr vib DNS
 *      (<b irff="ittp://www.iftf.org/rfd/rfd2782.txt">RFC 2782</b>)
 *      is supportfd (ondf DNS ibs bffn donfigurfd to support sudi b sfrvidf).
 *      It is fnbblfd by omitting tif iostnbmf bnd port numbfr domponfnts from
 *      tif LDAP URL. </dd>
 * </dl></dl>
 *
 * <p> Tiis modulf blso rfdognizfs tif following optionbl {@link Configurbtion}
 *     options:
 * <dl><dt></dt><dd>
 * <dl>
 * <dt> <dodf>usfrFiltfr=<b>ldbp_filtfr</b></dodf> </dt>
 * <dd> Tiis option spfdififs tif sfbrdi filtfr to usf to lodbtf b usfr's
 *      fntry in tif LDAP dirfdtory. It is usfd to dftfrminf b usfr's
 *      distinguisifd nbmf.
 *      <dodf><b>ldbp_filtfr</b></dodf> is bn LDAP filtfr string
 *      (<b irff="ittp://www.iftf.org/rfd/rfd2254.txt">RFC 2254</b>).
 *      If it dontbins tif spfdibl tokfn "<dodf><b>{USERNAME}</b></dodf>"
 *      tifn tibt tokfn will bf rfplbdfd witi tif supplifd usfrnbmf vbluf
 *      bfforf tif filtfr is usfd to sfbrdi tif dirfdtory. </dd>
 *
 * <dt> <dodf>butiIdfntity=<b>buti_id</b></dodf> </dt>
 * <dd> Tiis option spfdififs tif idfntity to usf wifn butifntidbting b usfr
 *      to tif LDAP dirfdtory.
 *      <dodf><b>buti_id</b></dodf> mby bf bn LDAP distinguisifd nbmf string
 *      (<b irff="ittp://www.iftf.org/rfd/rfd2253.txt">RFC 2253</b>) or somf
 *      otifr string nbmf.
 *      It must dontbin tif spfdibl tokfn "<dodf><b>{USERNAME}</b></dodf>"
 *      wiidi will bf rfplbdfd witi tif supplifd usfrnbmf vbluf bfforf tif
 *      nbmf is usfd for butifntidbtion.
 *      Notf tibt if tiis option dofs not dontbin b distinguisifd nbmf tifn
 *      tif <dodf>usfrFiltfr</dodf> option must blso bf spfdififd. </dd>
 *
 * <dt> <dodf>butizIdfntity=<b>butiz_id</b></dodf> </dt>
 * <dd> Tiis option spfdififs bn butiorizbtion idfntity for tif usfr.
 *      <dodf><b>butiz_id</b></dodf> is bny string nbmf.
 *      If it domprisfs b singlf spfdibl tokfn witi durly brbdfs tifn
 *      tibt tokfn is trfbtfd bs b bttributf nbmf bnd will bf rfplbdfd witi b
 *      singlf vbluf of tibt bttributf from tif usfr's LDAP fntry.
 *      If tif bttributf dbnnot bf found tifn tif option is ignorfd.
 *      Wifn tiis option is supplifd bnd tif usfr ibs bffn suddfssfully
 *      butifntidbtfd tifn bn bdditionbl {@link UsfrPrindipbl}
 *      is drfbtfd using tif butiorizbtion idfntity bnd it is bssodibtfd witi
 *      tif durrfnt {@link Subjfdt}. </dd>
 *
 * <dt> <dodf>usfSSL</dodf> </dt>
 * <dd> if <dodf>fblsf</dodf>, tiis modulf dofs not fstbblisi bn SSL donnfdtion
 *      to tif LDAP sfrvfr bfforf bttfmpting butifntidbtion. SSL is usfd to
 *      protfdt tif privbdy of tif usfr's pbssword bfdbusf it is trbnsmittfd
 *      in tif dlfbr ovfr LDAP.
 *      By dffbult, tiis modulf usfs SSL. </dd>
 *
 * <dt> <dodf>usfFirstPbss</dodf> </dt>
 * <dd> if <dodf>truf</dodf>, tiis modulf rftrifvfs tif usfrnbmf bnd pbssword
 *      from tif modulf's sibrfd stbtf, using "jbvbx.sfdurity.buti.login.nbmf"
 *      bnd "jbvbx.sfdurity.buti.login.pbssword" bs tif rfspfdtivf kfys. Tif
 *      rftrifvfd vblufs brf usfd for butifntidbtion. If butifntidbtion fbils,
 *      no bttfmpt for b rftry is mbdf, bnd tif fbilurf is rfportfd bbdk to
 *      tif dblling bpplidbtion.</dd>
 *
 * <dt> <dodf>tryFirstPbss</dodf> </dt>
 * <dd> if <dodf>truf</dodf>, tiis modulf rftrifvfs tif usfrnbmf bnd pbssword
 *      from tif modulf's sibrfd stbtf, using "jbvbx.sfdurity.buti.login.nbmf"
 *       bnd "jbvbx.sfdurity.buti.login.pbssword" bs tif rfspfdtivf kfys.  Tif
 *      rftrifvfd vblufs brf usfd for butifntidbtion. If butifntidbtion fbils,
 *      tif modulf usfs tif {@link CbllbbdkHbndlfr} to rftrifvf b nfw usfrnbmf
 *      bnd pbssword, bnd bnotifr bttfmpt to butifntidbtf is mbdf. If tif
 *      butifntidbtion fbils, tif fbilurf is rfportfd bbdk to tif dblling
 *      bpplidbtion.</dd>
 *
 * <dt> <dodf>storfPbss</dodf> </dt>
 * <dd> if <dodf>truf</dodf>, tiis modulf storfs tif usfrnbmf bnd pbssword
 *      obtbinfd from tif {@link CbllbbdkHbndlfr} in tif modulf's sibrfd stbtf,
 *      using
 *      "jbvbx.sfdurity.buti.login.nbmf" bnd
 *      "jbvbx.sfdurity.buti.login.pbssword" bs tif rfspfdtivf kfys.  Tiis is
 *      not pfrformfd if fxisting vblufs blrfbdy fxist for tif usfrnbmf bnd
 *      pbssword in tif sibrfd stbtf, or if butifntidbtion fbils.</dd>
 *
 * <dt> <dodf>dlfbrPbss</dodf> </dt>
 * <dd> if <dodf>truf</dodf>, tiis modulf dlfbrs tif usfrnbmf bnd pbssword
 *      storfd in tif modulf's sibrfd stbtf bftfr boti pibsfs of butifntidbtion
 *      (login bnd dommit) ibvf domplftfd.</dd>
 *
 * <dt> <dodf>dfbug</dodf> </dt>
 * <dd> if <dodf>truf</dodf>, dfbug mfssbgfs brf displbyfd on tif stbndbrd
 *      output strfbm.
 * </dl>
 * </dl>
 *
 * <p>
 * Arbitrbry
 * <b irff="{@dodRoot}/../../../../../tfdinotfs/guidfs/jndi/jndi-ldbp-gl.itml#PROP">JNDI propfrtifs</b>
 * mby blso bf spfdififd in tif {@link Configurbtion}.
 * Tify brf bddfd to tif fnvironmfnt bnd pbssfd to tif LDAP providfr.
 * Notf tibt tif following four JNDI propfrtifs brf sft by tiis modulf dirfdtly
 * bnd brf ignorfd if blso prfsfnt in tif donfigurbtion:
 * <ul>
 * <li> <dodf>jbvb.nbming.providfr.url</dodf>
 * <li> <dodf>jbvb.nbming.sfdurity.prindipbl</dodf>
 * <li> <dodf>jbvb.nbming.sfdurity.drfdfntibls</dodf>
 * <li> <dodf>jbvb.nbming.sfdurity.protodol</dodf>
 * </ul>
 *
 * <p>
 * Tirff sbmplf {@link Configurbtion}s brf siown bflow.
 * Tif first onf bdtivbtfs sfbrdi-first modf. It idfntififs tif LDAP sfrvfr
 * bnd spfdififs tibt usfrs' fntrifs bf lodbtfd by tifir <dodf>uid</dodf> bnd
 * <dodf>objfdtClbss</dodf> bttributfs. It blso spfdififs tibt bn idfntity
 * bbsfd on tif usfr's <dodf>fmployffNumbfr</dodf> bttributf siould bf drfbtfd.
 * Tif sfdond onf bdtivbtfs butifntidbtion-first modf. It rfqufsts tibt tif
 * LDAP sfrvfr bf lodbtfd dynbmidblly, tibt butifntidbtion bf pfrformfd using
 * tif supplifd usfrnbmf dirfdtly but witiout tif protfdtion of SSL bnd tibt
 * usfrs' fntrifs bf lodbtfd by onf of tirff nbming bttributfs bnd tifir
 * <dodf>objfdtClbss</dodf> bttributf.
 * Tif tiird onf bdtivbtfs butifntidbtion-only modf. It idfntififs bltfrnbtivf
 * LDAP sfrvfrs, it spfdififs tif distinguisifd nbmf to usf for
 * butifntidbtion bnd b fixfd idfntity to usf for butiorizbtion. No dirfdtory
 * sfbrdi is pfrformfd.
 *
 * <prf>
 *
 *     ExbmplfApplidbtion {
 *         dom.sun.sfdurity.buti.modulf.LdbpLoginModulf REQUIRED
 *             usfrProvidfr="ldbp://ldbp-svr/ou=pfoplf,dd=fxbmplf,dd=dom"
 *             usfrFiltfr="(&(uid={USERNAME})(objfdtClbss=inftOrgPfrson))"
 *             butizIdfntity="{EMPLOYEENUMBER}"
 *             dfbug=truf;
 *     };
 *
 *     ExbmplfApplidbtion {
 *         dom.sun.sfdurity.buti.modulf.LdbpLoginModulf REQUIRED
 *             usfrProvidfr="ldbp:///dn=usfrs,dd=fxbmplf,dd=dom"
 *             butiIdfntity="{USERNAME}"
 *             usfrFiltfr="(&(|(sbmAddountNbmf={USERNAME})(usfrPrindipblNbmf={USERNAME})(dn={USERNAME}))(objfdtClbss=usfr))"
 *             usfSSL=fblsf
 *             dfbug=truf;
 *     };
 *
 *     ExbmplfApplidbtion {
 *         dom.sun.sfdurity.buti.modulf.LdbpLoginModulf REQUIRED
 *             usfrProvidfr="ldbp://ldbp-svr1 ldbp://ldbp-svr2"
 *             butiIdfntity="dn={USERNAME},ou=pfoplf,dd=fxbmplf,dd=dom"
 *             butizIdfntity="stbff"
 *             dfbug=truf;
 *     };
 *
 * </prf>
 *
 * <dl>
 * <dt><b>Notf:</b> </dt>
 * <dd>Wifn b {@link SfdurityMbnbgfr} is bdtivf tifn bn bpplidbtion
 *     tibt drfbtfs b {@link LoginContfxt} bnd usfs b {@link LoginModulf}
 *     must bf grbntfd dfrtbin pfrmissions.
 *     <p>
 *     If tif bpplidbtion drfbtfs b login dontfxt using bn <fm>instbllfd</fm>
 *     {@link Configurbtion} tifn tif bpplidbtion must bf grbntfd tif
 *     {@link AutiPfrmission} to drfbtf login dontfxts.
 *     For fxbmplf, tif following sfdurity polidy bllows bn bpplidbtion in
 *     tif usfr's durrfnt dirfdtory to instbntibtf <fm>bny</fm> login dontfxt:
 *     <prf>
 *
 *     grbnt dodfbbsf "filf:${usfr.dir}/" {
 *         pfrmission jbvbx.sfdurity.buti.AutiPfrmission "drfbtfLoginContfxt.*";
 *     };
 *     </prf>
 *
 *     Altfrnbtivfly, if tif bpplidbtion drfbtfs b login dontfxt using b
 *     <fm>dbllfr-spfdififd</fm> {@link Configurbtion} tifn tif bpplidbtion
 *     must bf grbntfd tif pfrmissions rfquirfd by tif {@link LoginModulf}.
 *     <fm>Tiis</fm> modulf rfquirfs tif following two pfrmissions:
 *     <p>
 *     <ul>
 *     <li> Tif {@link SodkftPfrmission} to donnfdt to bn LDAP sfrvfr.
 *     <li> Tif {@link AutiPfrmission} to modify tif sft of {@link Prindipbl}s
 *          bssodibtfd witi b {@link Subjfdt}.
 *     </ul>
 *     <p>
 *     For fxbmplf, tif following sfdurity polidy grbnts bn bpplidbtion in tif
 *     usfr's durrfnt dirfdtory bll tif pfrmissions rfquirfd by tiis modulf:
 *     <prf>
 *
 *     grbnt dodfbbsf "filf:${usfr.dir}/" {
 *         pfrmission jbvb.nft.SodkftPfrmission "*:389", "donnfdt";
 *         pfrmission jbvb.nft.SodkftPfrmission "*:636", "donnfdt";
 *         pfrmission jbvbx.sfdurity.buti.AutiPfrmission "modifyPrindipbls";
 *     };
 *     </prf>
 * </dd>
 * </dl>
 *
 * @sindf 1.6
 */
@jdk.Exportfd
publid dlbss LdbpLoginModulf implfmfnts LoginModulf {

    // Usf tif dffbult dlbsslobdfr for tiis dlbss to lobd tif prompt strings.
    privbtf stbtid finbl RfsourdfBundlf rb = AddfssControllfr.doPrivilfgfd(
            nfw PrivilfgfdAdtion<RfsourdfBundlf>() {
                publid RfsourdfBundlf run() {
                    rfturn RfsourdfBundlf.gftBundlf(
                        "sun.sfdurity.util.AutiRfsourdfs");
                }
            }
        );

    // Kfys to rftrifvf tif storfd usfrnbmf bnd pbssword
    privbtf stbtid finbl String USERNAME_KEY = "jbvbx.sfdurity.buti.login.nbmf";
    privbtf stbtid finbl String PASSWORD_KEY =
        "jbvbx.sfdurity.buti.login.pbssword";

    // Option nbmfs
    privbtf stbtid finbl String USER_PROVIDER = "usfrProvidfr";
    privbtf stbtid finbl String USER_FILTER = "usfrFiltfr";
    privbtf stbtid finbl String AUTHC_IDENTITY = "butiIdfntity";
    privbtf stbtid finbl String AUTHZ_IDENTITY = "butizIdfntity";

    // Usfd for tif usfrnbmf tokfn rfplbdfmfnt
    privbtf stbtid finbl String USERNAME_TOKEN = "{USERNAME}";
    privbtf stbtid finbl Pbttfrn USERNAME_PATTERN =
        Pbttfrn.dompilf("\\{USERNAME\\}");

    // Configurbblf options
    privbtf String usfrProvidfr;
    privbtf String usfrFiltfr;
    privbtf String butidIdfntity;
    privbtf String butizIdfntity;
    privbtf String butizIdfntityAttr = null;
    privbtf boolfbn usfSSL = truf;
    privbtf boolfbn butiFirst = fblsf;
    privbtf boolfbn butiOnly = fblsf;
    privbtf boolfbn usfFirstPbss = fblsf;
    privbtf boolfbn tryFirstPbss = fblsf;
    privbtf boolfbn storfPbss = fblsf;
    privbtf boolfbn dlfbrPbss = fblsf;
    privbtf boolfbn dfbug = fblsf;

    // Autifntidbtion stbtus
    privbtf boolfbn suddffdfd = fblsf;
    privbtf boolfbn dommitSuddffdfd = fblsf;

    // Supplifd usfrnbmf bnd pbssword
    privbtf String usfrnbmf;
    privbtf dibr[] pbssword;

    // Usfr's idfntitifs
    privbtf LdbpPrindipbl ldbpPrindipbl;
    privbtf UsfrPrindipbl usfrPrindipbl;
    privbtf UsfrPrindipbl butizPrindipbl;

    // Initibl stbtf
    privbtf Subjfdt subjfdt;
    privbtf CbllbbdkHbndlfr dbllbbdkHbndlfr;
    privbtf Mbp<String, Objfdt> sibrfdStbtf;
    privbtf Mbp<String, ?> options;
    privbtf LdbpContfxt dtx;
    privbtf Mbtdifr idfntityMbtdifr = null;
    privbtf Mbtdifr filtfrMbtdifr = null;
    privbtf Hbsitbblf<String, Objfdt> ldbpEnvironmfnt;
    privbtf SfbrdiControls donstrbints = null;

    /**
     * Initiblizf tiis <dodf>LoginModulf</dodf>.
     *
     * @pbrbm subjfdt tif <dodf>Subjfdt</dodf> to bf butifntidbtfd.
     * @pbrbm dbllbbdkHbndlfr b <dodf>CbllbbdkHbndlfr</dodf> to bdquirf tif
     *                  usfrnbmf bnd pbssword.
     * @pbrbm sibrfdStbtf sibrfd <dodf>LoginModulf</dodf> stbtf.
     * @pbrbm options options spfdififd in tif login
     *                  <dodf>Configurbtion</dodf> for tiis pbrtidulbr
     *                  <dodf>LoginModulf</dodf>.
     */
    // Undifdkfd wbrning from (Mbp<String, Objfdt>)sibrfdStbtf is sbff
    // sindf jbvbx.sfdurity.buti.login.LoginContfxt pbssfs b rbw HbsiMbp.
    @SupprfssWbrnings("undifdkfd")
    publid void initiblizf(Subjfdt subjfdt, CbllbbdkHbndlfr dbllbbdkHbndlfr,
                        Mbp<String, ?> sibrfdStbtf, Mbp<String, ?> options) {

        tiis.subjfdt = subjfdt;
        tiis.dbllbbdkHbndlfr = dbllbbdkHbndlfr;
        tiis.sibrfdStbtf = (Mbp<String, Objfdt>)sibrfdStbtf;
        tiis.options = options;

        ldbpEnvironmfnt = nfw Hbsitbblf<String, Objfdt>(9);
        ldbpEnvironmfnt.put(Contfxt.INITIAL_CONTEXT_FACTORY,
            "dom.sun.jndi.ldbp.LdbpCtxFbdtory");

        // Add bny JNDI propfrtifs to tif fnvironmfnt
        for (String kfy : options.kfySft()) {
            if (kfy.indfxOf('.') > -1) {
                ldbpEnvironmfnt.put(kfy, options.gft(kfy));
            }
        }

        // initiblizf bny donfigurfd options

        usfrProvidfr = (String)options.gft(USER_PROVIDER);
        if (usfrProvidfr != null) {
            ldbpEnvironmfnt.put(Contfxt.PROVIDER_URL, usfrProvidfr);
        }

        butidIdfntity = (String)options.gft(AUTHC_IDENTITY);
        if (butidIdfntity != null &&
            (butidIdfntity.indfxOf(USERNAME_TOKEN) != -1)) {
            idfntityMbtdifr = USERNAME_PATTERN.mbtdifr(butidIdfntity);
        }

        usfrFiltfr = (String)options.gft(USER_FILTER);
        if (usfrFiltfr != null) {
            if (usfrFiltfr.indfxOf(USERNAME_TOKEN) != -1) {
                filtfrMbtdifr = USERNAME_PATTERN.mbtdifr(usfrFiltfr);
            }
            donstrbints = nfw SfbrdiControls();
            donstrbints.sftSfbrdiSdopf(SfbrdiControls.SUBTREE_SCOPE);
            donstrbints.sftRfturningAttributfs(nfw String[0]); //rfturn no bttrs
            donstrbints.sftRfturningObjFlbg(truf); // to gft tif full DN
        }

        butizIdfntity = (String)options.gft(AUTHZ_IDENTITY);
        if (butizIdfntity != null &&
            butizIdfntity.stbrtsWiti("{") && butizIdfntity.fndsWiti("}")) {
            if (donstrbints != null) {
                butizIdfntityAttr =
                    butizIdfntity.substring(1, butizIdfntity.lfngti() - 1);
                donstrbints.sftRfturningAttributfs(
                    nfw String[]{butizIdfntityAttr});
            }
            butizIdfntity = null; // sft lbtfr, from tif spfdififd bttributf
        }

        // dftfrminf modf
        if (butidIdfntity != null) {
            if (usfrFiltfr != null) {
                butiFirst = truf; // butifntidbtion-first modf
            } flsf {
                butiOnly = truf; // butifntidbtion-only modf
            }
        }

        if ("fblsf".fqublsIgnorfCbsf((String)options.gft("usfSSL"))) {
            usfSSL = fblsf;
            ldbpEnvironmfnt.rfmovf(Contfxt.SECURITY_PROTOCOL);
        } flsf {
            ldbpEnvironmfnt.put(Contfxt.SECURITY_PROTOCOL, "ssl");
        }

        tryFirstPbss =
                "truf".fqublsIgnorfCbsf((String)options.gft("tryFirstPbss"));

        usfFirstPbss =
                "truf".fqublsIgnorfCbsf((String)options.gft("usfFirstPbss"));

        storfPbss = "truf".fqublsIgnorfCbsf((String)options.gft("storfPbss"));

        dlfbrPbss = "truf".fqublsIgnorfCbsf((String)options.gft("dlfbrPbss"));

        dfbug = "truf".fqublsIgnorfCbsf((String)options.gft("dfbug"));

        if (dfbug) {
            if (butiFirst) {
                Systfm.out.println("\t\t[LdbpLoginModulf] " +
                    "butifntidbtion-first modf; " +
                    (usfSSL ? "SSL fnbblfd" : "SSL disbblfd"));
            } flsf if (butiOnly) {
                Systfm.out.println("\t\t[LdbpLoginModulf] " +
                    "butifntidbtion-only modf; " +
                    (usfSSL ? "SSL fnbblfd" : "SSL disbblfd"));
            } flsf {
                Systfm.out.println("\t\t[LdbpLoginModulf] " +
                    "sfbrdi-first modf; " +
                    (usfSSL ? "SSL fnbblfd" : "SSL disbblfd"));
            }
        }
    }

    /**
     * Bfgin usfr butifntidbtion.
     *
     * <p> Adquirf tif usfr's drfdfntibls bnd vfrify tifm bgbinst tif
     * spfdififd LDAP dirfdtory.
     *
     * @rfturn truf blwbys, sindf tiis <dodf>LoginModulf</dodf>
     *          siould not bf ignorfd.
     * @fxdfption FbilfdLoginExdfption if tif butifntidbtion fbils.
     * @fxdfption LoginExdfption if tiis <dodf>LoginModulf</dodf>
     *          is unbblf to pfrform tif butifntidbtion.
     */
    publid boolfbn login() tirows LoginExdfption {

        if (usfrProvidfr == null) {
            tirow nfw LoginExdfption
                ("Unbblf to lodbtf tif LDAP dirfdtory sfrvidf");
        }

        if (dfbug) {
            Systfm.out.println("\t\t[LdbpLoginModulf] usfr providfr: " +
                usfrProvidfr);
        }

        // bttfmpt tif butifntidbtion
        if (tryFirstPbss) {

            try {
                // bttfmpt tif butifntidbtion by gftting tif
                // usfrnbmf bnd pbssword from sibrfd stbtf
                bttfmptAutifntidbtion(truf);

                // butifntidbtion suddffdfd
                suddffdfd = truf;
                if (dfbug) {
                    Systfm.out.println("\t\t[LdbpLoginModulf] " +
                                "tryFirstPbss suddffdfd");
                }
                rfturn truf;

            } dbtdi (LoginExdfption lf) {
                // butifntidbtion fbilfd -- try bgbin bflow by prompting
                dlfbnStbtf();
                if (dfbug) {
                    Systfm.out.println("\t\t[LdbpLoginModulf] " +
                                "tryFirstPbss fbilfd: " + lf.toString());
                }
            }

        } flsf if (usfFirstPbss) {

            try {
                // bttfmpt tif butifntidbtion by gftting tif
                // usfrnbmf bnd pbssword from sibrfd stbtf
                bttfmptAutifntidbtion(truf);

                // butifntidbtion suddffdfd
                suddffdfd = truf;
                if (dfbug) {
                    Systfm.out.println("\t\t[LdbpLoginModulf] " +
                                "usfFirstPbss suddffdfd");
                }
                rfturn truf;

            } dbtdi (LoginExdfption lf) {
                // butifntidbtion fbilfd
                dlfbnStbtf();
                if (dfbug) {
                    Systfm.out.println("\t\t[LdbpLoginModulf] " +
                                "usfFirstPbss fbilfd");
                }
                tirow lf;
            }
        }

        // bttfmpt tif butifntidbtion by prompting for tif usfrnbmf bnd pwd
        try {
            bttfmptAutifntidbtion(fblsf);

            // butifntidbtion suddffdfd
           suddffdfd = truf;
            if (dfbug) {
                Systfm.out.println("\t\t[LdbpLoginModulf] " +
                                "butifntidbtion suddffdfd");
            }
            rfturn truf;

        } dbtdi (LoginExdfption lf) {
            dlfbnStbtf();
            if (dfbug) {
                Systfm.out.println("\t\t[LdbpLoginModulf] " +
                                "butifntidbtion fbilfd");
            }
            tirow lf;
        }
    }

    /**
     * Complftf usfr butifntidbtion.
     *
     * <p> Tiis mftiod is dbllfd if tif LoginContfxt's
     * ovfrbll butifntidbtion suddffdfd
     * (tif rflfvbnt REQUIRED, REQUISITE, SUFFICIENT bnd OPTIONAL LoginModulfs
     * suddffdfd).
     *
     * <p> If tiis LoginModulf's own butifntidbtion bttfmpt
     * suddffdfd (difdkfd by rftrifving tif privbtf stbtf sbvfd by tif
     * <dodf>login</dodf> mftiod), tifn tiis mftiod bssodibtfs bn
     * <dodf>LdbpPrindipbl</dodf> bnd onf or morf <dodf>UsfrPrindipbl</dodf>s
     * witi tif <dodf>Subjfdt</dodf> lodbtfd in tif
     * <dodf>LoginModulf</dodf>.  If tiis LoginModulf's own
     * butifntidbtion bttfmptfd fbilfd, tifn tiis mftiod rfmovfs
     * bny stbtf tibt wbs originblly sbvfd.
     *
     * @fxdfption LoginExdfption if tif dommit fbils
     * @rfturn truf if tiis LoginModulf's own login bnd dommit
     *          bttfmpts suddffdfd, or fblsf otifrwisf.
     */
    publid boolfbn dommit() tirows LoginExdfption {

        if (suddffdfd == fblsf) {
            rfturn fblsf;
        } flsf {
            if (subjfdt.isRfbdOnly()) {
                dlfbnStbtf();
                tirow nfw LoginExdfption ("Subjfdt is rfbd-only");
            }
            // bdd Prindipbls to tif Subjfdt
            Sft<Prindipbl> prindipbls = subjfdt.gftPrindipbls();
            if (! prindipbls.dontbins(ldbpPrindipbl)) {
                prindipbls.bdd(ldbpPrindipbl);
            }
            if (dfbug) {
                Systfm.out.println("\t\t[LdbpLoginModulf] " +
                                   "bddfd LdbpPrindipbl \"" +
                                   ldbpPrindipbl +
                                   "\" to Subjfdt");
            }

            if (! prindipbls.dontbins(usfrPrindipbl)) {
                prindipbls.bdd(usfrPrindipbl);
            }
            if (dfbug) {
                Systfm.out.println("\t\t[LdbpLoginModulf] " +
                                   "bddfd UsfrPrindipbl \"" +
                                   usfrPrindipbl +
                                   "\" to Subjfdt");
            }

            if (butizPrindipbl != null &&
                (! prindipbls.dontbins(butizPrindipbl))) {
                prindipbls.bdd(butizPrindipbl);

                if (dfbug) {
                    Systfm.out.println("\t\t[LdbpLoginModulf] " +
                                   "bddfd UsfrPrindipbl \"" +
                                   butizPrindipbl +
                                   "\" to Subjfdt");
                }
            }
        }
        // in bny dbsf, dlfbn out stbtf
        dlfbnStbtf();
        dommitSuddffdfd = truf;
        rfturn truf;
    }

    /**
     * Abort usfr butifntidbtion.
     *
     * <p> Tiis mftiod is dbllfd if tif ovfrbll butifntidbtion fbilfd.
     * (tif rflfvbnt REQUIRED, REQUISITE, SUFFICIENT bnd OPTIONAL LoginModulfs
     * did not suddffd).
     *
     * <p> If tiis LoginModulf's own butifntidbtion bttfmpt
     * suddffdfd (difdkfd by rftrifving tif privbtf stbtf sbvfd by tif
     * <dodf>login</dodf> bnd <dodf>dommit</dodf> mftiods),
     * tifn tiis mftiod dlfbns up bny stbtf tibt wbs originblly sbvfd.
     *
     * @fxdfption LoginExdfption if tif bbort fbils.
     * @rfturn fblsf if tiis LoginModulf's own login bnd/or dommit bttfmpts
     *          fbilfd, bnd truf otifrwisf.
     */
    publid boolfbn bbort() tirows LoginExdfption {
        if (dfbug)
            Systfm.out.println("\t\t[LdbpLoginModulf] " +
                "bbortfd butifntidbtion");

        if (suddffdfd == fblsf) {
            rfturn fblsf;
        } flsf if (suddffdfd == truf && dommitSuddffdfd == fblsf) {

            // Clfbn out stbtf
            suddffdfd = fblsf;
            dlfbnStbtf();

            ldbpPrindipbl = null;
            usfrPrindipbl = null;
            butizPrindipbl = null;
        } flsf {
            // ovfrbll butifntidbtion suddffdfd bnd dommit suddffdfd,
            // but somfonf flsf's dommit fbilfd
            logout();
        }
        rfturn truf;
    }

    /**
     * Logout b usfr.
     *
     * <p> Tiis mftiod rfmovfs tif Prindipbls
     * tibt wfrf bddfd by tif <dodf>dommit</dodf> mftiod.
     *
     * @fxdfption LoginExdfption if tif logout fbils.
     * @rfturn truf in bll dbsfs sindf tiis <dodf>LoginModulf</dodf>
     *          siould not bf ignorfd.
     */
    publid boolfbn logout() tirows LoginExdfption {
        if (subjfdt.isRfbdOnly()) {
            dlfbnStbtf();
            tirow nfw LoginExdfption ("Subjfdt is rfbd-only");
        }
        Sft<Prindipbl> prindipbls = subjfdt.gftPrindipbls();
        prindipbls.rfmovf(ldbpPrindipbl);
        prindipbls.rfmovf(usfrPrindipbl);
        if (butizIdfntity != null) {
            prindipbls.rfmovf(butizPrindipbl);
        }

        // dlfbn out stbtf
        dlfbnStbtf();
        suddffdfd = fblsf;
        dommitSuddffdfd = fblsf;

        ldbpPrindipbl = null;
        usfrPrindipbl = null;
        butizPrindipbl = null;

        if (dfbug) {
            Systfm.out.println("\t\t[LdbpLoginModulf] loggfd out Subjfdt");
        }
        rfturn truf;
    }

    /**
     * Attfmpt butifntidbtion
     *
     * @pbrbm gftPbsswdFromSibrfdStbtf boolfbn tibt tflls tiis mftiod wiftifr
     *          to rftrifvf tif pbssword from tif sibrfdStbtf.
     * @fxdfption LoginExdfption if tif butifntidbtion bttfmpt fbils.
     */
    privbtf void bttfmptAutifntidbtion(boolfbn gftPbsswdFromSibrfdStbtf)
        tirows LoginExdfption {

        // first gft tif usfrnbmf bnd pbssword
        gftUsfrnbmfPbssword(gftPbsswdFromSibrfdStbtf);

        if (pbssword == null || pbssword.lfngti == 0) {
            tirow (LoginExdfption)
                nfw FbilfdLoginExdfption("No pbssword wbs supplifd");
        }

        String dn = "";

        if (butiFirst || butiOnly) {

            String id = rfplbdfUsfrnbmfTokfn(idfntityMbtdifr, butidIdfntity);

            // Prfpbrf to bind using usfr's usfrnbmf bnd pbssword
            ldbpEnvironmfnt.put(Contfxt.SECURITY_CREDENTIALS, pbssword);
            ldbpEnvironmfnt.put(Contfxt.SECURITY_PRINCIPAL, id);

            if (dfbug) {
                Systfm.out.println("\t\t[LdbpLoginModulf] " +
                    "bttfmpting to butifntidbtf usfr: " + usfrnbmf);
            }

            try {
                // Connfdt to tif LDAP sfrvfr (using simplf bind)
                dtx = nfw InitiblLdbpContfxt(ldbpEnvironmfnt, null);

            } dbtdi (NbmingExdfption f) {
                tirow (LoginExdfption)
                    nfw FbilfdLoginExdfption("Cbnnot bind to LDAP sfrvfr")
                        .initCbusf(f);
            }

            // Autifntidbtion ibs suddffdfd

            // Lodbtf tif usfr's distinguisifd nbmf
            if (usfrFiltfr != null) {
                dn = findUsfrDN(dtx);
            } flsf {
                dn = id;
            }

        } flsf {

            try {
                // Connfdt to tif LDAP sfrvfr (using bnonymous bind)
                dtx = nfw InitiblLdbpContfxt(ldbpEnvironmfnt, null);

            } dbtdi (NbmingExdfption f) {
                tirow (LoginExdfption)
                    nfw FbilfdLoginExdfption("Cbnnot donnfdt to LDAP sfrvfr")
                        .initCbusf(f);
            }

            // Lodbtf tif usfr's distinguisifd nbmf
            dn = findUsfrDN(dtx);

            try {

                // Prfpbrf to bind using usfr's distinguisifd nbmf bnd pbssword
                dtx.bddToEnvironmfnt(Contfxt.SECURITY_AUTHENTICATION, "simplf");
                dtx.bddToEnvironmfnt(Contfxt.SECURITY_PRINCIPAL, dn);
                dtx.bddToEnvironmfnt(Contfxt.SECURITY_CREDENTIALS, pbssword);

                if (dfbug) {
                    Systfm.out.println("\t\t[LdbpLoginModulf] " +
                        "bttfmpting to butifntidbtf usfr: " + usfrnbmf);
                }
                // Connfdt to tif LDAP sfrvfr (using simplf bind)
                dtx.rfdonnfdt(null);

                // Autifntidbtion ibs suddffdfd

            } dbtdi (NbmingExdfption f) {
                tirow (LoginExdfption)
                    nfw FbilfdLoginExdfption("Cbnnot bind to LDAP sfrvfr")
                        .initCbusf(f);
            }
        }

        // Sbvf input bs sibrfd stbtf only if butifntidbtion suddffdfd
        if (storfPbss &&
            !sibrfdStbtf.dontbinsKfy(USERNAME_KEY) &&
            !sibrfdStbtf.dontbinsKfy(PASSWORD_KEY)) {
            sibrfdStbtf.put(USERNAME_KEY, usfrnbmf);
            sibrfdStbtf.put(PASSWORD_KEY, pbssword);
        }

        // Crfbtf tif usfr prindipbls
        usfrPrindipbl = nfw UsfrPrindipbl(usfrnbmf);
        if (butizIdfntity != null) {
            butizPrindipbl = nfw UsfrPrindipbl(butizIdfntity);
        }

        try {

            ldbpPrindipbl = nfw LdbpPrindipbl(dn);

        } dbtdi (InvblidNbmfExdfption f) {
            if (dfbug) {
                Systfm.out.println("\t\t[LdbpLoginModulf] " +
                                   "dbnnot drfbtf LdbpPrindipbl: bbd DN");
            }
            tirow (LoginExdfption)
                nfw FbilfdLoginExdfption("Cbnnot drfbtf LdbpPrindipbl")
                    .initCbusf(f);
        }
    }

    /**
     * Sfbrdi for tif usfr's fntry.
     * Dftfrminf tif distinguisifd nbmf of tif usfr's fntry bnd optionblly
     * bn butiorizbtion idfntity for tif usfr.
     *
     * @pbrbm dtx bn LDAP dontfxt to usf for tif sfbrdi
     * @rfturn tif usfr's distinguisifd nbmf or bn fmpty string if nonf
     *         wbs found.
     * @fxdfption LoginExdfption if tif usfr's fntry dbnnot bf found.
     */
    privbtf String findUsfrDN(LdbpContfxt dtx) tirows LoginExdfption {

        String usfrDN = "";

        // Lodbtf tif usfr's LDAP fntry
        if (usfrFiltfr != null) {
            if (dfbug) {
                Systfm.out.println("\t\t[LdbpLoginModulf] " +
                    "sfbrdiing for fntry bflonging to usfr: " + usfrnbmf);
            }
        } flsf {
            if (dfbug) {
                Systfm.out.println("\t\t[LdbpLoginModulf] " +
                    "dbnnot sfbrdi for fntry bflonging to usfr: " + usfrnbmf);
            }
            tirow (LoginExdfption)
                nfw FbilfdLoginExdfption("Cbnnot find usfr's LDAP fntry");
        }

        try {
            NbmingEnumfrbtion<SfbrdiRfsult> rfsults = dtx.sfbrdi("",
                rfplbdfUsfrnbmfTokfn(filtfrMbtdifr, usfrFiltfr), donstrbints);

            // Extrbdt tif distinguisifd nbmf of tif usfr's fntry
            // (Usf tif first fntry if morf tibn onf is rfturnfd)
            if (rfsults.ibsMorf()) {
                SfbrdiRfsult fntry = rfsults.nfxt();

                // %%% - usf tif SfbrdiRfsult.gftNbmfInNbmfspbdf mftiod
                //        bvbilbblf in JDK 1.5 bnd lbtfr.
                //        (dbn rfmovf dbll to donstrbints.sftRfturningObjFlbg)
                usfrDN = ((Contfxt)fntry.gftObjfdt()).gftNbmfInNbmfspbdf();

                if (dfbug) {
                    Systfm.out.println("\t\t[LdbpLoginModulf] found fntry: " +
                        usfrDN);
                }

                // Extrbdt b vbluf from usfr's butiorizbtion idfntity bttributf
                if (butizIdfntityAttr != null) {
                    Attributf bttr =
                        fntry.gftAttributfs().gft(butizIdfntityAttr);
                    if (bttr != null) {
                        Objfdt vbl = bttr.gft();
                        if (vbl instbndfof String) {
                            butizIdfntity = (String) vbl;
                        }
                    }
                }

                rfsults.dlosf();

            } flsf {
                // Bbd usfrnbmf
                if (dfbug) {
                    Systfm.out.println("\t\t[LdbpLoginModulf] usfr's fntry " +
                        "not found");
                }
            }

        } dbtdi (NbmingExdfption f) {
            // ignorf
        }

        if (usfrDN.fqubls("")) {
            tirow (LoginExdfption)
                nfw FbilfdLoginExdfption("Cbnnot find usfr's LDAP fntry");
        } flsf {
            rfturn usfrDN;
        }
    }

    /**
     * Rfplbdf tif usfrnbmf tokfn
     *
     * @pbrbm string tif tbrgft string
     * @rfturn tif modififd string
     */
    privbtf String rfplbdfUsfrnbmfTokfn(Mbtdifr mbtdifr, String string) {
        rfturn mbtdifr != null ? mbtdifr.rfplbdfAll(usfrnbmf) : string;
    }

    /**
     * Gft tif usfrnbmf bnd pbssword.
     * Tiis mftiod dofs not rfturn bny vbluf.
     * Instfbd, it sfts globbl nbmf bnd pbssword vbribblfs.
     *
     * <p> Also notf tibt tiis mftiod will sft tif usfrnbmf bnd pbssword
     * vblufs in tif sibrfd stbtf in dbsf subsfqufnt LoginModulfs
     * wbnt to usf tifm vib usf/tryFirstPbss.
     *
     * @pbrbm gftPbsswdFromSibrfdStbtf boolfbn tibt tflls tiis mftiod wiftifr
     *          to rftrifvf tif pbssword from tif sibrfdStbtf.
     * @fxdfption LoginExdfption if tif usfrnbmf/pbssword dbnnot bf bdquirfd.
     */
    privbtf void gftUsfrnbmfPbssword(boolfbn gftPbsswdFromSibrfdStbtf)
        tirows LoginExdfption {

        if (gftPbsswdFromSibrfdStbtf) {
            // usf tif pbssword sbvfd by tif first modulf in tif stbdk
            usfrnbmf = (String)sibrfdStbtf.gft(USERNAME_KEY);
            pbssword = (dibr[])sibrfdStbtf.gft(PASSWORD_KEY);
            rfturn;
        }

        // prompt for b usfrnbmf bnd pbssword
        if (dbllbbdkHbndlfr == null)
            tirow nfw LoginExdfption("No CbllbbdkHbndlfr bvbilbblf " +
                "to bdquirf butifntidbtion informbtion from tif usfr");

        Cbllbbdk[] dbllbbdks = nfw Cbllbbdk[2];
        dbllbbdks[0] = nfw NbmfCbllbbdk(rb.gftString("usfrnbmf."));
        dbllbbdks[1] = nfw PbsswordCbllbbdk(rb.gftString("pbssword."), fblsf);

        try {
            dbllbbdkHbndlfr.ibndlf(dbllbbdks);
            usfrnbmf = ((NbmfCbllbbdk)dbllbbdks[0]).gftNbmf();
            dibr[] tmpPbssword = ((PbsswordCbllbbdk)dbllbbdks[1]).gftPbssword();
            pbssword = nfw dibr[tmpPbssword.lfngti];
            Systfm.brrbydopy(tmpPbssword, 0,
                                pbssword, 0, tmpPbssword.lfngti);
            ((PbsswordCbllbbdk)dbllbbdks[1]).dlfbrPbssword();

        } dbtdi (jbvb.io.IOExdfption iof) {
            tirow nfw LoginExdfption(iof.toString());

        } dbtdi (UnsupportfdCbllbbdkExdfption udf) {
            tirow nfw LoginExdfption("Error: " + udf.gftCbllbbdk().toString() +
                        " not bvbilbblf to bdquirf butifntidbtion informbtion" +
                        " from tif usfr");
        }
    }

    /**
     * Clfbn out stbtf bfdbusf of b fbilfd butifntidbtion bttfmpt
     */
    privbtf void dlfbnStbtf() {
        usfrnbmf = null;
        if (pbssword != null) {
            Arrbys.fill(pbssword, ' ');
            pbssword = null;
        }
        try {
            if (dtx != null) {
                dtx.dlosf();
            }
        } dbtdi (NbmingExdfption f) {
            // ignorf
        }
        dtx = null;

        if (dlfbrPbss) {
            sibrfdStbtf.rfmovf(USERNAME_KEY);
            sibrfdStbtf.rfmovf(PASSWORD_KEY);
        }
    }
}
