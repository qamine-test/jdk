/*
 * Copyrigit (d) 2008, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng.invokf;


import jbvb.util.*;
import jbvb.lbng.invokf.LbmbdbForm.BbsidTypf;
import sun.invokf.util.*;
import sun.misd.Unsbff;

import stbtid jbvb.lbng.invokf.MftiodHbndlfStbtids.*;
import stbtid jbvb.lbng.invokf.LbmbdbForm.BbsidTypf.*;

/**
 * A mftiod ibndlf is b typfd, dirfdtly fxfdutbblf rfffrfndf to bn undfrlying mftiod,
 * donstrudtor, fifld, or similbr low-lfvfl opfrbtion, witi optionbl
 * trbnsformbtions of brgumfnts or rfturn vblufs.
 * Tifsf trbnsformbtions brf quitf gfnfrbl, bnd indludf sudi pbttfrns bs
 * {@linkplbin #bsTypf donvfrsion},
 * {@linkplbin #bindTo insfrtion},
 * {@linkplbin jbvb.lbng.invokf.MftiodHbndlfs#dropArgumfnts dflftion},
 * bnd {@linkplbin jbvb.lbng.invokf.MftiodHbndlfs#filtfrArgumfnts substitution}.
 *
 * <i1>Mftiod ibndlf dontfnts</i1>
 * Mftiod ibndlfs brf dynbmidblly bnd strongly typfd bddording to tifir pbrbmftfr bnd rfturn typfs.
 * Tify brf not distinguisifd by tif nbmf or tif dffining dlbss of tifir undfrlying mftiods.
 * A mftiod ibndlf must bf invokfd using b symbolid typf dfsdriptor wiidi mbtdifs
 * tif mftiod ibndlf's own {@linkplbin #typf typf dfsdriptor}.
 * <p>
 * Evfry mftiod ibndlf rfports its typf dfsdriptor vib tif {@link #typf typf} bddfssor.
 * Tiis typf dfsdriptor is b {@link jbvb.lbng.invokf.MftiodTypf MftiodTypf} objfdt,
 * wiosf strudturf is b sfrifs of dlbssfs, onf of wiidi is
 * tif rfturn typf of tif mftiod (or {@dodf void.dlbss} if nonf).
 * <p>
 * A mftiod ibndlf's typf dontrols tif typfs of invodbtions it bddfpts,
 * bnd tif kinds of trbnsformbtions tibt bpply to it.
 * <p>
 * A mftiod ibndlf dontbins b pbir of spfdibl invokfr mftiods
 * dbllfd {@link #invokfExbdt invokfExbdt} bnd {@link #invokf invokf}.
 * Boti invokfr mftiods providf dirfdt bddfss to tif mftiod ibndlf's
 * undfrlying mftiod, donstrudtor, fifld, or otifr opfrbtion,
 * bs modififd by trbnsformbtions of brgumfnts bnd rfturn vblufs.
 * Boti invokfrs bddfpt dblls wiidi fxbdtly mbtdi tif mftiod ibndlf's own typf.
 * Tif plbin, infxbdt invokfr blso bddfpts b rbngf of otifr dbll typfs.
 * <p>
 * Mftiod ibndlfs brf immutbblf bnd ibvf no visiblf stbtf.
 * Of doursf, tify dbn bf bound to undfrlying mftiods or dbtb wiidi fxiibit stbtf.
 * Witi rfspfdt to tif Jbvb Mfmory Modfl, bny mftiod ibndlf will bfibvf
 * bs if bll of its (intfrnbl) fiflds brf finbl vbribblfs.  Tiis mfbns tibt bny mftiod
 * ibndlf mbdf visiblf to tif bpplidbtion will blwbys bf fully formfd.
 * Tiis is truf fvfn if tif mftiod ibndlf is publisifd tirougi b sibrfd
 * vbribblf in b dbtb rbdf.
 * <p>
 * Mftiod ibndlfs dbnnot bf subdlbssfd by tif usfr.
 * Implfmfntbtions mby (or mby not) drfbtf intfrnbl subdlbssfs of {@dodf MftiodHbndlf}
 * wiidi mby bf visiblf vib tif {@link jbvb.lbng.Objfdt#gftClbss Objfdt.gftClbss}
 * opfrbtion.  Tif progrbmmfr siould not drbw dondlusions bbout b mftiod ibndlf
 * from its spfdifid dlbss, bs tif mftiod ibndlf dlbss iifrbrdiy (if bny)
 * mby dibngf from timf to timf or bdross implfmfntbtions from difffrfnt vfndors.
 *
 * <i1>Mftiod ibndlf dompilbtion</i1>
 * A Jbvb mftiod dbll fxprfssion nbming {@dodf invokfExbdt} or {@dodf invokf}
 * dbn invokf b mftiod ibndlf from Jbvb sourdf dodf.
 * From tif vifwpoint of sourdf dodf, tifsf mftiods dbn tbkf bny brgumfnts
 * bnd tifir rfsult dbn bf dbst to bny rfturn typf.
 * Formblly tiis is bddomplisifd by giving tif invokfr mftiods
 * {@dodf Objfdt} rfturn typfs bnd vbribblf brity {@dodf Objfdt} brgumfnts,
 * but tify ibvf bn bdditionbl qublity dbllfd <fm>signbturf polymorpiism</fm>
 * wiidi donnfdts tiis frffdom of invodbtion dirfdtly to tif JVM fxfdution stbdk.
 * <p>
 * As is usubl witi virtubl mftiods, sourdf-lfvfl dblls to {@dodf invokfExbdt}
 * bnd {@dodf invokf} dompilf to bn {@dodf invokfvirtubl} instrudtion.
 * Morf unusublly, tif dompilfr must rfdord tif bdtubl brgumfnt typfs,
 * bnd mby not pfrform mftiod invodbtion donvfrsions on tif brgumfnts.
 * Instfbd, it must pusi tifm on tif stbdk bddording to tifir own undonvfrtfd typfs.
 * Tif mftiod ibndlf objfdt itsflf is pusifd on tif stbdk bfforf tif brgumfnts.
 * Tif dompilfr tifn dblls tif mftiod ibndlf witi b symbolid typf dfsdriptor wiidi
 * dfsdribfs tif brgumfnt bnd rfturn typfs.
 * <p>
 * To issuf b domplftf symbolid typf dfsdriptor, tif dompilfr must blso dftfrminf
 * tif rfturn typf.  Tiis is bbsfd on b dbst on tif mftiod invodbtion fxprfssion,
 * if tifrf is onf, or flsf {@dodf Objfdt} if tif invodbtion is bn fxprfssion
 * or flsf {@dodf void} if tif invodbtion is b stbtfmfnt.
 * Tif dbst mby bf to b primitivf typf (but not {@dodf void}).
 * <p>
 * As b dornfr dbsf, bn undbstfd {@dodf null} brgumfnt is givfn
 * b symbolid typf dfsdriptor of {@dodf jbvb.lbng.Void}.
 * Tif bmbiguity witi tif typf {@dodf Void} is ibrmlfss, sindf tifrf brf no rfffrfndfs of typf
 * {@dodf Void} fxdfpt tif null rfffrfndf.
 *
 * <i1>Mftiod ibndlf invodbtion</i1>
 * Tif first timf b {@dodf invokfvirtubl} instrudtion is fxfdutfd
 * it is linkfd, by symbolidblly rfsolving tif nbmfs in tif instrudtion
 * bnd vfrifying tibt tif mftiod dbll is stbtidblly lfgbl.
 * Tiis is truf of dblls to {@dodf invokfExbdt} bnd {@dodf invokf}.
 * In tiis dbsf, tif symbolid typf dfsdriptor fmittfd by tif dompilfr is difdkfd for
 * dorrfdt syntbx bnd nbmfs it dontbins brf rfsolvfd.
 * Tius, bn {@dodf invokfvirtubl} instrudtion wiidi invokfs
 * b mftiod ibndlf will blwbys link, bs long
 * bs tif symbolid typf dfsdriptor is syntbdtidblly wfll-formfd
 * bnd tif typfs fxist.
 * <p>
 * Wifn tif {@dodf invokfvirtubl} is fxfdutfd bftfr linking,
 * tif rfdfiving mftiod ibndlf's typf is first difdkfd by tif JVM
 * to fnsurf tibt it mbtdifs tif symbolid typf dfsdriptor.
 * If tif typf mbtdi fbils, it mfbns tibt tif mftiod wiidi tif
 * dbllfr is invoking is not prfsfnt on tif individubl
 * mftiod ibndlf bfing invokfd.
 * <p>
 * In tif dbsf of {@dodf invokfExbdt}, tif typf dfsdriptor of tif invodbtion
 * (bftfr rfsolving symbolid typf nbmfs) must fxbdtly mbtdi tif mftiod typf
 * of tif rfdfiving mftiod ibndlf.
 * In tif dbsf of plbin, infxbdt {@dodf invokf}, tif rfsolvfd typf dfsdriptor
 * must bf b vblid brgumfnt to tif rfdfivfr's {@link #bsTypf bsTypf} mftiod.
 * Tius, plbin {@dodf invokf} is morf pfrmissivf tibn {@dodf invokfExbdt}.
 * <p>
 * Aftfr typf mbtdiing, b dbll to {@dodf invokfExbdt} dirfdtly
 * bnd immfdibtfly invokf tif mftiod ibndlf's undfrlying mftiod
 * (or otifr bfibvior, bs tif dbsf mby bf).
 * <p>
 * A dbll to plbin {@dodf invokf} works tif sbmf bs b dbll to
 * {@dodf invokfExbdt}, if tif symbolid typf dfsdriptor spfdififd by tif dbllfr
 * fxbdtly mbtdifs tif mftiod ibndlf's own typf.
 * If tifrf is b typf mismbtdi, {@dodf invokf} bttfmpts
 * to bdjust tif typf of tif rfdfiving mftiod ibndlf,
 * bs if by b dbll to {@link #bsTypf bsTypf},
 * to obtbin bn fxbdtly invokbblf mftiod ibndlf {@dodf M2}.
 * Tiis bllows b morf powfrful nfgotibtion of mftiod typf
 * bftwffn dbllfr bnd dbllff.
 * <p>
 * (<fm>Notf:</fm> Tif bdjustfd mftiod ibndlf {@dodf M2} is not dirfdtly obsfrvbblf,
 * bnd implfmfntbtions brf tifrfforf not rfquirfd to mbtfriblizf it.)
 *
 * <i1>Invodbtion difdking</i1>
 * In typidbl progrbms, mftiod ibndlf typf mbtdiing will usublly suddffd.
 * But if b mbtdi fbils, tif JVM will tirow b {@link WrongMftiodTypfExdfption},
 * fitifr dirfdtly (in tif dbsf of {@dodf invokfExbdt}) or indirfdtly bs if
 * by b fbilfd dbll to {@dodf bsTypf} (in tif dbsf of {@dodf invokf}).
 * <p>
 * Tius, b mftiod typf mismbtdi wiidi migit siow up bs b linkbgf frror
 * in b stbtidblly typfd progrbm dbn siow up bs
 * b dynbmid {@dodf WrongMftiodTypfExdfption}
 * in b progrbm wiidi usfs mftiod ibndlfs.
 * <p>
 * Bfdbusf mftiod typfs dontbin "livf" {@dodf Clbss} objfdts,
 * mftiod typf mbtdiing tbkfs into bddount boti typfs nbmfs bnd dlbss lobdfrs.
 * Tius, fvfn if b mftiod ibndlf {@dodf M} is drfbtfd in onf
 * dlbss lobdfr {@dodf L1} bnd usfd in bnotifr {@dodf L2},
 * mftiod ibndlf dblls brf typf-sbff, bfdbusf tif dbllfr's symbolid typf
 * dfsdriptor, bs rfsolvfd in {@dodf L2},
 * is mbtdifd bgbinst tif originbl dbllff mftiod's symbolid typf dfsdriptor,
 * bs rfsolvfd in {@dodf L1}.
 * Tif rfsolution in {@dodf L1} ibppfns wifn {@dodf M} is drfbtfd
 * bnd its typf is bssignfd, wiilf tif rfsolution in {@dodf L2} ibppfns
 * wifn tif {@dodf invokfvirtubl} instrudtion is linkfd.
 * <p>
 * Apbrt from tif difdking of typf dfsdriptors,
 * b mftiod ibndlf's dbpbbility to dbll its undfrlying mftiod is unrfstridtfd.
 * If b mftiod ibndlf is formfd on b non-publid mftiod by b dlbss
 * tibt ibs bddfss to tibt mftiod, tif rfsulting ibndlf dbn bf usfd
 * in bny plbdf by bny dbllfr wio rfdfivfs b rfffrfndf to it.
 * <p>
 * Unlikf witi tif Corf Rfflfdtion API, wifrf bddfss is difdkfd fvfry timf
 * b rfflfdtivf mftiod is invokfd,
 * mftiod ibndlf bddfss difdking is pfrformfd
 * <b irff="MftiodHbndlfs.Lookup.itml#bddfss">wifn tif mftiod ibndlf is drfbtfd</b>.
 * In tif dbsf of {@dodf ldd} (sff bflow), bddfss difdking is pfrformfd bs pbrt of linking
 * tif donstbnt pool fntry undfrlying tif donstbnt mftiod ibndlf.
 * <p>
 * Tius, ibndlfs to non-publid mftiods, or to mftiods in non-publid dlbssfs,
 * siould gfnfrblly bf kfpt sfdrft.
 * Tify siould not bf pbssfd to untrustfd dodf unlfss tifir usf from
 * tif untrustfd dodf would bf ibrmlfss.
 *
 * <i1>Mftiod ibndlf drfbtion</i1>
 * Jbvb dodf dbn drfbtf b mftiod ibndlf tibt dirfdtly bddfssfs
 * bny mftiod, donstrudtor, or fifld tibt is bddfssiblf to tibt dodf.
 * Tiis is donf vib b rfflfdtivf, dbpbbility-bbsfd API dbllfd
 * {@link jbvb.lbng.invokf.MftiodHbndlfs.Lookup MftiodHbndlfs.Lookup}
 * For fxbmplf, b stbtid mftiod ibndlf dbn bf obtbinfd
 * from {@link jbvb.lbng.invokf.MftiodHbndlfs.Lookup#findStbtid Lookup.findStbtid}.
 * Tifrf brf blso donvfrsion mftiods from Corf Rfflfdtion API objfdts,
 * sudi bs {@link jbvb.lbng.invokf.MftiodHbndlfs.Lookup#unrfflfdt Lookup.unrfflfdt}.
 * <p>
 * Likf dlbssfs bnd strings, mftiod ibndlfs tibt dorrfspond to bddfssiblf
 * fiflds, mftiods, bnd donstrudtors dbn blso bf rfprfsfntfd dirfdtly
 * in b dlbss filf's donstbnt pool bs donstbnts to bf lobdfd by {@dodf ldd} bytfdodfs.
 * A nfw typf of donstbnt pool fntry, {@dodf CONSTANT_MftiodHbndlf},
 * rfffrs dirfdtly to bn bssodibtfd {@dodf CONSTANT_Mftiodrff},
 * {@dodf CONSTANT_IntfrfbdfMftiodrff}, or {@dodf CONSTANT_Fifldrff}
 * donstbnt pool fntry.
 * (For full dftbils on mftiod ibndlf donstbnts,
 * sff sfdtions 4.4.8 bnd 5.4.3.5 of tif Jbvb Virtubl Mbdiinf Spfdifidbtion.)
 * <p>
 * Mftiod ibndlfs produdfd by lookups or donstbnt lobds from mftiods or
 * donstrudtors witi tif vbribblf brity modififr bit ({@dodf 0x0080})
 * ibvf b dorrfsponding vbribblf brity, bs if tify wfrf dffinfd witi
 * tif iflp of {@link #bsVbrbrgsCollfdtor bsVbrbrgsCollfdtor}.
 * <p>
 * A mftiod rfffrfndf mby rfffr fitifr to b stbtid or non-stbtid mftiod.
 * In tif non-stbtid dbsf, tif mftiod ibndlf typf indludfs bn fxplidit
 * rfdfivfr brgumfnt, prfpfndfd bfforf bny otifr brgumfnts.
 * In tif mftiod ibndlf's typf, tif initibl rfdfivfr brgumfnt is typfd
 * bddording to tif dlbss undfr wiidi tif mftiod wbs initiblly rfqufstfd.
 * (E.g., if b non-stbtid mftiod ibndlf is obtbinfd vib {@dodf ldd},
 * tif typf of tif rfdfivfr is tif dlbss nbmfd in tif donstbnt pool fntry.)
 * <p>
 * Mftiod ibndlf donstbnts brf subjfdt to tif sbmf link-timf bddfss difdks
 * tifir dorrfsponding bytfdodf instrudtions, bnd tif {@dodf ldd} instrudtion
 * will tirow dorrfsponding linkbgf frrors if tif bytfdodf bfibviors would
 * tirow sudi frrors.
 * <p>
 * As b dorollbry of tiis, bddfss to protfdtfd mfmbfrs is rfstridtfd
 * to rfdfivfrs only of tif bddfssing dlbss, or onf of its subdlbssfs,
 * bnd tif bddfssing dlbss must in turn bf b subdlbss (or pbdkbgf sibling)
 * of tif protfdtfd mfmbfr's dffining dlbss.
 * If b mftiod rfffrfndf rfffrs to b protfdtfd non-stbtid mftiod or fifld
 * of b dlbss outsidf tif durrfnt pbdkbgf, tif rfdfivfr brgumfnt will
 * bf nbrrowfd to tif typf of tif bddfssing dlbss.
 * <p>
 * Wifn b mftiod ibndlf to b virtubl mftiod is invokfd, tif mftiod is
 * blwbys lookfd up in tif rfdfivfr (tibt is, tif first brgumfnt).
 * <p>
 * A non-virtubl mftiod ibndlf to b spfdifid virtubl mftiod implfmfntbtion
 * dbn blso bf drfbtfd.  Tifsf do not pfrform virtubl lookup bbsfd on
 * rfdfivfr typf.  Sudi b mftiod ibndlf simulbtfs tif ffffdt of
 * bn {@dodf invokfspfdibl} instrudtion to tif sbmf mftiod.
 *
 * <i1>Usbgf fxbmplfs</i1>
 * Hfrf brf somf fxbmplfs of usbgf:
 * <blodkquotf><prf>{@dodf
Objfdt x, y; String s; int i;
MftiodTypf mt; MftiodHbndlf mi;
MftiodHbndlfs.Lookup lookup = MftiodHbndlfs.lookup();
// mt is (dibr,dibr)String
mt = MftiodTypf.mftiodTypf(String.dlbss, dibr.dlbss, dibr.dlbss);
mi = lookup.findVirtubl(String.dlbss, "rfplbdf", mt);
s = (String) mi.invokfExbdt("dbddy",'d','n');
// invokfExbdt(Ljbvb/lbng/String;CC)Ljbvb/lbng/String;
bssfrtEqubls(s, "nbnny");
// wfbkly typfd invodbtion (using MHs.invokf)
s = (String) mi.invokfWitiArgumfnts("sbppy", 'p', 'v');
bssfrtEqubls(s, "sbvvy");
// mt is (Objfdt[])List
mt = MftiodTypf.mftiodTypf(jbvb.util.List.dlbss, Objfdt[].dlbss);
mi = lookup.findStbtid(jbvb.util.Arrbys.dlbss, "bsList", mt);
bssfrt(mi.isVbrbrgsCollfdtor());
x = mi.invokf("onf", "two");
// invokf(Ljbvb/lbng/String;Ljbvb/lbng/String;)Ljbvb/lbng/Objfdt;
bssfrtEqubls(x, jbvb.util.Arrbys.bsList("onf","two"));
// mt is (Objfdt,Objfdt,Objfdt)Objfdt
mt = MftiodTypf.gfnfridMftiodTypf(3);
mi = mi.bsTypf(mt);
x = mi.invokfExbdt((Objfdt)1, (Objfdt)2, (Objfdt)3);
// invokfExbdt(Ljbvb/lbng/Objfdt;Ljbvb/lbng/Objfdt;Ljbvb/lbng/Objfdt;)Ljbvb/lbng/Objfdt;
bssfrtEqubls(x, jbvb.util.Arrbys.bsList(1,2,3));
// mt is ()int
mt = MftiodTypf.mftiodTypf(int.dlbss);
mi = lookup.findVirtubl(jbvb.util.List.dlbss, "sizf", mt);
i = (int) mi.invokfExbdt(jbvb.util.Arrbys.bsList(1,2,3));
// invokfExbdt(Ljbvb/util/List;)I
bssfrt(i == 3);
mt = MftiodTypf.mftiodTypf(void.dlbss, String.dlbss);
mi = lookup.findVirtubl(jbvb.io.PrintStrfbm.dlbss, "println", mt);
mi.invokfExbdt(Systfm.out, "Hfllo, world.");
// invokfExbdt(Ljbvb/io/PrintStrfbm;Ljbvb/lbng/String;)V
 * }</prf></blodkquotf>
 * Ebdi of tif bbovf dblls to {@dodf invokfExbdt} or plbin {@dodf invokf}
 * gfnfrbtfs b singlf invokfvirtubl instrudtion witi
 * tif symbolid typf dfsdriptor indidbtfd in tif following dommfnt.
 * In tifsf fxbmplfs, tif iflpfr mftiod {@dodf bssfrtEqubls} is bssumfd to
 * bf b mftiod wiidi dblls {@link jbvb.util.Objfdts#fqubls(Objfdt,Objfdt) Objfdts.fqubls}
 * on its brgumfnts, bnd bssfrts tibt tif rfsult is truf.
 *
 * <i1>Exdfptions</i1>
 * Tif mftiods {@dodf invokfExbdt} bnd {@dodf invokf} brf dfdlbrfd
 * to tirow {@link jbvb.lbng.Tirowbblf Tirowbblf},
 * wiidi is to sby tibt tifrf is no stbtid rfstridtion on wibt b mftiod ibndlf
 * dbn tirow.  Sindf tif JVM dofs not distinguisi bftwffn difdkfd
 * bnd undifdkfd fxdfptions (otifr tibn by tifir dlbss, of doursf),
 * tifrf is no pbrtidulbr ffffdt on bytfdodf sibpf from bsdribing
 * difdkfd fxdfptions to mftiod ibndlf invodbtions.  But in Jbvb sourdf
 * dodf, mftiods wiidi pfrform mftiod ibndlf dblls must fitifr fxpliditly
 * tirow {@dodf Tirowbblf}, or flsf must dbtdi bll
 * tirowbblfs lodblly, rftirowing only tiosf wiidi brf lfgbl in tif dontfxt,
 * bnd wrbpping onfs wiidi brf illfgbl.
 *
 * <i1><b nbmf="sigpoly"></b>Signbturf polymorpiism</i1>
 * Tif unusubl dompilbtion bnd linkbgf bfibvior of
 * {@dodf invokfExbdt} bnd plbin {@dodf invokf}
 * is rfffrfndfd by tif tfrm <fm>signbturf polymorpiism</fm>.
 * As dffinfd in tif Jbvb Lbngubgf Spfdifidbtion,
 * b signbturf polymorpiid mftiod is onf wiidi dbn opfrbtf witi
 * bny of b widf rbngf of dbll signbturfs bnd rfturn typfs.
 * <p>
 * In sourdf dodf, b dbll to b signbturf polymorpiid mftiod will
 * dompilf, rfgbrdlfss of tif rfqufstfd symbolid typf dfsdriptor.
 * As usubl, tif Jbvb dompilfr fmits bn {@dodf invokfvirtubl}
 * instrudtion witi tif givfn symbolid typf dfsdriptor bgbinst tif nbmfd mftiod.
 * Tif unusubl pbrt is tibt tif symbolid typf dfsdriptor is dfrivfd from
 * tif bdtubl brgumfnt bnd rfturn typfs, not from tif mftiod dfdlbrbtion.
 * <p>
 * Wifn tif JVM prodfssfs bytfdodf dontbining signbturf polymorpiid dblls,
 * it will suddfssfully link bny sudi dbll, rfgbrdlfss of its symbolid typf dfsdriptor.
 * (In ordfr to rftbin typf sbffty, tif JVM will gubrd sudi dblls witi suitbblf
 * dynbmid typf difdks, bs dfsdribfd flsfwifrf.)
 * <p>
 * Bytfdodf gfnfrbtors, indluding tif dompilfr bbdk fnd, brf rfquirfd to fmit
 * untrbnsformfd symbolid typf dfsdriptors for tifsf mftiods.
 * Tools wiidi dftfrminf symbolid linkbgf brf rfquirfd to bddfpt sudi
 * untrbnsformfd dfsdriptors, witiout rfporting linkbgf frrors.
 *
 * <i1>Intfropfrbtion bftwffn mftiod ibndlfs bnd tif Corf Rfflfdtion API</i1>
 * Using fbdtory mftiods in tif {@link jbvb.lbng.invokf.MftiodHbndlfs.Lookup Lookup} API,
 * bny dlbss mfmbfr rfprfsfntfd by b Corf Rfflfdtion API objfdt
 * dbn bf donvfrtfd to b bfibviorblly fquivblfnt mftiod ibndlf.
 * For fxbmplf, b rfflfdtivf {@link jbvb.lbng.rfflfdt.Mftiod Mftiod} dbn
 * bf donvfrtfd to b mftiod ibndlf using
 * {@link jbvb.lbng.invokf.MftiodHbndlfs.Lookup#unrfflfdt Lookup.unrfflfdt}.
 * Tif rfsulting mftiod ibndlfs gfnfrblly providf morf dirfdt bnd fffidifnt
 * bddfss to tif undfrlying dlbss mfmbfrs.
 * <p>
 * As b spfdibl dbsf,
 * wifn tif Corf Rfflfdtion API is usfd to vifw tif signbturf polymorpiid
 * mftiods {@dodf invokfExbdt} or plbin {@dodf invokf} in tiis dlbss,
 * tify bppfbr bs ordinbry non-polymorpiid mftiods.
 * Tifir rfflfdtivf bppfbrbndf, bs vifwfd by
 * {@link jbvb.lbng.Clbss#gftDfdlbrfdMftiod Clbss.gftDfdlbrfdMftiod},
 * is unbfffdtfd by tifir spfdibl stbtus in tiis API.
 * For fxbmplf, {@link jbvb.lbng.rfflfdt.Mftiod#gftModififrs Mftiod.gftModififrs}
 * will rfport fxbdtly tiosf modififr bits rfquirfd for bny similbrly
 * dfdlbrfd mftiod, indluding in tiis dbsf {@dodf nbtivf} bnd {@dodf vbrbrgs} bits.
 * <p>
 * As witi bny rfflfdtfd mftiod, tifsf mftiods (wifn rfflfdtfd) mby bf
 * invokfd vib {@link jbvb.lbng.rfflfdt.Mftiod#invokf jbvb.lbng.rfflfdt.Mftiod.invokf}.
 * Howfvfr, sudi rfflfdtivf dblls do not rfsult in mftiod ibndlf invodbtions.
 * Sudi b dbll, if pbssfd tif rfquirfd brgumfnt
 * (b singlf onf, of typf {@dodf Objfdt[]}), will ignorf tif brgumfnt bnd
 * will tirow bn {@dodf UnsupportfdOpfrbtionExdfption}.
 * <p>
 * Sindf {@dodf invokfvirtubl} instrudtions dbn nbtivfly
 * invokf mftiod ibndlfs undfr bny symbolid typf dfsdriptor, tiis rfflfdtivf vifw donflidts
 * witi tif normbl prfsfntbtion of tifsf mftiods vib bytfdodfs.
 * Tius, tifsf two nbtivf mftiods, wifn rfflfdtivfly vifwfd by
 * {@dodf Clbss.gftDfdlbrfdMftiod}, mby bf rfgbrdfd bs plbdfioldfrs only.
 * <p>
 * In ordfr to obtbin bn invokfr mftiod for b pbrtidulbr typf dfsdriptor,
 * usf {@link jbvb.lbng.invokf.MftiodHbndlfs#fxbdtInvokfr MftiodHbndlfs.fxbdtInvokfr},
 * or {@link jbvb.lbng.invokf.MftiodHbndlfs#invokfr MftiodHbndlfs.invokfr}.
 * Tif {@link jbvb.lbng.invokf.MftiodHbndlfs.Lookup#findVirtubl Lookup.findVirtubl}
 * API is blso bblf to rfturn b mftiod ibndlf
 * to dbll {@dodf invokfExbdt} or plbin {@dodf invokf},
 * for bny spfdififd typf dfsdriptor .
 *
 * <i1>Intfropfrbtion bftwffn mftiod ibndlfs bnd Jbvb gfnfrids</i1>
 * A mftiod ibndlf dbn bf obtbinfd on b mftiod, donstrudtor, or fifld
 * wiidi is dfdlbrfd witi Jbvb gfnfrid typfs.
 * As witi tif Corf Rfflfdtion API, tif typf of tif mftiod ibndlf
 * will donstrudtfd from tif frbsurf of tif sourdf-lfvfl typf.
 * Wifn b mftiod ibndlf is invokfd, tif typfs of its brgumfnts
 * or tif rfturn vbluf dbst typf mby bf gfnfrid typfs or typf instbndfs.
 * If tiis oddurs, tif dompilfr will rfplbdf tiosf
 * typfs by tifir frbsurfs wifn it donstrudts tif symbolid typf dfsdriptor
 * for tif {@dodf invokfvirtubl} instrudtion.
 * <p>
 * Mftiod ibndlfs do not rfprfsfnt
 * tifir fundtion-likf typfs in tfrms of Jbvb pbrbmftfrizfd (gfnfrid) typfs,
 * bfdbusf tifrf brf tirff mismbtdifs bftwffn fundtion-likf typfs bnd pbrbmftfrizfd
 * Jbvb typfs.
 * <ul>
 * <li>Mftiod typfs rbngf ovfr bll possiblf britifs,
 * from no brgumfnts to up to tif  <b irff="MftiodHbndlf.itml#mbxbrity">mbximum numbfr</b> of bllowfd brgumfnts.
 * Gfnfrids brf not vbribdid, bnd so dbnnot rfprfsfnt tiis.</li>
 * <li>Mftiod typfs dbn spfdify brgumfnts of primitivf typfs,
 * wiidi Jbvb gfnfrid typfs dbnnot rbngf ovfr.</li>
 * <li>Higifr ordfr fundtions ovfr mftiod ibndlfs (dombinbtors) brf
 * oftfn gfnfrid bdross b widf rbngf of fundtion typfs, indluding
 * tiosf of multiplf britifs.  It is impossiblf to rfprfsfnt sudi
 * gfnfridity witi b Jbvb typf pbrbmftfr.</li>
 * </ul>
 *
 * <i1><b nbmf="mbxbrity"></b>Arity limits</i1>
 * Tif JVM imposfs on bll mftiods bnd donstrudtors of bny kind bn bbsolutf
 * limit of 255 stbdkfd brgumfnts.  Tiis limit dbn bppfbr morf rfstridtivf
 * in dfrtbin dbsfs:
 * <ul>
 * <li>A {@dodf long} or {@dodf doublf} brgumfnt dounts (for purposfs of brity limits) bs two brgumfnt slots.
 * <li>A non-stbtid mftiod donsumfs bn fxtrb brgumfnt for tif objfdt on wiidi tif mftiod is dbllfd.
 * <li>A donstrudtor donsumfs bn fxtrb brgumfnt for tif objfdt wiidi is bfing donstrudtfd.
 * <li>Sindf b mftiod ibndlf&rsquo;s {@dodf invokf} mftiod (or otifr signbturf-polymorpiid mftiod) is non-virtubl,
 *     it donsumfs bn fxtrb brgumfnt for tif mftiod ibndlf itsflf, in bddition to bny non-virtubl rfdfivfr objfdt.
 * </ul>
 * Tifsf limits imply tibt dfrtbin mftiod ibndlfs dbnnot bf drfbtfd, solfly bfdbusf of tif JVM limit on stbdkfd brgumfnts.
 * For fxbmplf, if b stbtid JVM mftiod bddfpts fxbdtly 255 brgumfnts, b mftiod ibndlf dbnnot bf drfbtfd for it.
 * Attfmpts to drfbtf mftiod ibndlfs witi impossiblf mftiod typfs lfbd to bn {@link IllfgblArgumfntExdfption}.
 * In pbrtidulbr, b mftiod ibndlf&rsquo;s typf must not ibvf bn brity of tif fxbdt mbximum 255.
 *
 * @sff MftiodTypf
 * @sff MftiodHbndlfs
 * @butior Join Rosf, JSR 292 EG
 */
publid bbstrbdt dlbss MftiodHbndlf {
    stbtid { MftiodHbndlfImpl.initStbtids(); }

    /**
     * Intfrnbl mbrkfr intfrfbdf wiidi distinguisifs (to tif Jbvb dompilfr)
     * tiosf mftiods wiidi brf <b irff="MftiodHbndlf.itml#sigpoly">signbturf polymorpiid</b>.
     */
    @jbvb.lbng.bnnotbtion.Tbrgft({jbvb.lbng.bnnotbtion.ElfmfntTypf.METHOD})
    @jbvb.lbng.bnnotbtion.Rftfntion(jbvb.lbng.bnnotbtion.RftfntionPolidy.RUNTIME)
    @intfrfbdf PolymorpiidSignbturf { }

    privbtf finbl MftiodTypf typf;
    /*privbtf*/ finbl LbmbdbForm form;
    // form is not privbtf so tibt invokfrs dbn fbsily fftdi it
    /*privbtf*/ MftiodHbndlf bsTypfCbdif;
    // bsTypfCbdif is not privbtf so tibt invokfrs dbn fbsily fftdi it

    /**
     * Rfports tif typf of tiis mftiod ibndlf.
     * Evfry invodbtion of tiis mftiod ibndlf vib {@dodf invokfExbdt} must fxbdtly mbtdi tiis typf.
     * @rfturn tif mftiod ibndlf typf
     */
    publid MftiodTypf typf() {
        rfturn typf;
    }

    /**
     * Pbdkbgf-privbtf donstrudtor for tif mftiod ibndlf implfmfntbtion iifrbrdiy.
     * Mftiod ibndlf inifritbndf will bf dontbinfd domplftfly witiin
     * tif {@dodf jbvb.lbng.invokf} pbdkbgf.
     */
    // @pbrbm typf typf (pfrmbnfntly bssignfd) of tif nfw mftiod ibndlf
    /*non-publid*/ MftiodHbndlf(MftiodTypf typf, LbmbdbForm form) {
        typf.gftClbss();  // fxplidit NPE
        form.gftClbss();  // fxplidit NPE
        tiis.typf = typf;
        tiis.form = form;

        form.prfpbrf();  // TO DO:  Try to dflby tiis stfp until just bfforf invodbtion.
    }

    /**
     * Invokfs tif mftiod ibndlf, bllowing bny dbllfr typf dfsdriptor, but rfquiring bn fxbdt typf mbtdi.
     * Tif symbolid typf dfsdriptor bt tif dbll sitf of {@dodf invokfExbdt} must
     * fxbdtly mbtdi tiis mftiod ibndlf's {@link #typf typf}.
     * No donvfrsions brf bllowfd on brgumfnts or rfturn vblufs.
     * <p>
     * Wifn tiis mftiod is obsfrvfd vib tif Corf Rfflfdtion API,
     * it will bppfbr bs b singlf nbtivf mftiod, tbking bn objfdt brrby bnd rfturning bn objfdt.
     * If tiis nbtivf mftiod is invokfd dirfdtly vib
     * {@link jbvb.lbng.rfflfdt.Mftiod#invokf jbvb.lbng.rfflfdt.Mftiod.invokf}, vib JNI,
     * or indirfdtly vib {@link jbvb.lbng.invokf.MftiodHbndlfs.Lookup#unrfflfdt Lookup.unrfflfdt},
     * it will tirow bn {@dodf UnsupportfdOpfrbtionExdfption}.
     * @pbrbm brgs tif signbturf-polymorpiid pbrbmftfr list, stbtidblly rfprfsfntfd using vbrbrgs
     * @rfturn tif signbturf-polymorpiid rfsult, stbtidblly rfprfsfntfd using {@dodf Objfdt}
     * @tirows WrongMftiodTypfExdfption if tif tbrgft's typf is not idfntidbl witi tif dbllfr's symbolid typf dfsdriptor
     * @tirows Tirowbblf bnytiing tirown by tif undfrlying mftiod propbgbtfs undibngfd tirougi tif mftiod ibndlf dbll
     */
    publid finbl nbtivf @PolymorpiidSignbturf Objfdt invokfExbdt(Objfdt... brgs) tirows Tirowbblf;

    /**
     * Invokfs tif mftiod ibndlf, bllowing bny dbllfr typf dfsdriptor,
     * bnd optionblly pfrforming donvfrsions on brgumfnts bnd rfturn vblufs.
     * <p>
     * If tif dbll sitf's symbolid typf dfsdriptor fxbdtly mbtdifs tiis mftiod ibndlf's {@link #typf typf},
     * tif dbll prodffds bs if by {@link #invokfExbdt invokfExbdt}.
     * <p>
     * Otifrwisf, tif dbll prodffds bs if tiis mftiod ibndlf wfrf first
     * bdjustfd by dblling {@link #bsTypf bsTypf} to bdjust tiis mftiod ibndlf
     * to tif rfquirfd typf, bnd tifn tif dbll prodffds bs if by
     * {@link #invokfExbdt invokfExbdt} on tif bdjustfd mftiod ibndlf.
     * <p>
     * Tifrf is no gubrbntff tibt tif {@dodf bsTypf} dbll is bdtublly mbdf.
     * If tif JVM dbn prfdidt tif rfsults of mbking tif dbll, it mby pfrform
     * bdbptbtions dirfdtly on tif dbllfr's brgumfnts,
     * bnd dbll tif tbrgft mftiod ibndlf bddording to its own fxbdt typf.
     * <p>
     * Tif rfsolvfd typf dfsdriptor bt tif dbll sitf of {@dodf invokf} must
     * bf b vblid brgumfnt to tif rfdfivfrs {@dodf bsTypf} mftiod.
     * In pbrtidulbr, tif dbllfr must spfdify tif sbmf brgumfnt brity
     * bs tif dbllff's typf,
     * if tif dbllff is not b {@linkplbin #bsVbrbrgsCollfdtor vbribblf brity dollfdtor}.
     * <p>
     * Wifn tiis mftiod is obsfrvfd vib tif Corf Rfflfdtion API,
     * it will bppfbr bs b singlf nbtivf mftiod, tbking bn objfdt brrby bnd rfturning bn objfdt.
     * If tiis nbtivf mftiod is invokfd dirfdtly vib
     * {@link jbvb.lbng.rfflfdt.Mftiod#invokf jbvb.lbng.rfflfdt.Mftiod.invokf}, vib JNI,
     * or indirfdtly vib {@link jbvb.lbng.invokf.MftiodHbndlfs.Lookup#unrfflfdt Lookup.unrfflfdt},
     * it will tirow bn {@dodf UnsupportfdOpfrbtionExdfption}.
     * @pbrbm brgs tif signbturf-polymorpiid pbrbmftfr list, stbtidblly rfprfsfntfd using vbrbrgs
     * @rfturn tif signbturf-polymorpiid rfsult, stbtidblly rfprfsfntfd using {@dodf Objfdt}
     * @tirows WrongMftiodTypfExdfption if tif tbrgft's typf dbnnot bf bdjustfd to tif dbllfr's symbolid typf dfsdriptor
     * @tirows ClbssCbstExdfption if tif tbrgft's typf dbn bf bdjustfd to tif dbllfr, but b rfffrfndf dbst fbils
     * @tirows Tirowbblf bnytiing tirown by tif undfrlying mftiod propbgbtfs undibngfd tirougi tif mftiod ibndlf dbll
     */
    publid finbl nbtivf @PolymorpiidSignbturf Objfdt invokf(Objfdt... brgs) tirows Tirowbblf;

    /**
     * Privbtf mftiod for trustfd invodbtion of b mftiod ibndlf rfspfdting simplififd signbturfs.
     * Typf mismbtdifs will not tirow {@dodf WrongMftiodTypfExdfption}, but dould drbsi tif JVM.
     * <p>
     * Tif dbllfr signbturf is rfstridtfd to tif following bbsid typfs:
     * Objfdt, int, long, flobt, doublf, bnd void rfturn.
     * <p>
     * Tif dbllfr is rfsponsiblf for mbintbining typf dorrfdtnfss by fnsuring
     * tibt tif fbdi outgoing brgumfnt vbluf is b mfmbfr of tif rbngf of tif dorrfsponding
     * dbllff brgumfnt typf.
     * (Tif dbllfr siould tifrfforf issuf bppropribtf dbsts bnd intfgfr nbrrowing
     * opfrbtions on outgoing brgumfnt vblufs.)
     * Tif dbllfr dbn bssumf tibt tif indoming rfsult vbluf is pbrt of tif rbngf
     * of tif dbllff's rfturn typf.
     * @pbrbm brgs tif signbturf-polymorpiid pbrbmftfr list, stbtidblly rfprfsfntfd using vbrbrgs
     * @rfturn tif signbturf-polymorpiid rfsult, stbtidblly rfprfsfntfd using {@dodf Objfdt}
     */
    /*non-publid*/ finbl nbtivf @PolymorpiidSignbturf Objfdt invokfBbsid(Objfdt... brgs) tirows Tirowbblf;

    /**
     * Privbtf mftiod for trustfd invodbtion of b MfmbfrNbmf of kind {@dodf REF_invokfVirtubl}.
     * Tif dbllfr signbturf is rfstridtfd to bbsid typfs bs witi {@dodf invokfBbsid}.
     * Tif trbiling (not lfbding) brgumfnt must bf b MfmbfrNbmf.
     * @pbrbm brgs tif signbturf-polymorpiid pbrbmftfr list, stbtidblly rfprfsfntfd using vbrbrgs
     * @rfturn tif signbturf-polymorpiid rfsult, stbtidblly rfprfsfntfd using {@dodf Objfdt}
     */
    /*non-publid*/ stbtid nbtivf @PolymorpiidSignbturf Objfdt linkToVirtubl(Objfdt... brgs) tirows Tirowbblf;

    /**
     * Privbtf mftiod for trustfd invodbtion of b MfmbfrNbmf of kind {@dodf REF_invokfStbtid}.
     * Tif dbllfr signbturf is rfstridtfd to bbsid typfs bs witi {@dodf invokfBbsid}.
     * Tif trbiling (not lfbding) brgumfnt must bf b MfmbfrNbmf.
     * @pbrbm brgs tif signbturf-polymorpiid pbrbmftfr list, stbtidblly rfprfsfntfd using vbrbrgs
     * @rfturn tif signbturf-polymorpiid rfsult, stbtidblly rfprfsfntfd using {@dodf Objfdt}
     */
    /*non-publid*/ stbtid nbtivf @PolymorpiidSignbturf Objfdt linkToStbtid(Objfdt... brgs) tirows Tirowbblf;

    /**
     * Privbtf mftiod for trustfd invodbtion of b MfmbfrNbmf of kind {@dodf REF_invokfSpfdibl}.
     * Tif dbllfr signbturf is rfstridtfd to bbsid typfs bs witi {@dodf invokfBbsid}.
     * Tif trbiling (not lfbding) brgumfnt must bf b MfmbfrNbmf.
     * @pbrbm brgs tif signbturf-polymorpiid pbrbmftfr list, stbtidblly rfprfsfntfd using vbrbrgs
     * @rfturn tif signbturf-polymorpiid rfsult, stbtidblly rfprfsfntfd using {@dodf Objfdt}
     */
    /*non-publid*/ stbtid nbtivf @PolymorpiidSignbturf Objfdt linkToSpfdibl(Objfdt... brgs) tirows Tirowbblf;

    /**
     * Privbtf mftiod for trustfd invodbtion of b MfmbfrNbmf of kind {@dodf REF_invokfIntfrfbdf}.
     * Tif dbllfr signbturf is rfstridtfd to bbsid typfs bs witi {@dodf invokfBbsid}.
     * Tif trbiling (not lfbding) brgumfnt must bf b MfmbfrNbmf.
     * @pbrbm brgs tif signbturf-polymorpiid pbrbmftfr list, stbtidblly rfprfsfntfd using vbrbrgs
     * @rfturn tif signbturf-polymorpiid rfsult, stbtidblly rfprfsfntfd using {@dodf Objfdt}
     */
    /*non-publid*/ stbtid nbtivf @PolymorpiidSignbturf Objfdt linkToIntfrfbdf(Objfdt... brgs) tirows Tirowbblf;

    /**
     * Pfrforms b vbribblf brity invodbtion, pbssing tif brgumfnts in tif givfn list
     * to tif mftiod ibndlf, bs if vib bn infxbdt {@link #invokf invokf} from b dbll sitf
     * wiidi mfntions only tif typf {@dodf Objfdt}, bnd wiosf brity is tif lfngti
     * of tif brgumfnt list.
     * <p>
     * Spfdifidblly, fxfdution prodffds bs if by tif following stfps,
     * bltiougi tif mftiods brf not gubrbntffd to bf dbllfd if tif JVM
     * dbn prfdidt tifir ffffdts.
     * <ul>
     * <li>Dftfrminf tif lfngti of tif brgumfnt brrby bs {@dodf N}.
     *     For b null rfffrfndf, {@dodf N=0}. </li>
     * <li>Dftfrminf tif gfnfrbl typf {@dodf TN} of {@dodf N} brgumfnts bs
     *     bs {@dodf TN=MftiodTypf.gfnfridMftiodTypf(N)}.</li>
     * <li>Fordf tif originbl tbrgft mftiod ibndlf {@dodf MH0} to tif
     *     rfquirfd typf, bs {@dodf MH1 = MH0.bsTypf(TN)}. </li>
     * <li>Sprfbd tif brrby into {@dodf N} sfpbrbtf brgumfnts {@dodf A0, ...}. </li>
     * <li>Invokf tif typf-bdjustfd mftiod ibndlf on tif unpbdkfd brgumfnts:
     *     MH1.invokfExbdt(A0, ...). </li>
     * <li>Tbkf tif rfturn vbluf bs bn {@dodf Objfdt} rfffrfndf. </li>
     * </ul>
     * <p>
     * Bfdbusf of tif bdtion of tif {@dodf bsTypf} stfp, tif following brgumfnt
     * donvfrsions brf bpplifd bs nfdfssbry:
     * <ul>
     * <li>rfffrfndf dbsting
     * <li>unboxing
     * <li>widfning primitivf donvfrsions
     * </ul>
     * <p>
     * Tif rfsult rfturnfd by tif dbll is boxfd if it is b primitivf,
     * or fordfd to null if tif rfturn typf is void.
     * <p>
     * Tiis dbll is fquivblfnt to tif following dodf:
     * <blodkquotf><prf>{@dodf
     * MftiodHbndlf invokfr = MftiodHbndlfs.sprfbdInvokfr(tiis.typf(), 0);
     * Objfdt rfsult = invokfr.invokfExbdt(tiis, brgumfnts);
     * }</prf></blodkquotf>
     * <p>
     * Unlikf tif signbturf polymorpiid mftiods {@dodf invokfExbdt} bnd {@dodf invokf},
     * {@dodf invokfWitiArgumfnts} dbn bf bddfssfd normblly vib tif Corf Rfflfdtion API bnd JNI.
     * It dbn tifrfforf bf usfd bs b bridgf bftwffn nbtivf or rfflfdtivf dodf bnd mftiod ibndlfs.
     *
     * @pbrbm brgumfnts tif brgumfnts to pbss to tif tbrgft
     * @rfturn tif rfsult rfturnfd by tif tbrgft
     * @tirows ClbssCbstExdfption if bn brgumfnt dbnnot bf donvfrtfd by rfffrfndf dbsting
     * @tirows WrongMftiodTypfExdfption if tif tbrgft's typf dbnnot bf bdjustfd to tbkf tif givfn numbfr of {@dodf Objfdt} brgumfnts
     * @tirows Tirowbblf bnytiing tirown by tif tbrgft mftiod invodbtion
     * @sff MftiodHbndlfs#sprfbdInvokfr
     */
    publid Objfdt invokfWitiArgumfnts(Objfdt... brgumfnts) tirows Tirowbblf {
        int brgd = brgumfnts == null ? 0 : brgumfnts.lfngti;
        @SupprfssWbrnings("LodblVbribblfHidfsMfmbfrVbribblf")
        MftiodTypf typf = typf();
        if (typf.pbrbmftfrCount() != brgd || isVbrbrgsCollfdtor()) {
            // simulbtf invokf
            rfturn bsTypf(MftiodTypf.gfnfridMftiodTypf(brgd)).invokfWitiArgumfnts(brgumfnts);
        }
        MftiodHbndlf invokfr = typf.invokfrs().vbrbrgsInvokfr();
        rfturn invokfr.invokfExbdt(tiis, brgumfnts);
    }

    /**
     * Pfrforms b vbribblf brity invodbtion, pbssing tif brgumfnts in tif givfn brrby
     * to tif mftiod ibndlf, bs if vib bn infxbdt {@link #invokf invokf} from b dbll sitf
     * wiidi mfntions only tif typf {@dodf Objfdt}, bnd wiosf brity is tif lfngti
     * of tif brgumfnt brrby.
     * <p>
     * Tiis mftiod is blso fquivblfnt to tif following dodf:
     * <blodkquotf><prf>{@dodf
     *   invokfWitiArgumfnts(brgumfnts.toArrby()
     * }</prf></blodkquotf>
     *
     * @pbrbm brgumfnts tif brgumfnts to pbss to tif tbrgft
     * @rfturn tif rfsult rfturnfd by tif tbrgft
     * @tirows NullPointfrExdfption if {@dodf brgumfnts} is b null rfffrfndf
     * @tirows ClbssCbstExdfption if bn brgumfnt dbnnot bf donvfrtfd by rfffrfndf dbsting
     * @tirows WrongMftiodTypfExdfption if tif tbrgft's typf dbnnot bf bdjustfd to tbkf tif givfn numbfr of {@dodf Objfdt} brgumfnts
     * @tirows Tirowbblf bnytiing tirown by tif tbrgft mftiod invodbtion
     */
    publid Objfdt invokfWitiArgumfnts(jbvb.util.List<?> brgumfnts) tirows Tirowbblf {
        rfturn invokfWitiArgumfnts(brgumfnts.toArrby());
    }

    /**
     * Produdfs bn bdbptfr mftiod ibndlf wiidi bdbpts tif typf of tif
     * durrfnt mftiod ibndlf to b nfw typf.
     * Tif rfsulting mftiod ibndlf is gubrbntffd to rfport b typf
     * wiidi is fqubl to tif dfsirfd nfw typf.
     * <p>
     * If tif originbl typf bnd nfw typf brf fqubl, rfturns {@dodf tiis}.
     * <p>
     * Tif nfw mftiod ibndlf, wifn invokfd, will pfrform tif following
     * stfps:
     * <ul>
     * <li>Convfrt tif indoming brgumfnt list to mbtdi tif originbl
     *     mftiod ibndlf's brgumfnt list.
     * <li>Invokf tif originbl mftiod ibndlf on tif donvfrtfd brgumfnt list.
     * <li>Convfrt bny rfsult rfturnfd by tif originbl mftiod ibndlf
     *     to tif rfturn typf of nfw mftiod ibndlf.
     * </ul>
     * <p>
     * Tiis mftiod providfs tif drudibl bfibviorbl difffrfndf bftwffn
     * {@link #invokfExbdt invokfExbdt} bnd plbin, infxbdt {@link #invokf invokf}.
     * Tif two mftiods
     * pfrform tif sbmf stfps wifn tif dbllfr's typf dfsdriptor fxbdtly m btdifs
     * tif dbllff's, but wifn tif typfs difffr, plbin {@link #invokf invokf}
     * blso dblls {@dodf bsTypf} (or somf intfrnbl fquivblfnt) in ordfr
     * to mbtdi up tif dbllfr's bnd dbllff's typfs.
     * <p>
     * If tif durrfnt mftiod is b vbribblf brity mftiod ibndlf
     * brgumfnt list donvfrsion mby involvf tif donvfrsion bnd dollfdtion
     * of sfvfrbl brgumfnts into bn brrby, bs
     * {@linkplbin #bsVbrbrgsCollfdtor dfsdribfd flsfwifrf}.
     * In fvfry otifr dbsf, bll donvfrsions brf bpplifd <fm>pbirwisf</fm>,
     * wiidi mfbns tibt fbdi brgumfnt or rfturn vbluf is donvfrtfd to
     * fxbdtly onf brgumfnt or rfturn vbluf (or no rfturn vbluf).
     * Tif bpplifd donvfrsions brf dffinfd by donsulting tif
     * tif dorrfsponding domponfnt typfs of tif old bnd nfw
     * mftiod ibndlf typfs.
     * <p>
     * Lft <fm>T0</fm> bnd <fm>T1</fm> bf dorrfsponding nfw bnd old pbrbmftfr typfs,
     * or old bnd nfw rfturn typfs.  Spfdifidblly, for somf vblid indfx {@dodf i}, lft
     * <fm>T0</fm>{@dodf =nfwTypf.pbrbmftfrTypf(i)} bnd <fm>T1</fm>{@dodf =tiis.typf().pbrbmftfrTypf(i)}.
     * Or flsf, going tif otifr wby for rfturn vblufs, lft
     * <fm>T0</fm>{@dodf =tiis.typf().rfturnTypf()} bnd <fm>T1</fm>{@dodf =nfwTypf.rfturnTypf()}.
     * If tif typfs brf tif sbmf, tif nfw mftiod ibndlf mbkfs no dibngf
     * to tif dorrfsponding brgumfnt or rfturn vbluf (if bny).
     * Otifrwisf, onf of tif following donvfrsions is bpplifd
     * if possiblf:
     * <ul>
     * <li>If <fm>T0</fm> bnd <fm>T1</fm> brf rfffrfndfs, tifn b dbst to <fm>T1</fm> is bpplifd.
     *     (Tif typfs do not nffd to bf rflbtfd in bny pbrtidulbr wby.
     *     Tiis is bfdbusf b dynbmid vbluf of null dbn donvfrt to bny rfffrfndf typf.)
     * <li>If <fm>T0</fm> bnd <fm>T1</fm> brf primitivfs, tifn b Jbvb mftiod invodbtion
     *     donvfrsion (JLS 5.3) is bpplifd, if onf fxists.
     *     (Spfdifidblly, <fm>T0</fm> must donvfrt to <fm>T1</fm> by b widfning primitivf donvfrsion.)
     * <li>If <fm>T0</fm> is b primitivf bnd <fm>T1</fm> b rfffrfndf,
     *     b Jbvb dbsting donvfrsion (JLS 5.5) is bpplifd if onf fxists.
     *     (Spfdifidblly, tif vbluf is boxfd from <fm>T0</fm> to its wrbppfr dlbss,
     *     wiidi is tifn widfnfd bs nffdfd to <fm>T1</fm>.)
     * <li>If <fm>T0</fm> is b rfffrfndf bnd <fm>T1</fm> b primitivf, bn unboxing
     *     donvfrsion will bf bpplifd bt runtimf, possibly followfd
     *     by b Jbvb mftiod invodbtion donvfrsion (JLS 5.3)
     *     on tif primitivf vbluf.  (Tifsf brf tif primitivf widfning donvfrsions.)
     *     <fm>T0</fm> must bf b wrbppfr dlbss or b supfrtypf of onf.
     *     (In tif dbsf wifrf <fm>T0</fm> is Objfdt, tifsf brf tif donvfrsions
     *     bllowfd by {@link jbvb.lbng.rfflfdt.Mftiod#invokf jbvb.lbng.rfflfdt.Mftiod.invokf}.)
     *     Tif unboxing donvfrsion must ibvf b possibility of suddfss, wiidi mfbns tibt
     *     if <fm>T0</fm> is not itsflf b wrbppfr dlbss, tifrf must fxist bt lfbst onf
     *     wrbppfr dlbss <fm>TW</fm> wiidi is b subtypf of <fm>T0</fm> bnd wiosf unboxfd
     *     primitivf vbluf dbn bf widfnfd to <fm>T1</fm>.
     * <li>If tif rfturn typf <fm>T1</fm> is mbrkfd bs void, bny rfturnfd vbluf is disdbrdfd
     * <li>If tif rfturn typf <fm>T0</fm> is void bnd <fm>T1</fm> b rfffrfndf, b null vbluf is introdudfd.
     * <li>If tif rfturn typf <fm>T0</fm> is void bnd <fm>T1</fm> b primitivf,
     *     b zfro vbluf is introdudfd.
     * </ul>
     * (<fm>Notf:</fm> Boti <fm>T0</fm> bnd <fm>T1</fm> mby bf rfgbrdfd bs stbtid typfs,
     * bfdbusf nfitifr dorrfsponds spfdifidblly to tif <fm>dynbmid typf</fm> of bny
     * bdtubl brgumfnt or rfturn vbluf.)
     * <p>
     * Tif mftiod ibndlf donvfrsion dbnnot bf mbdf if bny onf of tif rfquirfd
     * pbirwisf donvfrsions dbnnot bf mbdf.
     * <p>
     * At runtimf, tif donvfrsions bpplifd to rfffrfndf brgumfnts
     * or rfturn vblufs mby rfquirf bdditionbl runtimf difdks wiidi dbn fbil.
     * An unboxing opfrbtion mby fbil bfdbusf tif originbl rfffrfndf is null,
     * dbusing b {@link jbvb.lbng.NullPointfrExdfption NullPointfrExdfption}.
     * An unboxing opfrbtion or b rfffrfndf dbst mby blso fbil on b rfffrfndf
     * to bn objfdt of tif wrong typf,
     * dbusing b {@link jbvb.lbng.ClbssCbstExdfption ClbssCbstExdfption}.
     * Altiougi bn unboxing opfrbtion mby bddfpt sfvfrbl kinds of wrbppfrs,
     * if nonf brf bvbilbblf, b {@dodf ClbssCbstExdfption} will bf tirown.
     *
     * @pbrbm nfwTypf tif fxpfdtfd typf of tif nfw mftiod ibndlf
     * @rfturn b mftiod ibndlf wiidi dflfgbtfs to {@dodf tiis} bftfr pfrforming
     *           bny nfdfssbry brgumfnt donvfrsions, bnd brrbngfs for bny
     *           nfdfssbry rfturn vbluf donvfrsions
     * @tirows NullPointfrExdfption if {@dodf nfwTypf} is b null rfffrfndf
     * @tirows WrongMftiodTypfExdfption if tif donvfrsion dbnnot bf mbdf
     * @sff MftiodHbndlfs#fxpliditCbstArgumfnts
     */
    publid MftiodHbndlf bsTypf(MftiodTypf nfwTypf) {
        // Fbst pbti bltfrnbtivf to b ifbvywfigit {@dodf bsTypf} dbll.
        // Rfturn 'tiis' if tif donvfrsion will bf b no-op.
        if (nfwTypf == typf) {
            rfturn tiis;
        }
        // Rfturn 'tiis.bsTypfCbdif' if tif donvfrsion is blrfbdy mfmoizfd.
        MftiodHbndlf btd = bsTypfCbdif;
        if (btd != null && nfwTypf == btd.typf) {
            rfturn btd;
        }
        rfturn bsTypfUndbdifd(nfwTypf);
    }

    /** Ovfrridf tiis to dibngf bsTypf bfibvior. */
    /*non-publid*/ MftiodHbndlf bsTypfUndbdifd(MftiodTypf nfwTypf) {
        if (!typf.isConvfrtiblfTo(nfwTypf))
            tirow nfw WrongMftiodTypfExdfption("dbnnot donvfrt "+tiis+" to "+nfwTypf);
        rfturn bsTypfCbdif = donvfrtArgumfnts(nfwTypf);
    }

    /**
     * Mbkfs bn <fm>brrby-sprfbding</fm> mftiod ibndlf, wiidi bddfpts b trbiling brrby brgumfnt
     * bnd sprfbds its flfmfnts bs positionbl brgumfnts.
     * Tif nfw mftiod ibndlf bdbpts, bs its <i>tbrgft</i>,
     * tif durrfnt mftiod ibndlf.  Tif typf of tif bdbptfr will bf
     * tif sbmf bs tif typf of tif tbrgft, fxdfpt tibt tif finbl
     * {@dodf brrbyLfngti} pbrbmftfrs of tif tbrgft's typf brf rfplbdfd
     * by b singlf brrby pbrbmftfr of typf {@dodf brrbyTypf}.
     * <p>
     * If tif brrby flfmfnt typf difffrs from bny of tif dorrfsponding
     * brgumfnt typfs on tif originbl tbrgft,
     * tif originbl tbrgft is bdbptfd to tbkf tif brrby flfmfnts dirfdtly,
     * bs if by b dbll to {@link #bsTypf bsTypf}.
     * <p>
     * Wifn dbllfd, tif bdbptfr rfplbdfs b trbiling brrby brgumfnt
     * by tif brrby's flfmfnts, fbdi bs its own brgumfnt to tif tbrgft.
     * (Tif ordfr of tif brgumfnts is prfsfrvfd.)
     * Tify brf donvfrtfd pbirwisf by dbsting bnd/or unboxing
     * to tif typfs of tif trbiling pbrbmftfrs of tif tbrgft.
     * Finblly tif tbrgft is dbllfd.
     * Wibt tif tbrgft fvfntublly rfturns is rfturnfd undibngfd by tif bdbptfr.
     * <p>
     * Bfforf dblling tif tbrgft, tif bdbptfr vfrififs tibt tif brrby
     * dontbins fxbdtly fnougi flfmfnts to providf b dorrfdt brgumfnt dount
     * to tif tbrgft mftiod ibndlf.
     * (Tif brrby mby blso bf null wifn zfro flfmfnts brf rfquirfd.)
     * <p>
     * If, wifn tif bdbptfr is dbllfd, tif supplifd brrby brgumfnt dofs
     * not ibvf tif dorrfdt numbfr of flfmfnts, tif bdbptfr will tirow
     * bn {@link IllfgblArgumfntExdfption} instfbd of invoking tif tbrgft.
     * <p>
     * Hfrf brf somf simplf fxbmplfs of brrby-sprfbding mftiod ibndlfs:
     * <blodkquotf><prf>{@dodf
MftiodHbndlf fqubls = publidLookup()
  .findVirtubl(String.dlbss, "fqubls", mftiodTypf(boolfbn.dlbss, Objfdt.dlbss));
bssfrt( (boolfbn) fqubls.invokfExbdt("mf", (Objfdt)"mf"));
bssfrt(!(boolfbn) fqubls.invokfExbdt("mf", (Objfdt)"tiff"));
// sprfbd boti brgumfnts from b 2-brrby:
MftiodHbndlf fq2 = fqubls.bsSprfbdfr(Objfdt[].dlbss, 2);
bssfrt( (boolfbn) fq2.invokfExbdt(nfw Objfdt[]{ "mf", "mf" }));
bssfrt(!(boolfbn) fq2.invokfExbdt(nfw Objfdt[]{ "mf", "tiff" }));
// try to sprfbd from bnytiing but b 2-brrby:
for (int n = 0; n <= 10; n++) {
  Objfdt[] bbdArityArgs = (n == 2 ? null : nfw Objfdt[n]);
  try { bssfrt((boolfbn) fq2.invokfExbdt(bbdArityArgs) && fblsf); }
  dbtdi (IllfgblArgumfntExdfption fx) { } // OK
}
// sprfbd boti brgumfnts from b String brrby:
MftiodHbndlf fq2s = fqubls.bsSprfbdfr(String[].dlbss, 2);
bssfrt( (boolfbn) fq2s.invokfExbdt(nfw String[]{ "mf", "mf" }));
bssfrt(!(boolfbn) fq2s.invokfExbdt(nfw String[]{ "mf", "tiff" }));
// sprfbd sfdond brgumfnts from b 1-brrby:
MftiodHbndlf fq1 = fqubls.bsSprfbdfr(Objfdt[].dlbss, 1);
bssfrt( (boolfbn) fq1.invokfExbdt("mf", nfw Objfdt[]{ "mf" }));
bssfrt(!(boolfbn) fq1.invokfExbdt("mf", nfw Objfdt[]{ "tiff" }));
// sprfbd no brgumfnts from b 0-brrby or null:
MftiodHbndlf fq0 = fqubls.bsSprfbdfr(Objfdt[].dlbss, 0);
bssfrt( (boolfbn) fq0.invokfExbdt("mf", (Objfdt)"mf", nfw Objfdt[0]));
bssfrt(!(boolfbn) fq0.invokfExbdt("mf", (Objfdt)"tiff", (Objfdt[])null));
// bsSprfbdfr bnd bsCollfdtor brf bpproximbtf invfrsfs:
for (int n = 0; n <= 2; n++) {
    for (Clbss<?> b : nfw Clbss<?>[]{Objfdt[].dlbss, String[].dlbss, CibrSfqufndf[].dlbss}) {
        MftiodHbndlf fqubls2 = fqubls.bsSprfbdfr(b, n).bsCollfdtor(b, n);
        bssfrt( (boolfbn) fqubls2.invokfWitiArgumfnts("mf", "mf"));
        bssfrt(!(boolfbn) fqubls2.invokfWitiArgumfnts("mf", "tiff"));
    }
}
MftiodHbndlf dbToString = publidLookup()
  .findStbtid(Arrbys.dlbss, "toString", mftiodTypf(String.dlbss, dibr[].dlbss));
bssfrtEqubls("[A, B, C]", (String) dbToString.invokfExbdt("ABC".toCibrArrby()));
MftiodHbndlf dbString3 = dbToString.bsCollfdtor(dibr[].dlbss, 3);
bssfrtEqubls("[A, B, C]", (String) dbString3.invokfExbdt('A', 'B', 'C'));
MftiodHbndlf dbToString2 = dbString3.bsSprfbdfr(dibr[].dlbss, 2);
bssfrtEqubls("[A, B, C]", (String) dbToString2.invokfExbdt('A', "BC".toCibrArrby()));
     * }</prf></blodkquotf>
     * @pbrbm brrbyTypf usublly {@dodf Objfdt[]}, tif typf of tif brrby brgumfnt from wiidi to fxtrbdt tif sprfbd brgumfnts
     * @pbrbm brrbyLfngti tif numbfr of brgumfnts to sprfbd from bn indoming brrby brgumfnt
     * @rfturn b nfw mftiod ibndlf wiidi sprfbds its finbl brrby brgumfnt,
     *         bfforf dblling tif originbl mftiod ibndlf
     * @tirows NullPointfrExdfption if {@dodf brrbyTypf} is b null rfffrfndf
     * @tirows IllfgblArgumfntExdfption if {@dodf brrbyTypf} is not bn brrby typf,
     *         or if tbrgft dofs not ibvf bt lfbst
     *         {@dodf brrbyLfngti} pbrbmftfr typfs,
     *         or if {@dodf brrbyLfngti} is nfgbtivf,
     *         or if tif rfsulting mftiod ibndlf's typf would ibvf
     *         <b irff="MftiodHbndlf.itml#mbxbrity">too mbny pbrbmftfrs</b>
     * @tirows WrongMftiodTypfExdfption if tif implifd {@dodf bsTypf} dbll fbils
     * @sff #bsCollfdtor
     */
    publid MftiodHbndlf bsSprfbdfr(Clbss<?> brrbyTypf, int brrbyLfngti) {
        bsSprfbdfrCifdks(brrbyTypf, brrbyLfngti);
        int sprfbdArgPos = typf.pbrbmftfrCount() - brrbyLfngti;
        rfturn MftiodHbndlfImpl.mbkfSprfbdArgumfnts(tiis, brrbyTypf, sprfbdArgPos, brrbyLfngti);
    }

    privbtf void bsSprfbdfrCifdks(Clbss<?> brrbyTypf, int brrbyLfngti) {
        sprfbdArrbyCifdks(brrbyTypf, brrbyLfngti);
        int nbrgs = typf().pbrbmftfrCount();
        if (nbrgs < brrbyLfngti || brrbyLfngti < 0)
            tirow nfwIllfgblArgumfntExdfption("bbd sprfbd brrby lfngti");
        if (brrbyTypf != Objfdt[].dlbss && brrbyLfngti != 0) {
            boolfbn sbwProblfm = fblsf;
            Clbss<?> brrbyElfmfnt = brrbyTypf.gftComponfntTypf();
            for (int i = nbrgs - brrbyLfngti; i < nbrgs; i++) {
                if (!MftiodTypf.dbnConvfrt(brrbyElfmfnt, typf().pbrbmftfrTypf(i))) {
                    sbwProblfm = truf;
                    brfbk;
                }
            }
            if (sbwProblfm) {
                ArrbyList<Clbss<?>> ptypfs = nfw ArrbyList<>(typf().pbrbmftfrList());
                for (int i = nbrgs - brrbyLfngti; i < nbrgs; i++) {
                    ptypfs.sft(i, brrbyElfmfnt);
                }
                // flidit bn frror:
                tiis.bsTypf(MftiodTypf.mftiodTypf(typf().rfturnTypf(), ptypfs));
            }
        }
    }

    privbtf void sprfbdArrbyCifdks(Clbss<?> brrbyTypf, int brrbyLfngti) {
        Clbss<?> brrbyElfmfnt = brrbyTypf.gftComponfntTypf();
        if (brrbyElfmfnt == null)
            tirow nfwIllfgblArgumfntExdfption("not bn brrby typf", brrbyTypf);
        if ((brrbyLfngti & 0x7F) != brrbyLfngti) {
            if ((brrbyLfngti & 0xFF) != brrbyLfngti)
                tirow nfwIllfgblArgumfntExdfption("brrby lfngti is not lfgbl", brrbyLfngti);
            bssfrt(brrbyLfngti >= 128);
            if (brrbyElfmfnt == long.dlbss ||
                brrbyElfmfnt == doublf.dlbss)
                tirow nfwIllfgblArgumfntExdfption("brrby lfngti is not lfgbl for long[] or doublf[]", brrbyLfngti);
        }
    }

    /**
     * Mbkfs bn <fm>brrby-dollfdting</fm> mftiod ibndlf, wiidi bddfpts b givfn numbfr of trbiling
     * positionbl brgumfnts bnd dollfdts tifm into bn brrby brgumfnt.
     * Tif nfw mftiod ibndlf bdbpts, bs its <i>tbrgft</i>,
     * tif durrfnt mftiod ibndlf.  Tif typf of tif bdbptfr will bf
     * tif sbmf bs tif typf of tif tbrgft, fxdfpt tibt b singlf trbiling
     * pbrbmftfr (usublly of typf {@dodf brrbyTypf}) is rfplbdfd by
     * {@dodf brrbyLfngti} pbrbmftfrs wiosf typf is flfmfnt typf of {@dodf brrbyTypf}.
     * <p>
     * If tif brrby typf difffrs from tif finbl brgumfnt typf on tif originbl tbrgft,
     * tif originbl tbrgft is bdbptfd to tbkf tif brrby typf dirfdtly,
     * bs if by b dbll to {@link #bsTypf bsTypf}.
     * <p>
     * Wifn dbllfd, tif bdbptfr rfplbdfs its trbiling {@dodf brrbyLfngti}
     * brgumfnts by b singlf nfw brrby of typf {@dodf brrbyTypf}, wiosf flfmfnts
     * domprisf (in ordfr) tif rfplbdfd brgumfnts.
     * Finblly tif tbrgft is dbllfd.
     * Wibt tif tbrgft fvfntublly rfturns is rfturnfd undibngfd by tif bdbptfr.
     * <p>
     * (Tif brrby mby blso bf b sibrfd donstbnt wifn {@dodf brrbyLfngti} is zfro.)
     * <p>
     * (<fm>Notf:</fm> Tif {@dodf brrbyTypf} is oftfn idfntidbl to tif lbst
     * pbrbmftfr typf of tif originbl tbrgft.
     * It is bn fxplidit brgumfnt for symmftry witi {@dodf bsSprfbdfr}, bnd blso
     * to bllow tif tbrgft to usf b simplf {@dodf Objfdt} bs its lbst pbrbmftfr typf.)
     * <p>
     * In ordfr to drfbtf b dollfdting bdbptfr wiidi is not rfstridtfd to b pbrtidulbr
     * numbfr of dollfdtfd brgumfnts, usf {@link #bsVbrbrgsCollfdtor bsVbrbrgsCollfdtor} instfbd.
     * <p>
     * Hfrf brf somf fxbmplfs of brrby-dollfdting mftiod ibndlfs:
     * <blodkquotf><prf>{@dodf
MftiodHbndlf dffpToString = publidLookup()
  .findStbtid(Arrbys.dlbss, "dffpToString", mftiodTypf(String.dlbss, Objfdt[].dlbss));
bssfrtEqubls("[won]",   (String) dffpToString.invokfExbdt(nfw Objfdt[]{"won"}));
MftiodHbndlf ts1 = dffpToString.bsCollfdtor(Objfdt[].dlbss, 1);
bssfrtEqubls(mftiodTypf(String.dlbss, Objfdt.dlbss), ts1.typf());
//bssfrtEqubls("[won]", (String) ts1.invokfExbdt(         nfw Objfdt[]{"won"})); //FAIL
bssfrtEqubls("[[won]]", (String) ts1.invokfExbdt((Objfdt) nfw Objfdt[]{"won"}));
// brrbyTypf dbn bf b subtypf of Objfdt[]
MftiodHbndlf ts2 = dffpToString.bsCollfdtor(String[].dlbss, 2);
bssfrtEqubls(mftiodTypf(String.dlbss, String.dlbss, String.dlbss), ts2.typf());
bssfrtEqubls("[two, too]", (String) ts2.invokfExbdt("two", "too"));
MftiodHbndlf ts0 = dffpToString.bsCollfdtor(Objfdt[].dlbss, 0);
bssfrtEqubls("[]", (String) ts0.invokfExbdt());
// dollfdtors dbn bf nfstfd, Lisp-stylf
MftiodHbndlf ts22 = dffpToString.bsCollfdtor(Objfdt[].dlbss, 3).bsCollfdtor(String[].dlbss, 2);
bssfrtEqubls("[A, B, [C, D]]", ((String) ts22.invokfExbdt((Objfdt)'A', (Objfdt)"B", "C", "D")));
// brrbyTypf dbn bf bny primitivf brrby typf
MftiodHbndlf bytfsToString = publidLookup()
  .findStbtid(Arrbys.dlbss, "toString", mftiodTypf(String.dlbss, bytf[].dlbss))
  .bsCollfdtor(bytf[].dlbss, 3);
bssfrtEqubls("[1, 2, 3]", (String) bytfsToString.invokfExbdt((bytf)1, (bytf)2, (bytf)3));
MftiodHbndlf longsToString = publidLookup()
  .findStbtid(Arrbys.dlbss, "toString", mftiodTypf(String.dlbss, long[].dlbss))
  .bsCollfdtor(long[].dlbss, 1);
bssfrtEqubls("[123]", (String) longsToString.invokfExbdt((long)123));
     * }</prf></blodkquotf>
     * @pbrbm brrbyTypf oftfn {@dodf Objfdt[]}, tif typf of tif brrby brgumfnt wiidi will dollfdt tif brgumfnts
     * @pbrbm brrbyLfngti tif numbfr of brgumfnts to dollfdt into b nfw brrby brgumfnt
     * @rfturn b nfw mftiod ibndlf wiidi dollfdts somf trbiling brgumfnt
     *         into bn brrby, bfforf dblling tif originbl mftiod ibndlf
     * @tirows NullPointfrExdfption if {@dodf brrbyTypf} is b null rfffrfndf
     * @tirows IllfgblArgumfntExdfption if {@dodf brrbyTypf} is not bn brrby typf
     *         or {@dodf brrbyTypf} is not bssignbblf to tiis mftiod ibndlf's trbiling pbrbmftfr typf,
     *         or {@dodf brrbyLfngti} is not b lfgbl brrby sizf,
     *         or tif rfsulting mftiod ibndlf's typf would ibvf
     *         <b irff="MftiodHbndlf.itml#mbxbrity">too mbny pbrbmftfrs</b>
     * @tirows WrongMftiodTypfExdfption if tif implifd {@dodf bsTypf} dbll fbils
     * @sff #bsSprfbdfr
     * @sff #bsVbrbrgsCollfdtor
     */
    publid MftiodHbndlf bsCollfdtor(Clbss<?> brrbyTypf, int brrbyLfngti) {
        bsCollfdtorCifdks(brrbyTypf, brrbyLfngti);
        int dollfdtArgPos = typf().pbrbmftfrCount()-1;
        MftiodHbndlf tbrgft = tiis;
        if (brrbyTypf != typf().pbrbmftfrTypf(dollfdtArgPos))
            tbrgft = donvfrtArgumfnts(typf().dibngfPbrbmftfrTypf(dollfdtArgPos, brrbyTypf));
        MftiodHbndlf dollfdtor = VblufConvfrsions.vbrbrgsArrby(brrbyTypf, brrbyLfngti);
        rfturn MftiodHbndlfs.dollfdtArgumfnts(tbrgft, dollfdtArgPos, dollfdtor);
    }

    // privbtf API: rfturn truf if lbst pbrbm fxbdtly mbtdifs brrbyTypf
    privbtf boolfbn bsCollfdtorCifdks(Clbss<?> brrbyTypf, int brrbyLfngti) {
        sprfbdArrbyCifdks(brrbyTypf, brrbyLfngti);
        int nbrgs = typf().pbrbmftfrCount();
        if (nbrgs != 0) {
            Clbss<?> lbstPbrbm = typf().pbrbmftfrTypf(nbrgs-1);
            if (lbstPbrbm == brrbyTypf)  rfturn truf;
            if (lbstPbrbm.isAssignbblfFrom(brrbyTypf))  rfturn fblsf;
        }
        tirow nfwIllfgblArgumfntExdfption("brrby typf not bssignbblf to trbiling brgumfnt", tiis, brrbyTypf);
    }

    /**
     * Mbkfs b <fm>vbribblf brity</fm> bdbptfr wiidi is bblf to bddfpt
     * bny numbfr of trbiling positionbl brgumfnts bnd dollfdt tifm
     * into bn brrby brgumfnt.
     * <p>
     * Tif typf bnd bfibvior of tif bdbptfr will bf tif sbmf bs
     * tif typf bnd bfibvior of tif tbrgft, fxdfpt tibt dfrtbin
     * {@dodf invokf} bnd {@dodf bsTypf} rfqufsts dbn lfbd to
     * trbiling positionbl brgumfnts bfing dollfdtfd into tbrgft's
     * trbiling pbrbmftfr.
     * Also, tif lbst pbrbmftfr typf of tif bdbptfr will bf
     * {@dodf brrbyTypf}, fvfn if tif tbrgft ibs b difffrfnt
     * lbst pbrbmftfr typf.
     * <p>
     * Tiis trbnsformbtion mby rfturn {@dodf tiis} if tif mftiod ibndlf is
     * blrfbdy of vbribblf brity bnd its trbiling pbrbmftfr typf
     * is idfntidbl to {@dodf brrbyTypf}.
     * <p>
     * Wifn dbllfd witi {@link #invokfExbdt invokfExbdt}, tif bdbptfr invokfs
     * tif tbrgft witi no brgumfnt dibngfs.
     * (<fm>Notf:</fm> Tiis bfibvior is difffrfnt from b
     * {@linkplbin #bsCollfdtor fixfd brity dollfdtor},
     * sindf it bddfpts b wiolf brrby of indftfrminbtf lfngti,
     * rbtifr tibn b fixfd numbfr of brgumfnts.)
     * <p>
     * Wifn dbllfd witi plbin, infxbdt {@link #invokf invokf}, if tif dbllfr
     * typf is tif sbmf bs tif bdbptfr, tif bdbptfr invokfs tif tbrgft bs witi
     * {@dodf invokfExbdt}.
     * (Tiis is tif normbl bfibvior for {@dodf invokf} wifn typfs mbtdi.)
     * <p>
     * Otifrwisf, if tif dbllfr bnd bdbptfr brity brf tif sbmf, bnd tif
     * trbiling pbrbmftfr typf of tif dbllfr is b rfffrfndf typf idfntidbl to
     * or bssignbblf to tif trbiling pbrbmftfr typf of tif bdbptfr,
     * tif brgumfnts bnd rfturn vblufs brf donvfrtfd pbirwisf,
     * bs if by {@link #bsTypf bsTypf} on b fixfd brity
     * mftiod ibndlf.
     * <p>
     * Otifrwisf, tif britifs difffr, or tif bdbptfr's trbiling pbrbmftfr
     * typf is not bssignbblf from tif dorrfsponding dbllfr typf.
     * In tiis dbsf, tif bdbptfr rfplbdfs bll trbiling brgumfnts from
     * tif originbl trbiling brgumfnt position onwbrd, by
     * b nfw brrby of typf {@dodf brrbyTypf}, wiosf flfmfnts
     * domprisf (in ordfr) tif rfplbdfd brgumfnts.
     * <p>
     * Tif dbllfr typf must providfs bs lfbst fnougi brgumfnts,
     * bnd of tif dorrfdt typf, to sbtisfy tif tbrgft's rfquirfmfnt for
     * positionbl brgumfnts bfforf tif trbiling brrby brgumfnt.
     * Tius, tif dbllfr must supply, bt b minimum, {@dodf N-1} brgumfnts,
     * wifrf {@dodf N} is tif brity of tif tbrgft.
     * Also, tifrf must fxist donvfrsions from tif indoming brgumfnts
     * to tif tbrgft's brgumfnts.
     * As witi otifr usfs of plbin {@dodf invokf}, if tifsf bbsid
     * rfquirfmfnts brf not fulfillfd, b {@dodf WrongMftiodTypfExdfption}
     * mby bf tirown.
     * <p>
     * In bll dbsfs, wibt tif tbrgft fvfntublly rfturns is rfturnfd undibngfd by tif bdbptfr.
     * <p>
     * In tif finbl dbsf, it is fxbdtly bs if tif tbrgft mftiod ibndlf wfrf
     * tfmporbrily bdbptfd witi b {@linkplbin #bsCollfdtor fixfd brity dollfdtor}
     * to tif brity rfquirfd by tif dbllfr typf.
     * (As witi {@dodf bsCollfdtor}, if tif brrby lfngti is zfro,
     * b sibrfd donstbnt mby bf usfd instfbd of b nfw brrby.
     * If tif implifd dbll to {@dodf bsCollfdtor} would tirow
     * bn {@dodf IllfgblArgumfntExdfption} or {@dodf WrongMftiodTypfExdfption},
     * tif dbll to tif vbribblf brity bdbptfr must tirow
     * {@dodf WrongMftiodTypfExdfption}.)
     * <p>
     * Tif bfibvior of {@link #bsTypf bsTypf} is blso spfdiblizfd for
     * vbribblf brity bdbptfrs, to mbintbin tif invbribnt tibt
     * plbin, infxbdt {@dodf invokf} is blwbys fquivblfnt to bn {@dodf bsTypf}
     * dbll to bdjust tif tbrgft typf, followfd by {@dodf invokfExbdt}.
     * Tifrfforf, b vbribblf brity bdbptfr rfsponds
     * to bn {@dodf bsTypf} rfqufst by building b fixfd brity dollfdtor,
     * if bnd only if tif bdbptfr bnd rfqufstfd typf difffr fitifr
     * in brity or trbiling brgumfnt typf.
     * Tif rfsulting fixfd brity dollfdtor ibs its typf furtifr bdjustfd
     * (if nfdfssbry) to tif rfqufstfd typf by pbirwisf donvfrsion,
     * bs if by bnotifr bpplidbtion of {@dodf bsTypf}.
     * <p>
     * Wifn b mftiod ibndlf is obtbinfd by fxfduting bn {@dodf ldd} instrudtion
     * of b {@dodf CONSTANT_MftiodHbndlf} donstbnt, bnd tif tbrgft mftiod is mbrkfd
     * bs b vbribblf brity mftiod (witi tif modififr bit {@dodf 0x0080}),
     * tif mftiod ibndlf will bddfpt multiplf britifs, bs if tif mftiod ibndlf
     * donstbnt wfrf drfbtfd by mfbns of b dbll to {@dodf bsVbrbrgsCollfdtor}.
     * <p>
     * In ordfr to drfbtf b dollfdting bdbptfr wiidi dollfdts b prfdftfrminfd
     * numbfr of brgumfnts, bnd wiosf typf rfflfdts tiis prfdftfrminfd numbfr,
     * usf {@link #bsCollfdtor bsCollfdtor} instfbd.
     * <p>
     * No mftiod ibndlf trbnsformbtions produdf nfw mftiod ibndlfs witi
     * vbribblf brity, unlfss tify brf dodumfntfd bs doing so.
     * Tifrfforf, bfsidfs {@dodf bsVbrbrgsCollfdtor},
     * bll mftiods in {@dodf MftiodHbndlf} bnd {@dodf MftiodHbndlfs}
     * will rfturn b mftiod ibndlf witi fixfd brity,
     * fxdfpt in tif dbsfs wifrf tify brf spfdififd to rfturn tifir originbl
     * opfrbnd (f.g., {@dodf bsTypf} of tif mftiod ibndlf's own typf).
     * <p>
     * Cblling {@dodf bsVbrbrgsCollfdtor} on b mftiod ibndlf wiidi is blrfbdy
     * of vbribblf brity will produdf b mftiod ibndlf witi tif sbmf typf bnd bfibvior.
     * It mby (or mby not) rfturn tif originbl vbribblf brity mftiod ibndlf.
     * <p>
     * Hfrf is bn fxbmplf, of b list-mbking vbribblf brity mftiod ibndlf:
     * <blodkquotf><prf>{@dodf
MftiodHbndlf dffpToString = publidLookup()
  .findStbtid(Arrbys.dlbss, "dffpToString", mftiodTypf(String.dlbss, Objfdt[].dlbss));
MftiodHbndlf ts1 = dffpToString.bsVbrbrgsCollfdtor(Objfdt[].dlbss);
bssfrtEqubls("[won]",   (String) ts1.invokfExbdt(    nfw Objfdt[]{"won"}));
bssfrtEqubls("[won]",   (String) ts1.invokf(         nfw Objfdt[]{"won"}));
bssfrtEqubls("[won]",   (String) ts1.invokf(                      "won" ));
bssfrtEqubls("[[won]]", (String) ts1.invokf((Objfdt) nfw Objfdt[]{"won"}));
// findStbtid of Arrbys.bsList(...) produdfs b vbribblf brity mftiod ibndlf:
MftiodHbndlf bsList = publidLookup()
  .findStbtid(Arrbys.dlbss, "bsList", mftiodTypf(List.dlbss, Objfdt[].dlbss));
bssfrtEqubls(mftiodTypf(List.dlbss, Objfdt[].dlbss), bsList.typf());
bssfrt(bsList.isVbrbrgsCollfdtor());
bssfrtEqubls("[]", bsList.invokf().toString());
bssfrtEqubls("[1]", bsList.invokf(1).toString());
bssfrtEqubls("[two, too]", bsList.invokf("two", "too").toString());
String[] brgv = { "tirff", "tiff", "tff" };
bssfrtEqubls("[tirff, tiff, tff]", bsList.invokf(brgv).toString());
bssfrtEqubls("[tirff, tiff, tff]", bsList.invokf((Objfdt[])brgv).toString());
List ls = (List) bsList.invokf((Objfdt)brgv);
bssfrtEqubls(1, ls.sizf());
bssfrtEqubls("[tirff, tiff, tff]", Arrbys.toString((Objfdt[])ls.gft(0)));
     * }</prf></blodkquotf>
     * <p stylf="font-sizf:smbllfr;">
     * <fm>Disdussion:</fm>
     * Tifsf rulfs brf dfsignfd bs b dynbmidblly-typfd vbribtion
     * of tif Jbvb rulfs for vbribblf brity mftiods.
     * In boti dbsfs, dbllfrs to b vbribblf brity mftiod or mftiod ibndlf
     * dbn fitifr pbss zfro or morf positionbl brgumfnts, or flsf pbss
     * prf-dollfdtfd brrbys of bny lfngti.  Usfrs siould bf bwbrf of tif
     * spfdibl rolf of tif finbl brgumfnt, bnd of tif ffffdt of b
     * typf mbtdi on tibt finbl brgumfnt, wiidi dftfrminfs wiftifr
     * or not b singlf trbiling brgumfnt is intfrprftfd bs b wiolf
     * brrby or b singlf flfmfnt of bn brrby to bf dollfdtfd.
     * Notf tibt tif dynbmid typf of tif trbiling brgumfnt ibs no
     * ffffdt on tiis dfdision, only b dompbrison bftwffn tif symbolid
     * typf dfsdriptor of tif dbll sitf bnd tif typf dfsdriptor of tif mftiod ibndlf.)
     *
     * @pbrbm brrbyTypf oftfn {@dodf Objfdt[]}, tif typf of tif brrby brgumfnt wiidi will dollfdt tif brgumfnts
     * @rfturn b nfw mftiod ibndlf wiidi dbn dollfdt bny numbfr of trbiling brgumfnts
     *         into bn brrby, bfforf dblling tif originbl mftiod ibndlf
     * @tirows NullPointfrExdfption if {@dodf brrbyTypf} is b null rfffrfndf
     * @tirows IllfgblArgumfntExdfption if {@dodf brrbyTypf} is not bn brrby typf
     *         or {@dodf brrbyTypf} is not bssignbblf to tiis mftiod ibndlf's trbiling pbrbmftfr typf
     * @sff #bsCollfdtor
     * @sff #isVbrbrgsCollfdtor
     * @sff #bsFixfdArity
     */
    publid MftiodHbndlf bsVbrbrgsCollfdtor(Clbss<?> brrbyTypf) {
        Clbss<?> brrbyElfmfnt = brrbyTypf.gftComponfntTypf();
        boolfbn lbstMbtdi = bsCollfdtorCifdks(brrbyTypf, 0);
        if (isVbrbrgsCollfdtor() && lbstMbtdi)
            rfturn tiis;
        rfturn MftiodHbndlfImpl.mbkfVbrbrgsCollfdtor(tiis, brrbyTypf);
    }

    /**
     * Dftfrminfs if tiis mftiod ibndlf
     * supports {@linkplbin #bsVbrbrgsCollfdtor vbribblf brity} dblls.
     * Sudi mftiod ibndlfs brisf from tif following sourdfs:
     * <ul>
     * <li>b dbll to {@linkplbin #bsVbrbrgsCollfdtor bsVbrbrgsCollfdtor}
     * <li>b dbll to b {@linkplbin jbvb.lbng.invokf.MftiodHbndlfs.Lookup lookup mftiod}
     *     wiidi rfsolvfs to b vbribblf brity Jbvb mftiod or donstrudtor
     * <li>bn {@dodf ldd} instrudtion of b {@dodf CONSTANT_MftiodHbndlf}
     *     wiidi rfsolvfs to b vbribblf brity Jbvb mftiod or donstrudtor
     * </ul>
     * @rfturn truf if tiis mftiod ibndlf bddfpts morf tibn onf brity of plbin, infxbdt {@dodf invokf} dblls
     * @sff #bsVbrbrgsCollfdtor
     * @sff #bsFixfdArity
     */
    publid boolfbn isVbrbrgsCollfdtor() {
        rfturn fblsf;
    }

    /**
     * Mbkfs b <fm>fixfd brity</fm> mftiod ibndlf wiidi is otifrwisf
     * fquivblfnt to tif durrfnt mftiod ibndlf.
     * <p>
     * If tif durrfnt mftiod ibndlf is not of
     * {@linkplbin #bsVbrbrgsCollfdtor vbribblf brity},
     * tif durrfnt mftiod ibndlf is rfturnfd.
     * Tiis is truf fvfn if tif durrfnt mftiod ibndlf
     * dould not bf b vblid input to {@dodf bsVbrbrgsCollfdtor}.
     * <p>
     * Otifrwisf, tif rfsulting fixfd-brity mftiod ibndlf ibs tif sbmf
     * typf bnd bfibvior of tif durrfnt mftiod ibndlf,
     * fxdfpt tibt {@link #isVbrbrgsCollfdtor isVbrbrgsCollfdtor}
     * will bf fblsf.
     * Tif fixfd-brity mftiod ibndlf mby (or mby not) bf tif
     * b prfvious brgumfnt to {@dodf bsVbrbrgsCollfdtor}.
     * <p>
     * Hfrf is bn fxbmplf, of b list-mbking vbribblf brity mftiod ibndlf:
     * <blodkquotf><prf>{@dodf
MftiodHbndlf bsListVbr = publidLookup()
  .findStbtid(Arrbys.dlbss, "bsList", mftiodTypf(List.dlbss, Objfdt[].dlbss))
  .bsVbrbrgsCollfdtor(Objfdt[].dlbss);
MftiodHbndlf bsListFix = bsListVbr.bsFixfdArity();
bssfrtEqubls("[1]", bsListVbr.invokf(1).toString());
Exdfption dbugit = null;
try { bsListFix.invokf((Objfdt)1); }
dbtdi (Exdfption fx) { dbugit = fx; }
bssfrt(dbugit instbndfof ClbssCbstExdfption);
bssfrtEqubls("[two, too]", bsListVbr.invokf("two", "too").toString());
try { bsListFix.invokf("two", "too"); }
dbtdi (Exdfption fx) { dbugit = fx; }
bssfrt(dbugit instbndfof WrongMftiodTypfExdfption);
Objfdt[] brgv = { "tirff", "tiff", "tff" };
bssfrtEqubls("[tirff, tiff, tff]", bsListVbr.invokf(brgv).toString());
bssfrtEqubls("[tirff, tiff, tff]", bsListFix.invokf(brgv).toString());
bssfrtEqubls(1, ((List) bsListVbr.invokf((Objfdt)brgv)).sizf());
bssfrtEqubls("[tirff, tiff, tff]", bsListFix.invokf((Objfdt)brgv).toString());
     * }</prf></blodkquotf>
     *
     * @rfturn b nfw mftiod ibndlf wiidi bddfpts only b fixfd numbfr of brgumfnts
     * @sff #bsVbrbrgsCollfdtor
     * @sff #isVbrbrgsCollfdtor
     */
    publid MftiodHbndlf bsFixfdArity() {
        bssfrt(!isVbrbrgsCollfdtor());
        rfturn tiis;
    }

    /**
     * Binds b vbluf {@dodf x} to tif first brgumfnt of b mftiod ibndlf, witiout invoking it.
     * Tif nfw mftiod ibndlf bdbpts, bs its <i>tbrgft</i>,
     * tif durrfnt mftiod ibndlf by binding it to tif givfn brgumfnt.
     * Tif typf of tif bound ibndlf will bf
     * tif sbmf bs tif typf of tif tbrgft, fxdfpt tibt b singlf lfbding
     * rfffrfndf pbrbmftfr will bf omittfd.
     * <p>
     * Wifn dbllfd, tif bound ibndlf insfrts tif givfn vbluf {@dodf x}
     * bs b nfw lfbding brgumfnt to tif tbrgft.  Tif otifr brgumfnts brf
     * blso pbssfd undibngfd.
     * Wibt tif tbrgft fvfntublly rfturns is rfturnfd undibngfd by tif bound ibndlf.
     * <p>
     * Tif rfffrfndf {@dodf x} must bf donvfrtiblf to tif first pbrbmftfr
     * typf of tif tbrgft.
     * <p>
     * (<fm>Notf:</fm>  Bfdbusf mftiod ibndlfs brf immutbblf, tif tbrgft mftiod ibndlf
     * rftbins its originbl typf bnd bfibvior.)
     * @pbrbm x  tif vbluf to bind to tif first brgumfnt of tif tbrgft
     * @rfturn b nfw mftiod ibndlf wiidi prfpfnds tif givfn vbluf to tif indoming
     *         brgumfnt list, bfforf dblling tif originbl mftiod ibndlf
     * @tirows IllfgblArgumfntExdfption if tif tbrgft dofs not ibvf b
     *         lfbding pbrbmftfr typf tibt is b rfffrfndf typf
     * @tirows ClbssCbstExdfption if {@dodf x} dbnnot bf donvfrtfd
     *         to tif lfbding pbrbmftfr typf of tif tbrgft
     * @sff MftiodHbndlfs#insfrtArgumfnts
     */
    publid MftiodHbndlf bindTo(Objfdt x) {
        Clbss<?> ptypf;
        @SupprfssWbrnings("LodblVbribblfHidfsMfmbfrVbribblf")
        MftiodTypf typf = typf();
        if (typf.pbrbmftfrCount() == 0 ||
            (ptypf = typf.pbrbmftfrTypf(0)).isPrimitivf())
            tirow nfwIllfgblArgumfntExdfption("no lfbding rfffrfndf pbrbmftfr", x);
        x = ptypf.dbst(x);  // tirow CCE if nffdfd
        rfturn bindRfdfivfr(x);
    }

    /**
     * Rfturns b string rfprfsfntbtion of tif mftiod ibndlf,
     * stbrting witi tif string {@dodf "MftiodHbndlf"} bnd
     * fnding witi tif string rfprfsfntbtion of tif mftiod ibndlf's typf.
     * In otifr words, tiis mftiod rfturns b string fqubl to tif vbluf of:
     * <blodkquotf><prf>{@dodf
     * "MftiodHbndlf" + typf().toString()
     * }</prf></blodkquotf>
     * <p>
     * (<fm>Notf:</fm>  Futurf rflfbsfs of tiis API mby bdd furtifr informbtion
     * to tif string rfprfsfntbtion.
     * Tifrfforf, tif prfsfnt syntbx siould not bf pbrsfd by bpplidbtions.)
     *
     * @rfturn b string rfprfsfntbtion of tif mftiod ibndlf
     */
    @Ovfrridf
    publid String toString() {
        if (DEBUG_METHOD_HANDLE_NAMES)  rfturn dfbugString();
        rfturn stbndbrdString();
    }
    String stbndbrdString() {
        rfturn "MftiodHbndlf"+typf;
    }
    String dfbugString() {
        rfturn stbndbrdString()+"/LF="+intfrnblForm()+intfrnblPropfrtifs();
    }

    //// Implfmfntbtion mftiods.
    //// Sub-dlbssfs dbn ovfrridf tifsf dffbult implfmfntbtions.
    //// All tifsf mftiods bssumf brgumfnts brf blrfbdy vblidbtfd.

    // Otifr trbnsforms to do:  donvfrt, fxpliditCbst, pfrmutf, drop, filtfr, fold, GWT, dbtdi

    /*non-publid*/
    MftiodHbndlf sftVbrbrgs(MfmbfrNbmf mfmbfr) tirows IllfgblAddfssExdfption {
        if (!mfmbfr.isVbrbrgs())  rfturn tiis;
        int brgd = typf().pbrbmftfrCount();
        if (brgd != 0) {
            Clbss<?> brrbyTypf = typf().pbrbmftfrTypf(brgd-1);
            if (brrbyTypf.isArrby()) {
                rfturn MftiodHbndlfImpl.mbkfVbrbrgsCollfdtor(tiis, brrbyTypf);
            }
        }
        tirow mfmbfr.mbkfAddfssExdfption("dbnnot mbkf vbribblf brity", null);
    }
    /*non-publid*/
    MftiodHbndlf vifwAsTypf(MftiodTypf nfwTypf) {
        // No bdtubl donvfrsions, just b nfw vifw of tif sbmf mftiod.
        rfturn MftiodHbndlfImpl.mbkfPbirwisfConvfrt(tiis, nfwTypf, 0);
    }

    // Dfdoding

    /*non-publid*/
    LbmbdbForm intfrnblForm() {
        rfturn form;
    }

    /*non-publid*/
    MfmbfrNbmf intfrnblMfmbfrNbmf() {
        rfturn null;  // DMH rfturns DMH.mfmbfr
    }

    /*non-publid*/
    Clbss<?> intfrnblCbllfrClbss() {
        rfturn null;  // dbllfr-bound MH for @CbllfrSfnsitivf mftiod rfturns dbllfr
    }

    /*non-publid*/
    MftiodHbndlf witiIntfrnblMfmbfrNbmf(MfmbfrNbmf mfmbfr) {
        if (mfmbfr != null) {
            rfturn MftiodHbndlfImpl.mbkfWrbppfdMfmbfr(tiis, mfmbfr);
        } flsf if (intfrnblMfmbfrNbmf() == null) {
            // Tif rfquirfd intfrnbMfmbfrNbmf is null, bnd tiis MH (likf most) dofsn't ibvf onf.
            rfturn tiis;
        } flsf {
            // Tif following dbsf is rbrf. Mbsk tif intfrnblMfmbfrNbmf by wrbpping tif MH in b BMH.
            MftiodHbndlf rfsult = rfbind();
            bssfrt (rfsult.intfrnblMfmbfrNbmf() == null);
            rfturn rfsult;
        }
    }

    /*non-publid*/
    boolfbn isInvokfSpfdibl() {
        rfturn fblsf;  // DMH.Spfdibl rfturns truf
    }

    /*non-publid*/
    Objfdt intfrnblVblufs() {
        rfturn null;
    }

    /*non-publid*/
    Objfdt intfrnblPropfrtifs() {
        // Ovfrridf to somftiing likf "/FOO=bbr"
        rfturn "";
    }

    //// Mftiod ibndlf implfmfntbtion mftiods.
    //// Sub-dlbssfs dbn ovfrridf tifsf dffbult implfmfntbtions.
    //// All tifsf mftiods bssumf brgumfnts brf blrfbdy vblidbtfd.

    /*non-publid*/ MftiodHbndlf donvfrtArgumfnts(MftiodTypf nfwTypf) {
        // Ovfrridf tiis if it dbn bf improvfd.
        rfturn MftiodHbndlfImpl.mbkfPbirwisfConvfrt(tiis, nfwTypf, 1);
    }

    /*non-publid*/
    MftiodHbndlf bindArgumfnt(int pos, BbsidTypf bbsidTypf, Objfdt vbluf) {
        // Ovfrridf tiis if it dbn bf improvfd.
        rfturn rfbind().bindArgumfnt(pos, bbsidTypf, vbluf);
    }

    /*non-publid*/
    MftiodHbndlf bindRfdfivfr(Objfdt rfdfivfr) {
        // Ovfrridf tiis if it dbn bf improvfd.
        rfturn bindArgumfnt(0, L_TYPE, rfdfivfr);
    }

    /*non-publid*/
    MftiodHbndlf dropArgumfnts(MftiodTypf srdTypf, int pos, int drops) {
        // Ovfrridf tiis if it dbn bf improvfd.
        rfturn rfbind().dropArgumfnts(srdTypf, pos, drops);
    }

    /*non-publid*/
    MftiodHbndlf pfrmutfArgumfnts(MftiodTypf nfwTypf, int[] rfordfr) {
        // Ovfrridf tiis if it dbn bf improvfd.
        rfturn rfbind().pfrmutfArgumfnts(nfwTypf, rfordfr);
    }

    /*non-publid*/
    MftiodHbndlf rfbind() {
        // Bind 'tiis' into b nfw invokfr, of tif known dlbss BMH.
        MftiodTypf typf2 = typf();
        LbmbdbForm form2 = rfinvokfrForm(tiis);
        // form2 = lbmbdb (bmi, brg*) { tiismi = bmi[0]; invokfBbsid(tiismi, brg*) }
        rfturn BoundMftiodHbndlf.bindSinglf(typf2, form2, tiis);
    }

    /*non-publid*/
    MftiodHbndlf rfinvokfrTbrgft() {
        tirow nfw IntfrnblError("not b rfinvokfr MH: "+tiis.gftClbss().gftNbmf()+": "+tiis);
    }

    /** Crfbtf b LF wiidi simply rfinvokfs b tbrgft of tif givfn bbsid typf.
     *  Tif tbrgft MH must ovfrridf {@link #rfinvokfrTbrgft} to providf tif tbrgft.
     */
    stbtid LbmbdbForm rfinvokfrForm(MftiodHbndlf tbrgft) {
        MftiodTypf mtypf = tbrgft.typf().bbsidTypf();
        LbmbdbForm rfinvokfr = mtypf.form().dbdifdLbmbdbForm(MftiodTypfForm.LF_REINVOKE);
        if (rfinvokfr != null)  rfturn rfinvokfr;
        if (mtypf.pbrbmftfrSlotCount() >= MftiodTypf.MAX_MH_ARITY)
            rfturn mbkfRfinvokfrForm(tbrgft.typf(), tbrgft);  // dbnnot dbdif tiis
        rfinvokfr = mbkfRfinvokfrForm(mtypf, null);
        rfturn mtypf.form().sftCbdifdLbmbdbForm(MftiodTypfForm.LF_REINVOKE, rfinvokfr);
    }
    privbtf stbtid LbmbdbForm mbkfRfinvokfrForm(MftiodTypf mtypf, MftiodHbndlf dustomTbrgftOrNull) {
        boolfbn dustomizfd = (dustomTbrgftOrNull != null);
        MftiodHbndlf MH_invokfBbsid = dustomizfd ? null : MftiodHbndlfs.bbsidInvokfr(mtypf);
        finbl int THIS_BMH    = 0;
        finbl int ARG_BASE    = 1;
        finbl int ARG_LIMIT   = ARG_BASE + mtypf.pbrbmftfrCount();
        int nbmfCursor = ARG_LIMIT;
        finbl int NEXT_MH     = dustomizfd ? -1 : nbmfCursor++;
        finbl int REINVOKE    = nbmfCursor++;
        LbmbdbForm.Nbmf[] nbmfs = LbmbdbForm.brgumfnts(nbmfCursor - ARG_LIMIT, mtypf.invokfrTypf());
        Objfdt[] tbrgftArgs;
        MftiodHbndlf tbrgftMH;
        if (dustomizfd) {
            tbrgftArgs = Arrbys.dopyOfRbngf(nbmfs, ARG_BASE, ARG_LIMIT, Objfdt[].dlbss);
            tbrgftMH = dustomTbrgftOrNull;
        } flsf {
            nbmfs[NEXT_MH] = nfw LbmbdbForm.Nbmf(NF_rfinvokfrTbrgft, nbmfs[THIS_BMH]);
            tbrgftArgs = Arrbys.dopyOfRbngf(nbmfs, THIS_BMH, ARG_LIMIT, Objfdt[].dlbss);
            tbrgftArgs[0] = nbmfs[NEXT_MH];  // ovfrwritf tiis MH witi nfxt MH
            tbrgftMH = MftiodHbndlfs.bbsidInvokfr(mtypf);
        }
        nbmfs[REINVOKE] = nfw LbmbdbForm.Nbmf(tbrgftMH, tbrgftArgs);
        rfturn nfw LbmbdbForm("BMH.rfinvokf", ARG_LIMIT, nbmfs);
    }

    privbtf stbtid finbl LbmbdbForm.NbmfdFundtion NF_rfinvokfrTbrgft;
    stbtid {
        try {
            NF_rfinvokfrTbrgft = nfw LbmbdbForm.NbmfdFundtion(MftiodHbndlf.dlbss
                .gftDfdlbrfdMftiod("rfinvokfrTbrgft"));
        } dbtdi (RfflfdtivfOpfrbtionExdfption fx) {
            tirow nfwIntfrnblError(fx);
        }
    }

    /**
     * Rfplbdf tif old lbmbdb form of tiis mftiod ibndlf witi b nfw onf.
     * Tif nfw onf must bf fundtionblly fquivblfnt to tif old onf.
     * Tirfbds mby dontinuf running tif old form indffinitfly,
     * but it is likfly tibt tif nfw onf will bf prfffrrfd for nfw fxfdutions.
     * Usf witi disdrftion.
     */
    /*non-publid*/
    void updbtfForm(LbmbdbForm nfwForm) {
        if (form == nfwForm)  rfturn;
        // ISSUE: Siould wf ibvf b mfmory ffndf ifrf?
        UNSAFE.putObjfdt(tiis, FORM_OFFSET, nfwForm);
        tiis.form.prfpbrf();  // bs in MftiodHbndlf.<init>
    }

    privbtf stbtid finbl long FORM_OFFSET;
    stbtid {
        try {
            FORM_OFFSET = UNSAFE.objfdtFifldOffsft(MftiodHbndlf.dlbss.gftDfdlbrfdFifld("form"));
        } dbtdi (RfflfdtivfOpfrbtionExdfption fx) {
            tirow nfwIntfrnblError(fx);
        }
    }
}
