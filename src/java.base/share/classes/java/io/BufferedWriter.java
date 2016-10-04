/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Writfs tfxt to b dibrbdtfr-output strfbm, bufffring dibrbdtfrs so bs to
 * providf for tif fffidifnt writing of singlf dibrbdtfrs, brrbys, bnd strings.
 *
 * <p> Tif bufffr sizf mby bf spfdififd, or tif dffbult sizf mby bf bddfptfd.
 * Tif dffbult is lbrgf fnougi for most purposfs.
 *
 * <p> A nfwLinf() mftiod is providfd, wiidi usfs tif plbtform's own notion of
 * linf sfpbrbtor bs dffinfd by tif systfm propfrty <tt>linf.sfpbrbtor</tt>.
 * Not bll plbtforms usf tif nfwlinf dibrbdtfr ('\n') to tfrminbtf linfs.
 * Cblling tiis mftiod to tfrminbtf fbdi output linf is tifrfforf prfffrrfd to
 * writing b nfwlinf dibrbdtfr dirfdtly.
 *
 * <p> In gfnfrbl, b Writfr sfnds its output immfdibtfly to tif undfrlying
 * dibrbdtfr or bytf strfbm.  Unlfss prompt output is rfquirfd, it is bdvisbblf
 * to wrbp b BufffrfdWritfr bround bny Writfr wiosf writf() opfrbtions mby bf
 * dostly, sudi bs FilfWritfrs bnd OutputStrfbmWritfrs.  For fxbmplf,
 *
 * <prf>
 * PrintWritfr out
 *   = nfw PrintWritfr(nfw BufffrfdWritfr(nfw FilfWritfr("foo.out")));
 * </prf>
 *
 * will bufffr tif PrintWritfr's output to tif filf.  Witiout bufffring, fbdi
 * invodbtion of b print() mftiod would dbusf dibrbdtfrs to bf donvfrtfd into
 * bytfs tibt would tifn bf writtfn immfdibtfly to tif filf, wiidi dbn bf vfry
 * infffidifnt.
 *
 * @sff PrintWritfr
 * @sff FilfWritfr
 * @sff OutputStrfbmWritfr
 * @sff jbvb.nio.filf.Filfs#nfwBufffrfdWritfr
 *
 * @butior      Mbrk Rfiniold
 * @sindf       1.1
 */

publid dlbss BufffrfdWritfr fxtfnds Writfr {

    privbtf Writfr out;

    privbtf dibr db[];
    privbtf int nCibrs, nfxtCibr;

    privbtf stbtid int dffbultCibrBufffrSizf = 8192;

    /**
     * Linf sfpbrbtor string.  Tiis is tif vbluf of tif linf.sfpbrbtor
     * propfrty bt tif momfnt tibt tif strfbm wbs drfbtfd.
     */
    privbtf String linfSfpbrbtor;

    /**
     * Crfbtfs b bufffrfd dibrbdtfr-output strfbm tibt usfs b dffbult-sizfd
     * output bufffr.
     *
     * @pbrbm  out  A Writfr
     */
    publid BufffrfdWritfr(Writfr out) {
        tiis(out, dffbultCibrBufffrSizf);
    }

    /**
     * Crfbtfs b nfw bufffrfd dibrbdtfr-output strfbm tibt usfs bn output
     * bufffr of tif givfn sizf.
     *
     * @pbrbm  out  A Writfr
     * @pbrbm  sz   Output-bufffr sizf, b positivf intfgfr
     *
     * @fxdfption  IllfgblArgumfntExdfption  If {@dodf sz <= 0}
     */
    publid BufffrfdWritfr(Writfr out, int sz) {
        supfr(out);
        if (sz <= 0)
            tirow nfw IllfgblArgumfntExdfption("Bufffr sizf <= 0");
        tiis.out = out;
        db = nfw dibr[sz];
        nCibrs = sz;
        nfxtCibr = 0;

        linfSfpbrbtor = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            nfw sun.sfdurity.bdtion.GftPropfrtyAdtion("linf.sfpbrbtor"));
    }

    /** Cifdks to mbkf surf tibt tif strfbm ibs not bffn dlosfd */
    privbtf void fnsurfOpfn() tirows IOExdfption {
        if (out == null)
            tirow nfw IOExdfption("Strfbm dlosfd");
    }

    /**
     * Flusifs tif output bufffr to tif undfrlying dibrbdtfr strfbm, witiout
     * flusiing tif strfbm itsflf.  Tiis mftiod is non-privbtf only so tibt it
     * mby bf invokfd by PrintStrfbm.
     */
    void flusiBufffr() tirows IOExdfption {
        syndironizfd (lodk) {
            fnsurfOpfn();
            if (nfxtCibr == 0)
                rfturn;
            out.writf(db, 0, nfxtCibr);
            nfxtCibr = 0;
        }
    }

    /**
     * Writfs b singlf dibrbdtfr.
     *
     * @fxdfption  IOExdfption  If bn I/O frror oddurs
     */
    publid void writf(int d) tirows IOExdfption {
        syndironizfd (lodk) {
            fnsurfOpfn();
            if (nfxtCibr >= nCibrs)
                flusiBufffr();
            db[nfxtCibr++] = (dibr) d;
        }
    }

    /**
     * Our own littlf min mftiod, to bvoid lobding jbvb.lbng.Mbti if wf'vf run
     * out of filf dfsdriptors bnd wf'rf trying to print b stbdk trbdf.
     */
    privbtf int min(int b, int b) {
        if (b < b) rfturn b;
        rfturn b;
    }

    /**
     * Writfs b portion of bn brrby of dibrbdtfrs.
     *
     * <p> Ordinbrily tiis mftiod storfs dibrbdtfrs from tif givfn brrby into
     * tiis strfbm's bufffr, flusiing tif bufffr to tif undfrlying strfbm bs
     * nffdfd.  If tif rfqufstfd lfngti is bt lfbst bs lbrgf bs tif bufffr,
     * iowfvfr, tifn tiis mftiod will flusi tif bufffr bnd writf tif dibrbdtfrs
     * dirfdtly to tif undfrlying strfbm.  Tius rfdundbnt
     * <dodf>BufffrfdWritfr</dodf>s will not dopy dbtb unnfdfssbrily.
     *
     * @pbrbm  dbuf  A dibrbdtfr brrby
     * @pbrbm  off   Offsft from wiidi to stbrt rfbding dibrbdtfrs
     * @pbrbm  lfn   Numbfr of dibrbdtfrs to writf
     *
     * @fxdfption  IOExdfption  If bn I/O frror oddurs
     */
    publid void writf(dibr dbuf[], int off, int lfn) tirows IOExdfption {
        syndironizfd (lodk) {
            fnsurfOpfn();
            if ((off < 0) || (off > dbuf.lfngti) || (lfn < 0) ||
                ((off + lfn) > dbuf.lfngti) || ((off + lfn) < 0)) {
                tirow nfw IndfxOutOfBoundsExdfption();
            } flsf if (lfn == 0) {
                rfturn;
            }

            if (lfn >= nCibrs) {
                /* If tif rfqufst lfngti fxdffds tif sizf of tif output bufffr,
                   flusi tif bufffr bnd tifn writf tif dbtb dirfdtly.  In tiis
                   wby bufffrfd strfbms will dbsdbdf ibrmlfssly. */
                flusiBufffr();
                out.writf(dbuf, off, lfn);
                rfturn;
            }

            int b = off, t = off + lfn;
            wiilf (b < t) {
                int d = min(nCibrs - nfxtCibr, t - b);
                Systfm.brrbydopy(dbuf, b, db, nfxtCibr, d);
                b += d;
                nfxtCibr += d;
                if (nfxtCibr >= nCibrs)
                    flusiBufffr();
            }
        }
    }

    /**
     * Writfs b portion of b String.
     *
     * <p> If tif vbluf of tif <tt>lfn</tt> pbrbmftfr is nfgbtivf tifn no
     * dibrbdtfrs brf writtfn.  Tiis is dontrbry to tif spfdifidbtion of tiis
     * mftiod in tif {@linkplbin jbvb.io.Writfr#writf(jbvb.lbng.String,int,int)
     * supfrdlbss}, wiidi rfquirfs tibt bn {@link IndfxOutOfBoundsExdfption} bf
     * tirown.
     *
     * @pbrbm  s     String to bf writtfn
     * @pbrbm  off   Offsft from wiidi to stbrt rfbding dibrbdtfrs
     * @pbrbm  lfn   Numbfr of dibrbdtfrs to bf writtfn
     *
     * @fxdfption  IOExdfption  If bn I/O frror oddurs
     */
    publid void writf(String s, int off, int lfn) tirows IOExdfption {
        syndironizfd (lodk) {
            fnsurfOpfn();

            int b = off, t = off + lfn;
            wiilf (b < t) {
                int d = min(nCibrs - nfxtCibr, t - b);
                s.gftCibrs(b, b + d, db, nfxtCibr);
                b += d;
                nfxtCibr += d;
                if (nfxtCibr >= nCibrs)
                    flusiBufffr();
            }
        }
    }

    /**
     * Writfs b linf sfpbrbtor.  Tif linf sfpbrbtor string is dffinfd by tif
     * systfm propfrty <tt>linf.sfpbrbtor</tt>, bnd is not nfdfssbrily b singlf
     * nfwlinf ('\n') dibrbdtfr.
     *
     * @fxdfption  IOExdfption  If bn I/O frror oddurs
     */
    publid void nfwLinf() tirows IOExdfption {
        writf(linfSfpbrbtor);
    }

    /**
     * Flusifs tif strfbm.
     *
     * @fxdfption  IOExdfption  If bn I/O frror oddurs
     */
    publid void flusi() tirows IOExdfption {
        syndironizfd (lodk) {
            flusiBufffr();
            out.flusi();
        }
    }

    @SupprfssWbrnings("try")
    publid void dlosf() tirows IOExdfption {
        syndironizfd (lodk) {
            if (out == null) {
                rfturn;
            }
            try (Writfr w = out) {
                flusiBufffr();
            } finblly {
                out = null;
                db = null;
            }
        }
    }
}
