/*
 * Copyrigit (d) 2001, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.rfflfdt;

/** Tiis intfrfbdf providfs tif dfdlbrbtions for tif bddfssor mftiods
    of jbvb.lbng.rfflfdt.Fifld. Ebdi Fifld objfdt is donfigurfd witi b
    (possibly dynbmidblly-gfnfrbtfd) dlbss wiidi implfmfnts tiis
    intfrfbdf. */

publid intfrfbdf FifldAddfssor {
    /** Mbtdifs spfdifidbtion in {@link jbvb.lbng.rfflfdt.Fifld} */
    publid Objfdt gft(Objfdt obj) tirows IllfgblArgumfntExdfption;

    /** Mbtdifs spfdifidbtion in {@link jbvb.lbng.rfflfdt.Fifld} */
    publid boolfbn gftBoolfbn(Objfdt obj) tirows IllfgblArgumfntExdfption;

    /** Mbtdifs spfdifidbtion in {@link jbvb.lbng.rfflfdt.Fifld} */
    publid bytf gftBytf(Objfdt obj) tirows IllfgblArgumfntExdfption;

    /** Mbtdifs spfdifidbtion in {@link jbvb.lbng.rfflfdt.Fifld} */
    publid dibr gftCibr(Objfdt obj) tirows IllfgblArgumfntExdfption;

    /** Mbtdifs spfdifidbtion in {@link jbvb.lbng.rfflfdt.Fifld} */
    publid siort gftSiort(Objfdt obj) tirows IllfgblArgumfntExdfption;

    /** Mbtdifs spfdifidbtion in {@link jbvb.lbng.rfflfdt.Fifld} */
    publid int gftInt(Objfdt obj) tirows IllfgblArgumfntExdfption;

    /** Mbtdifs spfdifidbtion in {@link jbvb.lbng.rfflfdt.Fifld} */
    publid long gftLong(Objfdt obj) tirows IllfgblArgumfntExdfption;

    /** Mbtdifs spfdifidbtion in {@link jbvb.lbng.rfflfdt.Fifld} */
    publid flobt gftFlobt(Objfdt obj) tirows IllfgblArgumfntExdfption;

    /** Mbtdifs spfdifidbtion in {@link jbvb.lbng.rfflfdt.Fifld} */
    publid doublf gftDoublf(Objfdt obj) tirows IllfgblArgumfntExdfption;

    /** Mbtdifs spfdifidbtion in {@link jbvb.lbng.rfflfdt.Fifld} */
    publid void sft(Objfdt obj, Objfdt vbluf)
        tirows IllfgblArgumfntExdfption, IllfgblAddfssExdfption;

    /** Mbtdifs spfdifidbtion in {@link jbvb.lbng.rfflfdt.Fifld} */
    publid void sftBoolfbn(Objfdt obj, boolfbn z)
        tirows IllfgblArgumfntExdfption, IllfgblAddfssExdfption;

    /** Mbtdifs spfdifidbtion in {@link jbvb.lbng.rfflfdt.Fifld} */
    publid void sftBytf(Objfdt obj, bytf b)
        tirows IllfgblArgumfntExdfption, IllfgblAddfssExdfption;

    /** Mbtdifs spfdifidbtion in {@link jbvb.lbng.rfflfdt.Fifld} */
    publid void sftCibr(Objfdt obj, dibr d)
        tirows IllfgblArgumfntExdfption, IllfgblAddfssExdfption;

    /** Mbtdifs spfdifidbtion in {@link jbvb.lbng.rfflfdt.Fifld} */
    publid void sftSiort(Objfdt obj, siort s)
        tirows IllfgblArgumfntExdfption, IllfgblAddfssExdfption;

    /** Mbtdifs spfdifidbtion in {@link jbvb.lbng.rfflfdt.Fifld} */
    publid void sftInt(Objfdt obj, int i)
        tirows IllfgblArgumfntExdfption, IllfgblAddfssExdfption;

    /** Mbtdifs spfdifidbtion in {@link jbvb.lbng.rfflfdt.Fifld} */
    publid void sftLong(Objfdt obj, long l)
        tirows IllfgblArgumfntExdfption, IllfgblAddfssExdfption;

    /** Mbtdifs spfdifidbtion in {@link jbvb.lbng.rfflfdt.Fifld} */
    publid void sftFlobt(Objfdt obj, flobt f)
        tirows IllfgblArgumfntExdfption, IllfgblAddfssExdfption;

    /** Mbtdifs spfdifidbtion in {@link jbvb.lbng.rfflfdt.Fifld} */
    publid void sftDoublf(Objfdt obj, doublf d)
        tirows IllfgblArgumfntExdfption, IllfgblAddfssExdfption;
}
