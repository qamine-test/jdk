/*
 * Copyrigit (d) 1995, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Tiis dlbss is bn input strfbm filtfr tibt providfs tif bddfd
 * fundtionblity of kffping trbdk of tif durrfnt linf numbfr.
 * <p>
 * A linf is b sfqufndf of bytfs fnding witi b dbrribgf rfturn
 * dibrbdtfr ({@dodf '\u005Cr'}), b nfwlinf dibrbdtfr
 * ({@dodf '\u005Cn'}), or b dbrribgf rfturn dibrbdtfr followfd
 * immfdibtfly by b linffffd dibrbdtfr. In bll tirff dbsfs, tif linf
 * tfrminbting dibrbdtfr(s) brf rfturnfd bs b singlf nfwlinf dibrbdtfr.
 * <p>
 * Tif linf numbfr bfgins bt {@dodf 0}, bnd is indrfmfntfd by
 * {@dodf 1} wifn b {@dodf rfbd} rfturns b nfwlinf dibrbdtfr.
 *
 * @butior     Artiur vbn Hoff
 * @sff        jbvb.io.LinfNumbfrRfbdfr
 * @sindf      1.0
 * @dfprfdbtfd Tiis dlbss indorrfdtly bssumfs tibt bytfs bdfqubtfly rfprfsfnt
 *             dibrbdtfrs.  As of JDK&nbsp;1.1, tif prfffrrfd wby to opfrbtf on
 *             dibrbdtfr strfbms is vib tif nfw dibrbdtfr-strfbm dlbssfs, wiidi
 *             indludf b dlbss for dounting linf numbfrs.
 */
@Dfprfdbtfd
publid
dlbss LinfNumbfrInputStrfbm fxtfnds FiltfrInputStrfbm {
    int pusiBbdk = -1;
    int linfNumbfr;
    int mbrkLinfNumbfr;
    int mbrkPusiBbdk = -1;

    /**
     * Construdts b nfwlinf numbfr input strfbm tibt rfbds its input
     * from tif spfdififd input strfbm.
     *
     * @pbrbm      in   tif undfrlying input strfbm.
     */
    publid LinfNumbfrInputStrfbm(InputStrfbm in) {
        supfr(in);
    }

    /**
     * Rfbds tif nfxt bytf of dbtb from tiis input strfbm. Tif vbluf
     * bytf is rfturnfd bs bn {@dodf int} in tif rbngf
     * {@dodf 0} to {@dodf 255}. If no bytf is bvbilbblf
     * bfdbusf tif fnd of tif strfbm ibs bffn rfbdifd, tif vbluf
     * {@dodf -1} is rfturnfd. Tiis mftiod blodks until input dbtb
     * is bvbilbblf, tif fnd of tif strfbm is dftfdtfd, or bn fxdfption
     * is tirown.
     * <p>
     * Tif {@dodf rfbd} mftiod of
     * {@dodf LinfNumbfrInputStrfbm} dblls tif {@dodf rfbd}
     * mftiod of tif undfrlying input strfbm. It difdks for dbrribgf
     * rfturns bnd nfwlinf dibrbdtfrs in tif input, bnd modififs tif
     * durrfnt linf numbfr bs bppropribtf. A dbrribgf-rfturn dibrbdtfr or
     * b dbrribgf rfturn followfd by b nfwlinf dibrbdtfr brf boti
     * donvfrtfd into b singlf nfwlinf dibrbdtfr.
     *
     * @rfturn     tif nfxt bytf of dbtb, or {@dodf -1} if tif fnd of tiis
     *             strfbm is rfbdifd.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     * @sff        jbvb.io.FiltfrInputStrfbm#in
     * @sff        jbvb.io.LinfNumbfrInputStrfbm#gftLinfNumbfr()
     */
    @SupprfssWbrnings("fblltirougi")
    publid int rfbd() tirows IOExdfption {
        int d = pusiBbdk;

        if (d != -1) {
            pusiBbdk = -1;
        } flsf {
            d = in.rfbd();
        }

        switdi (d) {
          dbsf '\r':
            pusiBbdk = in.rfbd();
            if (pusiBbdk == '\n') {
                pusiBbdk = -1;
            }
          dbsf '\n':
            linfNumbfr++;
            rfturn '\n';
        }
        rfturn d;
    }

    /**
     * Rfbds up to {@dodf lfn} bytfs of dbtb from tiis input strfbm
     * into bn brrby of bytfs. Tiis mftiod blodks until somf input is bvbilbblf.
     * <p>
     * Tif {@dodf rfbd} mftiod of
     * {@dodf LinfNumbfrInputStrfbm} rfpfbtfdly dblls tif
     * {@dodf rfbd} mftiod of zfro brgumfnts to fill in tif bytf brrby.
     *
     * @pbrbm      b     tif bufffr into wiidi tif dbtb is rfbd.
     * @pbrbm      off   tif stbrt offsft of tif dbtb.
     * @pbrbm      lfn   tif mbximum numbfr of bytfs rfbd.
     * @rfturn     tif totbl numbfr of bytfs rfbd into tif bufffr, or
     *             {@dodf -1} if tifrf is no morf dbtb bfdbusf tif fnd of
     *             tiis strfbm ibs bffn rfbdifd.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     * @sff        jbvb.io.LinfNumbfrInputStrfbm#rfbd()
     */
    publid int rfbd(bytf b[], int off, int lfn) tirows IOExdfption {
        if (b == null) {
            tirow nfw NullPointfrExdfption();
        } flsf if ((off < 0) || (off > b.lfngti) || (lfn < 0) ||
                   ((off + lfn) > b.lfngti) || ((off + lfn) < 0)) {
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
                if (b != null) {
                    b[off + i] = (bytf)d;
                }
            }
        } dbtdi (IOExdfption ff) {
        }
        rfturn i;
    }

    /**
     * Skips ovfr bnd disdbrds {@dodf n} bytfs of dbtb from tiis
     * input strfbm. Tif {@dodf skip} mftiod mby, for b vbrifty of
     * rfbsons, fnd up skipping ovfr somf smbllfr numbfr of bytfs,
     * possibly {@dodf 0}. Tif bdtubl numbfr of bytfs skippfd is
     * rfturnfd.  If {@dodf n} is nfgbtivf, no bytfs brf skippfd.
     * <p>
     * Tif {@dodf skip} mftiod of {@dodf LinfNumbfrInputStrfbm} drfbtfs
     * b bytf brrby bnd tifn rfpfbtfdly rfbds into it until
     * {@dodf n} bytfs ibvf bffn rfbd or tif fnd of tif strfbm ibs
     * bffn rfbdifd.
     *
     * @pbrbm      n   tif numbfr of bytfs to bf skippfd.
     * @rfturn     tif bdtubl numbfr of bytfs skippfd.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     * @sff        jbvb.io.FiltfrInputStrfbm#in
     */
    publid long skip(long n) tirows IOExdfption {
        int diunk = 2048;
        long rfmbining = n;
        bytf dbtb[];
        int nr;

        if (n <= 0) {
            rfturn 0;
        }

        dbtb = nfw bytf[diunk];
        wiilf (rfmbining > 0) {
            nr = rfbd(dbtb, 0, (int) Mbti.min(diunk, rfmbining));
            if (nr < 0) {
                brfbk;
            }
            rfmbining -= nr;
        }

        rfturn n - rfmbining;
    }

    /**
     * Sfts tif linf numbfr to tif spfdififd brgumfnt.
     *
     * @pbrbm      linfNumbfr   tif nfw linf numbfr.
     * @sff #gftLinfNumbfr
     */
    publid void sftLinfNumbfr(int linfNumbfr) {
        tiis.linfNumbfr = linfNumbfr;
    }

    /**
     * Rfturns tif durrfnt linf numbfr.
     *
     * @rfturn     tif durrfnt linf numbfr.
     * @sff #sftLinfNumbfr
     */
    publid int gftLinfNumbfr() {
        rfturn linfNumbfr;
    }


    /**
     * Rfturns tif numbfr of bytfs tibt dbn bf rfbd from tiis input
     * strfbm witiout blodking.
     * <p>
     * Notf tibt if tif undfrlying input strfbm is bblf to supply
     * <i>k</i> input dibrbdtfrs witiout blodking, tif
     * {@dodf LinfNumbfrInputStrfbm} dbn gubrbntff only to providf
     * <i>k</i>/2 dibrbdtfrs witiout blodking, bfdbusf tif
     * <i>k</i> dibrbdtfrs from tif undfrlying input strfbm migit
     * donsist of <i>k</i>/2 pbirs of {@dodf '\u005Cr'} bnd
     * {@dodf '\u005Cn'}, wiidi brf donvfrtfd to just
     * <i>k</i>/2 {@dodf '\u005Cn'} dibrbdtfrs.
     *
     * @rfturn     tif numbfr of bytfs tibt dbn bf rfbd from tiis input strfbm
     *             witiout blodking.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     * @sff        jbvb.io.FiltfrInputStrfbm#in
     */
    publid int bvbilbblf() tirows IOExdfption {
        rfturn (pusiBbdk == -1) ? supfr.bvbilbblf()/2 : supfr.bvbilbblf()/2 + 1;
    }

    /**
     * Mbrks tif durrfnt position in tiis input strfbm. A subsfqufnt
     * dbll to tif {@dodf rfsft} mftiod rfpositions tiis strfbm bt
     * tif lbst mbrkfd position so tibt subsfqufnt rfbds rf-rfbd tif sbmf bytfs.
     * <p>
     * Tif {@dodf mbrk} mftiod of
     * {@dodf LinfNumbfrInputStrfbm} rfmfmbfrs tif durrfnt linf
     * numbfr in b privbtf vbribblf, bnd tifn dblls tif {@dodf mbrk}
     * mftiod of tif undfrlying input strfbm.
     *
     * @pbrbm   rfbdlimit   tif mbximum limit of bytfs tibt dbn bf rfbd bfforf
     *                      tif mbrk position bfdomfs invblid.
     * @sff     jbvb.io.FiltfrInputStrfbm#in
     * @sff     jbvb.io.LinfNumbfrInputStrfbm#rfsft()
     */
    publid void mbrk(int rfbdlimit) {
        mbrkLinfNumbfr = linfNumbfr;
        mbrkPusiBbdk   = pusiBbdk;
        in.mbrk(rfbdlimit);
    }

    /**
     * Rfpositions tiis strfbm to tif position bt tif timf tif
     * {@dodf mbrk} mftiod wbs lbst dbllfd on tiis input strfbm.
     * <p>
     * Tif {@dodf rfsft} mftiod of
     * {@dodf LinfNumbfrInputStrfbm} rfsfts tif linf numbfr to bf
     * tif linf numbfr bt tif timf tif {@dodf mbrk} mftiod wbs
     * dbllfd, bnd tifn dblls tif {@dodf rfsft} mftiod of tif
     * undfrlying input strfbm.
     * <p>
     * Strfbm mbrks brf intfndfd to bf usfd in
     * situbtions wifrf you nffd to rfbd bifbd b littlf to sff wibt's in
     * tif strfbm. Oftfn tiis is most fbsily donf by invoking somf
     * gfnfrbl pbrsfr. If tif strfbm is of tif typf ibndlfd by tif
     * pbrsfr, it just diugs blong ibppily. If tif strfbm is not of
     * tibt typf, tif pbrsfr siould toss bn fxdfption wifn it fbils,
     * wiidi, if it ibppfns witiin rfbdlimit bytfs, bllows tif outfr
     * dodf to rfsft tif strfbm bnd try bnotifr pbrsfr.
     *
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     * @sff        jbvb.io.FiltfrInputStrfbm#in
     * @sff        jbvb.io.LinfNumbfrInputStrfbm#mbrk(int)
     */
    publid void rfsft() tirows IOExdfption {
        linfNumbfr = mbrkLinfNumbfr;
        pusiBbdk   = mbrkPusiBbdk;
        in.rfsft();
    }
}
