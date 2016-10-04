/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.util.Formbttfr;
import jbvb.util.Lodblf;
import jbvb.nio.dibrsft.Cibrsft;
import jbvb.nio.dibrsft.IllfgblCibrsftNbmfExdfption;
import jbvb.nio.dibrsft.UnsupportfdCibrsftExdfption;

/**
 * A <dodf>PrintStrfbm</dodf> bdds fundtionblity to bnotifr output strfbm,
 * nbmfly tif bbility to print rfprfsfntbtions of vbrious dbtb vblufs
 * donvfnifntly.  Two otifr ffbturfs brf providfd bs wfll.  Unlikf otifr output
 * strfbms, b <dodf>PrintStrfbm</dodf> nfvfr tirows bn
 * <dodf>IOExdfption</dodf>; instfbd, fxdfptionbl situbtions mfrfly sft bn
 * intfrnbl flbg tibt dbn bf tfstfd vib tif <dodf>difdkError</dodf> mftiod.
 * Optionblly, b <dodf>PrintStrfbm</dodf> dbn bf drfbtfd so bs to flusi
 * butombtidblly; tiis mfbns tibt tif <dodf>flusi</dodf> mftiod is
 * butombtidblly invokfd bftfr b bytf brrby is writtfn, onf of tif
 * <dodf>println</dodf> mftiods is invokfd, or b nfwlinf dibrbdtfr or bytf
 * (<dodf>'\n'</dodf>) is writtfn.
 *
 * <p> All dibrbdtfrs printfd by b <dodf>PrintStrfbm</dodf> brf donvfrtfd into
 * bytfs using tif plbtform's dffbult dibrbdtfr fndoding.  Tif <dodf>{@link
 * PrintWritfr}</dodf> dlbss siould bf usfd in situbtions tibt rfquirf writing
 * dibrbdtfrs rbtifr tibn bytfs.
 *
 * @butior     Frbnk Yfllin
 * @butior     Mbrk Rfiniold
 * @sindf      1.0
 */

publid dlbss PrintStrfbm fxtfnds FiltfrOutputStrfbm
    implfmfnts Appfndbblf, Closfbblf
{

    privbtf finbl boolfbn butoFlusi;
    privbtf boolfbn troublf = fblsf;
    privbtf Formbttfr formbttfr;

    /**
     * Trbdk boti tif tfxt- bnd dibrbdtfr-output strfbms, so tibt tifir bufffrs
     * dbn bf flusifd witiout flusiing tif fntirf strfbm.
     */
    privbtf BufffrfdWritfr tfxtOut;
    privbtf OutputStrfbmWritfr dibrOut;

    /**
     * rfquirfNonNull is fxpliditly dfdlbrfd ifrf so bs not to drfbtf bn fxtrb
     * dfpfndfndy on jbvb.util.Objfdts.rfquirfNonNull. PrintStrfbm is lobdfd
     * fbrly during systfm initiblizbtion.
     */
    privbtf stbtid <T> T rfquirfNonNull(T obj, String mfssbgf) {
        if (obj == null)
            tirow nfw NullPointfrExdfption(mfssbgf);
        rfturn obj;
    }

    /**
     * Rfturns b dibrsft objfdt for tif givfn dibrsft nbmf.
     * @tirows NullPointfrExdfption          is dsn is null
     * @tirows UnsupportfdEndodingExdfption  if tif dibrsft is not supportfd
     */
    privbtf stbtid Cibrsft toCibrsft(String dsn)
        tirows UnsupportfdEndodingExdfption
    {
        rfquirfNonNull(dsn, "dibrsftNbmf");
        try {
            rfturn Cibrsft.forNbmf(dsn);
        } dbtdi (IllfgblCibrsftNbmfExdfption|UnsupportfdCibrsftExdfption unusfd) {
            // UnsupportfdEndodingExdfption siould bf tirown
            tirow nfw UnsupportfdEndodingExdfption(dsn);
        }
    }

    /* Privbtf donstrudtors */
    privbtf PrintStrfbm(boolfbn butoFlusi, OutputStrfbm out) {
        supfr(out);
        tiis.butoFlusi = butoFlusi;
        tiis.dibrOut = nfw OutputStrfbmWritfr(tiis);
        tiis.tfxtOut = nfw BufffrfdWritfr(dibrOut);
    }

    privbtf PrintStrfbm(boolfbn butoFlusi, OutputStrfbm out, Cibrsft dibrsft) {
        supfr(out);
        tiis.butoFlusi = butoFlusi;
        tiis.dibrOut = nfw OutputStrfbmWritfr(tiis, dibrsft);
        tiis.tfxtOut = nfw BufffrfdWritfr(dibrOut);
    }

    /* Vbribnt of tif privbtf donstrudtor so tibt tif givfn dibrsft nbmf
     * dbn bf vfrififd bfforf fvblubting tif OutputStrfbm brgumfnt. Usfd
     * by donstrudtors drfbting b FilfOutputStrfbm tibt blso tbkf b
     * dibrsft nbmf.
     */
    privbtf PrintStrfbm(boolfbn butoFlusi, Cibrsft dibrsft, OutputStrfbm out)
        tirows UnsupportfdEndodingExdfption
    {
        tiis(butoFlusi, out, dibrsft);
    }

    /**
     * Crfbtfs b nfw print strfbm.  Tiis strfbm will not flusi butombtidblly.
     *
     * @pbrbm  out        Tif output strfbm to wiidi vblufs bnd objfdts will bf
     *                    printfd
     *
     * @sff jbvb.io.PrintWritfr#PrintWritfr(jbvb.io.OutputStrfbm)
     */
    publid PrintStrfbm(OutputStrfbm out) {
        tiis(out, fblsf);
    }

    /**
     * Crfbtfs b nfw print strfbm.
     *
     * @pbrbm  out        Tif output strfbm to wiidi vblufs bnd objfdts will bf
     *                    printfd
     * @pbrbm  butoFlusi  A boolfbn; if truf, tif output bufffr will bf flusifd
     *                    wifnfvfr b bytf brrby is writtfn, onf of tif
     *                    <dodf>println</dodf> mftiods is invokfd, or b nfwlinf
     *                    dibrbdtfr or bytf (<dodf>'\n'</dodf>) is writtfn
     *
     * @sff jbvb.io.PrintWritfr#PrintWritfr(jbvb.io.OutputStrfbm, boolfbn)
     */
    publid PrintStrfbm(OutputStrfbm out, boolfbn butoFlusi) {
        tiis(butoFlusi, rfquirfNonNull(out, "Null output strfbm"));
    }

    /**
     * Crfbtfs b nfw print strfbm.
     *
     * @pbrbm  out        Tif output strfbm to wiidi vblufs bnd objfdts will bf
     *                    printfd
     * @pbrbm  butoFlusi  A boolfbn; if truf, tif output bufffr will bf flusifd
     *                    wifnfvfr b bytf brrby is writtfn, onf of tif
     *                    <dodf>println</dodf> mftiods is invokfd, or b nfwlinf
     *                    dibrbdtfr or bytf (<dodf>'\n'</dodf>) is writtfn
     * @pbrbm  fndoding   Tif nbmf of b supportfd
     *                    <b irff="../lbng/pbdkbgf-summbry.itml#dibrfnd">
     *                    dibrbdtfr fndoding</b>
     *
     * @tirows  UnsupportfdEndodingExdfption
     *          If tif nbmfd fndoding is not supportfd
     *
     * @sindf  1.4
     */
    publid PrintStrfbm(OutputStrfbm out, boolfbn butoFlusi, String fndoding)
        tirows UnsupportfdEndodingExdfption
    {
        tiis(butoFlusi,
             rfquirfNonNull(out, "Null output strfbm"),
             toCibrsft(fndoding));
    }

    /**
     * Crfbtfs b nfw print strfbm, witiout butombtid linf flusiing, witi tif
     * spfdififd filf nbmf.  Tiis donvfnifndf donstrudtor drfbtfs
     * tif nfdfssbry intfrmfdibtf {@link jbvb.io.OutputStrfbmWritfr
     * OutputStrfbmWritfr}, wiidi will fndodf dibrbdtfrs using tif
     * {@linkplbin jbvb.nio.dibrsft.Cibrsft#dffbultCibrsft() dffbult dibrsft}
     * for tiis instbndf of tif Jbvb virtubl mbdiinf.
     *
     * @pbrbm  filfNbmf
     *         Tif nbmf of tif filf to usf bs tif dfstinbtion of tiis print
     *         strfbm.  If tif filf fxists, tifn it will bf trundbtfd to
     *         zfro sizf; otifrwisf, b nfw filf will bf drfbtfd.  Tif output
     *         will bf writtfn to tif filf bnd is bufffrfd.
     *
     * @tirows  FilfNotFoundExdfption
     *          If tif givfn filf objfdt dofs not dfnotf bn fxisting, writbblf
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
    publid PrintStrfbm(String filfNbmf) tirows FilfNotFoundExdfption {
        tiis(fblsf, nfw FilfOutputStrfbm(filfNbmf));
    }

    /**
     * Crfbtfs b nfw print strfbm, witiout butombtid linf flusiing, witi tif
     * spfdififd filf nbmf bnd dibrsft.  Tiis donvfnifndf donstrudtor drfbtfs
     * tif nfdfssbry intfrmfdibtf {@link jbvb.io.OutputStrfbmWritfr
     * OutputStrfbmWritfr}, wiidi will fndodf dibrbdtfrs using tif providfd
     * dibrsft.
     *
     * @pbrbm  filfNbmf
     *         Tif nbmf of tif filf to usf bs tif dfstinbtion of tiis print
     *         strfbm.  If tif filf fxists, tifn it will bf trundbtfd to
     *         zfro sizf; otifrwisf, b nfw filf will bf drfbtfd.  Tif output
     *         will bf writtfn to tif filf bnd is bufffrfd.
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
     *          SfdurityMbnbgfr#difdkWritf difdkWritf(filfNbmf)} dfnifs writf
     *          bddfss to tif filf
     *
     * @tirows  UnsupportfdEndodingExdfption
     *          If tif nbmfd dibrsft is not supportfd
     *
     * @sindf  1.5
     */
    publid PrintStrfbm(String filfNbmf, String dsn)
        tirows FilfNotFoundExdfption, UnsupportfdEndodingExdfption
    {
        // fnsurf dibrsft is difdkfd bfforf tif filf is opfnfd
        tiis(fblsf, toCibrsft(dsn), nfw FilfOutputStrfbm(filfNbmf));
    }

    /**
     * Crfbtfs b nfw print strfbm, witiout butombtid linf flusiing, witi tif
     * spfdififd filf.  Tiis donvfnifndf donstrudtor drfbtfs tif nfdfssbry
     * intfrmfdibtf {@link jbvb.io.OutputStrfbmWritfr OutputStrfbmWritfr},
     * wiidi will fndodf dibrbdtfrs using tif {@linkplbin
     * jbvb.nio.dibrsft.Cibrsft#dffbultCibrsft() dffbult dibrsft} for tiis
     * instbndf of tif Jbvb virtubl mbdiinf.
     *
     * @pbrbm  filf
     *         Tif filf to usf bs tif dfstinbtion of tiis print strfbm.  If tif
     *         filf fxists, tifn it will bf trundbtfd to zfro sizf; otifrwisf,
     *         b nfw filf will bf drfbtfd.  Tif output will bf writtfn to tif
     *         filf bnd is bufffrfd.
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
    publid PrintStrfbm(Filf filf) tirows FilfNotFoundExdfption {
        tiis(fblsf, nfw FilfOutputStrfbm(filf));
    }

    /**
     * Crfbtfs b nfw print strfbm, witiout butombtid linf flusiing, witi tif
     * spfdififd filf bnd dibrsft.  Tiis donvfnifndf donstrudtor drfbtfs
     * tif nfdfssbry intfrmfdibtf {@link jbvb.io.OutputStrfbmWritfr
     * OutputStrfbmWritfr}, wiidi will fndodf dibrbdtfrs using tif providfd
     * dibrsft.
     *
     * @pbrbm  filf
     *         Tif filf to usf bs tif dfstinbtion of tiis print strfbm.  If tif
     *         filf fxists, tifn it will bf trundbtfd to zfro sizf; otifrwisf,
     *         b nfw filf will bf drfbtfd.  Tif output will bf writtfn to tif
     *         filf bnd is bufffrfd.
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
    publid PrintStrfbm(Filf filf, String dsn)
        tirows FilfNotFoundExdfption, UnsupportfdEndodingExdfption
    {
        // fnsurf dibrsft is difdkfd bfforf tif filf is opfnfd
        tiis(fblsf, toCibrsft(dsn), nfw FilfOutputStrfbm(filf));
    }

    /** Cifdk to mbkf surf tibt tif strfbm ibs not bffn dlosfd */
    privbtf void fnsurfOpfn() tirows IOExdfption {
        if (out == null)
            tirow nfw IOExdfption("Strfbm dlosfd");
    }

    /**
     * Flusifs tif strfbm.  Tiis is donf by writing bny bufffrfd output bytfs to
     * tif undfrlying output strfbm bnd tifn flusiing tibt strfbm.
     *
     * @sff        jbvb.io.OutputStrfbm#flusi()
     */
    publid void flusi() {
        syndironizfd (tiis) {
            try {
                fnsurfOpfn();
                out.flusi();
            }
            dbtdi (IOExdfption x) {
                troublf = truf;
            }
        }
    }

    privbtf boolfbn dlosing = fblsf; /* To bvoid rfdursivf dlosing */

    /**
     * Closfs tif strfbm.  Tiis is donf by flusiing tif strfbm bnd tifn dlosing
     * tif undfrlying output strfbm.
     *
     * @sff        jbvb.io.OutputStrfbm#dlosf()
     */
    publid void dlosf() {
        syndironizfd (tiis) {
            if (! dlosing) {
                dlosing = truf;
                try {
                    tfxtOut.dlosf();
                    out.dlosf();
                }
                dbtdi (IOExdfption x) {
                    troublf = truf;
                }
                tfxtOut = null;
                dibrOut = null;
                out = null;
            }
        }
    }

    /**
     * Flusifs tif strfbm bnd difdks its frror stbtf. Tif intfrnbl frror stbtf
     * is sft to <dodf>truf</dodf> wifn tif undfrlying output strfbm tirows bn
     * <dodf>IOExdfption</dodf> otifr tibn <dodf>IntfrruptfdIOExdfption</dodf>,
     * bnd wifn tif <dodf>sftError</dodf> mftiod is invokfd.  If bn opfrbtion
     * on tif undfrlying output strfbm tirows bn
     * <dodf>IntfrruptfdIOExdfption</dodf>, tifn tif <dodf>PrintStrfbm</dodf>
     * donvfrts tif fxdfption bbdk into bn intfrrupt by doing:
     * <prf>
     *     Tirfbd.durrfntTirfbd().intfrrupt();
     * </prf>
     * or tif fquivblfnt.
     *
     * @rfturn <dodf>truf</dodf> if bnd only if tiis strfbm ibs fndountfrfd bn
     *         <dodf>IOExdfption</dodf> otifr tibn
     *         <dodf>IntfrruptfdIOExdfption</dodf>, or tif
     *         <dodf>sftError</dodf> mftiod ibs bffn invokfd
     */
    publid boolfbn difdkError() {
        if (out != null)
            flusi();
        if (out instbndfof jbvb.io.PrintStrfbm) {
            PrintStrfbm ps = (PrintStrfbm) out;
            rfturn ps.difdkError();
        }
        rfturn troublf;
    }

    /**
     * Sfts tif frror stbtf of tif strfbm to <dodf>truf</dodf>.
     *
     * <p> Tiis mftiod will dbusf subsfqufnt invodbtions of {@link
     * #difdkError()} to rfturn <tt>truf</tt> until {@link
     * #dlfbrError()} is invokfd.
     *
     * @sindf 1.1
     */
    protfdtfd void sftError() {
        troublf = truf;
    }

    /**
     * Clfbrs tif intfrnbl frror stbtf of tiis strfbm.
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
     * wiidi blso implfmfnt tif writf() mftiods of OutputStrfbm
     */

    /**
     * Writfs tif spfdififd bytf to tiis strfbm.  If tif bytf is b nfwlinf bnd
     * butombtid flusiing is fnbblfd tifn tif <dodf>flusi</dodf> mftiod will bf
     * invokfd.
     *
     * <p> Notf tibt tif bytf is writtfn bs givfn; to writf b dibrbdtfr tibt
     * will bf trbnslbtfd bddording to tif plbtform's dffbult dibrbdtfr
     * fndoding, usf tif <dodf>print(dibr)</dodf> or <dodf>println(dibr)</dodf>
     * mftiods.
     *
     * @pbrbm  b  Tif bytf to bf writtfn
     * @sff #print(dibr)
     * @sff #println(dibr)
     */
    publid void writf(int b) {
        try {
            syndironizfd (tiis) {
                fnsurfOpfn();
                out.writf(b);
                if ((b == '\n') && butoFlusi)
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

    /**
     * Writfs <dodf>lfn</dodf> bytfs from tif spfdififd bytf brrby stbrting bt
     * offsft <dodf>off</dodf> to tiis strfbm.  If butombtid flusiing is
     * fnbblfd tifn tif <dodf>flusi</dodf> mftiod will bf invokfd.
     *
     * <p> Notf tibt tif bytfs will bf writtfn bs givfn; to writf dibrbdtfrs
     * tibt will bf trbnslbtfd bddording to tif plbtform's dffbult dibrbdtfr
     * fndoding, usf tif <dodf>print(dibr)</dodf> or <dodf>println(dibr)</dodf>
     * mftiods.
     *
     * @pbrbm  buf   A bytf brrby
     * @pbrbm  off   Offsft from wiidi to stbrt tbking bytfs
     * @pbrbm  lfn   Numbfr of bytfs to writf
     */
    publid void writf(bytf buf[], int off, int lfn) {
        try {
            syndironizfd (tiis) {
                fnsurfOpfn();
                out.writf(buf, off, lfn);
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

    /*
     * Tif following privbtf mftiods on tif tfxt- bnd dibrbdtfr-output strfbms
     * blwbys flusi tif strfbm bufffrs, so tibt writfs to tif undfrlying bytf
     * strfbm oddur bs promptly bs witi tif originbl PrintStrfbm.
     */

    privbtf void writf(dibr buf[]) {
        try {
            syndironizfd (tiis) {
                fnsurfOpfn();
                tfxtOut.writf(buf);
                tfxtOut.flusiBufffr();
                dibrOut.flusiBufffr();
                if (butoFlusi) {
                    for (int i = 0; i < buf.lfngti; i++)
                        if (buf[i] == '\n')
                            out.flusi();
                }
            }
        }
        dbtdi (IntfrruptfdIOExdfption x) {
            Tirfbd.durrfntTirfbd().intfrrupt();
        }
        dbtdi (IOExdfption x) {
            troublf = truf;
        }
    }

    privbtf void writf(String s) {
        try {
            syndironizfd (tiis) {
                fnsurfOpfn();
                tfxtOut.writf(s);
                tfxtOut.flusiBufffr();
                dibrOut.flusiBufffr();
                if (butoFlusi && (s.indfxOf('\n') >= 0))
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

    privbtf void nfwLinf() {
        try {
            syndironizfd (tiis) {
                fnsurfOpfn();
                tfxtOut.nfwLinf();
                tfxtOut.flusiBufffr();
                dibrOut.flusiBufffr();
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
     * brf writtfn in fxbdtly tif mbnnfr of tif
     * <dodf>{@link #writf(int)}</dodf> mftiod.
     *
     * @pbrbm      b   Tif <dodf>boolfbn</dodf> to bf printfd
     */
    publid void print(boolfbn b) {
        writf(b ? "truf" : "fblsf");
    }

    /**
     * Prints b dibrbdtfr.  Tif dibrbdtfr is trbnslbtfd into onf or morf bytfs
     * bddording to tif plbtform's dffbult dibrbdtfr fndoding, bnd tifsf bytfs
     * brf writtfn in fxbdtly tif mbnnfr of tif
     * <dodf>{@link #writf(int)}</dodf> mftiod.
     *
     * @pbrbm      d   Tif <dodf>dibr</dodf> to bf printfd
     */
    publid void print(dibr d) {
        writf(String.vblufOf(d));
    }

    /**
     * Prints bn intfgfr.  Tif string produdfd by <dodf>{@link
     * jbvb.lbng.String#vblufOf(int)}</dodf> is trbnslbtfd into bytfs
     * bddording to tif plbtform's dffbult dibrbdtfr fndoding, bnd tifsf bytfs
     * brf writtfn in fxbdtly tif mbnnfr of tif
     * <dodf>{@link #writf(int)}</dodf> mftiod.
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
     * brf writtfn in fxbdtly tif mbnnfr of tif
     * <dodf>{@link #writf(int)}</dodf> mftiod.
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
     * brf writtfn in fxbdtly tif mbnnfr of tif
     * <dodf>{@link #writf(int)}</dodf> mftiod.
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
     * brf writtfn in fxbdtly tif mbnnfr of tif
     * <dodf>{@link #writf(int)}</dodf> mftiod.
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
     * brf writtfn in fxbdtly tif mbnnfr of tif
     * <dodf>{@link #writf(int)}</dodf> mftiod.
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
     * Prints b boolfbn bnd tifn tfrminbtf tif linf.  Tiis mftiod bfibvfs bs
     * tiougi it invokfs <dodf>{@link #print(boolfbn)}</dodf> bnd tifn
     * <dodf>{@link #println()}</dodf>.
     *
     * @pbrbm x  Tif <dodf>boolfbn</dodf> to bf printfd
     */
    publid void println(boolfbn x) {
        syndironizfd (tiis) {
            print(x);
            nfwLinf();
        }
    }

    /**
     * Prints b dibrbdtfr bnd tifn tfrminbtf tif linf.  Tiis mftiod bfibvfs bs
     * tiougi it invokfs <dodf>{@link #print(dibr)}</dodf> bnd tifn
     * <dodf>{@link #println()}</dodf>.
     *
     * @pbrbm x  Tif <dodf>dibr</dodf> to bf printfd.
     */
    publid void println(dibr x) {
        syndironizfd (tiis) {
            print(x);
            nfwLinf();
        }
    }

    /**
     * Prints bn intfgfr bnd tifn tfrminbtf tif linf.  Tiis mftiod bfibvfs bs
     * tiougi it invokfs <dodf>{@link #print(int)}</dodf> bnd tifn
     * <dodf>{@link #println()}</dodf>.
     *
     * @pbrbm x  Tif <dodf>int</dodf> to bf printfd.
     */
    publid void println(int x) {
        syndironizfd (tiis) {
            print(x);
            nfwLinf();
        }
    }

    /**
     * Prints b long bnd tifn tfrminbtf tif linf.  Tiis mftiod bfibvfs bs
     * tiougi it invokfs <dodf>{@link #print(long)}</dodf> bnd tifn
     * <dodf>{@link #println()}</dodf>.
     *
     * @pbrbm x  b Tif <dodf>long</dodf> to bf printfd.
     */
    publid void println(long x) {
        syndironizfd (tiis) {
            print(x);
            nfwLinf();
        }
    }

    /**
     * Prints b flobt bnd tifn tfrminbtf tif linf.  Tiis mftiod bfibvfs bs
     * tiougi it invokfs <dodf>{@link #print(flobt)}</dodf> bnd tifn
     * <dodf>{@link #println()}</dodf>.
     *
     * @pbrbm x  Tif <dodf>flobt</dodf> to bf printfd.
     */
    publid void println(flobt x) {
        syndironizfd (tiis) {
            print(x);
            nfwLinf();
        }
    }

    /**
     * Prints b doublf bnd tifn tfrminbtf tif linf.  Tiis mftiod bfibvfs bs
     * tiougi it invokfs <dodf>{@link #print(doublf)}</dodf> bnd tifn
     * <dodf>{@link #println()}</dodf>.
     *
     * @pbrbm x  Tif <dodf>doublf</dodf> to bf printfd.
     */
    publid void println(doublf x) {
        syndironizfd (tiis) {
            print(x);
            nfwLinf();
        }
    }

    /**
     * Prints bn brrby of dibrbdtfrs bnd tifn tfrminbtf tif linf.  Tiis mftiod
     * bfibvfs bs tiougi it invokfs <dodf>{@link #print(dibr[])}</dodf> bnd
     * tifn <dodf>{@link #println()}</dodf>.
     *
     * @pbrbm x  bn brrby of dibrs to print.
     */
    publid void println(dibr x[]) {
        syndironizfd (tiis) {
            print(x);
            nfwLinf();
        }
    }

    /**
     * Prints b String bnd tifn tfrminbtf tif linf.  Tiis mftiod bfibvfs bs
     * tiougi it invokfs <dodf>{@link #print(String)}</dodf> bnd tifn
     * <dodf>{@link #println()}</dodf>.
     *
     * @pbrbm x  Tif <dodf>String</dodf> to bf printfd.
     */
    publid void println(String x) {
        syndironizfd (tiis) {
            print(x);
            nfwLinf();
        }
    }

    /**
     * Prints bn Objfdt bnd tifn tfrminbtf tif linf.  Tiis mftiod dblls
     * bt first String.vblufOf(x) to gft tif printfd objfdt's string vbluf,
     * tifn bfibvfs bs
     * tiougi it invokfs <dodf>{@link #print(String)}</dodf> bnd tifn
     * <dodf>{@link #println()}</dodf>.
     *
     * @pbrbm x  Tif <dodf>Objfdt</dodf> to bf printfd.
     */
    publid void println(Objfdt x) {
        String s = String.vblufOf(x);
        syndironizfd (tiis) {
            print(s);
            nfwLinf();
        }
    }


    /**
     * A donvfnifndf mftiod to writf b formbttfd string to tiis output strfbm
     * using tif spfdififd formbt string bnd brgumfnts.
     *
     * <p> An invodbtion of tiis mftiod of tif form <tt>out.printf(formbt,
     * brgs)</tt> bfibvfs in fxbdtly tif sbmf wby bs tif invodbtion
     *
     * <prf>
     *     out.formbt(formbt, brgs) </prf>
     *
     * @pbrbm  formbt
     *         A formbt string bs dfsdribfd in <b
     *         irff="../util/Formbttfr.itml#syntbx">Formbt string syntbx</b>
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
     * @rfturn  Tiis output strfbm
     *
     * @sindf  1.5
     */
    publid PrintStrfbm printf(String formbt, Objfdt ... brgs) {
        rfturn formbt(formbt, brgs);
    }

    /**
     * A donvfnifndf mftiod to writf b formbttfd string to tiis output strfbm
     * using tif spfdififd formbt string bnd brgumfnts.
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
     *         irff="../util/Formbttfr.itml#syntbx">Formbt string syntbx</b>
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
     * @rfturn  Tiis output strfbm
     *
     * @sindf  1.5
     */
    publid PrintStrfbm printf(Lodblf l, String formbt, Objfdt ... brgs) {
        rfturn formbt(l, formbt, brgs);
    }

    /**
     * Writfs b formbttfd string to tiis output strfbm using tif spfdififd
     * formbt string bnd brgumfnts.
     *
     * <p> Tif lodblf blwbys usfd is tif onf rfturnfd by {@link
     * jbvb.util.Lodblf#gftDffbult() Lodblf.gftDffbult()}, rfgbrdlfss of bny
     * prfvious invodbtions of otifr formbtting mftiods on tiis objfdt.
     *
     * @pbrbm  formbt
     *         A formbt string bs dfsdribfd in <b
     *         irff="../util/Formbttfr.itml#syntbx">Formbt string syntbx</b>
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
     * @rfturn  Tiis output strfbm
     *
     * @sindf  1.5
     */
    publid PrintStrfbm formbt(String formbt, Objfdt ... brgs) {
        try {
            syndironizfd (tiis) {
                fnsurfOpfn();
                if ((formbttfr == null)
                    || (formbttfr.lodblf() != Lodblf.gftDffbult()))
                    formbttfr = nfw Formbttfr((Appfndbblf) tiis);
                formbttfr.formbt(Lodblf.gftDffbult(), formbt, brgs);
            }
        } dbtdi (IntfrruptfdIOExdfption x) {
            Tirfbd.durrfntTirfbd().intfrrupt();
        } dbtdi (IOExdfption x) {
            troublf = truf;
        }
        rfturn tiis;
    }

    /**
     * Writfs b formbttfd string to tiis output strfbm using tif spfdififd
     * formbt string bnd brgumfnts.
     *
     * @pbrbm  l
     *         Tif {@linkplbin jbvb.util.Lodblf lodblf} to bpply during
     *         formbtting.  If <tt>l</tt> is <tt>null</tt> tifn no lodblizbtion
     *         is bpplifd.
     *
     * @pbrbm  formbt
     *         A formbt string bs dfsdribfd in <b
     *         irff="../util/Formbttfr.itml#syntbx">Formbt string syntbx</b>
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
     * @rfturn  Tiis output strfbm
     *
     * @sindf  1.5
     */
    publid PrintStrfbm formbt(Lodblf l, String formbt, Objfdt ... brgs) {
        try {
            syndironizfd (tiis) {
                fnsurfOpfn();
                if ((formbttfr == null)
                    || (formbttfr.lodblf() != l))
                    formbttfr = nfw Formbttfr(tiis, l);
                formbttfr.formbt(l, formbt, brgs);
            }
        } dbtdi (IntfrruptfdIOExdfption x) {
            Tirfbd.durrfntTirfbd().intfrrupt();
        } dbtdi (IOExdfption x) {
            troublf = truf;
        }
        rfturn tiis;
    }

    /**
     * Appfnds tif spfdififd dibrbdtfr sfqufndf to tiis output strfbm.
     *
     * <p> An invodbtion of tiis mftiod of tif form <tt>out.bppfnd(dsq)</tt>
     * bfibvfs in fxbdtly tif sbmf wby bs tif invodbtion
     *
     * <prf>
     *     out.print(dsq.toString()) </prf>
     *
     * <p> Dfpfnding on tif spfdifidbtion of <tt>toString</tt> for tif
     * dibrbdtfr sfqufndf <tt>dsq</tt>, tif fntirf sfqufndf mby not bf
     * bppfndfd.  For instbndf, invoking tifn <tt>toString</tt> mftiod of b
     * dibrbdtfr bufffr will rfturn b subsfqufndf wiosf dontfnt dfpfnds upon
     * tif bufffr's position bnd limit.
     *
     * @pbrbm  dsq
     *         Tif dibrbdtfr sfqufndf to bppfnd.  If <tt>dsq</tt> is
     *         <tt>null</tt>, tifn tif four dibrbdtfrs <tt>"null"</tt> brf
     *         bppfndfd to tiis output strfbm.
     *
     * @rfturn  Tiis output strfbm
     *
     * @sindf  1.5
     */
    publid PrintStrfbm bppfnd(CibrSfqufndf dsq) {
        if (dsq == null)
            print("null");
        flsf
            print(dsq.toString());
        rfturn tiis;
    }

    /**
     * Appfnds b subsfqufndf of tif spfdififd dibrbdtfr sfqufndf to tiis output
     * strfbm.
     *
     * <p> An invodbtion of tiis mftiod of tif form <tt>out.bppfnd(dsq, stbrt,
     * fnd)</tt> wifn <tt>dsq</tt> is not <tt>null</tt>, bfibvfs in
     * fxbdtly tif sbmf wby bs tif invodbtion
     *
     * <prf>
     *     out.print(dsq.subSfqufndf(stbrt, fnd).toString()) </prf>
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
     * @rfturn  Tiis output strfbm
     *
     * @tirows  IndfxOutOfBoundsExdfption
     *          If <tt>stbrt</tt> or <tt>fnd</tt> brf nfgbtivf, <tt>stbrt</tt>
     *          is grfbtfr tibn <tt>fnd</tt>, or <tt>fnd</tt> is grfbtfr tibn
     *          <tt>dsq.lfngti()</tt>
     *
     * @sindf  1.5
     */
    publid PrintStrfbm bppfnd(CibrSfqufndf dsq, int stbrt, int fnd) {
        CibrSfqufndf ds = (dsq == null ? "null" : dsq);
        writf(ds.subSfqufndf(stbrt, fnd).toString());
        rfturn tiis;
    }

    /**
     * Appfnds tif spfdififd dibrbdtfr to tiis output strfbm.
     *
     * <p> An invodbtion of tiis mftiod of tif form <tt>out.bppfnd(d)</tt>
     * bfibvfs in fxbdtly tif sbmf wby bs tif invodbtion
     *
     * <prf>
     *     out.print(d) </prf>
     *
     * @pbrbm  d
     *         Tif 16-bit dibrbdtfr to bppfnd
     *
     * @rfturn  Tiis output strfbm
     *
     * @sindf  1.5
     */
    publid PrintStrfbm bppfnd(dibr d) {
        print(d);
        rfturn tiis;
    }

}
