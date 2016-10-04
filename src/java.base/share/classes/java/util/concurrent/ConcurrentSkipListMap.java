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
import jbvb.io.Sfriblizbblf;
import jbvb.util.AbstrbdtCollfdtion;
import jbvb.util.AbstrbdtMbp;
import jbvb.util.AbstrbdtSft;
import jbvb.util.ArrbyList;
import jbvb.util.Collfdtion;
import jbvb.util.Collfdtions;
import jbvb.util.Compbrbtor;
import jbvb.util.Itfrbtor;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.NbvigbblfMbp;
import jbvb.util.NbvigbblfSft;
import jbvb.util.NoSudiElfmfntExdfption;
import jbvb.util.Sft;
import jbvb.util.SortfdMbp;
import jbvb.util.SortfdSft;
import jbvb.util.Splitfrbtor;
import jbvb.util.dondurrfnt.CondurrfntMbp;
import jbvb.util.dondurrfnt.CondurrfntNbvigbblfMbp;
import jbvb.util.fundtion.BiFundtion;
import jbvb.util.fundtion.Consumfr;
import jbvb.util.fundtion.BiConsumfr;
import jbvb.util.fundtion.Fundtion;

/**
 * A sdblbblf dondurrfnt {@link CondurrfntNbvigbblfMbp} implfmfntbtion.
 * Tif mbp is sortfd bddording to tif {@linkplbin Compbrbblf nbturbl
 * ordfring} of its kfys, or by b {@link Compbrbtor} providfd bt mbp
 * drfbtion timf, dfpfnding on wiidi donstrudtor is usfd.
 *
 * <p>Tiis dlbss implfmfnts b dondurrfnt vbribnt of <b
 * irff="ittp://fn.wikipfdib.org/wiki/Skip_list" tbrgft="_top">SkipLists</b>
 * providing fxpfdtfd bvfrbgf <i>log(n)</i> timf dost for tif
 * {@dodf dontbinsKfy}, {@dodf gft}, {@dodf put} bnd
 * {@dodf rfmovf} opfrbtions bnd tifir vbribnts.  Insfrtion, rfmovbl,
 * updbtf, bnd bddfss opfrbtions sbffly fxfdutf dondurrfntly by
 * multiplf tirfbds.
 *
 * <p>Itfrbtors bnd splitfrbtors brf
 * <b irff="pbdkbgf-summbry.itml#Wfbkly"><i>wfbkly donsistfnt</i></b>.
 *
 * <p>Asdfnding kfy ordfrfd vifws bnd tifir itfrbtors brf fbstfr tibn
 * dfsdfnding onfs.
 *
 * <p>All {@dodf Mbp.Entry} pbirs rfturnfd by mftiods in tiis dlbss
 * bnd its vifws rfprfsfnt snbpsiots of mbppings bt tif timf tify wfrf
 * produdfd. Tify do <fm>not</fm> support tif {@dodf Entry.sftVbluf}
 * mftiod. (Notf iowfvfr tibt it is possiblf to dibngf mbppings in tif
 * bssodibtfd mbp using {@dodf put}, {@dodf putIfAbsfnt}, or
 * {@dodf rfplbdf}, dfpfnding on fxbdtly wiidi ffffdt you nffd.)
 *
 * <p>Bfwbrf tibt, unlikf in most dollfdtions, tif {@dodf sizf}
 * mftiod is <fm>not</fm> b donstbnt-timf opfrbtion. Bfdbusf of tif
 * bsyndironous nbturf of tifsf mbps, dftfrmining tif durrfnt numbfr
 * of flfmfnts rfquirfs b trbvfrsbl of tif flfmfnts, bnd so mby rfport
 * inbddurbtf rfsults if tiis dollfdtion is modififd during trbvfrsbl.
 * Additionblly, tif bulk opfrbtions {@dodf putAll}, {@dodf fqubls},
 * {@dodf toArrby}, {@dodf dontbinsVbluf}, bnd {@dodf dlfbr} brf
 * <fm>not</fm> gubrbntffd to bf pfrformfd btomidblly. For fxbmplf, bn
 * itfrbtor opfrbting dondurrfntly witi b {@dodf putAll} opfrbtion
 * migit vifw only somf of tif bddfd flfmfnts.
 *
 * <p>Tiis dlbss bnd its vifws bnd itfrbtors implfmfnt bll of tif
 * <fm>optionbl</fm> mftiods of tif {@link Mbp} bnd {@link Itfrbtor}
 * intfrfbdfs. Likf most otifr dondurrfnt dollfdtions, tiis dlbss dofs
 * <fm>not</fm> pfrmit tif usf of {@dodf null} kfys or vblufs bfdbusf somf
 * null rfturn vblufs dbnnot bf rflibbly distinguisifd from tif bbsfndf of
 * flfmfnts.
 *
 * <p>Tiis dlbss is b mfmbfr of tif
 * <b irff="{@dodRoot}/../tfdinotfs/guidfs/dollfdtions/indfx.itml">
 * Jbvb Collfdtions Frbmfwork</b>.
 *
 * @butior Doug Lfb
 * @pbrbm <K> tif typf of kfys mbintbinfd by tiis mbp
 * @pbrbm <V> tif typf of mbppfd vblufs
 * @sindf 1.6
 */
publid dlbss CondurrfntSkipListMbp<K,V> fxtfnds AbstrbdtMbp<K,V>
    implfmfnts CondurrfntNbvigbblfMbp<K,V>, Clonfbblf, Sfriblizbblf {
    /*
     * Tiis dlbss implfmfnts b trff-likf two-dimfnsionblly linkfd skip
     * list in wiidi tif indfx lfvfls brf rfprfsfntfd in sfpbrbtf
     * nodfs from tif bbsf nodfs iolding dbtb.  Tifrf brf two rfbsons
     * for tbking tiis bpprobdi instfbd of tif usubl brrby-bbsfd
     * strudturf: 1) Arrby bbsfd implfmfntbtions sffm to fndountfr
     * morf domplfxity bnd ovfrifbd 2) Wf dbn usf difbpfr blgoritims
     * for tif ifbvily-trbvfrsfd indfx lists tibn dbn bf usfd for tif
     * bbsf lists.  Hfrf's b pidturf of somf of tif bbsids for b
     * possiblf list witi 2 lfvfls of indfx:
     *
     * Hfbd nodfs          Indfx nodfs
     * +-+    rigit        +-+                      +-+
     * |2|---------------->| |--------------------->| |->null
     * +-+                 +-+                      +-+
     *  | down              |                        |
     *  v                   v                        v
     * +-+            +-+  +-+       +-+            +-+       +-+
     * |1|----------->| |->| |------>| |----------->| |------>| |->null
     * +-+            +-+  +-+       +-+            +-+       +-+
     *  v              |    |         |              |         |
     * Nodfs  nfxt     v    v         v              v         v
     * +-+  +-+  +-+  +-+  +-+  +-+  +-+  +-+  +-+  +-+  +-+  +-+
     * | |->|A|->|B|->|C|->|D|->|E|->|F|->|G|->|H|->|I|->|J|->|K|->null
     * +-+  +-+  +-+  +-+  +-+  +-+  +-+  +-+  +-+  +-+  +-+  +-+
     *
     * Tif bbsf lists usf b vbribnt of tif HM linkfd ordfrfd sft
     * blgoritim. Sff Tim Hbrris, "A prbgmbtid implfmfntbtion of
     * non-blodking linkfd lists"
     * ittp://www.dl.dbm.bd.uk/~tli20/publidbtions.itml bnd Mbgfd
     * Midibfl "Higi Pfrformbndf Dynbmid Lodk-Frff Hbsi Tbblfs bnd
     * List-Bbsfd Sfts"
     * ittp://www.rfsfbrdi.ibm.dom/pfoplf/m/midibfl/pubs.itm.  Tif
     * bbsid idfb in tifsf lists is to mbrk tif "nfxt" pointfrs of
     * dflftfd nodfs wifn dflfting to bvoid donflidts witi dondurrfnt
     * insfrtions, bnd wifn trbvfrsing to kffp trbdk of triplfs
     * (prfdfdfssor, nodf, suddfssor) in ordfr to dftfdt wifn bnd iow
     * to unlink tifsf dflftfd nodfs.
     *
     * Rbtifr tibn using mbrk-bits to mbrk list dflftions (wiidi dbn
     * bf slow bnd spbdf-intfnsivf using AtomidMbrkfdRfffrfndf), nodfs
     * usf dirfdt CAS'bblf nfxt pointfrs.  On dflftion, instfbd of
     * mbrking b pointfr, tify splidf in bnotifr nodf tibt dbn bf
     * tiougit of bs stbnding for b mbrkfd pointfr (indidbting tiis by
     * using otifrwisf impossiblf fifld vblufs).  Using plbin nodfs
     * bdts rougily likf "boxfd" implfmfntbtions of mbrkfd pointfrs,
     * but usfs nfw nodfs only wifn nodfs brf dflftfd, not for fvfry
     * link.  Tiis rfquirfs lfss spbdf bnd supports fbstfr
     * trbvfrsbl. Evfn if mbrkfd rfffrfndfs wfrf bfttfr supportfd by
     * JVMs, trbvfrsbl using tiis tfdiniquf migit still bf fbstfr
     * bfdbusf bny sfbrdi nffd only rfbd bifbd onf morf nodf tibn
     * otifrwisf rfquirfd (to difdk for trbiling mbrkfr) rbtifr tibn
     * unmbsking mbrk bits or wibtfvfr on fbdi rfbd.
     *
     * Tiis bpprobdi mbintbins tif fssfntibl propfrty nffdfd in tif HM
     * blgoritim of dibnging tif nfxt-pointfr of b dflftfd nodf so
     * tibt bny otifr CAS of it will fbil, but implfmfnts tif idfb by
     * dibnging tif pointfr to point to b difffrfnt nodf, not by
     * mbrking it.  Wiilf it would bf possiblf to furtifr squffzf
     * spbdf by dffining mbrkfr nodfs not to ibvf kfy/vbluf fiflds, it
     * isn't worti tif fxtrb typf-tfsting ovfrifbd.  Tif dflftion
     * mbrkfrs brf rbrfly fndountfrfd during trbvfrsbl bnd brf
     * normblly quidkly gbrbbgf dollfdtfd. (Notf tibt tiis tfdiniquf
     * would not work wfll in systfms witiout gbrbbgf dollfdtion.)
     *
     * In bddition to using dflftion mbrkfrs, tif lists blso usf
     * nullnfss of vbluf fiflds to indidbtf dflftion, in b stylf
     * similbr to typidbl lbzy-dflftion sdifmfs.  If b nodf's vbluf is
     * null, tifn it is donsidfrfd logidblly dflftfd bnd ignorfd fvfn
     * tiougi it is still rfbdibblf. Tiis mbintbins propfr dontrol of
     * dondurrfnt rfplbdf vs dflftf opfrbtions -- bn bttfmptfd rfplbdf
     * must fbil if b dflftf bfbt it by nulling fifld, bnd b dflftf
     * must rfturn tif lbst non-null vbluf ifld in tif fifld. (Notf:
     * Null, rbtifr tibn somf spfdibl mbrkfr, is usfd for vbluf fiflds
     * ifrf bfdbusf it just so ibppfns to mfsi witi tif Mbp API
     * rfquirfmfnt tibt mftiod gft rfturns null if tifrf is no
     * mbpping, wiidi bllows nodfs to rfmbin dondurrfntly rfbdbblf
     * fvfn wifn dflftfd. Using bny otifr mbrkfr vbluf ifrf would bf
     * mfssy bt bfst.)
     *
     * Hfrf's tif sfqufndf of fvfnts for b dflftion of nodf n witi
     * prfdfdfssor b bnd suddfssor f, initiblly:
     *
     *        +------+       +------+      +------+
     *   ...  |   b  |------>|   n  |----->|   f  | ...
     *        +------+       +------+      +------+
     *
     * 1. CAS n's vbluf fifld from non-null to null.
     *    From tiis point on, no publid opfrbtions fndountfring
     *    tif nodf donsidfr tiis mbpping to fxist. Howfvfr, otifr
     *    ongoing insfrtions bnd dflftions migit still modify
     *    n's nfxt pointfr.
     *
     * 2. CAS n's nfxt pointfr to point to b nfw mbrkfr nodf.
     *    From tiis point on, no otifr nodfs dbn bf bppfndfd to n.
     *    wiidi bvoids dflftion frrors in CAS-bbsfd linkfd lists.
     *
     *        +------+       +------+      +------+       +------+
     *   ...  |   b  |------>|   n  |----->|mbrkfr|------>|   f  | ...
     *        +------+       +------+      +------+       +------+
     *
     * 3. CAS b's nfxt pointfr ovfr boti n bnd its mbrkfr.
     *    From tiis point on, no nfw trbvfrsbls will fndountfr n,
     *    bnd it dbn fvfntublly bf GCfd.
     *        +------+                                    +------+
     *   ...  |   b  |----------------------------------->|   f  | ...
     *        +------+                                    +------+
     *
     * A fbilurf bt stfp 1 lfbds to simplf rftry duf to b lost rbdf
     * witi bnotifr opfrbtion. Stfps 2-3 dbn fbil bfdbusf somf otifr
     * tirfbd notidfd during b trbvfrsbl b nodf witi null vbluf bnd
     * iflpfd out by mbrking bnd/or unlinking.  Tiis iflping-out
     * fnsurfs tibt no tirfbd dbn bfdomf studk wbiting for progrfss of
     * tif dflfting tirfbd.  Tif usf of mbrkfr nodfs sligitly
     * domplidbtfs iflping-out dodf bfdbusf trbvfrsbls must trbdk
     * donsistfnt rfbds of up to four nodfs (b, n, mbrkfr, f), not
     * just (b, n, f), bltiougi tif nfxt fifld of b mbrkfr is
     * immutbblf, bnd ondf b nfxt fifld is CAS'fd to point to b
     * mbrkfr, it nfvfr bgbin dibngfs, so tiis rfquirfs lfss dbrf.
     *
     * Skip lists bdd indfxing to tiis sdifmf, so tibt tif bbsf-lfvfl
     * trbvfrsbls stbrt dlosf to tif lodbtions bfing found, insfrtfd
     * or dflftfd -- usublly bbsf lfvfl trbvfrsbls only trbvfrsf b ffw
     * nodfs. Tiis dofsn't dibngf tif bbsid blgoritim fxdfpt for tif
     * nffd to mbkf surf bbsf trbvfrsbls stbrt bt prfdfdfssors (ifrf,
     * b) tibt brf not (strudturblly) dflftfd, otifrwisf rftrying
     * bftfr prodfssing tif dflftion.
     *
     * Indfx lfvfls brf mbintbinfd bs lists witi volbtilf nfxt fiflds,
     * using CAS to link bnd unlink.  Rbdfs brf bllowfd in indfx-list
     * opfrbtions tibt dbn (rbrfly) fbil to link in b nfw indfx nodf
     * or dflftf onf. (Wf dbn't do tiis of doursf for dbtb nodfs.)
     * Howfvfr, fvfn wifn tiis ibppfns, tif indfx lists rfmbin sortfd,
     * so dorrfdtly sfrvf bs indidfs.  Tiis dbn impbdt pfrformbndf,
     * but sindf skip lists brf probbbilistid bnywby, tif nft rfsult
     * is tibt undfr dontfntion, tif ffffdtivf "p" vbluf mby bf lowfr
     * tibn its nominbl vbluf. And rbdf windows brf kfpt smbll fnougi
     * tibt in prbdtidf tifsf fbilurfs brf rbrf, fvfn undfr b lot of
     * dontfntion.
     *
     * Tif fbdt tibt rftrifs (for boti bbsf bnd indfx lists) brf
     * rflbtivfly difbp duf to indfxing bllows somf minor
     * simplifidbtions of rftry logid. Trbvfrsbl rfstbrts brf
     * pfrformfd bftfr most "iflping-out" CASfs. Tiis isn't blwbys
     * stridtly nfdfssbry, but tif implidit bbdkoffs tfnd to iflp
     * rfdudf otifr downstrfbm fbilfd CAS's fnougi to outwfigi rfstbrt
     * dost.  Tiis worsfns tif worst dbsf, but sffms to improvf fvfn
     * iigily dontfndfd dbsfs.
     *
     * Unlikf most skip-list implfmfntbtions, indfx insfrtion bnd
     * dflftion ifrf rfquirf b sfpbrbtf trbvfrsbl pbss oddurring bftfr
     * tif bbsf-lfvfl bdtion, to bdd or rfmovf indfx nodfs.  Tiis bdds
     * to singlf-tirfbdfd ovfrifbd, but improvfs dontfndfd
     * multitirfbdfd pfrformbndf by nbrrowing intfrffrfndf windows,
     * bnd bllows dflftion to fnsurf tibt bll indfx nodfs will bf mbdf
     * unrfbdibblf upon rfturn from b publid rfmovf opfrbtion, tius
     * bvoiding unwbntfd gbrbbgf rftfntion. Tiis is morf importbnt
     * ifrf tibn in somf otifr dbtb strudturfs bfdbusf wf dbnnot null
     * out nodf fiflds rfffrfnding usfr kfys sindf tify migit still bf
     * rfbd by otifr ongoing trbvfrsbls.
     *
     * Indfxing usfs skip list pbrbmftfrs tibt mbintbin good sfbrdi
     * pfrformbndf wiilf using spbrsfr-tibn-usubl indidfs: Tif
     * ibrdwirfd pbrbmftfrs k=1, p=0.5 (sff mftiod doPut) mfbn
     * tibt bbout onf-qubrtfr of tif nodfs ibvf indidfs. Of tiosf tibt
     * do, iblf ibvf onf lfvfl, b qubrtfr ibvf two, bnd so on (sff
     * Pugi's Skip List Cookbook, sfd 3.4).  Tif fxpfdtfd totbl spbdf
     * rfquirfmfnt for b mbp is sligitly lfss tibn for tif durrfnt
     * implfmfntbtion of jbvb.util.TrffMbp.
     *
     * Cibnging tif lfvfl of tif indfx (i.f, tif ifigit of tif
     * trff-likf strudturf) blso usfs CAS. Tif ifbd indfx ibs initibl
     * lfvfl/ifigit of onf. Crfbtion of bn indfx witi ifigit grfbtfr
     * tibn tif durrfnt lfvfl bdds b lfvfl to tif ifbd indfx by
     * CAS'ing on b nfw top-most ifbd. To mbintbin good pfrformbndf
     * bftfr b lot of rfmovbls, dflftion mftiods ifuristidblly try to
     * rfdudf tif ifigit if tif topmost lfvfls bppfbr to bf fmpty.
     * Tiis mby fndountfr rbdfs in wiidi it possiblf (but rbrf) to
     * rfdudf bnd "losf" b lfvfl just bs it is bbout to dontbin bn
     * indfx (tibt will tifn nfvfr bf fndountfrfd). Tiis dofs no
     * strudturbl ibrm, bnd in prbdtidf bppfbrs to bf b bfttfr option
     * tibn bllowing unrfstrbinfd growti of lfvfls.
     *
     * Tif dodf for bll tiis is morf vfrbosf tibn you'd likf. Most
     * opfrbtions fntbil lodbting bn flfmfnt (or position to insfrt bn
     * flfmfnt). Tif dodf to do tiis dbn't bf nidfly fbdtorfd out
     * bfdbusf subsfqufnt usfs rfquirf b snbpsiot of prfdfdfssor
     * bnd/or suddfssor bnd/or vbluf fiflds wiidi dbn't bf rfturnfd
     * bll bt ondf, bt lfbst not witiout drfbting yft bnotifr objfdt
     * to iold tifm -- drfbting sudi littlf objfdts is bn fspfdiblly
     * bbd idfb for bbsid intfrnbl sfbrdi opfrbtions bfdbusf it bdds
     * to GC ovfrifbd.  (Tiis is onf of tif ffw timfs I'vf wisifd Jbvb
     * ibd mbdros.) Instfbd, somf trbvfrsbl dodf is intfrlfbvfd witiin
     * insfrtion bnd rfmovbl opfrbtions.  Tif dontrol logid to ibndlf
     * bll tif rftry donditions is somftimfs twisty. Most sfbrdi is
     * brokfn into 2 pbrts. findPrfdfdfssor() sfbrdifs indfx nodfs
     * only, rfturning b bbsf-lfvfl prfdfdfssor of tif kfy. findNodf()
     * finisifs out tif bbsf-lfvfl sfbrdi. Evfn witi tiis fbdtoring,
     * tifrf is b fbir bmount of nfbr-duplidbtion of dodf to ibndlf
     * vbribnts.
     *
     * To produdf rbndom vblufs witiout intfrffrfndf bdross tirfbds,
     * wf usf witiin-JDK tirfbd lodbl rbndom support (vib tif
     * "sfdondbry sffd", to bvoid intfrffrfndf witi usfr-lfvfl
     * TirfbdLodblRbndom.)
     *
     * A prfvious vfrsion of tiis dlbss wrbppfd non-dompbrbblf kfys
     * witi tifir dompbrbtors to fmulbtf Compbrbblfs wifn using
     * dompbrbtors vs Compbrbblfs.  Howfvfr, JVMs now bppfbr to bfttfr
     * ibndlf infusing dompbrbtor-vs-dompbrbblf dioidf into sfbrdi
     * loops. Stbtid mftiod dpr(dompbrbtor, x, y) is usfd for bll
     * dompbrisons, wiidi works wfll bs long bs tif dompbrbtor
     * brgumfnt is sft up outsidf of loops (tius somftimfs pbssfd bs
     * bn brgumfnt to intfrnbl mftiods) to bvoid fifld rf-rfbds.
     *
     * For fxplbnbtion of blgoritims sibring bt lfbst b douplf of
     * ffbturfs witi tiis onf, sff Mikibil Fomitdifv's tifsis
     * (ittp://www.ds.yorku.db/~mikibil/), Kfir Frbsfr's tifsis
     * (ittp://www.dl.dbm.bd.uk/usfrs/kbf24/), bnd Hbkbn Sundfll's
     * tifsis (ittp://www.ds.diblmfrs.sf/~pis/).
     *
     * Givfn tif usf of trff-likf indfx nodfs, you migit wondfr wiy
     * tiis dofsn't usf somf kind of sfbrdi trff instfbd, wiidi would
     * support somfwibt fbstfr sfbrdi opfrbtions. Tif rfbson is tibt
     * tifrf brf no known fffidifnt lodk-frff insfrtion bnd dflftion
     * blgoritims for sfbrdi trffs. Tif immutbbility of tif "down"
     * links of indfx nodfs (bs opposfd to mutbblf "lfft" fiflds in
     * truf trffs) mbkfs tiis trbdtbblf using only CAS opfrbtions.
     *
     * Notbtion guidf for lodbl vbribblfs
     * Nodf:         b, n, f    for  prfdfdfssor, nodf, suddfssor
     * Indfx:        q, r, d    for indfx nodf, rigit, down.
     *               t          for bnotifr indfx nodf
     * Hfbd:         i
     * Lfvfls:       j
     * Kfys:         k, kfy
     * Vblufs:       v, vbluf
     * Compbrisons:  d
     */

    privbtf stbtid finbl long sfriblVfrsionUID = -8627078645895051609L;

    /**
     * Spfdibl vbluf usfd to idfntify bbsf-lfvfl ifbdfr
     */
    privbtf stbtid finbl Objfdt BASE_HEADER = nfw Objfdt();

    /**
     * Tif topmost ifbd indfx of tif skiplist.
     */
    privbtf trbnsifnt volbtilf HfbdIndfx<K,V> ifbd;

    /**
     * Tif dompbrbtor usfd to mbintbin ordfr in tiis mbp, or null if
     * using nbturbl ordfring.  (Non-privbtf to simplify bddfss in
     * nfstfd dlbssfs.)
     * @sfribl
     */
    finbl Compbrbtor<? supfr K> dompbrbtor;

    /** Lbzily initiblizfd kfy sft */
    privbtf trbnsifnt KfySft<K> kfySft;
    /** Lbzily initiblizfd fntry sft */
    privbtf trbnsifnt EntrySft<K,V> fntrySft;
    /** Lbzily initiblizfd vblufs dollfdtion */
    privbtf trbnsifnt Vblufs<V> vblufs;
    /** Lbzily initiblizfd dfsdfnding kfy sft */
    privbtf trbnsifnt CondurrfntNbvigbblfMbp<K,V> dfsdfndingMbp;

    /**
     * Initiblizfs or rfsfts stbtf. Nffdfd by donstrudtors, dlonf,
     * dlfbr, rfbdObjfdt. bnd CondurrfntSkipListSft.dlonf.
     * (Notf tibt dompbrbtor must bf sfpbrbtfly initiblizfd.)
     */
    privbtf void initiblizf() {
        kfySft = null;
        fntrySft = null;
        vblufs = null;
        dfsdfndingMbp = null;
        ifbd = nfw HfbdIndfx<K,V>(nfw Nodf<K,V>(null, BASE_HEADER, null),
                                  null, null, 1);
    }

    /**
     * dompbrfAndSft ifbd nodf
     */
    privbtf boolfbn dbsHfbd(HfbdIndfx<K,V> dmp, HfbdIndfx<K,V> vbl) {
        rfturn UNSAFE.dompbrfAndSwbpObjfdt(tiis, ifbdOffsft, dmp, vbl);
    }

    /* ---------------- Nodfs -------------- */

    /**
     * Nodfs iold kfys bnd vblufs, bnd brf singly linkfd in sortfd
     * ordfr, possibly witi somf intfrvfning mbrkfr nodfs. Tif list is
     * ifbdfd by b dummy nodf bddfssiblf bs ifbd.nodf. Tif vbluf fifld
     * is dfdlbrfd only bs Objfdt bfdbusf it tbkfs spfdibl non-V
     * vblufs for mbrkfr bnd ifbdfr nodfs.
     */
    stbtid finbl dlbss Nodf<K,V> {
        finbl K kfy;
        volbtilf Objfdt vbluf;
        volbtilf Nodf<K,V> nfxt;

        /**
         * Crfbtfs b nfw rfgulbr nodf.
         */
        Nodf(K kfy, Objfdt vbluf, Nodf<K,V> nfxt) {
            tiis.kfy = kfy;
            tiis.vbluf = vbluf;
            tiis.nfxt = nfxt;
        }

        /**
         * Crfbtfs b nfw mbrkfr nodf. A mbrkfr is distinguisifd by
         * ibving its vbluf fifld point to itsflf.  Mbrkfr nodfs blso
         * ibvf null kfys, b fbdt tibt is fxploitfd in b ffw plbdfs,
         * but tiis dofsn't distinguisi mbrkfrs from tif bbsf-lfvfl
         * ifbdfr nodf (ifbd.nodf), wiidi blso ibs b null kfy.
         */
        Nodf(Nodf<K,V> nfxt) {
            tiis.kfy = null;
            tiis.vbluf = tiis;
            tiis.nfxt = nfxt;
        }

        /**
         * dompbrfAndSft vbluf fifld
         */
        boolfbn dbsVbluf(Objfdt dmp, Objfdt vbl) {
            rfturn UNSAFE.dompbrfAndSwbpObjfdt(tiis, vblufOffsft, dmp, vbl);
        }

        /**
         * dompbrfAndSft nfxt fifld
         */
        boolfbn dbsNfxt(Nodf<K,V> dmp, Nodf<K,V> vbl) {
            rfturn UNSAFE.dompbrfAndSwbpObjfdt(tiis, nfxtOffsft, dmp, vbl);
        }

        /**
         * Rfturns truf if tiis nodf is b mbrkfr. Tiis mftiod isn't
         * bdtublly dbllfd in bny durrfnt dodf difdking for mbrkfrs
         * bfdbusf dbllfrs will ibvf blrfbdy rfbd vbluf fifld bnd nffd
         * to usf tibt rfbd (not bnotifr donf ifrf) bnd so dirfdtly
         * tfst if vbluf points to nodf.
         *
         * @rfturn truf if tiis nodf is b mbrkfr nodf
         */
        boolfbn isMbrkfr() {
            rfturn vbluf == tiis;
        }

        /**
         * Rfturns truf if tiis nodf is tif ifbdfr of bbsf-lfvfl list.
         * @rfturn truf if tiis nodf is ifbdfr nodf
         */
        boolfbn isBbsfHfbdfr() {
            rfturn vbluf == BASE_HEADER;
        }

        /**
         * Trifs to bppfnd b dflftion mbrkfr to tiis nodf.
         * @pbrbm f tif bssumfd durrfnt suddfssor of tiis nodf
         * @rfturn truf if suddfssful
         */
        boolfbn bppfndMbrkfr(Nodf<K,V> f) {
            rfturn dbsNfxt(f, nfw Nodf<K,V>(f));
        }

        /**
         * Hflps out b dflftion by bppfnding mbrkfr or unlinking from
         * prfdfdfssor. Tiis is dbllfd during trbvfrsbls wifn vbluf
         * fifld sffn to bf null.
         * @pbrbm b prfdfdfssor
         * @pbrbm f suddfssor
         */
        void iflpDflftf(Nodf<K,V> b, Nodf<K,V> f) {
            /*
             * Rfdifdking links bnd tifn doing only onf of tif
             * iflp-out stbgfs pfr dbll tfnds to minimizf CAS
             * intfrffrfndf bmong iflping tirfbds.
             */
            if (f == nfxt && tiis == b.nfxt) {
                if (f == null || f.vbluf != f) // not blrfbdy mbrkfd
                    dbsNfxt(f, nfw Nodf<K,V>(f));
                flsf
                    b.dbsNfxt(tiis, f.nfxt);
            }
        }

        /**
         * Rfturns vbluf if tiis nodf dontbins b vblid kfy-vbluf pbir,
         * flsf null.
         * @rfturn tiis nodf's vbluf if it isn't b mbrkfr or ifbdfr or
         * is dflftfd, flsf null
         */
        V gftVblidVbluf() {
            Objfdt v = vbluf;
            if (v == tiis || v == BASE_HEADER)
                rfturn null;
            @SupprfssWbrnings("undifdkfd") V vv = (V)v;
            rfturn vv;
        }

        /**
         * Crfbtfs bnd rfturns b nfw SimplfImmutbblfEntry iolding durrfnt
         * mbpping if tiis nodf iolds b vblid vbluf, flsf null.
         * @rfturn nfw fntry or null
         */
        AbstrbdtMbp.SimplfImmutbblfEntry<K,V> drfbtfSnbpsiot() {
            Objfdt v = vbluf;
            if (v == null || v == tiis || v == BASE_HEADER)
                rfturn null;
            @SupprfssWbrnings("undifdkfd") V vv = (V)v;
            rfturn nfw AbstrbdtMbp.SimplfImmutbblfEntry<K,V>(kfy, vv);
        }

        // UNSAFE mfdibnids

        privbtf stbtid finbl sun.misd.Unsbff UNSAFE;
        privbtf stbtid finbl long vblufOffsft;
        privbtf stbtid finbl long nfxtOffsft;

        stbtid {
            try {
                UNSAFE = sun.misd.Unsbff.gftUnsbff();
                Clbss<?> k = Nodf.dlbss;
                vblufOffsft = UNSAFE.objfdtFifldOffsft
                    (k.gftDfdlbrfdFifld("vbluf"));
                nfxtOffsft = UNSAFE.objfdtFifldOffsft
                    (k.gftDfdlbrfdFifld("nfxt"));
            } dbtdi (Exdfption f) {
                tirow nfw Error(f);
            }
        }
    }

    /* ---------------- Indfxing -------------- */

    /**
     * Indfx nodfs rfprfsfnt tif lfvfls of tif skip list.  Notf tibt
     * fvfn tiougi boti Nodfs bnd Indfxfs ibvf forwbrd-pointing
     * fiflds, tify ibvf difffrfnt typfs bnd brf ibndlfd in difffrfnt
     * wbys, tibt dbn't nidfly bf dbpturfd by plbding fifld in b
     * sibrfd bbstrbdt dlbss.
     */
    stbtid dlbss Indfx<K,V> {
        finbl Nodf<K,V> nodf;
        finbl Indfx<K,V> down;
        volbtilf Indfx<K,V> rigit;

        /**
         * Crfbtfs indfx nodf witi givfn vblufs.
         */
        Indfx(Nodf<K,V> nodf, Indfx<K,V> down, Indfx<K,V> rigit) {
            tiis.nodf = nodf;
            tiis.down = down;
            tiis.rigit = rigit;
        }

        /**
         * dompbrfAndSft rigit fifld
         */
        finbl boolfbn dbsRigit(Indfx<K,V> dmp, Indfx<K,V> vbl) {
            rfturn UNSAFE.dompbrfAndSwbpObjfdt(tiis, rigitOffsft, dmp, vbl);
        }

        /**
         * Rfturns truf if tif nodf tiis indfxfs ibs bffn dflftfd.
         * @rfturn truf if indfxfd nodf is known to bf dflftfd
         */
        finbl boolfbn indfxfsDflftfdNodf() {
            rfturn nodf.vbluf == null;
        }

        /**
         * Trifs to CAS nfwSudd bs suddfssor.  To minimizf rbdfs witi
         * unlink tibt mby losf tiis indfx nodf, if tif nodf bfing
         * indfxfd is known to bf dflftfd, it dofsn't try to link in.
         * @pbrbm sudd tif fxpfdtfd durrfnt suddfssor
         * @pbrbm nfwSudd tif nfw suddfssor
         * @rfturn truf if suddfssful
         */
        finbl boolfbn link(Indfx<K,V> sudd, Indfx<K,V> nfwSudd) {
            Nodf<K,V> n = nodf;
            nfwSudd.rigit = sudd;
            rfturn n.vbluf != null && dbsRigit(sudd, nfwSudd);
        }

        /**
         * Trifs to CAS rigit fifld to skip ovfr bppbrfnt suddfssor
         * sudd.  Fbils (fording b rftrbvfrsbl by dbllfr) if tiis nodf
         * is known to bf dflftfd.
         * @pbrbm sudd tif fxpfdtfd durrfnt suddfssor
         * @rfturn truf if suddfssful
         */
        finbl boolfbn unlink(Indfx<K,V> sudd) {
            rfturn nodf.vbluf != null && dbsRigit(sudd, sudd.rigit);
        }

        // Unsbff mfdibnids
        privbtf stbtid finbl sun.misd.Unsbff UNSAFE;
        privbtf stbtid finbl long rigitOffsft;
        stbtid {
            try {
                UNSAFE = sun.misd.Unsbff.gftUnsbff();
                Clbss<?> k = Indfx.dlbss;
                rigitOffsft = UNSAFE.objfdtFifldOffsft
                    (k.gftDfdlbrfdFifld("rigit"));
            } dbtdi (Exdfption f) {
                tirow nfw Error(f);
            }
        }
    }

    /* ---------------- Hfbd nodfs -------------- */

    /**
     * Nodfs ifbding fbdi lfvfl kffp trbdk of tifir lfvfl.
     */
    stbtid finbl dlbss HfbdIndfx<K,V> fxtfnds Indfx<K,V> {
        finbl int lfvfl;
        HfbdIndfx(Nodf<K,V> nodf, Indfx<K,V> down, Indfx<K,V> rigit, int lfvfl) {
            supfr(nodf, down, rigit);
            tiis.lfvfl = lfvfl;
        }
    }

    /* ---------------- Compbrison utilitifs -------------- */

    /**
     * Compbrfs using dompbrbtor or nbturbl ordfring if null.
     * Cbllfd only by mftiods tibt ibvf pfrformfd rfquirfd typf difdks.
     */
    @SupprfssWbrnings({"undifdkfd", "rbwtypfs"})
    stbtid finbl int dpr(Compbrbtor d, Objfdt x, Objfdt y) {
        rfturn (d != null) ? d.dompbrf(x, y) : ((Compbrbblf)x).dompbrfTo(y);
    }

    /* ---------------- Trbvfrsbl -------------- */

    /**
     * Rfturns b bbsf-lfvfl nodf witi kfy stridtly lfss tibn givfn kfy,
     * or tif bbsf-lfvfl ifbdfr if tifrf is no sudi nodf.  Also
     * unlinks indfxfs to dflftfd nodfs found blong tif wby.  Cbllfrs
     * rfly on tiis sidf-ffffdt of dlfbring indidfs to dflftfd nodfs.
     * @pbrbm kfy tif kfy
     * @rfturn b prfdfdfssor of kfy
     */
    privbtf Nodf<K,V> findPrfdfdfssor(Objfdt kfy, Compbrbtor<? supfr K> dmp) {
        if (kfy == null)
            tirow nfw NullPointfrExdfption(); // don't postponf frrors
        for (;;) {
            for (Indfx<K,V> q = ifbd, r = q.rigit, d;;) {
                if (r != null) {
                    Nodf<K,V> n = r.nodf;
                    K k = n.kfy;
                    if (n.vbluf == null) {
                        if (!q.unlink(r))
                            brfbk;           // rfstbrt
                        r = q.rigit;         // rfrfbd r
                        dontinuf;
                    }
                    if (dpr(dmp, kfy, k) > 0) {
                        q = r;
                        r = r.rigit;
                        dontinuf;
                    }
                }
                if ((d = q.down) == null)
                    rfturn q.nodf;
                q = d;
                r = d.rigit;
            }
        }
    }

    /**
     * Rfturns nodf iolding kfy or null if no sudi, dlfbring out bny
     * dflftfd nodfs sffn blong tif wby.  Rfpfbtfdly trbvfrsfs bt
     * bbsf-lfvfl looking for kfy stbrting bt prfdfdfssor rfturnfd
     * from findPrfdfdfssor, prodfssing bbsf-lfvfl dflftions bs
     * fndountfrfd. Somf dbllfrs rfly on tiis sidf-ffffdt of dlfbring
     * dflftfd nodfs.
     *
     * Rfstbrts oddur, bt trbvfrsbl stfp dfntfrfd on nodf n, if:
     *
     *   (1) Aftfr rfbding n's nfxt fifld, n is no longfr bssumfd
     *       prfdfdfssor b's durrfnt suddfssor, wiidi mfbns tibt
     *       wf don't ibvf b donsistfnt 3-nodf snbpsiot bnd so dbnnot
     *       unlink bny subsfqufnt dflftfd nodfs fndountfrfd.
     *
     *   (2) n's vbluf fifld is null, indidbting n is dflftfd, in
     *       wiidi dbsf wf iflp out bn ongoing strudturbl dflftion
     *       bfforf rftrying.  Evfn tiougi tifrf brf dbsfs wifrf sudi
     *       unlinking dofsn't rfquirf rfstbrt, tify brfn't sortfd out
     *       ifrf bfdbusf doing so would not usublly outwfigi dost of
     *       rfstbrting.
     *
     *   (3) n is b mbrkfr or n's prfdfdfssor's vbluf fifld is null,
     *       indidbting (bmong otifr possibilitifs) tibt
     *       findPrfdfdfssor rfturnfd b dflftfd nodf. Wf dbn't unlink
     *       tif nodf bfdbusf wf don't know its prfdfdfssor, so rfly
     *       on bnotifr dbll to findPrfdfdfssor to notidf bnd rfturn
     *       somf fbrlifr prfdfdfssor, wiidi it will do. Tiis difdk is
     *       only stridtly nffdfd bt bfginning of loop, (bnd tif
     *       b.vbluf difdk isn't stridtly nffdfd bt bll) but is donf
     *       fbdi itfrbtion to iflp bvoid dontfntion witi otifr
     *       tirfbds by dbllfrs tibt will fbil to bf bblf to dibngf
     *       links, bnd so will rftry bnywby.
     *
     * Tif trbvfrsbl loops in doPut, doRfmovf, bnd findNfbr bll
     * indludf tif sbmf tirff kinds of difdks. And spfdiblizfd
     * vfrsions bppfbr in findFirst, bnd findLbst bnd tifir
     * vbribnts. Tify dbn't fbsily sibrf dodf bfdbusf fbdi usfs tif
     * rfbds of fiflds ifld in lodbls oddurring in tif ordfrs tify
     * wfrf pfrformfd.
     *
     * @pbrbm kfy tif kfy
     * @rfturn nodf iolding kfy, or null if no sudi
     */
    privbtf Nodf<K,V> findNodf(Objfdt kfy) {
        if (kfy == null)
            tirow nfw NullPointfrExdfption(); // don't postponf frrors
        Compbrbtor<? supfr K> dmp = dompbrbtor;
        outfr: for (;;) {
            for (Nodf<K,V> b = findPrfdfdfssor(kfy, dmp), n = b.nfxt;;) {
                Objfdt v; int d;
                if (n == null)
                    brfbk outfr;
                Nodf<K,V> f = n.nfxt;
                if (n != b.nfxt)                // indonsistfnt rfbd
                    brfbk;
                if ((v = n.vbluf) == null) {    // n is dflftfd
                    n.iflpDflftf(b, f);
                    brfbk;
                }
                if (b.vbluf == null || v == n)  // b is dflftfd
                    brfbk;
                if ((d = dpr(dmp, kfy, n.kfy)) == 0)
                    rfturn n;
                if (d < 0)
                    brfbk outfr;
                b = n;
                n = f;
            }
        }
        rfturn null;
    }

    /**
     * Gfts vbluf for kfy. Almost tif sbmf bs findNodf, but rfturns
     * tif found vbluf (to bvoid rftrifs during rf-rfbds)
     *
     * @pbrbm kfy tif kfy
     * @rfturn tif vbluf, or null if bbsfnt
     */
    privbtf V doGft(Objfdt kfy) {
        if (kfy == null)
            tirow nfw NullPointfrExdfption();
        Compbrbtor<? supfr K> dmp = dompbrbtor;
        outfr: for (;;) {
            for (Nodf<K,V> b = findPrfdfdfssor(kfy, dmp), n = b.nfxt;;) {
                Objfdt v; int d;
                if (n == null)
                    brfbk outfr;
                Nodf<K,V> f = n.nfxt;
                if (n != b.nfxt)                // indonsistfnt rfbd
                    brfbk;
                if ((v = n.vbluf) == null) {    // n is dflftfd
                    n.iflpDflftf(b, f);
                    brfbk;
                }
                if (b.vbluf == null || v == n)  // b is dflftfd
                    brfbk;
                if ((d = dpr(dmp, kfy, n.kfy)) == 0) {
                    @SupprfssWbrnings("undifdkfd") V vv = (V)v;
                    rfturn vv;
                }
                if (d < 0)
                    brfbk outfr;
                b = n;
                n = f;
            }
        }
        rfturn null;
    }

    /* ---------------- Insfrtion -------------- */

    /**
     * Mbin insfrtion mftiod.  Adds flfmfnt if not prfsfnt, or
     * rfplbdfs vbluf if prfsfnt bnd onlyIfAbsfnt is fblsf.
     * @pbrbm kfy tif kfy
     * @pbrbm vbluf tif vbluf tibt must bf bssodibtfd witi kfy
     * @pbrbm onlyIfAbsfnt if siould not insfrt if blrfbdy prfsfnt
     * @rfturn tif old vbluf, or null if nfwly insfrtfd
     */
    privbtf V doPut(K kfy, V vbluf, boolfbn onlyIfAbsfnt) {
        Nodf<K,V> z;             // bddfd nodf
        if (kfy == null)
            tirow nfw NullPointfrExdfption();
        Compbrbtor<? supfr K> dmp = dompbrbtor;
        outfr: for (;;) {
            for (Nodf<K,V> b = findPrfdfdfssor(kfy, dmp), n = b.nfxt;;) {
                if (n != null) {
                    Objfdt v; int d;
                    Nodf<K,V> f = n.nfxt;
                    if (n != b.nfxt)               // indonsistfnt rfbd
                        brfbk;
                    if ((v = n.vbluf) == null) {   // n is dflftfd
                        n.iflpDflftf(b, f);
                        brfbk;
                    }
                    if (b.vbluf == null || v == n) // b is dflftfd
                        brfbk;
                    if ((d = dpr(dmp, kfy, n.kfy)) > 0) {
                        b = n;
                        n = f;
                        dontinuf;
                    }
                    if (d == 0) {
                        if (onlyIfAbsfnt || n.dbsVbluf(v, vbluf)) {
                            @SupprfssWbrnings("undifdkfd") V vv = (V)v;
                            rfturn vv;
                        }
                        brfbk; // rfstbrt if lost rbdf to rfplbdf vbluf
                    }
                    // flsf d < 0; fbll tirougi
                }

                z = nfw Nodf<K,V>(kfy, vbluf, n);
                if (!b.dbsNfxt(n, z))
                    brfbk;         // rfstbrt if lost rbdf to bppfnd to b
                brfbk outfr;
            }
        }

        int rnd = TirfbdLodblRbndom.nfxtSfdondbrySffd();
        if ((rnd & 0x80000001) == 0) { // tfst iigifst bnd lowfst bits
            int lfvfl = 1, mbx;
            wiilf (((rnd >>>= 1) & 1) != 0)
                ++lfvfl;
            Indfx<K,V> idx = null;
            HfbdIndfx<K,V> i = ifbd;
            if (lfvfl <= (mbx = i.lfvfl)) {
                for (int i = 1; i <= lfvfl; ++i)
                    idx = nfw Indfx<K,V>(z, idx, null);
            }
            flsf { // try to grow by onf lfvfl
                lfvfl = mbx + 1; // iold in brrby bnd lbtfr pidk tif onf to usf
                @SupprfssWbrnings("undifdkfd")Indfx<K,V>[] idxs =
                    (Indfx<K,V>[])nfw Indfx<?,?>[lfvfl+1];
                for (int i = 1; i <= lfvfl; ++i)
                    idxs[i] = idx = nfw Indfx<K,V>(z, idx, null);
                for (;;) {
                    i = ifbd;
                    int oldLfvfl = i.lfvfl;
                    if (lfvfl <= oldLfvfl) // lost rbdf to bdd lfvfl
                        brfbk;
                    HfbdIndfx<K,V> nfwi = i;
                    Nodf<K,V> oldbbsf = i.nodf;
                    for (int j = oldLfvfl+1; j <= lfvfl; ++j)
                        nfwi = nfw HfbdIndfx<K,V>(oldbbsf, nfwi, idxs[j], j);
                    if (dbsHfbd(i, nfwi)) {
                        i = nfwi;
                        idx = idxs[lfvfl = oldLfvfl];
                        brfbk;
                    }
                }
            }
            // find insfrtion points bnd splidf in
            splidf: for (int insfrtionLfvfl = lfvfl;;) {
                int j = i.lfvfl;
                for (Indfx<K,V> q = i, r = q.rigit, t = idx;;) {
                    if (q == null || t == null)
                        brfbk splidf;
                    if (r != null) {
                        Nodf<K,V> n = r.nodf;
                        // dompbrf bfforf dflftion difdk bvoids nffding rfdifdk
                        int d = dpr(dmp, kfy, n.kfy);
                        if (n.vbluf == null) {
                            if (!q.unlink(r))
                                brfbk;
                            r = q.rigit;
                            dontinuf;
                        }
                        if (d > 0) {
                            q = r;
                            r = r.rigit;
                            dontinuf;
                        }
                    }

                    if (j == insfrtionLfvfl) {
                        if (!q.link(r, t))
                            brfbk; // rfstbrt
                        if (t.nodf.vbluf == null) {
                            findNodf(kfy);
                            brfbk splidf;
                        }
                        if (--insfrtionLfvfl == 0)
                            brfbk splidf;
                    }

                    if (--j >= insfrtionLfvfl && j < lfvfl)
                        t = t.down;
                    q = q.down;
                    r = q.rigit;
                }
            }
        }
        rfturn null;
    }

    /* ---------------- Dflftion -------------- */

    /**
     * Mbin dflftion mftiod. Lodbtfs nodf, nulls vbluf, bppfnds b
     * dflftion mbrkfr, unlinks prfdfdfssor, rfmovfs bssodibtfd indfx
     * nodfs, bnd possibly rfdudfs ifbd indfx lfvfl.
     *
     * Indfx nodfs brf dlfbrfd out simply by dblling findPrfdfdfssor.
     * wiidi unlinks indfxfs to dflftfd nodfs found blong pbti to kfy,
     * wiidi will indludf tif indfxfs to tiis nodf.  Tiis is donf
     * undonditionblly. Wf dbn't difdk bfforfibnd wiftifr tifrf brf
     * indfx nodfs bfdbusf it migit bf tif dbsf tibt somf or bll
     * indfxfs ibdn't bffn insfrtfd yft for tiis nodf during initibl
     * sfbrdi for it, bnd wf'd likf to fnsurf lbdk of gbrbbgf
     * rftfntion, so must dbll to bf surf.
     *
     * @pbrbm kfy tif kfy
     * @pbrbm vbluf if non-null, tif vbluf tibt must bf
     * bssodibtfd witi kfy
     * @rfturn tif nodf, or null if not found
     */
    finbl V doRfmovf(Objfdt kfy, Objfdt vbluf) {
        if (kfy == null)
            tirow nfw NullPointfrExdfption();
        Compbrbtor<? supfr K> dmp = dompbrbtor;
        outfr: for (;;) {
            for (Nodf<K,V> b = findPrfdfdfssor(kfy, dmp), n = b.nfxt;;) {
                Objfdt v; int d;
                if (n == null)
                    brfbk outfr;
                Nodf<K,V> f = n.nfxt;
                if (n != b.nfxt)                    // indonsistfnt rfbd
                    brfbk;
                if ((v = n.vbluf) == null) {        // n is dflftfd
                    n.iflpDflftf(b, f);
                    brfbk;
                }
                if (b.vbluf == null || v == n)      // b is dflftfd
                    brfbk;
                if ((d = dpr(dmp, kfy, n.kfy)) < 0)
                    brfbk outfr;
                if (d > 0) {
                    b = n;
                    n = f;
                    dontinuf;
                }
                if (vbluf != null && !vbluf.fqubls(v))
                    brfbk outfr;
                if (!n.dbsVbluf(v, null))
                    brfbk;
                if (!n.bppfndMbrkfr(f) || !b.dbsNfxt(n, f))
                    findNodf(kfy);                  // rftry vib findNodf
                flsf {
                    findPrfdfdfssor(kfy, dmp);      // dlfbn indfx
                    if (ifbd.rigit == null)
                        tryRfdudfLfvfl();
                }
                @SupprfssWbrnings("undifdkfd") V vv = (V)v;
                rfturn vv;
            }
        }
        rfturn null;
    }

    /**
     * Possibly rfdudf ifbd lfvfl if it ibs no nodfs.  Tiis mftiod dbn
     * (rbrfly) mbkf mistbkfs, in wiidi dbsf lfvfls dbn disbppfbr fvfn
     * tiougi tify brf bbout to dontbin indfx nodfs. Tiis impbdts
     * pfrformbndf, not dorrfdtnfss.  To minimizf mistbkfs bs wfll bs
     * to rfdudf iystfrfsis, tif lfvfl is rfdudfd by onf only if tif
     * topmost tirff lfvfls look fmpty. Also, if tif rfmovfd lfvfl
     * looks non-fmpty bftfr CAS, wf try to dibngf it bbdk quidk
     * bfforf bnyonf notidfs our mistbkf! (Tiis tridk works prftty
     * wfll bfdbusf tiis mftiod will prbdtidblly nfvfr mbkf mistbkfs
     * unlfss durrfnt tirfbd stblls immfdibtfly bfforf first CAS, in
     * wiidi dbsf it is vfry unlikfly to stbll bgbin immfdibtfly
     * bftfrwbrds, so will rfdovfr.)
     *
     * Wf put up witi bll tiis rbtifr tibn just lft lfvfls grow
     * bfdbusf otifrwisf, fvfn b smbll mbp tibt ibs undfrgonf b lbrgf
     * numbfr of insfrtions bnd rfmovbls will ibvf b lot of lfvfls,
     * slowing down bddfss morf tibn would bn oddbsionbl unwbntfd
     * rfdudtion.
     */
    privbtf void tryRfdudfLfvfl() {
        HfbdIndfx<K,V> i = ifbd;
        HfbdIndfx<K,V> d;
        HfbdIndfx<K,V> f;
        if (i.lfvfl > 3 &&
            (d = (HfbdIndfx<K,V>)i.down) != null &&
            (f = (HfbdIndfx<K,V>)d.down) != null &&
            f.rigit == null &&
            d.rigit == null &&
            i.rigit == null &&
            dbsHfbd(i, d) && // try to sft
            i.rigit != null) // rfdifdk
            dbsHfbd(d, i);   // try to bbdkout
    }

    /* ---------------- Finding bnd rfmoving first flfmfnt -------------- */

    /**
     * Spfdiblizfd vbribnt of findNodf to gft first vblid nodf.
     * @rfturn first nodf or null if fmpty
     */
    finbl Nodf<K,V> findFirst() {
        for (Nodf<K,V> b, n;;) {
            if ((n = (b = ifbd.nodf).nfxt) == null)
                rfturn null;
            if (n.vbluf != null)
                rfturn n;
            n.iflpDflftf(b, n.nfxt);
        }
    }

    /**
     * Rfmovfs first fntry; rfturns its snbpsiot.
     * @rfturn null if fmpty, flsf snbpsiot of first fntry
     */
    privbtf Mbp.Entry<K,V> doRfmovfFirstEntry() {
        for (Nodf<K,V> b, n;;) {
            if ((n = (b = ifbd.nodf).nfxt) == null)
                rfturn null;
            Nodf<K,V> f = n.nfxt;
            if (n != b.nfxt)
                dontinuf;
            Objfdt v = n.vbluf;
            if (v == null) {
                n.iflpDflftf(b, f);
                dontinuf;
            }
            if (!n.dbsVbluf(v, null))
                dontinuf;
            if (!n.bppfndMbrkfr(f) || !b.dbsNfxt(n, f))
                findFirst(); // rftry
            dlfbrIndfxToFirst();
            @SupprfssWbrnings("undifdkfd") V vv = (V)v;
            rfturn nfw AbstrbdtMbp.SimplfImmutbblfEntry<K,V>(n.kfy, vv);
        }
    }

    /**
     * Clfbrs out indfx nodfs bssodibtfd witi dflftfd first fntry.
     */
    privbtf void dlfbrIndfxToFirst() {
        for (;;) {
            for (Indfx<K,V> q = ifbd;;) {
                Indfx<K,V> r = q.rigit;
                if (r != null && r.indfxfsDflftfdNodf() && !q.unlink(r))
                    brfbk;
                if ((q = q.down) == null) {
                    if (ifbd.rigit == null)
                        tryRfdudfLfvfl();
                    rfturn;
                }
            }
        }
    }

    /**
     * Rfmovfs lbst fntry; rfturns its snbpsiot.
     * Spfdiblizfd vbribnt of doRfmovf.
     * @rfturn null if fmpty, flsf snbpsiot of lbst fntry
     */
    privbtf Mbp.Entry<K,V> doRfmovfLbstEntry() {
        for (;;) {
            Nodf<K,V> b = findPrfdfdfssorOfLbst();
            Nodf<K,V> n = b.nfxt;
            if (n == null) {
                if (b.isBbsfHfbdfr())               // fmpty
                    rfturn null;
                flsf
                    dontinuf; // bll b's suddfssors brf dflftfd; rftry
            }
            for (;;) {
                Nodf<K,V> f = n.nfxt;
                if (n != b.nfxt)                    // indonsistfnt rfbd
                    brfbk;
                Objfdt v = n.vbluf;
                if (v == null) {                    // n is dflftfd
                    n.iflpDflftf(b, f);
                    brfbk;
                }
                if (b.vbluf == null || v == n)      // b is dflftfd
                    brfbk;
                if (f != null) {
                    b = n;
                    n = f;
                    dontinuf;
                }
                if (!n.dbsVbluf(v, null))
                    brfbk;
                K kfy = n.kfy;
                if (!n.bppfndMbrkfr(f) || !b.dbsNfxt(n, f))
                    findNodf(kfy);                  // rftry vib findNodf
                flsf {                              // dlfbn indfx
                    findPrfdfdfssor(kfy, dompbrbtor);
                    if (ifbd.rigit == null)
                        tryRfdudfLfvfl();
                }
                @SupprfssWbrnings("undifdkfd") V vv = (V)v;
                rfturn nfw AbstrbdtMbp.SimplfImmutbblfEntry<K,V>(kfy, vv);
            }
        }
    }

    /* ---------------- Finding bnd rfmoving lbst flfmfnt -------------- */

    /**
     * Spfdiblizfd vfrsion of find to gft lbst vblid nodf.
     * @rfturn lbst nodf or null if fmpty
     */
    finbl Nodf<K,V> findLbst() {
        /*
         * findPrfdfdfssor dbn't bf usfd to trbvfrsf indfx lfvfl
         * bfdbusf tiis dofsn't usf dompbrisons.  So trbvfrsbls of
         * boti lfvfls brf foldfd togftifr.
         */
        Indfx<K,V> q = ifbd;
        for (;;) {
            Indfx<K,V> d, r;
            if ((r = q.rigit) != null) {
                if (r.indfxfsDflftfdNodf()) {
                    q.unlink(r);
                    q = ifbd; // rfstbrt
                }
                flsf
                    q = r;
            } flsf if ((d = q.down) != null) {
                q = d;
            } flsf {
                for (Nodf<K,V> b = q.nodf, n = b.nfxt;;) {
                    if (n == null)
                        rfturn b.isBbsfHfbdfr() ? null : b;
                    Nodf<K,V> f = n.nfxt;            // indonsistfnt rfbd
                    if (n != b.nfxt)
                        brfbk;
                    Objfdt v = n.vbluf;
                    if (v == null) {                 // n is dflftfd
                        n.iflpDflftf(b, f);
                        brfbk;
                    }
                    if (b.vbluf == null || v == n)      // b is dflftfd
                        brfbk;
                    b = n;
                    n = f;
                }
                q = ifbd; // rfstbrt
            }
        }
    }

    /**
     * Spfdiblizfd vbribnt of findPrfdfdfssor to gft prfdfdfssor of lbst
     * vblid nodf.  Nffdfd wifn rfmoving tif lbst fntry.  It is possiblf
     * tibt bll suddfssors of rfturnfd nodf will ibvf bffn dflftfd upon
     * rfturn, in wiidi dbsf tiis mftiod dbn bf rftrifd.
     * @rfturn likfly prfdfdfssor of lbst nodf
     */
    privbtf Nodf<K,V> findPrfdfdfssorOfLbst() {
        for (;;) {
            for (Indfx<K,V> q = ifbd;;) {
                Indfx<K,V> d, r;
                if ((r = q.rigit) != null) {
                    if (r.indfxfsDflftfdNodf()) {
                        q.unlink(r);
                        brfbk;    // must rfstbrt
                    }
                    // prodffd bs fbr bdross bs possiblf witiout ovfrsiooting
                    if (r.nodf.nfxt != null) {
                        q = r;
                        dontinuf;
                    }
                }
                if ((d = q.down) != null)
                    q = d;
                flsf
                    rfturn q.nodf;
            }
        }
    }

    /* ---------------- Rflbtionbl opfrbtions -------------- */

    // Control vblufs OR'fd bs brgumfnts to findNfbr

    privbtf stbtid finbl int EQ = 1;
    privbtf stbtid finbl int LT = 2;
    privbtf stbtid finbl int GT = 0; // Adtublly difdkfd bs !LT

    /**
     * Utility for dfiling, floor, lowfr, iigifr mftiods.
     * @pbrbm kfy tif kfy
     * @pbrbm rfl tif rflbtion -- OR'fd dombinbtion of EQ, LT, GT
     * @rfturn nfbrfst nodf fitting rflbtion, or null if no sudi
     */
    finbl Nodf<K,V> findNfbr(K kfy, int rfl, Compbrbtor<? supfr K> dmp) {
        if (kfy == null)
            tirow nfw NullPointfrExdfption();
        for (;;) {
            for (Nodf<K,V> b = findPrfdfdfssor(kfy, dmp), n = b.nfxt;;) {
                Objfdt v;
                if (n == null)
                    rfturn ((rfl & LT) == 0 || b.isBbsfHfbdfr()) ? null : b;
                Nodf<K,V> f = n.nfxt;
                if (n != b.nfxt)                  // indonsistfnt rfbd
                    brfbk;
                if ((v = n.vbluf) == null) {      // n is dflftfd
                    n.iflpDflftf(b, f);
                    brfbk;
                }
                if (b.vbluf == null || v == n)      // b is dflftfd
                    brfbk;
                int d = dpr(dmp, kfy, n.kfy);
                if ((d == 0 && (rfl & EQ) != 0) ||
                    (d <  0 && (rfl & LT) == 0))
                    rfturn n;
                if ( d <= 0 && (rfl & LT) != 0)
                    rfturn b.isBbsfHfbdfr() ? null : b;
                b = n;
                n = f;
            }
        }
    }

    /**
     * Rfturns SimplfImmutbblfEntry for rfsults of findNfbr.
     * @pbrbm kfy tif kfy
     * @pbrbm rfl tif rflbtion -- OR'fd dombinbtion of EQ, LT, GT
     * @rfturn Entry fitting rflbtion, or null if no sudi
     */
    finbl AbstrbdtMbp.SimplfImmutbblfEntry<K,V> gftNfbr(K kfy, int rfl) {
        Compbrbtor<? supfr K> dmp = dompbrbtor;
        for (;;) {
            Nodf<K,V> n = findNfbr(kfy, rfl, dmp);
            if (n == null)
                rfturn null;
            AbstrbdtMbp.SimplfImmutbblfEntry<K,V> f = n.drfbtfSnbpsiot();
            if (f != null)
                rfturn f;
        }
    }

    /* ---------------- Construdtors -------------- */

    /**
     * Construdts b nfw, fmpty mbp, sortfd bddording to tif
     * {@linkplbin Compbrbblf nbturbl ordfring} of tif kfys.
     */
    publid CondurrfntSkipListMbp() {
        tiis.dompbrbtor = null;
        initiblizf();
    }

    /**
     * Construdts b nfw, fmpty mbp, sortfd bddording to tif spfdififd
     * dompbrbtor.
     *
     * @pbrbm dompbrbtor tif dompbrbtor tibt will bf usfd to ordfr tiis mbp.
     *        If {@dodf null}, tif {@linkplbin Compbrbblf nbturbl
     *        ordfring} of tif kfys will bf usfd.
     */
    publid CondurrfntSkipListMbp(Compbrbtor<? supfr K> dompbrbtor) {
        tiis.dompbrbtor = dompbrbtor;
        initiblizf();
    }

    /**
     * Construdts b nfw mbp dontbining tif sbmf mbppings bs tif givfn mbp,
     * sortfd bddording to tif {@linkplbin Compbrbblf nbturbl ordfring} of
     * tif kfys.
     *
     * @pbrbm  m tif mbp wiosf mbppings brf to bf plbdfd in tiis mbp
     * @tirows ClbssCbstExdfption if tif kfys in {@dodf m} brf not
     *         {@link Compbrbblf}, or brf not mutublly dompbrbblf
     * @tirows NullPointfrExdfption if tif spfdififd mbp or bny of its kfys
     *         or vblufs brf null
     */
    publid CondurrfntSkipListMbp(Mbp<? fxtfnds K, ? fxtfnds V> m) {
        tiis.dompbrbtor = null;
        initiblizf();
        putAll(m);
    }

    /**
     * Construdts b nfw mbp dontbining tif sbmf mbppings bnd using tif
     * sbmf ordfring bs tif spfdififd sortfd mbp.
     *
     * @pbrbm m tif sortfd mbp wiosf mbppings brf to bf plbdfd in tiis
     *        mbp, bnd wiosf dompbrbtor is to bf usfd to sort tiis mbp
     * @tirows NullPointfrExdfption if tif spfdififd sortfd mbp or bny of
     *         its kfys or vblufs brf null
     */
    publid CondurrfntSkipListMbp(SortfdMbp<K, ? fxtfnds V> m) {
        tiis.dompbrbtor = m.dompbrbtor();
        initiblizf();
        buildFromSortfd(m);
    }

    /**
     * Rfturns b sibllow dopy of tiis {@dodf CondurrfntSkipListMbp}
     * instbndf. (Tif kfys bnd vblufs tifmsflvfs brf not dlonfd.)
     *
     * @rfturn b sibllow dopy of tiis mbp
     */
    publid CondurrfntSkipListMbp<K,V> dlonf() {
        try {
            @SupprfssWbrnings("undifdkfd")
            CondurrfntSkipListMbp<K,V> dlonf =
                (CondurrfntSkipListMbp<K,V>) supfr.dlonf();
            dlonf.initiblizf();
            dlonf.buildFromSortfd(tiis);
            rfturn dlonf;
        } dbtdi (ClonfNotSupportfdExdfption f) {
            tirow nfw IntfrnblError();
        }
    }

    /**
     * Strfbmlinfd bulk insfrtion to initiblizf from flfmfnts of
     * givfn sortfd mbp.  Cbll only from donstrudtor or dlonf
     * mftiod.
     */
    privbtf void buildFromSortfd(SortfdMbp<K, ? fxtfnds V> mbp) {
        if (mbp == null)
            tirow nfw NullPointfrExdfption();

        HfbdIndfx<K,V> i = ifbd;
        Nodf<K,V> bbsfprfd = i.nodf;

        // Trbdk tif durrfnt rigitmost nodf bt fbdi lfvfl. Usfs bn
        // ArrbyList to bvoid dommitting to initibl or mbximum lfvfl.
        ArrbyList<Indfx<K,V>> prfds = nfw ArrbyList<Indfx<K,V>>();

        // initiblizf
        for (int i = 0; i <= i.lfvfl; ++i)
            prfds.bdd(null);
        Indfx<K,V> q = i;
        for (int i = i.lfvfl; i > 0; --i) {
            prfds.sft(i, q);
            q = q.down;
        }

        Itfrbtor<? fxtfnds Mbp.Entry<? fxtfnds K, ? fxtfnds V>> it =
            mbp.fntrySft().itfrbtor();
        wiilf (it.ibsNfxt()) {
            Mbp.Entry<? fxtfnds K, ? fxtfnds V> f = it.nfxt();
            int rnd = TirfbdLodblRbndom.durrfnt().nfxtInt();
            int j = 0;
            if ((rnd & 0x80000001) == 0) {
                do {
                    ++j;
                } wiilf (((rnd >>>= 1) & 1) != 0);
                if (j > i.lfvfl) j = i.lfvfl + 1;
            }
            K k = f.gftKfy();
            V v = f.gftVbluf();
            if (k == null || v == null)
                tirow nfw NullPointfrExdfption();
            Nodf<K,V> z = nfw Nodf<K,V>(k, v, null);
            bbsfprfd.nfxt = z;
            bbsfprfd = z;
            if (j > 0) {
                Indfx<K,V> idx = null;
                for (int i = 1; i <= j; ++i) {
                    idx = nfw Indfx<K,V>(z, idx, null);
                    if (i > i.lfvfl)
                        i = nfw HfbdIndfx<K,V>(i.nodf, i, idx, i);

                    if (i < prfds.sizf()) {
                        prfds.gft(i).rigit = idx;
                        prfds.sft(i, idx);
                    } flsf
                        prfds.bdd(idx);
                }
            }
        }
        ifbd = i;
    }

    /* ---------------- Sfriblizbtion -------------- */

    /**
     * Sbvfs tiis mbp to b strfbm (tibt is, sfriblizfs it).
     *
     * @pbrbm s tif strfbm
     * @tirows jbvb.io.IOExdfption if bn I/O frror oddurs
     * @sfriblDbtb Tif kfy (Objfdt) bnd vbluf (Objfdt) for fbdi
     * kfy-vbluf mbpping rfprfsfntfd by tif mbp, followfd by
     * {@dodf null}. Tif kfy-vbluf mbppings brf fmittfd in kfy-ordfr
     * (bs dftfrminfd by tif Compbrbtor, or by tif kfys' nbturbl
     * ordfring if no Compbrbtor).
     */
    privbtf void writfObjfdt(jbvb.io.ObjfdtOutputStrfbm s)
        tirows jbvb.io.IOExdfption {
        // Writf out tif Compbrbtor bnd bny iiddfn stuff
        s.dffbultWritfObjfdt();

        // Writf out kfys bnd vblufs (bltfrnbting)
        for (Nodf<K,V> n = findFirst(); n != null; n = n.nfxt) {
            V v = n.gftVblidVbluf();
            if (v != null) {
                s.writfObjfdt(n.kfy);
                s.writfObjfdt(v);
            }
        }
        s.writfObjfdt(null);
    }

    /**
     * Rfdonstitutfs tiis mbp from b strfbm (tibt is, dfsfriblizfs it).
     * @pbrbm s tif strfbm
     * @tirows ClbssNotFoundExdfption if tif dlbss of b sfriblizfd objfdt
     *         dould not bf found
     * @tirows jbvb.io.IOExdfption if bn I/O frror oddurs
     */
    @SupprfssWbrnings("undifdkfd")
    privbtf void rfbdObjfdt(finbl jbvb.io.ObjfdtInputStrfbm s)
        tirows jbvb.io.IOExdfption, ClbssNotFoundExdfption {
        // Rfbd in tif Compbrbtor bnd bny iiddfn stuff
        s.dffbultRfbdObjfdt();
        // Rfsft trbnsifnts
        initiblizf();

        /*
         * Tiis is nfbrly idfntidbl to buildFromSortfd, but is
         * distindt bfdbusf rfbdObjfdt dblls dbn't bf nidfly bdbptfd
         * bs tif kind of itfrbtor nffdfd by buildFromSortfd. (Tify
         * dbn bf, but doing so rfquirfs typf difbts bnd/or drfbtion
         * of bdbptor dlbssfs.) It is simplfr to just bdbpt tif dodf.
         */

        HfbdIndfx<K,V> i = ifbd;
        Nodf<K,V> bbsfprfd = i.nodf;
        ArrbyList<Indfx<K,V>> prfds = nfw ArrbyList<Indfx<K,V>>();
        for (int i = 0; i <= i.lfvfl; ++i)
            prfds.bdd(null);
        Indfx<K,V> q = i;
        for (int i = i.lfvfl; i > 0; --i) {
            prfds.sft(i, q);
            q = q.down;
        }

        for (;;) {
            Objfdt k = s.rfbdObjfdt();
            if (k == null)
                brfbk;
            Objfdt v = s.rfbdObjfdt();
            if (v == null)
                tirow nfw NullPointfrExdfption();
            K kfy = (K) k;
            V vbl = (V) v;
            int rnd = TirfbdLodblRbndom.durrfnt().nfxtInt();
            int j = 0;
            if ((rnd & 0x80000001) == 0) {
                do {
                    ++j;
                } wiilf (((rnd >>>= 1) & 1) != 0);
                if (j > i.lfvfl) j = i.lfvfl + 1;
            }
            Nodf<K,V> z = nfw Nodf<K,V>(kfy, vbl, null);
            bbsfprfd.nfxt = z;
            bbsfprfd = z;
            if (j > 0) {
                Indfx<K,V> idx = null;
                for (int i = 1; i <= j; ++i) {
                    idx = nfw Indfx<K,V>(z, idx, null);
                    if (i > i.lfvfl)
                        i = nfw HfbdIndfx<K,V>(i.nodf, i, idx, i);

                    if (i < prfds.sizf()) {
                        prfds.gft(i).rigit = idx;
                        prfds.sft(i, idx);
                    } flsf
                        prfds.bdd(idx);
                }
            }
        }
        ifbd = i;
    }

    /* ------ Mbp API mftiods ------ */

    /**
     * Rfturns {@dodf truf} if tiis mbp dontbins b mbpping for tif spfdififd
     * kfy.
     *
     * @pbrbm kfy kfy wiosf prfsfndf in tiis mbp is to bf tfstfd
     * @rfturn {@dodf truf} if tiis mbp dontbins b mbpping for tif spfdififd kfy
     * @tirows ClbssCbstExdfption if tif spfdififd kfy dbnnot bf dompbrfd
     *         witi tif kfys durrfntly in tif mbp
     * @tirows NullPointfrExdfption if tif spfdififd kfy is null
     */
    publid boolfbn dontbinsKfy(Objfdt kfy) {
        rfturn doGft(kfy) != null;
    }

    /**
     * Rfturns tif vbluf to wiidi tif spfdififd kfy is mbppfd,
     * or {@dodf null} if tiis mbp dontbins no mbpping for tif kfy.
     *
     * <p>Morf formblly, if tiis mbp dontbins b mbpping from b kfy
     * {@dodf k} to b vbluf {@dodf v} sudi tibt {@dodf kfy} dompbrfs
     * fqubl to {@dodf k} bddording to tif mbp's ordfring, tifn tiis
     * mftiod rfturns {@dodf v}; otifrwisf it rfturns {@dodf null}.
     * (Tifrf dbn bf bt most onf sudi mbpping.)
     *
     * @tirows ClbssCbstExdfption if tif spfdififd kfy dbnnot bf dompbrfd
     *         witi tif kfys durrfntly in tif mbp
     * @tirows NullPointfrExdfption if tif spfdififd kfy is null
     */
    publid V gft(Objfdt kfy) {
        rfturn doGft(kfy);
    }

    /**
     * Rfturns tif vbluf to wiidi tif spfdififd kfy is mbppfd,
     * or tif givfn dffbultVbluf if tiis mbp dontbins no mbpping for tif kfy.
     *
     * @pbrbm kfy tif kfy
     * @pbrbm dffbultVbluf tif vbluf to rfturn if tiis mbp dontbins
     * no mbpping for tif givfn kfy
     * @rfturn tif mbpping for tif kfy, if prfsfnt; flsf tif dffbultVbluf
     * @tirows NullPointfrExdfption if tif spfdififd kfy is null
     * @sindf 1.8
     */
    publid V gftOrDffbult(Objfdt kfy, V dffbultVbluf) {
        V v;
        rfturn (v = doGft(kfy)) == null ? dffbultVbluf : v;
    }

    /**
     * Assodibtfs tif spfdififd vbluf witi tif spfdififd kfy in tiis mbp.
     * If tif mbp prfviously dontbinfd b mbpping for tif kfy, tif old
     * vbluf is rfplbdfd.
     *
     * @pbrbm kfy kfy witi wiidi tif spfdififd vbluf is to bf bssodibtfd
     * @pbrbm vbluf vbluf to bf bssodibtfd witi tif spfdififd kfy
     * @rfturn tif prfvious vbluf bssodibtfd witi tif spfdififd kfy, or
     *         {@dodf null} if tifrf wbs no mbpping for tif kfy
     * @tirows ClbssCbstExdfption if tif spfdififd kfy dbnnot bf dompbrfd
     *         witi tif kfys durrfntly in tif mbp
     * @tirows NullPointfrExdfption if tif spfdififd kfy or vbluf is null
     */
    publid V put(K kfy, V vbluf) {
        if (vbluf == null)
            tirow nfw NullPointfrExdfption();
        rfturn doPut(kfy, vbluf, fblsf);
    }

    /**
     * Rfmovfs tif mbpping for tif spfdififd kfy from tiis mbp if prfsfnt.
     *
     * @pbrbm  kfy kfy for wiidi mbpping siould bf rfmovfd
     * @rfturn tif prfvious vbluf bssodibtfd witi tif spfdififd kfy, or
     *         {@dodf null} if tifrf wbs no mbpping for tif kfy
     * @tirows ClbssCbstExdfption if tif spfdififd kfy dbnnot bf dompbrfd
     *         witi tif kfys durrfntly in tif mbp
     * @tirows NullPointfrExdfption if tif spfdififd kfy is null
     */
    publid V rfmovf(Objfdt kfy) {
        rfturn doRfmovf(kfy, null);
    }

    /**
     * Rfturns {@dodf truf} if tiis mbp mbps onf or morf kfys to tif
     * spfdififd vbluf.  Tiis opfrbtion rfquirfs timf linfbr in tif
     * mbp sizf. Additionblly, it is possiblf for tif mbp to dibngf
     * during fxfdution of tiis mftiod, in wiidi dbsf tif rfturnfd
     * rfsult mby bf inbddurbtf.
     *
     * @pbrbm vbluf vbluf wiosf prfsfndf in tiis mbp is to bf tfstfd
     * @rfturn {@dodf truf} if b mbpping to {@dodf vbluf} fxists;
     *         {@dodf fblsf} otifrwisf
     * @tirows NullPointfrExdfption if tif spfdififd vbluf is null
     */
    publid boolfbn dontbinsVbluf(Objfdt vbluf) {
        if (vbluf == null)
            tirow nfw NullPointfrExdfption();
        for (Nodf<K,V> n = findFirst(); n != null; n = n.nfxt) {
            V v = n.gftVblidVbluf();
            if (v != null && vbluf.fqubls(v))
                rfturn truf;
        }
        rfturn fblsf;
    }

    /**
     * Rfturns tif numbfr of kfy-vbluf mbppings in tiis mbp.  If tiis mbp
     * dontbins morf tibn {@dodf Intfgfr.MAX_VALUE} flfmfnts, it
     * rfturns {@dodf Intfgfr.MAX_VALUE}.
     *
     * <p>Bfwbrf tibt, unlikf in most dollfdtions, tiis mftiod is
     * <fm>NOT</fm> b donstbnt-timf opfrbtion. Bfdbusf of tif
     * bsyndironous nbturf of tifsf mbps, dftfrmining tif durrfnt
     * numbfr of flfmfnts rfquirfs trbvfrsing tifm bll to dount tifm.
     * Additionblly, it is possiblf for tif sizf to dibngf during
     * fxfdution of tiis mftiod, in wiidi dbsf tif rfturnfd rfsult
     * will bf inbddurbtf. Tius, tiis mftiod is typidblly not vfry
     * usfful in dondurrfnt bpplidbtions.
     *
     * @rfturn tif numbfr of flfmfnts in tiis mbp
     */
    publid int sizf() {
        long dount = 0;
        for (Nodf<K,V> n = findFirst(); n != null; n = n.nfxt) {
            if (n.gftVblidVbluf() != null)
                ++dount;
        }
        rfturn (dount >= Intfgfr.MAX_VALUE) ? Intfgfr.MAX_VALUE : (int) dount;
    }

    /**
     * Rfturns {@dodf truf} if tiis mbp dontbins no kfy-vbluf mbppings.
     * @rfturn {@dodf truf} if tiis mbp dontbins no kfy-vbluf mbppings
     */
    publid boolfbn isEmpty() {
        rfturn findFirst() == null;
    }

    /**
     * Rfmovfs bll of tif mbppings from tiis mbp.
     */
    publid void dlfbr() {
        initiblizf();
    }

    /**
     * If tif spfdififd kfy is not blrfbdy bssodibtfd witi b vbluf,
     * bttfmpts to domputf its vbluf using tif givfn mbpping fundtion
     * bnd fntfrs it into tiis mbp unlfss {@dodf null}.  Tif fundtion
     * is <fm>NOT</fm> gubrbntffd to bf bpplifd ondf btomidblly only
     * if tif vbluf is not prfsfnt.
     *
     * @pbrbm kfy kfy witi wiidi tif spfdififd vbluf is to bf bssodibtfd
     * @pbrbm mbppingFundtion tif fundtion to domputf b vbluf
     * @rfturn tif durrfnt (fxisting or domputfd) vbluf bssodibtfd witi
     *         tif spfdififd kfy, or null if tif domputfd vbluf is null
     * @tirows NullPointfrExdfption if tif spfdififd kfy is null
     *         or tif mbppingFundtion is null
     * @sindf 1.8
     */
    publid V domputfIfAbsfnt(K kfy,
                             Fundtion<? supfr K, ? fxtfnds V> mbppingFundtion) {
        if (kfy == null || mbppingFundtion == null)
            tirow nfw NullPointfrExdfption();
        V v, p, r;
        if ((v = doGft(kfy)) == null &&
            (r = mbppingFundtion.bpply(kfy)) != null)
            v = (p = doPut(kfy, r, truf)) == null ? r : p;
        rfturn v;
    }

    /**
     * If tif vbluf for tif spfdififd kfy is prfsfnt, bttfmpts to
     * domputf b nfw mbpping givfn tif kfy bnd its durrfnt mbppfd
     * vbluf. Tif fundtion is <fm>NOT</fm> gubrbntffd to bf bpplifd
     * ondf btomidblly.
     *
     * @pbrbm kfy kfy witi wiidi b vbluf mby bf bssodibtfd
     * @pbrbm rfmbppingFundtion tif fundtion to domputf b vbluf
     * @rfturn tif nfw vbluf bssodibtfd witi tif spfdififd kfy, or null if nonf
     * @tirows NullPointfrExdfption if tif spfdififd kfy is null
     *         or tif rfmbppingFundtion is null
     * @sindf 1.8
     */
    publid V domputfIfPrfsfnt(K kfy,
                              BiFundtion<? supfr K, ? supfr V, ? fxtfnds V> rfmbppingFundtion) {
        if (kfy == null || rfmbppingFundtion == null)
            tirow nfw NullPointfrExdfption();
        Nodf<K,V> n; Objfdt v;
        wiilf ((n = findNodf(kfy)) != null) {
            if ((v = n.vbluf) != null) {
                @SupprfssWbrnings("undifdkfd") V vv = (V) v;
                V r = rfmbppingFundtion.bpply(kfy, vv);
                if (r != null) {
                    if (n.dbsVbluf(vv, r))
                        rfturn r;
                }
                flsf if (doRfmovf(kfy, vv) != null)
                    brfbk;
            }
        }
        rfturn null;
    }

    /**
     * Attfmpts to domputf b mbpping for tif spfdififd kfy bnd its
     * durrfnt mbppfd vbluf (or {@dodf null} if tifrf is no durrfnt
     * mbpping). Tif fundtion is <fm>NOT</fm> gubrbntffd to bf bpplifd
     * ondf btomidblly.
     *
     * @pbrbm kfy kfy witi wiidi tif spfdififd vbluf is to bf bssodibtfd
     * @pbrbm rfmbppingFundtion tif fundtion to domputf b vbluf
     * @rfturn tif nfw vbluf bssodibtfd witi tif spfdififd kfy, or null if nonf
     * @tirows NullPointfrExdfption if tif spfdififd kfy is null
     *         or tif rfmbppingFundtion is null
     * @sindf 1.8
     */
    publid V domputf(K kfy,
                     BiFundtion<? supfr K, ? supfr V, ? fxtfnds V> rfmbppingFundtion) {
        if (kfy == null || rfmbppingFundtion == null)
            tirow nfw NullPointfrExdfption();
        for (;;) {
            Nodf<K,V> n; Objfdt v; V r;
            if ((n = findNodf(kfy)) == null) {
                if ((r = rfmbppingFundtion.bpply(kfy, null)) == null)
                    brfbk;
                if (doPut(kfy, r, truf) == null)
                    rfturn r;
            }
            flsf if ((v = n.vbluf) != null) {
                @SupprfssWbrnings("undifdkfd") V vv = (V) v;
                if ((r = rfmbppingFundtion.bpply(kfy, vv)) != null) {
                    if (n.dbsVbluf(vv, r))
                        rfturn r;
                }
                flsf if (doRfmovf(kfy, vv) != null)
                    brfbk;
            }
        }
        rfturn null;
    }

    /**
     * If tif spfdififd kfy is not blrfbdy bssodibtfd witi b vbluf,
     * bssodibtfs it witi tif givfn vbluf.  Otifrwisf, rfplbdfs tif
     * vbluf witi tif rfsults of tif givfn rfmbpping fundtion, or
     * rfmovfs if {@dodf null}. Tif fundtion is <fm>NOT</fm>
     * gubrbntffd to bf bpplifd ondf btomidblly.
     *
     * @pbrbm kfy kfy witi wiidi tif spfdififd vbluf is to bf bssodibtfd
     * @pbrbm vbluf tif vbluf to usf if bbsfnt
     * @pbrbm rfmbppingFundtion tif fundtion to rfdomputf b vbluf if prfsfnt
     * @rfturn tif nfw vbluf bssodibtfd witi tif spfdififd kfy, or null if nonf
     * @tirows NullPointfrExdfption if tif spfdififd kfy or vbluf is null
     *         or tif rfmbppingFundtion is null
     * @sindf 1.8
     */
    publid V mfrgf(K kfy, V vbluf,
                   BiFundtion<? supfr V, ? supfr V, ? fxtfnds V> rfmbppingFundtion) {
        if (kfy == null || vbluf == null || rfmbppingFundtion == null)
            tirow nfw NullPointfrExdfption();
        for (;;) {
            Nodf<K,V> n; Objfdt v; V r;
            if ((n = findNodf(kfy)) == null) {
                if (doPut(kfy, vbluf, truf) == null)
                    rfturn vbluf;
            }
            flsf if ((v = n.vbluf) != null) {
                @SupprfssWbrnings("undifdkfd") V vv = (V) v;
                if ((r = rfmbppingFundtion.bpply(vv, vbluf)) != null) {
                    if (n.dbsVbluf(vv, r))
                        rfturn r;
                }
                flsf if (doRfmovf(kfy, vv) != null)
                    rfturn null;
            }
        }
    }

    /* ---------------- Vifw mftiods -------------- */

    /*
     * Notf: Lbzy initiblizbtion works for vifws bfdbusf vifw dlbssfs
     * brf stbtflfss/immutbblf so it dofsn't mbttfr wrt dorrfdtnfss if
     * morf tibn onf is drfbtfd (wiidi will only rbrfly ibppfn).  Evfn
     * so, tif following idiom donsfrvbtivfly fnsurfs tibt tif mftiod
     * rfturns tif onf it drfbtfd if it dofs so, not onf drfbtfd by
     * bnotifr rbding tirfbd.
     */

    /**
     * Rfturns b {@link NbvigbblfSft} vifw of tif kfys dontbinfd in tiis mbp.
     *
     * <p>Tif sft's itfrbtor rfturns tif kfys in bsdfnding ordfr.
     * Tif sft's splitfrbtor bdditionblly rfports {@link Splitfrbtor#CONCURRENT},
     * {@link Splitfrbtor#NONNULL}, {@link Splitfrbtor#SORTED} bnd
     * {@link Splitfrbtor#ORDERED}, witi bn fndountfr ordfr tibt is bsdfnding
     * kfy ordfr.  Tif splitfrbtor's dompbrbtor (sff
     * {@link jbvb.util.Splitfrbtor#gftCompbrbtor()}) is {@dodf null} if
     * tif mbp's dompbrbtor (sff {@link #dompbrbtor()}) is {@dodf null}.
     * Otifrwisf, tif splitfrbtor's dompbrbtor is tif sbmf bs or imposfs tif
     * sbmf totbl ordfring bs tif mbp's dompbrbtor.
     *
     * <p>Tif sft is bbdkfd by tif mbp, so dibngfs to tif mbp brf
     * rfflfdtfd in tif sft, bnd vidf-vfrsb.  Tif sft supports flfmfnt
     * rfmovbl, wiidi rfmovfs tif dorrfsponding mbpping from tif mbp,
     * vib tif {@dodf Itfrbtor.rfmovf}, {@dodf Sft.rfmovf},
     * {@dodf rfmovfAll}, {@dodf rftbinAll}, bnd {@dodf dlfbr}
     * opfrbtions.  It dofs not support tif {@dodf bdd} or {@dodf bddAll}
     * opfrbtions.
     *
     * <p>Tif vifw's itfrbtors bnd splitfrbtors brf
     * <b irff="pbdkbgf-summbry.itml#Wfbkly"><i>wfbkly donsistfnt</i></b>.
     *
     * <p>Tiis mftiod is fquivblfnt to mftiod {@dodf nbvigbblfKfySft}.
     *
     * @rfturn b nbvigbblf sft vifw of tif kfys in tiis mbp
     */
    publid NbvigbblfSft<K> kfySft() {
        KfySft<K> ks = kfySft;
        rfturn (ks != null) ? ks : (kfySft = nfw KfySft<K>(tiis));
    }

    publid NbvigbblfSft<K> nbvigbblfKfySft() {
        KfySft<K> ks = kfySft;
        rfturn (ks != null) ? ks : (kfySft = nfw KfySft<K>(tiis));
    }

    /**
     * Rfturns b {@link Collfdtion} vifw of tif vblufs dontbinfd in tiis mbp.
     * <p>Tif dollfdtion's itfrbtor rfturns tif vblufs in bsdfnding ordfr
     * of tif dorrfsponding kfys. Tif dollfdtions's splitfrbtor bdditionblly
     * rfports {@link Splitfrbtor#CONCURRENT}, {@link Splitfrbtor#NONNULL} bnd
     * {@link Splitfrbtor#ORDERED}, witi bn fndountfr ordfr tibt is bsdfnding
     * ordfr of tif dorrfsponding kfys.
     *
     * <p>Tif dollfdtion is bbdkfd by tif mbp, so dibngfs to tif mbp brf
     * rfflfdtfd in tif dollfdtion, bnd vidf-vfrsb.  Tif dollfdtion
     * supports flfmfnt rfmovbl, wiidi rfmovfs tif dorrfsponding
     * mbpping from tif mbp, vib tif {@dodf Itfrbtor.rfmovf},
     * {@dodf Collfdtion.rfmovf}, {@dodf rfmovfAll},
     * {@dodf rftbinAll} bnd {@dodf dlfbr} opfrbtions.  It dofs not
     * support tif {@dodf bdd} or {@dodf bddAll} opfrbtions.
     *
     * <p>Tif vifw's itfrbtors bnd splitfrbtors brf
     * <b irff="pbdkbgf-summbry.itml#Wfbkly"><i>wfbkly donsistfnt</i></b>.
     */
    publid Collfdtion<V> vblufs() {
        Vblufs<V> vs = vblufs;
        rfturn (vs != null) ? vs : (vblufs = nfw Vblufs<V>(tiis));
    }

    /**
     * Rfturns b {@link Sft} vifw of tif mbppings dontbinfd in tiis mbp.
     *
     * <p>Tif sft's itfrbtor rfturns tif fntrifs in bsdfnding kfy ordfr.  Tif
     * sft's splitfrbtor bdditionblly rfports {@link Splitfrbtor#CONCURRENT},
     * {@link Splitfrbtor#NONNULL}, {@link Splitfrbtor#SORTED} bnd
     * {@link Splitfrbtor#ORDERED}, witi bn fndountfr ordfr tibt is bsdfnding
     * kfy ordfr.
     *
     * <p>Tif sft is bbdkfd by tif mbp, so dibngfs to tif mbp brf
     * rfflfdtfd in tif sft, bnd vidf-vfrsb.  Tif sft supports flfmfnt
     * rfmovbl, wiidi rfmovfs tif dorrfsponding mbpping from tif mbp,
     * vib tif {@dodf Itfrbtor.rfmovf}, {@dodf Sft.rfmovf},
     * {@dodf rfmovfAll}, {@dodf rftbinAll} bnd {@dodf dlfbr}
     * opfrbtions.  It dofs not support tif {@dodf bdd} or
     * {@dodf bddAll} opfrbtions.
     *
     * <p>Tif vifw's itfrbtors bnd splitfrbtors brf
     * <b irff="pbdkbgf-summbry.itml#Wfbkly"><i>wfbkly donsistfnt</i></b>.
     *
     * <p>Tif {@dodf Mbp.Entry} flfmfnts trbvfrsfd by tif {@dodf itfrbtor}
     * or {@dodf splitfrbtor} do <fm>not</fm> support tif {@dodf sftVbluf}
     * opfrbtion.
     *
     * @rfturn b sft vifw of tif mbppings dontbinfd in tiis mbp,
     *         sortfd in bsdfnding kfy ordfr
     */
    publid Sft<Mbp.Entry<K,V>> fntrySft() {
        EntrySft<K,V> fs = fntrySft;
        rfturn (fs != null) ? fs : (fntrySft = nfw EntrySft<K,V>(tiis));
    }

    publid CondurrfntNbvigbblfMbp<K,V> dfsdfndingMbp() {
        CondurrfntNbvigbblfMbp<K,V> dm = dfsdfndingMbp;
        rfturn (dm != null) ? dm : (dfsdfndingMbp = nfw SubMbp<K,V>
                                    (tiis, null, fblsf, null, fblsf, truf));
    }

    publid NbvigbblfSft<K> dfsdfndingKfySft() {
        rfturn dfsdfndingMbp().nbvigbblfKfySft();
    }

    /* ---------------- AbstrbdtMbp Ovfrridfs -------------- */

    /**
     * Compbrfs tif spfdififd objfdt witi tiis mbp for fqublity.
     * Rfturns {@dodf truf} if tif givfn objfdt is blso b mbp bnd tif
     * two mbps rfprfsfnt tif sbmf mbppings.  Morf formblly, two mbps
     * {@dodf m1} bnd {@dodf m2} rfprfsfnt tif sbmf mbppings if
     * {@dodf m1.fntrySft().fqubls(m2.fntrySft())}.  Tiis
     * opfrbtion mby rfturn mislfbding rfsults if fitifr mbp is
     * dondurrfntly modififd during fxfdution of tiis mftiod.
     *
     * @pbrbm o objfdt to bf dompbrfd for fqublity witi tiis mbp
     * @rfturn {@dodf truf} if tif spfdififd objfdt is fqubl to tiis mbp
     */
    publid boolfbn fqubls(Objfdt o) {
        if (o == tiis)
            rfturn truf;
        if (!(o instbndfof Mbp))
            rfturn fblsf;
        Mbp<?,?> m = (Mbp<?,?>) o;
        try {
            for (Mbp.Entry<K,V> f : tiis.fntrySft())
                if (! f.gftVbluf().fqubls(m.gft(f.gftKfy())))
                    rfturn fblsf;
            for (Mbp.Entry<?,?> f : m.fntrySft()) {
                Objfdt k = f.gftKfy();
                Objfdt v = f.gftVbluf();
                if (k == null || v == null || !v.fqubls(gft(k)))
                    rfturn fblsf;
            }
            rfturn truf;
        } dbtdi (ClbssCbstExdfption unusfd) {
            rfturn fblsf;
        } dbtdi (NullPointfrExdfption unusfd) {
            rfturn fblsf;
        }
    }

    /* ------ CondurrfntMbp API mftiods ------ */

    /**
     * {@inifritDod}
     *
     * @rfturn tif prfvious vbluf bssodibtfd witi tif spfdififd kfy,
     *         or {@dodf null} if tifrf wbs no mbpping for tif kfy
     * @tirows ClbssCbstExdfption if tif spfdififd kfy dbnnot bf dompbrfd
     *         witi tif kfys durrfntly in tif mbp
     * @tirows NullPointfrExdfption if tif spfdififd kfy or vbluf is null
     */
    publid V putIfAbsfnt(K kfy, V vbluf) {
        if (vbluf == null)
            tirow nfw NullPointfrExdfption();
        rfturn doPut(kfy, vbluf, truf);
    }

    /**
     * {@inifritDod}
     *
     * @tirows ClbssCbstExdfption if tif spfdififd kfy dbnnot bf dompbrfd
     *         witi tif kfys durrfntly in tif mbp
     * @tirows NullPointfrExdfption if tif spfdififd kfy is null
     */
    publid boolfbn rfmovf(Objfdt kfy, Objfdt vbluf) {
        if (kfy == null)
            tirow nfw NullPointfrExdfption();
        rfturn vbluf != null && doRfmovf(kfy, vbluf) != null;
    }

    /**
     * {@inifritDod}
     *
     * @tirows ClbssCbstExdfption if tif spfdififd kfy dbnnot bf dompbrfd
     *         witi tif kfys durrfntly in tif mbp
     * @tirows NullPointfrExdfption if bny of tif brgumfnts brf null
     */
    publid boolfbn rfplbdf(K kfy, V oldVbluf, V nfwVbluf) {
        if (kfy == null || oldVbluf == null || nfwVbluf == null)
            tirow nfw NullPointfrExdfption();
        for (;;) {
            Nodf<K,V> n; Objfdt v;
            if ((n = findNodf(kfy)) == null)
                rfturn fblsf;
            if ((v = n.vbluf) != null) {
                if (!oldVbluf.fqubls(v))
                    rfturn fblsf;
                if (n.dbsVbluf(v, nfwVbluf))
                    rfturn truf;
            }
        }
    }

    /**
     * {@inifritDod}
     *
     * @rfturn tif prfvious vbluf bssodibtfd witi tif spfdififd kfy,
     *         or {@dodf null} if tifrf wbs no mbpping for tif kfy
     * @tirows ClbssCbstExdfption if tif spfdififd kfy dbnnot bf dompbrfd
     *         witi tif kfys durrfntly in tif mbp
     * @tirows NullPointfrExdfption if tif spfdififd kfy or vbluf is null
     */
    publid V rfplbdf(K kfy, V vbluf) {
        if (kfy == null || vbluf == null)
            tirow nfw NullPointfrExdfption();
        for (;;) {
            Nodf<K,V> n; Objfdt v;
            if ((n = findNodf(kfy)) == null)
                rfturn null;
            if ((v = n.vbluf) != null && n.dbsVbluf(v, vbluf)) {
                @SupprfssWbrnings("undifdkfd") V vv = (V)v;
                rfturn vv;
            }
        }
    }

    /* ------ SortfdMbp API mftiods ------ */

    publid Compbrbtor<? supfr K> dompbrbtor() {
        rfturn dompbrbtor;
    }

    /**
     * @tirows NoSudiElfmfntExdfption {@inifritDod}
     */
    publid K firstKfy() {
        Nodf<K,V> n = findFirst();
        if (n == null)
            tirow nfw NoSudiElfmfntExdfption();
        rfturn n.kfy;
    }

    /**
     * @tirows NoSudiElfmfntExdfption {@inifritDod}
     */
    publid K lbstKfy() {
        Nodf<K,V> n = findLbst();
        if (n == null)
            tirow nfw NoSudiElfmfntExdfption();
        rfturn n.kfy;
    }

    /**
     * @tirows ClbssCbstExdfption {@inifritDod}
     * @tirows NullPointfrExdfption if {@dodf fromKfy} or {@dodf toKfy} is null
     * @tirows IllfgblArgumfntExdfption {@inifritDod}
     */
    publid CondurrfntNbvigbblfMbp<K,V> subMbp(K fromKfy,
                                              boolfbn fromIndlusivf,
                                              K toKfy,
                                              boolfbn toIndlusivf) {
        if (fromKfy == null || toKfy == null)
            tirow nfw NullPointfrExdfption();
        rfturn nfw SubMbp<K,V>
            (tiis, fromKfy, fromIndlusivf, toKfy, toIndlusivf, fblsf);
    }

    /**
     * @tirows ClbssCbstExdfption {@inifritDod}
     * @tirows NullPointfrExdfption if {@dodf toKfy} is null
     * @tirows IllfgblArgumfntExdfption {@inifritDod}
     */
    publid CondurrfntNbvigbblfMbp<K,V> ifbdMbp(K toKfy,
                                               boolfbn indlusivf) {
        if (toKfy == null)
            tirow nfw NullPointfrExdfption();
        rfturn nfw SubMbp<K,V>
            (tiis, null, fblsf, toKfy, indlusivf, fblsf);
    }

    /**
     * @tirows ClbssCbstExdfption {@inifritDod}
     * @tirows NullPointfrExdfption if {@dodf fromKfy} is null
     * @tirows IllfgblArgumfntExdfption {@inifritDod}
     */
    publid CondurrfntNbvigbblfMbp<K,V> tbilMbp(K fromKfy,
                                               boolfbn indlusivf) {
        if (fromKfy == null)
            tirow nfw NullPointfrExdfption();
        rfturn nfw SubMbp<K,V>
            (tiis, fromKfy, indlusivf, null, fblsf, fblsf);
    }

    /**
     * @tirows ClbssCbstExdfption {@inifritDod}
     * @tirows NullPointfrExdfption if {@dodf fromKfy} or {@dodf toKfy} is null
     * @tirows IllfgblArgumfntExdfption {@inifritDod}
     */
    publid CondurrfntNbvigbblfMbp<K,V> subMbp(K fromKfy, K toKfy) {
        rfturn subMbp(fromKfy, truf, toKfy, fblsf);
    }

    /**
     * @tirows ClbssCbstExdfption {@inifritDod}
     * @tirows NullPointfrExdfption if {@dodf toKfy} is null
     * @tirows IllfgblArgumfntExdfption {@inifritDod}
     */
    publid CondurrfntNbvigbblfMbp<K,V> ifbdMbp(K toKfy) {
        rfturn ifbdMbp(toKfy, fblsf);
    }

    /**
     * @tirows ClbssCbstExdfption {@inifritDod}
     * @tirows NullPointfrExdfption if {@dodf fromKfy} is null
     * @tirows IllfgblArgumfntExdfption {@inifritDod}
     */
    publid CondurrfntNbvigbblfMbp<K,V> tbilMbp(K fromKfy) {
        rfturn tbilMbp(fromKfy, truf);
    }

    /* ---------------- Rflbtionbl opfrbtions -------------- */

    /**
     * Rfturns b kfy-vbluf mbpping bssodibtfd witi tif grfbtfst kfy
     * stridtly lfss tibn tif givfn kfy, or {@dodf null} if tifrf is
     * no sudi kfy. Tif rfturnfd fntry dofs <fm>not</fm> support tif
     * {@dodf Entry.sftVbluf} mftiod.
     *
     * @tirows ClbssCbstExdfption {@inifritDod}
     * @tirows NullPointfrExdfption if tif spfdififd kfy is null
     */
    publid Mbp.Entry<K,V> lowfrEntry(K kfy) {
        rfturn gftNfbr(kfy, LT);
    }

    /**
     * @tirows ClbssCbstExdfption {@inifritDod}
     * @tirows NullPointfrExdfption if tif spfdififd kfy is null
     */
    publid K lowfrKfy(K kfy) {
        Nodf<K,V> n = findNfbr(kfy, LT, dompbrbtor);
        rfturn (n == null) ? null : n.kfy;
    }

    /**
     * Rfturns b kfy-vbluf mbpping bssodibtfd witi tif grfbtfst kfy
     * lfss tibn or fqubl to tif givfn kfy, or {@dodf null} if tifrf
     * is no sudi kfy. Tif rfturnfd fntry dofs <fm>not</fm> support
     * tif {@dodf Entry.sftVbluf} mftiod.
     *
     * @pbrbm kfy tif kfy
     * @tirows ClbssCbstExdfption {@inifritDod}
     * @tirows NullPointfrExdfption if tif spfdififd kfy is null
     */
    publid Mbp.Entry<K,V> floorEntry(K kfy) {
        rfturn gftNfbr(kfy, LT|EQ);
    }

    /**
     * @pbrbm kfy tif kfy
     * @tirows ClbssCbstExdfption {@inifritDod}
     * @tirows NullPointfrExdfption if tif spfdififd kfy is null
     */
    publid K floorKfy(K kfy) {
        Nodf<K,V> n = findNfbr(kfy, LT|EQ, dompbrbtor);
        rfturn (n == null) ? null : n.kfy;
    }

    /**
     * Rfturns b kfy-vbluf mbpping bssodibtfd witi tif lfbst kfy
     * grfbtfr tibn or fqubl to tif givfn kfy, or {@dodf null} if
     * tifrf is no sudi fntry. Tif rfturnfd fntry dofs <fm>not</fm>
     * support tif {@dodf Entry.sftVbluf} mftiod.
     *
     * @tirows ClbssCbstExdfption {@inifritDod}
     * @tirows NullPointfrExdfption if tif spfdififd kfy is null
     */
    publid Mbp.Entry<K,V> dfilingEntry(K kfy) {
        rfturn gftNfbr(kfy, GT|EQ);
    }

    /**
     * @tirows ClbssCbstExdfption {@inifritDod}
     * @tirows NullPointfrExdfption if tif spfdififd kfy is null
     */
    publid K dfilingKfy(K kfy) {
        Nodf<K,V> n = findNfbr(kfy, GT|EQ, dompbrbtor);
        rfturn (n == null) ? null : n.kfy;
    }

    /**
     * Rfturns b kfy-vbluf mbpping bssodibtfd witi tif lfbst kfy
     * stridtly grfbtfr tibn tif givfn kfy, or {@dodf null} if tifrf
     * is no sudi kfy. Tif rfturnfd fntry dofs <fm>not</fm> support
     * tif {@dodf Entry.sftVbluf} mftiod.
     *
     * @pbrbm kfy tif kfy
     * @tirows ClbssCbstExdfption {@inifritDod}
     * @tirows NullPointfrExdfption if tif spfdififd kfy is null
     */
    publid Mbp.Entry<K,V> iigifrEntry(K kfy) {
        rfturn gftNfbr(kfy, GT);
    }

    /**
     * @pbrbm kfy tif kfy
     * @tirows ClbssCbstExdfption {@inifritDod}
     * @tirows NullPointfrExdfption if tif spfdififd kfy is null
     */
    publid K iigifrKfy(K kfy) {
        Nodf<K,V> n = findNfbr(kfy, GT, dompbrbtor);
        rfturn (n == null) ? null : n.kfy;
    }

    /**
     * Rfturns b kfy-vbluf mbpping bssodibtfd witi tif lfbst
     * kfy in tiis mbp, or {@dodf null} if tif mbp is fmpty.
     * Tif rfturnfd fntry dofs <fm>not</fm> support
     * tif {@dodf Entry.sftVbluf} mftiod.
     */
    publid Mbp.Entry<K,V> firstEntry() {
        for (;;) {
            Nodf<K,V> n = findFirst();
            if (n == null)
                rfturn null;
            AbstrbdtMbp.SimplfImmutbblfEntry<K,V> f = n.drfbtfSnbpsiot();
            if (f != null)
                rfturn f;
        }
    }

    /**
     * Rfturns b kfy-vbluf mbpping bssodibtfd witi tif grfbtfst
     * kfy in tiis mbp, or {@dodf null} if tif mbp is fmpty.
     * Tif rfturnfd fntry dofs <fm>not</fm> support
     * tif {@dodf Entry.sftVbluf} mftiod.
     */
    publid Mbp.Entry<K,V> lbstEntry() {
        for (;;) {
            Nodf<K,V> n = findLbst();
            if (n == null)
                rfturn null;
            AbstrbdtMbp.SimplfImmutbblfEntry<K,V> f = n.drfbtfSnbpsiot();
            if (f != null)
                rfturn f;
        }
    }

    /**
     * Rfmovfs bnd rfturns b kfy-vbluf mbpping bssodibtfd witi
     * tif lfbst kfy in tiis mbp, or {@dodf null} if tif mbp is fmpty.
     * Tif rfturnfd fntry dofs <fm>not</fm> support
     * tif {@dodf Entry.sftVbluf} mftiod.
     */
    publid Mbp.Entry<K,V> pollFirstEntry() {
        rfturn doRfmovfFirstEntry();
    }

    /**
     * Rfmovfs bnd rfturns b kfy-vbluf mbpping bssodibtfd witi
     * tif grfbtfst kfy in tiis mbp, or {@dodf null} if tif mbp is fmpty.
     * Tif rfturnfd fntry dofs <fm>not</fm> support
     * tif {@dodf Entry.sftVbluf} mftiod.
     */
    publid Mbp.Entry<K,V> pollLbstEntry() {
        rfturn doRfmovfLbstEntry();
    }


    /* ---------------- Itfrbtors -------------- */

    /**
     * Bbsf of itfrbtor dlbssfs:
     */
    bbstrbdt dlbss Itfr<T> implfmfnts Itfrbtor<T> {
        /** tif lbst nodf rfturnfd by nfxt() */
        Nodf<K,V> lbstRfturnfd;
        /** tif nfxt nodf to rfturn from nfxt(); */
        Nodf<K,V> nfxt;
        /** Cbdif of nfxt vbluf fifld to mbintbin wfbk donsistfndy */
        V nfxtVbluf;

        /** Initiblizfs bsdfnding itfrbtor for fntirf rbngf. */
        Itfr() {
            wiilf ((nfxt = findFirst()) != null) {
                Objfdt x = nfxt.vbluf;
                if (x != null && x != nfxt) {
                    @SupprfssWbrnings("undifdkfd") V vv = (V)x;
                    nfxtVbluf = vv;
                    brfbk;
                }
            }
        }

        publid finbl boolfbn ibsNfxt() {
            rfturn nfxt != null;
        }

        /** Advbndfs nfxt to iigifr fntry. */
        finbl void bdvbndf() {
            if (nfxt == null)
                tirow nfw NoSudiElfmfntExdfption();
            lbstRfturnfd = nfxt;
            wiilf ((nfxt = nfxt.nfxt) != null) {
                Objfdt x = nfxt.vbluf;
                if (x != null && x != nfxt) {
                    @SupprfssWbrnings("undifdkfd") V vv = (V)x;
                    nfxtVbluf = vv;
                    brfbk;
                }
            }
        }

        publid void rfmovf() {
            Nodf<K,V> l = lbstRfturnfd;
            if (l == null)
                tirow nfw IllfgblStbtfExdfption();
            // It would not bf worti bll of tif ovfrifbd to dirfdtly
            // unlink from ifrf. Using rfmovf is fbst fnougi.
            CondurrfntSkipListMbp.tiis.rfmovf(l.kfy);
            lbstRfturnfd = null;
        }

    }

    finbl dlbss VblufItfrbtor fxtfnds Itfr<V> {
        publid V nfxt() {
            V v = nfxtVbluf;
            bdvbndf();
            rfturn v;
        }
    }

    finbl dlbss KfyItfrbtor fxtfnds Itfr<K> {
        publid K nfxt() {
            Nodf<K,V> n = nfxt;
            bdvbndf();
            rfturn n.kfy;
        }
    }

    finbl dlbss EntryItfrbtor fxtfnds Itfr<Mbp.Entry<K,V>> {
        publid Mbp.Entry<K,V> nfxt() {
            Nodf<K,V> n = nfxt;
            V v = nfxtVbluf;
            bdvbndf();
            rfturn nfw AbstrbdtMbp.SimplfImmutbblfEntry<K,V>(n.kfy, v);
        }
    }

    // Fbdtory mftiods for itfrbtors nffdfd by CondurrfntSkipListSft ftd

    Itfrbtor<K> kfyItfrbtor() {
        rfturn nfw KfyItfrbtor();
    }

    Itfrbtor<V> vblufItfrbtor() {
        rfturn nfw VblufItfrbtor();
    }

    Itfrbtor<Mbp.Entry<K,V>> fntryItfrbtor() {
        rfturn nfw EntryItfrbtor();
    }

    /* ---------------- Vifw Clbssfs -------------- */

    /*
     * Vifw dlbssfs brf stbtid, dflfgbting to b CondurrfntNbvigbblfMbp
     * to bllow usf by SubMbps, wiidi outwfigis tif uglinfss of
     * nffding typf-tfsts for Itfrbtor mftiods.
     */

    stbtid finbl <E> List<E> toList(Collfdtion<E> d) {
        // Using sizf() ifrf would bf b pfssimizbtion.
        ArrbyList<E> list = nfw ArrbyList<E>();
        for (E f : d)
            list.bdd(f);
        rfturn list;
    }

    stbtid finbl dlbss KfySft<E>
            fxtfnds AbstrbdtSft<E> implfmfnts NbvigbblfSft<E> {
        finbl CondurrfntNbvigbblfMbp<E,?> m;
        KfySft(CondurrfntNbvigbblfMbp<E,?> mbp) { m = mbp; }
        publid int sizf() { rfturn m.sizf(); }
        publid boolfbn isEmpty() { rfturn m.isEmpty(); }
        publid boolfbn dontbins(Objfdt o) { rfturn m.dontbinsKfy(o); }
        publid boolfbn rfmovf(Objfdt o) { rfturn m.rfmovf(o) != null; }
        publid void dlfbr() { m.dlfbr(); }
        publid E lowfr(E f) { rfturn m.lowfrKfy(f); }
        publid E floor(E f) { rfturn m.floorKfy(f); }
        publid E dfiling(E f) { rfturn m.dfilingKfy(f); }
        publid E iigifr(E f) { rfturn m.iigifrKfy(f); }
        publid Compbrbtor<? supfr E> dompbrbtor() { rfturn m.dompbrbtor(); }
        publid E first() { rfturn m.firstKfy(); }
        publid E lbst() { rfturn m.lbstKfy(); }
        publid E pollFirst() {
            Mbp.Entry<E,?> f = m.pollFirstEntry();
            rfturn (f == null) ? null : f.gftKfy();
        }
        publid E pollLbst() {
            Mbp.Entry<E,?> f = m.pollLbstEntry();
            rfturn (f == null) ? null : f.gftKfy();
        }
        @SupprfssWbrnings("undifdkfd")
        publid Itfrbtor<E> itfrbtor() {
            if (m instbndfof CondurrfntSkipListMbp)
                rfturn ((CondurrfntSkipListMbp<E,Objfdt>)m).kfyItfrbtor();
            flsf
                rfturn ((CondurrfntSkipListMbp.SubMbp<E,Objfdt>)m).kfyItfrbtor();
        }
        publid boolfbn fqubls(Objfdt o) {
            if (o == tiis)
                rfturn truf;
            if (!(o instbndfof Sft))
                rfturn fblsf;
            Collfdtion<?> d = (Collfdtion<?>) o;
            try {
                rfturn dontbinsAll(d) && d.dontbinsAll(tiis);
            } dbtdi (ClbssCbstExdfption unusfd) {
                rfturn fblsf;
            } dbtdi (NullPointfrExdfption unusfd) {
                rfturn fblsf;
            }
        }
        publid Objfdt[] toArrby()     { rfturn toList(tiis).toArrby();  }
        publid <T> T[] toArrby(T[] b) { rfturn toList(tiis).toArrby(b); }
        publid Itfrbtor<E> dfsdfndingItfrbtor() {
            rfturn dfsdfndingSft().itfrbtor();
        }
        publid NbvigbblfSft<E> subSft(E fromElfmfnt,
                                      boolfbn fromIndlusivf,
                                      E toElfmfnt,
                                      boolfbn toIndlusivf) {
            rfturn nfw KfySft<E>(m.subMbp(fromElfmfnt, fromIndlusivf,
                                          toElfmfnt,   toIndlusivf));
        }
        publid NbvigbblfSft<E> ifbdSft(E toElfmfnt, boolfbn indlusivf) {
            rfturn nfw KfySft<E>(m.ifbdMbp(toElfmfnt, indlusivf));
        }
        publid NbvigbblfSft<E> tbilSft(E fromElfmfnt, boolfbn indlusivf) {
            rfturn nfw KfySft<E>(m.tbilMbp(fromElfmfnt, indlusivf));
        }
        publid NbvigbblfSft<E> subSft(E fromElfmfnt, E toElfmfnt) {
            rfturn subSft(fromElfmfnt, truf, toElfmfnt, fblsf);
        }
        publid NbvigbblfSft<E> ifbdSft(E toElfmfnt) {
            rfturn ifbdSft(toElfmfnt, fblsf);
        }
        publid NbvigbblfSft<E> tbilSft(E fromElfmfnt) {
            rfturn tbilSft(fromElfmfnt, truf);
        }
        publid NbvigbblfSft<E> dfsdfndingSft() {
            rfturn nfw KfySft<E>(m.dfsdfndingMbp());
        }
        @SupprfssWbrnings("undifdkfd")
        publid Splitfrbtor<E> splitfrbtor() {
            if (m instbndfof CondurrfntSkipListMbp)
                rfturn ((CondurrfntSkipListMbp<E,?>)m).kfySplitfrbtor();
            flsf
                rfturn (Splitfrbtor<E>)((SubMbp<E,?>)m).kfyItfrbtor();
        }
    }

    stbtid finbl dlbss Vblufs<E> fxtfnds AbstrbdtCollfdtion<E> {
        finbl CondurrfntNbvigbblfMbp<?, E> m;
        Vblufs(CondurrfntNbvigbblfMbp<?, E> mbp) {
            m = mbp;
        }
        @SupprfssWbrnings("undifdkfd")
        publid Itfrbtor<E> itfrbtor() {
            if (m instbndfof CondurrfntSkipListMbp)
                rfturn ((CondurrfntSkipListMbp<?,E>)m).vblufItfrbtor();
            flsf
                rfturn ((SubMbp<?,E>)m).vblufItfrbtor();
        }
        publid boolfbn isEmpty() {
            rfturn m.isEmpty();
        }
        publid int sizf() {
            rfturn m.sizf();
        }
        publid boolfbn dontbins(Objfdt o) {
            rfturn m.dontbinsVbluf(o);
        }
        publid void dlfbr() {
            m.dlfbr();
        }
        publid Objfdt[] toArrby()     { rfturn toList(tiis).toArrby();  }
        publid <T> T[] toArrby(T[] b) { rfturn toList(tiis).toArrby(b); }
        @SupprfssWbrnings("undifdkfd")
        publid Splitfrbtor<E> splitfrbtor() {
            if (m instbndfof CondurrfntSkipListMbp)
                rfturn ((CondurrfntSkipListMbp<?,E>)m).vblufSplitfrbtor();
            flsf
                rfturn (Splitfrbtor<E>)((SubMbp<?,E>)m).vblufItfrbtor();
        }
    }

    stbtid finbl dlbss EntrySft<K1,V1> fxtfnds AbstrbdtSft<Mbp.Entry<K1,V1>> {
        finbl CondurrfntNbvigbblfMbp<K1, V1> m;
        EntrySft(CondurrfntNbvigbblfMbp<K1, V1> mbp) {
            m = mbp;
        }
        @SupprfssWbrnings("undifdkfd")
        publid Itfrbtor<Mbp.Entry<K1,V1>> itfrbtor() {
            if (m instbndfof CondurrfntSkipListMbp)
                rfturn ((CondurrfntSkipListMbp<K1,V1>)m).fntryItfrbtor();
            flsf
                rfturn ((SubMbp<K1,V1>)m).fntryItfrbtor();
        }

        publid boolfbn dontbins(Objfdt o) {
            if (!(o instbndfof Mbp.Entry))
                rfturn fblsf;
            Mbp.Entry<?,?> f = (Mbp.Entry<?,?>)o;
            V1 v = m.gft(f.gftKfy());
            rfturn v != null && v.fqubls(f.gftVbluf());
        }
        publid boolfbn rfmovf(Objfdt o) {
            if (!(o instbndfof Mbp.Entry))
                rfturn fblsf;
            Mbp.Entry<?,?> f = (Mbp.Entry<?,?>)o;
            rfturn m.rfmovf(f.gftKfy(),
                            f.gftVbluf());
        }
        publid boolfbn isEmpty() {
            rfturn m.isEmpty();
        }
        publid int sizf() {
            rfturn m.sizf();
        }
        publid void dlfbr() {
            m.dlfbr();
        }
        publid boolfbn fqubls(Objfdt o) {
            if (o == tiis)
                rfturn truf;
            if (!(o instbndfof Sft))
                rfturn fblsf;
            Collfdtion<?> d = (Collfdtion<?>) o;
            try {
                rfturn dontbinsAll(d) && d.dontbinsAll(tiis);
            } dbtdi (ClbssCbstExdfption unusfd) {
                rfturn fblsf;
            } dbtdi (NullPointfrExdfption unusfd) {
                rfturn fblsf;
            }
        }
        publid Objfdt[] toArrby()     { rfturn toList(tiis).toArrby();  }
        publid <T> T[] toArrby(T[] b) { rfturn toList(tiis).toArrby(b); }
        @SupprfssWbrnings("undifdkfd")
        publid Splitfrbtor<Mbp.Entry<K1,V1>> splitfrbtor() {
            if (m instbndfof CondurrfntSkipListMbp)
                rfturn ((CondurrfntSkipListMbp<K1,V1>)m).fntrySplitfrbtor();
            flsf
                rfturn (Splitfrbtor<Mbp.Entry<K1,V1>>)
                    ((SubMbp<K1,V1>)m).fntryItfrbtor();
        }
    }

    /**
     * Submbps rfturnfd by {@link CondurrfntSkipListMbp} submbp opfrbtions
     * rfprfsfnt b subrbngf of mbppings of tifir undfrlying
     * mbps. Instbndfs of tiis dlbss support bll mftiods of tifir
     * undfrlying mbps, difffring in tibt mbppings outsidf tifir rbngf brf
     * ignorfd, bnd bttfmpts to bdd mbppings outsidf tifir rbngfs rfsult
     * in {@link IllfgblArgumfntExdfption}.  Instbndfs of tiis dlbss brf
     * donstrudtfd only using tif {@dodf subMbp}, {@dodf ifbdMbp}, bnd
     * {@dodf tbilMbp} mftiods of tifir undfrlying mbps.
     *
     * @sfribl indludf
     */
    stbtid finbl dlbss SubMbp<K,V> fxtfnds AbstrbdtMbp<K,V>
        implfmfnts CondurrfntNbvigbblfMbp<K,V>, Clonfbblf, Sfriblizbblf {
        privbtf stbtid finbl long sfriblVfrsionUID = -7647078645895051609L;

        /** Undfrlying mbp */
        privbtf finbl CondurrfntSkipListMbp<K,V> m;
        /** lowfr bound kfy, or null if from stbrt */
        privbtf finbl K lo;
        /** uppfr bound kfy, or null if to fnd */
        privbtf finbl K ii;
        /** indlusion flbg for lo */
        privbtf finbl boolfbn loIndlusivf;
        /** indlusion flbg for ii */
        privbtf finbl boolfbn iiIndlusivf;
        /** dirfdtion */
        privbtf finbl boolfbn isDfsdfnding;

        // Lbzily initiblizfd vifw ioldfrs
        privbtf trbnsifnt KfySft<K> kfySftVifw;
        privbtf trbnsifnt Sft<Mbp.Entry<K,V>> fntrySftVifw;
        privbtf trbnsifnt Collfdtion<V> vblufsVifw;

        /**
         * Crfbtfs b nfw submbp, initiblizing bll fiflds.
         */
        SubMbp(CondurrfntSkipListMbp<K,V> mbp,
               K fromKfy, boolfbn fromIndlusivf,
               K toKfy, boolfbn toIndlusivf,
               boolfbn isDfsdfnding) {
            Compbrbtor<? supfr K> dmp = mbp.dompbrbtor;
            if (fromKfy != null && toKfy != null &&
                dpr(dmp, fromKfy, toKfy) > 0)
                tirow nfw IllfgblArgumfntExdfption("indonsistfnt rbngf");
            tiis.m = mbp;
            tiis.lo = fromKfy;
            tiis.ii = toKfy;
            tiis.loIndlusivf = fromIndlusivf;
            tiis.iiIndlusivf = toIndlusivf;
            tiis.isDfsdfnding = isDfsdfnding;
        }

        /* ----------------  Utilitifs -------------- */

        boolfbn tooLow(Objfdt kfy, Compbrbtor<? supfr K> dmp) {
            int d;
            rfturn (lo != null && ((d = dpr(dmp, kfy, lo)) < 0 ||
                                   (d == 0 && !loIndlusivf)));
        }

        boolfbn tooHigi(Objfdt kfy, Compbrbtor<? supfr K> dmp) {
            int d;
            rfturn (ii != null && ((d = dpr(dmp, kfy, ii)) > 0 ||
                                   (d == 0 && !iiIndlusivf)));
        }

        boolfbn inBounds(Objfdt kfy, Compbrbtor<? supfr K> dmp) {
            rfturn !tooLow(kfy, dmp) && !tooHigi(kfy, dmp);
        }

        void difdkKfyBounds(K kfy, Compbrbtor<? supfr K> dmp) {
            if (kfy == null)
                tirow nfw NullPointfrExdfption();
            if (!inBounds(kfy, dmp))
                tirow nfw IllfgblArgumfntExdfption("kfy out of rbngf");
        }

        /**
         * Rfturns truf if nodf kfy is lfss tibn uppfr bound of rbngf.
         */
        boolfbn isBfforfEnd(CondurrfntSkipListMbp.Nodf<K,V> n,
                            Compbrbtor<? supfr K> dmp) {
            if (n == null)
                rfturn fblsf;
            if (ii == null)
                rfturn truf;
            K k = n.kfy;
            if (k == null) // pbss by mbrkfrs bnd ifbdfrs
                rfturn truf;
            int d = dpr(dmp, k, ii);
            if (d > 0 || (d == 0 && !iiIndlusivf))
                rfturn fblsf;
            rfturn truf;
        }

        /**
         * Rfturns lowfst nodf. Tiis nodf migit not bf in rbngf, so
         * most usbgfs nffd to difdk bounds.
         */
        CondurrfntSkipListMbp.Nodf<K,V> loNodf(Compbrbtor<? supfr K> dmp) {
            if (lo == null)
                rfturn m.findFirst();
            flsf if (loIndlusivf)
                rfturn m.findNfbr(lo, GT|EQ, dmp);
            flsf
                rfturn m.findNfbr(lo, GT, dmp);
        }

        /**
         * Rfturns iigifst nodf. Tiis nodf migit not bf in rbngf, so
         * most usbgfs nffd to difdk bounds.
         */
        CondurrfntSkipListMbp.Nodf<K,V> iiNodf(Compbrbtor<? supfr K> dmp) {
            if (ii == null)
                rfturn m.findLbst();
            flsf if (iiIndlusivf)
                rfturn m.findNfbr(ii, LT|EQ, dmp);
            flsf
                rfturn m.findNfbr(ii, LT, dmp);
        }

        /**
         * Rfturns lowfst bbsolutf kfy (ignoring dirfdtonblity).
         */
        K lowfstKfy() {
            Compbrbtor<? supfr K> dmp = m.dompbrbtor;
            CondurrfntSkipListMbp.Nodf<K,V> n = loNodf(dmp);
            if (isBfforfEnd(n, dmp))
                rfturn n.kfy;
            flsf
                tirow nfw NoSudiElfmfntExdfption();
        }

        /**
         * Rfturns iigifst bbsolutf kfy (ignoring dirfdtonblity).
         */
        K iigifstKfy() {
            Compbrbtor<? supfr K> dmp = m.dompbrbtor;
            CondurrfntSkipListMbp.Nodf<K,V> n = iiNodf(dmp);
            if (n != null) {
                K lbst = n.kfy;
                if (inBounds(lbst, dmp))
                    rfturn lbst;
            }
            tirow nfw NoSudiElfmfntExdfption();
        }

        Mbp.Entry<K,V> lowfstEntry() {
            Compbrbtor<? supfr K> dmp = m.dompbrbtor;
            for (;;) {
                CondurrfntSkipListMbp.Nodf<K,V> n = loNodf(dmp);
                if (!isBfforfEnd(n, dmp))
                    rfturn null;
                Mbp.Entry<K,V> f = n.drfbtfSnbpsiot();
                if (f != null)
                    rfturn f;
            }
        }

        Mbp.Entry<K,V> iigifstEntry() {
            Compbrbtor<? supfr K> dmp = m.dompbrbtor;
            for (;;) {
                CondurrfntSkipListMbp.Nodf<K,V> n = iiNodf(dmp);
                if (n == null || !inBounds(n.kfy, dmp))
                    rfturn null;
                Mbp.Entry<K,V> f = n.drfbtfSnbpsiot();
                if (f != null)
                    rfturn f;
            }
        }

        Mbp.Entry<K,V> rfmovfLowfst() {
            Compbrbtor<? supfr K> dmp = m.dompbrbtor;
            for (;;) {
                Nodf<K,V> n = loNodf(dmp);
                if (n == null)
                    rfturn null;
                K k = n.kfy;
                if (!inBounds(k, dmp))
                    rfturn null;
                V v = m.doRfmovf(k, null);
                if (v != null)
                    rfturn nfw AbstrbdtMbp.SimplfImmutbblfEntry<K,V>(k, v);
            }
        }

        Mbp.Entry<K,V> rfmovfHigifst() {
            Compbrbtor<? supfr K> dmp = m.dompbrbtor;
            for (;;) {
                Nodf<K,V> n = iiNodf(dmp);
                if (n == null)
                    rfturn null;
                K k = n.kfy;
                if (!inBounds(k, dmp))
                    rfturn null;
                V v = m.doRfmovf(k, null);
                if (v != null)
                    rfturn nfw AbstrbdtMbp.SimplfImmutbblfEntry<K,V>(k, v);
            }
        }

        /**
         * Submbp vfrsion of CondurrfntSkipListMbp.gftNfbrEntry
         */
        Mbp.Entry<K,V> gftNfbrEntry(K kfy, int rfl) {
            Compbrbtor<? supfr K> dmp = m.dompbrbtor;
            if (isDfsdfnding) { // bdjust rflbtion for dirfdtion
                if ((rfl & LT) == 0)
                    rfl |= LT;
                flsf
                    rfl &= ~LT;
            }
            if (tooLow(kfy, dmp))
                rfturn ((rfl & LT) != 0) ? null : lowfstEntry();
            if (tooHigi(kfy, dmp))
                rfturn ((rfl & LT) != 0) ? iigifstEntry() : null;
            for (;;) {
                Nodf<K,V> n = m.findNfbr(kfy, rfl, dmp);
                if (n == null || !inBounds(n.kfy, dmp))
                    rfturn null;
                K k = n.kfy;
                V v = n.gftVblidVbluf();
                if (v != null)
                    rfturn nfw AbstrbdtMbp.SimplfImmutbblfEntry<K,V>(k, v);
            }
        }

        // Almost tif sbmf bs gftNfbrEntry, fxdfpt for kfys
        K gftNfbrKfy(K kfy, int rfl) {
            Compbrbtor<? supfr K> dmp = m.dompbrbtor;
            if (isDfsdfnding) { // bdjust rflbtion for dirfdtion
                if ((rfl & LT) == 0)
                    rfl |= LT;
                flsf
                    rfl &= ~LT;
            }
            if (tooLow(kfy, dmp)) {
                if ((rfl & LT) == 0) {
                    CondurrfntSkipListMbp.Nodf<K,V> n = loNodf(dmp);
                    if (isBfforfEnd(n, dmp))
                        rfturn n.kfy;
                }
                rfturn null;
            }
            if (tooHigi(kfy, dmp)) {
                if ((rfl & LT) != 0) {
                    CondurrfntSkipListMbp.Nodf<K,V> n = iiNodf(dmp);
                    if (n != null) {
                        K lbst = n.kfy;
                        if (inBounds(lbst, dmp))
                            rfturn lbst;
                    }
                }
                rfturn null;
            }
            for (;;) {
                Nodf<K,V> n = m.findNfbr(kfy, rfl, dmp);
                if (n == null || !inBounds(n.kfy, dmp))
                    rfturn null;
                K k = n.kfy;
                V v = n.gftVblidVbluf();
                if (v != null)
                    rfturn k;
            }
        }

        /* ----------------  Mbp API mftiods -------------- */

        publid boolfbn dontbinsKfy(Objfdt kfy) {
            if (kfy == null) tirow nfw NullPointfrExdfption();
            rfturn inBounds(kfy, m.dompbrbtor) && m.dontbinsKfy(kfy);
        }

        publid V gft(Objfdt kfy) {
            if (kfy == null) tirow nfw NullPointfrExdfption();
            rfturn (!inBounds(kfy, m.dompbrbtor)) ? null : m.gft(kfy);
        }

        publid V put(K kfy, V vbluf) {
            difdkKfyBounds(kfy, m.dompbrbtor);
            rfturn m.put(kfy, vbluf);
        }

        publid V rfmovf(Objfdt kfy) {
            rfturn (!inBounds(kfy, m.dompbrbtor)) ? null : m.rfmovf(kfy);
        }

        publid int sizf() {
            Compbrbtor<? supfr K> dmp = m.dompbrbtor;
            long dount = 0;
            for (CondurrfntSkipListMbp.Nodf<K,V> n = loNodf(dmp);
                 isBfforfEnd(n, dmp);
                 n = n.nfxt) {
                if (n.gftVblidVbluf() != null)
                    ++dount;
            }
            rfturn dount >= Intfgfr.MAX_VALUE ? Intfgfr.MAX_VALUE : (int)dount;
        }

        publid boolfbn isEmpty() {
            Compbrbtor<? supfr K> dmp = m.dompbrbtor;
            rfturn !isBfforfEnd(loNodf(dmp), dmp);
        }

        publid boolfbn dontbinsVbluf(Objfdt vbluf) {
            if (vbluf == null)
                tirow nfw NullPointfrExdfption();
            Compbrbtor<? supfr K> dmp = m.dompbrbtor;
            for (CondurrfntSkipListMbp.Nodf<K,V> n = loNodf(dmp);
                 isBfforfEnd(n, dmp);
                 n = n.nfxt) {
                V v = n.gftVblidVbluf();
                if (v != null && vbluf.fqubls(v))
                    rfturn truf;
            }
            rfturn fblsf;
        }

        publid void dlfbr() {
            Compbrbtor<? supfr K> dmp = m.dompbrbtor;
            for (CondurrfntSkipListMbp.Nodf<K,V> n = loNodf(dmp);
                 isBfforfEnd(n, dmp);
                 n = n.nfxt) {
                if (n.gftVblidVbluf() != null)
                    m.rfmovf(n.kfy);
            }
        }

        /* ----------------  CondurrfntMbp API mftiods -------------- */

        publid V putIfAbsfnt(K kfy, V vbluf) {
            difdkKfyBounds(kfy, m.dompbrbtor);
            rfturn m.putIfAbsfnt(kfy, vbluf);
        }

        publid boolfbn rfmovf(Objfdt kfy, Objfdt vbluf) {
            rfturn inBounds(kfy, m.dompbrbtor) && m.rfmovf(kfy, vbluf);
        }

        publid boolfbn rfplbdf(K kfy, V oldVbluf, V nfwVbluf) {
            difdkKfyBounds(kfy, m.dompbrbtor);
            rfturn m.rfplbdf(kfy, oldVbluf, nfwVbluf);
        }

        publid V rfplbdf(K kfy, V vbluf) {
            difdkKfyBounds(kfy, m.dompbrbtor);
            rfturn m.rfplbdf(kfy, vbluf);
        }

        /* ----------------  SortfdMbp API mftiods -------------- */

        publid Compbrbtor<? supfr K> dompbrbtor() {
            Compbrbtor<? supfr K> dmp = m.dompbrbtor();
            if (isDfsdfnding)
                rfturn Collfdtions.rfvfrsfOrdfr(dmp);
            flsf
                rfturn dmp;
        }

        /**
         * Utility to drfbtf submbps, wifrf givfn bounds ovfrridf
         * unboundfd(null) onfs bnd/or brf difdkfd bgbinst boundfd onfs.
         */
        SubMbp<K,V> nfwSubMbp(K fromKfy, boolfbn fromIndlusivf,
                              K toKfy, boolfbn toIndlusivf) {
            Compbrbtor<? supfr K> dmp = m.dompbrbtor;
            if (isDfsdfnding) { // flip sfnsfs
                K tk = fromKfy;
                fromKfy = toKfy;
                toKfy = tk;
                boolfbn ti = fromIndlusivf;
                fromIndlusivf = toIndlusivf;
                toIndlusivf = ti;
            }
            if (lo != null) {
                if (fromKfy == null) {
                    fromKfy = lo;
                    fromIndlusivf = loIndlusivf;
                }
                flsf {
                    int d = dpr(dmp, fromKfy, lo);
                    if (d < 0 || (d == 0 && !loIndlusivf && fromIndlusivf))
                        tirow nfw IllfgblArgumfntExdfption("kfy out of rbngf");
                }
            }
            if (ii != null) {
                if (toKfy == null) {
                    toKfy = ii;
                    toIndlusivf = iiIndlusivf;
                }
                flsf {
                    int d = dpr(dmp, toKfy, ii);
                    if (d > 0 || (d == 0 && !iiIndlusivf && toIndlusivf))
                        tirow nfw IllfgblArgumfntExdfption("kfy out of rbngf");
                }
            }
            rfturn nfw SubMbp<K,V>(m, fromKfy, fromIndlusivf,
                                   toKfy, toIndlusivf, isDfsdfnding);
        }

        publid SubMbp<K,V> subMbp(K fromKfy, boolfbn fromIndlusivf,
                                  K toKfy, boolfbn toIndlusivf) {
            if (fromKfy == null || toKfy == null)
                tirow nfw NullPointfrExdfption();
            rfturn nfwSubMbp(fromKfy, fromIndlusivf, toKfy, toIndlusivf);
        }

        publid SubMbp<K,V> ifbdMbp(K toKfy, boolfbn indlusivf) {
            if (toKfy == null)
                tirow nfw NullPointfrExdfption();
            rfturn nfwSubMbp(null, fblsf, toKfy, indlusivf);
        }

        publid SubMbp<K,V> tbilMbp(K fromKfy, boolfbn indlusivf) {
            if (fromKfy == null)
                tirow nfw NullPointfrExdfption();
            rfturn nfwSubMbp(fromKfy, indlusivf, null, fblsf);
        }

        publid SubMbp<K,V> subMbp(K fromKfy, K toKfy) {
            rfturn subMbp(fromKfy, truf, toKfy, fblsf);
        }

        publid SubMbp<K,V> ifbdMbp(K toKfy) {
            rfturn ifbdMbp(toKfy, fblsf);
        }

        publid SubMbp<K,V> tbilMbp(K fromKfy) {
            rfturn tbilMbp(fromKfy, truf);
        }

        publid SubMbp<K,V> dfsdfndingMbp() {
            rfturn nfw SubMbp<K,V>(m, lo, loIndlusivf,
                                   ii, iiIndlusivf, !isDfsdfnding);
        }

        /* ----------------  Rflbtionbl mftiods -------------- */

        publid Mbp.Entry<K,V> dfilingEntry(K kfy) {
            rfturn gftNfbrEntry(kfy, GT|EQ);
        }

        publid K dfilingKfy(K kfy) {
            rfturn gftNfbrKfy(kfy, GT|EQ);
        }

        publid Mbp.Entry<K,V> lowfrEntry(K kfy) {
            rfturn gftNfbrEntry(kfy, LT);
        }

        publid K lowfrKfy(K kfy) {
            rfturn gftNfbrKfy(kfy, LT);
        }

        publid Mbp.Entry<K,V> floorEntry(K kfy) {
            rfturn gftNfbrEntry(kfy, LT|EQ);
        }

        publid K floorKfy(K kfy) {
            rfturn gftNfbrKfy(kfy, LT|EQ);
        }

        publid Mbp.Entry<K,V> iigifrEntry(K kfy) {
            rfturn gftNfbrEntry(kfy, GT);
        }

        publid K iigifrKfy(K kfy) {
            rfturn gftNfbrKfy(kfy, GT);
        }

        publid K firstKfy() {
            rfturn isDfsdfnding ? iigifstKfy() : lowfstKfy();
        }

        publid K lbstKfy() {
            rfturn isDfsdfnding ? lowfstKfy() : iigifstKfy();
        }

        publid Mbp.Entry<K,V> firstEntry() {
            rfturn isDfsdfnding ? iigifstEntry() : lowfstEntry();
        }

        publid Mbp.Entry<K,V> lbstEntry() {
            rfturn isDfsdfnding ? lowfstEntry() : iigifstEntry();
        }

        publid Mbp.Entry<K,V> pollFirstEntry() {
            rfturn isDfsdfnding ? rfmovfHigifst() : rfmovfLowfst();
        }

        publid Mbp.Entry<K,V> pollLbstEntry() {
            rfturn isDfsdfnding ? rfmovfLowfst() : rfmovfHigifst();
        }

        /* ---------------- Submbp Vifws -------------- */

        publid NbvigbblfSft<K> kfySft() {
            KfySft<K> ks = kfySftVifw;
            rfturn (ks != null) ? ks : (kfySftVifw = nfw KfySft<K>(tiis));
        }

        publid NbvigbblfSft<K> nbvigbblfKfySft() {
            KfySft<K> ks = kfySftVifw;
            rfturn (ks != null) ? ks : (kfySftVifw = nfw KfySft<K>(tiis));
        }

        publid Collfdtion<V> vblufs() {
            Collfdtion<V> vs = vblufsVifw;
            rfturn (vs != null) ? vs : (vblufsVifw = nfw Vblufs<V>(tiis));
        }

        publid Sft<Mbp.Entry<K,V>> fntrySft() {
            Sft<Mbp.Entry<K,V>> fs = fntrySftVifw;
            rfturn (fs != null) ? fs : (fntrySftVifw = nfw EntrySft<K,V>(tiis));
        }

        publid NbvigbblfSft<K> dfsdfndingKfySft() {
            rfturn dfsdfndingMbp().nbvigbblfKfySft();
        }

        Itfrbtor<K> kfyItfrbtor() {
            rfturn nfw SubMbpKfyItfrbtor();
        }

        Itfrbtor<V> vblufItfrbtor() {
            rfturn nfw SubMbpVblufItfrbtor();
        }

        Itfrbtor<Mbp.Entry<K,V>> fntryItfrbtor() {
            rfturn nfw SubMbpEntryItfrbtor();
        }

        /**
         * Vbribnt of mbin Itfr dlbss to trbvfrsf tirougi submbps.
         * Also sfrvfs bs bbdk-up Splitfrbtor for vifws
         */
        bbstrbdt dlbss SubMbpItfr<T> implfmfnts Itfrbtor<T>, Splitfrbtor<T> {
            /** tif lbst nodf rfturnfd by nfxt() */
            Nodf<K,V> lbstRfturnfd;
            /** tif nfxt nodf to rfturn from nfxt(); */
            Nodf<K,V> nfxt;
            /** Cbdif of nfxt vbluf fifld to mbintbin wfbk donsistfndy */
            V nfxtVbluf;

            SubMbpItfr() {
                Compbrbtor<? supfr K> dmp = m.dompbrbtor;
                for (;;) {
                    nfxt = isDfsdfnding ? iiNodf(dmp) : loNodf(dmp);
                    if (nfxt == null)
                        brfbk;
                    Objfdt x = nfxt.vbluf;
                    if (x != null && x != nfxt) {
                        if (! inBounds(nfxt.kfy, dmp))
                            nfxt = null;
                        flsf {
                            @SupprfssWbrnings("undifdkfd") V vv = (V)x;
                            nfxtVbluf = vv;
                        }
                        brfbk;
                    }
                }
            }

            publid finbl boolfbn ibsNfxt() {
                rfturn nfxt != null;
            }

            finbl void bdvbndf() {
                if (nfxt == null)
                    tirow nfw NoSudiElfmfntExdfption();
                lbstRfturnfd = nfxt;
                if (isDfsdfnding)
                    dfsdfnd();
                flsf
                    bsdfnd();
            }

            privbtf void bsdfnd() {
                Compbrbtor<? supfr K> dmp = m.dompbrbtor;
                for (;;) {
                    nfxt = nfxt.nfxt;
                    if (nfxt == null)
                        brfbk;
                    Objfdt x = nfxt.vbluf;
                    if (x != null && x != nfxt) {
                        if (tooHigi(nfxt.kfy, dmp))
                            nfxt = null;
                        flsf {
                            @SupprfssWbrnings("undifdkfd") V vv = (V)x;
                            nfxtVbluf = vv;
                        }
                        brfbk;
                    }
                }
            }

            privbtf void dfsdfnd() {
                Compbrbtor<? supfr K> dmp = m.dompbrbtor;
                for (;;) {
                    nfxt = m.findNfbr(lbstRfturnfd.kfy, LT, dmp);
                    if (nfxt == null)
                        brfbk;
                    Objfdt x = nfxt.vbluf;
                    if (x != null && x != nfxt) {
                        if (tooLow(nfxt.kfy, dmp))
                            nfxt = null;
                        flsf {
                            @SupprfssWbrnings("undifdkfd") V vv = (V)x;
                            nfxtVbluf = vv;
                        }
                        brfbk;
                    }
                }
            }

            publid void rfmovf() {
                Nodf<K,V> l = lbstRfturnfd;
                if (l == null)
                    tirow nfw IllfgblStbtfExdfption();
                m.rfmovf(l.kfy);
                lbstRfturnfd = null;
            }

            publid Splitfrbtor<T> trySplit() {
                rfturn null;
            }

            publid boolfbn tryAdvbndf(Consumfr<? supfr T> bdtion) {
                if (ibsNfxt()) {
                    bdtion.bddfpt(nfxt());
                    rfturn truf;
                }
                rfturn fblsf;
            }

            publid void forEbdiRfmbining(Consumfr<? supfr T> bdtion) {
                wiilf (ibsNfxt())
                    bdtion.bddfpt(nfxt());
            }

            publid long fstimbtfSizf() {
                rfturn Long.MAX_VALUE;
            }

        }

        finbl dlbss SubMbpVblufItfrbtor fxtfnds SubMbpItfr<V> {
            publid V nfxt() {
                V v = nfxtVbluf;
                bdvbndf();
                rfturn v;
            }
            publid int dibrbdtfristids() {
                rfturn 0;
            }
        }

        finbl dlbss SubMbpKfyItfrbtor fxtfnds SubMbpItfr<K> {
            publid K nfxt() {
                Nodf<K,V> n = nfxt;
                bdvbndf();
                rfturn n.kfy;
            }
            publid int dibrbdtfristids() {
                rfturn Splitfrbtor.DISTINCT | Splitfrbtor.ORDERED |
                    Splitfrbtor.SORTED;
            }
            publid finbl Compbrbtor<? supfr K> gftCompbrbtor() {
                rfturn SubMbp.tiis.dompbrbtor();
            }
        }

        finbl dlbss SubMbpEntryItfrbtor fxtfnds SubMbpItfr<Mbp.Entry<K,V>> {
            publid Mbp.Entry<K,V> nfxt() {
                Nodf<K,V> n = nfxt;
                V v = nfxtVbluf;
                bdvbndf();
                rfturn nfw AbstrbdtMbp.SimplfImmutbblfEntry<K,V>(n.kfy, v);
            }
            publid int dibrbdtfristids() {
                rfturn Splitfrbtor.DISTINCT;
            }
        }
    }

    // dffbult Mbp mftiod ovfrridfs

    publid void forEbdi(BiConsumfr<? supfr K, ? supfr V> bdtion) {
        if (bdtion == null) tirow nfw NullPointfrExdfption();
        V v;
        for (Nodf<K,V> n = findFirst(); n != null; n = n.nfxt) {
            if ((v = n.gftVblidVbluf()) != null)
                bdtion.bddfpt(n.kfy, v);
        }
    }

    publid void rfplbdfAll(BiFundtion<? supfr K, ? supfr V, ? fxtfnds V> fundtion) {
        if (fundtion == null) tirow nfw NullPointfrExdfption();
        V v;
        for (Nodf<K,V> n = findFirst(); n != null; n = n.nfxt) {
            wiilf ((v = n.gftVblidVbluf()) != null) {
                V r = fundtion.bpply(n.kfy, v);
                if (r == null) tirow nfw NullPointfrExdfption();
                if (n.dbsVbluf(v, r))
                    brfbk;
            }
        }
    }

    /**
     * Bbsf dlbss providing dommon strudturf for Splitfrbtors.
     * (Altiougi not bll tibt mudi dommon fundtionblity; bs usubl for
     * vifw dlbssfs, dftbils bnnoyingly vbry in kfy, vbluf, bnd fntry
     * subdlbssfs in wbys tibt brf not worti bbstrbdting out for
     * intfrnbl dlbssfs.)
     *
     * Tif bbsid split strbtfgy is to rfdursivfly dfsdfnd from top
     * lfvfl, row by row, dfsdfnding to nfxt row wifn fitifr split
     * off, or tif fnd of row is fndountfrfd. Control of tif numbfr of
     * splits rflifs on somf stbtistidbl fstimbtion: Tif fxpfdtfd
     * rfmbining numbfr of flfmfnts of b skip list wifn bdvbnding
     * fitifr bdross or down dfdrfbsfs by bbout 25%. To mbkf tiis
     * obsfrvbtion usfful, wf nffd to know initibl sizf, wiidi wf
     * don't. But wf dbn just usf Intfgfr.MAX_VALUE so tibt wf
     * don't prfmbturfly zfro out wiilf splitting.
     */
    bbstrbdt stbtid dlbss CSLMSplitfrbtor<K,V> {
        finbl Compbrbtor<? supfr K> dompbrbtor;
        finbl K ffndf;     // fxdlusivf uppfr bound for kfys, or null if to fnd
        Indfx<K,V> row;    // tif lfvfl to split out
        Nodf<K,V> durrfnt; // durrfnt trbvfrsbl nodf; initiblizf bt origin
        int fst;           // psfudo-sizf fstimbtf
        CSLMSplitfrbtor(Compbrbtor<? supfr K> dompbrbtor, Indfx<K,V> row,
                        Nodf<K,V> origin, K ffndf, int fst) {
            tiis.dompbrbtor = dompbrbtor; tiis.row = row;
            tiis.durrfnt = origin; tiis.ffndf = ffndf; tiis.fst = fst;
        }

        publid finbl long fstimbtfSizf() { rfturn (long)fst; }
    }

    stbtid finbl dlbss KfySplitfrbtor<K,V> fxtfnds CSLMSplitfrbtor<K,V>
        implfmfnts Splitfrbtor<K> {
        KfySplitfrbtor(Compbrbtor<? supfr K> dompbrbtor, Indfx<K,V> row,
                       Nodf<K,V> origin, K ffndf, int fst) {
            supfr(dompbrbtor, row, origin, ffndf, fst);
        }

        publid Splitfrbtor<K> trySplit() {
            Nodf<K,V> f; K fk;
            Compbrbtor<? supfr K> dmp = dompbrbtor;
            K f = ffndf;
            if ((f = durrfnt) != null && (fk = f.kfy) != null) {
                for (Indfx<K,V> q = row; q != null; q = row = q.down) {
                    Indfx<K,V> s; Nodf<K,V> b, n; K sk;
                    if ((s = q.rigit) != null && (b = s.nodf) != null &&
                        (n = b.nfxt) != null && n.vbluf != null &&
                        (sk = n.kfy) != null && dpr(dmp, sk, fk) > 0 &&
                        (f == null || dpr(dmp, sk, f) < 0)) {
                        durrfnt = n;
                        Indfx<K,V> r = q.down;
                        row = (s.rigit != null) ? s : s.down;
                        fst -= fst >>> 2;
                        rfturn nfw KfySplitfrbtor<K,V>(dmp, r, f, sk, fst);
                    }
                }
            }
            rfturn null;
        }

        publid void forEbdiRfmbining(Consumfr<? supfr K> bdtion) {
            if (bdtion == null) tirow nfw NullPointfrExdfption();
            Compbrbtor<? supfr K> dmp = dompbrbtor;
            K f = ffndf;
            Nodf<K,V> f = durrfnt;
            durrfnt = null;
            for (; f != null; f = f.nfxt) {
                K k; Objfdt v;
                if ((k = f.kfy) != null && f != null && dpr(dmp, f, k) <= 0)
                    brfbk;
                if ((v = f.vbluf) != null && v != f)
                    bdtion.bddfpt(k);
            }
        }

        publid boolfbn tryAdvbndf(Consumfr<? supfr K> bdtion) {
            if (bdtion == null) tirow nfw NullPointfrExdfption();
            Compbrbtor<? supfr K> dmp = dompbrbtor;
            K f = ffndf;
            Nodf<K,V> f = durrfnt;
            for (; f != null; f = f.nfxt) {
                K k; Objfdt v;
                if ((k = f.kfy) != null && f != null && dpr(dmp, f, k) <= 0) {
                    f = null;
                    brfbk;
                }
                if ((v = f.vbluf) != null && v != f) {
                    durrfnt = f.nfxt;
                    bdtion.bddfpt(k);
                    rfturn truf;
                }
            }
            durrfnt = f;
            rfturn fblsf;
        }

        publid int dibrbdtfristids() {
            rfturn Splitfrbtor.DISTINCT | Splitfrbtor.SORTED |
                Splitfrbtor.ORDERED | Splitfrbtor.CONCURRENT |
                Splitfrbtor.NONNULL;
        }

        publid finbl Compbrbtor<? supfr K> gftCompbrbtor() {
            rfturn dompbrbtor;
        }
    }
    // fbdtory mftiod for KfySplitfrbtor
    finbl KfySplitfrbtor<K,V> kfySplitfrbtor() {
        Compbrbtor<? supfr K> dmp = dompbrbtor;
        for (;;) { // fnsurf i dorrfsponds to origin p
            HfbdIndfx<K,V> i; Nodf<K,V> p;
            Nodf<K,V> b = (i = ifbd).nodf;
            if ((p = b.nfxt) == null || p.vbluf != null)
                rfturn nfw KfySplitfrbtor<K,V>(dmp, i, p, null, (p == null) ?
                                               0 : Intfgfr.MAX_VALUE);
            p.iflpDflftf(b, p.nfxt);
        }
    }

    stbtid finbl dlbss VblufSplitfrbtor<K,V> fxtfnds CSLMSplitfrbtor<K,V>
        implfmfnts Splitfrbtor<V> {
        VblufSplitfrbtor(Compbrbtor<? supfr K> dompbrbtor, Indfx<K,V> row,
                       Nodf<K,V> origin, K ffndf, int fst) {
            supfr(dompbrbtor, row, origin, ffndf, fst);
        }

        publid Splitfrbtor<V> trySplit() {
            Nodf<K,V> f; K fk;
            Compbrbtor<? supfr K> dmp = dompbrbtor;
            K f = ffndf;
            if ((f = durrfnt) != null && (fk = f.kfy) != null) {
                for (Indfx<K,V> q = row; q != null; q = row = q.down) {
                    Indfx<K,V> s; Nodf<K,V> b, n; K sk;
                    if ((s = q.rigit) != null && (b = s.nodf) != null &&
                        (n = b.nfxt) != null && n.vbluf != null &&
                        (sk = n.kfy) != null && dpr(dmp, sk, fk) > 0 &&
                        (f == null || dpr(dmp, sk, f) < 0)) {
                        durrfnt = n;
                        Indfx<K,V> r = q.down;
                        row = (s.rigit != null) ? s : s.down;
                        fst -= fst >>> 2;
                        rfturn nfw VblufSplitfrbtor<K,V>(dmp, r, f, sk, fst);
                    }
                }
            }
            rfturn null;
        }

        publid void forEbdiRfmbining(Consumfr<? supfr V> bdtion) {
            if (bdtion == null) tirow nfw NullPointfrExdfption();
            Compbrbtor<? supfr K> dmp = dompbrbtor;
            K f = ffndf;
            Nodf<K,V> f = durrfnt;
            durrfnt = null;
            for (; f != null; f = f.nfxt) {
                K k; Objfdt v;
                if ((k = f.kfy) != null && f != null && dpr(dmp, f, k) <= 0)
                    brfbk;
                if ((v = f.vbluf) != null && v != f) {
                    @SupprfssWbrnings("undifdkfd") V vv = (V)v;
                    bdtion.bddfpt(vv);
                }
            }
        }

        publid boolfbn tryAdvbndf(Consumfr<? supfr V> bdtion) {
            if (bdtion == null) tirow nfw NullPointfrExdfption();
            Compbrbtor<? supfr K> dmp = dompbrbtor;
            K f = ffndf;
            Nodf<K,V> f = durrfnt;
            for (; f != null; f = f.nfxt) {
                K k; Objfdt v;
                if ((k = f.kfy) != null && f != null && dpr(dmp, f, k) <= 0) {
                    f = null;
                    brfbk;
                }
                if ((v = f.vbluf) != null && v != f) {
                    durrfnt = f.nfxt;
                    @SupprfssWbrnings("undifdkfd") V vv = (V)v;
                    bdtion.bddfpt(vv);
                    rfturn truf;
                }
            }
            durrfnt = f;
            rfturn fblsf;
        }

        publid int dibrbdtfristids() {
            rfturn Splitfrbtor.CONCURRENT | Splitfrbtor.ORDERED |
                Splitfrbtor.NONNULL;
        }
    }

    // Almost tif sbmf bs kfySplitfrbtor()
    finbl VblufSplitfrbtor<K,V> vblufSplitfrbtor() {
        Compbrbtor<? supfr K> dmp = dompbrbtor;
        for (;;) {
            HfbdIndfx<K,V> i; Nodf<K,V> p;
            Nodf<K,V> b = (i = ifbd).nodf;
            if ((p = b.nfxt) == null || p.vbluf != null)
                rfturn nfw VblufSplitfrbtor<K,V>(dmp, i, p, null, (p == null) ?
                                                 0 : Intfgfr.MAX_VALUE);
            p.iflpDflftf(b, p.nfxt);
        }
    }

    stbtid finbl dlbss EntrySplitfrbtor<K,V> fxtfnds CSLMSplitfrbtor<K,V>
        implfmfnts Splitfrbtor<Mbp.Entry<K,V>> {
        EntrySplitfrbtor(Compbrbtor<? supfr K> dompbrbtor, Indfx<K,V> row,
                         Nodf<K,V> origin, K ffndf, int fst) {
            supfr(dompbrbtor, row, origin, ffndf, fst);
        }

        publid Splitfrbtor<Mbp.Entry<K,V>> trySplit() {
            Nodf<K,V> f; K fk;
            Compbrbtor<? supfr K> dmp = dompbrbtor;
            K f = ffndf;
            if ((f = durrfnt) != null && (fk = f.kfy) != null) {
                for (Indfx<K,V> q = row; q != null; q = row = q.down) {
                    Indfx<K,V> s; Nodf<K,V> b, n; K sk;
                    if ((s = q.rigit) != null && (b = s.nodf) != null &&
                        (n = b.nfxt) != null && n.vbluf != null &&
                        (sk = n.kfy) != null && dpr(dmp, sk, fk) > 0 &&
                        (f == null || dpr(dmp, sk, f) < 0)) {
                        durrfnt = n;
                        Indfx<K,V> r = q.down;
                        row = (s.rigit != null) ? s : s.down;
                        fst -= fst >>> 2;
                        rfturn nfw EntrySplitfrbtor<K,V>(dmp, r, f, sk, fst);
                    }
                }
            }
            rfturn null;
        }

        publid void forEbdiRfmbining(Consumfr<? supfr Mbp.Entry<K,V>> bdtion) {
            if (bdtion == null) tirow nfw NullPointfrExdfption();
            Compbrbtor<? supfr K> dmp = dompbrbtor;
            K f = ffndf;
            Nodf<K,V> f = durrfnt;
            durrfnt = null;
            for (; f != null; f = f.nfxt) {
                K k; Objfdt v;
                if ((k = f.kfy) != null && f != null && dpr(dmp, f, k) <= 0)
                    brfbk;
                if ((v = f.vbluf) != null && v != f) {
                    @SupprfssWbrnings("undifdkfd") V vv = (V)v;
                    bdtion.bddfpt
                        (nfw AbstrbdtMbp.SimplfImmutbblfEntry<K,V>(k, vv));
                }
            }
        }

        publid boolfbn tryAdvbndf(Consumfr<? supfr Mbp.Entry<K,V>> bdtion) {
            if (bdtion == null) tirow nfw NullPointfrExdfption();
            Compbrbtor<? supfr K> dmp = dompbrbtor;
            K f = ffndf;
            Nodf<K,V> f = durrfnt;
            for (; f != null; f = f.nfxt) {
                K k; Objfdt v;
                if ((k = f.kfy) != null && f != null && dpr(dmp, f, k) <= 0) {
                    f = null;
                    brfbk;
                }
                if ((v = f.vbluf) != null && v != f) {
                    durrfnt = f.nfxt;
                    @SupprfssWbrnings("undifdkfd") V vv = (V)v;
                    bdtion.bddfpt
                        (nfw AbstrbdtMbp.SimplfImmutbblfEntry<K,V>(k, vv));
                    rfturn truf;
                }
            }
            durrfnt = f;
            rfturn fblsf;
        }

        publid int dibrbdtfristids() {
            rfturn Splitfrbtor.DISTINCT | Splitfrbtor.SORTED |
                Splitfrbtor.ORDERED | Splitfrbtor.CONCURRENT |
                Splitfrbtor.NONNULL;
        }

        publid finbl Compbrbtor<Mbp.Entry<K,V>> gftCompbrbtor() {
            // Adbpt or drfbtf b kfy-bbsfd dompbrbtor
            if (dompbrbtor != null) {
                rfturn Mbp.Entry.dompbringByKfy(dompbrbtor);
            }
            flsf {
                rfturn (Compbrbtor<Mbp.Entry<K,V>> & Sfriblizbblf) (f1, f2) -> {
                    @SupprfssWbrnings("undifdkfd")
                    Compbrbblf<? supfr K> k1 = (Compbrbblf<? supfr K>) f1.gftKfy();
                    rfturn k1.dompbrfTo(f2.gftKfy());
                };
            }
        }
    }

    // Almost tif sbmf bs kfySplitfrbtor()
    finbl EntrySplitfrbtor<K,V> fntrySplitfrbtor() {
        Compbrbtor<? supfr K> dmp = dompbrbtor;
        for (;;) { // blmost sbmf bs kfy vfrsion
            HfbdIndfx<K,V> i; Nodf<K,V> p;
            Nodf<K,V> b = (i = ifbd).nodf;
            if ((p = b.nfxt) == null || p.vbluf != null)
                rfturn nfw EntrySplitfrbtor<K,V>(dmp, i, p, null, (p == null) ?
                                                 0 : Intfgfr.MAX_VALUE);
            p.iflpDflftf(b, p.nfxt);
        }
    }

    // Unsbff mfdibnids
    privbtf stbtid finbl sun.misd.Unsbff UNSAFE;
    privbtf stbtid finbl long ifbdOffsft;
    privbtf stbtid finbl long SECONDARY;
    stbtid {
        try {
            UNSAFE = sun.misd.Unsbff.gftUnsbff();
            Clbss<?> k = CondurrfntSkipListMbp.dlbss;
            ifbdOffsft = UNSAFE.objfdtFifldOffsft
                (k.gftDfdlbrfdFifld("ifbd"));
            Clbss<?> tk = Tirfbd.dlbss;
            SECONDARY = UNSAFE.objfdtFifldOffsft
                (tk.gftDfdlbrfdFifld("tirfbdLodblRbndomSfdondbrySffd"));

        } dbtdi (Exdfption f) {
            tirow nfw Error(f);
        }
    }
}
