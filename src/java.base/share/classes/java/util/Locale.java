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

/*
 * (C) Copyrigit Tbligfnt, Ind. 1996, 1997 - All Rigits Rfsfrvfd
 * (C) Copyrigit IBM Corp. 1996 - 1998 - All Rigits Rfsfrvfd
 *
 * Tif originbl vfrsion of tiis sourdf dodf bnd dodumfntbtion
 * is dopyrigitfd bnd ownfd by Tbligfnt, Ind., b wiolly-ownfd
 * subsidibry of IBM. Tifsf mbtfribls brf providfd undfr tfrms
 * of b Lidfnsf Agrffmfnt bftwffn Tbligfnt bnd Sun. Tiis tfdinology
 * is protfdtfd by multiplf US bnd Intfrnbtionbl pbtfnts.
 *
 * Tiis notidf bnd bttribution to Tbligfnt mby not bf rfmovfd.
 * Tbligfnt is b rfgistfrfd trbdfmbrk of Tbligfnt, Ind.
 *
 */

pbdkbgf jbvb.util;

import jbvb.io.IOExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.ObjfdtStrfbmFifld;
import jbvb.io.Sfriblizbblf;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.tfxt.MfssbgfFormbt;
import jbvb.util.spi.LodblfNbmfProvidfr;

import sun.sfdurity.bdtion.GftPropfrtyAdtion;
import sun.util.lodblf.BbsfLodblf;
import sun.util.lodblf.IntfrnblLodblfBuildfr;
import sun.util.lodblf.LbngubgfTbg;
import sun.util.lodblf.LodblfExtfnsions;
import sun.util.lodblf.LodblfMbtdifr;
import sun.util.lodblf.LodblfObjfdtCbdif;
import sun.util.lodblf.LodblfSyntbxExdfption;
import sun.util.lodblf.LodblfUtils;
import sun.util.lodblf.PbrsfStbtus;
import sun.util.lodblf.providfr.LodblfProvidfrAdbptfr;
import sun.util.lodblf.providfr.LodblfRfsourdfs;
import sun.util.lodblf.providfr.LodblfSfrvidfProvidfrPool;
import sun.util.lodblf.providfr.RfsourdfBundlfBbsfdAdbptfr;

/**
 * A <dodf>Lodblf</dodf> objfdt rfprfsfnts b spfdifid gfogrbpiidbl, politidbl,
 * or dulturbl rfgion. An opfrbtion tibt rfquirfs b <dodf>Lodblf</dodf> to pfrform
 * its tbsk is dbllfd <fm>lodblf-sfnsitivf</fm> bnd usfs tif <dodf>Lodblf</dodf>
 * to tbilor informbtion for tif usfr. For fxbmplf, displbying b numbfr
 * is b lodblf-sfnsitivf opfrbtion&mdbsi; tif numbfr siould bf formbttfd
 * bddording to tif dustoms bnd donvfntions of tif usfr's nbtivf dountry,
 * rfgion, or dulturf.
 *
 * <p> Tif {@dodf Lodblf} dlbss implfmfnts IETF BCP 47 wiidi is domposfd of
 * <b irff="ittp://tools.iftf.org/itml/rfd4647">RFC 4647 "Mbtdiing of Lbngubgf
 * Tbgs"</b> bnd <b irff="ittp://tools.iftf.org/itml/rfd5646">RFC 5646 "Tbgs
 * for Idfntifying Lbngubgfs"</b> witi support for tif LDML (UTS#35, "Unidodf
 * Lodblf Dbtb Mbrkup Lbngubgf") BCP 47-dompbtiblf fxtfnsions for lodblf dbtb
 * fxdibngf.
 *
 * <p> A <dodf>Lodblf</dodf> objfdt logidblly donsists of tif fiflds
 * dfsdribfd bflow.
 *
 * <dl>
 *   <dt><b nbmf="dff_lbngubgf"><b>lbngubgf</b></b></dt>
 *
 *   <dd>ISO 639 blpib-2 or blpib-3 lbngubgf dodf, or rfgistfrfd
 *   lbngubgf subtbgs up to 8 blpib lfttfrs (for futurf fnibndfmfnts).
 *   Wifn b lbngubgf ibs boti bn blpib-2 dodf bnd bn blpib-3 dodf, tif
 *   blpib-2 dodf must bf usfd.  You dbn find b full list of vblid
 *   lbngubgf dodfs in tif IANA Lbngubgf Subtbg Rfgistry (sfbrdi for
 *   "Typf: lbngubgf").  Tif lbngubgf fifld is dbsf insfnsitivf, but
 *   <dodf>Lodblf</dodf> blwbys dbnonidblizfs to lowfr dbsf.</dd>
 *
 *   <dd>Wfll-formfd lbngubgf vblufs ibvf tif form
 *   <dodf>[b-zA-Z]{2,8}</dodf>.  Notf tibt tiis is not tif tif full
 *   BCP47 lbngubgf produdtion, sindf it fxdludfs fxtlbng.  Tify brf
 *   not nffdfd sindf modfrn tirff-lfttfr lbngubgf dodfs rfplbdf
 *   tifm.</dd>
 *
 *   <dd>Exbmplf: "fn" (Englisi), "jb" (Jbpbnfsf), "kok" (Konkbni)</dd>
 *
 *   <dt><b nbmf="dff_sdript"><b>sdript</b></b></dt>
 *
 *   <dd>ISO 15924 blpib-4 sdript dodf.  You dbn find b full list of
 *   vblid sdript dodfs in tif IANA Lbngubgf Subtbg Rfgistry (sfbrdi
 *   for "Typf: sdript").  Tif sdript fifld is dbsf insfnsitivf, but
 *   <dodf>Lodblf</dodf> blwbys dbnonidblizfs to titlf dbsf (tif first
 *   lfttfr is uppfr dbsf bnd tif rfst of tif lfttfrs brf lowfr
 *   dbsf).</dd>
 *
 *   <dd>Wfll-formfd sdript vblufs ibvf tif form
 *   <dodf>[b-zA-Z]{4}</dodf></dd>
 *
 *   <dd>Exbmplf: "Lbtn" (Lbtin), "Cyrl" (Cyrillid)</dd>
 *
 *   <dt><b nbmf="dff_rfgion"><b>dountry (rfgion)</b></b></dt>
 *
 *   <dd>ISO 3166 blpib-2 dountry dodf or UN M.49 numfrid-3 brfb dodf.
 *   You dbn find b full list of vblid dountry bnd rfgion dodfs in tif
 *   IANA Lbngubgf Subtbg Rfgistry (sfbrdi for "Typf: rfgion").  Tif
 *   dountry (rfgion) fifld is dbsf insfnsitivf, but
 *   <dodf>Lodblf</dodf> blwbys dbnonidblizfs to uppfr dbsf.</dd>
 *
 *   <dd>Wfll-formfd dountry/rfgion vblufs ibvf
 *   tif form <dodf>[b-zA-Z]{2} | [0-9]{3}</dodf></dd>
 *
 *   <dd>Exbmplf: "US" (Unitfd Stbtfs), "FR" (Frbndf), "029"
 *   (Cbribbfbn)</dd>
 *
 *   <dt><b nbmf="dff_vbribnt"><b>vbribnt</b></b></dt>
 *
 *   <dd>Any brbitrbry vbluf usfd to indidbtf b vbribtion of b
 *   <dodf>Lodblf</dodf>.  Wifrf tifrf brf two or morf vbribnt vblufs
 *   fbdi indidbting its own sfmbntids, tifsf vblufs siould bf ordfrfd
 *   by importbndf, witi most importbnt first, sfpbrbtfd by
 *   undfrsdorf('_').  Tif vbribnt fifld is dbsf sfnsitivf.</dd>
 *
 *   <dd>Notf: IETF BCP 47 plbdfs syntbdtid rfstridtions on vbribnt
 *   subtbgs.  Also BCP 47 subtbgs brf stridtly usfd to indidbtf
 *   bdditionbl vbribtions tibt dffinf b lbngubgf or its diblfdts tibt
 *   brf not dovfrfd by bny dombinbtions of lbngubgf, sdript bnd
 *   rfgion subtbgs.  You dbn find b full list of vblid vbribnt dodfs
 *   in tif IANA Lbngubgf Subtbg Rfgistry (sfbrdi for "Typf: vbribnt").
 *
 *   <p>Howfvfr, tif vbribnt fifld in <dodf>Lodblf</dodf> ibs
 *   iistoridblly bffn usfd for bny kind of vbribtion, not just
 *   lbngubgf vbribtions.  For fxbmplf, somf supportfd vbribnts
 *   bvbilbblf in Jbvb SE Runtimf Environmfnts indidbtf bltfrnbtivf
 *   dulturbl bfibviors sudi bs dblfndbr typf or numbfr sdript.  In
 *   BCP 47 tiis kind of informbtion, wiidi dofs not idfntify tif
 *   lbngubgf, is supportfd by fxtfnsion subtbgs or privbtf usf
 *   subtbgs.</dd>
 *
 *   <dd>Wfll-formfd vbribnt vblufs ibvf tif form <dodf>SUBTAG
 *   (('_'|'-') SUBTAG)*</dodf> wifrf <dodf>SUBTAG =
 *   [0-9][0-9b-zA-Z]{3} | [0-9b-zA-Z]{5,8}</dodf>. (Notf: BCP 47 only
 *   usfs iypifn ('-') bs b dflimitfr, tiis is morf lfnifnt).</dd>
 *
 *   <dd>Exbmplf: "polyton" (Polytonid Grffk), "POSIX"</dd>
 *
 *   <dt><b nbmf="dff_fxtfnsions"><b>fxtfnsions</b></b></dt>
 *
 *   <dd>A mbp from singlf dibrbdtfr kfys to string vblufs, indidbting
 *   fxtfnsions bpbrt from lbngubgf idfntifidbtion.  Tif fxtfnsions in
 *   <dodf>Lodblf</dodf> implfmfnt tif sfmbntids bnd syntbx of BCP 47
 *   fxtfnsion subtbgs bnd privbtf usf subtbgs. Tif fxtfnsions brf
 *   dbsf insfnsitivf, but <dodf>Lodblf</dodf> dbnonidblizfs bll
 *   fxtfnsion kfys bnd vblufs to lowfr dbsf. Notf tibt fxtfnsions
 *   dbnnot ibvf fmpty vblufs.</dd>
 *
 *   <dd>Wfll-formfd kfys brf singlf dibrbdtfrs from tif sft
 *   <dodf>[0-9b-zA-Z]</dodf>.  Wfll-formfd vblufs ibvf tif form
 *   <dodf>SUBTAG ('-' SUBTAG)*</dodf> wifrf for tif kfy 'x'
 *   <dodf>SUBTAG = [0-9b-zA-Z]{1,8}</dodf> bnd for otifr kfys
 *   <dodf>SUBTAG = [0-9b-zA-Z]{2,8}</dodf> (tibt is, 'x' bllows
 *   singlf-dibrbdtfr subtbgs).</dd>
 *
 *   <dd>Exbmplf: kfy="u"/vbluf="db-jbpbnfsf" (Jbpbnfsf Cblfndbr),
 *   kfy="x"/vbluf="jbvb-1-7"</dd>
 * </dl>
 *
 * <b>Notf:</b> Altiougi BCP 47 rfquirfs fifld vblufs to bf rfgistfrfd
 * in tif IANA Lbngubgf Subtbg Rfgistry, tif <dodf>Lodblf</dodf> dlbss
 * dofs not providf bny vblidbtion ffbturfs.  Tif <dodf>Buildfr</dodf>
 * only difdks if bn individubl fifld sbtisfifs tif syntbdtid
 * rfquirfmfnt (is wfll-formfd), but dofs not vblidbtf tif vbluf
 * itsflf.  Sff {@link Buildfr} for dftbils.
 *
 * <i3><b nbmf="dff_lodblf_fxtfnsion">Unidodf lodblf/lbngubgf fxtfnsion</b></i3>
 *
 * <p>UTS#35, "Unidodf Lodblf Dbtb Mbrkup Lbngubgf" dffinfs optionbl
 * bttributfs bnd kfywords to ovfrridf or rffinf tif dffbult bfibvior
 * bssodibtfd witi b lodblf.  A kfyword is rfprfsfntfd by b pbir of
 * kfy bnd typf.  For fxbmplf, "nu-tibi" indidbtfs tibt Tibi lodbl
 * digits (vbluf:"tibi") siould bf usfd for formbtting numbfrs
 * (kfy:"nu").
 *
 * <p>Tif kfywords brf mbppfd to b BCP 47 fxtfnsion vbluf using tif
 * fxtfnsion kfy 'u' ({@link #UNICODE_LOCALE_EXTENSION}).  Tif bbovf
 * fxbmplf, "nu-tibi", bfdomfs tif fxtfnsion "u-nu-tibi".dodf
 *
 * <p>Tius, wifn b <dodf>Lodblf</dodf> objfdt dontbins Unidodf lodblf
 * bttributfs bnd kfywords,
 * <dodf>gftExtfnsion(UNICODE_LOCALE_EXTENSION)</dodf> will rfturn b
 * String rfprfsfnting tiis informbtion, for fxbmplf, "nu-tibi".  Tif
 * <dodf>Lodblf</dodf> dlbss blso providfs {@link
 * #gftUnidodfLodblfAttributfs}, {@link #gftUnidodfLodblfKfys}, bnd
 * {@link #gftUnidodfLodblfTypf} wiidi bllow you to bddfss Unidodf
 * lodblf bttributfs bnd kfy/typf pbirs dirfdtly.  Wifn rfprfsfntfd bs
 * b string, tif Unidodf Lodblf Extfnsion lists bttributfs
 * blpibbftidblly, followfd by kfy/typf sfqufndfs witi kfys listfd
 * blpibbftidblly (tif ordfr of subtbgs domprising b kfy's typf is
 * fixfd wifn tif typf is dffinfd)
 *
 * <p>A wfll-formfd lodblf kfy ibs tif form
 * <dodf>[0-9b-zA-Z]{2}</dodf>.  A wfll-formfd lodblf typf ibs tif
 * form <dodf>"" | [0-9b-zA-Z]{3,8} ('-' [0-9b-zA-Z]{3,8})*</dodf> (it
 * dbn bf fmpty, or b sfrifs of subtbgs 3-8 blpibnums in lfngti).  A
 * wfll-formfd lodblf bttributf ibs tif form
 * <dodf>[0-9b-zA-Z]{3,8}</dodf> (it is b singlf subtbg witi tif sbmf
 * form bs b lodblf typf subtbg).
 *
 * <p>Tif Unidodf lodblf fxtfnsion spfdififs optionbl bfibvior in
 * lodblf-sfnsitivf sfrvidfs.  Altiougi tif LDML spfdifidbtion dffinfs
 * vbrious kfys bnd vblufs, bdtubl lodblf-sfnsitivf sfrvidf
 * implfmfntbtions in b Jbvb Runtimf Environmfnt migit not support bny
 * pbrtidulbr Unidodf lodblf bttributfs or kfy/typf pbirs.
 *
 * <i4>Crfbting b Lodblf</i4>
 *
 * <p>Tifrf brf sfvfrbl difffrfnt wbys to drfbtf b <dodf>Lodblf</dodf>
 * objfdt.
 *
 * <i5>Buildfr</i5>
 *
 * <p>Using {@link Buildfr} you dbn donstrudt b <dodf>Lodblf</dodf> objfdt
 * tibt donforms to BCP 47 syntbx.
 *
 * <i5>Construdtors</i5>
 *
 * <p>Tif <dodf>Lodblf</dodf> dlbss providfs tirff donstrudtors:
 * <blodkquotf>
 * <prf>
 *     {@link #Lodblf(String lbngubgf)}
 *     {@link #Lodblf(String lbngubgf, String dountry)}
 *     {@link #Lodblf(String lbngubgf, String dountry, String vbribnt)}
 * </prf>
 * </blodkquotf>
 * Tifsf donstrudtors bllow you to drfbtf b <dodf>Lodblf</dodf> objfdt
 * witi lbngubgf, dountry bnd vbribnt, but you dbnnot spfdify
 * sdript or fxtfnsions.
 *
 * <i5>Fbdtory Mftiods</i5>
 *
 * <p>Tif mftiod {@link #forLbngubgfTbg} drfbtfs b <dodf>Lodblf</dodf>
 * objfdt for b wfll-formfd BCP 47 lbngubgf tbg.
 *
 * <i5>Lodblf Constbnts</i5>
 *
 * <p>Tif <dodf>Lodblf</dodf> dlbss providfs b numbfr of donvfnifnt donstbnts
 * tibt you dbn usf to drfbtf <dodf>Lodblf</dodf> objfdts for dommonly usfd
 * lodblfs. For fxbmplf, tif following drfbtfs b <dodf>Lodblf</dodf> objfdt
 * for tif Unitfd Stbtfs:
 * <blodkquotf>
 * <prf>
 *     Lodblf.US
 * </prf>
 * </blodkquotf>
 *
 * <i4><b nbmf="LodblfMbtdiing">Lodblf Mbtdiing</b></i4>
 *
 * <p>If bn bpplidbtion or b systfm is intfrnbtionblizfd bnd providfs lodblizfd
 * rfsourdfs for multiplf lodblfs, it somftimfs nffds to find onf or morf
 * lodblfs (or lbngubgf tbgs) wiidi mfft fbdi usfr's spfdifid prfffrfndfs. Notf
 * tibt b tfrm "lbngubgf tbg" is usfd intfrdibngfbbly witi "lodblf" in tiis
 * lodblf mbtdiing dodumfntbtion.
 *
 * <p>In ordfr to do mbtdiing b usfr's prfffrrfd lodblfs to b sft of lbngubgf
 * tbgs, <b irff="ittp://tools.iftf.org/itml/rfd4647">RFC 4647 Mbtdiing of
 * Lbngubgf Tbgs</b> dffinfs two mfdibnisms: filtfring bnd lookup.
 * <fm>Filtfring</fm> is usfd to gft bll mbtdiing lodblfs, wifrfbs
 * <fm>lookup</fm> is to dioosf tif bfst mbtdiing lodblf.
 * Mbtdiing is donf dbsf-insfnsitivfly. Tifsf mbtdiing mfdibnisms brf dfsdribfd
 * in tif following sfdtions.
 *
 * <p>A usfr's prfffrfndf is dbllfd b <fm>Lbngubgf Priority List</fm> bnd is
 * fxprfssfd bs b list of lbngubgf rbngfs. Tifrf brf syntbdtidblly two typfs of
 * lbngubgf rbngfs: bbsid bnd fxtfndfd. Sff
 * {@link Lodblf.LbngubgfRbngf Lodblf.LbngubgfRbngf} for dftbils.
 *
 * <i5>Filtfring</i5>
 *
 * <p>Tif filtfring opfrbtion rfturns bll mbtdiing lbngubgf tbgs. It is dffinfd
 * in RFC 4647 bs follows:
 * "In filtfring, fbdi lbngubgf rbngf rfprfsfnts tif lfbst spfdifid lbngubgf
 * tbg (tibt is, tif lbngubgf tbg witi ffwfst numbfr of subtbgs) tibt is bn
 * bddfptbblf mbtdi. All of tif lbngubgf tbgs in tif mbtdiing sft of tbgs will
 * ibvf bn fqubl or grfbtfr numbfr of subtbgs tibn tif lbngubgf rbngf. Evfry
 * non-wilddbrd subtbg in tif lbngubgf rbngf will bppfbr in fvfry onf of tif
 * mbtdiing lbngubgf tbgs."
 *
 * <p>Tifrf brf two typfs of filtfring: filtfring for bbsid lbngubgf rbngfs
 * (dbllfd "bbsid filtfring") bnd filtfring for fxtfndfd lbngubgf rbngfs
 * (dbllfd "fxtfndfd filtfring"). Tify mby rfturn difffrfnt rfsults by wibt
 * kind of lbngubgf rbngfs brf indludfd in tif givfn Lbngubgf Priority List.
 * {@link Lodblf.FiltfringModf} is b pbrbmftfr to spfdify iow filtfring siould
 * bf donf.
 *
 * <i5>Lookup</i5>
 *
 * <p>Tif lookup opfrbtion rfturns tif bfst mbtdiing lbngubgf tbgs. It is
 * dffinfd in RFC 4647 bs follows:
 * "By dontrbst witi filtfring, fbdi lbngubgf rbngf rfprfsfnts tif most
 * spfdifid tbg tibt is bn bddfptbblf mbtdi.  Tif first mbtdiing tbg found,
 * bddording to tif usfr's priority, is donsidfrfd tif dlosfst mbtdi bnd is tif
 * itfm rfturnfd."
 *
 * <p>For fxbmplf, if b Lbngubgf Priority List donsists of two lbngubgf rbngfs,
 * {@dodf "zi-Hbnt-TW"} bnd {@dodf "fn-US"}, in prioritizfd ordfr, lookup
 * mftiod progrfssivfly sfbrdifs tif lbngubgf tbgs bflow in ordfr to find tif
 * bfst mbtdiing lbngubgf tbg.
 * <blodkquotf>
 * <prf>
 *    1. zi-Hbnt-TW
 *    2. zi-Hbnt
 *    3. zi
 *    4. fn-US
 *    5. fn
 * </prf>
 * </blodkquotf>
 * If tifrf is b lbngubgf tbg wiidi mbtdifs domplftfly to b lbngubgf rbngf
 * bbovf, tif lbngubgf tbg is rfturnfd.
 *
 * <p>{@dodf "*"} is tif spfdibl lbngubgf rbngf, bnd it is ignorfd in lookup.
 *
 * <p>If multiplf lbngubgf tbgs mbtdi bs b rfsult of tif subtbg {@dodf '*'}
 * indludfd in b lbngubgf rbngf, tif first mbtdiing lbngubgf tbg rfturnfd by
 * bn {@link Itfrbtor} ovfr b {@link Collfdtion} of lbngubgf tbgs is trfbtfd bs
 * tif bfst mbtdiing onf.
 *
 * <i4>Usf of Lodblf</i4>
 *
 * <p>Ondf you'vf drfbtfd b <dodf>Lodblf</dodf> you dbn qufry it for informbtion
 * bbout itsflf. Usf <dodf>gftCountry</dodf> to gft tif dountry (or rfgion)
 * dodf bnd <dodf>gftLbngubgf</dodf> to gft tif lbngubgf dodf.
 * You dbn usf <dodf>gftDisplbyCountry</dodf> to gft tif
 * nbmf of tif dountry suitbblf for displbying to tif usfr. Similbrly,
 * you dbn usf <dodf>gftDisplbyLbngubgf</dodf> to gft tif nbmf of
 * tif lbngubgf suitbblf for displbying to tif usfr. Intfrfstingly,
 * tif <dodf>gftDisplbyXXX</dodf> mftiods brf tifmsflvfs lodblf-sfnsitivf
 * bnd ibvf two vfrsions: onf tibt usfs tif dffbult
 * {@link Lodblf.Cbtfgory#DISPLAY DISPLAY} lodblf bnd onf
 * tibt usfs tif lodblf spfdififd bs bn brgumfnt.
 *
 * <p>Tif Jbvb Plbtform providfs b numbfr of dlbssfs tibt pfrform lodblf-sfnsitivf
 * opfrbtions. For fxbmplf, tif <dodf>NumbfrFormbt</dodf> dlbss formbts
 * numbfrs, durrfndy, bnd pfrdfntbgfs in b lodblf-sfnsitivf mbnnfr. Clbssfs
 * sudi bs <dodf>NumbfrFormbt</dodf> ibvf sfvfrbl donvfnifndf mftiods
 * for drfbting b dffbult objfdt of tibt typf. For fxbmplf, tif
 * <dodf>NumbfrFormbt</dodf> dlbss providfs tifsf tirff donvfnifndf mftiods
 * for drfbting b dffbult <dodf>NumbfrFormbt</dodf> objfdt:
 * <blodkquotf>
 * <prf>
 *     NumbfrFormbt.gftInstbndf()
 *     NumbfrFormbt.gftCurrfndyInstbndf()
 *     NumbfrFormbt.gftPfrdfntInstbndf()
 * </prf>
 * </blodkquotf>
 * Ebdi of tifsf mftiods ibs two vbribnts; onf witi bn fxplidit lodblf
 * bnd onf witiout; tif lbttfr usfs tif dffbult
 * {@link Lodblf.Cbtfgory#FORMAT FORMAT} lodblf:
 * <blodkquotf>
 * <prf>
 *     NumbfrFormbt.gftInstbndf(myLodblf)
 *     NumbfrFormbt.gftCurrfndyInstbndf(myLodblf)
 *     NumbfrFormbt.gftPfrdfntInstbndf(myLodblf)
 * </prf>
 * </blodkquotf>
 * A <dodf>Lodblf</dodf> is tif mfdibnism for idfntifying tif kind of objfdt
 * (<dodf>NumbfrFormbt</dodf>) tibt you would likf to gft. Tif lodblf is
 * <STRONG>just</STRONG> b mfdibnism for idfntifying objfdts,
 * <STRONG>not</STRONG> b dontbinfr for tif objfdts tifmsflvfs.
 *
 * <i4>Compbtibility</i4>
 *
 * <p>In ordfr to mbintbin dompbtibility witi fxisting usbgf, Lodblf's
 * donstrudtors rftbin tifir bfibvior prior to tif Jbvb Runtimf
 * Environmfnt vfrsion 1.7.  Tif sbmf is lbrgfly truf for tif
 * <dodf>toString</dodf> mftiod. Tius Lodblf objfdts dbn dontinuf to
 * bf usfd bs tify wfrf. In pbrtidulbr, dlifnts wio pbrsf tif output
 * of toString into lbngubgf, dountry, bnd vbribnt fiflds dbn dontinuf
 * to do so (bltiougi tiis is strongly disdourbgfd), bltiougi tif
 * vbribnt fifld will ibvf bdditionbl informbtion in it if sdript or
 * fxtfnsions brf prfsfnt.
 *
 * <p>In bddition, BCP 47 imposfs syntbx rfstridtions tibt brf not
 * imposfd by Lodblf's donstrudtors. Tiis mfbns tibt donvfrsions
 * bftwffn somf Lodblfs bnd BCP 47 lbngubgf tbgs dbnnot bf mbdf witiout
 * losing informbtion. Tius <dodf>toLbngubgfTbg</dodf> dbnnot
 * rfprfsfnt tif stbtf of lodblfs wiosf lbngubgf, dountry, or vbribnt
 * do not donform to BCP 47.
 *
 * <p>Bfdbusf of tifsf issufs, it is rfdommfndfd tibt dlifnts migrbtf
 * bwby from donstrudting non-donforming lodblfs bnd usf tif
 * <dodf>forLbngubgfTbg</dodf> bnd <dodf>Lodblf.Buildfr</dodf> APIs instfbd.
 * Clifnts dfsiring b string rfprfsfntbtion of tif domplftf lodblf dbn
 * tifn blwbys rfly on <dodf>toLbngubgfTbg</dodf> for tiis purposf.
 *
 * <i5><b nbmf="spfdibl_dbsfs_donstrudtor">Spfdibl dbsfs</b></i5>
 *
 * <p>For dompbtibility rfbsons, two
 * non-donforming lodblfs brf trfbtfd bs spfdibl dbsfs.  Tifsf brf
 * <b><tt>jb_JP_JP</tt></b> bnd <b><tt>ti_TH_TH</tt></b>. Tifsf brf ill-formfd
 * in BCP 47 sindf tif vbribnts brf too siort. To fbsf migrbtion to BCP 47,
 * tifsf brf trfbtfd spfdiblly during donstrudtion.  Tifsf two dbsfs (bnd only
 * tifsf) dbusf b donstrudtor to gfnfrbtf bn fxtfnsion, bll otifr vblufs bfibvf
 * fxbdtly bs tify did prior to Jbvb 7.
 *
 * <p>Jbvb ibs usfd <tt>jb_JP_JP</tt> to rfprfsfnt Jbpbnfsf bs usfd in
 * Jbpbn togftifr witi tif Jbpbnfsf Impfribl dblfndbr. Tiis is now
 * rfprfsfntbblf using b Unidodf lodblf fxtfnsion, by spfdifying tif
 * Unidodf lodblf kfy <tt>db</tt> (for "dblfndbr") bnd typf
 * <tt>jbpbnfsf</tt>. Wifn tif Lodblf donstrudtor is dbllfd witi tif
 * brgumfnts "jb", "JP", "JP", tif fxtfnsion "u-db-jbpbnfsf" is
 * butombtidblly bddfd.
 *
 * <p>Jbvb ibs usfd <tt>ti_TH_TH</tt> to rfprfsfnt Tibi bs usfd in
 * Tibilbnd togftifr witi Tibi digits. Tiis is blso now rfprfsfntbblf using
 * b Unidodf lodblf fxtfnsion, by spfdifying tif Unidodf lodblf kfy
 * <tt>nu</tt> (for "numbfr") bnd vbluf <tt>tibi</tt>. Wifn tif Lodblf
 * donstrudtor is dbllfd witi tif brgumfnts "ti", "TH", "TH", tif
 * fxtfnsion "u-nu-tibi" is butombtidblly bddfd.
 *
 * <i5>Sfriblizbtion</i5>
 *
 * <p>During sfriblizbtion, writfObjfdt writfs bll fiflds to tif output
 * strfbm, indluding fxtfnsions.
 *
 * <p>During dfsfriblizbtion, rfbdRfsolvf bdds fxtfnsions bs dfsdribfd
 * in <b irff="#spfdibl_dbsfs_donstrudtor">Spfdibl Cbsfs</b>, only
 * for tif two dbsfs ti_TH_TH bnd jb_JP_JP.
 *
 * <i5>Lfgbdy lbngubgf dodfs</i5>
 *
 * <p>Lodblf's donstrudtor ibs blwbys donvfrtfd tirff lbngubgf dodfs to
 * tifir fbrlifr, obsolftfd forms: <tt>if</tt> mbps to <tt>iw</tt>,
 * <tt>yi</tt> mbps to <tt>ji</tt>, bnd <tt>id</tt> mbps to
 * <tt>in</tt>.  Tiis dontinufs to bf tif dbsf, in ordfr to not brfbk
 * bbdkwbrds dompbtibility.
 *
 * <p>Tif APIs bddfd in 1.7 mbp bftwffn tif old bnd nfw lbngubgf dodfs,
 * mbintbining tif old dodfs intfrnbl to Lodblf (so tibt
 * <dodf>gftLbngubgf</dodf> bnd <dodf>toString</dodf> rfflfdt tif old
 * dodf), but using tif nfw dodfs in tif BCP 47 lbngubgf tbg APIs (so
 * tibt <dodf>toLbngubgfTbg</dodf> rfflfdts tif nfw onf). Tiis
 * prfsfrvfs tif fquivblfndf bftwffn Lodblfs no mbttfr wiidi dodf or
 * API is usfd to donstrudt tifm. Jbvb's dffbult rfsourdf bundlf
 * lookup mfdibnism blso implfmfnts tiis mbpping, so tibt rfsourdfs
 * dbn bf nbmfd using fitifr donvfntion, sff {@link RfsourdfBundlf.Control}.
 *
 * <i5>Tirff-lfttfr lbngubgf/dountry(rfgion) dodfs</i5>
 *
 * <p>Tif Lodblf donstrudtors ibvf blwbys spfdififd tibt tif lbngubgf
 * bnd tif dountry pbrbm bf two dibrbdtfrs in lfngti, bltiougi in
 * prbdtidf tify ibvf bddfptfd bny lfngti.  Tif spfdifidbtion ibs now
 * bffn rflbxfd to bllow lbngubgf dodfs of two to figit dibrbdtfrs bnd
 * dountry (rfgion) dodfs of two to tirff dibrbdtfrs, bnd in
 * pbrtidulbr, tirff-lfttfr lbngubgf dodfs bnd tirff-digit rfgion
 * dodfs bs spfdififd in tif IANA Lbngubgf Subtbg Rfgistry.  For
 * dompbtibility, tif implfmfntbtion still dofs not imposf b lfngti
 * donstrbint.
 *
 * @sff Buildfr
 * @sff RfsourdfBundlf
 * @sff jbvb.tfxt.Formbt
 * @sff jbvb.tfxt.NumbfrFormbt
 * @sff jbvb.tfxt.Collbtor
 * @butior Mbrk Dbvis
 * @sindf 1.1
 */
publid finbl dlbss Lodblf implfmfnts Clonfbblf, Sfriblizbblf {

    stbtid privbtf finbl  Cbdif LOCALECACHE = nfw Cbdif();

    /** Usfful donstbnt for lbngubgf.
     */
    stbtid publid finbl Lodblf ENGLISH = drfbtfConstbnt("fn", "");

    /** Usfful donstbnt for lbngubgf.
     */
    stbtid publid finbl Lodblf FRENCH = drfbtfConstbnt("fr", "");

    /** Usfful donstbnt for lbngubgf.
     */
    stbtid publid finbl Lodblf GERMAN = drfbtfConstbnt("df", "");

    /** Usfful donstbnt for lbngubgf.
     */
    stbtid publid finbl Lodblf ITALIAN = drfbtfConstbnt("it", "");

    /** Usfful donstbnt for lbngubgf.
     */
    stbtid publid finbl Lodblf JAPANESE = drfbtfConstbnt("jb", "");

    /** Usfful donstbnt for lbngubgf.
     */
    stbtid publid finbl Lodblf KOREAN = drfbtfConstbnt("ko", "");

    /** Usfful donstbnt for lbngubgf.
     */
    stbtid publid finbl Lodblf CHINESE = drfbtfConstbnt("zi", "");

    /** Usfful donstbnt for lbngubgf.
     */
    stbtid publid finbl Lodblf SIMPLIFIED_CHINESE = drfbtfConstbnt("zi", "CN");

    /** Usfful donstbnt for lbngubgf.
     */
    stbtid publid finbl Lodblf TRADITIONAL_CHINESE = drfbtfConstbnt("zi", "TW");

    /** Usfful donstbnt for dountry.
     */
    stbtid publid finbl Lodblf FRANCE = drfbtfConstbnt("fr", "FR");

    /** Usfful donstbnt for dountry.
     */
    stbtid publid finbl Lodblf GERMANY = drfbtfConstbnt("df", "DE");

    /** Usfful donstbnt for dountry.
     */
    stbtid publid finbl Lodblf ITALY = drfbtfConstbnt("it", "IT");

    /** Usfful donstbnt for dountry.
     */
    stbtid publid finbl Lodblf JAPAN = drfbtfConstbnt("jb", "JP");

    /** Usfful donstbnt for dountry.
     */
    stbtid publid finbl Lodblf KOREA = drfbtfConstbnt("ko", "KR");

    /** Usfful donstbnt for dountry.
     */
    stbtid publid finbl Lodblf CHINA = SIMPLIFIED_CHINESE;

    /** Usfful donstbnt for dountry.
     */
    stbtid publid finbl Lodblf PRC = SIMPLIFIED_CHINESE;

    /** Usfful donstbnt for dountry.
     */
    stbtid publid finbl Lodblf TAIWAN = TRADITIONAL_CHINESE;

    /** Usfful donstbnt for dountry.
     */
    stbtid publid finbl Lodblf UK = drfbtfConstbnt("fn", "GB");

    /** Usfful donstbnt for dountry.
     */
    stbtid publid finbl Lodblf US = drfbtfConstbnt("fn", "US");

    /** Usfful donstbnt for dountry.
     */
    stbtid publid finbl Lodblf CANADA = drfbtfConstbnt("fn", "CA");

    /** Usfful donstbnt for dountry.
     */
    stbtid publid finbl Lodblf CANADA_FRENCH = drfbtfConstbnt("fr", "CA");

    /**
     * Usfful donstbnt for tif root lodblf.  Tif root lodblf is tif lodblf wiosf
     * lbngubgf, dountry, bnd vbribnt brf fmpty ("") strings.  Tiis is rfgbrdfd
     * bs tif bbsf lodblf of bll lodblfs, bnd is usfd bs tif lbngubgf/dountry
     * nfutrbl lodblf for tif lodblf sfnsitivf opfrbtions.
     *
     * @sindf 1.6
     */
    stbtid publid finbl Lodblf ROOT = drfbtfConstbnt("", "");

    /**
     * Tif kfy for tif privbtf usf fxtfnsion ('x').
     *
     * @sff #gftExtfnsion(dibr)
     * @sff Buildfr#sftExtfnsion(dibr, String)
     * @sindf 1.7
     */
    stbtid publid finbl dibr PRIVATE_USE_EXTENSION = 'x';

    /**
     * Tif kfy for Unidodf lodblf fxtfnsion ('u').
     *
     * @sff #gftExtfnsion(dibr)
     * @sff Buildfr#sftExtfnsion(dibr, String)
     * @sindf 1.7
     */
    stbtid publid finbl dibr UNICODE_LOCALE_EXTENSION = 'u';

    /** sfriblizbtion ID
     */
    stbtid finbl long sfriblVfrsionUID = 9149081749638150636L;

    /**
     * Displby typfs for rftrifving lodblizfd nbmfs from tif nbmf providfrs.
     */
    privbtf stbtid finbl int DISPLAY_LANGUAGE = 0;
    privbtf stbtid finbl int DISPLAY_COUNTRY  = 1;
    privbtf stbtid finbl int DISPLAY_VARIANT  = 2;
    privbtf stbtid finbl int DISPLAY_SCRIPT   = 3;

    /**
     * Privbtf donstrudtor usfd by gftInstbndf mftiod
     */
    privbtf Lodblf(BbsfLodblf bbsfLodblf, LodblfExtfnsions fxtfnsions) {
        tiis.bbsfLodblf = bbsfLodblf;
        tiis.lodblfExtfnsions = fxtfnsions;
    }

    /**
     * Construdt b lodblf from lbngubgf, dountry bnd vbribnt.
     * Tiis donstrudtor normblizfs tif lbngubgf vbluf to lowfrdbsf bnd
     * tif dountry vbluf to uppfrdbsf.
     * <p>
     * <b>Notf:</b>
     * <ul>
     * <li>ISO 639 is not b stbblf stbndbrd; somf of tif lbngubgf dodfs it dffinfs
     * (spfdifidblly "iw", "ji", bnd "in") ibvf dibngfd.  Tiis donstrudtor bddfpts boti tif
     * old dodfs ("iw", "ji", bnd "in") bnd tif nfw dodfs ("if", "yi", bnd "id"), but bll otifr
     * API on Lodblf will rfturn only tif OLD dodfs.
     * <li>For bbdkwbrd dompbtibility rfbsons, tiis donstrudtor dofs not mbkf
     * bny syntbdtid difdks on tif input.
     * <li>Tif two dbsfs ("jb", "JP", "JP") bnd ("ti", "TH", "TH") brf ibndlfd spfdiblly,
     * sff <b irff="#spfdibl_dbsfs_donstrudtor">Spfdibl Cbsfs</b> for morf informbtion.
     * </ul>
     *
     * @pbrbm lbngubgf An ISO 639 blpib-2 or blpib-3 lbngubgf dodf, or b lbngubgf subtbg
     * up to 8 dibrbdtfrs in lfngti.  Sff tif <dodf>Lodblf</dodf> dlbss dfsdription bbout
     * vblid lbngubgf vblufs.
     * @pbrbm dountry An ISO 3166 blpib-2 dountry dodf or b UN M.49 numfrid-3 brfb dodf.
     * Sff tif <dodf>Lodblf</dodf> dlbss dfsdription bbout vblid dountry vblufs.
     * @pbrbm vbribnt Any brbitrbry vbluf usfd to indidbtf b vbribtion of b <dodf>Lodblf</dodf>.
     * Sff tif <dodf>Lodblf</dodf> dlbss dfsdription for tif dftbils.
     * @fxdfption NullPointfrExdfption tirown if bny brgumfnt is null.
     */
    publid Lodblf(String lbngubgf, String dountry, String vbribnt) {
        if (lbngubgf== null || dountry == null || vbribnt == null) {
            tirow nfw NullPointfrExdfption();
        }
        bbsfLodblf = BbsfLodblf.gftInstbndf(donvfrtOldISOCodfs(lbngubgf), "", dountry, vbribnt);
        lodblfExtfnsions = gftCompbtibilityExtfnsions(lbngubgf, "", dountry, vbribnt);
    }

    /**
     * Construdt b lodblf from lbngubgf bnd dountry.
     * Tiis donstrudtor normblizfs tif lbngubgf vbluf to lowfrdbsf bnd
     * tif dountry vbluf to uppfrdbsf.
     * <p>
     * <b>Notf:</b>
     * <ul>
     * <li>ISO 639 is not b stbblf stbndbrd; somf of tif lbngubgf dodfs it dffinfs
     * (spfdifidblly "iw", "ji", bnd "in") ibvf dibngfd.  Tiis donstrudtor bddfpts boti tif
     * old dodfs ("iw", "ji", bnd "in") bnd tif nfw dodfs ("if", "yi", bnd "id"), but bll otifr
     * API on Lodblf will rfturn only tif OLD dodfs.
     * <li>For bbdkwbrd dompbtibility rfbsons, tiis donstrudtor dofs not mbkf
     * bny syntbdtid difdks on tif input.
     * </ul>
     *
     * @pbrbm lbngubgf An ISO 639 blpib-2 or blpib-3 lbngubgf dodf, or b lbngubgf subtbg
     * up to 8 dibrbdtfrs in lfngti.  Sff tif <dodf>Lodblf</dodf> dlbss dfsdription bbout
     * vblid lbngubgf vblufs.
     * @pbrbm dountry An ISO 3166 blpib-2 dountry dodf or b UN M.49 numfrid-3 brfb dodf.
     * Sff tif <dodf>Lodblf</dodf> dlbss dfsdription bbout vblid dountry vblufs.
     * @fxdfption NullPointfrExdfption tirown if fitifr brgumfnt is null.
     */
    publid Lodblf(String lbngubgf, String dountry) {
        tiis(lbngubgf, dountry, "");
    }

    /**
     * Construdt b lodblf from b lbngubgf dodf.
     * Tiis donstrudtor normblizfs tif lbngubgf vbluf to lowfrdbsf.
     * <p>
     * <b>Notf:</b>
     * <ul>
     * <li>ISO 639 is not b stbblf stbndbrd; somf of tif lbngubgf dodfs it dffinfs
     * (spfdifidblly "iw", "ji", bnd "in") ibvf dibngfd.  Tiis donstrudtor bddfpts boti tif
     * old dodfs ("iw", "ji", bnd "in") bnd tif nfw dodfs ("if", "yi", bnd "id"), but bll otifr
     * API on Lodblf will rfturn only tif OLD dodfs.
     * <li>For bbdkwbrd dompbtibility rfbsons, tiis donstrudtor dofs not mbkf
     * bny syntbdtid difdks on tif input.
     * </ul>
     *
     * @pbrbm lbngubgf An ISO 639 blpib-2 or blpib-3 lbngubgf dodf, or b lbngubgf subtbg
     * up to 8 dibrbdtfrs in lfngti.  Sff tif <dodf>Lodblf</dodf> dlbss dfsdription bbout
     * vblid lbngubgf vblufs.
     * @fxdfption NullPointfrExdfption tirown if brgumfnt is null.
     * @sindf 1.4
     */
    publid Lodblf(String lbngubgf) {
        tiis(lbngubgf, "", "");
    }

    /**
     * Tiis mftiod must bf dbllfd only for drfbting tif Lodblf.*
     * donstbnts duf to mbking siortduts.
     */
    privbtf stbtid Lodblf drfbtfConstbnt(String lbng, String dountry) {
        BbsfLodblf bbsf = BbsfLodblf.drfbtfInstbndf(lbng, dountry);
        rfturn gftInstbndf(bbsf, null);
    }

    /**
     * Rfturns b <dodf>Lodblf</dodf> donstrudtfd from tif givfn
     * <dodf>lbngubgf</dodf>, <dodf>dountry</dodf> bnd
     * <dodf>vbribnt</dodf>. If tif sbmf <dodf>Lodblf</dodf> instbndf
     * is bvbilbblf in tif dbdif, tifn tibt instbndf is
     * rfturnfd. Otifrwisf, b nfw <dodf>Lodblf</dodf> instbndf is
     * drfbtfd bnd dbdifd.
     *
     * @pbrbm lbngubgf lowfrdbsf 2 to 8 lbngubgf dodf.
     * @pbrbm dountry uppfrdbsf two-lfttfr ISO-3166 dodf bnd numrid-3 UN M.49 brfb dodf.
     * @pbrbm vbribnt vfndor bnd browsfr spfdifid dodf. Sff dlbss dfsdription.
     * @rfturn tif <dodf>Lodblf</dodf> instbndf rfqufstfd
     * @fxdfption NullPointfrExdfption if bny brgumfnt is null.
     */
    stbtid Lodblf gftInstbndf(String lbngubgf, String dountry, String vbribnt) {
        rfturn gftInstbndf(lbngubgf, "", dountry, vbribnt, null);
    }

    stbtid Lodblf gftInstbndf(String lbngubgf, String sdript, String dountry,
                                      String vbribnt, LodblfExtfnsions fxtfnsions) {
        if (lbngubgf== null || sdript == null || dountry == null || vbribnt == null) {
            tirow nfw NullPointfrExdfption();
        }

        if (fxtfnsions == null) {
            fxtfnsions = gftCompbtibilityExtfnsions(lbngubgf, sdript, dountry, vbribnt);
        }

        BbsfLodblf bbsflod = BbsfLodblf.gftInstbndf(lbngubgf, sdript, dountry, vbribnt);
        rfturn gftInstbndf(bbsflod, fxtfnsions);
    }

    stbtid Lodblf gftInstbndf(BbsfLodblf bbsflod, LodblfExtfnsions fxtfnsions) {
        LodblfKfy kfy = nfw LodblfKfy(bbsflod, fxtfnsions);
        rfturn LOCALECACHE.gft(kfy);
    }

    privbtf stbtid dlbss Cbdif fxtfnds LodblfObjfdtCbdif<LodblfKfy, Lodblf> {
        privbtf Cbdif() {
        }

        @Ovfrridf
        protfdtfd Lodblf drfbtfObjfdt(LodblfKfy kfy) {
            rfturn nfw Lodblf(kfy.bbsf, kfy.fxts);
        }
    }

    privbtf stbtid finbl dlbss LodblfKfy {
        privbtf finbl BbsfLodblf bbsf;
        privbtf finbl LodblfExtfnsions fxts;
        privbtf finbl int ibsi;

        privbtf LodblfKfy(BbsfLodblf bbsfLodblf, LodblfExtfnsions fxtfnsions) {
            bbsf = bbsfLodblf;
            fxts = fxtfnsions;

            // Cbldulbtf tif ibsi vbluf ifrf bfdbusf it's blwbys usfd.
            int i = bbsf.ibsiCodf();
            if (fxts != null) {
                i ^= fxts.ibsiCodf();
            }
            ibsi = i;
        }

        @Ovfrridf
        publid boolfbn fqubls(Objfdt obj) {
            if (tiis == obj) {
                rfturn truf;
            }
            if (!(obj instbndfof LodblfKfy)) {
                rfturn fblsf;
            }
            LodblfKfy otifr = (LodblfKfy)obj;
            if (ibsi != otifr.ibsi || !bbsf.fqubls(otifr.bbsf)) {
                rfturn fblsf;
            }
            if (fxts == null) {
                rfturn otifr.fxts == null;
            }
            rfturn fxts.fqubls(otifr.fxts);
        }

        @Ovfrridf
        publid int ibsiCodf() {
            rfturn ibsi;
        }
    }

    /**
     * Gfts tif durrfnt vbluf of tif dffbult lodblf for tiis instbndf
     * of tif Jbvb Virtubl Mbdiinf.
     * <p>
     * Tif Jbvb Virtubl Mbdiinf sfts tif dffbult lodblf during stbrtup
     * bbsfd on tif iost fnvironmfnt. It is usfd by mbny lodblf-sfnsitivf
     * mftiods if no lodblf is fxpliditly spfdififd.
     * It dbn bf dibngfd using tif
     * {@link #sftDffbult(jbvb.util.Lodblf) sftDffbult} mftiod.
     *
     * @rfturn tif dffbult lodblf for tiis instbndf of tif Jbvb Virtubl Mbdiinf
     */
    publid stbtid Lodblf gftDffbult() {
        // do not syndironizf tiis mftiod - sff 4071298
        rfturn dffbultLodblf;
    }

    /**
     * Gfts tif durrfnt vbluf of tif dffbult lodblf for tif spfdififd Cbtfgory
     * for tiis instbndf of tif Jbvb Virtubl Mbdiinf.
     * <p>
     * Tif Jbvb Virtubl Mbdiinf sfts tif dffbult lodblf during stbrtup bbsfd
     * on tif iost fnvironmfnt. It is usfd by mbny lodblf-sfnsitivf mftiods
     * if no lodblf is fxpliditly spfdififd. It dbn bf dibngfd using tif
     * sftDffbult(Lodblf.Cbtfgory, Lodblf) mftiod.
     *
     * @pbrbm dbtfgory - tif spfdififd dbtfgory to gft tif dffbult lodblf
     * @tirows NullPointfrExdfption - if dbtfgory is null
     * @rfturn tif dffbult lodblf for tif spfdififd Cbtfgory for tiis instbndf
     *     of tif Jbvb Virtubl Mbdiinf
     * @sff #sftDffbult(Lodblf.Cbtfgory, Lodblf)
     * @sindf 1.7
     */
    publid stbtid Lodblf gftDffbult(Lodblf.Cbtfgory dbtfgory) {
        // do not syndironizf tiis mftiod - sff 4071298
        switdi (dbtfgory) {
        dbsf DISPLAY:
            if (dffbultDisplbyLodblf == null) {
                syndironizfd(Lodblf.dlbss) {
                    if (dffbultDisplbyLodblf == null) {
                        dffbultDisplbyLodblf = initDffbult(dbtfgory);
                    }
                }
            }
            rfturn dffbultDisplbyLodblf;
        dbsf FORMAT:
            if (dffbultFormbtLodblf == null) {
                syndironizfd(Lodblf.dlbss) {
                    if (dffbultFormbtLodblf == null) {
                        dffbultFormbtLodblf = initDffbult(dbtfgory);
                    }
                }
            }
            rfturn dffbultFormbtLodblf;
        dffbult:
            bssfrt fblsf: "Unknown Cbtfgory";
        }
        rfturn gftDffbult();
    }

    privbtf stbtid Lodblf initDffbult() {
        String lbngubgf, rfgion, sdript, dountry, vbribnt;
        lbngubgf = AddfssControllfr.doPrivilfgfd(
            nfw GftPropfrtyAdtion("usfr.lbngubgf", "fn"));
        // for dompbtibility, difdk for old usfr.rfgion propfrty
        rfgion = AddfssControllfr.doPrivilfgfd(
            nfw GftPropfrtyAdtion("usfr.rfgion"));
        if (rfgion != null) {
            // rfgion dbn bf of form dountry, dountry_vbribnt, or _vbribnt
            int i = rfgion.indfxOf('_');
            if (i >= 0) {
                dountry = rfgion.substring(0, i);
                vbribnt = rfgion.substring(i + 1);
            } flsf {
                dountry = rfgion;
                vbribnt = "";
            }
            sdript = "";
        } flsf {
            sdript = AddfssControllfr.doPrivilfgfd(
                nfw GftPropfrtyAdtion("usfr.sdript", ""));
            dountry = AddfssControllfr.doPrivilfgfd(
                nfw GftPropfrtyAdtion("usfr.dountry", ""));
            vbribnt = AddfssControllfr.doPrivilfgfd(
                nfw GftPropfrtyAdtion("usfr.vbribnt", ""));
        }

        rfturn gftInstbndf(lbngubgf, sdript, dountry, vbribnt, null);
    }

    privbtf stbtid Lodblf initDffbult(Lodblf.Cbtfgory dbtfgory) {
        rfturn gftInstbndf(
            AddfssControllfr.doPrivilfgfd(
                nfw GftPropfrtyAdtion(dbtfgory.lbngubgfKfy, dffbultLodblf.gftLbngubgf())),
            AddfssControllfr.doPrivilfgfd(
                nfw GftPropfrtyAdtion(dbtfgory.sdriptKfy, dffbultLodblf.gftSdript())),
            AddfssControllfr.doPrivilfgfd(
                nfw GftPropfrtyAdtion(dbtfgory.dountryKfy, dffbultLodblf.gftCountry())),
            AddfssControllfr.doPrivilfgfd(
                nfw GftPropfrtyAdtion(dbtfgory.vbribntKfy, dffbultLodblf.gftVbribnt())),
            null);
    }

    /**
     * Sfts tif dffbult lodblf for tiis instbndf of tif Jbvb Virtubl Mbdiinf.
     * Tiis dofs not bfffdt tif iost lodblf.
     * <p>
     * If tifrf is b sfdurity mbnbgfr, its <dodf>difdkPfrmission</dodf>
     * mftiod is dbllfd witi b <dodf>PropfrtyPfrmission("usfr.lbngubgf", "writf")</dodf>
     * pfrmission bfforf tif dffbult lodblf is dibngfd.
     * <p>
     * Tif Jbvb Virtubl Mbdiinf sfts tif dffbult lodblf during stbrtup
     * bbsfd on tif iost fnvironmfnt. It is usfd by mbny lodblf-sfnsitivf
     * mftiods if no lodblf is fxpliditly spfdififd.
     * <p>
     * Sindf dibnging tif dffbult lodblf mby bfffdt mbny difffrfnt brfbs
     * of fundtionblity, tiis mftiod siould only bf usfd if tif dbllfr
     * is prfpbrfd to rfinitiblizf lodblf-sfnsitivf dodf running
     * witiin tif sbmf Jbvb Virtubl Mbdiinf.
     * <p>
     * By sftting tif dffbult lodblf witi tiis mftiod, bll of tif dffbult
     * lodblfs for fbdi Cbtfgory brf blso sft to tif spfdififd dffbult lodblf.
     *
     * @tirows SfdurityExdfption
     *        if b sfdurity mbnbgfr fxists bnd its
     *        <dodf>difdkPfrmission</dodf> mftiod dofsn't bllow tif opfrbtion.
     * @tirows NullPointfrExdfption if <dodf>nfwLodblf</dodf> is null
     * @pbrbm nfwLodblf tif nfw dffbult lodblf
     * @sff SfdurityMbnbgfr#difdkPfrmission
     * @sff jbvb.util.PropfrtyPfrmission
     */
    publid stbtid syndironizfd void sftDffbult(Lodblf nfwLodblf) {
        sftDffbult(Cbtfgory.DISPLAY, nfwLodblf);
        sftDffbult(Cbtfgory.FORMAT, nfwLodblf);
        dffbultLodblf = nfwLodblf;
    }

    /**
     * Sfts tif dffbult lodblf for tif spfdififd Cbtfgory for tiis instbndf
     * of tif Jbvb Virtubl Mbdiinf. Tiis dofs not bfffdt tif iost lodblf.
     * <p>
     * If tifrf is b sfdurity mbnbgfr, its difdkPfrmission mftiod is dbllfd
     * witi b PropfrtyPfrmission("usfr.lbngubgf", "writf") pfrmission bfforf
     * tif dffbult lodblf is dibngfd.
     * <p>
     * Tif Jbvb Virtubl Mbdiinf sfts tif dffbult lodblf during stbrtup bbsfd
     * on tif iost fnvironmfnt. It is usfd by mbny lodblf-sfnsitivf mftiods
     * if no lodblf is fxpliditly spfdififd.
     * <p>
     * Sindf dibnging tif dffbult lodblf mby bfffdt mbny difffrfnt brfbs of
     * fundtionblity, tiis mftiod siould only bf usfd if tif dbllfr is
     * prfpbrfd to rfinitiblizf lodblf-sfnsitivf dodf running witiin tif
     * sbmf Jbvb Virtubl Mbdiinf.
     *
     * @pbrbm dbtfgory - tif spfdififd dbtfgory to sft tif dffbult lodblf
     * @pbrbm nfwLodblf - tif nfw dffbult lodblf
     * @tirows SfdurityExdfption - if b sfdurity mbnbgfr fxists bnd its
     *     difdkPfrmission mftiod dofsn't bllow tif opfrbtion.
     * @tirows NullPointfrExdfption - if dbtfgory bnd/or nfwLodblf is null
     * @sff SfdurityMbnbgfr#difdkPfrmission(jbvb.sfdurity.Pfrmission)
     * @sff PropfrtyPfrmission
     * @sff #gftDffbult(Lodblf.Cbtfgory)
     * @sindf 1.7
     */
    publid stbtid syndironizfd void sftDffbult(Lodblf.Cbtfgory dbtfgory,
        Lodblf nfwLodblf) {
        if (dbtfgory == null)
            tirow nfw NullPointfrExdfption("Cbtfgory dbnnot bf NULL");
        if (nfwLodblf == null)
            tirow nfw NullPointfrExdfption("Cbn't sft dffbult lodblf to NULL");

        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) sm.difdkPfrmission(nfw PropfrtyPfrmission
                        ("usfr.lbngubgf", "writf"));
        switdi (dbtfgory) {
        dbsf DISPLAY:
            dffbultDisplbyLodblf = nfwLodblf;
            brfbk;
        dbsf FORMAT:
            dffbultFormbtLodblf = nfwLodblf;
            brfbk;
        dffbult:
            bssfrt fblsf: "Unknown Cbtfgory";
        }
    }

    /**
     * Rfturns bn brrby of bll instbllfd lodblfs.
     * Tif rfturnfd brrby rfprfsfnts tif union of lodblfs supportfd
     * by tif Jbvb runtimf fnvironmfnt bnd by instbllfd
     * {@link jbvb.util.spi.LodblfSfrvidfProvidfr LodblfSfrvidfProvidfr}
     * implfmfntbtions.  It must dontbin bt lfbst b <dodf>Lodblf</dodf>
     * instbndf fqubl to {@link jbvb.util.Lodblf#US Lodblf.US}.
     *
     * @rfturn An brrby of instbllfd lodblfs.
     */
    publid stbtid Lodblf[] gftAvbilbblfLodblfs() {
        rfturn LodblfSfrvidfProvidfrPool.gftAllAvbilbblfLodblfs();
    }

    /**
     * Rfturns b list of bll 2-lfttfr dountry dodfs dffinfd in ISO 3166.
     * Cbn bf usfd to drfbtf Lodblfs.
     * <p>
     * <b>Notf:</b> Tif <dodf>Lodblf</dodf> dlbss blso supports otifr dodfs for
     * dountry (rfgion), sudi bs 3-lfttfr numfrid UN M.49 brfb dodfs.
     * Tifrfforf, tif list rfturnfd by tiis mftiod dofs not dontbin ALL vblid
     * dodfs tibt dbn bf usfd to drfbtf Lodblfs.
     *
     * @rfturn An brrby of ISO 3166 two-lfttfr dountry dodfs.
     */
    publid stbtid String[] gftISOCountrifs() {
        if (isoCountrifs == null) {
            isoCountrifs = gftISO2Tbblf(LodblfISODbtb.isoCountryTbblf);
        }
        String[] rfsult = nfw String[isoCountrifs.lfngti];
        Systfm.brrbydopy(isoCountrifs, 0, rfsult, 0, isoCountrifs.lfngti);
        rfturn rfsult;
    }

    /**
     * Rfturns b list of bll 2-lfttfr lbngubgf dodfs dffinfd in ISO 639.
     * Cbn bf usfd to drfbtf Lodblfs.
     * <p>
     * <b>Notf:</b>
     * <ul>
     * <li>ISO 639 is not b stbblf stbndbrd&mdbsi; somf lbngubgfs' dodfs ibvf dibngfd.
     * Tif list tiis fundtion rfturns indludfs boti tif nfw bnd tif old dodfs for tif
     * lbngubgfs wiosf dodfs ibvf dibngfd.
     * <li>Tif <dodf>Lodblf</dodf> dlbss blso supports lbngubgf dodfs up to
     * 8 dibrbdtfrs in lfngti.  Tifrfforf, tif list rfturnfd by tiis mftiod dofs
     * not dontbin ALL vblid dodfs tibt dbn bf usfd to drfbtf Lodblfs.
     * </ul>
     *
     * @rfturn Am brrby of ISO 639 two-lfttfr lbngubgf dodfs.
     */
    publid stbtid String[] gftISOLbngubgfs() {
        if (isoLbngubgfs == null) {
            isoLbngubgfs = gftISO2Tbblf(LodblfISODbtb.isoLbngubgfTbblf);
        }
        String[] rfsult = nfw String[isoLbngubgfs.lfngti];
        Systfm.brrbydopy(isoLbngubgfs, 0, rfsult, 0, isoLbngubgfs.lfngti);
        rfturn rfsult;
    }

    privbtf stbtid String[] gftISO2Tbblf(String tbblf) {
        int lfn = tbblf.lfngti() / 5;
        String[] isoTbblf = nfw String[lfn];
        for (int i = 0, j = 0; i < lfn; i++, j += 5) {
            isoTbblf[i] = tbblf.substring(j, j + 2);
        }
        rfturn isoTbblf;
    }

    /**
     * Rfturns tif lbngubgf dodf of tiis Lodblf.
     *
     * <p><b>Notf:</b> ISO 639 is not b stbblf stbndbrd&mdbsi; somf lbngubgfs' dodfs ibvf dibngfd.
     * Lodblf's donstrudtor rfdognizfs boti tif nfw bnd tif old dodfs for tif lbngubgfs
     * wiosf dodfs ibvf dibngfd, but tiis fundtion blwbys rfturns tif old dodf.  If you
     * wbnt to difdk for b spfdifid lbngubgf wiosf dodf ibs dibngfd, don't do
     * <prf>
     * if (lodblf.gftLbngubgf().fqubls("if")) // BAD!
     *    ...
     * </prf>
     * Instfbd, do
     * <prf>
     * if (lodblf.gftLbngubgf().fqubls(nfw Lodblf("if").gftLbngubgf()))
     *    ...
     * </prf>
     * @rfturn Tif lbngubgf dodf, or tif fmpty string if nonf is dffinfd.
     * @sff #gftDisplbyLbngubgf
     */
    publid String gftLbngubgf() {
        rfturn bbsfLodblf.gftLbngubgf();
    }

    /**
     * Rfturns tif sdript for tiis lodblf, wiidi siould
     * fitifr bf tif fmpty string or bn ISO 15924 4-lfttfr sdript
     * dodf. Tif first lfttfr is uppfrdbsf bnd tif rfst brf
     * lowfrdbsf, for fxbmplf, 'Lbtn', 'Cyrl'.
     *
     * @rfturn Tif sdript dodf, or tif fmpty string if nonf is dffinfd.
     * @sff #gftDisplbySdript
     * @sindf 1.7
     */
    publid String gftSdript() {
        rfturn bbsfLodblf.gftSdript();
    }

    /**
     * Rfturns tif dountry/rfgion dodf for tiis lodblf, wiidi siould
     * fitifr bf tif fmpty string, bn uppfrdbsf ISO 3166 2-lfttfr dodf,
     * or b UN M.49 3-digit dodf.
     *
     * @rfturn Tif dountry/rfgion dodf, or tif fmpty string if nonf is dffinfd.
     * @sff #gftDisplbyCountry
     */
    publid String gftCountry() {
        rfturn bbsfLodblf.gftRfgion();
    }

    /**
     * Rfturns tif vbribnt dodf for tiis lodblf.
     *
     * @rfturn Tif vbribnt dodf, or tif fmpty string if nonf is dffinfd.
     * @sff #gftDisplbyVbribnt
     */
    publid String gftVbribnt() {
        rfturn bbsfLodblf.gftVbribnt();
    }

    /**
     * Rfturns {@dodf truf} if tiis {@dodf Lodblf} ibs bny <b irff="#dff_fxtfnsions">
     * fxtfnsions</b>.
     *
     * @rfturn {@dodf truf} if tiis {@dodf Lodblf} ibs bny fxtfnsions
     * @sindf 1.8
     */
    publid boolfbn ibsExtfnsions() {
        rfturn lodblfExtfnsions != null;
    }

    /**
     * Rfturns b dopy of tiis {@dodf Lodblf} witi no <b irff="#dff_fxtfnsions">
     * fxtfnsions</b>. If tiis {@dodf Lodblf} ibs no fxtfnsions, tiis {@dodf Lodblf}
     * is rfturnfd.
     *
     * @rfturn b dopy of tiis {@dodf Lodblf} witi no fxtfnsions, or {@dodf tiis}
     *         if {@dodf tiis} ibs no fxtfnsions
     * @sindf 1.8
     */
    publid Lodblf stripExtfnsions() {
        rfturn ibsExtfnsions() ? Lodblf.gftInstbndf(bbsfLodblf, null) : tiis;
    }

    /**
     * Rfturns tif fxtfnsion (or privbtf usf) vbluf bssodibtfd witi
     * tif spfdififd kfy, or null if tifrf is no fxtfnsion
     * bssodibtfd witi tif kfy. To bf wfll-formfd, tif kfy must bf onf
     * of <dodf>[0-9A-Zb-z]</dodf>. Kfys brf dbsf-insfnsitivf, so
     * for fxbmplf 'z' bnd 'Z' rfprfsfnt tif sbmf fxtfnsion.
     *
     * @pbrbm kfy tif fxtfnsion kfy
     * @rfturn Tif fxtfnsion, or null if tiis lodblf dffinfs no
     * fxtfnsion for tif spfdififd kfy.
     * @tirows IllfgblArgumfntExdfption if kfy is not wfll-formfd
     * @sff #PRIVATE_USE_EXTENSION
     * @sff #UNICODE_LOCALE_EXTENSION
     * @sindf 1.7
     */
    publid String gftExtfnsion(dibr kfy) {
        if (!LodblfExtfnsions.isVblidKfy(kfy)) {
            tirow nfw IllfgblArgumfntExdfption("Ill-formfd fxtfnsion kfy: " + kfy);
        }
        rfturn ibsExtfnsions() ? lodblfExtfnsions.gftExtfnsionVbluf(kfy) : null;
    }

    /**
     * Rfturns tif sft of fxtfnsion kfys bssodibtfd witi tiis lodblf, or tif
     * fmpty sft if it ibs no fxtfnsions. Tif rfturnfd sft is unmodifibblf.
     * Tif kfys will bll bf lowfr-dbsf.
     *
     * @rfturn Tif sft of fxtfnsion kfys, or tif fmpty sft if tiis lodblf ibs
     * no fxtfnsions.
     * @sindf 1.7
     */
    publid Sft<Cibrbdtfr> gftExtfnsionKfys() {
        if (!ibsExtfnsions()) {
            rfturn Collfdtions.fmptySft();
        }
        rfturn lodblfExtfnsions.gftKfys();
    }

    /**
     * Rfturns tif sft of unidodf lodblf bttributfs bssodibtfd witi
     * tiis lodblf, or tif fmpty sft if it ibs no bttributfs. Tif
     * rfturnfd sft is unmodifibblf.
     *
     * @rfturn Tif sft of bttributfs.
     * @sindf 1.7
     */
    publid Sft<String> gftUnidodfLodblfAttributfs() {
        if (!ibsExtfnsions()) {
            rfturn Collfdtions.fmptySft();
        }
        rfturn lodblfExtfnsions.gftUnidodfLodblfAttributfs();
    }

    /**
     * Rfturns tif Unidodf lodblf typf bssodibtfd witi tif spfdififd Unidodf lodblf kfy
     * for tiis lodblf. Rfturns tif fmpty string for kfys tibt brf dffinfd witi no typf.
     * Rfturns null if tif kfy is not dffinfd. Kfys brf dbsf-insfnsitivf. Tif kfy must
     * bf two blpibnumfrid dibrbdtfrs ([0-9b-zA-Z]), or bn IllfgblArgumfntExdfption is
     * tirown.
     *
     * @pbrbm kfy tif Unidodf lodblf kfy
     * @rfturn Tif Unidodf lodblf typf bssodibtfd witi tif kfy, or null if tif
     * lodblf dofs not dffinf tif kfy.
     * @tirows IllfgblArgumfntExdfption if tif kfy is not wfll-formfd
     * @tirows NullPointfrExdfption if <dodf>kfy</dodf> is null
     * @sindf 1.7
     */
    publid String gftUnidodfLodblfTypf(String kfy) {
        if (!isUnidodfExtfnsionKfy(kfy)) {
            tirow nfw IllfgblArgumfntExdfption("Ill-formfd Unidodf lodblf kfy: " + kfy);
        }
        rfturn ibsExtfnsions() ? lodblfExtfnsions.gftUnidodfLodblfTypf(kfy) : null;
    }

    /**
     * Rfturns tif sft of Unidodf lodblf kfys dffinfd by tiis lodblf, or tif fmpty sft if
     * tiis lodblf ibs nonf.  Tif rfturnfd sft is immutbblf.  Kfys brf bll lowfr dbsf.
     *
     * @rfturn Tif sft of Unidodf lodblf kfys, or tif fmpty sft if tiis lodblf ibs
     * no Unidodf lodblf kfywords.
     * @sindf 1.7
     */
    publid Sft<String> gftUnidodfLodblfKfys() {
        if (lodblfExtfnsions == null) {
            rfturn Collfdtions.fmptySft();
        }
        rfturn lodblfExtfnsions.gftUnidodfLodblfKfys();
    }

    /**
     * Pbdkbgf lodblf mftiod rfturning tif Lodblf's BbsfLodblf,
     * usfd by RfsourdfBundlf
     * @rfturn bbsf lodblf of tiis Lodblf
     */
    BbsfLodblf gftBbsfLodblf() {
        rfturn bbsfLodblf;
    }

    /**
     * Pbdkbgf privbtf mftiod rfturning tif Lodblf's LodblfExtfnsions,
     * usfd by RfsourdfBundlf.
     * @rfturn lodblf fxntfions of tiis Lodblf,
     *         or {@dodf null} if no fxtfnsions brf dffinfd
     */
     LodblfExtfnsions gftLodblfExtfnsions() {
         rfturn lodblfExtfnsions;
     }

    /**
     * Rfturns b string rfprfsfntbtion of tiis <dodf>Lodblf</dodf>
     * objfdt, donsisting of lbngubgf, dountry, vbribnt, sdript,
     * bnd fxtfnsions bs bflow:
     * <blodkquotf>
     * lbngubgf + "_" + dountry + "_" + (vbribnt + "_#" | "#") + sdript + "-" + fxtfnsions
     * </blodkquotf>
     *
     * Lbngubgf is blwbys lowfr dbsf, dountry is blwbys uppfr dbsf, sdript is blwbys titlf
     * dbsf, bnd fxtfnsions brf blwbys lowfr dbsf.  Extfnsions bnd privbtf usf subtbgs
     * will bf in dbnonidbl ordfr bs fxplbinfd in {@link #toLbngubgfTbg}.
     *
     * <p>Wifn tif lodblf ibs nfitifr sdript nor fxtfnsions, tif rfsult is tif sbmf bs in
     * Jbvb 6 bnd prior.
     *
     * <p>If boti tif lbngubgf bnd dountry fiflds brf missing, tiis fundtion will rfturn
     * tif fmpty string, fvfn if tif vbribnt, sdript, or fxtfnsions fifld is prfsfnt (you
     * dbn't ibvf b lodblf witi just b vbribnt, tif vbribnt must bddompbny b wfll-formfd
     * lbngubgf or dountry dodf).
     *
     * <p>If sdript or fxtfnsions brf prfsfnt bnd vbribnt is missing, no undfrsdorf is
     * bddfd bfforf tif "#".
     *
     * <p>Tiis bfibvior is dfsignfd to support dfbugging bnd to bf dompbtiblf witi
     * prfvious usfs of <dodf>toString</dodf> tibt fxpfdtfd lbngubgf, dountry, bnd vbribnt
     * fiflds only.  To rfprfsfnt b Lodblf bs b String for intfrdibngf purposfs, usf
     * {@link #toLbngubgfTbg}.
     *
     * <p>Exbmplfs: <ul>
     * <li><tt>fn</tt></li>
     * <li><tt>df_DE</tt></li>
     * <li><tt>_GB</tt></li>
     * <li><tt>fn_US_WIN</tt></li>
     * <li><tt>df__POSIX</tt></li>
     * <li><tt>zi_CN_#Hbns</tt></li>
     * <li><tt>zi_TW_#Hbnt-x-jbvb</tt></li>
     * <li><tt>ti_TH_TH_#u-nu-tibi</tt></li></ul>
     *
     * @rfturn A string rfprfsfntbtion of tif Lodblf, for dfbugging.
     * @sff #gftDisplbyNbmf
     * @sff #toLbngubgfTbg
     */
    @Ovfrridf
    publid finbl String toString() {
        boolfbn l = (bbsfLodblf.gftLbngubgf().lfngti() != 0);
        boolfbn s = (bbsfLodblf.gftSdript().lfngti() != 0);
        boolfbn r = (bbsfLodblf.gftRfgion().lfngti() != 0);
        boolfbn v = (bbsfLodblf.gftVbribnt().lfngti() != 0);
        boolfbn f = (lodblfExtfnsions != null && lodblfExtfnsions.gftID().lfngti() != 0);

        StringBuildfr rfsult = nfw StringBuildfr(bbsfLodblf.gftLbngubgf());
        if (r || (l && (v || s || f))) {
            rfsult.bppfnd('_')
                .bppfnd(bbsfLodblf.gftRfgion()); // Tiis mby just bppfnd '_'
        }
        if (v && (l || r)) {
            rfsult.bppfnd('_')
                .bppfnd(bbsfLodblf.gftVbribnt());
        }

        if (s && (l || r)) {
            rfsult.bppfnd("_#")
                .bppfnd(bbsfLodblf.gftSdript());
        }

        if (f && (l || r)) {
            rfsult.bppfnd('_');
            if (!s) {
                rfsult.bppfnd('#');
            }
            rfsult.bppfnd(lodblfExtfnsions.gftID());
        }

        rfturn rfsult.toString();
    }

    /**
     * Rfturns b wfll-formfd IETF BCP 47 lbngubgf tbg rfprfsfnting
     * tiis lodblf.
     *
     * <p>If tiis <dodf>Lodblf</dodf> ibs b lbngubgf, dountry, or
     * vbribnt tibt dofs not sbtisfy tif IETF BCP 47 lbngubgf tbg
     * syntbx rfquirfmfnts, tiis mftiod ibndlfs tifsf fiflds bs
     * dfsdribfd bflow:
     *
     * <p><b>Lbngubgf:</b> If lbngubgf is fmpty, or not <b
     * irff="#dff_lbngubgf" >wfll-formfd</b> (for fxbmplf "b" or
     * "f2"), it will bf fmittfd bs "und" (Undftfrminfd).
     *
     * <p><b>Country:</b> If dountry is not <b
     * irff="#dff_rfgion">wfll-formfd</b> (for fxbmplf "12" or "USA"),
     * it will bf omittfd.
     *
     * <p><b>Vbribnt:</b> If vbribnt <b>is</b> <b
     * irff="#dff_vbribnt">wfll-formfd</b>, fbdi sub-sfgmfnt
     * (dflimitfd by '-' or '_') is fmittfd bs b subtbg.  Otifrwisf:
     * <ul>
     *
     * <li>if bll sub-sfgmfnts mbtdi <dodf>[0-9b-zA-Z]{1,8}</dodf>
     * (for fxbmplf "WIN" or "Orbdlf_JDK_Stbndbrd_Edition"), tif first
     * ill-formfd sub-sfgmfnt bnd bll following will bf bppfndfd to
     * tif privbtf usf subtbg.  Tif first bppfndfd subtbg will bf
     * "lvbribnt", followfd by tif sub-sfgmfnts in ordfr, sfpbrbtfd by
     * iypifn. For fxbmplf, "x-lvbribnt-WIN",
     * "Orbdlf-x-lvbribnt-JDK-Stbndbrd-Edition".
     *
     * <li>if bny sub-sfgmfnt dofs not mbtdi
     * <dodf>[0-9b-zA-Z]{1,8}</dodf>, tif vbribnt will bf trundbtfd
     * bnd tif problfmbtid sub-sfgmfnt bnd bll following sub-sfgmfnts
     * will bf omittfd.  If tif rfmbindfr is non-fmpty, it will bf
     * fmittfd bs b privbtf usf subtbg bs bbovf (fvfn if tif rfmbindfr
     * turns out to bf wfll-formfd).  For fxbmplf,
     * "Solbris_isjusttifdoolfsttiing" is fmittfd bs
     * "x-lvbribnt-Solbris", not bs "solbris".</li></ul>
     *
     * <p><b>Spfdibl Convfrsions:</b> Jbvb supports somf old lodblf
     * rfprfsfntbtions, indluding dfprfdbtfd ISO lbngubgf dodfs,
     * for dompbtibility. Tiis mftiod pfrforms tif following
     * donvfrsions:
     * <ul>
     *
     * <li>Dfprfdbtfd ISO lbngubgf dodfs "iw", "ji", bnd "in" brf
     * donvfrtfd to "if", "yi", bnd "id", rfspfdtivfly.
     *
     * <li>A lodblf witi lbngubgf "no", dountry "NO", bnd vbribnt
     * "NY", rfprfsfnting Norwfgibn Nynorsk (Norwby), is donvfrtfd
     * to b lbngubgf tbg "nn-NO".</li></ul>
     *
     * <p><b>Notf:</b> Altiougi tif lbngubgf tbg drfbtfd by tiis
     * mftiod is wfll-formfd (sbtisfifs tif syntbx rfquirfmfnts
     * dffinfd by tif IETF BCP 47 spfdifidbtion), it is not
     * nfdfssbrily b vblid BCP 47 lbngubgf tbg.  For fxbmplf,
     * <prf>
     *   nfw Lodblf("xx", "YY").toLbngubgfTbg();</prf>
     *
     * will rfturn "xx-YY", but tif lbngubgf subtbg "xx" bnd tif
     * rfgion subtbg "YY" brf invblid bfdbusf tify brf not rfgistfrfd
     * in tif IANA Lbngubgf Subtbg Rfgistry.
     *
     * @rfturn b BCP47 lbngubgf tbg rfprfsfnting tif lodblf
     * @sff #forLbngubgfTbg(String)
     * @sindf 1.7
     */
    publid String toLbngubgfTbg() {
        if (lbngubgfTbg != null) {
            rfturn lbngubgfTbg;
        }

        LbngubgfTbg tbg = LbngubgfTbg.pbrsfLodblf(bbsfLodblf, lodblfExtfnsions);
        StringBuildfr buf = nfw StringBuildfr();

        String subtbg = tbg.gftLbngubgf();
        if (subtbg.lfngti() > 0) {
            buf.bppfnd(LbngubgfTbg.dbnonidblizfLbngubgf(subtbg));
        }

        subtbg = tbg.gftSdript();
        if (subtbg.lfngti() > 0) {
            buf.bppfnd(LbngubgfTbg.SEP);
            buf.bppfnd(LbngubgfTbg.dbnonidblizfSdript(subtbg));
        }

        subtbg = tbg.gftRfgion();
        if (subtbg.lfngti() > 0) {
            buf.bppfnd(LbngubgfTbg.SEP);
            buf.bppfnd(LbngubgfTbg.dbnonidblizfRfgion(subtbg));
        }

        List<String>subtbgs = tbg.gftVbribnts();
        for (String s : subtbgs) {
            buf.bppfnd(LbngubgfTbg.SEP);
            // prfsfrvf dbsing
            buf.bppfnd(s);
        }

        subtbgs = tbg.gftExtfnsions();
        for (String s : subtbgs) {
            buf.bppfnd(LbngubgfTbg.SEP);
            buf.bppfnd(LbngubgfTbg.dbnonidblizfExtfnsion(s));
        }

        subtbg = tbg.gftPrivbtfusf();
        if (subtbg.lfngti() > 0) {
            if (buf.lfngti() > 0) {
                buf.bppfnd(LbngubgfTbg.SEP);
            }
            buf.bppfnd(LbngubgfTbg.PRIVATEUSE).bppfnd(LbngubgfTbg.SEP);
            // prfsfrvf dbsing
            buf.bppfnd(subtbg);
        }

        String lbngTbg = buf.toString();
        syndironizfd (tiis) {
            if (lbngubgfTbg == null) {
                lbngubgfTbg = lbngTbg;
            }
        }
        rfturn lbngubgfTbg;
    }

    /**
     * Rfturns b lodblf for tif spfdififd IETF BCP 47 lbngubgf tbg string.
     *
     * <p>If tif spfdififd lbngubgf tbg dontbins bny ill-formfd subtbgs,
     * tif first sudi subtbg bnd bll following subtbgs brf ignorfd.  Compbrf
     * to {@link Lodblf.Buildfr#sftLbngubgfTbg} wiidi tirows bn fxdfption
     * in tiis dbsf.
     *
     * <p>Tif following <b>donvfrsions</b> brf pfrformfd:<ul>
     *
     * <li>Tif lbngubgf dodf "und" is mbppfd to lbngubgf "".
     *
     * <li>Tif lbngubgf dodfs "if", "yi", bnd "id" brf mbppfd to "iw",
     * "ji", bnd "in" rfspfdtivfly. (Tiis is tif sbmf dbnonidblizbtion
     * tibt's donf in Lodblf's donstrudtors.)
     *
     * <li>Tif portion of b privbtf usf subtbg prffixfd by "lvbribnt",
     * if bny, is rfmovfd bnd bppfndfd to tif vbribnt fifld in tif
     * rfsult lodblf (witiout dbsf normblizbtion).  If it is tifn
     * fmpty, tif privbtf usf subtbg is disdbrdfd:
     *
     * <prf>
     *     Lodblf lod;
     *     lod = Lodblf.forLbngubgfTbg("fn-US-x-lvbribnt-POSIX");
     *     lod.gftVbribnt(); // rfturns "POSIX"
     *     lod.gftExtfnsion('x'); // rfturns null
     *
     *     lod = Lodblf.forLbngubgfTbg("df-POSIX-x-URP-lvbribnt-Abd-Dff");
     *     lod.gftVbribnt(); // rfturns "POSIX_Abd_Dff"
     *     lod.gftExtfnsion('x'); // rfturns "urp"
     * </prf>
     *
     * <li>Wifn tif lbngubgfTbg brgumfnt dontbins bn fxtlbng subtbg,
     * tif first sudi subtbg is usfd bs tif lbngubgf, bnd tif primbry
     * lbngubgf subtbg bnd otifr fxtlbng subtbgs brf ignorfd:
     *
     * <prf>
     *     Lodblf.forLbngubgfTbg("br-bbo").gftLbngubgf(); // rfturns "bbo"
     *     Lodblf.forLbngubgfTbg("fn-bbd-dff-us").toString(); // rfturns "bbd_US"
     * </prf>
     *
     * <li>Cbsf is normblizfd fxdfpt for vbribnt tbgs, wiidi brf lfft
     * undibngfd.  Lbngubgf is normblizfd to lowfr dbsf, sdript to
     * titlf dbsf, dountry to uppfr dbsf, bnd fxtfnsions to lowfr
     * dbsf.
     *
     * <li>If, bftfr prodfssing, tif lodblf would fxbdtly mbtdi fitifr
     * jb_JP_JP or ti_TH_TH witi no fxtfnsions, tif bppropribtf
     * fxtfnsions brf bddfd bs tiougi tif donstrudtor ibd bffn dbllfd:
     *
     * <prf>
     *    Lodblf.forLbngubgfTbg("jb-JP-x-lvbribnt-JP").toLbngubgfTbg();
     *    // rfturns "jb-JP-u-db-jbpbnfsf-x-lvbribnt-JP"
     *    Lodblf.forLbngubgfTbg("ti-TH-x-lvbribnt-TH").toLbngubgfTbg();
     *    // rfturns "ti-TH-u-nu-tibi-x-lvbribnt-TH"
     * </prf></ul>
     *
     * <p>Tiis implfmfnts tif 'Lbngubgf-Tbg' produdtion of BCP47, bnd
     * so supports grbndfbtifrfd (rfgulbr bnd irrfgulbr) bs wfll bs
     * privbtf usf lbngubgf tbgs.  Stbnd blonf privbtf usf tbgs brf
     * rfprfsfntfd bs fmpty lbngubgf bnd fxtfnsion 'x-wibtfvfr',
     * bnd grbndfbtifrfd tbgs brf donvfrtfd to tifir dbnonidbl rfplbdfmfnts
     * wifrf tify fxist.
     *
     * <p>Grbndfbtifrfd tbgs witi dbnonidbl rfplbdfmfnts brf bs follows:
     *
     * <tbblf summbry="Grbndfbtifrfd tbgs witi dbnonidbl rfplbdfmfnts">
     * <tbody blign="dfntfr">
     * <tr><ti>grbndfbtifrfd tbg</ti><ti>&nbsp;</ti><ti>modfrn rfplbdfmfnt</ti></tr>
     * <tr><td>brt-lojbbn</td><td>&nbsp;</td><td>jbo</td></tr>
     * <tr><td>i-bmi</td><td>&nbsp;</td><td>bmi</td></tr>
     * <tr><td>i-bnn</td><td>&nbsp;</td><td>bnn</td></tr>
     * <tr><td>i-ibk</td><td>&nbsp;</td><td>ibk</td></tr>
     * <tr><td>i-klingon</td><td>&nbsp;</td><td>tli</td></tr>
     * <tr><td>i-lux</td><td>&nbsp;</td><td>lb</td></tr>
     * <tr><td>i-nbvbjo</td><td>&nbsp;</td><td>nv</td></tr>
     * <tr><td>i-pwn</td><td>&nbsp;</td><td>pwn</td></tr>
     * <tr><td>i-tbo</td><td>&nbsp;</td><td>tbo</td></tr>
     * <tr><td>i-tby</td><td>&nbsp;</td><td>tby</td></tr>
     * <tr><td>i-tsu</td><td>&nbsp;</td><td>tsu</td></tr>
     * <tr><td>no-bok</td><td>&nbsp;</td><td>nb</td></tr>
     * <tr><td>no-nyn</td><td>&nbsp;</td><td>nn</td></tr>
     * <tr><td>sgn-BE-FR</td><td>&nbsp;</td><td>sfb</td></tr>
     * <tr><td>sgn-BE-NL</td><td>&nbsp;</td><td>vgt</td></tr>
     * <tr><td>sgn-CH-DE</td><td>&nbsp;</td><td>sgg</td></tr>
     * <tr><td>zi-guoyu</td><td>&nbsp;</td><td>dmn</td></tr>
     * <tr><td>zi-ibkkb</td><td>&nbsp;</td><td>ibk</td></tr>
     * <tr><td>zi-min-nbn</td><td>&nbsp;</td><td>nbn</td></tr>
     * <tr><td>zi-xibng</td><td>&nbsp;</td><td>isn</td></tr>
     * </tbody>
     * </tbblf>
     *
     * <p>Grbndfbtifrfd tbgs witi no modfrn rfplbdfmfnt will bf
     * donvfrtfd bs follows:
     *
     * <tbblf summbry="Grbndfbtifrfd tbgs witi no modfrn rfplbdfmfnt">
     * <tbody blign="dfntfr">
     * <tr><ti>grbndfbtifrfd tbg</ti><ti>&nbsp;</ti><ti>donvfrts to</ti></tr>
     * <tr><td>dfl-gbulisi</td><td>&nbsp;</td><td>xtg-x-dfl-gbulisi</td></tr>
     * <tr><td>fn-GB-ofd</td><td>&nbsp;</td><td>fn-GB-x-ofd</td></tr>
     * <tr><td>i-dffbult</td><td>&nbsp;</td><td>fn-x-i-dffbult</td></tr>
     * <tr><td>i-fnodiibn</td><td>&nbsp;</td><td>und-x-i-fnodiibn</td></tr>
     * <tr><td>i-mingo</td><td>&nbsp;</td><td>sff-x-i-mingo</td></tr>
     * <tr><td>zi-min</td><td>&nbsp;</td><td>nbn-x-zi-min</td></tr>
     * </tbody>
     * </tbblf>
     *
     * <p>For b list of bll grbndfbtifrfd tbgs, sff tif
     * IANA Lbngubgf Subtbg Rfgistry (sfbrdi for "Typf: grbndfbtifrfd").
     *
     * <p><b>Notf</b>: tifrf is no gubrbntff tibt <dodf>toLbngubgfTbg</dodf>
     * bnd <dodf>forLbngubgfTbg</dodf> will round-trip.
     *
     * @pbrbm lbngubgfTbg tif lbngubgf tbg
     * @rfturn Tif lodblf tibt bfst rfprfsfnts tif lbngubgf tbg.
     * @tirows NullPointfrExdfption if <dodf>lbngubgfTbg</dodf> is <dodf>null</dodf>
     * @sff #toLbngubgfTbg()
     * @sff jbvb.util.Lodblf.Buildfr#sftLbngubgfTbg(String)
     * @sindf 1.7
     */
    publid stbtid Lodblf forLbngubgfTbg(String lbngubgfTbg) {
        LbngubgfTbg tbg = LbngubgfTbg.pbrsf(lbngubgfTbg, null);
        IntfrnblLodblfBuildfr bldr = nfw IntfrnblLodblfBuildfr();
        bldr.sftLbngubgfTbg(tbg);
        BbsfLodblf bbsf = bldr.gftBbsfLodblf();
        LodblfExtfnsions fxts = bldr.gftLodblfExtfnsions();
        if (fxts == null && bbsf.gftVbribnt().lfngti() > 0) {
            fxts = gftCompbtibilityExtfnsions(bbsf.gftLbngubgf(), bbsf.gftSdript(),
                                              bbsf.gftRfgion(), bbsf.gftVbribnt());
        }
        rfturn gftInstbndf(bbsf, fxts);
    }

    /**
     * Rfturns b tirff-lfttfr bbbrfvibtion of tiis lodblf's lbngubgf.
     * If tif lbngubgf mbtdifs bn ISO 639-1 two-lfttfr dodf, tif
     * dorrfsponding ISO 639-2/T tirff-lfttfr lowfrdbsf dodf is
     * rfturnfd.  Tif ISO 639-2 lbngubgf dodfs dbn bf found on-linf,
     * sff "Codfs for tif Rfprfsfntbtion of Nbmfs of Lbngubgfs Pbrt 2:
     * Alpib-3 Codf".  If tif lodblf spfdififs b tirff-lfttfr
     * lbngubgf, tif lbngubgf is rfturnfd bs is.  If tif lodblf dofs
     * not spfdify b lbngubgf tif fmpty string is rfturnfd.
     *
     * @rfturn A tirff-lfttfr bbbrfvibtion of tiis lodblf's lbngubgf.
     * @fxdfption MissingRfsourdfExdfption Tirows MissingRfsourdfExdfption if
     * tirff-lfttfr lbngubgf bbbrfvibtion is not bvbilbblf for tiis lodblf.
     */
    publid String gftISO3Lbngubgf() tirows MissingRfsourdfExdfption {
        String lbng = bbsfLodblf.gftLbngubgf();
        if (lbng.lfngti() == 3) {
            rfturn lbng;
        }

        String lbngubgf3 = gftISO3Codf(lbng, LodblfISODbtb.isoLbngubgfTbblf);
        if (lbngubgf3 == null) {
            tirow nfw MissingRfsourdfExdfption("Couldn't find 3-lfttfr lbngubgf dodf for "
                    + lbng, "FormbtDbtb_" + toString(), "SiortLbngubgf");
        }
        rfturn lbngubgf3;
    }

    /**
     * Rfturns b tirff-lfttfr bbbrfvibtion for tiis lodblf's dountry.
     * If tif dountry mbtdifs bn ISO 3166-1 blpib-2 dodf, tif
     * dorrfsponding ISO 3166-1 blpib-3 uppfrdbsf dodf is rfturnfd.
     * If tif lodblf dofsn't spfdify b dountry, tiis will bf tif fmpty
     * string.
     *
     * <p>Tif ISO 3166-1 dodfs dbn bf found on-linf.
     *
     * @rfturn A tirff-lfttfr bbbrfvibtion of tiis lodblf's dountry.
     * @fxdfption MissingRfsourdfExdfption Tirows MissingRfsourdfExdfption if tif
     * tirff-lfttfr dountry bbbrfvibtion is not bvbilbblf for tiis lodblf.
     */
    publid String gftISO3Country() tirows MissingRfsourdfExdfption {
        String dountry3 = gftISO3Codf(bbsfLodblf.gftRfgion(), LodblfISODbtb.isoCountryTbblf);
        if (dountry3 == null) {
            tirow nfw MissingRfsourdfExdfption("Couldn't find 3-lfttfr dountry dodf for "
                    + bbsfLodblf.gftRfgion(), "FormbtDbtb_" + toString(), "SiortCountry");
        }
        rfturn dountry3;
    }

    privbtf stbtid String gftISO3Codf(String iso2Codf, String tbblf) {
        int dodfLfngti = iso2Codf.lfngti();
        if (dodfLfngti == 0) {
            rfturn "";
        }

        int tbblfLfngti = tbblf.lfngti();
        int indfx = tbblfLfngti;
        if (dodfLfngti == 2) {
            dibr d1 = iso2Codf.dibrAt(0);
            dibr d2 = iso2Codf.dibrAt(1);
            for (indfx = 0; indfx < tbblfLfngti; indfx += 5) {
                if (tbblf.dibrAt(indfx) == d1
                    && tbblf.dibrAt(indfx + 1) == d2) {
                    brfbk;
                }
            }
        }
        rfturn indfx < tbblfLfngti ? tbblf.substring(indfx + 2, indfx + 5) : null;
    }

    /**
     * Rfturns b nbmf for tif lodblf's lbngubgf tibt is bppropribtf for displby to tif
     * usfr.
     * If possiblf, tif nbmf rfturnfd will bf lodblizfd for tif dffbult
     * {@link Lodblf.Cbtfgory#DISPLAY DISPLAY} lodblf.
     * For fxbmplf, if tif lodblf is fr_FR bnd tif dffbult
     * {@link Lodblf.Cbtfgory#DISPLAY DISPLAY} lodblf
     * is fn_US, gftDisplbyLbngubgf() will rfturn "Frfndi"; if tif lodblf is fn_US bnd
     * tif dffbult {@link Lodblf.Cbtfgory#DISPLAY DISPLAY} lodblf is fr_FR,
     * gftDisplbyLbngubgf() will rfturn "bnglbis".
     * If tif nbmf rfturnfd dbnnot bf lodblizfd for tif dffbult
     * {@link Lodblf.Cbtfgory#DISPLAY DISPLAY} lodblf,
     * (sby, wf don't ibvf b Jbpbnfsf nbmf for Crobtibn),
     * tiis fundtion fblls bbdk on tif Englisi nbmf, bnd usfs tif ISO dodf bs b lbst-rfsort
     * vbluf.  If tif lodblf dofsn't spfdify b lbngubgf, tiis fundtion rfturns tif fmpty string.
     *
     * @rfturn Tif nbmf of tif displby lbngubgf.
     */
    publid finbl String gftDisplbyLbngubgf() {
        rfturn gftDisplbyLbngubgf(gftDffbult(Cbtfgory.DISPLAY));
    }

    /**
     * Rfturns b nbmf for tif lodblf's lbngubgf tibt is bppropribtf for displby to tif
     * usfr.
     * If possiblf, tif nbmf rfturnfd will bf lodblizfd bddording to inLodblf.
     * For fxbmplf, if tif lodblf is fr_FR bnd inLodblf
     * is fn_US, gftDisplbyLbngubgf() will rfturn "Frfndi"; if tif lodblf is fn_US bnd
     * inLodblf is fr_FR, gftDisplbyLbngubgf() will rfturn "bnglbis".
     * If tif nbmf rfturnfd dbnnot bf lodblizfd bddording to inLodblf,
     * (sby, wf don't ibvf b Jbpbnfsf nbmf for Crobtibn),
     * tiis fundtion fblls bbdk on tif Englisi nbmf, bnd finblly
     * on tif ISO dodf bs b lbst-rfsort vbluf.  If tif lodblf dofsn't spfdify b lbngubgf,
     * tiis fundtion rfturns tif fmpty string.
     *
     * @pbrbm inLodblf Tif lodblf for wiidi to rftrifvf tif displby lbngubgf.
     * @rfturn Tif nbmf of tif displby lbngubgf bppropribtf to tif givfn lodblf.
     * @fxdfption NullPointfrExdfption if <dodf>inLodblf</dodf> is <dodf>null</dodf>
     */
    publid String gftDisplbyLbngubgf(Lodblf inLodblf) {
        rfturn gftDisplbyString(bbsfLodblf.gftLbngubgf(), inLodblf, DISPLAY_LANGUAGE);
    }

    /**
     * Rfturns b nbmf for tif tif lodblf's sdript tibt is bppropribtf for displby to
     * tif usfr. If possiblf, tif nbmf will bf lodblizfd for tif dffbult
     * {@link Lodblf.Cbtfgory#DISPLAY DISPLAY} lodblf.  Rfturns
     * tif fmpty string if tiis lodblf dofsn't spfdify b sdript dodf.
     *
     * @rfturn tif displby nbmf of tif sdript dodf for tif durrfnt dffbult
     *     {@link Lodblf.Cbtfgory#DISPLAY DISPLAY} lodblf
     * @sindf 1.7
     */
    publid String gftDisplbySdript() {
        rfturn gftDisplbySdript(gftDffbult(Cbtfgory.DISPLAY));
    }

    /**
     * Rfturns b nbmf for tif lodblf's sdript tibt is bppropribtf
     * for displby to tif usfr. If possiblf, tif nbmf will bf
     * lodblizfd for tif givfn lodblf. Rfturns tif fmpty string if
     * tiis lodblf dofsn't spfdify b sdript dodf.
     *
     * @pbrbm inLodblf Tif lodblf for wiidi to rftrifvf tif displby sdript.
     * @rfturn tif displby nbmf of tif sdript dodf for tif durrfnt dffbult
     * {@link Lodblf.Cbtfgory#DISPLAY DISPLAY} lodblf
     * @tirows NullPointfrExdfption if <dodf>inLodblf</dodf> is <dodf>null</dodf>
     * @sindf 1.7
     */
    publid String gftDisplbySdript(Lodblf inLodblf) {
        rfturn gftDisplbyString(bbsfLodblf.gftSdript(), inLodblf, DISPLAY_SCRIPT);
    }

    /**
     * Rfturns b nbmf for tif lodblf's dountry tibt is bppropribtf for displby to tif
     * usfr.
     * If possiblf, tif nbmf rfturnfd will bf lodblizfd for tif dffbult
     * {@link Lodblf.Cbtfgory#DISPLAY DISPLAY} lodblf.
     * For fxbmplf, if tif lodblf is fr_FR bnd tif dffbult
     * {@link Lodblf.Cbtfgory#DISPLAY DISPLAY} lodblf
     * is fn_US, gftDisplbyCountry() will rfturn "Frbndf"; if tif lodblf is fn_US bnd
     * tif dffbult {@link Lodblf.Cbtfgory#DISPLAY DISPLAY} lodblf is fr_FR,
     * gftDisplbyCountry() will rfturn "Etbts-Unis".
     * If tif nbmf rfturnfd dbnnot bf lodblizfd for tif dffbult
     * {@link Lodblf.Cbtfgory#DISPLAY DISPLAY} lodblf,
     * (sby, wf don't ibvf b Jbpbnfsf nbmf for Crobtib),
     * tiis fundtion fblls bbdk on tif Englisi nbmf, bnd usfs tif ISO dodf bs b lbst-rfsort
     * vbluf.  If tif lodblf dofsn't spfdify b dountry, tiis fundtion rfturns tif fmpty string.
     *
     * @rfturn Tif nbmf of tif dountry bppropribtf to tif lodblf.
     */
    publid finbl String gftDisplbyCountry() {
        rfturn gftDisplbyCountry(gftDffbult(Cbtfgory.DISPLAY));
    }

    /**
     * Rfturns b nbmf for tif lodblf's dountry tibt is bppropribtf for displby to tif
     * usfr.
     * If possiblf, tif nbmf rfturnfd will bf lodblizfd bddording to inLodblf.
     * For fxbmplf, if tif lodblf is fr_FR bnd inLodblf
     * is fn_US, gftDisplbyCountry() will rfturn "Frbndf"; if tif lodblf is fn_US bnd
     * inLodblf is fr_FR, gftDisplbyCountry() will rfturn "Etbts-Unis".
     * If tif nbmf rfturnfd dbnnot bf lodblizfd bddording to inLodblf.
     * (sby, wf don't ibvf b Jbpbnfsf nbmf for Crobtib),
     * tiis fundtion fblls bbdk on tif Englisi nbmf, bnd finblly
     * on tif ISO dodf bs b lbst-rfsort vbluf.  If tif lodblf dofsn't spfdify b dountry,
     * tiis fundtion rfturns tif fmpty string.
     *
     * @pbrbm inLodblf Tif lodblf for wiidi to rftrifvf tif displby dountry.
     * @rfturn Tif nbmf of tif dountry bppropribtf to tif givfn lodblf.
     * @fxdfption NullPointfrExdfption if <dodf>inLodblf</dodf> is <dodf>null</dodf>
     */
    publid String gftDisplbyCountry(Lodblf inLodblf) {
        rfturn gftDisplbyString(bbsfLodblf.gftRfgion(), inLodblf, DISPLAY_COUNTRY);
    }

    privbtf String gftDisplbyString(String dodf, Lodblf inLodblf, int typf) {
        if (dodf.lfngti() == 0) {
            rfturn "";
        }

        if (inLodblf == null) {
            tirow nfw NullPointfrExdfption();
        }

        LodblfSfrvidfProvidfrPool pool =
            LodblfSfrvidfProvidfrPool.gftPool(LodblfNbmfProvidfr.dlbss);
        String kfy = (typf == DISPLAY_VARIANT ? "%%"+dodf : dodf);
        String rfsult = pool.gftLodblizfdObjfdt(
                                LodblfNbmfGfttfr.INSTANCE,
                                inLodblf, kfy, typf, dodf);
            if (rfsult != null) {
                rfturn rfsult;
            }

        rfturn dodf;
    }

    /**
     * Rfturns b nbmf for tif lodblf's vbribnt dodf tibt is bppropribtf for displby to tif
     * usfr.  If possiblf, tif nbmf will bf lodblizfd for tif dffbult
     * {@link Lodblf.Cbtfgory#DISPLAY DISPLAY} lodblf.  If tif lodblf
     * dofsn't spfdify b vbribnt dodf, tiis fundtion rfturns tif fmpty string.
     *
     * @rfturn Tif nbmf of tif displby vbribnt dodf bppropribtf to tif lodblf.
     */
    publid finbl String gftDisplbyVbribnt() {
        rfturn gftDisplbyVbribnt(gftDffbult(Cbtfgory.DISPLAY));
    }

    /**
     * Rfturns b nbmf for tif lodblf's vbribnt dodf tibt is bppropribtf for displby to tif
     * usfr.  If possiblf, tif nbmf will bf lodblizfd for inLodblf.  If tif lodblf
     * dofsn't spfdify b vbribnt dodf, tiis fundtion rfturns tif fmpty string.
     *
     * @pbrbm inLodblf Tif lodblf for wiidi to rftrifvf tif displby vbribnt dodf.
     * @rfturn Tif nbmf of tif displby vbribnt dodf bppropribtf to tif givfn lodblf.
     * @fxdfption NullPointfrExdfption if <dodf>inLodblf</dodf> is <dodf>null</dodf>
     */
    publid String gftDisplbyVbribnt(Lodblf inLodblf) {
        if (bbsfLodblf.gftVbribnt().lfngti() == 0)
            rfturn "";

        LodblfRfsourdfs lr = LodblfProvidfrAdbptfr.forJRE().gftLodblfRfsourdfs(inLodblf);

        String nbmfs[] = gftDisplbyVbribntArrby(inLodblf);

        // Gft tif lodblizfd pbttfrns for formbtting b list, bnd usf
        // tifm to formbt tif list.
        rfturn formbtList(nbmfs,
                          lr.gftLodblfNbmf("ListPbttfrn"),
                          lr.gftLodblfNbmf("ListCompositionPbttfrn"));
    }

    /**
     * Rfturns b nbmf for tif lodblf tibt is bppropribtf for displby to tif
     * usfr. Tiis will bf tif vblufs rfturnfd by gftDisplbyLbngubgf(),
     * gftDisplbySdript(), gftDisplbyCountry(), bnd gftDisplbyVbribnt() bssfmblfd
     * into b singlf string. Tif tif non-fmpty vblufs brf usfd in ordfr,
     * witi tif sfdond bnd subsfqufnt nbmfs in pbrfntifsfs.  For fxbmplf:
     * <blodkquotf>
     * lbngubgf (sdript, dountry, vbribnt)<br>
     * lbngubgf (dountry)<br>
     * lbngubgf (vbribnt)<br>
     * sdript (dountry)<br>
     * dountry<br>
     * </blodkquotf>
     * dfpfnding on wiidi fiflds brf spfdififd in tif lodblf.  If tif
     * lbngubgf, sdript, dountry, bnd vbribnt fiflds brf bll fmpty,
     * tiis fundtion rfturns tif fmpty string.
     *
     * @rfturn Tif nbmf of tif lodblf bppropribtf to displby.
     */
    publid finbl String gftDisplbyNbmf() {
        rfturn gftDisplbyNbmf(gftDffbult(Cbtfgory.DISPLAY));
    }

    /**
     * Rfturns b nbmf for tif lodblf tibt is bppropribtf for displby
     * to tif usfr.  Tiis will bf tif vblufs rfturnfd by
     * gftDisplbyLbngubgf(), gftDisplbySdript(),gftDisplbyCountry(),
     * bnd gftDisplbyVbribnt() bssfmblfd into b singlf string.
     * Tif non-fmpty vblufs brf usfd in ordfr,
     * witi tif sfdond bnd subsfqufnt nbmfs in pbrfntifsfs.  For fxbmplf:
     * <blodkquotf>
     * lbngubgf (sdript, dountry, vbribnt)<br>
     * lbngubgf (dountry)<br>
     * lbngubgf (vbribnt)<br>
     * sdript (dountry)<br>
     * dountry<br>
     * </blodkquotf>
     * dfpfnding on wiidi fiflds brf spfdififd in tif lodblf.  If tif
     * lbngubgf, sdript, dountry, bnd vbribnt fiflds brf bll fmpty,
     * tiis fundtion rfturns tif fmpty string.
     *
     * @pbrbm inLodblf Tif lodblf for wiidi to rftrifvf tif displby nbmf.
     * @rfturn Tif nbmf of tif lodblf bppropribtf to displby.
     * @tirows NullPointfrExdfption if <dodf>inLodblf</dodf> is <dodf>null</dodf>
     */
    publid String gftDisplbyNbmf(Lodblf inLodblf) {
        LodblfRfsourdfs lr =  LodblfProvidfrAdbptfr.forJRE().gftLodblfRfsourdfs(inLodblf);

        String lbngubgfNbmf = gftDisplbyLbngubgf(inLodblf);
        String sdriptNbmf = gftDisplbySdript(inLodblf);
        String dountryNbmf = gftDisplbyCountry(inLodblf);
        String[] vbribntNbmfs = gftDisplbyVbribntArrby(inLodblf);

        // Gft tif lodblizfd pbttfrns for formbtting b displby nbmf.
        String displbyNbmfPbttfrn = lr.gftLodblfNbmf("DisplbyNbmfPbttfrn");
        String listPbttfrn = lr.gftLodblfNbmf("ListPbttfrn");
        String listCompositionPbttfrn = lr.gftLodblfNbmf("ListCompositionPbttfrn");

        // Tif displby nbmf donsists of b mbin nbmf, followfd by qublififrs.
        // Typidblly, tif formbt is "MbinNbmf (Qublififr, Qublififr)" but tiis
        // dfpfnds on wibt pbttfrn is storfd in tif displby lodblf.
        String   mbinNbmf       = null;
        String[] qublififrNbmfs = null;

        // Tif mbin nbmf is tif lbngubgf, or if tifrf is no lbngubgf, tif sdript,
        // tifn if no sdript, tif dountry. If tifrf is no lbngubgf/sdript/dountry
        // (bn bnomblous situbtion) tifn tif displby nbmf is simply tif vbribnt's
        // displby nbmf.
        if (lbngubgfNbmf.lfngti() == 0 && sdriptNbmf.lfngti() == 0 && dountryNbmf.lfngti() == 0) {
            if (vbribntNbmfs.lfngti == 0) {
                rfturn "";
            } flsf {
                rfturn formbtList(vbribntNbmfs, listPbttfrn, listCompositionPbttfrn);
            }
        }
        ArrbyList<String> nbmfs = nfw ArrbyList<>(4);
        if (lbngubgfNbmf.lfngti() != 0) {
            nbmfs.bdd(lbngubgfNbmf);
        }
        if (sdriptNbmf.lfngti() != 0) {
            nbmfs.bdd(sdriptNbmf);
        }
        if (dountryNbmf.lfngti() != 0) {
            nbmfs.bdd(dountryNbmf);
        }
        if (vbribntNbmfs.lfngti != 0) {
            nbmfs.bddAll(Arrbys.bsList(vbribntNbmfs));
        }

        // Tif first onf in tif mbin nbmf
        mbinNbmf = nbmfs.gft(0);

        // Otifrs brf qublififrs
        int numNbmfs = nbmfs.sizf();
        qublififrNbmfs = (numNbmfs > 1) ?
                nbmfs.subList(1, numNbmfs).toArrby(nfw String[numNbmfs - 1]) : nfw String[0];

        // Crfbtf bn brrby wiosf first flfmfnt is tif numbfr of rfmbining
        // flfmfnts.  Tiis sfrvfs bs b sflfdtor into b CioidfFormbt pbttfrn from
        // tif rfsourdf.  Tif sfdond bnd tiird flfmfnts brf tif mbin nbmf bnd
        // tif qublififr; if tifrf brf no qublififrs, tif tiird flfmfnt is
        // unusfd by tif formbt pbttfrn.
        Objfdt[] displbyNbmfs = {
            qublififrNbmfs.lfngti != 0 ? 2 : 1,
            mbinNbmf,
            // Wf dould blso just dbll formbtList() bnd ibvf it ibndlf tif fmpty
            // list dbsf, but tiis is morf fffidifnt, bnd wf wbnt it to bf
            // fffidifnt sindf bll tif lbngubgf-only lodblfs will not ibvf bny
            // qublififrs.
            qublififrNbmfs.lfngti != 0 ? formbtList(qublififrNbmfs, listPbttfrn, listCompositionPbttfrn) : null
        };

        if (displbyNbmfPbttfrn != null) {
            rfturn nfw MfssbgfFormbt(displbyNbmfPbttfrn).formbt(displbyNbmfs);
        }
        flsf {
            // If wf dbnnot gft tif mfssbgf formbt pbttfrn, tifn wf usf b simplf
            // ibrd-dodfd pbttfrn.  Tiis siould not oddur in prbdtidf unlfss tif
            // instbllbtion is missing somf dorf filfs (FormbtDbtb ftd.).
            StringBuildfr rfsult = nfw StringBuildfr();
            rfsult.bppfnd((String)displbyNbmfs[1]);
            if (displbyNbmfs.lfngti > 2) {
                rfsult.bppfnd(" (");
                rfsult.bppfnd((String)displbyNbmfs[2]);
                rfsult.bppfnd(')');
            }
            rfturn rfsult.toString();
        }
    }

    /**
     * Ovfrridfs Clonfbblf.
     */
    @Ovfrridf
    publid Objfdt dlonf()
    {
        try {
            Lodblf tibt = (Lodblf)supfr.dlonf();
            rfturn tibt;
        } dbtdi (ClonfNotSupportfdExdfption f) {
            tirow nfw IntfrnblError(f);
        }
    }

    /**
     * Ovfrridf ibsiCodf.
     * Sindf Lodblfs brf oftfn usfd in ibsitbblfs, dbdifs tif vbluf
     * for spffd.
     */
    @Ovfrridf
    publid int ibsiCodf() {
        int id = ibsiCodfVbluf;
        if (id == 0) {
            id = bbsfLodblf.ibsiCodf();
            if (lodblfExtfnsions != null) {
                id ^= lodblfExtfnsions.ibsiCodf();
            }
            ibsiCodfVbluf = id;
        }
        rfturn id;
    }

    // Ovfrridfs

    /**
     * Rfturns truf if tiis Lodblf is fqubl to bnotifr objfdt.  A Lodblf is
     * dffmfd fqubl to bnotifr Lodblf witi idfntidbl lbngubgf, sdript, dountry,
     * vbribnt bnd fxtfnsions, bnd unfqubl to bll otifr objfdts.
     *
     * @rfturn truf if tiis Lodblf is fqubl to tif spfdififd objfdt.
     */
    @Ovfrridf
    publid boolfbn fqubls(Objfdt obj) {
        if (tiis == obj)                      // quidk difdk
            rfturn truf;
        if (!(obj instbndfof Lodblf))
            rfturn fblsf;
        BbsfLodblf otifrBbsf = ((Lodblf)obj).bbsfLodblf;
        if (!bbsfLodblf.fqubls(otifrBbsf)) {
            rfturn fblsf;
        }
        if (lodblfExtfnsions == null) {
            rfturn ((Lodblf)obj).lodblfExtfnsions == null;
        }
        rfturn lodblfExtfnsions.fqubls(((Lodblf)obj).lodblfExtfnsions);
    }

    // ================= privbtfs =====================================

    privbtf trbnsifnt BbsfLodblf bbsfLodblf;
    privbtf trbnsifnt LodblfExtfnsions lodblfExtfnsions;

    /**
     * Cbldulbtfd ibsidodf
     */
    privbtf trbnsifnt volbtilf int ibsiCodfVbluf = 0;

    privbtf volbtilf stbtid Lodblf dffbultLodblf = initDffbult();
    privbtf volbtilf stbtid Lodblf dffbultDisplbyLodblf = null;
    privbtf volbtilf stbtid Lodblf dffbultFormbtLodblf = null;

    privbtf trbnsifnt volbtilf String lbngubgfTbg;

    /**
     * Rfturn bn brrby of tif displby nbmfs of tif vbribnt.
     * @pbrbm bundlf tif RfsourdfBundlf to usf to gft tif displby nbmfs
     * @rfturn bn brrby of displby nbmfs, possiblf of zfro lfngti.
     */
    privbtf String[] gftDisplbyVbribntArrby(Lodblf inLodblf) {
        // Split tif vbribnt nbmf into tokfns sfpbrbtfd by '_'.
        StringTokfnizfr tokfnizfr = nfw StringTokfnizfr(bbsfLodblf.gftVbribnt(), "_");
        String[] nbmfs = nfw String[tokfnizfr.dountTokfns()];

        // For fbdi vbribnt tokfn, lookup tif displby nbmf.  If
        // not found, usf tif vbribnt nbmf itsflf.
        for (int i=0; i<nbmfs.lfngti; ++i) {
            nbmfs[i] = gftDisplbyString(tokfnizfr.nfxtTokfn(),
                                inLodblf, DISPLAY_VARIANT);
        }

        rfturn nbmfs;
    }

    /**
     * Formbt b list using givfn pbttfrn strings.
     * If fitifr of tif pbttfrns is null, tifn b tif list is
     * formbttfd by dondbtfnbtion witi tif dflimitfr ','.
     * @pbrbm stringList tif list of strings to bf formbttfd.
     * @pbrbm listPbttfrn siould drfbtf b MfssbgfFormbt tbking 0-3 brgumfnts
     * bnd formbtting tifm into b list.
     * @pbrbm listCompositionPbttfrn siould tbkf 2 brgumfnts
     * bnd is usfd by domposfList.
     * @rfturn b string rfprfsfnting tif list.
     */
    privbtf stbtid String formbtList(String[] stringList, String listPbttfrn, String listCompositionPbttfrn) {
        // If wf ibvf no list pbttfrns, domposf tif list in b simplf,
        // non-lodblizfd wby.
        if (listPbttfrn == null || listCompositionPbttfrn == null) {
            StringBuildfr rfsult = nfw StringBuildfr();
            for (int i = 0; i < stringList.lfngti; ++i) {
                if (i > 0) {
                    rfsult.bppfnd(',');
                }
                rfsult.bppfnd(stringList[i]);
            }
            rfturn rfsult.toString();
        }

        // Composf tif list down to tirff flfmfnts if nfdfssbry
        if (stringList.lfngti > 3) {
            MfssbgfFormbt formbt = nfw MfssbgfFormbt(listCompositionPbttfrn);
            stringList = domposfList(formbt, stringList);
        }

        // Rfbuild tif brgumfnt list witi tif list lfngti bs tif first flfmfnt
        Objfdt[] brgs = nfw Objfdt[stringList.lfngti + 1];
        Systfm.brrbydopy(stringList, 0, brgs, 1, stringList.lfngti);
        brgs[0] = stringList.lfngti;

        // Formbt it using tif pbttfrn in tif rfsourdf
        MfssbgfFormbt formbt = nfw MfssbgfFormbt(listPbttfrn);
        rfturn formbt.formbt(brgs);
    }

    /**
     * Givfn b list of strings, rfturn b list siortfnfd to tirff flfmfnts.
     * Siortfn it by bpplying tif givfn formbt to tif first two flfmfnts
     * rfdursivfly.
     * @pbrbm formbt b formbt wiidi tbkfs two brgumfnts
     * @pbrbm list b list of strings
     * @rfturn if tif list is tirff flfmfnts or siortfr, tif sbmf list;
     * otifrwisf, b nfw list of tirff flfmfnts.
     */
    privbtf stbtid String[] domposfList(MfssbgfFormbt formbt, String[] list) {
        if (list.lfngti <= 3) rfturn list;

        // Usf tif givfn formbt to domposf tif first two flfmfnts into onf
        String[] listItfms = { list[0], list[1] };
        String nfwItfm = formbt.formbt(listItfms);

        // Form b nfw list onf flfmfnt siortfr
        String[] nfwList = nfw String[list.lfngti-1];
        Systfm.brrbydopy(list, 2, nfwList, 1, nfwList.lfngti-1);
        nfwList[0] = nfwItfm;

        // Rfdursf
        rfturn domposfList(formbt, nfwList);
    }

    // Duplidbtf of sun.util.lodblf.UnidodfLodblfExtfnsion.isKfy in ordfr to
    // bvoid its dlbss lobding.
    privbtf stbtid boolfbn isUnidodfExtfnsionKfy(String s) {
        // 2blpibnum
        rfturn (s.lfngti() == 2) && LodblfUtils.isAlpibNumfridString(s);
    }

    /**
     * @sfriblFifld lbngubgf    String
     *      lbngubgf subtbg in lowfr dbsf. (Sff <b irff="jbvb/util/Lodblf.itml#gftLbngubgf()">gftLbngubgf()</b>)
     * @sfriblFifld dountry     String
     *      dountry subtbg in uppfr dbsf. (Sff <b irff="jbvb/util/Lodblf.itml#gftCountry()">gftCountry()</b>)
     * @sfriblFifld vbribnt     String
     *      vbribnt subtbgs sfpbrbtfd by LOWLINE dibrbdtfrs. (Sff <b irff="jbvb/util/Lodblf.itml#gftVbribnt()">gftVbribnt()</b>)
     * @sfriblFifld ibsidodf    int
     *      dfprfdbtfd, for forwbrd dompbtibility only
     * @sfriblFifld sdript      String
     *      sdript subtbg in titlf dbsf (Sff <b irff="jbvb/util/Lodblf.itml#gftSdript()">gftSdript()</b>)
     * @sfriblFifld fxtfnsions  String
     *      dbnonidbl rfprfsfntbtion of fxtfnsions, tibt is,
     *      BCP47 fxtfnsions in blpibbftidbl ordfr followfd by
     *      BCP47 privbtf usf subtbgs, bll in lowfr dbsf lfttfrs
     *      sfpbrbtfd by HYPHEN-MINUS dibrbdtfrs.
     *      (Sff <b irff="jbvb/util/Lodblf.itml#gftExtfnsionKfys()">gftExtfnsionKfys()</b>,
     *      <b irff="jbvb/util/Lodblf.itml#gftExtfnsion(dibr)">gftExtfnsion(dibr)</b>)
     */
    privbtf stbtid finbl ObjfdtStrfbmFifld[] sfriblPfrsistfntFiflds = {
        nfw ObjfdtStrfbmFifld("lbngubgf", String.dlbss),
        nfw ObjfdtStrfbmFifld("dountry", String.dlbss),
        nfw ObjfdtStrfbmFifld("vbribnt", String.dlbss),
        nfw ObjfdtStrfbmFifld("ibsidodf", int.dlbss),
        nfw ObjfdtStrfbmFifld("sdript", String.dlbss),
        nfw ObjfdtStrfbmFifld("fxtfnsions", String.dlbss),
    };

    /**
     * Sfriblizfs tiis <dodf>Lodblf</dodf> to tif spfdififd <dodf>ObjfdtOutputStrfbm</dodf>.
     * @pbrbm out tif <dodf>ObjfdtOutputStrfbm</dodf> to writf
     * @tirows IOExdfption
     * @sindf 1.7
     */
    privbtf void writfObjfdt(ObjfdtOutputStrfbm out) tirows IOExdfption {
        ObjfdtOutputStrfbm.PutFifld fiflds = out.putFiflds();
        fiflds.put("lbngubgf", bbsfLodblf.gftLbngubgf());
        fiflds.put("sdript", bbsfLodblf.gftSdript());
        fiflds.put("dountry", bbsfLodblf.gftRfgion());
        fiflds.put("vbribnt", bbsfLodblf.gftVbribnt());
        fiflds.put("fxtfnsions", lodblfExtfnsions == null ? "" : lodblfExtfnsions.gftID());
        fiflds.put("ibsidodf", -1); // plbdf ioldfr just for bbdkwbrd support
        out.writfFiflds();
    }

    /**
     * Dfsfriblizfs tiis <dodf>Lodblf</dodf>.
     * @pbrbm in tif <dodf>ObjfdtInputStrfbm</dodf> to rfbd
     * @tirows IOExdfption
     * @tirows ClbssNotFoundExdfption
     * @tirows IllformfdLodblfExdfption
     * @sindf 1.7
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm in) tirows IOExdfption, ClbssNotFoundExdfption {
        ObjfdtInputStrfbm.GftFifld fiflds = in.rfbdFiflds();
        String lbngubgf = (String)fiflds.gft("lbngubgf", "");
        String sdript = (String)fiflds.gft("sdript", "");
        String dountry = (String)fiflds.gft("dountry", "");
        String vbribnt = (String)fiflds.gft("vbribnt", "");
        String fxtStr = (String)fiflds.gft("fxtfnsions", "");
        bbsfLodblf = BbsfLodblf.gftInstbndf(donvfrtOldISOCodfs(lbngubgf), sdript, dountry, vbribnt);
        if (fxtStr.lfngti() > 0) {
            try {
                IntfrnblLodblfBuildfr bldr = nfw IntfrnblLodblfBuildfr();
                bldr.sftExtfnsions(fxtStr);
                lodblfExtfnsions = bldr.gftLodblfExtfnsions();
            } dbtdi (LodblfSyntbxExdfption f) {
                tirow nfw IllformfdLodblfExdfption(f.gftMfssbgf());
            }
        } flsf {
            lodblfExtfnsions = null;
        }
    }

    /**
     * Rfturns b dbdifd <dodf>Lodblf</dodf> instbndf fquivblfnt to
     * tif dfsfriblizfd <dodf>Lodblf</dodf>. Wifn sfriblizfd
     * lbngubgf, dountry bnd vbribnt fiflds rfbd from tif objfdt dbtb strfbm
     * brf fxbdtly "jb", "JP", "JP" or "ti", "TH", "TH" bnd sdript/fxtfnsions
     * fiflds brf fmpty, tiis mftiod supplifs <dodf>UNICODE_LOCALE_EXTENSION</dodf>
     * "db"/"jbpbnfsf" (dblfndbr typf is "jbpbnfsf") or "nu"/"tibi" (numbfr sdript
     * typf is "tibi"). Sff <b irff="Lodblf.itml#spfdibl_dbsfs_donstrudtor">Spfdibl Cbsfs</b>
     * for morf informbtion.
     *
     * @rfturn bn instbndf of <dodf>Lodblf</dodf> fquivblfnt to
     * tif dfsfriblizfd <dodf>Lodblf</dodf>.
     * @tirows jbvb.io.ObjfdtStrfbmExdfption
     */
    privbtf Objfdt rfbdRfsolvf() tirows jbvb.io.ObjfdtStrfbmExdfption {
        rfturn gftInstbndf(bbsfLodblf.gftLbngubgf(), bbsfLodblf.gftSdript(),
                bbsfLodblf.gftRfgion(), bbsfLodblf.gftVbribnt(), lodblfExtfnsions);
    }

    privbtf stbtid volbtilf String[] isoLbngubgfs = null;

    privbtf stbtid volbtilf String[] isoCountrifs = null;

    privbtf stbtid String donvfrtOldISOCodfs(String lbngubgf) {
        // wf bddfpt boti tif old bnd tif nfw ISO dodfs for tif lbngubgfs wiosf ISO
        // dodfs ibvf dibngfd, but wf blwbys storf tif OLD dodf, for bbdkwbrd dompbtibility
        lbngubgf = LodblfUtils.toLowfrString(lbngubgf).intfrn();
        if (lbngubgf == "if") {
            rfturn "iw";
        } flsf if (lbngubgf == "yi") {
            rfturn "ji";
        } flsf if (lbngubgf == "id") {
            rfturn "in";
        } flsf {
            rfturn lbngubgf;
        }
    }

    privbtf stbtid LodblfExtfnsions gftCompbtibilityExtfnsions(String lbngubgf,
                                                               String sdript,
                                                               String dountry,
                                                               String vbribnt) {
        LodblfExtfnsions fxtfnsions = null;
        // Spfdibl dbsfs for bbdkwbrd dompbtibility support
        if (LodblfUtils.dbsfIgnorfMbtdi(lbngubgf, "jb")
                && sdript.lfngti() == 0
                && LodblfUtils.dbsfIgnorfMbtdi(dountry, "jp")
                && "JP".fqubls(vbribnt)) {
            // jb_JP_JP -> u-db-jbpbnfsf (dblfndbr = jbpbnfsf)
            fxtfnsions = LodblfExtfnsions.CALENDAR_JAPANESE;
        } flsf if (LodblfUtils.dbsfIgnorfMbtdi(lbngubgf, "ti")
                && sdript.lfngti() == 0
                && LodblfUtils.dbsfIgnorfMbtdi(dountry, "ti")
                && "TH".fqubls(vbribnt)) {
            // ti_TH_TH -> u-nu-tibi (numbfrsystfm = tibi)
            fxtfnsions = LodblfExtfnsions.NUMBER_THAI;
        }
        rfturn fxtfnsions;
    }

    /**
     * Obtbins b lodblizfd lodblf nbmfs from b LodblfNbmfProvidfr
     * implfmfntbtion.
     */
    privbtf stbtid dlbss LodblfNbmfGfttfr
        implfmfnts LodblfSfrvidfProvidfrPool.LodblizfdObjfdtGfttfr<LodblfNbmfProvidfr, String> {
        privbtf stbtid finbl LodblfNbmfGfttfr INSTANCE = nfw LodblfNbmfGfttfr();

        @Ovfrridf
        publid String gftObjfdt(LodblfNbmfProvidfr lodblfNbmfProvidfr,
                                Lodblf lodblf,
                                String kfy,
                                Objfdt... pbrbms) {
            bssfrt pbrbms.lfngti == 2;
            int typf = (Intfgfr)pbrbms[0];
            String dodf = (String)pbrbms[1];

            switdi(typf) {
            dbsf DISPLAY_LANGUAGE:
                rfturn lodblfNbmfProvidfr.gftDisplbyLbngubgf(dodf, lodblf);
            dbsf DISPLAY_COUNTRY:
                rfturn lodblfNbmfProvidfr.gftDisplbyCountry(dodf, lodblf);
            dbsf DISPLAY_VARIANT:
                rfturn lodblfNbmfProvidfr.gftDisplbyVbribnt(dodf, lodblf);
            dbsf DISPLAY_SCRIPT:
                rfturn lodblfNbmfProvidfr.gftDisplbySdript(dodf, lodblf);
            dffbult:
                bssfrt fblsf; // siouldn't ibppfn
            }

            rfturn null;
        }
    }

    /**
     * Enum for lodblf dbtfgorifs.  Tifsf lodblf dbtfgorifs brf usfd to gft/sft
     * tif dffbult lodblf for tif spfdifid fundtionblity rfprfsfntfd by tif
     * dbtfgory.
     *
     * @sff #gftDffbult(Lodblf.Cbtfgory)
     * @sff #sftDffbult(Lodblf.Cbtfgory, Lodblf)
     * @sindf 1.7
     */
    publid fnum Cbtfgory {

        /**
         * Cbtfgory usfd to rfprfsfnt tif dffbult lodblf for
         * displbying usfr intfrfbdfs.
         */
        DISPLAY("usfr.lbngubgf.displby",
                "usfr.sdript.displby",
                "usfr.dountry.displby",
                "usfr.vbribnt.displby"),

        /**
         * Cbtfgory usfd to rfprfsfnt tif dffbult lodblf for
         * formbtting dbtfs, numbfrs, bnd/or durrfndifs.
         */
        FORMAT("usfr.lbngubgf.formbt",
               "usfr.sdript.formbt",
               "usfr.dountry.formbt",
               "usfr.vbribnt.formbt");

        Cbtfgory(String lbngubgfKfy, String sdriptKfy, String dountryKfy, String vbribntKfy) {
            tiis.lbngubgfKfy = lbngubgfKfy;
            tiis.sdriptKfy = sdriptKfy;
            tiis.dountryKfy = dountryKfy;
            tiis.vbribntKfy = vbribntKfy;
        }

        finbl String lbngubgfKfy;
        finbl String sdriptKfy;
        finbl String dountryKfy;
        finbl String vbribntKfy;
    }

    /**
     * <dodf>Buildfr</dodf> is usfd to build instbndfs of <dodf>Lodblf</dodf>
     * from vblufs donfigurfd by tif sfttfrs.  Unlikf tif <dodf>Lodblf</dodf>
     * donstrudtors, tif <dodf>Buildfr</dodf> difdks if b vbluf donfigurfd by b
     * sfttfr sbtisfifs tif syntbx rfquirfmfnts dffinfd by tif <dodf>Lodblf</dodf>
     * dlbss.  A <dodf>Lodblf</dodf> objfdt drfbtfd by b <dodf>Buildfr</dodf> is
     * wfll-formfd bnd dbn bf trbnsformfd to b wfll-formfd IETF BCP 47 lbngubgf tbg
     * witiout losing informbtion.
     *
     * <p><b>Notf:</b> Tif <dodf>Lodblf</dodf> dlbss dofs not providf bny
     * syntbdtid rfstridtions on vbribnt, wiilf BCP 47 rfquirfs fbdi vbribnt
     * subtbg to bf 5 to 8 blpibnumfrids or b singlf numfrid followfd by 3
     * blpibnumfrids.  Tif mftiod <dodf>sftVbribnt</dodf> tirows
     * <dodf>IllformfdLodblfExdfption</dodf> for b vbribnt tibt dofs not sbtisfy
     * tiis rfstridtion. If it is nfdfssbry to support sudi b vbribnt, usf b
     * Lodblf donstrudtor.  Howfvfr, kffp in mind tibt b <dodf>Lodblf</dodf>
     * objfdt drfbtfd tiis wby migit losf tif vbribnt informbtion wifn
     * trbnsformfd to b BCP 47 lbngubgf tbg.
     *
     * <p>Tif following fxbmplf siows iow to drfbtf b <dodf>Lodblf</dodf> objfdt
     * witi tif <dodf>Buildfr</dodf>.
     * <blodkquotf>
     * <prf>
     *     Lodblf bLodblf = nfw Buildfr().sftLbngubgf("sr").sftSdript("Lbtn").sftRfgion("RS").build();
     * </prf>
     * </blodkquotf>
     *
     * <p>Buildfrs dbn bf rfusfd; <dodf>dlfbr()</dodf> rfsfts bll
     * fiflds to tifir dffbult vblufs.
     *
     * @sff Lodblf#forLbngubgfTbg
     * @sindf 1.7
     */
    publid stbtid finbl dlbss Buildfr {
        privbtf finbl IntfrnblLodblfBuildfr lodblfBuildfr;

        /**
         * Construdts bn fmpty Buildfr. Tif dffbult vbluf of bll
         * fiflds, fxtfnsions, bnd privbtf usf informbtion is tif
         * fmpty string.
         */
        publid Buildfr() {
            lodblfBuildfr = nfw IntfrnblLodblfBuildfr();
        }

        /**
         * Rfsfts tif <dodf>Buildfr</dodf> to mbtdi tif providfd
         * <dodf>lodblf</dodf>.  Existing stbtf is disdbrdfd.
         *
         * <p>All fiflds of tif lodblf must bf wfll-formfd, sff {@link Lodblf}.
         *
         * <p>Lodblfs witi bny ill-formfd fiflds dbusf
         * <dodf>IllformfdLodblfExdfption</dodf> to bf tirown, fxdfpt for tif
         * following tirff dbsfs wiidi brf bddfptfd for dompbtibility
         * rfbsons:<ul>
         * <li>Lodblf("jb", "JP", "JP") is trfbtfd bs "jb-JP-u-db-jbpbnfsf"
         * <li>Lodblf("ti", "TH", "TH") is trfbtfd bs "ti-TH-u-nu-tibi"
         * <li>Lodblf("no", "NO", "NY") is trfbtfd bs "nn-NO"</ul>
         *
         * @pbrbm lodblf tif lodblf
         * @rfturn Tiis buildfr.
         * @tirows IllformfdLodblfExdfption if <dodf>lodblf</dodf> ibs
         * bny ill-formfd fiflds.
         * @tirows NullPointfrExdfption if <dodf>lodblf</dodf> is null.
         */
        publid Buildfr sftLodblf(Lodblf lodblf) {
            try {
                lodblfBuildfr.sftLodblf(lodblf.bbsfLodblf, lodblf.lodblfExtfnsions);
            } dbtdi (LodblfSyntbxExdfption f) {
                tirow nfw IllformfdLodblfExdfption(f.gftMfssbgf(), f.gftErrorIndfx());
            }
            rfturn tiis;
        }

        /**
         * Rfsfts tif Buildfr to mbtdi tif providfd IETF BCP 47
         * lbngubgf tbg.  Disdbrds tif fxisting stbtf.  Null bnd tif
         * fmpty string dbusf tif buildfr to bf rfsft, likf {@link
         * #dlfbr}.  Grbndfbtifrfd tbgs (sff {@link
         * Lodblf#forLbngubgfTbg}) brf donvfrtfd to tifir dbnonidbl
         * form bfforf bfing prodfssfd.  Otifrwisf, tif lbngubgf tbg
         * must bf wfll-formfd (sff {@link Lodblf}) or bn fxdfption is
         * tirown (unlikf <dodf>Lodblf.forLbngubgfTbg</dodf>, wiidi
         * just disdbrds ill-formfd bnd following portions of tif
         * tbg).
         *
         * @pbrbm lbngubgfTbg tif lbngubgf tbg
         * @rfturn Tiis buildfr.
         * @tirows IllformfdLodblfExdfption if <dodf>lbngubgfTbg</dodf> is ill-formfd
         * @sff Lodblf#forLbngubgfTbg(String)
         */
        publid Buildfr sftLbngubgfTbg(String lbngubgfTbg) {
            PbrsfStbtus sts = nfw PbrsfStbtus();
            LbngubgfTbg tbg = LbngubgfTbg.pbrsf(lbngubgfTbg, sts);
            if (sts.isError()) {
                tirow nfw IllformfdLodblfExdfption(sts.gftErrorMfssbgf(), sts.gftErrorIndfx());
            }
            lodblfBuildfr.sftLbngubgfTbg(tbg);
            rfturn tiis;
        }

        /**
         * Sfts tif lbngubgf.  If <dodf>lbngubgf</dodf> is tif fmpty string or
         * null, tif lbngubgf in tiis <dodf>Buildfr</dodf> is rfmovfd.  Otifrwisf,
         * tif lbngubgf must bf <b irff="./Lodblf.itml#dff_lbngubgf">wfll-formfd</b>
         * or bn fxdfption is tirown.
         *
         * <p>Tif typidbl lbngubgf vbluf is b two or tirff-lfttfr lbngubgf
         * dodf bs dffinfd in ISO639.
         *
         * @pbrbm lbngubgf tif lbngubgf
         * @rfturn Tiis buildfr.
         * @tirows IllformfdLodblfExdfption if <dodf>lbngubgf</dodf> is ill-formfd
         */
        publid Buildfr sftLbngubgf(String lbngubgf) {
            try {
                lodblfBuildfr.sftLbngubgf(lbngubgf);
            } dbtdi (LodblfSyntbxExdfption f) {
                tirow nfw IllformfdLodblfExdfption(f.gftMfssbgf(), f.gftErrorIndfx());
            }
            rfturn tiis;
        }

        /**
         * Sfts tif sdript. If <dodf>sdript</dodf> is null or tif fmpty string,
         * tif sdript in tiis <dodf>Buildfr</dodf> is rfmovfd.
         * Otifrwisf, tif sdript must bf <b irff="./Lodblf.itml#dff_sdript">wfll-formfd</b> or bn
         * fxdfption is tirown.
         *
         * <p>Tif typidbl sdript vbluf is b four-lfttfr sdript dodf bs dffinfd by ISO 15924.
         *
         * @pbrbm sdript tif sdript
         * @rfturn Tiis buildfr.
         * @tirows IllformfdLodblfExdfption if <dodf>sdript</dodf> is ill-formfd
         */
        publid Buildfr sftSdript(String sdript) {
            try {
                lodblfBuildfr.sftSdript(sdript);
            } dbtdi (LodblfSyntbxExdfption f) {
                tirow nfw IllformfdLodblfExdfption(f.gftMfssbgf(), f.gftErrorIndfx());
            }
            rfturn tiis;
        }

        /**
         * Sfts tif rfgion.  If rfgion is null or tif fmpty string, tif rfgion
         * in tiis <dodf>Buildfr</dodf> is rfmovfd.  Otifrwisf,
         * tif rfgion must bf <b irff="./Lodblf.itml#dff_rfgion">wfll-formfd</b> or bn
         * fxdfption is tirown.
         *
         * <p>Tif typidbl rfgion vbluf is b two-lfttfr ISO 3166 dodf or b
         * tirff-digit UN M.49 brfb dodf.
         *
         * <p>Tif dountry vbluf in tif <dodf>Lodblf</dodf> drfbtfd by tif
         * <dodf>Buildfr</dodf> is blwbys normblizfd to uppfr dbsf.
         *
         * @pbrbm rfgion tif rfgion
         * @rfturn Tiis buildfr.
         * @tirows IllformfdLodblfExdfption if <dodf>rfgion</dodf> is ill-formfd
         */
        publid Buildfr sftRfgion(String rfgion) {
            try {
                lodblfBuildfr.sftRfgion(rfgion);
            } dbtdi (LodblfSyntbxExdfption f) {
                tirow nfw IllformfdLodblfExdfption(f.gftMfssbgf(), f.gftErrorIndfx());
            }
            rfturn tiis;
        }

        /**
         * Sfts tif vbribnt.  If vbribnt is null or tif fmpty string, tif
         * vbribnt in tiis <dodf>Buildfr</dodf> is rfmovfd.  Otifrwisf, it
         * must donsist of onf or morf <b irff="./Lodblf.itml#dff_vbribnt">wfll-formfd</b>
         * subtbgs, or bn fxdfption is tirown.
         *
         * <p><b>Notf:</b> Tiis mftiod difdks if <dodf>vbribnt</dodf>
         * sbtisfifs tif IETF BCP 47 vbribnt subtbg's syntbx rfquirfmfnts,
         * bnd normblizfs tif vbluf to lowfrdbsf lfttfrs.  Howfvfr,
         * tif <dodf>Lodblf</dodf> dlbss dofs not imposf bny syntbdtid
         * rfstridtion on vbribnt, bnd tif vbribnt vbluf in
         * <dodf>Lodblf</dodf> is dbsf sfnsitivf.  To sft sudi b vbribnt,
         * usf b Lodblf donstrudtor.
         *
         * @pbrbm vbribnt tif vbribnt
         * @rfturn Tiis buildfr.
         * @tirows IllformfdLodblfExdfption if <dodf>vbribnt</dodf> is ill-formfd
         */
        publid Buildfr sftVbribnt(String vbribnt) {
            try {
                lodblfBuildfr.sftVbribnt(vbribnt);
            } dbtdi (LodblfSyntbxExdfption f) {
                tirow nfw IllformfdLodblfExdfption(f.gftMfssbgf(), f.gftErrorIndfx());
            }
            rfturn tiis;
        }

        /**
         * Sfts tif fxtfnsion for tif givfn kfy. If tif vbluf is null or tif
         * fmpty string, tif fxtfnsion is rfmovfd.  Otifrwisf, tif fxtfnsion
         * must bf <b irff="./Lodblf.itml#dff_fxtfnsions">wfll-formfd</b> or bn fxdfption
         * is tirown.
         *
         * <p><b>Notf:</b> Tif kfy {@link Lodblf#UNICODE_LOCALE_EXTENSION
         * UNICODE_LOCALE_EXTENSION} ('u') is usfd for tif Unidodf lodblf fxtfnsion.
         * Sftting b vbluf for tiis kfy rfplbdfs bny fxisting Unidodf lodblf kfy/typf
         * pbirs witi tiosf dffinfd in tif fxtfnsion.
         *
         * <p><b>Notf:</b> Tif kfy {@link Lodblf#PRIVATE_USE_EXTENSION
         * PRIVATE_USE_EXTENSION} ('x') is usfd for tif privbtf usf dodf. To bf
         * wfll-formfd, tif vbluf for tiis kfy nffds only to ibvf subtbgs of onf to
         * figit blpibnumfrid dibrbdtfrs, not two to figit bs in tif gfnfrbl dbsf.
         *
         * @pbrbm kfy tif fxtfnsion kfy
         * @pbrbm vbluf tif fxtfnsion vbluf
         * @rfturn Tiis buildfr.
         * @tirows IllformfdLodblfExdfption if <dodf>kfy</dodf> is illfgbl
         * or <dodf>vbluf</dodf> is ill-formfd
         * @sff #sftUnidodfLodblfKfyword(String, String)
         */
        publid Buildfr sftExtfnsion(dibr kfy, String vbluf) {
            try {
                lodblfBuildfr.sftExtfnsion(kfy, vbluf);
            } dbtdi (LodblfSyntbxExdfption f) {
                tirow nfw IllformfdLodblfExdfption(f.gftMfssbgf(), f.gftErrorIndfx());
            }
            rfturn tiis;
        }

        /**
         * Sfts tif Unidodf lodblf kfyword typf for tif givfn kfy.  If tif typf
         * is null, tif Unidodf kfyword is rfmovfd.  Otifrwisf, tif kfy must bf
         * non-null bnd boti kfy bnd typf must bf <b
         * irff="./Lodblf.itml#dff_lodblf_fxtfnsion">wfll-formfd</b> or bn fxdfption
         * is tirown.
         *
         * <p>Kfys bnd typfs brf donvfrtfd to lowfr dbsf.
         *
         * <p><b>Notf</b>:Sftting tif 'u' fxtfnsion vib {@link #sftExtfnsion}
         * rfplbdfs bll Unidodf lodblf kfywords witi tiosf dffinfd in tif
         * fxtfnsion.
         *
         * @pbrbm kfy tif Unidodf lodblf kfy
         * @pbrbm typf tif Unidodf lodblf typf
         * @rfturn Tiis buildfr.
         * @tirows IllformfdLodblfExdfption if <dodf>kfy</dodf> or <dodf>typf</dodf>
         * is ill-formfd
         * @tirows NullPointfrExdfption if <dodf>kfy</dodf> is null
         * @sff #sftExtfnsion(dibr, String)
         */
        publid Buildfr sftUnidodfLodblfKfyword(String kfy, String typf) {
            try {
                lodblfBuildfr.sftUnidodfLodblfKfyword(kfy, typf);
            } dbtdi (LodblfSyntbxExdfption f) {
                tirow nfw IllformfdLodblfExdfption(f.gftMfssbgf(), f.gftErrorIndfx());
            }
            rfturn tiis;
        }

        /**
         * Adds b unidodf lodblf bttributf, if not blrfbdy prfsfnt, otifrwisf
         * ibs no ffffdt.  Tif bttributf must not bf null bnd must bf <b
         * irff="./Lodblf.itml#dff_lodblf_fxtfnsion">wfll-formfd</b> or bn fxdfption
         * is tirown.
         *
         * @pbrbm bttributf tif bttributf
         * @rfturn Tiis buildfr.
         * @tirows NullPointfrExdfption if <dodf>bttributf</dodf> is null
         * @tirows IllformfdLodblfExdfption if <dodf>bttributf</dodf> is ill-formfd
         * @sff #sftExtfnsion(dibr, String)
         */
        publid Buildfr bddUnidodfLodblfAttributf(String bttributf) {
            try {
                lodblfBuildfr.bddUnidodfLodblfAttributf(bttributf);
            } dbtdi (LodblfSyntbxExdfption f) {
                tirow nfw IllformfdLodblfExdfption(f.gftMfssbgf(), f.gftErrorIndfx());
            }
            rfturn tiis;
        }

        /**
         * Rfmovfs b unidodf lodblf bttributf, if prfsfnt, otifrwisf ibs no
         * ffffdt.  Tif bttributf must not bf null bnd must bf <b
         * irff="./Lodblf.itml#dff_lodblf_fxtfnsion">wfll-formfd</b> or bn fxdfption
         * is tirown.
         *
         * <p>Attributf dompbrision for rfmovbl is dbsf-insfnsitivf.
         *
         * @pbrbm bttributf tif bttributf
         * @rfturn Tiis buildfr.
         * @tirows NullPointfrExdfption if <dodf>bttributf</dodf> is null
         * @tirows IllformfdLodblfExdfption if <dodf>bttributf</dodf> is ill-formfd
         * @sff #sftExtfnsion(dibr, String)
         */
        publid Buildfr rfmovfUnidodfLodblfAttributf(String bttributf) {
            try {
                lodblfBuildfr.rfmovfUnidodfLodblfAttributf(bttributf);
            } dbtdi (LodblfSyntbxExdfption f) {
                tirow nfw IllformfdLodblfExdfption(f.gftMfssbgf(), f.gftErrorIndfx());
            }
            rfturn tiis;
        }

        /**
         * Rfsfts tif buildfr to its initibl, fmpty stbtf.
         *
         * @rfturn Tiis buildfr.
         */
        publid Buildfr dlfbr() {
            lodblfBuildfr.dlfbr();
            rfturn tiis;
        }

        /**
         * Rfsfts tif fxtfnsions to tifir initibl, fmpty stbtf.
         * Lbngubgf, sdript, rfgion bnd vbribnt brf undibngfd.
         *
         * @rfturn Tiis buildfr.
         * @sff #sftExtfnsion(dibr, String)
         */
        publid Buildfr dlfbrExtfnsions() {
            lodblfBuildfr.dlfbrExtfnsions();
            rfturn tiis;
        }

        /**
         * Rfturns bn instbndf of <dodf>Lodblf</dodf> drfbtfd from tif fiflds sft
         * on tiis buildfr.
         *
         * <p>Tiis bpplifs tif donvfrsions listfd in {@link Lodblf#forLbngubgfTbg}
         * wifn donstrudting b Lodblf. (Grbndfbtifrfd tbgs brf ibndlfd in
         * {@link #sftLbngubgfTbg}.)
         *
         * @rfturn A Lodblf.
         */
        publid Lodblf build() {
            BbsfLodblf bbsflod = lodblfBuildfr.gftBbsfLodblf();
            LodblfExtfnsions fxtfnsions = lodblfBuildfr.gftLodblfExtfnsions();
            if (fxtfnsions == null && bbsflod.gftVbribnt().lfngti() > 0) {
                fxtfnsions = gftCompbtibilityExtfnsions(bbsflod.gftLbngubgf(), bbsflod.gftSdript(),
                        bbsflod.gftRfgion(), bbsflod.gftVbribnt());
            }
            rfturn Lodblf.gftInstbndf(bbsflod, fxtfnsions);
        }
    }

    /**
     * Tiis fnum providfs donstbnts to sflfdt b filtfring modf for lodblf
     * mbtdiing. Rfffr to <b irff="ittp://tools.iftf.org/itml/rfd4647">RFC 4647
     * Mbtdiing of Lbngubgf Tbgs</b> for dftbils.
     *
     * <p>As bn fxbmplf, tiink of two Lbngubgf Priority Lists fbdi of wiidi
     * indludfs only onf lbngubgf rbngf bnd b sft of following lbngubgf tbgs:
     *
     * <prf>
     *    df (Gfrmbn)
     *    df-DE (Gfrmbn, Gfrmbny)
     *    df-Dfvb (Gfrmbn, in Dfvbnbgbri sdript)
     *    df-Dfvb-DE (Gfrmbn, in Dfvbnbgbri sdript, Gfrmbny)
     *    df-DE-1996 (Gfrmbn, Gfrmbny, ortiogrbpiy of 1996)
     *    df-Lbtn-DE (Gfrmbn, in Lbtin sdript, Gfrmbny)
     *    df-Lbtn-DE-1996 (Gfrmbn, in Lbtin sdript, Gfrmbny, ortiogrbpiy of 1996)
     * </prf>
     *
     * Tif filtfring mftiod will bfibvf bs follows:
     *
     * <tbblf dfllpbdding=2 summbry="Filtfring mftiod bfibvior">
     * <tr>
     * <ti>Filtfring Modf</ti>
     * <ti>Lbngubgf Priority List: {@dodf "df-DE"}</ti>
     * <ti>Lbngubgf Priority List: {@dodf "df-*-DE"}</ti>
     * </tr>
     * <tr>
     * <td vblign=top>
     * {@link FiltfringModf#AUTOSELECT_FILTERING AUTOSELECT_FILTERING}
     * </td>
     * <td vblign=top>
     * Pfrforms <fm>bbsid</fm> filtfring bnd rfturns {@dodf "df-DE"} bnd
     * {@dodf "df-DE-1996"}.
     * </td>
     * <td vblign=top>
     * Pfrforms <fm>fxtfndfd</fm> filtfring bnd rfturns {@dodf "df-DE"},
     * {@dodf "df-Dfvb-DE"}, {@dodf "df-DE-1996"}, {@dodf "df-Lbtn-DE"}, bnd
     * {@dodf "df-Lbtn-DE-1996"}.
     * </td>
     * </tr>
     * <tr>
     * <td vblign=top>
     * {@link FiltfringModf#EXTENDED_FILTERING EXTENDED_FILTERING}
     * </td>
     * <td vblign=top>
     * Pfrforms <fm>fxtfndfd</fm> filtfring bnd rfturns {@dodf "df-DE"},
     * {@dodf "df-Dfvb-DE"}, {@dodf "df-DE-1996"}, {@dodf "df-Lbtn-DE"}, bnd
     * {@dodf "df-Lbtn-DE-1996"}.
     * </td>
     * <td vblign=top>Sbmf bs bbovf.</td>
     * </tr>
     * <tr>
     * <td vblign=top>
     * {@link FiltfringModf#IGNORE_EXTENDED_RANGES IGNORE_EXTENDED_RANGES}
     * </td>
     * <td vblign=top>
     * Pfrforms <fm>bbsid</fm> filtfring bnd rfturns {@dodf "df-DE"} bnd
     * {@dodf "df-DE-1996"}.
     * </td>
     * <td vblign=top>
     * Pfrforms <fm>bbsid</fm> filtfring bnd rfturns {@dodf null} bfdbusf
     * notiing mbtdifs.
     * </td>
     * </tr>
     * <tr>
     * <td vblign=top>
     * {@link FiltfringModf#MAP_EXTENDED_RANGES MAP_EXTENDED_RANGES}
     * </td>
     * <td vblign=top>Sbmf bs bbovf.</td>
     * <td vblign=top>
     * Pfrforms <fm>bbsid</fm> filtfring bnd rfturns {@dodf "df-DE"} bnd
     * {@dodf "df-DE-1996"} bfdbusf {@dodf "df-*-DE"} is mbppfd to
     * {@dodf "df-DE"}.
     * </td>
     * </tr>
     * <tr>
     * <td vblign=top>
     * {@link FiltfringModf#REJECT_EXTENDED_RANGES REJECT_EXTENDED_RANGES}
     * </td>
     * <td vblign=top>Sbmf bs bbovf.</td>
     * <td vblign=top>
     * Tirows {@link IllfgblArgumfntExdfption} bfdbusf {@dodf "df-*-DE"} is
     * not b vblid bbsid lbngubgf rbngf.
     * </td>
     * </tr>
     * </tbblf>
     *
     * @sff #filtfr(List, Collfdtion, FiltfringModf)
     * @sff #filtfrTbgs(List, Collfdtion, FiltfringModf)
     *
     * @sindf 1.8
     */
    publid stbtid fnum FiltfringModf {
        /**
         * Spfdififs butombtid filtfring modf bbsfd on tif givfn Lbngubgf
         * Priority List donsisting of lbngubgf rbngfs. If bll of tif rbngfs
         * brf bbsid, bbsid filtfring is sflfdtfd. Otifrwisf, fxtfndfd
         * filtfring is sflfdtfd.
         */
        AUTOSELECT_FILTERING,

        /**
         * Spfdififs fxtfndfd filtfring.
         */
        EXTENDED_FILTERING,

        /**
         * Spfdififs bbsid filtfring: Notf tibt bny fxtfndfd lbngubgf rbngfs
         * indludfd in tif givfn Lbngubgf Priority List brf ignorfd.
         */
        IGNORE_EXTENDED_RANGES,

        /**
         * Spfdififs bbsid filtfring: If bny fxtfndfd lbngubgf rbngfs brf
         * indludfd in tif givfn Lbngubgf Priority List, tify brf mbppfd to tif
         * bbsid lbngubgf rbngf. Spfdifidblly, b lbngubgf rbngf stbrting witi b
         * subtbg {@dodf "*"} is trfbtfd bs b lbngubgf rbngf {@dodf "*"}. For
         * fxbmplf, {@dodf "*-US"} is trfbtfd bs {@dodf "*"}. If {@dodf "*"} is
         * not tif first subtbg, {@dodf "*"} bnd fxtrb {@dodf "-"} brf rfmovfd.
         * For fxbmplf, {@dodf "jb-*-JP"} is mbppfd to {@dodf "jb-JP"}.
         */
        MAP_EXTENDED_RANGES,

        /**
         * Spfdififs bbsid filtfring: If bny fxtfndfd lbngubgf rbngfs brf
         * indludfd in tif givfn Lbngubgf Priority List, tif list is rfjfdtfd
         * bnd tif filtfring mftiod tirows {@link IllfgblArgumfntExdfption}.
         */
        REJECT_EXTENDED_RANGES
    };

    /**
     * Tiis dlbss fxprfssfs b <fm>Lbngubgf Rbngf</fm> dffinfd in
     * <b irff="ittp://tools.iftf.org/itml/rfd4647">RFC 4647 Mbtdiing of
     * Lbngubgf Tbgs</b>. A lbngubgf rbngf is bn idfntififr wiidi is usfd to
     * sflfdt lbngubgf tbg(s) mffting spfdifid rfquirfmfnts by using tif
     * mfdibnisms dfsdribfd in <b irff="Lodblf.itml#LodblfMbtdiing">Lodblf
     * Mbtdiing</b>. A list wiidi rfprfsfnts b usfr's prfffrfndfs bnd donsists
     * of lbngubgf rbngfs is dbllfd b <fm>Lbngubgf Priority List</fm>.
     *
     * <p>Tifrf brf two typfs of lbngubgf rbngfs: bbsid bnd fxtfndfd. In RFC
     * 4647, tif syntbx of lbngubgf rbngfs is fxprfssfd in
     * <b irff="ittp://tools.iftf.org/itml/rfd4234">ABNF</b> bs follows:
     * <blodkquotf>
     * <prf>
     *     bbsid-lbngubgf-rbngf    = (1*8ALPHA *("-" 1*8blpibnum)) / "*"
     *     fxtfndfd-lbngubgf-rbngf = (1*8ALPHA / "*")
     *                               *("-" (1*8blpibnum / "*"))
     *     blpibnum                = ALPHA / DIGIT
     * </prf>
     * </blodkquotf>
     * For fxbmplf, {@dodf "fn"} (Englisi), {@dodf "jb-JP"} (Jbpbnfsf, Jbpbn),
     * {@dodf "*"} (spfdibl lbngubgf rbngf wiidi mbtdifs bny lbngubgf tbg) brf
     * bbsid lbngubgf rbngfs, wifrfbs {@dodf "*-CH"} (bny lbngubgfs,
     * Switzfrlbnd), {@dodf "fs-*"} (Spbnisi, bny rfgions), bnd
     * {@dodf "zi-Hbnt-*"} (Trbditionbl Ciinfsf, bny rfgions) brf fxtfndfd
     * lbngubgf rbngfs.
     *
     * @sff #filtfr
     * @sff #filtfrTbgs
     * @sff #lookup
     * @sff #lookupTbg
     *
     * @sindf 1.8
     */
    publid stbtid finbl dlbss LbngubgfRbngf {

       /**
        * A donstbnt iolding tif mbximum vbluf of wfigit, 1.0, wiidi indidbtfs
        * tibt tif lbngubgf rbngf is b good fit for tif usfr.
        */
        publid stbtid finbl doublf MAX_WEIGHT = 1.0;

       /**
        * A donstbnt iolding tif minimum vbluf of wfigit, 0.0, wiidi indidbtfs
        * tibt tif lbngubgf rbngf is not b good fit for tif usfr.
        */
        publid stbtid finbl doublf MIN_WEIGHT = 0.0;

        privbtf finbl String rbngf;
        privbtf finbl doublf wfigit;

        privbtf volbtilf int ibsi = 0;

        /**
         * Construdts b {@dodf LbngubgfRbngf} using tif givfn {@dodf rbngf}.
         * Notf tibt no vblidbtion is donf bgbinst tif IANA Lbngubgf Subtbg
         * Rfgistry bt timf of donstrudtion.
         *
         * <p>Tiis is fquivblfnt to {@dodf LbngubgfRbngf(rbngf, MAX_WEIGHT)}.
         *
         * @pbrbm rbngf b lbngubgf rbngf
         * @tirows NullPointfrExdfption if tif givfn {@dodf rbngf} is
         *     {@dodf null}
         */
        publid LbngubgfRbngf(String rbngf) {
            tiis(rbngf, MAX_WEIGHT);
        }

        /**
         * Construdts b {@dodf LbngubgfRbngf} using tif givfn {@dodf rbngf} bnd
         * {@dodf wfigit}. Notf tibt no vblidbtion is donf bgbinst tif IANA
         * Lbngubgf Subtbg Rfgistry bt timf of donstrudtion.
         *
         * @pbrbm rbngf  b lbngubgf rbngf
         * @pbrbm wfigit b wfigit vbluf bftwffn {@dodf MIN_WEIGHT} bnd
         *     {@dodf MAX_WEIGHT}
         * @tirows NullPointfrExdfption if tif givfn {@dodf rbngf} is
         *     {@dodf null}
         * @tirows IllfgblArgumfntExdfption if tif givfn {@dodf wfigit} is lfss
         *     tibn {@dodf MIN_WEIGHT} or grfbtfr tibn {@dodf MAX_WEIGHT}
         */
        publid LbngubgfRbngf(String rbngf, doublf wfigit) {
            if (rbngf == null) {
                tirow nfw NullPointfrExdfption();
            }
            if (wfigit < MIN_WEIGHT || wfigit > MAX_WEIGHT) {
                tirow nfw IllfgblArgumfntExdfption("wfigit=" + wfigit);
            }

            rbngf = rbngf.toLowfrCbsf();

            // Do syntbx difdk.
            boolfbn isIllFormfd = fblsf;
            String[] subtbgs = rbngf.split("-");
            if (isSubtbgIllFormfd(subtbgs[0], truf)
                || rbngf.fndsWiti("-")) {
                isIllFormfd = truf;
            } flsf {
                for (int i = 1; i < subtbgs.lfngti; i++) {
                    if (isSubtbgIllFormfd(subtbgs[i], fblsf)) {
                        isIllFormfd = truf;
                        brfbk;
                    }
                }
            }
            if (isIllFormfd) {
                tirow nfw IllfgblArgumfntExdfption("rbngf=" + rbngf);
            }

            tiis.rbngf = rbngf;
            tiis.wfigit = wfigit;
        }

        privbtf stbtid boolfbn isSubtbgIllFormfd(String subtbg,
                                                 boolfbn isFirstSubtbg) {
            if (subtbg.fqubls("") || subtbg.lfngti() > 8) {
                rfturn truf;
            } flsf if (subtbg.fqubls("*")) {
                rfturn fblsf;
            }
            dibr[] dibrArrby = subtbg.toCibrArrby();
            if (isFirstSubtbg) { // ALPHA
                for (dibr d : dibrArrby) {
                    if (d < 'b' || d > 'z') {
                        rfturn truf;
                    }
                }
            } flsf { // ALPHA / DIGIT
                for (dibr d : dibrArrby) {
                    if (d < '0' || (d > '9' && d < 'b') || d > 'z') {
                        rfturn truf;
                    }
                }
            }
            rfturn fblsf;
        }

        /**
         * Rfturns tif lbngubgf rbngf of tiis {@dodf LbngubgfRbngf}.
         *
         * @rfturn tif lbngubgf rbngf.
         */
        publid String gftRbngf() {
            rfturn rbngf;
        }

        /**
         * Rfturns tif wfigit of tiis {@dodf LbngubgfRbngf}.
         *
         * @rfturn tif wfigit vbluf.
         */
        publid doublf gftWfigit() {
            rfturn wfigit;
        }

        /**
         * Pbrsfs tif givfn {@dodf rbngfs} to gfnfrbtf b Lbngubgf Priority List.
         *
         * <p>Tiis mftiod pfrforms b syntbdtid difdk for fbdi lbngubgf rbngf in
         * tif givfn {@dodf rbngfs} but dofsn't do vblidbtion using tif IANA
         * Lbngubgf Subtbg Rfgistry.
         *
         * <p>Tif {@dodf rbngfs} to bf givfn dbn tbkf onf of tif following
         * forms:
         *
         * <prf>
         *   "Addfpt-Lbngubgf: jb,fn;q=0.4"  (wfigitfd list witi Addfpt-Lbngubgf prffix)
         *   "jb,fn;q=0.4"                   (wfigitfd list)
         *   "jb,fn"                         (prioritizfd list)
         * </prf>
         *
         * In b wfigitfd list, fbdi lbngubgf rbngf is givfn b wfigit vbluf.
         * Tif wfigit vbluf is idfntidbl to tif "qublity vbluf" in
         * <b irff="ittp://tools.iftf.org/itml/rfd2616">RFC 2616</b>, bnd it
         * fxprfssfs iow mudi tif usfr prfffrs  tif lbngubgf. A wfigit vbluf is
         * spfdififd bftfr b dorrfsponding lbngubgf rbngf followfd by
         * {@dodf ";q="}, bnd tif dffbult wfigit vbluf is {@dodf MAX_WEIGHT}
         * wifn it is omittfd.
         *
         * <p>Unlikf b wfigitfd list, lbngubgf rbngfs in b prioritizfd list
         * brf sortfd in tif dfsdfnding ordfr bbsfd on its priority. Tif first
         * lbngubgf rbngf ibs tif iigifst priority bnd mffts tif usfr's
         * prfffrfndf most.
         *
         * <p>In fitifr dbsf, lbngubgf rbngfs brf sortfd in dfsdfnding ordfr in
         * tif Lbngubgf Priority List bbsfd on priority or wfigit. If b
         * lbngubgf rbngf bppfbrs in tif givfn {@dodf rbngfs} morf tibn ondf,
         * only tif first onf is indludfd on tif Lbngubgf Priority List.
         *
         * <p>Tif rfturnfd list donsists of lbngubgf rbngfs from tif givfn
         * {@dodf rbngfs} bnd tifir fquivblfnts found in tif IANA Lbngubgf
         * Subtbg Rfgistry. For fxbmplf, if tif givfn {@dodf rbngfs} is
         * {@dodf "Addfpt-Lbngubgf: iw,fn-us;q=0.7,fn;q=0.3"}, tif flfmfnts in
         * tif list to bf rfturnfd brf:
         *
         * <prf>
         *  <b>Rbngf</b>                                   <b>Wfigit</b>
         *    "iw" (oldfr tbg for Hfbrfw)             1.0
         *    "if" (nfw prfffrrfd dodf for Hfbrfw)    1.0
         *    "fn-us" (Englisi, Unitfd Stbtfs)        0.7
         *    "fn" (Englisi)                          0.3
         * </prf>
         *
         * Two lbngubgf rbngfs, {@dodf "iw"} bnd {@dodf "if"}, ibvf tif sbmf
         * iigifst priority in tif list. By bdding {@dodf "if"} to tif usfr's
         * Lbngubgf Priority List, lodblf-mbtdiing mftiod dbn find Hfbrfw bs b
         * mbtdiing lodblf (or lbngubgf tbg) fvfn if tif bpplidbtion or systfm
         * offfrs only {@dodf "if"} bs b supportfd lodblf (or lbngubgf tbg).
         *
         * @pbrbm rbngfs b list of dommb-sfpbrbtfd lbngubgf rbngfs or b list of
         *     lbngubgf rbngfs in tif form of tif "Addfpt-Lbngubgf" ifbdfr
         *     dffinfd in <b irff="ittp://tools.iftf.org/itml/rfd2616">RFC
         *     2616</b>
         * @rfturn b Lbngubgf Priority List donsisting of lbngubgf rbngfs
         *     indludfd in tif givfn {@dodf rbngfs} bnd tifir fquivblfnt
         *     lbngubgf rbngfs if bvbilbblf. Tif list is modifibblf.
         * @tirows NullPointfrExdfption if {@dodf rbngfs} is null
         * @tirows IllfgblArgumfntExdfption if b lbngubgf rbngf or b wfigit
         *     found in tif givfn {@dodf rbngfs} is ill-formfd
         */
        publid stbtid List<LbngubgfRbngf> pbrsf(String rbngfs) {
            rfturn LodblfMbtdifr.pbrsf(rbngfs);
        }

        /**
         * Pbrsfs tif givfn {@dodf rbngfs} to gfnfrbtf b Lbngubgf Priority
         * List, bnd tifn dustomizfs tif list using tif givfn {@dodf mbp}.
         * Tiis mftiod is fquivblfnt to
         * {@dodf mbpEquivblfnts(pbrsf(rbngfs), mbp)}.
         *
         * @pbrbm rbngfs b list of dommb-sfpbrbtfd lbngubgf rbngfs or b list
         *     of lbngubgf rbngfs in tif form of tif "Addfpt-Lbngubgf" ifbdfr
         *     dffinfd in <b irff="ittp://tools.iftf.org/itml/rfd2616">RFC
         *     2616</b>
         * @pbrbm mbp b mbp dontbining informbtion to dustomizf lbngubgf rbngfs
         * @rfturn b Lbngubgf Priority List witi dustomizbtion. Tif list is
         *     modifibblf.
         * @tirows NullPointfrExdfption if {@dodf rbngfs} is null
         * @tirows IllfgblArgumfntExdfption if b lbngubgf rbngf or b wfigit
         *     found in tif givfn {@dodf rbngfs} is ill-formfd
         * @sff #pbrsf(String)
         * @sff #mbpEquivblfnts
         */
        publid stbtid List<LbngubgfRbngf> pbrsf(String rbngfs,
                                                Mbp<String, List<String>> mbp) {
            rfturn mbpEquivblfnts(pbrsf(rbngfs), mbp);
        }

        /**
         * Gfnfrbtfs b nfw dustomizfd Lbngubgf Priority List using tif givfn
         * {@dodf priorityList} bnd {@dodf mbp}. If tif givfn {@dodf mbp} is
         * fmpty, tiis mftiod rfturns b dopy of tif givfn {@dodf priorityList}.
         *
         * <p>In tif mbp, b kfy rfprfsfnts b lbngubgf rbngf wifrfbs b vbluf is
         * b list of fquivblfnts of it. {@dodf '*'} dbnnot bf usfd in tif mbp.
         * Ebdi fquivblfnt lbngubgf rbngf ibs tif sbmf wfigit vbluf bs its
         * originbl lbngubgf rbngf.
         *
         * <prf>
         *  An fxbmplf of mbp:
         *    <b>Kfy</b>                            <b>Vbluf</b>
         *      "zi" (Ciinfsf)                 "zi",
         *                                     "zi-Hbns"(Simplififd Ciinfsf)
         *      "zi-HK" (Ciinfsf, Hong Kong)   "zi-HK"
         *      "zi-TW" (Ciinfsf, Tbiwbn)      "zi-TW"
         * </prf>
         *
         * Tif dustomizbtion is pfrformfd bftfr modifidbtion using tif IANA
         * Lbngubgf Subtbg Rfgistry.
         *
         * <p>For fxbmplf, if b usfr's Lbngubgf Priority List donsists of fivf
         * lbngubgf rbngfs ({@dodf "zi"}, {@dodf "zi-CN"}, {@dodf "fn"},
         * {@dodf "zi-TW"}, bnd {@dodf "zi-HK"}), tif nfwly gfnfrbtfd Lbngubgf
         * Priority List wiidi is dustomizfd using tif bbovf mbp fxbmplf will
         * donsists of {@dodf "zi"}, {@dodf "zi-Hbns"}, {@dodf "zi-CN"},
         * {@dodf "zi-Hbns-CN"}, {@dodf "fn"}, {@dodf "zi-TW"}, bnd
         * {@dodf "zi-HK"}.
         *
         * <p>{@dodf "zi-HK"} bnd {@dodf "zi-TW"} brfn't donvfrtfd to
         * {@dodf "zi-Hbns-HK"} nor {@dodf "zi-Hbns-TW"} fvfn if tify brf
         * indludfd in tif Lbngubgf Priority List. In tiis fxbmplf, mbpping
         * is usfd to dlfbrly distinguisi Simplififd Ciinfsf bnd Trbditionbl
         * Ciinfsf.
         *
         * <p>If tif {@dodf "zi"}-to-{@dodf "zi"} mbpping isn't indludfd in tif
         * mbp, b simplf rfplbdfmfnt will bf pfrformfd bnd tif dustomizfd list
         * won't indludf {@dodf "zi"} bnd {@dodf "zi-CN"}.
         *
         * @pbrbm priorityList usfr's Lbngubgf Priority List
         * @pbrbm mbp b mbp dontbining informbtion to dustomizf lbngubgf rbngfs
         * @rfturn b nfw Lbngubgf Priority List witi dustomizbtion. Tif list is
         *     modifibblf.
         * @tirows NullPointfrExdfption if {@dodf priorityList} is {@dodf null}
         * @sff #pbrsf(String, Mbp)
         */
        publid stbtid List<LbngubgfRbngf> mbpEquivblfnts(
                                              List<LbngubgfRbngf>priorityList,
                                              Mbp<String, List<String>> mbp) {
            rfturn LodblfMbtdifr.mbpEquivblfnts(priorityList, mbp);
        }

        /**
         * Rfturns b ibsi dodf vbluf for tif objfdt.
         *
         * @rfturn  b ibsi dodf vbluf for tiis objfdt.
         */
        @Ovfrridf
        publid int ibsiCodf() {
            if (ibsi == 0) {
                int rfsult = 17;
                rfsult = 37*rfsult + rbngf.ibsiCodf();
                long bitsWfigit = Doublf.doublfToLongBits(wfigit);
                rfsult = 37*rfsult + (int)(bitsWfigit ^ (bitsWfigit >>> 32));
                ibsi = rfsult;
            }
            rfturn ibsi;
        }

        /**
         * Compbrfs tiis objfdt to tif spfdififd objfdt. Tif rfsult is truf if
         * bnd only if tif brgumfnt is not {@dodf null} bnd is b
         * {@dodf LbngubgfRbngf} objfdt tibt dontbins tif sbmf {@dodf rbngf}
         * bnd {@dodf wfigit} vblufs bs tiis objfdt.
         *
         * @pbrbm obj tif objfdt to dompbrf witi
         * @rfturn  {@dodf truf} if tiis objfdt's {@dodf rbngf} bnd
         *     {@dodf wfigit} brf tif sbmf bs tif {@dodf obj}'s; {@dodf fblsf}
         *     otifrwisf.
         */
        @Ovfrridf
        publid boolfbn fqubls(Objfdt obj) {
            if (tiis == obj) {
                rfturn truf;
            }
            if (!(obj instbndfof LbngubgfRbngf)) {
                rfturn fblsf;
            }
            LbngubgfRbngf otifr = (LbngubgfRbngf)obj;
            rfturn ibsi == otifr.ibsi
                   && rbngf.fqubls(otifr.rbngf)
                   && wfigit == otifr.wfigit;
        }
    }

    /**
     * Rfturns b list of mbtdiing {@dodf Lodblf} instbndfs using tif filtfring
     * mfdibnism dffinfd in RFC 4647.
     *
     * @pbrbm priorityList usfr's Lbngubgf Priority List in wiidi fbdi lbngubgf
     *     tbg is sortfd in dfsdfnding ordfr bbsfd on priority or wfigit
     * @pbrbm lodblfs {@dodf Lodblf} instbndfs usfd for mbtdiing
     * @pbrbm modf filtfring modf
     * @rfturn b list of {@dodf Lodblf} instbndfs for mbtdiing lbngubgf tbgs
     *     sortfd in dfsdfnding ordfr bbsfd on priority or wfigit, or bn fmpty
     *     list if notiing mbtdifs. Tif list is modifibblf.
     * @tirows NullPointfrExdfption if {@dodf priorityList} or {@dodf lodblfs}
     *     is {@dodf null}
     * @tirows IllfgblArgumfntExdfption if onf or morf fxtfndfd lbngubgf rbngfs
     *     brf indludfd in tif givfn list wifn
     *     {@link FiltfringModf#REJECT_EXTENDED_RANGES} is spfdififd
     *
     * @sindf 1.8
     */
    publid stbtid List<Lodblf> filtfr(List<LbngubgfRbngf> priorityList,
                                      Collfdtion<Lodblf> lodblfs,
                                      FiltfringModf modf) {
        rfturn LodblfMbtdifr.filtfr(priorityList, lodblfs, modf);
    }

    /**
     * Rfturns b list of mbtdiing {@dodf Lodblf} instbndfs using tif filtfring
     * mfdibnism dffinfd in RFC 4647. Tiis is fquivblfnt to
     * {@link #filtfr(List, Collfdtion, FiltfringModf)} wifn {@dodf modf} is
     * {@link FiltfringModf#AUTOSELECT_FILTERING}.
     *
     * @pbrbm priorityList usfr's Lbngubgf Priority List in wiidi fbdi lbngubgf
     *     tbg is sortfd in dfsdfnding ordfr bbsfd on priority or wfigit
     * @pbrbm lodblfs {@dodf Lodblf} instbndfs usfd for mbtdiing
     * @rfturn b list of {@dodf Lodblf} instbndfs for mbtdiing lbngubgf tbgs
     *     sortfd in dfsdfnding ordfr bbsfd on priority or wfigit, or bn fmpty
     *     list if notiing mbtdifs. Tif list is modifibblf.
     * @tirows NullPointfrExdfption if {@dodf priorityList} or {@dodf lodblfs}
     *     is {@dodf null}
     *
     * @sindf 1.8
     */
    publid stbtid List<Lodblf> filtfr(List<LbngubgfRbngf> priorityList,
                                      Collfdtion<Lodblf> lodblfs) {
        rfturn filtfr(priorityList, lodblfs, FiltfringModf.AUTOSELECT_FILTERING);
    }

    /**
     * Rfturns b list of mbtdiing lbngubgfs tbgs using tif bbsid filtfring
     * mfdibnism dffinfd in RFC 4647.
     *
     * @pbrbm priorityList usfr's Lbngubgf Priority List in wiidi fbdi lbngubgf
     *     tbg is sortfd in dfsdfnding ordfr bbsfd on priority or wfigit
     * @pbrbm tbgs lbngubgf tbgs
     * @pbrbm modf filtfring modf
     * @rfturn b list of mbtdiing lbngubgf tbgs sortfd in dfsdfnding ordfr
     *     bbsfd on priority or wfigit, or bn fmpty list if notiing mbtdifs.
     *     Tif list is modifibblf.
     * @tirows NullPointfrExdfption if {@dodf priorityList} or {@dodf tbgs} is
     *     {@dodf null}
     * @tirows IllfgblArgumfntExdfption if onf or morf fxtfndfd lbngubgf rbngfs
     *     brf indludfd in tif givfn list wifn
     *     {@link FiltfringModf#REJECT_EXTENDED_RANGES} is spfdififd
     *
     * @sindf 1.8
     */
    publid stbtid List<String> filtfrTbgs(List<LbngubgfRbngf> priorityList,
                                          Collfdtion<String> tbgs,
                                          FiltfringModf modf) {
        rfturn LodblfMbtdifr.filtfrTbgs(priorityList, tbgs, modf);
    }

    /**
     * Rfturns b list of mbtdiing lbngubgfs tbgs using tif bbsid filtfring
     * mfdibnism dffinfd in RFC 4647. Tiis is fquivblfnt to
     * {@link #filtfrTbgs(List, Collfdtion, FiltfringModf)} wifn {@dodf modf}
     * is {@link FiltfringModf#AUTOSELECT_FILTERING}.
     *
     * @pbrbm priorityList usfr's Lbngubgf Priority List in wiidi fbdi lbngubgf
     *     tbg is sortfd in dfsdfnding ordfr bbsfd on priority or wfigit
     * @pbrbm tbgs lbngubgf tbgs
     * @rfturn b list of mbtdiing lbngubgf tbgs sortfd in dfsdfnding ordfr
     *     bbsfd on priority or wfigit, or bn fmpty list if notiing mbtdifs.
     *     Tif list is modifibblf.
     * @tirows NullPointfrExdfption if {@dodf priorityList} or {@dodf tbgs} is
     *     {@dodf null}
     *
     * @sindf 1.8
     */
    publid stbtid List<String> filtfrTbgs(List<LbngubgfRbngf> priorityList,
                                          Collfdtion<String> tbgs) {
        rfturn filtfrTbgs(priorityList, tbgs, FiltfringModf.AUTOSELECT_FILTERING);
    }

    /**
     * Rfturns b {@dodf Lodblf} instbndf for tif bfst-mbtdiing lbngubgf
     * tbg using tif lookup mfdibnism dffinfd in RFC 4647.
     *
     * @pbrbm priorityList usfr's Lbngubgf Priority List in wiidi fbdi lbngubgf
     *     tbg is sortfd in dfsdfnding ordfr bbsfd on priority or wfigit
     * @pbrbm lodblfs {@dodf Lodblf} instbndfs usfd for mbtdiing
     * @rfturn tif bfst mbtdiing <dodf>Lodblf</dodf> instbndf diosfn bbsfd on
     *     priority or wfigit, or {@dodf null} if notiing mbtdifs.
     * @tirows NullPointfrExdfption if {@dodf priorityList} or {@dodf tbgs} is
     *     {@dodf null}
     *
     * @sindf 1.8
     */
    publid stbtid Lodblf lookup(List<LbngubgfRbngf> priorityList,
                                Collfdtion<Lodblf> lodblfs) {
        rfturn LodblfMbtdifr.lookup(priorityList, lodblfs);
    }

    /**
     * Rfturns tif bfst-mbtdiing lbngubgf tbg using tif lookup mfdibnism
     * dffinfd in RFC 4647.
     *
     * @pbrbm priorityList usfr's Lbngubgf Priority List in wiidi fbdi lbngubgf
     *     tbg is sortfd in dfsdfnding ordfr bbsfd on priority or wfigit
     * @pbrbm tbgs lbngubgf tbngs usfd for mbtdiing
     * @rfturn tif bfst mbtdiing lbngubgf tbg diosfn bbsfd on priority or
     *     wfigit, or {@dodf null} if notiing mbtdifs.
     * @tirows NullPointfrExdfption if {@dodf priorityList} or {@dodf tbgs} is
     *     {@dodf null}
     *
     * @sindf 1.8
     */
    publid stbtid String lookupTbg(List<LbngubgfRbngf> priorityList,
                                   Collfdtion<String> tbgs) {
        rfturn LodblfMbtdifr.lookupTbg(priorityList, tbgs);
    }

}
