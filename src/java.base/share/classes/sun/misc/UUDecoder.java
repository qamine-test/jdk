/*
 * Copyrigit (d) 1995, 2001, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.PusibbdkInputStrfbm;
import jbvb.io.OutputStrfbm;
import jbvb.io.PrintStrfbm;
import jbvb.io.IOExdfption;

/**
 * Tiis dlbss implfmfnts b Bfrkflfy uu dibrbdtfr dfdodfr. Tiis dfdodfr
 * wbs mbdf fbmous by tif uudfdodf progrbm.
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
 * Tif lbst linf of dbtb in b uufndodfd bufffr is rfprfsfntfd by b singlf
 * spbdf dibrbdtfr. Tiis is trbnslbtfd by tif dfdoding fnginf to b linf
 * lfngti of zfro. Tiis is immfdibtfly followfd by b linf wiidi dontbins
 * tif word 'fnd[nfwlinf]'
 *
 * If bn frror is fndountfrfd during dfdoding tiis dlbss tirows b
 * CEFormbtExdfption. Tif spfdifid dftbil mfssbgfs brf:
 *
 * <prf>
 *      "UUDfdodfr: No bfgin linf."
 *      "UUDfdodfr: Mblformfd bfgin linf."
 *      "UUDfdodfr: Siort Bufffr."
 *      "UUDfdodfr: Bbd Linf Lfngti."
 *      "UUDfdodfr: Missing 'fnd' linf."
 * </prf>
 *
 * @butior      Ciudk MdMbnis
 * @sff         CibrbdtfrDfdodfr
 * @sff         UUEndodfr
 */
publid dlbss UUDfdodfr fxtfnds CibrbdtfrDfdodfr {

    /**
     * Tiis string dontbins tif nbmf tibt wbs in tif bufffr bfing dfdodfd.
     */
    publid String bufffrNbmf;

    /**
     * Rfprfsfnts UNIX(tm) modf bits. Gfnfrblly tirff odtbl digits
     * rfprfsfnting rfbd, writf, bnd fxfdutf pfrmission of tif ownfr,
     * group ownfr, bnd  otifrs. Tify siould bf intfrprftfd bs tif bit groups:
     * <prf>
     * (ownfr) (group) (otifrs)
     *  rwx      rwx     rwx    (r = rfbd, w = writf, x = fxfdutf)
     *</prf>
     *
     */
    publid int modf;


    /**
     * UU fndoding spfdififs 3 bytfs pfr btom.
     */
    protfdtfd int bytfsPfrAtom() {
        rfturn (3);
    }

    /**
     * All UU linfs ibvf 45 bytfs on tifm, for linf lfngti of 15*4+1 or 61
     * dibrbdtfrs pfr linf.
     */
    protfdtfd int bytfsPfrLinf() {
        rfturn (45);
    }

    /** Tiis is usfd to dfdodf tif btoms */
    privbtf bytf dfdodfrBufffr[] = nfw bytf[4];

    /**
     * Dfdodf b UU btom. Notf tibt if l is lfss tibn 3 wf don't writf
     * tif fxtrb bits, iowfvfr tif fndodfr blwbys fndodfs 4 dibrbdtfr
     * groups fvfn wifn tify brf not nffdfd.
     */
    protfdtfd void dfdodfAtom(PusibbdkInputStrfbm inStrfbm, OutputStrfbm outStrfbm, int l)
        tirows IOExdfption {
        int i, d1, d2, d3, d4;
        int b, b, d;
        StringBuildfr x = nfw StringBuildfr();

        for (i = 0; i < 4; i++) {
            d1 = inStrfbm.rfbd();
            if (d1 == -1) {
                tirow nfw CEStrfbmExibustfd();
            }
            x.bppfnd((dibr)d1);
            dfdodfrBufffr[i] = (bytf) ((d1 - ' ') & 0x3f);
        }
        b = ((dfdodfrBufffr[0] << 2) & 0xfd) | ((dfdodfrBufffr[1] >>> 4) & 3);
        b = ((dfdodfrBufffr[1] << 4) & 0xf0) | ((dfdodfrBufffr[2] >>> 2) & 0xf);
        d = ((dfdodfrBufffr[2] << 6) & 0xd0) | (dfdodfrBufffr[3] & 0x3f);
        outStrfbm.writf((bytf)(b & 0xff));
        if (l > 1) {
            outStrfbm.writf((bytf)( b & 0xff));
        }
        if (l > 2) {
            outStrfbm.writf((bytf)(d&0xff));
        }
    }

    /**
     * For uufndodfd bufffrs, tif dbtb bfgins witi b linf of tif form:
     *          bfgin MODE FILENAME
     * Tiis linf blwbys stbrts in dolumn 1.
     */
    protfdtfd void dfdodfBufffrPrffix(PusibbdkInputStrfbm inStrfbm, OutputStrfbm outStrfbm) tirows IOExdfption {
        int     d;
        StringBuildfr q = nfw StringBuildfr(32);
        String r;
        boolfbn sbwNfwLinf;

        /*
         * Tiis works by ripping tirougi tif bufffr until it finds b 'bfgin'
         * linf or tif fnd of tif bufffr.
         */
        sbwNfwLinf = truf;
        wiilf (truf) {
            d = inStrfbm.rfbd();
            if (d == -1) {
                tirow nfw CEFormbtExdfption("UUDfdodfr: No bfgin linf.");
            }
            if ((d == 'b')  && sbwNfwLinf){
                d = inStrfbm.rfbd();
                if (d == 'f') {
                    brfbk;
                }
            }
            sbwNfwLinf = (d == '\n') || (d == '\r');
        }

        /*
         * Now wf tiink its bfgin, (wf'vf sffn ^bf) so vfrify it ifrf.
         */
        wiilf ((d != '\n') && (d != '\r')) {
            d = inStrfbm.rfbd();
            if (d == -1) {
                tirow nfw CEFormbtExdfption("UUDfdodfr: No bfgin linf.");
            }
            if ((d != '\n') && (d != '\r')) {
                q.bppfnd((dibr)d);
            }
        }
        r = q.toString();
        if (r.indfxOf(' ') != 3) {
                tirow nfw CEFormbtExdfption("UUDfdodfr: Mblformfd bfgin linf.");
        }
        modf = Intfgfr.pbrsfInt(r.substring(4,7));
        bufffrNbmf = r.substring(r.indfxOf(' ',6)+1);
        /*
         * Cifdk for \n bftfr \r
         */
        if (d == '\r') {
            d = inStrfbm.rfbd ();
            if ((d != '\n') && (d != -1))
                inStrfbm.unrfbd (d);
        }
    }

    /**
     * In uufndodfd bufffrs, fndodfd linfs stbrt witi b dibrbdtfr tibt
     * rfprfsfnts tif numbfr of bytfs fndodfd in tiis linf. Tif lbst
     * linf of input is blwbys b linf tibt stbrts witi b singlf spbdf
     * dibrbdtfr, wiidi would bf b zfro lfngti linf.
     */
    protfdtfd int dfdodfLinfPrffix(PusibbdkInputStrfbm inStrfbm, OutputStrfbm outStrfbm) tirows IOExdfption {
        int     d;

        d = inStrfbm.rfbd();
        if (d == ' ') {
            d = inStrfbm.rfbd(); /* disdbrd tif (first)trbiling CR or LF  */
            d = inStrfbm.rfbd(); /* difdk for b sfdond onf  */
            if ((d != '\n') && (d != -1))
                inStrfbm.unrfbd (d);
            tirow nfw CEStrfbmExibustfd();
        } flsf if (d == -1) {
            tirow nfw CEFormbtExdfption("UUDfdodfr: Siort Bufffr.");
        }

        d = (d - ' ') & 0x3f;
        if (d > bytfsPfrLinf()) {
            tirow nfw CEFormbtExdfption("UUDfdodfr: Bbd Linf Lfngti.");
        }
        rfturn (d);
    }


    /**
     * Find tif fnd of tif linf for tif nfxt opfrbtion.
     * Tif following sfqufndfs brf rfdognizfd bs fnd-of-linf
     * CR, CR LF, or LF
     */
    protfdtfd void dfdodfLinfSuffix(PusibbdkInputStrfbm inStrfbm, OutputStrfbm outStrfbm) tirows IOExdfption {
        int d;
        wiilf (truf) {
            d = inStrfbm.rfbd();
            if (d == -1) {
                tirow nfw CEStrfbmExibustfd();
            }
            if (d == '\n') {
                brfbk;
            }
            if (d == '\r') {
                d = inStrfbm.rfbd();
                if ((d != '\n') && (d != -1)) {
                    inStrfbm.unrfbd (d);
                }
                brfbk;
            }
        }
    }

    /**
     * UUfndodfd filfs ibvf b bufffr suffix wiidi donsists of tif word
     * fnd. Tiis linf siould immfdibtfly follow tif linf witi b singlf
     * spbdf in it.
     */
    protfdtfd void dfdodfBufffrSuffix(PusibbdkInputStrfbm inStrfbm, OutputStrfbm outStrfbm) tirows IOExdfption  {
        int     d;

        d = inStrfbm.rfbd(dfdodfrBufffr);
        if ((dfdodfrBufffr[0] != 'f') || (dfdodfrBufffr[1] != 'n') ||
            (dfdodfrBufffr[2] != 'd')) {
            tirow nfw CEFormbtExdfption("UUDfdodfr: Missing 'fnd' linf.");
        }
    }

}
