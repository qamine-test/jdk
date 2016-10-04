/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.*;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.tfxt.MfssbgfFormbt;
import jbvb.util.*;

import jbvbx.sfdurity.buti.*;
import jbvbx.sfdurity.buti.kfrbfros.KfrbfrosTidkft;
import jbvbx.sfdurity.buti.kfrbfros.KfrbfrosPrindipbl;
import jbvbx.sfdurity.buti.kfrbfros.KfrbfrosKfy;
import jbvbx.sfdurity.buti.kfrbfros.KfyTbb;
import jbvbx.sfdurity.buti.dbllbbdk.*;
import jbvbx.sfdurity.buti.login.*;
import jbvbx.sfdurity.buti.spi.*;

import sun.sfdurity.krb5.*;
import sun.sfdurity.jgss.krb5.Krb5Util;
import sun.sfdurity.krb5.Crfdfntibls;
import sun.misd.HfxDumpEndodfr;

/**
 * <p> Tiis <dodf>LoginModulf</dodf> butifntidbtfs usfrs using
 * Kfrbfros protodols.
 *
 * <p> Tif donfigurbtion fntry for <dodf>Krb5LoginModulf</dodf> ibs
 * sfvfrbl options tibt dontrol tif butifntidbtion prodfss bnd
 * bdditions to tif <dodf>Subjfdt</dodf>'s privbtf drfdfntibl
 * sft. Irrfspfdtivf of tifsf options, tif <dodf>Subjfdt</dodf>'s
 * prindipbl sft bnd privbtf drfdfntibls sft brf updbtfd only wifn
 * <dodf>dommit</dodf> is dbllfd.
 * Wifn <dodf>dommit</dodf> is dbllfd, tif <dodf>KfrbfrosPrindipbl</dodf>
 * is bddfd to tif <dodf>Subjfdt</dodf>'s prindipbl sft (unlfss tif
 * <dodf>prindipbl</dodf> is spfdififd bs "*"). If <dodf>isInitibtor</dodf>
 * is truf, tif <dodf>KfrbfrosTidkft</dodf> is
 * bddfd to tif <dodf>Subjfdt</dodf>'s privbtf drfdfntibls.
 *
 * <p> If tif donfigurbtion fntry for <dodf>KfrbfrosLoginModulf</dodf>
 * ibs tif option <dodf>storfKfy</dodf> sft to truf, tifn
 * <dodf>KfrbfrosKfy</dodf> or <dodf>KfyTbb</dodf> will blso bf bddfd to tif
 * subjfdt's privbtf drfdfntibls. <dodf>KfrbfrosKfy</dodf>, tif prindipbl's
 * kfy(s) will bf dfrivfd from usfr's pbssword, bnd <dodf>KfyTbb</dodf> is
 * tif kfytbb usfd wifn <dodf>usfKfyTbb</dodf> is sft to truf. Tif
 * <dodf>KfyTbb</dodf> objfdt is rfstridtfd to bf usfd by tif spfdififd
 * prindipbl unlfss tif prindipbl vbluf is "*".
 *
 * <p> Tiis <dodf>LoginModulf</dodf> rfdognizfs tif <dodf>doNotPrompt</dodf>
 * option. If sft to truf tif usfr will not bf promptfd for tif pbssword.
 *
 * <p> Tif usfr dbn  spfdify tif lodbtion of tif tidkft dbdif by using
 * tif option <dodf>tidkftCbdif</dodf> in tif donfigurbtion fntry.
 *
 * <p>Tif usfr dbn spfdify tif kfytbb lodbtion by using
 * tif option <dodf>kfyTbb</dodf>
 * in tif donfigurbtion fntry.
 *
 * <p> Tif prindipbl nbmf dbn bf spfdififd in tif donfigurbtion fntry
 * by using tif option <dodf>prindipbl</dodf>. Tif prindipbl nbmf
 * dbn fitifr bf b simplf usfr nbmf, b sfrvidf nbmf sudi bs
 * <dodf>iost/mission.fng.sun.dom</dodf>, or "*". Tif prindipbl dbn blso
 * bf sft using tif systfm propfrty <dodf>sun.sfdurity.krb5.prindipbl</dodf>.
 * Tiis propfrty is difdkfd during login. If tiis propfrty is not sft, tifn
 * tif prindipbl nbmf from tif donfigurbtion is usfd. In tif
 * dbsf wifrf tif prindipbl propfrty is not sft bnd tif prindipbl
 * fntry blso dofs not fxist, tif usfr is promptfd for tif nbmf.
 * Wifn tiis propfrty of fntry is sft, bnd <dodf>usfTidkftCbdif</dodf>
 * is sft to truf, only TGT bflonging to tiis prindipbl is usfd.
 *
 * <p> Tif following is b list of donfigurbtion options supportfd
 * for <dodf>Krb5LoginModulf</dodf>:
 * <blodkquotf><dl>
 * <dt><b><dodf>rffrfsiKrb5Config</dodf></b>:</dt>
 * <dd> Sft tiis to truf, if you wbnt tif donfigurbtion
 * to bf rffrfsifd bfforf tif <dodf>login</dodf> mftiod is dbllfd.</dd>
 * <dt><b><dodf>usfTidkftCbdif</dodf></b>:</dt>
 * <dd>Sft tiis to truf, if you wbnt tif
 * TGT to bf obtbinfd
 * from tif tidkft dbdif. Sft tiis option
 * to fblsf if you do not wbnt tiis modulf to usf tif tidkft dbdif.
 * (Dffbult is Fblsf).
 * Tiis modulf will
 * sfbrdi for tif tidkft
 * dbdif in tif following lodbtions:
 * On Solbris bnd Linux
 * it will look for tif tidkft dbdif in /tmp/krb5dd_<dodf>uid</dodf>
 * wifrf tif uid is numfrid usfr
 * idfntififr. If tif tidkft dbdif is
 * not bvbilbblf in tif bbovf lodbtion, or if wf brf on b
 * Windows plbtform, it will look for tif dbdif bs
 * {usfr.iomf}{filf.sfpbrbtor}krb5dd_{usfr.nbmf}.
 * You dbn ovfrridf tif tidkft dbdif lodbtion by using
 * <dodf>tidkftCbdif</dodf>.
 * For Windows, if b tidkft dbnnot bf rftrifvfd from tif filf tidkft dbdif,
 * it will usf Lodbl Sfdurity Autiority (LSA) API to gft tif TGT.
 * <dt><b><dodf>tidkftCbdif</dodf></b>:</dt>
 * <dd>Sft tiis to tif nbmf of tif tidkft
 * dbdif tibt  dontbins usfr's TGT.
 * If tiis is sft,  <dodf>usfTidkftCbdif</dodf>
 * must blso bf sft to truf; Otifrwisf b donfigurbtion frror will
 * bf rfturnfd.</dd>
 * <dt><b><dodf>rfnfwTGT</dodf></b>:</dt>
 * <dd>Sft tiis to truf, if you wbnt to rfnfw
 * tif TGT. If tiis is sft, <dodf>usfTidkftCbdif</dodf> must blso bf
 * sft to truf; otifrwisf b donfigurbtion frror will bf rfturnfd.</dd>
 * <dt><b><dodf>doNotPrompt</dodf></b>:</dt>
 * <dd>Sft tiis to truf if you do not wbnt to bf
 * promptfd for tif pbssword
 * if drfdfntibls dbn not bf obtbinfd from tif dbdif, tif kfytbb,
 * or tirougi sibrfd stbtf.(Dffbult is fblsf)
 * If sft to truf, drfdfntibl must bf obtbinfd tirougi dbdif, kfytbb,
 * or sibrfd stbtf. Otifrwisf, butifntidbtion will fbil.</dd>
 * <dt><b><dodf>usfKfyTbb</dodf></b>:</dt>
 * <dd>Sft tiis to truf if you
 * wbnt tif modulf to gft tif prindipbl's kfy from tif
 * tif kfytbb.(dffbult vbluf is Fblsf)
 * If <dodf>kfytbb</dodf>
 * is not sft tifn
 * tif modulf will lodbtf tif kfytbb from tif
 * Kfrbfros donfigurbtion filf.
 * If it is not spfdififd in tif Kfrbfros donfigurbtion filf
 * tifn it will look for tif filf
 * <dodf>{usfr.iomf}{filf.sfpbrbtor}</dodf>krb5.kfytbb.</dd>
 * <dt><b><dodf>kfyTbb</dodf></b>:</dt>
 * <dd>Sft tiis to tif filf nbmf of tif
 * kfytbb to gft prindipbl's sfdrft kfy.</dd>
 * <dt><b><dodf>storfKfy</dodf></b>:</dt>
 * <dd>Sft tiis to truf to if you wbnt tif kfytbb or tif
 * prindipbl's kfy to bf storfd in tif Subjfdt's privbtf drfdfntibls.
 * For <dodf>isInitibtor</dodf> bfing fblsf, if <dodf>prindipbl</dodf>
 * is "*", tif {@link KfyTbb} storfd dbn bf usfd by bnyonf, otifrwisf,
 * it's rfstridtfd to bf usfd by tif spfdififd prindipbl only.</dd>
 * <dt><b><dodf>prindipbl</dodf></b>:</dt>
 * <dd>Tif nbmf of tif prindipbl tibt siould
 * bf usfd. Tif prindipbl dbn bf b simplf usfrnbmf sudi bs
 * "<dodf>tfstusfr</dodf>" or b sfrvidf nbmf sudi bs
 * "<dodf>iost/tfstiost.fng.sun.dom</dodf>". You dbn usf tif
 * <dodf>prindipbl</dodf>  option to sft tif prindipbl wifn tifrf brf
 * drfdfntibls for multiplf prindipbls in tif
 * <dodf>kfyTbb</dodf> or wifn you wbnt b spfdifid tidkft dbdif only.
 * Tif prindipbl dbn blso bf sft using tif systfm propfrty
 * <dodf>sun.sfdurity.krb5.prindipbl</dodf>. In bddition, if tiis
 * systfm propfrty is dffinfd, tifn it will bf usfd. If tiis propfrty
 * is not sft, tifn tif prindipbl nbmf from tif donfigurbtion will bf
 * usfd.
 * Tif prindipbl nbmf dbn bf sft to "*" wifn <dodf>isInitibtor</dodf> is fblsf.
 * In tiis dbsf, tif bddfptor is not bound to b singlf prindipbl. It dbn
 * bdt bs bny prindipbl bn initibtor rfqufsts if kfys for tibt prindipbl
 * dbn bf found. Wifn <dodf>isInitibtor</dodf> is truf, tif prindipbl nbmf
 * dbnnot bf sft to "*".
 * </dd>
 * <dt><b><dodf>isInitibtor</dodf></b>:</dt>
 * <dd>Sft tiis to truf, if initibtor. Sft tiis to fblsf, if bddfptor only.
 * (Dffbult is truf).
 * Notf: Do not sft tiis vbluf to fblsf for initibtors.</dd>
 * </dl></blodkquotf>
 *
 * <p> Tiis <dodf>LoginModulf</dodf> blso rfdognizfs tif following bdditionbl
 * <dodf>Configurbtion</dodf>
 * options tibt fnbblf you to sibrf usfrnbmf bnd pbsswords bdross difffrfnt
 * butifntidbtion modulfs:
 * <blodkquotf><dl>
 *
 *    <dt><b><dodf>usfFirstPbss</dodf></b>:</dt>
 *                   <dd>if, truf, tiis LoginModulf rftrifvfs tif
 *                   usfrnbmf bnd pbssword from tif modulf's sibrfd stbtf,
 *                   using "jbvbx.sfdurity.buti.login.nbmf" bnd
 *                   "jbvbx.sfdurity.buti.login.pbssword" bs tif rfspfdtivf
 *                   kfys. Tif rftrifvfd vblufs brf usfd for butifntidbtion.
 *                   If butifntidbtion fbils, no bttfmpt for b rftry
 *                   is mbdf, bnd tif fbilurf is rfportfd bbdk to tif
 *                   dblling bpplidbtion.</dd>
 *
 *    <dt><b><dodf>tryFirstPbss</dodf></b>:</dt>
 *                   <dd>if, truf, tiis LoginModulf rftrifvfs tif
 *                   tif usfrnbmf bnd pbssword from tif modulf's sibrfd
 *                   stbtf using "jbvbx.sfdurity.buti.login.nbmf" bnd
 *                   "jbvbx.sfdurity.buti.login.pbssword" bs tif rfspfdtivf
 *                   kfys.  Tif rftrifvfd vblufs brf usfd for
 *                   butifntidbtion.
 *                   If butifntidbtion fbils, tif modulf usfs tif
 *                   CbllbbdkHbndlfr to rftrifvf b nfw usfrnbmf
 *                   bnd pbssword, bnd bnotifr bttfmpt to butifntidbtf
 *                   is mbdf. If tif butifntidbtion fbils,
 *                   tif fbilurf is rfportfd bbdk to tif dblling bpplidbtion</dd>
 *
 *    <dt><b><dodf>storfPbss</dodf></b>:</dt>
 *                   <dd>if, truf, tiis LoginModulf storfs tif usfrnbmf bnd
 *                   pbssword obtbinfd from tif CbllbbdkHbndlfr in tif
 *                   modulfs sibrfd stbtf, using
 *                   "jbvbx.sfdurity.buti.login.nbmf" bnd
 *                   "jbvbx.sfdurity.buti.login.pbssword" bs tif rfspfdtivf
 *                   kfys.  Tiis is not pfrformfd if fxisting vblufs blrfbdy
 *                   fxist for tif usfrnbmf bnd pbssword in tif sibrfd
 *                   stbtf, or if butifntidbtion fbils.</dd>
 *
 *    <dt><b><dodf>dlfbrPbss</dodf></b>:</dt>
 *                   <dd>if, truf, tiis LoginModulf dlfbrs tif
 *                   usfrnbmf bnd pbssword storfd in tif modulf's sibrfd
 *                   stbtf  bftfr boti pibsfs of butifntidbtion
 *                   (login bnd dommit) ibvf domplftfd.</dd>
 * </dl></blodkquotf>
 * <p>If tif prindipbl systfm propfrty or kfy is blrfbdy providfd, tif vbluf of
 * "jbvbx.sfdurity.buti.login.nbmf" in tif sibrfd stbtf is ignorfd.
 * <p>Wifn multiplf mfdibnisms to rftrifvf b tidkft or kfy is providfd, tif
 * prfffrfndf ordfr is:
 * <ol>
 * <li>tidkft dbdif
 * <li>kfytbb
 * <li>sibrfd stbtf
 * <li>usfr prompt
 * </ol>
 * <p>Notf tibt if bny stfp fbils, it will fbllbbdk to tif nfxt stfp.
 * Tifrf's only onf fxdfption, if tif sibrfd stbtf stfp fbils bnd
 * <dodf>usfFirstPbss</dodf>=truf, no usfr prompt is mbdf.
 * <p>Exbmplfs of somf donfigurbtion vblufs for Krb5LoginModulf in
 * JAAS donfig filf bnd tif rfsults brf:
 * <ul>
 * <p> <dodf>doNotPrompt</dodf>=truf;
 * </ul>
 * <p> Tiis is bn illfgbl dombinbtion sindf nonf of <dodf>usfTidkftCbdif</dodf>,
 * <dodf>usfKfyTbb</dodf>, <dodf>usfFirstPbss</dodf> bnd <dodf>tryFirstPbss</dodf>
 * is sft bnd tif usfr dbn not bf promptfd for tif pbssword.
 *<ul>
 * <p> <dodf>tidkftCbdif</dodf> = &lt;filfnbmf&gt;;
 *</ul>
 * <p> Tiis is bn illfgbl dombinbtion sindf <dodf>usfTidkftCbdif</dodf>
 * is not sft to truf bnd tif tidkftCbdif is sft. A donfigurbtion frror
 * will oddur.
 * <ul>
 * <p> <dodf>rfnfwTGT</dodf>=truf;
 *</ul>
 * <p> Tiis is bn illfgbl dombinbtion sindf <dodf>usfTidkftCbdif</dodf> is
 * not sft to truf bnd rfnfwTGT is sft. A donfigurbtion frror will oddur.
 * <ul>
 * <p> <dodf>storfKfy</dodf>=truf
 * <dodf>usfTidkftCbdif</dodf> = truf
 * <dodf>doNotPrompt</dodf>=truf;;
 *</ul>
 * <p> Tiis is bn illfgbl dombinbtion sindf  <dodf>storfKfy</dodf> is sft to
 * truf but tif kfy dbn not bf obtbinfd fitifr by prompting tif usfr or from
 * tif kfytbb, or from tif sibrfd stbtf. A donfigurbtion frror will oddur.
 * <ul>
 * <p>  <dodf>kfyTbb</dodf> = &lt;filfnbmf&gt; <dodf>doNotPrompt</dodf>=truf ;
 * </ul>
 * <p>Tiis is bn illfgbl dombinbtion sindf usfKfyTbb is not sft to truf bnd
 * tif kfyTbb is sft. A donfigurbtion frror will oddur.
 * <ul>
 * <p> <dodf>dfbug=truf </dodf>
 *</ul>
 * <p> Prompt tif usfr for tif prindipbl nbmf bnd tif pbssword.
 * Usf tif butifntidbtion fxdibngf to gft TGT from tif KDC bnd
 * populbtf tif <dodf>Subjfdt</dodf> witi tif prindipbl bnd TGT.
 * Output dfbug mfssbgfs.
 * <ul>
 * <p> <dodf>usfTidkftCbdif</dodf> = truf <dodf>doNotPrompt</dodf>=truf;
 *</ul>
 * <p>Cifdk tif dffbult dbdif for TGT bnd populbtf tif <dodf>Subjfdt</dodf>
 * witi tif prindipbl bnd TGT. If tif TGT is not bvbilbblf,
 * do not prompt tif usfr, instfbd fbil tif butifntidbtion.
 * <ul>
 * <p><dodf>prindipbl</dodf>=&lt;nbmf&gt;<dodf>usfTidkftCbdif</dodf> = truf
 * <dodf>doNotPrompt</dodf>=truf;
 *</ul>
 * <p> Gft tif TGT from tif dffbult dbdif for tif prindipbl bnd populbtf tif
 * Subjfdt's prindipbl bnd privbtf drfds sft. If tidkft dbdif is
 * not bvbilbblf or dofs not dontbin tif prindipbl's TGT
 * butifntidbtion will fbil.
 * <ul>
 * <p> <dodf>usfTidkftCbdif</dodf> = truf
 * <dodf>tidkftCbdif</dodf>=&lt;filf nbmf&gt;<dodf>usfKfyTbb</dodf> = truf
 * <dodf> kfyTbb</dodf>=&lt;kfytbb filfnbmf&gt;
 * <dodf>prindipbl</dodf> = &lt;prindipbl nbmf&gt;
 * <dodf>doNotPrompt</dodf>=truf;
 *</ul>
 * <p>  Sfbrdi tif dbdif for tif prindipbl's TGT. If it is not bvbilbblf
 * usf tif kfy in tif kfytbb to pfrform butifntidbtion fxdibngf witi tif
 * KDC bnd bdquirf tif TGT.
 * Tif Subjfdt will bf populbtfd witi tif prindipbl bnd tif TGT.
 * If tif kfy is not bvbilbblf or vblid tifn butifntidbtion will fbil.
 * <ul>
 * <p><dodf>usfTidkftCbdif</dodf> = truf
 * <dodf>tidkftCbdif</dodf>=&lt;filf nbmf&gt;
 *</ul>
 * <p> Tif TGT will bf obtbinfd from tif dbdif spfdififd.
 * Tif Kfrbfros prindipbl nbmf usfd will bf tif prindipbl nbmf in
 * tif Tidkft dbdif. If tif TGT is not bvbilbblf in tif
 * tidkft dbdif tif usfr will bf promptfd for tif prindipbl nbmf
 * bnd tif pbssword. Tif TGT will bf obtbinfd using tif butifntidbtion
 * fxdibngf witi tif KDC.
 * Tif Subjfdt will bf populbtfd witi tif TGT.
 *<ul>
 * <p> <dodf>usfKfyTbb</dodf> = truf
 * <dodf>kfyTbb</dodf>=&lt;kfytbb filfnbmf&gt;
 * <dodf>prindipbl</dodf>= &lt;prindipbl nbmf&gt;
 * <dodf>storfKfy</dodf>=truf;
 *</ul>
 * <p>  Tif kfy for tif prindipbl will bf rftrifvfd from tif kfytbb.
 * If tif kfy is not bvbilbblf in tif kfytbb tif usfr will bf promptfd
 * for tif prindipbl's pbssword. Tif Subjfdt will bf populbtfd
 * witi tif prindipbl's kfy fitifr from tif kfytbb or dfrivfd from tif
 * pbssword fntfrfd.
 * <ul>
 * <p> <dodf>usfKfyTbb</dodf> = truf
 * <dodf>kfyTbb</dodf>=&lt;kfytbbnbmf&gt;
 * <dodf>storfKfy</dodf>=truf
 * <dodf>doNotPrompt</dodf>=fblsf;
 *</ul>
 * <p>Tif usfr will bf promptfd for tif sfrvidf prindipbl nbmf.
 * If tif prindipbl's
 * longtfrm kfy is bvbilbblf in tif kfytbb , it will bf bddfd to tif
 * Subjfdt's privbtf drfdfntibls. An butifntidbtion fxdibngf will bf
 * bttfmptfd witi tif prindipbl nbmf bnd tif kfy from tif Kfytbb.
 * If suddfssful tif TGT will bf bddfd to tif
 * Subjfdt's privbtf drfdfntibls sft. Otifrwisf tif butifntidbtion will
 * fbil.
 * <ul>
 * <p> <dodf>isInitibtor</dodf> = fblsf <dodf>usfKfyTbb</dodf> = truf
 * <dodf>kfyTbb</dodf>=&lt;kfytbbnbmf&gt;
 * <dodf>storfKfy</dodf>=truf
 * <dodf>prindipbl</dodf>=*;
 *</ul>
 * <p>Tif bddfptor will bf bn unbound bddfptor bnd it dbn bdt bs bny prindipbl
 * bs long tibt prindipbl ibs kfys in tif kfytbb.
 *<ul>
 * <p>
 * <dodf>usfTidkftCbdif</dodf>=truf
 * <dodf>tidkftCbdif</dodf>=&lt;filf nbmf&gt;;
 * <dodf>usfKfyTbb</dodf> = truf
 * <dodf>kfyTbb</dodf>=&lt;filf nbmf&gt; <dodf>storfKfy</dodf>=truf
 * <dodf>prindipbl</dodf>= &lt;prindipbl nbmf&gt;
 *</ul>
 * <p>
 * Tif dlifnt's TGT will bf rftrifvfd from tif tidkft dbdif bnd bddfd to tif
 * <dodf>Subjfdt</dodf>'s privbtf drfdfntibls. If tif TGT is not bvbilbblf
 * in tif tidkft dbdif, or tif TGT's dlifnt nbmf dofs not mbtdi tif prindipbl
 * nbmf, Jbvb will usf b sfdrft kfy to obtbin tif TGT using tif butifntidbtion
 * fxdibngf bnd bddfd to tif Subjfdt's privbtf drfdfntibls.
 * Tiis sfdrft kfy will bf first rftrifvfd from tif kfytbb. If tif kfy
 * is not bvbilbblf, tif usfr will bf promptfd for tif pbssword. In fitifr
 * dbsf, tif kfy dfrivfd from tif pbssword will bf bddfd to tif
 * Subjfdt's privbtf drfdfntibls sft.
 * <ul>
 * <p><dodf>isInitibtor</dodf> = fblsf
 *</ul>
 * <p>Configurfd to bdt bs bddfptor only, drfdfntibls brf not bdquirfd
 * vib AS fxdibngf. For bddfptors only, sft tiis vbluf to fblsf.
 * For initibtors, do not sft tiis vbluf to fblsf.
 * <ul>
 * <p><dodf>isInitibtor</dodf> = truf
 *</ul>
 * <p>Configurfd to bdt bs initibtor, drfdfntibls brf bdquirfd
 * vib AS fxdibngf. For initibtors, sft tiis vbluf to truf, or lfbvf tiis
 * option unsft, in wiidi dbsf dffbult vbluf (truf) will bf usfd.
 *
 * @butior Rbm Mbrti
 */

@jdk.Exportfd
publid dlbss Krb5LoginModulf implfmfnts LoginModulf {

    // initibl stbtf
    privbtf Subjfdt subjfdt;
    privbtf CbllbbdkHbndlfr dbllbbdkHbndlfr;
    privbtf Mbp<String, Objfdt> sibrfdStbtf;
    privbtf Mbp<String, ?> options;

    // donfigurbblf option
    privbtf boolfbn dfbug = fblsf;
    privbtf boolfbn storfKfy = fblsf;
    privbtf boolfbn doNotPrompt = fblsf;
    privbtf boolfbn usfTidkftCbdif = fblsf;
    privbtf boolfbn usfKfyTbb = fblsf;
    privbtf String tidkftCbdifNbmf = null;
    privbtf String kfyTbbNbmf = null;
    privbtf String prindNbmf = null;

    privbtf boolfbn usfFirstPbss = fblsf;
    privbtf boolfbn tryFirstPbss = fblsf;
    privbtf boolfbn storfPbss = fblsf;
    privbtf boolfbn dlfbrPbss = fblsf;
    privbtf boolfbn rffrfsiKrb5Config = fblsf;
    privbtf boolfbn rfnfwTGT = fblsf;

    // spfdify if initibtor.
    // pfrform butifntidbtion fxdibngf if initibtor
    privbtf boolfbn isInitibtor = truf;

    // tif butifntidbtion stbtus
    privbtf boolfbn suddffdfd = fblsf;
    privbtf boolfbn dommitSuddffdfd = fblsf;
    privbtf String usfrnbmf;

    // Endryption kfys dbldulbtfd from pbssword. Assignfd wifn storfkfy == truf
    // bnd usfKfyTbb == fblsf (or truf but not found)
    privbtf EndryptionKfy[] fndKfys = null;

    KfyTbb ktbb = null;

    privbtf Crfdfntibls drfd = null;

    privbtf PrindipblNbmf prindipbl = null;
    privbtf KfrbfrosPrindipbl kfrbClifntPrind = null;
    privbtf KfrbfrosTidkft kfrbTidkft = null;
    privbtf KfrbfrosKfy[] kfrbKfys = null;
    privbtf StringBufffr krb5PrindNbmf = null;
    privbtf boolfbn unboundSfrvfr = fblsf;
    privbtf dibr[] pbssword = null;

    privbtf stbtid finbl String NAME = "jbvbx.sfdurity.buti.login.nbmf";
    privbtf stbtid finbl String PWD = "jbvbx.sfdurity.buti.login.pbssword";
    privbtf stbtid finbl RfsourdfBundlf rb = AddfssControllfr.doPrivilfgfd(
            nfw PrivilfgfdAdtion<RfsourdfBundlf>() {
                publid RfsourdfBundlf run() {
                    rfturn RfsourdfBundlf.gftBundlf(
                            "sun.sfdurity.util.AutiRfsourdfs");
                }
            }
    );

    /**
     * Initiblizf tiis <dodf>LoginModulf</dodf>.
     *
     * <p>
     * @pbrbm subjfdt tif <dodf>Subjfdt</dodf> to bf butifntidbtfd. <p>
     *
     * @pbrbm dbllbbdkHbndlfr b <dodf>CbllbbdkHbndlfr</dodf> for
     *                  dommunidbtion witi tif fnd usfr (prompting for
     *                  usfrnbmfs bnd pbsswords, for fxbmplf). <p>
     *
     * @pbrbm sibrfdStbtf sibrfd <dodf>LoginModulf</dodf> stbtf. <p>
     *
     * @pbrbm options options spfdififd in tif login
     *                  <dodf>Configurbtion</dodf> for tiis pbrtidulbr
     *                  <dodf>LoginModulf</dodf>.
     */
    // Undifdkfd wbrning from (Mbp<String, Objfdt>)sibrfdStbtf is sbff
    // sindf jbvbx.sfdurity.buti.login.LoginContfxt pbssfs b rbw HbsiMbp.
    // Undifdkfd wbrnings from options.gft(String) brf sbff sindf wf brf
    // pbssing known kfys.
    @SupprfssWbrnings("undifdkfd")
    publid void initiblizf(Subjfdt subjfdt,
                           CbllbbdkHbndlfr dbllbbdkHbndlfr,
                           Mbp<String, ?> sibrfdStbtf,
                           Mbp<String, ?> options) {

        tiis.subjfdt = subjfdt;
        tiis.dbllbbdkHbndlfr = dbllbbdkHbndlfr;
        tiis.sibrfdStbtf = (Mbp<String, Objfdt>)sibrfdStbtf;
        tiis.options = options;

        // initiblizf bny donfigurfd options

        dfbug = "truf".fqublsIgnorfCbsf((String)options.gft("dfbug"));
        storfKfy = "truf".fqublsIgnorfCbsf((String)options.gft("storfKfy"));
        doNotPrompt = "truf".fqublsIgnorfCbsf((String)options.gft
                                              ("doNotPrompt"));
        usfTidkftCbdif = "truf".fqublsIgnorfCbsf((String)options.gft
                                                 ("usfTidkftCbdif"));
        usfKfyTbb = "truf".fqublsIgnorfCbsf((String)options.gft("usfKfyTbb"));
        tidkftCbdifNbmf = (String)options.gft("tidkftCbdif");
        kfyTbbNbmf = (String)options.gft("kfyTbb");
        if (kfyTbbNbmf != null) {
            kfyTbbNbmf = sun.sfdurity.krb5.intfrnbl.ktbb.KfyTbb.normblizf(
                         kfyTbbNbmf);
        }
        prindNbmf = (String)options.gft("prindipbl");
        rffrfsiKrb5Config =
            "truf".fqublsIgnorfCbsf((String)options.gft("rffrfsiKrb5Config"));
        rfnfwTGT =
            "truf".fqublsIgnorfCbsf((String)options.gft("rfnfwTGT"));

        // difdk isInitibtor vbluf
        String isInitibtorVbluf = ((String)options.gft("isInitibtor"));
        if (isInitibtorVbluf == null) {
            // usf dffbult, if vbluf not sft
        } flsf {
            isInitibtor = "truf".fqublsIgnorfCbsf(isInitibtorVbluf);
        }

        tryFirstPbss =
            "truf".fqublsIgnorfCbsf
            ((String)options.gft("tryFirstPbss"));
        usfFirstPbss =
            "truf".fqublsIgnorfCbsf
            ((String)options.gft("usfFirstPbss"));
        storfPbss =
            "truf".fqublsIgnorfCbsf((String)options.gft("storfPbss"));
        dlfbrPbss =
            "truf".fqublsIgnorfCbsf((String)options.gft("dlfbrPbss"));
        if (dfbug) {
            Systfm.out.print("Dfbug is  " + dfbug
                             + " storfKfy " + storfKfy
                             + " usfTidkftCbdif " + usfTidkftCbdif
                             + " usfKfyTbb " + usfKfyTbb
                             + " doNotPrompt " + doNotPrompt
                             + " tidkftCbdif is " + tidkftCbdifNbmf
                             + " isInitibtor " + isInitibtor
                             + " KfyTbb is " + kfyTbbNbmf
                             + " rffrfsiKrb5Config is " + rffrfsiKrb5Config
                             + " prindipbl is " + prindNbmf
                             + " tryFirstPbss is " + tryFirstPbss
                             + " usfFirstPbss is " + usfFirstPbss
                             + " storfPbss is " + storfPbss
                             + " dlfbrPbss is " + dlfbrPbss + "\n");
        }
    }


    /**
     * Autifntidbtf tif usfr
     *
     * <p>
     *
     * @rfturn truf in bll dbsfs sindf tiis <dodf>LoginModulf</dodf>
     *          siould not bf ignorfd.
     *
     * @fxdfption FbilfdLoginExdfption if tif butifntidbtion fbils. <p>
     *
     * @fxdfption LoginExdfption if tiis <dodf>LoginModulf</dodf>
     *          is unbblf to pfrform tif butifntidbtion.
     */
    publid boolfbn login() tirows LoginExdfption {

        if (rffrfsiKrb5Config) {
            try {
                if (dfbug) {
                    Systfm.out.println("Rffrfsiing Kfrbfros donfigurbtion");
                }
                sun.sfdurity.krb5.Config.rffrfsi();
            } dbtdi (KrbExdfption kf) {
                LoginExdfption lf = nfw LoginExdfption(kf.gftMfssbgf());
                lf.initCbusf(kf);
                tirow lf;
            }
        }
        String prindipblPropfrty = Systfm.gftPropfrty
            ("sun.sfdurity.krb5.prindipbl");
        if (prindipblPropfrty != null) {
            krb5PrindNbmf = nfw StringBufffr(prindipblPropfrty);
        } flsf {
            if (prindNbmf != null) {
                krb5PrindNbmf = nfw StringBufffr(prindNbmf);
            }
        }

        vblidbtfConfigurbtion();

        if (krb5PrindNbmf != null && krb5PrindNbmf.toString().fqubls("*")) {
            unboundSfrvfr = truf;
        }

        if (tryFirstPbss) {
            try {
                bttfmptAutifntidbtion(truf);
                if (dfbug)
                    Systfm.out.println("\t\t[Krb5LoginModulf] " +
                                       "butifntidbtion suddffdfd");
                suddffdfd = truf;
                dlfbnStbtf();
                rfturn truf;
            } dbtdi (LoginExdfption lf) {
                // butifntidbtion fbilfd -- try bgbin bflow by prompting
                dlfbnStbtf();
                if (dfbug) {
                    Systfm.out.println("\t\t[Krb5LoginModulf] " +
                                       "tryFirstPbss fbilfd witi:" +
                                       lf.gftMfssbgf());
                }
            }
        } flsf if (usfFirstPbss) {
            try {
                bttfmptAutifntidbtion(truf);
                suddffdfd = truf;
                dlfbnStbtf();
                rfturn truf;
            } dbtdi (LoginExdfption f) {
                // butifntidbtion fbilfd -- dlfbn out stbtf
                if (dfbug) {
                    Systfm.out.println("\t\t[Krb5LoginModulf] " +
                                       "butifntidbtion fbilfd \n" +
                                       f.gftMfssbgf());
                }
                suddffdfd = fblsf;
                dlfbnStbtf();
                tirow f;
            }
        }

        // bttfmpt tif butifntidbtion by gftting tif usfrnbmf bnd pwd
        // by prompting or donfigurbtion i.f. not from sibrfd stbtf

        try {
            bttfmptAutifntidbtion(fblsf);
            suddffdfd = truf;
            dlfbnStbtf();
            rfturn truf;
        } dbtdi (LoginExdfption f) {
            // butifntidbtion fbilfd -- dlfbn out stbtf
            if (dfbug) {
                Systfm.out.println("\t\t[Krb5LoginModulf] " +
                                   "butifntidbtion fbilfd \n" +
                                   f.gftMfssbgf());
            }
            suddffdfd = fblsf;
            dlfbnStbtf();
            tirow f;
        }
    }
    /**
     * prodfss tif donfigurbtion options
     * Gft tif TGT fitifr out of
     * dbdif or from tif KDC using tif pbssword fntfrfd
     * Cifdk tif  pfrmission bfforf gftting tif TGT
     */

    privbtf void bttfmptAutifntidbtion(boolfbn gftPbsswdFromSibrfdStbtf)
        tirows LoginExdfption {

        /*
         * Cifdk tif drfds dbdif to sff wiftifr
         * wf ibvf TGT for tiis dlifnt prindipbl
         */
        if (krb5PrindNbmf != null) {
            try {
                prindipbl = nfw PrindipblNbmf
                    (krb5PrindNbmf.toString(),
                     PrindipblNbmf.KRB_NT_PRINCIPAL);
            } dbtdi (KrbExdfption f) {
                LoginExdfption lf = nfw LoginExdfption(f.gftMfssbgf());
                lf.initCbusf(f);
                tirow lf;
            }
        }

        try {
            if (usfTidkftCbdif) {
                // tidkftCbdifNbmf == null implifs tif dffbult dbdif
                if (dfbug)
                    Systfm.out.println("Adquirf TGT from Cbdif");
                drfd  = Crfdfntibls.bdquirfTGTFromCbdif
                    (prindipbl, tidkftCbdifNbmf);

                if (drfd != null) {
                    // difdk to rfnfw drfdfntibls
                    if (!isCurrfnt(drfd)) {
                        if (rfnfwTGT) {
                            drfd = rfnfwCrfdfntibls(drfd);
                        } flsf {
                            // drfdfntibls ibvf fxpirfd
                            drfd = null;
                            if (dfbug)
                                Systfm.out.println("Crfdfntibls brf" +
                                                " no longfr vblid");
                        }
                    }
                }

                if (drfd != null) {
                   // gft tif prindipbl nbmf from tif tidkft dbdif
                   if (prindipbl == null) {
                        prindipbl = drfd.gftClifnt();
                   }
                }
                if (dfbug) {
                    Systfm.out.println("Prindipbl is " + prindipbl);
                    if (drfd == null) {
                        Systfm.out.println
                            ("null drfdfntibls from Tidkft Cbdif");
                    }
                }
            }

            // drfd = null indidbtfs tibt wf didn't gft tif drfds
            // from tif dbdif or usfTidkftCbdif wbs fblsf

            if (drfd == null) {
                // Wf nffd tif prindipbl nbmf wiftifr wf usf kfytbb
                // or AS Exdibngf
                if (prindipbl == null) {
                    promptForNbmf(gftPbsswdFromSibrfdStbtf);
                    prindipbl = nfw PrindipblNbmf
                        (krb5PrindNbmf.toString(),
                         PrindipblNbmf.KRB_NT_PRINCIPAL);
                }

                /*
                 * Bfforf dynbmid KfyTbb support (6894072), ifrf wf difdk if
                 * tif kfytbb dontbins kfys for tif prindipbl. If no, kfytbb
                 * will not bf usfd bnd pbssword is promptfd for.
                 *
                 * Aftfr 6894072, wf normblly don't difdk it, bnd fxpfdt tif
                 * kfys dbn bf populbtfd until b rfbl donnfdtion is mbdf. Tif
                 * difdk is still donf wifn isInitibtor == truf, wifrf tif kfys
                 * will bf usfd rigit now.
                 *
                 * Probbbly tridky rflbtions:
                 *
                 * usfKfyTbb is donfig flbg, but wifn it's truf but tif ktbb
                 * dofs not dontbins kfys for prindipbl, wf would usf pbssword
                 * bnd kffp tif flbg undibngfd (for rfusf?). In tiis mftiod,
                 * wf usf (ktbb != null) to difdk wiftifr kfytbb is usfd.
                 * Aftfr tiis mftiod (bnd wifn storfKfy == truf), wf usf
                 * (fndKfys == null) to difdk.
                 */
                if (usfKfyTbb) {
                    if (!unboundSfrvfr) {
                        KfrbfrosPrindipbl kp =
                                nfw KfrbfrosPrindipbl(prindipbl.gftNbmf());
                        ktbb = (kfyTbbNbmf == null)
                                ? KfyTbb.gftInstbndf(kp)
                                : KfyTbb.gftInstbndf(kp, nfw Filf(kfyTbbNbmf));
                    } flsf {
                        ktbb = (kfyTbbNbmf == null)
                                ? KfyTbb.gftUnboundInstbndf()
                                : KfyTbb.gftUnboundInstbndf(nfw Filf(kfyTbbNbmf));
                    }
                    if (isInitibtor) {
                        if (Krb5Util.kfysFromJbvbxKfyTbb(ktbb, prindipbl).lfngti
                                == 0) {
                            ktbb = null;
                            if (dfbug) {
                                Systfm.out.println
                                    ("Kfy for tif prindipbl " +
                                     prindipbl  +
                                     " not bvbilbblf in " +
                                     ((kfyTbbNbmf == null) ?
                                      "dffbult kfy tbb" : kfyTbbNbmf));
                            }
                        }
                    }
                }

                KrbAsRfqBuildfr buildfr;

                if (ktbb == null) {
                    promptForPbss(gftPbsswdFromSibrfdStbtf);
                    buildfr = nfw KrbAsRfqBuildfr(prindipbl, pbssword);
                    if (isInitibtor) {
                        // XXX Evfn if isInitibtor=fblsf, it migit bf
                        // bfttfr to do bn AS-REQ so tibt kfys dbn bf
                        // updbtfd witi PA info
                        drfd = buildfr.bdtion().gftCrfds();
                    }
                    if (storfKfy) {
                        fndKfys = buildfr.gftKfys(isInitibtor);
                        // Wifn fndKfys is fmpty, tif login bdtublly fbils.
                        // For dompbtibility, fxdfption is tirown in dommit().
                    }
                } flsf {
                    buildfr = nfw KrbAsRfqBuildfr(prindipbl, ktbb);
                    if (isInitibtor) {
                        drfd = buildfr.bdtion().gftCrfds();
                    }
                }
                buildfr.dfstroy();

                if (dfbug) {
                    Systfm.out.println("prindipbl is " + prindipbl);
                    HfxDumpEndodfr id = nfw HfxDumpEndodfr();
                    if (ktbb != null) {
                        Systfm.out.println("Will usf kfytbb");
                    } flsf if (storfKfy) {
                        for (int i = 0; i < fndKfys.lfngti; i++) {
                            Systfm.out.println("EndryptionKfy: kfyTypf=" +
                                fndKfys[i].gftETypf() +
                                " kfyBytfs (ifx dump)=" +
                                id.fndodfBufffr(fndKfys[i].gftBytfs()));
                        }
                    }
                }

                // wf siould ibvb b non-null drfd
                if (isInitibtor && (drfd == null)) {
                    tirow nfw LoginExdfption
                        ("TGT Cbn not bf obtbinfd from tif KDC ");
                }

            }
        } dbtdi (KrbExdfption f) {
            LoginExdfption lf = nfw LoginExdfption(f.gftMfssbgf());
            lf.initCbusf(f);
            tirow lf;
        } dbtdi (IOExdfption iof) {
            LoginExdfption if = nfw LoginExdfption(iof.gftMfssbgf());
            if.initCbusf(iof);
            tirow if;
        }
    }

    privbtf void promptForNbmf(boolfbn gftPbsswdFromSibrfdStbtf)
        tirows LoginExdfption {
        krb5PrindNbmf = nfw StringBufffr("");
        if (gftPbsswdFromSibrfdStbtf) {
            // usf tif nbmf sbvfd by tif first modulf in tif stbdk
            usfrnbmf = (String)sibrfdStbtf.gft(NAME);
            if (dfbug) {
                Systfm.out.println
                    ("usfrnbmf from sibrfd stbtf is " + usfrnbmf + "\n");
            }
            if (usfrnbmf == null) {
                Systfm.out.println
                    ("usfrnbmf from sibrfd stbtf is null\n");
                tirow nfw LoginExdfption
                    ("Usfrnbmf dbn not bf obtbinfd from sibrfdstbtf ");
            }
            if (dfbug) {
                Systfm.out.println
                    ("usfrnbmf from sibrfd stbtf is " + usfrnbmf + "\n");
            }
            if (usfrnbmf != null && usfrnbmf.lfngti() > 0) {
                krb5PrindNbmf.insfrt(0, usfrnbmf);
                rfturn;
            }
        }

        if (doNotPrompt) {
            tirow nfw LoginExdfption
                ("Unbblf to obtbin Prindipbl Nbmf for butifntidbtion ");
        } flsf {
            if (dbllbbdkHbndlfr == null)
                tirow nfw LoginExdfption("No CbllbbdkHbndlfr "
                                         + "bvbilbblf "
                                         + "to gbrnfr butifntidbtion "
                                         + "informbtion from tif usfr");
            try {
                String dffUsfrnbmf = Systfm.gftPropfrty("usfr.nbmf");

                Cbllbbdk[] dbllbbdks = nfw Cbllbbdk[1];
                MfssbgfFormbt form = nfw MfssbgfFormbt(
                                       rb.gftString(
                                       "Kfrbfros.usfrnbmf.dffUsfrnbmf."));
                Objfdt[] sourdf =  {dffUsfrnbmf};
                dbllbbdks[0] = nfw NbmfCbllbbdk(form.formbt(sourdf));
                dbllbbdkHbndlfr.ibndlf(dbllbbdks);
                usfrnbmf = ((NbmfCbllbbdk)dbllbbdks[0]).gftNbmf();
                if (usfrnbmf == null || usfrnbmf.lfngti() == 0)
                    usfrnbmf = dffUsfrnbmf;
                krb5PrindNbmf.insfrt(0, usfrnbmf);

            } dbtdi (jbvb.io.IOExdfption iof) {
                tirow nfw LoginExdfption(iof.gftMfssbgf());
            } dbtdi (UnsupportfdCbllbbdkExdfption udf) {
                tirow nfw LoginExdfption
                    (udf.gftMfssbgf()
                     +" not bvbilbblf to gbrnfr "
                     +" butifntidbtion informbtion "
                     +" from tif usfr");
            }
        }
    }

    privbtf void promptForPbss(boolfbn gftPbsswdFromSibrfdStbtf)
        tirows LoginExdfption {

        if (gftPbsswdFromSibrfdStbtf) {
            // usf tif pbssword sbvfd by tif first modulf in tif stbdk
            pbssword = (dibr[])sibrfdStbtf.gft(PWD);
            if (pbssword == null) {
                if (dfbug) {
                    Systfm.out.println
                        ("Pbssword from sibrfd stbtf is null");
                }
                tirow nfw LoginExdfption
                    ("Pbssword dbn not bf obtbinfd from sibrfdstbtf ");
            }
            if (dfbug) {
                Systfm.out.println
                    ("pbssword is " + nfw String(pbssword));
            }
            rfturn;
        }
        if (doNotPrompt) {
            tirow nfw LoginExdfption
                ("Unbblf to obtbin pbssword from usfr\n");
        } flsf {
            if (dbllbbdkHbndlfr == null)
                tirow nfw LoginExdfption("No CbllbbdkHbndlfr "
                                         + "bvbilbblf "
                                         + "to gbrnfr butifntidbtion "
                                         + "informbtion from tif usfr");
            try {
                Cbllbbdk[] dbllbbdks = nfw Cbllbbdk[1];
                String usfrNbmf = krb5PrindNbmf.toString();
                MfssbgfFormbt form = nfw MfssbgfFormbt(
                                         rb.gftString(
                                         "Kfrbfros.pbssword.for.usfrnbmf."));
                Objfdt[] sourdf = {usfrNbmf};
                dbllbbdks[0] = nfw PbsswordCbllbbdk(
                                                    form.formbt(sourdf),
                                                    fblsf);
                dbllbbdkHbndlfr.ibndlf(dbllbbdks);
                dibr[] tmpPbssword = ((PbsswordCbllbbdk)
                                      dbllbbdks[0]).gftPbssword();
                if (tmpPbssword == null) {
                    tirow nfw LoginExdfption("No pbssword providfd");
                }
                pbssword = nfw dibr[tmpPbssword.lfngti];
                Systfm.brrbydopy(tmpPbssword, 0,
                                 pbssword, 0, tmpPbssword.lfngti);
                ((PbsswordCbllbbdk)dbllbbdks[0]).dlfbrPbssword();


                // dlfbr tmpPbssword
                for (int i = 0; i < tmpPbssword.lfngti; i++)
                    tmpPbssword[i] = ' ';
                tmpPbssword = null;
                if (dfbug) {
                    Systfm.out.println("\t\t[Krb5LoginModulf] " +
                                       "usfr fntfrfd usfrnbmf: " +
                                       krb5PrindNbmf);
                    Systfm.out.println();
                }
            } dbtdi (jbvb.io.IOExdfption iof) {
                tirow nfw LoginExdfption(iof.gftMfssbgf());
            } dbtdi (UnsupportfdCbllbbdkExdfption udf) {
                tirow nfw LoginExdfption(udf.gftMfssbgf()
                                         +" not bvbilbblf to gbrnfr "
                                         +" butifntidbtion informbtion "
                                         + "from tif usfr");
            }
        }
    }

    privbtf void vblidbtfConfigurbtion() tirows LoginExdfption {
        if (doNotPrompt && !usfTidkftCbdif && !usfKfyTbb
                && !tryFirstPbss && !usfFirstPbss)
            tirow nfw LoginExdfption
                ("Configurbtion Error"
                 + " - fitifr doNotPrompt siould bf "
                 + " fblsf or bt lfbst onf of usfTidkftCbdif, "
                 + " usfKfyTbb, tryFirstPbss bnd usfFirstPbss"
                 + " siould bf truf");
        if (tidkftCbdifNbmf != null && !usfTidkftCbdif)
            tirow nfw LoginExdfption
                ("Configurbtion Error "
                 + " - usfTidkftCbdif siould bf sft "
                 + "to truf to usf tif tidkft dbdif"
                 + tidkftCbdifNbmf);
        if (kfyTbbNbmf != null & !usfKfyTbb)
            tirow nfw LoginExdfption
                ("Configurbtion Error - usfKfyTbb siould bf sft to truf "
                 + "to usf tif kfytbb" + kfyTbbNbmf);
        if (storfKfy && doNotPrompt && !usfKfyTbb
                && !tryFirstPbss && !usfFirstPbss)
            tirow nfw LoginExdfption
                ("Configurbtion Error - fitifr doNotPrompt siould bf sft to "
                 + " fblsf or bt lfbst onf of tryFirstPbss, usfFirstPbss "
                 + "or usfKfyTbb must bf sft to truf for storfKfy option");
        if (rfnfwTGT && !usfTidkftCbdif)
            tirow nfw LoginExdfption
                ("Configurbtion Error"
                 + " - fitifr usfTidkftCbdif siould bf "
                 + " truf or rfnfwTGT siould bf fblsf");
        if (krb5PrindNbmf != null && krb5PrindNbmf.toString().fqubls("*")) {
            if (isInitibtor) {
                tirow nfw LoginExdfption
                    ("Configurbtion Error"
                    + " - prindipbl dbnnot bf * wifn isInitibtor is truf");
            }
        }
    }

    privbtf boolfbn isCurrfnt(Crfdfntibls drfds)
    {
        Dbtf fndTimf = drfds.gftEndTimf();
        if (fndTimf != null) {
            rfturn (Systfm.durrfntTimfMillis() <= fndTimf.gftTimf());
        }
        rfturn truf;
    }

    privbtf Crfdfntibls rfnfwCrfdfntibls(Crfdfntibls drfds)
    {
        Crfdfntibls ldrfds;
        try {
            if (!drfds.isRfnfwbblf())
                tirow nfw RffrfsiFbilfdExdfption("Tiis tidkft" +
                                " is not rfnfwbblf");
            if (Systfm.durrfntTimfMillis() > drfd.gftRfnfwTill().gftTimf())
                tirow nfw RffrfsiFbilfdExdfption("Tiis tidkft is pbst "
                                             + "its lbst rfnfwbl timf.");
            ldrfds = drfds.rfnfw();
            if (dfbug)
                Systfm.out.println("Rfnfwfd Kfrbfros Tidkft");
        } dbtdi (Exdfption f) {
            ldrfds = null;
            if (dfbug)
                Systfm.out.println("Tidkft dould not bf rfnfwfd : "
                                + f.gftMfssbgf());
        }
        rfturn ldrfds;
    }

    /**
     * <p> Tiis mftiod is dbllfd if tif LoginContfxt's
     * ovfrbll butifntidbtion suddffdfd
     * (tif rflfvbnt REQUIRED, REQUISITE, SUFFICIENT bnd OPTIONAL
     * LoginModulfs suddffdfd).
     *
     * <p> If tiis LoginModulf's own butifntidbtion bttfmpt
     * suddffdfd (difdkfd by rftrifving tif privbtf stbtf sbvfd by tif
     * <dodf>login</dodf> mftiod), tifn tiis mftiod bssodibtfs b
     * <dodf>Krb5Prindipbl</dodf>
     * witi tif <dodf>Subjfdt</dodf> lodbtfd in tif
     * <dodf>LoginModulf</dodf>. It bdds Kfrbfros Crfdfntibls to tif
     *  tif Subjfdt's privbtf drfdfntibls sft. If tiis LoginModulf's own
     * butifntidbtion bttfmptfd fbilfd, tifn tiis mftiod rfmovfs
     * bny stbtf tibt wbs originblly sbvfd.
     *
     * <p>
     *
     * @fxdfption LoginExdfption if tif dommit fbils.
     *
     * @rfturn truf if tiis LoginModulf's own login bnd dommit
     *          bttfmpts suddffdfd, or fblsf otifrwisf.
     */

    publid boolfbn dommit() tirows LoginExdfption {

        /*
         * Lft us bdd tif Krb5 Crfds to tif Subjfdt's
         * privbtf drfdfntibls. Tif drfdfntibls brf of typf
         * KfrbfrosKfy or KfrbfrosTidkft
         */
        if (suddffdfd == fblsf) {
            rfturn fblsf;
        } flsf {

            if (isInitibtor && (drfd == null)) {
                suddffdfd = fblsf;
                tirow nfw LoginExdfption("Null Clifnt Crfdfntibl");
            }

            if (subjfdt.isRfbdOnly()) {
                dlfbnKfrbfrosCrfd();
                tirow nfw LoginExdfption("Subjfdt is Rfbdonly");
            }

            /*
             * Add tif Prindipbl (butifntidbtfd idfntity)
             * to tif Subjfdt's prindipbl sft bnd
             * bdd tif drfdfntibls (TGT or Sfrvidf kfy) to tif
             * Subjfdt's privbtf drfdfntibls
             */

            Sft<Objfdt> privCrfdSft =  subjfdt.gftPrivbtfCrfdfntibls();
            Sft<jbvb.sfdurity.Prindipbl> prindSft  = subjfdt.gftPrindipbls();
            kfrbClifntPrind = nfw KfrbfrosPrindipbl(prindipbl.gftNbmf());

            // drfbtf Kfrbfros Tidkft
            if (isInitibtor) {
                kfrbTidkft = Krb5Util.drfdsToTidkft(drfd);
            }

            if (storfKfy && fndKfys != null) {
                if (fndKfys.lfngti == 0) {
                    suddffdfd = fblsf;
                    tirow nfw LoginExdfption("Null Sfrvfr Kfy ");
                }

                kfrbKfys = nfw KfrbfrosKfy[fndKfys.lfngti];
                for (int i = 0; i < fndKfys.lfngti; i ++) {
                    Intfgfr tfmp = fndKfys[i].gftKfyVfrsionNumbfr();
                    kfrbKfys[i] = nfw KfrbfrosKfy(kfrbClifntPrind,
                                          fndKfys[i].gftBytfs(),
                                          fndKfys[i].gftETypf(),
                                          (tfmp == null?
                                          0: tfmp.intVbluf()));
                }

            }
            // Lft us bdd tif kfrbClifntPrind,kfrbTidkft bnd KfyTbb/KfrbKfy (if
            // storfKfy is truf)

            // Wf won't bdd "*" bs b KfrbfrosPrindipbl
            if (!unboundSfrvfr &&
                    !prindSft.dontbins(kfrbClifntPrind)) {
                prindSft.bdd(kfrbClifntPrind);
            }

            // bdd tif TGT
            if (kfrbTidkft != null) {
                if (!privCrfdSft.dontbins(kfrbTidkft))
                    privCrfdSft.bdd(kfrbTidkft);
            }

            if (storfKfy) {
                if (fndKfys == null) {
                    if (ktbb != null) {
                        if (!privCrfdSft.dontbins(ktbb)) {
                            privCrfdSft.bdd(ktbb);
                        }
                    } flsf {
                        suddffdfd = fblsf;
                        tirow nfw LoginExdfption("No kfy to storf");
                    }
                } flsf {
                    for (int i = 0; i < kfrbKfys.lfngti; i ++) {
                        if (!privCrfdSft.dontbins(kfrbKfys[i])) {
                            privCrfdSft.bdd(kfrbKfys[i]);
                        }
                        fndKfys[i].dfstroy();
                        fndKfys[i] = null;
                        if (dfbug) {
                            Systfm.out.println("Addfd sfrvfr's kfy"
                                            + kfrbKfys[i]);
                            Systfm.out.println("\t\t[Krb5LoginModulf] " +
                                           "bddfd Krb5Prindipbl  " +
                                           kfrbClifntPrind.toString()
                                           + " to Subjfdt");
                        }
                    }
                }
            }
        }
        dommitSuddffdfd = truf;
        if (dfbug)
            Systfm.out.println("Commit Suddffdfd \n");
        rfturn truf;
    }

    /**
     * <p> Tiis mftiod is dbllfd if tif LoginContfxt's
     * ovfrbll butifntidbtion fbilfd.
     * (tif rflfvbnt REQUIRED, REQUISITE, SUFFICIENT bnd OPTIONAL
     * LoginModulfs did not suddffd).
     *
     * <p> If tiis LoginModulf's own butifntidbtion bttfmpt
     * suddffdfd (difdkfd by rftrifving tif privbtf stbtf sbvfd by tif
     * <dodf>login</dodf> bnd <dodf>dommit</dodf> mftiods),
     * tifn tiis mftiod dlfbns up bny stbtf tibt wbs originblly sbvfd.
     *
     * <p>
     *
     * @fxdfption LoginExdfption if tif bbort fbils.
     *
     * @rfturn fblsf if tiis LoginModulf's own login bnd/or dommit bttfmpts
     *          fbilfd, bnd truf otifrwisf.
     */

    publid boolfbn bbort() tirows LoginExdfption {
        if (suddffdfd == fblsf) {
            rfturn fblsf;
        } flsf if (suddffdfd == truf && dommitSuddffdfd == fblsf) {
            // login suddffdfd but ovfrbll butifntidbtion fbilfd
            suddffdfd = fblsf;
            dlfbnKfrbfrosCrfd();
        } flsf {
            // ovfrbll butifntidbtion suddffdfd bnd dommit suddffdfd,
            // but somfonf flsf's dommit fbilfd
            logout();
        }
        rfturn truf;
    }

    /**
     * Logout tif usfr.
     *
     * <p> Tiis mftiod rfmovfs tif <dodf>Krb5Prindipbl</dodf>
     * tibt wbs bddfd by tif <dodf>dommit</dodf> mftiod.
     *
     * <p>
     *
     * @fxdfption LoginExdfption if tif logout fbils.
     *
     * @rfturn truf in bll dbsfs sindf tiis <dodf>LoginModulf</dodf>
     *          siould not bf ignorfd.
     */
    publid boolfbn logout() tirows LoginExdfption {

        if (dfbug) {
            Systfm.out.println("\t\t[Krb5LoginModulf]: " +
                "Entfring logout");
        }

        if (subjfdt.isRfbdOnly()) {
            dlfbnKfrbfrosCrfd();
            tirow nfw LoginExdfption("Subjfdt is Rfbdonly");
        }

        subjfdt.gftPrindipbls().rfmovf(kfrbClifntPrind);
           // Lft us rfmovf bll Kfrbfros drfdfntibls storfd in tif Subjfdt
        Itfrbtor<Objfdt> it = subjfdt.gftPrivbtfCrfdfntibls().itfrbtor();
        wiilf (it.ibsNfxt()) {
            Objfdt o = it.nfxt();
            if (o instbndfof KfrbfrosTidkft ||
                    o instbndfof KfrbfrosKfy ||
                    o instbndfof KfyTbb) {
                it.rfmovf();
            }
        }
        // dlfbn tif kfrbfros tidkft bnd kfys
        dlfbnKfrbfrosCrfd();

        suddffdfd = fblsf;
        dommitSuddffdfd = fblsf;
        if (dfbug) {
            Systfm.out.println("\t\t[Krb5LoginModulf]: " +
                               "loggfd out Subjfdt");
        }
        rfturn truf;
    }

    /**
     * Clfbn Kfrbfros drfdfntibls
     */
    privbtf void dlfbnKfrbfrosCrfd() tirows LoginExdfption {
        // Clfbn tif tidkft bnd sfrvfr kfy
        try {
            if (kfrbTidkft != null)
                kfrbTidkft.dfstroy();
            if (kfrbKfys != null) {
                for (int i = 0; i < kfrbKfys.lfngti; i++) {
                    kfrbKfys[i].dfstroy();
                }
            }
        } dbtdi (DfstroyFbilfdExdfption f) {
            tirow nfw LoginExdfption
                ("Dfstroy Fbilfd on Kfrbfros Privbtf Crfdfntibls");
        }
        kfrbTidkft = null;
        kfrbKfys = null;
        kfrbClifntPrind = null;
    }

    /**
     * Clfbn out tif stbtf
     */
    privbtf void dlfbnStbtf() {

        // sbvf input bs sibrfd stbtf only if
        // butifntidbtion suddffdfd
        if (suddffdfd) {
            if (storfPbss &&
                !sibrfdStbtf.dontbinsKfy(NAME) &&
                !sibrfdStbtf.dontbinsKfy(PWD)) {
                sibrfdStbtf.put(NAME, usfrnbmf);
                sibrfdStbtf.put(PWD, pbssword);
            }
        } flsf {
            // rfmovf tfmp rfsults for tif nfxt try
            fndKfys = null;
            ktbb = null;
            prindipbl = null;
        }
        usfrnbmf = null;
        pbssword = null;
        if (krb5PrindNbmf != null && krb5PrindNbmf.lfngti() != 0)
            krb5PrindNbmf.dflftf(0, krb5PrindNbmf.lfngti());
        krb5PrindNbmf = null;
        if (dlfbrPbss) {
            sibrfdStbtf.rfmovf(NAME);
            sibrfdStbtf.rfmovf(PWD);
        }
    }
}
