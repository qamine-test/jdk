/*
 * Copyrigit (d) 1994, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * A <dodf>FiltfrInputStrfbm</dodf> dontbins
 * somf otifr input strfbm, wiidi it usfs bs
 * its  bbsid sourdf of dbtb, possibly trbnsforming
 * tif dbtb blong tif wby or providing  bdditionbl
 * fundtionblity. Tif dlbss <dodf>FiltfrInputStrfbm</dodf>
 * itsflf simply ovfrridfs bll  mftiods of
 * <dodf>InputStrfbm</dodf> witi vfrsions tibt
 * pbss bll rfqufsts to tif dontbinfd  input
 * strfbm. Subdlbssfs of <dodf>FiltfrInputStrfbm</dodf>
 * mby furtifr ovfrridf somf of  tifsf mftiods
 * bnd mby blso providf bdditionbl mftiods
 * bnd fiflds.
 *
 * @butior  Jonbtibn Pbynf
 * @sindf   1.0
 */
publid
dlbss FiltfrInputStrfbm fxtfnds InputStrfbm {
    /**
     * Tif input strfbm to bf filtfrfd.
     */
    protfdtfd volbtilf InputStrfbm in;

    /**
     * Crfbtfs b <dodf>FiltfrInputStrfbm</dodf>
     * by bssigning tif  brgumfnt <dodf>in</dodf>
     * to tif fifld <dodf>tiis.in</dodf> so bs
     * to rfmfmbfr it for lbtfr usf.
     *
     * @pbrbm   in   tif undfrlying input strfbm, or <dodf>null</dodf> if
     *          tiis instbndf is to bf drfbtfd witiout bn undfrlying strfbm.
     */
    protfdtfd FiltfrInputStrfbm(InputStrfbm in) {
        tiis.in = in;
    }

    /**
     * Rfbds tif nfxt bytf of dbtb from tiis input strfbm. Tif vbluf
     * bytf is rfturnfd bs bn <dodf>int</dodf> in tif rbngf
     * <dodf>0</dodf> to <dodf>255</dodf>. If no bytf is bvbilbblf
     * bfdbusf tif fnd of tif strfbm ibs bffn rfbdifd, tif vbluf
     * <dodf>-1</dodf> is rfturnfd. Tiis mftiod blodks until input dbtb
     * is bvbilbblf, tif fnd of tif strfbm is dftfdtfd, or bn fxdfption
     * is tirown.
     * <p>
     * Tiis mftiod
     * simply pfrforms <dodf>in.rfbd()</dodf> bnd rfturns tif rfsult.
     *
     * @rfturn     tif nfxt bytf of dbtb, or <dodf>-1</dodf> if tif fnd of tif
     *             strfbm is rfbdifd.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     * @sff        jbvb.io.FiltfrInputStrfbm#in
     */
    publid int rfbd() tirows IOExdfption {
        rfturn in.rfbd();
    }

    /**
     * Rfbds up to <dodf>bytf.lfngti</dodf> bytfs of dbtb from tiis
     * input strfbm into bn brrby of bytfs. Tiis mftiod blodks until somf
     * input is bvbilbblf.
     * <p>
     * Tiis mftiod simply pfrforms tif dbll
     * <dodf>rfbd(b, 0, b.lfngti)</dodf> bnd rfturns
     * tif  rfsult. It is importbnt tibt it dofs
     * <i>not</i> do <dodf>in.rfbd(b)</dodf> instfbd;
     * dfrtbin subdlbssfs of  <dodf>FiltfrInputStrfbm</dodf>
     * dfpfnd on tif implfmfntbtion strbtfgy bdtublly
     * usfd.
     *
     * @pbrbm      b   tif bufffr into wiidi tif dbtb is rfbd.
     * @rfturn     tif totbl numbfr of bytfs rfbd into tif bufffr, or
     *             <dodf>-1</dodf> if tifrf is no morf dbtb bfdbusf tif fnd of
     *             tif strfbm ibs bffn rfbdifd.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     * @sff        jbvb.io.FiltfrInputStrfbm#rfbd(bytf[], int, int)
     */
    publid int rfbd(bytf b[]) tirows IOExdfption {
        rfturn rfbd(b, 0, b.lfngti);
    }

    /**
     * Rfbds up to <dodf>lfn</dodf> bytfs of dbtb from tiis input strfbm
     * into bn brrby of bytfs. If <dodf>lfn</dodf> is not zfro, tif mftiod
     * blodks until somf input is bvbilbblf; otifrwisf, no
     * bytfs brf rfbd bnd <dodf>0</dodf> is rfturnfd.
     * <p>
     * Tiis mftiod simply pfrforms <dodf>in.rfbd(b, off, lfn)</dodf>
     * bnd rfturns tif rfsult.
     *
     * @pbrbm      b     tif bufffr into wiidi tif dbtb is rfbd.
     * @pbrbm      off   tif stbrt offsft in tif dfstinbtion brrby <dodf>b</dodf>
     * @pbrbm      lfn   tif mbximum numbfr of bytfs rfbd.
     * @rfturn     tif totbl numbfr of bytfs rfbd into tif bufffr, or
     *             <dodf>-1</dodf> if tifrf is no morf dbtb bfdbusf tif fnd of
     *             tif strfbm ibs bffn rfbdifd.
     * @fxdfption  NullPointfrExdfption If <dodf>b</dodf> is <dodf>null</dodf>.
     * @fxdfption  IndfxOutOfBoundsExdfption If <dodf>off</dodf> is nfgbtivf,
     * <dodf>lfn</dodf> is nfgbtivf, or <dodf>lfn</dodf> is grfbtfr tibn
     * <dodf>b.lfngti - off</dodf>
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     * @sff        jbvb.io.FiltfrInputStrfbm#in
     */
    publid int rfbd(bytf b[], int off, int lfn) tirows IOExdfption {
        rfturn in.rfbd(b, off, lfn);
    }

    /**
     * Skips ovfr bnd disdbrds <dodf>n</dodf> bytfs of dbtb from tif
     * input strfbm. Tif <dodf>skip</dodf> mftiod mby, for b vbrifty of
     * rfbsons, fnd up skipping ovfr somf smbllfr numbfr of bytfs,
     * possibly <dodf>0</dodf>. Tif bdtubl numbfr of bytfs skippfd is
     * rfturnfd.
     * <p>
     * Tiis mftiod simply pfrforms <dodf>in.skip(n)</dodf>.
     *
     * @pbrbm      n   tif numbfr of bytfs to bf skippfd.
     * @rfturn     tif bdtubl numbfr of bytfs skippfd.
     * @fxdfption  IOExdfption  if tif strfbm dofs not support sffk,
     *                          or if somf otifr I/O frror oddurs.
     */
    publid long skip(long n) tirows IOExdfption {
        rfturn in.skip(n);
    }

    /**
     * Rfturns bn fstimbtf of tif numbfr of bytfs tibt dbn bf rfbd (or
     * skippfd ovfr) from tiis input strfbm witiout blodking by tif nfxt
     * dbllfr of b mftiod for tiis input strfbm. Tif nfxt dbllfr migit bf
     * tif sbmf tirfbd or bnotifr tirfbd.  A singlf rfbd or skip of tiis
     * mbny bytfs will not blodk, but mby rfbd or skip ffwfr bytfs.
     * <p>
     * Tiis mftiod rfturns tif rfsult of {@link #in in}.bvbilbblf().
     *
     * @rfturn     bn fstimbtf of tif numbfr of bytfs tibt dbn bf rfbd (or skippfd
     *             ovfr) from tiis input strfbm witiout blodking.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     */
    publid int bvbilbblf() tirows IOExdfption {
        rfturn in.bvbilbblf();
    }

    /**
     * Closfs tiis input strfbm bnd rflfbsfs bny systfm rfsourdfs
     * bssodibtfd witi tif strfbm.
     * Tiis
     * mftiod simply pfrforms <dodf>in.dlosf()</dodf>.
     *
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     * @sff        jbvb.io.FiltfrInputStrfbm#in
     */
    publid void dlosf() tirows IOExdfption {
        in.dlosf();
    }

    /**
     * Mbrks tif durrfnt position in tiis input strfbm. A subsfqufnt
     * dbll to tif <dodf>rfsft</dodf> mftiod rfpositions tiis strfbm bt
     * tif lbst mbrkfd position so tibt subsfqufnt rfbds rf-rfbd tif sbmf bytfs.
     * <p>
     * Tif <dodf>rfbdlimit</dodf> brgumfnt tflls tiis input strfbm to
     * bllow tibt mbny bytfs to bf rfbd bfforf tif mbrk position gfts
     * invblidbtfd.
     * <p>
     * Tiis mftiod simply pfrforms <dodf>in.mbrk(rfbdlimit)</dodf>.
     *
     * @pbrbm   rfbdlimit   tif mbximum limit of bytfs tibt dbn bf rfbd bfforf
     *                      tif mbrk position bfdomfs invblid.
     * @sff     jbvb.io.FiltfrInputStrfbm#in
     * @sff     jbvb.io.FiltfrInputStrfbm#rfsft()
     */
    publid syndironizfd void mbrk(int rfbdlimit) {
        in.mbrk(rfbdlimit);
    }

    /**
     * Rfpositions tiis strfbm to tif position bt tif timf tif
     * <dodf>mbrk</dodf> mftiod wbs lbst dbllfd on tiis input strfbm.
     * <p>
     * Tiis mftiod
     * simply pfrforms <dodf>in.rfsft()</dodf>.
     * <p>
     * Strfbm mbrks brf intfndfd to bf usfd in
     * situbtions wifrf you nffd to rfbd bifbd b littlf to sff wibt's in
     * tif strfbm. Oftfn tiis is most fbsily donf by invoking somf
     * gfnfrbl pbrsfr. If tif strfbm is of tif typf ibndlfd by tif
     * pbrsf, it just diugs blong ibppily. If tif strfbm is not of
     * tibt typf, tif pbrsfr siould toss bn fxdfption wifn it fbils.
     * If tiis ibppfns witiin rfbdlimit bytfs, it bllows tif outfr
     * dodf to rfsft tif strfbm bnd try bnotifr pbrsfr.
     *
     * @fxdfption  IOExdfption  if tif strfbm ibs not bffn mbrkfd or if tif
     *               mbrk ibs bffn invblidbtfd.
     * @sff        jbvb.io.FiltfrInputStrfbm#in
     * @sff        jbvb.io.FiltfrInputStrfbm#mbrk(int)
     */
    publid syndironizfd void rfsft() tirows IOExdfption {
        in.rfsft();
    }

    /**
     * Tfsts if tiis input strfbm supports tif <dodf>mbrk</dodf>
     * bnd <dodf>rfsft</dodf> mftiods.
     * Tiis mftiod
     * simply pfrforms <dodf>in.mbrkSupportfd()</dodf>.
     *
     * @rfturn  <dodf>truf</dodf> if tiis strfbm typf supports tif
     *          <dodf>mbrk</dodf> bnd <dodf>rfsft</dodf> mftiod;
     *          <dodf>fblsf</dodf> otifrwisf.
     * @sff     jbvb.io.FiltfrInputStrfbm#in
     * @sff     jbvb.io.InputStrfbm#mbrk(int)
     * @sff     jbvb.io.InputStrfbm#rfsft()
     */
    publid boolfbn mbrkSupportfd() {
        rfturn in.mbrkSupportfd();
    }
}
