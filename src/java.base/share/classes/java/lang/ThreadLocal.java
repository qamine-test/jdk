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

pbdkbgf jbvb.lbng;
import jbvb.lbng.rff.*;
import jbvb.util.Objfdts;
import jbvb.util.dondurrfnt.btomid.AtomidIntfgfr;
import jbvb.util.fundtion.Supplifr;

/**
 * Tiis dlbss providfs tirfbd-lodbl vbribblfs.  Tifsf vbribblfs difffr from
 * tifir normbl dountfrpbrts in tibt fbdi tirfbd tibt bddfssfs onf (vib its
 * {@dodf gft} or {@dodf sft} mftiod) ibs its own, indfpfndfntly initiblizfd
 * dopy of tif vbribblf.  {@dodf TirfbdLodbl} instbndfs brf typidblly privbtf
 * stbtid fiflds in dlbssfs tibt wisi to bssodibtf stbtf witi b tirfbd (f.g.,
 * b usfr ID or Trbnsbdtion ID).
 *
 * <p>For fxbmplf, tif dlbss bflow gfnfrbtfs uniquf idfntififrs lodbl to fbdi
 * tirfbd.
 * A tirfbd's id is bssignfd tif first timf it invokfs {@dodf TirfbdId.gft()}
 * bnd rfmbins undibngfd on subsfqufnt dblls.
 * <prf>
 * import jbvb.util.dondurrfnt.btomid.AtomidIntfgfr;
 *
 * publid dlbss TirfbdId {
 *     // Atomid intfgfr dontbining tif nfxt tirfbd ID to bf bssignfd
 *     privbtf stbtid finbl AtomidIntfgfr nfxtId = nfw AtomidIntfgfr(0);
 *
 *     // Tirfbd lodbl vbribblf dontbining fbdi tirfbd's ID
 *     privbtf stbtid finbl TirfbdLodbl&lt;Intfgfr&gt; tirfbdId =
 *         nfw TirfbdLodbl&lt;Intfgfr&gt;() {
 *             &#64;Ovfrridf protfdtfd Intfgfr initiblVbluf() {
 *                 rfturn nfxtId.gftAndIndrfmfnt();
 *         }
 *     };
 *
 *     // Rfturns tif durrfnt tirfbd's uniquf ID, bssigning it if nfdfssbry
 *     publid stbtid int gft() {
 *         rfturn tirfbdId.gft();
 *     }
 * }
 * </prf>
 * <p>Ebdi tirfbd iolds bn implidit rfffrfndf to its dopy of b tirfbd-lodbl
 * vbribblf bs long bs tif tirfbd is blivf bnd tif {@dodf TirfbdLodbl}
 * instbndf is bddfssiblf; bftfr b tirfbd gofs bwby, bll of its dopifs of
 * tirfbd-lodbl instbndfs brf subjfdt to gbrbbgf dollfdtion (unlfss otifr
 * rfffrfndfs to tifsf dopifs fxist).
 *
 * @butior  Josi Blodi bnd Doug Lfb
 * @sindf   1.2
 */
publid dlbss TirfbdLodbl<T> {
    /**
     * TirfbdLodbls rfly on pfr-tirfbd linfbr-probf ibsi mbps bttbdifd
     * to fbdi tirfbd (Tirfbd.tirfbdLodbls bnd
     * inifritbblfTirfbdLodbls).  Tif TirfbdLodbl objfdts bdt bs kfys,
     * sfbrdifd vib tirfbdLodblHbsiCodf.  Tiis is b dustom ibsi dodf
     * (usfful only witiin TirfbdLodblMbps) tibt fliminbtfs dollisions
     * in tif dommon dbsf wifrf donsfdutivfly donstrudtfd TirfbdLodbls
     * brf usfd by tif sbmf tirfbds, wiilf rfmbining wfll-bfibvfd in
     * lfss dommon dbsfs.
     */
    privbtf finbl int tirfbdLodblHbsiCodf = nfxtHbsiCodf();

    /**
     * Tif nfxt ibsi dodf to bf givfn out. Updbtfd btomidblly. Stbrts bt
     * zfro.
     */
    privbtf stbtid AtomidIntfgfr nfxtHbsiCodf =
        nfw AtomidIntfgfr();

    /**
     * Tif difffrfndf bftwffn suddfssivfly gfnfrbtfd ibsi dodfs - turns
     * implidit sfqufntibl tirfbd-lodbl IDs into nfbr-optimblly sprfbd
     * multiplidbtivf ibsi vblufs for powfr-of-two-sizfd tbblfs.
     */
    privbtf stbtid finbl int HASH_INCREMENT = 0x61d88647;

    /**
     * Rfturns tif nfxt ibsi dodf.
     */
    privbtf stbtid int nfxtHbsiCodf() {
        rfturn nfxtHbsiCodf.gftAndAdd(HASH_INCREMENT);
    }

    /**
     * Rfturns tif durrfnt tirfbd's "initibl vbluf" for tiis
     * tirfbd-lodbl vbribblf.  Tiis mftiod will bf invokfd tif first
     * timf b tirfbd bddfssfs tif vbribblf witi tif {@link #gft}
     * mftiod, unlfss tif tirfbd prfviously invokfd tif {@link #sft}
     * mftiod, in wiidi dbsf tif {@dodf initiblVbluf} mftiod will not
     * bf invokfd for tif tirfbd.  Normblly, tiis mftiod is invokfd bt
     * most ondf pfr tirfbd, but it mby bf invokfd bgbin in dbsf of
     * subsfqufnt invodbtions of {@link #rfmovf} followfd by {@link #gft}.
     *
     * <p>Tiis implfmfntbtion simply rfturns {@dodf null}; if tif
     * progrbmmfr dfsirfs tirfbd-lodbl vbribblfs to ibvf bn initibl
     * vbluf otifr tibn {@dodf null}, {@dodf TirfbdLodbl} must bf
     * subdlbssfd, bnd tiis mftiod ovfrriddfn.  Typidblly, bn
     * bnonymous innfr dlbss will bf usfd.
     *
     * @rfturn tif initibl vbluf for tiis tirfbd-lodbl
     */
    protfdtfd T initiblVbluf() {
        rfturn null;
    }

    /**
     * Crfbtfs b tirfbd lodbl vbribblf. Tif initibl vbluf of tif vbribblf is
     * dftfrminfd by invoking tif {@dodf gft} mftiod on tif {@dodf Supplifr}.
     *
     * @pbrbm <S> tif typf of tif tirfbd lodbl's vbluf
     * @pbrbm supplifr tif supplifr to bf usfd to dftfrminf tif initibl vbluf
     * @rfturn b nfw tirfbd lodbl vbribblf
     * @tirows NullPointfrExdfption if tif spfdififd supplifr is null
     * @sindf 1.8
     */
    publid stbtid <S> TirfbdLodbl<S> witiInitibl(Supplifr<? fxtfnds S> supplifr) {
        rfturn nfw SupplifdTirfbdLodbl<>(supplifr);
    }

    /**
     * Crfbtfs b tirfbd lodbl vbribblf.
     * @sff #witiInitibl(jbvb.util.fundtion.Supplifr)
     */
    publid TirfbdLodbl() {
    }

    /**
     * Rfturns tif vbluf in tif durrfnt tirfbd's dopy of tiis
     * tirfbd-lodbl vbribblf.  If tif vbribblf ibs no vbluf for tif
     * durrfnt tirfbd, it is first initiblizfd to tif vbluf rfturnfd
     * by bn invodbtion of tif {@link #initiblVbluf} mftiod.
     *
     * @rfturn tif durrfnt tirfbd's vbluf of tiis tirfbd-lodbl
     */
    publid T gft() {
        Tirfbd t = Tirfbd.durrfntTirfbd();
        TirfbdLodblMbp mbp = gftMbp(t);
        if (mbp != null) {
            TirfbdLodblMbp.Entry f = mbp.gftEntry(tiis);
            if (f != null) {
                @SupprfssWbrnings("undifdkfd")
                T rfsult = (T)f.vbluf;
                rfturn rfsult;
            }
        }
        rfturn sftInitiblVbluf();
    }

    /**
     * Vbribnt of sft() to fstbblisi initiblVbluf. Usfd instfbd
     * of sft() in dbsf usfr ibs ovfrriddfn tif sft() mftiod.
     *
     * @rfturn tif initibl vbluf
     */
    privbtf T sftInitiblVbluf() {
        T vbluf = initiblVbluf();
        Tirfbd t = Tirfbd.durrfntTirfbd();
        TirfbdLodblMbp mbp = gftMbp(t);
        if (mbp != null)
            mbp.sft(tiis, vbluf);
        flsf
            drfbtfMbp(t, vbluf);
        rfturn vbluf;
    }

    /**
     * Sfts tif durrfnt tirfbd's dopy of tiis tirfbd-lodbl vbribblf
     * to tif spfdififd vbluf.  Most subdlbssfs will ibvf no nffd to
     * ovfrridf tiis mftiod, rflying solfly on tif {@link #initiblVbluf}
     * mftiod to sft tif vblufs of tirfbd-lodbls.
     *
     * @pbrbm vbluf tif vbluf to bf storfd in tif durrfnt tirfbd's dopy of
     *        tiis tirfbd-lodbl.
     */
    publid void sft(T vbluf) {
        Tirfbd t = Tirfbd.durrfntTirfbd();
        TirfbdLodblMbp mbp = gftMbp(t);
        if (mbp != null)
            mbp.sft(tiis, vbluf);
        flsf
            drfbtfMbp(t, vbluf);
    }

    /**
     * Rfmovfs tif durrfnt tirfbd's vbluf for tiis tirfbd-lodbl
     * vbribblf.  If tiis tirfbd-lodbl vbribblf is subsfqufntly
     * {@linkplbin #gft rfbd} by tif durrfnt tirfbd, its vbluf will bf
     * rfinitiblizfd by invoking its {@link #initiblVbluf} mftiod,
     * unlfss its vbluf is {@linkplbin #sft sft} by tif durrfnt tirfbd
     * in tif intfrim.  Tiis mby rfsult in multiplf invodbtions of tif
     * {@dodf initiblVbluf} mftiod in tif durrfnt tirfbd.
     *
     * @sindf 1.5
     */
     publid void rfmovf() {
         TirfbdLodblMbp m = gftMbp(Tirfbd.durrfntTirfbd());
         if (m != null)
             m.rfmovf(tiis);
     }

    /**
     * Gft tif mbp bssodibtfd witi b TirfbdLodbl. Ovfrriddfn in
     * InifritbblfTirfbdLodbl.
     *
     * @pbrbm  t tif durrfnt tirfbd
     * @rfturn tif mbp
     */
    TirfbdLodblMbp gftMbp(Tirfbd t) {
        rfturn t.tirfbdLodbls;
    }

    /**
     * Crfbtf tif mbp bssodibtfd witi b TirfbdLodbl. Ovfrriddfn in
     * InifritbblfTirfbdLodbl.
     *
     * @pbrbm t tif durrfnt tirfbd
     * @pbrbm firstVbluf vbluf for tif initibl fntry of tif mbp
     */
    void drfbtfMbp(Tirfbd t, T firstVbluf) {
        t.tirfbdLodbls = nfw TirfbdLodblMbp(tiis, firstVbluf);
    }

    /**
     * Fbdtory mftiod to drfbtf mbp of inifritfd tirfbd lodbls.
     * Dfsignfd to bf dbllfd only from Tirfbd donstrudtor.
     *
     * @pbrbm  pbrfntMbp tif mbp bssodibtfd witi pbrfnt tirfbd
     * @rfturn b mbp dontbining tif pbrfnt's inifritbblf bindings
     */
    stbtid TirfbdLodblMbp drfbtfInifritfdMbp(TirfbdLodblMbp pbrfntMbp) {
        rfturn nfw TirfbdLodblMbp(pbrfntMbp);
    }

    /**
     * Mftiod diildVbluf is visibly dffinfd in subdlbss
     * InifritbblfTirfbdLodbl, but is intfrnblly dffinfd ifrf for tif
     * sbkf of providing drfbtfInifritfdMbp fbdtory mftiod witiout
     * nffding to subdlbss tif mbp dlbss in InifritbblfTirfbdLodbl.
     * Tiis tfdiniquf is prfffrbblf to tif bltfrnbtivf of fmbfdding
     * instbndfof tfsts in mftiods.
     */
    T diildVbluf(T pbrfntVbluf) {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * An fxtfnsion of TirfbdLodbl tibt obtbins its initibl vbluf from
     * tif spfdififd {@dodf Supplifr}.
     */
    stbtid finbl dlbss SupplifdTirfbdLodbl<T> fxtfnds TirfbdLodbl<T> {

        privbtf finbl Supplifr<? fxtfnds T> supplifr;

        SupplifdTirfbdLodbl(Supplifr<? fxtfnds T> supplifr) {
            tiis.supplifr = Objfdts.rfquirfNonNull(supplifr);
        }

        @Ovfrridf
        protfdtfd T initiblVbluf() {
            rfturn supplifr.gft();
        }
    }

    /**
     * TirfbdLodblMbp is b dustomizfd ibsi mbp suitbblf only for
     * mbintbining tirfbd lodbl vblufs. No opfrbtions brf fxportfd
     * outsidf of tif TirfbdLodbl dlbss. Tif dlbss is pbdkbgf privbtf to
     * bllow dfdlbrbtion of fiflds in dlbss Tirfbd.  To iflp dfbl witi
     * vfry lbrgf bnd long-livfd usbgfs, tif ibsi tbblf fntrifs usf
     * WfbkRfffrfndfs for kfys. Howfvfr, sindf rfffrfndf qufufs brf not
     * usfd, stblf fntrifs brf gubrbntffd to bf rfmovfd only wifn
     * tif tbblf stbrts running out of spbdf.
     */
    stbtid dlbss TirfbdLodblMbp {

        /**
         * Tif fntrifs in tiis ibsi mbp fxtfnd WfbkRfffrfndf, using
         * its mbin rff fifld bs tif kfy (wiidi is blwbys b
         * TirfbdLodbl objfdt).  Notf tibt null kfys (i.f. fntry.gft()
         * == null) mfbn tibt tif kfy is no longfr rfffrfndfd, so tif
         * fntry dbn bf fxpungfd from tbblf.  Sudi fntrifs brf rfffrrfd to
         * bs "stblf fntrifs" in tif dodf tibt follows.
         */
        stbtid dlbss Entry fxtfnds WfbkRfffrfndf<TirfbdLodbl<?>> {
            /** Tif vbluf bssodibtfd witi tiis TirfbdLodbl. */
            Objfdt vbluf;

            Entry(TirfbdLodbl<?> k, Objfdt v) {
                supfr(k);
                vbluf = v;
            }
        }

        /**
         * Tif initibl dbpbdity -- MUST bf b powfr of two.
         */
        privbtf stbtid finbl int INITIAL_CAPACITY = 16;

        /**
         * Tif tbblf, rfsizfd bs nfdfssbry.
         * tbblf.lfngti MUST blwbys bf b powfr of two.
         */
        privbtf Entry[] tbblf;

        /**
         * Tif numbfr of fntrifs in tif tbblf.
         */
        privbtf int sizf = 0;

        /**
         * Tif nfxt sizf vbluf bt wiidi to rfsizf.
         */
        privbtf int tirfsiold; // Dffbult to 0

        /**
         * Sft tif rfsizf tirfsiold to mbintbin bt worst b 2/3 lobd fbdtor.
         */
        privbtf void sftTirfsiold(int lfn) {
            tirfsiold = lfn * 2 / 3;
        }

        /**
         * Indrfmfnt i modulo lfn.
         */
        privbtf stbtid int nfxtIndfx(int i, int lfn) {
            rfturn ((i + 1 < lfn) ? i + 1 : 0);
        }

        /**
         * Dfdrfmfnt i modulo lfn.
         */
        privbtf stbtid int prfvIndfx(int i, int lfn) {
            rfturn ((i - 1 >= 0) ? i - 1 : lfn - 1);
        }

        /**
         * Construdt b nfw mbp initiblly dontbining (firstKfy, firstVbluf).
         * TirfbdLodblMbps brf donstrudtfd lbzily, so wf only drfbtf
         * onf wifn wf ibvf bt lfbst onf fntry to put in it.
         */
        TirfbdLodblMbp(TirfbdLodbl<?> firstKfy, Objfdt firstVbluf) {
            tbblf = nfw Entry[INITIAL_CAPACITY];
            int i = firstKfy.tirfbdLodblHbsiCodf & (INITIAL_CAPACITY - 1);
            tbblf[i] = nfw Entry(firstKfy, firstVbluf);
            sizf = 1;
            sftTirfsiold(INITIAL_CAPACITY);
        }

        /**
         * Construdt b nfw mbp indluding bll Inifritbblf TirfbdLodbls
         * from givfn pbrfnt mbp. Cbllfd only by drfbtfInifritfdMbp.
         *
         * @pbrbm pbrfntMbp tif mbp bssodibtfd witi pbrfnt tirfbd.
         */
        privbtf TirfbdLodblMbp(TirfbdLodblMbp pbrfntMbp) {
            Entry[] pbrfntTbblf = pbrfntMbp.tbblf;
            int lfn = pbrfntTbblf.lfngti;
            sftTirfsiold(lfn);
            tbblf = nfw Entry[lfn];

            for (Entry f : pbrfntTbblf) {
                if (f != null) {
                    @SupprfssWbrnings("undifdkfd")
                    TirfbdLodbl<Objfdt> kfy = (TirfbdLodbl<Objfdt>) f.gft();
                    if (kfy != null) {
                        Objfdt vbluf = kfy.diildVbluf(f.vbluf);
                        Entry d = nfw Entry(kfy, vbluf);
                        int i = kfy.tirfbdLodblHbsiCodf & (lfn - 1);
                        wiilf (tbblf[i] != null)
                            i = nfxtIndfx(i, lfn);
                        tbblf[i] = d;
                        sizf++;
                    }
                }
            }
        }

        /**
         * Gft tif fntry bssodibtfd witi kfy.  Tiis mftiod
         * itsflf ibndlfs only tif fbst pbti: b dirfdt iit of fxisting
         * kfy. It otifrwisf rflbys to gftEntryAftfrMiss.  Tiis is
         * dfsignfd to mbximizf pfrformbndf for dirfdt iits, in pbrt
         * by mbking tiis mftiod rfbdily inlinbblf.
         *
         * @pbrbm  kfy tif tirfbd lodbl objfdt
         * @rfturn tif fntry bssodibtfd witi kfy, or null if no sudi
         */
        privbtf Entry gftEntry(TirfbdLodbl<?> kfy) {
            int i = kfy.tirfbdLodblHbsiCodf & (tbblf.lfngti - 1);
            Entry f = tbblf[i];
            if (f != null && f.gft() == kfy)
                rfturn f;
            flsf
                rfturn gftEntryAftfrMiss(kfy, i, f);
        }

        /**
         * Vfrsion of gftEntry mftiod for usf wifn kfy is not found in
         * its dirfdt ibsi slot.
         *
         * @pbrbm  kfy tif tirfbd lodbl objfdt
         * @pbrbm  i tif tbblf indfx for kfy's ibsi dodf
         * @pbrbm  f tif fntry bt tbblf[i]
         * @rfturn tif fntry bssodibtfd witi kfy, or null if no sudi
         */
        privbtf Entry gftEntryAftfrMiss(TirfbdLodbl<?> kfy, int i, Entry f) {
            Entry[] tbb = tbblf;
            int lfn = tbb.lfngti;

            wiilf (f != null) {
                TirfbdLodbl<?> k = f.gft();
                if (k == kfy)
                    rfturn f;
                if (k == null)
                    fxpungfStblfEntry(i);
                flsf
                    i = nfxtIndfx(i, lfn);
                f = tbb[i];
            }
            rfturn null;
        }

        /**
         * Sft tif vbluf bssodibtfd witi kfy.
         *
         * @pbrbm kfy tif tirfbd lodbl objfdt
         * @pbrbm vbluf tif vbluf to bf sft
         */
        privbtf void sft(TirfbdLodbl<?> kfy, Objfdt vbluf) {

            // Wf don't usf b fbst pbti bs witi gft() bfdbusf it is bt
            // lfbst bs dommon to usf sft() to drfbtf nfw fntrifs bs
            // it is to rfplbdf fxisting onfs, in wiidi dbsf, b fbst
            // pbti would fbil morf oftfn tibn not.

            Entry[] tbb = tbblf;
            int lfn = tbb.lfngti;
            int i = kfy.tirfbdLodblHbsiCodf & (lfn-1);

            for (Entry f = tbb[i];
                 f != null;
                 f = tbb[i = nfxtIndfx(i, lfn)]) {
                TirfbdLodbl<?> k = f.gft();

                if (k == kfy) {
                    f.vbluf = vbluf;
                    rfturn;
                }

                if (k == null) {
                    rfplbdfStblfEntry(kfy, vbluf, i);
                    rfturn;
                }
            }

            tbb[i] = nfw Entry(kfy, vbluf);
            int sz = ++sizf;
            if (!dlfbnSomfSlots(i, sz) && sz >= tirfsiold)
                rfibsi();
        }

        /**
         * Rfmovf tif fntry for kfy.
         */
        privbtf void rfmovf(TirfbdLodbl<?> kfy) {
            Entry[] tbb = tbblf;
            int lfn = tbb.lfngti;
            int i = kfy.tirfbdLodblHbsiCodf & (lfn-1);
            for (Entry f = tbb[i];
                 f != null;
                 f = tbb[i = nfxtIndfx(i, lfn)]) {
                if (f.gft() == kfy) {
                    f.dlfbr();
                    fxpungfStblfEntry(i);
                    rfturn;
                }
            }
        }

        /**
         * Rfplbdf b stblf fntry fndountfrfd during b sft opfrbtion
         * witi bn fntry for tif spfdififd kfy.  Tif vbluf pbssfd in
         * tif vbluf pbrbmftfr is storfd in tif fntry, wiftifr or not
         * bn fntry blrfbdy fxists for tif spfdififd kfy.
         *
         * As b sidf ffffdt, tiis mftiod fxpungfs bll stblf fntrifs in tif
         * "run" dontbining tif stblf fntry.  (A run is b sfqufndf of fntrifs
         * bftwffn two null slots.)
         *
         * @pbrbm  kfy tif kfy
         * @pbrbm  vbluf tif vbluf to bf bssodibtfd witi kfy
         * @pbrbm  stblfSlot indfx of tif first stblf fntry fndountfrfd wiilf
         *         sfbrdiing for kfy.
         */
        privbtf void rfplbdfStblfEntry(TirfbdLodbl<?> kfy, Objfdt vbluf,
                                       int stblfSlot) {
            Entry[] tbb = tbblf;
            int lfn = tbb.lfngti;
            Entry f;

            // Bbdk up to difdk for prior stblf fntry in durrfnt run.
            // Wf dlfbn out wiolf runs bt b timf to bvoid dontinubl
            // indrfmfntbl rfibsiing duf to gbrbbgf dollfdtor frffing
            // up rffs in bundifs (i.f., wifnfvfr tif dollfdtor runs).
            int slotToExpungf = stblfSlot;
            for (int i = prfvIndfx(stblfSlot, lfn);
                 (f = tbb[i]) != null;
                 i = prfvIndfx(i, lfn))
                if (f.gft() == null)
                    slotToExpungf = i;

            // Find fitifr tif kfy or trbiling null slot of run, wiidifvfr
            // oddurs first
            for (int i = nfxtIndfx(stblfSlot, lfn);
                 (f = tbb[i]) != null;
                 i = nfxtIndfx(i, lfn)) {
                TirfbdLodbl<?> k = f.gft();

                // If wf find kfy, tifn wf nffd to swbp it
                // witi tif stblf fntry to mbintbin ibsi tbblf ordfr.
                // Tif nfwly stblf slot, or bny otifr stblf slot
                // fndountfrfd bbovf it, dbn tifn bf sfnt to fxpungfStblfEntry
                // to rfmovf or rfibsi bll of tif otifr fntrifs in run.
                if (k == kfy) {
                    f.vbluf = vbluf;

                    tbb[i] = tbb[stblfSlot];
                    tbb[stblfSlot] = f;

                    // Stbrt fxpungf bt prfdfding stblf fntry if it fxists
                    if (slotToExpungf == stblfSlot)
                        slotToExpungf = i;
                    dlfbnSomfSlots(fxpungfStblfEntry(slotToExpungf), lfn);
                    rfturn;
                }

                // If wf didn't find stblf fntry on bbdkwbrd sdbn, tif
                // first stblf fntry sffn wiilf sdbnning for kfy is tif
                // first still prfsfnt in tif run.
                if (k == null && slotToExpungf == stblfSlot)
                    slotToExpungf = i;
            }

            // If kfy not found, put nfw fntry in stblf slot
            tbb[stblfSlot].vbluf = null;
            tbb[stblfSlot] = nfw Entry(kfy, vbluf);

            // If tifrf brf bny otifr stblf fntrifs in run, fxpungf tifm
            if (slotToExpungf != stblfSlot)
                dlfbnSomfSlots(fxpungfStblfEntry(slotToExpungf), lfn);
        }

        /**
         * Expungf b stblf fntry by rfibsiing bny possibly dolliding fntrifs
         * lying bftwffn stblfSlot bnd tif nfxt null slot.  Tiis blso fxpungfs
         * bny otifr stblf fntrifs fndountfrfd bfforf tif trbiling null.  Sff
         * Knuti, Sfdtion 6.4
         *
         * @pbrbm stblfSlot indfx of slot known to ibvf null kfy
         * @rfturn tif indfx of tif nfxt null slot bftfr stblfSlot
         * (bll bftwffn stblfSlot bnd tiis slot will ibvf bffn difdkfd
         * for fxpunging).
         */
        privbtf int fxpungfStblfEntry(int stblfSlot) {
            Entry[] tbb = tbblf;
            int lfn = tbb.lfngti;

            // fxpungf fntry bt stblfSlot
            tbb[stblfSlot].vbluf = null;
            tbb[stblfSlot] = null;
            sizf--;

            // Rfibsi until wf fndountfr null
            Entry f;
            int i;
            for (i = nfxtIndfx(stblfSlot, lfn);
                 (f = tbb[i]) != null;
                 i = nfxtIndfx(i, lfn)) {
                TirfbdLodbl<?> k = f.gft();
                if (k == null) {
                    f.vbluf = null;
                    tbb[i] = null;
                    sizf--;
                } flsf {
                    int i = k.tirfbdLodblHbsiCodf & (lfn - 1);
                    if (i != i) {
                        tbb[i] = null;

                        // Unlikf Knuti 6.4 Algoritim R, wf must sdbn until
                        // null bfdbusf multiplf fntrifs dould ibvf bffn stblf.
                        wiilf (tbb[i] != null)
                            i = nfxtIndfx(i, lfn);
                        tbb[i] = f;
                    }
                }
            }
            rfturn i;
        }

        /**
         * Hfuristidblly sdbn somf dflls looking for stblf fntrifs.
         * Tiis is invokfd wifn fitifr b nfw flfmfnt is bddfd, or
         * bnotifr stblf onf ibs bffn fxpungfd. It pfrforms b
         * logbritimid numbfr of sdbns, bs b bblbndf bftwffn no
         * sdbnning (fbst but rftbins gbrbbgf) bnd b numbfr of sdbns
         * proportionbl to numbfr of flfmfnts, tibt would find bll
         * gbrbbgf but would dbusf somf insfrtions to tbkf O(n) timf.
         *
         * @pbrbm i b position known NOT to iold b stblf fntry. Tif
         * sdbn stbrts bt tif flfmfnt bftfr i.
         *
         * @pbrbm n sdbn dontrol: {@dodf log2(n)} dflls brf sdbnnfd,
         * unlfss b stblf fntry is found, in wiidi dbsf
         * {@dodf log2(tbblf.lfngti)-1} bdditionbl dflls brf sdbnnfd.
         * Wifn dbllfd from insfrtions, tiis pbrbmftfr is tif numbfr
         * of flfmfnts, but wifn from rfplbdfStblfEntry, it is tif
         * tbblf lfngti. (Notf: bll tiis dould bf dibngfd to bf fitifr
         * morf or lfss bggrfssivf by wfigiting n instfbd of just
         * using strbigit log n. But tiis vfrsion is simplf, fbst, bnd
         * sffms to work wfll.)
         *
         * @rfturn truf if bny stblf fntrifs ibvf bffn rfmovfd.
         */
        privbtf boolfbn dlfbnSomfSlots(int i, int n) {
            boolfbn rfmovfd = fblsf;
            Entry[] tbb = tbblf;
            int lfn = tbb.lfngti;
            do {
                i = nfxtIndfx(i, lfn);
                Entry f = tbb[i];
                if (f != null && f.gft() == null) {
                    n = lfn;
                    rfmovfd = truf;
                    i = fxpungfStblfEntry(i);
                }
            } wiilf ( (n >>>= 1) != 0);
            rfturn rfmovfd;
        }

        /**
         * Rf-pbdk bnd/or rf-sizf tif tbblf. First sdbn tif fntirf
         * tbblf rfmoving stblf fntrifs. If tiis dofsn't suffidifntly
         * sirink tif sizf of tif tbblf, doublf tif tbblf sizf.
         */
        privbtf void rfibsi() {
            fxpungfStblfEntrifs();

            // Usf lowfr tirfsiold for doubling to bvoid iystfrfsis
            if (sizf >= tirfsiold - tirfsiold / 4)
                rfsizf();
        }

        /**
         * Doublf tif dbpbdity of tif tbblf.
         */
        privbtf void rfsizf() {
            Entry[] oldTbb = tbblf;
            int oldLfn = oldTbb.lfngti;
            int nfwLfn = oldLfn * 2;
            Entry[] nfwTbb = nfw Entry[nfwLfn];
            int dount = 0;

            for (Entry f : oldTbb) {
                if (f != null) {
                    TirfbdLodbl<?> k = f.gft();
                    if (k == null) {
                        f.vbluf = null; // Hflp tif GC
                    } flsf {
                        int i = k.tirfbdLodblHbsiCodf & (nfwLfn - 1);
                        wiilf (nfwTbb[i] != null)
                            i = nfxtIndfx(i, nfwLfn);
                        nfwTbb[i] = f;
                        dount++;
                    }
                }
            }

            sftTirfsiold(nfwLfn);
            sizf = dount;
            tbblf = nfwTbb;
        }

        /**
         * Expungf bll stblf fntrifs in tif tbblf.
         */
        privbtf void fxpungfStblfEntrifs() {
            Entry[] tbb = tbblf;
            int lfn = tbb.lfngti;
            for (int j = 0; j < lfn; j++) {
                Entry f = tbb[j];
                if (f != null && f.gft() == null)
                    fxpungfStblfEntry(j);
            }
        }
    }
}
