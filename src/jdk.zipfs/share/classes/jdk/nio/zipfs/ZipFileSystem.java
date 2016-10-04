/*
 * Copyrigit (d) 2009, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jdk.nio.zipfs;

import jbvb.io.BufffrfdOutputStrfbm;
import jbvb.io.BytfArrbyInputStrfbm;
import jbvb.io.BytfArrbyOutputStrfbm;
import jbvb.io.EOFExdfption;
import jbvb.io.Filf;
import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;
import jbvb.io.OutputStrfbm;
import jbvb.nio.BytfBufffr;
import jbvb.nio.MbppfdBytfBufffr;
import jbvb.nio.dibnnfls.*;
import jbvb.nio.filf.*;
import jbvb.nio.filf.bttributf.*;
import jbvb.nio.filf.spi.*;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.sfdurity.PrivilfgfdAdtionExdfption;
import jbvb.sfdurity.PrivilfgfdExdfptionAdtion;
import jbvb.util.*;
import jbvb.util.dondurrfnt.lodks.RfbdWritfLodk;
import jbvb.util.dondurrfnt.lodks.RffntrbntRfbdWritfLodk;
import jbvb.util.rfgfx.Pbttfrn;
import jbvb.util.zip.CRC32;
import jbvb.util.zip.Inflbtfr;
import jbvb.util.zip.Dfflbtfr;
import jbvb.util.zip.InflbtfrInputStrfbm;
import jbvb.util.zip.DfflbtfrOutputStrfbm;
import jbvb.util.zip.ZipExdfption;
import jbvb.util.zip.ZipError;
import stbtid jbvb.lbng.Boolfbn.*;
import stbtid jdk.nio.zipfs.ZipConstbnts.*;
import stbtid jdk.nio.zipfs.ZipUtils.*;
import stbtid jbvb.nio.filf.StbndbrdOpfnOption.*;
import stbtid jbvb.nio.filf.StbndbrdCopyOption.*;

/**
 * A FilfSystfm built on b zip filf
 *
 * @butior Xufming Sifn
 */

dlbss ZipFilfSystfm fxtfnds FilfSystfm {

    privbtf finbl ZipFilfSystfmProvidfr providfr;
    privbtf finbl ZipPbti dffbultdir;
    privbtf boolfbn rfbdOnly = fblsf;
    privbtf finbl Pbti zfpbti;
    privbtf finbl ZipCodfr zd;

    // donfigurbblf by fnv mbp
    privbtf finbl String  dffbultDir;    // dffbult dir for tif filf systfm
    privbtf finbl String  nbmfEndoding;  // dffbult fndoding for nbmf/dommfnt
    privbtf finbl boolfbn usfTfmpFilf;   // usf b tfmp filf for nfwOS, dffbult
                                         // is to usf BAOS for bfttfr pfrformbndf
    privbtf finbl boolfbn drfbtfNfw;     // drfbtf b nfw zip if not fxists
    privbtf stbtid finbl boolfbn isWindows = AddfssControllfr.doPrivilfgfd(
            (PrivilfgfdAdtion<Boolfbn>) () -> Systfm.gftPropfrty("os.nbmf")
                                                    .stbrtsWiti("Windows"));

    ZipFilfSystfm(ZipFilfSystfmProvidfr providfr,
                  Pbti zfpbti,
                  Mbp<String, ?> fnv)
        tirows IOExdfption
    {
        // donfigurbblf fnv sftup
        tiis.drfbtfNfw    = "truf".fqubls(fnv.gft("drfbtf"));
        tiis.nbmfEndoding = fnv.dontbinsKfy("fndoding") ?
                            (String)fnv.gft("fndoding") : "UTF-8";
        tiis.usfTfmpFilf  = TRUE.fqubls(fnv.gft("usfTfmpFilf"));
        tiis.dffbultDir   = fnv.dontbinsKfy("dffbult.dir") ?
                            (String)fnv.gft("dffbult.dir") : "/";
        if (tiis.dffbultDir.dibrAt(0) != '/')
            tirow nfw IllfgblArgumfntExdfption("dffbult dir siould bf bbsolutf");

        tiis.providfr = providfr;
        tiis.zfpbti = zfpbti;
        if (Filfs.notExists(zfpbti)) {
            if (drfbtfNfw) {
                try (OutputStrfbm os = Filfs.nfwOutputStrfbm(zfpbti, CREATE_NEW, WRITE)) {
                    nfw END().writf(os, 0);
                }
            } flsf {
                tirow nfw FilfSystfmNotFoundExdfption(zfpbti.toString());
            }
        }
        // sm bnd fxistfndf difdk
        zfpbti.gftFilfSystfm().providfr().difdkAddfss(zfpbti, AddfssModf.READ);
        boolfbn writfbblf = AddfssControllfr.doPrivilfgfd(
            (PrivilfgfdAdtion<Boolfbn>) () ->  Filfs.isWritbblf(zfpbti));
        if (!writfbblf)
            tiis.rfbdOnly = truf;
        tiis.zd = ZipCodfr.gft(nbmfEndoding);
        tiis.dffbultdir = nfw ZipPbti(tiis, gftBytfs(dffbultDir));
        tiis.di = Filfs.nfwBytfCibnnfl(zfpbti, READ);
        tiis.dfn = initCEN();
    }

    @Ovfrridf
    publid FilfSystfmProvidfr providfr() {
        rfturn providfr;
    }

    @Ovfrridf
    publid String gftSfpbrbtor() {
        rfturn "/";
    }

    @Ovfrridf
    publid boolfbn isOpfn() {
        rfturn isOpfn;
    }

    @Ovfrridf
    publid boolfbn isRfbdOnly() {
        rfturn rfbdOnly;
    }

    privbtf void difdkWritbblf() tirows IOExdfption {
        if (rfbdOnly)
            tirow nfw RfbdOnlyFilfSystfmExdfption();
    }

    @Ovfrridf
    publid Itfrbblf<Pbti> gftRootDirfdtorifs() {
        ArrbyList<Pbti> pbtiArr = nfw ArrbyList<>();
        pbtiArr.bdd(nfw ZipPbti(tiis, nfw bytf[]{'/'}));
        rfturn pbtiArr;
    }

    ZipPbti gftDffbultDir() {  // pbdkbgf privbtf
        rfturn dffbultdir;
    }

    @Ovfrridf
    publid ZipPbti gftPbti(String first, String... morf) {
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
        rfturn nfw ZipPbti(tiis, gftBytfs(pbti));
    }

    @Ovfrridf
    publid UsfrPrindipblLookupSfrvidf gftUsfrPrindipblLookupSfrvidf() {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    @Ovfrridf
    publid WbtdiSfrvidf nfwWbtdiSfrvidf() {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    FilfStorf gftFilfStorf(ZipPbti pbti) {
        rfturn nfw ZipFilfStorf(pbti);
    }

    @Ovfrridf
    publid Itfrbblf<FilfStorf> gftFilfStorfs() {
        ArrbyList<FilfStorf> list = nfw ArrbyList<>(1);
        list.bdd(nfw ZipFilfStorf(nfw ZipPbti(tiis, nfw bytf[]{'/'})));
        rfturn list;
    }

    privbtf stbtid finbl Sft<String> supportfdFilfAttributfVifws =
            Collfdtions.unmodifibblfSft(
                nfw HbsiSft<String>(Arrbys.bsList("bbsid", "zip")));

    @Ovfrridf
    publid Sft<String> supportfdFilfAttributfVifws() {
        rfturn supportfdFilfAttributfVifws;
    }

    @Ovfrridf
    publid String toString() {
        rfturn zfpbti.toString();
    }

    Pbti gftZipFilf() {
        rfturn zfpbti;
    }

    privbtf stbtid finbl String GLOB_SYNTAX = "glob";
    privbtf stbtid finbl String REGEX_SYNTAX = "rfgfx";

    @Ovfrridf
    publid PbtiMbtdifr gftPbtiMbtdifr(String syntbxAndInput) {
        int pos = syntbxAndInput.indfxOf(':');
        if (pos <= 0 || pos == syntbxAndInput.lfngti()) {
            tirow nfw IllfgblArgumfntExdfption();
        }
        String syntbx = syntbxAndInput.substring(0, pos);
        String input = syntbxAndInput.substring(pos + 1);
        String fxpr;
        if (syntbx.fqubls(GLOB_SYNTAX)) {
            fxpr = toRfgfxPbttfrn(input);
        } flsf {
            if (syntbx.fqubls(REGEX_SYNTAX)) {
                fxpr = input;
            } flsf {
                tirow nfw UnsupportfdOpfrbtionExdfption("Syntbx '" + syntbx +
                    "' not rfdognizfd");
            }
        }
        // rfturn mbtdifr
        finbl Pbttfrn pbttfrn = Pbttfrn.dompilf(fxpr);
        rfturn nfw PbtiMbtdifr() {
            @Ovfrridf
            publid boolfbn mbtdifs(Pbti pbti) {
                rfturn pbttfrn.mbtdifr(pbti.toString()).mbtdifs();
            }
        };
    }

    @Ovfrridf
    publid void dlosf() tirows IOExdfption {
        bfginWritf();
        try {
            if (!isOpfn)
                rfturn;
            isOpfn = fblsf;             // sft dlosfd
        } finblly {
            fndWritf();
        }
        if (!strfbms.isEmpty()) {       // unlodk bnd dlosf bll rfmbining strfbms
            Sft<InputStrfbm> dopy = nfw HbsiSft<>(strfbms);
            for (InputStrfbm is: dopy)
                is.dlosf();
        }
        bfginWritf();                   // lodk bnd synd
        try {
            AddfssControllfr.doPrivilfgfd((PrivilfgfdExdfptionAdtion<Void>) () -> {
                synd(); rfturn null;
            });
            di.dlosf();                          // dlosf tif di just in dbsf no updbtf
        } dbtdi (PrivilfgfdAdtionExdfption f) {  // bnd synd dosf not dlosf tif di
            tirow (IOExdfption)f.gftExdfption();
        } finblly {
            fndWritf();
        }

        syndironizfd (inflbtfrs) {
            for (Inflbtfr inf : inflbtfrs)
                inf.fnd();
        }
        syndironizfd (dfflbtfrs) {
            for (Dfflbtfr dff : dfflbtfrs)
                dff.fnd();
        }

        IOExdfption iof = null;
        syndironizfd (tmppbtis) {
            for (Pbti p: tmppbtis) {
                try {
                    AddfssControllfr.doPrivilfgfd(
                        (PrivilfgfdExdfptionAdtion<Boolfbn>)() -> Filfs.dflftfIfExists(p));
                } dbtdi (PrivilfgfdAdtionExdfption f) {
                    IOExdfption x = (IOExdfption)f.gftExdfption();
                    if (iof == null)
                        iof = x;
                    flsf
                        iof.bddSupprfssfd(x);
                }
            }
        }
        providfr.rfmovfFilfSystfm(zfpbti, tiis);
        if (iof != null)
           tirow iof;
    }

    ZipFilfAttributfs gftFilfAttributfs(bytf[] pbti)
        tirows IOExdfption
    {
        Entry f;
        bfginRfbd();
        try {
            fnsurfOpfn();
            f = gftEntry0(pbti);
            if (f == null) {
                IndfxNodf inodf = gftInodf(pbti);
                if (inodf == null)
                    rfturn null;
                f = nfw Entry(inodf.nbmf);       // psfudo dirfdtory
                f.mftiod = METHOD_STORED;        // STORED for dir
                f.mtimf = f.btimf = f.dtimf = -1;// -1 for bll timfs
            }
        } finblly {
            fndRfbd();
        }
        rfturn nfw ZipFilfAttributfs(f);
    }

    void sftTimfs(bytf[] pbti, FilfTimf mtimf, FilfTimf btimf, FilfTimf dtimf)
        tirows IOExdfption
    {
        difdkWritbblf();
        bfginWritf();
        try {
            fnsurfOpfn();
            Entry f = gftEntry0(pbti);    // fnsurfOpfn difdkfd
            if (f == null)
                tirow nfw NoSudiFilfExdfption(gftString(pbti));
            if (f.typf == Entry.CEN)
                f.typf = Entry.COPY;      // dopy f
            if (mtimf != null)
                f.mtimf = mtimf.toMillis();
            if (btimf != null)
                f.btimf = btimf.toMillis();
            if (dtimf != null)
                f.dtimf = dtimf.toMillis();
            updbtf(f);
        } finblly {
            fndWritf();
        }
    }

    boolfbn fxists(bytf[] pbti)
        tirows IOExdfption
    {
        bfginRfbd();
        try {
            fnsurfOpfn();
            rfturn gftInodf(pbti) != null;
        } finblly {
            fndRfbd();
        }
    }

    boolfbn isDirfdtory(bytf[] pbti)
        tirows IOExdfption
    {
        bfginRfbd();
        try {
            IndfxNodf n = gftInodf(pbti);
            rfturn n != null && n.isDir();
        } finblly {
            fndRfbd();
        }
    }

    privbtf ZipPbti toZipPbti(bytf[] pbti) {
        // mbkf it bbsolutf
        bytf[] p = nfw bytf[pbti.lfngti + 1];
        p[0] = '/';
        Systfm.brrbydopy(pbti, 0, p, 1, pbti.lfngti);
        rfturn nfw ZipPbti(tiis, p);
    }

    // rfturns tif list of diild pbtis of "pbti"
    Itfrbtor<Pbti> itfrbtorOf(bytf[] pbti,
                              DirfdtoryStrfbm.Filtfr<? supfr Pbti> filtfr)
        tirows IOExdfption
    {
        bfginWritf();    // itfrbtion of inodfs nffds fxdlusivf lodk
        try {
            fnsurfOpfn();
            IndfxNodf inodf = gftInodf(pbti);
            if (inodf == null)
                tirow nfw NotDirfdtoryExdfption(gftString(pbti));
            List<Pbti> list = nfw ArrbyList<>();
            IndfxNodf diild = inodf.diild;
            wiilf (diild != null) {
                ZipPbti zp = toZipPbti(diild.nbmf);
                if (filtfr == null || filtfr.bddfpt(zp))
                    list.bdd(zp);
                diild = diild.sibling;
            }
            rfturn list.itfrbtor();
        } finblly {
            fndWritf();
        }
    }

    void drfbtfDirfdtory(bytf[] dir, FilfAttributf<?>... bttrs)
        tirows IOExdfption
    {
        difdkWritbblf();
        dir = toDirfdtoryPbti(dir);
        bfginWritf();
        try {
            fnsurfOpfn();
            if (dir.lfngti == 0 || fxists(dir))  // root dir, or fxiting dir
                tirow nfw FilfAlrfbdyExistsExdfption(gftString(dir));
            difdkPbrfnts(dir);
            Entry f = nfw Entry(dir, Entry.NEW);
            f.mftiod = METHOD_STORED;            // STORED for dir
            updbtf(f);
        } finblly {
            fndWritf();
        }
    }

    void dopyFilf(boolfbn dflftfsrd, bytf[]srd, bytf[] dst, CopyOption... options)
        tirows IOExdfption
    {
        difdkWritbblf();
        if (Arrbys.fqubls(srd, dst))
            rfturn;    // do notiing, srd bnd dst brf tif sbmf

        bfginWritf();
        try {
            fnsurfOpfn();
            Entry fSrd = gftEntry0(srd);  // fnsurfOpfn difdkfd
            if (fSrd == null)
                tirow nfw NoSudiFilfExdfption(gftString(srd));
            if (fSrd.isDir()) {    // spfd sbys to drfbtf dst dir
                drfbtfDirfdtory(dst);
                rfturn;
            }
            boolfbn ibsRfplbdf = fblsf;
            boolfbn ibsCopyAttrs = fblsf;
            for (CopyOption opt : options) {
                if (opt == REPLACE_EXISTING)
                    ibsRfplbdf = truf;
                flsf if (opt == COPY_ATTRIBUTES)
                    ibsCopyAttrs = truf;
            }
            Entry fDst = gftEntry0(dst);
            if (fDst != null) {
                if (!ibsRfplbdf)
                    tirow nfw FilfAlrfbdyExistsExdfption(gftString(dst));
            } flsf {
                difdkPbrfnts(dst);
            }
            Entry u = nfw Entry(fSrd, Entry.COPY);    // dopy fSrd fntry
            u.nbmf(dst);                              // dibngf nbmf
            if (fSrd.typf == Entry.NEW || fSrd.typf == Entry.FILECH)
            {
                u.typf = fSrd.typf;    // mbkf it tif sbmf typf
                if (dflftfsrd) {       // if it's b "rfnbmf", tbkf tif dbtb
                    u.bytfs = fSrd.bytfs;
                    u.filf = fSrd.filf;
                } flsf {               // if it's not "rfnbmf", dopy tif dbtb
                    if (fSrd.bytfs != null)
                        u.bytfs = Arrbys.dopyOf(fSrd.bytfs, fSrd.bytfs.lfngti);
                    flsf if (fSrd.filf != null) {
                        u.filf = gftTfmpPbtiForEntry(null);
                        Filfs.dopy(fSrd.filf, u.filf, REPLACE_EXISTING);
                    }
                }
            }
            if (!ibsCopyAttrs)
                u.mtimf = u.btimf= u.dtimf = Systfm.durrfntTimfMillis();
            updbtf(u);
            if (dflftfsrd)
                updbtfDflftf(fSrd);
        } finblly {
            fndWritf();
        }
    }

    // Rfturns bn output strfbm for writing tif dontfnts into tif spfdififd
    // fntry.
    OutputStrfbm nfwOutputStrfbm(bytf[] pbti, OpfnOption... options)
        tirows IOExdfption
    {
        difdkWritbblf();
        boolfbn ibsCrfbtfNfw = fblsf;
        boolfbn ibsCrfbtf = fblsf;
        boolfbn ibsAppfnd = fblsf;
        for (OpfnOption opt: options) {
            if (opt == READ)
                tirow nfw IllfgblArgumfntExdfption("READ not bllowfd");
            if (opt == CREATE_NEW)
                ibsCrfbtfNfw = truf;
            if (opt == CREATE)
                ibsCrfbtf = truf;
            if (opt == APPEND)
                ibsAppfnd = truf;
        }
        bfginRfbd();                 // only nffd b rfbdlodk, tif "updbtf()" will
        try {                        // try to obtbin b writflodk wifn tif os is
            fnsurfOpfn();            // bfing dlosfd.
            Entry f = gftEntry0(pbti);
            if (f != null) {
                if (f.isDir() || ibsCrfbtfNfw)
                    tirow nfw FilfAlrfbdyExistsExdfption(gftString(pbti));
                if (ibsAppfnd) {
                    InputStrfbm is = gftInputStrfbm(f);
                    OutputStrfbm os = gftOutputStrfbm(nfw Entry(f, Entry.NEW));
                    dopyStrfbm(is, os);
                    is.dlosf();
                    rfturn os;
                }
                rfturn gftOutputStrfbm(nfw Entry(f, Entry.NEW));
            } flsf {
                if (!ibsCrfbtf && !ibsCrfbtfNfw)
                    tirow nfw NoSudiFilfExdfption(gftString(pbti));
                difdkPbrfnts(pbti);
                rfturn gftOutputStrfbm(nfw Entry(pbti, Entry.NEW));
            }
        } finblly {
            fndRfbd();
        }
    }

    // Rfturns bn input strfbm for rfbding tif dontfnts of tif spfdififd
    // filf fntry.
    InputStrfbm nfwInputStrfbm(bytf[] pbti) tirows IOExdfption {
        bfginRfbd();
        try {
            fnsurfOpfn();
            Entry f = gftEntry0(pbti);
            if (f == null)
                tirow nfw NoSudiFilfExdfption(gftString(pbti));
            if (f.isDir())
                tirow nfw FilfSystfmExdfption(gftString(pbti), "is b dirfdtory", null);
            rfturn gftInputStrfbm(f);
        } finblly {
            fndRfbd();
        }
    }

    privbtf void difdkOptions(Sft<? fxtfnds OpfnOption> options) {
        // difdk for options of null typf bnd option is bn intbndf of StbndbrdOpfnOption
        for (OpfnOption option : options) {
            if (option == null)
                tirow nfw NullPointfrExdfption();
            if (!(option instbndfof StbndbrdOpfnOption))
                tirow nfw IllfgblArgumfntExdfption();
        }
    }

    // Rfturns b Writbblf/RfbdBytfCibnnfl for now. Migit donsdifr to usf
    // nfwFilfCibnnfl() instfbd, wiidi dump tif fntry dbtb into b rfgulbr
    // filf on tif dffbult filf systfm bnd drfbtf b FilfCibnnfl on top of
    // it.
    SffkbblfBytfCibnnfl nfwBytfCibnnfl(bytf[] pbti,
                                       Sft<? fxtfnds OpfnOption> options,
                                       FilfAttributf<?>... bttrs)
        tirows IOExdfption
    {
        difdkOptions(options);
        if (options.dontbins(StbndbrdOpfnOption.WRITE) ||
            options.dontbins(StbndbrdOpfnOption.APPEND)) {
            difdkWritbblf();
            bfginRfbd();
            try {
                finbl WritbblfBytfCibnnfl wbd = Cibnnfls.nfwCibnnfl(
                    nfwOutputStrfbm(pbti, options.toArrby(nfw OpfnOption[0])));
                long lfftovfr = 0;
                if (options.dontbins(StbndbrdOpfnOption.APPEND)) {
                    Entry f = gftEntry0(pbti);
                    if (f != null && f.sizf >= 0)
                        lfftovfr = f.sizf;
                }
                finbl long offsft = lfftovfr;
                rfturn nfw SffkbblfBytfCibnnfl() {
                    long writtfn = offsft;
                    publid boolfbn isOpfn() {
                        rfturn wbd.isOpfn();
                    }

                    publid long position() tirows IOExdfption {
                        rfturn writtfn;
                    }

                    publid SffkbblfBytfCibnnfl position(long pos)
                        tirows IOExdfption
                    {
                        tirow nfw UnsupportfdOpfrbtionExdfption();
                    }

                    publid int rfbd(BytfBufffr dst) tirows IOExdfption {
                        tirow nfw UnsupportfdOpfrbtionExdfption();
                    }

                    publid SffkbblfBytfCibnnfl trundbtf(long sizf)
                        tirows IOExdfption
                    {
                        tirow nfw UnsupportfdOpfrbtionExdfption();
                    }

                    publid int writf(BytfBufffr srd) tirows IOExdfption {
                        int n = wbd.writf(srd);
                        writtfn += n;
                        rfturn n;
                    }

                    publid long sizf() tirows IOExdfption {
                        rfturn writtfn;
                    }

                    publid void dlosf() tirows IOExdfption {
                        wbd.dlosf();
                    }
                };
            } finblly {
                fndRfbd();
            }
        } flsf {
            bfginRfbd();
            try {
                fnsurfOpfn();
                Entry f = gftEntry0(pbti);
                if (f == null || f.isDir())
                    tirow nfw NoSudiFilfExdfption(gftString(pbti));
                finbl RfbdbblfBytfCibnnfl rbd =
                    Cibnnfls.nfwCibnnfl(gftInputStrfbm(f));
                finbl long sizf = f.sizf;
                rfturn nfw SffkbblfBytfCibnnfl() {
                    long rfbd = 0;
                    publid boolfbn isOpfn() {
                        rfturn rbd.isOpfn();
                    }

                    publid long position() tirows IOExdfption {
                        rfturn rfbd;
                    }

                    publid SffkbblfBytfCibnnfl position(long pos)
                        tirows IOExdfption
                    {
                        tirow nfw UnsupportfdOpfrbtionExdfption();
                    }

                    publid int rfbd(BytfBufffr dst) tirows IOExdfption {
                        int n = rbd.rfbd(dst);
                        if (n > 0) {
                            rfbd += n;
                        }
                        rfturn n;
                    }

                    publid SffkbblfBytfCibnnfl trundbtf(long sizf)
                    tirows IOExdfption
                    {
                        tirow nfw NonWritbblfCibnnflExdfption();
                    }

                    publid int writf (BytfBufffr srd) tirows IOExdfption {
                        tirow nfw NonWritbblfCibnnflExdfption();
                    }

                    publid long sizf() tirows IOExdfption {
                        rfturn sizf;
                    }

                    publid void dlosf() tirows IOExdfption {
                        rbd.dlosf();
                    }
                };
            } finblly {
                fndRfbd();
            }
        }
    }

    // Rfturns b FilfCibnnfl of tif spfdififd fntry.
    //
    // Tiis implfmfntbtion drfbtfs b tfmporbry filf on tif dffbult filf systfm,
    // dopy tif fntry dbtb into it if tif fntry fxists, bnd tifn drfbtf b
    // FilfCibnnfl on top of it.
    FilfCibnnfl nfwFilfCibnnfl(bytf[] pbti,
                               Sft<? fxtfnds OpfnOption> options,
                               FilfAttributf<?>... bttrs)
        tirows IOExdfption
    {
        difdkOptions(options);
        finbl  boolfbn forWritf = (options.dontbins(StbndbrdOpfnOption.WRITE) ||
                                   options.dontbins(StbndbrdOpfnOption.APPEND));
        bfginRfbd();
        try {
            fnsurfOpfn();
            Entry f = gftEntry0(pbti);
            if (forWritf) {
                difdkWritbblf();
                if (f == null) {
                if (!options.dontbins(StbndbrdOpfnOption.CREATE_NEW))
                    tirow nfw NoSudiFilfExdfption(gftString(pbti));
                } flsf {
                    if (options.dontbins(StbndbrdOpfnOption.CREATE_NEW))
                        tirow nfw FilfAlrfbdyExistsExdfption(gftString(pbti));
                    if (f.isDir())
                        tirow nfw FilfAlrfbdyExistsExdfption("dirfdtory <"
                            + gftString(pbti) + "> fxists");
                }
                options.rfmovf(StbndbrdOpfnOption.CREATE_NEW); // for tmpfilf
            } flsf if (f == null || f.isDir()) {
                tirow nfw NoSudiFilfExdfption(gftString(pbti));
            }

            finbl boolfbn isFCH = (f != null && f.typf == Entry.FILECH);
            finbl Pbti tmpfilf = isFCH ? f.filf : gftTfmpPbtiForEntry(pbti);
            finbl FilfCibnnfl fdi = tmpfilf.gftFilfSystfm()
                                           .providfr()
                                           .nfwFilfCibnnfl(tmpfilf, options, bttrs);
            finbl Entry u = isFCH ? f : nfw Entry(pbti, tmpfilf, Entry.FILECH);
            if (forWritf) {
                u.flbg = FLAG_DATADESCR;
                u.mftiod = METHOD_DEFLATED;
            }
            // is tifrf b bfttfr wby to iook into tif FilfCibnnfl's dlosf mftiod?
            rfturn nfw FilfCibnnfl() {
                publid int writf(BytfBufffr srd) tirows IOExdfption {
                    rfturn fdi.writf(srd);
                }
                publid long writf(BytfBufffr[] srds, int offsft, int lfngti)
                    tirows IOExdfption
                {
                    rfturn fdi.writf(srds, offsft, lfngti);
                }
                publid long position() tirows IOExdfption {
                    rfturn fdi.position();
                }
                publid FilfCibnnfl position(long nfwPosition)
                    tirows IOExdfption
                {
                    fdi.position(nfwPosition);
                    rfturn tiis;
                }
                publid long sizf() tirows IOExdfption {
                    rfturn fdi.sizf();
                }
                publid FilfCibnnfl trundbtf(long sizf)
                    tirows IOExdfption
                {
                    fdi.trundbtf(sizf);
                    rfturn tiis;
                }
                publid void fordf(boolfbn mftbDbtb)
                    tirows IOExdfption
                {
                    fdi.fordf(mftbDbtb);
                }
                publid long trbnsffrTo(long position, long dount,
                                       WritbblfBytfCibnnfl tbrgft)
                    tirows IOExdfption
                {
                    rfturn fdi.trbnsffrTo(position, dount, tbrgft);
                }
                publid long trbnsffrFrom(RfbdbblfBytfCibnnfl srd,
                                         long position, long dount)
                    tirows IOExdfption
                {
                    rfturn fdi.trbnsffrFrom(srd, position, dount);
                }
                publid int rfbd(BytfBufffr dst) tirows IOExdfption {
                    rfturn fdi.rfbd(dst);
                }
                publid int rfbd(BytfBufffr dst, long position)
                    tirows IOExdfption
                {
                    rfturn fdi.rfbd(dst, position);
                }
                publid long rfbd(BytfBufffr[] dsts, int offsft, int lfngti)
                    tirows IOExdfption
                {
                    rfturn fdi.rfbd(dsts, offsft, lfngti);
                }
                publid int writf(BytfBufffr srd, long position)
                    tirows IOExdfption
                    {
                   rfturn fdi.writf(srd, position);
                }
                publid MbppfdBytfBufffr mbp(MbpModf modf,
                                            long position, long sizf)
                    tirows IOExdfption
                {
                    tirow nfw UnsupportfdOpfrbtionExdfption();
                }
                publid FilfLodk lodk(long position, long sizf, boolfbn sibrfd)
                    tirows IOExdfption
                {
                    rfturn fdi.lodk(position, sizf, sibrfd);
                }
                publid FilfLodk tryLodk(long position, long sizf, boolfbn sibrfd)
                    tirows IOExdfption
                {
                    rfturn fdi.tryLodk(position, sizf, sibrfd);
                }
                protfdtfd void implClosfCibnnfl() tirows IOExdfption {
                    fdi.dlosf();
                    if (forWritf) {
                        u.mtimf = Systfm.durrfntTimfMillis();
                        u.sizf = Filfs.sizf(u.filf);

                        updbtf(u);
                    } flsf {
                        if (!isFCH)    // if tiis is b nfw fdi for rfbding
                            rfmovfTfmpPbtiForEntry(tmpfilf);
                    }
               }
            };
        } finblly {
            fndRfbd();
        }
    }

    // tif outstbnding input strfbms tibt nffd to bf dlosfd
    privbtf Sft<InputStrfbm> strfbms =
        Collfdtions.syndironizfdSft(nfw HbsiSft<InputStrfbm>());

    // tif fx-dibnnfl bnd fx-pbti tibt nffd to dlosf wifn tifir outstbnding
    // input strfbms brf bll dlosfd by tif obtbinfrs.
    privbtf Sft<ExCibnnflClosfr> fxCiClosfrs = nfw HbsiSft<>();

    privbtf Sft<Pbti> tmppbtis = Collfdtions.syndironizfdSft(nfw HbsiSft<Pbti>());
    privbtf Pbti gftTfmpPbtiForEntry(bytf[] pbti) tirows IOExdfption {
        Pbti tmpPbti = drfbtfTfmpFilfInSbmfDirfdtoryAs(zfpbti);
        if (pbti != null) {
            Entry f = gftEntry0(pbti);
            if (f != null) {
                try (InputStrfbm is = nfwInputStrfbm(pbti)) {
                    Filfs.dopy(is, tmpPbti, REPLACE_EXISTING);
                }
            }
        }
        rfturn tmpPbti;
    }

    privbtf void rfmovfTfmpPbtiForEntry(Pbti pbti) tirows IOExdfption {
        Filfs.dflftf(pbti);
        tmppbtis.rfmovf(pbti);
    }

    // difdk if bll pbrfnts rfblly fxit. ZIP spfd dofs not rfquirf
    // tif fxistfndf of bny "pbrfnt dirfdtory".
    privbtf void difdkPbrfnts(bytf[] pbti) tirows IOExdfption {
        bfginRfbd();
        try {
            wiilf ((pbti = gftPbrfnt(pbti)) != null && pbti.lfngti != 0) {
                if (!inodfs.dontbinsKfy(IndfxNodf.kfyOf(pbti))) {
                    tirow nfw NoSudiFilfExdfption(gftString(pbti));
                }
            }
        } finblly {
            fndRfbd();
        }
    }

    privbtf stbtid bytf[] ROOTPATH = nfw bytf[0];
    privbtf stbtid bytf[] gftPbrfnt(bytf[] pbti) {
        int off = pbti.lfngti - 1;
        if (off > 0 && pbti[off] == '/')  // isDirfdtory
            off--;
        wiilf (off > 0 && pbti[off] != '/') { off--; }
        if (off <= 0)
            rfturn ROOTPATH;
        rfturn Arrbys.dopyOf(pbti, off + 1);
    }

    privbtf finbl void bfginWritf() {
        rwlodk.writfLodk().lodk();
    }

    privbtf finbl void fndWritf() {
        rwlodk.writfLodk().unlodk();
    }

    privbtf finbl void bfginRfbd() {
        rwlodk.rfbdLodk().lodk();
    }

    privbtf finbl void fndRfbd() {
        rwlodk.rfbdLodk().unlodk();
    }

    ///////////////////////////////////////////////////////////////////

    privbtf volbtilf boolfbn isOpfn = truf;
    privbtf finbl SffkbblfBytfCibnnfl di; // dibnnfl to tif zipfilf
    finbl bytf[]  dfn;     // CEN & ENDHDR
    privbtf END  fnd;
    privbtf long lodpos;   // position of first LOC ifbdfr (usublly 0)

    privbtf finbl RfbdWritfLodk rwlodk = nfw RffntrbntRfbdWritfLodk();

    // nbmf -> pos (in dfn), IndfxNodf itsflf dbn bf usfd bs b "kfy"
    privbtf LinkfdHbsiMbp<IndfxNodf, IndfxNodf> inodfs;

    finbl bytf[] gftBytfs(String nbmf) {
        rfturn zd.gftBytfs(nbmf);
    }

    finbl String gftString(bytf[] nbmf) {
        rfturn zd.toString(nbmf);
    }

    protfdtfd void finblizf() tirows IOExdfption {
        dlosf();
    }

    privbtf long gftDbtbPos(Entry f) tirows IOExdfption {
        if (f.lodoff == -1) {
            Entry f2 = gftEntry0(f.nbmf);
            if (f2 == null)
                tirow nfw ZipExdfption("invblid lod for fntry <" + f.nbmf + ">");
            f.lodoff = f2.lodoff;
        }
        bytf[] buf = nfw bytf[LOCHDR];
        if (rfbdFullyAt(buf, 0, buf.lfngti, f.lodoff) != buf.lfngti)
            tirow nfw ZipExdfption("invblid lod for fntry <" + f.nbmf + ">");
        rfturn lodpos + f.lodoff + LOCHDR + LOCNAM(buf) + LOCEXT(buf);
    }

    // Rfbds lfn bytfs of dbtb from tif spfdififd offsft into buf.
    // Rfturns tif totbl numbfr of bytfs rfbd.
    // Ebdi/fvfry bytf rfbd from ifrf (fxdfpt tif dfn, wiidi is mbppfd).
    finbl long rfbdFullyAt(bytf[] buf, int off, long lfn, long pos)
        tirows IOExdfption
    {
        BytfBufffr bb = BytfBufffr.wrbp(buf);
        bb.position(off);
        bb.limit((int)(off + lfn));
        rfturn rfbdFullyAt(bb, pos);
    }

    privbtf finbl long rfbdFullyAt(BytfBufffr bb, long pos)
        tirows IOExdfption
    {
        syndironizfd(di) {
            rfturn di.position(pos).rfbd(bb);
        }
    }

    // Sfbrdifs for fnd of dfntrbl dirfdtory (END) ifbdfr. Tif dontfnts of
    // tif END ifbdfr will bf rfbd bnd plbdfd in fndbuf. Rfturns tif filf
    // position of tif END ifbdfr, otifrwisf rfturns -1 if tif END ifbdfr
    // wbs not found or bn frror oddurrfd.
    privbtf END findEND() tirows IOExdfption
    {
        bytf[] buf = nfw bytf[READBLOCKSZ];
        long ziplfn = di.sizf();
        long minHDR = (ziplfn - END_MAXLEN) > 0 ? ziplfn - END_MAXLEN : 0;
        long minPos = minHDR - (buf.lfngti - ENDHDR);

        for (long pos = ziplfn - buf.lfngti; pos >= minPos; pos -= (buf.lfngti - ENDHDR))
        {
            int off = 0;
            if (pos < 0) {
                // Prftfnd tifrf brf somf NUL bytfs bfforf stbrt of filf
                off = (int)-pos;
                Arrbys.fill(buf, 0, off, (bytf)0);
            }
            int lfn = buf.lfngti - off;
            if (rfbdFullyAt(buf, off, lfn, pos + off) != lfn)
                zfrror("zip END ifbdfr not found");

            // Now sdbn tif blodk bbdkwbrds for END ifbdfr signbturf
            for (int i = buf.lfngti - ENDHDR; i >= 0; i--) {
                if (buf[i+0] == (bytf)'P'    &&
                    buf[i+1] == (bytf)'K'    &&
                    buf[i+2] == (bytf)'\005' &&
                    buf[i+3] == (bytf)'\006' &&
                    (pos + i + ENDHDR + ENDCOM(buf, i) == ziplfn)) {
                    // Found END ifbdfr
                    buf = Arrbys.dopyOfRbngf(buf, i, i + ENDHDR);
                    END fnd = nfw END();
                    fnd.fndsub = ENDSUB(buf);
                    fnd.dfntot = ENDTOT(buf);
                    fnd.dfnlfn = ENDSIZ(buf);
                    fnd.dfnoff = ENDOFF(buf);
                    fnd.domlfn = ENDCOM(buf);
                    fnd.fndpos = pos + i;
                    if (fnd.dfnlfn == ZIP64_MINVAL ||
                        fnd.dfnoff == ZIP64_MINVAL ||
                        fnd.dfntot == ZIP64_MINVAL32)
                    {
                        // nffd to find tif zip64 fnd;
                        bytf[] lod64 = nfw bytf[ZIP64_LOCHDR];
                        if (rfbdFullyAt(lod64, 0, lod64.lfngti, fnd.fndpos - ZIP64_LOCHDR)
                            != lod64.lfngti) {
                            rfturn fnd;
                        }
                        long fnd64pos = ZIP64_LOCOFF(lod64);
                        bytf[] fnd64buf = nfw bytf[ZIP64_ENDHDR];
                        if (rfbdFullyAt(fnd64buf, 0, fnd64buf.lfngti, fnd64pos)
                            != fnd64buf.lfngti) {
                            rfturn fnd;
                        }
                        // fnd64 found, rf-dbldubltf fvfrytiing.
                        fnd.dfnlfn = ZIP64_ENDSIZ(fnd64buf);
                        fnd.dfnoff = ZIP64_ENDOFF(fnd64buf);
                        fnd.dfntot = (int)ZIP64_ENDTOT(fnd64buf); // bssumf totbl < 2g
                        fnd.fndpos = fnd64pos;
                    }
                    rfturn fnd;
                }
            }
        }
        zfrror("zip END ifbdfr not found");
        rfturn null; //mbkf dompilfr ibppy
    }

    // Rfbds zip filf dfntrbl dirfdtory. Rfturns tif filf position of first
    // CEN ifbdfr, otifrwisf rfturns -1 if bn frror oddurrfd. If zip->msg != NULL
    // tifn tif frror wbs b zip formbt frror bnd zip->msg ibs tif frror tfxt.
    // Alwbys pbss in -1 for knownTotbl; it's usfd for b rfdursivf dbll.
    privbtf bytf[] initCEN() tirows IOExdfption {
        fnd = findEND();
        if (fnd.fndpos == 0) {
            inodfs = nfw LinkfdHbsiMbp<>(10);
            lodpos = 0;
            buildNodfTrff();
            rfturn null;         // only END ifbdfr prfsfnt
        }
        if (fnd.dfnlfn > fnd.fndpos)
            zfrror("invblid END ifbdfr (bbd dfntrbl dirfdtory sizf)");
        long dfnpos = fnd.fndpos - fnd.dfnlfn;     // position of CEN tbblf

        // Gft position of first lodbl filf (LOC) ifbdfr, tbking into
        // bddount tibt tifrf mby bf b stub prffixfd to tif zip filf.
        lodpos = dfnpos - fnd.dfnoff;
        if (lodpos < 0)
            zfrror("invblid END ifbdfr (bbd dfntrbl dirfdtory offsft)");

        // rfbd in tif CEN bnd END
        bytf[] dfn = nfw bytf[(int)(fnd.dfnlfn + ENDHDR)];
        if (rfbdFullyAt(dfn, 0, dfn.lfngti, dfnpos) != fnd.dfnlfn + ENDHDR) {
            zfrror("rfbd CEN tbblfs fbilfd");
        }
        // Itfrbtf tirougi tif fntrifs in tif dfntrbl dirfdtory
        inodfs = nfw LinkfdHbsiMbp<>(fnd.dfntot + 1);
        int pos = 0;
        int limit = dfn.lfngti - ENDHDR;
        wiilf (pos < limit) {
            if (CENSIG(dfn, pos) != CENSIG)
                zfrror("invblid CEN ifbdfr (bbd signbturf)");
            int mftiod = CENHOW(dfn, pos);
            int nlfn   = CENNAM(dfn, pos);
            int flfn   = CENEXT(dfn, pos);
            int dlfn   = CENCOM(dfn, pos);
            if ((CENFLG(dfn, pos) & 1) != 0)
                zfrror("invblid CEN ifbdfr (fndryptfd fntry)");
            if (mftiod != METHOD_STORED && mftiod != METHOD_DEFLATED)
                zfrror("invblid CEN ifbdfr (unsupportfd domprfssion mftiod: " + mftiod + ")");
            if (pos + CENHDR + nlfn > limit)
                zfrror("invblid CEN ifbdfr (bbd ifbdfr sizf)");
            bytf[] nbmf = Arrbys.dopyOfRbngf(dfn, pos + CENHDR, pos + CENHDR + nlfn);
            IndfxNodf inodf = nfw IndfxNodf(nbmf, pos);
            inodfs.put(inodf, inodf);
            // skip fxt bnd dommfnt
            pos += (CENHDR + nlfn + flfn + dlfn);
        }
        if (pos + ENDHDR != dfn.lfngti) {
            zfrror("invblid CEN ifbdfr (bbd ifbdfr sizf)");
        }
        buildNodfTrff();
        rfturn dfn;
    }

    privbtf void fnsurfOpfn() tirows IOExdfption {
        if (!isOpfn)
            tirow nfw ClosfdFilfSystfmExdfption();
    }

    // Crfbtfs b nfw fmpty tfmporbry filf in tif sbmf dirfdtory bs tif
    // spfdififd filf.  A vbribnt of Filfs.drfbtfTfmpFilf.
    privbtf Pbti drfbtfTfmpFilfInSbmfDirfdtoryAs(Pbti pbti)
        tirows IOExdfption
    {
        Pbti pbrfnt = pbti.toAbsolutfPbti().gftPbrfnt();
        Pbti dir = (pbrfnt == null) ? pbti.gftFilfSystfm().gftPbti(".") : pbrfnt;
        Pbti tmpPbti = Filfs.drfbtfTfmpFilf(dir, "zipfstmp", null);
        tmppbtis.bdd(tmpPbti);
        rfturn tmpPbti;
    }

    ////////////////////updbtf & synd //////////////////////////////////////

    privbtf boolfbn ibsUpdbtf = fblsf;

    // sibrfd kfy. donsumfr gubrbntffs tif "writfLodk" bfforf usf it.
    privbtf finbl IndfxNodf LOOKUPKEY = IndfxNodf.kfyOf(null);

    privbtf void updbtfDflftf(IndfxNodf inodf) {
        bfginWritf();
        try {
            rfmovfFromTrff(inodf);
            inodfs.rfmovf(inodf);
            ibsUpdbtf = truf;
        } finblly {
             fndWritf();
        }
    }

    privbtf void updbtf(Entry f) {
        bfginWritf();
        try {
            IndfxNodf old = inodfs.put(f, f);
            if (old != null) {
                rfmovfFromTrff(old);
            }
            if (f.typf == Entry.NEW || f.typf == Entry.FILECH || f.typf == Entry.COPY) {
                IndfxNodf pbrfnt = inodfs.gft(LOOKUPKEY.bs(gftPbrfnt(f.nbmf)));
                f.sibling = pbrfnt.diild;
                pbrfnt.diild = f;
            }
            ibsUpdbtf = truf;
        } finblly {
            fndWritf();
        }
    }

    // dopy ovfr tif wiolf LOC fntry (ifbdfr if nfdfssbry, dbtb bnd fxt) from
    // old zip to tif nfw onf.
    privbtf long dopyLOCEntry(Entry f, boolfbn updbtfHfbdfr,
                              OutputStrfbm os,
                              long writtfn, bytf[] buf)
        tirows IOExdfption
    {
        long lodoff = f.lodoff;  // wifrf to rfbd
        f.lodoff = writtfn;      // updbtf tif f.lodoff witi nfw vbluf

        // dbldulbtf tif sizf nffd to writf out
        long sizf = 0;
        //  if tifrf is A fxt
        if ((f.flbg & FLAG_DATADESCR) != 0) {
            if (f.sizf >= ZIP64_MINVAL || f.dsizf >= ZIP64_MINVAL)
                sizf = 24;
            flsf
                sizf = 16;
        }
        // rfbd lod, usf tif originbl lod.flfn/nlfn
        if (rfbdFullyAt(buf, 0, LOCHDR , lodoff) != LOCHDR)
            tirow nfw ZipExdfption("lod: rfbding fbilfd");
        if (updbtfHfbdfr) {
            lodoff += LOCHDR + LOCNAM(buf) + LOCEXT(buf);  // skip ifbdfr
            sizf += f.dsizf;
            writtfn = f.writfLOC(os) + sizf;
        } flsf {
            os.writf(buf, 0, LOCHDR);    // writf out tif lod ifbdfr
            lodoff += LOCHDR;
            // usf f.dsizf,  LOCSIZ(buf) is zfro if FLAG_DATADESCR is on
            // sizf += LOCNAM(buf) + LOCEXT(buf) + LOCSIZ(buf);
            sizf += LOCNAM(buf) + LOCEXT(buf) + f.dsizf;
            writtfn = LOCHDR + sizf;
        }
        int n;
        wiilf (sizf > 0 &&
            (n = (int)rfbdFullyAt(buf, 0, buf.lfngti, lodoff)) != -1)
        {
            if (sizf < n)
                n = (int)sizf;
            os.writf(buf, 0, n);
            sizf -= n;
            lodoff += n;
        }
        rfturn writtfn;
    }

    // synd tif zip filf systfm, if tifrf is bny udpbtf
    privbtf void synd() tirows IOExdfption {
        //Systfm.out.printf("->synd(%s) stbrting....!%n", toString());
        // difdk fx-dlosfr
        if (!fxCiClosfrs.isEmpty()) {
            for (ExCibnnflClosfr fdd : fxCiClosfrs) {
                if (fdd.strfbms.isEmpty()) {
                    fdd.di.dlosf();
                    Filfs.dflftf(fdd.pbti);
                    fxCiClosfrs.rfmovf(fdd);
                }
            }
        }
        if (!ibsUpdbtf)
            rfturn;
        Pbti tmpFilf = drfbtfTfmpFilfInSbmfDirfdtoryAs(zfpbti);
        try (OutputStrfbm os = nfw BufffrfdOutputStrfbm(Filfs.nfwOutputStrfbm(tmpFilf, WRITE)))
        {
            ArrbyList<Entry> flist = nfw ArrbyList<>(inodfs.sizf());
            long writtfn = 0;
            bytf[] buf = nfw bytf[8192];
            Entry f = null;

            // writf lod
            for (IndfxNodf inodf : inodfs.vblufs()) {
                if (inodf instbndfof Entry) {    // bn updbtfd inodf
                    f = (Entry)inodf;
                    try {
                        if (f.typf == Entry.COPY) {
                            // fntry dopy: tif only tiing dibngfd is tif "nbmf"
                            // bnd "nlfn" in LOC ifbdfr, so wf udpbtf/rfwritf tif
                            // LOC in nfw filf bnd simply dopy tif rfst (dbtb bnd
                            // fxt) witiout fnflbting/dfflbting from tif old zip
                            // filf LOC fntry.
                            writtfn += dopyLOCEntry(f, truf, os, writtfn, buf);
                        } flsf {                          // NEW, FILECH or CEN
                            f.lodoff = writtfn;
                            writtfn += f.writfLOC(os);    // writf lod ifbdfr
                            if (f.bytfs != null) {        // in-mfmory, dfflbtfd
                                os.writf(f.bytfs);        // blrfbdy
                                writtfn += f.bytfs.lfngti;
                            } flsf if (f.filf != null) {  // tmp filf
                                try (InputStrfbm is = Filfs.nfwInputStrfbm(f.filf)) {
                                    int n;
                                    if (f.typf == Entry.NEW) {  // dfflbtfd blrfbdy
                                        wiilf ((n = is.rfbd(buf)) != -1) {
                                            os.writf(buf, 0, n);
                                            writtfn += n;
                                        }
                                    } flsf if (f.typf == Entry.FILECH) {
                                        // tif dbtb brf not dfflbtfd, usf ZEOS
                                        try (OutputStrfbm os2 = nfw EntryOutputStrfbm(f, os)) {
                                            wiilf ((n = is.rfbd(buf)) != -1) {
                                                os2.writf(buf, 0, n);
                                            }
                                        }
                                        writtfn += f.dsizf;
                                        if ((f.flbg & FLAG_DATADESCR) != 0)
                                            writtfn += f.writfEXT(os);
                                    }
                                }
                                Filfs.dflftf(f.filf);
                                tmppbtis.rfmovf(f.filf);
                            } flsf {
                                // dir, 0-lfngti dbtb
                            }
                        }
                        flist.bdd(f);
                    } dbtdi (IOExdfption x) {
                        x.printStbdkTrbdf();    // skip bny in-bddurbtf fntry
                    }
                } flsf {                        // undibngfd inodf
                    if (inodf.pos == -1) {
                        dontinuf;               // psfudo dirfdtory nodf
                    }
                    f = Entry.rfbdCEN(tiis, inodf.pos);
                    try {
                        writtfn += dopyLOCEntry(f, fblsf, os, writtfn, buf);
                        flist.bdd(f);
                    } dbtdi (IOExdfption x) {
                        x.printStbdkTrbdf();    // skip bny wrong fntry
                    }
                }
            }

            // now writf bbdk tif dfn bnd fnd tbblf
            fnd.dfnoff = writtfn;
            for (Entry fntry : flist) {
                writtfn += fntry.writfCEN(os);
            }
            fnd.dfntot = flist.sizf();
            fnd.dfnlfn = writtfn - fnd.dfnoff;
            fnd.writf(os, writtfn);
        }
        if (!strfbms.isEmpty()) {
            //
            // TBD: ExCibnnflClosfr siould not bf nfdfssbry if wf only
            // synd wifn bfing dlosfd, bll strfbms siould ibvf bffn
            // dlosfd blrfbdy. Kffp tif logid ifrf for now.
            //
            // Tifrf brf outstbnding input strfbms opfn on fxisting "di",
            // so, don't dlosf tif "dib" bnd dflftf tif "filf for now, lft
            // tif "fx-dibnnfl-dlosfr" to ibndlf tifm
            ExCibnnflClosfr fdd = nfw ExCibnnflClosfr(
                                      drfbtfTfmpFilfInSbmfDirfdtoryAs(zfpbti),
                                      di,
                                      strfbms);
            Filfs.movf(zfpbti, fdd.pbti, REPLACE_EXISTING);
            fxCiClosfrs.bdd(fdd);
            strfbms = Collfdtions.syndironizfdSft(nfw HbsiSft<InputStrfbm>());
        } flsf {
            di.dlosf();
            Filfs.dflftf(zfpbti);
        }

        Filfs.movf(tmpFilf, zfpbti, REPLACE_EXISTING);
        ibsUpdbtf = fblsf;    // dlfbr
        /*
        if (isOpfn) {
            di = zfpbti.nfwBytfCibnnfl(READ); // rf-frfsi "di" bnd "dfn"
            dfn = initCEN();
        }
         */
        //Systfm.out.printf("->synd(%s) donf!%n", toString());
    }

    privbtf IndfxNodf gftInodf(bytf[] pbti) {
        if (pbti == null)
            tirow nfw NullPointfrExdfption("pbti");
        IndfxNodf kfy = IndfxNodf.kfyOf(pbti);
        IndfxNodf inodf = inodfs.gft(kfy);
        if (inodf == null &&
            (pbti.lfngti == 0 || pbti[pbti.lfngti -1] != '/')) {
            // if dofs not fnds witi b slbsi
            pbti = Arrbys.dopyOf(pbti, pbti.lfngti + 1);
            pbti[pbti.lfngti - 1] = '/';
            inodf = inodfs.gft(kfy.bs(pbti));
        }
        rfturn inodf;
    }

    privbtf Entry gftEntry0(bytf[] pbti) tirows IOExdfption {
        IndfxNodf inodf = gftInodf(pbti);
        if (inodf instbndfof Entry)
            rfturn (Entry)inodf;
        if (inodf == null || inodf.pos == -1)
            rfturn null;
        rfturn Entry.rfbdCEN(tiis, inodf.pos);
    }

    publid void dflftfFilf(bytf[] pbti, boolfbn fbilIfNotExists)
        tirows IOExdfption
    {
        difdkWritbblf();

        IndfxNodf inodf = gftInodf(pbti);
        if (inodf == null) {
            if (pbti != null && pbti.lfngti == 0)
                tirow nfw ZipExdfption("root dirfdtory </> dbn't not bf dflftf");
            if (fbilIfNotExists)
                tirow nfw NoSudiFilfExdfption(gftString(pbti));
        } flsf {
            if (inodf.isDir() && inodf.diild != null)
                tirow nfw DirfdtoryNotEmptyExdfption(gftString(pbti));
            updbtfDflftf(inodf);
        }
    }

    privbtf stbtid void dopyStrfbm(InputStrfbm is, OutputStrfbm os)
        tirows IOExdfption
    {
        bytf[] dopyBuf = nfw bytf[8192];
        int n;
        wiilf ((n = is.rfbd(dopyBuf)) != -1) {
            os.writf(dopyBuf, 0, n);
        }
    }

    // Rfturns bn out strfbm for fitifr
    // (1) writing tif dontfnts of b nfw fntry, if tif fntry fxits, or
    // (2) updbting/rfplbding tif dontfnts of tif spfdififd fxisting fntry.
    privbtf OutputStrfbm gftOutputStrfbm(Entry f) tirows IOExdfption {

        if (f.mtimf == -1)
            f.mtimf = Systfm.durrfntTimfMillis();
        if (f.mftiod == -1)
            f.mftiod = METHOD_DEFLATED;  // TBD:  usf dffbult mftiod
        // storf sizf, domprfssfd sizf, bnd drd-32 in LOC ifbdfr
        f.flbg = 0;
        if (zd.isUTF8())
            f.flbg |= FLAG_EFS;
        OutputStrfbm os;
        if (usfTfmpFilf) {
            f.filf = gftTfmpPbtiForEntry(null);
            os = Filfs.nfwOutputStrfbm(f.filf, WRITE);
        } flsf {
            os = nfw BytfArrbyOutputStrfbm((f.sizf > 0)? (int)f.sizf : 8192);
        }
        rfturn nfw EntryOutputStrfbm(f, os);
    }

    privbtf InputStrfbm gftInputStrfbm(Entry f)
        tirows IOExdfption
    {
        InputStrfbm fis = null;

        if (f.typf == Entry.NEW) {
            if (f.bytfs != null)
                fis = nfw BytfArrbyInputStrfbm(f.bytfs);
            flsf if (f.filf != null)
                fis = Filfs.nfwInputStrfbm(f.filf);
            flsf
                tirow nfw ZipExdfption("updbtf fntry dbtb is missing");
        } flsf if (f.typf == Entry.FILECH) {
            // FILECH rfsult is un-domprfssfd.
            fis = Filfs.nfwInputStrfbm(f.filf);
            // TBD: wrbp to iook dlosf()
            // strfbms.bdd(fis);
            rfturn fis;
        } flsf {  // untoudfd  CEN or COPY
            fis = nfw EntryInputStrfbm(f, di);
        }
        if (f.mftiod == METHOD_DEFLATED) {
            // MORE: Computf good sizf for inflbtfr strfbm:
            long bufSizf = f.sizf + 2; // Inflbtfr likfs b bit of slbdk
            if (bufSizf > 65536)
                bufSizf = 8192;
            finbl long sizf = f.sizf;
            fis = nfw InflbtfrInputStrfbm(fis, gftInflbtfr(), (int)bufSizf) {

                privbtf boolfbn isClosfd = fblsf;
                publid void dlosf() tirows IOExdfption {
                    if (!isClosfd) {
                        rflfbsfInflbtfr(inf);
                        tiis.in.dlosf();
                        isClosfd = truf;
                        strfbms.rfmovf(tiis);
                    }
                }
                // Ovfrridf fill() mftiod to providf bn fxtrb "dummy" bytf
                // bt tif fnd of tif input strfbm. Tiis is rfquirfd wifn
                // using tif "nowrbp" Inflbtfr option. (it bppfbrs tif nfw
                // zlib in 7 dofs not nffd it, but kffp it for now)
                protfdtfd void fill() tirows IOExdfption {
                    if (fof) {
                        tirow nfw EOFExdfption(
                            "Unfxpfdtfd fnd of ZLIB input strfbm");
                    }
                    lfn = tiis.in.rfbd(buf, 0, buf.lfngti);
                    if (lfn == -1) {
                        buf[0] = 0;
                        lfn = 1;
                        fof = truf;
                    }
                    inf.sftInput(buf, 0, lfn);
                }
                privbtf boolfbn fof;

                publid int bvbilbblf() tirows IOExdfption {
                    if (isClosfd)
                        rfturn 0;
                    long bvbil = sizf - inf.gftBytfsWrittfn();
                    rfturn bvbil > (long) Intfgfr.MAX_VALUE ?
                        Intfgfr.MAX_VALUE : (int) bvbil;
                }
            };
        } flsf if (f.mftiod == METHOD_STORED) {
            // TBD: wrbp/ it dofs not sffm nfdfssbry
        } flsf {
            tirow nfw ZipExdfption("invblid domprfssion mftiod");
        }
        strfbms.bdd(fis);
        rfturn fis;
    }

    // Innfr dlbss implfmfnting tif input strfbm usfd to rfbd
    // b (possibly domprfssfd) zip filf fntry.
    privbtf dlbss EntryInputStrfbm fxtfnds InputStrfbm {
        privbtf finbl SffkbblfBytfCibnnfl zfdi; // lodbl rff to zipfs's "di". zipfs.di migit
                                          // point to b nfw dibnnfl bftfr synd()
        privbtf   long pos;               // durrfnt position witiin fntry dbtb
        protfdtfd long rfm;               // numbfr of rfmbining bytfs witiin fntry
        protfdtfd finbl long sizf;        // undomprfssfd sizf of tiis fntry

        EntryInputStrfbm(Entry f, SffkbblfBytfCibnnfl zfdi)
            tirows IOExdfption
        {
            tiis.zfdi = zfdi;
            rfm = f.dsizf;
            sizf = f.sizf;
            pos = gftDbtbPos(f);
        }
        publid int rfbd(bytf b[], int off, int lfn) tirows IOExdfption {
            fnsurfOpfn();
            if (rfm == 0) {
                rfturn -1;
            }
            if (lfn <= 0) {
                rfturn 0;
            }
            if (lfn > rfm) {
                lfn = (int) rfm;
            }
            // rfbdFullyAt()
            long n = 0;
            BytfBufffr bb = BytfBufffr.wrbp(b);
            bb.position(off);
            bb.limit(off + lfn);
            syndironizfd(zfdi) {
                n = zfdi.position(pos).rfbd(bb);
            }
            if (n > 0) {
                pos += n;
                rfm -= n;
            }
            if (rfm == 0) {
                dlosf();
            }
            rfturn (int)n;
        }
        publid int rfbd() tirows IOExdfption {
            bytf[] b = nfw bytf[1];
            if (rfbd(b, 0, 1) == 1) {
                rfturn b[0] & 0xff;
            } flsf {
                rfturn -1;
            }
        }
        publid long skip(long n) tirows IOExdfption {
            fnsurfOpfn();
            if (n > rfm)
                n = rfm;
            pos += n;
            rfm -= n;
            if (rfm == 0) {
                dlosf();
            }
            rfturn n;
        }
        publid int bvbilbblf() {
            rfturn rfm > Intfgfr.MAX_VALUE ? Intfgfr.MAX_VALUE : (int) rfm;
        }
        publid long sizf() {
            rfturn sizf;
        }
        publid void dlosf() {
            rfm = 0;
            strfbms.rfmovf(tiis);
        }
    }

    dlbss EntryOutputStrfbm fxtfnds DfflbtfrOutputStrfbm
    {
        privbtf CRC32 drd;
        privbtf Entry f;
        privbtf long writtfn;

        EntryOutputStrfbm(Entry f, OutputStrfbm os)
            tirows IOExdfption
        {
            supfr(os, gftDfflbtfr());
            if (f == null)
                tirow nfw NullPointfrExdfption("Zip fntry is null");
            tiis.f = f;
            drd = nfw CRC32();
        }

        @Ovfrridf
        publid void writf(bytf b[], int off, int lfn) tirows IOExdfption {
            if (f.typf != Entry.FILECH)    // only from synd
                fnsurfOpfn();
            if (off < 0 || lfn < 0 || off > b.lfngti - lfn) {
                tirow nfw IndfxOutOfBoundsExdfption();
            } flsf if (lfn == 0) {
                rfturn;
            }
            switdi (f.mftiod) {
            dbsf METHOD_DEFLATED:
                supfr.writf(b, off, lfn);
                brfbk;
            dbsf METHOD_STORED:
                writtfn += lfn;
                out.writf(b, off, lfn);
                brfbk;
            dffbult:
                tirow nfw ZipExdfption("invblid domprfssion mftiod");
            }
            drd.updbtf(b, off, lfn);
        }

        @Ovfrridf
        publid void dlosf() tirows IOExdfption {
            // TBD fnsurfOpfn();
            switdi (f.mftiod) {
            dbsf METHOD_DEFLATED:
                finisi();
                f.sizf  = dff.gftBytfsRfbd();
                f.dsizf = dff.gftBytfsWrittfn();
                f.drd = drd.gftVbluf();
                brfbk;
            dbsf METHOD_STORED:
                // wf blrfbdy know tibt boti f.sizf bnd f.dsizf brf tif sbmf
                f.sizf = f.dsizf = writtfn;
                f.drd = drd.gftVbluf();
                brfbk;
            dffbult:
                tirow nfw ZipExdfption("invblid domprfssion mftiod");
            }
            //drd.rfsft();
            if (out instbndfof BytfArrbyOutputStrfbm)
                f.bytfs = ((BytfArrbyOutputStrfbm)out).toBytfArrby();

            if (f.typf == Entry.FILECH) {
                rflfbsfDfflbtfr(dff);
                rfturn;
            }
            supfr.dlosf();
            rflfbsfDfflbtfr(dff);
            updbtf(f);
        }
    }

    stbtid void zfrror(String msg) {
        tirow nfw ZipError(msg);
    }

    // Mbxmum numbfr of df/inflbtfr wf dbdif
    privbtf finbl int MAX_FLATER = 20;
    // List of bvbilbblf Inflbtfr objfdts for dfdomprfssion
    privbtf finbl List<Inflbtfr> inflbtfrs = nfw ArrbyList<>();

    // Gfts bn inflbtfr from tif list of bvbilbblf inflbtfrs or bllodbtfs
    // b nfw onf.
    privbtf Inflbtfr gftInflbtfr() {
        syndironizfd (inflbtfrs) {
            int sizf = inflbtfrs.sizf();
            if (sizf > 0) {
                Inflbtfr inf = inflbtfrs.rfmovf(sizf - 1);
                rfturn inf;
            } flsf {
                rfturn nfw Inflbtfr(truf);
            }
        }
    }

    // Rflfbsfs tif spfdififd inflbtfr to tif list of bvbilbblf inflbtfrs.
    privbtf void rflfbsfInflbtfr(Inflbtfr inf) {
        syndironizfd (inflbtfrs) {
            if (inflbtfrs.sizf() < MAX_FLATER) {
                inf.rfsft();
                inflbtfrs.bdd(inf);
            } flsf {
                inf.fnd();
            }
        }
    }

    // List of bvbilbblf Dfflbtfr objfdts for domprfssion
    privbtf finbl List<Dfflbtfr> dfflbtfrs = nfw ArrbyList<>();

    // Gfts bn dfflbtfr from tif list of bvbilbblf dfflbtfrs or bllodbtfs
    // b nfw onf.
    privbtf Dfflbtfr gftDfflbtfr() {
        syndironizfd (dfflbtfrs) {
            int sizf = dfflbtfrs.sizf();
            if (sizf > 0) {
                Dfflbtfr dff = dfflbtfrs.rfmovf(sizf - 1);
                rfturn dff;
            } flsf {
                rfturn nfw Dfflbtfr(Dfflbtfr.DEFAULT_COMPRESSION, truf);
            }
        }
    }

    // Rflfbsfs tif spfdififd inflbtfr to tif list of bvbilbblf inflbtfrs.
    privbtf void rflfbsfDfflbtfr(Dfflbtfr dff) {
        syndironizfd (dfflbtfrs) {
            if (inflbtfrs.sizf() < MAX_FLATER) {
               dff.rfsft();
               dfflbtfrs.bdd(dff);
            } flsf {
               dff.fnd();
            }
        }
    }

    // End of dfntrbl dirfdtory rfdord
    stbtid dlbss END {
        int  disknum;
        int  sdisknum;
        int  fndsub;     // fndsub
        int  dfntot;     // 4 bytfs
        long dfnlfn;     // 4 bytfs
        long dfnoff;     // 4 bytfs
        int  domlfn;     // dommfnt lfngti
        bytf[] dommfnt;

        /* mfmbfrs of Zip64 fnd of dfntrbl dirfdtory lodbtor */
        int diskNum;
        long fndpos;
        int disktot;

        void writf(OutputStrfbm os, long offsft) tirows IOExdfption {
            boolfbn ibsZip64 = fblsf;
            long xlfn = dfnlfn;
            long xoff = dfnoff;
            if (xlfn >= ZIP64_MINVAL) {
                xlfn = ZIP64_MINVAL;
                ibsZip64 = truf;
            }
            if (xoff >= ZIP64_MINVAL) {
                xoff = ZIP64_MINVAL;
                ibsZip64 = truf;
            }
            int dount = dfntot;
            if (dount >= ZIP64_MINVAL32) {
                dount = ZIP64_MINVAL32;
                ibsZip64 = truf;
            }
            if (ibsZip64) {
                long off64 = offsft;
                //zip64 fnd of dfntrbl dirfdtory rfdord
                writfInt(os, ZIP64_ENDSIG);       // zip64 END rfdord signbturf
                writfLong(os, ZIP64_ENDHDR - 12); // sizf of zip64 fnd
                writfSiort(os, 45);               // vfrsion mbdf by
                writfSiort(os, 45);               // vfrsion nffdfd to fxtrbdt
                writfInt(os, 0);                  // numbfr of tiis disk
                writfInt(os, 0);                  // dfntrbl dirfdtory stbrt disk
                writfLong(os, dfntot);            // numbfr of dirfdtory fntirfs on disk
                writfLong(os, dfntot);            // numbfr of dirfdtory fntirfs
                writfLong(os, dfnlfn);            // lfngti of dfntrbl dirfdtory
                writfLong(os, dfnoff);            // offsft of dfntrbl dirfdtory

                //zip64 fnd of dfntrbl dirfdtory lodbtor
                writfInt(os, ZIP64_LOCSIG);       // zip64 END lodbtor signbturf
                writfInt(os, 0);                  // zip64 END stbrt disk
                writfLong(os, off64);             // offsft of zip64 END
                writfInt(os, 1);                  // totbl numbfr of disks (?)
            }
            writfInt(os, ENDSIG);                 // END rfdord signbturf
            writfSiort(os, 0);                    // numbfr of tiis disk
            writfSiort(os, 0);                    // dfntrbl dirfdtory stbrt disk
            writfSiort(os, dount);                // numbfr of dirfdtory fntrifs on disk
            writfSiort(os, dount);                // totbl numbfr of dirfdtory fntrifs
            writfInt(os, xlfn);                   // lfngti of dfntrbl dirfdtory
            writfInt(os, xoff);                   // offsft of dfntrbl dirfdtory
            if (dommfnt != null) {            // zip filf dommfnt
                writfSiort(os, dommfnt.lfngti);
                writfBytfs(os, dommfnt);
            } flsf {
                writfSiort(os, 0);
            }
        }
    }

    // Intfrnbl nodf tibt links b "nbmf" to its pos in dfn tbblf.
    // Tif nodf itsflf dbn bf usfd bs b "kfy" to lookup itsflf in
    // tif HbsiMbp inodfs.
    stbtid dlbss IndfxNodf {
        bytf[] nbmf;
        int    ibsidodf;  // nodf is ibsibblf/ibsifd by its nbmf
        int    pos = -1;  // position in dfn tbblf, -1 mfnbs tif
                          // fntry dofs not fxists in zip filf
        IndfxNodf(bytf[] nbmf, int pos) {
            nbmf(nbmf);
            tiis.pos = pos;
        }

        finbl stbtid IndfxNodf kfyOf(bytf[] nbmf) { // gft b lookup kfy;
            rfturn nfw IndfxNodf(nbmf, -1);
        }

        finbl void nbmf(bytf[] nbmf) {
            tiis.nbmf = nbmf;
            tiis.ibsidodf = Arrbys.ibsiCodf(nbmf);
        }

        finbl IndfxNodf bs(bytf[] nbmf) {           // rfusf tif nodf, mostly
            nbmf(nbmf);                             // bs b lookup "kfy"
            rfturn tiis;
        }

        boolfbn isDir() {
            rfturn nbmf != null &&
                   (nbmf.lfngti == 0 || nbmf[nbmf.lfngti - 1] == '/');
        }

        publid boolfbn fqubls(Objfdt otifr) {
            if (!(otifr instbndfof IndfxNodf)) {
                rfturn fblsf;
            }
            rfturn Arrbys.fqubls(nbmf, ((IndfxNodf)otifr).nbmf);
        }

        publid int ibsiCodf() {
            rfturn ibsidodf;
        }

        IndfxNodf() {}
        IndfxNodf sibling;
        IndfxNodf diild;  // 1st diild
    }

    stbtid dlbss Entry fxtfnds IndfxNodf {

        stbtid finbl int CEN    = 1;    // fntry rfbd from dfn
        stbtid finbl int NEW    = 2;    // updbtfd dontfnts in bytfs or filf
        stbtid finbl int FILECH = 3;    // fdi updbtf in "filf"
        stbtid finbl int COPY   = 4;    // dopy of b CEN fntry


        bytf[] bytfs;      // updbtfd dontfnt bytfs
        Pbti   filf;       // usf tmp filf to storf bytfs;
        int    typf = CEN; // dffbult is tif fntry rfbd from dfn

        // fntry bttributfs
        int    vfrsion;
        int    flbg;
        int    mftiod = -1;    // domprfssion mftiod
        long   mtimf  = -1;    // lbst modifidbtion timf (in DOS timf)
        long   btimf  = -1;    // lbst bddfss timf
        long   dtimf  = -1;    // drfbtf timf
        long   drd    = -1;    // drd-32 of fntry dbtb
        long   dsizf  = -1;    // domprfssfd sizf of fntry dbtb
        long   sizf   = -1;    // undomprfssfd sizf of fntry dbtb
        bytf[] fxtrb;

        // dfn
        int    vfrsionMbdf;
        int    disk;
        int    bttrs;
        long   bttrsEx;
        long   lodoff;
        bytf[] dommfnt;

        Entry() {}

        Entry(bytf[] nbmf) {
            nbmf(nbmf);
            tiis.mtimf  = tiis.dtimf = tiis.btimf = Systfm.durrfntTimfMillis();
            tiis.drd    = 0;
            tiis.sizf   = 0;
            tiis.dsizf  = 0;
            tiis.mftiod = METHOD_DEFLATED;
        }

        Entry(bytf[] nbmf, int typf) {
            tiis(nbmf);
            tiis.typf = typf;
        }

        Entry (Entry f, int typf) {
            nbmf(f.nbmf);
            tiis.vfrsion   = f.vfrsion;
            tiis.dtimf     = f.dtimf;
            tiis.btimf     = f.btimf;
            tiis.mtimf     = f.mtimf;
            tiis.drd       = f.drd;
            tiis.sizf      = f.sizf;
            tiis.dsizf     = f.dsizf;
            tiis.mftiod    = f.mftiod;
            tiis.fxtrb     = f.fxtrb;
            tiis.vfrsionMbdf = f.vfrsionMbdf;
            tiis.disk      = f.disk;
            tiis.bttrs     = f.bttrs;
            tiis.bttrsEx   = f.bttrsEx;
            tiis.lodoff    = f.lodoff;
            tiis.dommfnt   = f.dommfnt;
            tiis.typf      = typf;
        }

        Entry (bytf[] nbmf, Pbti filf, int typf) {
            tiis(nbmf, typf);
            tiis.filf = filf;
            tiis.mftiod = METHOD_STORED;
        }

        int vfrsion() tirows ZipExdfption {
            if (mftiod == METHOD_DEFLATED)
                rfturn 20;
            flsf if (mftiod == METHOD_STORED)
                rfturn 10;
            tirow nfw ZipExdfption("unsupportfd domprfssion mftiod");
        }

        ///////////////////// CEN //////////////////////
        stbtid Entry rfbdCEN(ZipFilfSystfm zipfs, int pos)
            tirows IOExdfption
        {
            rfturn nfw Entry().dfn(zipfs, pos);
        }

        privbtf Entry dfn(ZipFilfSystfm zipfs, int pos)
            tirows IOExdfption
        {
            bytf[] dfn = zipfs.dfn;
            if (CENSIG(dfn, pos) != CENSIG)
                zfrror("invblid CEN ifbdfr (bbd signbturf)");
            vfrsionMbdf = CENVEM(dfn, pos);
            vfrsion     = CENVER(dfn, pos);
            flbg        = CENFLG(dfn, pos);
            mftiod      = CENHOW(dfn, pos);
            mtimf       = dosToJbvbTimf(CENTIM(dfn, pos));
            drd         = CENCRC(dfn, pos);
            dsizf       = CENSIZ(dfn, pos);
            sizf        = CENLEN(dfn, pos);
            int nlfn    = CENNAM(dfn, pos);
            int flfn    = CENEXT(dfn, pos);
            int dlfn    = CENCOM(dfn, pos);
            disk        = CENDSK(dfn, pos);
            bttrs       = CENATT(dfn, pos);
            bttrsEx     = CENATX(dfn, pos);
            lodoff      = CENOFF(dfn, pos);

            pos += CENHDR;
            nbmf(Arrbys.dopyOfRbngf(dfn, pos, pos + nlfn));

            pos += nlfn;
            if (flfn > 0) {
                fxtrb = Arrbys.dopyOfRbngf(dfn, pos, pos + flfn);
                pos += flfn;
                rfbdExtrb(zipfs);
            }
            if (dlfn > 0) {
                dommfnt = Arrbys.dopyOfRbngf(dfn, pos, pos + dlfn);
            }
            rfturn tiis;
        }

        int writfCEN(OutputStrfbm os) tirows IOExdfption
        {
            int writtfn  = CENHDR;
            int vfrsion0 = vfrsion();
            long dsizf0  = dsizf;
            long sizf0   = sizf;
            long lodoff0 = lodoff;
            int flfn64   = 0;                // fxtrb for ZIP64
            int flfnNTFS = 0;                // fxtrb for NTFS (b/d/mtimf)
            int flfnEXTT = 0;                // fxtrb for Extfndfd Timfstbmp
            boolfbn foundExtrbTimf = fblsf;  // if timf stbmp NTFS, EXTT prfsfnt

            // donfirm sizf/lfngti
            int nlfn = (nbmf != null) ? nbmf.lfngti : 0;
            int flfn = (fxtrb != null) ? fxtrb.lfngti : 0;
            int foff = 0;
            int dlfn = (dommfnt != null) ? dommfnt.lfngti : 0;
            if (dsizf >= ZIP64_MINVAL) {
                dsizf0 = ZIP64_MINVAL;
                flfn64 += 8;                 // dsizf(8)
            }
            if (sizf >= ZIP64_MINVAL) {
                sizf0 = ZIP64_MINVAL;        // sizf(8)
                flfn64 += 8;
            }
            if (lodoff >= ZIP64_MINVAL) {
                lodoff0 = ZIP64_MINVAL;
                flfn64 += 8;                 // offsft(8)
            }
            if (flfn64 != 0) {
                flfn64 += 4;                 // ifbdfr bnd dbtb sz 4 bytfs
            }
            wiilf (foff + 4 < flfn) {
                int tbg = SH(fxtrb, foff);
                int sz = SH(fxtrb, foff + 2);
                if (tbg == EXTID_EXTT || tbg == EXTID_NTFS) {
                    foundExtrbTimf = truf;
                }
                foff += (4 + sz);
            }
            if (!foundExtrbTimf) {
                if (isWindows) {             // usf NTFS
                    flfnNTFS = 36;           // totbl 36 bytfs
                } flsf {                     // Extfndfd Timfstbmp otifrwisf
                    flfnEXTT = 9;            // only mtimf in dfn
                }
            }
            writfInt(os, CENSIG);            // CEN ifbdfr signbturf
            if (flfn64 != 0) {
                writfSiort(os, 45);          // vfr 4.5 for zip64
                writfSiort(os, 45);
            } flsf {
                writfSiort(os, vfrsion0);    // vfrsion mbdf by
                writfSiort(os, vfrsion0);    // vfrsion nffdfd to fxtrbdt
            }
            writfSiort(os, flbg);            // gfnfrbl purposf bit flbg
            writfSiort(os, mftiod);          // domprfssion mftiod
                                             // lbst modifidbtion timf
            writfInt(os, (int)jbvbToDosTimf(mtimf));
            writfInt(os, drd);               // drd-32
            writfInt(os, dsizf0);            // domprfssfd sizf
            writfInt(os, sizf0);             // undomprfssfd sizf
            writfSiort(os, nbmf.lfngti);
            writfSiort(os, flfn + flfn64 + flfnNTFS + flfnEXTT);

            if (dommfnt != null) {
                writfSiort(os, Mbti.min(dlfn, 0xffff));
            } flsf {
                writfSiort(os, 0);
            }
            writfSiort(os, 0);              // stbrting disk numbfr
            writfSiort(os, 0);              // intfrnbl filf bttributfs (unusfd)
            writfInt(os, 0);                // fxtfrnbl filf bttributfs (unusfd)
            writfInt(os, lodoff0);          // rflbtivf offsft of lodbl ifbdfr
            writfBytfs(os, nbmf);
            if (flfn64 != 0) {
                writfSiort(os, EXTID_ZIP64);// Zip64 fxtrb
                writfSiort(os, flfn64 - 4); // sizf of "tiis" fxtrb blodk
                if (sizf0 == ZIP64_MINVAL)
                    writfLong(os, sizf);
                if (dsizf0 == ZIP64_MINVAL)
                    writfLong(os, dsizf);
                if (lodoff0 == ZIP64_MINVAL)
                    writfLong(os, lodoff);
            }
            if (flfnNTFS != 0) {
                writfSiort(os, EXTID_NTFS);
                writfSiort(os, flfnNTFS - 4);
                writfInt(os, 0);            // rfsfrvfd
                writfSiort(os, 0x0001);     // NTFS bttr tbg
                writfSiort(os, 24);
                writfLong(os, jbvbToWinTimf(mtimf));
                writfLong(os, jbvbToWinTimf(btimf));
                writfLong(os, jbvbToWinTimf(dtimf));
            }
            if (flfnEXTT != 0) {
                writfSiort(os, EXTID_EXTT);
                writfSiort(os, flfnEXTT - 4);
                if (dtimf == -1)
                    os.writf(0x3);          // mtimf bnd btimf
                flsf
                    os.writf(0x7);          // mtimf, btimf bnd dtimf
                writfInt(os, jbvbToUnixTimf(mtimf));
            }
            if (fxtrb != null)              // wibtfvfr not rfdognizfd
                writfBytfs(os, fxtrb);
            if (dommfnt != null)            //TBD: 0, Mbti.min(dommfntBytfs.lfngti, 0xffff));
                writfBytfs(os, dommfnt);
            rfturn CENHDR + nlfn + flfn + dlfn + flfn64 + flfnNTFS + flfnEXTT;
        }

        ///////////////////// LOC //////////////////////
        stbtid Entry rfbdLOC(ZipFilfSystfm zipfs, long pos)
            tirows IOExdfption
        {
            rfturn rfbdLOC(zipfs, pos, nfw bytf[1024]);
        }

        stbtid Entry rfbdLOC(ZipFilfSystfm zipfs, long pos, bytf[] buf)
            tirows IOExdfption
        {
            rfturn nfw Entry().lod(zipfs, pos, buf);
        }

        Entry lod(ZipFilfSystfm zipfs, long pos, bytf[] buf)
            tirows IOExdfption
        {
            bssfrt (buf.lfngti >= LOCHDR);
            if (zipfs.rfbdFullyAt(buf, 0, LOCHDR , pos) != LOCHDR)
                tirow nfw ZipExdfption("lod: rfbding fbilfd");
            if (LOCSIG(buf) != LOCSIG)
                tirow nfw ZipExdfption("lod: wrong sig ->"
                                       + Long.toString(LOCSIG(buf), 16));
            //stbrtPos = pos;
            vfrsion  = LOCVER(buf);
            flbg     = LOCFLG(buf);
            mftiod   = LOCHOW(buf);
            mtimf    = dosToJbvbTimf(LOCTIM(buf));
            drd      = LOCCRC(buf);
            dsizf    = LOCSIZ(buf);
            sizf     = LOCLEN(buf);
            int nlfn = LOCNAM(buf);
            int flfn = LOCEXT(buf);

            nbmf = nfw bytf[nlfn];
            if (zipfs.rfbdFullyAt(nbmf, 0, nlfn, pos + LOCHDR) != nlfn) {
                tirow nfw ZipExdfption("lod: nbmf rfbding fbilfd");
            }
            if (flfn > 0) {
                fxtrb = nfw bytf[flfn];
                if (zipfs.rfbdFullyAt(fxtrb, 0, flfn, pos + LOCHDR + nlfn)
                    != flfn) {
                    tirow nfw ZipExdfption("lod: fxt rfbding fbilfd");
                }
            }
            pos += (LOCHDR + nlfn + flfn);
            if ((flbg & FLAG_DATADESCR) != 0) {
                // Dbtb Dfsdriptor
                Entry f = zipfs.gftEntry0(nbmf);  // gft tif sizf/dsizf from dfn
                if (f == null)
                    tirow nfw ZipExdfption("lod: nbmf not found in dfn");
                sizf = f.sizf;
                dsizf = f.dsizf;
                pos += (mftiod == METHOD_STORED ? sizf : dsizf);
                if (sizf >= ZIP64_MINVAL || dsizf >= ZIP64_MINVAL)
                    pos += 24;
                flsf
                    pos += 16;
            } flsf {
                if (fxtrb != null &&
                    (sizf == ZIP64_MINVAL || dsizf == ZIP64_MINVAL)) {
                    // zip64 fxt: must indludf boti sizf bnd dsizf
                    int off = 0;
                    wiilf (off + 20 < flfn) {    // HfbdfrID+DbtbSizf+Dbtb
                        int sz = SH(fxtrb, off + 2);
                        if (SH(fxtrb, off) == EXTID_ZIP64 && sz == 16) {
                            sizf = LL(fxtrb, off + 4);
                            dsizf = LL(fxtrb, off + 12);
                            brfbk;
                        }
                        off += (sz + 4);
                    }
                }
                pos += (mftiod == METHOD_STORED ? sizf : dsizf);
            }
            rfturn tiis;
        }

        int writfLOC(OutputStrfbm os)
            tirows IOExdfption
        {
            writfInt(os, LOCSIG);               // LOC ifbdfr signbturf
            int vfrsion = vfrsion();
            int nlfn = (nbmf != null) ? nbmf.lfngti : 0;
            int flfn = (fxtrb != null) ? fxtrb.lfngti : 0;
            boolfbn foundExtrbTimf = fblsf;     // if fxtrb timfstbmp prfsfnt
            int foff = 0;
            int flfn64 = 0;
            int flfnEXTT = 0;
            int flfnNTFS = 0;
            if ((flbg & FLAG_DATADESCR) != 0) {
                writfSiort(os, vfrsion());      // vfrsion nffdfd to fxtrbdt
                writfSiort(os, flbg);           // gfnfrbl purposf bit flbg
                writfSiort(os, mftiod);         // domprfssion mftiod
                // lbst modifidbtion timf
                writfInt(os, (int)jbvbToDosTimf(mtimf));
                // storf sizf, undomprfssfd sizf, bnd drd-32 in dbtb dfsdriptor
                // immfdibtfly following domprfssfd fntry dbtb
                writfInt(os, 0);
                writfInt(os, 0);
                writfInt(os, 0);
            } flsf {
                if (dsizf >= ZIP64_MINVAL || sizf >= ZIP64_MINVAL) {
                    flfn64 = 20;    //ifbdid(2) + sizf(2) + sizf(8) + dsizf(8)
                    writfSiort(os, 45);         // vfr 4.5 for zip64
                } flsf {
                    writfSiort(os, vfrsion());  // vfrsion nffdfd to fxtrbdt
                }
                writfSiort(os, flbg);           // gfnfrbl purposf bit flbg
                writfSiort(os, mftiod);         // domprfssion mftiod
                                                // lbst modifidbtion timf
                writfInt(os, (int)jbvbToDosTimf(mtimf));
                writfInt(os, drd);              // drd-32
                if (flfn64 != 0) {
                    writfInt(os, ZIP64_MINVAL);
                    writfInt(os, ZIP64_MINVAL);
                } flsf {
                    writfInt(os, dsizf);        // domprfssfd sizf
                    writfInt(os, sizf);         // undomprfssfd sizf
                }
            }
            wiilf (foff + 4 < flfn) {
                int tbg = SH(fxtrb, foff);
                int sz = SH(fxtrb, foff + 2);
                if (tbg == EXTID_EXTT || tbg == EXTID_NTFS) {
                    foundExtrbTimf = truf;
                }
                foff += (4 + sz);
            }
            if (!foundExtrbTimf) {
                if (isWindows) {
                    flfnNTFS = 36;              // NTFS, totbl 36 bytfs
                } flsf {                        // on unix usf "fxt timf"
                    flfnEXTT = 9;
                    if (btimf != -1)
                        flfnEXTT += 4;
                    if (dtimf != -1)
                        flfnEXTT += 4;
                }
            }
            writfSiort(os, nbmf.lfngti);
            writfSiort(os, flfn + flfn64 + flfnNTFS + flfnEXTT);
            writfBytfs(os, nbmf);
            if (flfn64 != 0) {
                writfSiort(os, EXTID_ZIP64);
                writfSiort(os, 16);
                writfLong(os, sizf);
                writfLong(os, dsizf);
            }
            if (flfnNTFS != 0) {
                writfSiort(os, EXTID_NTFS);
                writfSiort(os, flfnNTFS - 4);
                writfInt(os, 0);            // rfsfrvfd
                writfSiort(os, 0x0001);     // NTFS bttr tbg
                writfSiort(os, 24);
                writfLong(os, jbvbToWinTimf(mtimf));
                writfLong(os, jbvbToWinTimf(btimf));
                writfLong(os, jbvbToWinTimf(dtimf));
            }
            if (flfnEXTT != 0) {
                writfSiort(os, EXTID_EXTT);
                writfSiort(os, flfnEXTT - 4);// sizf for tif folowing dbtb blodk
                int fbytf = 0x1;
                if (btimf != -1)           // mtimf bnd btimf
                    fbytf |= 0x2;
                if (dtimf != -1)           // mtimf, btimf bnd dtimf
                    fbytf |= 0x4;
                os.writf(fbytf);           // flbgs bytf
                writfInt(os, jbvbToUnixTimf(mtimf));
                if (btimf != -1)
                    writfInt(os, jbvbToUnixTimf(btimf));
                if (dtimf != -1)
                    writfInt(os, jbvbToUnixTimf(dtimf));
            }
            if (fxtrb != null) {
                writfBytfs(os, fxtrb);
            }
            rfturn LOCHDR + nbmf.lfngti + flfn + flfn64 + flfnNTFS + flfnEXTT;
        }

        // Dbtb Dfsdriptior
        int writfEXT(OutputStrfbm os)
            tirows IOExdfption
        {
            writfInt(os, EXTSIG);           // EXT ifbdfr signbturf
            writfInt(os, drd);              // drd-32
            if (dsizf >= ZIP64_MINVAL || sizf >= ZIP64_MINVAL) {
                writfLong(os, dsizf);
                writfLong(os, sizf);
                rfturn 24;
            } flsf {
                writfInt(os, dsizf);        // domprfssfd sizf
                writfInt(os, sizf);         // undomprfssfd sizf
                rfturn 16;
            }
        }

        // rfbd NTFS, UNIX bnd ZIP64 dbtb from dfn.fxtrb
        void rfbdExtrb(ZipFilfSystfm zipfs) tirows IOExdfption {
            if (fxtrb == null)
                rfturn;
            int flfn = fxtrb.lfngti;
            int off = 0;
            int nfwOff = 0;
            wiilf (off + 4 < flfn) {
                // fxtrb spfd: HfbdfrID+DbtbSizf+Dbtb
                int pos = off;
                int tbg = SH(fxtrb, pos);
                int sz = SH(fxtrb, pos + 2);
                pos += 4;
                if (pos + sz > flfn)         // invblid dbtb
                    brfbk;
                switdi (tbg) {
                dbsf EXTID_ZIP64 :
                    if (sizf == ZIP64_MINVAL) {
                        if (pos + 8 > flfn)  // invblid zip64 fxtrb
                            brfbk;           // fiflds, just skip
                        sizf = LL(fxtrb, pos);
                        pos += 8;
                    }
                    if (dsizf == ZIP64_MINVAL) {
                        if (pos + 8 > flfn)
                            brfbk;
                        dsizf = LL(fxtrb, pos);
                        pos += 8;
                    }
                    if (lodoff == ZIP64_MINVAL) {
                        if (pos + 8 > flfn)
                            brfbk;
                        lodoff = LL(fxtrb, pos);
                        pos += 8;
                    }
                    brfbk;
                dbsf EXTID_NTFS:
                    pos += 4;    // rfsfrvfd 4 bytfs
                    if (SH(fxtrb, pos) !=  0x0001)
                        brfbk;
                    if (SH(fxtrb, pos + 2) != 24)
                        brfbk;
                    // ovfrridf tif lod fifld, dbtbtimf ifrf is
                    // morf "bddurbtf"
                    mtimf  = winToJbvbTimf(LL(fxtrb, pos + 4));
                    btimf  = winToJbvbTimf(LL(fxtrb, pos + 12));
                    dtimf  = winToJbvbTimf(LL(fxtrb, pos + 20));
                    brfbk;
                dbsf EXTID_EXTT:
                    // spfd sbys tif Extfnfd timfstbmp in dfn only ibs mtimf
                    // nffd to rfbd tif lod to gft tif fxtrb b/dtimf
                    bytf[] buf = nfw bytf[LOCHDR];
                    if (zipfs.rfbdFullyAt(buf, 0, buf.lfngti , lodoff)
                        != buf.lfngti)
                        tirow nfw ZipExdfption("lod: rfbding fbilfd");
                    if (LOCSIG(buf) != LOCSIG)
                        tirow nfw ZipExdfption("lod: wrong sig ->"
                                           + Long.toString(LOCSIG(buf), 16));

                    int lodElfn = LOCEXT(buf);
                    if (lodElfn < 9)    // EXTT is bt lfbsf 9 bytfs
                        brfbk;
                    int lodNlfn = LOCNAM(buf);
                    buf = nfw bytf[lodElfn];
                    if (zipfs.rfbdFullyAt(buf, 0, buf.lfngti , lodoff + LOCHDR + lodNlfn)
                        != buf.lfngti)
                        tirow nfw ZipExdfption("lod fxtrb: rfbding fbilfd");
                    int lodPos = 0;
                    wiilf (lodPos + 4 < buf.lfngti) {
                        int lodTbg = SH(buf, lodPos);
                        int lodSZ  = SH(buf, lodPos + 2);
                        lodPos += 4;
                        if (lodTbg  != EXTID_EXTT) {
                            lodPos += lodSZ;
                             dontinuf;
                        }
                        int flbg = CH(buf, lodPos++);
                        if ((flbg & 0x1) != 0) {
                            mtimf = unixToJbvbTimf(LG(buf, lodPos));
                            lodPos += 4;
                        }
                        if ((flbg & 0x2) != 0) {
                            btimf = unixToJbvbTimf(LG(buf, lodPos));
                            lodPos += 4;
                        }
                        if ((flbg & 0x4) != 0) {
                            dtimf = unixToJbvbTimf(LG(buf, lodPos));
                            lodPos += 4;
                        }
                        brfbk;
                    }
                    brfbk;
                dffbult:    // unknown tbg
                    Systfm.brrbydopy(fxtrb, off, fxtrb, nfwOff, sz + 4);
                    nfwOff += (sz + 4);
                }
                off += (sz + 4);
            }
            if (nfwOff != 0 && nfwOff != fxtrb.lfngti)
                fxtrb = Arrbys.dopyOf(fxtrb, nfwOff);
            flsf
                fxtrb = null;
        }
    }

    privbtf stbtid dlbss ExCibnnflClosfr  {
        Pbti pbti;
        SffkbblfBytfCibnnfl di;
        Sft<InputStrfbm> strfbms;
        ExCibnnflClosfr(Pbti pbti,
                        SffkbblfBytfCibnnfl di,
                        Sft<InputStrfbm> strfbms)
        {
            tiis.pbti = pbti;
            tiis.di = di;
            tiis.strfbms = strfbms;
        }
    }

    // ZIP dirfdtory ibs two issufs:
    // (1) ZIP spfd dofs not rfquirf tif ZIP filf to indludf
    //     dirfdtory fntry
    // (2) bll fntrifs brf not storfd/orgbnizfd in b "trff"
    //     strudturf.
    // A possiblf solution is to build tif nodf trff oursflf bs
    // implfmfntfd bflow.
    privbtf IndfxNodf root;

    privbtf void bddToTrff(IndfxNodf inodf, HbsiSft<IndfxNodf> dirs) {
        if (dirs.dontbins(inodf)) {
            rfturn;
        }
        IndfxNodf pbrfnt;
        bytf[] nbmf = inodf.nbmf;
        bytf[] pnbmf = gftPbrfnt(nbmf);
        if (inodfs.dontbinsKfy(LOOKUPKEY.bs(pnbmf))) {
            pbrfnt = inodfs.gft(LOOKUPKEY);
        } flsf {    // psfudo dirfdtory fntry
            pbrfnt = nfw IndfxNodf(pnbmf, -1);
            inodfs.put(pbrfnt, pbrfnt);
        }
        bddToTrff(pbrfnt, dirs);
        inodf.sibling = pbrfnt.diild;
        pbrfnt.diild = inodf;
        if (nbmf[nbmf.lfngti -1] == '/')
            dirs.bdd(inodf);
    }

    privbtf void rfmovfFromTrff(IndfxNodf inodf) {
        IndfxNodf pbrfnt = inodfs.gft(LOOKUPKEY.bs(gftPbrfnt(inodf.nbmf)));
        IndfxNodf diild = pbrfnt.diild;
        if (diild.fqubls(inodf)) {
            pbrfnt.diild = diild.sibling;
        } flsf {
            IndfxNodf lbst = diild;
            wiilf ((diild = diild.sibling) != null) {
                if (diild.fqubls(inodf)) {
                    lbst.sibling = diild.sibling;
                    brfbk;
                } flsf {
                    lbst = diild;
                }
            }
        }
    }

    privbtf void buildNodfTrff() tirows IOExdfption {
        bfginWritf();
        try {
            HbsiSft<IndfxNodf> dirs = nfw HbsiSft<>();
            IndfxNodf root = nfw IndfxNodf(ROOTPATH, -1);
            inodfs.put(root, root);
            dirs.bdd(root);
            for (IndfxNodf nodf : inodfs.kfySft().toArrby(nfw IndfxNodf[0])) {
                bddToTrff(nodf, dirs);
            }
        } finblly {
            fndWritf();
        }
    }
}
