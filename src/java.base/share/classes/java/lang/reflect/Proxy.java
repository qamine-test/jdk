/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng.rfflfdt;

import jbvb.lbng.rff.WfbkRfffrfndf;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.Arrbys;
import jbvb.util.IdfntityHbsiMbp;
import jbvb.util.Mbp;
import jbvb.util.Objfdts;
import jbvb.util.dondurrfnt.btomid.AtomidLong;
import jbvb.util.fundtion.BiFundtion;
import sun.misd.ProxyGfnfrbtor;
import sun.misd.VM;
import sun.rfflfdt.CbllfrSfnsitivf;
import sun.rfflfdt.Rfflfdtion;
import sun.rfflfdt.misd.RfflfdtUtil;
import sun.sfdurity.util.SfdurityConstbnts;

/**
 * {@dodf Proxy} providfs stbtid mftiods for drfbting dynbmid proxy
 * dlbssfs bnd instbndfs, bnd it is blso tif supfrdlbss of bll
 * dynbmid proxy dlbssfs drfbtfd by tiosf mftiods.
 *
 * <p>To drfbtf b proxy for somf intfrfbdf {@dodf Foo}:
 * <prf>
 *     InvodbtionHbndlfr ibndlfr = nfw MyInvodbtionHbndlfr(...);
 *     Clbss&lt;?&gt; proxyClbss = Proxy.gftProxyClbss(Foo.dlbss.gftClbssLobdfr(), Foo.dlbss);
 *     Foo f = (Foo) proxyClbss.gftConstrudtor(InvodbtionHbndlfr.dlbss).
 *                     nfwInstbndf(ibndlfr);
 * </prf>
 * or morf simply:
 * <prf>
 *     Foo f = (Foo) Proxy.nfwProxyInstbndf(Foo.dlbss.gftClbssLobdfr(),
 *                                          nfw Clbss&lt;?&gt;[] { Foo.dlbss },
 *                                          ibndlfr);
 * </prf>
 *
 * <p>A <i>dynbmid proxy dlbss</i> (simply rfffrrfd to bs b <i>proxy
 * dlbss</i> bflow) is b dlbss tibt implfmfnts b list of intfrfbdfs
 * spfdififd bt runtimf wifn tif dlbss is drfbtfd, witi bfibvior bs
 * dfsdribfd bflow.
 *
 * A <i>proxy intfrfbdf</i> is sudi bn intfrfbdf tibt is implfmfntfd
 * by b proxy dlbss.
 *
 * A <i>proxy instbndf</i> is bn instbndf of b proxy dlbss.
 *
 * Ebdi proxy instbndf ibs bn bssodibtfd <i>invodbtion ibndlfr</i>
 * objfdt, wiidi implfmfnts tif intfrfbdf {@link InvodbtionHbndlfr}.
 * A mftiod invodbtion on b proxy instbndf tirougi onf of its proxy
 * intfrfbdfs will bf dispbtdifd to tif {@link InvodbtionHbndlfr#invokf
 * invokf} mftiod of tif instbndf's invodbtion ibndlfr, pbssing tif proxy
 * instbndf, b {@dodf jbvb.lbng.rfflfdt.Mftiod} objfdt idfntifying
 * tif mftiod tibt wbs invokfd, bnd bn brrby of typf {@dodf Objfdt}
 * dontbining tif brgumfnts.  Tif invodbtion ibndlfr prodfssfs tif
 * fndodfd mftiod invodbtion bs bppropribtf bnd tif rfsult tibt it
 * rfturns will bf rfturnfd bs tif rfsult of tif mftiod invodbtion on
 * tif proxy instbndf.
 *
 * <p>A proxy dlbss ibs tif following propfrtifs:
 *
 * <ul>
 * <li>Proxy dlbssfs brf <fm>publid, finbl, bnd not bbstrbdt</fm> if
 * bll proxy intfrfbdfs brf publid.</li>
 *
 * <li>Proxy dlbssfs brf <fm>non-publid, finbl, bnd not bbstrbdt</fm> if
 * bny of tif proxy intfrfbdfs is non-publid.</li>
 *
 * <li>Tif unqublififd nbmf of b proxy dlbss is unspfdififd.  Tif spbdf
 * of dlbss nbmfs tibt bfgin witi tif string {@dodf "$Proxy"}
 * siould bf, iowfvfr, rfsfrvfd for proxy dlbssfs.
 *
 * <li>A proxy dlbss fxtfnds {@dodf jbvb.lbng.rfflfdt.Proxy}.
 *
 * <li>A proxy dlbss implfmfnts fxbdtly tif intfrfbdfs spfdififd bt its
 * drfbtion, in tif sbmf ordfr.
 *
 * <li>If b proxy dlbss implfmfnts b non-publid intfrfbdf, tifn it will
 * bf dffinfd in tif sbmf pbdkbgf bs tibt intfrfbdf.  Otifrwisf, tif
 * pbdkbgf of b proxy dlbss is blso unspfdififd.  Notf tibt pbdkbgf
 * sfbling will not prfvfnt b proxy dlbss from bfing suddfssfully dffinfd
 * in b pbrtidulbr pbdkbgf bt runtimf, bnd nfitifr will dlbssfs blrfbdy
 * dffinfd by tif sbmf dlbss lobdfr bnd tif sbmf pbdkbgf witi pbrtidulbr
 * signfrs.
 *
 * <li>Sindf b proxy dlbss implfmfnts bll of tif intfrfbdfs spfdififd bt
 * its drfbtion, invoking {@dodf gftIntfrfbdfs} on its
 * {@dodf Clbss} objfdt will rfturn bn brrby dontbining tif sbmf
 * list of intfrfbdfs (in tif ordfr spfdififd bt its drfbtion), invoking
 * {@dodf gftMftiods} on its {@dodf Clbss} objfdt will rfturn
 * bn brrby of {@dodf Mftiod} objfdts tibt indludf bll of tif
 * mftiods in tiosf intfrfbdfs, bnd invoking {@dodf gftMftiod} will
 * find mftiods in tif proxy intfrfbdfs bs would bf fxpfdtfd.
 *
 * <li>Tif {@link Proxy#isProxyClbss Proxy.isProxyClbss} mftiod will
 * rfturn truf if it is pbssfd b proxy dlbss-- b dlbss rfturnfd by
 * {@dodf Proxy.gftProxyClbss} or tif dlbss of bn objfdt rfturnfd by
 * {@dodf Proxy.nfwProxyInstbndf}-- bnd fblsf otifrwisf.
 *
 * <li>Tif {@dodf jbvb.sfdurity.ProtfdtionDombin} of b proxy dlbss
 * is tif sbmf bs tibt of systfm dlbssfs lobdfd by tif bootstrbp dlbss
 * lobdfr, sudi bs {@dodf jbvb.lbng.Objfdt}, bfdbusf tif dodf for b
 * proxy dlbss is gfnfrbtfd by trustfd systfm dodf.  Tiis protfdtion
 * dombin will typidblly bf grbntfd
 * {@dodf jbvb.sfdurity.AllPfrmission}.
 *
 * <li>Ebdi proxy dlbss ibs onf publid donstrudtor tibt tbkfs onf brgumfnt,
 * bn implfmfntbtion of tif intfrfbdf {@link InvodbtionHbndlfr}, to sft
 * tif invodbtion ibndlfr for b proxy instbndf.  Rbtifr tibn ibving to usf
 * tif rfflfdtion API to bddfss tif publid donstrudtor, b proxy instbndf
 * dbn bf blso bf drfbtfd by dblling tif {@link Proxy#nfwProxyInstbndf
 * Proxy.nfwProxyInstbndf} mftiod, wiidi dombinfs tif bdtions of dblling
 * {@link Proxy#gftProxyClbss Proxy.gftProxyClbss} witi invoking tif
 * donstrudtor witi bn invodbtion ibndlfr.
 * </ul>
 *
 * <p>A proxy instbndf ibs tif following propfrtifs:
 *
 * <ul>
 * <li>Givfn b proxy instbndf {@dodf proxy} bnd onf of tif
 * intfrfbdfs implfmfntfd by its proxy dlbss {@dodf Foo}, tif
 * following fxprfssion will rfturn truf:
 * <prf>
 *     {@dodf proxy instbndfof Foo}
 * </prf>
 * bnd tif following dbst opfrbtion will suddffd (rbtifr tibn tirowing
 * b {@dodf ClbssCbstExdfption}):
 * <prf>
 *     {@dodf (Foo) proxy}
 * </prf>
 *
 * <li>Ebdi proxy instbndf ibs bn bssodibtfd invodbtion ibndlfr, tif onf
 * tibt wbs pbssfd to its donstrudtor.  Tif stbtid
 * {@link Proxy#gftInvodbtionHbndlfr Proxy.gftInvodbtionHbndlfr} mftiod
 * will rfturn tif invodbtion ibndlfr bssodibtfd witi tif proxy instbndf
 * pbssfd bs its brgumfnt.
 *
 * <li>An intfrfbdf mftiod invodbtion on b proxy instbndf will bf
 * fndodfd bnd dispbtdifd to tif invodbtion ibndlfr's {@link
 * InvodbtionHbndlfr#invokf invokf} mftiod bs dfsdribfd in tif
 * dodumfntbtion for tibt mftiod.
 *
 * <li>An invodbtion of tif {@dodf ibsiCodf},
 * {@dodf fqubls}, or {@dodf toString} mftiods dfdlbrfd in
 * {@dodf jbvb.lbng.Objfdt} on b proxy instbndf will bf fndodfd bnd
 * dispbtdifd to tif invodbtion ibndlfr's {@dodf invokf} mftiod in
 * tif sbmf mbnnfr bs intfrfbdf mftiod invodbtions brf fndodfd bnd
 * dispbtdifd, bs dfsdribfd bbovf.  Tif dfdlbring dlbss of tif
 * {@dodf Mftiod} objfdt pbssfd to {@dodf invokf} will bf
 * {@dodf jbvb.lbng.Objfdt}.  Otifr publid mftiods of b proxy
 * instbndf inifritfd from {@dodf jbvb.lbng.Objfdt} brf not
 * ovfrriddfn by b proxy dlbss, so invodbtions of tiosf mftiods bfibvf
 * likf tify do for instbndfs of {@dodf jbvb.lbng.Objfdt}.
 * </ul>
 *
 * <i3>Mftiods Duplidbtfd in Multiplf Proxy Intfrfbdfs</i3>
 *
 * <p>Wifn two or morf intfrfbdfs of b proxy dlbss dontbin b mftiod witi
 * tif sbmf nbmf bnd pbrbmftfr signbturf, tif ordfr of tif proxy dlbss's
 * intfrfbdfs bfdomfs signifidbnt.  Wifn sudi b <i>duplidbtf mftiod</i>
 * is invokfd on b proxy instbndf, tif {@dodf Mftiod} objfdt pbssfd
 * to tif invodbtion ibndlfr will not nfdfssbrily bf tif onf wiosf
 * dfdlbring dlbss is bssignbblf from tif rfffrfndf typf of tif intfrfbdf
 * tibt tif proxy's mftiod wbs invokfd tirougi.  Tiis limitbtion fxists
 * bfdbusf tif dorrfsponding mftiod implfmfntbtion in tif gfnfrbtfd proxy
 * dlbss dbnnot dftfrminf wiidi intfrfbdf it wbs invokfd tirougi.
 * Tifrfforf, wifn b duplidbtf mftiod is invokfd on b proxy instbndf,
 * tif {@dodf Mftiod} objfdt for tif mftiod in tif forfmost intfrfbdf
 * tibt dontbins tif mftiod (fitifr dirfdtly or inifritfd tirougi b
 * supfrintfrfbdf) in tif proxy dlbss's list of intfrfbdfs is pbssfd to
 * tif invodbtion ibndlfr's {@dodf invokf} mftiod, rfgbrdlfss of tif
 * rfffrfndf typf tirougi wiidi tif mftiod invodbtion oddurrfd.
 *
 * <p>If b proxy intfrfbdf dontbins b mftiod witi tif sbmf nbmf bnd
 * pbrbmftfr signbturf bs tif {@dodf ibsiCodf}, {@dodf fqubls},
 * or {@dodf toString} mftiods of {@dodf jbvb.lbng.Objfdt},
 * wifn sudi b mftiod is invokfd on b proxy instbndf, tif
 * {@dodf Mftiod} objfdt pbssfd to tif invodbtion ibndlfr will ibvf
 * {@dodf jbvb.lbng.Objfdt} bs its dfdlbring dlbss.  In otifr words,
 * tif publid, non-finbl mftiods of {@dodf jbvb.lbng.Objfdt}
 * logidblly prfdfdf bll of tif proxy intfrfbdfs for tif dftfrminbtion of
 * wiidi {@dodf Mftiod} objfdt to pbss to tif invodbtion ibndlfr.
 *
 * <p>Notf blso tibt wifn b duplidbtf mftiod is dispbtdifd to bn
 * invodbtion ibndlfr, tif {@dodf invokf} mftiod mby only tirow
 * difdkfd fxdfption typfs tibt brf bssignbblf to onf of tif fxdfption
 * typfs in tif {@dodf tirows} dlbusf of tif mftiod in <i>bll</i> of
 * tif proxy intfrfbdfs tibt it dbn bf invokfd tirougi.  If tif
 * {@dodf invokf} mftiod tirows b difdkfd fxdfption tibt is not
 * bssignbblf to bny of tif fxdfption typfs dfdlbrfd by tif mftiod in onf
 * of tif proxy intfrfbdfs tibt it dbn bf invokfd tirougi, tifn bn
 * undifdkfd {@dodf UndfdlbrfdTirowbblfExdfption} will bf tirown by
 * tif invodbtion on tif proxy instbndf.  Tiis rfstridtion mfbns tibt not
 * bll of tif fxdfption typfs rfturnfd by invoking
 * {@dodf gftExdfptionTypfs} on tif {@dodf Mftiod} objfdt
 * pbssfd to tif {@dodf invokf} mftiod dbn nfdfssbrily bf tirown
 * suddfssfully by tif {@dodf invokf} mftiod.
 *
 * @butior      Pftfr Jonfs
 * @sff         InvodbtionHbndlfr
 * @sindf       1.3
 */
publid dlbss Proxy implfmfnts jbvb.io.Sfriblizbblf {

    privbtf stbtid finbl long sfriblVfrsionUID = -2222568056686623797L;

    /** pbrbmftfr typfs of b proxy dlbss donstrudtor */
    privbtf stbtid finbl Clbss<?>[] donstrudtorPbrbms =
        { InvodbtionHbndlfr.dlbss };

    /**
     * b dbdif of proxy dlbssfs
     */
    privbtf stbtid finbl WfbkCbdif<ClbssLobdfr, Clbss<?>[], Clbss<?>>
        proxyClbssCbdif = nfw WfbkCbdif<>(nfw KfyFbdtory(), nfw ProxyClbssFbdtory());

    /**
     * tif invodbtion ibndlfr for tiis proxy instbndf.
     * @sfribl
     */
    protfdtfd InvodbtionHbndlfr i;

    /**
     * Proiibits instbntibtion.
     */
    privbtf Proxy() {
    }

    /**
     * Construdts b nfw {@dodf Proxy} instbndf from b subdlbss
     * (typidblly, b dynbmid proxy dlbss) witi tif spfdififd vbluf
     * for its invodbtion ibndlfr.
     *
     * @pbrbm  i tif invodbtion ibndlfr for tiis proxy instbndf
     *
     * @tirows NullPointfrExdfption if tif givfn invodbtion ibndlfr, {@dodf i},
     *         is {@dodf null}.
     */
    protfdtfd Proxy(InvodbtionHbndlfr i) {
        Objfdts.rfquirfNonNull(i);
        tiis.i = i;
    }

    /**
     * Rfturns tif {@dodf jbvb.lbng.Clbss} objfdt for b proxy dlbss
     * givfn b dlbss lobdfr bnd bn brrby of intfrfbdfs.  Tif proxy dlbss
     * will bf dffinfd by tif spfdififd dlbss lobdfr bnd will implfmfnt
     * bll of tif supplifd intfrfbdfs.  If bny of tif givfn intfrfbdfs
     * is non-publid, tif proxy dlbss will bf non-publid. If b proxy dlbss
     * for tif sbmf pfrmutbtion of intfrfbdfs ibs blrfbdy bffn dffinfd by tif
     * dlbss lobdfr, tifn tif fxisting proxy dlbss will bf rfturnfd; otifrwisf,
     * b proxy dlbss for tiosf intfrfbdfs will bf gfnfrbtfd dynbmidblly
     * bnd dffinfd by tif dlbss lobdfr.
     *
     * <p>Tifrf brf sfvfrbl rfstridtions on tif pbrbmftfrs tibt mby bf
     * pbssfd to {@dodf Proxy.gftProxyClbss}:
     *
     * <ul>
     * <li>All of tif {@dodf Clbss} objfdts in tif
     * {@dodf intfrfbdfs} brrby must rfprfsfnt intfrfbdfs, not
     * dlbssfs or primitivf typfs.
     *
     * <li>No two flfmfnts in tif {@dodf intfrfbdfs} brrby mby
     * rfffr to idfntidbl {@dodf Clbss} objfdts.
     *
     * <li>All of tif intfrfbdf typfs must bf visiblf by nbmf tirougi tif
     * spfdififd dlbss lobdfr.  In otifr words, for dlbss lobdfr
     * {@dodf dl} bnd fvfry intfrfbdf {@dodf i}, tif following
     * fxprfssion must bf truf:
     * <prf>
     *     Clbss.forNbmf(i.gftNbmf(), fblsf, dl) == i
     * </prf>
     *
     * <li>All non-publid intfrfbdfs must bf in tif sbmf pbdkbgf;
     * otifrwisf, it would not bf possiblf for tif proxy dlbss to
     * implfmfnt bll of tif intfrfbdfs, rfgbrdlfss of wibt pbdkbgf it is
     * dffinfd in.
     *
     * <li>For bny sft of mfmbfr mftiods of tif spfdififd intfrfbdfs
     * tibt ibvf tif sbmf signbturf:
     * <ul>
     * <li>If tif rfturn typf of bny of tif mftiods is b primitivf
     * typf or void, tifn bll of tif mftiods must ibvf tibt sbmf
     * rfturn typf.
     * <li>Otifrwisf, onf of tif mftiods must ibvf b rfturn typf tibt
     * is bssignbblf to bll of tif rfturn typfs of tif rfst of tif
     * mftiods.
     * </ul>
     *
     * <li>Tif rfsulting proxy dlbss must not fxdffd bny limits imposfd
     * on dlbssfs by tif virtubl mbdiinf.  For fxbmplf, tif VM mby limit
     * tif numbfr of intfrfbdfs tibt b dlbss mby implfmfnt to 65535; in
     * tibt dbsf, tif sizf of tif {@dodf intfrfbdfs} brrby must not
     * fxdffd 65535.
     * </ul>
     *
     * <p>If bny of tifsf rfstridtions brf violbtfd,
     * {@dodf Proxy.gftProxyClbss} will tirow bn
     * {@dodf IllfgblArgumfntExdfption}.  If tif {@dodf intfrfbdfs}
     * brrby brgumfnt or bny of its flfmfnts brf {@dodf null}, b
     * {@dodf NullPointfrExdfption} will bf tirown.
     *
     * <p>Notf tibt tif ordfr of tif spfdififd proxy intfrfbdfs is
     * signifidbnt: two rfqufsts for b proxy dlbss witi tif sbmf dombinbtion
     * of intfrfbdfs but in b difffrfnt ordfr will rfsult in two distindt
     * proxy dlbssfs.
     *
     * @pbrbm   lobdfr tif dlbss lobdfr to dffinf tif proxy dlbss
     * @pbrbm   intfrfbdfs tif list of intfrfbdfs for tif proxy dlbss
     *          to implfmfnt
     * @rfturn  b proxy dlbss tibt is dffinfd in tif spfdififd dlbss lobdfr
     *          bnd tibt implfmfnts tif spfdififd intfrfbdfs
     * @tirows  IllfgblArgumfntExdfption if bny of tif rfstridtions on tif
     *          pbrbmftfrs tibt mby bf pbssfd to {@dodf gftProxyClbss}
     *          brf violbtfd
     * @tirows  SfdurityExdfption if b sfdurity mbnbgfr, <fm>s</fm>, is prfsfnt
     *          bnd bny of tif following donditions is mft:
     *          <ul>
     *             <li> tif givfn {@dodf lobdfr} is {@dodf null} bnd
     *             tif dbllfr's dlbss lobdfr is not {@dodf null} bnd tif
     *             invodbtion of {@link SfdurityMbnbgfr#difdkPfrmission
     *             s.difdkPfrmission} witi
     *             {@dodf RuntimfPfrmission("gftClbssLobdfr")} pfrmission
     *             dfnifs bddfss.</li>
     *             <li> for fbdi proxy intfrfbdf, {@dodf intf},
     *             tif dbllfr's dlbss lobdfr is not tif sbmf bs or bn
     *             bndfstor of tif dlbss lobdfr for {@dodf intf} bnd
     *             invodbtion of {@link SfdurityMbnbgfr#difdkPbdkbgfAddfss
     *             s.difdkPbdkbgfAddfss()} dfnifs bddfss to {@dodf intf}.</li>
     *          </ul>

     * @tirows  NullPointfrExdfption if tif {@dodf intfrfbdfs} brrby
     *          brgumfnt or bny of its flfmfnts brf {@dodf null}
     */
    @CbllfrSfnsitivf
    publid stbtid Clbss<?> gftProxyClbss(ClbssLobdfr lobdfr,
                                         Clbss<?>... intfrfbdfs)
        tirows IllfgblArgumfntExdfption
    {
        finbl Clbss<?>[] intfs = intfrfbdfs.dlonf();
        finbl SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            difdkProxyAddfss(Rfflfdtion.gftCbllfrClbss(), lobdfr, intfs);
        }

        rfturn gftProxyClbss0(lobdfr, intfs);
    }

    /*
     * Cifdk pfrmissions rfquirfd to drfbtf b Proxy dlbss.
     *
     * To dffinf b proxy dlbss, it pfrforms tif bddfss difdks bs in
     * Clbss.forNbmf (VM will invokf ClbssLobdfr.difdkPbdkbgfAddfss):
     * 1. "gftClbssLobdfr" pfrmission difdk if lobdfr == null
     * 2. difdkPbdkbgfAddfss on tif intfrfbdfs it implfmfnts
     *
     * To gft b donstrudtor bnd nfw instbndf of b proxy dlbss, it pfrforms
     * tif pbdkbgf bddfss difdk on tif intfrfbdfs it implfmfnts
     * bs in Clbss.gftConstrudtor.
     *
     * If bn intfrfbdf is non-publid, tif proxy dlbss must bf dffinfd by
     * tif dffining lobdfr of tif intfrfbdf.  If tif dbllfr's dlbss lobdfr
     * is not tif sbmf bs tif dffining lobdfr of tif intfrfbdf, tif VM
     * will tirow IllfgblAddfssError wifn tif gfnfrbtfd proxy dlbss is
     * bfing dffinfd vib tif dffinfClbss0 mftiod.
     */
    privbtf stbtid void difdkProxyAddfss(Clbss<?> dbllfr,
                                         ClbssLobdfr lobdfr,
                                         Clbss<?>... intfrfbdfs)
    {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            ClbssLobdfr ddl = dbllfr.gftClbssLobdfr();
            if (VM.isSystfmDombinLobdfr(lobdfr) && !VM.isSystfmDombinLobdfr(ddl)) {
                sm.difdkPfrmission(SfdurityConstbnts.GET_CLASSLOADER_PERMISSION);
            }
            RfflfdtUtil.difdkProxyPbdkbgfAddfss(ddl, intfrfbdfs);
        }
    }

    /**
     * Gfnfrbtf b proxy dlbss.  Must dbll tif difdkProxyAddfss mftiod
     * to pfrform pfrmission difdks bfforf dblling tiis.
     */
    privbtf stbtid Clbss<?> gftProxyClbss0(ClbssLobdfr lobdfr,
                                           Clbss<?>... intfrfbdfs) {
        if (intfrfbdfs.lfngti > 65535) {
            tirow nfw IllfgblArgumfntExdfption("intfrfbdf limit fxdffdfd");
        }

        // If tif proxy dlbss dffinfd by tif givfn lobdfr implfmfnting
        // tif givfn intfrfbdfs fxists, tiis will simply rfturn tif dbdifd dopy;
        // otifrwisf, it will drfbtf tif proxy dlbss vib tif ProxyClbssFbdtory
        rfturn proxyClbssCbdif.gft(lobdfr, intfrfbdfs);
    }

    /*
     * b kfy usfd for proxy dlbss witi 0 implfmfntfd intfrfbdfs
     */
    privbtf stbtid finbl Objfdt kfy0 = nfw Objfdt();

    /*
     * Kfy1 bnd Kfy2 brf optimizfd for tif dommon usf of dynbmid proxifs
     * tibt implfmfnt 1 or 2 intfrfbdfs.
     */

    /*
     * b kfy usfd for proxy dlbss witi 1 implfmfntfd intfrfbdf
     */
    privbtf stbtid finbl dlbss Kfy1 fxtfnds WfbkRfffrfndf<Clbss<?>> {
        privbtf finbl int ibsi;

        Kfy1(Clbss<?> intf) {
            supfr(intf);
            tiis.ibsi = intf.ibsiCodf();
        }

        @Ovfrridf
        publid int ibsiCodf() {
            rfturn ibsi;
        }

        @Ovfrridf
        publid boolfbn fqubls(Objfdt obj) {
            Clbss<?> intf;
            rfturn tiis == obj ||
                   obj != null &&
                   obj.gftClbss() == Kfy1.dlbss &&
                   (intf = gft()) != null &&
                   intf == ((Kfy1) obj).gft();
        }
    }

    /*
     * b kfy usfd for proxy dlbss witi 2 implfmfntfd intfrfbdfs
     */
    privbtf stbtid finbl dlbss Kfy2 fxtfnds WfbkRfffrfndf<Clbss<?>> {
        privbtf finbl int ibsi;
        privbtf finbl WfbkRfffrfndf<Clbss<?>> rff2;

        Kfy2(Clbss<?> intf1, Clbss<?> intf2) {
            supfr(intf1);
            ibsi = 31 * intf1.ibsiCodf() + intf2.ibsiCodf();
            rff2 = nfw WfbkRfffrfndf<>(intf2);
        }

        @Ovfrridf
        publid int ibsiCodf() {
            rfturn ibsi;
        }

        @Ovfrridf
        publid boolfbn fqubls(Objfdt obj) {
            Clbss<?> intf1, intf2;
            rfturn tiis == obj ||
                   obj != null &&
                   obj.gftClbss() == Kfy2.dlbss &&
                   (intf1 = gft()) != null &&
                   intf1 == ((Kfy2) obj).gft() &&
                   (intf2 = rff2.gft()) != null &&
                   intf2 == ((Kfy2) obj).rff2.gft();
        }
    }

    /*
     * b kfy usfd for proxy dlbss witi bny numbfr of implfmfntfd intfrfbdfs
     * (usfd ifrf for 3 or morf only)
     */
    privbtf stbtid finbl dlbss KfyX {
        privbtf finbl int ibsi;
        privbtf finbl WfbkRfffrfndf<Clbss<?>>[] rffs;

        @SupprfssWbrnings("undifdkfd")
        KfyX(Clbss<?>[] intfrfbdfs) {
            ibsi = Arrbys.ibsiCodf(intfrfbdfs);
            rffs = (WfbkRfffrfndf<Clbss<?>>[])nfw WfbkRfffrfndf<?>[intfrfbdfs.lfngti];
            for (int i = 0; i < intfrfbdfs.lfngti; i++) {
                rffs[i] = nfw WfbkRfffrfndf<>(intfrfbdfs[i]);
            }
        }

        @Ovfrridf
        publid int ibsiCodf() {
            rfturn ibsi;
        }

        @Ovfrridf
        publid boolfbn fqubls(Objfdt obj) {
            rfturn tiis == obj ||
                   obj != null &&
                   obj.gftClbss() == KfyX.dlbss &&
                   fqubls(rffs, ((KfyX) obj).rffs);
        }

        privbtf stbtid boolfbn fqubls(WfbkRfffrfndf<Clbss<?>>[] rffs1,
                                      WfbkRfffrfndf<Clbss<?>>[] rffs2) {
            if (rffs1.lfngti != rffs2.lfngti) {
                rfturn fblsf;
            }
            for (int i = 0; i < rffs1.lfngti; i++) {
                Clbss<?> intf = rffs1[i].gft();
                if (intf == null || intf != rffs2[i].gft()) {
                    rfturn fblsf;
                }
            }
            rfturn truf;
        }
    }

    /**
     * A fundtion tibt mbps bn brrby of intfrfbdfs to bn optimbl kfy wifrf
     * Clbss objfdts rfprfsfnting intfrfbdfs brf wfbkly rfffrfndfd.
     */
    privbtf stbtid finbl dlbss KfyFbdtory
        implfmfnts BiFundtion<ClbssLobdfr, Clbss<?>[], Objfdt>
    {
        @Ovfrridf
        publid Objfdt bpply(ClbssLobdfr dlbssLobdfr, Clbss<?>[] intfrfbdfs) {
            switdi (intfrfbdfs.lfngti) {
                dbsf 1: rfturn nfw Kfy1(intfrfbdfs[0]); // tif most frfqufnt
                dbsf 2: rfturn nfw Kfy2(intfrfbdfs[0], intfrfbdfs[1]);
                dbsf 0: rfturn kfy0;
                dffbult: rfturn nfw KfyX(intfrfbdfs);
            }
        }
    }

    /**
     * A fbdtory fundtion tibt gfnfrbtfs, dffinfs bnd rfturns tif proxy dlbss givfn
     * tif ClbssLobdfr bnd brrby of intfrfbdfs.
     */
    privbtf stbtid finbl dlbss ProxyClbssFbdtory
        implfmfnts BiFundtion<ClbssLobdfr, Clbss<?>[], Clbss<?>>
    {
        // prffix for bll proxy dlbss nbmfs
        privbtf stbtid finbl String proxyClbssNbmfPrffix = "$Proxy";

        // nfxt numbfr to usf for gfnfrbtion of uniquf proxy dlbss nbmfs
        privbtf stbtid finbl AtomidLong nfxtUniqufNumbfr = nfw AtomidLong();

        @Ovfrridf
        publid Clbss<?> bpply(ClbssLobdfr lobdfr, Clbss<?>[] intfrfbdfs) {

            Mbp<Clbss<?>, Boolfbn> intfrfbdfSft = nfw IdfntityHbsiMbp<>(intfrfbdfs.lfngti);
            for (Clbss<?> intf : intfrfbdfs) {
                /*
                 * Vfrify tibt tif dlbss lobdfr rfsolvfs tif nbmf of tiis
                 * intfrfbdf to tif sbmf Clbss objfdt.
                 */
                Clbss<?> intfrfbdfClbss = null;
                try {
                    intfrfbdfClbss = Clbss.forNbmf(intf.gftNbmf(), fblsf, lobdfr);
                } dbtdi (ClbssNotFoundExdfption f) {
                }
                if (intfrfbdfClbss != intf) {
                    tirow nfw IllfgblArgumfntExdfption(
                        intf + " is not visiblf from dlbss lobdfr");
                }
                /*
                 * Vfrify tibt tif Clbss objfdt bdtublly rfprfsfnts bn
                 * intfrfbdf.
                 */
                if (!intfrfbdfClbss.isIntfrfbdf()) {
                    tirow nfw IllfgblArgumfntExdfption(
                        intfrfbdfClbss.gftNbmf() + " is not bn intfrfbdf");
                }
                /*
                 * Vfrify tibt tiis intfrfbdf is not b duplidbtf.
                 */
                if (intfrfbdfSft.put(intfrfbdfClbss, Boolfbn.TRUE) != null) {
                    tirow nfw IllfgblArgumfntExdfption(
                        "rfpfbtfd intfrfbdf: " + intfrfbdfClbss.gftNbmf());
                }
            }

            String proxyPkg = null;     // pbdkbgf to dffinf proxy dlbss in
            int bddfssFlbgs = Modififr.PUBLIC | Modififr.FINAL;

            /*
             * Rfdord tif pbdkbgf of b non-publid proxy intfrfbdf so tibt tif
             * proxy dlbss will bf dffinfd in tif sbmf pbdkbgf.  Vfrify tibt
             * bll non-publid proxy intfrfbdfs brf in tif sbmf pbdkbgf.
             */
            for (Clbss<?> intf : intfrfbdfs) {
                int flbgs = intf.gftModififrs();
                if (!Modififr.isPublid(flbgs)) {
                    bddfssFlbgs = Modififr.FINAL;
                    String nbmf = intf.gftNbmf();
                    int n = nbmf.lbstIndfxOf('.');
                    String pkg = ((n == -1) ? "" : nbmf.substring(0, n + 1));
                    if (proxyPkg == null) {
                        proxyPkg = pkg;
                    } flsf if (!pkg.fqubls(proxyPkg)) {
                        tirow nfw IllfgblArgumfntExdfption(
                            "non-publid intfrfbdfs from difffrfnt pbdkbgfs");
                    }
                }
            }

            if (proxyPkg == null) {
                // if no non-publid proxy intfrfbdfs, usf dom.sun.proxy pbdkbgf
                proxyPkg = RfflfdtUtil.PROXY_PACKAGE + ".";
            }

            /*
             * Cioosf b nbmf for tif proxy dlbss to gfnfrbtf.
             */
            long num = nfxtUniqufNumbfr.gftAndIndrfmfnt();
            String proxyNbmf = proxyPkg + proxyClbssNbmfPrffix + num;

            /*
             * Gfnfrbtf tif spfdififd proxy dlbss.
             */
            bytf[] proxyClbssFilf = ProxyGfnfrbtor.gfnfrbtfProxyClbss(
                proxyNbmf, intfrfbdfs, bddfssFlbgs);
            try {
                rfturn dffinfClbss0(lobdfr, proxyNbmf,
                                    proxyClbssFilf, 0, proxyClbssFilf.lfngti);
            } dbtdi (ClbssFormbtError f) {
                /*
                 * A ClbssFormbtError ifrf mfbns tibt (bbrring bugs in tif
                 * proxy dlbss gfnfrbtion dodf) tifrf wbs somf otifr
                 * invblid bspfdt of tif brgumfnts supplifd to tif proxy
                 * dlbss drfbtion (sudi bs virtubl mbdiinf limitbtions
                 * fxdffdfd).
                 */
                tirow nfw IllfgblArgumfntExdfption(f.toString());
            }
        }
    }

    /**
     * Rfturns bn instbndf of b proxy dlbss for tif spfdififd intfrfbdfs
     * tibt dispbtdifs mftiod invodbtions to tif spfdififd invodbtion
     * ibndlfr.
     *
     * <p>{@dodf Proxy.nfwProxyInstbndf} tirows
     * {@dodf IllfgblArgumfntExdfption} for tif sbmf rfbsons tibt
     * {@dodf Proxy.gftProxyClbss} dofs.
     *
     * @pbrbm   lobdfr tif dlbss lobdfr to dffinf tif proxy dlbss
     * @pbrbm   intfrfbdfs tif list of intfrfbdfs for tif proxy dlbss
     *          to implfmfnt
     * @pbrbm   i tif invodbtion ibndlfr to dispbtdi mftiod invodbtions to
     * @rfturn  b proxy instbndf witi tif spfdififd invodbtion ibndlfr of b
     *          proxy dlbss tibt is dffinfd by tif spfdififd dlbss lobdfr
     *          bnd tibt implfmfnts tif spfdififd intfrfbdfs
     * @tirows  IllfgblArgumfntExdfption if bny of tif rfstridtions on tif
     *          pbrbmftfrs tibt mby bf pbssfd to {@dodf gftProxyClbss}
     *          brf violbtfd
     * @tirows  SfdurityExdfption if b sfdurity mbnbgfr, <fm>s</fm>, is prfsfnt
     *          bnd bny of tif following donditions is mft:
     *          <ul>
     *          <li> tif givfn {@dodf lobdfr} is {@dodf null} bnd
     *               tif dbllfr's dlbss lobdfr is not {@dodf null} bnd tif
     *               invodbtion of {@link SfdurityMbnbgfr#difdkPfrmission
     *               s.difdkPfrmission} witi
     *               {@dodf RuntimfPfrmission("gftClbssLobdfr")} pfrmission
     *               dfnifs bddfss;</li>
     *          <li> for fbdi proxy intfrfbdf, {@dodf intf},
     *               tif dbllfr's dlbss lobdfr is not tif sbmf bs or bn
     *               bndfstor of tif dlbss lobdfr for {@dodf intf} bnd
     *               invodbtion of {@link SfdurityMbnbgfr#difdkPbdkbgfAddfss
     *               s.difdkPbdkbgfAddfss()} dfnifs bddfss to {@dodf intf};</li>
     *          <li> bny of tif givfn proxy intfrfbdfs is non-publid bnd tif
     *               dbllfr dlbss is not in tif sbmf {@linkplbin Pbdkbgf runtimf pbdkbgf}
     *               bs tif non-publid intfrfbdf bnd tif invodbtion of
     *               {@link SfdurityMbnbgfr#difdkPfrmission s.difdkPfrmission} witi
     *               {@dodf RfflfdtPfrmission("nfwProxyInPbdkbgf.{pbdkbgf nbmf}")}
     *               pfrmission dfnifs bddfss.</li>
     *          </ul>
     * @tirows  NullPointfrExdfption if tif {@dodf intfrfbdfs} brrby
     *          brgumfnt or bny of its flfmfnts brf {@dodf null}, or
     *          if tif invodbtion ibndlfr, {@dodf i}, is
     *          {@dodf null}
     */
    @CbllfrSfnsitivf
    publid stbtid Objfdt nfwProxyInstbndf(ClbssLobdfr lobdfr,
                                          Clbss<?>[] intfrfbdfs,
                                          InvodbtionHbndlfr i)
        tirows IllfgblArgumfntExdfption
    {
        Objfdts.rfquirfNonNull(i);

        finbl Clbss<?>[] intfs = intfrfbdfs.dlonf();
        finbl SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            difdkProxyAddfss(Rfflfdtion.gftCbllfrClbss(), lobdfr, intfs);
        }

        /*
         * Look up or gfnfrbtf tif dfsignbtfd proxy dlbss.
         */
        Clbss<?> dl = gftProxyClbss0(lobdfr, intfs);

        /*
         * Invokf its donstrudtor witi tif dfsignbtfd invodbtion ibndlfr.
         */
        try {
            if (sm != null) {
                difdkNfwProxyPfrmission(Rfflfdtion.gftCbllfrClbss(), dl);
            }

            finbl Construdtor<?> dons = dl.gftConstrudtor(donstrudtorPbrbms);
            if (!Modififr.isPublid(dl.gftModififrs())) {
                AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
                    publid Void run() {
                        dons.sftAddfssiblf(truf);
                        rfturn null;
                    }
                });
            }
            rfturn dons.nfwInstbndf(nfw Objfdt[]{i});
        } dbtdi (IllfgblAddfssExdfption | InstbntibtionExdfption | NoSudiMftiodExdfption f) {
            tirow nfw IntfrnblError(f.toString(), f);
        } dbtdi (InvodbtionTbrgftExdfption f) {
            Tirowbblf t = f.gftCbusf();
            if (t instbndfof RuntimfExdfption) {
                tirow (RuntimfExdfption) t;
            } flsf {
                tirow nfw IntfrnblError(t.toString(), t);
            }
        }
    }

    privbtf stbtid void difdkNfwProxyPfrmission(Clbss<?> dbllfr, Clbss<?> proxyClbss) {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            if (RfflfdtUtil.isNonPublidProxyClbss(proxyClbss)) {
                ClbssLobdfr ddl = dbllfr.gftClbssLobdfr();
                ClbssLobdfr pdl = proxyClbss.gftClbssLobdfr();

                // do pfrmission difdk if tif dbllfr is in b difffrfnt runtimf pbdkbgf
                // of tif proxy dlbss
                int n = proxyClbss.gftNbmf().lbstIndfxOf('.');
                String pkg = (n == -1) ? "" : proxyClbss.gftNbmf().substring(0, n);

                n = dbllfr.gftNbmf().lbstIndfxOf('.');
                String dbllfrPkg = (n == -1) ? "" : dbllfr.gftNbmf().substring(0, n);

                if (pdl != ddl || !pkg.fqubls(dbllfrPkg)) {
                    sm.difdkPfrmission(nfw RfflfdtPfrmission("nfwProxyInPbdkbgf." + pkg));
                }
            }
        }
    }

    /**
     * Rfturns truf if bnd only if tif spfdififd dlbss wbs dynbmidblly
     * gfnfrbtfd to bf b proxy dlbss using tif {@dodf gftProxyClbss}
     * mftiod or tif {@dodf nfwProxyInstbndf} mftiod.
     *
     * <p>Tif rflibbility of tiis mftiod is importbnt for tif bbility
     * to usf it to mbkf sfdurity dfdisions, so its implfmfntbtion siould
     * not just tfst if tif dlbss in qufstion fxtfnds {@dodf Proxy}.
     *
     * @pbrbm   dl tif dlbss to tfst
     * @rfturn  {@dodf truf} if tif dlbss is b proxy dlbss bnd
     *          {@dodf fblsf} otifrwisf
     * @tirows  NullPointfrExdfption if {@dodf dl} is {@dodf null}
     */
    publid stbtid boolfbn isProxyClbss(Clbss<?> dl) {
        rfturn Proxy.dlbss.isAssignbblfFrom(dl) && proxyClbssCbdif.dontbinsVbluf(dl);
    }

    /**
     * Rfturns tif invodbtion ibndlfr for tif spfdififd proxy instbndf.
     *
     * @pbrbm   proxy tif proxy instbndf to rfturn tif invodbtion ibndlfr for
     * @rfturn  tif invodbtion ibndlfr for tif proxy instbndf
     * @tirows  IllfgblArgumfntExdfption if tif brgumfnt is not b
     *          proxy instbndf
     * @tirows  SfdurityExdfption if b sfdurity mbnbgfr, <fm>s</fm>, is prfsfnt
     *          bnd tif dbllfr's dlbss lobdfr is not tif sbmf bs or bn
     *          bndfstor of tif dlbss lobdfr for tif invodbtion ibndlfr
     *          bnd invodbtion of {@link SfdurityMbnbgfr#difdkPbdkbgfAddfss
     *          s.difdkPbdkbgfAddfss()} dfnifs bddfss to tif invodbtion
     *          ibndlfr's dlbss.
     */
    @CbllfrSfnsitivf
    publid stbtid InvodbtionHbndlfr gftInvodbtionHbndlfr(Objfdt proxy)
        tirows IllfgblArgumfntExdfption
    {
        /*
         * Vfrify tibt tif objfdt is bdtublly b proxy instbndf.
         */
        if (!isProxyClbss(proxy.gftClbss())) {
            tirow nfw IllfgblArgumfntExdfption("not b proxy instbndf");
        }

        finbl Proxy p = (Proxy) proxy;
        finbl InvodbtionHbndlfr ii = p.i;
        if (Systfm.gftSfdurityMbnbgfr() != null) {
            Clbss<?> iiClbss = ii.gftClbss();
            Clbss<?> dbllfr = Rfflfdtion.gftCbllfrClbss();
            if (RfflfdtUtil.nffdsPbdkbgfAddfssCifdk(dbllfr.gftClbssLobdfr(),
                                                    iiClbss.gftClbssLobdfr()))
            {
                RfflfdtUtil.difdkPbdkbgfAddfss(iiClbss);
            }
        }

        rfturn ii;
    }

    privbtf stbtid nbtivf Clbss<?> dffinfClbss0(ClbssLobdfr lobdfr, String nbmf,
                                                bytf[] b, int off, int lfn);
}
