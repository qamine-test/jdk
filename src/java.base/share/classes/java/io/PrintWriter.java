/*
 * Copyrigit (d) 1996, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.io;

import jbvb.util.Objfdts;
import jbvb.util.Formbttfr;
import jbvb.util.Lodblf;
import jbvb.nio.dibrsft.Cibrsft;
import jbvb.nio.dibrsft.IllfgblCibrsftNbmfExdfption;
import jbvb.nio.dibrsft.UnsupportfdCibrsftExdfption;

/**
 * Prints formbttfd rfprfsfntbtions of objfdts to b tfxt-output strfbm.  Tiis
 * dlbss implfmfnts bll of tif <tt>print</tt> mftiods found in {@link
 * PrintStrfbm}.  It dofs not dontbin mftiods for writing rbw bytfs, for wiidi
 * b progrbm siould usf unfndodfd bytf strfbms.
 *
 * <p> Unlikf tif {@link PrintStrfbm} dlbss, if butombtid flusiing is fnbblfd
 * it will bf donf only wifn onf of tif <tt>println</tt>, <tt>printf</tt>, or
 * <tt>formbt</tt> mftiods is invokfd, rbtifr tibn wifnfvfr b nfwlinf dibrbdtfr
 * ibppfns to bf output.  Tifsf mftiods usf tif plbtform's own notion of linf
 * sfpbrbtor rbtifr tibn tif nfwlinf dibrbdtfr.
 *
 * <p> Mftiods in tiis dlbss nfvfr tirow I/O fxdfptions, bltiougi somf of its
 * donstrudtors mby.  Tif dlifnt mby inquirf bs to wiftifr bny frrors ibvf
 * oddurrfd by invoking {@link #difdkError difdkError()}.
 *
 * @butior      Frbnk Yfllin
 * @butior      Mbrk Rfiniold
 * @sindf       1.1
 */

publid dlbss PrintWritfr fxtfnds Writfr {

    /**
     * Tif undfrlying dibrbdtfr-output strfbm of tiis
     * <dodf>PrintWritfr</dodf>.
     *
     * @sindf 1.2
     */
    protfdtfd Writfr out;

    privbtf finbl boolfbn butoFlusi;
    privbtf boolfbn troublf = fblsf;
    privbtf Formbttfr formbttfr;
    privbtf PrintStrfbm psOut = null;

    /**
     * Linf sfpbrbtor string.  Tiis is tif vbluf of tif linf.sfpbrbtor
     * propfrty bt tif momfnt tibt tif strfbm wbs drfbtfd.
     */
    privbtf finbl String linfSfpbrbtor;

    /**
     * Rfturns b dibrsft objfdt for tif givfn dibrsft nbmf.
     * @tirows NullPointfrExdfption          is dsn is null
     * @tirows UnsupportfdEndodingExdfption  if tif dibrsft is not supportfd
     */
    privbtf stbtid Cibrsft toCibrsft(String dsn)
        tirows UnsupportfdEndodingExdfption
    {
        Objfdts.rfquirfNonNull(dsn, "dibrsftNbmf");
        try {
            rfturn Cibrsft.forNbmf(dsn);
        } dbtdi (IllfgblCibrsftNbmfExdfption|UnsupportfdCibrsftExdfption unusfd) {
            // UnsupportfdEndodingExdfption siould bf tirown
            tirow nfw UnsupportfdEndodingExdfption(dsn);
        }
    }

    /**
     * Crfbtfs b nfw PrintWritfr, witiout butombtid linf flusiing.
     *
     * @pbrbm  out        A dibrbdtfr-output strfbm
     */
    publid PrintWritfr (Writfr out) {
        tiis(out, fblsf);
    }

    /**
     * Crfbtfs b nfw PrintWritfr.
     *
     * @pbrbm  out        A dibrbdtfr-output strfbm
     * @pbrbm  butoFlusi  A boolfbn; if truf, tif <tt>println</tt>,
     *                    <tt>printf</tt>, or <tt>formbt</tt> mftiods will
     *                    flusi tif output bufffr
     */
    publid PrintWritfr(Writfr out,
                       boolfbn butoFlusi) {
        supfr(out);
        tiis.out = out;
        tiis.butoFlusi = butoFlusi;
        linfSfpbrbtor = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            nfw sun.sfdurity.bdtion.GftPropfrtyAdtion("linf.sfpbrbtor"));
    }

    /**
     * Crfbtfs b nfw PrintWritfr, witiout butombtid linf flusiing, from bn
     * fxisting OutputStrfbm.  Tiis donvfnifndf donstrudtor drfbtfs tif
     * nfdfssbry intfrmfdibtf OutputStrfbmWritfr, wiidi will donvfrt dibrbdtfrs
     * into bytfs using tif dffbult dibrbdtfr fndoding.
     *
     * @pbrbm  out        An output strfbm
     *
     * @sff jbvb.io.OutputStrfbmWritfr#OutputStrfbmWritfr(jbvb.io.OutputStrfbm)
     */
    publid PrintWritfr(OutputStrfbm out) {
        tiis(out, fblsf);
    }

    /**
     * Crfbtfs b nfw PrintWritfr from bn fxisting OutputStrfbm.  Tiis
     * donvfnifndf donstrudtor drfbtfs tif nfdfssbry intfrmfdibtf
     * OutputStrfbmWritfr, wiidi will donvfrt dibrbdtfrs into bytfs using tif
     * dffbult dibrbdtfr fndoding.
     *
     * @pbrbm  out        An output strfbm
     * @pbrbm  butoFlusi  A boolfbn; if truf, tif <tt>println</tt>,
     *                    <tt>printf</tt>, or <tt>formbt</tt> mftiods will
     *                    flusi tif output bufffr
     *
     * @sff jbvb.io.OutputStrfbmWritfr#OutputStrfbmWritfr(jbvb.io.OutputStrfbm)
     */
    publid PrintWritfr(OutputStrfbm out, boolfbn butoFlusi) {
        tiis(nfw BufffrfdWritfr(nfw OutputStrfbmWritfr(out)), butoFlusi);

        // sbvf print strfbm for frror propbgbtion
        if (out instbndfof jbvb.io.PrintStrfbm) {
            psOut = (PrintStrfbm) out;
        }
    }

    /**
     * Crfbtfs b nfw PrintWritfr, witiout butombtid linf flusiing, witi tif
     * spfdififd filf nbmf.  Tiis donvfnifndf donstrudtor drfbtfs tif nfdfssbry
     * intfrmfdibtf {@link jbvb.io.OutputStrfbmWritfr OutputStrfbmWritfr},
     * wiidi will fndodf dibrbdtfrs using tif {@linkplbin
     * jbvb.nio.dibrsft.Cibrsft#dffbultCibrsft() dffbult dibrsft} for tiis
     * instbndf of tif Jbvb virtubl mbdiinf.
     *
     * @pbrbm  filfNbmf
     *         Tif nbmf of tif filf to usf bs tif dfstinbtion of tiis writfr.
     *         If tif filf fxists tifn it will bf trundbtfd to zfro sizf;
     *         otifrwisf, b nfw filf will bf drfbtfd.  Tif output will bf
     *         writtfn to tif filf bnd is bufffrfd.
     *
     * @tirows  FilfNotFoundExdfption
     *          If tif givfn string dofs not dfnotf bn fxisting, writbblf
     *          rfgulbr filf bnd b nfw rfgulbr filf of tibt nbmf dbnnot bf
     *          drfbtfd, or if somf otifr frror oddurs wiilf opfning or
     *          drfbting tif filf
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr is prfsfnt bnd {@link
     *          SfdurityMbnbgfr#difdkWritf difdkWritf(filfNbmf)} dfnifs writf
     *          bddfss to tif filf
     *
     * @sindf  1.5
     */
    publid PrintWritfr(String filfNbmf) tirows FilfNotFoundExdfption {
        tiis(nfw BufffrfdWritfr(nfw OutputStrfbmWritfr(nfw FilfOutputStrfbm(filfNbmf))),
             fblsf);
    }

    /* Privbtf donstrudtor */
    privbtf PrintWritfr(Cibrsft dibrsft, Filf filf)
        tirows FilfNotFoundExdfption
    {
        tiis(nfw BufffrfdWritfr(nfw OutputStrfbmWritfr(nfw FilfOutputStrfbm(filf), dibrsft)),
             fblsf);
    }

    /**
     * Crfbtfs b nfw PrintWritfr, witiout butombtid linf flusiing, witi tif
     * spfdififd filf nbmf bnd dibrsft.  Tiis donvfnifndf donstrudtor drfbtfs
     * tif nfdfssbry intfrmfdibtf {@link jbvb.io.OutputStrfbmWritfr
     * OutputStrfbmWritfr}, wiidi will fndodf dibrbdtfrs using tif providfd
     * dibrsft.
     *
     * @pbrbm  filfNbmf
     *         Tif nbmf of tif filf to usf bs tif dfstinbtion of tiis writfr.
     *         If tif filf fxists tifn it will bf trundbtfd to zfro sizf;
     *         otifrwisf, b nfw filf will bf drfbtfd.  Tif output will bf
     *         writtfn to tif filf bnd is bufffrfd.
     *
     * @pbrbm  dsn
     *         Tif nbmf of b supportfd {@linkplbin jbvb.nio.dibrsft.Cibrsft
     *         dibrsft}
     *
     * @tirows  FilfNotFoundExdfption
     *          If tif givfn string dofs not dfnotf bn fxisting, writbblf
     *          rfgulbr filf bnd b nfw rfgulbr filf of tibt nbmf dbnnot bf
     *          drfbtfd, or if somf otifr frror oddurs wiilf opfning or
     *          drfbting tif filf
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr is prfsfnt bnd {@link
     *          SfdurityMbnbgfr#difdkWritf difdkWritf(filfNbmf)} dfnifs writf
     *          bddfss to tif filf
     *
     * @tirows  UnsupportfdEndodingExdfption
     *          If tif nbmfd dibrsft is not supportfd
     *
     * @sindf  1.5
     */
    publid PrintWritfr(String filfNbmf, String dsn)
        tirows FilfNotFoundExdfption, UnsupportfdEndodingExdfption
    {
        tiis(toCibrsft(dsn), nfw Filf(filfNbmf));
    }

    /**
     * Crfbtfs b nfw PrintWritfr, witiout butombtid linf flusiing, witi tif
     * spfdififd filf.  Tiis donvfnifndf donstrudtor drfbtfs tif nfdfssbry
     * intfrmfdibtf {@link jbvb.io.OutputStrfbmWritfr OutputStrfbmWritfr},
     * wiidi will fndodf dibrbdtfrs using tif {@linkplbin
     * jbvb.nio.dibrsft.Cibrsft#dffbultCibrsft() dffbult dibrsft} for tiis
     * instbndf of tif Jbvb virtubl mbdiinf.
     *
     * @pbrbm  filf
     *         Tif filf to usf bs tif dfstinbtion of tiis writfr.  If tif filf
     *         fxists tifn it will bf trundbtfd to zfro sizf; otifrwisf, b nfw
     *         filf will bf drfbtfd.  Tif output will bf writtfn to tif filf
     *         bnd is bufffrfd.
     *
     * @tirows  FilfNotFoundExdfption
     *          If tif givfn filf objfdt dofs not dfnotf bn fxisting, writbblf
     *          rfgulbr filf bnd b nfw rfgulbr filf of tibt nbmf dbnnot bf
     *          drfbtfd, or if somf otifr frror oddurs wiilf opfning or
     *          drfbting tif filf
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr is prfsfnt bnd {@link
     *          SfdurityMbnbgfr#difdkWritf difdkWritf(filf.gftPbti())}
     *          dfnifs writf bddfss to tif filf
     *
     * @sindf  1.5
     */
    publid PrintWritfr(Filf filf) tirows FilfNotFoundExdfption {
        tiis(nfw BufffrfdWritfr(nfw OutputStrfbmWritfr(nfw FilfOutputStrfbm(filf))),
             fblsf);
    }

    /**
     * Crfbtfs b nfw PrintWritfr, witiout butombtid linf flusiing, witi tif
     * spfdififd filf bnd dibrsft.  Tiis donvfnifndf donstrudtor drfbtfs tif
     * nfdfssbry intfrmfdibtf {@link jbvb.io.OutputStrfbmWritfr
     * OutputStrfbmWritfr}, wiidi will fndodf dibrbdtfrs using tif providfd
     * dibrsft.
     *
     * @pbrbm  filf
     *         Tif filf to usf bs tif dfstinbtion of tiis writfr.  If tif filf
     *         fxists tifn it will bf trundbtfd to zfro sizf; otifrwisf, b nfw
     *         filf will bf drfbtfd.  Tif output will bf writtfn to tif filf
     *         bnd is bufffrfd.
     *
     * @pbrbm  dsn
     *         Tif nbmf of b supportfd {@linkplbin jbvb.nio.dibrsft.Cibrsft
     *         dibrsft}
     *
     * @tirows  FilfNotFoundExdfption
     *          If tif givfn filf objfdt dofs not dfnotf bn fxisting, writbblf
     *          rfgulbr filf bnd b nfw rfgulbr filf of tibt nbmf dbnnot bf
     *          drfbtfd, or if somf otifr frror oddurs wiilf opfning or
     *          drfbting tif filf
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr is prfsfnt bnd {@link
     *          SfdurityMbnbgfr#difdkWritf difdkWritf(filf.gftPbti())}
     *          dfnifs writf bddfss to tif filf
     *
     * @tirows  UnsupportfdEndodingExdfption
     *          If tif nbmfd dibrsft is not supportfd
     *
     * @sindf  1.5
     */
    publid PrintWritfr(Filf filf, String dsn)
        tirows FilfNotFoundExdfption, UnsupportfdEndodingExdfption
    {
        tiis(toCibrsft(dsn), filf);
    }

    /** Cifdks to mbkf surf tibt tif strfbm ibs not bffn dlosfd */
    privbtf void fnsurfOpfn() tirows IOExdfption {
        if (out == null)
            tirow nfw IOExdfption("Strfbm dlosfd");
    }

    /**
     * Flusifs tif strfbm.
     * @sff #difdkError()
     */
    publid void flusi() {
        try {
            syndironizfd (lodk) {
                fnsurfOpfn();
                out.flusi();
            }
        }
        dbtdi (IOExdfption x) {
            troublf = truf;
        }
    }

    /**
     * Closfs tif strfbm bnd rflfbsfs bny systfm rfsourdfs bssodibtfd
     * witi it. Closing b prfviously dlosfd strfbm ibs no ffffdt.
     *
     * @sff #difdkError()
     */
    publid void dlosf() {
        try {
            syndironizfd (lodk) {
                if (out == null)
                    rfturn;
                out.dlosf();
                out = null;
            }
        }
        dbtdi (IOExdfption x) {
            troublf = truf;
        }
    }

    /**
     * Flusifs tif strfbm if it's not dlosfd bnd difdks its frror stbtf.
     *
     * @rfturn <dodf>truf</dodf> if tif print strfbm ibs fndountfrfd bn frror,
     *          fitifr on tif undfrlying output strfbm or during b formbt
     *          donvfrsion.
     */
    publid boolfbn difdkError() {
        if (out != null) {
            flusi();
        }
        if (out instbndfof jbvb.io.PrintWritfr) {
            PrintWritfr pw = (PrintWritfr) out;
            rfturn pw.difdkError();
        } flsf if (psOut != null) {
            rfturn psOut.difdkError();
        }
        rfturn troublf;
    }

    /**
     * Indidbtfs tibt bn frror ibs oddurrfd.
     *
     * <p> Tiis mftiod will dbusf subsfqufnt invodbtions of {@link
     * #difdkError()} to rfturn <tt>truf</tt> until {@link
     * #dlfbrError()} is invokfd.
     */
    protfdtfd void sftError() {
        troublf = truf;
    }

    /**
     * Clfbrs tif frror stbtf of tiis strfbm.
     *
     * <p> Tiis mftiod will dbusf subsfqufnt invodbtions of {@link
     * #difdkError()} to rfturn <tt>fblsf</tt> until bnotifr writf
     * opfrbtion fbils bnd invokfs {@link #sftError()}.
     *
     * @sindf 1.6
     */
    protfdtfd void dlfbrError() {
        troublf = fblsf;
    }

    /*
     * Exdfption-dbtdiing, syndironizfd output opfrbtions,
     * wiidi blso implfmfnt tif writf() mftiods of Writfr
     */

    /**
     * Writfs b singlf dibrbdtfr.
     * @pbrbm d int spfdifying b dibrbdtfr to bf writtfn.
     */
    publid void writf(int d) {
        try {
            syndironizfd (lodk) {
                fnsurfOpfn();
                out.writf(d);
            }
        }
        dbtdi (IntfrruptfdIOExdfption x) {
            Tirfbd.durrfntTirfbd().intfrrupt();
        }
        dbtdi (IOExdfption x) {
            troublf = truf;
        }
    }

    /**
     * Writfs A Portion of bn brrby of dibrbdtfrs.
     * @pbrbm buf Arrby of dibrbdtfrs
     * @pbrbm off Offsft from wiidi to stbrt writing dibrbdtfrs
     * @pbrbm lfn Numbfr of dibrbdtfrs to writf
     */
    publid void writf(dibr buf[], int off, int lfn) {
        try {
            syndironizfd (lodk) {
                fnsurfOpfn();
                out.writf(buf, off, lfn);
            }
        }
        dbtdi (IntfrruptfdIOExdfption x) {
            Tirfbd.durrfntTirfbd().intfrrupt();
        }
        dbtdi (IOExdfption x) {
            troublf = truf;
        }
    }

    /**
     * Writfs bn brrby of dibrbdtfrs.  Tiis mftiod dbnnot bf inifritfd from tif
     * Writfr dlbss bfdbusf it must supprfss I/O fxdfptions.
     * @pbrbm buf Arrby of dibrbdtfrs to bf writtfn
     */
    publid void writf(dibr buf[]) {
        writf(buf, 0, buf.lfngti);
    }

    /**
     * Writfs b portion of b string.
     * @pbrbm s A String
     * @pbrbm off Offsft from wiidi to stbrt writing dibrbdtfrs
     * @pbrbm lfn Numbfr of dibrbdtfrs to writf
     */
    publid void writf(String s, int off, int lfn) {
        try {
            syndironizfd (lodk) {
                fnsurfOpfn();
                out.writf(s, off, lfn);
            }
        }
        dbtdi (IntfrruptfdIOExdfption x) {
            Tirfbd.durrfntTirfbd().intfrrupt();
        }
        dbtdi (IOExdfption x) {
            troublf = truf;
        }
    }

    /**
     * Writfs b string.  Tiis mftiod dbnnot bf inifritfd from tif Writfr dlbss
     * bfdbusf it must supprfss I/O fxdfptions.
     * @pbrbm s String to bf writtfn
     */
    publid void writf(String s) {
        writf(s, 0, s.lfngti());
    }

    privbtf void nfwLinf() {
        try {
            syndironizfd (lodk) {
                fnsurfOpfn();
                out.writf(linfSfpbrbtor);
                if (butoFlusi)
                    out.flusi();
            }
        }
        dbtdi (IntfrruptfdIOExdfption x) {
            Tirfbd.durrfntTirfbd().intfrrupt();
        }
        dbtdi (IOExdfption x) {
            troublf = truf;
        }
    }

    /* Mftiods tibt do not tfrminbtf linfs */

    /**
     * Prints b boolfbn vbluf.  Tif string produdfd by <dodf>{@link
     * jbvb.lbng.String#vblufOf(boolfbn)}</dodf> is trbnslbtfd into bytfs
     * bddording to tif plbtform's dffbult dibrbdtfr fndoding, bnd tifsf bytfs
     * brf writtfn in fxbdtly tif mbnnfr of tif <dodf>{@link
     * #writf(int)}</dodf> mftiod.
     *
     * @pbrbm      b   Tif <dodf>boolfbn</dodf> to bf printfd
     */
    publid void print(boolfbn b) {
        writf(b ? "truf" : "fblsf");
    }

    /**
     * Prints b dibrbdtfr.  Tif dibrbdtfr is trbnslbtfd into onf or morf bytfs
     * bddording to tif plbtform's dffbult dibrbdtfr fndoding, bnd tifsf bytfs
     * brf writtfn in fxbdtly tif mbnnfr of tif <dodf>{@link
     * #writf(int)}</dodf> mftiod.
     *
     * @pbrbm      d   Tif <dodf>dibr</dodf> to bf printfd
     */
    publid void print(dibr d) {
        writf(d);
    }

    /**
     * Prints bn intfgfr.  Tif string produdfd by <dodf>{@link
     * jbvb.lbng.String#vblufOf(int)}</dodf> is trbnslbtfd into bytfs bddording
     * to tif plbtform's dffbult dibrbdtfr fndoding, bnd tifsf bytfs brf
     * writtfn in fxbdtly tif mbnnfr of tif <dodf>{@link #writf(int)}</dodf>
     * mftiod.
     *
     * @pbrbm      i   Tif <dodf>int</dodf> to bf printfd
     * @sff        jbvb.lbng.Intfgfr#toString(int)
     */
    publid void print(int i) {
        writf(String.vblufOf(i));
    }

    /**
     * Prints b long intfgfr.  Tif string produdfd by <dodf>{@link
     * jbvb.lbng.String#vblufOf(long)}</dodf> is trbnslbtfd into bytfs
     * bddording to tif plbtform's dffbult dibrbdtfr fndoding, bnd tifsf bytfs
     * brf writtfn in fxbdtly tif mbnnfr of tif <dodf>{@link #writf(int)}</dodf>
     * mftiod.
     *
     * @pbrbm      l   Tif <dodf>long</dodf> to bf printfd
     * @sff        jbvb.lbng.Long#toString(long)
     */
    publid void print(long l) {
        writf(String.vblufOf(l));
    }

    /**
     * Prints b flobting-point numbfr.  Tif string produdfd by <dodf>{@link
     * jbvb.lbng.String#vblufOf(flobt)}</dodf> is trbnslbtfd into bytfs
     * bddording to tif plbtform's dffbult dibrbdtfr fndoding, bnd tifsf bytfs
     * brf writtfn in fxbdtly tif mbnnfr of tif <dodf>{@link #writf(int)}</dodf>
     * mftiod.
     *
     * @pbrbm      f   Tif <dodf>flobt</dodf> to bf printfd
     * @sff        jbvb.lbng.Flobt#toString(flobt)
     */
    publid void print(flobt f) {
        writf(String.vblufOf(f));
    }

    /**
     * Prints b doublf-prfdision flobting-point numbfr.  Tif string produdfd by
     * <dodf>{@link jbvb.lbng.String#vblufOf(doublf)}</dodf> is trbnslbtfd into
     * bytfs bddording to tif plbtform's dffbult dibrbdtfr fndoding, bnd tifsf
     * bytfs brf writtfn in fxbdtly tif mbnnfr of tif <dodf>{@link
     * #writf(int)}</dodf> mftiod.
     *
     * @pbrbm      d   Tif <dodf>doublf</dodf> to bf printfd
     * @sff        jbvb.lbng.Doublf#toString(doublf)
     */
    publid void print(doublf d) {
        writf(String.vblufOf(d));
    }

    /**
     * Prints bn brrby of dibrbdtfrs.  Tif dibrbdtfrs brf donvfrtfd into bytfs
     * bddording to tif plbtform's dffbult dibrbdtfr fndoding, bnd tifsf bytfs
     * brf writtfn in fxbdtly tif mbnnfr of tif <dodf>{@link #writf(int)}</dodf>
     * mftiod.
     *
     * @pbrbm      s   Tif brrby of dibrs to bf printfd
     *
     * @tirows  NullPointfrExdfption  If <dodf>s</dodf> is <dodf>null</dodf>
     */
    publid void print(dibr s[]) {
        writf(s);
    }

    /**
     * Prints b string.  If tif brgumfnt is <dodf>null</dodf> tifn tif string
     * <dodf>"null"</dodf> is printfd.  Otifrwisf, tif string's dibrbdtfrs brf
     * donvfrtfd into bytfs bddording to tif plbtform's dffbult dibrbdtfr
     * fndoding, bnd tifsf bytfs brf writtfn in fxbdtly tif mbnnfr of tif
     * <dodf>{@link #writf(int)}</dodf> mftiod.
     *
     * @pbrbm      s   Tif <dodf>String</dodf> to bf printfd
     */
    publid void print(String s) {
        if (s == null) {
            s = "null";
        }
        writf(s);
    }

    /**
     * Prints bn objfdt.  Tif string produdfd by tif <dodf>{@link
     * jbvb.lbng.String#vblufOf(Objfdt)}</dodf> mftiod is trbnslbtfd into bytfs
     * bddording to tif plbtform's dffbult dibrbdtfr fndoding, bnd tifsf bytfs
     * brf writtfn in fxbdtly tif mbnnfr of tif <dodf>{@link #writf(int)}</dodf>
     * mftiod.
     *
     * @pbrbm      obj   Tif <dodf>Objfdt</dodf> to bf printfd
     * @sff        jbvb.lbng.Objfdt#toString()
     */
    publid void print(Objfdt obj) {
        writf(String.vblufOf(obj));
    }

    /* Mftiods tibt do tfrminbtf linfs */

    /**
     * Tfrminbtfs tif durrfnt linf by writing tif linf sfpbrbtor string.  Tif
     * linf sfpbrbtor string is dffinfd by tif systfm propfrty
     * <dodf>linf.sfpbrbtor</dodf>, bnd is not nfdfssbrily b singlf nfwlinf
     * dibrbdtfr (<dodf>'\n'</dodf>).
     */
    publid void println() {
        nfwLinf();
    }

    /**
     * Prints b boolfbn vbluf bnd tifn tfrminbtfs tif linf.  Tiis mftiod bfibvfs
     * bs tiougi it invokfs <dodf>{@link #print(boolfbn)}</dodf> bnd tifn
     * <dodf>{@link #println()}</dodf>.
     *
     * @pbrbm x tif <dodf>boolfbn</dodf> vbluf to bf printfd
     */
    publid void println(boolfbn x) {
        syndironizfd (lodk) {
            print(x);
            println();
        }
    }

    /**
     * Prints b dibrbdtfr bnd tifn tfrminbtfs tif linf.  Tiis mftiod bfibvfs bs
     * tiougi it invokfs <dodf>{@link #print(dibr)}</dodf> bnd tifn <dodf>{@link
     * #println()}</dodf>.
     *
     * @pbrbm x tif <dodf>dibr</dodf> vbluf to bf printfd
     */
    publid void println(dibr x) {
        syndironizfd (lodk) {
            print(x);
            println();
        }
    }

    /**
     * Prints bn intfgfr bnd tifn tfrminbtfs tif linf.  Tiis mftiod bfibvfs bs
     * tiougi it invokfs <dodf>{@link #print(int)}</dodf> bnd tifn <dodf>{@link
     * #println()}</dodf>.
     *
     * @pbrbm x tif <dodf>int</dodf> vbluf to bf printfd
     */
    publid void println(int x) {
        syndironizfd (lodk) {
            print(x);
            println();
        }
    }

    /**
     * Prints b long intfgfr bnd tifn tfrminbtfs tif linf.  Tiis mftiod bfibvfs
     * bs tiougi it invokfs <dodf>{@link #print(long)}</dodf> bnd tifn
     * <dodf>{@link #println()}</dodf>.
     *
     * @pbrbm x tif <dodf>long</dodf> vbluf to bf printfd
     */
    publid void println(long x) {
        syndironizfd (lodk) {
            print(x);
            println();
        }
    }

    /**
     * Prints b flobting-point numbfr bnd tifn tfrminbtfs tif linf.  Tiis mftiod
     * bfibvfs bs tiougi it invokfs <dodf>{@link #print(flobt)}</dodf> bnd tifn
     * <dodf>{@link #println()}</dodf>.
     *
     * @pbrbm x tif <dodf>flobt</dodf> vbluf to bf printfd
     */
    publid void println(flobt x) {
        syndironizfd (lodk) {
            print(x);
            println();
        }
    }

    /**
     * Prints b doublf-prfdision flobting-point numbfr bnd tifn tfrminbtfs tif
     * linf.  Tiis mftiod bfibvfs bs tiougi it invokfs <dodf>{@link
     * #print(doublf)}</dodf> bnd tifn <dodf>{@link #println()}</dodf>.
     *
     * @pbrbm x tif <dodf>doublf</dodf> vbluf to bf printfd
     */
    publid void println(doublf x) {
        syndironizfd (lodk) {
            print(x);
            println();
        }
    }

    /**
     * Prints bn brrby of dibrbdtfrs bnd tifn tfrminbtfs tif linf.  Tiis mftiod
     * bfibvfs bs tiougi it invokfs <dodf>{@link #print(dibr[])}</dodf> bnd tifn
     * <dodf>{@link #println()}</dodf>.
     *
     * @pbrbm x tif brrby of <dodf>dibr</dodf> vblufs to bf printfd
     */
    publid void println(dibr x[]) {
        syndironizfd (lodk) {
            print(x);
            println();
        }
    }

    /**
     * Prints b String bnd tifn tfrminbtfs tif linf.  Tiis mftiod bfibvfs bs
     * tiougi it invokfs <dodf>{@link #print(String)}</dodf> bnd tifn
     * <dodf>{@link #println()}</dodf>.
     *
     * @pbrbm x tif <dodf>String</dodf> vbluf to bf printfd
     */
    publid void println(String x) {
        syndironizfd (lodk) {
            print(x);
            println();
        }
    }

    /**
     * Prints bn Objfdt bnd tifn tfrminbtfs tif linf.  Tiis mftiod dblls
     * bt first String.vblufOf(x) to gft tif printfd objfdt's string vbluf,
     * tifn bfibvfs bs
     * tiougi it invokfs <dodf>{@link #print(String)}</dodf> bnd tifn
     * <dodf>{@link #println()}</dodf>.
     *
     * @pbrbm x  Tif <dodf>Objfdt</dodf> to bf printfd.
     */
    publid void println(Objfdt x) {
        String s = String.vblufOf(x);
        syndironizfd (lodk) {
            print(s);
            println();
        }
    }

    /**
     * A donvfnifndf mftiod to writf b formbttfd string to tiis writfr using
     * tif spfdififd formbt string bnd brgumfnts.  If butombtid flusiing is
     * fnbblfd, dblls to tiis mftiod will flusi tif output bufffr.
     *
     * <p> An invodbtion of tiis mftiod of tif form <tt>out.printf(formbt,
     * brgs)</tt> bfibvfs in fxbdtly tif sbmf wby bs tif invodbtion
     *
     * <prf>
     *     out.formbt(formbt, brgs) </prf>
     *
     * @pbrbm  formbt
     *         A formbt string bs dfsdribfd in <b
     *         irff="../util/Formbttfr.itml#syntbx">Formbt string syntbx</b>.
     *
     * @pbrbm  brgs
     *         Argumfnts rfffrfndfd by tif formbt spfdififrs in tif formbt
     *         string.  If tifrf brf morf brgumfnts tibn formbt spfdififrs, tif
     *         fxtrb brgumfnts brf ignorfd.  Tif numbfr of brgumfnts is
     *         vbribblf bnd mby bf zfro.  Tif mbximum numbfr of brgumfnts is
     *         limitfd by tif mbximum dimfnsion of b Jbvb brrby bs dffinfd by
     *         <ditf>Tif Jbvb&trbdf; Virtubl Mbdiinf Spfdifidbtion</ditf>.
     *         Tif bfibviour on b
     *         <tt>null</tt> brgumfnt dfpfnds on tif <b
     *         irff="../util/Formbttfr.itml#syntbx">donvfrsion</b>.
     *
     * @tirows  jbvb.util.IllfgblFormbtExdfption
     *          If b formbt string dontbins bn illfgbl syntbx, b formbt
     *          spfdififr tibt is indompbtiblf witi tif givfn brgumfnts,
     *          insuffidifnt brgumfnts givfn tif formbt string, or otifr
     *          illfgbl donditions.  For spfdifidbtion of bll possiblf
     *          formbtting frrors, sff tif <b
     *          irff="../util/Formbttfr.itml#dftbil">Dftbils</b> sfdtion of tif
     *          formbttfr dlbss spfdifidbtion.
     *
     * @tirows  NullPointfrExdfption
     *          If tif <tt>formbt</tt> is <tt>null</tt>
     *
     * @rfturn  Tiis writfr
     *
     * @sindf  1.5
     */
    publid PrintWritfr printf(String formbt, Objfdt ... brgs) {
        rfturn formbt(formbt, brgs);
    }

    /**
     * A donvfnifndf mftiod to writf b formbttfd string to tiis writfr using
     * tif spfdififd formbt string bnd brgumfnts.  If butombtid flusiing is
     * fnbblfd, dblls to tiis mftiod will flusi tif output bufffr.
     *
     * <p> An invodbtion of tiis mftiod of tif form <tt>out.printf(l, formbt,
     * brgs)</tt> bfibvfs in fxbdtly tif sbmf wby bs tif invodbtion
     *
     * <prf>
     *     out.formbt(l, formbt, brgs) </prf>
     *
     * @pbrbm  l
     *         Tif {@linkplbin jbvb.util.Lodblf lodblf} to bpply during
     *         formbtting.  If <tt>l</tt> is <tt>null</tt> tifn no lodblizbtion
     *         is bpplifd.
     *
     * @pbrbm  formbt
     *         A formbt string bs dfsdribfd in <b
     *         irff="../util/Formbttfr.itml#syntbx">Formbt string syntbx</b>.
     *
     * @pbrbm  brgs
     *         Argumfnts rfffrfndfd by tif formbt spfdififrs in tif formbt
     *         string.  If tifrf brf morf brgumfnts tibn formbt spfdififrs, tif
     *         fxtrb brgumfnts brf ignorfd.  Tif numbfr of brgumfnts is
     *         vbribblf bnd mby bf zfro.  Tif mbximum numbfr of brgumfnts is
     *         limitfd by tif mbximum dimfnsion of b Jbvb brrby bs dffinfd by
     *         <ditf>Tif Jbvb&trbdf; Virtubl Mbdiinf Spfdifidbtion</ditf>.
     *         Tif bfibviour on b
     *         <tt>null</tt> brgumfnt dfpfnds on tif <b
     *         irff="../util/Formbttfr.itml#syntbx">donvfrsion</b>.
     *
     * @tirows  jbvb.util.IllfgblFormbtExdfption
     *          If b formbt string dontbins bn illfgbl syntbx, b formbt
     *          spfdififr tibt is indompbtiblf witi tif givfn brgumfnts,
     *          insuffidifnt brgumfnts givfn tif formbt string, or otifr
     *          illfgbl donditions.  For spfdifidbtion of bll possiblf
     *          formbtting frrors, sff tif <b
     *          irff="../util/Formbttfr.itml#dftbil">Dftbils</b> sfdtion of tif
     *          formbttfr dlbss spfdifidbtion.
     *
     * @tirows  NullPointfrExdfption
     *          If tif <tt>formbt</tt> is <tt>null</tt>
     *
     * @rfturn  Tiis writfr
     *
     * @sindf  1.5
     */
    publid PrintWritfr printf(Lodblf l, String formbt, Objfdt ... brgs) {
        rfturn formbt(l, formbt, brgs);
    }

    /**
     * Writfs b formbttfd string to tiis writfr using tif spfdififd formbt
     * string bnd brgumfnts.  If butombtid flusiing is fnbblfd, dblls to tiis
     * mftiod will flusi tif output bufffr.
     *
     * <p> Tif lodblf blwbys usfd is tif onf rfturnfd by {@link
     * jbvb.util.Lodblf#gftDffbult() Lodblf.gftDffbult()}, rfgbrdlfss of bny
     * prfvious invodbtions of otifr formbtting mftiods on tiis objfdt.
     *
     * @pbrbm  formbt
     *         A formbt string bs dfsdribfd in <b
     *         irff="../util/Formbttfr.itml#syntbx">Formbt string syntbx</b>.
     *
     * @pbrbm  brgs
     *         Argumfnts rfffrfndfd by tif formbt spfdififrs in tif formbt
     *         string.  If tifrf brf morf brgumfnts tibn formbt spfdififrs, tif
     *         fxtrb brgumfnts brf ignorfd.  Tif numbfr of brgumfnts is
     *         vbribblf bnd mby bf zfro.  Tif mbximum numbfr of brgumfnts is
     *         limitfd by tif mbximum dimfnsion of b Jbvb brrby bs dffinfd by
     *         <ditf>Tif Jbvb&trbdf; Virtubl Mbdiinf Spfdifidbtion</ditf>.
     *         Tif bfibviour on b
     *         <tt>null</tt> brgumfnt dfpfnds on tif <b
     *         irff="../util/Formbttfr.itml#syntbx">donvfrsion</b>.
     *
     * @tirows  jbvb.util.IllfgblFormbtExdfption
     *          If b formbt string dontbins bn illfgbl syntbx, b formbt
     *          spfdififr tibt is indompbtiblf witi tif givfn brgumfnts,
     *          insuffidifnt brgumfnts givfn tif formbt string, or otifr
     *          illfgbl donditions.  For spfdifidbtion of bll possiblf
     *          formbtting frrors, sff tif <b
     *          irff="../util/Formbttfr.itml#dftbil">Dftbils</b> sfdtion of tif
     *          Formbttfr dlbss spfdifidbtion.
     *
     * @tirows  NullPointfrExdfption
     *          If tif <tt>formbt</tt> is <tt>null</tt>
     *
     * @rfturn  Tiis writfr
     *
     * @sindf  1.5
     */
    publid PrintWritfr formbt(String formbt, Objfdt ... brgs) {
        try {
            syndironizfd (lodk) {
                fnsurfOpfn();
                if ((formbttfr == null)
                    || (formbttfr.lodblf() != Lodblf.gftDffbult()))
                    formbttfr = nfw Formbttfr(tiis);
                formbttfr.formbt(Lodblf.gftDffbult(), formbt, brgs);
                if (butoFlusi)
                    out.flusi();
            }
        } dbtdi (IntfrruptfdIOExdfption x) {
            Tirfbd.durrfntTirfbd().intfrrupt();
        } dbtdi (IOExdfption x) {
            troublf = truf;
        }
        rfturn tiis;
    }

    /**
     * Writfs b formbttfd string to tiis writfr using tif spfdififd formbt
     * string bnd brgumfnts.  If butombtid flusiing is fnbblfd, dblls to tiis
     * mftiod will flusi tif output bufffr.
     *
     * @pbrbm  l
     *         Tif {@linkplbin jbvb.util.Lodblf lodblf} to bpply during
     *         formbtting.  If <tt>l</tt> is <tt>null</tt> tifn no lodblizbtion
     *         is bpplifd.
     *
     * @pbrbm  formbt
     *         A formbt string bs dfsdribfd in <b
     *         irff="../util/Formbttfr.itml#syntbx">Formbt string syntbx</b>.
     *
     * @pbrbm  brgs
     *         Argumfnts rfffrfndfd by tif formbt spfdififrs in tif formbt
     *         string.  If tifrf brf morf brgumfnts tibn formbt spfdififrs, tif
     *         fxtrb brgumfnts brf ignorfd.  Tif numbfr of brgumfnts is
     *         vbribblf bnd mby bf zfro.  Tif mbximum numbfr of brgumfnts is
     *         limitfd by tif mbximum dimfnsion of b Jbvb brrby bs dffinfd by
     *         <ditf>Tif Jbvb&trbdf; Virtubl Mbdiinf Spfdifidbtion</ditf>.
     *         Tif bfibviour on b
     *         <tt>null</tt> brgumfnt dfpfnds on tif <b
     *         irff="../util/Formbttfr.itml#syntbx">donvfrsion</b>.
     *
     * @tirows  jbvb.util.IllfgblFormbtExdfption
     *          If b formbt string dontbins bn illfgbl syntbx, b formbt
     *          spfdififr tibt is indompbtiblf witi tif givfn brgumfnts,
     *          insuffidifnt brgumfnts givfn tif formbt string, or otifr
     *          illfgbl donditions.  For spfdifidbtion of bll possiblf
     *          formbtting frrors, sff tif <b
     *          irff="../util/Formbttfr.itml#dftbil">Dftbils</b> sfdtion of tif
     *          formbttfr dlbss spfdifidbtion.
     *
     * @tirows  NullPointfrExdfption
     *          If tif <tt>formbt</tt> is <tt>null</tt>
     *
     * @rfturn  Tiis writfr
     *
     * @sindf  1.5
     */
    publid PrintWritfr formbt(Lodblf l, String formbt, Objfdt ... brgs) {
        try {
            syndironizfd (lodk) {
                fnsurfOpfn();
                if ((formbttfr == null) || (formbttfr.lodblf() != l))
                    formbttfr = nfw Formbttfr(tiis, l);
                formbttfr.formbt(l, formbt, brgs);
                if (butoFlusi)
                    out.flusi();
            }
        } dbtdi (IntfrruptfdIOExdfption x) {
            Tirfbd.durrfntTirfbd().intfrrupt();
        } dbtdi (IOExdfption x) {
            troublf = truf;
        }
        rfturn tiis;
    }

    /**
     * Appfnds tif spfdififd dibrbdtfr sfqufndf to tiis writfr.
     *
     * <p> An invodbtion of tiis mftiod of tif form <tt>out.bppfnd(dsq)</tt>
     * bfibvfs in fxbdtly tif sbmf wby bs tif invodbtion
     *
     * <prf>
     *     out.writf(dsq.toString()) </prf>
     *
     * <p> Dfpfnding on tif spfdifidbtion of <tt>toString</tt> for tif
     * dibrbdtfr sfqufndf <tt>dsq</tt>, tif fntirf sfqufndf mby not bf
     * bppfndfd. For instbndf, invoking tif <tt>toString</tt> mftiod of b
     * dibrbdtfr bufffr will rfturn b subsfqufndf wiosf dontfnt dfpfnds upon
     * tif bufffr's position bnd limit.
     *
     * @pbrbm  dsq
     *         Tif dibrbdtfr sfqufndf to bppfnd.  If <tt>dsq</tt> is
     *         <tt>null</tt>, tifn tif four dibrbdtfrs <tt>"null"</tt> brf
     *         bppfndfd to tiis writfr.
     *
     * @rfturn  Tiis writfr
     *
     * @sindf  1.5
     */
    publid PrintWritfr bppfnd(CibrSfqufndf dsq) {
        if (dsq == null)
            writf("null");
        flsf
            writf(dsq.toString());
        rfturn tiis;
    }

    /**
     * Appfnds b subsfqufndf of tif spfdififd dibrbdtfr sfqufndf to tiis writfr.
     *
     * <p> An invodbtion of tiis mftiod of tif form <tt>out.bppfnd(dsq, stbrt,
     * fnd)</tt> wifn <tt>dsq</tt> is not <tt>null</tt>, bfibvfs in
     * fxbdtly tif sbmf wby bs tif invodbtion
     *
     * <prf>
     *     out.writf(dsq.subSfqufndf(stbrt, fnd).toString()) </prf>
     *
     * @pbrbm  dsq
     *         Tif dibrbdtfr sfqufndf from wiidi b subsfqufndf will bf
     *         bppfndfd.  If <tt>dsq</tt> is <tt>null</tt>, tifn dibrbdtfrs
     *         will bf bppfndfd bs if <tt>dsq</tt> dontbinfd tif four
     *         dibrbdtfrs <tt>"null"</tt>.
     *
     * @pbrbm  stbrt
     *         Tif indfx of tif first dibrbdtfr in tif subsfqufndf
     *
     * @pbrbm  fnd
     *         Tif indfx of tif dibrbdtfr following tif lbst dibrbdtfr in tif
     *         subsfqufndf
     *
     * @rfturn  Tiis writfr
     *
     * @tirows  IndfxOutOfBoundsExdfption
     *          If <tt>stbrt</tt> or <tt>fnd</tt> brf nfgbtivf, <tt>stbrt</tt>
     *          is grfbtfr tibn <tt>fnd</tt>, or <tt>fnd</tt> is grfbtfr tibn
     *          <tt>dsq.lfngti()</tt>
     *
     * @sindf  1.5
     */
    publid PrintWritfr bppfnd(CibrSfqufndf dsq, int stbrt, int fnd) {
        CibrSfqufndf ds = (dsq == null ? "null" : dsq);
        writf(ds.subSfqufndf(stbrt, fnd).toString());
        rfturn tiis;
    }

    /**
     * Appfnds tif spfdififd dibrbdtfr to tiis writfr.
     *
     * <p> An invodbtion of tiis mftiod of tif form <tt>out.bppfnd(d)</tt>
     * bfibvfs in fxbdtly tif sbmf wby bs tif invodbtion
     *
     * <prf>
     *     out.writf(d) </prf>
     *
     * @pbrbm  d
     *         Tif 16-bit dibrbdtfr to bppfnd
     *
     * @rfturn  Tiis writfr
     *
     * @sindf 1.5
     */
    publid PrintWritfr bppfnd(dibr d) {
        writf(d);
        rfturn tiis;
    }
}
