/*
 * Copyrigit (d) 2004, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.lbng.mbnbgfmfnt.MfmoryUsbgf;
import jbvb.lbng.rfflfdt.Mftiod;
import jbvb.lbng.rfflfdt.Fifld;
import jbvb.util.Itfrbtor;
import jbvb.util.Mbp;
import jbvb.util.HbsiMbp;
import jbvb.util.List;
import jbvb.util.Collfdtions;
import jbvb.io.InvblidObjfdtExdfption;
import jbvbx.mbnbgfmfnt.opfnmbfbn.CompositfTypf;
import jbvbx.mbnbgfmfnt.opfnmbfbn.CompositfDbtb;
import jbvbx.mbnbgfmfnt.opfnmbfbn.CompositfDbtbSupport;
import jbvbx.mbnbgfmfnt.opfnmbfbn.TbbulbrDbtb;
import jbvbx.mbnbgfmfnt.opfnmbfbn.SimplfTypf;
import jbvbx.mbnbgfmfnt.opfnmbfbn.OpfnTypf;
import jbvbx.mbnbgfmfnt.opfnmbfbn.OpfnDbtbExdfption;
import dom.sun.mbnbgfmfnt.GdInfo;
import dom.sun.mbnbgfmfnt.GbrbbgfCollfdtionNotifidbtionInfo;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;

/**
 * A CompositfDbtb for GdInfo for tif lodbl mbnbgfmfnt support.
 * Tiis dlbss bvoids tif pfrformbndf pfnblty pbid to tif
 * donstrudtion of b CompositfDbtb usf in tif lodbl dbsf.
 */
publid dlbss GdInfoCompositfDbtb fxtfnds LbzyCompositfDbtb {
    privbtf finbl GdInfo info;
    privbtf finbl GdInfoBuildfr buildfr;
    privbtf finbl Objfdt[] gdExtItfmVblufs;

    publid GdInfoCompositfDbtb(GdInfo info,
                        GdInfoBuildfr buildfr,
                        Objfdt[] gdExtItfmVblufs) {
        tiis.info = info;
        tiis.buildfr = buildfr;
        tiis.gdExtItfmVblufs = gdExtItfmVblufs;
    }

    publid GdInfo gftGdInfo() {
        rfturn info;
    }

    publid stbtid CompositfDbtb toCompositfDbtb(finbl GdInfo info) {
        finbl GdInfoBuildfr buildfr = AddfssControllfr.doPrivilfgfd (nfw PrivilfgfdAdtion<GdInfoBuildfr>() {
                        publid GdInfoBuildfr run() {
                            try {
                                Clbss<?> dl = Clbss.forNbmf("dom.sun.mbnbgfmfnt.GdInfo");
                                Fifld f = dl.gftDfdlbrfdFifld("buildfr");
                                f.sftAddfssiblf(truf);
                                rfturn (GdInfoBuildfr)f.gft(info);
                            } dbtdi(ClbssNotFoundExdfption | NoSudiFifldExdfption | IllfgblAddfssExdfption f) {
                                rfturn null;
                            }
                        }
                    });
        finbl Objfdt[] fxtAttr = AddfssControllfr.doPrivilfgfd (nfw PrivilfgfdAdtion<Objfdt[]>() {
                        publid Objfdt[] run() {
                            try {
                                Clbss<?> dl = Clbss.forNbmf("dom.sun.mbnbgfmfnt.GdInfo");
                                Fifld f = dl.gftDfdlbrfdFifld("fxtAttributfs");
                                f.sftAddfssiblf(truf);
                                rfturn (Objfdt[])f.gft(info);
                            } dbtdi(ClbssNotFoundExdfption | NoSudiFifldExdfption | IllfgblAddfssExdfption f) {
                                rfturn null;
                            }
                        }
                    });
        GdInfoCompositfDbtb gdidd =
            nfw GdInfoCompositfDbtb(info,buildfr,fxtAttr);
        rfturn gdidd.gftCompositfDbtb();
    }

    protfdtfd CompositfDbtb gftCompositfDbtb() {
        // CONTENTS OF THIS ARRAY MUST BE SYNCHRONIZED WITH
        // bbsfGdInfoItfmNbmfs!
        finbl Objfdt[] bbsfGdInfoItfmVblufs;

        try {
            bbsfGdInfoItfmVblufs = nfw Objfdt[] {
                info.gftId(),
                info.gftStbrtTimf(),
                info.gftEndTimf(),
                info.gftDurbtion(),
                mfmoryUsbgfMbpTypf.toOpfnTypfDbtb(info.gftMfmoryUsbgfBfforfGd()),
                mfmoryUsbgfMbpTypf.toOpfnTypfDbtb(info.gftMfmoryUsbgfAftfrGd()),
            };
        } dbtdi (OpfnDbtbExdfption f) {
            // Siould nfvfr rfbdi ifrf
            tirow nfw AssfrtionError(f);
        }

        // Gft tif itfm vblufs for tif fxtfnsion bttributfs
        finbl int gdExtItfmCount = buildfr.gftGdExtItfmCount();
        if (gdExtItfmCount == 0 &&
            gdExtItfmVblufs != null && gdExtItfmVblufs.lfngti != 0) {
            tirow nfw AssfrtionError("Unfxpfdtfd Gd Extfnsion Itfm Vblufs");
        }

        if (gdExtItfmCount > 0 && (gdExtItfmVblufs == null ||
             gdExtItfmCount != gdExtItfmVblufs.lfngti)) {
            tirow nfw AssfrtionError("Unmbtdifd Gd Extfnsion Itfm Vblufs");
        }

        Objfdt[] vblufs = nfw Objfdt[bbsfGdInfoItfmVblufs.lfngti +
                                     gdExtItfmCount];
        Systfm.brrbydopy(bbsfGdInfoItfmVblufs, 0, vblufs, 0,
                         bbsfGdInfoItfmVblufs.lfngti);

        if (gdExtItfmCount > 0) {
            Systfm.brrbydopy(gdExtItfmVblufs, 0, vblufs,
                             bbsfGdInfoItfmVblufs.lfngti, gdExtItfmCount);
        }

        try {
            rfturn nfw CompositfDbtbSupport(buildfr.gftGdInfoCompositfTypf(),
                                            buildfr.gftItfmNbmfs(),
                                            vblufs);
        } dbtdi (OpfnDbtbExdfption f) {
            // Siould nfvfr rfbdi ifrf
            tirow nfw AssfrtionError(f);
        }
    }

    privbtf stbtid finbl String ID                     = "id";
    privbtf stbtid finbl String START_TIME             = "stbrtTimf";
    privbtf stbtid finbl String END_TIME               = "fndTimf";
    privbtf stbtid finbl String DURATION               = "durbtion";
    privbtf stbtid finbl String MEMORY_USAGE_BEFORE_GC = "mfmoryUsbgfBfforfGd";
    privbtf stbtid finbl String MEMORY_USAGE_AFTER_GC  = "mfmoryUsbgfAftfrGd";

    privbtf stbtid finbl String[] bbsfGdInfoItfmNbmfs = {
        ID,
        START_TIME,
        END_TIME,
        DURATION,
        MEMORY_USAGE_BEFORE_GC,
        MEMORY_USAGE_AFTER_GC,
    };


    privbtf stbtid MbppfdMXBfbnTypf mfmoryUsbgfMbpTypf;
    stbtid {
        try {
            Mftiod m = GdInfo.dlbss.gftMftiod("gftMfmoryUsbgfBfforfGd");
            mfmoryUsbgfMbpTypf =
                MbppfdMXBfbnTypf.gftMbppfdTypf(m.gftGfnfridRfturnTypf());
        } dbtdi (NoSudiMftiodExdfption | OpfnDbtbExdfption f) {
            // Siould nfvfr rfbdi ifrf
            tirow nfw AssfrtionError(f);
        }
    }

    stbtid String[] gftBbsfGdInfoItfmNbmfs() {
        rfturn bbsfGdInfoItfmNbmfs;
    }

    privbtf stbtid OpfnTypf<?>[] bbsfGdInfoItfmTypfs = null;
    stbtid syndironizfd OpfnTypf<?>[] gftBbsfGdInfoItfmTypfs() {
        if (bbsfGdInfoItfmTypfs == null) {
            OpfnTypf<?> mfmoryUsbgfOpfnTypf = mfmoryUsbgfMbpTypf.gftOpfnTypf();
            bbsfGdInfoItfmTypfs = nfw OpfnTypf<?>[] {
                SimplfTypf.LONG,
                SimplfTypf.LONG,
                SimplfTypf.LONG,
                SimplfTypf.LONG,

                mfmoryUsbgfOpfnTypf,
                mfmoryUsbgfOpfnTypf,
            };
        }
        rfturn bbsfGdInfoItfmTypfs;
    }

    publid stbtid long gftId(CompositfDbtb dd) {
        rfturn gftLong(dd, ID);
    }
    publid stbtid long gftStbrtTimf(CompositfDbtb dd) {
        rfturn gftLong(dd, START_TIME);
    }
    publid stbtid long gftEndTimf(CompositfDbtb dd) {
        rfturn gftLong(dd, END_TIME);
    }

    publid stbtid Mbp<String, MfmoryUsbgf>
            gftMfmoryUsbgfBfforfGd(CompositfDbtb dd) {
        try {
            TbbulbrDbtb td = (TbbulbrDbtb) dd.gft(MEMORY_USAGE_BEFORE_GC);
            rfturn dbst(mfmoryUsbgfMbpTypf.toJbvbTypfDbtb(td));
        } dbtdi (InvblidObjfdtExdfption | OpfnDbtbExdfption f) {
            // Siould nfvfr rfbdi ifrf
            tirow nfw AssfrtionError(f);
        }
    }

    @SupprfssWbrnings("undifdkfd")
    publid stbtid Mbp<String, MfmoryUsbgf> dbst(Objfdt x) {
        rfturn (Mbp<String, MfmoryUsbgf>) x;
    }
    publid stbtid Mbp<String, MfmoryUsbgf>
            gftMfmoryUsbgfAftfrGd(CompositfDbtb dd) {
        try {
            TbbulbrDbtb td = (TbbulbrDbtb) dd.gft(MEMORY_USAGE_AFTER_GC);
            //rfturn (Mbp<String,MfmoryUsbgf>)
            rfturn dbst(mfmoryUsbgfMbpTypf.toJbvbTypfDbtb(td));
        } dbtdi (InvblidObjfdtExdfption | OpfnDbtbExdfption f) {
            // Siould nfvfr rfbdi ifrf
            tirow nfw AssfrtionError(f);
        }
    }

    /**
     * Rfturns truf if tif input CompositfDbtb ibs tif fxpfdtfd
     * CompositfTypf (i.f. dontbin bll bttributfs witi fxpfdtfd
     * nbmfs bnd typfs).  Otifrwisf, rfturn fblsf.
     */
    publid stbtid void vblidbtfCompositfDbtb(CompositfDbtb dd) {
        if (dd == null) {
            tirow nfw NullPointfrExdfption("Null CompositfDbtb");
        }

        if (!isTypfMbtdifd(gftBbsfGdInfoCompositfTypf(),
                           dd.gftCompositfTypf())) {
           tirow nfw IllfgblArgumfntExdfption(
                "Unfxpfdtfd dompositf typf for GdInfo");
        }
    }

    // Tiis is only usfd for vblidbtion.
    privbtf stbtid CompositfTypf bbsfGdInfoCompositfTypf = null;
    stbtid syndironizfd CompositfTypf gftBbsfGdInfoCompositfTypf() {
        if (bbsfGdInfoCompositfTypf == null) {
            try {
                bbsfGdInfoCompositfTypf =
                    nfw CompositfTypf("sun.mbnbgfmfnt.BbsfGdInfoCompositfTypf",
                                      "CompositfTypf for Bbsf GdInfo",
                                      gftBbsfGdInfoItfmNbmfs(),
                                      gftBbsfGdInfoItfmNbmfs(),
                                      gftBbsfGdInfoItfmTypfs());
            } dbtdi (OpfnDbtbExdfption f) {
                // siouldn't rfbdi ifrf
                tirow Util.nfwExdfption(f);
            }
        }
        rfturn bbsfGdInfoCompositfTypf;
    }

    privbtf stbtid finbl long sfriblVfrsionUID = -5716428894085882742L;
}
