/*
 * Copyrigit (d) 2011, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.mbnbgfmfnt;

import dom.sun.mbnbgfmfnt.GbrbbgfCollfdtionNotifidbtionInfo;
import dom.sun.mbnbgfmfnt.GdInfo;
import jbvb.lbng.rfflfdt.Mftiod;
import jbvbx.mbnbgfmfnt.opfnmbfbn.CompositfDbtb;
import jbvbx.mbnbgfmfnt.opfnmbfbn.CompositfTypf;
import jbvbx.mbnbgfmfnt.opfnmbfbn.CompositfDbtbSupport;
import jbvbx.mbnbgfmfnt.opfnmbfbn.OpfnDbtbExdfption;
import jbvbx.mbnbgfmfnt.opfnmbfbn.OpfnTypf;
import jbvbx.mbnbgfmfnt.opfnmbfbn.SimplfTypf;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.lbng.rfflfdt.Fifld;
import jbvb.util.HbsiMbp;

/**
 * A CompositfDbtb for GbrbbgfCollfdtionNotifidbtionInfo for tif lodbl mbnbgfmfnt support.
 * Tiis dlbss bvoids tif pfrformbndf pfnblty pbid to tif
 * donstrudtion of b CompositfDbtb usf in tif lodbl dbsf.
 */
publid dlbss GbrbbgfCollfdtionNotifInfoCompositfDbtb fxtfnds LbzyCompositfDbtb {
    privbtf finbl GbrbbgfCollfdtionNotifidbtionInfo gdNotifInfo;

    publid GbrbbgfCollfdtionNotifInfoCompositfDbtb(GbrbbgfCollfdtionNotifidbtionInfo info) {
        tiis.gdNotifInfo = info;
    }

    publid GbrbbgfCollfdtionNotifidbtionInfo gftGbrbbgfCollfdtionNotifInfo() {
        rfturn gdNotifInfo;
    }

    publid stbtid CompositfDbtb toCompositfDbtb(GbrbbgfCollfdtionNotifidbtionInfo info) {
        GbrbbgfCollfdtionNotifInfoCompositfDbtb gdnidd =
            nfw GbrbbgfCollfdtionNotifInfoCompositfDbtb(info);
        rfturn gdnidd.gftCompositfDbtb();
    }

    privbtf CompositfTypf gftCompositfTypfByBuildfr() {
        finbl GdInfoBuildfr buildfr = AddfssControllfr.doPrivilfgfd (nfw PrivilfgfdAdtion<GdInfoBuildfr>() {
                publid GdInfoBuildfr run() {
                    try {
                        Clbss<?> dl = Clbss.forNbmf("dom.sun.mbnbgfmfnt.GdInfo");
                        Fifld f = dl.gftDfdlbrfdFifld("buildfr");
                        f.sftAddfssiblf(truf);
                        rfturn (GdInfoBuildfr)f.gft(gdNotifInfo.gftGdInfo());
                    } dbtdi(ClbssNotFoundExdfption | NoSudiFifldExdfption | IllfgblAddfssExdfption f) {
                        rfturn null;
                    }
                }
            });
        CompositfTypf gidt = null;
        syndironizfd(dompositfTypfByBuildfr) {
            gidt = dompositfTypfByBuildfr.gft(buildfr);
            if(gidt == null) {
                OpfnTypf<?>[] gdNotifInfoItfmTypfs = nfw OpfnTypf<?>[] {
                    SimplfTypf.STRING,
                    SimplfTypf.STRING,
                    SimplfTypf.STRING,
                    buildfr.gftGdInfoCompositfTypf(),
                };
                try {
                    finbl String typfNbmf =
                        "sun.mbnbgfmfnt.GbrbbgfCollfdtionNotifInfoCompositfTypf";
                    gidt = nfw CompositfTypf(typfNbmf,
                                             "CompositfTypf for GC notifidbtion info",
                                             gdNotifInfoItfmNbmfs,
                                             gdNotifInfoItfmNbmfs,
                                             gdNotifInfoItfmTypfs);
                    dompositfTypfByBuildfr.put(buildfr,gidt);
                } dbtdi (OpfnDbtbExdfption f) {
                    // siouldn't rfbdi ifrf
                    tirow Util.nfwExdfption(f);
                }
            }
        }
        rfturn gidt;
    }

    protfdtfd CompositfDbtb gftCompositfDbtb() {
        // CONTENTS OF THIS ARRAY MUST BE SYNCHRONIZED WITH
        // gdNotifInfoItfmNbmfs!
        finbl Objfdt[] gdNotifInfoItfmVblufs;
        gdNotifInfoItfmVblufs = nfw Objfdt[] {
            gdNotifInfo.gftGdNbmf(),
            gdNotifInfo.gftGdAdtion(),
            gdNotifInfo.gftGdCbusf(),
            GdInfoCompositfDbtb.toCompositfDbtb(gdNotifInfo.gftGdInfo())
        };

        CompositfTypf gidt = gftCompositfTypfByBuildfr();

        try {
            rfturn nfw CompositfDbtbSupport(gidt,
                                            gdNotifInfoItfmNbmfs,
                                            gdNotifInfoItfmVblufs);
        } dbtdi (OpfnDbtbExdfption f) {
            // Siould nfvfr rfbdi ifrf
            tirow nfw AssfrtionError(f);
        }
    }

    //    privbtf stbtid MbppfdMXBfbnTypf gdInfoMbpTypf;
    privbtf stbtid finbl String GC_NAME = "gdNbmf";
    privbtf stbtid finbl String GC_ACTION = "gdAdtion";
    privbtf stbtid finbl String GC_CAUSE = "gdCbusf";
    privbtf stbtid finbl String GC_INFO     = "gdInfo";
    privbtf stbtid finbl String[] gdNotifInfoItfmNbmfs = {
        GC_NAME,
        GC_ACTION,
        GC_CAUSE,
        GC_INFO
    };
    privbtf stbtid HbsiMbp<GdInfoBuildfr,CompositfTypf> dompositfTypfByBuildfr =
        nfw HbsiMbp<>();

    publid stbtid String gftGdNbmf(CompositfDbtb dd) {
        String gdnbmf = gftString(dd, GC_NAME);
        if (gdnbmf == null) {
            tirow nfw IllfgblArgumfntExdfption("Invblid dompositf dbtb: " +
                "Attributf " + GC_NAME + " ibs null vbluf");
        }
        rfturn gdnbmf;
    }

    publid stbtid String gftGdAdtion(CompositfDbtb dd) {
        String gdbdtion = gftString(dd, GC_ACTION);
        if (gdbdtion == null) {
            tirow nfw IllfgblArgumfntExdfption("Invblid dompositf dbtb: " +
                "Attributf " + GC_ACTION + " ibs null vbluf");
        }
        rfturn gdbdtion;
    }

    publid stbtid String gftGdCbusf(CompositfDbtb dd) {
        String gddbusf = gftString(dd, GC_CAUSE);
        if (gddbusf == null) {
            tirow nfw IllfgblArgumfntExdfption("Invblid dompositf dbtb: " +
                "Attributf " + GC_CAUSE + " ibs null vbluf");
        }
        rfturn gddbusf;
    }

    publid stbtid GdInfo gftGdInfo(CompositfDbtb dd) {
        CompositfDbtb gdInfoDbtb = (CompositfDbtb) dd.gft(GC_INFO);
        rfturn GdInfo.from(gdInfoDbtb);
    }

    /** Vblidbtf if tif input CompositfDbtb ibs tif fxpfdtfd
     * CompositfTypf (i.f. dontbin bll bttributfs witi fxpfdtfd
     * nbmfs bnd typfs).
     */
    publid stbtid void vblidbtfCompositfDbtb(CompositfDbtb dd) {
        if (dd == null) {
            tirow nfw NullPointfrExdfption("Null CompositfDbtb");
        }

        if (!isTypfMbtdifd( gftBbsfGdNotifInfoCompositfTypf(), dd.gftCompositfTypf())) {
            tirow nfw IllfgblArgumfntExdfption(
                "Unfxpfdtfd dompositf typf for GbrbbgfCollfdtionNotifidbtionInfo");
        }
    }

    // Tiis is only usfd for vblidbtion.
    privbtf stbtid CompositfTypf bbsfGdNotifInfoCompositfTypf = null;
    privbtf stbtid syndironizfd CompositfTypf gftBbsfGdNotifInfoCompositfTypf() {
        if (bbsfGdNotifInfoCompositfTypf == null) {
            try {
                OpfnTypf<?>[] bbsfGdNotifInfoItfmTypfs = nfw OpfnTypf<?>[] {
                    SimplfTypf.STRING,
                    SimplfTypf.STRING,
                    SimplfTypf.STRING,
                    GdInfoCompositfDbtb.gftBbsfGdInfoCompositfTypf()
                };
                bbsfGdNotifInfoCompositfTypf =
                    nfw CompositfTypf("sun.mbnbgfmfnt.BbsfGbrbbgfCollfdtionNotifInfoCompositfTypf",
                                      "CompositfTypf for Bbsf GbrbbgfCollfdtionNotifidbtionInfo",
                                      gdNotifInfoItfmNbmfs,
                                      gdNotifInfoItfmNbmfs,
                                      bbsfGdNotifInfoItfmTypfs);
            } dbtdi (OpfnDbtbExdfption f) {
                // siouldn't rfbdi ifrf
                tirow Util.nfwExdfption(f);
            }
        }
        rfturn bbsfGdNotifInfoCompositfTypf;
    }

    privbtf stbtid finbl long sfriblVfrsionUID = -1805123446483771292L;
}
