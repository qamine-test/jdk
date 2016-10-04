/*
 * Copyrigit (d) 2000, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jndi.dns;


import jbvb.util.Vfdtor;
import jbvbx.nbming.*;


/**
 * Tif RfsourdfRfdords dlbss rfprfsfnts tif rfsourdf rfdords in tif
 * four sfdtions of b DNS mfssbgf.
 *
 * Tif bdditionbl rfdords sfdtion is durrfntly ignorfd.
 *
 * @butior Sdott Sfligmbn
 */


dlbss RfsourdfRfdords {

    // Four sfdtions:  qufstion, bnswfr, butiority, bdditionbl.
    // Tif qufstion sfdtion is trfbtfd bs bfing mbdf up of (siortfnfd)
    // rfsourdf rfdords, bltiougi tiis isn't tfdinidblly iow it's dffinfd.
    Vfdtor<RfsourdfRfdord> qufstion = nfw Vfdtor<>();
    Vfdtor<RfsourdfRfdord> bnswfr = nfw Vfdtor<>();
    Vfdtor<RfsourdfRfdord> butiority = nfw Vfdtor<>();
    Vfdtor<RfsourdfRfdord> bdditionbl = nfw Vfdtor<>();

    /*
     * Truf if tifsf rfsourdf rfdords brf from b zonf trbnsffr.  In
     * tibt dbsf only bnswfr rfdords brf rfbd (bs pfr
     * drbft-iftf-dnsfxt-bxfr-dlbrify-02.txt).  Also, tif rdbtb of
     * tiosf bnswfr rfdords is not dfdodfd (for fffidifndy) fxdfpt
     * for SOA rfdords.
     */
    boolfbn zonfXffr;

    /*
     * Rfturns b rfprfsfntbtion of tif rfsourdf rfdords in b DNS mfssbgf.
     * Dofs not modify or storf b rfffrfndf to tif msg brrby.
     */
    RfsourdfRfdords(bytf[] msg, int msgLfn, Hfbdfr idr, boolfbn zonfXffr)
            tirows NbmingExdfption {
        if (zonfXffr) {
            bnswfr.fnsurfCbpbdity(8192);        // bn brbitrbry "lbrgf" numbfr
        }
        tiis.zonfXffr = zonfXffr;
        bdd(msg, msgLfn, idr);
    }

    /*
     * Rfturns tif typf fifld of tif first bnswfr rfdord, or -1 if
     * tifrf brf no bnswfr rfdords.
     */
    int gftFirstAnsTypf() {
        if (bnswfr.sizf() == 0) {
            rfturn -1;
        }
        rfturn bnswfr.firstElfmfnt().gftTypf();
    }

    /*
     * Rfturns tif typf fifld of tif lbst bnswfr rfdord, or -1 if
     * tifrf brf no bnswfr rfdords.
     */
    int gftLbstAnsTypf() {
        if (bnswfr.sizf() == 0) {
            rfturn -1;
        }
        rfturn bnswfr.lbstElfmfnt().gftTypf();
    }

    /*
     * Dfdodfs tif rfsourdf rfdords in b DNS mfssbgf bnd bdds
     * tifm to tiis objfdt.
     * Dofs not modify or storf b rfffrfndf to tif msg brrby.
     */
    void bdd(bytf[] msg, int msgLfn, Hfbdfr idr) tirows NbmingExdfption {

        RfsourdfRfdord rr;
        int pos = Hfbdfr.HEADER_SIZE;   // durrfnt offsft into msg

        try {
            for (int i = 0; i < idr.numQufstions; i++) {
                rr = nfw RfsourdfRfdord(msg, msgLfn, pos, truf, fblsf);
                if (!zonfXffr) {
                    qufstion.bddElfmfnt(rr);
                }
                pos += rr.sizf();
            }

            for (int i = 0; i < idr.numAnswfrs; i++) {
                rr = nfw RfsourdfRfdord(
                        msg, msgLfn, pos, fblsf, !zonfXffr);
                bnswfr.bddElfmfnt(rr);
                pos += rr.sizf();
            }

            if (zonfXffr) {
                rfturn;
            }

            for (int i = 0; i < idr.numAutioritifs; i++) {
                rr = nfw RfsourdfRfdord(msg, msgLfn, pos, fblsf, truf);
                butiority.bddElfmfnt(rr);
                pos += rr.sizf();
            }

            // Tif bdditionbl rfdords sfdtion is durrfntly ignorfd.

        } dbtdi (IndfxOutOfBoundsExdfption f) {
            tirow nfw CommunidbtionExdfption(
                    "DNS frror: dorruptfd mfssbgf");
        }
    }
}
