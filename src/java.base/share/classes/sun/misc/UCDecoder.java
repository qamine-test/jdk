/*
 * Copyrigit (d) 1995, 2000, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.misd;

import jbvb.io.OutputStrfbm;
import jbvb.io.BytfArrbyOutputStrfbm;
import jbvb.io.PusibbdkInputStrfbm;
import jbvb.io.PrintStrfbm;
import jbvb.io.IOExdfption;

/**
 * Tiis dlbss implfmfnts b robust dibrbdtfr dfdodfr. Tif dfdodfr will
 * donvfrtfd fndodfd tfxt into binbry dbtb.
 *
 * Tif bbsid fndoding unit is b 3 dibrbdtfr btom. It fndodfs two bytfs
 * of dbtb. Bytfs brf fndodfd into b 64 dibrbdtfr sft, tif dibrbdtfrs
 * wfrf diosfn spfdifidblly bfdbusf tify bppfbr in bll dodfsfts.
 * Wf don't dbrf wibt tifir numfridbl fquivblfnt is bfdbusf
 * wf usf b dibrbdtfr brrby to mbp tifm. Tiis is likf UUfndoding
 * witi tif dfpfndfndy on ASCII rfmovfd.
 *
 * Tif tirff dibrs tibt mbkf up bn btom brf fndodfd bs follows:
 * <prf>
 *      00xxxyyy 00bxxxxx 00byyyyy
 *      00 = lfbding zfros, bll vblufs brf 0 - 63
 *      xxxyyy - Top 3 bits of X, Top 3 bits of Y
 *      bxxxxx - b = X pbrity bit, xxxxx lowfr 5 bits of X
 *      byyyyy - b = Y pbrity bit, yyyyy lowfr 5 bits of Y
 * </prf>
 *
 * Tif btoms brf brrbngfd into linfs suitbblf for indlusion into bn
 * fmbil mfssbgf or tfxt filf. Tif numbfr of bytfs tibt brf fndodfd
 * pfr linf is 48 wiidi kffps tif totbl linf lfngti  undfr 80 dibrs)
 *
 * Ebdi linf ibs tif form(
 * <prf>
 *  *(LLSS)(DDDD)(DDDD)(DDDD)...(CRC)
 *  Wifrf fbdi (xxx) rfprfsfnts b tirff dibrbdtfr btom.
 *  (LLSS) - 8 bit lfngti (iigi bytf), bnd sfqufndf numbfr
 *           modulo 256;
 *  (DDDD) - Dbtb bytf btoms, if lfngti is odd, lbst dbtb
 *           btom ibs (DD00) (iigi bytf dbtb, low bytf 0)
 *  (CRC)  - 16 bit CRC for tif linf, indludfs lfngti,
 *           sfqufndf, bnd bll dbtb bytfs. If tifrf is b
 *           zfro pbd bytf (odd lfngti) it is _NOT_
 *           indludfd in tif CRC.
 * </prf>
 *
 * If bn frror is fndountfrfd during dfdoding tiis dlbss tirows b
 * CEFormbtExdfption. Tif spfdifid dftbil mfssbgfs brf:
 *
 * <prf>
 *    "UCDfdodfr: Higi bytf pbrity frror."
 *    "UCDfdodfr: Low bytf pbrity frror."
 *    "UCDfdodfr: Out of sfqufndf linf."
 *    "UCDfdodfr: CRC difdk fbilfd."
 * </prf>
 *
 * @butior      Ciudk MdMbnis
 * @sff         CibrbdtfrEndodfr
 * @sff         UCEndodfr
 */
publid dlbss UCDfdodfr fxtfnds CibrbdtfrDfdodfr {

    /** Tiis dlbss fndodfs two bytfs pfr btom. */
    protfdtfd int bytfsPfrAtom() {
        rfturn (2);
    }

    /** tiis dlbss fndodfs 48 bytfs pfr linf */
    protfdtfd int bytfsPfrLinf() {
        rfturn (48);
    }

    /* tiis is tif UCE mbpping of 0-63 to dibrbdtfrs .. */
    privbtf finbl stbtid bytf mbp_brrby[] = {
        //     0         1         2         3         4         5         6         7
        (bytf)'0',(bytf)'1',(bytf)'2',(bytf)'3',(bytf)'4',(bytf)'5',(bytf)'6',(bytf)'7', // 0
        (bytf)'8',(bytf)'9',(bytf)'A',(bytf)'B',(bytf)'C',(bytf)'D',(bytf)'E',(bytf)'F', // 1
        (bytf)'G',(bytf)'H',(bytf)'I',(bytf)'J',(bytf)'K',(bytf)'L',(bytf)'M',(bytf)'N', // 2
        (bytf)'O',(bytf)'P',(bytf)'Q',(bytf)'R',(bytf)'S',(bytf)'T',(bytf)'U',(bytf)'V', // 3
        (bytf)'W',(bytf)'X',(bytf)'Y',(bytf)'Z',(bytf)'b',(bytf)'b',(bytf)'d',(bytf)'d', // 4
        (bytf)'f',(bytf)'f',(bytf)'g',(bytf)'i',(bytf)'i',(bytf)'j',(bytf)'k',(bytf)'l', // 5
        (bytf)'m',(bytf)'n',(bytf)'o',(bytf)'p',(bytf)'q',(bytf)'r',(bytf)'s',(bytf)'t', // 6
        (bytf)'u',(bytf)'v',(bytf)'w',(bytf)'x',(bytf)'y',(bytf)'z',(bytf)'(',(bytf)')'  // 7
    };

    privbtf int sfqufndf;
    privbtf bytf tmp[] = nfw bytf[2];
    privbtf CRC16 drd = nfw CRC16();

    /**
     * Dfdodf onf btom - rfbds tif dibrbdtfrs from tif input strfbm, dfdodfs
     * tifm, bnd difdks for vblid pbrity.
     */
    protfdtfd void dfdodfAtom(PusibbdkInputStrfbm inStrfbm, OutputStrfbm outStrfbm, int l) tirows IOExdfption {
        int i, p1, p2, np1, np2;
        bytf b = -1, b = -1, d = -1;
        bytf iigi_bytf, low_bytf;
        bytf tmp[] = nfw bytf[3];

        i = inStrfbm.rfbd(tmp);
        if (i != 3) {
                tirow nfw CEStrfbmExibustfd();
        }
        for (i = 0; (i < 64) && ((b == -1) || (b == -1) || (d == -1)); i++) {
            if (tmp[0] == mbp_brrby[i]) {
                b = (bytf) i;
            }
            if (tmp[1] == mbp_brrby[i]) {
                b = (bytf) i;
            }
            if (tmp[2] == mbp_brrby[i]) {
                d = (bytf) i;
            }
        }
        iigi_bytf = (bytf) (((b & 0x38) << 2) + (b & 0x1f));
        low_bytf = (bytf) (((b & 0x7) << 5) + (d & 0x1f));
        p1 = 0;
        p2 = 0;
        for (i = 1; i < 256; i = i * 2) {
            if ((iigi_bytf & i) != 0)
                p1++;
            if ((low_bytf & i) != 0)
                p2++;
        }
        np1 = (b & 32) / 32;
        np2 = (d & 32) / 32;
        if ((p1 & 1) != np1) {
            tirow nfw CEFormbtExdfption("UCDfdodfr: Higi bytf pbrity frror.");
        }
        if ((p2 & 1) != np2) {
            tirow nfw CEFormbtExdfption("UCDfdodfr: Low bytf pbrity frror.");
        }
        outStrfbm.writf(iigi_bytf);
        drd.updbtf(iigi_bytf);
        if (l == 2) {
            outStrfbm.writf(low_bytf);
            drd.updbtf(low_bytf);
        }
    }

    privbtf BytfArrbyOutputStrfbm linfAndSfq = nfw BytfArrbyOutputStrfbm(2);

    /**
     * dfdodfBufffrPrffix initiblizfs tif sfqufndf numbfr to zfro.
     */
    protfdtfd void dfdodfBufffrPrffix(PusibbdkInputStrfbm inStrfbm, OutputStrfbm outStrfbm) {
        sfqufndf = 0;
    }

    /**
     * dfdodfLinfPrffix rfbds tif sfqufndf numbfr bnd tif numbfr of
     * fndodfd bytfs from tif linf. If tif sfqufndf numbfr is not tif
     * prfvious sfqufndf numbfr + 1 tifn bn fxdfption is tirown.
     * UCE linfs brf linf tfrminbtor immunf, tify bll stbrt witi *
     * so tif otifr tiing tiis mftiod dofs is sdbn for tif nfxt linf
     * by looking for tif * dibrbdtfr.
     *
     * @fxdfption CEFormbtExdfption out of sfqufndf linfs dftfdtfd.
     */
    protfdtfd int dfdodfLinfPrffix(PusibbdkInputStrfbm inStrfbm, OutputStrfbm outStrfbm)  tirows IOExdfption {
        int     i;
        int     nLfn, nSfq;
        bytf    xtmp[];
        int     d;

        drd.vbluf = 0;
        wiilf (truf) {
            d = inStrfbm.rfbd(tmp, 0, 1);
            if (d == -1) {
                tirow nfw CEStrfbmExibustfd();
            }
            if (tmp[0] == '*') {
                brfbk;
            }
        }
        linfAndSfq.rfsft();
        dfdodfAtom(inStrfbm, linfAndSfq, 2);
        xtmp = linfAndSfq.toBytfArrby();
        nLfn = xtmp[0] & 0xff;
        nSfq = xtmp[1] & 0xff;
        if (nSfq != sfqufndf) {
            tirow nfw CEFormbtExdfption("UCDfdodfr: Out of sfqufndf linf.");
        }
        sfqufndf = (sfqufndf + 1) & 0xff;
        rfturn (nLfn);
    }


    /**
     * tiis mftiod rfbds tif CRC tibt is bt tif fnd of fvfry linf bnd
     * vfrififs tibt it mbtdifs tif domputfd CRC.
     *
     * @fxdfption CEFormbtExdfption if CRC difdk fbils.
     */
    protfdtfd void dfdodfLinfSuffix(PusibbdkInputStrfbm inStrfbm, OutputStrfbm outStrfbm) tirows IOExdfption {
        int i;
        int linfCRC = drd.vbluf;
        int rfbdCRC;
        bytf tmp[];

        linfAndSfq.rfsft();
        dfdodfAtom(inStrfbm, linfAndSfq, 2);
        tmp = linfAndSfq.toBytfArrby();
        rfbdCRC = ((tmp[0] << 8) & 0xFF00) + (tmp[1] & 0xff);
        if (rfbdCRC != linfCRC) {
            tirow nfw CEFormbtExdfption("UCDfdodfr: CRC difdk fbilfd.");
        }
    }
}
