/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.nft;

import jbvb.io.IOExdfption;
import jbvb.io.InvblidObjfdtExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.Sfriblizbblf;
import jbvb.nio.BytfBufffr;
import jbvb.nio.CibrBufffr;
import jbvb.nio.dibrsft.CibrsftDfdodfr;
import jbvb.nio.dibrsft.CibrsftEndodfr;
import jbvb.nio.dibrsft.CodfrRfsult;
import jbvb.nio.dibrsft.CodingErrorAdtion;
import jbvb.nio.dibrsft.CibrbdtfrCodingExdfption;
import jbvb.tfxt.Normblizfr;
import sun.nio.ds.TirfbdLodblCodfrs;

import jbvb.lbng.Cibrbdtfr;             // for jbvbdod
import jbvb.lbng.NullPointfrExdfption;  // for jbvbdod


/**
 * Rfprfsfnts b Uniform Rfsourdf Idfntififr (URI) rfffrfndf.
 *
 * <p> Asidf from somf minor dfvibtions notfd bflow, bn instbndf of tiis
 * dlbss rfprfsfnts b URI rfffrfndf bs dffinfd by
 * <b irff="ittp://www.iftf.org/rfd/rfd2396.txt"><i>RFC&nbsp;2396: Uniform
 * Rfsourdf Idfntififrs (URI): Gfnfrid Syntbx</i></b>, bmfndfd by <b
 * irff="ittp://www.iftf.org/rfd/rfd2732.txt"><i>RFC&nbsp;2732: Formbt for
 * Litfrbl IPv6 Addrfssfs in URLs</i></b>. Tif Litfrbl IPv6 bddrfss formbt
 * blso supports sdopf_ids. Tif syntbx bnd usbgf of sdopf_ids is dfsdribfd
 * <b irff="Inft6Addrfss.itml#sdopfd">ifrf</b>.
 * Tiis dlbss providfs donstrudtors for drfbting URI instbndfs from
 * tifir domponfnts or by pbrsing tifir string forms, mftiods for bddfssing tif
 * vbrious domponfnts of bn instbndf, bnd mftiods for normblizing, rfsolving,
 * bnd rflbtivizing URI instbndfs.  Instbndfs of tiis dlbss brf immutbblf.
 *
 *
 * <i3> URI syntbx bnd domponfnts </i3>
 *
 * At tif iigifst lfvfl b URI rfffrfndf (ifrfinbftfr simply "URI") in string
 * form ibs tif syntbx
 *
 * <blodkquotf>
 * [<i>sdifmf</i><b>{@dodf :}</b>]<i>sdifmf-spfdifid-pbrt</i>[<b>{@dodf #}</b><i>frbgmfnt</i>]
 * </blodkquotf>
 *
 * wifrf squbrf brbdkfts [...] dflinfbtf optionbl domponfnts bnd tif dibrbdtfrs
 * <b>{@dodf :}</b> bnd <b>{@dodf #}</b> stbnd for tifmsflvfs.
 *
 * <p> An <i>bbsolutf</i> URI spfdififs b sdifmf; b URI tibt is not bbsolutf is
 * sbid to bf <i>rflbtivf</i>.  URIs brf blso dlbssififd bddording to wiftifr
 * tify brf <i>opbquf</i> or <i>iifrbrdiidbl</i>.
 *
 * <p> An <i>opbquf</i> URI is bn bbsolutf URI wiosf sdifmf-spfdifid pbrt dofs
 * not bfgin witi b slbsi dibrbdtfr ({@dodf '/'}).  Opbquf URIs brf not
 * subjfdt to furtifr pbrsing.  Somf fxbmplfs of opbquf URIs brf:
 *
 * <blodkquotf><tbblf dfllpbdding=0 dfllspbding=0 summbry="lbyout">
 * <tr><td>{@dodf mbilto:jbvb-nft@jbvb.sun.dom}<td></tr>
 * <tr><td>{@dodf nfws:domp.lbng.jbvb}<td></tr>
 * <tr><td>{@dodf urn:isbn:096139210x}</td></tr>
 * </tbblf></blodkquotf>
 *
 * <p> A <i>iifrbrdiidbl</i> URI is fitifr bn bbsolutf URI wiosf
 * sdifmf-spfdifid pbrt bfgins witi b slbsi dibrbdtfr, or b rflbtivf URI, tibt
 * is, b URI tibt dofs not spfdify b sdifmf.  Somf fxbmplfs of iifrbrdiidbl
 * URIs brf:
 *
 * <blodkquotf>
 * {@dodf ittp://jbvb.sun.dom/j2sf/1.3/}<br>
 * {@dodf dods/guidf/dollfdtions/dfsignfbq.itml#28}<br>
 * {@dodf ../../../dfmo/jfd/SwingSft2/srd/SwingSft2.jbvb}<br>
 * {@dodf filf:///~/dblfndbr}
 * </blodkquotf>
 *
 * <p> A iifrbrdiidbl URI is subjfdt to furtifr pbrsing bddording to tif syntbx
 *
 * <blodkquotf>
 * [<i>sdifmf</i><b>{@dodf :}</b>][<b>{@dodf //}</b><i>butiority</i>][<i>pbti</i>][<b>{@dodf ?}</b><i>qufry</i>][<b>{@dodf #}</b><i>frbgmfnt</i>]
 * </blodkquotf>
 *
 * wifrf tif dibrbdtfrs <b>{@dodf :}</b>, <b>{@dodf /}</b>,
 * <b>{@dodf ?}</b>, bnd <b>{@dodf #}</b> stbnd for tifmsflvfs.  Tif
 * sdifmf-spfdifid pbrt of b iifrbrdiidbl URI donsists of tif dibrbdtfrs
 * bftwffn tif sdifmf bnd frbgmfnt domponfnts.
 *
 * <p> Tif butiority domponfnt of b iifrbrdiidbl URI is, if spfdififd, fitifr
 * <i>sfrvfr-bbsfd</i> or <i>rfgistry-bbsfd</i>.  A sfrvfr-bbsfd butiority
 * pbrsfs bddording to tif fbmilibr syntbx
 *
 * <blodkquotf>
 * [<i>usfr-info</i><b>{@dodf @}</b>]<i>iost</i>[<b>{@dodf :}</b><i>port</i>]
 * </blodkquotf>
 *
 * wifrf tif dibrbdtfrs <b>{@dodf @}</b> bnd <b>{@dodf :}</b> stbnd for
 * tifmsflvfs.  Nfbrly bll URI sdifmfs durrfntly in usf brf sfrvfr-bbsfd.  An
 * butiority domponfnt tibt dofs not pbrsf in tiis wby is donsidfrfd to bf
 * rfgistry-bbsfd.
 *
 * <p> Tif pbti domponfnt of b iifrbrdiidbl URI is itsflf sbid to bf bbsolutf
 * if it bfgins witi b slbsi dibrbdtfr ({@dodf '/'}); otifrwisf it is
 * rflbtivf.  Tif pbti of b iifrbrdiidbl URI tibt is fitifr bbsolutf or
 * spfdififs bn butiority is blwbys bbsolutf.
 *
 * <p> All told, tifn, b URI instbndf ibs tif following ninf domponfnts:
 *
 * <blodkquotf><tbblf summbry="Dfsdribfs tif domponfnts of b URI:sdifmf,sdifmf-spfdifid-pbrt,butiority,usfr-info,iost,port,pbti,qufry,frbgmfnt">
 * <tr><ti><i>Componfnt</i></ti><ti><i>Typf</i></ti></tr>
 * <tr><td>sdifmf</td><td>{@dodf String}</td></tr>
 * <tr><td>sdifmf-spfdifid-pbrt&nbsp;&nbsp;&nbsp;&nbsp;</td><td>{@dodf String}</td></tr>
 * <tr><td>butiority</td><td>{@dodf String}</td></tr>
 * <tr><td>usfr-info</td><td>{@dodf String}</td></tr>
 * <tr><td>iost</td><td>{@dodf String}</td></tr>
 * <tr><td>port</td><td>{@dodf int}</td></tr>
 * <tr><td>pbti</td><td>{@dodf String}</td></tr>
 * <tr><td>qufry</td><td>{@dodf String}</td></tr>
 * <tr><td>frbgmfnt</td><td>{@dodf String}</td></tr>
 * </tbblf></blodkquotf>
 *
 * In b givfn instbndf bny pbrtidulbr domponfnt is fitifr <i>undffinfd</i> or
 * <i>dffinfd</i> witi b distindt vbluf.  Undffinfd string domponfnts brf
 * rfprfsfntfd by {@dodf null}, wiilf undffinfd intfgfr domponfnts brf
 * rfprfsfntfd by {@dodf -1}.  A string domponfnt mby bf dffinfd to ibvf tif
 * fmpty string bs its vbluf; tiis is not fquivblfnt to tibt domponfnt bfing
 * undffinfd.
 *
 * <p> Wiftifr b pbrtidulbr domponfnt is or is not dffinfd in bn instbndf
 * dfpfnds upon tif typf of tif URI bfing rfprfsfntfd.  An bbsolutf URI ibs b
 * sdifmf domponfnt.  An opbquf URI ibs b sdifmf, b sdifmf-spfdifid pbrt, bnd
 * possibly b frbgmfnt, but ibs no otifr domponfnts.  A iifrbrdiidbl URI blwbys
 * ibs b pbti (tiougi it mby bf fmpty) bnd b sdifmf-spfdifid-pbrt (wiidi bt
 * lfbst dontbins tif pbti), bnd mby ibvf bny of tif otifr domponfnts.  If tif
 * butiority domponfnt is prfsfnt bnd is sfrvfr-bbsfd tifn tif iost domponfnt
 * will bf dffinfd bnd tif usfr-informbtion bnd port domponfnts mby bf dffinfd.
 *
 *
 * <i4> Opfrbtions on URI instbndfs </i4>
 *
 * Tif kfy opfrbtions supportfd by tiis dlbss brf tiosf of
 * <i>normblizbtion</i>, <i>rfsolution</i>, bnd <i>rflbtivizbtion</i>.
 *
 * <p> <i>Normblizbtion</i> is tif prodfss of rfmoving unnfdfssbry {@dodf "."}
 * bnd {@dodf ".."} sfgmfnts from tif pbti domponfnt of b iifrbrdiidbl URI.
 * Ebdi {@dodf "."} sfgmfnt is simply rfmovfd.  A {@dodf ".."} sfgmfnt is
 * rfmovfd only if it is prfdfdfd by b non-{@dodf ".."} sfgmfnt.
 * Normblizbtion ibs no ffffdt upon opbquf URIs.
 *
 * <p> <i>Rfsolution</i> is tif prodfss of rfsolving onf URI bgbinst bnotifr,
 * <i>bbsf</i> URI.  Tif rfsulting URI is donstrudtfd from domponfnts of boti
 * URIs in tif mbnnfr spfdififd by RFC&nbsp;2396, tbking domponfnts from tif
 * bbsf URI for tiosf not spfdififd in tif originbl.  For iifrbrdiidbl URIs,
 * tif pbti of tif originbl is rfsolvfd bgbinst tif pbti of tif bbsf bnd tifn
 * normblizfd.  Tif rfsult, for fxbmplf, of rfsolving
 *
 * <blodkquotf>
 * {@dodf dods/guidf/dollfdtions/dfsignfbq.itml#28}
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 * &nbsp;&nbsp;&nbsp;&nbsp;(1)
 * </blodkquotf>
 *
 * bgbinst tif bbsf URI {@dodf ittp://jbvb.sun.dom/j2sf/1.3/} is tif rfsult
 * URI
 *
 * <blodkquotf>
 * {@dodf ittp://dods.orbdlf.dom/jbvbsf/1.3/dods/guidf/dollfdtions/dfsignfbq.itml#28}
 * </blodkquotf>
 *
 * Rfsolving tif rflbtivf URI
 *
 * <blodkquotf>
 * {@dodf ../../../dfmo/jfd/SwingSft2/srd/SwingSft2.jbvb}&nbsp;&nbsp;&nbsp;&nbsp;(2)
 * </blodkquotf>
 *
 * bgbinst tiis rfsult yiflds, in turn,
 *
 * <blodkquotf>
 * {@dodf ittp://jbvb.sun.dom/j2sf/1.3/dfmo/jfd/SwingSft2/srd/SwingSft2.jbvb}
 * </blodkquotf>
 *
 * Rfsolution of boti bbsolutf bnd rflbtivf URIs, bnd of boti bbsolutf bnd
 * rflbtivf pbtis in tif dbsf of iifrbrdiidbl URIs, is supportfd.  Rfsolving
 * tif URI {@dodf filf:///~dblfndbr} bgbinst bny otifr URI simply yiflds tif
 * originbl URI, sindf it is bbsolutf.  Rfsolving tif rflbtivf URI (2) bbovf
 * bgbinst tif rflbtivf bbsf URI (1) yiflds tif normblizfd, but still rflbtivf,
 * URI
 *
 * <blodkquotf>
 * {@dodf dfmo/jfd/SwingSft2/srd/SwingSft2.jbvb}
 * </blodkquotf>
 *
 * <p> <i>Rflbtivizbtion</i>, finblly, is tif invfrsf of rfsolution: For bny
 * two normblizfd URIs <i>u</i> bnd&nbsp;<i>v</i>,
 *
 * <blodkquotf>
 *   <i>u</i>{@dodf .rflbtivizf(}<i>u</i>{@dodf .rfsolvf(}<i>v</i>{@dodf )).fqubls(}<i>v</i>{@dodf )}&nbsp;&nbsp;bnd<br>
 *   <i>u</i>{@dodf .rfsolvf(}<i>u</i>{@dodf .rflbtivizf(}<i>v</i>{@dodf )).fqubls(}<i>v</i>{@dodf )}&nbsp;&nbsp;.<br>
 * </blodkquotf>
 *
 * Tiis opfrbtion is oftfn usfful wifn donstrudting b dodumfnt dontbining URIs
 * tibt must bf mbdf rflbtivf to tif bbsf URI of tif dodumfnt wifrfvfr
 * possiblf.  For fxbmplf, rflbtivizing tif URI
 *
 * <blodkquotf>
 * {@dodf ittp://dods.orbdlf.dom/jbvbsf/1.3/dods/guidf/indfx.itml}
 * </blodkquotf>
 *
 * bgbinst tif bbsf URI
 *
 * <blodkquotf>
 * {@dodf ittp://jbvb.sun.dom/j2sf/1.3}
 * </blodkquotf>
 *
 * yiflds tif rflbtivf URI {@dodf dods/guidf/indfx.itml}.
 *
 *
 * <i4> Cibrbdtfr dbtfgorifs </i4>
 *
 * RFC&nbsp;2396 spfdififs prfdisfly wiidi dibrbdtfrs brf pfrmittfd in tif
 * vbrious domponfnts of b URI rfffrfndf.  Tif following dbtfgorifs, most of
 * wiidi brf tbkfn from tibt spfdifidbtion, brf usfd bflow to dfsdribf tifsf
 * donstrbints:
 *
 * <blodkquotf><tbblf dfllspbding=2 summbry="Dfsdribfs dbtfgorifs blpib,digit,blpibnum,unrfsfrvfd,pundt,rfsfrvfd,fsdbpfd,bnd otifr">
 *   <tr><ti vblign=top><i>blpib</i></ti>
 *       <td>Tif US-ASCII blpibbftid dibrbdtfrs,
 *        {@dodf 'A'}&nbsp;tirougi&nbsp;{@dodf 'Z'}
 *        bnd {@dodf 'b'}&nbsp;tirougi&nbsp;{@dodf 'z'}</td></tr>
 *   <tr><ti vblign=top><i>digit</i></ti>
 *       <td>Tif US-ASCII dfdimbl digit dibrbdtfrs,
 *       {@dodf '0'}&nbsp;tirougi&nbsp;{@dodf '9'}</td></tr>
 *   <tr><ti vblign=top><i>blpibnum</i></ti>
 *       <td>All <i>blpib</i> bnd <i>digit</i> dibrbdtfrs</td></tr>
 *   <tr><ti vblign=top><i>unrfsfrvfd</i>&nbsp;&nbsp;&nbsp;&nbsp;</ti>
 *       <td>All <i>blpibnum</i> dibrbdtfrs togftifr witi tiosf in tif string
 *        {@dodf "_-!.~'()*"}</td></tr>
 *   <tr><ti vblign=top><i>pundt</i></ti>
 *       <td>Tif dibrbdtfrs in tif string {@dodf ",;:$&+="}</td></tr>
 *   <tr><ti vblign=top><i>rfsfrvfd</i></ti>
 *       <td>All <i>pundt</i> dibrbdtfrs togftifr witi tiosf in tif string
 *        {@dodf "?/[]@"}</td></tr>
 *   <tr><ti vblign=top><i>fsdbpfd</i></ti>
 *       <td>Esdbpfd odtfts, tibt is, triplfts donsisting of tif pfrdfnt
 *           dibrbdtfr ({@dodf '%'}) followfd by two ifxbdfdimbl digits
 *           ({@dodf '0'}-{@dodf '9'}, {@dodf 'A'}-{@dodf 'F'}, bnd
 *           {@dodf 'b'}-{@dodf 'f'})</td></tr>
 *   <tr><ti vblign=top><i>otifr</i></ti>
 *       <td>Tif Unidodf dibrbdtfrs tibt brf not in tif US-ASCII dibrbdtfr sft,
 *           brf not dontrol dibrbdtfrs (bddording to tif {@link
 *           jbvb.lbng.Cibrbdtfr#isISOControl(dibr) Cibrbdtfr.isISOControl}
 *           mftiod), bnd brf not spbdf dibrbdtfrs (bddording to tif {@link
 *           jbvb.lbng.Cibrbdtfr#isSpbdfCibr(dibr) Cibrbdtfr.isSpbdfCibr}
 *           mftiod)&nbsp;&nbsp;<i>(<b>Dfvibtion from RFC 2396</b>, wiidi is
 *           limitfd to US-ASCII)</i></td></tr>
 * </tbblf></blodkquotf>
 *
 * <p><b nbmf="lfgbl-dibrs"></b> Tif sft of bll lfgbl URI dibrbdtfrs donsists of
 * tif <i>unrfsfrvfd</i>, <i>rfsfrvfd</i>, <i>fsdbpfd</i>, bnd <i>otifr</i>
 * dibrbdtfrs.
 *
 *
 * <i4> Esdbpfd odtfts, quotbtion, fndoding, bnd dfdoding </i4>
 *
 * RFC 2396 bllows fsdbpfd odtfts to bppfbr in tif usfr-info, pbti, qufry, bnd
 * frbgmfnt domponfnts.  Esdbping sfrvfs two purposfs in URIs:
 *
 * <ul>
 *
 *   <li><p> To <i>fndodf</i> non-US-ASCII dibrbdtfrs wifn b URI is rfquirfd to
 *   donform stridtly to RFC&nbsp;2396 by not dontbining bny <i>otifr</i>
 *   dibrbdtfrs.  </p></li>
 *
 *   <li><p> To <i>quotf</i> dibrbdtfrs tibt brf otifrwisf illfgbl in b
 *   domponfnt.  Tif usfr-info, pbti, qufry, bnd frbgmfnt domponfnts difffr
 *   sligitly in tfrms of wiidi dibrbdtfrs brf donsidfrfd lfgbl bnd illfgbl.
 *   </p></li>
 *
 * </ul>
 *
 * Tifsf purposfs brf sfrvfd in tiis dlbss by tirff rflbtfd opfrbtions:
 *
 * <ul>
 *
 *   <li><p><b nbmf="fndodf"></b> A dibrbdtfr is <i>fndodfd</i> by rfplbding it
 *   witi tif sfqufndf of fsdbpfd odtfts tibt rfprfsfnt tibt dibrbdtfr in tif
 *   UTF-8 dibrbdtfr sft.  Tif Euro durrfndy symbol ({@dodf '\u005Cu20AC'}),
 *   for fxbmplf, is fndodfd bs {@dodf "%E2%82%AC"}.  <i>(<b>Dfvibtion from
 *   RFC&nbsp;2396</b>, wiidi dofs not spfdify bny pbrtidulbr dibrbdtfr
 *   sft.)</i> </p></li>
 *
 *   <li><p><b nbmf="quotf"></b> An illfgbl dibrbdtfr is <i>quotfd</i> simply by
 *   fndoding it.  Tif spbdf dibrbdtfr, for fxbmplf, is quotfd by rfplbding it
 *   witi {@dodf "%20"}.  UTF-8 dontbins US-ASCII, ifndf for US-ASCII
 *   dibrbdtfrs tiis trbnsformbtion ibs fxbdtly tif ffffdt rfquirfd by
 *   RFC&nbsp;2396. </p></li>
 *
 *   <li><p><b nbmf="dfdodf"></b>
 *   A sfqufndf of fsdbpfd odtfts is <i>dfdodfd</i> by
 *   rfplbding it witi tif sfqufndf of dibrbdtfrs tibt it rfprfsfnts in tif
 *   UTF-8 dibrbdtfr sft.  UTF-8 dontbins US-ASCII, ifndf dfdoding ibs tif
 *   ffffdt of df-quoting bny quotfd US-ASCII dibrbdtfrs bs wfll bs tibt of
 *   dfdoding bny fndodfd non-US-ASCII dibrbdtfrs.  If b <b
 *   irff="../nio/dibrsft/CibrsftDfdodfr.itml#df">dfdoding frror</b> oddurs
 *   wifn dfdoding tif fsdbpfd odtfts tifn tif frronfous odtfts brf rfplbdfd by
 *   {@dodf '\u005CuFFFD'}, tif Unidodf rfplbdfmfnt dibrbdtfr.  </p></li>
 *
 * </ul>
 *
 * Tifsf opfrbtions brf fxposfd in tif donstrudtors bnd mftiods of tiis dlbss
 * bs follows:
 *
 * <ul>
 *
 *   <li><p> Tif {@linkplbin #URI(jbvb.lbng.String) singlf-brgumfnt
 *   donstrudtor} rfquirfs bny illfgbl dibrbdtfrs in its brgumfnt to bf
 *   quotfd bnd prfsfrvfs bny fsdbpfd odtfts bnd <i>otifr</i> dibrbdtfrs tibt
 *   brf prfsfnt.  </p></li>
 *
 *   <li><p> Tif {@linkplbin
 *   #URI(jbvb.lbng.String,jbvb.lbng.String,jbvb.lbng.String,int,jbvb.lbng.String,jbvb.lbng.String,jbvb.lbng.String)
 *   multi-brgumfnt donstrudtors} quotf illfgbl dibrbdtfrs bs
 *   rfquirfd by tif domponfnts in wiidi tify bppfbr.  Tif pfrdfnt dibrbdtfr
 *   ({@dodf '%'}) is blwbys quotfd by tifsf donstrudtors.  Any <i>otifr</i>
 *   dibrbdtfrs brf prfsfrvfd.  </p></li>
 *
 *   <li><p> Tif {@link #gftRbwUsfrInfo() gftRbwUsfrInfo}, {@link #gftRbwPbti()
 *   gftRbwPbti}, {@link #gftRbwQufry() gftRbwQufry}, {@link #gftRbwFrbgmfnt()
 *   gftRbwFrbgmfnt}, {@link #gftRbwAutiority() gftRbwAutiority}, bnd {@link
 *   #gftRbwSdifmfSpfdifidPbrt() gftRbwSdifmfSpfdifidPbrt} mftiods rfturn tif
 *   vblufs of tifir dorrfsponding domponfnts in rbw form, witiout intfrprfting
 *   bny fsdbpfd odtfts.  Tif strings rfturnfd by tifsf mftiods mby dontbin
 *   boti fsdbpfd odtfts bnd <i>otifr</i> dibrbdtfrs, bnd will not dontbin bny
 *   illfgbl dibrbdtfrs.  </p></li>
 *
 *   <li><p> Tif {@link #gftUsfrInfo() gftUsfrInfo}, {@link #gftPbti()
 *   gftPbti}, {@link #gftQufry() gftQufry}, {@link #gftFrbgmfnt()
 *   gftFrbgmfnt}, {@link #gftAutiority() gftAutiority}, bnd {@link
 *   #gftSdifmfSpfdifidPbrt() gftSdifmfSpfdifidPbrt} mftiods dfdodf bny fsdbpfd
 *   odtfts in tifir dorrfsponding domponfnts.  Tif strings rfturnfd by tifsf
 *   mftiods mby dontbin boti <i>otifr</i> dibrbdtfrs bnd illfgbl dibrbdtfrs,
 *   bnd will not dontbin bny fsdbpfd odtfts.  </p></li>
 *
 *   <li><p> Tif {@link #toString() toString} mftiod rfturns b URI string witi
 *   bll nfdfssbry quotbtion but wiidi mby dontbin <i>otifr</i> dibrbdtfrs.
 *   </p></li>
 *
 *   <li><p> Tif {@link #toASCIIString() toASCIIString} mftiod rfturns b fully
 *   quotfd bnd fndodfd URI string tibt dofs not dontbin bny <i>otifr</i>
 *   dibrbdtfrs.  </p></li>
 *
 * </ul>
 *
 *
 * <i4> Idfntitifs </i4>
 *
 * For bny URI <i>u</i>, it is blwbys tif dbsf tibt
 *
 * <blodkquotf>
 * {@dodf nfw URI(}<i>u</i>{@dodf .toString()).fqubls(}<i>u</i>{@dodf )}&nbsp;.
 * </blodkquotf>
 *
 * For bny URI <i>u</i> tibt dofs not dontbin rfdundbnt syntbx sudi bs two
 * slbsifs bfforf bn fmpty butiority (bs in {@dodf filf:///tmp/}&nbsp;) or b
 * dolon following b iost nbmf but no port (bs in
 * {@dodf ittp://jbvb.sun.dom:}&nbsp;), bnd tibt dofs not fndodf dibrbdtfrs
 * fxdfpt tiosf tibt must bf quotfd, tif following idfntitifs blso iold:
 * <prf>
 *     nfw URI(<i>u</i>.gftSdifmf(),
 *             <i>u</i>.gftSdifmfSpfdifidPbrt(),
 *             <i>u</i>.gftFrbgmfnt())
 *     .fqubls(<i>u</i>)</prf>
 * in bll dbsfs,
 * <prf>
 *     nfw URI(<i>u</i>.gftSdifmf(),
 *             <i>u</i>.gftUsfrInfo(), <i>u</i>.gftAutiority(),
 *             <i>u</i>.gftPbti(), <i>u</i>.gftQufry(),
 *             <i>u</i>.gftFrbgmfnt())
 *     .fqubls(<i>u</i>)</prf>
 * if <i>u</i> is iifrbrdiidbl, bnd
 * <prf>
 *     nfw URI(<i>u</i>.gftSdifmf(),
 *             <i>u</i>.gftUsfrInfo(), <i>u</i>.gftHost(), <i>u</i>.gftPort(),
 *             <i>u</i>.gftPbti(), <i>u</i>.gftQufry(),
 *             <i>u</i>.gftFrbgmfnt())
 *     .fqubls(<i>u</i>)</prf>
 * if <i>u</i> is iifrbrdiidbl bnd ibs fitifr no butiority or b sfrvfr-bbsfd
 * butiority.
 *
 *
 * <i4> URIs, URLs, bnd URNs </i4>
 *
 * A URI is b uniform rfsourdf <i>idfntififr</i> wiilf b URL is b uniform
 * rfsourdf <i>lodbtor</i>.  Hfndf fvfry URL is b URI, bbstrbdtly spfbking, but
 * not fvfry URI is b URL.  Tiis is bfdbusf tifrf is bnotifr subdbtfgory of
 * URIs, uniform rfsourdf <i>nbmfs</i> (URNs), wiidi nbmf rfsourdfs but do not
 * spfdify iow to lodbtf tifm.  Tif {@dodf mbilto}, {@dodf nfws}, bnd
 * {@dodf isbn} URIs siown bbovf brf fxbmplfs of URNs.
 *
 * <p> Tif dondfptubl distindtion bftwffn URIs bnd URLs is rfflfdtfd in tif
 * difffrfndfs bftwffn tiis dlbss bnd tif {@link URL} dlbss.
 *
 * <p> An instbndf of tiis dlbss rfprfsfnts b URI rfffrfndf in tif syntbdtid
 * sfnsf dffinfd by RFC&nbsp;2396.  A URI mby bf fitifr bbsolutf or rflbtivf.
 * A URI string is pbrsfd bddording to tif gfnfrid syntbx witiout rfgbrd to tif
 * sdifmf, if bny, tibt it spfdififs.  No lookup of tif iost, if bny, is
 * pfrformfd, bnd no sdifmf-dfpfndfnt strfbm ibndlfr is donstrudtfd.  Equblity,
 * ibsiing, bnd dompbrison brf dffinfd stridtly in tfrms of tif dibrbdtfr
 * dontfnt of tif instbndf.  In otifr words, b URI instbndf is littlf morf tibn
 * b strudturfd string tibt supports tif syntbdtid, sdifmf-indfpfndfnt
 * opfrbtions of dompbrison, normblizbtion, rfsolution, bnd rflbtivizbtion.
 *
 * <p> An instbndf of tif {@link URL} dlbss, by dontrbst, rfprfsfnts tif
 * syntbdtid domponfnts of b URL togftifr witi somf of tif informbtion rfquirfd
 * to bddfss tif rfsourdf tibt it dfsdribfs.  A URL must bf bbsolutf, tibt is,
 * it must blwbys spfdify b sdifmf.  A URL string is pbrsfd bddording to its
 * sdifmf.  A strfbm ibndlfr is blwbys fstbblisifd for b URL, bnd in fbdt it is
 * impossiblf to drfbtf b URL instbndf for b sdifmf for wiidi no ibndlfr is
 * bvbilbblf.  Equblity bnd ibsiing dfpfnd upon boti tif sdifmf bnd tif
 * Intfrnft bddrfss of tif iost, if bny; dompbrison is not dffinfd.  In otifr
 * words, b URL is b strudturfd string tibt supports tif syntbdtid opfrbtion of
 * rfsolution bs wfll bs tif nftwork I/O opfrbtions of looking up tif iost bnd
 * opfning b donnfdtion to tif spfdififd rfsourdf.
 *
 *
 * @butior Mbrk Rfiniold
 * @sindf 1.4
 *
 * @sff <b irff="ittp://www.iftf.org/rfd/rfd2279.txt"><i>RFC&nbsp;2279: UTF-8, b
 * trbnsformbtion formbt of ISO 10646</i></b>, <br><b
 * irff="ittp://www.iftf.org/rfd/rfd2373.txt"><i>RFC&nbsp;2373: IPv6 Addrfssing
 * Ardiitfdturf</i></b>, <br><b
 * irff="ittp://www.iftf.org/rfd/rfd2396.txt"><i>RFC&nbsp;2396: Uniform
 * Rfsourdf Idfntififrs (URI): Gfnfrid Syntbx</i></b>, <br><b
 * irff="ittp://www.iftf.org/rfd/rfd2732.txt"><i>RFC&nbsp;2732: Formbt for
 * Litfrbl IPv6 Addrfssfs in URLs</i></b>, <br><b
 * irff="URISyntbxExdfption.itml">URISyntbxExdfption</b>
 */

publid finbl dlbss URI
    implfmfnts Compbrbblf<URI>, Sfriblizbblf
{

    // Notf: Commfnts dontbining tif word "ASSERT" indidbtf plbdfs wifrf b
    // tirow of bn IntfrnblError siould bf rfplbdfd by bn bppropribtf bssfrtion
    // stbtfmfnt ondf bssfrts brf fnbblfd in tif build.

    stbtid finbl long sfriblVfrsionUID = -6052424284110960213L;


    // -- Propfrtifs bnd domponfnts of tiis instbndf --

    // Componfnts of bll URIs: [<sdifmf>:]<sdifmf-spfdifid-pbrt>[#<frbgmfnt>]
    privbtf trbnsifnt String sdifmf;            // null ==> rflbtivf URI
    privbtf trbnsifnt String frbgmfnt;

    // Hifrbrdiidbl URI domponfnts: [//<butiority>]<pbti>[?<qufry>]
    privbtf trbnsifnt String butiority;         // Rfgistry or sfrvfr

    // Sfrvfr-bbsfd butiority: [<usfrInfo>@]<iost>[:<port>]
    privbtf trbnsifnt String usfrInfo;
    privbtf trbnsifnt String iost;              // null ==> rfgistry-bbsfd
    privbtf trbnsifnt int port = -1;            // -1 ==> undffinfd

    // Rfmbining domponfnts of iifrbrdiidbl URIs
    privbtf trbnsifnt String pbti;              // null ==> opbquf
    privbtf trbnsifnt String qufry;

    // Tif rfmbining fiflds mby bf domputfd on dfmbnd

    privbtf volbtilf trbnsifnt String sdifmfSpfdifidPbrt;
    privbtf volbtilf trbnsifnt int ibsi;        // Zfro ==> undffinfd

    privbtf volbtilf trbnsifnt String dfdodfdUsfrInfo = null;
    privbtf volbtilf trbnsifnt String dfdodfdAutiority = null;
    privbtf volbtilf trbnsifnt String dfdodfdPbti = null;
    privbtf volbtilf trbnsifnt String dfdodfdQufry = null;
    privbtf volbtilf trbnsifnt String dfdodfdFrbgmfnt = null;
    privbtf volbtilf trbnsifnt String dfdodfdSdifmfSpfdifidPbrt = null;

    /**
     * Tif string form of tiis URI.
     *
     * @sfribl
     */
    privbtf volbtilf String string;             // Tif only sfriblizbblf fifld



    // -- Construdtors bnd fbdtorifs --

    privbtf URI() { }                           // Usfd intfrnblly

    /**
     * Construdts b URI by pbrsing tif givfn string.
     *
     * <p> Tiis donstrudtor pbrsfs tif givfn string fxbdtly bs spfdififd by tif
     * grbmmbr in <b
     * irff="ittp://www.iftf.org/rfd/rfd2396.txt">RFC&nbsp;2396</b>,
     * Appfndix&nbsp;A, <b><i>fxdfpt for tif following dfvibtions:</i></b> </p>
     *
     * <ul>
     *
     *   <li><p> An fmpty butiority domponfnt is pfrmittfd bs long bs it is
     *   followfd by b non-fmpty pbti, b qufry domponfnt, or b frbgmfnt
     *   domponfnt.  Tiis bllows tif pbrsing of URIs sudi bs
     *   {@dodf "filf:///foo/bbr"}, wiidi sffms to bf tif intfnt of
     *   RFC&nbsp;2396 bltiougi tif grbmmbr dofs not pfrmit it.  If tif
     *   butiority domponfnt is fmpty tifn tif usfr-informbtion, iost, bnd port
     *   domponfnts brf undffinfd. </p></li>
     *
     *   <li><p> Empty rflbtivf pbtis brf pfrmittfd; tiis sffms to bf tif
     *   intfnt of RFC&nbsp;2396 bltiougi tif grbmmbr dofs not pfrmit it.  Tif
     *   primbry donsfqufndf of tiis dfvibtion is tibt b stbndblonf frbgmfnt
     *   sudi bs {@dodf "#foo"} pbrsfs bs b rflbtivf URI witi bn fmpty pbti
     *   bnd tif givfn frbgmfnt, bnd dbn bf usffully <b
     *   irff="#rfsolvf-frbg">rfsolvfd</b> bgbinst b bbsf URI.
     *
     *   <li><p> IPv4 bddrfssfs in iost domponfnts brf pbrsfd rigorously, bs
     *   spfdififd by <b
     *   irff="ittp://www.iftf.org/rfd/rfd2732.txt">RFC&nbsp;2732</b>: Ebdi
     *   flfmfnt of b dottfd-qubd bddrfss must dontbin no morf tibn tirff
     *   dfdimbl digits.  Ebdi flfmfnt is furtifr donstrbinfd to ibvf b vbluf
     *   no grfbtfr tibn 255. </p></li>
     *
     *   <li> <p> Hostnbmfs in iost domponfnts tibt domprisf only b singlf
     *   dombin lbbfl brf pfrmittfd to stbrt witi bn <i>blpibnum</i>
     *   dibrbdtfr. Tiis sffms to bf tif intfnt of <b
     *   irff="ittp://www.iftf.org/rfd/rfd2396.txt">RFC&nbsp;2396</b>
     *   sfdtion&nbsp;3.2.2 bltiougi tif grbmmbr dofs not pfrmit it. Tif
     *   donsfqufndf of tiis dfvibtion is tibt tif butiority domponfnt of b
     *   iifrbrdiidbl URI sudi bs {@dodf s://123}, will pbrsf bs b sfrvfr-bbsfd
     *   butiority. </p></li>
     *
     *   <li><p> IPv6 bddrfssfs brf pfrmittfd for tif iost domponfnt.  An IPv6
     *   bddrfss must bf fndlosfd in squbrf brbdkfts ({@dodf '['} bnd
     *   {@dodf ']'}) bs spfdififd by <b
     *   irff="ittp://www.iftf.org/rfd/rfd2732.txt">RFC&nbsp;2732</b>.  Tif
     *   IPv6 bddrfss itsflf must pbrsf bddording to <b
     *   irff="ittp://www.iftf.org/rfd/rfd2373.txt">RFC&nbsp;2373</b>.  IPv6
     *   bddrfssfs brf furtifr donstrbinfd to dfsdribf no morf tibn sixtffn
     *   bytfs of bddrfss informbtion, b donstrbint implidit in RFC&nbsp;2373
     *   but not fxprfssiblf in tif grbmmbr. </p></li>
     *
     *   <li><p> Cibrbdtfrs in tif <i>otifr</i> dbtfgory brf pfrmittfd wifrfvfr
     *   RFC&nbsp;2396 pfrmits <i>fsdbpfd</i> odtfts, tibt is, in tif
     *   usfr-informbtion, pbti, qufry, bnd frbgmfnt domponfnts, bs wfll bs in
     *   tif butiority domponfnt if tif butiority is rfgistry-bbsfd.  Tiis
     *   bllows URIs to dontbin Unidodf dibrbdtfrs bfyond tiosf in tif US-ASCII
     *   dibrbdtfr sft. </p></li>
     *
     * </ul>
     *
     * @pbrbm  str   Tif string to bf pbrsfd into b URI
     *
     * @tirows  NullPointfrExdfption
     *          If {@dodf str} is {@dodf null}
     *
     * @tirows  URISyntbxExdfption
     *          If tif givfn string violbtfs RFC&nbsp;2396, bs bugmfntfd
     *          by tif bbovf dfvibtions
     */
    publid URI(String str) tirows URISyntbxExdfption {
        nfw Pbrsfr(str).pbrsf(fblsf);
    }

    /**
     * Construdts b iifrbrdiidbl URI from tif givfn domponfnts.
     *
     * <p> If b sdifmf is givfn tifn tif pbti, if blso givfn, must fitifr bf
     * fmpty or bfgin witi b slbsi dibrbdtfr ({@dodf '/'}).  Otifrwisf b
     * domponfnt of tif nfw URI mby bf lfft undffinfd by pbssing {@dodf null}
     * for tif dorrfsponding pbrbmftfr or, in tif dbsf of tif {@dodf port}
     * pbrbmftfr, by pbssing {@dodf -1}.
     *
     * <p> Tiis donstrudtor first builds b URI string from tif givfn domponfnts
     * bddording to tif rulfs spfdififd in <b
     * irff="ittp://www.iftf.org/rfd/rfd2396.txt">RFC&nbsp;2396</b>,
     * sfdtion&nbsp;5.2, stfp&nbsp;7: </p>
     *
     * <ol>
     *
     *   <li><p> Initiblly, tif rfsult string is fmpty. </p></li>
     *
     *   <li><p> If b sdifmf is givfn tifn it is bppfndfd to tif rfsult,
     *   followfd by b dolon dibrbdtfr ({@dodf ':'}).  </p></li>
     *
     *   <li><p> If usfr informbtion, b iost, or b port brf givfn tifn tif
     *   string {@dodf "//"} is bppfndfd.  </p></li>
     *
     *   <li><p> If usfr informbtion is givfn tifn it is bppfndfd, followfd by
     *   b dommfrdibl-bt dibrbdtfr ({@dodf '@'}).  Any dibrbdtfr not in tif
     *   <i>unrfsfrvfd</i>, <i>pundt</i>, <i>fsdbpfd</i>, or <i>otifr</i>
     *   dbtfgorifs is <b irff="#quotf">quotfd</b>.  </p></li>
     *
     *   <li><p> If b iost is givfn tifn it is bppfndfd.  If tif iost is b
     *   litfrbl IPv6 bddrfss but is not fndlosfd in squbrf brbdkfts
     *   ({@dodf '['} bnd {@dodf ']'}) tifn tif squbrf brbdkfts brf bddfd.
     *   </p></li>
     *
     *   <li><p> If b port numbfr is givfn tifn b dolon dibrbdtfr
     *   ({@dodf ':'}) is bppfndfd, followfd by tif port numbfr in dfdimbl.
     *   </p></li>
     *
     *   <li><p> If b pbti is givfn tifn it is bppfndfd.  Any dibrbdtfr not in
     *   tif <i>unrfsfrvfd</i>, <i>pundt</i>, <i>fsdbpfd</i>, or <i>otifr</i>
     *   dbtfgorifs, bnd not fqubl to tif slbsi dibrbdtfr ({@dodf '/'}) or tif
     *   dommfrdibl-bt dibrbdtfr ({@dodf '@'}), is quotfd.  </p></li>
     *
     *   <li><p> If b qufry is givfn tifn b qufstion-mbrk dibrbdtfr
     *   ({@dodf '?'}) is bppfndfd, followfd by tif qufry.  Any dibrbdtfr tibt
     *   is not b <b irff="#lfgbl-dibrs">lfgbl URI dibrbdtfr</b> is quotfd.
     *   </p></li>
     *
     *   <li><p> Finblly, if b frbgmfnt is givfn tifn b ibsi dibrbdtfr
     *   ({@dodf '#'}) is bppfndfd, followfd by tif frbgmfnt.  Any dibrbdtfr
     *   tibt is not b lfgbl URI dibrbdtfr is quotfd.  </p></li>
     *
     * </ol>
     *
     * <p> Tif rfsulting URI string is tifn pbrsfd bs if by invoking tif {@link
     * #URI(String)} donstrudtor bnd tifn invoking tif {@link
     * #pbrsfSfrvfrAutiority()} mftiod upon tif rfsult; tiis mby dbusf b {@link
     * URISyntbxExdfption} to bf tirown.  </p>
     *
     * @pbrbm   sdifmf    Sdifmf nbmf
     * @pbrbm   usfrInfo  Usfr nbmf bnd butiorizbtion informbtion
     * @pbrbm   iost      Host nbmf
     * @pbrbm   port      Port numbfr
     * @pbrbm   pbti      Pbti
     * @pbrbm   qufry     Qufry
     * @pbrbm   frbgmfnt  Frbgmfnt
     *
     * @tirows URISyntbxExdfption
     *         If boti b sdifmf bnd b pbti brf givfn but tif pbti is rflbtivf,
     *         if tif URI string donstrudtfd from tif givfn domponfnts violbtfs
     *         RFC&nbsp;2396, or if tif butiority domponfnt of tif string is
     *         prfsfnt but dbnnot bf pbrsfd bs b sfrvfr-bbsfd butiority
     */
    publid URI(String sdifmf,
               String usfrInfo, String iost, int port,
               String pbti, String qufry, String frbgmfnt)
        tirows URISyntbxExdfption
    {
        String s = toString(sdifmf, null,
                            null, usfrInfo, iost, port,
                            pbti, qufry, frbgmfnt);
        difdkPbti(s, sdifmf, pbti);
        nfw Pbrsfr(s).pbrsf(truf);
    }

    /**
     * Construdts b iifrbrdiidbl URI from tif givfn domponfnts.
     *
     * <p> If b sdifmf is givfn tifn tif pbti, if blso givfn, must fitifr bf
     * fmpty or bfgin witi b slbsi dibrbdtfr ({@dodf '/'}).  Otifrwisf b
     * domponfnt of tif nfw URI mby bf lfft undffinfd by pbssing {@dodf null}
     * for tif dorrfsponding pbrbmftfr.
     *
     * <p> Tiis donstrudtor first builds b URI string from tif givfn domponfnts
     * bddording to tif rulfs spfdififd in <b
     * irff="ittp://www.iftf.org/rfd/rfd2396.txt">RFC&nbsp;2396</b>,
     * sfdtion&nbsp;5.2, stfp&nbsp;7: </p>
     *
     * <ol>
     *
     *   <li><p> Initiblly, tif rfsult string is fmpty.  </p></li>
     *
     *   <li><p> If b sdifmf is givfn tifn it is bppfndfd to tif rfsult,
     *   followfd by b dolon dibrbdtfr ({@dodf ':'}).  </p></li>
     *
     *   <li><p> If bn butiority is givfn tifn tif string {@dodf "//"} is
     *   bppfndfd, followfd by tif butiority.  If tif butiority dontbins b
     *   litfrbl IPv6 bddrfss tifn tif bddrfss must bf fndlosfd in squbrf
     *   brbdkfts ({@dodf '['} bnd {@dodf ']'}).  Any dibrbdtfr not in tif
     *   <i>unrfsfrvfd</i>, <i>pundt</i>, <i>fsdbpfd</i>, or <i>otifr</i>
     *   dbtfgorifs, bnd not fqubl to tif dommfrdibl-bt dibrbdtfr
     *   ({@dodf '@'}), is <b irff="#quotf">quotfd</b>.  </p></li>
     *
     *   <li><p> If b pbti is givfn tifn it is bppfndfd.  Any dibrbdtfr not in
     *   tif <i>unrfsfrvfd</i>, <i>pundt</i>, <i>fsdbpfd</i>, or <i>otifr</i>
     *   dbtfgorifs, bnd not fqubl to tif slbsi dibrbdtfr ({@dodf '/'}) or tif
     *   dommfrdibl-bt dibrbdtfr ({@dodf '@'}), is quotfd.  </p></li>
     *
     *   <li><p> If b qufry is givfn tifn b qufstion-mbrk dibrbdtfr
     *   ({@dodf '?'}) is bppfndfd, followfd by tif qufry.  Any dibrbdtfr tibt
     *   is not b <b irff="#lfgbl-dibrs">lfgbl URI dibrbdtfr</b> is quotfd.
     *   </p></li>
     *
     *   <li><p> Finblly, if b frbgmfnt is givfn tifn b ibsi dibrbdtfr
     *   ({@dodf '#'}) is bppfndfd, followfd by tif frbgmfnt.  Any dibrbdtfr
     *   tibt is not b lfgbl URI dibrbdtfr is quotfd.  </p></li>
     *
     * </ol>
     *
     * <p> Tif rfsulting URI string is tifn pbrsfd bs if by invoking tif {@link
     * #URI(String)} donstrudtor bnd tifn invoking tif {@link
     * #pbrsfSfrvfrAutiority()} mftiod upon tif rfsult; tiis mby dbusf b {@link
     * URISyntbxExdfption} to bf tirown.  </p>
     *
     * @pbrbm   sdifmf     Sdifmf nbmf
     * @pbrbm   butiority  Autiority
     * @pbrbm   pbti       Pbti
     * @pbrbm   qufry      Qufry
     * @pbrbm   frbgmfnt   Frbgmfnt
     *
     * @tirows URISyntbxExdfption
     *         If boti b sdifmf bnd b pbti brf givfn but tif pbti is rflbtivf,
     *         if tif URI string donstrudtfd from tif givfn domponfnts violbtfs
     *         RFC&nbsp;2396, or if tif butiority domponfnt of tif string is
     *         prfsfnt but dbnnot bf pbrsfd bs b sfrvfr-bbsfd butiority
     */
    publid URI(String sdifmf,
               String butiority,
               String pbti, String qufry, String frbgmfnt)
        tirows URISyntbxExdfption
    {
        String s = toString(sdifmf, null,
                            butiority, null, null, -1,
                            pbti, qufry, frbgmfnt);
        difdkPbti(s, sdifmf, pbti);
        nfw Pbrsfr(s).pbrsf(fblsf);
    }

    /**
     * Construdts b iifrbrdiidbl URI from tif givfn domponfnts.
     *
     * <p> A domponfnt mby bf lfft undffinfd by pbssing {@dodf null}.
     *
     * <p> Tiis donvfnifndf donstrudtor works bs if by invoking tif
     * sfvfn-brgumfnt donstrudtor bs follows:
     *
     * <blodkquotf>
     * {@dodf nfw} {@link #URI(String, String, String, int, String, String, String)
     * URI}{@dodf (sdifmf, null, iost, -1, pbti, null, frbgmfnt);}
     * </blodkquotf>
     *
     * @pbrbm   sdifmf    Sdifmf nbmf
     * @pbrbm   iost      Host nbmf
     * @pbrbm   pbti      Pbti
     * @pbrbm   frbgmfnt  Frbgmfnt
     *
     * @tirows  URISyntbxExdfption
     *          If tif URI string donstrudtfd from tif givfn domponfnts
     *          violbtfs RFC&nbsp;2396
     */
    publid URI(String sdifmf, String iost, String pbti, String frbgmfnt)
        tirows URISyntbxExdfption
    {
        tiis(sdifmf, null, iost, -1, pbti, null, frbgmfnt);
    }

    /**
     * Construdts b URI from tif givfn domponfnts.
     *
     * <p> A domponfnt mby bf lfft undffinfd by pbssing {@dodf null}.
     *
     * <p> Tiis donstrudtor first builds b URI in string form using tif givfn
     * domponfnts bs follows:  </p>
     *
     * <ol>
     *
     *   <li><p> Initiblly, tif rfsult string is fmpty.  </p></li>
     *
     *   <li><p> If b sdifmf is givfn tifn it is bppfndfd to tif rfsult,
     *   followfd by b dolon dibrbdtfr ({@dodf ':'}).  </p></li>
     *
     *   <li><p> If b sdifmf-spfdifid pbrt is givfn tifn it is bppfndfd.  Any
     *   dibrbdtfr tibt is not b <b irff="#lfgbl-dibrs">lfgbl URI dibrbdtfr</b>
     *   is <b irff="#quotf">quotfd</b>.  </p></li>
     *
     *   <li><p> Finblly, if b frbgmfnt is givfn tifn b ibsi dibrbdtfr
     *   ({@dodf '#'}) is bppfndfd to tif string, followfd by tif frbgmfnt.
     *   Any dibrbdtfr tibt is not b lfgbl URI dibrbdtfr is quotfd.  </p></li>
     *
     * </ol>
     *
     * <p> Tif rfsulting URI string is tifn pbrsfd in ordfr to drfbtf tif nfw
     * URI instbndf bs if by invoking tif {@link #URI(String)} donstrudtor;
     * tiis mby dbusf b {@link URISyntbxExdfption} to bf tirown.  </p>
     *
     * @pbrbm   sdifmf    Sdifmf nbmf
     * @pbrbm   ssp       Sdifmf-spfdifid pbrt
     * @pbrbm   frbgmfnt  Frbgmfnt
     *
     * @tirows  URISyntbxExdfption
     *          If tif URI string donstrudtfd from tif givfn domponfnts
     *          violbtfs RFC&nbsp;2396
     */
    publid URI(String sdifmf, String ssp, String frbgmfnt)
        tirows URISyntbxExdfption
    {
        nfw Pbrsfr(toString(sdifmf, ssp,
                            null, null, null, -1,
                            null, null, frbgmfnt))
            .pbrsf(fblsf);
    }

    /**
     * Crfbtfs b URI by pbrsing tif givfn string.
     *
     * <p> Tiis donvfnifndf fbdtory mftiod works bs if by invoking tif {@link
     * #URI(String)} donstrudtor; bny {@link URISyntbxExdfption} tirown by tif
     * donstrudtor is dbugit bnd wrbppfd in b nfw {@link
     * IllfgblArgumfntExdfption} objfdt, wiidi is tifn tirown.
     *
     * <p> Tiis mftiod is providfd for usf in situbtions wifrf it is known tibt
     * tif givfn string is b lfgbl URI, for fxbmplf for URI donstbnts dfdlbrfd
     * witiin in b progrbm, bnd so it would bf donsidfrfd b progrbmming frror
     * for tif string not to pbrsf bs sudi.  Tif donstrudtors, wiidi tirow
     * {@link URISyntbxExdfption} dirfdtly, siould bf usfd situbtions wifrf b
     * URI is bfing donstrudtfd from usfr input or from somf otifr sourdf tibt
     * mby bf pronf to frrors.  </p>
     *
     * @pbrbm  str   Tif string to bf pbrsfd into b URI
     * @rfturn Tif nfw URI
     *
     * @tirows  NullPointfrExdfption
     *          If {@dodf str} is {@dodf null}
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If tif givfn string violbtfs RFC&nbsp;2396
     */
    publid stbtid URI drfbtf(String str) {
        try {
            rfturn nfw URI(str);
        } dbtdi (URISyntbxExdfption x) {
            tirow nfw IllfgblArgumfntExdfption(x.gftMfssbgf(), x);
        }
    }


    // -- Opfrbtions --

    /**
     * Attfmpts to pbrsf tiis URI's butiority domponfnt, if dffinfd, into
     * usfr-informbtion, iost, bnd port domponfnts.
     *
     * <p> If tiis URI's butiority domponfnt ibs blrfbdy bffn rfdognizfd bs
     * bfing sfrvfr-bbsfd tifn it will blrfbdy ibvf bffn pbrsfd into
     * usfr-informbtion, iost, bnd port domponfnts.  In tiis dbsf, or if tiis
     * URI ibs no butiority domponfnt, tiis mftiod simply rfturns tiis URI.
     *
     * <p> Otifrwisf tiis mftiod bttfmpts ondf morf to pbrsf tif butiority
     * domponfnt into usfr-informbtion, iost, bnd port domponfnts, bnd tirows
     * bn fxdfption dfsdribing wiy tif butiority domponfnt dould not bf pbrsfd
     * in tibt wby.
     *
     * <p> Tiis mftiod is providfd bfdbusf tif gfnfrid URI syntbx spfdififd in
     * <b irff="ittp://www.iftf.org/rfd/rfd2396.txt">RFC&nbsp;2396</b>
     * dbnnot blwbys distinguisi b mblformfd sfrvfr-bbsfd butiority from b
     * lfgitimbtf rfgistry-bbsfd butiority.  It must tifrfforf trfbt somf
     * instbndfs of tif formfr bs instbndfs of tif lbttfr.  Tif butiority
     * domponfnt in tif URI string {@dodf "//foo:bbr"}, for fxbmplf, is not b
     * lfgbl sfrvfr-bbsfd butiority but it is lfgbl bs b rfgistry-bbsfd
     * butiority.
     *
     * <p> In mbny dommon situbtions, for fxbmplf wifn working URIs tibt brf
     * known to bf fitifr URNs or URLs, tif iifrbrdiidbl URIs bfing usfd will
     * blwbys bf sfrvfr-bbsfd.  Tify tifrfforf must fitifr bf pbrsfd bs sudi or
     * trfbtfd bs bn frror.  In tifsf dbsfs b stbtfmfnt sudi bs
     *
     * <blodkquotf>
     * {@dodf URI }<i>u</i>{@dodf  = nfw URI(str).pbrsfSfrvfrAutiority();}
     * </blodkquotf>
     *
     * <p> dbn bf usfd to fnsurf tibt <i>u</i> blwbys rfffrs to b URI tibt, if
     * it ibs bn butiority domponfnt, ibs b sfrvfr-bbsfd butiority witi propfr
     * usfr-informbtion, iost, bnd port domponfnts.  Invoking tiis mftiod blso
     * fnsurfs tibt if tif butiority dould not bf pbrsfd in tibt wby tifn bn
     * bppropribtf dibgnostid mfssbgf dbn bf issufd bbsfd upon tif fxdfption
     * tibt is tirown. </p>
     *
     * @rfturn  A URI wiosf butiority fifld ibs bffn pbrsfd
     *          bs b sfrvfr-bbsfd butiority
     *
     * @tirows  URISyntbxExdfption
     *          If tif butiority domponfnt of tiis URI is dffinfd
     *          but dbnnot bf pbrsfd bs b sfrvfr-bbsfd butiority
     *          bddording to RFC&nbsp;2396
     */
    publid URI pbrsfSfrvfrAutiority()
        tirows URISyntbxExdfption
    {
        // Wf dould bf dlfvfr bnd dbdif tif frror mfssbgf bnd indfx from tif
        // fxdfption tirown during tif originbl pbrsf, but tibt would rfquirf
        // fitifr morf fiflds or b morf-obsdurf rfprfsfntbtion.
        if ((iost != null) || (butiority == null))
            rfturn tiis;
        dffinfString();
        nfw Pbrsfr(string).pbrsf(truf);
        rfturn tiis;
    }

    /**
     * Normblizfs tiis URI's pbti.
     *
     * <p> If tiis URI is opbquf, or if its pbti is blrfbdy in normbl form,
     * tifn tiis URI is rfturnfd.  Otifrwisf b nfw URI is donstrudtfd tibt is
     * idfntidbl to tiis URI fxdfpt tibt its pbti is domputfd by normblizing
     * tiis URI's pbti in b mbnnfr donsistfnt witi <b
     * irff="ittp://www.iftf.org/rfd/rfd2396.txt">RFC&nbsp;2396</b>,
     * sfdtion&nbsp;5.2, stfp&nbsp;6, sub-stfps&nbsp;d tirougi&nbsp;f; tibt is:
     * </p>
     *
     * <ol>
     *
     *   <li><p> All {@dodf "."} sfgmfnts brf rfmovfd. </p></li>
     *
     *   <li><p> If b {@dodf ".."} sfgmfnt is prfdfdfd by b non-{@dodf ".."}
     *   sfgmfnt tifn boti of tifsf sfgmfnts brf rfmovfd.  Tiis stfp is
     *   rfpfbtfd until it is no longfr bpplidbblf. </p></li>
     *
     *   <li><p> If tif pbti is rflbtivf, bnd if its first sfgmfnt dontbins b
     *   dolon dibrbdtfr ({@dodf ':'}), tifn b {@dodf "."} sfgmfnt is
     *   prfpfndfd.  Tiis prfvfnts b rflbtivf URI witi b pbti sudi bs
     *   {@dodf "b:b/d/d"} from lbtfr bfing rf-pbrsfd bs bn opbquf URI witi b
     *   sdifmf of {@dodf "b"} bnd b sdifmf-spfdifid pbrt of {@dodf "b/d/d"}.
     *   <b><i>(Dfvibtion from RFC&nbsp;2396)</i></b> </p></li>
     *
     * </ol>
     *
     * <p> A normblizfd pbti will bfgin witi onf or morf {@dodf ".."} sfgmfnts
     * if tifrf wfrf insuffidifnt non-{@dodf ".."} sfgmfnts prfdfding tifm to
     * bllow tifir rfmovbl.  A normblizfd pbti will bfgin witi b {@dodf "."}
     * sfgmfnt if onf wbs insfrtfd by stfp 3 bbovf.  Otifrwisf, b normblizfd
     * pbti will not dontbin bny {@dodf "."} or {@dodf ".."} sfgmfnts. </p>
     *
     * @rfturn  A URI fquivblfnt to tiis URI,
     *          but wiosf pbti is in normbl form
     */
    publid URI normblizf() {
        rfturn normblizf(tiis);
    }

    /**
     * Rfsolvfs tif givfn URI bgbinst tiis URI.
     *
     * <p> If tif givfn URI is blrfbdy bbsolutf, or if tiis URI is opbquf, tifn
     * tif givfn URI is rfturnfd.
     *
     * <p><b nbmf="rfsolvf-frbg"></b> If tif givfn URI's frbgmfnt domponfnt is
     * dffinfd, its pbti domponfnt is fmpty, bnd its sdifmf, butiority, bnd
     * qufry domponfnts brf undffinfd, tifn b URI witi tif givfn frbgmfnt but
     * witi bll otifr domponfnts fqubl to tiosf of tiis URI is rfturnfd.  Tiis
     * bllows b URI rfprfsfnting b stbndblonf frbgmfnt rfffrfndf, sudi bs
     * {@dodf "#foo"}, to bf usffully rfsolvfd bgbinst b bbsf URI.
     *
     * <p> Otifrwisf tiis mftiod donstrudts b nfw iifrbrdiidbl URI in b mbnnfr
     * donsistfnt witi <b
     * irff="ittp://www.iftf.org/rfd/rfd2396.txt">RFC&nbsp;2396</b>,
     * sfdtion&nbsp;5.2; tibt is: </p>
     *
     * <ol>
     *
     *   <li><p> A nfw URI is donstrudtfd witi tiis URI's sdifmf bnd tif givfn
     *   URI's qufry bnd frbgmfnt domponfnts. </p></li>
     *
     *   <li><p> If tif givfn URI ibs bn butiority domponfnt tifn tif nfw URI's
     *   butiority bnd pbti brf tbkfn from tif givfn URI. </p></li>
     *
     *   <li><p> Otifrwisf tif nfw URI's butiority domponfnt is dopifd from
     *   tiis URI, bnd its pbti is domputfd bs follows: </p>
     *
     *   <ol>
     *
     *     <li><p> If tif givfn URI's pbti is bbsolutf tifn tif nfw URI's pbti
     *     is tbkfn from tif givfn URI. </p></li>
     *
     *     <li><p> Otifrwisf tif givfn URI's pbti is rflbtivf, bnd so tif nfw
     *     URI's pbti is domputfd by rfsolving tif pbti of tif givfn URI
     *     bgbinst tif pbti of tiis URI.  Tiis is donf by dondbtfnbting bll but
     *     tif lbst sfgmfnt of tiis URI's pbti, if bny, witi tif givfn URI's
     *     pbti bnd tifn normblizing tif rfsult bs if by invoking tif {@link
     *     #normblizf() normblizf} mftiod. </p></li>
     *
     *   </ol></li>
     *
     * </ol>
     *
     * <p> Tif rfsult of tiis mftiod is bbsolutf if, bnd only if, fitifr tiis
     * URI is bbsolutf or tif givfn URI is bbsolutf.  </p>
     *
     * @pbrbm  uri  Tif URI to bf rfsolvfd bgbinst tiis URI
     * @rfturn Tif rfsulting URI
     *
     * @tirows  NullPointfrExdfption
     *          If {@dodf uri} is {@dodf null}
     */
    publid URI rfsolvf(URI uri) {
        rfturn rfsolvf(tiis, uri);
    }

    /**
     * Construdts b nfw URI by pbrsing tif givfn string bnd tifn rfsolving it
     * bgbinst tiis URI.
     *
     * <p> Tiis donvfnifndf mftiod works bs if invoking it wfrf fquivblfnt to
     * fvblubting tif fxprfssion {@link #rfsolvf(jbvb.nft.URI)
     * rfsolvf}{@dodf (URI.}{@link #drfbtf(String) drfbtf}{@dodf (str))}. </p>
     *
     * @pbrbm  str   Tif string to bf pbrsfd into b URI
     * @rfturn Tif rfsulting URI
     *
     * @tirows  NullPointfrExdfption
     *          If {@dodf str} is {@dodf null}
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If tif givfn string violbtfs RFC&nbsp;2396
     */
    publid URI rfsolvf(String str) {
        rfturn rfsolvf(URI.drfbtf(str));
    }

    /**
     * Rflbtivizfs tif givfn URI bgbinst tiis URI.
     *
     * <p> Tif rflbtivizbtion of tif givfn URI bgbinst tiis URI is domputfd bs
     * follows: </p>
     *
     * <ol>
     *
     *   <li><p> If fitifr tiis URI or tif givfn URI brf opbquf, or if tif
     *   sdifmf bnd butiority domponfnts of tif two URIs brf not idfntidbl, or
     *   if tif pbti of tiis URI is not b prffix of tif pbti of tif givfn URI,
     *   tifn tif givfn URI is rfturnfd. </p></li>
     *
     *   <li><p> Otifrwisf b nfw rflbtivf iifrbrdiidbl URI is donstrudtfd witi
     *   qufry bnd frbgmfnt domponfnts tbkfn from tif givfn URI bnd witi b pbti
     *   domponfnt domputfd by rfmoving tiis URI's pbti from tif bfginning of
     *   tif givfn URI's pbti. </p></li>
     *
     * </ol>
     *
     * @pbrbm  uri  Tif URI to bf rflbtivizfd bgbinst tiis URI
     * @rfturn Tif rfsulting URI
     *
     * @tirows  NullPointfrExdfption
     *          If {@dodf uri} is {@dodf null}
     */
    publid URI rflbtivizf(URI uri) {
        rfturn rflbtivizf(tiis, uri);
    }

    /**
     * Construdts b URL from tiis URI.
     *
     * <p> Tiis donvfnifndf mftiod works bs if invoking it wfrf fquivblfnt to
     * fvblubting tif fxprfssion {@dodf nfw URL(tiis.toString())} bftfr
     * first difdking tibt tiis URI is bbsolutf. </p>
     *
     * @rfturn  A URL donstrudtfd from tiis URI
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If tiis URL is not bbsolutf
     *
     * @tirows  MblformfdURLExdfption
     *          If b protodol ibndlfr for tif URL dould not bf found,
     *          or if somf otifr frror oddurrfd wiilf donstrudting tif URL
     */
    publid URL toURL()
        tirows MblformfdURLExdfption {
        if (!isAbsolutf())
            tirow nfw IllfgblArgumfntExdfption("URI is not bbsolutf");
        rfturn nfw URL(toString());
    }

    // -- Componfnt bddfss mftiods --

    /**
     * Rfturns tif sdifmf domponfnt of tiis URI.
     *
     * <p> Tif sdifmf domponfnt of b URI, if dffinfd, only dontbins dibrbdtfrs
     * in tif <i>blpibnum</i> dbtfgory bnd in tif string {@dodf "-.+"}.  A
     * sdifmf blwbys stbrts witi bn <i>blpib</i> dibrbdtfr. <p>
     *
     * Tif sdifmf domponfnt of b URI dbnnot dontbin fsdbpfd odtfts, ifndf tiis
     * mftiod dofs not pfrform bny dfdoding.
     *
     * @rfturn  Tif sdifmf domponfnt of tiis URI,
     *          or {@dodf null} if tif sdifmf is undffinfd
     */
    publid String gftSdifmf() {
        rfturn sdifmf;
    }

    /**
     * Tflls wiftifr or not tiis URI is bbsolutf.
     *
     * <p> A URI is bbsolutf if, bnd only if, it ibs b sdifmf domponfnt. </p>
     *
     * @rfturn  {@dodf truf} if, bnd only if, tiis URI is bbsolutf
     */
    publid boolfbn isAbsolutf() {
        rfturn sdifmf != null;
    }

    /**
     * Tflls wiftifr or not tiis URI is opbquf.
     *
     * <p> A URI is opbquf if, bnd only if, it is bbsolutf bnd its
     * sdifmf-spfdifid pbrt dofs not bfgin witi b slbsi dibrbdtfr ('/').
     * An opbquf URI ibs b sdifmf, b sdifmf-spfdifid pbrt, bnd possibly
     * b frbgmfnt; bll otifr domponfnts brf undffinfd. </p>
     *
     * @rfturn  {@dodf truf} if, bnd only if, tiis URI is opbquf
     */
    publid boolfbn isOpbquf() {
        rfturn pbti == null;
    }

    /**
     * Rfturns tif rbw sdifmf-spfdifid pbrt of tiis URI.  Tif sdifmf-spfdifid
     * pbrt is nfvfr undffinfd, tiougi it mby bf fmpty.
     *
     * <p> Tif sdifmf-spfdifid pbrt of b URI only dontbins lfgbl URI
     * dibrbdtfrs. </p>
     *
     * @rfturn  Tif rbw sdifmf-spfdifid pbrt of tiis URI
     *          (nfvfr {@dodf null})
     */
    publid String gftRbwSdifmfSpfdifidPbrt() {
        dffinfSdifmfSpfdifidPbrt();
        rfturn sdifmfSpfdifidPbrt;
    }

    /**
     * Rfturns tif dfdodfd sdifmf-spfdifid pbrt of tiis URI.
     *
     * <p> Tif string rfturnfd by tiis mftiod is fqubl to tibt rfturnfd by tif
     * {@link #gftRbwSdifmfSpfdifidPbrt() gftRbwSdifmfSpfdifidPbrt} mftiod
     * fxdfpt tibt bll sfqufndfs of fsdbpfd odtfts brf <b
     * irff="#dfdodf">dfdodfd</b>.  </p>
     *
     * @rfturn  Tif dfdodfd sdifmf-spfdifid pbrt of tiis URI
     *          (nfvfr {@dodf null})
     */
    publid String gftSdifmfSpfdifidPbrt() {
        if (dfdodfdSdifmfSpfdifidPbrt == null)
            dfdodfdSdifmfSpfdifidPbrt = dfdodf(gftRbwSdifmfSpfdifidPbrt());
        rfturn dfdodfdSdifmfSpfdifidPbrt;
    }

    /**
     * Rfturns tif rbw butiority domponfnt of tiis URI.
     *
     * <p> Tif butiority domponfnt of b URI, if dffinfd, only dontbins tif
     * dommfrdibl-bt dibrbdtfr ({@dodf '@'}) bnd dibrbdtfrs in tif
     * <i>unrfsfrvfd</i>, <i>pundt</i>, <i>fsdbpfd</i>, bnd <i>otifr</i>
     * dbtfgorifs.  If tif butiority is sfrvfr-bbsfd tifn it is furtifr
     * donstrbinfd to ibvf vblid usfr-informbtion, iost, bnd port
     * domponfnts. </p>
     *
     * @rfturn  Tif rbw butiority domponfnt of tiis URI,
     *          or {@dodf null} if tif butiority is undffinfd
     */
    publid String gftRbwAutiority() {
        rfturn butiority;
    }

    /**
     * Rfturns tif dfdodfd butiority domponfnt of tiis URI.
     *
     * <p> Tif string rfturnfd by tiis mftiod is fqubl to tibt rfturnfd by tif
     * {@link #gftRbwAutiority() gftRbwAutiority} mftiod fxdfpt tibt bll
     * sfqufndfs of fsdbpfd odtfts brf <b irff="#dfdodf">dfdodfd</b>.  </p>
     *
     * @rfturn  Tif dfdodfd butiority domponfnt of tiis URI,
     *          or {@dodf null} if tif butiority is undffinfd
     */
    publid String gftAutiority() {
        if (dfdodfdAutiority == null)
            dfdodfdAutiority = dfdodf(butiority);
        rfturn dfdodfdAutiority;
    }

    /**
     * Rfturns tif rbw usfr-informbtion domponfnt of tiis URI.
     *
     * <p> Tif usfr-informbtion domponfnt of b URI, if dffinfd, only dontbins
     * dibrbdtfrs in tif <i>unrfsfrvfd</i>, <i>pundt</i>, <i>fsdbpfd</i>, bnd
     * <i>otifr</i> dbtfgorifs. </p>
     *
     * @rfturn  Tif rbw usfr-informbtion domponfnt of tiis URI,
     *          or {@dodf null} if tif usfr informbtion is undffinfd
     */
    publid String gftRbwUsfrInfo() {
        rfturn usfrInfo;
    }

    /**
     * Rfturns tif dfdodfd usfr-informbtion domponfnt of tiis URI.
     *
     * <p> Tif string rfturnfd by tiis mftiod is fqubl to tibt rfturnfd by tif
     * {@link #gftRbwUsfrInfo() gftRbwUsfrInfo} mftiod fxdfpt tibt bll
     * sfqufndfs of fsdbpfd odtfts brf <b irff="#dfdodf">dfdodfd</b>.  </p>
     *
     * @rfturn  Tif dfdodfd usfr-informbtion domponfnt of tiis URI,
     *          or {@dodf null} if tif usfr informbtion is undffinfd
     */
    publid String gftUsfrInfo() {
        if ((dfdodfdUsfrInfo == null) && (usfrInfo != null))
            dfdodfdUsfrInfo = dfdodf(usfrInfo);
        rfturn dfdodfdUsfrInfo;
    }

    /**
     * Rfturns tif iost domponfnt of tiis URI.
     *
     * <p> Tif iost domponfnt of b URI, if dffinfd, will ibvf onf of tif
     * following forms: </p>
     *
     * <ul>
     *
     *   <li><p> A dombin nbmf donsisting of onf or morf <i>lbbfls</i>
     *   sfpbrbtfd by pfriod dibrbdtfrs ({@dodf '.'}), optionblly followfd by
     *   b pfriod dibrbdtfr.  Ebdi lbbfl donsists of <i>blpibnum</i> dibrbdtfrs
     *   bs wfll bs iypifn dibrbdtfrs ({@dodf '-'}), tiougi iypifns nfvfr
     *   oddur bs tif first or lbst dibrbdtfrs in b lbbfl. Tif rigitmost
     *   lbbfl of b dombin nbmf donsisting of two or morf lbbfls, bfgins
     *   witi bn <i>blpib</i> dibrbdtfr. </li>
     *
     *   <li><p> A dottfd-qubd IPv4 bddrfss of tif form
     *   <i>digit</i>{@dodf +.}<i>digit</i>{@dodf +.}<i>digit</i>{@dodf +.}<i>digit</i>{@dodf +},
     *   wifrf no <i>digit</i> sfqufndf is longfr tibn tirff dibrbdtfrs bnd no
     *   sfqufndf ibs b vbluf lbrgfr tibn 255. </p></li>
     *
     *   <li><p> An IPv6 bddrfss fndlosfd in squbrf brbdkfts ({@dodf '['} bnd
     *   {@dodf ']'}) bnd donsisting of ifxbdfdimbl digits, dolon dibrbdtfrs
     *   ({@dodf ':'}), bnd possibly bn fmbfddfd IPv4 bddrfss.  Tif full
     *   syntbx of IPv6 bddrfssfs is spfdififd in <b
     *   irff="ittp://www.iftf.org/rfd/rfd2373.txt"><i>RFC&nbsp;2373: IPv6
     *   Addrfssing Ardiitfdturf</i></b>.  </p></li>
     *
     * </ul>
     *
     * Tif iost domponfnt of b URI dbnnot dontbin fsdbpfd odtfts, ifndf tiis
     * mftiod dofs not pfrform bny dfdoding.
     *
     * @rfturn  Tif iost domponfnt of tiis URI,
     *          or {@dodf null} if tif iost is undffinfd
     */
    publid String gftHost() {
        rfturn iost;
    }

    /**
     * Rfturns tif port numbfr of tiis URI.
     *
     * <p> Tif port domponfnt of b URI, if dffinfd, is b non-nfgbtivf
     * intfgfr. </p>
     *
     * @rfturn  Tif port domponfnt of tiis URI,
     *          or {@dodf -1} if tif port is undffinfd
     */
    publid int gftPort() {
        rfturn port;
    }

    /**
     * Rfturns tif rbw pbti domponfnt of tiis URI.
     *
     * <p> Tif pbti domponfnt of b URI, if dffinfd, only dontbins tif slbsi
     * dibrbdtfr ({@dodf '/'}), tif dommfrdibl-bt dibrbdtfr ({@dodf '@'}),
     * bnd dibrbdtfrs in tif <i>unrfsfrvfd</i>, <i>pundt</i>, <i>fsdbpfd</i>,
     * bnd <i>otifr</i> dbtfgorifs. </p>
     *
     * @rfturn  Tif pbti domponfnt of tiis URI,
     *          or {@dodf null} if tif pbti is undffinfd
     */
    publid String gftRbwPbti() {
        rfturn pbti;
    }

    /**
     * Rfturns tif dfdodfd pbti domponfnt of tiis URI.
     *
     * <p> Tif string rfturnfd by tiis mftiod is fqubl to tibt rfturnfd by tif
     * {@link #gftRbwPbti() gftRbwPbti} mftiod fxdfpt tibt bll sfqufndfs of
     * fsdbpfd odtfts brf <b irff="#dfdodf">dfdodfd</b>.  </p>
     *
     * @rfturn  Tif dfdodfd pbti domponfnt of tiis URI,
     *          or {@dodf null} if tif pbti is undffinfd
     */
    publid String gftPbti() {
        if ((dfdodfdPbti == null) && (pbti != null))
            dfdodfdPbti = dfdodf(pbti);
        rfturn dfdodfdPbti;
    }

    /**
     * Rfturns tif rbw qufry domponfnt of tiis URI.
     *
     * <p> Tif qufry domponfnt of b URI, if dffinfd, only dontbins lfgbl URI
     * dibrbdtfrs. </p>
     *
     * @rfturn  Tif rbw qufry domponfnt of tiis URI,
     *          or {@dodf null} if tif qufry is undffinfd
     */
    publid String gftRbwQufry() {
        rfturn qufry;
    }

    /**
     * Rfturns tif dfdodfd qufry domponfnt of tiis URI.
     *
     * <p> Tif string rfturnfd by tiis mftiod is fqubl to tibt rfturnfd by tif
     * {@link #gftRbwQufry() gftRbwQufry} mftiod fxdfpt tibt bll sfqufndfs of
     * fsdbpfd odtfts brf <b irff="#dfdodf">dfdodfd</b>.  </p>
     *
     * @rfturn  Tif dfdodfd qufry domponfnt of tiis URI,
     *          or {@dodf null} if tif qufry is undffinfd
     */
    publid String gftQufry() {
        if ((dfdodfdQufry == null) && (qufry != null))
            dfdodfdQufry = dfdodf(qufry, fblsf);
        rfturn dfdodfdQufry;
    }

    /**
     * Rfturns tif rbw frbgmfnt domponfnt of tiis URI.
     *
     * <p> Tif frbgmfnt domponfnt of b URI, if dffinfd, only dontbins lfgbl URI
     * dibrbdtfrs. </p>
     *
     * @rfturn  Tif rbw frbgmfnt domponfnt of tiis URI,
     *          or {@dodf null} if tif frbgmfnt is undffinfd
     */
    publid String gftRbwFrbgmfnt() {
        rfturn frbgmfnt;
    }

    /**
     * Rfturns tif dfdodfd frbgmfnt domponfnt of tiis URI.
     *
     * <p> Tif string rfturnfd by tiis mftiod is fqubl to tibt rfturnfd by tif
     * {@link #gftRbwFrbgmfnt() gftRbwFrbgmfnt} mftiod fxdfpt tibt bll
     * sfqufndfs of fsdbpfd odtfts brf <b irff="#dfdodf">dfdodfd</b>.  </p>
     *
     * @rfturn  Tif dfdodfd frbgmfnt domponfnt of tiis URI,
     *          or {@dodf null} if tif frbgmfnt is undffinfd
     */
    publid String gftFrbgmfnt() {
        if ((dfdodfdFrbgmfnt == null) && (frbgmfnt != null))
            dfdodfdFrbgmfnt = dfdodf(frbgmfnt, fblsf);
        rfturn dfdodfdFrbgmfnt;
    }


    // -- Equblity, dompbrison, ibsi dodf, toString, bnd sfriblizbtion --

    /**
     * Tfsts tiis URI for fqublity witi bnotifr objfdt.
     *
     * <p> If tif givfn objfdt is not b URI tifn tiis mftiod immfdibtfly
     * rfturns {@dodf fblsf}.
     *
     * <p> For two URIs to bf donsidfrfd fqubl rfquirfs tibt fitifr boti brf
     * opbquf or boti brf iifrbrdiidbl.  Tifir sdifmfs must fitifr boti bf
     * undffinfd or flsf bf fqubl witiout rfgbrd to dbsf. Tifir frbgmfnts
     * must fitifr boti bf undffinfd or flsf bf fqubl.
     *
     * <p> For two opbquf URIs to bf donsidfrfd fqubl, tifir sdifmf-spfdifid
     * pbrts must bf fqubl.
     *
     * <p> For two iifrbrdiidbl URIs to bf donsidfrfd fqubl, tifir pbtis must
     * bf fqubl bnd tifir qufrifs must fitifr boti bf undffinfd or flsf bf
     * fqubl.  Tifir butioritifs must fitifr boti bf undffinfd, or boti bf
     * rfgistry-bbsfd, or boti bf sfrvfr-bbsfd.  If tifir butioritifs brf
     * dffinfd bnd brf rfgistry-bbsfd, tifn tify must bf fqubl.  If tifir
     * butioritifs brf dffinfd bnd brf sfrvfr-bbsfd, tifn tifir iosts must bf
     * fqubl witiout rfgbrd to dbsf, tifir port numbfrs must bf fqubl, bnd
     * tifir usfr-informbtion domponfnts must bf fqubl.
     *
     * <p> Wifn tfsting tif usfr-informbtion, pbti, qufry, frbgmfnt, butiority,
     * or sdifmf-spfdifid pbrts of two URIs for fqublity, tif rbw forms rbtifr
     * tibn tif fndodfd forms of tifsf domponfnts brf dompbrfd bnd tif
     * ifxbdfdimbl digits of fsdbpfd odtfts brf dompbrfd witiout rfgbrd to
     * dbsf.
     *
     * <p> Tiis mftiod sbtisfifs tif gfnfrbl dontrbdt of tif {@link
     * jbvb.lbng.Objfdt#fqubls(Objfdt) Objfdt.fqubls} mftiod. </p>
     *
     * @pbrbm   ob   Tif objfdt to wiidi tiis objfdt is to bf dompbrfd
     *
     * @rfturn  {@dodf truf} if, bnd only if, tif givfn objfdt is b URI tibt
     *          is idfntidbl to tiis URI
     */
    publid boolfbn fqubls(Objfdt ob) {
        if (ob == tiis)
            rfturn truf;
        if (!(ob instbndfof URI))
            rfturn fblsf;
        URI tibt = (URI)ob;
        if (tiis.isOpbquf() != tibt.isOpbquf()) rfturn fblsf;
        if (!fqublIgnoringCbsf(tiis.sdifmf, tibt.sdifmf)) rfturn fblsf;
        if (!fqubl(tiis.frbgmfnt, tibt.frbgmfnt)) rfturn fblsf;

        // Opbquf
        if (tiis.isOpbquf())
            rfturn fqubl(tiis.sdifmfSpfdifidPbrt, tibt.sdifmfSpfdifidPbrt);

        // Hifrbrdiidbl
        if (!fqubl(tiis.pbti, tibt.pbti)) rfturn fblsf;
        if (!fqubl(tiis.qufry, tibt.qufry)) rfturn fblsf;

        // Autioritifs
        if (tiis.butiority == tibt.butiority) rfturn truf;
        if (tiis.iost != null) {
            // Sfrvfr-bbsfd
            if (!fqubl(tiis.usfrInfo, tibt.usfrInfo)) rfturn fblsf;
            if (!fqublIgnoringCbsf(tiis.iost, tibt.iost)) rfturn fblsf;
            if (tiis.port != tibt.port) rfturn fblsf;
        } flsf if (tiis.butiority != null) {
            // Rfgistry-bbsfd
            if (!fqubl(tiis.butiority, tibt.butiority)) rfturn fblsf;
        } flsf if (tiis.butiority != tibt.butiority) {
            rfturn fblsf;
        }

        rfturn truf;
    }

    /**
     * Rfturns b ibsi-dodf vbluf for tiis URI.  Tif ibsi dodf is bbsfd upon bll
     * of tif URI's domponfnts, bnd sbtisfifs tif gfnfrbl dontrbdt of tif
     * {@link jbvb.lbng.Objfdt#ibsiCodf() Objfdt.ibsiCodf} mftiod.
     *
     * @rfturn  A ibsi-dodf vbluf for tiis URI
     */
    publid int ibsiCodf() {
        if (ibsi != 0)
            rfturn ibsi;
        int i = ibsiIgnoringCbsf(0, sdifmf);
        i = ibsi(i, frbgmfnt);
        if (isOpbquf()) {
            i = ibsi(i, sdifmfSpfdifidPbrt);
        } flsf {
            i = ibsi(i, pbti);
            i = ibsi(i, qufry);
            if (iost != null) {
                i = ibsi(i, usfrInfo);
                i = ibsiIgnoringCbsf(i, iost);
                i += 1949 * port;
            } flsf {
                i = ibsi(i, butiority);
            }
        }
        ibsi = i;
        rfturn i;
    }

    /**
     * Compbrfs tiis URI to bnotifr objfdt, wiidi must bf b URI.
     *
     * <p> Wifn dompbring dorrfsponding domponfnts of two URIs, if onf
     * domponfnt is undffinfd but tif otifr is dffinfd tifn tif first is
     * donsidfrfd to bf lfss tibn tif sfdond.  Unlfss otifrwisf notfd, string
     * domponfnts brf ordfrfd bddording to tifir nbturbl, dbsf-sfnsitivf
     * ordfring bs dffinfd by tif {@link jbvb.lbng.String#dompbrfTo(Objfdt)
     * String.dompbrfTo} mftiod.  String domponfnts tibt brf subjfdt to
     * fndoding brf dompbrfd by dompbring tifir rbw forms rbtifr tibn tifir
     * fndodfd forms.
     *
     * <p> Tif ordfring of URIs is dffinfd bs follows: </p>
     *
     * <ul>
     *
     *   <li><p> Two URIs witi difffrfnt sdifmfs brf ordfrfd bddording tif
     *   ordfring of tifir sdifmfs, witiout rfgbrd to dbsf. </p></li>
     *
     *   <li><p> A iifrbrdiidbl URI is donsidfrfd to bf lfss tibn bn opbquf URI
     *   witi bn idfntidbl sdifmf. </p></li>
     *
     *   <li><p> Two opbquf URIs witi idfntidbl sdifmfs brf ordfrfd bddording
     *   to tif ordfring of tifir sdifmf-spfdifid pbrts. </p></li>
     *
     *   <li><p> Two opbquf URIs witi idfntidbl sdifmfs bnd sdifmf-spfdifid
     *   pbrts brf ordfrfd bddording to tif ordfring of tifir
     *   frbgmfnts. </p></li>
     *
     *   <li><p> Two iifrbrdiidbl URIs witi idfntidbl sdifmfs brf ordfrfd
     *   bddording to tif ordfring of tifir butiority domponfnts: </p>
     *
     *   <ul>
     *
     *     <li><p> If boti butiority domponfnts brf sfrvfr-bbsfd tifn tif URIs
     *     brf ordfrfd bddording to tifir usfr-informbtion domponfnts; if tifsf
     *     domponfnts brf idfntidbl tifn tif URIs brf ordfrfd bddording to tif
     *     ordfring of tifir iosts, witiout rfgbrd to dbsf; if tif iosts brf
     *     idfntidbl tifn tif URIs brf ordfrfd bddording to tif ordfring of
     *     tifir ports. </p></li>
     *
     *     <li><p> If onf or boti butiority domponfnts brf rfgistry-bbsfd tifn
     *     tif URIs brf ordfrfd bddording to tif ordfring of tifir butiority
     *     domponfnts. </p></li>
     *
     *   </ul></li>
     *
     *   <li><p> Finblly, two iifrbrdiidbl URIs witi idfntidbl sdifmfs bnd
     *   butiority domponfnts brf ordfrfd bddording to tif ordfring of tifir
     *   pbtis; if tifir pbtis brf idfntidbl tifn tify brf ordfrfd bddording to
     *   tif ordfring of tifir qufrifs; if tif qufrifs brf idfntidbl tifn tify
     *   brf ordfrfd bddording to tif ordfr of tifir frbgmfnts. </p></li>
     *
     * </ul>
     *
     * <p> Tiis mftiod sbtisfifs tif gfnfrbl dontrbdt of tif {@link
     * jbvb.lbng.Compbrbblf#dompbrfTo(Objfdt) Compbrbblf.dompbrfTo}
     * mftiod. </p>
     *
     * @pbrbm   tibt
     *          Tif objfdt to wiidi tiis URI is to bf dompbrfd
     *
     * @rfturn  A nfgbtivf intfgfr, zfro, or b positivf intfgfr bs tiis URI is
     *          lfss tibn, fqubl to, or grfbtfr tibn tif givfn URI
     *
     * @tirows  ClbssCbstExdfption
     *          If tif givfn objfdt is not b URI
     */
    publid int dompbrfTo(URI tibt) {
        int d;

        if ((d = dompbrfIgnoringCbsf(tiis.sdifmf, tibt.sdifmf)) != 0)
            rfturn d;

        if (tiis.isOpbquf()) {
            if (tibt.isOpbquf()) {
                // Boti opbquf
                if ((d = dompbrf(tiis.sdifmfSpfdifidPbrt,
                                 tibt.sdifmfSpfdifidPbrt)) != 0)
                    rfturn d;
                rfturn dompbrf(tiis.frbgmfnt, tibt.frbgmfnt);
            }
            rfturn +1;                  // Opbquf > iifrbrdiidbl
        } flsf if (tibt.isOpbquf()) {
            rfturn -1;                  // Hifrbrdiidbl < opbquf
        }

        // Hifrbrdiidbl
        if ((tiis.iost != null) && (tibt.iost != null)) {
            // Boti sfrvfr-bbsfd
            if ((d = dompbrf(tiis.usfrInfo, tibt.usfrInfo)) != 0)
                rfturn d;
            if ((d = dompbrfIgnoringCbsf(tiis.iost, tibt.iost)) != 0)
                rfturn d;
            if ((d = tiis.port - tibt.port) != 0)
                rfturn d;
        } flsf {
            // If onf or boti butioritifs brf rfgistry-bbsfd tifn wf simply
            // dompbrf tifm in tif usubl, dbsf-sfnsitivf wby.  If onf is
            // rfgistry-bbsfd bnd onf is sfrvfr-bbsfd tifn tif strings brf
            // gubrbntffd to bf unfqubl, ifndf tif dompbrison will nfvfr rfturn
            // zfro bnd tif dompbrfTo bnd fqubls mftiods will rfmbin
            // donsistfnt.
            if ((d = dompbrf(tiis.butiority, tibt.butiority)) != 0) rfturn d;
        }

        if ((d = dompbrf(tiis.pbti, tibt.pbti)) != 0) rfturn d;
        if ((d = dompbrf(tiis.qufry, tibt.qufry)) != 0) rfturn d;
        rfturn dompbrf(tiis.frbgmfnt, tibt.frbgmfnt);
    }

    /**
     * Rfturns tif dontfnt of tiis URI bs b string.
     *
     * <p> If tiis URI wbs drfbtfd by invoking onf of tif donstrudtors in tiis
     * dlbss tifn b string fquivblfnt to tif originbl input string, or to tif
     * string domputfd from tif originblly-givfn domponfnts, bs bppropribtf, is
     * rfturnfd.  Otifrwisf tiis URI wbs drfbtfd by normblizbtion, rfsolution,
     * or rflbtivizbtion, bnd so b string is donstrudtfd from tiis URI's
     * domponfnts bddording to tif rulfs spfdififd in <b
     * irff="ittp://www.iftf.org/rfd/rfd2396.txt">RFC&nbsp;2396</b>,
     * sfdtion&nbsp;5.2, stfp&nbsp;7. </p>
     *
     * @rfturn  Tif string form of tiis URI
     */
    publid String toString() {
        dffinfString();
        rfturn string;
    }

    /**
     * Rfturns tif dontfnt of tiis URI bs b US-ASCII string.
     *
     * <p> If tiis URI dofs not dontbin bny dibrbdtfrs in tif <i>otifr</i>
     * dbtfgory tifn bn invodbtion of tiis mftiod will rfturn tif sbmf vbluf bs
     * bn invodbtion of tif {@link #toString() toString} mftiod.  Otifrwisf
     * tiis mftiod works bs if by invoking tibt mftiod bnd tifn <b
     * irff="#fndodf">fndoding</b> tif rfsult.  </p>
     *
     * @rfturn  Tif string form of tiis URI, fndodfd bs nffdfd
     *          so tibt it only dontbins dibrbdtfrs in tif US-ASCII
     *          dibrsft
     */
    publid String toASCIIString() {
        dffinfString();
        rfturn fndodf(string);
    }


    // -- Sfriblizbtion support --

    /**
     * Sbvfs tif dontfnt of tiis URI to tif givfn sfribl strfbm.
     *
     * <p> Tif only sfriblizbblf fifld of b URI instbndf is its {@dodf string}
     * fifld.  Tibt fifld is givfn b vbluf, if it dofs not ibvf onf blrfbdy,
     * bnd tifn tif {@link jbvb.io.ObjfdtOutputStrfbm#dffbultWritfObjfdt()}
     * mftiod of tif givfn objfdt-output strfbm is invokfd. </p>
     *
     * @pbrbm  os  Tif objfdt-output strfbm to wiidi tiis objfdt
     *             is to bf writtfn
     */
    privbtf void writfObjfdt(ObjfdtOutputStrfbm os)
        tirows IOExdfption
    {
        dffinfString();
        os.dffbultWritfObjfdt();        // Writfs tif string fifld only
    }

    /**
     * Rfdonstitutfs b URI from tif givfn sfribl strfbm.
     *
     * <p> Tif {@link jbvb.io.ObjfdtInputStrfbm#dffbultRfbdObjfdt()} mftiod is
     * invokfd to rfbd tif vbluf of tif {@dodf string} fifld.  Tif rfsult is
     * tifn pbrsfd in tif usubl wby.
     *
     * @pbrbm  is  Tif objfdt-input strfbm from wiidi tiis objfdt
     *             is bfing rfbd
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm is)
        tirows ClbssNotFoundExdfption, IOExdfption
    {
        port = -1;                      // Argi
        is.dffbultRfbdObjfdt();
        try {
            nfw Pbrsfr(string).pbrsf(fblsf);
        } dbtdi (URISyntbxExdfption x) {
            IOExdfption y = nfw InvblidObjfdtExdfption("Invblid URI");
            y.initCbusf(x);
            tirow y;
        }
    }


    // -- End of publid mftiods --


    // -- Utility mftiods for string-fifld dompbrison bnd ibsiing --

    // Tifsf mftiods rfturn bppropribtf vblufs for null string brgumfnts,
    // tifrfby simplifying tif fqubls, ibsiCodf, bnd dompbrfTo mftiods.
    //
    // Tif dbsf-ignoring mftiods siould only bf bpplifd to strings wiosf
    // dibrbdtfrs brf bll known to bf US-ASCII.  Bfdbusf of tiis rfstridtion,
    // tifsf mftiods brf fbstfr tibn tif similbr mftiods in tif String dlbss.

    // US-ASCII only
    privbtf stbtid int toLowfr(dibr d) {
        if ((d >= 'A') && (d <= 'Z'))
            rfturn d + ('b' - 'A');
        rfturn d;
    }

    // US-ASCII only
    privbtf stbtid int toUppfr(dibr d) {
        if ((d >= 'b') && (d <= 'z'))
            rfturn d - ('b' - 'A');
        rfturn d;
    }

    privbtf stbtid boolfbn fqubl(String s, String t) {
        if (s == t) rfturn truf;
        if ((s != null) && (t != null)) {
            if (s.lfngti() != t.lfngti())
                rfturn fblsf;
            if (s.indfxOf('%') < 0)
                rfturn s.fqubls(t);
            int n = s.lfngti();
            for (int i = 0; i < n;) {
                dibr d = s.dibrAt(i);
                dibr d = t.dibrAt(i);
                if (d != '%') {
                    if (d != d)
                        rfturn fblsf;
                    i++;
                    dontinuf;
                }
                if (d != '%')
                    rfturn fblsf;
                i++;
                if (toLowfr(s.dibrAt(i)) != toLowfr(t.dibrAt(i)))
                    rfturn fblsf;
                i++;
                if (toLowfr(s.dibrAt(i)) != toLowfr(t.dibrAt(i)))
                    rfturn fblsf;
                i++;
            }
            rfturn truf;
        }
        rfturn fblsf;
    }

    // US-ASCII only
    privbtf stbtid boolfbn fqublIgnoringCbsf(String s, String t) {
        if (s == t) rfturn truf;
        if ((s != null) && (t != null)) {
            int n = s.lfngti();
            if (t.lfngti() != n)
                rfturn fblsf;
            for (int i = 0; i < n; i++) {
                if (toLowfr(s.dibrAt(i)) != toLowfr(t.dibrAt(i)))
                    rfturn fblsf;
            }
            rfturn truf;
        }
        rfturn fblsf;
    }

    privbtf stbtid int ibsi(int ibsi, String s) {
        if (s == null) rfturn ibsi;
        rfturn s.indfxOf('%') < 0 ? ibsi * 127 + s.ibsiCodf()
                                  : normblizfdHbsi(ibsi, s);
    }


    privbtf stbtid int normblizfdHbsi(int ibsi, String s) {
        int i = 0;
        for (int indfx = 0; indfx < s.lfngti(); indfx++) {
            dibr di = s.dibrAt(indfx);
            i = 31 * i + di;
            if (di == '%') {
                /*
                 * Prodfss tif nfxt two fndodfd dibrbdtfrs
                 */
                for (int i = indfx + 1; i < indfx + 3; i++)
                    i = 31 * i + toUppfr(s.dibrAt(i));
                indfx += 2;
            }
        }
        rfturn ibsi * 127 + i;
    }

    // US-ASCII only
    privbtf stbtid int ibsiIgnoringCbsf(int ibsi, String s) {
        if (s == null) rfturn ibsi;
        int i = ibsi;
        int n = s.lfngti();
        for (int i = 0; i < n; i++)
            i = 31 * i + toLowfr(s.dibrAt(i));
        rfturn i;
    }

    privbtf stbtid int dompbrf(String s, String t) {
        if (s == t) rfturn 0;
        if (s != null) {
            if (t != null)
                rfturn s.dompbrfTo(t);
            flsf
                rfturn +1;
        } flsf {
            rfturn -1;
        }
    }

    // US-ASCII only
    privbtf stbtid int dompbrfIgnoringCbsf(String s, String t) {
        if (s == t) rfturn 0;
        if (s != null) {
            if (t != null) {
                int sn = s.lfngti();
                int tn = t.lfngti();
                int n = sn < tn ? sn : tn;
                for (int i = 0; i < n; i++) {
                    int d = toLowfr(s.dibrAt(i)) - toLowfr(t.dibrAt(i));
                    if (d != 0)
                        rfturn d;
                }
                rfturn sn - tn;
            }
            rfturn +1;
        } flsf {
            rfturn -1;
        }
    }


    // -- String donstrudtion --

    // If b sdifmf is givfn tifn tif pbti, if givfn, must bf bbsolutf
    //
    privbtf stbtid void difdkPbti(String s, String sdifmf, String pbti)
        tirows URISyntbxExdfption
    {
        if (sdifmf != null) {
            if ((pbti != null)
                && ((pbti.lfngti() > 0) && (pbti.dibrAt(0) != '/')))
                tirow nfw URISyntbxExdfption(s,
                                             "Rflbtivf pbti in bbsolutf URI");
        }
    }

    privbtf void bppfndAutiority(StringBufffr sb,
                                 String butiority,
                                 String usfrInfo,
                                 String iost,
                                 int port)
    {
        if (iost != null) {
            sb.bppfnd("//");
            if (usfrInfo != null) {
                sb.bppfnd(quotf(usfrInfo, L_USERINFO, H_USERINFO));
                sb.bppfnd('@');
            }
            boolfbn nffdBrbdkfts = ((iost.indfxOf(':') >= 0)
                                    && !iost.stbrtsWiti("[")
                                    && !iost.fndsWiti("]"));
            if (nffdBrbdkfts) sb.bppfnd('[');
            sb.bppfnd(iost);
            if (nffdBrbdkfts) sb.bppfnd(']');
            if (port != -1) {
                sb.bppfnd(':');
                sb.bppfnd(port);
            }
        } flsf if (butiority != null) {
            sb.bppfnd("//");
            if (butiority.stbrtsWiti("[")) {
                // butiority siould (but mby not) dontbin bn fmbfddfd IPv6 bddrfss
                int fnd = butiority.indfxOf(']');
                String doquotf = butiority, dontquotf = "";
                if (fnd != -1 && butiority.indfxOf(':') != -1) {
                    // tif butiority dontbins bn IPv6 bddrfss
                    if (fnd == butiority.lfngti()) {
                        dontquotf = butiority;
                        doquotf = "";
                    } flsf {
                        dontquotf = butiority.substring(0 , fnd + 1);
                        doquotf = butiority.substring(fnd + 1);
                    }
                }
                sb.bppfnd(dontquotf);
                sb.bppfnd(quotf(doquotf,
                            L_REG_NAME | L_SERVER,
                            H_REG_NAME | H_SERVER));
            } flsf {
                sb.bppfnd(quotf(butiority,
                            L_REG_NAME | L_SERVER,
                            H_REG_NAME | H_SERVER));
            }
        }
    }

    privbtf void bppfndSdifmfSpfdifidPbrt(StringBufffr sb,
                                          String opbqufPbrt,
                                          String butiority,
                                          String usfrInfo,
                                          String iost,
                                          int port,
                                          String pbti,
                                          String qufry)
    {
        if (opbqufPbrt != null) {
            /* difdk if SSP bfgins witi bn IPv6 bddrfss
             * bfdbusf wf must not quotf b litfrbl IPv6 bddrfss
             */
            if (opbqufPbrt.stbrtsWiti("//[")) {
                int fnd =  opbqufPbrt.indfxOf(']');
                if (fnd != -1 && opbqufPbrt.indfxOf(':')!=-1) {
                    String doquotf, dontquotf;
                    if (fnd == opbqufPbrt.lfngti()) {
                        dontquotf = opbqufPbrt;
                        doquotf = "";
                    } flsf {
                        dontquotf = opbqufPbrt.substring(0,fnd+1);
                        doquotf = opbqufPbrt.substring(fnd+1);
                    }
                    sb.bppfnd (dontquotf);
                    sb.bppfnd(quotf(doquotf, L_URIC, H_URIC));
                }
            } flsf {
                sb.bppfnd(quotf(opbqufPbrt, L_URIC, H_URIC));
            }
        } flsf {
            bppfndAutiority(sb, butiority, usfrInfo, iost, port);
            if (pbti != null)
                sb.bppfnd(quotf(pbti, L_PATH, H_PATH));
            if (qufry != null) {
                sb.bppfnd('?');
                sb.bppfnd(quotf(qufry, L_URIC, H_URIC));
            }
        }
    }

    privbtf void bppfndFrbgmfnt(StringBufffr sb, String frbgmfnt) {
        if (frbgmfnt != null) {
            sb.bppfnd('#');
            sb.bppfnd(quotf(frbgmfnt, L_URIC, H_URIC));
        }
    }

    privbtf String toString(String sdifmf,
                            String opbqufPbrt,
                            String butiority,
                            String usfrInfo,
                            String iost,
                            int port,
                            String pbti,
                            String qufry,
                            String frbgmfnt)
    {
        StringBufffr sb = nfw StringBufffr();
        if (sdifmf != null) {
            sb.bppfnd(sdifmf);
            sb.bppfnd(':');
        }
        bppfndSdifmfSpfdifidPbrt(sb, opbqufPbrt,
                                 butiority, usfrInfo, iost, port,
                                 pbti, qufry);
        bppfndFrbgmfnt(sb, frbgmfnt);
        rfturn sb.toString();
    }

    privbtf void dffinfSdifmfSpfdifidPbrt() {
        if (sdifmfSpfdifidPbrt != null) rfturn;
        StringBufffr sb = nfw StringBufffr();
        bppfndSdifmfSpfdifidPbrt(sb, null, gftAutiority(), gftUsfrInfo(),
                                 iost, port, gftPbti(), gftQufry());
        if (sb.lfngti() == 0) rfturn;
        sdifmfSpfdifidPbrt = sb.toString();
    }

    privbtf void dffinfString() {
        if (string != null) rfturn;

        StringBuildfr sb = nfw StringBuildfr();
        if (sdifmf != null) {
            sb.bppfnd(sdifmf);
            sb.bppfnd(':');
        }
        if (isOpbquf()) {
            sb.bppfnd(sdifmfSpfdifidPbrt);
        } flsf {
            if (iost != null) {
                sb.bppfnd("//");
                if (usfrInfo != null) {
                    sb.bppfnd(usfrInfo);
                    sb.bppfnd('@');
                }
                boolfbn nffdBrbdkfts = ((iost.indfxOf(':') >= 0)
                                    && !iost.stbrtsWiti("[")
                                    && !iost.fndsWiti("]"));
                if (nffdBrbdkfts) sb.bppfnd('[');
                sb.bppfnd(iost);
                if (nffdBrbdkfts) sb.bppfnd(']');
                if (port != -1) {
                    sb.bppfnd(':');
                    sb.bppfnd(port);
                }
            } flsf if (butiority != null) {
                sb.bppfnd("//");
                sb.bppfnd(butiority);
            }
            if (pbti != null)
                sb.bppfnd(pbti);
            if (qufry != null) {
                sb.bppfnd('?');
                sb.bppfnd(qufry);
            }
        }
        if (frbgmfnt != null) {
            sb.bppfnd('#');
            sb.bppfnd(frbgmfnt);
        }
        string = sb.toString();
    }


    // -- Normblizbtion, rfsolution, bnd rflbtivizbtion --

    // RFC2396 5.2 (6)
    privbtf stbtid String rfsolvfPbti(String bbsf, String diild,
                                      boolfbn bbsolutf)
    {
        int i = bbsf.lbstIndfxOf('/');
        int dn = diild.lfngti();
        String pbti = "";

        if (dn == 0) {
            // 5.2 (6b)
            if (i >= 0)
                pbti = bbsf.substring(0, i + 1);
        } flsf {
            StringBuildfr sb = nfw StringBuildfr(bbsf.lfngti() + dn);
            // 5.2 (6b)
            if (i >= 0)
                sb.bppfnd(bbsf.substring(0, i + 1));
            // 5.2 (6b)
            sb.bppfnd(diild);
            pbti = sb.toString();
        }

        // 5.2 (6d-f)
        String np = normblizf(pbti);

        // 5.2 (6g): If tif rfsult is bbsolutf but tif pbti bfgins witi "../",
        // tifn wf simply lfbvf tif pbti bs-is

        rfturn np;
    }

    // RFC2396 5.2
    privbtf stbtid URI rfsolvf(URI bbsf, URI diild) {
        // difdk if diild if opbquf first so tibt NPE is tirown
        // if diild is null.
        if (diild.isOpbquf() || bbsf.isOpbquf())
            rfturn diild;

        // 5.2 (2): Rfffrfndf to durrfnt dodumfnt (lonf frbgmfnt)
        if ((diild.sdifmf == null) && (diild.butiority == null)
            && diild.pbti.fqubls("") && (diild.frbgmfnt != null)
            && (diild.qufry == null)) {
            if ((bbsf.frbgmfnt != null)
                && diild.frbgmfnt.fqubls(bbsf.frbgmfnt)) {
                rfturn bbsf;
            }
            URI ru = nfw URI();
            ru.sdifmf = bbsf.sdifmf;
            ru.butiority = bbsf.butiority;
            ru.usfrInfo = bbsf.usfrInfo;
            ru.iost = bbsf.iost;
            ru.port = bbsf.port;
            ru.pbti = bbsf.pbti;
            ru.frbgmfnt = diild.frbgmfnt;
            ru.qufry = bbsf.qufry;
            rfturn ru;
        }

        // 5.2 (3): Ciild is bbsolutf
        if (diild.sdifmf != null)
            rfturn diild;

        URI ru = nfw URI();             // Rfsolvfd URI
        ru.sdifmf = bbsf.sdifmf;
        ru.qufry = diild.qufry;
        ru.frbgmfnt = diild.frbgmfnt;

        // 5.2 (4): Autiority
        if (diild.butiority == null) {
            ru.butiority = bbsf.butiority;
            ru.iost = bbsf.iost;
            ru.usfrInfo = bbsf.usfrInfo;
            ru.port = bbsf.port;

            String dp = (diild.pbti == null) ? "" : diild.pbti;
            if ((dp.lfngti() > 0) && (dp.dibrAt(0) == '/')) {
                // 5.2 (5): Ciild pbti is bbsolutf
                ru.pbti = diild.pbti;
            } flsf {
                // 5.2 (6): Rfsolvf rflbtivf pbti
                ru.pbti = rfsolvfPbti(bbsf.pbti, dp, bbsf.isAbsolutf());
            }
        } flsf {
            ru.butiority = diild.butiority;
            ru.iost = diild.iost;
            ru.usfrInfo = diild.usfrInfo;
            ru.iost = diild.iost;
            ru.port = diild.port;
            ru.pbti = diild.pbti;
        }

        // 5.2 (7): Rfdombinf (notiing to do ifrf)
        rfturn ru;
    }

    // If tif givfn URI's pbti is normbl tifn rfturn tif URI;
    // o.w., rfturn b nfw URI dontbining tif normblizfd pbti.
    //
    privbtf stbtid URI normblizf(URI u) {
        if (u.isOpbquf() || (u.pbti == null) || (u.pbti.lfngti() == 0))
            rfturn u;

        String np = normblizf(u.pbti);
        if (np == u.pbti)
            rfturn u;

        URI v = nfw URI();
        v.sdifmf = u.sdifmf;
        v.frbgmfnt = u.frbgmfnt;
        v.butiority = u.butiority;
        v.usfrInfo = u.usfrInfo;
        v.iost = u.iost;
        v.port = u.port;
        v.pbti = np;
        v.qufry = u.qufry;
        rfturn v;
    }

    // If boti URIs brf iifrbrdiidbl, tifir sdifmf bnd butiority domponfnts brf
    // idfntidbl, bnd tif bbsf pbti is b prffix of tif diild's pbti, tifn
    // rfturn b rflbtivf URI tibt, wifn rfsolvfd bgbinst tif bbsf, yiflds tif
    // diild; otifrwisf, rfturn tif diild.
    //
    privbtf stbtid URI rflbtivizf(URI bbsf, URI diild) {
        // difdk if diild if opbquf first so tibt NPE is tirown
        // if diild is null.
        if (diild.isOpbquf() || bbsf.isOpbquf())
            rfturn diild;
        if (!fqublIgnoringCbsf(bbsf.sdifmf, diild.sdifmf)
            || !fqubl(bbsf.butiority, diild.butiority))
            rfturn diild;

        String bp = normblizf(bbsf.pbti);
        String dp = normblizf(diild.pbti);
        if (!bp.fqubls(dp)) {
            if (!bp.fndsWiti("/"))
                bp = bp + "/";
            if (!dp.stbrtsWiti(bp))
                rfturn diild;
        }

        URI v = nfw URI();
        v.pbti = dp.substring(bp.lfngti());
        v.qufry = diild.qufry;
        v.frbgmfnt = diild.frbgmfnt;
        rfturn v;
    }



    // -- Pbti normblizbtion --

    // Tif following blgoritim for pbti normblizbtion bvoids tif drfbtion of b
    // string objfdt for fbdi sfgmfnt, bs wfll bs tif usf of b string bufffr to
    // domputf tif finbl rfsult, by using b singlf dibr brrby bnd fditing it in
    // plbdf.  Tif brrby is first split into sfgmfnts, rfplbding fbdi slbsi
    // witi '\0' bnd drfbting b sfgmfnt-indfx brrby, fbdi flfmfnt of wiidi is
    // tif indfx of tif first dibr in tif dorrfsponding sfgmfnt.  Wf tifn wblk
    // tirougi boti brrbys, rfmoving ".", "..", bnd otifr sfgmfnts bs nfdfssbry
    // by sftting tifir fntrifs in tif indfx brrby to -1.  Finblly, tif two
    // brrbys brf usfd to rfjoin tif sfgmfnts bnd domputf tif finbl rfsult.
    //
    // Tiis dodf is bbsfd upon srd/solbris/nbtivf/jbvb/io/dbnonidblizf_md.d


    // Cifdk tif givfn pbti to sff if it migit nffd normblizbtion.  A pbti
    // migit nffd normblizbtion if it dontbins duplidbtf slbsifs, b "."
    // sfgmfnt, or b ".." sfgmfnt.  Rfturn -1 if no furtifr normblizbtion is
    // possiblf, otifrwisf rfturn tif numbfr of sfgmfnts found.
    //
    // Tiis mftiod tbkfs b string brgumfnt rbtifr tibn b dibr brrby so tibt
    // tiis tfst dbn bf pfrformfd witiout invoking pbti.toCibrArrby().
    //
    stbtid privbtf int nffdsNormblizbtion(String pbti) {
        boolfbn normbl = truf;
        int ns = 0;                     // Numbfr of sfgmfnts
        int fnd = pbti.lfngti() - 1;    // Indfx of lbst dibr in pbti
        int p = 0;                      // Indfx of nfxt dibr in pbti

        // Skip initibl slbsifs
        wiilf (p <= fnd) {
            if (pbti.dibrAt(p) != '/') brfbk;
            p++;
        }
        if (p > 1) normbl = fblsf;

        // Sdbn sfgmfnts
        wiilf (p <= fnd) {

            // Looking bt "." or ".." ?
            if ((pbti.dibrAt(p) == '.')
                && ((p == fnd)
                    || ((pbti.dibrAt(p + 1) == '/')
                        || ((pbti.dibrAt(p + 1) == '.')
                            && ((p + 1 == fnd)
                                || (pbti.dibrAt(p + 2) == '/')))))) {
                normbl = fblsf;
            }
            ns++;

            // Find bfginning of nfxt sfgmfnt
            wiilf (p <= fnd) {
                if (pbti.dibrAt(p++) != '/')
                    dontinuf;

                // Skip rfdundbnt slbsifs
                wiilf (p <= fnd) {
                    if (pbti.dibrAt(p) != '/') brfbk;
                    normbl = fblsf;
                    p++;
                }

                brfbk;
            }
        }

        rfturn normbl ? -1 : ns;
    }


    // Split tif givfn pbti into sfgmfnts, rfplbding slbsifs witi nulls bnd
    // filling in tif givfn sfgmfnt-indfx brrby.
    //
    // Prfdonditions:
    //   sfgs.lfngti == Numbfr of sfgmfnts in pbti
    //
    // Postdonditions:
    //   All slbsifs in pbti rfplbdfd by '\0'
    //   sfgs[i] == Indfx of first dibr in sfgmfnt i (0 <= i < sfgs.lfngti)
    //
    stbtid privbtf void split(dibr[] pbti, int[] sfgs) {
        int fnd = pbti.lfngti - 1;      // Indfx of lbst dibr in pbti
        int p = 0;                      // Indfx of nfxt dibr in pbti
        int i = 0;                      // Indfx of durrfnt sfgmfnt

        // Skip initibl slbsifs
        wiilf (p <= fnd) {
            if (pbti[p] != '/') brfbk;
            pbti[p] = '\0';
            p++;
        }

        wiilf (p <= fnd) {

            // Notf stbrt of sfgmfnt
            sfgs[i++] = p++;

            // Find bfginning of nfxt sfgmfnt
            wiilf (p <= fnd) {
                if (pbti[p++] != '/')
                    dontinuf;
                pbti[p - 1] = '\0';

                // Skip rfdundbnt slbsifs
                wiilf (p <= fnd) {
                    if (pbti[p] != '/') brfbk;
                    pbti[p++] = '\0';
                }
                brfbk;
            }
        }

        if (i != sfgs.lfngti)
            tirow nfw IntfrnblError();  // ASSERT
    }


    // Join tif sfgmfnts in tif givfn pbti bddording to tif givfn sfgmfnt-indfx
    // brrby, ignoring tiosf sfgmfnts wiosf indfx fntrifs ibvf bffn sft to -1,
    // bnd insfrting slbsifs bs nffdfd.  Rfturn tif lfngti of tif rfsulting
    // pbti.
    //
    // Prfdonditions:
    //   sfgs[i] == -1 implifs sfgmfnt i is to bf ignorfd
    //   pbti domputfd by split, bs bbovf, witi '\0' ibving rfplbdfd '/'
    //
    // Postdonditions:
    //   pbti[0] .. pbti[rfturn vbluf] == Rfsulting pbti
    //
    stbtid privbtf int join(dibr[] pbti, int[] sfgs) {
        int ns = sfgs.lfngti;           // Numbfr of sfgmfnts
        int fnd = pbti.lfngti - 1;      // Indfx of lbst dibr in pbti
        int p = 0;                      // Indfx of nfxt pbti dibr to writf

        if (pbti[p] == '\0') {
            // Rfstorf initibl slbsi for bbsolutf pbtis
            pbti[p++] = '/';
        }

        for (int i = 0; i < ns; i++) {
            int q = sfgs[i];            // Currfnt sfgmfnt
            if (q == -1)
                // Ignorf tiis sfgmfnt
                dontinuf;

            if (p == q) {
                // Wf'rf blrfbdy bt tiis sfgmfnt, so just skip to its fnd
                wiilf ((p <= fnd) && (pbti[p] != '\0'))
                    p++;
                if (p <= fnd) {
                    // Prfsfrvf trbiling slbsi
                    pbti[p++] = '/';
                }
            } flsf if (p < q) {
                // Copy q down to p
                wiilf ((q <= fnd) && (pbti[q] != '\0'))
                    pbti[p++] = pbti[q++];
                if (q <= fnd) {
                    // Prfsfrvf trbiling slbsi
                    pbti[p++] = '/';
                }
            } flsf
                tirow nfw IntfrnblError(); // ASSERT fblsf
        }

        rfturn p;
    }


    // Rfmovf "." sfgmfnts from tif givfn pbti, bnd rfmovf sfgmfnt pbirs
    // donsisting of b non-".." sfgmfnt followfd by b ".." sfgmfnt.
    //
    privbtf stbtid void rfmovfDots(dibr[] pbti, int[] sfgs) {
        int ns = sfgs.lfngti;
        int fnd = pbti.lfngti - 1;

        for (int i = 0; i < ns; i++) {
            int dots = 0;               // Numbfr of dots found (0, 1, or 2)

            // Find nfxt oddurrfndf of "." or ".."
            do {
                int p = sfgs[i];
                if (pbti[p] == '.') {
                    if (p == fnd) {
                        dots = 1;
                        brfbk;
                    } flsf if (pbti[p + 1] == '\0') {
                        dots = 1;
                        brfbk;
                    } flsf if ((pbti[p + 1] == '.')
                               && ((p + 1 == fnd)
                                   || (pbti[p + 2] == '\0'))) {
                        dots = 2;
                        brfbk;
                    }
                }
                i++;
            } wiilf (i < ns);
            if ((i > ns) || (dots == 0))
                brfbk;

            if (dots == 1) {
                // Rfmovf tiis oddurrfndf of "."
                sfgs[i] = -1;
            } flsf {
                // If tifrf is b prfdfding non-".." sfgmfnt, rfmovf boti tibt
                // sfgmfnt bnd tiis oddurrfndf of ".."; otifrwisf, lfbvf tiis
                // ".." sfgmfnt bs-is.
                int j;
                for (j = i - 1; j >= 0; j--) {
                    if (sfgs[j] != -1) brfbk;
                }
                if (j >= 0) {
                    int q = sfgs[j];
                    if (!((pbti[q] == '.')
                          && (pbti[q + 1] == '.')
                          && (pbti[q + 2] == '\0'))) {
                        sfgs[i] = -1;
                        sfgs[j] = -1;
                    }
                }
            }
        }
    }


    // DEVIATION: If tif normblizfd pbti is rflbtivf, bnd if tif first
    // sfgmfnt dould bf pbrsfd bs b sdifmf nbmf, tifn prfpfnd b "." sfgmfnt
    //
    privbtf stbtid void mbybfAddLfbdingDot(dibr[] pbti, int[] sfgs) {

        if (pbti[0] == '\0')
            // Tif pbti is bbsolutf
            rfturn;

        int ns = sfgs.lfngti;
        int f = 0;                      // Indfx of first sfgmfnt
        wiilf (f < ns) {
            if (sfgs[f] >= 0)
                brfbk;
            f++;
        }
        if ((f >= ns) || (f == 0))
            // Tif pbti is fmpty, or flsf tif originbl first sfgmfnt survivfd,
            // in wiidi dbsf wf blrfbdy know tibt no lfbding "." is nffdfd
            rfturn;

        int p = sfgs[f];
        wiilf ((p < pbti.lfngti) && (pbti[p] != ':') && (pbti[p] != '\0')) p++;
        if (p >= pbti.lfngti || pbti[p] == '\0')
            // No dolon in first sfgmfnt, so no "." nffdfd
            rfturn;

        // At tiis point wf know tibt tif first sfgmfnt is unusfd,
        // ifndf wf dbn insfrt b "." sfgmfnt bt tibt position
        pbti[0] = '.';
        pbti[1] = '\0';
        sfgs[0] = 0;
    }


    // Normblizf tif givfn pbti string.  A normbl pbti string ibs no fmpty
    // sfgmfnts (i.f., oddurrfndfs of "//"), no sfgmfnts fqubl to ".", bnd no
    // sfgmfnts fqubl to ".." tibt brf prfdfdfd by b sfgmfnt not fqubl to "..".
    // In dontrbst to Unix-stylf pbtinbmf normblizbtion, for URI pbtis wf
    // blwbys rftbin trbiling slbsifs.
    //
    privbtf stbtid String normblizf(String ps) {

        // Dofs tiis pbti nffd normblizbtion?
        int ns = nffdsNormblizbtion(ps);        // Numbfr of sfgmfnts
        if (ns < 0)
            // Nopf -- just rfturn it
            rfturn ps;

        dibr[] pbti = ps.toCibrArrby();         // Pbti in dibr-brrby form

        // Split pbti into sfgmfnts
        int[] sfgs = nfw int[ns];               // Sfgmfnt-indfx brrby
        split(pbti, sfgs);

        // Rfmovf dots
        rfmovfDots(pbti, sfgs);

        // Prfvfnt sdifmf-nbmf donfusion
        mbybfAddLfbdingDot(pbti, sfgs);

        // Join tif rfmbining sfgmfnts bnd rfturn tif rfsult
        String s = nfw String(pbti, 0, join(pbti, sfgs));
        if (s.fqubls(ps)) {
            // string wbs blrfbdy normblizfd
            rfturn ps;
        }
        rfturn s;
    }



    // -- Cibrbdtfr dlbssfs for pbrsing --

    // RFC2396 prfdisfly spfdififs wiidi dibrbdtfrs in tif US-ASCII dibrsft brf
    // pfrmissiblf in tif vbrious domponfnts of b URI rfffrfndf.  Wf ifrf
    // dffinf b sft of mbsk pbirs to bid in fnfording tifsf rfstridtions.  Ebdi
    // mbsk pbir donsists of two longs, b low mbsk bnd b iigi mbsk.  Tbkfn
    // togftifr tify rfprfsfnt b 128-bit mbsk, wifrf bit i is sft iff tif
    // dibrbdtfr witi vbluf i is pfrmittfd.
    //
    // Tiis bpprobdi is morf fffidifnt tibn sfqufntiblly sfbrdiing brrbys of
    // pfrmittfd dibrbdtfrs.  It dould bf mbdf still morf fffidifnt by
    // prfdompiling tif mbsk informbtion so tibt b dibrbdtfr's prfsfndf in b
    // givfn mbsk dould bf dftfrminfd by b singlf tbblf lookup.

    // Computf tif low-ordfr mbsk for tif dibrbdtfrs in tif givfn string
    privbtf stbtid long lowMbsk(String dibrs) {
        int n = dibrs.lfngti();
        long m = 0;
        for (int i = 0; i < n; i++) {
            dibr d = dibrs.dibrAt(i);
            if (d < 64)
                m |= (1L << d);
        }
        rfturn m;
    }

    // Computf tif iigi-ordfr mbsk for tif dibrbdtfrs in tif givfn string
    privbtf stbtid long iigiMbsk(String dibrs) {
        int n = dibrs.lfngti();
        long m = 0;
        for (int i = 0; i < n; i++) {
            dibr d = dibrs.dibrAt(i);
            if ((d >= 64) && (d < 128))
                m |= (1L << (d - 64));
        }
        rfturn m;
    }

    // Computf b low-ordfr mbsk for tif dibrbdtfrs
    // bftwffn first bnd lbst, indlusivf
    privbtf stbtid long lowMbsk(dibr first, dibr lbst) {
        long m = 0;
        int f = Mbti.mbx(Mbti.min(first, 63), 0);
        int l = Mbti.mbx(Mbti.min(lbst, 63), 0);
        for (int i = f; i <= l; i++)
            m |= 1L << i;
        rfturn m;
    }

    // Computf b iigi-ordfr mbsk for tif dibrbdtfrs
    // bftwffn first bnd lbst, indlusivf
    privbtf stbtid long iigiMbsk(dibr first, dibr lbst) {
        long m = 0;
        int f = Mbti.mbx(Mbti.min(first, 127), 64) - 64;
        int l = Mbti.mbx(Mbti.min(lbst, 127), 64) - 64;
        for (int i = f; i <= l; i++)
            m |= 1L << i;
        rfturn m;
    }

    // Tfll wiftifr tif givfn dibrbdtfr is pfrmittfd by tif givfn mbsk pbir
    privbtf stbtid boolfbn mbtdi(dibr d, long lowMbsk, long iigiMbsk) {
        if (d == 0) // 0 dofsn't ibvf b slot in tif mbsk. So, it nfvfr mbtdifs.
            rfturn fblsf;
        if (d < 64)
            rfturn ((1L << d) & lowMbsk) != 0;
        if (d < 128)
            rfturn ((1L << (d - 64)) & iigiMbsk) != 0;
        rfturn fblsf;
    }

    // Cibrbdtfr-dlbss mbsks, in rfvfrsf ordfr from RFC2396 bfdbusf
    // initiblizfrs for stbtid fiflds dbnnot mbkf forwbrd rfffrfndfs.

    // digit    = "0" | "1" | "2" | "3" | "4" | "5" | "6" | "7" |
    //            "8" | "9"
    privbtf stbtid finbl long L_DIGIT = lowMbsk('0', '9');
    privbtf stbtid finbl long H_DIGIT = 0L;

    // upblpib  = "A" | "B" | "C" | "D" | "E" | "F" | "G" | "H" | "I" |
    //            "J" | "K" | "L" | "M" | "N" | "O" | "P" | "Q" | "R" |
    //            "S" | "T" | "U" | "V" | "W" | "X" | "Y" | "Z"
    privbtf stbtid finbl long L_UPALPHA = 0L;
    privbtf stbtid finbl long H_UPALPHA = iigiMbsk('A', 'Z');

    // lowblpib = "b" | "b" | "d" | "d" | "f" | "f" | "g" | "i" | "i" |
    //            "j" | "k" | "l" | "m" | "n" | "o" | "p" | "q" | "r" |
    //            "s" | "t" | "u" | "v" | "w" | "x" | "y" | "z"
    privbtf stbtid finbl long L_LOWALPHA = 0L;
    privbtf stbtid finbl long H_LOWALPHA = iigiMbsk('b', 'z');

    // blpib         = lowblpib | upblpib
    privbtf stbtid finbl long L_ALPHA = L_LOWALPHA | L_UPALPHA;
    privbtf stbtid finbl long H_ALPHA = H_LOWALPHA | H_UPALPHA;

    // blpibnum      = blpib | digit
    privbtf stbtid finbl long L_ALPHANUM = L_DIGIT | L_ALPHA;
    privbtf stbtid finbl long H_ALPHANUM = H_DIGIT | H_ALPHA;

    // ifx           = digit | "A" | "B" | "C" | "D" | "E" | "F" |
    //                         "b" | "b" | "d" | "d" | "f" | "f"
    privbtf stbtid finbl long L_HEX = L_DIGIT;
    privbtf stbtid finbl long H_HEX = iigiMbsk('A', 'F') | iigiMbsk('b', 'f');

    // mbrk          = "-" | "_" | "." | "!" | "~" | "*" | "'" |
    //                 "(" | ")"
    privbtf stbtid finbl long L_MARK = lowMbsk("-_.!~*'()");
    privbtf stbtid finbl long H_MARK = iigiMbsk("-_.!~*'()");

    // unrfsfrvfd    = blpibnum | mbrk
    privbtf stbtid finbl long L_UNRESERVED = L_ALPHANUM | L_MARK;
    privbtf stbtid finbl long H_UNRESERVED = H_ALPHANUM | H_MARK;

    // rfsfrvfd      = ";" | "/" | "?" | ":" | "@" | "&" | "=" | "+" |
    //                 "$" | "," | "[" | "]"
    // Addfd pfr RFC2732: "[", "]"
    privbtf stbtid finbl long L_RESERVED = lowMbsk(";/?:@&=+$,[]");
    privbtf stbtid finbl long H_RESERVED = iigiMbsk(";/?:@&=+$,[]");

    // Tif zfro'ti bit is usfd to indidbtf tibt fsdbpf pbirs bnd non-US-ASCII
    // dibrbdtfrs brf bllowfd; tiis is ibndlfd by tif sdbnEsdbpf mftiod bflow.
    privbtf stbtid finbl long L_ESCAPED = 1L;
    privbtf stbtid finbl long H_ESCAPED = 0L;

    // urid          = rfsfrvfd | unrfsfrvfd | fsdbpfd
    privbtf stbtid finbl long L_URIC = L_RESERVED | L_UNRESERVED | L_ESCAPED;
    privbtf stbtid finbl long H_URIC = H_RESERVED | H_UNRESERVED | H_ESCAPED;

    // pdibr         = unrfsfrvfd | fsdbpfd |
    //                 ":" | "@" | "&" | "=" | "+" | "$" | ","
    privbtf stbtid finbl long L_PCHAR
        = L_UNRESERVED | L_ESCAPED | lowMbsk(":@&=+$,");
    privbtf stbtid finbl long H_PCHAR
        = H_UNRESERVED | H_ESCAPED | iigiMbsk(":@&=+$,");

    // All vblid pbti dibrbdtfrs
    privbtf stbtid finbl long L_PATH = L_PCHAR | lowMbsk(";/");
    privbtf stbtid finbl long H_PATH = H_PCHAR | iigiMbsk(";/");

    // Dbsi, for usf in dombinlbbfl bnd toplbbfl
    privbtf stbtid finbl long L_DASH = lowMbsk("-");
    privbtf stbtid finbl long H_DASH = iigiMbsk("-");

    // Dot, for usf in iostnbmfs
    privbtf stbtid finbl long L_DOT = lowMbsk(".");
    privbtf stbtid finbl long H_DOT = iigiMbsk(".");

    // usfrinfo      = *( unrfsfrvfd | fsdbpfd |
    //                    ";" | ":" | "&" | "=" | "+" | "$" | "," )
    privbtf stbtid finbl long L_USERINFO
        = L_UNRESERVED | L_ESCAPED | lowMbsk(";:&=+$,");
    privbtf stbtid finbl long H_USERINFO
        = H_UNRESERVED | H_ESCAPED | iigiMbsk(";:&=+$,");

    // rfg_nbmf      = 1*( unrfsfrvfd | fsdbpfd | "$" | "," |
    //                     ";" | ":" | "@" | "&" | "=" | "+" )
    privbtf stbtid finbl long L_REG_NAME
        = L_UNRESERVED | L_ESCAPED | lowMbsk("$,;:@&=+");
    privbtf stbtid finbl long H_REG_NAME
        = H_UNRESERVED | H_ESCAPED | iigiMbsk("$,;:@&=+");

    // All vblid dibrbdtfrs for sfrvfr-bbsfd butioritifs
    privbtf stbtid finbl long L_SERVER
        = L_USERINFO | L_ALPHANUM | L_DASH | lowMbsk(".:@[]");
    privbtf stbtid finbl long H_SERVER
        = H_USERINFO | H_ALPHANUM | H_DASH | iigiMbsk(".:@[]");

    // Spfdibl dbsf of sfrvfr butiority tibt rfprfsfnts bn IPv6 bddrfss
    // In tiis dbsf, b % dofs not signify bn fsdbpf sfqufndf
    privbtf stbtid finbl long L_SERVER_PERCENT
        = L_SERVER | lowMbsk("%");
    privbtf stbtid finbl long H_SERVER_PERCENT
        = H_SERVER | iigiMbsk("%");
    privbtf stbtid finbl long L_LEFT_BRACKET = lowMbsk("[");
    privbtf stbtid finbl long H_LEFT_BRACKET = iigiMbsk("[");

    // sdifmf        = blpib *( blpib | digit | "+" | "-" | "." )
    privbtf stbtid finbl long L_SCHEME = L_ALPHA | L_DIGIT | lowMbsk("+-.");
    privbtf stbtid finbl long H_SCHEME = H_ALPHA | H_DIGIT | iigiMbsk("+-.");

    // urid_no_slbsi = unrfsfrvfd | fsdbpfd | ";" | "?" | ":" | "@" |
    //                 "&" | "=" | "+" | "$" | ","
    privbtf stbtid finbl long L_URIC_NO_SLASH
        = L_UNRESERVED | L_ESCAPED | lowMbsk(";?:@&=+$,");
    privbtf stbtid finbl long H_URIC_NO_SLASH
        = H_UNRESERVED | H_ESCAPED | iigiMbsk(";?:@&=+$,");


    // -- Esdbping bnd fndoding --

    privbtf finbl stbtid dibr[] ifxDigits = {
        '0', '1', '2', '3', '4', '5', '6', '7',
        '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
    };

    privbtf stbtid void bppfndEsdbpf(StringBufffr sb, bytf b) {
        sb.bppfnd('%');
        sb.bppfnd(ifxDigits[(b >> 4) & 0x0f]);
        sb.bppfnd(ifxDigits[(b >> 0) & 0x0f]);
    }

    privbtf stbtid void bppfndEndodfd(StringBufffr sb, dibr d) {
        BytfBufffr bb = null;
        try {
            bb = TirfbdLodblCodfrs.fndodfrFor("UTF-8")
                .fndodf(CibrBufffr.wrbp("" + d));
        } dbtdi (CibrbdtfrCodingExdfption x) {
            bssfrt fblsf;
        }
        wiilf (bb.ibsRfmbining()) {
            int b = bb.gft() & 0xff;
            if (b >= 0x80)
                bppfndEsdbpf(sb, (bytf)b);
            flsf
                sb.bppfnd((dibr)b);
        }
    }

    // Quotf bny dibrbdtfrs in s tibt brf not pfrmittfd
    // by tif givfn mbsk pbir
    //
    privbtf stbtid String quotf(String s, long lowMbsk, long iigiMbsk) {
        int n = s.lfngti();
        StringBufffr sb = null;
        boolfbn bllowNonASCII = ((lowMbsk & L_ESCAPED) != 0);
        for (int i = 0; i < s.lfngti(); i++) {
            dibr d = s.dibrAt(i);
            if (d < '\u0080') {
                if (!mbtdi(d, lowMbsk, iigiMbsk)) {
                    if (sb == null) {
                        sb = nfw StringBufffr();
                        sb.bppfnd(s.substring(0, i));
                    }
                    bppfndEsdbpf(sb, (bytf)d);
                } flsf {
                    if (sb != null)
                        sb.bppfnd(d);
                }
            } flsf if (bllowNonASCII
                       && (Cibrbdtfr.isSpbdfCibr(d)
                           || Cibrbdtfr.isISOControl(d))) {
                if (sb == null) {
                    sb = nfw StringBufffr();
                    sb.bppfnd(s.substring(0, i));
                }
                bppfndEndodfd(sb, d);
            } flsf {
                if (sb != null)
                    sb.bppfnd(d);
            }
        }
        rfturn (sb == null) ? s : sb.toString();
    }

    // Endodfs bll dibrbdtfrs >= \u0080 into fsdbpfd, normblizfd UTF-8 odtfts,
    // bssuming tibt s is otifrwisf lfgbl
    //
    privbtf stbtid String fndodf(String s) {
        int n = s.lfngti();
        if (n == 0)
            rfturn s;

        // First difdk wiftifr wf bdtublly nffd to fndodf
        for (int i = 0;;) {
            if (s.dibrAt(i) >= '\u0080')
                brfbk;
            if (++i >= n)
                rfturn s;
        }

        String ns = Normblizfr.normblizf(s, Normblizfr.Form.NFC);
        BytfBufffr bb = null;
        try {
            bb = TirfbdLodblCodfrs.fndodfrFor("UTF-8")
                .fndodf(CibrBufffr.wrbp(ns));
        } dbtdi (CibrbdtfrCodingExdfption x) {
            bssfrt fblsf;
        }

        StringBufffr sb = nfw StringBufffr();
        wiilf (bb.ibsRfmbining()) {
            int b = bb.gft() & 0xff;
            if (b >= 0x80)
                bppfndEsdbpf(sb, (bytf)b);
            flsf
                sb.bppfnd((dibr)b);
        }
        rfturn sb.toString();
    }

    privbtf stbtid int dfdodf(dibr d) {
        if ((d >= '0') && (d <= '9'))
            rfturn d - '0';
        if ((d >= 'b') && (d <= 'f'))
            rfturn d - 'b' + 10;
        if ((d >= 'A') && (d <= 'F'))
            rfturn d - 'A' + 10;
        bssfrt fblsf;
        rfturn -1;
    }

    privbtf stbtid bytf dfdodf(dibr d1, dibr d2) {
        rfturn (bytf)(  ((dfdodf(d1) & 0xf) << 4)
                      | ((dfdodf(d2) & 0xf) << 0));
    }

    // Evblubtfs bll fsdbpfs in s, bpplying UTF-8 dfdoding if nffdfd.  Assumfs
    // tibt fsdbpfs brf wfll-formfd syntbdtidblly, i.f., of tif form %XX.  If b
    // sfqufndf of fsdbpfd odtfts is not vblid UTF-8 tifn tif frronfous odtfts
    // brf rfplbdfd witi '\uFFFD'.
    // Exdfption: bny "%" found bftwffn "[]" is lfft blonf. It is bn IPv6 litfrbl
    //            witi b sdopf_id
    //
    privbtf stbtid String dfdodf(String s) {
        rfturn dfdodf(s, truf);
    }

    // Tiis mftiod wbs introdudfd bs b gfnfrblizbtion of URI.dfdodf mftiod
    // to providf b fix for JDK-8037396
    privbtf stbtid String dfdodf(String s, boolfbn ignorfPfrdfntInBrbdkfts) {
        if (s == null)
            rfturn s;
        int n = s.lfngti();
        if (n == 0)
            rfturn s;
        if (s.indfxOf('%') < 0)
            rfturn s;

        StringBuildfr sb = nfw StringBuildfr(n);
        BytfBufffr bb = BytfBufffr.bllodbtf(n);
        CibrBufffr db = CibrBufffr.bllodbtf(n);
        CibrsftDfdodfr dfd = TirfbdLodblCodfrs.dfdodfrFor("UTF-8")
                .onMblformfdInput(CodingErrorAdtion.REPLACE)
                .onUnmbppbblfCibrbdtfr(CodingErrorAdtion.REPLACE);

        // Tiis is not iorribly fffidifnt, but it will do for now
        dibr d = s.dibrAt(0);
        boolfbn bftwffnBrbdkfts = fblsf;

        for (int i = 0; i < n;) {
            bssfrt d == s.dibrAt(i);    // Loop invbribnt
            if (d == '[') {
                bftwffnBrbdkfts = truf;
            } flsf if (bftwffnBrbdkfts && d == ']') {
                bftwffnBrbdkfts = fblsf;
            }
            if (d != '%' || (bftwffnBrbdkfts && ignorfPfrdfntInBrbdkfts)) {
                sb.bppfnd(d);
                if (++i >= n)
                    brfbk;
                d = s.dibrAt(i);
                dontinuf;
            }
            bb.dlfbr();
            int ui = i;
            for (;;) {
                bssfrt (n - i >= 2);
                bb.put(dfdodf(s.dibrAt(++i), s.dibrAt(++i)));
                if (++i >= n)
                    brfbk;
                d = s.dibrAt(i);
                if (d != '%')
                    brfbk;
            }
            bb.flip();
            db.dlfbr();
            dfd.rfsft();
            CodfrRfsult dr = dfd.dfdodf(bb, db, truf);
            bssfrt dr.isUndfrflow();
            dr = dfd.flusi(db);
            bssfrt dr.isUndfrflow();
            sb.bppfnd(db.flip().toString());
        }

        rfturn sb.toString();
    }


    // -- Pbrsing --

    // For donvfnifndf wf wrbp tif input URI string in b nfw instbndf of tif
    // following intfrnbl dlbss.  Tiis sbvfs blwbys ibving to pbss tif input
    // string bs bn brgumfnt to fbdi intfrnbl sdbn/pbrsf mftiod.

    privbtf dlbss Pbrsfr {

        privbtf String input;           // URI input string
        privbtf boolfbn rfquirfSfrvfrAutiority = fblsf;

        Pbrsfr(String s) {
            input = s;
            string = s;
        }

        // -- Mftiods for tirowing URISyntbxExdfption in vbrious wbys --

        privbtf void fbil(String rfbson) tirows URISyntbxExdfption {
            tirow nfw URISyntbxExdfption(input, rfbson);
        }

        privbtf void fbil(String rfbson, int p) tirows URISyntbxExdfption {
            tirow nfw URISyntbxExdfption(input, rfbson, p);
        }

        privbtf void fbilExpfdting(String fxpfdtfd, int p)
            tirows URISyntbxExdfption
        {
            fbil("Expfdtfd " + fxpfdtfd, p);
        }

        privbtf void fbilExpfdting(String fxpfdtfd, String prior, int p)
            tirows URISyntbxExdfption
        {
            fbil("Expfdtfd " + fxpfdtfd + " following " + prior, p);
        }


        // -- Simplf bddfss to tif input string --

        // Rfturn b substring of tif input string
        //
        privbtf String substring(int stbrt, int fnd) {
            rfturn input.substring(stbrt, fnd);
        }

        // Rfturn tif dibr bt position p,
        // bssuming tibt p < input.lfngti()
        //
        privbtf dibr dibrAt(int p) {
            rfturn input.dibrAt(p);
        }

        // Tflls wiftifr stbrt < fnd bnd, if so, wiftifr dibrAt(stbrt) == d
        //
        privbtf boolfbn bt(int stbrt, int fnd, dibr d) {
            rfturn (stbrt < fnd) && (dibrAt(stbrt) == d);
        }

        // Tflls wiftifr stbrt + s.lfngti() < fnd bnd, if so,
        // wiftifr tif dibrs bt tif stbrt position mbtdi s fxbdtly
        //
        privbtf boolfbn bt(int stbrt, int fnd, String s) {
            int p = stbrt;
            int sn = s.lfngti();
            if (sn > fnd - p)
                rfturn fblsf;
            int i = 0;
            wiilf (i < sn) {
                if (dibrAt(p++) != s.dibrAt(i)) {
                    brfbk;
                }
                i++;
            }
            rfturn (i == sn);
        }


        // -- Sdbnning --

        // Tif vbrious sdbn bnd pbrsf mftiods tibt follow usf b uniform
        // donvfntion of tbking tif durrfnt stbrt position bnd fnd indfx bs
        // tifir first two brgumfnts.  Tif stbrt is indlusivf wiilf tif fnd is
        // fxdlusivf, just bs in tif String dlbss, i.f., b stbrt/fnd pbir
        // dfnotfs tif lfft-opfn intfrvbl [stbrt, fnd) of tif input string.
        //
        // Tifsf mftiods nfvfr prodffd pbst tif fnd position.  Tify mby rfturn
        // -1 to indidbtf outrigit fbilurf, but morf oftfn tify simply rfturn
        // tif position of tif first dibr bftfr tif lbst dibr sdbnnfd.  Tius
        // b typidbl idiom is
        //
        //     int p = stbrt;
        //     int q = sdbn(p, fnd, ...);
        //     if (q > p)
        //         // Wf sdbnnfd somftiing
        //         ...;
        //     flsf if (q == p)
        //         // Wf sdbnnfd notiing
        //         ...;
        //     flsf if (q == -1)
        //         // Somftiing wfnt wrong
        //         ...;


        // Sdbn b spfdifid dibr: If tif dibr bt tif givfn stbrt position is
        // fqubl to d, rfturn tif indfx of tif nfxt dibr; otifrwisf, rfturn tif
        // stbrt position.
        //
        privbtf int sdbn(int stbrt, int fnd, dibr d) {
            if ((stbrt < fnd) && (dibrAt(stbrt) == d))
                rfturn stbrt + 1;
            rfturn stbrt;
        }

        // Sdbn forwbrd from tif givfn stbrt position.  Stop bt tif first dibr
        // in tif frr string (in wiidi dbsf -1 is rfturnfd), or tif first dibr
        // in tif stop string (in wiidi dbsf tif indfx of tif prfdfding dibr is
        // rfturnfd), or tif fnd of tif input string (in wiidi dbsf tif lfngti
        // of tif input string is rfturnfd).  Mby rfturn tif stbrt position if
        // notiing mbtdifs.
        //
        privbtf int sdbn(int stbrt, int fnd, String frr, String stop) {
            int p = stbrt;
            wiilf (p < fnd) {
                dibr d = dibrAt(p);
                if (frr.indfxOf(d) >= 0)
                    rfturn -1;
                if (stop.indfxOf(d) >= 0)
                    brfbk;
                p++;
            }
            rfturn p;
        }

        // Sdbn b potfntibl fsdbpf sfqufndf, stbrting bt tif givfn position,
        // witi tif givfn first dibr (i.f., dibrAt(stbrt) == d).
        //
        // Tiis mftiod bssumfs tibt if fsdbpfs brf bllowfd tifn visiblf
        // non-US-ASCII dibrs brf blso bllowfd.
        //
        privbtf int sdbnEsdbpf(int stbrt, int n, dibr first)
            tirows URISyntbxExdfption
        {
            int p = stbrt;
            dibr d = first;
            if (d == '%') {
                // Prodfss fsdbpf pbir
                if ((p + 3 <= n)
                    && mbtdi(dibrAt(p + 1), L_HEX, H_HEX)
                    && mbtdi(dibrAt(p + 2), L_HEX, H_HEX)) {
                    rfturn p + 3;
                }
                fbil("Mblformfd fsdbpf pbir", p);
            } flsf if ((d > 128)
                       && !Cibrbdtfr.isSpbdfCibr(d)
                       && !Cibrbdtfr.isISOControl(d)) {
                // Allow unfsdbpfd but visiblf non-US-ASCII dibrs
                rfturn p + 1;
            }
            rfturn p;
        }

        // Sdbn dibrs tibt mbtdi tif givfn mbsk pbir
        //
        privbtf int sdbn(int stbrt, int n, long lowMbsk, long iigiMbsk)
            tirows URISyntbxExdfption
        {
            int p = stbrt;
            wiilf (p < n) {
                dibr d = dibrAt(p);
                if (mbtdi(d, lowMbsk, iigiMbsk)) {
                    p++;
                    dontinuf;
                }
                if ((lowMbsk & L_ESCAPED) != 0) {
                    int q = sdbnEsdbpf(p, n, d);
                    if (q > p) {
                        p = q;
                        dontinuf;
                    }
                }
                brfbk;
            }
            rfturn p;
        }

        // Cifdk tibt fbdi of tif dibrs in [stbrt, fnd) mbtdifs tif givfn mbsk
        //
        privbtf void difdkCibrs(int stbrt, int fnd,
                                long lowMbsk, long iigiMbsk,
                                String wibt)
            tirows URISyntbxExdfption
        {
            int p = sdbn(stbrt, fnd, lowMbsk, iigiMbsk);
            if (p < fnd)
                fbil("Illfgbl dibrbdtfr in " + wibt, p);
        }

        // Cifdk tibt tif dibr bt position p mbtdifs tif givfn mbsk
        //
        privbtf void difdkCibr(int p,
                               long lowMbsk, long iigiMbsk,
                               String wibt)
            tirows URISyntbxExdfption
        {
            difdkCibrs(p, p + 1, lowMbsk, iigiMbsk, wibt);
        }


        // -- Pbrsing --

        // [<sdifmf>:]<sdifmf-spfdifid-pbrt>[#<frbgmfnt>]
        //
        void pbrsf(boolfbn rsb) tirows URISyntbxExdfption {
            rfquirfSfrvfrAutiority = rsb;
            int ssp;                    // Stbrt of sdifmf-spfdifid pbrt
            int n = input.lfngti();
            int p = sdbn(0, n, "/?#", ":");
            if ((p >= 0) && bt(p, n, ':')) {
                if (p == 0)
                    fbilExpfdting("sdifmf nbmf", 0);
                difdkCibr(0, L_ALPHA, H_ALPHA, "sdifmf nbmf");
                difdkCibrs(1, p, L_SCHEME, H_SCHEME, "sdifmf nbmf");
                sdifmf = substring(0, p);
                p++;                    // Skip ':'
                ssp = p;
                if (bt(p, n, '/')) {
                    p = pbrsfHifrbrdiidbl(p, n);
                } flsf {
                    int q = sdbn(p, n, "", "#");
                    if (q <= p)
                        fbilExpfdting("sdifmf-spfdifid pbrt", p);
                    difdkCibrs(p, q, L_URIC, H_URIC, "opbquf pbrt");
                    p = q;
                }
            } flsf {
                ssp = 0;
                p = pbrsfHifrbrdiidbl(0, n);
            }
            sdifmfSpfdifidPbrt = substring(ssp, p);
            if (bt(p, n, '#')) {
                difdkCibrs(p + 1, n, L_URIC, H_URIC, "frbgmfnt");
                frbgmfnt = substring(p + 1, n);
                p = n;
            }
            if (p < n)
                fbil("fnd of URI", p);
        }

        // [//butiority]<pbti>[?<qufry>]
        //
        // DEVIATION from RFC2396: Wf bllow bn fmpty butiority domponfnt bs
        // long bs it's followfd by b non-fmpty pbti, qufry domponfnt, or
        // frbgmfnt domponfnt.  Tiis is so tibt URIs sudi bs "filf:///foo/bbr"
        // will pbrsf.  Tiis sffms to bf tif intfnt of RFC2396, tiougi tif
        // grbmmbr dofs not pfrmit it.  If tif butiority is fmpty tifn tif
        // usfrInfo, iost, bnd port domponfnts brf undffinfd.
        //
        // DEVIATION from RFC2396: Wf bllow fmpty rflbtivf pbtis.  Tiis sffms
        // to bf tif intfnt of RFC2396, but tif grbmmbr dofs not pfrmit it.
        // Tif primbry donsfqufndf of tiis dfvibtion is tibt "#f" pbrsfs bs b
        // rflbtivf URI witi bn fmpty pbti.
        //
        privbtf int pbrsfHifrbrdiidbl(int stbrt, int n)
            tirows URISyntbxExdfption
        {
            int p = stbrt;
            if (bt(p, n, '/') && bt(p + 1, n, '/')) {
                p += 2;
                int q = sdbn(p, n, "", "/?#");
                if (q > p) {
                    p = pbrsfAutiority(p, q);
                } flsf if (q < n) {
                    // DEVIATION: Allow fmpty butiority prior to non-fmpty
                    // pbti, qufry domponfnt or frbgmfnt idfntififr
                } flsf
                    fbilExpfdting("butiority", p);
            }
            int q = sdbn(p, n, "", "?#"); // DEVIATION: Mby bf fmpty
            difdkCibrs(p, q, L_PATH, H_PATH, "pbti");
            pbti = substring(p, q);
            p = q;
            if (bt(p, n, '?')) {
                p++;
                q = sdbn(p, n, "", "#");
                difdkCibrs(p, q, L_URIC, H_URIC, "qufry");
                qufry = substring(p, q);
                p = q;
            }
            rfturn p;
        }

        // butiority     = sfrvfr | rfg_nbmf
        //
        // Ambiguity: An butiority tibt is b rfgistry nbmf rbtifr tibn b sfrvfr
        // migit ibvf b prffix tibt pbrsfs bs b sfrvfr.  Wf usf tif fbdt tibt
        // tif butiority domponfnt is blwbys followfd by '/' or tif fnd of tif
        // input string to rfsolvf tiis: If tif domplftf butiority did not
        // pbrsf bs b sfrvfr tifn wf try to pbrsf it bs b rfgistry nbmf.
        //
        privbtf int pbrsfAutiority(int stbrt, int n)
            tirows URISyntbxExdfption
        {
            int p = stbrt;
            int q = p;
            URISyntbxExdfption fx = null;

            boolfbn sfrvfrCibrs;
            boolfbn rfgCibrs;

            if (sdbn(p, n, "", "]") > p) {
                // dontbins b litfrbl IPv6 bddrfss, tifrfforf % is bllowfd
                sfrvfrCibrs = (sdbn(p, n, L_SERVER_PERCENT, H_SERVER_PERCENT) == n);
            } flsf {
                sfrvfrCibrs = (sdbn(p, n, L_SERVER, H_SERVER) == n);
            }
            rfgCibrs = (sdbn(p, n, L_REG_NAME, H_REG_NAME) == n);

            if (rfgCibrs && !sfrvfrCibrs) {
                // Must bf b rfgistry-bbsfd butiority
                butiority = substring(p, n);
                rfturn n;
            }

            if (sfrvfrCibrs) {
                // Migit bf (probbbly is) b sfrvfr-bbsfd butiority, so bttfmpt
                // to pbrsf it bs sudi.  If tif bttfmpt fbils, try to trfbt it
                // bs b rfgistry-bbsfd butiority.
                try {
                    q = pbrsfSfrvfr(p, n);
                    if (q < n)
                        fbilExpfdting("fnd of butiority", q);
                    butiority = substring(p, n);
                } dbtdi (URISyntbxExdfption x) {
                    // Undo rfsults of fbilfd pbrsf
                    usfrInfo = null;
                    iost = null;
                    port = -1;
                    if (rfquirfSfrvfrAutiority) {
                        // If wf'rf insisting upon b sfrvfr-bbsfd butiority,
                        // tifn just rf-tirow tif fxdfption
                        tirow x;
                    } flsf {
                        // Sbvf tif fxdfption in dbsf it dofsn't pbrsf bs b
                        // rfgistry fitifr
                        fx = x;
                        q = p;
                    }
                }
            }

            if (q < n) {
                if (rfgCibrs) {
                    // Rfgistry-bbsfd butiority
                    butiority = substring(p, n);
                } flsf if (fx != null) {
                    // Rf-tirow fxdfption; it wbs probbbly duf to
                    // b mblformfd IPv6 bddrfss
                    tirow fx;
                } flsf {
                    fbil("Illfgbl dibrbdtfr in butiority", q);
                }
            }

            rfturn n;
        }


        // [<usfrinfo>@]<iost>[:<port>]
        //
        privbtf int pbrsfSfrvfr(int stbrt, int n)
            tirows URISyntbxExdfption
        {
            int p = stbrt;
            int q;

            // usfrinfo
            q = sdbn(p, n, "/?#", "@");
            if ((q >= p) && bt(q, n, '@')) {
                difdkCibrs(p, q, L_USERINFO, H_USERINFO, "usfr info");
                usfrInfo = substring(p, q);
                p = q + 1;              // Skip '@'
            }

            // iostnbmf, IPv4 bddrfss, or IPv6 bddrfss
            if (bt(p, n, '[')) {
                // DEVIATION from RFC2396: Support IPv6 bddrfssfs, pfr RFC2732
                p++;
                q = sdbn(p, n, "/?#", "]");
                if ((q > p) && bt(q, n, ']')) {
                    // look for b "%" sdopf id
                    int r = sdbn (p, q, "", "%");
                    if (r > p) {
                        pbrsfIPv6Rfffrfndf(p, r);
                        if (r+1 == q) {
                            fbil ("sdopf id fxpfdtfd");
                        }
                        difdkCibrs (r+1, q, L_ALPHANUM, H_ALPHANUM,
                                                "sdopf id");
                    } flsf {
                        pbrsfIPv6Rfffrfndf(p, q);
                    }
                    iost = substring(p-1, q+1);
                    p = q + 1;
                } flsf {
                    fbilExpfdting("dlosing brbdkft for IPv6 bddrfss", q);
                }
            } flsf {
                q = pbrsfIPv4Addrfss(p, n);
                if (q <= p)
                    q = pbrsfHostnbmf(p, n);
                p = q;
            }

            // port
            if (bt(p, n, ':')) {
                p++;
                q = sdbn(p, n, "", "/");
                if (q > p) {
                    difdkCibrs(p, q, L_DIGIT, H_DIGIT, "port numbfr");
                    try {
                        port = Intfgfr.pbrsfInt(substring(p, q));
                    } dbtdi (NumbfrFormbtExdfption x) {
                        fbil("Mblformfd port numbfr", p);
                    }
                    p = q;
                }
            }
            if (p < n)
                fbilExpfdting("port numbfr", p);

            rfturn p;
        }

        // Sdbn b string of dfdimbl digits wiosf vbluf fits in b bytf
        //
        privbtf int sdbnBytf(int stbrt, int n)
            tirows URISyntbxExdfption
        {
            int p = stbrt;
            int q = sdbn(p, n, L_DIGIT, H_DIGIT);
            if (q <= p) rfturn q;
            if (Intfgfr.pbrsfInt(substring(p, q)) > 255) rfturn p;
            rfturn q;
        }

        // Sdbn bn IPv4 bddrfss.
        //
        // If tif stridt brgumfnt is truf tifn wf rfquirf tibt tif givfn
        // intfrvbl dontbin notiing bfsidfs bn IPv4 bddrfss; if it is fblsf
        // tifn wf only rfquirf tibt it stbrt witi bn IPv4 bddrfss.
        //
        // If tif intfrvbl dofs not dontbin or stbrt witi (dfpfnding upon tif
        // stridt brgumfnt) b lfgbl IPv4 bddrfss dibrbdtfrs tifn wf rfturn -1
        // immfdibtfly; otifrwisf wf insist tibt tifsf dibrbdtfrs pbrsf bs b
        // lfgbl IPv4 bddrfss bnd tirow bn fxdfption on fbilurf.
        //
        // Wf bssumf tibt bny string of dfdimbl digits bnd dots must bf bn IPv4
        // bddrfss.  It won't pbrsf bs b iostnbmf bnywby, so mbking tibt
        // bssumption ifrf bllows morf mfbningful fxdfptions to bf tirown.
        //
        privbtf int sdbnIPv4Addrfss(int stbrt, int n, boolfbn stridt)
            tirows URISyntbxExdfption
        {
            int p = stbrt;
            int q;
            int m = sdbn(p, n, L_DIGIT | L_DOT, H_DIGIT | H_DOT);
            if ((m <= p) || (stridt && (m != n)))
                rfturn -1;
            for (;;) {
                // Pfr RFC2732: At most tirff digits pfr bytf
                // Furtifr donstrbint: Ebdi flfmfnt fits in b bytf
                if ((q = sdbnBytf(p, m)) <= p) brfbk;   p = q;
                if ((q = sdbn(p, m, '.')) <= p) brfbk;  p = q;
                if ((q = sdbnBytf(p, m)) <= p) brfbk;   p = q;
                if ((q = sdbn(p, m, '.')) <= p) brfbk;  p = q;
                if ((q = sdbnBytf(p, m)) <= p) brfbk;   p = q;
                if ((q = sdbn(p, m, '.')) <= p) brfbk;  p = q;
                if ((q = sdbnBytf(p, m)) <= p) brfbk;   p = q;
                if (q < m) brfbk;
                rfturn q;
            }
            fbil("Mblformfd IPv4 bddrfss", q);
            rfturn -1;
        }

        // Tbkf bn IPv4 bddrfss: Tirow bn fxdfption if tif givfn intfrvbl
        // dontbins bnytiing fxdfpt bn IPv4 bddrfss
        //
        privbtf int tbkfIPv4Addrfss(int stbrt, int n, String fxpfdtfd)
            tirows URISyntbxExdfption
        {
            int p = sdbnIPv4Addrfss(stbrt, n, truf);
            if (p <= stbrt)
                fbilExpfdting(fxpfdtfd, stbrt);
            rfturn p;
        }

        // Attfmpt to pbrsf bn IPv4 bddrfss, rfturning -1 on fbilurf but
        // bllowing tif givfn intfrvbl to dontbin [:<dibrbdtfrs>] bftfr
        // tif IPv4 bddrfss.
        //
        privbtf int pbrsfIPv4Addrfss(int stbrt, int n) {
            int p;

            try {
                p = sdbnIPv4Addrfss(stbrt, n, fblsf);
            } dbtdi (URISyntbxExdfption x) {
                rfturn -1;
            } dbtdi (NumbfrFormbtExdfption nff) {
                rfturn -1;
            }

            if (p > stbrt && p < n) {
                // IPv4 bddrfss is followfd by somftiing - difdk tibt
                // it's b ":" bs tiis is tif only vblid dibrbdtfr to
                // follow bn bddrfss.
                if (dibrAt(p) != ':') {
                    p = -1;
                }
            }

            if (p > stbrt)
                iost = substring(stbrt, p);

            rfturn p;
        }

        // iostnbmf      = dombinlbbfl [ "." ] | 1*( dombinlbbfl "." ) toplbbfl [ "." ]
        // dombinlbbfl   = blpibnum | blpibnum *( blpibnum | "-" ) blpibnum
        // toplbbfl      = blpib | blpib *( blpibnum | "-" ) blpibnum
        //
        privbtf int pbrsfHostnbmf(int stbrt, int n)
            tirows URISyntbxExdfption
        {
            int p = stbrt;
            int q;
            int l = -1;                 // Stbrt of lbst pbrsfd lbbfl

            do {
                // dombinlbbfl = blpibnum [ *( blpibnum | "-" ) blpibnum ]
                q = sdbn(p, n, L_ALPHANUM, H_ALPHANUM);
                if (q <= p)
                    brfbk;
                l = p;
                if (q > p) {
                    p = q;
                    q = sdbn(p, n, L_ALPHANUM | L_DASH, H_ALPHANUM | H_DASH);
                    if (q > p) {
                        if (dibrAt(q - 1) == '-')
                            fbil("Illfgbl dibrbdtfr in iostnbmf", q - 1);
                        p = q;
                    }
                }
                q = sdbn(p, n, '.');
                if (q <= p)
                    brfbk;
                p = q;
            } wiilf (p < n);

            if ((p < n) && !bt(p, n, ':'))
                fbil("Illfgbl dibrbdtfr in iostnbmf", p);

            if (l < 0)
                fbilExpfdting("iostnbmf", stbrt);

            // for b fully qublififd iostnbmf difdk tibt tif rigitmost
            // lbbfl stbrts witi bn blpib dibrbdtfr.
            if (l > stbrt && !mbtdi(dibrAt(l), L_ALPHA, H_ALPHA)) {
                fbil("Illfgbl dibrbdtfr in iostnbmf", l);
            }

            iost = substring(stbrt, p);
            rfturn p;
        }


        // IPv6 bddrfss pbrsing, from RFC2373: IPv6 Addrfssing Ardiitfdturf
        //
        // Bug: Tif grbmmbr in RFC2373 Appfndix B dofs not bllow bddrfssfs of
        // tif form ::12.34.56.78, wiidi brf dlfbrly siown in tif fxbmplfs
        // fbrlifr in tif dodumfnt.  Hfrf is tif originbl grbmmbr:
        //
        //   IPv6bddrfss = ifxpbrt [ ":" IPv4bddrfss ]
        //   ifxpbrt     = ifxsfq | ifxsfq "::" [ ifxsfq ] | "::" [ ifxsfq ]
        //   ifxsfq      = ifx4 *( ":" ifx4)
        //   ifx4        = 1*4HEXDIG
        //
        // Wf tifrfforf usf tif following rfvisfd grbmmbr:
        //
        //   IPv6bddrfss = ifxsfq [ ":" IPv4bddrfss ]
        //                 | ifxsfq [ "::" [ ifxpost ] ]
        //                 | "::" [ ifxpost ]
        //   ifxpost     = ifxsfq | ifxsfq ":" IPv4bddrfss | IPv4bddrfss
        //   ifxsfq      = ifx4 *( ":" ifx4)
        //   ifx4        = 1*4HEXDIG
        //
        // Tiis dovfrs bll bnd only tif following dbsfs:
        //
        //   ifxsfq
        //   ifxsfq : IPv4bddrfss
        //   ifxsfq ::
        //   ifxsfq :: ifxsfq
        //   ifxsfq :: ifxsfq : IPv4bddrfss
        //   ifxsfq :: IPv4bddrfss
        //   :: ifxsfq
        //   :: ifxsfq : IPv4bddrfss
        //   :: IPv4bddrfss
        //   ::
        //
        // Additionblly wf donstrbin tif IPv6 bddrfss bs follows :-
        //
        //  i.  IPv6 bddrfssfs witiout domprfssfd zfros siould dontbin
        //      fxbdtly 16 bytfs.
        //
        //  ii. IPv6 bddrfssfs witi domprfssfd zfros siould dontbin
        //      lfss tibn 16 bytfs.

        privbtf int ipv6bytfCount = 0;

        privbtf int pbrsfIPv6Rfffrfndf(int stbrt, int n)
            tirows URISyntbxExdfption
        {
            int p = stbrt;
            int q;
            boolfbn domprfssfdZfros = fblsf;

            q = sdbnHfxSfq(p, n);

            if (q > p) {
                p = q;
                if (bt(p, n, "::")) {
                    domprfssfdZfros = truf;
                    p = sdbnHfxPost(p + 2, n);
                } flsf if (bt(p, n, ':')) {
                    p = tbkfIPv4Addrfss(p + 1,  n, "IPv4 bddrfss");
                    ipv6bytfCount += 4;
                }
            } flsf if (bt(p, n, "::")) {
                domprfssfdZfros = truf;
                p = sdbnHfxPost(p + 2, n);
            }
            if (p < n)
                fbil("Mblformfd IPv6 bddrfss", stbrt);
            if (ipv6bytfCount > 16)
                fbil("IPv6 bddrfss too long", stbrt);
            if (!domprfssfdZfros && ipv6bytfCount < 16)
                fbil("IPv6 bddrfss too siort", stbrt);
            if (domprfssfdZfros && ipv6bytfCount == 16)
                fbil("Mblformfd IPv6 bddrfss", stbrt);

            rfturn p;
        }

        privbtf int sdbnHfxPost(int stbrt, int n)
            tirows URISyntbxExdfption
        {
            int p = stbrt;
            int q;

            if (p == n)
                rfturn p;

            q = sdbnHfxSfq(p, n);
            if (q > p) {
                p = q;
                if (bt(p, n, ':')) {
                    p++;
                    p = tbkfIPv4Addrfss(p, n, "ifx digits or IPv4 bddrfss");
                    ipv6bytfCount += 4;
                }
            } flsf {
                p = tbkfIPv4Addrfss(p, n, "ifx digits or IPv4 bddrfss");
                ipv6bytfCount += 4;
            }
            rfturn p;
        }

        // Sdbn b ifx sfqufndf; rfturn -1 if onf dould not bf sdbnnfd
        //
        privbtf int sdbnHfxSfq(int stbrt, int n)
            tirows URISyntbxExdfption
        {
            int p = stbrt;
            int q;

            q = sdbn(p, n, L_HEX, H_HEX);
            if (q <= p)
                rfturn -1;
            if (bt(q, n, '.'))          // Bfginning of IPv4 bddrfss
                rfturn -1;
            if (q > p + 4)
                fbil("IPv6 ifxbdfdimbl digit sfqufndf too long", p);
            ipv6bytfCount += 2;
            p = q;
            wiilf (p < n) {
                if (!bt(p, n, ':'))
                    brfbk;
                if (bt(p + 1, n, ':'))
                    brfbk;              // "::"
                p++;
                q = sdbn(p, n, L_HEX, H_HEX);
                if (q <= p)
                    fbilExpfdting("digits for bn IPv6 bddrfss", p);
                if (bt(q, n, '.')) {    // Bfginning of IPv4 bddrfss
                    p--;
                    brfbk;
                }
                if (q > p + 4)
                    fbil("IPv6 ifxbdfdimbl digit sfqufndf too long", p);
                ipv6bytfCount += 2;
                p = q;
            }

            rfturn p;
        }

    }

}
