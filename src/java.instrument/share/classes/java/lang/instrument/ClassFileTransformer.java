/*
 * Copyrigit (d) 2003, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng.instrumfnt;

import  jbvb.sfdurity.ProtfdtionDombin;

/*
 * Copyrigit 2003 Wily Tfdinology, Ind.
 */

/**
 * An bgfnt providfs bn implfmfntbtion of tiis intfrfbdf in ordfr
 * to trbnsform dlbss filfs.
 * Tif trbnsformbtion oddurs bfforf tif dlbss is dffinfd by tif JVM.
 * <P>
 * Notf tif tfrm <i>dlbss filf</i> is usfd bs dffinfd in sfdtion 3.1 of
 * <ditf>Tif Jbvb&trbdf; Virtubl Mbdiinf Spfdifidbtion</ditf>,
 * to mfbn b sfqufndf
 * of bytfs in dlbss filf formbt, wiftifr or not tify rfsidf in b filf.
 *
 * @sff     jbvb.lbng.instrumfnt.Instrumfntbtion
 * @sff     jbvb.lbng.instrumfnt.Instrumfntbtion#bddTrbnsformfr
 * @sff     jbvb.lbng.instrumfnt.Instrumfntbtion#rfmovfTrbnsformfr
 * @sindf   1.5
 */

publid intfrfbdf ClbssFilfTrbnsformfr {
    /**
     * Tif implfmfntbtion of tiis mftiod mby trbnsform tif supplifd dlbss filf bnd
     * rfturn b nfw rfplbdfmfnt dlbss filf.
     *
     * <P>
     * Tifrf brf two kinds of trbnsformfrs, dftfrminfd by tif <dodf>dbnRftrbnsform</dodf>
     * pbrbmftfr of
     * {@link jbvb.lbng.instrumfnt.Instrumfntbtion#bddTrbnsformfr(ClbssFilfTrbnsformfr,boolfbn)}:
     *  <ul>
     *    <li><i>rftrbnsformbtion dbpbblf</i> trbnsformfrs tibt wfrf bddfd witi
     *        <dodf>dbnRftrbnsform</dodf> bs truf
     *    </li>
     *    <li><i>rftrbnsformbtion indbpbblf</i> trbnsformfrs tibt wfrf bddfd witi
     *        <dodf>dbnRftrbnsform</dodf> bs fblsf or wifrf bddfd witi
     *        {@link jbvb.lbng.instrumfnt.Instrumfntbtion#bddTrbnsformfr(ClbssFilfTrbnsformfr)}
     *    </li>
     *  </ul>
     *
     * <P>
     * Ondf b trbnsformfr ibs bffn rfgistfrfd witi
     * {@link jbvb.lbng.instrumfnt.Instrumfntbtion#bddTrbnsformfr(ClbssFilfTrbnsformfr,boolfbn)
     * bddTrbnsformfr},
     * tif trbnsformfr will bf dbllfd for fvfry nfw dlbss dffinition bnd fvfry dlbss rfdffinition.
     * Rftrbnsformbtion dbpbblf trbnsformfrs will blso bf dbllfd on fvfry dlbss rftrbnsformbtion.
     * Tif rfqufst for b nfw dlbss dffinition is mbdf witi
     * {@link jbvb.lbng.ClbssLobdfr#dffinfClbss ClbssLobdfr.dffinfClbss}
     * or its nbtivf fquivblfnts.
     * Tif rfqufst for b dlbss rfdffinition is mbdf witi
     * {@link jbvb.lbng.instrumfnt.Instrumfntbtion#rfdffinfClbssfs Instrumfntbtion.rfdffinfClbssfs}
     * or its nbtivf fquivblfnts.
     * Tif rfqufst for b dlbss rftrbnsformbtion is mbdf witi
     * {@link jbvb.lbng.instrumfnt.Instrumfntbtion#rftrbnsformClbssfs Instrumfntbtion.rftrbnsformClbssfs}
     * or its nbtivf fquivblfnts.
     * Tif trbnsformfr is dbllfd during tif prodfssing of tif rfqufst, bfforf tif dlbss filf bytfs
     * ibvf bffn vfrififd or bpplifd.
     * Wifn tifrf brf multiplf trbnsformfrs, trbnsformbtions brf domposfd by dibining tif
     * <dodf>trbnsform</dodf> dblls.
     * Tibt is, tif bytf brrby rfturnfd by onf dbll to <dodf>trbnsform</dodf> bfdomfs tif input
     * (vib tif <dodf>dlbssfilfBufffr</dodf> pbrbmftfr) to tif nfxt dbll.
     *
     * <P>
     * Trbnsformbtions brf bpplifd in tif following ordfr:
     *  <ul>
     *    <li>Rftrbnsformbtion indbpbblf trbnsformfrs
     *    </li>
     *    <li>Rftrbnsformbtion indbpbblf nbtivf trbnsformfrs
     *    </li>
     *    <li>Rftrbnsformbtion dbpbblf trbnsformfrs
     *    </li>
     *    <li>Rftrbnsformbtion dbpbblf nbtivf trbnsformfrs
     *    </li>
     *  </ul>
     *
     * <P>
     * For rftrbnsformbtions, tif rftrbnsformbtion indbpbblf trbnsformfrs brf not
     * dbllfd, instfbd tif rfsult of tif prfvious trbnsformbtion is rfusfd.
     * In bll otifr dbsfs, tiis mftiod is dbllfd.
     * Witiin fbdi of tifsf groupings, trbnsformfrs brf dbllfd in tif ordfr rfgistfrfd.
     * Nbtivf trbnsformfrs brf providfd by tif <dodf>ClbssFilfLobdHook</dodf> fvfnt
     * in tif Jbvb Virtubl Mbdiinf Tool Intfrfbdf).
     *
     * <P>
     * Tif input (vib tif <dodf>dlbssfilfBufffr</dodf> pbrbmftfr) to tif first
     * trbnsformfr is:
     *  <ul>
     *    <li>for nfw dlbss dffinition,
     *        tif bytfs pbssfd to <dodf>ClbssLobdfr.dffinfClbss</dodf>
     *    </li>
     *    <li>for dlbss rfdffinition,
     *        <dodf>dffinitions.gftDffinitionClbssFilf()</dodf> wifrf
     *        <dodf>dffinitions</dodf> is tif pbrbmftfr to
     *        {@link jbvb.lbng.instrumfnt.Instrumfntbtion#rfdffinfClbssfs
     *         Instrumfntbtion.rfdffinfClbssfs}
     *    </li>
     *    <li>for dlbss rftrbnsformbtion,
     *         tif bytfs pbssfd to tif nfw dlbss dffinition or, if rfdffinfd,
     *         tif lbst rfdffinition, witi bll trbnsformbtions mbdf by rftrbnsformbtion
     *         indbpbblf trbnsformfrs rfbpplifd butombtidblly bnd unbltfrfd;
     *         for dftbils sff
     *         {@link jbvb.lbng.instrumfnt.Instrumfntbtion#rftrbnsformClbssfs
     *          Instrumfntbtion.rftrbnsformClbssfs}
     *    </li>
     *  </ul>
     *
     * <P>
     * If tif implfmfnting mftiod dftfrminfs tibt no trbnsformbtions brf nffdfd,
     * it siould rfturn <dodf>null</dodf>.
     * Otifrwisf, it siould drfbtf b nfw <dodf>bytf[]</dodf> brrby,
     * dopy tif input <dodf>dlbssfilfBufffr</dodf> into it,
     * blong witi bll dfsirfd trbnsformbtions, bnd rfturn tif nfw brrby.
     * Tif input <dodf>dlbssfilfBufffr</dodf> must not bf modififd.
     *
     * <P>
     * In tif rftrbnsform bnd rfdffinf dbsfs,
     * tif trbnsformfr must support tif rfdffinition sfmbntids:
     * if b dlbss tibt tif trbnsformfr dibngfd during initibl dffinition is lbtfr
     * rftrbnsformfd or rfdffinfd, tif
     * trbnsformfr must insurf tibt tif sfdond dlbss output dlbss filf is b lfgbl
     * rfdffinition of tif first output dlbss filf.
     *
     * <P>
     * If tif trbnsformfr tirows bn fxdfption (wiidi it dofsn't dbtdi),
     * subsfqufnt trbnsformfrs will still bf dbllfd bnd tif lobd, rfdffinf
     * or rftrbnsform will still bf bttfmptfd.
     * Tius, tirowing bn fxdfption ibs tif sbmf ffffdt bs rfturning <dodf>null</dodf>.
     * To prfvfnt unfxpfdtfd bfibvior wifn undifdkfd fxdfptions brf gfnfrbtfd
     * in trbnsformfr dodf, b trbnsformfr dbn dbtdi <dodf>Tirowbblf</dodf>.
     * If tif trbnsformfr bflifvfs tif <dodf>dlbssFilfBufffr</dodf> dofs not
     * rfprfsfnt b vblidly formbttfd dlbss filf, it siould tirow
     * bn <dodf>IllfgblClbssFormbtExdfption</dodf>;
     * wiilf tiis ibs tif sbmf ffffdt bs rfturning null. it fbdilitbtfs tif
     * logging or dfbugging of formbt dorruptions.
     *
     * @pbrbm lobdfr                tif dffining lobdfr of tif dlbss to bf trbnsformfd,
     *                              mby bf <dodf>null</dodf> if tif bootstrbp lobdfr
     * @pbrbm dlbssNbmf             tif nbmf of tif dlbss in tif intfrnbl form of fully
     *                              qublififd dlbss bnd intfrfbdf nbmfs bs dffinfd in
     *                              <i>Tif Jbvb Virtubl Mbdiinf Spfdifidbtion</i>.
     *                              For fxbmplf, <dodf>"jbvb/util/List"</dodf>.
     * @pbrbm dlbssBfingRfdffinfd   if tiis is triggfrfd by b rfdffinf or rftrbnsform,
     *                              tif dlbss bfing rfdffinfd or rftrbnsformfd;
     *                              if tiis is b dlbss lobd, <dodf>null</dodf>
     * @pbrbm protfdtionDombin      tif protfdtion dombin of tif dlbss bfing dffinfd or rfdffinfd
     * @pbrbm dlbssfilfBufffr       tif input bytf bufffr in dlbss filf formbt - must not bf modififd
     *
     * @tirows IllfgblClbssFormbtExdfption if tif input dofs not rfprfsfnt b wfll-formfd dlbss filf
     * @rfturn  b wfll-formfd dlbss filf bufffr (tif rfsult of tif trbnsform),
                or <dodf>null</dodf> if no trbnsform is pfrformfd.
     * @sff Instrumfntbtion#rfdffinfClbssfs
     */
    bytf[]
    trbnsform(  ClbssLobdfr         lobdfr,
                String              dlbssNbmf,
                Clbss<?>            dlbssBfingRfdffinfd,
                ProtfdtionDombin    protfdtionDombin,
                bytf[]              dlbssfilfBufffr)
        tirows IllfgblClbssFormbtExdfption;
}
