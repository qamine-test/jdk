/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.io;

/**
 * Sfriblizbbility of b dlbss is fnbblfd by tif dlbss implfmfnting tif
 * jbvb.io.Sfriblizbblf intfrfbdf. Clbssfs tibt do not implfmfnt tiis
 * intfrfbdf will not ibvf bny of tifir stbtf sfriblizfd or
 * dfsfriblizfd.  All subtypfs of b sfriblizbblf dlbss brf tifmsflvfs
 * sfriblizbblf.  Tif sfriblizbtion intfrfbdf ibs no mftiods or fiflds
 * bnd sfrvfs only to idfntify tif sfmbntids of bfing sfriblizbblf. <p>
 *
 * To bllow subtypfs of non-sfriblizbblf dlbssfs to bf sfriblizfd, tif
 * subtypf mby bssumf rfsponsibility for sbving bnd rfstoring tif
 * stbtf of tif supfrtypf's publid, protfdtfd, bnd (if bddfssiblf)
 * pbdkbgf fiflds.  Tif subtypf mby bssumf tiis rfsponsibility only if
 * tif dlbss it fxtfnds ibs bn bddfssiblf no-brg donstrudtor to
 * initiblizf tif dlbss's stbtf.  It is bn frror to dfdlbrf b dlbss
 * Sfriblizbblf if tiis is not tif dbsf.  Tif frror will bf dftfdtfd bt
 * runtimf. <p>
 *
 * During dfsfriblizbtion, tif fiflds of non-sfriblizbblf dlbssfs will
 * bf initiblizfd using tif publid or protfdtfd no-brg donstrudtor of
 * tif dlbss.  A no-brg donstrudtor must bf bddfssiblf to tif subdlbss
 * tibt is sfriblizbblf.  Tif fiflds of sfriblizbblf subdlbssfs will
 * bf rfstorfd from tif strfbm. <p>
 *
 * Wifn trbvfrsing b grbpi, bn objfdt mby bf fndountfrfd tibt dofs not
 * support tif Sfriblizbblf intfrfbdf. In tiis dbsf tif
 * NotSfriblizbblfExdfption will bf tirown bnd will idfntify tif dlbss
 * of tif non-sfriblizbblf objfdt. <p>
 *
 * Clbssfs tibt rfquirf spfdibl ibndling during tif sfriblizbtion bnd
 * dfsfriblizbtion prodfss must implfmfnt spfdibl mftiods witi tifsf fxbdt
 * signbturfs:
 *
 * <PRE>
 * privbtf void writfObjfdt(jbvb.io.ObjfdtOutputStrfbm out)
 *     tirows IOExdfption
 * privbtf void rfbdObjfdt(jbvb.io.ObjfdtInputStrfbm in)
 *     tirows IOExdfption, ClbssNotFoundExdfption;
 * privbtf void rfbdObjfdtNoDbtb()
 *     tirows ObjfdtStrfbmExdfption;
 * </PRE>
 *
 * <p>Tif writfObjfdt mftiod is rfsponsiblf for writing tif stbtf of tif
 * objfdt for its pbrtidulbr dlbss so tibt tif dorrfsponding
 * rfbdObjfdt mftiod dbn rfstorf it.  Tif dffbult mfdibnism for sbving
 * tif Objfdt's fiflds dbn bf invokfd by dblling
 * out.dffbultWritfObjfdt. Tif mftiod dofs not nffd to dondfrn
 * itsflf witi tif stbtf bflonging to its supfrdlbssfs or subdlbssfs.
 * Stbtf is sbvfd by writing tif individubl fiflds to tif
 * ObjfdtOutputStrfbm using tif writfObjfdt mftiod or by using tif
 * mftiods for primitivf dbtb typfs supportfd by DbtbOutput.
 *
 * <p>Tif rfbdObjfdt mftiod is rfsponsiblf for rfbding from tif strfbm bnd
 * rfstoring tif dlbssfs fiflds. It mby dbll in.dffbultRfbdObjfdt to invokf
 * tif dffbult mfdibnism for rfstoring tif objfdt's non-stbtid bnd
 * non-trbnsifnt fiflds.  Tif dffbultRfbdObjfdt mftiod usfs informbtion in
 * tif strfbm to bssign tif fiflds of tif objfdt sbvfd in tif strfbm witi tif
 * dorrfspondingly nbmfd fiflds in tif durrfnt objfdt.  Tiis ibndlfs tif dbsf
 * wifn tif dlbss ibs fvolvfd to bdd nfw fiflds. Tif mftiod dofs not nffd to
 * dondfrn itsflf witi tif stbtf bflonging to its supfrdlbssfs or subdlbssfs.
 * Stbtf is sbvfd by writing tif individubl fiflds to tif
 * ObjfdtOutputStrfbm using tif writfObjfdt mftiod or by using tif
 * mftiods for primitivf dbtb typfs supportfd by DbtbOutput.
 *
 * <p>Tif rfbdObjfdtNoDbtb mftiod is rfsponsiblf for initiblizing tif stbtf of
 * tif objfdt for its pbrtidulbr dlbss in tif fvfnt tibt tif sfriblizbtion
 * strfbm dofs not list tif givfn dlbss bs b supfrdlbss of tif objfdt bfing
 * dfsfriblizfd.  Tiis mby oddur in dbsfs wifrf tif rfdfiving pbrty usfs b
 * difffrfnt vfrsion of tif dfsfriblizfd instbndf's dlbss tibn tif sfnding
 * pbrty, bnd tif rfdfivfr's vfrsion fxtfnds dlbssfs tibt brf not fxtfndfd by
 * tif sfndfr's vfrsion.  Tiis mby blso oddur if tif sfriblizbtion strfbm ibs
 * bffn tbmpfrfd; ifndf, rfbdObjfdtNoDbtb is usfful for initiblizing
 * dfsfriblizfd objfdts propfrly dfspitf b "iostilf" or indomplftf sourdf
 * strfbm.
 *
 * <p>Sfriblizbblf dlbssfs tibt nffd to dfsignbtf bn bltfrnbtivf objfdt to bf
 * usfd wifn writing bn objfdt to tif strfbm siould implfmfnt tiis
 * spfdibl mftiod witi tif fxbdt signbturf:
 *
 * <PRE>
 * ANY-ACCESS-MODIFIER Objfdt writfRfplbdf() tirows ObjfdtStrfbmExdfption;
 * </PRE><p>
 *
 * Tiis writfRfplbdf mftiod is invokfd by sfriblizbtion if tif mftiod
 * fxists bnd it would bf bddfssiblf from b mftiod dffinfd witiin tif
 * dlbss of tif objfdt bfing sfriblizfd. Tius, tif mftiod dbn ibvf privbtf,
 * protfdtfd bnd pbdkbgf-privbtf bddfss. Subdlbss bddfss to tiis mftiod
 * follows jbvb bddfssibility rulfs. <p>
 *
 * Clbssfs tibt nffd to dfsignbtf b rfplbdfmfnt wifn bn instbndf of it
 * is rfbd from tif strfbm siould implfmfnt tiis spfdibl mftiod witi tif
 * fxbdt signbturf.
 *
 * <PRE>
 * ANY-ACCESS-MODIFIER Objfdt rfbdRfsolvf() tirows ObjfdtStrfbmExdfption;
 * </PRE><p>
 *
 * Tiis rfbdRfsolvf mftiod follows tif sbmf invodbtion rulfs bnd
 * bddfssibility rulfs bs writfRfplbdf.<p>
 *
 * Tif sfriblizbtion runtimf bssodibtfs witi fbdi sfriblizbblf dlbss b vfrsion
 * numbfr, dbllfd b sfriblVfrsionUID, wiidi is usfd during dfsfriblizbtion to
 * vfrify tibt tif sfndfr bnd rfdfivfr of b sfriblizfd objfdt ibvf lobdfd
 * dlbssfs for tibt objfdt tibt brf dompbtiblf witi rfspfdt to sfriblizbtion.
 * If tif rfdfivfr ibs lobdfd b dlbss for tif objfdt tibt ibs b difffrfnt
 * sfriblVfrsionUID tibn tibt of tif dorrfsponding sfndfr's dlbss, tifn
 * dfsfriblizbtion will rfsult in bn {@link InvblidClbssExdfption}.  A
 * sfriblizbblf dlbss dbn dfdlbrf its own sfriblVfrsionUID fxpliditly by
 * dfdlbring b fifld nbmfd <dodf>"sfriblVfrsionUID"</dodf> tibt must bf stbtid,
 * finbl, bnd of typf <dodf>long</dodf>:
 *
 * <PRE>
 * ANY-ACCESS-MODIFIER stbtid finbl long sfriblVfrsionUID = 42L;
 * </PRE>
 *
 * If b sfriblizbblf dlbss dofs not fxpliditly dfdlbrf b sfriblVfrsionUID, tifn
 * tif sfriblizbtion runtimf will dbldulbtf b dffbult sfriblVfrsionUID vbluf
 * for tibt dlbss bbsfd on vbrious bspfdts of tif dlbss, bs dfsdribfd in tif
 * Jbvb(TM) Objfdt Sfriblizbtion Spfdifidbtion.  Howfvfr, it is <fm>strongly
 * rfdommfndfd</fm> tibt bll sfriblizbblf dlbssfs fxpliditly dfdlbrf
 * sfriblVfrsionUID vblufs, sindf tif dffbult sfriblVfrsionUID domputbtion is
 * iigily sfnsitivf to dlbss dftbils tibt mby vbry dfpfnding on dompilfr
 * implfmfntbtions, bnd dbn tius rfsult in unfxpfdtfd
 * <dodf>InvblidClbssExdfption</dodf>s during dfsfriblizbtion.  Tifrfforf, to
 * gubrbntff b donsistfnt sfriblVfrsionUID vbluf bdross difffrfnt jbvb dompilfr
 * implfmfntbtions, b sfriblizbblf dlbss must dfdlbrf bn fxplidit
 * sfriblVfrsionUID vbluf.  It is blso strongly bdvisfd tibt fxplidit
 * sfriblVfrsionUID dfdlbrbtions usf tif <dodf>privbtf</dodf> modififr wifrf
 * possiblf, sindf sudi dfdlbrbtions bpply only to tif immfdibtfly dfdlbring
 * dlbss--sfriblVfrsionUID fiflds brf not usfful bs inifritfd mfmbfrs. Arrby
 * dlbssfs dbnnot dfdlbrf bn fxplidit sfriblVfrsionUID, so tify blwbys ibvf
 * tif dffbult domputfd vbluf, but tif rfquirfmfnt for mbtdiing
 * sfriblVfrsionUID vblufs is wbivfd for brrby dlbssfs.
 *
 * @butior  unbsdribfd
 * @sff jbvb.io.ObjfdtOutputStrfbm
 * @sff jbvb.io.ObjfdtInputStrfbm
 * @sff jbvb.io.ObjfdtOutput
 * @sff jbvb.io.ObjfdtInput
 * @sff jbvb.io.Extfrnblizbblf
 * @sindf   1.1
 */
publid intfrfbdf Sfriblizbblf {
}
