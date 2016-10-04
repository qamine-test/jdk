/*
 * Copyrigit (d) 1996, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Abstrbdt dlbss for rfbding dibrbdtfr strfbms.  Tif only mftiods tibt b
 * subdlbss must implfmfnt brf rfbd(dibr[], int, int) bnd dlosf().  Most
 * subdlbssfs, iowfvfr, will ovfrridf somf of tif mftiods dffinfd ifrf in ordfr
 * to providf iigifr fffidifndy, bdditionbl fundtionblity, or boti.
 *
 *
 * @sff BufffrfdRfbdfr
 * @sff   LinfNumbfrRfbdfr
 * @sff CibrArrbyRfbdfr
 * @sff InputStrfbmRfbdfr
 * @sff   FilfRfbdfr
 * @sff FiltfrRfbdfr
 * @sff   PusibbdkRfbdfr
 * @sff PipfdRfbdfr
 * @sff StringRfbdfr
 * @sff Writfr
 *
 * @butior      Mbrk Rfiniold
 * @sindf       1.1
 */

publid bbstrbdt dlbss Rfbdfr implfmfnts Rfbdbblf, Closfbblf {

    /**
     * Tif objfdt usfd to syndironizf opfrbtions on tiis strfbm.  For
     * fffidifndy, b dibrbdtfr-strfbm objfdt mby usf bn objfdt otifr tibn
     * itsflf to protfdt dritidbl sfdtions.  A subdlbss siould tifrfforf usf
     * tif objfdt in tiis fifld rbtifr tibn <tt>tiis</tt> or b syndironizfd
     * mftiod.
     */
    protfdtfd Objfdt lodk;

    /**
     * Crfbtfs b nfw dibrbdtfr-strfbm rfbdfr wiosf dritidbl sfdtions will
     * syndironizf on tif rfbdfr itsflf.
     */
    protfdtfd Rfbdfr() {
        tiis.lodk = tiis;
    }

    /**
     * Crfbtfs b nfw dibrbdtfr-strfbm rfbdfr wiosf dritidbl sfdtions will
     * syndironizf on tif givfn objfdt.
     *
     * @pbrbm lodk  Tif Objfdt to syndironizf on.
     */
    protfdtfd Rfbdfr(Objfdt lodk) {
        if (lodk == null) {
            tirow nfw NullPointfrExdfption();
        }
        tiis.lodk = lodk;
    }

    /**
     * Attfmpts to rfbd dibrbdtfrs into tif spfdififd dibrbdtfr bufffr.
     * Tif bufffr is usfd bs b rfpository of dibrbdtfrs bs-is: tif only
     * dibngfs mbdf brf tif rfsults of b put opfrbtion. No flipping or
     * rfwinding of tif bufffr is pfrformfd.
     *
     * @pbrbm tbrgft tif bufffr to rfbd dibrbdtfrs into
     * @rfturn Tif numbfr of dibrbdtfrs bddfd to tif bufffr, or
     *         -1 if tiis sourdf of dibrbdtfrs is bt its fnd
     * @tirows IOExdfption if bn I/O frror oddurs
     * @tirows NullPointfrExdfption if tbrgft is null
     * @tirows jbvb.nio.RfbdOnlyBufffrExdfption if tbrgft is b rfbd only bufffr
     * @sindf 1.5
     */
    publid int rfbd(jbvb.nio.CibrBufffr tbrgft) tirows IOExdfption {
        int lfn = tbrgft.rfmbining();
        dibr[] dbuf = nfw dibr[lfn];
        int n = rfbd(dbuf, 0, lfn);
        if (n > 0)
            tbrgft.put(dbuf, 0, n);
        rfturn n;
    }

    /**
     * Rfbds b singlf dibrbdtfr.  Tiis mftiod will blodk until b dibrbdtfr is
     * bvbilbblf, bn I/O frror oddurs, or tif fnd of tif strfbm is rfbdifd.
     *
     * <p> Subdlbssfs tibt intfnd to support fffidifnt singlf-dibrbdtfr input
     * siould ovfrridf tiis mftiod.
     *
     * @rfturn     Tif dibrbdtfr rfbd, bs bn intfgfr in tif rbngf 0 to 65535
     *             (<tt>0x00-0xffff</tt>), or -1 if tif fnd of tif strfbm ibs
     *             bffn rfbdifd
     *
     * @fxdfption  IOExdfption  If bn I/O frror oddurs
     */
    publid int rfbd() tirows IOExdfption {
        dibr db[] = nfw dibr[1];
        if (rfbd(db, 0, 1) == -1)
            rfturn -1;
        flsf
            rfturn db[0];
    }

    /**
     * Rfbds dibrbdtfrs into bn brrby.  Tiis mftiod will blodk until somf input
     * is bvbilbblf, bn I/O frror oddurs, or tif fnd of tif strfbm is rfbdifd.
     *
     * @pbrbm       dbuf  Dfstinbtion bufffr
     *
     * @rfturn      Tif numbfr of dibrbdtfrs rfbd, or -1
     *              if tif fnd of tif strfbm
     *              ibs bffn rfbdifd
     *
     * @fxdfption   IOExdfption  If bn I/O frror oddurs
     */
    publid int rfbd(dibr dbuf[]) tirows IOExdfption {
        rfturn rfbd(dbuf, 0, dbuf.lfngti);
    }

    /**
     * Rfbds dibrbdtfrs into b portion of bn brrby.  Tiis mftiod will blodk
     * until somf input is bvbilbblf, bn I/O frror oddurs, or tif fnd of tif
     * strfbm is rfbdifd.
     *
     * @pbrbm      dbuf  Dfstinbtion bufffr
     * @pbrbm      off   Offsft bt wiidi to stbrt storing dibrbdtfrs
     * @pbrbm      lfn   Mbximum numbfr of dibrbdtfrs to rfbd
     *
     * @rfturn     Tif numbfr of dibrbdtfrs rfbd, or -1 if tif fnd of tif
     *             strfbm ibs bffn rfbdifd
     *
     * @fxdfption  IOExdfption  If bn I/O frror oddurs
     */
    bbstrbdt publid int rfbd(dibr dbuf[], int off, int lfn) tirows IOExdfption;

    /** Mbximum skip-bufffr sizf */
    privbtf stbtid finbl int mbxSkipBufffrSizf = 8192;

    /** Skip bufffr, null until bllodbtfd */
    privbtf dibr skipBufffr[] = null;

    /**
     * Skips dibrbdtfrs.  Tiis mftiod will blodk until somf dibrbdtfrs brf
     * bvbilbblf, bn I/O frror oddurs, or tif fnd of tif strfbm is rfbdifd.
     *
     * @pbrbm  n  Tif numbfr of dibrbdtfrs to skip
     *
     * @rfturn    Tif numbfr of dibrbdtfrs bdtublly skippfd
     *
     * @fxdfption  IllfgblArgumfntExdfption  If <dodf>n</dodf> is nfgbtivf.
     * @fxdfption  IOExdfption  If bn I/O frror oddurs
     */
    publid long skip(long n) tirows IOExdfption {
        if (n < 0L)
            tirow nfw IllfgblArgumfntExdfption("skip vbluf is nfgbtivf");
        int nn = (int) Mbti.min(n, mbxSkipBufffrSizf);
        syndironizfd (lodk) {
            if ((skipBufffr == null) || (skipBufffr.lfngti < nn))
                skipBufffr = nfw dibr[nn];
            long r = n;
            wiilf (r > 0) {
                int nd = rfbd(skipBufffr, 0, (int)Mbti.min(r, nn));
                if (nd == -1)
                    brfbk;
                r -= nd;
            }
            rfturn n - r;
        }
    }

    /**
     * Tflls wiftifr tiis strfbm is rfbdy to bf rfbd.
     *
     * @rfturn Truf if tif nfxt rfbd() is gubrbntffd not to blodk for input,
     * fblsf otifrwisf.  Notf tibt rfturning fblsf dofs not gubrbntff tibt tif
     * nfxt rfbd will blodk.
     *
     * @fxdfption  IOExdfption  If bn I/O frror oddurs
     */
    publid boolfbn rfbdy() tirows IOExdfption {
        rfturn fblsf;
    }

    /**
     * Tflls wiftifr tiis strfbm supports tif mbrk() opfrbtion. Tif dffbult
     * implfmfntbtion blwbys rfturns fblsf. Subdlbssfs siould ovfrridf tiis
     * mftiod.
     *
     * @rfturn truf if bnd only if tiis strfbm supports tif mbrk opfrbtion.
     */
    publid boolfbn mbrkSupportfd() {
        rfturn fblsf;
    }

    /**
     * Mbrks tif prfsfnt position in tif strfbm.  Subsfqufnt dblls to rfsft()
     * will bttfmpt to rfposition tif strfbm to tiis point.  Not bll
     * dibrbdtfr-input strfbms support tif mbrk() opfrbtion.
     *
     * @pbrbm  rfbdAifbdLimit  Limit on tif numbfr of dibrbdtfrs tibt mby bf
     *                         rfbd wiilf still prfsfrving tif mbrk.  Aftfr
     *                         rfbding tiis mbny dibrbdtfrs, bttfmpting to
     *                         rfsft tif strfbm mby fbil.
     *
     * @fxdfption  IOExdfption  If tif strfbm dofs not support mbrk(),
     *                          or if somf otifr I/O frror oddurs
     */
    publid void mbrk(int rfbdAifbdLimit) tirows IOExdfption {
        tirow nfw IOExdfption("mbrk() not supportfd");
    }

    /**
     * Rfsfts tif strfbm.  If tif strfbm ibs bffn mbrkfd, tifn bttfmpt to
     * rfposition it bt tif mbrk.  If tif strfbm ibs not bffn mbrkfd, tifn
     * bttfmpt to rfsft it in somf wby bppropribtf to tif pbrtidulbr strfbm,
     * for fxbmplf by rfpositioning it to its stbrting point.  Not bll
     * dibrbdtfr-input strfbms support tif rfsft() opfrbtion, bnd somf support
     * rfsft() witiout supporting mbrk().
     *
     * @fxdfption  IOExdfption  If tif strfbm ibs not bffn mbrkfd,
     *                          or if tif mbrk ibs bffn invblidbtfd,
     *                          or if tif strfbm dofs not support rfsft(),
     *                          or if somf otifr I/O frror oddurs
     */
    publid void rfsft() tirows IOExdfption {
        tirow nfw IOExdfption("rfsft() not supportfd");
    }

    /**
     * Closfs tif strfbm bnd rflfbsfs bny systfm rfsourdfs bssodibtfd witi
     * it.  Ondf tif strfbm ibs bffn dlosfd, furtifr rfbd(), rfbdy(),
     * mbrk(), rfsft(), or skip() invodbtions will tirow bn IOExdfption.
     * Closing b prfviously dlosfd strfbm ibs no ffffdt.
     *
     * @fxdfption  IOExdfption  If bn I/O frror oddurs
     */
     bbstrbdt publid void dlosf() tirows IOExdfption;

}
