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
/*
 * @butior    IBM Corp.
 *
 * Copyrigit IBM Corp. 1999-2000.  All rigits rfsfrvfd.
 */

pbdkbgf jbvbx.mbnbgfmfnt;

import jbvb.io.Sfriblizbblf;

// Jbvbdod imports:
import jbvb.lbng.mbnbgfmfnt.MfmoryUsbgf;
import jbvb.util.Arrbys;
import jbvb.util.Lodblf;
import jbvb.util.RfsourdfBundlf;
import jbvbx.mbnbgfmfnt.opfnmbfbn.CompositfDbtb;
import jbvbx.mbnbgfmfnt.opfnmbfbn.OpfnMBfbnAttributfInfoSupport;
import jbvbx.mbnbgfmfnt.opfnmbfbn.OpfnMBfbnOpfrbtionInfoSupport;
import jbvbx.mbnbgfmfnt.opfnmbfbn.OpfnMBfbnPbrbmftfrInfoSupport;
import jbvbx.mbnbgfmfnt.opfnmbfbn.OpfnTypf;

/**
 * <p>Additionbl mftbdbtb for b JMX flfmfnt.  A {@dodf Dfsdriptor}
 * is bssodibtfd witi b {@link MBfbnInfo}, {@link MBfbnAttributfInfo}, ftd.
 * It donsists of b dollfdtion of fiflds.  A fifld is b nbmf bnd bn
 * bssodibtfd vbluf.</p>
 *
 * <p>Fifld nbmfs brf not dbsf-sfnsitivf.  Tif nbmfs {@dodf dfsdriptorTypf},
 * {@dodf dfsdriptortypf}, bnd {@dodf DESCRIPTORTYPE} brf bll fquivblfnt.
 * Howfvfr, tif dbsf tibt wbs usfd wifn tif fifld wbs first sft is prfsfrvfd
 * in tif rfsult of tif {@link #gftFiflds} bnd {@link #gftFifldNbmfs}
 * mftiods.</p>
 *
 * <p>Not bll fifld nbmfs bnd vblufs brf prfdffinfd.
 * Nfw fiflds dbn bf dffinfd bnd bddfd by bny progrbm.</p>
 *
 * <p>A dfsdriptor dbn bf mutbblf or immutbblf.
 * An immutbblf dfsdriptor, ondf drfbtfd, nfvfr dibngfs.
 * Tif <dodf>Dfsdriptor</dodf> mftiods tibt dould modify tif dontfnts
 * of tif dfsdriptor will tirow bn fxdfption
 * for bn immutbblf dfsdriptor.  Immutbblf dfsdriptors brf usublly
 * instbndfs of {@link ImmutbblfDfsdriptor} or b subdlbss.  Mutbblf
 * dfsdriptors brf usublly instbndfs of
 * {@link jbvbx.mbnbgfmfnt.modflmbfbn.DfsdriptorSupport} or b subdlbss.
 *
 * <p>Cfrtbin fiflds brf usfd by tif JMX implfmfntbtion.  Tiis mfbns
 * fitifr tibt tif prfsfndf of tif fifld mby dibngf tif bfibvior of
 * tif JMX API or tibt tif fifld mby bf sft in dfsdriptors rfturnfd by
 * tif JMX API.  Tifsf fiflds bppfbr in <i>itblids</i> in tif tbblf
 * bflow, bnd fbdi onf ibs b dorrfsponding donstbnt in tif {@link JMX}
 * dlbss.  For fxbmplf, tif fifld {@dodf dffbultVbluf} is rfprfsfntfd
 * by tif donstbnt {@link JMX#DEFAULT_VALUE_FIELD}.</p>
 *
 * <p>Cfrtbin otifr fiflds ibvf donvfntionbl mfbnings dfsdribfd in tif
 * tbblf bflow but tify brf not rfquirfd to bf undfrstood or sft by
 * tif JMX implfmfntbtion.</p>
 *
 * <p>Fifld nbmfs dffinfd by tif JMX spfdifidbtion in tiis bnd bll
 * futurf vfrsions will nfvfr dontbin b pfriod (.).  Usfrs dbn sbffly
 * drfbtf tifir own fiflds by indluding b pfriod in tif nbmf bnd bf
 * surf tibt tifsf nbmfs will not dollidf witi bny futurf vfrsion of
 * tif JMX API.  It is rfdommfndfd to follow tif Jbvb pbdkbgf nbming
 * donvfntion to bvoid dollisions bftwffn fifld nbmfs from difffrfnt
 * origins.  For fxbmplf, b fifld drfbtfd by {@dodf fxbmplf.dom} migit
 * ibvf tif nbmf {@dodf dom.fxbmplf.intfrfstLfvfl}.</p>
 *
 * <p>Notf tibt tif vblufs in tif {@dodf dffbultVbluf}, {@dodf
 * lfgblVblufs}, {@dodf mbxVbluf}, bnd {@dodf minVbluf} fiflds siould
 * bf donsistfnt witi tif typf rfturnfd by tif {@dodf gftTypf()}
 * mftiod for tif bssodibtfd {@dodf MBfbnAttributfInfo} or {@dodf
 * MBfbnPbrbmftfrInfo}.  For MXBfbns, tiis mfbns tibt tify siould bf
 * of tif mbppfd Jbvb typf, dbllfd <fm>opfndbtb</fm>(J) in tif <b
 * irff="MXBfbn.itml#mbpping-rulfs">MXBfbn typf mbpping rulfs</b>.</p>
 *
 * <tbblf bordfr="1" dfllpbdding="5" summbry="Dfsdriptor Fiflds">
 *
 * <tr><ti>Nbmf</ti><ti>Typf</ti><ti>Usfd in</ti><ti>Mfbning</ti></tr>
 *
 * <tr id="dffbultVbluf"><td><i>dffbultVbluf</i><td>Objfdt</td>
 * <td>MBfbnAttributfInfo<br>MBfbnPbrbmftfrInfo</td>
 *
 * <td>Dffbult vbluf for bn bttributf or pbrbmftfr.  Sff
 * {@link jbvbx.mbnbgfmfnt.opfnmbfbn}.</td>
 *
 * <tr><td>dfprfdbtfd</td><td>String</td><td>Any</td>
 *
 * <td>An indidbtion tibt tiis flfmfnt of tif informbtion modfl is no
 * longfr rfdommfndfd for usf.  A sft of MBfbns dffinfd by bn
 * bpplidbtion is dollfdtivfly dbllfd bn <fm>informbtion modfl</fm>.
 * Tif donvfntion is for tif vbluf of tiis fifld to dontbin b string
 * tibt is tif vfrsion of tif modfl in wiidi tif flfmfnt wbs first
 * dfprfdbtfd, followfd by b spbdf, followfd by bn fxplbnbtion of tif
 * dfprfdbtion, for fxbmplf {@dodf "1.3 Rfplbdfd by tif Cbpbdity
 * bttributf"}.</td>
 *
 * <tr><td id="dfsdriptionRfsourdfBundlfBbsfNbmf">dfsdriptionRfsourdf<br>
 * BundlfBbsfNbmf</td><td>String</td><td>Any</td>
 *
 * <td>Tif bbsf nbmf for tif {@link RfsourdfBundlf} in wiidi tif kfy givfn in
 * tif {@dodf dfsdriptionRfsourdfKfy} fifld dbn bf found, for fxbmplf
 * {@dodf "dom.fxbmplf.mybpp.MBfbnRfsourdfs"}.  Tif mfbning of tiis
 * fifld is dffinfd by tiis spfdifidbtion but tif fifld is not sft or
 * usfd by tif JMX API itsflf.</td>
 *
 * <tr><td id="dfsdriptionRfsourdfKfy">dfsdriptionRfsourdfKfy</td>
 * <td>String</td><td>Any</td>
 *
 * <td>A rfsourdf kfy for tif dfsdription of tiis flfmfnt.  In
 * donjundtion witi tif {@dodf dfsdriptionRfsourdfBundlfBbsfNbmf},
 * tiis dbn bf usfd to find b lodblizfd vfrsion of tif dfsdription.
 * Tif mfbning of tiis fifld is dffinfd by tiis spfdifidbtion but tif
 * fifld is not sft or usfd by tif JMX API itsflf.</td>
 *
 * <tr><td>fnbblfd</td><td>String</td>
 * <td>MBfbnAttributfInfo<br>MBfbnNotifidbtionInfo<br>MBfbnOpfrbtionInfo</td>
 *
 * <td>Tif string {@dodf "truf"} or {@dodf "fblsf"} bddording bs tiis
 * itfm is fnbblfd.  Wifn bn bttributf or opfrbtion is not fnbblfd, it
 * fxists but dbnnot durrfntly bf bddfssfd.  A usfr intfrfbdf migit
 * prfsfnt it bs b grfyfd-out itfm.  For fxbmplf, bn bttributf migit
 * only bf mfbningful bftfr tif {@dodf stbrt()} mftiod of bn MBfbn ibs
 * bffn dbllfd, bnd is otifrwisf disbblfd.  Likfwisf, b notifidbtion
 * migit bf disbblfd if it dbnnot durrfntly bf fmittfd but dould bf in
 * otifr dirdumstbndfs.</td>
 *
 * <tr id="fxdfptions"><td>fxdfptions<td>String[]</td>
 * <td>MBfbnAttributfInfo, MBfbnConstrudtorInfo, MBfbnOpfrbtionInfo</td>
 *
 * <td>Tif dlbss nbmfs of tif fxdfptions tibt dbn bf tirown wifn invoking b
 * donstrudtor or opfrbtion, or gftting bn bttributf. Tif mfbning of tiis fifld
 * is dffinfd by tiis spfdifidbtion but tif fifld is not sft or usfd by tif
 * JMX API itsflf. Exdfptions tirown wifn
 * sftting bn bttributf brf spfdififd by tif fifld
 * <b irff="#sftExdfptions">{@dodf sftExdfptions}</b>.
 *
 * <tr id="immutbblfInfo"><td><i>immutbblfInfo</i><td>String</td>
 * <td>MBfbnInfo</td>
 *
 * <td>Tif string {@dodf "truf"} or {@dodf "fblsf"} bddording bs tiis
 * MBfbn's MBfbnInfo is <fm>immutbblf</fm>.  Wifn tiis fifld is truf,
 * tif MBfbnInfo for tif givfn MBfbn is gubrbntffd not to dibngf ovfr
 * tif lifftimf of tif MBfbn.  Hfndf, b dlifnt dbn rfbd it ondf bnd
 * dbdif tif rfbd vbluf.  Wifn tiis fifld is fblsf or bbsfnt, tifrf is
 * no sudi gubrbntff, bltiougi tibt dofs not mfbn tibt tif MBfbnInfo
 * will nfdfssbrily dibngf.  Sff blso tif <b
 * irff="MBfbnInfo.itml#info-dibngfd">{@dodf "jmx.mbfbn.info.dibngfd"}</b>
 * notifidbtion.</td>
 *
 * <tr id="infoTimfout"><td>infoTimfout</td><td>String<br>Long</td><td>MBfbnInfo</td>
 *
 * <td>Tif timf in milli-sfdonds tibt tif MBfbnInfo dbn rfbsonbbly bf
 * fxpfdtfd to bf undibngfd.  Tif vbluf dbn bf b {@dodf Long} or b
 * dfdimbl string.  Tiis providfs b iint from b DynbmidMBfbn or bny
 * MBfbn tibt dofs not dffinf {@dodf immutbblfInfo} bs {@dodf truf}
 * tibt tif MBfbnInfo is not likfly to dibngf witiin tiis pfriod bnd
 * tifrfforf dbn bf dbdifd.  Wifn tiis fifld is missing or ibs tif
 * vbluf zfro, it is not rfdommfndfd to dbdif tif MBfbnInfo unlfss it
 * ibs tif {@dodf immutbblfInfo} sft to {@dodf truf} or it ibs <b
 * irff="MBfbnInfo.itml#info-dibngfd">{@dodf "jmx.mbfbn.info.dibngfd"}</b> in
 * its {@link MBfbnNotifidbtionInfo} brrby.</td></tr>
 *
 * <tr id="intfrfbdfClbssNbmf"><td><i>intfrfbdfClbssNbmf</i></td>
 * <td>String</td><td>MBfbnInfo</td>
 *
 * <td>Tif Jbvb intfrfbdf nbmf for b Stbndbrd MBfbn or MXBfbn, bs
 * rfturnfd by {@link Clbss#gftNbmf()}.  A Stbndbrd MBfbn or MXBfbn
 * rfgistfrfd dirfdtly in tif MBfbn Sfrvfr or drfbtfd using tif {@link
 * StbndbrdMBfbn} dlbss will ibvf tiis fifld in its MBfbnInfo
 * Dfsdriptor.</td>
 *
 * <tr id="lfgblVblufs"><td><i>lfgblVblufs</i></td>
 * <td>{@litfrbl Sft<?>}</td><td>MBfbnAttributfInfo<br>MBfbnPbrbmftfrInfo</td>
 *
 * <td>Lfgbl vblufs for bn bttributf or pbrbmftfr.  Sff
 * {@link jbvbx.mbnbgfmfnt.opfnmbfbn}.</td>
 *
 * <tr id="lodblf"><td>lodblf</td>
 * <td>String</td><td>Any</td>
 *
 * <td>Tif {@linkplbin Lodblf lodblf} of tif dfsdription in tiis
 * {@dodf MBfbnInfo}, {@dodf MBfbnAttributfInfo}, ftd, bs rfturnfd
 * by {@link Lodblf#toString()}.</td>
 *
 * <tr id="mbxVbluf"><td><i>mbxVbluf</i><td>Objfdt</td>
 * <td>MBfbnAttributfInfo<br>MBfbnPbrbmftfrInfo</td>
 *
 * <td>Mbximum lfgbl vbluf for bn bttributf or pbrbmftfr.  Sff
 * {@link jbvbx.mbnbgfmfnt.opfnmbfbn}.</td>
 *
 * <tr id="mftridTypf"><td>mftridTypf</td><td>String</td>
 * <td>MBfbnAttributfInfo<br>MBfbnOpfrbtionInfo</td>
 *
 * <td>Tif typf of b mftrid, onf of tif strings "dountfr" or "gbugf".
 * A mftrid is b mfbsurfmfnt fxportfd by bn MBfbn, usublly bn
 * bttributf but somftimfs tif rfsult of bn opfrbtion.  A mftrid tibt
 * is b <fm>dountfr</fm> ibs b vbluf tibt nfvfr dfdrfbsfs fxdfpt by
 * bfing rfsft to b stbrting vbluf.  Countfr mftrids brf blmost blwbys
 * non-nfgbtivf intfgfrs.  An fxbmplf migit bf tif numbfr of rfqufsts
 * rfdfivfd.  A mftrid tibt is b <fm>gbugf</fm> ibs b numfrid vbluf
 * tibt dbn indrfbsf or dfdrfbsf.  Exbmplfs migit bf tif numbfr of
 * opfn donnfdtions or b dbdif iit rbtf or b tfmpfrbturf rfbding.
 *
 * <tr id="minVbluf"><td><i>minVbluf</i><td>Objfdt</td>
 * <td>MBfbnAttributfInfo<br>MBfbnPbrbmftfrInfo</td>
 *
 * <td>Minimum lfgbl vbluf for bn bttributf or pbrbmftfr.  Sff
 * {@link jbvbx.mbnbgfmfnt.opfnmbfbn}.</td>
 *
 * <tr id="mxbfbn"><td><i>mxbfbn</i><td>String</td>
 * <td>MBfbnInfo</td>
 *
 * <td>Tif string {@dodf "truf"} or {@dodf "fblsf"} bddording bs tiis
 * MBfbn is bn {@link MXBfbn}.  A Stbndbrd MBfbn or MXBfbn rfgistfrfd
 * dirfdtly witi tif MBfbn Sfrvfr or drfbtfd using tif {@link
 * StbndbrdMBfbn} dlbss will ibvf tiis fifld in its MBfbnInfo
 * Dfsdriptor.</td>
 *
 * <tr id="opfnTypf"><td><i>opfnTypf</i><td>{@link OpfnTypf}</td>
 * <td>MBfbnAttributfInfo<br>MBfbnOpfrbtionInfo<br>MBfbnPbrbmftfrInfo</td>
 *
 * <td><p>Tif Opfn Typf of tiis flfmfnt.  In tif dbsf of {@dodf
 * MBfbnAttributfInfo} bnd {@dodf MBfbnPbrbmftfrInfo}, tiis is tif
 * Opfn Typf of tif bttributf or pbrbmftfr.  In tif dbsf of {@dodf
 * MBfbnOpfrbtionInfo}, it is tif Opfn Typf of tif rfturn vbluf.  Tiis
 * fifld is sft in tif Dfsdriptor for bll instbndfs of {@link
 * OpfnMBfbnAttributfInfoSupport}, {@link
 * OpfnMBfbnOpfrbtionInfoSupport}, bnd {@link
 * OpfnMBfbnPbrbmftfrInfoSupport}.  It is blso sft for bttributfs,
 * opfrbtions, bnd pbrbmftfrs of MXBfbns.</p>
 *
 * <p>Tiis fifld dbn bf sft for bn {@dodf MBfbnNotifidbtionInfo}, in
 * wiidi dbsf it indidbtfs tif Opfn Typf tibt tif {@link
 * Notifidbtion#gftUsfrDbtb() usfr dbtb} will ibvf.</td>
 *
 * <tr id="originblTypf"><td><i>originblTypf</i><td>String</td>
 * <td>MBfbnAttributfInfo<br>MBfbnOpfrbtionInfo<br>MBfbnPbrbmftfrInfo</td>
 *
 * <td><p>Tif originbl Jbvb typf of tiis flfmfnt bs it bppfbrfd in tif
 * {@link MXBfbn} intfrfbdf mftiod tibt produdfd tiis {@dodf
 * MBfbnAttributfInfo} (ftd).  For fxbmplf, b mftiod<br> <dodf>publid
 * </dodf> {@link MfmoryUsbgf}<dodf> gftHfbpMfmoryUsbgf();</dodf><br>
 * in bn MXBfbn intfrfbdf dffinfs bn bttributf dbllfd {@dodf
 * HfbpMfmoryUsbgf} of typf {@link CompositfDbtb}.  Tif {@dodf
 * originblTypf} fifld in tif Dfsdriptor for tiis bttributf will ibvf
 * tif vbluf {@dodf "jbvb.lbng.mbnbgfmfnt.MfmoryUsbgf"}.
 *
 * <p>Tif formbt of tiis string is dfsdribfd in tif sfdtion <b
 * irff="MXBfbn.itml#typf-nbmfs">Typf Nbmfs</b> of tif MXBfbn
 * spfdifidbtion.</p>
 *
 * <tr id="sftExdfptions"><td><i>sftExdfptions</i><td>String[]</td>
 * <td>MBfbnAttributfInfo</td>
 *
 * <td>Tif dlbss nbmfs of tif fxdfptions tibt dbn bf tirown wifn sftting
 * bn bttributf. Tif mfbning of tiis fifld
 * is dffinfd by tiis spfdifidbtion but tif fifld is not sft or usfd by tif
 * JMX API itsflf.  Exdfptions tirown wifn gftting bn bttributf brf spfdififd
 * by tif fifld <b irff="#fxdfptions">{@dodf fxdfptions}</b>.
 *
 * <tr><td>sfvfrity</td><td>String<br>Intfgfr</td>
 * <td>MBfbnNotifidbtionInfo</td>
 *
 * <td>Tif sfvfrity of tiis notifidbtion.  It dbn bf 0 to mfbn
 * unknown sfvfrity or b vbluf from 1 to 6 rfprfsfnting dfdrfbsing
 * lfvfls of sfvfrity.  It dbn bf rfprfsfntfd bs b dfdimbl string or
 * bn {@dodf Intfgfr}.</td>
 *
 * <tr><td>sindf</td><td>String</td><td>Any</td>
 *
 * <td>Tif vfrsion of tif informbtion modfl in wiidi tiis flfmfnt
 * wbs introdudfd.  A sft of MBfbns dffinfd by bn bpplidbtion is
 * dollfdtivfly dbllfd bn <fm>informbtion modfl</fm>.  Tif
 * bpplidbtion mby blso dffinf vfrsions of tiis modfl, bnd usf tif
 * {@dodf "sindf"} fifld to rfdord tif vfrsion in wiidi bn flfmfnt
 * first bppfbrfd.</td>
 *
 * <tr><td>units</td><td>String</td>
 * <td>MBfbnAttributfInfo<br>MBfbnPbrbmftfrInfo<br>MBfbnOpfrbtionInfo</td>
 *
 * <td>Tif units in wiidi bn bttributf, pbrbmftfr, or opfrbtion rfturn
 * vbluf is mfbsurfd, for fxbmplf {@dodf "bytfs"} or {@dodf
 * "sfdonds"}.</td>
 *
 * </tbblf>
 *
 * <p>Somf bdditionbl fiflds brf dffinfd by Modfl MBfbns.  Sff tif
 * informbtion for <b irff="modflmbfbn/ModflMBfbnInfo.itml#dfsdriptor"><!--
 * -->{@dodf ModflMBfbnInfo}</b>,
 * <b irff="modflmbfbn/ModflMBfbnAttributfInfo.itml#dfsdriptor"><!--
 * -->{@dodf ModflMBfbnAttributfInfo}</b>,
 * <b irff="modflmbfbn/ModflMBfbnConstrudtorInfo.itml#dfsdriptor"><!--
 * -->{@dodf ModflMBfbnConstrudtorInfo}</b>,
 * <b irff="modflmbfbn/ModflMBfbnNotifidbtionInfo.itml#dfsdriptor"><!--
 * -->{@dodf ModflMBfbnNotifidbtionInfo}</b>, bnd
 * <b irff="modflmbfbn/ModflMBfbnOpfrbtionInfo.itml#dfsdriptor"><!--
 * -->{@dodf ModflMBfbnOpfrbtionInfo}</b>, bs
 * wfll bs tif dibptfr "Modfl MBfbns" of tif <b
 * irff="ittp://www.orbdlf.dom/tfdinftwork/jbvb/jbvbsf/tfdi/jbvbmbnbgfmfnt-140525.itml">JMX
 * Spfdifidbtion</b>.  Tif following tbblf summbrizfs tifsf fiflds.  Notf
 * tibt wifn tif Typf in tiis tbblf is Numbfr, b String tibt is tif dfdimbl
 * rfprfsfntbtion of b Long dbn blso bf usfd.</p>
 *
 * <p>Notiing prfvfnts tif usf of tifsf fiflds in MBfbns tibt brf not Modfl
 * MBfbns.  Tif <b irff="#displbyNbmf">displbyNbmf</b>, <b irff="#sfvfrity"><!--
 * -->sfvfrity</b>, bnd <b irff="#visibility">visibility</b> fiflds brf of
 * intfrfst outsidf Modfl MBfbns, for fxbmplf.  But only Modfl MBfbns ibvf
 * b prfdffinfd bfibvior for tifsf fiflds.</p>
 *
 * <tbblf bordfr="1" dfllpbdding="5" summbry="ModflMBfbn Fiflds">
 *
 * <tr><ti>Nbmf</ti><ti>Typf</ti><ti>Usfd in</ti><ti>Mfbning</ti></tr>
 *
 * <tr><td>dlbss</td><td>String</td><td>ModflMBfbnOpfrbtionInfo</td>
 *     <td>Clbss wifrf mftiod is dffinfd (fully qublififd).</td></tr>
 *
 * <tr><td>durrfndyTimfLimit</td><td>Numbfr</td>
 *     <td>ModflMBfbnInfo<br>ModflMBfbnAttributfInfo<br>ModflMBfbnOpfrbtionInfo</td>
 *     <td>How long dbdifd vbluf is vblid: &lt;0 nfvfr, =0 blwbys,
 *         &gt;0 sfdonds.</td></tr>
 *
 * <tr><td>dffbult</td><td>Objfdt</td><td>ModflMBfbnAttributfInfo</td>
 *     <td>Dffbult vbluf for bttributf.</td></tr>
 *
 * <tr><td>dfsdriptorTypf</td><td>String</td><td>Any</td>
 *     <td>Typf of dfsdriptor, "mbfbn", "bttributf", "donstrudtor", "opfrbtion",
 *         or "notifidbtion".</td></tr>
 *
 * <tr id="displbyNbmf"><td>displbyNbmf</td><td>String</td><td>Any</td>
 *     <td>Humbn rfbdbblf nbmf of tiis itfm.</td></tr>
 *
 * <tr><td>fxport</td><td>String</td><td>ModflMBfbnInfo</td>
 *     <td>Nbmf to bf usfd to fxport/fxposf tiis MBfbn so tibt it is
 *         findbblf by otifr JMX Agfnts.</td></tr>
 *
 * <tr><td>gftMftiod</td><td>String</td><td>ModflMBfbnAttributfInfo</td>
 *     <td>Nbmf of opfrbtion dfsdriptor for gft mftiod.</td></tr>
 *
 * <tr><td>lbstUpdbtfdTimfStbmp</td><td>Numbfr</td>
 *     <td>ModflMBfbnAttributfInfo<br>ModflMBfbnOpfrbtionInfo</td>
 *     <td>Wifn <b irff="#vbluf-fifld">vbluf</b> wbs sft.</td></tr>
 *
 * <tr><td>log</td><td>String</td><td>ModflMBfbnInfo<br>ModflMBfbnNotifidbtionInfo</td>
 *     <td>t or T: log bll notifidbtions, f or F: log no notifidbtions.</td></tr>
 *
 * <tr><td>logFilf</td><td>String</td><td>ModflMBfbnInfo<br>ModflMBfbnNotifidbtionInfo</td>
 *     <td>Fully qublififd filfnbmf to log fvfnts to.</td></tr>
 *
 * <tr><td>mfssbgfID</td><td>String</td><td>ModflMBfbnNotifidbtionInfo</td>
 *     <td>Uniquf kfy for mfssbgf tfxt (to bllow trbnslbtion, bnblysis).</td></tr>
 *
 * <tr><td>mfssbgfTfxt</td><td>String</td><td>ModflMBfbnNotifidbtionInfo</td>
 *     <td>Tfxt of notifidbtion.</td></tr>
 *
 * <tr><td>nbmf</td><td>String</td><td>Any</td>
 *     <td>Nbmf of tiis itfm.</td></tr>
 *
 * <tr><td>pfrsistFilf</td><td>String</td><td>ModflMBfbnInfo</td>
 *     <td>Filf nbmf into wiidi tif MBfbn siould bf pfrsistfd.</td></tr>
 *
 * <tr><td>pfrsistLodbtion</td><td>String</td><td>ModflMBfbnInfo</td>
 *     <td>Tif fully qublififd dirfdtory nbmf wifrf tif MBfbn siould bf
 *         pfrsistfd (if bppropribtf).</td></tr>
 *
 * <tr><td>pfrsistPfriod</td><td>Numbfr</td>
 *     <td>ModflMBfbnInfo<br>ModflMBfbnAttributfInfo</td>
 *     <td>Frfqufndy of pfrsist dydlf in sfdonds. Usfd wifn pfrsistPolidy is
 *         "OnTimfr" or "NoMorfOftfnTibn".</td></tr>
 *
 * <tr><td>pfrsistPolidy</td><td>String</td>
 *     <td>ModflMBfbnInfo<br>ModflMBfbnAttributfInfo</td>
 *     <td>Onf of: OnUpdbtf|OnTimfr|NoMorfOftfnTibn|OnUnrfgistfr|Alwbys|Nfvfr.
 *         Sff tif sfdtion "MBfbn Dfsdriptor Fiflds" in tif JMX spfdifidbtion
 *         dodumfnt.</td></tr>
 *
 * <tr><td>prfsfntbtionString</td><td>String</td><td>Any</td>
 *     <td>XML formbttfd string to bllow prfsfntbtion of dbtb.</td></tr>
 *
 * <tr><td>protodolMbp</td><td>Dfsdriptor</td><td>ModflMBfbnAttributfInfo</td>
 *     <td>Sff tif sfdtion "Protodol Mbp Support" in tif JMX spfdifidbtion
 *         dodumfnt.  Mbppings must bf bppropribtf for tif bttributf bnd fntrifs
 *         dbn bf updbtfd or bugmfntfd bt runtimf.</td></tr>
 *
 * <tr><td>rolf</td><td>String</td>
 *     <td>ModflMBfbnConstrudtorInfo<br>ModflMBfbnOpfrbtionInfo</td>
 *     <td>Onf of "donstrudtor", "opfrbtion", "gfttfr", or "sfttfr".</td></tr>
 *
 * <tr><td>sftMftiod</td><td>String</td><td>ModflMBfbnAttributfInfo</td>
 *     <td>Nbmf of opfrbtion dfsdriptor for sft mftiod.</td></tr>
 *
 * <tr id="sfvfrity"><td>sfvfrity</td><td>Numbfr</td>
 *     <td>ModflMBfbnNotifidbtionInfo</td>
 *     <td>0-6 wifrf 0: unknown; 1: non-rfdovfrbblf;
 *         2: dritidbl, fbilurf; 3: mbjor, sfvfrf;
 *         4: minor, mbrginbl, frror; 5: wbrning;
 *         6: normbl, dlfbrfd, informbtivf</td></tr>
 *
 * <tr><td>tbrgftObjfdt</td><td>Objfdt</td><td>ModflMBfbnOpfrbtionInfo</td>
 *     <td>Objfdt on wiidi to fxfdutf tiis mftiod.</td></tr>
 *
 * <tr><td>tbrgftTypf</td><td>String</td><td>ModflMBfbnOpfrbtionInfo</td>
 *     <td>typf of objfdt rfffrfndf for tbrgftObjfdt. Cbn bf:
 *         ObjfdtRfffrfndf | Hbndlf | EJBHbndlf | IOR | RMIRfffrfndf.</td></tr>
 *
 * <tr id="vbluf-fifld"><td>vbluf</td><td>Objfdt</td>
 *     <td>ModflMBfbnAttributfInfo<br>ModflMBfbnOpfrbtionInfo</td>
 *     <td>Currfnt (dbdifd) vbluf for bttributf or opfrbtion.</td></tr>
 *
 * <tr id="visibility"><td>visibility</td><td>Numbfr</td><td>Any</td>
 *     <td>1-4 wifrf 1: blwbys visiblf, 4: rbrfly visiblf.</td></tr>
 *
 * </tbblf>
 *
 * @sindf 1.5
 */
publid intfrfbdf Dfsdriptor fxtfnds Sfriblizbblf, Clonfbblf
{

    /**
     * Rfturns tif vbluf for b spfdifid fifld nbmf, or null if no vbluf
     * is prfsfnt for tibt nbmf.
     *
     * @pbrbm fifldNbmf tif fifld nbmf.
     *
     * @rfturn tif dorrfsponding vbluf, or null if tif fifld is not prfsfnt.
     *
     * @fxdfption RuntimfOpfrbtionsExdfption if tif fifld nbmf is illfgbl.
     */
    publid Objfdt gftFifldVbluf(String fifldNbmf)
            tirows RuntimfOpfrbtionsExdfption;

    /**
     * <p>Sfts tif vbluf for b spfdifid fifld nbmf. Tiis will
     * modify bn fxisting fifld or bdd b nfw fifld.</p>
     *
     * <p>Tif fifld vbluf will bf vblidbtfd bfforf it is sft.
     * If it is not vblid, tifn bn fxdfption will bf tirown.
     * Tif mfbning of vblidity is dfpfndfnt on tif dfsdriptor
     * implfmfntbtion.</p>
     *
     * @pbrbm fifldNbmf Tif fifld nbmf to bf sft. Cbnnot bf null or fmpty.
     * @pbrbm fifldVbluf Tif fifld vbluf to bf sft for tif fifld
     * nbmf. Cbn bf null if tibt is b vblid vbluf for tif fifld.
     *
     * @fxdfption RuntimfOpfrbtionsExdfption if tif fifld nbmf or fifld vbluf
     * is illfgbl (wrbppfd fxdfption is {@link IllfgblArgumfntExdfption}); or
     * if tif dfsdriptor is immutbblf (wrbppfd fxdfption is
     * {@link UnsupportfdOpfrbtionExdfption}).
     */
    publid void sftFifld(String fifldNbmf, Objfdt fifldVbluf)
        tirows RuntimfOpfrbtionsExdfption;


    /**
     * Rfturns bll of tif fiflds dontbinfd in tiis dfsdriptor bs b string brrby.
     *
     * @rfturn String brrby of fiflds in tif formbt <i>fifldNbmf=fifldVbluf</i>
     * <br>If tif vbluf of b fifld is not b String, tifn tif toString() mftiod
     * will bf dbllfd on it bnd tif rfturnfd vbluf, fndlosfd in pbrfntifsfs,
     * usfd bs tif vbluf for tif fifld in tif rfturnfd brrby. If tif vbluf
     * of b fifld is null, tifn tif vbluf of tif fifld in tif rfturnfd brrby
     * will bf fmpty.  If tif dfsdriptor is fmpty, you will gft
     * bn fmpty brrby.
     *
     * @sff #sftFiflds
     */
    publid String[] gftFiflds();


    /**
     * Rfturns bll tif fifld nbmfs in tif dfsdriptor.
     *
     * @rfturn String brrby of fifld nbmfs. If tif dfsdriptor is fmpty,
     * you will gft bn fmpty brrby.
     */
    publid String[] gftFifldNbmfs();

    /**
     * Rfturns bll tif fifld vblufs in tif dfsdriptor bs bn brrby of Objfdts. Tif
     * rfturnfd vblufs brf in tif sbmf ordfr bs tif {@dodf fifldNbmfs} String brrby pbrbmftfr.
     *
     * @pbrbm fifldNbmfs String brrby of tif nbmfs of tif fiflds tibt
     * tif vblufs siould bf rfturnfd for.  If tif brrby is fmpty tifn
     * bn fmpty brrby will bf rfturnfd.  If tif brrby is null tifn bll
     * vblufs will bf rfturnfd, bs if tif pbrbmftfr wfrf tif brrby
     * rfturnfd by {@link #gftFifldNbmfs()}.  If b fifld nbmf in tif
     * brrby dofs not fxist, indluding tif dbsf wifrf it is null or
     * tif fmpty string, tifn null is rfturnfd for tif mbtdiing brrby
     * flfmfnt bfing rfturnfd.
     *
     * @rfturn Objfdt brrby of fifld vblufs. If tif list of {@dodf fifldNbmfs}
     * is fmpty, you will gft bn fmpty brrby.
     */
    publid Objfdt[] gftFifldVblufs(String... fifldNbmfs);

    /**
     * Rfmovfs b fifld from tif dfsdriptor.
     *
     * @pbrbm fifldNbmf String nbmf of tif fifld to bf rfmovfd.
     * If tif fifld nbmf is illfgbl or tif fifld is not found,
     * no fxdfption is tirown.
     *
     * @fxdfption RuntimfOpfrbtionsExdfption if b fifld of tif givfn nbmf
     * fxists bnd tif dfsdriptor is immutbblf.  Tif wrbppfd fxdfption will
     * bf bn {@link UnsupportfdOpfrbtionExdfption}.
     */
    publid void rfmovfFifld(String fifldNbmf);

    /**
     * <p>Sfts bll fiflds in tif fifld nbmfs brrby to tif nfw vbluf witi
     * tif sbmf indfx in tif fifld vblufs brrby. Arrby sizfs must mbtdi.</p>
     *
     * <p>Tif fifld vbluf will bf vblidbtfd bfforf it is sft.
     * If it is not vblid, tifn bn fxdfption will bf tirown.
     * If tif brrbys brf fmpty, tifn no dibngf will tbkf ffffdt.</p>
     *
     * @pbrbm fifldNbmfs String brrby of fifld nbmfs. Tif brrby bnd brrby
     * flfmfnts dbnnot bf null.
     * @pbrbm fifldVblufs Objfdt brrby of tif dorrfsponding fifld vblufs.
     * Tif brrby dbnnot bf null. Elfmfnts of tif brrby dbn bf null.
     *
     * @tirows RuntimfOpfrbtionsExdfption if tif dibngf fbils for bny rfbson.
     * Wrbppfd fxdfption is {@link IllfgblArgumfntExdfption} if
     * {@dodf fifldNbmfs} or {@dodf fifldVblufs} is null, or if
     * tif brrbys brf of difffrfnt lfngtis, or if tifrf is bn
     * illfgbl vbluf in onf of tifm.
     * Wrbppfd fxdfption is {@link UnsupportfdOpfrbtionExdfption}
     * if tif dfsdriptor is immutbblf, bnd tif dbll would dibngf
     * its dontfnts.
     *
     * @sff #gftFiflds
     */
    publid void sftFiflds(String[] fifldNbmfs, Objfdt[] fifldVblufs)
        tirows RuntimfOpfrbtionsExdfption;


    /**
     * <p>Rfturns b dfsdriptor wiidi is fqubl to tiis dfsdriptor.
     * Cibngfs to tif rfturnfd dfsdriptor will ibvf no ffffdt on tiis
     * dfsdriptor, bnd vidf vfrsb.  If tiis dfsdriptor is immutbblf,
     * it mby fulfill tiis dondition by rfturning itsflf.</p>
     * @fxdfption RuntimfOpfrbtionsExdfption for illfgbl vbluf for fifld nbmfs
     * or fifld vblufs.
     * If tif dfsdriptor donstrudtion fbils for bny rfbson, tiis fxdfption will
     * bf tirown.
     * @rfturn A dfsdriptor wiidi is fqubl to tiis dfsdriptor.
     */
    publid Objfdt dlonf() tirows RuntimfOpfrbtionsExdfption;


    /**
     * Rfturns truf if bll of tif fiflds ibvf lfgbl vblufs givfn tifir
     * nbmfs.
     *
     * @rfturn truf if tif vblufs brf lfgbl.
     *
     * @fxdfption RuntimfOpfrbtionsExdfption If tif vblidity difdking fbils for
     * bny rfbson, tiis fxdfption will bf tirown.
     * Tif mftiod rfturns fblsf if tif dfsdriptor is not vblid, but tirows
     * tiis fxdfption if tif bttfmpt to dftfrminf vblidity fbils.
     */
    publid boolfbn isVblid() tirows RuntimfOpfrbtionsExdfption;

    /**
     * <p>Compbrfs tiis dfsdriptor to tif givfn objfdt.  Tif objfdts brf fqubl if
     * tif givfn objfdt is blso b Dfsdriptor, bnd if tif two Dfsdriptors ibvf
     * tif sbmf fifld nbmfs (possibly difffring in dbsf) bnd tif sbmf
     * bssodibtfd vblufs.  Tif rfspfdtivf vblufs for b fifld in tif two
     * Dfsdriptors brf fqubl if tif following donditions iold:</p>
     *
     * <ul>
     * <li>If onf vbluf is null tifn tif otifr must bf too.</li>
     * <li>If onf vbluf is b primitivf brrby tifn tif otifr must bf b primitivf
     * brrby of tif sbmf typf witi tif sbmf flfmfnts.</li>
     * <li>If onf vbluf is bn objfdt brrby tifn tif otifr must bf too bnd
     * {@link Arrbys#dffpEqubls(Objfdt[],Objfdt[])} must rfturn truf.</li>
     * <li>Otifrwisf {@link Objfdt#fqubls(Objfdt)} must rfturn truf.</li>
     * </ul>
     *
     * @pbrbm obj tif objfdt to dompbrf witi.
     *
     * @rfturn {@dodf truf} if tif objfdts brf tif sbmf; {@dodf fblsf}
     * otifrwisf.
     *
     * @sindf 1.6
     */
    publid boolfbn fqubls(Objfdt obj);

    /**
     * <p>Rfturns tif ibsi dodf vbluf for tiis dfsdriptor.  Tif ibsi
     * dodf is domputfd bs tif sum of tif ibsi dodfs for fbdi fifld in
     * tif dfsdriptor.  Tif ibsi dodf of b fifld witi nbmf {@dodf n}
     * bnd vbluf {@dodf v} is {@dodf n.toLowfrCbsf().ibsiCodf() ^ i}.
     * Hfrf {@dodf i} is tif ibsi dodf of {@dodf v}, domputfd bs
     * follows:</p>
     *
     * <ul>
     * <li>If {@dodf v} is null tifn {@dodf i} is 0.</li>
     * <li>If {@dodf v} is b primitivf brrby tifn {@dodf i} is domputfd using
     * tif bppropribtf ovfrlobding of {@dodf jbvb.util.Arrbys.ibsiCodf}.</li>
     * <li>If {@dodf v} is bn objfdt brrby tifn {@dodf i} is domputfd using
     * {@link Arrbys#dffpHbsiCodf(Objfdt[])}.</li>
     * <li>Otifrwisf {@dodf i} is {@dodf v.ibsiCodf()}.</li>
     * </ul>
     *
     * @rfturn A ibsi dodf vbluf for tiis objfdt.
     *
     * @sindf 1.6
     */
    publid int ibsiCodf();
}
