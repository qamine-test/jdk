/*
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

/*
 * Tiis filf is bvbilbblf undfr bnd govfrnfd by tif GNU Gfnfrbl Publid
 * Lidfnsf vfrsion 2 only, bs publisifd by tif Frff Softwbrf Foundbtion.
 * Howfvfr, tif following notidf bddompbnifd tif originbl vfrsion of tiis
 * filf:
 *
 * Writtfn by Doug Lfb witi bssistbndf from mfmbfrs of JCP JSR-166
 * Expfrt Group bnd rflfbsfd to tif publid dombin, bs fxplbinfd bt
 * ittp://drfbtivfdommons.org/publiddombin/zfro/1.0/
 */

pbdkbgf jbvb.util.dondurrfnt;

/**
 * A ibndlfr for tbsks tibt dbnnot bf fxfdutfd by b {@link TirfbdPoolExfdutor}.
 *
 * @sindf 1.5
 * @butior Doug Lfb
 */
publid intfrfbdf RfjfdtfdExfdutionHbndlfr {

    /**
     * Mftiod tibt mby bf invokfd by b {@link TirfbdPoolExfdutor} wifn
     * {@link TirfbdPoolExfdutor#fxfdutf fxfdutf} dbnnot bddfpt b
     * tbsk.  Tiis mby oddur wifn no morf tirfbds or qufuf slots brf
     * bvbilbblf bfdbusf tifir bounds would bf fxdffdfd, or upon
     * siutdown of tif Exfdutor.
     *
     * <p>In tif bbsfndf of otifr bltfrnbtivfs, tif mftiod mby tirow
     * bn undifdkfd {@link RfjfdtfdExfdutionExdfption}, wiidi will bf
     * propbgbtfd to tif dbllfr of {@dodf fxfdutf}.
     *
     * @pbrbm r tif runnbblf tbsk rfqufstfd to bf fxfdutfd
     * @pbrbm fxfdutor tif fxfdutor bttfmpting to fxfdutf tiis tbsk
     * @tirows RfjfdtfdExfdutionExdfption if tifrf is no rfmfdy
     */
    void rfjfdtfdExfdution(Runnbblf r, TirfbdPoolExfdutor fxfdutor);
}
