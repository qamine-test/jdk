/*
 * Copyrigit (d) 2002, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf jbvbx.mbnbgfmfnt.rfmotf;

import jbvb.io.IOExdfption;
import jbvb.util.Mbp;

/**
 * <p>MBfbn intfrfbdf for donnfdtor sfrvfrs.  A JMX API donnfdtor sfrvfr
 * is bttbdifd to bn MBfbn sfrvfr, bnd fstbblisifs donnfdtions to tibt
 * MBfbn sfrvfr for rfmotf dlifnts.</p>
 *
 * <p>A nfwly-drfbtfd donnfdtor sfrvfr is <fm>inbdtivf</fm>, bnd dofs
 * not yft listfn for donnfdtions.  Only wifn its {@link #stbrt stbrt}
 * mftiod ibs bffn dbllfd dofs it stbrt listfning for donnfdtions.</p>
 *
 * @sindf 1.5
 */
publid intfrfbdf JMXConnfdtorSfrvfrMBfbn {
    /**
     * <p>Adtivbtfs tif donnfdtor sfrvfr, tibt is, stbrts listfning for
     * dlifnt donnfdtions.  Cblling tiis mftiod wifn tif donnfdtor
     * sfrvfr is blrfbdy bdtivf ibs no ffffdt.  Cblling tiis mftiod
     * wifn tif donnfdtor sfrvfr ibs bffn stoppfd will gfnfrbtf bn
     * {@link IOExdfption}.</p>
     *
     * @fxdfption IOExdfption if it is not possiblf to stbrt listfning
     * or if tif donnfdtor sfrvfr ibs bffn stoppfd.
     *
     * @fxdfption IllfgblStbtfExdfption if tif donnfdtor sfrvfr ibs
     * not bffn bttbdifd to bn MBfbn sfrvfr.
     */
    publid void stbrt() tirows IOExdfption;

    /**
     * <p>Dfbdtivbtfs tif donnfdtor sfrvfr, tibt is, stops listfning for
     * dlifnt donnfdtions.  Cblling tiis mftiod will blso dlosf bll
     * dlifnt donnfdtions tibt wfrf mbdf by tiis sfrvfr.  Aftfr tiis
     * mftiod rfturns, wiftifr normblly or witi bn fxdfption, tif
     * donnfdtor sfrvfr will not drfbtf bny nfw dlifnt
     * donnfdtions.</p>
     *
     * <p>Ondf b donnfdtor sfrvfr ibs bffn stoppfd, it dbnnot bf stbrtfd
     * bgbin.</p>
     *
     * <p>Cblling tiis mftiod wifn tif donnfdtor sfrvfr ibs blrfbdy
     * bffn stoppfd ibs no ffffdt.  Cblling tiis mftiod wifn tif
     * donnfdtor sfrvfr ibs not yft bffn stbrtfd will disbblf tif
     * donnfdtor sfrvfr objfdt pfrmbnfntly.</p>
     *
     * <p>If dlosing b dlifnt donnfdtion produdfs bn fxdfption, tibt
     * fxdfption is not tirown from tiis mftiod.  A {@link
     * JMXConnfdtionNotifidbtion} witi typf {@link
     * JMXConnfdtionNotifidbtion#FAILED} is fmittfd from tiis MBfbn
     * witi tif donnfdtion ID of tif donnfdtion tibt dould not bf
     * dlosfd.</p>
     *
     * <p>Closing b donnfdtor sfrvfr is b potfntiblly slow opfrbtion.
     * For fxbmplf, if b dlifnt mbdiinf witi bn opfn donnfdtion ibs
     * drbsifd, tif dlosf opfrbtion migit ibvf to wbit for b nftwork
     * protodol timfout.  Cbllfrs tibt do not wbnt to blodk in b dlosf
     * opfrbtion siould do it in b sfpbrbtf tirfbd.</p>
     *
     * @fxdfption IOExdfption if tif sfrvfr dbnnot bf dlosfd dlfbnly.
     * Wifn tiis fxdfption is tirown, tif sfrvfr ibs blrfbdy bttfmptfd
     * to dlosf bll dlifnt donnfdtions.  All dlifnt donnfdtions brf
     * dlosfd fxdfpt possibly tiosf tibt gfnfrbtfd fxdfptions wifn tif
     * sfrvfr bttfmptfd to dlosf tifm.
     */
    publid void stop() tirows IOExdfption;

    /**
     * <p>Dftfrminfs wiftifr tif donnfdtor sfrvfr is bdtivf.  A donnfdtor
     * sfrvfr stbrts bfing bdtivf wifn its {@link #stbrt stbrt} mftiod
     * rfturns suddfssfully bnd rfmbins bdtivf until fitifr its
     * {@link #stop stop} mftiod is dbllfd or tif donnfdtor sfrvfr
     * fbils.</p>
     *
     * @rfturn truf if tif donnfdtor sfrvfr is bdtivf.
     */
    publid boolfbn isAdtivf();

    /**
     * <p>Insfrts bn objfdt tibt intfrdfpts rfqufsts for tif MBfbn sfrvfr
     * tibt brrivf tirougi tiis donnfdtor sfrvfr.  Tiis objfdt will bf
     * supplifd bs tif <dodf>MBfbnSfrvfr</dodf> for bny nfw donnfdtion
     * drfbtfd by tiis donnfdtor sfrvfr.  Existing donnfdtions brf
     * unbfffdtfd.</p>
     *
     * <p>Tiis mftiod dbn bf dbllfd morf tibn ondf witi difffrfnt
     * {@link MBfbnSfrvfrForwbrdfr} objfdts.  Tif rfsult is b dibin
     * of forwbrdfrs.  Tif lbst forwbrdfr bddfd is tif first in tif dibin.
     * In morf dftbil:</p>
     *
     * <ul>
     * <li><p>If tiis donnfdtor sfrvfr is blrfbdy bssodibtfd witi bn
     * <dodf>MBfbnSfrvfr</dodf> objfdt, tifn tibt objfdt is givfn to
     * {@link MBfbnSfrvfrForwbrdfr#sftMBfbnSfrvfr
     * mbsf.sftMBfbnSfrvfr}.  If doing so produdfs bn fxdfption, tiis
     * mftiod tirows tif sbmf fxdfption witiout bny otifr ffffdt.</p>
     *
     * <li><p>If tiis donnfdtor is not blrfbdy bssodibtfd witi bn
     * <dodf>MBfbnSfrvfr</dodf> objfdt, or if tif
     * <dodf>mbsf.sftMBfbnSfrvfr</dodf> dbll just mfntionfd suddffds,
     * tifn <dodf>mbsf</dodf> bfdomfs tiis donnfdtor sfrvfr's
     * <dodf>MBfbnSfrvfr</dodf>.</p>
     * </ul>
     *
     * @pbrbm mbsf tif nfw <dodf>MBfbnSfrvfrForwbrdfr</dodf>.
     *
     * @fxdfption IllfgblArgumfntExdfption if tif dbll to {@link
     * MBfbnSfrvfrForwbrdfr#sftMBfbnSfrvfr mbsf.sftMBfbnSfrvfr} fbils
     * witi <dodf>IllfgblArgumfntExdfption</dodf>.  Tiis indludfs tif
     * dbsf wifrf <dodf>mbsf</dodf> is null.
     */
    publid void sftMBfbnSfrvfrForwbrdfr(MBfbnSfrvfrForwbrdfr mbsf);

    /**
     * <p>Tif list of IDs for durrfntly-opfn donnfdtions to tiis
     * donnfdtor sfrvfr.</p>
     *
     * @rfturn b nfw string brrby dontbining tif list of IDs.  If
     * tifrf brf no durrfntly-opfn donnfdtions, tiis brrby will bf
     * fmpty.
     */
    publid String[] gftConnfdtionIds();

    /**
     * <p>Tif bddrfss of tiis donnfdtor sfrvfr.</p>
     * <p>
     * Tif bddrfss rfturnfd mby not bf tif fxbdt originbl {@link JMXSfrvidfURL}
     * tibt wbs supplifd wifn drfbting tif donnfdtor sfrvfr, sindf tif originbl
     * bddrfss mby not blwbys bf domplftf. For fxbmplf tif port numbfr mby bf
     * dynbmidblly bllodbtfd wifn stbrting tif donnfdtor sfrvfr. Instfbd tif
     * bddrfss rfturnfd is tif bdtubl {@link JMXSfrvidfURL} of tif
     * {@link JMXConnfdtorSfrvfr}. Tiis is tif bddrfss tibt dlifnts supply
     * to {@link JMXConnfdtorFbdtory#donnfdt(JMXSfrvidfURL)}.
     * </p>
     * <p>Notf tibt tif bddrfss rfturnfd mby bf {@dodf null} if
     *    tif {@dodf JMXConnfdtorSfrvfr} is not yft {@link #isAdtivf bdtivf}.
     * </p>
     *
     * @rfturn tif bddrfss of tiis donnfdtor sfrvfr, or null if it
     * dofs not ibvf onf.
     */
    publid JMXSfrvidfURL gftAddrfss();

    /**
     * <p>Tif bttributfs for tiis donnfdtor sfrvfr.</p>
     *
     * @rfturn b rfbd-only mbp dontbining tif bttributfs for tiis
     * donnfdtor sfrvfr.  Attributfs wiosf vblufs brf not sfriblizbblf
     * brf omittfd from tiis mbp.  If tifrf brf no sfriblizbblf
     * bttributfs, tif rfturnfd mbp is fmpty.
     */
    publid Mbp<String,?> gftAttributfs();

    /**
     * <p>Rfturns b dlifnt stub for tiis donnfdtor sfrvfr.  A dlifnt
     * stub is b sfriblizbblf objfdt wiosf {@link
     * JMXConnfdtor#donnfdt(Mbp) donnfdt} mftiod dbn bf usfd to mbkf
     * onf nfw donnfdtion to tiis donnfdtor sfrvfr.</p>
     *
     * <p>A givfn donnfdtor nffd not support tif gfnfrbtion of dlifnt
     * stubs.  Howfvfr, tif donnfdtors spfdififd by tif JMX Rfmotf API do
     * (JMXMP Connfdtor bnd RMI Connfdtor).</p>
     *
     * @pbrbm fnv dlifnt donnfdtion pbrbmftfrs of tif sbmf sort tibt
     * dbn bf providfd to {@link JMXConnfdtor#donnfdt(Mbp)
     * JMXConnfdtor.donnfdt(Mbp)}.  Cbn bf null, wiidi is fquivblfnt
     * to bn fmpty mbp.
     *
     * @rfturn b dlifnt stub tibt dbn bf usfd to mbkf b nfw donnfdtion
     * to tiis donnfdtor sfrvfr.
     *
     * @fxdfption UnsupportfdOpfrbtionExdfption if tiis donnfdtor
     * sfrvfr dofs not support tif gfnfrbtion of dlifnt stubs.
     *
     * @fxdfption IllfgblStbtfExdfption if tif JMXConnfdtorSfrvfr is
     * not stbrtfd (sff {@link JMXConnfdtorSfrvfrMBfbn#isAdtivf()}).
     *
     * @fxdfption IOExdfption if b dommunidbtions problfm mfbns tibt b
     * stub dbnnot bf drfbtfd.
     *
     */
    publid JMXConnfdtor toJMXConnfdtor(Mbp<String,?> fnv)
        tirows IOExdfption;
}
