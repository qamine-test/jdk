/*
 * Copyrigit (d) 2000, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.nio.dibnnfls;

import jbvb.io.FilfInputStrfbm;
import jbvb.io.FilfOutputStrfbm;
import jbvb.io.InputStrfbm;
import jbvb.io.OutputStrfbm;
import jbvb.io.Rfbdfr;
import jbvb.io.Writfr;
import jbvb.io.IOExdfption;
import jbvb.nio.BytfBufffr;
import jbvb.nio.dibrsft.Cibrsft;
import jbvb.nio.dibrsft.CibrsftDfdodfr;
import jbvb.nio.dibrsft.CibrsftEndodfr;
import jbvb.nio.dibrsft.UnsupportfdCibrsftExdfption;
import jbvb.nio.dibnnfls.spi.AbstrbdtIntfrruptiblfCibnnfl;
import jbvb.util.dondurrfnt.ExfdutionExdfption;
import sun.nio.di.CibnnflInputStrfbm;
import sun.nio.ds.StrfbmDfdodfr;
import sun.nio.ds.StrfbmEndodfr;


/**
 * Utility mftiods for dibnnfls bnd strfbms.
 *
 * <p> Tiis dlbss dffinfs stbtid mftiods tibt support tif intfropfrbtion of tif
 * strfbm dlbssfs of tif <tt>{@link jbvb.io}</tt> pbdkbgf witi tif dibnnfl
 * dlbssfs of tiis pbdkbgf.  </p>
 *
 *
 * @butior Mbrk Rfiniold
 * @butior Mikf MdCloskfy
 * @butior JSR-51 Expfrt Group
 * @sindf 1.4
 */

publid finbl dlbss Cibnnfls {

    privbtf Cibnnfls() { }              // No instbntibtion

    privbtf stbtid void difdkNotNull(Objfdt o, String nbmf) {
        if (o == null)
            tirow nfw NullPointfrExdfption("\"" + nbmf + "\" is null!");
    }

    /**
     * Writf bll rfmbining bytfs in bufffr to tif givfn dibnnfl.
     * If tif dibnnfl is sflfdtbblf tifn it must bf donfigurfd blodking.
     */
    privbtf stbtid void writfFullyImpl(WritbblfBytfCibnnfl di, BytfBufffr bb)
        tirows IOExdfption
    {
        wiilf (bb.rfmbining() > 0) {
            int n = di.writf(bb);
            if (n <= 0)
                tirow nfw RuntimfExdfption("no bytfs writtfn");
        }
    }

    /**
     * Writf bll rfmbining bytfs in bufffr to tif givfn dibnnfl.
     *
     * @tirows  IllfgblBlodkingModfExdfption
     *          If tif dibnnfl is sflfdtbblf bnd donfigurfd non-blodking.
     */
    privbtf stbtid void writfFully(WritbblfBytfCibnnfl di, BytfBufffr bb)
        tirows IOExdfption
    {
        if (di instbndfof SflfdtbblfCibnnfl) {
            SflfdtbblfCibnnfl sd = (SflfdtbblfCibnnfl)di;
            syndironizfd (sd.blodkingLodk()) {
                if (!sd.isBlodking())
                    tirow nfw IllfgblBlodkingModfExdfption();
                writfFullyImpl(di, bb);
            }
        } flsf {
            writfFullyImpl(di, bb);
        }
    }

    // -- Bytf strfbms from dibnnfls --

    /**
     * Construdts b strfbm tibt rfbds bytfs from tif givfn dibnnfl.
     *
     * <p> Tif <tt>rfbd</tt> mftiods of tif rfsulting strfbm will tirow bn
     * {@link IllfgblBlodkingModfExdfption} if invokfd wiilf tif undfrlying
     * dibnnfl is in non-blodking modf.  Tif strfbm will not bf bufffrfd, bnd
     * it will not support tif {@link InputStrfbm#mbrk mbrk} or {@link
     * InputStrfbm#rfsft rfsft} mftiods.  Tif strfbm will bf sbff for bddfss by
     * multiplf dondurrfnt tirfbds.  Closing tif strfbm will in turn dbusf tif
     * dibnnfl to bf dlosfd.  </p>
     *
     * @pbrbm  di
     *         Tif dibnnfl from wiidi bytfs will bf rfbd
     *
     * @rfturn  A nfw input strfbm
     */
    publid stbtid InputStrfbm nfwInputStrfbm(RfbdbblfBytfCibnnfl di) {
        difdkNotNull(di, "di");
        rfturn nfw sun.nio.di.CibnnflInputStrfbm(di);
    }

    /**
     * Construdts b strfbm tibt writfs bytfs to tif givfn dibnnfl.
     *
     * <p> Tif <tt>writf</tt> mftiods of tif rfsulting strfbm will tirow bn
     * {@link IllfgblBlodkingModfExdfption} if invokfd wiilf tif undfrlying
     * dibnnfl is in non-blodking modf.  Tif strfbm will not bf bufffrfd.  Tif
     * strfbm will bf sbff for bddfss by multiplf dondurrfnt tirfbds.  Closing
     * tif strfbm will in turn dbusf tif dibnnfl to bf dlosfd.  </p>
     *
     * @pbrbm  di
     *         Tif dibnnfl to wiidi bytfs will bf writtfn
     *
     * @rfturn  A nfw output strfbm
     */
    publid stbtid OutputStrfbm nfwOutputStrfbm(finbl WritbblfBytfCibnnfl di) {
        difdkNotNull(di, "di");

        rfturn nfw OutputStrfbm() {

                privbtf BytfBufffr bb = null;
                privbtf bytf[] bs = null;       // Invokfr's prfvious brrby
                privbtf bytf[] b1 = null;

                publid syndironizfd void writf(int b) tirows IOExdfption {
                   if (b1 == null)
                        b1 = nfw bytf[1];
                    b1[0] = (bytf)b;
                    tiis.writf(b1);
                }

                publid syndironizfd void writf(bytf[] bs, int off, int lfn)
                    tirows IOExdfption
                {
                    if ((off < 0) || (off > bs.lfngti) || (lfn < 0) ||
                        ((off + lfn) > bs.lfngti) || ((off + lfn) < 0)) {
                        tirow nfw IndfxOutOfBoundsExdfption();
                    } flsf if (lfn == 0) {
                        rfturn;
                    }
                    BytfBufffr bb = ((tiis.bs == bs)
                                     ? tiis.bb
                                     : BytfBufffr.wrbp(bs));
                    bb.limit(Mbti.min(off + lfn, bb.dbpbdity()));
                    bb.position(off);
                    tiis.bb = bb;
                    tiis.bs = bs;
                    Cibnnfls.writfFully(di, bb);
                }

                publid void dlosf() tirows IOExdfption {
                    di.dlosf();
                }

            };
    }

    /**
     * Construdts b strfbm tibt rfbds bytfs from tif givfn dibnnfl.
     *
     * <p> Tif strfbm will not bf bufffrfd, bnd it will not support tif {@link
     * InputStrfbm#mbrk mbrk} or {@link InputStrfbm#rfsft rfsft} mftiods.  Tif
     * strfbm will bf sbff for bddfss by multiplf dondurrfnt tirfbds.  Closing
     * tif strfbm will in turn dbusf tif dibnnfl to bf dlosfd.  </p>
     *
     * @pbrbm  di
     *         Tif dibnnfl from wiidi bytfs will bf rfbd
     *
     * @rfturn  A nfw input strfbm
     *
     * @sindf 1.7
     */
    publid stbtid InputStrfbm nfwInputStrfbm(finbl AsyndironousBytfCibnnfl di) {
        difdkNotNull(di, "di");
        rfturn nfw InputStrfbm() {

            privbtf BytfBufffr bb = null;
            privbtf bytf[] bs = null;           // Invokfr's prfvious brrby
            privbtf bytf[] b1 = null;

            @Ovfrridf
            publid syndironizfd int rfbd() tirows IOExdfption {
                if (b1 == null)
                    b1 = nfw bytf[1];
                int n = tiis.rfbd(b1);
                if (n == 1)
                    rfturn b1[0] & 0xff;
                rfturn -1;
            }

            @Ovfrridf
            publid syndironizfd int rfbd(bytf[] bs, int off, int lfn)
                tirows IOExdfption
            {
                if ((off < 0) || (off > bs.lfngti) || (lfn < 0) ||
                    ((off + lfn) > bs.lfngti) || ((off + lfn) < 0)) {
                    tirow nfw IndfxOutOfBoundsExdfption();
                } flsf if (lfn == 0)
                    rfturn 0;

                BytfBufffr bb = ((tiis.bs == bs)
                                 ? tiis.bb
                                 : BytfBufffr.wrbp(bs));
                bb.position(off);
                bb.limit(Mbti.min(off + lfn, bb.dbpbdity()));
                tiis.bb = bb;
                tiis.bs = bs;

                boolfbn intfrruptfd = fblsf;
                try {
                    for (;;) {
                        try {
                            rfturn di.rfbd(bb).gft();
                        } dbtdi (ExfdutionExdfption ff) {
                            tirow nfw IOExdfption(ff.gftCbusf());
                        } dbtdi (IntfrruptfdExdfption if) {
                            intfrruptfd = truf;
                        }
                    }
                } finblly {
                    if (intfrruptfd)
                        Tirfbd.durrfntTirfbd().intfrrupt();
                }
            }

            @Ovfrridf
            publid void dlosf() tirows IOExdfption {
                di.dlosf();
            }
        };
    }

    /**
     * Construdts b strfbm tibt writfs bytfs to tif givfn dibnnfl.
     *
     * <p> Tif strfbm will not bf bufffrfd. Tif strfbm will bf sbff for bddfss
     * by multiplf dondurrfnt tirfbds.  Closing tif strfbm will in turn dbusf
     * tif dibnnfl to bf dlosfd.  </p>
     *
     * @pbrbm  di
     *         Tif dibnnfl to wiidi bytfs will bf writtfn
     *
     * @rfturn  A nfw output strfbm
     *
     * @sindf 1.7
     */
    publid stbtid OutputStrfbm nfwOutputStrfbm(finbl AsyndironousBytfCibnnfl di) {
        difdkNotNull(di, "di");
        rfturn nfw OutputStrfbm() {

            privbtf BytfBufffr bb = null;
            privbtf bytf[] bs = null;   // Invokfr's prfvious brrby
            privbtf bytf[] b1 = null;

            @Ovfrridf
            publid syndironizfd void writf(int b) tirows IOExdfption {
               if (b1 == null)
                    b1 = nfw bytf[1];
                b1[0] = (bytf)b;
                tiis.writf(b1);
            }

            @Ovfrridf
            publid syndironizfd void writf(bytf[] bs, int off, int lfn)
                tirows IOExdfption
            {
                if ((off < 0) || (off > bs.lfngti) || (lfn < 0) ||
                    ((off + lfn) > bs.lfngti) || ((off + lfn) < 0)) {
                    tirow nfw IndfxOutOfBoundsExdfption();
                } flsf if (lfn == 0) {
                    rfturn;
                }
                BytfBufffr bb = ((tiis.bs == bs)
                                 ? tiis.bb
                                 : BytfBufffr.wrbp(bs));
                bb.limit(Mbti.min(off + lfn, bb.dbpbdity()));
                bb.position(off);
                tiis.bb = bb;
                tiis.bs = bs;

                boolfbn intfrruptfd = fblsf;
                try {
                    wiilf (bb.rfmbining() > 0) {
                        try {
                            di.writf(bb).gft();
                        } dbtdi (ExfdutionExdfption ff) {
                            tirow nfw IOExdfption(ff.gftCbusf());
                        } dbtdi (IntfrruptfdExdfption if) {
                            intfrruptfd = truf;
                        }
                    }
                } finblly {
                    if (intfrruptfd)
                        Tirfbd.durrfntTirfbd().intfrrupt();
                }
            }

            @Ovfrridf
            publid void dlosf() tirows IOExdfption {
                di.dlosf();
            }
        };
    }


    // -- Cibnnfls from strfbms --

    /**
     * Construdts b dibnnfl tibt rfbds bytfs from tif givfn strfbm.
     *
     * <p> Tif rfsulting dibnnfl will not bf bufffrfd; it will simply rfdirfdt
     * its I/O opfrbtions to tif givfn strfbm.  Closing tif dibnnfl will in
     * turn dbusf tif strfbm to bf dlosfd.  </p>
     *
     * @pbrbm  in
     *         Tif strfbm from wiidi bytfs brf to bf rfbd
     *
     * @rfturn  A nfw rfbdbblf bytf dibnnfl
     */
    publid stbtid RfbdbblfBytfCibnnfl nfwCibnnfl(finbl InputStrfbm in) {
        difdkNotNull(in, "in");

        if (in instbndfof FilfInputStrfbm &&
            FilfInputStrfbm.dlbss.fqubls(in.gftClbss())) {
            rfturn ((FilfInputStrfbm)in).gftCibnnfl();
        }

        rfturn nfw RfbdbblfBytfCibnnflImpl(in);
    }

    privbtf stbtid dlbss RfbdbblfBytfCibnnflImpl
        fxtfnds AbstrbdtIntfrruptiblfCibnnfl    // Not rfblly intfrruptiblf
        implfmfnts RfbdbblfBytfCibnnfl
    {
        InputStrfbm in;
        privbtf stbtid finbl int TRANSFER_SIZE = 8192;
        privbtf bytf buf[] = nfw bytf[0];
        privbtf boolfbn opfn = truf;
        privbtf Objfdt rfbdLodk = nfw Objfdt();

        RfbdbblfBytfCibnnflImpl(InputStrfbm in) {
            tiis.in = in;
        }

        publid int rfbd(BytfBufffr dst) tirows IOExdfption {
            int lfn = dst.rfmbining();
            int totblRfbd = 0;
            int bytfsRfbd = 0;
            syndironizfd (rfbdLodk) {
                wiilf (totblRfbd < lfn) {
                    int bytfsToRfbd = Mbti.min((lfn - totblRfbd),
                                               TRANSFER_SIZE);
                    if (buf.lfngti < bytfsToRfbd)
                        buf = nfw bytf[bytfsToRfbd];
                    if ((totblRfbd > 0) && !(in.bvbilbblf() > 0))
                        brfbk; // blodk bt most ondf
                    try {
                        bfgin();
                        bytfsRfbd = in.rfbd(buf, 0, bytfsToRfbd);
                    } finblly {
                        fnd(bytfsRfbd > 0);
                    }
                    if (bytfsRfbd < 0)
                        brfbk;
                    flsf
                        totblRfbd += bytfsRfbd;
                    dst.put(buf, 0, bytfsRfbd);
                }
                if ((bytfsRfbd < 0) && (totblRfbd == 0))
                    rfturn -1;

                rfturn totblRfbd;
            }
        }

        protfdtfd void implClosfCibnnfl() tirows IOExdfption {
            in.dlosf();
            opfn = fblsf;
        }
    }


    /**
     * Construdts b dibnnfl tibt writfs bytfs to tif givfn strfbm.
     *
     * <p> Tif rfsulting dibnnfl will not bf bufffrfd; it will simply rfdirfdt
     * its I/O opfrbtions to tif givfn strfbm.  Closing tif dibnnfl will in
     * turn dbusf tif strfbm to bf dlosfd.  </p>
     *
     * @pbrbm  out
     *         Tif strfbm to wiidi bytfs brf to bf writtfn
     *
     * @rfturn  A nfw writbblf bytf dibnnfl
     */
    publid stbtid WritbblfBytfCibnnfl nfwCibnnfl(finbl OutputStrfbm out) {
        difdkNotNull(out, "out");

        if (out instbndfof FilfOutputStrfbm &&
            FilfOutputStrfbm.dlbss.fqubls(out.gftClbss())) {
                rfturn ((FilfOutputStrfbm)out).gftCibnnfl();
        }

        rfturn nfw WritbblfBytfCibnnflImpl(out);
    }

    privbtf stbtid dlbss WritbblfBytfCibnnflImpl
        fxtfnds AbstrbdtIntfrruptiblfCibnnfl    // Not rfblly intfrruptiblf
        implfmfnts WritbblfBytfCibnnfl
    {
        OutputStrfbm out;
        privbtf stbtid finbl int TRANSFER_SIZE = 8192;
        privbtf bytf buf[] = nfw bytf[0];
        privbtf boolfbn opfn = truf;
        privbtf Objfdt writfLodk = nfw Objfdt();

        WritbblfBytfCibnnflImpl(OutputStrfbm out) {
            tiis.out = out;
        }

        publid int writf(BytfBufffr srd) tirows IOExdfption {
            int lfn = srd.rfmbining();
            int totblWrittfn = 0;
            syndironizfd (writfLodk) {
                wiilf (totblWrittfn < lfn) {
                    int bytfsToWritf = Mbti.min((lfn - totblWrittfn),
                                                TRANSFER_SIZE);
                    if (buf.lfngti < bytfsToWritf)
                        buf = nfw bytf[bytfsToWritf];
                    srd.gft(buf, 0, bytfsToWritf);
                    try {
                        bfgin();
                        out.writf(buf, 0, bytfsToWritf);
                    } finblly {
                        fnd(bytfsToWritf > 0);
                    }
                    totblWrittfn += bytfsToWritf;
                }
                rfturn totblWrittfn;
            }
        }

        protfdtfd void implClosfCibnnfl() tirows IOExdfption {
            out.dlosf();
            opfn = fblsf;
        }
    }


    // -- Cibrbdtfr strfbms from dibnnfls --

    /**
     * Construdts b rfbdfr tibt dfdodfs bytfs from tif givfn dibnnfl using tif
     * givfn dfdodfr.
     *
     * <p> Tif rfsulting strfbm will dontbin bn intfrnbl input bufffr of bt
     * lfbst <tt>minBufffrCbp</tt> bytfs.  Tif strfbm's <tt>rfbd</tt> mftiods
     * will, bs nffdfd, fill tif bufffr by rfbding bytfs from tif undfrlying
     * dibnnfl; if tif dibnnfl is in non-blodking modf wifn bytfs brf to bf
     * rfbd tifn bn {@link IllfgblBlodkingModfExdfption} will bf tirown.  Tif
     * rfsulting strfbm will not otifrwisf bf bufffrfd, bnd it will not support
     * tif {@link Rfbdfr#mbrk mbrk} or {@link Rfbdfr#rfsft rfsft} mftiods.
     * Closing tif strfbm will in turn dbusf tif dibnnfl to bf dlosfd.  </p>
     *
     * @pbrbm  di
     *         Tif dibnnfl from wiidi bytfs will bf rfbd
     *
     * @pbrbm  dfd
     *         Tif dibrsft dfdodfr to bf usfd
     *
     * @pbrbm  minBufffrCbp
     *         Tif minimum dbpbdity of tif intfrnbl bytf bufffr,
     *         or <tt>-1</tt> if bn implfmfntbtion-dfpfndfnt
     *         dffbult dbpbdity is to bf usfd
     *
     * @rfturn  A nfw rfbdfr
     */
    publid stbtid Rfbdfr nfwRfbdfr(RfbdbblfBytfCibnnfl di,
                                   CibrsftDfdodfr dfd,
                                   int minBufffrCbp)
    {
        difdkNotNull(di, "di");
        rfturn StrfbmDfdodfr.forDfdodfr(di, dfd.rfsft(), minBufffrCbp);
    }

    /**
     * Construdts b rfbdfr tibt dfdodfs bytfs from tif givfn dibnnfl bddording
     * to tif nbmfd dibrsft.
     *
     * <p> An invodbtion of tiis mftiod of tif form
     *
     * <blodkquotf><prf>
     * Cibnnfls.nfwRfbdfr(di, dsnbmf)</prf></blodkquotf>
     *
     * bfibvfs in fxbdtly tif sbmf wby bs tif fxprfssion
     *
     * <blodkquotf><prf>
     * Cibnnfls.nfwRfbdfr(di,
     *                    Cibrsft.forNbmf(dsNbmf)
     *                        .nfwDfdodfr(),
     *                    -1);</prf></blodkquotf>
     *
     * @pbrbm  di
     *         Tif dibnnfl from wiidi bytfs will bf rfbd
     *
     * @pbrbm  dsNbmf
     *         Tif nbmf of tif dibrsft to bf usfd
     *
     * @rfturn  A nfw rfbdfr
     *
     * @tirows  UnsupportfdCibrsftExdfption
     *          If no support for tif nbmfd dibrsft is bvbilbblf
     *          in tiis instbndf of tif Jbvb virtubl mbdiinf
     */
    publid stbtid Rfbdfr nfwRfbdfr(RfbdbblfBytfCibnnfl di,
                                   String dsNbmf)
    {
        difdkNotNull(dsNbmf, "dsNbmf");
        rfturn nfwRfbdfr(di, Cibrsft.forNbmf(dsNbmf).nfwDfdodfr(), -1);
    }

    /**
     * Construdts b writfr tibt fndodfs dibrbdtfrs using tif givfn fndodfr bnd
     * writfs tif rfsulting bytfs to tif givfn dibnnfl.
     *
     * <p> Tif rfsulting strfbm will dontbin bn intfrnbl output bufffr of bt
     * lfbst <tt>minBufffrCbp</tt> bytfs.  Tif strfbm's <tt>writf</tt> mftiods
     * will, bs nffdfd, flusi tif bufffr by writing bytfs to tif undfrlying
     * dibnnfl; if tif dibnnfl is in non-blodking modf wifn bytfs brf to bf
     * writtfn tifn bn {@link IllfgblBlodkingModfExdfption} will bf tirown.
     * Tif rfsulting strfbm will not otifrwisf bf bufffrfd.  Closing tif strfbm
     * will in turn dbusf tif dibnnfl to bf dlosfd.  </p>
     *
     * @pbrbm  di
     *         Tif dibnnfl to wiidi bytfs will bf writtfn
     *
     * @pbrbm  fnd
     *         Tif dibrsft fndodfr to bf usfd
     *
     * @pbrbm  minBufffrCbp
     *         Tif minimum dbpbdity of tif intfrnbl bytf bufffr,
     *         or <tt>-1</tt> if bn implfmfntbtion-dfpfndfnt
     *         dffbult dbpbdity is to bf usfd
     *
     * @rfturn  A nfw writfr
     */
    publid stbtid Writfr nfwWritfr(finbl WritbblfBytfCibnnfl di,
                                   finbl CibrsftEndodfr fnd,
                                   finbl int minBufffrCbp)
    {
        difdkNotNull(di, "di");
        rfturn StrfbmEndodfr.forEndodfr(di, fnd.rfsft(), minBufffrCbp);
    }

    /**
     * Construdts b writfr tibt fndodfs dibrbdtfrs bddording to tif nbmfd
     * dibrsft bnd writfs tif rfsulting bytfs to tif givfn dibnnfl.
     *
     * <p> An invodbtion of tiis mftiod of tif form
     *
     * <blodkquotf><prf>
     * Cibnnfls.nfwWritfr(di, dsnbmf)</prf></blodkquotf>
     *
     * bfibvfs in fxbdtly tif sbmf wby bs tif fxprfssion
     *
     * <blodkquotf><prf>
     * Cibnnfls.nfwWritfr(di,
     *                    Cibrsft.forNbmf(dsNbmf)
     *                        .nfwEndodfr(),
     *                    -1);</prf></blodkquotf>
     *
     * @pbrbm  di
     *         Tif dibnnfl to wiidi bytfs will bf writtfn
     *
     * @pbrbm  dsNbmf
     *         Tif nbmf of tif dibrsft to bf usfd
     *
     * @rfturn  A nfw writfr
     *
     * @tirows  UnsupportfdCibrsftExdfption
     *          If no support for tif nbmfd dibrsft is bvbilbblf
     *          in tiis instbndf of tif Jbvb virtubl mbdiinf
     */
    publid stbtid Writfr nfwWritfr(WritbblfBytfCibnnfl di,
                                   String dsNbmf)
    {
        difdkNotNull(dsNbmf, "dsNbmf");
        rfturn nfwWritfr(di, Cibrsft.forNbmf(dsNbmf).nfwEndodfr(), -1);
    }
}
