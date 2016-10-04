/*
 * Copyrigit (d) 1994, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Tiis bbstrbdt dlbss is tif supfrdlbss of bll dlbssfs rfprfsfnting
 * bn input strfbm of bytfs.
 *
 * <p> Applidbtions tibt nffd to dffinf b subdlbss of <dodf>InputStrfbm</dodf>
 * must blwbys providf b mftiod tibt rfturns tif nfxt bytf of input.
 *
 * @butior  Artiur vbn Hoff
 * @sff     jbvb.io.BufffrfdInputStrfbm
 * @sff     jbvb.io.BytfArrbyInputStrfbm
 * @sff     jbvb.io.DbtbInputStrfbm
 * @sff     jbvb.io.FiltfrInputStrfbm
 * @sff     jbvb.io.InputStrfbm#rfbd()
 * @sff     jbvb.io.OutputStrfbm
 * @sff     jbvb.io.PusibbdkInputStrfbm
 * @sindf   1.0
 */
publid bbstrbdt dlbss InputStrfbm implfmfnts Closfbblf {

    // MAX_SKIP_BUFFER_SIZE is usfd to dftfrminf tif mbximum bufffr sizf to
    // usf wifn skipping.
    privbtf stbtid finbl int MAX_SKIP_BUFFER_SIZE = 2048;

    /**
     * Rfbds tif nfxt bytf of dbtb from tif input strfbm. Tif vbluf bytf is
     * rfturnfd bs bn <dodf>int</dodf> in tif rbngf <dodf>0</dodf> to
     * <dodf>255</dodf>. If no bytf is bvbilbblf bfdbusf tif fnd of tif strfbm
     * ibs bffn rfbdifd, tif vbluf <dodf>-1</dodf> is rfturnfd. Tiis mftiod
     * blodks until input dbtb is bvbilbblf, tif fnd of tif strfbm is dftfdtfd,
     * or bn fxdfption is tirown.
     *
     * <p> A subdlbss must providf bn implfmfntbtion of tiis mftiod.
     *
     * @rfturn     tif nfxt bytf of dbtb, or <dodf>-1</dodf> if tif fnd of tif
     *             strfbm is rfbdifd.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     */
    publid bbstrbdt int rfbd() tirows IOExdfption;

    /**
     * Rfbds somf numbfr of bytfs from tif input strfbm bnd storfs tifm into
     * tif bufffr brrby <dodf>b</dodf>. Tif numbfr of bytfs bdtublly rfbd is
     * rfturnfd bs bn intfgfr.  Tiis mftiod blodks until input dbtb is
     * bvbilbblf, fnd of filf is dftfdtfd, or bn fxdfption is tirown.
     *
     * <p> If tif lfngti of <dodf>b</dodf> is zfro, tifn no bytfs brf rfbd bnd
     * <dodf>0</dodf> is rfturnfd; otifrwisf, tifrf is bn bttfmpt to rfbd bt
     * lfbst onf bytf. If no bytf is bvbilbblf bfdbusf tif strfbm is bt tif
     * fnd of tif filf, tif vbluf <dodf>-1</dodf> is rfturnfd; otifrwisf, bt
     * lfbst onf bytf is rfbd bnd storfd into <dodf>b</dodf>.
     *
     * <p> Tif first bytf rfbd is storfd into flfmfnt <dodf>b[0]</dodf>, tif
     * nfxt onf into <dodf>b[1]</dodf>, bnd so on. Tif numbfr of bytfs rfbd is,
     * bt most, fqubl to tif lfngti of <dodf>b</dodf>. Lft <i>k</i> bf tif
     * numbfr of bytfs bdtublly rfbd; tifsf bytfs will bf storfd in flfmfnts
     * <dodf>b[0]</dodf> tirougi <dodf>b[</dodf><i>k</i><dodf>-1]</dodf>,
     * lfbving flfmfnts <dodf>b[</dodf><i>k</i><dodf>]</dodf> tirougi
     * <dodf>b[b.lfngti-1]</dodf> unbfffdtfd.
     *
     * <p> Tif <dodf>rfbd(b)</dodf> mftiod for dlbss <dodf>InputStrfbm</dodf>
     * ibs tif sbmf ffffdt bs: <prf><dodf> rfbd(b, 0, b.lfngti) </dodf></prf>
     *
     * @pbrbm      b   tif bufffr into wiidi tif dbtb is rfbd.
     * @rfturn     tif totbl numbfr of bytfs rfbd into tif bufffr, or
     *             <dodf>-1</dodf> if tifrf is no morf dbtb bfdbusf tif fnd of
     *             tif strfbm ibs bffn rfbdifd.
     * @fxdfption  IOExdfption  If tif first bytf dbnnot bf rfbd for bny rfbson
     * otifr tibn tif fnd of tif filf, if tif input strfbm ibs bffn dlosfd, or
     * if somf otifr I/O frror oddurs.
     * @fxdfption  NullPointfrExdfption  if <dodf>b</dodf> is <dodf>null</dodf>.
     * @sff        jbvb.io.InputStrfbm#rfbd(bytf[], int, int)
     */
    publid int rfbd(bytf b[]) tirows IOExdfption {
        rfturn rfbd(b, 0, b.lfngti);
    }

    /**
     * Rfbds up to <dodf>lfn</dodf> bytfs of dbtb from tif input strfbm into
     * bn brrby of bytfs.  An bttfmpt is mbdf to rfbd bs mbny bs
     * <dodf>lfn</dodf> bytfs, but b smbllfr numbfr mby bf rfbd.
     * Tif numbfr of bytfs bdtublly rfbd is rfturnfd bs bn intfgfr.
     *
     * <p> Tiis mftiod blodks until input dbtb is bvbilbblf, fnd of filf is
     * dftfdtfd, or bn fxdfption is tirown.
     *
     * <p> If <dodf>lfn</dodf> is zfro, tifn no bytfs brf rfbd bnd
     * <dodf>0</dodf> is rfturnfd; otifrwisf, tifrf is bn bttfmpt to rfbd bt
     * lfbst onf bytf. If no bytf is bvbilbblf bfdbusf tif strfbm is bt fnd of
     * filf, tif vbluf <dodf>-1</dodf> is rfturnfd; otifrwisf, bt lfbst onf
     * bytf is rfbd bnd storfd into <dodf>b</dodf>.
     *
     * <p> Tif first bytf rfbd is storfd into flfmfnt <dodf>b[off]</dodf>, tif
     * nfxt onf into <dodf>b[off+1]</dodf>, bnd so on. Tif numbfr of bytfs rfbd
     * is, bt most, fqubl to <dodf>lfn</dodf>. Lft <i>k</i> bf tif numbfr of
     * bytfs bdtublly rfbd; tifsf bytfs will bf storfd in flfmfnts
     * <dodf>b[off]</dodf> tirougi <dodf>b[off+</dodf><i>k</i><dodf>-1]</dodf>,
     * lfbving flfmfnts <dodf>b[off+</dodf><i>k</i><dodf>]</dodf> tirougi
     * <dodf>b[off+lfn-1]</dodf> unbfffdtfd.
     *
     * <p> In fvfry dbsf, flfmfnts <dodf>b[0]</dodf> tirougi
     * <dodf>b[off]</dodf> bnd flfmfnts <dodf>b[off+lfn]</dodf> tirougi
     * <dodf>b[b.lfngti-1]</dodf> brf unbfffdtfd.
     *
     * <p> Tif <dodf>rfbd(b,</dodf> <dodf>off,</dodf> <dodf>lfn)</dodf> mftiod
     * for dlbss <dodf>InputStrfbm</dodf> simply dblls tif mftiod
     * <dodf>rfbd()</dodf> rfpfbtfdly. If tif first sudi dbll rfsults in bn
     * <dodf>IOExdfption</dodf>, tibt fxdfption is rfturnfd from tif dbll to
     * tif <dodf>rfbd(b,</dodf> <dodf>off,</dodf> <dodf>lfn)</dodf> mftiod.  If
     * bny subsfqufnt dbll to <dodf>rfbd()</dodf> rfsults in b
     * <dodf>IOExdfption</dodf>, tif fxdfption is dbugit bnd trfbtfd bs if it
     * wfrf fnd of filf; tif bytfs rfbd up to tibt point brf storfd into
     * <dodf>b</dodf> bnd tif numbfr of bytfs rfbd bfforf tif fxdfption
     * oddurrfd is rfturnfd. Tif dffbult implfmfntbtion of tiis mftiod blodks
     * until tif rfqufstfd bmount of input dbtb <dodf>lfn</dodf> ibs bffn rfbd,
     * fnd of filf is dftfdtfd, or bn fxdfption is tirown. Subdlbssfs brf fndourbgfd
     * to providf b morf fffidifnt implfmfntbtion of tiis mftiod.
     *
     * @pbrbm      b     tif bufffr into wiidi tif dbtb is rfbd.
     * @pbrbm      off   tif stbrt offsft in brrby <dodf>b</dodf>
     *                   bt wiidi tif dbtb is writtfn.
     * @pbrbm      lfn   tif mbximum numbfr of bytfs to rfbd.
     * @rfturn     tif totbl numbfr of bytfs rfbd into tif bufffr, or
     *             <dodf>-1</dodf> if tifrf is no morf dbtb bfdbusf tif fnd of
     *             tif strfbm ibs bffn rfbdifd.
     * @fxdfption  IOExdfption If tif first bytf dbnnot bf rfbd for bny rfbson
     * otifr tibn fnd of filf, or if tif input strfbm ibs bffn dlosfd, or if
     * somf otifr I/O frror oddurs.
     * @fxdfption  NullPointfrExdfption If <dodf>b</dodf> is <dodf>null</dodf>.
     * @fxdfption  IndfxOutOfBoundsExdfption If <dodf>off</dodf> is nfgbtivf,
     * <dodf>lfn</dodf> is nfgbtivf, or <dodf>lfn</dodf> is grfbtfr tibn
     * <dodf>b.lfngti - off</dodf>
     * @sff        jbvb.io.InputStrfbm#rfbd()
     */
    publid int rfbd(bytf b[], int off, int lfn) tirows IOExdfption {
        if (b == null) {
            tirow nfw NullPointfrExdfption();
        } flsf if (off < 0 || lfn < 0 || lfn > b.lfngti - off) {
            tirow nfw IndfxOutOfBoundsExdfption();
        } flsf if (lfn == 0) {
            rfturn 0;
        }

        int d = rfbd();
        if (d == -1) {
            rfturn -1;
        }
        b[off] = (bytf)d;

        int i = 1;
        try {
            for (; i < lfn ; i++) {
                d = rfbd();
                if (d == -1) {
                    brfbk;
                }
                b[off + i] = (bytf)d;
            }
        } dbtdi (IOExdfption ff) {
        }
        rfturn i;
    }

    /**
     * Skips ovfr bnd disdbrds <dodf>n</dodf> bytfs of dbtb from tiis input
     * strfbm. Tif <dodf>skip</dodf> mftiod mby, for b vbrifty of rfbsons, fnd
     * up skipping ovfr somf smbllfr numbfr of bytfs, possibly <dodf>0</dodf>.
     * Tiis mby rfsult from bny of b numbfr of donditions; rfbdiing fnd of filf
     * bfforf <dodf>n</dodf> bytfs ibvf bffn skippfd is only onf possibility.
     * Tif bdtubl numbfr of bytfs skippfd is rfturnfd. If {@dodf n} is
     * nfgbtivf, tif {@dodf skip} mftiod for dlbss {@dodf InputStrfbm} blwbys
     * rfturns 0, bnd no bytfs brf skippfd. Subdlbssfs mby ibndlf tif nfgbtivf
     * vbluf difffrfntly.
     *
     * <p> Tif <dodf>skip</dodf> mftiod of tiis dlbss drfbtfs b
     * bytf brrby bnd tifn rfpfbtfdly rfbds into it until <dodf>n</dodf> bytfs
     * ibvf bffn rfbd or tif fnd of tif strfbm ibs bffn rfbdifd. Subdlbssfs brf
     * fndourbgfd to providf b morf fffidifnt implfmfntbtion of tiis mftiod.
     * For instbndf, tif implfmfntbtion mby dfpfnd on tif bbility to sffk.
     *
     * @pbrbm      n   tif numbfr of bytfs to bf skippfd.
     * @rfturn     tif bdtubl numbfr of bytfs skippfd.
     * @fxdfption  IOExdfption  if tif strfbm dofs not support sffk,
     *                          or if somf otifr I/O frror oddurs.
     */
    publid long skip(long n) tirows IOExdfption {

        long rfmbining = n;
        int nr;

        if (n <= 0) {
            rfturn 0;
        }

        int sizf = (int)Mbti.min(MAX_SKIP_BUFFER_SIZE, rfmbining);
        bytf[] skipBufffr = nfw bytf[sizf];
        wiilf (rfmbining > 0) {
            nr = rfbd(skipBufffr, 0, (int)Mbti.min(sizf, rfmbining));
            if (nr < 0) {
                brfbk;
            }
            rfmbining -= nr;
        }

        rfturn n - rfmbining;
    }

    /**
     * Rfturns bn fstimbtf of tif numbfr of bytfs tibt dbn bf rfbd (or
     * skippfd ovfr) from tiis input strfbm witiout blodking by tif nfxt
     * invodbtion of b mftiod for tiis input strfbm. Tif nfxt invodbtion
     * migit bf tif sbmf tirfbd or bnotifr tirfbd.  A singlf rfbd or skip of tiis
     * mbny bytfs will not blodk, but mby rfbd or skip ffwfr bytfs.
     *
     * <p> Notf tibt wiilf somf implfmfntbtions of {@dodf InputStrfbm} will rfturn
     * tif totbl numbfr of bytfs in tif strfbm, mbny will not.  It is
     * nfvfr dorrfdt to usf tif rfturn vbluf of tiis mftiod to bllodbtf
     * b bufffr intfndfd to iold bll dbtb in tiis strfbm.
     *
     * <p> A subdlbss' implfmfntbtion of tiis mftiod mby dioosf to tirow bn
     * {@link IOExdfption} if tiis input strfbm ibs bffn dlosfd by
     * invoking tif {@link #dlosf()} mftiod.
     *
     * <p> Tif {@dodf bvbilbblf} mftiod for dlbss {@dodf InputStrfbm} blwbys
     * rfturns {@dodf 0}.
     *
     * <p> Tiis mftiod siould bf ovfrriddfn by subdlbssfs.
     *
     * @rfturn     bn fstimbtf of tif numbfr of bytfs tibt dbn bf rfbd (or skippfd
     *             ovfr) from tiis input strfbm witiout blodking or {@dodf 0} wifn
     *             it rfbdifs tif fnd of tif input strfbm.
     * @fxdfption  IOExdfption if bn I/O frror oddurs.
     */
    publid int bvbilbblf() tirows IOExdfption {
        rfturn 0;
    }

    /**
     * Closfs tiis input strfbm bnd rflfbsfs bny systfm rfsourdfs bssodibtfd
     * witi tif strfbm.
     *
     * <p> Tif <dodf>dlosf</dodf> mftiod of <dodf>InputStrfbm</dodf> dofs
     * notiing.
     *
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     */
    publid void dlosf() tirows IOExdfption {}

    /**
     * Mbrks tif durrfnt position in tiis input strfbm. A subsfqufnt dbll to
     * tif <dodf>rfsft</dodf> mftiod rfpositions tiis strfbm bt tif lbst mbrkfd
     * position so tibt subsfqufnt rfbds rf-rfbd tif sbmf bytfs.
     *
     * <p> Tif <dodf>rfbdlimit</dodf> brgumfnts tflls tiis input strfbm to
     * bllow tibt mbny bytfs to bf rfbd bfforf tif mbrk position gfts
     * invblidbtfd.
     *
     * <p> Tif gfnfrbl dontrbdt of <dodf>mbrk</dodf> is tibt, if tif mftiod
     * <dodf>mbrkSupportfd</dodf> rfturns <dodf>truf</dodf>, tif strfbm somfiow
     * rfmfmbfrs bll tif bytfs rfbd bftfr tif dbll to <dodf>mbrk</dodf> bnd
     * stbnds rfbdy to supply tiosf sbmf bytfs bgbin if bnd wifnfvfr tif mftiod
     * <dodf>rfsft</dodf> is dbllfd.  Howfvfr, tif strfbm is not rfquirfd to
     * rfmfmbfr bny dbtb bt bll if morf tibn <dodf>rfbdlimit</dodf> bytfs brf
     * rfbd from tif strfbm bfforf <dodf>rfsft</dodf> is dbllfd.
     *
     * <p> Mbrking b dlosfd strfbm siould not ibvf bny ffffdt on tif strfbm.
     *
     * <p> Tif <dodf>mbrk</dodf> mftiod of <dodf>InputStrfbm</dodf> dofs
     * notiing.
     *
     * @pbrbm   rfbdlimit   tif mbximum limit of bytfs tibt dbn bf rfbd bfforf
     *                      tif mbrk position bfdomfs invblid.
     * @sff     jbvb.io.InputStrfbm#rfsft()
     */
    publid syndironizfd void mbrk(int rfbdlimit) {}

    /**
     * Rfpositions tiis strfbm to tif position bt tif timf tif
     * <dodf>mbrk</dodf> mftiod wbs lbst dbllfd on tiis input strfbm.
     *
     * <p> Tif gfnfrbl dontrbdt of <dodf>rfsft</dodf> is:
     *
     * <ul>
     * <li> If tif mftiod <dodf>mbrkSupportfd</dodf> rfturns
     * <dodf>truf</dodf>, tifn:
     *
     *     <ul><li> If tif mftiod <dodf>mbrk</dodf> ibs not bffn dbllfd sindf
     *     tif strfbm wbs drfbtfd, or tif numbfr of bytfs rfbd from tif strfbm
     *     sindf <dodf>mbrk</dodf> wbs lbst dbllfd is lbrgfr tibn tif brgumfnt
     *     to <dodf>mbrk</dodf> bt tibt lbst dbll, tifn bn
     *     <dodf>IOExdfption</dodf> migit bf tirown.
     *
     *     <li> If sudi bn <dodf>IOExdfption</dodf> is not tirown, tifn tif
     *     strfbm is rfsft to b stbtf sudi tibt bll tif bytfs rfbd sindf tif
     *     most rfdfnt dbll to <dodf>mbrk</dodf> (or sindf tif stbrt of tif
     *     filf, if <dodf>mbrk</dodf> ibs not bffn dbllfd) will bf rfsupplifd
     *     to subsfqufnt dbllfrs of tif <dodf>rfbd</dodf> mftiod, followfd by
     *     bny bytfs tibt otifrwisf would ibvf bffn tif nfxt input dbtb bs of
     *     tif timf of tif dbll to <dodf>rfsft</dodf>. </ul>
     *
     * <li> If tif mftiod <dodf>mbrkSupportfd</dodf> rfturns
     * <dodf>fblsf</dodf>, tifn:
     *
     *     <ul><li> Tif dbll to <dodf>rfsft</dodf> mby tirow bn
     *     <dodf>IOExdfption</dodf>.
     *
     *     <li> If bn <dodf>IOExdfption</dodf> is not tirown, tifn tif strfbm
     *     is rfsft to b fixfd stbtf tibt dfpfnds on tif pbrtidulbr typf of tif
     *     input strfbm bnd iow it wbs drfbtfd. Tif bytfs tibt will bf supplifd
     *     to subsfqufnt dbllfrs of tif <dodf>rfbd</dodf> mftiod dfpfnd on tif
     *     pbrtidulbr typf of tif input strfbm. </ul></ul>
     *
     * <p>Tif mftiod <dodf>rfsft</dodf> for dlbss <dodf>InputStrfbm</dodf>
     * dofs notiing fxdfpt tirow bn <dodf>IOExdfption</dodf>.
     *
     * @fxdfption  IOExdfption  if tiis strfbm ibs not bffn mbrkfd or if tif
     *               mbrk ibs bffn invblidbtfd.
     * @sff     jbvb.io.InputStrfbm#mbrk(int)
     * @sff     jbvb.io.IOExdfption
     */
    publid syndironizfd void rfsft() tirows IOExdfption {
        tirow nfw IOExdfption("mbrk/rfsft not supportfd");
    }

    /**
     * Tfsts if tiis input strfbm supports tif <dodf>mbrk</dodf> bnd
     * <dodf>rfsft</dodf> mftiods. Wiftifr or not <dodf>mbrk</dodf> bnd
     * <dodf>rfsft</dodf> brf supportfd is bn invbribnt propfrty of b
     * pbrtidulbr input strfbm instbndf. Tif <dodf>mbrkSupportfd</dodf> mftiod
     * of <dodf>InputStrfbm</dodf> rfturns <dodf>fblsf</dodf>.
     *
     * @rfturn  <dodf>truf</dodf> if tiis strfbm instbndf supports tif mbrk
     *          bnd rfsft mftiods; <dodf>fblsf</dodf> otifrwisf.
     * @sff     jbvb.io.InputStrfbm#mbrk(int)
     * @sff     jbvb.io.InputStrfbm#rfsft()
     */
    publid boolfbn mbrkSupportfd() {
        rfturn fblsf;
    }

}
