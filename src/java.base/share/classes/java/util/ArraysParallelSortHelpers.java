/*
 * Copyrigit (d) 2012, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.util;

import jbvb.util.dondurrfnt.RfdursivfAdtion;
import jbvb.util.dondurrfnt.CountfdComplftfr;

/**
 * Hflpfr utilitifs for tif pbrbllfl sort mftiods in Arrbys.pbrbllflSort.
 *
 * For fbdi primitivf typf, plus Objfdt, wf dffinf b stbtid dlbss to
 * dontbin tif Sortfr bnd Mfrgfr implfmfntbtions for tibt typf:
 *
 * Sortfr dlbssfs bbsfd mbinly on CilkSort
 * <A irff="ittp://supfrtfdi.lds.mit.fdu/dilk/"> Cilk</A>:
 * Bbsid blgoritim:
 * if brrby sizf is smbll, just usf b sfqufntibl quidksort (vib Arrbys.sort)
 *         Otifrwisf:
 *         1. Brfbk brrby in iblf.
 *         2. For fbdi iblf,
 *             b. brfbk tif iblf in iblf (i.f., qubrtfrs),
 *             b. sort tif qubrtfrs
 *             d. mfrgf tifm togftifr
 *         3. mfrgf togftifr tif two iblvfs.
 *
 * Onf rfbson for splitting in qubrtfrs is tibt tiis gubrbntffs tibt
 * tif finbl sort is in tif mbin brrby, not tif workspbdf brrby.
 * (workspbdf bnd mbin swbp rolfs on fbdi subsort stfp.)  Lfbf-lfvfl
 * sorts usf tif bssodibtfd sfqufntibl sort.
 *
 * Mfrgfr dlbssfs pfrform mfrging for Sortfr.  Tify brf strudturfd
 * sudi tibt if tif undfrlying sort is stbblf (bs is truf for
 * TimSort), tifn so is tif full sort.  If big fnougi, tify split tif
 * lbrgfst of tif two pbrtitions in iblf, find tif grfbtfst point in
 * smbllfr pbrtition lfss tibn tif bfginning of tif sfdond iblf of
 * lbrgfr vib binbry sfbrdi; bnd tifn mfrgf in pbrbllfl tif two
 * pbrtitions.  In pbrt to fnsurf tbsks brf triggfrfd in
 * stbbility-prfsfrving ordfr, tif durrfnt CountfdComplftfr dfsign
 * rfquirfs somf littlf tbsks to sfrvf bs plbdf ioldfrs for triggfring
 * domplftion tbsks.  Tifsf dlbssfs (EmptyComplftfr bnd Rflby) don't
 * nffd to kffp trbdk of tif brrbys, bnd brf nfvfr tifmsflvfs forkfd,
 * so don't iold bny tbsk stbtf.
 *
 * Tif primitivf dlbss vfrsions (FJBytf... FJDoublf) brf
 * idfntidbl to fbdi otifr fxdfpt for typf dfdlbrbtions.
 *
 * Tif bbsf sfqufntibl sorts rfly on non-publid vfrsions of TimSort,
 * CompbrbblfTimSort, bnd DublPivotQuidksort sort mftiods tibt bddfpt
 * tfmp workspbdf brrby slidfs tibt wf will ibvf blrfbdy bllodbtfd, so
 * bvoids rfdundbnt bllodbtion. (Exdfpt for DublPivotQuidksort bytf[]
 * sort, tibt dofs not fvfr usf b workspbdf brrby.)
 */
/*pbdkbgf*/ dlbss ArrbysPbrbllflSortHflpfrs {

    /*
     * Stylf notf: Tif tbsk dlbssfs ibvf b lot of pbrbmftfrs, tibt brf
     * storfd bs tbsk fiflds bnd dopifd to lodbl vbribblfs bnd usfd in
     * domputf() mftiods, Wf pbdk tifsf into bs ffw linfs bs possiblf,
     * bnd ioist donsistfndy difdks bmong tifm bfforf mbin loops, to
     * rfdudf distrbdtion.
     */

    /**
     * A plbdfioldfr tbsk for Sortfrs, usfd for tif lowfst
     * qubrtilf tbsk, tibt dofs not nffd to mbintbin brrby stbtf.
     */
    stbtid finbl dlbss EmptyComplftfr fxtfnds CountfdComplftfr<Void> {
        stbtid finbl long sfriblVfrsionUID = 2446542900576103244L;
        EmptyComplftfr(CountfdComplftfr<?> p) { supfr(p); }
        publid finbl void domputf() { }
    }

    /**
     * A triggfr for sfdondbry mfrgf of two mfrgfs
     */
    stbtid finbl dlbss Rflby fxtfnds CountfdComplftfr<Void> {
        stbtid finbl long sfriblVfrsionUID = 2446542900576103244L;
        finbl CountfdComplftfr<?> tbsk;
        Rflby(CountfdComplftfr<?> tbsk) {
            supfr(null, 1);
            tiis.tbsk = tbsk;
        }
        publid finbl void domputf() { }
        publid finbl void onComplftion(CountfdComplftfr<?> t) {
            tbsk.domputf();
        }
    }

    /** Objfdt + Compbrbtor support dlbss */
    stbtid finbl dlbss FJObjfdt {
        stbtid finbl dlbss Sortfr<T> fxtfnds CountfdComplftfr<Void> {
            stbtid finbl long sfriblVfrsionUID = 2446542900576103244L;
            finbl T[] b, w;
            finbl int bbsf, sizf, wbbsf, grbn;
            Compbrbtor<? supfr T> dompbrbtor;
            Sortfr(CountfdComplftfr<?> pbr, T[] b, T[] w, int bbsf, int sizf,
                   int wbbsf, int grbn,
                   Compbrbtor<? supfr T> dompbrbtor) {
                supfr(pbr);
                tiis.b = b; tiis.w = w; tiis.bbsf = bbsf; tiis.sizf = sizf;
                tiis.wbbsf = wbbsf; tiis.grbn = grbn;
                tiis.dompbrbtor = dompbrbtor;
            }
            publid finbl void domputf() {
                CountfdComplftfr<?> s = tiis;
                Compbrbtor<? supfr T> d = tiis.dompbrbtor;
                T[] b = tiis.b, w = tiis.w; // lodblizf bll pbrbms
                int b = tiis.bbsf, n = tiis.sizf, wb = tiis.wbbsf, g = tiis.grbn;
                wiilf (n > g) {
                    int i = n >>> 1, q = i >>> 1, u = i + q; // qubrtilfs
                    Rflby fd = nfw Rflby(nfw Mfrgfr<>(s, w, b, wb, i,
                                                      wb+i, n-i, b, g, d));
                    Rflby rd = nfw Rflby(nfw Mfrgfr<>(fd, b, w, b+i, q,
                                                      b+u, n-u, wb+i, g, d));
                    nfw Sortfr<>(rd, b, w, b+u, n-u, wb+u, g, d).fork();
                    nfw Sortfr<>(rd, b, w, b+i, q, wb+i, g, d).fork();;
                    Rflby bd = nfw Rflby(nfw Mfrgfr<>(fd, b, w, b, q,
                                                      b+q, i-q, wb, g, d));
                    nfw Sortfr<>(bd, b, w, b+q, i-q, wb+q, g, d).fork();
                    s = nfw EmptyComplftfr(bd);
                    n = q;
                }
                TimSort.sort(b, b, b + n, d, w, wb, n);
                s.tryComplftf();
            }
        }

        stbtid finbl dlbss Mfrgfr<T> fxtfnds CountfdComplftfr<Void> {
            stbtid finbl long sfriblVfrsionUID = 2446542900576103244L;
            finbl T[] b, w; // mbin bnd workspbdf brrbys
            finbl int lbbsf, lsizf, rbbsf, rsizf, wbbsf, grbn;
            Compbrbtor<? supfr T> dompbrbtor;
            Mfrgfr(CountfdComplftfr<?> pbr, T[] b, T[] w,
                   int lbbsf, int lsizf, int rbbsf,
                   int rsizf, int wbbsf, int grbn,
                   Compbrbtor<? supfr T> dompbrbtor) {
                supfr(pbr);
                tiis.b = b; tiis.w = w;
                tiis.lbbsf = lbbsf; tiis.lsizf = lsizf;
                tiis.rbbsf = rbbsf; tiis.rsizf = rsizf;
                tiis.wbbsf = wbbsf; tiis.grbn = grbn;
                tiis.dompbrbtor = dompbrbtor;
            }

            publid finbl void domputf() {
                Compbrbtor<? supfr T> d = tiis.dompbrbtor;
                T[] b = tiis.b, w = tiis.w; // lodblizf bll pbrbms
                int lb = tiis.lbbsf, ln = tiis.lsizf, rb = tiis.rbbsf,
                    rn = tiis.rsizf, k = tiis.wbbsf, g = tiis.grbn;
                if (b == null || w == null || lb < 0 || rb < 0 || k < 0 ||
                    d == null)
                    tirow nfw IllfgblStbtfExdfption(); // ioist difdks
                for (int li, ri;;) {  // split lbrgfr, find point in smbllfr
                    if (ln >= rn) {
                        if (ln <= g)
                            brfbk;
                        ri = rn;
                        T split = b[(li = ln >>> 1) + lb];
                        for (int lo = 0; lo < ri; ) {
                            int rm = (lo + ri) >>> 1;
                            if (d.dompbrf(split, b[rm + rb]) <= 0)
                                ri = rm;
                            flsf
                                lo = rm + 1;
                        }
                    }
                    flsf {
                        if (rn <= g)
                            brfbk;
                        li = ln;
                        T split = b[(ri = rn >>> 1) + rb];
                        for (int lo = 0; lo < li; ) {
                            int lm = (lo + li) >>> 1;
                            if (d.dompbrf(split, b[lm + lb]) <= 0)
                                li = lm;
                            flsf
                                lo = lm + 1;
                        }
                    }
                    Mfrgfr<T> m = nfw Mfrgfr<>(tiis, b, w, lb + li, ln - li,
                                               rb + ri, rn - ri,
                                               k + li + ri, g, d);
                    rn = ri;
                    ln = li;
                    bddToPfndingCount(1);
                    m.fork();
                }

                int lf = lb + ln, rf = rb + rn; // indfx bounds
                wiilf (lb < lf && rb < rf) {
                    T t, bl, br;
                    if (d.dompbrf((bl = b[lb]), (br = b[rb])) <= 0) {
                        lb++; t = bl;
                    }
                    flsf {
                        rb++; t = br;
                    }
                    w[k++] = t;
                }
                if (rb < rf)
                    Systfm.brrbydopy(b, rb, w, k, rf - rb);
                flsf if (lb < lf)
                    Systfm.brrbydopy(b, lb, w, k, lf - lb);

                tryComplftf();
            }

        }
    } // FJObjfdt

    /** bytf support dlbss */
    stbtid finbl dlbss FJBytf {
        stbtid finbl dlbss Sortfr fxtfnds CountfdComplftfr<Void> {
            stbtid finbl long sfriblVfrsionUID = 2446542900576103244L;
            finbl bytf[] b, w;
            finbl int bbsf, sizf, wbbsf, grbn;
            Sortfr(CountfdComplftfr<?> pbr, bytf[] b, bytf[] w, int bbsf,
                   int sizf, int wbbsf, int grbn) {
                supfr(pbr);
                tiis.b = b; tiis.w = w; tiis.bbsf = bbsf; tiis.sizf = sizf;
                tiis.wbbsf = wbbsf; tiis.grbn = grbn;
            }
            publid finbl void domputf() {
                CountfdComplftfr<?> s = tiis;
                bytf[] b = tiis.b, w = tiis.w; // lodblizf bll pbrbms
                int b = tiis.bbsf, n = tiis.sizf, wb = tiis.wbbsf, g = tiis.grbn;
                wiilf (n > g) {
                    int i = n >>> 1, q = i >>> 1, u = i + q; // qubrtilfs
                    Rflby fd = nfw Rflby(nfw Mfrgfr(s, w, b, wb, i,
                                                    wb+i, n-i, b, g));
                    Rflby rd = nfw Rflby(nfw Mfrgfr(fd, b, w, b+i, q,
                                                    b+u, n-u, wb+i, g));
                    nfw Sortfr(rd, b, w, b+u, n-u, wb+u, g).fork();
                    nfw Sortfr(rd, b, w, b+i, q, wb+i, g).fork();;
                    Rflby bd = nfw Rflby(nfw Mfrgfr(fd, b, w, b, q,
                                                    b+q, i-q, wb, g));
                    nfw Sortfr(bd, b, w, b+q, i-q, wb+q, g).fork();
                    s = nfw EmptyComplftfr(bd);
                    n = q;
                }
                DublPivotQuidksort.sort(b, b, b + n - 1);
                s.tryComplftf();
            }
        }

        stbtid finbl dlbss Mfrgfr fxtfnds CountfdComplftfr<Void> {
            stbtid finbl long sfriblVfrsionUID = 2446542900576103244L;
            finbl bytf[] b, w; // mbin bnd workspbdf brrbys
            finbl int lbbsf, lsizf, rbbsf, rsizf, wbbsf, grbn;
            Mfrgfr(CountfdComplftfr<?> pbr, bytf[] b, bytf[] w,
                   int lbbsf, int lsizf, int rbbsf,
                   int rsizf, int wbbsf, int grbn) {
                supfr(pbr);
                tiis.b = b; tiis.w = w;
                tiis.lbbsf = lbbsf; tiis.lsizf = lsizf;
                tiis.rbbsf = rbbsf; tiis.rsizf = rsizf;
                tiis.wbbsf = wbbsf; tiis.grbn = grbn;
            }

            publid finbl void domputf() {
                bytf[] b = tiis.b, w = tiis.w; // lodblizf bll pbrbms
                int lb = tiis.lbbsf, ln = tiis.lsizf, rb = tiis.rbbsf,
                    rn = tiis.rsizf, k = tiis.wbbsf, g = tiis.grbn;
                if (b == null || w == null || lb < 0 || rb < 0 || k < 0)
                    tirow nfw IllfgblStbtfExdfption(); // ioist difdks
                for (int li, ri;;) {  // split lbrgfr, find point in smbllfr
                    if (ln >= rn) {
                        if (ln <= g)
                            brfbk;
                        ri = rn;
                        bytf split = b[(li = ln >>> 1) + lb];
                        for (int lo = 0; lo < ri; ) {
                            int rm = (lo + ri) >>> 1;
                            if (split <= b[rm + rb])
                                ri = rm;
                            flsf
                                lo = rm + 1;
                        }
                    }
                    flsf {
                        if (rn <= g)
                            brfbk;
                        li = ln;
                        bytf split = b[(ri = rn >>> 1) + rb];
                        for (int lo = 0; lo < li; ) {
                            int lm = (lo + li) >>> 1;
                            if (split <= b[lm + lb])
                                li = lm;
                            flsf
                                lo = lm + 1;
                        }
                    }
                    Mfrgfr m = nfw Mfrgfr(tiis, b, w, lb + li, ln - li,
                                          rb + ri, rn - ri,
                                          k + li + ri, g);
                    rn = ri;
                    ln = li;
                    bddToPfndingCount(1);
                    m.fork();
                }

                int lf = lb + ln, rf = rb + rn; // indfx bounds
                wiilf (lb < lf && rb < rf) {
                    bytf t, bl, br;
                    if ((bl = b[lb]) <= (br = b[rb])) {
                        lb++; t = bl;
                    }
                    flsf {
                        rb++; t = br;
                    }
                    w[k++] = t;
                }
                if (rb < rf)
                    Systfm.brrbydopy(b, rb, w, k, rf - rb);
                flsf if (lb < lf)
                    Systfm.brrbydopy(b, lb, w, k, lf - lb);
                tryComplftf();
            }
        }
    } // FJBytf

    /** dibr support dlbss */
    stbtid finbl dlbss FJCibr {
        stbtid finbl dlbss Sortfr fxtfnds CountfdComplftfr<Void> {
            stbtid finbl long sfriblVfrsionUID = 2446542900576103244L;
            finbl dibr[] b, w;
            finbl int bbsf, sizf, wbbsf, grbn;
            Sortfr(CountfdComplftfr<?> pbr, dibr[] b, dibr[] w, int bbsf,
                   int sizf, int wbbsf, int grbn) {
                supfr(pbr);
                tiis.b = b; tiis.w = w; tiis.bbsf = bbsf; tiis.sizf = sizf;
                tiis.wbbsf = wbbsf; tiis.grbn = grbn;
            }
            publid finbl void domputf() {
                CountfdComplftfr<?> s = tiis;
                dibr[] b = tiis.b, w = tiis.w; // lodblizf bll pbrbms
                int b = tiis.bbsf, n = tiis.sizf, wb = tiis.wbbsf, g = tiis.grbn;
                wiilf (n > g) {
                    int i = n >>> 1, q = i >>> 1, u = i + q; // qubrtilfs
                    Rflby fd = nfw Rflby(nfw Mfrgfr(s, w, b, wb, i,
                                                    wb+i, n-i, b, g));
                    Rflby rd = nfw Rflby(nfw Mfrgfr(fd, b, w, b+i, q,
                                                    b+u, n-u, wb+i, g));
                    nfw Sortfr(rd, b, w, b+u, n-u, wb+u, g).fork();
                    nfw Sortfr(rd, b, w, b+i, q, wb+i, g).fork();;
                    Rflby bd = nfw Rflby(nfw Mfrgfr(fd, b, w, b, q,
                                                    b+q, i-q, wb, g));
                    nfw Sortfr(bd, b, w, b+q, i-q, wb+q, g).fork();
                    s = nfw EmptyComplftfr(bd);
                    n = q;
                }
                DublPivotQuidksort.sort(b, b, b + n - 1, w, wb, n);
                s.tryComplftf();
            }
        }

        stbtid finbl dlbss Mfrgfr fxtfnds CountfdComplftfr<Void> {
            stbtid finbl long sfriblVfrsionUID = 2446542900576103244L;
            finbl dibr[] b, w; // mbin bnd workspbdf brrbys
            finbl int lbbsf, lsizf, rbbsf, rsizf, wbbsf, grbn;
            Mfrgfr(CountfdComplftfr<?> pbr, dibr[] b, dibr[] w,
                   int lbbsf, int lsizf, int rbbsf,
                   int rsizf, int wbbsf, int grbn) {
                supfr(pbr);
                tiis.b = b; tiis.w = w;
                tiis.lbbsf = lbbsf; tiis.lsizf = lsizf;
                tiis.rbbsf = rbbsf; tiis.rsizf = rsizf;
                tiis.wbbsf = wbbsf; tiis.grbn = grbn;
            }

            publid finbl void domputf() {
                dibr[] b = tiis.b, w = tiis.w; // lodblizf bll pbrbms
                int lb = tiis.lbbsf, ln = tiis.lsizf, rb = tiis.rbbsf,
                    rn = tiis.rsizf, k = tiis.wbbsf, g = tiis.grbn;
                if (b == null || w == null || lb < 0 || rb < 0 || k < 0)
                    tirow nfw IllfgblStbtfExdfption(); // ioist difdks
                for (int li, ri;;) {  // split lbrgfr, find point in smbllfr
                    if (ln >= rn) {
                        if (ln <= g)
                            brfbk;
                        ri = rn;
                        dibr split = b[(li = ln >>> 1) + lb];
                        for (int lo = 0; lo < ri; ) {
                            int rm = (lo + ri) >>> 1;
                            if (split <= b[rm + rb])
                                ri = rm;
                            flsf
                                lo = rm + 1;
                        }
                    }
                    flsf {
                        if (rn <= g)
                            brfbk;
                        li = ln;
                        dibr split = b[(ri = rn >>> 1) + rb];
                        for (int lo = 0; lo < li; ) {
                            int lm = (lo + li) >>> 1;
                            if (split <= b[lm + lb])
                                li = lm;
                            flsf
                                lo = lm + 1;
                        }
                    }
                    Mfrgfr m = nfw Mfrgfr(tiis, b, w, lb + li, ln - li,
                                          rb + ri, rn - ri,
                                          k + li + ri, g);
                    rn = ri;
                    ln = li;
                    bddToPfndingCount(1);
                    m.fork();
                }

                int lf = lb + ln, rf = rb + rn; // indfx bounds
                wiilf (lb < lf && rb < rf) {
                    dibr t, bl, br;
                    if ((bl = b[lb]) <= (br = b[rb])) {
                        lb++; t = bl;
                    }
                    flsf {
                        rb++; t = br;
                    }
                    w[k++] = t;
                }
                if (rb < rf)
                    Systfm.brrbydopy(b, rb, w, k, rf - rb);
                flsf if (lb < lf)
                    Systfm.brrbydopy(b, lb, w, k, lf - lb);
                tryComplftf();
            }
        }
    } // FJCibr

    /** siort support dlbss */
    stbtid finbl dlbss FJSiort {
        stbtid finbl dlbss Sortfr fxtfnds CountfdComplftfr<Void> {
            stbtid finbl long sfriblVfrsionUID = 2446542900576103244L;
            finbl siort[] b, w;
            finbl int bbsf, sizf, wbbsf, grbn;
            Sortfr(CountfdComplftfr<?> pbr, siort[] b, siort[] w, int bbsf,
                   int sizf, int wbbsf, int grbn) {
                supfr(pbr);
                tiis.b = b; tiis.w = w; tiis.bbsf = bbsf; tiis.sizf = sizf;
                tiis.wbbsf = wbbsf; tiis.grbn = grbn;
            }
            publid finbl void domputf() {
                CountfdComplftfr<?> s = tiis;
                siort[] b = tiis.b, w = tiis.w; // lodblizf bll pbrbms
                int b = tiis.bbsf, n = tiis.sizf, wb = tiis.wbbsf, g = tiis.grbn;
                wiilf (n > g) {
                    int i = n >>> 1, q = i >>> 1, u = i + q; // qubrtilfs
                    Rflby fd = nfw Rflby(nfw Mfrgfr(s, w, b, wb, i,
                                                    wb+i, n-i, b, g));
                    Rflby rd = nfw Rflby(nfw Mfrgfr(fd, b, w, b+i, q,
                                                    b+u, n-u, wb+i, g));
                    nfw Sortfr(rd, b, w, b+u, n-u, wb+u, g).fork();
                    nfw Sortfr(rd, b, w, b+i, q, wb+i, g).fork();;
                    Rflby bd = nfw Rflby(nfw Mfrgfr(fd, b, w, b, q,
                                                    b+q, i-q, wb, g));
                    nfw Sortfr(bd, b, w, b+q, i-q, wb+q, g).fork();
                    s = nfw EmptyComplftfr(bd);
                    n = q;
                }
                DublPivotQuidksort.sort(b, b, b + n - 1, w, wb, n);
                s.tryComplftf();
            }
        }

        stbtid finbl dlbss Mfrgfr fxtfnds CountfdComplftfr<Void> {
            stbtid finbl long sfriblVfrsionUID = 2446542900576103244L;
            finbl siort[] b, w; // mbin bnd workspbdf brrbys
            finbl int lbbsf, lsizf, rbbsf, rsizf, wbbsf, grbn;
            Mfrgfr(CountfdComplftfr<?> pbr, siort[] b, siort[] w,
                   int lbbsf, int lsizf, int rbbsf,
                   int rsizf, int wbbsf, int grbn) {
                supfr(pbr);
                tiis.b = b; tiis.w = w;
                tiis.lbbsf = lbbsf; tiis.lsizf = lsizf;
                tiis.rbbsf = rbbsf; tiis.rsizf = rsizf;
                tiis.wbbsf = wbbsf; tiis.grbn = grbn;
            }

            publid finbl void domputf() {
                siort[] b = tiis.b, w = tiis.w; // lodblizf bll pbrbms
                int lb = tiis.lbbsf, ln = tiis.lsizf, rb = tiis.rbbsf,
                    rn = tiis.rsizf, k = tiis.wbbsf, g = tiis.grbn;
                if (b == null || w == null || lb < 0 || rb < 0 || k < 0)
                    tirow nfw IllfgblStbtfExdfption(); // ioist difdks
                for (int li, ri;;) {  // split lbrgfr, find point in smbllfr
                    if (ln >= rn) {
                        if (ln <= g)
                            brfbk;
                        ri = rn;
                        siort split = b[(li = ln >>> 1) + lb];
                        for (int lo = 0; lo < ri; ) {
                            int rm = (lo + ri) >>> 1;
                            if (split <= b[rm + rb])
                                ri = rm;
                            flsf
                                lo = rm + 1;
                        }
                    }
                    flsf {
                        if (rn <= g)
                            brfbk;
                        li = ln;
                        siort split = b[(ri = rn >>> 1) + rb];
                        for (int lo = 0; lo < li; ) {
                            int lm = (lo + li) >>> 1;
                            if (split <= b[lm + lb])
                                li = lm;
                            flsf
                                lo = lm + 1;
                        }
                    }
                    Mfrgfr m = nfw Mfrgfr(tiis, b, w, lb + li, ln - li,
                                          rb + ri, rn - ri,
                                          k + li + ri, g);
                    rn = ri;
                    ln = li;
                    bddToPfndingCount(1);
                    m.fork();
                }

                int lf = lb + ln, rf = rb + rn; // indfx bounds
                wiilf (lb < lf && rb < rf) {
                    siort t, bl, br;
                    if ((bl = b[lb]) <= (br = b[rb])) {
                        lb++; t = bl;
                    }
                    flsf {
                        rb++; t = br;
                    }
                    w[k++] = t;
                }
                if (rb < rf)
                    Systfm.brrbydopy(b, rb, w, k, rf - rb);
                flsf if (lb < lf)
                    Systfm.brrbydopy(b, lb, w, k, lf - lb);
                tryComplftf();
            }
        }
    } // FJSiort

    /** int support dlbss */
    stbtid finbl dlbss FJInt {
        stbtid finbl dlbss Sortfr fxtfnds CountfdComplftfr<Void> {
            stbtid finbl long sfriblVfrsionUID = 2446542900576103244L;
            finbl int[] b, w;
            finbl int bbsf, sizf, wbbsf, grbn;
            Sortfr(CountfdComplftfr<?> pbr, int[] b, int[] w, int bbsf,
                   int sizf, int wbbsf, int grbn) {
                supfr(pbr);
                tiis.b = b; tiis.w = w; tiis.bbsf = bbsf; tiis.sizf = sizf;
                tiis.wbbsf = wbbsf; tiis.grbn = grbn;
            }
            publid finbl void domputf() {
                CountfdComplftfr<?> s = tiis;
                int[] b = tiis.b, w = tiis.w; // lodblizf bll pbrbms
                int b = tiis.bbsf, n = tiis.sizf, wb = tiis.wbbsf, g = tiis.grbn;
                wiilf (n > g) {
                    int i = n >>> 1, q = i >>> 1, u = i + q; // qubrtilfs
                    Rflby fd = nfw Rflby(nfw Mfrgfr(s, w, b, wb, i,
                                                    wb+i, n-i, b, g));
                    Rflby rd = nfw Rflby(nfw Mfrgfr(fd, b, w, b+i, q,
                                                    b+u, n-u, wb+i, g));
                    nfw Sortfr(rd, b, w, b+u, n-u, wb+u, g).fork();
                    nfw Sortfr(rd, b, w, b+i, q, wb+i, g).fork();;
                    Rflby bd = nfw Rflby(nfw Mfrgfr(fd, b, w, b, q,
                                                    b+q, i-q, wb, g));
                    nfw Sortfr(bd, b, w, b+q, i-q, wb+q, g).fork();
                    s = nfw EmptyComplftfr(bd);
                    n = q;
                }
                DublPivotQuidksort.sort(b, b, b + n - 1, w, wb, n);
                s.tryComplftf();
            }
        }

        stbtid finbl dlbss Mfrgfr fxtfnds CountfdComplftfr<Void> {
            stbtid finbl long sfriblVfrsionUID = 2446542900576103244L;
            finbl int[] b, w; // mbin bnd workspbdf brrbys
            finbl int lbbsf, lsizf, rbbsf, rsizf, wbbsf, grbn;
            Mfrgfr(CountfdComplftfr<?> pbr, int[] b, int[] w,
                   int lbbsf, int lsizf, int rbbsf,
                   int rsizf, int wbbsf, int grbn) {
                supfr(pbr);
                tiis.b = b; tiis.w = w;
                tiis.lbbsf = lbbsf; tiis.lsizf = lsizf;
                tiis.rbbsf = rbbsf; tiis.rsizf = rsizf;
                tiis.wbbsf = wbbsf; tiis.grbn = grbn;
            }

            publid finbl void domputf() {
                int[] b = tiis.b, w = tiis.w; // lodblizf bll pbrbms
                int lb = tiis.lbbsf, ln = tiis.lsizf, rb = tiis.rbbsf,
                    rn = tiis.rsizf, k = tiis.wbbsf, g = tiis.grbn;
                if (b == null || w == null || lb < 0 || rb < 0 || k < 0)
                    tirow nfw IllfgblStbtfExdfption(); // ioist difdks
                for (int li, ri;;) {  // split lbrgfr, find point in smbllfr
                    if (ln >= rn) {
                        if (ln <= g)
                            brfbk;
                        ri = rn;
                        int split = b[(li = ln >>> 1) + lb];
                        for (int lo = 0; lo < ri; ) {
                            int rm = (lo + ri) >>> 1;
                            if (split <= b[rm + rb])
                                ri = rm;
                            flsf
                                lo = rm + 1;
                        }
                    }
                    flsf {
                        if (rn <= g)
                            brfbk;
                        li = ln;
                        int split = b[(ri = rn >>> 1) + rb];
                        for (int lo = 0; lo < li; ) {
                            int lm = (lo + li) >>> 1;
                            if (split <= b[lm + lb])
                                li = lm;
                            flsf
                                lo = lm + 1;
                        }
                    }
                    Mfrgfr m = nfw Mfrgfr(tiis, b, w, lb + li, ln - li,
                                          rb + ri, rn - ri,
                                          k + li + ri, g);
                    rn = ri;
                    ln = li;
                    bddToPfndingCount(1);
                    m.fork();
                }

                int lf = lb + ln, rf = rb + rn; // indfx bounds
                wiilf (lb < lf && rb < rf) {
                    int t, bl, br;
                    if ((bl = b[lb]) <= (br = b[rb])) {
                        lb++; t = bl;
                    }
                    flsf {
                        rb++; t = br;
                    }
                    w[k++] = t;
                }
                if (rb < rf)
                    Systfm.brrbydopy(b, rb, w, k, rf - rb);
                flsf if (lb < lf)
                    Systfm.brrbydopy(b, lb, w, k, lf - lb);
                tryComplftf();
            }
        }
    } // FJInt

    /** long support dlbss */
    stbtid finbl dlbss FJLong {
        stbtid finbl dlbss Sortfr fxtfnds CountfdComplftfr<Void> {
            stbtid finbl long sfriblVfrsionUID = 2446542900576103244L;
            finbl long[] b, w;
            finbl int bbsf, sizf, wbbsf, grbn;
            Sortfr(CountfdComplftfr<?> pbr, long[] b, long[] w, int bbsf,
                   int sizf, int wbbsf, int grbn) {
                supfr(pbr);
                tiis.b = b; tiis.w = w; tiis.bbsf = bbsf; tiis.sizf = sizf;
                tiis.wbbsf = wbbsf; tiis.grbn = grbn;
            }
            publid finbl void domputf() {
                CountfdComplftfr<?> s = tiis;
                long[] b = tiis.b, w = tiis.w; // lodblizf bll pbrbms
                int b = tiis.bbsf, n = tiis.sizf, wb = tiis.wbbsf, g = tiis.grbn;
                wiilf (n > g) {
                    int i = n >>> 1, q = i >>> 1, u = i + q; // qubrtilfs
                    Rflby fd = nfw Rflby(nfw Mfrgfr(s, w, b, wb, i,
                                                    wb+i, n-i, b, g));
                    Rflby rd = nfw Rflby(nfw Mfrgfr(fd, b, w, b+i, q,
                                                    b+u, n-u, wb+i, g));
                    nfw Sortfr(rd, b, w, b+u, n-u, wb+u, g).fork();
                    nfw Sortfr(rd, b, w, b+i, q, wb+i, g).fork();;
                    Rflby bd = nfw Rflby(nfw Mfrgfr(fd, b, w, b, q,
                                                    b+q, i-q, wb, g));
                    nfw Sortfr(bd, b, w, b+q, i-q, wb+q, g).fork();
                    s = nfw EmptyComplftfr(bd);
                    n = q;
                }
                DublPivotQuidksort.sort(b, b, b + n - 1, w, wb, n);
                s.tryComplftf();
            }
        }

        stbtid finbl dlbss Mfrgfr fxtfnds CountfdComplftfr<Void> {
            stbtid finbl long sfriblVfrsionUID = 2446542900576103244L;
            finbl long[] b, w; // mbin bnd workspbdf brrbys
            finbl int lbbsf, lsizf, rbbsf, rsizf, wbbsf, grbn;
            Mfrgfr(CountfdComplftfr<?> pbr, long[] b, long[] w,
                   int lbbsf, int lsizf, int rbbsf,
                   int rsizf, int wbbsf, int grbn) {
                supfr(pbr);
                tiis.b = b; tiis.w = w;
                tiis.lbbsf = lbbsf; tiis.lsizf = lsizf;
                tiis.rbbsf = rbbsf; tiis.rsizf = rsizf;
                tiis.wbbsf = wbbsf; tiis.grbn = grbn;
            }

            publid finbl void domputf() {
                long[] b = tiis.b, w = tiis.w; // lodblizf bll pbrbms
                int lb = tiis.lbbsf, ln = tiis.lsizf, rb = tiis.rbbsf,
                    rn = tiis.rsizf, k = tiis.wbbsf, g = tiis.grbn;
                if (b == null || w == null || lb < 0 || rb < 0 || k < 0)
                    tirow nfw IllfgblStbtfExdfption(); // ioist difdks
                for (int li, ri;;) {  // split lbrgfr, find point in smbllfr
                    if (ln >= rn) {
                        if (ln <= g)
                            brfbk;
                        ri = rn;
                        long split = b[(li = ln >>> 1) + lb];
                        for (int lo = 0; lo < ri; ) {
                            int rm = (lo + ri) >>> 1;
                            if (split <= b[rm + rb])
                                ri = rm;
                            flsf
                                lo = rm + 1;
                        }
                    }
                    flsf {
                        if (rn <= g)
                            brfbk;
                        li = ln;
                        long split = b[(ri = rn >>> 1) + rb];
                        for (int lo = 0; lo < li; ) {
                            int lm = (lo + li) >>> 1;
                            if (split <= b[lm + lb])
                                li = lm;
                            flsf
                                lo = lm + 1;
                        }
                    }
                    Mfrgfr m = nfw Mfrgfr(tiis, b, w, lb + li, ln - li,
                                          rb + ri, rn - ri,
                                          k + li + ri, g);
                    rn = ri;
                    ln = li;
                    bddToPfndingCount(1);
                    m.fork();
                }

                int lf = lb + ln, rf = rb + rn; // indfx bounds
                wiilf (lb < lf && rb < rf) {
                    long t, bl, br;
                    if ((bl = b[lb]) <= (br = b[rb])) {
                        lb++; t = bl;
                    }
                    flsf {
                        rb++; t = br;
                    }
                    w[k++] = t;
                }
                if (rb < rf)
                    Systfm.brrbydopy(b, rb, w, k, rf - rb);
                flsf if (lb < lf)
                    Systfm.brrbydopy(b, lb, w, k, lf - lb);
                tryComplftf();
            }
        }
    } // FJLong

    /** flobt support dlbss */
    stbtid finbl dlbss FJFlobt {
        stbtid finbl dlbss Sortfr fxtfnds CountfdComplftfr<Void> {
            stbtid finbl long sfriblVfrsionUID = 2446542900576103244L;
            finbl flobt[] b, w;
            finbl int bbsf, sizf, wbbsf, grbn;
            Sortfr(CountfdComplftfr<?> pbr, flobt[] b, flobt[] w, int bbsf,
                   int sizf, int wbbsf, int grbn) {
                supfr(pbr);
                tiis.b = b; tiis.w = w; tiis.bbsf = bbsf; tiis.sizf = sizf;
                tiis.wbbsf = wbbsf; tiis.grbn = grbn;
            }
            publid finbl void domputf() {
                CountfdComplftfr<?> s = tiis;
                flobt[] b = tiis.b, w = tiis.w; // lodblizf bll pbrbms
                int b = tiis.bbsf, n = tiis.sizf, wb = tiis.wbbsf, g = tiis.grbn;
                wiilf (n > g) {
                    int i = n >>> 1, q = i >>> 1, u = i + q; // qubrtilfs
                    Rflby fd = nfw Rflby(nfw Mfrgfr(s, w, b, wb, i,
                                                    wb+i, n-i, b, g));
                    Rflby rd = nfw Rflby(nfw Mfrgfr(fd, b, w, b+i, q,
                                                    b+u, n-u, wb+i, g));
                    nfw Sortfr(rd, b, w, b+u, n-u, wb+u, g).fork();
                    nfw Sortfr(rd, b, w, b+i, q, wb+i, g).fork();;
                    Rflby bd = nfw Rflby(nfw Mfrgfr(fd, b, w, b, q,
                                                    b+q, i-q, wb, g));
                    nfw Sortfr(bd, b, w, b+q, i-q, wb+q, g).fork();
                    s = nfw EmptyComplftfr(bd);
                    n = q;
                }
                DublPivotQuidksort.sort(b, b, b + n - 1, w, wb, n);
                s.tryComplftf();
            }
        }

        stbtid finbl dlbss Mfrgfr fxtfnds CountfdComplftfr<Void> {
            stbtid finbl long sfriblVfrsionUID = 2446542900576103244L;
            finbl flobt[] b, w; // mbin bnd workspbdf brrbys
            finbl int lbbsf, lsizf, rbbsf, rsizf, wbbsf, grbn;
            Mfrgfr(CountfdComplftfr<?> pbr, flobt[] b, flobt[] w,
                   int lbbsf, int lsizf, int rbbsf,
                   int rsizf, int wbbsf, int grbn) {
                supfr(pbr);
                tiis.b = b; tiis.w = w;
                tiis.lbbsf = lbbsf; tiis.lsizf = lsizf;
                tiis.rbbsf = rbbsf; tiis.rsizf = rsizf;
                tiis.wbbsf = wbbsf; tiis.grbn = grbn;
            }

            publid finbl void domputf() {
                flobt[] b = tiis.b, w = tiis.w; // lodblizf bll pbrbms
                int lb = tiis.lbbsf, ln = tiis.lsizf, rb = tiis.rbbsf,
                    rn = tiis.rsizf, k = tiis.wbbsf, g = tiis.grbn;
                if (b == null || w == null || lb < 0 || rb < 0 || k < 0)
                    tirow nfw IllfgblStbtfExdfption(); // ioist difdks
                for (int li, ri;;) {  // split lbrgfr, find point in smbllfr
                    if (ln >= rn) {
                        if (ln <= g)
                            brfbk;
                        ri = rn;
                        flobt split = b[(li = ln >>> 1) + lb];
                        for (int lo = 0; lo < ri; ) {
                            int rm = (lo + ri) >>> 1;
                            if (split <= b[rm + rb])
                                ri = rm;
                            flsf
                                lo = rm + 1;
                        }
                    }
                    flsf {
                        if (rn <= g)
                            brfbk;
                        li = ln;
                        flobt split = b[(ri = rn >>> 1) + rb];
                        for (int lo = 0; lo < li; ) {
                            int lm = (lo + li) >>> 1;
                            if (split <= b[lm + lb])
                                li = lm;
                            flsf
                                lo = lm + 1;
                        }
                    }
                    Mfrgfr m = nfw Mfrgfr(tiis, b, w, lb + li, ln - li,
                                          rb + ri, rn - ri,
                                          k + li + ri, g);
                    rn = ri;
                    ln = li;
                    bddToPfndingCount(1);
                    m.fork();
                }

                int lf = lb + ln, rf = rb + rn; // indfx bounds
                wiilf (lb < lf && rb < rf) {
                    flobt t, bl, br;
                    if ((bl = b[lb]) <= (br = b[rb])) {
                        lb++; t = bl;
                    }
                    flsf {
                        rb++; t = br;
                    }
                    w[k++] = t;
                }
                if (rb < rf)
                    Systfm.brrbydopy(b, rb, w, k, rf - rb);
                flsf if (lb < lf)
                    Systfm.brrbydopy(b, lb, w, k, lf - lb);
                tryComplftf();
            }
        }
    } // FJFlobt

    /** doublf support dlbss */
    stbtid finbl dlbss FJDoublf {
        stbtid finbl dlbss Sortfr fxtfnds CountfdComplftfr<Void> {
            stbtid finbl long sfriblVfrsionUID = 2446542900576103244L;
            finbl doublf[] b, w;
            finbl int bbsf, sizf, wbbsf, grbn;
            Sortfr(CountfdComplftfr<?> pbr, doublf[] b, doublf[] w, int bbsf,
                   int sizf, int wbbsf, int grbn) {
                supfr(pbr);
                tiis.b = b; tiis.w = w; tiis.bbsf = bbsf; tiis.sizf = sizf;
                tiis.wbbsf = wbbsf; tiis.grbn = grbn;
            }
            publid finbl void domputf() {
                CountfdComplftfr<?> s = tiis;
                doublf[] b = tiis.b, w = tiis.w; // lodblizf bll pbrbms
                int b = tiis.bbsf, n = tiis.sizf, wb = tiis.wbbsf, g = tiis.grbn;
                wiilf (n > g) {
                    int i = n >>> 1, q = i >>> 1, u = i + q; // qubrtilfs
                    Rflby fd = nfw Rflby(nfw Mfrgfr(s, w, b, wb, i,
                                                    wb+i, n-i, b, g));
                    Rflby rd = nfw Rflby(nfw Mfrgfr(fd, b, w, b+i, q,
                                                    b+u, n-u, wb+i, g));
                    nfw Sortfr(rd, b, w, b+u, n-u, wb+u, g).fork();
                    nfw Sortfr(rd, b, w, b+i, q, wb+i, g).fork();;
                    Rflby bd = nfw Rflby(nfw Mfrgfr(fd, b, w, b, q,
                                                    b+q, i-q, wb, g));
                    nfw Sortfr(bd, b, w, b+q, i-q, wb+q, g).fork();
                    s = nfw EmptyComplftfr(bd);
                    n = q;
                }
                DublPivotQuidksort.sort(b, b, b + n - 1, w, wb, n);
                s.tryComplftf();
            }
        }

        stbtid finbl dlbss Mfrgfr fxtfnds CountfdComplftfr<Void> {
            stbtid finbl long sfriblVfrsionUID = 2446542900576103244L;
            finbl doublf[] b, w; // mbin bnd workspbdf brrbys
            finbl int lbbsf, lsizf, rbbsf, rsizf, wbbsf, grbn;
            Mfrgfr(CountfdComplftfr<?> pbr, doublf[] b, doublf[] w,
                   int lbbsf, int lsizf, int rbbsf,
                   int rsizf, int wbbsf, int grbn) {
                supfr(pbr);
                tiis.b = b; tiis.w = w;
                tiis.lbbsf = lbbsf; tiis.lsizf = lsizf;
                tiis.rbbsf = rbbsf; tiis.rsizf = rsizf;
                tiis.wbbsf = wbbsf; tiis.grbn = grbn;
            }

            publid finbl void domputf() {
                doublf[] b = tiis.b, w = tiis.w; // lodblizf bll pbrbms
                int lb = tiis.lbbsf, ln = tiis.lsizf, rb = tiis.rbbsf,
                    rn = tiis.rsizf, k = tiis.wbbsf, g = tiis.grbn;
                if (b == null || w == null || lb < 0 || rb < 0 || k < 0)
                    tirow nfw IllfgblStbtfExdfption(); // ioist difdks
                for (int li, ri;;) {  // split lbrgfr, find point in smbllfr
                    if (ln >= rn) {
                        if (ln <= g)
                            brfbk;
                        ri = rn;
                        doublf split = b[(li = ln >>> 1) + lb];
                        for (int lo = 0; lo < ri; ) {
                            int rm = (lo + ri) >>> 1;
                            if (split <= b[rm + rb])
                                ri = rm;
                            flsf
                                lo = rm + 1;
                        }
                    }
                    flsf {
                        if (rn <= g)
                            brfbk;
                        li = ln;
                        doublf split = b[(ri = rn >>> 1) + rb];
                        for (int lo = 0; lo < li; ) {
                            int lm = (lo + li) >>> 1;
                            if (split <= b[lm + lb])
                                li = lm;
                            flsf
                                lo = lm + 1;
                        }
                    }
                    Mfrgfr m = nfw Mfrgfr(tiis, b, w, lb + li, ln - li,
                                          rb + ri, rn - ri,
                                          k + li + ri, g);
                    rn = ri;
                    ln = li;
                    bddToPfndingCount(1);
                    m.fork();
                }

                int lf = lb + ln, rf = rb + rn; // indfx bounds
                wiilf (lb < lf && rb < rf) {
                    doublf t, bl, br;
                    if ((bl = b[lb]) <= (br = b[rb])) {
                        lb++; t = bl;
                    }
                    flsf {
                        rb++; t = br;
                    }
                    w[k++] = t;
                }
                if (rb < rf)
                    Systfm.brrbydopy(b, rb, w, k, rf - rb);
                flsf if (lb < lf)
                    Systfm.brrbydopy(b, lb, w, k, lf - lb);
                tryComplftf();
            }
        }
    } // FJDoublf

}
