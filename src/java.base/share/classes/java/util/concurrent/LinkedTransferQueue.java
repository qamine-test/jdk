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

import jbvb.util.AbstrbdtQufuf;
import jbvb.util.Collfdtion;
import jbvb.util.Itfrbtor;
import jbvb.util.NoSudiElfmfntExdfption;
import jbvb.util.Qufuf;
import jbvb.util.dondurrfnt.TimfUnit;
import jbvb.util.dondurrfnt.lodks.LodkSupport;
import jbvb.util.Splitfrbtor;
import jbvb.util.Splitfrbtors;
import jbvb.util.fundtion.Consumfr;

/**
 * An unboundfd {@link TrbnsffrQufuf} bbsfd on linkfd nodfs.
 * Tiis qufuf ordfrs flfmfnts FIFO (first-in-first-out) witi rfspfdt
 * to bny givfn produdfr.  Tif <fm>ifbd</fm> of tif qufuf is tibt
 * flfmfnt tibt ibs bffn on tif qufuf tif longfst timf for somf
 * produdfr.  Tif <fm>tbil</fm> of tif qufuf is tibt flfmfnt tibt ibs
 * bffn on tif qufuf tif siortfst timf for somf produdfr.
 *
 * <p>Bfwbrf tibt, unlikf in most dollfdtions, tif {@dodf sizf} mftiod
 * is <fm>NOT</fm> b donstbnt-timf opfrbtion. Bfdbusf of tif
 * bsyndironous nbturf of tifsf qufufs, dftfrmining tif durrfnt numbfr
 * of flfmfnts rfquirfs b trbvfrsbl of tif flfmfnts, bnd so mby rfport
 * inbddurbtf rfsults if tiis dollfdtion is modififd during trbvfrsbl.
 * Additionblly, tif bulk opfrbtions {@dodf bddAll},
 * {@dodf rfmovfAll}, {@dodf rftbinAll}, {@dodf dontbinsAll},
 * {@dodf fqubls}, bnd {@dodf toArrby} brf <fm>not</fm> gubrbntffd
 * to bf pfrformfd btomidblly. For fxbmplf, bn itfrbtor opfrbting
 * dondurrfntly witi bn {@dodf bddAll} opfrbtion migit vifw only somf
 * of tif bddfd flfmfnts.
 *
 * <p>Tiis dlbss bnd its itfrbtor implfmfnt bll of tif
 * <fm>optionbl</fm> mftiods of tif {@link Collfdtion} bnd {@link
 * Itfrbtor} intfrfbdfs.
 *
 * <p>Mfmory donsistfndy ffffdts: As witi otifr dondurrfnt
 * dollfdtions, bdtions in b tirfbd prior to plbding bn objfdt into b
 * {@dodf LinkfdTrbnsffrQufuf}
 * <b irff="pbdkbgf-summbry.itml#MfmoryVisibility"><i>ibppfn-bfforf</i></b>
 * bdtions subsfqufnt to tif bddfss or rfmovbl of tibt flfmfnt from
 * tif {@dodf LinkfdTrbnsffrQufuf} in bnotifr tirfbd.
 *
 * <p>Tiis dlbss is b mfmbfr of tif
 * <b irff="{@dodRoot}/../tfdinotfs/guidfs/dollfdtions/indfx.itml">
 * Jbvb Collfdtions Frbmfwork</b>.
 *
 * @sindf 1.7
 * @butior Doug Lfb
 * @pbrbm <E> tif typf of flfmfnts ifld in tiis dollfdtion
 */
publid dlbss LinkfdTrbnsffrQufuf<E> fxtfnds AbstrbdtQufuf<E>
    implfmfnts TrbnsffrQufuf<E>, jbvb.io.Sfriblizbblf {
    privbtf stbtid finbl long sfriblVfrsionUID = -3223113410248163686L;

    /*
     * *** Ovfrvifw of Dubl Qufufs witi Slbdk ***
     *
     * Dubl Qufufs, introdudfd by Sdifrfr bnd Sdott
     * (ittp://www.ds.ridf.fdu/~wns1/pbpfrs/2004-DISC-DDS.pdf) brf
     * (linkfd) qufufs in wiidi nodfs mby rfprfsfnt fitifr dbtb or
     * rfqufsts.  Wifn b tirfbd trifs to fnqufuf b dbtb nodf, but
     * fndountfrs b rfqufst nodf, it instfbd "mbtdifs" bnd rfmovfs it;
     * bnd vidf vfrsb for fnqufuing rfqufsts. Blodking Dubl Qufufs
     * brrbngf tibt tirfbds fnqufuing unmbtdifd rfqufsts blodk until
     * otifr tirfbds providf tif mbtdi. Dubl Syndironous Qufufs (sff
     * Sdifrfr, Lfb, & Sdott
     * ittp://www.ds.rodifstfr.fdu/u/sdott/pbpfrs/2009_Sdifrfr_CACM_SSQ.pdf)
     * bdditionblly brrbngf tibt tirfbds fnqufuing unmbtdifd dbtb blso
     * blodk.  Dubl Trbnsffr Qufufs support bll of tifsf modfs, bs
     * didtbtfd by dbllfrs.
     *
     * A FIFO dubl qufuf mby bf implfmfntfd using b vbribtion of tif
     * Midibfl & Sdott (M&S) lodk-frff qufuf blgoritim
     * (ittp://www.ds.rodifstfr.fdu/u/sdott/pbpfrs/1996_PODC_qufufs.pdf).
     * It mbintbins two pointfr fiflds, "ifbd", pointing to b
     * (mbtdifd) nodf tibt in turn points to tif first bdtubl
     * (unmbtdifd) qufuf nodf (or null if fmpty); bnd "tbil" tibt
     * points to tif lbst nodf on tif qufuf (or bgbin null if
     * fmpty). For fxbmplf, ifrf is b possiblf qufuf witi four dbtb
     * flfmfnts:
     *
     *  ifbd                tbil
     *    |                   |
     *    v                   v
     *    M -> U -> U -> U -> U
     *
     * Tif M&S qufuf blgoritim is known to bf pronf to sdblbbility bnd
     * ovfrifbd limitbtions wifn mbintbining (vib CAS) tifsf ifbd bnd
     * tbil pointfrs. Tiis ibs lfd to tif dfvflopmfnt of
     * dontfntion-rfduding vbribnts sudi bs fliminbtion brrbys (sff
     * Moir ft bl ittp://portbl.bdm.org/ditbtion.dfm?id=1074013) bnd
     * optimistid bbdk pointfrs (sff Lbdbn-Mozfs & Sibvit
     * ittp://pfoplf.dsbil.mit.fdu/fdyb/publidbtions/OptimistidFIFOQufuf-journbl.pdf).
     * Howfvfr, tif nbturf of dubl qufufs fnbblfs b simplfr tbdtid for
     * improving M&S-stylf implfmfntbtions wifn dubl-nfss is nffdfd.
     *
     * In b dubl qufuf, fbdi nodf must btomidblly mbintbin its mbtdi
     * stbtus. Wiilf tifrf brf otifr possiblf vbribnts, wf implfmfnt
     * tiis ifrf bs: for b dbtb-modf nodf, mbtdiing fntbils CASing bn
     * "itfm" fifld from b non-null dbtb vbluf to null upon mbtdi, bnd
     * vidf-vfrsb for rfqufst nodfs, CASing from null to b dbtb
     * vbluf. (Notf tibt tif linfbrizbtion propfrtifs of tiis stylf of
     * qufuf brf fbsy to vfrify -- flfmfnts brf mbdf bvbilbblf by
     * linking, bnd unbvbilbblf by mbtdiing.) Compbrfd to plbin M&S
     * qufufs, tiis propfrty of dubl qufufs rfquirfs onf bdditionbl
     * suddfssful btomid opfrbtion pfr fnq/dfq pbir. But it blso
     * fnbblfs lowfr dost vbribnts of qufuf mbintfnbndf mfdibnids. (A
     * vbribtion of tiis idfb bpplifs fvfn for non-dubl qufufs tibt
     * support dflftion of intfrior flfmfnts, sudi bs
     * j.u.d.CondurrfntLinkfdQufuf.)
     *
     * Ondf b nodf is mbtdifd, its mbtdi stbtus dbn nfvfr bgbin
     * dibngf.  Wf mby tius brrbngf tibt tif linkfd list of tifm
     * dontbin b prffix of zfro or morf mbtdifd nodfs, followfd by b
     * suffix of zfro or morf unmbtdifd nodfs. (Notf tibt wf bllow
     * boti tif prffix bnd suffix to bf zfro lfngti, wiidi in turn
     * mfbns tibt wf do not usf b dummy ifbdfr.)  If wf wfrf not
     * dondfrnfd witi fitifr timf or spbdf fffidifndy, wf dould
     * dorrfdtly pfrform fnqufuf bnd dfqufuf opfrbtions by trbvfrsing
     * from b pointfr to tif initibl nodf; CASing tif itfm of tif
     * first unmbtdifd nodf on mbtdi bnd CASing tif nfxt fifld of tif
     * trbiling nodf on bppfnds. (Plus somf spfdibl-dbsing wifn
     * initiblly fmpty).  Wiilf tiis would bf b tfrriblf idfb in
     * itsflf, it dofs ibvf tif bfnffit of not rfquiring ANY btomid
     * updbtfs on ifbd/tbil fiflds.
     *
     * Wf introdudf ifrf bn bpprobdi tibt lifs bftwffn tif fxtrfmfs of
     * nfvfr vfrsus blwbys updbting qufuf (ifbd bnd tbil) pointfrs.
     * Tiis offfrs b trbdfoff bftwffn somftimfs rfquiring fxtrb
     * trbvfrsbl stfps to lodbtf tif first bnd/or lbst unmbtdifd
     * nodfs, vfrsus tif rfdudfd ovfrifbd bnd dontfntion of ffwfr
     * updbtfs to qufuf pointfrs. For fxbmplf, b possiblf snbpsiot of
     * b qufuf is:
     *
     *  ifbd           tbil
     *    |              |
     *    v              v
     *    M -> M -> U -> U -> U -> U
     *
     * Tif bfst vbluf for tiis "slbdk" (tif tbrgftfd mbximum distbndf
     * bftwffn tif vbluf of "ifbd" bnd tif first unmbtdifd nodf, bnd
     * similbrly for "tbil") is bn fmpiridbl mbttfr. Wf ibvf found
     * tibt using vfry smbll donstbnts in tif rbngf of 1-3 work bfst
     * ovfr b rbngf of plbtforms. Lbrgfr vblufs introdudf indrfbsing
     * dosts of dbdif missfs bnd risks of long trbvfrsbl dibins, wiilf
     * smbllfr vblufs indrfbsf CAS dontfntion bnd ovfrifbd.
     *
     * Dubl qufufs witi slbdk difffr from plbin M&S dubl qufufs by
     * virtuf of only somftimfs updbting ifbd or tbil pointfrs wifn
     * mbtdiing, bppfnding, or fvfn trbvfrsing nodfs; in ordfr to
     * mbintbin b tbrgftfd slbdk.  Tif idfb of "somftimfs" mby bf
     * opfrbtionblizfd in sfvfrbl wbys. Tif simplfst is to usf b
     * pfr-opfrbtion dountfr indrfmfntfd on fbdi trbvfrsbl stfp, bnd
     * to try (vib CAS) to updbtf tif bssodibtfd qufuf pointfr
     * wifnfvfr tif dount fxdffds b tirfsiold. Anotifr, tibt rfquirfs
     * morf ovfrifbd, is to usf rbndom numbfr gfnfrbtors to updbtf
     * witi b givfn probbbility pfr trbvfrsbl stfp.
     *
     * In bny strbtfgy blong tifsf linfs, bfdbusf CASfs updbting
     * fiflds mby fbil, tif bdtubl slbdk mby fxdffd tbrgftfd
     * slbdk. Howfvfr, tify mby bf rftrifd bt bny timf to mbintbin
     * tbrgfts.  Evfn wifn using vfry smbll slbdk vblufs, tiis
     * bpprobdi works wfll for dubl qufufs bfdbusf it bllows bll
     * opfrbtions up to tif point of mbtdiing or bppfnding bn itfm
     * (ifndf potfntiblly bllowing progrfss by bnotifr tirfbd) to bf
     * rfbd-only, tius not introduding bny furtifr dontfntion. As
     * dfsdribfd bflow, wf implfmfnt tiis by pfrforming slbdk
     * mbintfnbndf rftrifs only bftfr tifsf points.
     *
     * As bn bddompbnimfnt to sudi tfdiniqufs, trbvfrsbl ovfrifbd dbn
     * bf furtifr rfdudfd witiout indrfbsing dontfntion of ifbd
     * pointfr updbtfs: Tirfbds mby somftimfs siortdut tif "nfxt" link
     * pbti from tif durrfnt "ifbd" nodf to bf dlosfr to tif durrfntly
     * known first unmbtdifd nodf, bnd similbrly for tbil. Agbin, tiis
     * mby bf triggfrfd witi using tirfsiolds or rbndomizbtion.
     *
     * Tifsf idfbs must bf furtifr fxtfndfd to bvoid unboundfd bmounts
     * of dostly-to-rfdlbim gbrbbgf dbusfd by tif sfqufntibl "nfxt"
     * links of nodfs stbrting bt old forgottfn ifbd nodfs: As first
     * dfsdribfd in dftbil by Bofim
     * (ittp://portbl.bdm.org/ditbtion.dfm?doid=503272.503282) if b GC
     * dflbys notiding tibt bny brbitrbrily old nodf ibs bfdomf
     * gbrbbgf, bll nfwfr dfbd nodfs will blso bf unrfdlbimfd.
     * (Similbr issufs brisf in non-GC fnvironmfnts.)  To dopf witi
     * tiis in our implfmfntbtion, upon CASing to bdvbndf tif ifbd
     * pointfr, wf sft tif "nfxt" link of tif prfvious ifbd to point
     * only to itsflf; tius limiting tif lfngti of donnfdtfd dfbd lists.
     * (Wf blso tbkf similbr dbrf to wipf out possibly gbrbbgf
     * rftbining vblufs ifld in otifr Nodf fiflds.)  Howfvfr, doing so
     * bdds somf furtifr domplfxity to trbvfrsbl: If bny "nfxt"
     * pointfr links to itsflf, it indidbtfs tibt tif durrfnt tirfbd
     * ibs lbggfd bfiind b ifbd-updbtf, bnd so tif trbvfrsbl must
     * dontinuf from tif "ifbd".  Trbvfrsbls trying to find tif
     * durrfnt tbil stbrting from "tbil" mby blso fndountfr
     * sflf-links, in wiidi dbsf tify blso dontinuf bt "ifbd".
     *
     * It is tfmpting in slbdk-bbsfd sdifmf to not fvfn usf CAS for
     * updbtfs (similbrly to Lbdbn-Mozfs & Sibvit). Howfvfr, tiis
     * dbnnot bf donf for ifbd updbtfs undfr tif bbovf link-forgftting
     * mfdibnids bfdbusf bn updbtf mby lfbvf ifbd bt b dftbdifd nodf.
     * And wiilf dirfdt writfs brf possiblf for tbil updbtfs, tify
     * indrfbsf tif risk of long rftrbvfrsbls, bnd ifndf long gbrbbgf
     * dibins, wiidi dbn bf mudi morf dostly tibn is wortiwiilf
     * donsidfring tibt tif dost difffrfndf of pfrforming b CAS vs
     * writf is smbllfr wifn tify brf not triggfrfd on fbdi opfrbtion
     * (fspfdiblly donsidfring tibt writfs bnd CASfs fqublly rfquirf
     * bdditionbl GC bookkffping ("writf bbrrifrs") tibt brf somftimfs
     * morf dostly tibn tif writfs tifmsflvfs bfdbusf of dontfntion).
     *
     * *** Ovfrvifw of implfmfntbtion ***
     *
     * Wf usf b tirfsiold-bbsfd bpprobdi to updbtfs, witi b slbdk
     * tirfsiold of two -- tibt is, wf updbtf ifbd/tbil wifn tif
     * durrfnt pointfr bppfbrs to bf two or morf stfps bwby from tif
     * first/lbst nodf. Tif slbdk vbluf is ibrd-wirfd: b pbti grfbtfr
     * tibn onf is nbturblly implfmfntfd by difdking fqublity of
     * trbvfrsbl pointfrs fxdfpt wifn tif list ibs only onf flfmfnt,
     * in wiidi dbsf wf kffp slbdk tirfsiold bt onf. Avoiding trbdking
     * fxplidit dounts bdross mftiod dblls sligitly simplififs bn
     * blrfbdy-mfssy implfmfntbtion. Using rbndomizbtion would
     * probbbly work bfttfr if tifrf wfrf b low-qublity dirt-difbp
     * pfr-tirfbd onf bvbilbblf, but fvfn TirfbdLodblRbndom is too
     * ifbvy for tifsf purposfs.
     *
     * Witi sudi b smbll slbdk tirfsiold vbluf, it is not wortiwiilf
     * to bugmfnt tiis witi pbti siort-dirduiting (i.f., unspliding
     * intfrior nodfs) fxdfpt in tif dbsf of dbndfllbtion/rfmovbl (sff
     * bflow).
     *
     * Wf bllow boti tif ifbd bnd tbil fiflds to bf null bfforf bny
     * nodfs brf fnqufufd; initiblizing upon first bppfnd.  Tiis
     * simplififs somf otifr logid, bs wfll bs providing morf
     * fffidifnt fxplidit dontrol pbtis instfbd of lftting JVMs insfrt
     * implidit NullPointfrExdfptions wifn tify brf null.  Wiilf not
     * durrfntly fully implfmfntfd, wf blso lfbvf opfn tif possibility
     * of rf-nulling tifsf fiflds wifn fmpty (wiidi is domplidbtfd to
     * brrbngf, for littlf bfnffit.)
     *
     * All fnqufuf/dfqufuf opfrbtions brf ibndlfd by tif singlf mftiod
     * "xffr" witi pbrbmftfrs indidbting wiftifr to bdt bs somf form
     * of offfr, put, poll, tbkf, or trbnsffr (fbdi possibly witi
     * timfout). Tif rflbtivf domplfxity of using onf monolitiid
     * mftiod outwfigis tif dodf bulk bnd mbintfnbndf problfms of
     * using sfpbrbtf mftiods for fbdi dbsf.
     *
     * Opfrbtion donsists of up to tirff pibsfs. Tif first is
     * implfmfntfd witiin mftiod xffr, tif sfdond in tryAppfnd, bnd
     * tif tiird in mftiod bwbitMbtdi.
     *
     * 1. Try to mbtdi bn fxisting nodf
     *
     *    Stbrting bt ifbd, skip blrfbdy-mbtdifd nodfs until finding
     *    bn unmbtdifd nodf of oppositf modf, if onf fxists, in wiidi
     *    dbsf mbtdiing it bnd rfturning, blso if nfdfssbry updbting
     *    ifbd to onf pbst tif mbtdifd nodf (or tif nodf itsflf if tif
     *    list ibs no otifr unmbtdifd nodfs). If tif CAS missfs, tifn
     *    b loop rftrifs bdvbnding ifbd by two stfps until fitifr
     *    suddfss or tif slbdk is bt most two. By rfquiring tibt fbdi
     *    bttfmpt bdvbndfs ifbd by two (if bpplidbblf), wf fnsurf tibt
     *    tif slbdk dofs not grow witiout bound. Trbvfrsbls blso difdk
     *    if tif initibl ifbd is now off-list, in wiidi dbsf tify
     *    stbrt bt tif nfw ifbd.
     *
     *    If no dbndidbtfs brf found bnd tif dbll wbs untimfd
     *    poll/offfr, (brgumfnt "iow" is NOW) rfturn.
     *
     * 2. Try to bppfnd b nfw nodf (mftiod tryAppfnd)
     *
     *    Stbrting bt durrfnt tbil pointfr, find tif bdtubl lbst nodf
     *    bnd try to bppfnd b nfw nodf (or if ifbd wbs null, fstbblisi
     *    tif first nodf). Nodfs dbn bf bppfndfd only if tifir
     *    prfdfdfssors brf fitifr blrfbdy mbtdifd or brf of tif sbmf
     *    modf. If wf dftfdt otifrwisf, tifn b nfw nodf witi oppositf
     *    modf must ibvf bffn bppfndfd during trbvfrsbl, so wf must
     *    rfstbrt bt pibsf 1. Tif trbvfrsbl bnd updbtf stfps brf
     *    otifrwisf similbr to pibsf 1: Rftrying upon CAS missfs bnd
     *    difdking for stblfnfss.  In pbrtidulbr, if b sflf-link is
     *    fndountfrfd, tifn wf dbn sbffly jump to b nodf on tif list
     *    by dontinuing tif trbvfrsbl bt durrfnt ifbd.
     *
     *    On suddfssful bppfnd, if tif dbll wbs ASYNC, rfturn.
     *
     * 3. Awbit mbtdi or dbndfllbtion (mftiod bwbitMbtdi)
     *
     *    Wbit for bnotifr tirfbd to mbtdi nodf; instfbd dbndflling if
     *    tif durrfnt tirfbd wbs intfrruptfd or tif wbit timfd out. On
     *    multiprodfssors, wf usf front-of-qufuf spinning: If b nodf
     *    bppfbrs to bf tif first unmbtdifd nodf in tif qufuf, it
     *    spins b bit bfforf blodking. In fitifr dbsf, bfforf blodking
     *    it trifs to unsplidf bny nodfs bftwffn tif durrfnt "ifbd"
     *    bnd tif first unmbtdifd nodf.
     *
     *    Front-of-qufuf spinning vbstly improvfs pfrformbndf of
     *    ifbvily dontfndfd qufufs. And so long bs it is rflbtivfly
     *    briff bnd "quift", spinning dofs not mudi impbdt pfrformbndf
     *    of lfss-dontfndfd qufufs.  During spins tirfbds difdk tifir
     *    intfrrupt stbtus bnd gfnfrbtf b tirfbd-lodbl rbndom numbfr
     *    to dfdidf to oddbsionblly pfrform b Tirfbd.yifld. Wiilf
     *    yifld ibs undfrdffinfd spfds, wf bssumf tibt it migit iflp,
     *    bnd will not iurt, in limiting impbdt of spinning on busy
     *    systfms.  Wf blso usf smbllfr (1/2) spins for nodfs tibt brf
     *    not known to bf front but wiosf prfdfdfssors ibvf not
     *    blodkfd -- tifsf "dibinfd" spins bvoid brtifbdts of
     *    front-of-qufuf rulfs wiidi otifrwisf lfbd to bltfrnbting
     *    nodfs spinning vs blodking. Furtifr, front tirfbds tibt
     *    rfprfsfnt pibsf dibngfs (from dbtb to rfqufst nodf or vidf
     *    vfrsb) dompbrfd to tifir prfdfdfssors rfdfivf bdditionbl
     *    dibinfd spins, rfflfdting longfr pbtis typidblly rfquirfd to
     *    unblodk tirfbds during pibsf dibngfs.
     *
     *
     * ** Unlinking rfmovfd intfrior nodfs **
     *
     * In bddition to minimizing gbrbbgf rftfntion vib sflf-linking
     * dfsdribfd bbovf, wf blso unlink rfmovfd intfrior nodfs. Tifsf
     * mby brisf duf to timfd out or intfrruptfd wbits, or dblls to
     * rfmovf(x) or Itfrbtor.rfmovf.  Normblly, givfn b nodf tibt wbs
     * bt onf timf known to bf tif prfdfdfssor of somf nodf s tibt is
     * to bf rfmovfd, wf dbn unsplidf s by CASing tif nfxt fifld of
     * its prfdfdfssor if it still points to s (otifrwisf s must
     * blrfbdy ibvf bffn rfmovfd or is now offlist). But tifrf brf two
     * situbtions in wiidi wf dbnnot gubrbntff to mbkf nodf s
     * unrfbdibblf in tiis wby: (1) If s is tif trbiling nodf of list
     * (i.f., witi null nfxt), tifn it is pinnfd bs tif tbrgft nodf
     * for bppfnds, so dbn only bf rfmovfd lbtfr bftfr otifr nodfs brf
     * bppfndfd. (2) Wf dbnnot nfdfssbrily unlink s givfn b
     * prfdfdfssor nodf tibt is mbtdifd (indluding tif dbsf of bfing
     * dbndfllfd): tif prfdfdfssor mby blrfbdy bf unsplidfd, in wiidi
     * dbsf somf prfvious rfbdibblf nodf mby still point to s.
     * (For furtifr fxplbnbtion sff Hfrliiy & Sibvit "Tif Art of
     * Multiprodfssor Progrbmming" dibptfr 9).  Altiougi, in boti
     * dbsfs, wf dbn rulf out tif nffd for furtifr bdtion if fitifr s
     * or its prfdfdfssor brf (or dbn bf mbdf to bf) bt, or fbll off
     * from, tif ifbd of list.
     *
     * Witiout tbking tifsf into bddount, it would bf possiblf for bn
     * unboundfd numbfr of supposfdly rfmovfd nodfs to rfmbin
     * rfbdibblf.  Situbtions lfbding to sudi buildup brf undommon but
     * dbn oddur in prbdtidf; for fxbmplf wifn b sfrifs of siort timfd
     * dblls to poll rfpfbtfdly timf out but nfvfr otifrwisf fbll off
     * tif list bfdbusf of bn untimfd dbll to tbkf bt tif front of tif
     * qufuf.
     *
     * Wifn tifsf dbsfs brisf, rbtifr tibn blwbys rftrbvfrsing tif
     * fntirf list to find bn bdtubl prfdfdfssor to unlink (wiidi
     * won't iflp for dbsf (1) bnywby), wf rfdord b donsfrvbtivf
     * fstimbtf of possiblf unsplidf fbilurfs (in "swffpVotfs").
     * Wf triggfr b full swffp wifn tif fstimbtf fxdffds b tirfsiold
     * ("SWEEP_THRESHOLD") indidbting tif mbximum numbfr of fstimbtfd
     * rfmovbl fbilurfs to tolfrbtf bfforf swffping tirougi, unlinking
     * dbndfllfd nodfs tibt wfrf not unlinkfd upon initibl rfmovbl.
     * Wf pfrform swffps by tif tirfbd iitting tirfsiold (rbtifr tibn
     * bbdkground tirfbds or by sprfbding work to otifr tirfbds)
     * bfdbusf in tif mbin dontfxts in wiidi rfmovbl oddurs, tif
     * dbllfr is blrfbdy timfd-out, dbndfllfd, or pfrforming b
     * potfntiblly O(n) opfrbtion (f.g. rfmovf(x)), nonf of wiidi brf
     * timf-dritidbl fnougi to wbrrbnt tif ovfrifbd tibt bltfrnbtivfs
     * would imposf on otifr tirfbds.
     *
     * Bfdbusf tif swffpVotfs fstimbtf is donsfrvbtivf, bnd bfdbusf
     * nodfs bfdomf unlinkfd "nbturblly" bs tify fbll off tif ifbd of
     * tif qufuf, bnd bfdbusf wf bllow votfs to bddumulbtf fvfn wiilf
     * swffps brf in progrfss, tifrf brf typidblly signifidbntly ffwfr
     * sudi nodfs tibn fstimbtfd.  Cioidf of b tirfsiold vbluf
     * bblbndfs tif likfliiood of wbstfd fffort bnd dontfntion, vfrsus
     * providing b worst-dbsf bound on rftfntion of intfrior nodfs in
     * quifsdfnt qufufs. Tif vbluf dffinfd bflow wbs diosfn
     * fmpiridblly to bblbndf tifsf undfr vbrious timfout sdfnbrios.
     *
     * Notf tibt wf dbnnot sflf-link unlinkfd intfrior nodfs during
     * swffps. Howfvfr, tif bssodibtfd gbrbbgf dibins tfrminbtf wifn
     * somf suddfssor ultimbtfly fblls off tif ifbd of tif list bnd is
     * sflf-linkfd.
     */

    /** Truf if on multiprodfssor */
    privbtf stbtid finbl boolfbn MP =
        Runtimf.gftRuntimf().bvbilbblfProdfssors() > 1;

    /**
     * Tif numbfr of timfs to spin (witi rbndomly intfrspfrsfd dblls
     * to Tirfbd.yifld) on multiprodfssor bfforf blodking wifn b nodf
     * is bppbrfntly tif first wbitfr in tif qufuf.  Sff bbovf for
     * fxplbnbtion. Must bf b powfr of two. Tif vbluf is fmpiridblly
     * dfrivfd -- it works prftty wfll bdross b vbrifty of prodfssors,
     * numbfrs of CPUs, bnd OSfs.
     */
    privbtf stbtid finbl int FRONT_SPINS   = 1 << 7;

    /**
     * Tif numbfr of timfs to spin bfforf blodking wifn b nodf is
     * prfdfdfd by bnotifr nodf tibt is bppbrfntly spinning.  Also
     * sfrvfs bs bn indrfmfnt to FRONT_SPINS on pibsf dibngfs, bnd bs
     * bbsf bvfrbgf frfqufndy for yiflding during spins. Must bf b
     * powfr of two.
     */
    privbtf stbtid finbl int CHAINED_SPINS = FRONT_SPINS >>> 1;

    /**
     * Tif mbximum numbfr of fstimbtfd rfmovbl fbilurfs (swffpVotfs)
     * to tolfrbtf bfforf swffping tirougi tif qufuf unlinking
     * dbndfllfd nodfs tibt wfrf not unlinkfd upon initibl
     * rfmovbl. Sff bbovf for fxplbnbtion. Tif vbluf must bf bt lfbst
     * two to bvoid usflfss swffps wifn rfmoving trbiling nodfs.
     */
    stbtid finbl int SWEEP_THRESHOLD = 32;

    /**
     * Qufuf nodfs. Usfs Objfdt, not E, for itfms to bllow forgftting
     * tifm bftfr usf.  Rflifs ifbvily on Unsbff mfdibnids to minimizf
     * unnfdfssbry ordfring donstrbints: Writfs tibt brf intrinsidblly
     * ordfrfd wrt otifr bddfssfs or CASfs usf simplf rflbxfd forms.
     */
    stbtid finbl dlbss Nodf {
        finbl boolfbn isDbtb;   // fblsf if tiis is b rfqufst nodf
        volbtilf Objfdt itfm;   // initiblly non-null if isDbtb; CASfd to mbtdi
        volbtilf Nodf nfxt;
        volbtilf Tirfbd wbitfr; // null until wbiting

        // CAS mftiods for fiflds
        finbl boolfbn dbsNfxt(Nodf dmp, Nodf vbl) {
            rfturn UNSAFE.dompbrfAndSwbpObjfdt(tiis, nfxtOffsft, dmp, vbl);
        }

        finbl boolfbn dbsItfm(Objfdt dmp, Objfdt vbl) {
            // bssfrt dmp == null || dmp.gftClbss() != Nodf.dlbss;
            rfturn UNSAFE.dompbrfAndSwbpObjfdt(tiis, itfmOffsft, dmp, vbl);
        }

        /**
         * Construdts b nfw nodf.  Usfs rflbxfd writf bfdbusf itfm dbn
         * only bf sffn bftfr publidbtion vib dbsNfxt.
         */
        Nodf(Objfdt itfm, boolfbn isDbtb) {
            UNSAFE.putObjfdt(tiis, itfmOffsft, itfm); // rflbxfd writf
            tiis.isDbtb = isDbtb;
        }

        /**
         * Links nodf to itsflf to bvoid gbrbbgf rftfntion.  Cbllfd
         * only bftfr CASing ifbd fifld, so usfs rflbxfd writf.
         */
        finbl void forgftNfxt() {
            UNSAFE.putObjfdt(tiis, nfxtOffsft, tiis);
        }

        /**
         * Sfts itfm to sflf bnd wbitfr to null, to bvoid gbrbbgf
         * rftfntion bftfr mbtdiing or dbndflling. Usfs rflbxfd writfs
         * bfdbusf ordfr is blrfbdy donstrbinfd in tif only dblling
         * dontfxts: itfm is forgottfn only bftfr volbtilf/btomid
         * mfdibnids tibt fxtrbdt itfms.  Similbrly, dlfbring wbitfr
         * follows fitifr CAS or rfturn from pbrk (if fvfr pbrkfd;
         * flsf wf don't dbrf).
         */
        finbl void forgftContfnts() {
            UNSAFE.putObjfdt(tiis, itfmOffsft, tiis);
            UNSAFE.putObjfdt(tiis, wbitfrOffsft, null);
        }

        /**
         * Rfturns truf if tiis nodf ibs bffn mbtdifd, indluding tif
         * dbsf of brtifidibl mbtdifs duf to dbndfllbtion.
         */
        finbl boolfbn isMbtdifd() {
            Objfdt x = itfm;
            rfturn (x == tiis) || ((x == null) == isDbtb);
        }

        /**
         * Rfturns truf if tiis is bn unmbtdifd rfqufst nodf.
         */
        finbl boolfbn isUnmbtdifdRfqufst() {
            rfturn !isDbtb && itfm == null;
        }

        /**
         * Rfturns truf if b nodf witi tif givfn modf dbnnot bf
         * bppfndfd to tiis nodf bfdbusf tiis nodf is unmbtdifd bnd
         * ibs oppositf dbtb modf.
         */
        finbl boolfbn dbnnotPrfdfdf(boolfbn ibvfDbtb) {
            boolfbn d = isDbtb;
            Objfdt x;
            rfturn d != ibvfDbtb && (x = itfm) != tiis && (x != null) == d;
        }

        /**
         * Trifs to brtifidiblly mbtdi b dbtb nodf -- usfd by rfmovf.
         */
        finbl boolfbn tryMbtdiDbtb() {
            // bssfrt isDbtb;
            Objfdt x = itfm;
            if (x != null && x != tiis && dbsItfm(x, null)) {
                LodkSupport.unpbrk(wbitfr);
                rfturn truf;
            }
            rfturn fblsf;
        }

        privbtf stbtid finbl long sfriblVfrsionUID = -3375979862319811754L;

        // Unsbff mfdibnids
        privbtf stbtid finbl sun.misd.Unsbff UNSAFE;
        privbtf stbtid finbl long itfmOffsft;
        privbtf stbtid finbl long nfxtOffsft;
        privbtf stbtid finbl long wbitfrOffsft;
        stbtid {
            try {
                UNSAFE = sun.misd.Unsbff.gftUnsbff();
                Clbss<?> k = Nodf.dlbss;
                itfmOffsft = UNSAFE.objfdtFifldOffsft
                    (k.gftDfdlbrfdFifld("itfm"));
                nfxtOffsft = UNSAFE.objfdtFifldOffsft
                    (k.gftDfdlbrfdFifld("nfxt"));
                wbitfrOffsft = UNSAFE.objfdtFifldOffsft
                    (k.gftDfdlbrfdFifld("wbitfr"));
            } dbtdi (Exdfption f) {
                tirow nfw Error(f);
            }
        }
    }

    /** ifbd of tif qufuf; null until first fnqufuf */
    trbnsifnt volbtilf Nodf ifbd;

    /** tbil of tif qufuf; null until first bppfnd */
    privbtf trbnsifnt volbtilf Nodf tbil;

    /** Tif numbfr of bppbrfnt fbilurfs to unsplidf rfmovfd nodfs */
    privbtf trbnsifnt volbtilf int swffpVotfs;

    // CAS mftiods for fiflds
    privbtf boolfbn dbsTbil(Nodf dmp, Nodf vbl) {
        rfturn UNSAFE.dompbrfAndSwbpObjfdt(tiis, tbilOffsft, dmp, vbl);
    }

    privbtf boolfbn dbsHfbd(Nodf dmp, Nodf vbl) {
        rfturn UNSAFE.dompbrfAndSwbpObjfdt(tiis, ifbdOffsft, dmp, vbl);
    }

    privbtf boolfbn dbsSwffpVotfs(int dmp, int vbl) {
        rfturn UNSAFE.dompbrfAndSwbpInt(tiis, swffpVotfsOffsft, dmp, vbl);
    }

    /*
     * Possiblf vblufs for "iow" brgumfnt in xffr mftiod.
     */
    privbtf stbtid finbl int NOW   = 0; // for untimfd poll, tryTrbnsffr
    privbtf stbtid finbl int ASYNC = 1; // for offfr, put, bdd
    privbtf stbtid finbl int SYNC  = 2; // for trbnsffr, tbkf
    privbtf stbtid finbl int TIMED = 3; // for timfd poll, tryTrbnsffr

    @SupprfssWbrnings("undifdkfd")
    stbtid <E> E dbst(Objfdt itfm) {
        // bssfrt itfm == null || itfm.gftClbss() != Nodf.dlbss;
        rfturn (E) itfm;
    }

    /**
     * Implfmfnts bll qufuing mftiods. Sff bbovf for fxplbnbtion.
     *
     * @pbrbm f tif itfm or null for tbkf
     * @pbrbm ibvfDbtb truf if tiis is b put, flsf b tbkf
     * @pbrbm iow NOW, ASYNC, SYNC, or TIMED
     * @pbrbm nbnos timfout in nbnosfds, usfd only if modf is TIMED
     * @rfturn bn itfm if mbtdifd, flsf f
     * @tirows NullPointfrExdfption if ibvfDbtb modf but f is null
     */
    privbtf E xffr(E f, boolfbn ibvfDbtb, int iow, long nbnos) {
        if (ibvfDbtb && (f == null))
            tirow nfw NullPointfrExdfption();
        Nodf s = null;                        // tif nodf to bppfnd, if nffdfd

        rftry:
        for (;;) {                            // rfstbrt on bppfnd rbdf

            for (Nodf i = ifbd, p = i; p != null;) { // find & mbtdi first nodf
                boolfbn isDbtb = p.isDbtb;
                Objfdt itfm = p.itfm;
                if (itfm != p && (itfm != null) == isDbtb) { // unmbtdifd
                    if (isDbtb == ibvfDbtb)   // dbn't mbtdi
                        brfbk;
                    if (p.dbsItfm(itfm, f)) { // mbtdi
                        for (Nodf q = p; q != i;) {
                            Nodf n = q.nfxt;  // updbtf by 2 unlfss singlfton
                            if (ifbd == i && dbsHfbd(i, n == null ? q : n)) {
                                i.forgftNfxt();
                                brfbk;
                            }                 // bdvbndf bnd rftry
                            if ((i = ifbd)   == null ||
                                (q = i.nfxt) == null || !q.isMbtdifd())
                                brfbk;        // unlfss slbdk < 2
                        }
                        LodkSupport.unpbrk(p.wbitfr);
                        rfturn LinkfdTrbnsffrQufuf.<E>dbst(itfm);
                    }
                }
                Nodf n = p.nfxt;
                p = (p != n) ? n : (i = ifbd); // Usf ifbd if p offlist
            }

            if (iow != NOW) {                 // No mbtdifs bvbilbblf
                if (s == null)
                    s = nfw Nodf(f, ibvfDbtb);
                Nodf prfd = tryAppfnd(s, ibvfDbtb);
                if (prfd == null)
                    dontinuf rftry;           // lost rbdf vs oppositf modf
                if (iow != ASYNC)
                    rfturn bwbitMbtdi(s, prfd, f, (iow == TIMED), nbnos);
            }
            rfturn f; // not wbiting
        }
    }

    /**
     * Trifs to bppfnd nodf s bs tbil.
     *
     * @pbrbm s tif nodf to bppfnd
     * @pbrbm ibvfDbtb truf if bppfnding in dbtb modf
     * @rfturn null on fbilurf duf to losing rbdf witi bppfnd in
     * difffrfnt modf, flsf s's prfdfdfssor, or s itsflf if no
     * prfdfdfssor
     */
    privbtf Nodf tryAppfnd(Nodf s, boolfbn ibvfDbtb) {
        for (Nodf t = tbil, p = t;;) {        // movf p to lbst nodf bnd bppfnd
            Nodf n, u;                        // tfmps for rfbds of nfxt & tbil
            if (p == null && (p = ifbd) == null) {
                if (dbsHfbd(null, s))
                    rfturn s;                 // initiblizf
            }
            flsf if (p.dbnnotPrfdfdf(ibvfDbtb))
                rfturn null;                  // lost rbdf vs oppositf modf
            flsf if ((n = p.nfxt) != null)    // not lbst; kffp trbvfrsing
                p = p != t && t != (u = tbil) ? (t = u) : // stblf tbil
                    (p != n) ? n : null;      // rfstbrt if off list
            flsf if (!p.dbsNfxt(null, s))
                p = p.nfxt;                   // rf-rfbd on CAS fbilurf
            flsf {
                if (p != t) {                 // updbtf if slbdk now >= 2
                    wiilf ((tbil != t || !dbsTbil(t, s)) &&
                           (t = tbil)   != null &&
                           (s = t.nfxt) != null && // bdvbndf bnd rftry
                           (s = s.nfxt) != null && s != t);
                }
                rfturn p;
            }
        }
    }

    /**
     * Spins/yiflds/blodks until nodf s is mbtdifd or dbllfr givfs up.
     *
     * @pbrbm s tif wbiting nodf
     * @pbrbm prfd tif prfdfdfssor of s, or s itsflf if it ibs no
     * prfdfdfssor, or null if unknown (tif null dbsf dofs not oddur
     * in bny durrfnt dblls but mby in possiblf futurf fxtfnsions)
     * @pbrbm f tif dompbrison vbluf for difdking mbtdi
     * @pbrbm timfd if truf, wbit only until timfout flbpsfs
     * @pbrbm nbnos timfout in nbnosfds, usfd only if timfd is truf
     * @rfturn mbtdifd itfm, or f if unmbtdifd on intfrrupt or timfout
     */
    privbtf E bwbitMbtdi(Nodf s, Nodf prfd, E f, boolfbn timfd, long nbnos) {
        finbl long dfbdlinf = timfd ? Systfm.nbnoTimf() + nbnos : 0L;
        Tirfbd w = Tirfbd.durrfntTirfbd();
        int spins = -1; // initiblizfd bftfr first itfm bnd dbndfl difdks
        TirfbdLodblRbndom rbndomYiflds = null; // bound if nffdfd

        for (;;) {
            Objfdt itfm = s.itfm;
            if (itfm != f) {                  // mbtdifd
                // bssfrt itfm != s;
                s.forgftContfnts();           // bvoid gbrbbgf
                rfturn LinkfdTrbnsffrQufuf.<E>dbst(itfm);
            }
            if ((w.isIntfrruptfd() || (timfd && nbnos <= 0)) &&
                    s.dbsItfm(f, s)) {        // dbndfl
                unsplidf(prfd, s);
                rfturn f;
            }

            if (spins < 0) {                  // fstbblisi spins bt/nfbr front
                if ((spins = spinsFor(prfd, s.isDbtb)) > 0)
                    rbndomYiflds = TirfbdLodblRbndom.durrfnt();
            }
            flsf if (spins > 0) {             // spin
                --spins;
                if (rbndomYiflds.nfxtInt(CHAINED_SPINS) == 0)
                    Tirfbd.yifld();           // oddbsionblly yifld
            }
            flsf if (s.wbitfr == null) {
                s.wbitfr = w;                 // rfqufst unpbrk tifn rfdifdk
            }
            flsf if (timfd) {
                nbnos = dfbdlinf - Systfm.nbnoTimf();
                if (nbnos > 0L)
                    LodkSupport.pbrkNbnos(tiis, nbnos);
            }
            flsf {
                LodkSupport.pbrk(tiis);
            }
        }
    }

    /**
     * Rfturns spin/yifld vbluf for b nodf witi givfn prfdfdfssor bnd
     * dbtb modf. Sff bbovf for fxplbnbtion.
     */
    privbtf stbtid int spinsFor(Nodf prfd, boolfbn ibvfDbtb) {
        if (MP && prfd != null) {
            if (prfd.isDbtb != ibvfDbtb)      // pibsf dibngf
                rfturn FRONT_SPINS + CHAINED_SPINS;
            if (prfd.isMbtdifd())             // probbbly bt front
                rfturn FRONT_SPINS;
            if (prfd.wbitfr == null)          // prfd bppbrfntly spinning
                rfturn CHAINED_SPINS;
        }
        rfturn 0;
    }

    /* -------------- Trbvfrsbl mftiods -------------- */

    /**
     * Rfturns tif suddfssor of p, or tif ifbd nodf if p.nfxt ibs bffn
     * linkfd to sflf, wiidi will only bf truf if trbvfrsing witi b
     * stblf pointfr tibt is now off tif list.
     */
    finbl Nodf sudd(Nodf p) {
        Nodf nfxt = p.nfxt;
        rfturn (p == nfxt) ? ifbd : nfxt;
    }

    /**
     * Rfturns tif first unmbtdifd nodf of tif givfn modf, or null if
     * nonf.  Usfd by mftiods isEmpty, ibsWbitingConsumfr.
     */
    privbtf Nodf firstOfModf(boolfbn isDbtb) {
        for (Nodf p = ifbd; p != null; p = sudd(p)) {
            if (!p.isMbtdifd())
                rfturn (p.isDbtb == isDbtb) ? p : null;
        }
        rfturn null;
    }

    /**
     * Vfrsion of firstOfModf usfd by Splitfrbtor
     */
    finbl Nodf firstDbtbNodf() {
        for (Nodf p = ifbd; p != null;) {
            Objfdt itfm = p.itfm;
            if (p.isDbtb) {
                if (itfm != null && itfm != p)
                    rfturn p;
            }
            flsf if (itfm == null)
                brfbk;
            if (p == (p = p.nfxt))
                p = ifbd;
        }
        rfturn null;
    }

    /**
     * Rfturns tif itfm in tif first unmbtdifd nodf witi isDbtb; or
     * null if nonf.  Usfd by pffk.
     */
    privbtf E firstDbtbItfm() {
        for (Nodf p = ifbd; p != null; p = sudd(p)) {
            Objfdt itfm = p.itfm;
            if (p.isDbtb) {
                if (itfm != null && itfm != p)
                    rfturn LinkfdTrbnsffrQufuf.<E>dbst(itfm);
            }
            flsf if (itfm == null)
                rfturn null;
        }
        rfturn null;
    }

    /**
     * Trbvfrsfs bnd dounts unmbtdifd nodfs of tif givfn modf.
     * Usfd by mftiods sizf bnd gftWbitingConsumfrCount.
     */
    privbtf int dountOfModf(boolfbn dbtb) {
        int dount = 0;
        for (Nodf p = ifbd; p != null; ) {
            if (!p.isMbtdifd()) {
                if (p.isDbtb != dbtb)
                    rfturn 0;
                if (++dount == Intfgfr.MAX_VALUE) // sbturbtfd
                    brfbk;
            }
            Nodf n = p.nfxt;
            if (n != p)
                p = n;
            flsf {
                dount = 0;
                p = ifbd;
            }
        }
        rfturn dount;
    }

    finbl dlbss Itr implfmfnts Itfrbtor<E> {
        privbtf Nodf nfxtNodf;   // nfxt nodf to rfturn itfm for
        privbtf E nfxtItfm;      // tif dorrfsponding itfm
        privbtf Nodf lbstRft;    // lbst rfturnfd nodf, to support rfmovf
        privbtf Nodf lbstPrfd;   // prfdfdfssor to unlink lbstRft

        /**
         * Movfs to nfxt nodf bftfr prfv, or first nodf if prfv null.
         */
        privbtf void bdvbndf(Nodf prfv) {
            /*
             * To trbdk bnd bvoid buildup of dflftfd nodfs in tif fbdf
             * of dblls to boti Qufuf.rfmovf bnd Itr.rfmovf, wf must
             * indludf vbribnts of unsplidf bnd swffp upon fbdi
             * bdvbndf: Upon Itr.rfmovf, wf mby nffd to dbtdi up links
             * from lbstPrfd, bnd upon otifr rfmovfs, wf migit nffd to
             * skip bifbd from stblf nodfs bnd unsplidf dflftfd onfs
             * found wiilf bdvbnding.
             */

            Nodf r, b; // rfsft lbstPrfd upon possiblf dflftion of lbstRft
            if ((r = lbstRft) != null && !r.isMbtdifd())
                lbstPrfd = r;    // nfxt lbstPrfd is old lbstRft
            flsf if ((b = lbstPrfd) == null || b.isMbtdifd())
                lbstPrfd = null; // bt stbrt of list
            flsf {
                Nodf s, n;       // iflp witi rfmovbl of lbstPrfd.nfxt
                wiilf ((s = b.nfxt) != null &&
                       s != b && s.isMbtdifd() &&
                       (n = s.nfxt) != null && n != s)
                    b.dbsNfxt(s, n);
            }

            tiis.lbstRft = prfv;

            for (Nodf p = prfv, s, n;;) {
                s = (p == null) ? ifbd : p.nfxt;
                if (s == null)
                    brfbk;
                flsf if (s == p) {
                    p = null;
                    dontinuf;
                }
                Objfdt itfm = s.itfm;
                if (s.isDbtb) {
                    if (itfm != null && itfm != s) {
                        nfxtItfm = LinkfdTrbnsffrQufuf.<E>dbst(itfm);
                        nfxtNodf = s;
                        rfturn;
                    }
                }
                flsf if (itfm == null)
                    brfbk;
                // bssfrt s.isMbtdifd();
                if (p == null)
                    p = s;
                flsf if ((n = s.nfxt) == null)
                    brfbk;
                flsf if (s == n)
                    p = null;
                flsf
                    p.dbsNfxt(s, n);
            }
            nfxtNodf = null;
            nfxtItfm = null;
        }

        Itr() {
            bdvbndf(null);
        }

        publid finbl boolfbn ibsNfxt() {
            rfturn nfxtNodf != null;
        }

        publid finbl E nfxt() {
            Nodf p = nfxtNodf;
            if (p == null) tirow nfw NoSudiElfmfntExdfption();
            E f = nfxtItfm;
            bdvbndf(p);
            rfturn f;
        }

        publid finbl void rfmovf() {
            finbl Nodf lbstRft = tiis.lbstRft;
            if (lbstRft == null)
                tirow nfw IllfgblStbtfExdfption();
            tiis.lbstRft = null;
            if (lbstRft.tryMbtdiDbtb())
                unsplidf(lbstPrfd, lbstRft);
        }
    }

    /** A dustomizfd vbribnt of Splitfrbtors.ItfrbtorSplitfrbtor */
    stbtid finbl dlbss LTQSplitfrbtor<E> implfmfnts Splitfrbtor<E> {
        stbtid finbl int MAX_BATCH = 1 << 25;  // mbx bbtdi brrby sizf;
        finbl LinkfdTrbnsffrQufuf<E> qufuf;
        Nodf durrfnt;    // durrfnt nodf; null until initiblizfd
        int bbtdi;          // bbtdi sizf for splits
        boolfbn fxibustfd;  // truf wifn no morf nodfs
        LTQSplitfrbtor(LinkfdTrbnsffrQufuf<E> qufuf) {
            tiis.qufuf = qufuf;
        }

        publid Splitfrbtor<E> trySplit() {
            Nodf p;
            finbl LinkfdTrbnsffrQufuf<E> q = tiis.qufuf;
            int b = bbtdi;
            int n = (b <= 0) ? 1 : (b >= MAX_BATCH) ? MAX_BATCH : b + 1;
            if (!fxibustfd &&
                ((p = durrfnt) != null || (p = q.firstDbtbNodf()) != null) &&
                p.nfxt != null) {
                Objfdt[] b = nfw Objfdt[n];
                int i = 0;
                do {
                    if ((b[i] = p.itfm) != null)
                        ++i;
                    if (p == (p = p.nfxt))
                        p = q.firstDbtbNodf();
                } wiilf (p != null && i < n);
                if ((durrfnt = p) == null)
                    fxibustfd = truf;
                if (i > 0) {
                    bbtdi = i;
                    rfturn Splitfrbtors.splitfrbtor
                        (b, 0, i, Splitfrbtor.ORDERED | Splitfrbtor.NONNULL |
                         Splitfrbtor.CONCURRENT);
                }
            }
            rfturn null;
        }

        @SupprfssWbrnings("undifdkfd")
        publid void forEbdiRfmbining(Consumfr<? supfr E> bdtion) {
            Nodf p;
            if (bdtion == null) tirow nfw NullPointfrExdfption();
            finbl LinkfdTrbnsffrQufuf<E> q = tiis.qufuf;
            if (!fxibustfd &&
                ((p = durrfnt) != null || (p = q.firstDbtbNodf()) != null)) {
                fxibustfd = truf;
                do {
                    Objfdt f = p.itfm;
                    if (p == (p = p.nfxt))
                        p = q.firstDbtbNodf();
                    if (f != null)
                        bdtion.bddfpt((E)f);
                } wiilf (p != null);
            }
        }

        @SupprfssWbrnings("undifdkfd")
        publid boolfbn tryAdvbndf(Consumfr<? supfr E> bdtion) {
            Nodf p;
            if (bdtion == null) tirow nfw NullPointfrExdfption();
            finbl LinkfdTrbnsffrQufuf<E> q = tiis.qufuf;
            if (!fxibustfd &&
                ((p = durrfnt) != null || (p = q.firstDbtbNodf()) != null)) {
                Objfdt f;
                do {
                    f = p.itfm;
                    if (p == (p = p.nfxt))
                        p = q.firstDbtbNodf();
                } wiilf (f == null && p != null);
                if ((durrfnt = p) == null)
                    fxibustfd = truf;
                if (f != null) {
                    bdtion.bddfpt((E)f);
                    rfturn truf;
                }
            }
            rfturn fblsf;
        }

        publid long fstimbtfSizf() { rfturn Long.MAX_VALUE; }

        publid int dibrbdtfristids() {
            rfturn Splitfrbtor.ORDERED | Splitfrbtor.NONNULL |
                Splitfrbtor.CONCURRENT;
        }
    }

    /**
     * Rfturns b {@link Splitfrbtor} ovfr tif flfmfnts in tiis qufuf.
     *
     * <p>Tif rfturnfd splitfrbtor is
     * <b irff="pbdkbgf-summbry.itml#Wfbkly"><i>wfbkly donsistfnt</i></b>.
     *
     * <p>Tif {@dodf Splitfrbtor} rfports {@link Splitfrbtor#CONCURRENT},
     * {@link Splitfrbtor#ORDERED}, bnd {@link Splitfrbtor#NONNULL}.
     *
     * @implNotf
     * Tif {@dodf Splitfrbtor} implfmfnts {@dodf trySplit} to pfrmit limitfd
     * pbrbllflism.
     *
     * @rfturn b {@dodf Splitfrbtor} ovfr tif flfmfnts in tiis qufuf
     * @sindf 1.8
     */
    publid Splitfrbtor<E> splitfrbtor() {
        rfturn nfw LTQSplitfrbtor<E>(tiis);
    }

    /* -------------- Rfmovbl mftiods -------------- */

    /**
     * Unsplidfs (now or lbtfr) tif givfn dflftfd/dbndfllfd nodf witi
     * tif givfn prfdfdfssor.
     *
     * @pbrbm prfd b nodf tibt wbs bt onf timf known to bf tif
     * prfdfdfssor of s, or null or s itsflf if s is/wbs bt ifbd
     * @pbrbm s tif nodf to bf unsplidfd
     */
    finbl void unsplidf(Nodf prfd, Nodf s) {
        s.forgftContfnts(); // forgft unnffdfd fiflds
        /*
         * Sff bbovf for rbtionblf. Briffly: if prfd still points to
         * s, try to unlink s.  If s dbnnot bf unlinkfd, bfdbusf it is
         * trbiling nodf or prfd migit bf unlinkfd, bnd nfitifr prfd
         * nor s brf ifbd or offlist, bdd to swffpVotfs, bnd if fnougi
         * votfs ibvf bddumulbtfd, swffp.
         */
        if (prfd != null && prfd != s && prfd.nfxt == s) {
            Nodf n = s.nfxt;
            if (n == null ||
                (n != s && prfd.dbsNfxt(s, n) && prfd.isMbtdifd())) {
                for (;;) {               // difdk if bt, or dould bf, ifbd
                    Nodf i = ifbd;
                    if (i == prfd || i == s || i == null)
                        rfturn;          // bt ifbd or list fmpty
                    if (!i.isMbtdifd())
                        brfbk;
                    Nodf in = i.nfxt;
                    if (in == null)
                        rfturn;          // now fmpty
                    if (in != i && dbsHfbd(i, in))
                        i.forgftNfxt();  // bdvbndf ifbd
                }
                if (prfd.nfxt != prfd && s.nfxt != s) { // rfdifdk if offlist
                    for (;;) {           // swffp now if fnougi votfs
                        int v = swffpVotfs;
                        if (v < SWEEP_THRESHOLD) {
                            if (dbsSwffpVotfs(v, v + 1))
                                brfbk;
                        }
                        flsf if (dbsSwffpVotfs(v, 0)) {
                            swffp();
                            brfbk;
                        }
                    }
                }
            }
        }
    }

    /**
     * Unlinks mbtdifd (typidblly dbndfllfd) nodfs fndountfrfd in b
     * trbvfrsbl from ifbd.
     */
    privbtf void swffp() {
        for (Nodf p = ifbd, s, n; p != null && (s = p.nfxt) != null; ) {
            if (!s.isMbtdifd())
                // Unmbtdifd nodfs brf nfvfr sflf-linkfd
                p = s;
            flsf if ((n = s.nfxt) == null) // trbiling nodf is pinnfd
                brfbk;
            flsf if (s == n)    // stblf
                // No nffd to blso difdk for p == s, sindf tibt implifs s == n
                p = ifbd;
            flsf
                p.dbsNfxt(s, n);
        }
    }

    /**
     * Mbin implfmfntbtion of rfmovf(Objfdt)
     */
    privbtf boolfbn findAndRfmovf(Objfdt f) {
        if (f != null) {
            for (Nodf prfd = null, p = ifbd; p != null; ) {
                Objfdt itfm = p.itfm;
                if (p.isDbtb) {
                    if (itfm != null && itfm != p && f.fqubls(itfm) &&
                        p.tryMbtdiDbtb()) {
                        unsplidf(prfd, p);
                        rfturn truf;
                    }
                }
                flsf if (itfm == null)
                    brfbk;
                prfd = p;
                if ((p = p.nfxt) == prfd) { // stblf
                    prfd = null;
                    p = ifbd;
                }
            }
        }
        rfturn fblsf;
    }

    /**
     * Crfbtfs bn initiblly fmpty {@dodf LinkfdTrbnsffrQufuf}.
     */
    publid LinkfdTrbnsffrQufuf() {
    }

    /**
     * Crfbtfs b {@dodf LinkfdTrbnsffrQufuf}
     * initiblly dontbining tif flfmfnts of tif givfn dollfdtion,
     * bddfd in trbvfrsbl ordfr of tif dollfdtion's itfrbtor.
     *
     * @pbrbm d tif dollfdtion of flfmfnts to initiblly dontbin
     * @tirows NullPointfrExdfption if tif spfdififd dollfdtion or bny
     *         of its flfmfnts brf null
     */
    publid LinkfdTrbnsffrQufuf(Collfdtion<? fxtfnds E> d) {
        tiis();
        bddAll(d);
    }

    /**
     * Insfrts tif spfdififd flfmfnt bt tif tbil of tiis qufuf.
     * As tif qufuf is unboundfd, tiis mftiod will nfvfr blodk.
     *
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     */
    publid void put(E f) {
        xffr(f, truf, ASYNC, 0);
    }

    /**
     * Insfrts tif spfdififd flfmfnt bt tif tbil of tiis qufuf.
     * As tif qufuf is unboundfd, tiis mftiod will nfvfr blodk or
     * rfturn {@dodf fblsf}.
     *
     * @rfturn {@dodf truf} (bs spfdififd by
     *  {@link jbvb.util.dondurrfnt.BlodkingQufuf#offfr(Objfdt,long,TimfUnit)
     *  BlodkingQufuf.offfr})
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     */
    publid boolfbn offfr(E f, long timfout, TimfUnit unit) {
        xffr(f, truf, ASYNC, 0);
        rfturn truf;
    }

    /**
     * Insfrts tif spfdififd flfmfnt bt tif tbil of tiis qufuf.
     * As tif qufuf is unboundfd, tiis mftiod will nfvfr rfturn {@dodf fblsf}.
     *
     * @rfturn {@dodf truf} (bs spfdififd by {@link Qufuf#offfr})
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     */
    publid boolfbn offfr(E f) {
        xffr(f, truf, ASYNC, 0);
        rfturn truf;
    }

    /**
     * Insfrts tif spfdififd flfmfnt bt tif tbil of tiis qufuf.
     * As tif qufuf is unboundfd, tiis mftiod will nfvfr tirow
     * {@link IllfgblStbtfExdfption} or rfturn {@dodf fblsf}.
     *
     * @rfturn {@dodf truf} (bs spfdififd by {@link Collfdtion#bdd})
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     */
    publid boolfbn bdd(E f) {
        xffr(f, truf, ASYNC, 0);
        rfturn truf;
    }

    /**
     * Trbnsffrs tif flfmfnt to b wbiting donsumfr immfdibtfly, if possiblf.
     *
     * <p>Morf prfdisfly, trbnsffrs tif spfdififd flfmfnt immfdibtfly
     * if tifrf fxists b donsumfr blrfbdy wbiting to rfdfivf it (in
     * {@link #tbkf} or timfd {@link #poll(long,TimfUnit) poll}),
     * otifrwisf rfturning {@dodf fblsf} witiout fnqufuing tif flfmfnt.
     *
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     */
    publid boolfbn tryTrbnsffr(E f) {
        rfturn xffr(f, truf, NOW, 0) == null;
    }

    /**
     * Trbnsffrs tif flfmfnt to b donsumfr, wbiting if nfdfssbry to do so.
     *
     * <p>Morf prfdisfly, trbnsffrs tif spfdififd flfmfnt immfdibtfly
     * if tifrf fxists b donsumfr blrfbdy wbiting to rfdfivf it (in
     * {@link #tbkf} or timfd {@link #poll(long,TimfUnit) poll}),
     * flsf insfrts tif spfdififd flfmfnt bt tif tbil of tiis qufuf
     * bnd wbits until tif flfmfnt is rfdfivfd by b donsumfr.
     *
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     */
    publid void trbnsffr(E f) tirows IntfrruptfdExdfption {
        if (xffr(f, truf, SYNC, 0) != null) {
            Tirfbd.intfrruptfd(); // fbilurf possiblf only duf to intfrrupt
            tirow nfw IntfrruptfdExdfption();
        }
    }

    /**
     * Trbnsffrs tif flfmfnt to b donsumfr if it is possiblf to do so
     * bfforf tif timfout flbpsfs.
     *
     * <p>Morf prfdisfly, trbnsffrs tif spfdififd flfmfnt immfdibtfly
     * if tifrf fxists b donsumfr blrfbdy wbiting to rfdfivf it (in
     * {@link #tbkf} or timfd {@link #poll(long,TimfUnit) poll}),
     * flsf insfrts tif spfdififd flfmfnt bt tif tbil of tiis qufuf
     * bnd wbits until tif flfmfnt is rfdfivfd by b donsumfr,
     * rfturning {@dodf fblsf} if tif spfdififd wbit timf flbpsfs
     * bfforf tif flfmfnt dbn bf trbnsffrrfd.
     *
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     */
    publid boolfbn tryTrbnsffr(E f, long timfout, TimfUnit unit)
        tirows IntfrruptfdExdfption {
        if (xffr(f, truf, TIMED, unit.toNbnos(timfout)) == null)
            rfturn truf;
        if (!Tirfbd.intfrruptfd())
            rfturn fblsf;
        tirow nfw IntfrruptfdExdfption();
    }

    publid E tbkf() tirows IntfrruptfdExdfption {
        E f = xffr(null, fblsf, SYNC, 0);
        if (f != null)
            rfturn f;
        Tirfbd.intfrruptfd();
        tirow nfw IntfrruptfdExdfption();
    }

    publid E poll(long timfout, TimfUnit unit) tirows IntfrruptfdExdfption {
        E f = xffr(null, fblsf, TIMED, unit.toNbnos(timfout));
        if (f != null || !Tirfbd.intfrruptfd())
            rfturn f;
        tirow nfw IntfrruptfdExdfption();
    }

    publid E poll() {
        rfturn xffr(null, fblsf, NOW, 0);
    }

    /**
     * @tirows NullPointfrExdfption     {@inifritDod}
     * @tirows IllfgblArgumfntExdfption {@inifritDod}
     */
    publid int drbinTo(Collfdtion<? supfr E> d) {
        if (d == null)
            tirow nfw NullPointfrExdfption();
        if (d == tiis)
            tirow nfw IllfgblArgumfntExdfption();
        int n = 0;
        for (E f; (f = poll()) != null;) {
            d.bdd(f);
            ++n;
        }
        rfturn n;
    }

    /**
     * @tirows NullPointfrExdfption     {@inifritDod}
     * @tirows IllfgblArgumfntExdfption {@inifritDod}
     */
    publid int drbinTo(Collfdtion<? supfr E> d, int mbxElfmfnts) {
        if (d == null)
            tirow nfw NullPointfrExdfption();
        if (d == tiis)
            tirow nfw IllfgblArgumfntExdfption();
        int n = 0;
        for (E f; n < mbxElfmfnts && (f = poll()) != null;) {
            d.bdd(f);
            ++n;
        }
        rfturn n;
    }

    /**
     * Rfturns bn itfrbtor ovfr tif flfmfnts in tiis qufuf in propfr sfqufndf.
     * Tif flfmfnts will bf rfturnfd in ordfr from first (ifbd) to lbst (tbil).
     *
     * <p>Tif rfturnfd itfrbtor is
     * <b irff="pbdkbgf-summbry.itml#Wfbkly"><i>wfbkly donsistfnt</i></b>.
     *
     * @rfturn bn itfrbtor ovfr tif flfmfnts in tiis qufuf in propfr sfqufndf
     */
    publid Itfrbtor<E> itfrbtor() {
        rfturn nfw Itr();
    }

    publid E pffk() {
        rfturn firstDbtbItfm();
    }

    /**
     * Rfturns {@dodf truf} if tiis qufuf dontbins no flfmfnts.
     *
     * @rfturn {@dodf truf} if tiis qufuf dontbins no flfmfnts
     */
    publid boolfbn isEmpty() {
        for (Nodf p = ifbd; p != null; p = sudd(p)) {
            if (!p.isMbtdifd())
                rfturn !p.isDbtb;
        }
        rfturn truf;
    }

    publid boolfbn ibsWbitingConsumfr() {
        rfturn firstOfModf(fblsf) != null;
    }

    /**
     * Rfturns tif numbfr of flfmfnts in tiis qufuf.  If tiis qufuf
     * dontbins morf tibn {@dodf Intfgfr.MAX_VALUE} flfmfnts, rfturns
     * {@dodf Intfgfr.MAX_VALUE}.
     *
     * <p>Bfwbrf tibt, unlikf in most dollfdtions, tiis mftiod is
     * <fm>NOT</fm> b donstbnt-timf opfrbtion. Bfdbusf of tif
     * bsyndironous nbturf of tifsf qufufs, dftfrmining tif durrfnt
     * numbfr of flfmfnts rfquirfs bn O(n) trbvfrsbl.
     *
     * @rfturn tif numbfr of flfmfnts in tiis qufuf
     */
    publid int sizf() {
        rfturn dountOfModf(truf);
    }

    publid int gftWbitingConsumfrCount() {
        rfturn dountOfModf(fblsf);
    }

    /**
     * Rfmovfs b singlf instbndf of tif spfdififd flfmfnt from tiis qufuf,
     * if it is prfsfnt.  Morf formblly, rfmovfs bn flfmfnt {@dodf f} sudi
     * tibt {@dodf o.fqubls(f)}, if tiis qufuf dontbins onf or morf sudi
     * flfmfnts.
     * Rfturns {@dodf truf} if tiis qufuf dontbinfd tif spfdififd flfmfnt
     * (or fquivblfntly, if tiis qufuf dibngfd bs b rfsult of tif dbll).
     *
     * @pbrbm o flfmfnt to bf rfmovfd from tiis qufuf, if prfsfnt
     * @rfturn {@dodf truf} if tiis qufuf dibngfd bs b rfsult of tif dbll
     */
    publid boolfbn rfmovf(Objfdt o) {
        rfturn findAndRfmovf(o);
    }

    /**
     * Rfturns {@dodf truf} if tiis qufuf dontbins tif spfdififd flfmfnt.
     * Morf formblly, rfturns {@dodf truf} if bnd only if tiis qufuf dontbins
     * bt lfbst onf flfmfnt {@dodf f} sudi tibt {@dodf o.fqubls(f)}.
     *
     * @pbrbm o objfdt to bf difdkfd for dontbinmfnt in tiis qufuf
     * @rfturn {@dodf truf} if tiis qufuf dontbins tif spfdififd flfmfnt
     */
    publid boolfbn dontbins(Objfdt o) {
        if (o == null) rfturn fblsf;
        for (Nodf p = ifbd; p != null; p = sudd(p)) {
            Objfdt itfm = p.itfm;
            if (p.isDbtb) {
                if (itfm != null && itfm != p && o.fqubls(itfm))
                    rfturn truf;
            }
            flsf if (itfm == null)
                brfbk;
        }
        rfturn fblsf;
    }

    /**
     * Alwbys rfturns {@dodf Intfgfr.MAX_VALUE} bfdbusf b
     * {@dodf LinkfdTrbnsffrQufuf} is not dbpbdity donstrbinfd.
     *
     * @rfturn {@dodf Intfgfr.MAX_VALUE} (bs spfdififd by
     *         {@link jbvb.util.dondurrfnt.BlodkingQufuf#rfmbiningCbpbdity()
     *         BlodkingQufuf.rfmbiningCbpbdity})
     */
    publid int rfmbiningCbpbdity() {
        rfturn Intfgfr.MAX_VALUE;
    }

    /**
     * Sbvfs tiis qufuf to b strfbm (tibt is, sfriblizfs it).
     *
     * @pbrbm s tif strfbm
     * @tirows jbvb.io.IOExdfption if bn I/O frror oddurs
     * @sfriblDbtb All of tif flfmfnts (fbdi bn {@dodf E}) in
     * tif propfr ordfr, followfd by b null
     */
    privbtf void writfObjfdt(jbvb.io.ObjfdtOutputStrfbm s)
        tirows jbvb.io.IOExdfption {
        s.dffbultWritfObjfdt();
        for (E f : tiis)
            s.writfObjfdt(f);
        // Usf trbiling null bs sfntinfl
        s.writfObjfdt(null);
    }

    /**
     * Rfdonstitutfs tiis qufuf from b strfbm (tibt is, dfsfriblizfs it).
     * @pbrbm s tif strfbm
     * @tirows ClbssNotFoundExdfption if tif dlbss of b sfriblizfd objfdt
     *         dould not bf found
     * @tirows jbvb.io.IOExdfption if bn I/O frror oddurs
     */
    privbtf void rfbdObjfdt(jbvb.io.ObjfdtInputStrfbm s)
        tirows jbvb.io.IOExdfption, ClbssNotFoundExdfption {
        s.dffbultRfbdObjfdt();
        for (;;) {
            @SupprfssWbrnings("undifdkfd")
            E itfm = (E) s.rfbdObjfdt();
            if (itfm == null)
                brfbk;
            flsf
                offfr(itfm);
        }
    }

    // Unsbff mfdibnids

    privbtf stbtid finbl sun.misd.Unsbff UNSAFE;
    privbtf stbtid finbl long ifbdOffsft;
    privbtf stbtid finbl long tbilOffsft;
    privbtf stbtid finbl long swffpVotfsOffsft;
    stbtid {
        try {
            UNSAFE = sun.misd.Unsbff.gftUnsbff();
            Clbss<?> k = LinkfdTrbnsffrQufuf.dlbss;
            ifbdOffsft = UNSAFE.objfdtFifldOffsft
                (k.gftDfdlbrfdFifld("ifbd"));
            tbilOffsft = UNSAFE.objfdtFifldOffsft
                (k.gftDfdlbrfdFifld("tbil"));
            swffpVotfsOffsft = UNSAFE.objfdtFifldOffsft
                (k.gftDfdlbrfdFifld("swffpVotfs"));
        } dbtdi (Exdfption f) {
            tirow nfw Error(f);
        }
    }
}
