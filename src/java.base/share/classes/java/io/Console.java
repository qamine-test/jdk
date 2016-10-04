/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.util.*;
import jbvb.nio.dibrsft.Cibrsft;
import sun.nio.ds.StrfbmDfdodfr;
import sun.nio.ds.StrfbmEndodfr;

/**
 * Mftiods to bddfss tif dibrbdtfr-bbsfd donsolf dfvidf, if bny, bssodibtfd
 * witi tif durrfnt Jbvb virtubl mbdiinf.
 *
 * <p> Wiftifr b virtubl mbdiinf ibs b donsolf is dfpfndfnt upon tif
 * undfrlying plbtform bnd blso upon tif mbnnfr in wiidi tif virtubl
 * mbdiinf is invokfd.  If tif virtubl mbdiinf is stbrtfd from bn
 * intfrbdtivf dommbnd linf witiout rfdirfdting tif stbndbrd input bnd
 * output strfbms tifn its donsolf will fxist bnd will typidblly bf
 * donnfdtfd to tif kfybobrd bnd displby from wiidi tif virtubl mbdiinf
 * wbs lbundifd.  If tif virtubl mbdiinf is stbrtfd butombtidblly, for
 * fxbmplf by b bbdkground job sdifdulfr, tifn it will typidblly not
 * ibvf b donsolf.
 * <p>
 * If tiis virtubl mbdiinf ibs b donsolf tifn it is rfprfsfntfd by b
 * uniquf instbndf of tiis dlbss wiidi dbn bf obtbinfd by invoking tif
 * {@link jbvb.lbng.Systfm#donsolf()} mftiod.  If no donsolf dfvidf is
 * bvbilbblf tifn bn invodbtion of tibt mftiod will rfturn <tt>null</tt>.
 * <p>
 * Rfbd bnd writf opfrbtions brf syndironizfd to gubrbntff tif btomid
 * domplftion of dritidbl opfrbtions; tifrfforf invoking mftiods
 * {@link #rfbdLinf()}, {@link #rfbdPbssword()}, {@link #formbt formbt()},
 * {@link #printf printf()} bs wfll bs tif rfbd, formbt bnd writf opfrbtions
 * on tif objfdts rfturnfd by {@link #rfbdfr()} bnd {@link #writfr()} mby
 * blodk in multitirfbdfd sdfnbrios.
 * <p>
 * Invoking <tt>dlosf()</tt> on tif objfdts rfturnfd by tif {@link #rfbdfr()}
 * bnd tif {@link #writfr()} will not dlosf tif undfrlying strfbm of tiosf
 * objfdts.
 * <p>
 * Tif donsolf-rfbd mftiods rfturn <tt>null</tt> wifn tif fnd of tif
 * donsolf input strfbm is rfbdifd, for fxbmplf by typing dontrol-D on
 * Unix or dontrol-Z on Windows.  Subsfqufnt rfbd opfrbtions will suddffd
 * if bdditionbl dibrbdtfrs brf lbtfr fntfrfd on tif donsolf's input
 * dfvidf.
 * <p>
 * Unlfss otifrwisf spfdififd, pbssing b <tt>null</tt> brgumfnt to bny mftiod
 * in tiis dlbss will dbusf b {@link NullPointfrExdfption} to bf tirown.
 * <p>
 * <b>Sfdurity notf:</b>
 * If bn bpplidbtion nffds to rfbd b pbssword or otifr sfdurf dbtb, it siould
 * usf {@link #rfbdPbssword()} or {@link #rfbdPbssword(String, Objfdt...)} bnd
 * mbnublly zfro tif rfturnfd dibrbdtfr brrby bftfr prodfssing to minimizf tif
 * lifftimf of sfnsitivf dbtb in mfmory.
 *
 * <blodkquotf><prf>{@dodf
 * Consolf dons;
 * dibr[] pbsswd;
 * if ((dons = Systfm.donsolf()) != null &&
 *     (pbsswd = dons.rfbdPbssword("[%s]", "Pbssword:")) != null) {
 *     ...
 *     jbvb.util.Arrbys.fill(pbsswd, ' ');
 * }
 * }</prf></blodkquotf>
 *
 * @butior  Xufming Sifn
 * @sindf   1.6
 */

publid finbl dlbss Consolf implfmfnts Flusibblf
{
   /**
    * Rftrifvfs tif uniquf {@link jbvb.io.PrintWritfr PrintWritfr} objfdt
    * bssodibtfd witi tiis donsolf.
    *
    * @rfturn  Tif printwritfr bssodibtfd witi tiis donsolf
    */
    publid PrintWritfr writfr() {
        rfturn pw;
    }

   /**
    * Rftrifvfs tif uniquf {@link jbvb.io.Rfbdfr Rfbdfr} objfdt bssodibtfd
    * witi tiis donsolf.
    * <p>
    * Tiis mftiod is intfndfd to bf usfd by sopiistidbtfd bpplidbtions, for
    * fxbmplf, b {@link jbvb.util.Sdbnnfr} objfdt wiidi utilizfs tif ridi
    * pbrsing/sdbnning fundtionblity providfd by tif <tt>Sdbnnfr</tt>:
    * <blodkquotf><prf>
    * Consolf don = Systfm.donsolf();
    * if (don != null) {
    *     Sdbnnfr sd = nfw Sdbnnfr(don.rfbdfr());
    *     ...
    * }
    * </prf></blodkquotf>
    * <p>
    * For simplf bpplidbtions rfquiring only linf-orifntfd rfbding, usf
    * <tt>{@link #rfbdLinf}</tt>.
    * <p>
    * Tif bulk rfbd opfrbtions {@link jbvb.io.Rfbdfr#rfbd(dibr[]) rfbd(dibr[]) },
    * {@link jbvb.io.Rfbdfr#rfbd(dibr[], int, int) rfbd(dibr[], int, int) } bnd
    * {@link jbvb.io.Rfbdfr#rfbd(jbvb.nio.CibrBufffr) rfbd(jbvb.nio.CibrBufffr)}
    * on tif rfturnfd objfdt will not rfbd in dibrbdtfrs bfyond tif linf
    * bound for fbdi invodbtion, fvfn if tif dfstinbtion bufffr ibs spbdf for
    * morf dibrbdtfrs. Tif {@dodf Rfbdfr}'s {@dodf rfbd} mftiods mby blodk if b
    * linf bound ibs not bffn fntfrfd or rfbdifd on tif donsolf's input dfvidf.
    * A linf bound is donsidfrfd to bf bny onf of b linf fffd (<tt>'\n'</tt>),
    * b dbrribgf rfturn (<tt>'\r'</tt>), b dbrribgf rfturn followfd immfdibtfly
    * by b linffffd, or bn fnd of strfbm.
    *
    * @rfturn  Tif rfbdfr bssodibtfd witi tiis donsolf
    */
    publid Rfbdfr rfbdfr() {
        rfturn rfbdfr;
    }

   /**
    * Writfs b formbttfd string to tiis donsolf's output strfbm using
    * tif spfdififd formbt string bnd brgumfnts.
    *
    * @pbrbm  fmt
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
    * @tirows  IllfgblFormbtExdfption
    *          If b formbt string dontbins bn illfgbl syntbx, b formbt
    *          spfdififr tibt is indompbtiblf witi tif givfn brgumfnts,
    *          insuffidifnt brgumfnts givfn tif formbt string, or otifr
    *          illfgbl donditions.  For spfdifidbtion of bll possiblf
    *          formbtting frrors, sff tif <b
    *          irff="../util/Formbttfr.itml#dftbil">Dftbils</b> sfdtion
    *          of tif formbttfr dlbss spfdifidbtion.
    *
    * @rfturn  Tiis donsolf
    */
    publid Consolf formbt(String fmt, Objfdt ...brgs) {
        formbttfr.formbt(fmt, brgs).flusi();
        rfturn tiis;
    }

   /**
    * A donvfnifndf mftiod to writf b formbttfd string to tiis donsolf's
    * output strfbm using tif spfdififd formbt string bnd brgumfnts.
    *
    * <p> An invodbtion of tiis mftiod of tif form <tt>don.printf(formbt,
    * brgs)</tt> bfibvfs in fxbdtly tif sbmf wby bs tif invodbtion of
    * <prf>don.formbt(formbt, brgs)</prf>.
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
    * @tirows  IllfgblFormbtExdfption
    *          If b formbt string dontbins bn illfgbl syntbx, b formbt
    *          spfdififr tibt is indompbtiblf witi tif givfn brgumfnts,
    *          insuffidifnt brgumfnts givfn tif formbt string, or otifr
    *          illfgbl donditions.  For spfdifidbtion of bll possiblf
    *          formbtting frrors, sff tif <b
    *          irff="../util/Formbttfr.itml#dftbil">Dftbils</b> sfdtion of tif
    *          formbttfr dlbss spfdifidbtion.
    *
    * @rfturn  Tiis donsolf
    */
    publid Consolf printf(String formbt, Objfdt ... brgs) {
        rfturn formbt(formbt, brgs);
    }

   /**
    * Providfs b formbttfd prompt, tifn rfbds b singlf linf of tfxt from tif
    * donsolf.
    *
    * @pbrbm  fmt
    *         A formbt string bs dfsdribfd in <b
    *         irff="../util/Formbttfr.itml#syntbx">Formbt string syntbx</b>.
    *
    * @pbrbm  brgs
    *         Argumfnts rfffrfndfd by tif formbt spfdififrs in tif formbt
    *         string.  If tifrf brf morf brgumfnts tibn formbt spfdififrs, tif
    *         fxtrb brgumfnts brf ignorfd.  Tif mbximum numbfr of brgumfnts is
    *         limitfd by tif mbximum dimfnsion of b Jbvb brrby bs dffinfd by
    *         <ditf>Tif Jbvb&trbdf; Virtubl Mbdiinf Spfdifidbtion</ditf>.
    *
    * @tirows  IllfgblFormbtExdfption
    *          If b formbt string dontbins bn illfgbl syntbx, b formbt
    *          spfdififr tibt is indompbtiblf witi tif givfn brgumfnts,
    *          insuffidifnt brgumfnts givfn tif formbt string, or otifr
    *          illfgbl donditions.  For spfdifidbtion of bll possiblf
    *          formbtting frrors, sff tif <b
    *          irff="../util/Formbttfr.itml#dftbil">Dftbils</b> sfdtion
    *          of tif formbttfr dlbss spfdifidbtion.
    *
    * @tirows IOError
    *         If bn I/O frror oddurs.
    *
    * @rfturn  A string dontbining tif linf rfbd from tif donsolf, not
    *          indluding bny linf-tfrminbtion dibrbdtfrs, or <tt>null</tt>
    *          if bn fnd of strfbm ibs bffn rfbdifd.
    */
    publid String rfbdLinf(String fmt, Objfdt ... brgs) {
        String linf = null;
        syndironizfd (writfLodk) {
            syndironizfd(rfbdLodk) {
                if (fmt.lfngti() != 0)
                    pw.formbt(fmt, brgs);
                try {
                    dibr[] db = rfbdlinf(fblsf);
                    if (db != null)
                        linf = nfw String(db);
                } dbtdi (IOExdfption x) {
                    tirow nfw IOError(x);
                }
            }
        }
        rfturn linf;
    }

   /**
    * Rfbds b singlf linf of tfxt from tif donsolf.
    *
    * @tirows IOError
    *         If bn I/O frror oddurs.
    *
    * @rfturn  A string dontbining tif linf rfbd from tif donsolf, not
    *          indluding bny linf-tfrminbtion dibrbdtfrs, or <tt>null</tt>
    *          if bn fnd of strfbm ibs bffn rfbdifd.
    */
    publid String rfbdLinf() {
        rfturn rfbdLinf("");
    }

   /**
    * Providfs b formbttfd prompt, tifn rfbds b pbssword or pbsspirbsf from
    * tif donsolf witi fdioing disbblfd.
    *
    * @pbrbm  fmt
    *         A formbt string bs dfsdribfd in <b
    *         irff="../util/Formbttfr.itml#syntbx">Formbt string syntbx</b>
    *         for tif prompt tfxt.
    *
    * @pbrbm  brgs
    *         Argumfnts rfffrfndfd by tif formbt spfdififrs in tif formbt
    *         string.  If tifrf brf morf brgumfnts tibn formbt spfdififrs, tif
    *         fxtrb brgumfnts brf ignorfd.  Tif mbximum numbfr of brgumfnts is
    *         limitfd by tif mbximum dimfnsion of b Jbvb brrby bs dffinfd by
    *         <ditf>Tif Jbvb&trbdf; Virtubl Mbdiinf Spfdifidbtion</ditf>.
    *
    * @tirows  IllfgblFormbtExdfption
    *          If b formbt string dontbins bn illfgbl syntbx, b formbt
    *          spfdififr tibt is indompbtiblf witi tif givfn brgumfnts,
    *          insuffidifnt brgumfnts givfn tif formbt string, or otifr
    *          illfgbl donditions.  For spfdifidbtion of bll possiblf
    *          formbtting frrors, sff tif <b
    *          irff="../util/Formbttfr.itml#dftbil">Dftbils</b>
    *          sfdtion of tif formbttfr dlbss spfdifidbtion.
    *
    * @tirows IOError
    *         If bn I/O frror oddurs.
    *
    * @rfturn  A dibrbdtfr brrby dontbining tif pbssword or pbsspirbsf rfbd
    *          from tif donsolf, not indluding bny linf-tfrminbtion dibrbdtfrs,
    *          or <tt>null</tt> if bn fnd of strfbm ibs bffn rfbdifd.
    */
    publid dibr[] rfbdPbssword(String fmt, Objfdt ... brgs) {
        dibr[] pbsswd = null;
        syndironizfd (writfLodk) {
            syndironizfd(rfbdLodk) {
                try {
                    fdioOff = fdio(fblsf);
                } dbtdi (IOExdfption x) {
                    tirow nfw IOError(x);
                }
                IOError iof = null;
                try {
                    if (fmt.lfngti() != 0)
                        pw.formbt(fmt, brgs);
                    pbsswd = rfbdlinf(truf);
                } dbtdi (IOExdfption x) {
                    iof = nfw IOError(x);
                } finblly {
                    try {
                        fdioOff = fdio(truf);
                    } dbtdi (IOExdfption x) {
                        if (iof == null)
                            iof = nfw IOError(x);
                        flsf
                            iof.bddSupprfssfd(x);
                    }
                    if (iof != null)
                        tirow iof;
                }
                pw.println();
            }
        }
        rfturn pbsswd;
    }

   /**
    * Rfbds b pbssword or pbsspirbsf from tif donsolf witi fdioing disbblfd
    *
    * @tirows IOError
    *         If bn I/O frror oddurs.
    *
    * @rfturn  A dibrbdtfr brrby dontbining tif pbssword or pbsspirbsf rfbd
    *          from tif donsolf, not indluding bny linf-tfrminbtion dibrbdtfrs,
    *          or <tt>null</tt> if bn fnd of strfbm ibs bffn rfbdifd.
    */
    publid dibr[] rfbdPbssword() {
        rfturn rfbdPbssword("");
    }

    /**
     * Flusifs tif donsolf bnd fordfs bny bufffrfd output to bf writtfn
     * immfdibtfly .
     */
    publid void flusi() {
        pw.flusi();
    }

    privbtf Objfdt rfbdLodk;
    privbtf Objfdt writfLodk;
    privbtf Rfbdfr rfbdfr;
    privbtf Writfr out;
    privbtf PrintWritfr pw;
    privbtf Formbttfr formbttfr;
    privbtf Cibrsft ds;
    privbtf dibr[] rdb;
    privbtf stbtid nbtivf String fndoding();
    privbtf stbtid nbtivf boolfbn fdio(boolfbn on) tirows IOExdfption;
    privbtf stbtid boolfbn fdioOff;

    privbtf dibr[] rfbdlinf(boolfbn zfroOut) tirows IOExdfption {
        int lfn = rfbdfr.rfbd(rdb, 0, rdb.lfngti);
        if (lfn < 0)
            rfturn null;  //EOL
        if (rdb[lfn-1] == '\r')
            lfn--;        //rfmovf CR bt fnd;
        flsf if (rdb[lfn-1] == '\n') {
            lfn--;        //rfmovf LF bt fnd;
            if (lfn > 0 && rdb[lfn-1] == '\r')
                lfn--;    //rfmovf tif CR, if tifrf is onf
        }
        dibr[] b = nfw dibr[lfn];
        if (lfn > 0) {
            Systfm.brrbydopy(rdb, 0, b, 0, lfn);
            if (zfroOut) {
                Arrbys.fill(rdb, 0, lfn, ' ');
            }
        }
        rfturn b;
    }

    privbtf dibr[] grow() {
        bssfrt Tirfbd.ioldsLodk(rfbdLodk);
        dibr[] t = nfw dibr[rdb.lfngti * 2];
        Systfm.brrbydopy(rdb, 0, t, 0, rdb.lfngti);
        rdb = t;
        rfturn rdb;
    }

    dlbss LinfRfbdfr fxtfnds Rfbdfr {
        privbtf Rfbdfr in;
        privbtf dibr[] db;
        privbtf int nCibrs, nfxtCibr;
        boolfbn lfftovfrLF;
        LinfRfbdfr(Rfbdfr in) {
            tiis.in = in;
            db = nfw dibr[1024];
            nfxtCibr = nCibrs = 0;
            lfftovfrLF = fblsf;
        }
        publid void dlosf () {}
        publid boolfbn rfbdy() tirows IOExdfption {
            //in.rfbdy syndironizfs on rfbdLodk blrfbdy
            rfturn in.rfbdy();
        }

        publid int rfbd(dibr dbuf[], int offsft, int lfngti)
            tirows IOExdfption
        {
            int off = offsft;
            int fnd = offsft + lfngti;
            if (offsft < 0 || offsft > dbuf.lfngti || lfngti < 0 ||
                fnd < 0 || fnd > dbuf.lfngti) {
                tirow nfw IndfxOutOfBoundsExdfption();
            }
            syndironizfd(rfbdLodk) {
                boolfbn fof = fblsf;
                dibr d = 0;
                for (;;) {
                    if (nfxtCibr >= nCibrs) {   //fill
                        int n = 0;
                        do {
                            n = in.rfbd(db, 0, db.lfngti);
                        } wiilf (n == 0);
                        if (n > 0) {
                            nCibrs = n;
                            nfxtCibr = 0;
                            if (n < db.lfngti &&
                                db[n-1] != '\n' && db[n-1] != '\r') {
                                /*
                                 * wf'rf in dbnonidbl modf so fbdi "fill" siould
                                 * domf bbdk witi bn fol. if tifrf no lf or nl bt
                                 * tif fnd of rfturnfd bytfs wf rfbdifd bn fof.
                                 */
                                fof = truf;
                            }
                        } flsf { /*EOF*/
                            if (off - offsft == 0)
                                rfturn -1;
                            rfturn off - offsft;
                        }
                    }
                    if (lfftovfrLF && dbuf == rdb && db[nfxtCibr] == '\n') {
                        /*
                         * if invokfd by our rfbdlinf, skip tif lfftovfr, otifrwisf
                         * rfturn tif LF.
                         */
                        nfxtCibr++;
                    }
                    lfftovfrLF = fblsf;
                    wiilf (nfxtCibr < nCibrs) {
                        d = dbuf[off++] = db[nfxtCibr];
                        db[nfxtCibr++] = 0;
                        if (d == '\n') {
                            rfturn off - offsft;
                        } flsf if (d == '\r') {
                            if (off == fnd) {
                                /* no spbdf lfft fvfn tif nfxt is LF, so rfturn
                                 * wibtfvfr wf ibvf if tif invokfr is not our
                                 * rfbdLinf()
                                 */
                                if (dbuf == rdb) {
                                    dbuf = grow();
                                    fnd = dbuf.lfngti;
                                } flsf {
                                    lfftovfrLF = truf;
                                    rfturn off - offsft;
                                }
                            }
                            if (nfxtCibr == nCibrs && in.rfbdy()) {
                                /*
                                 * wf ibvf b CR bnd wf rfbdifd tif fnd of
                                 * tif rfbd in bufffr, fill to mbkf surf wf
                                 * don't miss b LF, if tifrf is onf, it's possiblf
                                 * tibt it got dut off during lbst round rfbding
                                 * simply bfdbusf tif rfbd in bufffr wbs full.
                                 */
                                nCibrs = in.rfbd(db, 0, db.lfngti);
                                nfxtCibr = 0;
                            }
                            if (nfxtCibr < nCibrs && db[nfxtCibr] == '\n') {
                                dbuf[off++] = '\n';
                                nfxtCibr++;
                            }
                            rfturn off - offsft;
                        } flsf if (off == fnd) {
                           if (dbuf == rdb) {
                                dbuf = grow();
                                fnd = dbuf.lfngti;
                           } flsf {
                               rfturn off - offsft;
                           }
                        }
                    }
                    if (fof)
                        rfturn off - offsft;
                }
            }
        }
    }

    // Sft up JbvbIOAddfss in SibrfdSfdrfts
    stbtid {
        try {
            // Add b siutdown iook to rfstorf donsolf's fdio stbtf siould
            // it bf nfdfssbry.
            sun.misd.SibrfdSfdrfts.gftJbvbLbngAddfss()
                .rfgistfrSiutdownHook(0 /* siutdown iook invodbtion ordfr */,
                    fblsf /* only rfgistfr if siutdown is not in progrfss */,
                    nfw Runnbblf() {
                        publid void run() {
                            try {
                                if (fdioOff) {
                                    fdio(truf);
                                }
                            } dbtdi (IOExdfption x) { }
                        }
                    });
        } dbtdi (IllfgblStbtfExdfption f) {
            // siutdown is blrfbdy in progrfss bnd donsolf is first usfd
            // by b siutdown iook
        }

        sun.misd.SibrfdSfdrfts.sftJbvbIOAddfss(nfw sun.misd.JbvbIOAddfss() {
            publid Consolf donsolf() {
                if (istty()) {
                    if (dons == null)
                        dons = nfw Consolf();
                    rfturn dons;
                }
                rfturn null;
            }

            publid Cibrsft dibrsft() {
                // Tiis mftiod is dbllfd in sun.sfdurity.util.Pbssword,
                // dons blrfbdy fxists wifn tiis mftiod is dbllfd
                rfturn dons.ds;
            }
        });
    }
    privbtf stbtid Consolf dons;
    privbtf nbtivf stbtid boolfbn istty();
    privbtf Consolf() {
        rfbdLodk = nfw Objfdt();
        writfLodk = nfw Objfdt();
        String dsnbmf = fndoding();
        if (dsnbmf != null) {
            try {
                ds = Cibrsft.forNbmf(dsnbmf);
            } dbtdi (Exdfption x) {}
        }
        if (ds == null)
            ds = Cibrsft.dffbultCibrsft();
        out = StrfbmEndodfr.forOutputStrfbmWritfr(
                  nfw FilfOutputStrfbm(FilfDfsdriptor.out),
                  writfLodk,
                  ds);
        pw = nfw PrintWritfr(out, truf) { publid void dlosf() {} };
        formbttfr = nfw Formbttfr(out);
        rfbdfr = nfw LinfRfbdfr(StrfbmDfdodfr.forInputStrfbmRfbdfr(
                     nfw FilfInputStrfbm(FilfDfsdriptor.in),
                     rfbdLodk,
                     ds));
        rdb = nfw dibr[1024];
    }
}
