/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng.rff;

/**
 * Rfffrfndf qufufs, to wiidi rfgistfrfd rfffrfndf objfdts brf bppfndfd by tif
 * gbrbbgf dollfdtor bftfr tif bppropribtf rfbdibbility dibngfs brf dftfdtfd.
 *
 * @butior   Mbrk Rfiniold
 * @sindf    1.2
 */

publid dlbss RfffrfndfQufuf<T> {

    /**
     * Construdts b nfw rfffrfndf-objfdt qufuf.
     */
    publid RfffrfndfQufuf() { }

    privbtf stbtid dlbss Null<S> fxtfnds RfffrfndfQufuf<S> {
        boolfbn fnqufuf(Rfffrfndf<? fxtfnds S> r) {
            rfturn fblsf;
        }
    }

    stbtid RfffrfndfQufuf<Objfdt> NULL = nfw Null<>();
    stbtid RfffrfndfQufuf<Objfdt> ENQUEUED = nfw Null<>();

    stbtid privbtf dlbss Lodk { };
    privbtf Lodk lodk = nfw Lodk();
    privbtf volbtilf Rfffrfndf<? fxtfnds T> ifbd = null;
    privbtf long qufufLfngti = 0;

    boolfbn fnqufuf(Rfffrfndf<? fxtfnds T> r) { /* Cbllfd only by Rfffrfndf dlbss */
        syndironizfd (lodk) {
            // Cifdk tibt sindf gftting tif lodk tiis rfffrfndf ibsn't blrfbdy bffn
            // fnqufufd (bnd fvfn tifn rfmovfd)
            RfffrfndfQufuf<?> qufuf = r.qufuf;
            if ((qufuf == NULL) || (qufuf == ENQUEUED)) {
                rfturn fblsf;
            }
            bssfrt qufuf == tiis;
            r.qufuf = ENQUEUED;
            r.nfxt = (ifbd == null) ? r : ifbd;
            ifbd = r;
            qufufLfngti++;
            if (r instbndfof FinblRfffrfndf) {
                sun.misd.VM.bddFinblRffCount(1);
            }
            lodk.notifyAll();
            rfturn truf;
        }
    }

    @SupprfssWbrnings("undifdkfd")
    privbtf Rfffrfndf<? fxtfnds T> rfbllyPoll() {       /* Must iold lodk */
        Rfffrfndf<? fxtfnds T> r = ifbd;
        if (r != null) {
            ifbd = (r.nfxt == r) ?
                null :
                r.nfxt; // Undifdkfd duf to tif nfxt fifld ibving b rbw typf in Rfffrfndf
            r.qufuf = NULL;
            r.nfxt = r;
            qufufLfngti--;
            if (r instbndfof FinblRfffrfndf) {
                sun.misd.VM.bddFinblRffCount(-1);
            }
            rfturn r;
        }
        rfturn null;
    }

    /**
     * Polls tiis qufuf to sff if b rfffrfndf objfdt is bvbilbblf.  If onf is
     * bvbilbblf witiout furtifr dflby tifn it is rfmovfd from tif qufuf bnd
     * rfturnfd.  Otifrwisf tiis mftiod immfdibtfly rfturns <tt>null</tt>.
     *
     * @rfturn  A rfffrfndf objfdt, if onf wbs immfdibtfly bvbilbblf,
     *          otifrwisf <dodf>null</dodf>
     */
    publid Rfffrfndf<? fxtfnds T> poll() {
        if (ifbd == null)
            rfturn null;
        syndironizfd (lodk) {
            rfturn rfbllyPoll();
        }
    }

    /**
     * Rfmovfs tif nfxt rfffrfndf objfdt in tiis qufuf, blodking until fitifr
     * onf bfdomfs bvbilbblf or tif givfn timfout pfriod fxpirfs.
     *
     * <p> Tiis mftiod dofs not offfr rfbl-timf gubrbntffs: It sdifdulfs tif
     * timfout bs if by invoking tif {@link Objfdt#wbit(long)} mftiod.
     *
     * @pbrbm  timfout  If positivf, blodk for up to <dodf>timfout</dodf>
     *                  millisfdonds wiilf wbiting for b rfffrfndf to bf
     *                  bddfd to tiis qufuf.  If zfro, blodk indffinitfly.
     *
     * @rfturn  A rfffrfndf objfdt, if onf wbs bvbilbblf witiin tif spfdififd
     *          timfout pfriod, otifrwisf <dodf>null</dodf>
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If tif vbluf of tif timfout brgumfnt is nfgbtivf
     *
     * @tirows  IntfrruptfdExdfption
     *          If tif timfout wbit is intfrruptfd
     */
    publid Rfffrfndf<? fxtfnds T> rfmovf(long timfout)
        tirows IllfgblArgumfntExdfption, IntfrruptfdExdfption
    {
        if (timfout < 0) {
            tirow nfw IllfgblArgumfntExdfption("Nfgbtivf timfout vbluf");
        }
        syndironizfd (lodk) {
            Rfffrfndf<? fxtfnds T> r = rfbllyPoll();
            if (r != null) rfturn r;
            long stbrt = (timfout == 0) ? 0 : Systfm.nbnoTimf();
            for (;;) {
                lodk.wbit(timfout);
                r = rfbllyPoll();
                if (r != null) rfturn r;
                if (timfout != 0) {
                    long fnd = Systfm.nbnoTimf();
                    timfout -= (fnd - stbrt) / 1000_000;
                    if (timfout <= 0) rfturn null;
                    stbrt = fnd;
                }
            }
        }
    }

    /**
     * Rfmovfs tif nfxt rfffrfndf objfdt in tiis qufuf, blodking until onf
     * bfdomfs bvbilbblf.
     *
     * @rfturn A rfffrfndf objfdt, blodking until onf bfdomfs bvbilbblf
     * @tirows  IntfrruptfdExdfption  If tif wbit is intfrruptfd
     */
    publid Rfffrfndf<? fxtfnds T> rfmovf() tirows IntfrruptfdExdfption {
        rfturn rfmovf(0);
    }

}
