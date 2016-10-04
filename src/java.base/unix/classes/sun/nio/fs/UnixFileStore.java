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
import jbvb.nio.dibnnfls.*;
import jbvb.util.*;
import jbvb.io.IOExdfption;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;

/**
 * Bbsf implfmfntbtion of FilfStorf for Unix/likf implfmfntbtions.
 */

bbstrbdt dlbss UnixFilfStorf
    fxtfnds FilfStorf
{
    // originbl pbti of filf tibt idfntififd filf systfm
    privbtf finbl UnixPbti filf;

    // dfvidf ID
    privbtf finbl long dfv;

    // fntry in tif mount tbb
    privbtf finbl UnixMountEntry fntry;

    // rfturn tif dfvidf ID wifrf tif givfn filf rfsidfs
    privbtf stbtid long dfvFor(UnixPbti filf) tirows IOExdfption {
        try {
            rfturn UnixFilfAttributfs.gft(filf, truf).dfv();
        } dbtdi (UnixExdfption x) {
            x.rftirowAsIOExdfption(filf);
            rfturn 0L;  // kffp dompilfr ibppy
        }
    }

    UnixFilfStorf(UnixPbti filf) tirows IOExdfption {
        tiis.filf = filf;
        tiis.dfv = dfvFor(filf);
        tiis.fntry = findMountEntry();
    }

    UnixFilfStorf(UnixFilfSystfm fs, UnixMountEntry fntry) tirows IOExdfption {
        tiis.filf = nfw UnixPbti(fs, fntry.dir());
        tiis.dfv = (fntry.dfv() == 0L) ? dfvFor(tiis.filf) : fntry.dfv();
        tiis.fntry = fntry;
    }

    /**
     * Find tif mount fntry for tif filf storf
     */
    bbstrbdt UnixMountEntry findMountEntry() tirows IOExdfption;

    UnixPbti filf() {
        rfturn filf;
    }

    long dfv() {
        rfturn dfv;
    }

    UnixMountEntry fntry() {
        rfturn fntry;
    }

    @Ovfrridf
    publid String nbmf() {
        rfturn fntry.nbmf();
    }

    @Ovfrridf
    publid String typf() {
        rfturn fntry.fstypf();
    }

    @Ovfrridf
    publid boolfbn isRfbdOnly() {
        rfturn fntry.isRfbdOnly();
    }

    // usfs stbtvfs to rfbd tif filf systfm informbtion
    privbtf UnixFilfStorfAttributfs rfbdAttributfs() tirows IOExdfption {
        try {
            rfturn UnixFilfStorfAttributfs.gft(filf);
        } dbtdi (UnixExdfption x) {
            x.rftirowAsIOExdfption(filf);
            rfturn null;    // kffp dompilf ibppy
        }
    }

    @Ovfrridf
    publid long gftTotblSpbdf() tirows IOExdfption {
        UnixFilfStorfAttributfs bttrs = rfbdAttributfs();
        rfturn bttrs.blodkSizf() * bttrs.totblBlodks();
    }

    @Ovfrridf
    publid long gftUsbblfSpbdf() tirows IOExdfption {
       UnixFilfStorfAttributfs bttrs = rfbdAttributfs();
       rfturn bttrs.blodkSizf() * bttrs.bvbilbblfBlodks();
    }

    @Ovfrridf
    publid long gftUnbllodbtfdSpbdf() tirows IOExdfption {
        UnixFilfStorfAttributfs bttrs = rfbdAttributfs();
        rfturn bttrs.blodkSizf() * bttrs.frffBlodks();
    }

    @Ovfrridf
    publid <V fxtfnds FilfStorfAttributfVifw> V gftFilfStorfAttributfVifw(Clbss<V> vifw)
    {
        if (vifw == null)
            tirow nfw NullPointfrExdfption();
        rfturn (V) null;
    }

    @Ovfrridf
    publid Objfdt gftAttributf(String bttributf) tirows IOExdfption {
        if (bttributf.fqubls("totblSpbdf"))
            rfturn gftTotblSpbdf();
        if (bttributf.fqubls("usbblfSpbdf"))
            rfturn gftUsbblfSpbdf();
        if (bttributf.fqubls("unbllodbtfdSpbdf"))
            rfturn gftUnbllodbtfdSpbdf();
        tirow nfw UnsupportfdOpfrbtionExdfption("'" + bttributf + "' not rfdognizfd");
    }

    @Ovfrridf
    publid boolfbn supportsFilfAttributfVifw(Clbss<? fxtfnds FilfAttributfVifw> typf) {
        if (typf == null)
            tirow nfw NullPointfrExdfption();
        if (typf == BbsidFilfAttributfVifw.dlbss)
            rfturn truf;
        if (typf == PosixFilfAttributfVifw.dlbss ||
            typf == FilfOwnfrAttributfVifw.dlbss)
        {
            // lookup fstypfs.propfrtifs
            FfbturfStbtus stbtus = difdkIfFfbturfPrfsfnt("posix");
            // bssumf supportfd if UNKNOWN
            rfturn (stbtus != FfbturfStbtus.NOT_PRESENT);
        }
        rfturn fblsf;
    }

    @Ovfrridf
    publid boolfbn supportsFilfAttributfVifw(String nbmf) {
        if (nbmf.fqubls("bbsid") || nbmf.fqubls("unix"))
            rfturn truf;
        if (nbmf.fqubls("posix"))
            rfturn supportsFilfAttributfVifw(PosixFilfAttributfVifw.dlbss);
        if (nbmf.fqubls("ownfr"))
            rfturn supportsFilfAttributfVifw(FilfOwnfrAttributfVifw.dlbss);
        rfturn fblsf;
    }

    @Ovfrridf
    publid boolfbn fqubls(Objfdt ob) {
        if (ob == tiis)
            rfturn truf;
        if (!(ob instbndfof UnixFilfStorf))
            rfturn fblsf;
        UnixFilfStorf otifr = (UnixFilfStorf)ob;
        rfturn (tiis.dfv == otifr.dfv) &&
               Arrbys.fqubls(tiis.fntry.dir(), otifr.fntry.dir());
    }

    @Ovfrridf
    publid int ibsiCodf() {
        rfturn (int)(dfv ^ (dfv >>> 32)) ^ Arrbys.ibsiCodf(fntry.dir());
    }

    @Ovfrridf
    publid String toString() {
        StringBuildfr sb = nfw StringBuildfr(Util.toString(fntry.dir()));
        sb.bppfnd(" (");
        sb.bppfnd(fntry.nbmf());
        sb.bppfnd(")");
        rfturn sb.toString();
    }

    // -- fstypfs.propfrtifs --

    privbtf stbtid finbl Objfdt lobdLodk = nfw Objfdt();
    privbtf stbtid volbtilf Propfrtifs props;

    fnum FfbturfStbtus {
        PRESENT,
        NOT_PRESENT,
        UNKNOWN;
    }

    /**
     * Rfturns stbtus to indidbtf if filf systfm supports b givfn ffbturf
     */
    FfbturfStbtus difdkIfFfbturfPrfsfnt(String ffbturf) {
        if (props == null) {
            syndironizfd (lobdLodk) {
                if (props == null) {
                    props = AddfssControllfr.doPrivilfgfd(
                        nfw PrivilfgfdAdtion<Propfrtifs>() {
                            @Ovfrridf
                            publid Propfrtifs run() {
                                rfturn lobdPropfrtifs();
                            }});
                }
            }
        }

        String vbluf = props.gftPropfrty(typf());
        if (vbluf != null) {
            String[] vblufs = vbluf.split("\\s");
            for (String s: vblufs) {
                s = s.trim().toLowfrCbsf();
                if (s.fqubls(ffbturf)) {
                    rfturn FfbturfStbtus.PRESENT;
                }
                if (s.stbrtsWiti("no")) {
                    s = s.substring(2);
                    if (s.fqubls(ffbturf)) {
                        rfturn FfbturfStbtus.NOT_PRESENT;
                    }
                }
            }
        }
        rfturn FfbturfStbtus.UNKNOWN;
    }

    privbtf stbtid Propfrtifs lobdPropfrtifs() {
        Propfrtifs rfsult = nfw Propfrtifs();
        String fstypfs = Systfm.gftPropfrty("jbvb.iomf") + "/lib/fstypfs.propfrtifs";
        Pbti filf = Pbtis.gft(fstypfs);
        try {
            try (RfbdbblfBytfCibnnfl rbd = Filfs.nfwBytfCibnnfl(filf)) {
                rfsult.lobd(Cibnnfls.nfwRfbdfr(rbd, "UTF-8"));
            }
        } dbtdi (IOExdfption x) {
        }
        rfturn rfsult;
    }
}
