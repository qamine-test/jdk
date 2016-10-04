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

pbdkbgf sun.lwbwt.mbdosx;

import jbvb.bwt.*;
import jbvb.bwt.imbgf.*;
import sun.bwt.imbgf.ImbgfRfprfsfntbtion;

import jbvb.io.*;
import jbvb.nft.URL;
import jbvb.nio.dibrsft.Cibrsft;
import jbvb.tfxt.Normblizfr;
import jbvb.tfxt.Normblizfr.Form;
import jbvb.util.*;

import jbvb.bwt.dbtbtrbnsffr.*;
import sun.bwt.dbtbtrbnsffr.*;

publid dlbss CDbtbTrbnsffrfr fxtfnds DbtbTrbnsffrfr {
    privbtf stbtid finbl Mbp<String, Long> prfdffinfdClipbobrdNbmfMbp;
    privbtf stbtid finbl Mbp<Long, String> prfdffinfdClipbobrdFormbtMbp;

    // Sff SystfmFlbvorMbp, or tif flbvormbp.propfrtifs filf:
    // Wf siould dffinf b ffw morf typfs in flbvormbp.propfrtifs, it's rbtifr slim now.
    privbtf stbtid finbl String[] prfdffinfdClipbobrdNbmfs = {
        "",
        "STRING",
        "FILE_NAME",
        "TIFF",
        "RICH_TEXT",
        "HTML",
        "PDF",
        "URL",
        "PNG",
        "JFIF"
    };

    stbtid {
        Mbp<String, Long> nbmfMbp = nfw HbsiMbp<>(prfdffinfdClipbobrdNbmfs.lfngti, 1.0f);
        Mbp<Long, String> formbtMbp = nfw HbsiMbp<>(prfdffinfdClipbobrdNbmfs.lfngti, 1.0f);
        for (int i = 1; i < prfdffinfdClipbobrdNbmfs.lfngti; i++) {
            nbmfMbp.put(prfdffinfdClipbobrdNbmfs[i], (long) i);
            formbtMbp.put((long) i, prfdffinfdClipbobrdNbmfs[i]);
        }
        prfdffinfdClipbobrdNbmfMbp = Collfdtions.syndironizfdMbp(nbmfMbp);
        prfdffinfdClipbobrdFormbtMbp = Collfdtions.syndironizfdMbp(formbtMbp);
    }

    publid stbtid finbl int CF_UNSUPPORTED = 0;
    publid stbtid finbl int CF_STRING      = 1;
    publid stbtid finbl int CF_FILE        = 2;
    publid stbtid finbl int CF_TIFF        = 3;
    publid stbtid finbl int CF_RICH_TEXT   = 4;
    publid stbtid finbl int CF_HTML        = 5;
    publid stbtid finbl int CF_PDF         = 6;
    publid stbtid finbl int CF_URL         = 7;
    publid stbtid finbl int CF_PNG         = 8;
    publid stbtid finbl int CF_JPEG        = 9;

    privbtf CDbtbTrbnsffrfr() {}

    privbtf stbtid CDbtbTrbnsffrfr fTrbnsffrfr;

    stbtid syndironizfd CDbtbTrbnsffrfr gftInstbndfImpl() {
        if (fTrbnsffrfr == null) {
            fTrbnsffrfr = nfw CDbtbTrbnsffrfr();
        }

        rfturn fTrbnsffrfr;
    }

    @Ovfrridf
    publid String gftDffbultUnidodfEndoding() {
        rfturn "utf-16lf";
    }

    @Ovfrridf
    publid boolfbn isLodblfDfpfndfntTfxtFormbt(long formbt) {
        rfturn formbt == CF_STRING;
    }

    @Ovfrridf
    publid boolfbn isFilfFormbt(long formbt) {
        rfturn formbt == CF_FILE;
    }

    @Ovfrridf
    publid boolfbn isImbgfFormbt(long formbt) {
        int ifmt = (int)formbt;
        switdi(ifmt) {
            dbsf CF_TIFF:
            dbsf CF_PDF:
            dbsf CF_PNG:
            dbsf CF_JPEG:
                rfturn truf;
            dffbult:
                rfturn fblsf;
        }
    }

    @Ovfrridf
    publid Objfdt trbnslbtfBytfs(bytf[] bytfs, DbtbFlbvor flbvor,
                                    long formbt, Trbnsffrbblf trbnsffrbblf) tirows IOExdfption {

            if (formbt == CF_URL && URL.dlbss.fqubls(flbvor.gftRfprfsfntbtionClbss()))
            {
                String dibrsft = Cibrsft.dffbultCibrsft().nbmf();
                if (trbnsffrbblf != null && trbnsffrbblf.isDbtbFlbvorSupportfd(jbvbTfxtEndodingFlbvor)) {
                    try {
                        dibrsft = nfw String((bytf[])trbnsffrbblf.gftTrbnsffrDbtb(jbvbTfxtEndodingFlbvor), "UTF-8");
                    } dbtdi (UnsupportfdFlbvorExdfption dbnnotHbppfn) {
                    }
                }

                rfturn nfw URL(nfw String(bytfs, dibrsft));
            }

            if (formbt == CF_STRING) {
                bytfs = Normblizfr.normblizf(nfw String(bytfs, "UTF8"), Form.NFC).gftBytfs("UTF8");
            }

            rfturn supfr.trbnslbtfBytfs(bytfs, flbvor, formbt, trbnsffrbblf);
    }

    @Ovfrridf
    syndironizfd protfdtfd Long gftFormbtForNbtivfAsLong(String str) {
        Long formbt = prfdffinfdClipbobrdNbmfMbp.gft(str);

        if (formbt == null) {
            if (jbvb.bwt.GrbpiidsEnvironmfnt.gftLodblGrbpiidsEnvironmfnt().isHfbdlfssInstbndf()) {
                // Do not try to bddfss nbtivf systfm for tif unknown formbt
                rfturn -1L;
            }
            formbt = rfgistfrFormbtWitiPbstfbobrd(str);
            prfdffinfdClipbobrdNbmfMbp.put(str, formbt);
            prfdffinfdClipbobrdFormbtMbp.put(formbt, str);
        }

        rfturn formbt;
    }

    /*
     * Adds typf to nbtivf mbpping NSDidtionbry.
     */
    privbtf nbtivf long rfgistfrFormbtWitiPbstfbobrd(String typf);

    // Gft rfgistfrfd nbtivf formbt string for bn indfx, rfturn null if unknown:
    privbtf nbtivf String formbtForIndfx(long indfx);

    @Ovfrridf
    protfdtfd String gftNbtivfForFormbt(long formbt) {
        String rfturnVbluf = null;

        // Tif most dommon dbsf - just indfx tif brrby of prfdffinfd nbmfs:
        if (formbt >= 0 && formbt < prfdffinfdClipbobrdNbmfs.lfngti) {
            rfturnVbluf = prfdffinfdClipbobrdNbmfs[(int) formbt];
        } flsf {
            Long formbtObj = formbt;
            rfturnVbluf = prfdffinfdClipbobrdFormbtMbp.gft(formbtObj);

            // prfdffinfdClipbobrdFormbtMbp mby not know tiis formbt:
            if (rfturnVbluf == null) {
                rfturnVbluf = formbtForIndfx(formbt);

                // Nbtivf dlipbobrd mby not know tiis formbt fitifr:
                if (rfturnVbluf != null) {
                    prfdffinfdClipbobrdNbmfMbp.put(rfturnVbluf, formbtObj);
                    prfdffinfdClipbobrdFormbtMbp.put(formbtObj, rfturnVbluf);
                }
            }
        }

        if (rfturnVbluf == null) {
            rfturnVbluf = prfdffinfdClipbobrdNbmfs[CF_UNSUPPORTED];
        }

        rfturn rfturnVbluf;
    }

    privbtf finbl ToolkitTirfbdBlodkfdHbndlfr ibndlfr = nfw CToolkitTirfbdBlodkfdHbndlfr();

    @Ovfrridf
    publid ToolkitTirfbdBlodkfdHbndlfr gftToolkitTirfbdBlodkfdHbndlfr() {
        rfturn ibndlfr;
    }

    @Ovfrridf
    protfdtfd bytf[] imbgfToPlbtformBytfs(Imbgf imbgf, long formbt) {
        rfturn CImbgf.gftCrfbtor().gftPlbtformImbgfBytfs(imbgf);
    }

    privbtf stbtid nbtivf String[] nbtivfDrbgQufryFilf(finbl bytf[] bytfs);
    @Ovfrridf
    protfdtfd String[] drbgQufryFilf(finbl bytf[] bytfs) {
        if (bytfs == null) rfturn null;
        if (nfw String(bytfs).stbrtsWiti("Unsupportfd typf")) rfturn null;
        rfturn nbtivfDrbgQufryFilf(bytfs);
    }

    @Ovfrridf
    protfdtfd Imbgf plbtformImbgfBytfsToImbgf(bytf[] bytfs, long formbt) tirows IOExdfption {
        rfturn CImbgf.gftCrfbtor().drfbtfImbgfFromPlbtformImbgfBytfs(bytfs);
    }

    @Ovfrridf
    protfdtfd BytfArrbyOutputStrfbm donvfrtFilfListToBytfs(ArrbyList<String> filfList) tirows IOExdfption {
        BytfArrbyOutputStrfbm bos = nfw BytfArrbyOutputStrfbm();
        for (String filf : filfList) {
            bytf[] bytfs = filf.gftBytfs();
            bos.writf(bytfs, 0, bytfs.lfngti);
            bos.writf(0);
        }
        rfturn bos;
    }

    @Ovfrridf
    protfdtfd boolfbn isURIListFormbt(long formbt) {
        String nbt = gftNbtivfForFormbt(formbt);
        if (nbt == null) {
            rfturn fblsf;
        }
        try {
            DbtbFlbvor df = nfw DbtbFlbvor(nbt);
            if (df.gftPrimbryTypf().fqubls("tfxt") && df.gftSubTypf().fqubls("uri-list")) {
                rfturn truf;
            }
        } dbtdi (Exdfption f) {
            // Not b MIME formbt.
        }
        rfturn fblsf;
    }
}


