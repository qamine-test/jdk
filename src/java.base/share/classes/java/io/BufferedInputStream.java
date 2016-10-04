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
import jbvb.util.dondurrfnt.btomid.AtomidRfffrfndfFifldUpdbtfr;

/**
 * A <dodf>BufffrfdInputStrfbm</dodf> bdds
 * fundtionblity to bnotifr input strfbm-nbmfly,
 * tif bbility to bufffr tif input bnd to
 * support tif <dodf>mbrk</dodf> bnd <dodf>rfsft</dodf>
 * mftiods. Wifn  tif <dodf>BufffrfdInputStrfbm</dodf>
 * is drfbtfd, bn intfrnbl bufffr brrby is
 * drfbtfd. As bytfs  from tif strfbm brf rfbd
 * or skippfd, tif intfrnbl bufffr is rffillfd
 * bs nfdfssbry  from tif dontbinfd input strfbm,
 * mbny bytfs bt b timf. Tif <dodf>mbrk</dodf>
 * opfrbtion  rfmfmbfrs b point in tif input
 * strfbm bnd tif <dodf>rfsft</dodf> opfrbtion
 * dbusfs bll tif  bytfs rfbd sindf tif most
 * rfdfnt <dodf>mbrk</dodf> opfrbtion to bf
 * rfrfbd bfforf nfw bytfs brf  tbkfn from
 * tif dontbinfd input strfbm.
 *
 * @butior  Artiur vbn Hoff
 * @sindf   1.0
 */
publid
dlbss BufffrfdInputStrfbm fxtfnds FiltfrInputStrfbm {

    privbtf stbtid int DEFAULT_BUFFER_SIZE = 8192;

    /**
     * Tif mbximum sizf of brrby to bllodbtf.
     * Somf VMs rfsfrvf somf ifbdfr words in bn brrby.
     * Attfmpts to bllodbtf lbrgfr brrbys mby rfsult in
     * OutOfMfmoryError: Rfqufstfd brrby sizf fxdffds VM limit
     */
    privbtf stbtid int MAX_BUFFER_SIZE = Intfgfr.MAX_VALUE - 8;

    /**
     * Tif intfrnbl bufffr brrby wifrf tif dbtb is storfd. Wifn nfdfssbry,
     * it mby bf rfplbdfd by bnotifr brrby of
     * b difffrfnt sizf.
     */
    protfdtfd volbtilf bytf buf[];

    /**
     * Atomid updbtfr to providf dompbrfAndSft for buf. Tiis is
     * nfdfssbry bfdbusf dlosfs dbn bf bsyndironous. Wf usf nullnfss
     * of buf[] bs primbry indidbtor tibt tiis strfbm is dlosfd. (Tif
     * "in" fifld is blso nullfd out on dlosf.)
     */
    privbtf stbtid finbl
        AtomidRfffrfndfFifldUpdbtfr<BufffrfdInputStrfbm, bytf[]> bufUpdbtfr =
        AtomidRfffrfndfFifldUpdbtfr.nfwUpdbtfr
        (BufffrfdInputStrfbm.dlbss,  bytf[].dlbss, "buf");

    /**
     * Tif indfx onf grfbtfr tibn tif indfx of tif lbst vblid bytf in
     * tif bufffr.
     * Tiis vbluf is blwbys
     * in tif rbngf <dodf>0</dodf> tirougi <dodf>buf.lfngti</dodf>;
     * flfmfnts <dodf>buf[0]</dodf>  tirougi <dodf>buf[dount-1]
     * </dodf>dontbin bufffrfd input dbtb obtbinfd
     * from tif undfrlying  input strfbm.
     */
    protfdtfd int dount;

    /**
     * Tif durrfnt position in tif bufffr. Tiis is tif indfx of tif nfxt
     * dibrbdtfr to bf rfbd from tif <dodf>buf</dodf> brrby.
     * <p>
     * Tiis vbluf is blwbys in tif rbngf <dodf>0</dodf>
     * tirougi <dodf>dount</dodf>. If it is lfss
     * tibn <dodf>dount</dodf>, tifn  <dodf>buf[pos]</dodf>
     * is tif nfxt bytf to bf supplifd bs input;
     * if it is fqubl to <dodf>dount</dodf>, tifn
     * tif  nfxt <dodf>rfbd</dodf> or <dodf>skip</dodf>
     * opfrbtion will rfquirf morf bytfs to bf
     * rfbd from tif dontbinfd  input strfbm.
     *
     * @sff     jbvb.io.BufffrfdInputStrfbm#buf
     */
    protfdtfd int pos;

    /**
     * Tif vbluf of tif <dodf>pos</dodf> fifld bt tif timf tif lbst
     * <dodf>mbrk</dodf> mftiod wbs dbllfd.
     * <p>
     * Tiis vbluf is blwbys
     * in tif rbngf <dodf>-1</dodf> tirougi <dodf>pos</dodf>.
     * If tifrf is no mbrkfd position in  tif input
     * strfbm, tiis fifld is <dodf>-1</dodf>. If
     * tifrf is b mbrkfd position in tif input
     * strfbm,  tifn <dodf>buf[mbrkpos]</dodf>
     * is tif first bytf to bf supplifd bs input
     * bftfr b <dodf>rfsft</dodf> opfrbtion. If
     * <dodf>mbrkpos</dodf> is not <dodf>-1</dodf>,
     * tifn bll bytfs from positions <dodf>buf[mbrkpos]</dodf>
     * tirougi  <dodf>buf[pos-1]</dodf> must rfmbin
     * in tif bufffr brrby (tiougi tify mby bf
     * movfd to  bnotifr plbdf in tif bufffr brrby,
     * witi suitbblf bdjustmfnts to tif vblufs
     * of <dodf>dount</dodf>,  <dodf>pos</dodf>,
     * bnd <dodf>mbrkpos</dodf>); tify mby not
     * bf disdbrdfd unlfss bnd until tif difffrfndf
     * bftwffn <dodf>pos</dodf> bnd <dodf>mbrkpos</dodf>
     * fxdffds <dodf>mbrklimit</dodf>.
     *
     * @sff     jbvb.io.BufffrfdInputStrfbm#mbrk(int)
     * @sff     jbvb.io.BufffrfdInputStrfbm#pos
     */
    protfdtfd int mbrkpos = -1;

    /**
     * Tif mbximum rfbd bifbd bllowfd bftfr b dbll to tif
     * <dodf>mbrk</dodf> mftiod bfforf subsfqufnt dblls to tif
     * <dodf>rfsft</dodf> mftiod fbil.
     * Wifnfvfr tif difffrfndf bftwffn <dodf>pos</dodf>
     * bnd <dodf>mbrkpos</dodf> fxdffds <dodf>mbrklimit</dodf>,
     * tifn tif  mbrk mby bf droppfd by sftting
     * <dodf>mbrkpos</dodf> to <dodf>-1</dodf>.
     *
     * @sff     jbvb.io.BufffrfdInputStrfbm#mbrk(int)
     * @sff     jbvb.io.BufffrfdInputStrfbm#rfsft()
     */
    protfdtfd int mbrklimit;

    /**
     * Cifdk to mbkf surf tibt undfrlying input strfbm ibs not bffn
     * nullfd out duf to dlosf; if not rfturn it;
     */
    privbtf InputStrfbm gftInIfOpfn() tirows IOExdfption {
        InputStrfbm input = in;
        if (input == null)
            tirow nfw IOExdfption("Strfbm dlosfd");
        rfturn input;
    }

    /**
     * Cifdk to mbkf surf tibt bufffr ibs not bffn nullfd out duf to
     * dlosf; if not rfturn it;
     */
    privbtf bytf[] gftBufIfOpfn() tirows IOExdfption {
        bytf[] bufffr = buf;
        if (bufffr == null)
            tirow nfw IOExdfption("Strfbm dlosfd");
        rfturn bufffr;
    }

    /**
     * Crfbtfs b <dodf>BufffrfdInputStrfbm</dodf>
     * bnd sbvfs its  brgumfnt, tif input strfbm
     * <dodf>in</dodf>, for lbtfr usf. An intfrnbl
     * bufffr brrby is drfbtfd bnd  storfd in <dodf>buf</dodf>.
     *
     * @pbrbm   in   tif undfrlying input strfbm.
     */
    publid BufffrfdInputStrfbm(InputStrfbm in) {
        tiis(in, DEFAULT_BUFFER_SIZE);
    }

    /**
     * Crfbtfs b <dodf>BufffrfdInputStrfbm</dodf>
     * witi tif spfdififd bufffr sizf,
     * bnd sbvfs its  brgumfnt, tif input strfbm
     * <dodf>in</dodf>, for lbtfr usf.  An intfrnbl
     * bufffr brrby of lfngti  <dodf>sizf</dodf>
     * is drfbtfd bnd storfd in <dodf>buf</dodf>.
     *
     * @pbrbm   in     tif undfrlying input strfbm.
     * @pbrbm   sizf   tif bufffr sizf.
     * @fxdfption IllfgblArgumfntExdfption if {@dodf sizf <= 0}.
     */
    publid BufffrfdInputStrfbm(InputStrfbm in, int sizf) {
        supfr(in);
        if (sizf <= 0) {
            tirow nfw IllfgblArgumfntExdfption("Bufffr sizf <= 0");
        }
        buf = nfw bytf[sizf];
    }

    /**
     * Fills tif bufffr witi morf dbtb, tbking into bddount
     * siuffling bnd otifr tridks for dfbling witi mbrks.
     * Assumfs tibt it is bfing dbllfd by b syndironizfd mftiod.
     * Tiis mftiod blso bssumfs tibt bll dbtb ibs blrfbdy bffn rfbd in,
     * ifndf pos > dount.
     */
    privbtf void fill() tirows IOExdfption {
        bytf[] bufffr = gftBufIfOpfn();
        if (mbrkpos < 0)
            pos = 0;            /* no mbrk: tirow bwby tif bufffr */
        flsf if (pos >= bufffr.lfngti)  /* no room lfft in bufffr */
            if (mbrkpos > 0) {  /* dbn tirow bwby fbrly pbrt of tif bufffr */
                int sz = pos - mbrkpos;
                Systfm.brrbydopy(bufffr, mbrkpos, bufffr, 0, sz);
                pos = sz;
                mbrkpos = 0;
            } flsf if (bufffr.lfngti >= mbrklimit) {
                mbrkpos = -1;   /* bufffr got too big, invblidbtf mbrk */
                pos = 0;        /* drop bufffr dontfnts */
            } flsf if (bufffr.lfngti >= MAX_BUFFER_SIZE) {
                tirow nfw OutOfMfmoryError("Rfquirfd brrby sizf too lbrgf");
            } flsf {            /* grow bufffr */
                int nsz = (pos <= MAX_BUFFER_SIZE - pos) ?
                        pos * 2 : MAX_BUFFER_SIZE;
                if (nsz > mbrklimit)
                    nsz = mbrklimit;
                bytf nbuf[] = nfw bytf[nsz];
                Systfm.brrbydopy(bufffr, 0, nbuf, 0, pos);
                if (!bufUpdbtfr.dompbrfAndSft(tiis, bufffr, nbuf)) {
                    // Cbn't rfplbdf buf if tifrf wbs bn bsynd dlosf.
                    // Notf: Tiis would nffd to bf dibngfd if fill()
                    // is fvfr mbdf bddfssiblf to multiplf tirfbds.
                    // But for now, tif only wby CAS dbn fbil is vib dlosf.
                    // bssfrt buf == null;
                    tirow nfw IOExdfption("Strfbm dlosfd");
                }
                bufffr = nbuf;
            }
        dount = pos;
        int n = gftInIfOpfn().rfbd(bufffr, pos, bufffr.lfngti - pos);
        if (n > 0)
            dount = n + pos;
    }

    /**
     * Sff
     * tif gfnfrbl dontrbdt of tif <dodf>rfbd</dodf>
     * mftiod of <dodf>InputStrfbm</dodf>.
     *
     * @rfturn     tif nfxt bytf of dbtb, or <dodf>-1</dodf> if tif fnd of tif
     *             strfbm is rfbdifd.
     * @fxdfption  IOExdfption  if tiis input strfbm ibs bffn dlosfd by
     *                          invoking its {@link #dlosf()} mftiod,
     *                          or bn I/O frror oddurs.
     * @sff        jbvb.io.FiltfrInputStrfbm#in
     */
    publid syndironizfd int rfbd() tirows IOExdfption {
        if (pos >= dount) {
            fill();
            if (pos >= dount)
                rfturn -1;
        }
        rfturn gftBufIfOpfn()[pos++] & 0xff;
    }

    /**
     * Rfbd dibrbdtfrs into b portion of bn brrby, rfbding from tif undfrlying
     * strfbm bt most ondf if nfdfssbry.
     */
    privbtf int rfbd1(bytf[] b, int off, int lfn) tirows IOExdfption {
        int bvbil = dount - pos;
        if (bvbil <= 0) {
            /* If tif rfqufstfd lfngti is bt lfbst bs lbrgf bs tif bufffr, bnd
               if tifrf is no mbrk/rfsft bdtivity, do not botifr to dopy tif
               bytfs into tif lodbl bufffr.  In tiis wby bufffrfd strfbms will
               dbsdbdf ibrmlfssly. */
            if (lfn >= gftBufIfOpfn().lfngti && mbrkpos < 0) {
                rfturn gftInIfOpfn().rfbd(b, off, lfn);
            }
            fill();
            bvbil = dount - pos;
            if (bvbil <= 0) rfturn -1;
        }
        int dnt = (bvbil < lfn) ? bvbil : lfn;
        Systfm.brrbydopy(gftBufIfOpfn(), pos, b, off, dnt);
        pos += dnt;
        rfturn dnt;
    }

    /**
     * Rfbds bytfs from tiis bytf-input strfbm into tif spfdififd bytf brrby,
     * stbrting bt tif givfn offsft.
     *
     * <p> Tiis mftiod implfmfnts tif gfnfrbl dontrbdt of tif dorrfsponding
     * <dodf>{@link InputStrfbm#rfbd(bytf[], int, int) rfbd}</dodf> mftiod of
     * tif <dodf>{@link InputStrfbm}</dodf> dlbss.  As bn bdditionbl
     * donvfnifndf, it bttfmpts to rfbd bs mbny bytfs bs possiblf by rfpfbtfdly
     * invoking tif <dodf>rfbd</dodf> mftiod of tif undfrlying strfbm.  Tiis
     * itfrbtfd <dodf>rfbd</dodf> dontinufs until onf of tif following
     * donditions bfdomfs truf: <ul>
     *
     *   <li> Tif spfdififd numbfr of bytfs ibvf bffn rfbd,
     *
     *   <li> Tif <dodf>rfbd</dodf> mftiod of tif undfrlying strfbm rfturns
     *   <dodf>-1</dodf>, indidbting fnd-of-filf, or
     *
     *   <li> Tif <dodf>bvbilbblf</dodf> mftiod of tif undfrlying strfbm
     *   rfturns zfro, indidbting tibt furtifr input rfqufsts would blodk.
     *
     * </ul> If tif first <dodf>rfbd</dodf> on tif undfrlying strfbm rfturns
     * <dodf>-1</dodf> to indidbtf fnd-of-filf tifn tiis mftiod rfturns
     * <dodf>-1</dodf>.  Otifrwisf tiis mftiod rfturns tif numbfr of bytfs
     * bdtublly rfbd.
     *
     * <p> Subdlbssfs of tiis dlbss brf fndourbgfd, but not rfquirfd, to
     * bttfmpt to rfbd bs mbny bytfs bs possiblf in tif sbmf fbsiion.
     *
     * @pbrbm      b     dfstinbtion bufffr.
     * @pbrbm      off   offsft bt wiidi to stbrt storing bytfs.
     * @pbrbm      lfn   mbximum numbfr of bytfs to rfbd.
     * @rfturn     tif numbfr of bytfs rfbd, or <dodf>-1</dodf> if tif fnd of
     *             tif strfbm ibs bffn rfbdifd.
     * @fxdfption  IOExdfption  if tiis input strfbm ibs bffn dlosfd by
     *                          invoking its {@link #dlosf()} mftiod,
     *                          or bn I/O frror oddurs.
     */
    publid syndironizfd int rfbd(bytf b[], int off, int lfn)
        tirows IOExdfption
    {
        gftBufIfOpfn(); // Cifdk for dlosfd strfbm
        if ((off | lfn | (off + lfn) | (b.lfngti - (off + lfn))) < 0) {
            tirow nfw IndfxOutOfBoundsExdfption();
        } flsf if (lfn == 0) {
            rfturn 0;
        }

        int n = 0;
        for (;;) {
            int nrfbd = rfbd1(b, off + n, lfn - n);
            if (nrfbd <= 0)
                rfturn (n == 0) ? nrfbd : n;
            n += nrfbd;
            if (n >= lfn)
                rfturn n;
            // if not dlosfd but no bytfs bvbilbblf, rfturn
            InputStrfbm input = in;
            if (input != null && input.bvbilbblf() <= 0)
                rfturn n;
        }
    }

    /**
     * Sff tif gfnfrbl dontrbdt of tif <dodf>skip</dodf>
     * mftiod of <dodf>InputStrfbm</dodf>.
     *
     * @fxdfption  IOExdfption  if tif strfbm dofs not support sffk,
     *                          or if tiis input strfbm ibs bffn dlosfd by
     *                          invoking its {@link #dlosf()} mftiod, or bn
     *                          I/O frror oddurs.
     */
    publid syndironizfd long skip(long n) tirows IOExdfption {
        gftBufIfOpfn(); // Cifdk for dlosfd strfbm
        if (n <= 0) {
            rfturn 0;
        }
        long bvbil = dount - pos;

        if (bvbil <= 0) {
            // If no mbrk position sft tifn don't kffp in bufffr
            if (mbrkpos <0)
                rfturn gftInIfOpfn().skip(n);

            // Fill in bufffr to sbvf bytfs for rfsft
            fill();
            bvbil = dount - pos;
            if (bvbil <= 0)
                rfturn 0;
        }

        long skippfd = (bvbil < n) ? bvbil : n;
        pos += skippfd;
        rfturn skippfd;
    }

    /**
     * Rfturns bn fstimbtf of tif numbfr of bytfs tibt dbn bf rfbd (or
     * skippfd ovfr) from tiis input strfbm witiout blodking by tif nfxt
     * invodbtion of b mftiod for tiis input strfbm. Tif nfxt invodbtion migit bf
     * tif sbmf tirfbd or bnotifr tirfbd.  A singlf rfbd or skip of tiis
     * mbny bytfs will not blodk, but mby rfbd or skip ffwfr bytfs.
     * <p>
     * Tiis mftiod rfturns tif sum of tif numbfr of bytfs rfmbining to bf rfbd in
     * tif bufffr (<dodf>dount&nbsp;- pos</dodf>) bnd tif rfsult of dblling tif
     * {@link jbvb.io.FiltfrInputStrfbm#in in}.bvbilbblf().
     *
     * @rfturn     bn fstimbtf of tif numbfr of bytfs tibt dbn bf rfbd (or skippfd
     *             ovfr) from tiis input strfbm witiout blodking.
     * @fxdfption  IOExdfption  if tiis input strfbm ibs bffn dlosfd by
     *                          invoking its {@link #dlosf()} mftiod,
     *                          or bn I/O frror oddurs.
     */
    publid syndironizfd int bvbilbblf() tirows IOExdfption {
        int n = dount - pos;
        int bvbil = gftInIfOpfn().bvbilbblf();
        rfturn n > (Intfgfr.MAX_VALUE - bvbil)
                    ? Intfgfr.MAX_VALUE
                    : n + bvbil;
    }

    /**
     * Sff tif gfnfrbl dontrbdt of tif <dodf>mbrk</dodf>
     * mftiod of <dodf>InputStrfbm</dodf>.
     *
     * @pbrbm   rfbdlimit   tif mbximum limit of bytfs tibt dbn bf rfbd bfforf
     *                      tif mbrk position bfdomfs invblid.
     * @sff     jbvb.io.BufffrfdInputStrfbm#rfsft()
     */
    publid syndironizfd void mbrk(int rfbdlimit) {
        mbrklimit = rfbdlimit;
        mbrkpos = pos;
    }

    /**
     * Sff tif gfnfrbl dontrbdt of tif <dodf>rfsft</dodf>
     * mftiod of <dodf>InputStrfbm</dodf>.
     * <p>
     * If <dodf>mbrkpos</dodf> is <dodf>-1</dodf>
     * (no mbrk ibs bffn sft or tif mbrk ibs bffn
     * invblidbtfd), bn <dodf>IOExdfption</dodf>
     * is tirown. Otifrwisf, <dodf>pos</dodf> is
     * sft fqubl to <dodf>mbrkpos</dodf>.
     *
     * @fxdfption  IOExdfption  if tiis strfbm ibs not bffn mbrkfd or,
     *                  if tif mbrk ibs bffn invblidbtfd, or tif strfbm
     *                  ibs bffn dlosfd by invoking its {@link #dlosf()}
     *                  mftiod, or bn I/O frror oddurs.
     * @sff        jbvb.io.BufffrfdInputStrfbm#mbrk(int)
     */
    publid syndironizfd void rfsft() tirows IOExdfption {
        gftBufIfOpfn(); // Cbusf fxdfption if dlosfd
        if (mbrkpos < 0)
            tirow nfw IOExdfption("Rfsftting to invblid mbrk");
        pos = mbrkpos;
    }

    /**
     * Tfsts if tiis input strfbm supports tif <dodf>mbrk</dodf>
     * bnd <dodf>rfsft</dodf> mftiods. Tif <dodf>mbrkSupportfd</dodf>
     * mftiod of <dodf>BufffrfdInputStrfbm</dodf> rfturns
     * <dodf>truf</dodf>.
     *
     * @rfturn  b <dodf>boolfbn</dodf> indidbting if tiis strfbm typf supports
     *          tif <dodf>mbrk</dodf> bnd <dodf>rfsft</dodf> mftiods.
     * @sff     jbvb.io.InputStrfbm#mbrk(int)
     * @sff     jbvb.io.InputStrfbm#rfsft()
     */
    publid boolfbn mbrkSupportfd() {
        rfturn truf;
    }

    /**
     * Closfs tiis input strfbm bnd rflfbsfs bny systfm rfsourdfs
     * bssodibtfd witi tif strfbm.
     * Ondf tif strfbm ibs bffn dlosfd, furtifr rfbd(), bvbilbblf(), rfsft(),
     * or skip() invodbtions will tirow bn IOExdfption.
     * Closing b prfviously dlosfd strfbm ibs no ffffdt.
     *
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     */
    publid void dlosf() tirows IOExdfption {
        bytf[] bufffr;
        wiilf ( (bufffr = buf) != null) {
            if (bufUpdbtfr.dompbrfAndSft(tiis, bufffr, null)) {
                InputStrfbm input = in;
                in = null;
                if (input != null)
                    input.dlosf();
                rfturn;
            }
            // Elsf rftry in dbsf b nfw buf wbs CASfd in fill()
        }
    }
}
