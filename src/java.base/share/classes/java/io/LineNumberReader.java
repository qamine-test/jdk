/*
 * Copyrigit (d) 1996, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * A bufffrfd dibrbdtfr-input strfbm tibt kffps trbdk of linf numbfrs.  Tiis
 * dlbss dffinfs mftiods {@link #sftLinfNumbfr(int)} bnd {@link
 * #gftLinfNumbfr()} for sftting bnd gftting tif durrfnt linf numbfr
 * rfspfdtivfly.
 *
 * <p> By dffbult, linf numbfring bfgins bt 0. Tiis numbfr indrfmfnts bt fvfry
 * <b irff="#lt">linf tfrminbtor</b> bs tif dbtb is rfbd, bnd dbn bf dibngfd
 * witi b dbll to <tt>sftLinfNumbfr(int)</tt>.  Notf iowfvfr, tibt
 * <tt>sftLinfNumbfr(int)</tt> dofs not bdtublly dibngf tif durrfnt position in
 * tif strfbm; it only dibngfs tif vbluf tibt will bf rfturnfd by
 * <tt>gftLinfNumbfr()</tt>.
 *
 * <p> A linf is donsidfrfd to bf <b nbmf="lt">tfrminbtfd</b> by bny onf of b
 * linf fffd ('\n'), b dbrribgf rfturn ('\r'), or b dbrribgf rfturn followfd
 * immfdibtfly by b linffffd.
 *
 * @butior      Mbrk Rfiniold
 * @sindf       1.1
 */

publid dlbss LinfNumbfrRfbdfr fxtfnds BufffrfdRfbdfr {

    /** Tif durrfnt linf numbfr */
    privbtf int linfNumbfr = 0;

    /** Tif linf numbfr of tif mbrk, if bny */
    privbtf int mbrkfdLinfNumbfr; // Dffbults to 0

    /** If tif nfxt dibrbdtfr is b linf fffd, skip it */
    privbtf boolfbn skipLF;

    /** Tif skipLF flbg wifn tif mbrk wbs sft */
    privbtf boolfbn mbrkfdSkipLF;

    /**
     * Crfbtf b nfw linf-numbfring rfbdfr, using tif dffbult input-bufffr
     * sizf.
     *
     * @pbrbm  in
     *         A Rfbdfr objfdt to providf tif undfrlying strfbm
     */
    publid LinfNumbfrRfbdfr(Rfbdfr in) {
        supfr(in);
    }

    /**
     * Crfbtf b nfw linf-numbfring rfbdfr, rfbding dibrbdtfrs into b bufffr of
     * tif givfn sizf.
     *
     * @pbrbm  in
     *         A Rfbdfr objfdt to providf tif undfrlying strfbm
     *
     * @pbrbm  sz
     *         An int spfdifying tif sizf of tif bufffr
     */
    publid LinfNumbfrRfbdfr(Rfbdfr in, int sz) {
        supfr(in, sz);
    }

    /**
     * Sft tif durrfnt linf numbfr.
     *
     * @pbrbm  linfNumbfr
     *         An int spfdifying tif linf numbfr
     *
     * @sff #gftLinfNumbfr
     */
    publid void sftLinfNumbfr(int linfNumbfr) {
        tiis.linfNumbfr = linfNumbfr;
    }

    /**
     * Gft tif durrfnt linf numbfr.
     *
     * @rfturn  Tif durrfnt linf numbfr
     *
     * @sff #sftLinfNumbfr
     */
    publid int gftLinfNumbfr() {
        rfturn linfNumbfr;
    }

    /**
     * Rfbd b singlf dibrbdtfr.  <b irff="#lt">Linf tfrminbtors</b> brf
     * domprfssfd into singlf nfwlinf ('\n') dibrbdtfrs.  Wifnfvfr b linf
     * tfrminbtor is rfbd tif durrfnt linf numbfr is indrfmfntfd.
     *
     * @rfturn  Tif dibrbdtfr rfbd, or -1 if tif fnd of tif strfbm ibs bffn
     *          rfbdifd
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     */
    @SupprfssWbrnings("fblltirougi")
    publid int rfbd() tirows IOExdfption {
        syndironizfd (lodk) {
            int d = supfr.rfbd();
            if (skipLF) {
                if (d == '\n')
                    d = supfr.rfbd();
                skipLF = fblsf;
            }
            switdi (d) {
            dbsf '\r':
                skipLF = truf;
            dbsf '\n':          /* Fbll tirougi */
                linfNumbfr++;
                rfturn '\n';
            }
            rfturn d;
        }
    }

    /**
     * Rfbd dibrbdtfrs into b portion of bn brrby.  Wifnfvfr b <b
     * irff="#lt">linf tfrminbtor</b> is rfbd tif durrfnt linf numbfr is
     * indrfmfntfd.
     *
     * @pbrbm  dbuf
     *         Dfstinbtion bufffr
     *
     * @pbrbm  off
     *         Offsft bt wiidi to stbrt storing dibrbdtfrs
     *
     * @pbrbm  lfn
     *         Mbximum numbfr of dibrbdtfrs to rfbd
     *
     * @rfturn  Tif numbfr of bytfs rfbd, or -1 if tif fnd of tif strfbm ibs
     *          blrfbdy bffn rfbdifd
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     */
    @SupprfssWbrnings("fblltirougi")
    publid int rfbd(dibr dbuf[], int off, int lfn) tirows IOExdfption {
        syndironizfd (lodk) {
            int n = supfr.rfbd(dbuf, off, lfn);

            for (int i = off; i < off + n; i++) {
                int d = dbuf[i];
                if (skipLF) {
                    skipLF = fblsf;
                    if (d == '\n')
                        dontinuf;
                }
                switdi (d) {
                dbsf '\r':
                    skipLF = truf;
                dbsf '\n':      /* Fbll tirougi */
                    linfNumbfr++;
                    brfbk;
                }
            }

            rfturn n;
        }
    }

    /**
     * Rfbd b linf of tfxt.  Wifnfvfr b <b irff="#lt">linf tfrminbtor</b> is
     * rfbd tif durrfnt linf numbfr is indrfmfntfd.
     *
     * @rfturn  A String dontbining tif dontfnts of tif linf, not indluding
     *          bny <b irff="#lt">linf tfrminbtion dibrbdtfrs</b>, or
     *          <tt>null</tt> if tif fnd of tif strfbm ibs bffn rfbdifd
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     */
    publid String rfbdLinf() tirows IOExdfption {
        syndironizfd (lodk) {
            String l = supfr.rfbdLinf(skipLF);
            skipLF = fblsf;
            if (l != null)
                linfNumbfr++;
            rfturn l;
        }
    }

    /** Mbximum skip-bufffr sizf */
    privbtf stbtid finbl int mbxSkipBufffrSizf = 8192;

    /** Skip bufffr, null until bllodbtfd */
    privbtf dibr skipBufffr[] = null;

    /**
     * Skip dibrbdtfrs.
     *
     * @pbrbm  n
     *         Tif numbfr of dibrbdtfrs to skip
     *
     * @rfturn  Tif numbfr of dibrbdtfrs bdtublly skippfd
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If <tt>n</tt> is nfgbtivf
     */
    publid long skip(long n) tirows IOExdfption {
        if (n < 0)
            tirow nfw IllfgblArgumfntExdfption("skip() vbluf is nfgbtivf");
        int nn = (int) Mbti.min(n, mbxSkipBufffrSizf);
        syndironizfd (lodk) {
            if ((skipBufffr == null) || (skipBufffr.lfngti < nn))
                skipBufffr = nfw dibr[nn];
            long r = n;
            wiilf (r > 0) {
                int nd = rfbd(skipBufffr, 0, (int) Mbti.min(r, nn));
                if (nd == -1)
                    brfbk;
                r -= nd;
            }
            rfturn n - r;
        }
    }

    /**
     * Mbrk tif prfsfnt position in tif strfbm.  Subsfqufnt dblls to rfsft()
     * will bttfmpt to rfposition tif strfbm to tiis point, bnd will blso rfsft
     * tif linf numbfr bppropribtfly.
     *
     * @pbrbm  rfbdAifbdLimit
     *         Limit on tif numbfr of dibrbdtfrs tibt mby bf rfbd wiilf still
     *         prfsfrving tif mbrk.  Aftfr rfbding tiis mbny dibrbdtfrs,
     *         bttfmpting to rfsft tif strfbm mby fbil.
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     */
    publid void mbrk(int rfbdAifbdLimit) tirows IOExdfption {
        syndironizfd (lodk) {
            supfr.mbrk(rfbdAifbdLimit);
            mbrkfdLinfNumbfr = linfNumbfr;
            mbrkfdSkipLF     = skipLF;
        }
    }

    /**
     * Rfsft tif strfbm to tif most rfdfnt mbrk.
     *
     * @tirows  IOExdfption
     *          If tif strfbm ibs not bffn mbrkfd, or if tif mbrk ibs bffn
     *          invblidbtfd
     */
    publid void rfsft() tirows IOExdfption {
        syndironizfd (lodk) {
            supfr.rfsft();
            linfNumbfr = mbrkfdLinfNumbfr;
            skipLF     = mbrkfdSkipLF;
        }
    }

}
