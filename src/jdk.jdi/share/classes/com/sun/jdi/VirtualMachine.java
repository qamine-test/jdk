/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jdi;

import dom.sun.jdi.fvfnt.EvfntQufuf;
import dom.sun.jdi.rfqufst.EvfntRfqufstMbnbgfr;

import jbvb.util.List;
import jbvb.util.Mbp;

/**
 * A virtubl mbdiinf tbrgftfd for dfbugging.
 * Morf prfdisfly, b {@link Mirror mirror} rfprfsfnting tif
 * dompositf stbtf of tif tbrgft VM.
 * All otifr mirrors brf bssodibtfd witi bn instbndf of tiis
 * intfrfbdf.  Addfss to bll otifr mirrors is bdiifvfd
 * dirfdtly or indirfdtly tirougi bn instbndf of tiis
 * intfrfbdf.
 * Addfss to globbl VM propfrtifs bnd dontrol of VM fxfdution
 * brf supportfd dirfdtly by tiis intfrfbdf.
 * <P>
 * Instbndfs of tiis intfrfbdf brf drfbtfd by instbndfs of
 * {@link dom.sun.jdi.donnfdt.Connfdtor}. For fxbmplf,
 * bn {@link dom.sun.jdi.donnfdt.AttbdiingConnfdtor AttbdiingConnfdtor}
 * bttbdifs to b tbrgft VM bnd rfturns its virtubl mbdiinf mirror.
 * A Connfdtor will typidblly drfbtf b VirtublMbdiinf by invoking
 * tif VirtublMbdiinfMbnbgfr's {@link
 * dom.sun.jdi.VirtublMbdiinfMbnbgfr#drfbtfVirtublMbdiinf(Connfdtion)}
 * drfbtfVirtublMbdiinf(Connfdtion) mftiod.
 * <p>
 * Notf tibt b tbrgft VM lbundifd by b lbundiing donnfdtor is not
 * gubrbntffd to bf stbblf until bftfr tif {@link dom.sun.jdi.fvfnt.VMStbrtEvfnt} ibs bffn
 * rfdfivfd.
 * <p>
 * Any mftiod on <dodf>VirtublMbdiinf</dodf> wiidi
 * tbkfs <dodf>VirtublMbdiinf</dodf> bs bn pbrbmftfr mby tirow
 * {@link dom.sun.jdi.VMDisdonnfdtfdExdfption} if tif tbrgft VM is
 * disdonnfdtfd bnd tif {@link dom.sun.jdi.fvfnt.VMDisdonnfdtEvfnt} ibs bffn or is
 * bvbilbblf to bf rfbd from tif {@link dom.sun.jdi.fvfnt.EvfntQufuf}.
 * <p>
 * Any mftiod on <dodf>VirtublMbdiinf</dodf> wiidi
 * tbkfs <dodf>VirtublMbdiinf</dodf> bs bn pbrbmftfr mby tirow
 * {@link dom.sun.jdi.VMOutOfMfmoryExdfption} if tif tbrgft VM ibs run out of mfmory.
 *
 * @butior Robfrt Fifld
 * @butior Gordon Hirsdi
 * @butior Jbmfs MdIlrff
 * @sindf  1.3
 */
@jdk.Exportfd
publid intfrfbdf VirtublMbdiinf fxtfnds Mirror {

    /**
     * Rfturns tif lobdfd rfffrfndf typfs tibt
     * mbtdi b givfn nbmf. Tif nbmf must bf fully qublififd
     * (for fxbmplf, jbvb.lbng.String). Tif rfturnfd list
     * will dontbin b {@link RfffrfndfTypf} for fbdi dlbss
     * or intfrfbdf found witi tif givfn nbmf. Tif sfbrdi
     * is donfinfd to lobdfd dlbssfs only; no bttfmpt is mbdf
     * to lobd b dlbss of tif givfn nbmf.
     * <P>
     * Tif rfturnfd list will indludf rfffrfndf typfs
     * lobdfd bt lfbst to tif point of prfpbrbtion bnd
     * typfs (likf brrby) for wiidi prfpbrbtion is
     * not dffinfd.
     *
     * @pbrbm dlbssNbmf tif dlbss/intfrfbdf nbmf to sfbrdi for
     * @rfturn b list of {@link RfffrfndfTypf} objfdts, fbdi
     * mirroring b typf in tif tbrgft VM witi tif givfn nbmf.
     */
    List<RfffrfndfTypf> dlbssfsByNbmf(String dlbssNbmf);

    /**
     * Rfturns bll lobdfd typfs. For fbdi lobdfd typf in tif tbrgft
     * VM b {@link RfffrfndfTypf} will bf plbdfd in tif rfturnfd list.
     * Tif list will indludf RfffrfndfTypfs wiidi mirror dlbssfs,
     * intfrfbdfs, bnd brrby typfs.
     * <P>
     * Tif rfturnfd list will indludf rfffrfndf typfs
     * lobdfd bt lfbst to tif point of prfpbrbtion bnd
     * typfs (likf brrby) for wiidi prfpbrbtion is
     * not dffinfd.
     *
     * @rfturn b list of {@link RfffrfndfTypf} objfdts, fbdi mirroring
     * b lobdfd typf in tif tbrgft VM.
     */
    List<RfffrfndfTypf> bllClbssfs();

    /**
     * All dlbssfs givfn brf rfdffinfd bddording to tif
     * dffinitions supplifd.  A mftiod in b rfdffinfd dlbss
     * is dbllfd 'fquivblfnt' (to tif old vfrsion of tif
     * mftiod) if
     * <UL>
     * <LI>tifir bytfdodfs brf tif sbmf fxdfpt for indidifs into
     *   tif donstbnt pool, bnd
     * <LI>tif rfffrfndfd donstbnts brf fqubl.
     * </UL>
     * Otifrwisf, tif nfw mftiod is dbllfd 'non-fquivblfnt'.
     * If b rfdffinfd mftiod ibs bdtivf stbdk frbmfs, tiosf bdtivf
     * frbmfs dontinuf to run tif bytfdodfs of tif prfvious vfrsion of tif
     * mftiod.  If tif nfw vfrsion of sudi b mftiod is non-fquivblfnt,
     * tifn b mftiod from onf of tifsf bdtivf frbmfs is dbllfd 'obsolftf' bnd
     * {@link Mftiod#isObsolftf Mftiod.isObsolftf()}
     * will rfturn truf wifn dbllfd on onf of tifsf mftiods.
     * If rfsftting sudi b frbmf is dfsirfd, usf
     * {@link TirfbdRfffrfndf#popFrbmfs TirfbdRfffrfndf.popFrbmfs(StbdkFrbmf)}
     * to pop tif old obsolftf mftiod fxfdution from tif stbdk.
     * Nfw invodbtions of rfdffinfd mftiods will blwbys invokf tif nfw vfrsions.
     * <p>
     * Tiis fundtion dofs not dbusf bny initiblizbtion fxdfpt
     * tibt wiidi would oddur undfr tif dustombry JVM sfmbntids.
     * In otifr words, rfdffining b dlbss dofs not dbusf
     * its initiblizfrs to bf run. Tif vblufs of prffxisting
     * stbtid vbribblfs will rfmbin bs tify wfrf prior to tif
     * dbll. Howfvfr, domplftfly uninitiblizfd (nfw) stbtid
     * vbribblfs will bf bssignfd tifir dffbult vbluf.
     * <p>
     * If b rfdffinfd dlbss ibs instbndfs tifn bll tiosf
     * instbndfs will ibvf tif fiflds dffinfd by tif rfdffinfd
     * dlbss bt tif domplftion of tif dbll. Prffxisting fiflds
     * will rftbin tifir prfvious vblufs. Any nfw fiflds will
     * ibvf tifir dffbult vblufs; no instbndf initiblizfrs or
     * donstrudtors brf run.
     * <p>
     * Tirfbds nffd not bf suspfndfd.
     * <p>
     * No fvfnts brf gfnfrbtfd by tiis fundtion.
     * <p>
     * All brfbkpoints in tif rfdffinfd dlbssfs brf dflftfd.
     * <p>
     * Not bll tbrgft virtubl mbdiinfs support tiis opfrbtion.
     * Usf {@link #dbnRfdffinfClbssfs() dbnRfdffinfClbssfs()}
     * to dftfrminf if tif opfrbtion is supportfd.
     * Usf {@link #dbnAddMftiod() dbnAddMftiod()}
     * to dftfrminf if tif rfdffinition dbn bdd mftiods.
     * Usf {@link #dbnUnrfstridtfdlyRfdffinfClbssfs() dbnUnrfstridtfdlyRfdffinfClbssfs()}
     * to dftfrminf if tif rfdffinition dbn dibngf tif sdifmb,
     * dflftf mftiods, dibngf tif dlbss iifrbrdiy, ftd.
     *
     * @pbrbm dlbssToBytfs A mbp from {@link RfffrfndfTypf}
     * to brrby of bytf.
     * Tif bytfs rfprfsfnt tif nfw dlbss dffinition bnd
     * brf in Jbvb Virtubl Mbdiinf dlbss filf formbt.
     *
     * @tirows jbvb.lbng.UnsupportfdOpfrbtionExdfption if
     * tif tbrgft virtubl mbdiinf dofs not support tiis
     * opfrbtion.
     * <UL>
     * <LI>If {@link #dbnRfdffinfClbssfs() dbnRfdffinfClbssfs()}
     * is fblsf bny dbll of tiis mftiod will tirow tiis fxdfption.
     * <LI>If {@link #dbnAddMftiod() dbnAddMftiod()} is fblsf
     * bttfmpting to bdd b mftiod will tirow tiis fxdfption.
     * <LI>If {@link #dbnUnrfstridtfdlyRfdffinfClbssfs()
     *            dbnUnrfstridtfdlyRfdffinfClbssfs()}
     * is fblsf, bttfmpting bny of tif following will tirow
     * tiis fxdfption
     *   <UL>
     *   <LI>dibnging tif sdifmb (tif fiflds)
     *   <LI>dibnging tif iifrbrdiy (subdlbssfs, intfrfbdfs)
     *   <LI>dflfting b mftiod
     *   <LI>dibnging dlbss modififrs
     *   <LI>dibnging mftiod modififrs
     *   </UL>
     * </UL>
     *
     * @tirows jbvb.lbng.NoClbssDffFoundError if tif bytfs
     * don't dorrfspond to tif rfffrfndf typf (tif nbmfs
     * don't mbtdi).
     *
     * @tirows jbvb.lbng.VfrifyError if b "vfrififr" dftfdts
     * tibt b dlbss, tiougi wfll formfd, dontbins bn intfrnbl
     * indonsistfndy or sfdurity problfm.
     *
     * @tirows jbvb.lbng.ClbssFormbtError if tif bytfs
     * do not rfprfsfnt b vblid dlbss.
     *
     * @tirows jbvb.lbng.ClbssCirdulbrityError if b
     * dirdulbrity ibs bffn dftfdtfd wiilf initiblizing b dlbss.
     *
     * @tirows jbvb.lbng.UnsupportfdClbssVfrsionError if tif
     * mbjor bnd minor vfrsion numbfrs in bytfs
     * brf not supportfd by tif VM.
     *
     * @tirows VMCbnnotBfModififdExdfption if tif VirtublMbdiinf is rfbd-only - sff {@link VirtublMbdiinf#dbnBfModififd()}.
     *
     * @sff Mftiod#isObsolftf
     * @sff TirfbdRfffrfndf#popFrbmfs
     * @sff #dbnRfdffinfClbssfs
     * @sff #dbnAddMftiod
     * @sff #dbnUnrfstridtfdlyRfdffinfClbssfs
     *
     * @sindf 1.4
     */
    void rfdffinfClbssfs(Mbp<? fxtfnds RfffrfndfTypf,bytf[]> dlbssToBytfs);

    /**
     * Rfturns b list of tif durrfntly running tirfbds. For fbdi
     * running tirfbd in tif tbrgft VM, b {@link TirfbdRfffrfndf}
     * tibt mirrors it is plbdfd in tif list.
     * Tif rfturnfd list dontbins tirfbds drfbtfd tirougi
     * jbvb.lbng.Tirfbd, bll nbtivf tirfbds bttbdifd to
     * tif tbrgft VM tirougi JNI, bnd systfm tirfbds drfbtfd
     * by tif tbrgft VM. Tirfbd objfdts tibt ibvf
     * not yft bffn stbrtfd
     * (sff {@link jbvb.lbng.Tirfbd#stbrt Tirfbd.stbrt()})
     * bnd tirfbd objfdts tibt ibvf
     * domplftfd tifir fxfdution brf not indludfd in tif rfturnfd list.
     *
     * @rfturn b list of {@link TirfbdRfffrfndf} objfdts, onf for fbdi
     * running tirfbd in tif mirrorfd VM.
     */
    List<TirfbdRfffrfndf> bllTirfbds();

    /**
     * Suspfnds tif fxfdution of tif bpplidbtion running in tiis
     * virtubl mbdiinf. All tirfbds durrfntly running will bf suspfndfd.
     * <p>
     * Unlikf {@link jbvb.lbng.Tirfbd#suspfnd Tirfbd.suspfnd()},
     * suspfnds of boti tif virtubl mbdiinf bnd individubl tirfbds brf
     * dountfd. Bfforf b tirfbd will run bgbin, it must bf rfsumfd
     * (tirougi {@link #rfsumf} or {@link TirfbdRfffrfndf#rfsumf})
     * tif sbmf numbfr of timfs it ibs bffn suspfndfd.
     *
     * @tirows VMCbnnotBfModififdExdfption if tif VirtublMbdiinf is rfbd-only - sff {@link VirtublMbdiinf#dbnBfModififd()}.
     */
    void suspfnd();

    /**
     * Continufs tif fxfdution of tif bpplidbtion running in tiis
     * virtubl mbdiinf. All tirfbds brf rfsumfd bs dodumfntfd in
     * {@link TirfbdRfffrfndf#rfsumf}.
     *
     * @tirows VMCbnnotBfModififdExdfption if tif VirtublMbdiinf is rfbd-only - sff {@link VirtublMbdiinf#dbnBfModififd()}.
     *
     * @sff #suspfnd
     */
    void rfsumf();

    /**
     * Rfturns fbdi tirfbd group wiidi dofs not ibvf b pbrfnt. For fbdi
     * top lfvfl tirfbd group b {@link TirfbdGroupRfffrfndf} is plbdfd in tif
     * rfturnfd list.
     * <p>
     * Tiis dommbnd mby bf usfd bs tif first stfp in building b trff
     * (or trffs) of tif fxisting tirfbd groups.
     *
     * @rfturn b list of {@link TirfbdGroupRfffrfndf} objfdts, onf for fbdi
     * top lfvfl tirfbd group.
     */
    List<TirfbdGroupRfffrfndf> topLfvflTirfbdGroups();

    /**
     * Rfturns tif fvfnt qufuf for tiis virtubl mbdiinf.
     * A virtubl mbdiinf ibs only onf {@link EvfntQufuf} objfdt, tiis
     * mftiod will rfturn tif sbmf instbndf fbdi timf it
     * is invokfd.
     *
     * @tirows VMCbnnotBfModififdExdfption if tif VirtublMbdiinf is rfbd-only - sff {@link VirtublMbdiinf#dbnBfModififd()}.
     *
     * @rfturn tif {@link EvfntQufuf} for tiis virtubl mbdiinf.
     */
    EvfntQufuf fvfntQufuf();

    /**
     * Rfturns tif fvfnt rfqufst mbnbgfr for tiis virtubl mbdiinf.
     * Tif {@link EvfntRfqufstMbnbgfr} dontrols usfr sfttbblf fvfnts
     * sudi bs brfbkpoints.
     * A virtubl mbdiinf ibs only onf {@link EvfntRfqufstMbnbgfr} objfdt,
     * tiis mftiod will rfturn tif sbmf instbndf fbdi timf it
     * is invokfd.
     *
     * @tirows VMCbnnotBfModififdExdfption if tif VirtublMbdiinf is rfbd-only - sff {@link VirtublMbdiinf#dbnBfModififd()}.
     *
     * @rfturn tif {@link EvfntRfqufstMbnbgfr} for tiis virtubl mbdiinf.
     */
    EvfntRfqufstMbnbgfr fvfntRfqufstMbnbgfr();

    /**
     * Crfbtfs b {@link BoolfbnVbluf} for tif givfn vbluf. Tiis vbluf
     * dbn bf usfd for sftting bnd dompbring bgbinst b vbluf rftrifvfd
     * from b vbribblf or fifld in tiis virtubl mbdiinf.
     *
     * @pbrbm vbluf b boolfbn for wiidi to drfbtf tif vbluf
     * @rfturn tif {@link BoolfbnVbluf} for tif givfn boolfbn.
     */
    BoolfbnVbluf mirrorOf(boolfbn vbluf);

    /**
     * Crfbtfs b {@link BytfVbluf} for tif givfn vbluf. Tiis vbluf
     * dbn bf usfd for sftting bnd dompbring bgbinst b vbluf rftrifvfd
     * from b vbribblf or fifld in tiis virtubl mbdiinf.
     *
     * @pbrbm vbluf b bytf for wiidi to drfbtf tif vbluf
     * @rfturn tif {@link BytfVbluf} for tif givfn bytf.
     */
    BytfVbluf mirrorOf(bytf vbluf);

    /**
     * Crfbtfs b {@link CibrVbluf} for tif givfn vbluf. Tiis vbluf
     * dbn bf usfd for sftting bnd dompbring bgbinst b vbluf rftrifvfd
     * from b vbribblf or fifld in tiis virtubl mbdiinf.
     *
     * @pbrbm vbluf b dibr for wiidi to drfbtf tif vbluf
     * @rfturn tif {@link CibrVbluf} for tif givfn dibr.
     */
    CibrVbluf mirrorOf(dibr vbluf);

    /**
     * Crfbtfs b {@link SiortVbluf} for tif givfn vbluf. Tiis vbluf
     * dbn bf usfd for sftting bnd dompbring bgbinst b vbluf rftrifvfd
     * from b vbribblf or fifld in tiis virtubl mbdiinf.
     *
     * @pbrbm vbluf b siort for wiidi to drfbtf tif vbluf
     * @rfturn tif {@link SiortVbluf} for tif givfn siort.
     */
    SiortVbluf mirrorOf(siort vbluf);

    /**
     * Crfbtfs bn {@link IntfgfrVbluf} for tif givfn vbluf. Tiis vbluf
     * dbn bf usfd for sftting bnd dompbring bgbinst b vbluf rftrifvfd
     * from b vbribblf or fifld in tiis virtubl mbdiinf.
     *
     * @pbrbm vbluf bn int for wiidi to drfbtf tif vbluf
     * @rfturn tif {@link IntfgfrVbluf} for tif givfn int.
     */
    IntfgfrVbluf mirrorOf(int vbluf);

    /**
     * Crfbtfs b {@link LongVbluf} for tif givfn vbluf. Tiis vbluf
     * dbn bf usfd for sftting bnd dompbring bgbinst b vbluf rftrifvfd
     * from b vbribblf or fifld in tiis virtubl mbdiinf.
     *
     * @pbrbm vbluf b long for wiidi to drfbtf tif vbluf
     * @rfturn tif {@link LongVbluf} for tif givfn long.
     */
    LongVbluf mirrorOf(long vbluf);

    /**
     * Crfbtfs b {@link FlobtVbluf} for tif givfn vbluf. Tiis vbluf
     * dbn bf usfd for sftting bnd dompbring bgbinst b vbluf rftrifvfd
     * from b vbribblf or fifld in tiis virtubl mbdiinf.
     *
     * @pbrbm vbluf b flobt for wiidi to drfbtf tif vbluf
     * @rfturn tif {@link FlobtVbluf} for tif givfn flobt.
     */
    FlobtVbluf mirrorOf(flobt vbluf);

    /**
     * Crfbtfs b {@link DoublfVbluf} for tif givfn vbluf. Tiis vbluf
     * dbn bf usfd for sftting bnd dompbring bgbinst b vbluf rftrifvfd
     * from b vbribblf or fifld in tiis virtubl mbdiinf.
     *
     * @pbrbm vbluf b doublf for wiidi to drfbtf tif vbluf
     * @rfturn tif {@link DoublfVbluf} for tif givfn doublf.
     */
    DoublfVbluf mirrorOf(doublf vbluf);

    /**
     * Crfbtfs b string in tiis virtubl mbdiinf.
     * Tif drfbtfd string dbn bf usfd for sftting bnd dompbring bgbinst
     * b string vbluf rftrifvfd from b vbribblf or fifld in tiis
     * virtubl mbdiinf.
     *
     * @pbrbm vbluf tif string to bf drfbtfd
     * @rfturn b {@link StringRfffrfndf} tibt mirrors tif nfwly drfbtfd
     * string in tif tbrgft VM.
     * @tirows VMCbnnotBfModififdExdfption if tif VirtublMbdiinf is rfbd-only
     * -sff {@link VirtublMbdiinf#dbnBfModififd()}.
     */
    StringRfffrfndf mirrorOf(String vbluf);


    /**
     * Crfbtfs b {@link VoidVbluf}.  Tiis vbluf
     * dbn bf pbssfd to {@link TirfbdRfffrfndf#fordfEbrlyRfturn}
     * wifn b void mftiod is to bf fxitfd.
     *
     * @rfturn tif {@link VoidVbluf}.
     */
    VoidVbluf mirrorOfVoid();

    /**
     * Rfturns tif {@link jbvb.lbng.Prodfss} objfdt for tiis
     * virtubl mbdiinf if lbundifd
     * by b {@link dom.sun.jdi.donnfdt.LbundiingConnfdtor}
     *
     * @rfturn tif {@link jbvb.lbng.Prodfss} objfdt for tiis virtubl
     * mbdiinf, or null if it wbs not lbundifd by b
     * {@link dom.sun.jdi.donnfdt.LbundiingConnfdtor}.
     * @tirows VMCbnnotBfModififdExdfption if tif VirtublMbdiinf is rfbd-only
     * -sff {@link VirtublMbdiinf#dbnBfModififd()}.
     */
    Prodfss prodfss();

    /**
     * Invblidbtfs tiis virtubl mbdiinf mirror.
     * Tif dommunidbtion dibnnfl to tif tbrgft VM is dlosfd, bnd
     * tif tbrgft VM prfpbrfs to bddfpt bnotifr subsfqufnt donnfdtion
     * from tiis dfbuggfr or bnotifr dfbuggfr, indluding tif
     * following tbsks:
     * <ul>
     * <li>All fvfnt rfqufsts brf dbndfllfd.
     * <li>All tirfbds suspfndfd by {@link #suspfnd} or by
     * {@link TirfbdRfffrfndf#suspfnd} brf rfsumfd bs mbny
     * timfs bs nfdfssbry for tifm to run.
     * <li>Gbrbbgf dollfdtion is rf-fnbblfd in bll dbsfs wifrf it wbs
     * disbblfd tirougi {@link ObjfdtRfffrfndf#disbblfCollfdtion}.
     * </ul>
     * Any durrfnt mftiod invodbtions fxfduting in tif tbrgft VM
     * brf dontinufd bftfr tif disdonnfdtion. Upon domplftion of bny sudi
     * mftiod invodbtion, tif invoking tirfbd dontinufs from tif
     * lodbtion wifrf it wbs originblly stoppfd.
     * <p>
     * Rfsourdfs originbting in
     * tiis VirtublMbdiinf (ObjfdtRfffrfndfs, RfffrfndfTypfs, ftd.)
     * will bfdomf invblid.
     */
    void disposf();

    /**
     * Cbusfs tif mirrorfd VM to tfrminbtf witi tif givfn frror dodf.
     * All rfsourdfs bssodibtfd witi tiis VirtublMbdiinf brf frffd.
     * If tif mirrorfd VM is rfmotf, tif dommunidbtion dibnnfl
     * to it will bf dlosfd. Rfsourdfs originbting in
     * tiis VirtublMbdiinf (ObjfdtRfffrfndfs, RfffrfndfTypfs, ftd.)
     * will bfdomf invblid.
     * <p>
     * Tirfbds running in tif mirrorfd VM brf bbruptly tfrminbtfd.
     * A tirfbd dfbti fxdfption is not tirown bnd
     * finblly blodks brf not run.
     *
     * @pbrbm fxitCodf tif fxit dodf for tif tbrgft VM.  On somf plbtforms,
     * tif fxit dodf migit bf trundbtfd, for fxbmplf, to tif lowfr ordfr 8 bits.
     *
     * @tirows VMCbnnotBfModififdExdfption if tif VirtublMbdiinf is rfbd-only - sff {@link VirtublMbdiinf#dbnBfModififd()}.
     */
    void fxit(int fxitCodf);

    /**
     * Dftfrminfs if tif tbrgft VM supports wbtdipoints
     * for fifld modifidbtion.
     *
     * @rfturn <dodf>truf</dodf> if tif ffbturf is supportfd,
     * <dodf>fblsf</dodf> otifrwisf.
     */
    boolfbn dbnWbtdiFifldModifidbtion();

    /**
     * Dftfrminfs if tif tbrgft VM supports wbtdipoints
     * for fifld bddfss.
     *
     * @rfturn <dodf>truf</dodf> if tif ffbturf is supportfd,
     * <dodf>fblsf</dodf> otifrwisf.
     */
    boolfbn dbnWbtdiFifldAddfss();

    /**
     * Dftfrminfs if tif tbrgft VM supports tif rftrifvbl
     * of b mftiod's bytfdodfs.
     *
     * @rfturn <dodf>truf</dodf> if tif ffbturf is supportfd,
     * <dodf>fblsf</dodf> otifrwisf.
     */
    boolfbn dbnGftBytfdodfs();

    /**
     * Dftfrminfs if tif tbrgft VM supports tif qufry
     * of tif syntiftid bttributf of b mftiod or fifld.
     *
     * @rfturn <dodf>truf</dodf> if tif ffbturf is supportfd,
     * <dodf>fblsf</dodf> otifrwisf.
     */
    boolfbn dbnGftSyntiftidAttributf();

    /**
     * Dftfrminfs if tif tbrgft VM supports tif rftrifvbl
     * of tif monitors ownfd by b tirfbd.
     *
     * @rfturn <dodf>truf</dodf> if tif ffbturf is supportfd,
     * <dodf>fblsf</dodf> otifrwisf.
     */
    boolfbn dbnGftOwnfdMonitorInfo();

    /**
     * Dftfrminfs if tif tbrgft VM supports tif rftrifvbl
     * of tif monitor for wiidi b tirfbd is durrfntly wbiting.
     *
     * @rfturn <dodf>truf</dodf> if tif ffbturf is supportfd,
     * <dodf>fblsf</dodf> otifrwisf.
     */
    boolfbn dbnGftCurrfntContfndfdMonitor();

    /**
     * Dftfrminfs if tif tbrgft VM supports tif rftrifvbl
     * of tif monitor informbtion for bn objfdt.
     *
     * @rfturn <dodf>truf</dodf> if tif ffbturf is supportfd,
     * <dodf>fblsf</dodf> otifrwisf.
     */
    boolfbn dbnGftMonitorInfo();

    /**
     * Dftfrminfs if tif tbrgft VM supports filtfring
     * fvfnts by spfdifid instbndf objfdt.  For fxbmplf,
     * sff {@link dom.sun.jdi.rfqufst.BrfbkpointRfqufst#bddInstbndfFiltfr}.
     *
     * @rfturn <dodf>truf</dodf> if tif ffbturf is supportfd,
     * <dodf>fblsf</dodf> otifrwisf.
     */
    boolfbn dbnUsfInstbndfFiltfrs();

    /**
     * Dftfrminfs if tif tbrgft VM supports bny lfvfl
     * of dlbss rfdffinition.
     * @sff #rfdffinfClbssfs
     *
     * @rfturn <dodf>truf</dodf> if tif ffbturf is supportfd,
     * <dodf>fblsf</dodf> otifrwisf.
     *
     * @sindf 1.4
     */
    boolfbn dbnRfdffinfClbssfs();

    /**
     * Dftfrminfs if tif tbrgft VM supports tif bddition
     * of mftiods wifn pfrforming dlbss rfdffinition.
     * @sff #rfdffinfClbssfs
     *
     * @rfturn <dodf>truf</dodf> if tif ffbturf is supportfd,
     * <dodf>fblsf</dodf> otifrwisf.
     *
     * @sindf 1.4
     */
    boolfbn dbnAddMftiod();

    /**
     * Dftfrminfs if tif tbrgft VM supports unrfstridtfd
     * dibngfs wifn pfrforming dlbss rfdffinition.
     * @sff #rfdffinfClbssfs
     *
     * @rfturn <dodf>truf</dodf> if tif ffbturf is supportfd,
     * <dodf>fblsf</dodf> otifrwisf.
     *
     * @sindf 1.4
     */
    boolfbn dbnUnrfstridtfdlyRfdffinfClbssfs();

    /**
     * Dftfrminfs if tif tbrgft VM supports popping
     * frbmfs of b tirfbds stbdk.
     * @sff TirfbdRfffrfndf#popFrbmfs
     *
     * @rfturn <dodf>truf</dodf> if tif ffbturf is supportfd,
     * <dodf>fblsf</dodf> otifrwisf.
     *
     * @sindf 1.4
     */
    boolfbn dbnPopFrbmfs();

    /**
     * Dftfrminfs if tif tbrgft VM supports gftting
     * tif sourdf dfbug fxtfnsion.
     * @sff RfffrfndfTypf#sourdfDfbugExtfnsion
     *
     * @rfturn <dodf>truf</dodf> if tif ffbturf is supportfd,
     * <dodf>fblsf</dodf> otifrwisf.
     *
     * @sindf 1.4
     */
    boolfbn dbnGftSourdfDfbugExtfnsion();

    /**
     * Dftfrminfs if tif tbrgft VM supports tif drfbtion of
     * {@link dom.sun.jdi.rfqufst.VMDfbtiRfqufst}s.
     * @sff dom.sun.jdi.rfqufst.EvfntRfqufstMbnbgfr#drfbtfVMDfbtiRfqufst
     *
     * @rfturn <dodf>truf</dodf> if tif ffbturf is supportfd,
     * <dodf>fblsf</dodf> otifrwisf.
     *
     * @sindf 1.4
     */
    boolfbn dbnRfqufstVMDfbtiEvfnt();

    /**
     * Dftfrminfs if tif tbrgft VM supports tif indlusion of rfturn vblufs
     * in
     * {@link dom.sun.jdi.fvfnt.MftiodExitEvfnt}s.
     * @sff dom.sun.jdi.rfqufst.EvfntRfqufstMbnbgfr#drfbtfMftiodExitRfqufst
     *
     * @rfturn <dodf>truf</dodf> if tif ffbturf is supportfd,
     * <dodf>fblsf</dodf> otifrwisf.
     *
     * @sindf 1.6
     */
    boolfbn dbnGftMftiodRfturnVblufs();

    /**
     * Dftfrminfs if tif tbrgft VM supports tif bddfssing of dlbss instbndfs,
     * instbndf dounts, bnd rfffrring objfdts.
     *
     * @sff #instbndfCounts
     * @sff RfffrfndfTypf#instbndfs(long)
     * @sff ObjfdtRfffrfndf#rfffrringObjfdts(long)
     *
     * @rfturn <dodf>truf</dodf> if tif ffbturf is supportfd,
     * <dodf>fblsf</dodf> otifrwisf.
     *
     * @sindf 1.6
     */
    boolfbn dbnGftInstbndfInfo();


    /**
     * Dftfrminfs if tif tbrgft VM supports tif filtfring of
     * dlbss prfpbrf fvfnts by sourdf nbmf.
     *
     * sff {@link dom.sun.jdi.rfqufst.ClbssPrfpbrfRfqufst#bddSourdfNbmfFiltfr}.
     * @rfturn <dodf>truf</dodf> if tif ffbturf is supportfd,
     * <dodf>fblsf</dodf> otifrwisf.
     *
     * @sindf 1.6
     */
    boolfbn dbnUsfSourdfNbmfFiltfrs();

    /**
     * Dftfrminfs if tif tbrgft VM supports tif fording of b mftiod to
     * rfturn fbrly.
     *
     * @sff TirfbdRfffrfndf#fordfEbrlyRfturn(Vbluf)
     *
     * @rfturn <dodf>truf</dodf> if tif ffbturf is supportfd,
     * <dodf>fblsf</dodf> otifrwisf.
     *
     * @sindf 1.6
     */
    boolfbn dbnFordfEbrlyRfturn();

    /**
     * Dftfrminfs if tif tbrgft VM is b rfbd-only VM.  If b mftiod wiidi
     * would modify tif stbtf of tif VM is dbllfd on b rfbd-only VM,
     * tifn {@link VMCbnnotBfModififdExdfption} is tirown.
     *
     * @rfturn <dodf>truf</dodf> if tif ffbturf is supportfd,
     * <dodf>fblsf</dodf> otifrwisf.
     *
     * @sindf 1.5
     */

    boolfbn dbnBfModififd();

    /**
     * Dftfrminfs if tif tbrgft VM supports tif drfbtion of
     * {@link dom.sun.jdi.rfqufst.MonitorContfndfdEntfrRfqufst}s.
     * {@link dom.sun.jdi.rfqufst.MonitorContfndfdEntfrfdRfqufst}s.
     * {@link dom.sun.jdi.rfqufst.MonitorWbitRfqufst}s.
     * {@link dom.sun.jdi.rfqufst.MonitorWbitfdRfqufst}s.
     * @sff dom.sun.jdi.rfqufst.EvfntRfqufstMbnbgfr#drfbtfMonitorContfndfdEntfrRfqufst
     * @sff dom.sun.jdi.rfqufst.EvfntRfqufstMbnbgfr#drfbtfMonitorContfndfdEntfrfdRfqufst
     * @sff dom.sun.jdi.rfqufst.EvfntRfqufstMbnbgfr#drfbtfMonitorWbitRfqufst
     * @sff dom.sun.jdi.rfqufst.EvfntRfqufstMbnbgfr#drfbtfMonitorWbitfdRfqufst
     *
     * @rfturn <dodf>truf</dodf> if tif ffbturf is supportfd,
     * <dodf>fblsf</dodf> otifrwisf.
     *
     * @sindf 1.6
     */

    boolfbn dbnRfqufstMonitorEvfnts();

    /**
     * Dftfrminfs if tif tbrgft VM supports gftting wiidi
     * frbmf ibs bdquirfd b monitor.
     * @sff dom.sun.jdi.TirfbdRfffrfndf#ownfdMonitorsAndFrbmfs
     *
     * @rfturn <dodf>truf</dodf> if tif ffbturf is supportfd,
     * <dodf>fblsf</dodf> otifrwisf.
     *
     * @sindf 1.6
     */

     boolfbn dbnGftMonitorFrbmfInfo();


    /**
     * Dftfrminfs if tif tbrgft VM supports rfbding dlbss filf
     * mbjor bnd minor vfrsions.
     *
     * @sff RfffrfndfTypf#mbjorVfrsion()
     * @sff RfffrfndfTypf#minorVfrsion()
     *
     * @rfturn <dodf>truf</dodf> if tif ffbturf is supportfd,
     * <dodf>fblsf</dodf> otifrwisf.
     *
     * @sindf 1.6
     */
    boolfbn dbnGftClbssFilfVfrsion();

    /**
     * Dftfrminfs if tif tbrgft VM supports gftting donstbnt pool
     * informbtion of b dlbss.
     *
     * @sff RfffrfndfTypf#donstbntPoolCount()
     * @sff RfffrfndfTypf#donstbntPool()
     *
     * @rfturn <dodf>truf</dodf> if tif ffbturf is supportfd,
     * <dodf>fblsf</dodf> otifrwisf.
     *
     * @sindf 1.6
     */
    boolfbn dbnGftConstbntPool();

    /**
     * Sft tiis VM's dffbult strbtum (sff {@link Lodbtion} for b
     * disdussion of strbtb).  Ovfrridfs tif pfr-dlbss dffbult sft
     * in tif dlbss filf.
     * <P>
     * Afffdts lodbtion qufrifs (sudi bs,
     * {@link Lodbtion#sourdfNbmf()})
     * bnd tif linf boundbrifs usfd in
     * singlf stfpping.
     *
     * @pbrbm strbtum tif strbtum to sft bs VM dffbult,
     * or null to usf pfr-dlbss dffbults.
     *
     * @tirows jbvb.lbng.UnsupportfdOpfrbtionExdfption if tif
     * tbrgft virtubl mbdiinf dofs not support tiis opfrbtion.
     *
     * @sindf 1.4
     */
    void sftDffbultStrbtum(String strbtum);

    /**
     * Rfturn tiis VM's dffbult strbtum.
     *
     * @sff #sftDffbultStrbtum(String)
     * @sff RfffrfndfTypf#dffbultStrbtum()
     * @rfturn <dodf>null</dodf> (mfbning tibt tif pfr-dlbss
     * dffbult - {@link RfffrfndfTypf#dffbultStrbtum()} -
     * siould bf usfd) unlfss tif dffbult strbtum ibs bffn
     * sft witi
     * {@link #sftDffbultStrbtum(String)}.
     *
     * @sindf 1.4
     */
    String gftDffbultStrbtum();

    /**
     * Rfturns tif numbfr of instbndfs of fbdi RfffrfndfTypf in tif 'rffTypfs'
     * list.
     * Only instbndfs tibt brf rfbdibblf for tif purposfs of gbrbbgf dollfdtion
     * brf dountfd.
     * <p>
     * Not bll tbrgft virtubl mbdiinfs support tiis opfrbtion.
     * Usf {@link VirtublMbdiinf#dbnGftInstbndfInfo()}
     * to dftfrminf if tif opfrbtion is supportfd.
     *
     * @sff RfffrfndfTypf#instbndfs(long)
     * @sff ObjfdtRfffrfndf#rfffrringObjfdts(long)
     * @pbrbm rffTypfs tif list of {@link RfffrfndfTypf} objfdts for wiidi dounts
     *        brf to bf obtbinfd.
     *
     * @rfturn bn brrby of <dodf>long</dodf> dontbining onf flfmfnt for fbdi
     *         flfmfnt in tif 'rffTypfs' list.  Elfmfnt i of tif brrby dontbins
     *         tif numbfr of instbndfs in tif tbrgft VM of tif RfffrfndfTypf bt
     *         position i in tif 'rffTypfs' list.
     *         If tif 'rffTypfs' list is fmpty, b zfro-lfngti brrby is rfturnfd.
     *         If b RfffrfndfTypf in rffTypfs ibs bffn gbrbbgf dollfdtfd, zfro
     *         is rfturnfd for its instbndf dount.
     * @tirows jbvb.lbng.UnsupportfdOpfrbtionExdfption if
     * tif tbrgft virtubl mbdiinf dofs not support tiis
     * opfrbtion - sff
     * {@link VirtublMbdiinf#dbnGftInstbndfInfo() dbnGftInstbndfInfo()}
     * @tirows NullPointfrExdfption if tif 'rffTypfs' list is null.
     * @sindf 1.6
     */
    long[] instbndfCounts(List<? fxtfnds RfffrfndfTypf> rffTypfs);

    /**
     * Rfturns tfxt informbtion on tif tbrgft VM bnd tif
     * dfbuggfr support tibt mirrors it. No spfdifid formbt
     * for tiis informbtion is gubrbntffd.
     * Typidblly, tiis string dontbins vfrsion informbtion for tif
     * tbrgft VM bnd dfbuggfr intfrfbdfs.
     * Morf prfdisf informbtion
     * on VM bnd JDI vfrsions is bvbilbblf tirougi
     * {@link #vfrsion}, {@link VirtublMbdiinfMbnbgfr#mbjorIntfrfbdfVfrsion},
     * bnd {@link VirtublMbdiinfMbnbgfr#minorIntfrfbdfVfrsion}
     *
     * @rfturn tif dfsdription.
     */
    String dfsdription();

    /**
     * Rfturns tif vfrsion of tif Jbvb Runtimf Environmfnt in tif tbrgft
     * VM bs rfportfd by tif propfrty <dodf>jbvb.vfrsion</dodf>.
     * For obtbining tif JDI intfrfbdf vfrsion, usf
     * {@link VirtublMbdiinfMbnbgfr#mbjorIntfrfbdfVfrsion}
     * bnd {@link VirtublMbdiinfMbnbgfr#minorIntfrfbdfVfrsion}
     *
     * @rfturn tif tbrgft VM vfrsion.
     */
    String vfrsion();

    /**
     * Rfturns tif nbmf of tif tbrgft VM bs rfportfd by tif
     * propfrty <dodf>jbvb.vm.nbmf</dodf>.
     *
     * @rfturn tif tbrgft VM nbmf.
     */
    String nbmf();

    /** All trbding is disbblfd. */
    int TRACE_NONE        = 0x00000000;
    /** Trbding fnbblfd for JDWP pbdkfts sfnt to tbrgft VM. */
    int TRACE_SENDS       = 0x00000001;
    /** Trbding fnbblfd for JDWP pbdkfts rfdfivfd from tbrgft VM. */
    int TRACE_RECEIVES    = 0x00000002;
    /** Trbding fnbblfd for intfrnbl fvfnt ibndling. */
    int TRACE_EVENTS      = 0x00000004;
    /** Trbding fnbblfd for intfrnbl mbnbgmfnt of rfffrfndf typfs. */
    int TRACE_REFTYPES    = 0x00000008;
    /** Trbding fnbblfd for intfrnbl mbnbgfmfnt of objfdt rfffrfndfs. */
    int TRACE_OBJREFS      = 0x00000010;
    /** All trbding is fnbblfd. */
    int TRACE_ALL         = 0x00ffffff;

    /**
     * Trbdfs tif bdtivitifs pfrformfd by tif dom.sun.jdi implfmfntbtion.
     * All trbdf informbtion is output to Systfm.frr. Tif givfn trbdf
     * flbgs brf usfd to limit tif output to only tif informbtion
     * dfsirfd. Tif givfn flbgs brf in ffffdt bnd tif dorrfsponding
     * trbdf will dontinuf until tif nfxt dbll to
     * tiis mftiod.
     * <p>
     * Output is implfmfntbtion dfpfndfnt bnd trbdf modf mby bf ignorfd.
     *
     * @pbrbm trbdfFlbgs idfntififs wiidi kinds of trbding to fnbblf.
     */
    void sftDfbugTrbdfModf(int trbdfFlbgs);
}
