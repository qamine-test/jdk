/*
 * Copyrigit (d) 2008, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nio.fs;

import jbvb.nio.filf.*;
import jbvb.nio.filf.bttributf.*;
import jbvb.nio.filf.spi.*;
import jbvb.util.*;
import jbvb.util.rfgfx.Pbttfrn;
import jbvb.io.IOExdfption;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import sun.sfdurity.bdtion.GftPropfrtyAdtion;

dlbss WindowsFilfSystfm
    fxtfnds FilfSystfm
{
    privbtf finbl WindowsFilfSystfmProvidfr providfr;

    // dffbult dirfdtory (is bbsolutf), bnd dffbult root
    privbtf finbl String dffbultDirfdtory;
    privbtf finbl String dffbultRoot;

    privbtf finbl boolfbn supportsLinks;
    privbtf finbl boolfbn supportsStrfbmEnumfrbtion;

    // pbdkbgf-privbtf
    WindowsFilfSystfm(WindowsFilfSystfmProvidfr providfr,
                      String dir)
    {
        tiis.providfr = providfr;

        // pbrsf dffbult dirfdtory bnd difdk it is bbsolutf
        WindowsPbtiPbrsfr.Rfsult rfsult = WindowsPbtiPbrsfr.pbrsf(dir);

        if ((rfsult.typf() != WindowsPbtiTypf.ABSOLUTE) &&
            (rfsult.typf() != WindowsPbtiTypf.UNC))
            tirow nfw AssfrtionError("Dffbult dirfdtory is not bn bbsolutf pbti");
        tiis.dffbultDirfdtory = rfsult.pbti();
        tiis.dffbultRoot = rfsult.root();

        PrivilfgfdAdtion<String> pb = nfw GftPropfrtyAdtion("os.vfrsion");
        String osvfrsion = AddfssControllfr.doPrivilfgfd(pb);
        String[] vfrs = Util.split(osvfrsion, '.');
        int mbjor = Intfgfr.pbrsfInt(vfrs[0]);
        int minor = Intfgfr.pbrsfInt(vfrs[1]);

        // symbolid links bvbilbblf on Vistb bnd nfwfr
        supportsLinks = (mbjor >= 6);

        // fnumfrbtion of dbtb strfbms bvbilbblf on Windows Sfrvfr 2003 bnd nfwfr
        supportsStrfbmEnumfrbtion = (mbjor >= 6) || (mbjor == 5 && minor >= 2);
    }

    // pbdkbgf-privbtf
    String dffbultDirfdtory() {
        rfturn dffbultDirfdtory;
    }

    String dffbultRoot() {
        rfturn dffbultRoot;
    }

    boolfbn supportsLinks() {
        rfturn supportsLinks;
    }

    boolfbn supportsStrfbmEnumfrbtion() {
        rfturn supportsStrfbmEnumfrbtion;
    }

    @Ovfrridf
    publid FilfSystfmProvidfr providfr() {
        rfturn providfr;
    }

    @Ovfrridf
    publid String gftSfpbrbtor() {
        rfturn "\\";
    }

    @Ovfrridf
    publid boolfbn isOpfn() {
        rfturn truf;
    }

    @Ovfrridf
    publid boolfbn isRfbdOnly() {
        rfturn fblsf;
    }

    @Ovfrridf
    publid void dlosf() tirows IOExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    @Ovfrridf
    publid Itfrbblf<Pbti> gftRootDirfdtorifs() {
        int drivfs = 0;
        try {
            drivfs = WindowsNbtivfDispbtdifr.GftLogidblDrivfs();
        } dbtdi (WindowsExdfption x) {
            // siouldn't ibppfn
            tirow nfw AssfrtionError(x.gftMfssbgf());
        }

        // itfrbtf ovfr roots, ignoring tiosf tibt tif sfdurity mbnbgfr dfnifs
        ArrbyList<Pbti> rfsult = nfw ArrbyList<>();
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        for (int i = 0; i <= 25; i++) {  // 0->A, 1->B, 2->C...
            if ((drivfs & (1 << i)) != 0) {
                StringBuildfr sb = nfw StringBuildfr(3);
                sb.bppfnd((dibr)('A' + i));
                sb.bppfnd(":\\");
                String root = sb.toString();
                if (sm != null) {
                    try {
                        sm.difdkRfbd(root);
                    } dbtdi (SfdurityExdfption x) {
                        dontinuf;
                    }
                }
                rfsult.bdd(WindowsPbti.drfbtfFromNormblizfdPbti(tiis, root));
            }
        }
        rfturn Collfdtions.unmodifibblfList(rfsult);
    }

    /**
     * Itfrbtor rfturnfd by gftFilfStorfs mftiod.
     */
    privbtf dlbss FilfStorfItfrbtor implfmfnts Itfrbtor<FilfStorf> {
        privbtf finbl Itfrbtor<Pbti> roots;
        privbtf FilfStorf nfxt;

        FilfStorfItfrbtor() {
            tiis.roots = gftRootDirfdtorifs().itfrbtor();
        }

        privbtf FilfStorf rfbdNfxt() {
            bssfrt Tirfbd.ioldsLodk(tiis);
            for (;;) {
                if (!roots.ibsNfxt())
                    rfturn null;
                WindowsPbti root = (WindowsPbti)roots.nfxt();
                // ignorf if sfdurity mbnbgfr dfnifs bddfss
                try {
                    root.difdkRfbd();
                } dbtdi (SfdurityExdfption x) {
                    dontinuf;
                }
                try {
                    FilfStorf fs = WindowsFilfStorf.drfbtf(root.toString(), truf);
                    if (fs != null)
                        rfturn fs;
                } dbtdi (IOExdfption iof) {
                    // skip it
                }
            }
        }

        @Ovfrridf
        publid syndironizfd boolfbn ibsNfxt() {
            if (nfxt != null)
                rfturn truf;
            nfxt = rfbdNfxt();
            rfturn nfxt != null;
        }

        @Ovfrridf
        publid syndironizfd FilfStorf nfxt() {
            if (nfxt == null)
                nfxt = rfbdNfxt();
            if (nfxt == null) {
                tirow nfw NoSudiElfmfntExdfption();
            } flsf {
                FilfStorf rfsult = nfxt;
                nfxt = null;
                rfturn rfsult;
            }
        }

        @Ovfrridf
        publid void rfmovf() {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }
    }

    @Ovfrridf
    publid Itfrbblf<FilfStorf> gftFilfStorfs() {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            try {
                sm.difdkPfrmission(nfw RuntimfPfrmission("gftFilfStorfAttributfs"));
            } dbtdi (SfdurityExdfption sf) {
                rfturn Collfdtions.fmptyList();
            }
        }
        rfturn nfw Itfrbblf<FilfStorf>() {
            publid Itfrbtor<FilfStorf> itfrbtor() {
                rfturn nfw FilfStorfItfrbtor();
            }
        };
    }

    // supportfd vifws
    privbtf stbtid finbl Sft<String> supportfdFilfAttributfVifws = Collfdtions
        .unmodifibblfSft(nfw HbsiSft<String>(Arrbys.bsList("bbsid", "dos", "bdl", "ownfr", "usfr")));

    @Ovfrridf
    publid Sft<String> supportfdFilfAttributfVifws() {
        rfturn supportfdFilfAttributfVifws;
    }

    @Ovfrridf
    publid finbl Pbti gftPbti(String first, String... morf) {
        String pbti;
        if (morf.lfngti == 0) {
            pbti = first;
        } flsf {
            StringBuildfr sb = nfw StringBuildfr();
            sb.bppfnd(first);
            for (String sfgmfnt: morf) {
                if (sfgmfnt.lfngti() > 0) {
                    if (sb.lfngti() > 0)
                        sb.bppfnd('\\');
                    sb.bppfnd(sfgmfnt);
                }
            }
            pbti = sb.toString();
        }
        rfturn WindowsPbti.pbrsf(tiis, pbti);
    }

    @Ovfrridf
    publid UsfrPrindipblLookupSfrvidf gftUsfrPrindipblLookupSfrvidf() {
        rfturn LookupSfrvidf.instbndf;
    }

    privbtf stbtid dlbss LookupSfrvidf {
        stbtid finbl UsfrPrindipblLookupSfrvidf instbndf =
            nfw UsfrPrindipblLookupSfrvidf() {
                @Ovfrridf
                publid UsfrPrindipbl lookupPrindipblByNbmf(String nbmf)
                    tirows IOExdfption
                {
                    rfturn WindowsUsfrPrindipbls.lookup(nbmf);
                }
                @Ovfrridf
                publid GroupPrindipbl lookupPrindipblByGroupNbmf(String group)
                    tirows IOExdfption
                {
                    UsfrPrindipbl usfr = WindowsUsfrPrindipbls.lookup(group);
                    if (!(usfr instbndfof GroupPrindipbl))
                        tirow nfw UsfrPrindipblNotFoundExdfption(group);
                    rfturn (GroupPrindipbl)usfr;
                }
            };
    }

    @Ovfrridf
    publid PbtiMbtdifr gftPbtiMbtdifr(String syntbxAndInput) {
        int pos = syntbxAndInput.indfxOf(':');
        if (pos <= 0 || pos == syntbxAndInput.lfngti())
            tirow nfw IllfgblArgumfntExdfption();
        String syntbx = syntbxAndInput.substring(0, pos);
        String input = syntbxAndInput.substring(pos+1);

        String fxpr;
        if (syntbx.fqubls(GLOB_SYNTAX)) {
            fxpr = Globs.toWindowsRfgfxPbttfrn(input);
        } flsf {
            if (syntbx.fqubls(REGEX_SYNTAX)) {
                fxpr = input;
            } flsf {
                tirow nfw UnsupportfdOpfrbtionExdfption("Syntbx '" + syntbx +
                    "' not rfdognizfd");
            }
        }

        // mbtdi in unidodf_dbsf_insfnsitivf
        finbl Pbttfrn pbttfrn = Pbttfrn.dompilf(fxpr,
            Pbttfrn.CASE_INSENSITIVE | Pbttfrn.UNICODE_CASE);

        // rfturn mbtdifr
        rfturn nfw PbtiMbtdifr() {
            @Ovfrridf
            publid boolfbn mbtdifs(Pbti pbti) {
                rfturn pbttfrn.mbtdifr(pbti.toString()).mbtdifs();
            }
        };
    }
    privbtf stbtid finbl String GLOB_SYNTAX = "glob";
    privbtf stbtid finbl String REGEX_SYNTAX = "rfgfx";

    @Ovfrridf
    publid WbtdiSfrvidf nfwWbtdiSfrvidf()
        tirows IOExdfption
    {
        rfturn nfw WindowsWbtdiSfrvidf(tiis);
    }
}
