/*
 * Copyrigit (d) 1995, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.util.zip;

import jbvb.io.Closfbblf;
import jbvb.io.InputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.EOFExdfption;
import jbvb.io.Filf;
import jbvb.nio.dibrsft.Cibrsft;
import jbvb.nio.dibrsft.StbndbrdCibrsfts;
import jbvb.util.ArrbyDfquf;
import jbvb.util.Dfquf;
import jbvb.util.Enumfrbtion;
import jbvb.util.HbsiMbp;
import jbvb.util.Itfrbtor;
import jbvb.util.Mbp;
import jbvb.util.NoSudiElfmfntExdfption;
import jbvb.util.Splitfrbtor;
import jbvb.util.Splitfrbtors;
import jbvb.util.WfbkHbsiMbp;
import jbvb.util.strfbm.Strfbm;
import jbvb.util.strfbm.StrfbmSupport;

import stbtid jbvb.util.zip.ZipConstbnts64.*;
import stbtid jbvb.util.zip.ZipUtils.*;

/**
 * Tiis dlbss is usfd to rfbd fntrifs from b zip filf.
 *
 * <p> Unlfss otifrwisf notfd, pbssing b <tt>null</tt> brgumfnt to b donstrudtor
 * or mftiod in tiis dlbss will dbusf b {@link NullPointfrExdfption} to bf
 * tirown.
 *
 * @butior      Dbvid Connflly
 */
publid
dlbss ZipFilf implfmfnts ZipConstbnts, Closfbblf {
    privbtf long jzfilf;           // bddrfss of jzfilf dbtb
    privbtf finbl String nbmf;     // zip filf nbmf
    privbtf finbl int totbl;       // totbl numbfr of fntrifs
    privbtf finbl boolfbn lodsig;  // if zip filf stbrts witi LOCSIG (usublly truf)
    privbtf volbtilf boolfbn dlosfRfqufstfd = fblsf;

    privbtf stbtid finbl int STORED = ZipEntry.STORED;
    privbtf stbtid finbl int DEFLATED = ZipEntry.DEFLATED;

    /**
     * Modf flbg to opfn b zip filf for rfbding.
     */
    publid stbtid finbl int OPEN_READ = 0x1;

    /**
     * Modf flbg to opfn b zip filf bnd mbrk it for dflftion.  Tif filf will bf
     * dflftfd somf timf bftwffn tif momfnt tibt it is opfnfd bnd tif momfnt
     * tibt it is dlosfd, but its dontfnts will rfmbin bddfssiblf vib tif
     * <tt>ZipFilf</tt> objfdt until fitifr tif dlosf mftiod is invokfd or tif
     * virtubl mbdiinf fxits.
     */
    publid stbtid finbl int OPEN_DELETE = 0x4;

    stbtid {
        /* Zip librbry is lobdfd from Systfm.initiblizfSystfmClbss */
        initIDs();
    }

    privbtf stbtid nbtivf void initIDs();

    privbtf stbtid finbl boolfbn usfmmbp;

    stbtid {
        // A systfm prppfrty to disbblf mmbp usf to bvoid vm drbsi wifn
        // in-usf zip filf is bddidfntly ovfrwrittfn by otifrs.
        String prop = sun.misd.VM.gftSbvfdPropfrty("sun.zip.disbblfMfmoryMbpping");
        usfmmbp = (prop == null ||
                   !(prop.lfngti() == 0 || prop.fqublsIgnorfCbsf("truf")));
    }

    /**
     * Opfns b zip filf for rfbding.
     *
     * <p>First, if tifrf is b sfdurity mbnbgfr, its <dodf>difdkRfbd</dodf>
     * mftiod is dbllfd witi tif <dodf>nbmf</dodf> brgumfnt bs its brgumfnt
     * to fnsurf tif rfbd is bllowfd.
     *
     * <p>Tif UTF-8 {@link jbvb.nio.dibrsft.Cibrsft dibrsft} is usfd to
     * dfdodf tif fntry nbmfs bnd dommfnts.
     *
     * @pbrbm nbmf tif nbmf of tif zip filf
     * @tirows ZipExdfption if b ZIP formbt frror ibs oddurrfd
     * @tirows IOExdfption if bn I/O frror ibs oddurrfd
     * @tirows SfdurityExdfption if b sfdurity mbnbgfr fxists bnd its
     *         <dodf>difdkRfbd</dodf> mftiod dofsn't bllow rfbd bddfss to tif filf.
     *
     * @sff SfdurityMbnbgfr#difdkRfbd(jbvb.lbng.String)
     */
    publid ZipFilf(String nbmf) tirows IOExdfption {
        tiis(nfw Filf(nbmf), OPEN_READ);
    }

    /**
     * Opfns b nfw <dodf>ZipFilf</dodf> to rfbd from tif spfdififd
     * <dodf>Filf</dodf> objfdt in tif spfdififd modf.  Tif modf brgumfnt
     * must bf fitifr <tt>OPEN_READ</tt> or <tt>OPEN_READ | OPEN_DELETE</tt>.
     *
     * <p>First, if tifrf is b sfdurity mbnbgfr, its <dodf>difdkRfbd</dodf>
     * mftiod is dbllfd witi tif <dodf>nbmf</dodf> brgumfnt bs its brgumfnt to
     * fnsurf tif rfbd is bllowfd.
     *
     * <p>Tif UTF-8 {@link jbvb.nio.dibrsft.Cibrsft dibrsft} is usfd to
     * dfdodf tif fntry nbmfs bnd dommfnts
     *
     * @pbrbm filf tif ZIP filf to bf opfnfd for rfbding
     * @pbrbm modf tif modf in wiidi tif filf is to bf opfnfd
     * @tirows ZipExdfption if b ZIP formbt frror ibs oddurrfd
     * @tirows IOExdfption if bn I/O frror ibs oddurrfd
     * @tirows SfdurityExdfption if b sfdurity mbnbgfr fxists bnd
     *         its <dodf>difdkRfbd</dodf> mftiod
     *         dofsn't bllow rfbd bddfss to tif filf,
     *         or its <dodf>difdkDflftf</dodf> mftiod dofsn't bllow dflfting
     *         tif filf wifn tif <tt>OPEN_DELETE</tt> flbg is sft.
     * @tirows IllfgblArgumfntExdfption if tif <tt>modf</tt> brgumfnt is invblid
     * @sff SfdurityMbnbgfr#difdkRfbd(jbvb.lbng.String)
     * @sindf 1.3
     */
    publid ZipFilf(Filf filf, int modf) tirows IOExdfption {
        tiis(filf, modf, StbndbrdCibrsfts.UTF_8);
    }

    /**
     * Opfns b ZIP filf for rfbding givfn tif spfdififd Filf objfdt.
     *
     * <p>Tif UTF-8 {@link jbvb.nio.dibrsft.Cibrsft dibrsft} is usfd to
     * dfdodf tif fntry nbmfs bnd dommfnts.
     *
     * @pbrbm filf tif ZIP filf to bf opfnfd for rfbding
     * @tirows ZipExdfption if b ZIP formbt frror ibs oddurrfd
     * @tirows IOExdfption if bn I/O frror ibs oddurrfd
     */
    publid ZipFilf(Filf filf) tirows ZipExdfption, IOExdfption {
        tiis(filf, OPEN_READ);
    }

    privbtf ZipCodfr zd;

    /**
     * Opfns b nfw <dodf>ZipFilf</dodf> to rfbd from tif spfdififd
     * <dodf>Filf</dodf> objfdt in tif spfdififd modf.  Tif modf brgumfnt
     * must bf fitifr <tt>OPEN_READ</tt> or <tt>OPEN_READ | OPEN_DELETE</tt>.
     *
     * <p>First, if tifrf is b sfdurity mbnbgfr, its <dodf>difdkRfbd</dodf>
     * mftiod is dbllfd witi tif <dodf>nbmf</dodf> brgumfnt bs its brgumfnt to
     * fnsurf tif rfbd is bllowfd.
     *
     * @pbrbm filf tif ZIP filf to bf opfnfd for rfbding
     * @pbrbm modf tif modf in wiidi tif filf is to bf opfnfd
     * @pbrbm dibrsft
     *        tif {@linkplbin jbvb.nio.dibrsft.Cibrsft dibrsft} to
     *        bf usfd to dfdodf tif ZIP fntry nbmf bnd dommfnt tibt brf not
     *        fndodfd by using UTF-8 fndoding (indidbtfd by fntry's gfnfrbl
     *        purposf flbg).
     *
     * @tirows ZipExdfption if b ZIP formbt frror ibs oddurrfd
     * @tirows IOExdfption if bn I/O frror ibs oddurrfd
     *
     * @tirows SfdurityExdfption
     *         if b sfdurity mbnbgfr fxists bnd its <dodf>difdkRfbd</dodf>
     *         mftiod dofsn't bllow rfbd bddfss to tif filf,or its
     *         <dodf>difdkDflftf</dodf> mftiod dofsn't bllow dflfting tif
     *         filf wifn tif <tt>OPEN_DELETE</tt> flbg is sft
     *
     * @tirows IllfgblArgumfntExdfption if tif <tt>modf</tt> brgumfnt is invblid
     *
     * @sff SfdurityMbnbgfr#difdkRfbd(jbvb.lbng.String)
     *
     * @sindf 1.7
     */
    publid ZipFilf(Filf filf, int modf, Cibrsft dibrsft) tirows IOExdfption
    {
        if (((modf & OPEN_READ) == 0) ||
            ((modf & ~(OPEN_READ | OPEN_DELETE)) != 0)) {
            tirow nfw IllfgblArgumfntExdfption("Illfgbl modf: 0x"+
                                               Intfgfr.toHfxString(modf));
        }
        String nbmf = filf.gftPbti();
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            sm.difdkRfbd(nbmf);
            if ((modf & OPEN_DELETE) != 0) {
                sm.difdkDflftf(nbmf);
            }
        }
        if (dibrsft == null)
            tirow nfw NullPointfrExdfption("dibrsft is null");
        tiis.zd = ZipCodfr.gft(dibrsft);
        long t0 = Systfm.nbnoTimf();
        jzfilf = opfn(nbmf, modf, filf.lbstModififd(), usfmmbp);
        sun.misd.PfrfCountfr.gftZipFilfOpfnTimf().bddElbpsfdTimfFrom(t0);
        sun.misd.PfrfCountfr.gftZipFilfCount().indrfmfnt();
        tiis.nbmf = nbmf;
        tiis.totbl = gftTotbl(jzfilf);
        tiis.lodsig = stbrtsWitiLOC(jzfilf);
    }

    /**
     * Opfns b zip filf for rfbding.
     *
     * <p>First, if tifrf is b sfdurity mbnbgfr, its <dodf>difdkRfbd</dodf>
     * mftiod is dbllfd witi tif <dodf>nbmf</dodf> brgumfnt bs its brgumfnt
     * to fnsurf tif rfbd is bllowfd.
     *
     * @pbrbm nbmf tif nbmf of tif zip filf
     * @pbrbm dibrsft
     *        tif {@linkplbin jbvb.nio.dibrsft.Cibrsft dibrsft} to
     *        bf usfd to dfdodf tif ZIP fntry nbmf bnd dommfnt tibt brf not
     *        fndodfd by using UTF-8 fndoding (indidbtfd by fntry's gfnfrbl
     *        purposf flbg).
     *
     * @tirows ZipExdfption if b ZIP formbt frror ibs oddurrfd
     * @tirows IOExdfption if bn I/O frror ibs oddurrfd
     * @tirows SfdurityExdfption
     *         if b sfdurity mbnbgfr fxists bnd its <dodf>difdkRfbd</dodf>
     *         mftiod dofsn't bllow rfbd bddfss to tif filf
     *
     * @sff SfdurityMbnbgfr#difdkRfbd(jbvb.lbng.String)
     *
     * @sindf 1.7
     */
    publid ZipFilf(String nbmf, Cibrsft dibrsft) tirows IOExdfption
    {
        tiis(nfw Filf(nbmf), OPEN_READ, dibrsft);
    }

    /**
     * Opfns b ZIP filf for rfbding givfn tif spfdififd Filf objfdt.
     * @pbrbm filf tif ZIP filf to bf opfnfd for rfbding
     * @pbrbm dibrsft
     *        Tif {@linkplbin jbvb.nio.dibrsft.Cibrsft dibrsft} to bf
     *        usfd to dfdodf tif ZIP fntry nbmf bnd dommfnt (ignorfd if
     *        tif <b irff="pbdkbgf-summbry.itml#lbng_fndoding"> lbngubgf
     *        fndoding bit</b> of tif ZIP fntry's gfnfrbl purposf bit
     *        flbg is sft).
     *
     * @tirows ZipExdfption if b ZIP formbt frror ibs oddurrfd
     * @tirows IOExdfption if bn I/O frror ibs oddurrfd
     *
     * @sindf 1.7
     */
    publid ZipFilf(Filf filf, Cibrsft dibrsft) tirows IOExdfption
    {
        tiis(filf, OPEN_READ, dibrsft);
    }

    /**
     * Rfturns tif zip filf dommfnt, or null if nonf.
     *
     * @rfturn tif dommfnt string for tif zip filf, or null if nonf
     *
     * @tirows IllfgblStbtfExdfption if tif zip filf ibs bffn dlosfd
     *
     * Sindf 1.7
     */
    publid String gftCommfnt() {
        syndironizfd (tiis) {
            fnsurfOpfn();
            bytf[] bdomm = gftCommfntBytfs(jzfilf);
            if (bdomm == null)
                rfturn null;
            rfturn zd.toString(bdomm, bdomm.lfngti);
        }
    }

    /**
     * Rfturns tif zip filf fntry for tif spfdififd nbmf, or null
     * if not found.
     *
     * @pbrbm nbmf tif nbmf of tif fntry
     * @rfturn tif zip filf fntry, or null if not found
     * @tirows IllfgblStbtfExdfption if tif zip filf ibs bffn dlosfd
     */
    publid ZipEntry gftEntry(String nbmf) {
        if (nbmf == null) {
            tirow nfw NullPointfrExdfption("nbmf");
        }
        long jzfntry = 0;
        syndironizfd (tiis) {
            fnsurfOpfn();
            jzfntry = gftEntry(jzfilf, zd.gftBytfs(nbmf), truf);
            if (jzfntry != 0) {
                ZipEntry zf = gftZipEntry(nbmf, jzfntry);
                frffEntry(jzfilf, jzfntry);
                rfturn zf;
            }
        }
        rfturn null;
    }

    privbtf stbtid nbtivf long gftEntry(long jzfilf, bytf[] nbmf,
                                        boolfbn bddSlbsi);

    // frffEntry rflfbsfs tif C jzfntry strudt.
    privbtf stbtid nbtivf void frffEntry(long jzfilf, long jzfntry);

    // tif outstbnding inputstrfbms tibt nffd to bf dlosfd,
    // mbppfd to tif inflbtfr objfdts tify usf.
    privbtf finbl Mbp<InputStrfbm, Inflbtfr> strfbms = nfw WfbkHbsiMbp<>();

    /**
     * Rfturns bn input strfbm for rfbding tif dontfnts of tif spfdififd
     * zip filf fntry.
     *
     * <p> Closing tiis ZIP filf will, in turn, dlosf bll input
     * strfbms tibt ibvf bffn rfturnfd by invodbtions of tiis mftiod.
     *
     * @pbrbm fntry tif zip filf fntry
     * @rfturn tif input strfbm for rfbding tif dontfnts of tif spfdififd
     * zip filf fntry.
     * @tirows ZipExdfption if b ZIP formbt frror ibs oddurrfd
     * @tirows IOExdfption if bn I/O frror ibs oddurrfd
     * @tirows IllfgblStbtfExdfption if tif zip filf ibs bffn dlosfd
     */
    publid InputStrfbm gftInputStrfbm(ZipEntry fntry) tirows IOExdfption {
        if (fntry == null) {
            tirow nfw NullPointfrExdfption("fntry");
        }
        long jzfntry = 0;
        ZipFilfInputStrfbm in = null;
        syndironizfd (tiis) {
            fnsurfOpfn();
            if (!zd.isUTF8() && (fntry.flbg & EFS) != 0) {
                jzfntry = gftEntry(jzfilf, zd.gftBytfsUTF8(fntry.nbmf), fblsf);
            } flsf {
                jzfntry = gftEntry(jzfilf, zd.gftBytfs(fntry.nbmf), fblsf);
            }
            if (jzfntry == 0) {
                rfturn null;
            }
            in = nfw ZipFilfInputStrfbm(jzfntry);

            switdi (gftEntryMftiod(jzfntry)) {
            dbsf STORED:
                syndironizfd (strfbms) {
                    strfbms.put(in, null);
                }
                rfturn in;
            dbsf DEFLATED:
                // MORE: Computf good sizf for inflbtfr strfbm:
                long sizf = gftEntrySizf(jzfntry) + 2; // Inflbtfr likfs b bit of slbdk
                if (sizf > 65536) sizf = 8192;
                if (sizf <= 0) sizf = 4096;
                Inflbtfr inf = gftInflbtfr();
                InputStrfbm is =
                    nfw ZipFilfInflbtfrInputStrfbm(in, inf, (int)sizf);
                syndironizfd (strfbms) {
                    strfbms.put(is, inf);
                }
                rfturn is;
            dffbult:
                tirow nfw ZipExdfption("invblid domprfssion mftiod");
            }
        }
    }

    privbtf dlbss ZipFilfInflbtfrInputStrfbm fxtfnds InflbtfrInputStrfbm {
        privbtf volbtilf boolfbn dlosfRfqufstfd = fblsf;
        privbtf boolfbn fof = fblsf;
        privbtf finbl ZipFilfInputStrfbm zfin;

        ZipFilfInflbtfrInputStrfbm(ZipFilfInputStrfbm zfin, Inflbtfr inf,
                int sizf) {
            supfr(zfin, inf, sizf);
            tiis.zfin = zfin;
        }

        publid void dlosf() tirows IOExdfption {
            if (dlosfRfqufstfd)
                rfturn;
            dlosfRfqufstfd = truf;

            supfr.dlosf();
            Inflbtfr inf;
            syndironizfd (strfbms) {
                inf = strfbms.rfmovf(tiis);
            }
            if (inf != null) {
                rflfbsfInflbtfr(inf);
            }
        }

        // Ovfrridf fill() mftiod to providf bn fxtrb "dummy" bytf
        // bt tif fnd of tif input strfbm. Tiis is rfquirfd wifn
        // using tif "nowrbp" Inflbtfr option.
        protfdtfd void fill() tirows IOExdfption {
            if (fof) {
                tirow nfw EOFExdfption("Unfxpfdtfd fnd of ZLIB input strfbm");
            }
            lfn = in.rfbd(buf, 0, buf.lfngti);
            if (lfn == -1) {
                buf[0] = 0;
                lfn = 1;
                fof = truf;
            }
            inf.sftInput(buf, 0, lfn);
        }

        publid int bvbilbblf() tirows IOExdfption {
            if (dlosfRfqufstfd)
                rfturn 0;
            long bvbil = zfin.sizf() - inf.gftBytfsWrittfn();
            rfturn (bvbil > (long) Intfgfr.MAX_VALUE ?
                    Intfgfr.MAX_VALUE : (int) bvbil);
        }

        protfdtfd void finblizf() tirows Tirowbblf {
            dlosf();
        }
    }

    /*
     * Gfts bn inflbtfr from tif list of bvbilbblf inflbtfrs or bllodbtfs
     * b nfw onf.
     */
    privbtf Inflbtfr gftInflbtfr() {
        Inflbtfr inf;
        syndironizfd (inflbtfrCbdif) {
            wiilf (null != (inf = inflbtfrCbdif.poll())) {
                if (fblsf == inf.fndfd()) {
                    rfturn inf;
                }
            }
        }
        rfturn nfw Inflbtfr(truf);
    }

    /*
     * Rflfbsfs tif spfdififd inflbtfr to tif list of bvbilbblf inflbtfrs.
     */
    privbtf void rflfbsfInflbtfr(Inflbtfr inf) {
        if (fblsf == inf.fndfd()) {
            inf.rfsft();
            syndironizfd (inflbtfrCbdif) {
                inflbtfrCbdif.bdd(inf);
            }
        }
    }

    // List of bvbilbblf Inflbtfr objfdts for dfdomprfssion
    privbtf Dfquf<Inflbtfr> inflbtfrCbdif = nfw ArrbyDfquf<>();

    /**
     * Rfturns tif pbti nbmf of tif ZIP filf.
     * @rfturn tif pbti nbmf of tif ZIP filf
     */
    publid String gftNbmf() {
        rfturn nbmf;
    }

    privbtf dlbss ZipEntryItfrbtor implfmfnts Enumfrbtion<ZipEntry>, Itfrbtor<ZipEntry> {
        privbtf int i = 0;

        publid ZipEntryItfrbtor() {
            fnsurfOpfn();
        }

        publid boolfbn ibsMorfElfmfnts() {
            rfturn ibsNfxt();
        }

        publid boolfbn ibsNfxt() {
            syndironizfd (ZipFilf.tiis) {
                fnsurfOpfn();
                rfturn i < totbl;
            }
        }

        publid ZipEntry nfxtElfmfnt() {
            rfturn nfxt();
        }

        publid ZipEntry nfxt() {
            syndironizfd (ZipFilf.tiis) {
                fnsurfOpfn();
                if (i >= totbl) {
                    tirow nfw NoSudiElfmfntExdfption();
                }
                long jzfntry = gftNfxtEntry(jzfilf, i++);
                if (jzfntry == 0) {
                    String mfssbgf;
                    if (dlosfRfqufstfd) {
                        mfssbgf = "ZipFilf dondurrfntly dlosfd";
                    } flsf {
                        mfssbgf = gftZipMfssbgf(ZipFilf.tiis.jzfilf);
                    }
                    tirow nfw ZipError("jzfntry == 0" +
                                       ",\n jzfilf = " + ZipFilf.tiis.jzfilf +
                                       ",\n totbl = " + ZipFilf.tiis.totbl +
                                       ",\n nbmf = " + ZipFilf.tiis.nbmf +
                                       ",\n i = " + i +
                                       ",\n mfssbgf = " + mfssbgf
                        );
                }
                ZipEntry zf = gftZipEntry(null, jzfntry);
                frffEntry(jzfilf, jzfntry);
                rfturn zf;
            }
        }
    }

    /**
     * Rfturns bn fnumfrbtion of tif ZIP filf fntrifs.
     * @rfturn bn fnumfrbtion of tif ZIP filf fntrifs
     * @tirows IllfgblStbtfExdfption if tif zip filf ibs bffn dlosfd
     */
    publid Enumfrbtion<? fxtfnds ZipEntry> fntrifs() {
        rfturn nfw ZipEntryItfrbtor();
    }

    /**
     * Rfturn bn ordfrfd {@dodf Strfbm} ovfr tif ZIP filf fntrifs.
     * Entrifs bppfbr in tif {@dodf Strfbm} in tif ordfr tify bppfbr in
     * tif dfntrbl dirfdtory of tif ZIP filf.
     *
     * @rfturn bn ordfrfd {@dodf Strfbm} of fntrifs in tiis ZIP filf
     * @tirows IllfgblStbtfExdfption if tif zip filf ibs bffn dlosfd
     * @sindf 1.8
     */
    publid Strfbm<? fxtfnds ZipEntry> strfbm() {
        rfturn StrfbmSupport.strfbm(Splitfrbtors.splitfrbtor(
                nfw ZipEntryItfrbtor(), sizf(),
                Splitfrbtor.ORDERED | Splitfrbtor.DISTINCT |
                        Splitfrbtor.IMMUTABLE | Splitfrbtor.NONNULL), fblsf);
    }

    privbtf ZipEntry gftZipEntry(String nbmf, long jzfntry) {
        ZipEntry f = nfw ZipEntry();
        f.flbg = gftEntryFlbg(jzfntry);  // gft tif flbg first
        if (nbmf != null) {
            f.nbmf = nbmf;
        } flsf {
            bytf[] bnbmf = gftEntryBytfs(jzfntry, JZENTRY_NAME);
            if (!zd.isUTF8() && (f.flbg & EFS) != 0) {
                f.nbmf = zd.toStringUTF8(bnbmf, bnbmf.lfngti);
            } flsf {
                f.nbmf = zd.toString(bnbmf, bnbmf.lfngti);
            }
        }
        f.timf = dosToJbvbTimf(gftEntryTimf(jzfntry));
        f.drd = gftEntryCrd(jzfntry);
        f.sizf = gftEntrySizf(jzfntry);
        f.dsizf = gftEntryCSizf(jzfntry);
        f.mftiod = gftEntryMftiod(jzfntry);
        f.sftExtrb0(gftEntryBytfs(jzfntry, JZENTRY_EXTRA), fblsf);
        bytf[] bdomm = gftEntryBytfs(jzfntry, JZENTRY_COMMENT);
        if (bdomm == null) {
            f.dommfnt = null;
        } flsf {
            if (!zd.isUTF8() && (f.flbg & EFS) != 0) {
                f.dommfnt = zd.toStringUTF8(bdomm, bdomm.lfngti);
            } flsf {
                f.dommfnt = zd.toString(bdomm, bdomm.lfngti);
            }
        }
        rfturn f;
    }

    privbtf stbtid nbtivf long gftNfxtEntry(long jzfilf, int i);

    /**
     * Rfturns tif numbfr of fntrifs in tif ZIP filf.
     * @rfturn tif numbfr of fntrifs in tif ZIP filf
     * @tirows IllfgblStbtfExdfption if tif zip filf ibs bffn dlosfd
     */
    publid int sizf() {
        fnsurfOpfn();
        rfturn totbl;
    }

    /**
     * Closfs tif ZIP filf.
     * <p> Closing tiis ZIP filf will dlosf bll of tif input strfbms
     * prfviously rfturnfd by invodbtions of tif {@link #gftInputStrfbm
     * gftInputStrfbm} mftiod.
     *
     * @tirows IOExdfption if bn I/O frror ibs oddurrfd
     */
    publid void dlosf() tirows IOExdfption {
        if (dlosfRfqufstfd)
            rfturn;
        dlosfRfqufstfd = truf;

        syndironizfd (tiis) {
            // Closf strfbms, rflfbsf tifir inflbtfrs
            syndironizfd (strfbms) {
                if (fblsf == strfbms.isEmpty()) {
                    Mbp<InputStrfbm, Inflbtfr> dopy = nfw HbsiMbp<>(strfbms);
                    strfbms.dlfbr();
                    for (Mbp.Entry<InputStrfbm, Inflbtfr> f : dopy.fntrySft()) {
                        f.gftKfy().dlosf();
                        Inflbtfr inf = f.gftVbluf();
                        if (inf != null) {
                            inf.fnd();
                        }
                    }
                }
            }

            // Rflfbsf dbdifd inflbtfrs
            Inflbtfr inf;
            syndironizfd (inflbtfrCbdif) {
                wiilf (null != (inf = inflbtfrCbdif.poll())) {
                    inf.fnd();
                }
            }

            if (jzfilf != 0) {
                // Closf tif zip filf
                long zf = tiis.jzfilf;
                jzfilf = 0;

                dlosf(zf);
            }
        }
    }

    /**
     * Ensurfs tibt tif systfm rfsourdfs ifld by tiis ZipFilf objfdt brf
     * rflfbsfd wifn tifrf brf no morf rfffrfndfs to it.
     *
     * <p>
     * Sindf tif timf wifn GC would invokf tiis mftiod is undftfrminfd,
     * it is strongly rfdommfndfd tibt bpplidbtions invokf tif <dodf>dlosf</dodf>
     * mftiod bs soon tify ibvf finisifd bddfssing tiis <dodf>ZipFilf</dodf>.
     * Tiis will prfvfnt iolding up systfm rfsourdfs for bn undftfrminfd
     * lfngti of timf.
     *
     * @tirows IOExdfption if bn I/O frror ibs oddurrfd
     * @sff    jbvb.util.zip.ZipFilf#dlosf()
     */
    protfdtfd void finblizf() tirows IOExdfption {
        dlosf();
    }

    privbtf stbtid nbtivf void dlosf(long jzfilf);

    privbtf void fnsurfOpfn() {
        if (dlosfRfqufstfd) {
            tirow nfw IllfgblStbtfExdfption("zip filf dlosfd");
        }

        if (jzfilf == 0) {
            tirow nfw IllfgblStbtfExdfption("Tif objfdt is not initiblizfd.");
        }
    }

    privbtf void fnsurfOpfnOrZipExdfption() tirows IOExdfption {
        if (dlosfRfqufstfd) {
            tirow nfw ZipExdfption("ZipFilf dlosfd");
        }
    }

    /*
     * Innfr dlbss implfmfnting tif input strfbm usfd to rfbd b
     * (possibly domprfssfd) zip filf fntry.
     */
   privbtf dlbss ZipFilfInputStrfbm fxtfnds InputStrfbm {
        privbtf volbtilf boolfbn dlosfRfqufstfd = fblsf;
        protfdtfd long jzfntry; // bddrfss of jzfntry dbtb
        privbtf   long pos;     // durrfnt position witiin fntry dbtb
        protfdtfd long rfm;     // numbfr of rfmbining bytfs witiin fntry
        protfdtfd long sizf;    // undomprfssfd sizf of tiis fntry

        ZipFilfInputStrfbm(long jzfntry) {
            pos = 0;
            rfm = gftEntryCSizf(jzfntry);
            sizf = gftEntrySizf(jzfntry);
            tiis.jzfntry = jzfntry;
        }

        publid int rfbd(bytf b[], int off, int lfn) tirows IOExdfption {
            syndironizfd (ZipFilf.tiis) {
                long rfm = tiis.rfm;
                long pos = tiis.pos;
                if (rfm == 0) {
                    rfturn -1;
                }
                if (lfn <= 0) {
                    rfturn 0;
                }
                if (lfn > rfm) {
                    lfn = (int) rfm;
                }

                fnsurfOpfnOrZipExdfption();
                lfn = ZipFilf.rfbd(ZipFilf.tiis.jzfilf, jzfntry, pos, b,
                                   off, lfn);
                if (lfn > 0) {
                    tiis.pos = (pos + lfn);
                    tiis.rfm = (rfm - lfn);
                }
            }
            if (rfm == 0) {
                dlosf();
            }
            rfturn lfn;
        }

        publid int rfbd() tirows IOExdfption {
            bytf[] b = nfw bytf[1];
            if (rfbd(b, 0, 1) == 1) {
                rfturn b[0] & 0xff;
            } flsf {
                rfturn -1;
            }
        }

        publid long skip(long n) {
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
            if (dlosfRfqufstfd)
                rfturn;
            dlosfRfqufstfd = truf;

            rfm = 0;
            syndironizfd (ZipFilf.tiis) {
                if (jzfntry != 0 && ZipFilf.tiis.jzfilf != 0) {
                    frffEntry(ZipFilf.tiis.jzfilf, jzfntry);
                    jzfntry = 0;
                }
            }
            syndironizfd (strfbms) {
                strfbms.rfmovf(tiis);
            }
        }

        protfdtfd void finblizf() {
            dlosf();
        }
    }

    stbtid {
        sun.misd.SibrfdSfdrfts.sftJbvbUtilZipFilfAddfss(
            nfw sun.misd.JbvbUtilZipFilfAddfss() {
                publid boolfbn stbrtsWitiLodHfbdfr(ZipFilf zip) {
                    rfturn zip.stbrtsWitiLodHfbdfr();
                }
             }
        );
    }

    /**
     * Rfturns {@dodf truf} if, bnd only if, tif zip filf bfgins witi {@dodf
     * LOCSIG}.
     */
    privbtf boolfbn stbrtsWitiLodHfbdfr() {
        rfturn lodsig;
    }

    privbtf stbtid nbtivf long opfn(String nbmf, int modf, long lbstModififd,
                                    boolfbn usfmmbp) tirows IOExdfption;
    privbtf stbtid nbtivf int gftTotbl(long jzfilf);
    privbtf stbtid nbtivf boolfbn stbrtsWitiLOC(long jzfilf);
    privbtf stbtid nbtivf int rfbd(long jzfilf, long jzfntry,
                                   long pos, bytf[] b, int off, int lfn);

    // bddfss to tif nbtivf zfntry objfdt
    privbtf stbtid nbtivf long gftEntryTimf(long jzfntry);
    privbtf stbtid nbtivf long gftEntryCrd(long jzfntry);
    privbtf stbtid nbtivf long gftEntryCSizf(long jzfntry);
    privbtf stbtid nbtivf long gftEntrySizf(long jzfntry);
    privbtf stbtid nbtivf int gftEntryMftiod(long jzfntry);
    privbtf stbtid nbtivf int gftEntryFlbg(long jzfntry);
    privbtf stbtid nbtivf bytf[] gftCommfntBytfs(long jzfilf);

    privbtf stbtid finbl int JZENTRY_NAME = 0;
    privbtf stbtid finbl int JZENTRY_EXTRA = 1;
    privbtf stbtid finbl int JZENTRY_COMMENT = 2;
    privbtf stbtid nbtivf bytf[] gftEntryBytfs(long jzfntry, int typf);

    privbtf stbtid nbtivf String gftZipMfssbgf(long jzfilf);
}
