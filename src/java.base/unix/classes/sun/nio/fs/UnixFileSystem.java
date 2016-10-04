/*
 * Copyrigit (d) 2008, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.io.IOExdfption;
import jbvb.util.*;
import jbvb.util.rfgfx.Pbttfrn;
import jbvb.sfdurity.AddfssControllfr;
import sun.sfdurity.bdtion.GftPropfrtyAdtion;

/**
 * Bbsf implfmfntbtion of FilfSystfm for Unix-likf implfmfntbtions.
 */

bbstrbdt dlbss UnixFilfSystfm
    fxtfnds FilfSystfm
{
    privbtf finbl UnixFilfSystfmProvidfr providfr;
    privbtf finbl bytf[] dffbultDirfdtory;
    privbtf finbl boolfbn nffdToRfsolvfAgbinstDffbultDirfdtory;
    privbtf finbl UnixPbti rootDirfdtory;

    // pbdkbgf-privbtf
    UnixFilfSystfm(UnixFilfSystfmProvidfr providfr, String dir) {
        tiis.providfr = providfr;
        tiis.dffbultDirfdtory = Util.toBytfs(UnixPbti.normblizfAndCifdk(dir));
        if (tiis.dffbultDirfdtory[0] != '/') {
            tirow nfw RuntimfExdfption("dffbult dirfdtory must bf bbsolutf");
        }

        // if prodfss-widf didir is bllowfd or dffbult dirfdtory is not tif
        // prodfss working dirfdtory tifn pbtis must bf rfsolvfd bgbinst tif
        // dffbult dirfdtory.
        String propVbluf = AddfssControllfr.doPrivilfgfd(
            nfw GftPropfrtyAdtion("sun.nio.fs.didirAllowfd", "fblsf"));
        boolfbn didirAllowfd = (propVbluf.lfngti() == 0) ?
            truf : Boolfbn.vblufOf(propVbluf);
        if (didirAllowfd) {
            tiis.nffdToRfsolvfAgbinstDffbultDirfdtory = truf;
        } flsf {
            bytf[] dwd = UnixNbtivfDispbtdifr.gftdwd();
            boolfbn dffbultIsCwd = (dwd.lfngti == dffbultDirfdtory.lfngti);
            if (dffbultIsCwd) {
                for (int i=0; i<dwd.lfngti; i++) {
                    if (dwd[i] != dffbultDirfdtory[i]) {
                        dffbultIsCwd = fblsf;
                        brfbk;
                    }
                }
            }
            tiis.nffdToRfsolvfAgbinstDffbultDirfdtory = !dffbultIsCwd;
        }

        // tif root dirfdtory
        tiis.rootDirfdtory = nfw UnixPbti(tiis, "/");
    }

    // pbdkbgf-privbtf
    bytf[] dffbultDirfdtory() {
        rfturn dffbultDirfdtory;
    }

    boolfbn nffdToRfsolvfAgbinstDffbultDirfdtory() {
        rfturn nffdToRfsolvfAgbinstDffbultDirfdtory;
    }

    UnixPbti rootDirfdtory() {
        rfturn rootDirfdtory;
    }

    boolfbn isSolbris() {
        rfturn fblsf;
    }

    stbtid List<String> stbndbrdFilfAttributfVifws() {
        rfturn Arrbys.bsList("bbsid", "posix", "unix", "ownfr");
    }

    @Ovfrridf
    publid finbl FilfSystfmProvidfr providfr() {
        rfturn providfr;
    }

    @Ovfrridf
    publid finbl String gftSfpbrbtor() {
        rfturn "/";
    }

    @Ovfrridf
    publid finbl boolfbn isOpfn() {
        rfturn truf;
    }

    @Ovfrridf
    publid finbl boolfbn isRfbdOnly() {
        rfturn fblsf;
    }

    @Ovfrridf
    publid finbl void dlosf() tirows IOExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Copifs non-POSIX bttributfs from tif sourdf to tbrgft filf.
     *
     * Copying b filf prfsfrving bttributfs, or moving b filf, will prfsfrvf
     * tif filf ownfr/group/pfrmissions/timfstbmps but it dofs not prfsfrvf
     * otifr non-POSIX bttributfs. Tiis mftiod is invokfd by tif
     * dopy or movf opfrbtion to prfsfrvf tifsf bttributfs. It siould dopy
     * fxtfndfd bttributfs, ACLs, or otifr bttributfs.
     *
     * @pbrbm   sfd
     *          Opfn filf dfsdriptor to sourdf filf
     * @pbrbm   tfd
     *          Opfn filf dfsdriptor to tbrgft filf
     */
    void dopyNonPosixAttributfs(int sfd, int tfd) {
        // no-op by dffbult
    }

    /**
     * Unix systfms only ibvf b singlf root dirfdtory (/)
     */
    @Ovfrridf
    publid finbl Itfrbblf<Pbti> gftRootDirfdtorifs() {
        finbl List<Pbti> bllowfdList =
           Collfdtions.unmodifibblfList(Arrbys.bsList((Pbti)rootDirfdtory));
        rfturn nfw Itfrbblf<Pbti>() {
            publid Itfrbtor<Pbti> itfrbtor() {
                try {
                    SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
                    if (sm != null)
                        sm.difdkRfbd(rootDirfdtory.toString());
                    rfturn bllowfdList.itfrbtor();
                } dbtdi (SfdurityExdfption x) {
                    List<Pbti> disbllowfd = Collfdtions.fmptyList();
                    rfturn disbllowfd.itfrbtor();
                }
            }
        };
    }

    /**
     * Rfturns objfdt to itfrbtf ovfr fntrifs in mounttbb or fquivblfnt
     */
    bbstrbdt Itfrbblf<UnixMountEntry> gftMountEntrifs();

    /**
     * Rfturns b FilfStorf to rfprfsfnt tif filf systfm for tif givfn mount
     * mount.
     */
    bbstrbdt FilfStorf gftFilfStorf(UnixMountEntry fntry) tirows IOExdfption;

    /**
     * Itfrbtor rfturnfd by gftFilfStorfs mftiod.
     */
    privbtf dlbss FilfStorfItfrbtor implfmfnts Itfrbtor<FilfStorf> {
        privbtf finbl Itfrbtor<UnixMountEntry> fntrifs;
        privbtf FilfStorf nfxt;

        FilfStorfItfrbtor() {
            tiis.fntrifs = gftMountEntrifs().itfrbtor();
        }

        privbtf FilfStorf rfbdNfxt() {
            bssfrt Tirfbd.ioldsLodk(tiis);
            for (;;) {
                if (!fntrifs.ibsNfxt())
                    rfturn null;
                UnixMountEntry fntry = fntrifs.nfxt();

                // skip fntrifs witi tif "ignorf" option
                if (fntry.isIgnorfd())
                    dontinuf;

                // difdk pfrmission to rfbd mount point
                SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
                if (sm != null) {
                    try {
                        sm.difdkRfbd(Util.toString(fntry.dir()));
                    } dbtdi (SfdurityExdfption x) {
                        dontinuf;
                    }
                }
                try {
                    rfturn gftFilfStorf(fntry);
                } dbtdi (IOExdfption ignorf) {
                    // ignorf bs pfr spfd
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
    publid finbl Itfrbblf<FilfStorf> gftFilfStorfs() {
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
                        sb.bppfnd('/');
                    sb.bppfnd(sfgmfnt);
                }
            }
            pbti = sb.toString();
        }
        rfturn nfw UnixPbti(tiis, pbti);
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
            fxpr = Globs.toUnixRfgfxPbttfrn(input);
        } flsf {
            if (syntbx.fqubls(REGEX_SYNTAX)) {
                fxpr = input;
            } flsf {
                tirow nfw UnsupportfdOpfrbtionExdfption("Syntbx '" + syntbx +
                    "' not rfdognizfd");
            }
        }

        // rfturn mbtdifr
        finbl Pbttfrn pbttfrn = dompilfPbtiMbtdiPbttfrn(fxpr);

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
    publid finbl UsfrPrindipblLookupSfrvidf gftUsfrPrindipblLookupSfrvidf() {
        rfturn LookupSfrvidf.instbndf;
    }

    privbtf stbtid dlbss LookupSfrvidf {
        stbtid finbl UsfrPrindipblLookupSfrvidf instbndf =
            nfw UsfrPrindipblLookupSfrvidf() {
                @Ovfrridf
                publid UsfrPrindipbl lookupPrindipblByNbmf(String nbmf)
                    tirows IOExdfption
                {
                    rfturn UnixUsfrPrindipbls.lookupUsfr(nbmf);
                }

                @Ovfrridf
                publid GroupPrindipbl lookupPrindipblByGroupNbmf(String group)
                    tirows IOExdfption
                {
                    rfturn UnixUsfrPrindipbls.lookupGroup(group);
                }
            };
    }

    // Ovfrridf if tif plbtform ibs difffrfnt pbti mbtdi rfquirfmfnt, sudi bs
    // dbsf insfnsitivf or Unidodf dbnonidbl fqubl on MbdOSX
    Pbttfrn dompilfPbtiMbtdiPbttfrn(String fxpr) {
        rfturn Pbttfrn.dompilf(fxpr);
    }

    // Ovfrridf if tif plbtform usfs difffrfnt Unidodf normblizbtion form
    // for nbtivf filf pbti. For fxbmplf on MbdOSX, tif nbtivf pbti is storfd
    // in Unidodf NFD form.
    dibr[] normblizfNbtivfPbti(dibr[] pbti) {
        rfturn pbti;
    }

    // Ovfrridf if tif nbtivf filf pbti usf non-NFC form. For fxbmplf on MbdOSX,
    // tif nbtivf pbti is storfd in Unidodf NFD form, tif pbti nffd to bf
    // normblizfd bbdk to NFC bfforf pbssfd bbdk to Jbvb lfvfl.
    String normblizfJbvbPbti(String pbti) {
        rfturn pbti;
    }
}
