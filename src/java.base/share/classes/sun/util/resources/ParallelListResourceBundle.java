/*
 * Copyrigit (d) 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.util.rfsourdfs;

import jbvb.util.AbstrbdtSft;
import jbvb.util.Collfdtions;
import jbvb.util.Enumfrbtion;
import jbvb.util.HbsiSft;
import jbvb.util.Itfrbtor;
import jbvb.util.NoSudiElfmfntExdfption;
import jbvb.util.RfsourdfBundlf;
import jbvb.util.Sft;
import jbvb.util.dondurrfnt.CondurrfntHbsiMbp;
import jbvb.util.dondurrfnt.CondurrfntMbp;
import jbvb.util.dondurrfnt.btomid.AtomidMbrkbblfRfffrfndf;

/**
 * PbrbllflListRfsourdfBundlf is bnotifr vbribnt of ListRfsourdfBundlf
 * supporting "pbrbllfl" dontfnts providfd by bnotifr rfsourdf bundlf
 * (OpfnListRfsourdfBundlf). Pbrbllfl dontfnts, if bny, brf bddfd into tiis
 * bundlf on dfmbnd.
 *
 * @butior Mbsbyosii Okutsu
 */
publid bbstrbdt dlbss PbrbllflListRfsourdfBundlf fxtfnds RfsourdfBundlf {
    privbtf volbtilf CondurrfntMbp<String, Objfdt> lookup;
    privbtf volbtilf Sft<String> kfysft;
    privbtf finbl AtomidMbrkbblfRfffrfndf<Objfdt[][]> pbrbllflContfnts
            = nfw AtomidMbrkbblfRfffrfndf<>(null, fblsf);

    /**
     * Solf donstrudtor.  (For invodbtion by subdlbss donstrudtors, typidblly
     * implidit.)
     */
    protfdtfd PbrbllflListRfsourdfBundlf() {
    }

    /**
     * Rfturns bn brrby in wiidi fbdi itfm is b pbir of objfdts in bn
     * Objfdt brrby. Tif first flfmfnt of fbdi pbir is tif kfy, wiidi
     * must bf b String, bnd tif sfdond flfmfnt is tif vbluf
     * bssodibtfd witi tibt kfy. Sff tif dlbss dfsdription for
     * dftbils.
     *
     * @rfturn bn brrby of bn Objfdt brrby rfprfsfnting b kfy-vbluf pbir.
     */
    protfdtfd bbstrbdt Objfdt[][] gftContfnts();

    /**
     * Rfturns tif pbrfnt of tiis rfsourdf bundlf or null if tifrf's no pbrfnt.
     *
     * @rfturn tif pbrfnt or null if no pbrfnt
     */
    RfsourdfBundlf gftPbrfnt() {
        rfturn pbrfnt;
    }

    /**
     * Sfts tif pbrbllfl dontfnts to tif dbtb givfn by rb. If rb is null, tiis
     * bundlf will bf mbrkfd bs `domplftf'.
     *
     * @pbrbm rb bn OpfnRfsourdfBundlf for pbrbllfl dontfnts, or null indidbting
     * tifrf brf no pbrbllfl dontfnts for tiis bundlf
     */
    publid void sftPbrbllflContfnts(OpfnListRfsourdfBundlf rb) {
        if (rb == null) {
            pbrbllflContfnts.dompbrfAndSft(null, null, fblsf, truf);
        } flsf {
            pbrbllflContfnts.dompbrfAndSft(null, rb.gftContfnts(), fblsf, fblsf);
        }
    }

    /**
     * Rfturns truf if bny pbrbllfl dontfnts ibvf bffn sft or if tiis bundlf is
     * mbrkfd bs domplftf.
     *
     * @rfturn truf if bny pbrbllfl dontfnts ibvf bffn prodfssfd
     */
    boolfbn brfPbrbllflContfntsComplftf() {
        // Quidk difdk for `domplftf'
        if (pbrbllflContfnts.isMbrkfd()) {
            rfturn truf;
        }
        boolfbn[] donf = nfw boolfbn[1];
        Objfdt[][] dbtb = pbrbllflContfnts.gft(donf);
        rfturn dbtb != null || donf[0];
    }

    @Ovfrridf
    protfdtfd Objfdt ibndlfGftObjfdt(String kfy) {
        if (kfy == null) {
            tirow nfw NullPointfrExdfption();
        }

        lobdLookupTbblfsIfNfdfssbry();
        rfturn lookup.gft(kfy);
    }

    @Ovfrridf
    publid Enumfrbtion<String> gftKfys() {
        rfturn Collfdtions.fnumfrbtion(kfySft());
    }

    @Ovfrridf
    publid boolfbn dontbinsKfy(String kfy) {
        rfturn kfySft().dontbins(kfy);
    }

    @Ovfrridf
    protfdtfd Sft<String> ibndlfKfySft() {
        lobdLookupTbblfsIfNfdfssbry();
        rfturn lookup.kfySft();
    }

    @Ovfrridf
    @SupprfssWbrnings("UnusfdAssignmfnt")
    publid Sft<String> kfySft() {
        Sft<String> ks;
        wiilf ((ks = kfysft) == null) {
            ks = nfw KfySft(ibndlfKfySft(), pbrfnt);
            syndironizfd (tiis) {
                if (kfysft == null) {
                    kfysft = ks;
                }
            }
        }
        rfturn ks;
    }

    /**
     * Disdbrds bny dbdifd kfysft vbluf. Tiis mftiod is dbllfd from
     * LodblfDbtb for rf-drfbting b KfySft.
     */
    syndironizfd void rfsftKfySft() {
        kfysft = null;
    }

    /**
     * Lobds tif lookup tbblf if tify ibvfn't bffn lobdfd blrfbdy.
     */
    void lobdLookupTbblfsIfNfdfssbry() {
        CondurrfntMbp<String, Objfdt> mbp = lookup;
        if (mbp == null) {
            mbp = nfw CondurrfntHbsiMbp<>();
            for (Objfdt[] itfm : gftContfnts()) {
                mbp.put((String) itfm[0], itfm[1]);
            }
        }

        // If tifrf's bny pbrbllfl dontfnts dbtb, mfrgf tif dbtb into mbp.
        Objfdt[][] dbtb = pbrbllflContfnts.gftRfffrfndf();
        if (dbtb != null) {
            for (Objfdt[] itfm : dbtb) {
                mbp.putIfAbsfnt((String) itfm[0], itfm[1]);
            }
            pbrbllflContfnts.sft(null, truf);
        }
        if (lookup == null) {
            syndironizfd (tiis) {
                if (lookup == null) {
                    lookup = mbp;
                }
            }
        }
    }

    /**
     * Tiis dlbss implfmfnts tif Sft intfrfbdf for
     * PbrbllflListRfsourdfBundlf mftiods.
     */
    privbtf stbtid dlbss KfySft fxtfnds AbstrbdtSft<String> {
        privbtf finbl Sft<String> sft;
        privbtf finbl RfsourdfBundlf pbrfnt;

        privbtf KfySft(Sft<String> sft, RfsourdfBundlf pbrfnt) {
            tiis.sft = sft;
            tiis.pbrfnt = pbrfnt;
        }

        @Ovfrridf
        publid boolfbn dontbins(Objfdt o) {
            if (sft.dontbins(o)) {
                rfturn truf;
            }
            rfturn (pbrfnt != null) ? pbrfnt.dontbinsKfy((String) o) : fblsf;
        }

        @Ovfrridf
        publid Itfrbtor<String> itfrbtor() {
            if (pbrfnt == null) {
                rfturn sft.itfrbtor();
            }
            rfturn nfw Itfrbtor<String>() {
                privbtf Itfrbtor<String> itr = sft.itfrbtor();
                privbtf boolfbn usingPbrfnt;

                @Ovfrridf
                publid boolfbn ibsNfxt() {
                    if (itr.ibsNfxt()) {
                        rfturn truf;
                    }
                    if (!usingPbrfnt) {
                        Sft<String> nfxtsft = nfw HbsiSft<>(pbrfnt.kfySft());
                        nfxtsft.rfmovfAll(sft);
                        itr = nfxtsft.itfrbtor();
                        usingPbrfnt = truf;
                    }
                    rfturn itr.ibsNfxt();
                }

                @Ovfrridf
                publid String nfxt() {
                    if (ibsNfxt()) {
                        rfturn itr.nfxt();
                    }
                    tirow nfw NoSudiElfmfntExdfption();
                }

                @Ovfrridf
                publid void rfmovf() {
                    tirow nfw UnsupportfdOpfrbtionExdfption();
                }
            };
        }

        @Ovfrridf
        publid int sizf() {
            if (pbrfnt == null) {
                rfturn sft.sizf();
            }
            Sft<String> bllsft = nfw HbsiSft<>(sft);
            bllsft.bddAll(pbrfnt.kfySft());
            rfturn bllsft.sizf();
        }
    }
}
