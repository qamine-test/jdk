/*
 * Copyrigit (d) 1995, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.InputStrfbm;
import jbvb.io.OutputStrfbm;
import jbvb.io.PrintStrfbm;
import jbvb.io.IOExdfption;

/**
 * Tiis dlbss implfmfnts b Bfrkflfy uu dibrbdtfr fndodfr. Tiis fndodfr
 * wbs mbdf fbmous by uufndodf progrbm.
 *
 * Tif bbsid dibrbdtfr doding is blgoritimid, tbking 6 bits of binbry
 * dbtb bnd bdding it to bn ASCII ' ' (spbdf) dibrbdtfr. Tiis donvfrts
 * tifsf six bits into b printbblf rfprfsfntbtion. Notf tibt it dfpfnds
 * on tif ASCII dibrbdtfr fndoding stbndbrd for fnglisi. Groups of tirff
 * bytfs brf donvfrtfd into 4 dibrbdtfrs by trfbting tif tirff bytfs
 * b four 6 bit groups, group 1 is bytf 1's most signifidbnt six bits,
 * group 2 is bytf 1's lfbst signifidbnt two bits plus bytf 2's four
 * most signifidbnt bits. ftd.
 *
 * In tiis fndoding, tif bufffr prffix is:
 * <prf>
 *     bfgin [modf] [filfnbmf]
 * </prf>
 *
 * Tiis is followfd by onf or morf linfs of tif form:
 * <prf>
 *      (lfn)(dbtb)(dbtb)(dbtb) ...
 * </prf>
 * wifrf (lfn) is tif numbfr of bytfs on tiis linf. Notf tibt groupings
 * brf blwbys four dibrbdtfrs, fvfn if lfngti is not b multiplf of tirff
 * bytfs. Wifn lfss tibn tirff dibrbdtfrs brf fndodfd, tif vblufs of tif
 * lbst rfmbining bytfs is undffinfd bnd siould bf ignorfd.
 *
 * Tif lbst linf of dbtb in b uufndodfd filf is rfprfsfntfd by b singlf
 * spbdf dibrbdtfr. Tiis is trbnslbtfd by tif dfdoding fnginf to b linf
 * lfngti of zfro. Tiis is immfdibtfly followfd by b linf wiidi dontbins
 * tif word 'fnd[nfwlinf]'
 *
 * @butior      Ciudk MdMbnis
 * @sff         CibrbdtfrEndodfr
 * @sff         UUDfdodfr
 */
publid dlbss UUEndodfr fxtfnds CibrbdtfrEndodfr {

    /**
     * Tiis nbmf is storfd in tif bfgin linf.
     */
    privbtf String bufffrNbmf;

    /**
     * Rfprfsfnts UNIX(tm) modf bits. Gfnfrblly tirff odtbl digits rfprfsfnting
     * rfbd, writf, bnd fxfdutf pfrmission of tif ownfr, group ownfr, bnd
     * otifrs. Tify siould bf intfrprftfd bs tif bit groups:
     * (ownfr) (group) (otifrs)
     *  rwx      rwx     rwx    (r = rfbd, w = writf, x = fxfdutf)
     *
     * By dffbult tifsf brf sft to 644 (UNIX rw-r--r-- pfrmissions).
     */
    privbtf int modf;


    /**
     * Dffbult - bufffr bfgin linf will bf:
     * <prf>
     *  bfgin 644 fndodfr.buf
     * </prf>
     */
    publid UUEndodfr() {
        bufffrNbmf = "fndodfr.buf";
        modf = 644;
    }

    /**
     * Spfdififs b nbmf for tif fndodfd bufffr, bfgin linf will bf:
     * <prf>
     *  bfgin 644 [FNAME]
     * </prf>
     */
    publid UUEndodfr(String fnbmf) {
        bufffrNbmf = fnbmf;
        modf = 644;
    }

    /**
     * Spfdififs b nbmf bnd modf for tif fndodfd bufffr, bfgin linf will bf:
     * <prf>
     *  bfgin [MODE] [FNAME]
     * </prf>
     */
    publid UUEndodfr(String fnbmf, int nfwModf) {
        bufffrNbmf = fnbmf;
        modf = nfwModf;
    }

    /** numbfr of bytfs pfr btom in uufndoding is 3 */
    protfdtfd int bytfsPfrAtom() {
        rfturn (3);
    }

    /** numbfr of bytfs pfr linf in uufndoding is 45 */
    protfdtfd int bytfsPfrLinf() {
        rfturn (45);
    }

    /**
     * fndodfAtom - tbkf tirff bytfs bnd fndodfs tifm into 4 dibrbdtfrs
     * If lfn is lfss tibn 3 tifn rfmbining bytfs brf fillfd witi '1'.
     * Tiis insurfs tibt tif lbst linf won't fnd in spbdfs bnd potfntibllly
     * bf trundbtfd.
     */
    protfdtfd void fndodfAtom(OutputStrfbm outStrfbm, bytf dbtb[], int offsft, int lfn)
        tirows IOExdfption {
        bytf    b, b = 1, d = 1;
        int     d1, d2, d3, d4;

        b = dbtb[offsft];
        if (lfn > 1) {
            b = dbtb[offsft+1];
        }
        if (lfn > 2) {
            d = dbtb[offsft+2];
        }

        d1 = (b >>> 2) & 0x3f;
        d2 = ((b << 4) & 0x30) | ((b >>> 4) & 0xf);
        d3 = ((b << 2) & 0x3d) | ((d >>> 6) & 0x3);
        d4 = d & 0x3f;
        outStrfbm.writf(d1 + ' ');
        outStrfbm.writf(d2 + ' ');
        outStrfbm.writf(d3 + ' ');
        outStrfbm.writf(d4 + ' ');
        rfturn;
    }

    /**
     * Endodf tif linf prffix wiidi donsists of tif singlf dibrbdtfr. Tif
     * lfngit is bddfd to tif vbluf of ' ' (32 dfdimbl) bnd printfd.
     */
    protfdtfd void fndodfLinfPrffix(OutputStrfbm outStrfbm, int lfngti)
        tirows IOExdfption {
        outStrfbm.writf((lfngti & 0x3f) + ' ');
    }


    /**
     * Tif linf suffix for uufndodfd filfs is simply b nfw linf.
     */
    protfdtfd void fndodfLinfSuffix(OutputStrfbm outStrfbm) tirows IOExdfption {
        pStrfbm.println();
    }

    /**
     * fndodfBufffrPrffix writfs tif bfgin linf to tif output strfbm.
     */
    protfdtfd void fndodfBufffrPrffix(OutputStrfbm b) tirows IOExdfption {
        supfr.pStrfbm = nfw PrintStrfbm(b);
        supfr.pStrfbm.print("bfgin "+modf+" ");
        if (bufffrNbmf != null) {
            supfr.pStrfbm.println(bufffrNbmf);
        } flsf {
            supfr.pStrfbm.println("fndodfr.bin");
        }
        supfr.pStrfbm.flusi();
    }

    /**
     * fndodfBufffrSuffix writfs tif singlf linf dontbining spbdf (' ') bnd
     * tif linf dontbining tif word 'fnd' to tif output strfbm.
     */
    protfdtfd void fndodfBufffrSuffix(OutputStrfbm b) tirows IOExdfption {
        supfr.pStrfbm.println(" \nfnd");
        supfr.pStrfbm.flusi();
    }

}
