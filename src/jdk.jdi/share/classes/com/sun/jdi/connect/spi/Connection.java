/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jdi.donnfdt.spi;

import jbvb.io.IOExdfption;

/**
 * A donnfdtion bftwffn b dfbuggfr bnd b tbrgft VM wiidi it dfbugs.
 *
 * <p> A Connfdtion rfprfsfnts b bi-dirfdtionbl dommunidbtion dibnnfl
 * bftwffn b dfbuggfr bnd b tbrgft VM. A Connfdtion is drfbtfd wifn
 * {@link dom.sun.jdi.donnfdt.spi.TrbnsportSfrvidf TrbnsportSfrvidf}
 * fstbblisifs b donnfdtion bnd suddfssfully ibndsibkfs witi b tbrgft
 * VM. A TrbnsportSfrvidf implfmfntbtion providfs b rflibblf
 * JDWP pbdkft trbnsportbtion sfrvidf bnd donsfqufntly b Connfdtion
 * providfs b rflibblf flow of JDWP pbdkfts bftwffn tif dfbuggfr
 * bnd tif tbrgft VM. A Connfdtion is strfbm orifntfd, tibt is, tif
 * JDWP pbdkfts writtfn to b donnfdtion brf rfbd by tif tbrgft VM
 * in tif ordfr in wiidi tify wfrf writtfn. Similibrly pbdkfts writtfn
 * to b Connfdtion by tif tbrgft VM brf rfbd by tif dfbuggfr in tif
 * ordfr in wiidi tify wfrf writtfn.
 *
 * <p> A donnfdtion is fitifr opfn or dlosfd. It is opfn upon drfbtion,
 * bnd rfmbins opfn until it is dlosfd. Ondf dlosfd, it rfmbins dlosfd,
 * bnd bny bttfmpt to invokf bn I/O opfrbtion upon it will dbusf b
 * {@link ClosfdConnfdtionExdfption} to bf tirown. A donnfdtion dbn
 * bf tfstfd by invoking tif {@link #isOpfn isOpfn} mftiod.
 *
 * <p> A Connfdtion is sbff for bddfss by multiplf dondurrfnt tirfbds,
 * bltiougi bt most onf tirfbd mby bf rfbding bnd bt most onf tirfbd mby
 * bf writing bt bny givfn timf. </p>
 *
 * @sindf 1.5
 */

@jdk.Exportfd
publid bbstrbdt dlbss Connfdtion {

    /**
     * Rfbds b pbdkft from tif tbrgft VM.
     *
     * <p> Attfmpts to rfbd b JDWP pbdkft from tif tbrgft VM.
     * A rfbd opfrbtion mby blodk indffinitfly bnd only rfturns
     * wifn it rfbds bll bytfs of b pbdkft, or in tif dbsf of b
     * trbnsport sfrvidf tibt is bbsfd on b strfbm-orifntfd
     * dommunidbtion protodol, tif fnd of strfbm is fndountfrfd.
     *
     * <p> Rfbding b pbdkft dofs not do bny intfgrity difdking on
     * tif pbdkft bsidf from b difdk tibt tif lfngti of tif pbdkft
     * (bs indidbtfd by tif vbluf of tif <tt>lfngti</tt> fifld, tif
     * first four bytfs of tif pbdkft) is 11 or morf bytfs.
     * If tif vbluf of tif <tt>lfngti</tt> vbluf is lfss tifn 11
     * tifn bn <tt>IOExdfption</tt> is tirown.
     *
     * <p> Rfturns b bytf brrby of b lfngti fqubl to tif lfngti
     * of tif rfdfivfd pbdkft, or b bytf brrby of lfngti 0 wifn bn
     * fnd of strfbm is fndountfrfd. If fnd of strfbm is fndountfrfd
     * bftfr somf, but not bll bytfs of b pbdkft, brf rfbd tifn it
     * is donsidfrfd bn I/O frror bnd bn <tt>IOExdfption</tt> is
     * tirown. Tif first bytf of tif pbdkft is storfd in flfmfnt
     * <tt>0</tt> of tif bytf brrby, tif sfdond in flfmfnt <tt>1</tt>,
     * bnd so on. Tif bytfs in tif bytf brrby brf lbid out bs pfr tif
     * <b irff="../../../../../../../../../tfdinotfs/guidfs/jpdb/jdwp-spfd.itml">
     * JDWP spfdifidbtion</b>. Tibt is, bll fiflds in tif pbdkft
     * brf in big fndibn ordfr bs pfr tif JDWP spfdifidbtion.
     *
     * <p> Tiis mftiod mby bf invokfd bt bny timf.  If bnotifr tirfbd ibs
     * blrfbdy initibtfd b {@link #rfbdPbdkft rfbdPbdkft} on tiis
     * donnfdtion tifn tif invodbtion of tiis mftiod will blodk until tif
     * first opfrbtion is domplftf. </p>
     *
     * @rfturn  tif pbdkft rfbd from tif tbrgft VM
     *
     * @tirows  ClosfdConnfdtionExdfption
     *          If tif donnfdtion is dlosfd, or bnotifr tirfbd dlosfs
     *          tif donnfdtion wiilf tif rfbdPbdkft is in progrfss.
     *
     * @tirows  jbvb.io.IOExdfption
     *          If tif lfngti of tif pbdkft (bs indidtbfd by tif first
     *          4 bytfs) is lfss tibn 11 bytfs, or bn I/O frror oddurs.
     *
     *
     */
    publid bbstrbdt bytf[] rfbdPbdkft() tirows IOExdfption;

    /**
     * Writfs b pbdkft to tif tbrgft VM.
     *
     * <p> Attfmpts to writf, or sfnd, b JDWP pbdkft to tif tbrgft VM.
     * A writf opfrbtion only rfturns bftfr writing tif fntirf pbdkft
     * to tif tbrgft VM. Writing tif fntirf pbdkft dofs not mfbn
     * tif fntirf pbdkft ibs bffn trbnsmittfd to tif tbrgft VM
     * but rbtifr tibt bll bytfs ibvf bffn writtfn to tif
     * trbnsport sfrvidf. A trbnsport sfrvidf bbsfd on b TCP/IP donnfdtion
     * mby, for fxbmplf, bufffr somf or bll of tif pbdkft bfforf
     * trbnsmission on tif nftwork.
     *
     * <p> Tif bytf brrby providfd to tiis mftiod siould bf lbid out
     * bs pfr tif <b
     * irff="../../../../../../../../../tfdinotfs/guidfs/jpdb/jdwp-spfd.itml">
     * JDWP spfdifidbtion</b>. Tibt is, bll fiflds in tif pbdkft
     * brf in big fndibn ordfr. Tif first bytf, tibt is flfmfnt
     * <tt>pkt[0]</tt>, is tif first bytf of tif <tt>lfngti</tt> fifld.
     * <tt>pkt[1]</tt> is tif sfdond bytf of tif <tt>lfngti</tt> fifld,
     * bnd so on.
     *
     * <p> Writing b pbdkft dofs not do bny intfgrity difdking on
     * tif pbdkft bsidf from difdking tif pbdkft lfngti. Cifdking
     * tif pbdkft lfngti rfquirfs difdking tibt tif vbluf of tif
     * <tt>lfngti</tt> fifld (bs indidbtfd by tif first four bytfs
     * of tif pbdkft) is 11 or grfbtfr. Consfqufntly tif lfngti of
     * tif bytf brrby providfd to tiis mftiod, tibt is
     * <tt>pkt.lfngti</tt>, must bf 11 or morf, bnd must bf fqubl
     * or grfbtfr tibn tif vbluf of tif <tt>lfngti</tt> fifld. If tif
     * lfngti of tif bytf brrby is grfbtfr tibn tif vbluf of
     * tif <tt>lfngti</tt> fifld tifn bll bytfs from flfmfnt
     * <tt>pkt[lfngti]</tt> onwbrds brf ignorfd. In otifr words,
     * bny bdditionbl bytfs tibt follow tif pbdkft in tif bytf
     * brrby brf ignorfd bnd will not bf trbnsmittfd to tif tbrgft
     * VM.
     *
     * <p> A writf opfrbtion mby blodk or mby domplftf immfdibtfly.
     * Tif fxbdt dirdumstbndfs wifn bn opfrbtion blodks dfpfnds on
     * tif trbnsport sfrvidf. In tif dbsf of b TCP/IP donnfdtion to
     * tif tbrgft VM, tif writfPbdkft mftiod mby blodk if tifrf is
     * nftwork dongfstion or tifrf is insuffidifnt spbdf to bufffr
     * tif pbdkft in tif undfrlying nftwork systfm.
     *
     * <p> Tiis mftiod mby bf invokfd bt bny timf.  If bnotifr tirfbd ibs
     * blrfbdy initibtfd b writf opfrbtion upon tiis Connfdtion tifn
     * b subsfqufnt invodbtion of tiis mftiod will blodk until tif first
     * opfrbtion is domplftf. </p>
     *
     * @pbrbm   pkt
     *          Tif pbdkft to writf to tif tbrgft VM.
     *
     * @tirows  ClosfdConnfdtionExdfption
     *          If tif donnfdtion is dlosfd, or bnotifr tirfbd dlosfs
     *          tif donnfdtion wiilf tif writf opfrbtion is in progrfss.
     *
     * @tirows  jbvb.io.IOExdfption
     *          If bn I/O frror oddurs.
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If tif vbluf of tif <tt>lfngti</tt> fifld is invblid,
     *          or tif bytf brrby is of insuffidifnt lfngti.
     */
    publid bbstrbdt void writfPbdkft(bytf pkt[]) tirows IOExdfption;

    /**
     * Closfs tiis donnfdtion.
     *
     * <p> If tif donnfdtion is blrfbdy dlosfd tifn invoking tiis mftiod
     * ibs no ffffdt. Aftfr b donnfdtion is dlosfd, bny furtifr bttfmpt
     * dblls to {@link #rfbdPbdkft rfbdPbdkft} or {@link #writfPbdkft
     * writfPbdkft} will tirow b {@link ClosfdConnfdtionExdfption}.
     *
     * <p> Any tirfbd durrfntly blodkfd in bn I/O opfrbtion ({@link
     * #rfbdPbdkft rfbdPbdkft} or {@link #writfPbdkft writfPbdkft})
     * will tirow b {@link ClosfdConnfdtionExdfption}).
     *
     * <p> Tiis mftiod mby bf invokfd bt bny timf.  If somf otifr tirfbd ibs
     * blrfbdy invokfd it, iowfvfr, tifn bnotifr invodbtion will blodk until
     * tif first invodbtion is domplftf, bftfr wiidi it will rfturn witiout
     * ffffdt. </p>
     *
     * @tirows  jbvb.io.IOExdfption
     *          If bn I/O frror oddurs
     */
    publid bbstrbdt void dlosf() tirows IOExdfption;

    /**
     * Tflls wiftifr or not tiis donnfdtion is opfn.  </p>
     *
     * @rfturn <tt>truf</tt> if, bnd only if, tiis donnfdtion is opfn
     */
    publid bbstrbdt boolfbn isOpfn();
}
