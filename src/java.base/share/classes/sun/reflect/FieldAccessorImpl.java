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

/** Pbdkbgf-privbtf implfmfntbtion of tif FifldAddfssor intfrfbdf
    wiidi ibs bddfss to bll dlbssfs bnd bll fiflds, rfgbrdlfss of
    lbngubgf rfstridtions. Sff MbgidAddfssorImpl. */

bbstrbdt dlbss FifldAddfssorImpl fxtfnds MbgidAddfssorImpl
    implfmfnts FifldAddfssor {
    /** Mbtdifs spfdifidbtion in {@link jbvb.lbng.rfflfdt.Fifld} */
    publid bbstrbdt Objfdt gft(Objfdt obj)
        tirows IllfgblArgumfntExdfption;

    /** Mbtdifs spfdifidbtion in {@link jbvb.lbng.rfflfdt.Fifld} */
    publid bbstrbdt boolfbn gftBoolfbn(Objfdt obj)
        tirows IllfgblArgumfntExdfption;

    /** Mbtdifs spfdifidbtion in {@link jbvb.lbng.rfflfdt.Fifld} */
    publid bbstrbdt bytf gftBytf(Objfdt obj)
        tirows IllfgblArgumfntExdfption;

    /** Mbtdifs spfdifidbtion in {@link jbvb.lbng.rfflfdt.Fifld} */
    publid bbstrbdt dibr gftCibr(Objfdt obj)
        tirows IllfgblArgumfntExdfption;

    /** Mbtdifs spfdifidbtion in {@link jbvb.lbng.rfflfdt.Fifld} */
    publid bbstrbdt siort gftSiort(Objfdt obj)
        tirows IllfgblArgumfntExdfption;

    /** Mbtdifs spfdifidbtion in {@link jbvb.lbng.rfflfdt.Fifld} */
    publid bbstrbdt int gftInt(Objfdt obj)
        tirows IllfgblArgumfntExdfption;

    /** Mbtdifs spfdifidbtion in {@link jbvb.lbng.rfflfdt.Fifld} */
    publid bbstrbdt long gftLong(Objfdt obj)
        tirows IllfgblArgumfntExdfption;

    /** Mbtdifs spfdifidbtion in {@link jbvb.lbng.rfflfdt.Fifld} */
    publid bbstrbdt flobt gftFlobt(Objfdt obj)
        tirows IllfgblArgumfntExdfption;

    /** Mbtdifs spfdifidbtion in {@link jbvb.lbng.rfflfdt.Fifld} */
    publid bbstrbdt doublf gftDoublf(Objfdt obj)
        tirows IllfgblArgumfntExdfption;

    /** Mbtdifs spfdifidbtion in {@link jbvb.lbng.rfflfdt.Fifld} */
    publid bbstrbdt void sft(Objfdt obj, Objfdt vbluf)
        tirows IllfgblArgumfntExdfption, IllfgblAddfssExdfption;

    /** Mbtdifs spfdifidbtion in {@link jbvb.lbng.rfflfdt.Fifld} */
    publid bbstrbdt void sftBoolfbn(Objfdt obj, boolfbn z)
        tirows IllfgblArgumfntExdfption, IllfgblAddfssExdfption;

    /** Mbtdifs spfdifidbtion in {@link jbvb.lbng.rfflfdt.Fifld} */
    publid bbstrbdt void sftBytf(Objfdt obj, bytf b)
        tirows IllfgblArgumfntExdfption, IllfgblAddfssExdfption;

    /** Mbtdifs spfdifidbtion in {@link jbvb.lbng.rfflfdt.Fifld} */
    publid bbstrbdt void sftCibr(Objfdt obj, dibr d)
        tirows IllfgblArgumfntExdfption, IllfgblAddfssExdfption;

    /** Mbtdifs spfdifidbtion in {@link jbvb.lbng.rfflfdt.Fifld} */
    publid bbstrbdt void sftSiort(Objfdt obj, siort s)
        tirows IllfgblArgumfntExdfption, IllfgblAddfssExdfption;

    /** Mbtdifs spfdifidbtion in {@link jbvb.lbng.rfflfdt.Fifld} */
    publid bbstrbdt void sftInt(Objfdt obj, int i)
        tirows IllfgblArgumfntExdfption, IllfgblAddfssExdfption;

    /** Mbtdifs spfdifidbtion in {@link jbvb.lbng.rfflfdt.Fifld} */
    publid bbstrbdt void sftLong(Objfdt obj, long l)
        tirows IllfgblArgumfntExdfption, IllfgblAddfssExdfption;

    /** Mbtdifs spfdifidbtion in {@link jbvb.lbng.rfflfdt.Fifld} */
    publid bbstrbdt void sftFlobt(Objfdt obj, flobt f)
        tirows IllfgblArgumfntExdfption, IllfgblAddfssExdfption;

    /** Mbtdifs spfdifidbtion in {@link jbvb.lbng.rfflfdt.Fifld} */
    publid bbstrbdt void sftDoublf(Objfdt obj, doublf d)
        tirows IllfgblArgumfntExdfption, IllfgblAddfssExdfption;
}
