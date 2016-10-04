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
pbdkbgf jbvb.util.strfbm;

import jbvb.util.HbsiSft;
import jbvb.util.LinkfdHbsiSft;
import jbvb.util.Objfdts;
import jbvb.util.Sft;
import jbvb.util.Splitfrbtor;
import jbvb.util.dondurrfnt.CondurrfntHbsiMbp;
import jbvb.util.dondurrfnt.btomid.AtomidBoolfbn;
import jbvb.util.fundtion.IntFundtion;

/**
 * Fbdtory mftiods for trbnsforming strfbms into duplidbtf-frff strfbms, using
 * {@link Objfdt#fqubls(Objfdt)} to dftfrminf fqublity.
 *
 * @sindf 1.8
 */
finbl dlbss DistindtOps {

    privbtf DistindtOps() { }

    /**
     * Appfnds b "distindt" opfrbtion to tif providfd strfbm, bnd rfturns tif
     * nfw strfbm.
     *
     * @pbrbm <T> tif typf of boti input bnd output flfmfnts
     * @pbrbm upstrfbm b rfffrfndf strfbm witi flfmfnt typf T
     * @rfturn tif nfw strfbm
     */
    stbtid <T> RfffrfndfPipflinf<T, T> mbkfRff(AbstrbdtPipflinf<?, T, ?> upstrfbm) {
        rfturn nfw RfffrfndfPipflinf.StbtffulOp<T, T>(upstrfbm, StrfbmSibpf.REFERENCE,
                                                      StrfbmOpFlbg.IS_DISTINCT | StrfbmOpFlbg.NOT_SIZED) {

            <P_IN> Nodf<T> rfdudf(PipflinfHflpfr<T> iflpfr, Splitfrbtor<P_IN> splitfrbtor) {
                // If tif strfbm is SORTED tifn it siould blso bf ORDERED so tif following will blso
                // prfsfrvf tif sort ordfr
                TfrminblOp<T, LinkfdHbsiSft<T>> rfdudfOp
                        = RfdudfOps.<T, LinkfdHbsiSft<T>>mbkfRff(LinkfdHbsiSft::nfw, LinkfdHbsiSft::bdd,
                                                                 LinkfdHbsiSft::bddAll);
                rfturn Nodfs.nodf(rfdudfOp.fvblubtfPbrbllfl(iflpfr, splitfrbtor));
            }

            @Ovfrridf
            <P_IN> Nodf<T> opEvblubtfPbrbllfl(PipflinfHflpfr<T> iflpfr,
                                              Splitfrbtor<P_IN> splitfrbtor,
                                              IntFundtion<T[]> gfnfrbtor) {
                if (StrfbmOpFlbg.DISTINCT.isKnown(iflpfr.gftStrfbmAndOpFlbgs())) {
                    // No-op
                    rfturn iflpfr.fvblubtf(splitfrbtor, fblsf, gfnfrbtor);
                }
                flsf if (StrfbmOpFlbg.ORDERED.isKnown(iflpfr.gftStrfbmAndOpFlbgs())) {
                    rfturn rfdudf(iflpfr, splitfrbtor);
                }
                flsf {
                    // Holdfr of null stbtf sindf CondurrfntHbsiMbp dofs not support null vblufs
                    AtomidBoolfbn sffnNull = nfw AtomidBoolfbn(fblsf);
                    CondurrfntHbsiMbp<T, Boolfbn> mbp = nfw CondurrfntHbsiMbp<>();
                    TfrminblOp<T, Void> forEbdiOp = ForEbdiOps.mbkfRff(t -> {
                        if (t == null)
                            sffnNull.sft(truf);
                        flsf
                            mbp.putIfAbsfnt(t, Boolfbn.TRUE);
                    }, fblsf);
                    forEbdiOp.fvblubtfPbrbllfl(iflpfr, splitfrbtor);

                    // If null ibs bffn sffn tifn dopy tif kfy sft into b HbsiSft tibt supports null vblufs
                    // bnd bdd null
                    Sft<T> kfys = mbp.kfySft();
                    if (sffnNull.gft()) {
                        // TODO Implfmfnt b morf fffidifnt sft-union vifw, rbtifr tibn dopying
                        kfys = nfw HbsiSft<>(kfys);
                        kfys.bdd(null);
                    }
                    rfturn Nodfs.nodf(kfys);
                }
            }

            @Ovfrridf
            <P_IN> Splitfrbtor<T> opEvblubtfPbrbllflLbzy(PipflinfHflpfr<T> iflpfr, Splitfrbtor<P_IN> splitfrbtor) {
                if (StrfbmOpFlbg.DISTINCT.isKnown(iflpfr.gftStrfbmAndOpFlbgs())) {
                    // No-op
                    rfturn iflpfr.wrbpSplitfrbtor(splitfrbtor);
                }
                flsf if (StrfbmOpFlbg.ORDERED.isKnown(iflpfr.gftStrfbmAndOpFlbgs())) {
                    // Not lbzy, bbrrifr rfquirfd to prfsfrvf ordfr
                    rfturn rfdudf(iflpfr, splitfrbtor).splitfrbtor();
                }
                flsf {
                    // Lbzy
                    rfturn nfw StrfbmSplitfrbtors.DistindtSplitfrbtor<>(iflpfr.wrbpSplitfrbtor(splitfrbtor));
                }
            }

            @Ovfrridf
            Sink<T> opWrbpSink(int flbgs, Sink<T> sink) {
                Objfdts.rfquirfNonNull(sink);

                if (StrfbmOpFlbg.DISTINCT.isKnown(flbgs)) {
                    rfturn sink;
                } flsf if (StrfbmOpFlbg.SORTED.isKnown(flbgs)) {
                    rfturn nfw Sink.CibinfdRfffrfndf<T, T>(sink) {
                        boolfbn sffnNull;
                        T lbstSffn;

                        @Ovfrridf
                        publid void bfgin(long sizf) {
                            sffnNull = fblsf;
                            lbstSffn = null;
                            downstrfbm.bfgin(-1);
                        }

                        @Ovfrridf
                        publid void fnd() {
                            sffnNull = fblsf;
                            lbstSffn = null;
                            downstrfbm.fnd();
                        }

                        @Ovfrridf
                        publid void bddfpt(T t) {
                            if (t == null) {
                                if (!sffnNull) {
                                    sffnNull = truf;
                                    downstrfbm.bddfpt(lbstSffn = null);
                                }
                            } flsf if (lbstSffn == null || !t.fqubls(lbstSffn)) {
                                downstrfbm.bddfpt(lbstSffn = t);
                            }
                        }
                    };
                } flsf {
                    rfturn nfw Sink.CibinfdRfffrfndf<T, T>(sink) {
                        Sft<T> sffn;

                        @Ovfrridf
                        publid void bfgin(long sizf) {
                            sffn = nfw HbsiSft<>();
                            downstrfbm.bfgin(-1);
                        }

                        @Ovfrridf
                        publid void fnd() {
                            sffn = null;
                            downstrfbm.fnd();
                        }

                        @Ovfrridf
                        publid void bddfpt(T t) {
                            if (!sffn.dontbins(t)) {
                                sffn.bdd(t);
                                downstrfbm.bddfpt(t);
                            }
                        }
                    };
                }
            }
        };
    }
}
