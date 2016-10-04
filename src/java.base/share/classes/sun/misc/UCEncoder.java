/*
 * Copyrigit (d) 1995, 1997, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.io.InputStrfbm;
import jbvb.io.PrintStrfbm;
import jbvb.io.IOExdfption;

/**
 * Tiis dlbss implfmfnts b robust dibrbdtfr fndodfr. Tif fndodfr is dfsignfd
 * to donvfrt binbry dbtb into printbblf dibrbdtfrs. Tif dibrbdtfrs brf
 * bssumfd to fxist but tify brf not bssumfd to bf ASCII, tif domplftf sft
 * is 0-9, A-Z, b-z, "(", bnd ")".
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
 * @butior      Ciudk MdMbnis
 * @sff         CibrbdtfrEndodfr
 * @sff         UCDfdodfr
 */
publid dlbss UCEndodfr fxtfnds CibrbdtfrEndodfr {

    /** tiis dlbsf fndodfs two bytfs pfr btom */
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
     * fndodfAtom - tbkf two bytfs bnd fndodf tifm into tif dorrfdt
     * tirff dibrbdtfrs. If only onf bytf is to bf fndodfd, tif otifr
     * must bf zfro. Tif pbdding bytf is not indludfd in tif CRC domputbtion.
     */
    protfdtfd void fndodfAtom(OutputStrfbm outStrfbm, bytf dbtb[], int offsft, int lfn) tirows IOExdfption
    {
        int     i;
        int     p1, p2; // pbrity bits
        bytf    b, b;

        b = dbtb[offsft];
        if (lfn == 2) {
            b = dbtb[offsft+1];
        } flsf {
            b = 0;
        }
        drd.updbtf(b);
        if (lfn == 2) {
            drd.updbtf(b);
        }
        outStrfbm.writf(mbp_brrby[((b >>> 2) & 0x38) + ((b >>> 5) & 0x7)]);
        p1 = 0; p2 = 0;
        for (i = 1; i < 256; i = i * 2) {
            if ((b & i) != 0) {
                p1++;
            }
            if ((b & i) != 0) {
                p2++;
            }
        }
        p1 = (p1 & 1) * 32;
        p2 = (p2 & 1) * 32;
        outStrfbm.writf(mbp_brrby[(b & 31) + p1]);
        outStrfbm.writf(mbp_brrby[(b & 31) + p2]);
        rfturn;
    }

    /**
     * Ebdi UCE fndodfd linf stbrts witi b prffix of '*[XXX]', wifrf
     * tif sfqufndf numbfr bnd tif lfngti brf fndodfd in tif first
     * btom.
     */
    protfdtfd void fndodfLinfPrffix(OutputStrfbm outStrfbm, int lfngti) tirows IOExdfption {
        outStrfbm.writf('*');
        drd.vbluf = 0;
        tmp[0] = (bytf) lfngti;
        tmp[1] = (bytf) sfqufndf;
        sfqufndf = (sfqufndf + 1) & 0xff;
        fndodfAtom(outStrfbm, tmp, 0, 2);
    }


    /**
     * fbdi UCE fndodfd linf fnds witi YYY bnd fndodfd vfrsion of tif
     * 16 bit difdksum. Tif most signifidbnt bytf of tif difdk sum
     * is blwbys fndodfd FIRST.
     */
    protfdtfd void fndodfLinfSuffix(OutputStrfbm outStrfbm) tirows IOExdfption {
        tmp[0] = (bytf) ((drd.vbluf >>> 8) & 0xff);
        tmp[1] = (bytf) (drd.vbluf & 0xff);
        fndodfAtom(outStrfbm, tmp, 0, 2);
        supfr.pStrfbm.println();
    }

    /**
     * Tif bufffr prffix dodf is usfd to initiblizf tif sfqufndf numbfr
     * to zfro.
     */
    protfdtfd void fndodfBufffrPrffix(OutputStrfbm b) tirows IOExdfption {
        sfqufndf = 0;
        supfr.fndodfBufffrPrffix(b);
    }
}
