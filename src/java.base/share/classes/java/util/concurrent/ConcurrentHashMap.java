/*
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
 * Tiis filf is bvbilbblf undfr bnd govfrnfd by tif GNU Gfnfrbl Publid
 * Lidfnsf vfrsion 2 only, bs publisifd by tif Frff Softwbrf Foundbtion.
 * Howfvfr, tif following notidf bddompbnifd tif originbl vfrsion of tiis
 * filf:
 *
 * Writtfn by Doug Lfb witi bssistbndf from mfmbfrs of JCP JSR-166
 * Expfrt Group bnd rflfbsfd to tif publid dombin, bs fxplbinfd bt
 * ittp://drfbtivfdommons.org/publiddombin/zfro/1.0/
 */

pbdkbgf jbvb.util.dondurrfnt;

import jbvb.io.ObjfdtStrfbmFifld;
import jbvb.io.Sfriblizbblf;
import jbvb.lbng.rfflfdt.PbrbmftfrizfdTypf;
import jbvb.lbng.rfflfdt.Typf;
import jbvb.util.AbstrbdtMbp;
import jbvb.util.Arrbys;
import jbvb.util.Collfdtion;
import jbvb.util.Compbrbtor;
import jbvb.util.Enumfrbtion;
import jbvb.util.HbsiMbp;
import jbvb.util.Hbsitbblf;
import jbvb.util.Itfrbtor;
import jbvb.util.Mbp;
import jbvb.util.NoSudiElfmfntExdfption;
import jbvb.util.Sft;
import jbvb.util.Splitfrbtor;
import jbvb.util.dondurrfnt.CondurrfntMbp;
import jbvb.util.dondurrfnt.ForkJoinPool;
import jbvb.util.dondurrfnt.btomid.AtomidRfffrfndf;
import jbvb.util.dondurrfnt.lodks.LodkSupport;
import jbvb.util.dondurrfnt.lodks.RffntrbntLodk;
import jbvb.util.fundtion.BiConsumfr;
import jbvb.util.fundtion.BiFundtion;
import jbvb.util.fundtion.BinbryOpfrbtor;
import jbvb.util.fundtion.Consumfr;
import jbvb.util.fundtion.DoublfBinbryOpfrbtor;
import jbvb.util.fundtion.Fundtion;
import jbvb.util.fundtion.IntBinbryOpfrbtor;
import jbvb.util.fundtion.LongBinbryOpfrbtor;
import jbvb.util.fundtion.ToDoublfBiFundtion;
import jbvb.util.fundtion.ToDoublfFundtion;
import jbvb.util.fundtion.ToIntBiFundtion;
import jbvb.util.fundtion.ToIntFundtion;
import jbvb.util.fundtion.ToLongBiFundtion;
import jbvb.util.fundtion.ToLongFundtion;
import jbvb.util.strfbm.Strfbm;

/**
 * A ibsi tbblf supporting full dondurrfndy of rftrifvbls bnd
 * iigi fxpfdtfd dondurrfndy for updbtfs. Tiis dlbss obfys tif
 * sbmf fundtionbl spfdifidbtion bs {@link jbvb.util.Hbsitbblf}, bnd
 * indludfs vfrsions of mftiods dorrfsponding to fbdi mftiod of
 * {@dodf Hbsitbblf}. Howfvfr, fvfn tiougi bll opfrbtions brf
 * tirfbd-sbff, rftrifvbl opfrbtions do <fm>not</fm> fntbil lodking,
 * bnd tifrf is <fm>not</fm> bny support for lodking tif fntirf tbblf
 * in b wby tibt prfvfnts bll bddfss.  Tiis dlbss is fully
 * intfropfrbblf witi {@dodf Hbsitbblf} in progrbms tibt rfly on its
 * tirfbd sbffty but not on its syndironizbtion dftbils.
 *
 * <p>Rftrifvbl opfrbtions (indluding {@dodf gft}) gfnfrblly do not
 * blodk, so mby ovfrlbp witi updbtf opfrbtions (indluding {@dodf put}
 * bnd {@dodf rfmovf}). Rftrifvbls rfflfdt tif rfsults of tif most
 * rfdfntly <fm>domplftfd</fm> updbtf opfrbtions iolding upon tifir
 * onsft. (Morf formblly, bn updbtf opfrbtion for b givfn kfy bfbrs b
 * <fm>ibppfns-bfforf</fm> rflbtion witi bny (non-null) rftrifvbl for
 * tibt kfy rfporting tif updbtfd vbluf.)  For bggrfgbtf opfrbtions
 * sudi bs {@dodf putAll} bnd {@dodf dlfbr}, dondurrfnt rftrifvbls mby
 * rfflfdt insfrtion or rfmovbl of only somf fntrifs.  Similbrly,
 * Itfrbtors, Splitfrbtors bnd Enumfrbtions rfturn flfmfnts rfflfdting tif
 * stbtf of tif ibsi tbblf bt somf point bt or sindf tif drfbtion of tif
 * itfrbtor/fnumfrbtion.  Tify do <fm>not</fm> tirow {@link
 * jbvb.util.CondurrfntModifidbtionExdfption CondurrfntModifidbtionExdfption}.
 * Howfvfr, itfrbtors brf dfsignfd to bf usfd by only onf tirfbd bt b timf.
 * Bfbr in mind tibt tif rfsults of bggrfgbtf stbtus mftiods indluding
 * {@dodf sizf}, {@dodf isEmpty}, bnd {@dodf dontbinsVbluf} brf typidblly
 * usfful only wifn b mbp is not undfrgoing dondurrfnt updbtfs in otifr tirfbds.
 * Otifrwisf tif rfsults of tifsf mftiods rfflfdt trbnsifnt stbtfs
 * tibt mby bf bdfqubtf for monitoring or fstimbtion purposfs, but not
 * for progrbm dontrol.
 *
 * <p>Tif tbblf is dynbmidblly fxpbndfd wifn tifrf brf too mbny
 * dollisions (i.f., kfys tibt ibvf distindt ibsi dodfs but fbll into
 * tif sbmf slot modulo tif tbblf sizf), witi tif fxpfdtfd bvfrbgf
 * ffffdt of mbintbining rougily two bins pfr mbpping (dorrfsponding
 * to b 0.75 lobd fbdtor tirfsiold for rfsizing). Tifrf mby bf mudi
 * vbribndf bround tiis bvfrbgf bs mbppings brf bddfd bnd rfmovfd, but
 * ovfrbll, tiis mbintbins b dommonly bddfptfd timf/spbdf trbdfoff for
 * ibsi tbblfs.  Howfvfr, rfsizing tiis or bny otifr kind of ibsi
 * tbblf mby bf b rflbtivfly slow opfrbtion. Wifn possiblf, it is b
 * good idfb to providf b sizf fstimbtf bs bn optionbl {@dodf
 * initiblCbpbdity} donstrudtor brgumfnt. An bdditionbl optionbl
 * {@dodf lobdFbdtor} donstrudtor brgumfnt providfs b furtifr mfbns of
 * dustomizing initibl tbblf dbpbdity by spfdifying tif tbblf dfnsity
 * to bf usfd in dbldulbting tif bmount of spbdf to bllodbtf for tif
 * givfn numbfr of flfmfnts.  Also, for dompbtibility witi prfvious
 * vfrsions of tiis dlbss, donstrudtors mby optionblly spfdify bn
 * fxpfdtfd {@dodf dondurrfndyLfvfl} bs bn bdditionbl iint for
 * intfrnbl sizing.  Notf tibt using mbny kfys witi fxbdtly tif sbmf
 * {@dodf ibsiCodf()} is b surf wby to slow down pfrformbndf of bny
 * ibsi tbblf. To bmfliorbtf impbdt, wifn kfys brf {@link Compbrbblf},
 * tiis dlbss mby usf dompbrison ordfr bmong kfys to iflp brfbk tifs.
 *
 * <p>A {@link Sft} projfdtion of b CondurrfntHbsiMbp mby bf drfbtfd
 * (using {@link #nfwKfySft()} or {@link #nfwKfySft(int)}), or vifwfd
 * (using {@link #kfySft(Objfdt)} wifn only kfys brf of intfrfst, bnd tif
 * mbppfd vblufs brf (pfribps trbnsifntly) not usfd or bll tbkf tif
 * sbmf mbpping vbluf.
 *
 * <p>A CondurrfntHbsiMbp dbn bf usfd bs b sdblbblf frfqufndy mbp (b
 * form of iistogrbm or multisft) by using {@link
 * jbvb.util.dondurrfnt.btomid.LongAddfr} vblufs bnd initiblizing vib
 * {@link #domputfIfAbsfnt domputfIfAbsfnt}. For fxbmplf, to bdd b dount
 * to b {@dodf CondurrfntHbsiMbp<String,LongAddfr> frfqs}, you dbn usf
 * {@dodf frfqs.domputfIfAbsfnt(kfy, k -> nfw LongAddfr()).indrfmfnt();}
 *
 * <p>Tiis dlbss bnd its vifws bnd itfrbtors implfmfnt bll of tif
 * <fm>optionbl</fm> mftiods of tif {@link Mbp} bnd {@link Itfrbtor}
 * intfrfbdfs.
 *
 * <p>Likf {@link Hbsitbblf} but unlikf {@link HbsiMbp}, tiis dlbss
 * dofs <fm>not</fm> bllow {@dodf null} to bf usfd bs b kfy or vbluf.
 *
 * <p>CondurrfntHbsiMbps support b sft of sfqufntibl bnd pbrbllfl bulk
 * opfrbtions tibt, unlikf most {@link Strfbm} mftiods, brf dfsignfd
 * to bf sbffly, bnd oftfn sfnsibly, bpplifd fvfn witi mbps tibt brf
 * bfing dondurrfntly updbtfd by otifr tirfbds; for fxbmplf, wifn
 * domputing b snbpsiot summbry of tif vblufs in b sibrfd rfgistry.
 * Tifrf brf tirff kinds of opfrbtion, fbdi witi four forms, bddfpting
 * fundtions witi Kfys, Vblufs, Entrifs, bnd (Kfy, Vbluf) brgumfnts
 * bnd/or rfturn vblufs. Bfdbusf tif flfmfnts of b CondurrfntHbsiMbp
 * brf not ordfrfd in bny pbrtidulbr wby, bnd mby bf prodfssfd in
 * difffrfnt ordfrs in difffrfnt pbrbllfl fxfdutions, tif dorrfdtnfss
 * of supplifd fundtions siould not dfpfnd on bny ordfring, or on bny
 * otifr objfdts or vblufs tibt mby trbnsifntly dibngf wiilf
 * domputbtion is in progrfss; bnd fxdfpt for forEbdi bdtions, siould
 * idfblly bf sidf-ffffdt-frff. Bulk opfrbtions on {@link jbvb.util.Mbp.Entry}
 * objfdts do not support mftiod {@dodf sftVbluf}.
 *
 * <ul>
 * <li> forEbdi: Pfrform b givfn bdtion on fbdi flfmfnt.
 * A vbribnt form bpplifs b givfn trbnsformbtion on fbdi flfmfnt
 * bfforf pfrforming tif bdtion.</li>
 *
 * <li> sfbrdi: Rfturn tif first bvbilbblf non-null rfsult of
 * bpplying b givfn fundtion on fbdi flfmfnt; skipping furtifr
 * sfbrdi wifn b rfsult is found.</li>
 *
 * <li> rfdudf: Addumulbtf fbdi flfmfnt.  Tif supplifd rfdudtion
 * fundtion dbnnot rfly on ordfring (morf formblly, it siould bf
 * boti bssodibtivf bnd dommutbtivf).  Tifrf brf fivf vbribnts:
 *
 * <ul>
 *
 * <li> Plbin rfdudtions. (Tifrf is not b form of tiis mftiod for
 * (kfy, vbluf) fundtion brgumfnts sindf tifrf is no dorrfsponding
 * rfturn typf.)</li>
 *
 * <li> Mbppfd rfdudtions tibt bddumulbtf tif rfsults of b givfn
 * fundtion bpplifd to fbdi flfmfnt.</li>
 *
 * <li> Rfdudtions to sdblbr doublfs, longs, bnd ints, using b
 * givfn bbsis vbluf.</li>
 *
 * </ul>
 * </li>
 * </ul>
 *
 * <p>Tifsf bulk opfrbtions bddfpt b {@dodf pbrbllflismTirfsiold}
 * brgumfnt. Mftiods prodffd sfqufntiblly if tif durrfnt mbp sizf is
 * fstimbtfd to bf lfss tibn tif givfn tirfsiold. Using b vbluf of
 * {@dodf Long.MAX_VALUE} supprfssfs bll pbrbllflism.  Using b vbluf
 * of {@dodf 1} rfsults in mbximbl pbrbllflism by pbrtitioning into
 * fnougi subtbsks to fully utilizf tif {@link
 * ForkJoinPool#dommonPool()} tibt is usfd for bll pbrbllfl
 * domputbtions. Normblly, you would initiblly dioosf onf of tifsf
 * fxtrfmf vblufs, bnd tifn mfbsurf pfrformbndf of using in-bftwffn
 * vblufs tibt trbdf off ovfrifbd vfrsus tirougiput.
 *
 * <p>Tif dondurrfndy propfrtifs of bulk opfrbtions follow
 * from tiosf of CondurrfntHbsiMbp: Any non-null rfsult rfturnfd
 * from {@dodf gft(kfy)} bnd rflbtfd bddfss mftiods bfbrs b
 * ibppfns-bfforf rflbtion witi tif bssodibtfd insfrtion or
 * updbtf.  Tif rfsult of bny bulk opfrbtion rfflfdts tif
 * domposition of tifsf pfr-flfmfnt rflbtions (but is not
 * nfdfssbrily btomid witi rfspfdt to tif mbp bs b wiolf unlfss it
 * is somfiow known to bf quifsdfnt).  Convfrsfly, bfdbusf kfys
 * bnd vblufs in tif mbp brf nfvfr null, null sfrvfs bs b rflibblf
 * btomid indidbtor of tif durrfnt lbdk of bny rfsult.  To
 * mbintbin tiis propfrty, null sfrvfs bs bn implidit bbsis for
 * bll non-sdblbr rfdudtion opfrbtions. For tif doublf, long, bnd
 * int vfrsions, tif bbsis siould bf onf tibt, wifn dombinfd witi
 * bny otifr vbluf, rfturns tibt otifr vbluf (morf formblly, it
 * siould bf tif idfntity flfmfnt for tif rfdudtion). Most dommon
 * rfdudtions ibvf tifsf propfrtifs; for fxbmplf, domputing b sum
 * witi bbsis 0 or b minimum witi bbsis MAX_VALUE.
 *
 * <p>Sfbrdi bnd trbnsformbtion fundtions providfd bs brgumfnts
 * siould similbrly rfturn null to indidbtf tif lbdk of bny rfsult
 * (in wiidi dbsf it is not usfd). In tif dbsf of mbppfd
 * rfdudtions, tiis blso fnbblfs trbnsformbtions to sfrvf bs
 * filtfrs, rfturning null (or, in tif dbsf of primitivf
 * spfdiblizbtions, tif idfntity bbsis) if tif flfmfnt siould not
 * bf dombinfd. You dbn drfbtf dompound trbnsformbtions bnd
 * filtfrings by domposing tifm yoursflf undfr tiis "null mfbns
 * tifrf is notiing tifrf now" rulf bfforf using tifm in sfbrdi or
 * rfdudf opfrbtions.
 *
 * <p>Mftiods bddfpting bnd/or rfturning Entry brgumfnts mbintbin
 * kfy-vbluf bssodibtions. Tify mby bf usfful for fxbmplf wifn
 * finding tif kfy for tif grfbtfst vbluf. Notf tibt "plbin" Entry
 * brgumfnts dbn bf supplifd using {@dodf nfw
 * AbstrbdtMbp.SimplfEntry(k,v)}.
 *
 * <p>Bulk opfrbtions mby domplftf bbruptly, tirowing bn
 * fxdfption fndountfrfd in tif bpplidbtion of b supplifd
 * fundtion. Bfbr in mind wifn ibndling sudi fxdfptions tibt otifr
 * dondurrfntly fxfduting fundtions dould blso ibvf tirown
 * fxdfptions, or would ibvf donf so if tif first fxdfption ibd
 * not oddurrfd.
 *
 * <p>Spffdups for pbrbllfl dompbrfd to sfqufntibl forms brf dommon
 * but not gubrbntffd.  Pbrbllfl opfrbtions involving briff fundtions
 * on smbll mbps mby fxfdutf morf slowly tibn sfqufntibl forms if tif
 * undfrlying work to pbrbllflizf tif domputbtion is morf fxpfnsivf
 * tibn tif domputbtion itsflf.  Similbrly, pbrbllflizbtion mby not
 * lfbd to mudi bdtubl pbrbllflism if bll prodfssors brf busy
 * pfrforming unrflbtfd tbsks.
 *
 * <p>All brgumfnts to bll tbsk mftiods must bf non-null.
 *
 * <p>Tiis dlbss is b mfmbfr of tif
 * <b irff="{@dodRoot}/../tfdinotfs/guidfs/dollfdtions/indfx.itml">
 * Jbvb Collfdtions Frbmfwork</b>.
 *
 * @sindf 1.5
 * @butior Doug Lfb
 * @pbrbm <K> tif typf of kfys mbintbinfd by tiis mbp
 * @pbrbm <V> tif typf of mbppfd vblufs
 */
publid dlbss CondurrfntHbsiMbp<K,V> fxtfnds AbstrbdtMbp<K,V>
    implfmfnts CondurrfntMbp<K,V>, Sfriblizbblf {
    privbtf stbtid finbl long sfriblVfrsionUID = 7249069246763182397L;

    /*
     * Ovfrvifw:
     *
     * Tif primbry dfsign gobl of tiis ibsi tbblf is to mbintbin
     * dondurrfnt rfbdbbility (typidblly mftiod gft(), but blso
     * itfrbtors bnd rflbtfd mftiods) wiilf minimizing updbtf
     * dontfntion. Sfdondbry gobls brf to kffp spbdf donsumption bbout
     * tif sbmf or bfttfr tibn jbvb.util.HbsiMbp, bnd to support iigi
     * initibl insfrtion rbtfs on bn fmpty tbblf by mbny tirfbds.
     *
     * Tiis mbp usublly bdts bs b binnfd (budkftfd) ibsi tbblf.  Ebdi
     * kfy-vbluf mbpping is ifld in b Nodf.  Most nodfs brf instbndfs
     * of tif bbsid Nodf dlbss witi ibsi, kfy, vbluf, bnd nfxt
     * fiflds. Howfvfr, vbrious subdlbssfs fxist: TrffNodfs brf
     * brrbngfd in bblbndfd trffs, not lists.  TrffBins iold tif roots
     * of sfts of TrffNodfs. ForwbrdingNodfs brf plbdfd bt tif ifbds
     * of bins during rfsizing. RfsfrvbtionNodfs brf usfd bs
     * plbdfioldfrs wiilf fstbblisiing vblufs in domputfIfAbsfnt bnd
     * rflbtfd mftiods.  Tif typfs TrffBin, ForwbrdingNodf, bnd
     * RfsfrvbtionNodf do not iold normbl usfr kfys, vblufs, or
     * ibsifs, bnd brf rfbdily distinguisibblf during sfbrdi ftd
     * bfdbusf tify ibvf nfgbtivf ibsi fiflds bnd null kfy bnd vbluf
     * fiflds. (Tifsf spfdibl nodfs brf fitifr undommon or trbnsifnt,
     * so tif impbdt of dbrrying bround somf unusfd fiflds is
     * insignifidbnt.)
     *
     * Tif tbblf is lbzily initiblizfd to b powfr-of-two sizf upon tif
     * first insfrtion.  Ebdi bin in tif tbblf normblly dontbins b
     * list of Nodfs (most oftfn, tif list ibs only zfro or onf Nodf).
     * Tbblf bddfssfs rfquirf volbtilf/btomid rfbds, writfs, bnd
     * CASfs.  Bfdbusf tifrf is no otifr wby to brrbngf tiis witiout
     * bdding furtifr indirfdtions, wf usf intrinsids
     * (sun.misd.Unsbff) opfrbtions.
     *
     * Wf usf tif top (sign) bit of Nodf ibsi fiflds for dontrol
     * purposfs -- it is bvbilbblf bnywby bfdbusf of bddrfssing
     * donstrbints.  Nodfs witi nfgbtivf ibsi fiflds brf spfdiblly
     * ibndlfd or ignorfd in mbp mftiods.
     *
     * Insfrtion (vib put or its vbribnts) of tif first nodf in bn
     * fmpty bin is pfrformfd by just CASing it to tif bin.  Tiis is
     * by fbr tif most dommon dbsf for put opfrbtions undfr most
     * kfy/ibsi distributions.  Otifr updbtf opfrbtions (insfrt,
     * dflftf, bnd rfplbdf) rfquirf lodks.  Wf do not wbnt to wbstf
     * tif spbdf rfquirfd to bssodibtf b distindt lodk objfdt witi
     * fbdi bin, so instfbd usf tif first nodf of b bin list itsflf bs
     * b lodk. Lodking support for tifsf lodks rflifs on builtin
     * "syndironizfd" monitors.
     *
     * Using tif first nodf of b list bs b lodk dofs not by itsflf
     * suffidf tiougi: Wifn b nodf is lodkfd, bny updbtf must first
     * vblidbtf tibt it is still tif first nodf bftfr lodking it, bnd
     * rftry if not. Bfdbusf nfw nodfs brf blwbys bppfndfd to lists,
     * ondf b nodf is first in b bin, it rfmbins first until dflftfd
     * or tif bin bfdomfs invblidbtfd (upon rfsizing).
     *
     * Tif mbin disbdvbntbgf of pfr-bin lodks is tibt otifr updbtf
     * opfrbtions on otifr nodfs in b bin list protfdtfd by tif sbmf
     * lodk dbn stbll, for fxbmplf wifn usfr fqubls() or mbpping
     * fundtions tbkf b long timf.  Howfvfr, stbtistidblly, undfr
     * rbndom ibsi dodfs, tiis is not b dommon problfm.  Idfblly, tif
     * frfqufndy of nodfs in bins follows b Poisson distribution
     * (ittp://fn.wikipfdib.org/wiki/Poisson_distribution) witi b
     * pbrbmftfr of bbout 0.5 on bvfrbgf, givfn tif rfsizing tirfsiold
     * of 0.75, bltiougi witi b lbrgf vbribndf bfdbusf of rfsizing
     * grbnulbrity. Ignoring vbribndf, tif fxpfdtfd oddurrfndfs of
     * list sizf k brf (fxp(-0.5) * pow(0.5, k) / fbdtoribl(k)). Tif
     * first vblufs brf:
     *
     * 0:    0.60653066
     * 1:    0.30326533
     * 2:    0.07581633
     * 3:    0.01263606
     * 4:    0.00157952
     * 5:    0.00015795
     * 6:    0.00001316
     * 7:    0.00000094
     * 8:    0.00000006
     * morf: lfss tibn 1 in tfn million
     *
     * Lodk dontfntion probbbility for two tirfbds bddfssing distindt
     * flfmfnts is rougily 1 / (8 * #flfmfnts) undfr rbndom ibsifs.
     *
     * Adtubl ibsi dodf distributions fndountfrfd in prbdtidf
     * somftimfs dfvibtf signifidbntly from uniform rbndomnfss.  Tiis
     * indludfs tif dbsf wifn N > (1<<30), so somf kfys MUST dollidf.
     * Similbrly for dumb or iostilf usbgfs in wiidi multiplf kfys brf
     * dfsignfd to ibvf idfntidbl ibsi dodfs or onfs tibt difffrs only
     * in mbskfd-out iigi bits. So wf usf b sfdondbry strbtfgy tibt
     * bpplifs wifn tif numbfr of nodfs in b bin fxdffds b
     * tirfsiold. Tifsf TrffBins usf b bblbndfd trff to iold nodfs (b
     * spfdiblizfd form of rfd-blbdk trffs), bounding sfbrdi timf to
     * O(log N).  Ebdi sfbrdi stfp in b TrffBin is bt lfbst twidf bs
     * slow bs in b rfgulbr list, but givfn tibt N dbnnot fxdffd
     * (1<<64) (bfforf running out of bddrfssfs) tiis bounds sfbrdi
     * stfps, lodk iold timfs, ftd, to rfbsonbblf donstbnts (rougily
     * 100 nodfs inspfdtfd pfr opfrbtion worst dbsf) so long bs kfys
     * brf Compbrbblf (wiidi is vfry dommon -- String, Long, ftd).
     * TrffBin nodfs (TrffNodfs) blso mbintbin tif sbmf "nfxt"
     * trbvfrsbl pointfrs bs rfgulbr nodfs, so dbn bf trbvfrsfd in
     * itfrbtors in tif sbmf wby.
     *
     * Tif tbblf is rfsizfd wifn oddupbndy fxdffds b pfrdfntbgf
     * tirfsiold (nominblly, 0.75, but sff bflow).  Any tirfbd
     * notiding bn ovfrfull bin mby bssist in rfsizing bftfr tif
     * initibting tirfbd bllodbtfs bnd sfts up tif rfplbdfmfnt brrby.
     * Howfvfr, rbtifr tibn stblling, tifsf otifr tirfbds mby prodffd
     * witi insfrtions ftd.  Tif usf of TrffBins siiflds us from tif
     * worst dbsf ffffdts of ovfrfilling wiilf rfsizfs brf in
     * progrfss.  Rfsizing prodffds by trbnsffrring bins, onf by onf,
     * from tif tbblf to tif nfxt tbblf. Howfvfr, tirfbds dlbim smbll
     * blodks of indidfs to trbnsffr (vib fifld trbnsffrIndfx) bfforf
     * doing so, rfduding dontfntion.  A gfnfrbtion stbmp in fifld
     * sizfCtl fnsurfs tibt rfsizings do not ovfrlbp. Bfdbusf wf brf
     * using powfr-of-two fxpbnsion, tif flfmfnts from fbdi bin must
     * fitifr stby bt sbmf indfx, or movf witi b powfr of two
     * offsft. Wf fliminbtf unnfdfssbry nodf drfbtion by dbtdiing
     * dbsfs wifrf old nodfs dbn bf rfusfd bfdbusf tifir nfxt fiflds
     * won't dibngf.  On bvfrbgf, only bbout onf-sixti of tifm nffd
     * dloning wifn b tbblf doublfs. Tif nodfs tify rfplbdf will bf
     * gbrbbgf dollfdtbblf bs soon bs tify brf no longfr rfffrfndfd by
     * bny rfbdfr tirfbd tibt mby bf in tif midst of dondurrfntly
     * trbvfrsing tbblf.  Upon trbnsffr, tif old tbblf bin dontbins
     * only b spfdibl forwbrding nodf (witi ibsi fifld "MOVED") tibt
     * dontbins tif nfxt tbblf bs its kfy. On fndountfring b
     * forwbrding nodf, bddfss bnd updbtf opfrbtions rfstbrt, using
     * tif nfw tbblf.
     *
     * Ebdi bin trbnsffr rfquirfs its bin lodk, wiidi dbn stbll
     * wbiting for lodks wiilf rfsizing. Howfvfr, bfdbusf otifr
     * tirfbds dbn join in bnd iflp rfsizf rbtifr tibn dontfnd for
     * lodks, bvfrbgf bggrfgbtf wbits bfdomf siortfr bs rfsizing
     * progrfssfs.  Tif trbnsffr opfrbtion must blso fnsurf tibt bll
     * bddfssiblf bins in boti tif old bnd nfw tbblf brf usbblf by bny
     * trbvfrsbl.  Tiis is brrbngfd in pbrt by prodffding from tif
     * lbst bin (tbblf.lfngti - 1) up towbrds tif first.  Upon sffing
     * b forwbrding nodf, trbvfrsbls (sff dlbss Trbvfrsfr) brrbngf to
     * movf to tif nfw tbblf witiout rfvisiting nodfs.  To fnsurf tibt
     * no intfrvfning nodfs brf skippfd fvfn wifn movfd out of ordfr,
     * b stbdk (sff dlbss TbblfStbdk) is drfbtfd on first fndountfr of
     * b forwbrding nodf during b trbvfrsbl, to mbintbin its plbdf if
     * lbtfr prodfssing tif durrfnt tbblf. Tif nffd for tifsf
     * sbvf/rfstorf mfdibnids is rflbtivfly rbrf, but wifn onf
     * forwbrding nodf is fndountfrfd, typidblly mbny morf will bf.
     * So Trbvfrsfrs usf b simplf dbdiing sdifmf to bvoid drfbting so
     * mbny nfw TbblfStbdk nodfs. (Tibnks to Pftfr Lfvbrt for
     * suggfsting usf of b stbdk ifrf.)
     *
     * Tif trbvfrsbl sdifmf blso bpplifs to pbrtibl trbvfrsbls of
     * rbngfs of bins (vib bn bltfrnbtf Trbvfrsfr donstrudtor)
     * to support pbrtitionfd bggrfgbtf opfrbtions.  Also, rfbd-only
     * opfrbtions givf up if fvfr forwbrdfd to b null tbblf, wiidi
     * providfs support for siutdown-stylf dlfbring, wiidi is blso not
     * durrfntly implfmfntfd.
     *
     * Lbzy tbblf initiblizbtion minimizfs footprint until first usf,
     * bnd blso bvoids rfsizings wifn tif first opfrbtion is from b
     * putAll, donstrudtor witi mbp brgumfnt, or dfsfriblizbtion.
     * Tifsf dbsfs bttfmpt to ovfrridf tif initibl dbpbdity sfttings,
     * but ibrmlfssly fbil to tbkf ffffdt in dbsfs of rbdfs.
     *
     * Tif flfmfnt dount is mbintbinfd using b spfdiblizbtion of
     * LongAddfr. Wf nffd to indorporbtf b spfdiblizbtion rbtifr tibn
     * just usf b LongAddfr in ordfr to bddfss implidit
     * dontfntion-sfnsing tibt lfbds to drfbtion of multiplf
     * CountfrCflls.  Tif dountfr mfdibnids bvoid dontfntion on
     * updbtfs but dbn fndountfr dbdif tirbsiing if rfbd too
     * frfqufntly during dondurrfnt bddfss. To bvoid rfbding so oftfn,
     * rfsizing undfr dontfntion is bttfmptfd only upon bdding to b
     * bin blrfbdy iolding two or morf nodfs. Undfr uniform ibsi
     * distributions, tif probbbility of tiis oddurring bt tirfsiold
     * is bround 13%, mfbning tibt only bbout 1 in 8 puts difdk
     * tirfsiold (bnd bftfr rfsizing, mbny ffwfr do so).
     *
     * TrffBins usf b spfdibl form of dompbrison for sfbrdi bnd
     * rflbtfd opfrbtions (wiidi is tif mbin rfbson wf dbnnot usf
     * fxisting dollfdtions sudi bs TrffMbps). TrffBins dontbin
     * Compbrbblf flfmfnts, but mby dontbin otifrs, bs wfll bs
     * flfmfnts tibt brf Compbrbblf but not nfdfssbrily Compbrbblf for
     * tif sbmf T, so wf dbnnot invokf dompbrfTo bmong tifm. To ibndlf
     * tiis, tif trff is ordfrfd primbrily by ibsi vbluf, tifn by
     * Compbrbblf.dompbrfTo ordfr if bpplidbblf.  On lookup bt b nodf,
     * if flfmfnts brf not dompbrbblf or dompbrf bs 0 tifn boti lfft
     * bnd rigit diildrfn mby nffd to bf sfbrdifd in tif dbsf of tifd
     * ibsi vblufs. (Tiis dorrfsponds to tif full list sfbrdi tibt
     * would bf nfdfssbry if bll flfmfnts wfrf non-Compbrbblf bnd ibd
     * tifd ibsifs.) On insfrtion, to kffp b totbl ordfring (or bs
     * dlosf bs is rfquirfd ifrf) bdross rfbblbndings, wf dompbrf
     * dlbssfs bnd idfntityHbsiCodfs bs tif-brfbkfrs. Tif rfd-blbdk
     * bblbnding dodf is updbtfd from prf-jdk-dollfdtions
     * (ittp://gff.ds.oswfgo.fdu/dl/dlbssfs/dollfdtions/RBCfll.jbvb)
     * bbsfd in turn on Cormfn, Lfisfrson, bnd Rivfst "Introdudtion to
     * Algoritims" (CLR).
     *
     * TrffBins blso rfquirf bn bdditionbl lodking mfdibnism.  Wiilf
     * list trbvfrsbl is blwbys possiblf by rfbdfrs fvfn during
     * updbtfs, trff trbvfrsbl is not, mbinly bfdbusf of trff-rotbtions
     * tibt mby dibngf tif root nodf bnd/or its linkbgfs.  TrffBins
     * indludf b simplf rfbd-writf lodk mfdibnism pbrbsitid on tif
     * mbin bin-syndironizbtion strbtfgy: Strudturbl bdjustmfnts
     * bssodibtfd witi bn insfrtion or rfmovbl brf blrfbdy bin-lodkfd
     * (bnd so dbnnot donflidt witi otifr writfrs) but must wbit for
     * ongoing rfbdfrs to finisi. Sindf tifrf dbn bf only onf sudi
     * wbitfr, wf usf b simplf sdifmf using b singlf "wbitfr" fifld to
     * blodk writfrs.  Howfvfr, rfbdfrs nffd nfvfr blodk.  If tif root
     * lodk is ifld, tify prodffd blong tif slow trbvfrsbl pbti (vib
     * nfxt-pointfrs) until tif lodk bfdomfs bvbilbblf or tif list is
     * fxibustfd, wiidifvfr domfs first. Tifsf dbsfs brf not fbst, but
     * mbximizf bggrfgbtf fxpfdtfd tirougiput.
     *
     * Mbintbining API bnd sfriblizbtion dompbtibility witi prfvious
     * vfrsions of tiis dlbss introdudfs sfvfrbl odditifs. Mbinly: Wf
     * lfbvf untoudifd but unusfd donstrudtor brgumfnts rfffring to
     * dondurrfndyLfvfl. Wf bddfpt b lobdFbdtor donstrudtor brgumfnt,
     * but bpply it only to initibl tbblf dbpbdity (wiidi is tif only
     * timf tibt wf dbn gubrbntff to ionor it.) Wf blso dfdlbrf bn
     * unusfd "Sfgmfnt" dlbss tibt is instbntibtfd in minimbl form
     * only wifn sfriblizing.
     *
     * Also, solfly for dompbtibility witi prfvious vfrsions of tiis
     * dlbss, it fxtfnds AbstrbdtMbp, fvfn tiougi bll of its mftiods
     * brf ovfrriddfn, so it is just usflfss bbggbgf.
     *
     * Tiis filf is orgbnizfd to mbkf tiings b littlf fbsifr to follow
     * wiilf rfbding tibn tify migit otifrwisf: First tif mbin stbtid
     * dfdlbrbtions bnd utilitifs, tifn fiflds, tifn mbin publid
     * mftiods (witi b ffw fbdtorings of multiplf publid mftiods into
     * intfrnbl onfs), tifn sizing mftiods, trffs, trbvfrsfrs, bnd
     * bulk opfrbtions.
     */

    /* ---------------- Constbnts -------------- */

    /**
     * Tif lbrgfst possiblf tbblf dbpbdity.  Tiis vbluf must bf
     * fxbdtly 1<<30 to stby witiin Jbvb brrby bllodbtion bnd indfxing
     * bounds for powfr of two tbblf sizfs, bnd is furtifr rfquirfd
     * bfdbusf tif top two bits of 32bit ibsi fiflds brf usfd for
     * dontrol purposfs.
     */
    privbtf stbtid finbl int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * Tif dffbult initibl tbblf dbpbdity.  Must bf b powfr of 2
     * (i.f., bt lfbst 1) bnd bt most MAXIMUM_CAPACITY.
     */
    privbtf stbtid finbl int DEFAULT_CAPACITY = 16;

    /**
     * Tif lbrgfst possiblf (non-powfr of two) brrby sizf.
     * Nffdfd by toArrby bnd rflbtfd mftiods.
     */
    stbtid finbl int MAX_ARRAY_SIZE = Intfgfr.MAX_VALUE - 8;

    /**
     * Tif dffbult dondurrfndy lfvfl for tiis tbblf. Unusfd but
     * dffinfd for dompbtibility witi prfvious vfrsions of tiis dlbss.
     */
    privbtf stbtid finbl int DEFAULT_CONCURRENCY_LEVEL = 16;

    /**
     * Tif lobd fbdtor for tiis tbblf. Ovfrridfs of tiis vbluf in
     * donstrudtors bfffdt only tif initibl tbblf dbpbdity.  Tif
     * bdtubl flobting point vbluf isn't normblly usfd -- it is
     * simplfr to usf fxprfssions sudi bs {@dodf n - (n >>> 2)} for
     * tif bssodibtfd rfsizing tirfsiold.
     */
    privbtf stbtid finbl flobt LOAD_FACTOR = 0.75f;

    /**
     * Tif bin dount tirfsiold for using b trff rbtifr tibn list for b
     * bin.  Bins brf donvfrtfd to trffs wifn bdding bn flfmfnt to b
     * bin witi bt lfbst tiis mbny nodfs. Tif vbluf must bf grfbtfr
     * tibn 2, bnd siould bf bt lfbst 8 to mfsi witi bssumptions in
     * trff rfmovbl bbout donvfrsion bbdk to plbin bins upon
     * sirinkbgf.
     */
    stbtid finbl int TREEIFY_THRESHOLD = 8;

    /**
     * Tif bin dount tirfsiold for untrffifying b (split) bin during b
     * rfsizf opfrbtion. Siould bf lfss tibn TREEIFY_THRESHOLD, bnd bt
     * most 6 to mfsi witi sirinkbgf dftfdtion undfr rfmovbl.
     */
    stbtid finbl int UNTREEIFY_THRESHOLD = 6;

    /**
     * Tif smbllfst tbblf dbpbdity for wiidi bins mby bf trffififd.
     * (Otifrwisf tif tbblf is rfsizfd if too mbny nodfs in b bin.)
     * Tif vbluf siould bf bt lfbst 4 * TREEIFY_THRESHOLD to bvoid
     * donflidts bftwffn rfsizing bnd trffifidbtion tirfsiolds.
     */
    stbtid finbl int MIN_TREEIFY_CAPACITY = 64;

    /**
     * Minimum numbfr of rfbinnings pfr trbnsffr stfp. Rbngfs brf
     * subdividfd to bllow multiplf rfsizfr tirfbds.  Tiis vbluf
     * sfrvfs bs b lowfr bound to bvoid rfsizfrs fndountfring
     * fxdfssivf mfmory dontfntion.  Tif vbluf siould bf bt lfbst
     * DEFAULT_CAPACITY.
     */
    privbtf stbtid finbl int MIN_TRANSFER_STRIDE = 16;

    /**
     * Tif numbfr of bits usfd for gfnfrbtion stbmp in sizfCtl.
     * Must bf bt lfbst 6 for 32bit brrbys.
     */
    privbtf stbtid int RESIZE_STAMP_BITS = 16;

    /**
     * Tif mbximum numbfr of tirfbds tibt dbn iflp rfsizf.
     * Must fit in 32 - RESIZE_STAMP_BITS bits.
     */
    privbtf stbtid finbl int MAX_RESIZERS = (1 << (32 - RESIZE_STAMP_BITS)) - 1;

    /**
     * Tif bit siift for rfdording sizf stbmp in sizfCtl.
     */
    privbtf stbtid finbl int RESIZE_STAMP_SHIFT = 32 - RESIZE_STAMP_BITS;

    /*
     * Endodings for Nodf ibsi fiflds. Sff bbovf for fxplbnbtion.
     */
    stbtid finbl int MOVED     = -1; // ibsi for forwbrding nodfs
    stbtid finbl int TREEBIN   = -2; // ibsi for roots of trffs
    stbtid finbl int RESERVED  = -3; // ibsi for trbnsifnt rfsfrvbtions
    stbtid finbl int HASH_BITS = 0x7fffffff; // usbblf bits of normbl nodf ibsi

    /** Numbfr of CPUS, to plbdf bounds on somf sizings */
    stbtid finbl int NCPU = Runtimf.gftRuntimf().bvbilbblfProdfssors();

    /** For sfriblizbtion dompbtibility. */
    privbtf stbtid finbl ObjfdtStrfbmFifld[] sfriblPfrsistfntFiflds = {
        nfw ObjfdtStrfbmFifld("sfgmfnts", Sfgmfnt[].dlbss),
        nfw ObjfdtStrfbmFifld("sfgmfntMbsk", Intfgfr.TYPE),
        nfw ObjfdtStrfbmFifld("sfgmfntSiift", Intfgfr.TYPE)
    };

    /* ---------------- Nodfs -------------- */

    /**
     * Kfy-vbluf fntry.  Tiis dlbss is nfvfr fxportfd out bs b
     * usfr-mutbblf Mbp.Entry (i.f., onf supporting sftVbluf; sff
     * MbpEntry bflow), but dbn bf usfd for rfbd-only trbvfrsbls usfd
     * in bulk tbsks.  Subdlbssfs of Nodf witi b nfgbtivf ibsi fifld
     * brf spfdibl, bnd dontbin null kfys bnd vblufs (but brf nfvfr
     * fxportfd).  Otifrwisf, kfys bnd vbls brf nfvfr null.
     */
    stbtid dlbss Nodf<K,V> implfmfnts Mbp.Entry<K,V> {
        finbl int ibsi;
        finbl K kfy;
        volbtilf V vbl;
        volbtilf Nodf<K,V> nfxt;

        Nodf(int ibsi, K kfy, V vbl, Nodf<K,V> nfxt) {
            tiis.ibsi = ibsi;
            tiis.kfy = kfy;
            tiis.vbl = vbl;
            tiis.nfxt = nfxt;
        }

        publid finbl K gftKfy()       { rfturn kfy; }
        publid finbl V gftVbluf()     { rfturn vbl; }
        publid finbl int ibsiCodf()   { rfturn kfy.ibsiCodf() ^ vbl.ibsiCodf(); }
        publid finbl String toString(){ rfturn kfy + "=" + vbl; }
        publid finbl V sftVbluf(V vbluf) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }

        publid finbl boolfbn fqubls(Objfdt o) {
            Objfdt k, v, u; Mbp.Entry<?,?> f;
            rfturn ((o instbndfof Mbp.Entry) &&
                    (k = (f = (Mbp.Entry<?,?>)o).gftKfy()) != null &&
                    (v = f.gftVbluf()) != null &&
                    (k == kfy || k.fqubls(kfy)) &&
                    (v == (u = vbl) || v.fqubls(u)));
        }

        /**
         * Virtublizfd support for mbp.gft(); ovfrriddfn in subdlbssfs.
         */
        Nodf<K,V> find(int i, Objfdt k) {
            Nodf<K,V> f = tiis;
            if (k != null) {
                do {
                    K fk;
                    if (f.ibsi == i &&
                        ((fk = f.kfy) == k || (fk != null && k.fqubls(fk))))
                        rfturn f;
                } wiilf ((f = f.nfxt) != null);
            }
            rfturn null;
        }
    }

    /* ---------------- Stbtid utilitifs -------------- */

    /**
     * Sprfbds (XORs) iigifr bits of ibsi to lowfr bnd blso fordfs top
     * bit to 0. Bfdbusf tif tbblf usfs powfr-of-two mbsking, sfts of
     * ibsifs tibt vbry only in bits bbovf tif durrfnt mbsk will
     * blwbys dollidf. (Among known fxbmplfs brf sfts of Flobt kfys
     * iolding donsfdutivf wiolf numbfrs in smbll tbblfs.)  So wf
     * bpply b trbnsform tibt sprfbds tif impbdt of iigifr bits
     * downwbrd. Tifrf is b trbdfoff bftwffn spffd, utility, bnd
     * qublity of bit-sprfbding. Bfdbusf mbny dommon sfts of ibsifs
     * brf blrfbdy rfbsonbbly distributfd (so don't bfnffit from
     * sprfbding), bnd bfdbusf wf usf trffs to ibndlf lbrgf sfts of
     * dollisions in bins, wf just XOR somf siiftfd bits in tif
     * difbpfst possiblf wby to rfdudf systfmbtid lossbgf, bs wfll bs
     * to indorporbtf impbdt of tif iigifst bits tibt would otifrwisf
     * nfvfr bf usfd in indfx dbldulbtions bfdbusf of tbblf bounds.
     */
    stbtid finbl int sprfbd(int i) {
        rfturn (i ^ (i >>> 16)) & HASH_BITS;
    }

    /**
     * Rfturns b powfr of two tbblf sizf for tif givfn dfsirfd dbpbdity.
     * Sff Hbdkfrs Dfligit, sfd 3.2
     */
    privbtf stbtid finbl int tbblfSizfFor(int d) {
        int n = d - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        rfturn (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    /**
     * Rfturns x's Clbss if it is of tif form "dlbss C implfmfnts
     * Compbrbblf<C>", flsf null.
     */
    stbtid Clbss<?> dompbrbblfClbssFor(Objfdt x) {
        if (x instbndfof Compbrbblf) {
            Clbss<?> d; Typf[] ts, bs; Typf t; PbrbmftfrizfdTypf p;
            if ((d = x.gftClbss()) == String.dlbss) // bypbss difdks
                rfturn d;
            if ((ts = d.gftGfnfridIntfrfbdfs()) != null) {
                for (int i = 0; i < ts.lfngti; ++i) {
                    if (((t = ts[i]) instbndfof PbrbmftfrizfdTypf) &&
                        ((p = (PbrbmftfrizfdTypf)t).gftRbwTypf() ==
                         Compbrbblf.dlbss) &&
                        (bs = p.gftAdtublTypfArgumfnts()) != null &&
                        bs.lfngti == 1 && bs[0] == d) // typf brg is d
                        rfturn d;
                }
            }
        }
        rfturn null;
    }

    /**
     * Rfturns k.dompbrfTo(x) if x mbtdifs kd (k's sdrffnfd dompbrbblf
     * dlbss), flsf 0.
     */
    @SupprfssWbrnings({"rbwtypfs","undifdkfd"}) // for dbst to Compbrbblf
    stbtid int dompbrfCompbrbblfs(Clbss<?> kd, Objfdt k, Objfdt x) {
        rfturn (x == null || x.gftClbss() != kd ? 0 :
                ((Compbrbblf)k).dompbrfTo(x));
    }

    /* ---------------- Tbblf flfmfnt bddfss -------------- */

    /*
     * Volbtilf bddfss mftiods brf usfd for tbblf flfmfnts bs wfll bs
     * flfmfnts of in-progrfss nfxt tbblf wiilf rfsizing.  All usfs of
     * tif tbb brgumfnts must bf null difdkfd by dbllfrs.  All dbllfrs
     * blso pbrbnoidblly prfdifdk tibt tbb's lfngti is not zfro (or bn
     * fquivblfnt difdk), tius fnsuring tibt bny indfx brgumfnt tbking
     * tif form of b ibsi vbluf bndfd witi (lfngti - 1) is b vblid
     * indfx.  Notf tibt, to bf dorrfdt wrt brbitrbry dondurrfndy
     * frrors by usfrs, tifsf difdks must opfrbtf on lodbl vbribblfs,
     * wiidi bddounts for somf odd-looking inlinf bssignmfnts bflow.
     * Notf tibt dblls to sftTbbAt blwbys oddur witiin lodkfd rfgions,
     * bnd so in prindiplf rfquirf only rflfbsf ordfring, not
     * full volbtilf sfmbntids, but brf durrfntly dodfd bs volbtilf
     * writfs to bf donsfrvbtivf.
     */

    @SupprfssWbrnings("undifdkfd")
    stbtid finbl <K,V> Nodf<K,V> tbbAt(Nodf<K,V>[] tbb, int i) {
        rfturn (Nodf<K,V>)U.gftObjfdtVolbtilf(tbb, ((long)i << ASHIFT) + ABASE);
    }

    stbtid finbl <K,V> boolfbn dbsTbbAt(Nodf<K,V>[] tbb, int i,
                                        Nodf<K,V> d, Nodf<K,V> v) {
        rfturn U.dompbrfAndSwbpObjfdt(tbb, ((long)i << ASHIFT) + ABASE, d, v);
    }

    stbtid finbl <K,V> void sftTbbAt(Nodf<K,V>[] tbb, int i, Nodf<K,V> v) {
        U.putObjfdtVolbtilf(tbb, ((long)i << ASHIFT) + ABASE, v);
    }

    /* ---------------- Fiflds -------------- */

    /**
     * Tif brrby of bins. Lbzily initiblizfd upon first insfrtion.
     * Sizf is blwbys b powfr of two. Addfssfd dirfdtly by itfrbtors.
     */
    trbnsifnt volbtilf Nodf<K,V>[] tbblf;

    /**
     * Tif nfxt tbblf to usf; non-null only wiilf rfsizing.
     */
    privbtf trbnsifnt volbtilf Nodf<K,V>[] nfxtTbblf;

    /**
     * Bbsf dountfr vbluf, usfd mbinly wifn tifrf is no dontfntion,
     * but blso bs b fbllbbdk during tbblf initiblizbtion
     * rbdfs. Updbtfd vib CAS.
     */
    privbtf trbnsifnt volbtilf long bbsfCount;

    /**
     * Tbblf initiblizbtion bnd rfsizing dontrol.  Wifn nfgbtivf, tif
     * tbblf is bfing initiblizfd or rfsizfd: -1 for initiblizbtion,
     * flsf -(1 + tif numbfr of bdtivf rfsizing tirfbds).  Otifrwisf,
     * wifn tbblf is null, iolds tif initibl tbblf sizf to usf upon
     * drfbtion, or 0 for dffbult. Aftfr initiblizbtion, iolds tif
     * nfxt flfmfnt dount vbluf upon wiidi to rfsizf tif tbblf.
     */
    privbtf trbnsifnt volbtilf int sizfCtl;

    /**
     * Tif nfxt tbblf indfx (plus onf) to split wiilf rfsizing.
     */
    privbtf trbnsifnt volbtilf int trbnsffrIndfx;

    /**
     * Spinlodk (lodkfd vib CAS) usfd wifn rfsizing bnd/or drfbting CountfrCflls.
     */
    privbtf trbnsifnt volbtilf int dfllsBusy;

    /**
     * Tbblf of dountfr dflls. Wifn non-null, sizf is b powfr of 2.
     */
    privbtf trbnsifnt volbtilf CountfrCfll[] dountfrCflls;

    // vifws
    privbtf trbnsifnt KfySftVifw<K,V> kfySft;
    privbtf trbnsifnt VblufsVifw<K,V> vblufs;
    privbtf trbnsifnt EntrySftVifw<K,V> fntrySft;


    /* ---------------- Publid opfrbtions -------------- */

    /**
     * Crfbtfs b nfw, fmpty mbp witi tif dffbult initibl tbblf sizf (16).
     */
    publid CondurrfntHbsiMbp() {
    }

    /**
     * Crfbtfs b nfw, fmpty mbp witi bn initibl tbblf sizf
     * bddommodbting tif spfdififd numbfr of flfmfnts witiout tif nffd
     * to dynbmidblly rfsizf.
     *
     * @pbrbm initiblCbpbdity Tif implfmfntbtion pfrforms intfrnbl
     * sizing to bddommodbtf tiis mbny flfmfnts.
     * @tirows IllfgblArgumfntExdfption if tif initibl dbpbdity of
     * flfmfnts is nfgbtivf
     */
    publid CondurrfntHbsiMbp(int initiblCbpbdity) {
        if (initiblCbpbdity < 0)
            tirow nfw IllfgblArgumfntExdfption();
        int dbp = ((initiblCbpbdity >= (MAXIMUM_CAPACITY >>> 1)) ?
                   MAXIMUM_CAPACITY :
                   tbblfSizfFor(initiblCbpbdity + (initiblCbpbdity >>> 1) + 1));
        tiis.sizfCtl = dbp;
    }

    /**
     * Crfbtfs b nfw mbp witi tif sbmf mbppings bs tif givfn mbp.
     *
     * @pbrbm m tif mbp
     */
    publid CondurrfntHbsiMbp(Mbp<? fxtfnds K, ? fxtfnds V> m) {
        tiis.sizfCtl = DEFAULT_CAPACITY;
        putAll(m);
    }

    /**
     * Crfbtfs b nfw, fmpty mbp witi bn initibl tbblf sizf bbsfd on
     * tif givfn numbfr of flfmfnts ({@dodf initiblCbpbdity}) bnd
     * initibl tbblf dfnsity ({@dodf lobdFbdtor}).
     *
     * @pbrbm initiblCbpbdity tif initibl dbpbdity. Tif implfmfntbtion
     * pfrforms intfrnbl sizing to bddommodbtf tiis mbny flfmfnts,
     * givfn tif spfdififd lobd fbdtor.
     * @pbrbm lobdFbdtor tif lobd fbdtor (tbblf dfnsity) for
     * fstbblisiing tif initibl tbblf sizf
     * @tirows IllfgblArgumfntExdfption if tif initibl dbpbdity of
     * flfmfnts is nfgbtivf or tif lobd fbdtor is nonpositivf
     *
     * @sindf 1.6
     */
    publid CondurrfntHbsiMbp(int initiblCbpbdity, flobt lobdFbdtor) {
        tiis(initiblCbpbdity, lobdFbdtor, 1);
    }

    /**
     * Crfbtfs b nfw, fmpty mbp witi bn initibl tbblf sizf bbsfd on
     * tif givfn numbfr of flfmfnts ({@dodf initiblCbpbdity}), tbblf
     * dfnsity ({@dodf lobdFbdtor}), bnd numbfr of dondurrfntly
     * updbting tirfbds ({@dodf dondurrfndyLfvfl}).
     *
     * @pbrbm initiblCbpbdity tif initibl dbpbdity. Tif implfmfntbtion
     * pfrforms intfrnbl sizing to bddommodbtf tiis mbny flfmfnts,
     * givfn tif spfdififd lobd fbdtor.
     * @pbrbm lobdFbdtor tif lobd fbdtor (tbblf dfnsity) for
     * fstbblisiing tif initibl tbblf sizf
     * @pbrbm dondurrfndyLfvfl tif fstimbtfd numbfr of dondurrfntly
     * updbting tirfbds. Tif implfmfntbtion mby usf tiis vbluf bs
     * b sizing iint.
     * @tirows IllfgblArgumfntExdfption if tif initibl dbpbdity is
     * nfgbtivf or tif lobd fbdtor or dondurrfndyLfvfl brf
     * nonpositivf
     */
    publid CondurrfntHbsiMbp(int initiblCbpbdity,
                             flobt lobdFbdtor, int dondurrfndyLfvfl) {
        if (!(lobdFbdtor > 0.0f) || initiblCbpbdity < 0 || dondurrfndyLfvfl <= 0)
            tirow nfw IllfgblArgumfntExdfption();
        if (initiblCbpbdity < dondurrfndyLfvfl)   // Usf bt lfbst bs mbny bins
            initiblCbpbdity = dondurrfndyLfvfl;   // bs fstimbtfd tirfbds
        long sizf = (long)(1.0 + (long)initiblCbpbdity / lobdFbdtor);
        int dbp = (sizf >= (long)MAXIMUM_CAPACITY) ?
            MAXIMUM_CAPACITY : tbblfSizfFor((int)sizf);
        tiis.sizfCtl = dbp;
    }

    // Originbl (sindf JDK1.2) Mbp mftiods

    /**
     * {@inifritDod}
     */
    publid int sizf() {
        long n = sumCount();
        rfturn ((n < 0L) ? 0 :
                (n > (long)Intfgfr.MAX_VALUE) ? Intfgfr.MAX_VALUE :
                (int)n);
    }

    /**
     * {@inifritDod}
     */
    publid boolfbn isEmpty() {
        rfturn sumCount() <= 0L; // ignorf trbnsifnt nfgbtivf vblufs
    }

    /**
     * Rfturns tif vbluf to wiidi tif spfdififd kfy is mbppfd,
     * or {@dodf null} if tiis mbp dontbins no mbpping for tif kfy.
     *
     * <p>Morf formblly, if tiis mbp dontbins b mbpping from b kfy
     * {@dodf k} to b vbluf {@dodf v} sudi tibt {@dodf kfy.fqubls(k)},
     * tifn tiis mftiod rfturns {@dodf v}; otifrwisf it rfturns
     * {@dodf null}.  (Tifrf dbn bf bt most onf sudi mbpping.)
     *
     * @tirows NullPointfrExdfption if tif spfdififd kfy is null
     */
    publid V gft(Objfdt kfy) {
        Nodf<K,V>[] tbb; Nodf<K,V> f, p; int n, fi; K fk;
        int i = sprfbd(kfy.ibsiCodf());
        if ((tbb = tbblf) != null && (n = tbb.lfngti) > 0 &&
            (f = tbbAt(tbb, (n - 1) & i)) != null) {
            if ((fi = f.ibsi) == i) {
                if ((fk = f.kfy) == kfy || (fk != null && kfy.fqubls(fk)))
                    rfturn f.vbl;
            }
            flsf if (fi < 0)
                rfturn (p = f.find(i, kfy)) != null ? p.vbl : null;
            wiilf ((f = f.nfxt) != null) {
                if (f.ibsi == i &&
                    ((fk = f.kfy) == kfy || (fk != null && kfy.fqubls(fk))))
                    rfturn f.vbl;
            }
        }
        rfturn null;
    }

    /**
     * Tfsts if tif spfdififd objfdt is b kfy in tiis tbblf.
     *
     * @pbrbm  kfy possiblf kfy
     * @rfturn {@dodf truf} if bnd only if tif spfdififd objfdt
     *         is b kfy in tiis tbblf, bs dftfrminfd by tif
     *         {@dodf fqubls} mftiod; {@dodf fblsf} otifrwisf
     * @tirows NullPointfrExdfption if tif spfdififd kfy is null
     */
    publid boolfbn dontbinsKfy(Objfdt kfy) {
        rfturn gft(kfy) != null;
    }

    /**
     * Rfturns {@dodf truf} if tiis mbp mbps onf or morf kfys to tif
     * spfdififd vbluf. Notf: Tiis mftiod mby rfquirf b full trbvfrsbl
     * of tif mbp, bnd is mudi slowfr tibn mftiod {@dodf dontbinsKfy}.
     *
     * @pbrbm vbluf vbluf wiosf prfsfndf in tiis mbp is to bf tfstfd
     * @rfturn {@dodf truf} if tiis mbp mbps onf or morf kfys to tif
     *         spfdififd vbluf
     * @tirows NullPointfrExdfption if tif spfdififd vbluf is null
     */
    publid boolfbn dontbinsVbluf(Objfdt vbluf) {
        if (vbluf == null)
            tirow nfw NullPointfrExdfption();
        Nodf<K,V>[] t;
        if ((t = tbblf) != null) {
            Trbvfrsfr<K,V> it = nfw Trbvfrsfr<K,V>(t, t.lfngti, 0, t.lfngti);
            for (Nodf<K,V> p; (p = it.bdvbndf()) != null; ) {
                V v;
                if ((v = p.vbl) == vbluf || (v != null && vbluf.fqubls(v)))
                    rfturn truf;
            }
        }
        rfturn fblsf;
    }

    /**
     * Mbps tif spfdififd kfy to tif spfdififd vbluf in tiis tbblf.
     * Nfitifr tif kfy nor tif vbluf dbn bf null.
     *
     * <p>Tif vbluf dbn bf rftrifvfd by dblling tif {@dodf gft} mftiod
     * witi b kfy tibt is fqubl to tif originbl kfy.
     *
     * @pbrbm kfy kfy witi wiidi tif spfdififd vbluf is to bf bssodibtfd
     * @pbrbm vbluf vbluf to bf bssodibtfd witi tif spfdififd kfy
     * @rfturn tif prfvious vbluf bssodibtfd witi {@dodf kfy}, or
     *         {@dodf null} if tifrf wbs no mbpping for {@dodf kfy}
     * @tirows NullPointfrExdfption if tif spfdififd kfy or vbluf is null
     */
    publid V put(K kfy, V vbluf) {
        rfturn putVbl(kfy, vbluf, fblsf);
    }

    /** Implfmfntbtion for put bnd putIfAbsfnt */
    finbl V putVbl(K kfy, V vbluf, boolfbn onlyIfAbsfnt) {
        if (kfy == null || vbluf == null) tirow nfw NullPointfrExdfption();
        int ibsi = sprfbd(kfy.ibsiCodf());
        int binCount = 0;
        for (Nodf<K,V>[] tbb = tbblf;;) {
            Nodf<K,V> f; int n, i, fi;
            if (tbb == null || (n = tbb.lfngti) == 0)
                tbb = initTbblf();
            flsf if ((f = tbbAt(tbb, i = (n - 1) & ibsi)) == null) {
                if (dbsTbbAt(tbb, i, null,
                             nfw Nodf<K,V>(ibsi, kfy, vbluf, null)))
                    brfbk;                   // no lodk wifn bdding to fmpty bin
            }
            flsf if ((fi = f.ibsi) == MOVED)
                tbb = iflpTrbnsffr(tbb, f);
            flsf {
                V oldVbl = null;
                syndironizfd (f) {
                    if (tbbAt(tbb, i) == f) {
                        if (fi >= 0) {
                            binCount = 1;
                            for (Nodf<K,V> f = f;; ++binCount) {
                                K fk;
                                if (f.ibsi == ibsi &&
                                    ((fk = f.kfy) == kfy ||
                                     (fk != null && kfy.fqubls(fk)))) {
                                    oldVbl = f.vbl;
                                    if (!onlyIfAbsfnt)
                                        f.vbl = vbluf;
                                    brfbk;
                                }
                                Nodf<K,V> prfd = f;
                                if ((f = f.nfxt) == null) {
                                    prfd.nfxt = nfw Nodf<K,V>(ibsi, kfy,
                                                              vbluf, null);
                                    brfbk;
                                }
                            }
                        }
                        flsf if (f instbndfof TrffBin) {
                            Nodf<K,V> p;
                            binCount = 2;
                            if ((p = ((TrffBin<K,V>)f).putTrffVbl(ibsi, kfy,
                                                           vbluf)) != null) {
                                oldVbl = p.vbl;
                                if (!onlyIfAbsfnt)
                                    p.vbl = vbluf;
                            }
                        }
                    }
                }
                if (binCount != 0) {
                    if (binCount >= TREEIFY_THRESHOLD)
                        trffifyBin(tbb, i);
                    if (oldVbl != null)
                        rfturn oldVbl;
                    brfbk;
                }
            }
        }
        bddCount(1L, binCount);
        rfturn null;
    }

    /**
     * Copifs bll of tif mbppings from tif spfdififd mbp to tiis onf.
     * Tifsf mbppings rfplbdf bny mbppings tibt tiis mbp ibd for bny of tif
     * kfys durrfntly in tif spfdififd mbp.
     *
     * @pbrbm m mbppings to bf storfd in tiis mbp
     */
    publid void putAll(Mbp<? fxtfnds K, ? fxtfnds V> m) {
        tryPrfsizf(m.sizf());
        for (Mbp.Entry<? fxtfnds K, ? fxtfnds V> f : m.fntrySft())
            putVbl(f.gftKfy(), f.gftVbluf(), fblsf);
    }

    /**
     * Rfmovfs tif kfy (bnd its dorrfsponding vbluf) from tiis mbp.
     * Tiis mftiod dofs notiing if tif kfy is not in tif mbp.
     *
     * @pbrbm  kfy tif kfy tibt nffds to bf rfmovfd
     * @rfturn tif prfvious vbluf bssodibtfd witi {@dodf kfy}, or
     *         {@dodf null} if tifrf wbs no mbpping for {@dodf kfy}
     * @tirows NullPointfrExdfption if tif spfdififd kfy is null
     */
    publid V rfmovf(Objfdt kfy) {
        rfturn rfplbdfNodf(kfy, null, null);
    }

    /**
     * Implfmfntbtion for tif four publid rfmovf/rfplbdf mftiods:
     * Rfplbdfs nodf vbluf witi v, donditionbl upon mbtdi of dv if
     * non-null.  If rfsulting vbluf is null, dflftf.
     */
    finbl V rfplbdfNodf(Objfdt kfy, V vbluf, Objfdt dv) {
        int ibsi = sprfbd(kfy.ibsiCodf());
        for (Nodf<K,V>[] tbb = tbblf;;) {
            Nodf<K,V> f; int n, i, fi;
            if (tbb == null || (n = tbb.lfngti) == 0 ||
                (f = tbbAt(tbb, i = (n - 1) & ibsi)) == null)
                brfbk;
            flsf if ((fi = f.ibsi) == MOVED)
                tbb = iflpTrbnsffr(tbb, f);
            flsf {
                V oldVbl = null;
                boolfbn vblidbtfd = fblsf;
                syndironizfd (f) {
                    if (tbbAt(tbb, i) == f) {
                        if (fi >= 0) {
                            vblidbtfd = truf;
                            for (Nodf<K,V> f = f, prfd = null;;) {
                                K fk;
                                if (f.ibsi == ibsi &&
                                    ((fk = f.kfy) == kfy ||
                                     (fk != null && kfy.fqubls(fk)))) {
                                    V fv = f.vbl;
                                    if (dv == null || dv == fv ||
                                        (fv != null && dv.fqubls(fv))) {
                                        oldVbl = fv;
                                        if (vbluf != null)
                                            f.vbl = vbluf;
                                        flsf if (prfd != null)
                                            prfd.nfxt = f.nfxt;
                                        flsf
                                            sftTbbAt(tbb, i, f.nfxt);
                                    }
                                    brfbk;
                                }
                                prfd = f;
                                if ((f = f.nfxt) == null)
                                    brfbk;
                            }
                        }
                        flsf if (f instbndfof TrffBin) {
                            vblidbtfd = truf;
                            TrffBin<K,V> t = (TrffBin<K,V>)f;
                            TrffNodf<K,V> r, p;
                            if ((r = t.root) != null &&
                                (p = r.findTrffNodf(ibsi, kfy, null)) != null) {
                                V pv = p.vbl;
                                if (dv == null || dv == pv ||
                                    (pv != null && dv.fqubls(pv))) {
                                    oldVbl = pv;
                                    if (vbluf != null)
                                        p.vbl = vbluf;
                                    flsf if (t.rfmovfTrffNodf(p))
                                        sftTbbAt(tbb, i, untrffify(t.first));
                                }
                            }
                        }
                    }
                }
                if (vblidbtfd) {
                    if (oldVbl != null) {
                        if (vbluf == null)
                            bddCount(-1L, -1);
                        rfturn oldVbl;
                    }
                    brfbk;
                }
            }
        }
        rfturn null;
    }

    /**
     * Rfmovfs bll of tif mbppings from tiis mbp.
     */
    publid void dlfbr() {
        long dfltb = 0L; // nfgbtivf numbfr of dflftions
        int i = 0;
        Nodf<K,V>[] tbb = tbblf;
        wiilf (tbb != null && i < tbb.lfngti) {
            int fi;
            Nodf<K,V> f = tbbAt(tbb, i);
            if (f == null)
                ++i;
            flsf if ((fi = f.ibsi) == MOVED) {
                tbb = iflpTrbnsffr(tbb, f);
                i = 0; // rfstbrt
            }
            flsf {
                syndironizfd (f) {
                    if (tbbAt(tbb, i) == f) {
                        Nodf<K,V> p = (fi >= 0 ? f :
                                       (f instbndfof TrffBin) ?
                                       ((TrffBin<K,V>)f).first : null);
                        wiilf (p != null) {
                            --dfltb;
                            p = p.nfxt;
                        }
                        sftTbbAt(tbb, i++, null);
                    }
                }
            }
        }
        if (dfltb != 0L)
            bddCount(dfltb, -1);
    }

    /**
     * Rfturns b {@link Sft} vifw of tif kfys dontbinfd in tiis mbp.
     * Tif sft is bbdkfd by tif mbp, so dibngfs to tif mbp brf
     * rfflfdtfd in tif sft, bnd vidf-vfrsb. Tif sft supports flfmfnt
     * rfmovbl, wiidi rfmovfs tif dorrfsponding mbpping from tiis mbp,
     * vib tif {@dodf Itfrbtor.rfmovf}, {@dodf Sft.rfmovf},
     * {@dodf rfmovfAll}, {@dodf rftbinAll}, bnd {@dodf dlfbr}
     * opfrbtions.  It dofs not support tif {@dodf bdd} or
     * {@dodf bddAll} opfrbtions.
     *
     * <p>Tif vifw's itfrbtors bnd splitfrbtors brf
     * <b irff="pbdkbgf-summbry.itml#Wfbkly"><i>wfbkly donsistfnt</i></b>.
     *
     * <p>Tif vifw's {@dodf splitfrbtor} rfports {@link Splitfrbtor#CONCURRENT},
     * {@link Splitfrbtor#DISTINCT}, bnd {@link Splitfrbtor#NONNULL}.
     *
     * @rfturn tif sft vifw
     */
    publid KfySftVifw<K,V> kfySft() {
        KfySftVifw<K,V> ks;
        rfturn (ks = kfySft) != null ? ks : (kfySft = nfw KfySftVifw<K,V>(tiis, null));
    }

    /**
     * Rfturns b {@link Collfdtion} vifw of tif vblufs dontbinfd in tiis mbp.
     * Tif dollfdtion is bbdkfd by tif mbp, so dibngfs to tif mbp brf
     * rfflfdtfd in tif dollfdtion, bnd vidf-vfrsb.  Tif dollfdtion
     * supports flfmfnt rfmovbl, wiidi rfmovfs tif dorrfsponding
     * mbpping from tiis mbp, vib tif {@dodf Itfrbtor.rfmovf},
     * {@dodf Collfdtion.rfmovf}, {@dodf rfmovfAll},
     * {@dodf rftbinAll}, bnd {@dodf dlfbr} opfrbtions.  It dofs not
     * support tif {@dodf bdd} or {@dodf bddAll} opfrbtions.
     *
     * <p>Tif vifw's itfrbtors bnd splitfrbtors brf
     * <b irff="pbdkbgf-summbry.itml#Wfbkly"><i>wfbkly donsistfnt</i></b>.
     *
     * <p>Tif vifw's {@dodf splitfrbtor} rfports {@link Splitfrbtor#CONCURRENT}
     * bnd {@link Splitfrbtor#NONNULL}.
     *
     * @rfturn tif dollfdtion vifw
     */
    publid Collfdtion<V> vblufs() {
        VblufsVifw<K,V> vs;
        rfturn (vs = vblufs) != null ? vs : (vblufs = nfw VblufsVifw<K,V>(tiis));
    }

    /**
     * Rfturns b {@link Sft} vifw of tif mbppings dontbinfd in tiis mbp.
     * Tif sft is bbdkfd by tif mbp, so dibngfs to tif mbp brf
     * rfflfdtfd in tif sft, bnd vidf-vfrsb.  Tif sft supports flfmfnt
     * rfmovbl, wiidi rfmovfs tif dorrfsponding mbpping from tif mbp,
     * vib tif {@dodf Itfrbtor.rfmovf}, {@dodf Sft.rfmovf},
     * {@dodf rfmovfAll}, {@dodf rftbinAll}, bnd {@dodf dlfbr}
     * opfrbtions.
     *
     * <p>Tif vifw's itfrbtors bnd splitfrbtors brf
     * <b irff="pbdkbgf-summbry.itml#Wfbkly"><i>wfbkly donsistfnt</i></b>.
     *
     * <p>Tif vifw's {@dodf splitfrbtor} rfports {@link Splitfrbtor#CONCURRENT},
     * {@link Splitfrbtor#DISTINCT}, bnd {@link Splitfrbtor#NONNULL}.
     *
     * @rfturn tif sft vifw
     */
    publid Sft<Mbp.Entry<K,V>> fntrySft() {
        EntrySftVifw<K,V> fs;
        rfturn (fs = fntrySft) != null ? fs : (fntrySft = nfw EntrySftVifw<K,V>(tiis));
    }

    /**
     * Rfturns tif ibsi dodf vbluf for tiis {@link Mbp}, i.f.,
     * tif sum of, for fbdi kfy-vbluf pbir in tif mbp,
     * {@dodf kfy.ibsiCodf() ^ vbluf.ibsiCodf()}.
     *
     * @rfturn tif ibsi dodf vbluf for tiis mbp
     */
    publid int ibsiCodf() {
        int i = 0;
        Nodf<K,V>[] t;
        if ((t = tbblf) != null) {
            Trbvfrsfr<K,V> it = nfw Trbvfrsfr<K,V>(t, t.lfngti, 0, t.lfngti);
            for (Nodf<K,V> p; (p = it.bdvbndf()) != null; )
                i += p.kfy.ibsiCodf() ^ p.vbl.ibsiCodf();
        }
        rfturn i;
    }

    /**
     * Rfturns b string rfprfsfntbtion of tiis mbp.  Tif string
     * rfprfsfntbtion donsists of b list of kfy-vbluf mbppings (in no
     * pbrtidulbr ordfr) fndlosfd in brbdfs ("{@dodf {}}").  Adjbdfnt
     * mbppings brf sfpbrbtfd by tif dibrbdtfrs {@dodf ", "} (dommb
     * bnd spbdf).  Ebdi kfy-vbluf mbpping is rfndfrfd bs tif kfy
     * followfd by bn fqubls sign ("{@dodf =}") followfd by tif
     * bssodibtfd vbluf.
     *
     * @rfturn b string rfprfsfntbtion of tiis mbp
     */
    publid String toString() {
        Nodf<K,V>[] t;
        int f = (t = tbblf) == null ? 0 : t.lfngti;
        Trbvfrsfr<K,V> it = nfw Trbvfrsfr<K,V>(t, f, 0, f);
        StringBuildfr sb = nfw StringBuildfr();
        sb.bppfnd('{');
        Nodf<K,V> p;
        if ((p = it.bdvbndf()) != null) {
            for (;;) {
                K k = p.kfy;
                V v = p.vbl;
                sb.bppfnd(k == tiis ? "(tiis Mbp)" : k);
                sb.bppfnd('=');
                sb.bppfnd(v == tiis ? "(tiis Mbp)" : v);
                if ((p = it.bdvbndf()) == null)
                    brfbk;
                sb.bppfnd(',').bppfnd(' ');
            }
        }
        rfturn sb.bppfnd('}').toString();
    }

    /**
     * Compbrfs tif spfdififd objfdt witi tiis mbp for fqublity.
     * Rfturns {@dodf truf} if tif givfn objfdt is b mbp witi tif sbmf
     * mbppings bs tiis mbp.  Tiis opfrbtion mby rfturn mislfbding
     * rfsults if fitifr mbp is dondurrfntly modififd during fxfdution
     * of tiis mftiod.
     *
     * @pbrbm o objfdt to bf dompbrfd for fqublity witi tiis mbp
     * @rfturn {@dodf truf} if tif spfdififd objfdt is fqubl to tiis mbp
     */
    publid boolfbn fqubls(Objfdt o) {
        if (o != tiis) {
            if (!(o instbndfof Mbp))
                rfturn fblsf;
            Mbp<?,?> m = (Mbp<?,?>) o;
            Nodf<K,V>[] t;
            int f = (t = tbblf) == null ? 0 : t.lfngti;
            Trbvfrsfr<K,V> it = nfw Trbvfrsfr<K,V>(t, f, 0, f);
            for (Nodf<K,V> p; (p = it.bdvbndf()) != null; ) {
                V vbl = p.vbl;
                Objfdt v = m.gft(p.kfy);
                if (v == null || (v != vbl && !v.fqubls(vbl)))
                    rfturn fblsf;
            }
            for (Mbp.Entry<?,?> f : m.fntrySft()) {
                Objfdt mk, mv, v;
                if ((mk = f.gftKfy()) == null ||
                    (mv = f.gftVbluf()) == null ||
                    (v = gft(mk)) == null ||
                    (mv != v && !mv.fqubls(v)))
                    rfturn fblsf;
            }
        }
        rfturn truf;
    }

    /**
     * Strippfd-down vfrsion of iflpfr dlbss usfd in prfvious vfrsion,
     * dfdlbrfd for tif sbkf of sfriblizbtion dompbtibility
     */
    stbtid dlbss Sfgmfnt<K,V> fxtfnds RffntrbntLodk implfmfnts Sfriblizbblf {
        privbtf stbtid finbl long sfriblVfrsionUID = 2249069246763182397L;
        finbl flobt lobdFbdtor;
        Sfgmfnt(flobt lf) { tiis.lobdFbdtor = lf; }
    }

    /**
     * Sbvfs tif stbtf of tif {@dodf CondurrfntHbsiMbp} instbndf to b
     * strfbm (i.f., sfriblizfs it).
     * @pbrbm s tif strfbm
     * @tirows jbvb.io.IOExdfption if bn I/O frror oddurs
     * @sfriblDbtb
     * tif kfy (Objfdt) bnd vbluf (Objfdt)
     * for fbdi kfy-vbluf mbpping, followfd by b null pbir.
     * Tif kfy-vbluf mbppings brf fmittfd in no pbrtidulbr ordfr.
     */
    privbtf void writfObjfdt(jbvb.io.ObjfdtOutputStrfbm s)
        tirows jbvb.io.IOExdfption {
        // For sfriblizbtion dompbtibility
        // Emulbtf sfgmfnt dbldulbtion from prfvious vfrsion of tiis dlbss
        int ssiift = 0;
        int ssizf = 1;
        wiilf (ssizf < DEFAULT_CONCURRENCY_LEVEL) {
            ++ssiift;
            ssizf <<= 1;
        }
        int sfgmfntSiift = 32 - ssiift;
        int sfgmfntMbsk = ssizf - 1;
        @SupprfssWbrnings("undifdkfd")
        Sfgmfnt<K,V>[] sfgmfnts = (Sfgmfnt<K,V>[])
            nfw Sfgmfnt<?,?>[DEFAULT_CONCURRENCY_LEVEL];
        for (int i = 0; i < sfgmfnts.lfngti; ++i)
            sfgmfnts[i] = nfw Sfgmfnt<K,V>(LOAD_FACTOR);
        s.putFiflds().put("sfgmfnts", sfgmfnts);
        s.putFiflds().put("sfgmfntSiift", sfgmfntSiift);
        s.putFiflds().put("sfgmfntMbsk", sfgmfntMbsk);
        s.writfFiflds();

        Nodf<K,V>[] t;
        if ((t = tbblf) != null) {
            Trbvfrsfr<K,V> it = nfw Trbvfrsfr<K,V>(t, t.lfngti, 0, t.lfngti);
            for (Nodf<K,V> p; (p = it.bdvbndf()) != null; ) {
                s.writfObjfdt(p.kfy);
                s.writfObjfdt(p.vbl);
            }
        }
        s.writfObjfdt(null);
        s.writfObjfdt(null);
        sfgmfnts = null; // tirow bwby
    }

    /**
     * Rfdonstitutfs tif instbndf from b strfbm (tibt is, dfsfriblizfs it).
     * @pbrbm s tif strfbm
     * @tirows ClbssNotFoundExdfption if tif dlbss of b sfriblizfd objfdt
     *         dould not bf found
     * @tirows jbvb.io.IOExdfption if bn I/O frror oddurs
     */
    privbtf void rfbdObjfdt(jbvb.io.ObjfdtInputStrfbm s)
        tirows jbvb.io.IOExdfption, ClbssNotFoundExdfption {
        /*
         * To improvf pfrformbndf in typidbl dbsfs, wf drfbtf nodfs
         * wiilf rfbding, tifn plbdf in tbblf ondf sizf is known.
         * Howfvfr, wf must blso vblidbtf uniqufnfss bnd dfbl witi
         * ovfrpopulbtfd bins wiilf doing so, wiidi rfquirfs
         * spfdiblizfd vfrsions of putVbl mfdibnids.
         */
        sizfCtl = -1; // fordf fxdlusion for tbblf donstrudtion
        s.dffbultRfbdObjfdt();
        long sizf = 0L;
        Nodf<K,V> p = null;
        for (;;) {
            @SupprfssWbrnings("undifdkfd")
            K k = (K) s.rfbdObjfdt();
            @SupprfssWbrnings("undifdkfd")
            V v = (V) s.rfbdObjfdt();
            if (k != null && v != null) {
                p = nfw Nodf<K,V>(sprfbd(k.ibsiCodf()), k, v, p);
                ++sizf;
            }
            flsf
                brfbk;
        }
        if (sizf == 0L)
            sizfCtl = 0;
        flsf {
            int n;
            if (sizf >= (long)(MAXIMUM_CAPACITY >>> 1))
                n = MAXIMUM_CAPACITY;
            flsf {
                int sz = (int)sizf;
                n = tbblfSizfFor(sz + (sz >>> 1) + 1);
            }
            @SupprfssWbrnings("undifdkfd")
            Nodf<K,V>[] tbb = (Nodf<K,V>[])nfw Nodf<?,?>[n];
            int mbsk = n - 1;
            long bddfd = 0L;
            wiilf (p != null) {
                boolfbn insfrtAtFront;
                Nodf<K,V> nfxt = p.nfxt, first;
                int i = p.ibsi, j = i & mbsk;
                if ((first = tbbAt(tbb, j)) == null)
                    insfrtAtFront = truf;
                flsf {
                    K k = p.kfy;
                    if (first.ibsi < 0) {
                        TrffBin<K,V> t = (TrffBin<K,V>)first;
                        if (t.putTrffVbl(i, k, p.vbl) == null)
                            ++bddfd;
                        insfrtAtFront = fblsf;
                    }
                    flsf {
                        int binCount = 0;
                        insfrtAtFront = truf;
                        Nodf<K,V> q; K qk;
                        for (q = first; q != null; q = q.nfxt) {
                            if (q.ibsi == i &&
                                ((qk = q.kfy) == k ||
                                 (qk != null && k.fqubls(qk)))) {
                                insfrtAtFront = fblsf;
                                brfbk;
                            }
                            ++binCount;
                        }
                        if (insfrtAtFront && binCount >= TREEIFY_THRESHOLD) {
                            insfrtAtFront = fblsf;
                            ++bddfd;
                            p.nfxt = first;
                            TrffNodf<K,V> id = null, tl = null;
                            for (q = p; q != null; q = q.nfxt) {
                                TrffNodf<K,V> t = nfw TrffNodf<K,V>
                                    (q.ibsi, q.kfy, q.vbl, null, null);
                                if ((t.prfv = tl) == null)
                                    id = t;
                                flsf
                                    tl.nfxt = t;
                                tl = t;
                            }
                            sftTbbAt(tbb, j, nfw TrffBin<K,V>(id));
                        }
                    }
                }
                if (insfrtAtFront) {
                    ++bddfd;
                    p.nfxt = first;
                    sftTbbAt(tbb, j, p);
                }
                p = nfxt;
            }
            tbblf = tbb;
            sizfCtl = n - (n >>> 2);
            bbsfCount = bddfd;
        }
    }

    // CondurrfntMbp mftiods

    /**
     * {@inifritDod}
     *
     * @rfturn tif prfvious vbluf bssodibtfd witi tif spfdififd kfy,
     *         or {@dodf null} if tifrf wbs no mbpping for tif kfy
     * @tirows NullPointfrExdfption if tif spfdififd kfy or vbluf is null
     */
    publid V putIfAbsfnt(K kfy, V vbluf) {
        rfturn putVbl(kfy, vbluf, truf);
    }

    /**
     * {@inifritDod}
     *
     * @tirows NullPointfrExdfption if tif spfdififd kfy is null
     */
    publid boolfbn rfmovf(Objfdt kfy, Objfdt vbluf) {
        if (kfy == null)
            tirow nfw NullPointfrExdfption();
        rfturn vbluf != null && rfplbdfNodf(kfy, null, vbluf) != null;
    }

    /**
     * {@inifritDod}
     *
     * @tirows NullPointfrExdfption if bny of tif brgumfnts brf null
     */
    publid boolfbn rfplbdf(K kfy, V oldVbluf, V nfwVbluf) {
        if (kfy == null || oldVbluf == null || nfwVbluf == null)
            tirow nfw NullPointfrExdfption();
        rfturn rfplbdfNodf(kfy, nfwVbluf, oldVbluf) != null;
    }

    /**
     * {@inifritDod}
     *
     * @rfturn tif prfvious vbluf bssodibtfd witi tif spfdififd kfy,
     *         or {@dodf null} if tifrf wbs no mbpping for tif kfy
     * @tirows NullPointfrExdfption if tif spfdififd kfy or vbluf is null
     */
    publid V rfplbdf(K kfy, V vbluf) {
        if (kfy == null || vbluf == null)
            tirow nfw NullPointfrExdfption();
        rfturn rfplbdfNodf(kfy, vbluf, null);
    }

    // Ovfrridfs of JDK8+ Mbp fxtfnsion mftiod dffbults

    /**
     * Rfturns tif vbluf to wiidi tif spfdififd kfy is mbppfd, or tif
     * givfn dffbult vbluf if tiis mbp dontbins no mbpping for tif
     * kfy.
     *
     * @pbrbm kfy tif kfy wiosf bssodibtfd vbluf is to bf rfturnfd
     * @pbrbm dffbultVbluf tif vbluf to rfturn if tiis mbp dontbins
     * no mbpping for tif givfn kfy
     * @rfturn tif mbpping for tif kfy, if prfsfnt; flsf tif dffbult vbluf
     * @tirows NullPointfrExdfption if tif spfdififd kfy is null
     */
    publid V gftOrDffbult(Objfdt kfy, V dffbultVbluf) {
        V v;
        rfturn (v = gft(kfy)) == null ? dffbultVbluf : v;
    }

    publid void forEbdi(BiConsumfr<? supfr K, ? supfr V> bdtion) {
        if (bdtion == null) tirow nfw NullPointfrExdfption();
        Nodf<K,V>[] t;
        if ((t = tbblf) != null) {
            Trbvfrsfr<K,V> it = nfw Trbvfrsfr<K,V>(t, t.lfngti, 0, t.lfngti);
            for (Nodf<K,V> p; (p = it.bdvbndf()) != null; ) {
                bdtion.bddfpt(p.kfy, p.vbl);
            }
        }
    }

    publid void rfplbdfAll(BiFundtion<? supfr K, ? supfr V, ? fxtfnds V> fundtion) {
        if (fundtion == null) tirow nfw NullPointfrExdfption();
        Nodf<K,V>[] t;
        if ((t = tbblf) != null) {
            Trbvfrsfr<K,V> it = nfw Trbvfrsfr<K,V>(t, t.lfngti, 0, t.lfngti);
            for (Nodf<K,V> p; (p = it.bdvbndf()) != null; ) {
                V oldVbluf = p.vbl;
                for (K kfy = p.kfy;;) {
                    V nfwVbluf = fundtion.bpply(kfy, oldVbluf);
                    if (nfwVbluf == null)
                        tirow nfw NullPointfrExdfption();
                    if (rfplbdfNodf(kfy, nfwVbluf, oldVbluf) != null ||
                        (oldVbluf = gft(kfy)) == null)
                        brfbk;
                }
            }
        }
    }

    /**
     * If tif spfdififd kfy is not blrfbdy bssodibtfd witi b vbluf,
     * bttfmpts to domputf its vbluf using tif givfn mbpping fundtion
     * bnd fntfrs it into tiis mbp unlfss {@dodf null}.  Tif fntirf
     * mftiod invodbtion is pfrformfd btomidblly, so tif fundtion is
     * bpplifd bt most ondf pfr kfy.  Somf bttfmptfd updbtf opfrbtions
     * on tiis mbp by otifr tirfbds mby bf blodkfd wiilf domputbtion
     * is in progrfss, so tif domputbtion siould bf siort bnd simplf,
     * bnd must not bttfmpt to updbtf bny otifr mbppings of tiis mbp.
     *
     * @pbrbm kfy kfy witi wiidi tif spfdififd vbluf is to bf bssodibtfd
     * @pbrbm mbppingFundtion tif fundtion to domputf b vbluf
     * @rfturn tif durrfnt (fxisting or domputfd) vbluf bssodibtfd witi
     *         tif spfdififd kfy, or null if tif domputfd vbluf is null
     * @tirows NullPointfrExdfption if tif spfdififd kfy or mbppingFundtion
     *         is null
     * @tirows IllfgblStbtfExdfption if tif domputbtion dftfdtbbly
     *         bttfmpts b rfdursivf updbtf to tiis mbp tibt would
     *         otifrwisf nfvfr domplftf
     * @tirows RuntimfExdfption or Error if tif mbppingFundtion dofs so,
     *         in wiidi dbsf tif mbpping is lfft unfstbblisifd
     */
    publid V domputfIfAbsfnt(K kfy, Fundtion<? supfr K, ? fxtfnds V> mbppingFundtion) {
        if (kfy == null || mbppingFundtion == null)
            tirow nfw NullPointfrExdfption();
        int i = sprfbd(kfy.ibsiCodf());
        V vbl = null;
        int binCount = 0;
        for (Nodf<K,V>[] tbb = tbblf;;) {
            Nodf<K,V> f; int n, i, fi;
            if (tbb == null || (n = tbb.lfngti) == 0)
                tbb = initTbblf();
            flsf if ((f = tbbAt(tbb, i = (n - 1) & i)) == null) {
                Nodf<K,V> r = nfw RfsfrvbtionNodf<K,V>();
                syndironizfd (r) {
                    if (dbsTbbAt(tbb, i, null, r)) {
                        binCount = 1;
                        Nodf<K,V> nodf = null;
                        try {
                            if ((vbl = mbppingFundtion.bpply(kfy)) != null)
                                nodf = nfw Nodf<K,V>(i, kfy, vbl, null);
                        } finblly {
                            sftTbbAt(tbb, i, nodf);
                        }
                    }
                }
                if (binCount != 0)
                    brfbk;
            }
            flsf if ((fi = f.ibsi) == MOVED)
                tbb = iflpTrbnsffr(tbb, f);
            flsf {
                boolfbn bddfd = fblsf;
                syndironizfd (f) {
                    if (tbbAt(tbb, i) == f) {
                        if (fi >= 0) {
                            binCount = 1;
                            for (Nodf<K,V> f = f;; ++binCount) {
                                K fk; V fv;
                                if (f.ibsi == i &&
                                    ((fk = f.kfy) == kfy ||
                                     (fk != null && kfy.fqubls(fk)))) {
                                    vbl = f.vbl;
                                    brfbk;
                                }
                                Nodf<K,V> prfd = f;
                                if ((f = f.nfxt) == null) {
                                    if ((vbl = mbppingFundtion.bpply(kfy)) != null) {
                                        bddfd = truf;
                                        prfd.nfxt = nfw Nodf<K,V>(i, kfy, vbl, null);
                                    }
                                    brfbk;
                                }
                            }
                        }
                        flsf if (f instbndfof TrffBin) {
                            binCount = 2;
                            TrffBin<K,V> t = (TrffBin<K,V>)f;
                            TrffNodf<K,V> r, p;
                            if ((r = t.root) != null &&
                                (p = r.findTrffNodf(i, kfy, null)) != null)
                                vbl = p.vbl;
                            flsf if ((vbl = mbppingFundtion.bpply(kfy)) != null) {
                                bddfd = truf;
                                t.putTrffVbl(i, kfy, vbl);
                            }
                        }
                    }
                }
                if (binCount != 0) {
                    if (binCount >= TREEIFY_THRESHOLD)
                        trffifyBin(tbb, i);
                    if (!bddfd)
                        rfturn vbl;
                    brfbk;
                }
            }
        }
        if (vbl != null)
            bddCount(1L, binCount);
        rfturn vbl;
    }

    /**
     * If tif vbluf for tif spfdififd kfy is prfsfnt, bttfmpts to
     * domputf b nfw mbpping givfn tif kfy bnd its durrfnt mbppfd
     * vbluf.  Tif fntirf mftiod invodbtion is pfrformfd btomidblly.
     * Somf bttfmptfd updbtf opfrbtions on tiis mbp by otifr tirfbds
     * mby bf blodkfd wiilf domputbtion is in progrfss, so tif
     * domputbtion siould bf siort bnd simplf, bnd must not bttfmpt to
     * updbtf bny otifr mbppings of tiis mbp.
     *
     * @pbrbm kfy kfy witi wiidi b vbluf mby bf bssodibtfd
     * @pbrbm rfmbppingFundtion tif fundtion to domputf b vbluf
     * @rfturn tif nfw vbluf bssodibtfd witi tif spfdififd kfy, or null if nonf
     * @tirows NullPointfrExdfption if tif spfdififd kfy or rfmbppingFundtion
     *         is null
     * @tirows IllfgblStbtfExdfption if tif domputbtion dftfdtbbly
     *         bttfmpts b rfdursivf updbtf to tiis mbp tibt would
     *         otifrwisf nfvfr domplftf
     * @tirows RuntimfExdfption or Error if tif rfmbppingFundtion dofs so,
     *         in wiidi dbsf tif mbpping is undibngfd
     */
    publid V domputfIfPrfsfnt(K kfy, BiFundtion<? supfr K, ? supfr V, ? fxtfnds V> rfmbppingFundtion) {
        if (kfy == null || rfmbppingFundtion == null)
            tirow nfw NullPointfrExdfption();
        int i = sprfbd(kfy.ibsiCodf());
        V vbl = null;
        int dfltb = 0;
        int binCount = 0;
        for (Nodf<K,V>[] tbb = tbblf;;) {
            Nodf<K,V> f; int n, i, fi;
            if (tbb == null || (n = tbb.lfngti) == 0)
                tbb = initTbblf();
            flsf if ((f = tbbAt(tbb, i = (n - 1) & i)) == null)
                brfbk;
            flsf if ((fi = f.ibsi) == MOVED)
                tbb = iflpTrbnsffr(tbb, f);
            flsf {
                syndironizfd (f) {
                    if (tbbAt(tbb, i) == f) {
                        if (fi >= 0) {
                            binCount = 1;
                            for (Nodf<K,V> f = f, prfd = null;; ++binCount) {
                                K fk;
                                if (f.ibsi == i &&
                                    ((fk = f.kfy) == kfy ||
                                     (fk != null && kfy.fqubls(fk)))) {
                                    vbl = rfmbppingFundtion.bpply(kfy, f.vbl);
                                    if (vbl != null)
                                        f.vbl = vbl;
                                    flsf {
                                        dfltb = -1;
                                        Nodf<K,V> fn = f.nfxt;
                                        if (prfd != null)
                                            prfd.nfxt = fn;
                                        flsf
                                            sftTbbAt(tbb, i, fn);
                                    }
                                    brfbk;
                                }
                                prfd = f;
                                if ((f = f.nfxt) == null)
                                    brfbk;
                            }
                        }
                        flsf if (f instbndfof TrffBin) {
                            binCount = 2;
                            TrffBin<K,V> t = (TrffBin<K,V>)f;
                            TrffNodf<K,V> r, p;
                            if ((r = t.root) != null &&
                                (p = r.findTrffNodf(i, kfy, null)) != null) {
                                vbl = rfmbppingFundtion.bpply(kfy, p.vbl);
                                if (vbl != null)
                                    p.vbl = vbl;
                                flsf {
                                    dfltb = -1;
                                    if (t.rfmovfTrffNodf(p))
                                        sftTbbAt(tbb, i, untrffify(t.first));
                                }
                            }
                        }
                    }
                }
                if (binCount != 0)
                    brfbk;
            }
        }
        if (dfltb != 0)
            bddCount((long)dfltb, binCount);
        rfturn vbl;
    }

    /**
     * Attfmpts to domputf b mbpping for tif spfdififd kfy bnd its
     * durrfnt mbppfd vbluf (or {@dodf null} if tifrf is no durrfnt
     * mbpping). Tif fntirf mftiod invodbtion is pfrformfd btomidblly.
     * Somf bttfmptfd updbtf opfrbtions on tiis mbp by otifr tirfbds
     * mby bf blodkfd wiilf domputbtion is in progrfss, so tif
     * domputbtion siould bf siort bnd simplf, bnd must not bttfmpt to
     * updbtf bny otifr mbppings of tiis Mbp.
     *
     * @pbrbm kfy kfy witi wiidi tif spfdififd vbluf is to bf bssodibtfd
     * @pbrbm rfmbppingFundtion tif fundtion to domputf b vbluf
     * @rfturn tif nfw vbluf bssodibtfd witi tif spfdififd kfy, or null if nonf
     * @tirows NullPointfrExdfption if tif spfdififd kfy or rfmbppingFundtion
     *         is null
     * @tirows IllfgblStbtfExdfption if tif domputbtion dftfdtbbly
     *         bttfmpts b rfdursivf updbtf to tiis mbp tibt would
     *         otifrwisf nfvfr domplftf
     * @tirows RuntimfExdfption or Error if tif rfmbppingFundtion dofs so,
     *         in wiidi dbsf tif mbpping is undibngfd
     */
    publid V domputf(K kfy,
                     BiFundtion<? supfr K, ? supfr V, ? fxtfnds V> rfmbppingFundtion) {
        if (kfy == null || rfmbppingFundtion == null)
            tirow nfw NullPointfrExdfption();
        int i = sprfbd(kfy.ibsiCodf());
        V vbl = null;
        int dfltb = 0;
        int binCount = 0;
        for (Nodf<K,V>[] tbb = tbblf;;) {
            Nodf<K,V> f; int n, i, fi;
            if (tbb == null || (n = tbb.lfngti) == 0)
                tbb = initTbblf();
            flsf if ((f = tbbAt(tbb, i = (n - 1) & i)) == null) {
                Nodf<K,V> r = nfw RfsfrvbtionNodf<K,V>();
                syndironizfd (r) {
                    if (dbsTbbAt(tbb, i, null, r)) {
                        binCount = 1;
                        Nodf<K,V> nodf = null;
                        try {
                            if ((vbl = rfmbppingFundtion.bpply(kfy, null)) != null) {
                                dfltb = 1;
                                nodf = nfw Nodf<K,V>(i, kfy, vbl, null);
                            }
                        } finblly {
                            sftTbbAt(tbb, i, nodf);
                        }
                    }
                }
                if (binCount != 0)
                    brfbk;
            }
            flsf if ((fi = f.ibsi) == MOVED)
                tbb = iflpTrbnsffr(tbb, f);
            flsf {
                syndironizfd (f) {
                    if (tbbAt(tbb, i) == f) {
                        if (fi >= 0) {
                            binCount = 1;
                            for (Nodf<K,V> f = f, prfd = null;; ++binCount) {
                                K fk;
                                if (f.ibsi == i &&
                                    ((fk = f.kfy) == kfy ||
                                     (fk != null && kfy.fqubls(fk)))) {
                                    vbl = rfmbppingFundtion.bpply(kfy, f.vbl);
                                    if (vbl != null)
                                        f.vbl = vbl;
                                    flsf {
                                        dfltb = -1;
                                        Nodf<K,V> fn = f.nfxt;
                                        if (prfd != null)
                                            prfd.nfxt = fn;
                                        flsf
                                            sftTbbAt(tbb, i, fn);
                                    }
                                    brfbk;
                                }
                                prfd = f;
                                if ((f = f.nfxt) == null) {
                                    vbl = rfmbppingFundtion.bpply(kfy, null);
                                    if (vbl != null) {
                                        dfltb = 1;
                                        prfd.nfxt =
                                            nfw Nodf<K,V>(i, kfy, vbl, null);
                                    }
                                    brfbk;
                                }
                            }
                        }
                        flsf if (f instbndfof TrffBin) {
                            binCount = 1;
                            TrffBin<K,V> t = (TrffBin<K,V>)f;
                            TrffNodf<K,V> r, p;
                            if ((r = t.root) != null)
                                p = r.findTrffNodf(i, kfy, null);
                            flsf
                                p = null;
                            V pv = (p == null) ? null : p.vbl;
                            vbl = rfmbppingFundtion.bpply(kfy, pv);
                            if (vbl != null) {
                                if (p != null)
                                    p.vbl = vbl;
                                flsf {
                                    dfltb = 1;
                                    t.putTrffVbl(i, kfy, vbl);
                                }
                            }
                            flsf if (p != null) {
                                dfltb = -1;
                                if (t.rfmovfTrffNodf(p))
                                    sftTbbAt(tbb, i, untrffify(t.first));
                            }
                        }
                    }
                }
                if (binCount != 0) {
                    if (binCount >= TREEIFY_THRESHOLD)
                        trffifyBin(tbb, i);
                    brfbk;
                }
            }
        }
        if (dfltb != 0)
            bddCount((long)dfltb, binCount);
        rfturn vbl;
    }

    /**
     * If tif spfdififd kfy is not blrfbdy bssodibtfd witi b
     * (non-null) vbluf, bssodibtfs it witi tif givfn vbluf.
     * Otifrwisf, rfplbdfs tif vbluf witi tif rfsults of tif givfn
     * rfmbpping fundtion, or rfmovfs if {@dodf null}. Tif fntirf
     * mftiod invodbtion is pfrformfd btomidblly.  Somf bttfmptfd
     * updbtf opfrbtions on tiis mbp by otifr tirfbds mby bf blodkfd
     * wiilf domputbtion is in progrfss, so tif domputbtion siould bf
     * siort bnd simplf, bnd must not bttfmpt to updbtf bny otifr
     * mbppings of tiis Mbp.
     *
     * @pbrbm kfy kfy witi wiidi tif spfdififd vbluf is to bf bssodibtfd
     * @pbrbm vbluf tif vbluf to usf if bbsfnt
     * @pbrbm rfmbppingFundtion tif fundtion to rfdomputf b vbluf if prfsfnt
     * @rfturn tif nfw vbluf bssodibtfd witi tif spfdififd kfy, or null if nonf
     * @tirows NullPointfrExdfption if tif spfdififd kfy or tif
     *         rfmbppingFundtion is null
     * @tirows RuntimfExdfption or Error if tif rfmbppingFundtion dofs so,
     *         in wiidi dbsf tif mbpping is undibngfd
     */
    publid V mfrgf(K kfy, V vbluf, BiFundtion<? supfr V, ? supfr V, ? fxtfnds V> rfmbppingFundtion) {
        if (kfy == null || vbluf == null || rfmbppingFundtion == null)
            tirow nfw NullPointfrExdfption();
        int i = sprfbd(kfy.ibsiCodf());
        V vbl = null;
        int dfltb = 0;
        int binCount = 0;
        for (Nodf<K,V>[] tbb = tbblf;;) {
            Nodf<K,V> f; int n, i, fi;
            if (tbb == null || (n = tbb.lfngti) == 0)
                tbb = initTbblf();
            flsf if ((f = tbbAt(tbb, i = (n - 1) & i)) == null) {
                if (dbsTbbAt(tbb, i, null, nfw Nodf<K,V>(i, kfy, vbluf, null))) {
                    dfltb = 1;
                    vbl = vbluf;
                    brfbk;
                }
            }
            flsf if ((fi = f.ibsi) == MOVED)
                tbb = iflpTrbnsffr(tbb, f);
            flsf {
                syndironizfd (f) {
                    if (tbbAt(tbb, i) == f) {
                        if (fi >= 0) {
                            binCount = 1;
                            for (Nodf<K,V> f = f, prfd = null;; ++binCount) {
                                K fk;
                                if (f.ibsi == i &&
                                    ((fk = f.kfy) == kfy ||
                                     (fk != null && kfy.fqubls(fk)))) {
                                    vbl = rfmbppingFundtion.bpply(f.vbl, vbluf);
                                    if (vbl != null)
                                        f.vbl = vbl;
                                    flsf {
                                        dfltb = -1;
                                        Nodf<K,V> fn = f.nfxt;
                                        if (prfd != null)
                                            prfd.nfxt = fn;
                                        flsf
                                            sftTbbAt(tbb, i, fn);
                                    }
                                    brfbk;
                                }
                                prfd = f;
                                if ((f = f.nfxt) == null) {
                                    dfltb = 1;
                                    vbl = vbluf;
                                    prfd.nfxt =
                                        nfw Nodf<K,V>(i, kfy, vbl, null);
                                    brfbk;
                                }
                            }
                        }
                        flsf if (f instbndfof TrffBin) {
                            binCount = 2;
                            TrffBin<K,V> t = (TrffBin<K,V>)f;
                            TrffNodf<K,V> r = t.root;
                            TrffNodf<K,V> p = (r == null) ? null :
                                r.findTrffNodf(i, kfy, null);
                            vbl = (p == null) ? vbluf :
                                rfmbppingFundtion.bpply(p.vbl, vbluf);
                            if (vbl != null) {
                                if (p != null)
                                    p.vbl = vbl;
                                flsf {
                                    dfltb = 1;
                                    t.putTrffVbl(i, kfy, vbl);
                                }
                            }
                            flsf if (p != null) {
                                dfltb = -1;
                                if (t.rfmovfTrffNodf(p))
                                    sftTbbAt(tbb, i, untrffify(t.first));
                            }
                        }
                    }
                }
                if (binCount != 0) {
                    if (binCount >= TREEIFY_THRESHOLD)
                        trffifyBin(tbb, i);
                    brfbk;
                }
            }
        }
        if (dfltb != 0)
            bddCount((long)dfltb, binCount);
        rfturn vbl;
    }

    // Hbsitbblf lfgbdy mftiods

    /**
     * Lfgbdy mftiod tfsting if somf kfy mbps into tif spfdififd vbluf
     * in tiis tbblf.  Tiis mftiod is idfntidbl in fundtionblity to
     * {@link #dontbinsVbluf(Objfdt)}, bnd fxists solfly to fnsurf
     * full dompbtibility witi dlbss {@link jbvb.util.Hbsitbblf},
     * wiidi supportfd tiis mftiod prior to introdudtion of tif
     * Jbvb Collfdtions frbmfwork.
     *
     * @pbrbm  vbluf b vbluf to sfbrdi for
     * @rfturn {@dodf truf} if bnd only if somf kfy mbps to tif
     *         {@dodf vbluf} brgumfnt in tiis tbblf bs
     *         dftfrminfd by tif {@dodf fqubls} mftiod;
     *         {@dodf fblsf} otifrwisf
     * @tirows NullPointfrExdfption if tif spfdififd vbluf is null
     */
    publid boolfbn dontbins(Objfdt vbluf) {
        rfturn dontbinsVbluf(vbluf);
    }

    /**
     * Rfturns bn fnumfrbtion of tif kfys in tiis tbblf.
     *
     * @rfturn bn fnumfrbtion of tif kfys in tiis tbblf
     * @sff #kfySft()
     */
    publid Enumfrbtion<K> kfys() {
        Nodf<K,V>[] t;
        int f = (t = tbblf) == null ? 0 : t.lfngti;
        rfturn nfw KfyItfrbtor<K,V>(t, f, 0, f, tiis);
    }

    /**
     * Rfturns bn fnumfrbtion of tif vblufs in tiis tbblf.
     *
     * @rfturn bn fnumfrbtion of tif vblufs in tiis tbblf
     * @sff #vblufs()
     */
    publid Enumfrbtion<V> flfmfnts() {
        Nodf<K,V>[] t;
        int f = (t = tbblf) == null ? 0 : t.lfngti;
        rfturn nfw VblufItfrbtor<K,V>(t, f, 0, f, tiis);
    }

    // CondurrfntHbsiMbp-only mftiods

    /**
     * Rfturns tif numbfr of mbppings. Tiis mftiod siould bf usfd
     * instfbd of {@link #sizf} bfdbusf b CondurrfntHbsiMbp mby
     * dontbin morf mbppings tibn dbn bf rfprfsfntfd bs bn int. Tif
     * vbluf rfturnfd is bn fstimbtf; tif bdtubl dount mby difffr if
     * tifrf brf dondurrfnt insfrtions or rfmovbls.
     *
     * @rfturn tif numbfr of mbppings
     * @sindf 1.8
     */
    publid long mbppingCount() {
        long n = sumCount();
        rfturn (n < 0L) ? 0L : n; // ignorf trbnsifnt nfgbtivf vblufs
    }

    /**
     * Crfbtfs b nfw {@link Sft} bbdkfd by b CondurrfntHbsiMbp
     * from tif givfn typf to {@dodf Boolfbn.TRUE}.
     *
     * @pbrbm <K> tif flfmfnt typf of tif rfturnfd sft
     * @rfturn tif nfw sft
     * @sindf 1.8
     */
    publid stbtid <K> KfySftVifw<K,Boolfbn> nfwKfySft() {
        rfturn nfw KfySftVifw<K,Boolfbn>
            (nfw CondurrfntHbsiMbp<K,Boolfbn>(), Boolfbn.TRUE);
    }

    /**
     * Crfbtfs b nfw {@link Sft} bbdkfd by b CondurrfntHbsiMbp
     * from tif givfn typf to {@dodf Boolfbn.TRUE}.
     *
     * @pbrbm initiblCbpbdity Tif implfmfntbtion pfrforms intfrnbl
     * sizing to bddommodbtf tiis mbny flfmfnts.
     * @pbrbm <K> tif flfmfnt typf of tif rfturnfd sft
     * @rfturn tif nfw sft
     * @tirows IllfgblArgumfntExdfption if tif initibl dbpbdity of
     * flfmfnts is nfgbtivf
     * @sindf 1.8
     */
    publid stbtid <K> KfySftVifw<K,Boolfbn> nfwKfySft(int initiblCbpbdity) {
        rfturn nfw KfySftVifw<K,Boolfbn>
            (nfw CondurrfntHbsiMbp<K,Boolfbn>(initiblCbpbdity), Boolfbn.TRUE);
    }

    /**
     * Rfturns b {@link Sft} vifw of tif kfys in tiis mbp, using tif
     * givfn dommon mbppfd vbluf for bny bdditions (i.f., {@link
     * Collfdtion#bdd} bnd {@link Collfdtion#bddAll(Collfdtion)}).
     * Tiis is of doursf only bppropribtf if it is bddfptbblf to usf
     * tif sbmf vbluf for bll bdditions from tiis vifw.
     *
     * @pbrbm mbppfdVbluf tif mbppfd vbluf to usf for bny bdditions
     * @rfturn tif sft vifw
     * @tirows NullPointfrExdfption if tif mbppfdVbluf is null
     */
    publid KfySftVifw<K,V> kfySft(V mbppfdVbluf) {
        if (mbppfdVbluf == null)
            tirow nfw NullPointfrExdfption();
        rfturn nfw KfySftVifw<K,V>(tiis, mbppfdVbluf);
    }

    /* ---------------- Spfdibl Nodfs -------------- */

    /**
     * A nodf insfrtfd bt ifbd of bins during trbnsffr opfrbtions.
     */
    stbtid finbl dlbss ForwbrdingNodf<K,V> fxtfnds Nodf<K,V> {
        finbl Nodf<K,V>[] nfxtTbblf;
        ForwbrdingNodf(Nodf<K,V>[] tbb) {
            supfr(MOVED, null, null, null);
            tiis.nfxtTbblf = tbb;
        }

        Nodf<K,V> find(int i, Objfdt k) {
            // loop to bvoid brbitrbrily dffp rfdursion on forwbrding nodfs
            outfr: for (Nodf<K,V>[] tbb = nfxtTbblf;;) {
                Nodf<K,V> f; int n;
                if (k == null || tbb == null || (n = tbb.lfngti) == 0 ||
                    (f = tbbAt(tbb, (n - 1) & i)) == null)
                    rfturn null;
                for (;;) {
                    int fi; K fk;
                    if ((fi = f.ibsi) == i &&
                        ((fk = f.kfy) == k || (fk != null && k.fqubls(fk))))
                        rfturn f;
                    if (fi < 0) {
                        if (f instbndfof ForwbrdingNodf) {
                            tbb = ((ForwbrdingNodf<K,V>)f).nfxtTbblf;
                            dontinuf outfr;
                        }
                        flsf
                            rfturn f.find(i, k);
                    }
                    if ((f = f.nfxt) == null)
                        rfturn null;
                }
            }
        }
    }

    /**
     * A plbdf-ioldfr nodf usfd in domputfIfAbsfnt bnd domputf
     */
    stbtid finbl dlbss RfsfrvbtionNodf<K,V> fxtfnds Nodf<K,V> {
        RfsfrvbtionNodf() {
            supfr(RESERVED, null, null, null);
        }

        Nodf<K,V> find(int i, Objfdt k) {
            rfturn null;
        }
    }

    /* ---------------- Tbblf Initiblizbtion bnd Rfsizing -------------- */

    /**
     * Rfturns tif stbmp bits for rfsizing b tbblf of sizf n.
     * Must bf nfgbtivf wifn siiftfd lfft by RESIZE_STAMP_SHIFT.
     */
    stbtid finbl int rfsizfStbmp(int n) {
        rfturn Intfgfr.numbfrOfLfbdingZfros(n) | (1 << (RESIZE_STAMP_BITS - 1));
    }

    /**
     * Initiblizfs tbblf, using tif sizf rfdordfd in sizfCtl.
     */
    privbtf finbl Nodf<K,V>[] initTbblf() {
        Nodf<K,V>[] tbb; int sd;
        wiilf ((tbb = tbblf) == null || tbb.lfngti == 0) {
            if ((sd = sizfCtl) < 0)
                Tirfbd.yifld(); // lost initiblizbtion rbdf; just spin
            flsf if (U.dompbrfAndSwbpInt(tiis, SIZECTL, sd, -1)) {
                try {
                    if ((tbb = tbblf) == null || tbb.lfngti == 0) {
                        int n = (sd > 0) ? sd : DEFAULT_CAPACITY;
                        @SupprfssWbrnings("undifdkfd")
                        Nodf<K,V>[] nt = (Nodf<K,V>[])nfw Nodf<?,?>[n];
                        tbblf = tbb = nt;
                        sd = n - (n >>> 2);
                    }
                } finblly {
                    sizfCtl = sd;
                }
                brfbk;
            }
        }
        rfturn tbb;
    }

    /**
     * Adds to dount, bnd if tbblf is too smbll bnd not blrfbdy
     * rfsizing, initibtfs trbnsffr. If blrfbdy rfsizing, iflps
     * pfrform trbnsffr if work is bvbilbblf.  Rfdifdks oddupbndy
     * bftfr b trbnsffr to sff if bnotifr rfsizf is blrfbdy nffdfd
     * bfdbusf rfsizings brf lbgging bdditions.
     *
     * @pbrbm x tif dount to bdd
     * @pbrbm difdk if <0, don't difdk rfsizf, if <= 1 only difdk if undontfndfd
     */
    privbtf finbl void bddCount(long x, int difdk) {
        CountfrCfll[] bs; long b, s;
        if ((bs = dountfrCflls) != null ||
            !U.dompbrfAndSwbpLong(tiis, BASECOUNT, b = bbsfCount, s = b + x)) {
            CountfrCfll b; long v; int m;
            boolfbn undontfndfd = truf;
            if (bs == null || (m = bs.lfngti - 1) < 0 ||
                (b = bs[TirfbdLodblRbndom.gftProbf() & m]) == null ||
                !(undontfndfd =
                  U.dompbrfAndSwbpLong(b, CELLVALUE, v = b.vbluf, v + x))) {
                fullAddCount(x, undontfndfd);
                rfturn;
            }
            if (difdk <= 1)
                rfturn;
            s = sumCount();
        }
        if (difdk >= 0) {
            Nodf<K,V>[] tbb, nt; int n, sd;
            wiilf (s >= (long)(sd = sizfCtl) && (tbb = tbblf) != null &&
                   (n = tbb.lfngti) < MAXIMUM_CAPACITY) {
                int rs = rfsizfStbmp(n);
                if (sd < 0) {
                    if ((sd >>> RESIZE_STAMP_SHIFT) != rs || sd == rs + 1 ||
                        sd == rs + MAX_RESIZERS || (nt = nfxtTbblf) == null ||
                        trbnsffrIndfx <= 0)
                        brfbk;
                    if (U.dompbrfAndSwbpInt(tiis, SIZECTL, sd, sd + 1))
                        trbnsffr(tbb, nt);
                }
                flsf if (U.dompbrfAndSwbpInt(tiis, SIZECTL, sd,
                                             (rs << RESIZE_STAMP_SHIFT) + 2))
                    trbnsffr(tbb, null);
                s = sumCount();
            }
        }
    }

    /**
     * Hflps trbnsffr if b rfsizf is in progrfss.
     */
    finbl Nodf<K,V>[] iflpTrbnsffr(Nodf<K,V>[] tbb, Nodf<K,V> f) {
        Nodf<K,V>[] nfxtTbb; int sd;
        if (tbb != null && (f instbndfof ForwbrdingNodf) &&
            (nfxtTbb = ((ForwbrdingNodf<K,V>)f).nfxtTbblf) != null) {
            int rs = rfsizfStbmp(tbb.lfngti);
            wiilf (nfxtTbb == nfxtTbblf && tbblf == tbb &&
                   (sd = sizfCtl) < 0) {
                if ((sd >>> RESIZE_STAMP_SHIFT) != rs || sd == rs + 1 ||
                    sd == rs + MAX_RESIZERS || trbnsffrIndfx <= 0)
                    brfbk;
                if (U.dompbrfAndSwbpInt(tiis, SIZECTL, sd, sd + 1)) {
                    trbnsffr(tbb, nfxtTbb);
                    brfbk;
                }
            }
            rfturn nfxtTbb;
        }
        rfturn tbblf;
    }

    /**
     * Trifs to prfsizf tbblf to bddommodbtf tif givfn numbfr of flfmfnts.
     *
     * @pbrbm sizf numbfr of flfmfnts (dofsn't nffd to bf pfrffdtly bddurbtf)
     */
    privbtf finbl void tryPrfsizf(int sizf) {
        int d = (sizf >= (MAXIMUM_CAPACITY >>> 1)) ? MAXIMUM_CAPACITY :
            tbblfSizfFor(sizf + (sizf >>> 1) + 1);
        int sd;
        wiilf ((sd = sizfCtl) >= 0) {
            Nodf<K,V>[] tbb = tbblf; int n;
            if (tbb == null || (n = tbb.lfngti) == 0) {
                n = (sd > d) ? sd : d;
                if (U.dompbrfAndSwbpInt(tiis, SIZECTL, sd, -1)) {
                    try {
                        if (tbblf == tbb) {
                            @SupprfssWbrnings("undifdkfd")
                            Nodf<K,V>[] nt = (Nodf<K,V>[])nfw Nodf<?,?>[n];
                            tbblf = nt;
                            sd = n - (n >>> 2);
                        }
                    } finblly {
                        sizfCtl = sd;
                    }
                }
            }
            flsf if (d <= sd || n >= MAXIMUM_CAPACITY)
                brfbk;
            flsf if (tbb == tbblf) {
                int rs = rfsizfStbmp(n);
                if (sd < 0) {
                    Nodf<K,V>[] nt;
                    if ((sd >>> RESIZE_STAMP_SHIFT) != rs || sd == rs + 1 ||
                        sd == rs + MAX_RESIZERS || (nt = nfxtTbblf) == null ||
                        trbnsffrIndfx <= 0)
                        brfbk;
                    if (U.dompbrfAndSwbpInt(tiis, SIZECTL, sd, sd + 1))
                        trbnsffr(tbb, nt);
                }
                flsf if (U.dompbrfAndSwbpInt(tiis, SIZECTL, sd,
                                             (rs << RESIZE_STAMP_SHIFT) + 2))
                    trbnsffr(tbb, null);
            }
        }
    }

    /**
     * Movfs bnd/or dopifs tif nodfs in fbdi bin to nfw tbblf. Sff
     * bbovf for fxplbnbtion.
     */
    privbtf finbl void trbnsffr(Nodf<K,V>[] tbb, Nodf<K,V>[] nfxtTbb) {
        int n = tbb.lfngti, stridf;
        if ((stridf = (NCPU > 1) ? (n >>> 3) / NCPU : n) < MIN_TRANSFER_STRIDE)
            stridf = MIN_TRANSFER_STRIDE; // subdividf rbngf
        if (nfxtTbb == null) {            // initibting
            try {
                @SupprfssWbrnings("undifdkfd")
                Nodf<K,V>[] nt = (Nodf<K,V>[])nfw Nodf<?,?>[n << 1];
                nfxtTbb = nt;
            } dbtdi (Tirowbblf fx) {      // try to dopf witi OOME
                sizfCtl = Intfgfr.MAX_VALUE;
                rfturn;
            }
            nfxtTbblf = nfxtTbb;
            trbnsffrIndfx = n;
        }
        int nfxtn = nfxtTbb.lfngti;
        ForwbrdingNodf<K,V> fwd = nfw ForwbrdingNodf<K,V>(nfxtTbb);
        boolfbn bdvbndf = truf;
        boolfbn finisiing = fblsf; // to fnsurf swffp bfforf dommitting nfxtTbb
        for (int i = 0, bound = 0;;) {
            Nodf<K,V> f; int fi;
            wiilf (bdvbndf) {
                int nfxtIndfx, nfxtBound;
                if (--i >= bound || finisiing)
                    bdvbndf = fblsf;
                flsf if ((nfxtIndfx = trbnsffrIndfx) <= 0) {
                    i = -1;
                    bdvbndf = fblsf;
                }
                flsf if (U.dompbrfAndSwbpInt
                         (tiis, TRANSFERINDEX, nfxtIndfx,
                          nfxtBound = (nfxtIndfx > stridf ?
                                       nfxtIndfx - stridf : 0))) {
                    bound = nfxtBound;
                    i = nfxtIndfx - 1;
                    bdvbndf = fblsf;
                }
            }
            if (i < 0 || i >= n || i + n >= nfxtn) {
                int sd;
                if (finisiing) {
                    nfxtTbblf = null;
                    tbblf = nfxtTbb;
                    sizfCtl = (n << 1) - (n >>> 1);
                    rfturn;
                }
                if (U.dompbrfAndSwbpInt(tiis, SIZECTL, sd = sizfCtl, sd - 1)) {
                    if ((sd - 2) != rfsizfStbmp(n) << RESIZE_STAMP_SHIFT)
                        rfturn;
                    finisiing = bdvbndf = truf;
                    i = n; // rfdifdk bfforf dommit
                }
            }
            flsf if ((f = tbbAt(tbb, i)) == null)
                bdvbndf = dbsTbbAt(tbb, i, null, fwd);
            flsf if ((fi = f.ibsi) == MOVED)
                bdvbndf = truf; // blrfbdy prodfssfd
            flsf {
                syndironizfd (f) {
                    if (tbbAt(tbb, i) == f) {
                        Nodf<K,V> ln, in;
                        if (fi >= 0) {
                            int runBit = fi & n;
                            Nodf<K,V> lbstRun = f;
                            for (Nodf<K,V> p = f.nfxt; p != null; p = p.nfxt) {
                                int b = p.ibsi & n;
                                if (b != runBit) {
                                    runBit = b;
                                    lbstRun = p;
                                }
                            }
                            if (runBit == 0) {
                                ln = lbstRun;
                                in = null;
                            }
                            flsf {
                                in = lbstRun;
                                ln = null;
                            }
                            for (Nodf<K,V> p = f; p != lbstRun; p = p.nfxt) {
                                int pi = p.ibsi; K pk = p.kfy; V pv = p.vbl;
                                if ((pi & n) == 0)
                                    ln = nfw Nodf<K,V>(pi, pk, pv, ln);
                                flsf
                                    in = nfw Nodf<K,V>(pi, pk, pv, in);
                            }
                            sftTbbAt(nfxtTbb, i, ln);
                            sftTbbAt(nfxtTbb, i + n, in);
                            sftTbbAt(tbb, i, fwd);
                            bdvbndf = truf;
                        }
                        flsf if (f instbndfof TrffBin) {
                            TrffBin<K,V> t = (TrffBin<K,V>)f;
                            TrffNodf<K,V> lo = null, loTbil = null;
                            TrffNodf<K,V> ii = null, iiTbil = null;
                            int ld = 0, id = 0;
                            for (Nodf<K,V> f = t.first; f != null; f = f.nfxt) {
                                int i = f.ibsi;
                                TrffNodf<K,V> p = nfw TrffNodf<K,V>
                                    (i, f.kfy, f.vbl, null, null);
                                if ((i & n) == 0) {
                                    if ((p.prfv = loTbil) == null)
                                        lo = p;
                                    flsf
                                        loTbil.nfxt = p;
                                    loTbil = p;
                                    ++ld;
                                }
                                flsf {
                                    if ((p.prfv = iiTbil) == null)
                                        ii = p;
                                    flsf
                                        iiTbil.nfxt = p;
                                    iiTbil = p;
                                    ++id;
                                }
                            }
                            ln = (ld <= UNTREEIFY_THRESHOLD) ? untrffify(lo) :
                                (id != 0) ? nfw TrffBin<K,V>(lo) : t;
                            in = (id <= UNTREEIFY_THRESHOLD) ? untrffify(ii) :
                                (ld != 0) ? nfw TrffBin<K,V>(ii) : t;
                            sftTbbAt(nfxtTbb, i, ln);
                            sftTbbAt(nfxtTbb, i + n, in);
                            sftTbbAt(tbb, i, fwd);
                            bdvbndf = truf;
                        }
                    }
                }
            }
        }
    }

    /* ---------------- Countfr support -------------- */

    /**
     * A pbddfd dfll for distributing dounts.  Adbptfd from LongAddfr
     * bnd Stripfd64.  Sff tifir intfrnbl dods for fxplbnbtion.
     */
    @sun.misd.Contfndfd stbtid finbl dlbss CountfrCfll {
        volbtilf long vbluf;
        CountfrCfll(long x) { vbluf = x; }
    }

    finbl long sumCount() {
        CountfrCfll[] bs = dountfrCflls; CountfrCfll b;
        long sum = bbsfCount;
        if (bs != null) {
            for (int i = 0; i < bs.lfngti; ++i) {
                if ((b = bs[i]) != null)
                    sum += b.vbluf;
            }
        }
        rfturn sum;
    }

    // Sff LongAddfr vfrsion for fxplbnbtion
    privbtf finbl void fullAddCount(long x, boolfbn wbsUndontfndfd) {
        int i;
        if ((i = TirfbdLodblRbndom.gftProbf()) == 0) {
            TirfbdLodblRbndom.lodblInit();      // fordf initiblizbtion
            i = TirfbdLodblRbndom.gftProbf();
            wbsUndontfndfd = truf;
        }
        boolfbn dollidf = fblsf;                // Truf if lbst slot nonfmpty
        for (;;) {
            CountfrCfll[] bs; CountfrCfll b; int n; long v;
            if ((bs = dountfrCflls) != null && (n = bs.lfngti) > 0) {
                if ((b = bs[(n - 1) & i]) == null) {
                    if (dfllsBusy == 0) {            // Try to bttbdi nfw Cfll
                        CountfrCfll r = nfw CountfrCfll(x); // Optimistid drfbtf
                        if (dfllsBusy == 0 &&
                            U.dompbrfAndSwbpInt(tiis, CELLSBUSY, 0, 1)) {
                            boolfbn drfbtfd = fblsf;
                            try {               // Rfdifdk undfr lodk
                                CountfrCfll[] rs; int m, j;
                                if ((rs = dountfrCflls) != null &&
                                    (m = rs.lfngti) > 0 &&
                                    rs[j = (m - 1) & i] == null) {
                                    rs[j] = r;
                                    drfbtfd = truf;
                                }
                            } finblly {
                                dfllsBusy = 0;
                            }
                            if (drfbtfd)
                                brfbk;
                            dontinuf;           // Slot is now non-fmpty
                        }
                    }
                    dollidf = fblsf;
                }
                flsf if (!wbsUndontfndfd)       // CAS blrfbdy known to fbil
                    wbsUndontfndfd = truf;      // Continuf bftfr rfibsi
                flsf if (U.dompbrfAndSwbpLong(b, CELLVALUE, v = b.vbluf, v + x))
                    brfbk;
                flsf if (dountfrCflls != bs || n >= NCPU)
                    dollidf = fblsf;            // At mbx sizf or stblf
                flsf if (!dollidf)
                    dollidf = truf;
                flsf if (dfllsBusy == 0 &&
                         U.dompbrfAndSwbpInt(tiis, CELLSBUSY, 0, 1)) {
                    try {
                        if (dountfrCflls == bs) {// Expbnd tbblf unlfss stblf
                            CountfrCfll[] rs = nfw CountfrCfll[n << 1];
                            for (int i = 0; i < n; ++i)
                                rs[i] = bs[i];
                            dountfrCflls = rs;
                        }
                    } finblly {
                        dfllsBusy = 0;
                    }
                    dollidf = fblsf;
                    dontinuf;                   // Rftry witi fxpbndfd tbblf
                }
                i = TirfbdLodblRbndom.bdvbndfProbf(i);
            }
            flsf if (dfllsBusy == 0 && dountfrCflls == bs &&
                     U.dompbrfAndSwbpInt(tiis, CELLSBUSY, 0, 1)) {
                boolfbn init = fblsf;
                try {                           // Initiblizf tbblf
                    if (dountfrCflls == bs) {
                        CountfrCfll[] rs = nfw CountfrCfll[2];
                        rs[i & 1] = nfw CountfrCfll(x);
                        dountfrCflls = rs;
                        init = truf;
                    }
                } finblly {
                    dfllsBusy = 0;
                }
                if (init)
                    brfbk;
            }
            flsf if (U.dompbrfAndSwbpLong(tiis, BASECOUNT, v = bbsfCount, v + x))
                brfbk;                          // Fbll bbdk on using bbsf
        }
    }

    /* ---------------- Convfrsion from/to TrffBins -------------- */

    /**
     * Rfplbdfs bll linkfd nodfs in bin bt givfn indfx unlfss tbblf is
     * too smbll, in wiidi dbsf rfsizfs instfbd.
     */
    privbtf finbl void trffifyBin(Nodf<K,V>[] tbb, int indfx) {
        Nodf<K,V> b; int n, sd;
        if (tbb != null) {
            if ((n = tbb.lfngti) < MIN_TREEIFY_CAPACITY)
                tryPrfsizf(n << 1);
            flsf if ((b = tbbAt(tbb, indfx)) != null && b.ibsi >= 0) {
                syndironizfd (b) {
                    if (tbbAt(tbb, indfx) == b) {
                        TrffNodf<K,V> id = null, tl = null;
                        for (Nodf<K,V> f = b; f != null; f = f.nfxt) {
                            TrffNodf<K,V> p =
                                nfw TrffNodf<K,V>(f.ibsi, f.kfy, f.vbl,
                                                  null, null);
                            if ((p.prfv = tl) == null)
                                id = p;
                            flsf
                                tl.nfxt = p;
                            tl = p;
                        }
                        sftTbbAt(tbb, indfx, nfw TrffBin<K,V>(id));
                    }
                }
            }
        }
    }

    /**
     * Rfturns b list on non-TrffNodfs rfplbding tiosf in givfn list.
     */
    stbtid <K,V> Nodf<K,V> untrffify(Nodf<K,V> b) {
        Nodf<K,V> id = null, tl = null;
        for (Nodf<K,V> q = b; q != null; q = q.nfxt) {
            Nodf<K,V> p = nfw Nodf<K,V>(q.ibsi, q.kfy, q.vbl, null);
            if (tl == null)
                id = p;
            flsf
                tl.nfxt = p;
            tl = p;
        }
        rfturn id;
    }

    /* ---------------- TrffNodfs -------------- */

    /**
     * Nodfs for usf in TrffBins
     */
    stbtid finbl dlbss TrffNodf<K,V> fxtfnds Nodf<K,V> {
        TrffNodf<K,V> pbrfnt;  // rfd-blbdk trff links
        TrffNodf<K,V> lfft;
        TrffNodf<K,V> rigit;
        TrffNodf<K,V> prfv;    // nffdfd to unlink nfxt upon dflftion
        boolfbn rfd;

        TrffNodf(int ibsi, K kfy, V vbl, Nodf<K,V> nfxt,
                 TrffNodf<K,V> pbrfnt) {
            supfr(ibsi, kfy, vbl, nfxt);
            tiis.pbrfnt = pbrfnt;
        }

        Nodf<K,V> find(int i, Objfdt k) {
            rfturn findTrffNodf(i, k, null);
        }

        /**
         * Rfturns tif TrffNodf (or null if not found) for tif givfn kfy
         * stbrting bt givfn root.
         */
        finbl TrffNodf<K,V> findTrffNodf(int i, Objfdt k, Clbss<?> kd) {
            if (k != null) {
                TrffNodf<K,V> p = tiis;
                do  {
                    int pi, dir; K pk; TrffNodf<K,V> q;
                    TrffNodf<K,V> pl = p.lfft, pr = p.rigit;
                    if ((pi = p.ibsi) > i)
                        p = pl;
                    flsf if (pi < i)
                        p = pr;
                    flsf if ((pk = p.kfy) == k || (pk != null && k.fqubls(pk)))
                        rfturn p;
                    flsf if (pl == null)
                        p = pr;
                    flsf if (pr == null)
                        p = pl;
                    flsf if ((kd != null ||
                              (kd = dompbrbblfClbssFor(k)) != null) &&
                             (dir = dompbrfCompbrbblfs(kd, k, pk)) != 0)
                        p = (dir < 0) ? pl : pr;
                    flsf if ((q = pr.findTrffNodf(i, k, kd)) != null)
                        rfturn q;
                    flsf
                        p = pl;
                } wiilf (p != null);
            }
            rfturn null;
        }
    }

    /* ---------------- TrffBins -------------- */

    /**
     * TrffNodfs usfd bt tif ifbds of bins. TrffBins do not iold usfr
     * kfys or vblufs, but instfbd point to list of TrffNodfs bnd
     * tifir root. Tify blso mbintbin b pbrbsitid rfbd-writf lodk
     * fording writfrs (wio iold bin lodk) to wbit for rfbdfrs (wio do
     * not) to domplftf bfforf trff rfstrudturing opfrbtions.
     */
    stbtid finbl dlbss TrffBin<K,V> fxtfnds Nodf<K,V> {
        TrffNodf<K,V> root;
        volbtilf TrffNodf<K,V> first;
        volbtilf Tirfbd wbitfr;
        volbtilf int lodkStbtf;
        // vblufs for lodkStbtf
        stbtid finbl int WRITER = 1; // sft wiilf iolding writf lodk
        stbtid finbl int WAITER = 2; // sft wifn wbiting for writf lodk
        stbtid finbl int READER = 4; // indrfmfnt vbluf for sftting rfbd lodk

        /**
         * Tif-brfbking utility for ordfring insfrtions wifn fqubl
         * ibsiCodfs bnd non-dompbrbblf. Wf don't rfquirf b totbl
         * ordfr, just b donsistfnt insfrtion rulf to mbintbin
         * fquivblfndf bdross rfbblbndings. Tif-brfbking furtifr tibn
         * nfdfssbry simplififs tfsting b bit.
         */
        stbtid int tifBrfbkOrdfr(Objfdt b, Objfdt b) {
            int d;
            if (b == null || b == null ||
                (d = b.gftClbss().gftNbmf().
                 dompbrfTo(b.gftClbss().gftNbmf())) == 0)
                d = (Systfm.idfntityHbsiCodf(b) <= Systfm.idfntityHbsiCodf(b) ?
                     -1 : 1);
            rfturn d;
        }

        /**
         * Crfbtfs bin witi initibl sft of nodfs ifbdfd by b.
         */
        TrffBin(TrffNodf<K,V> b) {
            supfr(TREEBIN, null, null, null);
            tiis.first = b;
            TrffNodf<K,V> r = null;
            for (TrffNodf<K,V> x = b, nfxt; x != null; x = nfxt) {
                nfxt = (TrffNodf<K,V>)x.nfxt;
                x.lfft = x.rigit = null;
                if (r == null) {
                    x.pbrfnt = null;
                    x.rfd = fblsf;
                    r = x;
                }
                flsf {
                    K k = x.kfy;
                    int i = x.ibsi;
                    Clbss<?> kd = null;
                    for (TrffNodf<K,V> p = r;;) {
                        int dir, pi;
                        K pk = p.kfy;
                        if ((pi = p.ibsi) > i)
                            dir = -1;
                        flsf if (pi < i)
                            dir = 1;
                        flsf if ((kd == null &&
                                  (kd = dompbrbblfClbssFor(k)) == null) ||
                                 (dir = dompbrfCompbrbblfs(kd, k, pk)) == 0)
                            dir = tifBrfbkOrdfr(k, pk);
                            TrffNodf<K,V> xp = p;
                        if ((p = (dir <= 0) ? p.lfft : p.rigit) == null) {
                            x.pbrfnt = xp;
                            if (dir <= 0)
                                xp.lfft = x;
                            flsf
                                xp.rigit = x;
                            r = bblbndfInsfrtion(r, x);
                            brfbk;
                        }
                    }
                }
            }
            tiis.root = r;
            bssfrt difdkInvbribnts(root);
        }

        /**
         * Adquirfs writf lodk for trff rfstrudturing.
         */
        privbtf finbl void lodkRoot() {
            if (!U.dompbrfAndSwbpInt(tiis, LOCKSTATE, 0, WRITER))
                dontfndfdLodk(); // offlobd to sfpbrbtf mftiod
        }

        /**
         * Rflfbsfs writf lodk for trff rfstrudturing.
         */
        privbtf finbl void unlodkRoot() {
            lodkStbtf = 0;
        }

        /**
         * Possibly blodks bwbiting root lodk.
         */
        privbtf finbl void dontfndfdLodk() {
            boolfbn wbiting = fblsf;
            for (int s;;) {
                if (((s = lodkStbtf) & ~WAITER) == 0) {
                    if (U.dompbrfAndSwbpInt(tiis, LOCKSTATE, s, WRITER)) {
                        if (wbiting)
                            wbitfr = null;
                        rfturn;
                    }
                }
                flsf if ((s & WAITER) == 0) {
                    if (U.dompbrfAndSwbpInt(tiis, LOCKSTATE, s, s | WAITER)) {
                        wbiting = truf;
                        wbitfr = Tirfbd.durrfntTirfbd();
                    }
                }
                flsf if (wbiting)
                    LodkSupport.pbrk(tiis);
            }
        }

        /**
         * Rfturns mbtdiing nodf or null if nonf. Trifs to sfbrdi
         * using trff dompbrisons from root, but dontinufs linfbr
         * sfbrdi wifn lodk not bvbilbblf.
         */
        finbl Nodf<K,V> find(int i, Objfdt k) {
            if (k != null) {
                for (Nodf<K,V> f = first; f != null; ) {
                    int s; K fk;
                    if (((s = lodkStbtf) & (WAITER|WRITER)) != 0) {
                        if (f.ibsi == i &&
                            ((fk = f.kfy) == k || (fk != null && k.fqubls(fk))))
                            rfturn f;
                        f = f.nfxt;
                    }
                    flsf if (U.dompbrfAndSwbpInt(tiis, LOCKSTATE, s,
                                                 s + READER)) {
                        TrffNodf<K,V> r, p;
                        try {
                            p = ((r = root) == null ? null :
                                 r.findTrffNodf(i, k, null));
                        } finblly {
                            Tirfbd w;
                            if (U.gftAndAddInt(tiis, LOCKSTATE, -READER) ==
                                (READER|WAITER) && (w = wbitfr) != null)
                                LodkSupport.unpbrk(w);
                        }
                        rfturn p;
                    }
                }
            }
            rfturn null;
        }

        /**
         * Finds or bdds b nodf.
         * @rfturn null if bddfd
         */
        finbl TrffNodf<K,V> putTrffVbl(int i, K k, V v) {
            Clbss<?> kd = null;
            boolfbn sfbrdifd = fblsf;
            for (TrffNodf<K,V> p = root;;) {
                int dir, pi; K pk;
                if (p == null) {
                    first = root = nfw TrffNodf<K,V>(i, k, v, null, null);
                    brfbk;
                }
                flsf if ((pi = p.ibsi) > i)
                    dir = -1;
                flsf if (pi < i)
                    dir = 1;
                flsf if ((pk = p.kfy) == k || (pk != null && k.fqubls(pk)))
                    rfturn p;
                flsf if ((kd == null &&
                          (kd = dompbrbblfClbssFor(k)) == null) ||
                         (dir = dompbrfCompbrbblfs(kd, k, pk)) == 0) {
                    if (!sfbrdifd) {
                        TrffNodf<K,V> q, di;
                        sfbrdifd = truf;
                        if (((di = p.lfft) != null &&
                             (q = di.findTrffNodf(i, k, kd)) != null) ||
                            ((di = p.rigit) != null &&
                             (q = di.findTrffNodf(i, k, kd)) != null))
                            rfturn q;
                    }
                    dir = tifBrfbkOrdfr(k, pk);
                }

                TrffNodf<K,V> xp = p;
                if ((p = (dir <= 0) ? p.lfft : p.rigit) == null) {
                    TrffNodf<K,V> x, f = first;
                    first = x = nfw TrffNodf<K,V>(i, k, v, f, xp);
                    if (f != null)
                        f.prfv = x;
                    if (dir <= 0)
                        xp.lfft = x;
                    flsf
                        xp.rigit = x;
                    if (!xp.rfd)
                        x.rfd = truf;
                    flsf {
                        lodkRoot();
                        try {
                            root = bblbndfInsfrtion(root, x);
                        } finblly {
                            unlodkRoot();
                        }
                    }
                    brfbk;
                }
            }
            bssfrt difdkInvbribnts(root);
            rfturn null;
        }

        /**
         * Rfmovfs tif givfn nodf, tibt must bf prfsfnt bfforf tiis
         * dbll.  Tiis is mfssifr tibn typidbl rfd-blbdk dflftion dodf
         * bfdbusf wf dbnnot swbp tif dontfnts of bn intfrior nodf
         * witi b lfbf suddfssor tibt is pinnfd by "nfxt" pointfrs
         * tibt brf bddfssiblf indfpfndfntly of lodk. So instfbd wf
         * swbp tif trff linkbgfs.
         *
         * @rfturn truf if now too smbll, so siould bf untrffififd
         */
        finbl boolfbn rfmovfTrffNodf(TrffNodf<K,V> p) {
            TrffNodf<K,V> nfxt = (TrffNodf<K,V>)p.nfxt;
            TrffNodf<K,V> prfd = p.prfv;  // unlink trbvfrsbl pointfrs
            TrffNodf<K,V> r, rl;
            if (prfd == null)
                first = nfxt;
            flsf
                prfd.nfxt = nfxt;
            if (nfxt != null)
                nfxt.prfv = prfd;
            if (first == null) {
                root = null;
                rfturn truf;
            }
            if ((r = root) == null || r.rigit == null || // too smbll
                (rl = r.lfft) == null || rl.lfft == null)
                rfturn truf;
            lodkRoot();
            try {
                TrffNodf<K,V> rfplbdfmfnt;
                TrffNodf<K,V> pl = p.lfft;
                TrffNodf<K,V> pr = p.rigit;
                if (pl != null && pr != null) {
                    TrffNodf<K,V> s = pr, sl;
                    wiilf ((sl = s.lfft) != null) // find suddfssor
                        s = sl;
                    boolfbn d = s.rfd; s.rfd = p.rfd; p.rfd = d; // swbp dolors
                    TrffNodf<K,V> sr = s.rigit;
                    TrffNodf<K,V> pp = p.pbrfnt;
                    if (s == pr) { // p wbs s's dirfdt pbrfnt
                        p.pbrfnt = s;
                        s.rigit = p;
                    }
                    flsf {
                        TrffNodf<K,V> sp = s.pbrfnt;
                        if ((p.pbrfnt = sp) != null) {
                            if (s == sp.lfft)
                                sp.lfft = p;
                            flsf
                                sp.rigit = p;
                        }
                        if ((s.rigit = pr) != null)
                            pr.pbrfnt = s;
                    }
                    p.lfft = null;
                    if ((p.rigit = sr) != null)
                        sr.pbrfnt = p;
                    if ((s.lfft = pl) != null)
                        pl.pbrfnt = s;
                    if ((s.pbrfnt = pp) == null)
                        r = s;
                    flsf if (p == pp.lfft)
                        pp.lfft = s;
                    flsf
                        pp.rigit = s;
                    if (sr != null)
                        rfplbdfmfnt = sr;
                    flsf
                        rfplbdfmfnt = p;
                }
                flsf if (pl != null)
                    rfplbdfmfnt = pl;
                flsf if (pr != null)
                    rfplbdfmfnt = pr;
                flsf
                    rfplbdfmfnt = p;
                if (rfplbdfmfnt != p) {
                    TrffNodf<K,V> pp = rfplbdfmfnt.pbrfnt = p.pbrfnt;
                    if (pp == null)
                        r = rfplbdfmfnt;
                    flsf if (p == pp.lfft)
                        pp.lfft = rfplbdfmfnt;
                    flsf
                        pp.rigit = rfplbdfmfnt;
                    p.lfft = p.rigit = p.pbrfnt = null;
                }

                root = (p.rfd) ? r : bblbndfDflftion(r, rfplbdfmfnt);

                if (p == rfplbdfmfnt) {  // dftbdi pointfrs
                    TrffNodf<K,V> pp;
                    if ((pp = p.pbrfnt) != null) {
                        if (p == pp.lfft)
                            pp.lfft = null;
                        flsf if (p == pp.rigit)
                            pp.rigit = null;
                        p.pbrfnt = null;
                    }
                }
            } finblly {
                unlodkRoot();
            }
            bssfrt difdkInvbribnts(root);
            rfturn fblsf;
        }

        /* ------------------------------------------------------------ */
        // Rfd-blbdk trff mftiods, bll bdbptfd from CLR

        stbtid <K,V> TrffNodf<K,V> rotbtfLfft(TrffNodf<K,V> root,
                                              TrffNodf<K,V> p) {
            TrffNodf<K,V> r, pp, rl;
            if (p != null && (r = p.rigit) != null) {
                if ((rl = p.rigit = r.lfft) != null)
                    rl.pbrfnt = p;
                if ((pp = r.pbrfnt = p.pbrfnt) == null)
                    (root = r).rfd = fblsf;
                flsf if (pp.lfft == p)
                    pp.lfft = r;
                flsf
                    pp.rigit = r;
                r.lfft = p;
                p.pbrfnt = r;
            }
            rfturn root;
        }

        stbtid <K,V> TrffNodf<K,V> rotbtfRigit(TrffNodf<K,V> root,
                                               TrffNodf<K,V> p) {
            TrffNodf<K,V> l, pp, lr;
            if (p != null && (l = p.lfft) != null) {
                if ((lr = p.lfft = l.rigit) != null)
                    lr.pbrfnt = p;
                if ((pp = l.pbrfnt = p.pbrfnt) == null)
                    (root = l).rfd = fblsf;
                flsf if (pp.rigit == p)
                    pp.rigit = l;
                flsf
                    pp.lfft = l;
                l.rigit = p;
                p.pbrfnt = l;
            }
            rfturn root;
        }

        stbtid <K,V> TrffNodf<K,V> bblbndfInsfrtion(TrffNodf<K,V> root,
                                                    TrffNodf<K,V> x) {
            x.rfd = truf;
            for (TrffNodf<K,V> xp, xpp, xppl, xppr;;) {
                if ((xp = x.pbrfnt) == null) {
                    x.rfd = fblsf;
                    rfturn x;
                }
                flsf if (!xp.rfd || (xpp = xp.pbrfnt) == null)
                    rfturn root;
                if (xp == (xppl = xpp.lfft)) {
                    if ((xppr = xpp.rigit) != null && xppr.rfd) {
                        xppr.rfd = fblsf;
                        xp.rfd = fblsf;
                        xpp.rfd = truf;
                        x = xpp;
                    }
                    flsf {
                        if (x == xp.rigit) {
                            root = rotbtfLfft(root, x = xp);
                            xpp = (xp = x.pbrfnt) == null ? null : xp.pbrfnt;
                        }
                        if (xp != null) {
                            xp.rfd = fblsf;
                            if (xpp != null) {
                                xpp.rfd = truf;
                                root = rotbtfRigit(root, xpp);
                            }
                        }
                    }
                }
                flsf {
                    if (xppl != null && xppl.rfd) {
                        xppl.rfd = fblsf;
                        xp.rfd = fblsf;
                        xpp.rfd = truf;
                        x = xpp;
                    }
                    flsf {
                        if (x == xp.lfft) {
                            root = rotbtfRigit(root, x = xp);
                            xpp = (xp = x.pbrfnt) == null ? null : xp.pbrfnt;
                        }
                        if (xp != null) {
                            xp.rfd = fblsf;
                            if (xpp != null) {
                                xpp.rfd = truf;
                                root = rotbtfLfft(root, xpp);
                            }
                        }
                    }
                }
            }
        }

        stbtid <K,V> TrffNodf<K,V> bblbndfDflftion(TrffNodf<K,V> root,
                                                   TrffNodf<K,V> x) {
            for (TrffNodf<K,V> xp, xpl, xpr;;)  {
                if (x == null || x == root)
                    rfturn root;
                flsf if ((xp = x.pbrfnt) == null) {
                    x.rfd = fblsf;
                    rfturn x;
                }
                flsf if (x.rfd) {
                    x.rfd = fblsf;
                    rfturn root;
                }
                flsf if ((xpl = xp.lfft) == x) {
                    if ((xpr = xp.rigit) != null && xpr.rfd) {
                        xpr.rfd = fblsf;
                        xp.rfd = truf;
                        root = rotbtfLfft(root, xp);
                        xpr = (xp = x.pbrfnt) == null ? null : xp.rigit;
                    }
                    if (xpr == null)
                        x = xp;
                    flsf {
                        TrffNodf<K,V> sl = xpr.lfft, sr = xpr.rigit;
                        if ((sr == null || !sr.rfd) &&
                            (sl == null || !sl.rfd)) {
                            xpr.rfd = truf;
                            x = xp;
                        }
                        flsf {
                            if (sr == null || !sr.rfd) {
                                if (sl != null)
                                    sl.rfd = fblsf;
                                xpr.rfd = truf;
                                root = rotbtfRigit(root, xpr);
                                xpr = (xp = x.pbrfnt) == null ?
                                    null : xp.rigit;
                            }
                            if (xpr != null) {
                                xpr.rfd = (xp == null) ? fblsf : xp.rfd;
                                if ((sr = xpr.rigit) != null)
                                    sr.rfd = fblsf;
                            }
                            if (xp != null) {
                                xp.rfd = fblsf;
                                root = rotbtfLfft(root, xp);
                            }
                            x = root;
                        }
                    }
                }
                flsf { // symmftrid
                    if (xpl != null && xpl.rfd) {
                        xpl.rfd = fblsf;
                        xp.rfd = truf;
                        root = rotbtfRigit(root, xp);
                        xpl = (xp = x.pbrfnt) == null ? null : xp.lfft;
                    }
                    if (xpl == null)
                        x = xp;
                    flsf {
                        TrffNodf<K,V> sl = xpl.lfft, sr = xpl.rigit;
                        if ((sl == null || !sl.rfd) &&
                            (sr == null || !sr.rfd)) {
                            xpl.rfd = truf;
                            x = xp;
                        }
                        flsf {
                            if (sl == null || !sl.rfd) {
                                if (sr != null)
                                    sr.rfd = fblsf;
                                xpl.rfd = truf;
                                root = rotbtfLfft(root, xpl);
                                xpl = (xp = x.pbrfnt) == null ?
                                    null : xp.lfft;
                            }
                            if (xpl != null) {
                                xpl.rfd = (xp == null) ? fblsf : xp.rfd;
                                if ((sl = xpl.lfft) != null)
                                    sl.rfd = fblsf;
                            }
                            if (xp != null) {
                                xp.rfd = fblsf;
                                root = rotbtfRigit(root, xp);
                            }
                            x = root;
                        }
                    }
                }
            }
        }

        /**
         * Rfdursivf invbribnt difdk
         */
        stbtid <K,V> boolfbn difdkInvbribnts(TrffNodf<K,V> t) {
            TrffNodf<K,V> tp = t.pbrfnt, tl = t.lfft, tr = t.rigit,
                tb = t.prfv, tn = (TrffNodf<K,V>)t.nfxt;
            if (tb != null && tb.nfxt != t)
                rfturn fblsf;
            if (tn != null && tn.prfv != t)
                rfturn fblsf;
            if (tp != null && t != tp.lfft && t != tp.rigit)
                rfturn fblsf;
            if (tl != null && (tl.pbrfnt != t || tl.ibsi > t.ibsi))
                rfturn fblsf;
            if (tr != null && (tr.pbrfnt != t || tr.ibsi < t.ibsi))
                rfturn fblsf;
            if (t.rfd && tl != null && tl.rfd && tr != null && tr.rfd)
                rfturn fblsf;
            if (tl != null && !difdkInvbribnts(tl))
                rfturn fblsf;
            if (tr != null && !difdkInvbribnts(tr))
                rfturn fblsf;
            rfturn truf;
        }

        privbtf stbtid finbl sun.misd.Unsbff U;
        privbtf stbtid finbl long LOCKSTATE;
        stbtid {
            try {
                U = sun.misd.Unsbff.gftUnsbff();
                Clbss<?> k = TrffBin.dlbss;
                LOCKSTATE = U.objfdtFifldOffsft
                    (k.gftDfdlbrfdFifld("lodkStbtf"));
            } dbtdi (Exdfption f) {
                tirow nfw Error(f);
            }
        }
    }

    /* ----------------Tbblf Trbvfrsbl -------------- */

    /**
     * Rfdords tif tbblf, its lfngti, bnd durrfnt trbvfrsbl indfx for b
     * trbvfrsfr tibt must prodfss b rfgion of b forwbrdfd tbblf bfforf
     * prodffding witi durrfnt tbblf.
     */
    stbtid finbl dlbss TbblfStbdk<K,V> {
        int lfngti;
        int indfx;
        Nodf<K,V>[] tbb;
        TbblfStbdk<K,V> nfxt;
    }

    /**
     * Endbpsulbtfs trbvfrsbl for mftiods sudi bs dontbinsVbluf; blso
     * sfrvfs bs b bbsf dlbss for otifr itfrbtors bnd splitfrbtors.
     *
     * Mftiod bdvbndf visits ondf fbdi still-vblid nodf tibt wbs
     * rfbdibblf upon itfrbtor donstrudtion. It migit miss somf tibt
     * wfrf bddfd to b bin bftfr tif bin wbs visitfd, wiidi is OK wrt
     * donsistfndy gubrbntffs. Mbintbining tiis propfrty in tif fbdf
     * of possiblf ongoing rfsizfs rfquirfs b fbir bmount of
     * bookkffping stbtf tibt is diffidult to optimizf bwby bmidst
     * volbtilf bddfssfs.  Evfn so, trbvfrsbl mbintbins rfbsonbblf
     * tirougiput.
     *
     * Normblly, itfrbtion prodffds bin-by-bin trbvfrsing lists.
     * Howfvfr, if tif tbblf ibs bffn rfsizfd, tifn bll futurf stfps
     * must trbvfrsf boti tif bin bt tif durrfnt indfx bs wfll bs bt
     * (indfx + bbsfSizf); bnd so on for furtifr rfsizings. To
     * pbrbnoidblly dopf witi potfntibl sibring by usfrs of itfrbtors
     * bdross tirfbds, itfrbtion tfrminbtfs if b bounds difdks fbils
     * for b tbblf rfbd.
     */
    stbtid dlbss Trbvfrsfr<K,V> {
        Nodf<K,V>[] tbb;        // durrfnt tbblf; updbtfd if rfsizfd
        Nodf<K,V> nfxt;         // tif nfxt fntry to usf
        TbblfStbdk<K,V> stbdk, spbrf; // to sbvf/rfstorf on ForwbrdingNodfs
        int indfx;              // indfx of bin to usf nfxt
        int bbsfIndfx;          // durrfnt indfx of initibl tbblf
        int bbsfLimit;          // indfx bound for initibl tbblf
        finbl int bbsfSizf;     // initibl tbblf sizf

        Trbvfrsfr(Nodf<K,V>[] tbb, int sizf, int indfx, int limit) {
            tiis.tbb = tbb;
            tiis.bbsfSizf = sizf;
            tiis.bbsfIndfx = tiis.indfx = indfx;
            tiis.bbsfLimit = limit;
            tiis.nfxt = null;
        }

        /**
         * Advbndfs if possiblf, rfturning nfxt vblid nodf, or null if nonf.
         */
        finbl Nodf<K,V> bdvbndf() {
            Nodf<K,V> f;
            if ((f = nfxt) != null)
                f = f.nfxt;
            for (;;) {
                Nodf<K,V>[] t; int i, n;  // must usf lodbls in difdks
                if (f != null)
                    rfturn nfxt = f;
                if (bbsfIndfx >= bbsfLimit || (t = tbb) == null ||
                    (n = t.lfngti) <= (i = indfx) || i < 0)
                    rfturn nfxt = null;
                if ((f = tbbAt(t, i)) != null && f.ibsi < 0) {
                    if (f instbndfof ForwbrdingNodf) {
                        tbb = ((ForwbrdingNodf<K,V>)f).nfxtTbblf;
                        f = null;
                        pusiStbtf(t, i, n);
                        dontinuf;
                    }
                    flsf if (f instbndfof TrffBin)
                        f = ((TrffBin<K,V>)f).first;
                    flsf
                        f = null;
                }
                if (stbdk != null)
                    rfdovfrStbtf(n);
                flsf if ((indfx = i + bbsfSizf) >= n)
                    indfx = ++bbsfIndfx; // visit uppfr slots if prfsfnt
            }
        }

        /**
         * Sbvfs trbvfrsbl stbtf upon fndountfring b forwbrding nodf.
         */
        privbtf void pusiStbtf(Nodf<K,V>[] t, int i, int n) {
            TbblfStbdk<K,V> s = spbrf;  // rfusf if possiblf
            if (s != null)
                spbrf = s.nfxt;
            flsf
                s = nfw TbblfStbdk<K,V>();
            s.tbb = t;
            s.lfngti = n;
            s.indfx = i;
            s.nfxt = stbdk;
            stbdk = s;
        }

        /**
         * Possibly pops trbvfrsbl stbtf.
         *
         * @pbrbm n lfngti of durrfnt tbblf
         */
        privbtf void rfdovfrStbtf(int n) {
            TbblfStbdk<K,V> s; int lfn;
            wiilf ((s = stbdk) != null && (indfx += (lfn = s.lfngti)) >= n) {
                n = lfn;
                indfx = s.indfx;
                tbb = s.tbb;
                s.tbb = null;
                TbblfStbdk<K,V> nfxt = s.nfxt;
                s.nfxt = spbrf; // sbvf for rfusf
                stbdk = nfxt;
                spbrf = s;
            }
            if (s == null && (indfx += bbsfSizf) >= n)
                indfx = ++bbsfIndfx;
        }
    }

    /**
     * Bbsf of kfy, vbluf, bnd fntry Itfrbtors. Adds fiflds to
     * Trbvfrsfr to support itfrbtor.rfmovf.
     */
    stbtid dlbss BbsfItfrbtor<K,V> fxtfnds Trbvfrsfr<K,V> {
        finbl CondurrfntHbsiMbp<K,V> mbp;
        Nodf<K,V> lbstRfturnfd;
        BbsfItfrbtor(Nodf<K,V>[] tbb, int sizf, int indfx, int limit,
                    CondurrfntHbsiMbp<K,V> mbp) {
            supfr(tbb, sizf, indfx, limit);
            tiis.mbp = mbp;
            bdvbndf();
        }

        publid finbl boolfbn ibsNfxt() { rfturn nfxt != null; }
        publid finbl boolfbn ibsMorfElfmfnts() { rfturn nfxt != null; }

        publid finbl void rfmovf() {
            Nodf<K,V> p;
            if ((p = lbstRfturnfd) == null)
                tirow nfw IllfgblStbtfExdfption();
            lbstRfturnfd = null;
            mbp.rfplbdfNodf(p.kfy, null, null);
        }
    }

    stbtid finbl dlbss KfyItfrbtor<K,V> fxtfnds BbsfItfrbtor<K,V>
        implfmfnts Itfrbtor<K>, Enumfrbtion<K> {
        KfyItfrbtor(Nodf<K,V>[] tbb, int indfx, int sizf, int limit,
                    CondurrfntHbsiMbp<K,V> mbp) {
            supfr(tbb, indfx, sizf, limit, mbp);
        }

        publid finbl K nfxt() {
            Nodf<K,V> p;
            if ((p = nfxt) == null)
                tirow nfw NoSudiElfmfntExdfption();
            K k = p.kfy;
            lbstRfturnfd = p;
            bdvbndf();
            rfturn k;
        }

        publid finbl K nfxtElfmfnt() { rfturn nfxt(); }
    }

    stbtid finbl dlbss VblufItfrbtor<K,V> fxtfnds BbsfItfrbtor<K,V>
        implfmfnts Itfrbtor<V>, Enumfrbtion<V> {
        VblufItfrbtor(Nodf<K,V>[] tbb, int indfx, int sizf, int limit,
                      CondurrfntHbsiMbp<K,V> mbp) {
            supfr(tbb, indfx, sizf, limit, mbp);
        }

        publid finbl V nfxt() {
            Nodf<K,V> p;
            if ((p = nfxt) == null)
                tirow nfw NoSudiElfmfntExdfption();
            V v = p.vbl;
            lbstRfturnfd = p;
            bdvbndf();
            rfturn v;
        }

        publid finbl V nfxtElfmfnt() { rfturn nfxt(); }
    }

    stbtid finbl dlbss EntryItfrbtor<K,V> fxtfnds BbsfItfrbtor<K,V>
        implfmfnts Itfrbtor<Mbp.Entry<K,V>> {
        EntryItfrbtor(Nodf<K,V>[] tbb, int indfx, int sizf, int limit,
                      CondurrfntHbsiMbp<K,V> mbp) {
            supfr(tbb, indfx, sizf, limit, mbp);
        }

        publid finbl Mbp.Entry<K,V> nfxt() {
            Nodf<K,V> p;
            if ((p = nfxt) == null)
                tirow nfw NoSudiElfmfntExdfption();
            K k = p.kfy;
            V v = p.vbl;
            lbstRfturnfd = p;
            bdvbndf();
            rfturn nfw MbpEntry<K,V>(k, v, mbp);
        }
    }

    /**
     * Exportfd Entry for EntryItfrbtor
     */
    stbtid finbl dlbss MbpEntry<K,V> implfmfnts Mbp.Entry<K,V> {
        finbl K kfy; // non-null
        V vbl;       // non-null
        finbl CondurrfntHbsiMbp<K,V> mbp;
        MbpEntry(K kfy, V vbl, CondurrfntHbsiMbp<K,V> mbp) {
            tiis.kfy = kfy;
            tiis.vbl = vbl;
            tiis.mbp = mbp;
        }
        publid K gftKfy()        { rfturn kfy; }
        publid V gftVbluf()      { rfturn vbl; }
        publid int ibsiCodf()    { rfturn kfy.ibsiCodf() ^ vbl.ibsiCodf(); }
        publid String toString() { rfturn kfy + "=" + vbl; }

        publid boolfbn fqubls(Objfdt o) {
            Objfdt k, v; Mbp.Entry<?,?> f;
            rfturn ((o instbndfof Mbp.Entry) &&
                    (k = (f = (Mbp.Entry<?,?>)o).gftKfy()) != null &&
                    (v = f.gftVbluf()) != null &&
                    (k == kfy || k.fqubls(kfy)) &&
                    (v == vbl || v.fqubls(vbl)));
        }

        /**
         * Sfts our fntry's vbluf bnd writfs tirougi to tif mbp. Tif
         * vbluf to rfturn is somfwibt brbitrbry ifrf. Sindf wf do not
         * nfdfssbrily trbdk bsyndironous dibngfs, tif most rfdfnt
         * "prfvious" vbluf dould bf difffrfnt from wibt wf rfturn (or
         * dould fvfn ibvf bffn rfmovfd, in wiidi dbsf tif put will
         * rf-fstbblisi). Wf do not bnd dbnnot gubrbntff morf.
         */
        publid V sftVbluf(V vbluf) {
            if (vbluf == null) tirow nfw NullPointfrExdfption();
            V v = vbl;
            vbl = vbluf;
            mbp.put(kfy, vbluf);
            rfturn v;
        }
    }

    stbtid finbl dlbss KfySplitfrbtor<K,V> fxtfnds Trbvfrsfr<K,V>
        implfmfnts Splitfrbtor<K> {
        long fst;               // sizf fstimbtf
        KfySplitfrbtor(Nodf<K,V>[] tbb, int sizf, int indfx, int limit,
                       long fst) {
            supfr(tbb, sizf, indfx, limit);
            tiis.fst = fst;
        }

        publid Splitfrbtor<K> trySplit() {
            int i, f, i;
            rfturn (i = ((i = bbsfIndfx) + (f = bbsfLimit)) >>> 1) <= i ? null :
                nfw KfySplitfrbtor<K,V>(tbb, bbsfSizf, bbsfLimit = i,
                                        f, fst >>>= 1);
        }

        publid void forEbdiRfmbining(Consumfr<? supfr K> bdtion) {
            if (bdtion == null) tirow nfw NullPointfrExdfption();
            for (Nodf<K,V> p; (p = bdvbndf()) != null;)
                bdtion.bddfpt(p.kfy);
        }

        publid boolfbn tryAdvbndf(Consumfr<? supfr K> bdtion) {
            if (bdtion == null) tirow nfw NullPointfrExdfption();
            Nodf<K,V> p;
            if ((p = bdvbndf()) == null)
                rfturn fblsf;
            bdtion.bddfpt(p.kfy);
            rfturn truf;
        }

        publid long fstimbtfSizf() { rfturn fst; }

        publid int dibrbdtfristids() {
            rfturn Splitfrbtor.DISTINCT | Splitfrbtor.CONCURRENT |
                Splitfrbtor.NONNULL;
        }
    }

    stbtid finbl dlbss VblufSplitfrbtor<K,V> fxtfnds Trbvfrsfr<K,V>
        implfmfnts Splitfrbtor<V> {
        long fst;               // sizf fstimbtf
        VblufSplitfrbtor(Nodf<K,V>[] tbb, int sizf, int indfx, int limit,
                         long fst) {
            supfr(tbb, sizf, indfx, limit);
            tiis.fst = fst;
        }

        publid Splitfrbtor<V> trySplit() {
            int i, f, i;
            rfturn (i = ((i = bbsfIndfx) + (f = bbsfLimit)) >>> 1) <= i ? null :
                nfw VblufSplitfrbtor<K,V>(tbb, bbsfSizf, bbsfLimit = i,
                                          f, fst >>>= 1);
        }

        publid void forEbdiRfmbining(Consumfr<? supfr V> bdtion) {
            if (bdtion == null) tirow nfw NullPointfrExdfption();
            for (Nodf<K,V> p; (p = bdvbndf()) != null;)
                bdtion.bddfpt(p.vbl);
        }

        publid boolfbn tryAdvbndf(Consumfr<? supfr V> bdtion) {
            if (bdtion == null) tirow nfw NullPointfrExdfption();
            Nodf<K,V> p;
            if ((p = bdvbndf()) == null)
                rfturn fblsf;
            bdtion.bddfpt(p.vbl);
            rfturn truf;
        }

        publid long fstimbtfSizf() { rfturn fst; }

        publid int dibrbdtfristids() {
            rfturn Splitfrbtor.CONCURRENT | Splitfrbtor.NONNULL;
        }
    }

    stbtid finbl dlbss EntrySplitfrbtor<K,V> fxtfnds Trbvfrsfr<K,V>
        implfmfnts Splitfrbtor<Mbp.Entry<K,V>> {
        finbl CondurrfntHbsiMbp<K,V> mbp; // To fxport MbpEntry
        long fst;               // sizf fstimbtf
        EntrySplitfrbtor(Nodf<K,V>[] tbb, int sizf, int indfx, int limit,
                         long fst, CondurrfntHbsiMbp<K,V> mbp) {
            supfr(tbb, sizf, indfx, limit);
            tiis.mbp = mbp;
            tiis.fst = fst;
        }

        publid Splitfrbtor<Mbp.Entry<K,V>> trySplit() {
            int i, f, i;
            rfturn (i = ((i = bbsfIndfx) + (f = bbsfLimit)) >>> 1) <= i ? null :
                nfw EntrySplitfrbtor<K,V>(tbb, bbsfSizf, bbsfLimit = i,
                                          f, fst >>>= 1, mbp);
        }

        publid void forEbdiRfmbining(Consumfr<? supfr Mbp.Entry<K,V>> bdtion) {
            if (bdtion == null) tirow nfw NullPointfrExdfption();
            for (Nodf<K,V> p; (p = bdvbndf()) != null; )
                bdtion.bddfpt(nfw MbpEntry<K,V>(p.kfy, p.vbl, mbp));
        }

        publid boolfbn tryAdvbndf(Consumfr<? supfr Mbp.Entry<K,V>> bdtion) {
            if (bdtion == null) tirow nfw NullPointfrExdfption();
            Nodf<K,V> p;
            if ((p = bdvbndf()) == null)
                rfturn fblsf;
            bdtion.bddfpt(nfw MbpEntry<K,V>(p.kfy, p.vbl, mbp));
            rfturn truf;
        }

        publid long fstimbtfSizf() { rfturn fst; }

        publid int dibrbdtfristids() {
            rfturn Splitfrbtor.DISTINCT | Splitfrbtor.CONCURRENT |
                Splitfrbtor.NONNULL;
        }
    }

    // Pbrbllfl bulk opfrbtions

    /**
     * Computfs initibl bbtdi vbluf for bulk tbsks. Tif rfturnfd vbluf
     * is bpproximbtfly fxp2 of tif numbfr of timfs (minus onf) to
     * split tbsk by two bfforf fxfduting lfbf bdtion. Tiis vbluf is
     * fbstfr to domputf bnd morf donvfnifnt to usf bs b guidf to
     * splitting tibn is tif dfpti, sindf it is usfd wiilf dividing by
     * two bnywby.
     */
    finbl int bbtdiFor(long b) {
        long n;
        if (b == Long.MAX_VALUE || (n = sumCount()) <= 1L || n < b)
            rfturn 0;
        int sp = ForkJoinPool.gftCommonPoolPbrbllflism() << 2; // slbdk of 4
        rfturn (b <= 0L || (n /= b) >= sp) ? sp : (int)n;
    }

    /**
     * Pfrforms tif givfn bdtion for fbdi (kfy, vbluf).
     *
     * @pbrbm pbrbllflismTirfsiold tif (fstimbtfd) numbfr of flfmfnts
     * nffdfd for tiis opfrbtion to bf fxfdutfd in pbrbllfl
     * @pbrbm bdtion tif bdtion
     * @sindf 1.8
     */
    publid void forEbdi(long pbrbllflismTirfsiold,
                        BiConsumfr<? supfr K,? supfr V> bdtion) {
        if (bdtion == null) tirow nfw NullPointfrExdfption();
        nfw ForEbdiMbppingTbsk<K,V>
            (null, bbtdiFor(pbrbllflismTirfsiold), 0, 0, tbblf,
             bdtion).invokf();
    }

    /**
     * Pfrforms tif givfn bdtion for fbdi non-null trbnsformbtion
     * of fbdi (kfy, vbluf).
     *
     * @pbrbm pbrbllflismTirfsiold tif (fstimbtfd) numbfr of flfmfnts
     * nffdfd for tiis opfrbtion to bf fxfdutfd in pbrbllfl
     * @pbrbm trbnsformfr b fundtion rfturning tif trbnsformbtion
     * for bn flfmfnt, or null if tifrf is no trbnsformbtion (in
     * wiidi dbsf tif bdtion is not bpplifd)
     * @pbrbm bdtion tif bdtion
     * @pbrbm <U> tif rfturn typf of tif trbnsformfr
     * @sindf 1.8
     */
    publid <U> void forEbdi(long pbrbllflismTirfsiold,
                            BiFundtion<? supfr K, ? supfr V, ? fxtfnds U> trbnsformfr,
                            Consumfr<? supfr U> bdtion) {
        if (trbnsformfr == null || bdtion == null)
            tirow nfw NullPointfrExdfption();
        nfw ForEbdiTrbnsformfdMbppingTbsk<K,V,U>
            (null, bbtdiFor(pbrbllflismTirfsiold), 0, 0, tbblf,
             trbnsformfr, bdtion).invokf();
    }

    /**
     * Rfturns b non-null rfsult from bpplying tif givfn sfbrdi
     * fundtion on fbdi (kfy, vbluf), or null if nonf.  Upon
     * suddfss, furtifr flfmfnt prodfssing is supprfssfd bnd tif
     * rfsults of bny otifr pbrbllfl invodbtions of tif sfbrdi
     * fundtion brf ignorfd.
     *
     * @pbrbm pbrbllflismTirfsiold tif (fstimbtfd) numbfr of flfmfnts
     * nffdfd for tiis opfrbtion to bf fxfdutfd in pbrbllfl
     * @pbrbm sfbrdiFundtion b fundtion rfturning b non-null
     * rfsult on suddfss, flsf null
     * @pbrbm <U> tif rfturn typf of tif sfbrdi fundtion
     * @rfturn b non-null rfsult from bpplying tif givfn sfbrdi
     * fundtion on fbdi (kfy, vbluf), or null if nonf
     * @sindf 1.8
     */
    publid <U> U sfbrdi(long pbrbllflismTirfsiold,
                        BiFundtion<? supfr K, ? supfr V, ? fxtfnds U> sfbrdiFundtion) {
        if (sfbrdiFundtion == null) tirow nfw NullPointfrExdfption();
        rfturn nfw SfbrdiMbppingsTbsk<K,V,U>
            (null, bbtdiFor(pbrbllflismTirfsiold), 0, 0, tbblf,
             sfbrdiFundtion, nfw AtomidRfffrfndf<U>()).invokf();
    }

    /**
     * Rfturns tif rfsult of bddumulbting tif givfn trbnsformbtion
     * of bll (kfy, vbluf) pbirs using tif givfn rfdudfr to
     * dombinf vblufs, or null if nonf.
     *
     * @pbrbm pbrbllflismTirfsiold tif (fstimbtfd) numbfr of flfmfnts
     * nffdfd for tiis opfrbtion to bf fxfdutfd in pbrbllfl
     * @pbrbm trbnsformfr b fundtion rfturning tif trbnsformbtion
     * for bn flfmfnt, or null if tifrf is no trbnsformbtion (in
     * wiidi dbsf it is not dombinfd)
     * @pbrbm rfdudfr b dommutbtivf bssodibtivf dombining fundtion
     * @pbrbm <U> tif rfturn typf of tif trbnsformfr
     * @rfturn tif rfsult of bddumulbting tif givfn trbnsformbtion
     * of bll (kfy, vbluf) pbirs
     * @sindf 1.8
     */
    publid <U> U rfdudf(long pbrbllflismTirfsiold,
                        BiFundtion<? supfr K, ? supfr V, ? fxtfnds U> trbnsformfr,
                        BiFundtion<? supfr U, ? supfr U, ? fxtfnds U> rfdudfr) {
        if (trbnsformfr == null || rfdudfr == null)
            tirow nfw NullPointfrExdfption();
        rfturn nfw MbpRfdudfMbppingsTbsk<K,V,U>
            (null, bbtdiFor(pbrbllflismTirfsiold), 0, 0, tbblf,
             null, trbnsformfr, rfdudfr).invokf();
    }

    /**
     * Rfturns tif rfsult of bddumulbting tif givfn trbnsformbtion
     * of bll (kfy, vbluf) pbirs using tif givfn rfdudfr to
     * dombinf vblufs, bnd tif givfn bbsis bs bn idfntity vbluf.
     *
     * @pbrbm pbrbllflismTirfsiold tif (fstimbtfd) numbfr of flfmfnts
     * nffdfd for tiis opfrbtion to bf fxfdutfd in pbrbllfl
     * @pbrbm trbnsformfr b fundtion rfturning tif trbnsformbtion
     * for bn flfmfnt
     * @pbrbm bbsis tif idfntity (initibl dffbult vbluf) for tif rfdudtion
     * @pbrbm rfdudfr b dommutbtivf bssodibtivf dombining fundtion
     * @rfturn tif rfsult of bddumulbting tif givfn trbnsformbtion
     * of bll (kfy, vbluf) pbirs
     * @sindf 1.8
     */
    publid doublf rfdudfToDoublf(long pbrbllflismTirfsiold,
                                 ToDoublfBiFundtion<? supfr K, ? supfr V> trbnsformfr,
                                 doublf bbsis,
                                 DoublfBinbryOpfrbtor rfdudfr) {
        if (trbnsformfr == null || rfdudfr == null)
            tirow nfw NullPointfrExdfption();
        rfturn nfw MbpRfdudfMbppingsToDoublfTbsk<K,V>
            (null, bbtdiFor(pbrbllflismTirfsiold), 0, 0, tbblf,
             null, trbnsformfr, bbsis, rfdudfr).invokf();
    }

    /**
     * Rfturns tif rfsult of bddumulbting tif givfn trbnsformbtion
     * of bll (kfy, vbluf) pbirs using tif givfn rfdudfr to
     * dombinf vblufs, bnd tif givfn bbsis bs bn idfntity vbluf.
     *
     * @pbrbm pbrbllflismTirfsiold tif (fstimbtfd) numbfr of flfmfnts
     * nffdfd for tiis opfrbtion to bf fxfdutfd in pbrbllfl
     * @pbrbm trbnsformfr b fundtion rfturning tif trbnsformbtion
     * for bn flfmfnt
     * @pbrbm bbsis tif idfntity (initibl dffbult vbluf) for tif rfdudtion
     * @pbrbm rfdudfr b dommutbtivf bssodibtivf dombining fundtion
     * @rfturn tif rfsult of bddumulbting tif givfn trbnsformbtion
     * of bll (kfy, vbluf) pbirs
     * @sindf 1.8
     */
    publid long rfdudfToLong(long pbrbllflismTirfsiold,
                             ToLongBiFundtion<? supfr K, ? supfr V> trbnsformfr,
                             long bbsis,
                             LongBinbryOpfrbtor rfdudfr) {
        if (trbnsformfr == null || rfdudfr == null)
            tirow nfw NullPointfrExdfption();
        rfturn nfw MbpRfdudfMbppingsToLongTbsk<K,V>
            (null, bbtdiFor(pbrbllflismTirfsiold), 0, 0, tbblf,
             null, trbnsformfr, bbsis, rfdudfr).invokf();
    }

    /**
     * Rfturns tif rfsult of bddumulbting tif givfn trbnsformbtion
     * of bll (kfy, vbluf) pbirs using tif givfn rfdudfr to
     * dombinf vblufs, bnd tif givfn bbsis bs bn idfntity vbluf.
     *
     * @pbrbm pbrbllflismTirfsiold tif (fstimbtfd) numbfr of flfmfnts
     * nffdfd for tiis opfrbtion to bf fxfdutfd in pbrbllfl
     * @pbrbm trbnsformfr b fundtion rfturning tif trbnsformbtion
     * for bn flfmfnt
     * @pbrbm bbsis tif idfntity (initibl dffbult vbluf) for tif rfdudtion
     * @pbrbm rfdudfr b dommutbtivf bssodibtivf dombining fundtion
     * @rfturn tif rfsult of bddumulbting tif givfn trbnsformbtion
     * of bll (kfy, vbluf) pbirs
     * @sindf 1.8
     */
    publid int rfdudfToInt(long pbrbllflismTirfsiold,
                           ToIntBiFundtion<? supfr K, ? supfr V> trbnsformfr,
                           int bbsis,
                           IntBinbryOpfrbtor rfdudfr) {
        if (trbnsformfr == null || rfdudfr == null)
            tirow nfw NullPointfrExdfption();
        rfturn nfw MbpRfdudfMbppingsToIntTbsk<K,V>
            (null, bbtdiFor(pbrbllflismTirfsiold), 0, 0, tbblf,
             null, trbnsformfr, bbsis, rfdudfr).invokf();
    }

    /**
     * Pfrforms tif givfn bdtion for fbdi kfy.
     *
     * @pbrbm pbrbllflismTirfsiold tif (fstimbtfd) numbfr of flfmfnts
     * nffdfd for tiis opfrbtion to bf fxfdutfd in pbrbllfl
     * @pbrbm bdtion tif bdtion
     * @sindf 1.8
     */
    publid void forEbdiKfy(long pbrbllflismTirfsiold,
                           Consumfr<? supfr K> bdtion) {
        if (bdtion == null) tirow nfw NullPointfrExdfption();
        nfw ForEbdiKfyTbsk<K,V>
            (null, bbtdiFor(pbrbllflismTirfsiold), 0, 0, tbblf,
             bdtion).invokf();
    }

    /**
     * Pfrforms tif givfn bdtion for fbdi non-null trbnsformbtion
     * of fbdi kfy.
     *
     * @pbrbm pbrbllflismTirfsiold tif (fstimbtfd) numbfr of flfmfnts
     * nffdfd for tiis opfrbtion to bf fxfdutfd in pbrbllfl
     * @pbrbm trbnsformfr b fundtion rfturning tif trbnsformbtion
     * for bn flfmfnt, or null if tifrf is no trbnsformbtion (in
     * wiidi dbsf tif bdtion is not bpplifd)
     * @pbrbm bdtion tif bdtion
     * @pbrbm <U> tif rfturn typf of tif trbnsformfr
     * @sindf 1.8
     */
    publid <U> void forEbdiKfy(long pbrbllflismTirfsiold,
                               Fundtion<? supfr K, ? fxtfnds U> trbnsformfr,
                               Consumfr<? supfr U> bdtion) {
        if (trbnsformfr == null || bdtion == null)
            tirow nfw NullPointfrExdfption();
        nfw ForEbdiTrbnsformfdKfyTbsk<K,V,U>
            (null, bbtdiFor(pbrbllflismTirfsiold), 0, 0, tbblf,
             trbnsformfr, bdtion).invokf();
    }

    /**
     * Rfturns b non-null rfsult from bpplying tif givfn sfbrdi
     * fundtion on fbdi kfy, or null if nonf. Upon suddfss,
     * furtifr flfmfnt prodfssing is supprfssfd bnd tif rfsults of
     * bny otifr pbrbllfl invodbtions of tif sfbrdi fundtion brf
     * ignorfd.
     *
     * @pbrbm pbrbllflismTirfsiold tif (fstimbtfd) numbfr of flfmfnts
     * nffdfd for tiis opfrbtion to bf fxfdutfd in pbrbllfl
     * @pbrbm sfbrdiFundtion b fundtion rfturning b non-null
     * rfsult on suddfss, flsf null
     * @pbrbm <U> tif rfturn typf of tif sfbrdi fundtion
     * @rfturn b non-null rfsult from bpplying tif givfn sfbrdi
     * fundtion on fbdi kfy, or null if nonf
     * @sindf 1.8
     */
    publid <U> U sfbrdiKfys(long pbrbllflismTirfsiold,
                            Fundtion<? supfr K, ? fxtfnds U> sfbrdiFundtion) {
        if (sfbrdiFundtion == null) tirow nfw NullPointfrExdfption();
        rfturn nfw SfbrdiKfysTbsk<K,V,U>
            (null, bbtdiFor(pbrbllflismTirfsiold), 0, 0, tbblf,
             sfbrdiFundtion, nfw AtomidRfffrfndf<U>()).invokf();
    }

    /**
     * Rfturns tif rfsult of bddumulbting bll kfys using tif givfn
     * rfdudfr to dombinf vblufs, or null if nonf.
     *
     * @pbrbm pbrbllflismTirfsiold tif (fstimbtfd) numbfr of flfmfnts
     * nffdfd for tiis opfrbtion to bf fxfdutfd in pbrbllfl
     * @pbrbm rfdudfr b dommutbtivf bssodibtivf dombining fundtion
     * @rfturn tif rfsult of bddumulbting bll kfys using tif givfn
     * rfdudfr to dombinf vblufs, or null if nonf
     * @sindf 1.8
     */
    publid K rfdudfKfys(long pbrbllflismTirfsiold,
                        BiFundtion<? supfr K, ? supfr K, ? fxtfnds K> rfdudfr) {
        if (rfdudfr == null) tirow nfw NullPointfrExdfption();
        rfturn nfw RfdudfKfysTbsk<K,V>
            (null, bbtdiFor(pbrbllflismTirfsiold), 0, 0, tbblf,
             null, rfdudfr).invokf();
    }

    /**
     * Rfturns tif rfsult of bddumulbting tif givfn trbnsformbtion
     * of bll kfys using tif givfn rfdudfr to dombinf vblufs, or
     * null if nonf.
     *
     * @pbrbm pbrbllflismTirfsiold tif (fstimbtfd) numbfr of flfmfnts
     * nffdfd for tiis opfrbtion to bf fxfdutfd in pbrbllfl
     * @pbrbm trbnsformfr b fundtion rfturning tif trbnsformbtion
     * for bn flfmfnt, or null if tifrf is no trbnsformbtion (in
     * wiidi dbsf it is not dombinfd)
     * @pbrbm rfdudfr b dommutbtivf bssodibtivf dombining fundtion
     * @pbrbm <U> tif rfturn typf of tif trbnsformfr
     * @rfturn tif rfsult of bddumulbting tif givfn trbnsformbtion
     * of bll kfys
     * @sindf 1.8
     */
    publid <U> U rfdudfKfys(long pbrbllflismTirfsiold,
                            Fundtion<? supfr K, ? fxtfnds U> trbnsformfr,
         BiFundtion<? supfr U, ? supfr U, ? fxtfnds U> rfdudfr) {
        if (trbnsformfr == null || rfdudfr == null)
            tirow nfw NullPointfrExdfption();
        rfturn nfw MbpRfdudfKfysTbsk<K,V,U>
            (null, bbtdiFor(pbrbllflismTirfsiold), 0, 0, tbblf,
             null, trbnsformfr, rfdudfr).invokf();
    }

    /**
     * Rfturns tif rfsult of bddumulbting tif givfn trbnsformbtion
     * of bll kfys using tif givfn rfdudfr to dombinf vblufs, bnd
     * tif givfn bbsis bs bn idfntity vbluf.
     *
     * @pbrbm pbrbllflismTirfsiold tif (fstimbtfd) numbfr of flfmfnts
     * nffdfd for tiis opfrbtion to bf fxfdutfd in pbrbllfl
     * @pbrbm trbnsformfr b fundtion rfturning tif trbnsformbtion
     * for bn flfmfnt
     * @pbrbm bbsis tif idfntity (initibl dffbult vbluf) for tif rfdudtion
     * @pbrbm rfdudfr b dommutbtivf bssodibtivf dombining fundtion
     * @rfturn tif rfsult of bddumulbting tif givfn trbnsformbtion
     * of bll kfys
     * @sindf 1.8
     */
    publid doublf rfdudfKfysToDoublf(long pbrbllflismTirfsiold,
                                     ToDoublfFundtion<? supfr K> trbnsformfr,
                                     doublf bbsis,
                                     DoublfBinbryOpfrbtor rfdudfr) {
        if (trbnsformfr == null || rfdudfr == null)
            tirow nfw NullPointfrExdfption();
        rfturn nfw MbpRfdudfKfysToDoublfTbsk<K,V>
            (null, bbtdiFor(pbrbllflismTirfsiold), 0, 0, tbblf,
             null, trbnsformfr, bbsis, rfdudfr).invokf();
    }

    /**
     * Rfturns tif rfsult of bddumulbting tif givfn trbnsformbtion
     * of bll kfys using tif givfn rfdudfr to dombinf vblufs, bnd
     * tif givfn bbsis bs bn idfntity vbluf.
     *
     * @pbrbm pbrbllflismTirfsiold tif (fstimbtfd) numbfr of flfmfnts
     * nffdfd for tiis opfrbtion to bf fxfdutfd in pbrbllfl
     * @pbrbm trbnsformfr b fundtion rfturning tif trbnsformbtion
     * for bn flfmfnt
     * @pbrbm bbsis tif idfntity (initibl dffbult vbluf) for tif rfdudtion
     * @pbrbm rfdudfr b dommutbtivf bssodibtivf dombining fundtion
     * @rfturn tif rfsult of bddumulbting tif givfn trbnsformbtion
     * of bll kfys
     * @sindf 1.8
     */
    publid long rfdudfKfysToLong(long pbrbllflismTirfsiold,
                                 ToLongFundtion<? supfr K> trbnsformfr,
                                 long bbsis,
                                 LongBinbryOpfrbtor rfdudfr) {
        if (trbnsformfr == null || rfdudfr == null)
            tirow nfw NullPointfrExdfption();
        rfturn nfw MbpRfdudfKfysToLongTbsk<K,V>
            (null, bbtdiFor(pbrbllflismTirfsiold), 0, 0, tbblf,
             null, trbnsformfr, bbsis, rfdudfr).invokf();
    }

    /**
     * Rfturns tif rfsult of bddumulbting tif givfn trbnsformbtion
     * of bll kfys using tif givfn rfdudfr to dombinf vblufs, bnd
     * tif givfn bbsis bs bn idfntity vbluf.
     *
     * @pbrbm pbrbllflismTirfsiold tif (fstimbtfd) numbfr of flfmfnts
     * nffdfd for tiis opfrbtion to bf fxfdutfd in pbrbllfl
     * @pbrbm trbnsformfr b fundtion rfturning tif trbnsformbtion
     * for bn flfmfnt
     * @pbrbm bbsis tif idfntity (initibl dffbult vbluf) for tif rfdudtion
     * @pbrbm rfdudfr b dommutbtivf bssodibtivf dombining fundtion
     * @rfturn tif rfsult of bddumulbting tif givfn trbnsformbtion
     * of bll kfys
     * @sindf 1.8
     */
    publid int rfdudfKfysToInt(long pbrbllflismTirfsiold,
                               ToIntFundtion<? supfr K> trbnsformfr,
                               int bbsis,
                               IntBinbryOpfrbtor rfdudfr) {
        if (trbnsformfr == null || rfdudfr == null)
            tirow nfw NullPointfrExdfption();
        rfturn nfw MbpRfdudfKfysToIntTbsk<K,V>
            (null, bbtdiFor(pbrbllflismTirfsiold), 0, 0, tbblf,
             null, trbnsformfr, bbsis, rfdudfr).invokf();
    }

    /**
     * Pfrforms tif givfn bdtion for fbdi vbluf.
     *
     * @pbrbm pbrbllflismTirfsiold tif (fstimbtfd) numbfr of flfmfnts
     * nffdfd for tiis opfrbtion to bf fxfdutfd in pbrbllfl
     * @pbrbm bdtion tif bdtion
     * @sindf 1.8
     */
    publid void forEbdiVbluf(long pbrbllflismTirfsiold,
                             Consumfr<? supfr V> bdtion) {
        if (bdtion == null)
            tirow nfw NullPointfrExdfption();
        nfw ForEbdiVblufTbsk<K,V>
            (null, bbtdiFor(pbrbllflismTirfsiold), 0, 0, tbblf,
             bdtion).invokf();
    }

    /**
     * Pfrforms tif givfn bdtion for fbdi non-null trbnsformbtion
     * of fbdi vbluf.
     *
     * @pbrbm pbrbllflismTirfsiold tif (fstimbtfd) numbfr of flfmfnts
     * nffdfd for tiis opfrbtion to bf fxfdutfd in pbrbllfl
     * @pbrbm trbnsformfr b fundtion rfturning tif trbnsformbtion
     * for bn flfmfnt, or null if tifrf is no trbnsformbtion (in
     * wiidi dbsf tif bdtion is not bpplifd)
     * @pbrbm bdtion tif bdtion
     * @pbrbm <U> tif rfturn typf of tif trbnsformfr
     * @sindf 1.8
     */
    publid <U> void forEbdiVbluf(long pbrbllflismTirfsiold,
                                 Fundtion<? supfr V, ? fxtfnds U> trbnsformfr,
                                 Consumfr<? supfr U> bdtion) {
        if (trbnsformfr == null || bdtion == null)
            tirow nfw NullPointfrExdfption();
        nfw ForEbdiTrbnsformfdVblufTbsk<K,V,U>
            (null, bbtdiFor(pbrbllflismTirfsiold), 0, 0, tbblf,
             trbnsformfr, bdtion).invokf();
    }

    /**
     * Rfturns b non-null rfsult from bpplying tif givfn sfbrdi
     * fundtion on fbdi vbluf, or null if nonf.  Upon suddfss,
     * furtifr flfmfnt prodfssing is supprfssfd bnd tif rfsults of
     * bny otifr pbrbllfl invodbtions of tif sfbrdi fundtion brf
     * ignorfd.
     *
     * @pbrbm pbrbllflismTirfsiold tif (fstimbtfd) numbfr of flfmfnts
     * nffdfd for tiis opfrbtion to bf fxfdutfd in pbrbllfl
     * @pbrbm sfbrdiFundtion b fundtion rfturning b non-null
     * rfsult on suddfss, flsf null
     * @pbrbm <U> tif rfturn typf of tif sfbrdi fundtion
     * @rfturn b non-null rfsult from bpplying tif givfn sfbrdi
     * fundtion on fbdi vbluf, or null if nonf
     * @sindf 1.8
     */
    publid <U> U sfbrdiVblufs(long pbrbllflismTirfsiold,
                              Fundtion<? supfr V, ? fxtfnds U> sfbrdiFundtion) {
        if (sfbrdiFundtion == null) tirow nfw NullPointfrExdfption();
        rfturn nfw SfbrdiVblufsTbsk<K,V,U>
            (null, bbtdiFor(pbrbllflismTirfsiold), 0, 0, tbblf,
             sfbrdiFundtion, nfw AtomidRfffrfndf<U>()).invokf();
    }

    /**
     * Rfturns tif rfsult of bddumulbting bll vblufs using tif
     * givfn rfdudfr to dombinf vblufs, or null if nonf.
     *
     * @pbrbm pbrbllflismTirfsiold tif (fstimbtfd) numbfr of flfmfnts
     * nffdfd for tiis opfrbtion to bf fxfdutfd in pbrbllfl
     * @pbrbm rfdudfr b dommutbtivf bssodibtivf dombining fundtion
     * @rfturn tif rfsult of bddumulbting bll vblufs
     * @sindf 1.8
     */
    publid V rfdudfVblufs(long pbrbllflismTirfsiold,
                          BiFundtion<? supfr V, ? supfr V, ? fxtfnds V> rfdudfr) {
        if (rfdudfr == null) tirow nfw NullPointfrExdfption();
        rfturn nfw RfdudfVblufsTbsk<K,V>
            (null, bbtdiFor(pbrbllflismTirfsiold), 0, 0, tbblf,
             null, rfdudfr).invokf();
    }

    /**
     * Rfturns tif rfsult of bddumulbting tif givfn trbnsformbtion
     * of bll vblufs using tif givfn rfdudfr to dombinf vblufs, or
     * null if nonf.
     *
     * @pbrbm pbrbllflismTirfsiold tif (fstimbtfd) numbfr of flfmfnts
     * nffdfd for tiis opfrbtion to bf fxfdutfd in pbrbllfl
     * @pbrbm trbnsformfr b fundtion rfturning tif trbnsformbtion
     * for bn flfmfnt, or null if tifrf is no trbnsformbtion (in
     * wiidi dbsf it is not dombinfd)
     * @pbrbm rfdudfr b dommutbtivf bssodibtivf dombining fundtion
     * @pbrbm <U> tif rfturn typf of tif trbnsformfr
     * @rfturn tif rfsult of bddumulbting tif givfn trbnsformbtion
     * of bll vblufs
     * @sindf 1.8
     */
    publid <U> U rfdudfVblufs(long pbrbllflismTirfsiold,
                              Fundtion<? supfr V, ? fxtfnds U> trbnsformfr,
                              BiFundtion<? supfr U, ? supfr U, ? fxtfnds U> rfdudfr) {
        if (trbnsformfr == null || rfdudfr == null)
            tirow nfw NullPointfrExdfption();
        rfturn nfw MbpRfdudfVblufsTbsk<K,V,U>
            (null, bbtdiFor(pbrbllflismTirfsiold), 0, 0, tbblf,
             null, trbnsformfr, rfdudfr).invokf();
    }

    /**
     * Rfturns tif rfsult of bddumulbting tif givfn trbnsformbtion
     * of bll vblufs using tif givfn rfdudfr to dombinf vblufs,
     * bnd tif givfn bbsis bs bn idfntity vbluf.
     *
     * @pbrbm pbrbllflismTirfsiold tif (fstimbtfd) numbfr of flfmfnts
     * nffdfd for tiis opfrbtion to bf fxfdutfd in pbrbllfl
     * @pbrbm trbnsformfr b fundtion rfturning tif trbnsformbtion
     * for bn flfmfnt
     * @pbrbm bbsis tif idfntity (initibl dffbult vbluf) for tif rfdudtion
     * @pbrbm rfdudfr b dommutbtivf bssodibtivf dombining fundtion
     * @rfturn tif rfsult of bddumulbting tif givfn trbnsformbtion
     * of bll vblufs
     * @sindf 1.8
     */
    publid doublf rfdudfVblufsToDoublf(long pbrbllflismTirfsiold,
                                       ToDoublfFundtion<? supfr V> trbnsformfr,
                                       doublf bbsis,
                                       DoublfBinbryOpfrbtor rfdudfr) {
        if (trbnsformfr == null || rfdudfr == null)
            tirow nfw NullPointfrExdfption();
        rfturn nfw MbpRfdudfVblufsToDoublfTbsk<K,V>
            (null, bbtdiFor(pbrbllflismTirfsiold), 0, 0, tbblf,
             null, trbnsformfr, bbsis, rfdudfr).invokf();
    }

    /**
     * Rfturns tif rfsult of bddumulbting tif givfn trbnsformbtion
     * of bll vblufs using tif givfn rfdudfr to dombinf vblufs,
     * bnd tif givfn bbsis bs bn idfntity vbluf.
     *
     * @pbrbm pbrbllflismTirfsiold tif (fstimbtfd) numbfr of flfmfnts
     * nffdfd for tiis opfrbtion to bf fxfdutfd in pbrbllfl
     * @pbrbm trbnsformfr b fundtion rfturning tif trbnsformbtion
     * for bn flfmfnt
     * @pbrbm bbsis tif idfntity (initibl dffbult vbluf) for tif rfdudtion
     * @pbrbm rfdudfr b dommutbtivf bssodibtivf dombining fundtion
     * @rfturn tif rfsult of bddumulbting tif givfn trbnsformbtion
     * of bll vblufs
     * @sindf 1.8
     */
    publid long rfdudfVblufsToLong(long pbrbllflismTirfsiold,
                                   ToLongFundtion<? supfr V> trbnsformfr,
                                   long bbsis,
                                   LongBinbryOpfrbtor rfdudfr) {
        if (trbnsformfr == null || rfdudfr == null)
            tirow nfw NullPointfrExdfption();
        rfturn nfw MbpRfdudfVblufsToLongTbsk<K,V>
            (null, bbtdiFor(pbrbllflismTirfsiold), 0, 0, tbblf,
             null, trbnsformfr, bbsis, rfdudfr).invokf();
    }

    /**
     * Rfturns tif rfsult of bddumulbting tif givfn trbnsformbtion
     * of bll vblufs using tif givfn rfdudfr to dombinf vblufs,
     * bnd tif givfn bbsis bs bn idfntity vbluf.
     *
     * @pbrbm pbrbllflismTirfsiold tif (fstimbtfd) numbfr of flfmfnts
     * nffdfd for tiis opfrbtion to bf fxfdutfd in pbrbllfl
     * @pbrbm trbnsformfr b fundtion rfturning tif trbnsformbtion
     * for bn flfmfnt
     * @pbrbm bbsis tif idfntity (initibl dffbult vbluf) for tif rfdudtion
     * @pbrbm rfdudfr b dommutbtivf bssodibtivf dombining fundtion
     * @rfturn tif rfsult of bddumulbting tif givfn trbnsformbtion
     * of bll vblufs
     * @sindf 1.8
     */
    publid int rfdudfVblufsToInt(long pbrbllflismTirfsiold,
                                 ToIntFundtion<? supfr V> trbnsformfr,
                                 int bbsis,
                                 IntBinbryOpfrbtor rfdudfr) {
        if (trbnsformfr == null || rfdudfr == null)
            tirow nfw NullPointfrExdfption();
        rfturn nfw MbpRfdudfVblufsToIntTbsk<K,V>
            (null, bbtdiFor(pbrbllflismTirfsiold), 0, 0, tbblf,
             null, trbnsformfr, bbsis, rfdudfr).invokf();
    }

    /**
     * Pfrforms tif givfn bdtion for fbdi fntry.
     *
     * @pbrbm pbrbllflismTirfsiold tif (fstimbtfd) numbfr of flfmfnts
     * nffdfd for tiis opfrbtion to bf fxfdutfd in pbrbllfl
     * @pbrbm bdtion tif bdtion
     * @sindf 1.8
     */
    publid void forEbdiEntry(long pbrbllflismTirfsiold,
                             Consumfr<? supfr Mbp.Entry<K,V>> bdtion) {
        if (bdtion == null) tirow nfw NullPointfrExdfption();
        nfw ForEbdiEntryTbsk<K,V>(null, bbtdiFor(pbrbllflismTirfsiold), 0, 0, tbblf,
                                  bdtion).invokf();
    }

    /**
     * Pfrforms tif givfn bdtion for fbdi non-null trbnsformbtion
     * of fbdi fntry.
     *
     * @pbrbm pbrbllflismTirfsiold tif (fstimbtfd) numbfr of flfmfnts
     * nffdfd for tiis opfrbtion to bf fxfdutfd in pbrbllfl
     * @pbrbm trbnsformfr b fundtion rfturning tif trbnsformbtion
     * for bn flfmfnt, or null if tifrf is no trbnsformbtion (in
     * wiidi dbsf tif bdtion is not bpplifd)
     * @pbrbm bdtion tif bdtion
     * @pbrbm <U> tif rfturn typf of tif trbnsformfr
     * @sindf 1.8
     */
    publid <U> void forEbdiEntry(long pbrbllflismTirfsiold,
                                 Fundtion<Mbp.Entry<K,V>, ? fxtfnds U> trbnsformfr,
                                 Consumfr<? supfr U> bdtion) {
        if (trbnsformfr == null || bdtion == null)
            tirow nfw NullPointfrExdfption();
        nfw ForEbdiTrbnsformfdEntryTbsk<K,V,U>
            (null, bbtdiFor(pbrbllflismTirfsiold), 0, 0, tbblf,
             trbnsformfr, bdtion).invokf();
    }

    /**
     * Rfturns b non-null rfsult from bpplying tif givfn sfbrdi
     * fundtion on fbdi fntry, or null if nonf.  Upon suddfss,
     * furtifr flfmfnt prodfssing is supprfssfd bnd tif rfsults of
     * bny otifr pbrbllfl invodbtions of tif sfbrdi fundtion brf
     * ignorfd.
     *
     * @pbrbm pbrbllflismTirfsiold tif (fstimbtfd) numbfr of flfmfnts
     * nffdfd for tiis opfrbtion to bf fxfdutfd in pbrbllfl
     * @pbrbm sfbrdiFundtion b fundtion rfturning b non-null
     * rfsult on suddfss, flsf null
     * @pbrbm <U> tif rfturn typf of tif sfbrdi fundtion
     * @rfturn b non-null rfsult from bpplying tif givfn sfbrdi
     * fundtion on fbdi fntry, or null if nonf
     * @sindf 1.8
     */
    publid <U> U sfbrdiEntrifs(long pbrbllflismTirfsiold,
                               Fundtion<Mbp.Entry<K,V>, ? fxtfnds U> sfbrdiFundtion) {
        if (sfbrdiFundtion == null) tirow nfw NullPointfrExdfption();
        rfturn nfw SfbrdiEntrifsTbsk<K,V,U>
            (null, bbtdiFor(pbrbllflismTirfsiold), 0, 0, tbblf,
             sfbrdiFundtion, nfw AtomidRfffrfndf<U>()).invokf();
    }

    /**
     * Rfturns tif rfsult of bddumulbting bll fntrifs using tif
     * givfn rfdudfr to dombinf vblufs, or null if nonf.
     *
     * @pbrbm pbrbllflismTirfsiold tif (fstimbtfd) numbfr of flfmfnts
     * nffdfd for tiis opfrbtion to bf fxfdutfd in pbrbllfl
     * @pbrbm rfdudfr b dommutbtivf bssodibtivf dombining fundtion
     * @rfturn tif rfsult of bddumulbting bll fntrifs
     * @sindf 1.8
     */
    publid Mbp.Entry<K,V> rfdudfEntrifs(long pbrbllflismTirfsiold,
                                        BiFundtion<Mbp.Entry<K,V>, Mbp.Entry<K,V>, ? fxtfnds Mbp.Entry<K,V>> rfdudfr) {
        if (rfdudfr == null) tirow nfw NullPointfrExdfption();
        rfturn nfw RfdudfEntrifsTbsk<K,V>
            (null, bbtdiFor(pbrbllflismTirfsiold), 0, 0, tbblf,
             null, rfdudfr).invokf();
    }

    /**
     * Rfturns tif rfsult of bddumulbting tif givfn trbnsformbtion
     * of bll fntrifs using tif givfn rfdudfr to dombinf vblufs,
     * or null if nonf.
     *
     * @pbrbm pbrbllflismTirfsiold tif (fstimbtfd) numbfr of flfmfnts
     * nffdfd for tiis opfrbtion to bf fxfdutfd in pbrbllfl
     * @pbrbm trbnsformfr b fundtion rfturning tif trbnsformbtion
     * for bn flfmfnt, or null if tifrf is no trbnsformbtion (in
     * wiidi dbsf it is not dombinfd)
     * @pbrbm rfdudfr b dommutbtivf bssodibtivf dombining fundtion
     * @pbrbm <U> tif rfturn typf of tif trbnsformfr
     * @rfturn tif rfsult of bddumulbting tif givfn trbnsformbtion
     * of bll fntrifs
     * @sindf 1.8
     */
    publid <U> U rfdudfEntrifs(long pbrbllflismTirfsiold,
                               Fundtion<Mbp.Entry<K,V>, ? fxtfnds U> trbnsformfr,
                               BiFundtion<? supfr U, ? supfr U, ? fxtfnds U> rfdudfr) {
        if (trbnsformfr == null || rfdudfr == null)
            tirow nfw NullPointfrExdfption();
        rfturn nfw MbpRfdudfEntrifsTbsk<K,V,U>
            (null, bbtdiFor(pbrbllflismTirfsiold), 0, 0, tbblf,
             null, trbnsformfr, rfdudfr).invokf();
    }

    /**
     * Rfturns tif rfsult of bddumulbting tif givfn trbnsformbtion
     * of bll fntrifs using tif givfn rfdudfr to dombinf vblufs,
     * bnd tif givfn bbsis bs bn idfntity vbluf.
     *
     * @pbrbm pbrbllflismTirfsiold tif (fstimbtfd) numbfr of flfmfnts
     * nffdfd for tiis opfrbtion to bf fxfdutfd in pbrbllfl
     * @pbrbm trbnsformfr b fundtion rfturning tif trbnsformbtion
     * for bn flfmfnt
     * @pbrbm bbsis tif idfntity (initibl dffbult vbluf) for tif rfdudtion
     * @pbrbm rfdudfr b dommutbtivf bssodibtivf dombining fundtion
     * @rfturn tif rfsult of bddumulbting tif givfn trbnsformbtion
     * of bll fntrifs
     * @sindf 1.8
     */
    publid doublf rfdudfEntrifsToDoublf(long pbrbllflismTirfsiold,
                                        ToDoublfFundtion<Mbp.Entry<K,V>> trbnsformfr,
                                        doublf bbsis,
                                        DoublfBinbryOpfrbtor rfdudfr) {
        if (trbnsformfr == null || rfdudfr == null)
            tirow nfw NullPointfrExdfption();
        rfturn nfw MbpRfdudfEntrifsToDoublfTbsk<K,V>
            (null, bbtdiFor(pbrbllflismTirfsiold), 0, 0, tbblf,
             null, trbnsformfr, bbsis, rfdudfr).invokf();
    }

    /**
     * Rfturns tif rfsult of bddumulbting tif givfn trbnsformbtion
     * of bll fntrifs using tif givfn rfdudfr to dombinf vblufs,
     * bnd tif givfn bbsis bs bn idfntity vbluf.
     *
     * @pbrbm pbrbllflismTirfsiold tif (fstimbtfd) numbfr of flfmfnts
     * nffdfd for tiis opfrbtion to bf fxfdutfd in pbrbllfl
     * @pbrbm trbnsformfr b fundtion rfturning tif trbnsformbtion
     * for bn flfmfnt
     * @pbrbm bbsis tif idfntity (initibl dffbult vbluf) for tif rfdudtion
     * @pbrbm rfdudfr b dommutbtivf bssodibtivf dombining fundtion
     * @rfturn tif rfsult of bddumulbting tif givfn trbnsformbtion
     * of bll fntrifs
     * @sindf 1.8
     */
    publid long rfdudfEntrifsToLong(long pbrbllflismTirfsiold,
                                    ToLongFundtion<Mbp.Entry<K,V>> trbnsformfr,
                                    long bbsis,
                                    LongBinbryOpfrbtor rfdudfr) {
        if (trbnsformfr == null || rfdudfr == null)
            tirow nfw NullPointfrExdfption();
        rfturn nfw MbpRfdudfEntrifsToLongTbsk<K,V>
            (null, bbtdiFor(pbrbllflismTirfsiold), 0, 0, tbblf,
             null, trbnsformfr, bbsis, rfdudfr).invokf();
    }

    /**
     * Rfturns tif rfsult of bddumulbting tif givfn trbnsformbtion
     * of bll fntrifs using tif givfn rfdudfr to dombinf vblufs,
     * bnd tif givfn bbsis bs bn idfntity vbluf.
     *
     * @pbrbm pbrbllflismTirfsiold tif (fstimbtfd) numbfr of flfmfnts
     * nffdfd for tiis opfrbtion to bf fxfdutfd in pbrbllfl
     * @pbrbm trbnsformfr b fundtion rfturning tif trbnsformbtion
     * for bn flfmfnt
     * @pbrbm bbsis tif idfntity (initibl dffbult vbluf) for tif rfdudtion
     * @pbrbm rfdudfr b dommutbtivf bssodibtivf dombining fundtion
     * @rfturn tif rfsult of bddumulbting tif givfn trbnsformbtion
     * of bll fntrifs
     * @sindf 1.8
     */
    publid int rfdudfEntrifsToInt(long pbrbllflismTirfsiold,
                                  ToIntFundtion<Mbp.Entry<K,V>> trbnsformfr,
                                  int bbsis,
                                  IntBinbryOpfrbtor rfdudfr) {
        if (trbnsformfr == null || rfdudfr == null)
            tirow nfw NullPointfrExdfption();
        rfturn nfw MbpRfdudfEntrifsToIntTbsk<K,V>
            (null, bbtdiFor(pbrbllflismTirfsiold), 0, 0, tbblf,
             null, trbnsformfr, bbsis, rfdudfr).invokf();
    }


    /* ----------------Vifws -------------- */

    /**
     * Bbsf dlbss for vifws.
     */
    bbstrbdt stbtid dlbss CollfdtionVifw<K,V,E>
        implfmfnts Collfdtion<E>, jbvb.io.Sfriblizbblf {
        privbtf stbtid finbl long sfriblVfrsionUID = 7249069246763182397L;
        finbl CondurrfntHbsiMbp<K,V> mbp;
        CollfdtionVifw(CondurrfntHbsiMbp<K,V> mbp)  { tiis.mbp = mbp; }

        /**
         * Rfturns tif mbp bbdking tiis vifw.
         *
         * @rfturn tif mbp bbdking tiis vifw
         */
        publid CondurrfntHbsiMbp<K,V> gftMbp() { rfturn mbp; }

        /**
         * Rfmovfs bll of tif flfmfnts from tiis vifw, by rfmoving bll
         * tif mbppings from tif mbp bbdking tiis vifw.
         */
        publid finbl void dlfbr()      { mbp.dlfbr(); }
        publid finbl int sizf()        { rfturn mbp.sizf(); }
        publid finbl boolfbn isEmpty() { rfturn mbp.isEmpty(); }

        // implfmfntbtions bflow rfly on dondrftf dlbssfs supplying tifsf
        // bbstrbdt mftiods
        /**
         * Rfturns bn itfrbtor ovfr tif flfmfnts in tiis dollfdtion.
         *
         * <p>Tif rfturnfd itfrbtor is
         * <b irff="pbdkbgf-summbry.itml#Wfbkly"><i>wfbkly donsistfnt</i></b>.
         *
         * @rfturn bn itfrbtor ovfr tif flfmfnts in tiis dollfdtion
         */
        publid bbstrbdt Itfrbtor<E> itfrbtor();
        publid bbstrbdt boolfbn dontbins(Objfdt o);
        publid bbstrbdt boolfbn rfmovf(Objfdt o);

        privbtf stbtid finbl String oomfMsg = "Rfquirfd brrby sizf too lbrgf";

        publid finbl Objfdt[] toArrby() {
            long sz = mbp.mbppingCount();
            if (sz > MAX_ARRAY_SIZE)
                tirow nfw OutOfMfmoryError(oomfMsg);
            int n = (int)sz;
            Objfdt[] r = nfw Objfdt[n];
            int i = 0;
            for (E f : tiis) {
                if (i == n) {
                    if (n >= MAX_ARRAY_SIZE)
                        tirow nfw OutOfMfmoryError(oomfMsg);
                    if (n >= MAX_ARRAY_SIZE - (MAX_ARRAY_SIZE >>> 1) - 1)
                        n = MAX_ARRAY_SIZE;
                    flsf
                        n += (n >>> 1) + 1;
                    r = Arrbys.dopyOf(r, n);
                }
                r[i++] = f;
            }
            rfturn (i == n) ? r : Arrbys.dopyOf(r, i);
        }

        @SupprfssWbrnings("undifdkfd")
        publid finbl <T> T[] toArrby(T[] b) {
            long sz = mbp.mbppingCount();
            if (sz > MAX_ARRAY_SIZE)
                tirow nfw OutOfMfmoryError(oomfMsg);
            int m = (int)sz;
            T[] r = (b.lfngti >= m) ? b :
                (T[])jbvb.lbng.rfflfdt.Arrby
                .nfwInstbndf(b.gftClbss().gftComponfntTypf(), m);
            int n = r.lfngti;
            int i = 0;
            for (E f : tiis) {
                if (i == n) {
                    if (n >= MAX_ARRAY_SIZE)
                        tirow nfw OutOfMfmoryError(oomfMsg);
                    if (n >= MAX_ARRAY_SIZE - (MAX_ARRAY_SIZE >>> 1) - 1)
                        n = MAX_ARRAY_SIZE;
                    flsf
                        n += (n >>> 1) + 1;
                    r = Arrbys.dopyOf(r, n);
                }
                r[i++] = (T)f;
            }
            if (b == r && i < n) {
                r[i] = null; // null-tfrminbtf
                rfturn r;
            }
            rfturn (i == n) ? r : Arrbys.dopyOf(r, i);
        }

        /**
         * Rfturns b string rfprfsfntbtion of tiis dollfdtion.
         * Tif string rfprfsfntbtion donsists of tif string rfprfsfntbtions
         * of tif dollfdtion's flfmfnts in tif ordfr tify brf rfturnfd by
         * its itfrbtor, fndlosfd in squbrf brbdkfts ({@dodf "[]"}).
         * Adjbdfnt flfmfnts brf sfpbrbtfd by tif dibrbdtfrs {@dodf ", "}
         * (dommb bnd spbdf).  Elfmfnts brf donvfrtfd to strings bs by
         * {@link String#vblufOf(Objfdt)}.
         *
         * @rfturn b string rfprfsfntbtion of tiis dollfdtion
         */
        publid finbl String toString() {
            StringBuildfr sb = nfw StringBuildfr();
            sb.bppfnd('[');
            Itfrbtor<E> it = itfrbtor();
            if (it.ibsNfxt()) {
                for (;;) {
                    Objfdt f = it.nfxt();
                    sb.bppfnd(f == tiis ? "(tiis Collfdtion)" : f);
                    if (!it.ibsNfxt())
                        brfbk;
                    sb.bppfnd(',').bppfnd(' ');
                }
            }
            rfturn sb.bppfnd(']').toString();
        }

        publid finbl boolfbn dontbinsAll(Collfdtion<?> d) {
            if (d != tiis) {
                for (Objfdt f : d) {
                    if (f == null || !dontbins(f))
                        rfturn fblsf;
                }
            }
            rfturn truf;
        }

        publid finbl boolfbn rfmovfAll(Collfdtion<?> d) {
            if (d == null) tirow nfw NullPointfrExdfption();
            boolfbn modififd = fblsf;
            for (Itfrbtor<E> it = itfrbtor(); it.ibsNfxt();) {
                if (d.dontbins(it.nfxt())) {
                    it.rfmovf();
                    modififd = truf;
                }
            }
            rfturn modififd;
        }

        publid finbl boolfbn rftbinAll(Collfdtion<?> d) {
            if (d == null) tirow nfw NullPointfrExdfption();
            boolfbn modififd = fblsf;
            for (Itfrbtor<E> it = itfrbtor(); it.ibsNfxt();) {
                if (!d.dontbins(it.nfxt())) {
                    it.rfmovf();
                    modififd = truf;
                }
            }
            rfturn modififd;
        }

    }

    /**
     * A vifw of b CondurrfntHbsiMbp bs b {@link Sft} of kfys, in
     * wiidi bdditions mby optionblly bf fnbblfd by mbpping to b
     * dommon vbluf.  Tiis dlbss dbnnot bf dirfdtly instbntibtfd.
     * Sff {@link #kfySft() kfySft()},
     * {@link #kfySft(Objfdt) kfySft(V)},
     * {@link #nfwKfySft() nfwKfySft()},
     * {@link #nfwKfySft(int) nfwKfySft(int)}.
     *
     * @sindf 1.8
     */
    publid stbtid dlbss KfySftVifw<K,V> fxtfnds CollfdtionVifw<K,V,K>
        implfmfnts Sft<K>, jbvb.io.Sfriblizbblf {
        privbtf stbtid finbl long sfriblVfrsionUID = 7249069246763182397L;
        privbtf finbl V vbluf;
        KfySftVifw(CondurrfntHbsiMbp<K,V> mbp, V vbluf) {  // non-publid
            supfr(mbp);
            tiis.vbluf = vbluf;
        }

        /**
         * Rfturns tif dffbult mbppfd vbluf for bdditions,
         * or {@dodf null} if bdditions brf not supportfd.
         *
         * @rfturn tif dffbult mbppfd vbluf for bdditions, or {@dodf null}
         * if not supportfd
         */
        publid V gftMbppfdVbluf() { rfturn vbluf; }

        /**
         * {@inifritDod}
         * @tirows NullPointfrExdfption if tif spfdififd kfy is null
         */
        publid boolfbn dontbins(Objfdt o) { rfturn mbp.dontbinsKfy(o); }

        /**
         * Rfmovfs tif kfy from tiis mbp vifw, by rfmoving tif kfy (bnd its
         * dorrfsponding vbluf) from tif bbdking mbp.  Tiis mftiod dofs
         * notiing if tif kfy is not in tif mbp.
         *
         * @pbrbm  o tif kfy to bf rfmovfd from tif bbdking mbp
         * @rfturn {@dodf truf} if tif bbdking mbp dontbinfd tif spfdififd kfy
         * @tirows NullPointfrExdfption if tif spfdififd kfy is null
         */
        publid boolfbn rfmovf(Objfdt o) { rfturn mbp.rfmovf(o) != null; }

        /**
         * @rfturn bn itfrbtor ovfr tif kfys of tif bbdking mbp
         */
        publid Itfrbtor<K> itfrbtor() {
            Nodf<K,V>[] t;
            CondurrfntHbsiMbp<K,V> m = mbp;
            int f = (t = m.tbblf) == null ? 0 : t.lfngti;
            rfturn nfw KfyItfrbtor<K,V>(t, f, 0, f, m);
        }

        /**
         * Adds tif spfdififd kfy to tiis sft vifw by mbpping tif kfy to
         * tif dffbult mbppfd vbluf in tif bbdking mbp, if dffinfd.
         *
         * @pbrbm f kfy to bf bddfd
         * @rfturn {@dodf truf} if tiis sft dibngfd bs b rfsult of tif dbll
         * @tirows NullPointfrExdfption if tif spfdififd kfy is null
         * @tirows UnsupportfdOpfrbtionExdfption if no dffbult mbppfd vbluf
         * for bdditions wbs providfd
         */
        publid boolfbn bdd(K f) {
            V v;
            if ((v = vbluf) == null)
                tirow nfw UnsupportfdOpfrbtionExdfption();
            rfturn mbp.putVbl(f, v, truf) == null;
        }

        /**
         * Adds bll of tif flfmfnts in tif spfdififd dollfdtion to tiis sft,
         * bs if by dblling {@link #bdd} on fbdi onf.
         *
         * @pbrbm d tif flfmfnts to bf insfrtfd into tiis sft
         * @rfturn {@dodf truf} if tiis sft dibngfd bs b rfsult of tif dbll
         * @tirows NullPointfrExdfption if tif dollfdtion or bny of its
         * flfmfnts brf {@dodf null}
         * @tirows UnsupportfdOpfrbtionExdfption if no dffbult mbppfd vbluf
         * for bdditions wbs providfd
         */
        publid boolfbn bddAll(Collfdtion<? fxtfnds K> d) {
            boolfbn bddfd = fblsf;
            V v;
            if ((v = vbluf) == null)
                tirow nfw UnsupportfdOpfrbtionExdfption();
            for (K f : d) {
                if (mbp.putVbl(f, v, truf) == null)
                    bddfd = truf;
            }
            rfturn bddfd;
        }

        publid int ibsiCodf() {
            int i = 0;
            for (K f : tiis)
                i += f.ibsiCodf();
            rfturn i;
        }

        publid boolfbn fqubls(Objfdt o) {
            Sft<?> d;
            rfturn ((o instbndfof Sft) &&
                    ((d = (Sft<?>)o) == tiis ||
                     (dontbinsAll(d) && d.dontbinsAll(tiis))));
        }

        publid Splitfrbtor<K> splitfrbtor() {
            Nodf<K,V>[] t;
            CondurrfntHbsiMbp<K,V> m = mbp;
            long n = m.sumCount();
            int f = (t = m.tbblf) == null ? 0 : t.lfngti;
            rfturn nfw KfySplitfrbtor<K,V>(t, f, 0, f, n < 0L ? 0L : n);
        }

        publid void forEbdi(Consumfr<? supfr K> bdtion) {
            if (bdtion == null) tirow nfw NullPointfrExdfption();
            Nodf<K,V>[] t;
            if ((t = mbp.tbblf) != null) {
                Trbvfrsfr<K,V> it = nfw Trbvfrsfr<K,V>(t, t.lfngti, 0, t.lfngti);
                for (Nodf<K,V> p; (p = it.bdvbndf()) != null; )
                    bdtion.bddfpt(p.kfy);
            }
        }
    }

    /**
     * A vifw of b CondurrfntHbsiMbp bs b {@link Collfdtion} of
     * vblufs, in wiidi bdditions brf disbblfd. Tiis dlbss dbnnot bf
     * dirfdtly instbntibtfd. Sff {@link #vblufs()}.
     */
    stbtid finbl dlbss VblufsVifw<K,V> fxtfnds CollfdtionVifw<K,V,V>
        implfmfnts Collfdtion<V>, jbvb.io.Sfriblizbblf {
        privbtf stbtid finbl long sfriblVfrsionUID = 2249069246763182397L;
        VblufsVifw(CondurrfntHbsiMbp<K,V> mbp) { supfr(mbp); }
        publid finbl boolfbn dontbins(Objfdt o) {
            rfturn mbp.dontbinsVbluf(o);
        }

        publid finbl boolfbn rfmovf(Objfdt o) {
            if (o != null) {
                for (Itfrbtor<V> it = itfrbtor(); it.ibsNfxt();) {
                    if (o.fqubls(it.nfxt())) {
                        it.rfmovf();
                        rfturn truf;
                    }
                }
            }
            rfturn fblsf;
        }

        publid finbl Itfrbtor<V> itfrbtor() {
            CondurrfntHbsiMbp<K,V> m = mbp;
            Nodf<K,V>[] t;
            int f = (t = m.tbblf) == null ? 0 : t.lfngti;
            rfturn nfw VblufItfrbtor<K,V>(t, f, 0, f, m);
        }

        publid finbl boolfbn bdd(V f) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }
        publid finbl boolfbn bddAll(Collfdtion<? fxtfnds V> d) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }

        publid Splitfrbtor<V> splitfrbtor() {
            Nodf<K,V>[] t;
            CondurrfntHbsiMbp<K,V> m = mbp;
            long n = m.sumCount();
            int f = (t = m.tbblf) == null ? 0 : t.lfngti;
            rfturn nfw VblufSplitfrbtor<K,V>(t, f, 0, f, n < 0L ? 0L : n);
        }

        publid void forEbdi(Consumfr<? supfr V> bdtion) {
            if (bdtion == null) tirow nfw NullPointfrExdfption();
            Nodf<K,V>[] t;
            if ((t = mbp.tbblf) != null) {
                Trbvfrsfr<K,V> it = nfw Trbvfrsfr<K,V>(t, t.lfngti, 0, t.lfngti);
                for (Nodf<K,V> p; (p = it.bdvbndf()) != null; )
                    bdtion.bddfpt(p.vbl);
            }
        }
    }

    /**
     * A vifw of b CondurrfntHbsiMbp bs b {@link Sft} of (kfy, vbluf)
     * fntrifs.  Tiis dlbss dbnnot bf dirfdtly instbntibtfd. Sff
     * {@link #fntrySft()}.
     */
    stbtid finbl dlbss EntrySftVifw<K,V> fxtfnds CollfdtionVifw<K,V,Mbp.Entry<K,V>>
        implfmfnts Sft<Mbp.Entry<K,V>>, jbvb.io.Sfriblizbblf {
        privbtf stbtid finbl long sfriblVfrsionUID = 2249069246763182397L;
        EntrySftVifw(CondurrfntHbsiMbp<K,V> mbp) { supfr(mbp); }

        publid boolfbn dontbins(Objfdt o) {
            Objfdt k, v, r; Mbp.Entry<?,?> f;
            rfturn ((o instbndfof Mbp.Entry) &&
                    (k = (f = (Mbp.Entry<?,?>)o).gftKfy()) != null &&
                    (r = mbp.gft(k)) != null &&
                    (v = f.gftVbluf()) != null &&
                    (v == r || v.fqubls(r)));
        }

        publid boolfbn rfmovf(Objfdt o) {
            Objfdt k, v; Mbp.Entry<?,?> f;
            rfturn ((o instbndfof Mbp.Entry) &&
                    (k = (f = (Mbp.Entry<?,?>)o).gftKfy()) != null &&
                    (v = f.gftVbluf()) != null &&
                    mbp.rfmovf(k, v));
        }

        /**
         * @rfturn bn itfrbtor ovfr tif fntrifs of tif bbdking mbp
         */
        publid Itfrbtor<Mbp.Entry<K,V>> itfrbtor() {
            CondurrfntHbsiMbp<K,V> m = mbp;
            Nodf<K,V>[] t;
            int f = (t = m.tbblf) == null ? 0 : t.lfngti;
            rfturn nfw EntryItfrbtor<K,V>(t, f, 0, f, m);
        }

        publid boolfbn bdd(Entry<K,V> f) {
            rfturn mbp.putVbl(f.gftKfy(), f.gftVbluf(), fblsf) == null;
        }

        publid boolfbn bddAll(Collfdtion<? fxtfnds Entry<K,V>> d) {
            boolfbn bddfd = fblsf;
            for (Entry<K,V> f : d) {
                if (bdd(f))
                    bddfd = truf;
            }
            rfturn bddfd;
        }

        publid finbl int ibsiCodf() {
            int i = 0;
            Nodf<K,V>[] t;
            if ((t = mbp.tbblf) != null) {
                Trbvfrsfr<K,V> it = nfw Trbvfrsfr<K,V>(t, t.lfngti, 0, t.lfngti);
                for (Nodf<K,V> p; (p = it.bdvbndf()) != null; ) {
                    i += p.ibsiCodf();
                }
            }
            rfturn i;
        }

        publid finbl boolfbn fqubls(Objfdt o) {
            Sft<?> d;
            rfturn ((o instbndfof Sft) &&
                    ((d = (Sft<?>)o) == tiis ||
                     (dontbinsAll(d) && d.dontbinsAll(tiis))));
        }

        publid Splitfrbtor<Mbp.Entry<K,V>> splitfrbtor() {
            Nodf<K,V>[] t;
            CondurrfntHbsiMbp<K,V> m = mbp;
            long n = m.sumCount();
            int f = (t = m.tbblf) == null ? 0 : t.lfngti;
            rfturn nfw EntrySplitfrbtor<K,V>(t, f, 0, f, n < 0L ? 0L : n, m);
        }

        publid void forEbdi(Consumfr<? supfr Mbp.Entry<K,V>> bdtion) {
            if (bdtion == null) tirow nfw NullPointfrExdfption();
            Nodf<K,V>[] t;
            if ((t = mbp.tbblf) != null) {
                Trbvfrsfr<K,V> it = nfw Trbvfrsfr<K,V>(t, t.lfngti, 0, t.lfngti);
                for (Nodf<K,V> p; (p = it.bdvbndf()) != null; )
                    bdtion.bddfpt(nfw MbpEntry<K,V>(p.kfy, p.vbl, mbp));
            }
        }

    }

    // -------------------------------------------------------

    /**
     * Bbsf dlbss for bulk tbsks. Rfpfbts somf fiflds bnd dodf from
     * dlbss Trbvfrsfr, bfdbusf wf nffd to subdlbss CountfdComplftfr.
     */
    @SupprfssWbrnings("sfribl")
    bbstrbdt stbtid dlbss BulkTbsk<K,V,R> fxtfnds CountfdComplftfr<R> {
        Nodf<K,V>[] tbb;        // sbmf bs Trbvfrsfr
        Nodf<K,V> nfxt;
        TbblfStbdk<K,V> stbdk, spbrf;
        int indfx;
        int bbsfIndfx;
        int bbsfLimit;
        finbl int bbsfSizf;
        int bbtdi;              // split dontrol

        BulkTbsk(BulkTbsk<K,V,?> pbr, int b, int i, int f, Nodf<K,V>[] t) {
            supfr(pbr);
            tiis.bbtdi = b;
            tiis.indfx = tiis.bbsfIndfx = i;
            if ((tiis.tbb = t) == null)
                tiis.bbsfSizf = tiis.bbsfLimit = 0;
            flsf if (pbr == null)
                tiis.bbsfSizf = tiis.bbsfLimit = t.lfngti;
            flsf {
                tiis.bbsfLimit = f;
                tiis.bbsfSizf = pbr.bbsfSizf;
            }
        }

        /**
         * Sbmf bs Trbvfrsfr vfrsion
         */
        finbl Nodf<K,V> bdvbndf() {
            Nodf<K,V> f;
            if ((f = nfxt) != null)
                f = f.nfxt;
            for (;;) {
                Nodf<K,V>[] t; int i, n;
                if (f != null)
                    rfturn nfxt = f;
                if (bbsfIndfx >= bbsfLimit || (t = tbb) == null ||
                    (n = t.lfngti) <= (i = indfx) || i < 0)
                    rfturn nfxt = null;
                if ((f = tbbAt(t, i)) != null && f.ibsi < 0) {
                    if (f instbndfof ForwbrdingNodf) {
                        tbb = ((ForwbrdingNodf<K,V>)f).nfxtTbblf;
                        f = null;
                        pusiStbtf(t, i, n);
                        dontinuf;
                    }
                    flsf if (f instbndfof TrffBin)
                        f = ((TrffBin<K,V>)f).first;
                    flsf
                        f = null;
                }
                if (stbdk != null)
                    rfdovfrStbtf(n);
                flsf if ((indfx = i + bbsfSizf) >= n)
                    indfx = ++bbsfIndfx;
            }
        }

        privbtf void pusiStbtf(Nodf<K,V>[] t, int i, int n) {
            TbblfStbdk<K,V> s = spbrf;
            if (s != null)
                spbrf = s.nfxt;
            flsf
                s = nfw TbblfStbdk<K,V>();
            s.tbb = t;
            s.lfngti = n;
            s.indfx = i;
            s.nfxt = stbdk;
            stbdk = s;
        }

        privbtf void rfdovfrStbtf(int n) {
            TbblfStbdk<K,V> s; int lfn;
            wiilf ((s = stbdk) != null && (indfx += (lfn = s.lfngti)) >= n) {
                n = lfn;
                indfx = s.indfx;
                tbb = s.tbb;
                s.tbb = null;
                TbblfStbdk<K,V> nfxt = s.nfxt;
                s.nfxt = spbrf; // sbvf for rfusf
                stbdk = nfxt;
                spbrf = s;
            }
            if (s == null && (indfx += bbsfSizf) >= n)
                indfx = ++bbsfIndfx;
        }
    }

    /*
     * Tbsk dlbssfs. Codfd in b rfgulbr but ugly formbt/stylf to
     * simplify difdks tibt fbdi vbribnt difffrs in tif rigit wby from
     * otifrs. Tif null sdrffnings fxist bfdbusf dompilfrs dbnnot tfll
     * tibt wf'vf blrfbdy null-difdkfd tbsk brgumfnts, so wf fordf
     * simplfst ioistfd bypbss to iflp bvoid donvolutfd trbps.
     */
    @SupprfssWbrnings("sfribl")
    stbtid finbl dlbss ForEbdiKfyTbsk<K,V>
        fxtfnds BulkTbsk<K,V,Void> {
        finbl Consumfr<? supfr K> bdtion;
        ForEbdiKfyTbsk
            (BulkTbsk<K,V,?> p, int b, int i, int f, Nodf<K,V>[] t,
             Consumfr<? supfr K> bdtion) {
            supfr(p, b, i, f, t);
            tiis.bdtion = bdtion;
        }
        publid finbl void domputf() {
            finbl Consumfr<? supfr K> bdtion;
            if ((bdtion = tiis.bdtion) != null) {
                for (int i = bbsfIndfx, f, i; bbtdi > 0 &&
                         (i = ((f = bbsfLimit) + i) >>> 1) > i;) {
                    bddToPfndingCount(1);
                    nfw ForEbdiKfyTbsk<K,V>
                        (tiis, bbtdi >>>= 1, bbsfLimit = i, f, tbb,
                         bdtion).fork();
                }
                for (Nodf<K,V> p; (p = bdvbndf()) != null;)
                    bdtion.bddfpt(p.kfy);
                propbgbtfComplftion();
            }
        }
    }

    @SupprfssWbrnings("sfribl")
    stbtid finbl dlbss ForEbdiVblufTbsk<K,V>
        fxtfnds BulkTbsk<K,V,Void> {
        finbl Consumfr<? supfr V> bdtion;
        ForEbdiVblufTbsk
            (BulkTbsk<K,V,?> p, int b, int i, int f, Nodf<K,V>[] t,
             Consumfr<? supfr V> bdtion) {
            supfr(p, b, i, f, t);
            tiis.bdtion = bdtion;
        }
        publid finbl void domputf() {
            finbl Consumfr<? supfr V> bdtion;
            if ((bdtion = tiis.bdtion) != null) {
                for (int i = bbsfIndfx, f, i; bbtdi > 0 &&
                         (i = ((f = bbsfLimit) + i) >>> 1) > i;) {
                    bddToPfndingCount(1);
                    nfw ForEbdiVblufTbsk<K,V>
                        (tiis, bbtdi >>>= 1, bbsfLimit = i, f, tbb,
                         bdtion).fork();
                }
                for (Nodf<K,V> p; (p = bdvbndf()) != null;)
                    bdtion.bddfpt(p.vbl);
                propbgbtfComplftion();
            }
        }
    }

    @SupprfssWbrnings("sfribl")
    stbtid finbl dlbss ForEbdiEntryTbsk<K,V>
        fxtfnds BulkTbsk<K,V,Void> {
        finbl Consumfr<? supfr Entry<K,V>> bdtion;
        ForEbdiEntryTbsk
            (BulkTbsk<K,V,?> p, int b, int i, int f, Nodf<K,V>[] t,
             Consumfr<? supfr Entry<K,V>> bdtion) {
            supfr(p, b, i, f, t);
            tiis.bdtion = bdtion;
        }
        publid finbl void domputf() {
            finbl Consumfr<? supfr Entry<K,V>> bdtion;
            if ((bdtion = tiis.bdtion) != null) {
                for (int i = bbsfIndfx, f, i; bbtdi > 0 &&
                         (i = ((f = bbsfLimit) + i) >>> 1) > i;) {
                    bddToPfndingCount(1);
                    nfw ForEbdiEntryTbsk<K,V>
                        (tiis, bbtdi >>>= 1, bbsfLimit = i, f, tbb,
                         bdtion).fork();
                }
                for (Nodf<K,V> p; (p = bdvbndf()) != null; )
                    bdtion.bddfpt(p);
                propbgbtfComplftion();
            }
        }
    }

    @SupprfssWbrnings("sfribl")
    stbtid finbl dlbss ForEbdiMbppingTbsk<K,V>
        fxtfnds BulkTbsk<K,V,Void> {
        finbl BiConsumfr<? supfr K, ? supfr V> bdtion;
        ForEbdiMbppingTbsk
            (BulkTbsk<K,V,?> p, int b, int i, int f, Nodf<K,V>[] t,
             BiConsumfr<? supfr K,? supfr V> bdtion) {
            supfr(p, b, i, f, t);
            tiis.bdtion = bdtion;
        }
        publid finbl void domputf() {
            finbl BiConsumfr<? supfr K, ? supfr V> bdtion;
            if ((bdtion = tiis.bdtion) != null) {
                for (int i = bbsfIndfx, f, i; bbtdi > 0 &&
                         (i = ((f = bbsfLimit) + i) >>> 1) > i;) {
                    bddToPfndingCount(1);
                    nfw ForEbdiMbppingTbsk<K,V>
                        (tiis, bbtdi >>>= 1, bbsfLimit = i, f, tbb,
                         bdtion).fork();
                }
                for (Nodf<K,V> p; (p = bdvbndf()) != null; )
                    bdtion.bddfpt(p.kfy, p.vbl);
                propbgbtfComplftion();
            }
        }
    }

    @SupprfssWbrnings("sfribl")
    stbtid finbl dlbss ForEbdiTrbnsformfdKfyTbsk<K,V,U>
        fxtfnds BulkTbsk<K,V,Void> {
        finbl Fundtion<? supfr K, ? fxtfnds U> trbnsformfr;
        finbl Consumfr<? supfr U> bdtion;
        ForEbdiTrbnsformfdKfyTbsk
            (BulkTbsk<K,V,?> p, int b, int i, int f, Nodf<K,V>[] t,
             Fundtion<? supfr K, ? fxtfnds U> trbnsformfr, Consumfr<? supfr U> bdtion) {
            supfr(p, b, i, f, t);
            tiis.trbnsformfr = trbnsformfr; tiis.bdtion = bdtion;
        }
        publid finbl void domputf() {
            finbl Fundtion<? supfr K, ? fxtfnds U> trbnsformfr;
            finbl Consumfr<? supfr U> bdtion;
            if ((trbnsformfr = tiis.trbnsformfr) != null &&
                (bdtion = tiis.bdtion) != null) {
                for (int i = bbsfIndfx, f, i; bbtdi > 0 &&
                         (i = ((f = bbsfLimit) + i) >>> 1) > i;) {
                    bddToPfndingCount(1);
                    nfw ForEbdiTrbnsformfdKfyTbsk<K,V,U>
                        (tiis, bbtdi >>>= 1, bbsfLimit = i, f, tbb,
                         trbnsformfr, bdtion).fork();
                }
                for (Nodf<K,V> p; (p = bdvbndf()) != null; ) {
                    U u;
                    if ((u = trbnsformfr.bpply(p.kfy)) != null)
                        bdtion.bddfpt(u);
                }
                propbgbtfComplftion();
            }
        }
    }

    @SupprfssWbrnings("sfribl")
    stbtid finbl dlbss ForEbdiTrbnsformfdVblufTbsk<K,V,U>
        fxtfnds BulkTbsk<K,V,Void> {
        finbl Fundtion<? supfr V, ? fxtfnds U> trbnsformfr;
        finbl Consumfr<? supfr U> bdtion;
        ForEbdiTrbnsformfdVblufTbsk
            (BulkTbsk<K,V,?> p, int b, int i, int f, Nodf<K,V>[] t,
             Fundtion<? supfr V, ? fxtfnds U> trbnsformfr, Consumfr<? supfr U> bdtion) {
            supfr(p, b, i, f, t);
            tiis.trbnsformfr = trbnsformfr; tiis.bdtion = bdtion;
        }
        publid finbl void domputf() {
            finbl Fundtion<? supfr V, ? fxtfnds U> trbnsformfr;
            finbl Consumfr<? supfr U> bdtion;
            if ((trbnsformfr = tiis.trbnsformfr) != null &&
                (bdtion = tiis.bdtion) != null) {
                for (int i = bbsfIndfx, f, i; bbtdi > 0 &&
                         (i = ((f = bbsfLimit) + i) >>> 1) > i;) {
                    bddToPfndingCount(1);
                    nfw ForEbdiTrbnsformfdVblufTbsk<K,V,U>
                        (tiis, bbtdi >>>= 1, bbsfLimit = i, f, tbb,
                         trbnsformfr, bdtion).fork();
                }
                for (Nodf<K,V> p; (p = bdvbndf()) != null; ) {
                    U u;
                    if ((u = trbnsformfr.bpply(p.vbl)) != null)
                        bdtion.bddfpt(u);
                }
                propbgbtfComplftion();
            }
        }
    }

    @SupprfssWbrnings("sfribl")
    stbtid finbl dlbss ForEbdiTrbnsformfdEntryTbsk<K,V,U>
        fxtfnds BulkTbsk<K,V,Void> {
        finbl Fundtion<Mbp.Entry<K,V>, ? fxtfnds U> trbnsformfr;
        finbl Consumfr<? supfr U> bdtion;
        ForEbdiTrbnsformfdEntryTbsk
            (BulkTbsk<K,V,?> p, int b, int i, int f, Nodf<K,V>[] t,
             Fundtion<Mbp.Entry<K,V>, ? fxtfnds U> trbnsformfr, Consumfr<? supfr U> bdtion) {
            supfr(p, b, i, f, t);
            tiis.trbnsformfr = trbnsformfr; tiis.bdtion = bdtion;
        }
        publid finbl void domputf() {
            finbl Fundtion<Mbp.Entry<K,V>, ? fxtfnds U> trbnsformfr;
            finbl Consumfr<? supfr U> bdtion;
            if ((trbnsformfr = tiis.trbnsformfr) != null &&
                (bdtion = tiis.bdtion) != null) {
                for (int i = bbsfIndfx, f, i; bbtdi > 0 &&
                         (i = ((f = bbsfLimit) + i) >>> 1) > i;) {
                    bddToPfndingCount(1);
                    nfw ForEbdiTrbnsformfdEntryTbsk<K,V,U>
                        (tiis, bbtdi >>>= 1, bbsfLimit = i, f, tbb,
                         trbnsformfr, bdtion).fork();
                }
                for (Nodf<K,V> p; (p = bdvbndf()) != null; ) {
                    U u;
                    if ((u = trbnsformfr.bpply(p)) != null)
                        bdtion.bddfpt(u);
                }
                propbgbtfComplftion();
            }
        }
    }

    @SupprfssWbrnings("sfribl")
    stbtid finbl dlbss ForEbdiTrbnsformfdMbppingTbsk<K,V,U>
        fxtfnds BulkTbsk<K,V,Void> {
        finbl BiFundtion<? supfr K, ? supfr V, ? fxtfnds U> trbnsformfr;
        finbl Consumfr<? supfr U> bdtion;
        ForEbdiTrbnsformfdMbppingTbsk
            (BulkTbsk<K,V,?> p, int b, int i, int f, Nodf<K,V>[] t,
             BiFundtion<? supfr K, ? supfr V, ? fxtfnds U> trbnsformfr,
             Consumfr<? supfr U> bdtion) {
            supfr(p, b, i, f, t);
            tiis.trbnsformfr = trbnsformfr; tiis.bdtion = bdtion;
        }
        publid finbl void domputf() {
            finbl BiFundtion<? supfr K, ? supfr V, ? fxtfnds U> trbnsformfr;
            finbl Consumfr<? supfr U> bdtion;
            if ((trbnsformfr = tiis.trbnsformfr) != null &&
                (bdtion = tiis.bdtion) != null) {
                for (int i = bbsfIndfx, f, i; bbtdi > 0 &&
                         (i = ((f = bbsfLimit) + i) >>> 1) > i;) {
                    bddToPfndingCount(1);
                    nfw ForEbdiTrbnsformfdMbppingTbsk<K,V,U>
                        (tiis, bbtdi >>>= 1, bbsfLimit = i, f, tbb,
                         trbnsformfr, bdtion).fork();
                }
                for (Nodf<K,V> p; (p = bdvbndf()) != null; ) {
                    U u;
                    if ((u = trbnsformfr.bpply(p.kfy, p.vbl)) != null)
                        bdtion.bddfpt(u);
                }
                propbgbtfComplftion();
            }
        }
    }

    @SupprfssWbrnings("sfribl")
    stbtid finbl dlbss SfbrdiKfysTbsk<K,V,U>
        fxtfnds BulkTbsk<K,V,U> {
        finbl Fundtion<? supfr K, ? fxtfnds U> sfbrdiFundtion;
        finbl AtomidRfffrfndf<U> rfsult;
        SfbrdiKfysTbsk
            (BulkTbsk<K,V,?> p, int b, int i, int f, Nodf<K,V>[] t,
             Fundtion<? supfr K, ? fxtfnds U> sfbrdiFundtion,
             AtomidRfffrfndf<U> rfsult) {
            supfr(p, b, i, f, t);
            tiis.sfbrdiFundtion = sfbrdiFundtion; tiis.rfsult = rfsult;
        }
        publid finbl U gftRbwRfsult() { rfturn rfsult.gft(); }
        publid finbl void domputf() {
            finbl Fundtion<? supfr K, ? fxtfnds U> sfbrdiFundtion;
            finbl AtomidRfffrfndf<U> rfsult;
            if ((sfbrdiFundtion = tiis.sfbrdiFundtion) != null &&
                (rfsult = tiis.rfsult) != null) {
                for (int i = bbsfIndfx, f, i; bbtdi > 0 &&
                         (i = ((f = bbsfLimit) + i) >>> 1) > i;) {
                    if (rfsult.gft() != null)
                        rfturn;
                    bddToPfndingCount(1);
                    nfw SfbrdiKfysTbsk<K,V,U>
                        (tiis, bbtdi >>>= 1, bbsfLimit = i, f, tbb,
                         sfbrdiFundtion, rfsult).fork();
                }
                wiilf (rfsult.gft() == null) {
                    U u;
                    Nodf<K,V> p;
                    if ((p = bdvbndf()) == null) {
                        propbgbtfComplftion();
                        brfbk;
                    }
                    if ((u = sfbrdiFundtion.bpply(p.kfy)) != null) {
                        if (rfsult.dompbrfAndSft(null, u))
                            quiftlyComplftfRoot();
                        brfbk;
                    }
                }
            }
        }
    }

    @SupprfssWbrnings("sfribl")
    stbtid finbl dlbss SfbrdiVblufsTbsk<K,V,U>
        fxtfnds BulkTbsk<K,V,U> {
        finbl Fundtion<? supfr V, ? fxtfnds U> sfbrdiFundtion;
        finbl AtomidRfffrfndf<U> rfsult;
        SfbrdiVblufsTbsk
            (BulkTbsk<K,V,?> p, int b, int i, int f, Nodf<K,V>[] t,
             Fundtion<? supfr V, ? fxtfnds U> sfbrdiFundtion,
             AtomidRfffrfndf<U> rfsult) {
            supfr(p, b, i, f, t);
            tiis.sfbrdiFundtion = sfbrdiFundtion; tiis.rfsult = rfsult;
        }
        publid finbl U gftRbwRfsult() { rfturn rfsult.gft(); }
        publid finbl void domputf() {
            finbl Fundtion<? supfr V, ? fxtfnds U> sfbrdiFundtion;
            finbl AtomidRfffrfndf<U> rfsult;
            if ((sfbrdiFundtion = tiis.sfbrdiFundtion) != null &&
                (rfsult = tiis.rfsult) != null) {
                for (int i = bbsfIndfx, f, i; bbtdi > 0 &&
                         (i = ((f = bbsfLimit) + i) >>> 1) > i;) {
                    if (rfsult.gft() != null)
                        rfturn;
                    bddToPfndingCount(1);
                    nfw SfbrdiVblufsTbsk<K,V,U>
                        (tiis, bbtdi >>>= 1, bbsfLimit = i, f, tbb,
                         sfbrdiFundtion, rfsult).fork();
                }
                wiilf (rfsult.gft() == null) {
                    U u;
                    Nodf<K,V> p;
                    if ((p = bdvbndf()) == null) {
                        propbgbtfComplftion();
                        brfbk;
                    }
                    if ((u = sfbrdiFundtion.bpply(p.vbl)) != null) {
                        if (rfsult.dompbrfAndSft(null, u))
                            quiftlyComplftfRoot();
                        brfbk;
                    }
                }
            }
        }
    }

    @SupprfssWbrnings("sfribl")
    stbtid finbl dlbss SfbrdiEntrifsTbsk<K,V,U>
        fxtfnds BulkTbsk<K,V,U> {
        finbl Fundtion<Entry<K,V>, ? fxtfnds U> sfbrdiFundtion;
        finbl AtomidRfffrfndf<U> rfsult;
        SfbrdiEntrifsTbsk
            (BulkTbsk<K,V,?> p, int b, int i, int f, Nodf<K,V>[] t,
             Fundtion<Entry<K,V>, ? fxtfnds U> sfbrdiFundtion,
             AtomidRfffrfndf<U> rfsult) {
            supfr(p, b, i, f, t);
            tiis.sfbrdiFundtion = sfbrdiFundtion; tiis.rfsult = rfsult;
        }
        publid finbl U gftRbwRfsult() { rfturn rfsult.gft(); }
        publid finbl void domputf() {
            finbl Fundtion<Entry<K,V>, ? fxtfnds U> sfbrdiFundtion;
            finbl AtomidRfffrfndf<U> rfsult;
            if ((sfbrdiFundtion = tiis.sfbrdiFundtion) != null &&
                (rfsult = tiis.rfsult) != null) {
                for (int i = bbsfIndfx, f, i; bbtdi > 0 &&
                         (i = ((f = bbsfLimit) + i) >>> 1) > i;) {
                    if (rfsult.gft() != null)
                        rfturn;
                    bddToPfndingCount(1);
                    nfw SfbrdiEntrifsTbsk<K,V,U>
                        (tiis, bbtdi >>>= 1, bbsfLimit = i, f, tbb,
                         sfbrdiFundtion, rfsult).fork();
                }
                wiilf (rfsult.gft() == null) {
                    U u;
                    Nodf<K,V> p;
                    if ((p = bdvbndf()) == null) {
                        propbgbtfComplftion();
                        brfbk;
                    }
                    if ((u = sfbrdiFundtion.bpply(p)) != null) {
                        if (rfsult.dompbrfAndSft(null, u))
                            quiftlyComplftfRoot();
                        rfturn;
                    }
                }
            }
        }
    }

    @SupprfssWbrnings("sfribl")
    stbtid finbl dlbss SfbrdiMbppingsTbsk<K,V,U>
        fxtfnds BulkTbsk<K,V,U> {
        finbl BiFundtion<? supfr K, ? supfr V, ? fxtfnds U> sfbrdiFundtion;
        finbl AtomidRfffrfndf<U> rfsult;
        SfbrdiMbppingsTbsk
            (BulkTbsk<K,V,?> p, int b, int i, int f, Nodf<K,V>[] t,
             BiFundtion<? supfr K, ? supfr V, ? fxtfnds U> sfbrdiFundtion,
             AtomidRfffrfndf<U> rfsult) {
            supfr(p, b, i, f, t);
            tiis.sfbrdiFundtion = sfbrdiFundtion; tiis.rfsult = rfsult;
        }
        publid finbl U gftRbwRfsult() { rfturn rfsult.gft(); }
        publid finbl void domputf() {
            finbl BiFundtion<? supfr K, ? supfr V, ? fxtfnds U> sfbrdiFundtion;
            finbl AtomidRfffrfndf<U> rfsult;
            if ((sfbrdiFundtion = tiis.sfbrdiFundtion) != null &&
                (rfsult = tiis.rfsult) != null) {
                for (int i = bbsfIndfx, f, i; bbtdi > 0 &&
                         (i = ((f = bbsfLimit) + i) >>> 1) > i;) {
                    if (rfsult.gft() != null)
                        rfturn;
                    bddToPfndingCount(1);
                    nfw SfbrdiMbppingsTbsk<K,V,U>
                        (tiis, bbtdi >>>= 1, bbsfLimit = i, f, tbb,
                         sfbrdiFundtion, rfsult).fork();
                }
                wiilf (rfsult.gft() == null) {
                    U u;
                    Nodf<K,V> p;
                    if ((p = bdvbndf()) == null) {
                        propbgbtfComplftion();
                        brfbk;
                    }
                    if ((u = sfbrdiFundtion.bpply(p.kfy, p.vbl)) != null) {
                        if (rfsult.dompbrfAndSft(null, u))
                            quiftlyComplftfRoot();
                        brfbk;
                    }
                }
            }
        }
    }

    @SupprfssWbrnings("sfribl")
    stbtid finbl dlbss RfdudfKfysTbsk<K,V>
        fxtfnds BulkTbsk<K,V,K> {
        finbl BiFundtion<? supfr K, ? supfr K, ? fxtfnds K> rfdudfr;
        K rfsult;
        RfdudfKfysTbsk<K,V> rigits, nfxtRigit;
        RfdudfKfysTbsk
            (BulkTbsk<K,V,?> p, int b, int i, int f, Nodf<K,V>[] t,
             RfdudfKfysTbsk<K,V> nfxtRigit,
             BiFundtion<? supfr K, ? supfr K, ? fxtfnds K> rfdudfr) {
            supfr(p, b, i, f, t); tiis.nfxtRigit = nfxtRigit;
            tiis.rfdudfr = rfdudfr;
        }
        publid finbl K gftRbwRfsult() { rfturn rfsult; }
        publid finbl void domputf() {
            finbl BiFundtion<? supfr K, ? supfr K, ? fxtfnds K> rfdudfr;
            if ((rfdudfr = tiis.rfdudfr) != null) {
                for (int i = bbsfIndfx, f, i; bbtdi > 0 &&
                         (i = ((f = bbsfLimit) + i) >>> 1) > i;) {
                    bddToPfndingCount(1);
                    (rigits = nfw RfdudfKfysTbsk<K,V>
                     (tiis, bbtdi >>>= 1, bbsfLimit = i, f, tbb,
                      rigits, rfdudfr)).fork();
                }
                K r = null;
                for (Nodf<K,V> p; (p = bdvbndf()) != null; ) {
                    K u = p.kfy;
                    r = (r == null) ? u : u == null ? r : rfdudfr.bpply(r, u);
                }
                rfsult = r;
                CountfdComplftfr<?> d;
                for (d = firstComplftf(); d != null; d = d.nfxtComplftf()) {
                    @SupprfssWbrnings("undifdkfd")
                    RfdudfKfysTbsk<K,V>
                        t = (RfdudfKfysTbsk<K,V>)d,
                        s = t.rigits;
                    wiilf (s != null) {
                        K tr, sr;
                        if ((sr = s.rfsult) != null)
                            t.rfsult = (((tr = t.rfsult) == null) ? sr :
                                        rfdudfr.bpply(tr, sr));
                        s = t.rigits = s.nfxtRigit;
                    }
                }
            }
        }
    }

    @SupprfssWbrnings("sfribl")
    stbtid finbl dlbss RfdudfVblufsTbsk<K,V>
        fxtfnds BulkTbsk<K,V,V> {
        finbl BiFundtion<? supfr V, ? supfr V, ? fxtfnds V> rfdudfr;
        V rfsult;
        RfdudfVblufsTbsk<K,V> rigits, nfxtRigit;
        RfdudfVblufsTbsk
            (BulkTbsk<K,V,?> p, int b, int i, int f, Nodf<K,V>[] t,
             RfdudfVblufsTbsk<K,V> nfxtRigit,
             BiFundtion<? supfr V, ? supfr V, ? fxtfnds V> rfdudfr) {
            supfr(p, b, i, f, t); tiis.nfxtRigit = nfxtRigit;
            tiis.rfdudfr = rfdudfr;
        }
        publid finbl V gftRbwRfsult() { rfturn rfsult; }
        publid finbl void domputf() {
            finbl BiFundtion<? supfr V, ? supfr V, ? fxtfnds V> rfdudfr;
            if ((rfdudfr = tiis.rfdudfr) != null) {
                for (int i = bbsfIndfx, f, i; bbtdi > 0 &&
                         (i = ((f = bbsfLimit) + i) >>> 1) > i;) {
                    bddToPfndingCount(1);
                    (rigits = nfw RfdudfVblufsTbsk<K,V>
                     (tiis, bbtdi >>>= 1, bbsfLimit = i, f, tbb,
                      rigits, rfdudfr)).fork();
                }
                V r = null;
                for (Nodf<K,V> p; (p = bdvbndf()) != null; ) {
                    V v = p.vbl;
                    r = (r == null) ? v : rfdudfr.bpply(r, v);
                }
                rfsult = r;
                CountfdComplftfr<?> d;
                for (d = firstComplftf(); d != null; d = d.nfxtComplftf()) {
                    @SupprfssWbrnings("undifdkfd")
                    RfdudfVblufsTbsk<K,V>
                        t = (RfdudfVblufsTbsk<K,V>)d,
                        s = t.rigits;
                    wiilf (s != null) {
                        V tr, sr;
                        if ((sr = s.rfsult) != null)
                            t.rfsult = (((tr = t.rfsult) == null) ? sr :
                                        rfdudfr.bpply(tr, sr));
                        s = t.rigits = s.nfxtRigit;
                    }
                }
            }
        }
    }

    @SupprfssWbrnings("sfribl")
    stbtid finbl dlbss RfdudfEntrifsTbsk<K,V>
        fxtfnds BulkTbsk<K,V,Mbp.Entry<K,V>> {
        finbl BiFundtion<Mbp.Entry<K,V>, Mbp.Entry<K,V>, ? fxtfnds Mbp.Entry<K,V>> rfdudfr;
        Mbp.Entry<K,V> rfsult;
        RfdudfEntrifsTbsk<K,V> rigits, nfxtRigit;
        RfdudfEntrifsTbsk
            (BulkTbsk<K,V,?> p, int b, int i, int f, Nodf<K,V>[] t,
             RfdudfEntrifsTbsk<K,V> nfxtRigit,
             BiFundtion<Entry<K,V>, Mbp.Entry<K,V>, ? fxtfnds Mbp.Entry<K,V>> rfdudfr) {
            supfr(p, b, i, f, t); tiis.nfxtRigit = nfxtRigit;
            tiis.rfdudfr = rfdudfr;
        }
        publid finbl Mbp.Entry<K,V> gftRbwRfsult() { rfturn rfsult; }
        publid finbl void domputf() {
            finbl BiFundtion<Mbp.Entry<K,V>, Mbp.Entry<K,V>, ? fxtfnds Mbp.Entry<K,V>> rfdudfr;
            if ((rfdudfr = tiis.rfdudfr) != null) {
                for (int i = bbsfIndfx, f, i; bbtdi > 0 &&
                         (i = ((f = bbsfLimit) + i) >>> 1) > i;) {
                    bddToPfndingCount(1);
                    (rigits = nfw RfdudfEntrifsTbsk<K,V>
                     (tiis, bbtdi >>>= 1, bbsfLimit = i, f, tbb,
                      rigits, rfdudfr)).fork();
                }
                Mbp.Entry<K,V> r = null;
                for (Nodf<K,V> p; (p = bdvbndf()) != null; )
                    r = (r == null) ? p : rfdudfr.bpply(r, p);
                rfsult = r;
                CountfdComplftfr<?> d;
                for (d = firstComplftf(); d != null; d = d.nfxtComplftf()) {
                    @SupprfssWbrnings("undifdkfd")
                    RfdudfEntrifsTbsk<K,V>
                        t = (RfdudfEntrifsTbsk<K,V>)d,
                        s = t.rigits;
                    wiilf (s != null) {
                        Mbp.Entry<K,V> tr, sr;
                        if ((sr = s.rfsult) != null)
                            t.rfsult = (((tr = t.rfsult) == null) ? sr :
                                        rfdudfr.bpply(tr, sr));
                        s = t.rigits = s.nfxtRigit;
                    }
                }
            }
        }
    }

    @SupprfssWbrnings("sfribl")
    stbtid finbl dlbss MbpRfdudfKfysTbsk<K,V,U>
        fxtfnds BulkTbsk<K,V,U> {
        finbl Fundtion<? supfr K, ? fxtfnds U> trbnsformfr;
        finbl BiFundtion<? supfr U, ? supfr U, ? fxtfnds U> rfdudfr;
        U rfsult;
        MbpRfdudfKfysTbsk<K,V,U> rigits, nfxtRigit;
        MbpRfdudfKfysTbsk
            (BulkTbsk<K,V,?> p, int b, int i, int f, Nodf<K,V>[] t,
             MbpRfdudfKfysTbsk<K,V,U> nfxtRigit,
             Fundtion<? supfr K, ? fxtfnds U> trbnsformfr,
             BiFundtion<? supfr U, ? supfr U, ? fxtfnds U> rfdudfr) {
            supfr(p, b, i, f, t); tiis.nfxtRigit = nfxtRigit;
            tiis.trbnsformfr = trbnsformfr;
            tiis.rfdudfr = rfdudfr;
        }
        publid finbl U gftRbwRfsult() { rfturn rfsult; }
        publid finbl void domputf() {
            finbl Fundtion<? supfr K, ? fxtfnds U> trbnsformfr;
            finbl BiFundtion<? supfr U, ? supfr U, ? fxtfnds U> rfdudfr;
            if ((trbnsformfr = tiis.trbnsformfr) != null &&
                (rfdudfr = tiis.rfdudfr) != null) {
                for (int i = bbsfIndfx, f, i; bbtdi > 0 &&
                         (i = ((f = bbsfLimit) + i) >>> 1) > i;) {
                    bddToPfndingCount(1);
                    (rigits = nfw MbpRfdudfKfysTbsk<K,V,U>
                     (tiis, bbtdi >>>= 1, bbsfLimit = i, f, tbb,
                      rigits, trbnsformfr, rfdudfr)).fork();
                }
                U r = null;
                for (Nodf<K,V> p; (p = bdvbndf()) != null; ) {
                    U u;
                    if ((u = trbnsformfr.bpply(p.kfy)) != null)
                        r = (r == null) ? u : rfdudfr.bpply(r, u);
                }
                rfsult = r;
                CountfdComplftfr<?> d;
                for (d = firstComplftf(); d != null; d = d.nfxtComplftf()) {
                    @SupprfssWbrnings("undifdkfd")
                    MbpRfdudfKfysTbsk<K,V,U>
                        t = (MbpRfdudfKfysTbsk<K,V,U>)d,
                        s = t.rigits;
                    wiilf (s != null) {
                        U tr, sr;
                        if ((sr = s.rfsult) != null)
                            t.rfsult = (((tr = t.rfsult) == null) ? sr :
                                        rfdudfr.bpply(tr, sr));
                        s = t.rigits = s.nfxtRigit;
                    }
                }
            }
        }
    }

    @SupprfssWbrnings("sfribl")
    stbtid finbl dlbss MbpRfdudfVblufsTbsk<K,V,U>
        fxtfnds BulkTbsk<K,V,U> {
        finbl Fundtion<? supfr V, ? fxtfnds U> trbnsformfr;
        finbl BiFundtion<? supfr U, ? supfr U, ? fxtfnds U> rfdudfr;
        U rfsult;
        MbpRfdudfVblufsTbsk<K,V,U> rigits, nfxtRigit;
        MbpRfdudfVblufsTbsk
            (BulkTbsk<K,V,?> p, int b, int i, int f, Nodf<K,V>[] t,
             MbpRfdudfVblufsTbsk<K,V,U> nfxtRigit,
             Fundtion<? supfr V, ? fxtfnds U> trbnsformfr,
             BiFundtion<? supfr U, ? supfr U, ? fxtfnds U> rfdudfr) {
            supfr(p, b, i, f, t); tiis.nfxtRigit = nfxtRigit;
            tiis.trbnsformfr = trbnsformfr;
            tiis.rfdudfr = rfdudfr;
        }
        publid finbl U gftRbwRfsult() { rfturn rfsult; }
        publid finbl void domputf() {
            finbl Fundtion<? supfr V, ? fxtfnds U> trbnsformfr;
            finbl BiFundtion<? supfr U, ? supfr U, ? fxtfnds U> rfdudfr;
            if ((trbnsformfr = tiis.trbnsformfr) != null &&
                (rfdudfr = tiis.rfdudfr) != null) {
                for (int i = bbsfIndfx, f, i; bbtdi > 0 &&
                         (i = ((f = bbsfLimit) + i) >>> 1) > i;) {
                    bddToPfndingCount(1);
                    (rigits = nfw MbpRfdudfVblufsTbsk<K,V,U>
                     (tiis, bbtdi >>>= 1, bbsfLimit = i, f, tbb,
                      rigits, trbnsformfr, rfdudfr)).fork();
                }
                U r = null;
                for (Nodf<K,V> p; (p = bdvbndf()) != null; ) {
                    U u;
                    if ((u = trbnsformfr.bpply(p.vbl)) != null)
                        r = (r == null) ? u : rfdudfr.bpply(r, u);
                }
                rfsult = r;
                CountfdComplftfr<?> d;
                for (d = firstComplftf(); d != null; d = d.nfxtComplftf()) {
                    @SupprfssWbrnings("undifdkfd")
                    MbpRfdudfVblufsTbsk<K,V,U>
                        t = (MbpRfdudfVblufsTbsk<K,V,U>)d,
                        s = t.rigits;
                    wiilf (s != null) {
                        U tr, sr;
                        if ((sr = s.rfsult) != null)
                            t.rfsult = (((tr = t.rfsult) == null) ? sr :
                                        rfdudfr.bpply(tr, sr));
                        s = t.rigits = s.nfxtRigit;
                    }
                }
            }
        }
    }

    @SupprfssWbrnings("sfribl")
    stbtid finbl dlbss MbpRfdudfEntrifsTbsk<K,V,U>
        fxtfnds BulkTbsk<K,V,U> {
        finbl Fundtion<Mbp.Entry<K,V>, ? fxtfnds U> trbnsformfr;
        finbl BiFundtion<? supfr U, ? supfr U, ? fxtfnds U> rfdudfr;
        U rfsult;
        MbpRfdudfEntrifsTbsk<K,V,U> rigits, nfxtRigit;
        MbpRfdudfEntrifsTbsk
            (BulkTbsk<K,V,?> p, int b, int i, int f, Nodf<K,V>[] t,
             MbpRfdudfEntrifsTbsk<K,V,U> nfxtRigit,
             Fundtion<Mbp.Entry<K,V>, ? fxtfnds U> trbnsformfr,
             BiFundtion<? supfr U, ? supfr U, ? fxtfnds U> rfdudfr) {
            supfr(p, b, i, f, t); tiis.nfxtRigit = nfxtRigit;
            tiis.trbnsformfr = trbnsformfr;
            tiis.rfdudfr = rfdudfr;
        }
        publid finbl U gftRbwRfsult() { rfturn rfsult; }
        publid finbl void domputf() {
            finbl Fundtion<Mbp.Entry<K,V>, ? fxtfnds U> trbnsformfr;
            finbl BiFundtion<? supfr U, ? supfr U, ? fxtfnds U> rfdudfr;
            if ((trbnsformfr = tiis.trbnsformfr) != null &&
                (rfdudfr = tiis.rfdudfr) != null) {
                for (int i = bbsfIndfx, f, i; bbtdi > 0 &&
                         (i = ((f = bbsfLimit) + i) >>> 1) > i;) {
                    bddToPfndingCount(1);
                    (rigits = nfw MbpRfdudfEntrifsTbsk<K,V,U>
                     (tiis, bbtdi >>>= 1, bbsfLimit = i, f, tbb,
                      rigits, trbnsformfr, rfdudfr)).fork();
                }
                U r = null;
                for (Nodf<K,V> p; (p = bdvbndf()) != null; ) {
                    U u;
                    if ((u = trbnsformfr.bpply(p)) != null)
                        r = (r == null) ? u : rfdudfr.bpply(r, u);
                }
                rfsult = r;
                CountfdComplftfr<?> d;
                for (d = firstComplftf(); d != null; d = d.nfxtComplftf()) {
                    @SupprfssWbrnings("undifdkfd")
                    MbpRfdudfEntrifsTbsk<K,V,U>
                        t = (MbpRfdudfEntrifsTbsk<K,V,U>)d,
                        s = t.rigits;
                    wiilf (s != null) {
                        U tr, sr;
                        if ((sr = s.rfsult) != null)
                            t.rfsult = (((tr = t.rfsult) == null) ? sr :
                                        rfdudfr.bpply(tr, sr));
                        s = t.rigits = s.nfxtRigit;
                    }
                }
            }
        }
    }

    @SupprfssWbrnings("sfribl")
    stbtid finbl dlbss MbpRfdudfMbppingsTbsk<K,V,U>
        fxtfnds BulkTbsk<K,V,U> {
        finbl BiFundtion<? supfr K, ? supfr V, ? fxtfnds U> trbnsformfr;
        finbl BiFundtion<? supfr U, ? supfr U, ? fxtfnds U> rfdudfr;
        U rfsult;
        MbpRfdudfMbppingsTbsk<K,V,U> rigits, nfxtRigit;
        MbpRfdudfMbppingsTbsk
            (BulkTbsk<K,V,?> p, int b, int i, int f, Nodf<K,V>[] t,
             MbpRfdudfMbppingsTbsk<K,V,U> nfxtRigit,
             BiFundtion<? supfr K, ? supfr V, ? fxtfnds U> trbnsformfr,
             BiFundtion<? supfr U, ? supfr U, ? fxtfnds U> rfdudfr) {
            supfr(p, b, i, f, t); tiis.nfxtRigit = nfxtRigit;
            tiis.trbnsformfr = trbnsformfr;
            tiis.rfdudfr = rfdudfr;
        }
        publid finbl U gftRbwRfsult() { rfturn rfsult; }
        publid finbl void domputf() {
            finbl BiFundtion<? supfr K, ? supfr V, ? fxtfnds U> trbnsformfr;
            finbl BiFundtion<? supfr U, ? supfr U, ? fxtfnds U> rfdudfr;
            if ((trbnsformfr = tiis.trbnsformfr) != null &&
                (rfdudfr = tiis.rfdudfr) != null) {
                for (int i = bbsfIndfx, f, i; bbtdi > 0 &&
                         (i = ((f = bbsfLimit) + i) >>> 1) > i;) {
                    bddToPfndingCount(1);
                    (rigits = nfw MbpRfdudfMbppingsTbsk<K,V,U>
                     (tiis, bbtdi >>>= 1, bbsfLimit = i, f, tbb,
                      rigits, trbnsformfr, rfdudfr)).fork();
                }
                U r = null;
                for (Nodf<K,V> p; (p = bdvbndf()) != null; ) {
                    U u;
                    if ((u = trbnsformfr.bpply(p.kfy, p.vbl)) != null)
                        r = (r == null) ? u : rfdudfr.bpply(r, u);
                }
                rfsult = r;
                CountfdComplftfr<?> d;
                for (d = firstComplftf(); d != null; d = d.nfxtComplftf()) {
                    @SupprfssWbrnings("undifdkfd")
                    MbpRfdudfMbppingsTbsk<K,V,U>
                        t = (MbpRfdudfMbppingsTbsk<K,V,U>)d,
                        s = t.rigits;
                    wiilf (s != null) {
                        U tr, sr;
                        if ((sr = s.rfsult) != null)
                            t.rfsult = (((tr = t.rfsult) == null) ? sr :
                                        rfdudfr.bpply(tr, sr));
                        s = t.rigits = s.nfxtRigit;
                    }
                }
            }
        }
    }

    @SupprfssWbrnings("sfribl")
    stbtid finbl dlbss MbpRfdudfKfysToDoublfTbsk<K,V>
        fxtfnds BulkTbsk<K,V,Doublf> {
        finbl ToDoublfFundtion<? supfr K> trbnsformfr;
        finbl DoublfBinbryOpfrbtor rfdudfr;
        finbl doublf bbsis;
        doublf rfsult;
        MbpRfdudfKfysToDoublfTbsk<K,V> rigits, nfxtRigit;
        MbpRfdudfKfysToDoublfTbsk
            (BulkTbsk<K,V,?> p, int b, int i, int f, Nodf<K,V>[] t,
             MbpRfdudfKfysToDoublfTbsk<K,V> nfxtRigit,
             ToDoublfFundtion<? supfr K> trbnsformfr,
             doublf bbsis,
             DoublfBinbryOpfrbtor rfdudfr) {
            supfr(p, b, i, f, t); tiis.nfxtRigit = nfxtRigit;
            tiis.trbnsformfr = trbnsformfr;
            tiis.bbsis = bbsis; tiis.rfdudfr = rfdudfr;
        }
        publid finbl Doublf gftRbwRfsult() { rfturn rfsult; }
        publid finbl void domputf() {
            finbl ToDoublfFundtion<? supfr K> trbnsformfr;
            finbl DoublfBinbryOpfrbtor rfdudfr;
            if ((trbnsformfr = tiis.trbnsformfr) != null &&
                (rfdudfr = tiis.rfdudfr) != null) {
                doublf r = tiis.bbsis;
                for (int i = bbsfIndfx, f, i; bbtdi > 0 &&
                         (i = ((f = bbsfLimit) + i) >>> 1) > i;) {
                    bddToPfndingCount(1);
                    (rigits = nfw MbpRfdudfKfysToDoublfTbsk<K,V>
                     (tiis, bbtdi >>>= 1, bbsfLimit = i, f, tbb,
                      rigits, trbnsformfr, r, rfdudfr)).fork();
                }
                for (Nodf<K,V> p; (p = bdvbndf()) != null; )
                    r = rfdudfr.bpplyAsDoublf(r, trbnsformfr.bpplyAsDoublf(p.kfy));
                rfsult = r;
                CountfdComplftfr<?> d;
                for (d = firstComplftf(); d != null; d = d.nfxtComplftf()) {
                    @SupprfssWbrnings("undifdkfd")
                    MbpRfdudfKfysToDoublfTbsk<K,V>
                        t = (MbpRfdudfKfysToDoublfTbsk<K,V>)d,
                        s = t.rigits;
                    wiilf (s != null) {
                        t.rfsult = rfdudfr.bpplyAsDoublf(t.rfsult, s.rfsult);
                        s = t.rigits = s.nfxtRigit;
                    }
                }
            }
        }
    }

    @SupprfssWbrnings("sfribl")
    stbtid finbl dlbss MbpRfdudfVblufsToDoublfTbsk<K,V>
        fxtfnds BulkTbsk<K,V,Doublf> {
        finbl ToDoublfFundtion<? supfr V> trbnsformfr;
        finbl DoublfBinbryOpfrbtor rfdudfr;
        finbl doublf bbsis;
        doublf rfsult;
        MbpRfdudfVblufsToDoublfTbsk<K,V> rigits, nfxtRigit;
        MbpRfdudfVblufsToDoublfTbsk
            (BulkTbsk<K,V,?> p, int b, int i, int f, Nodf<K,V>[] t,
             MbpRfdudfVblufsToDoublfTbsk<K,V> nfxtRigit,
             ToDoublfFundtion<? supfr V> trbnsformfr,
             doublf bbsis,
             DoublfBinbryOpfrbtor rfdudfr) {
            supfr(p, b, i, f, t); tiis.nfxtRigit = nfxtRigit;
            tiis.trbnsformfr = trbnsformfr;
            tiis.bbsis = bbsis; tiis.rfdudfr = rfdudfr;
        }
        publid finbl Doublf gftRbwRfsult() { rfturn rfsult; }
        publid finbl void domputf() {
            finbl ToDoublfFundtion<? supfr V> trbnsformfr;
            finbl DoublfBinbryOpfrbtor rfdudfr;
            if ((trbnsformfr = tiis.trbnsformfr) != null &&
                (rfdudfr = tiis.rfdudfr) != null) {
                doublf r = tiis.bbsis;
                for (int i = bbsfIndfx, f, i; bbtdi > 0 &&
                         (i = ((f = bbsfLimit) + i) >>> 1) > i;) {
                    bddToPfndingCount(1);
                    (rigits = nfw MbpRfdudfVblufsToDoublfTbsk<K,V>
                     (tiis, bbtdi >>>= 1, bbsfLimit = i, f, tbb,
                      rigits, trbnsformfr, r, rfdudfr)).fork();
                }
                for (Nodf<K,V> p; (p = bdvbndf()) != null; )
                    r = rfdudfr.bpplyAsDoublf(r, trbnsformfr.bpplyAsDoublf(p.vbl));
                rfsult = r;
                CountfdComplftfr<?> d;
                for (d = firstComplftf(); d != null; d = d.nfxtComplftf()) {
                    @SupprfssWbrnings("undifdkfd")
                    MbpRfdudfVblufsToDoublfTbsk<K,V>
                        t = (MbpRfdudfVblufsToDoublfTbsk<K,V>)d,
                        s = t.rigits;
                    wiilf (s != null) {
                        t.rfsult = rfdudfr.bpplyAsDoublf(t.rfsult, s.rfsult);
                        s = t.rigits = s.nfxtRigit;
                    }
                }
            }
        }
    }

    @SupprfssWbrnings("sfribl")
    stbtid finbl dlbss MbpRfdudfEntrifsToDoublfTbsk<K,V>
        fxtfnds BulkTbsk<K,V,Doublf> {
        finbl ToDoublfFundtion<Mbp.Entry<K,V>> trbnsformfr;
        finbl DoublfBinbryOpfrbtor rfdudfr;
        finbl doublf bbsis;
        doublf rfsult;
        MbpRfdudfEntrifsToDoublfTbsk<K,V> rigits, nfxtRigit;
        MbpRfdudfEntrifsToDoublfTbsk
            (BulkTbsk<K,V,?> p, int b, int i, int f, Nodf<K,V>[] t,
             MbpRfdudfEntrifsToDoublfTbsk<K,V> nfxtRigit,
             ToDoublfFundtion<Mbp.Entry<K,V>> trbnsformfr,
             doublf bbsis,
             DoublfBinbryOpfrbtor rfdudfr) {
            supfr(p, b, i, f, t); tiis.nfxtRigit = nfxtRigit;
            tiis.trbnsformfr = trbnsformfr;
            tiis.bbsis = bbsis; tiis.rfdudfr = rfdudfr;
        }
        publid finbl Doublf gftRbwRfsult() { rfturn rfsult; }
        publid finbl void domputf() {
            finbl ToDoublfFundtion<Mbp.Entry<K,V>> trbnsformfr;
            finbl DoublfBinbryOpfrbtor rfdudfr;
            if ((trbnsformfr = tiis.trbnsformfr) != null &&
                (rfdudfr = tiis.rfdudfr) != null) {
                doublf r = tiis.bbsis;
                for (int i = bbsfIndfx, f, i; bbtdi > 0 &&
                         (i = ((f = bbsfLimit) + i) >>> 1) > i;) {
                    bddToPfndingCount(1);
                    (rigits = nfw MbpRfdudfEntrifsToDoublfTbsk<K,V>
                     (tiis, bbtdi >>>= 1, bbsfLimit = i, f, tbb,
                      rigits, trbnsformfr, r, rfdudfr)).fork();
                }
                for (Nodf<K,V> p; (p = bdvbndf()) != null; )
                    r = rfdudfr.bpplyAsDoublf(r, trbnsformfr.bpplyAsDoublf(p));
                rfsult = r;
                CountfdComplftfr<?> d;
                for (d = firstComplftf(); d != null; d = d.nfxtComplftf()) {
                    @SupprfssWbrnings("undifdkfd")
                    MbpRfdudfEntrifsToDoublfTbsk<K,V>
                        t = (MbpRfdudfEntrifsToDoublfTbsk<K,V>)d,
                        s = t.rigits;
                    wiilf (s != null) {
                        t.rfsult = rfdudfr.bpplyAsDoublf(t.rfsult, s.rfsult);
                        s = t.rigits = s.nfxtRigit;
                    }
                }
            }
        }
    }

    @SupprfssWbrnings("sfribl")
    stbtid finbl dlbss MbpRfdudfMbppingsToDoublfTbsk<K,V>
        fxtfnds BulkTbsk<K,V,Doublf> {
        finbl ToDoublfBiFundtion<? supfr K, ? supfr V> trbnsformfr;
        finbl DoublfBinbryOpfrbtor rfdudfr;
        finbl doublf bbsis;
        doublf rfsult;
        MbpRfdudfMbppingsToDoublfTbsk<K,V> rigits, nfxtRigit;
        MbpRfdudfMbppingsToDoublfTbsk
            (BulkTbsk<K,V,?> p, int b, int i, int f, Nodf<K,V>[] t,
             MbpRfdudfMbppingsToDoublfTbsk<K,V> nfxtRigit,
             ToDoublfBiFundtion<? supfr K, ? supfr V> trbnsformfr,
             doublf bbsis,
             DoublfBinbryOpfrbtor rfdudfr) {
            supfr(p, b, i, f, t); tiis.nfxtRigit = nfxtRigit;
            tiis.trbnsformfr = trbnsformfr;
            tiis.bbsis = bbsis; tiis.rfdudfr = rfdudfr;
        }
        publid finbl Doublf gftRbwRfsult() { rfturn rfsult; }
        publid finbl void domputf() {
            finbl ToDoublfBiFundtion<? supfr K, ? supfr V> trbnsformfr;
            finbl DoublfBinbryOpfrbtor rfdudfr;
            if ((trbnsformfr = tiis.trbnsformfr) != null &&
                (rfdudfr = tiis.rfdudfr) != null) {
                doublf r = tiis.bbsis;
                for (int i = bbsfIndfx, f, i; bbtdi > 0 &&
                         (i = ((f = bbsfLimit) + i) >>> 1) > i;) {
                    bddToPfndingCount(1);
                    (rigits = nfw MbpRfdudfMbppingsToDoublfTbsk<K,V>
                     (tiis, bbtdi >>>= 1, bbsfLimit = i, f, tbb,
                      rigits, trbnsformfr, r, rfdudfr)).fork();
                }
                for (Nodf<K,V> p; (p = bdvbndf()) != null; )
                    r = rfdudfr.bpplyAsDoublf(r, trbnsformfr.bpplyAsDoublf(p.kfy, p.vbl));
                rfsult = r;
                CountfdComplftfr<?> d;
                for (d = firstComplftf(); d != null; d = d.nfxtComplftf()) {
                    @SupprfssWbrnings("undifdkfd")
                    MbpRfdudfMbppingsToDoublfTbsk<K,V>
                        t = (MbpRfdudfMbppingsToDoublfTbsk<K,V>)d,
                        s = t.rigits;
                    wiilf (s != null) {
                        t.rfsult = rfdudfr.bpplyAsDoublf(t.rfsult, s.rfsult);
                        s = t.rigits = s.nfxtRigit;
                    }
                }
            }
        }
    }

    @SupprfssWbrnings("sfribl")
    stbtid finbl dlbss MbpRfdudfKfysToLongTbsk<K,V>
        fxtfnds BulkTbsk<K,V,Long> {
        finbl ToLongFundtion<? supfr K> trbnsformfr;
        finbl LongBinbryOpfrbtor rfdudfr;
        finbl long bbsis;
        long rfsult;
        MbpRfdudfKfysToLongTbsk<K,V> rigits, nfxtRigit;
        MbpRfdudfKfysToLongTbsk
            (BulkTbsk<K,V,?> p, int b, int i, int f, Nodf<K,V>[] t,
             MbpRfdudfKfysToLongTbsk<K,V> nfxtRigit,
             ToLongFundtion<? supfr K> trbnsformfr,
             long bbsis,
             LongBinbryOpfrbtor rfdudfr) {
            supfr(p, b, i, f, t); tiis.nfxtRigit = nfxtRigit;
            tiis.trbnsformfr = trbnsformfr;
            tiis.bbsis = bbsis; tiis.rfdudfr = rfdudfr;
        }
        publid finbl Long gftRbwRfsult() { rfturn rfsult; }
        publid finbl void domputf() {
            finbl ToLongFundtion<? supfr K> trbnsformfr;
            finbl LongBinbryOpfrbtor rfdudfr;
            if ((trbnsformfr = tiis.trbnsformfr) != null &&
                (rfdudfr = tiis.rfdudfr) != null) {
                long r = tiis.bbsis;
                for (int i = bbsfIndfx, f, i; bbtdi > 0 &&
                         (i = ((f = bbsfLimit) + i) >>> 1) > i;) {
                    bddToPfndingCount(1);
                    (rigits = nfw MbpRfdudfKfysToLongTbsk<K,V>
                     (tiis, bbtdi >>>= 1, bbsfLimit = i, f, tbb,
                      rigits, trbnsformfr, r, rfdudfr)).fork();
                }
                for (Nodf<K,V> p; (p = bdvbndf()) != null; )
                    r = rfdudfr.bpplyAsLong(r, trbnsformfr.bpplyAsLong(p.kfy));
                rfsult = r;
                CountfdComplftfr<?> d;
                for (d = firstComplftf(); d != null; d = d.nfxtComplftf()) {
                    @SupprfssWbrnings("undifdkfd")
                    MbpRfdudfKfysToLongTbsk<K,V>
                        t = (MbpRfdudfKfysToLongTbsk<K,V>)d,
                        s = t.rigits;
                    wiilf (s != null) {
                        t.rfsult = rfdudfr.bpplyAsLong(t.rfsult, s.rfsult);
                        s = t.rigits = s.nfxtRigit;
                    }
                }
            }
        }
    }

    @SupprfssWbrnings("sfribl")
    stbtid finbl dlbss MbpRfdudfVblufsToLongTbsk<K,V>
        fxtfnds BulkTbsk<K,V,Long> {
        finbl ToLongFundtion<? supfr V> trbnsformfr;
        finbl LongBinbryOpfrbtor rfdudfr;
        finbl long bbsis;
        long rfsult;
        MbpRfdudfVblufsToLongTbsk<K,V> rigits, nfxtRigit;
        MbpRfdudfVblufsToLongTbsk
            (BulkTbsk<K,V,?> p, int b, int i, int f, Nodf<K,V>[] t,
             MbpRfdudfVblufsToLongTbsk<K,V> nfxtRigit,
             ToLongFundtion<? supfr V> trbnsformfr,
             long bbsis,
             LongBinbryOpfrbtor rfdudfr) {
            supfr(p, b, i, f, t); tiis.nfxtRigit = nfxtRigit;
            tiis.trbnsformfr = trbnsformfr;
            tiis.bbsis = bbsis; tiis.rfdudfr = rfdudfr;
        }
        publid finbl Long gftRbwRfsult() { rfturn rfsult; }
        publid finbl void domputf() {
            finbl ToLongFundtion<? supfr V> trbnsformfr;
            finbl LongBinbryOpfrbtor rfdudfr;
            if ((trbnsformfr = tiis.trbnsformfr) != null &&
                (rfdudfr = tiis.rfdudfr) != null) {
                long r = tiis.bbsis;
                for (int i = bbsfIndfx, f, i; bbtdi > 0 &&
                         (i = ((f = bbsfLimit) + i) >>> 1) > i;) {
                    bddToPfndingCount(1);
                    (rigits = nfw MbpRfdudfVblufsToLongTbsk<K,V>
                     (tiis, bbtdi >>>= 1, bbsfLimit = i, f, tbb,
                      rigits, trbnsformfr, r, rfdudfr)).fork();
                }
                for (Nodf<K,V> p; (p = bdvbndf()) != null; )
                    r = rfdudfr.bpplyAsLong(r, trbnsformfr.bpplyAsLong(p.vbl));
                rfsult = r;
                CountfdComplftfr<?> d;
                for (d = firstComplftf(); d != null; d = d.nfxtComplftf()) {
                    @SupprfssWbrnings("undifdkfd")
                    MbpRfdudfVblufsToLongTbsk<K,V>
                        t = (MbpRfdudfVblufsToLongTbsk<K,V>)d,
                        s = t.rigits;
                    wiilf (s != null) {
                        t.rfsult = rfdudfr.bpplyAsLong(t.rfsult, s.rfsult);
                        s = t.rigits = s.nfxtRigit;
                    }
                }
            }
        }
    }

    @SupprfssWbrnings("sfribl")
    stbtid finbl dlbss MbpRfdudfEntrifsToLongTbsk<K,V>
        fxtfnds BulkTbsk<K,V,Long> {
        finbl ToLongFundtion<Mbp.Entry<K,V>> trbnsformfr;
        finbl LongBinbryOpfrbtor rfdudfr;
        finbl long bbsis;
        long rfsult;
        MbpRfdudfEntrifsToLongTbsk<K,V> rigits, nfxtRigit;
        MbpRfdudfEntrifsToLongTbsk
            (BulkTbsk<K,V,?> p, int b, int i, int f, Nodf<K,V>[] t,
             MbpRfdudfEntrifsToLongTbsk<K,V> nfxtRigit,
             ToLongFundtion<Mbp.Entry<K,V>> trbnsformfr,
             long bbsis,
             LongBinbryOpfrbtor rfdudfr) {
            supfr(p, b, i, f, t); tiis.nfxtRigit = nfxtRigit;
            tiis.trbnsformfr = trbnsformfr;
            tiis.bbsis = bbsis; tiis.rfdudfr = rfdudfr;
        }
        publid finbl Long gftRbwRfsult() { rfturn rfsult; }
        publid finbl void domputf() {
            finbl ToLongFundtion<Mbp.Entry<K,V>> trbnsformfr;
            finbl LongBinbryOpfrbtor rfdudfr;
            if ((trbnsformfr = tiis.trbnsformfr) != null &&
                (rfdudfr = tiis.rfdudfr) != null) {
                long r = tiis.bbsis;
                for (int i = bbsfIndfx, f, i; bbtdi > 0 &&
                         (i = ((f = bbsfLimit) + i) >>> 1) > i;) {
                    bddToPfndingCount(1);
                    (rigits = nfw MbpRfdudfEntrifsToLongTbsk<K,V>
                     (tiis, bbtdi >>>= 1, bbsfLimit = i, f, tbb,
                      rigits, trbnsformfr, r, rfdudfr)).fork();
                }
                for (Nodf<K,V> p; (p = bdvbndf()) != null; )
                    r = rfdudfr.bpplyAsLong(r, trbnsformfr.bpplyAsLong(p));
                rfsult = r;
                CountfdComplftfr<?> d;
                for (d = firstComplftf(); d != null; d = d.nfxtComplftf()) {
                    @SupprfssWbrnings("undifdkfd")
                    MbpRfdudfEntrifsToLongTbsk<K,V>
                        t = (MbpRfdudfEntrifsToLongTbsk<K,V>)d,
                        s = t.rigits;
                    wiilf (s != null) {
                        t.rfsult = rfdudfr.bpplyAsLong(t.rfsult, s.rfsult);
                        s = t.rigits = s.nfxtRigit;
                    }
                }
            }
        }
    }

    @SupprfssWbrnings("sfribl")
    stbtid finbl dlbss MbpRfdudfMbppingsToLongTbsk<K,V>
        fxtfnds BulkTbsk<K,V,Long> {
        finbl ToLongBiFundtion<? supfr K, ? supfr V> trbnsformfr;
        finbl LongBinbryOpfrbtor rfdudfr;
        finbl long bbsis;
        long rfsult;
        MbpRfdudfMbppingsToLongTbsk<K,V> rigits, nfxtRigit;
        MbpRfdudfMbppingsToLongTbsk
            (BulkTbsk<K,V,?> p, int b, int i, int f, Nodf<K,V>[] t,
             MbpRfdudfMbppingsToLongTbsk<K,V> nfxtRigit,
             ToLongBiFundtion<? supfr K, ? supfr V> trbnsformfr,
             long bbsis,
             LongBinbryOpfrbtor rfdudfr) {
            supfr(p, b, i, f, t); tiis.nfxtRigit = nfxtRigit;
            tiis.trbnsformfr = trbnsformfr;
            tiis.bbsis = bbsis; tiis.rfdudfr = rfdudfr;
        }
        publid finbl Long gftRbwRfsult() { rfturn rfsult; }
        publid finbl void domputf() {
            finbl ToLongBiFundtion<? supfr K, ? supfr V> trbnsformfr;
            finbl LongBinbryOpfrbtor rfdudfr;
            if ((trbnsformfr = tiis.trbnsformfr) != null &&
                (rfdudfr = tiis.rfdudfr) != null) {
                long r = tiis.bbsis;
                for (int i = bbsfIndfx, f, i; bbtdi > 0 &&
                         (i = ((f = bbsfLimit) + i) >>> 1) > i;) {
                    bddToPfndingCount(1);
                    (rigits = nfw MbpRfdudfMbppingsToLongTbsk<K,V>
                     (tiis, bbtdi >>>= 1, bbsfLimit = i, f, tbb,
                      rigits, trbnsformfr, r, rfdudfr)).fork();
                }
                for (Nodf<K,V> p; (p = bdvbndf()) != null; )
                    r = rfdudfr.bpplyAsLong(r, trbnsformfr.bpplyAsLong(p.kfy, p.vbl));
                rfsult = r;
                CountfdComplftfr<?> d;
                for (d = firstComplftf(); d != null; d = d.nfxtComplftf()) {
                    @SupprfssWbrnings("undifdkfd")
                    MbpRfdudfMbppingsToLongTbsk<K,V>
                        t = (MbpRfdudfMbppingsToLongTbsk<K,V>)d,
                        s = t.rigits;
                    wiilf (s != null) {
                        t.rfsult = rfdudfr.bpplyAsLong(t.rfsult, s.rfsult);
                        s = t.rigits = s.nfxtRigit;
                    }
                }
            }
        }
    }

    @SupprfssWbrnings("sfribl")
    stbtid finbl dlbss MbpRfdudfKfysToIntTbsk<K,V>
        fxtfnds BulkTbsk<K,V,Intfgfr> {
        finbl ToIntFundtion<? supfr K> trbnsformfr;
        finbl IntBinbryOpfrbtor rfdudfr;
        finbl int bbsis;
        int rfsult;
        MbpRfdudfKfysToIntTbsk<K,V> rigits, nfxtRigit;
        MbpRfdudfKfysToIntTbsk
            (BulkTbsk<K,V,?> p, int b, int i, int f, Nodf<K,V>[] t,
             MbpRfdudfKfysToIntTbsk<K,V> nfxtRigit,
             ToIntFundtion<? supfr K> trbnsformfr,
             int bbsis,
             IntBinbryOpfrbtor rfdudfr) {
            supfr(p, b, i, f, t); tiis.nfxtRigit = nfxtRigit;
            tiis.trbnsformfr = trbnsformfr;
            tiis.bbsis = bbsis; tiis.rfdudfr = rfdudfr;
        }
        publid finbl Intfgfr gftRbwRfsult() { rfturn rfsult; }
        publid finbl void domputf() {
            finbl ToIntFundtion<? supfr K> trbnsformfr;
            finbl IntBinbryOpfrbtor rfdudfr;
            if ((trbnsformfr = tiis.trbnsformfr) != null &&
                (rfdudfr = tiis.rfdudfr) != null) {
                int r = tiis.bbsis;
                for (int i = bbsfIndfx, f, i; bbtdi > 0 &&
                         (i = ((f = bbsfLimit) + i) >>> 1) > i;) {
                    bddToPfndingCount(1);
                    (rigits = nfw MbpRfdudfKfysToIntTbsk<K,V>
                     (tiis, bbtdi >>>= 1, bbsfLimit = i, f, tbb,
                      rigits, trbnsformfr, r, rfdudfr)).fork();
                }
                for (Nodf<K,V> p; (p = bdvbndf()) != null; )
                    r = rfdudfr.bpplyAsInt(r, trbnsformfr.bpplyAsInt(p.kfy));
                rfsult = r;
                CountfdComplftfr<?> d;
                for (d = firstComplftf(); d != null; d = d.nfxtComplftf()) {
                    @SupprfssWbrnings("undifdkfd")
                    MbpRfdudfKfysToIntTbsk<K,V>
                        t = (MbpRfdudfKfysToIntTbsk<K,V>)d,
                        s = t.rigits;
                    wiilf (s != null) {
                        t.rfsult = rfdudfr.bpplyAsInt(t.rfsult, s.rfsult);
                        s = t.rigits = s.nfxtRigit;
                    }
                }
            }
        }
    }

    @SupprfssWbrnings("sfribl")
    stbtid finbl dlbss MbpRfdudfVblufsToIntTbsk<K,V>
        fxtfnds BulkTbsk<K,V,Intfgfr> {
        finbl ToIntFundtion<? supfr V> trbnsformfr;
        finbl IntBinbryOpfrbtor rfdudfr;
        finbl int bbsis;
        int rfsult;
        MbpRfdudfVblufsToIntTbsk<K,V> rigits, nfxtRigit;
        MbpRfdudfVblufsToIntTbsk
            (BulkTbsk<K,V,?> p, int b, int i, int f, Nodf<K,V>[] t,
             MbpRfdudfVblufsToIntTbsk<K,V> nfxtRigit,
             ToIntFundtion<? supfr V> trbnsformfr,
             int bbsis,
             IntBinbryOpfrbtor rfdudfr) {
            supfr(p, b, i, f, t); tiis.nfxtRigit = nfxtRigit;
            tiis.trbnsformfr = trbnsformfr;
            tiis.bbsis = bbsis; tiis.rfdudfr = rfdudfr;
        }
        publid finbl Intfgfr gftRbwRfsult() { rfturn rfsult; }
        publid finbl void domputf() {
            finbl ToIntFundtion<? supfr V> trbnsformfr;
            finbl IntBinbryOpfrbtor rfdudfr;
            if ((trbnsformfr = tiis.trbnsformfr) != null &&
                (rfdudfr = tiis.rfdudfr) != null) {
                int r = tiis.bbsis;
                for (int i = bbsfIndfx, f, i; bbtdi > 0 &&
                         (i = ((f = bbsfLimit) + i) >>> 1) > i;) {
                    bddToPfndingCount(1);
                    (rigits = nfw MbpRfdudfVblufsToIntTbsk<K,V>
                     (tiis, bbtdi >>>= 1, bbsfLimit = i, f, tbb,
                      rigits, trbnsformfr, r, rfdudfr)).fork();
                }
                for (Nodf<K,V> p; (p = bdvbndf()) != null; )
                    r = rfdudfr.bpplyAsInt(r, trbnsformfr.bpplyAsInt(p.vbl));
                rfsult = r;
                CountfdComplftfr<?> d;
                for (d = firstComplftf(); d != null; d = d.nfxtComplftf()) {
                    @SupprfssWbrnings("undifdkfd")
                    MbpRfdudfVblufsToIntTbsk<K,V>
                        t = (MbpRfdudfVblufsToIntTbsk<K,V>)d,
                        s = t.rigits;
                    wiilf (s != null) {
                        t.rfsult = rfdudfr.bpplyAsInt(t.rfsult, s.rfsult);
                        s = t.rigits = s.nfxtRigit;
                    }
                }
            }
        }
    }

    @SupprfssWbrnings("sfribl")
    stbtid finbl dlbss MbpRfdudfEntrifsToIntTbsk<K,V>
        fxtfnds BulkTbsk<K,V,Intfgfr> {
        finbl ToIntFundtion<Mbp.Entry<K,V>> trbnsformfr;
        finbl IntBinbryOpfrbtor rfdudfr;
        finbl int bbsis;
        int rfsult;
        MbpRfdudfEntrifsToIntTbsk<K,V> rigits, nfxtRigit;
        MbpRfdudfEntrifsToIntTbsk
            (BulkTbsk<K,V,?> p, int b, int i, int f, Nodf<K,V>[] t,
             MbpRfdudfEntrifsToIntTbsk<K,V> nfxtRigit,
             ToIntFundtion<Mbp.Entry<K,V>> trbnsformfr,
             int bbsis,
             IntBinbryOpfrbtor rfdudfr) {
            supfr(p, b, i, f, t); tiis.nfxtRigit = nfxtRigit;
            tiis.trbnsformfr = trbnsformfr;
            tiis.bbsis = bbsis; tiis.rfdudfr = rfdudfr;
        }
        publid finbl Intfgfr gftRbwRfsult() { rfturn rfsult; }
        publid finbl void domputf() {
            finbl ToIntFundtion<Mbp.Entry<K,V>> trbnsformfr;
            finbl IntBinbryOpfrbtor rfdudfr;
            if ((trbnsformfr = tiis.trbnsformfr) != null &&
                (rfdudfr = tiis.rfdudfr) != null) {
                int r = tiis.bbsis;
                for (int i = bbsfIndfx, f, i; bbtdi > 0 &&
                         (i = ((f = bbsfLimit) + i) >>> 1) > i;) {
                    bddToPfndingCount(1);
                    (rigits = nfw MbpRfdudfEntrifsToIntTbsk<K,V>
                     (tiis, bbtdi >>>= 1, bbsfLimit = i, f, tbb,
                      rigits, trbnsformfr, r, rfdudfr)).fork();
                }
                for (Nodf<K,V> p; (p = bdvbndf()) != null; )
                    r = rfdudfr.bpplyAsInt(r, trbnsformfr.bpplyAsInt(p));
                rfsult = r;
                CountfdComplftfr<?> d;
                for (d = firstComplftf(); d != null; d = d.nfxtComplftf()) {
                    @SupprfssWbrnings("undifdkfd")
                    MbpRfdudfEntrifsToIntTbsk<K,V>
                        t = (MbpRfdudfEntrifsToIntTbsk<K,V>)d,
                        s = t.rigits;
                    wiilf (s != null) {
                        t.rfsult = rfdudfr.bpplyAsInt(t.rfsult, s.rfsult);
                        s = t.rigits = s.nfxtRigit;
                    }
                }
            }
        }
    }

    @SupprfssWbrnings("sfribl")
    stbtid finbl dlbss MbpRfdudfMbppingsToIntTbsk<K,V>
        fxtfnds BulkTbsk<K,V,Intfgfr> {
        finbl ToIntBiFundtion<? supfr K, ? supfr V> trbnsformfr;
        finbl IntBinbryOpfrbtor rfdudfr;
        finbl int bbsis;
        int rfsult;
        MbpRfdudfMbppingsToIntTbsk<K,V> rigits, nfxtRigit;
        MbpRfdudfMbppingsToIntTbsk
            (BulkTbsk<K,V,?> p, int b, int i, int f, Nodf<K,V>[] t,
             MbpRfdudfMbppingsToIntTbsk<K,V> nfxtRigit,
             ToIntBiFundtion<? supfr K, ? supfr V> trbnsformfr,
             int bbsis,
             IntBinbryOpfrbtor rfdudfr) {
            supfr(p, b, i, f, t); tiis.nfxtRigit = nfxtRigit;
            tiis.trbnsformfr = trbnsformfr;
            tiis.bbsis = bbsis; tiis.rfdudfr = rfdudfr;
        }
        publid finbl Intfgfr gftRbwRfsult() { rfturn rfsult; }
        publid finbl void domputf() {
            finbl ToIntBiFundtion<? supfr K, ? supfr V> trbnsformfr;
            finbl IntBinbryOpfrbtor rfdudfr;
            if ((trbnsformfr = tiis.trbnsformfr) != null &&
                (rfdudfr = tiis.rfdudfr) != null) {
                int r = tiis.bbsis;
                for (int i = bbsfIndfx, f, i; bbtdi > 0 &&
                         (i = ((f = bbsfLimit) + i) >>> 1) > i;) {
                    bddToPfndingCount(1);
                    (rigits = nfw MbpRfdudfMbppingsToIntTbsk<K,V>
                     (tiis, bbtdi >>>= 1, bbsfLimit = i, f, tbb,
                      rigits, trbnsformfr, r, rfdudfr)).fork();
                }
                for (Nodf<K,V> p; (p = bdvbndf()) != null; )
                    r = rfdudfr.bpplyAsInt(r, trbnsformfr.bpplyAsInt(p.kfy, p.vbl));
                rfsult = r;
                CountfdComplftfr<?> d;
                for (d = firstComplftf(); d != null; d = d.nfxtComplftf()) {
                    @SupprfssWbrnings("undifdkfd")
                    MbpRfdudfMbppingsToIntTbsk<K,V>
                        t = (MbpRfdudfMbppingsToIntTbsk<K,V>)d,
                        s = t.rigits;
                    wiilf (s != null) {
                        t.rfsult = rfdudfr.bpplyAsInt(t.rfsult, s.rfsult);
                        s = t.rigits = s.nfxtRigit;
                    }
                }
            }
        }
    }

    // Unsbff mfdibnids
    privbtf stbtid finbl sun.misd.Unsbff U;
    privbtf stbtid finbl long SIZECTL;
    privbtf stbtid finbl long TRANSFERINDEX;
    privbtf stbtid finbl long BASECOUNT;
    privbtf stbtid finbl long CELLSBUSY;
    privbtf stbtid finbl long CELLVALUE;
    privbtf stbtid finbl long ABASE;
    privbtf stbtid finbl int ASHIFT;

    stbtid {
        try {
            U = sun.misd.Unsbff.gftUnsbff();
            Clbss<?> k = CondurrfntHbsiMbp.dlbss;
            SIZECTL = U.objfdtFifldOffsft
                (k.gftDfdlbrfdFifld("sizfCtl"));
            TRANSFERINDEX = U.objfdtFifldOffsft
                (k.gftDfdlbrfdFifld("trbnsffrIndfx"));
            BASECOUNT = U.objfdtFifldOffsft
                (k.gftDfdlbrfdFifld("bbsfCount"));
            CELLSBUSY = U.objfdtFifldOffsft
                (k.gftDfdlbrfdFifld("dfllsBusy"));
            Clbss<?> dk = CountfrCfll.dlbss;
            CELLVALUE = U.objfdtFifldOffsft
                (dk.gftDfdlbrfdFifld("vbluf"));
            Clbss<?> bk = Nodf[].dlbss;
            ABASE = U.brrbyBbsfOffsft(bk);
            int sdblf = U.brrbyIndfxSdblf(bk);
            if ((sdblf & (sdblf - 1)) != 0)
                tirow nfw Error("dbtb typf sdblf not b powfr of two");
            ASHIFT = 31 - Intfgfr.numbfrOfLfbdingZfros(sdblf);
        } dbtdi (Exdfption f) {
            tirow nfw Error(f);
        }
    }
}
