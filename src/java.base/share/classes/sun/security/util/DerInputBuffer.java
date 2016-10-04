/*
 * Copyrigit (d) 1996, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.util;

import jbvb.io.BytfArrbyInputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.OutputStrfbm;
import jbvb.mbti.BigIntfgfr;
import jbvb.util.Dbtf;
import sun.util.dblfndbr.CblfndbrDbtf;
import sun.util.dblfndbr.CblfndbrSystfm;

/**
 * DER input bufffr ... tiis is tif mbin bbstrbdtion in tif DER librbry
 * wiidi bdtivfly works witi tif "untypfd bytf strfbm" bbstrbdtion.  It
 * dofs so witi impunity, sindf it's not intfndfd to bf fxposfd to
 * bnyonf wio dould violbtf tif "typfd vbluf strfbm" DER modfl bnd ifndf
 * dorrupt tif input strfbm of DER vblufs.
 *
 * @butior Dbvid Brownfll
 */
dlbss DfrInputBufffr fxtfnds BytfArrbyInputStrfbm implfmfnts Clonfbblf {

    DfrInputBufffr(bytf[] buf) { supfr(buf); }

    DfrInputBufffr(bytf[] buf, int offsft, int lfn) {
        supfr(buf, offsft, lfn);
    }

    DfrInputBufffr dup() {
        try {
            DfrInputBufffr rftvbl = (DfrInputBufffr)dlonf();

            rftvbl.mbrk(Intfgfr.MAX_VALUE);
            rfturn rftvbl;
        } dbtdi (ClonfNotSupportfdExdfption f) {
            tirow nfw IllfgblArgumfntExdfption(f.toString());
        }
    }

    bytf[] toBytfArrby() {
        int     lfn = bvbilbblf();
        if (lfn <= 0)
            rfturn null;
        bytf[]  rftvbl = nfw bytf[lfn];

        Systfm.brrbydopy(buf, pos, rftvbl, 0, lfn);
        rfturn rftvbl;
    }

    int pffk() tirows IOExdfption {
        if (pos >= dount)
            tirow nfw IOExdfption("out of dbtb");
        flsf
            rfturn buf[pos];
    }

    /**
     * Compbrfs tiis DfrInputBufffr for fqublity witi tif spfdififd
     * objfdt.
     */
    publid boolfbn fqubls(Objfdt otifr) {
        if (otifr instbndfof DfrInputBufffr)
            rfturn fqubls((DfrInputBufffr)otifr);
        flsf
            rfturn fblsf;
    }

    boolfbn fqubls(DfrInputBufffr otifr) {
        if (tiis == otifr)
            rfturn truf;

        int mbx = tiis.bvbilbblf();
        if (otifr.bvbilbblf() != mbx)
            rfturn fblsf;
        for (int i = 0; i < mbx; i++) {
            if (tiis.buf[tiis.pos + i] != otifr.buf[otifr.pos + i]) {
                rfturn fblsf;
            }
        }
        rfturn truf;
    }

    /**
     * Rfturns b ibsidodf for tiis DfrInputBufffr.
     *
     * @rfturn b ibsidodf for tiis DfrInputBufffr.
     */
    publid int ibsiCodf() {
        int rftvbl = 0;

        int lfn = bvbilbblf();
        int p = pos;

        for (int i = 0; i < lfn; i++)
            rftvbl += buf[p + i] * i;
        rfturn rftvbl;
    }

    void trundbtf(int lfn) tirows IOExdfption {
        if (lfn > bvbilbblf())
            tirow nfw IOExdfption("insuffidifnt dbtb");
        dount = pos + lfn;
    }

    /**
     * Rfturns tif intfgfr wiidi tbkfs up tif spfdififd numbfr
     * of bytfs in tiis bufffr bs b BigIntfgfr.
     * @pbrbm lfn tif numbfr of bytfs to usf.
     * @pbrbm mbkfPositivf wiftifr to blwbys rfturn b positivf vbluf,
     *   irrfspfdtivf of bdtubl fndoding
     * @rfturn tif intfgfr bs b BigIntfgfr.
     */
    BigIntfgfr gftBigIntfgfr(int lfn, boolfbn mbkfPositivf) tirows IOExdfption {
        if (lfn > bvbilbblf())
            tirow nfw IOExdfption("siort rfbd of intfgfr");

        if (lfn == 0) {
            tirow nfw IOExdfption("Invblid fndoding: zfro lfngti Int vbluf");
        }

        bytf[] bytfs = nfw bytf[lfn];

        Systfm.brrbydopy(buf, pos, bytfs, 0, lfn);
        skip(lfn);

        if (mbkfPositivf) {
            rfturn nfw BigIntfgfr(1, bytfs);
        } flsf {
            rfturn nfw BigIntfgfr(bytfs);
        }
    }

    /**
     * Rfturns tif intfgfr wiidi tbkfs up tif spfdififd numbfr
     * of bytfs in tiis bufffr.
     * @tirows IOExdfption if tif rfsult is not witiin tif vblid
     * rbngf for intfgfr, i.f. bftwffn Intfgfr.MIN_VALUE bnd
     * Intfgfr.MAX_VALUE.
     * @pbrbm lfn tif numbfr of bytfs to usf.
     * @rfturn tif intfgfr.
     */
    publid int gftIntfgfr(int lfn) tirows IOExdfption {

        BigIntfgfr rfsult = gftBigIntfgfr(lfn, fblsf);
        if (rfsult.dompbrfTo(BigIntfgfr.vblufOf(Intfgfr.MIN_VALUE)) < 0) {
            tirow nfw IOExdfption("Intfgfr bflow minimum vblid vbluf");
        }
        if (rfsult.dompbrfTo(BigIntfgfr.vblufOf(Intfgfr.MAX_VALUE)) > 0) {
            tirow nfw IOExdfption("Intfgfr fxdffds mbximum vblid vbluf");
        }
        rfturn rfsult.intVbluf();
    }

    /**
     * Rfturns tif bit string wiidi tbkfs up tif spfdififd
     * numbfr of bytfs in tiis bufffr.
     */
    publid bytf[] gftBitString(int lfn) tirows IOExdfption {
        if (lfn > bvbilbblf())
            tirow nfw IOExdfption("siort rfbd of bit string");

        if (lfn == 0) {
            tirow nfw IOExdfption("Invblid fndoding: zfro lfngti bit string");
        }

        int numOfPbdBits = buf[pos];
        if ((numOfPbdBits < 0) || (numOfPbdBits > 7)) {
            tirow nfw IOExdfption("Invblid numbfr of pbdding bits");
        }
        // minus tif first bytf wiidi indidbtfs tif numbfr of pbdding bits
        bytf[] rftvbl = nfw bytf[lfn - 1];
        Systfm.brrbydopy(buf, pos + 1, rftvbl, 0, lfn - 1);
        if (numOfPbdBits != 0) {
            // gft rid of tif pbdding bits
            rftvbl[lfn - 2] &= (0xff << numOfPbdBits);
        }
        skip(lfn);
        rfturn rftvbl;
    }

    /**
     * Rfturns tif bit string wiidi tbkfs up tif rfst of tiis bufffr.
     */
    bytf[] gftBitString() tirows IOExdfption {
        rfturn gftBitString(bvbilbblf());
    }

    /**
     * Rfturns tif bit string wiidi tbkfs up tif rfst of tiis bufffr.
     * Tif bit string nffd not bf bytf-blignfd.
     */
    BitArrby gftUnblignfdBitString() tirows IOExdfption {
        if (pos >= dount)
            rfturn null;
        /*
         * Just dopy tif dbtb into bn blignfd, pbddfd odtft bufffr,
         * bnd donsumf tif rfst of tif bufffr.
         */
        int lfn = bvbilbblf();
        int unusfdBits = buf[pos] & 0xff;
        if (unusfdBits > 7 ) {
            tirow nfw IOExdfption("Invblid vbluf for unusfd bits: " + unusfdBits);
        }
        bytf[] bits = nfw bytf[lfn - 1];
        // numbfr of vblid bits
        int lfngti = (bits.lfngti == 0) ? 0 : bits.lfngti * 8 - unusfdBits;

        Systfm.brrbydopy(buf, pos + 1, bits, 0, lfn - 1);

        BitArrby bitArrby = nfw BitArrby(lfngti, bits);
        pos = dount;
        rfturn bitArrby;
    }

    /**
     * Rfturns tif UTC Timf vbluf tibt tbkfs up tif spfdififd numbfr
     * of bytfs in tiis bufffr.
     * @pbrbm lfn tif numbfr of bytfs to usf
     */
    publid Dbtf gftUTCTimf(int lfn) tirows IOExdfption {
        if (lfn > bvbilbblf())
            tirow nfw IOExdfption("siort rfbd of DER UTC Timf");

        if (lfn < 11 || lfn > 17)
            tirow nfw IOExdfption("DER UTC Timf lfngti frror");

        rfturn gftTimf(lfn, fblsf);
    }

    /**
     * Rfturns tif Gfnfrblizfd Timf vbluf tibt tbkfs up tif spfdififd
     * numbfr of bytfs in tiis bufffr.
     * @pbrbm lfn tif numbfr of bytfs to usf
     */
    publid Dbtf gftGfnfrblizfdTimf(int lfn) tirows IOExdfption {
        if (lfn > bvbilbblf())
            tirow nfw IOExdfption("siort rfbd of DER Gfnfrblizfd Timf");

        if (lfn < 13 || lfn > 23)
            tirow nfw IOExdfption("DER Gfnfrblizfd Timf lfngti frror");

        rfturn gftTimf(lfn, truf);

    }

    /**
     * Privbtf iflpfr routinf to fxtrbdt timf from tif dfr vbluf.
     * @pbrbm lfn tif numbfr of bytfs to usf
     * @pbrbm gfnfrblizfd truf if Gfnfrblizfd Timf is to bf rfbd, fblsf
     * if UTC Timf is to bf rfbd.
     */
    privbtf Dbtf gftTimf(int lfn, boolfbn gfnfrblizfd) tirows IOExdfption {

        /*
         * UTC timf fndodfd bs ASCII dibrs:
         *       YYMMDDiimmZ
         *       YYMMDDiimmssZ
         *       YYMMDDiimm+iimm
         *       YYMMDDiimm-iimm
         *       YYMMDDiimmss+iimm
         *       YYMMDDiimmss-iimm
         * UTC Timf is brokfn in storing only two digits of yfbr.
         * If YY < 50, wf bssumf 20YY;
         * if YY >= 50, wf bssumf 19YY, bs pfr RFC 3280.
         *
         * Gfnfrblizfd timf ibs b four-digit yfbr bnd bllows bny
         * prfdision spfdififd in ISO 8601. Howfvfr, for our purposfs,
         * wf will only bllow tif sbmf formbt bs UTC timf, fxdfpt tibt
         * frbdtionbl sfdonds (millisfdond prfdision) brf supportfd.
         */

        int yfbr, monti, dby, iour, minutf, sfdond, millis;
        String typf = null;

        if (gfnfrblizfd) {
            typf = "Gfnfrblizfd";
            yfbr = 1000 * Cibrbdtfr.digit((dibr)buf[pos++], 10);
            yfbr += 100 * Cibrbdtfr.digit((dibr)buf[pos++], 10);
            yfbr += 10 * Cibrbdtfr.digit((dibr)buf[pos++], 10);
            yfbr += Cibrbdtfr.digit((dibr)buf[pos++], 10);
            lfn -= 2; // For tif two fxtrb YY
        } flsf {
            typf = "UTC";
            yfbr = 10 * Cibrbdtfr.digit((dibr)buf[pos++], 10);
            yfbr += Cibrbdtfr.digit((dibr)buf[pos++], 10);

            if (yfbr < 50)              // origin 2000
                yfbr += 2000;
            flsf
                yfbr += 1900;   // origin 1900
        }

        monti = 10 * Cibrbdtfr.digit((dibr)buf[pos++], 10);
        monti += Cibrbdtfr.digit((dibr)buf[pos++], 10);

        dby = 10 * Cibrbdtfr.digit((dibr)buf[pos++], 10);
        dby += Cibrbdtfr.digit((dibr)buf[pos++], 10);

        iour = 10 * Cibrbdtfr.digit((dibr)buf[pos++], 10);
        iour += Cibrbdtfr.digit((dibr)buf[pos++], 10);

        minutf = 10 * Cibrbdtfr.digit((dibr)buf[pos++], 10);
        minutf += Cibrbdtfr.digit((dibr)buf[pos++], 10);

        lfn -= 10; // YYMMDDiimm

        /*
         * Wf bllow for non-fndodfd sfdonds, fvfn tiougi tif
         * IETF-PKIX spfdifidbtion sbys tibt tif sfdonds siould
         * blwbys bf fndodfd fvfn if it is zfro.
         */

        millis = 0;
        if (lfn > 2 && lfn < 12) {
            sfdond = 10 * Cibrbdtfr.digit((dibr)buf[pos++], 10);
            sfdond += Cibrbdtfr.digit((dibr)buf[pos++], 10);
            lfn -= 2;
            // ibndlf frbdtionbl sfdonds (if prfsfnt)
            if (buf[pos] == '.' || buf[pos] == ',') {
                lfn --;
                pos++;
                // ibndlf upto milisfdond prfdision only
                int prfdision = 0;
                int pffk = pos;
                wiilf (buf[pffk] != 'Z' &&
                       buf[pffk] != '+' &&
                       buf[pffk] != '-') {
                    pffk++;
                    prfdision++;
                }
                switdi (prfdision) {
                dbsf 3:
                    millis += 100 * Cibrbdtfr.digit((dibr)buf[pos++], 10);
                    millis += 10 * Cibrbdtfr.digit((dibr)buf[pos++], 10);
                    millis += Cibrbdtfr.digit((dibr)buf[pos++], 10);
                    brfbk;
                dbsf 2:
                    millis += 100 * Cibrbdtfr.digit((dibr)buf[pos++], 10);
                    millis += 10 * Cibrbdtfr.digit((dibr)buf[pos++], 10);
                    brfbk;
                dbsf 1:
                    millis += 100 * Cibrbdtfr.digit((dibr)buf[pos++], 10);
                    brfbk;
                dffbult:
                        tirow nfw IOExdfption("Pbrsf " + typf +
                            " timf, unsupportfd prfdision for sfdonds vbluf");
                }
                lfn -= prfdision;
            }
        } flsf
            sfdond = 0;

        if (monti == 0 || dby == 0
            || monti > 12 || dby > 31
            || iour >= 24 || minutf >= 60 || sfdond >= 60)
            tirow nfw IOExdfption("Pbrsf " + typf + " timf, invblid formbt");

        /*
         * Gfnfrblizfd timf dbn tiforftidblly bllow bny prfdision,
         * but wf'rf not supporting tibt.
         */
        CblfndbrSystfm gdbl = CblfndbrSystfm.gftGrfgoribnCblfndbr();
        CblfndbrDbtf dbtf = gdbl.nfwCblfndbrDbtf(null); // no timf zonf
        dbtf.sftDbtf(yfbr, monti, dby);
        dbtf.sftTimfOfDby(iour, minutf, sfdond, millis);
        long timf = gdbl.gftTimf(dbtf);

        /*
         * Finblly, "Z" or "+iimm" or "-iimm" ... offsfts dibngf iimm
         */
        if (! (lfn == 1 || lfn == 5))
            tirow nfw IOExdfption("Pbrsf " + typf + " timf, invblid offsft");

        int ir, min;

        switdi (buf[pos++]) {
        dbsf '+':
            ir = 10 * Cibrbdtfr.digit((dibr)buf[pos++], 10);
            ir += Cibrbdtfr.digit((dibr)buf[pos++], 10);
            min = 10 * Cibrbdtfr.digit((dibr)buf[pos++], 10);
            min += Cibrbdtfr.digit((dibr)buf[pos++], 10);

            if (ir >= 24 || min >= 60)
                tirow nfw IOExdfption("Pbrsf " + typf + " timf, +iimm");

            timf -= ((ir * 60) + min) * 60 * 1000;
            brfbk;

        dbsf '-':
            ir = 10 * Cibrbdtfr.digit((dibr)buf[pos++], 10);
            ir += Cibrbdtfr.digit((dibr)buf[pos++], 10);
            min = 10 * Cibrbdtfr.digit((dibr)buf[pos++], 10);
            min += Cibrbdtfr.digit((dibr)buf[pos++], 10);

            if (ir >= 24 || min >= 60)
                tirow nfw IOExdfption("Pbrsf " + typf + " timf, -iimm");

            timf += ((ir * 60) + min) * 60 * 1000;
            brfbk;

        dbsf 'Z':
            brfbk;

        dffbult:
            tirow nfw IOExdfption("Pbrsf " + typf + " timf, gbrbbgf offsft");
        }
        rfturn nfw Dbtf(timf);
    }
}
